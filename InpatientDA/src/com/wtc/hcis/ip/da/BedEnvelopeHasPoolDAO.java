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
 * BedEnvelopeHasPool entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedEnvelopeHasPool
 * @author MyEclipse Persistence Tools
 */

public class BedEnvelopeHasPoolDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BedEnvelopeHasPoolDAO.class);
	// property constants
	public static final String VERSION = "version";

	protected void initDao() {
		// do nothing
	}

	public void save(BedEnvelopeHasPool transientInstance) {
		log.debug("saving BedEnvelopeHasPool instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedEnvelopeHasPool persistentInstance) {
		log.debug("deleting BedEnvelopeHasPool instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedEnvelopeHasPool findById(
			com.wtc.hcis.ip.da.BedEnvelopeHasPoolId id) {
		log.debug("getting BedEnvelopeHasPool instance with id: " + id);
		try {
			BedEnvelopeHasPool instance = (BedEnvelopeHasPool) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedEnvelopeHasPool", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedEnvelopeHasPool instance) {
		log.debug("finding BedEnvelopeHasPool instance by example");
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
		log.debug("finding BedEnvelopeHasPool instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedEnvelopeHasPool as model where model."
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

	public List findAll() {
		log.debug("finding all BedEnvelopeHasPool instances");
		try {
			String queryString = "from BedEnvelopeHasPool";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedEnvelopeHasPool merge(BedEnvelopeHasPool detachedInstance) {
		log.debug("merging BedEnvelopeHasPool instance");
		try {
			BedEnvelopeHasPool result = (BedEnvelopeHasPool) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedEnvelopeHasPool instance) {
		log.debug("attaching dirty BedEnvelopeHasPool instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedEnvelopeHasPool instance) {
		log.debug("attaching clean BedEnvelopeHasPool instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedEnvelopeHasPoolDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedEnvelopeHasPoolDAO) ctx.getBean("BedEnvelopeHasPoolDAO");
	}
}