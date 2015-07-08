package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReferenceDataList entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ReferenceDataList
 * @author MyEclipse Persistence Tools
 */

public class ReferenceDataListDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(ReferenceDataListDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String ATTR_DESC = "attrDesc";
	public static final String SEQ_NBR = "seqNbr";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(ReferenceDataList transientInstance) {
		log.debug("saving ReferenceDataList instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReferenceDataList persistentInstance) {
		log.debug("deleting ReferenceDataList instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferenceDataList findById(com.wtc.hcis.da.ReferenceDataListId id) {
		log.debug("getting ReferenceDataList instance with id: " + id);
		try {
			ReferenceDataList instance = (ReferenceDataList) getHibernateTemplate()
					.get("com.wtc.hcis.da.ReferenceDataList", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReferenceDataList instance) {
		log.debug("finding ReferenceDataList instance by example");
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
		log.debug("finding ReferenceDataList instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReferenceDataList as model where model."
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

	public List findByAttrDesc(Object attrDesc) {
		return findByProperty(ATTR_DESC, attrDesc);
	}

	public List findBySeqNbr(Object seqNbr) {
		return findByProperty(SEQ_NBR, seqNbr);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all ReferenceDataList instances");
		try {
			String queryString = "from ReferenceDataList";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReferenceDataList merge(ReferenceDataList detachedInstance) {
		log.debug("merging ReferenceDataList instance");
		try {
			ReferenceDataList result = (ReferenceDataList) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferenceDataList instance) {
		log.debug("attaching dirty ReferenceDataList instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferenceDataList instance) {
		log.debug("attaching clean ReferenceDataList instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferenceDataListDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReferenceDataListDAO) ctx.getBean("ReferenceDataListDAO");
	}
}