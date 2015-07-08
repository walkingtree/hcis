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
 * LabTestTemplate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTestTemplate
 * @author MyEclipse Persistence Tools
 */

public class LabTestTemplateDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LabTestTemplateDAO.class);
	// property constants
	public static final String TEMPLATE_NAME = "templateName";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTestTemplate transientInstance) {
		log.debug("saving LabTestTemplate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTestTemplate persistentInstance) {
		log.debug("deleting LabTestTemplate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTestTemplate findById(java.lang.Integer id) {
		log.debug("getting LabTestTemplate instance with id: " + id);
		try {
			LabTestTemplate instance = (LabTestTemplate) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTestTemplate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTestTemplate instance) {
		log.debug("finding LabTestTemplate instance by example");
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
		log.debug("finding LabTestTemplate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTestTemplate as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTemplateName(Object templateName) {
		return findByProperty(TEMPLATE_NAME, templateName);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByLastModifiedBy(Object lastModifiedBy) {
		return findByProperty(LAST_MODIFIED_BY, lastModifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabTestTemplate instances");
		try {
			String queryString = "from LabTestTemplate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTestTemplate merge(LabTestTemplate detachedInstance) {
		log.debug("merging LabTestTemplate instance");
		try {
			LabTestTemplate result = (LabTestTemplate) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTestTemplate instance) {
		log.debug("attaching dirty LabTestTemplate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTestTemplate instance) {
		log.debug("attaching clean LabTestTemplate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestTemplateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTestTemplateDAO) ctx.getBean("LabTestTemplateDAO");
	}
}