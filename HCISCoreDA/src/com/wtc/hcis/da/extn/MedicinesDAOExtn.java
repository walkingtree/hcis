/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Medicines;
import com.wtc.hcis.da.MedicinesDAO;

/**
 * @author Rohit
 *
 */
public class MedicinesDAOExtn extends MedicinesDAO 
{
	private static final Log log = LogFactory.getLog(MedicinesDAOExtn.class);
	
	public static final String MEDICINE_CODE = "medicineCode";
	public static final String BRAND_CODE = "brand.brandCode";
	public static final String MEDICINE_TYPE_CODE = "medicineType.medicineTypeCode";
	public static final String ACTIVE_YES = "Y";
	public static final String ACTIVE_NO = "N";
	
	
	public List<Medicines> getMedicine(	String medicineCode,
										String medicineName, 
										String brandCode, 
										String strength,
										String medicineTypeCode,
										String maximumDosage, 
										Boolean active)
	{
		try
		{
			List<Medicines> medicinesList = null;
			DetachedCriteria medicineCriteria = DetachedCriteria.forClass(Medicines.class);
			if( medicineCode != null && !medicineCode.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.ilike( MedicinesDAOExtn.MEDICINE_CODE, "%" + medicineCode + "%" ) );
			}
			if( medicineName != null && !medicineName.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.ilike( MedicinesDAOExtn.MEDICINE_NAME, "%" + medicineName + "%" ) );
			}
			if( brandCode != null && !brandCode.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.eq( MedicinesDAOExtn.BRAND_CODE, brandCode ) );
			}
			if( strength != null && !strength.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.ilike( MedicinesDAOExtn.STRENGTH, "%" + strength + "%" ) );
			}
			if( medicineTypeCode != null && !medicineTypeCode.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.eq( MedicinesDAOExtn.MEDICINE_TYPE_CODE, medicineTypeCode ) );
			}
			if( maximumDosage != null && !maximumDosage.equalsIgnoreCase("") ) {
				medicineCriteria.add( Restrictions.ilike( MedicinesDAOExtn.MAXIMUM_DOSAGE, "%" + maximumDosage + "%" ) );
			}
			if( active != null  ) {
				if( active.booleanValue() == true ){
					medicineCriteria.add( Restrictions.eq( MedicinesDAOExtn.ACTIVE, MedicinesDAOExtn.ACTIVE_YES) );
				}else{
					medicineCriteria.add( Restrictions.eq( MedicinesDAOExtn.ACTIVE, MedicinesDAOExtn.ACTIVE_NO) );
				}
			}
			
			
			medicinesList = getHibernateTemplate().findByCriteria( medicineCriteria );
			return medicinesList;
		}catch (RuntimeException ex ) {
			log.error("Failed to retrieve doctors "  + ex.getMessage() );
			throw ex;
		}
	}

}
