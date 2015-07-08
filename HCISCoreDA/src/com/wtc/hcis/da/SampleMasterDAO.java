package com.wtc.hcis.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SampleMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.SampleMaster
 * @author MyEclipse Persistence Tools
 */

public class SampleMasterDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SampleMasterDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String SAMPLE_DESCRIPTION = "sampleDescription";
	public static final String ACTIVE = "active";

	protected void initDao() {
		// do nothing
	}

	public void save(SampleMaster transientInstance) {
		log.debug("saving SampleMaster instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SampleMaster persistentInstance) {
		log.debug("deleting SampleMaster instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SampleMaster findById(java.lang.String id) {
		log.debug("getting SampleMaster instance with id: " + id);
		try {
			SampleMaster instance = (SampleMaster) getHibernateTemplate().get(
					"com.wtc.hcis.da.SampleMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SampleMaster instance) {
		log.debug("finding SampleMaster instance by example");
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
		log.debug("finding SampleMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SampleMaster as model where model."
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

	public List findBySampleDescription(Object sampleDescription) {
		return findByProperty(SAMPLE_DESCRIPTION, sampleDescription);
	}

	public List findByActive(Object active) {
		return findByProperty(ACTIVE, active);
	}

	public List findAll() {
		log.debug("finding all SampleMaster instances");
		try {
			String queryString = "from SampleMaster";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SampleMaster merge(SampleMaster detachedInstance) {
		log.debug("merging SampleMaster instance");
		try {
			SampleMaster result = (SampleMaster) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SampleMaster instance) {
		log.debug("attaching dirty SampleMaster instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SampleMaster instance) {
		log.debug("attaching clean SampleMaster instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SampleMasterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SampleMasterDAO) ctx.getBean("SampleMasterDAO");
	}
}