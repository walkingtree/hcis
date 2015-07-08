package in.wtc.hcis.ot.bl.common;

import in.wtc.hcis.exceptions.HCISException;

import com.wtc.hcis.ip.da.OtBooking;
import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotes;
import com.wtc.hcis.ip.da.OtPatientSurgeryNotesId;
import com.wtc.hcis.ip.da.OtSurgery;
import com.wtc.hcis.ip.da.OtSurgeryAssociation;
import com.wtc.hcis.ip.da.OtSurgeryAssociationId;
import com.wtc.hcis.ip.da.OtSurgeryStatus;
import com.wtc.hcis.ip.da.OtSurgeryStatusTime;
import com.wtc.hcis.ip.da.OtSurgeryStatusTimeId;

public interface OTDataModelManager {
	OtDetail getOTDetail(String otCode) throws HCISException;
	
	OtSurgery getOTSurgery(String surgeryCode)throws HCISException;
	
	OtSurgeryAssociation getOTSurgeryAssociation(OtSurgeryAssociationId otSurgeryAssociationId)throws HCISException;
	
	OtSurgeryStatus getOTSurgeryStatus(String otSurgeryStatusCode)throws HCISException;
	
	OtSurgeryStatusTime getOTSurgeryStatusTime(OtSurgeryStatusTimeId otSurgeryStatusTimeId)throws HCISException;
	
	OtBooking getOTBooking(Long otBookingNbr)throws HCISException;
	
	OtPatientSurgeryNotes getPatientSurgeryNotes(OtPatientSurgeryNotesId id)throws HCISException;
}
