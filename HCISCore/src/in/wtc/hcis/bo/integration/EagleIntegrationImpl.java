package in.wtc.hcis.bo.integration;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.PaymentReceiptBM;
import in.wtc.hcis.bm.base.ReceivableBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.context.AppContext;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

import pl._3e.adinterface.DataField;
import pl._3e.adinterface.DataRow;
import pl._3e.adinterface.DataSet;
import pl._3e.adinterface.ModelADServiceClient;
import pl._3e.adinterface.extn.ModelADServiceClientExtn;
import pl._3e.adinterface.extn.ModelServiceConstants;

import com.wtc.hcis.da.EntityAcctMap;
import com.wtc.hcis.da.EntityAcctMapDAO;
import com.wtc.hcis.da.EntityAcctMapId;

public class EagleIntegrationImpl implements EagleIntegration {

	private EntityAcctMapDAO entityAcctMapDAO;
	
	
	public Integer createAccount(AccountInfoBM accountInfoBM, boolean createMapping ){ 
		
		try {
			String entityCategory = "";
			String entityRating = "";
			
			ContactDetailsBM contactDetailsBM = accountInfoBM.getContactDetailsBM();
			
			if( accountInfoBM.getEntityCategory() != null && accountInfoBM.getEntityCategory().getCode() != null ){
				entityCategory = accountInfoBM.getEntityCategory().getCode(); 
			}
			if( accountInfoBM.getEntityRating() != null && accountInfoBM.getEntityRating().getCode() != null ){
				entityRating = accountInfoBM.getEntityRating().getCode(); 
			}
			
			String tempId = null;//contactDetailsBM.getPersonelId() + contactDetailsBM.getForEntity().getCode();
			
			Integer businessPartnerId = this.createBPartner(tempId,
															contactDetailsBM.getFirstName(),
															contactDetailsBM.getMiddleName(),
															contactDetailsBM.getLastName(),
															entityCategory, entityRating);
			
			if ( businessPartnerId == null ){
				throw new Exception(" Failed to created Business Partner");
			}
			
			String stateCode = "";
			
			String countryCode = "";
			
			if( contactDetailsBM.getState() != null && contactDetailsBM.getState().getCode() != null ){
				stateCode = contactDetailsBM.getState().getCode();
			}
			
			if( contactDetailsBM.getCountry() != null && contactDetailsBM.getCountry().getCode() != null ){
				countryCode = contactDetailsBM.getCountry().getCode();
			}
			
			Integer locationId = this.createLocation(contactDetailsBM.getHouseNumber(),contactDetailsBM.getStreet(),
													contactDetailsBM.getCity(),stateCode,countryCode,
													contactDetailsBM.getPincode());

			if ( locationId == null ){
				throw new Exception(" Failed to created Lcation");
			}
			
			String salutationCode = "";
			
			
			if( contactDetailsBM.getSalutation() != null && contactDetailsBM.getSalutation().getCode() != null ){
				salutationCode = contactDetailsBM.getSalutation().getCode();
			}
			
			this.createContact(businessPartnerId, salutationCode,
							   contactDetailsBM.getFirstName(), 
							   contactDetailsBM.getMobileNumber(), contactDetailsBM.getPhoneNumber(),
							   contactDetailsBM.getEmail(), contactDetailsBM.getFaxNumber());

			this.createBPAddress(businessPartnerId, locationId, contactDetailsBM.getFirstName());
			
			if( createMapping){
				this.createEntityAcctMap(contactDetailsBM.getPersonelId(),
						contactDetailsBM.getForEntity().getCode(),
						businessPartnerId, contactDetailsBM.getCreatedBy());
			}
		
			return businessPartnerId;
		} catch ( Exception e) {
			e.printStackTrace();
			Fault fault = new Fault(ApplicationErrors.CREATE_ACCOUNT_FAILED);
			throw new HCISException(fault, e);
		}
	}
	
	
	private Integer createBPartner(String entityId, String firstName,
			String middleName, String lastName, String entityCategory,
			String entityRating) throws HCISException {

		Map<String, String> valuesMap = new HashMap<String, String>();
		
//		valuesMap.put("Value", String.valueOf(entityId));
		valuesMap.put("Name", String.valueOf(firstName));
		valuesMap.put("Name2", String.valueOf(middleName));
		valuesMap.put("Description", String.valueOf(lastName));
		valuesMap.put("C_BP_Group_ID", String.valueOf(entityCategory));
		valuesMap.put("Rating", String.valueOf(entityRating));
		
		// Invoke the web service
		Integer recordID = null;
		try{
			ModelADServiceClient client = new ModelADServiceClient();
			try {
				recordID = client.invokeEagleWebService(valuesMap, "createBPartner");
				
				if( recordID == null){
					throw new Exception();
				}
			} catch (Exception e) {
				Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
				throw new HCISException(fault, e);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		return recordID;
	}
	

	private Integer createLocation(String addressLine1, String addressLine2,
			String city, String stateCode, String countryCode, String pinCode)
			throws HCISException {
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("Address1", String.valueOf(addressLine1));
		valuesMap.put("Address2", String.valueOf(addressLine2));
		valuesMap.put("City", String.valueOf(city));
		valuesMap.put("C_Region_ID", String.valueOf(stateCode));
		valuesMap.put("C_Country_ID", String.valueOf(countryCode));
		valuesMap.put("Postal", String.valueOf(pinCode));

		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		Integer recordID;
		try {
			recordID = client.invokeEagleWebService(valuesMap, "createLocation");
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
			throw new HCISException(fault, e);
		}
		
		return recordID;
	}

	private Integer createBPAddress(Integer businessPartnerId, Integer locationId, String entityName) throws HCISException {
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("Name", String.valueOf(entityName));
		valuesMap.put("C_BPartner_ID", String.valueOf(businessPartnerId));
		valuesMap.put("C_Location_ID", String.valueOf(locationId));

		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		Integer recordID;
		try {
			recordID = client.invokeEagleWebService(valuesMap, "createBPAddress");
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
			throw new HCISException(fault, e);
		}
		
		return recordID;
	}
	
	private Integer createContact(Integer businessPartnerId,
			String salutionCode, String entityName, String phoneNbr,
			String phoneNbr2, String emailId, String faxNbr)
			throws HCISException {
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("C_BPartner_ID", String.valueOf(businessPartnerId));
		valuesMap.put("C_Greeting_ID", String.valueOf(salutionCode));
		valuesMap.put("Name", String.valueOf(entityName));
		valuesMap.put("Phone", String.valueOf(phoneNbr));
		valuesMap.put("Phone2", String.valueOf(phoneNbr2));
		valuesMap.put("Fax", String.valueOf(faxNbr));
		valuesMap.put("EMailUser", String.valueOf(emailId));

		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		Integer recordID;
		try {
			recordID = client.invokeEagleWebService(valuesMap, "createContact");
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
			throw new HCISException(fault, e);
		}
		
		return recordID;
	}

	public Integer createInvoice(Integer businessPartnerId, Double grandTotal,String description ) throws HCISException {
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("C_BPartner_ID", String.valueOf(businessPartnerId));
		valuesMap.put("GrandTotal", String.valueOf(grandTotal));
		valuesMap.put("Description", description);
		valuesMap.put("Processed", String.valueOf("Y"));
//TODO: need to add doc_type
		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		Integer recordID;
		try {
			recordID = client.invokeEagleWebService(valuesMap, "createInvoice");
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
			throw new HCISException(fault, e);
		}
		
		return recordID;
	}
	
	public Integer createPayment(Integer businessPartnerId,	Double payAmt, String bankAccountId, String remarks) throws HCISException {
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("C_BPartner_ID", String.valueOf(businessPartnerId));
		valuesMap.put("IsReceipt", String.valueOf("N"));
		valuesMap.put("C_Currency_ID", "304");//For Indian Rupee
		valuesMap.put("PayAmt", String.valueOf(payAmt));
		valuesMap.put("Description", remarks);
		
		if( bankAccountId != null && bankAccountId.length() > 0 ){
			valuesMap.put("C_BankAccount_ID", String.valueOf(bankAccountId));
		}else{
			valuesMap.put("C_BankAccount_ID", String.valueOf( 100 ));//bank account for generic client
		}

		
		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		Integer recordID;
		try {
			recordID = client.invokeEagleWebService(valuesMap, "createPayment");
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_RECEIPT_FALIED);
			throw new HCISException(fault, e);
		}
		
		return recordID;
	}

	public void createEntityAcctMap(Integer entityId, String entityType,
			Integer businessPartnerId, String userId) throws HCISException {
		try{
			entityAcctMapDAO =  (EntityAcctMapDAO)AppContext.getApplicationContext().getBean("EntityAcctMapDAO");
			
			EntityAcctMapId id = new EntityAcctMapId();
			id.setEntityId(entityId);
			id.setEntityType(entityType);
			
			EntityAcctMap entityAcctMap = new EntityAcctMap();
			entityAcctMap.setId(id);
			entityAcctMap.setBusinessPartnerId(businessPartnerId);
			entityAcctMap.setUserId(userId);
			entityAcctMap.setLastModifiedDtm(new Date());
			
			entityAcctMapDAO.save(entityAcctMap);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Integer getBusinessPartnerId(Integer entityId, String entityType) throws HCISException {
		try{
			
			if( entityAcctMapDAO == null ){
				
				entityAcctMapDAO =  (EntityAcctMapDAO)AppContext.getApplicationContext().getBean("EntityAcctMapDAO");
			}
			
			EntityAcctMapId id = new EntityAcctMapId();
			id.setEntityId(entityId);
			id.setEntityType(entityType);
			EntityAcctMap entityAcctMap = entityAcctMapDAO.findById(id);
			
			if( entityAcctMap == null ){
				 throw new Exception(" Business partner with id " + entityId+" is not exists.");
			}
			Integer bpId =entityAcctMap.getBusinessPartnerId(); 
			return bpId;
		}catch(Exception e){
			Fault fault = new Fault(ApplicationErrors.READ_BP_FAILED);
			throw new HCISException(fault);
		}
	}
	
	public void deleteEntityAcctMap(Integer entityId, String entityType) throws HCISException {
		EntityAcctMapId id = new EntityAcctMapId();
		id.setEntityId(entityId);
		id.setEntityType(entityType);
		EntityAcctMap entityAcctMap = entityAcctMapDAO.findById(id);
		entityAcctMapDAO.delete(entityAcctMap);
	}

	//TODO:Needs to be completed
	public void deleteAccount(Integer entityId, String entityType,
			String userId) throws HCISException {
		
		Integer businessPartnerId = null;
		EntityAcctMapId id = new EntityAcctMapId();
		id.setEntityId(entityId);
		id.setEntityType(entityType);
		
		EntityAcctMap entityAcctMap = new EntityAcctMap();
		entityAcctMap.setId(id);
		entityAcctMap.setBusinessPartnerId(businessPartnerId);
		entityAcctMap.setUserId(userId);
		entityAcctMap.setLastModifiedDtm(new Date());
		
		entityAcctMapDAO.save(entityAcctMap);
		
	}

	public void modifyAccountDetails( AccountInfoBM modifiedAccountInfoBM){
		
		Validate.notNull(modifiedAccountInfoBM, " AccountInfoBM must not be null ");

		ContactDetailsBM contactDetailsBM = modifiedAccountInfoBM.getContactDetailsBM();
		
		Validate.notNull(contactDetailsBM, " ContactDetailsBM must not be null ");
		
		Integer bpId = modifiedAccountInfoBM.getBusinessPartnerId();
		
		if( bpId == null ){
//			try to get it from EntityAcctMap
			
			bpId = this.getBusinessPartnerId(contactDetailsBM.getPersonelId(),
											 contactDetailsBM.getForEntity().getCode());
		}

		Map<String,String>bpValuesMap = new HashMap<String, String>(0);
		
		bpValuesMap.put("Name", String.valueOf(bpId));
		bpValuesMap.put("Name", String.valueOf(contactDetailsBM.getFirstName()));
		bpValuesMap.put("Name2", String.valueOf(contactDetailsBM.getMiddleName()));
		bpValuesMap.put("Description", String.valueOf(contactDetailsBM.getLastName()));
		
		String entityCategory = "";
		String entityRating = "";
		
		
		if( modifiedAccountInfoBM.getEntityCategory() != null &&
						modifiedAccountInfoBM.getEntityCategory().getCode() != null ){
			entityCategory = modifiedAccountInfoBM.getEntityCategory().getCode(); 
		}
		if( modifiedAccountInfoBM.getEntityRating() != null && 
						modifiedAccountInfoBM.getEntityRating().getCode() != null ){
			entityRating = modifiedAccountInfoBM.getEntityRating().getCode(); 
		}
		
		bpValuesMap.put("C_BP_Group_ID", String.valueOf(entityCategory));
		bpValuesMap.put("Rating", String.valueOf(entityRating));
		
		Integer recordID = null;

		try {
		
		Map<String,String> bpAdressResultMap = this.queryBPAddress( bpId );
		
		Map<String,String> locationValueMap = this.convertAccountInfo2LocationMap( modifiedAccountInfoBM );
		
		Integer locationId = Integer.parseInt(bpAdressResultMap.get("C_Location_ID"));
		
		Map<String,String>  addressValueMap = this.convertAccountInfo2addressMap( modifiedAccountInfoBM );
		Integer adUserId = Integer.parseInt(bpAdressResultMap.get("C_BPartner_Location_ID"));
		
		ModelADServiceClient client = new ModelADServiceClient();
				client.invokeUpdateWebService(bpId, bpValuesMap,"updateBPartner");
				client.invokeUpdateWebService(locationId, locationValueMap, "updateLocation");
				client.invokeUpdateWebService(adUserId, addressValueMap, "updateContact");
				
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_BP_FAILED);
			throw new HCISException(fault, e);
		}
	}
	
	private Map<String, String> queryBPAddress( Integer businessPartnerId ) throws Exception{
		  
		ModelADServiceClient client = new ModelADServiceClient();
		  
			 List<DataSet> list =  (List<DataSet>) client.invokeQueryWebService("C_BPartner_ID=" + businessPartnerId,
					 				"queryBPAddress");
			 
			 Map<String,String> reusltMap = new HashMap<String, String>(0);
			 if( list != null ){
				 for( DataSet dataSet : list ){
					 
					 List<DataRow> dataRowList = dataSet.getDataRow();
					 for( DataRow dataRow : dataRowList ){
						 
						 List<DataField> fieldList = dataRow.getField() ;
						 if( fieldList != null ){
							 for( DataField field : fieldList ){
								 
								 reusltMap.put(field.getColumn(), field.getVal());
							 }
						 }
					 }
				 }
			 }
			 return reusltMap;
	}
	
	private Map<String,String> convertAccountInfo2LocationMap(AccountInfoBM infoBM ){
		
		ContactDetailsBM contactDetailsBM = infoBM.getContactDetailsBM();		

		String stateCode = "";
		String countryCode = "";
		
		if( contactDetailsBM.getState() != null && contactDetailsBM.getState().getCode() != null ){
			stateCode = contactDetailsBM.getState().getCode();
		}
		
		if( contactDetailsBM.getCountry() != null && contactDetailsBM.getCountry().getCode() != null ){
			countryCode = contactDetailsBM.getCountry().getCode();
		}
		
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("Address1", String.valueOf(contactDetailsBM.getHouseNumber()));
		valuesMap.put("Address2", String.valueOf(contactDetailsBM.getStreet()));
		valuesMap.put("City", String.valueOf(contactDetailsBM.getCity()));
		valuesMap.put("C_Region_ID", String.valueOf(stateCode));
		valuesMap.put("C_Country_ID", String.valueOf(countryCode));
		valuesMap.put("Postal", String.valueOf(contactDetailsBM.getPincode()));
		
		return valuesMap;
	}
	
	private Map<String,String> convertAccountInfo2addressMap(AccountInfoBM infoBM ){
		
		ContactDetailsBM contactDetailsBM = infoBM.getContactDetailsBM();		

		String salutationCode = "";
		
		Map<String, String> valuesMap = new HashMap<String, String>(0);
		
		if( contactDetailsBM.getSalutation() != null && contactDetailsBM.getSalutation().getCode() != null ){
			salutationCode = contactDetailsBM.getSalutation().getCode();
		}
		valuesMap.put("C_BPartner_ID", String.valueOf(infoBM.getBusinessPartnerId()));
		valuesMap.put("C_Greeting_ID", String.valueOf(salutationCode));
		valuesMap.put("Name", String.valueOf(contactDetailsBM.getFirstName()));
		valuesMap.put("Phone", String.valueOf(contactDetailsBM.getMobileNumber()));
		valuesMap.put("Phone2", String.valueOf( contactDetailsBM.getPhoneNumber()));
		valuesMap.put("Fax", String.valueOf( contactDetailsBM.getFaxNumber()));
		valuesMap.put("EMailUser", String.valueOf( contactDetailsBM.getEmail()));

		
		return valuesMap;
	}

	public Integer createReceipt( Integer businessPartnerId, Double payAmt ,
				   				   Map<String,String> attrMap, String createdBy,
				   				   String invoiceId, String remarks ){
		
		try {
		Map<String, String> valuesMap = new HashMap<String, String>();
		
		valuesMap.put("C_BPartner_ID", String.valueOf(businessPartnerId));
		valuesMap.put("IsReceipt", String.valueOf("Y"));
		valuesMap.put("PayAmt", String.valueOf(payAmt));
		valuesMap.put("Description", remarks);
		valuesMap.put("Processed", String.valueOf("N"));
		valuesMap.put("C_Currency_ID", "304");//For Indian Rupee
		
		
		if( invoiceId != null && invoiceId.length() > 0 ){
			valuesMap.put("C_Invoice_ID",invoiceId );
		}
		
		if( attrMap != null && !attrMap.isEmpty()){
			
			valuesMap.putAll( attrMap );
		}
		
//		if( attrMap != null && attrMap.isEmpty() ){
//			
//			for( String attribute : (Set<String>)attrMap.keySet()){
//				
//				valuesMap.put(attribute, String.valueOf(attrMap.get(key)));
//			}
//		}
		
		String bankAccountId = valuesMap.get("C_BankAccount_ID");
		
		if( bankAccountId == null || bankAccountId.length() > 0 ){
			valuesMap.put("C_BankAccount_ID", String.valueOf( 100 ));//bank account for generic client
		}

		// Invoke the web service
		ModelADServiceClient client = new ModelADServiceClient();
		ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
		Integer recordID;
			recordID = client.invokeEagleWebService(valuesMap, "createPayment");
			
			clientExtn.runAutoAllocation( String.valueOf(businessPartnerId) );
			
			return recordID;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CREATE_RECEIPT_FALIED);
			throw new HCISException(fault, e);
		}
		
	}
	
	public List<ReceivableBM> getReceivables( Integer businessPartnerId ){
		
		
		try {
			List<Map<String, String>> receivableList = null;
			ModelADServiceClientExtn client = new ModelADServiceClientExtn();
			
			receivableList = client.getReceivables(businessPartnerId);
			
			
			List<ReceivableBM> receivableBMList = new ArrayList<ReceivableBM>(0);
			
			if( receivableList != null && !receivableList.isEmpty() ){
				
				for( Map<String,String> currentRvblMap : receivableList ){
					
					
					String invoiceId = currentRvblMap.get(ModelServiceConstants.COLUMN_INVOICE_ID);
					
					//Now get total allocated amount for this Invoice 
					List< Map<String,String> > result = client.getAllocationLine(businessPartnerId, Integer.valueOf(invoiceId));

					Double totalAssigedAmount = 0.0;
					
					if( result != null && !result.isEmpty()){

						for( Map<String,String> resultMap : result ){
							
							
							totalAssigedAmount=totalAssigedAmount+Double.valueOf( resultMap.get(ModelServiceConstants.COLUMN_ALLOCATION_AMOUNT));
						}
					}

					ReceivableBM receivableBM = new ReceivableBM();
					
					Double grandTotal = Double.valueOf(currentRvblMap.get("GrandTotal"));
					
					receivableBM.setAmount(grandTotal);
					receivableBM.setRemngAmount(grandTotal - totalAssigedAmount);
					receivableBM.setReceivableId( invoiceId );
					
					SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
					
					Date transactionDate = dateFormat.parse(currentRvblMap.get(ModelServiceConstants.COLUMN_INVOICE_CREATE_DTM));
					receivableBM.setTransactionDate(transactionDate);
					
					receivableBMList.add( receivableBM );
				}
			}
			return receivableBMList;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.READ_RECEIVABLE_FAILED);
			throw new HCISException(fault, e);
		}
		
	}
	
