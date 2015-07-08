/**
 * 
 */
package in.wtc.hcis.ot.bl.surgery;

import in.wtc.hcis.bm.base.CheckListBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ot.bm.OtNotesBM;
import in.wtc.hcis.ot.bm.SurgeryBM;
import in.wtc.hcis.ot.bm.SurgeryStatusTimeBM;

import java.util.Date;
import java.util.List;

/**
 * @author Bhavesh
 *
 *This manager contains methods for configuring(add/edit) surgery, and assignment of those surgery to 
 *a patient.Every surgery will be configured as service first. 
 */
public interface SurgeryManager{

	
	/**
	 * This method setups new surgery on the system. For this first create service then create 
	 * surgery related details. Timing details for each status also needs to be captured.
	 * Additionally this method also creates association with related OTs.
	 * @param surgeryBM
	 * @throws HCISException
	 */
	
	void addSurgery( SurgeryBM surgeryBM) throws HCISException;
	
	/**
	 * First modify the service related details then surgery.
	 * @param surgeryBM
	 * @return
	 * @throws HCISException
	 */
	SurgeryBM modifySurgery ( SurgeryBM surgeryBM ) throws HCISException;
	
	/**
	 * Returns statusTime detail for given surgery code. If no surgery code
	 * is passed then return all available  surgery status with no time detail 
	 * (read from surgery_status table). 
	 * @param surgeryCode
	 * @return
	 */
	 List<SurgeryStatusTimeBM> getSurgeryStatusTimeDetail( String surgeryCode);
	 
	 
	 /**
	  *  This method will return surgeryBM for the given surgeryCode
	  * @param surgeryCode
	  * @return
	  * @throws HCISException
	  */
	 SurgeryBM getSurgeryForCode( String surgeryCode) throws HCISException;
	 
	 /**
	  * @param patientName
	  * @param doctorName
	  * @param otCode
	  * @param surgeryCode
	  * @param fromDate
	  * @param toDate
	  * @param referenceType
	  * @param referenceNbr
	  * @param otBookingNbr
	  * @param start
	  * @param count
	  * @param orderBy
	  * @return
	  */
	 ListRange getOTSurgeries( String patientName, Integer doctorId,
				String otCode,String surgeryCode,Date fromDate,
				Date toDate,String referenceType, String referenceNbr,
				String otBookingNbr, int start, int count, String orderBy);
	
	 /**
	  * This method returns OT noteBM which contains list of OtNotesFieldsBM.
	  * 
	  * OT notes fields are configurable fields which can easily modified by
	  * data configuration.
	  * 
	  * Basic fields value of OT note ate fetched from 'PatientSurgery' table itself and for OtNotesFields first
	  * get all available fields from 'OtNotesConfiguration' and get corresponding fields value form 'PatientSuregeryNotes'
	  * @param patientSurgeryId
	  * @return
	  * @throws HCISException
	  */
	 OtNotesBM getOtNote(Long patientSurgeryId) throws HCISException;
	 
	
	 /**
	  * Saves/modifies only 'OtNotesFields' value. Basic fields value of OT note are fetched from 'PatientSurgery',
	  * so these method will not modify those values. 
	  * 
	  * @param otNotesBM
	  * @throws HCISException
	  */
	 void saveOtNotes(OtNotesBM otNotesBM) throws HCISException;
	 
	 
	 /**
	  * Modifies PatientSurgery status according to the status transition configured in the system.
	  * 
	  * @param patientSurgeryId
	  * @param newStatusCode
	  * @param remarks
	  * @param createdBy
	  * @throws HCISException
	  */
	 void updatePatientSurgeryStatus(Long patientSurgeryId, String newStatusCode, 
			 						String remarks, String createdBy ,CheckListBM[] checListBMList) throws HCISException;
	 
		/**
		 * Updates the OTBooking status based on the status transition configured in the system.
		 * Booking newStatus 'ADMITTED' marks corresponding patientSurgery also as 'ADMITTED'.
		 * 
		 * @param patientSurgeryId
		 * @param newStatusCode
		 * @param remarks
		 * @param createdBy
		 * @throws HCISException
		 */
		void updateOtBookingStatus(Long patientSurgeryId, String newStatusCode, 
					String remarks, String createdBy) throws HCISException;
		
		/**
		 * 
		 * @param patientSurgeryId
		 * @param surgeryStatusCode
		 * @return
		 * @throws HCISException
		 */
		List<CheckListBM> getCheckLists( Long patientSurgeryId , String surgeryStatusCode ) throws HCISException;
}
