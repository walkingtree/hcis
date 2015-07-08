package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServiceHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ServiceHistory
 * @author MyEclipse Persistence Tools
 */

public class ServiceHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ServiceHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ServiceHistory transientInstance) {
		log.debug("saving ServiceHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServiceHistory persistentInstance) {
		log.debug("deleting ServiceHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServiceHistory findById(com.wtc.hcis.da.ServiceHistoryId id) {
		log.debug("getting ServiceHistory instance with id: " + id);
		try {
			ServiceHistory instance = (ServiceHistory) getHibernateTemplate()
					.get("com.wtc.hcis.da.ServiceHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServiceHistory instance) {
		log.debug("finding ServiceHistory instance by example");
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
		log.debug("finding ServiceHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServiceHistory as model where model."
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
		log.debug("finding all ServiceHistory instances");
		try {
			String queryString = "from ServiceHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServiceHistory merge(ServiceHistory detachedInstance) {
		log.debug("merging ServiceHistory instance");
		try {
			ServiceHistory result = (ServiceHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServiceHistory instance) {
		log.debug("attaching dirty ServiceHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServiceHistory instance) {
		log.debug("attaching clean ServiceHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServiceHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServiceHistoryDAO) ctx.getBean("ServiceHistoryDAO");
	}
}