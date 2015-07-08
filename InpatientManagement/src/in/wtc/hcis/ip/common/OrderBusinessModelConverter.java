/**
 * 
 */
package in.wtc.hcis.ip.common;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.ip.AdmissionBM;
import in.wtc.hcis.bm.ip.DischargeBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.ip.bo.order.DoctorOrderConstants;

import java.io.Serializable;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class OrderBusinessModelConverter implements Serializable {
	public DoctorOrderBM convertAdmissionBM2DoctorOrderBM( AdmissionBM admissionBM ) {
		DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
		
		doctorOrderBM.setAdmissionNbr( admissionBM.getAdmissionNbr() );
		doctorOrderBM.setDoctorId( admissionBM.getDoctorId() );
		doctorOrderBM.setDoctorOrderDetailsList( admissionBM.getDoctorOrderDetailsList() );
		doctorOrderBM.setDoctorOrderNbr( admissionBM.getDoctorOrderNbr() );
		doctorOrderBM.setDoctorOrderStatus( admissionBM.getDoctorOrderStatus() );
		doctorOrderBM.setDoctorOrderTemplate( null );
		doctorOrderBM.setDoctorOrderType( new CodeAndDescription( DoctorOrderConstants.ORDER_TYPE_IP_ADMISSION, "") );
		doctorOrderBM.setLastModifiedTm( admissionBM.getLastUpdatedDtm() );
		doctorOrderBM.setModifiedBy( admissionBM.getModifiedBy() );
		doctorOrderBM.setOrderDesc( admissionBM.getOrderDesc() );
		doctorOrderBM.setOrderDictation( admissionBM.getOrderDictation() );
		doctorOrderBM.setOrderSpecificAttributes( null );
		doctorOrderBM.setPatientId( admissionBM.getPatientId() );
		doctorOrderBM.setDoctorOrderDetailsList(admissionBM.getDoctorOrderDetailsList());
		return doctorOrderBM;
	}
	
	public DoctorOrderBM convertDischargeBM2DoctorOrderBM( DischargeBM dischargeBM ) {
		DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
		
		doctorOrderBM.setAdmissionNbr( dischargeBM.getAdmissionReqNbr() ); //TODO: Need to verify
		doctorOrderBM.setDoctorId( dischargeBM.getDoctorId() );
		doctorOrderBM.setDoctorOrderDetailsList( dischargeBM.getDoctorOrderDetailsList() );
		doctorOrderBM.setDoctorOrderNbr( dischargeBM.getDoctorOrderNbr() );
		doctorOrderBM.setDoctorOrderStatus( dischargeBM.getDoctorOrderStatus() );
		doctorOrderBM.setDoctorOrderTemplate( null );
		doctorOrderBM.setDoctorOrderType( new CodeAndDescription( DoctorOrderConstants.ORDER_TYPE_DISCHARGE, "") );
		doctorOrderBM.setLastModifiedTm( dischargeBM.getLastModifiedDtm());
		doctorOrderBM.setModifiedBy( dischargeBM.getModifiedBy() );
		doctorOrderBM.setOrderDesc( dischargeBM.getOrderDesc() );
		doctorOrderBM.setOrderDictation( dischargeBM.getOrderDictation() );
		doctorOrderBM.setOrderSpecificAttributes( null );
		doctorOrderBM.setPatientId( dischargeBM.getPatientId() );
		
		doctorOrderBM.setDoctorOrderDetailsList(dischargeBM.getDoctorOrderDetailsList());

		return doctorOrderBM;
	}
	

}
