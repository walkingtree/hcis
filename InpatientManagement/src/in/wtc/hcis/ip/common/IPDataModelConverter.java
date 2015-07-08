/**
 * 
 */
package in.wtc.hcis.ip.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.ip.AdmissionBM;
import in.wtc.hcis.bm.ip.BedBookingDetails;
import in.wtc.hcis.bm.ip.BedEnvelopeBM;
import in.wtc.hcis.bm.ip.BedEnvelopePoolAsgmBM;
import in.wtc.hcis.bm.ip.BedMasterBM;
import in.wtc.hcis.bm.ip.BedPoolBM;
import in.wtc.hcis.bm.ip.BedPoolUnitTypeAsgmBM;
import in.wtc.hcis.bm.ip.BedReservationBM;
import in.wtc.hcis.bm.ip.BedSpecialFeatureAvailability;
import in.wtc.hcis.bm.ip.ClaimRequestBM;
import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bm.ip.DoctorOrderGroupBM;
import in.wtc.hcis.bm.ip.InsurancePlanBM;
import in.wtc.hcis.bm.ip.InsurerBM;
import in.wtc.hcis.bm.ip.OrderGroupTemplateAssociationBM;
import in.wtc.hcis.bm.ip.OrderTemplateBM;
import in.wtc.hcis.bm.ip.OrderTemplateDetailsBM;
import in.wtc.hcis.bm.ip.SponsorDetailsBM;
import in.wtc.hcis.bm.ip.SponsorInsurerAssociationBM;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.ip.bo.bed.BedManagementConstants;
import in.wtc.hcis.ip.bo.order.DoctorOrderConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Gender;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.Room;
import com.wtc.hcis.da.Saluation;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.StateId;
import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionClaimRequest;
import com.wtc.hcis.ip.da.AdmissionEntryPoint;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.BedEnvelopeHasPool;
import com.wtc.hcis.ip.da.BedEnvelopeHasPoolId;
import com.wtc.hcis.ip.da.BedHasSpecialFeatures;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedPoolHasUnitType;
import com.wtc.hcis.ip.da.BedPoolHasUnitTypeId;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedSpecialFeature;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.CreditClass;
import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderDetails;
import com.wtc.hcis.ip.da.DoctorOrderDetailsId;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplate;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplateId;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetails;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetailsId;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.OrderAttributeValue;
import com.wtc.hcis.ip.da.OrderAttributeValueDAO;
import com.wtc.hcis.ip.da.ReservationStatus;
import com.wtc.hcis.ip.da.SponsorClaimStatus;
import com.wtc.hcis.ip.da.SponsorInsurerAssociation;
import com.wtc.hcis.ip.da.SponsorType;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class IPDataModelConverter implements Serializable {
	
	private IPDataModelManager dataModelManager;
	private DataModelManager coreDataModelManager;
	private OrderAttributeValueDAO orderAttributeValueDAO;
	
	public DischargeBM convertDischargeOrderDM2BM( DischargeOrder dischargeOrder ) {
		DischargeBM dischargeBM = new DischargeBM();
		dischargeBM.setActualDischargeDtm( dischargeOrder.getActualDischargeDtm());
		dischargeBM.setAdmissionReqNbr( dischargeOrder.getAdmissionReqNbr() );
		dischargeBM.setApprovalTime( dischargeOrder.getApprovalTime() );
		dischargeBM.setApprovedBy( dischargeOrder.getApprovedBy() );
		
		DischargeType dischargeType = dischargeOrder.getDischargeType();
		if ( dischargeType != null ) {
			dischargeBM.setDischargeType( new CodeAndDescription( dischargeType.getDischargeTypeCd(), dischargeType.getDescription() ) );
		}
		
		dischargeBM.setDoctorId( dischargeOrder.getDoctorOrder().getDoctorId() );
		dischargeBM.setDoctorOrderNbr( dischargeOrder.getDoctorOrder().getDoctorOrderNbr() );
		DoctorOrderStatus doctorOrderStatus = dischargeOrder.getDoctorOrderStatus();
		dischargeBM.setDoctorOrderStatus( new CodeAndDescription( doctorOrderStatus.getOrderStatusCd(), doctorOrderStatus.getStatusDesc() ) );
		dischargeBM.setExpectedDischargeDate( dischargeOrder.getExpectedDischargeDtm() );
		dischargeBM.setLastModifiedDtm( dischargeOrder.getLastModifiedDtm() );
		dischargeBM.setModifiedBy( dischargeOrder.getModifiedBy() );
		dischargeBM.setOrderCreationDtm( dischargeOrder.getOrderCreationDtm() );
		dischargeBM.setOrderDesc( dischargeOrder.getDoctorOrder().getOrderDesc() );
		dischargeBM.setOrderDictation( dischargeOrder.getDoctorOrder().getOrderDictation() );
		dischargeBM.setOrderRequestedBy( dischargeOrder.getOrderRequestedBy() );
		dischargeBM.setDischargeSummary( dischargeOrder.getDischargeSummary() );
		dischargeBM.setPatientId( dischargeOrder.getDoctorOrder().getPatientId() );
		return dischargeBM;
	}
	public DischargeOrder convertDischargeOrderBM2DM( DischargeBM dischargeBM, DischargeOrder existingDischargeOrder ) {
		DischargeOrder dischargeOrder = null;
		
		if ( existingDischargeOrder != null ) {
			dischargeOrder = existingDischargeOrder;
		} else {
			dischargeOrder = new DischargeOrder();
		}
		
		
		Admission admission = dataModelManager.getAdmission( dischargeBM.getAdmissionReqNbr() );
		dischargeOrder.setAdmission(admission);
		dischargeOrder.setAdmissionReqNbr( admission.getAdmissionReqNbr() );
		dischargeOrder.setApprovalTime( dischargeBM.getApprovalTime() );
		dischargeOrder.setApprovedBy( dischargeBM.getApprovedBy() );
		
		if( dischargeBM.getDischargeType() != null && dischargeBM.getDischargeType().getCode() != null 
												   && !dischargeBM.getDischargeType().getCode().isEmpty() ){
			
		DischargeType dischargeType = dataModelManager.getDischargeTpe( dischargeBM.getDischargeType().getCode() );
		dischargeOrder.setDischargeType(dischargeType);
		}
		
		if( dischargeBM.getDoctorOrderNbr() != null ){
			DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( dischargeBM.getDoctorOrderNbr() );
			dischargeOrder.setDoctorOrder(doctorOrder);
		}
		
		if( dischargeBM.getDoctorOrderStatus()!= null && dischargeBM.getDoctorOrderStatus().getCode() != null 
													  && !dischargeBM.getDoctorOrderStatus().getCode().isEmpty() ){
			DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( dischargeBM.getDoctorOrderStatus().getCode() );
			dischargeOrder.setDoctorOrderStatus(doctorOrderStatus);
		}
		dischargeOrder.setExpectedDischargeDtm( dischargeBM.getExpectedDischargeDate() );
		dischargeOrder.setDischargeSummary( dischargeBM.getDischargeSummary() );
		dischargeOrder.setLastModifiedDtm( dischargeBM.getLastModifiedDtm() );
		dischargeOrder.setModifiedBy( dischargeBM.getModifiedBy() );
		dischargeOrder.setOrderCreationDtm( dischargeBM.getOrderCreationDtm() );
		dischargeOrder.setOrderRequestedBy( dischargeBM.getOrderRequestedBy() );
		dischargeOrder.setDischargeSummary( dischargeBM.getDischargeSummary() );
		return dischargeOrder;
	}
	
	public Admission convertAdmissionBM2DM( AdmissionBM admissionBM, Admission existingAdmission ) {
		
		Admission admission = null;
		if ( existingAdmission != null ) {
			admission = existingAdmission;
		} else {
			admission = new Admission();
		}
		
		admission.setAdmissionDt( admissionBM.getAdmissionDt() );
		if ( admissionBM.getAdmissionEntryPoint() != null && admissionBM.getAdmissionEntryPoint().getCode() != null
														  && !admissionBM.getAdmissionEntryPoint().getCode().isEmpty()) {
			AdmissionEntryPoint admissionEntryPoint = dataModelManager.getAdmissionEntryPoint( admissionBM.getAdmissionEntryPoint().getCode() );
			admission.setAdmissionEntryPoint( admissionEntryPoint );
		}
		
		admission.setAdmissionNbr( admissionBM.getAdmissionNbr() );
		admission.setAdmissionRequestedBy( admissionBM.getAdmissionRequestedBy() );
		
		if ( admissionBM.getAdmissionStatus() != null && admissionBM.getAdmissionStatus().getCode() != null
													  && !admissionBM.getAdmissionStatus().getCode().isEmpty()) {
			AdmissionStatus admissionStatus = dataModelManager.getAdmissionStatus( admissionBM.getAdmissionStatus().getCode() ) ;
			admission.setAdmissionStatus( admissionStatus );
		}
		
		admission.setAgenda( admissionBM.getAgenda() );
		admission.setCreateDtm( admissionBM.getCreateDtm() );
		admission.setDischargeByDoctorId( admissionBM.getDischargeByDoctorId() );
		admission.setDischargeDtm( admissionBM.getDischargeDtm() );
		admission.setDoctorId( admissionBM.getDoctorId() );
		admission.setEntryPointReference( admissionBM.getEntryPointReference() );
		admission.setExpectedDischargeDtm( admissionBM.getExpectedDischargeDtm() );
		admission.setLastUpdatedDtm( admissionBM.getLastUpdatedDtm() );
		admission.setModifiedBy( admissionBM.getModifiedBy() );
		admission.setNextVisitDt( admissionBM.getNextVisitDt() );
		
		if ( admissionBM.getNursingUnitType() != null && admissionBM.getNursingUnitType().getCode() != null
													  && !admissionBM.getNursingUnitType().getCode().isEmpty() ) {
			NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType( admissionBM.getNursingUnitType().getCode() ) ;
			admission.setNursingUnitType( nursingUnitType );
		}
		
		if ( admissionBM.getDoctorOrderNbr() != null ){
		
//			admission.setDoctorOrder( dataModelManager.getDoctorOrder(admissionBM.getDoctorOrderNbr()));
		}
		
		admission.setPatientId( admissionBM.getPatientId() );
		admission.setReasonForAdmission( admissionBM.getReasonForAdmission() );
		admission.setTreatmentActualCost( admissionBM.getTreatmentActualCost() );
		admission.setTreatmentEstimatedCost( admissionBM.getTreatmentEstimatedCost() );
		admission.setTreatmentEstimationBy( admissionBM.getTreatmentEstimationBy() );
		admission.setAdmissionRequestedBy(admissionBM.getAdmissionRequestedBy());

		return admission;
	}
	
	public AdmissionBM convertAdmissionDM2BM( Admission admission ) {
		AdmissionBM admissionBM = new AdmissionBM();
		admissionBM.setAdmissionDt( admission.getAdmissionDt() );
		if ( admission.getAdmissionEntryPoint() != null ) {
			admissionBM.setAdmissionEntryPoint( new CodeAndDescription( admission.getAdmissionEntryPoint().getEntryPointName(),
																		admission.getAdmissionEntryPoint().getEntryPointDesc() ));
		}
		admissionBM.setAdmissionReqNbr(admission.getAdmissionReqNbr());
		admissionBM.setAdmissionNbr( admission.getAdmissionNbr() );
		admissionBM.setAdmissionRequestedBy( admission.getAdmissionRequestedBy() );
		
		if ( admission.getAdmissionStatus() != null){
		AdmissionStatus admissionStatus = admission.getAdmissionStatus();
		admissionBM.setAdmissionStatus( new CodeAndDescription( admissionStatus.getAdmissionStatusCd(), 
				                                                admissionStatus.getAdmissionStatusDesc() ) );
		}
		
		admissionBM.setAgenda( admission.getAgenda() );
		admissionBM.setCreateDtm( admission.getCreateDtm() );
		admissionBM.setDischargeByDoctorId( admission.getDischargeByDoctorId() );
		admissionBM.setDischargeDtm( admission.getDischargeDtm() );
		admissionBM.setDoctorId( admission.getDoctorId() );
		admissionBM.setEntryPointReference( admission.getEntryPointReference() );
		admissionBM.setExpectedDischargeDtm( admission.getExpectedDischargeDtm() );
		admissionBM.setLastUpdatedDtm( admission.getLastUpdatedDtm() );
		admissionBM.setModifiedBy( admission.getModifiedBy() );
		admissionBM.setNextVisitDt( admission.getNextVisitDt() );
		
		NursingUnitType nursingUnitType = admission.getNursingUnitType();
		if( null != nursingUnitType ){
			admissionBM.setNursingUnitType( new CodeAndDescription( nursingUnitType.getUnitTypeCd(), nursingUnitType.getUnitTypeDesc() ));
			admissionBM.setPatientId( admission.getPatientId() );
		}
		
		
		Patient patient = coreDataModelManager.getPatient(admission.getPatientId());
		if( patient != null ){
			admissionBM.setPatientName(patient.getFullName());
		}
		admissionBM.setReasonForAdmission( admission.getReasonForAdmission() );
		admissionBM.setTreatmentActualCost( admission.getTreatmentActualCost() );
		admissionBM.setTreatmentEstimatedCost( admission.getTreatmentEstimatedCost() );
		admissionBM.setTreatmentEstimationBy( admission.getTreatmentEstimationBy() );
		
		return admissionBM;
	}
	
	public DoctorOrderDetails convertDoctorOrderDetailsBM2DM( DoctorOrderDetailsBM doctorOrderDetailsBM, 
															  DoctorOrderDetails existingDoctorOrderDetails ) {
		
		DoctorOrderDetails doctorOrderDetails = null;
		if ( existingDoctorOrderDetails != null ) {
			doctorOrderDetails = existingDoctorOrderDetails;
		} else {
			doctorOrderDetails = new DoctorOrderDetails();
		}
		
		
		doctorOrderDetails.setActionDesc( doctorOrderDetailsBM.getActionDesc() );
		doctorOrderDetails.setActionDtm( doctorOrderDetailsBM.getActionDtm() );
		doctorOrderDetails.setActionRemarks( doctorOrderDetailsBM.getActionRemarks() );
		
		if( doctorOrderDetailsBM.getActionStatus()!= null && doctorOrderDetailsBM.getActionStatus().getCode() != null
														  && !doctorOrderDetailsBM.getActionStatus().getCode().isEmpty() ){
			ActionStatus actionStatus = dataModelManager.getActionStatus( doctorOrderDetailsBM.getActionStatus().getCode() );
			doctorOrderDetails.setActionStatus(actionStatus);
		}
		
		
		doctorOrderDetails.setActionTakenBy( doctorOrderDetailsBM.getActionTakenBy() );
		
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( doctorOrderDetailsBM.getDoctorOrderNumber() );
		doctorOrderDetails.setDoctorOrder(doctorOrder);
		
		DoctorOrderDetailsId doctorOrderDetailsId = new DoctorOrderDetailsId();
		doctorOrderDetailsId.setOrderNbr( doctorOrder.getDoctorOrderNbr() );
		doctorOrderDetailsId.setSequenceNbr( doctorOrderDetailsBM.getSequenceNbr() );
		doctorOrderDetailsId.setSubSequenceNbr( doctorOrderDetailsBM.getSubSequenceNbr() );
		doctorOrderDetails.setId( doctorOrderDetailsId );
		
		if (ApplicationConstants.YES_CODE.equals(doctorOrderDetailsBM.getPackageIndicator())) {
			ServicePackage svcPackage = dataModelManager.getPackage( doctorOrderDetailsBM.getServicePackage().getCode() );
			doctorOrderDetails.setPackageId( svcPackage.getPackageId() );
			doctorOrderDetails.setResponsibleDepartmentId( doctorOrderDetailsBM.getResponsibleDepartment().getCode() );
		}

		if ( doctorOrderDetailsBM.getServiceCode() != null && doctorOrderDetailsBM.getServiceCode().length() > 0 ) {
			Service service = dataModelManager.getService( doctorOrderDetailsBM.getServiceCode() );
			doctorOrderDetails.setServiceId( service.getServiceCode() );
			doctorOrderDetails.setResponsibleDepartmentId( service.getDepartment().getDepartmentCode() );
		} else {
			//
			// If service id has not been specified then copy the responsible department id as it is. 
			// This may be applicable when an item on the order is not actually configured as a service
			// in service master table but someone still needs to take some action.
			//
			if( doctorOrderDetailsBM.getResponsibleDepartment() != null ){
				doctorOrderDetails.setResponsibleDepartmentId( doctorOrderDetailsBM.getResponsibleDepartment().getCode() );
			}
			
		}
		
		
		return doctorOrderDetails;
	}
	
	public DoctorOrderDetailsBM convertDoctorOrderDetailsDM2BM( DoctorOrderDetails doctorOrderDetails ){
		  
			DoctorOrderDetailsBM doctorOrderDetailsBM = new DoctorOrderDetailsBM();
			
			doctorOrderDetailsBM.setActionDesc( doctorOrderDetails.getActionDesc() );
			doctorOrderDetailsBM.setActionDtm( doctorOrderDetails.getActionDtm() );
			doctorOrderDetailsBM.setActionRemarks( doctorOrderDetails.getActionRemarks() );
			doctorOrderDetailsBM.setActionTakenBy( doctorOrderDetails.getActionTakenBy() );
			
			ActionStatus actionStatus = doctorOrderDetails.getActionStatus();
			if( actionStatus != null ){
				doctorOrderDetailsBM.setActionStatus(new CodeAndDescription( actionStatus.getActionStatusCd(), actionStatus.getActionStatusDesc()));
			}
			doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrderDetails.getId().getOrderNbr() );
			
			if( doctorOrderDetails.getResponsibleDepartmentId() != null && !doctorOrderDetails.getResponsibleDepartmentId().isEmpty()){
				Department department = coreDataModelManager.getDepartment( doctorOrderDetails.getResponsibleDepartmentId() );
				if( department != null ){
					doctorOrderDetailsBM.setResponsibleDepartment( new CodeAndDescription(department.getDepartmentCode(),department.getDepartmentName()));
				}
			}
			
			doctorOrderDetailsBM.setSequenceNbr( doctorOrderDetails.getId().getSequenceNbr() );
			
			if( doctorOrderDetails.getServiceId() != null && !doctorOrderDetails.getServiceId().isEmpty()){
				
				Service service = coreDataModelManager.getServiceByCode( doctorOrderDetails.getServiceId() );
				doctorOrderDetailsBM.setServiceCode( service.getServiceCode() );
				doctorOrderDetailsBM.setServiceName( service.getServiceName() );
			}
			
			doctorOrderDetailsBM.setPackageIndicator( DoctorOrderConstants.PACKAGE_INDICATOR_NO);
			
			if( doctorOrderDetails.getPackageId() != null && !doctorOrderDetails.getPackageId().isEmpty())
			{
				ServicePackage servicePackage = coreDataModelManager.getServicePackageByCode(doctorOrderDetails.getPackageId());
				
				doctorOrderDetailsBM.setServicePackage( new CodeAndDescription(
														servicePackage.getPackageId(),
														servicePackage.getName()));
				
				doctorOrderDetailsBM.setPackageIndicator( DoctorOrderConstants.PACKAGE_INDICATOR);
			}
			
			
			doctorOrderDetailsBM.setSubSequenceNbr( doctorOrderDetails.getId().getSubSequenceNbr() );
			
			return doctorOrderDetailsBM;
	}
	
	public DoctorOrder convertDoctorOrderBM2DM( DoctorOrderBM doctorOrderBM, DoctorOrder existingDoctorOrder ) {
		DoctorOrder doctorOrder = null;
		
		if ( existingDoctorOrder != null ) {
			doctorOrder = existingDoctorOrder;
		} else {
			doctorOrder = new DoctorOrder();
		}
		
		if ( doctorOrderBM.getAdmissionNbr() != null ) {
			Admission admission = dataModelManager.getAdmission( doctorOrderBM.getAdmissionNbr() );
			doctorOrder.setAdmission(admission);
		}
		
		if ( doctorOrderBM.getDoctorOrderStatus() != null && doctorOrderBM.getDoctorOrderStatus().getCode() != null 
														  && !doctorOrderBM.getDoctorOrderStatus().getCode().isEmpty() ) {
			DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( doctorOrderBM.getDoctorOrderStatus().getCode() );
			doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
		}
		
		if ( doctorOrderBM.getDoctorOrderTemplate() != null && doctorOrderBM.getDoctorOrderTemplate().getCode() != null 
															&& !doctorOrderBM.getDoctorOrderTemplate().getCode().isEmpty() ) {
			
			DoctorOrderTemplate doctorOrderTemplate = dataModelManager.getDoctorOrderTemplate( doctorOrderBM.getDoctorOrderTemplate().getCode() );
			doctorOrder.setDoctorOrderTemplate(doctorOrderTemplate);
		}
		
		if ( doctorOrderBM.getDoctorOrderType() != null && doctorOrderBM.getDoctorOrderType().getCode() != null
														&& !doctorOrderBM.getDoctorOrderType().getCode().isEmpty() ) {
			
			DoctorOrderType doctorOrderType = dataModelManager.getDoctorOrderType( doctorOrderBM.getDoctorOrderType().getCode() );
			doctorOrder.setDoctorOrderType( doctorOrderType );
		}
		
		
		doctorOrder.setOrderDesc( doctorOrderBM.getOrderDesc() );
		
		if (doctorOrderBM.getOrderDictation() == null){
			doctorOrder.setOrderDictation( DoctorOrderConstants.ORDER_DICTATION_SELF );	
		}else{
			doctorOrder.setOrderDictation( doctorOrderBM.getOrderDictation() );
		}
		
		doctorOrder.setDoctorId( doctorOrderBM.getDoctorId() );
		doctorOrder.setPatientId( doctorOrderBM.getPatientId() );
		
		if ( doctorOrderBM.getDoctorOrderGroup() != null && doctorOrderBM.getDoctorOrderGroup().getCode() != null 
														 && !doctorOrderBM.getDoctorOrderGroup().getCode().isEmpty() ){
			
			DoctorOrderGroup doctorOrderGroup = dataModelManager.getDoctorOrderGroup( doctorOrderBM.getDoctorOrderGroup().getCode() );
			doctorOrder.setDoctorOrderGroup( doctorOrderGroup );
		}
		
		return doctorOrder;
	}
	
	public DoctorOrderBM convertDoctorOrderDM2BM( DoctorOrder doctorOrder ) {
		
		DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
		
		Admission admission = doctorOrder.getAdmission();
		if ( admission != null ) {
			doctorOrderBM.setAdmissionNbr( admission.getAdmissionNbr() );
		}
		
		
		doctorOrderBM.setDoctorOrderNbr( doctorOrder.getDoctorOrderNbr() );
		
		DoctorOrderStatus doctorOrderStatus = doctorOrder.getDoctorOrderStatus();
		if ( doctorOrderStatus != null ) {
			doctorOrderBM.setDoctorOrderStatus( new CodeAndDescription( doctorOrderStatus.getOrderStatusCd(), doctorOrderStatus.getStatusDesc() ) );
		}
		
		DoctorOrderTemplate doctorOrderTemplate = doctorOrder.getDoctorOrderTemplate();
		if ( doctorOrderTemplate != null ) {
			doctorOrderBM.setDoctorOrderTemplate( new CodeAndDescription( doctorOrderTemplate.getTemplateId(), doctorOrderTemplate.getTemplateDesc() ) );
		}
		
		DoctorOrderType doctorOrderType = doctorOrder.getDoctorOrderType();
		if ( doctorOrderType != null ) {
			doctorOrderBM.setDoctorOrderType( new CodeAndDescription( doctorOrderType.getOrderTypeCd(), doctorOrderType.getOrderTypeDesc() ) );
		}
		
		doctorOrderBM.setOrderDesc( doctorOrder.getOrderDesc() );
		doctorOrderBM.setOrderDictation( doctorOrder.getOrderDictation() );
		
		doctorOrderBM.setDoctorId( doctorOrder.getDoctorId() );
		doctorOrderBM.setPatientId( doctorOrder.getPatientId() );

// code below is added later  		
		DoctorOrderGroup doctorOrderGroup = doctorOrder.getDoctorOrderGroup();
		if( doctorOrderGroup != null ){
			doctorOrderBM.setDoctorOrderGroup(new CodeAndDescription( doctorOrderGroup.getOrderGroupName(), doctorOrderGroup.getDescription() ));
		}
		
		doctorOrderBM.setCreatedBy( doctorOrder.getCreatedBy() );
		doctorOrderBM.setOrderCreationDate( doctorOrder.getOrderCreationDtm() );
		
		if ( doctorOrder.getPatientId() != null  ){
			Patient patient = coreDataModelManager.getPatient( doctorOrder.getPatientId() );
			doctorOrderBM.setPatientName( patient.getFullName() );
		}
		
		if( doctorOrder.getDoctorId() != null ){
			Doctor doctor = coreDataModelManager.getDoctor( doctorOrder.getDoctorId() );
					
			doctorOrderBM.setDoctorName(doctor.getFullName());
		}
		
		List <OrderAttributeValue> orderAttributeValueList = orderAttributeValueDAO.findByProperty("id.orderNbr", doctorOrder.getDoctorOrderNbr());
		
		//Build attribute value map
		Map<String, String> admissionOrderAttributes = new HashMap<String, String>(0);
		
		if( orderAttributeValueList != null && !orderAttributeValueList.isEmpty() ){
			
			for( OrderAttributeValue attributeValue : orderAttributeValueList  ){
				
				admissionOrderAttributes.put(attributeValue.getId().getAttributeName(), attributeValue.getAttributeValue() );
			}
		}

		doctorOrderBM.setOrderSpecificAttributes(admissionOrderAttributes);
		return doctorOrderBM;
	}
	

	public DoctorOrderGroupHasTemplate convertDoctorOrderGroupHasTemplateBM2DM ( OrderGroupTemplateAssociationBM groupTemplateAssociationBM, 
			                                                                     DoctorOrderGroupHasTemplate existingDoctorOrderGroup ) {
		DoctorOrderGroupHasTemplate groupHasTemplate = null;
		
		if ( existingDoctorOrderGroup != null ) {
			groupHasTemplate = existingDoctorOrderGroup;
			
		} else {
			groupHasTemplate = new DoctorOrderGroupHasTemplate();
			
			DoctorOrderGroup doctorOrderGroup = dataModelManager.getDoctorOrderGroup( groupTemplateAssociationBM.getOrderGroupName() );
			DoctorOrderTemplate doctorOrderTemplate = dataModelManager.getDoctorOrderTemplate( groupTemplateAssociationBM.getOrderTemplateName() );
			
			DoctorOrderGroupHasTemplateId groupHasTemplateId = new DoctorOrderGroupHasTemplateId();
			groupHasTemplateId.setOrderGroupName( doctorOrderGroup.getOrderGroupName() );
			groupHasTemplateId.setOrderTemplateId( doctorOrderTemplate.getTemplateId() );
			groupHasTemplateId.setSequenceNbr( groupTemplateAssociationBM.getSequenceNbr() );
			groupHasTemplate.setId( groupHasTemplateId );
			groupHasTemplate.setDoctorOrderGroup(doctorOrderGroup);
			groupHasTemplate.setDoctorOrderTemplate(doctorOrderTemplate);
		}
		
		groupHasTemplate.setEffectiveFromDt( groupTemplateAssociationBM.getEffectiveFromDt() );
		groupHasTemplate.setEffectiveToDt( groupTemplateAssociationBM.getEffectiveToDt() );
		return groupHasTemplate;
	}
	
	public OrderGroupTemplateAssociationBM convertDoctorOrderGroupHasTemplateDM2BM ( DoctorOrderGroupHasTemplate doctorOrderGroupHasTemplate ) {
		OrderGroupTemplateAssociationBM groupTemplateAssociationBM = new OrderGroupTemplateAssociationBM();
		groupTemplateAssociationBM.setEffectiveFromDt( doctorOrderGroupHasTemplate.getEffectiveFromDt() );
		groupTemplateAssociationBM.setEffectiveToDt( doctorOrderGroupHasTemplate.getEffectiveToDt() );
		
		DoctorOrderGroup doctorOrderGroup = doctorOrderGroupHasTemplate.getDoctorOrderGroup();
		groupTemplateAssociationBM.setOrderGroupName( doctorOrderGroup.getOrderGroupName() );
		
		DoctorOrderTemplate doctorOrderTemplate = doctorOrderGroupHasTemplate.getDoctorOrderTemplate();
		groupTemplateAssociationBM.setOrderTemplateName( doctorOrderTemplate.getTemplateId() );
		groupTemplateAssociationBM.setOrderTemplateDesc( doctorOrderTemplate.getTemplateDesc() );
		groupTemplateAssociationBM.setSequenceNbr( doctorOrderGroupHasTemplate.getId().getSequenceNbr() );
		
		return groupTemplateAssociationBM;
	}
	
	public DoctorOrderGroup convertDoctorOrderGroupBM2DM( DoctorOrderGroupBM doctorOrderGroupBM, DoctorOrderGroup existingDoctorOrderGroup ) {
		
		DoctorOrderGroup doctorOrderGroup = null;
		
		if ( existingDoctorOrderGroup != null ) {
			doctorOrderGroup = existingDoctorOrderGroup;
		} else {
			doctorOrderGroup = new DoctorOrderGroup();
			doctorOrderGroup.setOrderGroupName( doctorOrderGroupBM.getOrderGroupName() );
		}
		
		doctorOrderGroup.setDescription( doctorOrderGroupBM.getOrderGroupDesc() );
		doctorOrderGroup.setGroupForDoctorId( doctorOrderGroupBM.getGroupForDoctorId() );
		doctorOrderGroup.setCreatedBy( doctorOrderGroupBM.getCreatedBy() );
		
		return doctorOrderGroup;
	}
	
	public DoctorOrderGroupBM convertDoctorOrderGroupDM2BM( DoctorOrderGroup doctorOrderGroup ) {
		
		DoctorOrderGroupBM doctorOrderGroupBM = new DoctorOrderGroupBM();
		doctorOrderGroupBM.setGroupForDoctorId( doctorOrderGroup.getGroupForDoctorId() );
		doctorOrderGroupBM.setOrderGroupName( doctorOrderGroup.getOrderGroupName() );
		doctorOrderGroupBM.setOrderGroupDesc(doctorOrderGroup.getDescription() );
		doctorOrderGroupBM.setCreatedBy( doctorOrderGroup.getCreatedBy() );
		doctorOrderGroupBM.setCreationDate( doctorOrderGroup.getCreationDtm() );
		
		if( doctorOrderGroup.getGroupForDoctorId() != null ){
			Doctor doctor = coreDataModelManager.getDoctor( doctorOrderGroup.getGroupForDoctorId() );
			doctorOrderGroupBM.setGroupForDoctorName( doctor.getFullName());
		}

		return doctorOrderGroupBM;
	}
	
	public OrderTemplateBM convertDoctorOrderTemplateDM2BM( DoctorOrderTemplate doctorOrderTemplate ) {
		
		OrderTemplateBM orderTemplateBM = new OrderTemplateBM();
		
		orderTemplateBM.setActiveFlag( doctorOrderTemplate.getActiveFlag() );
		orderTemplateBM.setDoctorId( doctorOrderTemplate.getDoctorId() );
		
		if( doctorOrderTemplate.getDoctorId() != null ){
			Doctor doctor = coreDataModelManager.getDoctor( doctorOrderTemplate.getDoctorId() );
			
			orderTemplateBM.setDoctorName( doctor.getFullName() );
		}
		DoctorOrderType doctorOrderType = doctorOrderTemplate.getDoctorOrderType();
		
		if( doctorOrderType != null ){
		orderTemplateBM.setDoctorOrderType( new CodeAndDescription( doctorOrderType.getOrderTypeCd(), doctorOrderType.getOrderTypeDesc() ) );
		}
		orderTemplateBM.setTemplateDesc( doctorOrderTemplate.getTemplateDesc() );
		orderTemplateBM.setTemplateId( doctorOrderTemplate.getTemplateId() );
		
		return orderTemplateBM;
	}
	
	public DoctorOrderTemplate convertDoctorOrderTemplateBM2DM( OrderTemplateBM orderTemplateBM , DoctorOrderTemplate existingTemplateDM ) {
		
		DoctorOrderTemplate doctorOrderTemplate = null;
		
		if ( existingTemplateDM != null ) {
			doctorOrderTemplate = existingTemplateDM;
		} else {
			doctorOrderTemplate = new DoctorOrderTemplate();
			doctorOrderTemplate.setTemplateId( orderTemplateBM.getTemplateId() );
		}
		
		doctorOrderTemplate.setActiveFlag( orderTemplateBM.getActiveFlag() );
		doctorOrderTemplate.setDoctorId( orderTemplateBM.getDoctorId() );
		
		if( orderTemplateBM.getDoctorOrderType()!= null && orderTemplateBM.getDoctorOrderType().getCode()!=null
														&& !orderTemplateBM.getDoctorOrderType().getCode().isEmpty()){
			DoctorOrderType doctorOrderType = dataModelManager.getDoctorOrderType( orderTemplateBM.getDoctorOrderType().getCode() );
			doctorOrderTemplate.setDoctorOrderType(doctorOrderType);
		}
		doctorOrderTemplate.setTemplateDesc( orderTemplateBM.getTemplateDesc() );
		
		return doctorOrderTemplate;
	}
	
	
	public DoctorOrderTemplateDetails convertDoctorOrderTemplateDetailsBM2DM(OrderTemplateDetailsBM orderTemplateDetailsBM,DoctorOrderTemplateDetails existingTemplateDetails ){
		
		DoctorOrderTemplateDetails doctorOrderTemplateDetails = null;
		
		if( existingTemplateDetails != null){
			doctorOrderTemplateDetails = existingTemplateDetails;	
		}else{
			doctorOrderTemplateDetails = new DoctorOrderTemplateDetails();
			DoctorOrderTemplateDetailsId orderTemplateDetailsId = new DoctorOrderTemplateDetailsId();

			orderTemplateDetailsId.setTemplateId(orderTemplateDetailsBM.getTemplateId());
			orderTemplateDetailsId.setSequenceNbr(orderTemplateDetailsBM.getSequenceNbr());
			orderTemplateDetailsId.setSubSequenceNbr(orderTemplateDetailsBM.getSubSequenceNbr());
			
			doctorOrderTemplateDetails.setId(orderTemplateDetailsId);
		}
		
		doctorOrderTemplateDetails.setDoctorOrderTemplate(dataModelManager.getDoctorOrderTemplate(orderTemplateDetailsBM.getTemplateId()));
		doctorOrderTemplateDetails.setActionDesc(orderTemplateDetailsBM.getActionDesc());
		
		if ( orderTemplateDetailsBM.getResponsibleDepartment() != null ){
			doctorOrderTemplateDetails.setResponsibleDepartmentId(orderTemplateDetailsBM.getResponsibleDepartment().getCode());
		}
		if(DoctorOrderConstants.PACKAGE_INDICATOR.equals(orderTemplateDetailsBM.getPackageIndicator())){
			if(orderTemplateDetailsBM.getServicePackage()!=null && !orderTemplateDetailsBM.getServicePackage().getCode().equals("")){
				doctorOrderTemplateDetails.setPackageId(orderTemplateDetailsBM.getServicePackage().getCode());
			}
		}else{
			doctorOrderTemplateDetails.setServiceId(orderTemplateDetailsBM.getServiceCode());
		}
		
		
		return doctorOrderTemplateDetails;
		
		
	}
	
	
	public OrderTemplateDetailsBM convertDoctorOrderTemplateDetailsDM2BM(DoctorOrderTemplateDetails doctorOrderTemplateDetails){
		
			OrderTemplateDetailsBM orderTemplateDetailsBM = new OrderTemplateDetailsBM();
			
			DoctorOrderTemplateDetailsId orderTemplateDetailsId = doctorOrderTemplateDetails.getId();
			
			orderTemplateDetailsBM.setTemplateId(orderTemplateDetailsId.getTemplateId());
			orderTemplateDetailsBM.setSequenceNbr(orderTemplateDetailsId.getSequenceNbr());
			orderTemplateDetailsBM.setSubSequenceNbr(orderTemplateDetailsId.getSubSequenceNbr());
			
			if(doctorOrderTemplateDetails.getPackageId()!=null){
				orderTemplateDetailsBM.setPackageIndicator(DoctorOrderConstants.PACKAGE_INDICATOR);
				ServicePackage servicePackage = coreDataModelManager.getServicePackageByCode(doctorOrderTemplateDetails.getPackageId());
				if(servicePackage!= null){
					orderTemplateDetailsBM.setServicePackage(new CodeAndDescription(servicePackage.getPackageId(),servicePackage.getDescription()));
				}
			}else if(doctorOrderTemplateDetails.getServiceId()!=null && !doctorOrderTemplateDetails.getServiceId().equals("")){
				Service service = coreDataModelManager.getServiceByCode(doctorOrderTemplateDetails.getServiceId());
				if(service != null){
					orderTemplateDetailsBM.setServiceCode(doctorOrderTemplateDetails.getServiceId());
					orderTemplateDetailsBM.setServiceName(service.getServiceName());
					orderTemplateDetailsBM.setPackageIndicator("N");
				}
			}
			
			if( doctorOrderTemplateDetails.getServiceId() != null && !doctorOrderTemplateDetails.getServiceId().isEmpty() ){
				Service service = dataModelManager.getService( doctorOrderTemplateDetails.getServiceId() );
				orderTemplateDetailsBM.setServiceName(service.getServiceName());
				orderTemplateDetailsBM.setServiceCode(doctorOrderTemplateDetails.getServiceId());
			}
			//TODO:Action Description
			orderTemplateDetailsBM.setActionDesc(doctorOrderTemplateDetails.getActionDesc()); 
		
			if( doctorOrderTemplateDetails.getResponsibleDepartmentId() != null && !doctorOrderTemplateDetails.getResponsibleDepartmentId().isEmpty() ){

				Department department = coreDataModelManager.getDepartment( doctorOrderTemplateDetails.getResponsibleDepartmentId() );
				orderTemplateDetailsBM.setResponsibleDepartment(new CodeAndDescription( department.getDepartmentCode(), department.getDepartmentName()) );
			}
			return orderTemplateDetailsBM;
	}
	
	public BedReservationBM convertBedReservationDM2BM( BedReservation bedReservation ) {
		BedReservationBM bedReservationBM = new BedReservationBM();
		BedMaster bedMaster = bedReservation.getBedMaster();
		
		if ( bedMaster != null ) {
			bedReservationBM.setBedNumber( bedMaster.getBedNumber() );
		}
		
		if( bedReservation.getAdmission() != null ){
			bedReservationBM.setAdmissionReqNbr( bedReservation.getAdmission().getAdmissionReqNbr());
		}
		
		if( bedReservation.getAdmission() != null ){
			
			CodeAndDescription admissionStatus = new CodeAndDescription();
			admissionStatus.setCode( bedReservation.getAdmission().getAdmissionStatus().getAdmissionStatusCd());
			admissionStatus.setDescription( bedReservation.getAdmission().getAdmissionStatus().getAdmissionStatusDesc() );
			
			bedReservationBM.setAdmissionStatus(admissionStatus);
		}
		
		bedReservationBM.setBedReservationNbr( bedReservation.getBedReservationNbr() );
		
		BedType bedType = bedReservation.getBedType();
		if( bedType != null ){
			bedReservationBM.setBedType( new CodeAndDescription(bedType.getBedTypeCd(),bedType.getBedTypeDesc()));
		}
		
		bedReservationBM.setCreatedBy( bedReservation.getRequestCreatedBy());
		bedReservationBM.setGotPreferredRoom( bedReservation.getGotPreferredRoom() );
		bedReservationBM.setPatientId( bedReservation.getPatientId() );
		bedReservationBM.setRequestValidTill( bedReservation.getRequestValidTill() );
		bedReservationBM.setReservationFromDtm( bedReservation.getReservationFromDtm() );
		
		ReservationStatus reservationStatus = bedReservation.getReservationStatus();
		if ( reservationStatus != null ){
			bedReservationBM.setReservationStatus( new CodeAndDescription( reservationStatus.getReservationStatusCd(), reservationStatus.getDescription() ) );
		}
		bedReservationBM.setReservationToDtm( bedReservation.getReservationToDtm() );
		
		NursingUnitType unitType = bedReservation.getNursingUnitType();
		if( unitType != null ){
			bedReservationBM.setUnitType( new CodeAndDescription( unitType.getUnitTypeCd(), unitType.getUnitTypeDesc()));
		}
		
		return bedReservationBM;
	}
	public BedReservation convertBedReservationBM2DM( BedReservationBM bedReservationBM , BedReservation existingBedReservation){
		
		BedReservation bedReservation = null ;
		if( existingBedReservation != null ){
			bedReservation = existingBedReservation;
		}else{
			bedReservation = new BedReservation();
			bedReservation.setBedReservationNbr( bedReservationBM.getBedReservationNbr() );
		}
		
		Admission admission = dataModelManager.getAdmission( bedReservationBM.getAdmissionReqNbr());
		if( admission != null ){
			bedReservation.setAdmission( admission );
			bedReservation.setPatientId( admission.getPatientId());
		}
		if (bedReservationBM.getBedType() != null && bedReservationBM.getBedType().getCode() != null
				&& !bedReservationBM.getBedType().getCode().isEmpty()) {
			bedReservation.setBedType(dataModelManager.getBedType(bedReservationBM.getBedType().getCode()));
		}
		if (bedReservationBM.getUnitType() != null && bedReservationBM.getUnitType().getCode() != null
				&& !bedReservationBM.getUnitType().getCode().isEmpty()) {
			bedReservation.setNursingUnitType(dataModelManager.getNursingUnitType(bedReservationBM.getUnitType().getCode()));
		}
		bedReservation.setReservationFromDtm(bedReservationBM.getReservationFromDtm());
		bedReservation.setReservationToDtm(bedReservationBM.getReservationToDtm());
		
		if( bedReservationBM.getReservationStatus() != null &&  bedReservationBM.getReservationStatus().getCode() != null  ){
			bedReservation.setReservationStatus(dataModelManager.getReservationStatus(bedReservationBM.getReservationStatus().getCode()));	
		}
		
		return bedReservation;
	}
	
	public BedBookingDetails convertBedReservationDM2BedBookingDetails( BedReservation bedReservation ) {
		BedBookingDetails bedBookingDetails = new BedBookingDetails();
		
		Admission admission = bedReservation.getAdmission();
		bedBookingDetails.setAdmissionNbr( admission.getAdmissionNbr() );
		bedBookingDetails.setAdmissionDtm( admission.getAdmissionDt() );
		bedBookingDetails.setDoctorId( admission.getDoctorId() );
		
		BedMaster bedMaster = bedReservation.getBedMaster();
		if ( bedMaster != null ) {
			bedBookingDetails.setBedMasterBM( this.convertBedMasterDM2BM( bedMaster ) );
		}
		
		BedReservationBM bedReservationBM = this.convertBedReservationDM2BM( bedReservation );
		bedBookingDetails.setBedReservationBM( bedReservationBM );
		
		return bedBookingDetails;
	}
	
	public BedEnvelopeHasPool convertBedEnvelopeHasPoolBM2DM( BedEnvelopePoolAsgmBM bedEnvelopePoolAsgmBM, 
	       BedEnvelopeHasPool existingBedEnvelopeHasPool ) {
			BedEnvelopeHasPool bedEnvelopeHasPool = null;
			
			if ( existingBedEnvelopeHasPool != null ) {
				bedEnvelopeHasPool = existingBedEnvelopeHasPool;
			} else {
				bedEnvelopeHasPool = new BedEnvelopeHasPool();
			}
			BedEnvelope bedEnvelope = dataModelManager.getBedEnvelope( bedEnvelopePoolAsgmBM.getEnvelopeName() );
			//bedEnvelopeHasPool.setBedEnvelope( bedEnvelope );
			
			BedPool bedPool = dataModelManager.getBedPool( bedEnvelopePoolAsgmBM.getPoolName() );
			//bedEnvelopeHasPool.setBedPool( bedPool );
			
			BedEnvelopeHasPoolId bedEnvelopeHasPoolId = new BedEnvelopeHasPoolId();
			bedEnvelopeHasPoolId.setEnvelopeName(bedEnvelope.getEnvelopeName());
			bedEnvelopeHasPoolId.setPoolName(bedPool.getBedPoolName());
			bedEnvelopeHasPool.setId(bedEnvelopeHasPoolId);
			
			
			bedEnvelopeHasPool.setEffectiveFromDt( bedEnvelopePoolAsgmBM.getEffectiveFromDt() );
			bedEnvelopeHasPool.setEffectiveToDt( bedEnvelopePoolAsgmBM.getEffectiveToDt() );
			
			return bedEnvelopeHasPool;
	}
	
	public BedEnvelopePoolAsgmBM convertBedEnvelopeHasPoolDM2BM( BedEnvelopeHasPool bedEnvelopePoolAsgm ) {
		BedEnvelopePoolAsgmBM bedEnvelopePoolAsgmBM = new BedEnvelopePoolAsgmBM();
	
		bedEnvelopePoolAsgmBM.setEnvelopeName( bedEnvelopePoolAsgm.getBedEnvelope().getEnvelopeName() );
	
		bedEnvelopePoolAsgmBM.setPoolName( bedEnvelopePoolAsgm.getBedPool().getBedPoolName() );
		bedEnvelopePoolAsgmBM.setPoolDescription( bedEnvelopePoolAsgm.getBedPool().getPoolDesc());
		
		bedEnvelopePoolAsgmBM.setEffectiveFromDt( bedEnvelopePoolAsgm.getEffectiveFromDt() );
		bedEnvelopePoolAsgmBM.setEffectiveToDt( bedEnvelopePoolAsgm.getEffectiveToDt() );
		
		return bedEnvelopePoolAsgmBM;
	}
	
	public BedEnvelopeBM convertBedEnvelopeDM2BM( BedEnvelope bedEnvelope ) {
		BedEnvelopeBM bedEnvelopeBM = new BedEnvelopeBM();
		bedEnvelopeBM.setDescription( bedEnvelope.getDescription() );
		bedEnvelopeBM.setEnvelopeName( bedEnvelope.getEnvelopeName() );
		
		bedEnvelopeBM.setFacilityTypeInd( bedEnvelope.getFacilityTypeInd() );
		
		return bedEnvelopeBM;
	}
	
	public BedEnvelope convertBedEnvelopeBM2DM( BedEnvelopeBM bedEnvelopeBM,
			                                    BedEnvelope existingBedEnvelope ) {
		BedEnvelope bedEnvelope = null;
		
		if ( existingBedEnvelope != null ) {
			bedEnvelope = existingBedEnvelope;
		} else {
			bedEnvelope = new BedEnvelope();
			bedEnvelope.setEnvelopeName( bedEnvelopeBM.getEnvelopeName() );
		}
		bedEnvelope.setDescription( bedEnvelopeBM.getDescription() );
		bedEnvelope.setFacilityTypeInd( bedEnvelopeBM.getFacilityTypeInd() );
		
		return bedEnvelope;
	}
	
	public BedPoolUnitTypeAsgmBM convertBedPoolHasUnitTypeDM2BM( BedPoolHasUnitType bedPoolHasUnitType ) {
		
		BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM = new BedPoolUnitTypeAsgmBM();
		
//		BedType bedType = bedPoolHasUnitType.getBedType();
//		bedPoolUnitTypeAsgmBM.setBedType( new CodeAndDescription( bedType.getBedTypeCd(), bedType.getBedTypeDesc() ) );
		
		NursingUnitType nursingUnitType = bedPoolHasUnitType.getNursingUnitType();
		bedPoolUnitTypeAsgmBM.setUnitType( new CodeAndDescription(nursingUnitType.getUnitTypeCd(), nursingUnitType.getUnitTypeDesc() ) );
		bedPoolUnitTypeAsgmBM.setPoolName( bedPoolHasUnitType.getBedPool().getBedPoolName() );
		bedPoolUnitTypeAsgmBM.setEffectiveFromDt( bedPoolHasUnitType.getEffectiveFromDt() );
		bedPoolUnitTypeAsgmBM.setEffectiveToDate( bedPoolHasUnitType.getEffectiveToDate() );
		
		return bedPoolUnitTypeAsgmBM;
	}
	
	public BedPoolHasUnitType convertBedPoolHasUnitTypeBM2DM( BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM, 
			                                                  BedPoolHasUnitType existingBedPoolHasUnitType ) {
		BedPoolHasUnitType bedPoolHasUnitType = null;
		
		if ( existingBedPoolHasUnitType != null ) {
			bedPoolHasUnitType = existingBedPoolHasUnitType;
		} else {
			bedPoolHasUnitType = new BedPoolHasUnitType();
		}
		
		if( bedPoolUnitTypeAsgmBM.getPoolName() != null && !bedPoolUnitTypeAsgmBM.getPoolName().isEmpty() ){
			BedPool bedPool = dataModelManager.getBedPool( bedPoolUnitTypeAsgmBM.getPoolName() );
			bedPoolHasUnitType.setBedPool(bedPool);
		}
		bedPoolHasUnitType.setEffectiveFromDt( bedPoolUnitTypeAsgmBM.getEffectiveFromDt() );
		bedPoolHasUnitType.setEffectiveToDate( bedPoolUnitTypeAsgmBM.getEffectiveToDate() );
		
		if( bedPoolUnitTypeAsgmBM.getUnitType() !=  null && bedPoolUnitTypeAsgmBM.getUnitType().getCode() != null 
														 && !bedPoolUnitTypeAsgmBM.getUnitType().getCode().isEmpty()){
			NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType( bedPoolUnitTypeAsgmBM.getUnitType().getCode() );
			bedPoolHasUnitType.setNursingUnitType(nursingUnitType);
		}
		BedPoolHasUnitTypeId poolHasUnitTypeId = new BedPoolHasUnitTypeId();
		poolHasUnitTypeId.setPoolName( bedPoolUnitTypeAsgmBM.getPoolName() );
		poolHasUnitTypeId.setUnitTypeCd( bedPoolUnitTypeAsgmBM.getUnitType().getCode() );
		bedPoolHasUnitType.setId( poolHasUnitTypeId );
		
		return bedPoolHasUnitType;
	}
	
	
	
	public BedPoolBM convertBedPoolDM2BM( BedPool bedPool ) {
		BedPoolBM bedPoolBM = new BedPoolBM();
		bedPoolBM.setBedPoolName( bedPool.getBedPoolName() );
		bedPoolBM.setPoolDesc( bedPool.getPoolDesc() );
		bedPoolBM.setNumberOfAvailableBeds( bedPool.getNumberOfAvailableBeds() );
		bedPoolBM.setTotalNumberOfBed( bedPool.getTotalNumberOfBed() );
		
		return bedPoolBM;
	}
	
	public BedPool convertBedPoolBM2DM( BedPoolBM bedPoolBM, BedPool existingBedPool ) {
		
		BedPool bedPool = null;
		
		if ( existingBedPool != null ) {
			bedPool = existingBedPool;
		} else {
			bedPool = new BedPool(); 
			bedPool.setBedPoolName( bedPoolBM.getBedPoolName() );
		}

/** 
 *  We don't want to modify the bed count manually
 */		

/*		if ( bedPoolBM.getNumberOfAvailableBeds() != null ) {
			bedPool.setNumberOfAvailableBeds( bedPoolBM.getNumberOfAvailableBeds() );
		}
		
		if ( bedPoolBM.getTotalNumberOfBed() != null ) {
			bedPool.setTotalNumberOfBed( bedPoolBM.getTotalNumberOfBed() );
		}
*/		
		bedPool.setPoolDesc( bedPoolBM.getPoolDesc() );	
		
		return bedPool;
	}
	
	
	/**
	 * This method converts bed master data model object into business model object.
	 * @param bedMaster
	 * @return
	 */
	public BedMasterBM convertBedMasterDM2BM( BedMaster bedMaster ) {
		BedMasterBM bedMasterBM = new BedMasterBM();
		bedMasterBM.setBedNumber( bedMaster.getBedNumber() );
		bedMasterBM.setBedStatus( new CodeAndDescription( bedMaster.getBedStatus().getBedStatusCd(),
				                                          bedMaster.getBedStatus().getDescription() ) );
		bedMasterBM.setBedType( new CodeAndDescription( bedMaster.getBedType().getBedTypeCd(),
														bedMaster.getBedType().getBedTypeDesc() ) );
		
		bedMasterBM.setDailyCharge( bedMaster.getDailyCharge() );
		bedMasterBM.setDepositAmount( bedMaster.getDepositAmount()  );
		bedMasterBM.setDescription( bedMaster.getDescription() );
		bedMasterBM.setFloorNbr( bedMaster.getFloorNbr() );
		bedMasterBM.setHourlyCharge( bedMaster.getHourlyCharge() );
		bedMasterBM.setNursingUnit( new CodeAndDescription(bedMaster.getNursingUnit().getUnitName(),bedMaster.getNursingUnit().getUnitDescription() ));
		bedMasterBM.setSiteName( bedMaster.getSiteName() );
		bedMasterBM.setModifiedBy(bedMaster.getModifiedBy());
		
		Room room = coreDataModelManager.getRoom( bedMaster.getRoomNbr() );
		if( room != null ){
			bedMasterBM.setRoomNbr( new CodeAndDescription( room.getRoomCode() , room.getDescription()) );	
		}
		
		List < BedHasSpecialFeatures> bedHasSpecialFeaturesList = dataModelManager.getBedHasSpecialFeaturesList(bedMaster.getBedNumber());
		
		if(bedHasSpecialFeaturesList != null && !bedHasSpecialFeaturesList.isEmpty()){

			List<BedSpecialFeatureAvailability> specialFeatureAvailabilitylist = new ArrayList<BedSpecialFeatureAvailability>(0);
			
			
			for(BedHasSpecialFeatures bedHasSpecialFeatures : bedHasSpecialFeaturesList){
					BedSpecialFeatureAvailability specialFeatureAvailability= new BedSpecialFeatureAvailability();
					
					BedSpecialFeature bedSpecialFeature = dataModelManager.getBedSpecialFeature(bedHasSpecialFeatures.getId().getFeatureName());
					specialFeatureAvailability.setFeatureName(bedSpecialFeature.getFeatureName());
					specialFeatureAvailability.setDescription(bedSpecialFeature.getDescription());
					
				
					if( new Date().after(bedHasSpecialFeatures.getEffectiveFromDate()) && (bedHasSpecialFeatures.getEffectiveToDate() == null || new Date().before(bedHasSpecialFeatures.getEffectiveToDate()))){
						specialFeatureAvailability.setAvailabilityFlag(Boolean.TRUE);
					}else{
						specialFeatureAvailability.setAvailabilityFlag(Boolean.FALSE);
					}
			
					specialFeatureAvailabilitylist.add(specialFeatureAvailability);	
			}
			
			bedMasterBM.setSpecialFeatureAvailabilityList(specialFeatureAvailabilitylist);
		}
		
		
		
		return bedMasterBM;
	}
	
	/**
	 * This method converts bed mater business model object into data model object.
	 * If an existing BedMaster object has already been supplied then this method 
	 * does not create a new object.
	 * 
	 * @param bedMasterBM
	 * @param existingBedMaster
	 * @return
	 */
	public BedMaster convertBedMasterBM2DM( BedMasterBM bedMasterBM, BedMaster existingBedMaster ) {
		
		BedMaster bedMaster = null;
		
		if ( existingBedMaster != null ) {
			bedMaster = existingBedMaster;
		} else {
			bedMaster = new BedMaster();
			bedMaster.setBedNumber( bedMasterBM.getBedNumber() );
		}
		
		if( bedMasterBM.getBedStatus() != null && bedMasterBM.getBedStatus().getCode() != null 
											   && !bedMasterBM.getBedStatus().getCode().isEmpty() ){
			BedStatus bedStatus = dataModelManager.getBedStatus( bedMasterBM.getBedStatus().getCode() );
			bedMaster.setBedStatus( bedStatus );
		}
		if( bedMasterBM.getBedType() != null && bedMasterBM.getBedType().getCode() != null
											 && !bedMasterBM.getBedType().getCode().isEmpty() ){
			BedType bedType = dataModelManager.getBedType( bedMasterBM.getBedType().getCode() );
			bedMaster.setBedType( bedType );
		}
		bedMaster.setDailyCharge( bedMasterBM.getDailyCharge() );
		bedMaster.setDepositAmount( bedMasterBM.getDepositAmount()  );
		bedMaster.setDescription( bedMasterBM.getDescription() );
		bedMaster.setFloorNbr( bedMasterBM.getFloorNbr() );
		bedMaster.setHourlyCharge( bedMasterBM.getHourlyCharge() );
		bedMaster.setModifiedBy(bedMasterBM.getModifiedBy());
		
		if ( bedMasterBM.getNursingUnit() != null && bedMasterBM.getNursingUnit().getCode() != null ) {
			NursingUnit nursingUnit = dataModelManager.getNursingUnit( bedMasterBM.getNursingUnit().getCode() );
			bedMaster.setNursingUnit( nursingUnit );
		} 
		
		bedMaster.setRoomNbr( bedMasterBM.getRoomNbr().getCode() );
		bedMaster.setSiteName( bedMasterBM.getSiteName() );
		
		if( bedMaster.getFloorNbr() == null || bedMaster.getFloorNbr().equals("")){
			bedMaster.setFloorNbr(BedManagementConstants.BED_DEFAULT_FLOOR);
		}
		
		return bedMaster;
	}
	
	
	public ClaimSponsor convertClaimSponsorBM2DM( SponsorDetailsBM sponsorDetailsBM,ClaimSponsor existingClaimSponsor){
		
		ClaimSponsor claimSponsor = null;
		
		if(existingClaimSponsor != null){
			claimSponsor = existingClaimSponsor;
		}else{
			
			claimSponsor = new ClaimSponsor();
			claimSponsor.setSponsorsName( sponsorDetailsBM.getSponsorsName() );
		}
		 
		claimSponsor.setAccountNumber( sponsorDetailsBM.getAccountNumber() );
		
		if( sponsorDetailsBM.getCreditClass() != null && sponsorDetailsBM.getCreditClass().getCode() != null 
													  && !sponsorDetailsBM.getCreditClass().getCode().isEmpty() ){
			claimSponsor.setCreditClass( dataModelManager.getCreditClass(sponsorDetailsBM.getCreditClass().getCode()) );
		}
		claimSponsor.setSponsorDesc( sponsorDetailsBM.getSponsorDesc() );
		
		if ( sponsorDetailsBM.getSponsorType() != null && sponsorDetailsBM.getSponsorType().getCode() != null 
													   && !sponsorDetailsBM.getSponsorType().getCode().isEmpty()){
			claimSponsor.setSponsorType( dataModelManager.getSposorType(sponsorDetailsBM.getSponsorType().getCode()) );
		}

		return claimSponsor;
	}
	
	public SponsorDetailsBM convertSponsorDetailsDM2BM( ClaimSponsor claimSponsor ){
		
		SponsorDetailsBM  sponsorDetailsBM = new SponsorDetailsBM();
		
		sponsorDetailsBM.setAccountNumber( claimSponsor.getAccountNumber() );
		
		
		CreditClass creditClass = claimSponsor.getCreditClass();
		if( creditClass != null){
			
			sponsorDetailsBM.setCreditClass( new CodeAndDescription( creditClass.getCreditClassCd(), creditClass.getDescription() ));
		}
		
//		sponsorDetailsBM.setContactDetailsBM(contactDetailsBM);  // Contact details should be set by ManagerImpl
		
		sponsorDetailsBM.setModifiedBy( claimSponsor.getLastModifiedBy() );
		sponsorDetailsBM.setSponsorDesc( claimSponsor.getSponsorDesc() );
		sponsorDetailsBM.setSponsorsName( claimSponsor.getSponsorsName() );
		
		SponsorType sponsorType = claimSponsor.getSponsorType();
		if( sponsorType != null ){
		
			sponsorDetailsBM.setSponsorType( new CodeAndDescription( sponsorType.getSponsorTypeCd() , sponsorType.getSponsorDesc()) );
		}
		
       return sponsorDetailsBM;
		
	}
	

	public Insurer convertInsurerBM2DM( InsurerBM insurerBM , Insurer existingInsurer ){
		
		Insurer insurer = null;
		if( existingInsurer != null){
			insurer = existingInsurer;
		}else{
			insurer = new Insurer();
			insurer.setInsurerName( insurerBM.getInsurerName() );	
		}
		
		insurer.setInsurerDesc( insurerBM.getInsurerDesc() );
		insurer.setCreatedBy( insurerBM.getCreatedBy() );
//		insurer.setCreatedDtm( insurerBM.getCreatedDate() );
		insurer.setLastModifiedBy( insurerBM.getModifiedBy() );
		insurer.setModifiedDtm( insurerBM.getModifiedDate() );
		
		return insurer;
		
	}
	
	public InsurerBM convertInsurerDM2BM( Insurer insurer ){
		InsurerBM insurerBM =  new InsurerBM();
		
		insurerBM.setInsurerName( insurer.getInsurerName() );
		insurerBM.setInsurerDesc( insurer.getInsurerDesc() );
		insurerBM.setCreatedBy( insurer.getCreatedBy() );
		insurerBM.setCreatedDate( insurer.getCreatedDtm() );
		insurerBM.setModifiedBy( insurer.getLastModifiedBy() );
		insurerBM.setModifiedDate( insurer.getModifiedDtm() );
		
		return insurerBM;
	}
	
	public SponsorInsurerAssociationBM convertSponsorInsurerAssociationDM2BM( SponsorInsurerAssociation sponsorInsurerAssociation ){
		SponsorInsurerAssociationBM associationBM = new SponsorInsurerAssociationBM();
		associationBM.setSponsorName( sponsorInsurerAssociation.getId().getSponsorName());
		associationBM.setInsurerName( sponsorInsurerAssociation.getId().getInsurerName() );
		associationBM.setEffectiveFromDate( sponsorInsurerAssociation.getEffectiveFromDt() );
		associationBM.setEffectiveToDate( sponsorInsurerAssociation.getEffectiveToDt() );
		associationBM.setCreatedBy( sponsorInsurerAssociation.getCreatedBy() );
		associationBM.setCreatedDate( sponsorInsurerAssociation.getCreatedDtm() );
		
		return associationBM;
	}
	public AdmissionClaimRequest convertClaimRequestBM2DM( ClaimRequestBM claimRequestBM ,AdmissionClaimRequest existingClaimRequest ){
		
		AdmissionClaimRequest admissionClaimRequest = null;
		
		if( existingClaimRequest != null ){
			admissionClaimRequest = existingClaimRequest;
		}else{
			admissionClaimRequest = new AdmissionClaimRequest();
			admissionClaimRequest.setRequestSequenceNbr( claimRequestBM.getRequestSequenceNbr() );
		}
			
		
		if( claimRequestBM.getAdmissionReqNbr() != null ){
			Admission  admission = dataModelManager.getAdmission( claimRequestBM.getAdmissionReqNbr() );
			admissionClaimRequest.setAdmission( admission );
		}
		admissionClaimRequest.setApprovalDtm( claimRequestBM.getApprovalDate() );
		admissionClaimRequest.setApprovalThrough( claimRequestBM.getApprovalThrough() );
		admissionClaimRequest.setApprovedAmount( claimRequestBM.getApprovedAmount() );
		admissionClaimRequest.setBillNbr( claimRequestBM.getBillNbr() );
		
		if( claimRequestBM.getSponsorName() != null && !claimRequestBM.getSponsorName().isEmpty()){
			
			ClaimSponsor claimSponsor = dataModelManager.getClaimSponsor( claimRequestBM.getSponsorName() );
			admissionClaimRequest.setClaimSponsor(claimSponsor);
		}
		
		admissionClaimRequest.setClaimSubsequenceNbr( claimRequestBM.getClaimSubsequenceNbr() );
		admissionClaimRequest.setCreatedBy( claimRequestBM.getCreatedBy() );
		admissionClaimRequest.setFinalClaimedAmount( claimRequestBM.getFinalClaimedAmount() );
		
		if( claimRequestBM.getPlanCode() != null && !claimRequestBM.getPlanCode().isEmpty()){
			
			InsurancePlans insurancePlans = dataModelManager.getInsurancePlan( claimRequestBM.getPlanCode()  );
			admissionClaimRequest.setInsurancePlans(insurancePlans);
		}
		admissionClaimRequest.setPatientAmount( claimRequestBM.getPatientAmount() );
		
		if( claimRequestBM.getInsurerName() != null && !claimRequestBM.getInsurerName().isEmpty() ){
			Insurer insurer = dataModelManager.getInsurer( claimRequestBM.getInsurerName() );
			admissionClaimRequest.setInsurer(insurer);
			
		}
		
		admissionClaimRequest.setPolicyEffectiveFromDt( claimRequestBM.getPolicyEffectiveFromDt() );
		admissionClaimRequest.setPolicyEffectiveToDt( claimRequestBM.getPolicyEffectiveToDt() );
		admissionClaimRequest.setPolicyNbr( claimRequestBM.getPolicyNbr() );
		admissionClaimRequest.setPolicyHolderName( claimRequestBM.getPolicyHolderName() );
		admissionClaimRequest.setPreferenceSequenceNbr( claimRequestBM.getPreferenceSequenceNbr() );
		admissionClaimRequest.setRequestDtm( claimRequestBM.getRequestDtm() );
		admissionClaimRequest.setRequestedAmount( claimRequestBM.getRequestedAmount() );
		
		if( claimRequestBM.getClaimStatusCd() != null && claimRequestBM.getClaimStatusCd().getCode() != null
													  && !claimRequestBM.getClaimStatusCd().getCode().isEmpty() ){
			SponsorClaimStatus  sponsorClaimStatus = dataModelManager.getSponsorClaimStatus(claimRequestBM.getClaimStatusCd().getCode());
			admissionClaimRequest.setSponsorClaimStatus(sponsorClaimStatus);
		}
		
		return admissionClaimRequest;	
		}
	
	
	public ClaimRequestBM convertAdmissionClaimRequestDM2BM( AdmissionClaimRequest admissionClaimRequest){
		
			ClaimRequestBM claimRequestBM = new ClaimRequestBM();
			
			claimRequestBM.setAdmissionReqNbr(admissionClaimRequest.getAdmission().getAdmissionReqNbr());
			claimRequestBM.setApprovalDate(admissionClaimRequest.getApprovalDtm());
			claimRequestBM.setApprovalThrough(admissionClaimRequest.getApprovalThrough());
			claimRequestBM.setApprovedAmount(admissionClaimRequest.getApprovedAmount());
			claimRequestBM.setBillNbr(admissionClaimRequest.getBillNbr());
			
			SponsorClaimStatus claimStatus = admissionClaimRequest.getSponsorClaimStatus();
			
			if( claimStatus != null){
				claimRequestBM.setClaimStatusCd( new CodeAndDescription(claimStatus.getClaimStatusCd(),claimStatus.getClaimStatusDesc()));
			}
			
			claimRequestBM.setClaimSubsequenceNbr(admissionClaimRequest.getClaimSubsequenceNbr());
			claimRequestBM.setCreatedBy(admissionClaimRequest.getCreatedBy());
			claimRequestBM.setCreateDtm(admissionClaimRequest.getCreatedDtm());
			claimRequestBM.setFinalClaimedAmount(admissionClaimRequest.getFinalClaimedAmount());
			claimRequestBM.setLastModifiedDtm(admissionClaimRequest.getLastModifiedDtm());
			claimRequestBM.setModifiedBy(admissionClaimRequest.getModifiedBy());
			
			claimRequestBM.setPlanCode(admissionClaimRequest.getInsurancePlans().getPlanCd());
			claimRequestBM.setPlanName(admissionClaimRequest.getInsurancePlans().getPlanName());
			
			claimRequestBM.setPolicyEffectiveFromDt(admissionClaimRequest.getPolicyEffectiveFromDt());
			claimRequestBM.setPolicyEffectiveToDt(admissionClaimRequest.getPolicyEffectiveToDt());
			claimRequestBM.setPolicyNbr(admissionClaimRequest.getPolicyNbr());
			claimRequestBM.setPreferenceSequenceNbr(admissionClaimRequest.getPreferenceSequenceNbr());
			claimRequestBM.setRequestDtm(admissionClaimRequest.getRequestDtm());
			claimRequestBM.setRequestedAmount(admissionClaimRequest.getRequestedAmount());
			claimRequestBM.setRequestSequenceNbr(admissionClaimRequest.getRequestSequenceNbr());
			
			ClaimSponsor claimSponsor = admissionClaimRequest.getClaimSponsor();
			if( claimSponsor != null ){
				claimRequestBM.setSponsorName( claimSponsor.getSponsorsName());
				claimRequestBM.setSponsorDesc( claimSponsor.getSponsorDesc());
			}
			
			Insurer insurer = admissionClaimRequest.getInsurer();
			if( insurer != null ){
				claimRequestBM.setInsurerName( admissionClaimRequest.getInsurer().getInsurerName() );
			}
			claimRequestBM.setPolicyHolderName( admissionClaimRequest.getPolicyHolderName() );
			claimRequestBM.setPatientAmount( admissionClaimRequest.getPatientAmount() );
		return claimRequestBM;
	}
	
	public InsurancePlans convertInsurancePlanBM2DM( InsurancePlanBM insurancePlanBM , InsurancePlans existingInsurancePlans){
		
		InsurancePlans insurancePlans = null;
		
		if( existingInsurancePlans != null){
			insurancePlans = existingInsurancePlans;
		}else{
			
			 insurancePlans = new InsurancePlans();
			 insurancePlans.setPlanCd(insurancePlanBM.getPlanCode());
		}
		insurancePlans.setPlanName(insurancePlanBM.getPlanName());
		
		if( insurancePlanBM.getInsurerName() != null && !insurancePlanBM.getInsurerName().isEmpty() ){
		
			Insurer insurer = dataModelManager.getInsurer( insurancePlanBM.getInsurerName() );
			insurancePlans.setInsurer( insurer );
		}
		
		insurancePlans.setValidFromDt(insurancePlanBM.getValidFromDt());
		insurancePlans.setValidToDt(insurancePlanBM.getValidToDt());
		insurancePlans.setTotalCoverageAmt(insurancePlanBM.getTotalCoverageAmt());
		insurancePlans.setPercentAbsInd(insurancePlanBM.getPercentAbsInd());
		
		
		return insurancePlans;
	}
	
	
	public InsurancePlanBM convertInsurancePlansDM2BM( InsurancePlans insurancePlans ){
		
		InsurancePlanBM insurancePlanBM = new InsurancePlanBM();
		
		insurancePlanBM.setPlanCode( insurancePlans.getPlanCd() );
		insurancePlanBM.setPlanName( insurancePlans.getPlanName() );
		insurancePlanBM.setInsurerName(insurancePlans.getInsurer().getInsurerName() );
		insurancePlanBM.setTotalCoverageAmt( insurancePlans.getTotalCoverageAmt() );
		insurancePlanBM.setPercentAbsInd( insurancePlans.getPercentAbsInd() );
		insurancePlanBM.setValidFromDt( insurancePlans.getValidFromDt() );
		insurancePlanBM.setValidToDt( insurancePlans.getValidToDt() );
		insurancePlanBM.setLastModifiedBy( insurancePlans.getModifiedBy() );
		insurancePlanBM.setLastModifiedDt( insurancePlans.getLastModifiedDtm() );
		insurancePlanBM.setCreatedBy( insurancePlans.getCreatedBy() );
		insurancePlanBM.setCreateDtm( insurancePlans.getCreatedDtm() );
		return insurancePlanBM;
	}
	
	public ContactDetails convertContactDetalisBM2DM ( ContactDetailsBM contactDetailsBM, ContactDetails existingContactDetails){
		
		ContactDetails contactDetails = null;
		
		if(existingContactDetails != null){
			
			contactDetails = existingContactDetails;
		}else{
			
			contactDetails = new ContactDetails();
			contactDetails.setContactCode(contactDetailsBM.getContactDetailsCode() );
		}
				
		contactDetails.setFirstName( contactDetailsBM.getFirstName() );
		contactDetails.setMiddleName( contactDetailsBM.getMiddleName() );
		contactDetails.setLastName( contactDetailsBM.getLastName() );
		contactDetails.setAddressLine1( contactDetailsBM.getHouseNumber() );
		contactDetails.setAddressLine2( contactDetailsBM.getStreet() );
		contactDetails.setCity( contactDetailsBM.getCity() );
		contactDetails.setPincode( contactDetailsBM.getPincode() );
		contactDetails.setStayDuration( contactDetailsBM.getStayDuration() );
		contactDetails.setContactNumber( contactDetailsBM.getPhoneNumber() );
		contactDetails.setMobileNumber( contactDetailsBM.getMobileNumber() );
		contactDetails.setEmail( contactDetailsBM.getEmail() );
		contactDetails.setFaxNumber( contactDetailsBM.getFaxNumber() );
		if( contactDetailsBM.getSalutation() != null &&  contactDetailsBM.getSalutation().getCode().length() > 0) {
			Saluation saluation = new Saluation();
			saluation.setSaluationCode( contactDetailsBM.getSalutation().getCode() );
			saluation.setDescription( contactDetailsBM.getSalutation().getDescription() );
			contactDetails.setSaluation( saluation );
		}
		if( contactDetailsBM.getGender() != null &&  contactDetailsBM.getGender().getCode().length() > 0 ) {
			Gender gender = new Gender();
			gender.setGenderCode( contactDetailsBM.getGender().getCode() );
			gender.setDescription( contactDetailsBM.getGender().getDescription() );
			contactDetails.setGender( gender );
		}
		if( contactDetailsBM.getState() != null && contactDetailsBM.getCountry() != null &&
				 contactDetailsBM.getState().getCode().length() > 0 && 
				 contactDetailsBM.getCountry().getCode().length() > 0) {
			contactDetails.setStateCode( contactDetailsBM.getState().getCode() );
			
		}
		if( contactDetailsBM.getCountry() != null && 
				 contactDetailsBM.getCountry().getCode().length() > 0 ) {
			contactDetails.setCountryCode(contactDetailsBM.getCountry().getCode());
		}
		
		
		return contactDetails;
	}
	
	public ContactDetailsBM convertContactDetailsDM2BM(ContactDetails contactDetailsDM) {
		ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
		
		contactDetailsBM.setContactDetailsCode( contactDetailsDM.getContactCode() );
		if( contactDetailsDM.getSaluation() != null ) {
			contactDetailsBM.setSalutation( new CodeAndDescription( contactDetailsDM.getSaluation().getSaluationCode(), contactDetailsDM.getSaluation().getDescription() ) );
		}
		contactDetailsBM.setFirstName( contactDetailsDM.getFirstName() );
		contactDetailsBM.setMiddleName( contactDetailsDM.getMiddleName() );
		contactDetailsBM.setLastName( contactDetailsDM.getLastName() );
		if( contactDetailsDM.getGender() != null ) {
			contactDetailsBM.setGender( new CodeAndDescription( contactDetailsDM.getGender().getGenderCode(), contactDetailsDM.getGender().getDescription() ) );
		}
		contactDetailsBM.setHouseNumber( contactDetailsDM.getAddressLine1() );
		contactDetailsBM.setStreet( contactDetailsDM.getAddressLine2() );
		contactDetailsBM.setCity( contactDetailsDM.getCity() );
		
		if(contactDetailsDM.getCountryCode()!=null && !contactDetailsDM.getCountryCode().equals("")) {
			Country country =  coreDataModelManager.getCountrybyCode( contactDetailsDM.getCountryCode());
			contactDetailsBM.setCountry( new CodeAndDescription( country.getCountryCode(), country.getDescription() ) );
		}
		if(contactDetailsDM.getStateCode()!=null && !contactDetailsDM.getStateCode().equals("") &&
		   contactDetailsDM.getCountryCode()!=null && !contactDetailsDM.getCountryCode().equals("")) {
			StateId stateId = new StateId();
			stateId.setCountryCode(contactDetailsDM.getCountryCode());
			stateId.setStateCode(contactDetailsDM.getStateCode());
			State state = coreDataModelManager.getStateByCode( stateId );
			contactDetailsBM.setState( new CodeAndDescription(state.getId().getStateCode(), state.getDescription() ) ); 	
		}
		
		
		contactDetailsBM.setPincode( contactDetailsDM.getPincode() );
		contactDetailsBM.setPhoneNumber( contactDetailsDM.getContactNumber() );
		contactDetailsBM.setMobileNumber( contactDetailsDM.getMobileNumber() );
		contactDetailsBM.setFaxNumber( contactDetailsDM.getFaxNumber() );
		contactDetailsBM.setEmail( contactDetailsDM.getEmail() );
		
		
		return contactDetailsBM;
	
	}
	
	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}
	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}
	public void setOrderAttributeValueDAO(
			OrderAttributeValueDAO orderAttributeValueDAO) {
		this.orderAttributeValueDAO = orderAttributeValueDAO;
	}
	
}
