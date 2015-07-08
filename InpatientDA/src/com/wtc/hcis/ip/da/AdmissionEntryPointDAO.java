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
 * AdmissionEntryPoint entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.AdmissionEntryPoint
 * @author MyEclipse Persistence Tools
 */

public class AdmissionEntryPointDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AdmissionEntryPointDAO.class);
	// property constants
	public static final String ENTRY_POINT_DESC = "entryPointDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(AdmissionEntryPoint transientInstance) {
		log.debug("saving AdmissionEntryPoint instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdmissionEntryPoint persistentInstance) {
		log.debug("deleting AdmissionEntryPoint instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdmissionEntryPoint findById(java.lang.String id) {
		log.debug("getting AdmissionEntryPoint instance with id: " + id);
		try {
			AdmissionEntryPoint instance = (AdmissionEntryPoint) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.AdmissionEntryPoint", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdmissionEntryPoint instance) {
		log.debug("finding AdmissionEntryPoint instance by example");
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
		log.debug("finding AdmissionEntryPoint instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdmissionEntryPoint as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByEntryPointDesc(Object entryPointDesc) {
		return findByProperty(ENTRY_POINT_DESC, entryPointDesc);
	}

	public List findAll() {
		log.debug("finding all AdmissionEntryPoint instances");
		try {
			String queryString = "from AdmissionEntryPoint";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AdmissionEntryPoint merge(AdmissionEntryPoint detachedInstance) {
		log.debug("merging AdmissionEntryPoint instance");
		try {
			AdmissionEntryPoint result = (AdmissionEntryPoint) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdmissionEntryPoint instance) {
		log.debug("attaching dirty AdmissionEntryPoint instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdmissionEntryPoint instance) {
		log.debug("attaching clean AdmissionEntryPoint instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionEntryPointDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdmissionEntryPointDAO) ctx.getBean("AdmissionEntryPointDAO");
	}
}