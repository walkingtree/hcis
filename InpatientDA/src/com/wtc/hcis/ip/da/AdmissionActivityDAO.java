package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AdmissionActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.AdmissionActivity
 * @author MyEclipse Persistence Tools
 */

public class AdmissionActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AdmissionActivityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(AdmissionActivity transientInstance) {
		log.debug("saving AdmissionActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdmissionActivity persistentInstance) {
		log.debug("deleting AdmissionActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdmissionActivity findById(com.wtc.hcis.ip.da.AdmissionActivityId id) {
		log.debug("getting AdmissionActivity instance with id: " + id);
		try {
			AdmissionActivity instance = (AdmissionActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.AdmissionActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdmissionActivity instance) {
		log.debug("finding AdmissionActivity instance by example");
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
		log.debug("finding AdmissionActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdmissionActivity as model where model."
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

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all AdmissionActivity instances");
		try {
			String queryString = "from AdmissionActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AdmissionActivity merge(AdmissionActivity detachedInstance) {
		log.debug("merging AdmissionActivity instance");
		try {
			AdmissionActivity result = (AdmissionActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdmissionActivity instance) {
		log.debug("attaching dirty AdmissionActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdmissionActivity instance) {
		log.debug("attaching clean AdmissionActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdmissionActivityDAO) ctx.getBean("AdmissionActivityDAO");
	}
}