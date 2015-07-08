/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bm.ip.DoctorOrderGroupBM;
import in.wtc.hcis.bm.ip.OrderGroupTemplateAssociationBM;
import in.wtc.hcis.bm.ip.OrderTemplateBM;
import in.wtc.hcis.bm.ip.OrderTemplateDetailsBM;
import in.wtc.hcis.bm.ip.OrderTypeAttributeAssociationBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.configuration.ConfigurationManager;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.constants.ApplicationEntities;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.bo.constants.ServicesConstants;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.bed.BedManager;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataAccessException;

import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceStatus;
import com.wtc.hcis.da.extn.ServiceDAOExtn;
import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionEntryPoint;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.Attribute;
import com.wtc.hcis.ip.da.DischargeActivity;
import com.wtc.hcis.ip.da.DischargeActivityDAO;
import com.wtc.hcis.ip.da.DischargeActivityId;
import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderActivity;
import com.wtc.hcis.ip.da.DoctorOrderActivityDAO;
import com.wtc.hcis.ip.da.DoctorOrderActivityId;
import com.wtc.hcis.ip.da.DoctorOrderDetails;
import com.wtc.hcis.ip.da.DoctorOrderDetailsDAO;
import com.wtc.hcis.ip.da.DoctorOrderDetailsId;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplate;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplateDAO;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetails;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetailsDAO;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.DoctorOrderTypeDAO;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.OrderAttributeValue;
import com.wtc.hcis.ip.da.OrderAttributeValueDAO;
import com.wtc.hcis.ip.da.OrderAttributeValueId;
import com.wtc.hcis.ip.da.OrderTypeHasAttributes;
import com.wtc.hcis.ip.da.OrderTypeHasAttributesDAO;
import com.wtc.hcis.ip.da.extn.AdmissionDAOExtn;
import com.wtc.hcis.ip.da.extn.DischargeOrderDAOExtn;
import com.wtc.hcis.ip.da.extn.DoctorOrderDAOExtn;
import com.wtc.hcis.ip.da.extn.DoctorOrderGroupDAOExtn;
import com.wtc.hcis.ip.da.extn.DoctorOrderTemplateDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class OrderManagerImpl implements OrderManager,ApplicationContextAware {

	private IPDataModelConverter converter;
	private IPDataModelManager dataModelManager;
	private DataModelManager baseDataModelManager;
	private static ResourceBundle doctorOrderConfig = ResourceBundle.getBundle("in.wtc.hcis.ip.common.config.DoctorOrder", Locale.getDefault());
	
	private DoctorOrderTypeDAO doctorOrderTypeDAO;
	private DoctorOrderTemplateDAOExtn doctorOrderTemplateDAO;
	private DoctorOrderGroupDAOExtn doctorOrderGroupDAO;
	private DoctorOrderGroupHasTemplateDAO doctorOrderGroupHasTemplateDAO;
	private DoctorOrderDetailsDAO doctorOrderDetailsDAO;
	private ServiceDAOExtn serviceDAO;
	private DoctorOrderDAOExtn doctorOrderDAO;
	private DoctorOrderTemplateDetailsDAO doctorOrderTemplateDetailsDAO;
	private PatientManager patientManager;
	private DoctorOrderActivityDAO doctorOrderActivityDAO;
	private OrderTypeHasAttributesDAO orderTypeHasAttributesDAO;
	private OrderAttributeValueDAO orderAttributeValueDAO;
	private AdmissionDAOExtn admissionDAO;
	private ServiceManager serviceManager;
	private ApplicationContext applicationContext;
	private DischargeOrderDAOExtn dischargeOrderDAO; 
	private DischargeActivityDAO dischargeActivityDAO;

	private BedManager bedManager;
	private ConfigurationManager configurationManager;
	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	
	private  String ENTITY_TYPE_IPD = "IPD"; 
	/* (non-Javadoc)
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createDoctorOrderType(in.wtc.hcis.bm.base.CodeAndDescription)
	 */
	public void createDoctorOrderType( CodeAndDescription doctorOrderTypeBM ) {
		
		DoctorOrderType doctorOrderType = new DoctorOrderType();
		doctorOrderType.setOrderTypeCd( doctorOrderTypeBM.getCode() );
		doctorOrderType.setOrderTypeDesc( doctorOrderTypeBM.getDescription() );
		
		doctorOrderTypeDAO.save( doctorOrderType );
	}
	
	/* (non-Javadoc)
s	 * For every DoctorOrderBM in the list -- create a doctor order.
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createDoctorOrder(java.util.List)
	 */
	
	/**
	 *When order is being created using 'Order Group; to make this batch of BMs identifiable, we mark all BMs with
	 * same 'creationSeqNbr'
	 * 
	 * If this list of BMs contains at least one 'Admission order' then it is fair 
	 * to have BM without ARN ,but in else case it is mandatory.
	 * 
	 * AdmissionBM always should be without ARN.
	 * 
	 * For creating any order; other than 'admission' this method checks 
	 * whether active admission order or active admission is exists for the
	 * patient or not; otherwise throws exception.
	 * 
	 *  
	 */
	public void createDoctorOrder(DoctorOrderBM[] doctorOrderBMList ) {
		
		try {
			if( doctorOrderBMList != null && doctorOrderBMList.length > 0 ){
					
				//Mark all BM with same 'creationSeqNbr'
				Integer  creationSeqNbr = doctorOrderDAO.nextCreationSequenceNumber();
					
				for( DoctorOrderBM doctorOrderBM : doctorOrderBMList  ){
					
					doctorOrderBM.setCreationSeqNbr( creationSeqNbr);
					this.createDoctorOrder(doctorOrderBM);
				}
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.CREATE_DOCTOR_ORDER_FAILED );
			throw new HCISException( fault, e);		
		}
		
	}
	
	/**
	 * This method creates a doctor order record for a given doctor order business model.
	 *  
	 *  Creating the doctor order for specific type is being performed in two steps - 
	 *  
	 *  1. First create the order and put all the specific  values in 'order attribute value' table.
	 *  2. Approval of the order - This is event when the actual entries in corresponding table. (i.e. for order type of Admission create entry in Admission table )
	 *  
	 *  Now if Order is been created by using order group
	 *  
	 * @param doctorOrderBM
	 * @return
	 */
	private DoctorOrder createDoctorOrder( DoctorOrderBM doctorOrderBM ) throws Exception {
		
		Validate.notNull(doctorOrderBM, " DoctorOrderBM must not be null ");
		
			if( !doctorOrderBM.getDoctorOrderType().getCode().equals(DoctorOrderConstants.ORDER_TYPE_IP_ADMISSION)){
				
				// check whether this BM is part of order group, and we already have admission order in same group
				
				if( ! doctorOrderDAO.isCreationSeqHasAdmssionOrder( doctorOrderBM.getCreationSeqNbr()  ) &&
						 doctorOrderBM.getAdmissionNbr() == null  ){
					
					throw new Exception(" Please provide ARN ");
					
				}
		} else {
			
			List<Admission> activeAdmissions =  admissionDAO.getActiveAdmissions( doctorOrderBM.getPatientId() );
			
			if( activeAdmissions != null 	&& !activeAdmissions.isEmpty() ){
				
				throw new Exception( " Active admission already exists." );
			}
		}
			

		DoctorOrder doctorOrder = converter.convertDoctorOrderBM2DM(doctorOrderBM, null );
		doctorOrder.setDoctorOrderStatus( dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_CREATED));
		doctorOrder.setCreatedBy( doctorOrderBM.getCreatedBy() ); 
		doctorOrder.setCreationSeqNbr(doctorOrderBM.getCreationSeqNbr());
		doctorOrder.setOrderCreationDtm( new Date());
		
		doctorOrderDAO.save(doctorOrder);

		List<DoctorOrderDetailsBM>doctorOrderDetailsBMList = doctorOrderBM.getDoctorOrderDetailsList();
		
		if ( doctorOrderDetailsBMList != null && !doctorOrderDetailsBMList.isEmpty() ) {
			for ( DoctorOrderDetailsBM doctorOrderDetailsBM : doctorOrderDetailsBMList ) {
				doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrder.getDoctorOrderNbr() );
				
				DoctorOrderDetails doctorOrderDetails = converter.convertDoctorOrderDetailsBM2DM(doctorOrderDetailsBM, null );
				doctorOrderDetails.setActionStatus( dataModelManager.getActionStatus( DoctorOrderConstants.ACTION_STATUS_CREATED ));
				doctorOrderDetailsDAO.save( doctorOrderDetails );
			}
		}

		//
		//Create Attribute value entry 
		
		doctorOrderBM.setDoctorOrderNbr( doctorOrder.getDoctorOrderNbr() );
		this.createAttributeValueEntry(doctorOrderBM , false);
