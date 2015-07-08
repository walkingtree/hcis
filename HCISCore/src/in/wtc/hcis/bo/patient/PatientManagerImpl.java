package in.wtc.hcis.bo.patient;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.ImagePopertyBM;
import in.wtc.hcis.bm.base.OtherDetailsBM;
import in.wtc.hcis.bm.base.PatientAllergiesBM;
import in.wtc.hcis.bm.base.PatientImmunizationsBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientInfoSummaryBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bo.common.ConvertNumberToWordFormat;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.bo.constants.ContactType;
import in.wtc.hcis.bo.constants.ImageConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.ReportsDetail;
import in.wtc.hcis.bo.contact.ContactManager;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.wtc.hcis.da.Allergies;
import com.wtc.hcis.da.AllergiesDAO;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.BloodGroup;
import com.wtc.hcis.da.BloodGroupDAO;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.EntityContactCode;
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
import com.wtc.hcis.da.extn.PatientDAOExtn;
import com.wtc.report.ReportManager;
import com.wtc.report.model.ReportDetail;

public class PatientManagerImpl implements PatientManager {
	
	private static final Log log = LogFactory.getLog( PatientManagerImpl.class );
	
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
	
	private ReportManager reportManager;

	private PatientDAOExtn patientDAOExtn;

	private ContactManager contactManager;
	
	private ReferenceDataListDAO referenceDataListDAO;

	public static String NORMAL = "NORMAL";
	
	EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	
	private ResourceBundle ImagesConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Image", Locale.getDefault());
	private ResourceBundle ReportsConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.ReportDetail", Locale.getDefault());
	

	public List<PatientInfoSummaryBM> getPatients(Integer patientId,
			String patientName, String phoneNumber, String genderCode,
			String maritalStatusCode, String fatherHusbandName,
			String registrationStatus, Date registrationFromDate,
			Date registrationToDate, Date fromAge, Date toAge,
			Date patientLastVisitedFromDate, Date patientLastVisitedToDate)
			throws HCISException {
		
		try {
			DateUtils.updateDateForSearch(registrationToDate);
			DateUtils.updateDateForSearch(toAge);
			DateUtils.updateDateForSearch(patientLastVisitedToDate);
			
			List patientSearchList = patientDAOExtn.getpatientsSearched(
					patientId, patientName, phoneNumber, genderCode,
					maritalStatusCode, fatherHusbandName, registrationStatus,
					registrationFromDate, registrationToDate, fromAge, toAge,
					patientLastVisitedFromDate, patientLastVisitedToDate);

			if (patientSearchList != null && !patientSearchList.isEmpty()) {
				
				List<PatientInfoSummaryBM> patientInfoSummaryBMList = new ArrayList<PatientInfoSummaryBM>();
				
				Iterator<Object> iterator = patientSearchList.iterator();
				while (iterator.hasNext()) {
					Object[] objArray = (Object[]) iterator.next();
					Patient patientDM = (Patient) objArray[0];
					Appointments appointmentsDM = (Appointments) objArray[1];
					ContactDetails contactDetails = (ContactDetails) objArray[2];

					PatientInfoSummaryBM patientInfoSummaryBM = new PatientInfoSummaryBM();
					PersonalDetailsBM personalDetailsBM = getPersonalDetailsBM(patientDM);

					List<ContactDetailsBM> contactDetailsBMList = 
						contactManager.getContactDetails(ApplicationEntities.PATIENT,patientDM.getPatientId(),ContactType.CURRENT);
					
					ContactDetailsBM contactDetailsBM = contactManager.convertContactDetailsDM2BM(contactDetails);
					
					patientInfoSummaryBM.setPersonalDetailsBM(personalDetailsBM);
					patientInfoSummaryBM.setContactDetailsBM(contactDetailsBM);
					
					if (appointmentsDM != null) {
						patientInfoSummaryBM.setLastVisited(appointmentsDM.getCapturedDtm());
					}
					patientInfoSummaryBMList.add(patientInfoSummaryBM);
				}
				return patientInfoSummaryBMList;
			}
			
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_FETCHED);
			HCISException hcisException = new HCISException(fault, exception);
			throw hcisException;
		}
		
