/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabTestAttribute;
import com.wtc.hcis.da.LabTestAttributeDAO;

/**
 * @author Bhavesh
 *
 */
public class LabTestAttributeDAOExtn extends LabTestAttributeDAO {

	
	public static final String TEST_CODE = "testAttrAsso.id.testCode";
	
	public List<LabTestAttribute> getLabTestAttributes(String labAttributeCode, String labAttributeName,String type,String testCode){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( LabTestAttribute.class );
		
		if( labAttributeCode!= null && !labAttributeCode.isEmpty() ){
			
			criteria.add( Restrictions.eq( "attributeCode", labAttributeCode));
		}
				
		if( labAttributeName!= null && !labAttributeName.isEmpty() ){
					
					criteria.add( Restrictions.ilike( LabTestAttributeDAOExtn.ATTRIBUTE_NAME, "%" + labAttributeName + "%"));
		}
		
		if( type!= null && !type.isEmpty() ){
			
			criteria.add( Restrictions.eq( LabTestAttributeDAOExtn.TYPE, type));
		}
		
		if( testCode!= null && !testCode.isEmpty() ){
			
			criteria.createAlias("labTestAttributeAssociations", "testAttrAsso");
			criteria.add( Restrictions.eq( LabTestAttributeDAOExtn.TEST_CODE, testCode));
		}

		return getHibernateTemplate().findByCriteria(criteria);
	}
}
