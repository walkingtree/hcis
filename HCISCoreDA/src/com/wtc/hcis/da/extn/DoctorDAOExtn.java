package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.DoctorDAO;

public class DoctorDAOExtn extends DoctorDAO {
	
	private static final Log log = LogFactory.getLog(DoctorDAOExtn.class);
	
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	
	public static final String DOCTOR_ACTIVE_YES = "Y";

	private Map<String,String> UIDBFieldMapForOrder = new HashMap<String, String>();
	
	public List<Doctor> findDoctors(Integer doctorId,
			String department, String speciality, String name,
			String roomNo,
			Double consultationChargeFrom, Double consultationChargeTo,
			Date joiningDateFrom, Date joiningDateTo, int start, int limit, String orderByClause, String sortDir) {
		try {
			List<Doctor> doctorList = null;
			DetachedCriteria doctorCriteria = DetachedCriteria.forClass(Doctor.class);
			doctorCriteria.createAlias("doctorDetails", "doctorDetails");
			doctorCriteria.createAlias("doctorEspecialties", "de");
			if (doctorId != null) {
				doctorCriteria.add(Restrictions.eq("doctorDetails.doctorId", doctorId))
			            .add(Restrictions.eq("de.id.doctorId", doctorId));
			}
			if (name != null && !name.equals("")) {
				doctorCriteria.add(Restrictions.ilike(DoctorDAO.FULL_NAME, "%" + name + "%"));
			}

			if (joiningDateFrom != null) {
				if (joiningDateTo != null) {
					doctorCriteria.add(Restrictions.between("doctorDetails.joiningDt", joiningDateFrom, joiningDateTo));
				} else {
					doctorCriteria.add(Restrictions.ge("doctorDetails.joiningDt", joiningDateFrom));
				}
			} else {
				if (joiningDateTo != null)
					doctorCriteria.add(Restrictions.le("doctorDetails.joiningDt", joiningDateTo));
			}
			
			if (roomNo != null && !roomNo.equals("")) {
				doctorCriteria.add(Restrictions.ilike("de.room.roomCode", "%" + roomNo + "%"));
			}
			
			if(department!=null && !department.equals("")) {
				doctorCriteria.add(Restrictions.ilike("de.department.departmentCode", "%"+ department + "%"));
			}
			
			if(speciality!=null && !speciality.equals("")) {
				doctorCriteria.add(Restrictions.like("de.especialty.especialtyCode", "%"+ speciality + "%"));
			}
			
			if (consultationChargeFrom != null) {
				if (consultationChargeTo != null) {
					doctorCriteria.add(Restrictions.between("de.consultationCharge", consultationChargeFrom, consultationChargeTo));
				}else {
					doctorCriteria.add(Restrictions.ge("de.consultationCharge", consultationChargeFrom));
				}
			}else if (consultationChargeTo != null) {
				if (consultationChargeFrom != null && consultationChargeFrom >= consultationChargeTo) {
					doctorCriteria.add(Restrictions.between("de.consultationCharge", consultationChargeFrom, consultationChargeTo));
				}else {
					doctorCriteria.add(Restrictions.le("de.consultationCharge", consultationChargeTo));
				}
			}
			
			if (orderByClause != null && !orderByClause.isEmpty()) {
				if (sortDir.equals(SORT_DIRECTION_ASC))
					doctorCriteria.addOrder(Order.asc(UIDBFieldMapForOrder.get(orderByClause)));
				else
					doctorCriteria.addOrder(Order.desc(UIDBFieldMapForOrder.get(orderByClause)));
			}
				
			doctorList = getHibernateTemplate().findByCriteria(doctorCriteria);
			
			return doctorList;
			
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve doctors "  + ex.getMessage() );
			throw ex;
		}
	}

	public List<Doctor> getActiveDoctorOfDepartment( String departmentCode ){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Doctor.class);
		
		try {
			criteria.createAlias("doctorEspecialties", "doctorEspecialties");
			
			criteria.add(Restrictions.eq("doctorEspecialties.department.departmentCode", departmentCode))
					.add(Restrictions.eq("active", DoctorDAOExtn.DOCTOR_ACTIVE_YES));
			
			return getHibernateTemplate().findByCriteria(criteria);
		} catch (RuntimeException ex) {
			log.error("Failed to retrieve doctors "  + ex.getMessage() );
			throw ex;
		}
	}
	public void setUIDBFieldMapForOrder(Map<String, String> fieldMapForOrder) {
		UIDBFieldMapForOrder = fieldMapForOrder;
	}
	
	public List<Doctor> getActiveDoctorOfSpeaciality( String speacialityCode ){
			
			DetachedCriteria criteria = DetachedCriteria.forClass(Doctor.class);
			
			try {
				criteria.createAlias("doctorEspecialties", "doctorEspecialties");
				
				criteria.add(Restrictions.eq("doctorEspecialties.especialty.especialtyCode", speacialityCode))
						.add(Restrictions.eq("active", DoctorDAOExtn.DOCTOR_ACTIVE_YES));
				
				return getHibernateTemplate().findByCriteria(criteria);
			} catch (RuntimeException ex) {
				log.error("Failed to retrieve doctors "  + ex.getMessage() );
				throw ex;
			}
		}

}
