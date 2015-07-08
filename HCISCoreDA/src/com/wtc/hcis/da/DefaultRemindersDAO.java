package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DefaultReminders entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DefaultReminders
 * @author MyEclipse Persistence Tools
 */

public class DefaultRemindersDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DefaultRemindersDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REMINDER_TIME = "reminderTime";
	public static final String DURATION_CODE = "durationCode";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DefaultReminders transientInstance) {
		log.debug("saving DefaultReminders instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DefaultReminders persistentInstance) {
		log.debug("deleting DefaultReminders instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DefaultReminders findById(com.wtc.hcis.da.DefaultRemindersId id) {
		log.debug("getting DefaultReminders instance with id: " + id);
		try {
			DefaultReminders instance = (DefaultReminders) getHibernateTemplate()
					.get("com.wtc.hcis.da.DefaultReminders", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DefaultReminders instance) {
		log.debug("finding DefaultReminders instance by example");
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
		log.debug("finding DefaultReminders instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DefaultReminders as model where model."
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

	public List findByReminderTime(Object reminderTime) {
		return findByProperty(REMINDER_TIME, reminderTime);
	}

	public List findByDurationCode(Object durationCode) {
		return findByProperty(DURATION_CODE, durationCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all DefaultReminders instances");
		try {
			String queryString = "from DefaultReminders";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DefaultReminders merge(DefaultReminders detachedInstance) {
		log.debug("merging DefaultReminders instance");
		try {
			DefaultReminders result = (DefaultReminders) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DefaultReminders instance) {
		log.debug("attaching dirty DefaultReminders instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DefaultReminders instance) {
		log.debug("attaching clean DefaultReminders instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DefaultRemindersDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DefaultRemindersDAO) ctx.getBean("DefaultRemindersDAO");
	}
}