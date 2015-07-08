package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DischargeActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DischargeActivity
 * @author MyEclipse Persistence Tools
 */

public class DischargeActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DischargeActivityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(DischargeActivity transientInstance) {
		log.debug("saving DischargeActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DischargeActivity persistentInstance) {
		log.debug("deleting DischargeActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DischargeActivity findById(com.wtc.hcis.ip.da.DischargeActivityId id) {
		log.debug("getting DischargeActivity instance with id: " + id);
		try {
			DischargeActivity instance = (DischargeActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DischargeActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DischargeActivity instance) {
		log.debug("finding DischargeActivity instance by example");
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
		log.debug("finding DischargeActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DischargeActivity as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all DischargeActivity instances");
		try {
			String queryString = "from DischargeActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DischargeActivity merge(DischargeActivity detachedInstance) {
		log.debug("merging DischargeActivity instance");
		try {
			DischargeActivity result = (DischargeActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DischargeActivity instance) {
		log.debug("attaching dirty DischargeActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DischargeActivity instance) {
		log.debug("attaching clean DischargeActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DischargeActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DischargeActivityDAO) ctx.getBean("DischargeActivityDAO");
	}
}