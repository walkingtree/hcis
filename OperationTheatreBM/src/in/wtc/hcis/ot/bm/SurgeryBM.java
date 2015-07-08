package in.wtc.hcis.ot.bm;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ServiceBM;

import java.util.Date;
import java.util.List;

public class SurgeryBM {

	private String surgeryCode;
	private String surgeryName;
	private CodeAndDescription typeCode;
	private CodeAndDescription specialtyCode;
	private Integer doctorRefreshmentTime;
	private Integer totalTimeRequired;
	private String userId;
	private Date createdDtm;
	
	private ServiceBM serviceBM; //Every surgery is service
	
	private List<SurgeryStatusTimeBM> surgeryStatusTimeBMList;
	private List<OtSurgeryAssoBM> otSurgeryAssoBMList;

	public String getSurgeryCode() {
		return surgeryCode;
	}

	public void setSurgeryCode(String surgeryCode) {
		this.surgeryCode = surgeryCode;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public CodeAndDescription getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(CodeAndDescription typeCode) {
		this.typeCode = typeCode;
	}

	public CodeAndDescription getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(CodeAndDescription specialtyCode) {
		this.specialtyCode = specialtyCode;
	}

	public Integer getDoctorRefreshmentTime() {
		return doctorRefreshmentTime;
	}

	public void setDoctorRefreshmentTime(Integer doctorRefreshmentTime) {
		this.doctorRefreshmentTime = doctorRefreshmentTime;
	}

	public Integer getTotalTimeRequired() {
		return totalTimeRequired;
	}

	public void setTotalTimeRequired(Integer totalTimeRequired) {
		this.totalTimeRequired = totalTimeRequired;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreatedDtm() {
		return createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
	}

	public List<OtSurgeryAssoBM> getOtSurgeryAssoBMList() {
		return otSurgeryAssoBMList;
	}

	public void setOtSurgeryAssoBMList(List<OtSurgeryAssoBM> otSurgeryAssoBMList) {
		this.otSurgeryAssoBMList = otSurgeryAssoBMList;
	}

	public ServiceBM getServiceBM() {
		return serviceBM;
	}

	public void setServiceBM(ServiceBM serviceBM) {
		this.serviceBM = serviceBM;
	}

	public List<SurgeryStatusTimeBM> getSurgeryStatusTimeBMList() {
		return surgeryStatusTimeBMList;
	}

	public void setSurgeryStatusTimeBMList(
			List<SurgeryStatusTimeBM> surgeryStatusTimeBMList) {
		this.surgeryStatusTimeBMList = surgeryStatusTimeBMList;
	}
	
}