		return null;
	}
	
	
	public PatientInfoDetailBM getPatientInfoDetailBM(Integer patientId) {
		Patient patient = this.getPatient(patientId);
		return this.convertPatientDM2BM(patient);
	}
	
	public String generatePatientCard(Integer patientId) throws HCISException{
		try {
			PatientInfoDetailBM patientInfoDetailBM = getPatientInfoDetailBM(patientId);
			if (patientInfoDetailBM == null) {
				throw new Exception("Patient with PATIENT_ID = " + patientId + " does not exist.");
			}
			ContactDetailsBM contactDetailsBM = null;
			List<ContactDetailsBM> contactList = patientInfoDetailBM.getContacts().getContactDetailList();
			if (contactList != null && contactList.size() > 0) {
				contactDetailsBM = contactList.get(0);
			}
			
			Image image = null;
			try{
				image = Toolkit.getDefaultToolkit().createImage(patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM().getBufferedImage().getSource());
			}
			catch(Exception e){
				
			}
		
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			PersonalDetailsBM personalDetail = patientInfoDetailBM.getPersonalDetailsBM();
			reportParamMap.put("fullName", personalDetail.getFirstName() + " " + personalDetail.getMiddleName() + " " + personalDetail.getLastName());
			reportParamMap.put("patientId", personalDetail.getRegistrationNumber().toString());
			reportParamMap.put("dob", personalDetail.getDateOfBirth()!= null ? personalDetail.getDateOfBirth().toString() : "");
			reportParamMap.put("gender", personalDetail.getGender() != null ? personalDetail.getGender().getDescription() : "");
			reportParamMap.put("city", contactDetailsBM.getCity());
			reportParamMap.put("stateCountry", (contactDetailsBM.getState() != null ? contactDetailsBM.getState().getDescription() : "") + "(" + (contactDetailsBM.getCountry() != null ? contactDetailsBM.getCountry().getDescription() : "") + ")");
			reportParamMap.put("street", contactDetailsBM.getStreet());
//			reportParamMap.put("area", contactDetailsBM.getPincode());
			reportParamMap.put("phoneNumber", contactDetailsBM.getPhoneNumber());
			reportParamMap.put("bottomMessage", "Please bring Patient ID Card whenever you come for treatment");
			reportParamMap.put("patientImage", image);
			reportParamMap.put("regDateAndTime", patientInfoDetailBM.getPersonalDetailsBM().getRegistrationDate());
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.PATIENT_CARD_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.PATIENT_CARD_REPORT_FILENAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, null);
			
			return retStr;
				
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
	}

	public String generateAttendantCard(Integer patientId) throws HCISException{
		try {
			PatientInfoDetailBM patientInfoDetailBM = getPatientInfoDetailBM(patientId);
			if (patientInfoDetailBM == null) {
				throw new Exception("Patient with PATIENT_ID = " + patientId + " does not exist.");
			}
			ContactDetailsBM contactDetailsBM = null;
			List<ContactDetailsBM> contactList = patientInfoDetailBM.getContacts().getContactDetailList();
			if (contactList != null && contactList.size() > 0) {
				contactDetailsBM = contactList.get(0);
			}
			
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			PersonalDetailsBM personalDetail = patientInfoDetailBM.getPersonalDetailsBM();
			reportParamMap.put("fullName", personalDetail.getFirstName() + " " + personalDetail.getMiddleName() + " " + personalDetail.getLastName());
			reportParamMap.put("patientId", personalDetail.getRegistrationNumber().toString());
			reportParamMap.put("dob", personalDetail.getDateOfBirth()!= null ? personalDetail.getDateOfBirth().toString() : "");
			reportParamMap.put("gender", personalDetail.getGender() != null ? personalDetail.getGender().getDescription() : "");
			reportParamMap.put("city", contactDetailsBM.getCity());
			reportParamMap.put("stateCountry", (contactDetailsBM.getState() != null ? contactDetailsBM.getState().getDescription() : "") + "(" + (contactDetailsBM.getCountry() != null ? contactDetailsBM.getCountry().getDescription() : "") + ")");
			reportParamMap.put("street", contactDetailsBM.getStreet());
			reportParamMap.put("area", contactDetailsBM.getPincode());
			reportParamMap.put("phoneNumber", contactDetailsBM.getPhoneNumber());
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.PATIENT_ATTENDANT_CARD_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.PATIENT_ATTENDANT_CARD_REPORT_FILENAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, null);
			
			return retStr;
				
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
	}

	public String generateRecieptSlip(Integer patientId, Double amount) {
		try {
			PatientInfoDetailBM patientInfoDetailBM = getPatientInfoDetailBM(patientId);
			if (patientInfoDetailBM == null) {
				throw new Exception("Patient with PATIENT_ID = " + patientId + " does not exist.");
			}
			
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			PersonalDetailsBM personalDetail = patientInfoDetailBM.getPersonalDetailsBM();
			reportParamMap.put("patientId", patientId.toString());
			reportParamMap.put("serialNbr", personalDetail.getRegistrationNumber().toString());
			reportParamMap.put("receiptDate", new Date().toString());
			reportParamMap.put("patientName", personalDetail.getFirstName() + " " + personalDetail.getMiddleName() + " " + personalDetail.getLastName());
			reportParamMap.put("amountNumeric", amount.toString());
			
			String amountWordFormat = ConvertNumberToWordFormat.convert(amount.longValue());
			reportParamMap.put("amountInWords", amountWordFormat);
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.REGISTRATION_FEE_RECEIPT_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.REGISTRATION_FEE_RECEIPT_FILE_NAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, null);
			
			return retStr;
				
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
	}
	
	private Patient getPatient(Integer patientId) throws HCISException {
		try {
			Patient patient = patientDAOExtn.findById(patientId);
			if (patient == null) {
				throw new Exception("Patient with PATIENT_ID = " + patientId
						+ " does not exist.");
			}
			return patient;
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.PATIENT_DOES_NOT_EXIST);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
	}

	private PatientInfoDetailBM convertPatientDM2BM( Patient patient ){
		
		List<ContactDetailsBM> contactDetailsList = new ArrayList();
		
		PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
		
		PersonalDetailsBM personalDetailsBM = this.getPersonalDetailsBM( patient );
		patientInfoDetailBM.setPersonalDetailsBM( personalDetailsBM );

		PersonalDetailsAdditionalBM personalDetailsAdditionalBM = this.getPersonalDetailsAdditionalBM( patient );
		patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
		
		ContactDetailsBM contactDetailsBM = contactManager.getContactDetail(patient.getCurrentContactDetailId());
		contactDetailsBM.setContactType(new CodeAndDescription(ContactType.CURRENT,""));
		contactDetailsList.add(contactDetailsBM);
		
		if( patient.getPermanentContactDetailId() != null ){
			contactDetailsBM = contactManager.getContactDetail(patient.getPermanentContactDetailId());
			contactDetailsBM.setContactType(new CodeAndDescription(ContactType.PERMANENT,""));
			contactDetailsList.add(contactDetailsBM);
		}
		
		ContactBM contactBM = new ContactBM();
		contactBM.setContactDetailList(contactDetailsList);
		patientInfoDetailBM.setContacts(contactBM);

		List<PatientAllergiesBM> patientAllergiesBMList = this.getAllergiesList(patient);
		patientInfoDetailBM.setPatientAllergiesBMList(patientAllergiesBMList);

		List<PatientImmunizationsBM> patientImmunizationsBMList = this.getImmunizationsList(patient);
		patientInfoDetailBM.setPatientImmunizationsBMList(patientImmunizationsBMList);

		OtherDetailsBM otherDetailsBM = this.getOtherDetailsOfPatient(patient);
		patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );

		return patientInfoDetailBM;
	}
	
	private PersonalDetailsBM getPersonalDetailsBM( Patient patient ){
		
		PersonalDetailsBM personalDetailsBM = new PersonalDetailsBM();
		try
		{
			personalDetailsBM.setRegistrationDate( patient.getRegistrationDate() );
			personalDetailsBM.setRegistrationNumber( patient.getPatientId() );
			if( patient.getRegistrationType() != null){
				personalDetailsBM.setRegistrationType( new CodeAndDescription( patient.getRegistrationType().getRegistrationTypeCode(), patient.getRegistrationType().getDescription() ) );
			}
			if( patient.getRegistrationStatus() != null ) {
				personalDetailsBM.setRegistrationStatus(new CodeAndDescription(patient.getRegistrationStatus().getRegistrationStatusCode(),patient.getRegistrationStatus().getDescription()));
			}
			if( patient.getSaluation() != null ) {
				personalDetailsBM.setTitle( new CodeAndDescription( patient.getSaluation().getSaluationCode(), patient.getSaluation().getDescription() ) );
			}
			personalDetailsBM.setFirstName( patient.getFirstName() );
			personalDetailsBM.setMiddleName( patient.getMiddleName() );
			personalDetailsBM.setLastName( patient.getLastName() );
			if( patient.getGender() != null ) {
				personalDetailsBM.setGender( new CodeAndDescription( patient.getGender().getGenderCode(), patient.getGender().getDescription() ) );
			}
			personalDetailsBM.setDateOfBirth( patient.getDateOfBirth() );
			if( patient.getPatientRating() != null ){
				personalDetailsBM.setPatientRating( new CodeAndDescription( patient.getPatientRating().getRatingCode(), patient.getPatientRating().getDescription() ) );
			}
			if( patient.getPatientCategory() != null ){
				personalDetailsBM.setPatientCategory( new CodeAndDescription( patient.getPatientCategory().getCategoryCode(), patient.getPatientCategory().getDescription() ) );
			}

			ImagePopertyBM popertyBM = this.getPatientImage(patient);
			personalDetailsBM.setImagePopertyBM( popertyBM );
		} catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_DOES_NOT_EXIST );
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		return personalDetailsBM;
	}
	
	private ImagePopertyBM getPatientImage( Patient patient) {

		try {
//			File image = new File(ImagesConfig.getString(ImageConstants.PATIENT_IMAGES) + patientId); 
//			BufferedImage bi = ImageIO.read(image);
			ImagePopertyBM imagePopertyBM = new ImagePopertyBM();
//			imagePopertyBM.setBufferedImage( bi );
//			imagePopertyBM.setFileName( image.getName() );
//			return imagePopertyBM;
			
			//string to ByteArrayInputStream
			
			String imageString = patient.getImage();
			if(imageString != null && !imageString.isEmpty()){
				
				BufferedImage bImage = null;
				BASE64Decoder b64dec = new BASE64Decoder();
				try {
					byte[] output = b64dec.decodeBuffer(imageString);
					ByteArrayInputStream bais = new ByteArrayInputStream(output);
					bImage = ImageIO.read(bais);
				} catch (IOException e) {
					System.err.println("Error");
				}
				
				imagePopertyBM.setBufferedImage(bImage);
				
			}

	        return imagePopertyBM;
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Image for patient id="+patient.getPatientId()+" not exists. ");
		}
		return null;
	}
	private PersonalDetailsAdditionalBM getPersonalDetailsAdditionalBM( Patient patient ) {
		
		PersonalDetailsAdditionalBM personalDetailsAdditionalBM = new PersonalDetailsAdditionalBM();
		PatientDetails patientDetails = patientDetailsDAO.findById( patient.getPatientId() );
		try {
			personalDetailsAdditionalBM.setFatherHusband( patient.getFatherHusband() );
			personalDetailsAdditionalBM.setIdNumber( patientDetails.getIdNumber() );
			personalDetailsAdditionalBM.setIdValidUpto( patientDetails.getIdValidUpto() );
			personalDetailsAdditionalBM.setVisaNumber( patientDetails.getVisaNumber() );
			personalDetailsAdditionalBM.setVisaValidUpto( patientDetails.getVisaValidUpto( ) );
			personalDetailsAdditionalBM.setPatientOccupation( patient.getOccupation() );
			personalDetailsAdditionalBM.setMonthlyIncome( patient.getMonthlyIncome() );
			personalDetailsAdditionalBM.setHeight( patient.getHeight() );
			personalDetailsAdditionalBM.setWeight( patient.getWeight() );
			personalDetailsAdditionalBM.setBloodDonorId( patientDetails.getBloodDonorId() );

			if( patient.getMarital() != null ){
				personalDetailsAdditionalBM.setMaritalStatus( new CodeAndDescription( patient.getMarital().getMaritalCode(), patient.getMarital().getDescription() ) );
			}
			if( patientDetails.getIdProofs() != null ){
				personalDetailsAdditionalBM.setIdProof( new CodeAndDescription( patientDetails.getIdProofs().getIdProofsCode(), patientDetails.getIdProofs().getDescription() ) );
			}
			if( patient.getBloodGroup() != null ){
				personalDetailsAdditionalBM.setBloodGroup( new CodeAndDescription( patient.getBloodGroup().getBloodGroupCode(), patient.getBloodGroup().getDescription() ) );
			}
			if( patient.getReligion() != null ){
				personalDetailsAdditionalBM.setReligion( new CodeAndDescription( patient.getReligion().getReligionCode(), patient.getReligion().getDescription() ) );
			}
			if( patient.getNationality() != null ){
				personalDetailsAdditionalBM.setNationality( new CodeAndDescription( patient.getNationality().getNationalityCode(), patient.getNationality().getDescription() ) );
			}
			if( patient.getMotherTongue() != null ){
				personalDetailsAdditionalBM.setMotherTongue( new CodeAndDescription( patient.getMotherTongue().getMotherTongueCode(), patient.getMotherTongue().getDescription() ) );
			}
			if( patient.getReferral() != null ){
				personalDetailsAdditionalBM.setReferredBy( new CodeAndDescription( patient.getReferral().getReferralCode().toString(), patient.getReferral().getReferralName() ));
			}
			if( patient.getHeightInd() != null ){
				ReferenceDataList referenceData = getReferenceData( RegistrationConstants.HEIGHT_INDICATOR, 
																	patient.getHeightInd());
				CodeAndDescription heightInd = new CodeAndDescription();
				heightInd.setCode( referenceData.getId().getAttrCode());
				heightInd.setDescription( referenceData.getAttrDesc());
				personalDetailsAdditionalBM.setHeightIndicator( heightInd );
			}
			if( patient.getWeightInd() != null ){
				ReferenceDataList referenceData = getReferenceData( RegistrationConstants.WEIGHT_INDICATOR, 
																	patient.getWeightInd());
				CodeAndDescription weightInd = new CodeAndDescription();
				weightInd.setCode( referenceData.getId().getAttrCode());
				weightInd.setDescription( referenceData.getAttrDesc());
				personalDetailsAdditionalBM.setWeightIndicator(weightInd);
			}
			
		} catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_DETAILS_DOES_NOT_EXIST );
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		return personalDetailsAdditionalBM;
	}
	
	private List<PatientAllergiesBM> getAllergiesList(Patient patient){
		try {
			List<PatientAllergies> patientAllergiesList = 
				patientAllergiesDAO.findByProperty("patient.patientId", patient.getPatientId());

			List<PatientAllergiesBM> allergiesBMList = new ArrayList<PatientAllergiesBM>();
			if( patientAllergiesList != null && !patientAllergiesList.isEmpty() ) {
				Iterator<PatientAllergies> iter =patientAllergiesList.iterator();
				while( iter.hasNext() ) {
					PatientAllergies patientAllergies = iter.next();
					
					PatientAllergiesBM allergiesBM = new  PatientAllergiesBM();
					allergiesBM.setAllergyCode(patientAllergies.getAllergies().getAllergiesCode().toString());
					allergiesBM.setAllergyDescrption(patientAllergies.getAllergies().getAllergryDescription());
					allergiesBM.setFromDate(patientAllergies.getStartDate());
					allergiesBM.setToDate(patientAllergies.getEndDate());
					
					allergiesBMList.add( allergiesBM  );
				}
			}
			return allergiesBMList;
			
		}catch ( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_ALLERGIES_COULD_NOT_BE_FETCHED );
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}

	}
	
	private List<PatientImmunizationsBM> getImmunizationsList(Patient patient){
		try {
			List<PatientImmunization> patientImmunizationsList = 
				patientImmunizationDAO.findByProperty("patient.patientId", patient.getPatientId());

			List<PatientImmunizationsBM> immuList = new ArrayList<PatientImmunizationsBM>();
			if( patientImmunizationsList != null && !patientImmunizationsList.isEmpty() ) {
				Iterator<PatientImmunization> iter =patientImmunizationsList.iterator();
				while( iter.hasNext() ) {
					PatientImmunization patientImmunization = iter.next();
					
					PatientImmunizationsBM immuBM = new  PatientImmunizationsBM();
					immuBM.setImmunizationCode(patientImmunization.getImmunization().getName());
					immuBM.setImmunizationDescrption(patientImmunization.getImmunization().getDescription());
					immuBM.setVaccinationDate(patientImmunization.getImmunizationDtm());
					
					immuList.add( immuBM  );
				}
			}
			return immuList;
			
		}catch ( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_IMMUNIZATIONS_COULD_NOT_BE_FETCHED );
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}

	}

	private OtherDetailsBM getOtherDetailsOfPatient( Patient patient )  {
		OtherDetailsBM otherDetailsBM = new OtherDetailsBM();
		
		PatientDetails patientDetails = patientDetailsDAO.findById( patient.getPatientId() );
		otherDetailsBM.setDrinksAlcohol( (patientDetails.getDrinking() != null) &&(patientDetails.getDrinking() > 0) ? true : false );
		otherDetailsBM.setSmokingHabitFlag( (patientDetails.getSmoking() != null) &&(patientDetails.getSmoking() > 0) ? true : false  );

		return otherDetailsBM;
	}
	
	public List<PatientInfoSummaryBM> emergencyToNormal( List<Integer> patientIdList ) 
	{
		List<PatientInfoSummaryBM> patientInfoSummaryBMList = new ArrayList<PatientInfoSummaryBM>();
		
		if( patientIdList != null && !patientIdList.isEmpty() ) {
			RegistrationType registrationType  = registrationTypeDAO.findById(PatientManagerImpl.NORMAL);
			Iterator<Integer> patientIdIterator = patientIdList.iterator();
			while( patientIdIterator.hasNext() ) {
				Patient patientDM = patientDAOExtn.findById( patientIdIterator.next() );
					patientDM.setRegistrationType( registrationType );
				patientDAOExtn.attachDirty( patientDM );
			}
		}
		return patientInfoSummaryBMList;
	}
	
	public PatientInfoDetailBM modifyPatientDetails( PatientInfoDetailBM patientInfoDetailBM ) {
		
		
		// Modify the photo if new file is given otherwise leave it as it is
		String imageString = null;
		ImagePopertyBM imagePopertyBM = patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM() ;
		
		if( imagePopertyBM != null &&  imagePopertyBM.getFileName() != null && !imagePopertyBM.getFileName().isEmpty()){
			
				
			try {
				imagePopertyBM =  patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM();
				
				File tempImage = new File( imagePopertyBM.getFilePath()+ File.separator + imagePopertyBM.getFileName());
				BufferedImage bi = ImageIO.read( tempImage );
				
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		            ImageIO.write(bi, "jpg", baos);
		            baos.flush();
		            byte[] imageAsRawBytes = baos.toByteArray();
		            baos.close();
		 
		            //bytes to string
		            BASE64Encoder b64enc = new BASE64Encoder();
		            imageString = b64enc.encode(imageAsRawBytes);
		            //don't do this!!!
		            //imageString = new String(imageAsRawBytes);
		            
		        	tempImage.delete();
		        } catch (IOException ex) {
		           System.out.println("Failed to convert image into string");
		        }
			}
		Patient patientDM = convertPatientBM2DM(patientInfoDetailBM);
		if (patientDM != null) {
			patientDM.setPatientId( patientDM.getPatientId() );
			patientDM.setLastModifiedDtm(Calendar.getInstance().getTime());
			
			if( imageString != null){
				patientDM.setImage(imageString);
			}
			
			if( patientInfoDetailBM.getModifiedBy() != null ) {
				patientDM.setModifiedBy(patientInfoDetailBM.getModifiedBy());
			}
			try {
				patientDAOExtn.attachDirty(patientDM);
			} catch (Exception exception) {
				Fault fault = new Fault(
						ApplicationErrors.PATIENT_DOES_NOT_EXIST);

				throw new HCISException(fault.getFaultMessage()
						+ exception.getMessage(), fault.getFaultCode(), fault
						.getFaultType());
			}
		}

		//Modify image
		

//		if( patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM() != null  ){
//			
//			try {
//				File tempImage = new File( ImagesConfig.getString(ImageConstants.IMAGES_DIR_PATH) + 
//					patientInfoDetailBM.getPersonalDetailsBM().getImagePopertyBM().getFileName());
//				BufferedImage bi = ImageIO.read( tempImage );
//				
//				File image = new File( ImagesConfig.getString(ImageConstants.PATIENT_IMAGES) + patientDM.getPatientId());
//				ImageIO.write(bi,"jpg" , image);
//				
//				if( !tempImage.equals(image)){
//					tempImage.delete();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Failed to save image");
//			}
//			
//		}
//		
		// PATIENT_DETAILS
		PatientDetails patientDetailsDM = convertPatientDetailsBM2DM(patientInfoDetailBM);
		patientDetailsDM.setPatientId(patientDM.getPatientId());
		patientDetailsDM.setLastModifiedDtm(Calendar.getInstance().getTime());
		if( patientInfoDetailBM.getModifiedBy() != null ) {
			patientDetailsDM.setModifiedBy(patientInfoDetailBM.getModifiedBy());
		}
		try {
			patientDetailsDAO.attachDirty(patientDetailsDM);
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_DETAILS_DOES_NOT_EXIST);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
		
// Modify contact details
		
		ContactDetailsBM currentContactDetailsBM = null;
		
		if (patientInfoDetailBM.getContacts() != null
				&& !patientInfoDetailBM.getContacts().getContactDetailList().isEmpty()) {
			
			Iterator<ContactDetailsBM> iter = patientInfoDetailBM.getContacts().getContactDetailList().iterator();
			while (iter.hasNext()) {
				ContactDetailsBM contactDetailsBM = iter.next();
				contactDetailsBM.setCreatedBy(patientInfoDetailBM.getCreatedBy());
				
				if( contactDetailsBM.getContactType() != null && contactDetailsBM.getContactType().getCode().equals(ContactType.CURRENT)){
					 
					currentContactDetailsBM = contactDetailsBM;
					contactManager.modifyContactDetail(contactDetailsBM);
					contactDetailsBM.setContactDetailsCode(patientDM.getPermanentContactDetailId());
				}
				
				if( contactDetailsBM.getContactType() != null && contactDetailsBM.getContactType().getCode().equals(ContactType.PERMANENT)){
				
					if( contactDetailsBM.getContactDetailsCode() == null){
						contactManager.createContactDetails(contactDetailsBM);
						patientDM.setPermanentContactDetailId(contactDetailsBM.getContactDetailsCode());
						patientDAOExtn.attachDirty(patientDM);
					}
					else{
						contactManager.modifyContactDetail(contactDetailsBM);
					}
				}

			}
		}
		
		// CONTACT_DETAILS
		try {
			
			//Now modify contact details on accounting system
			AccountInfoBM accountInfoBM = new AccountInfoBM();
			
			
			
			currentContactDetailsBM.setForEntity( new CodeAndDescription(
													   RegistrationConstants.REFERENCE_TYPE_PAT,""));
			currentContactDetailsBM.setPersonelId( patientDM.getPatientId());
			accountInfoBM.setContactDetailsBM(currentContactDetailsBM);
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

			eagleIntegration.modifyAccountDetails(accountInfoBM);

		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_DETAILS_HAVE_BEEN_MODIFIED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}

		// PATIENT_ALLERGIES - In case of update, use buildozer method to update allergies information.
		try {
			
			List<PatientAllergies> tmpList = 
				patientAllergiesDAO.findByProperty("patient.patientId", 
						patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber());
			
			if( tmpList != null && !tmpList.isEmpty() ) {
				Iterator<PatientAllergies> iter = tmpList.iterator();
				while (iter.hasNext()) {
					patientAllergiesDAO.delete( iter.next() );
				}
			}
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_ALLERGIES_COULD_NOT_BE_FETCHED);

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


		// PATIENT_IMMUNIZATION - In case of update, use buildozer method to update immunizations information.
		try {
			
			List<PatientImmunization> tmpList = 
				patientImmunizationDAO.findByProperty("patient.patientId", 
						patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber());
			
			if( tmpList != null && !tmpList.isEmpty() ) {
				Iterator<PatientImmunization> iter = tmpList.iterator();
				while (iter.hasNext()) {
					patientImmunizationDAO.delete( iter.next() );
				}
			}
		} catch (Exception exception) {
			Fault fault = new Fault(
					ApplicationErrors.PATIENT_IMMUNIZATIONS_COULD_NOT_BE_FETCHED);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
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

		PatientInfoDetailBM infoDetailBM = getPatientInfoDetailBM( patientDetailsDM.getPatientId() );
		return infoDetailBM;
	}
	
	private Patient convertPatientBM2DM( PatientInfoDetailBM patientInfoDetailBM ) {
		
		Patient patientDM = null;
		
		if( null != patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber()){
			patientDM = patientDAOExtn.findById(patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber());
		}else {
			patientDM = new Patient();
		}
		
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

			if (patientInfoDetailBM.getPersonalDetailsBM().getRegistrationType() != null) {
				RegistrationType registrationType = registrationTypeDAO.findById(
						patientInfoDetailBM.getPersonalDetailsBM().getRegistrationType().getCode());
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
	
	private PatientDetails convertPatientDetailsBM2DM( PatientInfoDetailBM patientInfoDetailBM )
	{
		PatientDetails patientDetails = null;
		if(patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber()!=null){
			patientDetails = patientDetailsDAO.findById(patientInfoDetailBM.getPersonalDetailsBM().getRegistrationNumber());		
		}else {
			patientDetails = new PatientDetails();
		}
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

	public PatientLiteBM getPatientLiteBM( Integer patientId ) throws HCISException {
		try {
			PatientLiteBM patientLiteBM = new PatientLiteBM();
			
			Patient patientDM = patientDAOExtn.findById( patientId );
			if(patientDM != null) {
				
				patientLiteBM.setPatientId( patientDM.getPatientId() );
				patientLiteBM.setFirstName( patientDM.getFirstName() );
				patientLiteBM.setMiddleName( patientDM.getMiddleName() );
				patientLiteBM.setLastName( patientDM.getLastName() );
				patientLiteBM.setFullName( patientDM.getFullName());
				patientLiteBM.setDateOfBirth( patientDM.getDateOfBirth() );
				if( patientDM.getSaluation() != null ) {
					CodeAndDescription saluation = new CodeAndDescription();
					saluation.setCode( patientDM.getSaluation().getSaluationCode() );
					saluation.setDescription( patientDM.getSaluation().getDescription() );
					patientLiteBM.setTitle( saluation );
				}
				if( patientDM.getGender() != null ) {
					CodeAndDescription gender = new CodeAndDescription();
					gender.setCode( patientDM.getGender().getGenderCode() );
					gender.setDescription( patientDM.getGender().getDescription() );
					patientLiteBM.setGender( gender );
				}
				
//				List<ContactDetailsBM> contactDetailsBMList = contactManager.getContactDetails(ApplicationEntities.PATIENT,  patientDM.getPatientId(), ContactType.CURRENT ); 
//				if( contactDetailsBMList != null && !contactDetailsBMList.isEmpty() ) {
//					ContactDetailsBM contactDetailsBM = contactDetailsBMList.get(0); 
//					patientLiteBM.setContactDetailsBM( contactDetailsBM );
//				}
//				
				ContactDetailsBM contactDetailsBM = contactManager.getContactDetail( patientDM.getCurrentContactDetailId() );
				patientLiteBM.setContactDetailsBM(contactDetailsBM);
				
				patientLiteBM.setBusinessPartnerId( this.getBusinessPartnerId(patientId));
				return patientLiteBM;
				
			} else {
				return null;
			}
		}catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_DETAILS_COULD_NOT_BE_FETCHED );
			HCISException hcisException = new HCISException(fault, exception);
			throw hcisException;
		}
		
	}

	public PatientInfoDetailBM getPatientDetails( PatientInfoSummaryBM patientInfoSummaryBM ) {
		try{
			Patient patientDM = patientDAOExtn.findById( patientInfoSummaryBM.getPersonalDetailsBM().getRegistrationNumber() );
			
			PersonalDetailsBM personalDetailsBM = getPersonalDetailsBM( patientDM );
			PersonalDetailsAdditionalBM personalDetailsAdditionalBM = getPersonalDetailsAdditionalBM( patientDM );
			OtherDetailsBM otherDetailsBM = getOtherDetailsOfPatient( patientDM );
			
//			List<ContactDetailsBM> contactList = contactManager.getContactDetails(ApplicationEntities.PATIENT, patientDM.getPatientId(), null );
//			ContactBM contactBM = new ContactBM();
//			contactBM.setContactDetailList(contactList);

			ContactDetailsBM currentContactDetail  = contactManager.getContactDetail(patientDM.getCurrentContactDetailId());
			ContactDetailsBM permanentContactDetail = null;
			if( patientDM.getPermanentContactDetailId() != null ){
				permanentContactDetail = contactManager.getContactDetail(patientDM.getPermanentContactDetailId());
			}
			
			List<ContactDetailsBM> contactDetailList = new ArrayList<ContactDetailsBM>(0);
			ContactBM contactBM = new ContactBM();
			contactDetailList.add(currentContactDetail);
			contactDetailList.add( permanentContactDetail );
			contactBM.setContactDetailList(contactDetailList);
			
			PatientInfoDetailBM patientInfoDetailBM = new PatientInfoDetailBM();
			patientInfoDetailBM.setPersonalDetailsBM( personalDetailsBM );
			patientInfoDetailBM.setPersonalDetailsAdditionalBM( personalDetailsAdditionalBM );
			patientInfoDetailBM.setContacts(contactBM);
			patientInfoDetailBM.setOtherDetailsBM( otherDetailsBM );
			
			return patientInfoDetailBM;
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_DOES_NOT_EXIST );
			throw new HCISException( fault.getFaultMessage() + exception.getMessage(),
									fault.getFaultCode(),
									fault.getFaultType() );
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
	public String generateRegistrationReceiptSlip(Integer patientId)
	throws HCISException {
	// TODO Auto-generated method stub
	return null;
	}

	public Integer getBusinessPartnerId(Integer pateintId){
		
		return eagleIntegration.getBusinessPartnerId(pateintId, RegistrationConstants.REFERENCE_TYPE_PAT);
	}
	public List<PatientInfoSummaryBM> getPatientHistory(Integer patientId) {
		Patient patientDM = patientDAOExtn.findById( patientId );
		return null;
	}

	public void setPatientDAOExtn(PatientDAOExtn patientDAOExtn) {
		this.patientDAOExtn = patientDAOExtn;
	}

	public void setPatientDetailsDAO(PatientDetailsDAO patientDetailsDAO) {
		this.patientDetailsDAO = patientDetailsDAO;
	}

	public void setIdProofsDAO(IdProofsDAO idProofsDAO) {
		this.idProofsDAO = idProofsDAO;
	}

	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}

	public void setRegistrationTypeDAO(RegistrationTypeDAO registrationTypeDAO) {
		this.registrationTypeDAO = registrationTypeDAO;
	}

	public void setPatientAllergiesDAO(PatientAllergiesDAO patientAllergiesDAO) {
		this.patientAllergiesDAO = patientAllergiesDAO;
	}

	public static void setNORMAL(String normal) {
		NORMAL = normal;
	}

	public void setReferralDAO(ReferralDAO referralDAO) {
		this.referralDAO = referralDAO;
	}

	public void setAllergiesDAO(AllergiesDAO allergiesDAO) {
		this.allergiesDAO = allergiesDAO;
	}


	public void setPatientImmunizationDAO(
			PatientImmunizationDAO patientImmunizationDAO) {
		this.patientImmunizationDAO = patientImmunizationDAO;
	}


	public void setImmunizationDAO(ImmunizationDAO immunizationDAO) {
		this.immunizationDAO = immunizationDAO;
	}


	public void setSaluationDAO(SaluationDAO saluationDAO) {
		this.saluationDAO = saluationDAO;
	}


	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
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


	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}


	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

}