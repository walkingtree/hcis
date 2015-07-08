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
 * LabTest entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTest
 * @author MyEclipse Persistence Tools
 */

public class LabTestDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LabTestDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String TEST_NAME = "testName";
	public static final String TEST_TYPE = "testType";
	public static final String AVAILABLE_FOR_GENDER = "availableForGender";
	public static final String PRE_REQUISITE = "preRequisite";
	public static final String MIN_TIME_REQUIRED = "minTimeRequired";
	public static final String REVIEW_REQUIRED = "reviewRequired";
	public static final String DEFAULT_REVIEWER_ID = "defaultReviewerId";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTest transientInstance) {
		log.debug("saving LabTest instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTest persistentInstance) {
		log.debug("deleting LabTest instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTest findById(java.lang.String id) {
		log.debug("getting LabTest instance with id: " + id);
		try {
			LabTest instance = (LabTest) getHibernateTemplate().get(
					"com.wtc.hcis.da.LabTest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTest instance) {
		log.debug("finding LabTest instance by example");
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
		log.debug("finding LabTest instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LabTest as model where model."
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

	public List findByTestName(Object testName) {
		return findByProperty(TEST_NAME, testName);
	}

	public List findByTestType(Object testType) {
		return findByProperty(TEST_TYPE, testType);
	}

	public List findByAvailableForGender(Object availableForGender) {
		return findByProperty(AVAILABLE_FOR_GENDER, availableForGender);
	}

	public List findByPreRequisite(Object preRequisite) {
		return findByProperty(PRE_REQUISITE, preRequisite);
	}

	public List findByMinTimeRequired(Object minTimeRequired) {
		return findByProperty(MIN_TIME_REQUIRED, minTimeRequired);
	}

	public List findByReviewRequired(Object reviewRequired) {
		return findByProperty(REVIEW_REQUIRED, reviewRequired);
	}

	public List findByDefaultReviewerId(Object defaultReviewerId) {
		return findByProperty(DEFAULT_REVIEWER_ID, defaultReviewerId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabTest instances");
		try {
			String queryString = "from LabTest";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTest merge(LabTest detachedInstance) {
		log.debug("merging LabTest instance");
		try {
			LabTest result = (LabTest) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTest instance) {
		log.debug("attaching dirty LabTest instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTest instance) {
		log.debug("attaching clean LabTest instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LabTestDAO) ctx.getBean("LabTestDAO");
	}
}