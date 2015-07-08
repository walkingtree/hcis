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
 * OtSurgeryStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtSurgeryStatus
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OtSurgeryStatusDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String SEQ_NO = "seqNo";
	public static final String CAPTURE_TIME = "captureTime";
	public static final String CONTRIBUTE_TO_SCHEDULING = "contributeToScheduling";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtSurgeryStatus transientInstance) {
		log.debug("saving OtSurgeryStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtSurgeryStatus persistentInstance) {
		log.debug("deleting OtSurgeryStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtSurgeryStatus findById(java.lang.String id) {
		log.debug("getting OtSurgeryStatus instance with id: " + id);
		try {
			OtSurgeryStatus instance = (OtSurgeryStatus) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtSurgeryStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtSurgeryStatus instance) {
		log.debug("finding OtSurgeryStatus instance by example");
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
		log.debug("finding OtSurgeryStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtSurgeryStatus as model where model."
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

	public List findBySeqNo(Object seqNo) {
		return findByProperty(SEQ_NO, seqNo);
	}

	public List findByCaptureTime(Object captureTime) {
		return findByProperty(CAPTURE_TIME, captureTime);
	}

	public List findByContributeToScheduling(Object contributeToScheduling) {
		return findByProperty(CONTRIBUTE_TO_SCHEDULING, contributeToScheduling);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtSurgeryStatus instances");
		try {
			String queryString = "from OtSurgeryStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtSurgeryStatus merge(OtSurgeryStatus detachedInstance) {
		log.debug("merging OtSurgeryStatus instance");
		try {
			OtSurgeryStatus result = (OtSurgeryStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtSurgeryStatus instance) {
		log.debug("attaching dirty OtSurgeryStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtSurgeryStatus instance) {
		log.debug("attaching clean OtSurgeryStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtSurgeryStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtSurgeryStatusDAO) ctx.getBean("OtSurgeryStatusDAO");
	}
}