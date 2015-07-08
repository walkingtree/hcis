package com.wtc.hcis.da;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocCheckListDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.DocCheckListDetail
 * @author MyEclipse Persistence Tools
 */

public class DocCheckListDetailDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(DocCheckListDetailDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String QUESTION = "question";
	public static final String GROUP_NAME = "groupName";
	public static final String ROLE_CODE = "roleCode";
	public static final String CREATED_BY = "createdBy";

	protected void initDao() {
		// do nothing
	}

	public void save(DocCheckListDetail transientInstance) {
		log.debug("saving DocCheckListDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocCheckListDetail persistentInstance) {
		log.debug("deleting DocCheckListDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocCheckListDetail findById(java.lang.Long id) {
		log.debug("getting DocCheckListDetail instance with id: " + id);
		try {
			DocCheckListDetail instance = (DocCheckListDetail) getHibernateTemplate()
					.get("com.wtc.hcis.da.DocCheckListDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(DocCheckListDetail instance) {
		log.debug("finding DocCheckListDetail instance by example");
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
		log.debug("finding DocCheckListDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocCheckListDetail as model where model."
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

	public List findByQuestion(Object question) {
		return findByProperty(QUESTION, question);
	}

	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	public List findByRoleCode(Object roleCode) {
		return findByProperty(ROLE_CODE, roleCode);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findAll() {
		log.debug("finding all DocCheckListDetail instances");
		try {
			String queryString = "from DocCheckListDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocCheckListDetail merge(DocCheckListDetail detachedInstance) {
		log.debug("merging DocCheckListDetail instance");
		try {
			DocCheckListDetail result = (DocCheckListDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocCheckListDetail instance) {
		log.debug("attaching dirty DocCheckListDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocCheckListDetail instance) {
		log.debug("attaching clean DocCheckListDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocCheckListDetailDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocCheckListDetailDAO) ctx.getBean("DocCheckListDetailDAO");
	}
}