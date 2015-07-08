
package pl._3e.adinterface;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.namespace.QName;

import org.codehaus.xfire.XFireRuntimeException;
import org.codehaus.xfire.aegis.AegisBindingProvider;
import org.codehaus.xfire.annotations.AnnotationServiceFactory;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.jaxb2.JaxbTypeRegistry;
import org.codehaus.xfire.service.Endpoint;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.soap.AbstractSoapBinding;
import org.codehaus.xfire.transport.TransportManager;

public class ModelADServiceClient {

    private static XFireProxyFactory proxyFactory = new XFireProxyFactory();
    private HashMap endpoints = new HashMap();
    private Service service0;

	private ResourceBundle Config = ResourceBundle.getBundle("pl._3e.adinterface.WebServices", Locale.getDefault());
    public ModelADServiceClient() {
        create0();
        Endpoint ModelADServicePortTypeLocalEndpointEP = service0 .addEndpoint(new QName("http://3e.pl/ADInterface", "ModelADServicePortTypeLocalEndpoint"), new QName("http://3e.pl/ADInterface", "ModelADServicePortTypeLocalBinding"), "xfire.local://ModelADService");
        endpoints.put(new QName("http://3e.pl/ADInterface", "ModelADServicePortTypeLocalEndpoint"), ModelADServicePortTypeLocalEndpointEP);
        Endpoint ModelADServiceHttpPortEP = service0 .addEndpoint(new QName("http://3e.pl/ADInterface", "ModelADServiceHttpPort"), new QName("http://3e.pl/ADInterface", "ModelADServiceHttpBinding"), Config.getString("SERVER_URL"));
        endpoints.put(new QName("http://3e.pl/ADInterface", "ModelADServiceHttpPort"), ModelADServiceHttpPortEP);
    }
    
    public Object getEndpoint(Endpoint endpoint) {
        try {
            return proxyFactory.create((endpoint).getBinding(), (endpoint).getUrl());
        } catch (MalformedURLException e) {
            throw new XFireRuntimeException("Invalid URL", e);
        }
    }

    public Object getEndpoint(QName name) {
        Endpoint endpoint = ((Endpoint) endpoints.get((name)));
        if ((endpoint) == null) {
            throw new IllegalStateException("No such endpoint!");
        }
        return getEndpoint((endpoint));
    }

    public Collection getEndpoints() {
        return endpoints.values();
    }

