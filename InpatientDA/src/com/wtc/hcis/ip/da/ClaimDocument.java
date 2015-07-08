package com.wtc.hcis.ip.da;

/**
 * ClaimDocument entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ClaimDocument implements java.io.Serializable {

	// Fields

	private ClaimDocumentId id;
	private Integer version;
	private AdmissionClaimRequest admissionClaimRequest;
	private String documentPath;
	private String createdBy;

	// Constructors

	/** default constructor */
	public ClaimDocument() {
	}

	/** minimal constructor */
	public ClaimDocument(ClaimDocumentId id,
			AdmissionClaimRequest admissionClaimRequest) {
		this.id = id;
		this.admissionClaimRequest = admissionClaimRequest;
	}

	/** full constructor */
	public ClaimDocument(ClaimDocumentId id,
			AdmissionClaimRequest admissionClaimRequest, String documentPath,
			String createdBy) {
		this.id = id;
		this.admissionClaimRequest = admissionClaimRequest;
		this.documentPath = documentPath;
		this.createdBy = createdBy;
	}

	// Property accessors

	public ClaimDocumentId getId() {
		return this.id;
	}

	public void setId(ClaimDocumentId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public AdmissionClaimRequest getAdmissionClaimRequest() {
		return this.admissionClaimRequest;
	}

	public void setAdmissionClaimRequest(
			AdmissionClaimRequest admissionClaimRequest) {
		this.admissionClaimRequest = admissionClaimRequest;
	}

	public String getDocumentPath() {
		return this.documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}