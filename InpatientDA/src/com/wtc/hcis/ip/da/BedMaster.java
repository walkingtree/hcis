package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BedMaster entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedMaster implements java.io.Serializable {

	// Fields

	private String bedNumber;
	private Integer version;
	private NursingUnit nursingUnit;
	private BedStatus bedStatus;
	private Admission admission;
	private BedType bedType;
	private String roomNbr;
	private String floorNbr;
	private String siteName;
	private String description;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Double dailyCharge;
	private Double hourlyCharge;
	private Double depositAmount;
	private Date admissionDtm;
	private Date expectedDischargeDtm;
	private Integer patientId;
	private Integer doctorId;
	private Set bedReservations = new HashSet(0);
	private Set bedHasSpecialFeatureses = new HashSet(0);
	private Set bedUsageHistories = new HashSet(0);
	private Set bedActivities = new HashSet(0);
	private Set otDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedMaster() {
	}

	/** minimal constructor */
	public BedMaster(String bedNumber, NursingUnit nursingUnit,
			BedStatus bedStatus, BedType bedType, String roomNbr) {
		this.bedNumber = bedNumber;
		this.nursingUnit = nursingUnit;
		this.bedStatus = bedStatus;
		this.bedType = bedType;
		this.roomNbr = roomNbr;
	}

	/** full constructor */
	public BedMaster(String bedNumber, NursingUnit nursingUnit,
			BedStatus bedStatus, Admission admission, BedType bedType,
			String roomNbr, String floorNbr, String siteName,
			String description, String modifiedBy, Date lastModifiedDtm,
			Double dailyCharge, Double hourlyCharge, Double depositAmount,
			Date admissionDtm, Date expectedDischargeDtm, Integer patientId,
			Integer doctorId, Set bedReservations, Set bedHasSpecialFeatureses,
			Set bedUsageHistories, Set bedActivities, Set otDetails) {
		this.bedNumber = bedNumber;
		this.nursingUnit = nursingUnit;
		this.bedStatus = bedStatus;
		this.admission = admission;
		this.bedType = bedType;
		this.roomNbr = roomNbr;
		this.floorNbr = floorNbr;
		this.siteName = siteName;
		this.description = description;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.dailyCharge = dailyCharge;
		this.hourlyCharge = hourlyCharge;
		this.depositAmount = depositAmount;
		this.admissionDtm = admissionDtm;
		this.expectedDischargeDtm = expectedDischargeDtm;
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.bedReservations = bedReservations;
		this.bedHasSpecialFeatureses = bedHasSpecialFeatureses;
		this.bedUsageHistories = bedUsageHistories;
		this.bedActivities = bedActivities;
		this.otDetails = otDetails;
	}

	// Property accessors

	public String getBedNumber() {
		return this.bedNumber;
	}

	public void setBedNumber(String bedNumber) {
		this.bedNumber = bedNumber;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public NursingUnit getNursingUnit() {
		return this.nursingUnit;
	}

	public void setNursingUnit(NursingUnit nursingUnit) {
		this.nursingUnit = nursingUnit;
	}

	public BedStatus getBedStatus() {
		return this.bedStatus;
	}

	public void setBedStatus(BedStatus bedStatus) {
		this.bedStatus = bedStatus;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public BedType getBedType() {
		return this.bedType;
	}

	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}

	public String getRoomNbr() {
		return this.roomNbr;
	}

	public void setRoomNbr(String roomNbr) {
		this.roomNbr = roomNbr;
	}

	public String getFloorNbr() {
		return this.floorNbr;
	}

	public void setFloorNbr(String floorNbr) {
		this.floorNbr = floorNbr;
	}

	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Double getDailyCharge() {
		return this.dailyCharge;
	}

	public void setDailyCharge(Double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public Double getHourlyCharge() {
		return this.hourlyCharge;
	}

	public void setHourlyCharge(Double hourlyCharge) {
		this.hourlyCharge = hourlyCharge;
	}

	public Double getDepositAmount() {
		return this.depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Date getAdmissionDtm() {
		return this.admissionDtm;
	}

	public void setAdmissionDtm(Date admissionDtm) {
		this.admissionDtm = admissionDtm;
	}

	public Date getExpectedDischargeDtm() {
		return this.expectedDischargeDtm;
	}

	public void setExpectedDischargeDtm(Date expectedDischargeDtm) {
		this.expectedDischargeDtm = expectedDischargeDtm;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Set getBedReservations() {
		return this.bedReservations;
	}

	public void setBedReservations(Set bedReservations) {
		this.bedReservations = bedReservations;
	}

	public Set getBedHasSpecialFeatureses() {
		return this.bedHasSpecialFeatureses;
	}

	public void setBedHasSpecialFeatureses(Set bedHasSpecialFeatureses) {
		this.bedHasSpecialFeatureses = bedHasSpecialFeatureses;
	}

	public Set getBedUsageHistories() {
		return this.bedUsageHistories;
	}

	public void setBedUsageHistories(Set bedUsageHistories) {
		this.bedUsageHistories = bedUsageHistories;
	}

	public Set getBedActivities() {
		return this.bedActivities;
	}

	public void setBedActivities(Set bedActivities) {
		this.bedActivities = bedActivities;
	}

	public Set getOtDetails() {
		return this.otDetails;
	}

	public void setOtDetails(Set otDetails) {
		this.otDetails = otDetails;
	}

}