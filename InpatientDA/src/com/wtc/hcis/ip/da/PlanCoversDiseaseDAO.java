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
 * PlanCoversDisease entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.PlanCoversDisease
 * @author MyEclipse Persistence Tools
 */

public class PlanCoversDiseaseDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(PlanCoversDiseaseDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String IS_COVERD = "isCoverd";
	public static final String PERCENT_ABS_IND = "percentAbsInd";
	public static final String COVERAGE_AMT = "coverageAmt";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(PlanCoversDisease transientInstance) {
		log.debug("saving PlanCoversDisease instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PlanCoversDisease persistentInstance) {
		log.debug("deleting PlanCoversDisease instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PlanCoversDisease findById(com.wtc.hcis.ip.da.PlanCoversDiseaseId id) {
		log.debug("getting PlanCoversDisease instance with id: " + id);
		try {
			PlanCoversDisease instance = (PlanCoversDisease) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.PlanCoversDisease", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PlanCoversDisease instance) {
		log.debug("finding PlanCoversDisease instance by example");
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
		log.debug("finding PlanCoversDisease instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PlanCoversDisease as model where model."
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
		log.debug("finding all PlanCoversDisease instances");
		try {
			String queryString = "from PlanCoversDisease";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PlanCoversDisease merge(PlanCoversDisease detachedInstance) {
		log.debug("merging PlanCoversDisease instance");
		try {
			PlanCoversDisease result = (PlanCoversDisease) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PlanCoversDisease instance) {
		log.debug("attaching dirty PlanCoversDisease instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PlanCoversDisease instance) {
		log.debug("attaching clean PlanCoversDisease instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PlanCoversDiseaseDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PlanCoversDiseaseDAO) ctx.getBean("PlanCoversDiseaseDAO");
	}
}