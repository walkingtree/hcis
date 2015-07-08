package com.wtc.hcis.billing.da;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * BillItemDetails entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wtc.hcis.billing.da.BillItemDetails
 * @author MyEclipse Persistence Tools
 */

public class BillItemDetailsDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BillItemDetailsDAO.class);
	// property constants
	public static final String ITEM_NAME = "itemName";
	public static final String ITEM_COUNT = "itemCount";
	public static final String ITEM_PRICE = "itemPrice";
	public static final String DISCOUNTS = "discounts";
	public static final String TOTAL_AMOUNT = "totalAmount";

	protected void initDao() {
		// do nothing
	}

	public void save(BillItemDetails transientInstance) {
		log.debug("saving BillItemDetails instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(BillItemDetails persistentInstance) {
		log.debug("deleting BillItemDetails instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public BillItemDetails findById(com.wtc.hcis.billing.da.BillItemDetailsId id) {
		log.debug("getting BillItemDetails instance with id: " + id);
		try {
			BillItemDetails instance = (BillItemDetails) getHibernateTemplate()
					.get("com.wtc.hcis.billing.da.BillItemDetails", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(BillItemDetails instance) {
		log.debug("finding BillItemDetails instance by example");
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
		log.debug("finding BillItemDetails instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BillItemDetails as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByItemName(Object itemName) {
		return findByProperty(ITEM_NAME, itemName);
	}

	public List findByItemCount(Object itemCount) {
		return findByProperty(ITEM_COUNT, itemCount);
	}

	public List findByItemPrice(Object itemPrice) {
		return findByProperty(ITEM_PRICE, itemPrice);
	}

	public List findByDiscounts(Object discounts) {
		return findByProperty(DISCOUNTS, discounts);
	}

	public List findByTotalAmount(Object totalAmount) {
		return findByProperty(TOTAL_AMOUNT, totalAmount);
	}

	public List findAll() {
		log.debug("finding all BillItemDetails instances");
		try {
			String queryString = "from BillItemDetails";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public BillItemDetails merge(BillItemDetails detachedInstance) {
		log.debug("merging BillItemDetails instance");
		try {
			BillItemDetails result = (BillItemDetails) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BillItemDetails instance) {
		log.debug("attaching dirty BillItemDetails instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BillItemDetails instance) {
		log.debug("attaching clean BillItemDetails instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BillItemDetailsDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (BillItemDetailsDAO) ctx.getBean("BillItemDetailsDAO");
	}
}