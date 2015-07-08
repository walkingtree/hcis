/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Alok Ranjan
 * 
 */
@SuppressWarnings("serial")
public class OrderGroupTemplateAssociationBM implements Serializable {

	private String orderGroupName;

	private Integer sequenceNbr;
	private String orderTemplateName;
	private String orderTemplateDesc;
	private Date effectiveFromDt;
	private Date effectiveToDt;

	public String getOrderGroupName() {
		return orderGroupName;
	}

	public void setOrderGroupName(String orderGroupName) {
		this.orderGroupName = orderGroupName;
	}

	public Integer getSequenceNbr() {
		return sequenceNbr;
	}

	public void setSequenceNbr(Integer sequenceNbr) {
		this.sequenceNbr = sequenceNbr;
	}

	public Date getEffectiveFromDt() {
		return effectiveFromDt;
	}

	public void setEffectiveFromDt(Date effectiveFromDt) {
		this.effectiveFromDt = effectiveFromDt;
	}

	public Date getEffectiveToDt() {
		return effectiveToDt;
	}

	public void setEffectiveToDt(Date effectiveToDt) {
		this.effectiveToDt = effectiveToDt;
	}

	public String getOrderTemplateName() {
		return orderTemplateName;
	}

	public void setOrderTemplateName(String orderTemplateName) {
		this.orderTemplateName = orderTemplateName;
	}

	public String getOrderTemplateDesc() {
		return orderTemplateDesc;
	}

	public void setOrderTemplateDesc(String orderTemplateDesc) {
		this.orderTemplateDesc = orderTemplateDesc;
	}
}