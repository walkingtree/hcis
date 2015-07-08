/**
 * 
 */
package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ObservationBM;
import in.wtc.hcis.bm.base.PrescriptionBM;
import in.wtc.hcis.bm.base.PrescriptionLineItemBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.observations.ObservationManager;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.prescription.PrescriptionManager;
import in.wtc.ui.model.MedicalPrescriptionModel;
import in.wtc.ui.model.MedicalPrescriptionSummaryModel;
import in.wtc.ui.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sandeep Kumar
 *
 */
public class MedicalPrescriptionController {
	
	private PrescriptionManager prescriptionManager;
	private ObservationManager observationManager;
	
	public ListRange getPrescriptionDetails( 
											 Integer appointmentNo,
											 int start, 
											 int count, 
											 String orderBy){
		
		ListRange listrange = new ListRange();
		
		Integer appointmentCode = null;
		if( ! appointmentNo.equals("")){
			appointmentCode = new Integer( appointmentNo );
		}
		
			
			List<PrescriptionBM> prescriptionList = prescriptionManager.getMedicalPrescriptions( appointmentCode );
			List<MedicalPrescriptionSummaryModel> medicalPrescriptionList = new ArrayList<MedicalPrescriptionSummaryModel>();
			
			int serialNo = Constants.GRID_ROW_INDEX;
			if( prescriptionList!= null && prescriptionList.size()>0 ) {
				for(PrescriptionBM prescription : prescriptionList){
					Iterator<PrescriptionLineItemBM>  iter = prescription.getPrescriptionLineItemSet().iterator();
					while(iter.hasNext()) {
						PrescriptionLineItemBM prescriptionLineItem = iter.next();
						MedicalPrescriptionSummaryModel prescriptionDetails = new MedicalPrescriptionSummaryModel();
						prescriptionDetails.setDosage(prescriptionLineItem.getDosage());
						prescriptionDetails.setMedicine(new CodeAndDescription(prescriptionLineItem.getMedicineCode(),null));
						prescriptionDetails.setRemarks(prescriptionLineItem.getRemarks());
						prescriptionDetails.setRepeats(prescriptionLineItem.getRepeats());
						prescriptionDetails.setSerialNo(serialNo);
						prescriptionDetails.setStrength(prescriptionLineItem.getStrength());
						prescriptionDetails.setForm( prescriptionLineItem.getForm());
						prescriptionDetails.setPrescriptionNumber(prescription.getPrescriptionNumber());	
						prescriptionDetails.setAppointmentNumber( prescription.getAppointmentNumber());
						medicalPrescriptionList.add(prescriptionDetails);
						serialNo++;
					}
				}
				
				listrange.setData(medicalPrescriptionList.toArray());
				listrange.setTotalSize(medicalPrescriptionList.size());
			} else {
				listrange.setData( new Object[0]);
				listrange.setTotalSize(0);
			}
		
		return listrange;
	}
	
	public ListRange getPrescriptionDetailsForPatient( Integer patientId,
														int start, 
														int count, 
														String orderBy){
		ListRange listrange = new ListRange();
		
		List<PrescriptionBM> prescriptionList = prescriptionManager.getMedicalPrescriptionsForPatient( patientId );
		List<MedicalPrescriptionSummaryModel> medicalPrescriptionList = new ArrayList<MedicalPrescriptionSummaryModel>();
		
		int serialNo = Constants.GRID_ROW_INDEX;
		if( prescriptionList!= null && prescriptionList.size()>0 ) {
			for(PrescriptionBM prescription : prescriptionList){
				Iterator<PrescriptionLineItemBM>  iter = prescription.getPrescriptionLineItemSet().iterator();
				while(iter.hasNext()) {
					PrescriptionLineItemBM prescriptionLineItem = iter.next();
					MedicalPrescriptionSummaryModel prescriptionDetails = new MedicalPrescriptionSummaryModel();
					prescriptionDetails.setDosage(prescriptionLineItem.getDosage());
					prescriptionDetails.setMedicine(new CodeAndDescription(prescriptionLineItem.getMedicineCode(),null));
					prescriptionDetails.setRemarks(prescriptionLineItem.getRemarks());
					prescriptionDetails.setRepeats(prescriptionLineItem.getRepeats());
					prescriptionDetails.setSerialNo(serialNo);
					prescriptionDetails.setStrength(prescriptionLineItem.getStrength());
					prescriptionDetails.setForm( prescriptionLineItem.getForm());
					prescriptionDetails.setPrescriptionNumber(prescription.getPrescriptionNumber());	
					prescriptionDetails.setAppointmentNumber( prescription.getAppointmentNumber());
					medicalPrescriptionList.add(prescriptionDetails);
					serialNo++;
				}
			}
			
			listrange.setData(medicalPrescriptionList.toArray());
			listrange.setTotalSize(medicalPrescriptionList.size());
		} else {
			listrange.setData( new Object[0]);
			listrange.setTotalSize(0);
		}
		
		return listrange;
		
	}
	public ListRange getObservations (Integer appointmentNo,
										 int start, 
										 int count, 
										 String orderBy) {
		ListRange listRange = new ListRange();
		List<ObservationBM> observationList = observationManager.getObservations(appointmentNo);
		if(observationList!=null && observationList.size()>0) {
			listRange.setData(observationList.toArray());
			listRange.setTotalSize(observationList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
	}
	
	public ListRange getObservationsForPatient( Integer patientId,
												int start, 
												int count, 
												String orderBy) {
		ListRange listRange = new ListRange();
		List<ObservationBM> observationList = observationManager.getObservationsForPatient(patientId);
		if(observationList!=null && observationList.size()>0) {
			listRange.setData(observationList.toArray());
			listRange.setTotalSize(observationList.size());
		}else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}
	public ListRange getPatientInfo( String patientName,
								     String patientId, 
								     String doctorName,
								     String patientType, 
								     String consultationDate,
								     String admissionDate, 
								     String dischargeDate,
								     String reasonForTrtmnt, 
								     String doctorRemark,
								     String doctorId,
								     String departmentName,
								     int start, 
                                     int count, 
                                     String orderBy
								     ){
		
		ListRange listrange = new ListRange();
		
		try {
			MedicalPrescriptionModel patientInfo = new MedicalPrescriptionModel();
			
			int consDate = (new Date(consultationDate)).getYear() + 1900;
			
			if( consDate == 1970){
				patientInfo.setConsultationDate(null);
			}else{
//				patientInfo.setConsultationDate(consultationDate);
			}
			
			patientInfo.setDepartmentName(departmentName);
			patientInfo.setDoctorAddress(null);
			patientInfo.setDoctorName(doctorName);
			patientInfo.setPatientId(patientId);
			patientInfo.setPatientType(patientType);
			patientInfo.setPatientName(patientName);
			
			Object[] prescriptionDetailsObj = new Object[1];
			prescriptionDetailsObj[0] = patientInfo;
			
			listrange.setData(prescriptionDetailsObj);
			
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return listrange;
	}
	
	public void removePrescription( List<MedicalPrescriptionSummaryModel> prescriptionList){
		for(MedicalPrescriptionSummaryModel prescription : prescriptionList){
			System.out.println( prescriptionList );
		}
	}
	
	public void savePrescription(List<MedicalPrescriptionSummaryModel> prescriptionList){
		for( MedicalPrescriptionSummaryModel prescription : prescriptionList){
			
		}
	}

	public void setPrescriptionManager(PrescriptionManager prescriptionManager) {
		this.prescriptionManager = prescriptionManager;
	}

	public void setObservationManager(ObservationManager observationManager) {
		this.observationManager = observationManager;
	}
}
