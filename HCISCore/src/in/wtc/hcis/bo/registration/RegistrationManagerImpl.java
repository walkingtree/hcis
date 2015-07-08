package in.wtc.hcis.bo.registration;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.ImagePopertyBM;
import in.wtc.hcis.bm.base.PatientAllergiesBM;
import in.wtc.hcis.bm.base.PatientImmunizationsBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.bo.constants.ContactType;
import in.wtc.hcis.bo.constants.ImageConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.contact.ContactManager;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Encoder;

import com.wtc.hcis.da.Allergies;
import com.wtc.hcis.da.AllergiesDAO;
import com.wtc.hcis.da.BloodGroup;
import com.wtc.hcis.da.BloodGroupDAO;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.GenderDAO;
import com.wtc.hcis.da.IdProofs;
import com.wtc.hcis.da.IdProofsDAO;
import com.wtc.hcis.da.Immunization;
import com.wtc.hcis.da.ImmunizationDAO;
import com.wtc.hcis.da.Marital;
import com.wtc.hcis.da.MaritalDAO;
import com.wtc.hcis.da.MotherTongue;
import com.wtc.hcis.da.MotherTongueDAO;
import com.wtc.hcis.da.Nationality;
import com.wtc.hcis.da.NationalityDAO;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.PatientAllergies;
import com.wtc.hcis.da.PatientAllergiesDAO;
import com.wtc.hcis.da.PatientCategory;
import com.wtc.hcis.da.PatientCategoryDAO;
import com.wtc.hcis.da.PatientDetails;
import com.wtc.hcis.da.PatientDetailsDAO;
import com.wtc.hcis.da.PatientImmunization;
import com.wtc.hcis.da.PatientImmunizationDAO;
import com.wtc.hcis.da.PatientRating;
import com.wtc.hcis.da.PatientRatingDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;
import com.wtc.hcis.da.ReferenceDataListId;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.ReferralDAO;
import com.wtc.hcis.da.RegistrationStatus;
import com.wtc.hcis.da.RegistrationStatusDAO;
import com.wtc.hcis.da.RegistrationType;
import com.wtc.hcis.da.RegistrationTypeDAO;
import com.wtc.hcis.da.Religion;
import com.wtc.hcis.da.ReligionDAO;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.SaluationDAO;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.extn.PatientDAOExtn;


public class RegistrationManagerImpl implements RegistrationManager {
	
	private static final Log log = LogFactory.getLog( RegistrationManagerImpl.class );
	
	private PatientDetailsDAO patientDetailsDAO;
	private PatientAllergiesDAO patientAllergiesDAO;
	private PatientImmunizationDAO patientImmunizationDAO;
	private ReferralDAO referralDAO;
	private AllergiesDAO allergiesDAO;
	private ImmunizationDAO immunizationDAO;
	private SaluationDAO saluationDAO;
	private GenderDAO genderDAO;
	private RegistrationTypeDAO registrationTypeDAO;
	private RegistrationStatusDAO registrationStatusDAO;
	private PatientRatingDAO patientRatingDAO;
	private PatientCategoryDAO patientCategoryDAO;
	private BloodGroupDAO bloodGroupDAO;
	private MaritalDAO maritalDAO;
	private ReligionDAO religionDAO;
	private MotherTongueDAO motherTongueDAO;
	private NationalityDAO nationalityDAO;
	private IdProofsDAO idProofsDAO; 

	private ReferenceDataListDAO referenceDataListDAO;
	private PatientDAOExtn patientDAOExtn;

	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	private DataModelManager dataModelManager;
	private ContactManager contactManager;
	private ServiceManager serviceManager;
	
	private ResourceBundle Config = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Config", Locale.getDefault());
	private ResourceBundle ImagesConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Image", Locale.getDefault());
			
