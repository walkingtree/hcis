/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bm.ip.DoctorOrderGroupBM;
import in.wtc.hcis.bm.ip.OrderTemplateBM;

import in.wtc.hcis.bm.ip.OrderTypeAttributeAssociationBM;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 * 
 */
public interface OrderManager {
	/**
	 * This method allows user to create doctor order type. Some of the examples
	 * of order type are: ADMISSIONS DISCHARGE MEDICATION TRANSFER DIETARY Order
	 * Nursing observation order Pre-operative orders
	 * 
	 * @param doctorOrderTypeBM
	 */
	void createDoctorOrderType(CodeAndDescription doctorOrderTypeBM);

	/**
	 * This method deletes all the doctor order type specified in the parameter
	 * list. If any of the specified order type is used in creating any order
	 * then system does not allow deletion of order type.
	 * 
	 * @param doctorOrderTypeList
	 * @return
	 */
	boolean removeDoctorOrderType(List<CodeAndDescription> doctorOrderTypeList);

	/**
	 * This method modifies the doctor order type. At this moment, it allows
	 * modification for description. However, if we add more attributes in the
	 * order type then other attributes should also be allowed to be modified.
	 * 
	 * @param doctorOrderType
	 * @return
	 */
	CodeAndDescription modifyDoctorOrderType(CodeAndDescription doctorOrderType);

	/**
	 * This method return all the order types available in the system.
	 * 
	 * @return
	 */
	List<CodeAndDescription> getOrderTypes();

	/**
	 * This method creates doctor order template. The OrderTemplateBM also
	 * contains list of order template details. Although, usually there will be
	 * one or more OrderTemplateDetailsBM record associated with the the order
	 * template, the system does allow user to create template without any order
	 * items.
	 * 
	 * @param orderTemplateBM
	 */
	void createOrderTemplate(OrderTemplateBM orderTemplateBM);

	/**
	 * For a given template identifier, this method deletes doctor's order
	 * template. This method assumes that the template has not been used. For
	 * example this template has not been used to create any doctor's order
	 * group or it has not been used to create any order.
	 * 
	 * @param templateId
	 * @return
	 */
	boolean removeOrderTemplate(String templateId);

	/**
	 * This method modified an existing order template. Apart from the basic
	 * template attributes like order type and description, this method also
	 * allows you to modify the
	 * 
	 * @param orderTemplateBM
	 * @return
	 */
	OrderTemplateBM modifyOrderTemplate(OrderTemplateBM orderTemplateBM);

	

	/**
	 * This method method creates doctor order group. The purpose of doctor
	 * order group is that -- a doctor can create a group of zero or more orders
	 * to save his recurring work. For example -- if a patient with "left leg
	 * broken" case comes for a surgery then most likely there will be a defined
	 * set of orders that the doctor needs to issue. This concept of grouping
	 * allows the doctor to achieve the same. The DoctorOrderGroupBM object also
	 * contains a list of associated templates, which a doctor can associate
	 * with the group.
	 * 
	 * @param doctorOrderGroupBM
	 */
	void createOrderGroup(DoctorOrderGroupBM doctorOrderGroupBM);

	/**
	 * For a given order group name, this method deletes the doctor order group.
	 * This method also deletes the association between the the group name and
	 * doctor order template.
	 * 
	 * @param doctorOrderGroupName
	 * @return
	 */
	boolean removeOrderGroup(String doctorOrderGroupName);

	/**
	 * This method allows modification of doctor order group. Apart from
	 * modifying basic details of doctor order group, one can also remove or add
	 * zero or more doctor order template in the group.
	 * 
	 * @param doctorOrderGroupBM
	 * @return
	 */
	DoctorOrderGroupBM modifyOrderGroup(DoctorOrderGroupBM doctorOrderGroupBM);

	/**
	 * This method retrieves doctor order group's for a given group name,
	 * description and / or associated doctor identifier. This method allows
	 * partial lookup for order group name and description.
	 * 
	 * @param orderGroupName
	 * @param description
	 * @param groupForDoctorId
	 * @return
	 */
	List<DoctorOrderGroupBM> getDoctorOrderGroups(String orderGroupName,
			String description, Integer groupForDoctorId);

