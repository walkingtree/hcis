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
 * AppEntities entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AppEntities
 * @author MyEclipse Persistence Tools
 */

public class AppEntitiesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AppEntitiesDAO.class);
	// property constants
	public static final String ENTITY_NAME = "entityName";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(AppEntities transientInstance) {
		log.debug("saving AppEntities instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AppEntities persistentInstance) {
		log.debug("deleting AppEntities instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AppEntities findById(java.lang.String id) {
		log.debug("getting AppEntities instance with id: " + id);
		try {
			AppEntities instance = (AppEntities) getHibernateTemplate().get(
					"com.wtc.hcis.da.AppEntities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AppEntities instance) {
		log.debug("finding AppEntities instance by example");
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
		log.debug("finding AppEntities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AppEntities as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEntityName(Object entityName) {
		return findByProperty(ENTITY_NAME, entityName);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all AppEntities instances");
		try {
			String queryString = "from AppEntities";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AppEntities merge(AppEntities detachedInstance) {
		log.debug("merging AppEntities instance");
		try {
			AppEntities result = (AppEntities) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AppEntities instance) {
		log.debug("attaching dirty AppEntities instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AppEntities instance) {
		log.debug("attaching clean AppEntities instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AppEntitiesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AppEntitiesDAO) ctx.getBean("AppEntitiesDAO");
	}
}