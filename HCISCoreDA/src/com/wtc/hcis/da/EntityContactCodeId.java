package com.wtc.hcis.da;

/**
 * EntityContactCodeId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class EntityContactCodeId implements java.io.Serializable {

	// Fields

	private Integer personelId;
	private String forEntityCode;
	private String contactTypeInd;

	// Constructors

	/** default constructor */
	public EntityContactCodeId() {
	}

	/** full constructor */
	public EntityContactCodeId(Integer personelId, String forEntityCode,
			String contactTypeInd) {
		this.personelId = personelId;
		this.forEntityCode = forEntityCode;
		this.contactTypeInd = contactTypeInd;
	}

	// Property accessors

	public Integer getPersonelId() {
		return this.personelId;
	}

	public void setPersonelId(Integer personelId) {
		this.personelId = personelId;
	}

	public String getForEntityCode() {
		return this.forEntityCode;
	}

	public void setForEntityCode(String forEntityCode) {
		this.forEntityCode = forEntityCode;
	}

	public String getContactTypeInd() {
		return this.contactTypeInd;
	}

	public void setContactTypeInd(String contactTypeInd) {
		this.contactTypeInd = contactTypeInd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntityContactCodeId))
			return false;
		EntityContactCodeId castOther = (EntityContactCodeId) other;

		return ((this.getPersonelId() == castOther.getPersonelId()) || (this
				.getPersonelId() != null
				&& castOther.getPersonelId() != null && this.getPersonelId()
				.equals(castOther.getPersonelId())))
				&& ((this.getForEntityCode() == castOther.getForEntityCode()) || (this
						.getForEntityCode() != null
						&& castOther.getForEntityCode() != null && this
						.getForEntityCode()
						.equals(castOther.getForEntityCode())))
				&& ((this.getContactTypeInd() == castOther.getContactTypeInd()) || (this
						.getContactTypeInd() != null
						&& castOther.getContactTypeInd() != null && this
						.getContactTypeInd().equals(
								castOther.getContactTypeInd())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getPersonelId() == null ? 0 : this.getPersonelId()
						.hashCode());
		result = 37
				* result
				+ (getForEntityCode() == null ? 0 : this.getForEntityCode()
						.hashCode());
		result = 37
				* result
				+ (getContactTypeInd() == null ? 0 : this.getContactTypeInd()
						.hashCode());
		return result;
	}

}