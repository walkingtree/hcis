package com.wtc.hcis.ip.da;

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
 * OtBooking entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtBooking
 * @author MyEclipse Persistence Tools
 */

public class OtBookingDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(OtBookingDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DOCTOR_ID = "doctorId";
	public static final String PATIENT_ID = "patientId";
	public static final String REFERENCE_TYPE = "referenceType";
	public static final String REFERENCE_NUMBER = "referenceNumber";
	public static final String DEPOSIT_AMOUNT = "depositAmount";
	public static final String BOOKING_STATUS = "bookingStatus";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtBooking transientInstance) {
		log.debug("saving OtBooking instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtBooking persistentInstance) {
		log.debug("deleting OtBooking instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtBooking findById(java.lang.Long id) {
		log.debug("getting OtBooking instance with id: " + id);
		try {
			OtBooking instance = (OtBooking) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.OtBooking", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtBooking instance) {
		log.debug("finding OtBooking instance by example");
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
		log.debug("finding OtBooking instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from OtBooking as model where model."
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

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
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

	public List findByDepositAmount(Object depositAmount) {
		return findByProperty(DEPOSIT_AMOUNT, depositAmount);
	}

	public List findByBookingStatus(Object bookingStatus) {
		return findByProperty(BOOKING_STATUS, bookingStatus);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all OtBooking instances");
		try {
			String queryString = "from OtBooking";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtBooking merge(OtBooking detachedInstance) {
		log.debug("merging OtBooking instance");
		try {
			OtBooking result = (OtBooking) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtBooking instance) {
		log.debug("attaching dirty OtBooking instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtBooking instance) {
		log.debug("attaching clean OtBooking instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtBookingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (OtBookingDAO) ctx.getBean("OtBookingDAO");
	}
}