


/**
 * 
 */
package in.wtc.hcis.bo.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ServiceSummaryBM;
import in.wtc.hcis.bo.appointment.AppointmentConstants;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ReferenceDataConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.RosterConstants;
import in.wtc.hcis.bo.constants.ScheduleManagerConstants;
import in.wtc.hcis.bo.constants.ServicesConstants;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.wtc.hcis.da.Allergies;
import com.wtc.hcis.da.AllergiesDAO;
import com.wtc.hcis.da.AppEntities;
import com.wtc.hcis.da.AppEntitiesDAO;
import com.wtc.hcis.da.AppointmentStatus;
import com.wtc.hcis.da.AppointmentStatusDAO;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.AppointmentsDAO;
import com.wtc.hcis.da.BloodGroup;
import com.wtc.hcis.da.BloodGroupDAO;
import com.wtc.hcis.da.BookingType;
import com.wtc.hcis.da.BookingTypeDAO;
import com.wtc.hcis.da.Brand;
import com.wtc.hcis.da.BrandDAO;
import com.wtc.hcis.da.ChangeReason;
import com.wtc.hcis.da.ChangeReasonDAO;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.CountryDAO;
import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.DepartmentDAO;
import com.wtc.hcis.da.DeptEspecialityAssoc;
import com.wtc.hcis.da.Disease;
import com.wtc.hcis.da.DiseaseDAO;
import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.DocCheckListDAO;
import com.wtc.hcis.da.DocCheckListDetail;
import com.wtc.hcis.da.DocCheckListDetailDAO;
import com.wtc.hcis.da.DocCheckListInstance;
import com.wtc.hcis.da.DocCheckListInstanceDAO;
import com.wtc.hcis.da.DocPatientVital;
import com.wtc.hcis.da.DocPatientVitalDAO;
import com.wtc.hcis.da.DocVitalField;
import com.wtc.hcis.da.DocVitalFieldDAO;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.DoctorEspecialty;
import com.wtc.hcis.da.DoctorEspecialtyDAO;
import com.wtc.hcis.da.Entity;
import com.wtc.hcis.da.EntityDAO;
import com.wtc.hcis.da.Especialty;
import com.wtc.hcis.da.EspecialtyDAO;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.GenderDAO;
import com.wtc.hcis.da.IdProofs;
import com.wtc.hcis.da.IdProofsDAO;
import com.wtc.hcis.da.Immunization;
import com.wtc.hcis.da.ImmunizationDAO;
import com.wtc.hcis.da.Marital;
import com.wtc.hcis.da.MaritalDAO;
import com.wtc.hcis.da.MedicineType;
import com.wtc.hcis.da.MedicineTypeDAO;
import com.wtc.hcis.da.Medicines;
import com.wtc.hcis.da.MedicinesDAO;
import com.wtc.hcis.da.MotherTongue;
import com.wtc.hcis.da.MotherTongueDAO;
import com.wtc.hcis.da.Nationality;
import com.wtc.hcis.da.NationalityDAO;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.PatientCategory;
import com.wtc.hcis.da.PatientCategoryDAO;
import com.wtc.hcis.da.PatientRating;
import com.wtc.hcis.da.PatientRatingDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;
import com.wtc.hcis.da.ReferenceDataListId;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.RegistrationStatus;
import com.wtc.hcis.da.RegistrationStatusDAO;
import com.wtc.hcis.da.RegistrationType;
import com.wtc.hcis.da.RegistrationTypeDAO;
import com.wtc.hcis.da.Relation;
import com.wtc.hcis.da.RelationDAO;
import com.wtc.hcis.da.Religion;
import com.wtc.hcis.da.ReligionDAO;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.RoomDAO;
import com.wtc.hcis.da.Roster;
import com.wtc.hcis.da.RosterDAO;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.SaluationDAO;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceGroup;
import com.wtc.hcis.da.ServiceGroupDAO;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.ServicePackageDAO;
import com.wtc.hcis.da.ServicePackageStatus;
import com.wtc.hcis.da.ServicePackageStatusDAO;
import com.wtc.hcis.da.ServiceStatus;
import com.wtc.hcis.da.ServiceStatusDAO;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.StateDAO;
import com.wtc.hcis.da.StateId;
import com.wtc.hcis.da.Status;
import com.wtc.hcis.da.StatusDAO;
import com.wtc.hcis.da.Vaccination;
import com.wtc.hcis.da.VaccinationDAO;
import com.wtc.hcis.da.VaccinationSchedule;
import com.wtc.hcis.da.VaccinationScheduleDAO;
import com.wtc.hcis.da.extn.DeptEspecialityAssocDAOExtn;
import com.wtc.hcis.da.extn.DoctorDAOExtn;
import com.wtc.hcis.da.extn.PatientDAOExtn;
import com.wtc.hcis.da.extn.ReferralDAOExtn;
import com.wtc.hcis.da.extn.ServiceDAOExtn;

/**
 * @author Alok Ranjan
 *
 */
public class DataModelManagerImpl implements DataModelManager {

	private AppointmentsDAO appointmentsDAO;
	private AppointmentStatusDAO appointmentStatusDAO;
	private BookingTypeDAO bookingTypeDAO;
	private DoctorDAOExtn doctorDAO;
	private PatientDAOExtn patientDAO;
	private RosterDAO rosterDAO;
	private RoomDAO roomDAO;
	private GenderDAO genderDAO;
	private ServiceGroupDAO serviceGroupDAO;
	private ServiceStatusDAO serviceStatusDAO;
	private BloodGroupDAO bloodGroupDAO;
	private DepartmentDAO departmentDAO;
	private EspecialtyDAO especialtyDAO;
	private AllergiesDAO allergiesDAO;
	private ImmunizationDAO immunizationDAO;
	private IdProofsDAO idProofsDAO;
	private MaritalDAO maritalDAO;
	private MotherTongueDAO motherTongueDAO;
	private NationalityDAO nationalityDAO;
	private PatientCategoryDAO patientCategoryDAO;
	private PatientRatingDAO patientRatingDAO;
	private ReferralDAOExtn referralDAO;
	private RegistrationStatusDAO registrationStatusDAO;
	private RegistrationTypeDAO registrationTypeDAO;
	private RelationDAO relationDAO;
	private ReligionDAO religionDAO; 
	private SaluationDAO saluationDAO;
	private ServiceDAOExtn serviceDAO;
	private CountryDAO countryDAO;
	private StateDAO stateDAO;
	private MedicineTypeDAO medicineTypeDAO;
	private ChangeReasonDAO changeReasonDAO;
	private BrandDAO brandDAO;
	private StatusDAO statusDAO;
	private AppEntitiesDAO appEntitiesDAO;
	private MedicinesDAO medicinesDAO;
	private ServicePackageDAO servicePackageDAO;
	private ServicePackageStatusDAO servicePackageStatusDAO;
	private DiseaseDAO diseaseDAO;
	private DoctorEspecialtyDAO doctorEspecialtyDAO;
	private ReferenceDataListDAO referenceDataListDAO;
	private DeptEspecialityAssocDAOExtn deptEspecialityAssocDAO;
	private VaccinationScheduleDAO vaccinationScheduleDAO;
	private VaccinationDAO vaccinationDAO;
	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	private EntityDAO entityDAO ;
	private DocPatientVitalDAO patientVitalDAO;
	private DocVitalFieldDAO vitalFieldDAO;
	private DocCheckListDAO checkListDAO;
	private DocCheckListDetailDAO checkListDetailDAO;
	private DocCheckListInstanceDAO checkListInstanceDAO;
	
	private static String DEPRTMENT_CODE = "id.departmentCode";
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getAppointment(java.lang.Integer)
	 */
	
