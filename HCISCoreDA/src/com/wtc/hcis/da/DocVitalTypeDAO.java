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
 * DocVitalType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DocVitalType
 * @author MyEclipse Persistence Tools
 */

public class DocVitalTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DocVitalTypeDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DocVitalType transientInstance) {
		log.debug("saving DocVitalType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocVitalType persistentInstance) {
		log.debug("deleting DocVitalType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocVitalType findById(java.lang.Integer id) {
		log.debug("getting DocVitalType instance with id: " + id);
		try {
			DocVitalType instance = (DocVitalType) getHibernateTemplate().get(
					"com.wtc.hcis.da.DocVitalType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocVitalType instance) {
		log.debug("finding DocVitalType instance by example");
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
		log.debug("finding DocVitalType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocVitalType as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DocVitalType instances");
		try {
			String queryString = "from DocVitalType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocVitalType merge(DocVitalType detachedInstance) {
		log.debug("merging DocVitalType instance");
		try {
			DocVitalType result = (DocVitalType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocVitalType instance) {
		log.debug("attaching dirty DocVitalType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocVitalType instance) {
		log.debug("attaching clean DocVitalType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocVitalTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocVitalTypeDAO) ctx.getBean("DocVitalTypeDAO");
	}
}