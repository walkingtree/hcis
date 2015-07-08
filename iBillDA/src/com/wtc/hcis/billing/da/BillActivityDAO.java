package com.wtc.hcis.billing.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillActivity
 * @author MyEclipse Persistence Tools
 */

public class BillActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillActivityDAO.class);
	// property constants
	public static final String ACTIVITY_TYPE_DESC = "activityTypeDesc";
	public static final String ACTIVITY_ORDER = "activityOrder";

	protected void initDao() {
		// do nothing
	}

	public void save(BillActivity transientInstance) {
		log.debug("saving BillActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillActivity persistentInstance) {
		log.debug("deleting BillActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillActivity findById(java.lang.String id) {
		log.debug("getting BillActivity instance with id: " + id);
		try {
			BillActivity instance = (BillActivity) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.BillActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillActivity instance) {
		log.debug("finding BillActivity instance by example");
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
		log.debug("finding BillActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillActivity as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActivityTypeDesc(Object activityTypeDesc) {
		return findByProperty(ACTIVITY_TYPE_DESC, activityTypeDesc);
	}

	public List findByActivityOrder(Object activityOrder) {
		return findByProperty(ACTIVITY_ORDER, activityOrder);
	}

	public List findAll() {
		log.debug("finding all BillActivity instances");
		try {
			String queryString = "from BillActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillActivity merge(BillActivity detachedInstance) {
		log.debug("merging BillActivity instance");
		try {
			BillActivity result = (BillActivity) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillActivity instance) {
		log.debug("attaching dirty BillActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillActivity instance) {
		log.debug("attaching clean BillActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillActivityDAO) ctx.getBean("BillActivityDAO");
	}
}