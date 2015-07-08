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
 * FnclTrnsctSummary entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.FnclTrnsctSummary
 * @author MyEclipse Persistence Tools
 */

public class FnclTrnsctSummaryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(FnclTrnsctSummaryDAO.class);
	// property constants
	public static final String CREDIT_DEBIT_IND = "creditDebitInd";
	public static final String AMOUNT = "amount";

	protected void initDao() {
		// do nothing
	}

	public void save(FnclTrnsctSummary transientInstance) {
		log.debug("saving FnclTrnsctSummary instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FnclTrnsctSummary persistentInstance) {
		log.debug("deleting FnclTrnsctSummary instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FnclTrnsctSummary findById(
			com.wtc.hcis.billing.da.FnclTrnsctSummaryId id) {
		log.debug("getting FnclTrnsctSummary instance with id: " + id);
		try {
			FnclTrnsctSummary instance = (FnclTrnsctSummary) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.FnclTrnsctSummary", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FnclTrnsctSummary instance) {
		log.debug("finding FnclTrnsctSummary instance by example");
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
		log.debug("finding FnclTrnsctSummary instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FnclTrnsctSummary as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreditDebitInd(Object creditDebitInd) {
		return findByProperty(CREDIT_DEBIT_IND, creditDebitInd);
	}

	public List findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	public List findAll() {
		log.debug("finding all FnclTrnsctSummary instances");
		try {
			String queryString = "from FnclTrnsctSummary";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FnclTrnsctSummary merge(FnclTrnsctSummary detachedInstance) {
		log.debug("merging FnclTrnsctSummary instance");
		try {
			FnclTrnsctSummary result = (FnclTrnsctSummary) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FnclTrnsctSummary instance) {
		log.debug("attaching dirty FnclTrnsctSummary instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FnclTrnsctSummary instance) {
		log.debug("attaching clean FnclTrnsctSummary instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FnclTrnsctSummaryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FnclTrnsctSummaryDAO) ctx.getBean("FnclTrnsctSummaryDAO");
	}
}