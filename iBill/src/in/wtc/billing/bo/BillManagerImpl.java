/**
 * 
 */
package in.wtc.billing.bo;

import in.wtc.billing.bm.AccountBillData;
import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillObjectBM;
import in.wtc.billing.bm.BillOverviewBM;
import in.wtc.billing.bm.BillSummaryBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.billing.bm.FnclChargesBM;
import in.wtc.billing.bm.FnclTransctBM;
import in.wtc.billing.constants.BillingConstants;
import in.wtc.billing.exception.BillException;
import in.wtc.billing.exception.Fault;
import in.wtc.billing.util.BillCalendar;
import in.wtc.billing.util.ListRange;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wtc.hcis.billing.da.BillActivity;
import com.wtc.hcis.billing.da.BillActivityDAO;
import com.wtc.hcis.billing.da.BillActivityDetails;
import com.wtc.hcis.billing.da.BillActivityDetailsDAO;
import com.wtc.hcis.billing.da.BillInfo;
import com.wtc.hcis.billing.da.BillItemDetails;
import com.wtc.hcis.billing.da.BillItemDetailsId;
import com.wtc.hcis.billing.da.BillProcessRouting;
import com.wtc.hcis.billing.da.BillRegister;
import com.wtc.hcis.billing.da.BillSetting;
import com.wtc.hcis.billing.da.BillSettingDAO;
import com.wtc.hcis.billing.da.FnclCharge;
import com.wtc.hcis.billing.da.FnclChargeDAO;
import com.wtc.hcis.billing.da.FnclChargeType;
import com.wtc.hcis.billing.da.FnclTrnsctSummary;
import com.wtc.hcis.billing.da.FnclTrnsctSummaryDAO;
import com.wtc.hcis.billing.da.extn.BillInfoDAOExtn;
import com.wtc.hcis.billing.da.extn.BillItemDetailsDAOExtn;
import com.wtc.hcis.billing.da.extn.BillProcessRoutingDAOExtn;
import com.wtc.hcis.billing.da.extn.BillRegisterDAOExtn;
import com.wtc.hcis.billing.da.extn.FnclChargeTypeDAOExtn;

/**
 * @author Alok Ranjan
 *
 */
public class BillManagerImpl implements BillManager {
	
	private BillSettingDAO billSettingDAO;
	private BillInfoDAOExtn billInfoDAO;
	private BillRegisterDAOExtn billRegisterDAO;
	private BillProcessRoutingDAOExtn billProcessRoutingDAO;
	private FnclChargeTypeDAOExtn fnclChargeTypeDAO;
	private FnclChargeDAO fnclChargeDAO;
	private BillItemDetailsDAOExtn billItemDetailsDAO;
	private BillActivityDetailsDAO billActivityDetailsDAO;
	private FnclTrnsctSummaryDAO fnclTrnsctSummaryDAO;
	private BillActivityDAO billActivityDAO;
	private BillProcessAdapter processAdapter;
	
	
	/**
	 * This method assumes that process is registered with the billing.
	 * 
	 * @param processName
	 * @return
	 */
	private BillRegister getBillRegister( String processName ) {
		BillRegister billRegister = billRegisterDAO.findById( processName );
		
		if ( billRegister != null ) {
			return billRegister;
		} else {
			Fault fault = new Fault( BillingConstants.PROCESS_NOT_REGISTERED );
			throw new BillException( fault.getFaultMessage() 
					                    + " PROCESS_NAME = " 
					                    + processName, 
									 fault.getFaultCode(),
									 fault.getFaultType()  );
		}
	}

	/**
	 * This method assumes that financial charge type already exist.
	 * 
	 * @param fnclChargeTypeId
	 * @return
	 */
	private FnclChargeType getFnclChargeType( Integer fnclChargeTypeId ) {
		
		FnclChargeType fnclChargeType = fnclChargeTypeDAO.findById( fnclChargeTypeId );
		
		if ( fnclChargeType != null ) {
			return fnclChargeType;
			
		} else {
			Fault fault = new Fault( BillingConstants.CHARGE_TYPE_DOES_NOT_EXIST );
			throw new BillException( fault.getFaultMessage() 
					                    + " FNCL_CHARGE_TYPE_ID = " 
					                    + fnclChargeTypeId, 
									 fault.getFaultCode(),
									 fault.getFaultType()  );
		}
	}
	
	/**
	 * For a given bill number, this method returns BillInfo data model object
	 * 
	 * @param billNumber
	 * @return
	 * @throws BillException
	 */
	private BillInfo getBill( Long billNumber ) throws BillException {
		BillInfo billInfo = billInfoDAO.findById( billNumber );
		
		if ( billInfo != null ) {
			return billInfo;
		} else {
			Fault fault = new Fault( BillingConstants.BILL_DOES_NOT_EXIST );
			throw new BillException( fault.getFaultMessage() 
					                    + " BILL_NUMBER = " 
					                    + billNumber, 
									 fault.getFaultCode(),
									 fault.getFaultType()  );
		}
	}
	
	/* (non-Javadoc)
	 * @see in.wtc.billing.bo.BillManager#getBillDetails(java.math.Long)
	 */
	public BillObjectBM getBillDetails( Long billNumber )
			throws BillException {
		
		BillInfo billInfo = this.getBill(billNumber);
		
		BillObjectBM billObjectBM = this.convertBillInfoIntoBillObject( billInfo );
		
		return billObjectBM;
	}
	
