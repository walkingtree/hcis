package in.wtc.hcis.ot.bl.common;

import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtBookingDAO;
import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtDetailDAO;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotes;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotesDAO;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotesId;
import com.wtc.hcis.ip.da.OtSurgery;
import com.wtc.hcis.ip.da.OtSurgeryAssociation;
import com.wtc.hcis.ip.da.OtSurgeryAssociationDAO;
import com.wtc.hcis.ip.da.OtSurgeryAssociationId;
import com.wtc.hcis.ip.da.OtSurgeryDAO;
import com.wtc.hcis.ip.da.OtSurgeryStatus;
import com.wtc.hcis.ip.da.OtSurgeryStatusDAO;
import com.wtc.hcis.ip.da.OtSurgeryStatusTime;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeDAO;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeId;

public class OTDataModelManagerImpl implements OTDataModelManager {
	
	OtDetailDAO otDetailDAO;
	OtSurgeryDAO otSurgeryDAO;
	OtSurgeryAssociationDAO otSurgeryAssociationDAO;
	OtSurgeryStatusDAO otSurgeryStatusDAO;
	OtSurgeryStatusTimeDAO otSurgeryStatusTimeDAO;
	OtBookingDAO otBookingDAO;
	OtPatientSurgeryNotesDAO patientSurgeryNotesDAO;

	public OtDetail getOTDetail(String otCode) throws HCISException{
		
		try {
			OtDetail otDetail = otDetailDAO.findById(otCode);
			
			if ( otDetail == null ){
				throw new Exception();
			}
			
			return otDetail;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}
	
	public OtSurgery getOTSurgery(String surgeryCode) throws HCISException{
		
		try {
			OtSurgery otSurgery = otSurgeryDAO.findById(surgeryCode);
			
			if ( otSurgery == null ){
				throw new Exception();
			}
			
			return otSurgery;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}
	
	
	public OtSurgeryAssociation getOTSurgeryAssociation(OtSurgeryAssociationId otSurgeryAssociationId)throws HCISException{
		try {
			OtSurgeryAssociation otSurgeryAssociation = otSurgeryAssociationDAO.findById(otSurgeryAssociationId);
			
			if ( otSurgeryAssociation == null ){
				throw new Exception();
			}
			
			return otSurgeryAssociation;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}
	
	public OtSurgeryStatus getOTSurgeryStatus(String otSurgeryStatusCode)throws HCISException{
		try {
			OtSurgeryStatus otSurgeryStatus = otSurgeryStatusDAO.findById(otSurgeryStatusCode);
			
			if ( otSurgeryStatus == null ){
				throw new Exception();
			}
			
			return otSurgeryStatus;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}
	
	public OtSurgeryStatusTime getOTSurgeryStatusTime(OtSurgeryStatusTimeId otSurgeryStatusTimeId)throws HCISException{
		try {
			OtSurgeryStatusTime otSurgeryStatusTime = otSurgeryStatusTimeDAO.findById(otSurgeryStatusTimeId);
			
			if ( otSurgeryStatusTime == null ){
				throw new Exception();
			}
			
			return otSurgeryStatusTime;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}

	public OtBooking getOTBooking(Long otBookingNbr)throws HCISException{
		try {
			OtBooking otBooking = otBookingDAO.findById(otBookingNbr);
			
			if ( otBooking == null ){
				throw new Exception();
			}
			
			return otBooking;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}

	public OtPatientSurgeryNotes getPatientSurgeryNotes(OtPatientSurgeryNotesId id)throws HCISException{
		try {
			OtPatientSurgeryNotes patientSurgeryNotes = patientSurgeryNotesDAO.findById(id);
			
			if ( patientSurgeryNotes == null ){
				throw new Exception();
			}
			
			return patientSurgeryNotes;
		} catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault );
		}
	}
	
	public void setOtDetailDAO(OtDetailDAO otDetailDAO) {
		this.otDetailDAO = otDetailDAO;
	}

	public void setOtSurgeryDAO(OtSurgeryDAO otSurgeryDAO) {
		this.otSurgeryDAO = otSurgeryDAO;
	}

	public void setOtSurgeryAssociationDAO(
			OtSurgeryAssociationDAO otSurgeryAssociationDAO) {
		this.otSurgeryAssociationDAO = otSurgeryAssociationDAO;
	}

	public void setOtSurgeryStatusDAO(OtSurgeryStatusDAO otSurgeryStatusDAO) {
		this.otSurgeryStatusDAO = otSurgeryStatusDAO;
	}

	public void setOtSurgeryStatusTimeDAO(
			OtSurgeryStatusTimeDAO otSurgeryStatusTimeDAO) {
		this.otSurgeryStatusTimeDAO = otSurgeryStatusTimeDAO;
	}

	public void setOtBookingDAO(OtBookingDAO otBookingDAO) {
		this.otBookingDAO = otBookingDAO;
	}

	public void setPatientSurgeryNotesDAO(
			OtPatientSurgeryNotesDAO patientSurgeryNotesDAO) {
		this.patientSurgeryNotesDAO = patientSurgeryNotesDAO;
	}
	
}
