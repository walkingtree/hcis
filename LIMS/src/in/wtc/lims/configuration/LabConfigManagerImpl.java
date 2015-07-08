/**
 * 
 */
package in.wtc.lims.configuration;

import in.wtc.lims.bm.LabTestAttributeBM;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.bm.TechniqueReagentBM;
import in.wtc.lims.common.LimsDataModelConvertor;
import in.wtc.lims.common.LimsDataModelManager;
import in.wtc.lims.common.LimsUtils;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.constant.LimsErrors;
import in.wtc.lims.exception.Fault;
import in.wtc.lims.exception.LimsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTemplateWidgetDAO;
import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestAttributeAssociation;
import com.wtc.hcis.da.extn.LabTechniqueReagentDAOExtn;
import com.wtc.hcis.da.extn.LabTestAttributeDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class LabConfigManagerImpl implements LabConfigManager {

	/* (non-Javadoc)
	 * @see in.wtc.lims.configuration.LabConfigManager#addTechniquReagent(in.wtc.lims.bm.TechniequeReagentBM)
	 */
	
	private LimsDataModelConvertor limsDataModelConvertor;
	private LimsDataModelManager dataModelManager;
	private LabTechniqueReagentDAOExtn labTechniqueReagentDAO;
	private LabTestAttributeDAOExtn testAttributeDAO;
	private LabTemplateWidgetDAO labTemplateWidgetDAO;
	
	@Override
	public void addTechniqueReagent(TechniqueReagentBM techniqueReagentBM) {
		

		try {
			LabTechniqueReagent techniqueReagent = new LabTechniqueReagent();
			techniqueReagent.setName(techniqueReagentBM.getName() );
			techniqueReagent.setDescription( techniqueReagentBM.getDescription());
			techniqueReagent.setCreatedBy(techniqueReagentBM.getCreatedBy());
			techniqueReagent.setCreatedDtm( new Date() );
			techniqueReagent.setIsTechnique(techniqueReagentBM.getIsTechnique());
			labTechniqueReagentDAO.save(techniqueReagent);
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.TECHNIQUE_ADD_FAILED);
			throw new LimsException(fault, e);
		}
		
	}

	@Override
	public TechniqueReagentBM modifyTechniqueReagent( TechniqueReagentBM modifiedTechniqueReagentBM) {
		
		try {
			LabTechniqueReagent existingTechniqueReagent = 
				dataModelManager.getLabTechniqueReagent(modifiedTechniqueReagentBM.getTechniequeReagentId());
			

			existingTechniqueReagent.setName(modifiedTechniqueReagentBM.getName() );
			existingTechniqueReagent.setDescription( modifiedTechniqueReagentBM.getDescription());
			existingTechniqueReagent.setIsTechnique( modifiedTechniqueReagentBM.getIsTechnique() );
			
			labTechniqueReagentDAO.attachDirty(existingTechniqueReagent);
			
			return modifiedTechniqueReagentBM;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.TECHNIQUE_MODIFY_FAILED);
			throw new LimsException(fault, e);
		}
	}

	/*
	 * Returns  list of technique and reagents
	 */
	@Override
	public ListRange getTechniquReagents(Integer techReagentId, String name,
			String isTechnique, String testCode, int start, int count, String orderBy)
			throws LimsException {
		
		try {
			List<LabTechniqueReagent> labTechniqueReagentList = 
				labTechniqueReagentDAO.getLabTechniqueReagent(techReagentId,name, isTechnique, testCode);

			ListRange listRange = new ListRange();
			
			if( labTechniqueReagentList != null && !labTechniqueReagentList.isEmpty() ){
				
				int index = 0;
				List<TechniqueReagentBM> pageSizeData = new ArrayList<TechniqueReagentBM>(0);
				
				while ((start + index < start + count) && (labTechniqueReagentList.size() > start+index) ){ 
					TechniqueReagentBM techniqueReagentBM = new TechniqueReagentBM();
					
					LabTechniqueReagent techniqueReagent = labTechniqueReagentList.get(start+index);
					
					techniqueReagentBM.setTechniequeReagentId(techniqueReagent.getId());
					techniqueReagentBM.setName( techniqueReagent.getName() );
					techniqueReagentBM.setDescription( techniqueReagent.getDescription() );
					techniqueReagentBM.setIsTechnique( techniqueReagent.getIsTechnique());
					techniqueReagentBM.setCreatedBy( techniqueReagent.getCreatedBy() );
					techniqueReagentBM.setCreatedDate( techniqueReagent.getCreatedDtm());
					
					pageSizeData.add( techniqueReagentBM );
						index++;
				}
					listRange.setData(pageSizeData.toArray());
					listRange.setTotalSize(labTechniqueReagentList.size());
				}else {
					listRange.setData(new Object[0]);
					listRange.setTotalSize(0);
				}
				
				return listRange;
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.TECHNIQUE_READ_FAILED);
			throw new LimsException(fault, e);
		}
	}
	
	/**
	 * Removes technique and reagents of given ids
	 */
	@Override
	public boolean removeTechniqueReagent(Integer[] techniqueReagentIds)
																throws LimsException {
		
		try {
			if (techniqueReagentIds != null && techniqueReagentIds.length > 0) {

				for (Integer techniqueReagentId : techniqueReagentIds) {

					labTechniqueReagentDAO.delete(dataModelManager
							.getLabTechniqueReagent(techniqueReagentId));
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.TECHNIQUE_DELETE_FAILED);
			throw new LimsException(fault, e);
		}
		
	}
	
	public LabTestAttributeBM getTestAttribute(String attributeCode ){
		
		try {
			LabTestAttribute testAttribute =  dataModelManager.getLabTestAttribute(attributeCode);
			
			return limsDataModelConvertor.convertLabTestAttributeDM2BM(testAttribute);
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.LAB_TEST_ATTRIBUTE_READ_FAILED);
			throw new LimsException(fault, e);
		}
		
		
	}
