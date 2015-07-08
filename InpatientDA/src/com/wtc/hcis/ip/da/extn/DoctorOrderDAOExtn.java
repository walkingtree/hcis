/**
 * 
 */
package com.wtc.hcis.ip.da.extn;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderDAO;

/**
 * @author Alok Ranjan
 *
 */
public class DoctorOrderDAOExtn extends DoctorOrderDAO {
	
	private static final Log log = LogFactory.getLog(DoctorOrderDAOExtn.class);
	
	public static final String DOCTOR_ORDER_NUMBER = "doctorOrderNbr";
	public static final String DOCTOR_ORDER_TYPE_CD = "doctorOrderType.orderTypeCd";
	public static final String DOCTOR_ORDER_TEMPLATE_ID = "doctorOrderTemplate.templateId";
	public static final String ADMISSION_NBR = "admissions.admissionNbr";
	public static final String DOCTOR_STATUS_CD = "doctorOrderStatus.orderStatusCd";
	public static final String CREATION_SEQ_NBR = "creationSeqNbr";
	
	public static final String ORDER_TYPE_ADMISSION = "IP_ADMISSION";
	public static final String ORDER_STATUS_COMPLEATED = "IP_ADMISSION";
	public static final String ORDER_STATUS_DISAPPROVED = "DISAPPROVED";
	
