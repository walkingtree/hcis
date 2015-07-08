package com.wtc.hcis.ip.da;

import java.util.Date;

/**
 * SponsorInsurerAssociation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SponsorInsurerAssociation implements java.io.Serializable {

	// Fields

	private SponsorInsurerAssociationId id;
	private Integer version;
	private ClaimSponsor claimSponsor;
	private Insurer insurer;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	private String createdBy;
	private Date createdDtm;

	// Constructors

	/** default constructor */
	public SponsorInsurerAssociation() {
	}

	/** minimal constructor */
	public SponsorInsurerAssociation(SponsorInsurerAssociationId id,
			ClaimSponsor claimSponsor, Insurer insurer, String createdBy,
			Date createdDtm) {
		this.id = id;
		this.claimSponsor = claimSponsor;
		this.insurer = insurer;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	/** full constructor */
	public SponsorInsurerAssociation(SponsorInsurerAssociationId id,
			ClaimSponsor claimSponsor, Insurer insurer, Date effectiveFromDt,
			Date effectiveToDt, String createdBy, Date createdDtm) {
		this.id = id;
		this.claimSponsor = claimSponsor;
		this.insurer = insurer;
		this.effectiveFromDt = effectiveFromDt;
		this.effectiveToDt = effectiveToDt;
		this.createdBy = createdBy;
		this.createdDtm = createdDtm;
	}

	// Property accessors

	public SponsorInsurerAssociationId getId() {
		return this.id;
	}

	public void setId(SponsorInsurerAssociationId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ClaimSponsor getClaimSponsor() {
		return this.claimSponsor;
	}

	public void setClaimSponsor(ClaimSponsor claimSponsor) {
		this.claimSponsor = claimSponsor;
	}

	public Insurer getInsurer() {
		return this.insurer;
	}

	public void setInsurer(Insurer insurer) {
		this.insurer = insurer;
	}

	public Date getEffectiveFromDt() {
		return this.effectiveFromDt;
	}

	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}

	public Date getEffectiveToDt() {
		return this.effectiveToDt;
	}

	public void setEffectiveToDt(Date effectiveToDt) {
		this.effectiveToDt = effectiveToDt;
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

}