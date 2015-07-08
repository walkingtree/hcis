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
 * EntityAcctMap entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.EntityAcctMap
 * @author MyEclipse Persistence Tools
 */

public class EntityAcctMapDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(EntityAcctMapDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String BUSINESS_PARTNER_ID = "businessPartnerId";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(EntityAcctMap transientInstance) {
		log.debug("saving EntityAcctMap instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EntityAcctMap persistentInstance) {
		log.debug("deleting EntityAcctMap instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EntityAcctMap findById(com.wtc.hcis.da.EntityAcctMapId id) {
		log.debug("getting EntityAcctMap instance with id: " + id);
		try {
			EntityAcctMap instance = (EntityAcctMap) getHibernateTemplate()
					.get("com.wtc.hcis.da.EntityAcctMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EntityAcctMap instance) {
		log.debug("finding EntityAcctMap instance by example");
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
		log.debug("finding EntityAcctMap instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EntityAcctMap as model where model."
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

	public List findByBusinessPartnerId(Object businessPartnerId) {
		return findByProperty(BUSINESS_PARTNER_ID, businessPartnerId);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all EntityAcctMap instances");
		try {
			String queryString = "from EntityAcctMap";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EntityAcctMap merge(EntityAcctMap detachedInstance) {
		log.debug("merging EntityAcctMap instance");
		try {
			EntityAcctMap result = (EntityAcctMap) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EntityAcctMap instance) {
		log.debug("attaching dirty EntityAcctMap instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EntityAcctMap instance) {
		log.debug("attaching clean EntityAcctMap instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntityAcctMapDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EntityAcctMapDAO) ctx.getBean("EntityAcctMapDAO");
	}
}