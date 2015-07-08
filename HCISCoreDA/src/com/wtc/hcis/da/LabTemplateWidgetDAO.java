package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * LabTemplateWidget entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTemplateWidget
 * @author MyEclipse Persistence Tools
 */

public class LabTemplateWidgetDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabTemplateWidgetDAO.class);
	// property constants
	public static final String WIDGET_LABEL = "widgetLabel";
	public static final String WIDGET_TYPE = "widgetType";
	public static final String SECTION_CODE = "sectionCode";
	public static final String VALUE_TYPE = "valueType";
	public static final String WIDGET_VALUE_PROVIDER = "widgetValueProvider";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTemplateWidget transientInstance) {
		log.debug("saving LabTemplateWidget instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTemplateWidget persistentInstance) {
		log.debug("deleting LabTemplateWidget instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTemplateWidget findById(java.lang.String id) {
		log.debug("getting LabTemplateWidget instance with id: " + id);
		try {
			LabTemplateWidget instance = (LabTemplateWidget) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTemplateWidget", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTemplateWidget instance) {
		log.debug("finding LabTemplateWidget instance by example");
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
		log.debug("finding LabTemplateWidget instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTemplateWidget as model where model."
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

	public List findByWidgetType(Object widgetType) {
		return findByProperty(WIDGET_TYPE, widgetType);
	}

	public List findBySectionCode(Object sectionCode) {
		return findByProperty(SECTION_CODE, sectionCode);
	}

	public List findByValueType(Object valueType) {
		return findByProperty(VALUE_TYPE, valueType);
	}

	public List findByWidgetValueProvider(Object widgetValueProvider) {
		return findByProperty(WIDGET_VALUE_PROVIDER, widgetValueProvider);
	}

	public List findAll() {
		log.debug("finding all LabTemplateWidget instances");
		try {
			String queryString = "from LabTemplateWidget";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTemplateWidget merge(LabTemplateWidget detachedInstance) {
		log.debug("merging LabTemplateWidget instance");
		try {
			LabTemplateWidget result = (LabTemplateWidget) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTemplateWidget instance) {
		log.debug("attaching dirty LabTemplateWidget instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTemplateWidget instance) {
		log.debug("attaching clean LabTemplateWidget instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTemplateWidgetDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTemplateWidgetDAO) ctx.getBean("LabTemplateWidgetDAO");
	}
}