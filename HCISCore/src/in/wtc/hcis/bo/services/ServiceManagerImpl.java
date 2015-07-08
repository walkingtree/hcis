package in.wtc.hcis.bo.services;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PackageHasServiceBM;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.base.ServiceGroupBM;
import in.wtc.hcis.bm.base.ServicePackageBM;
import in.wtc.hcis.bm.base.ServicePackageSummaryBM;
import in.wtc.hcis.bm.base.ServicePriceDetailBM;
import in.wtc.hcis.bm.base.ServicePriceUpdateBM;
import in.wtc.hcis.bm.base.ServiceSummaryBM;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.ServicesConstants;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;

import sun.rmi.runtime.NewThreadAction;

import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.AssignedPackage;
import com.wtc.hcis.da.AssignedPackageStatus;
import com.wtc.hcis.da.AssignedPackageStatusDAO;
import com.wtc.hcis.da.AssignedServiceHistory;
import com.wtc.hcis.da.AssignedServiceHistoryDAO;
import com.wtc.hcis.da.AssignedServiceHistoryId;
import com.wtc.hcis.da.AssignedServiceStatus;
import com.wtc.hcis.da.AssignedServiceStatusDAO;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.DateDim;
import com.wtc.hcis.da.Department;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.LabPatientTestDetailDAO;
import com.wtc.hcis.da.LabRequisitionOrder;
import com.wtc.hcis.da.LabRequisitionOrderDAO;
import com.wtc.hcis.da.PackageHasService;
import com.wtc.hcis.da.PackageHasServiceDAO;
import com.wtc.hcis.da.PackageHasServiceId;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.RenderedService;
import com.wtc.hcis.da.RenderedServiceId;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceGroup;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.ServicePackageStatus;
import com.wtc.hcis.da.ServicePackageStatusDAO;
import com.wtc.hcis.da.ServicePriceDetail;
import com.wtc.hcis.da.ServicePriceDetailId;
import com.wtc.hcis.da.ServiceStatus;
import com.wtc.hcis.da.ServiceStatusDAO;
import com.wtc.hcis.da.extn.AppointmentsDAOExtn;
import com.wtc.hcis.da.extn.AssignedPackageDAOExtn;
import com.wtc.hcis.da.extn.AssignedServicesDAOExtn;
import com.wtc.hcis.da.extn.DateDimDAOExtn;
import com.wtc.hcis.da.extn.RenderedServiceDAOExtn;
import com.wtc.hcis.da.extn.ServiceDAOExtn;
import com.wtc.hcis.da.extn.ServiceGroupDAOExtn;
import com.wtc.hcis.da.extn.ServicePackageDAOExtn;
import com.wtc.hcis.da.extn.ServicePriceDetailDAOExtn;

public class ServiceManagerImpl implements ServiceManager {
	
	ServiceDAOExtn serviceDAO;
	ServiceGroupDAOExtn serviceGroupDAO;
	AssignedServicesDAOExtn assignedServicesDAO;
	AppointmentsDAOExtn appointmentsDAO;
	ServiceStatusDAO serviceStatusDAO;
	ServicePackageStatusDAO servicePackageStatusDAO;
	ServicePackageDAOExtn servicePackageDAO;
	PackageHasServiceDAO packageHasServiceDAO;
	AssignedPackageDAOExtn assignedPackageDAO;
	AssignedServiceStatusDAO assignedServiceStatusDAO;
	AssignedServiceHistoryDAO assignedServiceHistoryDAO;
	AssignedPackageStatusDAO assignedPackageStatusDAO; 
	RenderedServiceDAOExtn renderedServiceDAO;
	LabRequisitionOrderDAO labRequisitionOrderDAO;
	ServicePriceDetailDAOExtn servicePriceDetailDAO;
	DateDimDAOExtn dateDimDAO;
	
	CommonDataManager commonDataManager;
	
	DataModelManager dataModelManager;
	
	LabPatientTestDetailDAO patientTestDetailDAO;
	
	private final String SERVICE_ORDER_NBR = "labRequisitionOrder.orderNbr"; 

