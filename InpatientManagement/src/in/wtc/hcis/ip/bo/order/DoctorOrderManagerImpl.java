/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bm.ip.DoctorOrderGroupBM;
import in.wtc.hcis.bm.ip.OrderGroupTemplateAssociationBM;
import in.wtc.hcis.bm.ip.OrderTemplateBM;
import in.wtc.hcis.bm.ip.OrderTemplateDetailsBM;
import in.wtc.hcis.bm.ip.OrderTemplateSummaryBM;
import in.wtc.hcis.bm.ip.OrderTypeAttributeAssociationBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceStatus;
import com.wtc.hcis.da.extn.ServiceDAOExtn;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.Attribute;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderActivity;
import com.wtc.hcis.ip.da.DoctorOrderActivityDAO;
import com.wtc.hcis.ip.da.DoctorOrderDetails;
import com.wtc.hcis.ip.da.DoctorOrderDetailsDAO;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplate;
import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplateDAO;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetails;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDetailsDAO;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.DoctorOrderTypeDAO;
import com.wtc.hcis.ip.da.OrderTypeHasAttributes;
import com.wtc.hcis.ip.da.OrderTypeHasAttributesDAO;
import com.wtc.hcis.ip.da.extn.DoctorOrderDAOExtn;
import com.wtc.hcis.ip.da.extn.DoctorOrderGroupDAOExtn;
import com.wtc.hcis.ip.da.extn.DoctorOrderTemplateDAOExtn;


/**
 * @author Alok Ranjan
 *
 */
public class DoctorOrderManagerImpl implements DoctorOrderManager {

	private IPDataModelConverter converter;
	private IPDataModelManager dataModelManager;
	private DoctorOrderBase doctorOrderBase;
	
	private DoctorOrderTypeDAO doctorOrderTypeDAO;
	private DoctorOrderTemplateDAOExtn doctorOrderTemplateDAO;
	private DoctorOrderGroupDAOExtn doctorOrderGroupDAO;
	private DoctorOrderGroupHasTemplateDAO doctorOrderGroupHasTemplateDAO;
	private DoctorOrderDetailsDAO doctorOrderDetailsDAO;
	private ServiceDAOExtn serviceDAO;
	private DoctorOrderDAOExtn doctorOrderDAO;
//	private DoctorOrderFactory doctorOrderFactory;
	private DoctorOrderTemplateDetailsDAO doctorOrderTemplateDetailsDAO;
	private PatientManager patientManager;
	private DoctorOrderActivityDAO doctorOrderActivityDAO;
	private OrderTypeHasAttributesDAO orderTypeHasAttributesDAO;
	
	/* (non-Javadoc)
	 * 1) Retrieve doctor order
	 * 2) For the given doctor order type, create appropriate doctor order object
	 * 3) Call the overridden approveDoctorOrder method.
	 * 4) Create approval activity
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#approveDoctorOrder(java.lang.Integer, java.lang.Integer)
	 */
	public DoctorOrderBM approveDoctorOrder(Integer orderNumber,
			Integer doctorId, String approvedBy ) {
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );
		
		DoctorOrderType doctorOrderType = doctorOrder.getDoctorOrderType();
		
//		DoctorOrderBase doctorOrderBase = doctorOrderFactory.getDoctorOrderObject( doctorOrderType.getOrderTypeCd() );
		
		doctorOrder = doctorOrderBase.approveDoctorOrder( doctorOrder, doctorId, approvedBy );
		
