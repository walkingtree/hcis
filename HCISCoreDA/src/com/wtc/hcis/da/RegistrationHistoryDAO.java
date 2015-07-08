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
 * RegistrationHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.RegistrationHistory
 * @author MyEclipse Persistence Tools
 */

public class RegistrationHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(RegistrationHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REGISTRATION_STATUS = "registrationStatus";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(RegistrationHistory transientInstance) {
		log.debug("saving RegistrationHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RegistrationHistory persistentInstance) {
		log.debug("deleting RegistrationHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegistrationHistory findById(com.wtc.hcis.da.RegistrationHistoryId id) {
		log.debug("getting RegistrationHistory instance with id: " + id);
		try {
			RegistrationHistory instance = (RegistrationHistory) getHibernateTemplate()
					.get("com.wtc.hcis.da.RegistrationHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RegistrationHistory instance) {
		log.debug("finding RegistrationHistory instance by example");
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
		log.debug("finding RegistrationHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RegistrationHistory as model where model."
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

	public List findByRegistrationStatus(Object registrationStatus) {
		return findByProperty(REGISTRATION_STATUS, registrationStatus);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all RegistrationHistory instances");
		try {
			String queryString = "from RegistrationHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RegistrationHistory merge(RegistrationHistory detachedInstance) {
		log.debug("merging RegistrationHistory instance");
		try {
			RegistrationHistory result = (RegistrationHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RegistrationHistory instance) {
		log.debug("attaching dirty RegistrationHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegistrationHistory instance) {
		log.debug("attaching clean RegistrationHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RegistrationHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RegistrationHistoryDAO) ctx.getBean("RegistrationHistoryDAO");
	}
}