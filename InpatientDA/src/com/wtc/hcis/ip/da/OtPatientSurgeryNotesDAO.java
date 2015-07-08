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
 * OtPatientSurgeryNotes entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtPatientSurgeryNotes
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryNotesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtPatientSurgeryNotesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String VALUE = "value";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtPatientSurgeryNotes transientInstance) {
		log.debug("saving OtPatientSurgeryNotes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtPatientSurgeryNotes persistentInstance) {
		log.debug("deleting OtPatientSurgeryNotes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryNotes findById(
			com.wtc.hcis.ip.da.OtPatientSurgeryNotesId id) {
		log.debug("getting OtPatientSurgeryNotes instance with id: " + id);
		try {
			OtPatientSurgeryNotes instance = (OtPatientSurgeryNotes) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtPatientSurgeryNotes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtPatientSurgeryNotes instance) {
		log.debug("finding OtPatientSurgeryNotes instance by example");
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
		log.debug("finding OtPatientSurgeryNotes instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtPatientSurgeryNotes as model where model."
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

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtPatientSurgeryNotes instances");
		try {
			String queryString = "from OtPatientSurgeryNotes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryNotes merge(OtPatientSurgeryNotes detachedInstance) {
		log.debug("merging OtPatientSurgeryNotes instance");
		try {
			OtPatientSurgeryNotes result = (OtPatientSurgeryNotes) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtPatientSurgeryNotes instance) {
		log.debug("attaching dirty OtPatientSurgeryNotes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtPatientSurgeryNotes instance) {
		log.debug("attaching clean OtPatientSurgeryNotes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtPatientSurgeryNotesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtPatientSurgeryNotesDAO) ctx
				.getBean("OtPatientSurgeryNotesDAO");
	}
}