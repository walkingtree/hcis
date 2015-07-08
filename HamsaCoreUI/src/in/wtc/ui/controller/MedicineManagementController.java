package in.wtc.ui.controller;

import java.util.ArrayList;
import java.util.List;


import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.MedicineBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.medicine.MedicineManager;
import in.wtc.ui.utils.Converters;

public class MedicineManagementController {

	private MedicineManager medicineManager;
	
	public void addMedicine(MedicineBM medicineBM) {
		
			CodeAndDescription brand = medicineBM.getBrand();
			medicineBM.setBrand(Converters.getEntityValue(brand));
			medicineBM.setMedicineType(Converters.getEntityValue(medicineBM.getMedicineType()));
			medicineManager.addMedicine(medicineBM);
		
	}
	
	public ListRange getMedicines(String medicineCode, String medicineName, String brandCode, 
									String strength, String medicineTypeCode, String maximumDosage,Boolean active, 
									int start, int count, String orderBy) {
		ListRange listRange = new ListRange();
		
		List<MedicineBM> medicineList = medicineManager.getMedicine(medicineCode, medicineName, brandCode, strength, medicineTypeCode,maximumDosage, active);
		List<MedicineBM> pageSizeData = new ArrayList<MedicineBM>();
		if(medicineList!=null && medicineList.size()>0) {
			int index = 0;
			while( (start+index < start + count) && (medicineList.size() > start+index) ){
					pageSizeData.add(medicineList.get(start+index));
					index++;
			}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(medicineList.size());
		}else{
			  Object[] medicineSummaryObj = new Object[0] ;
			  listRange.setData(medicineSummaryObj);
			  listRange.setTotalSize(medicineSummaryObj.length);
		  }

		return listRange;
	}
	
	public void modifyMedicine(MedicineBM medicineBM) {
			medicineBM.setBrand(Converters.getEntityValue(medicineBM.getBrand()));
			medicineBM.setMedicineType(Converters.getEntityValue(medicineBM.getMedicineType()));
			medicineManager.modifyMedicine(medicineBM);
	}
	
	public void removeMedicines(List<String> medicineCodeList) {
			medicineManager.removeMedicine(medicineCodeList);
	}
	
	public MedicineBM getMedicineDetails(String medicineCode) {
		List<MedicineBM> medicineList = medicineManager.getMedicine(medicineCode, null, null, null, null,null, null);
		
		if(medicineList!= null && medicineList.size()>0) {
			return medicineList.get(0);
			
		}else {
			return null;
		}
	}
	public boolean isMedicineExist(String medicineCode){
		return medicineManager.isMedicineExist(medicineCode);
	}

	
	public void setMedicineManager(MedicineManager medicineManager) {
		this.medicineManager = medicineManager;
	}
	
}
