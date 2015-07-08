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
 * Insurer entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.Insurer
 * @author MyEclipse Persistence Tools
 */

public class InsurerDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(InsurerDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String INSURER_DESC = "insurerDesc";
	public static final String CONTACT_CODE = "contactCode";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Insurer transientInstance) {
		log.debug("saving Insurer instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Insurer persistentInstance) {
		log.debug("deleting Insurer instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Insurer findById(java.lang.String id) {
		log.debug("getting Insurer instance with id: " + id);
		try {
			Insurer instance = (Insurer) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.Insurer", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Insurer instance) {
		log.debug("finding Insurer instance by example");
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
		log.debug("finding Insurer instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Insurer as model where model."
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

	public List findByInsurerDesc(Object insurerDesc) {
		return findByProperty(INSURER_DESC, insurerDesc);
	}

	public List findByContactCode(Object contactCode) {
		return findByProperty(CONTACT_CODE, contactCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByLastModifiedBy(Object lastModifiedBy) {
		return findByProperty(LAST_MODIFIED_BY, lastModifiedBy);
	}

	public List findAll() {
		log.debug("finding all Insurer instances");
		try {
			String queryString = "from Insurer";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Insurer merge(Insurer detachedInstance) {
		log.debug("merging Insurer instance");
		try {
			Insurer result = (Insurer) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Insurer instance) {
		log.debug("attaching dirty Insurer instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Insurer instance) {
		log.debug("attaching clean Insurer instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InsurerDAO getFromApplicationContext(ApplicationContext ctx) {
		return (InsurerDAO) ctx.getBean("InsurerDAO");
	}
}