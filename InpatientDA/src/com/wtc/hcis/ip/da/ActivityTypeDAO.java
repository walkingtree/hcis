package com.wtc.hcis.ip.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ActivityType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ActivityType
 * @author MyEclipse Persistence Tools
 */

public class ActivityTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ActivityTypeDAO.class);
	// property constants
	public static final String ACTIVITY_DESC = "activityDesc";
	public static final String ACTIVITY_GROUP = "activityGroup";

	protected void initDao() {
		// do nothing
	}

	public void save(ActivityType transientInstance) {
		log.debug("saving ActivityType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActivityType persistentInstance) {
		log.debug("deleting ActivityType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActivityType findById(java.lang.String id) {
		log.debug("getting ActivityType instance with id: " + id);
		try {
			ActivityType instance = (ActivityType) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.ActivityType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActivityType instance) {
		log.debug("finding ActivityType instance by example");
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
		log.debug("finding ActivityType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActivityType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActivityDesc(Object activityDesc) {
		return findByProperty(ACTIVITY_DESC, activityDesc);
	}

	public List findByActivityGroup(Object activityGroup) {
		return findByProperty(ACTIVITY_GROUP, activityGroup);
	}

	public List findAll() {
		log.debug("finding all ActivityType instances");
		try {
			String queryString = "from ActivityType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActivityType merge(ActivityType detachedInstance) {
		log.debug("merging ActivityType instance");
		try {
			ActivityType result = (ActivityType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActivityType instance) {
		log.debug("attaching dirty ActivityType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActivityType instance) {
		log.debug("attaching clean ActivityType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActivityTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActivityTypeDAO) ctx.getBean("ActivityTypeDAO");
	}
}