package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DateDim entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DateDim
 * @author MyEclipse Persistence Tools
 */

public class DateDimDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DateDimDAO.class);
	// property constants
	public static final String DAY_OF_YEAR = "dayOfYear";
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String YEAR_QUARTER = "yearQuarter";
	public static final String YEAR_AND_MONTH = "yearAndMonth";
	public static final String YEAR_MONTH_WEEK = "yearMonthWeek";
	public static final String YEAR_MONTH_DATE = "yearMonthDate";
	public static final String DAY_OF_MONTH = "dayOfMonth";
	public static final String WEEK_NBR = "weekNbr";
	public static final String QUARTER_NBR = "quarterNbr";
	public static final String DAY_NAME = "dayName";
	public static final String MONTH_NAME = "monthName";

	protected void initDao() {
		// do nothing
	}

	public void save(DateDim transientInstance) {
		log.debug("saving DateDim instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DateDim persistentInstance) {
		log.debug("deleting DateDim instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DateDim findById(java.lang.Integer id) {
		log.debug("getting DateDim instance with id: " + id);
		try {
			DateDim instance = (DateDim) getHibernateTemplate().get(
					"com.wtc.hcis.da.DateDim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DateDim instance) {
		log.debug("finding DateDim instance by example");
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
		log.debug("finding DateDim instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DateDim as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDayOfYear(Object dayOfYear) {
		return findByProperty(DAY_OF_YEAR, dayOfYear);
	}

	public List findByYear(Object year) {
		return findByProperty(YEAR, year);
	}

	public List findByMonth(Object month) {
		return findByProperty(MONTH, month);
	}

	public List findByYearQuarter(Object yearQuarter) {
		return findByProperty(YEAR_QUARTER, yearQuarter);
	}

	public List findByYearAndMonth(Object yearAndMonth) {
		return findByProperty(YEAR_AND_MONTH, yearAndMonth);
	}

	public List findByYearMonthWeek(Object yearMonthWeek) {
		return findByProperty(YEAR_MONTH_WEEK, yearMonthWeek);
	}

	public List findByYearMonthDate(Object yearMonthDate) {
		return findByProperty(YEAR_MONTH_DATE, yearMonthDate);
	}

	public List findByDayOfMonth(Object dayOfMonth) {
		return findByProperty(DAY_OF_MONTH, dayOfMonth);
	}

	public List findByWeekNbr(Object weekNbr) {
		return findByProperty(WEEK_NBR, weekNbr);
	}

	public List findByQuarterNbr(Object quarterNbr) {
		return findByProperty(QUARTER_NBR, quarterNbr);
	}

	public List findByDayName(Object dayName) {
		return findByProperty(DAY_NAME, dayName);
	}

	public List findByMonthName(Object monthName) {
		return findByProperty(MONTH_NAME, monthName);
	}

	public List findAll() {
		log.debug("finding all DateDim instances");
		try {
			String queryString = "from DateDim";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DateDim merge(DateDim detachedInstance) {
		log.debug("merging DateDim instance");
		try {
			DateDim result = (DateDim) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DateDim instance) {
		log.debug("attaching dirty DateDim instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DateDim instance) {
		log.debug("attaching clean DateDim instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DateDimDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DateDimDAO) ctx.getBean("DateDimDAO");
	}
}