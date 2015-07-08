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
 * PatientDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.PatientDetails
 * @author MyEclipse Persistence Tools
 */

public class PatientDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PatientDetailsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String BLOOD_DONOR_ID = "bloodDonorId";
	public static final String ORGAN_DONOR_ID = "organDonorId";
	public static final String ORGAN_DONATED_TO = "organDonatedTo";
	public static final String ID_NUMBER = "idNumber";
	public static final String VISA_NUMBER = "visaNumber";
	public static final String SMOKING = "smoking";
	public static final String DRINKING = "drinking";
	public static final String FITNESS_ACTIVITY = "fitnessActivity";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(PatientDetails transientInstance) {
		log.debug("saving PatientDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PatientDetails persistentInstance) {
		log.debug("deleting PatientDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PatientDetails findById(java.lang.Integer id) {
		log.debug("getting PatientDetails instance with id: " + id);
		try {
			PatientDetails instance = (PatientDetails) getHibernateTemplate()
					.get("com.wtc.hcis.da.PatientDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PatientDetails instance) {
		log.debug("finding PatientDetails instance by example");
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
		log.debug("finding PatientDetails instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PatientDetails as model where model."
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

	public List findByBloodDonorId(Object bloodDonorId) {
		return findByProperty(BLOOD_DONOR_ID, bloodDonorId);
	}

	public List findByOrganDonorId(Object organDonorId) {
		return findByProperty(ORGAN_DONOR_ID, organDonorId);
	}

	public List findByOrganDonatedTo(Object organDonatedTo) {
		return findByProperty(ORGAN_DONATED_TO, organDonatedTo);
	}

	public List findByIdNumber(Object idNumber) {
		return findByProperty(ID_NUMBER, idNumber);
	}

	public List findByVisaNumber(Object visaNumber) {
		return findByProperty(VISA_NUMBER, visaNumber);
	}

	public List findBySmoking(Object smoking) {
		return findByProperty(SMOKING, smoking);
	}

	public List findByDrinking(Object drinking) {
		return findByProperty(DRINKING, drinking);
	}

	public List findByFitnessActivity(Object fitnessActivity) {
		return findByProperty(FITNESS_ACTIVITY, fitnessActivity);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all PatientDetails instances");
		try {
			String queryString = "from PatientDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PatientDetails merge(PatientDetails detachedInstance) {
		log.debug("merging PatientDetails instance");
		try {
			PatientDetails result = (PatientDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PatientDetails instance) {
		log.debug("attaching dirty PatientDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PatientDetails instance) {
		log.debug("attaching clean PatientDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PatientDetailsDAO) ctx.getBean("PatientDetailsDAO");
	}
}