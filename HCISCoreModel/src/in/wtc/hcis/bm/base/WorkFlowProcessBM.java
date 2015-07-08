/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Bhavesh
 *
 */
public class WorkFlowProcessBM implements Serializable {

	private String processId; //WindowId in case of Hamsa
	private String processName; //Window title
	private Set<WorkFlowProcessBM> linkedProcessSet;
	
	
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Set<WorkFlowProcessBM> getLinkedProcessSet() {
		return linkedProcessSet;
	}
	public void setLinkedProcessSet(Set<WorkFlowProcessBM> linkedProcessSet) {
		this.linkedProcessSet = linkedProcessSet;
	}
	
	
}
