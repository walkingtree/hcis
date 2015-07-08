package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BedSpecialFeature entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedSpecialFeature
 * @author MyEclipse Persistence Tools
 */

public class BedSpecialFeatureDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BedSpecialFeatureDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String LOCATION_WRT_BED_IND = "locationWrtBedInd";

	protected void initDao() {
		// do nothing
	}

	public void save(BedSpecialFeature transientInstance) {
		log.debug("saving BedSpecialFeature instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedSpecialFeature persistentInstance) {
		log.debug("deleting BedSpecialFeature instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedSpecialFeature findById(java.lang.String id) {
		log.debug("getting BedSpecialFeature instance with id: " + id);
		try {
			BedSpecialFeature instance = (BedSpecialFeature) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedSpecialFeature", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedSpecialFeature instance) {
		log.debug("finding BedSpecialFeature instance by example");
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
		log.debug("finding BedSpecialFeature instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedSpecialFeature as model where model."
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

	public List findByLocationWrtBedInd(Object locationWrtBedInd) {
		return findByProperty(LOCATION_WRT_BED_IND, locationWrtBedInd);
	}

	public List findAll() {
		log.debug("finding all BedSpecialFeature instances");
		try {
			String queryString = "from BedSpecialFeature";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedSpecialFeature merge(BedSpecialFeature detachedInstance) {
		log.debug("merging BedSpecialFeature instance");
		try {
			BedSpecialFeature result = (BedSpecialFeature) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedSpecialFeature instance) {
		log.debug("attaching dirty BedSpecialFeature instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedSpecialFeature instance) {
		log.debug("attaching clean BedSpecialFeature instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedSpecialFeatureDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedSpecialFeatureDAO) ctx.getBean("BedSpecialFeatureDAO");
	}
}