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
 * ServicePriceDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ServicePriceDetail
 * @author MyEclipse Persistence Tools
 */

public class ServicePriceDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ServicePriceDetailDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SERVICE_CHARGE = "serviceCharge";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ServicePriceDetail transientInstance) {
		log.debug("saving ServicePriceDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ServicePriceDetail persistentInstance) {
		log.debug("deleting ServicePriceDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ServicePriceDetail findById(com.wtc.hcis.da.ServicePriceDetailId id) {
		log.debug("getting ServicePriceDetail instance with id: " + id);
		try {
			ServicePriceDetail instance = (ServicePriceDetail) getHibernateTemplate()
					.get("com.wtc.hcis.da.ServicePriceDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ServicePriceDetail instance) {
		log.debug("finding ServicePriceDetail instance by example");
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
		log.debug("finding ServicePriceDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ServicePriceDetail as model where model."
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

	public List findByServiceCharge(Object serviceCharge) {
		return findByProperty(SERVICE_CHARGE, serviceCharge);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all ServicePriceDetail instances");
		try {
			String queryString = "from ServicePriceDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ServicePriceDetail merge(ServicePriceDetail detachedInstance) {
		log.debug("merging ServicePriceDetail instance");
		try {
			ServicePriceDetail result = (ServicePriceDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ServicePriceDetail instance) {
		log.debug("attaching dirty ServicePriceDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ServicePriceDetail instance) {
		log.debug("attaching clean ServicePriceDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServicePriceDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ServicePriceDetailDAO) ctx.getBean("ServicePriceDetailDAO");
	}
}