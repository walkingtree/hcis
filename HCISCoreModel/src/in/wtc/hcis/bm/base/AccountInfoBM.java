package in.wtc.hcis.bm.base;


/**
 * @author Bhavesh
 *
 */

@SuppressWarnings("serial")
public class AccountInfoBM {

	Integer businessPartnerId ;
	CodeAndDescription entityCategory;
	CodeAndDescription entityRating;
	ContactDetailsBM contactDetailsBM; //This field must not be null
	
	public AccountInfoBM() {
		super();
	}

	public Integer getBusinessPartnerId() {
		return businessPartnerId;
	}
	public void setBusinessPartnerId(Integer businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}
	public CodeAndDescription getEntityCategory() {
		return entityCategory;
	}
	public void setEntityCategory(CodeAndDescription entityCategory) {
		this.entityCategory = entityCategory;
	}
	public CodeAndDescription getEntityRating() {
		return entityRating;
	}

	public void setEntityRating(CodeAndDescription entityRating) {
		this.entityRating = entityRating;
	}

	public ContactDetailsBM getContactDetailsBM() {
		return contactDetailsBM;
	}

	public void setContactDetailsBM(ContactDetailsBM contactDetailsBM) {
		this.contactDetailsBM = contactDetailsBM;
	}

	}
