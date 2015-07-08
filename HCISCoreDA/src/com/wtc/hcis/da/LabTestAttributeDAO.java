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
 * LabTestAttribute entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTestAttribute
 * @author MyEclipse Persistence Tools
 */

public class LabTestAttributeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LabTestAttributeDAO.class);
	// property constants
	public static final String ATTRIBUTE_NAME = "attributeName";
	public static final String TYPE = "type";
	public static final String MIN_VALUE = "minValue";
	public static final String MAX_VALUE = "maxValue";
	public static final String UNIT = "unit";
	public static final String OBSERVATION_VALUE = "observationValue";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTestAttribute transientInstance) {
		log.debug("saving LabTestAttribute instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTestAttribute persistentInstance) {
		log.debug("deleting LabTestAttribute instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTestAttribute findById(java.lang.String id) {
		log.debug("getting LabTestAttribute instance with id: " + id);
		try {
			LabTestAttribute instance = (LabTestAttribute) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTestAttribute", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTestAttribute instance) {
		log.debug("finding LabTestAttribute instance by example");
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
		log.debug("finding LabTestAttribute instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTestAttribute as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAttributeName(Object attributeName) {
		return findByProperty(ATTRIBUTE_NAME, attributeName);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByMinValue(Object minValue) {
		return findByProperty(MIN_VALUE, minValue);
	}

	public List findByMaxValue(Object maxValue) {
		return findByProperty(MAX_VALUE, maxValue);
	}

	public List findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List findByObservationValue(Object observationValue) {
		return findByProperty(OBSERVATION_VALUE, observationValue);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabTestAttribute instances");
		try {
			String queryString = "from LabTestAttribute";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTestAttribute merge(LabTestAttribute detachedInstance) {
		log.debug("merging LabTestAttribute instance");
		try {
			LabTestAttribute result = (LabTestAttribute) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTestAttribute instance) {
		log.debug("attaching dirty LabTestAttribute instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTestAttribute instance) {
		log.debug("attaching clean LabTestAttribute instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestAttributeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTestAttributeDAO) ctx.getBean("LabTestAttributeDAO");
	}
}