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
 * AdmissionClaimRequest entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.AdmissionClaimRequest
 * @author MyEclipse Persistence Tools
 */

public class AdmissionClaimRequestDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(AdmissionClaimRequestDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CLAIM_SUBSEQUENCE_NBR = "claimSubsequenceNbr";
	public static final String POLICY_NBR = "policyNbr";
	public static final String PREFERENCE_SEQUENCE_NBR = "preferenceSequenceNbr";
	public static final String POLICY_HOLDER_NAME = "policyHolderName";
	public static final String CREATED_BY = "createdBy";
	public static final String REQUESTED_AMOUNT = "requestedAmount";
	public static final String APPROVAL_THROUGH = "approvalThrough";
	public static final String APPROVED_AMOUNT = "approvedAmount";
	public static final String FINAL_CLAIMED_AMOUNT = "finalClaimedAmount";
	public static final String PATIENT_AMOUNT = "patientAmount";
	public static final String BILL_NBR = "billNbr";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(AdmissionClaimRequest transientInstance) {
		log.debug("saving AdmissionClaimRequest instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AdmissionClaimRequest persistentInstance) {
		log.debug("deleting AdmissionClaimRequest instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AdmissionClaimRequest findById(java.lang.Long id) {
		log.debug("getting AdmissionClaimRequest instance with id: " + id);
		try {
			AdmissionClaimRequest instance = (AdmissionClaimRequest) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.AdmissionClaimRequest", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AdmissionClaimRequest instance) {
		log.debug("finding AdmissionClaimRequest instance by example");
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
		log.debug("finding AdmissionClaimRequest instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AdmissionClaimRequest as model where model."
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

	public List findByClaimSubsequenceNbr(Object claimSubsequenceNbr) {
		return findByProperty(CLAIM_SUBSEQUENCE_NBR, claimSubsequenceNbr);
	}

	public List findByPolicyNbr(Object policyNbr) {
		return findByProperty(POLICY_NBR, policyNbr);
	}

	public List findByPreferenceSequenceNbr(Object preferenceSequenceNbr) {
		return findByProperty(PREFERENCE_SEQUENCE_NBR, preferenceSequenceNbr);
	}

	public List findByPolicyHolderName(Object policyHolderName) {
		return findByProperty(POLICY_HOLDER_NAME, policyHolderName);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByRequestedAmount(Object requestedAmount) {
		return findByProperty(REQUESTED_AMOUNT, requestedAmount);
	}

	public List findByApprovalThrough(Object approvalThrough) {
		return findByProperty(APPROVAL_THROUGH, approvalThrough);
	}

	public List findByApprovedAmount(Object approvedAmount) {
		return findByProperty(APPROVED_AMOUNT, approvedAmount);
	}

	public List findByFinalClaimedAmount(Object finalClaimedAmount) {
		return findByProperty(FINAL_CLAIMED_AMOUNT, finalClaimedAmount);
	}

	public List findByPatientAmount(Object patientAmount) {
		return findByProperty(PATIENT_AMOUNT, patientAmount);
	}

	public List findByBillNbr(Object billNbr) {
		return findByProperty(BILL_NBR, billNbr);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all AdmissionClaimRequest instances");
		try {
			String queryString = "from AdmissionClaimRequest";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AdmissionClaimRequest merge(AdmissionClaimRequest detachedInstance) {
		log.debug("merging AdmissionClaimRequest instance");
		try {
			AdmissionClaimRequest result = (AdmissionClaimRequest) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AdmissionClaimRequest instance) {
		log.debug("attaching dirty AdmissionClaimRequest instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AdmissionClaimRequest instance) {
		log.debug("attaching clean AdmissionClaimRequest instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AdmissionClaimRequestDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AdmissionClaimRequestDAO) ctx
				.getBean("AdmissionClaimRequestDAO");
	}
}