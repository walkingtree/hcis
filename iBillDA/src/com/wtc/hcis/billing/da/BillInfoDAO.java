package com.wtc.hcis.billing.da;

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
 * BillInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillInfo
 * @author MyEclipse Persistence Tools
 */

public class BillInfoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillInfoDAO.class);
	// property constants
	public static final String ACCOUNT_ID = "accountId";
	public static final String CLIENT_NAME = "clientName";
	public static final String BILL_AMT = "billAmt";
	public static final String CURRENT_BALANCE = "currentBalance";
	public static final String PAID_AMOUNT = "paidAmount";
	public static final String PREVIOUS_BALANCE = "previousBalance";
	public static final String DUPLICATE_BILL_PRINT_COUNT = "duplicateBillPrintCount";

	protected void initDao() {
		// do nothing
	}

	public void save(BillInfo transientInstance) {
		log.debug("saving BillInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillInfo persistentInstance) {
		log.debug("deleting BillInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillInfo findById(java.lang.Long id) {
		log.debug("getting BillInfo instance with id: " + id);
		try {
			BillInfo instance = (BillInfo) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.BillInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillInfo instance) {
		log.debug("finding BillInfo instance by example");
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
		log.debug("finding BillInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BillInfo as model where model."
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

	public List findByClientName(Object clientName) {
		return findByProperty(CLIENT_NAME, clientName);
	}

	public List findByBillAmt(Object billAmt) {
		return findByProperty(BILL_AMT, billAmt);
	}

	public List findByCurrentBalance(Object currentBalance) {
		return findByProperty(CURRENT_BALANCE, currentBalance);
	}

	public List findByPaidAmount(Object paidAmount) {
		return findByProperty(PAID_AMOUNT, paidAmount);
	}

	public List findByPreviousBalance(Object previousBalance) {
		return findByProperty(PREVIOUS_BALANCE, previousBalance);
	}

	public List findByDuplicateBillPrintCount(Object duplicateBillPrintCount) {
		return findByProperty(DUPLICATE_BILL_PRINT_COUNT,
				duplicateBillPrintCount);
	}

	public List findAll() {
		log.debug("finding all BillInfo instances");
		try {
			String queryString = "from BillInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillInfo merge(BillInfo detachedInstance) {
		log.debug("merging BillInfo instance");
		try {
			BillInfo result = (BillInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillInfo instance) {
		log.debug("attaching dirty BillInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillInfo instance) {
		log.debug("attaching clean BillInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BillInfoDAO) ctx.getBean("BillInfoDAO");
	}
}