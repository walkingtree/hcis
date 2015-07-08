package com.wtc.hcis.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReminderOptions entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ReminderOptions
 * @author MyEclipse Persistence Tools
 */

public class ReminderOptionsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ReminderOptionsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(ReminderOptions transientInstance) {
		log.debug("saving ReminderOptions instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReminderOptions persistentInstance) {
		log.debug("deleting ReminderOptions instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReminderOptions findById(java.lang.String id) {
		log.debug("getting ReminderOptions instance with id: " + id);
		try {
			ReminderOptions instance = (ReminderOptions) getHibernateTemplate()
					.get("com.wtc.hcis.da.ReminderOptions", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReminderOptions instance) {
		log.debug("finding ReminderOptions instance by example");
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
		log.debug("finding ReminderOptions instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReminderOptions as model where model."
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all ReminderOptions instances");
		try {
			String queryString = "from ReminderOptions";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReminderOptions merge(ReminderOptions detachedInstance) {
		log.debug("merging ReminderOptions instance");
		try {
			ReminderOptions result = (ReminderOptions) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReminderOptions instance) {
		log.debug("attaching dirty ReminderOptions instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReminderOptions instance) {
		log.debug("attaching clean ReminderOptions instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReminderOptionsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReminderOptionsDAO) ctx.getBean("ReminderOptionsDAO");
	}
}