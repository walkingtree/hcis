/**
 * 
 */
package pl._3e.adinterface.extn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl._3e.adinterface.ADLoginRequest;
import pl._3e.adinterface.DataField;
import pl._3e.adinterface.DataRow;
import pl._3e.adinterface.DataSet;
import pl._3e.adinterface.ModelADServiceClient;
import pl._3e.adinterface.ModelADServicePortType;
import pl._3e.adinterface.ModelRunProcess;
import pl._3e.adinterface.ModelRunProcessRequest;
import pl._3e.adinterface.RunProcessResponse;

/**
 * @author Bhavesh
 *
 */
public class ModelADServiceClientExtn extends ModelADServiceClient {

	public ModelADServiceClientExtn(){
		super();
	}
	
	
	public List< Map<String,String> > getReceivables( Integer BPartnerId) throws Exception{
		  
		try {
			//		ModelADServiceClient client = new ModelADServiceClient();
					  
					String filterValue = "C_BPartner_ID="+ BPartnerId+ " and C_DocType_ID=1000002";// and IsPaid=N ";
						 
					List<DataSet> list =  (List<DataSet>)invokeQueryWebService(filterValue,"getInvoice");
						 
				
					List<Map<String,String>> resultList = this.getQueryResult(list);
					
					return resultList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	public List <Map<String,String>> getInoice( Integer BPartnerId ) throws Exception{
		
		try {
			//		ModelADServiceClient client = new ModelADServiceClient();
					  
					String filterValue = "C_BPartner_ID="+ BPartnerId;
						 
					List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterValue,"getInvoice");
						 
				
					List<Map<String,String>> resultList = this.getQueryResult(list);
					
					return resultList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		
	}
	
	private List<Map<String,String>> getQueryResult(List<DataSet> list){
		
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>(0);
		
		 if( list != null ){
			 for( DataSet dataSet : list){
				 
				 Map<String,String> reusltMap = new HashMap<String, String>(0); 
				 List<DataRow> dataRowList = dataSet.getDataRow();
				 for( DataRow dataRow : dataRowList ){
					 
					 List<DataField> fieldList = dataRow.getField() ;
					 if( fieldList != null ){
						 for( DataField field : fieldList ){
							 reusltMap.put(field.getColumn(), field.getVal());
							 
						 }
					 }
					 resultList.add( reusltMap );
				 }
			 }
		 }

		 return resultList;
	}

	
	public List< Map<String,String> > getBPartener( Integer BPartenerId) throws Exception{
	
		String filterValue = "C_BPartner_ID="+ BPartenerId;
		 
		List<DataSet> list =  (List<DataSet>) new ModelADServiceClient().invokeQueryWebService(filterValue,"getBPartener");
			 
	
		List<Map<String,String>> resultList = this.getQueryResult(list);
		
		return resultList;
		
	}
	
	public List< Map<String,String> > getPayments(Integer BPartenerId,Date createFromDtm, Date createdToDtm) throws Exception{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
		
		String filterQuery =  ModelServiceConstants.COLUMN_BPartner_ID +"=" + BPartenerId +" and "+
							  ModelServiceConstants.COLUMN_IsReceipt +" ='N' "+ " and "+
							  ModelServiceConstants.COLUMN_CREATED+ ">='"+ dateFormat.format(createFromDtm)+ "' and " +
							  ModelServiceConstants.COLUMN_CREATED+"<='"+dateFormat.format(createdToDtm)+"'";
		
		List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterQuery,"getPayment");
		
		return this.getQueryResult(list);
	}
	
	public List< Map<String,String> > getReceipts(Integer BPartenerId,Date createFromDtm, Date createdToDtm) throws Exception{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
		String filterQuery =  ModelServiceConstants.COLUMN_BPartner_ID +"=" + BPartenerId + " and "+
							  ModelServiceConstants.COLUMN_IsReceipt +" ='Y' "+ " and "+
							  ModelServiceConstants.COLUMN_CREATED+ ">='"+ dateFormat.format(createFromDtm)+ "' and " +
							  ModelServiceConstants.COLUMN_CREATED+"<='"+dateFormat.format(createdToDtm)+"'";

		List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterQuery,"getPayment");
		
		return this.getQueryResult(list);
	}
	
	public List< Map<String,String> > getPayments(Integer BPartenerId,String invoiceId) throws Exception{
		
		String filterQuery =  ModelServiceConstants.COLUMN_BPartner_ID +"=" + BPartenerId + " and "+
							  ModelServiceConstants.COLUMN_INVOICE_ID +"="+ invoiceId;

		List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterQuery,"getPayment");
		
