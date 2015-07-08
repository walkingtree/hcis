/**
 * 
 */
package in.wtc.hcis.bo.contact;

import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.exceptions.HCISException;

import java.util.List;

import com.wtc.hcis.da.ContactDetails;

/**
 * This interface provides the API for the following
 * 				> Retrieve the contact details
 * 				> Add a contact detail
 * 				> Modify the contact details
 * 				> Remove a contact details
 * @author Rohit
 *
 */
public interface ContactManager {
	/**
	 * When adding a contact details if a current address is added and for permanent address we check the same as current check box 
	 * then the entry for address has not be saved but only the code for where the current address is stored will be updated.
	 * For emergency address we will store the entry in the database.
	 * 
	 * @param contactBM
	 * @return
	 * @throws HCISException
	 */
	void addContactDetails ( ContactBM contactBM )  throws HCISException;
	
	/**
	 * The method returns the contact list of the specified entity type and personnel id. If contactTypeInd is
	 * specified (non-null), the matching contact is returned. Else, the complete list of contacts is returned
	 * @param entityTypeInd entity type indicator (e.g. PATIENT, DOCTOR, etc.)
	 * @param personelId unique identifier of the patient/doctor/nurse/etc.
	 * @return return the matching contacts
	 * @throws HCISException
	 */
	List<ContactDetailsBM> getContactDetails(String entityTypeInd, Integer personelId, String contactTypeInd);
	
	/**
	 * The method updates the new contact information com the system. Contact code cannot be modified.
	 * @param modifiedContactBM updated contact model
	 * @return
	 * @throws HCISException
	 */
	ContactBM modifyContactDetails( ContactBM modifiedContactBM, String appEntityTypeId ) throws HCISException;
	
	/**
	 * 
	 * @param contactDetailsBM
	 * @return
	 * @throws HCISException
	 */
	Integer createContactDetails(ContactDetailsBM contactDetailsBM)throws HCISException;
	/**
	 * 
	 * @param contactDetailsDM
	 * @return
	 */
	ContactDetailsBM convertContactDetailsDM2BM(ContactDetails contactDetailsDM);
	
	/**
	 * 
	 * @param contactDetailCode
	 * @return
	 */
	
	ContactDetailsBM getContactDetail( Integer contactDetailCode);
	
	/**
	 * 
	 * @param contactDetailsBM
	 */
	void modifyContactDetail( ContactDetailsBM contactDetailsBM);
	
	
	/**
	 * 
	 * @param contactDetailsBM
	 * @param existingContactDetails
	 * @return
	 * @throws HCISException
	 */
	ContactDetails convertContactDetailsBM2DM( ContactDetailsBM contactDetailsBM, ContactDetails existingContactDetails ) throws HCISException;
	
}
