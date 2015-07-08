/**
 * 
 */
package in.wtc.hcis.ip.bo.order;

import in.wtc.hcis.bm.base.DoctorBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.PaymentReceiptBM;
import in.wtc.hcis.bm.base.ReceivableBM;
import in.wtc.hcis.bm.ip.AdmissionBM;
import in.wtc.hcis.bm.ip.PatientBasicDetailBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.ReportsDetail;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.wtc.hcis.da.Patient;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionStatus;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.extn.AdmissionDAOExtn;
import com.wtc.hcis.ip.da.extn.BedMasterDAOExtn;
import com.wtc.report.ReportManager;
import com.wtc.report.model.ReportDetail;

/**
 * @author Alok Ranjan
 * Admission orders are written instructions by the doctors for the care and treatment of the patient upon entry into the hospital.
 * Some of the example contents (also called services) of admission orders could be
 * 1) Admission agreement to be signed by patient or his / her representative
 * 2) Advance directive in case patience has a chance to become incapacitated
 * 3) Printing of allergy information bracelet
 * 4) Bed Assignments to the patient
 * 5) Insurance processing 
 * 6) Preparing patient's identification labels
 * 7) 
 */
public class AdmissionOrder  implements AdmissionManager {

	private AdmissionDAOExtn admissionDAO;
	private BedMasterDAOExtn bedMasterDAO;
	private DataModelManager coreDataModelManager;
	private IPDataModelManager dataModelManager;
	private IPDataModelConverter converter;
	private PatientManager patientManager;
	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	
	private ReportManager reportManager = null;
	private DoctorManager doctorManager = null;
	
	private final String REFERENCE_TYPE_IPD = "IPD";

	private ResourceBundle ReportsConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.ReportDetail", Locale.getDefault());

