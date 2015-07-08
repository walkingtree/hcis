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
 * BedReservation entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedReservation
 * @author MyEclipse Persistence Tools
 */

public class BedReservationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedReservationDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REQUEST_CREATED_BY = "requestCreatedBy";
	public static final String GOT_PREFERRED_ROOM = "gotPreferredRoom";
	public static final String PATIENT_ID = "patientId";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(BedReservation transientInstance) {
		log.debug("saving BedReservation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedReservation persistentInstance) {
		log.debug("deleting BedReservation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedReservation findById(java.lang.Integer id) {
		log.debug("getting BedReservation instance with id: " + id);
		try {
			BedReservation instance = (BedReservation) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.BedReservation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedReservation instance) {
		log.debug("finding BedReservation instance by example");
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
		log.debug("finding BedReservation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BedReservation as model where model."
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

	public List findByRequestCreatedBy(Object requestCreatedBy) {
		return findByProperty(REQUEST_CREATED_BY, requestCreatedBy);
	}

	public List findByGotPreferredRoom(Object gotPreferredRoom) {
		return findByProperty(GOT_PREFERRED_ROOM, gotPreferredRoom);
	}

	public List findByPatientId(Object patientId) {
		return findByProperty(PATIENT_ID, patientId);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all BedReservation instances");
		try {
			String queryString = "from BedReservation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedReservation merge(BedReservation detachedInstance) {
		log.debug("merging BedReservation instance");
		try {
			BedReservation result = (BedReservation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedReservation instance) {
		log.debug("attaching dirty BedReservation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedReservation instance) {
		log.debug("attaching clean BedReservation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedReservationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BedReservationDAO) ctx.getBean("BedReservationDAO");
	}
}