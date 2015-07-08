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
 * DocVitalField entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DocVitalField
 * @author MyEclipse Persistence Tools
 */

public class DocVitalFieldDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DocVitalFieldDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String MEASUREMENT_UNIT_CD = "measurementUnitCd";
	public static final String FIELD_TYPE = "fieldType";
	public static final String FIELD_GROUP = "fieldGroup";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DocVitalField transientInstance) {
		log.debug("saving DocVitalField instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocVitalField persistentInstance) {
		log.debug("deleting DocVitalField instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocVitalField findById(java.lang.String id) {
		log.debug("getting DocVitalField instance with id: " + id);
		try {
			DocVitalField instance = (DocVitalField) getHibernateTemplate()
					.get("com.wtc.hcis.da.DocVitalField", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocVitalField instance) {
		log.debug("finding DocVitalField instance by example");
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
		log.debug("finding DocVitalField instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocVitalField as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByMeasurementUnitCd(Object measurementUnitCd) {
		return findByProperty(MEASUREMENT_UNIT_CD, measurementUnitCd);
	}

	public List findByFieldType(Object fieldType) {
		return findByProperty(FIELD_TYPE, fieldType);
	}

	public List findByFieldGroup(Object fieldGroup) {
		return findByProperty(FIELD_GROUP, fieldGroup);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DocVitalField instances");
		try {
			String queryString = "from DocVitalField";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocVitalField merge(DocVitalField detachedInstance) {
		log.debug("merging DocVitalField instance");
		try {
			DocVitalField result = (DocVitalField) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocVitalField instance) {
		log.debug("attaching dirty DocVitalField instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocVitalField instance) {
		log.debug("attaching clean DocVitalField instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocVitalFieldDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocVitalFieldDAO) ctx.getBean("DocVitalFieldDAO");
	}
}