	/**
	 * This method is useful for creating doctor orders for a given list of
	 * doctor order details.
	 * 
	 * Creation of doctor order majorly differentiated by two ways
			1.	General order.
			2.	Specific order.  [specific order may be Admission order or Transfer order or medication order or Discharge order].

		If we are creating general order we have two possibilities.

			a.	We can create general order with just instructions [Actions which needs to be performed]. 
				In this case we don�t create any assigned services.
				
			b.	We can create general order with services [which is really billable]. 
				In this case patient must be associated with admission number. In this case all services information assigned service table.

		While creating a specific order must need admission number. In this case all services information would be stored in assigned service table.

		So we need to check admission number before assigning services to that patient. If admission number is not valid number system needs to through 
		an exception. 
		At the same time front end also should be aware of this admission number. 
		If user is assigning any services to that patient without mentioning the admission number system sholud raise an error message.


//Bhavesh -- Discharge order can not be generated without a confirmed admission (admission number will be generated)
 * {confirmed this point by Srikanth}
 * 
	 *  
	 * @param doctorOrderBMList
	 */
	void createDoctorOrder(DoctorOrderBM[] doctorOrderBMList);// array

	/**
	 * This method modifies an existing doctor order. Apart from modifying
	 * common attributes this method also allows modification of order item
	 * details. The system also records the activities on a given order. For
	 * example -- if an order is being moved from pending approval to approved
	 * then one activity gets recorded.
	 * 
	 * @param doctorOrderBM
	 * @return
	 */
	DoctorOrderBM modifyDoctorOrder(DoctorOrderBM doctorOrderBM);

	/**
	 * If an order is in pending status (possibly created by someone with lesser
	 * authority) then for a doctor with permission to approve the order, this
	 * method moves the order into the approved status and creates corresponding
	 * order activity.
	 * 
	 * @param orderNumber
	 * @param doctorId
	 * @param approvedBy
	 * @return
	 */
	DoctorOrderBM approveDoctorOrder(Integer orderNumber, Integer doctorId,
			String remarks,String approvedByPersonId);

	/**
	 * If an order is in pending status (possibly created by someone with lesser
	 * authority) then for a doctor with permission to approve/disapprove the
	 * order, this method moves the order into the disapproved status and
	 * creates corresponding order activity. This method also allows user to
	 * enter the remarks for disapproving the order.
	 * 
	 * @param orderNumber
	 * @param doctorId
	 * @param remarks
	 * @param disapprovedByPersonId
	 * @return
	 */
	DoctorOrderBM disapproveDoctorOrder(Integer orderNumber, Integer doctorId,
			String remarks, String disapprovedByPersonId);

	/**
	 * Similar to disapprove doctor order
	 * @param orderNumber
	 * @param remarks
	 * @param personId
	 * @return
	 */
	DoctorOrderBM cancelDoctorOrder( Integer orderNumber,
						            String remarks,
						            String personId );

	/**
	 * This method retrieves doctor's order for the matching parameter values.
	 * All parameters are optional.
	 * 
	 * @param doctorOrderNbr
	 * @param doctorOrderTypeCd
	 * @param doctorOrderTemplateId
	 * @param admissionNbr
	 * @param doctorOrderStatusCd
	 * @param patientId
	 * @param orderDesc
	 * @param orderDictationMethod
	 * @return
	 */
	List<DoctorOrderBM> getDoctorOrders(Integer doctorOrderNbr,
			String doctorOrderTypeCd, String doctorOrderTemplateId,
			Integer admissionNbr, String doctorOrderStatusCd, String patientId,
			String orderDesc, String orderDictationMethod);

	/**
	 * A doctor order can contain just some instruction, as well as it can
	 * contain one or more services. This method returns details about all the
	 * services associated with an order.
	 * 
	 * @param doctorOrderNbr
	 * @return
	 */
	List<ServiceBM> getServicesAssociatedWithOrder(Integer doctorOrderNbr);

	/**
	 * Will be used in notifying the concern departments etc.
	 * 
	 * @param doctorOrderNbr
	 * @return
	 */
	List<DoctorOrderDetailsBM> getOrderLineItems(Integer doctorOrderNbr);
	
	List<DoctorOrderDetailsBM> getOrderDetailsForTemplate(String TemplateId);
	
	List<OrderTemplateBM> getOrderTemplatesForGroup( String orderGroupName);

