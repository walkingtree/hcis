package com.wtc.hcis.ip.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SponsorClaimStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.SponsorClaimStatus
 * @author MyEclipse Persistence Tools
 */

public class SponsorClaimStatusDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(SponsorClaimStatusDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String CLAIM_STATUS_DESC = "claimStatusDesc";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(SponsorClaimStatus transientInstance) {
		log.debug("saving SponsorClaimStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SponsorClaimStatus persistentInstance) {
		log.debug("deleting SponsorClaimStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SponsorClaimStatus findById(java.lang.String id) {
		log.debug("getting SponsorClaimStatus instance with id: " + id);
		try {
			SponsorClaimStatus instance = (SponsorClaimStatus) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.SponsorClaimStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SponsorClaimStatus instance) {
		log.debug("finding SponsorClaimStatus instance by example");
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
		log.debug("finding SponsorClaimStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SponsorClaimStatus as model where model."
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

	public List findByClaimStatusDesc(Object claimStatusDesc) {
		return findByProperty(CLAIM_STATUS_DESC, claimStatusDesc);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all SponsorClaimStatus instances");
		try {
			String queryString = "from SponsorClaimStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SponsorClaimStatus merge(SponsorClaimStatus detachedInstance) {
		log.debug("merging SponsorClaimStatus instance");
		try {
			SponsorClaimStatus result = (SponsorClaimStatus) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SponsorClaimStatus instance) {
		log.debug("attaching dirty SponsorClaimStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SponsorClaimStatus instance) {
		log.debug("attaching clean SponsorClaimStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SponsorClaimStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SponsorClaimStatusDAO) ctx.getBean("SponsorClaimStatusDAO");
	}
}