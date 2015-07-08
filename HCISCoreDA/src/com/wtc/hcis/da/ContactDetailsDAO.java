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
 * ContactDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ContactDetails
 * @author MyEclipse Persistence Tools
 */

public class ContactDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ContactDetailsDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String FIRST_NAME = "firstName";
	public static final String MIDDLE_NAME = "middleName";
	public static final String LAST_NAME = "lastName";
	public static final String ADDRESS_LINE1 = "addressLine1";
	public static final String ADDRESS_LINE2 = "addressLine2";
	public static final String CITY = "city";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String STATE_CODE = "stateCode";
	public static final String PINCODE = "pincode";
	public static final String CONTACT_NUMBER = "contactNumber";
	public static final String MOBILE_NUMBER = "mobileNumber";
	public static final String FAX_NUMBER = "faxNumber";
	public static final String EMAIL = "email";
	public static final String STAY_DURATION = "stayDuration";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ContactDetails transientInstance) {
		log.debug("saving ContactDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ContactDetails persistentInstance) {
		log.debug("deleting ContactDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ContactDetails findById(java.lang.Integer id) {
		log.debug("getting ContactDetails instance with id: " + id);
		try {
			ContactDetails instance = (ContactDetails) getHibernateTemplate()
					.get("com.wtc.hcis.da.ContactDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ContactDetails instance) {
		log.debug("finding ContactDetails instance by example");
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
		log.debug("finding ContactDetails instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ContactDetails as model where model."
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

	public List findByFirstName(Object firstName) {
		return findByProperty(FIRST_NAME, firstName);
	}

	public List findByMiddleName(Object middleName) {
		return findByProperty(MIDDLE_NAME, middleName);
	}

	public List findByLastName(Object lastName) {
		return findByProperty(LAST_NAME, lastName);
	}

	public List findByAddressLine1(Object addressLine1) {
		return findByProperty(ADDRESS_LINE1, addressLine1);
	}

	public List findByAddressLine2(Object addressLine2) {
		return findByProperty(ADDRESS_LINE2, addressLine2);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByCountryCode(Object countryCode) {
		return findByProperty(COUNTRY_CODE, countryCode);
	}

	public List findByStateCode(Object stateCode) {
		return findByProperty(STATE_CODE, stateCode);
	}

	public List findByPincode(Object pincode) {
		return findByProperty(PINCODE, pincode);
	}

	public List findByContactNumber(Object contactNumber) {
		return findByProperty(CONTACT_NUMBER, contactNumber);
	}

	public List findByMobileNumber(Object mobileNumber) {
		return findByProperty(MOBILE_NUMBER, mobileNumber);
	}

	public List findByFaxNumber(Object faxNumber) {
		return findByProperty(FAX_NUMBER, faxNumber);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByStayDuration(Object stayDuration) {
		return findByProperty(STAY_DURATION, stayDuration);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all ContactDetails instances");
		try {
			String queryString = "from ContactDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ContactDetails merge(ContactDetails detachedInstance) {
		log.debug("merging ContactDetails instance");
		try {
			ContactDetails result = (ContactDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ContactDetails instance) {
		log.debug("attaching dirty ContactDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ContactDetails instance) {
		log.debug("attaching clean ContactDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ContactDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ContactDetailsDAO) ctx.getBean("ContactDetailsDAO");
	}
}