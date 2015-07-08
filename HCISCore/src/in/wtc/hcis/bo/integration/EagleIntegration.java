package in.wtc.hcis.bo.integration;

import java.util.List;
import java.util.Map;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PaymentReceiptBM;
import in.wtc.hcis.bm.base.ReceivableBM;
import in.wtc.hcis.exceptions.HCISException;

public interface EagleIntegration {

	Integer createAccount(AccountInfoBM accountInfoBM, boolean createMapping ) throws HCISException;

	Integer createInvoice(Integer businessPartnerId, Double grandTotal, String description)
			throws HCISException;

	Integer createPayment(Integer businessPartnerId, Double payAmt,
			String bankAccountId, String remarks) throws HCISException;

	void createEntityAcctMap(Integer entityId, String entityType,
			Integer businessPartnerId, String userId) throws HCISException;

	Integer getBusinessPartnerId(Integer entityId, String entityType)
			throws HCISException;
	
	void modifyAccountDetails( AccountInfoBM modifiedAccountInfoBM);
	
	Integer createReceipt( 	Integer businessPartnerId, 
							Double payAmt ,
							Map<String,String> valueMap, 
							String createdBy,
							String invoiceId, String remarks );
	
	List<ReceivableBM> getReceivables( Integer businessPartnerId );
	
	List<CodeAndDescription> getCreditCardType();
	List<CodeAndDescription> getPaymentModes();
	List<CodeAndDescription> getTransactionTypes();
	
	List<PaymentReceiptBM> getPaymentReceipt( Integer businessPartnerId, String invoiceId );
}
