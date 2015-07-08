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
 * LabPatientTestAttributeValue entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.LabPatientTestAttributeValue
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestAttributeValueDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabPatientTestAttributeValueDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ATTRIBUTE_VALUE = "attributeValue";
	public static final String REMARKS = "remarks";
	public static final String COMPARISON_IND = "comparisonInd";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabPatientTestAttributeValue transientInstance) {
		log.debug("saving LabPatientTestAttributeValue instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabPatientTestAttributeValue persistentInstance) {
		log.debug("deleting LabPatientTestAttributeValue instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabPatientTestAttributeValue findById(
			com.wtc.hcis.da.LabPatientTestAttributeValueId id) {
		log.debug("getting LabPatientTestAttributeValue instance with id: "
				+ id);
		try {
			LabPatientTestAttributeValue instance = (LabPatientTestAttributeValue) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabPatientTestAttributeValue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabPatientTestAttributeValue instance) {
		log.debug("finding LabPatientTestAttributeValue instance by example");
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
		log
				.debug("finding LabPatientTestAttributeValue instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabPatientTestAttributeValue as model where model."
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

	public List findByAttributeValue(Object attributeValue) {
		return findByProperty(ATTRIBUTE_VALUE, attributeValue);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByComparisonInd(Object comparisonInd) {
		return findByProperty(COMPARISON_IND, comparisonInd);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByLastModifiedBy(Object lastModifiedBy) {
		return findByProperty(LAST_MODIFIED_BY, lastModifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabPatientTestAttributeValue instances");
		try {
			String queryString = "from LabPatientTestAttributeValue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabPatientTestAttributeValue merge(
			LabPatientTestAttributeValue detachedInstance) {
		log.debug("merging LabPatientTestAttributeValue instance");
		try {
			LabPatientTestAttributeValue result = (LabPatientTestAttributeValue) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabPatientTestAttributeValue instance) {
		log.debug("attaching dirty LabPatientTestAttributeValue instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabPatientTestAttributeValue instance) {
		log.debug("attaching clean LabPatientTestAttributeValue instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabPatientTestAttributeValueDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabPatientTestAttributeValueDAO) ctx
				.getBean("LabPatientTestAttributeValueDAO");
	}
}