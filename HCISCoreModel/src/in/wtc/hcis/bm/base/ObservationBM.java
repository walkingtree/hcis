package in.wtc.hcis.bm.base;

import java.util.Date;


@SuppressWarnings("serial")
public class ObservationBM implements java.io.Serializable {

	// Fields

	private String observationSeqNo;
	private Integer appointmentNumber;
	private Date     appointmentDate;
	private CodeAndDescription doctor;
	private String observationText;
	private String remarks;
	private Date date;

	// Constructors

	/** default constructor */
	public ObservationBM() {
	}

	public String getObservationSeqNo() {
		return observationSeqNo;
	}

	public void setObservationSeqNo(String observationSeqNo) {
		this.observationSeqNo = observationSeqNo;
	}

	public Integer getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(Integer appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public CodeAndDescription getDoctor() {
		return doctor;
	}

	public void setDoctor(CodeAndDescription doctor) {
		this.doctor = doctor;
	}

	public String getObservationText() {
		return observationText;
	}

	public void setObservationText(String observationText) {
		this.observationText = observationText;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

}