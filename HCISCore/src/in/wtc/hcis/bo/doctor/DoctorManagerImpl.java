package in.wtc.hcis.bo.doctor;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.DoctorDetailBM;
import in.wtc.hcis.bm.base.DoctorEspecialtyBM;
import in.wtc.hcis.bm.base.DoctorLiteBM;
import in.wtc.hcis.bm.base.DoctorSummaryBM;
import in.wtc.hcis.bm.base.ImagePopertyBM;
import in.wtc.hcis.bo.configuration.ConfigurationManager;
import in.wtc.hcis.bo.configuration.ConfigurationManagerImpl;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.bo.constants.ContactType;
import in.wtc.hcis.bo.constants.ImageConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.contact.ContactManager;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.wtc.hcis.da.BloodGroup;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.DoctorDetail;
import com.wtc.hcis.da.DoctorDetailDAO;
import com.wtc.hcis.da.DoctorEspecialty;
import com.wtc.hcis.da.DoctorEspecialtyDAO;
import com.wtc.hcis.da.DoctorEspecialtyId;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.IdProofs;
import com.wtc.hcis.da.Marital;
import com.wtc.hcis.da.Nationality;
import com.wtc.hcis.da.Religion;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.extn.DoctorDAOExtn;

public class DoctorManagerImpl implements DoctorManager,ServletContextAware {
	
	ContactManager contactManager;
	ConfigurationManager configurationManager;
	DoctorDAOExtn doctorDAO;
	DoctorDetailDAO doctorDetailDAO;
	DoctorEspecialtyDAO doctorEspecialtyDAO;
	EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	private static String doctorId = "id.doctorId";
	
