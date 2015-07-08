/**
 * 
 */
package in.wtc.hcis.bo.medicine;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.MedicineBM;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.List;

import com.wtc.hcis.da.Brand;
import com.wtc.hcis.da.MedicineType;
import com.wtc.hcis.da.MedicineTypeDAO;
import com.wtc.hcis.da.Medicines;
import com.wtc.hcis.da.extn.BrandDAOExtn;
import com.wtc.hcis.da.extn.MedicinesDAOExtn;

/**
 * @author Rohit
 *
 */
public class MedicineManagerImpl implements MedicineManager 
{
	MedicinesDAOExtn medicinesDAOExtn;
	BrandDAOExtn brandDAO;
	MedicineTypeDAO medicineTypeDAO;
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.medicine.MedicineManager#addMedicine(in.wtc.hcis.bm.base.MedicineBM)
	 */
	public void addMedicine( MedicineBM medicineBM ) throws HCISException 
	{
		try
		{
			Medicines medicinesDM = convertMedicineBM2DM( medicineBM, null );
			
			medicinesDAOExtn.save( medicinesDM );
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.MEDICINE_ADD_FAILED );
			HCISException hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}

	
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.medicine.MedicineManager#getMedicine(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	public List<MedicineBM> getMedicine(String medicineCode,
										String medicineName, 
										String brandCode, 
										String strength,
										String medicineTypeCode, 
										String maximumDosage,
										Boolean active) 
	{
		List<Medicines> medicinesList = medicinesDAOExtn.getMedicine(	medicineCode, 
																		medicineName, 
																		brandCode, 
																		strength, 
																		medicineTypeCode, 
																		maximumDosage,
																		active );
		List<MedicineBM> medicineBMList = new ArrayList<MedicineBM>();
		if( medicinesList != null ) {
			for( Medicines tmpMedicinesDM : medicinesList ) {
				MedicineBM tmpMedicineBM = convertMedicinesDM2BM( tmpMedicinesDM );
//				tmpMedicineBM.setMedicineCode( tmpMedicinesDM.getMedicineCode() );
//				tmpMedicineBM.setMedicineName( tmpMedicinesDM.getMedicineName() );
//				if( tmpMedicinesDM.getBrand() != null ) {
//					CodeAndDescription brand = new CodeAndDescription();
//					brand.setCode( tmpMedicinesDM.getBrand().getBrandCode() );
//					brand.setDescription( tmpMedicinesDM.getBrand().getDescription() );
//					tmpMedicineBM.setBrand( brand );
//				}
//				if( tmpMedicinesDM.getMedicineType() != null ) {
//					CodeAndDescription medicineType = new CodeAndDescription();
//					medicineType.setCode( tmpMedicinesDM.getMedicineType().getMedicineTypeCode() );
//					medicineType.setDescription( tmpMedicinesDM.getMedicineType().getDescription() );
//					tmpMedicineBM.setMedicineType( medicineType );
//				}
				medicineBMList.add( tmpMedicineBM );
			}
		}
		return medicineBMList;
	}
	


	public MedicineBM modifyMedicine( MedicineBM modifiedMedicineBM ) throws HCISException 
	{
		try
		{
			Medicines medicines = this.getMedicine( modifiedMedicineBM.getMedicineCode() );
			Medicines medicinesDM = convertMedicineBM2DM( modifiedMedicineBM, medicines );
			
			medicinesDAOExtn.attachDirty( medicinesDM );

			MedicineBM medicineBM = convertMedicinesDM2BM( medicinesDM );
			return medicineBM;
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.MEDICINE_MODIFY_FAILED );
			HCISException hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}



	public void removeMedicine( List<String> medicineCodeList ) throws HCISException 
	{
		try
		{
			for( String tmpId : medicineCodeList ) {
				Medicines medicinesDM = medicinesDAOExtn.findById( tmpId );
				medicinesDM.setActive("N" );
				medicinesDAOExtn.attachDirty( medicinesDM );
			}
		}catch( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.MEDICINE_REMOVE_FAILED );
			HCISException hcisException = new HCISException( fault, exception );
			throw hcisException;
		}
	}


	
	
	private Medicines convertMedicineBM2DM( MedicineBM medicineBM, Medicines medicinesDM )
	{
		if( medicinesDM == null ){
			medicinesDM = new Medicines();
		}
		if( medicineBM != null ) {
			medicinesDM.setMedicineCode( medicineBM.getMedicineCode() );
			medicinesDM.setMedicineName( medicineBM.getMedicineName() );
			if( medicineBM.getBrand()!=null && medicineBM.getBrand().getCode()!= null &&
					!medicineBM.getBrand().getCode().isEmpty()) {
				Brand brand = this.getBrand( medicineBM.getBrand().getCode());
				medicinesDM.setBrand( brand );
			}
			medicinesDM.setStrength( medicineBM.getStrength() );
			if( medicineBM.getMedicineType()!=null && medicineBM.getMedicineType().getCode() !=null &&
					!medicineBM.getMedicineType().getCode().isEmpty() ) {
				MedicineType medicineType = this.getMedicineType( medicineBM.getMedicineType().getCode() );
				medicinesDM.setMedicineType( medicineType );
			}
			medicinesDM.setMaximumDosage( medicineBM.getMaximumDosage() );
			medicinesDM.setActive( medicineBM.getActive()?"Y":"N" );
		}
		return medicinesDM;
	}
	
	
	private MedicineBM convertMedicinesDM2BM( Medicines medicinesDM )
	{
		MedicineBM medicineBM = new MedicineBM();
		
		if( medicinesDM != null ) {
			medicineBM.setMedicineCode( medicinesDM.getMedicineCode() );
			medicineBM.setMedicineName( medicinesDM.getMedicineName() );
			medicineBM.setStrength( medicinesDM.getStrength() );
			medicineBM.setMaximumDosage( medicinesDM.getMaximumDosage() );
			medicineBM.setActive( medicinesDM.getActive().equals("Y")? true : false );
			if( medicinesDM.getBrand() != null ) {
				CodeAndDescription brand = new CodeAndDescription();
				brand.setCode( medicinesDM.getBrand().getBrandCode() );
				brand.setDescription( medicinesDM.getBrand().getDescription() );
				medicineBM.setBrand( brand );
			}
			if( medicinesDM.getMedicineType() != null ) {
				CodeAndDescription medicineType = new CodeAndDescription();
				medicineType.setCode( medicinesDM.getMedicineType().getMedicineTypeCode() );
				medicineType.setDescription( medicinesDM.getMedicineType().getDescription() );
				medicineBM.setMedicineType( medicineType );
			}
		}
		return medicineBM;
	}

	private Brand getBrand( String brandCode ) throws HCISException{
		try {
			
			Brand brand = brandDAO.findById( brandCode );
			
			if( brand == null){
				throw new Exception("Brand with name "+ brandCode +" does not exist");
			}
			return brand;
		} catch (Exception exception ) {
			Fault fault = new Fault(ApplicationErrors.BRAND_READ_FAILED);
			throw new HCISException(fault, exception);
		}
		
	}
	
	private MedicineType getMedicineType( String medicineTypeCode ) throws HCISException{
		try {
			MedicineType medicineType = medicineTypeDAO.findById( medicineTypeCode );
			
			if( medicineType == null){
				throw new Exception("Medicine type with name "+ medicineTypeCode + " does not exist");
			}
			return medicineType;
		} catch ( Exception exception ) {
			Fault fault = new Fault(ApplicationErrors.MEDICINE_TYPE_READ_FAILED);
			throw new HCISException( fault, exception);
		}
	}
	
	private Medicines getMedicine( String medicineCode ) throws HCISException{
		try {
			Medicines medicines = medicinesDAOExtn.findById( medicineCode );
			if( medicines == null ){
				throw new Exception( "Medicine with name "+ medicineCode + " does not exist");
			}
			return medicines;
		} catch ( Exception exception ) {
			Fault fault = new Fault( ApplicationErrors.MEDICINE_READ_FAILED);
			throw new HCISException( fault, exception );
		}
	}
	public boolean isMedicineExist( String medicineCode ){
		
		if( medicineCode != null && !medicineCode.isEmpty() ){
			Medicines medicines = medicinesDAOExtn.findById( medicineCode );
			if( medicines != null ){
				return true;
			}
		}
		return false;
	}
	public void setMedicinesDAOExtn(MedicinesDAOExtn medicinesDAOExtn) {
		this.medicinesDAOExtn = medicinesDAOExtn;
	}



	public void setBrandDAO(BrandDAOExtn brandDAO) {
		this.brandDAO = brandDAO;
	}



	public void setMedicineTypeDAO(MedicineTypeDAO medicineTypeDAO) {
		this.medicineTypeDAO = medicineTypeDAO;
	}
}
