package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.AppointmentBM;
import in.wtc.hcis.bo.appointment.AppointmentManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.ui.model.TreatmentSummary;
import in.wtc.ui.utils.Constants;
import in.wtc.ui.utils.Converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * @author Sandeep Kumar
 *
 */
public class TreatmentHistoryController {
	
	private AppointmentManager appointmentManager;
	
    public ListRange getTreatmentDetails(Map<String, String> values,
	                                     int start, 
	                                     int count, 
	                                     String orderBy){
		Date fromDate = Converters.getDate(values.get("tretamentForm")); 
		Date toDate = Converters.getDate(values.get("treatmentTo"));
		Integer patientId = null;
		if( values.get("patientId")!= null && !values.get("patientId").equals("")) {
			patientId = new Integer(values.get("patientId"));
		}
//		Boolean inPatient = Converters.convertChkValueFromString2Boolean(values.get("inPatient"));
//		Boolean outPatient = Converters.convertChkValueFromString2Boolean(values.get("outPatient"));
		
    List<AppointmentBM> treatmentSummaryList = appointmentManager.
                                               getAppointments( fromDate,
		  														toDate, 
		  														patientId, 
		  														values.get("patientName"), 
		  														values.get("doctorName"), 
		  														null,
		  					                                    null,
		  					                                    null,
		  					                                    null);
	  
	List<TreatmentSummary> treatmentSummary = new ArrayList<TreatmentSummary>();
	List<TreatmentSummary> pageSizeData = new ArrayList<TreatmentSummary>();
	 ListRange listrange = new ListRange();
	 if(treatmentSummaryList!=null && treatmentSummaryList.size()>0) {
		 int rowIndex = Constants.GRID_ROW_INDEX;
			  
			for( AppointmentBM treatment : treatmentSummaryList ){
				  TreatmentSummary trtmntSummary = new TreatmentSummary();
				  trtmntSummary.setSerialNo(rowIndex);
				  trtmntSummary.setAppointmentNumber(treatment.getAppointmentNumber());
				  trtmntSummary.setAdmissiondate(null);
				  trtmntSummary.setAppointmentAgenda(treatment.getAppointmentAgenda());
				  trtmntSummary.setAppointmentDate(treatment.getAppointmentDate());
				  trtmntSummary.setAppointmentRemarks(treatment.getAppointmentRemarks());
				  trtmntSummary.setDischargeDate(null);
				  trtmntSummary.setIoPatient(Constants.OUT_PATIENT);
				  trtmntSummary.setName(treatment.getFirstName() + " " + treatment.getMiddleName() + " " + treatment.getLastName());
				  trtmntSummary.setPatientId(treatment.getPatientId().intValue());
				  
				  if( treatment.getPrimaryDoctor() != null){
					  trtmntSummary.setPrimaryDoctor(treatment.getPrimaryDoctor().getDescription());
					  trtmntSummary.setPrimaryDoctorId(treatment.getPrimaryDoctor().getCode());
				  }
				  
				  if( treatment.getSpeaciality() != null){
					  trtmntSummary.setSpecialty( treatment.getSpeaciality().getDescription());
					  trtmntSummary.setSpecialtyCode( treatment.getSpeaciality().getCode());
				  }
				  
				  treatmentSummary.add(trtmntSummary);
				  rowIndex++;
			}
			int index = 0;
			while( (start+index < start + count) && (treatmentSummary.size() > start+index) ){
					pageSizeData.add(treatmentSummary.get(start+index));
					index++;
				}
		  listrange.setData(pageSizeData.toArray());
		  listrange.setTotalSize(treatmentSummary.size());
	 } else {
		 Object [] obj = new Object[0];
		 listrange.setData(obj);
		 listrange.setTotalSize(0);
		
	 }
	
	 return listrange;
	
  }

	public void setAppointmentManager(AppointmentManager appointmentManager) {
		this.appointmentManager = appointmentManager;
	}
}
