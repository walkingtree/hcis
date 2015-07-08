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
 * Nationality entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Nationality
 * @author MyEclipse Persistence Tools
 */

public class NationalityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(NationalityDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String DEFAULT_CODE = "defaultCode";

	protected void initDao() {
		// do nothing
	}

	public void save(Nationality transientInstance) {
		log.debug("saving Nationality instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Nationality persistentInstance) {
		log.debug("deleting Nationality instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Nationality findById(java.lang.String id) {
		log.debug("getting Nationality instance with id: " + id);
		try {
			Nationality instance = (Nationality) getHibernateTemplate().get(
					"com.wtc.hcis.da.Nationality", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Nationality instance) {
		log.debug("finding Nationality instance by example");
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
		log.debug("finding Nationality instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Nationality as model where model."
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
		log.debug("finding all Nationality instances");
		try {
			String queryString = "from Nationality";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Nationality merge(Nationality detachedInstance) {
		log.debug("merging Nationality instance");
		try {
			Nationality result = (Nationality) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Nationality instance) {
		log.debug("attaching dirty Nationality instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Nationality instance) {
		log.debug("attaching clean Nationality instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NationalityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (NationalityDAO) ctx.getBean("NationalityDAO");
	}
}