		return this.getQueryResult(list);
	}

	public void runAutoAllocation(String BPartenerId ){
		
		Map<String, String> paramValueMap = new HashMap<String, String>(0);
		
		paramValueMap.put(ModelServiceConstants.COLUMN_BPartner_ID, String.valueOf(BPartenerId));
		paramValueMap.put("AllocateOldest", "Y");
		paramValueMap.put("APAR", "A");
		
		this.runProcess(ModelServiceConstants.SERVICE_TYPE_AOUTOALLOCATION, paramValueMap);
		
		
	}
	
	public void runProcess( String processName,Map<String,String> paramValueMap){
		
		ADLoginRequest aLoginRequest = getADLoginRequest();

		ModelRunProcessRequest runProcessRequest = new ModelRunProcessRequest();
		runProcessRequest.setADLoginRequest(aLoginRequest);
		
		ModelRunProcess modelRunProcess = new ModelRunProcess();
		modelRunProcess.setServiceType(processName);
		modelRunProcess.setParamValues( this.convertParamMap2ParamValues(paramValueMap));
		
		runProcessRequest.setModelRunProcess(modelRunProcess);
		ModelADServicePortType modelADServicePortType = getModelADServiceHttpPort();
		RunProcessResponse response = modelADServicePortType.runProcess(runProcessRequest);
		
		System.out.println(response);
	}
	
	private DataRow convertParamMap2ParamValues(Map<String,String> paramValue){
		
		Set<String> keySet = paramValue.keySet();
		
		DataRow dataRow = new DataRow();
		
		List<DataField> fieldList = dataRow.getField();
		
		if( keySet != null && !keySet.isEmpty()){
			for( String key : keySet ){
				
				DataField dataField = new DataField();
				dataField.setColumn(key);
				dataField.setVal(paramValue.get(key));
				
				fieldList.add(dataField);
			}
		}
		
		return dataRow;
	}
	
	public Integer createInvoice( Integer BPartenerId, Double amount,String description ) throws Exception{
		
		try {
			Map<String, String> valuesMap = new HashMap<String, String>();
			
			valuesMap.put(ModelServiceConstants.COLUMN_BPartner_ID, String.valueOf(BPartenerId));
			valuesMap.put("GrandTotal", String.valueOf(amount));
			valuesMap.put("Description", description);
			valuesMap.put("Processed", String.valueOf("Y"));
			valuesMap.put(ModelServiceConstants.COLUMN_INVOICE_TYPE_ID, ModelServiceConstants.INVOICE_TYPE_RECEIVABLE);//default for customer invoice  
			// Invoke the web service
			ModelADServiceClient client = new ModelADServiceClient();
			Integer recordID;
			recordID = client.invokeEagleWebService(valuesMap, "createInvoice");
			
			return recordID;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<Map<String,String>>  getAllocationLine( Integer BPartenerId,Integer invoiceId ) throws Exception{
	
		String filterQuery = ModelServiceConstants.COLUMN_BPartner_ID +"="+BPartenerId
							+" and "+ModelServiceConstants.COLUMN_INVOICE_ID +"="+invoiceId;
		
		List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterQuery,"getAllocationLine");
		
		return this.getQueryResult(list);
	}
	
	
	public void updateInvoice(Integer invoiceId,Double newAmount ){
		
		Map<String,String> paramValuesMap = new HashMap<String, String>(0);
		paramValuesMap.put(ModelServiceConstants.COL_INVOICE_GRAND_TOTAL, String.valueOf(newAmount));
		invokeUpdateWebService(invoiceId, paramValuesMap,"updateInvoice");
		
	}
	
	public List<Map<String,String>> getPaymentModes() throws Exception{
	
		List<DataSet> list = this.invokeListWebService(null, "getPaymentModes");
		return this.getQueryResult(list);
	}
	
	public List<Map<String,String>> getTransactionTypes() throws Exception{
		
		List<DataSet> list = this.invokeListWebService(null, "getTransactionTypes");
		return this.getQueryResult(list);
	}
	
	public List<Map<String,String>> getCreditCardType() throws Exception{
		
		List<DataSet> list = this.invokeListWebService(null, "getCreditCardType");
		return this.getQueryResult(list);
	}
	
	public List< Map<String,String> > getOrders( Integer BPartenerId,Date createFromDtm, Date createdToDtm) throws Exception{
		  
		SimpleDateFormat dateFormat = new SimpleDateFormat(ModelServiceConstants.DATE_FORMAT);
		
		String filterQuery =  ModelServiceConstants.COLUMN_BPartner_ID +"=" + BPartenerId +" and "+
							  
							  ModelServiceConstants.COLUMN_CREATED+ ">='"+ dateFormat.format(createFromDtm)+ "' and " +
							  ModelServiceConstants.COLUMN_CREATED+"<='"+dateFormat.format(createdToDtm)+"'";
		
		List<DataSet> list =  (List<DataSet>) invokeQueryWebService(filterQuery,"getOrder");
		
		return this.getQueryResult(list);
	}
}
