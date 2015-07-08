package in.wtc.hcis.ot.bl.surgery;

import in.wtc.hcis.bm.base.CheckListBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.bo.constants.ReferenceDataConstants;
import in.wtc.hcis.bo.document.CheckListManager;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ot.bl.common.OTDataModelConverter;
import in.wtc.hcis.ot.bl.common.OTDataModelManager;
import in.wtc.hcis.ot.bl.constant.OTConstant;
import in.wtc.hcis.ot.bl.constant.OTErrors;
import in.wtc.hcis.ot.bm.OtNotesBM;
import in.wtc.hcis.ot.bm.OtNotesFieldsBM;
import in.wtc.hcis.ot.bm.OtSurgeryAssoBM;
import in.wtc.hcis.ot.bm.PatientSurgeryActivityBM;
import in.wtc.hcis.ot.bm.PatientSurgeryBM;
import in.wtc.hcis.ot.bm.SurgeryBM;
import in.wtc.hcis.ot.bm.SurgeryStatusTimeBM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.DocCheckListInstance;
import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.PatientDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.extn.PatientDAOExtn;
import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtNotesConfiguration;
import com.wtc.hcis.ip.da.OtNotesConfigurationDAO;
import com.wtc.hcis.ip.da.OtPatientSurgery;
import com.wtc.hcis.ip.da.OtPatientSurgeryActivity;
import com.wtc.hcis.ip.da.OtPatientSurgeryActivityDAO;
import com.wtc.hcis.ip.da.OtPatientSurgeryActivityId;
import com.wtc.hcis.ip.da.OtPatientSurgeryChecklist;
import com.wtc.hcis.ip.da.OtPatientSurgeryChecklistDAO;
import com.wtc.hcis.ip.da.OtPatientSurgeryChecklistId;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotes;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotesDAO;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotesId;
import com.wtc.hcis.ip.da.OtStatusChecklistAsso;
import com.wtc.hcis.ip.da.OtStatusChecklistAssoDAO;
import com.wtc.hcis.ip.da.OtSurgery;
import com.wtc.hcis.ip.da.OtSurgeryAssociation;
import com.wtc.hcis.ip.da.OtSurgeryAssociationId;
import com.wtc.hcis.ip.da.OtSurgeryDAO;
import com.wtc.hcis.ip.da.OtSurgeryStatus;
import com.wtc.hcis.ip.da.OtSurgeryStatusTime;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeDAO;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeId;
import com.wtc.hcis.ip.da.extn.OtBookingDAOExtn;
import com.wtc.hcis.ip.da.extn.OtPatientSurgeryDAOExtn;
import com.wtc.hcis.ip.da.extn.OtStatusChecklistAssoDAOExtn;
import com.wtc.hcis.ip.da.extn.OtSurgeryAssociationDAOExtn;
import com.wtc.hcis.ip.da.extn.OtSurgeryStatusDAOExtn;

public class SurgeryManagerImpl implements SurgeryManager {
	
	OTDataModelConverter otdataModelConverter;
	OTDataModelManager otDataModelManager;
	OtSurgeryStatusDAOExtn otSurgeryStatusDAO;
	OtSurgeryStatusTimeDAO otSurgeryStatusTimeDAO;
	OtSurgeryDAO otSurgeryDAO;
	OtSurgeryAssociationDAOExtn otSurgeryAssociationDAO;
	ServiceManager serviceManager;
	OtPatientSurgeryDAOExtn patientSurgeryDAO;
	PatientDAOExtn patientDAO;
	CommonDataManager commonDataManager;
	OtBookingDAOExtn otBookingDAO;
	DataModelManager dataModelManager;
	OtNotesConfigurationDAO notesConfigurationDAO;
	OtPatientSurgeryNotesDAO patientSurgeryNotesDAO;
	OtPatientSurgeryActivityDAO patientSurgeryActivityDAO;
	OtStatusChecklistAssoDAOExtn statusChecklistAssoDAO;
	OtPatientSurgeryChecklistDAO patientSurgeryChecklistDAO;
	CheckListManager checkListManager;
	
	
	/**
	 * This method setups new surgery on the system. For this first create service then create 
	 * surgery related details. Timing details for each status also needs to be captured.
	 * Additionally this method also creates association with related OTs.
	 * @param surgeryBM
	 * @throws HCISException
	 */