	public List<DoctorOrder> getDoctorOrders ( Integer doctorOrderNbr,
									           String doctorOrderTypeCd, 
									           String doctorOrderTemplateId,
									           Integer admissionNbr, 
									           String doctorOrderStatusCd, 
									           String patientId,
									           String orderDesc, 
									           String orderDictationMethod ) {

		DetachedCriteria doctorOrderCriteria = DetachedCriteria.forClass( DoctorOrder.class );
		
		if ( doctorOrderNbr != null ) {
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.DOCTOR_ORDER_NUMBER, doctorOrderNbr ) );
		}
		
		if ( doctorOrderTypeCd != null && doctorOrderTypeCd.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.ilike( DoctorOrderDAOExtn.DOCTOR_ORDER_TYPE_CD, doctorOrderTypeCd ) );
		}
		
		if ( doctorOrderTemplateId != null && doctorOrderTemplateId.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.ilike( DoctorOrderDAOExtn.DOCTOR_ORDER_TEMPLATE_ID, "%" + doctorOrderTemplateId + "%" ) );
		}
		
		if ( admissionNbr != null ) {
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.ADMISSION_NBR, admissionNbr ) );
		}
		
		if ( doctorOrderStatusCd != null && doctorOrderStatusCd.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.DOCTOR_STATUS_CD, doctorOrderStatusCd ) );
		}
		
		if ( patientId != null && patientId.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.PATIENT_ID, patientId ) );
		}
		
		if ( orderDesc != null && orderDesc.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.ilike( DoctorOrderDAOExtn.ORDER_DESC, "%" +  orderDesc + "%" ) );
		}
		
		if ( orderDictationMethod != null && orderDictationMethod.length() > 0 ) {
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.ORDER_DICTATION, orderDictationMethod ) );
		}
		
		List<DoctorOrder>doctorOrderList = getHibernateTemplate().findByCriteria( doctorOrderCriteria );
		
		return doctorOrderList;
	}
	
	public List<DoctorOrder> findDoctorOrders ( Integer admissionNbr,
												Integer patientId,
										        String  patientName,
										        String  doctorOrderStatusCd,
										        String  doctorOrderTypeCd, 
										        Date    orderDateFrom,
										        Date    orderDateTo){
		String joinQuery = "";
		String and = "";
		 boolean whereFalg = false;
		
		if(patientName != null && !patientName.isEmpty()){
			joinQuery = "from DoctorOrder as doctorOrder,Patient as patient where doctorOrder.patientId = patient.patientId";
			and = " and ";
		}else{
			joinQuery = "from DoctorOrder as doctorOrder";
		}
		
		String admissionNbrQuery = "";
		String patientIdQuery = "";
        String patientNameQuery = "";
        String doctorOrderStatusCdQuery = "";
        String doctorOrderTypeCdQuery = "";
        String orderDateFromQuery = "";
        String orderDateToQuery = "";
        
       
        
        if(admissionNbr != null ){
        	admissionNbrQuery = and + " doctorOrder.admission.admissionReqNbr = " + admissionNbr;
        	
        	and = " and ";
        	whereFalg = true;
        }
		if(patientId != null){
			patientIdQuery = and + " doctorOrder.patientId = " + patientId;
			
			and = " and ";
			whereFalg = true;
		}
		
		if(doctorOrderStatusCd != null && !doctorOrderStatusCd.isEmpty()){
			doctorOrderStatusCdQuery = and + " doctorOrder.doctorOrderStatus.orderStatusCd = '"+ doctorOrderStatusCd + "'";
			
			and = " and ";
			whereFalg = true;
		}
		if(doctorOrderTypeCd != null && !doctorOrderTypeCd.isEmpty()){
			doctorOrderTypeCdQuery = and + " doctorOrder.doctorOrderType.orderTypeCd = '" + doctorOrderTypeCd + "'";
			
			and = " and ";
			whereFalg = true;
		}
		if(orderDateFrom != null){
			orderDateFromQuery = and + " doctorOrder.orderCreationDtm >= :orderDateFrom";
			
			and = " and ";
			whereFalg = true;
		}
		if(orderDateTo != null){
			orderDateToQuery = and + " doctorOrder.orderCreationDtm <= :orderDateTo";
			
			and = " and ";
			whereFalg = true;
		}
/*
 * 	This condition is intentionally being checked at last to set the "whereFalg" accordingly i.e
 *  do not add "where clause" again if it has already been added.
 */

		if(patientName != null && !patientName.isEmpty()){
			
			patientNameQuery = and + " patient.firstName||' '||patient.middleName||' '||patient.lastName like '%" + patientName + "%'";
			whereFalg = false;
		}
		
		String where ="";
		if(whereFalg){
			where = " where ";
		}
		
		
		String hqlQuery = 	joinQuery + where +
							admissionNbrQuery +
							patientIdQuery +
							patientNameQuery + 
							doctorOrderStatusCdQuery +
							doctorOrderTypeCdQuery +
							orderDateFromQuery +
							orderDateToQuery;
						
		Query doctorOrderSearched = getSession().createQuery( hqlQuery ); 
		
		if(orderDateFrom != null){
			
			doctorOrderSearched.setDate( "orderDateFrom", orderDateFrom );
		}
		if(orderDateTo != null){
			
			doctorOrderSearched.setDate( "orderDateTo", orderDateTo );
		}
		
		
		List<Object> resultList = doctorOrderSearched.list();	
		Set<Integer> doctorOrderNbrSet = new HashSet<Integer>();
		List<DoctorOrder> doctorOrderList = new ArrayList<DoctorOrder>();
		
		Iterator<Object> iterator = resultList.iterator();

// The resultList may contain list of Collection(in case of table joins) or list of  "DoctorOrder" objects			
		if( resultList !=null && !resultList.isEmpty() ){
			if( resultList.get(0) instanceof DoctorOrder ){
				
				while( iterator.hasNext() ){
						DoctorOrder doctorOrder = (DoctorOrder) iterator.next();
					if(!doctorOrderNbrSet.contains( doctorOrder.getDoctorOrderNbr() )){
						doctorOrderNbrSet.add( doctorOrder.getDoctorOrderNbr() );
						doctorOrderList.add( doctorOrder );
					}
					
				}
			}else{
				
				while( iterator.hasNext() ) {
					Object [] objArray = (Object[])iterator.next();
					DoctorOrder doctorOrder = (DoctorOrder)objArray[0];
					if(!doctorOrderNbrSet.contains( doctorOrder.getDoctorOrderNbr() )){
						doctorOrderNbrSet.add( doctorOrder.getDoctorOrderNbr() );
						doctorOrderList.add( doctorOrder );
					}
				}
			}
			
			return new ArrayList<DoctorOrder>( doctorOrderList );
		}	
	return null;
	}
	
	public Integer nextCreationSequenceNumber( ){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DoctorOrder.class);
		Integer subsequenceNumber = 1;
		
		criteria.setProjection( Projections.max( DoctorOrderDAOExtn.CREATION_SEQ_NBR));
		
		List result = getHibernateTemplate().findByCriteria( criteria );
		
		if( result != null && !result.isEmpty() && result.get(0)!= null  ){
			subsequenceNumber = (Integer)result.get(0) + 1;
		}
		
		return subsequenceNumber;
	}

	public List<DoctorOrder> getActiveAdmissionOrder(Integer patientId){
		
		DetachedCriteria doctorOrderCriteria = DetachedCriteria.forClass( DoctorOrder.class );
		
			doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.PATIENT_ID, patientId ) )
							   .add( Restrictions.eq( DoctorOrderDAOExtn.DOCTOR_ORDER_TYPE_CD, DoctorOrderDAOExtn.ORDER_TYPE_ADMISSION))
							   .add( Restrictions.ne(DoctorOrderDAOExtn.DOCTOR_STATUS_CD , DoctorOrderDAOExtn.ORDER_STATUS_DISAPPROVED));
		
			return getHibernateTemplate().findByCriteria( doctorOrderCriteria );
	}
	
	
	public void stampARN(Integer creationSeqNbr, Integer admissionReqNbr, String personId){
		try {
			String hqlQuery = "update DoctorOrder d set d.admission.admissionReqNbr = :admissionReqNbr , d.modifiedBy =  :personId" 
							  +",d.lastModifiedTm = :currentTime  where d.creationSeqNbr= :creationSeqNbr";
	
			int updatedEntities = getSession().createQuery( hqlQuery )
																				.setInteger("admissionReqNbr", admissionReqNbr)
																				.setString( "personId", personId)
																				.setDate( "currentTime", new Date() )
																				.setInteger("creationSeqNbr", creationSeqNbr)
																		      	.executeUpdate();
		} catch (RuntimeException ex ) {
			log.error(" Failed to update doctor order "  + ex.getMessage() );
			throw ex;
		}

	}
	
	public boolean isCreationSeqHasAdmssionOrder( Integer creationSeqNbr ){
		
		DetachedCriteria doctorOrderCriteria = DetachedCriteria.forClass( DoctorOrder.class );
		
		doctorOrderCriteria.add( Restrictions.eq( DoctorOrderDAOExtn.CREATION_SEQ_NBR, creationSeqNbr ) )
						   .add( Restrictions.eq( DoctorOrderDAOExtn.DOCTOR_ORDER_TYPE_CD, DoctorOrderDAOExtn.ORDER_TYPE_ADMISSION));
		
		List<DoctorOrder> result = getHibernateTemplate().findByCriteria( doctorOrderCriteria );
			
		if( result != null &&  result.size() > 0 ){
			
			return true;
		}
		return false;
	}
}
