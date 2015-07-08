package com.wtc.hcis.ip.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DischargeType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DischargeType
 * @author MyEclipse Persistence Tools
 */

public class DischargeTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DischargeTypeDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String PROCEDURE = "procedure";

	protected void initDao() {
		// do nothing
	}

	public void save(DischargeType transientInstance) {
		log.debug("saving DischargeType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DischargeType persistentInstance) {
		log.debug("deleting DischargeType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DischargeType findById(java.lang.String id) {
		log.debug("getting DischargeType instance with id: " + id);
		try {
			DischargeType instance = (DischargeType) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DischargeType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DischargeType instance) {
		log.debug("finding DischargeType instance by example");
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
		log.debug("finding DischargeType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DischargeType as model where model."
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

	public List findByProcedure(Object procedure) {
		return findByProperty(PROCEDURE, procedure);
	}

	public List findAll() {
		log.debug("finding all DischargeType instances");
		try {
			String queryString = "from DischargeType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DischargeType merge(DischargeType detachedInstance) {
		log.debug("merging DischargeType instance");
		try {
			DischargeType result = (DischargeType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DischargeType instance) {
		log.debug("attaching dirty DischargeType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DischargeType instance) {
		log.debug("attaching clean DischargeType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DischargeTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DischargeTypeDAO) ctx.getBean("DischargeTypeDAO");
	}
}