	@Override
	public void addSurgery(SurgeryBM surgeryBM) throws HCISException {
		
		try{
			
			serviceManager.addService(surgeryBM.getServiceBM());
			
			OtSurgery surgery = otdataModelConverter.convertSurgeryBM2DM(surgeryBM, null);
			
			surgery.setSurgeryCode(surgeryBM.getServiceBM().getServiceCode());
			surgery.setSurgeryName(surgeryBM.getServiceBM().getServiceName());
			surgery.setCreatedBy(surgeryBM.getUserId());
			surgery.setCreatedDtm(new Date());
			
			otSurgeryDAO.save(surgery);
			
			this.addSurgeryOTAssociation(surgeryBM.getOtSurgeryAssoBMList(), surgery);
			
			this.addSurgeryStatusTimeDetail(surgeryBM.getSurgeryStatusTimeBMList());
			
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.SURGERY_ADD_FAILED);
			throw new HCISException(fault,e);
		}

	}

	
	/**
	 * Returns statusTime detail for given surgery code. If no surgery code
	 * is passed then return all available  surgery status with no time detail 
	 * (read from surgery_status table). 
	 * @param surgeryCode
	 * @return
	 */

	public List<SurgeryStatusTimeBM> getSurgeryStatusTimeDetail( String surgeryCode)throws HCISException{
			try{
				
				List<SurgeryStatusTimeBM> otSurgeryStatusTimeBMList = new ArrayList<SurgeryStatusTimeBM>(0);
				if( surgeryCode != null && !surgeryCode.isEmpty()){
					List<OtSurgeryStatusTime> otSurgeryStatusTimeList = otSurgeryStatusTimeDAO.findByProperty("id.surgeryCode", surgeryCode);
					
					if( otSurgeryStatusTimeList != null && otSurgeryStatusTimeList.size() > 0){
						for (OtSurgeryStatusTime otSurgeryStatusTime : otSurgeryStatusTimeList) {
							SurgeryStatusTimeBM surgeryStatusTimeBM = otdataModelConverter.convertSurgeryStatusTimeDM2BM(otSurgeryStatusTime);
							otSurgeryStatusTimeBMList.add(surgeryStatusTimeBM);
						}
					}
				}
				
				else{
					List<OtSurgeryStatus> otSurgeryStatusList = otSurgeryStatusDAO.getOTSurgeryStatusList("Y", null);
					if( otSurgeryStatusList != null && otSurgeryStatusList.size() > 0){
						for (OtSurgeryStatus otSurgeryStatus : otSurgeryStatusList) {
							SurgeryStatusTimeBM surgeryStatusTimeBM = new SurgeryStatusTimeBM();
							
							CodeAndDescription surgeryStatus = new CodeAndDescription();
							surgeryStatus.setCode(otSurgeryStatus.getStatusCode());
							surgeryStatus.setDescription(otSurgeryStatus.getDescription());
							
							surgeryStatusTimeBM.setSurgeryStatus(surgeryStatus);
							
							otSurgeryStatusTimeBMList.add(surgeryStatusTimeBM);
						}
					}
				}
				
				return otSurgeryStatusTimeBMList;
				
			}
			catch (Exception e) {
				Fault fault = new Fault(OTErrors.SURGERY_STATUS_TIME_DETAIL_READ_FAILED);
				throw new HCISException(fault,e);
			}
	}
	

	/**
	 * First modify the service related details then surgery.
	 * @param surgeryBM
	 * @return
	 * @throws HCISException
	 */

	@Override
	public SurgeryBM modifySurgery(SurgeryBM surgeryBM) throws HCISException {
		try{
			
			serviceManager.modifyService(surgeryBM.getServiceBM());
			
			OtSurgery surgery = otDataModelManager.getOTSurgery(surgeryBM.getServiceBM().getServiceCode());
			surgery = otdataModelConverter.convertSurgeryBM2DM(surgeryBM, surgery);
//			surgery.setSurgeryCode(surgeryBM.getServiceBM().getServiceCode());
//			surgery.setSurgeryName(surgeryBM.getServiceBM().getServiceName());
			surgery.setModifiedBy(surgeryBM.getUserId());
			
			otSurgeryDAO.attachDirty(surgery);
			
			List<OtSurgeryAssociation> surgeryOTAssociationList = otSurgeryAssociationDAO.findByProperty("id.surgeryCode", surgery.getSurgeryCode());
			
			this.removeAssociatedOT(surgeryOTAssociationList);
			
			this.addSurgeryOTAssociation(surgeryBM.getOtSurgeryAssoBMList(), surgery);
			
			List<OtSurgeryStatusTime> surgeryStatusTimeList = otSurgeryStatusTimeDAO.findByProperty("id.surgeryCode", surgery.getSurgeryCode());
			
			this.removeSurgeryStatusTimeDetails(surgeryStatusTimeList);
			
			this.addSurgeryStatusTimeDetail(surgeryBM.getSurgeryStatusTimeBMList());
			
			return otdataModelConverter.convertSurgeryDM2BM(surgery);
			
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.SURGERY_ADD_FAILED);
			throw new HCISException(fault,e);
		}
		
	}
	
	/**
	 *  This method will used to add associated Operation theatre with the surgery.
	 * @param otSurgeryAssoBMList
	 * @param surgery
	 */
	private void addSurgeryOTAssociation( List<OtSurgeryAssoBM> otSurgeryAssoBMList ,OtSurgery surgery){
		try{
			if( otSurgeryAssoBMList != null && !otSurgeryAssoBMList.isEmpty()){
				for( OtSurgeryAssoBM surgeryOTAssoBM : otSurgeryAssoBMList){
					String otId = null;
					
					OtSurgeryAssociation otSurgeryAsso = new OtSurgeryAssociation();
					
					OtSurgeryAssociationId otSurgeryAssociationId = new OtSurgeryAssociationId();
					otSurgeryAssociationId.setSurgeryCode(surgery.getSurgeryCode());
					
					if( surgeryOTAssoBM.getOtName() != null ){
						otId = surgeryOTAssoBM.getOtName().getCode();
					}
					otSurgeryAssociationId.setOtId(otId);

					OtDetail otDetail = otDataModelManager.getOTDetail(otId);
					otSurgeryAsso.setOtSurgery(surgery);
					otSurgeryAsso.setOtDetail(otDetail);
					otSurgeryAsso.setCreatedBy(surgeryOTAssoBM.getCreatedBy());
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
 *  This method will be used to remove associated Operation with the Surgery	
 * @param surgeryOTAssociationList
 */
	
	private void removeAssociatedOT( List<OtSurgeryAssociation> surgeryOTAssociationList ){
		try{
			
			if( surgeryOTAssociationList != null && !surgeryOTAssociationList.isEmpty() ){
				for( OtSurgeryAssociation otSuregeryAssociation : surgeryOTAssociationList ){
					otSurgeryAssociationDAO.delete(otSuregeryAssociation);
				}
			}
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.OPERATON_THEATRE_REMOVE_SURGERY_ASSOCIATION_FAILED);
			throw new HCISException(fault);
		}
	}
/**
 * This method will be used to remove surgery status time detail.	
 * @param surgeryStatusTimeList
 */
	private void removeSurgeryStatusTimeDetails( List<OtSurgeryStatusTime> surgeryStatusTimeList){
		try{
			if( surgeryStatusTimeList != null ){
				for (OtSurgeryStatusTime otSurgeryStatusTime : surgeryStatusTimeList) {
					otSurgeryStatusTimeDAO.delete(otSurgeryStatusTime);
				}
			}	
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.SURGERY_STATUS_TIME_DETAIL_REMOVE_FAILED);
			throw new HCISException(fault);
		}
	}
/**
 *  This method will be used to save surgery status detail in OtSurgeryStatusDetail table	
 * @param surgeryStatusTimeBMList
 */
	
	private void addSurgeryStatusTimeDetail( List<SurgeryStatusTimeBM> surgeryStatusTimeBMList){
		try{
			if( surgeryStatusTimeBMList != null && surgeryStatusTimeBMList.size() > 0){
				for (SurgeryStatusTimeBM surgeryStatusTimeBM : surgeryStatusTimeBMList) {
					OtSurgeryStatusTime surgeryStatusTime = otdataModelConverter.convertSurgeryStatusTimeBM2DM(surgeryStatusTimeBM, null);
					
					OtSurgeryStatusTimeId otSurgeryStatusTimeId = new OtSurgeryStatusTimeId();
					otSurgeryStatusTimeId.setSurgeryCode(surgeryStatusTimeBM.getSurgeryCode());
					if( surgeryStatusTimeBM.getSurgeryStatus() != null ){
						otSurgeryStatusTimeId.setSurgeryStatusCode(surgeryStatusTimeBM.getSurgeryStatus().getCode());
					}
					
					surgeryStatusTime.setId(otSurgeryStatusTimeId);
					surgeryStatusTime.setCreatedBy(surgeryStatusTimeBM.getUserId());
					surgeryStatusTime.setCreatedDtm(new Date());
					
					otSurgeryStatusTimeDAO.save(surgeryStatusTime);
				}
			}
		}
		catch (Exception e) {
			Fault fault = new Fault(OTErrors.SURGERY_STATUS_TIME_DETAIL_ADD_FAILED);
			throw new HCISException(fault);
		}
	}
	
	/**
	 *  This method will return SurgeryBM for the given surgeyr code.
	 */
	
	public SurgeryBM getSurgeryForCode( String surgeryCode) throws HCISException {
		SurgeryBM surgeryBM = new SurgeryBM();
		
		OtSurgery surgery = otDataModelManager.getOTSurgery(surgeryCode);
		
		surgeryBM = otdataModelConverter.convertSurgeryDM2BM(surgery);
		
		List<OtSurgeryAssociation> otSurgeryAssoList = otSurgeryAssociationDAO.getSurgeryAssociationList(null, surgeryCode);
		
		if( otSurgeryAssoList != null && otSurgeryAssoList.size() > 0 ){
			List<OtSurgeryAssoBM> otSurgeryAssoBMList = new ArrayList<OtSurgeryAssoBM>(0);
			for (OtSurgeryAssociation otSurgeryAssociation : otSurgeryAssoList) {
				OtSurgeryAssoBM otSurgeryAssoBM = new OtSurgeryAssoBM();
				CodeAndDescription operationTheatre = new CodeAndDescription();
				if( otSurgeryAssociation.getOtDetail() != null){
					operationTheatre.setCode(otSurgeryAssociation.getOtDetail().getOtId());
					operationTheatre.setDescription(otSurgeryAssociation.getOtDetail().getName());
				}
				otSurgeryAssoBM.setOtName(operationTheatre);
				otSurgeryAssoBMList.add(otSurgeryAssoBM);
			}
			surgeryBM.setOtSurgeryAssoBMList(otSurgeryAssoBMList);
		}
		
		surgeryBM.setSurgeryStatusTimeBMList(this.getSurgeryStatusTimeDetail(surgeryCode));
		
		return surgeryBM;
	}
	
	public ListRange getOTSurgeries( String patientName, Integer doctorId,
				String otCode,String surgeryCode,Date fromDate,
				Date toDate,String referenceType, String referenceNbr,
				String otBookingNbr, int start, int count, String orderBy){
		
		
		List<Integer> patientIdList = patientDAO.getPatientIdList(patientName);
		
		List<OtPatientSurgery> patientSuregeryList = null;
		if( patientIdList != null && patientIdList.size() > 0){
		patientSuregeryList = patientSurgeryDAO.getPatientSurgeries(patientIdList, doctorId, 
																		otCode, surgeryCode, 
																		fromDate, toDate, 
																		referenceType, referenceNbr, 
																		otBookingNbr);
		}
		
		List<PatientSurgeryBM> patientSurgeryBMList = null;
		
		if( patientSuregeryList != null && patientSuregeryList.size() > 0){
			patientSurgeryBMList = new ArrayList<PatientSurgeryBM>(0);
			for (OtPatientSurgery otPatientSurgery : patientSuregeryList) {
				PatientSurgeryBM patientSurgeryBM = otdataModelConverter.convertOTPatientSurgeryDM2BM(otPatientSurgery);
				
				if( otPatientSurgery.getOtBooking() != null){
					if( otPatientSurgery.getOtBooking().getBookingStatus().equals("ADMITTED")){
						List<CodeAndDescription> surgeryStatusList = this.getSurgeryStatusList("SURGERY_STATUS", otPatientSurgery.getStatusCode());
						patientSurgeryBM.setSurgeryStatusList(surgeryStatusList);
					}
					else{
						List<CodeAndDescription> bookingStatusList = this.getBookingStatusList("OT_BOOKING", otPatientSurgery.getOtBooking().getBookingStatus());
						patientSurgeryBM.setBookingStatusList(bookingStatusList);
					}
				}
				
				patientSurgeryBMList.add(patientSurgeryBM);
			}
		}
		
		return WtcUtils.convertListToListRange(patientSurgeryBMList, start, count);
	} 
	 /**
	  * This method returns OT noteBM which contains list of OtNotesFieldsBM.
	  * 
	  * OT notes fields are configurable fields which can easily modified by
	  * data configuration.
	  * 
	  * Basic fields value of OT note ate fetched from 'PatientSurgery' table itself and for OtNotesFields first
	  * get all available fields from 'OtNotesConfiguration' and get corresponding fields value form 'PatientSuregeryNotes'
	  * @param patientSurgeryId
	  * @return
	  * @throws HCISException
	  */
	
	public OtNotesBM getOtNote(Long patientSurgeryId) throws HCISException{
		
		OtPatientSurgery patientSurgery = patientSurgeryDAO.findById(patientSurgeryId);
		OtNotesBM otNotesBM = new OtNotesBM();
		
		Patient patient = dataModelManager.getPatient(patientSurgery.getPatientId());
		Doctor doctor = dataModelManager.getDoctor(patientSurgery.getDoctorId());
		
		otNotesBM.setPatientName(patient.getFullName());
		otNotesBM.setPatientId(patientSurgery.getPatientId());
		otNotesBM.setPatientSurgeryId(patientSurgeryId);
		otNotesBM.setReferenceNumber(patientSurgery.getOtBooking().getReferenceNumber());
		otNotesBM.setReferenceType(patientSurgery.getOtBooking().getReferenceType());
		otNotesBM.setSurgeonId(doctor.getDoctorId());
		otNotesBM.setSurgeonName(doctor.getFullName());
		
		List<OtNotesConfiguration> notesConfigurationList = notesConfigurationDAO.findAll();
		List<OtNotesFieldsBM> otNotesFieldsBMList = null;
		
		if( notesConfigurationList != null && notesConfigurationList.size() > 0){
			otNotesFieldsBMList = new ArrayList<OtNotesFieldsBM>(0);
			for (OtNotesConfiguration otNotesConfiguration : notesConfigurationList) {
				OtNotesFieldsBM notesFieldsBM = otdataModelConverter.convertOTNotesConfiguration2OTFieldsBM(otNotesConfiguration);
			
				OtPatientSurgeryNotesId id = new OtPatientSurgeryNotesId();
				id.setFieldCode(otNotesConfiguration.getFieldCode());
				id.setPatientSurgeryId(patientSurgeryId);
				OtPatientSurgeryNotes otPatientSurgeryNotes = patientSurgeryNotesDAO.findById(id);
				if( otPatientSurgeryNotes != null){
					notesFieldsBM.setValue(otPatientSurgeryNotes.getValue());
				}
				
				otNotesFieldsBMList.add(notesFieldsBM);
			}
			
			otNotesBM.setOtNotesFieldsBMList(otNotesFieldsBMList);
		}
		
		return otNotesBM;
	}

	 /**
	  * Modifies PatientSurgery status according to the status transition configured in the system.
	  * 
	  * @param patientSurgeryId
	  * @param newStatusCode
	  * @param remarks
	  * @param createdBy
	  * @throws HCISException
	  */
	
	 public void updatePatientSurgeryStatus(Long patientSurgeryId, String newStatusCode, 
				String remarks, String createdBy, CheckListBM[] checkListBMList) throws HCISException{
		 try{
			 
			 OtPatientSurgery patientSurgery = null;
			 if( patientSurgeryId != null){
				 patientSurgery = patientSurgeryDAO.findById(patientSurgeryId);
				 if( patientSurgery.getOtBooking() != null ){
					 if( !patientSurgery.getOtBooking().getBookingStatus().equals(OTConstant.BOOKING_STATUS_ADMITTED)){
						 throw new Exception("Patient surgery should be in Admitted status");
					 }
				 }
				 
				 String fromStatus = patientSurgery.getStatusCode();
				 if( commonDataManager.isValidTransition("SURGERY_STATUS", null, null, fromStatus, newStatusCode)){
					 patientSurgery.setStatusCode(newStatusCode);
					 patientSurgeryDAO.attachDirty(patientSurgery);
					 
					 this.createPatientSurgeryActivity(patientSurgery, remarks, createdBy);
					 
				 }
			 }
			 
			 if( checkListBMList != null && checkListBMList.length > 0){
				 this.saveCheckLists(checkListBMList, newStatusCode, patientSurgeryId);
			 }
		 }
		 catch (Exception e) {
			 Fault fault = new Fault("SURGERY_STATUS_UPDATE_FAILED");
			throw new HCISException(fault,e);
		}
		 
	 }

	 /**
	  * Saves/modifies only 'OtNotesFields' value. Basic fields value of OT note are fetched from 'PatientSurgery',
	  * so these method will not modify those values. 
	  * 
	  * @param otNotesBM
	  * @throws HCISException
	  */
	 
	 public void saveOtNotes(OtNotesBM otNotesBM) throws HCISException{
		 try {
			 
			 Long patientSurgeryId = otNotesBM.getPatientSurgeryId();
			 OtPatientSurgery patientSurgery = patientSurgeryDAO.findById(patientSurgeryId);
			 
			 List<OtPatientSurgeryNotes> patientSurgeryNotesList = patientSurgeryNotesDAO.findByProperty("id.patientSurgeryId", patientSurgeryId);
			 
			 if( patientSurgeryNotesList != null && patientSurgeryNotesList.size() > 0){
				 List<OtNotesFieldsBM> otNotesFieldsBMList = otNotesBM.getOtNotesFieldsBMList();
				 if( otNotesFieldsBMList != null && otNotesFieldsBMList.size() > 0){
					 for (OtNotesFieldsBM otNotesFieldsBM : otNotesFieldsBMList) {
						 OtPatientSurgeryNotesId id = new OtPatientSurgeryNotesId();
						 id.setFieldCode(otNotesFieldsBM.getFieldCode());
						 id.setPatientSurgeryId(patientSurgeryId);
						 OtPatientSurgeryNotes patientSurgeryNotes = otDataModelManager.getPatientSurgeryNotes(id);
						 
						 patientSurgeryNotes.setValue(otNotesFieldsBM.getValue());
//						 patientSurgeryNotes.set
						 patientSurgeryNotesDAO.attachDirty(patientSurgeryNotes);
						 // TODO : setModifiedBy and modifiedDTM needs to be in table.
					}
				 }
			 }
			 else{
				 
				 List<OtNotesFieldsBM> otNotesFieldsBMList = otNotesBM.getOtNotesFieldsBMList();
				 if( otNotesFieldsBMList != null && otNotesFieldsBMList.size() > 0){
					 for (OtNotesFieldsBM otNotesFieldsBM : otNotesFieldsBMList) {
						 OtNotesConfiguration otNotesConfiguration = notesConfigurationDAO.findById(otNotesFieldsBM.getFieldCode());
						 
						 OtPatientSurgeryNotes patientSurgeryNotes = new OtPatientSurgeryNotes();
						 
						 OtPatientSurgeryNotesId id = new OtPatientSurgeryNotesId();
						 id.setFieldCode(otNotesConfiguration.getFieldCode());
						 id.setPatientSurgeryId(patientSurgeryId);
						 
						 patientSurgeryNotes.setId(id);
						 patientSurgeryNotes.setCreatedDtm(new Date());
						 patientSurgeryNotes.setCreatedBy(otNotesBM.getUserId());
						 patientSurgeryNotes.setOtNotesConfiguration(otNotesConfiguration);
						 patientSurgeryNotes.setOtPatientSurgery(patientSurgery);
						 patientSurgeryNotes.setValue(otNotesFieldsBM.getValue());
						 
						 patientSurgeryNotesDAO.save(patientSurgeryNotes);

					 }
				 }
			 }
			 
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException(fault , e);
		}
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
	 
	 public void updateOtBookingStatus(Long patientSurgeryId, String newStatusCode, 
				String remarks, String createdBy) throws HCISException{
		 
		 if( patientSurgeryId != null){
			 OtPatientSurgery patientSurgery = patientSurgeryDAO.findById(patientSurgeryId);
			 OtBooking booking = patientSurgery.getOtBooking();
			 String fromStatus = booking.getBookingStatus();
			 
			 if(commonDataManager.isValidTransition("OT_BOOKING", null, null, fromStatus, newStatusCode)){
				 booking.setBookingStatus(newStatusCode);
				 otBookingDAO.attachDirty(booking);
			 }
		 }
	 }
	 
	 private List<CodeAndDescription> getBookingStatusList( String context , String fromStatus){
		 
		 List<CodeAndDescription> statusList = null;
		 List<String> toStatusList = commonDataManager.getToStatusList(context, fromStatus);
		 
		 if( toStatusList != null && toStatusList.size() > 0){
			 statusList = new ArrayList<CodeAndDescription>(0);
			 for (String statusCode : toStatusList) {
				CodeAndDescription status = new CodeAndDescription();
				
				ReferenceDataList statusData= dataModelManager.getReferenceData("OT_BOOKING_STATUS", statusCode);
				
				status.setCode(statusCode);
				status.setDescription(statusData.getAttrDesc());
				
				statusList.add(status);
			}
		 }
		 return statusList;
	 }

	 private List<CodeAndDescription> getSurgeryStatusList( String context , String fromStatus){
		 
		 List<CodeAndDescription> statusList = null;
		 List<String> toStatusList = commonDataManager.getToStatusList(context, fromStatus);
		 
		 if( toStatusList != null && toStatusList.size() > 0){
			 statusList = new ArrayList<CodeAndDescription>(0);
			 for (String statusCode : toStatusList) {
				 OtSurgeryStatus surgeryStatus = otSurgeryStatusDAO.findById(statusCode);

				 CodeAndDescription status = new CodeAndDescription();
				 
				 status.setCode(surgeryStatus.getStatusCode());
				 status.setDescription(surgeryStatus.getDescription());
				 
				 statusList.add(status);
			 }
		 }
		 
		 return statusList;
		 
	 }
	 /**]
	  * 
	  * @param patientSurgery
	  * @param remarks
	  * @param userId
	  */
	 
	 private void createPatientSurgeryActivity( OtPatientSurgery patientSurgery,
			 									String remarks, String userId){
		 
		 OtPatientSurgeryActivity patientSurgeryActivity = new OtPatientSurgeryActivity();
		 
		 patientSurgeryActivity.setActivityTime(new Date());
		 patientSurgeryActivity.setCreatedBy(userId);
		 patientSurgeryActivity.setCreatedDtm(new Date());
		 patientSurgeryActivity.setOtPatientSurgery(patientSurgery);
		 
		 OtPatientSurgeryActivityId id = new OtPatientSurgeryActivityId();
		 
		 id.setPatientSurgeryId(patientSurgery.getPatientSurgeryId());
		 id.setSurgeryStatus(patientSurgery.getStatusCode());
		 
		 patientSurgeryActivity.setId(id);
		 patientSurgeryActivity.setRemarks(remarks);

		 patientSurgeryActivityDAO.save(patientSurgeryActivity);
	 }
/**
 * @param patientSurgeryId
 * @param surgeryStatusCode
 * 	 
 */
	 
	 public List<CheckListBM> getCheckLists( Long patientSurgeryId , String surgeryStatusCode ) throws HCISException{
		 List<CheckListBM> checkListBMs = null;
//		 if( patientSurgeryId != null){
			 List<OtPatientSurgeryChecklist> patientSurgeryCheckLists = patientSurgeryChecklistDAO.findByProperty("id.patientSurgeryId", patientSurgeryId);
			 if( patientSurgeryCheckLists!= null && patientSurgeryCheckLists.size() > 0 ){
				 checkListBMs = new ArrayList<CheckListBM>(0);
				 for (OtPatientSurgeryChecklist patientSurgeryChecklist : patientSurgeryCheckLists) {
				 	if( patientSurgeryChecklist.getId().getChecklistInstanceId() != null)
				 	{
				 		Long checkListInstanceId =  patientSurgeryChecklist.getId().getChecklistInstanceId();
				 		
				 		CheckListBM checkListBM = checkListManager.getCheckListOfId(null, checkListInstanceId);
				 		checkListBM.setCheckListInstanceId(checkListInstanceId);
				 		
				 		checkListBMs.add(checkListBM);
				 	}
				}
			 }
			 
//		 }
		 else if( surgeryStatusCode != null && !surgeryStatusCode.isEmpty()){
			 List<OtStatusChecklistAsso> statusCheckListAssoList = statusChecklistAssoDAO.getStatusCheckListAssoList(surgeryStatusCode, "B");
			 if( statusCheckListAssoList != null && statusCheckListAssoList.size() > 0){
				 checkListBMs = new ArrayList<CheckListBM>(0);
				 
				 for (OtStatusChecklistAsso statusChecklistAsso : statusCheckListAssoList) {
					Integer checkListId = statusChecklistAsso.getId().getCheckListId();
					CheckListBM checkListBM= checkListManager.getCheckListOfId(checkListId, null);
					
					checkListBMs.add(checkListBM);
				}
			 }
		 }
		 
		 return checkListBMs;
	 }
	 /**
	  * 
	  * This method will create Patient surgery checklist. 
	  * @param checkListBMList
	  * @param statusCode
	  * 
	  * 
	  */
	 
	 public void saveCheckLists(CheckListBM[] checkListBMList, String statusCode, Long patientSurgeryId ){
		 try {
			 if( checkListBMList != null && checkListBMList.length > 0 ){
				 OtPatientSurgery patientSurgery = patientSurgeryDAO.findById(patientSurgeryId);
				 for (CheckListBM checkListBM : checkListBMList) {
					Long checkListInstanceId = checkListManager.saveCheckListInstance(checkListBM);
					
					if( checkListBM.getCheckListInstanceId() == null){
					
						OtPatientSurgeryChecklist patientSurgeryCheckList = new OtPatientSurgeryChecklist();
						
						OtPatientSurgeryChecklistId id = new OtPatientSurgeryChecklistId();
						
						id.setChecklistInstanceId(checkListInstanceId);
						id.setPatientSurgeryId(patientSurgeryId);
						id.setSurgeryStatusCode(statusCode);
						patientSurgeryCheckList.setId(id);
						
						OtSurgeryStatus surgeryStatus = otSurgeryStatusDAO.findById(statusCode);
						
						patientSurgeryCheckList.setOtSurgeryStatus(surgeryStatus);
						
						patientSurgeryCheckList.setOtPatientSurgery(patientSurgery);
	//					patientSurgeryCheckList.set
						
						patientSurgeryCheckList.setCreatedBy(checkListBM.getUserId());
						patientSurgeryCheckList.setCreatedDtm(new Date());
						
						patientSurgeryChecklistDAO.save(patientSurgeryCheckList);
					}
					
				}
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}
	 }
	 

	public void setOtdataModelConverter(OTDataModelConverter otdataModelConverter) {
		this.otdataModelConverter = otdataModelConverter;
	}


	public void setOtDataModelManager(OTDataModelManager otDataModelManager) {
		this.otDataModelManager = otDataModelManager;
	}


	public void setOtSurgeryStatusDAO(OtSurgeryStatusDAOExtn otSurgeryStatusDAO) {
		this.otSurgeryStatusDAO = otSurgeryStatusDAO;
	}


	public void setOtSurgeryStatusTimeDAO(
			OtSurgeryStatusTimeDAO otSurgeryStatusTimeDAO) {
		this.otSurgeryStatusTimeDAO = otSurgeryStatusTimeDAO;
	}


	public void setOtSurgeryDAO(OtSurgeryDAO otSurgeryDAO) {
		this.otSurgeryDAO = otSurgeryDAO;
	}


	public void setOtSurgeryAssociationDAO(
			OtSurgeryAssociationDAOExtn otSurgeryAssociationDAO) {
		this.otSurgeryAssociationDAO = otSurgeryAssociationDAO;
	}


	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}


	public void setPatientSurgeryDAO(OtPatientSurgeryDAOExtn patientSurgeryDAO) {
		this.patientSurgeryDAO = patientSurgeryDAO;
	}


	public void setPatientDAO(PatientDAOExtn patientDAO) {
		this.patientDAO = patientDAO;
	}


	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setOtBookingDAO(OtBookingDAOExtn otBookingDAO) {
		this.otBookingDAO = otBookingDAO;
	}


	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}


	public void setNotesConfigurationDAO(
			OtNotesConfigurationDAO notesConfigurationDAO) {
		this.notesConfigurationDAO = notesConfigurationDAO;
	}


	public void setPatientSurgeryNotesDAO(
			OtPatientSurgeryNotesDAO patientSurgeryNotesDAO) {
		this.patientSurgeryNotesDAO = patientSurgeryNotesDAO;
	}


	public void setPatientSurgeryActivityDAO(
			OtPatientSurgeryActivityDAO patientSurgeryActivityDAO) {
		this.patientSurgeryActivityDAO = patientSurgeryActivityDAO;
	}



	public void setPatientSurgeryChecklistDAO(
			OtPatientSurgeryChecklistDAO patientSurgeryChecklistDAO) {
		this.patientSurgeryChecklistDAO = patientSurgeryChecklistDAO;
	}


	public void setCheckListManager(CheckListManager checkListManager) {
		this.checkListManager = checkListManager;
	}


	public void setStatusChecklistAssoDAO(
			OtStatusChecklistAssoDAOExtn statusChecklistAssoDAO) {
		this.statusChecklistAssoDAO = statusChecklistAssoDAO;
	}

}
