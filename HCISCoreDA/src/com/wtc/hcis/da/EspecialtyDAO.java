package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Especialty entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Especialty
 * @author MyEclipse Persistence Tools
 */

public class EspecialtyDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(EspecialtyDAO.class);
	// property constants
	public static final String ESPECIALTY_NAME = "especialtyName";
	public static final String ACTIVE = "active";
	public static final String ESPECILALTY = "especilalty";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Especialty transientInstance) {
		log.debug("saving Especialty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Especialty persistentInstance) {
		log.debug("deleting Especialty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Especialty findById(java.lang.String id) {
		log.debug("getting Especialty instance with id: " + id);
		try {
			Especialty instance = (Especialty) getHibernateTemplate().get(
					"com.wtc.hcis.da.Especialty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Especialty instance) {
		log.debug("finding Especialty instance by example");
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
		log.debug("finding Especialty instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Especialty as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEspecialtyName(Object especialtyName) {
		return findByProperty(ESPECIALTY_NAME, especialtyName);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByEspecilalty(Object especilalty) {
		return findByProperty(ESPECILALTY, especilalty);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Especialty instances");
		try {
			String queryString = "from Especialty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Especialty merge(Especialty detachedInstance) {
		log.debug("merging Especialty instance");
		try {
			Especialty result = (Especialty) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Especialty instance) {
		log.debug("attaching dirty Especialty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Especialty instance) {
		log.debug("attaching clean Especialty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EspecialtyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EspecialtyDAO) ctx.getBean("EspecialtyDAO");
	}
}