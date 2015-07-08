package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.LabRequisitionOrderDAO;

public class LabRequisitionOrderDAOExtn extends LabRequisitionOrderDAO {
	
	public static String PATIENT_NAME = "patient.fullName";
	public static String PATIENT_ID = "patient.patientId";
	public static String DOCTOR_NAME = "doctor.fullName";
	public static String DOCTOR_ID = "doctor.doctorId";
	public static String CREATED_DTM = "createdDtm";
	public static String REFERENCE_TYPE = "assignedServiceses.referenceType";
	public static String SERVICE_CODE = "assignedServiceses.service.serviceCode";
	public static String SERVICE_TYPE_CODE = "services.serviceTypeCd";
	
	
	public List<LabRequisitionOrder> getRequisitionOrders( String patientName, 
			  Integer patientId,
			  String referenceType,
			  String doctorName,
			  Integer doctorId,
			  Date requisitionFromDate,
			  Date requisitionToDate,
			  String testName,
			  String testStatus){
		
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LabRequisitionOrder.class);
		criteria.createAlias("assignedServiceses", "assignedServiceses");

		
		if( patientName != null && !patientName.isEmpty()){
				
			criteria.createAlias("patient", "patient");
			criteria.add(Restrictions.ilike(LabRequisitionOrderDAOExtn.PATIENT_NAME, "%" + patientName +"%" ));
		}
		
		if( patientId != null){
			
			criteria.add(Restrictions.eq(LabRequisitionOrderDAOExtn.PATIENT_ID, patientId));
			
		}
		
		if( referenceType!= null && !referenceType.isEmpty()){
			criteria.add(Restrictions.ilike(LabRequisitionOrderDAOExtn.REFERENCE_TYPE, referenceType));
		}
		
		
		
		if( doctorName != null && !doctorName.isEmpty()){
			criteria.createAlias("doctor", "doctor");
			criteria.add(Restrictions.ilike(LabRequisitionOrderDAOExtn.DOCTOR_NAME, "%" + doctorName + "%"));
		}
		
		if( doctorId!= null ){
			criteria.add(Restrictions.eq(LabRequisitionOrderDAOExtn.DOCTOR_ID, doctorId));
		}
		
		if( requisitionFromDate!= null ){
			criteria.add(Restrictions.ge(LabRequisitionOrderDAOExtn.CREATED_DTM, requisitionFromDate));
		}
		
		if( requisitionToDate!= null){
			criteria.add(Restrictions.le(LabRequisitionOrderDAOExtn.CREATED_DTM, requisitionToDate));
		}
		
		if( testName!= null && !testName.isEmpty()){
//			criteria.createAlias("assignedServiceses", "assignedServiceses");
			criteria.add(Restrictions.ilike(LabRequisitionOrderDAOExtn.SERVICE_CODE, "%" + testName + "%"));
		}
		
		if( testStatus!= null && !testStatus.isEmpty()){
			criteria.add(Restrictions.ilike(LabRequisitionOrderDAOExtn.STATUS_CODE,"%" + testStatus + "%"));
		}
		
		criteria.createAlias("assignedServiceses.service", "services");
		
		criteria.add(Restrictions.ne(LabRequisitionOrderDAOExtn.SERVICE_TYPE_CODE, "ADMINISTRATIVE"));
		
		criteria.setResultTransformer( Criteria.DISTINCT_ROOT_ENTITY );
		
		List<LabRequisitionOrder> labRequisitionOrdersList = getHibernateTemplate().findByCriteria(criteria);
		
		return labRequisitionOrdersList;
	}

}
