/**
 * 
 */
package in.wtc.lims.configuration;

import java.util.List;

import in.wtc.lims.bm.LabTestAttributeBM;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.TechniqueReagentBM;
import in.wtc.lims.exception.LimsException;

/**
 * @author Bhavesh
 * 
 * 
 *        This manager will be used to manage all basic configuration of any
 *         laboratory
 */
public interface LabConfigManager {

	/**
	 * Laboratory test can be  performed using either some technique method or 
	 * reagents.In terms of data, there is no significant deference between both of them 
	 * so they are being stored in same table with a differentiater flag 'isTechnique'.
	 * @param techniequeReagentBM
	 */
	void addTechniqueReagent(TechniqueReagentBM techniequeReagentBM)
			throws LimsException;

	
	TechniqueReagentBM modifyTechniqueReagent(
			TechniqueReagentBM modifiedTechniequeReagentBM)
			throws LimsException;

	ListRange getTechniquReagents(Integer techReagentId, String name,
			String isTechnique, String testName, int start, int limit,
			String orderBy) throws LimsException;

	boolean removeTechniqueReagent(Integer[] techniqueReagentIds)
			throws LimsException;

	LabTestAttributeBM getTestAttribute(String attributeCode)
			throws LimsException;

	void addTestAttribute(LabTestAttributeBM attributeBM) throws LimsException;

	ListRange getTestAttribute(String labAttributeCode,
			String labAttributeName, String type, String testCode, int start,
			int count, String orderBy) throws LimsException;

	LabTestAttributeBM modifyTestAttribute(
			LabTestAttributeBM modifiedAttributeBM) throws LimsException;
	
	/**
	 * This method remove test attribute(s) and corresponding widget from the system, 
	 * only if it is not associated with any labTest; throws exception otherwise.
	 *  
	 * @param attributeCodeList
	 */
	void removeTestAttribute( List<String> attributeCodeList) throws LimsException;
}
