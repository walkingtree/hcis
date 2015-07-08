/**
 * 
 */
package pl._3e.adinterface.extn;

/**
 * @author Bhavesh
 *
 */
public interface ModelServiceConstants {

	static String SERVICE_TYPE_AOUTOALLOCATION = "autoAllocation";

	static String COLUMN_CREATED = "Created";

	static String COLUMN_INVOICE_TYPE_ID = "C_DocType_ID";
	static String INVOICE_TYPE_RECEIVABLE = "1000002";
	static String COLUMN_BPartner_ID = "C_BPartner_ID";
	static String COLUMN_INVOICE_DATE = "DateInvoiced";
	static String COLUMN_INVOICE_CREATE_DTM = "Created";
	static String COLUMN_BPARTNER_CREATE_DTM = "Created";
	static String COLUMN_IsReceipt = "IsReceipt";
	static String COLUMN_PAYMENT_AMT = "PayAmt";
	static String COLUMN_PAYMENT_TRX_DTM = "DateTrx";
	static String COLUMN_ORDER_TRX_DTM = "DateOrdered";
	static String COLUMN_PAYMENT_ID = "C_Payment_ID";
	static String COLUMN_INVOICE_ID = "C_Invoice_ID";
	static String COLUMN_ORDER_ID = "C_Order_ID";

	static String COLUMN_INVOICE_GRAND_TOTAL = "Grand_Total";
	static String COLUMN_ORDER_GRAND_TOTAL = "GrandTotal";
	static String COL_INVOICE_GRAND_TOTAL = "GrandTotal";
	// Allocation line
	static String COLUMN_ALLOCATION_AMOUNT = "Amount";
	static String COLUMN_ALLOCATION_DISCOUNT = "DiscountAmt";
	static String COLUMN_ALLOCATION_WRITE_OFF = "WriteOffAmt";

	static String REF_VALUE = "Value";
	static String REF_NAME = "Name";

	static String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

}
