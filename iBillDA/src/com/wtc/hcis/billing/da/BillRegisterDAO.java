package com.wtc.hcis.billing.da;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillRegister entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillRegister
 * @author MyEclipse Persistence Tools
 */

public class BillRegisterDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillRegisterDAO.class);
	// property constants
	public static final String CREATE_DETAILS_FLAG = "createDetailsFlag";
	public static final String SYNC_ASYNC_TYPE = "syncAsyncType";
	public static final String ACTIVE_FLAG = "activeFlag";
	public static final String DISPLAY_SEQUENCE_NBR = "displaySequenceNbr";
	public static final String DISPLAY_DESCRIPTION = "displayDescription";
	public static final String IMPL_CLASS_NAME = "implClassName";

	protected void initDao() {
		// do nothing
	}

	public void save(BillRegister transientInstance) {
		log.debug("saving BillRegister instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillRegister persistentInstance) {
		log.debug("deleting BillRegister instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillRegister findById(java.lang.String id) {
		log.debug("getting BillRegister instance with id: " + id);
		try {
			BillRegister instance = (BillRegister) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.BillRegister", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillRegister instance) {
		log.debug("finding BillRegister instance by example");
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
		log.debug("finding BillRegister instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillRegister as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCreateDetailsFlag(Object createDetailsFlag) {
		return findByProperty(CREATE_DETAILS_FLAG, createDetailsFlag);
	}

	public List findBySyncAsyncType(Object syncAsyncType) {
		return findByProperty(SYNC_ASYNC_TYPE, syncAsyncType);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findByDisplaySequenceNbr(Object displaySequenceNbr) {
		return findByProperty(DISPLAY_SEQUENCE_NBR, displaySequenceNbr);
	}

	public List findByDisplayDescription(Object displayDescription) {
		return findByProperty(DISPLAY_DESCRIPTION, displayDescription);
	}

	public List findByImplClassName(Object implClassName) {
		return findByProperty(IMPL_CLASS_NAME, implClassName);
	}

	public List findAll() {
		log.debug("finding all BillRegister instances");
		try {
			String queryString = "from BillRegister";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillRegister merge(BillRegister detachedInstance) {
		log.debug("merging BillRegister instance");
		try {
			BillRegister result = (BillRegister) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillRegister instance) {
		log.debug("attaching dirty BillRegister instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillRegister instance) {
		log.debug("attaching clean BillRegister instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillRegisterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillRegisterDAO) ctx.getBean("BillRegisterDAO");
	}
}