package com.wtc.hcis.da;

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
 * DocPatientVital entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DocPatientVital
 * @author MyEclipse Persistence Tools
 */

public class DocPatientVitalDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DocPatientVitalDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String PATIENT_ID = "patientId";
	public static final String REFERENCE_TYPE = "referenceType";
	public static final String REFERENCE_NUMBER = "referenceNumber";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DocPatientVital transientInstance) {
		log.debug("saving DocPatientVital instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocPatientVital persistentInstance) {
		log.debug("deleting DocPatientVital instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocPatientVital findById(java.lang.Integer id) {
		log.debug("getting DocPatientVital instance with id: " + id);
		try {
			DocPatientVital instance = (DocPatientVital) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DocPatientVital", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocPatientVital instance) {
		log.debug("finding DocPatientVital instance by example");
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
		log.debug("finding DocPatientVital instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocPatientVital as model where model."
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

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByReferenceType(Object referenceType) {
		return findByProperty(REFERENCE_TYPE, referenceType);
	}

	public List findByReferenceNumber(Object referenceNumber) {
		return findByProperty(REFERENCE_NUMBER, referenceNumber);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DocPatientVital instances");
		try {
			String queryString = "from DocPatientVital";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocPatientVital merge(DocPatientVital detachedInstance) {
		log.debug("merging DocPatientVital instance");
		try {
			DocPatientVital result = (DocPatientVital) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocPatientVital instance) {
		log.debug("attaching dirty DocPatientVital instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocPatientVital instance) {
		log.debug("attaching clean DocPatientVital instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocPatientVitalDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocPatientVitalDAO) ctx.getBean("DocPatientVitalDAO");
	}
}