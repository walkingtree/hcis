/**
 * 
 */
package in.wtc.hcis.bm.base;

import java.io.Serializable;

/**
 * @author Rohit
 * 
 */
public class CodeAndDescription implements Serializable {
	final static long serialVersionUID = 200904130508L;

	private String code;
	private String description;
	private String isDefault;

	public CodeAndDescription() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param code
	 * @param description
	 */
	public CodeAndDescription(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}
