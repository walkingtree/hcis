/**
 * 
 */
package in.wtc.hcis.bo.services;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.base.ServiceGroupBM;
import in.wtc.hcis.bm.base.ServicePackageBM;
import in.wtc.hcis.bm.base.ServicePackageSummaryBM;
import in.wtc.hcis.bm.base.ServicePriceDetailBM;
import in.wtc.hcis.bm.base.ServicePriceUpdateBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
public interface ServiceManager 
{
	/**
	 * The method adds a service entry into the system.
	 * 
	 * @param servicerBM service information
	 */
	void addService( ServiceBM servicerBM );
	
	/**
	 * The method creates a service group in the system.
	 * 
	 * @param serviceGroupBM service group information 
	 */
	void addServiceGroup( ServiceGroupBM serviceGroupBM );
	
	/**
	 * 
	 * This method creates service package. 
	 * A package may contain at zero or more services.
	 * Based on the list of PackageHasServiceBM entries, it creates association between 
	 * a package and a service. 
	 * 
	 * discount types are two types
	 * 1.Absolute -->'A'
	 * 2.percentage -->'P'
	 * 
	 * override types are two types
	 * 1. package override --> 'P'
	 * 2. service override --> 'S'
	 * @param servicePackageBM
	 */
	void addServicePackage( ServicePackageBM servicePackageBM );
		
	/**
	 * The method returns the matching services information for the specified parameters
	 * 
	 * All the parameters are optional
	 * 
	 * @param serviceName full or partial name of the service
	 * @param serviceCode the unique service code
	 * @param serviceGroup  service group code
	 * @param departmentCode  department code
	 * @param serviceDescription full or partial description
	 * @param procedure full or partial procedure
	 * @param serviceStatusCode full status code
	 * @param createdBefore -- created before date
	 * @param createdAfter -- created after date
	 * @param chargesFrom service charge (from)
	 * @param chargesTo service charge (to)
	 * @return returns the matching services information
	 */
	List<ServiceBM> getServices( String serviceName,
			                     String serviceCode,
							     String serviceGroup,
							     String departmentCode,
							     String serviceDescription,
							     String procedure,
							     String serviceStatusCode,
							     Date createdBefore,
							     Date createdAfter,
							     Double chargesFrom,
							     Double chargesTo );
	
	/**
	 * The method returns the service group information based on the specified parameters.
	 * All the parameters are optional
	 * @param serviceCode 
     * @param serviceGroupCode code of service group
	 * @param serviceGroupName full or partial service group name
	 * @param parentGroupCode code of parent service group
	 * @return returns the matching service groups information
	 */
	List<ServiceGroupBM> getServiceGroup (  String serviceGroupCode, String serviceGroupName,
			  								String parentGroupCode, String serviceCode );
	
	
	
	/**
	 * The method returns the complete list of active services code and description. This is useful in
	 * populating the drop-downs on the UI.
	 * The services are considered active, if it is in ACTIVE status and its effective date is before 
	 * today and expiration date is either not set or it is set to sometime in future.
	 * 
	 * @return
	 */
	List<CodeAndDescription> getServicesList();
	
	/**
	 * The method returns the active services list for the specified service group.
	 * If the group code is set to "ALL", it will mean that user intend to retrieve all
	 * the active services, irrespective of the group they belong to.
	 * 
	 * @param serviceGroupCode service group
	 * @return returns the list of services
	 */
	List<CodeAndDescription> getServicesOfGroup( String serviceGroupCode );
	
	/**
	 * The method returns the complete list of active service groups code and description.
	 * @return
	 */
	List<CodeAndDescription> getServiceGroupList();
	
	/**
	 * This method retrieves the list of packages configured into the systems based on the criteria 
	 * entered by the user. All the parameters are optional. If none of the parameters have been passed 
	 * then this call returns all the packages ordered on their name. 
	 * 
	 * @param packageName -- complete or partial match
	 * @param packageId -- complete match
	 * @param packageStatus  -- complete match
	 * @param descriotion -- complete or partial match
	 * @param effectiveFromDt -- the package's effective from date must be later than or same as this date
	 * @param effectiveToDt -- the package's effective to date must be before or same as this date
	 * @return
	 */
	List<ServicePackageSummaryBM> getServicePackages( String packageName,
													  String packageId, 
										              String packageStatus, 
										              String description,
										              String chargeOverrideLevel,
										              String ServiceCode,
										              Date   createdAfter,
										              Date   createdBefore,
										              Date 	 effectiveFromDt, 
										              Date 	 effectiveToDt ) ;
	
