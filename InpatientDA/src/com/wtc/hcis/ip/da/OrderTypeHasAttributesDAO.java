package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * OrderTypeHasAttributes entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OrderTypeHasAttributes
 * @author MyEclipse Persistence Tools
 */

public class OrderTypeHasAttributesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OrderTypeHasAttributesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String IS_MANDATORY = "isMandatory";
	public static final String SEQUENCE_NUMBER = "sequenceNumber";

	protected void initDao() {
		// do nothing
	}

	public void save(OrderTypeHasAttributes transientInstance) {
		log.debug("saving OrderTypeHasAttributes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OrderTypeHasAttributes persistentInstance) {
		log.debug("deleting OrderTypeHasAttributes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OrderTypeHasAttributes findById(
			com.wtc.hcis.ip.da.OrderTypeHasAttributesId id) {
		log.debug("getting OrderTypeHasAttributes instance with id: " + id);
		try {
			OrderTypeHasAttributes instance = (OrderTypeHasAttributes) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OrderTypeHasAttributes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OrderTypeHasAttributes instance) {
		log.debug("finding OrderTypeHasAttributes instance by example");
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
		log.debug("finding OrderTypeHasAttributes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OrderTypeHasAttributes as model where model."
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

	public List findByIsMandatory(Object isMandatory) {
		return findByProperty(IS_MANDATORY, isMandatory);
	}

	public List findBySequenceNumber(Object sequenceNumber) {
		return findByProperty(SEQUENCE_NUMBER, sequenceNumber);
	}

	public List findAll() {
		log.debug("finding all OrderTypeHasAttributes instances");
		try {
			String queryString = "from OrderTypeHasAttributes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OrderTypeHasAttributes merge(OrderTypeHasAttributes detachedInstance) {
		log.debug("merging OrderTypeHasAttributes instance");
		try {
			OrderTypeHasAttributes result = (OrderTypeHasAttributes) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OrderTypeHasAttributes instance) {
		log.debug("attaching dirty OrderTypeHasAttributes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OrderTypeHasAttributes instance) {
		log.debug("attaching clean OrderTypeHasAttributes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OrderTypeHasAttributesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OrderTypeHasAttributesDAO) ctx
				.getBean("OrderTypeHasAttributesDAO");
	}
}