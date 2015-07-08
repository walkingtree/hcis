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
 * LabDetails entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabDetails
 * @author MyEclipse Persistence Tools
 */

public class LabDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LabDetailsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String LAB_NAME = "labName";
	public static final String LAB_TYPE = "labType";
	public static final String BUSINESS_NAME = "businessName";
	public static final String BRANCH_NAME = "branchName";
	public static final String DIRECTION_FROM_KNOWN_PLACE = "directionFromKnownPlace";
	public static final String LAB_OPERATOR_ID = "labOperatorId";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabDetails transientInstance) {
		log.debug("saving LabDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabDetails persistentInstance) {
		log.debug("deleting LabDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabDetails findById(java.lang.String id) {
		log.debug("getting LabDetails instance with id: " + id);
		try {
			LabDetails instance = (LabDetails) getHibernateTemplate().get(
					"com.wtc.hcis.da.LabDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabDetails instance) {
		log.debug("finding LabDetails instance by example");
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
		log.debug("finding LabDetails instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from LabDetails as model where model."
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

	public List findByLabName(Object labName) {
		return findByProperty(LAB_NAME, labName);
	}

	public List findByLabType(Object labType) {
		return findByProperty(LAB_TYPE, labType);
	}

	public List findByBusinessName(Object businessName) {
		return findByProperty(BUSINESS_NAME, businessName);
	}

	public List findByBranchName(Object branchName) {
		return findByProperty(BRANCH_NAME, branchName);
	}

	public List findByDirectionFromKnownPlace(Object directionFromKnownPlace) {
		return findByProperty(DIRECTION_FROM_KNOWN_PLACE,
				directionFromKnownPlace);
	}

	public List findByLabOperatorId(Object labOperatorId) {
		return findByProperty(LAB_OPERATOR_ID, labOperatorId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabDetails instances");
		try {
			String queryString = "from LabDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabDetails merge(LabDetails detachedInstance) {
		log.debug("merging LabDetails instance");
		try {
			LabDetails result = (LabDetails) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabDetails instance) {
		log.debug("attaching dirty LabDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabDetails instance) {
		log.debug("attaching clean LabDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabDetailsDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LabDetailsDAO) ctx.getBean("LabDetailsDAO");
	}
}