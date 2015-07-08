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
 * OtDetail entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtDetail
 * @author MyEclipse Persistence Tools
 */

public class OtDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OtDetailDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NAME = "name";
	public static final String COORDINATOR_ID = "coordinatorId";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtDetail transientInstance) {
		log.debug("saving OtDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtDetail persistentInstance) {
		log.debug("deleting OtDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtDetail findById(java.lang.String id) {
		log.debug("getting OtDetail instance with id: " + id);
		try {
			OtDetail instance = (OtDetail) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.OtDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtDetail instance) {
		log.debug("finding OtDetail instance by example");
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
		log.debug("finding OtDetail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OtDetail as model where model."
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

	public List findByCoordinatorId(Object coordinatorId) {
		return findByProperty(COORDINATOR_ID, coordinatorId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtDetail instances");
		try {
			String queryString = "from OtDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtDetail merge(OtDetail detachedInstance) {
		log.debug("merging OtDetail instance");
		try {
			OtDetail result = (OtDetail) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtDetail instance) {
		log.debug("attaching dirty OtDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtDetail instance) {
		log.debug("attaching clean OtDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtDetailDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OtDetailDAO) ctx.getBean("OtDetailDAO");
	}
}