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
 * Observations entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Observations
 * @author MyEclipse Persistence Tools
 */

public class ObservationsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ObservationsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String OBSERVATIONS = "observations";
	public static final String REMARKS = "remarks";
	public static final String CREADTED_BY = "creadtedBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Observations transientInstance) {
		log.debug("saving Observations instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Observations persistentInstance) {
		log.debug("deleting Observations instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Observations findById(com.wtc.hcis.da.ObservationsId id) {
		log.debug("getting Observations instance with id: " + id);
		try {
			Observations instance = (Observations) getHibernateTemplate().get(
					"com.wtc.hcis.da.Observations", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Observations instance) {
		log.debug("finding Observations instance by example");
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
		log.debug("finding Observations instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Observations as model where model."
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

	public List findByObservations(Object observations) {
		return findByProperty(OBSERVATIONS, observations);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreadtedBy(Object creadtedBy) {
		return findByProperty(CREADTED_BY, creadtedBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Observations instances");
		try {
			String queryString = "from Observations";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Observations merge(Observations detachedInstance) {
		log.debug("merging Observations instance");
		try {
			Observations result = (Observations) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Observations instance) {
		log.debug("attaching dirty Observations instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Observations instance) {
		log.debug("attaching clean Observations instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ObservationsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ObservationsDAO) ctx.getBean("ObservationsDAO");
	}
}