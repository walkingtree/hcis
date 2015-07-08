/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.ip.DischargeBM;

import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
public interface DischargeManager {
	/**
	 * This method creates an discharge order, which includes following:
	 * 1) Creation of doctor order and corresponding order details
	 * 2) Creation of discharge record
	 * 3) Creation of corresponding order and discharge activity
	 * 
	 * @param dischargeBM
	 * @return
	 */
	Integer createDischargeOrder( DischargeBM dischargeBM  );
	
	/**
	 * This method allows you to modify the discharge details for example 
	 * 1) Discharge status
	 * 2) Discharge details
	 * 
	 * Modify discharge will basically follow following process
	 * 1) 
	 * 
	 * @param modifiedDischargeBM
	 * @return
	 */
	DischargeBM modifyDischargeDetails( DischargeBM modifiedDischargeBM );
	
	
	/**
	 * This method allows performing lookup for discharge records.
	 * All the parameters of this method are optional. 
	 * Following fields also supports partial lookup
	 * 1) requested by person 
	 * 2) approved by person
	 * 
	 * @param doctorId
	 * @param patientId
	 * @param admissionNumber
	 * @param dischargeStatusCd
	 * @param dischargeTypeCd
	 * @param dischargeFromDate
	 * @param dischargeToDate
	 * @param expectedDischargeFromDate
	 * @param expectedDischargeToDate
	 * @param dischargeRequestedBy
	 * @param dischargeApprovedBy
	 * @return
	 * 
	 */
	List<DischargeBM> getDischarge (  Integer doctorId, 
					                   Integer patientId,
					                   Integer admissionNumber,
					                   String dischargeStatusCd,
					                   String dischargeTypeCd,
					                   Date dischargeFromDate, 
					                   Date dischargeToDate,
					                   Date expectedDischargeFromDate,
					                   Date expectedDischargeToDate,
					                   String dischargeRequestedBy,
					                   String dischargeApprovedBy );
}
