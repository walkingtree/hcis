/**
 * 
 */
package in.wtc.hcis.bo.document;

import in.wtc.hcis.bm.base.CheckListBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Bhavesh
 *
 *Manger responsible for creating, modifying and assignment of checklist.
 *Check list can be of any type i.e. patient care, inventory chekclist 
 */
public interface CheckListManager {

	void addCheckList( CheckListBM checkListBM)throws HCISException;
	
	/**
	 * If forceModify flag is set to true then modify all the checklist detail field otherwise throws
	 * exception is any of the checklist filed is being used.
	 * 
	 * @param modifiedCheckList
	 * @param forceModify
	 * @return
	 */
	CheckListBM modifyCheckList( CheckListBM modifiedCheckList , boolean forceModify);
	
	/**
	 * If checkListId is passed then return CheckListBM and if checkListInstanceId
	 * CheckListBM with instance information.
	 * 
	 * @param checkListId
	 * @param checkListInstanceId
	 * @return
	 */
	CheckListBM getCheckListOfId( Integer checkListId, Long checkListInstanceId);
	
	/**
	 * Useful for UI group text field to act as auto-complete field
	 * @param partialGroupName
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange getCheckListGroups(String partialGroupName ,int start, int count, String orderBy) throws HCISException;
	
	/**
	 * 
	 * @param checkListName
	 * @param type
	 * @param groupName
	 * @param roleCd
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange getCheckList(String checkListName ,String type ,
						   String groupName ,String roleCd ,int start, int count, String orderBy) throws HCISException;
	
	/**
	 * 
	 * @param checkListIds
	 * @throws HCISException
	 */
	void removeCheckList ( Integer[] checkListIds) throws HCISException;
	
	/**
	 * 
	 * @param checkListId
	 * @return
	 */
	ListRange getCheckListDetails(Integer checkListId, int start, int count,String orderBy);
	
	/**
	 * 
	 * Saves/modifies CheckListInstance.
	 * 
	 * Create check list instance first, then create checkListInstanceDetail.
	 * Return checkList instance id for further use of caller of this method.
	 * (There will be multiple clients of checkList they will store this id to 
	 * there concern tables )
	 * 
	 * @param checkListInstance
	 * @return checkListInstaceId
	 */
	
	Long saveCheckListInstance( CheckListBM  checkListInstance) throws HCISException;
}