	/**
	 * This method returns complete service package details for a given package identifier
	 * 
	 * @param packageId
	 * @return
	 */
	ServicePackageBM getServicePackageDetail(  String packageId );
	
	/**
	 * For a given reference type (for example OPD or IPD) and reference number (for example of 
	 * appointment number of admission request number), this method retrieves all the assigned services and 
	 * packages. 
	 * 
	 * If a single reference number has single service order then there will be one AssignSvcAndPackageBM object
	 * per reference number. Usually this will be the case in OPD and DIRECT services. However, there may be 
	 * situations where single reference number may have multiple service order numbers (i.e. services were assigned
	 * multiple times as new service order), in that case single reference number may have multiple entries of 
	 * the AssignSvcAndPackageBM object. 
	 * 
	 * The returned list will be ordered on Service order number
	 * 
	 * @param referenceNumber --  This is a mandatory parameter. For OPD it will be appointment number and for 
	 *                            IPD it will be admission request number. For DIRECT patient, it will be patient id.
	 * @param referenceType  -- This is a mandatory parameter, which indicates the billing client name (e.g. OPD)
	 * @param unbilledFlag -- If this is set to true then only the unbilled services and 
	 *                        packages will be returned. This is an optional parameter. A 
	 *                        null value of False value will retrieve all the services and 
	 *                        packages.
	 * @return
	 */
	List<AssignSvcAndPackageBM> getAssignedServiceAndPackageList( String referenceNumber, 
			                                                      String referenceType , 
			                                                      Boolean unbilledFlag );
	
	/**
	 * This method returns the list of packages available for assignment at this moment.
	 * 
	 * @return
	 */
	List<ServicePackageSummaryBM> getCurrentlyAvailableServicePackages();
	
	/**
	 * The method updates the service information in the system.
	 * and it modifies the  Service and Service Group associations. 
	 * @param modifiedServiceBM updated service model
	 * @return returns the updated model
	 */
	ServiceBM modifyService( ServiceBM modifiedServiceBM );
	
	/**
	 * The method updates the service group information in the system.
	 * and it modifies the  Service and Service Group associations.
	 * 
	 * @param modifiedServiceGroupBM modified service group model
	 * @return returns the updated model
	 */
	ServiceGroupBM modifyServiceGroup( ServiceGroupBM modifiedServiceGroupBM );
	
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
	ServicePackageBM modifyServicePackage( ServicePackageBM modifiedServicePackageBM );

	/**
	 * This method modifies the assigned service details
	 * Some of the important events which this method specially takes are are
	 * 1) Modification of status of an assigned service
	 * 2) Change in the number of rendered units. During this event, it also creates rendered units
	 *    entry, which will allow billing to bill such units.
	 * 3) Marking the units as billed
	 * 
	 * @param assignedServiceBM
	 * @return
	 */
	AssignedServiceBM modifyAssignedService( AssignedServiceBM assignedServiceBM );
	
	/**
	 * This method modifies a assigned service status to rendered status and created corresponding
	 * details in the database. 
	 * 
	 * @param serviceUID -- (mandatory) the service assignment unique identifier
	 * @param numberOfUnitsRendered -- (optional) the number of units rendered. If this value has 
	 *        been supplied (default value = 1) then it must be greater than or equal to 1. 
	 *        Also, this value must not be more than the <<requested unit>> minus <<already rendered units>>
	 * @param renderedBy -- the person who is updating the assigned service as rendered
	 * 
	 */
	void modifyAssignedServiceToRendered( Integer serviceUID, Integer numberOfUnitsRendered, String renderedBy );
	
