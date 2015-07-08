package in.wtc.hcis.billing.process;

import in.wtc.billing.bm.AccountBillData;
import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.billing.processes.Accounting;
import in.wtc.hcis.bo.context.AppContext;
import in.wtc.hcis.bo.context.ApplicationContextProvider;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.insurance.InsuranceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl._3e.adinterface.extn.ModelADServiceClientExtn;
import pl._3e.adinterface.extn.ModelServiceConstants;

public class AccountingProcessImpl implements Accounting {

	private static final String ACCOUNTING_PROCESS="ACCOUNTING";
	private final static String ACCOUNTING_SUBPROCESS_PAYMENT = "PAYMENT";
	private final static String ACCOUNTING_SUBPROCESS_RECEIPT = "RECEIPT";
	private final static String REFERENCE_TYPE_IPD = "IPD";
	
	public AccountBillData applyCreditResiduals(String referenceType,
			String accountId, Long billNumber, boolean evaluateDeposits) {

		AccountBillData accountBillData = new AccountBillData();
		
//		 Invoke the web service
		ModelADServiceClientExtn client = new ModelADServiceClientExtn();

		try {
			//First run auto allocation
			client.runAutoAllocation(accountId);
			
			//Now get total allocated amount for this Invoice 
			List< Map<String,String> > result = client.getAllocationLine(Integer.valueOf(accountId), billNumber.intValue());

			Double totalAssigedAmount = 0.0;
			
			if( result != null && !result.isEmpty()){

				for( Map<String,String> resultMap : result ){
					
					
					totalAssigedAmount=totalAssigedAmount+Double.valueOf( resultMap.get(ModelServiceConstants.COLUMN_ALLOCATION_AMOUNT));
				}
			}
			//
			//If we are billing an IPD then process the Med-claim
			
			if( evaluateDeposits && referenceType.equals(AccountingProcessImpl.REFERENCE_TYPE_IPD)){
				InsuranceManager insuranceManager = (InsuranceManager) AppContext.getApplicationContext().getBean("InsuranceManager");
				BillDataBM billDataBM = insuranceManager.processMediclaim(referenceType, accountId, billNumber);
				accountBillData.setBillDataBM(billDataBM);
			}
			
			accountBillData.setTotalAssignedAmount( totalAssigedAmount );
			return accountBillData;
			
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException(fault, e);
		}
		
	}

	public double createReceivables(double totalChargeAmt, Long billNumber,
			String memo, String referenceType, String accountId) {
		
		
		ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
		
		clientExtn.updateInvoice(billNumber.intValue(), totalChargeAmt);
		
		
		
//		Map<String, String> valuesMap = new HashMap<String, String>();
//		
//		valuesMap.put(ACCOUNT_ID, String.valueOf(accountId));
//		valuesMap.put("GrandTotal", String.valueOf(totalChargeAmt));
//		valuesMap.put("Description", memo);
//		valuesMap.put(INVOICE_TYPE, INVOICE_TYPE_VALUE);//default for customer invoice  
//		// Invoke the web service
//		ModelADServiceClient client = new ModelADServiceClient();
//		Integer recordID;
//		try {
//			recordID = client.invokeEagleWebService(client, valuesMap, "createInvoice");
//		} catch (Exception e) {
//			Fault fault = new Fault();
//			throw new HCISException(fault, e);
//		}
		

//		//To get all open receivable
//		ModelADServiceClientExtn client2 = new ModelADServiceClientExtn();
//		List<Map<String,String>> result = null;
//		Double totalInvoicedAmount = 0.0;
//		try {
//			result = client2.getUnpaidInvoice(Integer.valueOf(accountId));
//			
//			if( result != null && !result.isEmpty()){
//				
//				for( Map<String,String> resultMap : result ){
//					
//					totalInvoicedAmount = totalInvoicedAmount + 
//									Double.valueOf(resultMap.get("GrandTotol"));//this is output column 
//				}
//			}
//		} catch (Exception e) {
//			Fault fault = new Fault("Get invoice failed");
//			throw new HCISException(fault, e);
//		}
//
		
		return totalChargeAmt;
	}

