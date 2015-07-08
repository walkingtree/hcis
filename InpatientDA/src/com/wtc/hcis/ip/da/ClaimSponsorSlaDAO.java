package com.wtc.hcis.ip.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ClaimSponsorSla entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.ClaimSponsorSla
 * @author MyEclipse Persistence Tools
 */

public class ClaimSponsorSlaDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ClaimSponsorSlaDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SLA_PERIOD = "slaPeriod";
	public static final String PERIOD_UNIT = "periodUnit";

	protected void initDao() {
		// do nothing
	}

	public void save(ClaimSponsorSla transientInstance) {
		log.debug("saving ClaimSponsorSla instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ClaimSponsorSla persistentInstance) {
		log.debug("deleting ClaimSponsorSla instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ClaimSponsorSla findById(com.wtc.hcis.ip.da.ClaimSponsorSlaId id) {
		log.debug("getting ClaimSponsorSla instance with id: " + id);
		try {
			ClaimSponsorSla instance = (ClaimSponsorSla) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.ClaimSponsorSla", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ClaimSponsorSla instance) {
		log.debug("finding ClaimSponsorSla instance by example");
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
		log.debug("finding ClaimSponsorSla instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ClaimSponsorSla as model where model."
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

	public List findBySlaPeriod(Object slaPeriod) {
		return findByProperty(SLA_PERIOD, slaPeriod);
	}

	public List findByPeriodUnit(Object periodUnit) {
		return findByProperty(PERIOD_UNIT, periodUnit);
	}

	public List findAll() {
		log.debug("finding all ClaimSponsorSla instances");
		try {
			String queryString = "from ClaimSponsorSla";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ClaimSponsorSla merge(ClaimSponsorSla detachedInstance) {
		log.debug("merging ClaimSponsorSla instance");
		try {
			ClaimSponsorSla result = (ClaimSponsorSla) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ClaimSponsorSla instance) {
		log.debug("attaching dirty ClaimSponsorSla instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ClaimSponsorSla instance) {
		log.debug("attaching clean ClaimSponsorSla instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ClaimSponsorSlaDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ClaimSponsorSlaDAO) ctx.getBean("ClaimSponsorSlaDAO");
	}
}