//		this.assignServices( doctorOrderBM);//  Moved to admission confirmation
		this.createDoctorOrderActivity( doctorOrder, DoctorOrderConstants.ACTIVITY_TYPE_ORDER_CREATED, null );
		
		
		//If system configuration says that 'Create and Approve'
		//then approve order at the same time
		String paramValue = configurationManager.getSystemConfiguration(
												ConfigurationConstants.ORDER_APPROVE_AT_CREATION); 
		
		if( ConfigurationConstants.PARAM_VALUE_YES.equals( paramValue)){
			
			this.approveDoctorOrder(doctorOrder.getDoctorOrderNbr(), doctorOrder.getDoctorId(),
									" Creted and Approved", doctorOrder.getCreatedBy());
		}
		
		return doctorOrder;
	}
	
	/**
	 * This method creates entry in orderAttributeValue table.
	 * 
	 * @param doctorOrderBM
	 * @param modified
	 */
	private void createAttributeValueEntry( DoctorOrderBM doctorOrderBM , boolean modified){
		Map<String,String> orderAttributesMap = doctorOrderBM.getOrderSpecificAttributes();
		if ( orderAttributesMap != null && orderAttributesMap.size() > 0 ){
			
			List<OrderTypeHasAttributes> orderTypeHasAttributesList = orderTypeHasAttributesDAO.findByProperty( "id.orderTypeCd", doctorOrderBM.getDoctorOrderType().getCode());
			 if( orderTypeHasAttributesList != null && !orderTypeHasAttributesList.isEmpty() ){
				  
				 for( OrderTypeHasAttributes attributes : orderTypeHasAttributesList ){
					  
					  String attributeName = attributes.getAttribute().getName();
					  String attributeValue = orderAttributesMap.get( attributeName );
					  
					  if( attributes.getIsMandatory().equals( DoctorOrderConstants.ATTRIBUTE_IS_MANDATORY_YES) && 
						 ( attributeValue == null	|| attributeValue.isEmpty())  ){
						  
							Fault fault = new Fault( ApplicationErrors.VALUE_REQUIRE_FOR_MANDATORY_FIELD );
							
							throw new HCISException( fault.getFaultMessage()+" #"+ attributeName +"#",
													 fault.getFaultCode(),
													 fault.getFaultType() );
					  }else{

						  OrderAttributeValue orderAttributeValue = new OrderAttributeValue();
						  OrderAttributeValueId orderAttributeValueId = new OrderAttributeValueId();
						  orderAttributeValueId.setOrderNbr( doctorOrderBM.getDoctorOrderNbr() );
						  orderAttributeValueId.setAttributeName( attributeName );
						  
						  orderAttributeValue.setId( orderAttributeValueId );
						  orderAttributeValue.setAttributeValue(attributeValue);
						  if( modified){
							  orderAttributeValue.setLastModifiedDtm( new Date() );
						  }
						  orderAttributeValueDAO.save( orderAttributeValue );
					  }
				  }
			 }
	}

	}
	
	/**
	 * 
	 * @param orderNumber
	 * @param orderStatusCd
	 */
	private void createDoctorOrderActivity( DoctorOrder doctorOrder, 
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
	

	/**
	 * This method creates admission request. Before creating an admission request it 
	 * also checks if an active admission/admission_reqest  already exist for the patient.
	 * An admission record is considered active - if it is in one of the
	 * following status:
	 * 	1) REQUESTED
	 *  2) ADMITTED
	 *  3) APPROVED
	 *  4) EMERGENCY_REQUEST
	 *  5) PRE_DISCHARGE 
	 * TODO:
	 * 
	 * This method will be called only when the corresponding order is approved.
	 *  
	 * This method also creates an entry into accounting module where account number is same as
	 * admission request number. The format of Account number is configurable and default
	 * format is IPD-<<Admission Number >>.
	 *
	 * Similarly, agenda will be defined by the doctor while updating the admission details.
	 * This method also supports <Name>:<Value> pair mechanism to pass specific parameters 
	 * which are applicable for the given order. For example -- values for the specific 
	 * attributes like NURSING_UNIT_TYPE_CD, TREATMENT_ESTIMATION_BY, TREATMENT_ESTIMATED_COST, 
	 * AGENDA and ENTRY_POINT_NAME can be passed using the specificAttributeMap of DoctorOrderBM
	 * object.  
	 * 
	 */
	
	private Admission createAdmissionRequest( Integer orderNumber , String createdBy ) throws Exception{
		
		DoctorOrder  doctorOrder = dataModelManager.getDoctorOrder(orderNumber);
		
		List<Admission> activeAdmissionList = admissionDAO.getActiveAdmissions( doctorOrder.getPatientId() );
		
		if ( activeAdmissionList != null && !activeAdmissionList.isEmpty() ) {
			
			Fault fault = new Fault( ApplicationErrors.ACTIVE_ADMISSION_ALREADY_EXIST );
			
			throw new HCISException( fault.getFaultMessage() + ". PATIENT_ID =  " + doctorOrder.getPatientId(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
		List <OrderAttributeValue> orderAttributeValueList = orderAttributeValueDAO.findByProperty("id.orderNbr", orderNumber);
		
		//Build attribute value map
		Map<String, String> admissionOrderAttributes = new HashMap<String, String>(0);
		
		if( orderAttributeValueList != null && !orderAttributeValueList.isEmpty() ){
			
			for( OrderAttributeValue attributeValue : orderAttributeValueList  ){
				
				admissionOrderAttributes.put(attributeValue.getId().getAttributeName(), attributeValue.getAttributeValue() );
			}
		}

		
		
//		DoctorOrder doctorOrder = this.createDoctorOrder( doctorOrderBM );
		
		Calendar today = Calendar.getInstance();
		Admission admission = new Admission();
		admission.setAdmissionDt( today.getTime() ); 
		admission.setCreateDtm( today.getTime() );
		admission.setAdmissionRequestedBy( createdBy );
		
		AdmissionStatus admissionStatus =  dataModelManager.getAdmissionStatus( DoctorOrderConstants.ADMISSION_STATUS_REQUESTED );
		admission.setAdmissionStatus(admissionStatus);
		
		//Make sure that Doctor with specified Id is already exists in the system
		Doctor doctor = baseDataModelManager.getDoctor( doctorOrder.getDoctorId()  );
		admission.setDoctorId( doctor.getDoctorId() );
		admission.setPatientId( doctorOrder.getPatientId() );
		
		
		if ( admissionOrderAttributes != null && !admissionOrderAttributes.isEmpty() ) {
			
			String nursringUnitTypeCd = admissionOrderAttributes.get( DoctorOrderConstants.NURSING_UNIT_TYPE_CD_ATTR );
		
			if ( nursringUnitTypeCd != null && nursringUnitTypeCd.length() > 0 ) {
				NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType( nursringUnitTypeCd );
				admission.setNursingUnitType( nursingUnitType );
			}
			
			String admissionEntryPointCd = admissionOrderAttributes.get( DoctorOrderConstants.ADMISSION_ENTRY_POINT_CD_ATTR );
			if ( admissionEntryPointCd != null && admissionEntryPointCd.length() > 0 ) {
				AdmissionEntryPoint admissionEntryPoint = dataModelManager.getAdmissionEntryPoint(admissionEntryPointCd);
				admission.setAdmissionEntryPoint( admissionEntryPoint );
			}
			
			admission.setEntryPointReference( admissionOrderAttributes.get( DoctorOrderConstants.ADMISSION_ENTRY_POINT_REFERENCE_ATTR ) );
			
			String admissionAgenda = admissionOrderAttributes.get( DoctorOrderConstants.ADMISSION_AGENDA_ATTR );
			if ( admissionAgenda == null || admissionAgenda.length() == 0 ) {
				admissionAgenda = doctorOrderConfig.getString( DoctorOrderConstants.DEFAULT_ADMISSION_AGENDA );
			}
			admission.setAgenda( admissionAgenda );
			
			String admissionEstimatedCost = admissionOrderAttributes.get( DoctorOrderConstants.ESTIMATED_TREATMENT_COST_ATTR );
			if ( admissionEstimatedCost != null && admissionEstimatedCost.length() > 0 ) {
				admission.setTreatmentEstimatedCost( Double.parseDouble( admissionEstimatedCost ) );
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("d/M/y h:m a");
			
			String expectedDischargeDate = admissionOrderAttributes.get( DoctorOrderConstants.EXPECTED_DISCHARGE_DT_ATTR );
			if ( expectedDischargeDate != null && expectedDischargeDate.length() > 0 ) {
				try {
					admission.setExpectedDischargeDtm( sdf.parse( expectedDischargeDate ) );
				} catch (ParseException e) {
					throw new Exception("Date formate of " + DoctorOrderConstants.EXPECTED_DISCHARGE_DT_ATTR +" is not suported.");
				}
			}
			
			String reasonForAdmission = admissionOrderAttributes.get( DoctorOrderConstants.REASON_FOR_ADMISSION_ATTR);
			if ( reasonForAdmission != null && reasonForAdmission.length() > 0 ) {
				admission.setReasonForAdmission(reasonForAdmission);
			}
		}
		
		
		admissionDAO.save( admission );

/*		accountantIntgManager.createAccount(admission.getAdmissionReqNbr().toString(), 
														DoctorOrderConstants.REFERENCE_TYPE_IPD, 
														patient.getDescription(), 
														ApplicationEntities.PATIENT, 
														DoctorOrderConstants.REFERENCE_TYPE_PAT, 
														admission.getPatientId().toString(), 
														createdBy );
*/		
		
		this.createAccount( admission.getAdmissionReqNbr());
		
		return admission;
		
	}

	private void createAccount(Integer admissionReqNbr ){
		if(admissionReqNbr != null){
			Admission admission = dataModelManager.getAdmission(admissionReqNbr);
			if(admission != null){
				PatientLiteBM patinetLiteBM = patientManager.getPatientLiteBM( admission.getPatientId() );
				
				AccountInfoBM accountInfoBM = new AccountInfoBM();
				
				ContactDetailsBM contactDetailsBM = patinetLiteBM.getContactDetailsBM();
				contactDetailsBM.setPersonelId(admission.getAdmissionReqNbr());
				contactDetailsBM.setForEntity(new CodeAndDescription(ENTITY_TYPE_IPD,""));
				contactDetailsBM.setCreatedBy(admission.getAdmissionRequestedBy());
				accountInfoBM.setContactDetailsBM(contactDetailsBM);
				
				accountInfoBM.setEntityCategory( new CodeAndDescription("1000004",""));//TODO:Change it to real one,its just for testing
				eagleIntegration.createAccount(accountInfoBM, true);
			}
		}
	}
	
	private void assignServices( DoctorOrder doctorOrder , String assignedByPersonId ){ 
	
	try {
		//Make sure that active admission is already exists
			List <Admission> admissionList = admissionDAO.getActiveAdmissions(doctorOrder.getPatientId());
		
			if( admissionList != null && !admissionList.isEmpty() ){
				
				AssignSvcAndPackageBM assignSvcAndPackageBM = this.getAssignedServiceBm(doctorOrder);
				if((assignSvcAndPackageBM.getAssignedPackageBMList()!= null && 
						assignSvcAndPackageBM.getAssignedPackageBMList().size()>0)|| 
						(assignSvcAndPackageBM.getAssignedServiceBMList()!=null && 
								assignSvcAndPackageBM.getAssignedServiceBMList().size()>0)){
					
					assignSvcAndPackageBM.setCreatedBy( assignedByPersonId );
					serviceManager.assignSvcAndPackages(assignSvcAndPackageBM);
				}
			}
	} catch (RuntimeException e) {
		
		Fault fault = new Fault( in.wtc.hcis.bo.constants.ApplicationErrors.SERVICE_ASSIGNMENT_FAILED );
		throw new HCISException(fault, e );
		
	}
	
	}
	
	private void createDischargeOrder( Integer orderNumber , String createdBy ) throws HCISException{
		
		try {
			DoctorOrder  doctorOrder = dataModelManager.getDoctorOrder(orderNumber);
			
			//active admission must exist
			List<Admission> activeAdmissionList = admissionDAO.getActiveAdmissions( doctorOrder.getPatientId() );
			
			Admission admission = doctorOrder.getAdmission(); 
			if ( admission == null || activeAdmissionList == null   ) {
				
				Fault fault = new Fault( ApplicationErrors.ACTIVE_ADMISSION_DOES_NOT_EXIST );
				
				throw new HCISException( fault.getFaultMessage() + ". PATIENT_ID =  " + doctorOrder.getPatientId(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}
			
			
			if (!admission.getAdmissionStatus().getAdmissionStatusCd().equals( DoctorOrderConstants.ADMISSION_STATUS_ADMITTED )){
				
				throw new Exception(" Admission must be in '" + DoctorOrderConstants.ADMISSION_STATUS_ADMITTED + "' satatus. ");
			}
			
//				Code to ensure that discharge order in not exist for the admission
				DischargeOrder dischargeOrder = dischargeOrderDAO.findById(admission.getAdmissionReqNbr());
				
				if( dischargeOrder != null ){
					
					Fault fault = new Fault( ApplicationErrors.DISCHARGE_ORDER_ALREADY_EXIST );
					
					throw new HCISException( fault.getFaultMessage() + ". For ARN =  " + admission.getAdmissionReqNbr(),
											 fault.getFaultCode(),
											 fault.getFaultType() );
				}
			
			List <OrderAttributeValue> orderAttributeValueList = orderAttributeValueDAO.findByProperty("id.orderNbr", orderNumber);
			
			//Build attribute value map
			Map<String, String> dischargeOrderAttributes = new HashMap<String, String>(0);
			
			if( orderAttributeValueList != null && !orderAttributeValueList.isEmpty() ){
				
				for( OrderAttributeValue attributeValue : orderAttributeValueList  ){
					
					dischargeOrderAttributes.put(attributeValue.getId().getAttributeName(), attributeValue.getAttributeValue() );
				}
			}
			
			dischargeOrder = new DischargeOrder();
			
			dischargeOrder.setAdmission(admission);
			dischargeOrder.setAdmissionReqNbr(admission.getAdmissionReqNbr());
			dischargeOrder.setDoctorOrder(doctorOrder);
			dischargeOrder.setDoctorOrderStatus( dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_CREATED));
			dischargeOrder.setOrderCreationDtm( new Date() );
			dischargeOrder.setOrderRequestedBy( createdBy);
			
			if ( dischargeOrderAttributes != null && !dischargeOrderAttributes.isEmpty() ) {

				String dischargeTypeAttr = dischargeOrderAttributes.get( DoctorOrderConstants.DISCHARGE_TYPE_CD_ATTR );
				
				if ( dischargeTypeAttr != null && dischargeTypeAttr.length() > 0 ) {
					DischargeType dischargeType = dataModelManager.getDischargeTpe( dischargeTypeAttr );
					dischargeOrder.setDischargeType(dischargeType);
				}
				
				SimpleDateFormat smpDateFormate = new SimpleDateFormat("d/M/y h:m a");
				
				String expectedDischargeDateAttr = dischargeOrderAttributes.get( DoctorOrderConstants.EXPECTED_DISCHARGE_DT_ATTR);
				
				if ( expectedDischargeDateAttr != null && expectedDischargeDateAttr.length() > 0 ) {
					try {

						Date expectedDischargeDate = smpDateFormate.parse( expectedDischargeDateAttr );
						dischargeOrder.setExpectedDischargeDtm( expectedDischargeDate );
					} catch (ParseException e) {

						throw new Exception("Date formate of " + DoctorOrderConstants.EXPECTED_DISCHARGE_DT_ATTR +" is not suported.");
					}
				}
			}
			
			dischargeOrderDAO.save( dischargeOrder );
			
			//Move the corresponding admission to 'PRE_DISCHARGE' status
			//
			
			admission.setAdmissionStatus( dataModelManager.getAdmissionStatus(DoctorOrderConstants.ADMISSION_STATUS_PREDISCHARGE));
			admission.setModifiedBy(createdBy);
			admission.setLastUpdatedDtm( new Date() );
			
			admissionDAO.attachDirty( admission ); 
			
			this.createDischargeOrderActivity(dischargeOrder, " Discharge Order created");
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.CREATE_DISCHARGE_ENTRY_FAILED);
			throw new HCISException(fault ,e );
		}
		
	}

	private void createDischargeOrderActivity( DischargeOrder dischargeOrder, String remarks ) {
		DischargeActivity dischargeActivity = new DischargeActivity();
		
		DoctorOrderStatus doctorOrderStatus = dischargeOrder.getDoctorOrderStatus();
		String activityTypeCd = DoctorOrderConstants.DISCHARGE_ACTIVITY_PREFIX + doctorOrderStatus.getOrderStatusCd();
		ActivityType activityType = dataModelManager.getActivityType( activityTypeCd );
		dischargeActivity.setActivityType( activityType );
		dischargeActivity.setAdmission( dischargeOrder.getAdmission() );
		dischargeActivity.setCreatedBy( dischargeOrder.getModifiedBy() );
		dischargeActivity.setDoctorOrderStatus( doctorOrderStatus );
		
		DischargeActivityId dischargeActivityId = new DischargeActivityId();
		dischargeActivityId.setActivityTypeCd(activityTypeCd);
		dischargeActivityId.setAdmissionReqNbr( dischargeOrder.getAdmission().getAdmissionReqNbr() );
		dischargeActivityId.setCreationDtm( dischargeOrder.getLastModifiedDtm() );
		dischargeActivity.setId( dischargeActivityId );
		
		dischargeActivity.setRemarks(remarks);
		dischargeActivityDAO.save( dischargeActivity );
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
	public DoctorOrderBM modifyDoctorOrder( DoctorOrderBM doctorOrderBM  ) {
		
		try {
			DoctorOrder existingDoctorOrder = dataModelManager.getDoctorOrder( doctorOrderBM.getDoctorOrderNbr() );
			if( !existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd().equals(DoctorOrderConstants.ORDER_STATUS_CREATED )){
				
				throw new Exception(" Doctor orders with status '" + DoctorOrderConstants.ORDER_STATUS_CREATED +
						"' only can be modified, it is in "+existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd() +"' staus.");
				
			}else{
				
				
				this.isValidOrderStatusTransition(existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd(),
											doctorOrderBM.getDoctorOrderStatus().getCode());
				
				
				
				
					DoctorOrder doctorOrder = converter.convertDoctorOrderBM2DM( doctorOrderBM, existingDoctorOrder );
					doctorOrder.setModifiedBy( doctorOrderBM.getModifiedBy() );
					doctorOrder.setLastModifiedTm( new Date() );
					
					doctorOrderDAO.attachDirty( doctorOrder );

					List<DoctorOrderDetails>  doctorOrderDetailsList = doctorOrderDetailsDAO.findByProperty("id.orderNbr", doctorOrder.getDoctorOrderNbr());
					
					if( doctorOrderDetailsList != null && !doctorOrderDetailsList.isEmpty() ){
						
						for( DoctorOrderDetails orderDetails : doctorOrderDetailsList ){
							
							if( orderDetails.getActionStatus() != null && 
									orderDetails.getActionStatus().getActionStatusCd().equals(DoctorOrderConstants.ACTION_STATUS_COMPLETED)){
								
								Fault fault = new Fault( ApplicationErrors.DOCTOR_ORDER_ALREADY_COMPLETED );
								throw new HCISException( "Can't Modify : " + fault.getFaultMessage(),
																			 fault.getFaultCode(),
																			 fault.getFaultType() );
							}else{
								doctorOrderDetailsDAO.delete( orderDetails );
							}
						}
					}
					
					List<DoctorOrderDetailsBM>doctorOrderDetailsBMList = doctorOrderBM.getDoctorOrderDetailsList();
					
					if ( doctorOrderDetailsBMList != null && !doctorOrderDetailsBMList.isEmpty() ) {
						for ( DoctorOrderDetailsBM doctorOrderDetailsBM : doctorOrderDetailsBMList ) {

							doctorOrderDetailsBM.setDoctorOrderNumber( doctorOrder.getDoctorOrderNbr() );
							
							DoctorOrderDetails doctorOrderDetails = converter.convertDoctorOrderDetailsBM2DM(doctorOrderDetailsBM, null );
							doctorOrderDetails.setActionStatus( dataModelManager.getActionStatus( DoctorOrderConstants.ACTION_STATUS_CREATED ));
							doctorOrderDetailsDAO.save( doctorOrderDetails );
						}
					}
					
					String currentStatusOfOrder = existingDoctorOrder.getDoctorOrderStatus().getOrderStatusCd();
					String newStatusOfOrder = doctorOrder.getDoctorOrderStatus().getOrderStatusCd();
					
					//
					//Bulldoze the old attribute value value if any 
					
					List<OrderAttributeValue> orderAttributeValueList = orderAttributeValueDAO.findByProperty("id.orderNbr", doctorOrder.getDoctorOrderNbr());
					
					if( orderAttributeValueList != null && !orderAttributeValueList.isEmpty()){
						for( OrderAttributeValue attributeValue : orderAttributeValueList  )
						orderAttributeValueDAO.delete(attributeValue);
					}
					
					//
					//Now create new AttributeValue in modification mode
					this.createAttributeValueEntry(doctorOrderBM , true);
					
					
					if ( !newStatusOfOrder.equals( currentStatusOfOrder ) ) {
						//
						// Assumption is that -- all the activity type related to ORDER will have "ORDER_" 
						// preceded in its name. 
						//
						this.createDoctorOrderActivity( doctorOrder, 
					                                    "ORDER_" + newStatusOfOrder,
					                                    " Order status changed from " + currentStatusOfOrder + " to status = " + newStatusOfOrder );
					}
					
					return converter.convertDoctorOrderDM2BM(doctorOrder);
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.MODIFY_DOCTOR_ORDER_FAILED );
			
			throw new HCISException( fault , e);
		}
	}
	
	
	/*
	 * For discharge there are only three status -
	 * 1. Created 
	 * 2 Completed
	 * 
	 * Modification are allowed only when order is in CREAED status.
	 */
	
	public DischargeBM modifyDischargeDetails( DischargeBM modifiedDischargeBM ) {
		
		DischargeOrder dischargeOrder = dataModelManager.getDischargeOrder( modifiedDischargeBM.getAdmissionReqNbr() ); 
		
		DoctorOrderStatus existingStatus =  dischargeOrder.getDoctorOrderStatus();
		 
		if( DoctorOrderConstants.DISCHARGE_STATUS_CREATED.equals( existingStatus.getOrderStatusCd())){
			
		}
		
		dischargeOrder.setDischargeSummary( modifiedDischargeBM.getDischargeSummary() );

		if( modifiedDischargeBM.getDischargeType() != null && modifiedDischargeBM.getDischargeType().getCode() != null ){
			
			DischargeType dischargeType = dataModelManager.getDischargeTpe( modifiedDischargeBM.getDischargeType().getCode()   );
			dischargeOrder.setDischargeType(dischargeType);
		}
		
		dischargeOrder.setExpectedDischargeDtm( modifiedDischargeBM.getExpectedDischargeDate()  );
		dischargeOrder.setLastModifiedDtm( new Date() );
		dischargeOrder.setModifiedBy( modifiedDischargeBM.getModifiedBy() );
		
		dischargeOrderDAO.attachDirty( dischargeOrder );
		
		this.createDischargeOrderActivity( dischargeOrder, ""); 

		return converter.convertDischargeOrderDM2BM(  dischargeOrder  );
		
	}
	
	
	public void dischargePatient( Integer admissionReqNbr , String dischargeSummary, String personId){
		
		try {
			Admission admission = dataModelManager.getAdmission(admissionReqNbr);
			
			//Admission must in pre-discharge status
			
			if(! DoctorOrderConstants.ADMISSION_STATUS_PREDISCHARGE.equals(admission.getAdmissionStatus().getAdmissionStatusCd())){
				
				throw new Exception(" Admission must be in " + DoctorOrderConstants.ADMISSION_STATUS_PREDISCHARGE + " status." +
						"(It is in '"+ admission.getAdmissionStatus().getAdmissionStatusCd() + "' status)");
			}
			
			DischargeOrder dischargeOrder = dataModelManager.getDischargeOrder( admissionReqNbr );
			
			 
			if(! DoctorOrderConstants.DISCHARGE_STATUS_CREATED.equals( dischargeOrder.getDoctorOrderStatus().getOrderStatusCd())){
				
				throw new Exception(" Discharge entry must be in created status ");
			}
			
				dischargeOrder.setActualDischargeDtm( new Date() );
				dischargeOrder.setDischargeSummary(dischargeSummary);
				
				DoctorOrderStatus completedStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_COMPLETED);
				dischargeOrder.setDoctorOrderStatus(completedStatus);
				dischargeOrder.setLastModifiedDtm( new Date() );
				dischargeOrder.setModifiedBy(personId);
				
				dischargeOrderDAO.attachDirty( dischargeOrder );
				
				this.createDischargeOrderActivity(dischargeOrder, " Patient discharged ");
				
				
				// update the status of admission to "DISCHARGED" 
				admission.setAdmissionStatus( dataModelManager.getAdmissionStatus(DoctorOrderConstants.ADMISSION_STATUS_DISCHARGE));
				admission.setModifiedBy(personId);
				admission.setLastUpdatedDtm( new Date() );
				
				admissionDAO.attachDirty( admission ); 
				
				//Release the bed(s) occupied by patient
				//
				
				List<CodeAndDescription> bedList = bedManager.getOccupiedBedFromPatient(admissionReqNbr, null);
				
				if( bedList != null && !bedList.isEmpty() ){
					for( CodeAndDescription bed : bedList){
						
						bedManager.releaseBed(bed.getCode(), "", " Dischate process ");
					}
				}
				
		}  catch (Exception e) {
			 Fault fault = new Fault(ApplicationErrors.DISCHARGE_OPERATION_FAILED);
			 throw new HCISException(fault , e);
		}
		
	}
	
	//
	//This method updates the current status of doctor order  
	//Remarks should be mandatory in case of DISAPPROVAL 
	public boolean updateOrderStatus(   Integer orderNumber, String newStatusCode,  String personId , String remarks){
		
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );

		if(  DoctorOrderConstants.ORDER_STATUS_APPROVED.equals( newStatusCode)  ){
			
			this.approveDoctorOrder(orderNumber, doctorOrder.getDoctorId(),remarks, personId);
			
			return true;
			
		}else if( DoctorOrderConstants.ORDER_STATUS_DISAPPROVED.equals( newStatusCode ) ){
			
			this.disapproveDoctorOrder(orderNumber, doctorOrder.getDoctorId()  , remarks, personId);
			
			return true;
		}else if( DoctorOrderConstants.ORDER_STATUS_CANCELED.equals( newStatusCode ) ){
			
			this.cancelDoctorOrder(orderNumber, remarks, personId);
			
			return true;
		}
		
		return false;
	}
	
	/*
	 * This method plays a great role in driving the whole order life-cycle.
	 * 
	 * 1. If the order is of type admission then create entry in Admission table, hence ARN will get generated. 
	 * 2. If order type is other then Admission order then check for active admission record in the system
	 * 	  If it is not found then throw an exception 
	 * 3. Assign services and package if require
	 * 	
	 * (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.OrderManager#approveDoctorOrder(java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	
	public DoctorOrderBM approveDoctorOrder( Integer orderNumber, 
			                               Integer doctorId,
			                               String remarks,
			                               String approvedByPersonId ) {
		 
		
		try {
			
			Doctor doctor = baseDataModelManager.getDoctor(doctorId);
			
			DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );

			this.isValidOrderStatusTransition(doctorOrder.getDoctorOrderStatus().getOrderStatusCd(),
										 	  DoctorOrderConstants.ORDER_STATUS_APPROVED);
				
				
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
			
			Admission admission = null;
			
			if( doctorOrder.getDoctorOrderType().getOrderTypeCd().equals( DoctorOrderConstants.ORDER_TYPE_IP_ADMISSION)){
				
				admission =this.createAdmissionRequest(orderNumber,approvedByPersonId);
			
				//search for the orders, created along with this order (creationSeqNbr will same) and 
				//mark with same ARN
				
//				doctorOrderDAO.stampARN(doctorOrder.getCreationSeqNbr(),admission.getAdmissionReqNbr(),approvedByPersonId);
				
				//----------------------------------------------
				
				List<DoctorOrder> doctorOrderList = doctorOrderDAO.findByCreationSeqNbr(doctorOrder.getCreationSeqNbr());
				//======================================
				
				if( doctorOrderList != null && !doctorOrderList.isEmpty()  ){
					for(  DoctorOrder order : doctorOrderList ){
						
						order.setAdmission(admission);
						order.setModifiedBy(approvedByPersonId);
						order.setLastModifiedTm( new Date() );
						doctorOrderDAO.attachDirty( order );
					}
				}

			}else if( doctorOrder.getDoctorOrderType().getOrderTypeCd().equals( DoctorOrderConstants.ORDER_TYPE_DISCHARGE) ){
		
				this.createDischargeOrder(orderNumber, approvedByPersonId);
			}
			
			//Assign the services 
			//
			this.assignServices( doctorOrder , approvedByPersonId );
			
			//
			//Update the status if necessary 
			this.autoUpdateOrderStatus(doctorOrder);
			
			this.createDoctorOrderActivity( doctorOrder, 
					                        DoctorOrderConstants.ACTIVITY_TYPE_ORDER_APPROVED, 
					                        remarks );
			
			return converter.convertDoctorOrderDM2BM(doctorOrder);
		} catch (Exception e) {
			 Fault fault = new Fault(ApplicationErrors.APPROVE_ORDER_FAILED);
			 throw new HCISException(fault , e);
		}
	}
	
	/**
	 * This method disapproves the doctor order only if it is in 'CREATED' status.
	 * If order is an 'Admission' order then disapprove all other orders created in same batch (group)
	 */
	public DoctorOrderBM disapproveDoctorOrder( Integer orderNumber,
			                                  Integer doctorId,
			                                  String disapprovalRemarks,
			                                  String disApprovedByPersonId ) {
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );
	
		Doctor doctor = baseDataModelManager.getDoctor(doctorId);
		
//		doctor.getDoctorHasOrderAuthorizationPermission();
		// TODO : Check if this doctor has a role to authorize pending order or not
		// At this moment -- we do not have role management integrated, however whenever
		// we do that, this part of code must be modified.
		//
		
		this.isValidOrderStatusTransition(doctorOrder.getDoctorOrderStatus().getOrderStatusCd(),
			 	  DoctorOrderConstants.ORDER_STATUS_DISAPPROVED);
		
		
		DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_DISAPPROVED );
		doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
		doctorOrder.setLastModifiedTm( new Date() );
		doctorOrder.setModifiedBy( disApprovedByPersonId );
		doctorOrderDAO.attachDirty( doctorOrder );
		
		this.createDoctorOrderActivity( doctorOrder, 
				                        DoctorOrderConstants.ACTIVITY_TYPE_ORDER_DISAPPROVED, 
				                        disapprovalRemarks );
		
		return converter.convertDoctorOrderDM2BM(doctorOrder);
	}
	
	/**
	 * 
	 * @param orderNumber
	 * @param remarks
	 * @param personId
	 * @return
	 */
	public DoctorOrderBM cancelDoctorOrder( Integer orderNumber,
								            String remarks,
								            String personId ){
		
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );
		this.isValidOrderStatusTransition(doctorOrder.getDoctorOrderStatus().getOrderStatusCd(),
			 	  DoctorOrderConstants.ORDER_STATUS_CANCELED);
		
		
		DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus( DoctorOrderConstants.ORDER_STATUS_CANCELED );
		doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
		doctorOrder.setLastModifiedTm( new Date() );
		doctorOrder.setModifiedBy( personId );
		doctorOrderDAO.attachDirty( doctorOrder );
		
		this.createDoctorOrderActivity( doctorOrder, 
				                        DoctorOrderConstants.ACTIVITY_TYPE_ORDER_CANCELED, 
				                        remarks );
		
		return converter.convertDoctorOrderDM2BM(doctorOrder);
		
	}
	/*
	 * -------------------------------------------------------
	 * 	Old Status					New Status
	 * -------------------------------------------------------
	 * CREATED						ASSIGNED, REJECTED
	 * ASSIGNED					INPROGRESS, HOLD, COMPLETED
	 * HOLD						-  INPROGRESS,REJECTED
	 * INPROGRESS				COMPLETED
	 * REJECTED					-	
	 * COMPLETED
	 * -------------------------------------------------------
	 * 
	 * 
	 */
	
	private boolean isValidOrderDetailStatusTransition(String oldStatus, String newStatus){
		
		if( oldStatus.equals(newStatus)){
			
			return true;
		}
		
		//
		//For now there will be only two status CREATED --> COMPLETED
		
		if( DoctorOrderConstants.ACTION_STATUS_CREATED.equals( oldStatus )&& 
				(DoctorOrderConstants.ACTION_STATUS_COMPLETED .equals(newStatus))){
			 return true;
		}
		
		
/*	
		if( DoctorOrderConstants.ACTION_STATUS_CREATED.equals( oldStatus )&& 
			(DoctorOrderConstants.ACTION_STATUS_ASSIGNED .equals(newStatus) ||
			 DoctorOrderConstants.ACTION_STATUS_REJECTED.equals(newStatus))){
			
			return true;
		}else if(DoctorOrderConstants.ACTION_STATUS_ASSIGNED.equals( oldStatus ) &&
				 DoctorOrderConstants.ACTION_STATUS_INPROGRESS.equals( newStatus ) ||
				 DoctorOrderConstants.ACTION_STATUS_HOLD.equals( newStatus ) ||
				 DoctorOrderConstants.ACTION_STATUS_COMPLETED.equals(newStatus)){
			
			return true;
		}else if(DoctorOrderConstants.ACTION_STATUS_HOLD.equals( oldStatus ) &&
				 DoctorOrderConstants.ACTION_STATUS_INPROGRESS.equals( newStatus ) ||
				 DoctorOrderConstants.ACTION_STATUS_REJECTED.equals(newStatus)){
			
			return true;
		}else if(DoctorOrderConstants.ACTION_STATUS_INPROGRESS.equals( oldStatus ) &&
				 DoctorOrderConstants.ACTION_STATUS_COMPLETED.equals( newStatus )){
			return true;
		}
	*/
		
		
		Fault fault = new Fault( in.wtc.hcis.bo.constants.ApplicationErrors.INVALID_STATUS_TRANSITION);
		throw new HCISException( fault.getFaultMessage() +  "From '" + oldStatus + "' to '"+ newStatus,
							     fault.getFaultCode(),fault.getFaultType());
	}
 
	//
	//	This method modified the current status of order detail.
	//Method performs status transition  validation as mentioned  above.
	//Additionally it is assumed that UI should take care of order( sequence ) of actions 
	//to be performed (as seq number and sub seq number is managed by UI only )
	//
	//If 
	//
	
	public boolean updateOrderDetailsStatus ( 	 Integer orderNumber, Integer sequenceNbr, Integer subSequenceNbr,
																								 String newStatus ,String  presonid, String remarks ){

		try {
			DoctorOrder doctorOrder =  dataModelManager.getDoctorOrder(orderNumber);
			
			if( ! DoctorOrderConstants.ORDER_STATUS_APPROVED.equals( doctorOrder.getDoctorOrderStatus().getOrderStatusCd())){
				
				throw new Exception( "Doctor order is in '" + doctorOrder.getDoctorOrderStatus().getStatusDesc() + "' status. ");
			}
			
			
			DoctorOrderDetailsId id = new DoctorOrderDetailsId();
			id.setOrderNbr(orderNumber);
			id.setSequenceNbr(sequenceNbr);
			id.setSubSequenceNbr(subSequenceNbr);
			
			DoctorOrderDetails orderDetails =  doctorOrderDetailsDAO.findById( id) ;
			
			ActionStatus actionStatus =  dataModelManager.getActionStatus( newStatus );
			
			if( orderDetails != null ){
				this.isValidOrderDetailStatusTransition( orderDetails.getActionStatus().getActionStatusCd() , newStatus) ;
				
				orderDetails.setActionStatus(actionStatus);
				orderDetails.setActionTakenBy(presonid);
				
				if( remarks != null  ){
					
						
						orderDetails.setActionRemarks(  remarks );
				}
				
				doctorOrderDetailsDAO.attachDirty( orderDetails );
				
				if( DoctorOrderConstants.ACTION_STATUS_COMPLETED.equals( newStatus )){
					
					this.autoUpdateOrderStatus( doctorOrder );
				}
				
				return true;
			}
			
			return false;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.MODIFY_DOCTOR_ORDER_FAILED );
			
			throw new HCISException( fault , e);
		}
	}

	/**
	 * This method updates the status of doctor order based on the status of its details.
	 * 
	 * If all the entries of detail is marked as completed then mark the order also as "completed".
	 * @param doctorOrder
	 */
	private void autoUpdateOrderStatus( DoctorOrder doctorOrder ){
		
		Set< DoctorOrderDetails > doctorOrderDetailsSet =  doctorOrder.getDoctorOrderDetailses();
		
		int compleatedDetails = 0;
		int totoalDetails = 0;
		
		if( doctorOrderDetailsSet != null && !doctorOrderDetailsSet.isEmpty() ){
			totoalDetails = doctorOrderDetailsSet.size() ;
			
			for( DoctorOrderDetails orderDetails : doctorOrderDetailsSet ){
				
				if(  DoctorOrderConstants.ACTION_STATUS_COMPLETED.equals(orderDetails.getActionStatus().getActionStatusCd())){
					compleatedDetails ++;
				}
			}
			
			}
		
		if( totoalDetails ==  compleatedDetails ){
			DoctorOrderStatus doctorOrderStatus = dataModelManager.getDoctorOrderStatus(DoctorOrderConstants.ORDER_STATUS_COMPLETED);
			doctorOrder.setDoctorOrderStatus(doctorOrderStatus);
			
			doctorOrderDAO.attachDirty( doctorOrder );
		}
	}
	
	/* (non-Javadoc)
	 * 1) Create an order group
	 * 2) Create association between this group and existing doctor order templates
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createOrderGroup(in.wtc.hcis.bm.ip.DoctorOrderGroupBM)
	 */
	public void createOrderGroup( DoctorOrderGroupBM doctorOrderGroupBM ) {
		DoctorOrderGroup doctorOrderGroup = converter.convertDoctorOrderGroupBM2DM( doctorOrderGroupBM, null );
		doctorOrderGroup.setCreationDtm( new Date() );
		
		doctorOrderGroupDAO.save( doctorOrderGroup );
		
		List<OrderGroupTemplateAssociationBM>orderGroupTemplateList = doctorOrderGroupBM.getOrderGroupTemplateAssociationList();
		
		if ( orderGroupTemplateList !=  null && !orderGroupTemplateList.isEmpty() ) {
			for ( OrderGroupTemplateAssociationBM groupTemplateAssociationBM : orderGroupTemplateList ) {
				groupTemplateAssociationBM.setOrderGroupName( doctorOrderGroupBM.getOrderGroupName() );
				DoctorOrderGroupHasTemplate groupHasTemplate = converter.convertDoctorOrderGroupHasTemplateBM2DM( groupTemplateAssociationBM, null );
				doctorOrderGroupHasTemplateDAO.save( groupHasTemplate );
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createOrderTemplate(in.wtc.hcis.bm.ip.OrderTemplateBM)
	 */
	public void createOrderTemplate(OrderTemplateBM orderTemplateBM) {
		DoctorOrderTemplate doctorOrderTemplate = converter.convertDoctorOrderTemplateBM2DM( orderTemplateBM, null );
		doctorOrderTemplateDAO.save( doctorOrderTemplate );
		
		List<OrderTemplateDetailsBM> orderTemplateDetailsBMList = orderTemplateBM.getOrderTemplateDetailsList();
		
		if(orderTemplateDetailsBMList != null && !orderTemplateDetailsBMList.isEmpty()){
			
			for(OrderTemplateDetailsBM templateDetailsBM : orderTemplateDetailsBMList){
				if(templateDetailsBM.getTemplateId() == null || templateDetailsBM.getTemplateId().isEmpty()){
					templateDetailsBM.setTemplateId(orderTemplateBM.getTemplateId());
				}
				DoctorOrderTemplateDetails orderTemplateDetails = converter.convertDoctorOrderTemplateDetailsBM2DM( templateDetailsBM,null );
				doctorOrderTemplateDetailsDAO.save(orderTemplateDetails);
				
			}
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#getDoctorOrderGroups(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	public List<DoctorOrderGroupBM> getDoctorOrderGroups( String orderGroupName,
			                                              String description, 
			                                              Integer groupForDoctorId ) {
		
		List<DoctorOrderGroup>doctorOrderGroupList = doctorOrderGroupDAO.getDoctorOrderGroups(  orderGroupName, 
								                                                                description, 
								                                                                groupForDoctorId );
		
		if ( doctorOrderGroupList != null && !doctorOrderGroupList.isEmpty() ) {
			
			List<DoctorOrderGroupBM>doctorOrderGroupBMList = new ArrayList<DoctorOrderGroupBM>(0);
			
			for ( DoctorOrderGroup doctorOrderGroup : doctorOrderGroupList ) {
				doctorOrderGroupBMList.add( converter.convertDoctorOrderGroupDM2BM( doctorOrderGroup  ) );
			}
			
			return doctorOrderGroupBMList;
		} else {
			return null;
		}
	}

	public List<DoctorOrderBM> getDoctorOrders( Integer doctorOrderNbr,
            String doctorOrderTypeCd, 
            String doctorOrderTemplateId,
            Integer admissionNbr, 
            String doctorOrderStatusCd, 
            String patientId,
            String orderDesc, 
            String orderDictationMethod ) {

		List<DoctorOrder> doctorOrderList = doctorOrderDAO.getDoctorOrders(
				doctorOrderNbr, doctorOrderTypeCd, doctorOrderTemplateId,
				admissionNbr, doctorOrderStatusCd, patientId, orderDesc,
				orderDictationMethod);

		if (doctorOrderList != null && !doctorOrderList.isEmpty()) {

			List<DoctorOrderBM> doctorOrderBMList = new ArrayList<DoctorOrderBM>(0);

			for (DoctorOrder doctorOrder : doctorOrderList) {
				doctorOrderBMList.add(converter.convertDoctorOrderDM2BM(doctorOrder));
			}

			return doctorOrderBMList;
		} else {
			return null;
		}
	}


//	method needs to be verified  
	public ListRange findDoctorOrders(Integer admissionNbr,
									  Integer patientId,
									  String patientName,
							          String doctorOrderStatusCd,
							          String doctorOrderTypeCd, 
							          Date orderDateFrom,
							          Date orderDateTo,
							          int start,
									  int count,
									  String orderBy){
	
		List<DoctorOrder> doctorOrderList = doctorOrderDAO.findDoctorOrders( admissionNbr, 
																			 patientId,
																			 patientName,
																			 doctorOrderStatusCd,
																			 doctorOrderTypeCd,
																			 orderDateFrom, 
																			 orderDateTo );

		List<DoctorOrderBM> doctorOrderBMList = new ArrayList<DoctorOrderBM>();
		
		if( doctorOrderList != null && ! doctorOrderList.isEmpty() ){
			
			for( DoctorOrder doctorOrder : doctorOrderList ){
				
				DoctorOrderBM doctorOrderBM = converter.convertDoctorOrderDM2BM(doctorOrder);
				
				List<DoctorOrderDetailsBM> doctorOrderDetailsBMList =  new ArrayList<DoctorOrderDetailsBM>();
				List<DoctorOrderDetails> doctorOrderDetailsList =  doctorOrderDetailsDAO.findByProperty("id.orderNbr", doctorOrder.getDoctorOrderNbr() );
				
				if( doctorOrderDetailsList != null && !doctorOrderDetailsList.isEmpty() ){
					for( DoctorOrderDetails orderDetails : doctorOrderDetailsList ){
						
						DoctorOrderDetailsBM DoctorOrderDetailsBM = converter.convertDoctorOrderDetailsDM2BM( orderDetails );
						doctorOrderDetailsBMList.add( DoctorOrderDetailsBM );
					}
				}
				doctorOrderBM.setDoctorOrderDetailsList( doctorOrderDetailsBMList );
				doctorOrderBMList.add( doctorOrderBM );
			}
		}

		ListRange listRange = new ListRange();
		
		List<DoctorOrderBM> pageSizeData = new ArrayList<DoctorOrderBM> ();
		int index = 0;
		if (doctorOrderBMList != null && doctorOrderBMList.size() > 0) {
		while( (start+index < start + count) && (doctorOrderBMList.size() > start+index) ){
			
			DoctorOrderBM doctorOrderBM = doctorOrderBMList.get(start+index);
			doctorOrderBM.setSeqNbr( start + index +1 );
			pageSizeData.add( doctorOrderBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(doctorOrderBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
	}

	/* (non-Javadoc)
	 * 1) For a given order number, retrieve order details
	 * 2) If an order has service id populated on it then return corresponding service details
	 * 
	 * Service ID need not be mandatory for doctor order details. Some of the orders may
	 * just be standing instruction to nurse for some specific care. 
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#getServicesAssociatedWithOrder(java.lang.Integer)
	 */
	public List<ServiceBM> getServicesAssociatedWithOrder( Integer doctorOrderNbr ) {
		
		try {
			List<DoctorOrderDetails>doctorOrderDetailsList = doctorOrderDetailsDAO.findByProperty("id.orderNbr", doctorOrderNbr );
			
			List<ServiceBM>serviceBMList = null;
			
			if ( doctorOrderDetailsList != null && !doctorOrderDetailsList.isEmpty() ) {
				for ( DoctorOrderDetails doctorOrderDetails : doctorOrderDetailsList ) {
					if ( doctorOrderDetails.getServiceId() != null && doctorOrderDetails.getServiceId().length() > 0 ) {
						if ( serviceBMList == null ) {
							serviceBMList = new ArrayList<ServiceBM>(0);
						}
						
						Service service = serviceDAO.findById( doctorOrderDetails.getServiceId() );
						
						if ( service != null ) {
							ServiceBM serviceBM = new ServiceBM();
//							serviceBM.setAccountNumber( service.getAccountNumber() );
							Department department = service.getDepartment();
							serviceBM.setDepartment( new CodeAndDescription( department.getDepartmentCode(), department.getDepartmentName() ) );
							serviceBM.setServiceCharge( service.getServiceCharge() );
							serviceBM.setServiceCode( service.getServiceCode() );
							serviceBM.setServiceName( service.getServiceName() );
							
							ServiceStatus serviceStatus = service.getServiceStatus();
							serviceBM.setServiceStatus( new CodeAndDescription( serviceStatus.getServiceStatusCode(), serviceStatus.getDescription() ) );
							
							serviceBMList.add( serviceBM );
						} else {
							throw new Exception( "Service with SERVICE_ID = " + doctorOrderDetails.getServiceId() + " does not exist. " );
						}
					}
				}
			}
			
			return serviceBMList;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_SERVICES_WITH_ORDER_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}
	
	public List<CodeAndDescription> getOrderTypes() {
		List<DoctorOrderType>orderTypeList = doctorOrderTypeDAO.findAll();
		
		if ( orderTypeList != null && !orderTypeList.isEmpty() ) {
			List<CodeAndDescription>orderTypesBMList = new ArrayList<CodeAndDescription>(0);
			
			for ( DoctorOrderType doctorOrderType : orderTypeList ) {
				orderTypesBMList.add( new CodeAndDescription( doctorOrderType.getOrderTypeCd(), doctorOrderType.getOrderTypeDesc() ) );
			}
			
			return orderTypesBMList;
		} else {
			return null;
		}
	}
	
	public CodeAndDescription modifyDoctorOrderType( CodeAndDescription doctorOrderTypeBM ) {
		
		DoctorOrderType doctorOrderType = dataModelManager.getDoctorOrderType( doctorOrderTypeBM.getCode() );
		doctorOrderType.setOrderTypeDesc( doctorOrderTypeBM.getDescription() );
		doctorOrderTypeDAO.attachDirty( doctorOrderType );
		
		return doctorOrderTypeBM;
	}

	
	public DoctorOrderGroupBM modifyOrderGroup( DoctorOrderGroupBM doctorOrderGroupBM ) {
		
		try {
			DoctorOrderGroup existingOrderGroup = dataModelManager.getDoctorOrderGroup(doctorOrderGroupBM.getOrderGroupName());
			
			DoctorOrderGroup doctorOrderGroup = converter.convertDoctorOrderGroupBM2DM(doctorOrderGroupBM, existingOrderGroup );
			
			doctorOrderGroupDAO.attachDirty(doctorOrderGroup);
			
			List<DoctorOrderGroupHasTemplate>groupHasTemplateList = doctorOrderGroupHasTemplateDAO.findByProperty("id.orderGroupName", doctorOrderGroupBM.getOrderGroupName() );
			
			if ( groupHasTemplateList != null && !groupHasTemplateList.isEmpty() ) {
				for ( DoctorOrderGroupHasTemplate doctorOrderGroupHasTemplate : groupHasTemplateList ) {
					doctorOrderGroupHasTemplateDAO.delete( doctorOrderGroupHasTemplate );
				}
			}
			
			List<OrderGroupTemplateAssociationBM>orderGroupTemplateList = doctorOrderGroupBM.getOrderGroupTemplateAssociationList();
			
			if ( orderGroupTemplateList != null && !orderGroupTemplateList.isEmpty() ) {
				for ( OrderGroupTemplateAssociationBM groupTemplateAssociationBM : orderGroupTemplateList ) {
					groupTemplateAssociationBM.setOrderGroupName( doctorOrderGroupBM.getOrderGroupName() );
					DoctorOrderGroupHasTemplate doctorOrderGroupHasTemplate = converter.convertDoctorOrderGroupHasTemplateBM2DM(groupTemplateAssociationBM, null);
					
					doctorOrderGroupHasTemplateDAO.save( doctorOrderGroupHasTemplate );
				}
			}
			
			return converter.convertDoctorOrderGroupDM2BM(doctorOrderGroup);
		
		} catch (HCISException e) {
			Fault fault = new Fault( ApplicationErrors.MODIFY_ORDER_GROUP_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	public OrderTemplateBM modifyOrderTemplate( OrderTemplateBM orderTemplateBM ) {
		
		try {
			DoctorOrderTemplate existingTemplate = dataModelManager.getDoctorOrderTemplate( orderTemplateBM.getTemplateId() );
			
			DoctorOrderTemplate doctorOrderTemplate = converter.convertDoctorOrderTemplateBM2DM(orderTemplateBM, existingTemplate );
			doctorOrderTemplateDAO.attachDirty( doctorOrderTemplate );
	
	//Delete DoctorOrder Template Details if any			
			List<DoctorOrderTemplateDetails> doctorOrderTemplateDetailsList = doctorOrderTemplateDetailsDAO.findByProperty("id.templateId", doctorOrderTemplate.getTemplateId());
			
			if ( doctorOrderTemplateDetailsList != null && !doctorOrderTemplateDetailsList.isEmpty() ){
				
				for( DoctorOrderTemplateDetails orderTemplateDetails : doctorOrderTemplateDetailsList ){
					
					doctorOrderTemplateDetailsDAO.delete( orderTemplateDetails );
				}
			}	
			
			List<OrderTemplateDetailsBM> orderTemplateDetailsBMList = orderTemplateBM.getOrderTemplateDetailsList();
			
			if( orderTemplateDetailsBMList != null && !orderTemplateDetailsBMList.isEmpty()){
	
				for(OrderTemplateDetailsBM orderTemplateDetailsBM : orderTemplateDetailsBMList){
					
					orderTemplateDetailsBM.setTemplateId( doctorOrderTemplate.getTemplateId() );
					DoctorOrderTemplateDetails doctorOrderTemplateDetails = converter.convertDoctorOrderTemplateDetailsBM2DM(orderTemplateDetailsBM,null);
					doctorOrderTemplateDetailsDAO.save(doctorOrderTemplateDetails);
				}
			}
			
			return converter.convertDoctorOrderTemplateDM2BM( doctorOrderTemplate );
			
		} catch (HCISException e) {
			Fault fault = new Fault( ApplicationErrors.MODIFY_ORDER_TEMPLATE_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	public boolean removeOrderGroup( String doctorOrderGroupName ) {
		
		try {
			DoctorOrderGroup doctorOrderGroup = dataModelManager.getDoctorOrderGroup(doctorOrderGroupName);
			
			List<DoctorOrderGroupHasTemplate>groupHasTemplateList = doctorOrderGroupHasTemplateDAO.findByProperty( "id.orderGroupName", doctorOrderGroupName );
			
			if ( groupHasTemplateList != null && !groupHasTemplateList.isEmpty() ) {
				for ( DoctorOrderGroupHasTemplate doctorOrderGroupHasTemplate : groupHasTemplateList ) {
					doctorOrderGroupHasTemplateDAO.delete( doctorOrderGroupHasTemplate );
				}
			}
			
			doctorOrderGroupDAO.delete( doctorOrderGroup );
			//Code to interact with the database this moment immediately; which is not being done because of transaction boundaries.  
			doctorOrderGroupDAO.getSessionFactory().getCurrentSession().flush(); 
			
			return true;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_DOCTOR_ORDER_GROUP_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

/**
 * This method removes the Doctor Order group for given group name list
 * @param doctorOrderGroupNameList
 */

	public boolean removeOrderGroupList( List<String> doctorOrderGroupNameList ){
		
		try {
			if( doctorOrderGroupNameList!= null && !doctorOrderGroupNameList.isEmpty() ){
				for( String doctorOrderGroupName : doctorOrderGroupNameList ){
					this.removeOrderGroup(doctorOrderGroupName);
				}
				return true;
			}
		} catch (HCISException e) {
			throw e;
		}
		return false;
	}



	/**
	 * This method removes Doctor order template and related details.
	 * 
	 * If it fails to remove Template from database (most probably because of foreign key relationship or child record exist )
	 * then it only marks the active status of the template as 'N'.
	 * 
	 * At any case it removes the template details (as it is not being referred any where )
	 */
	public boolean removeOrderTemplate(String templateId) {
		
	 if( templateId != null && !templateId.isEmpty() ){
			DoctorOrderTemplate doctorOrderTemplate =  dataModelManager.getDoctorOrderTemplate(templateId);
	
		try {
			List<DoctorOrderTemplateDetails> doctorOrderTemplateDetailsList = doctorOrderTemplateDetailsDAO.findByProperty("id.templateId", templateId);
			
			if ( doctorOrderTemplateDetailsList != null && !doctorOrderTemplateDetailsList.isEmpty() ){
				
				for( DoctorOrderTemplateDetails orderTemplateDetails : doctorOrderTemplateDetailsList ){
					
					doctorOrderTemplateDetailsDAO.delete( orderTemplateDetails );
				}
			}
			
			doctorOrderTemplateDAO.delete(doctorOrderTemplate);
			//Code to interact with the database this moment immediately; which is not being done because of transaction boundaries.  
			doctorOrderTemplateDAO.getSessionFactory().getCurrentSession().flush(); 
			return true;
			
		} catch (Exception e) {
			
			try{
				doctorOrderTemplate.setActiveFlag( ApplicationConstants.ACTIVE_FLAG_NO );
				doctorOrderTemplateDAO.attachDirty( doctorOrderTemplate );
			}catch (Exception ex) {
				Fault fault = new Fault( ApplicationErrors.REMOVE_ORDER_TEMPLATE_FAILED );
				HCISException hcisException = new HCISException(fault, ex);
				throw hcisException;
			}
		}
		return true;
	 }else{
		 
		 return false;
	 }
	}
	/**
	 * This method removes the Templates of given Ids
	 * @param templateIdList
	 */
	
	public boolean removeOrderTemplateList( List<String> templateIdList ){
		
		try {
			if( templateIdList!= null && !templateIdList.isEmpty() ){
				for( String templateId : templateIdList ){
					this.removeOrderTemplate(templateId);
				}
				return true;
			}
		} catch (Exception ex) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_ORDER_TEMPLATE_FAILED );
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
		return false;
	}

/**
 * This method deletes the doctor order entry/entries form the system only when it has only one activity record (assuming that it has never been used),
 * and the doctor order or its detail is in "CREATED" status. Throw exception in other cases.
 * 
 * Method returns true in case of any successful deletion, false otherwise.   
 */	
	
	public boolean removeDoctorOrderList(Integer[] doctorOrderNbrList) {

		try {
			boolean isDeleted = false;
			if( doctorOrderNbrList != null && doctorOrderNbrList.length > 0 ){
			for(Integer doctorOrderNbr : doctorOrderNbrList ){

			if( doctorOrderNbr != null  ){
				
				List<DoctorOrderActivity> orderActivityList = doctorOrderActivityDAO.findByProperty("id.doctorOrderNumber", doctorOrderNbr );
				
				if( orderActivityList != null && orderActivityList.size() == 1 ){
//				It means doctorOrder is just added and has only record for activity. So go ahead for deletion.
					
					doctorOrderActivityDAO.delete( orderActivityList.get(0));
					
					List<DoctorOrderDetails> doctorOrderDetailsList = doctorOrderDetailsDAO.findByProperty("id.orderNbr", doctorOrderNbr );
					if( doctorOrderDetailsList != null && !doctorOrderDetailsList.isEmpty() ){
					  for( DoctorOrderDetails doctorOrderDetails : doctorOrderDetailsList ){
							  
						  if( doctorOrderDetails.getActionStatus().getActionStatusCd().equals( DoctorOrderConstants.ACTION_STATUS_CREATED )){
							  doctorOrderDetailsDAO.delete(doctorOrderDetails);
						  }else{
								  
						  	Fault fault = new Fault( ApplicationErrors.REMOVE_DOCTOR_ORDER_FAILED );
							HCISException hcisException = new HCISException(fault.getFaultMessage() +
																			"'Doctor Order Detail(s) is in Use'",
																			fault.getFaultCode(),
																			fault.getFaultType());
							throw hcisException;
						  }
					  	}
					 }
					 DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( doctorOrderNbr );
					 if( doctorOrder.getDoctorOrderStatus().getOrderStatusCd().equals( DoctorOrderConstants.ACTION_STATUS_CREATED  )){
						 doctorOrderDAO.delete( doctorOrder );
						 isDeleted = true;
					 }else{
						 Fault fault = new Fault( ApplicationErrors.REMOVE_DOCTOR_ORDER_FAILED );
						 HCISException hcisException = new HCISException(fault.getFaultMessage() +
																		"'Doctor Order with Nbr = "+ doctorOrderNbr + "is in Use'",
																		fault.getFaultCode(),
																		fault.getFaultType());
						throw hcisException;
					  }
					}
				}
			}
		}
		return isDeleted;
	} catch (Exception e) {
		Fault fault = new Fault( ApplicationErrors.REMOVE_DOCTOR_ORDER_FAILED );
		HCISException hcisException = new HCISException(fault);
		throw hcisException;
	} 
  }

	public boolean removeDoctorOrderType(List<CodeAndDescription> doctorOrderTypeList) {
		
		if ( doctorOrderTypeList != null ) {
			for ( CodeAndDescription orderType : doctorOrderTypeList ) {
				DoctorOrderType doctorOrderType = dataModelManager.getDoctorOrderType( orderType.getCode() );
				
				doctorOrderTypeDAO.delete( doctorOrderType );
			}
			
			return true;
			
		} else {
			return false;
		}
	}
	
	/* This method gives the name of the patient corresponding to
	* admission number.
	* Implemented only for front end (Create Doctor Order)
	*/
	public CodeAndDescription getAdmittedPatient(Integer admissionNbr){

	try {
		if(admissionNbr != null){
			Admission admission = dataModelManager.getAdmission(admissionNbr);
			if(admission != null){
				PatientLiteBM patinetLiteBM = patientManager.getPatientLiteBM( admission.getPatientId() );
				if(patinetLiteBM != null && patinetLiteBM.getPatientId() != null){
					String patientName = "";
					if(patinetLiteBM.getFirstName() != null && patinetLiteBM.getFirstName().length() > 0){
						patientName = patinetLiteBM.getFirstName()+ " ";
					}
					if(patinetLiteBM.getMiddleName() != null && patinetLiteBM.getMiddleName().length() > 0){
						patientName = patientName + patinetLiteBM.getMiddleName()+" ";
					}
					if(patinetLiteBM.getLastName() != null && patinetLiteBM.getLastName().length() > 0){
						patientName = patientName + patinetLiteBM.getLastName();
					}
				
					CodeAndDescription patientCodeDesc = new CodeAndDescription();
					patientCodeDesc.setCode(patinetLiteBM.getPatientId().toString());
					patientCodeDesc.setDescription(patientName);
					return patientCodeDesc;
				}
			}
		}
	} catch (HCISException e) {
	e.printStackTrace();
	}
	return null;

	}
	
	/**
	 * This method returns ListRange object containing doctorOrderGroup objects which satisfies the search criteria 
	 * all parameters are optional  
	 */
	public ListRange findDoctorOrderGroups(  String orderGroupName,
											 String  orderTemplateId,
											 Integer doctorId ,
											 Date createdFromDate,
											 Date createdToDate,
											 int start,  int count,  String orderBy) throws HCISException{
		
		List<DoctorOrderGroup>  doctorOrderGroupList = doctorOrderGroupDAO.findDoctorOrderGroups( orderGroupName, orderTemplateId,
																								  doctorId, createdFromDate, createdToDate);
		
		List<DoctorOrderGroupBM> doctorOrderGroupBMList = new ArrayList<DoctorOrderGroupBM>(0);
		
		
		if( doctorOrderGroupList != null && !doctorOrderGroupList.isEmpty() ){
			
			for ( DoctorOrderGroup doctorOrderGroup : doctorOrderGroupList ) {
				
				DoctorOrderGroupBM orderGroupBM = 	converter.convertDoctorOrderGroupDM2BM( doctorOrderGroup  );

				// Find association with DoctorOrderTemplate 				
				List<DoctorOrderGroupHasTemplate> groupHasTemplateList = 
										doctorOrderGroupHasTemplateDAO.findByProperty("id.orderGroupName", doctorOrderGroup.getOrderGroupName() );
				
				if( groupHasTemplateList != null  && !groupHasTemplateList.isEmpty() ){
		
					List<OrderGroupTemplateAssociationBM> orderGroupTemplateAssociationBMList = new ArrayList<OrderGroupTemplateAssociationBM>(0);

					for ( DoctorOrderGroupHasTemplate groupHasTemplate : groupHasTemplateList ){
						
						 orderGroupTemplateAssociationBMList.add( converter.convertDoctorOrderGroupHasTemplateDM2BM( groupHasTemplate ) );
					}
				
					orderGroupBM.setOrderGroupTemplateAssociationList(orderGroupTemplateAssociationBMList);
				}						
					
				doctorOrderGroupBMList.add( orderGroupBM );
			 
			}
		}
			
		ListRange listRange = new ListRange();
		
		List<DoctorOrderGroupBM> pageSizeData = new ArrayList<DoctorOrderGroupBM>();
		int index = 0;
		if (doctorOrderGroupBMList != null && doctorOrderGroupBMList.size() > 0) {
		while( (start+index < start + count) && (doctorOrderGroupBMList.size() > start+index) ){
			
			DoctorOrderGroupBM doctorOrderGroupBM = doctorOrderGroupBMList.get(start+index);
			doctorOrderGroupBM.setSeqNbr( start + index +1 );
			
			pageSizeData.add(doctorOrderGroupBM);
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(doctorOrderGroupBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
		
	}

	public List<OrderTemplateBM> getDoctorOrderTemplates( String templateId, String orderTypeCd,
														  Integer doctorId, String serviceId,
												          String departmentId ){
		List<DoctorOrderTemplate> doctorOrderTemplateList = doctorOrderTemplateDAO.findDoctorOrderTemplates(templateId, orderTypeCd, 
				doctorId, serviceId, departmentId);

		List<OrderTemplateBM> orderTemplateBMList = new ArrayList<OrderTemplateBM>(0);
		
		if( doctorOrderTemplateList != null && !doctorOrderTemplateList.isEmpty() ){
		
				OrderTemplateBM orderTemplateBM = null;
				for( DoctorOrderTemplate doctorOrderTemplate : doctorOrderTemplateList ){
					orderTemplateBM = converter.convertDoctorOrderTemplateDM2BM(doctorOrderTemplate);
					
					List<DoctorOrderTemplateDetails> orderTemplateDetailsList = doctorOrderTemplateDetailsDAO.findByProperty("id.templateId", doctorOrderTemplate.getTemplateId());
					
					if( orderTemplateDetailsList != null && !orderTemplateDetailsList.isEmpty() ){
						List<OrderTemplateDetailsBM> orderTemplateDetailsBMList = new ArrayList<OrderTemplateDetailsBM>(0);
						
						for( DoctorOrderTemplateDetails orderTemplateDetails : orderTemplateDetailsList ){
						
							orderTemplateDetailsBMList.add(converter.convertDoctorOrderTemplateDetailsDM2BM(orderTemplateDetails));
						}
						orderTemplateBM.setOrderTemplateDetailsList(orderTemplateDetailsBMList);
					}
					
					orderTemplateBMList.add(orderTemplateBM);
				}
			}
		return orderTemplateBMList;
		
	}
	
	public OrderTemplateBM getOrderTemplateForId( String templateId ) throws Exception{
		
		List<OrderTemplateBM> orderTemplateBMList = this.getDoctorOrderTemplates(templateId, null, null, null, null);
		
		if( orderTemplateBMList != null && !orderTemplateBMList.isEmpty()){
			return orderTemplateBMList.get(0);
		}else{
//			throw new Exception( " Order Template with TemplateId = " + templateId + " does not exist. " );
			return null;
		}
		
	}
	
	/**
	 * This method returns ListRange object containing DoctorOrderTemplates objects which satisfies the search criteria. 
	 * All parameters are optional.  
	 */
	
	public ListRange findDoctorOrderTemplates(	String templateId, String orderTypeCd,
												Integer doctorId, String serviceId,
												String departmentId, int start, int count, String orderBy) throws HCISException {
		
		List<OrderTemplateBM> orderTemplateBMList = this.getDoctorOrderTemplates(templateId, orderTypeCd,
																				 doctorId, serviceId, departmentId);
		
		ListRange listRange = new ListRange();
		
		List<OrderTemplateBM> pageSizeData = new ArrayList<OrderTemplateBM>();
		int index = 0;
		if (orderTemplateBMList != null && orderTemplateBMList.size() > 0) {
		while( (start+index < start + count) && (orderTemplateBMList.size() > start+index) ){
			
			OrderTemplateBM templateBM = orderTemplateBMList.get(start+index);
			templateBM.setSeqNbr( start + index +1 );
			pageSizeData.add( templateBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(orderTemplateBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}
	
	/**
	 * This is a UI specific method which return all the attribute value BM in ascending order 
	 */
	public List<OrderTypeAttributeAssociationBM> getOrderTypeAttributeBMForGroup( String orderGroupName){
		
		DoctorOrderGroup doctorOrderGroup = dataModelManager.getDoctorOrderGroup(orderGroupName );
		
	
		
		Set<DoctorOrderGroupHasTemplate> doctorOrderHasTemplateList = doctorOrderGroup.getDoctorOrderGroupHasTemplates();
		
		List<OrderTypeAttributeAssociationBM > retBMList = new ArrayList<OrderTypeAttributeAssociationBM>(0);
		
		if( doctorOrderHasTemplateList != null && !doctorOrderHasTemplateList.isEmpty() ){
			
			Set<String> addedOrderTypeCodeList = new HashSet<String>(0);

			for( DoctorOrderGroupHasTemplate orderHasTemplate : doctorOrderHasTemplateList ){
				
				List<OrderTypeAttributeAssociationBM> orderTypeAttrBMList = null;
				String orderType = orderHasTemplate.getDoctorOrderTemplate().
									getDoctorOrderType().getOrderTypeCd();
				
				if( !addedOrderTypeCodeList.contains( orderType)){

					orderTypeAttrBMList = this.getOrderTypeHasAttributesBM(orderType);
					
					//Add the orderName to list
					addedOrderTypeCodeList.add( orderType );

					if( orderTypeAttrBMList != null ){

						for(  OrderTypeAttributeAssociationBM tmpBM: orderTypeAttrBMList ){
							retBMList.add( tmpBM );
						}
					}
				}
					
			}
		}
		return retBMList;
		
	}
	
	public ListRange getOrderTypeHasAttribute( String orderTypeCd ,int start, int count, String orderBy ) throws HCISException{
		

		List<OrderTypeAttributeAssociationBM> orderTypeAttributeAssociationBMList = this.getOrderTypeHasAttributesBM( orderTypeCd );
		ListRange listRange = new ListRange();
		
		List<OrderTypeAttributeAssociationBM> pageSizeData = new ArrayList<OrderTypeAttributeAssociationBM>();
		int index = 0;
		if (orderTypeAttributeAssociationBMList != null && orderTypeAttributeAssociationBMList.size() > 0) {
		while( (start+index < start + count) && (orderTypeAttributeAssociationBMList.size() > start+index) ){
			
			OrderTypeAttributeAssociationBM associationBM = orderTypeAttributeAssociationBMList.get(start+index);
			pageSizeData.add( associationBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(orderTypeAttributeAssociationBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}

	
	private List<OrderTypeAttributeAssociationBM> getOrderTypeHasAttributesBM(String orderTypeCd){
		
		try {
		List<OrderTypeAttributeAssociationBM> orderTypeAttributeAssociationBMList = new ArrayList<OrderTypeAttributeAssociationBM>(0);
		if (orderTypeCd != null && !orderTypeCd.isEmpty()) {

			
			DetachedCriteria orderTypeAttributesCriteria = DetachedCriteria.forClass( OrderTypeHasAttributes.class );
			orderTypeAttributesCriteria.add( Restrictions.eq("id.orderTypeCd", orderTypeCd)).addOrder(Order.asc("sequenceNumber"));
			
//			List<OrderTypeHasAttributes> orderTypeHasAttributesList = orderTypeHasAttributesDAO.findByProperty("id.orderTypeCd", orderTypeCd);
			
			List<OrderTypeHasAttributes> orderTypeHasAttributesList = orderTypeHasAttributesDAO.getHibernateTemplate().findByCriteria(orderTypeAttributesCriteria);
			
			if (orderTypeHasAttributesList != null
					&& !orderTypeHasAttributesList.isEmpty()) {

				for (OrderTypeHasAttributes orderTypeHasAttributes : orderTypeHasAttributesList) {

					OrderTypeAttributeAssociationBM attributeAssociationBM = new OrderTypeAttributeAssociationBM();

					Attribute attribute = orderTypeHasAttributes.getAttribute();

					if (attribute != null) {
						attributeAssociationBM.setAttributeName(attribute.getName());
						attributeAssociationBM.setAttributeDesc(attribute.getDescription());
						attributeAssociationBM.setType(attribute.getType());
						attributeAssociationBM.setProvider(attribute.getProvider());
						attributeAssociationBM.setIsMandatory(orderTypeHasAttributes.getIsMandatory());
						attributeAssociationBM.setSeqNbr(orderTypeHasAttributes.getSequenceNumber() );
						attributeAssociationBM.setOrderType( new CodeAndDescription(orderTypeHasAttributes.getId().getOrderTypeCd(),
																					orderTypeHasAttributes.getId().getOrderTypeCd()));
						if( attribute.getType()!= null && attribute.getType().equals( DoctorOrderConstants.ATTRIBUTE_TYPE_MVL) ){

							if( attribute.getProvider() != null && !attribute.getProvider().isEmpty() ){
//			Use reflexion to invoke the method 
//			Here we have assumption that 'provider' contains QualifiedName (without argument list as it always should be
//			int,int,String) of the	provider method and bean name in the Spring configuration file is same as the class name.
								
								int lastIndex = attribute.getProvider().lastIndexOf(".");
								String className = attribute.getProvider().substring(0, lastIndex);
								String methodName = attribute.getProvider().substring( lastIndex +1 );
								String beanName = className.substring(className.lastIndexOf(".") + 1);
									 Class cl=Class.forName( className );
									 Class[] param = new Class[3];
									 param[0] = int.class;
									 param[1] = int.class;
									 param[2] = String.class;
								
									  Method method = cl.getMethod( methodName , param);
									
									ListRange listRange = (ListRange) method.invoke( applicationContext.getBean( beanName ),new Integer(0),new Integer(9999),"");
									attributeAssociationBM.setMVLvalueList(new ArrayList(Arrays.asList( listRange.getData() )));
							
									
							}
						}
						orderTypeAttributeAssociationBMList.add(attributeAssociationBM);
					}
				}
			}
		}
		
		return orderTypeAttributeAssociationBMList;

		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ATTRIBUTE_FAILED );
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	/**
	 * This method is for UI purpose in case of creating Doctor Order for
	 * Individual case in Create Doctor Order Window
	 * @param doctorOrderBM
	 */
	
	public void createDoctorOrderIndividualSelection( DoctorOrderBM doctorOrderBM ) throws HCISException{
		try {
			createDoctorOrder( doctorOrderBM );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<DoctorOrderDetailsBM> getOrderLineItems(Integer doctorOrderNbr) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrderTemplateBM> getOrderTemplatesForGroup( String orderGroupName ) {
		
		DetachedCriteria orderGroupCriteria = DetachedCriteria.forClass( DoctorOrderGroupHasTemplate.class );
		orderGroupCriteria.add( Restrictions.eq("id.orderGroupName", orderGroupName)).addOrder(Order.asc("id.sequenceNbr"));
     	List<DoctorOrderGroupHasTemplate> groupTemplateList = doctorOrderGroupHasTemplateDAO.getHibernateTemplate().findByCriteria(orderGroupCriteria);
		
     	List<OrderTemplateBM> orderTemplateBMList = new ArrayList<OrderTemplateBM>(0);
     	
     	
     	if( groupTemplateList != null && !groupTemplateList.isEmpty() ){
     		for( DoctorOrderGroupHasTemplate groupHasTemplate : groupTemplateList ){
     		
     			List<OrderTemplateBM> cuttentOrderTemplateBMList = this.getDoctorOrderTemplates(groupHasTemplate.getId().getOrderTemplateId(),
     																	                 null, null, null, null);
     			orderTemplateBMList.addAll( cuttentOrderTemplateBMList );
     		}
     	}
     	return orderTemplateBMList;
	}

	public List<DoctorOrderDetailsBM> getOrderDetailsForTemplate( String TemplateId) {
		
		DoctorOrderTemplate doctorOrderTemplate = doctorOrderTemplateDAO.findById( TemplateId );
		DoctorOrderType orderType = doctorOrderTemplate.getDoctorOrderType();
		
		List<DoctorOrderTemplateDetails> orderTemplateDetailsList = doctorOrderTemplateDetailsDAO.findByProperty("id.templateId", TemplateId );
		
		List<DoctorOrderDetailsBM> doctorOrderDetailsBMList =  new ArrayList<DoctorOrderDetailsBM>();
		
		
		if( orderTemplateDetailsList != null && !orderTemplateDetailsList.isEmpty() ){
			for( DoctorOrderTemplateDetails orderTemplateDetails : orderTemplateDetailsList ){
				
				DoctorOrderDetailsBM orderDetailsBM = new DoctorOrderDetailsBM();
				
				orderDetailsBM.setOrderType(new CodeAndDescription(orderType.getOrderTypeCd(),orderType.getOrderTypeDesc()) );
				
				if( orderTemplateDetails.getResponsibleDepartmentId() != null && !orderTemplateDetails.getResponsibleDepartmentId().isEmpty()){
					Department department = baseDataModelManager.getDepartment( orderTemplateDetails.getResponsibleDepartmentId());
					if( department!= null ){
						orderDetailsBM.setResponsibleDepartment( new CodeAndDescription(department.getDepartmentCode(),department.getDepartmentName()));						
					}
				}
				orderDetailsBM.setSequenceNbr( orderTemplateDetails.getId().getSequenceNbr() );
				orderDetailsBM.setSubSequenceNbr( orderTemplateDetails.getId().getSubSequenceNbr() );
				
				if( orderTemplateDetails.getServiceId() != null && !orderTemplateDetails.getServiceId().isEmpty() ){
					Service service = baseDataModelManager.getServiceByCode( orderTemplateDetails.getServiceId() );
					orderDetailsBM.setServiceCode( service.getServiceCode() );
					orderDetailsBM.setServiceName( service.getServiceName() );
				}
				
				
				doctorOrderDetailsBMList.add( orderDetailsBM );
			}
		}
		return doctorOrderDetailsBMList;
	}

	private AssignSvcAndPackageBM getAssignedServiceBm (DoctorOrder doctorOrder){
		
		List<DoctorOrderDetails> doctorOrderDetailsList = doctorOrderDetailsDAO.findByProperty("id.orderNbr", doctorOrder.getDoctorOrderNbr());
		
		
		AssignSvcAndPackageBM assignSvcAndPackageBM = new AssignSvcAndPackageBM();
		List<AssignedPackageBM> assignedPackageBMList = new ArrayList<AssignedPackageBM>(0);
		List<AssignedServiceBM> assignedServiceBMList = new ArrayList<AssignedServiceBM>(0);
		
//			List<DoctorOrderDetailsBM> doctorOrderDetailsBMList = doctorOrderBM.getDoctorOrderDetailsList();
			
			if(doctorOrderDetailsList!=null && doctorOrderDetailsList.size()>0){
				Iterator<DoctorOrderDetails> doctorOrderDetailIter = doctorOrderDetailsList.iterator();
				while (doctorOrderDetailIter.hasNext()) {
					
					DoctorOrderDetails doctorOrderDetails = doctorOrderDetailIter.next();
					DoctorOrderDetailsBM doctorOrderDetailsBM = converter.convertDoctorOrderDetailsDM2BM( doctorOrderDetails);
					if( doctorOrderDetails.getServiceId() != null || doctorOrderDetails.getPackageId() != null){
						
						if (doctorOrderDetailsBM.getPackageIndicator()!=null) {
							if(doctorOrderDetailsBM.getPackageIndicator().equals("Y")){
								AssignedPackageBM assignedPackageBM = new AssignedPackageBM();
								assignedPackageBM.setServicePackage(doctorOrderDetailsBM.getServicePackage());
								assignedPackageBM.setReferenceNumber(doctorOrder.getAdmission().getAdmissionReqNbr().toString());
								assignedPackageBM.setDoctorId(doctorOrder.getDoctorId());
								assignedPackageBM.setPatientId(doctorOrder.getPatientId());
								assignedPackageBM.setReferenceType(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD);
								assignedPackageBMList.add(assignedPackageBM);
								
							}else {
								AssignedServiceBM assignedServiceBM = new AssignedServiceBM();
								CodeAndDescription service = new CodeAndDescription();
								service.setCode(doctorOrderDetailsBM.getServiceCode());
								service.setDescription(doctorOrderDetailsBM.getServiceName());
								assignedServiceBM.setService(service);
								assignedServiceBM.setDoctorId(doctorOrder.getDoctorId());
								assignedServiceBM.setPatientId(doctorOrder.getPatientId());
								assignedServiceBM.setReferenceNumber(doctorOrder.getAdmission().getAdmissionReqNbr().toString());
								assignedServiceBM.setReferenceType(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD);
								
								assignedServiceBMList.add(assignedServiceBM);
							}
						}
					}
			}	
			assignSvcAndPackageBM.setDoctorId(doctorOrder.getDoctorId());
			assignSvcAndPackageBM.setPatientId(doctorOrder.getPatientId());
			assignSvcAndPackageBM.setReferenceNumber(doctorOrder.getAdmission().getAdmissionReqNbr().toString());
			assignSvcAndPackageBM.setReferenceType(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD);
			assignSvcAndPackageBM.setAssignedPackageBMList(assignedPackageBMList);
			assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
		}
		
		return assignSvcAndPackageBM;
		
	}
	
	/**
	 * This method check the status transition of Order status
	 * Throws exception in case of invalid exception. 
	 * -------------------------------------------------------
	 * 	Old Status					New Status
	 * -------------------------------------------------------
	 * CREATED						APPROVED, DISAPPROVED, CANCELED	
	 * APPROVED						COMPLETED
	 * COMPLETED						-
	 * DISAPPROVED						-
	 * CANCELED		
	 * -------------------------------------------------------
	 * 
	 */
	
	private boolean isValidOrderStatusTransition(String oldStatus, String newStatus){
		
		if( oldStatus.equals(newStatus)){
			
			return true;
		}
		
		if( DoctorOrderConstants.ORDER_STATUS_CREATED.equals( oldStatus )&& 
			(DoctorOrderConstants.ORDER_STATUS_APPROVED.equals(newStatus) ||
			 DoctorOrderConstants.ORDER_STATUS_DISAPPROVED.equals(newStatus) ||
			 DoctorOrderConstants.ORDER_STATUS_CANCELED.equals( newStatus))){
			
			return true;
		}else if(DoctorOrderConstants.ORDER_STATUS_APPROVED.equals( oldStatus ) &&
				 DoctorOrderConstants.ORDER_STATUS_COMPLETED.equals( newStatus )){
			
			return true;
		}
		
		Fault fault = new Fault( in.wtc.hcis.bo.constants.ApplicationErrors.INVALID_STATUS_TRANSITION);
		throw new HCISException( fault.getFaultMessage() +  "From '" + oldStatus + "' to '"+ newStatus,
							     fault.getFaultCode(),fault.getFaultType());
	}

  public String getOrderRemarks( Integer orderNumber, String statusTypeCd){
	   
	  try {
		DetachedCriteria criteria = DetachedCriteria.forClass( DoctorOrderActivity.class);
		  String remarks = null;
		  
		  criteria.add( Restrictions.eq("id.doctorOrderNumber", orderNumber))
		  		  .add( Restrictions.eq("doctorOrderStatus.orderStatusCd", statusTypeCd))
		  		  .addOrder(Order.desc("id.activityDtm"));
		  
		  List<DoctorOrderActivity> orderActivityList =  doctorOrderActivityDAO.getHibernateTemplate()
		  												.findByCriteria(criteria);
		  
		  if( orderActivityList != null && !orderActivityList.isEmpty() ){
			  
			  remarks = orderActivityList.get(0).getRemarks();
		  }
		  return remarks;
	} catch (DataAccessException e) {
		Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_FAILED);
		throw new HCISException( fault,e);
	}
  }
  
  public String getDishcargeSummary( Integer admissionReqNbr){
	  
	  try {
		String summary = null;
		  DischargeOrder dischargeOrder = dataModelManager.getDischargeOrder(admissionReqNbr);
		  
		  if( dischargeOrder.getDischargeSummary() != null &&
			  !dischargeOrder.getDischargeSummary().isEmpty()){
			  
			  summary = dischargeOrder.getDischargeSummary();
		  }
		  
		  return summary;
	} catch (HCISException e) {
		Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_FAILED);
		throw new HCISException( fault,e);
	
	}
  }
  
  public void saveDishcargeSummary( Integer admissionReqNbr, String summary , String personId ){
	  
	  try {
		DischargeOrder dischargeOrder = dataModelManager.getDischargeOrder(admissionReqNbr);
		  
		  DoctorOrderStatus currentStatus = dischargeOrder.getDoctorOrderStatus(); 
		  
		  if( DoctorOrderConstants.ORDER_STATUS_CANCELED.equals(currentStatus.getOrderStatusCd())
			  || DoctorOrderConstants.ORDER_STATUS_COMPLETED.equals(currentStatus.getOrderStatusCd())){
			
			  throw new Exception(" Order is in " +currentStatus.getStatusDesc()+ " status.");
		  }
		  
		  dischargeOrder.setDischargeSummary( summary);
		  dischargeOrder.setModifiedBy(personId);
		  dischargeOrder.setLastModifiedDtm( new Date() );
		  
		  dischargeOrderDAO.attachDirty(dischargeOrder);

	  } catch (Exception e) {
		
		Fault fault = new Fault( ApplicationErrors.MODIFY_DOCTOR_ORDER_FAILED);
		throw new HCISException( fault,e);
	}
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

	public void setDoctorOrderTypeDAO(DoctorOrderTypeDAO doctorOrderTypeDAO) {
		this.doctorOrderTypeDAO = doctorOrderTypeDAO;
	}

	public void setDoctorOrderTemplateDAO(
			DoctorOrderTemplateDAOExtn doctorOrderTemplateDAO) {
		this.doctorOrderTemplateDAO = doctorOrderTemplateDAO;
	}

	public void setDoctorOrderGroupDAO(DoctorOrderGroupDAOExtn doctorOrderGroupDAO) {
		this.doctorOrderGroupDAO = doctorOrderGroupDAO;
	}

	public void setDoctorOrderGroupHasTemplateDAO(
			DoctorOrderGroupHasTemplateDAO doctorOrderGroupHasTemplateDAO) {
		this.doctorOrderGroupHasTemplateDAO = doctorOrderGroupHasTemplateDAO;
	}

	public void setDoctorOrderDetailsDAO(DoctorOrderDetailsDAO doctorOrderDetailsDAO) {
		this.doctorOrderDetailsDAO = doctorOrderDetailsDAO;
	}

	public void setServiceDAO(ServiceDAOExtn serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public void setDoctorOrderDAO(DoctorOrderDAOExtn doctorOrderDAO) {
		this.doctorOrderDAO = doctorOrderDAO;
	}

	public void setDoctorOrderTemplateDetailsDAO(
			DoctorOrderTemplateDetailsDAO doctorOrderTemplateDetailsDAO) {
		this.doctorOrderTemplateDetailsDAO = doctorOrderTemplateDetailsDAO;
	}

	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public void setDoctorOrderActivityDAO(
			DoctorOrderActivityDAO doctorOrderActivityDAO) {
		this.doctorOrderActivityDAO = doctorOrderActivityDAO;
	}

	public void setOrderTypeHasAttributesDAO(
			OrderTypeHasAttributesDAO orderTypeHasAttributesDAO) {
		this.orderTypeHasAttributesDAO = orderTypeHasAttributesDAO;
	}

	public static void setDoctorOrderConfig(ResourceBundle doctorOrderConfig) {
		OrderManagerImpl.doctorOrderConfig = doctorOrderConfig;
	}

	public void setOrderAttributeValueDAO(
			OrderAttributeValueDAO orderAttributeValueDAO) {
		this.orderAttributeValueDAO = orderAttributeValueDAO;
	}

	public void setApplicationContext(ApplicationContext ctx )
			throws BeansException {
		this.applicationContext = ctx;
		
	}

	public void setAdmissionDAO(AdmissionDAOExtn admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setDischargeOrderDAO(DischargeOrderDAOExtn dischargeOrderDAO) {
		this.dischargeOrderDAO = dischargeOrderDAO;
	}

	public void setDischargeActivityDAO(DischargeActivityDAO dischargeActivityDAO) {
		this.dischargeActivityDAO = dischargeActivityDAO;
	}

	public void setBedManager(BedManager bedManager) {
		this.bedManager = bedManager;
	}

	public void setConfigurationManager(ConfigurationManager configurationManager) {
		this.configurationManager = configurationManager;
	}

		
}
