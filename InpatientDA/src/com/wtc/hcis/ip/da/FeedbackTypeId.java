package com.wtc.hcis.ip.da;

/**
 * FeedbackTypeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FeedbackTypeId implements java.io.Serializable {

	// Fields

	private String feedbackTypeCd;
	private Integer feedbackSequenceNbr;

	// Constructors

	/** default constructor */
	public FeedbackTypeId() {
	}

	/** full constructor */
	public FeedbackTypeId(String feedbackTypeCd, Integer feedbackSequenceNbr) {
		this.feedbackTypeCd = feedbackTypeCd;
		this.feedbackSequenceNbr = feedbackSequenceNbr;
	}

	// Property accessors

	public String getFeedbackTypeCd() {
		return this.feedbackTypeCd;
	}

	public void setFeedbackTypeCd(String feedbackTypeCd) {
		this.feedbackTypeCd = feedbackTypeCd;
	}

	public Integer getFeedbackSequenceNbr() {
		return this.feedbackSequenceNbr;
	}

	public void setFeedbackSequenceNbr(Integer feedbackSequenceNbr) {
		this.feedbackSequenceNbr = feedbackSequenceNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FeedbackTypeId))
			return false;
		FeedbackTypeId castOther = (FeedbackTypeId) other;

		return ((this.getFeedbackTypeCd() == castOther.getFeedbackTypeCd()) || (this
				.getFeedbackTypeCd() != null
				&& castOther.getFeedbackTypeCd() != null && this
				.getFeedbackTypeCd().equals(castOther.getFeedbackTypeCd())))
				&& ((this.getFeedbackSequenceNbr() == castOther
						.getFeedbackSequenceNbr()) || (this
						.getFeedbackSequenceNbr() != null
						&& castOther.getFeedbackSequenceNbr() != null && this
						.getFeedbackSequenceNbr().equals(
								castOther.getFeedbackSequenceNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getFeedbackTypeCd() == null ? 0 : this.getFeedbackTypeCd()
						.hashCode());
		result = 37
				* result
				+ (getFeedbackSequenceNbr() == null ? 0 : this
						.getFeedbackSequenceNbr().hashCode());
		return result;
	}

}