	public BillDataBM getBillData(Long billNumber) {
		// TODO Auto-generated method stub
		return null;
	}
/**
 * Here we will run billing for Eagle accounting system.
 * 
 * 1)First of all we get the billable payments-
	  Billable payments are those payments
	  which needs to be billed during this process.To determine this Eagle follows 
	  date range comparison, take the last invoice date as bill-start date and current date as bill-end date.
	  If there is no Invoice created to the given accountId(BP Id) then consider the account creation date as
	  bill-start date.
	 
	  Now Prepare the billSubprocessBM accordingly by keeping contribute_to_financial_charges flag true. 
 * 
 * 2)Get all billable receipts-
 *   Find all receipts in similar way that we applied for payments.Prepare another biilSubProcessBM
 *   accordingly by keeping contribute_to_financial_charges flag false.
 *   
 */
	public BillDataBM runBilling(String acAccountId, String referenceType,
								 String accountId, Long billNumber)throws Exception {
		
		try {
			
			Integer BPartenerId = Integer.valueOf( acAccountId );
			
			//Get the billable payments
			
			ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
	//
	//To determine bill from date get all invoices except the invoice generated
	//for this billing process
			
			List<Map<String,String>> result = clientExtn.getInoice( BPartenerId);
			
//			result = clientExtn.getUnpaidInvoice(Integer.valueOf(accountId));
			
			SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
			Calendar billFromDate = null;
			
			 
			if( result != null && !result.isEmpty()){
				
				billFromDate = Calendar.getInstance();
				billFromDate.setTimeInMillis( 0 );
				
				for( Map<String,String> resultMap : result ){
					
					//we need to exclude the current Invoice
					if( billNumber != Long.valueOf(resultMap.get(ModelServiceConstants.COLUMN_INVOICE_ID))){
						
						Date tmpDate = dateFormat.parse(resultMap.get(ModelServiceConstants.COLUMN_INVOICE_CREATE_DTM));
						if( tmpDate.after(billFromDate.getTime()) ){
							
							billFromDate.setTime(tmpDate);
						}
					}
				}
			}else{
				
				result = clientExtn.getBPartener(BPartenerId);
				if( result != null && !result.isEmpty()){
					
					billFromDate = Calendar.getInstance();
					
					for( Map<String,String> resultMap : result ){
						
							Date tmpDate = dateFormat.parse(resultMap.get(ModelServiceConstants.COLUMN_BPARTNER_CREATE_DTM));
							if( tmpDate.after(billFromDate.getTime()) ){
								
								billFromDate.setTime(tmpDate);
							}
					}
			}
		}
			
			//
			//Now we have bill-start with us, so first get billable payments
			//
			BillDataBM billDataBM = this.initializeBillData();
			Map<String, BillingSubprocessBM>billDetailsMap = billDataBM.getBillDetailsMap();
			
			List<Map<String,String>> paymentsList = clientExtn.getPayments(BPartenerId, billFromDate.getTime(), new Date());
			
			if( paymentsList != null && !paymentsList.isEmpty()){
				
				//
				// This is a payment from the business to the account (usually customer). Hence this may 
				// contribute to financial charge.
				//
				BillingSubprocessBM billingSubprocessBM = this.initializeBillingSubProcess( ACCOUNTING_SUBPROCESS_PAYMENT, Boolean.TRUE );
				
				Double subprocesTotal = 0.0;
				List<BillDetailsBM>billDetailsList = billingSubprocessBM.getBillDetailsList();
				
				for( Map<String,String> resultMap : paymentsList ){

						BillDetailsBM billDetailsBM = this.convertPaymentMapToBillDetailsBM(resultMap);
						billDetailsBM.setItemSequenceNbr( billDetailsList.size() + 1 );
						billDetailsList.add(billDetailsBM);
						subprocesTotal += billDetailsBM.getNetPrice();
					}
					
					billingSubprocessBM.setSubProcessTotals( subprocesTotal );
					billDetailsMap.put( ACCOUNTING_SUBPROCESS_PAYMENT, billingSubprocessBM );
					
					billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() 
														+ billingSubprocessBM.getSubProcessTotals());
				}
			//
			// Bill receipts
			//
			List<Map<String,String>> receiptsList = clientExtn.getReceipts(BPartenerId, billFromDate.getTime(), new Date());
			if ( receiptsList != null && !receiptsList.isEmpty() ) {
				//
				// This is a receipts from the business to the account (usually customer). Hence this may 
				// contribute to financial charge.
				//
				BillingSubprocessBM billingSubprocessBM = this.initializeBillingSubProcess( ACCOUNTING_SUBPROCESS_RECEIPT, Boolean.FALSE );
				List<BillDetailsBM>billDetailsList = billingSubprocessBM.getBillDetailsList();
				Double subprocesTotal = 0.0;
				
				for( Map<String,String> resultMap : receiptsList ){

					BillDetailsBM billDetailsBM = this.convertPaymentMapToBillDetailsBM(resultMap);
					billDetailsBM.setItemSequenceNbr( billDetailsList.size() + 1 );
					billDetailsList.add(billDetailsBM);
					subprocesTotal += billDetailsBM.getNetPrice();
				}
				
				
				billingSubprocessBM.setSubProcessTotals( subprocesTotal );
				billDetailsMap.put( ACCOUNTING_SUBPROCESS_RECEIPT, billingSubprocessBM );
				billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() 
													+ billingSubprocessBM.getSubProcessTotals());
			}
			
