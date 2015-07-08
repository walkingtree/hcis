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
 * ActionStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ActionStatus
 * @author MyEclipse Persistence Tools
 */

public class ActionStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ActionStatusDAO.class);
	// property constants
	public static final String ACTION_STATUS_DESC = "actionStatusDesc";
	public static final String CUSTOM_DESC = "customDesc";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(ActionStatus transientInstance) {
		log.debug("saving ActionStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActionStatus persistentInstance) {
		log.debug("deleting ActionStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActionStatus findById(java.lang.String id) {
		log.debug("getting ActionStatus instance with id: " + id);
		try {
			ActionStatus instance = (ActionStatus) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.ActionStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActionStatus instance) {
		log.debug("finding ActionStatus instance by example");
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
		log.debug("finding ActionStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ActionStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActionStatusDesc(Object actionStatusDesc) {
		return findByProperty(ACTION_STATUS_DESC, actionStatusDesc);
	}

	public List findByCustomDesc(Object customDesc) {
		return findByProperty(CUSTOM_DESC, customDesc);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all ActionStatus instances");
		try {
			String queryString = "from ActionStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActionStatus merge(ActionStatus detachedInstance) {
		log.debug("merging ActionStatus instance");
		try {
			ActionStatus result = (ActionStatus) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActionStatus instance) {
		log.debug("attaching dirty ActionStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActionStatus instance) {
		log.debug("attaching clean ActionStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ActionStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ActionStatusDAO) ctx.getBean("ActionStatusDAO");
	}
}