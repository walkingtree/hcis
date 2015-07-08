package com.wtc.hcis.da;

/**
 * DeptEspecialityAssocId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DeptEspecialityAssocId implements java.io.Serializable {

	// Fields

	private String departmentCode;
	private String especialityCode;

	// Constructors

	/** default constructor */
	public DeptEspecialityAssocId() {
	}

	/** full constructor */
	public DeptEspecialityAssocId(String departmentCode, String especialityCode) {
		this.departmentCode = departmentCode;
		this.especialityCode = especialityCode;
	}

	// Property accessors

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getEspecialityCode() {
		return this.especialityCode;
	}

	public void setEspecialityCode(String especialityCode) {
		this.especialityCode = especialityCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DeptEspecialityAssocId))
			return false;
		DeptEspecialityAssocId castOther = (DeptEspecialityAssocId) other;

		return ((this.getDepartmentCode() == castOther.getDepartmentCode()) || (this
				.getDepartmentCode() != null
				&& castOther.getDepartmentCode() != null && this
				.getDepartmentCode().equals(castOther.getDepartmentCode())))
				&& ((this.getEspecialityCode() == castOther
						.getEspecialityCode()) || (this.getEspecialityCode() != null
						&& castOther.getEspecialityCode() != null && this
						.getEspecialityCode().equals(
								castOther.getEspecialityCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getDepartmentCode() == null ? 0 : this.getDepartmentCode()
						.hashCode());
		result = 37
				* result
				+ (getEspecialityCode() == null ? 0 : this.getEspecialityCode()
						.hashCode());
		return result;
	}

}