package in.wtc.lims.requisition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.extn.AssignedServicesDAOExtn;
import com.wtc.hcis.da.extn.LabRequisitionOrderDAOExtn;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bo.constants.ServicesConstants;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.PatientRequisitionBM;
import in.wtc.lims.bm.RequisitionOrderBM;
import in.wtc.lims.bm.RequisitionOrderDetailBM;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.constant.RequisitionConstant;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

public class RequisitionOrderManagerImpl implements RequisitionOrderManager {
	
	private LabRequisitionOrderDAOExtn labRequisitionOrderDAO;
	private LimsDataModelConvertor limsDataModelConverter;
	private AssignedServicesDAOExtn assignedServicesDAO;
	private ServiceManager serviceManager;
	private LimsDataModelManager dataModelManager;
	private PatientManager patientManager;
	
	/**
	 * This will be called from the SearchRequisitionOrder screen.
	 * This method will return RequisitionOrderBM
	 * 
	 */
	public ListRange getRequisitionOrders( String patientName, 
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
														  String orderBy){
		
		
		
		
		
		try {
			List<LabRequisitionOrder> labRequisitionOrderList = labRequisitionOrderDAO.getRequisitionOrders(patientName, 
																				patientId, 
																				referenceType, 
																				doctorName, 
																				doctorId, 
																				requisitionFromDate, 
																				requisitionToDate, 
																				testName, 
																				testStatus);	
			
			
			ListRange listRange = new ListRange();
			
			if( labRequisitionOrderList != null && !labRequisitionOrderList.isEmpty() ){
				
				int index = 0;
				List<RequisitionOrderBM> pageSizeData = new ArrayList<RequisitionOrderBM>(0);
				
				while( (start+index < start + count) && (labRequisitionOrderList.size() > start+index) ){
					
					
					LabRequisitionOrder labRequisitionOrder = labRequisitionOrderList.get(start+index);
					
					RequisitionOrderBM requisitionOrderBM = limsDataModelConverter.convertLabRequisitionOrderDM2BM(labRequisitionOrder);
					
					pageSizeData.add( requisitionOrderBM );
						index++;
				}
					listRange.setData(pageSizeData.toArray());
					listRange.setTotalSize(labRequisitionOrderList.size());
				}else {
					listRange.setData(new Object[0]);
					listRange.setTotalSize(0);
				}
				
				return listRange;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.SEARCH_REQUISITION_FAILED);
			throw new LimsException(fault);
		}
		
	}

	/**
	 * This method will return RequisitionOrderDetailBM .
	 * First we get all assignedServices for the patient with this requisitionOrderNbr.
	 * Then we are preparing RequisitionOrderDetailBM from these assignedServices . 
	 */
	
	public ListRange getRequisitionOrderDetail( Integer requisitionOrderNbr, 
												int start,
												int count,
												String orderBy){
		
		try{
			
			List<AssignedServices> assignedServicesList = assignedServicesDAO.getServicesOfOrder(requisitionOrderNbr, true, null);
			
			ListRange listRange = new ListRange();
			
			if( assignedServicesList != null && !assignedServicesList.isEmpty() ){
				
				int index = 0;
				List<RequisitionOrderDetailBM> pageSizeData = new ArrayList<RequisitionOrderDetailBM>(0);
				
				while( (start+index < start + count) && (assignedServicesList.size() > start+index) ){
					
					
					AssignedServices assignedServices = assignedServicesList.get(start+index);
					
					RequisitionOrderDetailBM requisitionOrderDetailBM = limsDataModelConverter.convertAssignedService2RequisitionDetailBM(assignedServices);
					
					pageSizeData.add( requisitionOrderDetailBM );
						index++;
					}
					listRange.setData(pageSizeData.toArray());
					listRange.setTotalSize(assignedServicesList.size());
				}else {
					listRange.setData(new Object[0]);
					listRange.setTotalSize(0);
				}
				
				return listRange;
			
			
			
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.GET_REQUISITION_ORDER_DETAIL_FAILED);
			throw new LimsException(fault,e);
		}
	}
	
	/**
	 *  This method takes requisitionOrderNbr as a input and return PatientRequisitionBM.
	 *  This method will be called from SearchRequisitionOrder window(viewDetail) to view detail
	 *  for any requisitionOrderNbr(from the front end).
	 *  PatientRequisitionBM contains PatientLiteBM and RequisitionOrderDetailBM.
	 *  PatientLiteBM contains all the information about the patient for whom this requisitionOrder has been created.
	 *  And the RequisitionOrderDetailBM contains all the assignedServices detail.
	 *  
	 */
	public PatientRequisitionBM getPatientRequisitionDetail( Integer requisitionOrderNbr){
		
		LabRequisitionOrder requisitionOrder = dataModelManager.getLabRequisitionOrder(requisitionOrderNbr);
		
		List<AssignedServices> assignedServiceList = assignedServicesDAO.getServicesOfOrder(requisitionOrderNbr, true, null);

		List<RequisitionOrderDetailBM> requisitionOrderDeatilBMList = new ArrayList<RequisitionOrderDetailBM>(0);
		
		
		if( assignedServiceList != null && !assignedServiceList.isEmpty() ){
			
			for( AssignedServices assignedService : assignedServiceList ){
				
				RequisitionOrderDetailBM requisitionOrderDetailBM = limsDataModelConverter.convertAssignedService2RequisitionDetailBM(assignedService);
				
				requisitionOrderDeatilBMList.add(requisitionOrderDetailBM);
			}
		}
		
		PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM(requisitionOrder.getPatient().getPatientId());
		
		PatientRequisitionBM patientRequisitionBM = new PatientRequisitionBM();
		
		patientRequisitionBM.setPatientLiteBM(patientLiteBM);
		patientRequisitionBM.setRequisitionOrderDetailBM(requisitionOrderDeatilBMList);
		
		return patientRequisitionBM;
	}
	
	
	/**
	 * This method will cancel the RequisitionOrder.
	 * This method takes requisitionOrderNbr as a input.
	 * First it gets all the assigned services for the patient with this requisitionOrderNbr(ServiceOrderNbr)
	 * After getting assignedServiceList it calls cancelAssignedServices of the ServiceManager for canceling all the
	 * assigned services to patient if it successfully cancel all the assignedService then we are marking this 
	 * RequisitionOrder as cancel and it changes this requisitionOrder status as "CANCELLED".
	 * If any of asignedService is not being canceled then we throwing an exception why this service is not being cancel.
	 * In this case it does not changes status of the requisition order.  
	 */
	public void cancelRequisitionOrder( Integer requisitionOrderNbr , String cancelledBy){
		
		try{
			
			ArrayList<Integer> serviceUidList = new ArrayList<Integer>(0);
			Integer[] serviceUidListForCancellation =null;
			
			LabRequisitionOrder requisitionOrder = dataModelManager.getLabRequisitionOrder(requisitionOrderNbr);
			
					
			List<AssignedServices> assignedServicesList = assignedServicesDAO.getServicesOfOrder(requisitionOrderNbr, true, null);
			
			if( assignedServicesList != null && !assignedServicesList.isEmpty()){
					
				for( AssignedServices assignedService : assignedServicesList){
					
					
						serviceUidList.add(assignedService.getServiceUid());
				}
				
				serviceUidListForCancellation = new Integer[serviceUidList.size()];
				
				for( int i=0 ; i<serviceUidList.size() ; i++){
					
					serviceUidListForCancellation[i] = serviceUidList.get(i);
				}

			}
			
			serviceManager.cancelAssignedServices(serviceUidListForCancellation, cancelledBy);
			
			//Updating Status is taken care by serviceManger
//			requisitionOrder.setStatusCode(RequisitionConstant.REQUISITION_STATUS_CANCELLED);
//			requisitionOrder.setLastModifiedBy(cancelledBy);
//			requisitionOrder.setLastModifiedDtm(new Date());
			
			labRequisitionOrderDAO.attachDirty(requisitionOrder);
			
			
			
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.REQUISITION_ORDER_CANCEL_FAILED);
			throw new LimsException(fault,e);
		}
	}
	
	
	public void setLabRequisitionOrderDAO(
			LabRequisitionOrderDAOExtn labRequisitionOrderDAO) {
		this.labRequisitionOrderDAO = labRequisitionOrderDAO;
	}

	public void setLimsDataModelConverter(
			LimsDataModelConvertor limsDataModelConverter) {
		this.limsDataModelConverter = limsDataModelConverter;
	}


	public void setAssignedServicesDAO(AssignedServicesDAOExtn assignedServicesDAO) {
		this.assignedServicesDAO = assignedServicesDAO;
	}


	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}


	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}


	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}
}
