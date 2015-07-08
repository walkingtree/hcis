package com.wtc.hcis.billing.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillProcessRouting entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillProcessRouting
 * @author MyEclipse Persistence Tools
 */

public class BillProcessRoutingDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BillProcessRoutingDAO.class);
	// property constants
	public static final String PROCESS_SEQ_NBR = "processSeqNbr";
	public static final String OVERRIDE_IMPL_CLASS_NAME = "overrideImplClassName";

	protected void initDao() {
		// do nothing
	}

	public void save(BillProcessRouting transientInstance) {
		log.debug("saving BillProcessRouting instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillProcessRouting persistentInstance) {
		log.debug("deleting BillProcessRouting instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillProcessRouting findById(
			com.wtc.hcis.billing.da.BillProcessRoutingId id) {
		log.debug("getting BillProcessRouting instance with id: " + id);
		try {
			BillProcessRouting instance = (BillProcessRouting) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.BillProcessRouting", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillProcessRouting instance) {
		log.debug("finding BillProcessRouting instance by example");
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
		log.debug("finding BillProcessRouting instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillProcessRouting as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProcessSeqNbr(Object processSeqNbr) {
		return findByProperty(PROCESS_SEQ_NBR, processSeqNbr);
	}

	public List findByOverrideImplClassName(Object overrideImplClassName) {
		return findByProperty(OVERRIDE_IMPL_CLASS_NAME, overrideImplClassName);
	}

	public List findAll() {
		log.debug("finding all BillProcessRouting instances");
		try {
			String queryString = "from BillProcessRouting";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillProcessRouting merge(BillProcessRouting detachedInstance) {
		log.debug("merging BillProcessRouting instance");
		try {
			BillProcessRouting result = (BillProcessRouting) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillProcessRouting instance) {
		log.debug("attaching dirty BillProcessRouting instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillProcessRouting instance) {
		log.debug("attaching clean BillProcessRouting instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillProcessRoutingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillProcessRoutingDAO) ctx.getBean("BillProcessRoutingDAO");
	}
}