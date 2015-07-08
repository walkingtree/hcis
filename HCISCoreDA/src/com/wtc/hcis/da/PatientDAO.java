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
 * Patient entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Patient
 * @author MyEclipse Persistence Tools
 */

public class PatientDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PatientDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String FIRST_NAME = "firstName";
	public static final String MIDDLE_NAME = "middleName";
	public static final String LAST_NAME = "lastName";
	public static final String FULL_NAME = "fullName";
	public static final String MONTHLY_INCOME = "monthlyIncome";
	public static final String OCCUPATION = "occupation";
	public static final String HEIGHT = "height";
	public static final String HEIGHT_IND = "heightInd";
	public static final String WEIGHT = "weight";
	public static final String WEIGHT_IND = "weightInd";
	public static final String FATHER_HUSBAND = "fatherHusband";
	public static final String IMAGE = "image";
	public static final String CURRENT_CONTACT_DETAIL_ID = "currentContactDetailId";
	public static final String PERMANENT_CONTACT_DETAIL_ID = "permanentContactDetailId";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Patient transientInstance) {
		log.debug("saving Patient instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Patient persistentInstance) {
		log.debug("deleting Patient instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Patient findById(java.lang.Integer id) {
		log.debug("getting Patient instance with id: " + id);
		try {
			Patient instance = (Patient) getHibernateTemplate().get(
					"com.wtc.hcis.da.Patient", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Patient instance) {
		log.debug("finding Patient instance by example");
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
		log.debug("finding Patient instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Patient as model where model."
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

	public List findByFullName(Object fullName) {
		return findByProperty(FULL_NAME, fullName);
	}

	public List findByMonthlyIncome(Object monthlyIncome) {
		return findByProperty(MONTHLY_INCOME, monthlyIncome);
	}

	public List findByOccupation(Object occupation) {
		return findByProperty(OCCUPATION, occupation);
	}

	public List findByHeight(Object height) {
		return findByProperty(HEIGHT, height);
	}

	public List findByHeightInd(Object heightInd) {
		return findByProperty(HEIGHT_IND, heightInd);
	}

	public List findByWeight(Object weight) {
		return findByProperty(WEIGHT, weight);
	}

	public List findByWeightInd(Object weightInd) {
		return findByProperty(WEIGHT_IND, weightInd);
	}

	public List findByFatherHusband(Object fatherHusband) {
		return findByProperty(FATHER_HUSBAND, fatherHusband);
	}

	public List findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List findByCurrentContactDetailId(Object currentContactDetailId) {
		return findByProperty(CURRENT_CONTACT_DETAIL_ID, currentContactDetailId);
	}

	public List findByPermanentContactDetailId(Object permanentContactDetailId) {
		return findByProperty(PERMANENT_CONTACT_DETAIL_ID,
				permanentContactDetailId);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Patient instances");
		try {
			String queryString = "from Patient";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Patient merge(Patient detachedInstance) {
		log.debug("merging Patient instance");
		try {
			Patient result = (Patient) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Patient instance) {
		log.debug("attaching dirty Patient instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Patient instance) {
		log.debug("attaching clean Patient instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PatientDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PatientDAO) ctx.getBean("PatientDAO");
	}
}