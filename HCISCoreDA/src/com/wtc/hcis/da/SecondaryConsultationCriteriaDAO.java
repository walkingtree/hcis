package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecondaryConsultationCriteria entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.SecondaryConsultationCriteria
 * @author MyEclipse Persistence Tools
 */

public class SecondaryConsultationCriteriaDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(SecondaryConsultationCriteriaDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NUMBER_OF_DAYS = "numberOfDays";
	public static final String NUMBER_OF_VISITS = "numberOfVisits";
	public static final String DOCTOR_ID = "doctorId";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(SecondaryConsultationCriteria transientInstance) {
		log.debug("saving SecondaryConsultationCriteria instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecondaryConsultationCriteria persistentInstance) {
		log.debug("deleting SecondaryConsultationCriteria instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecondaryConsultationCriteria findById(java.lang.String id) {
		log.debug("getting SecondaryConsultationCriteria instance with id: "
				+ id);
		try {
			SecondaryConsultationCriteria instance = (SecondaryConsultationCriteria) getHibernateTemplate()
					.get("com.wtc.hcis.da.SecondaryConsultationCriteria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecondaryConsultationCriteria instance) {
		log.debug("finding SecondaryConsultationCriteria instance by example");
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
		log
				.debug("finding SecondaryConsultationCriteria instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecondaryConsultationCriteria as model where model."
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

	public List findByNumberOfDays(Object numberOfDays) {
		return findByProperty(NUMBER_OF_DAYS, numberOfDays);
	}

	public List findByNumberOfVisits(Object numberOfVisits) {
		return findByProperty(NUMBER_OF_VISITS, numberOfVisits);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all SecondaryConsultationCriteria instances");
		try {
			String queryString = "from SecondaryConsultationCriteria";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecondaryConsultationCriteria merge(
			SecondaryConsultationCriteria detachedInstance) {
		log.debug("merging SecondaryConsultationCriteria instance");
		try {
			SecondaryConsultationCriteria result = (SecondaryConsultationCriteria) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecondaryConsultationCriteria instance) {
		log.debug("attaching dirty SecondaryConsultationCriteria instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecondaryConsultationCriteria instance) {
		log.debug("attaching clean SecondaryConsultationCriteria instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SecondaryConsultationCriteriaDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SecondaryConsultationCriteriaDAO) ctx
				.getBean("SecondaryConsultationCriteriaDAO");
	}
}