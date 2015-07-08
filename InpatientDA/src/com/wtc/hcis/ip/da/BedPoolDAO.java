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
 * BedPool entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.BedPool
 * @author MyEclipse Persistence Tools
 */

public class BedPoolDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BedPoolDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String POOL_DESC = "poolDesc";
	public static final String TOTAL_NUMBER_OF_BED = "totalNumberOfBed";
	public static final String NUMBER_OF_AVAILABLE_BEDS = "numberOfAvailableBeds";

	protected void initDao() {
		// do nothing
	}

	public void save(BedPool transientInstance) {
		log.debug("saving BedPool instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BedPool persistentInstance) {
		log.debug("deleting BedPool instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BedPool findById(java.lang.String id) {
		log.debug("getting BedPool instance with id: " + id);
		try {
			BedPool instance = (BedPool) getHibernateTemplate().get(
					"com.wtc.hcis.ip.da.BedPool", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BedPool instance) {
		log.debug("finding BedPool instance by example");
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
		log.debug("finding BedPool instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BedPool as model where model."
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

	public List findByPoolDesc(Object poolDesc) {
		return findByProperty(POOL_DESC, poolDesc);
	}

	public List findByTotalNumberOfBed(Object totalNumberOfBed) {
		return findByProperty(TOTAL_NUMBER_OF_BED, totalNumberOfBed);
	}

	public List findByNumberOfAvailableBeds(Object numberOfAvailableBeds) {
		return findByProperty(NUMBER_OF_AVAILABLE_BEDS, numberOfAvailableBeds);
	}

	public List findAll() {
		log.debug("finding all BedPool instances");
		try {
			String queryString = "from BedPool";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BedPool merge(BedPool detachedInstance) {
		log.debug("merging BedPool instance");
		try {
			BedPool result = (BedPool) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BedPool instance) {
		log.debug("attaching dirty BedPool instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BedPool instance) {
		log.debug("attaching clean BedPool instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BedPoolDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BedPoolDAO) ctx.getBean("BedPoolDAO");
	}
}