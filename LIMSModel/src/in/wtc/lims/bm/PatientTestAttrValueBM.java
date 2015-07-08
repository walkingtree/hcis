/**
 * 
 */
package in.wtc.lims.bm;

/**
 * @author Bhavesh
 *
 */
public class PatientTestAttrValueBM {

	/**
	 * 
	 */


	private CodeAndDescription testAttribute;
	private Integer seqNbr;
	private String attrValue;
	private String remarks;
	
	public PatientTestAttrValueBM() {
		// TODO Auto-generated constructor stub
	} 
	
	public CodeAndDescription getTestAttribute() {
		return testAttribute;
	}
	public void setTestAttribute(CodeAndDescription testAttribute) {
		this.testAttribute = testAttribute;
	}
	public Integer getSeqNbr() {
		return seqNbr;
	}
	public void setSeqNbr(Integer seqNbr) {
		this.seqNbr = seqNbr;
	}
	public String getAttrValue() {
		return attrValue;
	}
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
