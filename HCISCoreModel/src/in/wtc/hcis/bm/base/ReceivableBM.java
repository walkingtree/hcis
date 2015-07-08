/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.util.Date;

/**
 * @author Bhavesh
 *
 */
public class ReceivableBM {

	private String receivableId;
	private Double amount;
	private Double remngAmount;
	private Date   transactionDate;
	
	public ReceivableBM(){
		
	}

	public String getReceivableId() {
		return receivableId;
	}

	public void setReceivableId(String receivableId) {
		this.receivableId = receivableId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getRemngAmount() {
		return remngAmount;
	}

	public void setRemngAmount(Double remngAmount) {
		this.remngAmount = remngAmount;
	}
		
}