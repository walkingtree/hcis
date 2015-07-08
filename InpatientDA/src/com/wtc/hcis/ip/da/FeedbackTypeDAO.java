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
 * FeedbackType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.FeedbackType
 * @author MyEclipse Persistence Tools
 */

public class FeedbackTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(FeedbackTypeDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String INVOLVED_PROCESS = "involvedProcess";
	public static final String FEED_BACK_VALUE_TYPE = "feedBackValueType";
	public static final String MAXIMUM_ALLOWED_SUBSEQUENCE = "maximumAllowedSubsequence";

	protected void initDao() {
		// do nothing
	}

	public void save(FeedbackType transientInstance) {
		log.debug("saving FeedbackType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(FeedbackType persistentInstance) {
		log.debug("deleting FeedbackType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public FeedbackType findById(com.wtc.hcis.ip.da.FeedbackTypeId id) {
		log.debug("getting FeedbackType instance with id: " + id);
		try {
			FeedbackType instance = (FeedbackType) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.FeedbackType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(FeedbackType instance) {
		log.debug("finding FeedbackType instance by example");
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
		log.debug("finding FeedbackType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from FeedbackType as model where model."
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

	public List findByInvolvedProcess(Object involvedProcess) {
		return findByProperty(INVOLVED_PROCESS, involvedProcess);
	}

	public List findByFeedBackValueType(Object feedBackValueType) {
		return findByProperty(FEED_BACK_VALUE_TYPE, feedBackValueType);
	}

	public List findByMaximumAllowedSubsequence(Object maximumAllowedSubsequence) {
		return findByProperty(MAXIMUM_ALLOWED_SUBSEQUENCE,
				maximumAllowedSubsequence);
	}

	public List findAll() {
		log.debug("finding all FeedbackType instances");
		try {
			String queryString = "from FeedbackType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public FeedbackType merge(FeedbackType detachedInstance) {
		log.debug("merging FeedbackType instance");
		try {
			FeedbackType result = (FeedbackType) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(FeedbackType instance) {
		log.debug("attaching dirty FeedbackType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(FeedbackType instance) {
		log.debug("attaching clean FeedbackType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static FeedbackTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (FeedbackTypeDAO) ctx.getBean("FeedbackTypeDAO");
	}
}