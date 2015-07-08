package in.wtc.hcis.ip.common;

import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.List;

import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.ServiceDAO;
import com.wtc.hcis.da.ServicePackage;
import com.wtc.hcis.da.extn.ServicePackageDAOExtn;
import com.wtc.hcis.ip.da.ActionStatus;
import com.wtc.hcis.ip.da.ActionStatusDAO;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.ActivityTypeDAO;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionClaimRequest;
import com.wtc.hcis.ip.da.AdmissionClaimRequestDAO;
import com.wtc.hcis.ip.da.AdmissionEntryPoint;
import com.wtc.hcis.ip.da.AdmissionEntryPointDAO;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.AdmissionStatusDAO;
import com.wtc.hcis.ip.da.Attribute;
import com.wtc.hcis.ip.da.AttributeDAO;
import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.BedEnvelopeDAO;
import com.wtc.hcis.ip.da.BedHasSpecialFeatures;
import com.wtc.hcis.ip.da.BedHasSpecialFeaturesDAO;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedMasterDAO;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedPoolDAO;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedReservationDAO;
import com.wtc.hcis.ip.da.BedSpecialFeature;
import com.wtc.hcis.ip.da.BedSpecialFeatureDAO;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedStatusDAO;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.BedTypeDAO;
import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.ClaimSponsorDAO;
import com.wtc.hcis.ip.da.CreditClass;
import com.wtc.hcis.ip.da.CreditClassDAO;
import com.wtc.hcis.ip.da.DischargeOrder;
import com.wtc.hcis.ip.da.DischargeOrderDAO;
import com.wtc.hcis.ip.da.DischargeType;
import com.wtc.hcis.ip.da.DischargeTypeDAO;
import com.wtc.hcis.ip.da.DoctorOrder;
import com.wtc.hcis.ip.da.DoctorOrderDAO;
import com.wtc.hcis.ip.da.DoctorOrderGroup;
import com.wtc.hcis.ip.da.DoctorOrderGroupDAO;
import com.wtc.hcis.ip.da.DoctorOrderStatus;
import com.wtc.hcis.ip.da.DoctorOrderStatusDAO;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDAO;
import com.wtc.hcis.ip.da.DoctorOrderType;
import com.wtc.hcis.ip.da.DoctorOrderTypeDAO;
import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.InsurancePlansDAO;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.InsurerDAO;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitDAO;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.NursingUnitTypeDAO;
import com.wtc.hcis.ip.da.ReservationStatus;
import com.wtc.hcis.ip.da.ReservationStatusDAO;
import com.wtc.hcis.ip.da.SponsorClaimStatus;
import com.wtc.hcis.ip.da.SponsorClaimStatusDAO;
import com.wtc.hcis.ip.da.SponsorType;
import com.wtc.hcis.ip.da.SponsorTypeDAO;
import com.wtc.hcis.ip.da.extn.AdmissionDAOExtn;

public class IPDataModelManagerImpl implements IPDataModelManager {

	BedEnvelopeDAO bedEnvelopeDAO;
	BedMasterDAO bedMasterDAO;
	BedPoolDAO bedPoolDAO;

	BedReservationDAO bedReservationDAO;
	BedStatusDAO bedStatusDAO;
	BedTypeDAO bedTypeDAO;
	NursingUnitDAO nursingUnitDAO;
	NursingUnitTypeDAO nursingUnitTypeDAO;
	AdmissionDAOExtn admissionDAO;
	
	DoctorOrderDAO doctorOrderDAO;
	DoctorOrderGroupDAO doctorOrderGroupDAO;
	DoctorOrderTemplateDAO doctorOrderTemplateDAO;
	DoctorOrderStatusDAO doctorOrderStatusDAO;
	DoctorOrderTypeDAO doctorOrderTypeDAO;
	ActionStatusDAO actionStatusDAO;
	ActivityTypeDAO activityTypeDAO;
	ServiceDAO serviceDAO;
	AdmissionStatusDAO admissionStatusDAO;
	BedHasSpecialFeaturesDAO bedHasSpecialFeaturesDAO;
	BedSpecialFeatureDAO bedSpecialFeatureDAO;
	
	AdmissionEntryPointDAO admissionEntryPointDAO;
	
	DischargeTypeDAO dischargeTypeDAO;
	DischargeOrderDAO dischargeOrderDAO;
	
