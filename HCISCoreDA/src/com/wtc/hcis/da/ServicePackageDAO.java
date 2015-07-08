package com.wtc.hcis.da;

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
 * ServicePackage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ServicePackage
 * @author MyEclipse Persistence Tools
 */

public class ServicePackageDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ServicePackageDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String CREATED_BY = "createdBy";
	public static final String PACKAGE_CHARGE = "packageCharge";
	public static final String CHARGE_OVERRIDE_LEVEL = "chargeOverrideLevel";
	public static final String DISCOUNT_AMT_PCT = "discountAmtPct";
	public static final String DISCOUNT_TYPE = "discountType";
	public static final String EFFECTIVE_CHARGE = "effectiveCharge";
	public static final String SUSPEND_LEVEL_FLAG = "suspendLevelFlag";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ServicePackage transientInstance) {
		log.debug("saving ServicePackage instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServicePackage persistentInstance) {
		log.debug("deleting ServicePackage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServicePackage findById(java.lang.String id) {
		log.debug("getting ServicePackage instance with id: " + id);
		try {
			ServicePackage instance = (ServicePackage) getHibernateTemplate()
					.get("com.wtc.hcis.da.ServicePackage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServicePackage instance) {
		log.debug("finding ServicePackage instance by example");
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
		log.debug("finding ServicePackage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServicePackage as model where model."
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

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByPackageCharge(Object packageCharge) {
		return findByProperty(PACKAGE_CHARGE, packageCharge);
	}

	public List findByChargeOverrideLevel(Object chargeOverrideLevel) {
		return findByProperty(CHARGE_OVERRIDE_LEVEL, chargeOverrideLevel);
	}

	public List findByDiscountAmtPct(Object discountAmtPct) {
		return findByProperty(DISCOUNT_AMT_PCT, discountAmtPct);
	}

	public List findByDiscountType(Object discountType) {
		return findByProperty(DISCOUNT_TYPE, discountType);
	}

	public List findByEffectiveCharge(Object effectiveCharge) {
		return findByProperty(EFFECTIVE_CHARGE, effectiveCharge);
	}

	public List findBySuspendLevelFlag(Object suspendLevelFlag) {
		return findByProperty(SUSPEND_LEVEL_FLAG, suspendLevelFlag);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all ServicePackage instances");
		try {
			String queryString = "from ServicePackage";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServicePackage merge(ServicePackage detachedInstance) {
		log.debug("merging ServicePackage instance");
		try {
			ServicePackage result = (ServicePackage) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServicePackage instance) {
		log.debug("attaching dirty ServicePackage instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServicePackage instance) {
		log.debug("attaching clean ServicePackage instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServicePackageDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServicePackageDAO) ctx.getBean("ServicePackageDAO");
	}
}