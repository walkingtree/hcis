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
 * AssignedServiceStatus entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AssignedServiceStatus
 * @author MyEclipse Persistence Tools
 */

public class AssignedServiceStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AssignedServiceStatusDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(AssignedServiceStatus transientInstance) {
		log.debug("saving AssignedServiceStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssignedServiceStatus persistentInstance) {
		log.debug("deleting AssignedServiceStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssignedServiceStatus findById(java.lang.String id) {
		log.debug("getting AssignedServiceStatus instance with id: " + id);
		try {
			AssignedServiceStatus instance = (AssignedServiceStatus) getHibernateTemplate()
					.get("com.wtc.hcis.da.AssignedServiceStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssignedServiceStatus instance) {
		log.debug("finding AssignedServiceStatus instance by example");
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
		log.debug("finding AssignedServiceStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssignedServiceStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findAll() {
		log.debug("finding all AssignedServiceStatus instances");
		try {
			String queryString = "from AssignedServiceStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssignedServiceStatus merge(AssignedServiceStatus detachedInstance) {
		log.debug("merging AssignedServiceStatus instance");
		try {
			AssignedServiceStatus result = (AssignedServiceStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssignedServiceStatus instance) {
		log.debug("attaching dirty AssignedServiceStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssignedServiceStatus instance) {
		log.debug("attaching clean AssignedServiceStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssignedServiceStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssignedServiceStatusDAO) ctx
				.getBean("AssignedServiceStatusDAO");
	}
}