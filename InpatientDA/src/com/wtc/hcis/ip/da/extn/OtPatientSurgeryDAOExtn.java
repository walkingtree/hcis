package com.wtc.hcis.ip.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtPatientSurgery;
import com.wtc.hcis.ip.da.OtPatientSurgeryDAO;

public class OtPatientSurgeryDAOExtn extends OtPatientSurgeryDAO {
	
	protected static final String PATIENT_ID = "patientId";
	protected static final String DOCTOR_ID = "doctorId";
	protected static final String OT_CODE = "otDetail.otId";
	protected static final String SURGERY_CODE = "otSurgery.surgeryCode";
	protected static final String SURGERY_DATE = "surgeryDate";
	protected static final String REFERENCE_TYPE = "otBooking.referenceType";
	protected static final String REFERENCE_NBR = "otBooking.referenceNumberr";
	protected static final String BOOKING_NBR = "otBooking.otBookingNbr";

	
	public List<OtPatientSurgery> getPatientSurgeries(List<Integer> patientIdList, Integer doctorId,
			String otCode,String surgeryCode,Date fromDate,
			Date toDate,String referenceType, String referenceNbr,
			String otBookingNbr){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OtPatientSurgery.class);
		criteria.createAlias("otBooking", "otBooking");
		
		if( patientIdList != null && !patientIdList.isEmpty()){
			criteria.add(Restrictions.in(OtPatientSurgeryDAOExtn.PATIENT_ID, patientIdList));
		}

		if( doctorId != null){
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.DOCTOR_ID, doctorId));
		}

		if( otCode != null && !otCode.isEmpty()){
			criteria.createAlias("otDetail", "otDetail");
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.OT_CODE, otCode));
		}

		if( surgeryCode != null && !surgeryCode.isEmpty()){
			criteria.createAlias("otSurgery", "otSurgery");
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.SURGERY_CODE, surgeryCode));
		}

		if( fromDate != null ){
			criteria.add(Restrictions.ge(OtPatientSurgeryDAOExtn.SURGERY_DATE, fromDate));
		}

		if( toDate != null ){
			criteria.add(Restrictions.le(OtPatientSurgeryDAOExtn.SURGERY_DATE, toDate));
		}
		
		if( referenceType != null && !referenceType.isEmpty()){
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.REFERENCE_TYPE, referenceType));
		}
		
		if( referenceNbr != null && !referenceNbr.isEmpty()){
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.REFERENCE_NBR, referenceNbr));
		}

		if( otBookingNbr != null && !otBookingNbr.isEmpty()){
			Long bookingNbr = Long.parseLong(otBookingNbr);
			criteria.add(Restrictions.eq(OtPatientSurgeryDAOExtn.BOOKING_NBR, bookingNbr));
		}


		return getHibernateTemplate().findByCriteria(criteria);
	}

}
