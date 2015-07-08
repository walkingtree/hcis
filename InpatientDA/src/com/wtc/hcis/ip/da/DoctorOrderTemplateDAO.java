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
 * DoctorOrderTemplate entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.ip.da.DoctorOrderTemplate
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderTemplateDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DoctorOrderTemplateDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String TEMPLATE_DESC = "templateDesc";
	public static final String DOCTOR_ID = "doctorId";
	public static final String ACTIVE_FLAG = "activeFlag";

	protected void initDao() {
		// do nothing
	}

	public void save(DoctorOrderTemplate transientInstance) {
		log.debug("saving DoctorOrderTemplate instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DoctorOrderTemplate persistentInstance) {
		log.debug("deleting DoctorOrderTemplate instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DoctorOrderTemplate findById(java.lang.String id) {
		log.debug("getting DoctorOrderTemplate instance with id: " + id);
		try {
			DoctorOrderTemplate instance = (DoctorOrderTemplate) getHibernateTemplate()
					.get("com.wtc.hcis.ip.da.DoctorOrderTemplate", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DoctorOrderTemplate instance) {
		log.debug("finding DoctorOrderTemplate instance by example");
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
		log.debug("finding DoctorOrderTemplate instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DoctorOrderTemplate as model where model."
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

	public List findByTemplateDesc(Object templateDesc) {
		return findByProperty(TEMPLATE_DESC, templateDesc);
	}

	public List findByDoctorId(Object doctorId) {
		return findByProperty(DOCTOR_ID, doctorId);
	}

	public List findByActiveFlag(Object activeFlag) {
		return findByProperty(ACTIVE_FLAG, activeFlag);
	}

	public List findAll() {
		log.debug("finding all DoctorOrderTemplate instances");
		try {
			String queryString = "from DoctorOrderTemplate";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DoctorOrderTemplate merge(DoctorOrderTemplate detachedInstance) {
		log.debug("merging DoctorOrderTemplate instance");
		try {
			DoctorOrderTemplate result = (DoctorOrderTemplate) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DoctorOrderTemplate instance) {
		log.debug("attaching dirty DoctorOrderTemplate instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DoctorOrderTemplate instance) {
		log.debug("attaching clean DoctorOrderTemplate instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DoctorOrderTemplateDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DoctorOrderTemplateDAO) ctx.getBean("DoctorOrderTemplateDAO");
	}
}