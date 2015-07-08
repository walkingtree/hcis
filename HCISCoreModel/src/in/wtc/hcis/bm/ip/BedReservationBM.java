/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedReservationBM implements Serializable {
	
	private Integer bedReservationNbr;
	private Integer admissionReqNbr;
	private CodeAndDescription unitType;//Not Null
	private String createdBy;			//ModifiedBy in case of modification
	private CodeAndDescription bedType;
	private String bedNumber;
	private CodeAndDescription reservationStatus;
	private Date requestValidTill;
	private String gotPreferredRoom; 	//'Y' or 'N'
	private Date reservationFromDtm;
	private Date reservationToDtm;
	private Integer patientId;
	private List<CodeAndDescription> requiredFacilities;
	private List<CodeAndDescription> desiredFacilities;
	
	// this field only use to display information in grid,for front end purpose
	private CodeAndDescription admissionStatus;

	
	public Integer getBedReservationNbr() {
		return bedReservationNbr;
	}
	public void setBedReservationNbr(Integer bedReservationNbr) {
		this.bedReservationNbr = bedReservationNbr;
	}
	public String getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}
	public CodeAndDescription getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(CodeAndDescription reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public Date getRequestValidTill() {
		return requestValidTill;
	}
	public void setRequestValidTill(Date requestValidTill) {
		this.requestValidTill = requestValidTill;
	}
	
	public Date getReservationFromDtm() {
		return reservationFromDtm;
	}
	public void setReservationFromDtm(Date reservationFromDtm) {
		this.reservationFromDtm = reservationFromDtm;
	}
	public Date getReservationToDtm() {
		return reservationToDtm;
	}
	public void setReservationToDtm(Date reservationToDtm) {
		this.reservationToDtm = reservationToDtm;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getAdmissionReqNbr() {
		return admissionReqNbr;
	}
	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}
	public CodeAndDescription getUnitType() {
		return unitType;
	}
	public void setUnitType(CodeAndDescription unitType) {
		this.unitType = unitType;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public List<CodeAndDescription> getRequiredFacilities() {
		return requiredFacilities;
	}
	public void setRequiredFacilities(List<CodeAndDescription> requiredFacilities) {
		this.requiredFacilities = requiredFacilities;
	}
	public List<CodeAndDescription> getDesiredFacilities() {
		return desiredFacilities;
	}
	public void setDesiredFacilities(List<CodeAndDescription> desiredFacilities) {
		this.desiredFacilities = desiredFacilities;
	}
	public String getGotPreferredRoom() {
		return gotPreferredRoom;
	}
	public void setGotPreferredRoom(String gotPreferredRoom) {
		this.gotPreferredRoom = gotPreferredRoom;
	}
	public CodeAndDescription getBedType() {
		return bedType;
	}
	public void setBedType(CodeAndDescription bedType) {
		this.bedType = bedType;
	}
	public BedReservationBM() {
	}
	public CodeAndDescription getAdmissionStatus() {
		return admissionStatus;
	}
	public void setAdmissionStatus(CodeAndDescription admissionStatus) {
		this.admissionStatus = admissionStatus;
	}
}
