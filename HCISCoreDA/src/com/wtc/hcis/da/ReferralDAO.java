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
 * Referral entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Referral
 * @author MyEclipse Persistence Tools
 */

public class ReferralDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ReferralDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String REFERRAL_TYPE_CODE = "referralTypeCode";
	public static final String QUALIFICATION = "qualification";
	public static final String REFERRAL_NAME = "referralName";
	public static final String PREFERRED_CONTACT_TIME = "preferredContactTime";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String STATE_CODE = "stateCode";
	public static final String PIN_CODE = "pinCode";
	public static final String PHONE = "phone";
	public static final String MOBILE = "mobile";
	public static final String FAX = "fax";
	public static final String EMAIL = "email";
	public static final String CREATED_BY = "createdBy";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(Referral transientInstance) {
		log.debug("saving Referral instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Referral persistentInstance) {
		log.debug("deleting Referral instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Referral findById(java.lang.Integer id) {
		log.debug("getting Referral instance with id: " + id);
		try {
			Referral instance = (Referral) getHibernateTemplate().get(
					"com.wtc.hcis.da.Referral", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Referral instance) {
		log.debug("finding Referral instance by example");
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
		log.debug("finding Referral instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Referral as model where model."
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

	public List findByReferralTypeCode(Object referralTypeCode) {
		return findByProperty(REFERRAL_TYPE_CODE, referralTypeCode);
	}

	public List findByQualification(Object qualification) {
		return findByProperty(QUALIFICATION, qualification);
	}

	public List findByReferralName(Object referralName) {
		return findByProperty(REFERRAL_NAME, referralName);
	}

	public List findByPreferredContactTime(Object preferredContactTime) {
		return findByProperty(PREFERRED_CONTACT_TIME, preferredContactTime);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByStateCode(Object stateCode) {
		return findByProperty(STATE_CODE, stateCode);
	}

	public List findByPinCode(Object pinCode) {
		return findByProperty(PIN_CODE, pinCode);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByMobile(Object mobile) {
		return findByProperty(MOBILE, mobile);
	}

	public List findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all Referral instances");
		try {
			String queryString = "from Referral";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Referral merge(Referral detachedInstance) {
		log.debug("merging Referral instance");
		try {
			Referral result = (Referral) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Referral instance) {
		log.debug("attaching dirty Referral instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Referral instance) {
		log.debug("attaching clean Referral instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferralDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReferralDAO) ctx.getBean("ReferralDAO");
	}
}