	/**
	 * This method retrieves bill details for a given BillInfo object.
	 * 
	 * @param billInfo
	 * @return
	 */
	private BillObjectBM convertBillInfoIntoBillObject( BillInfo billInfo ) {
		BillObjectBM billObjectBM = new BillObjectBM();
		billObjectBM.setAccountId( billInfo.getAccountId() );
		billObjectBM.setBillAmount( billInfo.getBillAmt() );
		billObjectBM.setBillDueDate( billInfo.getDueDate() );
		billObjectBM.setBillNumber( billInfo.getBillNbr() );
		billObjectBM.setBillDate( billInfo.getBillDate());
		List<BillDataBM>billDataList = this.getBillDataList( billInfo.getBillNbr() );
		billObjectBM.setBillDataList(billDataList);
		
		return billObjectBM;
	}
	
	/**
	 * This method retrieves bill item details for the given bill.
	 * This method expects that bill item details are retrieved in following order
	 * 1) Process Wise
	 * 2) Sub-process wise
	 * 3) Item wise 
	 * 
	 * This method returns list of BillDataBM, which primarily contain the process information
	 * and a map of subprocess and corresponding object which contains list of bill item details 
	 * associated with the subprocess (Map<String, BillingSubprocessBM>).    
	 * 
	 * @param billNbr
	 * @return
	 */
	private List<BillDataBM>getBillDataList( Long billNbr ) {
		List<BillDataBM>billDataList = new ArrayList<BillDataBM>(0);
		List<BillItemDetails>billItemsDetailsList = billItemDetailsDAO.getBillItemDetails( billNbr, "" );
		
		if ( billItemsDetailsList != null && !billItemsDetailsList.isEmpty() ) {

			Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);;
			BillDataBM billDataBM = new BillDataBM();
			BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
			
			String processName = billItemsDetailsList.get(0).getId().getProcessName();
			String subProcessName = billItemsDetailsList.get(0).getId().getSubProcessName();
			Double processTotalBillAmount = 0.0d;
			Double subProcessTotalBillAmount = 0.0d;
			
			billDataBM.setProcessName( processName );
			billingSubprocessBM.setSubProcessName( subProcessName );
			List<BillDetailsBM>billDetailsList = new ArrayList<BillDetailsBM>(0);
			
			
			int billItemCount = 0;
			
			for ( BillItemDetails billItemDetails : billItemsDetailsList ) {
				
				billItemCount++;
				
				 String tempProcessName = billItemDetails.getId().getProcessName();
				 String tempSubProcessName = billItemDetails.getId().getSubProcessName();
				 
				 if ( !tempProcessName.equals( processName ) ) {
					 //
					 // We are iterating for bill items for the different process.
					 // We must add the earlier process into the BillDataBM list.
					 // Create new object corresponding to the next process and re-initialize
					 // the BillDataBM object.
					 // Also, you must add the last sub-process into the bill details map
					 //
					 //
					 billDataBM.setProcessTotalBillAmount( processTotalBillAmount );
					 billingSubprocessBM.setSubProcessTotals( subProcessTotalBillAmount );
					 billingSubprocessBM.setBillDetailsList(billDetailsList);
					 billDetailsMap.put( subProcessName , billingSubprocessBM );
					 billDataBM.setBillDetailsMap( billDetailsMap );
					 billDataList.add( billDataBM );
					 
					 billDataBM = new BillDataBM();
					 billDataBM.setProcessName( tempProcessName );
					 processName = tempProcessName;
					 processTotalBillAmount = 0.0d;
					 billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);
					 
					 billingSubprocessBM = new BillingSubprocessBM();
					 subProcessName = tempSubProcessName;
					 billingSubprocessBM.setSubProcessName(subProcessName);
					 subProcessTotalBillAmount = 0.0d;
					 billDetailsList = new ArrayList<BillDetailsBM>(0);
				 } 
					
				 if ( !tempSubProcessName.equals( subProcessName ) ) {
					 //
					 // This is different sub-process. Add the current subprocess details in the bill
					 // details map
					 //
					 billingSubprocessBM.setSubProcessTotals( subProcessTotalBillAmount );
					 billingSubprocessBM.setBillDetailsList(billDetailsList);
					 billDetailsMap.put(subProcessName, billingSubprocessBM);
					 
					 billingSubprocessBM = new BillingSubprocessBM();
					 subProcessTotalBillAmount = 0.0d;
					 subProcessName = tempSubProcessName;
					 billDetailsList = new ArrayList<BillDetailsBM>(0);
				 }
				 
				 processTotalBillAmount += billItemDetails.getTotalAmount();
				 subProcessTotalBillAmount += billItemDetails.getTotalAmount();
				 
				 BillDetailsBM billDetailsBM = this.convertBillItemDetails2BillDetails(billItemDetails);
				 billDetailsList.add(billDetailsBM);
				 
				 if ( billItemCount == billItemsDetailsList.size() ) {
					 //
					 // This is the last bill item in the last sub-process of the last 
					 // process. We will not be looping back again. Hence, add the sub-process into the 
					 // process detail and process detail into the bill items details list.
					 //
					 billDataBM.setProcessTotalBillAmount( processTotalBillAmount );
					 billingSubprocessBM.setSubProcessTotals( subProcessTotalBillAmount );
					 billingSubprocessBM.setBillDetailsList( billDetailsList );
					 billDetailsMap.put( subProcessName , billingSubprocessBM );
					 billDataBM.setBillDetailsMap( billDetailsMap );
					 billDataList.add( billDataBM );
				 }
			}
		}
		
		return billDataList;
	}
	
	private BillDetailsBM convertBillItemDetails2BillDetails( BillItemDetails billItemDetails ) {
		BillDetailsBM billDetailsBM = new BillDetailsBM();
		
		billDetailsBM.setDiscounts( billItemDetails.getDiscounts() );
		billDetailsBM.setItemCount( billItemDetails.getItemCount() );
		billDetailsBM.setItemName( billItemDetails.getItemName() );
		billDetailsBM.setItemPrice( billItemDetails.getItemPrice() );
		billDetailsBM.setItemSequenceNbr( billItemDetails.getId().getItemSequenceNbr() );
		billDetailsBM.setNetPrice( billItemDetails.getTotalAmount() );
		
		return billDetailsBM;
	}

	/* (non-Javadoc)
	 * @see in.wtc.billing.bo.BillManager#getBillDetails(java.lang.String)
	 */
	public List<BillObjectBM> getBillDetails( String accountId )
			throws BillException {
		
		List<BillObjectBM>billObjectList = null;
		List<BillInfo>billInfoList = billInfoDAO.findByAccountId( accountId );
		
		if ( billInfoList != null && !billInfoList.isEmpty() ) {
			billObjectList = new ArrayList<BillObjectBM>(0);
			
			for ( BillInfo billInfo : billInfoList ) {
				BillObjectBM billObjectBM = this.convertBillInfoIntoBillObject(billInfo);
				billObjectList.add( billObjectBM );
			}			
		} 

		return billObjectList;
	}
	public ListRange findBillDetails(String referenceNumber, String referenceType, int start, int limit,String orderBy ) throws BillException{
		
		try {
			
			List<BillObjectBM>billObjectList = null;
			ListRange listRange = new ListRange();
			
			if( referenceNumber != null && referenceNumber.length() > 0 
					&& referenceType != null && referenceType.length() >0 ){
			List<BillInfo>billInfoList = billInfoDAO.getBillInfo(referenceNumber, referenceType);
			
			billObjectList = new ArrayList<BillObjectBM>(0);
			
			if ( billInfoList != null && !billInfoList.isEmpty() ) {
				int index = 0;
				while( (start+index < start + limit) && (billInfoList.size() > start+index) ){
					BillInfo billInfo = billInfoList.get( start+index );
					BillObjectBM billObjectBM = this.convertBillInfoIntoBillObject(billInfo);
					billObjectList.add( billObjectBM );
					index ++;
				}			
			}
				listRange.setData( billObjectList.toArray() );
				listRange.setTotalSize(billObjectList.size());
			
			}else{
				listRange.setData( new Object[0] );
				listRange.setTotalSize(0);
			}
			
			return listRange;
			
		} catch (Exception e) {
			 Fault fault = new  Fault( BillingConstants.BILL_DOES_NOT_EXIST);
			 throw new BillException(fault);
		}
	}
	/* (non-Javadoc)
	 * @see in.wtc.billing.bo.BillManager#getBillOverview(java.math.Long)
	 */
	public BillOverviewBM getBillOverview(Long billNumber)
			throws BillException {
		BillInfo billInfo = this.getBill(billNumber);
		
		return this.convertBillInfo2BillOverviewBM(billInfo);
	}

	/**
	 * This method creates bill overview object. If there are any charge and / or transaction details 
	 * available for this bill then list of financial charges and transactions details are returned as well. 
	 * @param billInfo
	 * @return
	 */
	private BillOverviewBM convertBillInfo2BillOverviewBM( BillInfo billInfo ) {
		BillOverviewBM billOverviewBM = new BillOverviewBM();
		
		billOverviewBM.setAccountId( billInfo.getAccountId() );
		billOverviewBM.setBillAmount( billInfo.getBillAmt() );
		billOverviewBM.setBillDate( billInfo.getBillDate() );
		billOverviewBM.setBillDueDate( billInfo.getDueDate() );
		billOverviewBM.setBillNumber( billInfo.getBillNbr() );
		billOverviewBM.setDueAmount( billInfo.getCurrentBalance() );
		billOverviewBM.setPaidAmount( billInfo.getCurrentBalance() );
		
		List<FnclChargesBM> fnclChargesList = this.getFnclChargeList( billInfo.getBillNbr() );
		
		billOverviewBM.setFnclChargesList(fnclChargesList);
		
		List<FnclTransctBM>fnclTransactionsList = this.getFnclTrnsct( billInfo.getBillNbr() );
		
		billOverviewBM.setFnclTransactionsList(fnclTransactionsList);
		
		
		return billOverviewBM;
	}
	
	private List<FnclTransctBM> getFnclTrnsct(Long billNbr ) {
		List<FnclTransctBM> fnclTransactionsList = null;
		
		List<FnclTrnsctSummary>fnclTrnscSummaryList = fnclTrnsctSummaryDAO.findByProperty("id.billNbr", billNbr );
		
		if ( fnclTrnscSummaryList != null && !fnclTrnscSummaryList.isEmpty() ) {
			fnclTransactionsList = new ArrayList<FnclTransctBM>(0);
			
			for ( FnclTrnsctSummary fnclTrnsctSummary : fnclTrnscSummaryList ) {
				
				FnclTransctBM fnclTransctBM = this.convertFnclTrnsctSummaryDM2BM( fnclTrnsctSummary );
				fnclTransactionsList.add( fnclTransctBM );
			}
		}
		
		return fnclTransactionsList;
	}
	
	private FnclTransctBM convertFnclTrnsctSummaryDM2BM( FnclTrnsctSummary fnclTrnsctSummary ) {
		FnclTransctBM fnclTransctBM = new FnclTransctBM();
		fnclTransctBM.setAmount( fnclTrnsctSummary.getAmount() );
		fnclTransctBM.setCreditDebitInd( fnclTrnsctSummary.getCreditDebitInd() );
		fnclTransctBM.setBillNumber( fnclTrnsctSummary.getId().getBillNbr() );
		fnclTransctBM.setTransactionDate( fnclTrnsctSummary.getTransactionDate() );
		fnclTransctBM.setTransactionReference( fnclTrnsctSummary.getId().getTransactionReference() );
		fnclTransctBM.setTransactionType( fnclTrnsctSummary.getId().getTransactionType() );
		
		return fnclTransctBM;
	}
	
	private List<FnclChargesBM> getFnclChargeList( Long billNumber ) {
		List<FnclCharge> fnclChargeList = fnclChargeDAO.findByProperty("billInfo.billNbr", billNumber);
		
		List<FnclChargesBM>fnclChargeBMList = null; 
		
		if ( fnclChargeList != null && !fnclChargeList.isEmpty() ) {
			fnclChargeBMList = new ArrayList<FnclChargesBM>(0);
			
			for ( FnclCharge fnclCharge : fnclChargeList ) {
				FnclChargesBM fnclChargesBM = this.convertFnclChargeDM2BM( fnclCharge );
				fnclChargeBMList.add( fnclChargesBM );
			}
		}
		
		return fnclChargeBMList;
	}
	
	/**
	 * 
	 * @param fnclCharge
	 * @return
	 */
	private FnclChargesBM convertFnclChargeDM2BM( FnclCharge fnclCharge ) {
		FnclChargesBM fnclChargesBM = new FnclChargesBM();
		
		fnclChargesBM.setAccountId( fnclCharge.getAccountId() );
		fnclChargesBM.setBillNbr( fnclCharge.getBillInfo().getBillNbr() );
		fnclChargesBM.setChargeAmount( fnclCharge.getChargeAmount() );
		fnclChargesBM.setCreationDate( fnclCharge.getCreationDate() );
		
		FnclChargeType fnclChargeType = this.getFnclChargeType( fnclCharge.getFnclChargeType().getChargeTypeId() );
		fnclChargesBM.setChargeTypeName( fnclChargeType.getChargeTypeName() );
		fnclChargesBM.setProcessName( fnclChargeType.getProcessName() );
		fnclChargesBM.setRemarks( fnclCharge.getRemarks() );
		
		return fnclChargesBM;
	}
	
	
	
	/* (non-Javadoc)
	 * @see in.wtc.billing.bo.BillManager#getBillSummary(java.lang.String)
	 */
	public List<BillSummaryBM> getBillSummary(String accountId)
			throws BillException {
		List<BillSummaryBM>billSummaryList = null;
		List<BillInfo>billInfoList = billInfoDAO.findByAccountId( accountId );
		
		if ( billInfoList != null && !billInfoList.isEmpty() ) {
			billSummaryList = new ArrayList<BillSummaryBM>(0);
			
			for ( BillInfo billInfo : billInfoList ) {
				BillSummaryBM billSummaryBM = this.convertBillInfoIntoBillSummary(billInfo);
				billSummaryList.add( billSummaryBM );
			}			
		} 

		return billSummaryList;
	}
	
	/**
	 * 
	 * @param billInfo
	 * @return
	 */
	private BillSummaryBM convertBillInfoIntoBillSummary( BillInfo billInfo ) {
		BillSummaryBM billSummaryBM = new BillSummaryBM();
		
		billSummaryBM.setAccountId( billInfo.getAccountId() );
		billSummaryBM.setBillAmount( billInfo.getBillAmt() );
		billSummaryBM.setBillDueDate( billInfo.getDueDate() );
		billSummaryBM.setBillNumber( billInfo.getBillNbr() );
		billSummaryBM.setBillDate( billInfo.getBillDate() );
		billSummaryBM.setDueAmount( billInfo.getCurrentBalance() );
		
		return billSummaryBM;
	}
	

	/* (non-Javadoc)
	 * This method assumes that bill already exist in the system.
	 * 
	 * Since, duplicate bill printing may require some charge to be collected
	 * from the customer, the charge collection will be supported only when
	 * accounting process is integrated with the iBilling.
	 * 
	 * @see in.wtc.billing.bo.BillManager#printDuplicateBill(java.math.Long)
	 */
	public BillObjectBM printDuplicateBill( Long billNumber )
			throws BillException {
		
		BillInfo billInfo = this.getBill(billNumber);
		
		BillObjectBM billObjectBM = this.convertBillInfoIntoBillObject(billInfo);
		
		BillRegister accountingRegistered = billRegisterDAO.findById( BillingConstants.PROCESS_ACCOUNTING );
		
		Integer duplicateBillCount = 0;
		
		if( billInfo.getDuplicateBillPrintCount() != null ){
			duplicateBillCount = billInfo.getDuplicateBillPrintCount();
		}
		
		if ( accountingRegistered != null ) {
			BillSetting freeDuplicateBillCount = billSettingDAO.findById( BillingConstants.FREE_DUPLICATE_BILL_COUNT );
			BillSetting duplicateBillCharge = billSettingDAO.findById( BillingConstants.DUPLICATE_BILL_PRINT_CHARGE );
			boolean chargeForDuplicateBill = true;
			
			if ( freeDuplicateBillCount != null ) {
				
				if ( duplicateBillCount < Integer.parseInt( freeDuplicateBillCount.getAttrValue() ) ) {
					chargeForDuplicateBill = false;
				}
			}
			
			if ( chargeForDuplicateBill == true && duplicateBillCharge != null ) {
				processAdapter.createReceivables( Double.parseDouble( duplicateBillCharge.getAttrValue() ), 
						                          billNumber, 
						                          BillingConstants.DUPLICATE_BILL_MEMO,
						                          billInfo.getClientName(),
						                          billInfo.getAccountId() );
			}
			
		}
		
		billInfo.setDuplicateBillPrintCount(duplicateBillCount + 1 );
		billInfo.setLastModifiedTime( new Date() );
		billInfoDAO.attachDirty( billInfo );
		
		return billObjectBM;
	}

	/* (non-Javadoc)
	 * 
	 * For an account a bill can be run many times. Every time we run the billing, the system
	 * first creates a bill record and gives us a bill number. 
	 * 
	 * We use this bill number to mark the billable items to indicate that the items are 
	 * related to the given bill.
	 * 
	 * @see in.wtc.billing.bo.BillManager#runBill(java.lang.String)
	 */
	public BillObjectBM runBill(String acAccountId, String clientName, String referenceNbr ) throws BillException {
		
		try {
			BillInfo billInfo = this.createDummyBillRecord( acAccountId, referenceNbr, clientName );
			
			
			this.runRegisteredBillingProcesses( billInfo , acAccountId );
			
			this.updateBillAndAccountDetails( billInfo , acAccountId );
			
			return this.getBillDetails( billInfo.getBillNbr() );
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BillException();
		}
	}
	
	
	/**
	 * This method must be called after all the processes have stamped their billable items
	 * and financial charges have already been persisted.
	 * If the taxation process is also involved then first thing this method should do is 
	 * to apply relevant taxes on the financial charges.
	 * 
	 * Primarily, this method is supposed to do following:
	 * 1) Apply any taxes; if applicable
	 * 2) Create receivables in the accounting module
	 * 3) Apply residuals 
	 * 4) Update bill information
	 * 
	 * @param billInfo
	 */
	private void updateBillAndAccountDetails( BillInfo billInfo ,String  acAccountId ) {
		
		//
		// This method creates a receivable based on the financial charges billed against 
		// the current bill number. 
		//
		double totalRemainingBalance = this.createReceivables(billInfo , acAccountId);
		
		//
		// Apply credit method must return the total credit amount assigned to the
		// open receivables during this transaction.
		//
		double creditAmount = this.applyCreditResiduals( billInfo, acAccountId );
		
		//
		// Update the bill record with the appropriate bill amount, paid amount and 
		// current balance amount
		//
		this.updateBillDetails( creditAmount, totalRemainingBalance, billInfo.getBillNbr() );
	}
	
	/**
	 * Thie method primarily updated the bill record with appropriate bill amount and current
	 * balance amount. Current balance amount should be considered as due amount.
	 * Bill Amount = Current Balance Amount + Paid Amount
	 * 
	 * @param creditAmount
	 * @param totalRemainingBalance
	 * @param billNumber
	 */
	private void updateBillDetails( double creditAmount, double totalRemainingBalance, Long billNumber ) {
		BillInfo billInfo = billInfoDAO.findById( billNumber );
		
		Double totalBillAmt = 0.0;
		
		//from line 628 to 636 (vefication needed )
		List<FnclCharge> fnclChargeList = fnclChargeDAO.findByProperty("billInfo.billNbr", billInfo.getBillNbr());
		
		if ( fnclChargeList != null && 
			!fnclChargeList.isEmpty() ) {
			for ( FnclCharge fnclCharge : fnclChargeList ) {
				totalBillAmt += fnclCharge.getChargeAmount().doubleValue();
			}
		}

		billInfo.setBillAmt( totalBillAmt );//TODO:  Need to verify 
		billInfo.setCurrentBalance( totalRemainingBalance - creditAmount );
		billInfo.setPaidAmount( creditAmount );
		
		billInfoDAO.attachDirty( billInfo );
	}
	
	/**
	 * This method is meant for applying payments against the receivables. This method also
	 * instructs accounting to proactively refund the refundable deposit. 
	 * 
	 * Specially, if a deposit for an account is refundable and its refund date is 
	 * less than or equal to today then that deposit should be considered for refund.
	 * 
	 * Usually, deposit refund is handled by finance (which can be part of either accounting
	 * or separate finance module). However, we can not expect a full fledged environment where 
	 * refund jobs will be run everyday and hence, it makes sense to at least review the deposits 
	 * at the time of billing. 
	 * Especially, if you consider a situation of health care information system, where
	 * deposit collection is very frequent, and usually all these deposits are meant
	 * to be adjusted against the bill. In such situation, concepts like asking finance / accounting
	 * to review deposits may be really useful. 
	 * 
	 * We have a bill settings parameter, REVIEW_REFUNDABLE_DEPOSITS, which indicates that 
	 * the finance / accounting process should be requested to refund the eligible deposits. 
	 *   
	 * @param billInfo
	 * @return
	 */
	private double applyCreditResiduals( BillInfo billInfo , String acAccountId ) {
		
		BillSetting billSetting = billSettingDAO.findById( BillingConstants.EVALUATE_DEPOSIT_REFUND );
		boolean evaluateDeposits = false;
		
		if ( billSetting != null ) {
			if ( billSetting.getAttrValue().equals( BillingConstants.FLAG_VALUE_YES ) ) {
				evaluateDeposits = true;
			}
		}
				
		AccountBillData accountBillData = processAdapter.applyCreditResiduals(billInfo.getClientName(), acAccountId ,billInfo.getBillNbr(), evaluateDeposits );
		
		BillDataBM billDataBM = accountBillData.getBillDataBM();
		if ( billDataBM != null ){
			//Create item details entry 
			
			BillRegister billRegister = this.getBillRegister( billDataBM.getProcessName() );
			String createDetailsFlag = billRegister.getCreateDetailsFlag().trim();
			
			this.createBillItemDetail(accountBillData.getBillDataBM() , billInfo, createDetailsFlag);
		}
		return accountBillData.getTotalAssignedAmount();
	}
	
	/**
	 * This method creates bill item details 
	 * 
	 * 
	 * @param billDataBM
	 * @param billInfo
	 * @param createDetailsFlag
	 */
	private void createBillItemDetail( BillDataBM billDataBM , BillInfo billInfo, String createDetailsFlag ){

		Map<String, BillingSubprocessBM>subProcessMap = billDataBM.getBillDetailsMap();
		
		if ( subProcessMap != null && !subProcessMap.isEmpty() ) {
			Set<String>subProcessSet = subProcessMap.keySet();
			
			for ( String subProcessName : subProcessSet ) {
				BillingSubprocessBM subProcessDetails = subProcessMap.get(subProcessName);
				
				if ( subProcessDetails.getContributeToFnclCharge().equals( Boolean.TRUE )	) {
					//
					// This process requires us to capture charge details.
					//
					FnclCharge fnclCharge = this.createFinancialCharge( billDataBM.getProcessName(), 
							                                            subProcessDetails,
							                                            billInfo.getAccountId(), 
							                                            billInfo );
				}
				
				if ( createDetailsFlag.equals( BillingConstants.FLAG_VALUE_YES ) ) {
					
					List<BillDetailsBM>billDetailsBMList = subProcessDetails.getBillDetailsList();
					
					if ( billDetailsBMList != null && !billDetailsBMList.isEmpty() ) {
						for ( BillDetailsBM billDetailsBM : billDetailsBMList ) {
							BillItemDetails billItemDetails = this.convertBillDetailsBM2DM( billDetailsBM, 
																							billDataBM.getProcessName(), 
									                                                        subProcessName,
									                                                        billInfo.getBillNbr() );
							
							billItemDetailsDAO.save( billItemDetails );
						}
					}
					
				} // end of create bill item details
				
			} // end of looping for sub-processes
		}
	
	}
	
	/**
	 * This method reads all the charges and creates receivable for them.
	 * The process adapter is supposed to implement this accounting metho3wd.
	 * 
	 * @param billInfo
	 * @return
	 */
	private double createReceivables( BillInfo billInfo ,String acAccountId ) {
		double totalChargeAmt = 0.0d;
		double totalRcvblRmngAmt = 0.0d;
		
		List<FnclCharge> fnclChargeList = fnclChargeDAO.findByProperty("billInfo.billNbr", billInfo.getBillNbr());
		
		if ( fnclChargeList != null && 
			!fnclChargeList.isEmpty() ) {
			for ( FnclCharge fnclCharge : fnclChargeList ) {
				totalChargeAmt += fnclCharge.getChargeAmount().doubleValue();
			}
		
			totalRcvblRmngAmt = this.processAdapter.createReceivables( totalChargeAmt, 
																	  billInfo.getBillNbr(), 
													                  BillingConstants.FNCL_CHARGES,
													                  billInfo.getClientName(),
													                  acAccountId );
		}		
		
		return totalRcvblRmngAmt;
	}
	
	
	
	/**
	 * This method finds out all the registered process for which billing needs to be run.
	 * This billing processes must be returned in the order that we want to process them.
	 * If there is no specific order then all the process can have same BILL_SEQUENCE_NBR
	 * value in the bill_register table.
	 *  
	 * For all the eligible processes, it calls the corresponding adapter method. 
	 * Account identifier and bill number also needs to be provided to the adapter.
	 * The billing module allows processes to define charges upto two levels 
	 * 1) All the charges at process level
	 * 2) Charges divided at sub-process level
	 * 
	 * In case all the charges are defined at process level, there will be only one
	 * sub-process with the name "All" to indicate that the charges need to be created
	 * at process level.
	 * 
	 * For every sub-processes associated with the process, we need to do following
	 * 1) Check if details for billed items need to be created
	 * 	  -- this is decided based on the CREATE_DETAILS_FLAG in BILL_REGISTER table
	 *    -- the default value of CREATE_DETAILS_FLAG is 'N', hence if the intention
	 *       is to save bill item level details then this flag value must be set to 'Y' 	
	 * 
	 *  
	 * @param accountId
	 * @param billNumber
	 * 
	 */
	private void runRegisteredBillingProcesses( BillInfo billInfo , String acAccountId ) {
		
		String accountId = billInfo.getAccountId() ;
		Long billNumber = billInfo.getBillNbr();
		
		List<BillProcessRouting>eligibleProcessList = billProcessRoutingDAO.getActiveProcesses(billInfo.getClientName());
		
		if ( eligibleProcessList != null && !eligibleProcessList.isEmpty() ) {
			
			List<BillDataBM> billDataList = new ArrayList<BillDataBM>(0);
			
			for ( BillProcessRouting billProcessRouting : eligibleProcessList ) {
				
				BillRegister billRegister = this.getBillRegister( billProcessRouting.getId().getProcessName() );
				
				this.createBillingActivity( billInfo, 
						                    BillingConstants.BILLING_STARTED_FOR_PROCESS, 
						                    "Process Name : " + billProcessRouting.getId().getProcessName() );
				
				
				
				BillDataBM billDataBM = processAdapter.runBillingForProcess( billProcessRouting, 
																			 billInfo.getAccountId(),
																			 acAccountId,
						                                                     billNumber );
				
				
				
				if ( billDataBM != null ) {
					
					this.createBillItemDetail(billDataBM, billInfo, billRegister.getCreateDetailsFlag());
					}
				
				//
				// Create bill event for completion of the current process
				//
				this.createBillingActivity( billInfo, 
						                    BillingConstants.BILLING_COMPLETED_FOR_PROCESS, 
						                    "Process Name : " + billRegister.getProcessName() );
				
				billDataList.add( billDataBM );
			}
		} 
	}
	
	/**
	 * This method creates billing activity. Examples of billing activity could be
	 * -- starting a registered billing process
	 * -- registered processes completing successfully
	 * -- bill getting paid completely
	 * -- duplicate bill being printed
	 * 
	 * @param billNumber
	 * @param activityTypeCd
	 * @param remarks
	 */
	private void createBillingActivity( BillInfo billInfo, 
			                            String activityTypeCd, 
			                            String remarks  ) {
		
		BillActivityDetails billActivityDetails = new BillActivityDetails();
		
		billActivityDetails.setRemarks(remarks);
		billActivityDetails.setActivityTime( new Date() );
		
		BillActivity billActivity = this.getBillActivity(activityTypeCd);
		
		billActivityDetails.setBillActivity(billActivity);
		billActivityDetails.setBillInfo(billInfo);
		billActivityDetailsDAO.save( billActivityDetails );
	}
	
	private BillActivity getBillActivity( String activityTypeCd ) {
		
		try {
			BillActivity billActivity = billActivityDAO.findById( activityTypeCd );
			
			if ( billActivity == null ) {
				throw new Exception("Bill Activity Type with ACTIVITY_TYPE_CD = " + activityTypeCd + " does not exist. " );
			}
			
			return billActivity;
		} catch (Exception e) {
			Fault fault = new Fault( BillingConstants.BILL_ACTIVITY_NOT_FOUND );
			throw new BillException( fault.getFaultMessage() + e.getMessage() , 
									 fault.getFaultCode(),
									 fault.getFaultType()  );
		}
	}
	
	/**
	 * 
	 * @param billDetailsBM
	 * @param processName
	 * @param billNumber
	 * @return
	 */
	private BillItemDetails convertBillDetailsBM2DM( BillDetailsBM billDetailsBM,
			                                         String processName,
			                                         String subProcessName,
			                                         Long billNumber ) {
		BillItemDetails billItemDetails = new BillItemDetails();
		
		billItemDetails.setDiscounts( billDetailsBM.getDiscounts() );
		
		BillItemDetailsId billItemDetailsId = new BillItemDetailsId();
		billItemDetailsId.setBillNumber(billNumber);
		billItemDetailsId.setProcessName(processName);
		billItemDetailsId.setSubProcessName(subProcessName);
		billItemDetailsId.setItemSequenceNbr( billDetailsBM.getItemSequenceNbr() );
		billItemDetails.setId( billItemDetailsId );

		billItemDetails.setItemCount( billDetailsBM.getItemCount() );
		billItemDetails.setItemName( billDetailsBM.getItemName() );
		billItemDetails.setItemPrice( billDetailsBM.getItemPrice() );
		billItemDetails.setTotalAmount( billDetailsBM.getNetPrice() );
		
		return billItemDetails;
	}
	
	/**
	 * 
	 * @param billRegister
	 * @param subProcessDetails
	 * @param accountId
	 * @param billNumber
	 * @return
	 */
	private FnclCharge createFinancialCharge( String processName, 
			                                  BillingSubprocessBM subProcessDetails,
			                                  String accountId, 
			                                  BillInfo billInfo ) {
		FnclCharge fnclCharge = new FnclCharge();
		fnclCharge.setAccountId(accountId);
		fnclCharge.setBillInfo(billInfo);
		fnclCharge.setChargeAmount( subProcessDetails.getSubProcessTotals() );
		
		FnclChargeType fnclChargeType = this.getFnclChargeType( processName, 
																subProcessDetails.getSubProcessName() );
		
		fnclCharge.setFnclChargeType(fnclChargeType);
		fnclCharge.setCreationDate( new Date() );
		
		fnclCharge.setRemarks( subProcessDetails.getRemarks() );
		
		fnclChargeDAO.save( fnclCharge );
		
		return fnclCharge;
	}
	
	/**
	 * 
	 * @param processName
	 * @param subProcessName
	 * @return
	 */
	private FnclChargeType getFnclChargeType( String processName, String subProcessName ) {
		try {
			FnclChargeType fnclChargeType = fnclChargeTypeDAO.getFnclChargeType( processName, subProcessName );
			
			if ( fnclChargeType == null ) {
				throw new Exception( "Financial charge type for PROCESS = " 
						            + processName
						            + " and SUB_PROCESS = " 
						            + subProcessName 
						            + " does not exist. ");
			}
			
			return fnclChargeType;
			
		} catch (Exception e) {
			Fault fault = new Fault( BillingConstants.FNCL_CHARGE_TYPE_DOES_NOT_EXIST );
			throw new BillException( fault.getFaultMessage() + e.getMessage(), 
									 fault.getFaultCode(),
									 fault.getFaultType()  );
		}
	}
	
	
	/**
	 * This method just creates the bill record and returns the BillInfo data model
	 * object for later usage. The due date is calculated based on the bill setting
	 * information. 
	 * 
	 * @param accountId
	 * @param acAccountId TODO
	 * @return
	 */
	private BillInfo createDummyBillRecord( String acAccountId, String accountId, String clientName ) {
		
		Integer invoiceId = processAdapter.createInvoice(acAccountId, 0.0, "Dummy bill record");
		
		BillInfo billInfo = new BillInfo();
		billInfo.setAccountId(accountId);
		billInfo.setClientName(clientName);
		billInfo.setBillAmt(0.0d);
		billInfo.setBillDate( new Date() );
		billInfo.setDueDate( this.calculateBillDueDate() );
		billInfo.setLastModifiedTime( new Date() );
		billInfo.setBillNbr(Long.valueOf(invoiceId));
		billInfoDAO.save( billInfo );
		
		return billInfo;
	}
	
	/**
	 * This method assumes that due date can be null. Based on bill settings parameter
	 * DUE_DATE_FROM_CALENDAR and BILL_DUE_DATE_PERIOD, it does following:
	 * 1) If the due date needs to consider the holidays to ensure that the period is actually
	 *    the number of working days then it expects DUE_DATE_FROM_CALENDAR to be set to Y
	 * 2) BILL_DUE_DATE_PERIOD defines says that the due date will be after this number of days
	 *    from today.
	 *       
	 * @return
	 */
	private Date calculateBillDueDate() {
		Date billDueDate = null;
		
		
		BillSetting dueDatePeriod = billSettingDAO.findById( BillingConstants.BILL_DUE_DATE_PERIOD );
		
		if ( dueDatePeriod != null ) {
			
			BillSetting dueDateFromCal = billSettingDAO.findById( BillingConstants.DUE_DATE_FROM_CALENDAR );
			
			int numberOfDays = Integer.parseInt( dueDatePeriod.getAttrValue() );
			
			if ( dueDateFromCal != null && 
				dueDateFromCal.getAttrValue().equalsIgnoreCase( BillingConstants.FLAG_VALUE_YES ) ) {
				billDueDate = BillCalendar.getDateAfterToday( numberOfDays );
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.add( Calendar.DAY_OF_YEAR , numberOfDays );
				billDueDate = calendar.getTime();
			}
		}
		
		return billDueDate; 
	}
	

	/* (non-Javadoc)
	 * @see in.wtc.billing.bo.BillManager#runBill(java.lang.String, java.util.Date, java.util.Date)
	 */
