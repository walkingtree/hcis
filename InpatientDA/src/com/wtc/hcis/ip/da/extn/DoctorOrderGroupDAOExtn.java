/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupDAO;

/**
 * @author Alok Ranjan
 *
 */
public class DoctorOrderGroupDAOExtn extends DoctorOrderGroupDAO {
	
	public static final String GROUP_NAME = "orderGroupName";
	
	public List<DoctorOrderGroup> getDoctorOrderGroups ( String orderGroupName,
					                                     String description, 
					                                     Integer groupForDoctorId ) {
		
		DetachedCriteria orderGroupCriteria = DetachedCriteria.forClass( DoctorOrderGroup.class );
		
		if ( orderGroupName != null ) {
			orderGroupCriteria.add( Restrictions.ilike( DoctorOrderGroupDAOExtn.GROUP_NAME, "%" + orderGroupName + "%" ) );
		}
		
		if ( description != null ) {
			orderGroupCriteria.add( Restrictions.ilike( DoctorOrderGroupDAOExtn.DESCRIPTION, "%" + description + "%" ) );
		}
		
		if ( groupForDoctorId != null ) {
			orderGroupCriteria.add( Restrictions.ilike( DoctorOrderGroupDAOExtn.GROUP_FOR_DOCTOR_ID, "%" + groupForDoctorId + "%" ) );
		}
		
		List<DoctorOrderGroup>doctorOrderGroupList = getHibernateTemplate().findByCriteria( orderGroupCriteria );
		
		return doctorOrderGroupList;
	}
	
	
	
	public List<DoctorOrderGroup> findDoctorOrderGroups( String orderGroupName,
														String  orderTemplateId,
														Integer doctorId ,
														Date createdFromDate,
														Date createdToDate ){
		
		StringBuffer hqlQueryString = new StringBuffer("from DoctorOrderGroup as orderGroup  left join  orderGroup.doctorOrderGroupHasTemplates as groupTemplate");
		
		String whereStr = "";
		String andStr = "";
		boolean whereFlag = true;
		
		
	 
		if (orderGroupName != null && orderGroupName.length() > 0) {
			whereStr = " where ";
			hqlQueryString.append(whereStr + " orderGroup.orderGroupName ='" + orderGroupName.toLowerCase() + "'");
			andStr = " and "; whereStr = ""; whereFlag = false;
		}

		if (orderTemplateId != null && orderTemplateId.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr  + " groupTemplate.id.orderTemplateId='" + orderTemplateId + "'");
			andStr = " and "; whereStr = "";
		}
		
		if (doctorId != null ) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " orderGroup.groupForDoctorId= " + doctorId );
			andStr = " and "; whereStr = "";
		}
		
		if( createdFromDate != null ){
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr + " orderGroup.creationDtm >= :createdFromDate ");
			andStr = " and "; whereStr = "";
			
		}
		if( createdToDate != null){
			
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append( whereStr + andStr + " orderGroup.creationDtm <= :createdToDate ");
		}
		
			
		Query query = getSession().createQuery(hqlQueryString.toString());
		

		if( createdFromDate != null ){
			query.setDate( "createdFromDate", createdFromDate );
		}
		if( createdToDate != null){
			query.setDate( "createdToDate", createdToDate );
		}
	
		List resultList = query.list();
		List newList = new ArrayList() ;
		List doctorGroupList = new ArrayList();

		Iterator<Object> iterator = resultList.iterator();
		while( iterator.hasNext() ) {
			Object [] objArray = (Object[])iterator.next();
			DoctorOrderGroup doctorOrderGroup = ( DoctorOrderGroup )objArray[0];
			if(newList.size() == 0){
					newList.add(doctorOrderGroup);
					doctorGroupList.add(doctorOrderGroup.getOrderGroupName());
			}else{
				if( ! doctorGroupList.contains( doctorOrderGroup.getOrderGroupName() )){
					newList.add(doctorOrderGroup);
					doctorGroupList.add(doctorOrderGroup.getOrderGroupName());
				}
			}
		}
		
		return newList;
		
		
	}
	
}
