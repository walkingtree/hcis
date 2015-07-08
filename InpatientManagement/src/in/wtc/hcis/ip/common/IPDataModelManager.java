/**
 * 
 */
package in.wtc.hcis.ip.common;

import java.util.List;

import in.wtc.hcis.exceptions.HCISException;

import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionClaimRequest;
import com.wtc.hcis.ip.da.AdmissionEntryPoint;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.Attribute;
import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.BedHasSpecialFeatures;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedSpecialFeature;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.CreditClass;
import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.ReservationStatus;
import com.wtc.hcis.ip.da.SponsorClaimStatus;
import com.wtc.hcis.ip.da.SponsorType;

/**
 * @author Alok Ranjan
 *
 */
public interface IPDataModelManager {
	
	
	/**
	 * 
	 * @param doctorOrderNbr
	 * @return
	 * @throws HCISException
	 */
	DischargeOrder getDischargeOrder( Integer admissionReqNbr ) throws HCISException;
	
	/**
	 * 
	 * @param dischargeTypeCd
	 * @return
	 * @throws HCISException
	 */
	DischargeType getDischargeTpe( String dischargeTypeCd ) throws HCISException;
	
	/**
	 * 
	 * @param admissionEntryPointCd
	 * @return
	 * @throws HCISException
	 */
	AdmissionEntryPoint getAdmissionEntryPoint( String admissionEntryPointCd) throws HCISException;
	
	/**
	 * 
	 * @param admissionStatusCd
	 * @return
	 * @throws HCISException
	 */
	AdmissionStatus getAdmissionStatus( String admissionStatusCd ) throws HCISException;
	
	/**
	 * 
	 * @param activityTypeCd
	 * @return
	 * @throws HCISException
	 */
	ActivityType getActivityType( String activityTypeCd ) throws HCISException;
	
	/**
	 * Ideally this method should have been part of HCIS core / base module
	 * 
	 * @param serviceId
	 * @return
	 * @throws HCISException
	 */
	Service getService( String serviceId ) throws HCISException;
	
	/**
	 * 
	 * @param actionStatusCd
	 * @return
	 * @throws HCISException
	 */
	ActionStatus getActionStatus( String actionStatusCd ) throws HCISException;
	
	/**
	 * 
	 * @param doctorStatusCd
	 * @return
	 * @throws HCISException
	 */
	DoctorOrderStatus getDoctorOrderStatus( String doctorStatusCd ) throws HCISException;
	
	/**
	 * 
	 * @param orderNumber
	 * @return
	 * @throws HCISException
	 */
	DoctorOrder getDoctorOrder( Integer orderNumber ) throws HCISException;
	
	/**
	 * 
	 * @param templateId
	 * @return
	 * @throws HCISException
	 */
	DoctorOrderTemplate getDoctorOrderTemplate( String templateId ) throws HCISException;
	
	/**
	 * 
	 * @param doctorOrderGroupName
	 * @return
	 * @throws HCISException
	 */
	DoctorOrderGroup getDoctorOrderGroup( String doctorOrderGroupName ) throws HCISException;
	
	/**
	 * For a given order type code , this method returns DoctorOrderType object.
	 * This method assumes that corresponding DoctorOrderType exist in the database.
	 * @param doctorOrderTypeCd
	 * @return
	 * @throws HCISException
	 */
	DoctorOrderType getDoctorOrderType( String doctorOrderTypeCd ) throws HCISException;

	/**
	 * For a given admission request Number , this method returns Admission object.
	 * This method assumes that Admission exist in the database.
	 * @param bedPoolUnitTypeId
	 * @return
	 * @throws HCISException
	 */
	
	Admission getAdmission( Integer admissionReqNbr) throws HCISException;
	

	/**
	 * For a given bed pooltype id, this method returns BedPoolUnitType object.
	 * This method assumes that bed pool type exist in the database.
	 * @param bedPoolUnitTypeId
	 * @return
	 * @throws HCISException
	 */
	
	/* BedPoolUnitType table is Removed from database
	BedPoolUnitType getBedPoolUnitType( BedPoolUnitTypeId bedPoolUnitTypeId ) throws HCISException;
	*/
	
