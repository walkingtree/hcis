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
public class BedEnvelopePoolAsgmBM implements Serializable {
	private String envelopeName;
	private String poolName;
	private String poolDescription;
	private Date effectiveFromDt;
	private Date effectiveToDt;
	
	public String getEnvelopeName() {
		return envelopeName;
	}
	public void setEnvelopeName(String envelopeName) {
		this.envelopeName = envelopeName;
	}
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
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
	public String getPoolDescription() {
		return poolDescription;
	}
	public void setPoolDescription(String poolDescription) {
		this.poolDescription = poolDescription;
	}
}
