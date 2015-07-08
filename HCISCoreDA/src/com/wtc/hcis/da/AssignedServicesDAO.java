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
 * AssignedServices entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.AssignedServices
 * @author MyEclipse Persistence Tools
 */

public class AssignedServicesDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AssignedServicesDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SEQ_NBR = "seqNbr";
	public static final String DOCTOR_ID = "doctorId";
	public static final String REQUESTED_UNITS = "requestedUnits";
	public static final String SERVICE_DATE_DIM_ID = "serviceDateDimId";
	public static final String RENDERED_UNITS = "renderedUnits";
	public static final String CANCELED_UNITS = "canceledUnits";
	public static final String BILLED_UNITS = "billedUnits";
	public static final String LAST_BILL_NBR = "lastBillNbr";
	public static final String SERVICE_CHARGE = "serviceCharge";
	public static final String REFERENCE_NUMBER = "referenceNumber";
	public static final String REFERENCE_TYPE = "referenceType";
	public static final String REMARKS = "remarks";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(AssignedServices transientInstance) {
		log.debug("saving AssignedServices instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(AssignedServices persistentInstance) {
		log.debug("deleting AssignedServices instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AssignedServices findById(java.lang.Integer id) {
		log.debug("getting AssignedServices instance with id: " + id);
		try {
			AssignedServices instance = (AssignedServices) getHibernateTemplate()
					.get("com.wtc.hcis.da.AssignedServices", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(AssignedServices instance) {
		log.debug("finding AssignedServices instance by example");
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
		log.debug("finding AssignedServices instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from AssignedServices as model where model."
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

	public List findBySeqNbr(Object seqNbr) {
		return findByProperty(SEQ_NBR, seqNbr);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByRequestedUnits(Object requestedUnits) {
		return findByProperty(REQUESTED_UNITS, requestedUnits);
	}

	public List findByServiceDateDimId(Object serviceDateDimId) {
		return findByProperty(SERVICE_DATE_DIM_ID, serviceDateDimId);
	}

	public List findByRenderedUnits(Object renderedUnits) {
		return findByProperty(RENDERED_UNITS, renderedUnits);
	}

	public List findByCanceledUnits(Object canceledUnits) {
		return findByProperty(CANCELED_UNITS, canceledUnits);
	}

	public List findByBilledUnits(Object billedUnits) {
		return findByProperty(BILLED_UNITS, billedUnits);
	}

	public List findByLastBillNbr(Object lastBillNbr) {
		return findByProperty(LAST_BILL_NBR, lastBillNbr);
	}

	public List findByServiceCharge(Object serviceCharge) {
		return findByProperty(SERVICE_CHARGE, serviceCharge);
	}

	public List findByReferenceNumber(Object referenceNumber) {
		return findByProperty(REFERENCE_NUMBER, referenceNumber);
	}

	public List findByReferenceType(Object referenceType) {
		return findByProperty(REFERENCE_TYPE, referenceType);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all AssignedServices instances");
		try {
			String queryString = "from AssignedServices";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AssignedServices merge(AssignedServices detachedInstance) {
		log.debug("merging AssignedServices instance");
		try {
			AssignedServices result = (AssignedServices) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(AssignedServices instance) {
		log.debug("attaching dirty AssignedServices instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(AssignedServices instance) {
		log.debug("attaching clean AssignedServices instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AssignedServicesDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AssignedServicesDAO) ctx.getBean("AssignedServicesDAO");
	}
}