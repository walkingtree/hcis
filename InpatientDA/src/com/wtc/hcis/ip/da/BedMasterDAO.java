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
 * BedMaster entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedMaster
 * @author MyEclipse Persistence Tools
 */

public class BedMasterDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedMasterDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ROOM_NBR = "roomNbr";
	public static final String FLOOR_NBR = "floorNbr";
	public static final String SITE_NAME = "siteName";
	public static final String DESCRIPTION = "description";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String DAILY_CHARGE = "dailyCharge";
	public static final String HOURLY_CHARGE = "hourlyCharge";
	public static final String DEPOSIT_AMOUNT = "depositAmount";
	public static final String PATIENT_ID = "patientId";
	public static final String DOCTOR_ID = "doctorId";

	protected void initDao() {
		// do nothing
	}

	public void save(BedMaster transientInstance) {
		log.debug("saving BedMaster instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedMaster persistentInstance) {
		log.debug("deleting BedMaster instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedMaster findById(java.lang.String id) {
		log.debug("getting BedMaster instance with id: " + id);
		try {
			BedMaster instance = (BedMaster) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.BedMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedMaster instance) {
		log.debug("finding BedMaster instance by example");
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
		log.debug("finding BedMaster instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BedMaster as model where model."
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

	public List findByRoomNbr(Object roomNbr) {
		return findByProperty(ROOM_NBR, roomNbr);
	}

	public List findByFloorNbr(Object floorNbr) {
		return findByProperty(FLOOR_NBR, floorNbr);
	}

	public List findBySiteName(Object siteName) {
		return findByProperty(SITE_NAME, siteName);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findByDailyCharge(Object dailyCharge) {
		return findByProperty(DAILY_CHARGE, dailyCharge);
	}

	public List findByHourlyCharge(Object hourlyCharge) {
		return findByProperty(HOURLY_CHARGE, hourlyCharge);
	}

	public List findByDepositAmount(Object depositAmount) {
		return findByProperty(DEPOSIT_AMOUNT, depositAmount);
	}

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findAll() {
		log.debug("finding all BedMaster instances");
		try {
			String queryString = "from BedMaster";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedMaster merge(BedMaster detachedInstance) {
		log.debug("merging BedMaster instance");
		try {
			BedMaster result = (BedMaster) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedMaster instance) {
		log.debug("attaching dirty BedMaster instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedMaster instance) {
		log.debug("attaching clean BedMaster instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedMasterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BedMasterDAO) ctx.getBean("BedMasterDAO");
	}
}