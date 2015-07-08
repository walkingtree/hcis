package in.wtc.lims.collectionPoint;

import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.SpecimenCollectionPointBM;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.common.LimsReferenceDataManager;
import in.wtc.lims.common.LimsUtils;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.LabCollectionPoint;
import com.wtc.hcis.da.LabCollectionPointDAO;
import com.wtc.hcis.da.LabCollectionPointLabAssociation;
import com.wtc.hcis.da.LabCollectionPointLabAssociationDAO;
import com.wtc.hcis.da.LabCollectionPointLabAssociationId;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.extn.LabCollectionPointDAOExtn;

public class CollectionPointManagerImpl implements CollectionPointManager {
	
	
	private LimsDataModelConvertor limsDataModelConvertor;
	private ContactDetailsDAO contactDetailsDAO;
	private LabCollectionPointDAOExtn collectionPointDAO;
	private LabCollectionPointLabAssociationDAO collectionPointLabAssociationDAO;
	private LimsDataModelManager limsDataModelManager;
	
	@Override
	public void addCollectionPoint(SpecimenCollectionPointBM collectionPointBM)
			throws LimsException {
		try{
			ContactDetails contactDetails = limsDataModelConvertor.convertContactDetailsBM2DM(collectionPointBM.getContactDetails(), null);
			
			contactDetails.setCreatedBy(collectionPointBM.getCreatedBy());
			contactDetails.setCreateDtm(new Date());
			
			contactDetailsDAO.save(contactDetails);
			
			LabCollectionPoint collectionPoint = limsDataModelConvertor.convertSpecimenCollectionPointBM2DM(collectionPointBM , null);
			
			collectionPoint.setContactDetails(contactDetails);
			collectionPoint.setCreatedDtm(new Date());
			collectionPoint.setCreatedBy(collectionPointBM.getCreatedBy());
			
			collectionPointDAO.save(collectionPoint);
			
			List<CodeAndDescription> associatedLabList = collectionPointBM.getAssociatedLabList();
			
			this.createCollectionPointLabAssociation(associatedLabList, collectionPoint);
			
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.COLLECTION_POINT_ADD_FAILED);
			throw new LimsException( fault, e );

		}

	}

	@Override
	public ListRange getCollectionPoint(
			String collectionPointName, String collectionPointId,
			String associatedLabId, String areaCovered, 
			String city, int start,
			int count ,String orderBy ) throws LimsException {

		try{
			List<LabCollectionPoint> collectionPointList = collectionPointDAO.getCollectionPoint(collectionPointName, 
					collectionPointId, 
					associatedLabId, 
					areaCovered, 
					city);
			
			List<SpecimenCollectionPointBM> list = new ArrayList<SpecimenCollectionPointBM>(0);
			
			if( collectionPointList != null && collectionPointList.size() > 0 ){
				for(LabCollectionPoint collectionPoint : collectionPointList){
					SpecimenCollectionPointBM collectionPointBM = limsDataModelConvertor.convertSpecimenCollectionPointDM2BM(collectionPoint);
					list.add(collectionPointBM);
				}
			}

			return LimsUtils.convertListToListRange(list, null);
		}
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.COLLECTION_POINT_READ_FAILED);
			throw new LimsException( fault, e );

		}
	}

	@Override
	public SpecimenCollectionPointBM modifyCollectionPoint(
			SpecimenCollectionPointBM collectionPointBM) throws LimsException {
		try{
			
			LabCollectionPoint existingCollectionPoint = limsDataModelManager.getLabCollectionPoint(collectionPointBM.getCollectionPointId());
			
			ContactDetails existingContactDetails = limsDataModelManager.getContactDetails(existingCollectionPoint.getContactDetails().getContactCode());
			ContactDetails contactDetails = limsDataModelConvertor.convertContactDetailsBM2DM(collectionPointBM.getContactDetails(), existingContactDetails);
			
			contactDetails.setModifiedBy(collectionPointBM.getCreatedBy());
			contactDetails.setModifiedDtm(new Date());
			
			contactDetailsDAO.attachDirty(contactDetails);
			
			LabCollectionPoint collectionPoint = limsDataModelConvertor.convertSpecimenCollectionPointBM2DM(collectionPointBM, existingCollectionPoint);
			
			collectionPoint.setModifiedBy(collectionPointBM.getCreatedBy());
			collectionPoint.setModifiedDtm(new Date());
			
			collectionPointDAO.attachDirty(collectionPoint);
			
			List<LabCollectionPointLabAssociation> collectionPointLabAssociationList = collectionPointLabAssociationDAO
										.findByProperty("id.collectionPointId", collectionPoint.getCollectionPointId());
			
			if( collectionPointLabAssociationList != null && collectionPointLabAssociationList.size() > 0 ){
				for( LabCollectionPointLabAssociation collectionPointLabAssociation : collectionPointLabAssociationList){
					collectionPointLabAssociationDAO.delete(collectionPointLabAssociation);
				}
			}
			
			List<CodeAndDescription> associatedLabList = collectionPointBM.getAssociatedLabList();
			
			this.createCollectionPointLabAssociation(associatedLabList, collectionPoint);
			
			return collectionPointBM;
		}
		
		catch(Exception e){
			Fault fault = new Fault(LimsErrors.COLLECTION_POINT_MODIFY_FAILED);
			throw new LimsException( fault, e );

		}
		
	}
	
	/*
	 * This method will save associated lab with the collection point into 
	 * the LabCollectionPointLabAssociation table
	 * 
	 */
	
	private void createCollectionPointLabAssociation( List<CodeAndDescription> associatedLabList ,LabCollectionPoint collectionPoint){
		if( associatedLabList != null && associatedLabList.size() > 0){
			for( CodeAndDescription associatedLab : associatedLabList){
				
				LabCollectionPointLabAssociationId id = new LabCollectionPointLabAssociationId();
				id.setCollectionPointId(collectionPoint.getCollectionPointId());
				id.setLabId(associatedLab.getCode());
				
				LabDetails labDetail = limsDataModelManager.getLabDetail( associatedLab.getCode() );
				LabCollectionPointLabAssociation collectionPointlabAssoc = new LabCollectionPointLabAssociation();
				
				collectionPointlabAssoc.setId(id);
				
//				collectionPointlabAssoc.setLabCollectionPoint(collectionPoint);
//				collectionPointlabAssoc.setLabDetails(labDetail);
				if( collectionPoint.getModifiedBy() != null){
					collectionPointlabAssoc.setCreatedBy(collectionPoint.getModifiedBy());
				}
				else{
					collectionPointlabAssoc.setCreatedBy(collectionPoint.getCreatedBy());
				}	
				collectionPointlabAssoc.setCreatedDtm(new Date());
				
				collectionPointLabAssociationDAO.save(collectionPointlabAssoc);
			}
		}

	}
	
	public SpecimenCollectionPointBM getCollectionPointForId( String collectionPointId)
			throws LimsException{
		try{
			LabCollectionPoint collectionPoint = limsDataModelManager.getLabCollectionPoint(collectionPointId);
			SpecimenCollectionPointBM collectionPointBM = limsDataModelConvertor.convertSpecimenCollectionPointDM2BM(collectionPoint);
			
			List<LabCollectionPointLabAssociation> associatedLabList = collectionPointLabAssociationDAO.findByProperty("id.collectionPointId", collectionPointId);
			
			List<CodeAndDescription> associatedLabs = null; 
			
			if( associatedLabList != null && associatedLabList.size() > 0){
				associatedLabs = new ArrayList<CodeAndDescription>(0);
				for( LabCollectionPointLabAssociation associatedLab : associatedLabList ){
					CodeAndDescription lab = new CodeAndDescription();
					
					if( associatedLab.getLabDetails() != null ){
						lab.setCode(associatedLab.getLabDetails().getLabId());
						lab.setDescription(associatedLab.getLabDetails().getLabName());
					}
					
					associatedLabs.add( lab );
				}
			}
			
			collectionPointBM.setAssociatedLabList(associatedLabs);
			
			return collectionPointBM;
		}
		catch(Exception e){
			Fault fault = new Fault( LimsErrors.COLLECTION_POINT_READ_FAILED );
			throw new LimsException( fault, e );

		}
	}
	

	public void setLimsDataModelConvertor(
			LimsDataModelConvertor limsDataModelConvertor) {
		this.limsDataModelConvertor = limsDataModelConvertor;
	}

	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}

	public void setCollectionPointDAO(LabCollectionPointDAOExtn collectionPointDAO) {
		this.collectionPointDAO = collectionPointDAO;
	}

	public void setCollectionPointLabAssociationDAO(
			LabCollectionPointLabAssociationDAO collectionPointLabAssociationDAO) {
		this.collectionPointLabAssociationDAO = collectionPointLabAssociationDAO;
	}

	public void setLimsDataModelManager(LimsDataModelManager limsDataModelManager) {
		this.limsDataModelManager = limsDataModelManager;
	}

}
