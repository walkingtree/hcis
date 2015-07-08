package com.wtc.hcis.ip.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DoctorOrderType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrderType
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(DoctorOrderTypeDAO.class);
	// property constants
	public static final String ORDER_TYPE_DESC = "orderTypeDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrderType transientInstance) {
		log.debug("saving DoctorOrderType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrderType persistentInstance) {
		log.debug("deleting DoctorOrderType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrderType findById(java.lang.String id) {
		log.debug("getting DoctorOrderType instance with id: " + id);
		try {
			DoctorOrderType instance = (DoctorOrderType) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DoctorOrderType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrderType instance) {
		log.debug("finding DoctorOrderType instance by example");
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
		log.debug("finding DoctorOrderType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorOrderType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOrderTypeDesc(Object orderTypeDesc) {
		return findByProperty(ORDER_TYPE_DESC, orderTypeDesc);
	}

	public List findAll() {
		log.debug("finding all DoctorOrderType instances");
		try {
			String queryString = "from DoctorOrderType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrderType merge(DoctorOrderType detachedInstance) {
		log.debug("merging DoctorOrderType instance");
		try {
			DoctorOrderType result = (DoctorOrderType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrderType instance) {
		log.debug("attaching dirty DoctorOrderType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrderType instance) {
		log.debug("attaching clean DoctorOrderType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderTypeDAO) ctx.getBean("DoctorOrderTypeDAO");
	}
}