package in.wtc.hcis.bo.contact;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.wtc.hcis.da.AppEntities;
import com.wtc.hcis.da.AppEntitiesDAO;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.ContactType;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.CountryDAO;
import com.wtc.hcis.da.EntityContactCode;
import com.wtc.hcis.da.EntityContactCodeId;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.GenderDAO;
import com.wtc.hcis.da.Relation;
import com.wtc.hcis.da.RelationDAO;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.SaluationDAO;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.StateDAO;
import com.wtc.hcis.da.StateId;
import com.wtc.hcis.da.extn.EntityContactCodeDAOExtn;

public class ContactManagerImpl implements ContactManager 
{
	private EntityContactCodeDAOExtn entityContactCodeDAOExtn;
	private ContactDetailsDAO contactDetailsDAO;
	private StateDAO stateDAO;
	private CountryDAO countryDAO;
	private AppEntitiesDAO appEntitiesDAO;
	private SaluationDAO saluationDAO;
	private GenderDAO genderDAO;
	private RelationDAO relationDAO;
	
	public void addContactDetails(ContactBM contactBM) throws HCISException {
		for (ContactDetailsBM tmpContactDetailsBM : contactBM.getContactDetailList()) {

			ContactDetails contactDetails = convertContactDetailsBM2DM(tmpContactDetailsBM, null);
			contactDetails.setCreateDtm(Calendar.getInstance().getTime());
			contactDetails.setCreatedBy(tmpContactDetailsBM.getCreatedBy());
			contactDetailsDAO.save(contactDetails);

			EntityContactCode entityContactCode = convertEntityContactDetailsBM2DM(tmpContactDetailsBM);
			entityContactCode.setContactDetails(contactDetails);

			entityContactCode.setSameAsCurrent(new Short("0"));
			if (tmpContactDetailsBM.getContactType().getCode().equals(in.wtc.hcis.bo.constants.ContactType.PERMANENT)
					&& contactBM.getIsPermanentContactSameAsCurrent()) {
				entityContactCode.setSameAsCurrent(new Short("1"));
				
			} else if (tmpContactDetailsBM.getContactType().getCode().equals(in.wtc.hcis.bo.constants.ContactType.EMERGENCY_PRIMARY)
					&& contactBM.getIsEmergencyPriContactSameAsCurrent()) {
				entityContactCode.setSameAsCurrent(new Short("1"));
				
			} else if (tmpContactDetailsBM.getContactType().getCode().equals(in.wtc.hcis.bo.constants.ContactType.EMERGENCY_SECONDARY)
					&& contactBM.getIsEmergencySecContactSameAsCurrent()) {
				entityContactCode.setSameAsCurrent(new Short("1"));
			} 

			entityContactCode.setCreateDtm(Calendar.getInstance().getTime());
			entityContactCode.setCreatedBy(tmpContactDetailsBM.getCreatedBy());

			entityContactCodeDAOExtn.save(entityContactCode);
		}
	}
	
	public ContactDetailsBM convertContactDetailsDM2BM(ContactDetails contactDetailsDM) {
		ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
		
		contactDetailsBM.setContactDetailsCode( contactDetailsDM.getContactCode() );
		if( contactDetailsDM.getSaluation() != null ) {
			contactDetailsBM.setSalutation( new CodeAndDescription( contactDetailsDM.getSaluation().getSaluationCode(), contactDetailsDM.getSaluation().getDescription() ) );
		}
		contactDetailsBM.setFirstName( contactDetailsDM.getFirstName() );
		contactDetailsBM.setMiddleName( contactDetailsDM.getMiddleName() );
		contactDetailsBM.setLastName( contactDetailsDM.getLastName() );
		if( contactDetailsDM.getGender() != null ) {
			contactDetailsBM.setGender( new CodeAndDescription( contactDetailsDM.getGender().getGenderCode(), contactDetailsDM.getGender().getDescription() ) );
		}
		contactDetailsBM.setHouseNumber( contactDetailsDM.getAddressLine1() );
		contactDetailsBM.setStreet( contactDetailsDM.getAddressLine2() );
		contactDetailsBM.setCity( contactDetailsDM.getCity() );
		
		if(contactDetailsDM.getCountryCode()!=null && !contactDetailsDM.getCountryCode().equals("")) {
			Country country = countryDAO.findById(contactDetailsDM.getCountryCode());
			contactDetailsBM.setCountry(new CodeAndDescription(country.getCountryCode(), country.getDescription()));
		}
		if(contactDetailsDM.getStateCode()!=null && !contactDetailsDM.getStateCode().equals("") &&
		   contactDetailsDM.getCountryCode()!=null && !contactDetailsDM.getCountryCode().equals("")) {
			StateId stateId = new StateId();
			stateId.setCountryCode(contactDetailsDM.getCountryCode());
			stateId.setStateCode(contactDetailsDM.getStateCode());
			State state = stateDAO.findById(stateId);
			contactDetailsBM.setState(new CodeAndDescription(state.getId().getStateCode(), state.getDescription()));	
		}
		
		
		contactDetailsBM.setPincode( contactDetailsDM.getPincode() );
		contactDetailsBM.setStayDuration( contactDetailsDM.getStayDuration() );
		contactDetailsBM.setPhoneNumber( contactDetailsDM.getContactNumber() );
		contactDetailsBM.setMobileNumber( contactDetailsDM.getMobileNumber() );
		contactDetailsBM.setFaxNumber( contactDetailsDM.getFaxNumber() );
		contactDetailsBM.setEmail( contactDetailsDM.getEmail() );
		if( contactDetailsDM.getRelation() != null ) {
			contactDetailsBM.setRelationCode(
					new CodeAndDescription(
						contactDetailsDM.getRelation().getRelationCode(),
						contactDetailsDM.getRelation().getDescription())
					);
		}
		
		return contactDetailsBM;
	
	}

