package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DoctorEspecialty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DoctorEspecialty
 * @author MyEclipse Persistence Tools
 */

public class DoctorEspecialtyDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DoctorEspecialtyDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ACTIVE = "active";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String CONSULTATION_CHARGE = "consultationCharge";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorEspecialty transientInstance) {
		log.debug("saving DoctorEspecialty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorEspecialty persistentInstance) {
		log.debug("deleting DoctorEspecialty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorEspecialty findById(com.wtc.hcis.da.DoctorEspecialtyId id) {
		log.debug("getting DoctorEspecialty instance with id: " + id);
		try {
			DoctorEspecialty instance = (DoctorEspecialty) getHibernateTemplate()
					.get("com.wtc.hcis.da.DoctorEspecialty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorEspecialty instance) {
		log.debug("finding DoctorEspecialty instance by example");
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
		log.debug("finding DoctorEspecialty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorEspecialty as model where model."
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

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findByConsultationCharge(Object consultationCharge) {
		return findByProperty(CONSULTATION_CHARGE, consultationCharge);
	}

	public List findAll() {
		log.debug("finding all DoctorEspecialty instances");
		try {
			String queryString = "from DoctorEspecialty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorEspecialty merge(DoctorEspecialty detachedInstance) {
		log.debug("merging DoctorEspecialty instance");
		try {
			DoctorEspecialty result = (DoctorEspecialty) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorEspecialty instance) {
		log.debug("attaching dirty DoctorEspecialty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorEspecialty instance) {
		log.debug("attaching clean DoctorEspecialty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorEspecialtyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorEspecialtyDAO) ctx.getBean("DoctorEspecialtyDAO");
	}
}