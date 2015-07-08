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
 * Hospital entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Hospital
 * @author MyEclipse Persistence Tools
 */

public class HospitalDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(HospitalDAO.class);
	// property constants
	public static final String HOSPITAL_NAME = "hospitalName";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Hospital transientInstance) {
		log.debug("saving Hospital instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Hospital persistentInstance) {
		log.debug("deleting Hospital instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Hospital findById(java.lang.String id) {
		log.debug("getting Hospital instance with id: " + id);
		try {
			Hospital instance = (Hospital) getHibernateTemplate().get(
					"com.wtc.hcis.da.Hospital", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Hospital instance) {
		log.debug("finding Hospital instance by example");
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
		log.debug("finding Hospital instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Hospital as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHospitalName(Object hospitalName) {
		return findByProperty(HOSPITAL_NAME, hospitalName);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all Hospital instances");
		try {
			String queryString = "from Hospital";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Hospital merge(Hospital detachedInstance) {
		log.debug("merging Hospital instance");
		try {
			Hospital result = (Hospital) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Hospital instance) {
		log.debug("attaching dirty Hospital instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Hospital instance) {
		log.debug("attaching clean Hospital instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static HospitalDAO getFromApplicationContext(ApplicationContext ctx) {
		return (HospitalDAO) ctx.getBean("HospitalDAO");
	}
}