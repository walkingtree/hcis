/**
 * 
 */
package in.wtc.billing.bm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BillDetailsBM implements Serializable {
	private String itemName;
	private Integer itemSequenceNbr;
	private Integer itemCount;
	private Double itemPrice;
	private Double discounts;
	private Double netPrice;
	private Date transactionDate;
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public Integer getItemSequenceNbr() {
		return itemSequenceNbr;
	}
	
	public void setItemSequenceNbr(Integer itemSequenceNbr) {
		this.itemSequenceNbr = itemSequenceNbr;
	}
	
	public Integer getItemCount() {
		return itemCount;
	}
	
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	
	public Double getItemPrice() {
		return itemPrice;
	}
	
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public Double getDiscounts() {
		return discounts;
	}
	
	public void setDiscounts(Double discounts) {
		this.discounts = discounts;
	}
	
	public Double getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public BillDetailsBM() {
	}
	
}
