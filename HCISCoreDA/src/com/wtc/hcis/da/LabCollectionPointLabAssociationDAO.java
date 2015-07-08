package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * LabCollectionPointLabAssociation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.LabCollectionPointLabAssociation
 * @author MyEclipse Persistence Tools
 */

public class LabCollectionPointLabAssociationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabCollectionPointLabAssociationDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabCollectionPointLabAssociation transientInstance) {
		log.debug("saving LabCollectionPointLabAssociation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabCollectionPointLabAssociation persistentInstance) {
		log.debug("deleting LabCollectionPointLabAssociation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabCollectionPointLabAssociation findById(
			com.wtc.hcis.da.LabCollectionPointLabAssociationId id) {
		log.debug("getting LabCollectionPointLabAssociation instance with id: "
				+ id);
		try {
			LabCollectionPointLabAssociation instance = (LabCollectionPointLabAssociation) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabCollectionPointLabAssociation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabCollectionPointLabAssociation instance) {
		log
				.debug("finding LabCollectionPointLabAssociation instance by example");
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
		log
				.debug("finding LabCollectionPointLabAssociation instance with property: "
						+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabCollectionPointLabAssociation as model where model."
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

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabCollectionPointLabAssociation instances");
		try {
			String queryString = "from LabCollectionPointLabAssociation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabCollectionPointLabAssociation merge(
			LabCollectionPointLabAssociation detachedInstance) {
		log.debug("merging LabCollectionPointLabAssociation instance");
		try {
			LabCollectionPointLabAssociation result = (LabCollectionPointLabAssociation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabCollectionPointLabAssociation instance) {
		log.debug("attaching dirty LabCollectionPointLabAssociation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabCollectionPointLabAssociation instance) {
		log.debug("attaching clean LabCollectionPointLabAssociation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabCollectionPointLabAssociationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabCollectionPointLabAssociationDAO) ctx
				.getBean("LabCollectionPointLabAssociationDAO");
	}
}