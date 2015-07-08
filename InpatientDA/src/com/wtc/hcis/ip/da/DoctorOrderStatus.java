package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * DoctorOrderStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderStatus implements java.io.Serializable {

	// Fields

	private String orderStatusCd;
	private Integer version;
	private String statusDesc;
	private String customStatusDesc;
	private String processName;
	private Set dischargeActivities = new HashSet(0);
	private Set dischargeOrders = new HashSet(0);
	private Set doctorOrders = new HashSet(0);
	private Set doctorOrderActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public DoctorOrderStatus() {
	}

	/** minimal constructor */
	public DoctorOrderStatus(String orderStatusCd) {
		this.orderStatusCd = orderStatusCd;
	}

	/** full constructor */
	public DoctorOrderStatus(String orderStatusCd, String statusDesc,
			String customStatusDesc, String processName,
			Set dischargeActivities, Set dischargeOrders, Set doctorOrders,
			Set doctorOrderActivities) {
		this.orderStatusCd = orderStatusCd;
		this.statusDesc = statusDesc;
		this.customStatusDesc = customStatusDesc;
		this.processName = processName;
		this.dischargeActivities = dischargeActivities;
		this.dischargeOrders = dischargeOrders;
		this.doctorOrders = doctorOrders;
		this.doctorOrderActivities = doctorOrderActivities;
	}

	// Property accessors

	public String getOrderStatusCd() {
		return this.orderStatusCd;
	}

	public void setOrderStatusCd(String orderStatusCd) {
		this.orderStatusCd = orderStatusCd;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getStatusDesc() {
		return this.statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getCustomStatusDesc() {
		return this.customStatusDesc;
	}

	public void setCustomStatusDesc(String customStatusDesc) {
		this.customStatusDesc = customStatusDesc;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Set getDischargeActivities() {
		return this.dischargeActivities;
	}

	public void setDischargeActivities(Set dischargeActivities) {
		this.dischargeActivities = dischargeActivities;
	}

	public Set getDischargeOrders() {
		return this.dischargeOrders;
	}

	public void setDischargeOrders(Set dischargeOrders) {
		this.dischargeOrders = dischargeOrders;
	}

	public Set getDoctorOrders() {
		return this.doctorOrders;
	}

	public void setDoctorOrders(Set doctorOrders) {
		this.doctorOrders = doctorOrders;
	}

	public Set getDoctorOrderActivities() {
		return this.doctorOrderActivities;
	}

	public void setDoctorOrderActivities(Set doctorOrderActivities) {
		this.doctorOrderActivities = doctorOrderActivities;
	}

}