	/**
	 * For a given bed number, this method returns BedMaster object.
	 * This method assumes that bed exist in the database.
	 * @param bedNumber
	 * @return
	 * @throws HCISException
	 */
	BedMaster getBedMaster( String bedNumber ) throws HCISException;
	
	/**
	 * For a given bed pool name, this method returns BedPool object.
	 * This method assumes that bed pool exist in the database.
	 * @param poolName
	 * @return
	 * @throws HCISException
	 */
	BedPool getBedPool( String poolName ) throws HCISException;
	
	/**
	 * For a given envelope name, this method returns BedEnvelope object.
	 * This method assumes that bed envelope exist in the database.
	 * @param envelopeName
	 * @return
	 * @throws HCISException
	 */
	BedEnvelope getBedEnvelope( String envelopeName ) throws HCISException;
	
	/**
	 * For a given bed type code, this method returns corresponding BedType object.
	 * @param bedTypeCd
	 * @return
	 * @throws HCISException
	 */
	BedType getBedType( String bedTypeCd ) throws HCISException;
	
	/**
	 * For a given bed status code, this method returns corresponding BedStatus object.
	 * @param bedStatusCd
	 * @return
	 * @throws HCISException
	 */
	BedStatus getBedStatus( String bedStatusCd ) throws HCISException;
	
	/**
	 * For a given nursing unit name, this method returns corresponding NursingUnit object.
	 * @param nursingUnitName
	 * @return
	 * @throws HCISException
	 */
	NursingUnit getNursingUnit( String nursingUnitName ) throws HCISException;
	
	
	/**
	 * 
	 * @param nursingUnitTypeCd
	 * @return
	 * @throws HCISException
	 */
	NursingUnitType getNursingUnitType( String nursingUnitTypeCd ) throws HCISException;
	
	/**
	 * 
	 * @param bedReservationNumber
	 * @return
	 * @throws HCISException
	 */
	BedReservation getBedReservation( Integer bedReservationNumber ) throws HCISException;
	
	/**
	 * 
	 * @param bedNumber
	 * @return BedHasSpecialFeaturesList
	 * 
	 */
	
	List<BedHasSpecialFeatures> getBedHasSpecialFeaturesList( String bedNumber ) ;
	
	
	/**
	 * 
	 * @param featureName
	 * @return BedSpecialFeature
	 * @throws HCISException
	 * 
	 */
	BedSpecialFeature getBedSpecialFeature( String featureName)	throws HCISException;
	
	/**
	 * 
	 * @param sponsorName
	 * @return
	 * @throws HCISException
	 */
	ClaimSponsor getClaimSponsor ( String sponsorName) throws HCISException;
	
	/**
	 * 
	 * @param sposnsorTypeCd
	 * @return
	 * @throws HCISException
	 */
	
	SponsorType getSposorType ( String sposnsorTypeCd) throws HCISException;
	
	/**
	 * 
	 * @param requestSequenceNbr
	 * @return
	 * @throws HCISException
	 */
	
	AdmissionClaimRequest getAdmissionClaimRequest(Long requestSequenceNbr  ) throws HCISException;
	
	/**
	 * 
	 * @param planCd
	 * @return
	 * @throws HCISException
	 */
	
	InsurancePlans getInsurancePlan( String planCd ) throws HCISException;
	
	/**
	 * 
	 * @param creditClassCd
	 * @return
	 * @throws HCISException
	 */
	CreditClass getCreditClass (String creditClassCd ) throws HCISException;
	
	/**
	 * 
	 * @param claimStatusCd
	 * @return
	 * @throws HCISException
	 */
	SponsorClaimStatus getSponsorClaimStatus ( String claimStatusCd ) throws HCISException;
	
	/**
	 * 
	 * @param diseaseName
	 * @return
	 * @throws HCISException
	 */
	
	Attribute getAttribute ( String attributeName ) throws HCISException;
	
	Insurer getInsurer( String insurerName ) throws HCISException;
	
	/**
	 * 
	 * @param reservationStatusCd
	 * @return
	 * @throws HCISException
	 */
	ReservationStatus getReservationStatus( String reservationStatusCd ) throws HCISException;
	
	Integer getActiveAdmissionOfPatient( Integer patientId ) throws HCISException;
	
	ServicePackage getPackage(String pkgCode);
}
