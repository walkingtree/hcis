package in.wtc.hcis.ot.bl.configuration;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.DoctorSummaryBM;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.constants.ServicesConstants;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ot.bl.common.OTDataModelConverter;
import in.wtc.hcis.ot.bl.common.OTDataModelManager;
import in.wtc.hcis.ot.bl.constant.OTConstant;
import in.wtc.hcis.ot.bl.constant.OTErrors;
import in.wtc.hcis.ot.bm.OTBookingBM;
import in.wtc.hcis.ot.bm.OTDetailBM;
import in.wtc.hcis.ot.bm.OTSurgeonAvaibilityBM;
import in.wtc.hcis.ot.bm.OtSurgeryAssoBM;
import in.wtc.hcis.ot.bm.PatientSurgeryBM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtBookingActivity;
import com.wtc.hcis.ip.da.OtBookingActivityDAO;
import com.wtc.hcis.ip.da.OtBookingActivityId;
import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtPatientSurgery;
import com.wtc.hcis.ip.da.OtPatientSurgeryDAO;
import com.wtc.hcis.ip.da.OtSurgery;
import com.wtc.hcis.ip.da.OtSurgeryAssociation;
import com.wtc.hcis.ip.da.OtSurgeryAssociationId;
import com.wtc.hcis.ip.da.OtSurgeryStatusTime;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeDAO;
import com.wtc.hcis.ip.da.extn.OtBookingDAOExtn;
import com.wtc.hcis.ip.da.extn.OtDetailDAOExtn;
import com.wtc.hcis.ip.da.extn.OtSurgeryAssociationDAOExtn;

public class OTManagerImpl implements OTManager {
	
	private OTDataModelConverter otdataModelConverter;
	private OTDataModelManager otDataModelManager;
	private OtDetailDAOExtn otDetailDAO;
	private OtSurgeryAssociationDAOExtn otSurgeryAssociationDAO;
	private DataModelManager dataModelManager;
	private DoctorManager doctorManager;
	private OtBookingDAOExtn otBookingDAO;
	private CommonDataManager commonDataManager;
	private OtPatientSurgeryDAO otPatientSurgeryDAO;
	private OtSurgeryStatusTimeDAO surgeryStatusTimeDAO;
	private OtBookingActivityDAO bookingActivityDAO;
/**
 *  Setups new Operation theater detail on the system, with optional association with surgery  
 */
	@Override
	public void addOT(OTDetailBM otDetailBM) throws HCISException {
		try{
			OtDetail otDetail = otdataModelConverter.convertOTDetailBM2DM(otDetailBM, null);
			otDetail.setCreatedBy(otDetailBM.getCreatedBy());
			otDetail.setCreatedDtm(new Date());
			
			List<OtDetail> otDetailList = otDetailDAO.findByProperty("bedMaster.bedNumber", otDetailBM.getBedNumber());
			
			if( otDetailList != null && otDetailList.size() > 0){
				throw new Exception("Bed : " + otDetailBM.getBedNumber() + " is already in "+
						otDetailList.get(0).getName()+ " operation theatre");
			}
			
			otDetailDAO.save(otDetail);
			
			List<OtSurgeryAssoBM> otSurgeryAssoList = otDetailBM.getOtSurgeryAssoBMList();
			
			this.addOTSurgeryAssociation(otSurgeryAssoList, otDetail);
		}
		catch(Exception e){
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_ADD_FAILED);
			throw new HCISException(fault,e);
		}
	}

/**
 * 		This method will be used for search window. it will return list of Operation theatre.
 */
	@Override
	public ListRange getOTDetail(String otCode, String otName,
			String bedNumber, String surgeryCode, int start, int count,
			String orderBy) throws HCISException {
		
		try{
			List<OtDetail> otDetailList = otDetailDAO.getOTDetails(otCode, otName, bedNumber, surgeryCode);
			
			List<OTDetailBM> otDetailBMList = new ArrayList<OTDetailBM>(0);
			if( otDetailList != null && !otDetailList.isEmpty()){
				for( OtDetail otDetail : otDetailList){
					otDetailBMList.add(otdataModelConverter.convertOTDetailDM2BM(otDetail));
				}
			}
			
			return WtcUtils.convertListToListRange(otDetailBMList);
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_READ_FAILED);
			throw new HCISException(fault);
		}
		
	}

