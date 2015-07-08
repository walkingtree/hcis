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
 * ReferralPayable entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ReferralPayable
 * @author MyEclipse Persistence Tools
 */

public class ReferralPayableDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ReferralPayableDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String PAYABLE_AMT = "payableAmt";
	public static final String ACCOUNTED = "accounted";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ReferralPayable transientInstance) {
		log.debug("saving ReferralPayable instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReferralPayable persistentInstance) {
		log.debug("deleting ReferralPayable instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferralPayable findById(com.wtc.hcis.da.ReferralPayableId id) {
		log.debug("getting ReferralPayable instance with id: " + id);
		try {
			ReferralPayable instance = (ReferralPayable) getHibernateTemplate()
					.get("com.wtc.hcis.da.ReferralPayable", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReferralPayable instance) {
		log.debug("finding ReferralPayable instance by example");
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
		log.debug("finding ReferralPayable instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReferralPayable as model where model."
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

	public List findByPayableAmt(Object payableAmt) {
		return findByProperty(PAYABLE_AMT, payableAmt);
	}

	public List findByAccounted(Object accounted) {
		return findByProperty(ACCOUNTED, accounted);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all ReferralPayable instances");
		try {
			String queryString = "from ReferralPayable";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReferralPayable merge(ReferralPayable detachedInstance) {
		log.debug("merging ReferralPayable instance");
		try {
			ReferralPayable result = (ReferralPayable) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferralPayable instance) {
		log.debug("attaching dirty ReferralPayable instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferralPayable instance) {
		log.debug("attaching clean ReferralPayable instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferralPayableDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReferralPayableDAO) ctx.getBean("ReferralPayableDAO");
	}
}