package com.wtc.hcis.ip.da;

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
 * OtNotesConfiguration entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtNotesConfiguration
 * @author MyEclipse Persistence Tools
 */

public class OtNotesConfigurationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtNotesConfigurationDAO.class);
	// property constants
	public static final String FIELD_NAME = "fieldName";
	public static final String FIELD_TYPE = "fieldType";
	public static final String DESCRIPTION = "description";
	public static final String SEQ_NBR = "seqNbr";
	public static final String ACTIVE = "active";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtNotesConfiguration transientInstance) {
		log.debug("saving OtNotesConfiguration instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtNotesConfiguration persistentInstance) {
		log.debug("deleting OtNotesConfiguration instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtNotesConfiguration findById(java.lang.String id) {
		log.debug("getting OtNotesConfiguration instance with id: " + id);
		try {
			OtNotesConfiguration instance = (OtNotesConfiguration) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtNotesConfiguration", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtNotesConfiguration instance) {
		log.debug("finding OtNotesConfiguration instance by example");
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
		log.debug("finding OtNotesConfiguration instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtNotesConfiguration as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFieldName(Object fieldName) {
		return findByProperty(FIELD_NAME, fieldName);
	}

	public List findByFieldType(Object fieldType) {
		return findByProperty(FIELD_TYPE, fieldType);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findBySeqNbr(Object seqNbr) {
		return findByProperty(SEQ_NBR, seqNbr);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtNotesConfiguration instances");
		try {
			String queryString = "from OtNotesConfiguration";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtNotesConfiguration merge(OtNotesConfiguration detachedInstance) {
		log.debug("merging OtNotesConfiguration instance");
		try {
			OtNotesConfiguration result = (OtNotesConfiguration) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtNotesConfiguration instance) {
		log.debug("attaching dirty OtNotesConfiguration instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtNotesConfiguration instance) {
		log.debug("attaching clean OtNotesConfiguration instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtNotesConfigurationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtNotesConfigurationDAO) ctx.getBean("OtNotesConfigurationDAO");
	}
}