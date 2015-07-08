package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * SystemParameter entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.SystemParameter
 * @author MyEclipse Persistence Tools
 */

public class SystemParameterDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(SystemParameterDAO.class);
	// property constants
	public static final String ATTR_LABEL = "attrLabel";
	public static final String ATTR_VALUE = "attrValue";
	public static final String DATA_TYPE = "dataType";
	public static final String ATTR_WIDTH = "attrWidth";
	public static final String DATA_PROVIDER = "dataProvider";
	public static final String ATTR_DESC = "attrDesc";

	protected void initDao() {
		// do nothing
	}

	public void save(SystemParameter transientInstance) {
		log.debug("saving SystemParameter instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SystemParameter persistentInstance) {
		log.debug("deleting SystemParameter instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemParameter findById(java.lang.String id) {
		log.debug("getting SystemParameter instance with id: " + id);
		try {
			SystemParameter instance = (SystemParameter) getHibernateTemplate()
					.get("com.wtc.hcis.da.SystemParameter", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SystemParameter instance) {
		log.debug("finding SystemParameter instance by example");
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
		log.debug("finding SystemParameter instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SystemParameter as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAttrLabel(Object attrLabel) {
		return findByProperty(ATTR_LABEL, attrLabel);
	}

	public List findByAttrValue(Object attrValue) {
		return findByProperty(ATTR_VALUE, attrValue);
	}

	public List findByDataType(Object dataType) {
		return findByProperty(DATA_TYPE, dataType);
	}

	public List findByAttrWidth(Object attrWidth) {
		return findByProperty(ATTR_WIDTH, attrWidth);
	}

	public List findByDataProvider(Object dataProvider) {
		return findByProperty(DATA_PROVIDER, dataProvider);
	}

	public List findByAttrDesc(Object attrDesc) {
		return findByProperty(ATTR_DESC, attrDesc);
	}

	public List findAll() {
		log.debug("finding all SystemParameter instances");
		try {
			String queryString = "from SystemParameter";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SystemParameter merge(SystemParameter detachedInstance) {
		log.debug("merging SystemParameter instance");
		try {
			SystemParameter result = (SystemParameter) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemParameter instance) {
		log.debug("attaching dirty SystemParameter instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemParameter instance) {
		log.debug("attaching clean SystemParameter instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SystemParameterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (SystemParameterDAO) ctx.getBean("SystemParameterDAO");
	}
}