	public PatientLiteBM registerPatient(PatientInfoDetailBM patientInfoDetailBM)
			throws HCISException {
		
		try {
			
			Integer currentContactCode = 0;
			Integer permanentContactCode =0;

			if (patientInfoDetailBM.getContacts() != null
					&& !patientInfoDetailBM.getContacts().getContactDetailList().isEmpty()) {
				
				Iterator<ContactDetailsBM> iter = patientInfoDetailBM.getContacts().getContactDetailList().iterator();
				while (iter.hasNext()) {
					ContactDetailsBM contactDetailsBM = iter.next();
					contactDetailsBM.setCreatedBy(patientInfoDetailBM.getCreatedBy());
					
					if( contactDetailsBM.getContactType() != null && contactDetailsBM.getContactType().getCode().equals(ContactType.CURRENT)){
						currentContactCode = contactManager.createContactDetails(contactDetailsBM);
					}
					
					if( contactDetailsBM.getContactType() != null && contactDetailsBM.getContactType().getCode().equals(ContactType.PERMANENT)){
						permanentContactCode = contactManager.createContactDetails(contactDetailsBM);
					}

				}
			}
			
//Save image
			
			
			String imageString = null;
			
			if( patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM() != null  ){
				
				try {
					
					ImagePopertyBM imagePopertyBM =  patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM();
					
					File tempImage = new File( imagePopertyBM.getFilePath()+ File.separator + imagePopertyBM.getFileName());
					BufferedImage bi = ImageIO.read( tempImage );
					
				    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			        try {
			            ImageIO.write(bi, "jpg", baos);
			            baos.flush();
			            byte[] imageAsRawBytes = baos.toByteArray();
			            baos.close();
			 
			            //bytes to string
			            BASE64Encoder b64enc = new BASE64Encoder();
			            imageString = b64enc.encode(imageAsRawBytes);
			            //don't do this!!!
			            //imageString = new String(imageAsRawBytes);
			        } catch (IOException ex) {
			            throw new  Exception("Failed to convert image into string");
			        }
			        
//					
//					File image = new File( ImagesConfig.getString(ImageConstants.IMAGES_DIR_PATH) + patientDM.getPatientId());
//					ImageIO.write(bi,"jpg" , image);
					tempImage.delete();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Filed to save image");
				}
				
			}
			
			// PATIENT
			Patient patientDM = convertPatientBM2DM(patientInfoDetailBM);
			if (patientDM != null) {
				patientDM.setCreateDtm(Calendar.getInstance().getTime());
				if( patientInfoDetailBM.getCreatedBy() != null ) {
					patientDM.setCreatedBy(patientInfoDetailBM.getCreatedBy());
				}
					
					patientDM.setCurrentContactDetailId(currentContactCode);
					patientDM.setPermanentContactDetailId(permanentContactCode);
					patientDM.setImage(imageString);
					patientDAOExtn.save(patientDM);
			}

			// PATIENT_DETAILS
			PatientDetails patientDetailsDM = convertPatientDetailsBM2DM(patientInfoDetailBM);
			patientDetailsDM.setPatientId(patientDM.getPatientId());
			patientDetailsDM.setCreatedDtm(Calendar.getInstance().getTime());

			if( patientInfoDetailBM.getCreatedBy() != null ) {
				patientDetailsDM.setCreatedBy(patientInfoDetailBM.getCreatedBy());
			}
			try {
				patientDetailsDAO.save(patientDetailsDM);
			} catch (Exception exception) {
				Fault fault = new Fault(
						ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_ADDED);

				throw new HCISException(fault.getFaultMessage()
						+ exception.getMessage(), fault.getFaultCode(), fault
						.getFaultType());
			}
			
			


			// CONTACT_DETAILS
//			try {
//				ContactBM contactBM = new ContactBM();
//				List<ContactDetailsBM> contactDetailsList = new ArrayList<ContactDetailsBM>();
//				
//				if (patientInfoDetailBM.getContacts() != null
//						&& !patientInfoDetailBM.getContacts().getContactDetailList().isEmpty()) {
//					
//					Iterator<ContactDetailsBM> iter = patientInfoDetailBM.getContacts().getContactDetailList().iterator();
//					while (iter.hasNext()) {
//						ContactDetailsBM contactDetailsBM = iter.next();
//						contactDetailsBM.setPersonelId(patientDM.getPatientId());
//						contactDetailsBM.setCreatedBy(patientInfoDetailBM.getCreatedBy());
//						contactDetailsList.add(contactDetailsBM);
//					}
//				}
//
//				contactBM.setContactDetailList(contactDetailsList);
//				contactBM.setIsPermanentContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsPermanentContactSameAsCurrent());
//				contactBM.setIsEmergencyPriContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsEmergencyPriContactSameAsCurrent());
//				contactBM.setIsEmergencySecContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsEmergencySecContactSameAsCurrent());
//
//				contactManager.addContactDetails(contactBM);
//
//			} catch (Exception exception) {
//				Fault fault = new Fault(
//						ApplicationErrors.PATIENT_CONTACT_DETAILS_COULD_NOT_BE_ADDED);
//
//				throw new HCISException(fault.getFaultMessage()
//						+ exception.getMessage(), fault.getFaultCode(), fault
//						.getFaultType());
//			}
			
			// PATIENT_ALLERGIES
			try {
				if (patientInfoDetailBM.getPatientAllergiesBMList() != null) {
					Iterator<PatientAllergiesBM> iter = patientInfoDetailBM.getPatientAllergiesBMList().iterator();
					while (iter.hasNext()) {
						PatientAllergiesBM allergiesBM = iter.next();
						PatientAllergies patientAllergies = convertPatientAllergiesBM2DM(allergiesBM);
						patientAllergies.setPatient(patientDM);
						patientAllergiesDAO.save(patientAllergies);
					}
				}
			} catch (Exception exception) {
				Fault fault = new Fault(
						ApplicationErrors.PATIENT_ALLERGIES_COULD_NOT_BE_ADDED);

				throw new HCISException(fault.getFaultMessage()
						+ exception.getMessage(), fault.getFaultCode(), fault
						.getFaultType());
			}

			// PATIENT_IMMUNIZATION
			try {
				if (patientInfoDetailBM.getPatientImmunizationsBMList() != null) {
					Iterator<PatientImmunizationsBM> iter = patientInfoDetailBM.getPatientImmunizationsBMList().iterator();
					while (iter.hasNext()) {
						PatientImmunizationsBM patientImmunizationsBM = iter.next();
						PatientImmunization patientImmunization = convertPatientImmunizationBM2DM(patientImmunizationsBM);
						patientImmunization.setPatient(patientDM);
						patientImmunization.setCreateDt(new Date());
						patientImmunizationDAO.save(patientImmunization);
					}
				}
			} catch (Exception exception) {
				Fault fault = new Fault(
						ApplicationErrors.PATIENT_IMMUNIZATIONS_COULD_NOT_BE_ADDED);

				throw new HCISException(fault.getFaultMessage()
						+ exception.getMessage(), fault.getFaultCode(), fault
						.getFaultType());
			}
			
			// Assign registration service to patient(if registration type is not 
			// 'DIRECT') and render it.
			
			if( !patientDM.getRegistrationType().getRegistrationTypeCode().equals(
									RegistrationConstants.REGISTRTION_TYPE_DIRECT)){
				
				Service service = null;
				try{
					service = dataModelManager.getServiceByCode(RegistrationConstants.REGISTRATION_SVC_CD);
					if(null != service){
						
						AssignedServiceBM assignedServiceBM = new AssignedServiceBM();
						assignedServiceBM.setService(new CodeAndDescription(service.getServiceCode(), service.getServiceName()));
						assignedServiceBM.setServiceDate(new Date());
						assignedServiceBM.setRequestedUnits(Integer.valueOf("1"));
						assignedServiceBM.setServiceCharge(service.getServiceCharge());
						assignedServiceBM.setCreateDtm(new Date());
						assignedServiceBM.setCreatedBy(patientDM.getCreatedBy());
						
						
						List<AssignedServiceBM> assignedServiceBMList = new ArrayList<AssignedServiceBM>();
						assignedServiceBMList.add(assignedServiceBM);
						
						AssignSvcAndPackageBM assignSvcAndPackageBM = new AssignSvcAndPackageBM();
						assignSvcAndPackageBM.setPatientId(patientDM.getPatientId());
						assignSvcAndPackageBM.setReferenceNumber(String.valueOf(patientDM.getPatientId()));
						assignSvcAndPackageBM.setReferenceType(RegistrationConstants.REFERENCE_TYPE_PAT);
						assignSvcAndPackageBM.setCreatedBy(patientDM.getCreatedBy());
						assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
						
						serviceManager.assignSvcAndPackages(assignSvcAndPackageBM);
						
						//At the same time render this assigned service
						List<AssignedServiceBM> svcList = serviceManager.getAssignedServicesList(String.valueOf(patientDM.getPatientId()), RegistrationConstants.REFERENCE_TYPE_PAT);
						Iterator<AssignedServiceBM> iter = svcList.iterator();
						while(iter.hasNext()){
							AssignedServiceBM tmpBM = (AssignedServiceBM) iter.next();
							if(tmpBM.getService().getCode().equals(RegistrationConstants.REGISTRATION_SVC_CD)){
								serviceManager.modifyAssignedServiceToRendered(tmpBM.getServiceUid(), new Integer(1), patientDM.getCreatedBy());
							}
						}
					}
					
				} catch (Exception exception) {
					Fault fault = new Fault(ApplicationErrors.SERVICE_ASSIGNMENT_FAILED);
					
					throw new HCISException(fault.getFaultMessage()
							+ exception.getMessage(), fault.getFaultCode(), fault.getFaultType());
				}
			}
			
			//Create account for patient in EAGLE ERP 
			AccountInfoBM accountInfoBM = new AccountInfoBM();
				
				
				if (patientInfoDetailBM.getContacts() != null
						&& !patientInfoDetailBM.getContacts().getContactDetailList().isEmpty()) {
					Iterator<ContactDetailsBM> iter = patientInfoDetailBM.getContacts().getContactDetailList().iterator();
					while (iter.hasNext()) {
						ContactDetailsBM contactDetailsBM = iter.next();
						if(contactDetailsBM.getContactType().getCode().equals("CURRENT")){
							
							contactDetailsBM.setForEntity( new CodeAndDescription(
														   RegistrationConstants.REFERENCE_TYPE_PAT,""));

							contactDetailsBM.setPersonelId( patientDM.getPatientId() );
							accountInfoBM.setContactDetailsBM(contactDetailsBM);
						}
					}
				}

				PatientRating patientRating = patientDM.getPatientRating();
				if( patientRating != null ){

					accountInfoBM.setEntityRating( new CodeAndDescription( patientRating.getRatingCode(),
															patientRating.getDescription()));
				}
				
				PatientCategory patientCategory = patientDM.getPatientCategory();
				if( patientCategory != null ){

					accountInfoBM.setEntityCategory( new CodeAndDescription( patientCategory.getCategoryCode(),
													 patientCategory.getDescription()));
				}

				eagleIntegration.createAccount(accountInfoBM, true);


			PatientLiteBM patientLiteBM = convertPatientDM2BM(patientDM);
			return patientLiteBM;
		} catch ( Exception e) {

			Fault fault = new Fault( ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_ADDED);
			throw new HCISException(fault,e);

		}
	}
	
