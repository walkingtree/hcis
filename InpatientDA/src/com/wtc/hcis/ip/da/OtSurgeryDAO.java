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
 * OtSurgery entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtSurgery
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OtSurgeryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SURGERY_NAME = "surgeryName";
	public static final String TYPE_CODE = "typeCode";
	public static final String SPECIALTY_CODE = "specialtyCode";
	public static final String DOCTOR_REFRESHMENT_TIME = "doctorRefreshmentTime";
	public static final String TOTAL_TIME_REQUIRED = "totalTimeRequired";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtSurgery transientInstance) {
		log.debug("saving OtSurgery instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtSurgery persistentInstance) {
		log.debug("deleting OtSurgery instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtSurgery findById(java.lang.String id) {
		log.debug("getting OtSurgery instance with id: " + id);
		try {
			OtSurgery instance = (OtSurgery) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.OtSurgery", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtSurgery instance) {
		log.debug("finding OtSurgery instance by example");
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
		log.debug("finding OtSurgery instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OtSurgery as model where model."
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

	public List findBySurgeryName(Object surgeryName) {
		return findByProperty(SURGERY_NAME, surgeryName);
	}

	public List findByTypeCode(Object typeCode) {
		return findByProperty(TYPE_CODE, typeCode);
	}

	public List findBySpecialtyCode(Object specialtyCode) {
		return findByProperty(SPECIALTY_CODE, specialtyCode);
	}

	public List findByDoctorRefreshmentTime(Object doctorRefreshmentTime) {
		return findByProperty(DOCTOR_REFRESHMENT_TIME, doctorRefreshmentTime);
	}

	public List findByTotalTimeRequired(Object totalTimeRequired) {
		return findByProperty(TOTAL_TIME_REQUIRED, totalTimeRequired);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtSurgery instances");
		try {
			String queryString = "from OtSurgery";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtSurgery merge(OtSurgery detachedInstance) {
		log.debug("merging OtSurgery instance");
		try {
			OtSurgery result = (OtSurgery) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtSurgery instance) {
		log.debug("attaching dirty OtSurgery instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtSurgery instance) {
		log.debug("attaching clean OtSurgery instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtSurgeryDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OtSurgeryDAO) ctx.getBean("OtSurgeryDAO");
	}
}