		return converter.convertDoctorOrderDM2BM(doctorOrder);
	}

	/* (non-Javadoc)
	 * For every DoctorOrderBM in the list -- create a doctor order.
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createDoctorOrder(java.util.List)
	 */
	public void createDoctorOrder(List<DoctorOrderBM> doctorOrderBMList ) {

		if ( doctorOrderBMList != null && !doctorOrderBMList.isEmpty() ) {
			for ( DoctorOrderBM doctorOrderBM : doctorOrderBMList ) {
				this.createDoctorOrder(doctorOrderBM);
			}
		}

	}

	/* (non-Javadoc)
	 * 1) Retrive doctor order group for the given group name
	 * 2) Retrive all the templates associated with the group
	 * 3) Create order for all the retrieved templates
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createDoctorOrder(java.lang.String)
	 */
	public void createDoctorOrder( String doctorOrderGroupName,  Integer doctorId, Integer patientId ) {
		DoctorOrderGroup doctorOrderGroup = dataModelManager.getDoctorOrderGroup(doctorOrderGroupName);
		
		List<DoctorOrderGroupHasTemplate>doctorOrderGroupTemplateList = doctorOrderGroupHasTemplateDAO.findByProperty( "doctorOrderGroup", doctorOrderGroup );
		
		if ( doctorOrderGroupTemplateList != null && 
			!doctorOrderGroupTemplateList.isEmpty() ) {
			
			for ( DoctorOrderGroupHasTemplate groupHasTemplate : doctorOrderGroupTemplateList ) {
				DoctorOrderTemplate orderTemplate = groupHasTemplate.getDoctorOrderTemplate();
				this.createDoctorOrder(orderTemplate, doctorId, patientId );
			}
		}
		
	}
	
	/**
	 * For a given template detail
	 * 1) Create doctor order
	 * 2) Create corresponding order activity
	 * 
	 * @param orderTemplate
	 */
	private void createDoctorOrder( DoctorOrderTemplate orderTemplate ,  Integer doctorId, Integer patientId )  {
		try {
			DoctorOrderType orderType =  orderTemplate.getDoctorOrderType();
//			DoctorOrderBase doctorOrderBase = doctorOrderFactory.getDoctorOrderObject( orderType.getOrderTypeCd() );
			
			if ( doctorOrderBase != null ) {
				DoctorOrder doctorOrder = doctorOrderBase.createDoctorOrder(orderTemplate, doctorId, patientId );
				doctorOrderBase.createDoctorOrderActivity( doctorOrder, DoctorOrderConstants.ACTIVITY_TYPE_ORDER_CREATED, null  );
			} else {
				throw new Exception(" The order type code = " + orderType.getOrderTypeCd() + " is not supported. " );
			}
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_DOCTOR_ORDER_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}
	
	/**
	 * This method creates a doctor order record for a given doctor order business model.
	 * Whenever we create a doctor order, we should also create doctor order activity. 
	 * For specific types of orders, we may need to create specific activities. Hence we 
	 * must call corresponding overridden createOrderActivity method.
	 * 
	 * @param doctorOrderBM
	 */
	private void createDoctorOrder( DoctorOrderBM doctorOrderBM )  {
		try {
//			DoctorOrderBase doctorOrderBase = doctorOrderFactory.getDoctorOrderObject( doctorOrderBM.getDoctorOrderType().getCode() );
			
			if ( doctorOrderBase != null ) {
				DoctorOrder doctorOrder = doctorOrderBase.createDoctorOrder(doctorOrderBM);
				doctorOrderBase.createDoctorOrderActivity( doctorOrder, DoctorOrderConstants.ACTIVITY_TYPE_ORDER_CREATED, null );
			} else {
				throw new Exception(" The order type code = " + doctorOrderBM.getDoctorOrderType().getCode() + " is not supported. " );
			}
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_DOCTOR_ORDER_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}

	/* (non-Javadoc)
	 * For every template in the order template list, create doctor order.
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#createDoctorOrderForTemplates(java.util.List)
	 */
	public void createDoctorOrderForTemplates(	List<String> orderTemplateIdList, Integer doctorId, Integer patientId ) {

		if ( orderTemplateIdList != null && !orderTemplateIdList.isEmpty() ) {
			for ( String orderTemplateId : orderTemplateIdList ) {
				DoctorOrderTemplate orderTemplate = dataModelManager.getDoctorOrderTemplate( orderTemplateId );
				
				this.createDoctorOrder(orderTemplate, doctorId, patientId);
			}
		}

	}

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

		
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#getDoctorOrders(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<DoctorOrderBM> getDoctorOrders( Integer doctorOrderNbr,
			                                    String doctorOrderTypeCd, 
			                                    String doctorOrderTemplateId,
			                                    Integer admissionNbr, 
			                                    String doctorOrderStatusCd, 
			                                    String patientId,
			                                    String orderDesc, 
			                                    String orderDictationMethod ) {
		
		List<DoctorOrder>doctorOrderList = doctorOrderDAO.getDoctorOrders( doctorOrderNbr, 
				                                                           doctorOrderTypeCd, 
				                                                           doctorOrderTemplateId, 
				                                                           admissionNbr, 
				                                                           doctorOrderStatusCd, 
				                                                           patientId, 
				                                                           orderDesc, 
				                                                           orderDictationMethod );
		
				
		if ( doctorOrderList != null && !doctorOrderList.isEmpty() ) {
			
			List<DoctorOrderBM>doctorOrderBMList = new ArrayList<DoctorOrderBM>(0);
			
			for ( DoctorOrder doctorOrder : doctorOrderList ) {
				doctorOrderBMList.add( converter.convertDoctorOrderDM2BM( doctorOrder ) );
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
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#getOrderTemplates(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, boolean)
	 */
	public List<OrderTemplateSummaryBM> getOrderTemplates( String templateId,
			                                               String templateDesc, 
			                                               Integer templateForDoctorId,
			                                               String orderTypeCd ) {
		
		List<DoctorOrderTemplate>orderTemplateList = doctorOrderTemplateDAO.getOrderTemplates( templateId, 
				                                                                               templateDesc, 
				                                                                               templateForDoctorId, 
				                                                                               orderTypeCd );
		if ( orderTemplateList != null && !orderTemplateList.isEmpty() ) {
			List<OrderTemplateSummaryBM>orderTemplateSummaryList = new ArrayList<OrderTemplateSummaryBM>(0);
			
			for ( DoctorOrderTemplate orderTemplate : orderTemplateList ) {
				OrderTemplateSummaryBM templateSummaryBM = new OrderTemplateSummaryBM();
				
				templateSummaryBM.setDoctorId( orderTemplate.getDoctorId() );
				DoctorOrderType doctorOrderType = orderTemplate.getDoctorOrderType();
				templateSummaryBM.setDoctorOrderType( new CodeAndDescription( doctorOrderType.getOrderTypeCd(), doctorOrderType.getOrderTypeDesc() ) );
				templateSummaryBM.setTemplateDesc( orderTemplate.getTemplateDesc() );
				templateSummaryBM.setTemplateId( orderTemplate.getTemplateId() );
				orderTemplateSummaryList.add( templateSummaryBM );
			}
			
			return orderTemplateSummaryList;
			
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#getOrderTypes()
	 */
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

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#modifyDoctorOrder(in.wtc.hcis.bm.ip.DoctorOrderBM)
	 */
	public DoctorOrderBM modifyDoctorOrder( DoctorOrderBM doctorOrderBM ) {
		
//		DoctorOrderBase doctorOrderBase = doctorOrderFactory.getDoctorOrderObject( doctorOrderBM.getDoctorOrderType().getCode() );
		DoctorOrder existingDoctorOrder = dataModelManager.getDoctorOrder( doctorOrderBM.getDoctorOrderNbr() );
		
		DoctorOrder doctorOrder = doctorOrderBase.modifyDoctorOrder( existingDoctorOrder, doctorOrderBM  );
		
		return converter.convertDoctorOrderDM2BM(doctorOrder);
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#modifyDoctorOrderType(in.wtc.hcis.bm.base.CodeAndDescription)
	 */
	public CodeAndDescription modifyDoctorOrderType( CodeAndDescription doctorOrderTypeBM ) {
		
		DoctorOrderType doctorOrderType = dataModelManager.getDoctorOrderType( doctorOrderTypeBM.getCode() );
		doctorOrderType.setOrderTypeDesc( doctorOrderTypeBM.getDescription() );
		doctorOrderTypeDAO.attachDirty( doctorOrderType );
		
		return doctorOrderTypeBM;
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#modifyOrderGroup(in.wtc.hcis.bm.ip.DoctorOrderGroupBM)
	 */
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

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#modifyOrderTemplate(in.wtc.hcis.bm.ip.OrderTemplateBM)
	 */
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

	/* (non-Javadoc)
	 * This method assumes that order type is not at all used by any order or order template in the system.
	 * 
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#removeDoctorOrderType(java.util.List)
	 */
	public boolean removeDoctorOrderType(
			List<CodeAndDescription> doctorOrderTypeList) {
		
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

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#removeOrderGroup(java.lang.String)
	 */
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

	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.order.DoctorOrderManager#disapproveDoctorOrder(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String )
	 */
	public DoctorOrderBM disapproveDoctorOrder( Integer orderNumber,
			                                    Integer doctorId,
			                                    String remarks,
			                                    String disapprovedByPersonId ) {
		
		DoctorOrder doctorOrder = dataModelManager.getDoctorOrder( orderNumber );
		
		DoctorOrderType doctorOrderType = doctorOrder.getDoctorOrderType();
		
//		DoctorOrderBase doctorOrderBase = doctorOrderFactory.getDoctorOrderObject( doctorOrderType.getOrderTypeCd() );
		
		doctorOrder = doctorOrderBase.disapproveDoctorOrder( doctorOrder, 
				                                             doctorId, 
				                                             remarks, 
				                                             disapprovedByPersonId );
		
		return converter.convertDoctorOrderDM2BM(doctorOrder);
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
	
	/**
	 * This method returns ListRange object containing DoctorOrderTemplates objects which satisfies the search criteria. 
	 * All parameters are optional.  
	 */
	
	public ListRange findDoctorOrderTemplates(	String templateId, String orderTypeCd,
												Integer doctorId, String serviceId,
												String departmentId, int start, int count, String orderBy) throws HCISException {
		
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
	 * This method is for UI in case of Create Doctor Order for Order Group case in
	 * Create Doctor Order Window
	 * @param doctorOrderGroupName
	 * @param doctorId
	 * @param patientId
	 */
	public void createDoctorOrderForGroup(String doctorOrderGroupName,  Integer doctorId, Integer patientId){
		createDoctorOrder( doctorOrderGroupName,  doctorId, patientId );
	}
	
	
	public ListRange getOrderTypeHasAttribute( String orderTypeCd ,int start, int count, String orderBy ) throws HCISException{
		
		List<OrderTypeAttributeAssociationBM> orderTypeAttributeAssociationBMList = new ArrayList<OrderTypeAttributeAssociationBM>();
		if (orderTypeCd != null && !orderTypeCd.isEmpty()) {

			List<OrderTypeHasAttributes> orderTypeHasAttributesList = orderTypeHasAttributesDAO.findByProperty("id.orderTypeCd", orderTypeCd);
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

						orderTypeAttributeAssociationBMList.add(attributeAssociationBM);
					}
				}
			}
		}
		
		
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
	
	/**
	 * This method is for UI purpose in case of creating Doctor Order for
	 * Individual case in Create Doctor Order Window
	 * @param doctorOrderBM
	 */
	
	public void createDoctorOrderIndividualSelection( DoctorOrderBM doctorOrderBM ){
		createDoctorOrder( doctorOrderBM );
	}

	
	public void setConverter(IPDataModelConverter converter) {
		this.converter = converter;
	}

	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
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

	public void setDoctorOrderBase(DoctorOrderBase doctorOrderBase) {
		this.doctorOrderBase = doctorOrderBase;
	}

	/**
	 * @param orderTypeHasAttributesDAO the orderTypeHasAttributesDAO to set
	 */
	public void setOrderTypeHasAttributesDAO(
			OrderTypeHasAttributesDAO orderTypeHasAttributesDAO) {
		this.orderTypeHasAttributesDAO = orderTypeHasAttributesDAO;
	}

	public List<DoctorOrderDetailsBM> getOrderLineItems(Integer doctorOrderNbr) {
		// TODO Auto-generated method stub
		return null;
	}




}
