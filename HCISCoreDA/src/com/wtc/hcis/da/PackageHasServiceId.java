package com.wtc.hcis.da;

/**
 * PackageHasServiceId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class PackageHasServiceId implements java.io.Serializable {

	// Fields

	private String packageId;
	private String serviceCode;

	// Constructors

	/** default constructor */
	public PackageHasServiceId() {
	}

	/** full constructor */
	public PackageHasServiceId(String packageId, String serviceCode) {
		this.packageId = packageId;
		this.serviceCode = serviceCode;
	}

	// Property accessors

	public String getPackageId() {
		return this.packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PackageHasServiceId))
			return false;
		PackageHasServiceId castOther = (PackageHasServiceId) other;

		return ((this.getPackageId() == castOther.getPackageId()) || (this
				.getPackageId() != null
				&& castOther.getPackageId() != null && this.getPackageId()
				.equals(castOther.getPackageId())))
				&& ((this.getServiceCode() == castOther.getServiceCode()) || (this
						.getServiceCode() != null
						&& castOther.getServiceCode() != null && this
						.getServiceCode().equals(castOther.getServiceCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPackageId() == null ? 0 : this.getPackageId().hashCode());
		result = 37
				* result
				+ (getServiceCode() == null ? 0 : this.getServiceCode()
						.hashCode());
		return result;
	}

}