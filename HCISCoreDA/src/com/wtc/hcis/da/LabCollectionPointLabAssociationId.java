package com.wtc.hcis.da;

/**
 * LabCollectionPointLabAssociationId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class LabCollectionPointLabAssociationId implements java.io.Serializable {

	// Fields

	private String labId;
	private String collectionPointId;

	// Constructors

	/** default constructor */
	public LabCollectionPointLabAssociationId() {
	}

	/** full constructor */
	public LabCollectionPointLabAssociationId(String labId,
			String collectionPointId) {
		this.labId = labId;
		this.collectionPointId = collectionPointId;
	}

	// Property accessors

	public String getLabId() {
		return this.labId;
	}

	public void setLabId(String labId) {
		this.labId = labId;
	}

	public String getCollectionPointId() {
		return this.collectionPointId;
	}

	public void setCollectionPointId(String collectionPointId) {
		this.collectionPointId = collectionPointId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LabCollectionPointLabAssociationId))
			return false;
		LabCollectionPointLabAssociationId castOther = (LabCollectionPointLabAssociationId) other;

		return ((this.getLabId() == castOther.getLabId()) || (this.getLabId() != null
				&& castOther.getLabId() != null && this.getLabId().equals(
				castOther.getLabId())))
				&& ((this.getCollectionPointId() == castOther
						.getCollectionPointId()) || (this
						.getCollectionPointId() != null
						&& castOther.getCollectionPointId() != null && this
						.getCollectionPointId().equals(
								castOther.getCollectionPointId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLabId() == null ? 0 : this.getLabId().hashCode());
		result = 37
				* result
				+ (getCollectionPointId() == null ? 0 : this
						.getCollectionPointId().hashCode());
		return result;
	}

}