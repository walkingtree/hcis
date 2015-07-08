/**
 * 
 */
package in.wtc.hcis.bo.configuration;

import in.wtc.hcis.bm.base.SystemParamBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ConfigurationConstants;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.Validate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.wtc.hcis.da.SystemParameter;
import com.wtc.hcis.da.SystemParameterDAO;

/**
 * @author Sandeep
 *
 */
public class ConfigurationManagerImpl implements ConfigurationManager ,ApplicationContextAware{

	private SystemParameterDAO systemParameterDAO;
	private ApplicationContext applicationContext;
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.configuration.ConfigurationManager#getSystemConfiguration(java.lang.String)
	 */

	public String getSystemConfiguration( String paramName ) throws HCISException{
		try {
			Validate.notEmpty(paramName , "param name should not be empty");

			String paramValue = null;
			SystemParameter sysParam = systemParameterDAO.findById( paramName );
			if( sysParam != null ){
				paramValue =	sysParam.getAttrValue();
			}
			return paramValue;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.READ_SYSTEM_CONFIGURATION_FAILED);
			throw new HCISException(fault, e);
		}
	}
	
	public ListRange getSystemConfigurations(int start, int count, String orderBy ) throws HCISException{
		
		try {
			List<SystemParamBM> systemParamBMList = new ArrayList<SystemParamBM>(0);
			
			List<SystemParameter> systemParameterList = systemParameterDAO.findAll();
			
			if( systemParameterList != null &&  !systemParameterList.isEmpty() ){
				
				for( SystemParameter systemParameter : systemParameterList ){
					
					SystemParamBM systemParamBM = new SystemParamBM();
					
					systemParamBM.setAttrName( systemParameter.getAttrName() );
					systemParamBM.setAttrLabel( systemParameter.getAttrLabel() );
					systemParamBM.setAttrDesc( systemParameter.getAttrDesc() );
					systemParamBM.setAttrValue( systemParameter.getAttrValue()  );
					systemParamBM.setDataType(  systemParameter.getDataType());
					systemParamBM.setAttrWidth( systemParameter.getAttrWidth() );
					
					if( systemParameter.getDataType().equals(ConfigurationConstants.ATTR_TYPE_MVL)){
						
						String dataProvider = systemParameter.getDataProvider();
						
						if( dataProvider != null && !dataProvider.isEmpty()){
//	Use reflexion to invoke the method 
//	Here we have assumption that 'provider' contains QualifiedName (without argument list as it always should be
//	int,int,String) of the	provider method and bean name in the Spring configuration file is same as the class name.
							
							int lastIndex = dataProvider.lastIndexOf(".");
							String className = dataProvider.substring(0, lastIndex);
							String methodName = dataProvider.substring( lastIndex +1 );
							
						
								 Class cl=Class.forName( className );
								 Class[] param = new Class[3];
								 param[0] = int.class;
								 param[1] = int.class;
								 param[2] = String.class;
							
								  Method method = cl.getMethod( methodName , param);
								
								  String[] beanNames  = applicationContext.getBeanNamesForType( cl );
								  
								  
								  if( beanNames != null  && beanNames[0] != null ){
									  
									  Object obj = applicationContext.getBean(beanNames[0]);
									  ListRange listRange = (ListRange) method.invoke(obj ,0,999,"");

									  systemParamBM.setMVLvalueList(new ArrayList(Arrays.asList( listRange.getData() )));
								  }else{
									  throw new Exception("Spring Bean with for class  '" +className  +"' is not defined.");
								  }
						
						}
					}
					
					systemParamBMList.add( systemParamBM );
				}
				
				}
			
			ListRange listRange = new ListRange();
			
			List<SystemParamBM> pageSizeData = new ArrayList<SystemParamBM>();
			int index = 0;
			if (systemParamBMList != null && systemParamBMList.size() > 0) {
			while( (start+index < start + count) && (systemParamBMList.size() > start+index) ){
				
				SystemParamBM systemParamBM = systemParamBMList.get(start+index);
				pageSizeData.add( systemParamBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(systemParamBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (Exception e) {
			
			Fault fault = new Fault(ApplicationErrors.READ_SYSTEM_CONFIGURATION_FAILED);
			throw new HCISException(fault,e);
		}
		
	}
	/**
	 * This method updates the value of existing system parameters.And if new value of system parameter is same as 
	 * old value then it does not modify the existing value;
	 */
	public void  updateSystemConfiguration(Map<String,String> paramValueMap){
		
		try {
			
			Validate.notEmpty(paramValueMap," Value-map must not be empty! ");
			
			Set<String> attrNameList = paramValueMap.keySet();

			if( attrNameList != null && attrNameList.size() > 0){
				
				for( String attrName : attrNameList ){
					
					SystemParameter systemParameter = this.getSystemParameter( attrName );
					
					if(  !systemParameter.getAttrValue() .equals( paramValueMap.get( attrName ) )){
						
						systemParameter.setAttrValue( paramValueMap.get( attrName ));
						
						systemParameterDAO.attachDirty( systemParameter );
					}
				}
				
			}
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.UPDATE_SYSTEM_CONFIGURATION_FAILED);
			
			throw new HCISException( fault,e);
		}
		
	}
	
	private SystemParameter getSystemParameter( String paramName ){

		try{
					SystemParameter sysParam = systemParameterDAO.findById( paramName );
					if ( paramName == null ) {
						throw new Exception( " System Parameter ' " + paramName +"' does not exists ");
					}
					
					return sysParam;
				} catch (Exception e) {
					Fault fault = new Fault( ApplicationErrors.READ_SYSTEM_CONFIGURATION_FAILED);
					
					throw new HCISException( fault.getFaultMessage() + e.getMessage(),
											 fault.getFaultCode(),
											 fault.getFaultType() );
				}
	}
	public void setSystemParameterDAO(SystemParameterDAO systemParameterDAO) {
		this.systemParameterDAO = systemParameterDAO;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