	public Appointments getAppointment( Integer appointmentNumber ) throws HCISException {
		try {
			Appointments appointments = appointmentsDAO.findById( appointmentNumber );
			
			if ( appointments == null ) {
				throw new Exception( " Appointment with appointment number = " + appointmentNumber + " does not exist. " );
			}
			
			return appointments;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_APPOINTMENT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getAppointmentStatus(java.lang.String)
	 */
	public AppointmentStatus getAppointmentStatus(String statusCode)
			throws HCISException {
		try {
			AppointmentStatus appointmentStatus = appointmentStatusDAO.findById( statusCode );
			
			if ( appointmentStatus == null ) {
				throw new Exception( " Appointment status with status code = " + statusCode + " does not exist. " );
			}
			
			return appointmentStatus;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_APPOINTMENT_STATUS_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getBookingType(java.lang.String)
	 */
	public BookingType getBookingType(String bookingTypeCd)
			throws HCISException {
		try {
			BookingType bookingType = bookingTypeDAO.findById( bookingTypeCd );
			
			if ( bookingType == null ) {
				throw new Exception( " Appointment booking type reference data with BOOKING_TYPE_CD = " + bookingTypeCd + " does not exist. " );
			}
			
			return bookingType;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BOOKING_TYPE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	/**
	 * This method gets the department corresponding to the specified department code
	 * @param departmentCode
	 * @return
	 * @throws HCISException
	 */
	public Department getDepartment(String departmentCode) 
			throws HCISException {
		try {
			Department department = departmentDAO.findById( departmentCode );
			
			if ( department == null ) {
				throw new Exception( " Departmet reference data with DEPARTMENT_CODE = " + departmentCode + " does not exist. " );
			}
			
			return department;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DEPARTMENT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getDoctor(java.lang.Integer)
	 */
	public Doctor getDoctor( Integer doctorId ) throws HCISException {
		try {
			Doctor doctor = doctorDAO.findById( doctorId );
			
			if ( doctor == null ) {
				throw new Exception( " Doctor with DOCTOR_ID = " + doctorId + " does not exist. " );
			}
			
			return doctor;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getPatient(java.lang.Integer)
	 */
	public Patient getPatient(Integer patientId) throws HCISException {
		try {
			Patient patient = patientDAO.findById( patientId );
			
			if ( patient == null ) {
				throw new Exception( " Patient with PATIENT_ID = " + patientId + " does not exist. " );
			}
			
			return patient;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_PATIENT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.common.DataModelManager#getRoster(java.lang.Integer)
	 */
	public Roster getRoster(Integer rosterCode) throws HCISException {
		try {
			Roster roster = rosterDAO.findById( rosterCode );
			
			if ( roster == null ) {
				throw new Exception( " Doctor roster does not exist. " );
			}
			
			return roster;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ROSTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + ". ROSTER_CODE = " + rosterCode ,
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	/**
	 * 
	 */
	public Room getRoom(String roomCode ) throws HCISException {
		try {
			Room room = roomDAO.findById( roomCode );
			
			if ( room == null ) {
				throw new Exception( " Room with ROOM_CODE = " + roomCode + " does not exist" );
			}
		
			return room;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ROOM_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public Service getServiceByCode (String serviceCode) throws HCISException {
		try {
			Service service = serviceDAO.findById( serviceCode );
			
			if ( service == null ) {
				throw new Exception( " Service with Service Code : " + serviceCode + " does not exist" );
			}
		
			return service;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_SERVICE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	public ServicePackage getServicePackageByCode (String servicePackageCode) throws HCISException {
		try {
			ServicePackage servicePackage = servicePackageDAO.findById( servicePackageCode );
			
			if ( servicePackage == null ) {
				throw new Exception( " Service package with Service package Code : " + servicePackageCode + " does not exist" );
			}
		
			return servicePackage;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_SERVICE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public Country getCountrybyCode (String countryCode) throws HCISException {
		try {
			Country country = countryDAO.findById( countryCode );
			
			if ( country == null ) {
				throw new Exception( " Country with Country Code : " + countryCode + " does not exist" );
			}
		
			return country;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_COUNTRY_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public State getStateByCode (StateId stateId) throws HCISException {
		try {
			State state = stateDAO.findById( stateId );
			
			if ( state == null ) {
				throw new Exception( " State with State Id : " + stateId + " does not exist" );
			}
		
			return state;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_STATE_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	
	
	public ListRange getGender(int start, int count, String orderBy ) throws HCISException {
		 ListRange listRange = new ListRange();
		 try {
			    List<Gender> genderList =(List<Gender>)genderDAO.findAll();
			    Object[] codeDescObj = new Object[genderList.size()];
			    if(genderList!=null && genderList.size()>0) {
			    	for(int i =0; i<genderList.size();i++) {
			    		Gender gender = genderList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(gender.getGenderCode());
				    	codeAndDescription.setDescription(gender.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(genderList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getBloodGroup(int start, int count, String orderBy) throws HCISException {
		 ListRange listRange = new ListRange();
		 try {
			    List<BloodGroup> bloodGroupList =(List<BloodGroup>)bloodGroupDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[bloodGroupList.size()];
			    if(bloodGroupList!=null && bloodGroupList.size()>0) {
			    	for(int i =0; i<bloodGroupList.size();i++) {
			    		BloodGroup bloodGroup = bloodGroupList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(bloodGroup.getBloodGroupCode());
				    	codeAndDescription.setDescription(bloodGroup.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(bloodGroupList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
		
	}

	public ListRange getCountry(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Country> countryList =(List<Country>)countryDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[countryList.size()];
			    if(countryList!=null && countryList.size()>0) {
			    	for(int i =0; i<countryList.size();i++) {
			    		Country country = countryList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(country.getCountryCode());
				    	codeAndDescription.setDescription(country.getDescription());
				    	codeAndDescription.setIsDefault(country.getIsDefault());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(countryList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;	
	}

	public ListRange getDepartments(int start, int count, String orderBy) throws HCISException 
	{
	 ListRange listRange = new ListRange();
	 try {
		     List<Department> departmentList =(List<Department>)departmentDAO.findByProperty( "active", new Short("1") );
		    Object[] codeDescObj = new Object[departmentList.size()];
		    if(departmentList!=null && departmentList.size()>0) {
		    	for(int i =0; i<departmentList.size();i++) {
		    		Department department = departmentList.get(i);
		    					    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(department.getDepartmentCode()); 
			    	codeAndDescription.setDescription(department.getDepartmentName());
			    	codeDescObj[i]=codeAndDescription;
			    	
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(departmentList.size());
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return listRange;
	}
	
	
	
	public ListRange getDepartmentType(int start, int count, String orderBy) throws HCISException 
	{
		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
    }


	public ListRange getEspectiality(int start, int count, String orderBy) throws HCISException 
			{ 
				ListRange listRange = new ListRange();
				try {
			     List<Especialty> especialtyList =(List<Especialty>)especialtyDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[especialtyList.size()];
			    if(especialtyList!=null && especialtyList.size()>0) {
			    	for(int i =0; i<especialtyList.size();i++) {
			    		Especialty especialty = especialtyList.get(i);
			    		CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(especialty.getEspecialtyCode()); 
				    	codeAndDescription.setDescription(especialty.getEspecialtyName());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(especialtyList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
			}


	public ListRange getFitnessActivity(int start, int count, String orderBy) throws HCISException 
	{

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getIdProof(int start, int count, String orderBy) {
	 ListRange listRange = new ListRange();
	 try {
		    List<IdProofs> idProofsList =(List<IdProofs>)idProofsDAO.findByProperty( "active", new Short("1") );
		    Object[] codeDescObj = new Object[idProofsList.size()];
		    if(idProofsList!=null && idProofsList.size()>0) {
		    	for(int i =0; i<idProofsList.size();i++) {
		    		IdProofs idProofs = idProofsList.get(i);
			    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(idProofs.getIdProofsCode());
			    	codeAndDescription.setDescription(idProofs.getDescription());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(idProofsList.size());
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return listRange;
	}

	public ListRange getMaritalStatus(int start, int count, String orderBy)throws HCISException 
	{
	 ListRange listRange = new ListRange();
	 try {
		    List<Marital> maritalList  =(List<Marital>)maritalDAO.findByProperty( "active", "Y" );
		    Object[] codeDescObj = new Object[maritalList.size()];
		    if(maritalList!=null && maritalList.size()>0) {
		    	for(int i =0; i<maritalList.size();i++) {
		    		Marital marital = maritalList.get(i);
			    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(marital.getMaritalCode());
			    	codeAndDescription.setDescription(marital.getDescription());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(maritalList.size());

	} catch (Exception e) {
		e.printStackTrace();
		}
	return listRange;
	}

	public ListRange getMotherTongue(int start, int count, String orderBy) throws HCISException 
	{

		 ListRange listRange = new ListRange();
		 try {
			    List<MotherTongue> motherTongueList =(List<MotherTongue>)	motherTongueDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[motherTongueList.size()];
			    if(motherTongueList!=null && motherTongueList.size()>0) {
			    	for(int i =0; i<motherTongueList.size();i++) {
			    		MotherTongue motherTongue = motherTongueList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(motherTongue.getMotherTongueCode());
				    	codeAndDescription.setDescription(motherTongue.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(motherTongueList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
		
	}

	public ListRange getNationality(int start, int count, String orderBy) throws HCISException 
	{
		ListRange listRange = new ListRange();
	       try {
		    List<Nationality> nationalityList =(List<Nationality>)nationalityDAO.findByProperty( "active", new Short("1") );
		    Object[] codeDescObj = new Object[nationalityList.size()];
		    if(nationalityList!=null && nationalityList.size()>0) {
		    	for(int i =0; i<nationalityList.size();i++) {
		    		Nationality nationality = nationalityList.get(i);
			    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(nationality.getNationalityCode());
			    	codeAndDescription.setDescription(nationality.getDescription());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(nationalityList.size());
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return listRange;
	}

	public ListRange getPatientCategory(int start, int count, String orderBy) throws HCISException {

		ListRange listRange = new ListRange();
	       try {
		    List<PatientCategory> patientCategoryList =(List<PatientCategory>)patientCategoryDAO.findByProperty( "active", new Short("1") );
		    Object[] codeDescObj = new Object[patientCategoryList.size()];
		    if(patientCategoryList!=null && patientCategoryList.size()>0) {
		    	for(int i =0; i<patientCategoryList.size();i++) {
		    		PatientCategory patientCategory = patientCategoryList.get(i);
			    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(patientCategory.getCategoryCode());
			    	codeAndDescription.setDescription(patientCategory.getDescription());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(patientCategoryList.size());
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return listRange;
	}

	public ListRange getPatientRating(int start, int count, String orderBy)	throws HCISException
	{
		ListRange listRange = new ListRange();
	       try {
		    List<PatientRating> patientRatingList =(List<PatientRating>)patientRatingDAO.findByProperty( "active", new Short("1") );
		    Object[] codeDescObj = new Object[patientRatingList.size()];
		    if(patientRatingList!=null && patientRatingList.size()>0) {
		    	for(int i =0; i<patientRatingList.size();i++) {
		    		PatientRating patientRating = patientRatingList.get(i);
			    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(patientRating.getRatingCode());
			    	codeAndDescription.setDescription(patientRating.getDescription());
			    	codeDescObj[i]=codeAndDescription;
		    	}
			 }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(patientRatingList.size());
		}
	 catch (Exception e) {
		e.printStackTrace();
	}
	 return listRange;	
	}

	public ListRange getReferral( String referralType, int start, int count, String orderBy) throws HCISException
	{
		ListRange listRange = new ListRange();
		
	    try {
	    	List<Referral> referralList = referralDAO.getActiveReferralOfType(referralType);
		    Object[] codeDescObj = new Object[referralList.size()];
		    if ( referralList != null && !referralList.isEmpty() ) {
			    if(referralList!=null && referralList.size()>0) {
			    	for(int i =0; i<referralList.size();i++) {
			    		Referral referral = referralList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(referral.getReferralCode().toString());
				    	
				    	StringBuilder referralDesc = new StringBuilder( referral.getReferralName() );
				    	referralDesc.append(" ( ");
				    	referralDesc.append( referral.getAddress() );
				    	referralDesc.append(", ");
				    	referralDesc.append( referral.getCity() );
				    	referralDesc.append( " ) " );
				    	
				    	codeAndDescription.setDescription( referralDesc.toString() );
				    	
				    	codeDescObj[i] = codeAndDescription;
			    	}	
			    }
		    }
		    listRange.setData( codeDescObj );
			listRange.setTotalSize(referralList.size());
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
		return listRange;	
			
	}

	public ListRange getRegistrationStatus(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<RegistrationStatus> registrationStatusList =(List<RegistrationStatus>)registrationStatusDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[registrationStatusList.size()];
			    if(registrationStatusList!=null && registrationStatusList.size()>0) {
			    	for(int i =0; i<registrationStatusList.size();i++) {
			    		RegistrationStatus registrationStatus = registrationStatusList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(registrationStatus.getRegistrationStatusCode());
				    	codeAndDescription.setDescription(registrationStatus.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(registrationStatusList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
		}

	public ListRange getRegistrationType(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<RegistrationType> registrationTypeList =(List<RegistrationType>)registrationTypeDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[registrationTypeList.size()];
			    if(registrationTypeList!=null && registrationTypeList.size()>0) {
			    	for(int i =0; i<registrationTypeList.size();i++) {
			    		RegistrationType registrationType = registrationTypeList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(registrationType.getRegistrationTypeCode());
				    	codeAndDescription.setDescription(registrationType.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(registrationTypeList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	
	}

	public ListRange getRelationship(int start, int count, String orderBy)	throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Relation> relationList =(List<Relation>)relationDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[relationList.size()];
			    if(relationList!=null && relationList.size()>0) {
			    	for(int i =0; i<relationList.size();i++) {
			    		Relation  relation = relationList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(relation.getRelationCode());
				    	codeAndDescription.setDescription(relation.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(relationList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getReligion(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Religion> religionList =(List<Religion>)religionDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[religionList.size()];
			    if(religionList!=null && religionList.size()>0) {
			    	for(int i =0; i<religionList.size();i++) {
			    		Religion  religion = religionList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(religion.getReligionCode());
				    	codeAndDescription.setDescription(religion.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(religionList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getRoomNo(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Room> roomList =(List<Room>)roomDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[roomList.size()];
			    if(roomList!=null && roomList.size()>0) {
			    	for(int i =0; i<roomList.size();i++) {
			    		Room  room = roomList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(room.getRoomCode());
				    	codeAndDescription.setDescription(room.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(roomList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	
	}

	public ListRange getSalutation(int start, int count, String orderBy) throws HCISException
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Saluation> saluationList =(List<Saluation>)saluationDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[saluationList.size()];
			    if(saluationList!=null && saluationList.size()>0) {
			    	for(int i =0; i<saluationList.size();i++) {
			    		Saluation  saluation = saluationList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(saluation.getSaluationCode());
				    	codeAndDescription.setDescription(saluation.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(saluationList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getService(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<Service> serviceList =(List<Service>)serviceDAO.findAllActiveServices();
			    Object[] codeDescObj = new Object[serviceList.size()];
			    if(serviceList!=null && serviceList.size()>0) {
			    	for(int i =0; i<serviceList.size();i++) {
			    		Service  service = serviceList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(service.getServiceCode());
				    	codeAndDescription.setDescription(service.getServiceName());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(serviceList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}
	
	public ListRange getServicePackage(int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<ServicePackage> servicePackageList =(List<ServicePackage>)servicePackageDAO.findAll();
			    Object[] codeDescObj = new Object[servicePackageList.size()];
			    if(servicePackageList!=null && servicePackageList.size()>0) {
			    	for(int i =0; i<servicePackageList.size();i++) {
			    		ServicePackage servicePackage = servicePackageList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(servicePackage.getPackageId());
				    	codeAndDescription.setDescription(servicePackage.getName());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(servicePackageList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getState(int start, int count, String orderBy)
			throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<State> stateList =(List<State>)stateDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[stateList.size()];
			    if(stateList!=null && stateList.size()>0) {
			    	for(int i =0; i<stateList.size();i++) {
			    		State state = stateList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(state.getId().getStateCode());
				    	codeAndDescription.setDescription(state.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(stateList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getAllergies(int start, int count, String orderBy)
			throws HCISException {
		ListRange listRange = new ListRange();
		 try {
			    List<Allergies> allergiesList =(List<Allergies>)allergiesDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[allergiesList.size()];
			    if( allergiesList != null && allergiesList.size()>0 ) {
			    	for( int i = 0; i < allergiesList.size() ; i++ ) {
			    		Allergies allergies = allergiesList.get( i );
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode( allergies.getAllergiesCode().toString() );
				    	codeAndDescription.setDescription( allergies.getAllergryDescription() );
				    	codeDescObj[i]=codeAndDescription;
			    	}
				    	
			    }
			    listRange.setData( codeDescObj );
				listRange.setTotalSize( allergiesList.size() );
			   
		} catch ( Exception exception ) {
			exception.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getImmunizations(int start, int count, String orderBy)
			throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<Immunization> immunizationsList = 
				(List<Immunization>) immunizationDAO.findByProperty("active", new Short("1"));
			Object[] codeDescObj = new Object[immunizationsList.size()];
			if (immunizationsList != null && immunizationsList.size() > 0) {
				for (int i = 0; i < immunizationsList.size(); i++) {
					Immunization immunization = immunizationsList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(immunization.getName().toString());
					codeAndDescription.setDescription(immunization.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(immunizationsList.size());

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return listRange;
	}

	public ListRange getDoctors(int start, int count, String orderBy) throws HCISException 
			{ ListRange listRange = new ListRange();
			 try {
				    List<Doctor> doctorList =(List<Doctor>)doctorDAO.findByProperty( "active","Y" );
				    Object[] codeDescObj = new Object[doctorList.size()];
				    if(doctorList!=null && doctorList.size()>0) {
				    	for(int i =0; i<doctorList.size();i++) {
					    	CodeAndDescription codeAndDescription = new CodeAndDescription();
					    	Doctor doctor = doctorList.get(i);
					    	if( doctor != null ){
						    	codeAndDescription.setCode(doctor.getDoctorId().toString());
						    	String doctorName = "";
									if(doctor.getFirstName() != null && doctor.getFirstName().length() > 0){
										doctorName = doctor.getFirstName()+ " ";
									}
									if(doctor.getMiddleName() != null && doctor.getMiddleName().length() > 0){
										doctorName = doctorName + doctor.getMiddleName()+" ";
									}
									if(doctor.getLastName() != null && doctor.getLastName().length() > 0){
										doctorName = doctorName + doctor.getLastName();
									}
								codeAndDescription.setDescription( doctorName );
						    	codeDescObj[i]=codeAndDescription;
					    	}
				    	}
				    }
				    listRange.setData(codeDescObj);
					listRange.setTotalSize(doctorList.size());
				   
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return listRange;
	}
	
	public ListRange getDoctorsOfDepartment( String departmentCode, 
											 int start, 
											 int count, 
											 String orderBy) throws HCISException{ 
		ListRange listRange = new ListRange();
		try {
			if(departmentCode != null && !departmentCode.isEmpty()){
				
				
				
				List<Doctor> activeDoctorsList = doctorDAO.getActiveDoctorOfDepartment(departmentCode);
				Object[] codeDescObj = new Object[activeDoctorsList.size()];
				
				
				int index = 0;
				
				for(Doctor doctor : activeDoctorsList){
				
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					
					if( doctor != null && ("Y").equals(doctor.getActive())){
						codeAndDescription.setCode(doctor.getDoctorId().toString());
						String doctorName = "";
						if(doctor.getFirstName() != null && doctor.getFirstName().length() > 0){
							doctorName = doctor.getFirstName()+ " ";
						}
						if(doctor.getMiddleName() != null && doctor.getMiddleName().length() > 0){
							doctorName = doctorName + doctor.getMiddleName()+" ";
						}
						if(doctor.getLastName() != null && doctor.getLastName().length() > 0){
							doctorName = doctorName + doctor.getLastName();
						}
						codeAndDescription.setDescription( doctorName );
						codeDescObj[index]=codeAndDescription;
						index++;
					}
				}
				listRange.setData(codeDescObj);
				listRange.setTotalSize(activeDoctorsList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getAppointmentWithStatus(int start, int count, String orderBy)	throws HCISException
	{
		 ListRange listRange = new ListRange();
		 try {
			    List<AppointmentStatus> appointmentStatusList =(List<AppointmentStatus>)appointmentStatusDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[appointmentStatusList.size()];
			    if(appointmentStatusList!=null && appointmentStatusList.size()>0) {
			    	for(int i =0; i<appointmentStatusList.size();i++) {
			    		AppointmentStatus  appointmentStatus = appointmentStatusList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(appointmentStatus.getStatusCode());
				    	codeAndDescription.setDescription(appointmentStatus.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(appointmentStatusList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	public ListRange getBookingTypes(int start, int count, String orderBy)
	throws HCISException {
		 ListRange listRange = new ListRange();
		 try {
			    List<BookingType> bookingTypeList =(List<BookingType>)bookingTypeDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[bookingTypeList.size()];
			    if(bookingTypeList!=null && bookingTypeList.size()>0) {
			    	for(int i =0; i<bookingTypeList.size();i++) {
			    		BookingType  bookingType = bookingTypeList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(bookingType.getBookingTypeCode());
				    	codeAndDescription.setDescription(bookingType.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(bookingTypeList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;

	}

	
	
	public ListRange getTitle(int start, int count, String orderBy)	throws HCISException
	{
		ListRange listRange = new ListRange();
		 try {
			    List<Saluation> saluationList =(List<Saluation>)saluationDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[saluationList.size()];
			    if(saluationList!=null && saluationList.size()>0) {
			    	for(int i =0; i<saluationList.size();i++) {
			    		Saluation  saluation = saluationList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(saluation.getSaluationCode());
				    	codeAndDescription.setDescription(saluation.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(saluationList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}

	
	public ListRange getForms(int start, int count, String orderBy)	throws HCISException 
	{
		ListRange listRange = new ListRange();
		 try {
			    List<MedicineType> medicineTypeList =(List<MedicineType>)medicineTypeDAO.findByProperty( "active", new Short("1"));
			    Object[] codeDescObj = new Object[medicineTypeList.size()];
			    if(medicineTypeList!=null && medicineTypeList.size()>0) {
			    	for(int i =0; i<medicineTypeList.size();i++) {
			    		MedicineType  medicineType = medicineTypeList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(medicineType.getMedicineTypeCode());
				    	codeAndDescription.setDescription(medicineType.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(medicineTypeList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}
	
	public ListRange getCancellationReason(int start, int count, String orderBy) throws HCISException
	{
		ListRange listRange = new ListRange();
		 try {
			    List<ChangeReason> changeReasonList =(List<ChangeReason>)changeReasonDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[changeReasonList.size()];
			    if(changeReasonList!=null && changeReasonList.size()>0) {
			    	for(int i =0; i<changeReasonList.size();i++) {
			    		ChangeReason  changeReason = changeReasonList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(changeReason.getChangeReasonCode());
				    	codeAndDescription.setDescription(changeReason.getDescription());
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(changeReasonList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;

	}

	public ListRange getSpecialityType(int start, int count, String orderBy)	throws HCISException 
	{
		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}
	
	
	public ListRange getPeriod(int start, int count, String orderBy) throws HCISException 
	{

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getServiceType(int start, int count, String orderBy) throws HCISException 
	{

		List<Service> list = serviceDAO.findAll();
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for( Service tmp : list ) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode( tmp.getServiceCode() );
			tmpObj.setDescription( tmp.getServiceName() );
			
			retList.add( tmpObj );
		}
		ListRange listRange = new ListRange();
		listRange.setData( retList.toArray() );
		listRange.setTotalSize( retList.size() );
		
		return listRange;
	}

	public ListRange getInsuranceCompany(int start, int count, String orderBy)
			throws HCISException {

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getSponserName(int start, int count, String orderBy)
			throws HCISException {

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getSponserStatus(int start, int count, String orderBy)
			throws HCISException {

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getSponserType(int start, int count, String orderBy)
			throws HCISException {

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;
	}

	public ListRange getPatientLstVisited(int start, int count, String orderBy)
			throws HCISException {

		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;

	}

	public List<CodeAndDescription> getServiceList() throws HCISException {
		List<Service> svcList = serviceDAO.findAll();
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(Service tmpSvc : svcList) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmpSvc.getServiceCode());
			tmpObj.setDescription(tmpSvc.getServiceName());
			
			retList.add(tmpObj);
		}
		
		return retList;
	}

	public ListRange getSessions(int start, int count, String orderBy)
			throws HCISException {
		ListRange listRange = new ListRange();
		Object[] codeDescObj = new Object[0];
		listRange.setData(codeDescObj);
		return listRange;

	}

	public ListRange getServiceGroupList(int start, int count,
			String orderBy) throws HCISException {
		List<ServiceGroup> svcGroupList = serviceGroupDAO.findAll();
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(ServiceGroup tmpSvcGrp : svcGroupList) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmpSvcGrp.getServiceGroupCode());
			tmpObj.setDescription(tmpSvcGrp.getGroupName());
			if( !tmpSvcGrp.getServiceGroupCode().equals("Administrative")){
				retList.add(tmpObj);
			}
		}
		
		ListRange listRange = new ListRange();
		listRange.setData(retList.toArray());
		listRange.setTotalSize(retList.size());
		
		return listRange;
	}

	public ListRange getServiceStatusList(int start, int count, String orderBy) throws HCISException {
		List<ServiceStatus> svcStatusList = serviceStatusDAO.findByProperty( "active", new Short("1") );
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(ServiceStatus tmpSvcStatus : svcStatusList) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmpSvcStatus.getServiceStatusCode());
			tmpObj.setDescription(tmpSvcStatus.getDescription());
			
			retList.add(tmpObj);
		}
		ListRange listRange = new ListRange();
		listRange.setData(retList.toArray());
		listRange.setTotalSize(retList.size());
		
		return listRange;
	} 
	/**
	 * This method returns all services which belongs to given service group.
	 * If no group name is given then it returns all active services.
	 */
	public ListRange getServicesOfGroup(String serviceGroupCode,int start, int count, String orderBy )throws HCISException{
		
		List<Service> serviceList = null;
		if( serviceGroupCode != null && !serviceGroupCode.equals("") ){
			serviceList = serviceDAO.findServicesOfGroup(serviceGroupCode.trim());
		}else{
			serviceList = serviceDAO.findAllActiveServices();
		}
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(Service tmpSvc : serviceList) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmpSvc.getServiceCode());
			tmpObj.setDescription(tmpSvc.getServiceName());
			
			retList.add(tmpObj);
		}
		
		ListRange listRange = new ListRange();
		listRange.setData(retList.toArray());
		listRange.setTotalSize(retList.size());
		
		return listRange;
	}
	
	public ListRange getServicePackageStatus(int start, int count, String orderBy) throws HCISException{
		
		List<ServicePackageStatus> servicePackageStatusList = servicePackageStatusDAO.findAll();
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(ServicePackageStatus tmpSvcPkg : servicePackageStatusList) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmpSvcPkg.getStatusCode());
			tmpObj.setDescription(tmpSvcPkg.getDescription());
			
			retList.add(tmpObj);
		}
		
		ListRange listRange = new ListRange();
		listRange.setData(retList.toArray());
		listRange.setTotalSize(retList.size());
		
		return listRange;
		
	}

	public ListRange getProfitCentersList(int start, int count, String orderBy) throws HCISException {
		List<Department> list = departmentDAO.findByProperty( "active", new Short("1") );
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for(Department tmp : list) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode(tmp.getDepartmentCode());
			tmpObj.setDescription(tmp.getDepartmentName());
			
			retList.add(tmpObj);
		}
		
		ListRange listRange = new ListRange();
		listRange.setData(retList.toArray());
		listRange.setTotalSize(retList.size());
		
		return listRange;
	}
	
	public ListRange getBrand(int start, int count, String orderBy) throws HCISException 
	{
		List<Brand> list = brandDAO.findByProperty("active", new Short("1") );
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for( Brand tmp : list ) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode( tmp.getBrandCode() );
			tmpObj.setDescription( tmp.getDescription() );
			
			retList.add( tmpObj );
		}
		ListRange listRange = new ListRange();
		listRange.setData( retList.toArray() );
		listRange.setTotalSize( retList.size() );
		
		return listRange;
	}

	public ListRange getMedicineType(int start, int count, String orderBy) throws HCISException 
	{
		List<MedicineType> list = medicineTypeDAO.findByProperty( "active", new Short("1") );
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for( MedicineType tmp : list ) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode( tmp.getMedicineTypeCode() );
			tmpObj.setDescription( tmp.getDescription() );
			
			retList.add( tmpObj );
		}
		ListRange listRange = new ListRange();
		listRange.setData( retList.toArray() );
		listRange.setTotalSize( retList.size() );
		
		return listRange;
	}
	
	public ListRange getStatus(int start, int count, String orderBy) throws HCISException 
	{
		List<Status> list = statusDAO.findAll();
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for( Status tmp : list ) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode( tmp.getId().getStatusCode().toString() );
			tmpObj.setDescription( tmp.getId().getStatusDesc() );
			
			retList.add( tmpObj );
		}
		ListRange listRange = new ListRange();
		listRange.setData( retList.toArray() );
		listRange.setTotalSize( retList.size() );
		
		return listRange;
	}
	
	public ListRange getStateWithCountry( String countryCode, int start, int count, String orderBy ) throws HCISException 
	{
		if( countryCode != null && !countryCode.equalsIgnoreCase("") ) {
			List<State> list = stateDAO.findByProperty( "id.countryCode", countryCode );
			
			List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
			for( State tmp : list ) {
				CodeAndDescription tmpObj = new CodeAndDescription();
				tmpObj.setCode( tmp.getId().getStateCode() );
				tmpObj.setDescription( tmp.getDescription() );
				
				retList.add( tmpObj );
			}
			ListRange listRange = new ListRange();
			listRange.setData( retList.toArray() );
			listRange.setTotalSize( retList.size() );
			
			return listRange;
		}
		return null;
	}
	
	public ListRange getIdsForEntity(String forEntity,int start, int count, String orderBy) throws HCISException 
	{
		ListRange listRange = new ListRange();
		try
		{
			if( forEntity != null && !forEntity.equalsIgnoreCase("") ) {
				if( forEntity.equalsIgnoreCase( ApplicationEntities.DOCTOR ) ) {
					List<Doctor> doctorList = doctorDAO.findByProperty( "active", "Y" );
					Object[] codeDescObj = new Object[doctorList.size()];
					if(doctorList!=null && doctorList.size()>0) {
						for(int i =0; i<doctorList.size();i++) {
							Doctor doctor = doctorList.get(i);
							CodeAndDescription codeAndDescription = new CodeAndDescription();
							codeAndDescription.setCode(doctor.getDoctorId().toString());
							codeAndDescription.setDescription(doctor.getFirstName()+" "+doctor.getMiddleName()+" "+doctor.getLastName());
							codeDescObj[i]=codeAndDescription;
						}
					}
					listRange.setData(codeDescObj);
					listRange.setTotalSize(doctorList.size());
				}else{
					listRange.setData(new Object[0]);
					listRange.setTotalSize(0);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return listRange;
	}

	public ListRange getEntityType(int start, int count, String orderBy) throws HCISException 
	{
		List<AppEntities> list = appEntitiesDAO.findByProperty( "active", new Short("1") );
		
		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		for( AppEntities tmp : list ) {
			CodeAndDescription tmpObj = new CodeAndDescription();
			tmpObj.setCode( tmp.getEntityType() );
			tmpObj.setDescription( tmp.getEntityName() );
			
			retList.add( tmpObj );
		}
		ListRange listRange = new ListRange();
		listRange.setData( retList.toArray() );
		listRange.setTotalSize( retList.size() );
		
		return listRange;
	}
	
	public ListRange getMonths(int start, int count, String orderBy) throws HCISException {
		  DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);  
		  String [] months = symbols.getMonths();
		  List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		  if(months !=null && months.length>0) {
			  for (int i =0; i<months.length;i++) {
				  if( months[i].length()>0){
				  	CodeAndDescription tmpObj = new CodeAndDescription();
					tmpObj.setCode( new Integer(i+1).toString() );
					tmpObj.setDescription( months[i] ); 
					retList.add(tmpObj);
				  }
			  }
		  }
		  ListRange listRange = new ListRange();
			listRange.setData( retList.toArray() );
			listRange.setTotalSize( retList.size() );
			
			return listRange;
	}
	
	public ListRange getYears(int start, int count, String orderBy) throws HCISException {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		  List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
			  for (int i=0;i<=RosterConstants.NO_OF_YEARS;i++) {
				  CodeAndDescription tmpObj = new CodeAndDescription();
					tmpObj.setCode( new Integer(year).toString() );
					tmpObj.setDescription( new Integer(year).toString() ); 
					retList.add(tmpObj);
					year++;
		  }
			  ListRange listRange = new ListRange();
			  listRange.setData( retList.toArray() );
			  listRange.setTotalSize( retList.size() );
			
			return listRange;
	}
	
	public ListRange getWeeks(String month, String year,int start, int count, String orderBy) throws HCISException {
		
		List<CodeAndDescription> recList = new ArrayList<CodeAndDescription>();
		ListRange listRange = new ListRange();
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.set(new Integer(year),new Integer(month),1);
		int startWeekNo = startCalendar.get(Calendar.WEEK_OF_YEAR );
		int endWeekNo;
		Calendar endCalendar = Calendar.getInstance();
		
		if(month.equals("0")|| month.equals("2") || month.equals("4") || 
				month.equals("6") || month.equals("7") ||month.equals("9") ||month.equals("11") ) {
			endCalendar.set(new Integer(year),new Integer(month), 31);
			endWeekNo = endCalendar.get(Calendar.WEEK_OF_YEAR );
			if(endWeekNo == 1){
				endWeekNo = endCalendar.getMaximum(Calendar.WEEK_OF_YEAR);
			}
			
		} else if(month.equals("3") || month.equals("5") || month.equals("8") || month.equals("10")) {
			 endCalendar.set(new Integer(year),new Integer(month), 30);
			 endWeekNo = endCalendar.get(Calendar.WEEK_OF_YEAR );
		} else {
			if(checkLeapYear(year)) {
				 endCalendar.set(new Integer(year),new Integer(month), 29);
				 endWeekNo = endCalendar.get(Calendar.WEEK_OF_YEAR );
			}else {
				 endCalendar.set(new Integer(year),new Integer(month),28);
				 endWeekNo = endCalendar.get(Calendar.WEEK_OF_YEAR );
			}
		}
		while(endWeekNo>=startWeekNo) {
			CodeAndDescription codeObj = new CodeAndDescription();
			codeObj.setCode(new Integer(startWeekNo).toString());
			codeObj.setDescription(new Integer(startWeekNo).toString());
			recList.add(codeObj);
			startWeekNo++;
			
		}
		  listRange.setData( recList.toArray() );
		  listRange.setTotalSize( recList.size() );
		  
		  return listRange;
	}
	
	public ListRange getWeekDays(int start, int count, String orderBy)throws HCISException {
		DateFormatSymbols symbols = new DateFormatSymbols(Locale.US);  
		  String [] days = symbols.getWeekdays();
		  List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
		  if(days !=null && days.length>0) {
			  for (int i =0; i<days.length;i++) {
				  	if(!days[i].equals("")){
				  		CodeAndDescription tmpObj = new CodeAndDescription();
						tmpObj.setCode( new Integer(i).toString() );
						tmpObj.setDescription( days[i] ); 
						retList.add(tmpObj);
				  	}
				  
			  }
		  }
		  	ListRange listRange = new ListRange();
			listRange.setData( retList.toArray() );
			listRange.setTotalSize( retList.size() );
			
			return listRange;
	}
	public ListRange getPatients (int start, int count, String orderBy)throws HCISException {
		 ListRange listRange = new ListRange();
		 try {
			     List<Patient> patientList = patientDAO.findAll();
			    Object[] codeDescObj = new Object[patientList.size()];
			   
			    if(patientList!=null && patientList.size()>0) {
			    	for(int i =0; i< patientList.size();i++) {
			    		Patient patient  = patientList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	
							String patientName = "";
							if(patient.getFirstName() != null && patient.getFirstName().length() > 0){
								patientName = patient.getFirstName()+ " ";
							}
							if(patient.getMiddleName() != null && patient.getMiddleName().length() > 0){
								patientName = patientName + patient.getMiddleName()+" ";
							}
							if(patient.getLastName() != null && patient.getLastName().length() > 0){
								patientName = patientName + patient.getLastName();
							}
				    	codeAndDescription.setCode(patient.getPatientId().toString()); 
				    	codeAndDescription.setDescription( patientName );
				    	codeDescObj[i]=codeAndDescription;
			    	}
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(patientList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}
	
	public ListRange getMedicines (int start, int count, String orderBy)throws HCISException {
		 ListRange listRange = new ListRange();
		 try {
			     List<Medicines> medicineList =(List<Medicines>)medicinesDAO.findAll();
			    Object[] codeDescObj = new Object[medicineList.size()];
			    if(medicineList!=null && medicineList.size()>0) {
			    	for(int i =0; i<medicineList.size();i++) {
			    		Medicines medicine = medicineList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(medicine.getMedicineCode()); 
				    	codeAndDescription.setDescription(medicine.getMedicineName());
				    	codeDescObj[i]=codeAndDescription;
			    	}
			    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(medicineList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	}
	private boolean checkLeapYear(String year) {
		Integer yrs = new Integer(year); 
		if(yrs%4 == 0)
		{
			if(yrs% 10 != 0)
			{
				return true;
			}
			else
			{
				if(yrs% 400 == 0)
					return true;
				else
					return false;
			}
		}
	return false;
	}
//	public ListRange getPatientCategory(int start, int count, String orderBy) throws HCISException {
//		List<Department> list = departmentDAO.findAll();
//		
//		List<CodeAndDescription> retList = new ArrayList<CodeAndDescription>();
//		for(Department tmp : list) {
//			CodeAndDescription tmpObj = new CodeAndDescription();
//			tmpObj.setCode(tmp.getDepartmentCode());
//			tmpObj.setDescription(tmp.getDepartmentName());
//			
//			retList.add(tmpObj);
//		}
//		
//		ListRange listRange = new ListRange();
//		listRange.setData(retList.toArray());
//		listRange.setTotalSize(retList.size());
//		
//		return listRange;
//	}
	
	public ListRange getDiscountTypesList ( int start, int count, String orderBy )throws HCISException{
		ListRange listRange = new ListRange();
		try {
			Object[] discountTypeObj = new Object[2];
			
			CodeAndDescription codeAndDescForAbsolute = new CodeAndDescription();
			codeAndDescForAbsolute.setCode( "A" );
			codeAndDescForAbsolute.setDescription( "Absolute" );
			discountTypeObj[0] = codeAndDescForAbsolute;
			
			CodeAndDescription codeAndDescForPercentage = new CodeAndDescription();
			codeAndDescForPercentage.setCode( "P" );
			codeAndDescForPercentage.setDescription( "Percentage" );
			discountTypeObj[1] = codeAndDescForPercentage;
			
			listRange.setData( discountTypeObj);
			listRange.setTotalSize( discountTypeObj.length );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getChargeOverrideTypesList ( int start, int count, String orderBy )throws HCISException{
		ListRange listRange = new ListRange();
		try {
			Object[] chargeOverrideTypeObj = new Object[2];
			
			CodeAndDescription codeAndDescForPackageLvl = new CodeAndDescription();
			codeAndDescForPackageLvl.setCode( ServicesConstants.CHARGE_OVERRIDE_LEVEL_PACKAGE );
			codeAndDescForPackageLvl.setDescription( "Package Level" );
			chargeOverrideTypeObj[0] = codeAndDescForPackageLvl;
			
			CodeAndDescription codeAndDescForServiceLvl = new CodeAndDescription();
			codeAndDescForServiceLvl.setCode( ServicesConstants.CHARGE_OVERRIDE_LEVEL_SERVICE );
			codeAndDescForServiceLvl.setDescription( "Service Level" );
			chargeOverrideTypeObj[1] = codeAndDescForServiceLvl;
			
			listRange.setData( chargeOverrideTypeObj);
			listRange.setTotalSize( chargeOverrideTypeObj.length );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}
	
	public ListRange getReferenceTypeList ( int start, int count, String orderBy ){
		ListRange listRange = new ListRange();
		try {
			Object[] chargeOverrideTypeObj = new Object[4];
			
			CodeAndDescription codeAndDescForOPD = new CodeAndDescription();
			codeAndDescForOPD.setCode( ServicesConstants.SERVICE_ASSIGNED_FROM_OPD );
			codeAndDescForOPD.setDescription( "Out patient" );
			chargeOverrideTypeObj[0] = codeAndDescForOPD;
			
			CodeAndDescription codeAndDescForIPD = new CodeAndDescription();
			codeAndDescForIPD.setCode( ServicesConstants.SERVICE_ASSIGNED_FROM_IPD );
			codeAndDescForIPD.setDescription( "In patient" );
			chargeOverrideTypeObj[1] = codeAndDescForIPD;
			
			CodeAndDescription codeAndDescForDirect = new CodeAndDescription();
			codeAndDescForDirect.setCode( ServicesConstants.SERVICE_ASSIGNED_FROM_DIRECT );
			codeAndDescForDirect.setDescription( "Direct" );
			chargeOverrideTypeObj[2] = codeAndDescForDirect;
			
			CodeAndDescription codeAndDescForEmergency = new CodeAndDescription();
			codeAndDescForEmergency.setCode( ServicesConstants.SERVICE_ASSIGNED_FROM_EMEREGENCY );
			codeAndDescForEmergency.setDescription( "Emergency" );
			chargeOverrideTypeObj[3] = codeAndDescForEmergency;
			
			listRange.setData( chargeOverrideTypeObj);
			listRange.setTotalSize( chargeOverrideTypeObj.length );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getDisease(int start, int count, String orderBy)throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<Disease> diseaseList = diseaseDAO.findAll();
			Object[] codeDescObj = new Object[diseaseList.size()];
			if (diseaseList != null && diseaseList.size() > 0) {
				for (int i = 0; i < diseaseList.size(); i++) {
					Disease tmp = diseaseList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getDiseaseName());
					codeAndDescription.setDescription(tmp.getDescription());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(diseaseList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	
	public ListRange getReferenceDataList(String context, int start, int count, String orderBy ) throws HCISException{
		ListRange listRange = new ListRange();
		try {
			List<ReferenceDataList> referenceDataList = referenceDataListDAO.findByProperty("id.context",context);
			Object[] codeDescObj = new Object[referenceDataList.size()];
			if (referenceDataList != null && referenceDataList.size() > 0) {
				for (int i = 0; i < referenceDataList.size(); i++) {
					ReferenceDataList tmp = referenceDataList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getId().getAttrCode());
					codeAndDescription.setDescription(tmp.getAttrDesc());
					codeAndDescription.setIsDefault(tmp.getIsDefault());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(referenceDataList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

	public ListRange getReportNameDataList(int start, int count, String orderBy ) throws HCISException{
		ListRange listRange = this.getReferenceDataList(ReferenceDataConstants.REPORT,start,count,orderBy);
		return listRange;
		
	}
	
	
	
	public ReferenceDataList getReferenceData(String context, String attrCode)throws HCISException{
		try {
			ReferenceDataListId id = new ReferenceDataListId();
			id.setContext(context);
			id.setAttrCode(attrCode);
			
			ReferenceDataList referenceData = referenceDataListDAO.findById(id);
			if( null == referenceData ){
				throw new Exception( "Reference data with '" + attrCode + "'for context '"+context +"' does not exist");
			}
			return referenceData;
		} catch (Exception exception) {
			Fault fault = new Fault( ApplicationErrors.REFERENCE_DATA_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	public ListRange getPeriodIndictors(int start, int count, String orderBy)
		throws HCISException {
	
		ListRange listRange = getReferenceDataList( ScheduleManagerConstants.PERIOD_IND,
													start,
													count,
													orderBy );
		return listRange;
	}
	
	public ListRange getVaccinationType(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = getReferenceDataList( ScheduleManagerConstants.VACCINATION_TYPE,
													start,
													count,
													orderBy );
	return listRange;
	}
	
	public ListRange getActiveVaccinationScheduleFlag(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = getReferenceDataList( ScheduleManagerConstants.VACCINATION_ACTIVE_FLAG,
													start,
													count,
													orderBy );
	return listRange;
	}
	
	public ListRange getVaccinations(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = new ListRange();
		List<Vaccination> vaccinationList = vaccinationDAO.findAll();
		
		Object[] codeDescObj = new Object[vaccinationList.size()];
		if (vaccinationList != null && vaccinationList.size() > 0) {
			for (int i = 0; i < vaccinationList.size(); i++) {
				Vaccination tmp = vaccinationList.get(i);
				
				CodeAndDescription codeAndDescription = new CodeAndDescription();
				codeAndDescription.setCode(tmp.getVaccinationCd());
				codeAndDescription.setDescription(tmp.getVaccinationName());
				codeDescObj[i] = codeAndDescription;
			}

		}
		listRange.setData(codeDescObj);
		listRange.setTotalSize(vaccinationList.size());
		return listRange;
	}
	
	public ListRange getHeightIndicators(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = getReferenceDataList( RegistrationConstants.HEIGHT_INDICATOR,
													start,
													count,
													orderBy );
		return listRange;
	}
	
	public ListRange getWeightIndicators(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = getReferenceDataList( RegistrationConstants.WEIGHT_INDICATOR,
													start,
													count,
													orderBy );
		return listRange;
	}
	
	public ListRange getAppointmentType(int start, int count, String orderBy)
		throws HCISException {
		ListRange listRange = getReferenceDataList( AppointmentConstants.APPONITMENT_TYPE,
													start,
													count,
													orderBy );
		return listRange;
	}
	
	public ListRange getSpeacialityForDepartmant( String departmentCode, int start, int count, String orderBy) throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<DeptEspecialityAssoc> depList = deptEspecialityAssocDAO.getSpecialityForDepartment(departmentCode);
			Object[] codeDescObj = new Object[depList.size()];
			if (depList != null && depList.size() > 0) {
				for (int i = 0; i < depList.size(); i++) {
					DeptEspecialityAssoc tmp = depList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getEspecialty().getEspecialtyCode());
					codeAndDescription.setDescription(tmp.getEspecialty().getEspecialtyName());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(depList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getDoctorsOfSpeaciality( String speacialityCode, int start, int count, String orderBy) throws HCISException{
		 
		ListRange listRange = new ListRange();
		try {
			if(speacialityCode != null && !speacialityCode.isEmpty()){
				
				
				
				List<Doctor> activeDoctorsList = doctorDAO.getActiveDoctorOfSpeaciality(speacialityCode);
				Object[] codeDescObj = new Object[activeDoctorsList.size()];
				
				int index = 0;
				
				for(Doctor doctor : activeDoctorsList){
				
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					
					if( doctor != null && ("Y").equals(doctor.getActive())){
						codeAndDescription.setCode(doctor.getDoctorId().toString());
						String doctorName = "";
						if(doctor.getFirstName() != null && doctor.getFirstName().length() > 0){
							doctorName = doctor.getFirstName()+ " ";
						}
						if(doctor.getMiddleName() != null && doctor.getMiddleName().length() > 0){
							doctorName = doctorName + doctor.getMiddleName()+" ";
						}
						if(doctor.getLastName() != null && doctor.getLastName().length() > 0){
							doctorName = doctorName + doctor.getLastName();
						}
						codeAndDescription.setDescription( doctorName );
						codeDescObj[index]=codeAndDescription;
						index++;
					}
				}
				listRange.setData(codeDescObj);
				listRange.setTotalSize(activeDoctorsList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getVaccinationSchedule( int start, int count, String orderBy) throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<VaccinationSchedule> vaccinationScheduleList = vaccinationScheduleDAO.findAll();
			Object[] codeDescObj = new Object[vaccinationScheduleList.size()];
			if (vaccinationScheduleList != null && vaccinationScheduleList.size() > 0) {
				for (int i = 0; i < vaccinationScheduleList.size(); i++) {
					VaccinationSchedule tmp = vaccinationScheduleList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getScheduleName());
					codeAndDescription.setDescription(tmp.getScheduleDesc());
					codeDescObj[i] = codeAndDescription;
				}
			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(vaccinationScheduleList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getActiveVaccinationSchedule( int start, int count, String orderBy) throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<VaccinationSchedule> vaccinationScheduleList = vaccinationScheduleDAO.findByActiveFlag("Y");
			Object[] codeDescObj = new Object[vaccinationScheduleList.size()];
			if (vaccinationScheduleList != null && vaccinationScheduleList.size() > 0) {
				for (int i = 0; i < vaccinationScheduleList.size(); i++) {
					VaccinationSchedule tmp = vaccinationScheduleList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getScheduleName());
					codeAndDescription.setDescription(tmp.getScheduleDesc());
					codeDescObj[i] = codeAndDescription;
				}
			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(vaccinationScheduleList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}
	
	public ListRange getActiveStatus( int start, int count, String orderBy) throws HCISException {
		ListRange listRange = getReferenceDataList(  ReferenceDataConstants.ACTIVE_STATUS, start, count,orderBy );
		return listRange;
	}
	public ListRange getCreditCardType(int start, int count, String orderBy){
		
		List<CodeAndDescription> creditCardTypeList = eagleIntegration.getCreditCardType();
		
		ListRange listRange = new ListRange();
		if( creditCardTypeList != null && !creditCardTypeList.isEmpty() ){
			Object[] codeDescObj = new Object[creditCardTypeList.size()];
			codeDescObj = creditCardTypeList.toArray();
			listRange.setData(codeDescObj);
			listRange.setTotalSize(creditCardTypeList.size());
		}else{
			listRange.setData( new Object[0]);
			listRange.setTotalSize(0);
		}
		return listRange;
	}
	
	public ListRange getRosterEntities(int start, int count, String orderBy) throws HCISException {
		ListRange listRange = getReferenceDataList( ReferenceDataConstants.ROSTER_ENTITIES, 
													start,
													count,
													orderBy );

		return listRange;
	}
	
	public ListRange getPaymentModes(int start, int count, String orderBy){
		List<CodeAndDescription> paymentModesList = eagleIntegration.getPaymentModes();
		
		ListRange listRange = new ListRange();
		if( paymentModesList != null && !paymentModesList.isEmpty() ){
			Object[] codeDescObj = new Object[paymentModesList.size()];
			codeDescObj = paymentModesList.toArray();
			listRange.setData(codeDescObj);
			listRange.setTotalSize(paymentModesList.size());
		}else{
			listRange.setData( new Object[0]);
			listRange.setTotalSize(0);
		}
		return listRange;
	}
	
	public ListRange getTransactionTypes(int start, int count, String orderBy){
		List<CodeAndDescription> transactionTypesList = eagleIntegration.getTransactionTypes();
		
		ListRange listRange = new ListRange();
		if( transactionTypesList != null && !transactionTypesList.isEmpty() ){
			Object[] codeDescObj = new Object[transactionTypesList.size()];
			codeDescObj = transactionTypesList.toArray();
			listRange.setData(codeDescObj);
			listRange.setTotalSize(transactionTypesList.size());
		}else{
			listRange.setData( new Object[0]);
			listRange.setTotalSize(0);
		}
		return listRange;
	}
	
	
	public ListRange getServiceTypes(int start, int count, String orderBy ) throws HCISException{
		ListRange listRange = new ListRange();
		try {
			List<ReferenceDataList> referenceDataList = referenceDataListDAO.findByProperty("id.context",ServicesConstants.SERVICE_TYPE);
			Object[] codeDescObj = new Object[referenceDataList.size()];
			if (referenceDataList != null && referenceDataList.size() > 0) {
				for (int i = 0; i < referenceDataList.size(); i++) {
					ReferenceDataList tmp = referenceDataList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getId().getAttrCode());
					codeAndDescription.setDescription(tmp.getAttrDesc());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(referenceDataList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

	
	public ListRange getServiceByServiceTypeCode(String serviceTypeCd, int start, int count, String orderBy ) throws HCISException{
		ListRange listRange = new ListRange();
		try {
			List<Service> serviceList = serviceDAO.getActiveServiceByServiceTypeCode(serviceTypeCd);
			Object[] serviceSummaryBMObj = new Object[serviceList.size()];
			if (serviceList != null && serviceList.size() > 0) {
				for (int i = 0; i < serviceList.size(); i++) {
					Service tmp = serviceList.get(i);
					ServiceSummaryBM serviceSummaryBM = new ServiceSummaryBM();
					serviceSummaryBM.setServiceCode(tmp.getServiceCode());
					serviceSummaryBM.setServiceName(tmp.getServiceName());
					serviceSummaryBM.setServiceCharge(tmp.getServiceCharge());
					serviceSummaryBMObj[i] = serviceSummaryBM;
				}

			}
			listRange.setData(serviceSummaryBMObj);
			listRange.setTotalSize(serviceList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

	
	public ListRange getEntities( String entityType, int start,
			int count, String orderBy)throws HCISException{
		try{
			ListRange listRange = new ListRange();
			List<Entity> entityList = entityDAO.findByProperty("type", entityType);
			Object[] codeDescObj = new Object[entityList.size()];
			if (entityList != null && entityList.size() > 0) {
				for (int i = 0; i < entityList.size(); i++) {
					Entity entity = entityList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(entity.getEntityId().toString());
					codeAndDescription.setDescription(entity.getName());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(entityList.size());
			
			return listRange;
		}
		catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException();
		}
	}
	
	
	public DocPatientVital getPatientVital(Integer patientVitalId) throws HCISException {
		try {
			DocPatientVital patientVital = patientVitalDAO.findById( patientVitalId );
			
			if ( patientVital == null ) {
				throw new Exception( " Patient with PATIENT_ID = " + patientVitalId + " does not exist. " );
			}
			
			return patientVital;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.PATIENT_VITAL_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	public DocVitalField getVitalField(String vitalCode) throws HCISException {
		try {
			DocVitalField vitalField = vitalFieldDAO.findById( vitalCode );
			
			if ( vitalField == null ) {
				throw new Exception( " Patient with VITAL_CODE = " + vitalCode + " does not exist. " );
			}
			
			return vitalField;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.VITAL_FIELD_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	
	public DocCheckList getCheckList(Integer checkListId) throws HCISException {
		try {
			DocCheckList docCheckList = checkListDAO.findById( checkListId );
			
			if ( docCheckList == null ) {
				throw new Exception( "Check list with ID  = " + checkListId + " does not exist. " );
			}
			
			return docCheckList;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.VITAL_FIELD_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	public DocCheckListDetail getCheckListDetail(Long checkListDetailId) throws HCISException {
		try {
			DocCheckListDetail docCheckListDtail = checkListDetailDAO.findById( checkListDetailId );
			
			if ( docCheckListDtail == null ) {
				throw new Exception( "Check list detail with ID  = " + docCheckListDtail + " does not exist. " );
			}
			
			return docCheckListDtail;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.VITAL_FIELD_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	public DocCheckListInstance getCheckListInstance(Long checkListInstanceId) throws HCISException {
		try {
			DocCheckListInstance docCheckListInstance = checkListInstanceDAO.findById( checkListInstanceId );
			
			if ( docCheckListInstance == null ) {
				throw new Exception( "Check list Instance with ID  = " + docCheckListInstance + " does not exist. " );
			}
			
			return docCheckListInstance;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.VITAL_FIELD_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	
	public ListRange getRoomsForDoctor(Integer doctorId , int start, int count, String orderBy) throws HCISException 
	{
		 ListRange listRange = new ListRange();
		 try {
			 DoctorEspecialty doctorEspeciality = null;
			 	if( doctorId != null ){
			 		doctorEspeciality = (DoctorEspecialty)doctorEspecialtyDAO.findByProperty("id.doctorId", doctorId).get(0);
			 	}
			    List<Room> roomList =(List<Room>)roomDAO.findByProperty( "active", new Short("1") );
			    Object[] codeDescObj = new Object[roomList.size()];
			    if(roomList!=null && roomList.size()>0) {
			    	for(int i =0; i<roomList.size();i++) {
			    		Room  room = roomList.get(i);
				    	CodeAndDescription codeAndDescription = new CodeAndDescription();
				    	codeAndDescription.setCode(room.getRoomCode());
				    	codeAndDescription.setDescription(room.getDescription());
				    	if( doctorEspeciality != null){
					    	if( doctorEspeciality.getRoom() != null ){
					    		if( room.getRoomCode().equals(doctorEspeciality.getRoom().getRoomCode())){
					    			codeAndDescription.setIsDefault("Y");
					    		}
					    	}
				    	}
				    	codeDescObj[i]=codeAndDescription;
			    	}
		    }
			    listRange.setData(codeDescObj);
				listRange.setTotalSize(roomList.size());
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return listRange;
	
	}

	
	
	public void setServiceGroupDAO(ServiceGroupDAO serviceGroupDAO) {
		this.serviceGroupDAO = serviceGroupDAO;
	}

	public void setServiceStatusDAO(ServiceStatusDAO serviceStatusDAO) {
		this.serviceStatusDAO = serviceStatusDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public void setMedicineTypeDAO(MedicineTypeDAO medicineTypeDAO) {
		this.medicineTypeDAO = medicineTypeDAO;
	}

	public void setChangeReasonDAO(ChangeReasonDAO changeReasonDAO) {
		this.changeReasonDAO = changeReasonDAO;
	}

	public void setBrandDAO(BrandDAO brandDAO) {
		this.brandDAO = brandDAO;
	}

	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}
	
	public void setAppointmentsDAO(AppointmentsDAO appointmentsDAO) {
		this.appointmentsDAO = appointmentsDAO;
	}

	public void setAppointmentStatusDAO(AppointmentStatusDAO appointmentStatusDAO) {
		this.appointmentStatusDAO = appointmentStatusDAO;
	}

	public void setBookingTypeDAO(BookingTypeDAO bookingTypeDAO) {
		this.bookingTypeDAO = bookingTypeDAO;
	}

	public void setDoctorDAO(DoctorDAOExtn doctorDAO) {
		this.doctorDAO = doctorDAO;
	}


	public void setRosterDAO(RosterDAO rosterDAO) {
		this.rosterDAO = rosterDAO;
	}

	public void setRoomDAO(RoomDAO roomDAO) {
		this.roomDAO = roomDAO;
	}
	public void setEspecialtyDAO(EspecialtyDAO especialtyDAO) {
		this.especialtyDAO = especialtyDAO;
	}

	public void setAllergiesDAO(AllergiesDAO allergiesDAO) {
		this.allergiesDAO = allergiesDAO;
	}

	public void setBloodGroupDAO(BloodGroupDAO bloodGroupDAO) {
		this.bloodGroupDAO = bloodGroupDAO;
	}

	public void setIdProofsDAO(IdProofsDAO idProofsDAO) {
		this.idProofsDAO = idProofsDAO;
	}

	public void setMaritalDAO(MaritalDAO maritalDAO) {
		this.maritalDAO = maritalDAO;
	}

	public void setMotherTongueDAO(MotherTongueDAO motherTongueDAO) {
		this.motherTongueDAO = motherTongueDAO;
	}

	public void setNationalityDAO(NationalityDAO nationalityDAO) {
		this.nationalityDAO = nationalityDAO;
	}

	public void setPatientCategoryDAO(PatientCategoryDAO patientCategoryDAO) {
		this.patientCategoryDAO = patientCategoryDAO;
	}

	public void setPatientRatingDAO(PatientRatingDAO patientRatingDAO) {
		this.patientRatingDAO = patientRatingDAO;
	}

	public void setReferralDAO(ReferralDAOExtn referralDAO) {
		this.referralDAO = referralDAO;
	}

	public void setRegistrationStatusDAO(RegistrationStatusDAO registrationStatusDAO) {
		this.registrationStatusDAO = registrationStatusDAO;
	}

	public void setRegistrationTypeDAO(RegistrationTypeDAO registrationTypeDAO) {
		this.registrationTypeDAO = registrationTypeDAO;
	}

	public void setRelationDAO(RelationDAO relationDAO) {
		this.relationDAO = relationDAO;
	}

	public void setReligionDAO(ReligionDAO religionDAO) {
		this.religionDAO = religionDAO;
	}

	public void setSaluationDAO(SaluationDAO saluationDAO) {
		this.saluationDAO = saluationDAO;
	}


	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public void setStateDAO(StateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	
	public void setGenderDAO(GenderDAO genderDAO) {
		this.genderDAO = genderDAO;
	}

	public void setAppEntitiesDAO(AppEntitiesDAO appEntitiesDAO) {
		this.appEntitiesDAO = appEntitiesDAO;
	}

	/**
	 * @param medicinesDAO the medicinesDAO to set
	 */
	public void setMedicinesDAO(MedicinesDAO medicinesDAO) {
		this.medicinesDAO = medicinesDAO;
	}

	public void setServicePackageDAO(ServicePackageDAO servicePackageDAO) {
		this.servicePackageDAO = servicePackageDAO;
	}

	public void setServicePackageStatusDAO(
			ServicePackageStatusDAO servicePackageStatusDAO) {
		this.servicePackageStatusDAO = servicePackageStatusDAO;
	}

	public void setServiceDAO(ServiceDAOExtn serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public void setImmunizationDAO(ImmunizationDAO immunizationDAO) {
		this.immunizationDAO = immunizationDAO;
	}

	
	public void setDiseaseDAO(DiseaseDAO diseaseDAO) {
		this.diseaseDAO = diseaseDAO;
	}

	public DoctorEspecialtyDAO getDoctorEspecialtyDAO() {
		return doctorEspecialtyDAO;
	}

	public void setDoctorEspecialtyDAO(DoctorEspecialtyDAO doctorEspecialtyDAO) {
		this.doctorEspecialtyDAO = doctorEspecialtyDAO;
	}

	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public static void setDEPRTMENT_CODE(String deprtment_code) {
		DEPRTMENT_CODE = deprtment_code;
	}

	public void setDeptEspecialityAssocDAO(
			DeptEspecialityAssocDAOExtn deptEspecialityAssocDAO) {
		this.deptEspecialityAssocDAO = deptEspecialityAssocDAO;
	}

	public void setVaccinationScheduleDAO(
			VaccinationScheduleDAO vaccinationScheduleDAO) {
		this.vaccinationScheduleDAO = vaccinationScheduleDAO;
	}

	public void setVaccinationDAO(VaccinationDAO vaccinationDAO) {
		this.vaccinationDAO = vaccinationDAO;
	}

	public void setPatientDAO(PatientDAOExtn patientDAO) {
		this.patientDAO = patientDAO;
	}

	public void setEagleIntegration(EagleIntegration eagleIntegration) {
		this.eagleIntegration = eagleIntegration;
	}

	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}

	public void setPatientVitalDAO(DocPatientVitalDAO patientVitalDAO) {
		this.patientVitalDAO = patientVitalDAO;
	}

	public void setVitalFieldDAO(DocVitalFieldDAO vitalFieldDAO) {
		this.vitalFieldDAO = vitalFieldDAO;
	}

	public void setCheckListDAO(DocCheckListDAO checkListDAO) {
		this.checkListDAO = checkListDAO;
	}

	public void setCheckListDetailDAO(DocCheckListDetailDAO checkListDetailDAO) {
		this.checkListDetailDAO = checkListDetailDAO;
	}

	public void setCheckListInstanceDAO(DocCheckListInstanceDAO checkListInstanceDAO) {
		this.checkListInstanceDAO = checkListInstanceDAO;
	}

}
