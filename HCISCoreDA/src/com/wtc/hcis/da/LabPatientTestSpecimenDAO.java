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
 * LabPatientTestSpecimen entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabPatientTestSpecimen
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestSpecimenDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabPatientTestSpecimenDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String QUANTITY = "quantity";
	public static final String UNIT = "unit";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabPatientTestSpecimen transientInstance) {
		log.debug("saving LabPatientTestSpecimen instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabPatientTestSpecimen persistentInstance) {
		log.debug("deleting LabPatientTestSpecimen instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabPatientTestSpecimen findById(
			com.wtc.hcis.da.LabPatientTestSpecimenId id) {
		log.debug("getting LabPatientTestSpecimen instance with id: " + id);
		try {
			LabPatientTestSpecimen instance = (LabPatientTestSpecimen) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabPatientTestSpecimen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabPatientTestSpecimen instance) {
		log.debug("finding LabPatientTestSpecimen instance by example");
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
		log.debug("finding LabPatientTestSpecimen instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabPatientTestSpecimen as model where model."
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

	public List findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabPatientTestSpecimen instances");
		try {
			String queryString = "from LabPatientTestSpecimen";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabPatientTestSpecimen merge(LabPatientTestSpecimen detachedInstance) {
		log.debug("merging LabPatientTestSpecimen instance");
		try {
			LabPatientTestSpecimen result = (LabPatientTestSpecimen) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabPatientTestSpecimen instance) {
		log.debug("attaching dirty LabPatientTestSpecimen instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabPatientTestSpecimen instance) {
		log.debug("attaching clean LabPatientTestSpecimen instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabPatientTestSpecimenDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabPatientTestSpecimenDAO) ctx
				.getBean("LabPatientTestSpecimenDAO");
	}
}