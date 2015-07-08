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
 * NursingUnitType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.NursingUnitType
 * @author MyEclipse Persistence Tools
 */

public class NursingUnitTypeDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(NursingUnitTypeDAO.class);
	// property constants
	public static final String UNIT_TYPE_DESC = "unitTypeDesc";
	public static final String PARENT_UNIT_TYPE_CD = "parentUnitTypeCd";
	public static final String TOTAL_BED_COUNT = "totalBedCount";
	public static final String AVAILABLE_BED_COUNT = "availableBedCount";
	public static final String THRESHOLD_FOR_CONFIRMATION = "thresholdForConfirmation";
	public static final String THRESHOLD_FOR_WAITLIST = "thresholdForWaitlist";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(NursingUnitType transientInstance) {
		log.debug("saving NursingUnitType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(NursingUnitType persistentInstance) {
		log.debug("deleting NursingUnitType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public NursingUnitType findById(java.lang.String id) {
		log.debug("getting NursingUnitType instance with id: " + id);
		try {
			NursingUnitType instance = (NursingUnitType) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.NursingUnitType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(NursingUnitType instance) {
		log.debug("finding NursingUnitType instance by example");
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
		log.debug("finding NursingUnitType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from NursingUnitType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUnitTypeDesc(Object unitTypeDesc) {
		return findByProperty(UNIT_TYPE_DESC, unitTypeDesc);
	}

	public List findByParentUnitTypeCd(Object parentUnitTypeCd) {
		return findByProperty(PARENT_UNIT_TYPE_CD, parentUnitTypeCd);
	}

	public List findByTotalBedCount(Object totalBedCount) {
		return findByProperty(TOTAL_BED_COUNT, totalBedCount);
	}

	public List findByAvailableBedCount(Object availableBedCount) {
		return findByProperty(AVAILABLE_BED_COUNT, availableBedCount);
	}

	public List findByThresholdForConfirmation(Object thresholdForConfirmation) {
		return findByProperty(THRESHOLD_FOR_CONFIRMATION,
				thresholdForConfirmation);
	}

	public List findByThresholdForWaitlist(Object thresholdForWaitlist) {
		return findByProperty(THRESHOLD_FOR_WAITLIST, thresholdForWaitlist);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all NursingUnitType instances");
		try {
			String queryString = "from NursingUnitType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public NursingUnitType merge(NursingUnitType detachedInstance) {
		log.debug("merging NursingUnitType instance");
		try {
			NursingUnitType result = (NursingUnitType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(NursingUnitType instance) {
		log.debug("attaching dirty NursingUnitType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(NursingUnitType instance) {
		log.debug("attaching clean NursingUnitType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NursingUnitTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (NursingUnitTypeDAO) ctx.getBean("NursingUnitTypeDAO");
	}
}