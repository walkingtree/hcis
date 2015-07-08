package com.wtc.hcis.da;

/**
 * EntityAcctMapId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EntityAcctMapId implements java.io.Serializable {

	// Fields

	private Integer entityId;
	private String entityType;

	// Constructors

	/** default constructor */
	public EntityAcctMapId() {
	}

	/** full constructor */
	public EntityAcctMapId(Integer entityId, String entityType) {
		this.entityId = entityId;
		this.entityType = entityType;
	}

	// Property accessors

	public Integer getEntityId() {
		return this.entityId;
	}

	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	public String getEntityType() {
		return this.entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntityAcctMapId))
			return false;
		EntityAcctMapId castOther = (EntityAcctMapId) other;

		return ((this.getEntityId() == castOther.getEntityId()) || (this
				.getEntityId() != null
				&& castOther.getEntityId() != null && this.getEntityId()
				.equals(castOther.getEntityId())))
				&& ((this.getEntityType() == castOther.getEntityType()) || (this
						.getEntityType() != null
						&& castOther.getEntityType() != null && this
						.getEntityType().equals(castOther.getEntityType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getEntityId() == null ? 0 : this.getEntityId().hashCode());
		result = 37
				* result
				+ (getEntityType() == null ? 0 : this.getEntityType()
						.hashCode());
		return result;
	}

}