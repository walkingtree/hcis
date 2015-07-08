/**
 * 
 */
package in.wtc.lims.common;

import in.wtc.lims.bm.CodeAndDescription;
import in.wtc.lims.bm.ListRange;
import in.wtc.lims.constant.LimsConstants;
import in.wtc.lims.exception.LimsException;

import java.util.ArrayList;
import java.util.List;

import com.wtc.hcis.da.Hospital;
import com.wtc.hcis.da.HospitalDAO;
import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.LabDetailsDAO;
import com.wtc.hcis.da.LabSpecimen;
import com.wtc.hcis.da.LabSpecimenDAO;
import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTechniqueReagentDAO;
import com.wtc.hcis.da.LabTest;
import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestAttributeDAO;
import com.wtc.hcis.da.LabTestDAO;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;

/**
 * @author Bhavesh
 *
 */
public class LimsReferenceDataManagerImpl implements LimsReferenceDataManager{

	/**
	 * 
	 */
	
	private ReferenceDataListDAO referenceDataListDAO;
	private LabTestAttributeDAO labTestAttributeDAO;
	private LabDetailsDAO labDetailsDAO;
	private LabSpecimenDAO labSpecimenDAO;
	private HospitalDAO hospitalDAO;
	private LabTechniqueReagentDAO labTechniqueReagentDAO;
	private LabTestDAO labTestDAO;
	
	private LimsDataModelManager limsDataModelManager;
	
	@Override
	public ListRange getAllTestAttribute(int start, int count, String orderBy)
			throws LimsException {
		
		List<LabTestAttribute> labTestAttributeList = labTestAttributeDAO.findAll();
		
		List<CodeAndDescription> list = new ArrayList<CodeAndDescription>(0);
		
		if( labTestAttributeList != null && !labTestAttributeList.isEmpty() ){
			
			for(LabTestAttribute labTestAttribute : labTestAttributeList){
				
				list.add(new CodeAndDescription(labTestAttribute.getAttributeCode(),
						labTestAttribute.getAttributeName()));
			}
		}
		
		return this.convertListToListRange(list);
	}

	@Override
	public ListRange getLabs(int start, int count, String orderBy)
			throws LimsException {
		
		List<LabDetails> labDetailsList = labDetailsDAO.findAll();
		
		List<CodeAndDescription> list = new ArrayList<CodeAndDescription>(0);
		
		if( labDetailsList != null && !labDetailsList.isEmpty() ){
			
			for(LabDetails labDetails : labDetailsList){
				
				list.add(new CodeAndDescription(labDetails.getLabId(),
						labDetails.getLabName()));
			}
		}
		
		
		return this.convertListToListRange( list );
	}

	@Override
	public ListRange getSpecimens(int start, int count, String orderBy)
			throws LimsException {
		
		List<LabSpecimen> labSpecimenList = labSpecimenDAO.findAll();
		
		List<CodeAndDescription> list = new ArrayList<CodeAndDescription>(0);
		
		if( labSpecimenList != null && !labSpecimenList.isEmpty() ){
			
			for(LabSpecimen labSpecimen : labSpecimenList){
				
				list.add(new CodeAndDescription(String.valueOf(labSpecimen.getSpecimenId()),
						labSpecimen.getSpecimenName()));
			}
		}
		
		
		return this.convertListToListRange( list );
	}

	@Override
	public ListRange getTestApplicableGengers(int start, int count,
			String orderBy) throws LimsException {
		
		return  this.getReferenceDataList( LimsConstants.CONTEXT_TEST_FOR_GENDER, start, count, orderBy);
	}

	@Override
	public ListRange getTestTypes(int start, int count, String orderBy)
			throws LimsException {
		return  this.getReferenceDataList( LimsConstants.CONTEXT_LAB_TYPE, start, count, orderBy);
	}