	/**
	 * This method modifies an assigned package details.
	 * An assigned package is created in "CREATED" status and from here it can move to one of the following status:
	 * 1) RENDERED
	 * 2) CANCELLED
	 * 3) DELETED
	 * 
	 * @param assignedPackageBM
	 */
	void modifyAssignedPackage( AssignedPackageBM assignedPackageBM );
	
	
	/**
	 * The method removes a service(s) from the system if it not assigned to any patient.
	 * 
	 * @param serviceCodeList -- list of serviceCodes 
	 */
	void removeServices( List<String> serviceCodeList );
	
	/**
	 * The method removes a service group(s) from the system if it is not assigned to any service
	 * 
	 * @param serviceGroupCodeList -- list of ServiceGroup codes
	 */
	void removeServiceGroups( List<String> serviceGroupCodeList );
	
	/**
	 * The service package or service packages can be removed as long as it is not assigned to any patient
	 * 
	 * @param servicePackageId
	 */
	void removeServicePackages( List<String> servicePackageIdList );
	
	/**
	 * This method assigns one or more service(s) and/or package(s) to a patient.
	 * 
	 * We can have Scenarios:
	 * 1. Doctor can assign services to particular patient at the time of Consultation.
	 *    In this case we can have appointment number. 
	 *    Reference Type = OPD
	 *    Reference Number = <<Appointment Number>>
	 * 2. Directly administrator can assign the services to patient in billing window,
	 *    for example Insurance test. In this case we appointment is not required.
	 *    Reference Type = DIRECT
	 *    Reference Number = <<-1>>
	 * 3. The service is part of a doctor order, which is associated with an admission number.
	 *    Reference Type = IPD
	 *    Reference Number = <<Admission Number>>
	 * 4. The service is part of an emergency treatment
	 * 	  Reference Type = EMERGENCY
	 *    Reference Number = <<Emergency Code>> 
	 * 
	 * The AssignSvcAndPackageBM contains two lists
	 * 1) List of AssignedPackageBM and
	 * 2) List of AssignedServiceBM    
	 * 
	 * This method expect that at least one of these list must contain at least one element.
	 *    
	 * @param assignSvcAndPackageBM
	 * @return
	 */
	Integer assignSvcAndPackages( AssignSvcAndPackageBM assignSvcAndPackageBM );
	
	/**
	 * 
	 * 
	 * @param serviceOrderNumber
	 * @param newPackageBMList
	 * @param deletedPackageBMList
	 * @param newServiceBMList
	 * @param deletedServiceBMList
	 * @param modifiedBy
	 */
	void modifyServiceAndPackageAssignment( Integer serviceOrderNumber, 
                                             AssignedPackageBM[] newPackageBMList, 
                                             AssignedPackageBM[] deletedPackageBMList, 
                                             AssignedServiceBM[] newServiceBMList,
                                             AssignedServiceBM[] deletedServiceBMList,
                                             String modifiedBy );
	
	/**
	 *  This method returns all the services assigned to a reference number of given reference types
	 *  The possible values of reference types are
	 *  1) DIRECT
	 *  2) OPD
	 *  3) EMERGENCY
	 *  4) IPD (reference number will be admission number)
	 *  
	 * @param referenceNumber
	 * @param referenceType
	 * @return
	 */
	List<AssignedServiceBM> getAssignedServicesList( String referenceNumber, String referenceType );
	
	/**
	 * For a given service order number, this method returns AssignSvcAndPackageBM object, which 
	 * contains list of assigned services and packages.
	 * 
	 * @param serviceOrderNumber -- is a number which user receives as part of the service order
	 * @param historyFlag -- this flag indicates whether service details should also be returned or not
	 * @return
	 * 
	 */
	AssignSvcAndPackageBM getAssignedServicesAndPackages( Integer serviceOrderNumber, Boolean historyFlag );
	
	
	
	/**
	 * Marks the assigned service as deleted. This method will not allow deletion of assigned services
	 * which are part of the package.
	 * 
	 * @param assignedServicesList
	 * @param removedBy
	 * @return
	 */
	void removeAssignedServices( Integer[] serviceUidList, String removedBy );
	
	/**
	 * This method removes list of assigned packages (move them to DELETED status). If any of the package 
	 * in the list has one or more services rendered then the transaction fails.
	 * 
	 * The assigned services associated with the assigned package also gets moved into the DELETED status.
	 * 
	 * @param packageAsgmIdList
	 * @param removedBy
	 */
	void removeAssignedPackages( List<Integer> packageAsgmIdList, String removedBy );
	
