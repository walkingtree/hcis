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
 * ReferralCommissionProcess entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.ReferralCommissionProcess
 * @author MyEclipse Persistence Tools
 */

public class ReferralCommissionProcessDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ReferralCommissionProcessDAO.class);
	// property constants
	public static final String COMMISSION_TYPE_DESC = "commissionTypeDesc";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(ReferralCommissionProcess transientInstance) {
		log.debug("saving ReferralCommissionProcess instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReferralCommissionProcess persistentInstance) {
		log.debug("deleting ReferralCommissionProcess instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferralCommissionProcess findById(java.lang.String id) {
		log.debug("getting ReferralCommissionProcess instance with id: " + id);
		try {
			ReferralCommissionProcess instance = (ReferralCommissionProcess) getHibernateTemplate()
					.get("com.wtc.hcis.da.ReferralCommissionProcess", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReferralCommissionProcess instance) {
		log.debug("finding ReferralCommissionProcess instance by example");
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
		log.debug("finding ReferralCommissionProcess instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReferralCommissionProcess as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCommissionTypeDesc(Object commissionTypeDesc) {
		return findByProperty(COMMISSION_TYPE_DESC, commissionTypeDesc);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all ReferralCommissionProcess instances");
		try {
			String queryString = "from ReferralCommissionProcess";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReferralCommissionProcess merge(
			ReferralCommissionProcess detachedInstance) {
		log.debug("merging ReferralCommissionProcess instance");
		try {
			ReferralCommissionProcess result = (ReferralCommissionProcess) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferralCommissionProcess instance) {
		log.debug("attaching dirty ReferralCommissionProcess instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferralCommissionProcess instance) {
		log.debug("attaching clean ReferralCommissionProcess instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferralCommissionProcessDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReferralCommissionProcessDAO) ctx
				.getBean("ReferralCommissionProcessDAO");
	}
}