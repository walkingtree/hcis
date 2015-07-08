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
 * PatientVaccinationScheduleDetails entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.PatientVaccinationScheduleDetails
 * @author MyEclipse Persistence Tools
 */

public class PatientVaccinationScheduleDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(PatientVaccinationScheduleDetailsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String PERIOD_IN_DAYS = "periodInDays";
	public static final String AGE = "age";
	public static final String VACCINATION_TYPE_CD = "vaccinationTypeCd";
	public static final String DOSAGE = "dosage";
	public static final String DOCTOR_COMMENTS = "doctorComments";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(PatientVaccinationScheduleDetails transientInstance) {
		log.debug("saving PatientVaccinationScheduleDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PatientVaccinationScheduleDetails persistentInstance) {
		log.debug("deleting PatientVaccinationScheduleDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PatientVaccinationScheduleDetails findById(
			com.wtc.hcis.da.PatientVaccinationScheduleDetailsId id) {
		log
				.debug("getting PatientVaccinationScheduleDetails instance with id: "
						+ id);
		try {
			PatientVaccinationScheduleDetails instance = (PatientVaccinationScheduleDetails) getHibernateTemplate()
					.get("com.wtc.hcis.da.PatientVaccinationScheduleDetails",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PatientVaccinationScheduleDetails instance) {
		log
				.debug("finding PatientVaccinationScheduleDetails instance by example");
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
				.debug("finding PatientVaccinationScheduleDetails instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from PatientVaccinationScheduleDetails as model where model."
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

	public List findByPeriodInDays(Object periodInDays) {
		return findByProperty(PERIOD_IN_DAYS, periodInDays);
	}

	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List findByVaccinationTypeCd(Object vaccinationTypeCd) {
		return findByProperty(VACCINATION_TYPE_CD, vaccinationTypeCd);
	}

	public List findByDosage(Object dosage) {
		return findByProperty(DOSAGE, dosage);
	}

	public List findByDoctorComments(Object doctorComments) {
		return findByProperty(DOCTOR_COMMENTS, doctorComments);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all PatientVaccinationScheduleDetails instances");
		try {
			String queryString = "from PatientVaccinationScheduleDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PatientVaccinationScheduleDetails merge(
			PatientVaccinationScheduleDetails detachedInstance) {
		log.debug("merging PatientVaccinationScheduleDetails instance");
		try {
			PatientVaccinationScheduleDetails result = (PatientVaccinationScheduleDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PatientVaccinationScheduleDetails instance) {
		log.debug("attaching dirty PatientVaccinationScheduleDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PatientVaccinationScheduleDetails instance) {
		log.debug("attaching clean PatientVaccinationScheduleDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientVaccinationScheduleDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PatientVaccinationScheduleDetailsDAO) ctx
				.getBean("PatientVaccinationScheduleDetailsDAO");
	}
}