package com.wtc.hcis.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Medicines entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Medicines
 * @author MyEclipse Persistence Tools
 */

public class MedicinesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MedicinesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String MEDICINE_NAME = "medicineName";
	public static final String STRENGTH = "strength";
	public static final String MAXIMUM_DOSAGE = "maximumDosage";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(Medicines transientInstance) {
		log.debug("saving Medicines instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Medicines persistentInstance) {
		log.debug("deleting Medicines instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Medicines findById(java.lang.String id) {
		log.debug("getting Medicines instance with id: " + id);
		try {
			Medicines instance = (Medicines) getHibernateTemplate().get(
					"com.wtc.hcis.da.Medicines", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Medicines instance) {
		log.debug("finding Medicines instance by example");
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
		log.debug("finding Medicines instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Medicines as model where model."
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

	public List findByMedicineName(Object medicineName) {
		return findByProperty(MEDICINE_NAME, medicineName);
	}

	public List findByStrength(Object strength) {
		return findByProperty(STRENGTH, strength);
	}

	public List findByMaximumDosage(Object maximumDosage) {
		return findByProperty(MAXIMUM_DOSAGE, maximumDosage);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all Medicines instances");
		try {
			String queryString = "from Medicines";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Medicines merge(Medicines detachedInstance) {
		log.debug("merging Medicines instance");
		try {
			Medicines result = (Medicines) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Medicines instance) {
		log.debug("attaching dirty Medicines instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Medicines instance) {
		log.debug("attaching clean Medicines instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MedicinesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MedicinesDAO) ctx.getBean("MedicinesDAO");
	}
}