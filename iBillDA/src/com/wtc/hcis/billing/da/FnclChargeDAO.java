package com.wtc.hcis.billing.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * FnclCharge entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.FnclCharge
 * @author MyEclipse Persistence Tools
 */

public class FnclChargeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FnclChargeDAO.class);
	// property constants
	public static final String ACCOUNT_ID = "accountId";
	public static final String CHARGE_AMOUNT = "chargeAmount";
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(FnclCharge transientInstance) {
		log.debug("saving FnclCharge instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FnclCharge persistentInstance) {
		log.debug("deleting FnclCharge instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FnclCharge findById(java.lang.Long id) {
		log.debug("getting FnclCharge instance with id: " + id);
		try {
			FnclCharge instance = (FnclCharge) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.FnclCharge", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FnclCharge instance) {
		log.debug("finding FnclCharge instance by example");
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
		log.debug("finding FnclCharge instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from FnclCharge as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAccountId(Object accountId) {
		return findByProperty(ACCOUNT_ID, accountId);
	}

	public List findByChargeAmount(Object chargeAmount) {
		return findByProperty(CHARGE_AMOUNT, chargeAmount);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all FnclCharge instances");
		try {
			String queryString = "from FnclCharge";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FnclCharge merge(FnclCharge detachedInstance) {
		log.debug("merging FnclCharge instance");
		try {
			FnclCharge result = (FnclCharge) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FnclCharge instance) {
		log.debug("attaching dirty FnclCharge instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FnclCharge instance) {
		log.debug("attaching clean FnclCharge instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FnclChargeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FnclChargeDAO) ctx.getBean("FnclChargeDAO");
	}
}