package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PackageHasService entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.PackageHasService
 * @author MyEclipse Persistence Tools
 */

public class PackageHasServiceDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(PackageHasServiceDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NUMBER_OF_UNITS = "numberOfUnits";
	public static final String SERVICE_CHARGE = "serviceCharge";
	public static final String DISCOUNT_TYPE = "discountType";
	public static final String DISCOUNT_AMT_PCT = "discountAmtPct";
	public static final String EFFECTIVE_CHARGE = "effectiveCharge";

	protected void initDao() {
		// do nothing
	}

	public void save(PackageHasService transientInstance) {
		log.debug("saving PackageHasService instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PackageHasService persistentInstance) {
		log.debug("deleting PackageHasService instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PackageHasService findById(com.wtc.hcis.da.PackageHasServiceId id) {
		log.debug("getting PackageHasService instance with id: " + id);
		try {
			PackageHasService instance = (PackageHasService) getHibernateTemplate()
					.get("com.wtc.hcis.da.PackageHasService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PackageHasService instance) {
		log.debug("finding PackageHasService instance by example");
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
		log.debug("finding PackageHasService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PackageHasService as model where model."
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

	public List findByNumberOfUnits(Object numberOfUnits) {
		return findByProperty(NUMBER_OF_UNITS, numberOfUnits);
	}

	public List findByServiceCharge(Object serviceCharge) {
		return findByProperty(SERVICE_CHARGE, serviceCharge);
	}

	public List findByDiscountType(Object discountType) {
		return findByProperty(DISCOUNT_TYPE, discountType);
	}

	public List findByDiscountAmtPct(Object discountAmtPct) {
		return findByProperty(DISCOUNT_AMT_PCT, discountAmtPct);
	}

	public List findByEffectiveCharge(Object effectiveCharge) {
		return findByProperty(EFFECTIVE_CHARGE, effectiveCharge);
	}

	public List findAll() {
		log.debug("finding all PackageHasService instances");
		try {
			String queryString = "from PackageHasService";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PackageHasService merge(PackageHasService detachedInstance) {
		log.debug("merging PackageHasService instance");
		try {
			PackageHasService result = (PackageHasService) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PackageHasService instance) {
		log.debug("attaching dirty PackageHasService instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PackageHasService instance) {
		log.debug("attaching clean PackageHasService instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PackageHasServiceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PackageHasServiceDAO) ctx.getBean("PackageHasServiceDAO");
	}
}