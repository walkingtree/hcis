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
 * VaccinationSchedule entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.VaccinationSchedule
 * @author MyEclipse Persistence Tools
 */

public class VaccinationScheduleDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(VaccinationScheduleDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SCHEDULE_DESC = "scheduleDesc";
	public static final String AGE_GROUP = "ageGroup";
	public static final String USER_ID = "userId";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(VaccinationSchedule transientInstance) {
		log.debug("saving VaccinationSchedule instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VaccinationSchedule persistentInstance) {
		log.debug("deleting VaccinationSchedule instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VaccinationSchedule findById(java.lang.String id) {
		log.debug("getting VaccinationSchedule instance with id: " + id);
		try {
			VaccinationSchedule instance = (VaccinationSchedule) getHibernateTemplate()
					.get("com.wtc.hcis.da.VaccinationSchedule", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VaccinationSchedule instance) {
		log.debug("finding VaccinationSchedule instance by example");
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
		log.debug("finding VaccinationSchedule instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VaccinationSchedule as model where model."
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

	public List findByScheduleDesc(Object scheduleDesc) {
		return findByProperty(SCHEDULE_DESC, scheduleDesc);
	}

	public List findByAgeGroup(Object ageGroup) {
		return findByProperty(AGE_GROUP, ageGroup);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all VaccinationSchedule instances");
		try {
			String queryString = "from VaccinationSchedule";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VaccinationSchedule merge(VaccinationSchedule detachedInstance) {
		log.debug("merging VaccinationSchedule instance");
		try {
			VaccinationSchedule result = (VaccinationSchedule) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VaccinationSchedule instance) {
		log.debug("attaching dirty VaccinationSchedule instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VaccinationSchedule instance) {
		log.debug("attaching clean VaccinationSchedule instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VaccinationScheduleDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (VaccinationScheduleDAO) ctx.getBean("VaccinationScheduleDAO");
	}
}