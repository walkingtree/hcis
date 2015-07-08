package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DischargeOrder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DischargeOrder
 * @author MyEclipse Persistence Tools
 */

public class DischargeOrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DischargeOrderDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ORDER_REQUESTED_BY = "orderRequestedBy";
	public static final String APPROVED_BY = "approvedBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String DISCHARGE_SUMMARY = "dischargeSummary";

	protected void initDao() {
		// do nothing
	}

	public void save(DischargeOrder transientInstance) {
		log.debug("saving DischargeOrder instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DischargeOrder persistentInstance) {
		log.debug("deleting DischargeOrder instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DischargeOrder findById(java.lang.Integer id) {
		log.debug("getting DischargeOrder instance with id: " + id);
		try {
			DischargeOrder instance = (DischargeOrder) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DischargeOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DischargeOrder instance) {
		log.debug("finding DischargeOrder instance by example");
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
		log.debug("finding DischargeOrder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DischargeOrder as model where model."
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

	public List findByOrderRequestedBy(Object orderRequestedBy) {
		return findByProperty(ORDER_REQUESTED_BY, orderRequestedBy);
	}

	public List findByApprovedBy(Object approvedBy) {
		return findByProperty(APPROVED_BY, approvedBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findByDischargeSummary(Object dischargeSummary) {
		return findByProperty(DISCHARGE_SUMMARY, dischargeSummary);
	}

	public List findAll() {
		log.debug("finding all DischargeOrder instances");
		try {
			String queryString = "from DischargeOrder";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DischargeOrder merge(DischargeOrder detachedInstance) {
		log.debug("merging DischargeOrder instance");
		try {
			DischargeOrder result = (DischargeOrder) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DischargeOrder instance) {
		log.debug("attaching dirty DischargeOrder instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DischargeOrder instance) {
		log.debug("attaching clean DischargeOrder instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DischargeOrderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DischargeOrderDAO) ctx.getBean("DischargeOrderDAO");
	}
}