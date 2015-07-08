/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import in.wtc.hcis.bm.ip.AdmissionBM;
import in.wtc.hcis.bm.ip.PatientBasicDetailBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Alok Ranjan
 *
 */
public interface AdmissionManager {

	/**
	 * This method allows performing lookup for admission records.
	 * All the parameters of this method are optional. 
	 * Following fields also supports partial lookup
	 * 1) entryPointName
	 * 2) admissionReason
	 * 3) agenda
	 * 
	 * @param doctorId
	 * @param patientId
	 * @param admissionStatusCd
	 * @param admissionFromDt
	 * @param admissionToDate
	 * @param entryPointName
	 * @param admissionReason
	 * @param agenda
	 * @param expectedDischargeFromDate
	 * @param expectedDischargeToDate
	 * @param dischargeFromDate
	 * @param dischargeToDate
	 * @param nursingUnitTypeCd
	 * @return
	 */
	List<AdmissionBM> getAdmissions (  Integer admissionNbr,
									   Integer doctorId, 
					                   Integer patientId,
					                   String  patientName,
					                   String admissionStatusCd, 
					                   Date admissionFromDt, 
					                   Date admissionToDate,
					                   String entryPointName,
					                   String admissionReason,
					                   String agenda,
					                   Date expectedDischargeFromDate,
					                   Date expectedDischargeToDate,
					                   Date dischargeFromDate,
					                   Date dischargeToDate,
					                   String nursingUnitTypeCd,
					                   String bedNumber);
	
	/**
	 * 
	 * @param admissionNbr
	 * @param doctorId
	 * @param patientId
	 * @param patientName
	 * @param admissionStatusCd
	 * @param admissionFromDt
	 * @param admissionToDate
	 * @param entryPointName
	 * @param admissionReason
	 * @param agenda
	 * @param expectedDischargeFromDate
	 * @param expectedDischargeToDate
	 * @param dischargeFromDate
	 * @param dischargeToDate
	 * @param nursingUnitTypeCd
	 * @param bedNumber
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange findAdmissions (Integer admissionNbr, 
							  Integer doctorId, 
				              Integer patientId,
				              String  patientName,
				              String admissionStatusCd, 
				              Date admissionFromDt,
				              Date admissionToDate, 
				              String entryPointName,
				              String admissionReason, 
				              String agenda,
				              Date expectedDischargeFromDate, 
				              Date expectedDischargeToDate,
				              Date dischargeFromDate, 
				              Date dischargeToDate,
				              String nursingUnitTypeCd,
				              String bedNumber,
				              int start,  int count,  String orderBy);
	
	void confirmAdmissions(Integer[] admissionReqNumber);

	/**
	 * Returns patient related details with bed and accounting amount data
	 * @param admissionReqNbr
	 * @return
	 */
	PatientBasicDetailBM  getPatientBasicDetails( Integer admissionReqNbr ) ;
	/**
	 * this method generate report for viewing a particular bill
	 * @param admissionReqNbr
	 * @param billNumber
	 * @return
	 * @throws HCISException
	 */
	
	 String generatePatientBill(Integer admissionReqNbr, Integer billNumber) throws HCISException;
	 
	 ListRange getReceivable(Integer admissionReqNbr,int start,int count,String orderBy) throws HCISException;
	 
	 ListRange getPaymentReceipt(Integer admissionReqNbr, String invoiceId, int start,int count,String orderBy);
	 
	 
	 Integer getBusinessPartnerId(Integer admissionReqNbr);
	 
	 /**
	  * This method creates receipt for the given admissionReqNbr
	  * @param admissionReqNbr
	  * @param amount
	  * @param attributeMap
	  * @param createdBy
	  * @param invoiceId
	  * @return
	  */
	 Integer createReceipt(Integer admissionReqNbr, Double amount,
				Map<String, String> attributeMap, String createdBy,String invoiceId);
}
