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
 * LabTestTemplateDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.LabTestTemplateDetail
 * @author MyEclipse Persistence Tools
 */

public class LabTestTemplateDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(LabTestTemplateDetailDAO.class);
	// property constants
	public static final String CELL_ID = "cellId";
	public static final String WIDGET_CODE = "widgetCode";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(LabTestTemplateDetail transientInstance) {
		log.debug("saving LabTestTemplateDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LabTestTemplateDetail persistentInstance) {
		log.debug("deleting LabTestTemplateDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LabTestTemplateDetail findById(
			com.wtc.hcis.da.LabTestTemplateDetailId id) {
		log.debug("getting LabTestTemplateDetail instance with id: " + id);
		try {
			LabTestTemplateDetail instance = (LabTestTemplateDetail) getHibernateTemplate()
					.get("com.wtc.hcis.da.LabTestTemplateDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LabTestTemplateDetail instance) {
		log.debug("finding LabTestTemplateDetail instance by example");
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
		log.debug("finding LabTestTemplateDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LabTestTemplateDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCellId(Object cellId) {
		return findByProperty(CELL_ID, cellId);
	}

	public List findByWidgetCode(Object widgetCode) {
		return findByProperty(WIDGET_CODE, widgetCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all LabTestTemplateDetail instances");
		try {
			String queryString = "from LabTestTemplateDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LabTestTemplateDetail merge(LabTestTemplateDetail detachedInstance) {
		log.debug("merging LabTestTemplateDetail instance");
		try {
			LabTestTemplateDetail result = (LabTestTemplateDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LabTestTemplateDetail instance) {
		log.debug("attaching dirty LabTestTemplateDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LabTestTemplateDetail instance) {
		log.debug("attaching clean LabTestTemplateDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LabTestTemplateDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LabTestTemplateDetailDAO) ctx
				.getBean("LabTestTemplateDetailDAO");
	}
}