    private void create0() {
        TransportManager tm = (org.codehaus.xfire.XFireFactory.newInstance().getXFire().getTransportManager());
        HashMap props = new HashMap();
        props.put("annotations.allow.interface", true);
        AnnotationServiceFactory asf = new AnnotationServiceFactory(new Jsr181WebAnnotations(), tm, new AegisBindingProvider(new JaxbTypeRegistry()));
        asf.setBindingCreationEnabled(false);
        service0 = asf.create((pl._3e.adinterface.ModelADServicePortType.class), props);
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://3e.pl/ADInterface", "ModelADServiceHttpBinding"), "http://schemas.xmlsoap.org/soap/http");
        }
        {
            AbstractSoapBinding soapBinding = asf.createSoap11Binding(service0, new QName("http://3e.pl/ADInterface", "ModelADServicePortTypeLocalBinding"), "urn:xfire:transport:local");
        }
    }

    public ModelADServicePortType getModelADServicePortTypeLocalEndpoint() {
        return ((ModelADServicePortType)(this).getEndpoint(new QName("http://3e.pl/ADInterface", "ModelADServicePortTypeLocalEndpoint")));
    }

    public ModelADServicePortType getModelADServicePortTypeLocalEndpoint(String url) {
        ModelADServicePortType var = getModelADServicePortTypeLocalEndpoint();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }

    public ModelADServicePortType getModelADServiceHttpPort() {
        return ((ModelADServicePortType)(this).getEndpoint(new QName("http://3e.pl/ADInterface", "ModelADServiceHttpPort")));
    }

    public ModelADServicePortType getModelADServiceHttpPort(String url) {
        ModelADServicePortType var = getModelADServiceHttpPort();
        org.codehaus.xfire.client.Client.getInstance(var).setUrl(url);
        return var;
    }
    
    public Integer invokeEagleWebService(Map<String, String> valuesMap,
			String svcType) throws Exception {

		ADLoginRequest aLoginRequest = getADLoginRequest();

		ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();
		modelCRUDRequest.setADLoginRequest(aLoginRequest);

		ModelCRUD modelCRUD = getModelCRUD(valuesMap, svcType);
		modelCRUDRequest.setModelCRUD(modelCRUD);

		ModelADServicePortType modelADServicePortType = getModelADServiceHttpPort();
		
		StandardResponse standardResponse = modelADServicePortType.createData(modelCRUDRequest);
		System.out.println(standardResponse.getRecordID());
		
		if( standardResponse != null ){

			if( standardResponse.isError != null &&  standardResponse.isError){
				new Exception(standardResponse.error);
			}
		}
		
		return standardResponse.getRecordID();
	}
    
    public void invokeUpdateWebService( Integer recordId,Map<String, String> valuesMap,
			String svcType ){
    	
    	try {
			ADLoginRequest aLoginRequest = getADLoginRequest();

			ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();
			modelCRUDRequest.setADLoginRequest(aLoginRequest);

			ModelCRUD modelCRUD = getModelCRUD(valuesMap, svcType);
			
			modelCRUD.setRecordID( recordId );
			modelCRUDRequest.setModelCRUD(modelCRUD);

			ModelADServicePortType modelADServicePortType = getModelADServiceHttpPort();
			
			
			StandardResponse standardResponse = modelADServicePortType.updateData(modelCRUDRequest);
	
			if( standardResponse != null ){

				if( standardResponse.isError != null &&  standardResponse.isError){
					new Exception(standardResponse.error);
				}
			}
		} catch ( Exception e) {
			e.printStackTrace();
			
		}
    }
        
    public List<DataSet>  invokeQueryWebService( String filterValue,String svcType ) throws Exception{
    	
    	try {
			ADLoginRequest aLoginRequest = getADLoginRequest();

			ModelCRUDRequest modelCRUDRequest = new ModelCRUDRequest();
			modelCRUDRequest.setADLoginRequest(aLoginRequest);

			ModelCRUD modelCRUD = new ModelCRUD();
			
			modelCRUD.setDataRow( new DataRow() );
			modelCRUD.setServiceType(svcType);
			modelCRUD.setFilter(filterValue);
			
			modelCRUDRequest.setModelCRUD(modelCRUD);

			ModelADServicePortType modelADServicePortType = getModelADServiceHttpPort();
			
			
			WindowTabData windowTabData = modelADServicePortType.queryData(modelCRUDRequest);
			
			if( windowTabData != null ){
				
				
				if( !windowTabData.success){
					new Exception(windowTabData.error);
				}
				
				if( windowTabData.dataSet != null){
					List<DataSet> dataSetList = windowTabData.dataSet;
					
					return dataSetList;
			}
				
			}
		} catch ( Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		
    }
    
 public List<DataSet> invokeListWebService( String filterValue,String svcType ) throws Exception{
    	
    	try {
			ADLoginRequest aLoginRequest = getADLoginRequest();
		
			ModelGetList modelGetList = new ModelGetList();
			modelGetList.setServiceType(svcType);
			modelGetList.setFilter(filterValue);
			
			ModelGetListRequest modelListRequest = new ModelGetListRequest();
			
			modelListRequest.setADLoginRequest(aLoginRequest);
			modelListRequest.setModelGetList(modelGetList);
			
			ModelADServicePortType modelADServicePortType = getModelADServiceHttpPort();
			WindowTabData windowTabData = modelADServicePortType.getList(modelListRequest);
			
			if( windowTabData != null ){
				
				
				if( !windowTabData.success){
					new Exception(windowTabData.error);
				}
				
				if( windowTabData.dataSet != null){
					List<DataSet> dataSetList = windowTabData.dataSet;
					
					return dataSetList;
			}
			}
			return null;
		} catch ( Exception e) {

			e.printStackTrace();
			throw e;
		}
		
    }
 
    private ModelCRUD getModelCRUD(Map<String, String> valuesMap, String svcType){
    	List<DataField> dataFieldsList = new ArrayList<DataField>();
    	Iterator<String> iter = valuesMap.keySet().iterator();
    	while(iter.hasNext()){
    		String key = iter.next().toString();
    		DataField dataField = new DataField();
    		dataField.setColumn(key);
    		dataField.setVal(valuesMap.get(key));
    		dataFieldsList.add(dataField);
    	}
    	
    	DataRow dataRow = new DataRow();
    	dataRow.field = dataFieldsList;
    	
    	ModelCRUD modelCrud = new ModelCRUD();
    	modelCrud.setDataRow(dataRow);
    	modelCrud.setServiceType(svcType);
    	
    	return modelCrud;
    }
    
    protected ADLoginRequest getADLoginRequest(){
       	ADLoginRequest adLoginRequest = new ADLoginRequest();
    	adLoginRequest.setUser(Config.getString("USER"));
    	adLoginRequest.setPass(Config.getString("PASSWORD"));
    	adLoginRequest.setClientID(Integer.parseInt(Config.getString("CLIENT_ID")));
    	adLoginRequest.setRoleID(Integer.parseInt(Config.getString("ROLE_ID")));
    	adLoginRequest.setOrgID(Integer.parseInt(Config.getString("ORGANISATION_ID")));
//    	adLoginRequest.setLang(Config.getString("LANGUAGE");
    	adLoginRequest.setWarehouseID(Integer.parseInt(Config.getString("WAREHOUSE_ID")));
//    	adLoginRequest.setStage(Integer.parseInt(Config.getString("STAGE")));
    	return adLoginRequest;
    }
}
