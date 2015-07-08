package in.wtc.hcis.bo.document;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.DocPatientVital;
import com.wtc.hcis.da.DocPatientVitalDAO;
import com.wtc.hcis.da.DocPatientVitalFieldsValue;
import com.wtc.hcis.da.DocPatientVitalFieldsValueDAO;
import com.wtc.hcis.da.DocPatientVitalFieldsValueId;
import com.wtc.hcis.da.DocVitalField;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.extn.DocPatientVitalDAOExtn;
import com.wtc.hcis.da.extn.DocPatientVitalFieldsValueDAOExtn;
import com.wtc.hcis.da.extn.DocVitalFieldDAOExtn;

import in.wtc.hcis.bm.base.PatientVitalBM;
import in.wtc.hcis.bm.base.VitalFieldBM;
import in.wtc.hcis.bo.common.DataModelConverter;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.common.WtcUtils;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

public class VitalManagerImpl implements VitalManager {
	
	DocVitalFieldDAOExtn vitalFieldDAO;
	DataModelConverter dataModelConverter;
	DataModelManager dataModelManager;
	DocPatientVitalDAOExtn patientVitalDAO;
	DocPatientVitalFieldsValueDAOExtn patientVitalFieldsValueDAO;

	/**
	 * 
	 * @return list of VitalFieldBM
	 */
	
	public List<VitalFieldBM> getVitalFields() throws HCISException {
		try{
			List<VitalFieldBM> vitalFieldBMList = null;
			List<DocVitalField> vitalFieldList = vitalFieldDAO.getVitalFields();
			if( vitalFieldList != null && vitalFieldList.size() > 0){
				vitalFieldBMList = new ArrayList<VitalFieldBM>(0);
				for (DocVitalField vitalField : vitalFieldList) {
					VitalFieldBM vitalFieldBM = dataModelConverter.convertVitalFieldDM2BM(vitalField);
					vitalFieldBMList.add(vitalFieldBM);
				}
			}
			
			return vitalFieldBMList;
			
		}
		catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException(fault,e);
		}
	}
	
	/**
	 * This method will add patient vital.
	 * @param patientVitalBM
	 * @throws HCISException
	 */
	
	public void addVital(PatientVitalBM patientVitalBM)throws HCISException{
		try{
			Patient patient = dataModelManager.getPatient(patientVitalBM.getPatientId());
			
			DocPatientVital patientVital = dataModelConverter.convertPatientVitalBM2DM(patientVitalBM, null);
			
			patientVital.setPatientId(patientVitalBM.getPatientId());
			patientVital.setCreatedBy(patientVitalBM.getUserId());
			patientVital.setCreatedDtm(new Date());
			
			patientVitalDAO.save(patientVital);
			
			this.addPatientVitalFieldValues(patientVitalBM.getVitalFieldBMList(), patientVital);
			
			
		}
		catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException(fault,e);
		}
	}
	
	/**
	 * 
	 */
	
	
	public List<VitalFieldBM> getPatientVitalDetails( String referenceNumber , String referenceType , 
			Date fromDate, Date toDate )throws HCISException{
		try{
			DateUtils.updateDateForSearch(toDate);
			List<VitalFieldBM> vitalFieldBMList = null;
			List<DocPatientVital> patientVitalList = patientVitalDAO.getPatientVitalList(referenceNumber, referenceType);			

			if( patientVitalList != null && patientVitalList.size() > 0){
				vitalFieldBMList = new ArrayList<VitalFieldBM>(0);
				for (DocPatientVital patientVital : patientVitalList) {
					Integer patientVitalId = patientVital.getPatientVitalId();
					List<DocPatientVitalFieldsValue> patientVitalFieldsValueList = patientVitalFieldsValueDAO.getPatientVitalFieldsValueList(patientVitalId, fromDate, toDate);
					
					if( patientVitalFieldsValueList != null && patientVitalFieldsValueList.size() > 0){
						for (DocPatientVitalFieldsValue docPatientVitalFieldsValue : patientVitalFieldsValueList) {
							
							DocVitalField vitalField = dataModelManager.getVitalField(docPatientVitalFieldsValue.getDocVitalField().getCode());
							VitalFieldBM vitalFieldBM = dataModelConverter.convertVitalFieldDM2BM(vitalField);
							vitalFieldBM.setValue(docPatientVitalFieldsValue.getValue());
							vitalFieldBM.setForTime(docPatientVitalFieldsValue.getId().getForTime());
							vitalFieldBMList.add(vitalFieldBM);
						}
					}
				}
			}
			
			
			return vitalFieldBMList;
		}
		catch (Exception e) {
			Fault fault = new Fault();
			throw new HCISException(fault,e);
		}
	}
	
	
	
	
	/**
	 *  This method will add patient vital field value.
	 * @param vitalFieldBMList
	 * @param patientVital
	 */
	private void addPatientVitalFieldValues( List<VitalFieldBM> vitalFieldBMList, DocPatientVital patientVital ){
		if( vitalFieldBMList != null && vitalFieldBMList.size() > 0){
			for (VitalFieldBM vitalFieldBM : vitalFieldBMList) {
				DocPatientVitalFieldsValue patientVitalFieldsValue = new DocPatientVitalFieldsValue();
				DocVitalField vitalField = dataModelManager.getVitalField(vitalFieldBM.getCode());
				
				patientVitalFieldsValue.setDocVitalField(vitalField);
				patientVitalFieldsValue.setDocPatientVital(patientVital);
				patientVitalFieldsValue.setValue(vitalFieldBM.getValue());
				patientVitalFieldsValue.setCreatedBy(patientVital.getCreatedBy());
				patientVitalFieldsValue.setCreatedDtm(new Date());
//				Date date = new Date();
				
				DocPatientVitalFieldsValueId id = new DocPatientVitalFieldsValueId();
				id.setForTime(vitalFieldBM.getForTime());
				id.setPatientVitalId(patientVital.getPatientVitalId());
				id.setVitalFieldCode(vitalField.getCode());
				
				patientVitalFieldsValue.setId(id);
				
				patientVitalFieldsValueDAO.save(patientVitalFieldsValue);
			}
		}
	}
	
	
	public Date getDateWithTime( String time){
		Date date = new Date();
		String[] temp = time.split(":");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, Integer.parseInt(temp[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(temp[1].split(" ")[0]));
	
		return calendar.getTime();
	}
	

	public void setVitalFieldDAO(DocVitalFieldDAOExtn vitalFieldDAO) {
		this.vitalFieldDAO = vitalFieldDAO;
	}

	public void setDataModelConverter(DataModelConverter dataModelConverter) {
		this.dataModelConverter = dataModelConverter;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setPatientVitalDAO(DocPatientVitalDAOExtn patientVitalDAO) {
		this.patientVitalDAO = patientVitalDAO;
	}

	public void setPatientVitalFieldsValueDAO(
			DocPatientVitalFieldsValueDAOExtn patientVitalFieldsValueDAO) {
		this.patientVitalFieldsValueDAO = patientVitalFieldsValueDAO;
	}

}
