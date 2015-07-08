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
 * ServicePackageStatus entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ServicePackageStatus
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ServicePackageStatusDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(ServicePackageStatus transientInstance) {
		log.debug("saving ServicePackageStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServicePackageStatus persistentInstance) {
		log.debug("deleting ServicePackageStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServicePackageStatus findById(java.lang.String id) {
		log.debug("getting ServicePackageStatus instance with id: " + id);
		try {
			ServicePackageStatus instance = (ServicePackageStatus) getHibernateTemplate()
					.get("com.wtc.hcis.da.ServicePackageStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServicePackageStatus instance) {
		log.debug("finding ServicePackageStatus instance by example");
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
		log.debug("finding ServicePackageStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServicePackageStatus as model where model."
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
		log.debug("finding all ServicePackageStatus instances");
		try {
			String queryString = "from ServicePackageStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServicePackageStatus merge(ServicePackageStatus detachedInstance) {
		log.debug("merging ServicePackageStatus instance");
		try {
			ServicePackageStatus result = (ServicePackageStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServicePackageStatus instance) {
		log.debug("attaching dirty ServicePackageStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServicePackageStatus instance) {
		log.debug("attaching clean ServicePackageStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServicePackageStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServicePackageStatusDAO) ctx.getBean("ServicePackageStatusDAO");
	}
}