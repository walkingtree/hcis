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
 * StatusTransition entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.StatusTransition
 * @author MyEclipse Persistence Tools
 */

public class StatusTransitionDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(StatusTransitionDAO.class);
	// property constants
	public static final String CONTEXT = "context";
	public static final String INPUT1 = "input1";
	public static final String INPUT2 = "input2";
	public static final String FROM_STATUS = "fromStatus";
	public static final String TO_STATUS = "toStatus";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(StatusTransition transientInstance) {
		log.debug("saving StatusTransition instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(StatusTransition persistentInstance) {
		log.debug("deleting StatusTransition instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public StatusTransition findById(java.lang.Integer id) {
		log.debug("getting StatusTransition instance with id: " + id);
		try {
			StatusTransition instance = (StatusTransition) getHibernateTemplate()
					.get("com.wtc.hcis.da.StatusTransition", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(StatusTransition instance) {
		log.debug("finding StatusTransition instance by example");
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
		log.debug("finding StatusTransition instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from StatusTransition as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findByInput1(Object input1) {
		return findByProperty(INPUT1, input1);
	}

	public List findByInput2(Object input2) {
		return findByProperty(INPUT2, input2);
	}

	public List findByFromStatus(Object fromStatus) {
		return findByProperty(FROM_STATUS, fromStatus);
	}

	public List findByToStatus(Object toStatus) {
		return findByProperty(TO_STATUS, toStatus);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all StatusTransition instances");
		try {
			String queryString = "from StatusTransition";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public StatusTransition merge(StatusTransition detachedInstance) {
		log.debug("merging StatusTransition instance");
		try {
			StatusTransition result = (StatusTransition) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(StatusTransition instance) {
		log.debug("attaching dirty StatusTransition instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(StatusTransition instance) {
		log.debug("attaching clean StatusTransition instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StatusTransitionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (StatusTransitionDAO) ctx.getBean("StatusTransitionDAO");
	}
}