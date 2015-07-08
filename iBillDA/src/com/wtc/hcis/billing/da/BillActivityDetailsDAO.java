package com.wtc.hcis.billing.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillActivityDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillActivityDetails
 * @author MyEclipse Persistence Tools
 */

public class BillActivityDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(BillActivityDetailsDAO.class);
	// property constants
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(BillActivityDetails transientInstance) {
		log.debug("saving BillActivityDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillActivityDetails persistentInstance) {
		log.debug("deleting BillActivityDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillActivityDetails findById(java.lang.Long id) {
		log.debug("getting BillActivityDetails instance with id: " + id);
		try {
			BillActivityDetails instance = (BillActivityDetails) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.BillActivityDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillActivityDetails instance) {
		log.debug("finding BillActivityDetails instance by example");
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
		log.debug("finding BillActivityDetails instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillActivityDetails as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all BillActivityDetails instances");
		try {
			String queryString = "from BillActivityDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillActivityDetails merge(BillActivityDetails detachedInstance) {
		log.debug("merging BillActivityDetails instance");
		try {
			BillActivityDetails result = (BillActivityDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillActivityDetails instance) {
		log.debug("attaching dirty BillActivityDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillActivityDetails instance) {
		log.debug("attaching clean BillActivityDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillActivityDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillActivityDetailsDAO) ctx.getBean("BillActivityDetailsDAO");
	}
}