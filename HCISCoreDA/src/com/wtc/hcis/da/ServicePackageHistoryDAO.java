package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ServicePackageHistory entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ServicePackageHistory
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ServicePackageHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ServicePackageHistory transientInstance) {
		log.debug("saving ServicePackageHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServicePackageHistory persistentInstance) {
		log.debug("deleting ServicePackageHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServicePackageHistory findById(
			com.wtc.hcis.da.ServicePackageHistoryId id) {
		log.debug("getting ServicePackageHistory instance with id: " + id);
		try {
			ServicePackageHistory instance = (ServicePackageHistory) getHibernateTemplate()
					.get("com.wtc.hcis.da.ServicePackageHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServicePackageHistory instance) {
		log.debug("finding ServicePackageHistory instance by example");
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
		log.debug("finding ServicePackageHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServicePackageHistory as model where model."
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
		log.debug("finding all ServicePackageHistory instances");
		try {
			String queryString = "from ServicePackageHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServicePackageHistory merge(ServicePackageHistory detachedInstance) {
		log.debug("merging ServicePackageHistory instance");
		try {
			ServicePackageHistory result = (ServicePackageHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServicePackageHistory instance) {
		log.debug("attaching dirty ServicePackageHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServicePackageHistory instance) {
		log.debug("attaching clean ServicePackageHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServicePackageHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServicePackageHistoryDAO) ctx
				.getBean("ServicePackageHistoryDAO");
	}
}