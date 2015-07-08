package in.wtc.hcis.bo.prescription;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PrescriptionBM;
import in.wtc.hcis.bm.base.PrescriptionLineItemBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.AppointmentsDAO;
import com.wtc.hcis.da.Medicines;
import com.wtc.hcis.da.Prescription;
import com.wtc.hcis.da.PrescriptionMedicineAssoc;
import com.wtc.hcis.da.PrescriptionMedicineAssocDAO;
import com.wtc.hcis.da.PrescriptionMedicineAssocId;
import com.wtc.hcis.da.extn.PrescriptionDAOExtn;

/**
 * @author Ajit Kumar
 *
 */
public class PrescriptionManagerImpl implements PrescriptionManager {
	
	PrescriptionDAOExtn prescriptionDAO;
	PrescriptionMedicineAssocDAO prescriptionMedicineAssocDAO;
	AppointmentsDAO appointmentsDAO;

	public void addPrescription(List<PrescriptionBM> prescriptionBMList) {
		try {
			if( prescriptionBMList != null && !prescriptionBMList.isEmpty()){
				for (PrescriptionBM tmpPrescriptionBM : prescriptionBMList) {
					List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = convertPrescriptionBM2DM(tmpPrescriptionBM);
					for (PrescriptionMedicineAssoc tmpPrescriptionMedicineAssoc : prescriptionMedicineAssocList) {
						prescriptionMedicineAssocDAO.save(tmpPrescriptionMedicineAssoc);
					}
				}
			}
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.PRESCRIPTION_ADD_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public List<PrescriptionBM> getMedicalPrescriptions(Integer appointmentNumber) {
		try {
			List<Prescription> prescriptionList =  prescriptionDAO.findPrescriptions(appointmentNumber);
			List<PrescriptionBM> prescriptionBMList = convertPrescriptionsDM2BM(prescriptionList);
			
			return prescriptionBMList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.PRESCRIPTION_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public PrescriptionBM modifyMedicalPrescription(PrescriptionBM modifiedPrescriptionBM) {
		try {
			Prescription prescription = prescriptionDAO.findById(new Integer(modifiedPrescriptionBM.getPrescriptionNumber()));
			if(prescription!= null) {
				List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = prescriptionMedicineAssocDAO.findByProperty("id.prescriptionNumber", 
																					modifiedPrescriptionBM.getPrescriptionNumber());
				if(prescriptionMedicineAssocList!=null && prescriptionMedicineAssocList.size()>0) {
				for( PrescriptionMedicineAssoc tmpPresMedicineAssoc: prescriptionMedicineAssocList ) {
				prescriptionMedicineAssocDAO.delete(tmpPresMedicineAssoc);
				}
				}
				prescriptionDAO.delete(prescription);
			}
			
			List<PrescriptionBM> prescriptionBMList = new ArrayList<PrescriptionBM>();
			prescriptionBMList.add(modifiedPrescriptionBM);
			addPrescription(prescriptionBMList);
			return modifiedPrescriptionBM;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.PRESCRIPTION_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void removePrescription(List<PrescriptionBM> prescriptionBMList) {
		try {
			for (PrescriptionBM tmpPrescriptionBM : prescriptionBMList) {
				for (PrescriptionLineItemBM tmpPrescriptionLineItem : tmpPrescriptionBM.getPrescriptionLineItemSet()) {
					List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = prescriptionMedicineAssocDAO.findByProperty("id.prescriptionNumber", 
							tmpPrescriptionBM.getPrescriptionNumber());
					if(prescriptionMedicineAssocList!=null && prescriptionMedicineAssocList.size()>0) {
						for( PrescriptionMedicineAssoc tmpPresMedicineAssoc: prescriptionMedicineAssocList ) {
							prescriptionMedicineAssocDAO.delete(tmpPresMedicineAssoc);
							}
							}
					}
					
					Prescription prescription = prescriptionDAO.findById(new Integer(tmpPrescriptionBM.getPrescriptionNumber()));
					prescriptionDAO.delete(prescription);
				}
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.PRESCRIPTION_REMOVE_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	private List<PrescriptionMedicineAssoc> convertPrescriptionBM2DM(PrescriptionBM prescriptionBM) {
		
		Prescription prescription = new Prescription();
		prescription.setPrescriptionDate(Calendar.getInstance().getTime());
		prescription.setPrescriptionText(prescriptionBM.getPrescriptionText());
		if(prescriptionBM.getAppointmentNumber()!=null && !prescriptionBM.getAppointmentNumber().equals("")) {
			prescription.setSrcRefNumber(new Integer(prescriptionBM.getAppointmentNumber()));
		}
			prescriptionDAO.save(prescription);
			
		List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = new ArrayList<PrescriptionMedicineAssoc>(0);
		
//		Iterator<PrescriptionLineItemBM> iter = prescriptionBM.getPrescriptionLineItemSet().iterator();
		
		//while(iter.hasNext()) {
			//PrescriptionLineItemBM prescriptionLineItem = iter.next();
		for(PrescriptionLineItemBM prescriptionLineItem : prescriptionBM.getPrescriptionLineItemSet()){
			
			if(prescriptionLineItem == null){
				continue;
			}
			
			PrescriptionMedicineAssoc prescriptionMedicineAssoc = new PrescriptionMedicineAssoc();
			
			PrescriptionMedicineAssocId prescriptionMedicineAssocId = new PrescriptionMedicineAssocId();
			prescriptionMedicineAssocId.setMedicineCode(prescriptionLineItem.getMedicineCode());
			prescriptionMedicineAssocId.setPrescriptionNumber(prescription.getPrescriptionNumber());
			
			prescriptionMedicineAssoc.setId(prescriptionMedicineAssocId);
			prescriptionMedicineAssoc.setDosage(prescriptionLineItem.getDosage());
			prescriptionMedicineAssoc.setRemarks(prescriptionLineItem.getRemarks());
			prescriptionMedicineAssoc.setRepeats(prescriptionLineItem.getRepeats());
			
			Medicines medicines = new Medicines();
			medicines.setMedicineCode(prescriptionLineItem.getMedicineCode());
			
			prescriptionMedicineAssoc.setMedicines(medicines);
			prescriptionMedicineAssoc.setPrescription(prescription);
			
			prescriptionMedicineAssocList.add(prescriptionMedicineAssoc);
		}
		
		return prescriptionMedicineAssocList;
	}
	
	private List<PrescriptionBM> convertPrescriptionsDM2BM(List<Prescription> prescriptionList) {
		List<PrescriptionBM> prescriptionBMList = new ArrayList<PrescriptionBM>();
		
		for (Prescription tmpPrescription : prescriptionList) {
			PrescriptionBM tmpPrescriptionBM = new PrescriptionBM();
			
			tmpPrescriptionBM.setAppointmentNumber(tmpPrescription.getSrcRefNumber().toString());
			tmpPrescriptionBM.setPrescriptionNumber(tmpPrescription.getPrescriptionNumber().toString());
			tmpPrescriptionBM.setPrescriptionText(tmpPrescription.getPrescriptionText());
			
			List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = prescriptionMedicineAssocDAO.findByProperty("id.prescriptionNumber", tmpPrescription.getPrescriptionNumber());
			if(prescriptionMedicineAssocList!=null) {
				for ( PrescriptionMedicineAssoc temPrescriptionMedicineAssoc : prescriptionMedicineAssocList ) {
					
					PrescriptionLineItemBM prescriptionLineItem = new PrescriptionLineItemBM();
					prescriptionLineItem.setDosage(temPrescriptionMedicineAssoc.getDosage());
					prescriptionLineItem.setMedicineCode(temPrescriptionMedicineAssoc.getId().getMedicineCode());
					prescriptionLineItem.setForm(new CodeAndDescription(temPrescriptionMedicineAssoc.getMedicines().getMedicineType().getMedicineTypeCode(),
							temPrescriptionMedicineAssoc.getMedicines().getMedicineType().getDescription())); 
					prescriptionLineItem.setPrescriptionDate(tmpPrescription.getPrescriptionDate());
					prescriptionLineItem.setRemarks(temPrescriptionMedicineAssoc.getRemarks());
					prescriptionLineItem.setRepeats(temPrescriptionMedicineAssoc.getRepeats());
					prescriptionLineItem.setStrength(temPrescriptionMedicineAssoc.getMedicines().getStrength()); 
					
					tmpPrescriptionBM.getPrescriptionLineItemSet().add(prescriptionLineItem);
					}
				prescriptionBMList.add(tmpPrescriptionBM);
				}
		}	
		return prescriptionBMList;
	}

	/**
	 * @param prescriptionDAO the prescriptionDAO to set
	 */
	public void setPrescriptionDAO(PrescriptionDAOExtn prescriptionDAO) {
		this.prescriptionDAO = prescriptionDAO;
	}

	/**
	 * @param prescriptionMedicineAssocDAO the prescriptionMedicineAssocDAO to set
	 */
	public void setPrescriptionMedicineAssocDAO(
			PrescriptionMedicineAssocDAO prescriptionMedicineAssocDAO) {
		this.prescriptionMedicineAssocDAO = prescriptionMedicineAssocDAO;
	}

	public List<PrescriptionBM> getMedicalPrescriptionsForPatient(Integer patientId ) throws HCISException {
		try {
			List<PrescriptionBM> prescriptionBMList = new ArrayList<PrescriptionBM>(0);
			List<Prescription> prescriptionList = prescriptionDAO.findPrescriptionForPatient( patientId );
			
			if( null != prescriptionList && prescriptionList.size() > 0 ){
				for (Prescription tmpPrescription : prescriptionList) {
					PrescriptionBM tmpPrescriptionBM = new PrescriptionBM();
					
					tmpPrescriptionBM.setPrescriptionNumber(tmpPrescription.getPrescriptionNumber().toString());
					tmpPrescriptionBM.setPrescriptionText(tmpPrescription.getPrescriptionText());
					
					//TODO: As till now we have OPD implemented only so SrcRefNbr  always be the 
				   //appointment number only. Once we implement IPD we would have ARN/DoctorOrderNUumber etc.
					//
					
					tmpPrescriptionBM.setAppointmentNumber(tmpPrescription.getSrcRefNumber().toString());
					
					Appointments appointments =   appointmentsDAO.findById( tmpPrescription.getSrcRefNumber() );

					if( appointments!= null  ){
						tmpPrescriptionBM.setAppointmentDate(appointments.getAppointmentDate());
					}
					
					
					List<PrescriptionMedicineAssoc> prescriptionMedicineAssocList = prescriptionMedicineAssocDAO.findByProperty("id.prescriptionNumber", tmpPrescription.getPrescriptionNumber());
					if(prescriptionMedicineAssocList!=null) {
						for ( PrescriptionMedicineAssoc temPrescriptionMedicineAssoc : prescriptionMedicineAssocList ) {
							
							PrescriptionLineItemBM prescriptionLineItem = new PrescriptionLineItemBM();
							prescriptionLineItem.setDosage(temPrescriptionMedicineAssoc.getDosage());
							prescriptionLineItem.setMedicineCode(temPrescriptionMedicineAssoc.getId().getMedicineCode());
							prescriptionLineItem.setForm(new CodeAndDescription(temPrescriptionMedicineAssoc.getMedicines().getMedicineType().getMedicineTypeCode(),
									temPrescriptionMedicineAssoc.getMedicines().getMedicineType().getDescription())); 
							prescriptionLineItem.setPrescriptionDate(tmpPrescription.getPrescriptionDate());
							prescriptionLineItem.setRemarks(temPrescriptionMedicineAssoc.getRemarks());
							prescriptionLineItem.setRepeats(temPrescriptionMedicineAssoc.getRepeats());
							prescriptionLineItem.setStrength(temPrescriptionMedicineAssoc.getMedicines().getStrength()); 
							
							tmpPrescriptionBM.getPrescriptionLineItemSet().add(prescriptionLineItem);
							}
						prescriptionBMList.add(tmpPrescriptionBM);
						}
					}
				
			}
			return prescriptionBMList;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault, e);
		}
	}

	public void setAppointmentsDAO(AppointmentsDAO appointmentsDAO) {
		this.appointmentsDAO = appointmentsDAO;
	}
	
}
