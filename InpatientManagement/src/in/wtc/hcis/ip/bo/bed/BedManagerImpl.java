/**
 * 
 */
package in.wtc.hcis.ip.bo.bed;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.BedBookingDetails;
import in.wtc.hcis.bm.ip.BedEnvelopeBM;
import in.wtc.hcis.bm.ip.BedEnvelopePoolAsgmBM;
import in.wtc.hcis.bm.ip.BedMasterBM;
import in.wtc.hcis.bm.ip.BedPoolBM;
import in.wtc.hcis.bm.ip.BedPoolUnitTypeAsgmBM;
import in.wtc.hcis.bm.ip.BedReservationBM;
import in.wtc.hcis.bm.ip.BedSpecialFeatureAvailability;
import in.wtc.hcis.bm.ip.PreferredBedBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.order.DoctorOrderConstants;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;


import com.wtc.hcis.da.Patient;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.BedActivity;
import com.wtc.hcis.ip.da.BedActivityDAO;
import com.wtc.hcis.ip.da.BedActivityId;
import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.extn.BedEnvelopeDAOExtn;

import com.wtc.hcis.ip.da.BedBillHistory;
import com.wtc.hcis.ip.da.BedBillHistoryDAO;
import com.wtc.hcis.ip.da.BedBillHistoryId;
import com.wtc.hcis.ip.da.BedEnvelopeHasPool;
import com.wtc.hcis.ip.da.BedHasSpecialFeatures;
import com.wtc.hcis.ip.da.BedHasSpecialFeaturesDAO;
import com.wtc.hcis.ip.da.BedHasSpecialFeaturesId;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedPoolHasUnitType;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedReservationActivity;
import com.wtc.hcis.ip.da.BedReservationActivityDAO;
import com.wtc.hcis.ip.da.BedReservationActivityId;
import com.wtc.hcis.ip.da.BedReservationSpecialFeatures;
import com.wtc.hcis.ip.da.BedReservationSpecialFeaturesDAO;
import com.wtc.hcis.ip.da.BedReservationSpecialFeaturesId;
import com.wtc.hcis.ip.da.BedSpecialFeature;
import com.wtc.hcis.ip.da.BedSpecialFeatureDAO;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedUsageHistory;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitType;
import com.wtc.hcis.ip.da.NursingUnitTypeDAO;
import com.wtc.hcis.ip.da.ReservationStatus;
import com.wtc.hcis.ip.da.extn.BedEnvelopeHasPoolDAOExtn;
import com.wtc.hcis.ip.da.extn.BedMasterDAOExtn;
import com.wtc.hcis.ip.da.extn.BedPoolDAOExtn;
import com.wtc.hcis.ip.da.extn.BedPoolHasUnitTypeDAOExtn;
import com.wtc.hcis.ip.da.extn.BedReservationDAOExtn;
import com.wtc.hcis.ip.da.extn.BedUsageHistoryDAOExtn;
import com.wtc.hcis.ip.da.extn.util.DAUtil;

/**
 * @author Alok Ranjan
 *
 */
public class BedManagerImpl implements BedManager {

	private IPDataModelConverter dataModelConverter;
	private IPDataModelManager dataModelManager;
	private DataModelManager coreDataModelManager;
	private BedActivityDAO bedActivityDAO;
	private BedMasterDAOExtn bedMasterDAO;
	private BedPoolHasUnitTypeDAOExtn bedPoolHasUnitTypeDAO;
	private BedEnvelopeDAOExtn bedEnvelopeDAO;
	private BedEnvelopeHasPoolDAOExtn bedEnvelopeHasPoolDAO;
	private BedReservationDAOExtn bedReservationDAO;
	private NursingUnitTypeDAO nursingUnitTypeDAO;
	private BedReservationSpecialFeaturesDAO bedReservationSpecialFeaturesDAO;
	private BedHasSpecialFeaturesDAO bedHasSpecialFeaturesDAO;
	private BedSpecialFeatureDAO bedSpecialFeatureDAO;
	private BedPoolDAOExtn bedPoolDAO;
	private BedReservationActivityDAO bedReservationActivityDAO;
	private BedUsageHistoryDAOExtn bedUsageHistoryDAO;
	private BedBillHistoryDAO bedBillHistoryDAO;

	final long DAY_IN_MILLS = 60*60*24*1000;
	final long HOUR_IN_MILLS= 60*60*1000;

	/* (non-Javadoc)
	 * This method adds bed details into the BED_MASTER table.
	 * Additionally, it also updates the total bed count and available bed count 
	 * values for the corresponding unit type and bed pools. 
	 * It is possible to add bed in a state other than "AVAILABLE" status. In such cases 
	 * we must not increase the total available count.
	 * 
	 * If BedMasterBM contains "SpecialFeatureAvailabilityList" then it creates "Bed Has Special Features" association(s). 
	 * 
	 * It also creates bed activity :- If bed is added in "AVAILABEL" status then it creates bed activity
	 * as "BED_AVAILABLE" otherwise create activity as "BED_CREATED".
	 *
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#addBed(in.wtc.hcis.bm.ip.BedMasterBM)
	 */
	public void setBedSpecialFeatureDAO(BedSpecialFeatureDAO bedSpecialFeatureDAO) {
		this.bedSpecialFeatureDAO = bedSpecialFeatureDAO;
	}

	public BedMasterBM addBed( BedMasterBM bedMasterBM ) throws HCISException {
		
		
		try {
			BedMaster bedMaster;
			bedMaster = dataModelConverter.convertBedMasterBM2DM( bedMasterBM, null );
			
			bedMasterDAO.save( bedMaster );

			List <BedSpecialFeatureAvailability> specialFeatureAvailabilityList = bedMasterBM.getSpecialFeatureAvailabilityList();
			
			if ( specialFeatureAvailabilityList != null && !specialFeatureAvailabilityList.isEmpty() ){
			
				for( BedSpecialFeatureAvailability featureAvailability : specialFeatureAvailabilityList ){
					
					BedHasSpecialFeatures bedHasSpecialFeatures = new BedHasSpecialFeatures();
					BedHasSpecialFeaturesId bedHasSpecialFeaturesId = new BedHasSpecialFeaturesId();
					bedHasSpecialFeaturesId.setBedNumber(bedMaster.getBedNumber());
					bedHasSpecialFeaturesId.setFeatureName(featureAvailability.getFeatureName());
					
					bedHasSpecialFeatures.setId(bedHasSpecialFeaturesId);
					bedHasSpecialFeatures.setEffectiveFromDate(new Date());

//TODO:				bedHasSpecialFeatures.setEffectiveToDate(effectiveToDate);
					 					
					bedHasSpecialFeaturesDAO.save(bedHasSpecialFeatures);
					
				}
				
			}
			boolean bedStatusAvail=false;
			NursingUnit nursingUnit = bedMaster.getNursingUnit();
			if ( nursingUnit != null ) {
				
				NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType(nursingUnit.getNursingUnitType().getUnitTypeCd());			
				nursingUnitType.setTotalBedCount(nursingUnitType.getTotalBedCount() + 1);
				
				
				BedStatus bedStatus = bedMaster.getBedStatus();
				if ( bedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) {
					nursingUnitType.setAvailableBedCount( nursingUnitType.getAvailableBedCount() + 1 );
					bedStatusAvail = true;
				}

				nursingUnitType.setLastModifiedDtm( new Date() );
				nursingUnitType.setModifiedBy(bedMaster.getModifiedBy());			
				nursingUnitTypeDAO.attachDirty( nursingUnitType );
			
				
			/* Increase the count of the bed in all related bed pool(s).
			 */
				BedPool bedPool=null;
				List<BedPoolHasUnitType> bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findBedPoolsByNursintUnitType(nursingUnitType.getUnitTypeCd());
			
				for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
				 	bedPool = dataModelManager.getBedPool(poolHasUnitType.getBedPool().getBedPoolName());
				 	bedPool.setTotalNumberOfBed(bedPool.getTotalNumberOfBed() + 1);
				 	if(bedStatusAvail){
				 		bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() + 1);
				 	}
				 	bedPoolDAO.attachDirty(bedPool);
				 }
			
			
			}
			
//		Create a bed activity record
				BedActivity bedActivity= new BedActivity();
				BedActivityId bedActivityId= new BedActivityId();
				
