/**
 * 
 */
package com.wtc.hcis.ip.da.extn;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.AdmissionClaimRequest;
import com.wtc.hcis.ip.da.AdmissionClaimRequestDAO;
/**
 * @author Bhavesh
 *
 */
public class AdmissionClaimRequestDAOExtn extends AdmissionClaimRequestDAO {
	
	
	public  static final String ADMISSION_NUMBER = "admission.admissionReqNbr";
	public  static final String PATIENT_ID = "admission.patientId";
	public 	static final String SPONSOR_TYPE = "claimSponsor.sponsorType";
	public 	static final String SPONSOR_NAME = "claimSponsor.sponsorsName";
	public 	static final String CLAIM_STATUS_CODE = "sponsorClaimStatus.claimStatusCd";
	public 	static final String PLAN_NAME = "insurancePlans.planName";
	public 	static final String PLAN_CODE = "insurancePlans.planCd";
	public 	static final String INSURER_NAME = "insurer.insurerName";
	public static final String REQ_SUBSEQUENCE_NBR = "claimSubsequenceNbr";
	
	//Fields to sort based on UI request 
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	private Map<String,String> UIDBFieldMapForOrder = new HashMap<String, String>();

	
	public List<AdmissionClaimRequest> getClaimRequests(	Integer requestSequenceNbr,
															Integer admissionReqNumber,
															Integer patientId,
															String sponsorType,
															String sponsorName,
															String planName,
															String planCode,
															String policyNumber,
															String claimStatusCode,
															Date claimRequestCreationFromDt,
															Date claimRequestCreationToDt,
															Date claimApprovalFromDt,
															Date claimApprovalToDt,
															String orderByClause, String sortDir){
		
		
		
		DetachedCriteria claimRequestCriteria = DetachedCriteria.forClass(AdmissionClaimRequest.class);
		
		claimRequestCriteria.createAlias("admission", "admission");
		claimRequestCriteria.createAlias("insurancePlans", "insurancePlans")
			.createAlias("insurer", "insurer").createAlias("sponsorClaimStatus", "sponsorClaimStatus");
		
		if( requestSequenceNbr != null){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.CLAIM_SUBSEQUENCE_NBR, requestSequenceNbr));
		}
		if( admissionReqNumber != null){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.ADMISSION_NUMBER, admissionReqNumber));
		}
		if( patientId != null ){
			
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.PATIENT_ID, patientId));
		}
		if( sponsorType != null && !sponsorType.isEmpty()){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.SPONSOR_TYPE, sponsorType) );
		}
		if( sponsorName != null && !sponsorName.isEmpty()){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.SPONSOR_NAME, sponsorName) );
		}
		if( planName != null && !planName.isEmpty()){
		
			claimRequestCriteria.add(Restrictions.ilike(AdmissionClaimRequestDAOExtn.PLAN_NAME, "%" + planName + "%") );
		}
		if( planCode != null && !planCode.isEmpty()){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.PLAN_CODE, planCode) );
		}
		if( policyNumber != null && !policyNumber.isEmpty()){
			claimRequestCriteria.add(Restrictions.ilike(AdmissionClaimRequestDAOExtn.POLICY_NBR,"%" + policyNumber + "%") );
		}
		if( claimStatusCode != null && !claimStatusCode.isEmpty()){
			claimRequestCriteria.add(Restrictions.eq(AdmissionClaimRequestDAOExtn.CLAIM_STATUS_CODE, claimStatusCode)); 
		}
		if( claimRequestCreationFromDt != null ){
			claimRequestCriteria.add(Restrictions.ge( "createdDtm",claimRequestCreationFromDt ));
		}
		if ( claimRequestCreationToDt != null ){
			claimRequestCriteria.add(Restrictions.le( "createdDtm",claimRequestCreationToDt ));
		}
		if( claimApprovalFromDt != null){
			claimRequestCriteria.add(Restrictions.ge( "approvalDtm",claimApprovalFromDt ));
		}
		if( claimApprovalToDt != null){
			claimRequestCriteria.add(Restrictions.le( "approvalDtm",claimApprovalToDt ));
		}
		
		if (orderByClause != null && orderByClause.length() > 0) {

			if (sortDir.equals(AdmissionClaimRequestDAOExtn.SORT_DIRECTION_ASC))

			if (sortDir.equals(SORT_DIRECTION_ASC))

				claimRequestCriteria.addOrder(Order.asc(UIDBFieldMapForOrder.get(orderByClause)));
			else
				claimRequestCriteria.addOrder(Order.desc(UIDBFieldMapForOrder.get(orderByClause)));
		}
		
		return  getHibernateTemplate().findByCriteria(claimRequestCriteria);
	}

	
	public List<AdmissionClaimRequest> getUnExhaustedClaimRequest( Integer admissionReqNbr ){
		
		List<AdmissionClaimRequest> admissionClaimRequestList  = null;
		
		DetachedCriteria claimRequestCriteria = DetachedCriteria.forClass(AdmissionClaimRequest.class);
		
		claimRequestCriteria.add( Restrictions.eq( AdmissionClaimRequestDAOExtn.ADMISSION_NUMBER, admissionReqNbr))
							.add(Restrictions.or(Restrictions.isNull( AdmissionClaimRequestDAOExtn.FINAL_CLAIMED_AMOUNT),
								Restrictions.ltProperty( AdmissionClaimRequestDAOExtn.FINAL_CLAIMED_AMOUNT, AdmissionClaimRequestDAOExtn.APPROVED_AMOUNT)))
							.addOrder(Order.asc( AdmissionClaimRequestDAOExtn.PREFERENCE_SEQUENCE_NBR ))
							.addOrder(Order.asc( AdmissionClaimRequestDAOExtn.CLAIM_SUBSEQUENCE_NBR ));
		
		
		admissionClaimRequestList = this.getHibernateTemplate().findByCriteria(claimRequestCriteria);
		
		return admissionClaimRequestList;
	}
	
	public Integer nextSubsequenceNumberForARN( Integer admissionReqNbr ){
		
		DetachedCriteria claimRequestCriteria = DetachedCriteria.forClass(AdmissionClaimRequest.class);
		Integer subsequenceNumber = 1;
		
		claimRequestCriteria.add( Restrictions.eq( AdmissionClaimRequestDAOExtn.ADMISSION_NUMBER , admissionReqNbr ))
							.setProjection( Projections.max( AdmissionClaimRequestDAOExtn.REQ_SUBSEQUENCE_NBR));
		
		List result = getHibernateTemplate().findByCriteria( claimRequestCriteria );
		
		if( result != null && !result.isEmpty() ){
			subsequenceNumber = ( (result.get(0)) == null ? 0 : (Integer)(result.get(0)) ) + 1;
		}
		
		return subsequenceNumber;
	}


	public void setUIDBFieldMapForOrder(Map<String, String> fieldMapForOrder) {
		UIDBFieldMapForOrder = fieldMapForOrder;
	}

}
