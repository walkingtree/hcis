package com.wtc.hcis.billing.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillProcessEvent entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillProcessEvent
 * @author MyEclipse Persistence Tools
 */

public class BillProcessEventDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillProcessEventDAO.class);
	// property constants
	public static final String EVENT_STATUS = "eventStatus";

	protected void initDao() {
		// do nothing
	}

	public void save(BillProcessEvent transientInstance) {
		log.debug("saving BillProcessEvent instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillProcessEvent persistentInstance) {
		log.debug("deleting BillProcessEvent instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillProcessEvent findById(java.lang.Long id) {
		log.debug("getting BillProcessEvent instance with id: " + id);
		try {
			BillProcessEvent instance = (BillProcessEvent) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.BillProcessEvent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillProcessEvent instance) {
		log.debug("finding BillProcessEvent instance by example");
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
		log.debug("finding BillProcessEvent instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillProcessEvent as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEventStatus(Object eventStatus) {
		return findByProperty(EVENT_STATUS, eventStatus);
	}

	public List findAll() {
		log.debug("finding all BillProcessEvent instances");
		try {
			String queryString = "from BillProcessEvent";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillProcessEvent merge(BillProcessEvent detachedInstance) {
		log.debug("merging BillProcessEvent instance");
		try {
			BillProcessEvent result = (BillProcessEvent) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillProcessEvent instance) {
		log.debug("attaching dirty BillProcessEvent instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillProcessEvent instance) {
		log.debug("attaching clean BillProcessEvent instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillProcessEventDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillProcessEventDAO) ctx.getBean("BillProcessEventDAO");
	}
}