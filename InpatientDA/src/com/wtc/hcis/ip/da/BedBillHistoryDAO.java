package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BedBillHistory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedBillHistory
 * @author MyEclipse Persistence Tools
 */

public class BedBillHistoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedBillHistoryDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String BILLED_HOUR_UNIT = "billedHourUnit";
	public static final String BILLED_DAY_UNIT = "billedDayUnit";
	public static final String HOURLY_CHARGE = "hourlyCharge";
	public static final String DAILY_CHARGE = "dailyCharge";
	public static final String TOTAL_CHARGE = "totalCharge";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(BedBillHistory transientInstance) {
		log.debug("saving BedBillHistory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedBillHistory persistentInstance) {
		log.debug("deleting BedBillHistory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedBillHistory findById(com.wtc.hcis.ip.da.BedBillHistoryId id) {
		log.debug("getting BedBillHistory instance with id: " + id);
		try {
			BedBillHistory instance = (BedBillHistory) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedBillHistory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedBillHistory instance) {
		log.debug("finding BedBillHistory instance by example");
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
		log.debug("finding BedBillHistory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedBillHistory as model where model."
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

	public List findByBilledHourUnit(Object billedHourUnit) {
		return findByProperty(BILLED_HOUR_UNIT, billedHourUnit);
	}

	public List findByBilledDayUnit(Object billedDayUnit) {
		return findByProperty(BILLED_DAY_UNIT, billedDayUnit);
	}

	public List findByHourlyCharge(Object hourlyCharge) {
		return findByProperty(HOURLY_CHARGE, hourlyCharge);
	}

	public List findByDailyCharge(Object dailyCharge) {
		return findByProperty(DAILY_CHARGE, dailyCharge);
	}

	public List findByTotalCharge(Object totalCharge) {
		return findByProperty(TOTAL_CHARGE, totalCharge);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all BedBillHistory instances");
		try {
			String queryString = "from BedBillHistory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedBillHistory merge(BedBillHistory detachedInstance) {
		log.debug("merging BedBillHistory instance");
		try {
			BedBillHistory result = (BedBillHistory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedBillHistory instance) {
		log.debug("attaching dirty BedBillHistory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedBillHistory instance) {
		log.debug("attaching clean BedBillHistory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedBillHistoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedBillHistoryDAO) ctx.getBean("BedBillHistoryDAO");
	}
}