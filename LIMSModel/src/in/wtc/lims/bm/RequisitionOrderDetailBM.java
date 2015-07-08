package in.wtc.lims.bm;


import java.util.Date;


public class RequisitionOrderDetailBM {
	
	private Integer requisitionOrderNbr;
	private CodeAndDescription testName;
	private Date testDate;
	private Double charges;
	private  CodeAndDescription status;
	private Integer unit;
	private Integer serviceUID;
	private String isBillable;
	private String type; //ServiceType
	
	public Integer getRequisitionOrderNbr() {
		return requisitionOrderNbr;
	}
	public void setRequisitionOrderNbr(Integer requisitionOrderNbr) {
		this.requisitionOrderNbr = requisitionOrderNbr;
	}
	public CodeAndDescription getTestName() {
		return testName;
	}
	public void setTestName(CodeAndDescription testName) {
		this.testName = testName;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Double getCharges() {
		return charges;
	}
	public void setCharges(Double charges) {
		this.charges = charges;
	}
	public CodeAndDescription getStatus() {
		return status;
	}
	public void setStatus(CodeAndDescription status) {
		this.status = status;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public Integer getServiceUID() {
		return serviceUID;
	}
	public void setServiceUID(Integer serviceUID) {
		this.serviceUID = serviceUID;
	}
	public String getIsBillable() {
		return isBillable;
	}
	public void setIsBillable(String isBillable) {
		this.isBillable = isBillable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

}
