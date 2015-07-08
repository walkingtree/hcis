package com.wtc.hcis.billing.da;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BillInfo entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BillInfo implements java.io.Serializable {

	// Fields

	private Long billNbr;
	private String accountId;
	private String clientName;
	private Date billDate;
	private Double billAmt;
	private Double currentBalance;
	private Date dueDate;
	private Double paidAmount;
	private Double previousBalance;
	private Date lastModifiedTime;
	private Date billFromDate;
	private Date billToDate;
	private Integer duplicateBillPrintCount;
	private Set fnclCharges = new HashSet(0);
	private Set billItemDetailses = new HashSet(0);
	private Set billActivityDetailses = new HashSet(0);
	private Set billProcessEvents = new HashSet(0);
	private Set fnclTrnsctSummaries = new HashSet(0);

	// Constructors

	/** default constructor */
	public BillInfo() {
	}

	/** minimal constructor */
	public BillInfo(String accountId, String clientName, Date billDate,
			Double billAmt, Date lastModifiedTime) {
		this.accountId = accountId;
		this.clientName = clientName;
		this.billDate = billDate;
		this.billAmt = billAmt;
		this.lastModifiedTime = lastModifiedTime;
	}

	/** full constructor */
	public BillInfo(String accountId, String clientName, Date billDate,
			Double billAmt, Double currentBalance, Date dueDate,
			Double paidAmount, Double previousBalance, Date lastModifiedTime,
			Date billFromDate, Date billToDate,
			Integer duplicateBillPrintCount, Set fnclCharges,
			Set billItemDetailses, Set billActivityDetailses,
			Set billProcessEvents, Set fnclTrnsctSummaries) {
		this.accountId = accountId;
		this.clientName = clientName;
		this.billDate = billDate;
		this.billAmt = billAmt;
		this.currentBalance = currentBalance;
		this.dueDate = dueDate;
		this.paidAmount = paidAmount;
		this.previousBalance = previousBalance;
		this.lastModifiedTime = lastModifiedTime;
		this.billFromDate = billFromDate;
		this.billToDate = billToDate;
		this.duplicateBillPrintCount = duplicateBillPrintCount;
		this.fnclCharges = fnclCharges;
		this.billItemDetailses = billItemDetailses;
		this.billActivityDetailses = billActivityDetailses;
		this.billProcessEvents = billProcessEvents;
		this.fnclTrnsctSummaries = fnclTrnsctSummaries;
	}

	// Property accessors

	public Long getBillNbr() {
		return this.billNbr;
	}

	public void setBillNbr(Long billNbr) {
		this.billNbr = billNbr;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Double getBillAmt() {
		return this.billAmt;
	}

	public void setBillAmt(Double billAmt) {
		this.billAmt = billAmt;
	}

	public Double getCurrentBalance() {
		return this.currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getPaidAmount() {
		return this.paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Double getPreviousBalance() {
		return this.previousBalance;
	}

	public void setPreviousBalance(Double previousBalance) {
		this.previousBalance = previousBalance;
	}

	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public Date getBillFromDate() {
		return this.billFromDate;
	}

	public void setBillFromDate(Date billFromDate) {
		this.billFromDate = billFromDate;
	}

	public Date getBillToDate() {
		return this.billToDate;
	}

	public void setBillToDate(Date billToDate) {
		this.billToDate = billToDate;
	}

	public Integer getDuplicateBillPrintCount() {
		return this.duplicateBillPrintCount;
	}

	public void setDuplicateBillPrintCount(Integer duplicateBillPrintCount) {
		this.duplicateBillPrintCount = duplicateBillPrintCount;
	}

	public Set getFnclCharges() {
		return this.fnclCharges;
	}

	public void setFnclCharges(Set fnclCharges) {
		this.fnclCharges = fnclCharges;
	}

	public Set getBillItemDetailses() {
		return this.billItemDetailses;
	}

	public void setBillItemDetailses(Set billItemDetailses) {
		this.billItemDetailses = billItemDetailses;
	}

	public Set getBillActivityDetailses() {
		return this.billActivityDetailses;
	}

	public void setBillActivityDetailses(Set billActivityDetailses) {
		this.billActivityDetailses = billActivityDetailses;
	}

	public Set getBillProcessEvents() {
		return this.billProcessEvents;
	}

	public void setBillProcessEvents(Set billProcessEvents) {
		this.billProcessEvents = billProcessEvents;
	}

	public Set getFnclTrnsctSummaries() {
		return this.fnclTrnsctSummaries;
	}

	public void setFnclTrnsctSummaries(Set fnclTrnsctSummaries) {
		this.fnclTrnsctSummaries = fnclTrnsctSummaries;
	}

}