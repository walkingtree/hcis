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
 * DoctorOrder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrder
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DoctorOrderDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATION_SEQ_NBR = "creationSeqNbr";
	public static final String DOCTOR_ID = "doctorId";
	public static final String PATIENT_ID = "patientId";
	public static final String ORDER_DESC = "orderDesc";
	public static final String ORDER_DICTATION = "orderDictation";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrder transientInstance) {
		log.debug("saving DoctorOrder instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrder persistentInstance) {
		log.debug("deleting DoctorOrder instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrder findById(java.lang.Integer id) {
		log.debug("getting DoctorOrder instance with id: " + id);
		try {
			DoctorOrder instance = (DoctorOrder) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.DoctorOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrder instance) {
		log.debug("finding DoctorOrder instance by example");
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
		log.debug("finding DoctorOrder instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DoctorOrder as model where model."
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

	public List findByCreationSeqNbr(Object creationSeqNbr) {
		return findByProperty(CREATION_SEQ_NBR, creationSeqNbr);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByOrderDesc(Object orderDesc) {
		return findByProperty(ORDER_DESC, orderDesc);
	}

	public List findByOrderDictation(Object orderDictation) {
		return findByProperty(ORDER_DICTATION, orderDictation);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all DoctorOrder instances");
		try {
			String queryString = "from DoctorOrder";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrder merge(DoctorOrder detachedInstance) {
		log.debug("merging DoctorOrder instance");
		try {
			DoctorOrder result = (DoctorOrder) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrder instance) {
		log.debug("attaching dirty DoctorOrder instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrder instance) {
		log.debug("attaching clean DoctorOrder instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderDAO) ctx.getBean("DoctorOrderDAO");
	}
}