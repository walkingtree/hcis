/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.DischargeActivity;
import com.wtc.hcis.ip.da.DischargeActivityDAO;
import com.wtc.hcis.ip.da.DischargeActivityId;
import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.extn.AdmissionDAOExtn;
import com.wtc.hcis.ip.da.extn.DischargeOrderDAOExtn;

import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.OrderBusinessModelConverter;

/**
 * @author Alok Ranjan
 *
 */
public class DoctorDischargeOrder extends DoctorOrderBase implements DischargeManager {
	
	private AdmissionDAOExtn admissionDAO;
	private DischargeOrderDAOExtn dischargeOrderDAO;
	private DischargeActivityDAO dischargeActivityDAO;
	private OrderBusinessModelConverter orderModelConverter;
	
	/**
	 * A discharge order must not get created without a valid admission order.
	 * Hence, if the admission detailed has been specified in the doctor order
	 * business model then use that, else look for an active discharge order.
	 * 
	 */
	public DoctorOrder createDoctorOrder( DoctorOrderBM doctorOrderBM ) {
		DoctorOrder doctorOrder = this.createDischargeDoctorOrder(doctorOrderBM, null);
		
		return doctorOrder;
	}
	
	private DoctorOrder createDischargeDoctorOrder( DoctorOrderBM doctorOrderBM, DischargeBM dischargeBM ) {
		Calendar calendar = Calendar.getInstance();
		
		if ( dischargeBM != null ) {
			doctorOrderBM = orderModelConverter.convertDischargeBM2DoctorOrderBM(dischargeBM);
		}
		
		Admission admission = null;
		if ( doctorOrderBM.getAdmissionNbr() != null ) {
			admission = dataModelManager.getAdmission( doctorOrderBM.getAdmissionNbr() );
		
//			Code to ensure that discharge order doesn't already exist for the 
// 			admission for which discharge has been requested.
			DischargeOrder dischargeOrder = dischargeOrderDAO.findById(admission.getAdmissionNbr());
			
			if( dischargeOrder != null ){
				
				Fault fault = new Fault( ApplicationErrors.DISCHARGE_ORDER_ALREADY_EXIST );
				
				throw new HCISException( fault.getFaultMessage() + ". ADMISSION_NO =  " + doctorOrderBM.getAdmissionNbr(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}
		}
		if ( admission == null ) {
			List<Admission>activeAdmissionsList = admissionDAO.getActiveAdmissions( doctorOrderBM.getPatientId() );
			
			if ( activeAdmissionsList != null && !activeAdmissionsList.isEmpty() ) {
//		here patient can have only 1 active admission record 
				admission = activeAdmissionsList.get(0);
				doctorOrderBM.setAdmissionNbr( admission.getAdmissionNbr() );
			} else {
				Fault fault = new Fault( ApplicationErrors.ACTIVE_ADMISSION_DOES_NOT_EXIST );
				
				throw new HCISException( fault.getFaultMessage() + ". PATIENT_ID =  " + doctorOrderBM.getPatientId(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}
		
		
		}
		
				
		doctorOrderBM.setLastModifiedTm( calendar.getTime() );
		
		DoctorOrder doctorOrder = super.createDoctorOrder( doctorOrderBM );
		
		DischargeOrder dischargeOrder = new DischargeOrder();
		dischargeOrder.setOrderRequestedBy( doctorOrderBM.getModifiedBy() );
		dischargeOrder.setOrderCreationDtm( calendar.getTime() );
		
		String dischargeTypeCd = null;
		
		if ( dischargeBM == null ) {
			Map<String, String>dischargeAttributes = doctorOrderBM.getOrderSpecificAttributes();
			
			if ( dischargeAttributes != null && !dischargeAttributes.isEmpty() ) {
				dischargeTypeCd = dischargeAttributes.get( DoctorOrderConstants.DISCHARGE_TYPE_CD_ATTR );
			}
		} else {
			dischargeTypeCd = dischargeBM.getDischargeType().getCode();
			dischargeOrder.setExpectedDischargeDtm( dischargeBM.getExpectedDischargeDate() );
			dischargeOrder.setActualDischargeDtm( dischargeBM.getActualDischargeDtm() );
			dischargeOrder.setDischargeSummary( dischargeBM.getDischargeSummary() );
			dischargeOrder.setApprovalTime( dischargeBM.getApprovalTime() );
			dischargeOrder.setApprovedBy( dischargeBM.getApprovedBy() );
		}
		
		
		if ( dischargeTypeCd == null ) {
			dischargeTypeCd = DoctorOrderConstants.DEFAULT_DISCHARGE_TYPE;
		}
		
		DischargeType dischargeType = dataModelManager.getDischargeTpe(dischargeTypeCd);
		dischargeOrder.setDischargeType(dischargeType);
		dischargeOrder.setDoctorOrder(doctorOrder);
		dischargeOrder.setDoctorOrderStatus( doctorOrder.getDoctorOrderStatus() );
		dischargeOrder.setExpectedDischargeDtm( null );
		dischargeOrder.setLastModifiedDtm( calendar.getTime() );
		dischargeOrder.setModifiedBy( doctorOrderBM.getModifiedBy() );
		
		dischargeOrder.setAdmissionReqNbr( admission.getAdmissionReqNbr() );
		dischargeOrder.setAdmission( admission );
		
		dischargeOrderDAO.save( dischargeOrder );
		
		this.createDischargeOrderActivity(dischargeOrder, "Discharge Order Created");
		
		return doctorOrder;
	}
	
	/**
	 * 
	 * @param dischargeOrder
	 * @param remarks
	 */
	private void createDischargeOrderActivity( DischargeOrder dischargeOrder, String remarks ) {
		DischargeActivity dischargeActivity = new DischargeActivity();
		
		DoctorOrderStatus doctorOrderStatus = dischargeOrder.getDoctorOrder().getDoctorOrderStatus();
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

	public Integer createDischargeOrder(DischargeBM dischargeBM) {
		DoctorOrder doctorOrder =  this.createDischargeDoctorOrder( null, dischargeBM);
		
		return doctorOrder.getDoctorOrderNbr();
	}

	public List<DischargeBM> getDischarge ( Integer doctorId, 
											Integer patientId,
											Integer admissionNumber, 
											String dischargeStatusCd,
											String dischargeTypeCd, 
											Date dischargeFromDate,
											Date dischargeToDate, 
											Date expectedDischargeFromDate,
											Date expectedDischargeToDate, 
											String dischargeRequestedBy,
											String dischargeApprovedBy ) {
		
		List<DischargeOrder> dischargeOrderList = dischargeOrderDAO.getDischarge( doctorId, patientId,
												  								  admissionNumber,
												  								  dischargeStatusCd,
												  								  dischargeTypeCd,
																				  dischargeFromDate,
																				  dischargeToDate,
																				  expectedDischargeFromDate,
																				  expectedDischargeToDate,
																				  dischargeRequestedBy,
																				  dischargeApprovedBy);
		List<DischargeBM> dischargeBMList = new ArrayList<DischargeBM>();
		
		if(dischargeOrderList != null && !dischargeOrderList.isEmpty()){
			
			for( DischargeOrder dischargeOrder : dischargeOrderList){
				dischargeBMList.add(converter.convertDischargeOrderDM2BM(dischargeOrder));
			}
		}
		
		return dischargeBMList;
	}

	public DischargeBM modifyDischargeDetails( DischargeBM modifiedDischargeBM ) {
		DoctorOrderBM doctorOrderBM = orderModelConverter.convertDischargeBM2DoctorOrderBM( modifiedDischargeBM );
		DoctorOrder existingDoctorOrder = dataModelManager.getDoctorOrder( doctorOrderBM.getDoctorOrderNbr() );
		DischargeOrder existingDischargeOrder = dataModelManager.getDischargeOrder( modifiedDischargeBM.getDoctorOrderNbr() );   
		super.modifyDoctorOrder(existingDoctorOrder, doctorOrderBM);
		
		DischargeOrder modifiedDischargeOrder = converter.convertDischargeOrderBM2DM( modifiedDischargeBM, existingDischargeOrder );
		modifiedDischargeOrder.setLastModifiedDtm( new Date());
		modifiedDischargeOrder.setModifiedBy( modifiedDischargeBM.getModifiedBy() );
		dischargeOrderDAO.attachDirty( modifiedDischargeOrder );
		
		if ( !existingDischargeOrder.getDoctorOrderStatus().getOrderStatusCd().equalsIgnoreCase( modifiedDischargeOrder.getDoctorOrderStatus().getOrderStatusCd() ) ) {
			this.createDischargeOrderActivity( modifiedDischargeOrder, "" );
		}
		
		// TODO : Bhavesh, through this method, we should also allow 
		// 1) Addition of zero or more doctor order details
		// 2) Updating zero or more existing doctor order details
		// 3) If an action in doctor order detail is already marked as complete then do not 
		//    allow any modification for such entries
		//
		// Finally, we need to have similar logic in admission order as well.
		// Also, note that Same order sheet can have orders from different doctors. Orders
		// like Admission and Discharge will be unique during a given admission. Hence, there 
		// should be a way to capture doctor details of the doctor who is modifying the 
		// doctor order details.
		// Currently doctor identifier doesn't exist on doctor order details and it is 
		// not possible to figure out who has modified what. Please have a discussion on this
		// with me and Ajit. This will be applicable for all the orders.
		// 
		
		return converter.convertDischargeOrderDM2BM( modifiedDischargeOrder ); 
	}

	public AdmissionDAOExtn getAdmissionDAO() {
		return admissionDAO;
	}

	public void setAdmissionDAO(AdmissionDAOExtn admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public DischargeOrderDAOExtn getDischargeOrderDAO() {
		return dischargeOrderDAO;
	}

	public void setDischargeOrderDAO(DischargeOrderDAOExtn dischargeOrderDAO) {
		this.dischargeOrderDAO = dischargeOrderDAO;
	}

	public DischargeActivityDAO getDischargeActivityDAO() {
		return dischargeActivityDAO;
	}

	public void setDischargeActivityDAO(DischargeActivityDAO dischargeActivityDAO) {
		this.dischargeActivityDAO = dischargeActivityDAO;
	}

	public OrderBusinessModelConverter getOrderModelConverter() {
		return orderModelConverter;
	}

	public void setOrderModelConverter(
			OrderBusinessModelConverter orderModelConverter) {
		this.orderModelConverter = orderModelConverter;
	}


}
