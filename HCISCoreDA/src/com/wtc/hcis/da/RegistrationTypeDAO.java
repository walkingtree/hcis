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
 * RegistrationType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.RegistrationType
 * @author MyEclipse Persistence Tools
 */

public class RegistrationTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RegistrationTypeDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(RegistrationType transientInstance) {
		log.debug("saving RegistrationType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RegistrationType persistentInstance) {
		log.debug("deleting RegistrationType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegistrationType findById(java.lang.String id) {
		log.debug("getting RegistrationType instance with id: " + id);
		try {
			RegistrationType instance = (RegistrationType) getHibernateTemplate()
					.get("com.wtc.hcis.da.RegistrationType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RegistrationType instance) {
		log.debug("finding RegistrationType instance by example");
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
		log.debug("finding RegistrationType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RegistrationType as model where model."
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
		log.debug("finding all RegistrationType instances");
		try {
			String queryString = "from RegistrationType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RegistrationType merge(RegistrationType detachedInstance) {
		log.debug("merging RegistrationType instance");
		try {
			RegistrationType result = (RegistrationType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RegistrationType instance) {
		log.debug("attaching dirty RegistrationType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegistrationType instance) {
		log.debug("attaching clean RegistrationType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RegistrationTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RegistrationTypeDAO) ctx.getBean("RegistrationTypeDAO");
	}
}