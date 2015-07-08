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
 * LabTechniqueReagent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTechniqueReagent
 * @author MyEclipse Persistence Tools
 */

public class LabTechniqueReagentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabTechniqueReagentDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NAME = "name";
	public static final String IS_TECHNIQUE = "isTechnique";
	public static final String DESCRIPTION = "description";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTechniqueReagent transientInstance) {
		log.debug("saving LabTechniqueReagent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTechniqueReagent persistentInstance) {
		log.debug("deleting LabTechniqueReagent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTechniqueReagent findById(java.lang.Integer id) {
		log.debug("getting LabTechniqueReagent instance with id: " + id);
		try {
			LabTechniqueReagent instance = (LabTechniqueReagent) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTechniqueReagent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTechniqueReagent instance) {
		log.debug("finding LabTechniqueReagent instance by example");
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
		log.debug("finding LabTechniqueReagent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTechniqueReagent as model where model."
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

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByIsTechnique(Object isTechnique) {
		return findByProperty(IS_TECHNIQUE, isTechnique);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabTechniqueReagent instances");
		try {
			String queryString = "from LabTechniqueReagent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTechniqueReagent merge(LabTechniqueReagent detachedInstance) {
		log.debug("merging LabTechniqueReagent instance");
		try {
			LabTechniqueReagent result = (LabTechniqueReagent) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTechniqueReagent instance) {
		log.debug("attaching dirty LabTechniqueReagent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTechniqueReagent instance) {
		log.debug("attaching clean LabTechniqueReagent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTechniqueReagentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTechniqueReagentDAO) ctx.getBean("LabTechniqueReagentDAO");
	}
}