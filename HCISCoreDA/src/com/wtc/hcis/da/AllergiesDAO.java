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
 * Allergies entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Allergies
 * @author MyEclipse Persistence Tools
 */

public class AllergiesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AllergiesDAO.class);
	// property constants
	public static final String ALLERGRY_DESCRIPTION = "allergryDescription";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(Allergies transientInstance) {
		log.debug("saving Allergies instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Allergies persistentInstance) {
		log.debug("deleting Allergies instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Allergies findById(java.lang.Integer id) {
		log.debug("getting Allergies instance with id: " + id);
		try {
			Allergies instance = (Allergies) getHibernateTemplate().get(
					"com.wtc.hcis.da.Allergies", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Allergies instance) {
		log.debug("finding Allergies instance by example");
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
		log.debug("finding Allergies instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Allergies as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAllergryDescription(Object allergryDescription) {
		return findByProperty(ALLERGRY_DESCRIPTION, allergryDescription);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all Allergies instances");
		try {
			String queryString = "from Allergies";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Allergies merge(Allergies detachedInstance) {
		log.debug("merging Allergies instance");
		try {
			Allergies result = (Allergies) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Allergies instance) {
		log.debug("attaching dirty Allergies instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Allergies instance) {
		log.debug("attaching clean Allergies instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AllergiesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AllergiesDAO) ctx.getBean("AllergiesDAO");
	}
}