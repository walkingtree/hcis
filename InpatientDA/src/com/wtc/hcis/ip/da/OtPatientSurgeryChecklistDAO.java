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
 * OtPatientSurgeryChecklist entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.ip.da.OtPatientSurgeryChecklist
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryChecklistDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtPatientSurgeryChecklistDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtPatientSurgeryChecklist transientInstance) {
		log.debug("saving OtPatientSurgeryChecklist instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtPatientSurgeryChecklist persistentInstance) {
		log.debug("deleting OtPatientSurgeryChecklist instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryChecklist findById(
			com.wtc.hcis.ip.da.OtPatientSurgeryChecklistId id) {
		log.debug("getting OtPatientSurgeryChecklist instance with id: " + id);
		try {
			OtPatientSurgeryChecklist instance = (OtPatientSurgeryChecklist) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtPatientSurgeryChecklist", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtPatientSurgeryChecklist instance) {
		log.debug("finding OtPatientSurgeryChecklist instance by example");
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
		log.debug("finding OtPatientSurgeryChecklist instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtPatientSurgeryChecklist as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtPatientSurgeryChecklist instances");
		try {
			String queryString = "from OtPatientSurgeryChecklist";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryChecklist merge(
			OtPatientSurgeryChecklist detachedInstance) {
		log.debug("merging OtPatientSurgeryChecklist instance");
		try {
			OtPatientSurgeryChecklist result = (OtPatientSurgeryChecklist) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtPatientSurgeryChecklist instance) {
		log.debug("attaching dirty OtPatientSurgeryChecklist instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtPatientSurgeryChecklist instance) {
		log.debug("attaching clean OtPatientSurgeryChecklist instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtPatientSurgeryChecklistDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtPatientSurgeryChecklistDAO) ctx
				.getBean("OtPatientSurgeryChecklistDAO");
	}
}