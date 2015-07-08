/**
 * 
 */
package in.wtc.hcis.bo.common;

import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.exceptions.HCISException;

import com.wtc.hcis.da.AppointmentStatus;
import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.BookingType;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.DocCheckListDetail;
import com.wtc.hcis.da.DocCheckListInstance;
import com.wtc.hcis.da.DocPatientVital;
import com.wtc.hcis.da.DocVitalField;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.Roster;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.StateId;

/**
 * @author Alok Ranjan
 *
 */
public interface DataModelManager {
	AppointmentStatus getAppointmentStatus( String statusCode ) throws HCISException;
	
	Patient getPatient( Integer patientId ) throws HCISException;
	
	Doctor getDoctor( Integer doctorId ) throws HCISException;
	
	BookingType getBookingType( String bookingTypeCd ) throws HCISException;
	
	Roster getRoster( Integer rosterCode ) throws HCISException;
	
	Appointments getAppointment( Integer appointmentNumber ) throws HCISException;
	
	Room getRoom( String roomCode ) throws HCISException;
	
	ListRange getDisease(int start, int count, String orderBy)throws HCISException;
	
	Department getDepartment( String departmentCode ) throws HCISException;
	
	Service  getServiceByCode( String serviceCode ) throws HCISException;
	
	Country  getCountrybyCode( String countryCode ) throws HCISException;
	
	State  getStateByCode( StateId stateId ) throws HCISException;

	ListRange getAllergies(int start, int count, String orderBy )throws HCISException;
	
	ListRange getImmunizations(int start, int count, String orderBy )throws HCISException;
	
