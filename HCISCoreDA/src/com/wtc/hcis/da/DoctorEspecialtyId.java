package com.wtc.hcis.da;

/**
 * DoctorEspecialtyId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class DoctorEspecialtyId implements java.io.Serializable {

	// Fields

	private String especialtyCode;
	private Integer doctorId;
	private String departmentCode;

	// Constructors

	/** default constructor */
	public DoctorEspecialtyId() {
	}

	/** full constructor */
	public DoctorEspecialtyId(String especialtyCode, Integer doctorId,
			String departmentCode) {
		this.especialtyCode = especialtyCode;
		this.doctorId = doctorId;
		this.departmentCode = departmentCode;
	}

	// Property accessors

	public String getEspecialtyCode() {
		return this.especialtyCode;
	}

	public void setEspecialtyCode(String especialtyCode) {
		this.especialtyCode = especialtyCode;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DoctorEspecialtyId))
			return false;
		DoctorEspecialtyId castOther = (DoctorEspecialtyId) other;

		return ((this.getEspecialtyCode() == castOther.getEspecialtyCode()) || (this
				.getEspecialtyCode() != null
				&& castOther.getEspecialtyCode() != null && this
				.getEspecialtyCode().equals(castOther.getEspecialtyCode())))
				&& ((this.getDoctorId() == castOther.getDoctorId()) || (this
						.getDoctorId() != null
						&& castOther.getDoctorId() != null && this
						.getDoctorId().equals(castOther.getDoctorId())))
				&& ((this.getDepartmentCode() == castOther.getDepartmentCode()) || (this
						.getDepartmentCode() != null
						&& castOther.getDepartmentCode() != null && this
						.getDepartmentCode().equals(
								castOther.getDepartmentCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getEspecialtyCode() == null ? 0 : this.getEspecialtyCode()
						.hashCode());
		result = 37 * result
				+ (getDoctorId() == null ? 0 : this.getDoctorId().hashCode());
		result = 37
				* result
				+ (getDepartmentCode() == null ? 0 : this.getDepartmentCode()
						.hashCode());
		return result;
	}

}