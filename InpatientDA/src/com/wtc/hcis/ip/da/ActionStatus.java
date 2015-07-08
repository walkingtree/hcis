package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ActionStatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ActionStatus implements java.io.Serializable {

	// Fields

	private String actionStatusCd;
	private String actionStatusDesc;
	private String customDesc;
	private String activeFlag;
	private Set doctorOrderDetailses = new HashSet(0);

	// Constructors

	/** default constructor */
	public ActionStatus() {
	}

	/** minimal constructor */
	public ActionStatus(String actionStatusCd) {
		this.actionStatusCd = actionStatusCd;
	}

	/** full constructor */
	public ActionStatus(String actionStatusCd, String actionStatusDesc,
			String customDesc, String activeFlag, Set doctorOrderDetailses) {
		this.actionStatusCd = actionStatusCd;
		this.actionStatusDesc = actionStatusDesc;
		this.customDesc = customDesc;
		this.activeFlag = activeFlag;
		this.doctorOrderDetailses = doctorOrderDetailses;
	}

	// Property accessors

	public String getActionStatusCd() {
		return this.actionStatusCd;
	}

	public void setActionStatusCd(String actionStatusCd) {
		this.actionStatusCd = actionStatusCd;
	}

	public String getActionStatusDesc() {
		return this.actionStatusDesc;
	}

	public void setActionStatusDesc(String actionStatusDesc) {
		this.actionStatusDesc = actionStatusDesc;
	}

	public String getCustomDesc() {
		return this.customDesc;
	}

	public void setCustomDesc(String customDesc) {
		this.customDesc = customDesc;
	}

	public String getActiveFlag() {
		return this.activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Set getDoctorOrderDetailses() {
		return this.doctorOrderDetailses;
	}

	public void setDoctorOrderDetailses(Set doctorOrderDetailses) {
		this.doctorOrderDetailses = doctorOrderDetailses;
	}

}