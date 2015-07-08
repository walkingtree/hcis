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
 * Appointments entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Appointments
 * @author MyEclipse Persistence Tools
 */

public class AppointmentsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AppointmentsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String APPOINTMENT_TYPE_CODE = "appointmentTypeCode";
	public static final String APPT_START_TIME = "apptStartTime";
	public static final String APPT_END_TIME = "apptEndTime";
	public static final String APPOINTMENT_AGENDA = "appointmentAgenda";
	public static final String CONSULTATION_CHARGE = "consultationCharge";
	public static final String APPOINTMENT_REMARKS = "appointmentRemarks";
	public static final String DATE_DIM_ID = "dateDimId";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Appointments transientInstance) {
		log.debug("saving Appointments instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Appointments persistentInstance) {
		log.debug("deleting Appointments instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Appointments findById(java.lang.Integer id) {
		log.debug("getting Appointments instance with id: " + id);
		try {
			Appointments instance = (Appointments) getHibernateTemplate().get(
					"com.wtc.hcis.da.Appointments", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Appointments instance) {
		log.debug("finding Appointments instance by example");
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
		log.debug("finding Appointments instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Appointments as model where model."
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

	public List findByAppointmentTypeCode(Object appointmentTypeCode) {
		return findByProperty(APPOINTMENT_TYPE_CODE, appointmentTypeCode);
	}

	public List findByApptStartTime(Object apptStartTime) {
		return findByProperty(APPT_START_TIME, apptStartTime);
	}

	public List findByApptEndTime(Object apptEndTime) {
		return findByProperty(APPT_END_TIME, apptEndTime);
	}

	public List findByAppointmentAgenda(Object appointmentAgenda) {
		return findByProperty(APPOINTMENT_AGENDA, appointmentAgenda);
	}

	public List findByConsultationCharge(Object consultationCharge) {
		return findByProperty(CONSULTATION_CHARGE, consultationCharge);
	}

	public List findByAppointmentRemarks(Object appointmentRemarks) {
		return findByProperty(APPOINTMENT_REMARKS, appointmentRemarks);
	}

	public List findByDateDimId(Object dateDimId) {
		return findByProperty(DATE_DIM_ID, dateDimId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Appointments instances");
		try {
			String queryString = "from Appointments";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Appointments merge(Appointments detachedInstance) {
		log.debug("merging Appointments instance");
		try {
			Appointments result = (Appointments) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Appointments instance) {
		log.debug("attaching dirty Appointments instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Appointments instance) {
		log.debug("attaching clean Appointments instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AppointmentsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AppointmentsDAO) ctx.getBean("AppointmentsDAO");
	}
}