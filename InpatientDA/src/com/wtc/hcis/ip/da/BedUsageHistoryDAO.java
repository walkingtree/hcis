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
 * BedUsageHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedUsageHistory
 * @author MyEclipse Persistence Tools
 */

public class BedUsageHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedUsageHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String LAST_BILL_NBR = "lastBillNbr";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(BedUsageHistory transientInstance) {
		log.debug("saving BedUsageHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedUsageHistory persistentInstance) {
		log.debug("deleting BedUsageHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedUsageHistory findById(java.lang.Integer id) {
		log.debug("getting BedUsageHistory instance with id: " + id);
		try {
			BedUsageHistory instance = (BedUsageHistory) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedUsageHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedUsageHistory instance) {
		log.debug("finding BedUsageHistory instance by example");
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
		log.debug("finding BedUsageHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedUsageHistory as model where model."
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

	public List findByLastBillNbr(Object lastBillNbr) {
		return findByProperty(LAST_BILL_NBR, lastBillNbr);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all BedUsageHistory instances");
		try {
			String queryString = "from BedUsageHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedUsageHistory merge(BedUsageHistory detachedInstance) {
		log.debug("merging BedUsageHistory instance");
		try {
			BedUsageHistory result = (BedUsageHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedUsageHistory instance) {
		log.debug("attaching dirty BedUsageHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedUsageHistory instance) {
		log.debug("attaching clean BedUsageHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedUsageHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedUsageHistoryDAO) ctx.getBean("BedUsageHistoryDAO");
	}
}