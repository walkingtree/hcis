/**
 * 
 */
package in.wtc.hcis.billing.process;

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
import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.billing.processes.BillProcess;

/**
 * @author Sandeep
 *
 */
public class PharmacyProcessImpl implements BillProcess {
	
	private static final String PHARMACY_PROCESS="PHARMACY";
//	private final static String PHARMACY_SUBPROCESS = "PHA";

	/* (non-Javadoc)
	 * @see in.wtc.billing.processes.BillProcess#getBillData(java.lang.Long)
	 */
	public BillDataBM getBillData(Long billNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.wtc.billing.processes.BillProcess#runBilling(java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 */
	public BillDataBM runBilling(String acAccountId, String referenceType,
			String accountId, Long billNumber){
		
		try {
			
			Integer BPartenerId = Integer.valueOf( acAccountId );
			
			//Get the billable payments
			
			ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
			//
			//To determine bill from date get all invoices except the invoice generated
			//for this billing process
			
			List<Map<String,String>> result = clientExtn.getInoice( BPartenerId);
			
			//result = clientExtn.getUnpaidInvoice(Integer.valueOf(accountId));
			
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
			
			List<Map<String,String>> paymentsList = clientExtn.getOrders(BPartenerId, billFromDate.getTime(), new Date());
			
			if( paymentsList != null && !paymentsList.isEmpty()){
				
				//
				// This is a payment from the business to the account (usually customer). Hence this may 
				// contribute to financial charge.
				//
				BillingSubprocessBM billingSubprocessBM = this.initializeBillingSubProcess( PHARMACY_PROCESS, Boolean.TRUE );
				
				Double subprocesTotal = 0.0;
				List<BillDetailsBM>billDetailsList = billingSubprocessBM.getBillDetailsList();
				
				for( Map<String,String> resultMap : paymentsList ){

						BillDetailsBM billDetailsBM = this.convertOrderMapToBillDetailsBM(resultMap);
						billDetailsBM.setItemSequenceNbr( billDetailsList.size() + 1 );
						billDetailsList.add(billDetailsBM);
						subprocesTotal += billDetailsBM.getNetPrice();
					}
					
					billingSubprocessBM.setSubProcessTotals( subprocesTotal );
					billDetailsMap.put( PHARMACY_PROCESS, billingSubprocessBM );
					
					billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() 
														+ billingSubprocessBM.getSubProcessTotals());
				}
			
			return billDataBM;
		}catch (Exception e ){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private BillDataBM initializeBillData() {
		BillDataBM billDataBM = new BillDataBM();
		billDataBM.setProcessName( PHARMACY_PROCESS );
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
	
	private BillDetailsBM convertOrderMapToBillDetailsBM( Map<String,String> orderMap ) throws Exception{
		BillDetailsBM billDetailsBM = new BillDetailsBM();
		
		billDetailsBM.setDiscounts(0.0);
		billDetailsBM.setItemCount(1);
		billDetailsBM.setItemName(  orderMap.get(ModelServiceConstants.COLUMN_ORDER_ID));
		billDetailsBM.setItemPrice( Double.valueOf(orderMap.get(ModelServiceConstants.COLUMN_ORDER_GRAND_TOTAL )));
		billDetailsBM.setItemSequenceNbr(1);
		billDetailsBM.setNetPrice( Double.valueOf(orderMap.get(ModelServiceConstants.COLUMN_ORDER_GRAND_TOTAL )));
		
			SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
			billDetailsBM.setTransactionDate( dateFormat.parse( orderMap.get( ModelServiceConstants.COLUMN_ORDER_TRX_DTM)));
		
		
		return billDetailsBM;
	}
	
	
}
