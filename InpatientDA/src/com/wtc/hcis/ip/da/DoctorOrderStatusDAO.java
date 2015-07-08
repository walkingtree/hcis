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
 * DoctorOrderStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrderStatus
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DoctorOrderStatusDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String STATUS_DESC = "statusDesc";
	public static final String CUSTOM_STATUS_DESC = "customStatusDesc";
	public static final String PROCESS_NAME = "processName";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrderStatus transientInstance) {
		log.debug("saving DoctorOrderStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrderStatus persistentInstance) {
		log.debug("deleting DoctorOrderStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrderStatus findById(java.lang.String id) {
		log.debug("getting DoctorOrderStatus instance with id: " + id);
		try {
			DoctorOrderStatus instance = (DoctorOrderStatus) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DoctorOrderStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrderStatus instance) {
		log.debug("finding DoctorOrderStatus instance by example");
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
		log.debug("finding DoctorOrderStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorOrderStatus as model where model."
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

	public List findByStatusDesc(Object statusDesc) {
		return findByProperty(STATUS_DESC, statusDesc);
	}

	public List findByCustomStatusDesc(Object customStatusDesc) {
		return findByProperty(CUSTOM_STATUS_DESC, customStatusDesc);
	}

	public List findByProcessName(Object processName) {
		return findByProperty(PROCESS_NAME, processName);
	}

	public List findAll() {
		log.debug("finding all DoctorOrderStatus instances");
		try {
			String queryString = "from DoctorOrderStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrderStatus merge(DoctorOrderStatus detachedInstance) {
		log.debug("merging DoctorOrderStatus instance");
		try {
			DoctorOrderStatus result = (DoctorOrderStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrderStatus instance) {
		log.debug("attaching dirty DoctorOrderStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrderStatus instance) {
		log.debug("attaching clean DoctorOrderStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderStatusDAO) ctx.getBean("DoctorOrderStatusDAO");
	}
}