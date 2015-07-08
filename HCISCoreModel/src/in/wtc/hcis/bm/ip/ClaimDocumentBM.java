/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class ClaimDocumentBM implements Serializable {
	
	private String requestSequenceNbr;
	private String documentName;
	private String documentPath;
	private Date   createDtm;
	private String createdBy;
	
	
	public String getRequestSequenceNbr() {
		return requestSequenceNbr;
	}
	public void setRequestSequenceNbr(String requestSequenceNbr) {
		this.requestSequenceNbr = requestSequenceNbr;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public Date getCreateDtm() {
		return createDtm;
	}
	public void setCreateDtm(Date createDtm) {
		this.createDtm = createDtm;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	} 

}
