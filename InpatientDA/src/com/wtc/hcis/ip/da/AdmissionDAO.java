package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Admission entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.Admission
 * @author MyEclipse Persistence Tools
 */

public class AdmissionDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AdmissionDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ADMISSION_NBR = "admissionNbr";
	public static final String ADMISSION_REQUESTED_BY = "admissionRequestedBy";
	public static final String DOCTOR_ID = "doctorId";
	public static final String PATIENT_ID = "patientId";
	public static final String ENTRY_POINT_REFERENCE = "entryPointReference";
	public static final String REASON_FOR_ADMISSION = "reasonForAdmission";
	public static final String AGENDA = "agenda";
	public static final String TREATMENT_ESTIMATION_BY = "treatmentEstimationBy";
	public static final String TREATMENT_ESTIMATED_COST = "treatmentEstimatedCost";
	public static final String TREATMENT_ACTUAL_COST = "treatmentActualCost";
	public static final String DISCHARGE_BY_DOCTOR_ID = "dischargeByDoctorId";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Admission transientInstance) {
		log.debug("saving Admission instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Admission persistentInstance) {
		log.debug("deleting Admission instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Admission findById(java.lang.Integer id) {
		log.debug("getting Admission instance with id: " + id);
		try {
			Admission instance = (Admission) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.Admission", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Admission instance) {
		log.debug("finding Admission instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Admission instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Admission as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	public List findByAdmissionNbr(Object admissionNbr) {
		return findByProperty(ADMISSION_NBR, admissionNbr);
	}

	public List findByAdmissionRequestedBy(Object admissionRequestedBy) {
		return findByProperty(ADMISSION_REQUESTED_BY, admissionRequestedBy);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByEntryPointReference(Object entryPointReference) {
		return findByProperty(ENTRY_POINT_REFERENCE, entryPointReference);
	}

	public List findByReasonForAdmission(Object reasonForAdmission) {
		return findByProperty(REASON_FOR_ADMISSION, reasonForAdmission);
	}

	public List findByAgenda(Object agenda) {
		return findByProperty(AGENDA, agenda);
	}

	public List findByTreatmentEstimationBy(Object treatmentEstimationBy) {
		return findByProperty(TREATMENT_ESTIMATION_BY, treatmentEstimationBy);
	}

	public List findByTreatmentEstimatedCost(Object treatmentEstimatedCost) {
		return findByProperty(TREATMENT_ESTIMATED_COST, treatmentEstimatedCost);
	}

	public List findByTreatmentActualCost(Object treatmentActualCost) {
		return findByProperty(TREATMENT_ACTUAL_COST, treatmentActualCost);
	}

	public List findByDischargeByDoctorId(Object dischargeByDoctorId) {
		return findByProperty(DISCHARGE_BY_DOCTOR_ID, dischargeByDoctorId);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Admission instances");
		try {
			String queryString = "from Admission";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Admission merge(Admission detachedInstance) {
		log.debug("merging Admission instance");
		try {
			Admission result = (Admission) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Admission instance) {
		log.debug("attaching dirty Admission instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Admission instance) {
		log.debug("attaching clean Admission instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AdmissionDAO) ctx.getBean("AdmissionDAO");
	}
}