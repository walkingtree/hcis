package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BusinessRule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.BusinessRule
 * @author MyEclipse Persistence Tools
 */

public class BusinessRuleDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BusinessRuleDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String RULE = "rule";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(BusinessRule transientInstance) {
		log.debug("saving BusinessRule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BusinessRule persistentInstance) {
		log.debug("deleting BusinessRule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BusinessRule findById(com.wtc.hcis.da.BusinessRuleId id) {
		log.debug("getting BusinessRule instance with id: " + id);
		try {
			BusinessRule instance = (BusinessRule) getHibernateTemplate().get(
					"com.wtc.hcis.da.BusinessRule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BusinessRule instance) {
		log.debug("finding BusinessRule instance by example");
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
		log.debug("finding BusinessRule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BusinessRule as model where model."
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

	public List findByRule(Object rule) {
		return findByProperty(RULE, rule);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all BusinessRule instances");
		try {
			String queryString = "from BusinessRule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BusinessRule merge(BusinessRule detachedInstance) {
		log.debug("merging BusinessRule instance");
		try {
			BusinessRule result = (BusinessRule) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BusinessRule instance) {
		log.debug("attaching dirty BusinessRule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BusinessRule instance) {
		log.debug("attaching clean BusinessRule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BusinessRuleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BusinessRuleDAO) ctx.getBean("BusinessRuleDAO");
	}
}