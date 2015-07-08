/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 * 
 */
@SuppressWarnings("serial")
public class DoctorOrderGroupBM implements Serializable {
	
	private String orderGroupName;
	private String orderGroupDesc;
	private Integer groupForDoctorId;
	private String  groupForDoctorName;
	private String  createdBy;
	private Date    creationDate;
	
	private List<OrderGroupTemplateAssociationBM> orderGroupTemplateAssociationList;

	private Integer seqNbr; // to be used for UI grid as sequence number
	
	public String getOrderGroupName() {
		return orderGroupName;
	}

	public void setOrderGroupName(String orderGroupName) {
		this.orderGroupName = orderGroupName;
	}

	public Integer getGroupForDoctorId() {
		return groupForDoctorId;
	}

	public void setGroupForDoctorId(Integer groupForDoctorId) {
		this.groupForDoctorId = groupForDoctorId;
	}

	public List<OrderGroupTemplateAssociationBM> getOrderGroupTemplateAssociationList() {
		return orderGroupTemplateAssociationList;
	}

	public void setOrderGroupTemplateAssociationList(
			List<OrderGroupTemplateAssociationBM> orderGroupTemplateAssociationList) {
		this.orderGroupTemplateAssociationList = orderGroupTemplateAssociationList;
	}

	public String getOrderGroupDesc() {
		return orderGroupDesc;
	}

	public void setOrderGroupDesc(String orderGroupDesc) {
		this.orderGroupDesc = orderGroupDesc;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getGroupForDoctorName() {
		return groupForDoctorName;
	}

	public void setGroupForDoctorName(String groupForDoctorName) {
		this.groupForDoctorName = groupForDoctorName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getSeqNbr() {
		return seqNbr;
	}

	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}

}
