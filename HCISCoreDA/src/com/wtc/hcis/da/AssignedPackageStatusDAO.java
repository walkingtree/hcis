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
 * AssignedPackageStatus entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AssignedPackageStatus
 * @author MyEclipse Persistence Tools
 */

public class AssignedPackageStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AssignedPackageStatusDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(AssignedPackageStatus transientInstance) {
		log.debug("saving AssignedPackageStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssignedPackageStatus persistentInstance) {
		log.debug("deleting AssignedPackageStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssignedPackageStatus findById(java.lang.String id) {
		log.debug("getting AssignedPackageStatus instance with id: " + id);
		try {
			AssignedPackageStatus instance = (AssignedPackageStatus) getHibernateTemplate()
					.get("com.wtc.hcis.da.AssignedPackageStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssignedPackageStatus instance) {
		log.debug("finding AssignedPackageStatus instance by example");
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
		log.debug("finding AssignedPackageStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssignedPackageStatus as model where model."
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
		log.debug("finding all AssignedPackageStatus instances");
		try {
			String queryString = "from AssignedPackageStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssignedPackageStatus merge(AssignedPackageStatus detachedInstance) {
		log.debug("merging AssignedPackageStatus instance");
		try {
			AssignedPackageStatus result = (AssignedPackageStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssignedPackageStatus instance) {
		log.debug("attaching dirty AssignedPackageStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssignedPackageStatus instance) {
		log.debug("attaching clean AssignedPackageStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssignedPackageStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssignedPackageStatusDAO) ctx
				.getBean("AssignedPackageStatusDAO");
	}
}