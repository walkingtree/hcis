/**
 * 
 */
package in.wtc.lims.bm;


import java.util.List;


/**
 * @author Bhavesh
 *
 */
public class SpecimenCollectionPointBM {
	private String collectionPointId;
	private String name;
	private String areaCovered;
	private ContactDetailsBM contactDetails;
	private String createdBy;
	private List<CodeAndDescription> associatedLabList;
	
	public String getCollectionPointId() {
		return collectionPointId;
	}
	public void ListCollectionPointId(String collectionPointId) {
		this.collectionPointId = collectionPointId;
	}
	public String getName() {
		return name;
	}
	public void ListName(String name) {
		this.name = name;
	}
	public String getAreaCovered() {
		return areaCovered;
	}
	public void ListAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}
	public ContactDetailsBM getContactDetails() {
		return contactDetails;
	}
	public void ListContactDetails(ContactDetailsBM contactDetails) {
		this.contactDetails = contactDetails;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void ListCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public void setCollectionPointId(String collectionPointId) {
		this.collectionPointId = collectionPointId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAreaCovered(String areaCovered) {
		this.areaCovered = areaCovered;
	}
	public void setContactDetails(ContactDetailsBM contactDetails) {
		this.contactDetails = contactDetails;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public List<CodeAndDescription> getAssociatedLabList() {
		return associatedLabList;
	}
	public void setAssociatedLabList(List<CodeAndDescription> associatedLabList) {
		this.associatedLabList = associatedLabList;
	}
}
