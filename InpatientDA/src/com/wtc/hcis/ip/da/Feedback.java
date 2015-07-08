package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * Feedback entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Feedback implements java.io.Serializable {

	// Fields

	private FeedbackId id;
	private Integer version;
	private FeedbackType feedbackType;
	private Date currentDtm;
	private String feedbackBy;
	private String entityType;
	private String feedbackValue;

	// Constructors

	/** default constructor */
	public Feedback() {
	}

	/** minimal constructor */
	public Feedback(FeedbackId id, FeedbackType feedbackType, Date currentDtm,
			String feedbackValue) {
		this.id = id;
		this.feedbackType = feedbackType;
		this.currentDtm = currentDtm;
		this.feedbackValue = feedbackValue;
	}

	/** full constructor */
	public Feedback(FeedbackId id, FeedbackType feedbackType, Date currentDtm,
			String feedbackBy, String entityType, String feedbackValue) {
		this.id = id;
		this.feedbackType = feedbackType;
		this.currentDtm = currentDtm;
		this.feedbackBy = feedbackBy;
		this.entityType = entityType;
		this.feedbackValue = feedbackValue;
	}

	// Property accessors

	public FeedbackId getId() {
		return this.id;
	}

	public void setId(FeedbackId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public FeedbackType getFeedbackType() {
		return this.feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	}

	public Date getCurrentDtm() {
		return this.currentDtm;
	}

	public void setCurrentDtm(Date currentDtm) {
		this.currentDtm = currentDtm;
	}

	public String getFeedbackBy() {
		return this.feedbackBy;
	}

	public void setFeedbackBy(String feedbackBy) {
		this.feedbackBy = feedbackBy;
	}

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getFeedbackValue() {
		return this.feedbackValue;
	}

	public void setFeedbackValue(String feedbackValue) {
		this.feedbackValue = feedbackValue;
	}

}