/**
 *  This method will modify existing otDetail
 * 
 * 	@param modifiedOtDetailBM
 */
	@Override
	public OTDetailBM modifyOTDetail(OTDetailBM modifiedOtDetailBM)
			throws HCISException {
		try{
			OtDetail otDetail = otDataModelManager.getOTDetail(modifiedOtDetailBM.getOtId());
			
			otDetail = otdataModelConverter.convertOTDetailBM2DM(modifiedOtDetailBM, otDetail);
			
			otDetail.setModifiedBy(modifiedOtDetailBM.getCreatedBy());
			otDetail.setLastModifiedDtm(new Date());
			
			otDetailDAO.attachDirty(otDetail);
			
			// This will get all the associated surgery with the given Operation theatre
			
			List<OtSurgeryAssociation> otSurgeryAssociationList = otSurgeryAssociationDAO.findByProperty("id.otId", otDetail.getOtId());
			
			//This will remove associated surgery with the operation theatre.
			
			this.removeAssociatedSurgery(otSurgeryAssociationList);
			
			List<OtSurgeryAssoBM> otSurgeryAssoBMList = modifiedOtDetailBM.getOtSurgeryAssoBMList();
			
			// This will add surgery for the operation theatre.
			
			this.addOTSurgeryAssociation(otSurgeryAssoBMList, otDetail);
			return otdataModelConverter.convertOTDetailDM2BM(otDetail);
			
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_MODIFY_FAILED);
			throw new HCISException(fault);
		}
	}
	
