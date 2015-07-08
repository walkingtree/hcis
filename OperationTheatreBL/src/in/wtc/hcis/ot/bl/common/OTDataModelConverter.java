package in.wtc.hcis.ot.bl.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.EntityBM;
import in.wtc.hcis.bo.admin.EntityManager;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.ip.common.IPDataModelManager;
import in.wtc.hcis.ot.bl.constant.OTConstant;
import in.wtc.hcis.ot.bm.OTBookingBM;
import in.wtc.hcis.ot.bm.OTDetailBM;
import in.wtc.hcis.ot.bm.OtNotesBM;
import in.wtc.hcis.ot.bm.OtNotesFieldsBM;
import in.wtc.hcis.ot.bm.PatientSurgeryBM;
import in.wtc.hcis.ot.bm.SurgeryBM;
import in.wtc.hcis.ot.bm.SurgeryStatusTimeBM;

import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtNotesConfiguration;
import com.wtc.hcis.ip.da.OtPatientSurgery;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotes;
import com.wtc.hcis.ip.da.OtSurgery;
import com.wtc.hcis.ip.da.OtSurgeryStatus;
import com.wtc.hcis.ip.da.OtSurgeryStatusTime;

public class OTDataModelConverter {
	
	IPDataModelManager ipDataModelManager;
	EntityManager entityManager;
	OTDataModelManager otDataModelManager;
	DataModelManager dataModelManager;
	
	public OtDetail convertOTDetailBM2DM(OTDetailBM otDetailBM , OtDetail existingOTDetail){
		OtDetail otDetail = null;
		if( existingOTDetail != null){
			otDetail = existingOTDetail;
		}
		else{
			otDetail = new OtDetail();
		}
		
		otDetail.setOtId(otDetailBM.getOtId());
		otDetail.setName(otDetailBM.getName());
		otDetail.setBedMaster(ipDataModelManager.getBedMaster(otDetailBM.getBedNumber()));
		if( otDetailBM.getCoordinator() != null ){
			otDetail.setCoordinatorId(otDetailBM.getCoordinator().getCode());
		}

		return otDetail;
	}
	
	public OTDetailBM convertOTDetailDM2BM( OtDetail otDetail){
		OTDetailBM otDetailBM = new OTDetailBM();
		
		otDetailBM.setOtId(otDetail.getOtId());
		otDetailBM.setName(otDetail.getName());
		if( otDetail.getBedMaster() != null){
			BedMaster bedMaster = otDetail.getBedMaster();
			otDetailBM.setBedNumber(bedMaster.getBedNumber());
		}
		
		CodeAndDescription coordinator = new CodeAndDescription();
		
		//TODO: call it form otDataModel Manager
		EntityBM entityBM = entityManager.getEntityDetail(Integer.parseInt(otDetail.getCoordinatorId()));
		
		coordinator.setCode(entityBM.getEntityId().toString());
		coordinator.setDescription(entityBM.getName());
		otDetailBM.setCoordinator(coordinator);
		otDetailBM.setCreatedDtm(otDetail.getCreatedDtm());
		otDetailBM.setCreatedBy(otDetail.getCreatedBy());
		
		return otDetailBM;
	}
	
	public SurgeryStatusTimeBM convertSurgeryStatusTimeDM2BM( OtSurgeryStatusTime surgeryStatusTime){
		SurgeryStatusTimeBM surgeryStatusTimeBM = new SurgeryStatusTimeBM();
		
		if( surgeryStatusTime.getOtSurgery() != null){
			surgeryStatusTimeBM.setSurgeryCode(surgeryStatusTime.getOtSurgery().getSurgeryCode());
		}
		if( surgeryStatusTime.getOtSurgeryStatus() != null){
			CodeAndDescription surgeryStatus = new CodeAndDescription();
			surgeryStatus.setCode(surgeryStatusTime.getOtSurgeryStatus().getStatusCode());
			surgeryStatus.setDescription(surgeryStatusTime.getOtSurgeryStatus().getDescription());
			
			surgeryStatusTimeBM.setSurgeryStatus(surgeryStatus);
		}
		surgeryStatusTimeBM.setSurgeonRequired(surgeryStatusTime.getSurgeonRequired());
		surgeryStatusTimeBM.setTime(surgeryStatusTime.getTime());
		
		surgeryStatusTimeBM.setContributeToScheduling(surgeryStatusTime.getOtSurgeryStatus().getContributeToScheduling());
		
		return surgeryStatusTimeBM;
	}
	