	public ListRange findAdmissions(Integer admissionNbr, Integer doctorId,
			Integer patientId, String patientName, String admissionStatusCd,
			Date admissionFromDt, Date admissionToDate, String entryPointName,
			String admissionReason, String agenda,
			Date expectedDischargeFromDate, Date expectedDischargeToDate,
			Date dischargeFromDate, Date dischargeToDate,
			String nursingUnitTypeCd, String bedNumber, int start, int count,
			String orderBy) {
		

		
		
		
		List<AdmissionBM> admissionBMList =  this.getAdmissions( admissionNbr, doctorId, patientId,
													patientName, admissionStatusCd, admissionFromDt,
													admissionToDate, entryPointName, admissionReason,
													agenda, expectedDischargeFromDate, expectedDischargeToDate,
													dischargeFromDate, dischargeToDate, nursingUnitTypeCd, bedNumber);
							
		
		ListRange listRange = new ListRange();
		
		List<AdmissionBM> pageSizeData = new ArrayList<AdmissionBM>();
		int index = 0;
		if (admissionBMList != null && admissionBMList.size() > 0) {
		while( (start+index < start + count) && (admissionBMList.size() > start+index) ){
			
			AdmissionBM admissionBM = admissionBMList.get(start+index);
			admissionBM.setSeqNbr( start + index +1 );
			pageSizeData.add( admissionBM );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(admissionBMList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
	return listRange;
	
		
	}

	public List<AdmissionBM> getAdmissions(Integer admissionNbr,
			Integer doctorId, Integer patientId, String patientName,
			String admissionStatusCd, Date admissionFromDt,
			Date admissionToDate, String entryPointName,
			String admissionReason, String agenda,
			Date expectedDischargeFromDate, Date expectedDischargeToDate,
			Date dischargeFromDate, Date dischargeToDate,
			String nursingUnitTypeCd, String bedNumber) {

		List<Admission> admissionList = admissionDAO.getAdmissions( admissionNbr,
																	doctorId, 
										                            patientId,
										                            admissionStatusCd, 
										                            admissionFromDt,
										                            admissionToDate, 
										                            entryPointName,
										                            admissionReason, 
										                            agenda,
										                            expectedDischargeFromDate, 
										                            expectedDischargeToDate,
										                            dischargeFromDate, 
										                            dischargeToDate,
										                            nursingUnitTypeCd );


//		_____________________________________________
		
		if ( admissionList != null && !admissionList.isEmpty() ) {
			
			List<AdmissionBM>admissionBMList = new ArrayList<AdmissionBM>();
			
			for ( Admission admission : admissionList ) {
				
				boolean nameMatched = true ;
				if ( patientName != null && !patientName.isEmpty()) {
					if ( admission.getPatientId() != null ) {
						Patient patient = coreDataModelManager.getPatient(  admission.getPatientId() );
						String[] patientNameArray = patientName.split(" " );
						
						for ( int nameParts = 0; nameParts < patientNameArray.length; nameParts++ ) {
							if ( ( patient.getFirstName() != null  && 
								   patient.getFirstName().equals( patientNameArray[nameParts] ) ) ||
								 ( patient.getLastName() != null  && 
								   patient.getLastName().equals( patientNameArray[nameParts] ) )  ||
								 ( patient.getMiddleName() != null  && 
								   patient.getMiddleName().equals( patientNameArray[nameParts] ) ) ) {
								
								nameMatched = true; //  part of the name has actually matched
							} else {
								nameMatched = false;
								break;
							}
						}
					}
				}
				
				boolean bedMached = true;
				if( bedNumber != null && !bedNumber.isEmpty() ){
					BedMaster bedMaster = dataModelManager.getBedMaster( bedNumber );
					if( bedMaster.getPatientId() != null && bedMaster.getPatientId().equals(admission.getPatientId())){
						bedMached = true;
					}else{
						bedMached = false;
					}
				}
				if( nameMatched && bedMached) {
					
					AdmissionBM admissionBM = converter.convertAdmissionDM2BM( admission);
					
//					List<DoctorOrderDetails> doctorOrderDetailsList = doctorOrderDetailsDAO.findByProperty( "id.orderNbr", admission.getDoctorOrder().getDoctorOrderNbr() );
					
//					if( doctorOrderDetailsList != null && !doctorOrderDetailsList.isEmpty() ){
//						 List<DoctorOrderDetailsBM> doctorOrderDetailsBMList = new ArrayList<DoctorOrderDetailsBM>();
//						for( DoctorOrderDetails orderDetails : doctorOrderDetailsList ){
//							
//							doctorOrderDetailsBMList.add( converter.convertDoctorOrderDetailsDM2BM(orderDetails));
//						}
//						admissionBM.setDoctorOrderDetailsList(doctorOrderDetailsBMList);
//					}
//					getting bedNuber
					List<BedMaster> bedMasterList = bedMasterDAO.findByPatientId( admission.getPatientId() );
					
					if( bedMasterList!= null && !bedMasterList.isEmpty() ){
						admissionBM.setBedNumber( bedMasterList.get(0).getBedNumber() );
					}
					
					admissionBMList.add( admissionBM );
					
				}
			}
			return admissionBMList;
		}else{
			return null;			
		}
	}



	public void confirmAdmissions(Integer[] admissionReqNumberList) {
		try{
		if(admissionReqNumberList!=null && admissionReqNumberList.length>0){
			for(Integer admissionReqNbr : admissionReqNumberList){
				Admission admission = dataModelManager.getAdmission(admissionReqNbr);
				if(admission.getAdmissionStatus()!=null && 
					admission.getAdmissionStatus().getAdmissionStatusCd().equals(DoctorOrderConstants.ADMISSION_STATUS_REQUESTED)){
					Integer admissionNbr = admissionDAO.getNextAdmissionNbr();
					admission.setAdmissionNbr(admissionNbr);
					AdmissionStatus admissionStatus = dataModelManager.getAdmissionStatus(DoctorOrderConstants.ADMISSION_STATUS_ADMITTED);
					if(admissionStatus!=null){
						admission.setAdmissionStatus(admissionStatus);
					}
					admissionDAO.attachDirty(admission);
				}else{
					throw new Exception("Admission Request number '"+admissionReqNbr+"' should be in REQUESTED Status");
				}
				
			}
		}
		}catch(Exception exception){
			Fault fault = new Fault( ApplicationErrors.CONFIRM_ADMISSION_FAILED );
			throw new HCISException(fault,exception);
		}
	}

	/**
	 * This method provides patient information with bed and billing/accounting amount.
	 * The method needs to placed on some suitable place. 
	 */
	public PatientBasicDetailBM  getPatientBasicDetails( Integer admissionReqNbr ){
		
		try {
			Admission admission = dataModelManager.getAdmission(admissionReqNbr);
			
			PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM(admission.getPatientId());
			
			List<BedMaster> bedMasterList = bedMasterDAO.getCurrentlyOccupiedBedBeds(admissionReqNbr, admission.getPatientId());
			PatientBasicDetailBM basicDetailBM = new PatientBasicDetailBM();
			
			if( patientLiteBM != null  ){
				 
				if( bedMasterList != null ){
					basicDetailBM.setBedNumber(bedMasterList.get(0).getBedNumber());//TODO: change this
				}

				basicDetailBM.setDateOfBirth(patientLiteBM.getDateOfBirth());
				basicDetailBM.setFirstName(patientLiteBM.getFirstName());
				basicDetailBM.setMiddleName(patientLiteBM.getMiddleName());
				basicDetailBM.setPatientId( admission.getPatientId() );
				basicDetailBM.setTitle( patientLiteBM.getTitle());
				
/*				ReceivableAmntData amntData = accountantIntegrationManager.getReceivableAmounts(admissionReqNbr.toString(), REFERENCE_TYPE_IPD );
				basicDetailBM.setTotalAmount(amntData.getTotalAmount());
				basicDetailBM.setTotalRemngAmount(amntData.getRemainingAmount());
//			basicDetailBM.setDepositAmount(amntData);
*/				
			}
			
			return basicDetailBM;
		} catch (HCISException e) {
			Fault fault = new Fault( ApplicationErrors.READ_PATIENT_FAILED );
			throw new HCISException(fault,e );
		}		
		
	}
	
	public String generatePatientBill(Integer admissionReqNbr, Integer billNumber) throws HCISException{
		try {
			
			Admission admission = dataModelManager.getAdmission(admissionReqNbr);
			
			PatientLiteBM patientLiteBM = patientManager.getPatientLiteBM(admission.getPatientId());
			
			DoctorBM doctorBM = doctorManager.getDoctorDetail(admission.getDoctorId());
			
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			reportParamMap.put("patientName", patientLiteBM.getFirstName() + " " + patientLiteBM.getMiddleName() + " " + patientLiteBM.getLastName());
			reportParamMap.put("admissionNumber", admission.getAdmissionReqNbr().toString());
			reportParamMap.put("referralDoctorName", "");
			reportParamMap.put("consultantName", doctorBM.getFirstName() + " " + doctorBM.getMiddleName() + " " + doctorBM.getLastName());
			reportParamMap.put("age", patientLiteBM.getDateOfBirth()!=null ? patientLiteBM.getDateOfBirth().toString() : "");
			reportParamMap.put("gender", patientLiteBM.getGender()!=null ? patientLiteBM.getGender().getDescription() : "");
			reportParamMap.put("doa", admission.getAdmissionDt()!=null ? admission.getAdmissionDt().toString() : "");
			reportParamMap.put("dod", admission.getDischargeDtm() != null ? admission.getDischargeDtm().toString() : "");
			reportParamMap.put("bill-date", (new Date()).toString());
			
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(ReportsConfig.getString(ReportsDetail.PATIENT_BILL_REPORT_NAME));
			reportDetail.setFileName(ReportsConfig.getString(ReportsDetail.PATIENT_BILL_REPORT_FILENAME));
			reportDetail.setReportsDirPath(ReportsConfig.getString(ReportsDetail.REPORTS_DIR_PATH));
			String sourceXMLFile = ReportsConfig.getString(ReportsDetail.PATINET_BILL_REPORT_PATH) + billNumber + ".xml";
			
			String retStr = reportManager.generateReport(reportDetail, reportParamMap, sourceXMLFile);
			
			return retStr;
				
		} catch (Exception exception) {
			Fault fault = new Fault(ApplicationErrors.ERROR_RUNNING_REPORT);

			throw new HCISException(fault.getFaultMessage()
					+ exception.getMessage(), fault.getFaultCode(), fault
					.getFaultType());
		}
		
	}

	public ListRange getReceivable(Integer admissionReqNbr,int start,int count,String orderBy) {
		
		Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(admissionReqNbr,
													"IPD");//TODO:
		
		List<ReceivableBM> receivableBMList = eagleIntegration.getReceivables(businessPartnerId);
		
		
		
		ListRange listRange = new ListRange();
		
		List<ReceivableBM> pageSizeData = new ArrayList<ReceivableBM>();
		int index = 0;
		if (receivableBMList != null && receivableBMList.size() > 0) {

			while( (start+index < start + count) && (receivableBMList.size() > start+index) ){
			
			pageSizeData.add( receivableBMList.get(start+index) );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(receivableBMList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;

		
	}

public ListRange getPaymentReceipt(Integer admissionReqNbr, String invoiceId, int start,int count,String orderBy) {
		
		Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(admissionReqNbr,
													"IPD");//TODO:
		
		List<PaymentReceiptBM> paymentReceiptBMList = eagleIntegration.getPaymentReceipt(
													businessPartnerId, invoiceId);
		
		
		
		ListRange listRange = new ListRange();
		
		List<PaymentReceiptBM> pageSizeData = new ArrayList<PaymentReceiptBM>();
		int index = 0;
		if (paymentReceiptBMList != null && paymentReceiptBMList.size() > 0) {

			while( (start+index < start + count) && (paymentReceiptBMList.size() > start+index) ){
			
			pageSizeData.add( paymentReceiptBMList.get(start+index) );
				index++;
		}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(paymentReceiptBMList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;

		
	}

	public Integer getBusinessPartnerId(Integer admissionReqNbr){
	
	return eagleIntegration.getBusinessPartnerId(admissionReqNbr, RegistrationConstants.REFERENCE_TYPE_IPD);
	}
	
	public Integer createReceipt(Integer admissionReqNbr, Double amount,
			Map<String, String> attributeMap, String createdBy,String invoiceId) {

		
		Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(admissionReqNbr,
											RegistrationConstants.REFERENCE_TYPE_IPD);
		
		return  eagleIntegration.createReceipt(businessPartnerId, amount, attributeMap, createdBy,invoiceId, null);
	}

	
	public void setAdmissionDAO(AdmissionDAOExtn admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public void setBedMasterDAO(BedMasterDAOExtn bedMasterDAO) {
		this.bedMasterDAO = bedMasterDAO;
	}

	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}

	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setConverter(IPDataModelConverter converter) {
		this.converter = converter;
	}

	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}
	
	
}