	/**
	 * This method is used for Search doctor Order so specific to fromt end
	 * requirment
	 * 
	 * 
	 * @param admissionNbr
	 * @param patientId
	 * @param patientName
	 * @param doctorOrderStatusCd
	 * @param doctorOrderTypeCd
	 * @param orderDateFrom
	 * @param orderDateTo
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */

	ListRange findDoctorOrders(Integer admissionNbr, Integer patientId,
			String patientName, String doctorOrderStatusCd,
			String doctorOrderTypeCd, Date orderDateFrom, Date orderDateTo,
			int start, int count, String orderBy);

	/**
	 * Made for front end requirement
	 * 
	 * @param admissionNbr
	 * @return
	 */

	CodeAndDescription getAdmittedPatient(Integer admissionNbr);

	/**
	 * This method is for UI purpose in case of creating Doctor Order for
	 * Individual case in Create Doctor Order Window
	 * 
	 * @param doctorOrderBM
	 */
	public void createDoctorOrderIndividualSelection(DoctorOrderBM doctorOrderBM) throws HCISException;//

	/**
	 * 
	 * @param orderGroupName
	 * @param orderTemplateId
	 * @param doctorId
	 * @param createdFromDate
	 * @param createdToDate
	 * @return
	 * @throws HCISException
	 */
	ListRange findDoctorOrderGroups(String orderGroupName,
			String orderTemplateId, Integer doctorId, Date createdFromDate,
			Date createdToDate, int start, int count, String orderBy)
			throws HCISException;

	/**
	 * 
	 * @param templateId
	 * @param orderTypeCd
	 * @param doctorId
	 * @param serviceId
	 * @param departmentId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange findDoctorOrderTemplates(String templateId, String orderTypeCd,
			Integer doctorId, String serviceId, String departmentId, int start,
			int count, String orderBy) throws HCISException;

	/**
	 * This method removes the Templates of given Ids
	 * 
	 * @param templateIdList
	 */
	boolean removeOrderTemplateList(List<String> templateIdList);

	/**
	 * This method removes the Doctor Order group for given group name list
	 * 
	 * @param doctorOrderGroupNameList
	 */
	boolean removeOrderGroupList(List<String> doctorOrderGroupNameList);

	boolean removeDoctorOrderList(Integer[] doctorOrderNbrList);

	/**
	 * This method returns ListRange object of OrderTypeAttributeAssociationBM,
	 * for given orderTypeCd.
	 * 
	 * @param orderTypeCd
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange getOrderTypeHasAttribute(String orderTypeCd, int start,
			int count, String orderBy) throws HCISException;
	
	/**
	 * This is UI specific method
	 * @param orderGroupName
	 * @return
	 */
	List<OrderTypeAttributeAssociationBM> getOrderTypeAttributeBMForGroup( String orderGroupName);
	OrderTemplateBM getOrderTemplateForId( String templateId ) throws Exception;
	
	
	/**
	 * This method updates the current status of doctor order
	 * Remarks should be mandatory in case of DISAPPROVAL
	 * @param orderNumber
	 * @param newStatusCode
	 * @param personId
	 * @param remarks
	 * @return
	 */
	boolean updateOrderStatus(   Integer orderNumber, String newStatusCode,  String personId , String remarks);
	/**
	 * 
	 * @param orderNumber
	 * @param sequenceNbr
	 * @param subSequenceNbr
	 * @param newStatus
	 * @param presonid
	 * @param remarks
	 * @return
	 */
	boolean updateOrderDetailsStatus ( 	 Integer orderNumber, Integer sequenceNbr, Integer subSequenceNbr,
			 String newStatus ,String  presonid, String remarks );
	/**
	 * 
	 * @param modifiedDischargeBM
	 * @return
	 */
	public DischargeBM modifyDischargeDetails( DischargeBM modifiedDischargeBM );
	
	/**
	 * 
	 * @param admissionReqNbr
	 * @param dischargeSummary
	 * @param personId
	 */
	void dischargePatient( Integer admissionReqNbr , String dischargeSummary, String personId);
	/**
	 * 
	 * @param admissionReqNbr
	 * @param summary
	 */
	void saveDishcargeSummary( Integer admissionReqNbr, String summary ,String personId  );
	
	/**
	 * 
	 * @param admissionReqNbr
	 * @return
	 */
	String getDishcargeSummary( Integer admissionReqNbr);
	
	/*
	 * 
	 */
	String getOrderRemarks( Integer orderNumber, String statusTypeCd);
}
