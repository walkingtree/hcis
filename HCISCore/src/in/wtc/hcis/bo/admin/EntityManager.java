/**
 * 
 */
package in.wtc.hcis.bo.admin;

import in.wtc.hcis.bm.base.EntityBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;


/**
 * This manager is used for managing all entity related configuration in a generic way.
 * Various entities will be differentiated base on the type, i.e. Nurse,Wordboy,Lab technician etc.
 *      
 * @author Bhavesh
 *
 */
public interface EntityManager {

	/**
	 * Setups new entity in the system.
	 * 
	 * Contact detail needs be saved first, then save entity and other detail. 
	 *  
	 * @param entityBM
	 * @return EntityBM with entity id 
	 * @throws HCISException
	 */
	void addEntity(EntityBM entityBM) throws HCISException; 
	
	/**
	 * Modifies the entity record.
	 * 
	 * Modification of any field is allowed.
	 * Stamps modified time and user id, and final returns modified EntityBM.
	 * 
	 * @param modifiedEntityBM
	 * @return
	 * @throws HCISException
	 */
	EntityBM modifyEntity( EntityBM modifiedEntityBM ) throws HCISException;
	
	
	/**
	 * Returns listRange of EntityBM. All parameters are optional.
	 * Size of Return object will be limited based on start and count.
	 * Returns ListRange object with no data and with size zero.

	 * @param entityId
	 * @param name
	 * @param genderCode
	 * @param typeCode
	 * @param stateCode
	 * @param contryCode
	 * @param city
	 * @param address
	 * @param isPermanent
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 */
	ListRange  getEntities( Integer entityId, String name,String genderCode,
							String typeCode, String stateCode,String contryCode,
							String city,String address,	String isPermanent,			
							int start, int count, String orderBy);
	
	EntityBM getEntityDetail( Integer entityId) throws HCISException;
}
