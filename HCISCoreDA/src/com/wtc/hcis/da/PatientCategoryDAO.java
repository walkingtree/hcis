package com.wtc.hcis.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PatientCategory entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.PatientCategory
 * @author MyEclipse Persistence Tools
 */

public class PatientCategoryDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PatientCategoryDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String DEFAULT_CODE = "defaultCode";

	protected void initDao() {
		// do nothing
	}

	public void save(PatientCategory transientInstance) {
		log.debug("saving PatientCategory instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PatientCategory persistentInstance) {
		log.debug("deleting PatientCategory instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PatientCategory findById(java.lang.String id) {
		log.debug("getting PatientCategory instance with id: " + id);
		try {
			PatientCategory instance = (PatientCategory) getHibernateTemplate()
					.get("com.wtc.hcis.da.PatientCategory", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PatientCategory instance) {
		log.debug("finding PatientCategory instance by example");
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
		log.debug("finding PatientCategory instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PatientCategory as model where model."
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

	public List findByDefaultCode(Object defaultCode) {
		return findByProperty(DEFAULT_CODE, defaultCode);
	}

	public List findAll() {
		log.debug("finding all PatientCategory instances");
		try {
			String queryString = "from PatientCategory";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PatientCategory merge(PatientCategory detachedInstance) {
		log.debug("merging PatientCategory instance");
		try {
			PatientCategory result = (PatientCategory) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PatientCategory instance) {
		log.debug("attaching dirty PatientCategory instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PatientCategory instance) {
		log.debug("attaching clean PatientCategory instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientCategoryDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PatientCategoryDAO) ctx.getBean("PatientCategoryDAO");
	}
}