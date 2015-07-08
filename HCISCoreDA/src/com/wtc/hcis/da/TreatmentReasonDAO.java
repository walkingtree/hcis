package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * TreatmentReason entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.TreatmentReason
 * @author MyEclipse Persistence Tools
 */

public class TreatmentReasonDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(TreatmentReasonDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(TreatmentReason transientInstance) {
		log.debug("saving TreatmentReason instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TreatmentReason persistentInstance) {
		log.debug("deleting TreatmentReason instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TreatmentReason findById(java.lang.String id) {
		log.debug("getting TreatmentReason instance with id: " + id);
		try {
			TreatmentReason instance = (TreatmentReason) getHibernateTemplate()
					.get("com.wtc.hcis.da.TreatmentReason", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TreatmentReason instance) {
		log.debug("finding TreatmentReason instance by example");
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
		log.debug("finding TreatmentReason instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TreatmentReason as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all TreatmentReason instances");
		try {
			String queryString = "from TreatmentReason";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TreatmentReason merge(TreatmentReason detachedInstance) {
		log.debug("merging TreatmentReason instance");
		try {
			TreatmentReason result = (TreatmentReason) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TreatmentReason instance) {
		log.debug("attaching dirty TreatmentReason instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TreatmentReason instance) {
		log.debug("attaching clean TreatmentReason instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TreatmentReasonDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TreatmentReasonDAO) ctx.getBean("TreatmentReasonDAO");
	}
}