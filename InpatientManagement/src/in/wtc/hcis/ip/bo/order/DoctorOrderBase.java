/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderActivity;
import com.wtc.hcis.ip.da.DoctorOrderActivityDAO;
import com.wtc.hcis.ip.da.DoctorOrderActivityId;
import com.wtc.hcis.ip.da.DoctorOrderDetails;
import com.wtc.hcis.ip.da.DoctorOrderDetailsDAO;
import com.wtc.hcis.ip.da.DoctorOrderDetailsId;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetails;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetailsDAO;
import com.wtc.hcis.ip.da.extn.DoctorOrderDAOExtn;

/**
 * @author Alok Ranjan
 *
 */
public class DoctorOrderBase {
	
	protected IPDataModelConverter converter;
	protected IPDataModelManager dataModelManager;
	protected DataModelManager baseDataModelManager;
	protected static ResourceBundle doctorOrderConfig = ResourceBundle.getBundle("in.wtc.hcis.ip.common.config.DoctorOrder", Locale.getDefault());
	
	protected DoctorOrderDAOExtn doctorOrderDAO;
	protected DoctorOrderDetailsDAO doctorOrderDetailsDAO;
	protected DoctorOrderActivityDAO doctorOrderActivityDAO;
	protected DoctorOrderTemplateDetailsDAO doctorOrderTemplateDetailsDAO;
	
	/**
	 * This method creates a doctor order record for a given doctor order business model.
	 *  
	 * @param doctorOrderBM
	 * @return
	 */
	public DoctorOrder createDoctorOrder( DoctorOrderBM doctorOrderBM ) {
		
		
		if ( doctorOrderBM.getDoctorOrderType() != null && 
					!doctorOrderBM.getDoctorOrderType().getCode().equals(DoctorOrderConstants.ORDER_TYPE_GENERAL)){
			
			Map<String,String> OrderAttributesMap = doctorOrderBM.getOrderSpecificAttributes();
			
		}
		
		DoctorOrder doctorOrder = converter.convertDoctorOrderBM2DM(doctorOrderBM, null );
		doctorOrder.setCreatedBy( doctorOrderBM.getCreatedBy() ); 
		doctorOrder.setOrderCreationDtm( new Date());
		doctorOrderDAO.save(doctorOrder);
		
		List<DoctorOrderDetailsBM>doctorOrderDetailsBMList = doctorOrderBM.getDoctorOrderDetailsList();
		
		if ( doctorOrderDetailsBMList != null && !doctorOrderDetailsBMList.isEmpty() ) {
			for ( DoctorOrderDetailsBM doctorOrderDetailsBM : doctorOrderDetailsBMList ) {
				doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrder.getDoctorOrderNbr() );
				
				if ( doctorOrderDetailsBM.getActionStatus() == null || 
					 doctorOrderDetailsBM.getActionStatus().getCode() == null  ||
					 doctorOrderDetailsBM.getActionStatus().getCode().length() == 0  ) {
					
					doctorOrderDetailsBM.setActionStatus( new CodeAndDescription( DoctorOrderConstants.ACTION_STATUS_CREATED, "" ) ); 
				}
				
				DoctorOrderDetails doctorOrderDetails = converter.convertDoctorOrderDetailsBM2DM(doctorOrderDetailsBM, null );
				doctorOrderDetailsDAO.save( doctorOrderDetails );
			}
		}
		
