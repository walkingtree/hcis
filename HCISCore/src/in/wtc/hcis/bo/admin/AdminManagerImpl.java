/**
 * 
 */
package in.wtc.hcis.bo.admin;

import in.wtc.hcis.bm.ip.DiseaseBM;
import in.wtc.hcis.bm.ip.DiseaseRequireServiceBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.Disease;
import com.wtc.hcis.da.DiseaseRequiresService;
import com.wtc.hcis.da.DiseaseRequiresServiceDAO;
import com.wtc.hcis.da.DiseaseRequiresServiceId;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.extn.DiseaseDAOExtn;

/**
 * @author Bhavesh
 *
 */
public class AdminManagerImpl implements AdminManager {
	
	private DiseaseDAOExtn diseaseDAO;
	private DiseaseRequiresServiceDAO diseaseRequiresServiceDAO;
	private DataModelManager dataModelManager;

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.admin.AdminManager#addDisease(in.wtc.hcis.bm.ip.DiseaseBM)
	 */
	public void addDisease(DiseaseBM diseaseBM) {

		
		Disease disease = new Disease();
		
		disease.setDiseaseName( diseaseBM.getDiseaseName() );
		disease.setDescription( diseaseBM.getDiseaseDesc() );
		disease.setDiseaseGroup( diseaseBM.getDiseaseGroup() );
		disease.setCreatedBy( diseaseBM.getCreatedBy() );
		disease.setCreatedDtm( new Date() );
		
		diseaseDAO.save( disease );
		
		List<DiseaseRequireServiceBM> diseaseRequireServiceBMList = diseaseBM.getDiseaseRequireServiceBMList();
		
		if( diseaseRequireServiceBMList != null && !diseaseRequireServiceBMList.isEmpty() ){
			for( DiseaseRequireServiceBM diseaseRequireServiceBM : diseaseRequireServiceBMList ){
				DiseaseRequiresService diseaseRequiresService = new DiseaseRequiresService();
				
				DiseaseRequiresServiceId id = new DiseaseRequiresServiceId();
				id.setDiseaseName( diseaseBM.getDiseaseName() );
				id.setServiceCode( diseaseRequireServiceBM.getServiceCode() );

				diseaseRequiresService.setId(id);
				diseaseRequiresService.setDisease(disease);
				diseaseRequiresService.setCreatedBy( diseaseBM.getCreatedBy() );
				diseaseRequiresService.setCreatedDtm( new Date() );
				diseaseRequiresServiceDAO.save( diseaseRequiresService );
			}
		}
	

	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.admin.AdminManager#getDisease(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<DiseaseBM> getDisease(String diseaseName, String diseaseDesc,
			String diseaseGroup, String serviceCode) {
		
		List<Disease> diseaseList = diseaseDAO.getDisease(diseaseName, diseaseDesc,
														diseaseGroup, serviceCode);
		
		List<DiseaseBM> diseaseBMList = new ArrayList<DiseaseBM>(0);
		
		if( diseaseList != null && !diseaseList.isEmpty() ){
			for( Disease disease : diseaseList ){
				DiseaseBM diseaseBM = new DiseaseBM();
				diseaseBM.setDiseaseName( disease.getDiseaseName() );
				diseaseBM.setDiseaseDesc( disease.getDescription() );
				diseaseBM.setDiseaseGroup( disease.getDiseaseGroup());
				diseaseBM.setCreatedBy( disease.getCreatedBy() );
				diseaseBM.setCreateDtm( disease.getCreatedDtm() );
				
				List< DiseaseRequiresService> diseaseRequiresServiceList = diseaseRequiresServiceDAO.findByProperty( "disease.diseaseName", disease.getDiseaseName() );
				List< DiseaseRequireServiceBM > diseaseRequireServiceBMList = new ArrayList<DiseaseRequireServiceBM>(0);
				if( diseaseRequiresServiceList != null && !diseaseRequiresServiceList.isEmpty() ){
					for( DiseaseRequiresService requiresService : diseaseRequiresServiceList ){
						DiseaseRequireServiceBM requireServiceBM = new DiseaseRequireServiceBM();
						requireServiceBM.setDiseaseName( disease.getDiseaseName() );
						requireServiceBM.setDeseaseDesc( disease.getDescription() );
						
						Service service = dataModelManager.getServiceByCode( requiresService.getId().getServiceCode());
						
						if( service != null ){
							requireServiceBM.setServiceCode( service.getServiceCode() );
							requireServiceBM.setServiceName( service.getServiceName() );
						}
						requireServiceBM.setCreatedBy( requiresService.getCreatedBy() );
						requireServiceBM.setCreateDtm( requiresService.getCreatedDtm() );
						diseaseRequireServiceBMList.add( requireServiceBM );
					}
				}
				diseaseBM.setDiseaseRequireServiceBMList(diseaseRequireServiceBMList);
				diseaseBMList.add( diseaseBM );
			}
		}
		                                                 
		return diseaseBMList;
		
	
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.admin.AdminManager#modifyDisease(in.wtc.hcis.bm.ip.DiseaseBM)
	 */
	public void modifyDisease(DiseaseBM diseaseBM) {

		Disease existingDisease = this.getDisease( diseaseBM.getDiseaseName() );
		
		existingDisease.setDescription( diseaseBM.getDiseaseDesc() );
		existingDisease.setDiseaseGroup( diseaseBM.getDiseaseGroup() );
		
		diseaseDAO.attachDirty( existingDisease );
		
		List<DiseaseRequiresService> diseaseRequiresServiceList = diseaseRequiresServiceDAO.findByProperty("id.diseaseName", diseaseBM.getDiseaseName());
		if( diseaseRequiresServiceList != null && !diseaseRequiresServiceList.isEmpty() ){
			for( DiseaseRequiresService diseaseRequiresService : diseaseRequiresServiceList ){
				diseaseRequiresServiceDAO.delete( diseaseRequiresService );
			}
		}
	
		List<DiseaseRequireServiceBM> diseaseRequireServiceBMList = diseaseBM.getDiseaseRequireServiceBMList();
		
		if( diseaseRequireServiceBMList != null && !diseaseRequireServiceBMList.isEmpty() ){
			for( DiseaseRequireServiceBM diseaseRequireServiceBM : diseaseRequireServiceBMList ){
				DiseaseRequiresService diseaseRequiresService = new DiseaseRequiresService();
				
				DiseaseRequiresServiceId id = new DiseaseRequiresServiceId();
				id.setDiseaseName( diseaseBM.getDiseaseName() );
				id.setServiceCode( diseaseRequireServiceBM.getServiceCode() );

				diseaseRequiresService.setId(id);
				diseaseRequiresService.setDisease( existingDisease );
				diseaseRequiresService.setCreatedBy( diseaseBM.getCreatedBy() );
				diseaseRequiresService.setCreatedDtm( new Date() );
				diseaseRequiresServiceDAO.save( diseaseRequiresService );
			}
		}

	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.admin.AdminManager#removeDiseases(java.util.List)
	 */
	public boolean removeDiseases(List<String> diseaseNameList) {
		if( diseaseNameList != null && !diseaseNameList.isEmpty() ){
			for( String diseaseName : diseaseNameList ){
				this.removeDisease(diseaseName);
			}
			return true;
		}
		return false;
	}
	
	private void removeDisease( String diseaseName ){
		
		try {
			Disease disease = this.getDisease(diseaseName);
			
			List<DiseaseRequiresService> diseaseRequiresServiceList = diseaseRequiresServiceDAO.findByProperty("id.diseaseName", diseaseName );
			if( diseaseRequiresServiceList != null && !diseaseRequiresServiceList.isEmpty() ){
				for( DiseaseRequiresService diseaseRequiresService : diseaseRequiresServiceList ){
					diseaseRequiresServiceDAO.delete( diseaseRequiresService );
				}
			}
			diseaseDAO.delete( disease );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public Disease getDisease(String diseaseName) throws HCISException {
		try{
			Disease disease = diseaseDAO.findById(diseaseName);
			
			if(disease == null ){
					throw new Exception("Diseas with Name : " + diseaseName + ", does not exist. ");
				}
				
				return disease;
				
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.READ_DISEASE_FAILED);
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );	
			}	
	}

	public void setDiseaseDAO(DiseaseDAOExtn diseaseDAO) {
		this.diseaseDAO = diseaseDAO;
	}

	public void setDiseaseRequiresServiceDAO(
			DiseaseRequiresServiceDAO diseaseRequiresServiceDAO) {
		this.diseaseRequiresServiceDAO = diseaseRequiresServiceDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}
}
