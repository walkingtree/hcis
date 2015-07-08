package com.wtc.hcis.da;

/**
 * VaccinationScheduleDetailsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class VaccinationScheduleDetailsId implements java.io.Serializable {

	// Fields

	private String scheduleName;
	private Integer seqNbr;

	// Constructors

	/** default constructor */
	public VaccinationScheduleDetailsId() {
	}

	/** full constructor */
	public VaccinationScheduleDetailsId(String scheduleName, Integer seqNbr) {
		this.scheduleName = scheduleName;
		this.seqNbr = seqNbr;
	}

	// Property accessors

	public String getScheduleName() {
		return this.scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public Integer getSeqNbr() {
		return this.seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VaccinationScheduleDetailsId))
			return false;
		VaccinationScheduleDetailsId castOther = (VaccinationScheduleDetailsId) other;

		return ((this.getScheduleName() == castOther.getScheduleName()) || (this
				.getScheduleName() != null
				&& castOther.getScheduleName() != null && this
				.getScheduleName().equals(castOther.getScheduleName())))
				&& ((this.getSeqNbr() == castOther.getSeqNbr()) || (this
						.getSeqNbr() != null
						&& castOther.getSeqNbr() != null && this.getSeqNbr()
						.equals(castOther.getSeqNbr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getScheduleName() == null ? 0 : this.getScheduleName()
						.hashCode());
		result = 37 * result
				+ (getSeqNbr() == null ? 0 : this.getSeqNbr().hashCode());
		return result;
	}

}