		return doctorOrder;
	}
	
	/**
	 * 
	 * @param orderNumber
	 * @param orderStatusCd
	 */
	public void createDoctorOrderActivity( DoctorOrder doctorOrder, 
			                               String activityTypeCd, 
			                               String remarks ) {
		DoctorOrderActivity doctorOrderActivity = new DoctorOrderActivity();
		
		ActivityType activityType = dataModelManager.getActivityType( activityTypeCd );
		doctorOrderActivity.setActivityType(activityType);
		doctorOrderActivity.setCreatedBy( doctorOrder.getCreatedBy()); //TODO: or to set modified by ??
		doctorOrderActivity.setDoctorOrder(doctorOrder);
		
		DoctorOrderActivityId doctorOrderActivityId = new DoctorOrderActivityId();
		doctorOrderActivityId.setActivityDtm( new Date() );
		doctorOrderActivityId.setActivityTypeCd( activityTypeCd );
		doctorOrderActivityId.setDoctorOrderNumber( doctorOrder.getDoctorOrderNbr() );
		doctorOrderActivity.setId( doctorOrderActivityId );
		
		doctorOrderActivity.setDoctorOrderStatus( doctorOrder.getDoctorOrderStatus() );
		doctorOrderActivity.setRemarks( remarks );
		
		doctorOrderActivityDAO.save( doctorOrderActivity );
	}
	
	public DoctorOrder createDoctorOrder( DoctorOrderTemplate doctorOrderTemplate,  Integer doctorId, Integer patientId  ) {
		DoctorOrder doctorOrder = new DoctorOrder();
		doctorOrder.setCreatedBy("ordermanager"); // TODO : change this name
		
		DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_CREATED );
		doctorOrder.setDoctorOrderStatus( doctorOrderStatus );
		
		doctorOrder.setDoctorOrderTemplate(doctorOrderTemplate);
		doctorOrder.setDoctorOrderType( doctorOrderTemplate.getDoctorOrderType() );
		doctorOrder.setDoctorId(doctorId);
		doctorOrder.setPatientId(patientId);
		doctorOrder.setOrderCreationDtm( new Date() );
		doctorOrder.setOrderDesc( doctorOrderTemplate.getTemplateDesc() );
		doctorOrder.setOrderDictation( DoctorOrderConstants.ORDER_DICTATION_TEMPLATE );
		
		doctorOrderDAO.save( doctorOrder );
		
		List<DoctorOrderTemplateDetails>doctorOrderTemplateDetailsList = doctorOrderTemplateDetailsDAO.findByProperty( "id.templateId", doctorOrderTemplate.getTemplateId() );
		
		if ( doctorOrderTemplateDetailsList != null && !doctorOrderTemplateDetailsList.isEmpty() ) {
			for ( DoctorOrderTemplateDetails doctorOrderTemplateDetails : doctorOrderTemplateDetailsList ) {
				DoctorOrderDetails doctorOrderDetails = new DoctorOrderDetails();
				
				doctorOrderDetails.setActionDesc( doctorOrderTemplateDetails.getActionDesc() );
				
				ActionStatus actionStatus = dataModelManager.getActionStatus( DoctorOrderConstants.ACTION_STATUS_CREATED );
				doctorOrderDetails.setActionStatus( actionStatus );
				doctorOrderDetails.setDoctorOrder( doctorOrder );
				
				DoctorOrderDetailsId doctorOrderDetailsId = new DoctorOrderDetailsId();
				doctorOrderDetailsId.setOrderNbr( doctorOrder.getDoctorOrderNbr() );
				doctorOrderDetailsId.setSequenceNbr( doctorOrderTemplateDetails.getId().getSequenceNbr() );
				doctorOrderDetailsId.setSubSequenceNbr( doctorOrderTemplateDetails.getId().getSubSequenceNbr() );
				doctorOrderDetails.setId( doctorOrderDetailsId );
				
				if ( doctorOrderTemplateDetails.getServiceId() != null && 
					 doctorOrderTemplateDetails.getServiceId().length() > 0  ) {
					Service service = dataModelManager.getService( doctorOrderTemplateDetails.getServiceId() );
					doctorOrderDetails.setResponsibleDepartmentId( service.getDepartment().getDepartmentCode() );
					doctorOrderDetails.setServiceId( service.getServiceCode() );
				} else {
					doctorOrderDetails.setResponsibleDepartmentId( doctorOrderTemplateDetails.getResponsibleDepartmentId() );
				}
				
				doctorOrderDetailsDAO.save(doctorOrderDetails);
			}
		}
		
		return doctorOrder;
	}
	
	/**
	 * This method modifies an existing doctor oder.
	 * This method does not allow deletion of any order details. However, it does allow doctor to 
	 * add new details in the doctor order.
	 * 
	 * This method throws an exception while attempting to modify those 'orders' or 'order details' which has 
	 * already been marked as 'COMPLEATED'.
	 * 
	 * Create doctor order activity for any change in order status.
	 * 
	 * @param existingDoctorOrder
	 * @param doctorOrderBM
	 * @return
	 */
	public DoctorOrder modifyDoctorOrder( DoctorOrder existingDoctorOrder, DoctorOrderBM doctorOrderBM ) {
		
		if( existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd().equals(DoctorOrderConstants.ORDER_STATUS_COMPLETED )){
			
			Fault fault = new Fault( "Can't Modify : " + ApplicationErrors.DOCTOR_ORDER_ALREADY_COMPLETED );
			
			throw new HCISException( fault.getFaultMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}else{
				
				DoctorOrder doctorOrder = converter.convertDoctorOrderBM2DM( doctorOrderBM, existingDoctorOrder );
				doctorOrder.setModifiedBy( doctorOrderBM.getModifiedBy() );
				doctorOrder.setLastModifiedTm( new Date() );
				
				doctorOrderDAO.attachDirty( doctorOrder );
				
				List<DoctorOrderDetailsBM>doctorOrderDetailsBMList = doctorOrderBM.getDoctorOrderDetailsList();
				
				if ( doctorOrderDetailsBMList != null && !doctorOrderDetailsBMList.isEmpty() ) {
					for ( DoctorOrderDetailsBM doctorOrderDetailsBM : doctorOrderDetailsBMList ) {
						
						DoctorOrderDetailsId doctorOrderDetailsId = new DoctorOrderDetailsId();
						doctorOrderDetailsId.setOrderNbr( doctorOrder.getDoctorOrderNbr() );
						doctorOrderDetailsId.setSequenceNbr( doctorOrderDetailsBM.getSequenceNbr() );
						doctorOrderDetailsId.setSubSequenceNbr( doctorOrderDetailsBM.getSubSequenceNbr() );
						
						DoctorOrderDetails doctorOrderDetails = doctorOrderDetailsDAO.findById( doctorOrderDetailsId );
						
						if ( doctorOrderDetails != null ) {
							
							if( doctorOrderDetails.getActionStatus().getActionStatusCd().equals(DoctorOrderConstants.ACTION_STATUS_COMPLETED)){
								
								Fault fault = new Fault( "Can't Modify : " + ApplicationErrors.DOCTOR_ORDER_ALREADY_COMPLETED );
								throw new HCISException( fault.getFaultMessage(),
														 fault.getFaultCode(),
														 fault.getFaultType() );
								
							}else{
								doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrderBM.getDoctorOrderNbr() );
								doctorOrderDetails = converter.convertDoctorOrderDetailsBM2DM(doctorOrderDetailsBM, doctorOrderDetails );
								doctorOrderDetailsDAO.attachDirty( doctorOrderDetails );
							}
						} else {
							doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrderBM.getDoctorOrderNbr() );
							doctorOrderDetails = converter.convertDoctorOrderDetailsBM2DM(doctorOrderDetailsBM, null );
							doctorOrderDetailsDAO.save(doctorOrderDetails);
						}
					}
				}
				
				String currentStatusOfOrder = existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd();
				String newStatusOfOrder = doctorOrder.getDoctorOrderStatus().getOrderStatusCd();
				
				if ( !newStatusOfOrder.equals( currentStatusOfOrder ) ) {
					//
					// Assumption is that -- all the activity type related to ORDER will have "ORDER_" 
					// preceded in its name. 
					//
					this.createDoctorOrderActivity( doctorOrder, 
				                                    "ORDER_" + newStatusOfOrder,
				                                    " Order status changed from " + currentStatusOfOrder + " to status = " + newStatusOfOrder );
				}
				
				return doctorOrder;
		}
	}
	
	public DoctorOrder approveDoctorOrder( DoctorOrder doctorOrder, 
			                               Integer doctorId, 
			                               String approvedByPersonId ) {
		 
		
		Doctor doctor = baseDataModelManager.getDoctor(doctorId);
		
//		doctor.getDoctorHasOrderAuthorizationPermission();
		// TODO : Check if this doctor has a role to authorize pending order or not
		// At this moment -- we do not have role management integrated, however whenever
		// we do that, this part of code must be modified.
		//
		DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_APPROVED );
		doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
		doctorOrder.setLastModifiedTm( new Date() );
		doctorOrder.setModifiedBy( approvedByPersonId );
		doctorOrderDAO.attachDirty( doctorOrder );
		
		this.createDoctorOrderActivity( doctorOrder, 
				                        DoctorOrderConstants.ACTIVITY_TYPE_ORDER_APPROVED, 
				                        null );
		
		return doctorOrder;
	}
	
	public DoctorOrder disapproveDoctorOrder( DoctorOrder doctorOrder,
			                                  Integer doctorId,
			                                  String disapprovalRemarks,
			                                  String disApprovedByPersonId ) {
		Doctor doctor = baseDataModelManager.getDoctor(doctorId);
		
//		doctor.getDoctorHasOrderAuthorizationPermission();
		// TODO : Check if this doctor has a role to authorize pending order or not
		// At this moment -- we do not have role management integrated, however whenever
		// we do that, this part of code must be modified.
		//
		DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_DISAPPROVED );
		doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
		doctorOrder.setLastModifiedTm( new Date() );
		doctorOrder.setModifiedBy( disApprovedByPersonId );
		doctorOrderDAO.attachDirty( doctorOrder );
		
		this.createDoctorOrderActivity( doctorOrder, 
				                        DoctorOrderConstants.ACTIVITY_TYPE_ORDER_DISAPPROVED, 
				                        disapprovalRemarks );
		
		return doctorOrder;
	}

	public void setConverter(IPDataModelConverter converter) {
		this.converter = converter;
	}

	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setBaseDataModelManager(DataModelManager baseDataModelManager) {
		this.baseDataModelManager = baseDataModelManager;
	}

	public void setDoctorOrderDAO(DoctorOrderDAOExtn doctorOrderDAO) {
		this.doctorOrderDAO = doctorOrderDAO;
	}

	public void setDoctorOrderDetailsDAO(DoctorOrderDetailsDAO doctorOrderDetailsDAO) {
		this.doctorOrderDetailsDAO = doctorOrderDetailsDAO;
	}

	public void setDoctorOrderActivityDAO(
			DoctorOrderActivityDAO doctorOrderActivityDAO) {
		this.doctorOrderActivityDAO = doctorOrderActivityDAO;
	}

	public void setDoctorOrderTemplateDetailsDAO(
			DoctorOrderTemplateDetailsDAO doctorOrderTemplateDetailsDAO) {
		this.doctorOrderTemplateDetailsDAO = doctorOrderTemplateDetailsDAO;
	}
	
	
}