	private  ServletContext servletContext;
	private ResourceBundle ImagesConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.Image", Locale.getDefault());
	
	
	public void addDoctor(DoctorBM doctorBM) throws HCISException {
		try {
			Doctor doctor = convertDoctorBM2DM(doctorBM);
			DoctorDetail doctorDetail = convertDoctorDetailBM2DM(doctorBM.getDoctorDetail());
			DoctorEspecialty doctorEspecialty = convertDoctorEspecialtyBM2DM(doctorBM);
			doctor.setCreatedDtm(Calendar.getInstance().getTime());
			doctorDAO.save(doctor);
			doctorDetail.setDoctorId(doctor.getDoctorId());
			
			doctorDetailDAO.save(doctorDetail);
			doctorEspecialty.getId().setDoctorId(doctor.getDoctorId());
			doctorEspecialtyDAO.save(doctorEspecialty);
			
			//save the doctor contact information
			ContactBM contactBM = new ContactBM();
			List<ContactDetailsBM> contactDetailsList = new ArrayList<ContactDetailsBM>();
			if(doctorBM.getContactDetailList()!=null && doctorBM.getContactDetailList().size()>0) {
				Iterator<ContactDetailsBM> contactDetailsIter = doctorBM.getContactDetailList().iterator();
				while(contactDetailsIter.hasNext()) {
					ContactDetailsBM contactDetailsBM = contactDetailsIter.next();
					contactDetailsBM.setPersonelId(doctor.getDoctorId());
					contactDetailsList.add(contactDetailsBM);
				}	
			}
			contactBM.setContactDetailList(contactDetailsList);
			contactManager.addContactDetails(contactBM);
			
			//Create account for patient in EAGLE ERP 
			try {


				AccountInfoBM accountInfoBM = new AccountInfoBM();
				
				
				if (contactBM.getContactDetailList() != null
						&& !contactBM.getContactDetailList().isEmpty()) {
					Iterator<ContactDetailsBM> iter = contactBM.getContactDetailList().iterator();
					while (iter.hasNext()) {
						ContactDetailsBM contactDetailsBM = iter.next();
						if(contactDetailsBM.getContactType().getCode().equals("CURRENT")){
							
							contactDetailsBM.setForEntity( new CodeAndDescription(
														   RegistrationConstants.REFERENCE_TYPE_DOCTOR,""));

							accountInfoBM.setContactDetailsBM(contactDetailsBM);
						}
					}
				}
				accountInfoBM.setEntityCategory(new CodeAndDescription("1000002",""));//TODO: for testing only
				eagleIntegration.createAccount(accountInfoBM, true);

			} catch (Exception e) {
				Fault fault = new Fault(ApplicationErrors.CREATE_ACCOUNT_FAILED);
				throw new HCISException( fault ,e);
			}

		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_ADD_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public List<DoctorSummaryBM> findDoctors(Integer doctorId,
			String department, String speciality, String name,
			String roomNo,
			Double consultationChargeFrom, Double consultationChargeTo,
			Date joiningDateFrom, Date joiningDateTo, int start, int limit, String orderBy) throws HCISException {
		
		String[] orderByInfo;
		
		if (orderBy != null && orderBy.length() > 0) {
			orderByInfo = orderBy.split(" ");
		} else {
			orderByInfo = new String[2];
		}
		List<Doctor> doctorsList = doctorDAO.findDoctors(doctorId, department, speciality, name, roomNo, consultationChargeFrom, consultationChargeTo, joiningDateFrom, joiningDateTo,start, limit, orderByInfo[0], orderByInfo[1]);
		
		List<DoctorSummaryBM> doctorSummaryList = new ArrayList<DoctorSummaryBM>(0);
		if (doctorsList != null) {
			for (Doctor tmpDoctor : doctorsList) {
				if(tmpDoctor.getActive().equals("Y")) {
				Set<DoctorEspecialty> docEspecialityList =  tmpDoctor.getDoctorEspecialties();
				for (DoctorEspecialty tmpEspeciality : docEspecialityList) {
					DoctorSummaryBM tmpSummary = new DoctorSummaryBM();
					tmpSummary.setConsultationCharge(tmpEspeciality.getConsultationCharge());
					CodeAndDescription tmpDepartment = new CodeAndDescription();
					tmpDepartment.setCode(tmpEspeciality.getDepartment().getDepartmentCode());
					tmpDepartment.setDescription(tmpEspeciality.getDepartment().getDepartmentName());
					tmpSummary.setDepartmentCode(tmpDepartment);
					
					CodeAndDescription doctor = new CodeAndDescription();
					doctor.setCode(tmpEspeciality.getDoctor().getDoctorId().toString());
					doctor.setDescription(tmpEspeciality.getDoctor().getFirstName() + " " + tmpEspeciality.getDoctor().getMiddleName() + " " + tmpEspeciality.getDoctor().getLastName());
					tmpSummary.setDoctor(doctor);
					
					CodeAndDescription especiality = new CodeAndDescription();
					especiality.setCode(tmpEspeciality.getEspecialty().getEspecialtyCode());
					especiality.setDescription(tmpEspeciality.getEspecialty().getEspecialtyName());
					tmpSummary.setEspecialtyCode(especiality);
					
					tmpSummary.setFirstName(tmpEspeciality.getDoctor().getFirstName());
					
					CodeAndDescription gender = new CodeAndDescription();
					DoctorDetail tmpDetail = (DoctorDetail)tmpDoctor.getDoctorDetails().toArray()[0];
					if(tmpDetail.getGender()!=null) {
						gender.setCode(tmpDetail.getGender().getGenderCode());
						gender.setDescription(tmpDetail.getGender().getDescription());
						tmpSummary.setGender(gender);
					}
					
					tmpSummary.setKnownLanguages(tmpDetail.getKnownLanguages());
					tmpSummary.setPermanent(tmpDetail.getPermanent() > 0 ? true : false);
					
					if(tmpEspeciality.getRoom()!=null) {
						CodeAndDescription room = new CodeAndDescription();
						room.setCode(tmpEspeciality.getRoom().getRoomCode());
						room.setDescription(tmpEspeciality.getRoom().getDescription());
						
						tmpSummary.setRoomCode(room);
					}
					
					if(tmpDoctor.getSaluation()!=null) {
						CodeAndDescription salutation = new CodeAndDescription();
						salutation.setCode(tmpDoctor.getSaluation().getSaluationCode());
						salutation.setDescription(tmpDoctor.getSaluation().getDescription());
						tmpSummary.setSaluation(salutation);
					}
					
					doctorSummaryList.add(tmpSummary);
				}
			}
			}
		}
		
		return doctorSummaryList;
	}

	public ContactDetailsBM getDoctorAddress(Integer doctorId) throws HCISException {
		try {
			List<ContactDetailsBM> contactList = contactManager.getContactDetails(ApplicationEntities.DOCTOR, doctorId, ContactType.CURRENT);
			
			return contactList.get(0);
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_GET_ADDRESS_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public DoctorBM getDoctorDetail(Integer doctorId) throws HCISException {
		try {
			
			Doctor doctor = doctorDAO.findById(doctorId);
			DoctorDetail doctorDetail = doctorDetailDAO.findById(doctorId);
			List<DoctorEspecialty> doctorEspecialty = doctorEspecialtyDAO.findByProperty("id.doctorId", doctorId);
			
			
			DoctorBM doctorBM = new DoctorBM();
			
			doctorBM.setDoctorId(new Integer(doctor.getDoctorId()));
			doctorBM.setFirstName(doctor.getFirstName());
			doctorBM.setMiddleName(doctor.getMiddleName());
			doctorBM.setLastName(doctor.getLastName());
			
			if(null != doctor && doctor.getSaluation() != null){
				doctorBM.setSaluationCode(new CodeAndDescription(doctor.getSaluation().getSaluationCode(),
																 doctor.getSaluation().getDescription()));
			}
			DoctorDetailBM doctorDetailBM = convertDoctorDetailDM2BM(doctorDetail);
			doctorBM.setDoctorDetail(doctorDetailBM);
			
			List<DoctorEspecialtyBM> docEspecialityList = new ArrayList<DoctorEspecialtyBM>(0);
			for (int i = 0; i < doctorEspecialty.size(); i++) {
				DoctorEspecialtyBM tmpDoctorEspecialtyBM = convertDoctorEspecialtyDM2BM(doctorEspecialty.get(i));
				docEspecialityList.add(tmpDoctorEspecialtyBM);
			}
			
			doctorBM.setDoctorEspecialtyList(docEspecialityList);
			
			List<ContactDetailsBM> contactList = contactManager.getContactDetails(ApplicationEntities.DOCTOR, doctorId, null);
			
			doctorBM.setContactDetailList(contactList);
			
			return doctorBM;
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_GET_DETAIL_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	

	public DoctorLiteBM getDoctorLiteInfo(Integer doctorId)
			throws HCISException {
		DoctorLiteBM doctorLiteBM = new DoctorLiteBM();
		
		Doctor doctor = doctorDAO.findById(doctorId);
		
		doctorLiteBM.setDoctorId(doctor.getDoctorId());
		doctorLiteBM.setFirstName(doctor.getFirstName());
		doctorLiteBM.setMiddleName(doctor.getMiddleName());
		doctorLiteBM.setLastName(doctor.getLastName());
		
		if(doctor.getSaluation()!=null) {
			CodeAndDescription salutation = new CodeAndDescription();
			salutation.setCode(doctor.getSaluation().getSaluationCode());
			salutation.setDescription(doctor.getSaluation().getDescription()); 
			doctorLiteBM.setSaluationCode(salutation);
		}
		

		return doctorLiteBM;
	}

	public DoctorBM modifyDoctor(DoctorBM modifiedDoctorBM)
			throws HCISException {
		try {
			Doctor doctor = convertDoctorBM2DM(modifiedDoctorBM);
			DoctorDetail doctorDetail = convertDoctorDetailBM2DM(modifiedDoctorBM.getDoctorDetail());
			DoctorEspecialty doctorEspecialtyToBeAdded = null;
			// deleting doctor specialty association.
			if( null != modifiedDoctorBM.getDoctorId()){
				List<DoctorEspecialty> doctorEspecialityList = doctorEspecialtyDAO.findByProperty(DoctorManagerImpl.doctorId, modifiedDoctorBM.getDoctorId());
				if( null != doctorEspecialityList && doctorEspecialityList.size() > 0 ){
					for( DoctorEspecialty doctorEspeciality : doctorEspecialityList){
						doctorEspecialtyDAO.delete( doctorEspeciality );
					}
				}
				doctorEspecialtyToBeAdded = convertDoctorEspecialtyBM2DM(modifiedDoctorBM);
			}
			
			doctorDAO.attachDirty(doctor);
			doctorDetailDAO.attachDirty(doctorDetail);
			// deleting association  and adding new association for doctor specialty.
			doctorEspecialtyDAO.save(doctorEspecialtyToBeAdded);
			
			//save the doctor contact information
			ContactBM contactBM = new ContactBM();
			contactBM.setContactDetailList(modifiedDoctorBM.getContactDetailList());
			contactManager.modifyContactDetails(contactBM, ApplicationEntities.DOCTOR);
			
			//Modify Image
			
			if( modifiedDoctorBM.getDoctorDetail().getImagePopertyBM() != null ){
				try {
				
					String tmpImageDirPath = servletContext.getRealPath("/") +
								ImagesConfig.getString(ImageConstants.IMAGES_DIR_PATH).replaceAll("/", File.separator) ;
					
					File tempImage = new File( tmpImageDirPath +File.separator+ 
							modifiedDoctorBM.getDoctorDetail().getImagePopertyBM().getFileName());
					BufferedImage bi = ImageIO.read( tempImage );
					
					String doctorImgDir = servletContext.getRealPath("/") +
					ImagesConfig.getString(ImageConstants.DOCTOR_IMAGES).replaceAll("/", File.separator) ;
					
					File image = new File( doctorImgDir + modifiedDoctorBM.getDoctorId());
					ImageIO.write(bi,"jpg" , image);
					
					if( !tempImage.equals(image)){
						tempImage.delete();

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Failed to save image");
				}

			}

			//Update the contact details in accounting system


			AccountInfoBM accountInfoBM = new AccountInfoBM();
			
			
			if (contactBM.getContactDetailList() != null
					&& !contactBM.getContactDetailList().isEmpty()) {
				Iterator<ContactDetailsBM> iter = contactBM.getContactDetailList().iterator();
				while (iter.hasNext()) {
					ContactDetailsBM contactDetailsBM = iter.next();
					if(contactDetailsBM.getContactType().getCode().equals("CURRENT")){
						
						contactDetailsBM.setForEntity( new CodeAndDescription(
													   RegistrationConstants.REFERENCE_TYPE_DOCTOR,""));

						accountInfoBM.setContactDetailsBM(contactDetailsBM);
					}

				}
			}
			accountInfoBM.setEntityCategory(new CodeAndDescription("1000002",""));//TODO: for testing only

//			eagleIntegration.modifyAccountDetails(accountInfoBM);
			
			return modifiedDoctorBM;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void removeDoctor(List<Integer> doctorIdList) throws HCISException {
		try {
			for (Integer tmpId : doctorIdList) {
				Doctor doctor = doctorDAO.findById(tmpId);
				doctor.setActive("N");
				doctorDAO.attachDirty(doctor);
				
				DoctorDetail doctorDetail = doctorDetailDAO.findById(tmpId);
				
				doctorDetailDAO.attachDirty(doctorDetail);
				
				List<DoctorEspecialty> doctorEspecialtyList = doctorEspecialtyDAO.findByProperty("id.doctorId", tmpId);
				for (DoctorEspecialty tmpDocEspecialty : doctorEspecialtyList) {
					tmpDocEspecialty.setActive(new Short("0"));
					doctorEspecialtyDAO.attachDirty(tmpDocEspecialty);
				}
			}
		
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_REMOVE_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	
	private DoctorEspecialtyBM convertDoctorEspecialtyDM2BM(DoctorEspecialty doctorEspecialty) {
		DoctorEspecialtyBM doctorEspecialtyBM = new DoctorEspecialtyBM();
		doctorEspecialtyBM.setActive(doctorEspecialty.getActive() > 0 ? true : false);
		doctorEspecialtyBM.setConsultationCharge(doctorEspecialty.getConsultationCharge());
		doctorEspecialtyBM.setFollowupCharge(doctorEspecialty.getFollowupCharge());
		doctorEspecialtyBM.setFollowupDay(doctorEspecialty.getFollowupDay());
		doctorEspecialtyBM.setFollowupVisit(doctorEspecialty.getFollowupVisit());
		
		CodeAndDescription department = new CodeAndDescription();
		department.setCode(doctorEspecialty.getDepartment().getDepartmentCode());
		department.setDescription(doctorEspecialty.getDepartment().getDepartmentName());
		doctorEspecialtyBM.setDepartmentCode(department);
		
		CodeAndDescription especiality = new CodeAndDescription();
		especiality.setCode(doctorEspecialty.getEspecialty().getEspecialtyCode());
		especiality.setDescription(doctorEspecialty.getEspecialty().getEspecialtyName()); 
		doctorEspecialtyBM.setEspecialtyCode(especiality);
		
		doctorEspecialtyBM.setJoiningDt(doctorEspecialty.getJoiningDt());
		doctorEspecialtyBM.setLeavingDt(doctorEspecialty.getLeavingDt());
		
		if(doctorEspecialty.getRoom()!=null) {
			CodeAndDescription room = new CodeAndDescription();
			room.setCode(doctorEspecialty.getRoom().getRoomCode());
			room.setDescription(doctorEspecialty.getRoom().getDescription()); 
			doctorEspecialtyBM.setRoomCode(room);
		}
		
		
		return doctorEspecialtyBM;
	}
	
	private DoctorDetailBM convertDoctorDetailDM2BM(DoctorDetail doctorDetail) {
		DoctorDetailBM doctorDetailBM = new DoctorDetailBM();
		
		doctorDetailBM.setBloodDonorId(doctorDetail.getBloodDonorId());
		
		if(doctorDetail.getBloodGroup()!=null) {
			CodeAndDescription bloodGroup = new CodeAndDescription();
			bloodGroup.setCode(doctorDetail.getBloodGroup().getBloodGroupCode());
			bloodGroup.setDescription(doctorDetail.getBloodGroup().getDescription());
			doctorDetailBM.setBloodGroup(bloodGroup);
		}
		
		doctorDetailBM.setDob(doctorDetail.getDob());
		
		if(doctorDetail.getDoctor()!=null) {
			CodeAndDescription doctor = new CodeAndDescription();
			doctor.setCode(doctorDetail.getDoctor().getDoctorId().toString());
			doctor.setDescription(doctorDetail.getDoctor().getFirstName() + " " + doctorDetail.getDoctor().getMiddleName() + " " + doctorDetail.getDoctor().getLastName());
			doctorDetailBM.setDoctor(doctor);
		}
		
		doctorDetailBM.setDutyEndTime(doctorDetail.getDutyEndTime());
		doctorDetailBM.setDutyStartTime(doctorDetail.getDutyStartTime());
		doctorDetailBM.setWorkExperience(doctorDetail.getWorkExperience());
		doctorDetailBM.setFatherHusbandName(doctorDetail.getFatherHusbandName());
		
		if(doctorDetail.getGender()!=null) {
			CodeAndDescription gender = new CodeAndDescription();
			gender.setCode(doctorDetail.getGender().getGenderCode());
			gender.setDescription(doctorDetail.getGender().getDescription());
			doctorDetailBM.setGender(gender);	
		}
		
		
		doctorDetailBM.setHeight(doctorDetail.getHeight());
		doctorDetailBM.setIdNumber(doctorDetail.getIdNumber());
		
		if(doctorDetail.getIdProofs()!=null) {
			CodeAndDescription idProof = new CodeAndDescription();
			idProof.setCode(doctorDetail.getIdProofs().getIdProofsCode());
			idProof.setDescription(doctorDetail.getIdProofs().getDescription());
			doctorDetailBM.setIdProof(idProof);
		}
		
		doctorDetailBM.setIdValidUpto(doctorDetail.getIdValidUpto());
		doctorDetailBM.setJoiningDt(doctorDetail.getJoiningDt());
		doctorDetailBM.setKnownLanguages(doctorDetail.getKnownLanguages());
		doctorDetailBM.setLeavingDt(doctorDetail.getLeavingDt());
		
		if(doctorDetail.getMarital()!=null) {
			CodeAndDescription marital = new CodeAndDescription();
			marital.setCode(doctorDetail.getMarital().getMaritalCode());
			marital.setDescription(doctorDetail.getMarital().getDescription());
			doctorDetailBM.setMarital(marital);
		}

		doctorDetailBM.setPermanent(doctorDetail.getPermanent()>0?true:false);
		
		if(doctorDetail.getReligion()!=null) {
			CodeAndDescription religion = new CodeAndDescription();
			religion.setCode(doctorDetail.getReligion().getReligionCode());
			religion.setDescription(doctorDetail.getReligion().getDescription());
			doctorDetailBM.setReligion(religion);
		}
		
		doctorDetailBM.setWeight(doctorDetail.getWeight());
		doctorDetailBM.setWorkExperience(doctorDetail.getWorkExperience());
		doctorDetailBM.setQualification(doctorDetail.getQualification());
		doctorDetailBM.setReferredBy(doctorDetail.getReferredBy());
		
		if(doctorDetail.getNationality() != null ) {
			CodeAndDescription nationality = new CodeAndDescription();
			nationality.setCode(doctorDetail.getNationality().getNationalityCode());
			nationality.setDescription(doctorDetail.getNationality().getDescription());
			doctorDetailBM.setNationality(nationality);
			
		}
		
		doctorDetailBM.setImagePopertyBM( this.getDoctorImage( doctorDetail.getDoctorId()));
		
		return doctorDetailBM;
	}
	
	private ImagePopertyBM getDoctorImage( Integer doctorId) {

		try {
			String doctorImgDir = servletContext.getRealPath("/") +
					ImagesConfig.getString(ImageConstants.DOCTOR_IMAGES).replaceAll("/", File.separator) ;
			
			File image = new File(doctorImgDir + doctorId); 
			BufferedImage bi = ImageIO.read(image);
			ImagePopertyBM imagePopertyBM = new ImagePopertyBM();
			imagePopertyBM.setBufferedImage( bi );
			imagePopertyBM.setFileName( image.getName() );
			return imagePopertyBM;
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Image for Doctor "+doctorId+" not exists. ");
		}
		return null;
	}
	
	private DoctorEspecialty convertDoctorEspecialtyBM2DM (DoctorBM doctorBM) {
		
		DoctorEspecialty doctorEspecialty = new DoctorEspecialty();
		
		DoctorEspecialtyId doctorEspecialtyId = new DoctorEspecialtyId();
		if(!doctorBM.getDoctorEspecialtyList().get(0).getDepartmentCode().getCode().equals("")) {
		doctorEspecialtyId.setDepartmentCode(doctorBM.getDoctorEspecialtyList().get(0).getDepartmentCode().getCode());
		}
		
		if(!doctorBM.getDoctorEspecialtyList().get(0).getEspecialtyCode().getCode().equals("")) {
		doctorEspecialtyId.setEspecialtyCode(doctorBM.getDoctorEspecialtyList().get(0).getEspecialtyCode().getCode());
		}
		if(doctorBM.getDoctorId()!=null){
			doctorEspecialtyId.setDoctorId(doctorBM.getDoctorId());
		}
		
		if(doctorBM.getDoctorEspecialtyList()!=null && !doctorBM.getDoctorEspecialtyList().isEmpty()){
			doctorEspecialty.setConsultationCharge(doctorBM.getDoctorEspecialtyList().get(0).getConsultationCharge());
		}
		
		doctorEspecialty.setFollowupDay(doctorBM.getDoctorEspecialtyList().get(0).getFollowupDay());
		doctorEspecialty.setFollowupVisit(doctorBM.getDoctorEspecialtyList().get(0).getFollowupVisit());
		
		if(doctorBM.getDoctorEspecialtyList().get(0).getFollowupCharge()!=null){
			
			doctorEspecialty.setFollowupCharge(doctorBM.getDoctorEspecialtyList().get(0).getFollowupCharge());
		}else{
			doctorEspecialty.setFollowupCharge(Double.parseDouble(configurationManager.getSystemConfiguration(ConfigurationConstants.FOLLOWUP_CHARGE)));
		}
		
		doctorEspecialty.setId(doctorEspecialtyId);
		doctorEspecialty.setActive(new Short(doctorBM.getDoctorEspecialtyList().get(0).getActive()?"1":"0"));
		doctorEspecialty.setJoiningDt(doctorBM.getDoctorEspecialtyList().get(0).getJoiningDt());
		doctorEspecialty.setLeavingDt(doctorBM.getDoctorEspecialtyList().get(0).getLeavingDt());
		
		if(doctorBM.getDoctorEspecialtyList().get(0).getRoomCode()!=null ) {
			Room room = new Room();
			room.setRoomCode(doctorBM.getDoctorEspecialtyList().get(0).getRoomCode().getCode());
			room.setDescription(doctorBM.getDoctorEspecialtyList().get(0).getRoomCode().getDescription());
			doctorEspecialty.setRoom(room);
		}
		
		return doctorEspecialty;
	}
	
	private Doctor convertDoctorBM2DM(DoctorBM doctorBM) {
		Doctor doctor = null;
		if(doctorBM.getDoctorId()!=null ){
			 doctor = doctorDAO.findById(doctorBM.getDoctorId());
		}else {
			doctor = new Doctor();
		}
		
		if(doctorBM.getDoctorId()!=null) {
			doctor.setDoctorId(new Integer(doctorBM.getDoctorId()));	
		}
		doctor.setActive( doctorBM.getActive()?"Y":"N");
		doctor.setFirstName(doctorBM.getFirstName());
		doctor.setMiddleName(doctorBM.getMiddleName());
		doctor.setLastName(doctorBM.getLastName());
		
		String fullName = "";
		if(doctorBM.getFirstName() != null && 
				doctorBM.getFirstName().length() > 0){
			fullName = doctorBM.getFirstName();
		}
		if(doctorBM.getMiddleName() != null && 
				doctorBM.getMiddleName().length() > 0){
			fullName = fullName + " " + doctorBM.getMiddleName();
		}
		if(doctorBM.getLastName() != null &&
				doctorBM.getLastName().length() > 0){
			fullName = fullName + " " + doctorBM.getLastName();
		}
		
		doctor.setFullName(fullName);
		
		if(doctorBM.getSaluationCode()!=null ){
			Saluation saluation = new Saluation();
			saluation.setSaluationCode(doctorBM.getSaluationCode().getCode());
			saluation.setDescription(doctorBM.getSaluationCode().getDescription());
			doctor.setSaluation(saluation);
		}
		return doctor;
	}
	
	private DoctorDetail convertDoctorDetailBM2DM(DoctorDetailBM doctorDetailBM) {
		
		DoctorDetail doctorDetailDM = null;
		if(doctorDetailBM.getDoctor()!=null && 
			doctorDetailBM.getDoctor().getCode()!=null && 
			!doctorDetailBM.getDoctor().getCode().equals("")){
			
			 doctorDetailDM = doctorDetailDAO.findById(Integer.parseInt(doctorDetailBM.getDoctor().getCode()));
		}else {
			doctorDetailDM = new DoctorDetail();
		}
		
		doctorDetailDM.setBloodDonorId(doctorDetailBM.getBloodDonorId());
		
		
		if(doctorDetailBM.getBloodGroup()!=null ) {
			BloodGroup bloodGroup = new BloodGroup();
			bloodGroup.setBloodGroupCode(doctorDetailBM.getBloodGroup().getCode());
			bloodGroup.setDescription(doctorDetailBM.getBloodGroup().getDescription());
			doctorDetailDM.setBloodGroup(bloodGroup);	
		}
		
		doctorDetailDM.setCreatedDtm(new Date());
		doctorDetailDM.setDob(doctorDetailBM.getDob());
		
		if(doctorDetailBM.getDoctor()!=null ) {
			doctorDetailDM.setDoctorId(new Integer(doctorDetailBM.getDoctor().getCode()));
		}
		doctorDetailDM.setDutyEndTime(doctorDetailBM.getDutyEndTime());
		doctorDetailDM.setDutyStartTime(doctorDetailBM.getDutyStartTime());
		doctorDetailDM.setFatherHusbandName(doctorDetailBM.getFatherHusbandName());
		
		if(doctorDetailBM.getGender()!=null ) {
			Gender gender = new Gender();
			gender.setGenderCode(doctorDetailBM.getGender().getCode());
			gender.setDescription(doctorDetailBM.getGender().getDescription());
			doctorDetailDM.setGender(gender);
		}
		
		if(doctorDetailBM.getNationality()!=null) {
			Nationality nationality = new Nationality();
			nationality.setNationalityCode(doctorDetailBM.getNationality().getCode());
			nationality.setDescription(doctorDetailBM.getNationality().getDescription());
			doctorDetailDM.setNationality(nationality);
		}
		doctorDetailDM.setQualification(doctorDetailBM.getQualification());
		doctorDetailDM.setReferredBy(doctorDetailBM.getReferredBy());
		doctorDetailDM.setHeight(doctorDetailBM.getHeight());
		doctorDetailDM.setIdNumber(doctorDetailBM.getIdNumber());
		
		if(doctorDetailBM.getIdProof()!=null ) {
			IdProofs idProofs = new IdProofs();
			idProofs.setIdProofsCode(doctorDetailBM.getIdProof().getCode());
			idProofs.setDescription(doctorDetailBM.getIdProof().getDescription());
			doctorDetailDM.setIdProofs(idProofs);
		}
		
		doctorDetailDM.setIdValidUpto(doctorDetailBM.getIdValidUpto());
		doctorDetailDM.setJoiningDt(doctorDetailBM.getJoiningDt());
		doctorDetailDM.setKnownLanguages(doctorDetailBM.getKnownLanguages());
		doctorDetailDM.setLeavingDt(doctorDetailBM.getLeavingDt());
		
		if(doctorDetailBM.getMarital()!=null ) {
			Marital marital = new Marital();
			marital.setMaritalCode(doctorDetailBM.getMarital().getCode());
			marital.setDescription(doctorDetailBM.getMarital().getDescription());
			doctorDetailDM.setMarital(marital);
		}
		
		doctorDetailDM.setPermanent(new Short(doctorDetailBM.getPermanent()?"1":"0"));
		
		if(doctorDetailBM.getReligion()!=null ) {
			Religion religion = new Religion();
			religion.setReligionCode(doctorDetailBM.getReligion().getCode());
			religion.setDescription(doctorDetailBM.getReligion().getDescription());
			doctorDetailDM.setReligion(religion);
		}
		
		if(doctorDetailBM.getSaluation()!=null ) {
			Saluation saluation = new Saluation();
			saluation.setSaluationCode(doctorDetailBM.getSaluation().getCode());
			saluation.setDescription(doctorDetailBM.getSaluation().getDescription());
		}
		
		doctorDetailDM.setWeight(doctorDetailBM.getWeight());
		doctorDetailDM.setWorkExperience(doctorDetailBM.getWorkExperience());
		
		return doctorDetailDM;
	}

	/**
	 * @param contactManager the contactManager to set
	 */
	public void setContactManager(ContactManager contactManager) {
		this.contactManager = contactManager;
	}

	/**
	 * @param doctorDAO the doctorDAO to set
	 */
	public void setDoctorDAO(DoctorDAOExtn doctorDAO) {
		this.doctorDAO = doctorDAO;
	}

	/**
	 * @param doctorDetailDAO the doctorDetailDAO to set
	 */
	public void setDoctorDetailDAO(DoctorDetailDAO doctorDetailDAO) {
		this.doctorDetailDAO = doctorDetailDAO;
	}

	/**
	 * @param doctorEspecialtyDAO the doctorEspecialtyDAO to set
	 */
	public void setDoctorEspecialtyDAO(DoctorEspecialtyDAO doctorEspecialtyDAO) {
		this.doctorEspecialtyDAO = doctorEspecialtyDAO;
	}

	public Double getConsultationCharge(Integer doctorId) throws HCISException {
		try {
			List<DoctorEspecialty> doctorEspecialtyList = doctorEspecialtyDAO.findByProperty("id.doctorId", doctorId);
			if(doctorEspecialtyList!=null && doctorEspecialtyList.size()>0) {
				return doctorEspecialtyList.get(0).getConsultationCharge();
			}
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_GET_CONSULTATION_CHARGES);
			HCISException hcisException = new HCISException(fault, exception);
			throw hcisException;
		}
		return null;
	}
	
	public Double getConsultationForFollowUpCharge (Integer doctorId) throws HCISException {
		try {
			List<DoctorEspecialty> doctorEspecialtyList = doctorEspecialtyDAO.findByProperty("id.doctorId", doctorId);
			if(doctorEspecialtyList!=null && doctorEspecialtyList.size()>0) {
				return doctorEspecialtyList.get(0).getFollowupCharge();
			}
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.DOCTOR_GET_CONSULTATION_CHARGES);
			HCISException hcisException = new HCISException(fault, exception);
			throw hcisException;
		}
		return null;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

}
