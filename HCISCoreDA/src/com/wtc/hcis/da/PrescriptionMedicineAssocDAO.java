package com.wtc.hcis.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrescriptionMedicineAssoc entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.wtc.hcis.da.PrescriptionMedicineAssoc
 * @author MyEclipse Persistence Tools
 */

public class PrescriptionMedicineAssocDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory
			.getLog(PrescriptionMedicineAssocDAO.class);
	// property constants
	public static final String VERSION = "version";
	public static final String DOSAGE = "dosage";
	public static final String REPEATS = "repeats";
	public static final String REMARKS = "remarks";

	protected void initDao() {
		// do nothing
	}

	public void save(PrescriptionMedicineAssoc transientInstance) {
		log.debug("saving PrescriptionMedicineAssoc instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PrescriptionMedicineAssoc persistentInstance) {
		log.debug("deleting PrescriptionMedicineAssoc instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PrescriptionMedicineAssoc findById(
			com.wtc.hcis.da.PrescriptionMedicineAssocId id) {
		log.debug("getting PrescriptionMedicineAssoc instance with id: " + id);
		try {
			PrescriptionMedicineAssoc instance = (PrescriptionMedicineAssoc) getHibernateTemplate()
					.get("com.wtc.hcis.da.PrescriptionMedicineAssoc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PrescriptionMedicineAssoc instance) {
		log.debug("finding PrescriptionMedicineAssoc instance by example");
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
		log.debug("finding PrescriptionMedicineAssoc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PrescriptionMedicineAssoc as model where model."
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

	public List findByDosage(Object dosage) {
		return findByProperty(DOSAGE, dosage);
	}

	public List findByRepeats(Object repeats) {
		return findByProperty(REPEATS, repeats);
	}

	public List findByRemarks(Object remarks) {
		return findByProperty(REMARKS, remarks);
	}

	public List findAll() {
		log.debug("finding all PrescriptionMedicineAssoc instances");
		try {
			String queryString = "from PrescriptionMedicineAssoc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PrescriptionMedicineAssoc merge(
			PrescriptionMedicineAssoc detachedInstance) {
		log.debug("merging PrescriptionMedicineAssoc instance");
		try {
			PrescriptionMedicineAssoc result = (PrescriptionMedicineAssoc) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PrescriptionMedicineAssoc instance) {
		log.debug("attaching dirty PrescriptionMedicineAssoc instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PrescriptionMedicineAssoc instance) {
		log.debug("attaching clean PrescriptionMedicineAssoc instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PrescriptionMedicineAssocDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PrescriptionMedicineAssocDAO) ctx
				.getBean("PrescriptionMedicineAssocDAO");
	}
}