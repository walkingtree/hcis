/**
 * 
 */
package in.wtc.hcis.bm.ip;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alok Ranjan
 *
 */
@SuppressWarnings("serial")
public class BedEnvelopeBM implements Serializable {
	private String envelopeName;
	private String description;
	private String facilityTypeInd;
	private List<BedEnvelopePoolAsgmBM>bedEnvelopePoolAsgmList;
	
	public String getEnvelopeName() {
		return envelopeName;
	}
	public void setEnvelopeName(String envelopeName) {
		this.envelopeName = envelopeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFacilityTypeInd() {
		return facilityTypeInd;
	}
	public void setFacilityTypeInd(String facilityTypeInd) {
		this.facilityTypeInd = facilityTypeInd;
	}
	public List<BedEnvelopePoolAsgmBM> getBedEnvelopePoolAsgmList() {
		return bedEnvelopePoolAsgmList;
	}
	public void setBedEnvelopePoolAsgmList(
			List<BedEnvelopePoolAsgmBM> bedEnvelopePoolAsgmList) {
		this.bedEnvelopePoolAsgmList = bedEnvelopePoolAsgmList;
	}
}
