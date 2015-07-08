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
 * OtPatientSurgeryActivity entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.ip.da.OtPatientSurgeryActivity
 * @author MyEclipse Persistence Tools
 */

public class OtPatientSurgeryActivityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtPatientSurgeryActivityDAO.class);
	// property constants
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtPatientSurgeryActivity transientInstance) {
		log.debug("saving OtPatientSurgeryActivity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtPatientSurgeryActivity persistentInstance) {
		log.debug("deleting OtPatientSurgeryActivity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryActivity findById(
			com.wtc.hcis.ip.da.OtPatientSurgeryActivityId id) {
		log.debug("getting OtPatientSurgeryActivity instance with id: " + id);
		try {
			OtPatientSurgeryActivity instance = (OtPatientSurgeryActivity) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtPatientSurgeryActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtPatientSurgeryActivity instance) {
		log.debug("finding OtPatientSurgeryActivity instance by example");
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
		log.debug("finding OtPatientSurgeryActivity instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtPatientSurgeryActivity as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtPatientSurgeryActivity instances");
		try {
			String queryString = "from OtPatientSurgeryActivity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtPatientSurgeryActivity merge(
			OtPatientSurgeryActivity detachedInstance) {
		log.debug("merging OtPatientSurgeryActivity instance");
		try {
			OtPatientSurgeryActivity result = (OtPatientSurgeryActivity) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtPatientSurgeryActivity instance) {
		log.debug("attaching dirty OtPatientSurgeryActivity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtPatientSurgeryActivity instance) {
		log.debug("attaching clean OtPatientSurgeryActivity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtPatientSurgeryActivityDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtPatientSurgeryActivityDAO) ctx
				.getBean("OtPatientSurgeryActivityDAO");
	}
}