package com.wtc.hcis.billing.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillAccount entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillAccount
 * @author MyEclipse Persistence Tools
 */

public class BillAccountDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillAccountDAO.class);
	// property constants
	public static final String ACCT_HOLDER_NAME = "acctHolderName";
	public static final String BILL_ADDRESS = "billAddress";
	public static final String EMAIL = "email";
	public static final String PHONE = "phone";

	protected void initDao() {
		// do nothing
	}

	public void save(BillAccount transientInstance) {
		log.debug("saving BillAccount instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillAccount persistentInstance) {
		log.debug("deleting BillAccount instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillAccount findById(com.wtc.hcis.billing.da.BillAccountId id) {
		log.debug("getting BillAccount instance with id: " + id);
		try {
			BillAccount instance = (BillAccount) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.BillAccount", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillAccount instance) {
		log.debug("finding BillAccount instance by example");
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
		log.debug("finding BillAccount instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BillAccount as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAcctHolderName(Object acctHolderName) {
		return findByProperty(ACCT_HOLDER_NAME, acctHolderName);
	}

	public List findByBillAddress(Object billAddress) {
		return findByProperty(BILL_ADDRESS, billAddress);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findAll() {
		log.debug("finding all BillAccount instances");
		try {
			String queryString = "from BillAccount";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillAccount merge(BillAccount detachedInstance) {
		log.debug("merging BillAccount instance");
		try {
			BillAccount result = (BillAccount) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillAccount instance) {
		log.debug("attaching dirty BillAccount instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillAccount instance) {
		log.debug("attaching clean BillAccount instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillAccountDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillAccountDAO) ctx.getBean("BillAccountDAO");
	}
}