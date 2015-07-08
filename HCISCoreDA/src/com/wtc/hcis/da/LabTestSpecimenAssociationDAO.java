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
 * LabTestSpecimenAssociation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.LabTestSpecimenAssociation
 * @author MyEclipse Persistence Tools
 */

public class LabTestSpecimenAssociationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabTestSpecimenAssociationDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String QUANTITY = "quantity";
	public static final String UNIT = "unit";
	public static final String IS_MANDATORY = "isMandatory";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTestSpecimenAssociation transientInstance) {
		log.debug("saving LabTestSpecimenAssociation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTestSpecimenAssociation persistentInstance) {
		log.debug("deleting LabTestSpecimenAssociation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTestSpecimenAssociation findById(
			com.wtc.hcis.da.LabTestSpecimenAssociationId id) {
		log.debug("getting LabTestSpecimenAssociation instance with id: " + id);
		try {
			LabTestSpecimenAssociation instance = (LabTestSpecimenAssociation) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTestSpecimenAssociation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTestSpecimenAssociation instance) {
		log.debug("finding LabTestSpecimenAssociation instance by example");
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
		log.debug("finding LabTestSpecimenAssociation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTestSpecimenAssociation as model where model."
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

	public List findByQuantity(Object quantity) {
		return findByProperty(QUANTITY, quantity);
	}

	public List findByUnit(Object unit) {
		return findByProperty(UNIT, unit);
	}

	public List findByIsMandatory(Object isMandatory) {
		return findByProperty(IS_MANDATORY, isMandatory);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabTestSpecimenAssociation instances");
		try {
			String queryString = "from LabTestSpecimenAssociation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTestSpecimenAssociation merge(
			LabTestSpecimenAssociation detachedInstance) {
		log.debug("merging LabTestSpecimenAssociation instance");
		try {
			LabTestSpecimenAssociation result = (LabTestSpecimenAssociation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTestSpecimenAssociation instance) {
		log.debug("attaching dirty LabTestSpecimenAssociation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTestSpecimenAssociation instance) {
		log.debug("attaching clean LabTestSpecimenAssociation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestSpecimenAssociationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTestSpecimenAssociationDAO) ctx
				.getBean("LabTestSpecimenAssociationDAO");
	}
}