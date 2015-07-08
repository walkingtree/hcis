/**
 * 
 */
package in.wtc.hcis.bo.document;

import in.wtc.hcis.bm.base.CheckListBM;
import in.wtc.hcis.bm.base.CheckListDetailBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.CommonDataManager;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.DocCheckList;
import com.wtc.hcis.da.DocCheckListDetail;
import com.wtc.hcis.da.DocCheckListInstance;
import com.wtc.hcis.da.DocCheckListInstanceDAO;
import com.wtc.hcis.da.DocCheckListInstanceDetail;
import com.wtc.hcis.da.DocCheckListInstanceDetailId;
import com.wtc.hcis.da.extn.DocCheckListDAOExtn;
import com.wtc.hcis.da.extn.DocCheckListDetailDAOExtn;
import com.wtc.hcis.da.extn.DocCheckListInstanceDetailDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class CheckListManagerImpl implements CheckListManager {

	private DataModelConverter dataModelConverter;
	private DocCheckListDetailDAOExtn checkListDetailDAO;
	private DocCheckListDAOExtn checkListDAO;
	private DocCheckListInstanceDetailDAOExtn checkListInstanceDetailDAOExtn;
	private CommonDataManager commonDataManager;
	private DocCheckListInstanceDAO checkListInstanceDAO;
	private DataModelManager dataModelManager;
	
	
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.document.CheckListManager#addCheckList(in.wtc.hcis.bm.base.CheckListBM)
	 */
	public void addCheckList(CheckListBM checkListBM) throws HCISException {
		try{
			DocCheckList checkList = dataModelConverter.convertCheckListBM2DM(checkListBM, null);
			checkList.setCreatedBy(checkListBM.getUserId());
			checkList.setCreatedDtm(new Date());
			
			checkListDAO.save(checkList);
			
			this.addCheckListDetails(checkListBM.getCheckListDetailBMList(), checkList);
			
		}
		catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_ADD_FAILED);
			throw new HCISException(fault,e);
		}

	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.document.CheckListManager#getCheckListOfId(java.lang.Integer)
	 */
	public CheckListBM getCheckListOfId(Integer checkListId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Modify method has two forms-
	 *
	 * 1. Normal mode-  if check list is being used (assigned to patient etc.) then throw
	 *    exception saying that- Modification of already used checklist is not allowed
	 * 
	 * 2. 'Force Modify' mode- Modify the check list and detail, irrespective of whether it
	 * 	   is being used or not.
	 */
	public CheckListBM modifyCheckList(CheckListBM modifiedCheckList,boolean forceModify) {
		
		try {
			Validate.notNull(modifiedCheckList, "CheckListBM must not be null");
			
			DocCheckList docCheckList = commonDataManager.getCheckList(modifiedCheckList.getCheckListId());
				
			//Check whether this check list is being used somewhere
			
			if(!forceModify){
				
				List<DocCheckListInstanceDetail> instanceDetails = checkListInstanceDetailDAOExtn
																	.getDocCheckListInstanceDetail(null, modifiedCheckList
																			.getCheckListId());
				
				if(WtcUtils.isValid(instanceDetails)){
					
					throw new Exception(" Modification of arleady used check list is not allwoed!");
				}
			}
			
			
			dataModelConverter.convertCheckListBM2DM(modifiedCheckList, docCheckList);
			
			checkListDAO.attachDirty(docCheckList);
			
			List<CheckListDetailBM> checkListDetailBMList = modifiedCheckList.getCheckListDetailBMList();
			
			List<DocCheckListDetail> existingCheckListDetal = this.getDocCheckListDetail(docCheckList.getCheckListId());
			
			List<DocCheckListDetail> newCheckListDetal = new ArrayList<DocCheckListDetail>();
			if(WtcUtils.isValid(checkListDetailBMList)){
				
				for( CheckListDetailBM checkListDetailBM : checkListDetailBMList){
					
					//Attach dirty will add new record if not exist otherwise update existing.
					DocCheckListDetail checkListDetail = dataModelConverter.convertCheckListDetailBM2DM(checkListDetailBM);
					
					checkListDetail.setCheckListDetailId(checkListDetailBM.getCheckListDetailId());
					checkListDetail.setCreatedBy( modifiedCheckList.getUserId());
					checkListDetail.setCreatedDtm( new Date());
					checkListDetail.setDocCheckList(docCheckList);
					checkListDetailDAO.attachDirty(checkListDetail);
					newCheckListDetal.add(checkListDetail);
				}
			}
			
			
			//Now try to delete other ones
			if(WtcUtils.isValid(existingCheckListDetal)){

				existingCheckListDetal.removeAll(newCheckListDetal);
				
				if(existingCheckListDetal.size() > 0){
					
					for(DocCheckListDetail checkListDetail : existingCheckListDetal){
						
						checkListDetailDAO.delete(checkListDetail);
					}
				}
				
			}
			
			return dataModelConverter.convertCheckListDM2BM(docCheckList);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_DETAIL_MODIFY_FAILED);
			throw new HCISException(fault,e);
		}
		
	}

	/**
	 * Specially meant for making group text field at UI as auto-complete
	 * @param partialGroupName
	 * @return
	 */
	public ListRange getCheckListGroups(String partialGroupName ,int start, int count, String orderBy){
		
		try {
			List<String>  groupList = checkListDetailDAO.getDistinctGroupList();
		
			List<CodeAndDescription> groupList2ret = new ArrayList<CodeAndDescription>(0);
			
			if( WtcUtils.isValid(groupList)){
				
				for( String groupName : groupList){
				
					groupList2ret.add(new CodeAndDescription(groupName, groupName));
				}
			}
			
			return WtcUtils.convertListToListRange(groupList2ret,start,count);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_GROUP_READ_FAILED);
			throw new HCISException(fault,e);
		}
	}
	/**
	 *  
	 * @param checkListDetailBMList
	 * @param checkList
	 */
	private void addCheckListDetails( List<CheckListDetailBM> checkListDetailBMList , DocCheckList checkList)throws HCISException{
		try{
			if( WtcUtils.isValid(checkListDetailBMList )){
	
				for (CheckListDetailBM checkListDetailBM : checkListDetailBMList) {
					DocCheckListDetail checkListDetail = dataModelConverter.convertCheckListDetailBM2DM(checkListDetailBM);
					checkListDetail.setCheckListDetailId(1l);
					checkListDetail.setDocCheckList(checkList);
					checkListDetail.setCreatedBy(checkList.getCreatedBy());
					checkListDetail.setCreatedDtm(new Date());
					
					checkListDetailDAO.save(checkListDetail);
				}
			}
		} 
		catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_DETAIL_ADD_FAILED);
			throw new HCISException(fault,e);
		}
	}
	
	public ListRange getCheckList(String checkListName, String type,
			String groupName, String roleCd, int start, int count,
			String orderBy) throws HCISException {
		
		
		try {
			List<DocCheckList>  docCheckLists = checkListDAO.getCheckList(checkListName, type, groupName, roleCd, null, null, null);
			
			List<CheckListBM> checkListBMs = new ArrayList<CheckListBM>(0);
			
			if(WtcUtils.isValid(docCheckLists)){
				
				
				for( DocCheckList checkList : docCheckLists){
					
					CheckListBM checkListBM = dataModelConverter.convertCheckListDM2BM(checkList); 
					
					checkListBMs.add(checkListBM);
					
				}
			}
			
			return WtcUtils.convertListToListRange(checkListBMs, start, count);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_DETAIL_READ_FAILED);
			throw new HCISException(fault,e);
		}
	}
	
	/**
	 * This method removes check-list and it detail from the system. 
	 */
	public void removeCheckList ( Integer[] checkListIds) throws HCISException{
		 
		try {
			if(checkListIds != null && checkListIds.length > 0){
				
				for(Integer checkListId : checkListIds){
					
					List<DocCheckListDetail> docCheckListDetailList = this.getDocCheckListDetail(checkListId);
					
					if(WtcUtils.isValid(docCheckListDetailList)){
						
						for( DocCheckListDetail checkListDetail : docCheckListDetailList){
							
							checkListDetailDAO.delete(checkListDetail);
							
						}
					}
					DocCheckList persistentInstance = commonDataManager.getCheckList(checkListId);
					checkListDAO.delete(persistentInstance);
				}
			}
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_DETAIL_REMOVE_FAILED);
			throw new HCISException(fault,e);
		}
		
	}
	
	/*
	 * private method to return Data model for given checklist id
	 * 
	 */
	private List<DocCheckListDetail> getDocCheckListDetail(Integer checkListId){
		
		return checkListDetailDAO.findByProperty("docCheckList.checkListId", checkListId);
	}

	/**
	 * Return checklist detail for given checklist id
	 * @param checkListId
	 * @return
	 */
	public ListRange getCheckListDetails(Integer checkListId ,int start, int count,
			String orderBy){
		
		try {
			List<DocCheckListDetail> docCheckListDetailList = this.getDocCheckListDetail(checkListId);
			
			List<CheckListDetailBM> checkListDetailBMList = new ArrayList<CheckListDetailBM>();
			
			if(WtcUtils.isValid(docCheckListDetailList)){
				
				for(DocCheckListDetail checkListDetail : docCheckListDetailList){
					
					checkListDetailBMList.add(dataModelConverter.convertCheckListDetailDM2BM(checkListDetail));
				}
			}

			return WtcUtils.convertListToListRange(checkListDetailBMList);
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.CHECK_LIST_DETAIL_REMOVE_FAILED);
			throw new HCISException(fault);
		}
	}
	

	/**
	 * If checkListId is passed then return CheckListBM and if checkListInstanceId
	 * CheckListBM with instance information.
	 * 
	 * @param checkListId
	 * @param checkListInstanceId
	 * @return
	 */
	public CheckListBM getCheckListOfId(Integer checkListId,
			Long checkListInstanceId) {
		
		CheckListBM checkListBM = new CheckListBM();// This will be prepare from the checklist and ChekListInstance.
		List<CheckListDetailBM> checkListDetailBMList = null;//This will be preapare from the CheckListDetail and CheckListInstanceDetail.
		DocCheckList checkList =  null;
		
		DocCheckListInstance checkListInstance = null;

		if( checkListInstanceId != null ){
			
			checkListInstance = checkListInstanceDAO.findById(checkListInstanceId);
			
			if( checkListInstance.getDocCheckList() != null){
				checkList = checkListInstance.getDocCheckList();
				checkListBM.setCheckListInstanceId(checkListInstanceId);
				checkListBM = dataModelConverter.convertCheckListDM2BM(checkList);
			}
			
		}
		if( checkList == null){
			checkList = checkListDAO.findById(checkListId);
			checkListBM = dataModelConverter.convertCheckListDM2BM(checkList);
		}
		List<DocCheckListDetail> checkListDetailList = checkListDetailDAO.getCheckListDetails(checkList.getCheckListId());
		
		
		if( checkListDetailList != null && checkListDetailList.size() > 0){
			checkListDetailBMList = new ArrayList<CheckListDetailBM>(0);
			for (DocCheckListDetail checkListDetail : checkListDetailList) {
				CheckListDetailBM checkListDetailBM = dataModelConverter.convertCheckListDetailDM2BM(checkListDetail);
				
				if( checkListInstance != null){
					DocCheckListInstanceDetailId id = new DocCheckListInstanceDetailId();
					id.setCheckListInstanceId(checkListInstance.getCheckListInstanceId());
					id.setCheckListDetailId(checkListDetail.getCheckListDetailId());
					
					DocCheckListInstanceDetail checkListInstanceDetail = checkListInstanceDetailDAOExtn.findById(id);
					
					checkListDetailBM.setAnswer(checkListInstanceDetail.getAnswer());
					
				}
				
				
				checkListDetailBMList.add(checkListDetailBM);
			}
			
			checkListBM.setCheckListDetailBMList(checkListDetailBMList);
		}
		
		return checkListBM;
	}

	
	/**
	 * 
	 * Saves/modifies CheckListInstance.
	 * 
	 * Create check list instance first, then create checkListInstanceDetail.
	 * Return checkList instance id for further use of caller of this method.
	 * (There will be multiple clients of checkList they will store this id to 
	 * there concern tables )
	 * 
	 * @param checkListInstance
	 * @return checkListInstaceId	
	 * 
	 */
	public Long saveCheckListInstance(CheckListBM checkListInstanceBM)
			throws HCISException {
		try{
			DocCheckList checkList = checkListDAO.findById(checkListInstanceBM.getCheckListId());
			Long checkListInstanceId = checkListInstanceBM.getCheckListInstanceId();
			DocCheckListInstance checkListInstance = null;
			
			if( checkListInstanceBM.getCheckListInstanceId() != null){
				
				DocCheckListInstance docCheckListInstance = dataModelManager.getCheckListInstance(checkListInstanceBM.getCheckListInstanceId());
				
				checkListInstance = dataModelConverter.convertCheckListInstanceBM2DM(checkListInstanceBM, docCheckListInstance);
				
				checkListInstance.setModifiedBy(checkListInstanceBM.getUserId());
				checkListInstance.setLastModifiedDtm(new Date());
				
				checkListInstanceDAO.attachDirty(checkListInstance);
				
				List<DocCheckListInstanceDetail> checkListInstanceDetailList = checkListInstanceDetailDAOExtn.findByProperty("id.checkListInstanceId", checkListInstanceId);
				
				this.removeCheckListInstanceDetails(checkListInstanceDetailList);
				
			}
			else{
				checkListInstance = dataModelConverter.convertCheckListInstanceBM2DM(checkListInstanceBM, null);
				
				checkListInstance.setCreatedBy(checkListInstanceBM.getUserId());
				checkListInstance.setCreatedDtm(new Date());
				
				checkListInstanceDAO.save(checkListInstance);
				checkListInstanceId = checkListInstance.getCheckListInstanceId();
				
			}
			
			
			List<CheckListDetailBM> checkListDetailBMList = checkListInstanceBM.getCheckListDetailBMList();
			
			this.saveCheckListInstanceDetails(checkListDetailBMList, checkListInstance);
			return checkListInstanceId;
	
		}
		catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException( fault , e);
		}
	}
	
	/**
	 * This method will create checkListDetailInstance.
	 * @param checkListDetailBMList
	 * @param checkListInstance
	 */
	private void saveCheckListInstanceDetails( List<CheckListDetailBM> checkListDetailBMList, DocCheckListInstance checkListInstance){
		if( checkListDetailBMList != null && checkListDetailBMList.size() > 0){
			Long checkListInstanceId = checkListInstance.getCheckListInstanceId();
			for (CheckListDetailBM checkListDetailBM : checkListDetailBMList) {
				
				DocCheckListDetail checkListDetail = checkListDetailDAO.findById(checkListDetailBM.getCheckListDetailId());
				DocCheckListInstanceDetail checkListInstanceDetail = new DocCheckListInstanceDetail();
				
				DocCheckListInstanceDetailId id = new DocCheckListInstanceDetailId();
				
				id.setCheckListDetailId(checkListDetail.getCheckListDetailId());
				id.setCheckListInstanceId(checkListInstanceId);
				
				checkListInstanceDetail.setId(id);
				
				checkListInstanceDetail.setAnswer(checkListDetailBM.getAnswer());
				checkListInstanceDetail.setCreatedBy(checkListInstance.getCreatedBy());
				checkListInstanceDetail.setCreatedDtm(new Date());
				checkListInstanceDetail.setDocCheckListDetail(checkListDetail);
				checkListInstanceDetail.setDocCheckListInstance(checkListInstance);
				
				checkListInstanceDetailDAOExtn.save(checkListInstanceDetail);
			}
		}
	}
	
	
	private void removeCheckListInstanceDetails(List<DocCheckListInstanceDetail> checkListInstanceDetails){
		if( WtcUtils.isValid(checkListInstanceDetails)){
			for (DocCheckListInstanceDetail checkListInstanceDetail : checkListInstanceDetails) {
				checkListInstanceDetailDAOExtn.delete(checkListInstanceDetail);
			}
		}
	}

	
	
	
	public void setCheckListDetailDAO(DocCheckListDetailDAOExtn checkListDetailDAO) {
		this.checkListDetailDAO = checkListDetailDAO;
	}

	public void setDataModelConverter(DataModelConverter dataModelConverter) {
		this.dataModelConverter = dataModelConverter;
	}

	public void setCheckListDAO(DocCheckListDAOExtn checkListDAO) {
		this.checkListDAO = checkListDAO;
	}

	public void setCommonDataManager(CommonDataManager commonDataManager) {
		this.commonDataManager = commonDataManager;
	}

	public void setCheckListInstanceDetailDAOExtn(
			DocCheckListInstanceDetailDAOExtn checkListInstanceDetailDAOExtn) {
		this.checkListInstanceDetailDAOExtn = checkListInstanceDetailDAOExtn;
	}

	public DataModelConverter getDataModelConverter() {
		return dataModelConverter;
	}

	public DocCheckListDetailDAOExtn getCheckListDetailDAO() {
		return checkListDetailDAO;
	}

	public DocCheckListDAOExtn getCheckListDAO() {
		return checkListDAO;
	}

	public CommonDataManager getCommonDataManager() {
		return commonDataManager;
	}

	public void setCheckListInstanceDAO(DocCheckListInstanceDAO checkListInstanceDAO) {
		this.checkListInstanceDAO = checkListInstanceDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

}