	public List<ContactDetailsBM> getContactDetails(String entityTypeInd,
			Integer personelId, String contactTypeInd) {
		try {
			List<ContactDetailsBM> contactDetailsList = new ArrayList<ContactDetailsBM>();
			
			if(contactTypeInd == null || contactTypeInd.equals("")) {
				List<EntityContactCode> contactDetails =  
					(List<EntityContactCode>)entityContactCodeDAOExtn.getEntityContactListWithForEntity(personelId, entityTypeInd);
				if(contactDetails!=null) {
					Iterator<EntityContactCode> contactDetailsIter = contactDetails.iterator();
					while(contactDetailsIter.hasNext()) {
						EntityContactCode entityContactCode =contactDetailsIter.next(); 
						ContactDetailsBM contactDetailsBM = convertContactDetailsDM2BM(entityContactCode.getContactDetails());
						if(entityContactCode.getContactType()!= null){
							CodeAndDescription tmpObj = new CodeAndDescription();
							tmpObj.setCode(entityContactCode.getContactType().getContactTypeInd());
							tmpObj.setDescription(entityContactCode.getContactType().getDescription());
							contactDetailsBM.setContactType(tmpObj);
						}
						if(entityContactCode.getAppEntities()!=null){
							CodeAndDescription tmpObj = new CodeAndDescription();
							tmpObj.setCode(entityContactCode.getAppEntities().getEntityType());
							tmpObj.setDescription(entityContactCode.getAppEntities().getEntityName());
							contactDetailsBM.setForEntity(tmpObj);
						}
						
						contactDetailsBM.setPersonelId(personelId);
						contactDetailsList.add(contactDetailsBM);
					}
				}
			}else {
				EntityContactCode entityContactCode = 
					entityContactCodeDAOExtn.findById(new EntityContactCodeId(personelId,entityTypeInd,contactTypeInd));
				
				if( entityContactCode != null ) {
					ContactDetails contactDetailsDM = entityContactCode.getContactDetails();
					
					ContactDetailsBM contactDetailsBM = convertContactDetailsDM2BM(contactDetailsDM);
					
					CodeAndDescription tmpObj = new CodeAndDescription();
					tmpObj.setCode(entityContactCode.getContactType().getContactTypeInd());
					tmpObj.setDescription(entityContactCode.getContactType().getDescription());
					contactDetailsBM.setContactType( tmpObj);
				
					AppEntities appEntities = appEntitiesDAO.findById(entityContactCode.getId().getForEntityCode());
					contactDetailsBM.setForEntity( new CodeAndDescription( appEntities.getEntityType(), appEntities.getEntityName() ) );
					contactDetailsList.add(contactDetailsBM);
				}
			}
			
			return contactDetailsList;
	
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.CONTACT_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public ContactDetails convertContactDetailsBM2DM( ContactDetailsBM contactDetailsBM, ContactDetails existingContactDetails ) {
		
		ContactDetails contactDetailsDM;
		
		if( existingContactDetails != null ){
			
			contactDetailsDM = existingContactDetails;
		}else{
			
			contactDetailsDM = new ContactDetails();
		}
		
		
		if( contactDetailsBM.getSalutation() != null &&  contactDetailsBM.getSalutation().getCode().length() > 0) {
			Saluation saluation = saluationDAO.findById(contactDetailsBM.getSalutation().getCode());
			contactDetailsDM.setSaluation( saluation );
		}

		contactDetailsDM.setFirstName( contactDetailsBM.getFirstName() );
		contactDetailsDM.setMiddleName( contactDetailsBM.getMiddleName() );
		contactDetailsDM.setLastName( contactDetailsBM.getLastName() );
		if( contactDetailsBM.getGender() != null &&  contactDetailsBM.getGender().getCode().length() > 0 ) {
			Gender gender = genderDAO.findById(contactDetailsBM.getGender().getCode());
			contactDetailsDM.setGender( gender );
		}
		contactDetailsDM.setAddressLine1( contactDetailsBM.getHouseNumber() );
		contactDetailsDM.setAddressLine2( contactDetailsBM.getStreet() );
		contactDetailsDM.setCity( contactDetailsBM.getCity() );
		if( contactDetailsBM.getCountry() != null && 
				 contactDetailsBM.getCountry().getCode().length() > 0 ) {
			contactDetailsDM.setCountryCode(contactDetailsBM.getCountry().getCode());
		}
		if( WtcUtils.isValidCode(contactDetailsBM.getState()) 
				&& WtcUtils.isValidCode(contactDetailsBM.getCountry() )) {
			contactDetailsDM.setStateCode( contactDetailsBM.getState().getCode() );
			
		}else{
			contactDetailsDM.setStateCode(null);
		}
		contactDetailsDM.setPincode( contactDetailsBM.getPincode() );
		contactDetailsDM.setContactNumber( contactDetailsBM.getPhoneNumber() );
		contactDetailsDM.setMobileNumber( contactDetailsBM.getMobileNumber() );
		contactDetailsDM.setFaxNumber( contactDetailsBM.getFaxNumber() );
		contactDetailsDM.setEmail( contactDetailsBM.getEmail() );
		contactDetailsDM.setStayDuration( contactDetailsBM.getStayDuration() );
		if( contactDetailsBM.getRelationCode() != null && 
				 contactDetailsBM.getRelationCode().getCode().length() > 0 ) {
			Relation relation = relationDAO.findById(contactDetailsBM.getRelationCode().getCode());
			contactDetailsDM.setRelation( relation );
		}
		
		return contactDetailsDM;
	}

	public Integer createContactDetails(ContactDetailsBM contactDetailsBM){
		

			ContactDetails contactDetails = convertContactDetailsBM2DM(contactDetailsBM, null);
			contactDetails.setCreateDtm(Calendar.getInstance().getTime());
			contactDetails.setCreatedBy(contactDetailsBM.getCreatedBy());
			contactDetailsDAO.save(contactDetails);
			
			return contactDetails.getContactCode();
	}
	
	
	public void modifyContactDetail( ContactDetailsBM contactDetailsBM){

		ContactDetails contactDetails = contactDetailsDAO.findById(contactDetailsBM.getContactDetailsCode());
		convertContactDetailsBM2DM(contactDetailsBM, contactDetails);
		contactDetails.setModifiedDtm(Calendar.getInstance().getTime());
		contactDetails.setModifiedBy(contactDetailsBM.getCreatedBy());
		contactDetailsDAO.attachDirty(contactDetails);

		
	}
	
	
	
	public EntityContactCode convertEntityContactDetailsBM2DM( ContactDetailsBM contactDetailsBM ){
		
		EntityContactCodeId entityContactCodeId = new EntityContactCodeId();
		
		entityContactCodeId.setPersonelId( contactDetailsBM.getPersonelId() );
		entityContactCodeId.setForEntityCode( contactDetailsBM.getForEntity().getCode() );
		if( contactDetailsBM.getContactType()!=null) {
			entityContactCodeId.setContactTypeInd( contactDetailsBM.getContactType().getCode() );
		}
		
		EntityContactCode entityContactCodeDM = new EntityContactCode();
		entityContactCodeDM.setId( entityContactCodeId );
		
		ContactType contactType = new ContactType();
		entityContactCodeDM.setContactType( contactType );
		
		return entityContactCodeDM;
	}

	// This method is used to modify a contact detail for a particular entity
	public ContactBM modifyContactDetails(ContactBM contactBM,
			String appEntityTypeId) {
		try {
			List<EntityContactCode> tmpEntityList = 
				entityContactCodeDAOExtn.getEntityContactListWithForEntity(
						contactBM.getContactDetailList().get(0).getPersonelId(), appEntityTypeId);

			for (EntityContactCode tmpEntity : tmpEntityList) {
				// delete all the entity code entries
				entityContactCodeDAOExtn.delete(tmpEntity);

				// delete all the contacts
				ContactDetails contact = contactDetailsDAO.findById(tmpEntity.getContactDetails().getContactCode());
				contactDetailsDAO.delete(contact);
			}

			// add the modified contacts
			this.addContactDetails(contactBM);

			return contactBM;

		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_CONTACT_DETAILS_DOES_NOT_EXIST);
			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault.getFaultType());
		}
	}
	
	
	
	public ContactDetailsBM getContactDetail( Integer contactDetailCode){
		
		ContactDetailsBM contactDetailsBM =null;
		
		ContactDetails contactDetails = contactDetailsDAO.findById(contactDetailCode);
			
		if( contactDetails != null ){
			contactDetailsBM = this.convertContactDetailsDM2BM(contactDetails);
		}
		
		return contactDetailsBM;
		
	}
	
	
	

	public void setEntityContactCodeDAOExtn(EntityContactCodeDAOExtn entityContactCodeDAOExtn) {
		this.entityContactCodeDAOExtn = entityContactCodeDAOExtn;
	}

	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}

	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setAppEntitiesDAO(AppEntitiesDAO appEntitiesDAO) {
		this.appEntitiesDAO = appEntitiesDAO;
	}

	public void setSaluationDAO(SaluationDAO saluationDAO) {
		this.saluationDAO = saluationDAO;
	}

	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}

	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}
}
