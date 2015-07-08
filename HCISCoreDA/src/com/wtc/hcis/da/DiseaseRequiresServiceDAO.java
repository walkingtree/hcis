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
 * DiseaseRequiresService entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DiseaseRequiresService
 * @author MyEclipse Persistence Tools
 */

public class DiseaseRequiresServiceDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DiseaseRequiresServiceDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DiseaseRequiresService transientInstance) {
		log.debug("saving DiseaseRequiresService instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DiseaseRequiresService persistentInstance) {
		log.debug("deleting DiseaseRequiresService instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DiseaseRequiresService findById(
			com.wtc.hcis.da.DiseaseRequiresServiceId id) {
		log.debug("getting DiseaseRequiresService instance with id: " + id);
		try {
			DiseaseRequiresService instance = (DiseaseRequiresService) getHibernateTemplate()
					.get("com.wtc.hcis.da.DiseaseRequiresService", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DiseaseRequiresService instance) {
		log.debug("finding DiseaseRequiresService instance by example");
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
		log.debug("finding DiseaseRequiresService instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DiseaseRequiresService as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DiseaseRequiresService instances");
		try {
			String queryString = "from DiseaseRequiresService";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DiseaseRequiresService merge(DiseaseRequiresService detachedInstance) {
		log.debug("merging DiseaseRequiresService instance");
		try {
			DiseaseRequiresService result = (DiseaseRequiresService) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DiseaseRequiresService instance) {
		log.debug("attaching dirty DiseaseRequiresService instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DiseaseRequiresService instance) {
		log.debug("attaching clean DiseaseRequiresService instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DiseaseRequiresServiceDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DiseaseRequiresServiceDAO) ctx
				.getBean("DiseaseRequiresServiceDAO");
	}
}