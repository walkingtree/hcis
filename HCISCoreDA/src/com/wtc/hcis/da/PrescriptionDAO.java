package com.wtc.hcis.da;

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
 * Prescription entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Prescription
 * @author MyEclipse Persistence Tools
 */

public class PrescriptionDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PrescriptionDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SRC_REF_NUMBER = "srcRefNumber";
	public static final String PRESCRIPTION_TEXT = "prescriptionText";

	protected void initDao() {
		// do nothing
	}

	public void save(Prescription transientInstance) {
		log.debug("saving Prescription instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Prescription persistentInstance) {
		log.debug("deleting Prescription instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Prescription findById(java.lang.Integer id) {
		log.debug("getting Prescription instance with id: " + id);
		try {
			Prescription instance = (Prescription) getHibernateTemplate().get(
					"com.wtc.hcis.da.Prescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Prescription instance) {
		log.debug("finding Prescription instance by example");
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
		log.debug("finding Prescription instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Prescription as model where model."
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

	public List findBySrcRefNumber(Object srcRefNumber) {
		return findByProperty(SRC_REF_NUMBER, srcRefNumber);
	}

	public List findByPrescriptionText(Object prescriptionText) {
		return findByProperty(PRESCRIPTION_TEXT, prescriptionText);
	}

	public List findAll() {
		log.debug("finding all Prescription instances");
		try {
			String queryString = "from Prescription";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Prescription merge(Prescription detachedInstance) {
		log.debug("merging Prescription instance");
		try {
			Prescription result = (Prescription) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Prescription instance) {
		log.debug("attaching dirty Prescription instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Prescription instance) {
		log.debug("attaching clean Prescription instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PrescriptionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PrescriptionDAO) ctx.getBean("PrescriptionDAO");
	}
}