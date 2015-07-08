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
 * DoctorOrderDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrderDetails
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DoctorOrderDetailsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String RESPONSIBLE_DEPARTMENT_ID = "responsibleDepartmentId";
	public static final String SERVICE_ID = "serviceId";
	public static final String PACKAGE_ID = "packageId";
	public static final String ACTION_DESC = "actionDesc";
	public static final String ACTION_REMARKS = "actionRemarks";
	public static final String ACTION_TAKEN_BY = "actionTakenBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrderDetails transientInstance) {
		log.debug("saving DoctorOrderDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrderDetails persistentInstance) {
		log.debug("deleting DoctorOrderDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrderDetails findById(
			com.wtc.hcis.ip.da.DoctorOrderDetailsId id) {
		log.debug("getting DoctorOrderDetails instance with id: " + id);
		try {
			DoctorOrderDetails instance = (DoctorOrderDetails) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DoctorOrderDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrderDetails instance) {
		log.debug("finding DoctorOrderDetails instance by example");
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
		log.debug("finding DoctorOrderDetails instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorOrderDetails as model where model."
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

	public List findByResponsibleDepartmentId(Object responsibleDepartmentId) {
		return findByProperty(RESPONSIBLE_DEPARTMENT_ID,
				responsibleDepartmentId);
	}

	public List findByServiceId(Object serviceId) {
		return findByProperty(SERVICE_ID, serviceId);
	}

	public List findByPackageId(Object packageId) {
		return findByProperty(PACKAGE_ID, packageId);
	}

	public List findByActionDesc(Object actionDesc) {
		return findByProperty(ACTION_DESC, actionDesc);
	}

	public List findByActionRemarks(Object actionRemarks) {
		return findByProperty(ACTION_REMARKS, actionRemarks);
	}

	public List findByActionTakenBy(Object actionTakenBy) {
		return findByProperty(ACTION_TAKEN_BY, actionTakenBy);
	}

	public List findAll() {
		log.debug("finding all DoctorOrderDetails instances");
		try {
			String queryString = "from DoctorOrderDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrderDetails merge(DoctorOrderDetails detachedInstance) {
		log.debug("merging DoctorOrderDetails instance");
		try {
			DoctorOrderDetails result = (DoctorOrderDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrderDetails instance) {
		log.debug("attaching dirty DoctorOrderDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrderDetails instance) {
		log.debug("attaching clean DoctorOrderDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderDetailsDAO) ctx.getBean("DoctorOrderDetailsDAO");
	}
}