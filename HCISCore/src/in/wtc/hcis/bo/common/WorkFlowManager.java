/**
 * 
 */
package in.wtc.hcis.bo.common;

import in.wtc.hcis.bm.base.WorkFlowProcessBM;

import java.util.List;

/**
 * @author Bhavesh
 * 
 * Workflow window displays the overall workflow of a process or subsytem. This
 * component will be developed as a part of Hamsa and should be pluggable to any
 * system with minor or no changes.
 *  
 *  This WorkFlowManager work with the data model WorkFlowProcessBM which has processId, processName and 
 *  linkedProcesses as its property.
 *  
 *  linkedProcesses tells what are process linked with current process; it shows the 
 *  valid transition 
 */
public interface WorkFlowManager {

	/**
	 * Give the list of Work Flow Processes with the liked process.
	 * 
	 * In order to get the linking relationship get the value from 'StatusTransition' table. For given processName; 
	 * processName will map to 'CONTEXT' of 'StatusTransision' table.
	 * 
	 * Create one map of processId(from/to - Status)  and  WorkFlowProcessBM  say WorkFlowProcessMap . To refer
	 * to the same object when needed.
	 * 
	 * After getting all the records start iterating through the elements.
	 * 
	 * Step 1: Get the fromStaus from StatusTransitionList and have a lookup on
	 * 			WorkFlowProcessMap if it is there then use it otherwise create new  WorkFlowProcessBM,
	 * 			Set the value of processId and processName.
	 * 
	 * Step 2: Get the toStatus from StatusTransitionList if it is not null then 
	 * 				have a lookup on WorkFlowProcessMap if it is there then use it otherwise create new  WorkFlowProcessBM.
	 * 			Add this object to the linkedProcessSet of current StatusTransision object
	 * 
	 * Step 3: Put both the objects to WorkFlowProcessMap;
	 * 
	 * Step 4: Repeat step 1 and 2 for all the StatusTransition object
	 * 
	 * @param workFlowName --> Name of the workFlow
	 * @return
	 */
	List<WorkFlowProcessBM> getWorkFlow( String workFlowName);
}
