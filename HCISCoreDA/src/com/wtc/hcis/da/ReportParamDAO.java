package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReportParam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.ReportParam
 * @author MyEclipse Persistence Tools
 */

public class ReportParamDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ReportParamDAO.class);
	// property constants
	public static final String WIDGET_LABEL = "widgetLabel";
	public static final String WIDGET_LENGTH = "widgetLength";
	public static final String XTYPE = "xtype";
	public static final String DATA_PROVIDER_METHOD = "dataProviderMethod";
	public static final String REQUIRED_FLAG = "requiredFlag";
	public static final String WIDGET_SEQ_NBR = "widgetSeqNbr";

	protected void initDao() {
		// do nothing
	}

	public void save(ReportParam transientInstance) {
		log.debug("saving ReportParam instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReportParam persistentInstance) {
		log.debug("deleting ReportParam instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReportParam findById(com.wtc.hcis.da.ReportParamId id) {
		log.debug("getting ReportParam instance with id: " + id);
		try {
			ReportParam instance = (ReportParam) getHibernateTemplate().get(
					"com.wtc.hcis.da.ReportParam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ReportParam instance) {
		log.debug("finding ReportParam instance by example");
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
		log.debug("finding ReportParam instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ReportParam as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWidgetLabel(Object widgetLabel) {
		return findByProperty(WIDGET_LABEL, widgetLabel);
	}

	public List findByWidgetLength(Object widgetLength) {
		return findByProperty(WIDGET_LENGTH, widgetLength);
	}

	public List findByXtype(Object xtype) {
		return findByProperty(XTYPE, xtype);
	}

	public List findByDataProviderMethod(Object dataProviderMethod) {
		return findByProperty(DATA_PROVIDER_METHOD, dataProviderMethod);
	}

	public List findByRequiredFlag(Object requiredFlag) {
		return findByProperty(REQUIRED_FLAG, requiredFlag);
	}

	public List findByWidgetSeqNbr(Object widgetSeqNbr) {
		return findByProperty(WIDGET_SEQ_NBR, widgetSeqNbr);
	}

	public List findAll() {
		log.debug("finding all ReportParam instances");
		try {
			String queryString = "from ReportParam";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReportParam merge(ReportParam detachedInstance) {
		log.debug("merging ReportParam instance");
		try {
			ReportParam result = (ReportParam) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReportParam instance) {
		log.debug("attaching dirty ReportParam instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReportParam instance) {
		log.debug("attaching clean ReportParam instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReportParamDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReportParamDAO) ctx.getBean("ReportParamDAO");
	}
}