package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * RenderedService entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.RenderedService
 * @author MyEclipse Persistence Tools
 */

public class RenderedServiceDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(RenderedServiceDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REFERENCE_NBR = "referenceNbr";
	public static final String REFERENCE_TYPE = "referenceType";
	public static final String RENDERED_QTY = "renderedQty";
	public static final String BILL_NBR = "billNbr";
	public static final String SERVICE_CHARGE = "serviceCharge";
	public static final String RENDERED_BY = "renderedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(RenderedService transientInstance) {
		log.debug("saving RenderedService instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RenderedService persistentInstance) {
		log.debug("deleting RenderedService instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RenderedService findById(com.wtc.hcis.da.RenderedServiceId id) {
		log.debug("getting RenderedService instance with id: " + id);
		try {
			RenderedService instance = (RenderedService) getHibernateTemplate()
					.get("com.wtc.hcis.da.RenderedService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RenderedService instance) {
		log.debug("finding RenderedService instance by example");
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
		log.debug("finding RenderedService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RenderedService as model where model."
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

	public List findByReferenceNbr(Object referenceNbr) {
		return findByProperty(REFERENCE_NBR, referenceNbr);
	}

	public List findByReferenceType(Object referenceType) {
		return findByProperty(REFERENCE_TYPE, referenceType);
	}

	public List findByRenderedQty(Object renderedQty) {
		return findByProperty(RENDERED_QTY, renderedQty);
	}

	public List findByBillNbr(Object billNbr) {
		return findByProperty(BILL_NBR, billNbr);
	}

	public List findByServiceCharge(Object serviceCharge) {
		return findByProperty(SERVICE_CHARGE, serviceCharge);
	}

	public List findByRenderedBy(Object renderedBy) {
		return findByProperty(RENDERED_BY, renderedBy);
	}

	public List findAll() {
		log.debug("finding all RenderedService instances");
		try {
			String queryString = "from RenderedService";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RenderedService merge(RenderedService detachedInstance) {
		log.debug("merging RenderedService instance");
		try {
			RenderedService result = (RenderedService) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RenderedService instance) {
		log.debug("attaching dirty RenderedService instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RenderedService instance) {
		log.debug("attaching clean RenderedService instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RenderedServiceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (RenderedServiceDAO) ctx.getBean("RenderedServiceDAO");
	}
}