	public OtSurgeryStatusTime convertSurgeryStatusTimeBM2DM( SurgeryStatusTimeBM surgeryStatusTimeBM,
								OtSurgeryStatusTime existingSurgeryStatusTime){
		
		OtSurgeryStatusTime surgeryStatusTime = null;
		
		if( existingSurgeryStatusTime != null){
			surgeryStatusTime = existingSurgeryStatusTime;
		}
		else{
			surgeryStatusTime = new OtSurgeryStatusTime();
		}
		
		if( surgeryStatusTimeBM.getSurgeryCode() != null && !surgeryStatusTimeBM.getSurgeryCode().isEmpty()){
			OtSurgery surgery = otDataModelManager.getOTSurgery(surgeryStatusTimeBM.getSurgeryCode());
			surgeryStatusTime.setOtSurgery(surgery);
		}
		
		if( surgeryStatusTimeBM.getSurgeryStatus() != null){
			if( surgeryStatusTimeBM.getSurgeryStatus().getCode() != null && !surgeryStatusTimeBM.getSurgeryStatus().getCode().isEmpty()){
				OtSurgeryStatus otSurgeryStatus = otDataModelManager.getOTSurgeryStatus(surgeryStatusTimeBM.getSurgeryStatus().getCode());
				surgeryStatusTime.setOtSurgeryStatus(otSurgeryStatus);
			}
		}
		
		surgeryStatusTime.setTime(surgeryStatusTimeBM.getTime());
		surgeryStatusTime.setSurgeonRequired(surgeryStatusTimeBM.getSurgeonRequired());
		
		return surgeryStatusTime;
	}
	
	public OtSurgery convertSurgeryBM2DM( SurgeryBM surgeryBM , OtSurgery existingSurgery) throws Exception{
		OtSurgery surgery = null;
		if( existingSurgery != null){
			surgery = existingSurgery;
		}
		else{
			surgery = new OtSurgery();
		}
		
//		surgery.setSurgeryCode(surgeryBM.getSurgeryCode());
		surgery.setSurgeryName(surgeryBM.getServiceBM().getServiceName());
		if( surgeryBM.getSpecialtyCode() != null){
			surgery.setSpecialtyCode( surgeryBM.getSpecialtyCode().getCode());
		}
		
		surgery.setDoctorRefreshmentTime(surgeryBM.getDoctorRefreshmentTime());
		surgery.setTotalTimeRequired(surgeryBM.getTotalTimeRequired());
		
		if( surgeryBM.getTypeCode() != null ){
			ReferenceDataList referenceDataList = dataModelManager.getReferenceData(OTConstant.SERVICE_TYPE_SURGERY, surgeryBM.getTypeCode().getCode());
			if( referenceDataList == null){
				throw new Exception("Surgery type with surgery code " + surgeryBM.getTypeCode().getCode() +" does not exist");
			}
			
			surgery.setTypeCode(surgeryBM.getTypeCode().getCode());
		}
		
		return surgery;
	}
	
