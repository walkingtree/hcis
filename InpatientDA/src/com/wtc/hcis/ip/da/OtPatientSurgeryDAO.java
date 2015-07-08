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
 * OtPatientSurgery entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtPatientSurgery
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OtPatientSurgeryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String PATIENT_ID = "patientId";
	public static final String DOCTOR_ID = "doctorId";
	public static final String ANESTHETIST_ID = "anesthetistId";
	public static final String STATUS_CODE = "statusCode";
	public static final String COORDINATOR_ID = "coordinatorId";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtPatientSurgery transientInstance) {
		log.debug("saving OtPatientSurgery instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtPatientSurgery persistentInstance) {
		log.debug("deleting OtPatientSurgery instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtPatientSurgery findById(java.lang.Long id) {
		log.debug("getting OtPatientSurgery instance with id: " + id);
		try {
			OtPatientSurgery instance = (OtPatientSurgery) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtPatientSurgery", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtPatientSurgery instance) {
		log.debug("finding OtPatientSurgery instance by example");
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
		log.debug("finding OtPatientSurgery instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtPatientSurgery as model where model."
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

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByAnesthetistId(Object anesthetistId) {
		return findByProperty(ANESTHETIST_ID, anesthetistId);
	}

	public List findByStatusCode(Object statusCode) {
		return findByProperty(STATUS_CODE, statusCode);
	}

	public List findByCoordinatorId(Object coordinatorId) {
		return findByProperty(COORDINATOR_ID, coordinatorId);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtPatientSurgery instances");
		try {
			String queryString = "from OtPatientSurgery";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtPatientSurgery merge(OtPatientSurgery detachedInstance) {
		log.debug("merging OtPatientSurgery instance");
		try {
			OtPatientSurgery result = (OtPatientSurgery) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtPatientSurgery instance) {
		log.debug("attaching dirty OtPatientSurgery instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtPatientSurgery instance) {
		log.debug("attaching clean OtPatientSurgery instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtPatientSurgeryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtPatientSurgeryDAO) ctx.getBean("OtPatientSurgeryDAO");
	}
}