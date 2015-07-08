/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import com.wtc.hcis.da.DocCheckListInstanceDetail;
import com.wtc.hcis.da.DocCheckListInstanceDetailDAO;

/**
 * @author Bhavesh
 *
 */
public class DocCheckListInstanceDetailDAOExtn extends DocCheckListInstanceDetailDAO{

	public List<DocCheckListInstanceDetail> getDocCheckListInstanceDetail(Long checkListInstanceId, Integer checkListDetailId){
		
		StringBuilder hql = new StringBuilder("from DocCheckListInstanceDetail detail ");
		
		boolean isWhereAded = false;
		
		if(checkListDetailId != null){
			
			if( !isWhereAded){
				
				hql.append(" where ");
				
				isWhereAded = true;
			}
			
			hql.append(" inner join DocCheckListDetail chkDetail on detail.id.checkListDetailId = chkDetail.docCheckList.checkListId ");
		}
		
		if( checkListInstanceId != null ){
			
			if( !isWhereAded){
				
				hql.append(" where ");
				
			}
			
			hql.append(" checkListInstanceId =" + checkListInstanceId );
			
			List resutl = this.getSession().createQuery(hql.toString()).list();
		}
	
		return null;
	}
}
