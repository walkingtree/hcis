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
 * NursingUnit entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.NursingUnit
 * @author MyEclipse Persistence Tools
 */

public class NursingUnitDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(NursingUnitDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String UNIT_DESCRIPTION = "unitDescription";
	public static final String UNIT_COORDINATOR_USER_ID = "unitCoordinatorUserId";

	protected void initDao() {
		// do nothing
	}

	public void save(NursingUnit transientInstance) {
		log.debug("saving NursingUnit instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NursingUnit persistentInstance) {
		log.debug("deleting NursingUnit instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NursingUnit findById(java.lang.String id) {
		log.debug("getting NursingUnit instance with id: " + id);
		try {
			NursingUnit instance = (NursingUnit) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.NursingUnit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NursingUnit instance) {
		log.debug("finding NursingUnit instance by example");
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
		log.debug("finding NursingUnit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from NursingUnit as model where model."
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

	public List findByUnitDescription(Object unitDescription) {
		return findByProperty(UNIT_DESCRIPTION, unitDescription);
	}

	public List findByUnitCoordinatorUserId(Object unitCoordinatorUserId) {
		return findByProperty(UNIT_COORDINATOR_USER_ID, unitCoordinatorUserId);
	}

	public List findAll() {
		log.debug("finding all NursingUnit instances");
		try {
			String queryString = "from NursingUnit";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NursingUnit merge(NursingUnit detachedInstance) {
		log.debug("merging NursingUnit instance");
		try {
			NursingUnit result = (NursingUnit) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NursingUnit instance) {
		log.debug("attaching dirty NursingUnit instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NursingUnit instance) {
		log.debug("attaching clean NursingUnit instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NursingUnitDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (NursingUnitDAO) ctx.getBean("NursingUnitDAO");
	}
}