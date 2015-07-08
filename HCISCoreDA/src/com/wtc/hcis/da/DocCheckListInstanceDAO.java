package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocCheckListInstance entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DocCheckListInstance
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListInstanceDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DocCheckListInstanceDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REFERENCE_TYPE = "referenceType";
	public static final String REFERENCE_NUMBER = "referenceNumber";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DocCheckListInstance transientInstance) {
		log.debug("saving DocCheckListInstance instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocCheckListInstance persistentInstance) {
		log.debug("deleting DocCheckListInstance instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocCheckListInstance findById(java.lang.Long id) {
		log.debug("getting DocCheckListInstance instance with id: " + id);
		try {
			DocCheckListInstance instance = (DocCheckListInstance) getHibernateTemplate()
					.get("com.wtc.hcis.da.DocCheckListInstance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocCheckListInstance instance) {
		log.debug("finding DocCheckListInstance instance by example");
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
		log.debug("finding DocCheckListInstance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocCheckListInstance as model where model."
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

	public List findByReferenceType(Object referenceType) {
		return findByProperty(REFERENCE_TYPE, referenceType);
	}

	public List findByReferenceNumber(Object referenceNumber) {
		return findByProperty(REFERENCE_NUMBER, referenceNumber);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all DocCheckListInstance instances");
		try {
			String queryString = "from DocCheckListInstance";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocCheckListInstance merge(DocCheckListInstance detachedInstance) {
		log.debug("merging DocCheckListInstance instance");
		try {
			DocCheckListInstance result = (DocCheckListInstance) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocCheckListInstance instance) {
		log.debug("attaching dirty DocCheckListInstance instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocCheckListInstance instance) {
		log.debug("attaching clean DocCheckListInstance instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocCheckListInstanceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocCheckListInstanceDAO) ctx.getBean("DocCheckListInstanceDAO");
	}
}