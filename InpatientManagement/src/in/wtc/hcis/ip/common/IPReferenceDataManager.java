/**
 * 
 */
package in.wtc.hcis.ip.common;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Alok Ranjan
 *
 */
public interface IPReferenceDataManager {
	
	ListRange getActionStatus(int start, int count, String orderBy)throws HCISException;
	
	ListRange getActivityType(int start, int count, String orderBy)throws HCISException;
	
	ListRange getActivityTypeForGroup( String activityGroupName,int start, int count, String orderBy )throws HCISException;
	
	ListRange getAdmissionStatus(int start, int count, String orderBy)throws HCISException;
	
	ListRange getAdmissionEntryPoints(int start, int count, String orderBy)throws HCISException;
	
	ListRange getBedSpecialFeatures(int start, int count, String orderBy)throws HCISException;
	
	ListRange getBedStatus(int start, int count, String orderBy)throws HCISException;
	
	ListRange getBedType(int start, int count, String orderBy)throws HCISException;
	
	ListRange getClaimSponsors(int start, int count, String orderBy)throws HCISException;
	
	ListRange getDischargeType(int start, int count, String orderBy)throws HCISException;

	ListRange getCreditClass(int start, int count, String orderBy)throws HCISException;
	
	ListRange getDoctorOrderGroup(int start, int count, String orderBy) throws HCISException;
	
	ListRange getDoctorOrderTemplate(int start, int count, String orderBy)throws HCISException;
	
	ListRange getDoctorOrderStatus(int start, int count, String orderBy)throws HCISException;

	ListRange getDoctorOrderType(int start, int count, String orderBy)throws HCISException;
	
	ListRange getFeedbackType( String involvedProcess ,int start, int count, String orderBy )throws HCISException;
	
	ListRange getNursingUnitType(int start, int count, String orderBy)throws HCISException;
	
	ListRange getNursingUnits(int start, int count, String orderBy) throws HCISException;

	ListRange getInsurerForSponsor(String sponsorName ,int start, int count, String orderBy)throws HCISException;

	ListRange getReservationStatus(int start, int count, String orderBy)throws HCISException;
	
	ListRange getSponsorClaimStatus(int start, int count, String orderBy)throws HCISException;
	
	ListRange getSponsorType(int start, int count, String orderBy)throws HCISException;
	
	ListRange getBedPools(int start, int count, String orderBy)throws HCISException;
	
	ListRange getInsurer(int start, int count, String orderBy)throws HCISException;
	
	ListRange getPlanForInsurer(String insurerName ,int start, int count, String orderBy)throws HCISException;
	
	ListRange getAdmissionReqNbr( int start, int count, String orderBy)throws HCISException; 
	
	ListRange getBedMaster( String bedType,int start, int count, String orderBy)throws HCISException; 
}
