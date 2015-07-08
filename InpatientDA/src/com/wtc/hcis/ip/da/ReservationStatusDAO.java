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
 * ReservationStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ReservationStatus
 * @author MyEclipse Persistence Tools
 */

public class ReservationStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ReservationStatusDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(ReservationStatus transientInstance) {
		log.debug("saving ReservationStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReservationStatus persistentInstance) {
		log.debug("deleting ReservationStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReservationStatus findById(java.lang.String id) {
		log.debug("getting ReservationStatus instance with id: " + id);
		try {
			ReservationStatus instance = (ReservationStatus) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.ReservationStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReservationStatus instance) {
		log.debug("finding ReservationStatus instance by example");
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
		log.debug("finding ReservationStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReservationStatus as model where model."
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all ReservationStatus instances");
		try {
			String queryString = "from ReservationStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReservationStatus merge(ReservationStatus detachedInstance) {
		log.debug("merging ReservationStatus instance");
		try {
			ReservationStatus result = (ReservationStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReservationStatus instance) {
		log.debug("attaching dirty ReservationStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReservationStatus instance) {
		log.debug("attaching clean ReservationStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReservationStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReservationStatusDAO) ctx.getBean("ReservationStatusDAO");
	}
}