/**
 *  This will create Associated surgery with this operation theatre.
 * @param otSurgeryAssoBM
 * @param otDetail
 */
	
	private void addOTSurgeryAssociation(List<OtSurgeryAssoBM> otSurgeryAssoBMList , OtDetail otDetail){
		try{
			if( otSurgeryAssoBMList != null && !otSurgeryAssoBMList.isEmpty()){
				for( OtSurgeryAssoBM otSurgeryAssoBM : otSurgeryAssoBMList){
					String surgeryCode = null;
					
					OtSurgeryAssociation otSurgeryAsso = new OtSurgeryAssociation();
					
					OtSurgeryAssociationId otSurgeryAssociationId = new OtSurgeryAssociationId();
					otSurgeryAssociationId.setOtId(otDetail.getOtId());
					
					if( otSurgeryAssoBM.getSurgeryName() != null ){
						surgeryCode = otSurgeryAssoBM.getSurgeryName().getCode();
					}

					otSurgeryAssociationId.setSurgeryCode(surgeryCode);
					OtSurgery otSurgery = otDataModelManager.getOTSurgery(surgeryCode);
					otSurgeryAsso.setOtSurgery(otSurgery);
					otSurgeryAsso.setOtDetail(otDetail);
					otSurgeryAsso.setCreatedBy(otSurgeryAssoBM.getCreatedBy());
					otSurgeryAsso.setCreatedDtm(new Date());
					otSurgeryAsso.setId(otSurgeryAssociationId);
					
					otSurgeryAssociationDAO.save(otSurgeryAsso);
				}
			}
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_ADD_SURGERY_ASSOCIATION_FAILED);
			throw new HCISException(fault);
		}

	}
	/**
	 *  This method will remove associated surgery with the given OTCODE.
	 * @param otCode
	 */
	private void removeAssociatedSurgery( List<OtSurgeryAssociation> otSurgeryAssociationList ){
		try{
			
			if( otSurgeryAssociationList != null && !otSurgeryAssociationList.isEmpty() ){
				for( OtSurgeryAssociation otSuregeryAssociation : otSurgeryAssociationList ){
					otSurgeryAssociationDAO.delete(otSuregeryAssociation);
				}
			}
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_REMOVE_SURGERY_ASSOCIATION_FAILED);
			throw new HCISException(fault);
		}
	}
	
	public OTDetailBM getAssociatedSurgeryForOT(String otId)throws HCISException{
		
		List<OtSurgeryAssociation> otSurgeryAssoList = otSurgeryAssociationDAO.getSurgeryAssociationList(otId, null);
		
		OTDetailBM otDetailBM = new OTDetailBM();
		
		if(otSurgeryAssoList!= null && otSurgeryAssoList.size() > 0 ){
			List<OtSurgeryAssoBM> otSurgeryAssoBMList = new ArrayList<OtSurgeryAssoBM>(0);
			for (OtSurgeryAssociation otSurgeryAssociation : otSurgeryAssoList) {
				OtSurgeryAssoBM otSurgeryAssoBM = new OtSurgeryAssoBM();
				
				CodeAndDescription surgery = new CodeAndDescription();
				if( otSurgeryAssociation.getOtSurgery() != null){
					surgery.setCode(otSurgeryAssociation.getOtSurgery().getSurgeryCode());
					surgery.setDescription(otSurgeryAssociation.getOtSurgery().getSurgeryName());
				}
				otSurgeryAssoBM.setSurgeryName(surgery);
				otSurgeryAssoBMList.add(otSurgeryAssoBM);
			}
			otDetailBM.setOtSurgeryAssoBMList(otSurgeryAssoBMList);
		}
		
		return otDetailBM;
	}
	
	public void removeOTList( List<String> otIDList, String removedBy )throws HCISException{
		try{
			if( otIDList != null && otIDList.size() > 0){
				for( String otId : otIDList ){
					List<OtSurgeryAssociation> otSurgeryAssoList = otSurgeryAssociationDAO.getSurgeryAssociationList(otId, null);
					if( otSurgeryAssoList != null && otSurgeryAssoList.size() > 0){
						for( OtSurgeryAssociation otSurgeryAsso : otSurgeryAssoList){
							otSurgeryAssociationDAO.delete(otSurgeryAsso);
						}
					}
					OtDetail otDetail = otDataModelManager.getOTDetail(otId);
					otDetailDAO.delete(otDetail);
				}
			}
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_REMOVE_FAILED);
			throw new HCISException(fault);
		}
	}

	/**
	 * 
	 * This method will return the best matching OT-Surgery combination based on the passed parameter.
	 *  
	 * SurgeryCode and fromDtm is required field for searching the best match, if not fromDtm is given then 
	 * take the current date and time.
	 * 
	 *  Searching algorithm:-
	 *  
	 *  1. Store from date, based on the surgery time calculate the time period (in minutes)
	 *  2. Start building OT-Time slot' combination list
	 *  2.1  Get all the available OT from the system
	 *  2.2 Start iterating through the OT list
	 *  2.3 Get booking detail for that OT, sorted by booking from date
	 *  2.4 For the first booking detail get the 'From date' subtract it from  'search from date' if it is
	 *  	greater then  desired time period add it to availability slot list,
	 *      set from date for this iteration to 'Booking to Date'
	 *  2.5 repeat step 2.4 until all list item has iterated or if to date is specified then, one list
	 *  is found whose from date is greater then 'To date' for the search condition
	 *  
	 *  2.6 Repeat from step 2.2 to 2.5 for all OTs
	 *  
	 *  
	 *  Now Get the surgeon detail for each 'OT-Time slot' combination
	 *  
	 *  Get the Doctor roster for given date
	 *   divide the roster into slots if it has any appointment against that roster.
	 *   keep only those slots which has more size/length than the required time slot
	 *   and discard others.
	 *   
	 *   
	 *  
	 *  first get all the  detail for given surgery and calculate the time require  
	 * 
	 * @param surgerCode
	 * @param otId
	 * @param doctorId
	 * @param fromDtm
	 * @param toDtm
	 * @param timePeriod - in minutes
	 * @return
	 */
	public ListRange getOTAvaibilitySlot(String surgeryCode, String otId, Integer doctorId,
							  Integer timePeriod,
							  Date fromDtm, Date toDtm,int start, int count,String orderBy){
		
		
		Validate.notNull(surgeryCode, "Surgery code is required ");
		
		Service service = dataModelManager.getServiceByCode(surgeryCode);
		
		
		if( timePeriod == null || timePeriod <= 0){
			timePeriod = this.getTimePeriod(surgeryCode);
			//calculate surgery time by adding the time of status transition which contributes to scheduling 
			//timePeriod = 
		}
		
		List<OtDetail> otDetailList = otDetailDAO.getOTDetails(otId, null, null, surgeryCode);
		
		Map<OtDetail,Map<Date,Date>> otTimeSlotMap = new HashMap<OtDetail, Map<Date,Date>>(0);
		List<OTSurgeonAvaibilityBM> otSurgeonAvaibilityBMList = new ArrayList<OTSurgeonAvaibilityBM>(); 
		
		if(WtcUtils.isValid(otDetailList)){
			
			for( OtDetail currentOT : otDetailList){
				
				Date fromDateForOt = (Date)fromDtm.clone();
				
				List<OtBooking>  otBookingList = otBookingDAO.getBookingDetail(currentOT.getOtId(),null, fromDateForOt, null);//Get booking detail for that OT, sorted by booking from date and greater then from date
				
				
				Map<Date,Date> mathcedSlot = new HashMap<Date, Date>();
				if( WtcUtils.isValid(otBookingList)){
					
					for( OtBooking currentRecord : otBookingList){
						
						if((currentRecord.getBookingFromDtm().getTime() - fromDateForOt.getTime())/1000*60 < timePeriod){
							
							//set the from date to 'booking to date' of booking record
							
							//if(currentRecord.getBookingToDtm().after(fromDateForOt))
								//confirm it that we are not overlapping the slots for ot
								fromDateForOt.setTime(currentRecord.getBookingToDtm().getTime());
							
						}else{
							
							
							mathcedSlot.put((Date)fromDateForOt.clone(), (Date)currentRecord.getBookingFromDtm().clone());

							//keep increasing the slot from time 
							fromDateForOt.setTime(currentRecord.getBookingToDtm().getTime());
						}
						
					}
				}
				//All booking for current OT has be scanned, if still we have not reach to the 
				//'to time' then create one more slot with from time only
				
				if(toDtm == null  ){
					
					mathcedSlot.put(fromDateForOt, null);
					
				}else if(fromDateForOt.before(toDtm)){
					
					mathcedSlot.put(fromDateForOt, toDtm);
				}
				
				otTimeSlotMap.put(currentOT,mathcedSlot );
				
			}
			
			
			//TODO:There will be further logic to build surgeon related slot, for each OT slot we need to figure out the surgeon's slot
			//for now we will assume that doctors are available all the time

			
			//Get the doctors list for Department and speciality
			
			
			if(otTimeSlotMap.size() > 0 ){
				
				List<DoctorSummaryBM> surgeonsList = doctorManager.findDoctors(doctorId, service.getDepartment().getDepartmentCode(), null,
																				null, null, null, null,
																				null, null, 0, 999, null);
				
				if(WtcUtils.isValid(surgeonsList)){
					
					
					Set<OtDetail> availableOTList = otTimeSlotMap.keySet();
					
					for( OtDetail otDetail :  availableOTList){
						
						//For every time slot get doctors time slot and prepare OTSurgeonAvaibilityBM
						
						for( DoctorSummaryBM doctor : surgeonsList){
							
							
							Iterator<Date> fromDateItr  =  	((Map<Date,Date>)otTimeSlotMap.get(otDetail)).keySet().iterator();
							
							while (fromDateItr.hasNext()) {

								OTSurgeonAvaibilityBM avaibilityBM = new OTSurgeonAvaibilityBM();
								Date  fromDateForThisSlot = fromDateItr.next();
								
								avaibilityBM.setAvailableFromDtm(fromDateForThisSlot);//TODO: change this surgeon time slot
								
								Date toDateForThisSlot = (Date)((Map<Date,Date>)otTimeSlotMap.get(otDetail)).get(fromDateForThisSlot);
								
								avaibilityBM.setAvailableToDTM(toDateForThisSlot);//TODO: change this surgeon time slot
								avaibilityBM.setDoctorId(Integer.valueOf(doctor.getDoctor().getCode()));
								avaibilityBM.setDoctorName(doctor.getDoctor().getDescription());
								avaibilityBM.setOtId(otDetail.getOtId());
								avaibilityBM.setOtName( otDetail.getName());
								
								
								DateFormat slotDateFormat = new SimpleDateFormat("(dd-M-yy) h:m a");
								
								StringBuilder avaibilitySlot =  new StringBuilder(slotDateFormat.format(fromDateForThisSlot));
								
								if(toDateForThisSlot == null ){
									
									avaibilitySlot.append(" ....");
								}else{
									
									avaibilitySlot.append(" to "+ slotDateFormat.format(toDateForThisSlot) );
								}
								avaibilityBM.setAvailableOTSlot(avaibilitySlot.toString());
								
								otSurgeonAvaibilityBMList.add(avaibilityBM);
							}
							
						}
						
					}
					
				}
			}
			
		}
		
		return WtcUtils.convertListToListRange(otSurgeonAvaibilityBMList, start, count);
	}
	
	/**
	 *
	 * Method creates OT booking entry, at the same time creates patient surgery entry.
	 * 
	 * @param bookingBM
	 * @param force
	 * @return
	 */
	public String bookOT( OTBookingBM bookingBM ,boolean force){
		try{
		
		Validate.notNull(bookingBM, " OTBookingBM must not be null");

		CodeAndDescription operationTheatre = null;
		if( bookingBM.getOperationTheater() != null){
			operationTheatre = bookingBM.getOperationTheater();
		}
		
		List<OtBooking> bookingList = otBookingDAO.getBookingDetail(operationTheatre.getCode(), null, bookingBM.getBookingFromDtm(), bookingBM.getBookingToDtm());
		
		if(!force && WtcUtils.isValid(bookingList)){

		throw new RuntimeException(" The slot you have specified is not available ");
//			return "You are going to overbook";
		}
		
		AssignedServices assignedServices = commonDataManager.getAssignedServices(bookingBM.getServiceUId());
		
		//TODO: make sure assigned service is eligible for redering
		
		if( assignedServices.getAssignedServiceStatus() != null){
			if( assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals(ServicesConstants.ASSIGNED_SERVICE_RENDERED)
				&& assignedServices.getAssignedServiceStatus().getServiceStatusCode().equals( ServicesConstants.ASSIGNED_SERVICE_CANCELED)){
					throw new Exception("Booking of assigned service with status "+ assignedServices.getAssignedServiceStatus().getServiceStatusCode() + " is not allowed");
			}
		}
		
		bookingBM.setReferenceNumber(assignedServices.getReferenceNumber());
		bookingBM.setReferenceType( assignedServices.getReferenceType());
		
		OtBooking booking = otdataModelConverter.convertOTBookingBM2DM(bookingBM, null);
		
		ReferenceDataList bookingStatus = commonDataManager.getReferenceData(OTConstant.CONTEXT_OT_BOOKING_STATUS, 
				OTConstant.INITIAL_OT_BOOKIG_STATUS);
		booking.setBookingStatus(bookingStatus.getId().getAttrCode());
		booking.setCreatedBy(bookingBM.getUserId());
		booking.setCretedDtm(new Date());
		
		otBookingDAO.save(booking);
		
		this.createPatientSurgeryActivity(booking, null);
		
		//For now create the patient surgery detail also
		//Later we may have some separate  work-flow for booking and surgery assignment
		
		PatientSurgeryBM patientSurgeryBM = this.convertOtBookingBM2PatientSurgeryBM(bookingBM);
		
		OtPatientSurgery otPatientSurgery=otdataModelConverter.convertOTPatientSurgeryBM2DM(patientSurgeryBM, null) ;//TODO: datamodelConvertor.convertOtPatientSurgeryBM2DM(patientSurgeryBM)
		OtPatientSurgery patientSurgery = otPatientSurgeryDAO.findById(Long.valueOf(assignedServices.getServiceUid()));
		if( patientSurgery != null ){
			throw new Exception("OT record already exist for this assigned service with ServiceUID : "+ assignedServices.getServiceUid());
		}
		
		otPatientSurgery.setPatientSurgeryId(Long.valueOf(assignedServices.getServiceUid()));
		otPatientSurgery.setOtBooking(booking);
		otPatientSurgery.setStatusCode(null);
		otPatientSurgery.setCoordinatorId(Integer.valueOf(booking.getOtDetail().getCoordinatorId()));
		
		ReferenceDataList surgeryStatus = commonDataManager.getReferenceData(OTConstant.CONTEXT_OT_BOOKING_STATUS, 
				OTConstant.INITIAL_PATIENT_SURGERY_STATUS);
		
		otPatientSurgery.setStatusCode(surgeryStatus.getId().getAttrCode());
		otPatientSurgery.setCreatedBy(booking.getCreatedBy());
		otPatientSurgery.setCratedDtm(new Date());
		
		otPatientSurgery.setSurgeryDate(booking.getBookingFromDtm());
		
		otPatientSurgeryDAO.save(otPatientSurgery);
		
		return booking.getOtBookingNbr().toString();
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_BOOKING_FAILED);
			throw new HCISException(fault,e);
		}
	}
	
	private PatientSurgeryBM convertOtBookingBM2PatientSurgeryBM( OTBookingBM otBookingBM ){
	
		PatientSurgeryBM patientSurgeryBM = new PatientSurgeryBM();
		
		patientSurgeryBM.setDoctorId(otBookingBM.getDoctorId());
		patientSurgeryBM.setPatientId(otBookingBM.getPatientId());
	
//		patientSurgeryBM.setCoordinatorId(otBookingBM.get)
		patientSurgeryBM.setOperationTheater(new CodeAndDescription(otBookingBM.getOperationTheater().getCode(),""));
		patientSurgeryBM.setSurgery( new CodeAndDescription( otBookingBM.getSurgery().getCode(),""));
		patientSurgeryBM.setSurgeryDate(otBookingBM.getBookingFromDtm());
		patientSurgeryBM.setUserId(otBookingBM.getUserId());
		patientSurgeryBM.setCratedDtm( new Date() );
		
		return patientSurgeryBM;
	}
	
	private Integer getTimePeriod( String surgeryCode){
		Integer timePeriod = 0;
		
		List<OtSurgeryStatusTime> surgeryStatusTimeList = surgeryStatusTimeDAO.findByProperty("id.surgeryCode", surgeryCode);
		
		if( surgeryStatusTimeList != null && surgeryStatusTimeList.size() > 0){
			for (OtSurgeryStatusTime surgeryStatusTime : surgeryStatusTimeList) {
				if( surgeryStatusTime.getOtSurgeryStatus() != null
						&& surgeryStatusTime.getTime() != null 
						&& !surgeryStatusTime.getTime().equals("")){
					if( surgeryStatusTime.getOtSurgeryStatus().getContributeToScheduling().equals("Y")){
						timePeriod += surgeryStatusTime.getTime();
					}
				}
			}
		}
		
		return timePeriod;
	}
	
	
	 
		/**
		 * Updates the OTBooking status based on the status transition configured in the system.
		 * Booking newStatus 'ADMITTED' marks corresponding patientSurgery also as 'ADMITTED'.
		 * 
		 * @param patientSurgeryId
		 * @param newStatusCode
		 * @param remarks
		 * @param createdBy
		 * @throws HCISException
		 */
	 
	 public void updateOtBookingStatus(Long bookingNbr, String newStatusCode, 
				String remarks, String createdBy) throws HCISException{
		 
		 if( bookingNbr != null){
			 OtBooking booking = otBookingDAO.findById(bookingNbr);
			 String fromStatus = booking.getBookingStatus();
			 
			 if(commonDataManager.isValidTransition("OT_BOOKING", null, null, fromStatus, newStatusCode)){
				 booking.setBookingStatus(newStatusCode);
				 booking.setModifiedBy(createdBy);
				 booking.setLastModifiedDtm(new Date());
				 otBookingDAO.attachDirty(booking);
				 
				 this.createPatientSurgeryActivity(booking, remarks);
			 }
		 }
	 }

	 private void createPatientSurgeryActivity( OtBooking booking, 
				String remarks ){

		OtBookingActivity bookingActivity = new OtBookingActivity();
		bookingActivity.setOtBooking(booking);
		bookingActivity.setBookingStatus(booking.getBookingStatus());
		bookingActivity.setRemarks(remarks);
		if( booking.getModifiedBy() != null){
		bookingActivity.setCreatedBy(booking.getModifiedBy());
		}
		else{
		bookingActivity.setCreatedBy(booking.getCreatedBy());
		}
		
		OtBookingActivityId id = new OtBookingActivityId();
		
		id.setOtBookingNbr(booking.getOtBookingNbr());
		id.setCreatedDtm(new Date());
		
		bookingActivity.setId(id);
		
		bookingActivityDAO.save(bookingActivity);
	 }

	
	public void setOtdataModelConverter(OTDataModelConverter otdataModelConverter) {
		this.otdataModelConverter = otdataModelConverter;
	}

	public void setOtDataModelManager(OTDataModelManager otDataModelManager) {
		this.otDataModelManager = otDataModelManager;
	}

	public void setOtDetailDAO(OtDetailDAOExtn otDetailDAO) {
		this.otDetailDAO = otDetailDAO;
	}

	public void setOtSurgeryAssociationDAO(
			OtSurgeryAssociationDAOExtn otSurgeryAssociationDAO) {
		this.otSurgeryAssociationDAO = otSurgeryAssociationDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}

	public void setOtBookingDAO(OtBookingDAOExtn otBookingDAO) {
		this.otBookingDAO = otBookingDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setOtPatientSurgeryDAO(OtPatientSurgeryDAO otPatientSurgeryDAO) {
		this.otPatientSurgeryDAO = otPatientSurgeryDAO;
	}

	public void setSurgeryStatusTimeDAO(OtSurgeryStatusTimeDAO surgeryStatusTimeDAO) {
		this.surgeryStatusTimeDAO = surgeryStatusTimeDAO;
	}

	public void setBookingActivityDAO(OtBookingActivityDAO bookingActivityDAO) {
		this.bookingActivityDAO = bookingActivityDAO;
	}



}