	public SurgeryBM convertSurgeryDM2BM( OtSurgery surgery){
		
		SurgeryBM surgeryBM = new SurgeryBM();
		
		surgeryBM.setSpecialtyCode(new CodeAndDescription(surgery.getSpecialtyCode(),null));
		surgeryBM.setSurgeryCode(surgery.getSurgeryCode());
		surgeryBM.setSurgeryName(surgery.getSurgeryName());
		surgeryBM.setDoctorRefreshmentTime(surgery.getDoctorRefreshmentTime());
		surgeryBM.setTotalTimeRequired(surgery.getTotalTimeRequired());
		surgeryBM.setCreatedDtm(surgery.getCreatedDtm());
		surgeryBM.setUserId(surgery.getCreatedBy());
		surgeryBM.setTypeCode(new CodeAndDescription(surgery.getTypeCode(),null));
		
		return surgeryBM;
	}
	
	
	public OTBookingBM  convertOTBookingDM2BM ( OtBooking otBooking ){
		
		OTBookingBM otBookingBM = new OTBookingBM();
		
		otBookingBM.setBookingFromDtm(otBooking.getBookingFromDtm());
		otBookingBM.setBookingToDtm(otBooking.getBookingToDtm());
		otBookingBM.setDoctorId(otBooking.getDoctorId());
		otBookingBM.setPatientId(otBooking.getPatientId());
		
		if( otBooking.getOtDetail() != null ){
			CodeAndDescription operationTheatre = new CodeAndDescription();
			operationTheatre.setCode(otBooking.getOtDetail().getOtId());
			operationTheatre.setDescription(otBooking.getOtDetail().getName());
			
			otBookingBM.setOperationTheater(operationTheatre);
		}
		
		if( otBooking.getOtSurgery() != null){
			CodeAndDescription surgery = new CodeAndDescription();
			
			surgery.setCode(otBooking.getOtSurgery().getSurgeryCode());
			surgery.setDescription(otBooking.getOtSurgery().getSurgeryName());
			
			otBookingBM.setSurgery(surgery);
		}
		
		otBookingBM.setReferenceNumber(otBooking.getReferenceNumber());
		otBookingBM.setReferenceType(otBooking.getReferenceType());
		
		return otBookingBM ;
	}
	
	public OtBooking convertOTBookingBM2DM(OTBookingBM otBookingBM , OtBooking existingOTBooking){
		OtBooking otBooking = null;
		if( existingOTBooking != null){
			otBooking = existingOTBooking;
		}
		else{
			otBooking = new OtBooking();
		}
		
		
		otBooking.setBookingFromDtm(otBookingBM.getBookingFromDtm());
		otBooking.setBookingToDtm(otBookingBM.getBookingToDtm());
		otBooking.setDoctorId(otBookingBM.getDoctorId());
		otBooking.setPatientId(otBookingBM.getPatientId());
		otBooking.setReferenceNumber(otBookingBM.getReferenceNumber());
		otBooking.setReferenceType(otBookingBM.getReferenceType());
		if( otBookingBM.getSurgery() != null ){
			otBooking.setOtSurgery(otDataModelManager.getOTSurgery(otBookingBM.getSurgery().getCode()));
		}
		if( otBookingBM.getOperationTheater() != null){
			otBooking.setOtDetail(otDataModelManager.getOTDetail(otBookingBM.getOperationTheater().getCode()));
		}
		
		return otBooking;
	}
	
	public OtPatientSurgery convertOTPatientSurgeryBM2DM( PatientSurgeryBM patientSurgeryBM , OtPatientSurgery existingPatientSurgery ){
		OtPatientSurgery patientSurgery = null;
		if( existingPatientSurgery != null){
			patientSurgery = existingPatientSurgery ;
		}
		else {
			patientSurgery = new OtPatientSurgery();
		}
		
		patientSurgery.setAnesthetistId(patientSurgeryBM.getAnesthetistId());
		patientSurgery.setCoordinatorId(patientSurgeryBM.getPatientId());
		patientSurgery.setDoctorId(patientSurgeryBM.getDoctorId());
		patientSurgery.setPatientId(patientSurgeryBM.getPatientId());
//		patientSurgery.setOtBookingNbr(patientSurgeryBM.getOtBookingNbr());
		
		if( patientSurgeryBM.getOperationTheater() != null){
			OtDetail otDetail = otDataModelManager.getOTDetail(patientSurgeryBM.getOperationTheater().getCode());
			patientSurgery.setOtDetail(otDetail);
		}

		if( patientSurgeryBM.getSurgery() != null){
			
			OtSurgery otSurgery = otDataModelManager.getOTSurgery(patientSurgeryBM.getSurgery().getCode());
			patientSurgery.setOtSurgery(otSurgery);
		}
		
		return patientSurgery;
	}
	
