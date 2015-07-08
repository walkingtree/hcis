package in.wtc.lims.requisition;

import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.LabRequisitionOrderDAO;

import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.PatientRequisitionBM;
import in.wtc.lims.bm.RequisitionOrderBM;

public interface RequisitionOrderManager {
	
	
/**
 * 
 * @param patientName
 * @param patientId
 * @param referenceType
 * @param doctorName
 * @param doctorId
 * @param requisitionFromDate
 * @param requisitionToDate
 * @param testName
 * @param testStatus
 * @param start
 * @param count
 * @param orderBy
 * @return
 */
	ListRange getRequisitionOrders( String patientName, 
												   Integer patientId,
												   String referenceType,
												   String doctorName,
												   Integer doctorId,
												   Date requisitionFromDate,
												   Date requisitionToDate,
												   String testName,
												   String testStatus,
												   int start,
												   int count,
												   String orderBy);	
	
	/**
	 * 
	 * @param requisitionOrderNbr
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange getRequisitionOrderDetail( Integer requisitionOrderNbr, 
			int start,
			int count,
			String orderBy);
	/**
	 * 
	 * This method will cancel the RequisitionOrder.
	 * This method takes requisitionOrderNbr as a input.
	 * First it gets all the assigned services for the patient with this requisitionOrderNbr(ServiceOrderNbr)
	 * After getting assignedServiceList it calls cancelAssignedServices of the ServiceManager for canceling all the
	 * assigned services to patient if it successfully cancel all the assignedService then we are marking this 
	 * RequisitionOrder as cancel and it changes this requisitionOrder status as "CANCELLED".
	 * If any of asignedService is not being canceled then we throwing an exception why this service is not being cancel.
	 * In this case it does not changes status of the requisition order.  
	 * 
	 * @param requisitionOrderNbrList
	 * @param removedBy
	 */
	void cancelRequisitionOrder( Integer requisitionOrderNbr , String removedBy);
	/**
	 * 
	 * @param requisitionOrderNbr
	 * @return
	 */
	PatientRequisitionBM getPatientRequisitionDetail( Integer requisitionOrderNbr);
	
}
