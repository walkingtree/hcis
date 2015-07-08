package in.wtc.hcis.bo.doctor;

import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;
import in.wtc.hcis.bm.base.DoctorLiteBM;
import in.wtc.hcis.bm.base.DoctorSummaryBM;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * This interface provides APIs to manager doctor and their detail.
 * @author Ajit Kumar
 *
 */
public interface DoctorManager {

	/**
	 * The method returns the lightweight doctor object, which contains the very basic
	 * information of a doctor
	 * @param doctorId unique doctor identifier whose basic information needs to be read 
	 * @return returns the basic doctor information
	 * @throws HCISException
	 */
	DoctorLiteBM getDoctorLiteInfo(Integer doctorId) throws HCISException;
	
	/**
	 * The method returns the present address of the specified doctor
	 * @param doctorId unique doctor identifier
	 * @return returns the present address of the doctor
	 * @throws HCISException
	 */
	ContactDetailsBM getDoctorAddress(Integer doctorId) throws HCISException;
	
	/**
	 * The methods searches for the doctors based on the specified search criterion and returns the
	 * summary information. To get the detail doctor information, getDoctorDetail API must be used 
	 * for a specific doctor.
	 * 
	 * All the parameters are optional
	 * 
	 * @param doctorId full or partial doctor identifier
	 * @param department full or partial department
	 * @param speciality full or partial speciality name
	 * @param firstName full or partial first name
	 * @param middleName full or partial middle name
	 * @param lastName full or partial last name
	 * @param roomNo full or partial room number
	 * @param consultationChargeFrom consultation charge range (from)
	 * @param consultationChargeTo consultation charge range (to)
	 * @param joiningDateFrom joining date (from)
	 * @param joiningDateTo joining date (to)
	 * @return returns the doctor summary information for the matching records
	 * @throws HCISException
	 */
	List<DoctorSummaryBM> findDoctors(Integer doctorId, 
									  String department, 
									  String speciality, 
									  String name,
									  String roomNo,
									  Double consultationChargeFrom,
									  Double consultationChargeTo,
									  Date joiningDateFrom,
									  Date joiningDateTo, 
									  int start, 
									  int limit, 
									  String orderBy) throws HCISException;
	
	/**
	 * The method returns the complete detail of the specified doctor
	 * @param doctorId unique doctor identifier whose detail information needs to be read
	 * @return returns the complete doctor information
	 * @throws HCISException
	 */
	DoctorBM getDoctorDetail(Integer doctorId) throws HCISException;
	
	/**
	 * The method adds a new doctor com the system
	 * @param doctorBM doctor information
	 * @throws HCISException
	 */
	void addDoctor(DoctorBM doctorBM) throws HCISException;
	
	/**
	 * The method updates a doctor information com the system
	 * @param modifiedDoctorBM updated information. The doctor identifier cannot be changed
	 * @return returns the updated doctor information
	 * @throws HCISException
	 */
	DoctorBM modifyDoctor(DoctorBM modifiedDoctorBM) throws HCISException;
	
	/**
	 * The method removes a doctor from the system. The method will not delete the doctor however,
	 * it marks the doctor as com-active com the system, which can later be purged
	 * @param doctorId unique doctor identifier
	 * @throws HCISException
	 */
	void removeDoctor(List<Integer> doctorId) throws HCISException;
	
	/**
	 * this method take the doctorId and it returns consultation charge.
	 * @param doctorId
	 * @return
	 * @throws HCISException
	 */
	Double getConsultationCharge (Integer doctorId) throws HCISException;
	
	/**
	 * this method take the doctorId and it returns consultation charge.
	 * @param doctorId
	 * @return
	 * @throws HCISException
	 */
	Double getConsultationForFollowUpCharge (Integer doctorId) throws HCISException;
	
	
	
}
