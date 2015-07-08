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
 * Vaccination entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Vaccination
 * @author MyEclipse Persistence Tools
 */

public class VaccinationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(VaccinationDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String VACCINATION_NAME = "vaccinationName";
	public static final String AGE_RANGE = "ageRange";
	public static final String SUBSTITUTE_FOR = "substituteFor";
	public static final String ACTIVE_FLAG = "activeFlag";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(Vaccination transientInstance) {
		log.debug("saving Vaccination instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Vaccination persistentInstance) {
		log.debug("deleting Vaccination instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Vaccination findById(java.lang.String id) {
		log.debug("getting Vaccination instance with id: " + id);
		try {
			Vaccination instance = (Vaccination) getHibernateTemplate().get(
					"com.wtc.hcis.da.Vaccination", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Vaccination instance) {
		log.debug("finding Vaccination instance by example");
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
		log.debug("finding Vaccination instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Vaccination as model where model."
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

	public List findByVaccinationName(Object vaccinationName) {
		return findByProperty(VACCINATION_NAME, vaccinationName);
	}

	public List findByAgeRange(Object ageRange) {
		return findByProperty(AGE_RANGE, ageRange);
	}

	public List findBySubstituteFor(Object substituteFor) {
		return findByProperty(SUBSTITUTE_FOR, substituteFor);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findAll() {
		log.debug("finding all Vaccination instances");
		try {
			String queryString = "from Vaccination";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Vaccination merge(Vaccination detachedInstance) {
		log.debug("merging Vaccination instance");
		try {
			Vaccination result = (Vaccination) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Vaccination instance) {
		log.debug("attaching dirty Vaccination instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Vaccination instance) {
		log.debug("attaching clean Vaccination instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VaccinationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (VaccinationDAO) ctx.getBean("VaccinationDAO");
	}
}