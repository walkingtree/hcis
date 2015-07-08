package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClaimDocument entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ClaimDocument
 * @author MyEclipse Persistence Tools
 */

public class ClaimDocumentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ClaimDocumentDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DOCUMENT_PATH = "documentPath";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ClaimDocument transientInstance) {
		log.debug("saving ClaimDocument instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClaimDocument persistentInstance) {
		log.debug("deleting ClaimDocument instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClaimDocument findById(com.wtc.hcis.ip.da.ClaimDocumentId id) {
		log.debug("getting ClaimDocument instance with id: " + id);
		try {
			ClaimDocument instance = (ClaimDocument) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.ClaimDocument", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ClaimDocument instance) {
		log.debug("finding ClaimDocument instance by example");
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
		log.debug("finding ClaimDocument instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ClaimDocument as model where model."
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

	public List findByDocumentPath(Object documentPath) {
		return findByProperty(DOCUMENT_PATH, documentPath);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all ClaimDocument instances");
		try {
			String queryString = "from ClaimDocument";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClaimDocument merge(ClaimDocument detachedInstance) {
		log.debug("merging ClaimDocument instance");
		try {
			ClaimDocument result = (ClaimDocument) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClaimDocument instance) {
		log.debug("attaching dirty ClaimDocument instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClaimDocument instance) {
		log.debug("attaching clean ClaimDocument instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClaimDocumentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ClaimDocumentDAO) ctx.getBean("ClaimDocumentDAO");
	}
}