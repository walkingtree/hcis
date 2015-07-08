package com.wtc.hcis.ip.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AdmissionClaimRequest entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class AdmissionClaimRequest implements java.io.Serializable {

	// Fields

	private Long requestSequenceNbr;
	private Integer version;
	private InsurancePlans insurancePlans;
	private ClaimSponsor claimSponsor;
	private Admission admission;
	private SponsorClaimStatus sponsorClaimStatus;
	private Insurer insurer;
	private Integer claimSubsequenceNbr;
	private String policyNbr;
	private Date policyEffectiveFromDt;
	private Date policyEffectiveToDt;
	private Integer preferenceSequenceNbr;
	private String policyHolderName;
	private String createdBy;
	private Date createdDtm;
	private Date requestDtm;
	private Double requestedAmount;
	private String approvalThrough;
	private Date approvalDtm;
	private Double approvedAmount;
	private Double finalClaimedAmount;
	private Double patientAmount;
	private Date lastModifiedDtm;
	private Long billNbr;
	private String modifiedBy;
	private Set claimDocuments = new HashSet(0);

	// Constructors

	/** default constructor */
	public AdmissionClaimRequest() {
	}

	/** minimal constructor */
	public AdmissionClaimRequest(Long requestSequenceNbr,
			InsurancePlans insurancePlans, ClaimSponsor claimSponsor,
			Admission admission, Integer claimSubsequenceNbr,
			Date policyEffectiveToDt, Date createdDtm, Double requestedAmount) {
		this.requestSequenceNbr = requestSequenceNbr;
		this.insurancePlans = insurancePlans;
		this.claimSponsor = claimSponsor;
		this.admission = admission;
		this.claimSubsequenceNbr = claimSubsequenceNbr;
		this.policyEffectiveToDt = policyEffectiveToDt;
		this.createdDtm = createdDtm;
		this.requestedAmount = requestedAmount;
	}

	/** full constructor */
	public AdmissionClaimRequest(Long requestSequenceNbr,
			InsurancePlans insurancePlans, ClaimSponsor claimSponsor,
			Admission admission, SponsorClaimStatus sponsorClaimStatus,
			Insurer insurer, Integer claimSubsequenceNbr, String policyNbr,
			Date policyEffectiveFromDt, Date policyEffectiveToDt,
			Integer preferenceSequenceNbr, String policyHolderName,
			String createdBy, Date createdDtm, Date requestDtm,
			Double requestedAmount, String approvalThrough, Date approvalDtm,
			Double approvedAmount, Double finalClaimedAmount,
			Double patientAmount, Date lastModifiedDtm, Long billNbr,
			String modifiedBy, Set claimDocuments) {
		this.requestSequenceNbr = requestSequenceNbr;
		this.insurancePlans = insurancePlans;
		this.claimSponsor = claimSponsor;
		this.admission = admission;
		this.sponsorClaimStatus = sponsorClaimStatus;
		this.insurer = insurer;
		this.claimSubsequenceNbr = claimSubsequenceNbr;
		this.policyNbr = policyNbr;
		this.policyEffectiveFromDt = policyEffectiveFromDt;
		this.policyEffectiveToDt = policyEffectiveToDt;
		this.preferenceSequenceNbr = preferenceSequenceNbr;
		this.policyHolderName = policyHolderName;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
		this.requestDtm = requestDtm;
		this.requestedAmount = requestedAmount;
		this.approvalThrough = approvalThrough;
		this.approvalDtm = approvalDtm;
		this.approvedAmount = approvedAmount;
		this.finalClaimedAmount = finalClaimedAmount;
		this.patientAmount = patientAmount;
		this.lastModifiedDtm = lastModifiedDtm;
		this.billNbr = billNbr;
		this.modifiedBy = modifiedBy;
		this.claimDocuments = claimDocuments;
	}

	// Property accessors

	public Long getRequestSequenceNbr() {
		return this.requestSequenceNbr;
	}

	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public InsurancePlans getInsurancePlans() {
		return this.insurancePlans;
	}

	public void setInsurancePlans(InsurancePlans insurancePlans) {
		this.insurancePlans = insurancePlans;
	}

	public ClaimSponsor getClaimSponsor() {
		return this.claimSponsor;
	}

	public void setClaimSponsor(ClaimSponsor claimSponsor) {
		this.claimSponsor = claimSponsor;
	}

	public Admission getAdmission() {
		return this.admission;
	}

	public void setAdmission(Admission admission) {
		this.admission = admission;
	}

	public SponsorClaimStatus getSponsorClaimStatus() {
		return this.sponsorClaimStatus;
	}

	public void setSponsorClaimStatus(SponsorClaimStatus sponsorClaimStatus) {
		this.sponsorClaimStatus = sponsorClaimStatus;
	}

	public Insurer getInsurer() {
		return this.insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public Integer getClaimSubsequenceNbr() {
		return this.claimSubsequenceNbr;
	}

	public void setClaimSubsequenceNbr(Integer claimSubsequenceNbr) {
		this.claimSubsequenceNbr = claimSubsequenceNbr;
	}

	public String getPolicyNbr() {
		return this.policyNbr;
	}

	public void setPolicyNbr(String policyNbr) {
		this.policyNbr = policyNbr;
	}

	public Date getPolicyEffectiveFromDt() {
		return this.policyEffectiveFromDt;
	}

	public void setPolicyEffectiveFromDt(Date policyEffectiveFromDt) {
		this.policyEffectiveFromDt = policyEffectiveFromDt;
	}

	public Date getPolicyEffectiveToDt() {
		return this.policyEffectiveToDt;
	}

	public void setPolicyEffectiveToDt(Date policyEffectiveToDt) {
		this.policyEffectiveToDt = policyEffectiveToDt;
	}

	public Integer getPreferenceSequenceNbr() {
		return this.preferenceSequenceNbr;
	}

	public void setPreferenceSequenceNbr(Integer preferenceSequenceNbr) {
		this.preferenceSequenceNbr = preferenceSequenceNbr;
	}

	public String getPolicyHolderName() {
		return this.policyHolderName;
	}

	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
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

	public Date getRequestDtm() {
		return this.requestDtm;
	}

	public void setRequestDtm(Date requestDtm) {
		this.requestDtm = requestDtm;
	}

	public Double getRequestedAmount() {
		return this.requestedAmount;
	}

	public void setRequestedAmount(Double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}

	public String getApprovalThrough() {
		return this.approvalThrough;
	}

	public void setApprovalThrough(String approvalThrough) {
		this.approvalThrough = approvalThrough;
	}

	public Date getApprovalDtm() {
		return this.approvalDtm;
	}

	public void setApprovalDtm(Date approvalDtm) {
		this.approvalDtm = approvalDtm;
	}

	public Double getApprovedAmount() {
		return this.approvedAmount;
	}

	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}

	public Double getFinalClaimedAmount() {
		return this.finalClaimedAmount;
	}

	public void setFinalClaimedAmount(Double finalClaimedAmount) {
		this.finalClaimedAmount = finalClaimedAmount;
	}

	public Double getPatientAmount() {
		return this.patientAmount;
	}

	public void setPatientAmount(Double patientAmount) {
		this.patientAmount = patientAmount;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

	public Long getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Long billNbr) {
		this.billNbr = billNbr;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Set getClaimDocuments() {
		return this.claimDocuments;
	}

	public void setClaimDocuments(Set claimDocuments) {
		this.claimDocuments = claimDocuments;
	}

}