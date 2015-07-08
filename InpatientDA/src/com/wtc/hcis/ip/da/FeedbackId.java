package com.wtc.hcis.ip.da;

/**
 * FeedbackId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FeedbackId implements java.io.Serializable {

	// Fields

	private Integer feedbackNumber;
	private String feedbackTypeCd;
	private Integer sequenceNbr;
	private Integer subsequenceNbr;

	// Constructors

	/** default constructor */
	public FeedbackId() {
	}

	/** full constructor */
	public FeedbackId(Integer feedbackNumber, String feedbackTypeCd,
			Integer sequenceNbr, Integer subsequenceNbr) {
		this.feedbackNumber = feedbackNumber;
		this.feedbackTypeCd = feedbackTypeCd;
		this.sequenceNbr = sequenceNbr;
		this.subsequenceNbr = subsequenceNbr;
	}

	// Property accessors

	public Integer getFeedbackNumber() {
		return this.feedbackNumber;
	}

	public void setFeedbackNumber(Integer feedbackNumber) {
		this.feedbackNumber = feedbackNumber;
	}

	public String getFeedbackTypeCd() {
		return this.feedbackTypeCd;
	}

	public void setFeedbackTypeCd(String feedbackTypeCd) {
		this.feedbackTypeCd = feedbackTypeCd;
	}

	public Integer getSequenceNbr() {
		return this.sequenceNbr;
	}

	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public Integer getSubsequenceNbr() {
		return this.subsequenceNbr;
	}

	public void setSubsequenceNbr(Integer subsequenceNbr) {
		this.subsequenceNbr = subsequenceNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FeedbackId))
			return false;
		FeedbackId castOther = (FeedbackId) other;

		return ((this.getFeedbackNumber() == castOther.getFeedbackNumber()) || (this
				.getFeedbackNumber() != null
				&& castOther.getFeedbackNumber() != null && this
				.getFeedbackNumber().equals(castOther.getFeedbackNumber())))
				&& ((this.getFeedbackTypeCd() == castOther.getFeedbackTypeCd()) || (this
						.getFeedbackTypeCd() != null
						&& castOther.getFeedbackTypeCd() != null && this
						.getFeedbackTypeCd().equals(
								castOther.getFeedbackTypeCd())))
				&& ((this.getSequenceNbr() == castOther.getSequenceNbr()) || (this
						.getSequenceNbr() != null
						&& castOther.getSequenceNbr() != null && this
						.getSequenceNbr().equals(castOther.getSequenceNbr())))
				&& ((this.getSubsequenceNbr() == castOther.getSubsequenceNbr()) || (this
						.getSubsequenceNbr() != null
						&& castOther.getSubsequenceNbr() != null && this
						.getSubsequenceNbr().equals(
								castOther.getSubsequenceNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFeedbackNumber() == null ? 0 : this.getFeedbackNumber()
						.hashCode());
		result = 37
				* result
				+ (getFeedbackTypeCd() == null ? 0 : this.getFeedbackTypeCd()
						.hashCode());
		result = 37
				* result
				+ (getSequenceNbr() == null ? 0 : this.getSequenceNbr()
						.hashCode());
		result = 37
				* result
				+ (getSubsequenceNbr() == null ? 0 : this.getSubsequenceNbr()
						.hashCode());
		return result;
	}

}