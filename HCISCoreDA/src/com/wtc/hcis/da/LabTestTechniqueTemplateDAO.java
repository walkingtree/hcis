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
 * LabTestTechniqueTemplate entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.LabTestTechniqueTemplate
 * @author MyEclipse Persistence Tools
 */

public class LabTestTechniqueTemplateDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabTestTechniqueTemplateDAO.class);
	// property constants
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTestTechniqueTemplate transientInstance) {
		log.debug("saving LabTestTechniqueTemplate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTestTechniqueTemplate persistentInstance) {
		log.debug("deleting LabTestTechniqueTemplate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTestTechniqueTemplate findById(
			com.wtc.hcis.da.LabTestTechniqueTemplateId id) {
		log.debug("getting LabTestTechniqueTemplate instance with id: " + id);
		try {
			LabTestTechniqueTemplate instance = (LabTestTechniqueTemplate) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTestTechniqueTemplate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTestTechniqueTemplate instance) {
		log.debug("finding LabTestTechniqueTemplate instance by example");
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
		log.debug("finding LabTestTechniqueTemplate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTestTechniqueTemplate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabTestTechniqueTemplate instances");
		try {
			String queryString = "from LabTestTechniqueTemplate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTestTechniqueTemplate merge(
			LabTestTechniqueTemplate detachedInstance) {
		log.debug("merging LabTestTechniqueTemplate instance");
		try {
			LabTestTechniqueTemplate result = (LabTestTechniqueTemplate) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTestTechniqueTemplate instance) {
		log.debug("attaching dirty LabTestTechniqueTemplate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTestTechniqueTemplate instance) {
		log.debug("attaching clean LabTestTechniqueTemplate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestTechniqueTemplateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTestTechniqueTemplateDAO) ctx
				.getBean("LabTestTechniqueTemplateDAO");
	}
}