	private Patient convertPatientBM2DM(PatientInfoDetailBM patientInfoDetailBM) {
		
		Patient patientDM = new Patient();

		// Values will come from personalDetailsBM to the patientDM
		if (patientInfoDetailBM.getPersonalDetailsBM() != null) {

			if (patientInfoDetailBM.getPersonalDetailsBM().getTitle() != null) {
				Saluation saluation = saluationDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getTitle().getCode());
				patientDM.setSaluation(saluation);
			}

			String firstName = patientInfoDetailBM.getPersonalDetailsBM().getFirstName();
			patientDM.setFirstName(firstName);
			
			String middleName = patientInfoDetailBM.getPersonalDetailsBM().getMiddleName();
			patientDM.setMiddleName(middleName);
			
			String lastName = patientInfoDetailBM.getPersonalDetailsBM().getLastName();
			patientDM.setLastName(lastName);

			StringBuffer fullName = new StringBuffer();
			if (firstName != null && firstName.length() > 0) {
				fullName.append(firstName);
			}
			if (middleName != null && middleName.length() > 0) {
				fullName.append(" " + middleName);
			}
			if (lastName != null && lastName.length() > 0) {
				fullName.append(" " + lastName);
			}
			patientDM.setFullName(fullName.toString());

			patientDM.setDateOfBirth(patientInfoDetailBM.getPersonalDetailsBM().getDateOfBirth());

			if (patientInfoDetailBM.getPersonalDetailsBM().getGender() != null) {
				Gender gender = genderDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getGender().getCode());
				patientDM.setGender(gender);
			}

