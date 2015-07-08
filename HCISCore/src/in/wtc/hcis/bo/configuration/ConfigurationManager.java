/**
 * 
 */
package in.wtc.hcis.bo.configuration;

import java.util.Map;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Sandeep
 *
 */
public interface ConfigurationManager {
	
	/**
	 * This method returns value of given system parameter 
	 * functionality in application 
	 * @return
	 */
	String getSystemConfiguration( String paramName ) throws HCISException;

    ListRange getSystemConfigurations(int start, int count, String orderBy ) throws HCISException;
  
    /**
     * 
     * @param paramValueMap <attrName,attrValue>
     * @throws HCISException
     */
    
void   updateSystemConfiguration(Map<String,String> paramValueMap) throws HCISException;
}
