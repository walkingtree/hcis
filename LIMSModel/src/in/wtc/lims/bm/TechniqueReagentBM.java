/**
 * 
 */
package in.wtc.lims.bm;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Bhavesh
 *
 */
@SuppressWarnings("serial")
public class TechniqueReagentBM implements Serializable {

	private Integer techniequeReagentId;
	private String name;
	private String description;
	private String isTechnique;
	
	private String createdBy;
	private Date createdDate;
	
	
	public TechniqueReagentBM() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsTechnique() {
		return isTechnique;
	}
	public void setIsTechnique(String isTechnique) {
		this.isTechnique = isTechnique;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getTechniequeReagentId() {
		return techniequeReagentId;
	}

	public void setTechniequeReagentId(Integer techniequeReagentId) {
		this.techniequeReagentId = techniequeReagentId;
	}
	
}
