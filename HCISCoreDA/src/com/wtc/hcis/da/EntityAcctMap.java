package com.wtc.hcis.da;

import java.util.Date;

/**
 * EntityAcctMap entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EntityAcctMap implements java.io.Serializable {

	// Fields

	private EntityAcctMapId id;
	private Integer version;
	private Integer businessPartnerId;
	private String userId;
	private Date lastModifiedDtm;

	// Constructors

	/** default constructor */
	public EntityAcctMap() {
	}

	/** full constructor */
	public EntityAcctMap(EntityAcctMapId id, Integer businessPartnerId,
			String userId, Date lastModifiedDtm) {
		this.id = id;
		this.businessPartnerId = businessPartnerId;
		this.userId = userId;
		this.lastModifiedDtm = lastModifiedDtm;
	}

	// Property accessors

	public EntityAcctMapId getId() {
		return this.id;
	}

	public void setId(EntityAcctMapId id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getBusinessPartnerId() {
		return this.businessPartnerId;
	}

	public void setBusinessPartnerId(Integer businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLastModifiedDtm() {
		return this.lastModifiedDtm;
	}

	public void setLastModifiedDtm(Date lastModifiedDtm) {
		this.lastModifiedDtm = lastModifiedDtm;
	}

}