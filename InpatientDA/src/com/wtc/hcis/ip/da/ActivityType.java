package com.wtc.hcis.ip.da;

import java.util.HashSet;
import java.util.Set;

/**
 * ActivityType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ActivityType implements java.io.Serializable {

	// Fields

	private String activityTypeCd;
	private String activityDesc;
	private String activityGroup;
	private Set bedActivities = new HashSet(0);
	private Set admissionActivities = new HashSet(0);
	private Set doctorOrderActivities = new HashSet(0);
	private Set claimSponsorSlas = new HashSet(0);
	private Set bedReservationActivities = new HashSet(0);
	private Set dischargeActivities = new HashSet(0);
	private Set admissionClaimActivities = new HashSet(0);

	// Constructors

	/** default constructor */
	public ActivityType() {
	}

	/** minimal constructor */
	public ActivityType(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}

	/** full constructor */
	public ActivityType(String activityTypeCd, String activityDesc,
			String activityGroup, Set bedActivities, Set admissionActivities,
			Set doctorOrderActivities, Set claimSponsorSlas,
			Set bedReservationActivities, Set dischargeActivities,
			Set admissionClaimActivities) {
		this.activityTypeCd = activityTypeCd;
		this.activityDesc = activityDesc;
		this.activityGroup = activityGroup;
		this.bedActivities = bedActivities;
		this.admissionActivities = admissionActivities;
		this.doctorOrderActivities = doctorOrderActivities;
		this.claimSponsorSlas = claimSponsorSlas;
		this.bedReservationActivities = bedReservationActivities;
		this.dischargeActivities = dischargeActivities;
		this.admissionClaimActivities = admissionClaimActivities;
	}

	// Property accessors

	public String getActivityTypeCd() {
		return this.activityTypeCd;
	}

	public void setActivityTypeCd(String activityTypeCd) {
		this.activityTypeCd = activityTypeCd;
	}

	public String getActivityDesc() {
		return this.activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	public String getActivityGroup() {
		return this.activityGroup;
	}

	public void setActivityGroup(String activityGroup) {
		this.activityGroup = activityGroup;
	}

	public Set getBedActivities() {
		return this.bedActivities;
	}

	public void setBedActivities(Set bedActivities) {
		this.bedActivities = bedActivities;
	}

	public Set getAdmissionActivities() {
		return this.admissionActivities;
	}

	public void setAdmissionActivities(Set admissionActivities) {
		this.admissionActivities = admissionActivities;
	}

	public Set getDoctorOrderActivities() {
		return this.doctorOrderActivities;
	}

	public void setDoctorOrderActivities(Set doctorOrderActivities) {
		this.doctorOrderActivities = doctorOrderActivities;
	}

	public Set getClaimSponsorSlas() {
		return this.claimSponsorSlas;
	}

	public void setClaimSponsorSlas(Set claimSponsorSlas) {
		this.claimSponsorSlas = claimSponsorSlas;
	}

	public Set getBedReservationActivities() {
		return this.bedReservationActivities;
	}

	public void setBedReservationActivities(Set bedReservationActivities) {
		this.bedReservationActivities = bedReservationActivities;
	}

	public Set getDischargeActivities() {
		return this.dischargeActivities;
	}

	public void setDischargeActivities(Set dischargeActivities) {
		this.dischargeActivities = dischargeActivities;
	}

	public Set getAdmissionClaimActivities() {
		return this.admissionClaimActivities;
	}

	public void setAdmissionClaimActivities(Set admissionClaimActivities) {
		this.admissionClaimActivities = admissionClaimActivities;
	}

}