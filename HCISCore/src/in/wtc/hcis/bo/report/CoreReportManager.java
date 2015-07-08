/**
 * 
 */
package in.wtc.hcis.bo.report;

import java.util.Map;

import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Bhavesh
 *
 */
public interface CoreReportManager {

	ListRange getReportWidgets( String reportCode ,int start, int count, String orderBy ) throws HCISException;
	
	String generateReport( String reportCode, Map<String,Object> paramNameValueMap );
}
