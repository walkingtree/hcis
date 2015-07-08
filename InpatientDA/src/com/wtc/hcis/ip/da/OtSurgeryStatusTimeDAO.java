package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * OtSurgeryStatusTime entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtSurgeryStatusTime
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryStatusTimeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtSurgeryStatusTimeDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String TIME = "time";
	public static final String SURGEON_REQUIRED = "surgeonRequired";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtSurgeryStatusTime transientInstance) {
		log.debug("saving OtSurgeryStatusTime instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtSurgeryStatusTime persistentInstance) {
		log.debug("deleting OtSurgeryStatusTime instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtSurgeryStatusTime findById(
			com.wtc.hcis.ip.da.OtSurgeryStatusTimeId id) {
		log.debug("getting OtSurgeryStatusTime instance with id: " + id);
		try {
			OtSurgeryStatusTime instance = (OtSurgeryStatusTime) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtSurgeryStatusTime", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtSurgeryStatusTime instance) {
		log.debug("finding OtSurgeryStatusTime instance by example");
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
		log.debug("finding OtSurgeryStatusTime instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtSurgeryStatusTime as model where model."
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

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findBySurgeonRequired(Object surgeonRequired) {
		return findByProperty(SURGEON_REQUIRED, surgeonRequired);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtSurgeryStatusTime instances");
		try {
			String queryString = "from OtSurgeryStatusTime";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtSurgeryStatusTime merge(OtSurgeryStatusTime detachedInstance) {
		log.debug("merging OtSurgeryStatusTime instance");
		try {
			OtSurgeryStatusTime result = (OtSurgeryStatusTime) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtSurgeryStatusTime instance) {
		log.debug("attaching dirty OtSurgeryStatusTime instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtSurgeryStatusTime instance) {
		log.debug("attaching clean OtSurgeryStatusTime instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtSurgeryStatusTimeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtSurgeryStatusTimeDAO) ctx.getBean("OtSurgeryStatusTimeDAO");
	}
}