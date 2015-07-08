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
 * LabPatientTestDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabPatientTestDetail
 * @author MyEclipse Persistence Tools
 */

public class LabPatientTestDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabPatientTestDetailDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String STATUS_CODE = "statusCode";
	public static final String APPROVER_NAME = "approverName";
	public static final String INVESTIGATOR_ID = "investigatorId";
	public static final String REMARKS = "remarks";
	public static final String CREATED_DATE_DIM = "createdDateDim";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";
	public static final String REPORT_COLLECTED_BY = "reportCollectedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabPatientTestDetail transientInstance) {
		log.debug("saving LabPatientTestDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabPatientTestDetail persistentInstance) {
		log.debug("deleting LabPatientTestDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabPatientTestDetail findById(java.lang.String id) {
		log.debug("getting LabPatientTestDetail instance with id: " + id);
		try {
			LabPatientTestDetail instance = (LabPatientTestDetail) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabPatientTestDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabPatientTestDetail instance) {
		log.debug("finding LabPatientTestDetail instance by example");
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
		log.debug("finding LabPatientTestDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabPatientTestDetail as model where model."
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

	public List findByApproverName(Object approverName) {
		return findByProperty(APPROVER_NAME, approverName);
	}

	public List findByInvestigatorId(Object investigatorId) {
		return findByProperty(INVESTIGATOR_ID, investigatorId);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedDateDim(Object createdDateDim) {
		return findByProperty(CREATED_DATE_DIM, createdDateDim);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findByReportCollectedBy(Object reportCollectedBy) {
		return findByProperty(REPORT_COLLECTED_BY, reportCollectedBy);
	}

	public List findAll() {
		log.debug("finding all LabPatientTestDetail instances");
		try {
			String queryString = "from LabPatientTestDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabPatientTestDetail merge(LabPatientTestDetail detachedInstance) {
		log.debug("merging LabPatientTestDetail instance");
		try {
			LabPatientTestDetail result = (LabPatientTestDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabPatientTestDetail instance) {
		log.debug("attaching dirty LabPatientTestDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabPatientTestDetail instance) {
		log.debug("attaching clean LabPatientTestDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabPatientTestDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabPatientTestDetailDAO) ctx.getBean("LabPatientTestDetailDAO");
	}
}