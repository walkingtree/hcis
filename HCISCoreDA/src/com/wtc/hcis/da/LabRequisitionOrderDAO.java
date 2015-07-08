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
 * LabRequisitionOrder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabRequisitionOrder
 * @author MyEclipse Persistence Tools
 */

public class LabRequisitionOrderDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabRequisitionOrderDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String STATUS_CODE = "statusCode";
	public static final String CREATED_BY = "createdBy";
	public static final String TOTAL_CHARGES = "totalCharges";
	public static final String REFUNDED_AMT = "refundedAmt";
	public static final String REFUNDABLE_AMT = "refundableAmt";
	public static final String REFERRAL_CODE = "referralCode";
	public static final String IS_EMERGENCY = "isEmergency";
	public static final String CREATED_DATE_DIM = "createdDateDim";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabRequisitionOrder transientInstance) {
		log.debug("saving LabRequisitionOrder instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabRequisitionOrder persistentInstance) {
		log.debug("deleting LabRequisitionOrder instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabRequisitionOrder findById(java.lang.Integer id) {
		log.debug("getting LabRequisitionOrder instance with id: " + id);
		try {
			LabRequisitionOrder instance = (LabRequisitionOrder) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabRequisitionOrder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabRequisitionOrder instance) {
		log.debug("finding LabRequisitionOrder instance by example");
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
		log.debug("finding LabRequisitionOrder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabRequisitionOrder as model where model."
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

	public List findByStatusCode(Object statusCode) {
		return findByProperty(STATUS_CODE, statusCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByTotalCharges(Object totalCharges) {
		return findByProperty(TOTAL_CHARGES, totalCharges);
	}

	public List findByRefundedAmt(Object refundedAmt) {
		return findByProperty(REFUNDED_AMT, refundedAmt);
	}

	public List findByRefundableAmt(Object refundableAmt) {
		return findByProperty(REFUNDABLE_AMT, refundableAmt);
	}

	public List findByReferralCode(Object referralCode) {
		return findByProperty(REFERRAL_CODE, referralCode);
	}

	public List findByIsEmergency(Object isEmergency) {
		return findByProperty(IS_EMERGENCY, isEmergency);
	}

	public List findByCreatedDateDim(Object createdDateDim) {
		return findByProperty(CREATED_DATE_DIM, createdDateDim);
	}

	public List findByLastModifiedBy(Object lastModifiedBy) {
		return findByProperty(LAST_MODIFIED_BY, lastModifiedBy);
	}

	public List findAll() {
		log.debug("finding all LabRequisitionOrder instances");
		try {
			String queryString = "from LabRequisitionOrder";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabRequisitionOrder merge(LabRequisitionOrder detachedInstance) {
		log.debug("merging LabRequisitionOrder instance");
		try {
			LabRequisitionOrder result = (LabRequisitionOrder) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabRequisitionOrder instance) {
		log.debug("attaching dirty LabRequisitionOrder instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabRequisitionOrder instance) {
		log.debug("attaching clean LabRequisitionOrder instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabRequisitionOrderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabRequisitionOrderDAO) ctx.getBean("LabRequisitionOrderDAO");
	}
}