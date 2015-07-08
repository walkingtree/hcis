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
 * MotherTongue entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.MotherTongue
 * @author MyEclipse Persistence Tools
 */

public class MotherTongueDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MotherTongueDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String DEFAULT_CODE = "defaultCode";

	protected void initDao() {
		// do nothing
	}

	public void save(MotherTongue transientInstance) {
		log.debug("saving MotherTongue instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(MotherTongue persistentInstance) {
		log.debug("deleting MotherTongue instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public MotherTongue findById(java.lang.String id) {
		log.debug("getting MotherTongue instance with id: " + id);
		try {
			MotherTongue instance = (MotherTongue) getHibernateTemplate().get(
					"com.wtc.hcis.da.MotherTongue", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(MotherTongue instance) {
		log.debug("finding MotherTongue instance by example");
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
		log.debug("finding MotherTongue instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from MotherTongue as model where model."
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
		log.debug("finding all MotherTongue instances");
		try {
			String queryString = "from MotherTongue";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public MotherTongue merge(MotherTongue detachedInstance) {
		log.debug("merging MotherTongue instance");
		try {
			MotherTongue result = (MotherTongue) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(MotherTongue instance) {
		log.debug("attaching dirty MotherTongue instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(MotherTongue instance) {
		log.debug("attaching clean MotherTongue instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MotherTongueDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (MotherTongueDAO) ctx.getBean("MotherTongueDAO");
	}
}