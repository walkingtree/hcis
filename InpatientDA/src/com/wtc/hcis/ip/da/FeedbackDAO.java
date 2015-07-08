package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Feedback entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.Feedback
 * @author MyEclipse Persistence Tools
 */

public class FeedbackDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FeedbackDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String FEEDBACK_BY = "feedbackBy";
	public static final String ENTITY_TYPE = "entityType";
	public static final String FEEDBACK_VALUE = "feedbackValue";

	protected void initDao() {
		// do nothing
	}

	public void save(Feedback transientInstance) {
		log.debug("saving Feedback instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feedback persistentInstance) {
		log.debug("deleting Feedback instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feedback findById(com.wtc.hcis.ip.da.FeedbackId id) {
		log.debug("getting Feedback instance with id: " + id);
		try {
			Feedback instance = (Feedback) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.Feedback", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Feedback instance) {
		log.debug("finding Feedback instance by example");
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
		log.debug("finding Feedback instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feedback as model where model."
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

	public List findByFeedbackBy(Object feedbackBy) {
		return findByProperty(FEEDBACK_BY, feedbackBy);
	}

	public List findByEntityType(Object entityType) {
		return findByProperty(ENTITY_TYPE, entityType);
	}

	public List findByFeedbackValue(Object feedbackValue) {
		return findByProperty(FEEDBACK_VALUE, feedbackValue);
	}

	public List findAll() {
		log.debug("finding all Feedback instances");
		try {
			String queryString = "from Feedback";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Feedback merge(Feedback detachedInstance) {
		log.debug("merging Feedback instance");
		try {
			Feedback result = (Feedback) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Feedback instance) {
		log.debug("attaching dirty Feedback instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Feedback instance) {
		log.debug("attaching clean Feedback instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeedbackDAO getFromApplicationContext(ApplicationContext ctx) {
		return (FeedbackDAO) ctx.getBean("FeedbackDAO");
	}
}