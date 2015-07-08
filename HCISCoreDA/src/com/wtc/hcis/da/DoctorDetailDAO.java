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
 * DoctorDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DoctorDetail
 * @author MyEclipse Persistence Tools
 */

public class DoctorDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DoctorDetailDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String WORK_EXPERIENCE = "workExperience";
	public static final String DUTY_START_TIME = "dutyStartTime";
	public static final String DUTY_END_TIME = "dutyEndTime";
	public static final String PERMANENT = "permanent";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	public static final String FATHER_HUSBAND_NAME = "fatherHusbandName";
	public static final String ID_NUMBER = "idNumber";
	public static final String BLOOD_DONOR_ID = "bloodDonorId";
	public static final String KNOWN_LANGUAGES = "knownLanguages";
	public static final String QUALIFICATION = "qualification";
	public static final String REFERRED_BY = "referredBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorDetail transientInstance) {
		log.debug("saving DoctorDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorDetail persistentInstance) {
		log.debug("deleting DoctorDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorDetail findById(java.lang.Integer id) {
		log.debug("getting DoctorDetail instance with id: " + id);
		try {
			DoctorDetail instance = (DoctorDetail) getHibernateTemplate().get(
					"com.wtc.hcis.da.DoctorDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorDetail instance) {
		log.debug("finding DoctorDetail instance by example");
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
		log.debug("finding DoctorDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorDetail as model where model."
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

	public List findByWorkExperience(Object workExperience) {
		return findByProperty(WORK_EXPERIENCE, workExperience);
	}

	public List findByDutyStartTime(Object dutyStartTime) {
		return findByProperty(DUTY_START_TIME, dutyStartTime);
	}

	public List findByDutyEndTime(Object dutyEndTime) {
		return findByProperty(DUTY_END_TIME, dutyEndTime);
	}

	public List findByPermanent(Object permanent) {
		return findByProperty(PERMANENT, permanent);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List findByWeight(Object weight) {
		return findByProperty(WEIGHT, weight);
	}

	public List findByFatherHusbandName(Object fatherHusbandName) {
		return findByProperty(FATHER_HUSBAND_NAME, fatherHusbandName);
	}

	public List findByIdNumber(Object idNumber) {
		return findByProperty(ID_NUMBER, idNumber);
	}

	public List findByBloodDonorId(Object bloodDonorId) {
		return findByProperty(BLOOD_DONOR_ID, bloodDonorId);
	}

	public List findByKnownLanguages(Object knownLanguages) {
		return findByProperty(KNOWN_LANGUAGES, knownLanguages);
	}

	public List findByQualification(Object qualification) {
		return findByProperty(QUALIFICATION, qualification);
	}

	public List findByReferredBy(Object referredBy) {
		return findByProperty(REFERRED_BY, referredBy);
	}

	public List findAll() {
		log.debug("finding all DoctorDetail instances");
		try {
			String queryString = "from DoctorDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorDetail merge(DoctorDetail detachedInstance) {
		log.debug("merging DoctorDetail instance");
		try {
			DoctorDetail result = (DoctorDetail) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorDetail instance) {
		log.debug("attaching dirty DoctorDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorDetail instance) {
		log.debug("attaching clean DoctorDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorDetailDAO) ctx.getBean("DoctorDetailDAO");
	}
}