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
 * AppointmentStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AppointmentStatus
 * @author MyEclipse Persistence Tools
 */

public class AppointmentStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AppointmentStatusDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(AppointmentStatus transientInstance) {
		log.debug("saving AppointmentStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppointmentStatus persistentInstance) {
		log.debug("deleting AppointmentStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppointmentStatus findById(java.lang.String id) {
		log.debug("getting AppointmentStatus instance with id: " + id);
		try {
			AppointmentStatus instance = (AppointmentStatus) getHibernateTemplate()
					.get("com.wtc.hcis.da.AppointmentStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppointmentStatus instance) {
		log.debug("finding AppointmentStatus instance by example");
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
		log.debug("finding AppointmentStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AppointmentStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all AppointmentStatus instances");
		try {
			String queryString = "from AppointmentStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppointmentStatus merge(AppointmentStatus detachedInstance) {
		log.debug("merging AppointmentStatus instance");
		try {
			AppointmentStatus result = (AppointmentStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppointmentStatus instance) {
		log.debug("attaching dirty AppointmentStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppointmentStatus instance) {
		log.debug("attaching clean AppointmentStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AppointmentStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AppointmentStatusDAO) ctx.getBean("AppointmentStatusDAO");
	}
}