	/**
	 * All the services and packages assigned under this service number will be marked as DELETED iff none of them 
	 * has been rendered or PROCESSED.
	 * 
	 * @param serviceOrderNumber
	 * @param removedBy
	 * 
	 */
	void removeAssignedPackageAndServices( Integer serviceOrderNumber, String removedBy );
	
	
	/**
	 * This method bills all the unbilled & rendered services and packages.
	 * 1) For an IPD, a package will be billed even if single service has been rendered. 
	 * 2) Also, for IPD, a service will get billed (if it is not part of package) only when it is 
	 *    rendered. Also, if the billing processes will be responsible for updating the billed
	 *    units count correctly on the assigned services.  
	 * 3) For OPD, EMERGENCY and DIRECT reference type of service usage, if billing is run then
	 *    it will get billed even if it is not rendered 
	 * 
	 * @param referenceType
	 * @param referenceNumber
	 * @param billNumber
	 * @param billedBy
	 * @return
	 */
	BillDataBM billServiceDetails( String referenceType, String referenceNumber, Integer billNumber, String billedBy );
	
	
	/**
	 * This method returns billData object containing structured data for given bill number.
	 * The bill data is organized in process and subprocess level, subprocess level data contains
	 * detail about each billed item. 
	 * @param billNumber
	 * @return
	 */
	
	BillDataBM getBillData( Integer billNumber );
	
	/**
	* This method return ListRange object containing list of ServiceBM.
	 * @param serviceName
	 * @param serviceCode
	 * @param serviceGroup
	 * @param departmentCode
	 * @param serviceDescription
	 * @param procedure
	 * @param serviceStatusCode
	 * @param createdBefore
	 * @param createdAfter
	 * @param chargesFrom
	 * @param chargesTo
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange  findServices ( 	 String serviceName,
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
							     int start, 
							     int count, 
							     String orderBy );
	
	/**
	 * This method return Listrange object containing list of ServiceGroupBM.
	 * @param serviceGroupCode
	 * @param serviceGroupName
	 * @param parentGroupCode
	 * @param serviceCode
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange findServiceGroup( String serviceGroupCode, 
								String serviceGroupName,
								String parentGroupCode, 
								String serviceCode,
								int start, 
								int count, 
								String orderBy );
	/**
	 * this method returns ListRange Object containing list of ServicePackageBM
	 * @param packageName
	 * @param packageId
	 * @param packageStatus
	 * @param description
	 * @param chargeOverrideLevel
	 * @param ServiceCode
	 * @param createdAfter
	 * @param createdBefore
	 * @param effectiveFromDt
	 * @param effectiveToDt
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	
	 ListRange findServicePackages(   String packageName,
											String packageId, 
								            String packageStatus, 
								            String description,
								            String chargeOverrideLevel,
								            String ServiceCode,
								            Date createdAfter,
								            Date createdBefore,
								            Date effectiveFromDt, 
								            Date effectiveToDt, 
								            int start, 
								            int count, 
								            String orderBy );
	
	/**
	 * This method returns serviceSummaryBM of currently available services for given serviceGroup.
	 * If no groupName is given then it returns serviceSummaryBM with 'default serviceGroup'.
	 * @param serviceGroupCode
	 * @return
	 */
	ListRange getServiceSummaryBMforGroup(String serviceGroupCode ,int start, int count, String orderBy  );
	
/**
 * This method returns ServiceBM for given serviceCode. 
 * @param serviceCode
 * @return
 */
	ServiceBM findServicesByServiceCode(String serviceCode) ;
	
	/**
	 * 
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return ServiceSummaryBM of all available services.
	 */
	ListRange getServiceSummaryBM( int start, int count, String orderBy );
	