//	public BillObjectBM runBill(String accountId, Date billFromDate,
//			Date billToDate) throws BillException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	/**
	 * First ensure that the process is really registered with the billing. 
	 * Now retrieve all the bill item details associated with the process.
	 *  
	 */
	public BillDataBM getBillDataForAProcess( Long billNumber, String processName )
			throws BillException {
		
		BillRegister billRegister = this.getBillRegister( processName );
		
		BillDataBM billDataBM = this.getBillData( billNumber, billRegister.getProcessName() );
		
		return billDataBM;
	}
	
	/**
	 * For a given bill and process name, this method return associated bill details.
	 * 
	 * @param billNumber
	 * @param processName
	 * @return
	 */
	private BillDataBM getBillData( Long billNumber, String processName ) {
		
		List<BillItemDetails>billItemDetailsList = billItemDetailsDAO.getBillItemDetails(billNumber, processName );
		
		if ( billItemDetailsList != null && !billItemDetailsList.isEmpty() ) {
			BillDataBM billDataBM = new BillDataBM();
			billDataBM.setProcessName(processName);
			double processTotalBillAmount = 0.0d;
			
			String subProcessName = billItemDetailsList.get(0).getId().getSubProcessName();
			Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);
			
			BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
			billingSubprocessBM.setSubProcessName(subProcessName);
			double subProcessTotals = 0.0d;
			List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
			
			for ( BillItemDetails billItemDetails : billItemDetailsList ) {
				String tempSubProcessName = billItemDetails.getId().getSubProcessName();
				
				if ( !tempSubProcessName.equals( subProcessName ) ) {
					billingSubprocessBM.setSubProcessTotals(subProcessTotals);
					billingSubprocessBM.setBillDetailsList(billDetailsBMList);
					billDetailsMap.put( subProcessName, billingSubprocessBM ); 
					
					billingSubprocessBM = new BillingSubprocessBM();
					subProcessName = tempSubProcessName;
					billingSubprocessBM.setSubProcessName(subProcessName);
					subProcessTotals = 0.0d;
				}
				
				BillDetailsBM billDetailsBM = this.convertBillItemDetails2BillDetails(billItemDetails);
				billDetailsBMList.add( billDetailsBM );
				subProcessTotals += billItemDetails.getTotalAmount();
				processTotalBillAmount += billItemDetails.getTotalAmount();
			}
			
			
			billingSubprocessBM.setSubProcessTotals(subProcessTotals);
			billingSubprocessBM.setBillDetailsList(billDetailsBMList);
			billDetailsMap.put( subProcessName, billingSubprocessBM );
			billDataBM.setBillDetailsMap( billDetailsMap );
			billDataBM.setProcessTotalBillAmount( processTotalBillAmount );
			
			return billDataBM;
		} else {
			return null;
		}
	}
	
	/**
	 * 
	 */
	public List<String> getCurrentlyRegisteredProcesses() throws BillException {
		List<String>processList = null;
		
		List<BillRegister>billRegisterList = billRegisterDAO.getActiveProcesses();
		
		if ( billRegisterList != null && !billRegisterList.isEmpty() ) {
			processList = new ArrayList<String>(0);
			
			for ( BillRegister billRegister : billRegisterList ) {
				processList.add( billRegister.getProcessName() );
			}
		}
		
		return processList;
	}

	public void setBillSettingDAO(BillSettingDAO billSettingDAO) {
		this.billSettingDAO = billSettingDAO;
	}

	public void setBillRegisterDAO(BillRegisterDAOExtn billRegisterDAO) {
		this.billRegisterDAO = billRegisterDAO;
	}

	public void setBillProcessRoutingDAO(
			BillProcessRoutingDAOExtn billProcessRoutingDAO) {
		this.billProcessRoutingDAO = billProcessRoutingDAO;
	}

	public void setFnclChargeTypeDAO(FnclChargeTypeDAOExtn fnclChargeTypeDAO) {
		this.fnclChargeTypeDAO = fnclChargeTypeDAO;
	}

	public void setFnclChargeDAO(FnclChargeDAO fnclChargeDAO) {
		this.fnclChargeDAO = fnclChargeDAO;
	}

	public void setBillItemDetailsDAO(BillItemDetailsDAOExtn billItemDetailsDAO) {
		this.billItemDetailsDAO = billItemDetailsDAO;
	}

	public void setBillActivityDetailsDAO(
			BillActivityDetailsDAO billActivityDetailsDAO) {
		this.billActivityDetailsDAO = billActivityDetailsDAO;
	}

	public void setFnclTrnsctSummaryDAO(FnclTrnsctSummaryDAO fnclTrnsctSummaryDAO) {
		this.fnclTrnsctSummaryDAO = fnclTrnsctSummaryDAO;
	}

	public void setBillActivityDAO(BillActivityDAO billActivityDAO) {
		this.billActivityDAO = billActivityDAO;
	}

	public void setProcessAdapter(BillProcessAdapter processAdapter) {
		this.processAdapter = processAdapter;
	}

	public void setBillInfoDAO(BillInfoDAOExtn billInfoDAO) {
		this.billInfoDAO = billInfoDAO;
	}

	
}
