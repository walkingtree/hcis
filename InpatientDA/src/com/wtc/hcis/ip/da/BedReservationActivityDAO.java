package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BedReservationActivity entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedReservationActivity
 * @author MyEclipse Persistence Tools
 */

public class BedReservationActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BedReservationActivityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(BedReservationActivity transientInstance) {
		log.debug("saving BedReservationActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedReservationActivity persistentInstance) {
		log.debug("deleting BedReservationActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedReservationActivity findById(
			com.wtc.hcis.ip.da.BedReservationActivityId id) {
		log.debug("getting BedReservationActivity instance with id: " + id);
		try {
			BedReservationActivity instance = (BedReservationActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedReservationActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedReservationActivity instance) {
		log.debug("finding BedReservationActivity instance by example");
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
		log.debug("finding BedReservationActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedReservationActivity as model where model."
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

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all BedReservationActivity instances");
		try {
			String queryString = "from BedReservationActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedReservationActivity merge(BedReservationActivity detachedInstance) {
		log.debug("merging BedReservationActivity instance");
		try {
			BedReservationActivity result = (BedReservationActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedReservationActivity instance) {
		log.debug("attaching dirty BedReservationActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedReservationActivity instance) {
		log.debug("attaching clean BedReservationActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedReservationActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedReservationActivityDAO) ctx
				.getBean("BedReservationActivityDAO");
	}
}