/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedBookingDetails  implements Serializable {
	private Integer admissionNbr;
	private Date admissionDtm;
	private Date transferDischargeDtm;
	private String transferDischargeInd;
	private Integer doctorId;
	private BedReservationBM bedReservationBM;
	private BedMasterBM bedMasterBM;
	
	public Integer getAdmissionNbr() {
		return admissionNbr;
	}
	public void setAdmissionNbr(Integer admissionNbr) {
		this.admissionNbr = admissionNbr;
	}
	public Date getAdmissionDtm() {
		return admissionDtm;
	}
	public void setAdmissionDtm(Date admissionDtm) {
		this.admissionDtm = admissionDtm;
	}
	public Date getTransferDischargeDtm() {
		return transferDischargeDtm;
	}
	public void setTransferDischargeDtm(Date transferDischargeDtm) {
		this.transferDischargeDtm = transferDischargeDtm;
	}
	public String getTransferDischargeInd() {
		return transferDischargeInd;
	}
	public void setTransferDischargeInd(String transferDischargeInd) {
		this.transferDischargeInd = transferDischargeInd;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public BedReservationBM getBedReservationBM() {
		return bedReservationBM;
	}
	public void setBedReservationBM(BedReservationBM bedReservationBM) {
		this.bedReservationBM = bedReservationBM;
	}
	public BedMasterBM getBedMasterBM() {
		return bedMasterBM;
	}
	public void setBedMasterBM(BedMasterBM bedMasterBM) {
		this.bedMasterBM = bedMasterBM;
	}
	
}
