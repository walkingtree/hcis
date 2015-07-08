package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * LabPatientTestChangeHistory entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.LabPatientTestChangeHistory
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestChangeHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabPatientTestChangeHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ATTRIBUTE_VALUE = "attributeValue";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabPatientTestChangeHistory transientInstance) {
		log.debug("saving LabPatientTestChangeHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabPatientTestChangeHistory persistentInstance) {
		log.debug("deleting LabPatientTestChangeHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabPatientTestChangeHistory findById(
			com.wtc.hcis.da.LabPatientTestChangeHistoryId id) {
		log
				.debug("getting LabPatientTestChangeHistory instance with id: "
						+ id);
		try {
			LabPatientTestChangeHistory instance = (LabPatientTestChangeHistory) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabPatientTestChangeHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabPatientTestChangeHistory instance) {
		log.debug("finding LabPatientTestChangeHistory instance by example");
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
		log
				.debug("finding LabPatientTestChangeHistory instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabPatientTestChangeHistory as model where model."
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

	public List findByAttributeValue(Object attributeValue) {
		return findByProperty(ATTRIBUTE_VALUE, attributeValue);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabPatientTestChangeHistory instances");
		try {
			String queryString = "from LabPatientTestChangeHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabPatientTestChangeHistory merge(
			LabPatientTestChangeHistory detachedInstance) {
		log.debug("merging LabPatientTestChangeHistory instance");
		try {
			LabPatientTestChangeHistory result = (LabPatientTestChangeHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabPatientTestChangeHistory instance) {
		log.debug("attaching dirty LabPatientTestChangeHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabPatientTestChangeHistory instance) {
		log.debug("attaching clean LabPatientTestChangeHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabPatientTestChangeHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabPatientTestChangeHistoryDAO) ctx
				.getBean("LabPatientTestChangeHistoryDAO");
	}
}