			return billDataBM;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}catch (Exception e ){
			e.printStackTrace();
			throw e;
		}
	}
	
	public Integer createInvoice( String acAccountId, Double amount,String description ) throws Exception {
		
		try {
			ModelADServiceClientExtn client = new ModelADServiceClientExtn();
			
			return client.createInvoice(Integer.valueOf(acAccountId), amount, description);
		} catch (Exception e) {

			e.printStackTrace();
			throw e;
		}
	}
	
	private BillDataBM initializeBillData() {
		BillDataBM billDataBM = new BillDataBM();
		billDataBM.setProcessName( ACCOUNTING_PROCESS );
		billDataBM.setProcessTotalBillAmount(0.0);
		Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);
		billDataBM.setBillDetailsMap(billDetailsMap);
		
		return billDataBM;
	}
	
	private BillingSubprocessBM initializeBillingSubProcess( String subProcessName, Boolean contributesToCharge ) {
		BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
		billingSubprocessBM.setContributeToFnclCharge( contributesToCharge );
		billingSubprocessBM.setRemarks("");
		billingSubprocessBM.setSubProcessTotals(0.0);
		billingSubprocessBM.setSubProcessName( subProcessName );
		
		List<BillDetailsBM>billDetailsList = new ArrayList<BillDetailsBM>(0);
		billingSubprocessBM.setBillDetailsList(billDetailsList);
		
		return billingSubprocessBM;
	}
	
	private BillDetailsBM convertPaymentMapToBillDetailsBM( Map<String,String> paymentsMap ) throws Exception{
		BillDetailsBM billDetailsBM = new BillDetailsBM();
		
		billDetailsBM.setDiscounts(0.0);
		billDetailsBM.setItemCount(1);
		billDetailsBM.setItemName(  paymentsMap.get(ModelServiceConstants.COLUMN_PAYMENT_ID));
		billDetailsBM.setItemPrice( Double.valueOf(paymentsMap.get(ModelServiceConstants.COLUMN_PAYMENT_AMT )));
		billDetailsBM.setItemSequenceNbr(1);
		billDetailsBM.setNetPrice( Double.valueOf(paymentsMap.get(ModelServiceConstants.COLUMN_PAYMENT_AMT )));
		
			SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
			billDetailsBM.setTransactionDate( dateFormat.parse( paymentsMap.get( ModelServiceConstants.COLUMN_PAYMENT_TRX_DTM)));
		
		
		return billDetailsBM;
	}
	
	
}
