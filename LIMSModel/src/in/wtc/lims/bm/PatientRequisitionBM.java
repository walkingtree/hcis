package in.wtc.lims.bm;

import java.util.List;

import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.PatientLiteBM;

public class PatientRequisitionBM {
	
	private PatientLiteBM patientLiteBM;
	private List<RequisitionOrderDetailBM> requisitionOrderDetailBM;
	
	public PatientLiteBM getPatientLiteBM() {
		return patientLiteBM;
	}
	public void setPatientLiteBM(PatientLiteBM patientLiteBM) {
		this.patientLiteBM = patientLiteBM;
	}
	public List<RequisitionOrderDetailBM> getRequisitionOrderDetailBM() {
		return requisitionOrderDetailBM;
	}
	public void setRequisitionOrderDetailBM(
			List<RequisitionOrderDetailBM> requisitionOrderDetailBM) {
		this.requisitionOrderDetailBM = requisitionOrderDetailBM;
	}
	
	

}
