/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;

import com.wtc.hcis.da.DocCheckListDetail;
import com.wtc.hcis.da.DocCheckListDetailDAO;

/**
 * @author Bhavesh
 *
 */
public class DocCheckListDetailDAOExtn extends DocCheckListDetailDAO {

	
	private static final Log log = LogFactory.getLog(DocCheckListDetailDAOExtn.class);
	private static String CHECK_LIST_ID = "docCheckList.checkListId"; 
	
	//
	//This method is not being used for now, please delete either this comment or the method :-)
	
	public List<DocCheckListDetail> getCheckListDetail( String group,String role, String question){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( DocCheckListDetail.class);
		
		if(group != null && !group.isEmpty()){
			
			criteria.add( Restrictions.ilike("roleCode", group, MatchMode.START));
		}
		if(role != null && !role.isEmpty()){
			
			criteria.add( Restrictions.ilike("group", group, MatchMode.START));
		}
		if(question != null && !question.isEmpty()){
			
			criteria.add( Restrictions.ilike("question", group, MatchMode.ANYWHERE));
		}

		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	/**
	 * 
	 * @return Distinct 'Group Name'
	 */
	public List<String> getDistinctGroupList() {
		
		try {
			String hql = "select distinct groupName from DocCheckListDetail ";
			
			return getSession().createQuery(hql).list();
			
		} catch (RuntimeException e) {
			log.error(" Failed to read distinct groupName", e);
			throw e;
		}
	}
	
	public List<DocCheckListDetail> getCheckListDetails( Integer checkListId ){
		DetachedCriteria criteria = DetachedCriteria.forClass(DocCheckListDetail.class);
		
		if( checkListId != null){
			criteria.createAlias("docCheckList", "docCheckList");
			criteria.add(Restrictions.eq(DocCheckListDetailDAOExtn.CHECK_LIST_ID, checkListId));
		}
		
		criteria.addOrder(Order.asc(DocCheckListDetailDAOExtn.GROUP_NAME));
		criteria.addOrder(Order.asc(DocCheckListDetailDAOExtn.ROLE_CODE));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
}
