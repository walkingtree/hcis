package com.wtc.hcis.billing.da;

/**
 * BillItemDetails entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillItemDetails implements java.io.Serializable {

	// Fields

	private BillItemDetailsId id;
	private BillInfo billInfo;
	private BillRegister billRegister;
	private String itemName;
	private Integer itemCount;
	private Double itemPrice;
	private Double discounts;
	private Double totalAmount;

	// Constructors

	/** default constructor */
	public BillItemDetails() {
	}

	/** minimal constructor */
	public BillItemDetails(BillItemDetailsId id, BillInfo billInfo,
			BillRegister billRegister, String itemName, Double totalAmount) {
		this.id = id;
		this.billInfo = billInfo;
		this.billRegister = billRegister;
		this.itemName = itemName;
		this.totalAmount = totalAmount;
	}

	/** full constructor */
	public BillItemDetails(BillItemDetailsId id, BillInfo billInfo,
			BillRegister billRegister, String itemName, Integer itemCount,
			Double itemPrice, Double discounts, Double totalAmount) {
		this.id = id;
		this.billInfo = billInfo;
		this.billRegister = billRegister;
		this.itemName = itemName;
		this.itemCount = itemCount;
		this.itemPrice = itemPrice;
		this.discounts = discounts;
		this.totalAmount = totalAmount;
	}

	// Property accessors

	public BillItemDetailsId getId() {
		return this.id;
	}

	public void setId(BillItemDetailsId id) {
		this.id = id;
	}

	public BillInfo getBillInfo() {
		return this.billInfo;
	}

	public void setBillInfo(BillInfo billInfo) {
		this.billInfo = billInfo;
	}

	public BillRegister getBillRegister() {
		return this.billRegister;
	}

	public void setBillRegister(BillRegister billRegister) {
		this.billRegister = billRegister;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemCount() {
		return this.itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Double getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Double getDiscounts() {
		return this.discounts;
	}

	public void setDiscounts(Double discounts) {
		this.discounts = discounts;
	}

	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}