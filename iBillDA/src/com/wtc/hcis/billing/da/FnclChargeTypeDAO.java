package com.wtc.hcis.billing.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * FnclChargeType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.FnclChargeType
 * @author MyEclipse Persistence Tools
 */

public class FnclChargeTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FnclChargeTypeDAO.class);
	// property constants
	public static final String CHARGE_TYPE_NAME = "chargeTypeName";
	public static final String PROCESS_NAME = "processName";
	public static final String DISPLAY_SEQUENCE_NBR = "displaySequenceNbr";
	public static final String DISPLAY_DETAILS = "displayDetails";

	protected void initDao() {
		// do nothing
	}

	public void save(FnclChargeType transientInstance) {
		log.debug("saving FnclChargeType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FnclChargeType persistentInstance) {
		log.debug("deleting FnclChargeType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FnclChargeType findById(java.lang.Integer id) {
		log.debug("getting FnclChargeType instance with id: " + id);
		try {
			FnclChargeType instance = (FnclChargeType) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.FnclChargeType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FnclChargeType instance) {
		log.debug("finding FnclChargeType instance by example");
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
		log.debug("finding FnclChargeType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FnclChargeType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByChargeTypeName(Object chargeTypeName) {
		return findByProperty(CHARGE_TYPE_NAME, chargeTypeName);
	}

	public List findByProcessName(Object processName) {
		return findByProperty(PROCESS_NAME, processName);
	}

	public List findByDisplaySequenceNbr(Object displaySequenceNbr) {
		return findByProperty(DISPLAY_SEQUENCE_NBR, displaySequenceNbr);
	}

	public List findByDisplayDetails(Object displayDetails) {
		return findByProperty(DISPLAY_DETAILS, displayDetails);
	}

	public List findAll() {
		log.debug("finding all FnclChargeType instances");
		try {
			String queryString = "from FnclChargeType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FnclChargeType merge(FnclChargeType detachedInstance) {
		log.debug("merging FnclChargeType instance");
		try {
			FnclChargeType result = (FnclChargeType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FnclChargeType instance) {
		log.debug("attaching dirty FnclChargeType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FnclChargeType instance) {
		log.debug("attaching clean FnclChargeType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FnclChargeTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FnclChargeTypeDAO) ctx.getBean("FnclChargeTypeDAO");
	}
}