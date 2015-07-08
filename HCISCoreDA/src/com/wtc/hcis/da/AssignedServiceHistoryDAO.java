package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AssignedServiceHistory entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AssignedServiceHistory
 * @author MyEclipse Persistence Tools
 */

public class AssignedServiceHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AssignedServiceHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(AssignedServiceHistory transientInstance) {
		log.debug("saving AssignedServiceHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssignedServiceHistory persistentInstance) {
		log.debug("deleting AssignedServiceHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssignedServiceHistory findById(
			com.wtc.hcis.da.AssignedServiceHistoryId id) {
		log.debug("getting AssignedServiceHistory instance with id: " + id);
		try {
			AssignedServiceHistory instance = (AssignedServiceHistory) getHibernateTemplate()
					.get("com.wtc.hcis.da.AssignedServiceHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssignedServiceHistory instance) {
		log.debug("finding AssignedServiceHistory instance by example");
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
		log.debug("finding AssignedServiceHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssignedServiceHistory as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all AssignedServiceHistory instances");
		try {
			String queryString = "from AssignedServiceHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssignedServiceHistory merge(AssignedServiceHistory detachedInstance) {
		log.debug("merging AssignedServiceHistory instance");
		try {
			AssignedServiceHistory result = (AssignedServiceHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssignedServiceHistory instance) {
		log.debug("attaching dirty AssignedServiceHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssignedServiceHistory instance) {
		log.debug("attaching clean AssignedServiceHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssignedServiceHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssignedServiceHistoryDAO) ctx
				.getBean("AssignedServiceHistoryDAO");
	}
}