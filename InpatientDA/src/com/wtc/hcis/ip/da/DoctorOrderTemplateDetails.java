package com.wtc.hcis.ip.da;

/**
 * DoctorOrderTemplateDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorOrderTemplateDetails implements java.io.Serializable {

	// Fields

	private DoctorOrderTemplateDetailsId id;
	private Integer version;
	private DoctorOrderTemplate doctorOrderTemplate;
	private String serviceId;
	private String packageId;
	private String responsibleDepartmentId;
	private String actionDesc;

	// Constructors

	/** default constructor */
	public DoctorOrderTemplateDetails() {
	}

	/** minimal constructor */
	public DoctorOrderTemplateDetails(DoctorOrderTemplateDetailsId id,
			DoctorOrderTemplate doctorOrderTemplate) {
		this.id = id;
		this.doctorOrderTemplate = doctorOrderTemplate;
	}

	/** full constructor */
	public DoctorOrderTemplateDetails(DoctorOrderTemplateDetailsId id,
			DoctorOrderTemplate doctorOrderTemplate, String serviceId,
			String packageId, String responsibleDepartmentId, String actionDesc) {
		this.id = id;
		this.doctorOrderTemplate = doctorOrderTemplate;
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.responsibleDepartmentId = responsibleDepartmentId;
		this.actionDesc = actionDesc;
	}

	// Property accessors

	public DoctorOrderTemplateDetailsId getId() {
		return this.id;
	}

	public void setId(DoctorOrderTemplateDetailsId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public DoctorOrderTemplate getDoctorOrderTemplate() {
		return this.doctorOrderTemplate;
	}

	public void setDoctorOrderTemplate(DoctorOrderTemplate doctorOrderTemplate) {
		this.doctorOrderTemplate = doctorOrderTemplate;
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

	public String getResponsibleDepartmentId() {
		return this.responsibleDepartmentId;
	}

	public void setResponsibleDepartmentId(String responsibleDepartmentId) {
		this.responsibleDepartmentId = responsibleDepartmentId;
	}

	public String getActionDesc() {
		return this.actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

}