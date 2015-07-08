package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DoctorOrderGroup entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderGroup implements java.io.Serializable {

	// Fields

	private String orderGroupName;
	private Integer version;
	private String description;
	private Integer groupForDoctorId;
	private Date creationDtm;
	private String createdBy;
	private Set doctorOrders = new HashSet(0);
	private Set doctorOrderGroupHasTemplates = new HashSet(0);

	// Constructors

	/** default constructor */
	public DoctorOrderGroup() {
	}

	/** minimal constructor */
	public DoctorOrderGroup(String orderGroupName) {
		this.orderGroupName = orderGroupName;
	}

	/** full constructor */
	public DoctorOrderGroup(String orderGroupName, String description,
			Integer groupForDoctorId, Date creationDtm, String createdBy,
			Set doctorOrders, Set doctorOrderGroupHasTemplates) {
		this.orderGroupName = orderGroupName;
		this.description = description;
		this.groupForDoctorId = groupForDoctorId;
		this.creationDtm = creationDtm;
		this.createdBy = createdBy;
		this.doctorOrders = doctorOrders;
		this.doctorOrderGroupHasTemplates = doctorOrderGroupHasTemplates;
	}

	// Property accessors

	public String getOrderGroupName() {
		return this.orderGroupName;
	}

	public void setOrderGroupName(String orderGroupName) {
		this.orderGroupName = orderGroupName;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupForDoctorId() {
		return this.groupForDoctorId;
	}

	public void setGroupForDoctorId(Integer groupForDoctorId) {
		this.groupForDoctorId = groupForDoctorId;
	}

	public Date getCreationDtm() {
		return this.creationDtm;
	}

	public void setCreationDtm(Date creationDtm) {
		this.creationDtm = creationDtm;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Set getDoctorOrders() {
		return this.doctorOrders;
	}

	public void setDoctorOrders(Set doctorOrders) {
		this.doctorOrders = doctorOrders;
	}

	public Set getDoctorOrderGroupHasTemplates() {
		return this.doctorOrderGroupHasTemplates;
	}

	public void setDoctorOrderGroupHasTemplates(Set doctorOrderGroupHasTemplates) {
		this.doctorOrderGroupHasTemplates = doctorOrderGroupHasTemplates;
	}

}