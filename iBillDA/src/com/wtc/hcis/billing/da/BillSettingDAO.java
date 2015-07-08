package com.wtc.hcis.billing.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillSetting entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillSetting
 * @author MyEclipse Persistence Tools
 */

public class BillSettingDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillSettingDAO.class);
	// property constants
	public static final String ATTR_VALUE = "attrValue";
	public static final String DATA_TYPE = "dataType";
	public static final String ATTR_DESC = "attrDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(BillSetting transientInstance) {
		log.debug("saving BillSetting instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillSetting persistentInstance) {
		log.debug("deleting BillSetting instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillSetting findById(java.lang.String id) {
		log.debug("getting BillSetting instance with id: " + id);
		try {
			BillSetting instance = (BillSetting) getHibernateTemplate().get(
					"com.wtc.hcis.billing.da.BillSetting", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillSetting instance) {
		log.debug("finding BillSetting instance by example");
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
		log.debug("finding BillSetting instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BillSetting as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAttrValue(Object attrValue) {
		return findByProperty(ATTR_VALUE, attrValue);
	}

	public List findByDataType(Object dataType) {
		return findByProperty(DATA_TYPE, dataType);
	}

	public List findByAttrDesc(Object attrDesc) {
		return findByProperty(ATTR_DESC, attrDesc);
	}

	public List findAll() {
		log.debug("finding all BillSetting instances");
		try {
			String queryString = "from BillSetting";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillSetting merge(BillSetting detachedInstance) {
		log.debug("merging BillSetting instance");
		try {
			BillSetting result = (BillSetting) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillSetting instance) {
		log.debug("attaching dirty BillSetting instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillSetting instance) {
		log.debug("attaching clean BillSetting instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillSettingDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillSettingDAO) ctx.getBean("BillSettingDAO");
	}
}