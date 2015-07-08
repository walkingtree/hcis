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
 * Entity entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wtc.hcis.da.Entity
 * @author MyEclipse Persistence Tools
 */

public class EntityDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(EntityDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String IMAGE = "image";
	public static final String KNOWN_LANGUAGES = "knownLanguages";
	public static final String QUALIFICATION = "qualification";
	public static final String IS_PERMANENT = "isPermanent";
	public static final String EXPERIENCE = "experience";
	public static final String REFERRED_BY = "referredBy";
	public static final String CREATED_BY = "createdBy";
	public static final String MODIFIED_BY = "modifiedBy";

	protected void initDao() {
		// do nothing
	}

	public void save(Entity transientInstance) {
		log.debug("saving Entity instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Entity persistentInstance) {
		log.debug("deleting Entity instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Entity findById(java.lang.Integer id) {
		log.debug("getting Entity instance with id: " + id);
		try {
			Entity instance = (Entity) getHibernateTemplate().get(
					"com.wtc.hcis.da.Entity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Entity instance) {
		log.debug("finding Entity instance by example");
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
		log.debug("finding Entity instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Entity as model where model."
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

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByImage(Object image) {
		return findByProperty(IMAGE, image);
	}

	public List findByKnownLanguages(Object knownLanguages) {
		return findByProperty(KNOWN_LANGUAGES, knownLanguages);
	}

	public List findByQualification(Object qualification) {
		return findByProperty(QUALIFICATION, qualification);
	}

	public List findByIsPermanent(Object isPermanent) {
		return findByProperty(IS_PERMANENT, isPermanent);
	}

	public List findByExperience(Object experience) {
		return findByProperty(EXPERIENCE, experience);
	}

	public List findByReferredBy(Object referredBy) {
		return findByProperty(REFERRED_BY, referredBy);
	}

	public List findByCreatedBy(Object createdBy) {
		return findByProperty(CREATED_BY, createdBy);
	}

	public List findByModifiedBy(Object modifiedBy) {
		return findByProperty(MODIFIED_BY, modifiedBy);
	}

	public List findAll() {
		log.debug("finding all Entity instances");
		try {
			String queryString = "from Entity";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Entity merge(Entity detachedInstance) {
		log.debug("merging Entity instance");
		try {
			Entity result = (Entity) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Entity instance) {
		log.debug("attaching dirty Entity instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Entity instance) {
		log.debug("attaching clean Entity instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EntityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EntityDAO) ctx.getBean("EntityDAO");
	}
}