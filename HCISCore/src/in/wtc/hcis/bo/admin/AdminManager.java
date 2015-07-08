/**
 * 
 */
package in.wtc.hcis.bo.admin;

import java.util.List;

import in.wtc.hcis.bm.ip.DiseaseBM;

/**
 * @author Bhavesh
 *
 */
public interface AdminManager {

	/**
	 * 
	 * @param diseaseBM
	 */
	void addDisease( DiseaseBM diseaseBM );
	
	/**
	 * 
	 * @param diseaseName
	 * @param diseaseDesc
	 * @param diseaseGroup
	 * @param serviceCode
	 * @return
	 */
	List<DiseaseBM> getDisease( String diseaseName, String diseaseDesc,
			   					String diseaseGroup,String serviceCode);
			   
	/**
	 * 
	 * @param diseaseBM
	 */
	void modifyDisease( DiseaseBM diseaseBM );
	
	/**
	 * 
	 * @param diseaseNameList
	 * @return
	 */
	boolean removeDiseases( List<String> diseaseNameList );
	
}
