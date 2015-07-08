package com.wtc.hcis.billing.da;

/**
 * BillProcessRouting entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillProcessRouting implements java.io.Serializable {

	// Fields

	private BillProcessRoutingId id;
	private BillRegister billRegister;
	private Integer processSeqNbr;
	private String overrideImplClassName;

	// Constructors

	/** default constructor */
	public BillProcessRouting() {
	}

	/** minimal constructor */
	public BillProcessRouting(BillProcessRoutingId id,
			BillRegister billRegister, Integer processSeqNbr) {
		this.id = id;
		this.billRegister = billRegister;
		this.processSeqNbr = processSeqNbr;
	}

	/** full constructor */
	public BillProcessRouting(BillProcessRoutingId id,
			BillRegister billRegister, Integer processSeqNbr,
			String overrideImplClassName) {
		this.id = id;
		this.billRegister = billRegister;
		this.processSeqNbr = processSeqNbr;
		this.overrideImplClassName = overrideImplClassName;
	}

	// Property accessors

	public BillProcessRoutingId getId() {
		return this.id;
	}

	public void setId(BillProcessRoutingId id) {
		this.id = id;
	}

	public BillRegister getBillRegister() {
		return this.billRegister;
	}

	public void setBillRegister(BillRegister billRegister) {
		this.billRegister = billRegister;
	}

	public Integer getProcessSeqNbr() {
		return this.processSeqNbr;
	}

	public void setProcessSeqNbr(Integer processSeqNbr) {
		this.processSeqNbr = processSeqNbr;
	}

	public String getOverrideImplClassName() {
		return this.overrideImplClassName;
	}

	public void setOverrideImplClassName(String overrideImplClassName) {
		this.overrideImplClassName = overrideImplClassName;
	}

}