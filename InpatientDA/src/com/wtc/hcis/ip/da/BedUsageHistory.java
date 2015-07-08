package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BedUsageHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedUsageHistory implements java.io.Serializable {

	// Fields

	private Integer bedAssignmentNbr;
	private Integer version;
	private BedMaster bedMaster;
	private Admission admission;
	private Date checkInDtm;
	private Date checkOutDtm;
	private Integer lastBillNbr;
	private Date lastBillDtm;
	private String createdBy;
	private Date createDtm;
	private Set bedBillHistories = new HashSet(0);

	// Constructors

	/** default constructor */
	public BedUsageHistory() {
	}

	/** minimal constructor */
	public BedUsageHistory(Integer bedAssignmentNbr, BedMaster bedMaster,
			Admission admission, String createdBy, Date createDtm) {
		this.bedAssignmentNbr = bedAssignmentNbr;
		this.bedMaster = bedMaster;
		this.admission = admission;
		this.createdBy = createdBy;
		this.createDtm = createDtm;
	}

	/** full constructor */
	public BedUsageHistory(Integer bedAssignmentNbr, BedMaster bedMaster,
			Admission admission, Date checkInDtm, Date checkOutDtm,
			Integer lastBillNbr, Date lastBillDtm, String createdBy,
			Date createDtm, Set bedBillHistories) {
		this.bedAssignmentNbr = bedAssignmentNbr;
		this.bedMaster = bedMaster;
		this.admission = admission;
		this.checkInDtm = checkInDtm;
		this.checkOutDtm = checkOutDtm;
		this.lastBillNbr = lastBillNbr;
		this.lastBillDtm = lastBillDtm;
		this.createdBy = createdBy;
		this.createDtm = createDtm;
		this.bedBillHistories = bedBillHistories;
	}

	// Property accessors

	public Integer getBedAssignmentNbr() {
		return this.bedAssignmentNbr;
	}

	public void setBedAssignmentNbr(Integer bedAssignmentNbr) {
		this.bedAssignmentNbr = bedAssignmentNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedMaster getBedMaster() {
		return this.bedMaster;
	}

	public void setBedMaster(BedMaster bedMaster) {
		this.bedMaster = bedMaster;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Date getCheckInDtm() {
		return this.checkInDtm;
	}

	public void setCheckInDtm(Date checkInDtm) {
		this.checkInDtm = checkInDtm;
	}

	public Date getCheckOutDtm() {
		return this.checkOutDtm;
	}

	public void setCheckOutDtm(Date checkOutDtm) {
		this.checkOutDtm = checkOutDtm;
	}

	public Integer getLastBillNbr() {
		return this.lastBillNbr;
	}

	public void setLastBillNbr(Integer lastBillNbr) {
		this.lastBillNbr = lastBillNbr;
	}

	public Date getLastBillDtm() {
		return this.lastBillDtm;
	}

	public void setLastBillDtm(Date lastBillDtm) {
		this.lastBillDtm = lastBillDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public Set getBedBillHistories() {
		return this.bedBillHistories;
	}

	public void setBedBillHistories(Set bedBillHistories) {
		this.bedBillHistories = bedBillHistories;
	}

}