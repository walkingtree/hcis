package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * OtDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class OtDetail implements java.io.Serializable {

	// Fields

	private String otId;
	private Integer version;
	private BedMaster bedMaster;
	private String name;
	private String coordinatorId;
	private String createdBy;
	private Date createdDtm;
	private String modifiedBy;
	private Date lastModifiedDtm;
	private Set otBookings = new HashSet(0);
	private Set otSurgeryAssociations = new HashSet(0);
	private Set otPatientSurgeries = new HashSet(0);

	// Constructors

	/** default constructor */
	public OtDetail() {
	}

	/** minimal constructor */
	public OtDetail(String otId, BedMaster bedMaster, String name,
			String createdBy, Date createdDtm) {
		this.otId = otId;
		this.bedMaster = bedMaster;
		this.name = name;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public OtDetail(String otId, BedMaster bedMaster, String name,
			String coordinatorId, String createdBy, Date createdDtm,
			String modifiedBy, Date lastModifiedDtm, Set otBookings,
			Set otSurgeryAssociations, Set otPatientSurgeries) {
		this.otId = otId;
		this.bedMaster = bedMaster;
		this.name = name;
		this.coordinatorId = coordinatorId;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.modifiedBy = modifiedBy;
		this.lastModifiedDtm = lastModifiedDtm;
		this.otBookings = otBookings;
		this.otSurgeryAssociations = otSurgeryAssociations;
		this.otPatientSurgeries = otPatientSurgeries;
	}

	// Property accessors

	public String getOtId() {
		return this.otId;
	}

	public void setOtId(String otId) {
		this.otId = otId;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoordinatorId() {
		return this.coordinatorId;
	}

	public void setCoordinatorId(String coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtm() {
		return this.createdDtm;
	}

	public void setCreatedDtm(Date createdDtm) {
		this.createdDtm = createdDtm;
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

	public Set getOtBookings() {
		return this.otBookings;
	}

	public void setOtBookings(Set otBookings) {
		this.otBookings = otBookings;
	}

	public Set getOtSurgeryAssociations() {
		return this.otSurgeryAssociations;
	}

	public void setOtSurgeryAssociations(Set otSurgeryAssociations) {
		this.otSurgeryAssociations = otSurgeryAssociations;
	}

	public Set getOtPatientSurgeries() {
		return this.otPatientSurgeries;
	}

	public void setOtPatientSurgeries(Set otPatientSurgeries) {
		this.otPatientSurgeries = otPatientSurgeries;
	}

}