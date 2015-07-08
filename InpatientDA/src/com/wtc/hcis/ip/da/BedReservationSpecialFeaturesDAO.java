package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BedReservationSpecialFeatures entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.ip.da.BedReservationSpecialFeatures
 * @author MyEclipse Persistence Tools
 */

public class BedReservationSpecialFeaturesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BedReservationSpecialFeaturesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REQUIRED_FLAG = "requiredFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(BedReservationSpecialFeatures transientInstance) {
		log.debug("saving BedReservationSpecialFeatures instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedReservationSpecialFeatures persistentInstance) {
		log.debug("deleting BedReservationSpecialFeatures instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedReservationSpecialFeatures findById(
			com.wtc.hcis.ip.da.BedReservationSpecialFeaturesId id) {
		log.debug("getting BedReservationSpecialFeatures instance with id: "
				+ id);
		try {
			BedReservationSpecialFeatures instance = (BedReservationSpecialFeatures) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedReservationSpecialFeatures", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedReservationSpecialFeatures instance) {
		log.debug("finding BedReservationSpecialFeatures instance by example");
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
		log
				.debug("finding BedReservationSpecialFeatures instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedReservationSpecialFeatures as model where model."
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

	public List findByRequiredFlag(Object requiredFlag) {
		return findByProperty(REQUIRED_FLAG, requiredFlag);
	}

	public List findAll() {
		log.debug("finding all BedReservationSpecialFeatures instances");
		try {
			String queryString = "from BedReservationSpecialFeatures";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedReservationSpecialFeatures merge(
			BedReservationSpecialFeatures detachedInstance) {
		log.debug("merging BedReservationSpecialFeatures instance");
		try {
			BedReservationSpecialFeatures result = (BedReservationSpecialFeatures) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedReservationSpecialFeatures instance) {
		log.debug("attaching dirty BedReservationSpecialFeatures instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedReservationSpecialFeatures instance) {
		log.debug("attaching clean BedReservationSpecialFeatures instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedReservationSpecialFeaturesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedReservationSpecialFeaturesDAO) ctx
				.getBean("BedReservationSpecialFeaturesDAO");
	}
}