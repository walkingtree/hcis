package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Reminders entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Reminders
 * @author MyEclipse Persistence Tools
 */

public class RemindersDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RemindersDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REMINDER_TIME = "reminderTime";

	protected void initDao() {
		// do nothing
	}

	public void save(Reminders transientInstance) {
		log.debug("saving Reminders instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reminders persistentInstance) {
		log.debug("deleting Reminders instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reminders findById(com.wtc.hcis.da.RemindersId id) {
		log.debug("getting Reminders instance with id: " + id);
		try {
			Reminders instance = (Reminders) getHibernateTemplate().get(
					"com.wtc.hcis.da.Reminders", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Reminders instance) {
		log.debug("finding Reminders instance by example");
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
		log.debug("finding Reminders instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reminders as model where model."
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

	public List findAll() {
		log.debug("finding all Reminders instances");
		try {
			String queryString = "from Reminders";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reminders merge(Reminders detachedInstance) {
		log.debug("merging Reminders instance");
		try {
			Reminders result = (Reminders) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reminders instance) {
		log.debug("attaching dirty Reminders instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reminders instance) {
		log.debug("attaching clean Reminders instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RemindersDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RemindersDAO) ctx.getBean("RemindersDAO");
	}
}