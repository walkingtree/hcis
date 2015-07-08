package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * BedBillHistory entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BedBillHistory implements java.io.Serializable {

	// Fields

	private BedBillHistoryId id;
	private Integer version;
	private BedUsageHistory bedUsageHistory;
	private Date billFromDtm;
	private Date billToDtm;
	private Integer billedHourUnit;
	private Integer billedDayUnit;
	private Double hourlyCharge;
	private Double dailyCharge;
	private Double totalCharge;
	private Date createDtm;
	private String createdBy;

	// Constructors

	/** default constructor */
	public BedBillHistory() {
	}

	/** minimal constructor */
	public BedBillHistory(BedBillHistoryId id, BedUsageHistory bedUsageHistory,
			Date billFromDtm, Date billToDtm, Double totalCharge, Date createDtm) {
		this.id = id;
		this.bedUsageHistory = bedUsageHistory;
		this.billFromDtm = billFromDtm;
		this.billToDtm = billToDtm;
		this.totalCharge = totalCharge;
		this.createDtm = createDtm;
	}

	/** full constructor */
	public BedBillHistory(BedBillHistoryId id, BedUsageHistory bedUsageHistory,
			Date billFromDtm, Date billToDtm, Integer billedHourUnit,
			Integer billedDayUnit, Double hourlyCharge, Double dailyCharge,
			Double totalCharge, Date createDtm, String createdBy) {
		this.id = id;
		this.bedUsageHistory = bedUsageHistory;
		this.billFromDtm = billFromDtm;
		this.billToDtm = billToDtm;
		this.billedHourUnit = billedHourUnit;
		this.billedDayUnit = billedDayUnit;
		this.hourlyCharge = hourlyCharge;
		this.dailyCharge = dailyCharge;
		this.totalCharge = totalCharge;
		this.createDtm = createDtm;
		this.createdBy = createdBy;
	}

	// Property accessors

	public BedBillHistoryId getId() {
		return this.id;
	}

	public void setId(BedBillHistoryId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public BedUsageHistory getBedUsageHistory() {
		return this.bedUsageHistory;
	}

	public void setBedUsageHistory(BedUsageHistory bedUsageHistory) {
		this.bedUsageHistory = bedUsageHistory;
	}

	public Date getBillFromDtm() {
		return this.billFromDtm;
	}

	public void setBillFromDtm(Date billFromDtm) {
		this.billFromDtm = billFromDtm;
	}

	public Date getBillToDtm() {
		return this.billToDtm;
	}

	public void setBillToDtm(Date billToDtm) {
		this.billToDtm = billToDtm;
	}

	public Integer getBilledHourUnit() {
		return this.billedHourUnit;
	}

	public void setBilledHourUnit(Integer billedHourUnit) {
		this.billedHourUnit = billedHourUnit;
	}

	public Integer getBilledDayUnit() {
		return this.billedDayUnit;
	}

	public void setBilledDayUnit(Integer billedDayUnit) {
		this.billedDayUnit = billedDayUnit;
	}

	public Double getHourlyCharge() {
		return this.hourlyCharge;
	}

	public void setHourlyCharge(Double hourlyCharge) {
		this.hourlyCharge = hourlyCharge;
	}

	public Double getDailyCharge() {
		return this.dailyCharge;
	}

	public void setDailyCharge(Double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public Double getTotalCharge() {
		return this.totalCharge;
	}

	public void setTotalCharge(Double totalCharge) {
		this.totalCharge = totalCharge;
	}

	public Date getCreateDtm() {
		return this.createDtm;
	}

	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}