	public void addService( ServiceBM serviceBM ) {
		try {
			
			Validate.notNull(serviceBM," serviceBM must not be null ");
			
			//Service can be either Created or Active  status only
			if( serviceBM.getServiceStatus() != null ){
				
				if( serviceBM.getServiceStatus().getCode().equals(ServicesConstants.SERVICE_STATUS_CREATED)
						|| serviceBM.getServiceStatus().getCode().equals("")){
				
					serviceBM.setServiceStatus( new CodeAndDescription(ServicesConstants.SERVICE_STATUS_CREATED,""));
				}else if(serviceBM.getServiceStatus().getCode().equals(ServicesConstants.SERVICE_STATUS_ACTIVE)){
					
					serviceBM.setServiceStatus( new CodeAndDescription(ServicesConstants.SERVICE_STATUS_ACTIVE,""));
				}else{
					
					throw new Exception(" Status should be either 'Created' or 'Active' ");
				}
			}else{
				//By default service will be in Created status 
				serviceBM.setServiceStatus( new CodeAndDescription(ServicesConstants.SERVICE_STATUS_CREATED,""));
			}
			
			Service service = this.convertServiceBM2DM(serviceBM, null);
			service.setCreateDtm(Calendar.getInstance().getTime());
			service.setCreatedBy( serviceBM.getPersonId() );
			
			//TODO: serviceGroup should be nullable
			if ( service.getServiceGroup() == null ) {
				ServiceGroup serviceGroup = this.getServiceGroup( ServicesConstants.DEFAULT_SERVICE_GROUP );
				service.setServiceGroup(serviceGroup);
			}
			
			serviceDAO.save(service);
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_ADD_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void addServiceGroup( ServiceGroupBM serviceGroupBM ) {
		try {
			Calendar now = Calendar.getInstance();
			ServiceGroup serviceGroup = this.convertServiceGroupBM2DM(serviceGroupBM, null);
			serviceGroup.setCreatedBy( serviceGroupBM.getCreatedBy() );
			serviceGroup.setCreateDtm( now.getTime() );
			serviceGroupDAO.save(serviceGroup);
			//
			// While adding a group, we may have a list of services which may be associated with the
			// new group.
			//
			List<ServiceSummaryBM> serviceSummaryBMList = serviceGroupBM.getServiceSummaryList();
			
			if ( serviceSummaryBMList != null && !serviceSummaryBMList.isEmpty() ) {
				
				for ( ServiceSummaryBM serviceSummaryBM : serviceSummaryBMList ) {
					Service service = dataModelManager.getServiceByCode( serviceSummaryBM.getServiceCode() );
					
					service.setServiceGroup(serviceGroup);
					service.setLastModifiedDtm( now.getTime() );
					service.setModifiedBy( serviceGroupBM.getCreatedBy() );
					serviceDAO.attachDirty( service );
				}
			}
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_GROUP_ADD_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	/**
	 * Finds matching groups.
	 * 
	 */
	public List<ServiceGroupBM> getServiceGroup(  String serviceGroupCode, String serviceGroupName,
			  									  String parentGroupCode, String serviceCode ) {
		try {
			List<ServiceGroup> serviceGroupsList = serviceGroupDAO.findServiceGroups(serviceGroupCode,
													serviceGroupName, parentGroupCode, serviceCode);
			
			List<ServiceGroupBM> retServiceGroupBMList = new ArrayList<ServiceGroupBM>(0);
			
			if ( serviceGroupsList != null && !serviceGroupsList.isEmpty() ) {
				
				for (ServiceGroup tmpSvcGrp : serviceGroupsList) {
					ServiceGroupBM tmpSvcGrpBM = this.convertServiceGroupDM2BM(tmpSvcGrp);
					retServiceGroupBMList.add( tmpSvcGrpBM );
				}
			}
			
			return retServiceGroupBMList;
		} catch(Exception ex) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_GROUP_READ_FAILED );
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public ListRange findServiceGroup(  String serviceGroupCode, String serviceGroupName,
			  							String parentGroupCode, String serviceCode,
			  							int start, int count, String orderBy ){
		
		 List<ServiceGroupBM> serviceGroupBMList = this.getServiceGroup(serviceGroupCode, serviceGroupName,
				 														parentGroupCode, serviceCode);
		 
		 ListRange listRange = new ListRange();
			
			List<ServiceGroupBM> pageSizeData = new ArrayList<ServiceGroupBM>();
			int index = 0;
			if (serviceGroupBMList != null && serviceGroupBMList.size() > 0) {
			while( (start+index < start + count) && (serviceGroupBMList.size() > start+index) ){
				
				ServiceGroupBM serviceGroupBM = serviceGroupBMList.get(start+index);
				pageSizeData.add( serviceGroupBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(serviceGroupBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
	}
	
	public List<CodeAndDescription> getServiceGroupList() {
		try {
			List<ServiceGroup> serviceGroupsList = serviceGroupDAO.findAllServiceGroups();
			
			List<CodeAndDescription> retServicesGroupList = new ArrayList<CodeAndDescription>(0);
			
			if (serviceGroupsList != null) {
				for (ServiceGroup tmpSvcGrp : serviceGroupsList) {
					CodeAndDescription tmpSvc = new CodeAndDescription();
					tmpSvc.setCode(tmpSvcGrp.getServiceGroupCode());
					tmpSvc.setDescription(tmpSvcGrp.getGroupName());
				}
			}
			
			return retServicesGroupList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_GROUP_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public List<CodeAndDescription> getServicesList() {
		try {
			List<Service> servicesList = serviceDAO.findAllActiveServices();
			
			List<CodeAndDescription> retServicesList = new ArrayList<CodeAndDescription>(0);
			
			if (servicesList != null) {
				for (Service tmpService : servicesList) {
					CodeAndDescription tmpSvc = new CodeAndDescription();
					tmpSvc.setCode(tmpService.getServiceCode());
					tmpSvc.setDescription(tmpService.getServiceName());
					
					retServicesList.add(tmpSvc);
				}
			}
			
			return retServicesList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public List<CodeAndDescription> getServicesOfGroup( String serviceGroupCode ) {
		try {
			List<Service> servicesList = serviceDAO.findServicesOfGroup(serviceGroupCode);
			
			List<CodeAndDescription> retServicesList = new ArrayList<CodeAndDescription>(0);
			if (servicesList != null) {
				for (Service tmpService : servicesList) {
					CodeAndDescription tmpSvc = new CodeAndDescription();
					tmpSvc.setCode(tmpService.getServiceCode());
					tmpSvc.setDescription(tmpService.getServiceName());
					
					retServicesList.add(tmpSvc);
				}
			}
			
			return retServicesList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	/**
	 * 
	 * If the user has increased the number of rendered units then check if we need to change status of the service.
	 * For example -- if the number of requested unit is 5 and 2-unit is rendered (with zero already rendered) then
	 * the service should be moved to PARTRENDERED status. If 1 or 2 unit is being rendered again then status remains 
	 * in the PARTRENDERED status. However, if all the services gets rendered then it should be moved to RENDERED status.
	 * 
	 */
	public AssignedServiceBM modifyAssignedService( AssignedServiceBM assignedServiceBM ) {
		try {
			AssignedServices assignedServices = this.getAssignedServices( assignedServiceBM.getServiceUid() );
			String newStatus = this.getNewStatusOfAssignedService( assignedServices, assignedServiceBM );
			boolean assignedServicesStatusChanged = false;
			
			if ( newStatus.equals( assignedServices.getAssignedServiceStatus().getServiceStatusCode() )  ) {
				//
				// The status of table is same as the persisted information. 
				// Check if rendered details have changed and create corresponding entry in rendered services table.
				//
				if ( assignedServices.getRenderedUnits() < assignedServiceBM.getRenderedUnits() ) {
					this.createRenderedServiceEntry(assignedServices, assignedServiceBM.getRenderedUnits() - assignedServices.getRenderedUnits() );
				}
			} else {
				
				// Also, check if rendered details have changed and create corresponding entry in rendered services table.
				//
				if ( assignedServices.getRenderedUnits() < assignedServiceBM.getRenderedUnits() ) {
					this.createRenderedServiceEntry(assignedServices, assignedServiceBM.getRenderedUnits() - assignedServices.getRenderedUnits() );
				}
				assignedServicesStatusChanged = true;
			}
			
			assignedServiceBM.setAssignedServiceStatus( new CodeAndDescription(newStatus,"") );
			AssignedServices assignedServicesDirty = this.convertAssignedServicesBM2DM( assignedServiceBM, assignedServices );
			
			
			if( assignedServicesStatusChanged ){
				// Status of service has changed and we must log this information into the assigned service history table.
				this.createAssignedServiceHistory(assignedServicesDirty);
				
				//at the same time update corresponding requisition status
				this.updateRequisitionStatus(assignedServicesDirty.getLabRequisitionOrder(), assignedServiceBM.getModifiedBy());
			}
			
			assignedServicesDirty.setLastModifiedDtm( new Date() );
			assignedServicesDirty.setModifiedBy( assignedServiceBM.getModifiedBy() );
			
			assignedServicesDAO.attachDirty( assignedServicesDirty );
			return this.convertAssignedServicesDM2BM(assignedServicesDirty);
			
		} catch(Exception ex) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_UPDATE_FAILED );
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	
	/**
	 *  
	 * @param assignedServices
	 * @param renderedUnitCount
	 */
	private void createRenderedServiceEntry( AssignedServices assignedServices, Integer renderedUnitCount ) {
		RenderedService renderedService = new RenderedService();
		renderedService.setAssignedServices(assignedServices);
		
		if ( !assignedServices.getReferenceType().equals( ServicesConstants.SERVICE_ASSIGNED_FROM_IPD) ) {
			//
			// Services assigned from OPD, DIRECT and EMERGENCY will be already billed. 
			// Hence, copy the bill information from the last bill number field of 
			// AssignedService details
			//
			renderedService.setBillNbr( assignedServices.getLastBillNbr() );
		} else {
			if ( assignedServices.getAssignedPackage() != null ) {
				//
				// If the service is part of an assigned package and the assigned package itself is billed
				// then the rendered services should also be automatically considered as billed.
				// The bill number will be same as the one used to stamp the assigned package.
				//
				renderedService.setBillNbr( assignedServices.getAssignedPackage().getBillNbr() );
			} else {
				; // Do nothing for billing. This needs to be billed separately.
			}
			
		}
		
		renderedService.setRenderedBy( assignedServices.getModifiedBy() );
		renderedService.setRenderedQty( renderedUnitCount );
		renderedService.setServiceCharge( renderedUnitCount * assignedServices.getServiceCharge() / assignedServices.getRequestedUnits() );
		renderedService.setAssignedPackage( assignedServices.getAssignedPackage() );
		renderedService.setReferenceNbr( assignedServices.getReferenceNumber() );
		renderedService.setReferenceType( assignedServices.getReferenceType() );
		RenderedServiceId renderedServiceId = new RenderedServiceId();
		renderedServiceId.setRenderedDtm( new Date() );
		renderedServiceId.setServiceUid( assignedServices.getServiceUid() );
		renderedService.setId( renderedServiceId );
		renderedServiceDAO.save( renderedService );
	}
	
	/**
	 * 
	 * @param assignedServices
	 * @param assignedServiceBM
	 * @return
	 * @throws Exception
	 */
	private String getNewStatusOfAssignedService( AssignedServices assignedServices, 
			                                      AssignedServiceBM assignedServiceBM ) throws Exception {
		
		String newStatus = assignedServices.getAssignedServiceStatus().getServiceStatusCode();
		
		if ( assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( assignedServiceBM.getAssignedServiceStatus().getCode() ) ) {
			if ( assignedServiceBM.getRenderedUnits() > 0 && 
				 assignedServiceBM.getRenderedUnits() > assignedServices.getRenderedUnits()	) {
				if ( assignedServiceBM.getRenderedUnits().intValue() == assignedServices.getRequestedUnits().intValue() ) {
					newStatus = ServicesConstants.ASSIGNED_SERVICE_RENDERED;
				} else {
					newStatus = ServicesConstants.ASSIGNED_SERVICE_PARTRENDERED;
				}
			} else if ( assignedServiceBM.getRenderedUnits() < assignedServices.getRenderedUnits() ) {
				throw new Exception( " New rendered units " 
						             + assignedServiceBM.getRenderedUnits() 
						             + " cannot be less than previous rendered unit " 
						             + assignedServices.getRenderedUnits() );
			} else {
				; // do nothing as neither status has changed nor the rendered units count
			}
		} else {
			//
			// New status is different from the existing one
			//
			if ( assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( ServicesConstants.ASSIGNED_SERVICE_REQUESTED ) ) {
				 
				if ( assignedServiceBM.getAssignedServiceStatus().getCode().equals( ServicesConstants.ASSIGNED_SERVICE_CANCELED ) ) {
					//
					// This request for modification must be for cancellation of services. 
					// Expectation is that number of rendered services already persisted and the number
					// of rendered services received through BM must be same
					//
					if ( assignedServiceBM.getRenderedUnits().intValue() == assignedServices.getRenderedUnits().intValue() ) {
						newStatus = ServicesConstants.ASSIGNED_SERVICE_CANCELED;
					} else {
						throw new Exception( " You cannot cancel an assigned service as well as update the rendered units count " );
					}
				} else if ( assignedServiceBM.getAssignedServiceStatus().getCode().equals( ServicesConstants.ASSIGNED_SERVICE_REPLACED  ) ) {
					//
					// This request for modification must be for cancellation of services. 
					// Expectation is that number of rendered services already persisted and the number
					// of rendered services received through BM must be same
					//
					if ( assignedServiceBM.getRenderedUnits().intValue() == 0 ) {
						newStatus = ServicesConstants.ASSIGNED_SERVICE_REPLACED;
					} else {
						throw new Exception( " You cannot replace an assigned service as well as update the rendered units count " );
					}
				}
				
			} else if ( assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( ServicesConstants.ASSIGNED_SERVICE_PARTRENDERED ) ) {
				
			if ( assignedServiceBM.getAssignedServiceStatus().getCode().equals( ServicesConstants.ASSIGNED_SERVICE_CANCELED ) ) {
				//
				// This request for modification must be for cancellation of services. 
				// Expectation is that number of rendered services already persisted and the number
				// of rendered services received through BM must be same
				//
				if ( assignedServiceBM.getRenderedUnits().intValue() == assignedServices.getRenderedUnits().intValue() ) {
					newStatus = ServicesConstants.ASSIGNED_SERVICE_PARTCANCELED;
				} else {
					throw new Exception( " You cannot cancel an assigned service as well as update the rendered units count " );
				}
			} else if ( assignedServiceBM.getAssignedServiceStatus().getCode().equals( ServicesConstants.ASSIGNED_SERVICE_REPLACED ) ) {
				//
				// This request for modification must be for replacement of remaining units of services. 
				// Expectation is that number of rendered services already persisted and the number
				// of rendered services received through BM must be same
				//
				if ( assignedServiceBM.getRenderedUnits().intValue() == assignedServices.getRenderedUnits().intValue() ) {
					newStatus = ServicesConstants.ASSIGNED_SERVICE_PARTREPLACED;
				} else {
					throw new Exception( " You cannot replace an assigned service as well as update the rendered units count " );
				} 
			} else if ( assignedServiceBM.getAssignedServiceStatus().getCode().equals( ServicesConstants.ASSIGNED_SERVICE_RENDERED ) ) {
					if ( assignedServiceBM.getRenderedUnits().intValue() == assignedServices.getRequestedUnits().intValue() ) {
						newStatus = ServicesConstants.ASSIGNED_SERVICE_RENDERED;
					} else {
						; 
						//
						// do nothing. 
						// User might have tried to render 1 or more units of this service. However, few requests are still pending.
						// The assigned service will continue to be in PENDINGRENDERED status.
						//
					}
				}
			}
			
		}
		
		return newStatus;
	}

	public ServiceBM modifyService(ServiceBM modifiedServiceBM) {
		try {
			
			Service service = dataModelManager.getServiceByCode( modifiedServiceBM.getServiceCode() );
			String currentStatus = service.getServiceStatus().getServiceStatusCode();
			String newStatus = modifiedServiceBM.getServiceStatus().getCode();
			
			Calendar today = Calendar.getInstance();
			
			if ( !currentStatus.equals( newStatus  ) ) {
				
				boolean transitionAllowed = this.checkForServiceStateTransitionValidity(currentStatus, newStatus);
				
				if ( !transitionAllowed ) {
					throw new Exception(" Service status change from STATUS_CODE = " 
							            + currentStatus
							            + " to STATUS_CODE = "
							            + newStatus 
							            + " is not allowed " );
				}	
			}
			
			Service dirtyService = convertServiceBM2DM( modifiedServiceBM, service );
			
			if ( !currentStatus.equals( newStatus  ) ) {
				if ( newStatus.equals( ServicesConstants.SERVICE_STATUS_ACTIVE ) ) {
					
					dirtyService.setSuspendedFromDt(null);
					dirtyService.setSuspendedToDt(null);
					
					//If the packages  related to services is marked suspended due to any of
					//the service suspension then check whether all the services of the package are
					//in active status; if yes then mark the associated package as active.
					//
					
					this.activateSuspendedPackageOfService(service, modifiedServiceBM.getPersonId());
					
				} else if ( newStatus.equals( ServicesConstants.SERVICE_STATUS_SUSPENDED ) ) {
					
					dirtyService.setSuspendedFromDt( today.getTime() );
//					dirtyService.setSuspendedToDt( modifiedServiceBM.getSuspendedToDt() );
					//Mark the related packages as suspended
					this.markPackageWithServiceAsSuspended(service, modifiedServiceBM.getPersonId() );
					
				} else if ( newStatus.equals( ServicesConstants.SERVICE_STATUS_EXPIRED ) ) {
					
					if ( dirtyService.getEffectiveToDt() == null || dirtyService.getEffectiveToDt().after( today.getTime() )  ) {
						dirtyService.setEffectiveToDt( today.getTime() );
						//Mark the related packages as suspended
						this.markPackageWithServiceAsSuspended(service, modifiedServiceBM.getPersonId() );
					}
					
				}
			}
			dirtyService.setLastModifiedDtm( today.getTime() );
			dirtyService.setModifiedBy( modifiedServiceBM.getPersonId() );
			serviceDAO.attachDirty( dirtyService );

			return this.convertServiceDM2BM(dirtyService);
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	
	/**
	 * Following state transition are allowed for services
	 * -----------------------------------------------------------
	 * From Status                To Status
	 * --------------			--------------------------------
	 * CREATED                  ACTIVE, SUSPENDED, EXPIRED
	 * ACTIVE                   SUSPENDED, EXPIRED
	 * SUSPENDED				ACTIVE, EXPIRED
	 * EXPIRED					Transition not allowed
	 * 
	 * @param currentStatus
	 * @param newStatus
	 * @return
	 */
	private boolean checkForServiceStateTransitionValidity( String currentStatus, String newStatus ) {
		boolean transitionAllowed = false;
		
		if ( currentStatus.equals( ServicesConstants.SERVICE_STATUS_CREATED ) ) {
			if ( newStatus.equals( ServicesConstants.SERVICE_STATUS_ACTIVE )	 ||
				 newStatus.equals( ServicesConstants.SERVICE_STATUS_SUSPENDED ) ||
				 newStatus.equals( ServicesConstants.SERVICE_STATUS_EXPIRED ) ) {
				
				transitionAllowed = true;
			}
		} else if ( currentStatus.equals( ServicesConstants.SERVICE_STATUS_ACTIVE ) ) {
			if ( newStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
				
				transitionAllowed = true;
			}
		} else if ( currentStatus.equals( ServicesConstants.SERVICE_STATUS_SUSPENDED ) ) {
			if (newStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE )   ||
				newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
					
				transitionAllowed = true;
			}
		} 
		
		return transitionAllowed;
	}
	
	/**
	 * This method marks the package associated with given service as suspended
	 * @param service
	 */
	private void markPackageWithServiceAsSuspended(Service service, String suspendedBy ){
		
		Calendar today = Calendar.getInstance();
		
		List<ServicePackage> servicePackageList = servicePackageDAO.getAvailablePackages( service.getServiceCode() );
		
		if( servicePackageList != null && !servicePackageList.isEmpty() ){
			
			for( ServicePackage servicePackage : servicePackageList ){
				
				ServicePackageStatus packageStatus = this.getServicePackageStatus(ServicesConstants.SERVICE_PACKAGE_SUSPENDED);
				servicePackage.setServicePackageStatus(packageStatus);
				servicePackage.setSuspendedFromDt( today.getTime() );
				servicePackage.setModificationDtm( today.getTime() );
				servicePackage.setModifiedBy(suspendedBy);
				servicePackage.setSuspendLevelFlag( ServicesConstants.PACKAGE_SUSPENSION_LEVEL_SERVICE );
				
				servicePackageDAO.attachDirty( servicePackage );
			}
		}
		
	}
	
	private Integer activateSuspendedPackageOfService(Service service, String activatedBy ){
		
		try {
			List<ServicePackage> servicePackageList = servicePackageDAO.getServiceLevelSuspendedPackages(service.getServiceCode());
			
			Integer activatedPackageCount = 0;
			
			if( servicePackageList != null && !servicePackageList.isEmpty() ){
				for( ServicePackage servicePackage : servicePackageList ){
					
					List<Service> activeServiceList = serviceDAO.findActiveServiceOfPackage(servicePackage.getPackageId());
					
					List<Service> allServiceList = serviceDAO.findAllServiceOfPackage(servicePackage.getPackageId());
					
					if( activeServiceList != null && !activeServiceList.isEmpty() &&
						allServiceList	!= null && !allServiceList.isEmpty() ){
						
						if(allServiceList.removeAll( activeServiceList )){

							//
							//Now allServiceList should contain only one service(unique)  
							
							if( new HashSet<Service>(allServiceList).size() <= 1 ){

								//This package should be marked as 'ACTIVE'
								//
								ServicePackageStatus packageStatus = this.getServicePackageStatus(ServicesConstants.SERVICE_PACKAGE_ACTIVE);
								servicePackage.setServicePackageStatus(packageStatus);
								servicePackage.setSuspendLevelFlag(null);
								servicePackage.setSuspendedFromDt(null);
								servicePackage.setSuspendedToDt( null );
								servicePackage.setModificationDtm( new Date() );
								servicePackage.setModifiedBy( activatedBy );
								servicePackageDAO.attachDirty( servicePackage );
								
								activatedPackageCount ++;
							}
							
						}
					}
				}
			}
			
			return activatedPackageCount;
		} catch ( Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	public ServiceGroupBM modifyServiceGroup( ServiceGroupBM modifiedServiceGroupBM ) {
		try {
			ServiceGroup serviceGrp = this.getServiceGroup( modifiedServiceGroupBM.getServiceGroupCode() );
			ServiceGroup dirtyServiceGrp = convertServiceGroupBM2DM(modifiedServiceGroupBM, serviceGrp);
			dirtyServiceGrp.setLastModifiedDtm( new Date() );
			dirtyServiceGrp.setModifiedBy( modifiedServiceGroupBM.getModifiedBy() );
			serviceGroupDAO.attachDirty(dirtyServiceGrp);
			
			//
			// Move all the services associated with this group into GENERAL (default) group
			//
			serviceDAO.changeGroupNameOfServicesForAGroup( dirtyServiceGrp.getServiceGroupCode(), 
						                                    ServicesConstants.DEFAULT_SERVICE_GROUP, 
						                                    modifiedServiceGroupBM.getModifiedBy() );
			
			List<ServiceSummaryBM>serviceSummaryBMList = modifiedServiceGroupBM.getServiceSummaryList();
			
			if ( serviceSummaryBMList != null && !serviceSummaryBMList.isEmpty() ) {
				
				for ( ServiceSummaryBM serviceSummaryBM : serviceSummaryBMList ) {
					Service service = dataModelManager.getServiceByCode( serviceSummaryBM.getServiceCode() );
					service.setServiceGroup( dirtyServiceGrp );
					service.setLastModifiedDtm(new Date());
					service.setModifiedBy( modifiedServiceGroupBM.getModifiedBy() );
					serviceDAO.attachDirty(service);
				}
			}
			
			
			return convertServiceGroupDM2BM( dirtyServiceGrp );
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_GROUP_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void removeService(ServiceBM serviceBM) {
		try {
			Service service = serviceDAO.findById(serviceBM.getServiceCode());
			serviceDAO.delete(service);
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_REMOVE_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void removeServiceGroup(ServiceGroupBM serviceGroupBM) {
		try {
			Validate.notNull(serviceGroupBM,"serviceGroupBM should not be null");
			this.removeServiceGroups(Arrays.asList( serviceGroupBM.getGroupName() ));
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_GROUP_REMOVE_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	

	
	
	/**
	 * Reference type acts as an indicator whether the patient was inpatient, outpatient, direct or emergency.
	 * 
	 */
	public List<AssignedServiceBM> getAssignedServicesList( String referenceNumber, 
			                                                String referenceType ) {
		try {
			AssignedServices assignedServicesObj = new AssignedServices();
			assignedServicesObj.setReferenceType( referenceType );
			assignedServicesObj.setReferenceNumber( referenceNumber );
			List<AssignedServices> assignedSvcList = assignedServicesDAO.findByExample( assignedServicesObj );
			
			List<AssignedServiceBM> assignedServiceBMList = null;
			
			if ( assignedSvcList != null && !assignedSvcList.isEmpty() ) {
				assignedServiceBMList =  new ArrayList<AssignedServiceBM>();
				
				for ( AssignedServices assignedServices : assignedSvcList ) {
					AssignedServiceBM assignedServiceBM = this.convertAssignedServicesDM2BM( assignedServices );
					assignedServiceBMList.add( assignedServiceBM );
				}
			}
			
			return assignedServiceBMList;
		} catch(Exception ex) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	/**
	 * 1) Calculate the next available service order number
	 * 2) Assign packages
	 * 3) Assign services
	 */
	public Integer assignSvcAndPackages( AssignSvcAndPackageBM assignSvcAndPackageBM ) {
		try {
			
			Validate.notNull(assignSvcAndPackageBM , "assignSvcAndPackageBM must not be null!");
			
			Integer newServiceOrderNbr;
			
			if( assignSvcAndPackageBM.getServiceOrderNumber() != null){
				
				newServiceOrderNbr = assignSvcAndPackageBM.getServiceOrderNumber();

			}
			else{
			
				LabRequisitionOrder labrequisitionOrder = this.createRequisitionOrder(assignSvcAndPackageBM);
				newServiceOrderNbr = labrequisitionOrder.getOrderNbr();

			}
			
			//
			// If the service has one or more packages then create assigned packages
			//
			List<AssignedPackageBM> assignedPackageBMList = assignSvcAndPackageBM.getAssignedPackageBMList();
			
			if ( assignedPackageBMList != null && !assignedPackageBMList.isEmpty() ) {
				
				for ( AssignedPackageBM assignedPackageBM : assignedPackageBMList ) {
					assignedPackageBM.setServiceOrderNumber(newServiceOrderNbr);
					assignedPackageBM.setDoctorId( assignSvcAndPackageBM.getDoctorId() );
					assignedPackageBM.setPatientId( assignSvcAndPackageBM.getPatientId() );
					assignedPackageBM.setReferenceType(assignSvcAndPackageBM.getReferenceType());
					assignedPackageBM.setReferenceNumber(assignSvcAndPackageBM.getReferenceNumber());
					assignedPackageBM.setCreatedBy(assignSvcAndPackageBM.getCreatedBy());
					
					this.assignPackageToAPatient( assignedPackageBM );
				}
			}
			
			List<AssignedServiceBM> assignedServiceBMList = assignSvcAndPackageBM.getAssignedServiceBMList();
			
			if ( assignedServiceBMList != null && !assignedServiceBMList.isEmpty() ) {
				
				for ( AssignedServiceBM assignedServiceBM : assignedServiceBMList ) {
					assignedServiceBM.setServiceOrderNumber( newServiceOrderNbr );
					assignedServiceBM.setDoctorId(assignSvcAndPackageBM.getDoctorId());
					assignedServiceBM.setPatientId(assignSvcAndPackageBM.getPatientId());
					assignedServiceBM.setReferenceType(assignSvcAndPackageBM.getReferenceType());
					assignedServiceBM.setReferenceNumber(assignSvcAndPackageBM.getReferenceNumber());
					assignedServiceBM.setCreatedBy(assignSvcAndPackageBM.getCreatedBy());
					this.assignServiceToAPatient( assignedServiceBM );
				}
			}
						
			return newServiceOrderNbr;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_ORDER_CREATION_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
		
	}
	
	

	/**
	 * 1) Create the package
	 * 2) Create service and package association
	 * 
	 */
	public void addServicePackage( ServicePackageBM servicePackageBM ) {
		try {
			//
			// The package must have one or more service associated with it
			//
			List<PackageHasServiceBM>packageHasServiceBMList = servicePackageBM.getServiceBMList();
			if ( packageHasServiceBMList != null && !packageHasServiceBMList.isEmpty() ) {
				
				ServicePackage servicePackage = this.convertServicePackageBM2DM(servicePackageBM, null );
				servicePackage.setCreationDate( new Date());
				servicePackage.setCreatedBy(servicePackageBM.getCreatedBy());
				
				//
				// If this is a service level override then we may not have the total discount 
				// amount and package effective charges available with us.
				//
				if ( servicePackageBM.getChargeOverrideLevel().equals( ServicesConstants.CHARGE_OVERRIDE_LEVEL_SERVICE ) ) {
					Double totalCharge = 0.0;
					Double totalDiscount = 0.0;
					
					for ( PackageHasServiceBM packageHasServiceBM : packageHasServiceBMList ) {
						
						totalCharge += packageHasServiceBM.getServiceCharge();
						
						if ( packageHasServiceBM.getDiscountType().equals( ServicesConstants.DISCOUNT_TYPE_ABSOLUTE ) ) {
							//
							// If the discount amount is absolute then it is assumed that the discount takes
							// care of number of units as well. Hence, we do not need to multiply again.
							//
							totalDiscount += packageHasServiceBM.getDiscountAmtPct();
						} else {
							totalDiscount += (  packageHasServiceBM.getServiceCharge() * packageHasServiceBM.getDiscountAmtPct() ) / 100.0;
						}
					}
					
					servicePackage.setEffectiveCharge( totalCharge - totalDiscount );
					servicePackage.setDiscountAmtPct( totalDiscount );
					servicePackage.setPackageCharge(totalCharge);
				}
				
				servicePackageDAO.save( servicePackage );
				
				for ( PackageHasServiceBM packageHasServiceBM : packageHasServiceBMList ) {
					//The package will be same for each assignment
					packageHasServiceBM.setServicePackage( new CodeAndDescription(servicePackageBM.getPackageId(),""));
					PackageHasService packageHasService = this.convertPackageHasServiceBM2DM(packageHasServiceBM, null);
					packageHasServiceDAO.save(packageHasService);
				}
			} else {
				throw new Exception(" Package must contain at least one service. ");
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_CREATION_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
		
	}


	/**
	 * Retrieve all the services and packages associated with the service order number
	 * TODO : based on how UI decides to use the history, business model needs to be 
     * modified and corresponding data needs to be populated in this section.
     * 
	 */
	public AssignSvcAndPackageBM getAssignedServicesAndPackages( Integer serviceOrderNumber, Boolean historyFlag ) {
		try {
			AssignSvcAndPackageBM assignSvcAndPackageBM = null;
			
			List<AssignedPackage> assignedPackageList = assignedPackageDAO.findByServiceOrderNumber(serviceOrderNumber);
			
			if ( assignedPackageList != null && !assignedPackageList.isEmpty()  ) {
				assignSvcAndPackageBM = new AssignSvcAndPackageBM();
				
				List<AssignedPackageBM>assignedPackageBMList = new ArrayList<AssignedPackageBM>(0);
				
				for ( AssignedPackage assignedPackage : assignedPackageList ) {
					AssignedPackageBM assignedPackageBM = this.convertAssignedPackageDM2BM(assignedPackage);
					assignedPackageBMList.add( assignedPackageBM );
					
					if ( assignSvcAndPackageBM.getDoctorId() == null ) {
						assignSvcAndPackageBM.setDoctorId( assignedPackageBM.getDoctorId() );
					}
					
					if ( assignSvcAndPackageBM.getPatientId() == null ) {
						assignSvcAndPackageBM.setPatientId( assignedPackageBM.getPatientId() );
					}
					
					if( assignSvcAndPackageBM.getReferenceNumber() == null){
						assignSvcAndPackageBM.setReferenceNumber(assignedPackageBM.getReferenceNumber());
					}
					
					if( assignSvcAndPackageBM.getReferenceType() == null ){
						assignSvcAndPackageBM.setReferenceType(assignedPackageBM.getReferenceType());
					}
				}
				
				assignSvcAndPackageBM.setAssignedPackageBMList(assignedPackageBMList);
			}
			
			List<AssignedServices>assignedServicesList = assignedServicesDAO.getServicesOfOrder( serviceOrderNumber, Boolean.FALSE, null );
			
			if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
				
				if ( assignSvcAndPackageBM == null ) {
					assignSvcAndPackageBM = new AssignSvcAndPackageBM();
				}
				
				List<AssignedServiceBM>assignedServicesBMList = new ArrayList<AssignedServiceBM>(0);
				
				for ( AssignedServices assignedServices : assignedServicesList ) {
					AssignedServiceBM assignedServiceBM = this.convertAssignedServicesDM2BM( assignedServices );
					assignedServicesBMList.add( assignedServiceBM );
					
					if ( assignSvcAndPackageBM.getDoctorId() == null ) {
						assignSvcAndPackageBM.setDoctorId( assignedServiceBM.getDoctorId() );
					}
					
					if ( assignSvcAndPackageBM.getPatientId() == null ) {
						assignSvcAndPackageBM.setPatientId( assignedServiceBM.getPatientId() );
					}
					
					if( assignSvcAndPackageBM.getReferenceNumber() == null ){
						assignSvcAndPackageBM.setReferenceNumber(assignedServiceBM.getReferenceNumber());
					}
					
					if( assignSvcAndPackageBM.getReferenceType() == null){
						assignSvcAndPackageBM.setReferenceType(assignedServiceBM.getReferenceType());
					}
				}
				
				assignSvcAndPackageBM.setAssignedServiceBMList(assignedServicesBMList);
			}
			
			if ( assignSvcAndPackageBM != null ) {
				assignSvcAndPackageBM.setServiceOrderNumber( serviceOrderNumber );
			}
			
			return assignSvcAndPackageBM;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_ORDER_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	


	/**
	 * This method bills all the unbilled & rendered services and packages.
	 * 1) For an IPD, a package will be billed even if single service has been rendered. 
	 * 2) Also, for IPD, a service will get billed (if it is not part of package) only when it is 
	 *    rendered. Also, if the billing processes will be responsible for updating the billed
	 *    units count correctly on the assigned services.  
	 * 3) For OPD, EMERGENCY and DIRECT reference type of service usage, if billing is run then
	 *    it will get billed even if it is not rendered 
	 * 
	 * From services perspective, we have two types of sub-processes
	 * 1) Assigned packages
	 * 2) Assigned services (which are not part of a package)
	 * 
	 * @param referenceType
	 * @param referenceNumber
	 * @param billNumber
	 * @param billedBy
	 * @return
	 */
	public BillDataBM billServiceDetails( String referenceType, String referenceNumber, Integer billNumber, String billedBy ) {
		try {
			BillDataBM billDataBM = null;
			Map<String, BillingSubprocessBM>billDataMap = null;
			
			List<AssignedPackage>assignedPackageList = assignedPackageDAO.getUnbilledAssignedPackages( referenceType, referenceNumber );
			
			if ( assignedPackageList != null && !assignedPackageList.isEmpty() ) {
				
				if ( billDataBM == null ) {
					billDataBM = new BillDataBM();
					billDataBM.setProcessName( ServicesConstants.SERVICE_PROCESS_NAME );
					billDataBM.setProcessTotalBillAmount( 0.0 );
				}
				
				billDataMap = billDataBM.getBillDetailsMap();
				
				if ( billDataMap == null || billDataMap.isEmpty() ) {
					billDataMap = new HashMap<String, BillingSubprocessBM>(0);
				}
				
				BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
				billingSubprocessBM.setContributeToFnclCharge( Boolean.TRUE );
				billingSubprocessBM.setRemarks("Billing for Packages assigned through " + referenceType );
				billingSubprocessBM.setSubProcessName( ServicesConstants.SERVICE_SUBPROCESS_PACKAGE );
				billingSubprocessBM.setSubProcessTotals(0.0);
				List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
				
				if ( referenceType.equals(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD ) ) {
					
					for ( AssignedPackage assignedPackage : assignedPackageList ) {
						//
						// Find out the if there is any items which has been rendered and it is not yet billed.
						// In such cases, this package should get billed.
						//
						List<RenderedService>renderedServicesList = renderedServiceDAO.getUnbilledRenderedServicesOfPackage( assignedPackage.getPackageAsgmId() );
						
						if ( renderedServicesList != null && !renderedServicesList.isEmpty() ) {
							//
							// This package must be billed completely and added into the bill data
							//
							this.billAssignedPackage(assignedPackage, billNumber, billedBy, billDetailsBMList);
							
							//TODO: SubProcessTotals  should be billed units(Column missing) * assignedPackage.getEffectiveCharge()
							billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + 
									(assignedPackage.getEffectiveCharge()* assignedPackage.getRequestedUnit()));
							
						}
					}
				} else {
					//
					// Bill irrespective of whether the services have been rendered or not
					//
					for ( AssignedPackage assignedPackage : assignedPackageList ) {		
						this.billAssignedPackage(assignedPackage, billNumber, billedBy, billDetailsBMList);
						
						//TODO: SubProcessTotals  should be billed units(Column missing) * assignedPackage.getEffectiveCharge()
						billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + 
											(assignedPackage.getEffectiveCharge() * assignedPackage.getRequestedUnit()));
					}
				}
				billingSubprocessBM.setBillDetailsList( billDetailsBMList );
				billDataMap.put(ServicesConstants.SERVICE_SUBPROCESS_PACKAGE, billingSubprocessBM );
				billDataBM.setBillDetailsMap(billDataMap);
				billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals());
			}
			
			List<AssignedServices>assignedServicesList = assignedServicesDAO.getUnbilledAssignedServices( referenceType, referenceNumber,false, null );
			
			if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
				//
				// Bill irrespective of whether the services have been rendered or not
				//
				if ( billDataBM == null ) {
					billDataBM = new BillDataBM();
					billDataBM.setProcessName( ServicesConstants.SERVICE_PROCESS_NAME );
					billDataBM.setProcessTotalBillAmount( 0.0 );
				}
				
				billDataMap = billDataBM.getBillDetailsMap();
				
				if ( billDataMap == null || billDataMap.isEmpty() ) {
					billDataMap = new HashMap<String, BillingSubprocessBM>(0);
				}
				
				BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
				billingSubprocessBM.setContributeToFnclCharge( Boolean.TRUE );
				billingSubprocessBM.setRemarks("Billing for services assigned through " + referenceType );
				billingSubprocessBM.setSubProcessName( ServicesConstants.SERVICE_SUBPROCESS_SERVICE );
				billingSubprocessBM.setSubProcessTotals(0.0);
				List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
				Integer itemSequenceNbr = 1;
				
				if ( referenceType.equals(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD ) ) {
					//
					// Since the services are assigned to inpatient, we must bill only those services which have been actually
					// rendered to them.
					//
					
					for ( AssignedServices assignedServices : assignedServicesList ) {
						Integer renderedBilledCount = this.markRenderedServicesOfAssignedServicesAsBilled(assignedServices, billNumber );
						
						if ( renderedBilledCount > 0 ) {
							assignedServices.setBilledUnits( assignedServices.getBilledUnits() + renderedBilledCount );
							assignedServices.setLastBillNbr(billNumber);
							assignedServices.setModifiedBy(billedBy);
							assignedServices.setLastModifiedDtm( new Date());
							assignedServices.setIsBillable(ServicesConstants.IS_BILLABLE_NO);
							assignedServicesDAO.attachDirty( assignedServices );
							
							BillDetailsBM billDetailsBM = this.getBillDetailsFromAssignedServices( assignedServices, renderedBilledCount );
							billDetailsBM.setItemSequenceNbr(itemSequenceNbr);
							billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + 
									( assignedServices.getServiceCharge() * assignedServices.getBilledUnits()) );
			
							billDetailsBMList.add( billDetailsBM );
							
						} else {
							; // do nothing. There was nothing to bill for this assigned service
						}
						
						itemSequenceNbr++;
					}
				} else {	
					
					for ( AssignedServices assignedServices : assignedServicesList ) {
						
						Integer billUnits = assignedServices.getRequestedUnits() -  assignedServices.getCanceledUnits();
						assignedServices.setBilledUnits( billUnits );
						assignedServices.setModifiedBy(billedBy);
						assignedServices.setLastModifiedDtm( new Date() );
						assignedServices.setLastBillNbr( billNumber );
						assignedServicesDAO.attachDirty( assignedServices );
						
						BillDetailsBM billDetailsBM = this.getBillDetailsFromAssignedServices(assignedServices, null );
						billDetailsBM.setItemSequenceNbr(itemSequenceNbr);
						billDetailsBMList.add( billDetailsBM );
						
						billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + 
												( assignedServices.getServiceCharge() * assignedServices.getBilledUnits()) );
						itemSequenceNbr++;
						
						this.markRenderedServicesOfAssignedServicesAsBilled(assignedServices, billNumber );
					}
					
				}
				
				billingSubprocessBM.setBillDetailsList( billDetailsBMList );
				billDataMap.put(ServicesConstants.SERVICE_SUBPROCESS_SERVICE, billingSubprocessBM );
				billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals());
			}
			
			if( billDataBM != null ){
				billDataBM.setBillDetailsMap(billDataMap);
			}
			return billDataBM;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_BILLING_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	private void billAssignedPackage( AssignedPackage assignedPackage, Integer billNumber, String billedBy, List<BillDetailsBM>billDetailsBMList  ) {
		assignedPackage.setBillNbr( billNumber );
		assignedPackage.setModifiedBy(billedBy);
		assignedPackage.setModificationDtm( new Date() );
		assignedPackageDAO.attachDirty( assignedPackage );
		
		List<AssignedServices>assignedServiceList = assignedServicesDAO.getServicesOfPackage( assignedPackage.getPackageAsgmId() );
		
		if ( assignedServiceList != null && !assignedServiceList.isEmpty() ) {
			this.markServicesOfAssignedPackageBilled( assignedPackage );
		}
		
		BillDetailsBM billDetailsBM = this.getBillDetailsFromAssignedPackage(assignedPackage);
		billDetailsBM.setItemSequenceNbr( billDetailsBMList.size() + 1 );
		billDetailsBMList.add( billDetailsBM );
	}
	
	private int markRenderedServicesOfAssignedServicesAsBilled( AssignedServices assignedServices, Integer billNumber ) {
		List<RenderedService>renderedServicesList = renderedServiceDAO.findByProperty( "id.serviceUid", assignedServices.getServiceUid() );
		int totalBilledUnits = 0;
		
		if ( renderedServicesList != null && !renderedServicesList.isEmpty() ) {
			
			for ( RenderedService renderedService : renderedServicesList ) {
				
				if ( renderedService.getBillNbr() == null  ) {
					
					renderedService.setBillNbr( billNumber );
					renderedServiceDAO.attachDirty(renderedService);
					totalBilledUnits += renderedService.getRenderedQty();
				}
			}
		}
		
		return totalBilledUnits;
	}
	
	
	private int getUnbilledRenderedServices( AssignedServices assignedServices ){
		List<RenderedService>renderedServicesList = renderedServiceDAO.findByProperty( "id.serviceUid", assignedServices.getServiceUid() );
		int totalBilledUnits = 0;
		
		if ( renderedServicesList != null && !renderedServicesList.isEmpty() ) {
			
			for ( RenderedService renderedService : renderedServicesList ) {
				
				if ( renderedService.getBillNbr() == null  ) {
					totalBilledUnits += renderedService.getRenderedQty();
				}
			}
		}
		
		return totalBilledUnits;
	}
	
	private BillDetailsBM getBillDetailsFromAssignedServices( AssignedServices assignedServices, Integer renderedBilledCount ) {
		BillDetailsBM billDetailsBM = new BillDetailsBM();
		Service service = assignedServices.getService();
		billDetailsBM.setDiscounts( service.getServiceCharge() - assignedServices.getServiceCharge() );
		if ( renderedBilledCount != null ) {
			billDetailsBM.setItemCount( renderedBilledCount );
		} else {
			billDetailsBM.setItemCount( assignedServices.getRequestedUnits() );
		}
		
		billDetailsBM.setItemName( service.getServiceName() );
		billDetailsBM.setItemPrice( assignedServices.getServiceCharge() );
		billDetailsBM.setItemSequenceNbr(1);
		billDetailsBM.setNetPrice( billDetailsBM.getItemPrice() * billDetailsBM.getItemCount() );
		
		return billDetailsBM;
	}
	
	private BillDetailsBM getBillDetailsFromAssignedPackage( AssignedPackage assignedPackage ) {
		BillDetailsBM billDetailsBM = new BillDetailsBM();
		billDetailsBM.setDiscounts( assignedPackage.getServicePackage().getPackageCharge() - assignedPackage.getServicePackage().getEffectiveCharge() );
		billDetailsBM.setItemCount( assignedPackage.getRequestedUnit() );
		billDetailsBM.setItemName( assignedPackage.getServicePackage().getName() );
		billDetailsBM.setItemPrice( assignedPackage.getServicePackage().getEffectiveCharge() );
		billDetailsBM.setItemSequenceNbr(1);
		billDetailsBM.setNetPrice( billDetailsBM.getItemCount() * billDetailsBM.getItemPrice()  );
		
		return billDetailsBM;
	}
	

	public BillDataBM getUbilledAssignedServices( String referenceType, String referenceNumber){

		try {
			BillDataBM billDataBM = null;
			Map<String, BillingSubprocessBM>billDataMap = null;
			
			List<AssignedPackage>assignedPackageList = assignedPackageDAO.getUnbilledAssignedPackages( referenceType, referenceNumber );
			
			if ( assignedPackageList != null && !assignedPackageList.isEmpty() ) {
				
				if ( billDataBM == null ) {
					billDataBM = new BillDataBM();
					billDataBM.setProcessName( ServicesConstants.SERVICE_PROCESS_NAME );
					billDataBM.setProcessTotalBillAmount( 0.0 );
				}
				
				billDataMap = billDataBM.getBillDetailsMap();
				
				if ( billDataMap == null || billDataMap.isEmpty() ) {
					billDataMap = new HashMap<String, BillingSubprocessBM>(0);
				}
				
				BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
				billingSubprocessBM.setContributeToFnclCharge( Boolean.TRUE );
				billingSubprocessBM.setRemarks("Billing for Packages assigned through " + referenceType );
				billingSubprocessBM.setSubProcessName( ServicesConstants.SERVICE_SUBPROCESS_PACKAGE );
				billingSubprocessBM.setSubProcessTotals(0.0);
				List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
				
				if ( referenceType.equals(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD ) ) {
					
					for ( AssignedPackage assignedPackage : assignedPackageList ) {
						//
						// Find out the if there is any items which has been rendered and it is not yet billed.
						// In such cases, this package should get billed.
						//
						List<RenderedService>renderedServicesList = renderedServiceDAO.getUnbilledRenderedServicesOfPackage( assignedPackage.getPackageAsgmId() );
						
						if ( renderedServicesList != null && !renderedServicesList.isEmpty() ) {
							//
							// This package must be billed completely and added into the bill data
							//
							billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + assignedPackage.getEffectiveCharge() );
							
						}
					}
				} else {
					//
					// Bill irrespective of whether the services have been rendered or not
					//
					for ( AssignedPackage assignedPackage : assignedPackageList ) {		
						billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + assignedPackage.getEffectiveCharge() );
					}
					
//					billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals() );
					
				}
				billingSubprocessBM.setBillDetailsList( billDetailsBMList );
				billDataMap.put(ServicesConstants.SERVICE_SUBPROCESS_PACKAGE, billingSubprocessBM );
				billDataBM.setBillDetailsMap(billDataMap);
				billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals());
			}
			
			List<AssignedServices>assignedServicesList = assignedServicesDAO.getUnbilledAssignedServices( referenceType, referenceNumber,true, null );
			
			if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
				//
				// Bill irrespective of whether the services have been rendered or not
				//
				if ( billDataBM == null ) {
					billDataBM = new BillDataBM();
					billDataBM.setProcessName( ServicesConstants.SERVICE_PROCESS_NAME );
					billDataBM.setProcessTotalBillAmount( 0.0 );
				}
				
				billDataMap = billDataBM.getBillDetailsMap();
				
				if ( billDataMap == null || billDataMap.isEmpty() ) {
					billDataMap = new HashMap<String, BillingSubprocessBM>(0);
				}
				
				BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
				billingSubprocessBM.setContributeToFnclCharge( Boolean.TRUE );
				billingSubprocessBM.setRemarks("Billing for services assigned through " + referenceType );
				billingSubprocessBM.setSubProcessName( ServicesConstants.SERVICE_SUBPROCESS_SERVICE );
				billingSubprocessBM.setSubProcessTotals(0.0);
				List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
				Integer itemSequenceNbr = 1;
				
				if ( referenceType.equals(ServicesConstants.SERVICE_ASSIGNED_FROM_IPD ) ) {
					//
					// Since the services are assigned to inpatient, we must bill only those services which have been actually
					// rendered to them.
					//
					
					for ( AssignedServices assignedServices : assignedServicesList ) {
						Integer renderedBilledCount = this.getUnbilledRenderedServices(assignedServices);
						
						if ( renderedBilledCount > 0 ) {
												
							BillDetailsBM billDetailsBM = this.getBillDetailsFromAssignedServices( assignedServices, renderedBilledCount );
							billDetailsBM.setItemSequenceNbr(itemSequenceNbr);
							billDetailsBMList.add( billDetailsBM );
							
						} else {
							; // do nothing. There was nothing to bill for this assigned service
						}
						billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + assignedServices.getServiceCharge() );
						itemSequenceNbr++;
					}
				} else {	
					
					for ( AssignedServices assignedServices : assignedServicesList ) {
						
						BillDetailsBM billDetailsBM = this.getBillDetailsFromAssignedServices(assignedServices, null );
						billDetailsBM.setItemSequenceNbr(itemSequenceNbr);
						billDetailsBMList.add( billDetailsBM );
						
						billingSubprocessBM.setSubProcessTotals( billingSubprocessBM.getSubProcessTotals() + assignedServices.getServiceCharge() );
						itemSequenceNbr++;
						
//						this.markRenderedServicesOfAssignedServicesAsBilled(assignedServices, billNumber );
					}
					
//					billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals() );
				}
				
				billingSubprocessBM.setBillDetailsList( billDetailsBMList );
				billDataMap.put(ServicesConstants.SERVICE_SUBPROCESS_SERVICE, billingSubprocessBM );
				billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() + billingSubprocessBM.getSubProcessTotals());
			}
			
			if( billDataBM != null ){
				billDataBM.setBillDetailsMap(billDataMap);
			}
			return billDataBM;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_BILLING_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	
		
	}
	
	/**
	 * This method returns BillDataBM object for given bill number with
	 * @return
	 */
	
	public BillDataBM getBillData( Integer billNumber ) {
		
			try {
				BillDataBM billDataBM = this.initializeBillData();
				Map<String, BillingSubprocessBM>billDetailsMap = billDataBM.getBillDetailsMap();
				
				List<AssignedPackage> assignedPackageList = assignedPackageDAO.getAssignedPackagesForBill(billNumber);
				
				if( assignedPackageList != null && !assignedPackageList.isEmpty() ){
					BillingSubprocessBM billingSubprocessBM = this.initializeBillingSubProcess(ServicesConstants.SERVICE_SUBPROCESS_PACKAGE,
																							   Boolean.TRUE);
					Double subProcessTotals = 0.0;
					List<BillDetailsBM>billDetailsBMList = billingSubprocessBM.getBillDetailsList();
					for( AssignedPackage assignedPackage : assignedPackageList ){
						
						BillDetailsBM billDetailsBM = new BillDetailsBM();
						billDetailsBM.setItemName(assignedPackage.getServicePackage().getName());
						billDetailsBM.setItemPrice(assignedPackage.getServicePackage().getEffectiveCharge());
						billDetailsBM.setItemCount(assignedPackage.getRequestedUnit());
						billDetailsBM.setItemSequenceNbr( billDetailsBMList.size() + 1 );
						billDetailsBM.setDiscounts(assignedPackage.getServicePackage().getPackageCharge() 
													- assignedPackage.getServicePackage().getEffectiveCharge() );
						billDetailsBM.setNetPrice(billDetailsBM.getItemCount() * billDetailsBM.getItemPrice());
						billDetailsBM.setTransactionDate(assignedPackage.getModificationDtm());
						
						billDetailsBMList.add( billDetailsBM );
						subProcessTotals += billDetailsBM.getNetPrice();
					}
					billingSubprocessBM.setSubProcessTotals(subProcessTotals);
					billDetailsMap.put( ServicesConstants.SERVICE_SUBPROCESS_PACKAGE, billingSubprocessBM);
				}
				
				List<AssignedServices>assignedServicesList = assignedServicesDAO.getAssignedServicesForBill(billNumber);
				
				if( assignedServicesList != null && !assignedServicesList.isEmpty() ){
					
					BillingSubprocessBM billingSubprocessBM = this.initializeBillingSubProcess(ServicesConstants.SERVICE_SUBPROCESS_SERVICE,
																								Boolean.TRUE);
					
					Double subProcessTotals = 0.0;
					List<BillDetailsBM>billDetailsBMList = billingSubprocessBM.getBillDetailsList();
					for( AssignedServices assignedServices : assignedServicesList ){
						
						BillDetailsBM billDetailsBM = new BillDetailsBM();
						
						Service service = assignedServices.getService();
						billDetailsBM.setDiscounts( service.getServiceCharge() - assignedServices.getServiceCharge() );
						
						billDetailsBM.setItemName(service.getServiceName());
						billDetailsBM.setItemPrice(assignedServices.getServiceCharge());
						billDetailsBM.setItemCount(assignedServices.getBilledUnits());
						billDetailsBM.setItemSequenceNbr( billDetailsBMList.size() + 1 );
						billDetailsBM.setNetPrice(billDetailsBM.getItemPrice().doubleValue() * billDetailsBM.getItemCount().doubleValue());
						billDetailsBM.setTransactionDate(assignedServices.getLastModifiedDtm());//TODO: review it.
						
						billDetailsBMList.add( billDetailsBM );
						subProcessTotals += billDetailsBM.getNetPrice();
					}
					billingSubprocessBM.setSubProcessTotals(subProcessTotals);
					billDetailsMap.put( ServicesConstants.SERVICE_SUBPROCESS_SERVICE, billingSubprocessBM);
				}
				
				return billDataBM;
			} catch (Exception e) {
				
				Fault fault = new Fault( ApplicationErrors.SERVICE_BILL_READ_FAILED );
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}
	}
	
	private BillDataBM initializeBillData() {
		BillDataBM billDataBM = new BillDataBM();
		billDataBM.setProcessName( ServicesConstants.SERVICE_PROCESS_NAME );
		billDataBM.setProcessTotalBillAmount(0.0);
		Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);
		billDataBM.setBillDetailsMap(billDetailsMap);
		
		return billDataBM;
	}
	
	private BillingSubprocessBM initializeBillingSubProcess( String subProcessName, Boolean contributesToCharge ) {
		BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
		billingSubprocessBM.setContributeToFnclCharge( contributesToCharge );
		billingSubprocessBM.setRemarks("");
		billingSubprocessBM.setSubProcessTotals(0.0);
		billingSubprocessBM.setSubProcessName( subProcessName );
		
		List<BillDetailsBM>billDetailsList = new ArrayList<BillDetailsBM>(0);
		billingSubprocessBM.setBillDetailsList(billDetailsList);
		
		return billingSubprocessBM;
	}

	public List<ServicePackageSummaryBM> getCurrentlyAvailableServicePackages() {
		try {
			List<ServicePackage> servicePackageList = servicePackageDAO.getAvailablePackages(null);
			List<ServicePackageSummaryBM> servicePackageSummaryBMList = null;
			
			if ( servicePackageList != null && !servicePackageList.isEmpty() ) {
				
				servicePackageSummaryBMList = new ArrayList<ServicePackageSummaryBM>(0);
				
				for ( ServicePackage servicePackage : servicePackageList ) {
					ServicePackageSummaryBM packageSummaryBM = this.convertServicePackageDM2Summary(servicePackage);
					servicePackageSummaryBMList.add( packageSummaryBM );
				}
			}
			
			return servicePackageSummaryBMList;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * Same as above method but with return type as ListRange.
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public ListRange  findAvailableServicePackages( int start, int count, String orderBy ){
		
		List<ServicePackageSummaryBM> servicePackageSummaryBMList = this.getCurrentlyAvailableServicePackages();
		
		List<ServicePackageSummaryBM> pageSizeData = new ArrayList<ServicePackageSummaryBM>();
		ListRange listRange = new ListRange();
		int index = 0;
		if (servicePackageSummaryBMList != null && servicePackageSummaryBMList.size() > 0) {
		while( (start+index < start + count) && (servicePackageSummaryBMList.size() > start+index) ){
			
			ServicePackageSummaryBM servicePackageSummaryBM = servicePackageSummaryBMList.get(start+index);
			pageSizeData.add( servicePackageSummaryBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(servicePackageSummaryBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
	}


	public List<ServicePackageSummaryBM> getServicePackages( String packageName,
															 String packageId, 
			                                                 String packageStatus, 
			                                                 String description,
			                                                 String chargeOverrideLevel,
			                                                 String ServiceCode,
			                                                 Date   createdAfter,
			                                                 Date   createdBefore,
			                                                 Date 	effectiveFromDt, 
			                                                 Date 	effectiveToDt) {
		try {
			List<ServicePackage> servicePackageList = servicePackageDAO.getServicePackages(packageName, packageId, packageStatus,
																						   description, chargeOverrideLevel, ServiceCode,
																						   createdAfter, createdBefore, effectiveFromDt,
																						   effectiveToDt);
			List<ServicePackageSummaryBM> servicePackageSummaryBMList = null;
			
			if ( servicePackageList != null && !servicePackageList.isEmpty() ) {
				
				servicePackageSummaryBMList = new ArrayList<ServicePackageSummaryBM>(0);
				
				for ( ServicePackage servicePackage : servicePackageList ) {
					ServicePackageSummaryBM packageSummaryBM = this.convertServicePackageDM2Summary(servicePackage);
					servicePackageSummaryBMList.add( packageSummaryBM );
				}
			}
			
			return servicePackageSummaryBMList;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	public ListRange findServicePackages(String packageName,
										 String packageId, 
							             String packageStatus, 
							             String description,
							             String chargeOverrideLevel,
							             String ServiceCode,
							             Date   createdAfter,
							             Date   createdBefore,
							             Date 	effectiveFromDt, 
							             Date 	effectiveToDt, 
							             int start, int count, String orderBy ){
		
	ListRange listRange = new ListRange();
	
	List<ServicePackageSummaryBM> servicePackageSummaryBMList = this.getServicePackages(  packageName, packageId, packageStatus,
																			description, chargeOverrideLevel, ServiceCode,
																			createdAfter, createdBefore, effectiveFromDt,
																			effectiveToDt);
		
		List<ServicePackageSummaryBM> pageSizeData = new ArrayList<ServicePackageSummaryBM>();
		int index = 0;
		if (servicePackageSummaryBMList != null && servicePackageSummaryBMList.size() > 0) {
		while( (start+index < start + count) && (servicePackageSummaryBMList.size() > start+index) ){
			
			ServicePackageSummaryBM servicePackageSummaryBM = servicePackageSummaryBMList.get(start+index);
			pageSizeData.add( servicePackageSummaryBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(servicePackageSummaryBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}

	public List<ServiceBM> getServices ( String serviceName,
							             String serviceCode,
									     String serviceGroup,
									     String departmentCode,
									     String serviceDescription,
									     String procedure,
									     String serviceStatusCode,
									     Date createdBefore,
									     Date createdAfter,
									     Double chargesFrom,
									     Double chargesTo ) {
		try {
			List<ServiceBM> serviceBMList = null;
			
			List<Service>serviceList = serviceDAO.findServices( serviceName, 
					                                            serviceCode, 
					                                            serviceGroup, 
					                                            departmentCode, 
					                                            serviceDescription, 
					                                            procedure, 
					                                            serviceStatusCode, 
					                                            createdBefore, 
					                                            createdAfter, 
					                                            chargesFrom, 
					                                            chargesTo );
			
			if ( serviceList != null && !serviceList.isEmpty() ) {
				
				serviceBMList = new ArrayList<ServiceBM>(0);
				
				for ( Service service : serviceList ) {
					ServiceBM serviceBM = this.convertServiceDM2BM(service);
					serviceBMList.add( serviceBM );
				}
			}
			
			return serviceBMList;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public ListRange  findServices ( String serviceName,
									 String serviceCode,
								     String serviceGroup,
								     String departmentCode,
								     String serviceDescription,
								     String procedure,
								     String serviceStatusCode,
								     Date createdBefore,
								     Date createdAfter,
								     Double chargesFrom,
								     Double chargesTo,
								     int start, int count, String orderBy ){
		
		 List<ServiceBM> serviceBMList = this.getServices(serviceName, serviceCode, serviceGroup,
				 										  departmentCode, serviceDescription, procedure,
				 									      serviceStatusCode, createdBefore, createdAfter,
				 									      chargesFrom, chargesTo);
		
		ListRange listRange = new ListRange();
		
		List<ServiceBM> pageSizeData = new ArrayList<ServiceBM>();
		int index = 0;
		if (serviceBMList != null && serviceBMList.size() > 0) {
		while( (start+index < start + count) && (serviceBMList.size() > start+index) ){
			
			ServiceBM serviceBM = serviceBMList.get(start+index);
			pageSizeData.add( serviceBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(serviceBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
	}
	
	
	public void modifyAssignedServiceToRendered(Integer serviceUID,
												Integer numberOfUnitsRendered, 
												String renderedBy ) {
		
		try {
			if( numberOfUnitsRendered != null && numberOfUnitsRendered > 0 ){
				AssignedServices assignedServices = this.getAssignedServices( serviceUID );
				
				
				Service service =  assignedServices.getService();
				
				if( !ServicesConstants.SERVICE_STATUS_ACTIVE.equals(service.getServiceStatus().getServiceStatusCode())){
					throw new Exception(" Service '" + service.getServiceName() + "' currently unavailable ");
				}
				
				if( ServicesConstants.ASSIGNED_SERVICE_DELETED.equals(assignedServices.getAssignedServiceStatus().getServiceStatusCode())){
					throw new Exception("Assigned service with UID=" + serviceUID + " is deleted.");
				}
				
				assignedServices.setRenderedUnits(assignedServices.getRenderedUnits() + numberOfUnitsRendered );
				assignedServices.setModifiedBy( renderedBy );
				assignedServices.setLastModifiedDtm( new Date() );
				
				Integer canceledUnits = 0;
				if( assignedServices.getCanceledUnits() != null   ){
					canceledUnits = assignedServices.getCanceledUnits();
				}
				
				if ( assignedServices.getRenderedUnits()  + canceledUnits  >  assignedServices.getRequestedUnits()  ) {
					throw new Exception("You cannot render more units("
										+ (assignedServices.getRenderedUnits() + numberOfUnitsRendered ) 
										+ ") than the requested units(" 
										+ assignedServices.getRequestedUnits() + ") + "
										+ "canceled units (" + canceledUnits + ")" );
				} else if ( assignedServices.getRenderedUnits().intValue() == assignedServices.getRequestedUnits().intValue() ) {
					//
					// All the services have been rendered. So, move the assigned service into RENDERED status
					//
					AssignedServiceStatus assignedServiceStatus = this.getAssignedServiceStatus( ServicesConstants.ASSIGNED_SERVICE_RENDERED );
					assignedServices.setAssignedServiceStatus(assignedServiceStatus);
					this.createAssignedServiceHistory(assignedServices);
				} else if ( assignedServices.getRenderedUnits()  < assignedServices.getRequestedUnits() &&
						    assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( ServicesConstants.ASSIGNED_SERVICE_REQUESTED ) ) {
					AssignedServiceStatus assignedServiceStatus = this.getAssignedServiceStatus( ServicesConstants.ASSIGNED_SERVICE_PARTRENDERED );
					assignedServices.setAssignedServiceStatus(assignedServiceStatus);
					this.createAssignedServiceHistory(assignedServices);
				}
				
				assignedServicesDAO.attachDirty( assignedServices );
				
				this.createRenderedServiceEntry(assignedServices, numberOfUnitsRendered );	
				
				//Update requisition status as well
				this.updateRequisitionStatus(assignedServices.getLabRequisitionOrder(), renderedBy);
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * Method to cancel assigned services.
	 * @param serviceUIDList
	 * @param canceledBy
	 */
	public void cancelAssignedServices(	Integer[] serviceUIDList,String canceledBy ){
		
		try {
			Set<AssignedPackage> packageAsgmIdList = new HashSet<AssignedPackage>(0);
			
			for( Integer serviceUID : serviceUIDList ){
				
				AssignedServices assignedServices = this.getAssignedServices( serviceUID );
				this.cancelAssignedSerive( assignedServices,canceledBy );

				AssignedPackage assignedPackage = assignedServices.getAssignedPackage(); 
				
				if(  assignedPackage != null   ){
					//this service is assigned through package, now check whether all services of this assigned package is
					//is canceled.
					//
					packageAsgmIdList.add( assignedPackage );
				}
			}
				
			if( packageAsgmIdList != null && !packageAsgmIdList.isEmpty()){
				
					for( AssignedPackage assignedPackage : packageAsgmIdList ){
						
						Integer countOfAssignedService = assignedServicesDAO.getCountOfServiceAssignedFromPkg(
																					assignedPackage.getPackageAsgmId(),null);
						
						Integer countOfCanceledServic = assignedServicesDAO.getCountOfServiceAssignedFromPkg(
															assignedPackage.getPackageAsgmId(),ServicesConstants.ASSIGNED_SERVICE_CANCELED);
						
						if( countOfAssignedService.equals( countOfCanceledServic )){
							// all of assigned services have been canceled 
							//mark the assigned package's status also as 'CANCELED'
							
							assignedPackage.setAssignedPackageStatus(this.getAssignedPackageStatus(ServicesConstants.ASSIGNED_PACKAGE_CANCELED)); 
							assignedPackage.setModificationDtm( new Date() );
							assignedPackage.setModifiedBy(canceledBy);
							
							assignedPackageDAO.attachDirty(assignedPackage);
							 
						} else{
							;  
							
							// 
							// Do nothing.
							// In order to inform lab or 'responsible department' of the service, that service has been cancel
							// we are allowing partial cancellation of service of package. Although billing will  happen for whole package.
							//
						}
							
					}
				}
		} catch ( Exception e) {

			Fault fault = new Fault(ApplicationErrors.ASSIGNED_SERVICE_CANCEL_FAILED);
			throw new HCISException( fault, e);
		}
		
	}
	
	/**
	 * This method cancels the assigned services to ant patient. 
	 * @param serviceUID
	 * @param canceledBy
	 * @return TODO
	 */
	
	private CodeAndDescription cancelAssignedSerive(	AssignedServices assignedServices,	String canceledBy ){
		
		try {
			AssignedServiceStatus newStatus = null;
			if( assignedServices != null ){
//				AssignedServices assignedServices = this.getAssignedServices( serviceUID );
				
							
				if( ServicesConstants.ASSIGNED_SERVICE_REQUESTED.equals(assignedServices.getAssignedServiceStatus().getServiceStatusCode()) ){
					//
					//Because still service is in requested state so cancel all requested units.
					//And mark the status of assignment as 'CANCELED'. 
					//
					
					assignedServices.setCanceledUnits(assignedServices.getRequestedUnits());
					newStatus = this.getAssignedServiceStatus(ServicesConstants.ASSIGNED_SERVICE_CANCELED);
					
					
				}else if( ServicesConstants.ASSIGNED_SERVICE_PARTRENDERED.equals(assignedServices.getAssignedServiceStatus().getServiceStatusCode()) ){
					//
					// Service is partially rendered so cancel the remaining assigned services.
					//And mark is status of assignment as 'PARTCANCELED'
					//
					
					assignedServices.setCanceledUnits( assignedServices.getRequestedUnits() - assignedServices.getRenderedUnits() );
					newStatus = this.getAssignedServiceStatus(ServicesConstants.ASSIGNED_SERVICE_PARTCANCELED);
				
					
				}else{
					//Here either the assigned service has canceled or fully rendered in both of the cases
					//Dont allow to cancel any.
					//
					
					throw new Exception("Cannot cancel assigned service '" + assignedServices.getService().getServiceName()
							+ "' with UID - " + assignedServices.getServiceUid() + ". As it is in '" 
							+ assignedServices.getAssignedServiceStatus().getDescription()+ "' status." );
					}
				assignedServices.setAssignedServiceStatus( newStatus);
				assignedServices.setModifiedBy( canceledBy );
				assignedServices.setLastModifiedDtm( new Date() );
				assignedServicesDAO.attachDirty( assignedServices );
				
				// As status of assigned services has changed so create a  Assigned Service History entry. 
				this.createAssignedServiceHistory(assignedServices);
				
				//Update requisition status as well
				this.updateRequisitionStatus(assignedServices.getLabRequisitionOrder(), canceledBy);
				
				CodeAndDescription newStatusToRet = new CodeAndDescription(newStatus.getServiceStatusCode(),
																			newStatus.getDescription());
				return newStatusToRet;
				}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		return null;//nothing to return
	}

	/**
	 * This method is similar to method "cancelAssignedServices(Integer[] serviceUIDList,String canceledBy )"
	 * 
	 * Except it takes only one serviceUID to cancel and returns the new status of assigned service.
	 * Created on UI-client requirement basis, as it needs Code and description on new status. 
	 * @return
	 */
	public CodeAndDescription cancelIndevidualAssignedService(	Integer serviceUID,String canceledBy){
		
		AssignedServices assignedServices = this.getAssignedServices(serviceUID);
		
		return this.cancelAssignedSerive(assignedServices, canceledBy);
	}
	
	/**
	 * If any of the service is render in the package then we can not cancel package
	 */
	
	public CodeAndDescription cancelAssignedPackages(Integer packageAsgmId , String cancelledBy){
		
		try {
			
			List<AssignedServices> assignedServicesList = assignedServicesDAO.findByProperty(AssignedServicesDAOExtn.PACKAGE_ASGM_ID, packageAsgmId);
			
			Integer[] serviceUIDList=null;
			
			if( assignedServicesList != null && assignedServicesList.size() > 0){
				serviceUIDList = new Integer[ assignedServicesList.size()];
				for( int i=0 ; i< assignedServicesList.size() ; i++){
					serviceUIDList[i] = assignedServicesList.get(i).getServiceUid();
				}
			}
			
			this.cancelAssignedServices(serviceUIDList, cancelledBy);
		
			AssignedPackage updatedAssignedPackage = this.getAssignedPackages(packageAsgmId);
			CodeAndDescription newPackageStatus = new CodeAndDescription(updatedAssignedPackage.getAssignedPackageStatus().getStatusCode(),
																			updatedAssignedPackage.getAssignedPackageStatus().getStatusCode());
			
			return newPackageStatus;
		}
		catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_PACKAGE_CANCEL_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	
	
//	private boolean isPackageEligibleForCancel(Integer packageAsgmId ){
//		try{
//			List<AssignedServices> assignedServicesList = assignedServicesDAO.findByProperty(AssignedServicesDAOExtn.PACKAGE_ASGM_ID, packageAsgmId);
//			
//			if( assignedServicesList != null && assignedServicesList.size()>0){
//				
//				for( AssignedServices assignedService : assignedServicesList){
//					if( assignedService.getAssignedServiceStatus().equals(this.getAssignedServiceStatus(ServicesConstants.ASSIGNED_SERVICE_RENDERED))
//							|| assignedService.getAssignedServiceStatus().equals(this.getAssignedServiceStatus(ServicesConstants.ASSIGNED_SERVICE_PARTRENDERED))){
//						
//						throw new Exception("Cannot cancel assigned service '" 
//								+ "' with service id - " + assignedService.getServiceUid() + ". As it is in '" 
//								+ assignedService.getAssignedServiceStatus().getDescription() + "' status." ); 
//					}
//				}
//			}
//			
//			return true;
//			
//		}
//		catch(Exception e){
//			Fault fault = new Fault( ApplicationErrors.IS_ELIGIBLE_FOR_CANCEL_FAILED );
//			
//			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
//									 fault.getFaultCode(),
//									 fault.getFaultType() );
//			
//		}
//		
//		
//	}
//		
	

	/**
	 * This method modifies an assigned package
	 * It ensures that assigned package is moving in the correct state and
	 * If the package itself is marked billed then corresponding services are also marked as billed.
	 */
	public void modifyAssignedPackage( AssignedPackageBM assignedPackageBM ) {
		try {
			AssignedPackage assignedPackage = this.getAssignedPackages( assignedPackageBM.getPackageAsgmId() );
			
			boolean isPackageBeingMarkedBilled = false;

			if ( !assignedPackageBM.getAssignedPackageStatus().getCode().equals( assignedPackage.getAssignedPackageStatus().getStatusCode() ) ) {
				//
				// We allow following state transition
				// ACTIVE to RENDERED, CANCELED or DELETED
				//
				if ( !assignedPackage.getAssignedPackageStatus().getStatusCode().equals(ServicesConstants.ASSIGNED_PACKAGE_REQUESTED )) {
					throw new Exception( " The assigned package with PACKAGE_ASGM_ID = "
							+ assignedPackageBM.getPackageAsgmId()
							+ " is in status = " 
							+ assignedPackage.getAssignedPackageStatus().getStatusCode()
							+ " which does not allow transition to status = "
							+ assignedPackageBM.getAssignedPackageStatus().getCode() );
				}
			}
			
			if ( assignedPackage.getBillNbr() == null && assignedPackageBM.getBillNbr() != null ) {
				isPackageBeingMarkedBilled = true;
			}
			 
			AssignedPackage assignedPackageDirty = this.convertAssignedPackageBM2DM( assignedPackageBM, assignedPackage );
			assignedPackageDirty.setModificationDtm( new Date() );
			assignedPackageDirty.setModifiedBy( assignedPackageBM.getModifiedBy() );
			assignedPackageDAO.attachDirty( assignedPackageDirty );
			
			if ( isPackageBeingMarkedBilled ) {
				//
				// Since this assigned package has been marked billed, we should also
				// mark associated services and rendered services as billed.
				//
				this.markServicesOfAssignedPackageBilled( assignedPackageDirty );
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_PACKAGE_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * 
	 * @param assignedPackage
	 */
	private void markServicesOfAssignedPackageBilled( AssignedPackage assignedPackage ) {
		List<AssignedServices> assignedServicesList = assignedServicesDAO.getServicesOfPackage( assignedPackage.getPackageAsgmId() );
		
		if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
			
			for ( AssignedServices assignedServices : assignedServicesList ) {
				assignedServices.setBilledUnits( assignedServices.getRequestedUnits() );
				assignedServices.setModifiedBy( assignedPackage.getModifiedBy() );
				assignedServices.setLastModifiedDtm( new Date() );
				assignedServicesDAO.attachDirty( assignedServices );
				
				List<RenderedService>renderedServiceList = renderedServiceDAO.findByProperty( "id.serviceUid", assignedServices.getServiceUid() );
				
				if ( renderedServiceList != null && !renderedServiceList.isEmpty() ) {
					for ( RenderedService renderedService : renderedServiceList ) {
						renderedService.setBillNbr( assignedPackage.getBillNbr() );
						renderedServiceDAO.attachDirty(renderedService);
					}
				}
			}
		}
	}

	/**
	 * This method allows user to modify the package details.
	 * Following modification will be allowed on packages:
	 * 1) Add / remove one or more services associated with the package
	 * 2) Update package details like override type, effective charge, override level, etc.
	 * Modification of type 1 or 2 will be allowed only when the package is not yet published. 
	 * 
	 * 3) A package can be suspended for a given period or for the indefinite period.
	 * 
	 * @param modifiedServicePackageBM
	 * @return
	 */
	public ServicePackageBM modifyServicePackage( ServicePackageBM modifiedServicePackageBM ) {
		try {
			ServicePackage servicePackage = this.getServicePackage( modifiedServicePackageBM.getPackageId() );
			
			if ( !servicePackage.getServicePackageStatus().getStatusCode().equals( ServicesConstants.SERVICE_PACKAGE_CREATED )  ) {
				//
				// Make sure following information has not changed
				// 1) Service list and associated details
				// 2) Package Charge
				// 3) Charge Override Level
				// 4) Discount Amount or Percentage 
				// 5) Discount Type
				// 6) Effective Charge
				//
				if ( !modifiedServicePackageBM.getChargeOverrideLevel().equals( servicePackage.getChargeOverrideLevel() ) 			  ||
					  modifiedServicePackageBM.getEffectiveCharge().doubleValue() != servicePackage.getEffectiveCharge().doubleValue()||
					 !modifiedServicePackageBM.getDiscountType().equals( servicePackage.getDiscountType() )                           || 
					  modifiedServicePackageBM.getDiscountAmountPct().doubleValue() != servicePackage.getDiscountAmtPct().doubleValue()||
					  modifiedServicePackageBM.getPackageCharge().doubleValue() != servicePackage.getPackageCharge().doubleValue()     ||
					 !this.hasServiceListBeenModified(modifiedServicePackageBM) ) {
					throw new Exception( " The package with PACKAGE_ID = " 
							             + modifiedServicePackageBM.getPackageId() 
							             + " is not in created status. Modification of basic information like"
							             +	" service list, package charge, discount or effective charge is not allowed. " );
				}
				
				//
				// Here we can modify package status and related information(suspended from/to dates) only. 
				//
				String currentPackageStatus = servicePackage.getServicePackageStatus().getStatusCode();
				String newPackageStatus =  modifiedServicePackageBM.getServicePackageStatus().getCode();
				
				if ( !newPackageStatus.equals( currentPackageStatus ) ) {
					
					boolean stateTransitionAllowed = this.checkForPackageStateTransitionValidity( currentPackageStatus , newPackageStatus );
					
					ServicePackageStatus servicePackageStatus = this.getServicePackageStatus( modifiedServicePackageBM.getServicePackageStatus().getCode() );
					if ( stateTransitionAllowed ) {
						
						servicePackage.setServicePackageStatus(servicePackageStatus);
						
						if ( newPackageStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ) {
							servicePackage.setSuspendedFromDt( new Date() );
							servicePackage.setSuspendLevelFlag( ServicesConstants.PACKAGE_SUSPENSION_LEVEL_PACKAGE);
//							servicePackage.setSuspendedToDt( modifiedServicePackageBM.getSuspendedToDt() );
						} else if ( newPackageStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
							servicePackage.setEffectiveToDt( new Date() );
						} else if ( newPackageStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE ) &&
								    servicePackage.getSuspendedFromDt() != null ) {
							
							//
							//Check whether all services of this package are in active status
							//
							
							List<Service> activeServiceList = serviceDAO.findActiveServiceOfPackage( servicePackage.getPackageId() );
							
							List<Service> serviceList = serviceDAO.findAllServiceOfPackage( servicePackage.getPackageId() );
							
							if( serviceList != null && !serviceList.isEmpty() && activeServiceList != null
													&& !activeServiceList.isEmpty()){
								
								if( serviceList.size() == activeServiceList.size()  ){

									// all services of this package are in active status
									servicePackage.setSuspendLevelFlag(null);
									servicePackage.setSuspendedFromDt( null );
									servicePackage.setSuspendedToDt( null );
								}
									
							}else{
								
								StringBuffer serviceName = new StringBuffer("");
								for( Service service : serviceList ){
									
									serviceName = serviceName.append( service.getServiceName() ).append( ", ");
								}
								
								throw new Exception(" Service(s) " + serviceName.toString() + " not in active status. " );
							}
						}
					}else{
						throw new Exception(" Status transition from '"+servicePackage.getServicePackageStatus().getDescription() +
								"' to '"+servicePackageStatus.getDescription()+"' is not valid! ");
					}
					
				}
				
				servicePackage.setDescription(modifiedServicePackageBM.getDescription());
				servicePackage.setName(modifiedServicePackageBM.getName());
				
			} else {
				//
				// Here we can do anything with this package
				//
				
					//
					// The package must have one or more service associated with it
					//
						List<PackageHasServiceBM>packageHasServiceBMList = modifiedServicePackageBM.getServiceBMList();
						
						if ( packageHasServiceBMList == null && packageHasServiceBMList.isEmpty() ) {
							throw new Exception(" Package must contain at least one service. ");
						}
						servicePackage = this.convertServicePackageBM2DM( modifiedServicePackageBM, servicePackage );
						
						//
						// If this is a service level override then we may not have the total discount 
						// amount and package effective charges available with us.
						//
						if ( modifiedServicePackageBM.getChargeOverrideLevel().equals( ServicesConstants.CHARGE_OVERRIDE_LEVEL_SERVICE ) ) {
							Double totalCharge = 0.0;
							Double totalDiscount = 0.0;
							
							for ( PackageHasServiceBM packageHasServiceBM : packageHasServiceBMList ) {
								
								totalCharge += packageHasServiceBM.getServiceCharge();
								
								if ( packageHasServiceBM.getDiscountType().equals( ServicesConstants.DISCOUNT_TYPE_ABSOLUTE ) ) {
									//
									// If the discount amount is absolute then it is assumed that the discount takes
									// care of number of units as well. Hence, we do not need to multiply again.
									//
									totalDiscount += packageHasServiceBM.getDiscountAmtPct();
								} else {
									totalDiscount += ( packageHasServiceBM.getServiceCharge() * packageHasServiceBM.getDiscountAmtPct() ) / 100.0;
								}
							}
							
							servicePackage.setEffectiveCharge( totalCharge - totalDiscount );
							servicePackage.setDiscountAmtPct( totalDiscount );
							servicePackage.setPackageCharge(totalCharge);
						}
				
				
				// Delete the old associations
					List<PackageHasService>packageHasServiceList = packageHasServiceDAO.findByProperty( "id.packageId", servicePackage.getPackageId() );
					
					if ( packageHasServiceList != null && !packageHasServiceList.isEmpty() ) {
						
						for ( PackageHasService packageHasService : packageHasServiceList ) {
							packageHasServiceDAO.delete(packageHasService);
						}
					}

				// Now create new associations 
					
				if ( packageHasServiceBMList != null && !packageHasServiceBMList.isEmpty() ) {
					for ( PackageHasServiceBM packageHasServiceBM : packageHasServiceBMList ) {
						packageHasServiceBM.setServicePackage( new CodeAndDescription(servicePackage.getPackageId(), servicePackage.getName() ));
						PackageHasService packageHasService = this.convertPackageHasServiceBM2DM(packageHasServiceBM, null);
						
						packageHasServiceDAO.save(packageHasService);
					}
				}
			}

			servicePackage.setModificationDtm( new Date() );
			servicePackage.setModifiedBy( modifiedServicePackageBM.getModifiedBy() );
			servicePackageDAO.attachDirty( servicePackage );
			
			return this.convertServicePackageDM2BM(servicePackage);
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * Allowed status transitions are
	 * CREATED --> PUBLISHED, ACTIVE, SUSPENDED, EXPIRED
	 * PUBLISHED --> ACTIVE, SUSPENDED, EXPIRED
	 * ACTIVE --> SUSPENDED, EXPIRED
	 * SUSPENDED --> ACTIVE, EXPIRED
	 * EXPIRED --> Transition not allowed
	 * 
	 * @param currentStatus
	 * @param newStatus
	 * @return
	 */
	private boolean checkForPackageStateTransitionValidity( String currentStatus, String newStatus ) {
		
		boolean transitionAllowed = false;
		
		if ( currentStatus.equals( ServicesConstants.SERVICE_PACKAGE_CREATED ) ) {
			if ( newStatus.equals( ServicesConstants.SERVICE_PACKAGE_PUBLISHED ) || 
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE )	 ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
				
				transitionAllowed = true;
			}
		} else if ( currentStatus.equals( ServicesConstants.SERVICE_PACKAGE_PUBLISHED ) ) {
			if ( newStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE )	 ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
				
				transitionAllowed = true;
			}
		} else if ( currentStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE ) ) {
			if ( newStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ||
				 newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
				
				transitionAllowed = true;
			}
		} else if ( currentStatus.equals( ServicesConstants.SERVICE_PACKAGE_SUSPENDED ) ) {
			if (newStatus.equals( ServicesConstants.SERVICE_PACKAGE_ACTIVE )   ||
				newStatus.equals( ServicesConstants.SERVICE_PACKAGE_EXPIRED ) ) {
					
				transitionAllowed = true;
			}
		} 
		
		return transitionAllowed;
	}
	
	/**
	 * Make sure that business model consists of same set of services that
	 * are already persisted.
	 * 
	 * @param servicePackageBM
	 * @return
	 */
	private boolean hasServiceListBeenModified( ServicePackageBM servicePackageBM ) {
		boolean hasSameServiceList = true;
		List<PackageHasService> packageHasServiceList = packageHasServiceDAO.findByProperty("id.packageId", servicePackageBM.getPackageId() );
		
		List<PackageHasServiceBM>packageHasServiceBMList = servicePackageBM.getServiceBMList();
		
		if ( packageHasServiceBMList != null && !packageHasServiceBMList.isEmpty() ) {
			if ( packageHasServiceList != null && !packageHasServiceList.isEmpty() ) {
				
				if ( packageHasServiceList.size() != packageHasServiceBMList.size() ) {
					hasSameServiceList = false;
				} else {
					for ( PackageHasServiceBM packageHasServiceBM : packageHasServiceBMList ) {
						boolean modelDataSameAsPersistedData = false;
						
						for ( PackageHasService packageHasService : packageHasServiceList ) {
							if (packageHasServiceBM.getService() != null &&
								packageHasServiceBM.getService().getCode().equals( packageHasService.getId().getServiceCode() ) ) {
								modelDataSameAsPersistedData = true;
								break;
							}
						}
						
						if ( !modelDataSameAsPersistedData ) {
							//
							// At least one service in business model is not matching the persisted services
							//
							hasSameServiceList = false;
							break;
						}
					}
				}

			} else {
				hasSameServiceList = false;
			}
		} else {
			if ( packageHasServiceList != null && !packageHasServiceList.isEmpty() ) {
				hasSameServiceList = false;
			}
		}
		
		return hasSameServiceList;
	}

	/**
	 * Ensure that none of the services of this service order is in processed status.
	 * 
	 */
	public void removeAssignedPackageAndServices(Integer serviceOrderNumber, String removedBy ) {
		try {
			List<AssignedServices>assignedServicesProcessedList = assignedServicesDAO.getProcessedServicesOfOrder( serviceOrderNumber );
			
			if ( assignedServicesProcessedList != null && !assignedServicesProcessedList.isEmpty() ) {
				throw new Exception( " The assigned services or packages of service order with SERVICE_ORDER_NUMBER = " 
						             + serviceOrderNumber
						             +  " has one or more processed service associated with it. " );
			}
			
			List<AssignedPackage>assignedPackageList = assignedPackageDAO.findByServiceOrderNumber(serviceOrderNumber);
			
			if ( assignedPackageList != null && !assignedPackageList.isEmpty() ) {
				for ( AssignedPackage assignedPackage : assignedPackageList ) {
					this.deleteAssignedPackage(assignedPackage, removedBy );
				}
			}
			
			List<AssignedServices>assignedServicesList = assignedServicesDAO.getServicesOfOrder( serviceOrderNumber, Boolean.FALSE, null );
			
			if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
				for ( AssignedServices assignedServices : assignedServicesList ) {
					this.deleteIndividualAssignedServices(assignedServices, removedBy);
				}
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_ORDER_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}

	/**
	 * None of the services of the package should be processed
	 * Assigned package should be moved to DELETED status
	 * The assigned services related to the assigned packages should be moved to deleted status
	 * 
	 */
	public void removeAssignedPackages(List<Integer> packageAsgmIdList, String removedBy ) {
		try {
			if ( packageAsgmIdList != null && !packageAsgmIdList.isEmpty() ) {
				for ( Integer packageAsgmId : packageAsgmIdList ) {
					
					AssignedPackage assignedPackage = this.getAssignedPackages( packageAsgmId );
					this.deleteAssignedPackage(assignedPackage, removedBy);
				}
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_PACKAGE_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}
	
	/**
	 * This method checks whether a package is eligible for deletion or not.
	 * If all the assigned services are in REQUESTED status then delete them
	 * one-by-one using deleteIndividualAssignedServices, which internally
	 * creates the assigned service history as well.
	 * 
	 * @param assignedPackage
	 */
	private void deleteAssignedPackage(AssignedPackage assignedPackage, String modifiedBy ) throws Exception {
		
		//
		// Ensure that package is in REQUESTED status
		//
		if ( !assignedPackage.getAssignedPackageStatus().getStatusCode().equals( ServicesConstants.ASSIGNED_PACKAGE_REQUESTED ) ) {
			throw new Exception(" The assigned package with PACKAGE_ASGM_ID = " 
					            + assignedPackage.getPackageAsgmId() 
					            + " must be in REQUESTED status. Currently it is in  "
					            + assignedPackage.getAssignedPackageStatus().getStatusCode()
					            + " status and deletion is not permitted. ");
		}
		
		//
		// ensure that all the assigned services associated with the package are still
		// in requested status.
		//
		List<AssignedServices>assignedServicesProcessedList = assignedServicesDAO.getProcessedServicesOfPackage( assignedPackage.getPackageAsgmId() );
		
		if ( assignedServicesProcessedList != null && !assignedServicesProcessedList.isEmpty() ) {
			throw new Exception( " The assigned package with ASSIGNED_PACKAGE_ID = " 
					             + assignedPackage.getPackageAsgmId() 
					             +  " has one or more processed service associated with it. " );
		}
		
		AssignedPackageStatus assignedPackageStatus = this.getAssignedPackageStatus( ServicesConstants.ASSIGNED_PACKAGE_DELETED );
		assignedPackage.setAssignedPackageStatus(assignedPackageStatus);
		assignedPackage.setModifiedBy(modifiedBy);
		assignedPackage.setModificationDtm( new Date() );
		assignedPackageDAO.attachDirty( assignedPackage );
		
		//
		// All the services are in unprocessed status
		// Delete individual assigned services.
		//
		List<AssignedServices>assignedServicesList = assignedServicesDAO.getServicesOfPackage( assignedPackage.getPackageAsgmId() );
		
		if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
			for ( AssignedServices assignedServices : assignedServicesList ) {
				this.deleteIndividualAssignedServices(assignedServices, modifiedBy );
			}
		}
	}
	


	/**
	 * This method marks the assigned service as deleted.
	 * If assigned service is part of a package and all services of the assigned package is
	 * deleted successfully the mark the assigned package also as deleted.
	 * It must be in requested status.
	 * 
	 */
	public void removeAssignedServices( Integer[] serviceUidList, String removedBy ) {
		try {
			Set<AssignedPackage> assignedPackageList = new HashSet<AssignedPackage>(0);
			
			if ( serviceUidList != null && serviceUidList.length >0 ) {
				for ( Integer serviceUid : serviceUidList ) {
					AssignedServices assignedServices = this.getAssignedServices(serviceUid);

					if ( !assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( ServicesConstants.ASSIGNED_SERVICE_REQUESTED ) ) {
						throw new Exception(" The assigned service '"+ assignedServices.getService().getServiceName() +"', must be in REQUESTED status. Currently it is in  " 
								+ assignedServices.getAssignedServiceStatus().getServiceStatusCode()  +" status. ");
					}
					
					if( assignedServices.getBilledUnits().intValue() > 0   ){
						throw new Exception(" assigned services '" +  assignedServices.getService().getServiceName() +"' is already billed " );
					}
					this.deleteIndividualAssignedServices(assignedServices, removedBy);
					
					if ( assignedServices.getAssignedPackage() != null ) {
					
						// here the service is assigned through package, so add packageAsgmId to process
						//with package deletion
						
						assignedPackageList.add( assignedServices.getAssignedPackage() );
					}
				}
				
				//Now start deleting eligible packages
				this.markAssignedPkgDeleted(new ArrayList<AssignedPackage>(assignedPackageList), removedBy);
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}
	
	private void markAssignedPkgDeleted(List<AssignedPackage> assignedPackageList, String removedBy ) throws Exception{
		
		if( assignedPackageList != null && !assignedPackageList.isEmpty() ){
			for( AssignedPackage assignedPackage : assignedPackageList ){
				//
				// Ensure that package is in REQUESTED status
				//
				if ( !assignedPackage.getAssignedPackageStatus().getStatusCode().equals( ServicesConstants.ASSIGNED_PACKAGE_REQUESTED ) ) {
					throw new Exception(" The assigned package with PACKAGE_ASGM_ID = " 
							            + assignedPackage.getPackageAsgmId() 
							            + " must be in REQUESTED status. Currently it is in  "
							            + assignedPackage.getAssignedPackageStatus().getStatusCode()
							            + " status and deletion is not permitted. ");
				}
				
				Integer countOfAssignedService = assignedServicesDAO.getCountOfServiceAssignedFromPkg(
																			assignedPackage.getPackageAsgmId(),null);
				
				Integer countOfCanceledServic = assignedServicesDAO.getCountOfServiceAssignedFromPkg(
													assignedPackage.getPackageAsgmId(),ServicesConstants.ASSIGNED_SERVICE_DELETED);
				
				if( countOfAssignedService.intValue() !=  countOfCanceledServic.intValue() ){
					throw new Exception(" Partial deletion for assigned services which are assigned through package (PACKAGE_ASGM_ID = " 
				            + assignedPackage.getPackageAsgmId() + ") is not permitted. ");
				}
				// all of assigned services has been Deleted 
				//mark the assigned package's status also as 'DELETED'			
				AssignedPackageStatus assignedPackageStatus = this.getAssignedPackageStatus( ServicesConstants.ASSIGNED_PACKAGE_DELETED );
				assignedPackage.setAssignedPackageStatus(assignedPackageStatus);
				assignedPackage.setModifiedBy(removedBy);
				assignedPackage.setModificationDtm( new Date() );
				assignedPackageDAO.attachDirty( assignedPackage );
				
			}
		}
	}

	/**
	 * This method removes an existing service. If this service is associated with
	 * one or more package or it has been assigned to any patient then foreign key 
	 * constraint will be thrown and corresponding deletion will fail.
	 * 
	 */
	public void removeServices(List<String> serviceCodeList ) {
		try {
			if(serviceCodeList !=null && serviceCodeList.size()>0){
				for(String serviceCode: serviceCodeList){
					Service service = dataModelManager.getServiceByCode(serviceCode);
					serviceDAO.delete(service);
				}
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	/**
	 * This method removes an existing service group. If this service group is associated with
	 * one or more service then foreign key constraint will be thrown and corresponding 
	 * deletion will fail.
	 * 
	 */
	public void removeServiceGroups( List<String> serviceGroupCodeList ) {
		try {
			
			if( serviceGroupCodeList != null && serviceGroupCodeList.size() > 0 ){
				for(String serviceGroupCode : serviceGroupCodeList){
					
					if( ServicesConstants.DEFAULT_SERVICE_GROUP.equals(serviceGroupCode.trim())){
						throw new Exception(" Deletion of default group '"+ServicesConstants.DEFAULT_SERVICE_GROUP
																				+"' is not allowed!");
					}
					
					ServiceGroup serviceGroup = this.getServiceGroup(serviceGroupCode);
					
					List<ServiceGroup>  parentServiceGroupList = serviceGroupDAO.findByParentGroupCode(serviceGroupCode);
					
					if(parentServiceGroupList != null && parentServiceGroupList.size() > 0 ){
						//
						//This group is parent of one or more service group, so do not allow to delete this group
						
						throw new Exception("Service group '" +serviceGroup.getGroupName() +"' is parent of"+
											"one or more service group(s)");
					}
					
					List<Service> serviceList = serviceDAO.findServicesOfGroup(serviceGroupCode);
					
					if( serviceList != null && !serviceList.isEmpty() ){
						for( Service service : serviceList ){
							
							service.setServiceGroup(this.getServiceGroup(ServicesConstants.DEFAULT_SERVICE_GROUP));
							serviceDAO.attachDirty(service);
						}
					}
					
					serviceGroupDAO.delete( serviceGroup );
				}
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_GROUP_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}

	/**
	 * This method deletes a newly created package or packages. If the package is assigned to
	 * any patient then the deletion is not allowed.
	 * While deleting the package, associated services entry in the package_has_services
	 * table also gets deleted.
	 * 
	 */
	public void removeServicePackages(List<String> servicePackageIdList ) {
		try {
			if( servicePackageIdList != null && !servicePackageIdList.isEmpty() ){
				
				for( String servicePackageId : servicePackageIdList ){
					
					ServicePackage servicePackage = this.getServicePackage( servicePackageId );
					
					List<AssignedPackage>assignedPackageList = assignedPackageDAO.getAssignedPackages(servicePackageId);
					
					if ( assignedPackageList != null && assignedPackageList.size() > 0 ) {
						throw new Exception("Service package with PACKAGE_ID = " + servicePackageId + " has been assigned to one or more patient ");
					}
					
					List<PackageHasService>packageHasServiceList = packageHasServiceDAO.findByProperty("id.packageId", servicePackageId);
					
					if ( packageHasServiceList != null && !packageHasServiceList.isEmpty() ) {
						
						for ( PackageHasService packageHasService : packageHasServiceList ) {
							packageHasServiceDAO.delete( packageHasService );
						}
					}
					
					servicePackageDAO.delete(servicePackage);
				}
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_REMOVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}
	
	public void modifyServiceAndPackageAssignment(
										Integer serviceOrderNumber,
										AssignedPackageBM[] newPackageBMList, 
                                        AssignedPackageBM[] deletedPackageBMList, 
                                        AssignedServiceBM[] newServiceBMList,
                                        AssignedServiceBM[] deletedServiceBMList,
										String modifiedBy ) {
		try {
			List<AssignedServices>servicesAssociatedWithOrderList = assignedServicesDAO.findByProperty(this.SERVICE_ORDER_NBR,serviceOrderNumber);
			Calendar today = Calendar.getInstance();
			
			if ( servicesAssociatedWithOrderList == null || servicesAssociatedWithOrderList.isEmpty()  ) {
				throw new Exception(" The service order with ORDER_NUMBER = " + serviceOrderNumber + " does not exist " );
			}
			//referenceNumber, referenceType and patientId will be same for compleate list
			String referenceNumber= servicesAssociatedWithOrderList.get(0).getReferenceNumber();
			String referenceType  = servicesAssociatedWithOrderList.get(0).getReferenceType();
			Integer patientId = null;
			if( servicesAssociatedWithOrderList.get(0).getPatient()!= null ){
				 patientId     = servicesAssociatedWithOrderList.get(0).getPatient().getPatientId();
			}
			Integer doctorId = servicesAssociatedWithOrderList.get(0).getDoctorId();
			
			if ( newPackageBMList != null && newPackageBMList.length >0 ) {

				for ( AssignedPackageBM assignedPackageBM : newPackageBMList ) {
					assignedPackageBM.setServiceOrderNumber(serviceOrderNumber);
					assignedPackageBM.setReferenceNumber(referenceNumber);
					assignedPackageBM.setReferenceType(referenceType);
					assignedPackageBM.setPatientId(patientId);
					assignedPackageBM.setDoctorId(doctorId);
					this.assignPackageToAPatient(assignedPackageBM);
				}
			}
			
			if ( deletedPackageBMList != null && deletedPackageBMList.length >0 ) {
				
				for ( AssignedPackageBM deletedAssignedPackageBM : deletedPackageBMList ) {
					AssignedPackage assignedPackage = this.getAssignedPackages( deletedAssignedPackageBM.getPackageAsgmId() );
					this.deleteAssignedPackage(assignedPackage, modifiedBy);
				}
			}
			
			if ( newServiceBMList != null && newServiceBMList.length > 0 ) {
				
				for ( AssignedServiceBM assignedServiceBM : newServiceBMList ) {
//					assignedServiceBM.setModifiedBy(modifiedBy);
					assignedServiceBM.setServiceOrderNumber(serviceOrderNumber);
					assignedServiceBM.setReferenceNumber(referenceNumber);
					assignedServiceBM.setReferenceType(referenceType);
					assignedServiceBM.setPatientId(patientId);
					assignedServiceBM.setDoctorId(doctorId);
					
					this.assignServiceToAPatient(assignedServiceBM);
				}
			}
			
			if ( deletedServiceBMList != null && deletedServiceBMList.length > 0 ) {
				for ( AssignedServiceBM deletedAssignedServiceBM : deletedServiceBMList ) {
					AssignedServices assignedServices = this.getAssignedServices( deletedAssignedServiceBM.getServiceUid() );
					this.deleteIndividualAssignedServices(assignedServices, modifiedBy );
				}
			}
				
		} catch ( Exception e ) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_ORDER_UPDATE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * 
	 * @param servicePackage
	 * @return
	 */
	private ServicePackageBM convertServicePackageDM2BM( ServicePackage servicePackage ) {
		ServicePackageBM servicePackageBM = new ServicePackageBM();
		servicePackageBM.setChargeOverrideLevel( servicePackage.getChargeOverrideLevel() );
		servicePackageBM.setCreatedBy( servicePackage.getCreatedBy() );
		servicePackageBM.setCreationDate( servicePackage.getCreationDate() );
		servicePackageBM.setDescription( servicePackage.getDescription() );
		servicePackageBM.setDiscountAmountPct( servicePackage.getDiscountAmtPct() );
		servicePackageBM.setDiscountType( servicePackage.getDiscountType() );
		servicePackageBM.setEffectiveCharge( servicePackage.getEffectiveCharge() );
		servicePackageBM.setEffectiveFromDt( servicePackage.getEffectiveFromDt() );
		servicePackageBM.setEffectiveToDt( servicePackage.getEffectiveToDt() );
		servicePackageBM.setModificationDtm( servicePackage.getModificationDtm() );
		servicePackageBM.setModifiedBy( servicePackage.getModifiedBy() );
		servicePackageBM.setName( servicePackage.getName() );
		servicePackageBM.setPackageCharge( servicePackage.getPackageCharge() );
		servicePackageBM.setPackageId( servicePackage.getPackageId() );
		
		ServicePackageStatus servicePackageStatus = servicePackage.getServicePackageStatus();
		servicePackageBM.setServicePackageStatus( new CodeAndDescription( servicePackageStatus.getStatusCode(), servicePackageStatus.getDescription() ));
		
		return servicePackageBM;
	}
	
	/**
	 * 
	 * @param servicePackageBM
	 * @param existingServicePackage
	 * @return
	 */
	private ServicePackage convertServicePackageBM2DM( ServicePackageBM servicePackageBM, ServicePackage existingServicePackage ) {
		
		ServicePackage servicePackage = null;
		
		if ( existingServicePackage != null ) {
			servicePackage = existingServicePackage;
		} else {
			servicePackage = new ServicePackage();	
			servicePackage.setPackageId( servicePackageBM.getPackageId() );
			servicePackage.setCreatedBy( servicePackageBM.getCreatedBy() );
		}
		
		servicePackage.setName( servicePackageBM.getName() );
		
		if( servicePackageBM.getChargeOverrideLevel() != null ){
			servicePackage.setChargeOverrideLevel( servicePackageBM.getChargeOverrideLevel() );
		}else{
			//set default value
			servicePackage.setChargeOverrideLevel( ServicesConstants.CHARGE_OVERRIDE_LEVEL_PACKAGE );
		}
		servicePackage.setDescription( servicePackageBM.getDescription() );
		
		if ( servicePackageBM.getDiscountAmountPct() != null ) {
			servicePackage.setDiscountAmtPct( servicePackageBM.getDiscountAmountPct() );
		} else {
			servicePackage.setDiscountAmtPct( 0.0 );
		}
		
		
		if(servicePackageBM.getDiscountType() != null ){
			servicePackage.setDiscountType( servicePackageBM.getDiscountType() );
		} else {
			//set default value
			servicePackage.setDiscountType( ServicesConstants.DISCOUNT_TYPE_ABSOLUTE );
		}
		
		if ( servicePackageBM.getEffectiveCharge() != null ) {
			servicePackage.setEffectiveCharge( servicePackageBM.getEffectiveCharge() );
		} else {
			servicePackage.setEffectiveCharge( 0.0 );
		}
		
		servicePackage.setEffectiveFromDt( servicePackageBM.getEffectiveFromDt() );
		servicePackage.setEffectiveToDt( servicePackageBM.getEffectiveToDt() );
		
		if ( servicePackageBM.getPackageCharge() != null ){
			servicePackage.setPackageCharge(servicePackageBM.getPackageCharge());
		} else{
			servicePackage.setPackageCharge(0.00);
		}
		
		ServicePackageStatus servicePackageStatus = null;
		
		if ( servicePackageBM.getServicePackageStatus() != null ) {
			servicePackageStatus = this.getServicePackageStatus( servicePackageBM.getServicePackageStatus().getCode() );
		} else {
			if ( servicePackage.getServicePackageStatus() == null ) {
				servicePackageStatus = this.getServicePackageStatus( ServicesConstants.SERVICE_PACKAGE_CREATED );
			}
		}
		
		if ( servicePackageStatus != null ) {
			servicePackage.setServicePackageStatus(servicePackageStatus);
		}

		return servicePackage;
	}
	
	/**
	 * 
	 * @param servicePackageStatusCode
	 * @return
	 */
	private ServicePackageStatus getServicePackageStatus( String servicePackageStatusCode ) {
		try {
			ServicePackageStatus servicePackageStatus = servicePackageStatusDAO.findById( servicePackageStatusCode );
			
			if ( servicePackageStatus == null ) {
				throw new Exception( " Service package status with PACKAGE_STATUS_CODE = " + servicePackageStatusCode + " does not exist. " );
			}
			
			return servicePackageStatus;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_STATUS_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	
	/**
	 * 
	 * @param packageHasServiceBM
	 * @param existingPackageHasService
	 * @return
	 */
	private PackageHasService convertPackageHasServiceBM2DM( PackageHasServiceBM packageHasServiceBM, PackageHasService existingPackageHasService ) {
		PackageHasService packageHasService = null;
		
		if ( existingPackageHasService != null ) {
			packageHasService = existingPackageHasService;
		} else {
			packageHasService = new PackageHasService();
			Service service = dataModelManager.getServiceByCode( packageHasServiceBM.getService().getCode() );
			packageHasService.setService(service);
			
			ServicePackage servicePackage = this.getServicePackage( packageHasServiceBM.getServicePackage().getCode() );
			packageHasService.setServicePackage(servicePackage);
			
			PackageHasServiceId packageHasServiceId = new PackageHasServiceId();
			packageHasServiceId.setPackageId( servicePackage.getPackageId() );
			packageHasServiceId.setServiceCode( service.getServiceCode() );
			packageHasService.setId( packageHasServiceId );
		}
		
		if( packageHasServiceBM.getDiscountAmtPct() != null  ){
			packageHasService.setDiscountAmtPct( packageHasServiceBM.getDiscountAmtPct() );
		}else{
			packageHasService.setDiscountAmtPct( 0.0 );
		}
		if( packageHasServiceBM.getDiscountType() != null ){
			packageHasService.setDiscountType( packageHasServiceBM.getDiscountType() );
		}else{
			packageHasService.setDiscountType( ServicesConstants.DISCOUNT_TYPE_ABSOLUTE );
		}
		if( packageHasServiceBM.getEffectiveCharge() != null ){
			packageHasService.setEffectiveCharge( packageHasServiceBM.getEffectiveCharge() );
		}else{
			packageHasService.setEffectiveCharge( 0.0 );
		}
		
		packageHasService.setNumberOfUnits( packageHasServiceBM.getNumberOfUnits() );
		packageHasService.setServiceCharge( packageHasServiceBM.getServiceCharge() );
		
		return packageHasService;
	}
	
	
	
	/**
	 * This method retrieves a service package for a given package identifier.
	 * 
	 * @param packageId
	 * @return
	 */
	private Service getService( String serviceCode ) {
		try {
			
			Service service = serviceDAO.findById( serviceCode ); 
			
			if ( service == null ) {
				throw new Exception( " Service with code = " + serviceCode + " does not exist. " );
			}
			
			return service;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}
	
	/**
	 * This method retrieves a service package for a given package identifier.
	 * 
	 * @param packageId
	 * @return
	 */
	private ServicePackage getServicePackage( String packageId ) {
		try {
			ServicePackage servicePackage = servicePackageDAO.findById( packageId ); 
			
			if ( servicePackage == null ) {
				throw new Exception( " Service package with PACKAGE_ID = " + packageId + " does not exist. " );
			}
			
			return servicePackage;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	/**
	 * 
	 * @param assignedServiceStatusCode
	 * @return
	 */
	private AssignedServiceStatus getAssignedServiceStatus( String assignedServiceStatusCode ) {
		try {
			AssignedServiceStatus assignedServiceStatus = assignedServiceStatusDAO.findById( assignedServiceStatusCode );
			
			if ( assignedServiceStatus == null ) {
				throw new Exception(" Assigned service status with STATUS_CODE = " + assignedServiceStatusCode + " does not exist ") ;
			}
			
			return assignedServiceStatus;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_STATUS_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
/**
	 * 
	 * 
	 * @param serviceUid
	 * @return
	 */
	private AssignedServices getAssignedServices( Integer serviceUid ) {
		try {
			AssignedServices assignedServices = assignedServicesDAO.findById( serviceUid );
			
			if ( assignedServices == null ) {
				throw new Exception(" Assigned services with SERVICE_UID = " + serviceUid + " does not exist" );
			}
			
			return assignedServices;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * This method creates assigned service history
	 * Assumption is that this method will be called after the creation of assigned service
	 * request as well as the modification of such requests.
	 * 
	 * @param assignedServices -- the most recent assigned service object
	 */
	private void createAssignedServiceHistory( AssignedServices assignedServices ) {
		AssignedServiceHistory assignedServiceHistory = new AssignedServiceHistory();
		Calendar cal = Calendar.getInstance();
		assignedServiceHistory.setAssignedServices(assignedServices);
		assignedServiceHistory.setAssignedServiceStatus( assignedServices.getAssignedServiceStatus() );
		assignedServiceHistory.setCreatedBy( (assignedServices.getModifiedBy() != null) ? assignedServices.getModifiedBy() : assignedServices.getCreatedBy() );
		
		AssignedServiceHistoryId assignedServiceHistoryId = new AssignedServiceHistoryId();
		assignedServiceHistoryId.setServiceUid( assignedServices.getServiceUid() );
		assignedServiceHistoryId.setCreatedDtm(cal.getTime());
		assignedServiceHistoryId.setChangeStatusCode( assignedServices.getAssignedServiceStatus().getServiceStatusCode() );
		assignedServiceHistory.setId(assignedServiceHistoryId);
		assignedServiceHistoryDAO.save(assignedServiceHistory);
	}
	
	/**
	 * 
	 * @param assignedPackageStatusCode
	 * @return
	 */
	private AssignedPackageStatus getAssignedPackageStatus( String assignedPackageStatusCode ) {
		try {
			AssignedPackageStatus assignedPackageStatus = assignedPackageStatusDAO.findById( assignedPackageStatusCode );
			
			if ( assignedPackageStatus == null ) {
				throw new Exception(" Assigned Package Status with ASSIGN_STATUS_CODE = " 
						            + assignedPackageStatusCode
						            + " does not exist. ");
			}
			
			return assignedPackageStatus;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_PACKAGE_STATUS_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * 
	 * @param assignedServices
	 * @param removedBy
	 */
	private void deleteIndividualAssignedServices( AssignedServices assignedServices, String removedBy  ) {
		AssignedServiceStatus assignedServiceStatus = this.getAssignedServiceStatus( ServicesConstants.ASSIGNED_SERVICE_DELETED );
		assignedServices.setAssignedServiceStatus(assignedServiceStatus);
		assignedServices.setModifiedBy(removedBy);
		assignedServices.setLastModifiedDtm( new Date() );
		assignedServicesDAO.attachDirty(assignedServices);
		
		this.createAssignedServiceHistory(assignedServices);
		
		//Update requisition status as well
		this.updateRequisitionStatus(assignedServices.getLabRequisitionOrder(), removedBy);
	}
	
	/**
	 * 
	 * 
	 * @param packageAsgmId
	 * @return
	 */
	private AssignedPackage getAssignedPackages( Integer packageAsgmId ) {
		try {
			AssignedPackage assignedPackage = assignedPackageDAO.findById( packageAsgmId );
			
			if ( assignedPackage == null ) {
				throw new Exception(" Assigned package with PACKAGE_ASGM_ID = " + packageAsgmId + " does not exist" );
			}
			
			return assignedPackage;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_PACKAGE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * 
	 * @param serviceGroupCode
	 * @return
	 */
	private ServiceGroup getServiceGroup( String serviceGroupCode ) {
		ServiceGroup serviceGroup = serviceGroupDAO.findById( serviceGroupCode ) ;
		
		if ( serviceGroup == null ) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_GROUP_NOT_FOUND );
			fault.setFaultMessage( fault.getFaultMessage() + ". SERVICE_STATUS_CODE = " + serviceGroupCode );
			HCISException hcisException = new HCISException( fault );
			throw hcisException;
		}
		
		return serviceGroup;
	}
	
	/**
	 * 
	 * @param serviceStatusCode
	 * @return
	 */
	private ServiceStatus getServiceStatus( String serviceStatusCode ) {
		ServiceStatus serviceStatus = serviceStatusDAO.findById( serviceStatusCode );
		
		if ( serviceStatus == null ) {
			Fault fault = new Fault(ApplicationErrors.SERVICE_STATUS_NOT_FOUND );
			fault.setFaultMessage( fault.getFaultMessage() + ". SERVICE_STATUS_CODE = " + serviceStatusCode );
			HCISException hcisException = new HCISException( fault );
			throw hcisException;
		}
		
		return serviceStatus;
	}
	
	/**
	 * 
	 * @param serviceGroupBM
	 * @param transientSvcGrp
	 * @return
	 */
	private ServiceGroup convertServiceGroupBM2DM(ServiceGroupBM serviceGroupBM, ServiceGroup transientSvcGrp) {
		ServiceGroup serviceGroup;
		
		if (transientSvcGrp != null) {
			serviceGroup = transientSvcGrp;
		} else {
			serviceGroup = new ServiceGroup();
			serviceGroup.setCreatedBy(serviceGroupBM.getCreatedBy());
			serviceGroup.setServiceGroupCode(serviceGroupBM.getServiceGroupCode());
		}

		serviceGroup.setGroupName(serviceGroupBM.getGroupName());
		
		if( serviceGroupBM.getParentGroup()!=null && 
			serviceGroupBM.getParentGroup().getCode() != null && 
			!serviceGroupBM.getParentGroup().getCode().equals("")) {
			
			ServiceGroup tmpSvcGrp = this.getServiceGroup( serviceGroupBM.getParentGroup().getCode() );
			serviceGroup.setServiceGroup(tmpSvcGrp);	
		}else{
			serviceGroup.setServiceGroup( null );
		}
		
		return serviceGroup;
	}
	
	private ServiceBM convertServiceDM2BM(Service service) {
		ServiceBM serviceBM = new ServiceBM();
		
		CodeAndDescription department = new CodeAndDescription();
		department.setCode(service.getDepartment().getDepartmentCode());
		department.setDescription(service.getDepartment().getDepartmentName());
		serviceBM.setDepartment(department);
		serviceBM.setDepartmentName(department.getDescription());
		
		serviceBM.setServiceCharge(service.getServiceCharge());
		serviceBM.setServiceCode(service.getServiceCode());
	
		serviceBM.setServiceName(service.getServiceName());
		
		CodeAndDescription status = new CodeAndDescription();
		status.setCode(service.getServiceStatus().getServiceStatusCode());
		status.setDescription(service.getServiceStatus().getDescription());
		serviceBM.setServiceStatus(status);
		
		if( service.getServiceGroup() != null ){
			CodeAndDescription serviceGroup = new CodeAndDescription();
			serviceGroup.setCode(service.getServiceGroup().getServiceGroupCode());
			serviceGroup.setDescription(service.getServiceGroup().getGroupName());
			serviceBM.setServiceGroup(serviceGroup);
		}
		serviceBM.setDepositAmount(service.getDepositAmt());
		serviceBM.setEffectiveFromDate(service.getEffectiveFromDt());
		serviceBM.setEffectiveToDate(service.getEffectiveToDt());
		serviceBM.setServiceDesc(service.getServiceDesc());
		serviceBM.setServiceProcedure(service.getServiceProcedure());
		
		ReferenceDataList serviceTypeRefdata =  dataModelManager.getReferenceData(ServicesConstants.SERVICE_TYPE,
											service.getServiceTypeCd());
		
		if( serviceTypeRefdata != null ){
			
			CodeAndDescription serviceType = new CodeAndDescription(serviceTypeRefdata.getId().getAttrCode(),
																	serviceTypeRefdata.getAttrDesc());
			
			serviceBM.setServiceType(serviceType);
		}
		
		return serviceBM;
	}
	
	private ServiceGroupBM convertServiceGroupDM2BM(ServiceGroup serviceGroup) {
		ServiceGroupBM serviceGroupBM = new ServiceGroupBM();
		
		serviceGroupBM.setGroupName(serviceGroup.getGroupName());
		if(serviceGroup.getServiceGroup()!=null) {
			serviceGroupBM.setParentGroup(new CodeAndDescription(serviceGroup.getServiceGroup().getServiceGroupCode(), serviceGroup.getServiceGroup().getGroupName()));
		}
		serviceGroupBM.setServiceGroupCode(serviceGroup.getServiceGroupCode());

		List< Service> serviceList = serviceDAO.findServicesOfGroup(serviceGroup.getServiceGroupCode());
		List<ServiceSummaryBM> serviceSummaryBMList = new ArrayList<ServiceSummaryBM>(0);
		
		if( serviceList != null && !serviceList.isEmpty() ){
			for( Service service : serviceList ){
				
				ServiceSummaryBM serviceSummaryBM = new ServiceSummaryBM();
				
				serviceSummaryBM.setServiceCode(service.getServiceCode() );
				serviceSummaryBM.setServiceName( service.getServiceName() );
				serviceSummaryBM.setServiceCharge(service.getServiceCharge() );
				
				ServiceStatus serviceStatus = service.getServiceStatus();
				if( serviceStatus != null ){
					serviceSummaryBM.setServiceStatus(new CodeAndDescription(serviceStatus.getServiceStatusCode(),serviceStatus.getDescription()));
				}
				serviceSummaryBMList.add( serviceSummaryBM );
			}
			serviceGroupBM.setServiceSummaryList(serviceSummaryBMList);
		}
		
		return serviceGroupBM;
	}
	
	/**
	 * 
	 * @param assignedServiceBM
	 * @param existingAssignedServices
	 * @return
	 */
	private AssignedServices convertAssignedServicesBM2DM( AssignedServiceBM assignedServiceBM, 
			                                               AssignedServices existingAssignedServices ) {
		AssignedServices assignedServices = null;
		
		if ( existingAssignedServices != null ) {
			assignedServices = existingAssignedServices;
		} else {
			assignedServices = new AssignedServices();
			
			LabRequisitionOrder requisitionOrder = labRequisitionOrderDAO.findById(assignedServiceBM.getServiceOrderNumber());
			assignedServices.setLabRequisitionOrder( requisitionOrder  );
			
			if ( assignedServiceBM.getAssignedServiceStatus() == null ||
				 assignedServiceBM.getAssignedServiceStatus().getCode() == null	) {
				assignedServiceBM.setAssignedServiceStatus( new CodeAndDescription(ServicesConstants.ASSIGNED_SERVICE_REQUESTED, null) );
			}
		}
		
		AssignedServiceStatus assignedServiceStatus = this.getAssignedServiceStatus( assignedServiceBM.getAssignedServiceStatus().getCode() );
		assignedServices.setAssignedServiceStatus(assignedServiceStatus);
		
		if(assignedServiceBM.getBilledUnits() != null ){
			assignedServices.setBilledUnits( assignedServiceBM.getBilledUnits() );
		}else{
			assignedServices.setBilledUnits( ServicesConstants.DEFAULT_BILLED_UNITS );//default unit
		}
		if(assignedServiceBM.getCanceledUnits()!= null ){
			assignedServices.setCanceledUnits( assignedServiceBM.getCanceledUnits() );
		}else{
			assignedServices.setCanceledUnits( ServicesConstants.DEFAULT_CANCELED_UNITS);//default value
		}
		
		 
		assignedServices.setDoctorId( assignedServiceBM.getDoctorId() );
		
		if(assignedServiceBM.getPackageAsgmId() != null ){
			AssignedPackage assignedPackage = this.getAssignedPackages( assignedServiceBM.getPackageAsgmId() );
			assignedServices.setAssignedPackage(assignedPackage);
		}
		
		
		if ( assignedServiceBM.getPatientId() != null ) {
			Patient patient = dataModelManager.getPatient( assignedServiceBM.getPatientId() );
			assignedServices.setPatient(patient);
		}
		
		if( assignedServiceBM.getReferenceNumber() != null ){
			assignedServices.setReferenceNumber( assignedServiceBM.getReferenceNumber() );
		}
		
		assignedServices.setReferenceType( assignedServiceBM.getReferenceType() );
		assignedServices.setRemarks( assignedServiceBM.getRemarks() );
		if(assignedServiceBM.getRenderedUnits() != null ){
			assignedServices.setRenderedUnits( assignedServiceBM.getRenderedUnits() );
		}else{
			assignedServices.setRenderedUnits( ServicesConstants.DEFAULT_RENDERED_UNITS);//default value
		}
		
		if( assignedServiceBM.getRequestedUnits() != null ){
			assignedServices.setRequestedUnits( assignedServiceBM.getRequestedUnits() );
		}else{
			assignedServices.setRequestedUnits( ServicesConstants.DEFAULT_REQUESTED_UNITS );
		}
		if( assignedServiceBM.getService() != null && assignedServiceBM.getService().getCode() != null 
											&& !assignedServiceBM.getService().getCode().equals("")){
			Service service = dataModelManager.getServiceByCode( assignedServiceBM.getService().getCode() );
			assignedServices.setService(service);
			
			//TODO: Service charge should be equal to the charge defined in service table
			if(assignedServices.getServiceCharge() == null){
				// Null condition kept because in case of appointment,
				// based on the doctor and department, the service charge differs.
				assignedServices.setServiceCharge( service.getServiceCharge());
			}
				
		}
		
		if(assignedServiceBM.getServiceCharge()!= null){
			assignedServices.setServiceCharge( assignedServiceBM.getServiceCharge() );
		}else{
			//Dont set; already set.
		}
		
		assignedServices.setServiceDate( assignedServiceBM.getServiceDate() );
		assignedServices.setLastBillNbr( assignedServiceBM.getLastBillNumber());
		
		return assignedServices;
	}
	 
	/**
	 * 
	 * @param assignedServiceDM
	 * @return
	 */
	private AssignedServiceBM convertAssignedServicesDM2BM( AssignedServices  assignedServiceDM ) {
		AssignedServiceBM assignedServiceBM = new AssignedServiceBM();
		
		AssignedServiceStatus assignedServiceStatus = assignedServiceDM.getAssignedServiceStatus();
		assignedServiceBM.setAssignedServiceStatus( new CodeAndDescription( assignedServiceStatus.getServiceStatusCode(), assignedServiceStatus.getDescription() ) );
		assignedServiceBM.setBilledUnits( assignedServiceDM.getBilledUnits() );
		assignedServiceBM.setCanceledUnits( assignedServiceDM.getCanceledUnits() );
		assignedServiceBM.setCreatedBy( assignedServiceDM.getCreatedBy() );
		assignedServiceBM.setCreateDtm( assignedServiceDM.getCreateDtm() );
		assignedServiceBM.setDoctorId( assignedServiceDM.getDoctorId() );
		assignedServiceBM.setLastModifiedDtm( assignedServiceDM.getLastModifiedDtm() );
		assignedServiceBM.setModifiedBy( assignedServiceDM.getModifiedBy() );
		AssignedPackage assignedPackage = assignedServiceDM.getAssignedPackage(); 
		if(assignedPackage != null ){
			assignedServiceBM.setPackageAsgmId( assignedPackage.getPackageAsgmId() );
			//set the service package to BM
			CodeAndDescription servicePackage = new CodeAndDescription();
			servicePackage.setCode( assignedPackage.getServicePackage().getPackageId());
			servicePackage.setDescription( assignedPackage.getServicePackage().getName() );
			assignedServiceBM.setServicePackage(servicePackage);
		}

		if( assignedServiceDM.getPatient() != null ){
			assignedServiceBM.setPatientId( assignedServiceDM.getPatient().getPatientId() );
		}
		
		assignedServiceBM.setReferenceType( assignedServiceDM.getReferenceType() );
		assignedServiceBM.setReferenceNumber(assignedServiceDM.getReferenceNumber());

		if( RegistrationConstants.REFERENCE_TYPE_OPD.equals(assignedServiceDM.getReferenceType() )){
			
			//Service is assigned through the appointment, so set appointment date
			Appointments appointments = null;
			if( assignedServiceDM.getReferenceNumber().indexOf("-") >= 0 ){
				String[] appiontmentNumber =assignedServiceDM.getReferenceNumber().split("-");
				appointments = dataModelManager.getAppointment( Integer.parseInt( appiontmentNumber[1] ));
				assignedServiceBM.setReferenceNumber( appiontmentNumber[1] );
			}else{
				appointments = dataModelManager.getAppointment( Integer.parseInt( assignedServiceDM.getReferenceNumber() ));
				assignedServiceBM.setReferenceNumber( assignedServiceDM.getReferenceNumber() );
			}
			assignedServiceBM.setAppointmentDate(appointments.getAppointmentDate());
			
		}
		
		assignedServiceBM.setRemarks( assignedServiceDM.getRemarks() );
		assignedServiceBM.setRenderedUnits( assignedServiceDM.getRenderedUnits() );
		assignedServiceBM.setRequestedUnits( assignedServiceDM.getRequestedUnits() );
		
		Service service = assignedServiceDM.getService();
		assignedServiceBM.setService( new CodeAndDescription( service.getServiceCode(), service.getServiceName() ) );
		assignedServiceBM.setServiceCharge( assignedServiceDM.getServiceCharge() );
		assignedServiceBM.setServiceDate( assignedServiceDM.getServiceDate() );
		assignedServiceBM.setServiceOrderNumber( assignedServiceDM.getLabRequisitionOrder().getOrderNbr() );
		assignedServiceBM.setServiceUid( assignedServiceDM.getServiceUid() );
		assignedServiceBM.setLastBillNumber(assignedServiceDM.getLastBillNbr());
		assignedServiceBM.setIsBillable(assignedServiceDM.getIsBillable());
		if( assignedServiceDM.getService().getServiceTypeCd() != null){
			CodeAndDescription serviceType = new CodeAndDescription();
			serviceType.setCode(assignedServiceDM.getService().getServiceTypeCd());
			
			assignedServiceBM.setServiceType(serviceType);
		}
		
		if( assignedServiceDM.getPatient() != null){
			assignedServiceBM.setPatientName(assignedServiceDM.getPatient().getFullName());
		}
		
		return assignedServiceBM;
	}
	
	private Service convertServiceBM2DM( ServiceBM serviceBM, Service transientSvc ) {
		Service service;
		
		if ( transientSvc != null ) {
			service = transientSvc;
		}
		else {
			service = new Service();
			service.setServiceCode( serviceBM.getServiceCode() );
			service.setCreatedBy( serviceBM.getPersonId() );
		}
		
		service.setDepositAmt( serviceBM.getDepositAmount() );
		service.setEffectiveFromDt( serviceBM.getEffectiveFromDate() );
		service.setEffectiveToDt( serviceBM.getEffectiveToDate() );
		service.setServiceDesc( serviceBM.getServiceDesc() );
		service.setServiceProcedure( serviceBM.getServiceProcedure() );
		
		if( serviceBM.getServiceGroup()!= null && !serviceBM.getServiceGroup().getCode().equals("")){
			ServiceGroup serviceGroup = this.getServiceGroup( serviceBM.getServiceGroup().getCode() );
			service.setServiceGroup(serviceGroup);
		}
		
		Department department = dataModelManager.getDepartment(serviceBM.getDepartment().getCode());
		service.setDepartment(department);
		
		service.setServiceCharge(serviceBM.getServiceCharge());
		service.setServiceName(serviceBM.getServiceName());
		
		ServiceStatus serviceStatus = this.getServiceStatus(serviceBM.getServiceStatus().getCode());
		service.setServiceStatus(serviceStatus);
		
		
		service.setServiceCode(serviceBM.getServiceCode());
		
		service.setServiceTypeCd(serviceBM.getServiceType().getCode());
		

		return service;
	}
	
	/**
	 * @param assignedPackageBM
	 */
	private void assignPackageToAPatient( AssignedPackageBM assignedPackageBM ) {
		try {
			assignedPackageBM.setAssignedPackageStatus( new CodeAndDescription(ServicesConstants.ASSIGNED_PACKAGE_REQUESTED, "") );
			AssignedPackage assignedPackage = this.convertAssignedPackageBM2DM(assignedPackageBM, null);
			assignedPackage.setCreateDate( new Date() );
			assignedPackageDAO.save( assignedPackage );
			
			//
			// For all the services associated with the package, create corresponding entry in
			// ASSIGNED_SERVICES entry table.
			//
			List<PackageHasService>packageHasServiceList = packageHasServiceDAO.findByProperty("id.packageId", assignedPackage.getServicePackage().getPackageId() );
			
			if ( packageHasServiceList != null && !packageHasServiceList.isEmpty() ) {
				for ( PackageHasService packageHasService : packageHasServiceList ) {
					this.createAssignedServiceForPackage( assignedPackage, 
							                              packageHasService,
							                              assignedPackageBM.getDoctorId(),
							                              assignedPackageBM.getPatientId() );
				}
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_ASSIGNMENT_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}
	
	/**
	 * 
	 * @param assignedPackage
	 * @param packageHasService
	 */
	private void createAssignedServiceForPackage( AssignedPackage assignedPackage, 
			                                      PackageHasService packageHasService,
			                                      Integer doctorId,
			                                      Integer patientId ) {
		AssignedServices assignedServices = new AssignedServices();
		AssignedServiceStatus assignedServiceStatus = this.getAssignedServiceStatus( ServicesConstants.ASSIGNED_SERVICE_REQUESTED );
		assignedServices.setAssignedServiceStatus( assignedServiceStatus );
		if ( assignedPackage.getBillNbr() != null ) {
			assignedServices.setBilledUnits( assignedPackage.getRequestedUnit() * packageHasService.getNumberOfUnits() );
		} else {
			assignedServices.setBilledUnits( ServicesConstants.DEFAULT_BILLED_UNITS );
		}
		
		assignedServices.setCanceledUnits(ServicesConstants.DEFAULT_CANCELED_UNITS);
		
		assignedServices.setCreatedBy( assignedPackage.getCreatedBy() );
		assignedServices.setCreateDtm( new Date() );
		assignedServices.setDoctorId( doctorId );
		assignedServices.setAssignedPackage(assignedPackage);
		
		Patient patient = dataModelManager.getPatient(patientId);
		assignedServices.setPatient(patient);
		
		assignedServices.setReferenceNumber( assignedPackage.getReferenceNumber() );
		assignedServices.setReferenceType( assignedPackage.getReferenceType() );
		assignedServices.setRemarks( "" );
		assignedServices.setIsBillable(ServicesConstants.IS_BILLABLE_YES);
		assignedServices.setRenderedUnits(ServicesConstants.DEFAULT_RENDERED_UNITS);
		assignedServices.setRequestedUnits( packageHasService.getNumberOfUnits() * assignedPackage.getRequestedUnit() );
		
		assignedServices.setService( packageHasService.getService() );
		
		if( packageHasService.getServiceCharge() != null ){
			assignedServices.setServiceCharge( packageHasService.getServiceCharge() );
		}else{
			assignedServices.setServiceCharge( 0.0 );
		}
		
		LabRequisitionOrder requisitionOrder = labRequisitionOrderDAO.findById(assignedPackage.getServiceOrderNumber());//TODO: verify it 
		assignedServices.setLabRequisitionOrder( requisitionOrder );
		
		assignedServicesDAO.save( assignedServices );
	}
	
	/**
	 * 
	 * @param assignedPackageBM
	 * @param existingAssignedPackage
	 * @return
	 */
	private AssignedPackage convertAssignedPackageBM2DM( AssignedPackageBM assignedPackageBM,
			                                             AssignedPackage existingAssignedPackage ) {
		AssignedPackage assignedPackage = null;
		
		if ( existingAssignedPackage != null ) {
			assignedPackage = existingAssignedPackage;
		} else {
			assignedPackage = new AssignedPackage();
			assignedPackage.setServiceOrderNumber( assignedPackageBM.getServiceOrderNumber() );
		}
		
		if( assignedPackageBM.getAssignedPackageStatus() != null &&
							assignedPackageBM.getAssignedPackageStatus().getCode() != null ){
			assignedPackage.setAssignedPackageStatus(
					this.getAssignedPackageStatus(assignedPackageBM.getAssignedPackageStatus().getCode()));
		}
		if( assignedPackageBM.getServicePackage() != null && assignedPackageBM.getServicePackage().getCode() != null  ){
			assignedPackage.setServicePackage( this.getServicePackage(assignedPackageBM.getServicePackage().getCode()));
		}
		
		if( assignedPackageBM.getDoctorId() != null ){
			Doctor doctor = dataModelManager.getDoctor(assignedPackageBM.getDoctorId());
			assignedPackage.setDoctor(doctor);
		}
		
		
		assignedPackage.setPackageAsgmId(assignedPackageBM.getPackageAsgmId());
	
		if(assignedPackageBM.getPatientId() != null ){
			Patient patient = dataModelManager.getPatient(assignedPackageBM.getPatientId());
			assignedPackage.setPatient(patient);
		}
		assignedPackage.setReferenceNumber(assignedPackageBM.getReferenceNumber());
		
		if( assignedPackageBM.getReferenceType() != null && !assignedPackageBM.getReferenceType().equals("")){
			assignedPackage.setReferenceType(assignedPackageBM.getReferenceType());
		}else{
			assignedPackage.setReferenceType(ServicesConstants.SERVICE_ASSIGNED_FROM_DIRECT);
		}
		
		if( assignedPackageBM.getRequestedUnit() != null ){
			assignedPackage.setRequestedUnit(assignedPackageBM.getRequestedUnit());
		}else{
			assignedPackage.setRequestedUnit( ServicesConstants.DEFAULT_REQUESTED_UNITS);//Default value
		}
		if( assignedPackageBM.getServicePackage() != null && assignedPackageBM.getServicePackage().getCode() != null  ){
			ServicePackage servicePackage = getServicePackage( assignedPackageBM.getServicePackage().getCode() );
			assignedPackage.setServicePackage(servicePackage);
			
			//TODO: Need to review from Alok sir
			assignedPackage.setEffectiveCharge( servicePackage.getEffectiveCharge() );
		}
		if( assignedPackageBM.getEffectiveCharge() != null ){
			assignedPackage.setEffectiveCharge(assignedPackageBM.getEffectiveCharge());
		}
		
		return assignedPackage;
	}
	
	/**
	 * This method creates an assigned service entry and corresponding history entry.
	 * 
	 * @param assignedServiceBM
	 */
	private void assignServiceToAPatient( AssignedServiceBM assignedServiceBM ) {
		try {
			Service service = dataModelManager.getServiceByCode( assignedServiceBM.getService().getCode() );
			AssignedServices assignedServices = this.convertAssignedServicesBM2DM(assignedServiceBM, null );
			assignedServices.setCreateDtm( new Date() );
			assignedServices.setCreatedBy( assignedServiceBM.getCreatedBy() );
			assignedServices.setIsBillable(ServicesConstants.IS_BILLABLE_YES);
			
			if(assignedServiceBM.getServiceDate() != null ){
				
				DateDim dateDim = commonDataManager.getDateDim( assignedServiceBM.getServiceDate() );
				
				if( dateDim != null ){
					
					assignedServices.setServiceDateDimId( dateDim.getDateId() );
				}
			}
			assignedServicesDAO.save( assignedServices );
			
			this.createAssignedServiceHistory(assignedServices);
						
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_ASSIGNMENT_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}
	
	
	
	
	/**
	 * 
	 * @param assignedPackage
	 * @return
	 */
	private AssignedPackageBM convertAssignedPackageDM2BM( AssignedPackage assignedPackage ) {
		AssignedPackageBM assignedPackageBM = new AssignedPackageBM();
		
		AssignedPackageStatus assignedPackageStatus = assignedPackage.getAssignedPackageStatus();
		assignedPackageBM.setAssignedPackageStatus( new CodeAndDescription( assignedPackageStatus.getStatusCode(), assignedPackageStatus.getDescription() ));
		assignedPackageBM.setBillNbr( assignedPackage.getBillNbr() );
		assignedPackageBM.setCreateDate( assignedPackage.getCreateDate() );
		assignedPackageBM.setCreatedBy( assignedPackage.getCreatedBy() );
		
		if ( assignedPackage.getDoctor() != null )
		assignedPackageBM.setDoctorId( assignedPackage.getDoctor().getDoctorId() );
		assignedPackageBM.setEffectiveCharge( assignedPackage.getEffectiveCharge() );
		assignedPackageBM.setModificationDtm( assignedPackage.getModificationDtm() );
		assignedPackageBM.setModifiedBy( assignedPackage.getModifiedBy() );
		assignedPackageBM.setPackageAsgmId( assignedPackage.getPackageAsgmId() );
		
		if ( assignedPackage.getPatient() != null ) {
			assignedPackageBM.setPatientId( assignedPackage.getPatient().getPatientId() );
		}
		
		assignedPackageBM.setReferenceNumber( assignedPackage.getReferenceNumber() );
		assignedPackageBM.setReferenceType( assignedPackage.getReferenceType() );
		assignedPackageBM.setRequestedUnit( assignedPackage.getRequestedUnit() );
		assignedPackageBM.setServiceOrderNumber( assignedPackage.getServiceOrderNumber() );
		
		assignedPackageBM.setServicePackage( new CodeAndDescription( assignedPackage.getServicePackage().getPackageId().toString(),
																	 assignedPackage.getServicePackage().getName() ) );
		
		return assignedPackageBM;
	}

	private ServicePackageSummaryBM convertServicePackageDM2Summary( ServicePackage servicePackage ) {
		ServicePackageSummaryBM packageSummaryBM = new ServicePackageSummaryBM();
		
		packageSummaryBM.setDescription( servicePackage.getDescription() );
		packageSummaryBM.setEffectiveCharge( servicePackage.getEffectiveCharge() );
		packageSummaryBM.setEffectiveFromDt( servicePackage.getEffectiveFromDt() );
		packageSummaryBM.setEffectiveToDt( servicePackage.getEffectiveToDt() );
		packageSummaryBM.setName( servicePackage.getName() );
		packageSummaryBM.setPackageId( servicePackage.getPackageId() );
		packageSummaryBM.setChargeOverrideLevel(servicePackage.getChargeOverrideLevel());
		packageSummaryBM.setServicePackageStatus( new CodeAndDescription( servicePackage.getServicePackageStatus().getStatusCode(), servicePackage.getServicePackageStatus().getDescription()));
		packageSummaryBM.setSuspendedFromDt( servicePackage.getSuspendedFromDt() );
		packageSummaryBM.setSuspendedToDt( servicePackage.getSuspendedToDt() );
		
		return packageSummaryBM;
	}

	public 	ListRange  getServiceSummaryBMforGroup(String serviceGroupCode ,int start, int count, String orderBy  ){
		
		try {
			List<Service> serviceList = null;
			if( serviceGroupCode != null && !serviceGroupCode.equals("")){
				serviceList = serviceDAO.findServicesOfGroup(serviceGroupCode);
			}else{
				serviceList = serviceDAO.findAllActiveServices();
			}
			
			List<ServiceSummaryBM> serviceSummaryBMList = new ArrayList<ServiceSummaryBM>(0);
			
			if( serviceList != null && !serviceList.isEmpty() ){
				for( Service service : serviceList ){
					
					ServiceSummaryBM serviceSummaryBM = new ServiceSummaryBM();
					
					serviceSummaryBM.setServiceCode(service.getServiceCode() );
					serviceSummaryBM.setServiceName( service.getServiceName() );
					serviceSummaryBM.setServiceCharge(service.getServiceCharge() );
					
					ServiceStatus serviceStatus = service.getServiceStatus();
					if( serviceStatus != null ){
						serviceSummaryBM.setServiceStatus(new CodeAndDescription(serviceStatus.getServiceStatusCode(),serviceStatus.getDescription()));
					}
					serviceSummaryBMList.add( serviceSummaryBM );
				}
			}
			
			
			ListRange listRange = new ListRange();
			
			List<ServiceSummaryBM> pageSizeData = new ArrayList<ServiceSummaryBM>();
			int index = 0;
			if (serviceSummaryBMList != null && serviceSummaryBMList.size() > 0) {
			while( (start+index < start + count) && (serviceSummaryBMList.size() > start+index) ){
				
				ServiceSummaryBM serviceSummaryBM = serviceSummaryBMList.get(start+index);
				pageSizeData.add( serviceSummaryBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(serviceSummaryBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}
	
	public ServiceBM findServicesByServiceCode(String serviceCode) {
		
		try {
			Service service = dataModelManager.getServiceByCode(serviceCode);
			
			if( service != null ){
				return this.convertServiceDM2BM(service);
			}
			return null;
		} catch (HCISException e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}
	
	public ListRange getServiceSummaryBM( int start, int count, String orderBy ){
		try {
			List<Service> serviceList = null;
		
			serviceList = serviceDAO.findAllActiveServices();
			
			List<ServiceSummaryBM> serviceSummaryBMList = new ArrayList<ServiceSummaryBM>(0);
			
			if( serviceList != null && !serviceList.isEmpty() ){
				for( Service service : serviceList ){
					
					ServiceSummaryBM serviceSummaryBM = new ServiceSummaryBM();
					
					serviceSummaryBM.setServiceCode(service.getServiceCode() );
					serviceSummaryBM.setServiceName( service.getServiceName() );
					serviceSummaryBM.setServiceCharge(service.getServiceCharge() );
					
					ServiceStatus serviceStatus = service.getServiceStatus();
					if( serviceStatus != null ){
						serviceSummaryBM.setServiceStatus(new CodeAndDescription(serviceStatus.getServiceStatusCode(),serviceStatus.getDescription()));
					}
					serviceSummaryBMList.add( serviceSummaryBM );
				}
			}
			
			ListRange listRange = new ListRange();
			
			List<ServiceSummaryBM> pageSizeData = new ArrayList<ServiceSummaryBM>();
			int index = 0;
			if (serviceSummaryBMList != null && serviceSummaryBMList.size() > 0) {
			while( (start+index < start + count) && (serviceSummaryBMList.size() > start+index) ){
				
				ServiceSummaryBM serviceSummaryBM = serviceSummaryBMList.get(start+index);
				pageSizeData.add( serviceSummaryBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(serviceSummaryBMList.size());
			} else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}
	
	public List<AssignSvcAndPackageBM> getAssignedServiceAndPackageList( String referenceNumber, 
			                                                             String referenceType,
			                                                             Boolean unbilledFlag ) {
	try {
			List<AssignSvcAndPackageBM>serviceAndPackageList = null;
			
			//
			// It is assumes that the getAssignedService will always return service list ordered by
			// the service order number. 
			// Also, it is assumed that all the services of the same packages will appear together. 
			//
			List<AssignedServices>assignedServicesList = null;
			
			if ( unbilledFlag != null && unbilledFlag.booleanValue() ) {
				assignedServicesList = assignedServicesDAO.getUnbilledAssignedServices(referenceType, referenceNumber,false, null);
			} else {
				assignedServicesList = assignedServicesDAO.getAssignedService(referenceType, referenceNumber);
			}
			
			
			if ( assignedServicesList != null && !assignedServicesList.isEmpty() ) {
				serviceAndPackageList = new ArrayList<AssignSvcAndPackageBM>(0);
				
				boolean packageAlreadyInList = false;
				Integer lastPackageAsgmId = -1;
				Integer lastServiceOrderNbr = -1;
				AssignSvcAndPackageBM assignSvcAndPackageBM = null;
				List<AssignedServiceBM>assignedServiceBMList = null;
				List<AssignedPackageBM>assignedPackageBMList = null;
				
				
				for ( AssignedServices assignedServices : assignedServicesList ) {
					//
					//
					// Check if the service order is same or different. For different service number we need to create 
					// different AssignSvcAndPackageBM object.
					// Check if the assigned service is part of the package. if yes then check if it is already included in
					// the list.
					//
					
					if ( lastServiceOrderNbr == -1 || !assignedServices.getLabRequisitionOrder().getOrderNbr().equals(lastServiceOrderNbr)  ) {
						//
						// This is either fist service in the list or service order has changed
						//
						
						if ( lastServiceOrderNbr.intValue() != -1  ) {
							//
							// Service order number has changed. So, add this into the list.
							//
							assignSvcAndPackageBM.setAssignedPackageBMList(assignedPackageBMList);
							assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
							serviceAndPackageList.add( assignSvcAndPackageBM );
							
							assignedPackageBMList = null;
							assignedServiceBMList = null;
						}
						
						assignSvcAndPackageBM = new AssignSvcAndPackageBM();
						assignSvcAndPackageBM.setCreatedBy( assignedServices.getCreatedBy() );
						assignSvcAndPackageBM.setDoctorId( assignedServices.getDoctorId() );
						assignSvcAndPackageBM.setPatientId( assignedServices.getPatient().getPatientId() );
						assignSvcAndPackageBM.setReferenceNumber(referenceNumber);
						assignSvcAndPackageBM.setReferenceType(referenceType);
						assignSvcAndPackageBM.setServiceOrderNumber( assignedServices.getLabRequisitionOrder().getOrderNbr() );
						lastServiceOrderNbr = assignedServices.getLabRequisitionOrder().getOrderNbr();
						
					}
					
					AssignedPackage assignedPackage = assignedServices.getAssignedPackage();
					
					if ( assignedPackage != null ) {
						
						if ( assignedPackageBMList == null ) {
							assignedPackageBMList = new ArrayList<AssignedPackageBM>(0);
						}
						
						if ( !assignedPackage.getPackageAsgmId().equals(lastPackageAsgmId) ) {
							//
							// This is either first package or some other package. This needs to be inserted in the list.
							//
							lastPackageAsgmId = assignedPackage.getPackageAsgmId();
							AssignedPackageBM assignedPackageBM = this.convertAssignedPackageDM2BM(assignedPackage);
							assignedPackageBMList.add(assignedPackageBM);
						}
					} else {
						//
						// This is a service.
						// 
						//
						if ( assignedServiceBMList == null ) {
							assignedServiceBMList = new ArrayList<AssignedServiceBM>(0);
						}
						
						AssignedServiceBM assignedServiceBM = this.convertAssignedServicesDM2BM( assignedServices );
						assignedServiceBMList.add( assignedServiceBM );
					}
				}
				
				assignSvcAndPackageBM.setAssignedPackageBMList(assignedPackageBMList);
				assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
				serviceAndPackageList.add( assignSvcAndPackageBM );
			}
			
			return serviceAndPackageList;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.ASSIGNED_SERVICE_READ_FAILED );
			HCISException hcisException = new HCISException(fault, e );
			throw hcisException;
		}
	}

	public ListRange  findAssignedServices( String serviceCode,String serviceName,String packageCode,String packageName,
										    Date serviceFromDt,Date serviceToDt, String departmetnCode,
										    String serviceGroupCode, String referenceType,String referenceNumber,
										    Integer patientId, int start,int count,	String orderBy  ){
		
		String[] orderByInfo;
		
		if( orderBy != null && orderBy.length() > 0 ){
			orderByInfo =  orderBy.trim().split(" "); 
		}else{
			orderByInfo = new String[2];
		}
		
		List<AssignedServices> assignedServicesList = 
			assignedServicesDAO.findAssignedServices(serviceCode, serviceName, packageCode,
												   packageName, serviceFromDt, serviceToDt,
												   departmetnCode, serviceGroupCode, referenceType,
												   referenceNumber, patientId,null,  orderByInfo[0],  orderByInfo[1]);

		ListRange listRange = new ListRange();
		
		List<AssignedServiceBM> pageSizeData = new ArrayList<AssignedServiceBM>();
		int index = 0;
		if (assignedServicesList != null && assignedServicesList.size() > 0) {
		while( (start+index < start + count) && (assignedServicesList.size() > start+index) ){
			
			AssignedServiceBM assignedServiceBM = convertAssignedServicesDM2BM(assignedServicesList.get(start+index));
			pageSizeData.add( assignedServiceBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(assignedServicesList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}
	
	/**
	 * Returns the list of all assigned services to the patient
	 * @param patientId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	public ListRange getAssignedServiceHistory(Integer patientId, int start,int count,	String orderBy){
		
		List<AssignedServices> assignedServicesList = 
			assignedServicesDAO.getAssignedServicesForPatient(patientId);

		ListRange listRange = new ListRange();
		
		List<AssignedServiceBM> pageSizeData = new ArrayList<AssignedServiceBM>();
		int index = 0;
		if (assignedServicesList != null && assignedServicesList.size() > 0) {
		while( (start+index < start + count) && (assignedServicesList.size() > start+index) ){
			
			AssignedServiceBM assignedServiceBM = convertAssignedServicesDM2BM(assignedServicesList.get(start+index));
			
			pageSizeData.add( assignedServiceBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(assignedServicesList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}
	
/**
 * This method checks whether the service with given code is already exist or not.
 * 
 * 
 */
	public boolean isServicePackageExist( String packageId ){
		
			try {
				if( packageId != null && packageId.length() > 0){
					ServicePackage servicePackage = servicePackageDAO.findById( packageId );
					
					if( servicePackage != null ){
						return true;
					}
					
					return false;
				}
				
				return false;
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_READ_FAILED );
				HCISException hcisException = new HCISException(fault, e );
				throw hcisException;
				
			}
		}
	
	/**
	 * This method checks whether the service group with given code is already exist or not.
	 * 
	 * 
	 */
	public boolean isServiceGroupExist( String serviceGroupCode ){
			
			try {
				if( serviceGroupCode != null && serviceGroupCode.length() > 0){
					ServiceGroup serviceGroup = serviceGroupDAO.findById( serviceGroupCode );
					
					if( serviceGroup != null ){
						return true;
					}
					
					return false;
				}
				
				return false;
			} catch ( Exception e) {
				Fault fault = new Fault( ApplicationErrors.SERVICE_GROUP_READ_FAILED );
				HCISException hcisException = new HCISException(fault, e );
				throw hcisException;
			}
		}
		/**
		 * This method checks whether the service with given code is already exist or not.
		 * 
		 * 
		 */
		public boolean isServiceExist( String serviceCode ){
				
				try {
					if( serviceCode != null && serviceCode.length() > 0){
							Service service = serviceDAO.findById( serviceCode );
							
						if( service != null ){
							return true;
						}
						
						return false;
					}
						
						return false;
				} catch ( Exception e) {
					Fault fault = new Fault( ApplicationErrors.SERVICE_READ_FAILED );
					HCISException hcisException = new HCISException(fault, e );
					throw hcisException;
				}
		}
		
		private LabRequisitionOrder createRequisitionOrder( AssignSvcAndPackageBM assignSvcAndPackageBM ){
			
			LabRequisitionOrder labRequisitionOrder = new LabRequisitionOrder();
			
			labRequisitionOrder.setPatient(dataModelManager.getPatient(assignSvcAndPackageBM.getPatientId()));
			if( assignSvcAndPackageBM.getDoctorId() != null ){
				labRequisitionOrder.setDoctor(dataModelManager.getDoctor(assignSvcAndPackageBM.getDoctorId()));
			}
			labRequisitionOrder.setReferenceNumber( assignSvcAndPackageBM.getReferenceNumber());
			labRequisitionOrder.setReferenceType( assignSvcAndPackageBM.getReferenceType() );
			labRequisitionOrder.setCreatedBy(assignSvcAndPackageBM.getCreatedBy());
			labRequisitionOrder.setCreatedDtm(new Date());
			labRequisitionOrder.setStatusCode(ServicesConstants.REQUISTION_ORDER_CREATED);
		
			Double totalCharges = 0.0;
			
			List<AssignedPackageBM> assignedPackageBMList = assignSvcAndPackageBM.getAssignedPackageBMList();
			if( assignSvcAndPackageBM.getAssignedPackageBMList() != null && !assignSvcAndPackageBM.getAssignedPackageBMList().isEmpty()){
				
				for( AssignedPackageBM assignedPackageBM : assignSvcAndPackageBM.getAssignedPackageBMList() ){
					
					totalCharges += this.getServicePackage(assignedPackageBM.getServicePackage().getCode()).getEffectiveCharge();
				}
							
			}
			
			if( assignSvcAndPackageBM.getAssignedServiceBMList() != null && !assignSvcAndPackageBM.getAssignedServiceBMList().isEmpty()){
				
				for(AssignedServiceBM assignedServiceBM : assignSvcAndPackageBM.getAssignedServiceBMList()){
					
					
					
					totalCharges += dataModelManager.getServiceByCode(assignedServiceBM.getService().getCode()).getServiceCharge();
				}
			}
			
			labRequisitionOrder.setTotalCharges(totalCharges);
			
			DateDim dateDim = commonDataManager.getDateDim( new Date() );
			
			if( dateDim != null){
				labRequisitionOrder.setCreatedDateDim(dateDim.getDateId());
			}
			
			labRequisitionOrderDAO.save(labRequisitionOrder);
			
			return labRequisitionOrder;
			
		}
	
	/**
	 * This method updates status of requisition order
	 * 
	 * Valid status transition for requisition is
	 * 
	 * FROM						TO STATUS		WHEN
	 * 
	 * CREATED					CANCELED 		All service has been canceled
	 * CREATED					COMPLETED		If only one service is assigned and rendered
	 * CREATED					IN_PROGRESS	    If rendered service count is greater than 0 and less than rendered + canceled
	 * IN_PROGRESS				COMPLETED		All of non canceled assigned services are rendered ( count of total assigned service = rendered + canceled)
	 * 
	 * @param requisitionOrder
	 */
	private void updateRequisitionStatus(LabRequisitionOrder requisitionOrder,String userId ){
		
		List<AssignedServices> assignedServicesList = assignedServicesDAO.findByProperty("labRequisitionOrder.orderNbr", 
																	requisitionOrder.getOrderNbr());
		
		int cancelServCount = 0;
		int renderedServCount = 0;
		
		String newStatus = null;
		if(assignedServicesList != null && !assignedServicesList.isEmpty()){
		
			for(AssignedServices services : assignedServicesList){
				
				cancelServCount = cancelServCount + services.getCanceledUnits();
				renderedServCount = renderedServCount + services.getRenderedUnits();
			}
			
			if(assignedServicesList.size() == cancelServCount){
				
				newStatus = ServicesConstants.REQUISITION_STATUS_CANCELED;
			
			}else if( renderedServCount > cancelServCount){
				
				if( (renderedServCount + cancelServCount) == assignedServicesList.size()){
					
					newStatus = ServicesConstants.REQUISITION_STATUS_COMPLETED;
				}else{
					
					newStatus = ServicesConstants.REQUISITION_STATUS_IN_PROGRESS;
				}
			}else{
				// Leave it, as it is
				newStatus = requisitionOrder.getStatusCode();
			}
			
			requisitionOrder.setStatusCode(newStatus);
			requisitionOrder.setLastModifiedBy(userId!=null ? userId : " " );//to prevent form failing because of database constraints
			requisitionOrder.setLastModifiedDtm( new Date() );
		}
		
			//Status needs be changed 
			labRequisitionOrderDAO.attachDirty(requisitionOrder);
	}
	
	
	public void setServiceDAO(ServiceDAOExtn serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public void setServiceGroupDAO(ServiceGroupDAOExtn serviceGroupDAO) {
		this.serviceGroupDAO = serviceGroupDAO;
	}

	public void setAssignedServicesDAO(AssignedServicesDAOExtn assignedServicesDAO) {
		this.assignedServicesDAO = assignedServicesDAO;
	}

	public void setAppointmentsDAO(AppointmentsDAOExtn appointmentsDAO) {
		this.appointmentsDAO = appointmentsDAO;
	}

	public void setServiceStatusDAO(ServiceStatusDAO serviceStatusDAO) {
		this.serviceStatusDAO = serviceStatusDAO;
	}

	public void setServicePackageStatusDAO(
			ServicePackageStatusDAO servicePackageStatusDAO) {
		this.servicePackageStatusDAO = servicePackageStatusDAO;
	}

	public void setServicePackageDAO(ServicePackageDAOExtn servicePackageDAO) {
		this.servicePackageDAO = servicePackageDAO;
	}

	public void setPackageHasServiceDAO(PackageHasServiceDAO packageHasServiceDAO) {
		this.packageHasServiceDAO = packageHasServiceDAO;
	}

	public void setAssignedPackageDAO(AssignedPackageDAOExtn assignedPackageDAO) {
		this.assignedPackageDAO = assignedPackageDAO;
	}

	public void setAssignedServiceStatusDAO(
			AssignedServiceStatusDAO assignedServiceStatusDAO) {
		this.assignedServiceStatusDAO = assignedServiceStatusDAO;
	}

	public void setAssignedServiceHistoryDAO(
			AssignedServiceHistoryDAO assignedServiceHistoryDAO) {
		this.assignedServiceHistoryDAO = assignedServiceHistoryDAO;
	}

	public void setAssignedPackageStatusDAO(
			AssignedPackageStatusDAO assignedPackageStatusDAO) {
		this.assignedPackageStatusDAO = assignedPackageStatusDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setRenderedServiceDAO(RenderedServiceDAOExtn renderedServiceDAO) {
		this.renderedServiceDAO = renderedServiceDAO;
	}
	
	private PackageHasServiceBM  convertPackageHasServiceDM2BM( PackageHasService packageHasService ) {
		PackageHasServiceBM packageHasServiceBM = new PackageHasServiceBM();
		packageHasServiceBM.setDiscountAmtPct( packageHasService.getDiscountAmtPct() );
		packageHasServiceBM.setDiscountType( packageHasService.getDiscountType() );
		packageHasServiceBM.setEffectiveCharge( packageHasService.getEffectiveCharge() );
		packageHasServiceBM.setNumberOfUnits( packageHasService.getNumberOfUnits() );
		
		CodeAndDescription serviceCodeDesc = new CodeAndDescription();
		serviceCodeDesc.setCode(packageHasService.getService().getServiceCode());
		serviceCodeDesc.setDescription(packageHasService.getService().getServiceName());
		
		packageHasServiceBM.setService( serviceCodeDesc );
		packageHasServiceBM.setServiceCharge(packageHasService.getServiceCharge());
		
		CodeAndDescription packageCodeDesc = new CodeAndDescription();
		packageCodeDesc.setCode(packageHasService.getServicePackage().getPackageId());
		packageCodeDesc.setDescription(packageHasService.getServicePackage().getName());
		
		packageHasServiceBM.setServicePackage( packageCodeDesc );
		
		return packageHasServiceBM;
	}

	public ServicePackageBM getServicePackageDetail(String packageId) {
		try {
			ServicePackage servicePackage = this.getServicePackage(packageId);
			
			ServicePackageBM servicePackageBM = this.convertServicePackageDM2BM(servicePackage);
			
			//
			// This method may be used for retrieving complete details about the package
			// Hence, populate associated service details as well
			//
			List<PackageHasServiceBM>serviceBMList = new ArrayList<PackageHasServiceBM>(0);
			
			List<PackageHasService>servicesOfPackageList = packageHasServiceDAO.findByProperty("id.packageId", packageId);
			
			if ( servicesOfPackageList != null && !servicesOfPackageList.isEmpty() ) {
				
				for ( PackageHasService packageHasService : servicesOfPackageList ) {
					PackageHasServiceBM packageHasServiceBM = this.convertPackageHasServiceDM2BM( packageHasService );
					serviceBMList.add( packageHasServiceBM );
				}
			}
			
			servicePackageBM.setServiceBMList(serviceBMList);
			
			return servicePackageBM;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_PACKAGE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	
	/**
	 * This method updates the price of service with the given amount. Updation
	 * can be increment or decrement, base on the indicator it will be decided.
	 * 
	 *Assumption is that if UI is calculating new applicable price for each
	 * service then it should put the corresponding price in the
	 * "servicePriceMap". Otherwise it will be calculated at BL layer by keeping
	 * details given at the BM it self (Not the map).
	 * 
	 * @param priceUpdateBM
	 */
	public void updateServicePrice( ServicePriceUpdateBM priceUpdateBM){
		
		try {
			Validate.notNull( priceUpdateBM );
			
			Double updateAmount = 0.0d;
			
			if(priceUpdateBM.getUpdateAmount() != null ){
				
				updateAmount = priceUpdateBM.getUpdateAmount();
			}
			
			if(ServicesConstants.DECREMENT_INDECATOR.equals( priceUpdateBM.getIncreDecreInd() )){
				
				updateAmount = updateAmount * -1;
			}
			
			
			
			Map<String,String> serviceAndPriceMap =  priceUpdateBM.getServicePriceMap();
				
			if(serviceAndPriceMap != null && !serviceAndPriceMap.isEmpty()){
				
				Set<String> serviceCodes =  serviceAndPriceMap.keySet();
				
				for(String serviceCode : serviceCodes){
					
					Service service = this.getService(serviceCode);
					
					Double serviceCharge = service.getServiceCharge();
					
					Double newPrice = 0.0d;
					
					
					if( serviceAndPriceMap.get( serviceCode ) != null &&
							!serviceAndPriceMap.get( serviceCode ).isEmpty()){
						
						
						newPrice = Double.valueOf(serviceAndPriceMap.get( serviceCode ));
					
					}else{
						
						
						if(ServicesConstants.AMOUNT_PERCENTAGE.equals( priceUpdateBM.getPectAbsInd())){
							
							newPrice = serviceCharge + ( serviceCharge *  updateAmount / 100 ) ;
							
						}else{
							
							//Default is  ServicesConstants.AMOUNT_ABSOULUTE
							
							newPrice = serviceCharge + updateAmount;
						}
						
					}

					this.createServicePriceDetail(service, newPrice, priceUpdateBM.getEffectiveFromDt(), priceUpdateBM.getUserId() );
				}
			}
		} catch (Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.SERVICE_MODIFY_FAILED );
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
			
	}

	
	/**
	 * This method works very similar to method 'updateServicePrice'.
	 * 
	 */
	public void createServicePriceDetailEntry( ServicePriceDetailBM[] servicePriceDetailBMList){
		
		try {
			if( servicePriceDetailBMList != null && servicePriceDetailBMList.length > 0){
				for( ServicePriceDetailBM servicePriceDetailBM : servicePriceDetailBMList){
				
					Service service = this.getService( servicePriceDetailBM.getServiceCode() );
					
					this.createServicePriceDetail(service, servicePriceDetailBM.getServiceCharge(), 
												servicePriceDetailBM.getEffectiveFromDt(),
												servicePriceDetailBM.getCreatedBy());
				}
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.SERVICE_MODIFY_FAILED );
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	
	/**
	 * here first we make sure that given effectiveFrom is future date and not conflicting with any existing entry.
	 * 
	 */
	
	
	private void createServicePriceDetail( Service service , Double newPrice, Date effectiveFrom, String createdBy) throws Exception {
		
		
		
			if(effectiveFrom.before( DateUtils.previousDate( new Date()) )){
				
				throw new Exception(" Effective From can not be past date ");
			}
		
			List<ServicePriceDetail> conflictedPriceDetailList = 
							servicePriceDetailDAO.getConflictedPriceList( service.getServiceCode(),effectiveFrom, null);
			
			if(conflictedPriceDetailList != null && conflictedPriceDetailList.size() > 0 ){
				
				StringBuilder exceptionMsg = new StringBuilder(" : Following service price configurtion(s) are conflicting- ");

				for( ServicePriceDetail servicePriceDetail : conflictedPriceDetailList  ){
					
					exceptionMsg.append("Service code: " + servicePriceDetail.getId().getServiceCd() +
										"\nService charge: " + servicePriceDetail.getServiceCharge() + 
										"\nEffective from: "+	servicePriceDetail.getId().getEffectiveFrom() + 
										(servicePriceDetail.getEffectiveTo() != null ? "\nEffective to: "+
												servicePriceDetail.getEffectiveTo():""));
				}
				
				throw new Exception( exceptionMsg.toString() );
			}
			
									
			ServicePriceDetail priceDetail = new ServicePriceDetail();
			
			ServicePriceDetailId id = new ServicePriceDetailId();
			
			id.setServiceCd(service.getServiceCode());
			id.setEffectiveFrom(effectiveFrom);
			priceDetail.setId(id);
			
			priceDetail.setCreatedBy(createdBy);
			priceDetail.setCreatedDtm( new Date() );
			priceDetail.setServiceCharge(newPrice);
			priceDetail.setProcessed( ServicesConstants.PROCESSED_N);
			
			servicePriceDetailDAO.save(  priceDetail );
		
	}
	
	/**
	 * This is background job process which is intended to execute everyday.
	 * This method gets all service price details entry for the current date 
	 * and applies the charge by modifying the detail in service table.
	 * After processing it marks corresponding entry as processed and populate
	 * the 'effective to date' for immediate  predecessor date entry (Effective to
	 * date will be 1 day prior to next entries effective from date ).
	 * @param serviceCode - optional, if given then process only given service
	 * @return
	 */
	public boolean processServicePriceDetail( String serviceCode,Date processForDate){
		
		if(processForDate == null){
			
			processForDate = DateUtils.truncateTime(new Date());
		}
		
		List<ServicePriceDetail> servicePriceDetailList = servicePriceDetailDAO.getServicePriceForProcessing(serviceCode,processForDate);
		
		Set<String> processedServiceSet = new HashSet<String>(0);
		
		if( servicePriceDetailList != null && !servicePriceDetailList.isEmpty() ){
			
			for( ServicePriceDetail servicePriceDetail : servicePriceDetailList ){
				
				if( !processedServiceSet.contains( servicePriceDetail.getId().getServiceCd()) ){
					
					Service service = servicePriceDetail.getService();
					
					service.setServiceCharge( servicePriceDetail.getServiceCharge() );
					service.setLastModifiedDtm( new Date() );
					service.setModifiedBy( ServicesConstants.PROCESS_SERVICE_PRICE_UPDATE);
					
					serviceDAO.attachDirty( service );
					
					servicePriceDetail.setProcessed( ServicesConstants.PROCESSED_Y );
					servicePriceDetailDAO.attachDirty( servicePriceDetail );
					
					//Get the immediate  predecessor date entry and populate effective to date
					List<ServicePriceDetail> earliarServicePriceDetailList = 
						servicePriceDetailDAO.getEarlierServicePriceDetail( service.getServiceCode() ,
																			servicePriceDetail.getId().getEffectiveFrom() );
				
					if( earliarServicePriceDetailList != null && !earliarServicePriceDetailList.isEmpty()){
						
						ServicePriceDetail latestServicePrice = earliarServicePriceDetailList.get( 0 );
						
						latestServicePrice.setEffectiveTo( DateUtils.previousDate(servicePriceDetail.getId().getEffectiveFrom()) );
						
						latestServicePrice.setLastModifiedDtm( new Date() );
						latestServicePrice.setModifiedBy( ServicesConstants.PROCESS_SERVICE_PRICE_UPDATE );
						
						servicePriceDetailDAO.attachDirty( latestServicePrice );
					}
					
				}
			}
			
		}
		return false;
	}
	
	/**
	 * 
	 */
	public ListRange getServicePriceDetail( String serviceCode, int start,int count, String orderBy){

		List<ServicePriceDetail> servicePriceDetailList = servicePriceDetailDAO.findByProperty("id.serviceCd", serviceCode);
		
		List<ServicePriceDetailBM> servicePriceDetailBMList = new ArrayList<ServicePriceDetailBM>();
		
		if( servicePriceDetailList != null && !servicePriceDetailList.isEmpty()){
			
			for( ServicePriceDetail priceDetail : servicePriceDetailList ){
			
				ServicePriceDetailBM priceDetailBM = new ServicePriceDetailBM();
				
				priceDetailBM.setCreatedBy(priceDetail.getCreatedBy());
				priceDetailBM.setCreatedDt(priceDetail.getCreatedDtm());
				priceDetailBM.setServiceCharge(priceDetail.getServiceCharge());
				priceDetailBM.setEffectiveFromDt(priceDetail.getId().getEffectiveFrom());
				priceDetailBM.setEffectiveToDt( priceDetail.getEffectiveTo() );
				priceDetailBM.setProcessed( priceDetail.getProcessed() );
				
				Service service = priceDetail.getService();
				priceDetailBM.setServiceCode( service.getServiceCode() );
				priceDetailBM.setServiceName( service.getServiceName() );
				
				servicePriceDetailBMList.add( priceDetailBM );
			}
		}
		
		return WtcUtils.convertListToListRange(servicePriceDetailBMList, start, count);
	}
	
	
	/**
	 *  
	 * Eligibility criteria for deletion is-
	 * 
	 *   Not marked as "Processed"
	 *   	
	 * 
	 * After deletion of 'PriceDetail' entry it removes "Effective to"
	 * date from next latest entry.(To make is open through out the service's life)
	 * 
	 *  
	 * 
	 * @param sercviceCode
	 * @param createdDtm
	 * @return
	 */
	public boolean removeServicePirceDetail(String sercviceCode,Date effectiveFrom){
		
		try {
			ServicePriceDetailId id = new ServicePriceDetailId();
			
			id.setServiceCd( sercviceCode );
			id.setEffectiveFrom(effectiveFrom);
			
			ServicePriceDetail priceDetail = servicePriceDetailDAO.findById( id );
			
			if( priceDetail != null ){
				
				if( "Y".equals(priceDetail.getProcessed()) ){
					
					throw new Exception(" This price configuration is already processed ");
				}
				
				servicePriceDetailDAO.delete( priceDetail );

				return true;
			}
			
			return false;
		} catch (Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.SERVICE_MODIFY_FAILED );
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
		
	}
	
	public void setLabRequisitionOrderDAO(
			LabRequisitionOrderDAO labRequisitionOrderDAO) {
		this.labRequisitionOrderDAO = labRequisitionOrderDAO;
	}

	public void setDateDimDAO(DateDimDAOExtn dateDimDAO) {
		this.dateDimDAO = dateDimDAO;
		
	}
	
	public String markAssignedServiceIsBillable( Integer serviceUID ){
		
		try{
			
			AssignedServices assignedService = assignedServicesDAO.findById(serviceUID);
			
			if( assignedService.getAssignedServiceStatus().getServiceStatusCode().equals(ServicesConstants.ASSIGNED_SERVICE_RENDERED) 
					&& assignedService.getLastBillNbr() != null 
					&& assignedService.getIsBillable().equals(ServicesConstants.IS_BILLABLE_YES)){
				
				throw new Exception(" This service is in " + assignedService.getAssignedServiceStatus().getDescription() +
						" status.");
			}
			
			if( assignedService.getLastBillNbr() != null){
				throw new Exception(" This service is already billed");
			}
			if( assignedService.getAssignedServiceStatus().getServiceStatusCode().equals(ServicesConstants.ASSIGNED_SERVICE_CANCELED)){
				
				throw new Exception(" This service is already canceled");
			}
			if( assignedService.getIsBillable().equals(ServicesConstants.IS_BILLABLE_YES)){
				assignedService.setIsBillable(ServicesConstants.IS_BILLABLE_NO);
			}
			else{
				assignedService.setIsBillable(ServicesConstants.IS_BILLABLE_YES);
			}
			
			assignedServicesDAO.attachDirty(assignedService);
			
			return assignedService.getIsBillable();

		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.MARK_ASSIGNED_SERVICE_IS_BILLABE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}
	/**
	 *  TODO : Needs to send only ready to render service list.
	 */
	
	public ListRange  getAssignedServicesForServiceType( String referenceType, String referenceNumber , Integer patientId, String serviceType, Integer start ,Integer count ,String orderBy ){
		List<AssignedServiceBM> assignedServiceBMList = null;
		List<AssignedServices> assignedServicesList = assignedServicesDAO.findAssignedServices(null, null, null, null, null, null, null, null, referenceType, referenceNumber, patientId, serviceType, null, null);
		if( assignedServicesList != null && assignedServicesList.size() > 0){
			assignedServiceBMList = new ArrayList<AssignedServiceBM>(0);
			for (AssignedServices assignedServices : assignedServicesList) {
				assignedServiceBMList.add(this.convertAssignedServicesDM2BM(assignedServices));
			}
		}
		return WtcUtils.convertListToListRange(assignedServiceBMList);
	}
	public void setServicePriceDetailDAO(
			ServicePriceDetailDAOExtn servicePriceDetailDAO) {
		this.servicePriceDetailDAO = servicePriceDetailDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setPatientTestDetailDAO(LabPatientTestDetailDAO patientTestDetailDAO) {
		this.patientTestDetailDAO = patientTestDetailDAO;
	}
	
 
}