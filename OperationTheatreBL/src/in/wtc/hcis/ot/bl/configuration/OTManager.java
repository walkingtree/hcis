/**
 * 
 */
package in.wtc.hcis.ot.bl.configuration;

import java.util.Date;
import java.util.List;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ot.bm.OTBookingBM;
import in.wtc.hcis.ot.bm.OTDetailBM;

/**
 * @author Bhavesh
 *
 */
public interface OTManager {

	/**
	 *  Setups new Operation theater detail on the system, with optional association with surgery  
	 * @param otDetailBM
	 * @throws HCISException
	 */
	
	void addOT( OTDetailBM otDetailBM) throws HCISException;
	
	
	/**
	 * 
	 * @param modifiedOtDetailBM
	 * @return
	 * @throws HCISException
	 */
	OTDetailBM modifyOTDetail( OTDetailBM modifiedOtDetailBM ) throws HCISException;
	
	
	/**
	 * 
	 * @param otCode
	 * @param otName
	 * @param bedNumber
	 * @param surgeryCode
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	
	ListRange getOTDetail ( String otCode, String otName,
							String bedNumber,String surgeryCode,
							int start, int count, String orderBy ) throws HCISException;
	/**
	 * 
	 * @param otId
	 * @return
	 * @throws HCISException
	 */
	
	public OTDetailBM getAssociatedSurgeryForOT(String otId)throws HCISException;
	
	/**
	 * 
	 * @param otIDList
	 * @param removedBy
	 * @throws HCISException
	 */
	void removeOTList( List<String> otIDList, String removedBy )throws HCISException;
	
	/**
	 * Searches the suitable time slot for passed parameter.
	 * Searching algorithm first determines available slot for OT and then available surgeon for
	 * available OT slot. 
	 * 
	 * See Implementation for complete description.
	 * @param surgeryCode
	 * @param otId
	 * @param doctorId
	 * @param timePeriod
	 * @param fromDtm
	 * @param toDtm
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange getOTAvaibilitySlot(String surgeryCode, String otId, Integer doctorId,
			  Integer timePeriod,
			  Date fromDtm, Date toDtm,int start, int count,String orderBy);
	
	/**
	 * Method creates ot booking entry, at the same time creates patient surgery entry.
	 * 
	 * @param bookingBM
	 * @param force
	 * @return
	 */
	public String bookOT( OTBookingBM bookingBM ,boolean force);

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

	
	void updateOtBookingStatus(Long bookingNbr, String newStatusCode, 
				String remarks, String createdBy) throws HCISException;
}
