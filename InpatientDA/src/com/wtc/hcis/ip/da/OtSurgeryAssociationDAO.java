package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * OtSurgeryAssociation entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.OtSurgeryAssociation
 * @author MyEclipse Persistence Tools
 */

public class OtSurgeryAssociationDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(OtSurgeryAssociationDAO.class);
	// property constants
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(OtSurgeryAssociation transientInstance) {
		log.debug("saving OtSurgeryAssociation instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(OtSurgeryAssociation persistentInstance) {
		log.debug("deleting OtSurgeryAssociation instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public OtSurgeryAssociation findById(
			com.wtc.hcis.ip.da.OtSurgeryAssociationId id) {
		log.debug("getting OtSurgeryAssociation instance with id: " + id);
		try {
			OtSurgeryAssociation instance = (OtSurgeryAssociation) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.OtSurgeryAssociation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(OtSurgeryAssociation instance) {
		log.debug("finding OtSurgeryAssociation instance by example");
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
		log.debug("finding OtSurgeryAssociation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from OtSurgeryAssociation as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all OtSurgeryAssociation instances");
		try {
			String queryString = "from OtSurgeryAssociation";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public OtSurgeryAssociation merge(OtSurgeryAssociation detachedInstance) {
		log.debug("merging OtSurgeryAssociation instance");
		try {
			OtSurgeryAssociation result = (OtSurgeryAssociation) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(OtSurgeryAssociation instance) {
		log.debug("attaching dirty OtSurgeryAssociation instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(OtSurgeryAssociation instance) {
		log.debug("attaching clean OtSurgeryAssociation instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static OtSurgeryAssociationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (OtSurgeryAssociationDAO) ctx.getBean("OtSurgeryAssociationDAO");
	}
}