			patientDM.setRegistrationDate(patientInfoDetailBM.getPersonalDetailsBM().getRegistrationDate());
			
			// if we get registration Type is null, then default registration Type is setting normal.
			
			if (patientInfoDetailBM.getPersonalDetailsBM().getRegistrationType() != null) {
				RegistrationType registrationType = registrationTypeDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getRegistrationType().getCode());
				patientDM.setRegistrationType(registrationType);
			}else{
				RegistrationType registrationType = registrationTypeDAO.findById(
													RegistrationConstants.REGISTRATION_TYPE_NORMAL);
				patientDM.setRegistrationType(registrationType);
				
			}

			if (patientInfoDetailBM.getPersonalDetailsBM().getRegistrationStatus() != null) {
				RegistrationStatus registrationStatus = registrationStatusDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getRegistrationStatus().getCode()); 
				patientDM.setRegistrationStatus(registrationStatus);
			}

			if (patientInfoDetailBM.getPersonalDetailsBM().getPatientRating() != null) {
				PatientRating patientRating = patientRatingDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getPatientRating().getCode());
				patientDM.setPatientRating(patientRating);
			}

			if (patientInfoDetailBM.getPersonalDetailsBM().getPatientCategory() != null) {
				PatientCategory patientCategory = patientCategoryDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getPatientCategory().getCode());
				patientDM.setPatientCategory(patientCategory);
			}else{
				//Assign default category
				PatientCategory patientCategory = patientCategoryDAO.findById(
						Config.getString(RegistrationConstants.DEFAULT_PATIENT_CATEGORY));
				patientDM.setPatientCategory(patientCategory);
			}
		}

		// Values will come from personalDetailsAdditionalBM to the patientDM
		if (patientInfoDetailBM.getPersonalDetailsAdditionalBM() != null) {
			
			patientDM.setHeight(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getHeight());
			patientDM.setWeight(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getWeight());
			
			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM().getBloodGroup() != null) {
				BloodGroup bloodGroup = bloodGroupDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getBloodGroup().getCode());
				patientDM.setBloodGroup(bloodGroup);
			}
			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM().getMaritalStatus() != null) {
				Marital marital = maritalDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getMaritalStatus().getCode());
				patientDM.setMarital(marital);
			}
			patientDM.setFatherHusband(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getFatherHusband());

			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM().getReligion() != null) {
				Religion religion = religionDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getReligion().getCode());
				patientDM.setReligion(religion);
			}

			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM()
					.getMotherTongue() != null) {
				MotherTongue motherTongue = motherTongueDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getMotherTongue().getCode());
				patientDM.setMotherTongue(motherTongue);
			}

			patientDM.setMonthlyIncome(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getMonthlyIncome());
			
			patientDM.setOccupation(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getPatientOccupation());
			
			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM().getNationality() != null) {
				Nationality nationality = nationalityDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getNationality().getCode());
				patientDM.setNationality(nationality);
			}

			if (patientInfoDetailBM.getPersonalDetailsAdditionalBM().getReferredBy() != null) {
				Referral referral = referralDAO.findById(
						new Integer(patientInfoDetailBM.getPersonalDetailsAdditionalBM().getReferredBy().getCode()));
				patientDM.setReferral(referral);
			}
			
			if( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getHeightIndicator() != null &&
					!patientInfoDetailBM.getPersonalDetailsAdditionalBM().getHeightIndicator().getCode().isEmpty()){
				ReferenceDataList referenceDataList = getReferenceData( RegistrationConstants.HEIGHT_INDICATOR,
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getHeightIndicator().getCode());
				patientDM.setHeightInd( referenceDataList.getId().getAttrCode());
			}
			if( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getWeightIndicator() != null &&
					!patientInfoDetailBM.getPersonalDetailsAdditionalBM().getWeightIndicator().getCode().isEmpty()){
				ReferenceDataList referenceDataList = getReferenceData( RegistrationConstants.WEIGHT_INDICATOR,
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getWeightIndicator().getCode());
				patientDM.setWeightInd( referenceDataList.getId().getAttrCode());
			}
		}
		return patientDM;
	}
	
	private PatientDetails convertPatientDetailsBM2DM( PatientInfoDetailBM patientInfoDetailBM ){
		
		PatientDetails patientDetails = new PatientDetails();
		
		if( patientInfoDetailBM.getPersonalDetailsBM() != null ) {
			patientDetails.setPatientId( patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber() );
		}
		if( patientInfoDetailBM.getPersonalDetailsAdditionalBM() != null ) {
			patientDetails.setBloodDonorId( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getBloodDonorId() );		
			if( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getIdProof() != null ) {
				IdProofs idProofs = idProofsDAO.findById(
						patientInfoDetailBM.getPersonalDetailsAdditionalBM().getIdProof().getCode());
				patientDetails.setIdProofs( idProofs );
			}
			patientDetails.setIdNumber( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getIdNumber() );
			patientDetails.setIdValidUpto(  patientInfoDetailBM.getPersonalDetailsAdditionalBM().getIdValidUpto() );
			
			patientDetails.setVisaNumber( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getVisaNumber() );
			patientDetails.setVisaValidUpto( patientInfoDetailBM.getPersonalDetailsAdditionalBM().getVisaValidUpto() );
		}
		
		if( patientInfoDetailBM.getOtherDetailsBM() != null ) {
			patientDetails.setSmoking( patientInfoDetailBM.getOtherDetailsBM().isSmokingHabitFlag()? new Short("1") : new Short("0") );
			patientDetails.setDrinking( patientInfoDetailBM.getOtherDetailsBM().isDrinksAlcohol()? new Short("1") : new Short("0") );
		}
		
		return patientDetails;
	}

	private PatientAllergies convertPatientAllergiesBM2DM(PatientAllergiesBM allergiesBM) {
		PatientAllergies patientAllergies = new PatientAllergies();
		patientAllergies.setStartDate(allergiesBM.getFromDate());
		patientAllergies.setEndDate(allergiesBM.getToDate());
		Allergies tmpAllergy = allergiesDAO.findById(new Integer(allergiesBM.getAllergyCode()));
		patientAllergies.setAllergies(tmpAllergy);
		return patientAllergies;
	}

	private PatientImmunization convertPatientImmunizationBM2DM(PatientImmunizationsBM patientImmunizationsBM) {
		PatientImmunization patientImmunizations = new PatientImmunization();
		
		patientImmunizations.setImmunizationDtm(patientImmunizationsBM.getVaccinationDate());
		Immunization immunization = immunizationDAO.findById(patientImmunizationsBM.getImmunizationCode());
		patientImmunizations.setImmunization(immunization);
		return patientImmunizations;
	}


	private PatientLiteBM convertPatientDM2BM(Patient patient) {
		PatientLiteBM patientLiteBM = new PatientLiteBM();

		patientLiteBM.setPatientId(patient.getPatientId());
		
		patientLiteBM.setFirstName(patient.getFirstName());
		patientLiteBM.setMiddleName(patient.getMiddleName());
		patientLiteBM.setLastName(patient.getLastName());
		
		patientLiteBM.setDateOfBirth(patient.getDateOfBirth());

		if (patient.getSaluation() != null) {
			CodeAndDescription saluation = new CodeAndDescription();
			saluation.setCode(patient.getSaluation().getSaluationCode());
			saluation.setDescription(patient.getSaluation().getDescription());
			patientLiteBM.setTitle(saluation);
		}
		if (patient.getGender() != null) {
			CodeAndDescription gender = new CodeAndDescription();
			gender.setCode(patient.getGender().getGenderCode());
			gender.setDescription(patient.getGender().getDescription());
			patientLiteBM.setGender(gender);
		}

		List<ContactDetailsBM> contactDetailsBMList = 
			contactManager.getContactDetails(ApplicationEntities.PATIENT, 
					patient.getPatientId(), ContactType.CURRENT);

		if (contactDetailsBMList != null && !contactDetailsBMList.isEmpty()) {
			ContactDetailsBM contactDetailsBM = contactDetailsBMList.get(0);
			patientLiteBM.setContactDetailsBM(contactDetailsBM);
		}

		return patientLiteBM;
	}
	
	public void setPatientDAOExtn(PatientDAOExtn patientDAOExtn) {
		this.patientDAOExtn = patientDAOExtn;
	}

	public void setPatientDetailsDAO(PatientDetailsDAO patientDetailsDAO) {
		this.patientDetailsDAO = patientDetailsDAO;
	}

	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}


	public void setPatientAllergiesDAO(PatientAllergiesDAO patientAllergiesDAO) {
		this.patientAllergiesDAO = patientAllergiesDAO;
	}

	public void setReferralDAO(ReferralDAO referralDAO) {
		this.referralDAO = referralDAO;
	}

	public void setPatientImmunizationDAO(
			PatientImmunizationDAO patientImmunizationDAO) {
		this.patientImmunizationDAO = patientImmunizationDAO;
	}

	public void setAllergiesDAO(AllergiesDAO allergiesDAO) {
		this.allergiesDAO = allergiesDAO;
	}

	public void setImmunizationDAO(ImmunizationDAO immunizationDAO) {
		this.immunizationDAO = immunizationDAO;
	}
	
	public void registerEmergencyPatient(PatientInfoDetailBM patientInfoDetailBM)
			throws HCISException {

		Patient patientDM = convertPatientBM2DM(patientInfoDetailBM);
		if (patientDM != null) {
			patientDM.setCreateDtm(Calendar.getInstance().getTime());
			try {
				patientDAOExtn.save(patientDM);
			} catch (Exception exception) {
				Fault fault = new Fault(
						ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_ADDED);

				throw new HCISException(fault.getFaultMessage()
						+ exception.getMessage(), fault.getFaultCode(), fault
						.getFaultType());
			}
		}

		PatientDetails patientDetailsDM = convertPatientDetailsBM2DM(patientInfoDetailBM);
		patientDetailsDM.setPatientId(patientDM.getPatientId());
		patientDetailsDM.setCreatedDtm(Calendar.getInstance().getTime());
		try {
			patientDetailsDAO.save(patientDetailsDM);
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_ADDED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}

		try {
			ContactBM contactBM = new ContactBM();
			List<ContactDetailsBM> contactDetailsList = new ArrayList<ContactDetailsBM>();
			if (patientInfoDetailBM.getContacts() != null
					&& !patientInfoDetailBM.getContacts().getContactDetailList().isEmpty()) {
				Iterator<ContactDetailsBM> contactDetailsIter = 
					patientInfoDetailBM.getContacts().getContactDetailList().iterator();
				while (contactDetailsIter.hasNext()) {
					ContactDetailsBM contactDetailsBM = contactDetailsIter.next();
					contactDetailsBM.setPersonelId(patientDM.getPatientId());
					contactDetailsList.add(contactDetailsBM);
				}
			}

			contactBM.setContactDetailList(contactDetailsList);
			contactBM.setIsEmergencyPriContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsEmergencyPriContactSameAsCurrent());
			contactBM.setIsEmergencySecContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsEmergencySecContactSameAsCurrent());
			contactBM.setIsPermanentContactSameAsCurrent(patientInfoDetailBM.getContacts().getIsPermanentContactSameAsCurrent());

			contactManager.addContactDetails(contactBM);

		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_CONTACT_DETAILS_COULD_NOT_BE_ADDED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}

		try {
			if (patientInfoDetailBM.getPatientAllergiesBMList() != null) {
				Iterator<PatientAllergiesBM> iter = patientInfoDetailBM.getPatientAllergiesBMList().iterator();
				while (iter.hasNext()) {
					PatientAllergiesBM allergiesBM = iter.next();
					PatientAllergies patientAllergies = convertPatientAllergiesBM2DM(allergiesBM);
					patientAllergies.setPatient(patientDM);
					patientAllergiesDAO.save(patientAllergies);
				}
			}
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_ALLERGIES_COULD_NOT_BE_ADDED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}

		try {
			if (patientInfoDetailBM.getPatientImmunizationsBMList() != null) {
				Iterator<PatientImmunizationsBM> iter = patientInfoDetailBM.getPatientImmunizationsBMList().iterator();
				while (iter.hasNext()) {
					PatientImmunizationsBM immuBM = iter.next();
					PatientImmunization patientImmunization = convertPatientImmunizationBM2DM(immuBM);
					patientImmunization.setPatient(patientDM);
					patientImmunization.setCreateDt(new Date());
					patientImmunizationDAO.save(patientImmunization);
				}
			}
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_IMMUNIZATIONS_COULD_NOT_BE_ADDED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}

	}

	private ReferenceDataList getReferenceData(String context, String attrCode)throws HCISException{
		try {
			ReferenceDataListId id = new ReferenceDataListId();
			id.setContext(context);
			id.setAttrCode(attrCode);
			
			ReferenceDataList referenceData = referenceDataListDAO.findById(id);
			if( null == referenceData ){
				throw new Exception( "Reference data with " + attrCode + " does not exist");
			}
			return referenceData;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.REFERENCE_DATA_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	public void setSaluationDAO(SaluationDAO saluationDAO) {
		this.saluationDAO = saluationDAO;
	}

	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}

	public void setRegistrationTypeDAO(RegistrationTypeDAO registrationTypeDAO) {
		this.registrationTypeDAO = registrationTypeDAO;
	}

	public void setRegistrationStatusDAO(RegistrationStatusDAO registrationStatusDAO) {
		this.registrationStatusDAO = registrationStatusDAO;
	}

	public void setPatientRatingDAO(PatientRatingDAO patientRatingDAO) {
		this.patientRatingDAO = patientRatingDAO;
	}

	public void setPatientCategoryDAO(PatientCategoryDAO patientCategoryDAO) {
		this.patientCategoryDAO = patientCategoryDAO;
	}

	public void setBloodGroupDAO(BloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public void setMaritalDAO(MaritalDAO maritalDAO) {
		this.maritalDAO = maritalDAO;
	}

	public void setReligionDAO(ReligionDAO religionDAO) {
		this.religionDAO = religionDAO;
	}

	public void setMotherTongueDAO(MotherTongueDAO motherTongueDAO) {
		this.motherTongueDAO = motherTongueDAO;
	}

	public void setNationalityDAO(NationalityDAO nationalityDAO) {
		this.nationalityDAO = nationalityDAO;
	}

	public void setIdProofsDAO(IdProofsDAO idProofsDAO) {
		this.idProofsDAO = idProofsDAO;
	}

	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
}
