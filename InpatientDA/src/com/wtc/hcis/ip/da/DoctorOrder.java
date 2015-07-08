package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DoctorOrder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrder implements java.io.Serializable {

	// Fields

	private Integer doctorOrderNbr;
	private Integer version;
	private DoctorOrderStatus doctorOrderStatus;
	private DoctorOrderGroup doctorOrderGroup;
	private DoctorOrderTemplate doctorOrderTemplate;
	private DoctorOrderType doctorOrderType;
	private Admission admission;
	private Integer creationSeqNbr;
	private Integer doctorId;
	private Integer patientId;
	private String orderDesc;
	private String orderDictation;
	private String createdBy;
	private Date orderCreationDtm;
	private String modifiedBy;
	private Date lastModifiedTm;
	private Set orderAttributeValues = new HashSet(0);
	private Set dischargeOrders = new HashSet(0);
	private Set doctorOrderActivities = new HashSet(0);
	private Set doctorOrderDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public DoctorOrder() {
	}

	/** minimal constructor */
	public DoctorOrder(Integer doctorOrderNbr,
			DoctorOrderStatus doctorOrderStatus,
			DoctorOrderType doctorOrderType, Integer creationSeqNbr,
			Integer doctorId, String orderDictation, Date orderCreationDtm) {
		this.doctorOrderNbr = doctorOrderNbr;
		this.doctorOrderStatus = doctorOrderStatus;
		this.doctorOrderType = doctorOrderType;
		this.creationSeqNbr = creationSeqNbr;
		this.doctorId = doctorId;
		this.orderDictation = orderDictation;
		this.orderCreationDtm = orderCreationDtm;
	}

	/** full constructor */
	public DoctorOrder(Integer doctorOrderNbr,
			DoctorOrderStatus doctorOrderStatus,
			DoctorOrderGroup doctorOrderGroup,
			DoctorOrderTemplate doctorOrderTemplate,
			DoctorOrderType doctorOrderType, Admission admission,
			Integer creationSeqNbr, Integer doctorId, Integer patientId,
			String orderDesc, String orderDictation, String createdBy,
			Date orderCreationDtm, String modifiedBy, Date lastModifiedTm,
			Set orderAttributeValues, Set dischargeOrders,
			Set doctorOrderActivities, Set doctorOrderDetailses) {
		this.doctorOrderNbr = doctorOrderNbr;
		this.doctorOrderStatus = doctorOrderStatus;
		this.doctorOrderGroup = doctorOrderGroup;
		this.doctorOrderTemplate = doctorOrderTemplate;
		this.doctorOrderType = doctorOrderType;
		this.admission = admission;
		this.creationSeqNbr = creationSeqNbr;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.orderDesc = orderDesc;
		this.orderDictation = orderDictation;
		this.createdBy = createdBy;
		this.orderCreationDtm = orderCreationDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedTm = lastModifiedTm;
		this.orderAttributeValues = orderAttributeValues;
		this.dischargeOrders = dischargeOrders;
		this.doctorOrderActivities = doctorOrderActivities;
		this.doctorOrderDetailses = doctorOrderDetailses;
	}

	// Property accessors

	public Integer getDoctorOrderNbr() {
		return this.doctorOrderNbr;
	}

	public void setDoctorOrderNbr(Integer doctorOrderNbr) {
		this.doctorOrderNbr = doctorOrderNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrderStatus getDoctorOrderStatus() {
		return this.doctorOrderStatus;
	}

	public void setDoctorOrderStatus(DoctorOrderStatus doctorOrderStatus) {
		this.doctorOrderStatus = doctorOrderStatus;
	}

	public DoctorOrderGroup getDoctorOrderGroup() {
		return this.doctorOrderGroup;
	}

	public void setDoctorOrderGroup(DoctorOrderGroup doctorOrderGroup) {
		this.doctorOrderGroup = doctorOrderGroup;
	}

	public DoctorOrderTemplate getDoctorOrderTemplate() {
		return this.doctorOrderTemplate;
	}

	public void setDoctorOrderTemplate(DoctorOrderTemplate doctorOrderTemplate) {
		this.doctorOrderTemplate = doctorOrderTemplate;
	}

	public DoctorOrderType getDoctorOrderType() {
		return this.doctorOrderType;
	}

	public void setDoctorOrderType(DoctorOrderType doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public Integer getCreationSeqNbr() {
		return this.creationSeqNbr;
	}

	public void setCreationSeqNbr(Integer creationSeqNbr) {
		this.creationSeqNbr = creationSeqNbr;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getOrderDesc() {
		return this.orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getOrderDictation() {
		return this.orderDictation;
	}

	public void setOrderDictation(String orderDictation) {
		this.orderDictation = orderDictation;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getOrderCreationDtm() {
		return this.orderCreationDtm;
	}

	public void setOrderCreationDtm(Date orderCreationDtm) {
		this.orderCreationDtm = orderCreationDtm;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModifiedTm() {
		return this.lastModifiedTm;
	}

	public void setLastModifiedTm(Date lastModifiedTm) {
		this.lastModifiedTm = lastModifiedTm;
	}

	public Set getOrderAttributeValues() {
		return this.orderAttributeValues;
	}

	public void setOrderAttributeValues(Set orderAttributeValues) {
		this.orderAttributeValues = orderAttributeValues;
	}

	public Set getDischargeOrders() {
		return this.dischargeOrders;
	}

	public void setDischargeOrders(Set dischargeOrders) {
		this.dischargeOrders = dischargeOrders;
	}

	public Set getDoctorOrderActivities() {
		return this.doctorOrderActivities;
	}

	public void setDoctorOrderActivities(Set doctorOrderActivities) {
		this.doctorOrderActivities = doctorOrderActivities;
	}

	public Set getDoctorOrderDetailses() {
		return this.doctorOrderDetailses;
	}

	public void setDoctorOrderDetailses(Set doctorOrderDetailses) {
		this.doctorOrderDetailses = doctorOrderDetailses;
	}

}