				if(bedStatusAvail){
					bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_AVAILABLE);
				}else{
					bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_ADDED);
				}
				bedActivityId.setBedNumber(bedMaster.getBedNumber());
				bedActivityId.setCreateDtm(new Date());
				bedActivity.setId(bedActivityId);
				bedActivity.setBedStatus(bedMaster.getBedStatus());
				bedActivity.setCreatedBy(bedMaster.getModifiedBy());
				bedActivityDAO.save(bedActivity);
				
			return dataModelConverter.convertBedMasterDM2BM( bedMaster );
				
		} catch (Exception e) {

			Fault fault = new Fault(ApplicationErrors.ADD_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	
	}

	/* (non-Javadoc)
	 * 1) Create BedEnvelope record
	 * 2) Create association between the bed envelope and the bed pools
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#addBedEnvelope(in.wtc.hcis.bm.ip.BedEnvelopeBM)
	 */
	public void addBedEnvelope( BedEnvelopeBM bedEnvelopeBM )
			throws HCISException {
		try {
			BedEnvelope bedEnvelope = dataModelConverter.convertBedEnvelopeBM2DM( bedEnvelopeBM, null );
			
			bedEnvelopeDAO.save( bedEnvelope );
			
			List<BedEnvelopePoolAsgmBM>bedEnvelopePoolAsgmList = bedEnvelopeBM.getBedEnvelopePoolAsgmList();
			
			if ( bedEnvelopePoolAsgmList != null && !bedEnvelopePoolAsgmList.isEmpty() ) {
				for ( BedEnvelopePoolAsgmBM bedEnvelopePoolAsgmBM : bedEnvelopePoolAsgmList ) {
					
					bedEnvelopePoolAsgmBM.setEnvelopeName( bedEnvelope.getEnvelopeName() );
					BedEnvelopeHasPool bedEnvelopeHasPool = dataModelConverter.convertBedEnvelopeHasPoolBM2DM( bedEnvelopePoolAsgmBM, null );
					bedEnvelopeHasPoolDAO.save( bedEnvelopeHasPool );
				}
			}
		} catch (Exception e) {

			Fault fault = new Fault(ApplicationErrors.ADD_BED_ENVELOPE_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* (non-Javadoc)
	 * 1) Create bed pool
	 * 2) Calculate the total bed count and available bed count
	 * 2) Create association between the bed pool and unit types
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#addBedPool(in.wtc.hcis.bm.ip.BedPoolBM)
	 */
	public void addBedPool( BedPoolBM bedPoolBM ) throws HCISException {
		
		try {
			BedPool bedPool = dataModelConverter.convertBedPoolBM2DM( bedPoolBM, null );
			List<BedPoolUnitTypeAsgmBM>bedPoolUnitTypeAsgmList = bedPoolBM.getBedPoolUnitTypeAsgm();
			NursingUnitType nursingUnitType = null;
			BedPoolHasUnitType bedPoolHasUnitType=null;

			Integer totalBedCount=new Integer(0);
			Integer availableBedCount=new Integer(0);

//		Calculate the bed counts of nursing units and add them consequently 

			if ( bedPoolUnitTypeAsgmList != null && !bedPoolUnitTypeAsgmList.isEmpty() ) {
  				for ( BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM : bedPoolUnitTypeAsgmList ) {
					bedPoolUnitTypeAsgmBM.setPoolName( bedPoolBM.getBedPoolName() );
					nursingUnitType = dataModelManager.getNursingUnitType( bedPoolUnitTypeAsgmBM.getUnitType().getCode() );
					totalBedCount += nursingUnitType.getTotalBedCount();
					availableBedCount += nursingUnitType.getAvailableBedCount();
				}
			}
					
			bedPool.setTotalNumberOfBed(totalBedCount);
			bedPool.setNumberOfAvailableBeds(availableBedCount);
				
			bedPoolDAO.save( bedPool );
			
			if ( bedPoolUnitTypeAsgmList != null && !bedPoolUnitTypeAsgmList.isEmpty() ) {
				for ( BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM : bedPoolUnitTypeAsgmList ) {
					bedPoolUnitTypeAsgmBM.setPoolName( bedPoolBM.getBedPoolName() );
					bedPoolHasUnitType = dataModelConverter.convertBedPoolHasUnitTypeBM2DM(bedPoolUnitTypeAsgmBM, null );
					bedPoolHasUnitTypeDAO.save( bedPoolHasUnitType );
				}
			}
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.ADD_BED_POOL_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}

	
	}

	/* (non-Javadoc)
	 * 
	 * This method returns current status of all the beds in the hospital.
	 * The beds are organised in the map in following ways
	 * 1) Map's key is the unit name
	 * 2) Value is the details of the list of beds in the unit
	 * 
	 * The system is supporting following organisation / ordering of beds
	 * 1) By Floor Number
	 * 2) By Nursing Units
	 * 
	 * If user doesn't care about the organisation then he/she can pass a value called NO_ORDERING.
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getAllBeds(in.wtc.hcis.ip.bo.bed.EnumBedOrganizationType)
	 */
	public Map<String, List<BedMasterBM>> getAllBeds(
			EnumBedOrganizationType bedOrganizationType ) throws HCISException {
		
		try {
			String orderByColumn = "";
			
			switch( bedOrganizationType ) {
				case FLOOR_NUMBER : 
					orderByColumn = BedMasterDAOExtn.ORDER_BY_FLOOR; 
					break;
					
				case NURSING_UNITS :
					orderByColumn = BedMasterDAOExtn.ORDER_BY_UNIT; 
					break;
					
				default : break;
			}
			
			List<BedMaster>bedMasterList = bedMasterDAO.getAllBeds(orderByColumn);
			
			
			if ( bedMasterList != null && !bedMasterList.isEmpty() ) {
				
				Map<String, List<BedMasterBM>>bedMasterBMMap = new HashMap<String, List<BedMasterBM>>(0);
				
				List<BedMasterBM> bedMasterBMList = null;
				
				switch( bedOrganizationType ) {
					case FLOOR_NUMBER :
						String currentFloorNbr = null ;
						
						for ( BedMaster bedMaster : bedMasterList ) {
							if ( currentFloorNbr != null && 
								 bedMaster.getFloorNbr().equals( currentFloorNbr ) ) {
								bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
							} else {
								//
								// Floor number has changed or we are here for the first time. If we are here first time
								// then currentFloorNumber will be null.
								//
								
								if ( currentFloorNbr != null ) {
									bedMasterBMMap.put( currentFloorNbr , bedMasterBMList );
								}
								
								currentFloorNbr = bedMaster.getFloorNbr();
								bedMasterBMList = new ArrayList<BedMasterBM>(); 
								bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
							}
							
						}
						
						//
						// Add last set of floor number into the map
						//
						if ( currentFloorNbr != null ) {
							bedMasterBMMap.put( currentFloorNbr , bedMasterBMList );
						}
						
						break;
						
					case NURSING_UNITS :
						String currentUnitName = null;
						
						for ( BedMaster bedMaster : bedMasterList ) {
							
							NursingUnit nursingUnit = bedMaster.getNursingUnit();
							
							if ( currentUnitName != null && 
								nursingUnit.getUnitName().equals( currentUnitName ) ) {
								
								bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
							} else {
								//
								// Floor number has changed or we are here for the first time. If we are here first time
								// then currentFloorNumber will be null.
								//
								
								if ( currentUnitName != null ) {
									bedMasterBMMap.put( currentUnitName , bedMasterBMList );
								}
								
								currentUnitName = nursingUnit.getUnitName();
								bedMasterBMList = new ArrayList<BedMasterBM>(); 
								bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
							}
						}
						
						//
						// Add last set of unit names into the map
						//
						if ( currentUnitName != null ) {
							bedMasterBMMap.put( currentUnitName , bedMasterBMList );
						}
						
						break;
						
					default : 
						bedMasterBMList = new ArrayList<BedMasterBM>();
						for ( BedMaster bedMaster : bedMasterList ) {
							bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM(bedMaster) );
						}
					
						bedMasterBMMap.put( BedManagementConstants.BED_ORGANIZATION_ORDER_TYPE_EMPTY , bedMasterBMList );
						break;
				}
				
				return bedMasterBMMap;
			}
			
			return null;
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_ALLBEDS_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* (non-Javadoc)
	 * It is possible that even though patient is admitted, he or she may not have a bed assigned yet.
	 * It is also possible that an admitted patient can have one or more beds assigned at the same time.
	 * So, this method returns all the beds booked for the given admission number.
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getBedBookingDetails(java.lang.Integer)
	 */
	public List<BedBookingDetails> getBedBookingDetails( Integer admissionNumber )
			throws HCISException {
		
		try {
			Admission admission = dataModelManager.getAdmission( admissionNumber );
			
			BedReservation tempBedReservation = new BedReservation();
			tempBedReservation.setAdmission(admission);
			List<BedReservation>bedReservationList = bedReservationDAO.findByExample( tempBedReservation );
			
			if ( bedReservationList != null && 
				!bedReservationList.isEmpty() ) {
				
				List<BedBookingDetails>bedBookingDetailsList = new ArrayList<BedBookingDetails>(0);
				
				for ( BedReservation bedReservation : bedReservationList ) {
					
					bedBookingDetailsList.add( dataModelConverter.convertBedReservationDM2BedBookingDetails( bedReservation ) );
				}
				
				return bedBookingDetailsList;
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_BOOKING_DETAILS_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getBedDetails(java.lang.String)
	 */
	public BedMasterBM getBedDetails( String bedNumber ) throws HCISException {
		try {
			BedMaster bedMaster = bedMasterDAO.findById( bedNumber );
			
			return dataModelConverter.convertBedMasterDM2BM(bedMaster);
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_BED_DETAILS_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* (non-Javadoc)
	 * This method retrieves bed master records for the given set of not-null properties.
	 * If patient name is supplied then it filters out the assigned bed separately.
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getBedDetails(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.util.Date, java.util.Date)
	 */
	public List<BedMasterBM> getBedDetails( String bedNumber, 
											String roomNumber,
											String floorNumber, 
											String nursingUnitName, 
											Integer patientId,
											String patientName, 
											Integer patientAdmissionNumber,
											String bedStatusCode, 
											Date fromDischargeDate, 
											Date toDischargeDate,
											List<String> bedFeatures,
											String orderByClause,
											String sortDir) throws HCISException {
		
		try {
			List<BedMaster>bedMasterList = bedMasterDAO.getBedMaster( bedNumber, 
																	  roomNumber,
																	  floorNumber, 
																	  nursingUnitName, 
																	  null,
																	  patientId,
																	  patientAdmissionNumber,
																	  bedStatusCode, 
																	  fromDischargeDate, 
																	  toDischargeDate,
																	  orderByClause,
																	  sortDir);
			
			if ( bedMasterList != null && !bedMasterList.isEmpty() ) {
				
				List<BedMasterBM>bedMasterBMList = new ArrayList<BedMasterBM>();
				
				for ( BedMaster bedMaster : bedMasterList ) {
					if ( patientName != null && !patientName.isEmpty()) {
						if ( bedMaster.getPatientId() != null ) {
							Patient patient = coreDataModelManager.getPatient(  bedMaster.getPatientId() );
							String[] patientNameArray = patientName.split(" " );
							boolean nameMatched = true;
							
							for ( int nameParts = 0; nameParts < patientNameArray.length; nameParts++ ) {
								if ( ( patient.getFirstName() != null  && 
									   patient.getFirstName().equals( patientNameArray[nameParts] ) ) ||
									 ( patient.getLastName() != null  && 
									   patient.getLastName().equals( patientNameArray[nameParts] ) )  ||
									 ( patient.getMiddleName() != null  && 
									   patient.getMiddleName().equals( patientNameArray[nameParts] ) ) ) {
									
									; // do nothing -- part of the name has actually matched
								} else {
									nameMatched = false;
									break;
								}
							}
							
							if ( nameMatched == true ) {
								if( bedFeatures!= null && bedFeatures.size() >0 ){
									List<String> featuresList = this.getBedFeaturesForBed ( bedMaster.getBedNumber() );
									if( featuresList.containsAll( bedFeatures ) ){
										bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
									}
								}else{
									bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
								}
								
							}
						}
					} else {
						if( bedFeatures!= null && bedFeatures.size()> 0 ){
							List<String> featuresList = this.getBedFeaturesForBed ( bedMaster.getBedNumber() );
							if( featuresList.containsAll( bedFeatures ) ){
								bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
							}
						}else{
							bedMasterBMList.add( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
						}
					}
					
				}
				
				return bedMasterBMList;
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_BEDMASTER_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}

	public ListRange findBeds(String bedNumber, String roomNumber,
			String floorNumber, String nursingUnitName, Integer patientId,
			String patientName, Integer patientAdmissionNumber,
			String bedStatusCode, Date fromDischargeDate, Date toDischargeDate,List<String> bedFeatures,
			int start, int count, String orderBy) throws HCISException {
		ListRange listRange = new ListRange();
		String[] orderByInfo;
		
		if (orderBy != null && orderBy.length() > 0) {
			orderByInfo = orderBy.split(" ");
		} else {
			orderByInfo = new String[2];
		}
		
		try {
			List<BedMasterBM> bedsList = this.getBedDetails(bedNumber, roomNumber, floorNumber, nursingUnitName, patientId, patientName, patientAdmissionNumber, bedStatusCode, fromDischargeDate, toDischargeDate, bedFeatures, orderByInfo[0], orderByInfo[1]);
			
			List<BedMasterBM> pageSizeData = new ArrayList<BedMasterBM>();
			int index = 0;
			if (bedsList != null && bedsList.size() > 0) {
			while( (start+index < start + count) && (bedsList.size() > start+index) ){
				
				BedMasterBM masterBM = bedsList.get(start+index);
				masterBM.setSeqNbr(start + index + 1);  // sequence number to be used to display on UI grid
				pageSizeData.add( masterBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(bedsList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getBedReservationDetails(java.lang.Integer)
	 */
	public BedBookingDetails getBedReservationDetails(
			Integer bedReservationNumber ) throws HCISException {
		BedReservation bedReservation = dataModelManager.getBedReservation( bedReservationNumber );
		
		BedBookingDetails bedBookingDetails = dataModelConverter.convertBedReservationDM2BedBookingDetails( bedReservation );
		
		return bedBookingDetails;
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getCurrentlyAvailableBeds(java.lang.String, java.lang.String)
	 */
	public List<BedMasterBM> getCurrentlyAvailableBeds(
			String nursingUnitTypeCd, String nursingUnitName )
			throws HCISException {
		List<BedMaster>availableBedList = bedMasterDAO.getAvailableBeds( nursingUnitTypeCd, nursingUnitName );
		
		if ( availableBedList != null && !availableBedList.isEmpty() ) {
			List<BedMasterBM>bedList = new ArrayList<BedMasterBM>(0);
			
			for ( BedMaster bedMaster : availableBedList ) {
//				bedList.add( dataModelConverter.convertBedMasterDM2BM(bedMaster) );
			}
			
			return bedList;
		} else {
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getCurrentlyOccupiedBed(java.lang.Integer, java.lang.Integer)
	 */
	
	//TODO: this method must be changed.
	//Because patient can occupy more than one bed at a time (in case of OT/ICU)
	//
	public BedMasterBM getCurrentlyOccupiedBed(Integer admissionNumber,Integer patientId ) throws HCISException {
		
		try {
			List<BedMaster> occupiedBeds = bedMasterDAO.getCurrentlyOccupiedBedBeds( admissionNumber, patientId );
			
			if ( occupiedBeds != null ) {
				
				return dataModelConverter.convertBedMasterDM2BM( occupiedBeds.get(0) ) ;
				
			} else {
				return null;
			}
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_BED_DETAILS_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	
	public ListRange findPreferredBedAvailability(Integer bedReservationNumber, int start, int count, String orderBy) {
		
		List<PreferredBedBM> preferredBedBMList = getPreferredBedAvailability(bedReservationNumber);
		ListRange listRange = new ListRange();
		
		List<PreferredBedBM> pageSizeData = new ArrayList<PreferredBedBM>();
		int index = 0;
		if (preferredBedBMList != null && preferredBedBMList.size() > 0) {
		while( (start+index < start + count) && (preferredBedBMList.size() > start+index) ){
			
			PreferredBedBM masterBM = preferredBedBMList.get(start+index);
			pageSizeData.add( masterBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(preferredBedBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;

		
	}

	/* (non-Javadoc)
	 * This method returns the possible fit for the preferred beds. 
	 * If no bed is found in the alloted Nursing unit, then it searches 
	 * for another Nursing unit within the same bed pool. Note that a nursing unit can be
	 * part of zero or more bed pools. If no bed is found in any of the pools then
	 * it searches within the sibling pools of the pools which contains desired nursing unit
	 * but in the same envelope.
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getPreferredBedAvailability(java.lang.Integer)
	 */
	
	
	public List<PreferredBedBM> getPreferredBedAvailability(
			Integer bedReservationNumber ) throws HCISException {
		
		
		try {
			List<PreferredBedBM>preferredBedBMList = null;
			
			BedReservation bedReservation = dataModelManager.getBedReservation( bedReservationNumber );
			BedReservationSpecialFeatures reservationSpecialFeatures = new BedReservationSpecialFeatures();
			
			reservationSpecialFeatures.setBedReservation( bedReservation );
			BedReservationSpecialFeaturesId featuresId = new BedReservationSpecialFeaturesId();
			featuresId.setReservationNbr( bedReservationNumber );
			reservationSpecialFeatures.setId( featuresId );
			List<BedReservationSpecialFeatures>requestedSpecialFeaturesList =
				              bedReservationSpecialFeaturesDAO.findByExample( reservationSpecialFeatures );
			
			NursingUnitType currentNursingUnitType = bedReservation.getNursingUnitType();
			List<NursingUnitType> currentNursingUnitTypeList=new ArrayList<NursingUnitType>();
			currentNursingUnitTypeList.add(currentNursingUnitType);
			preferredBedBMList= this.getPreferredBedList(requestedSpecialFeaturesList,currentNursingUnitTypeList);
			
			if(preferredBedBMList!=null && !preferredBedBMList.isEmpty()){
				return preferredBedBMList;
			}else{
					
//			Look on the Pool on which the Nursing Unit belongs to, and retrieve all units
					
					Set <NursingUnitType> unitTypeToBeCheckedList= new HashSet<NursingUnitType>(0);
//			Set<NursingUnitType> alreadyCheckedUnitsList= new HashSet< NursingUnitType>(0);
					
					Map<String, NursingUnitType> alreadyCheckedUnitsList= new HashMap<String, NursingUnitType>(0);
					Set <String> bedPoolsList=new HashSet<String>(0);
					Set <String> bedEnvelopeList;
					
					
					List<BedPoolHasUnitType> bedPoolUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", currentNursingUnitType.getUnitTypeCd());
					if(bedPoolUnitTypeList!=null && !bedPoolUnitTypeList.isEmpty()){
						
						 // it'll contain unique pool names
						
						for(BedPoolHasUnitType tmpPoolHasUnitType : bedPoolUnitTypeList){
							
//(As it is already 'Set')	if(!bedPoolsList.contains(tmpPoolHasUnitType.getBedPool().getBedPoolName()))
								bedPoolsList.add(tmpPoolHasUnitType.getBedPool().getBedPoolName());
							
						}
					}
					
					alreadyCheckedUnitsList.put(currentNursingUnitType.getUnitTypeCd(),currentNursingUnitType);
					
					for(String tmpBedPool : bedPoolsList){
							
						List<BedPoolHasUnitType> bedPoolHasUnitType = bedPoolHasUnitTypeDAO.findByProperty("id.poolName", tmpBedPool);
						for(BedPoolHasUnitType tmpPoolHasUnitType : bedPoolHasUnitType){
							
						//If unit type is already checked; don't add it to check list						
								if(!alreadyCheckedUnitsList.containsKey(tmpPoolHasUnitType.getNursingUnitType().getUnitTypeCd()))
								{
									unitTypeToBeCheckedList.add(tmpPoolHasUnitType.getNursingUnitType());
								}
						}
					}
					
					preferredBedBMList=this.getPreferredBedList(requestedSpecialFeaturesList, new ArrayList<NursingUnitType>(unitTypeToBeCheckedList));	
					if(preferredBedBMList!=null && !preferredBedBMList.isEmpty()){
					return preferredBedBMList;
					}else{
					
						unitTypeToBeCheckedList.clear();    /*  Empty the toBeChecked list, to avoid re-checking*/
						
						for(NursingUnitType unitType : unitTypeToBeCheckedList){
							alreadyCheckedUnitsList.put(unitType.getUnitTypeCd(), unitType);
						}
						
						bedEnvelopeList = new HashSet<String>(0);
						List<BedEnvelopeHasPool> bedEnvelopeHasPoolList;
						for(String bedPoolName : bedPoolsList){
							
							bedEnvelopeHasPoolList = bedEnvelopeHasPoolDAO.findByProperty("bedPool.bedPoolName", bedPoolName);
							if(bedEnvelopeHasPoolList!=null && !bedEnvelopeHasPoolList.isEmpty()){
								for(BedEnvelopeHasPool envelopeHasPool : bedEnvelopeHasPoolList){
									bedEnvelopeList.add(envelopeHasPool.getBedEnvelope().getEnvelopeName());
								}
						   }
						}
						for(String bedEnvelope : bedEnvelopeList){
							bedEnvelopeHasPoolList = bedEnvelopeHasPoolDAO.findByProperty("bedEnvelope.envelopeName", bedEnvelope);
							if(bedEnvelopeHasPoolList!=null && !bedEnvelopeHasPoolList.isEmpty()){
								for(BedEnvelopeHasPool envelopeHasPool : bedEnvelopeHasPoolList){
									bedPoolsList.add(envelopeHasPool.getBedPool().getBedPoolName());
								}
						   }
						}
					
						for(String tmpBedPool : bedPoolsList){
							
							List<BedPoolHasUnitType> bedPoolHasUnitType = bedPoolHasUnitTypeDAO.findByProperty("id.poolName", tmpBedPool);
							for(BedPoolHasUnitType tmpPoolHasUnitType : bedPoolHasUnitType){
								
							//If unit type is already checked; don't add it to check list						
									if(!alreadyCheckedUnitsList.containsKey(tmpPoolHasUnitType.getNursingUnitType().getUnitTypeCd()))
									{
										unitTypeToBeCheckedList.add(tmpPoolHasUnitType.getNursingUnitType());
									}
							}
						}
						preferredBedBMList=this.getPreferredBedList(requestedSpecialFeaturesList, new ArrayList<NursingUnitType>(unitTypeToBeCheckedList));
						return preferredBedBMList;
				 }
			}
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_BED_DETAILS_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}

		
	}
	
	
	
	/**
	 * This method returns the possible fit for the preferred beds. 
	 * For a given bed this method returns list of PreferredBedBM which internally contains 
	 * BedSpecialFeatureAvailability object. The BedSpecialFeatureAvailability object has a 
	 * mandatoryFlag ( Boolean Object ) attribute to indicate whether a feature was mandatory or 
	 * optional. If this mandatoryFlag object is null then it is assumed that the special 
	 * feature was not desired during the bed reservation. However, since the feature is anyway 
	 * available with the bed, it is good to show them along with the desired features.
	 * 
	 * This method returns the List of possible fit bed for requested special features and Nursing unit(s).
	 * For given Nursing unit it retrieves all the available beds and matches the mandatory special features; if it matches then add it 
	 * to bed list.
	 * 1) Check that all for given unit type-
	 * 2) Retrieve all the special features requested during the booking
	 * 3) Filter the mandatory special features requested during booking
	 * 4) Get all the available beds
	 * 5) For all the available beds, get all the features in that bed
	 * 		-- If all the mandatory features exist then add this bed into the preferred bed list
	 * 		-- While adding, also keep count of how many optional preferences are also matching
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#getPreferredBedList()
	 * 
	 * @param requestedSpecialFeaturesList
	 * @param unitTypeList
	 * @return getPreferredBedList
	 */
	
	
	
	private List<PreferredBedBM> getPreferredBedList(List<BedReservationSpecialFeatures>requestedSpecialFeaturesList,List<NursingUnitType> unitTypeList){
		
		List<PreferredBedBM>preferredBedBMList = null;
		List<BedReservationSpecialFeatures>mandatoryFeaturesList = null;
		
		if ( requestedSpecialFeaturesList != null && !requestedSpecialFeaturesList.isEmpty() ) {
			
			for ( BedReservationSpecialFeatures bedReservationSpecialFeatures : requestedSpecialFeaturesList ) {
				
				if ( bedReservationSpecialFeatures.getRequiredFlag().equals( ApplicationConstants.REQUIRED_FLAG_YES ) ) {
					if ( mandatoryFeaturesList == null ) {
						mandatoryFeaturesList = new ArrayList<BedReservationSpecialFeatures>(0);
					}
					
					mandatoryFeaturesList.add( bedReservationSpecialFeatures );
				}
			}
		}
		if(unitTypeList!=null && !unitTypeList.isEmpty()){
			for(NursingUnitType unitType : unitTypeList){
			List<BedMaster>availableBedList = bedMasterDAO.getAvailableBeds(unitType.getUnitTypeCd(),null);
			
			if ( availableBedList != null && !availableBedList.isEmpty() ) {
				
				
				for ( BedMaster bedMaster : availableBedList ) {
					boolean allRequiredFeatureAvailable = true;
					
					List<BedHasSpecialFeatures>specialFeatureOfBedList = bedHasSpecialFeaturesDAO.findByProperty( "id.bedNumber", bedMaster.getBedNumber() );
					Map<String, BedHasSpecialFeatures>specialFeatureMap = null;
					
					if ( specialFeatureOfBedList != null && !specialFeatureOfBedList.isEmpty() ) {
						specialFeatureMap = new HashMap<String, BedHasSpecialFeatures>();
						
						for ( BedHasSpecialFeatures bedHasSpecialFeatures : specialFeatureOfBedList ) {
							specialFeatureMap.put( bedHasSpecialFeatures.getId().getFeatureName() , bedHasSpecialFeatures );
						}
						
						if (mandatoryFeaturesList != null && !mandatoryFeaturesList.isEmpty()) {
						for ( BedReservationSpecialFeatures bedReservationSpecialFeatures : mandatoryFeaturesList ) {
							BedHasSpecialFeatures bedHasSpecialFeatures = specialFeatureMap.get( bedReservationSpecialFeatures.getId().getFeatureName() );
							
							if ( bedHasSpecialFeatures == null ) {
								allRequiredFeatureAvailable = false;
								break;
							}
						}
						}
					} else {
						if ( mandatoryFeaturesList != null && !mandatoryFeaturesList.isEmpty() ) {
							allRequiredFeatureAvailable = false;
						}
					}
					
					if ( allRequiredFeatureAvailable == true ) {
						//
						// This bed has all the special features matching the originally requested mandatory features
						//
						if ( preferredBedBMList == null ) {
							preferredBedBMList = new ArrayList<PreferredBedBM>(0);
						}
						
						PreferredBedBM preferredBedBM = this.getPreferredBedBM( bedMaster, specialFeatureMap, requestedSpecialFeaturesList );
						preferredBedBMList.add( preferredBedBM );
					}
					
				}
			  }
			}
		}
		return preferredBedBMList;
	}
		
	
	
	
	
	/**
	 * This method returns details about the preferred beds. Based on originally requested 
	 * features, it also determines if all the optional features are also available or not.
	 * This method assumes that all the mandatory special features have already been matched.
	 * 
	 * @param bedMaster
	 * @param specialFeatureMap
	 * @param requestedSpecialFeaturesList
	 * @return
	 */
	private PreferredBedBM  getPreferredBedBM( BedMaster bedMaster, 
			                                   Map<String,BedHasSpecialFeatures>specialFeatureMap,
			                                   List<BedReservationSpecialFeatures>requestedSpecialFeaturesList ) {
		
		PreferredBedBM preferredBedBM = new PreferredBedBM();
		
		
		
		if ( requestedSpecialFeaturesList != null && 
			!requestedSpecialFeaturesList.isEmpty() && 
			specialFeatureMap != null ) {
			
			int numberOfOptionalCriteriaMet = 0;
			int numberOfMandatoryCriteria = 0;
			
			for ( BedReservationSpecialFeatures bedReservationSpecialFeatures : requestedSpecialFeaturesList ) {
				
				if ( specialFeatureMap.get( bedReservationSpecialFeatures.getId().getFeatureName() ) != null ) {
					if ( bedReservationSpecialFeatures.getRequiredFlag().equals( ApplicationConstants.REQUIRED_FLAG_YES )  ) {
						numberOfMandatoryCriteria++;
					} else {
						numberOfOptionalCriteriaMet++;
					}
				} 
			}
			
			preferredBedBM.setNumberOfOptionalCriteriaMet(numberOfOptionalCriteriaMet);
			
			if ( ( numberOfOptionalCriteriaMet + numberOfMandatoryCriteria ) == requestedSpecialFeaturesList.size() ) {
				preferredBedBM.setAllCriteriaMet( Boolean.TRUE );
			} else {
				preferredBedBM.setAllCriteriaMet( Boolean.FALSE );
			}
			
			
		}
	
	/***
	 *  This part of code is no longer useful because we have moved "BedSpecialFeatureAvailabilityList" to BedMasterBM
	 *  and this list is being set to the BedMasterBM, while converting DM2BM.
	 * 
	 */	
/*		
		if ( specialFeatureMap != null ) {
			
			List<BedSpecialFeatureAvailability>specialFeatureAvailabilityList = new ArrayList<BedSpecialFeatureAvailability>(0);
			
			Set<String>keySets = specialFeatureMap.keySet();
			for ( String specialFeatureKey : keySets ) {
				BedHasSpecialFeatures specialFeatures = specialFeatureMap.get( specialFeatureKey );
				BedSpecialFeatureAvailability featureAvailability = new BedSpecialFeatureAvailability();
				featureAvailability.setAvailabilityFlag(Boolean.TRUE);
				featureAvailability.setDescription( specialFeatures.getBedSpecialFeature().getDescription() );
				featureAvailability.setFeatureName( specialFeatures.getBedSpecialFeature().getFeatureName() );
				specialFeatureAvailabilityList.add( featureAvailability );
			}
			
			preferredBedBM.setSpecialFeatureAvailabilityList( specialFeatureAvailabilityList );
		}
*/	
		preferredBedBM.setBedMasterBM( dataModelConverter.convertBedMasterDM2BM( bedMaster ) );
		
		return preferredBedBM;
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#modifyAllowedBookingThreshold(java.lang.String, java.lang.String, int)
	 */

	
/* Needs to be review   parameter bedTypeCd is not being use */
	
	public void modifyAllowedBookingThreshold(String unitTypeCd,
			String bedTypeCd,int newThresholdValue ) throws HCISException {
		
		
		try {
			NursingUnitType nursingUnitType= dataModelManager.getNursingUnitType(unitTypeCd);
			
			nursingUnitType.setThresholdForConfirmation(newThresholdValue);
			nursingUnitType.setLastModifiedDtm(new Date());
			
//		bedPoolUnitType.setModifiedBy(modifiedBy);
			nursingUnitTypeDAO.attachDirty(nursingUnitType);
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_BOOKING_THRESHOLD_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	

	/* (non-Javadoc)
	 * This method modifies bed details.
	 * If new bed type or unit type has changed then modify the corresponding pool information.
	 * If new bed status has changed from available to some other status or from some other status
	 * to available status then modify the availability details of the pool.
	 * 
	 * Modification on retired bed is not allowed.
	 * Making the status of bed as retired is not allowed.
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#modifyBed(in.wtc.hcis.bm.ip.BedMasterBM)
	 */
	
	/* |===============================================================================================================================|
	 * |EXISTING BED STATUS | CURRENT BED STATUS |UNIT TYPE CHANGED? |ACTION: EXISTING UNIT TYPE COUNT| ACTION: CURRNET UNIT TYPE COUNT|
	 * |===============================================================================================================================|
	 * | 	AVAILABLE       |	AVAILABLE		 |		  Y 		 |  (AVAILABLE-1)  &   (COUNT-1)  | (AVAILABLE + 1)  &  (COUNT + 1)|
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * |	AVAILABLE		|	NOT AVAILABLE    |        Y          |  (AVAILABLE-1)  &   (COUNT-1)  |			 (COUNT + 1)		   |
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * | 	AVAILABLE       |	NOT AVAILABLE 	 |		  N 		 |  		(AVAILABLE-1)	      |              ---               |
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * |	NOT AVAILABLE	|	NOT AVAILABLE    |        Y          |  	     (COUNT-1)  		  |	         (COUNT + 1)           |
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * |	NOT AVAILABLE	|	 AVAILABLE       |        Y          |           (COUNT-1)            |	AVAILABLE + 1)  & (COUNT + 1)  |
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * | 	NOT AVAILABLE   |	 AVAILABLE   	 |		  N 		 |  		 (COUNT-1) 	          | AVAILABLE + 1)  & (COUNT + 1)  |
	 * |-------------------------------------------------------------------------------------------------------------------------------|
	 * |	  -----     	|	  CREATED        |        N          |  	      ------    		  |	         (COUNT + 1)           |
	 * |-------------------------------------------------------------------------------------------------------------------------------|

	 */
	
	
	public BedMasterBM modifyBed(BedMasterBM bedMasterBM) throws HCISException {
		try {
			
			BedMaster existingBedMaster = dataModelManager.getBedMaster( bedMasterBM.getBedNumber() );
			
			BedStatus existingBedStatus = existingBedMaster.getBedStatus();
			
			if( BedManagementConstants.BED_STATUS_RETIRED.equals(existingBedStatus.getBedStatusCd())){
				throw new Exception(" Any modification on retired bed is not allowed");
			}
			
			
			
			NursingUnit existingNursingUnit = existingBedMaster.getNursingUnit();
			
			BedMaster bedMaster = dataModelConverter.convertBedMasterBM2DM(bedMasterBM, existingBedMaster);
			bedMaster.setLastModifiedDtm(new Date());
			
//			First Remove the old association "Bed Has Special Feature"
			
			List<BedHasSpecialFeatures> bedHasSpecialFeaturesList = dataModelManager.getBedHasSpecialFeaturesList(bedMasterBM.getBedNumber());
			if( bedHasSpecialFeaturesList != null && !bedHasSpecialFeaturesList.isEmpty()){
				
				for ( BedHasSpecialFeatures bedHasSpecialFeatures : bedHasSpecialFeaturesList){
					bedHasSpecialFeaturesDAO.delete(bedHasSpecialFeatures);
				}
			}
			
			bedMasterDAO.attachDirty(bedMaster);
			
//			Now create the new association "Bed Has Special Feature"
			
			List <BedSpecialFeatureAvailability> specialFeatureAvailabilityList = bedMasterBM.getSpecialFeatureAvailabilityList();
			
			if ( specialFeatureAvailabilityList != null && !specialFeatureAvailabilityList.isEmpty() ){
			
				
				
				for( BedSpecialFeatureAvailability featureAvailability : specialFeatureAvailabilityList ){
					
					BedHasSpecialFeatures bedHasSpecialFeatures = new BedHasSpecialFeatures();
					
					BedHasSpecialFeaturesId bedHasSpecialFeaturesId = new BedHasSpecialFeaturesId();
					bedHasSpecialFeaturesId.setBedNumber(bedMaster.getBedNumber());
					bedHasSpecialFeaturesId.setFeatureName(featureAvailability.getFeatureName());
					
					bedHasSpecialFeatures.setId(bedHasSpecialFeaturesId);
					bedHasSpecialFeatures.setEffectiveFromDate(new Date());

//TODO:				bedHasSpecialFeatures.setEffectiveToDate(effectiveToDate);
					 					
					bedHasSpecialFeaturesDAO.save(bedHasSpecialFeatures);
				}
				
			}
			
			boolean unitInfoNeedsToBeUpdated = true;

			if ( existingNursingUnit.getNursingUnitType().getUnitTypeCd().equals( bedMaster.getNursingUnit().getNursingUnitType().getUnitTypeCd() )  ) {

				unitInfoNeedsToBeUpdated = false;
				} 
			
			boolean bedAvailabilityStatusChanged = false;
			
			if ( ( existingBedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ^
				   bedMaster.getBedStatus().getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) ) {
				bedAvailabilityStatusChanged = true;
			}
			
			if ( unitInfoNeedsToBeUpdated == true ) {
				
				//
				// The bed was part of this pool. However, now it is going to be part of some other pool
				// hence reduce the availability count and threshold count.
				//
				// If the new status of bed is defective and old status was also defective then none of the 
				// count will change. 
				//
				// If current status is defective and new status is repaired then we will update count for
				// new unit type. For old -- it must have already been decremented when it was marked defective
				//
				// If the current status is something other than defective and new status is defective then 
				// even though unit type is changing, we should be updating count for the old unit type. We do not change
				// count for the new unit type.
				//
				
				
				NursingUnitType existingNursingUnitType = existingNursingUnit.getNursingUnitType();
				existingNursingUnitType.setTotalBedCount(existingNursingUnitType.getTotalBedCount() - 1);
//			TODO:	existingNursingUnitType.setThresholdForBooking(existingNursingUnitType.getThresholdForBooking() - 1);
				
				
				if ( existingBedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) {
					existingNursingUnitType.setAvailableBedCount( existingNursingUnitType.getAvailableBedCount() - 1 );
				}
				
				existingNursingUnitType.setLastModifiedDtm( new Date());
				existingNursingUnitType.setModifiedBy(bedMaster.getModifiedBy()); 
				nursingUnitTypeDAO.attachDirty(existingNursingUnitType);
				
				NursingUnitType newNursingUnitType = dataModelManager.getNursingUnitType(bedMaster.getNursingUnit().getNursingUnitType().getUnitTypeCd());
				
				newNursingUnitType.setTotalBedCount( newNursingUnitType.getTotalBedCount() + 1 );
//			TODO:	newNursingUnitType.setThresholdForBooking( newNursingUnitType.getThresholdForBooking() + 1 );
				
				if ( bedMaster.getBedStatus().getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) {
					newNursingUnitType.setAvailableBedCount( newNursingUnitType.getAvailableBedCount() + 1 );
				}
				
				newNursingUnitType.setLastModifiedDtm( new Date());
				newNursingUnitType.setModifiedBy(bedMaster.getModifiedBy());
				nursingUnitTypeDAO.attachDirty( newNursingUnitType );
				
//			Check whether poolInfo also needs to be changed
				List<BedPoolHasUnitType> bedPoolHasUnitTypeList=null;
				BedPool bedPool=null; 
				
			
				
				
				bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", existingNursingUnitType.getUnitTypeCd()); 
				for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
				 	bedPool = dataModelManager.getBedPool(poolHasUnitType.getBedPool().getBedPoolName());
				 	bedPool.setTotalNumberOfBed(bedPool.getTotalNumberOfBed() - 1);
				 	if ( bedMaster.getBedStatus().getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ){
				 		bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() - 1);
				 	}
				 	bedPoolDAO.attachDirty(bedPool);
				}
				
				
				bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", newNursingUnitType.getUnitTypeCd()); 
				for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
				 	bedPool = dataModelManager.getBedPool(poolHasUnitType.getBedPool().getBedPoolName());
				 	bedPool.setTotalNumberOfBed(bedPool.getTotalNumberOfBed() + 1);
				 	if ( bedMaster.getBedStatus().getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ){
				 		bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() + 1);
				 	}
				 	bedPoolDAO.attachDirty(bedPool);
				}
				
				
				
			} else if ( bedAvailabilityStatusChanged == true ) {
				//
				// Just the bed status has changed from available to non-available or from non-available to available.
				//
			
				
				
				NursingUnitType existingNursingUnitType  =existingNursingUnit.getNursingUnitType();
				
							
				if ( existingBedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) {
					existingNursingUnitType.setAvailableBedCount( existingNursingUnitType.getAvailableBedCount() - 1 );
					
				} else {
					existingNursingUnitType.setAvailableBedCount( existingNursingUnitType.getAvailableBedCount() + 1 );
					
				}
				
				existingNursingUnitType.setLastModifiedDtm( new Date());
				existingNursingUnitType.setModifiedBy(bedMaster.getModifiedBy());
				nursingUnitTypeDAO.attachDirty( existingNursingUnitType );
				
			} 
			
//		NEED TO SET ACTIVITY CODE, CORRESPONDING TO EACH BED STATUS, IT IS ASSUMED THAT EVERY BED ACTIVITY
//		STARTS WITH "BED_" AND REST PART IS SAME AS BED STATUS. (Except "ADDED" Status).
			
			BedActivity bedActivity= new BedActivity();
			BedActivityId bedActivityId= new BedActivityId();
			
			bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_PREFIX + bedMaster.getBedStatus().getBedStatusCd());
			bedActivityId.setBedNumber(bedMaster.getBedNumber());
			bedActivityId.setCreateDtm(new Date());
			bedActivity.setId(bedActivityId);
			bedActivity.setBedStatus(bedMaster.getBedStatus());
			bedActivity.setCreatedBy(bedMaster.getModifiedBy());
			
			bedActivityDAO.save(bedActivity);
			
			
			return dataModelConverter.convertBedMasterDM2BM(bedMaster);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}

	/* (non-Javadoc)
	 * 1) Modify the details of a bed envelope
	 * 2) Delete currently associated bed pools
	 * 3) Recreate association between envelope and newly supplied list of bed pools
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#modifyBedEnvelope(in.wtc.hcis.bm.ip.BedEnvelopeBM)
	 */
	public BedEnvelopeBM modifyBedEnvelope( BedEnvelopeBM bedEnvelopeBM ) throws HCISException {
		
		try {
			BedEnvelope existingBedEnvelope = dataModelManager.getBedEnvelope( bedEnvelopeBM.getEnvelopeName() );
			BedEnvelope bedEnvelope = dataModelConverter.convertBedEnvelopeBM2DM(bedEnvelopeBM, existingBedEnvelope);
			
			bedEnvelopeDAO.attachDirty( bedEnvelope );
			
			bedEnvelopeHasPoolDAO.deletePoolsOfEnvelope( bedEnvelopeBM.getEnvelopeName() );
			
			List<BedEnvelopePoolAsgmBM>poolAndEnvelopAssociationList = bedEnvelopeBM.getBedEnvelopePoolAsgmList();
			if ( poolAndEnvelopAssociationList != null && 
				 !poolAndEnvelopAssociationList.isEmpty() ) {
				
				for ( BedEnvelopePoolAsgmBM bedEnvelopePoolAsgmBM : poolAndEnvelopAssociationList ) {
					BedEnvelopeHasPool bedEnvelopeHasPool = dataModelConverter.convertBedEnvelopeHasPoolBM2DM(bedEnvelopePoolAsgmBM, null);
					bedEnvelopeHasPoolDAO.save( bedEnvelopeHasPool );
				}
			}
			
			return dataModelConverter.convertBedEnvelopeDM2BM(bedEnvelope);
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
			}
		
	}

	/* (non-Javadoc)
	 * 1) Retrieve the original bed pool
	 * 2) Convert the changed bed pool information from business model into the data model 
	 * 3) Update bed pool
	 * 4) Delete currently associated unit type and bed type details
	 * 5) Create new association with unit type and bed type details
	 * 
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#modifyBedPool(in.wtc.hcis.bm.ip.BedPoolBM)
	 */
	public BedPoolBM  modifyBedPool( BedPoolBM bedPoolBM ) throws HCISException {
		
		
		try {
			BedPool existingBedPool = dataModelManager.getBedPool( bedPoolBM.getBedPoolName() );
			BedPool bedPool = dataModelConverter.convertBedPoolBM2DM( bedPoolBM, existingBedPool);
			
			List<BedPoolUnitTypeAsgmBM>bedPoolUnitTypeAsgmList = bedPoolBM.getBedPoolUnitTypeAsgm();
			NursingUnitType nursingUnitType = null;
			BedPoolHasUnitType bedPoolHasUnitType=null;

			Integer totalBedCount=new Integer(0);
			Integer availableBedCount=new Integer(0);

//		Calculate the bed counts of nursing units and add then consequently 

			if ( bedPoolUnitTypeAsgmList != null && !bedPoolUnitTypeAsgmList.isEmpty() ) {
				for ( BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM : bedPoolUnitTypeAsgmList ) {
					bedPoolUnitTypeAsgmBM.setPoolName( bedPoolBM.getBedPoolName() );
					nursingUnitType = dataModelManager.getNursingUnitType( bedPoolUnitTypeAsgmBM.getUnitType().getCode() );
					totalBedCount += nursingUnitType.getTotalBedCount();
					availableBedCount += nursingUnitType.getAvailableBedCount();
				}
			}
					
			bedPool.setTotalNumberOfBed(totalBedCount);
			bedPool.setNumberOfAvailableBeds(availableBedCount);
				
			
			
			bedPoolDAO.attachDirty( bedPool );
			
			bedPoolHasUnitTypeDAO.deleteUnitTypesAssociatedWithPool( bedPoolBM.getBedPoolName() );
			
			List<BedPoolUnitTypeAsgmBM>poolUnitTypeAssociationList = bedPoolBM.getBedPoolUnitTypeAsgm();

			
			if ( bedPoolUnitTypeAsgmList != null && !bedPoolUnitTypeAsgmList.isEmpty() ) {
				for ( BedPoolUnitTypeAsgmBM bedPoolUnitTypeAsgmBM : bedPoolUnitTypeAsgmList ) {
					bedPoolUnitTypeAsgmBM.setPoolName( bedPoolBM.getBedPoolName() );
					bedPoolHasUnitType = dataModelConverter.convertBedPoolHasUnitTypeBM2DM(bedPoolUnitTypeAsgmBM, null );
					bedPoolHasUnitTypeDAO.save( bedPoolHasUnitType );
				}
			}

			
			
			
			return dataModelConverter.convertBedPoolDM2BM(bedPool);
		
		}
		catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_POOL_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#removeBed(java.util.List)
	 */
	public boolean removeBed(List<String> bedNumberList )
			throws HCISException {
		try {
			if ( bedNumberList != null && !bedNumberList.isEmpty() ) {
				
				for ( String bedNumber : bedNumberList ) {
					this.removeBed( bedNumber );
				}
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.REMOVE_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	/**
	 * This method checks that the bed being deleted, actually exist in the database. Also, a delete call 
	 * will be successful only when it doesn't have any child record.
	 * 
	 * Adding any bed also increases the bed pool count for corresponding nursing unit type and bed type.
	 * Hence, we must decrease the bed count by one. Additionally, if the bed being removed is in available
	 * status then we must decrease the available bed count by one.
	 * 
	 * 
	 * @param bedNumber
	 * @throws HCISException
	 */
	
	/* This method removes the bed from database only if there is single record (possibly activity type 'ADDED'/'AVAILABLE')
	 * in bed activity table .In this case it is assumed that bed has never been used (Perhaps added accidently).
	 * In this case it also removes the activity record and "bed has special feature" association from database. 
	 * 			OR
	 * If bed has many activity records, then  just change its status to RETIRED and reduces the count accordingly.
	 * 
	 */

	
	
	private void removeBed( String bedNumber ) throws HCISException {
		
		try {
			BedMaster bedMaster = dataModelManager.getBedMaster( bedNumber );
			
			BedStatus oldBedStatus = bedMaster.getBedStatus();
			
			List<BedActivity> bedActivityList= bedActivityDAO.findByProperty("id.bedNumber", bedMaster.getBedNumber());			
			
			if(bedActivityList!=null && !bedActivityList.isEmpty()){
//				Assuming that bed has just added so it has only 1 record 
				if(bedActivityList.size()==1){
					
//				Delete the association "Bed has special feature"
					
					List<BedHasSpecialFeatures> bedHasSpecialFeaturesList = dataModelManager.getBedHasSpecialFeaturesList(bedNumber);
					if( bedHasSpecialFeaturesList != null && !bedHasSpecialFeaturesList.isEmpty()){
						
						for ( BedHasSpecialFeatures bedHasSpecialFeatures : bedHasSpecialFeaturesList){
							bedHasSpecialFeaturesDAO.delete(bedHasSpecialFeatures);
						}
					}
					
					bedActivityDAO.delete(bedActivityList.get(0));
					bedMasterDAO.delete(bedMaster);
				}
				else{
					
					bedMaster.setBedStatus(dataModelManager.getBedStatus(BedManagementConstants.BED_STATUS_RETIRED));
	//				bedMaster.setModifiedBy(modifiedBy); TODO : figure out a way to read the user name
					bedMaster.setLastModifiedDtm(new Date());
					bedMasterDAO.attachDirty(bedMaster);
					
					/*
					 * Create a activity record
					 */				
					BedActivity bedActivity= new BedActivity();
					BedActivityId bedActivityId= new BedActivityId();
					bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_RETIRED);
					bedActivityId.setBedNumber(bedMaster.getBedNumber());
					bedActivityId.setCreateDtm(new Date());
					bedActivity.setId(bedActivityId);
					bedActivity.setBedStatus(bedMaster.getBedStatus());
	//				bedActivity.setCreatedBy(bedMaster.getModifiedBy()); TODO : figure out a way to read the user name
					
					bedActivityDAO.save(bedActivity);
					
				
				}
			
			}
		
		
			NursingUnit nursingUnit = dataModelManager.getNursingUnit(bedMaster.getNursingUnit().getUnitName());
			
			if ( nursingUnit != null ) {
				NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType(nursingUnit.getNursingUnitType().getUnitTypeCd()); 
				
				
				nursingUnitType.setTotalBedCount( nursingUnitType.getTotalBedCount() - 1 );
				
				if ( oldBedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE ) ) {
					nursingUnitType.setAvailableBedCount( nursingUnitType.getAvailableBedCount() - 1 );
				}

				nursingUnitType.setLastModifiedDtm( new Date() );
//				nursingUnitType.setModifiedBy(modifiedBy); TODO : figure out a way to read the user name
				
				nursingUnitTypeDAO.attachDirty( nursingUnitType );
/*
 * Reduce the total bed count from appropriate bed pool(s).
 */				
				BedPool bedPool=null;
				List<BedPoolHasUnitType> bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", nursingUnitType.getUnitTypeCd());
			
				for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
				 	bedPool = dataModelManager.getBedPool(poolHasUnitType.getBedPool().getBedPoolName());
				 	bedPool.setTotalNumberOfBed(bedPool.getTotalNumberOfBed() - 1);
				 	if(oldBedStatus.getBedStatusCd().equals( BedManagementConstants.BED_STATUS_AVAILABLE )){
				 		bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() - 1);
				 	}
				 	bedPoolDAO.attachDirty(bedPool);
				 }
			}
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_BED_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
		
	}
	
	
	/**
	 * Removes list of bed envelope
	 */
	public boolean removeBedEnvelope( List<String> bedEnvelopeNameList )
		throws HCISException {
		try {
			if ( bedEnvelopeNameList != null ) {
				
				for ( String  bedEnvelopeName : bedEnvelopeNameList ) {
					this.removeBedEnvelope(bedEnvelopeName);
				}
				
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.IP_REMOVE_HOSPITAL_BED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	
	/**
	 * This method removes one bed envelope at a time
	 * @param bedEnvelopeName
	 * @throws HCISException
	 */
	private void removeBedEnvelope( String bedEnvelopeName )
			throws HCISException {
		try {
			BedEnvelope bedEnvelope = dataModelManager.getBedEnvelope( bedEnvelopeName );
			bedEnvelopeHasPoolDAO.deletePoolsOfEnvelope( bedEnvelopeName ); 
			bedEnvelopeDAO.delete( bedEnvelope );
			
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.IP_REMOVE_BED_ENVELOPE );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.bed.BedManager#removeBedPool(java.util.List)
	 */
	public boolean removeBedPool(List<String> bedPoolNameList)
			throws HCISException {
		try {
			if ( bedPoolNameList != null ) {
				for ( String bedPoolName : bedPoolNameList ) {
					BedPool bedPool = dataModelManager.getBedPool( bedPoolName );
					bedPoolHasUnitTypeDAO.deleteUnitTypesAssociatedWithPool( bedPoolName );
					bedPoolDAO.delete( bedPool );
					
				}
				
				return true;
			} else {
				
				return false;
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.IP_REMOVE_BED_POOL );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	
	/**
	 * This method assigns bed for given admissionRequest number and bed number.
	 *  Additionally if the bed is being assigned against Bed Reservation this method
	 *  also modified the bed reservation record and creates bed reservation activity.  
	 *  
	 *  Based on the parameter "allowMultipleAssignment" multiple beds will be assigned,if this parameter said to false then
	 *  multiple bed assigning is not possible to the admissionRequest number then exception will be raised.
	 */
	
	public BedMasterBM assignBed( String bedNumber,Integer admissionReqNumber,Integer bedReservationNumber, String createdBy, boolean allowMultipleAssignment) throws HCISException{
		
			try {
					/*
					 * Code to check whether the bed exist for patient or not
					 */
					BedMasterBM bedMasterBM = this.getCurrentlyOccupiedBed(admissionReqNumber,null );
					if(bedMasterBM!=null && !allowMultipleAssignment){
						
						throw new Exception("Bed already assigned for this Patient.");
					}
					
					BedMaster bedMaster = dataModelManager.getBedMaster(bedNumber);
					
					if( !BedManagementConstants.BED_STATUS_AVAILABLE.equals(bedMaster.getBedStatus().getBedStatusCd()) ){
						throw new Exception("Bed (No. " + bedMaster.getBedNumber()+ ") is not Available for assignement");
					}
					
					//
					//Make sure patient is admitted 
					
					Admission admission = dataModelManager.getAdmission(admissionReqNumber);
					
					if( !DoctorOrderConstants.ADMISSION_STATUS_ADMITTED.equals(admission.getAdmissionStatus().getAdmissionStatusCd())){
						
						throw new Exception(" Admission must be in "+DoctorOrderConstants.ADMISSION_STATUS_ADMITTED+"status");
					}
					
					BedStatus bedStatus = dataModelManager.getBedStatus(BedManagementConstants.BED_STATUS_OCCUPIED);
					//
					//Update the bed availability count for corresponding unity types and related pools for that unit type
					
					this.updateUnitAndPoolBedAvailabilityCount(bedMaster, BedManagementConstants.BED_STATUS_OCCUPIED);
					
					Calendar today = Calendar.getInstance();
					
					bedMaster.setAdmission(admission);
					bedMaster.setPatientId(admission.getPatientId());
					bedMaster.setDoctorId(admission.getDoctorId());
					bedMaster.setAdmissionDtm(admission.getAdmissionDt());
					bedMaster.setBedStatus(bedStatus);
					bedMasterDAO.attachDirty(bedMaster);
					
					if(bedReservationNumber!=null){
						
						BedReservation bedReservation = dataModelManager.getBedReservation(bedReservationNumber);
						
						ReservationStatus reservationStatus = bedReservation.getReservationStatus();
						if( reservationStatus != null && !reservationStatus.getReservationStatusCd().equalsIgnoreCase(
														  BedManagementConstants.RESERVATION_STATUS_CONFIRMED)){
							throw new Exception("Bed booking status is not CONFIRMED !");
						}
						
						bedReservation.setAdmission(admission);
						bedReservation.setBedMaster(bedMaster);
						bedReservation.setReservationStatus( dataModelManager.getReservationStatus( BedManagementConstants.RESERVATION_STATUS_ASSIGNED));
						bedReservation.setPatientId(admission.getPatientId());
						bedReservation.setLastModifiedTime( today.getTime() );
						
						bedReservationDAO.attachDirty(bedReservation);
					  //Create bed reservation activity
						this.createBedReservaionActivity( bedReservation, "Bed is Assigned.", createdBy ); //TODO : createdBy
					}
					
		//		Create bed activity
					
					BedActivity bedActivity = new BedActivity();
					BedActivityId bedActivityId = new BedActivityId();
					
					bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_OCCUPIED);
					bedActivityId.setBedNumber(bedNumber);
					bedActivityId.setCreateDtm( today.getTime() );
					
					bedActivity.setId(bedActivityId);
					bedActivity.setCreatedBy(createdBy); 
					bedActivity.setBedStatus(bedStatus);
					
					bedActivityDAO.save(bedActivity);
					
		//			Create bed usage history
					
					BedUsageHistory bedUsageHistory = new BedUsageHistory();
					
					bedUsageHistory.setBedMaster(bedMaster);
					bedUsageHistory.setAdmission(admission);
					bedUsageHistory.setCheckInDtm( today.getTime() );//current date and time
					bedUsageHistory.setCreatedBy(createdBy);
					bedUsageHistory.setCreateDtm(today.getTime());
					bedUsageHistoryDAO.save(bedUsageHistory);
					
				return   dataModelConverter.convertBedMasterDM2BM(bedMaster);
			} catch (Exception e) {
				Fault fault = new Fault(ApplicationErrors.ASSIGN_BED_FAILED);
				HCISException hcisException = new HCISException(fault, e);
				throw hcisException;
			}
					
		 
	 }
	
	/**
	 * 
	 * This method release patient form given bed number.
	 * If new status of bed is given as parameter( CLEANING, PENDING_CLEANING  etc.) then
	 * it sets default status of bed as 'AVAILABLE'.
	 *  If no patient is currently occupying the bed then this method 
	 * throws exception.
	 * In case of successful operation, method creates usage history and bed activity also.   
	 * 
	 * @param bedNumber -- Bed Number from which the patient has to release
	 * @param releasedBy-- System User name
	 * @param bedNewStatus-- New bed status.('AVAILABLE' if null or Empty)
	 * @return
	 * @throws HCISException
	 */
	
	public BedMasterBM releaseBed( String bedNumber,String bedNewStatus, String releasedBy) throws HCISException{
		try {
			BedMaster bedMaster = dataModelManager.getBedMaster(bedNumber);
			BedStatus bedStatus;
			
			if( DAUtil.isValid(bedNewStatus)){
				bedStatus = dataModelManager.getBedStatus(bedNewStatus.trim());
			}else{
				bedStatus = dataModelManager.getBedStatus(BedManagementConstants.BED_STATUS_AVAILABLE);
			}
			
			//
			//Update unit type and bed pool's bed availability count
			
			this.updateUnitAndPoolBedAvailabilityCount(bedMaster, bedStatus.getBedStatusCd());
			
			Admission admission = bedMaster.getAdmission();
			
			Calendar today = Calendar.getInstance();
			
			bedMaster.setAdmission(null);
			bedMaster.setPatientId(null);
			bedMaster.setDoctorId(null);
			bedMaster.setAdmissionDtm(null);
			bedMaster.setBedStatus(bedStatus);
			bedMasterDAO.attachDirty(bedMaster);
			
			
//		Create bed activity
			
			BedActivity bedActivity = new BedActivity();
			BedActivityId bedActivityId = new BedActivityId();
			
			bedActivityId.setActivityTypeCd(BedManagementConstants.BED_ACTIVITY_PREFIX + bedStatus.getBedStatusCd());
			bedActivityId.setBedNumber(bedNumber);
			bedActivityId.setCreateDtm( today.getTime() );
			
			bedActivity.setId(bedActivityId);
			bedActivity.setCreatedBy(releasedBy); 
			bedActivity.setBedStatus(bedStatus);
			
			bedActivityDAO.save(bedActivity);
			
//			Modify bed usage history
			
			BedUsageHistory bedUsageHistory = bedUsageHistoryDAO.getUnclosedHistory(bedNumber, admission.getAdmissionReqNbr());
			if(bedUsageHistory != null ){
				bedUsageHistory.setCheckOutDtm(today.getTime());
				
			}
		return   dataModelConverter.convertBedMasterDM2BM(bedMaster);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.RELEASE_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
				
	 }
		
	/**This method first extract the admission request number from current assigned  bed then
	 * assign patient to new bed. 
	 * 
	 * @param oldBedNumber
	 * @param newBedNumber
	 * @param transferredBy
	 */
	public BedMasterBM transferPatientToNewBed(String currentBedNumber,String newBedNumber, String transferredBy){
		
		try {
			Validate.notEmpty(currentBedNumber, "Current bed number should not be Empty! ");
			Validate.notEmpty(newBedNumber, "New bed number should not be Empty!");
			
			BedMaster currentBed = dataModelManager.getBedMaster(currentBedNumber);
			Admission admission = currentBed.getAdmission();
			
			Validate.notNull(admission, "Current bed is not assigned to any patient");
			
			//First release old bed
			this.releaseBed(currentBedNumber, "", transferredBy);
			
			//Now assign new bed
			return this.assignBed(newBedNumber, admission.getAdmissionReqNbr(), null, transferredBy, false);
		
		} catch ( Exception e) {
			Fault fault = new Fault(ApplicationErrors.TRANSFER_TO_NEW_BED_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	/**
     * This method returns BedSpecialFeatureAvailability BM object containing all the bed special features
     * available in the database, it also sets the availability flag based on that the feature is effective
     * at current date or not.   
     *   
     * 
     * @param none 
     * @return List<BedSpecialFeatureAvailability>
     * @throws HCISException
     */
	
	
	
	public List<BedSpecialFeatureAvailability> getAllBedSpecialFeatures() throws HCISException {
		
		
		List<BedSpecialFeatureAvailability> bedSpecialFeatureAvailabilityList = new ArrayList<BedSpecialFeatureAvailability>();
		
		List<BedSpecialFeature> bedSpecialFeatureList = bedSpecialFeatureDAO.findAll();
		
		if( bedSpecialFeatureList != null && bedSpecialFeatureList.isEmpty()){
			
			BedSpecialFeatureAvailability bedSpecialFeatureAvailability = new BedSpecialFeatureAvailability();
			
			for( BedSpecialFeature bedSpecialFeature : bedSpecialFeatureList){
				bedSpecialFeatureAvailability.setFeatureName(bedSpecialFeature.getFeatureName());
				bedSpecialFeatureAvailability.setFeatureName(bedSpecialFeature.getDescription());
				

				if( new Date().after(bedSpecialFeature.getEffectiveFromDtm()) && new Date().before(bedSpecialFeature.getEffectiveToDtm())){
					bedSpecialFeatureAvailability.setAvailabilityFlag(Boolean.TRUE);
				}else{
					bedSpecialFeatureAvailability.setAvailabilityFlag(Boolean.FALSE);
				}
			
				bedSpecialFeatureAvailabilityList.add(bedSpecialFeatureAvailability);
			}
		}
		
		
		return bedSpecialFeatureAvailabilityList;
	
	}
	public List<BedPoolBM> getBedPools(String poolName, String nursingUnitTypeCd,
			Date effectiveFrom, Date effectiveTo) throws HCISException {
		List<BedPoolBM> bedPoolList = new ArrayList<BedPoolBM>(0);
		
		List<BedPool> bedPoolDMList = bedPoolDAO.findBedPools(poolName, nursingUnitTypeCd, effectiveFrom, effectiveTo);
		if (bedPoolDMList != null && bedPoolDMList.size() > 0) {
			
			for(BedPool tmpBedPool : bedPoolDMList) {
				BedPoolBM tmpBedPoolBM = dataModelConverter.convertBedPoolDM2BM(tmpBedPool);
				
				if (tmpBedPool.getBedPoolHasUnitTypes() != null && tmpBedPool.getBedPoolHasUnitTypes().size() > 0) {
					List<BedPoolUnitTypeAsgmBM> bedAsgmList = new ArrayList<BedPoolUnitTypeAsgmBM>(0);
					Iterator iter = tmpBedPool.getBedPoolHasUnitTypes().iterator();
					while (iter.hasNext()) {
						BedPoolUnitTypeAsgmBM tmpAsgm = dataModelConverter.convertBedPoolHasUnitTypeDM2BM((BedPoolHasUnitType)iter.next());
						bedAsgmList.add(tmpAsgm);
					}
					
					tmpBedPoolBM.setBedPoolUnitTypeAsgm(bedAsgmList);
				}
				
				bedPoolList.add(tmpBedPoolBM);
			}
		}
		
		return bedPoolList;
	}


	public ListRange findBedPools(String poolName, String nursingUnitTypeCd,
			Date effectiveFrom, Date effectiveTo, int start,
			int count, String orderBy) throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<BedPoolBM>  retBedPoolsList  = this.getBedPools(poolName, nursingUnitTypeCd, effectiveFrom, effectiveTo);

				if (retBedPoolsList != null && retBedPoolsList.size() > 0) {
					if (retBedPoolsList.size() < (start + count))
						count = retBedPoolsList.size() - start;
					
					listRange.setTotalSize(retBedPoolsList.size());
					retBedPoolsList = retBedPoolsList.subList(start, start + count);
					listRange.setData(retBedPoolsList.toArray());
				}else{
					listRange.setData(new Object[0]);
					listRange.setTotalSize(0);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

	public ListRange findBedEnvelopes(String envelopeName,
			String facilityTypeInd, String poolName, Date effectiveFrom,
			Date effectiveTo, int start, int count, String orderBy)
			throws HCISException {
		ListRange listRange = new ListRange();
		try {
			List<BedEnvelopeBM> retBedEnvelopesList = this.getBedEnvelopes(envelopeName, facilityTypeInd, poolName, effectiveFrom, effectiveTo);

			
				if (retBedEnvelopesList != null && retBedEnvelopesList.size() > 0) {
					if (retBedEnvelopesList.size() < (start + count))
						count = retBedEnvelopesList.size() - start;
					
					listRange.setTotalSize(retBedEnvelopesList.size());
					retBedEnvelopesList = retBedEnvelopesList.subList(start, start + count);
					listRange.setData(retBedEnvelopesList.toArray());
			}else{
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

	public List<BedEnvelopeBM> getBedEnvelopes(String envelopeName,
			String facilityTypeInd, String poolName, Date effectiveFrom,
			Date effectiveTo) throws HCISException {

		List<BedEnvelopeBM> bedEnvelopeList = new ArrayList<BedEnvelopeBM>(0);
		
		List<BedEnvelope> bedEnvelopeDMList = bedEnvelopeDAO.findBedEnvelopes(envelopeName, facilityTypeInd, poolName, effectiveFrom, effectiveTo);
		if (bedEnvelopeDMList != null && bedEnvelopeDMList.size() > 0) {
			
			for(BedEnvelope tmpBedEnvelope : bedEnvelopeDMList) {
				BedEnvelopeBM tmpBedEnvelopeBM = dataModelConverter.convertBedEnvelopeDM2BM(tmpBedEnvelope);
				
				if (tmpBedEnvelope.getBedEnvelopeHasPools() != null && tmpBedEnvelope.getBedEnvelopeHasPools().size() > 0) {
					List<BedEnvelopePoolAsgmBM> bedAsgmList = new ArrayList<BedEnvelopePoolAsgmBM>(0);
					Iterator iter = tmpBedEnvelope.getBedEnvelopeHasPools().iterator();
					while (iter.hasNext()) {
						BedEnvelopePoolAsgmBM tmpAsgm = dataModelConverter.convertBedEnvelopeHasPoolDM2BM((BedEnvelopeHasPool)iter.next());
						bedAsgmList.add(tmpAsgm);
					}
					
					tmpBedEnvelopeBM.setBedEnvelopePoolAsgmList(bedAsgmList);
				}
				
				bedEnvelopeList.add(tmpBedEnvelopeBM);
			}
		}
		
		return bedEnvelopeList;
		}

/** 
 * This method checks availability of preferred bed and return the status accordingly. 
 *          |			|		
 *          EDA		   EDD
 *          |-----------|			
 *    ------|			|
 *          |			|------
 *    ------|-----------|------
 *    		| ------    |   
 * We need to ensure that the number of reservations falling between the channel is less than the configured threshold.
 *       
 * @param bedReservationBM
 * @return
 */			
	public CodeAndDescription checkBedAvaibilityForBooking( BedReservationBM bedReservationBM ){
		Calendar cal = Calendar.getInstance();
		cal.setTime(bedReservationBM.getReservationFromDtm());
		cal.clear( Calendar.MINUTE);
		
		if( bedReservationBM.getReservationFromDtm() == null ||
			bedReservationBM.getReservationToDtm() == null ||
			( bedReservationBM.getReservationFromDtm().before(cal.getTime()))||
			bedReservationBM.getReservationToDtm().before( bedReservationBM.getReservationFromDtm() ) ){
			
			throw new HCISException("Invalid Date or Date Range!", "", "" );
		}
		
		
		
		String avaibilityStatus = BedManagementConstants.RESERVATION_STATUS_REGRET;
		boolean isRegret = false;
		
		if( bedReservationBM.getUnitType() != null && bedReservationBM.getUnitType().getCode() != null ){
			
			List<BedMaster>occupiedBedsList = bedMasterDAO.getOccupiedBeds( bedReservationBM.getUnitType().getCode(), 
																			bedReservationBM.getReservationFromDtm(), 
																			bedReservationBM.getReservationToDtm() );
			
			
			int occupiedBedCount = 0;
			
			if ( occupiedBedsList != null && !occupiedBedsList.isEmpty() ) {
				occupiedBedCount = occupiedBedsList.size();
			}
			
			NursingUnitType unitType = dataModelManager.getNursingUnitType( bedReservationBM.getUnitType().getCode());
			
//			first check for "CONFIRMED" reservation status 
			Integer thresholdForConfirmation = unitType.getThresholdForConfirmation();
			
			if( thresholdForConfirmation == null ){
//				Then assume that threshold is not applied here
				thresholdForConfirmation = Integer.MAX_VALUE;
			}
			
			List<BedReservation> bedReservationList = bedReservationDAO.getBedReservationDetails( unitType.getUnitTypeCd(),
					                                                                              BedManagementConstants.RESERVATION_STATUS_CONFIRMED,
																								  bedReservationBM.getReservationFromDtm(),
																								  bedReservationBM.getReservationToDtm() );
			if( ( bedReservationList != null ) && 
				( ( bedReservationList.size() + occupiedBedCount ) > thresholdForConfirmation ) ) {
				
				//
				// We have already reached the confirmed booking threshold. 
				// Now check if we can promise for "WAITLIST" reservation
				//
				
				Integer thresholdForWaitlist = unitType.getThresholdForWaitlist();
				
				if( thresholdForWaitlist == null ){
//					Then assume that threshold is not applied here
					thresholdForWaitlist = Integer.MAX_VALUE;
				}
				
			
				bedReservationList = bedReservationDAO.getBedReservationDetails( unitType.getUnitTypeCd(),
														                         BedManagementConstants.RESERVATION_STATUS_WAITLIST,
																				 bedReservationBM.getReservationFromDtm(),
																				 bedReservationBM.getReservationToDtm() );
				if( bedReservationList.size() > thresholdForWaitlist ){
					isRegret = true;
				} else {
					avaibilityStatus =  BedManagementConstants.RESERVATION_STATUS_WAITLIST;
				}
			} else {
				avaibilityStatus =  BedManagementConstants.RESERVATION_STATUS_CONFIRMED;
			}
		}
		
		if( !isRegret ){
			
			List<BedMaster> preferredBedList = this.getPreferredBedsForReservaion(bedReservationBM);
			if( preferredBedList != null && preferredBedList.size() > 0  ){
				ReservationStatus reservationStatus = dataModelManager.getReservationStatus(avaibilityStatus);
				if(reservationStatus != null ){
					return new CodeAndDescription( reservationStatus.getReservationStatusCd() , reservationStatus.getDescription());
				}
			}
		}
		
		return new CodeAndDescription(BedManagementConstants.RESERVATION_STATUS_REGRET,"Regret");
	}
	
	/**
	 * This method returns the list of possible preferred beds for given bed reservation request.
	 * 
	 * @param bedReservationBM
	 * @return
	 */
	private List<BedMaster> getPreferredBedsForReservaion( BedReservationBM bedReservationBM ){
		
		List<CodeAndDescription> requiredFacilitiesList = bedReservationBM.getRequiredFacilities();
		List<BedSpecialFeature>mandatoryFeaturesList = new ArrayList<BedSpecialFeature>(0);
		
		NursingUnitType unitType = dataModelManager.getNursingUnitType( bedReservationBM.getUnitType().getCode());
		
		if( requiredFacilitiesList != null && !requiredFacilitiesList.isEmpty() ){
			for ( CodeAndDescription requiredSpecialFeatureCode : requiredFacilitiesList ) {
				mandatoryFeaturesList.add( bedSpecialFeatureDAO.findById( requiredSpecialFeatureCode.getCode() ));
				}
			}
//		Now take into account all  Available beds and those currently occupied beds whose Expected discharge date is
//		before than 'reservationFromDtm'
			
		List<BedMaster>availableBedList = bedMasterDAO.getAvailableBeds(unitType.getUnitTypeCd(),null);
		
		List<BedMaster> occupiedbedsList = bedMasterDAO.getBedMaster(null, null,null,
																	 null, unitType.getUnitTypeCd(), null,null,
																	 BedManagementConstants.BED_STATUS_OCCUPIED,
																	 null, bedReservationBM.getReservationFromDtm(),null,null);	
		
		if( availableBedList != null && !availableBedList.isEmpty() ){
			if( occupiedbedsList != null && !occupiedbedsList.isEmpty() ){
				availableBedList.addAll( occupiedbedsList );
			}
		}else{
			if( occupiedbedsList != null && !occupiedbedsList.isEmpty() ){
				availableBedList = occupiedbedsList;
			}
		}
		
		
		List<BedMaster> preferredBedList = new ArrayList<BedMaster>(0);
		
			if ( availableBedList != null && !availableBedList.isEmpty() ) {
				for ( BedMaster bedMaster : availableBedList ) {
					boolean allRequiredFeatureAvailable = true;
					
					List<BedHasSpecialFeatures>specialFeatureOfBedList = bedHasSpecialFeaturesDAO.findByProperty( "id.bedNumber", bedMaster.getBedNumber() );
					Map<String, BedHasSpecialFeatures>specialFeatureMap = null;
					
					if ( specialFeatureOfBedList != null && !specialFeatureOfBedList.isEmpty() ) {
						specialFeatureMap = new HashMap<String, BedHasSpecialFeatures>();
						
						for ( BedHasSpecialFeatures bedHasSpecialFeatures : specialFeatureOfBedList ) {
							specialFeatureMap.put( bedHasSpecialFeatures.getId().getFeatureName().trim() , bedHasSpecialFeatures );
						}
						
						for ( BedSpecialFeature bedSpecialFeature : mandatoryFeaturesList ) {
							BedHasSpecialFeatures bedHasSpecialFeatures = specialFeatureMap.get( bedSpecialFeature.getFeatureName().trim() );

							if ( bedHasSpecialFeatures == null ) {
								allRequiredFeatureAvailable = false;
								break;
							}
						}
					} else {
						if ( mandatoryFeaturesList != null && !mandatoryFeaturesList.isEmpty() ) {
							allRequiredFeatureAvailable = false;
						}
					}
					
					if ( allRequiredFeatureAvailable == true ) {
						//
						// This bed has all the special features matching the originally requested features
						//
						preferredBedList.add( bedMaster );
					}
				}
		}
			return preferredBedList;
	}
	
	public BedReservationBM bookBed( BedReservationBM bedReservationBM ){
		CodeAndDescription avaibilityStatus = this.checkBedAvaibilityForBooking( bedReservationBM );
			
			if( avaibilityStatus ==  null || avaibilityStatus.getCode() == null ||
					avaibilityStatus.getCode().equalsIgnoreCase( BedManagementConstants.RESERVATION_STATUS_REGRET ) ){
				
				bedReservationBM.setReservationStatus( new CodeAndDescription( BedManagementConstants.RESERVATION_STATUS_REGRET, "Regret" ));
				return bedReservationBM;
			}
			
			if( bedReservationBM.getAdmissionReqNbr() == null ){
				Fault fault = new Fault(ApplicationErrors.ADDMISSION_REQ_NBR_REQUIRED);
				HCISException hcisException = new HCISException(fault);
				throw hcisException;
			}

			BedReservation bedReservation = dataModelConverter.convertBedReservationBM2DM(bedReservationBM, null);
			bedReservation.setReservationStatus(dataModelManager.getReservationStatus(avaibilityStatus.getCode()));
			bedReservation.setRequestCreationDtm( new Date() );
			Date bookingValidTillDt = new Date();
			bookingValidTillDt.setMonth( new Date().getMonth() + 3 ); //Booking is valid for next 3 months
			bedReservation.setRequestValidTill( bookingValidTillDt);//TODO: To be Changed 
			bedReservation.setRequestCreatedBy(bedReservationBM.getCreatedBy());
			bedReservationDAO.save( bedReservation );
			
//			Save the preferred bed special features also
			
			List<CodeAndDescription> requiredFacilitiesList = bedReservationBM.getRequiredFacilities();
			
			if( requiredFacilitiesList != null && !requiredFacilitiesList.isEmpty()){
				for( CodeAndDescription  requiredFacilities : requiredFacilitiesList){
					BedSpecialFeature bedSpecialFeature = dataModelManager.getBedSpecialFeature( requiredFacilities.getCode() );
					
					BedReservationSpecialFeatures reservationSpecialFeatures = new BedReservationSpecialFeatures();
					BedReservationSpecialFeaturesId reservationSpecialFeaturesId = new BedReservationSpecialFeaturesId();
					
					reservationSpecialFeaturesId.setReservationNbr( bedReservation.getBedReservationNbr() );
					reservationSpecialFeaturesId.setFeatureName( bedSpecialFeature.getFeatureName() );
					reservationSpecialFeatures.setId( reservationSpecialFeaturesId );
//					reservationSpecialFeatures.setBedReservation( bedReservation );
//					reservationSpecialFeatures.setBedSpecialFeature(bedSpecialFeature);
					reservationSpecialFeatures.setRequiredFlag( BedManagementConstants.REQUIRED_FLAG_YES );
					
					bedReservationSpecialFeaturesDAO.save( reservationSpecialFeatures );
				}
			}

			List<CodeAndDescription> desiredFacilitiesList = bedReservationBM.getDesiredFacilities();
			
			if( desiredFacilitiesList != null && !desiredFacilitiesList.isEmpty()){
				for( CodeAndDescription  desiredFacilities : desiredFacilitiesList){
					BedSpecialFeature bedSpecialFeature = dataModelManager.getBedSpecialFeature( desiredFacilities.getCode() );
					
					BedReservationSpecialFeatures reservationSpecialFeatures = new BedReservationSpecialFeatures();
					BedReservationSpecialFeaturesId reservationSpecialFeaturesId = new BedReservationSpecialFeaturesId();
					
					reservationSpecialFeaturesId.setReservationNbr( bedReservation.getBedReservationNbr() );
					reservationSpecialFeaturesId.setFeatureName( bedSpecialFeature.getFeatureName() );
					reservationSpecialFeatures.setId( reservationSpecialFeaturesId );
					reservationSpecialFeatures.setRequiredFlag(BedManagementConstants.REQUIRED_FLAG_NO );
					
					bedReservationSpecialFeaturesDAO.save( reservationSpecialFeatures );
				}
			}
			
//			Now create reservation activity
			this.createBedReservaionActivity(bedReservation, "Bed is Booked in " + avaibilityStatus.getDescription() + " Status." , bedReservationBM.getCreatedBy() );
			
			return dataModelConverter.convertBedReservationDM2BM(bedReservation);
	}
	
	/**
	 * This method creates BedResercaionActvity.
	 * It is assumed that activity type starts with prefix 'BED_RES_' and rest part is same as Reservation Status code.
	 */
	private void createBedReservaionActivity(BedReservation bedReservation , String remarks ,String createdBy ){
		
		BedReservationActivity reservationActivity = new BedReservationActivity();
		BedReservationActivityId reservationActivityId = new BedReservationActivityId();
		
		reservationActivityId.setBedReservationNbr(bedReservation.getBedReservationNbr() );
		reservationActivityId.setActivityTypeCd( BedManagementConstants.RESERVATION_ACTIVITY_PREFIX + bedReservation.getReservationStatus().getReservationStatusCd());
		reservationActivityId.setCreateDtm( new Date() );
		
		reservationActivity.setId( reservationActivityId );
		reservationActivity.setReservationStatus( bedReservation.getReservationStatus() );
		reservationActivity.setRemarks(remarks);
		reservationActivity.setCreatedBy(createdBy);
		
		bedReservationActivityDAO.save( reservationActivity );
	}
	
	public List<BedReservationBM> getBedBookings(  Integer bedReservationNbr,
												   String unitTypeCode,
												   String bedTypeCode,
												   String bedNumber,
												   String reservationStatusCd,
												   Integer admissionReqNbr,
												   Date   reservationFromDt,
												   Date   reservationToDt){
		 List<BedReservation> bedReservationList = bedReservationDAO.getBedReservationDetails(bedReservationNbr, unitTypeCode, bedTypeCode,
				 																			  bedNumber, reservationStatusCd,
				 																			  admissionReqNbr, reservationFromDt, reservationToDt);
		 List<BedReservationBM> bedReservationBMList = new ArrayList<BedReservationBM>(0);
		 
		 if( bedReservationList != null && !bedReservationList.isEmpty() ){
			 for( BedReservation bedReservation : bedReservationList ){
				 
				 BedReservationBM bedReservationBM = dataModelConverter.convertBedReservationDM2BM(bedReservation);
				 
				 List<BedReservationSpecialFeatures> bedReservationSpecialFeaturesList = bedReservationSpecialFeaturesDAO.findByProperty("id.reservationNbr", bedReservationBM.getBedReservationNbr());
				 if( bedReservationSpecialFeaturesList != null && !bedReservationSpecialFeaturesList.isEmpty() ){
					 List<CodeAndDescription> requiredFacilities = new ArrayList<CodeAndDescription>(0);
					 List<CodeAndDescription> desiredFacilities = new ArrayList<CodeAndDescription>(0);
					 
					 for( BedReservationSpecialFeatures specialFeatures : bedReservationSpecialFeaturesList ){
						 
						 if( specialFeatures.getRequiredFlag() != null &&
								 specialFeatures.getRequiredFlag().equals(BedManagementConstants.REQUIRED_FLAG_YES)) {
							 requiredFacilities.add( new CodeAndDescription( specialFeatures.getBedSpecialFeature().getFeatureName(),
									 									   specialFeatures.getBedSpecialFeature().getDescription()));
						 }else{
							 desiredFacilities.add( new CodeAndDescription( specialFeatures.getBedSpecialFeature().getFeatureName(),
									   specialFeatures.getBedSpecialFeature().getDescription()));
						 }
					 }
					 bedReservationBM.setRequiredFacilities(requiredFacilities);
					 bedReservationBM.setDesiredFacilities(desiredFacilities);
				 }
				 bedReservationBMList.add( bedReservationBM );
			 }
		 }
		 
		 return bedReservationBMList;
	}
	
	public ListRange findBedBookings(  Integer bedReservationNbr,
									   String unitTypeCode,
									   String bedTypeCode,
									   String bedNumber,
									   String reservationStatusCd,
									   Integer admissionReqNbr,
									   Date   reservationFromDt,
									   Date   reservationToDt,
									   int start, int count, String orderBy){
		
		List<BedReservationBM> bedReservationBMList = this.getBedBookings(bedReservationNbr, unitTypeCode, bedTypeCode,
																		  bedNumber, reservationStatusCd, admissionReqNbr,
																		  reservationFromDt, reservationToDt);
		ListRange listRange = new ListRange();
		
		List<BedReservationBM> pageSizeData = new ArrayList<BedReservationBM>();
		int index = 0;
		if (bedReservationBMList != null && bedReservationBMList.size() > 0) {
		while( (start+index < start + count) && (bedReservationBMList.size() > start+index) ){
			
			BedReservationBM bedReservationBM = bedReservationBMList.get(start+index);
			pageSizeData.add( bedReservationBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(bedReservationBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}	
	/**
	 * This method modified the existing bed reservation/booking record.
	 * If the reservation status is modified then it checks for validation for
	 * the status transition and proceed only if it is valid.
	 * It creates bedReservationActivity record if reservation status is modified.   
	 * @param modifiedBedReservationBM
	 * @return
	 */
	public BedReservationBM modifyBedBooking( BedReservationBM modifiedBedReservationBM ){
		
		try {
			if( modifiedBedReservationBM.getBedReservationNbr() != null ){
				BedReservation existingBedReservation = dataModelManager.getBedReservation(
						modifiedBedReservationBM.getBedReservationNbr());
				
				String oldReservationStatus = existingBedReservation.getReservationStatus().getReservationStatusCd();
				String newReservationStatus =  modifiedBedReservationBM.getReservationStatus().getCode();
				boolean reservationStatusChanged = true;
				
				if( oldReservationStatus.equalsIgnoreCase(newReservationStatus)){
					reservationStatusChanged = false;
				}
					if( reservationStatusChanged && !this.isValidStatusTransition(oldReservationStatus, newReservationStatus)) {
						
						throw new Exception("This 'Status transition' is Not Valid! ");
					}else{
						
//			Now start process of modification
						
						BedReservation bedReservation = dataModelConverter.convertBedReservationBM2DM(modifiedBedReservationBM, existingBedReservation);
						bedReservation.setModifiedBy( modifiedBedReservationBM.getCreatedBy() );
						bedReservation.setLastModifiedTime( new Date() );
						
						bedReservationDAO.attachDirty( bedReservation );
						
						
//					Modify the preferred bed special features also
						
						List<BedReservationSpecialFeatures> reservationSpecialFeaturesList = 
							bedReservationSpecialFeaturesDAO.findByProperty("id.reservationNbr", bedReservation.getBedReservationNbr());
						
						if( reservationSpecialFeaturesList != null && !reservationSpecialFeaturesList.isEmpty() ){
							for( BedReservationSpecialFeatures features : reservationSpecialFeaturesList ){
								bedReservationSpecialFeaturesDAO.delete( features );
							}
						}
							
						
						List<CodeAndDescription> requiredFacilitiesList = modifiedBedReservationBM.getRequiredFacilities();
						
						if( requiredFacilitiesList != null && !requiredFacilitiesList.isEmpty()){
							for( CodeAndDescription  requiredFacilities : requiredFacilitiesList){
								BedSpecialFeature bedSpecialFeature = dataModelManager.getBedSpecialFeature( requiredFacilities.getCode() );
								
								BedReservationSpecialFeatures reservationSpecialFeatures = new BedReservationSpecialFeatures();
								BedReservationSpecialFeaturesId reservationSpecialFeaturesId = new BedReservationSpecialFeaturesId();
								
								reservationSpecialFeaturesId.setReservationNbr( bedReservation.getBedReservationNbr() );
								reservationSpecialFeaturesId.setFeatureName( bedSpecialFeature.getFeatureName() );
								reservationSpecialFeatures.setId( reservationSpecialFeaturesId );
//							reservationSpecialFeatures.setBedReservation( bedReservation );
//							reservationSpecialFeatures.setBedSpecialFeature(bedSpecialFeature);
								reservationSpecialFeatures.setRequiredFlag( BedManagementConstants.REQUIRED_FLAG_YES );
								
								bedReservationSpecialFeaturesDAO.save( reservationSpecialFeatures );
							}
						}

						List<CodeAndDescription> desiredFacilitiesList = modifiedBedReservationBM.getDesiredFacilities();
						
						if( desiredFacilitiesList != null && !desiredFacilitiesList.isEmpty()){
							for( CodeAndDescription  desiredFacilities : desiredFacilitiesList){
								BedSpecialFeature bedSpecialFeature = dataModelManager.getBedSpecialFeature( desiredFacilities.getCode() );
								
								BedReservationSpecialFeatures reservationSpecialFeatures = new BedReservationSpecialFeatures();
								BedReservationSpecialFeaturesId reservationSpecialFeaturesId = new BedReservationSpecialFeaturesId();
								
								reservationSpecialFeaturesId.setReservationNbr( bedReservation.getBedReservationNbr() );
								reservationSpecialFeaturesId.setFeatureName( bedSpecialFeature.getFeatureName() );
								reservationSpecialFeatures.setId( reservationSpecialFeaturesId );
								reservationSpecialFeatures.setRequiredFlag(BedManagementConstants.REQUIRED_FLAG_NO );
								
								bedReservationSpecialFeaturesDAO.save( reservationSpecialFeatures );
							}
						}
						
//					Now if reservation status is changed then create reservation activity
						if(reservationStatusChanged){
							this.createBedReservaionActivity(bedReservation, "Reservation Status changed from " + oldReservationStatus + 
								" to "+ newReservationStatus  ,bedReservation.getModifiedBy()  );
					}
						return dataModelConverter.convertBedReservationDM2BM(bedReservation);
				}
			}
			
			return null;
			
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_REERVATION_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	/*
	 * This method validates the transition of reservation status.
	 * Method works fine only it there is status change so it should
	 * be called only when, there is a visible status change.
	 * 
	 * 
	 * Valid status transition is as-
	 * 
	 * ------------------------------------------------------------------------
	 *  		Old Status 		|	New Status
	 * ------------------------------------------------------------------------
	 * 		1) CONFIRMED  		|	CANCELED,ASSIGNED
	 * 		2) WAITLIST  		|	CONFIRMED, CANCELED
	 * 		3) CANCELED			|	-
	 * 		4) ASSIGNED			|	-
	 * --------------------------------------------------------------------------
	 *
	 */
	private boolean isValidStatusTransition(String oldReservationStatus, String newReservationStatus){
		
		if( oldReservationStatus.equals(BedManagementConstants.RESERVATION_STATUS_CONFIRMED ) ){
			if( newReservationStatus.equals( BedManagementConstants.RESERVATION_STATUS_ASSIGNED) ||
					newReservationStatus.equals( BedManagementConstants.RESERVATION_STATUS_CANCELED) ){
				return true;
			}
		}else if(oldReservationStatus.equals(BedManagementConstants.RESERVATION_STATUS_WAITLIST ) ){
			if( newReservationStatus.equals( BedManagementConstants.RESERVATION_STATUS_CONFIRMED) ||
					newReservationStatus.equals( BedManagementConstants.RESERVATION_STATUS_CANCELED) ){
				return true;
			}
		}
		return false;
	}
	
	/** This method CANCELS the existing booking only if it is eligible for cancellation.
	 * Creates bedReservation activity as well as.
	 * @param bedReservationNbr
	 * @return
	 */
	public boolean cancelBooking( Integer bedReservationNbr , String cancelledBy){
		try {
			if( bedReservationNbr != null ){
				BedReservation bedReservation = dataModelManager.getBedReservation(bedReservationNbr);

				if( this.isValidStatusTransition(bedReservation.getReservationStatus().getReservationStatusCd(),
						BedManagementConstants.RESERVATION_STATUS_CANCELED)){
					bedReservation.setReservationStatus( dataModelManager.getReservationStatus(BedManagementConstants.RESERVATION_STATUS_CANCELED));
					bedReservation.setModifiedBy(cancelledBy);
					bedReservation.setLastModifiedTime(new Date());
					
					this.createBedReservaionActivity(bedReservation,"Reservation Canceled", cancelledBy );
					
					return true;
				}
				
			}
			return false;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_BED_REERVATION_FAILED);
			throw new HCISException(fault);
		}
	}

/**
 * This method gives details of un-billed bed usage for given admissionReqNumber. 
 * It does not updates any record in database.  
 */
	
	public BillDataBM getUnbilledBedUsage(Integer admissionReqNbr){
		
		return this.performBilling(admissionReqNbr, null , null, false);
	}
	
	/**
	 * Method bills the bed usage for given admissionReqNumber and stamps the bill number over the
	 * billed item and finally returns the whole bill data into BillDataBM object. 
	 * 
	 */
	
	public BillDataBM billBedAssignments(Integer admissionReqNbr,
										 Integer billNumber,
										 String billedBy){
		
		return this.performBilling(admissionReqNbr, billNumber, billedBy, true);
	}
	

	/**
	 * 
	 * This method is used for both getting unbilled bed usage and to bill the bed usage;
	 * Based on the 'updateRecord' flag.
	 * If 'updateRecord' flag is set to 'true' then it marks the billable bed usage record
	 * billed; with given bill number.
	 * The flow of billing process is as follows -
	 * 
	 * 1. First determine whether the entry is going to bill for first time or 
	 * 	  already billed at least once.
	 * 2. If it is being billed for first time then-
	 * 		a. Start billing from  of check-in date/hour
	 * 		b. If   
	 * 
	 * 
	 * @param admissionReqNbr
	 * @param billNumber
	 * @param billedBy
	 * @param updateRecord
	 * @return
	 */
	
	private BillDataBM performBilling( Integer admissionReqNbr,
									   Integer billNumber,
									   String billedBy,
									   boolean updateRecord){
			
		try {
			
			Validate.notNull(admissionReqNbr, "Admission request number should not be null!");
			List<BedUsageHistory> bedUsageHistoryList = bedUsageHistoryDAO.getBillableBedUsageHistory(admissionReqNbr);
			
			Calendar today = Calendar.getInstance();
			
			// Initialize billData
			
			BillDataBM billDataBM = new BillDataBM();
			billDataBM.setProcessName( BedManagementConstants.BILLING_PROCESS_BED );
			billDataBM.setProcessTotalBillAmount(0.0);
			Map<String, BillingSubprocessBM>billDetailsMap = new HashMap<String, BillingSubprocessBM>(0);
			billDataBM.setBillDetailsMap(billDetailsMap);
			
			
			BillingSubprocessBM billingSubprocessBM = new BillingSubprocessBM();
			billingSubprocessBM.setContributeToFnclCharge( Boolean.TRUE );
			billingSubprocessBM.setRemarks("Billing for bed usage" );
			billingSubprocessBM.setSubProcessName( BedManagementConstants.BILLING_SUBPROCESS_BED);
			billingSubprocessBM.setSubProcessTotals(0.0);
			List<BillDetailsBM>billDetailsBMList = new ArrayList<BillDetailsBM>(0);
			
			//------------------------------------------
			
			if( DAUtil.isValid(bedUsageHistoryList)){
				for( BedUsageHistory bedUsageHistory : bedUsageHistoryList ){
					
					
					Calendar billFromDate = Calendar.getInstance();
					Calendar billFromHour = Calendar.getInstance();
					Calendar billToDate   = Calendar.getInstance();

					long billableDays = 0;
					long billableHours = 0;
					
					if( bedUsageHistory.getCheckOutDtm() != null ){
						
						//Bill up to check-out time
						billToDate.setTime( bedUsageHistory.getCheckOutDtm() );
					}// else : bill up to today
					 
					if( bedUsageHistory.getLastBillNbr() != null  ){
						
//						&& today.before(when)(bedUsageHistory.getLastBillDtm())
						
						//This entry is billed at least once; so start billing from next hour/day of last bill DTM
						
						billFromDate.setTime(bedUsageHistory.getLastBillDtm());
						
						Date fromDate = new Date(billFromDate.getTime().getTime());
						Date toDate = new Date( billToDate.getTime().getTime());
						fromDate = DateUtils.truncateTime(fromDate);
						toDate = DateUtils.truncateTime( toDate );
						
						if( fromDate.before(toDate)){
							billFromDate.setTimeInMillis( billFromDate.getTimeInMillis() + DAY_IN_MILLS );//billing should start form next day
						}
						
						billFromHour.setTime(bedUsageHistory.getLastBillDtm());
						billFromHour.setTimeInMillis(billFromHour.getTimeInMillis() + HOUR_IN_MILLS);//billing should start form next hour
						
						
					}else{
						
						//Bed assignment entry is going to bill for the first time
						
						billFromDate.setTime(bedUsageHistory.getCheckInDtm());
						billFromHour.setTime(bedUsageHistory.getCheckInDtm());
					}

					
					billableDays = (long)Math.ceil(( billToDate.getTimeInMillis() - billFromDate.getTimeInMillis()) / (double)DAY_IN_MILLS); 
					billableHours = (long)Math.ceil(( billToDate.getTimeInMillis() - billFromDate.getTimeInMillis()) / (double)HOUR_IN_MILLS);
					
					BedMaster currentBed = bedUsageHistory.getBedMaster();
					
					Double bedDailyCharge = currentBed.getDailyCharge();
					Double bedHourlyCharge = currentBed.getHourlyCharge();
					
					Double billAmount = 0.0;
					
					//additional billing data
					Integer billingUnitCount = 0;
						
					//------------------
					
					
					if( bedDailyCharge != null ){
						
						// TODO: From now onwards it has been assumed that charge will be either DAILY or HOURLY.
						// it can not be both.
						
//						if( bedHourlyCharge != null  ){
//							//TODO:
//							//Hourly and daily both charges are defined; so if billing needs to done for same day
//							//then apply hourly charge otherwise apply daily charge
//							//
//							if( billFromDate.DATE == billToDate.DATE ){
//								
//								billAmount = bedHourlyCharge * billableHours;
//								
//								billingUnitCount = (int)billableHours;
//							}else{
//								
//								billAmount = bedDailyCharge * billableDays;
//								
//								billingUnitCount = (int)billableDays;
//							}
//							
//						}else{
							//
							//Only Daily charges are defined for this bed, so bill the days accordingly
							
							billAmount = bedDailyCharge * billableDays;
							billingUnitCount = (int)billableDays;
//						}
					}else if( bedHourlyCharge != null ){
						//
						//Bill according to hours spent on bed
						
						billAmount = bedHourlyCharge * billableHours;
						billingUnitCount = (int)billableHours;
					}
					
					if( updateRecord){
					//Stamp bill number to the record
						
						Validate.notNull(billNumber, " Bill Number must not be null");
						
						bedUsageHistory.setLastBillNbr(billNumber);
						bedUsageHistory.setLastBillDtm(today.getTime());
						bedUsageHistoryDAO.attachDirty(bedUsageHistory);
						
					//Create bed bill history 
						
						try {
							BedBillHistory bedBillHistory = new BedBillHistory();
							BedBillHistoryId billHistoryId = new BedBillHistoryId();
							
							billHistoryId.setBedAssignmentNbr(bedUsageHistory.getBedAssignmentNbr());
							billHistoryId.setBillNbr(billNumber);
							bedBillHistory.setId( billHistoryId );
							
							bedBillHistory.setBillFromDtm( billFromDate.getTime() );
							bedBillHistory.setBillToDtm( billFromDate.getTime() );
							bedBillHistory.setCreatedBy( billedBy );
							bedBillHistory.setCreateDtm( today.getTime() );
							bedBillHistory.setTotalCharge( billAmount );
							bedBillHistory.setDailyCharge(bedDailyCharge);
							bedBillHistory.setHourlyCharge(bedHourlyCharge);
							bedBillHistory.setBilledDayUnit(billingUnitCount);
							bedBillHistory.setBilledHourUnit(billingUnitCount);
							
							bedBillHistoryDAO.save(bedBillHistory);
						} catch ( Exception e) {
							Fault fault =  new Fault(ApplicationErrors.ADD_BED_BILLING_HISTORY_FAILED);
							throw new HCISException(fault);
						}
					}
					
					BillDetailsBM billDetailsBM = new BillDetailsBM();
					billDetailsBM.setItemName( "Bed- "+currentBed.getBedNumber());
					billDetailsBM.setItemCount(billingUnitCount);
					if( billingUnitCount > 0 ){
						billDetailsBM.setItemPrice(billAmount/billingUnitCount);
					}else{
						billDetailsBM.setItemPrice(0.0);
					}
					billDetailsBM.setItemSequenceNbr(billDetailsBMList.size() + 1);
					billDetailsBM.setDiscounts(0.0);
					billDetailsBM.setNetPrice(billAmount);
					billDetailsBM.setTransactionDate(today.getTime());
					
					billDetailsBMList.add( billDetailsBM );
					billingSubprocessBM.setSubProcessTotals(billingSubprocessBM.getSubProcessTotals() 
															+ billAmount );

					//
					//Update processTotalAmount
					billDataBM.setProcessTotalBillAmount(billDataBM.getProcessTotalBillAmount() +
														billingSubprocessBM.getSubProcessTotals());
				}
				billingSubprocessBM.setBillDetailsList(billDetailsBMList);
				billDetailsMap.put(BedManagementConstants.BILLING_SUBPROCESS_BED, billingSubprocessBM);
			}
			
			return billDataBM;
		} catch (Exception e) {
			Fault fault =  new Fault(ApplicationErrors.BED_BILLING_FAILLED);
			throw new HCISException(fault);
		}
	}
	
	
	/**
	 * This method increases or decreases the bed availability count for belonging
	 * nursing unit type and bed pools . 
	 * In order to do this it checks current bed status and new bed status.
	 **/
	private void updateUnitAndPoolBedAvailabilityCount(BedMaster bedMaster, String newBedStatus ) throws Exception{

		try {
			NursingUnit nursingUnit = bedMaster.getNursingUnit();
			
			NursingUnitType existingNursingUnitType = nursingUnit.getNursingUnitType();

			BedStatus oldBedStatus = bedMaster.getBedStatus();
			
			if (  BedManagementConstants.BED_STATUS_AVAILABLE.equals(oldBedStatus.getBedStatusCd())
					&& !BedManagementConstants.BED_STATUS_AVAILABLE.equals(newBedStatus)) {

				//
				//Bed status has changed from available to unavailable(i.e. occupied,cleaning,defective etc.)
				//
				existingNursingUnitType.setAvailableBedCount( existingNursingUnitType.getAvailableBedCount() - 1 );
				
				existingNursingUnitType.setLastModifiedDtm( new Date());
				existingNursingUnitType.setModifiedBy(bedMaster.getModifiedBy()); 
				nursingUnitTypeDAO.attachDirty(existingNursingUnitType);
				
				// Increase the total available count for related bed pools by 1			
					
					List<BedPoolHasUnitType> bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", existingNursingUnitType.getUnitTypeCd()); 
					for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
						BedPool	bedPool = poolHasUnitType.getBedPool();
					 	bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() - 1);
					 	bedPoolDAO.attachDirty(bedPool);
					}

			}else if( !BedManagementConstants.BED_STATUS_AVAILABLE.equals(oldBedStatus.getBedStatusCd()) 
					&& BedManagementConstants.BED_STATUS_AVAILABLE.equals(newBedStatus) ){
				
				//
				//Bed status has changed to available from unavailable.
				
				existingNursingUnitType.setAvailableBedCount( existingNursingUnitType.getAvailableBedCount() + 1 );
				
				existingNursingUnitType.setLastModifiedDtm( new Date());
				existingNursingUnitType.setModifiedBy(bedMaster.getModifiedBy()); 
				nursingUnitTypeDAO.attachDirty(existingNursingUnitType);
				
				// Decrease the total available count for related bed pools by 1
					
					List<BedPoolHasUnitType> bedPoolHasUnitTypeList= bedPoolHasUnitTypeDAO.findByProperty("nursingUnitType.unitTypeCd", existingNursingUnitType.getUnitTypeCd()); 
					for(BedPoolHasUnitType poolHasUnitType : bedPoolHasUnitTypeList ){
						BedPool	bedPool = poolHasUnitType.getBedPool();
					 	bedPool.setNumberOfAvailableBeds(bedPool.getNumberOfAvailableBeds() + 1);
					 	bedPoolDAO.attachDirty(bedPool);
					}

			}
		} catch (Exception e) {

			throw new Exception(" Failed to updated Unit type and Pool information. ");
		}
		
	}
	
	public boolean isBedExist(String bedNumber) throws HCISException {
		try {
			
			BedMaster bedMaster = bedMasterDAO.findById(bedNumber);
			
			if( bedMaster == null ){
				return false;
			}
			return true;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.READ_BEDMASTER_FAILED );
			throw new HCISException( fault, e );
		}
	}
	
	private List<String> getBedFeaturesForBed ( String bedNumber ){
		List<String> featuresList = new ArrayList<String>(0);
		if( bedNumber != null && !bedNumber.isEmpty()){
			List<BedHasSpecialFeatures> bedHasSpecialFeaturesList = dataModelManager.getBedHasSpecialFeaturesList( bedNumber );
			if( bedHasSpecialFeaturesList != null && bedHasSpecialFeaturesList.size() > 0 ){
				for ( BedHasSpecialFeatures bedHasSpecialFeatures : bedHasSpecialFeaturesList ){
					featuresList.add( bedHasSpecialFeatures.getId().getFeatureName() );
				}
			}
		}
		return featuresList;
		
	}

	public List<CodeAndDescription> getOccupiedBedFromPatient(Integer admissionReqNbr, Integer patientId ){
		
		List<BedMaster> bedMasterList = bedMasterDAO.getCurrentlyOccupiedBedBeds(admissionReqNbr, patientId);
		List<CodeAndDescription> bedList = new ArrayList<CodeAndDescription>(0);
		
		if( bedMasterList != null && !bedMasterList.isEmpty()){
			for( BedMaster bedMaster : bedMasterList ){
				
				bedList.add( new CodeAndDescription( bedMaster.getBedNumber(),
													 bedMaster.getDescription()));
			}
		}
		return bedList;
	}
	
	public void setDataModelConverter(IPDataModelConverter dataModelConverter) {
		this.dataModelConverter = dataModelConverter;
	}


	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}


	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}


	public void setBedMasterDAO(BedMasterDAOExtn bedMasterDAO) {
		this.bedMasterDAO = bedMasterDAO;
	}


	public void setBedPoolDAO(BedPoolDAOExtn bedPoolDAO) {
		this.bedPoolDAO = bedPoolDAO;
	}


	public void setBedPoolHasUnitTypeDAO(
			BedPoolHasUnitTypeDAOExtn bedPoolHasUnitTypeDAO) {
		this.bedPoolHasUnitTypeDAO = bedPoolHasUnitTypeDAO;
	}

	public void setBedEnvelopeDAO(BedEnvelopeDAOExtn bedEnvelopeDAO) {
		this.bedEnvelopeDAO = bedEnvelopeDAO;
	}


	public void setBedEnvelopeHasPoolDAO(
			BedEnvelopeHasPoolDAOExtn bedEnvelopeHasPoolDAO) {
		this.bedEnvelopeHasPoolDAO = bedEnvelopeHasPoolDAO;
	}

	
	public void setBedReservationSpecialFeaturesDAO(
			BedReservationSpecialFeaturesDAO bedReservationSpecialFeaturesDAO) {
		this.bedReservationSpecialFeaturesDAO = bedReservationSpecialFeaturesDAO;
	}

	

	public void setBedHasSpecialFeaturesDAO(
			BedHasSpecialFeaturesDAO bedHasSpecialFeaturesDAO) {
		this.bedHasSpecialFeaturesDAO = bedHasSpecialFeaturesDAO;
	}

	public void setNursingUnitTypeDAO(NursingUnitTypeDAO nursingUnitTypeDAO) {
		this.nursingUnitTypeDAO = nursingUnitTypeDAO;
	}

	public void setBedActivityDAO(BedActivityDAO bedActivityDAO) {
		this.bedActivityDAO = bedActivityDAO;
	}

	public void setBedReservationDAO(BedReservationDAOExtn bedReservationDAO) {
		this.bedReservationDAO = bedReservationDAO;
	}

	public void setBedReservationActivityDAO(
			BedReservationActivityDAO bedReservationActivityDAO) {
		this.bedReservationActivityDAO = bedReservationActivityDAO;
	}

	public void setBedUsageHistoryDAO(BedUsageHistoryDAOExtn bedUsageHistoryDAO) {
		this.bedUsageHistoryDAO = bedUsageHistoryDAO;
	}


	public void setBedBillHistoryDAO(BedBillHistoryDAO bedBillHistoryDAO) {
		this.bedBillHistoryDAO = bedBillHistoryDAO;
	}

}
