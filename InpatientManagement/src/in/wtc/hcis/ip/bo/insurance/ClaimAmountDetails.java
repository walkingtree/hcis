/**
 * 
 */
package in.wtc.hcis.ip.bo.insurance;

/**
 * @author Bhavesh
 *
 */
public class ClaimAmountDetails {
	private Long requestSequenceNbr;
	private Double requestedAmount;
	private Double approvedAmount;
	private Double finalClaimedAmount;
	private Double additionalClaimedAmount;
	private Double patientAmount;
	private Double additionalPatientAmount;
	
	
	public Long getRequestSequenceNbr() {
		return requestSequenceNbr;
	}
	public void setRequestSequenceNbr(Long requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}
	public Double getRequestedAmount() {
		return requestedAmount;
	}
	public void setRequestedAmount(Double requestedAmount) {
		this.requestedAmount = requestedAmount;
	}
	public Double getApprovedAmount() {
		return approvedAmount;
	}
	public void setApprovedAmount(Double approvedAmount) {
		this.approvedAmount = approvedAmount;
	}
	public Double getFinalClaimedAmount() {
		return finalClaimedAmount;
	}
	public void setFinalClaimedAmount(Double finalClaimedAmount) {
		this.finalClaimedAmount = finalClaimedAmount;
	}
	public Double getPatientAmount() {
		return patientAmount;
	}
	public void setPatientAmount(Double patientAmount) {
		this.patientAmount = patientAmount;
	}
	public Double getAdditionalClaimedAmount() {
		return additionalClaimedAmount;
	}
	public void setAdditionalClaimedAmount(Double additionalClaimedAmount) {
		this.additionalClaimedAmount = additionalClaimedAmount;
	}
	public Double getAdditionalPatientAmount() {
		return additionalPatientAmount;
	}
	public void setAdditionalPatientAmount(Double additionalPatientAmount) {
		this.additionalPatientAmount = additionalPatientAmount;
	}
	
}
