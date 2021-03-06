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
 * Service entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Service
 * @author MyEclipse Persistence Tools
 */

public class ServiceDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ServiceDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SERVICE_NAME = "serviceName";
	public static final String SERVICE_DESC = "serviceDesc";
	public static final String SERVICE_PROCEDURE = "serviceProcedure";
	public static final String SERVICE_CHARGE = "serviceCharge";
	public static final String SERVICE_TYPE_CD = "serviceTypeCd";
	public static final String CREATED_BY = "createdBy";
	public static final String DEPOSIT_AMT = "depositAmt";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Service transientInstance) {
		log.debug("saving Service instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Service persistentInstance) {
		log.debug("deleting Service instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Service findById(java.lang.String id) {
		log.debug("getting Service instance with id: " + id);
		try {
			Service instance = (Service) getHibernateTemplate().get(
					"com.wtc.hcis.da.Service", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Service instance) {
		log.debug("finding Service instance by example");
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
		log.debug("finding Service instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Service as model where model."
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

	public List findByServiceName(Object serviceName) {
		return findByProperty(SERVICE_NAME, serviceName);
	}

	public List findByServiceDesc(Object serviceDesc) {
		return findByProperty(SERVICE_DESC, serviceDesc);
	}

	public List findByServiceProcedure(Object serviceProcedure) {
		return findByProperty(SERVICE_PROCEDURE, serviceProcedure);
	}

	public List findByServiceCharge(Object serviceCharge) {
		return findByProperty(SERVICE_CHARGE, serviceCharge);
	}

	public List findByServiceTypeCd(Object serviceTypeCd) {
		return findByProperty(SERVICE_TYPE_CD, serviceTypeCd);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByDepositAmt(Object depositAmt) {
		return findByProperty(DEPOSIT_AMT, depositAmt);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Service instances");
		try {
			String queryString = "from Service";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Service merge(Service detachedInstance) {
		log.debug("merging Service instance");
		try {
			Service result = (Service) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Service instance) {
		log.debug("attaching dirty Service instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Service instance) {
		log.debug("attaching clean Service instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ServiceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ServiceDAO) ctx.getBean("ServiceDAO");
	}
}