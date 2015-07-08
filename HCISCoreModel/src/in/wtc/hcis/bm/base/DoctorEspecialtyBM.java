package in.wtc.hcis.bm.base;

import java.util.Date;

@SuppressWarnings("serial")
public class DoctorEspecialtyBM implements java.io.Serializable {

	// Fields

	private CodeAndDescription especialtyCode;
	private CodeAndDescription departmentCode;
	private Boolean active;
	private Date joiningDt;
	private Date leavingDt;
	private CodeAndDescription roomCode;
	private Double consultationCharge;
	private Double followupCharge;
	private Integer followupDay;
	private Integer followupVisit;

	// Constructors

	/** default constructor */
	public DoctorEspecialtyBM() {
	}

	public CodeAndDescription getEspecialtyCode() {
		return especialtyCode;
	}

	public void setEspecialtyCode(CodeAndDescription especialtyCode) {
		this.especialtyCode = especialtyCode;
	}

	public CodeAndDescription getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(CodeAndDescription departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getJoiningDt() {
		return joiningDt;
	}

	public void setJoiningDt(Date joiningDt) {
		this.joiningDt = joiningDt;
	}

	public Date getLeavingDt() {
		return leavingDt;
	}

	public void setLeavingDt(Date leavingDt) {
		this.leavingDt = leavingDt;
	}

	public CodeAndDescription getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(CodeAndDescription roomCode) {
		this.roomCode = roomCode;
	}

	public Double getConsultationCharge() {
		return consultationCharge;
	}

	public void setConsultationCharge(Double consultationCharge) {
		this.consultationCharge = consultationCharge;
	}

	public Double getFollowupCharge() {
		return followupCharge;
	}

	public void setFollowupCharge(Double followupCharge) {
		this.followupCharge = followupCharge;
	}

	public Integer getFollowupDay() {
		return followupDay;
	}

	public void setFollowupDay(Integer followupDay) {
		this.followupDay = followupDay;
	}

	public Integer getFollowupVisit() {
		return followupVisit;
	}

	public void setFollowupVisit(Integer followupVisit) {
		this.followupVisit = followupVisit;
	}


}