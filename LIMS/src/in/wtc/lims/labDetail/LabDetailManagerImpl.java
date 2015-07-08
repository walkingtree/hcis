/**
 * 
 */
package in.wtc.lims.labDetail;

import in.wtc.lims.bm.ContactDetailsBM;
import in.wtc.lims.bm.LabDetailBM;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.extn.LabDetailsDAOExtn;


/**
 * @author AshaS
 *
 */
public class LabDetailManagerImpl implements LabDetailManager {

	/* (non-Javadoc)
	 * @see in.wtc.lims.labDetail.LabDetailManager#addLabDetail(in.wtc.lims.bm.LabDetailBM)
	 */
//	LabDetailsDAO labDetailsDAO;
	ContactDetailsDAO contactDetailsDAO;
	private LimsDataModelConvertor limsDataModelConvertor;
	private LimsDataModelManager dataModelManager;
	private LabDetailsDAOExtn labDetailsDAO;
	@Override
	public void addLabDetail(LabDetailBM labDetailBM) throws LimsException {
		try{
			
			//First check whether given lab id already exists
			{
				LabDetails labDetails = labDetailsDAO.findById(labDetailBM
						.getLabId());

				if (labDetails != null) {

					throw new Exception("Lab with id '"
							+ labDetailBM.getLabId() + "', already exists.");
				}
			}
			ContactDetails contactDetails = limsDataModelConvertor.convertContactDetailsBM2DM(labDetailBM.getContactDetail(), null);
			
			contactDetailsDAO.save(contactDetails);
			
			
			LabDetails labDetails = limsDataModelConvertor.convertLabDetailBM2DM(labDetailBM, null);
			labDetails.setCreatedBy(labDetailBM.getCreatedBy());
			labDetails.setCreatedDtm( new Date());
			
			labDetails.setContactDetails(contactDetails);
			
			labDetailsDAO.save(labDetails);
			
			
		}catch(Exception ex) {
			Fault fault = new Fault(LimsErrors.LAB_ADD_FAILED);
			LimsException limsException = new LimsException(fault,ex);
			throw limsException;
		}
	}
		// TODO Auto-generated method stub
	

	/* (non-Javadoc)
	 * @see in.wtc.lims.labDetail.LabDetailManager#getLabDetail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public ListRange getLabDetail(String labId,  String hospitalName, String labType,String labName,
			String businessName, String branchName, String labOperatorID,String directionFromKnownPlace,
			int start, int limit, String orderBy) throws LimsException {
		// TODO Auto-generated method stub
		
		try{
			
		
		List<LabDetails> labDetailList = labDetailsDAO.getLabDetails(labId, hospitalName,labType,labName,
																		businessName, branchName, labOperatorID, directionFromKnownPlace);
		
//		LabDetailBM labDetailBM = limsDataModelConvertor.convertLabDetailDM2BM(LabDetails);
		
		ListRange listRange = new ListRange();
		
		if( labDetailList != null && !labDetailList.isEmpty() ){
			
			int index = 0;
			List<LabDetailBM> pageSizeData = new ArrayList<LabDetailBM>(0);
			
			while( (start+index < start + limit) && (labDetailList.size() > start+index) ){
				
				LabDetailBM labDetailBM = new LabDetailBM();
				ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
				
				LabDetails labDetails = labDetailList.get(start+index);
				
				labDetailBM = limsDataModelConvertor.convertLabDetailDM2BM(labDetails);
				
				contactDetailsBM = limsDataModelConvertor.convertContactDetailsDM2BM(labDetails.getContactDetails());

				labDetailBM.setContactDetail(contactDetailsBM);
				
				pageSizeData.add( labDetailBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(labDetailList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		}catch(Exception ex) {
			Fault fault = new Fault(LimsErrors.LAB_SEARCH_FAILED);
			LimsException hcisException = new LimsException(fault, ex);
			throw hcisException;
		}
		
//		return null;
	}

	/* (non-Javadoc)
	 * @see in.wtc.lims.labDetail.LabDetailManager#modifyLabDetail(in.wtc.lims.bm.LabDetailBM)
	 */
	@Override
	public LabDetailBM modifyLabDetail(LabDetailBM modifiedLabDetailBM)
			throws LimsException {
		LabDetails existingLabDetails = dataModelManager.getLabDetail(modifiedLabDetailBM.getLabId());

		ContactDetails existingContactDetails = dataModelManager.getContactDetails(existingLabDetails.getContactDetails().getContactCode());
		ContactDetails contactDetails =limsDataModelConvertor.convertContactDetailsBM2DM(modifiedLabDetailBM.getContactDetail(),existingContactDetails);
		contactDetailsDAO.attachDirty(contactDetails);
		
		LabDetails labDetails = limsDataModelConvertor.convertLabDetailBM2DM(modifiedLabDetailBM, existingLabDetails);
		
		labDetailsDAO.attachDirty(labDetails);
		
		
		// TODO Auto-generated method stub
		return modifiedLabDetailBM;
	}
	
	// This Method deleting labDetail from laboratory list 
	
	public boolean removeLabDetail(String[] labIds)
	throws LimsException {

			try {
				if(labIds != null && labIds.length > 0){
					
				for( String labId :labIds)	{
				
				LabDetails labDetails = dataModelManager.getLabDetail(labId);
			
				ContactDetails contactDetails = dataModelManager.getContactDetails(labDetails.getContactDetails().getContactCode());
				
					if (labDetails != null && labDetails.getLabId() !=null) {
						
						labDetailsDAO.delete(labDetails);
						
						if(labDetails.getContactDetails().getContactCode() != null && contactDetails != null){
							
							contactDetailsDAO.delete(contactDetails);
						}
						
					}
				}
				return true;
							
			} else {
				return false;
				} 
			} catch (Exception e) {
				Fault fault = new Fault(LimsErrors.LAB_DETAILS_DELETE_FAILED);
				throw new LimsException(fault,e);
			}

	}
		
	
	public void setLimsDataModelConvertor(
			LimsDataModelConvertor limsDataModelConvertor) {
		this.limsDataModelConvertor = limsDataModelConvertor;
	}


	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}


	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}


	public void setLabDetailsDAO(LabDetailsDAOExtn labDetailsDAO) {
		this.labDetailsDAO = labDetailsDAO;
	}
}
