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
 * ClaimSponsor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ClaimSponsor
 * @author MyEclipse Persistence Tools
 */

public class ClaimSponsorDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ClaimSponsorDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SPONSOR_DESC = "sponsorDesc";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String CONTACT_CODE = "contactCode";
	public static final String CREATED_BY = "createdBy";
	public static final String LAST_MODIFIED_BY = "lastModifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ClaimSponsor transientInstance) {
		log.debug("saving ClaimSponsor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClaimSponsor persistentInstance) {
		log.debug("deleting ClaimSponsor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClaimSponsor findById(java.lang.String id) {
		log.debug("getting ClaimSponsor instance with id: " + id);
		try {
			ClaimSponsor instance = (ClaimSponsor) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.ClaimSponsor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ClaimSponsor instance) {
		log.debug("finding ClaimSponsor instance by example");
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
		log.debug("finding ClaimSponsor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ClaimSponsor as model where model."
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

	public List findBySponsorDesc(Object sponsorDesc) {
		return findByProperty(SPONSOR_DESC, sponsorDesc);
	}

	public List findByAccountNumber(Object accountNumber) {
		return findByProperty(ACCOUNT_NUMBER, accountNumber);
	}

	public List findByContactCode(Object contactCode) {
		return findByProperty(CONTACT_CODE, contactCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByLastModifiedBy(Object lastModifiedBy) {
		return findByProperty(LAST_MODIFIED_BY, lastModifiedBy);
	}

	public List findAll() {
		log.debug("finding all ClaimSponsor instances");
		try {
			String queryString = "from ClaimSponsor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClaimSponsor merge(ClaimSponsor detachedInstance) {
		log.debug("merging ClaimSponsor instance");
		try {
			ClaimSponsor result = (ClaimSponsor) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClaimSponsor instance) {
		log.debug("attaching dirty ClaimSponsor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClaimSponsor instance) {
		log.debug("attaching clean ClaimSponsor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClaimSponsorDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ClaimSponsorDAO) ctx.getBean("ClaimSponsorDAO");
	}
}