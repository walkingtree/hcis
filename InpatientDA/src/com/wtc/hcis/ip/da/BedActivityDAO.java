package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BedActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedActivity
 * @author MyEclipse Persistence Tools
 */

public class BedActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedActivityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(BedActivity transientInstance) {
		log.debug("saving BedActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedActivity persistentInstance) {
		log.debug("deleting BedActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedActivity findById(com.wtc.hcis.ip.da.BedActivityId id) {
		log.debug("getting BedActivity instance with id: " + id);
		try {
			BedActivity instance = (BedActivity) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.BedActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedActivity instance) {
		log.debug("finding BedActivity instance by example");
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
		log.debug("finding BedActivity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BedActivity as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all BedActivity instances");
		try {
			String queryString = "from BedActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedActivity merge(BedActivity detachedInstance) {
		log.debug("merging BedActivity instance");
		try {
			BedActivity result = (BedActivity) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedActivity instance) {
		log.debug("attaching dirty BedActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedActivity instance) {
		log.debug("attaching clean BedActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedActivityDAO) ctx.getBean("BedActivityDAO");
	}
}