/**
 * Create new lab test attribute into the system. At the same time creates corresponding
 * widget to enter the test result and to configure template. 
 */
	public void addTestAttribute( LabTestAttributeBM attributeBM){
		
		try {
			LabTestAttribute labTestAttribute =  limsDataModelConvertor.convertLabTestAttributeBM2DM(attributeBM, null);
			
			testAttributeDAO.save( labTestAttribute );
			
			//Now at the same time create corresponding widgets
			this.saveWidgetForAttribute(labTestAttribute, false);
			
		} catch (Exception e) {
			Fault fault = new Fault(LimsErrors.LAB_TEST_ATTRIBUTE_ADD_FAILED);
			throw new LimsException(fault, e);
		}
		
	}
	
	private void saveWidgetForAttribute(LabTestAttribute labTestAttribute, boolean isModifyMode){
		
		try {
			LabTemplateWidget templateWidget = null;
			
			if(isModifyMode){
				templateWidget = dataModelManager.getLabTemplateWidget( labTestAttribute.getAttributeCode());
			
			}else{
				
				templateWidget = new LabTemplateWidget();
				templateWidget.setWidgetCode(labTestAttribute.getAttributeCode());
			}
			
			templateWidget.setLabTestAttribute(labTestAttribute);
			templateWidget.setWidgetLabel( labTestAttribute.getAttributeName());
			templateWidget.setValueType( labTestAttribute.getType() );
			templateWidget.setWidgetType(this.getWidgetTypeCode(labTestAttribute.getType()));
			templateWidget.setSectionCode( LimsConstants.TEST_ATTRIBUTE_SECTION);
			
			labTemplateWidgetDAO.save(templateWidget);
		} catch (Exception e) {
			
			Fault fault = new Fault(LimsErrors.TEMPLATE_WIDGET_ADD_FAILED);
			throw new LimsException(fault, e);
		}
		
	}
	
	/**
	 * 
	 * @param attributeType
	 * @return corresponding widgetTypeCode
	 */
	private String getWidgetTypeCode( String attributeType){
		
		if( LimsConstants.ATTR_TYPE_NUMERIC.equals(attributeType) ){

			return LimsConstants.WIDGET_TYPE_NUMBER;
		
		}else if( LimsConstants.ATTR_TYPE_TEXT.equals(attributeType) ){
			
			return LimsConstants.WIDGET_TYPE_STRING;
		
		}else if( LimsConstants.ATTR_TYPE_OBSERVATION.equals(attributeType) ){
			
			return LimsConstants.WIDGET_TYPE_MVL;
			
		}
		
		return null;
	}
	public ListRange getTestAttribute( String labAttributeCode, String labAttributeName,
								String type,String testCode ,int start, int count, String orderBy){
		
		
		List<LabTestAttribute> labTestAttributeList = testAttributeDAO.getLabTestAttributes(labAttributeCode, 
																		labAttributeName, type, testCode);
		
		List<LabTestAttributeBM> labTestAttributeBMList = new ArrayList<LabTestAttributeBM>(0);
		
		int resultSize = 0;
		
		if( labTestAttributeList != null && !labTestAttributeList.isEmpty() ){
			
			int index = 0;
			resultSize = labTestAttributeList.size();
			
			while (( index <  count) && (labTestAttributeList.size() > start+index) ){ 
				
				LabTestAttribute labTestAttribute = labTestAttributeList.get(start+index);
				labTestAttributeBMList.add( limsDataModelConvertor.convertLabTestAttributeDM2BM(labTestAttribute));
				index++;
			}
		}
		
		ListRange result = LimsUtils.convertListToListRange(labTestAttributeBMList, resultSize);
		return result;
	}
