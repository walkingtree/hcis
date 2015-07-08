package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * OtBookingActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtBookingActivity
 * @author MyEclipse Persistence Tools
 */

public class OtBookingActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtBookingActivityDAO.class);
	// property constants
	public static final String BOOKING_STATUS = "bookingStatus";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtBookingActivity transientInstance) {
		log.debug("saving OtBookingActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtBookingActivity persistentInstance) {
		log.debug("deleting OtBookingActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtBookingActivity findById(com.wtc.hcis.ip.da.OtBookingActivityId id) {
		log.debug("getting OtBookingActivity instance with id: " + id);
		try {
			OtBookingActivity instance = (OtBookingActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtBookingActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtBookingActivity instance) {
		log.debug("finding OtBookingActivity instance by example");
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
		log.debug("finding OtBookingActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtBookingActivity as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBookingStatus(Object bookingStatus) {
		return findByProperty(BOOKING_STATUS, bookingStatus);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtBookingActivity instances");
		try {
			String queryString = "from OtBookingActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtBookingActivity merge(OtBookingActivity detachedInstance) {
		log.debug("merging OtBookingActivity instance");
		try {
			OtBookingActivity result = (OtBookingActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtBookingActivity instance) {
		log.debug("attaching dirty OtBookingActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtBookingActivity instance) {
		log.debug("attaching clean OtBookingActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtBookingActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtBookingActivityDAO) ctx.getBean("OtBookingActivityDAO");
	}
}