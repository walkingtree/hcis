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
 * PlanHasServices entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.PlanHasServices
 * @author MyEclipse Persistence Tools
 */

public class PlanHasServicesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PlanHasServicesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String IS_COVERD = "isCoverd";
	public static final String PERCENT_ABS_IND = "percentAbsInd";
	public static final String COVERAGE_AMT = "coverageAmt";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(PlanHasServices transientInstance) {
		log.debug("saving PlanHasServices instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PlanHasServices persistentInstance) {
		log.debug("deleting PlanHasServices instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PlanHasServices findById(com.wtc.hcis.ip.da.PlanHasServicesId id) {
		log.debug("getting PlanHasServices instance with id: " + id);
		try {
			PlanHasServices instance = (PlanHasServices) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.PlanHasServices", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PlanHasServices instance) {
		log.debug("finding PlanHasServices instance by example");
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
		log.debug("finding PlanHasServices instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PlanHasServices as model where model."
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

	public List findByIsCoverd(Object isCoverd) {
		return findByProperty(IS_COVERD, isCoverd);
	}

	public List findByPercentAbsInd(Object percentAbsInd) {
		return findByProperty(PERCENT_ABS_IND, percentAbsInd);
	}

	public List findByCoverageAmt(Object coverageAmt) {
		return findByProperty(COVERAGE_AMT, coverageAmt);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all PlanHasServices instances");
		try {
			String queryString = "from PlanHasServices";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PlanHasServices merge(PlanHasServices detachedInstance) {
		log.debug("merging PlanHasServices instance");
		try {
			PlanHasServices result = (PlanHasServices) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PlanHasServices instance) {
		log.debug("attaching dirty PlanHasServices instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PlanHasServices instance) {
		log.debug("attaching clean PlanHasServices instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanHasServicesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PlanHasServicesDAO) ctx.getBean("PlanHasServicesDAO");
	}
}