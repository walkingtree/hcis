package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AdmissionClaimActivity entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.AdmissionClaimActivity
 * @author MyEclipse Persistence Tools
 */

public class AdmissionClaimActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AdmissionClaimActivityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(AdmissionClaimActivity transientInstance) {
		log.debug("saving AdmissionClaimActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdmissionClaimActivity persistentInstance) {
		log.debug("deleting AdmissionClaimActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdmissionClaimActivity findById(
			com.wtc.hcis.ip.da.AdmissionClaimActivityId id) {
		log.debug("getting AdmissionClaimActivity instance with id: " + id);
		try {
			AdmissionClaimActivity instance = (AdmissionClaimActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.AdmissionClaimActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdmissionClaimActivity instance) {
		log.debug("finding AdmissionClaimActivity instance by example");
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
		log.debug("finding AdmissionClaimActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdmissionClaimActivity as model where model."
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

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all AdmissionClaimActivity instances");
		try {
			String queryString = "from AdmissionClaimActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AdmissionClaimActivity merge(AdmissionClaimActivity detachedInstance) {
		log.debug("merging AdmissionClaimActivity instance");
		try {
			AdmissionClaimActivity result = (AdmissionClaimActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdmissionClaimActivity instance) {
		log.debug("attaching dirty AdmissionClaimActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdmissionClaimActivity instance) {
		log.debug("attaching clean AdmissionClaimActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionClaimActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdmissionClaimActivityDAO) ctx
				.getBean("AdmissionClaimActivityDAO");
	}
}