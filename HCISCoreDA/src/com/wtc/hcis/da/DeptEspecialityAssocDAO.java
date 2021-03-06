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
 * DeptEspecialityAssoc entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DeptEspecialityAssoc
 * @author MyEclipse Persistence Tools
 */

public class DeptEspecialityAssocDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DeptEspecialityAssocDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ACTIVE = "active";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(DeptEspecialityAssoc transientInstance) {
		log.debug("saving DeptEspecialityAssoc instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DeptEspecialityAssoc persistentInstance) {
		log.debug("deleting DeptEspecialityAssoc instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DeptEspecialityAssoc findById(
			com.wtc.hcis.da.DeptEspecialityAssocId id) {
		log.debug("getting DeptEspecialityAssoc instance with id: " + id);
		try {
			DeptEspecialityAssoc instance = (DeptEspecialityAssoc) getHibernateTemplate()
					.get("com.wtc.hcis.da.DeptEspecialityAssoc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DeptEspecialityAssoc instance) {
		log.debug("finding DeptEspecialityAssoc instance by example");
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
		log.debug("finding DeptEspecialityAssoc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DeptEspecialityAssoc as model where model."
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

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all DeptEspecialityAssoc instances");
		try {
			String queryString = "from DeptEspecialityAssoc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DeptEspecialityAssoc merge(DeptEspecialityAssoc detachedInstance) {
		log.debug("merging DeptEspecialityAssoc instance");
		try {
			DeptEspecialityAssoc result = (DeptEspecialityAssoc) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DeptEspecialityAssoc instance) {
		log.debug("attaching dirty DeptEspecialityAssoc instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DeptEspecialityAssoc instance) {
		log.debug("attaching clean DeptEspecialityAssoc instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeptEspecialityAssocDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DeptEspecialityAssocDAO) ctx.getBean("DeptEspecialityAssocDAO");
	}
}