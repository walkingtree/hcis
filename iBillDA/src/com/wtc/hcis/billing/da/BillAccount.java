package com.wtc.hcis.billing.da;

/**
 * BillAccount entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillAccount implements java.io.Serializable {

	// Fields

	private BillAccountId id;
	private String acctHolderName;
	private String billAddress;
	private String email;
	private String phone;

	// Constructors

	/** default constructor */
	public BillAccount() {
	}

	/** minimal constructor */
	public BillAccount(BillAccountId id, String acctHolderName,
			String billAddress) {
		this.id = id;
		this.acctHolderName = acctHolderName;
		this.billAddress = billAddress;
	}

	/** full constructor */
	public BillAccount(BillAccountId id, String acctHolderName,
			String billAddress, String email, String phone) {
		this.id = id;
		this.acctHolderName = acctHolderName;
		this.billAddress = billAddress;
		this.email = email;
		this.phone = phone;
	}

	// Property accessors

	public BillAccountId getId() {
		return this.id;
	}

	public void setId(BillAccountId id) {
		this.id = id;
	}

	public String getAcctHolderName() {
		return this.acctHolderName;
	}

	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}

	public String getBillAddress() {
		return this.billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}