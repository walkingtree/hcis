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
 * AdmissionStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.AdmissionStatus
 * @author MyEclipse Persistence Tools
 */

public class AdmissionStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AdmissionStatusDAO.class);
	// property constants
	public static final String ADMISSION_STATUS_DESC = "admissionStatusDesc";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(AdmissionStatus transientInstance) {
		log.debug("saving AdmissionStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdmissionStatus persistentInstance) {
		log.debug("deleting AdmissionStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdmissionStatus findById(java.lang.String id) {
		log.debug("getting AdmissionStatus instance with id: " + id);
		try {
			AdmissionStatus instance = (AdmissionStatus) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.AdmissionStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdmissionStatus instance) {
		log.debug("finding AdmissionStatus instance by example");
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
		log.debug("finding AdmissionStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdmissionStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAdmissionStatusDesc(Object admissionStatusDesc) {
		return findByProperty(ADMISSION_STATUS_DESC, admissionStatusDesc);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all AdmissionStatus instances");
		try {
			String queryString = "from AdmissionStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AdmissionStatus merge(AdmissionStatus detachedInstance) {
		log.debug("merging AdmissionStatus instance");
		try {
			AdmissionStatus result = (AdmissionStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdmissionStatus instance) {
		log.debug("attaching dirty AdmissionStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdmissionStatus instance) {
		log.debug("attaching clean AdmissionStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdmissionStatusDAO) ctx.getBean("AdmissionStatusDAO");
	}
}