	/**
	 * 
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return ListRange with ServicePackageSummaryBM of all currently available package.
	 */
	ListRange  findAvailableServicePackages( int start, int count, String orderBy );
	
	
	/**
	 * This method exact match searching for 'code' type of parameters and
	 * for others provides partial lookup.
	 * All parameters of this method are optional.  
	 * @param serviceCode
	 * @param serviceName
	 * @param packageCode
	 * @param packageName
	 * @param serviceFromDt
	 * @param serviceToDt
	 * @param departmetnCode
	 * @param serviceGroupCode
	 * @param referenceType
	 * @param referenceNumber
	 * @param patientId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange  findAssignedServices( String serviceCode,String serviceName,String packageCode,String packageName,
		    Date serviceFromDt,Date serviceToDt, String departmetnCode,
		    String serviceGroupCode, String referenceType,String referenceNumber,
		    Integer patientId, int start,int count,	String orderBy  ) throws HCISException;
	
	/**
	 * 
	 * @param serviceGroupCode
	 * @return
	 * @throws HCISException
	 */
	boolean isServiceGroupExist( String serviceGroupCode ) throws HCISException;
	
	/**
	 * 
	 * @param serviceCode
	 * @return
	 * @throws HCISException
	 */
	boolean isServiceExist( String serviceCode ) throws HCISException;

	/**
	 * 
	 * @param packageId
	 * @return
	 * @throws HCISException
	 */
	boolean isServicePackageExist( String packageId ) throws HCISException;
	
	/**
	 * 
	 * @param serviceUIDList
	 * @param canceledBy
	 * @throws HCISException
	 */
	void cancelAssignedServices(	Integer[] serviceUIDList,String canceledBy ) throws HCISException;
	
	/**
	 * This method returns un-billed assigned services with given
	 * 
	 * @param referenceType
	 * @param referenceNumber
	 * @return
	 * @throws HCISException
	 */
	BillDataBM getUbilledAssignedServices( String referenceType, String referenceNumber ) throws HCISException;
	
	/**
	 * @param patientId
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return The list of all assigned services to the patient
	 */
	ListRange getAssignedServiceHistory(Integer patientId, int start,int count,	String orderBy);
	
	/**
	 * This method mark or unmark the assigned service as bill-able.
	 * @param serviceUID
	 * @return
	 */
	String markAssignedServiceIsBillable( Integer serviceUID );
	
	/**
	 * 
	 * @param packageAsgmId
	 * @param cancelledBy
	 * @return TODO
	 */
	CodeAndDescription cancelAssignedPackages(Integer packageAsgmId , String cancelledBy);
	
	
	/**
	 * This method creates updates future dated price configuration for given service list.
	 * 
	 *  New price of service can be given as individual level or at high level also.
	 *  In case if both prices are defined then individual level price will be applied.
	 *  
	 *  Method throws exception if more then two prices are defined for the same service with
	 *  common date range.
	 *  
	 * @param priceUpdateBM
	 */
	void updateServicePrice( ServicePriceUpdateBM priceUpdateBM);
	
	/**
	 * The method give the complete price detail for given service code
	 * @param serviceCode
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange getServicePriceDetail( String serviceCode, int start,int count, String orderBy);
	
	/**
	 * Remove service price configuration.
	 * @param sercviceCode
	 * @param createdDtm
	 * @return
	 */
	boolean removeServicePirceDetail(String sercviceCode,Date createdDtm);
	
	/**
	 * 
	 * @param servicePriceDetailBMList
	 */
	void createServicePriceDetailEntry( ServicePriceDetailBM[] servicePriceDetailBMList);
	
	/**
	 * 
	 * @return
	 */
	boolean processServicePriceDetail( String servicePrice, Date processForDate );
	
	/**
	 * This method will return List of assignedServiceBM
	 * @param referenceType
	 * @param referenceNumber
	 * @param patientId
	 * @param serviceType
	 * @return
	 */
	ListRange getAssignedServicesForServiceType( String referenceType, String referenceNumber , Integer patientId, String serviceType,
			Integer start ,Integer count ,String orderBy);
	
	/**
	 * Similar to method "cancelAssignedServices(Integer[] serviceUIDList,String canceledBy )"
	 * But it reruns new status of assignedService
	 * @param serviceUID
	 * @param canceledBy
	 * @return
	 * @throws HCISException
	 */
	CodeAndDescription cancelIndevidualAssignedService(	Integer serviceUID,String canceledBy ) throws HCISException;
}