	public List<CodeAndDescription> getCreditCardType(){
		
		try {
			ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
			List<Map<String,String>> resultList = clientExtn.getCreditCardType();
			
			List<CodeAndDescription> cardTypeList = new ArrayList<CodeAndDescription>(0);
				
			if( resultList != null && !resultList.isEmpty() ){
				for( Map<String,String> currentMap : resultList ){
					
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(currentMap.get(ModelServiceConstants.REF_NAME));
					codeAndDescription.setDescription(currentMap.get(ModelServiceConstants.REF_VALUE));
					cardTypeList.add(codeAndDescription);
				}
			}
			
			return cardTypeList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CodeAndDescription> getTransactionTypes(){
		
		try {
			ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
			List<Map<String,String>> resultList = clientExtn.getTransactionTypes();
			
			List<CodeAndDescription> transactionType = new ArrayList<CodeAndDescription>(0);
				
			if( resultList != null && !resultList.isEmpty() ){
				for( Map<String,String> currentMap : resultList ){
					
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(currentMap.get(ModelServiceConstants.REF_NAME));
					codeAndDescription.setDescription(currentMap.get(ModelServiceConstants.REF_VALUE));
					transactionType.add(codeAndDescription);
				}
			}
			
			return transactionType;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CodeAndDescription> getPaymentModes(){

		try{
		ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
		List<Map<String,String>> resultList = clientExtn.getPaymentModes();
			
			List<CodeAndDescription> transactionType = new ArrayList<CodeAndDescription>(0);
				
			if( resultList != null && !resultList.isEmpty() ){
				for( Map<String,String> currentMap : resultList ){
					
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(currentMap.get(ModelServiceConstants.REF_NAME));
					codeAndDescription.setDescription(currentMap.get(ModelServiceConstants.REF_VALUE));
					transactionType.add(codeAndDescription);
				}
			}
			
			return transactionType;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<PaymentReceiptBM> getPaymentReceipt( Integer businessPartnerId, String invoiceId ){
		
		try {
			ModelADServiceClientExtn clientExtn = new ModelADServiceClientExtn();
			
			
			List<Map<String, String>> paymentReceiptList = null;
			
			paymentReceiptList = clientExtn.getPayments(businessPartnerId, invoiceId);
			
			List<PaymentReceiptBM> paymentReceiptBMList = new ArrayList<PaymentReceiptBM>(0);
			
			if( paymentReceiptList != null && !paymentReceiptList.isEmpty() ){
				
				for( Map<String,String> currentPymtlMap : paymentReceiptList ){
					
					
					PaymentReceiptBM paymentReceiptBM = new PaymentReceiptBM();
				
					paymentReceiptBM.setTransactionId(currentPymtlMap.get(
													ModelServiceConstants.COLUMN_PAYMENT_ID));
					paymentReceiptBM.setAmount(Double.valueOf(
												currentPymtlMap.get(ModelServiceConstants.COLUMN_PAYMENT_AMT )));
					
					SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
					
					paymentReceiptBM.setTransactionDate( dateFormat.parse( 
									currentPymtlMap.get( ModelServiceConstants.COLUMN_PAYMENT_TRX_DTM)));
				
					paymentReceiptBM.setRemngAmount(0.0);
					
					paymentReceiptBM.setTransactionType(currentPymtlMap.get( 
							ModelServiceConstants.COLUMN_IsReceipt));
					
					paymentReceiptBMList.add(paymentReceiptBM );
				}
			}
			return paymentReceiptBMList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
	}
	public void setEntityAcctMapDAO(EntityAcctMapDAO entityAcctMapDAO) {
		this.entityAcctMapDAO = entityAcctMapDAO;
	}


}
