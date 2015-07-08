/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeOrderDAO;

/**
 * @author Bhavesh
 *
 */
public class DischargeOrderDAOExtn extends DischargeOrderDAO {
	
	public static final String DOCTOR_ID = "doctorOrder.doctorId";
	public static final String PATIENT_ID ="doctorOrder.patientId"; 
	public static final String ADMISSION_NBR = "admissionNbr";
	public static final String DOCTOR_ORDER_STATUS_CD = "doctorOrderStatus.orderStatusCd";
	public static final String DISCHARGE_TYPE_CD = "dischargeType.dischargeTypeCd";
	public static final String DISCHARGE_DT = "actualDischargeTime"; 
	public static final String EXPECTED_DISCHARGE_DT = "expectedDischargeDate";
	
	
	public List<DischargeOrder> getDischarge (  Integer doctorId, 
												Integer patientId,
												Integer admissionNumber, 
												String dischargeStatusCd,
												String dischargeTypeCd, 
												Date dischargeFromDate,
												Date dischargeToDate, 
												Date expectedDischargeFromDate,
												Date expectedDischargeToDate, 
												String dischargeRequestedBy,
												String dischargeApprovedBy ) {
		
		DetachedCriteria dischargeCriteria = DetachedCriteria.forClass(DischargeOrder.class);
		
		if( doctorId != null){
			dischargeCriteria.add(Restrictions.eq(DischargeOrderDAOExtn.DOCTOR_ID , doctorId));
		}
		
		if (patientId != null){
			dischargeCriteria.add(Restrictions.eq(DischargeOrderDAOExtn.PATIENT_ID,patientId));
		}
		
		if ( admissionNumber != null ){
			dischargeCriteria.add(Restrictions.eq(DischargeOrderDAOExtn.ADMISSION_NBR , admissionNumber));
		}
		
		if ( dischargeStatusCd != null){
			dischargeCriteria.add(Restrictions.eq(DischargeOrderDAOExtn.DOCTOR_ORDER_STATUS_CD , dischargeStatusCd));
		}
		
		if ( dischargeTypeCd != null){
			dischargeCriteria.add( Restrictions.eq(DischargeOrderDAOExtn.DISCHARGE_TYPE_CD, dischargeTypeCd));
		}
		
		if ( dischargeFromDate != null  ){
			if( dischargeToDate != null ){
				dischargeCriteria.add(Restrictions.between(DischargeOrderDAOExtn.DISCHARGE_DT, dischargeFromDate, dischargeToDate));
			}
			else{
				dischargeCriteria.add(Restrictions.ge(DischargeOrderDAOExtn.DISCHARGE_DT, dischargeFromDate));
			}
		}else if ( dischargeToDate != null ){
			dischargeCriteria.add(Restrictions.le(DischargeOrderDAOExtn.DISCHARGE_DT, dischargeToDate));
		}
		
		if ( expectedDischargeFromDate != null  ){
			if( expectedDischargeToDate != null ){
				dischargeCriteria.add(Restrictions.between(DischargeOrderDAOExtn.EXPECTED_DISCHARGE_DT, expectedDischargeFromDate, expectedDischargeToDate));
			}
			else{
				dischargeCriteria.add(Restrictions.ge(DischargeOrderDAOExtn.EXPECTED_DISCHARGE_DT, expectedDischargeFromDate));
			}
		}else if ( expectedDischargeToDate != null ){
			dischargeCriteria.add(Restrictions.le(DischargeOrderDAOExtn.EXPECTED_DISCHARGE_DT, expectedDischargeToDate));
		}
		
		if ( dischargeRequestedBy != null ){
			dischargeCriteria.add( Restrictions.eq(DischargeOrderDAOExtn.ORDER_REQUESTED_BY, dischargeRequestedBy));
		}
		
		if ( dischargeApprovedBy != null){
			dischargeCriteria.add( Restrictions.eq(DischargeOrderDAOExtn.APPROVED_BY, dischargeApprovedBy));
		}
		
		
		List<DischargeOrder> dischargeOrderList = getHibernateTemplate().findByCriteria(dischargeCriteria);
		return  dischargeOrderList;
	}

}