	AdmissionClaimRequestDAO admissionClaimRequestDAO;
	ClaimSponsorDAO claimSponsorDAO;
	CreditClassDAO creditClassDAO;
	InsurancePlansDAO insurancePlansDAO;
	SponsorTypeDAO sponsorTypeDAO;
	SponsorClaimStatusDAO sponsorClaimStatusDAO;
	AttributeDAO attributeDAO;
	InsurerDAO insurerDAO;
	ReservationStatusDAO reservationStatusDAO;
	ServicePackageDAOExtn servicePackageDAO;
	
	
	public Admission getAdmission( Integer admissionReqNbr)throws HCISException{
		try{
			Admission admission = admissionDAO.findById(admissionReqNbr);
			if ( admission == null ) {
				throw new Exception( " Admission with Request Number = " + admissionReqNbr + " does not exist. " );
			}
			
			return admission;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ADMISSION_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
		
	}
	
	public Attribute getAttribute(String attributeName) throws HCISException {
		try{
			Attribute attribute = attributeDAO.findById( attributeName ) ;
			if ( attribute == null ) {
				throw new Exception( " Attribute with Name = " + attributeName + " does not exist. " );
			}
			
			return attribute;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ATTRIBUTE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	
	public BedEnvelope getBedEnvelope(String envelopeName) throws HCISException {
		
		try{
		BedEnvelope bedEnvelope = bedEnvelopeDAO.findById(envelopeName);
		if ( bedEnvelope == null ) {
			throw new Exception( " Bed Envelope with Envelope Name : " + envelopeName + " does not exist. " );
		}
		
		return bedEnvelope;
	} catch (Exception e) {
		Fault fault = new Fault( ApplicationErrors.READ_BEDENVELOPE_FAILED );
		
		throw new HCISException( fault.getFaultMessage() + e.getMessage(),
								 fault.getFaultCode(),
								 fault.getFaultType() );		
	}
	
	}

	public BedMaster getBedMaster(String bedNumber) throws HCISException {
		try{
			BedMaster bedMaster = bedMasterDAO.findById(bedNumber);
			if ( bedMaster == null ) {
				throw new Exception( " Bed Master with Bed Number = " + bedNumber + " does not exist. " );
			}
			
			return bedMaster;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDMASTER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );		
		}
	}

	public BedPool getBedPool(String poolName) throws HCISException {
		
		try{
		BedPool bedPool= bedPoolDAO.findById(poolName);
			if ( bedPool == null ) {
			throw new Exception( " Bed pool with Pool Name : " + poolName + " does not exist. " );
		}
		
		return bedPool;
		
	} catch (Exception e) {
		Fault fault = new Fault( ApplicationErrors.READ_BEDPOOL_FAILED );
		
		throw new HCISException( fault.getFaultMessage() + e.getMessage(),
								 fault.getFaultCode(),
								 fault.getFaultType() );		
	}
	}

/*	public BedPoolUnitType getBedPoolUnitType(
			BedPoolUnitTypeId bedPoolUnitTypeId) throws HCISException {
		try {
			BedPoolUnitType bedPoolUnitType = bedPoolUnitTypeDAO.findById(bedPoolUnitTypeId);
			if(bedPoolUnitType == null ){
				throw new Exception("Bed Pool Unit Type with Id = " + bedPoolUnitTypeId + " does not exist. ");
			}
			
			return bedPoolUnitType;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDPOOL_UNIT_TYPE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
*/
	public BedReservation getBedReservation(Integer bedReservationNumber)
			throws HCISException {
		try {
			BedReservation bedReservation = bedReservationDAO.findById(bedReservationNumber);
			if(bedReservation == null ){
				throw new Exception("Bed Reservation with Bed Reservation Number = " + bedReservationNumber + " does not exist. ");
			}
			
			return bedReservation;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDRESERVATION_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}

	public BedStatus getBedStatus(String bedStatusCd) throws HCISException {
		
		try {
			BedStatus bedStatus = bedStatusDAO.findById(bedStatusCd);
			if(bedStatus == null ){
				throw new Exception("Bed Status for the Bed Status code = " + bedStatusCd + " does not exist. ");
			}
			
			return bedStatus;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDSTATUS_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}

	public BedType getBedType(String bedTypeCd) throws HCISException {
		try {
			BedType bedType = bedTypeDAO.findById(bedTypeCd);
			if(bedType == null ){
				throw new Exception("Bed Type with the Bed Type code = " + bedTypeCd + " does not exist. ");
			}
			
			return bedType;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDTYPE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}

	public NursingUnit getNursingUnit(String nursingUnitName)
			throws HCISException {
		try {
			NursingUnit nursingUnit = nursingUnitDAO.findById(nursingUnitName);
			if(nursingUnit == null ){
				throw new Exception("Nursing Unit with the Nursing Unit Name = " + nursingUnitName + " does not exist. ");
			}
			
			return nursingUnit;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_NURSING_UNIT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}

	public NursingUnitType getNursingUnitType(String nursingUnitTypeCd)
			throws HCISException {	try {
				NursingUnitType nursingUnitType = nursingUnitTypeDAO.findById(nursingUnitTypeCd);
				if(nursingUnitType == null ){
					throw new Exception("Nursing Unit Type with the Nursing Unit Type Code = " + nursingUnitTypeCd + " does not exist. ");
				}
				
				return nursingUnitType;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_NURSING_UNIT_TYPE_FAILED );
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}
	}
	
	
	
		public DoctorOrderGroup getDoctorOrderGroup(String doctorOrderGroupName) throws HCISException {
			try{
			DoctorOrderGroup doctorOrderGroup = doctorOrderGroupDAO.findById(doctorOrderGroupName);
			if(doctorOrderGroup == null ){
				throw new Exception("Doctor Order Group with Group Name: " + doctorOrderGroupName + " does not exist. ");
			}
			
			return doctorOrderGroup;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_GROUP_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
			
	}
	
	public DoctorOrderTemplate getDoctorOrderTemplate(String templateId)
		throws HCISException {
		try{
			DoctorOrderTemplate doctorOrderTemplate = doctorOrderTemplateDAO.findById(templateId);
			if(doctorOrderTemplate == null ){
				throw new Exception("Doctor Order Template  with Tempplate id = " + templateId + " does not exist. ");
			}
			
			return doctorOrderTemplate;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_TEMPLATE_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public DoctorOrderType getDoctorOrderType(String doctorOrderTypeCd)
		throws HCISException {
		try{
			DoctorOrderType doctorOrderType = doctorOrderTypeDAO.findById(doctorOrderTypeCd);
			if(doctorOrderType == null ){
				throw new Exception("Doctor Order Type  with Type Code = " + doctorOrderTypeCd + " does not exist. ");
			}
			
			return doctorOrderType;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_TYPE_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}	
	}
	
	public DoctorOrder getDoctorOrder(Integer orderNumber)
		throws HCISException {
		try{
			DoctorOrder doctorOrder = doctorOrderDAO.findById(orderNumber);
			if(doctorOrder == null ){
				throw new Exception("Doctor Order with Order Number = " + orderNumber + " does not exist. ");
			}
			
			return doctorOrder;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public DoctorOrderStatus getDoctorOrderStatus(String doctorStatusCd)
		throws HCISException {
		try{
			DoctorOrderStatus doctorOrderStatus = doctorOrderStatusDAO.findById(doctorStatusCd);
			if(doctorOrderStatus == null ){
				throw new Exception("Doctor Order Status with Status code = " + doctorStatusCd + " does not exist. ");
			}
			
			return doctorOrderStatus;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_DOCTOR_ORDER_STATUS_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public ActionStatus getActionStatus(String actionStatusCd)
		throws HCISException {
		try{
			ActionStatus actionStatus = actionStatusDAO.findById(actionStatusCd);
			if(actionStatus == null ){
				throw new Exception("Doctor Order Status with Status code = " + actionStatusCd + " does not exist. ");
			}
			
			return actionStatus;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ACTION_STATUS_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public Service getService(String serviceId) throws HCISException {
		try{
			
			Service service = serviceDAO.findById(serviceId);
			if(service == null ){
				throw new Exception("Service with Service Id = " + serviceId + " does not exist. ");
			}
			
			return service;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_SERVICE_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public ActivityType getActivityType(String activityTypeCd)
		throws HCISException {
	try{
		ActivityType activityType = activityTypeDAO.findById(activityTypeCd);	
		if(activityType == null ){
				throw new Exception("Activity Type with Activity Type Code = " + activityTypeCd + " does not exist. ");
			}
			
			return activityType;
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_ACTIVITY_TYPE_FAILED);
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );	
		}
	}
	
	public AdmissionStatus getAdmissionStatus(String admissionStatusCd)
		throws HCISException {
		try{
			AdmissionStatus admissionStatus = admissionStatusDAO.findById(admissionStatusCd);
			if(admissionStatus == null ){
					throw new Exception("Admission Status with Status Code = " + admissionStatusCd + " does not exist. ");
				}
				
				return admissionStatus;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_ADMISSION_STATUS_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}
	}


	/**
	 * @param bedMunber
	 * 
	 * 
	 * This method returns List of BedHasSpecialFeatures relationship for the given bed number.
	 * If no special feature(s) found, his method will return null; instead of throwing exception, because there are 
	 * possibilities that, the bed does not have any special feature.  
	 *
	 */
	
	
	public List<BedHasSpecialFeatures> getBedHasSpecialFeaturesList( String bedNumber ) 
	{
		try {
			List<BedHasSpecialFeatures>specialFeatureOfBedList = bedHasSpecialFeaturesDAO.findByProperty( "id.bedNumber",bedNumber );
			
			return specialFeatureOfBedList;
		} catch (Exception e) {
			
			return null;
		}
		
	}

	public BedSpecialFeature getBedSpecialFeature( String featureName)	throws HCISException {
		try{
			BedSpecialFeature bedSpecialFeature = bedSpecialFeatureDAO.findById(featureName);
			
//			if(bedSpecialFeature == null ){
//					throw new Exception("Bed Special Feature with Feature Name : " + featureName + " does not exist. ");
//				}
//				
				return bedSpecialFeature;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_BED_SPECIAL_FEATURE_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}
	
	public AdmissionEntryPoint getAdmissionEntryPoint( String admissionEntryPointCd ) throws HCISException {
		try{
			AdmissionEntryPoint admissionEntryPoint = admissionEntryPointDAO.findById(admissionEntryPointCd);
			
			if(admissionEntryPoint == null ){
					throw new Exception(" Entry point with Entrypoint code = " + admissionEntryPointCd + ", does not exist. ");
				}
				
				return admissionEntryPoint;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_ADMISSION_ENTRY_POINT_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}
	}

	public DischargeType getDischargeTpe( String dischargeTypeCd ) throws HCISException {
		try{
			DischargeType dischargeType = dischargeTypeDAO.findById( dischargeTypeCd );
			
			if(dischargeType == null ){
					throw new Exception(" Discharge Type with Type Code = " + dischargeTypeCd + ", does not exist. ");
				}
				
				return dischargeType;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_DISCHARGE_TYPE_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}
	}

	public DischargeOrder getDischargeOrder( Integer admissionReqNbr )
			throws HCISException {
		try{
			DischargeOrder dischargeOrder = dischargeOrderDAO.findById(admissionReqNbr);
			
			if(dischargeOrder == null ){
					throw new Exception(" Discharge Order for Admission Req Nbr = " + admissionReqNbr + ", does not exist. ");
				}
				
				return dischargeOrder;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_DISCHARGE_ORDER_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}
	}
	
	public AdmissionClaimRequest getAdmissionClaimRequest( Long requestSequenceNbr ) throws HCISException {
		try{
			AdmissionClaimRequest admissionClaimRequest = admissionClaimRequestDAO.findById(requestSequenceNbr);
			
			if(admissionClaimRequest == null ){
					throw new Exception(" Claim Request for Request Number= " + requestSequenceNbr + ", does not exist. ");
				}
				
				return admissionClaimRequest;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_CLAIM_REQUEST_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public ClaimSponsor getClaimSponsor( String sponsorName )	throws HCISException {
		try{
			ClaimSponsor claimSponsor = claimSponsorDAO.findById(sponsorName);
			
			if(claimSponsor == null ){
					throw new Exception("Sponsor with Sponsor Name : " + sponsorName + ", does not exist. ");
				}
				
				return claimSponsor;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_CLAIM_SPONSOR_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public CreditClass getCreditClass( String creditClassCd )	throws HCISException {
		try{
			CreditClass creditClass = creditClassDAO.findById(creditClassCd);
			
			if(creditClass == null ){
					throw new Exception("Credit class with Class Code =" + creditClassCd + ", does not exist. ");
				}
				
				return creditClass;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_CREDIT_CLASS_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public InsurancePlans getInsurancePlan(String planCd) throws HCISException {
		try{
			InsurancePlans insurancePlans = insurancePlansDAO.findById(planCd);
			
			if(insurancePlans == null ){
					throw new Exception("Insurance plan with plan Name : " + planCd + ", does not exist. ");
				}
				
				return insurancePlans;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_INSURANCE_PLAN_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public SponsorType getSposorType( String sposnsorTypeCd )	throws HCISException {
		try{
			SponsorType sponsorType = sponsorTypeDAO.findById(sposnsorTypeCd);
			
			if(sponsorType == null ){
					throw new Exception("Sponsor Type with Type Code =" + sposnsorTypeCd + ", does not exist. ");
				}
				
				return sponsorType;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_SPONSOR_TYPE_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public SponsorClaimStatus getSponsorClaimStatus( String claimStatusCd )	throws HCISException {
		try{
			SponsorClaimStatus sponsorClaimStatus = sponsorClaimStatusDAO.findById(claimStatusCd);
			
			if(sponsorClaimStatus == null ){
					throw new Exception("Sponsor claim status with Status Code : " + claimStatusCd + ", does not exist. ");
				}
				
				return sponsorClaimStatus;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_SPONSOR_CLAIM_STATUS_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	
	
	public Insurer getInsurer(String insurerName) throws HCISException {
		
		try{
			Insurer insurer = insurerDAO.findById( insurerName );
			
			if(insurer == null ){
					throw new Exception("Insurer with Name : " + insurerName + ", does not exist. ");
				}
				
				return insurer;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_INSURER_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
		
	}
	public ReservationStatus getReservationStatus( String reservationStatusCd ) throws HCISException{
		try{
			ReservationStatus reservationStatus = reservationStatusDAO.findById( reservationStatusCd );
			
			if(reservationStatus == null ){
					throw new Exception("Reservatino Status with Code : " + reservationStatusCd + ", does not exist. ");
				}
				
				return reservationStatus;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_RESERVAION_STATUS_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}
	/*
	 * This method returns active admission request number of given patientNumber. 
	 * (non-Javadoc)
	 * @see in.wtc.hcis.ip.common.IPDataModelManager#getActiveAdmissionOfPatient(java.lang.Integer)
	 */
	public Integer getActiveAdmissionOfPatient( Integer patientId ) throws HCISException{
		
		List<Admission> activeAdmissionList = admissionDAO.getActiveAdmissions(patientId);
		
		if( activeAdmissionList != null && !activeAdmissionList.isEmpty()){
			return activeAdmissionList.get(0).getAdmissionReqNbr();
		}
		return null;
	}
	
	public ServicePackage getPackage(String pkgCode){
		ServicePackage svcPackage = servicePackageDAO.findById(pkgCode);
		if(svcPackage == null ){
			throw new RuntimeException("Pacekage with Packag Id = " + pkgCode + " does not exist. ");
		}
		return svcPackage;
	}
	
	public BedEnvelopeDAO getBedEnvelopeDAO() {
		return bedEnvelopeDAO;
	}

	public void setBedEnvelopeDAO(BedEnvelopeDAO bedEnvelopeDAO) {
		this.bedEnvelopeDAO = bedEnvelopeDAO;
	}

	public BedMasterDAO getBedMasterDAO() {
		return bedMasterDAO;
	}

	public void setBedMasterDAO(BedMasterDAO bedMasterDAO) {
		this.bedMasterDAO = bedMasterDAO;
	}

	public BedPoolDAO getBedPoolDAO() {
		return bedPoolDAO;
	}

	public void setBedPoolDAO(BedPoolDAO bedPoolDAO) {
		this.bedPoolDAO = bedPoolDAO;
	}

	public BedReservationDAO getBedReservationDAO() {
		return bedReservationDAO;
	}

	public void setBedReservationDAO(BedReservationDAO bedReservationDAO) {
		this.bedReservationDAO = bedReservationDAO;
	}

	public BedStatusDAO getBedStatusDAO() {
		return bedStatusDAO;
	}

	public void setBedStatusDAO(BedStatusDAO bedStatusDAO) {
		this.bedStatusDAO = bedStatusDAO;
	}

	public BedTypeDAO getBedTypeDAO() {
		return bedTypeDAO;
	}

	public void setBedTypeDAO(BedTypeDAO bedTypeDAO) {
		this.bedTypeDAO = bedTypeDAO;
	}

	public NursingUnitDAO getNursingUnitDAO() {
		return nursingUnitDAO;
	}

	public void setNursingUnitDAO(NursingUnitDAO nursingUnitDAO) {
		this.nursingUnitDAO = nursingUnitDAO;
	}

	public NursingUnitTypeDAO getNursingUnitTypeDAO() {
		return nursingUnitTypeDAO;
	}

	public void setNursingUnitTypeDAO(NursingUnitTypeDAO nursingUnitTypeDAO) {
		this.nursingUnitTypeDAO = nursingUnitTypeDAO;
	}

	public void setDoctorOrderDAO(DoctorOrderDAO doctorOrderDAO) {
		this.doctorOrderDAO = doctorOrderDAO;
	}

	public void setDoctorOrderGroupDAO(DoctorOrderGroupDAO doctorOrderGroupDAO) {
		this.doctorOrderGroupDAO = doctorOrderGroupDAO;
	}

	public void setDoctorOrderTemplateDAO(
			DoctorOrderTemplateDAO doctorOrderTemplateDAO) {
		this.doctorOrderTemplateDAO = doctorOrderTemplateDAO;
	}

	public void setDoctorOrderStatusDAO(DoctorOrderStatusDAO doctorOrderStatusDAO) {
		this.doctorOrderStatusDAO = doctorOrderStatusDAO;
	}

	public void setDoctorOrderTypeDAO(DoctorOrderTypeDAO doctorOrderTypeDAO) {
		this.doctorOrderTypeDAO = doctorOrderTypeDAO;
	}

	public void setActionStatusDAO(ActionStatusDAO actionStatusDAO) {
		this.actionStatusDAO = actionStatusDAO;
	}

	public void setActivityTypeDAO(ActivityTypeDAO activityTypeDAO) {
		this.activityTypeDAO = activityTypeDAO;
	}

	public void setServiceDAO(ServiceDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	public void setAdmissionStatusDAO(AdmissionStatusDAO admissionStatusDAO) {
		this.admissionStatusDAO = admissionStatusDAO;
	}


	public void setBedHasSpecialFeaturesDAO(
			BedHasSpecialFeaturesDAO bedHasSpecialFeaturesDAO) {
		this.bedHasSpecialFeaturesDAO = bedHasSpecialFeaturesDAO;
	}

	public void setBedSpecialFeatureDAO(BedSpecialFeatureDAO bedSpecialFeatureDAO) {
		this.bedSpecialFeatureDAO = bedSpecialFeatureDAO;
	}

	public void setAdmissionClaimRequestDAO(
			AdmissionClaimRequestDAO admissionClaimRequestDAO) {
		this.admissionClaimRequestDAO = admissionClaimRequestDAO;
	}

	public void setClaimSponsorDAO(ClaimSponsorDAO claimSponsorDAO) {
		this.claimSponsorDAO = claimSponsorDAO;
	}

	public void setCreditClassDAO(CreditClassDAO creditClassDAO) {
		this.creditClassDAO = creditClassDAO;
	}

	public void setInsurancePlansDAO(InsurancePlansDAO insurancePlansDAO) {
		this.insurancePlansDAO = insurancePlansDAO;
	}

	public void setSponsorTypeDAO(SponsorTypeDAO sponsorTypeDAO) {
		this.sponsorTypeDAO = sponsorTypeDAO;
	}

	public void setSponsorClaimStatusDAO(SponsorClaimStatusDAO sponsorClaimStatusDAO) {
		this.sponsorClaimStatusDAO = sponsorClaimStatusDAO;
	}


	public void setAdmissionEntryPointDAO(
			AdmissionEntryPointDAO admissionEntryPointDAO) {
		this.admissionEntryPointDAO = admissionEntryPointDAO;
	}

	public void setDischargeTypeDAO(DischargeTypeDAO dischargeTypeDAO) {
		this.dischargeTypeDAO = dischargeTypeDAO;
	}

	public void setDischargeOrderDAO(DischargeOrderDAO dischargeOrderDAO) {
		this.dischargeOrderDAO = dischargeOrderDAO;
	}

	public void setAttributeDAO(AttributeDAO attributeDAO) {
		this.attributeDAO = attributeDAO;
	}

	public void setInsurerDAO(InsurerDAO insurerDAO) {
		this.insurerDAO = insurerDAO;
	}

	public void setReservationStatusDAO(ReservationStatusDAO reservationStatusDAO) {
		this.reservationStatusDAO = reservationStatusDAO;
	}

	public void setAdmissionDAO(AdmissionDAOExtn admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public void setServicePackageDAO(ServicePackageDAOExtn servicePackageDAO) {
		this.servicePackageDAO = servicePackageDAO;
	}



	
}