	ListRange getGender(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSalutation(int start, int count, String orderBy )throws HCISException;
	
	ListRange getReligion(int start, int count, String orderBy )throws HCISException;
	
	ListRange getNationality(int start, int count, String orderBy )throws HCISException;
	
	ListRange getBloodGroup(int start, int count, String orderBy )throws HCISException;
	
	ListRange getMaritalStatus(int start, int count, String orderBy )throws HCISException;
	
	ListRange getIdProof(int start, int count, String orderBy )throws HCISException;
	
	ListRange getMotherTongue(int start, int count, String orderBy )throws HCISException;
	
	ListRange getCountry(int start, int count, String orderBy )throws HCISException;
	
	ListRange getState(int start, int count, String orderBy )throws HCISException;
	
	ListRange getDepartments(int start, int count, String orderBy )throws HCISException;
	
	ListRange getEspectiality(int start, int count, String orderBy )throws HCISException;
	
	ListRange getRoomNo(int start, int count, String orderBy )throws HCISException;
	
	ListRange getPatientRating(int start, int count, String orderBy )throws HCISException;
	
	ListRange getPatientCategory(int start, int count, String orderBy )throws HCISException;
	
	ListRange getRegistrationType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getRegistrationStatus(int start, int count, String orderBy )throws HCISException;
	
	ListRange getFitnessActivity(int start, int count, String orderBy )throws HCISException;
	
	ListRange getRelationship(int start, int count, String orderBy )throws HCISException;
	
	ListRange getReferral( String referralType, int start, int count, String orderBy )throws HCISException;
	
	ListRange getService(int start, int count, String orderBy )throws HCISException;
	
	ListRange getServicesOfGroup(String serviceGroupCode,int start, int count, String orderBy )throws HCISException;
	
	ListRange getServiceGroupList(int start, int count, String orderBy) throws HCISException;
	
	ListRange getServicePackage(int start, int count, String orderBy) throws HCISException;
	
	ListRange getServiceStatusList(int start, int count, String orderBy)throws HCISException;

	ListRange getServicePackageStatus(int start, int count, String orderBy) throws HCISException;
	
	ListRange getProfitCentersList(int start, int count, String orderBy)throws HCISException;
	
	ListRange getServiceType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getDoctors(int start, int count, String orderBy )throws HCISException;
	
	ListRange getTitle(int start, int count, String orderBy )throws HCISException;
	
	ListRange getPeriod(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSponserType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSponserName(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSponserStatus(int start, int count, String orderBy )throws HCISException;
	
	ListRange getInsuranceCompany(int start, int count, String orderBy )throws HCISException;
	
	ListRange getPatientLstVisited(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSessions(int start, int count, String orderBy )throws HCISException;
	
	ListRange getAppointmentWithStatus(int start, int count, String orderBy )throws HCISException;
	
	ListRange getBookingTypes(int start, int count, String orderBy )throws HCISException;
	
	ListRange getForms(int start, int count, String orderBy )throws HCISException;
	
	ListRange getCancellationReason(int start, int count, String orderBy )throws HCISException;
	
	ListRange getSpecialityType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getDepartmentType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getBrand(int start, int count, String orderBy )throws HCISException;
	
	ListRange getStateWithCountry(String countryCode, int start, int count, String orderBy )throws HCISException;
	
	ListRange getMedicineType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getStatus(int start, int count, String orderBy )throws HCISException;
	
	ListRange getIdsForEntity( String forEntity,int start, int count, String orderBy )throws HCISException;
	
	ListRange getEntityType(int start, int count, String orderBy )throws HCISException;
	
	ListRange getMonths(int start, int count, String orderBy) throws HCISException;
	
	ListRange getYears(int start, int count, String orderBy) throws HCISException;
	
	ListRange getWeeks(String month, String year,int start, int count, String orderBy) throws HCISException;
	
	ListRange getWeekDays(int start, int count, String orderBy)throws HCISException;
	
	ListRange getMedicines (int start, int count, String orderBy)throws HCISException;
	
	public ListRange getPatients (int start, int count, String orderBy)throws HCISException;
	
	public ServicePackage getServicePackageByCode (String servicePackageCode) throws HCISException;
	
	ListRange getDiscountTypesList ( int start, int count, String orderBy )throws HCISException;
	
	ListRange getChargeOverrideTypesList ( int start, int count, String orderBy )throws HCISException;
	
	ListRange getReferenceTypeList ( int start, int count, String orderBy ) throws  HCISException;
	
	ListRange getDoctorsOfDepartment( String departmentCode, int start, int count, String orderBy) throws HCISException;
	
	ListRange getReferenceDataList( String context, int start, int count, String orderBy ) throws HCISException;
	
	ReferenceDataList getReferenceData(String context, String attrCode)throws HCISException;
	
	ListRange getReportNameDataList(int start, int count, String orderBy ) throws HCISException;
	
	ListRange getVaccinationType( int start, int count, String orderBy ) throws HCISException;
	
	ListRange getVaccinations( int start, int count, String orderBy ) throws HCISException;
	
	ListRange getPeriodIndictors( int start, int count, String orderBy ) throws HCISException;
	
	ListRange getHeightIndicators( int start, int count, String orderBy ) throws HCISException;
	
	ListRange getWeightIndicators( int start, int count, String orderBy ) throws HCISException;
	
	ListRange getAppointmentType( int start, int count, String orderBy ) throws HCISException;

	ListRange getSpeacialityForDepartmant( String deprtmentCode, int start, int count, String orderBy) throws HCISException;
	
	ListRange getDoctorsOfSpeaciality( String departmentCode, int start, int count, String orderBy) throws HCISException;
	
	ListRange getActiveVaccinationScheduleFlag( int start, int count, String orderBy) throws HCISException;
	
	ListRange getVaccinationSchedule( int start, int count, String orderBy) throws HCISException;
	
	ListRange getActiveVaccinationSchedule( int start, int count, String orderBy) throws HCISException;
	
	ListRange getActiveStatus( int start, int count, String orderBy) throws HCISException ;
	
	ListRange getCreditCardType(int start, int count, String orderBy);
	
	ListRange getPaymentModes(int start, int count, String orderBy);
	
	ListRange getTransactionTypes(int start, int count, String orderBy);
	
	ListRange getRosterEntities( int start, int count, String orderBy) throws HCISException ;

	ListRange getServiceTypes(int start, int count, String orderBy )throws HCISException;
	
	ListRange getServiceByServiceTypeCode(String serviceTypeCd, int start, int count, String orderBy );
	ListRange getEntities( String entityType, int start,
			int count, String orderBy)throws HCISException ;
	
	DocPatientVital getPatientVital(Integer patientVitalId) throws HCISException;
	
	DocVitalField getVitalField(String vitalCode) throws HCISException;
	
	DocCheckList getCheckList(Integer checkListId) throws HCISException;
	
	DocCheckListDetail getCheckListDetail(Long checkListDetailId) throws HCISException;
	
	DocCheckListInstance getCheckListInstance(Long checkListInstanceId) throws HCISException;
	
	ListRange getRoomsForDoctor(Integer doctorId , int start, int count, String orderBy) throws HCISException;

}


