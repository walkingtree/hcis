/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.lims.bm.ListRange;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.exception.LimsException;

/**
 * @author Bhavesh
 *
 *This manger contains all the method related to load reference data to UI
 */
public interface LimsReferenceDataManager {

	ListRange getTestTypes(int start, int count, String orderBy)throws LimsException;
	
	ListRange getTestApplicableGengers(int start, int count, String orderBy)throws LimsException; 
	
	ListRange getLabs(int start, int count, String orderBy)throws LimsException; 
	
	ListRange getSpecimens(int start, int count, String orderBy)throws LimsException; 
	
	ListRange getAllTestAttribute(int start, int count, String orderBy)throws LimsException; 
	
	ListRange getLabType(int start, int count, String orderBy) throws LimsException;
	
	ListRange getHospitalName(int start, int count, String orderBy) throws LimsException;
	
	ListRange getTechniqueReagent(int start, int count, String orderBy) throws LimsException;
	
	/**
	 * 
	 * @param isTechnique -"Y or N"
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange getTechniqueReagentByType(String isTechnique,int start, int count, String orderBy);
	
	ListRange getMeasurementUnit(int start, int count, String orderBy) throws LimsException;
	
	ListRange getAttributeType( int start, int count, String orderBy ) throws LimsException;
	
	ListRange getLabTests( int start, int count, String orderBy ) throws LimsException ;
		
}
