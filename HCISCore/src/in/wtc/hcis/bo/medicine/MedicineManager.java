/**
 * 
 */
package in.wtc.hcis.bo.medicine;

import java.util.List;

import in.wtc.hcis.bm.base.MedicineBM;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Rohit
 *
 */
public interface MedicineManager 
{
	void addMedicine( MedicineBM medicineBM ) throws HCISException;
	
	List<MedicineBM> getMedicine( String medicineCode,
									String medicineName,
									String brandCode,
									String strength,
									String medicineTypeCode,
									String maximumDosage,
									Boolean active 
								)throws HCISException;
	
	MedicineBM modifyMedicine( MedicineBM modifiedMedicineBM ) throws HCISException;
	
	void removeMedicine( List<String> medicineCodeList ) throws HCISException;
	boolean isMedicineExist( String medicineCode );
}