/**
 * Modifies the test attribute and at the same time made relative changes to 
 * corresponding widget.	
 */
	public LabTestAttributeBM modifyTestAttribute( LabTestAttributeBM modifiedAttributeBM){
		
			
		try {
			Validate.notNull(modifiedAttributeBM, " AttributeBM must not be null ");
			
			LabTestAttribute existingAttribute = dataModelManager.getLabTestAttribute( modifiedAttributeBM.getAttributeCode() );
			
			limsDataModelConvertor.convertLabTestAttributeBM2DM(modifiedAttributeBM, existingAttribute);
			
			testAttributeDAO.attachDirty( existingAttribute );
			
			LabTemplateWidget templateWidget = 
				dataModelManager.getLabTemplateWidget(existingAttribute.getAttributeCode());
			templateWidget.setLabTestAttribute(existingAttribute);
			templateWidget.setWidgetLabel( existingAttribute.getAttributeName());
			templateWidget.setValueType( existingAttribute.getType() );
			templateWidget.setWidgetType(this.getWidgetTypeCode(existingAttribute.getType()));
			templateWidget.setSectionCode( LimsConstants.TEST_ATTRIBUTE_SECTION);
			
			labTemplateWidgetDAO.attachDirty(templateWidget);
			
			return limsDataModelConvertor.convertLabTestAttributeDM2BM(existingAttribute);
		} catch (Exception e) {
			
			Fault fault = new Fault( LimsErrors.LAB_TEST_ATTRIBUTE_MODIFY_FAILED);
			
			throw new LimsException(fault, e);
		}
		
	}

	/**
	 * This method remove test attribute(s) and corresponding widget from the system, 
	 * only if it is not associated with any labTest; throws exception otherwise.
	 *  
	 * @param attributeCodeList
	 */
	public void removeTestAttribute( List<String> attributeCodeList){
		
		try {
			if( attributeCodeList != null && !attributeCodeList.isEmpty()){
				
				for(String attributeCode : attributeCodeList){
					
					LabTestAttribute testAttribute = dataModelManager.getLabTestAttribute(attributeCode);
					
					Set<LabTestAttributeAssociation> labTestsAssoList = testAttribute.getLabTestAttributeAssociations();
					
					if(labTestsAssoList != null && !labTestsAssoList.isEmpty()){
						
						StringBuilder errorMsg =  new StringBuilder("Attribute '"+testAttribute.getAttributeName()
								+"' is associated with test(s) ");
						
						for(LabTestAttributeAssociation association : labTestsAssoList){
							
							errorMsg = errorMsg.append( association.getLabTest().getTestName() +",");
						}
						
						throw new Exception(errorMsg.toString());
					}else{
						
						LabTemplateWidget widget = labTemplateWidgetDAO.findById(attributeCode);
						if(widget != null ){
							labTemplateWidgetDAO.delete(widget);
						}
						testAttributeDAO.delete(testAttribute);
					}
					
				}
			}
		} catch (Exception e) {

			Fault fault = new Fault( LimsErrors.LAB_TEST_ATTRIBUTE_REMOVE_FAILED);
			throw new LimsException(fault, e);
		}
		
	}
	
	public void setLimsDataModelConvertor(
			LimsDataModelConvertor limsDataModelConvertor) {
		this.limsDataModelConvertor = limsDataModelConvertor;
	}

	public void setDataModelManager(LimsDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setLabTechniqueReagentDAO(
			LabTechniqueReagentDAOExtn labTechniqueReagentDAO) {
		this.labTechniqueReagentDAO = labTechniqueReagentDAO;
	}

	public void setTestAttributeDAO(LabTestAttributeDAOExtn testAttributeDAO) {
		this.testAttributeDAO = testAttributeDAO;
	}

	public void setLabTemplateWidgetDAO(LabTemplateWidgetDAO labTemplateWidgetDAO) {
		this.labTemplateWidgetDAO = labTemplateWidgetDAO;
	}


	
	
}
