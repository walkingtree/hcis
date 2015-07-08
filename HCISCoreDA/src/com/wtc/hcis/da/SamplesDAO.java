package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Samples entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Samples
 * @author MyEclipse Persistence Tools
 */

public class SamplesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SamplesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DOCTOR_ID = "doctorId";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Samples transientInstance) {
		log.debug("saving Samples instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Samples persistentInstance) {
		log.debug("deleting Samples instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Samples findById(java.lang.String id) {
		log.debug("getting Samples instance with id: " + id);
		try {
			Samples instance = (Samples) getHibernateTemplate().get(
					"com.wtc.hcis.da.Samples", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Samples instance) {
		log.debug("finding Samples instance by example");
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
		log.debug("finding Samples instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Samples as model where model."
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

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
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
		log.debug("finding all Samples instances");
		try {
			String queryString = "from Samples";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Samples merge(Samples detachedInstance) {
		log.debug("merging Samples instance");
		try {
			Samples result = (Samples) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Samples instance) {
		log.debug("attaching dirty Samples instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Samples instance) {
		log.debug("attaching clean Samples instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SamplesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SamplesDAO) ctx.getBean("SamplesDAO");
	}
}