	public ListRange getReferenceDataList(String context, int start, int count, String orderBy ){
		ListRange listRange = new ListRange();
		try {
			List<ReferenceDataList> referenceDataList = referenceDataListDAO.findByProperty("id.context",context);
			Object[] codeDescObj = new Object[referenceDataList.size()];
			if (referenceDataList != null && referenceDataList.size() > 0) {
				for (int i = 0; i < referenceDataList.size(); i++) {
					ReferenceDataList tmp = referenceDataList.get(i);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(tmp.getId().getAttrCode());
					codeAndDescription.setDescription(tmp.getAttrDesc());
					codeAndDescription.setIsDefault( tmp.getIsDefault());
					codeDescObj[i] = codeAndDescription;
				}

			}
			listRange.setData(codeDescObj);
			listRange.setTotalSize(referenceDataList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listRange;
		
	}

public ListRange getTechniqueReagent(int start, int count, String orderBy){
		
		List <LabTechniqueReagent> labTechniqueReagentList = labTechniqueReagentDAO.findAll();
		
		List<CodeAndDescription> result = new ArrayList<CodeAndDescription>(0);
		
		if(labTechniqueReagentList != null && !labTechniqueReagentList.isEmpty()){
			for( LabTechniqueReagent techniqueReagent : labTechniqueReagentList){
				
				result.add( new CodeAndDescription( String.valueOf(techniqueReagent.getId()),
						techniqueReagent.getName()));
			}
		}
		
		return this.convertListToListRange(result);
	}

	public ListRange getTechniqueReagentByType(String isTechnique,int start, int count, String orderBy){
		
		List <LabTechniqueReagent> labTechniqueReagentList = labTechniqueReagentDAO.findByIsTechnique(isTechnique);
		
		List<CodeAndDescription> result = new ArrayList<CodeAndDescription>(0);
		
		if(labTechniqueReagentList != null && !labTechniqueReagentList.isEmpty()){
			for( LabTechniqueReagent techniqueReagent : labTechniqueReagentList){
				
				result.add( new CodeAndDescription( String.valueOf(techniqueReagent.getId()),
						techniqueReagent.getName()));
			}
		}
		
		return this.convertListToListRange(result);
	}

	private ListRange convertListToListRange(List listObject){
		
		ListRange listRange = new ListRange();
		if( listObject != null && !listObject.isEmpty() ){
			
			Object[] objects = listObject.toArray();
			listRange.setData( objects );
			listRange.setTotalSize(listObject.size());
		}else{
			
			listRange.setTotalSize(0);
			listRange.setData( new Object[0]);
		}
		
		return listRange;
	}
	
	public ListRange getLabType(int start, int count, String orderBy)
	throws LimsException {
	ListRange listRange = this.getReferenceDataList( LimsConstants.CONTEXT_LAB_TYPE,
												start,
												count,
												orderBy );
	return listRange;
}

	public ListRange getHospitalName(int start, int count, String orderBy) throws LimsException 
	{
	 ListRange listRange = new ListRange();
	 try {
		     List<Hospital> hospitalList =(List<Hospital>)hospitalDAO.findAll();
		    Object[] codeDescObj = new Object[hospitalList.size()];
		    if(hospitalList!=null && hospitalList.size()>0) {
		    	for(int i =0; i<hospitalList.size();i++) {
		    		Hospital hospital = hospitalList.get(i);
		    					    	CodeAndDescription codeAndDescription = new CodeAndDescription();
			    	codeAndDescription.setCode(hospital.getHospitalCode()); 
			    	codeAndDescription.setDescription(hospital.getHospitalName());
			    	codeDescObj[i]=codeAndDescription;
			    	
		    	}
			    	
		    }
		    listRange.setData(codeDescObj);
			listRange.setTotalSize(hospitalList.size());
		   
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return listRange;
	}

	/**
	 * 
	 */
	public ListRange getMeasurementUnit(int start, int count, String orderBy)
			throws LimsException {
		return  this.getReferenceDataList( LimsConstants.CONTEXT_LAB_MEASUREMENT_UNIT, start, count, orderBy);
		
	}
	
	/**
	 * 
	 
	 */
	public ListRange getAttributeType( int start, int count, String orderBy ){
		
		return  this.getReferenceDataList( LimsConstants.CONTEXT_TEST_ATTRIBUTE_TYPE, start, count, orderBy);
	}
	
	
	public ListRange getLabTests( int start, int count, String orderBy ){
		
		
		List <LabTest> labTestList = labTestDAO.findAll();
		
		List<CodeAndDescription> result = new ArrayList<CodeAndDescription>(0);
		
		if(labTestList != null && !labTestList.isEmpty()){
			for( LabTest labTest : labTestList){
				
				result.add( new CodeAndDescription( labTest.getTestCode(),
													labTest.getTestName()));
			}
		}
		
		return LimsUtils.convertListToListRange(result, null);
		
	}
	
	
	
	public void setHospitalDAO(HospitalDAO hospitalDAO) {
		this.hospitalDAO = hospitalDAO;
	}

	public void setLabDetailsDAO(LabDetailsDAO labDetailsDAO) {
		this.labDetailsDAO = labDetailsDAO;
	}

	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public void setLabTestAttributeDAO(LabTestAttributeDAO labTestAttributeDAO) {
		this.labTestAttributeDAO = labTestAttributeDAO;
	}

	public void setLabSpecimenDAO(LabSpecimenDAO labSpecimenDAO) {
		this.labSpecimenDAO = labSpecimenDAO;
	}

	public void setLabTechniqueReagentDAO(
			LabTechniqueReagentDAO labTechniqueReagentDAO) {
		this.labTechniqueReagentDAO = labTechniqueReagentDAO;
	}

	public void setLimsDataModelManager(LimsDataModelManager limsDataModelManager) {
		this.limsDataModelManager = limsDataModelManager;
	}

	public void setLabTestDAO(LabTestDAO labTestDAO) {
		this.labTestDAO = labTestDAO;
	}
	
}
