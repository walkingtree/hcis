/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.DocCheckListDAO;

/**
 * @author Bhavesh
 *
 */
public class DocCheckListDAOExtn extends DocCheckListDAO{
	
	protected static final String GROUP_NAME = "checkListDetails.groupName";
	protected static final String ROLE_CODE = "checkListDetails.roleCode";
	protected static final String QUESTION  = "checkListDetailsquestion";
	

	public List<DocCheckList>getCheckList(String checkListName, String type,
										  String groupName, String roleCd,String question,
										  Date createdFromDt,Date createdToDt){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( DocCheckList.class );
		
		if( checkListName != null && !checkListName.isEmpty()){
			
			criteria.add( Restrictions.ilike(DocCheckListDAOExtn.NAME, checkListName, MatchMode.ANYWHERE));
		}
		if( type != null && !type.isEmpty()){
					
			criteria.add( Restrictions.eq(DocCheckListDAOExtn.CHECK_LIST_TYPE, type));
		}
		
		boolean aliasCreated = false;
		
		if( groupName != null && !groupName.isEmpty()){
			
			if(!aliasCreated){
				
				this.createDetailAlias(criteria);
			}
			
			criteria.add( Restrictions.ilike(DocCheckListDAOExtn.GROUP_NAME, groupName, MatchMode.ANYWHERE));
		}
		
		if( roleCd != null && !roleCd.isEmpty()){
			

			if(!aliasCreated){
				
				this.createDetailAlias(criteria);
			}
			
			criteria.add( Restrictions.eq(DocCheckListDAOExtn.ROLE_CODE, roleCd));
		}
		
		if( question != null && !question.isEmpty()){
			

			if(!aliasCreated){
				
				this.createDetailAlias(criteria);
			}
			
			criteria.add( Restrictions.ilike(DocCheckListDAOExtn.QUESTION, question, MatchMode.ANYWHERE));
		}
		
		if(createdFromDt != null){
			criteria.add( Restrictions.ge("createdDtm", createdFromDt));
		}
		
		if(createdToDt != null){
			criteria.add( Restrictions.le("createdDtm", createdToDt));
		}
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
	
	private void  createDetailAlias(DetachedCriteria criteria){
		
		criteria.createAlias("docCheckListDetails", "checkListDetails");
	}
}