	public PatientSurgeryBM convertOTPatientSurgeryDM2BM( OtPatientSurgery patientSurgery){
		PatientSurgeryBM patientSurgeryBM = new PatientSurgeryBM();
		
		patientSurgeryBM.setPatientSurgeryId(patientSurgery.getPatientSurgeryId());
		patientSurgeryBM.setAnesthetistId(patientSurgery.getAnesthetistId());
		patientSurgeryBM.setCoordinatorId(patientSurgery.getCoordinatorId());
		patientSurgeryBM.setDoctorId(patientSurgery.getDoctorId());
		patientSurgeryBM.setPatientId(patientSurgery.getPatientId());
		
		if( patientSurgery.getOtBooking() != null){
			OtBooking otBooking = patientSurgery.getOtBooking();
			patientSurgeryBM.setOtBookingNbr(otBooking.getOtBookingNbr());
			patientSurgeryBM.setReferenceNbr(otBooking.getReferenceNumber());
			patientSurgeryBM.setReferenceType(otBooking.getReferenceType());
		}
		
		
		if( patientSurgery.getOtSurgery() != null){
			OtSurgery otSurgery = patientSurgery.getOtSurgery();
			CodeAndDescription surgery = new CodeAndDescription();
			surgery.setCode(otSurgery.getSurgeryCode());
			surgery.setDescription(otSurgery.getSurgeryName());
			
			patientSurgeryBM.setSurgery(surgery);
		}
		
		
		if(patientSurgery.getPatientId() != null){
			Patient patient = dataModelManager.getPatient(patientSurgery.getPatientId());
			patientSurgeryBM.setPatientName(patient.getFullName());
		}
		
		if( patientSurgery.getDoctorId() != null){
			Doctor doctor = dataModelManager.getDoctor(patientSurgery.getDoctorId());
			patientSurgeryBM.setDoctorName(doctor.getFullName());
		}
		
		if( patientSurgery.getOtDetail() != null){
			OtDetail otDetail = patientSurgery.getOtDetail();
			CodeAndDescription otDetails = new CodeAndDescription();
			otDetails.setCode(otDetail.getOtId());
			otDetails.setDescription(otDetail.getName());
			patientSurgeryBM.setOperationTheater(otDetails);
		}
		
//		patientSurgeryBM.setOtBookingNbr(patientSurgery.getOtBookingNbr());
		patientSurgeryBM.setPatientSurgeryId(patientSurgery.getPatientSurgeryId());
		patientSurgeryBM.setSurgeryDate(patientSurgery.getSurgeryDate());
		
		if( patientSurgery.getStatusCode() != null && !patientSurgery.getStatusCode().isEmpty()){
			
			OtSurgeryStatus surgeryStatus = otDataModelManager.getOTSurgeryStatus(patientSurgery.getStatusCode());
			CodeAndDescription surgeryStatusCode = new CodeAndDescription();
			surgeryStatusCode.setCode(surgeryStatus.getStatusCode());
			surgeryStatusCode.setDescription(surgeryStatus.getDescription());
			
			patientSurgeryBM.setStatusCode(surgeryStatusCode);
		}
		
		return patientSurgeryBM;
	}
	
	public OtNotesFieldsBM convertOTNotesConfiguration2OTFieldsBM( OtNotesConfiguration otNotesConfiguration ){
		
		OtNotesFieldsBM notesFieldsBM = new OtNotesFieldsBM();
		
		notesFieldsBM.setFieldCode(otNotesConfiguration.getFieldCode());
		notesFieldsBM.setDescription(otNotesConfiguration.getDescription());
		notesFieldsBM.setFieldName(otNotesConfiguration.getFieldName());
		notesFieldsBM.setFieldType(otNotesConfiguration.getFieldType());
		
		return notesFieldsBM;
	}
	
	
	public void setIpDataModelManager(IPDataModelManager ipDataModelManager) {
		this.ipDataModelManager = ipDataModelManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setOtDataModelManager(OTDataModelManager otDataModelManager) {
		this.otDataModelManager = otDataModelManager;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}
	
}
