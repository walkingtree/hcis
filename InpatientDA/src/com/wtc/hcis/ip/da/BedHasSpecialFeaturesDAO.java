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
 * BedHasSpecialFeatures entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedHasSpecialFeatures
 * @author MyEclipse Persistence Tools
 */

public class BedHasSpecialFeaturesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BedHasSpecialFeaturesDAO.class);
	// property constants
	public static final String VERSION = "version";

	protected void initDao() {
		// do nothing
	}

	public void save(BedHasSpecialFeatures transientInstance) {
		log.debug("saving BedHasSpecialFeatures instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedHasSpecialFeatures persistentInstance) {
		log.debug("deleting BedHasSpecialFeatures instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedHasSpecialFeatures findById(
			com.wtc.hcis.ip.da.BedHasSpecialFeaturesId id) {
		log.debug("getting BedHasSpecialFeatures instance with id: " + id);
		try {
			BedHasSpecialFeatures instance = (BedHasSpecialFeatures) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedHasSpecialFeatures", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedHasSpecialFeatures instance) {
		log.debug("finding BedHasSpecialFeatures instance by example");
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
		log.debug("finding BedHasSpecialFeatures instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedHasSpecialFeatures as model where model."
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

	public List findAll() {
		log.debug("finding all BedHasSpecialFeatures instances");
		try {
			String queryString = "from BedHasSpecialFeatures";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedHasSpecialFeatures merge(BedHasSpecialFeatures detachedInstance) {
		log.debug("merging BedHasSpecialFeatures instance");
		try {
			BedHasSpecialFeatures result = (BedHasSpecialFeatures) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedHasSpecialFeatures instance) {
		log.debug("attaching dirty BedHasSpecialFeatures instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedHasSpecialFeatures instance) {
		log.debug("attaching clean BedHasSpecialFeatures instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedHasSpecialFeaturesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedHasSpecialFeaturesDAO) ctx
				.getBean("BedHasSpecialFeaturesDAO");
	}
}