package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * DoctorOrderDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderDetails implements java.io.Serializable {

	// Fields

	private DoctorOrderDetailsId id;
	private Integer version;
	private DoctorOrder doctorOrder;
	private ActionStatus actionStatus;
	private String responsibleDepartmentId;
	private String serviceId;
	private String packageId;
	private String actionDesc;
	private String actionRemarks;
	private Date actionDtm;
	private String actionTakenBy;

	// Constructors

	/** default constructor */
	public DoctorOrderDetails() {
	}

	/** minimal constructor */
	public DoctorOrderDetails(DoctorOrderDetailsId id, DoctorOrder doctorOrder) {
		this.id = id;
		this.doctorOrder = doctorOrder;
	}

	/** full constructor */
	public DoctorOrderDetails(DoctorOrderDetailsId id, DoctorOrder doctorOrder,
			ActionStatus actionStatus, String responsibleDepartmentId,
			String serviceId, String packageId, String actionDesc,
			String actionRemarks, Date actionDtm, String actionTakenBy) {
		this.id = id;
		this.doctorOrder = doctorOrder;
		this.actionStatus = actionStatus;
		this.responsibleDepartmentId = responsibleDepartmentId;
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.actionDesc = actionDesc;
		this.actionRemarks = actionRemarks;
		this.actionDtm = actionDtm;
		this.actionTakenBy = actionTakenBy;
	}

	// Property accessors

	public DoctorOrderDetailsId getId() {
		return this.id;
	}

	public void setId(DoctorOrderDetailsId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrder getDoctorOrder() {
		return this.doctorOrder;
	}

	public void setDoctorOrder(DoctorOrder doctorOrder) {
		this.doctorOrder = doctorOrder;
	}

	public ActionStatus getActionStatus() {
		return this.actionStatus;
	}

	public void setActionStatus(ActionStatus actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getResponsibleDepartmentId() {
		return this.responsibleDepartmentId;
	}

	public void setResponsibleDepartmentId(String responsibleDepartmentId) {
		this.responsibleDepartmentId = responsibleDepartmentId;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getActionDesc() {
		return this.actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public String getActionRemarks() {
		return this.actionRemarks;
	}

	public void setActionRemarks(String actionRemarks) {
		this.actionRemarks = actionRemarks;
	}

	public Date getActionDtm() {
		return this.actionDtm;
	}

	public void setActionDtm(Date actionDtm) {
		this.actionDtm = actionDtm;
	}

	public String getActionTakenBy() {
		return this.actionTakenBy;
	}

	public void setActionTakenBy(String actionTakenBy) {
		this.actionTakenBy = actionTakenBy;
	}

}