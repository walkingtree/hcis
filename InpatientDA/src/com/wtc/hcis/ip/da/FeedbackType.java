package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * FeedbackType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FeedbackType implements java.io.Serializable {

	// Fields

	private FeedbackTypeId id;
	private String description;
	private String involvedProcess;
	private String feedBackValueType;
	private Integer maximumAllowedSubsequence;
	private Set feedbacks = new HashSet(0);

	// Constructors

	/** default constructor */
	public FeedbackType() {
	}

	/** minimal constructor */
	public FeedbackType(FeedbackTypeId id) {
		this.id = id;
	}

	/** full constructor */
	public FeedbackType(FeedbackTypeId id, String description,
			String involvedProcess, String feedBackValueType,
			Integer maximumAllowedSubsequence, Set feedbacks) {
		this.id = id;
		this.description = description;
		this.involvedProcess = involvedProcess;
		this.feedBackValueType = feedBackValueType;
		this.maximumAllowedSubsequence = maximumAllowedSubsequence;
		this.feedbacks = feedbacks;
	}

	// Property accessors

	public FeedbackTypeId getId() {
		return this.id;
	}

	public void setId(FeedbackTypeId id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvolvedProcess() {
		return this.involvedProcess;
	}

	public void setInvolvedProcess(String involvedProcess) {
		this.involvedProcess = involvedProcess;
	}

	public String getFeedBackValueType() {
		return this.feedBackValueType;
	}

	public void setFeedBackValueType(String feedBackValueType) {
		this.feedBackValueType = feedBackValueType;
	}

	public Integer getMaximumAllowedSubsequence() {
		return this.maximumAllowedSubsequence;
	}

	public void setMaximumAllowedSubsequence(Integer maximumAllowedSubsequence) {
		this.maximumAllowedSubsequence = maximumAllowedSubsequence;
	}

	public Set getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(Set feedbacks) {
		this.feedbacks = feedbacks;
	}

}