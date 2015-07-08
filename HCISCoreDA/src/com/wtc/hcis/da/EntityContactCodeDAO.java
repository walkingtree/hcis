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
 * EntityContactCode entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.EntityContactCode
 * @author MyEclipse Persistence Tools
 */

public class EntityContactCodeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(EntityContactCodeDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SAME_AS_CURRENT = "sameAsCurrent";
	public static final String ACTIVE = "active";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(EntityContactCode transientInstance) {
		log.debug("saving EntityContactCode instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(EntityContactCode persistentInstance) {
		log.debug("deleting EntityContactCode instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public EntityContactCode findById(com.wtc.hcis.da.EntityContactCodeId id) {
		log.debug("getting EntityContactCode instance with id: " + id);
		try {
			EntityContactCode instance = (EntityContactCode) getHibernateTemplate()
					.get("com.wtc.hcis.da.EntityContactCode", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(EntityContactCode instance) {
		log.debug("finding EntityContactCode instance by example");
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
		log.debug("finding EntityContactCode instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from EntityContactCode as model where model."
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

	public List findBySameAsCurrent(Object sameAsCurrent) {
		return findByProperty(SAME_AS_CURRENT, sameAsCurrent);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all EntityContactCode instances");
		try {
			String queryString = "from EntityContactCode";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public EntityContactCode merge(EntityContactCode detachedInstance) {
		log.debug("merging EntityContactCode instance");
		try {
			EntityContactCode result = (EntityContactCode) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(EntityContactCode instance) {
		log.debug("attaching dirty EntityContactCode instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(EntityContactCode instance) {
		log.debug("attaching clean EntityContactCode instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntityContactCodeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (EntityContactCodeDAO) ctx.getBean("EntityContactCodeDAO");
	}
}