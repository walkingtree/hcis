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
 * DoctorOrderGroup entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrderGroup
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderGroupDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DoctorOrderGroupDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DESCRIPTION = "description";
	public static final String GROUP_FOR_DOCTOR_ID = "groupForDoctorId";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrderGroup transientInstance) {
		log.debug("saving DoctorOrderGroup instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrderGroup persistentInstance) {
		log.debug("deleting DoctorOrderGroup instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrderGroup findById(java.lang.String id) {
		log.debug("getting DoctorOrderGroup instance with id: " + id);
		try {
			DoctorOrderGroup instance = (DoctorOrderGroup) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DoctorOrderGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrderGroup instance) {
		log.debug("finding DoctorOrderGroup instance by example");
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
		log.debug("finding DoctorOrderGroup instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorOrderGroup as model where model."
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

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByGroupForDoctorId(Object groupForDoctorId) {
		return findByProperty(GROUP_FOR_DOCTOR_ID, groupForDoctorId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DoctorOrderGroup instances");
		try {
			String queryString = "from DoctorOrderGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrderGroup merge(DoctorOrderGroup detachedInstance) {
		log.debug("merging DoctorOrderGroup instance");
		try {
			DoctorOrderGroup result = (DoctorOrderGroup) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrderGroup instance) {
		log.debug("attaching dirty DoctorOrderGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrderGroup instance) {
		log.debug("attaching clean DoctorOrderGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderGroupDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderGroupDAO) ctx.getBean("DoctorOrderGroupDAO");
	}
}