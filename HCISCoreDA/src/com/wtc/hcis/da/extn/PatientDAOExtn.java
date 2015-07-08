package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.PatientDAO;

public class PatientDAOExtn extends PatientDAO {

	public static final String REGISTRATION_TYPE_DIR = "DIRECTSERVICEREGISTRATION";
	public PatientDAOExtn() {
		super();
	}

	public List getpatientsSearched(Integer patientId,
			String patientName, String phoneNumber, String genderCode,
			String maritalStatusCode, String fatherHusbandName,
			String registrationStatus, Date registrationFromDate,
			Date registrationToDate, Date fromAge, Date toAge,
			Date patientLastVisitedFromDate, Date patientLastVisitedToDate) {

		StringBuffer searchQuery = new StringBuffer();

		String joinQuery = " from Patient as patient left outer join patient.appointmentses appointments,ContactDetails contactDetails  where "
				+ "contactDetails.contactCode  = patient.currentContactDetailId ";

		searchQuery.append(joinQuery);

		if (patientId != null) {
			searchQuery.append(" and patient.patientId = :patientId");
		}
		if (patientName != null) {
			searchQuery.append(" and lower(patient.fullName) like lower(:patientName)");
		}
		if (phoneNumber != null) {
			searchQuery.append(" and contactDetails.contactNumber = :phoneNumber");
		}
		if (genderCode != null) {
			searchQuery.append(" and patient.gender.genderCode = :genderCode");
		}
		if (maritalStatusCode != null) {
			searchQuery.append(" and patient.marital.maritalCode = :maritalStatusCode");
		}
		if (fatherHusbandName != null) {
			searchQuery.append(" and lower(patient.fatherHusband) like lower(:fatherHusbandName)");
		}
		if (registrationStatus != null) {
			searchQuery.append(" and patient.registrationStatus.registrationStatusCode = :registrationStatus");
		}
		if (registrationFromDate != null) {
			searchQuery.append(" and patient.registrationDate >= :registrationFromDate");
		}
		if (registrationToDate != null) {
			searchQuery.append(" and patient.registrationDate <= :registrationToDate");
		}
		if (fromAge != null) {
			searchQuery.append(" and patient.dateOfBirth >= :fromAge");
		}
		if (toAge != null) {
			searchQuery.append(" and patient.dateOfBirth <= :toAge");
		}
		if (patientLastVisitedFromDate != null) {
			String tmpStr = " and appointments.capturedDtm >= :patientLastVisitedFromDate"
					+ " and appointments.appointmentDate = ( select MAX(appointments.appointmentDate)"
					+ " from Appointments appointments"
					+ " group by appointments.patient.patientId "
					+ " having appointments.patient.patientId = patient.patientId )";
			searchQuery.append(tmpStr);
		}
		if (patientLastVisitedToDate != null) {
			String tmpStr = " and appointments.capturedDtm <= :patientLastVisitedToDate"
					+ " and appointments.appointmentDate = ( select MAX(appointments.appointmentDate)"
					+ " from Appointments appointments"
					+ " group by appointments.patient.patientId "
					+ " having appointments.patient.patientId = patient.patientId )";
			searchQuery.append(tmpStr);
		}

		searchQuery.append(" order by appointments.capturedDtm desc ");

		Query patientsSearched = getSession().createQuery(
				searchQuery.toString());

		if (patientId != null) {
			patientsSearched.setInteger("patientId", patientId);
		}
		if (patientName != null) {
			patientsSearched.setString("patientName", "%" + patientName + "%");
		}
		if (phoneNumber != null) {
			patientsSearched.setString("phoneNumber", phoneNumber);
		}
		if (genderCode != null) {
			patientsSearched.setString("genderCode", genderCode);
		}
		if (maritalStatusCode != null) {
			patientsSearched.setString("maritalStatusCode", maritalStatusCode);
		}
		if (fatherHusbandName != null) {
			patientsSearched.setString("fatherHusbandName", "%" + fatherHusbandName + "%");
		}
		if (registrationStatus != null) {
			patientsSearched.setString("registrationStatus", registrationStatus);
		}
		if (registrationFromDate != null) {
			patientsSearched.setDate("registrationFromDate", registrationFromDate);
		}
		if (registrationToDate != null) {
			patientsSearched.setDate("registrationToDate", registrationToDate);
		}
		if (fromAge != null) {
			patientsSearched.setDate("fromAge", fromAge);
		}
		if (toAge != null) {
			patientsSearched.setDate("toAge", toAge);
		}
		if (patientLastVisitedFromDate != null) {
			patientsSearched.setDate("patientLastVisitedFromDate", patientLastVisitedFromDate);
		}
		if (patientLastVisitedToDate != null) {
			patientsSearched.setDate("patientLastVisitedToDate", patientLastVisitedToDate);
		}
		List resultList = patientsSearched.list();
		
		List newList = new ArrayList();
		List patientIdList = new ArrayList();

		Iterator<Object> iterator = resultList.iterator();
		while (iterator.hasNext()) {
			Object[] objArray = (Object[]) iterator.next();
			Patient patient = (Patient) objArray[0];
			if (newList.size() == 0) {
				newList.add(objArray);
				patientIdList.add(patient.getPatientId());
			} else {
				if (!patientIdList.contains(patient.getPatientId())) {
					newList.add(objArray);
					patientIdList.add(patient.getPatientId());
				}
			}
		}
		return newList;
	}
	
	public List<Patient> getRegisteredPatients(){
		DetachedCriteria criteria = DetachedCriteria.forClass( Patient.class );
		
		criteria.add( Restrictions.ne("registrationType.registrationTypeCode",
									  PatientDAOExtn.REGISTRATION_TYPE_DIR));
		List<Patient> patients = getHibernateTemplate().findByCriteria(criteria);
		
		return patients;
	}
	
	public List<Integer> getPatientIdList( String patientName ){
		try{
			if( patientName == null){
				patientName = "";
			}
			String hql = "select patientId from Patient where fullName like'%"+ patientName+"%'";
			return getSession().createQuery(hql).list();
		}
		catch (RuntimeException e) {
			throw e;

		}
	}
}
