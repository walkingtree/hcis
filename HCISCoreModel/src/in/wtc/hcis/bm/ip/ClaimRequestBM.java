/**
 * 
 */
package in.wtc.hcis.bm.ip;

import in.wtc.hcis.bm.base.CodeAndDescription;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
public class ClaimRequestBM implements Serializable {
	static final long serialVersionUID = 200907311459l;
	
	private Long 				requestSequenceNbr;			//Auto Increment
	private Integer 			admissionReqNbr;			//Not Null
	private Integer 			claimSubsequenceNbr;		//Not Null
	private String 				sponsorName;				//Not Null
	private String 				sponsorDesc;
	private String 				insurerName;
	private String 				insurerDesc;
	private String 				planCode;					//Not Null
	private String 				planName;					
	private String 				policyNbr;
	private CodeAndDescription 	claimStatusCd;
	private Date 				policyEffectiveFromDt;		//Not Null
	private Date 				policyEffectiveToDt;
	private String 				policyHolderName;
	private Integer 			preferenceSequenceNbr;
	private String 				createdBy;
	private Date 				createDtm;
	private Date 				requestDtm;
	private Double 				requestedAmount;
	private Double 				patientAmount;			//Amount patient has to pay
	private String 				approvalThrough;
	private Double 				approvedAmount;
	private Date 				approvalDate;
	private Double 				finalClaimedAmount;
	private Long 				billNbr;
	private Date 				lastModifiedDtm;
	private String 				modifiedBy;
	private List<ClaimDocumentBM> claimDocumentBMList;
	
/**
 * This optional Field should only be set when  a new Insurer Name is given for the Sponsor.
 * ContancDetailsBM need not to be set.
 * SponsorInsurerAssociation of sponsorDetailsBM should be null.*/
	private InsurerBM insurerBM;
	

	//UI Specific fields
	private String patientId;
	private String patientName;
	private String estimationGivenBy;
	private Double estimatedCost;
	private Double insuranceAmount;
	private Integer seqNbr; // to be used for UI grid as sequence number

	public Long getRequestSequenceNbr() {
		return requestSequenceNbr;
	}
	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}
	public Integer getClaimSubsequenceNbr() {
		return claimSubsequenceNbr;
	}
	public void setClaimSubsequenceNbr(Integer claimSubsequenceNbr) {
		this.claimSubsequenceNbr = claimSubsequenceNbr;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getPolicyNbr() {
		return policyNbr;
	}
	public void setPolicyNbr(String policyNbr) {
		this.policyNbr = policyNbr;
	}
	public CodeAndDescription getClaimStatusCd() {
		return claimStatusCd;
	}
	public void setClaimStatusCd(CodeAndDescription claimStatusCd) {
		this.claimStatusCd = claimStatusCd;
	}
	
	public String getPlanCode() {
		return planCode;
	}
	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Date getPolicyEffectiveFromDt() {
		return policyEffectiveFromDt;
	}
	public void setPolicyEffectiveFromDt(Date policyEffectiveFromDt) {
		this.policyEffectiveFromDt = policyEffectiveFromDt;
	}
	public Date getPolicyEffectiveToDt() {
		return policyEffectiveToDt;
	}
	public void setPolicyEffectiveToDt(Date policyEffectiveToDt) {
		this.policyEffectiveToDt = policyEffectiveToDt;
	}
	public Integer getPreferenceSequenceNbr() {
		return preferenceSequenceNbr;
	}
	public void setPreferenceSequenceNbr(Integer preferenceSequenceNbr) {
		this.preferenceSequenceNbr = preferenceSequenceNbr;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	public Date getRequestDtm() {
		return requestDtm;
	}
	public void setRequestDtm(Date requestDtm) {
		this.requestDtm = requestDtm;
	}
	public Double getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(Double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public String getApprovalThrough() {
		return approvalThrough;
	}
	public void setApprovalThrough(String approvalThrough) {
		this.approvalThrough = approvalThrough;
	}
	public Double getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public Date getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
	public Double getFinalClaimedAmount() {
		return finalClaimedAmount;
	}
	public void setFinalClaimedAmount(Double finalClaimedAmount) {
		this.finalClaimedAmount = finalClaimedAmount;
	}
	public Long getBillNbr() {
		return billNbr;
	}
	public void setBillNbr(Long billNbr) {
		this.billNbr = billNbr;
	}
	public Date getLastModifiedDtm() {
		return lastModifiedDtm;
	}
	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public List<ClaimDocumentBM> getClaimDocumentBMList() {
		return claimDocumentBMList;
	}
	public void setClaimDocumentBMList(List<ClaimDocumentBM> claimDocumentBMList) {
		this.claimDocumentBMList = claimDocumentBMList;
	}

	public String getSponsorDesc() {
		return sponsorDesc;
	}
	public void setSponsorDesc(String sponsorDesc) {
		this.sponsorDesc = sponsorDesc;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getInsurerName() {
		return insurerName;
	}
	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}
	public String getInsurerDesc() {
		return insurerDesc;
	}
	public void setInsurerDesc(String insurerDesc) {
		this.insurerDesc = insurerDesc;
	}
	public Double getPatientAmount() {
		return patientAmount;
	}
	public void setPatientAmount(Double patientAmount) {
		this.patientAmount = patientAmount;
	}
	public Integer getAdmissionReqNbr() {
		return admissionReqNbr;
	}
	public void setAdmissionReqNbr(Integer admissionReqNbr) {
		this.admissionReqNbr = admissionReqNbr;
	}
	public String getEstimationGivenBy() {
		return estimationGivenBy;
	}
	public void setEstimationGivenBy(String estimationGivenBy) {
		this.estimationGivenBy = estimationGivenBy;
	}
	public Double getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public String getPolicyHolderName() {
		return policyHolderName;
	}
	public void setPolicyHolderName(String policyHolderName) {
		this.policyHolderName = policyHolderName;
	}
	public InsurerBM getInsurerBM() {
		return insurerBM;
	}
	public void setInsurerBM(InsurerBM insurerBM) {
		this.insurerBM = insurerBM;
	}
	public Double getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(Double insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	
}
