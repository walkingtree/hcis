package com.wtc.hcis.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PatientRating entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.PatientRating
 * @author MyEclipse Persistence Tools
 */

public class PatientRatingDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PatientRatingDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String ACTIVE = "active";
	public static final String DEFAULT_CODE = "defaultCode";

	protected void initDao() {
		// do nothing
	}

	public void save(PatientRating transientInstance) {
		log.debug("saving PatientRating instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PatientRating persistentInstance) {
		log.debug("deleting PatientRating instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PatientRating findById(java.lang.String id) {
		log.debug("getting PatientRating instance with id: " + id);
		try {
			PatientRating instance = (PatientRating) getHibernateTemplate()
					.get("com.wtc.hcis.da.PatientRating", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PatientRating instance) {
		log.debug("finding PatientRating instance by example");
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
		log.debug("finding PatientRating instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PatientRating as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findByDefaultCode(Object defaultCode) {
		return findByProperty(DEFAULT_CODE, defaultCode);
	}

	public List findAll() {
		log.debug("finding all PatientRating instances");
		try {
			String queryString = "from PatientRating";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PatientRating merge(PatientRating detachedInstance) {
		log.debug("merging PatientRating instance");
		try {
			PatientRating result = (PatientRating) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PatientRating instance) {
		log.debug("attaching dirty PatientRating instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PatientRating instance) {
		log.debug("attaching clean PatientRating instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientRatingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PatientRatingDAO) ctx.getBean("PatientRatingDAO");
	}
}