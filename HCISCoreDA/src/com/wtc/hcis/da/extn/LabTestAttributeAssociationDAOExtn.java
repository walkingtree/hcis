package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTestAttributeAssociation;
import com.wtc.hcis.da.LabTestAttributeAssociationDAO;

public class LabTestAttributeAssociationDAOExtn extends
		LabTestAttributeAssociationDAO {
	
	public static final String TEST_CODE = "id.testCode";
	public static final String ATTRIBUTE_CODE = "id.attributeCode";
	public static final String ATTRIBUTE_VALUE = "attrValues.attributeValue";
	public static final String PATIENT_TEST_ID = "attrValues.id.patientTestId";
	
	public LabTestAttributeAssociation getTestAttributeAsso( String testCode ,
			String attributeCode ){
		
		LabTestAttributeAssociation testAttributeAssociation = new LabTestAttributeAssociation();
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LabTestAttributeAssociation.class);
		
		if( testCode != null && !testCode.isEmpty()){
			criteria.add(Restrictions.eq(LabTestAttributeAssociationDAOExtn.TEST_CODE, testCode));
		}
		
		if( attributeCode != null && !attributeCode.isEmpty()){
			criteria.add(Restrictions.eq(LabTestAttributeAssociationDAOExtn.ATTRIBUTE_CODE, attributeCode));
		}
		
		List<LabTestAttributeAssociation> testAttributeAssoList = getHibernateTemplate().findByCriteria(criteria);
		
		if( testAttributeAssoList != null && testAttributeAssoList.size() > 0){
			testAttributeAssociation = testAttributeAssoList.get(0);
		}
		
		return testAttributeAssociation;
		
		
	}
	
	
	public List<LabTestAttributeAssociation> getTestAttributeAssoList( String testCode , String patientTestId){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LabTestAttributeAssociation.class);
		criteria.createAlias("labTestAttribute", "labTestAttribute");
		criteria.createAlias("labTestAttribute.labPatientTestAttributeValues", "attrValues");
		
		if( testCode != null && !testCode.isEmpty()){
			
		
			criteria.add(Restrictions.eq(LabTestAttributeAssociationDAOExtn.TEST_CODE, testCode))
					.add(Restrictions.eq(LabTestAttributeAssociationDAOExtn.IS_MANDATORY, "Y"))
					.add(Restrictions.or(Restrictions.eq(LabTestAttributeAssociationDAOExtn.ATTRIBUTE_VALUE,null),
						Restrictions.eq(LabTestAttributeAssociationDAOExtn.ATTRIBUTE_VALUE,"")));
		
		}
		
		if( patientTestId != null && !patientTestId.isEmpty()){
			criteria.add(Restrictions.eq(LabTestAttributeAssociationDAOExtn.PATIENT_TEST_ID, patientTestId));
		}
			
		
		List<LabTestAttributeAssociation> testAttributeAssoList = getHibernateTemplate().findByCriteria(criteria);
		
		return testAttributeAssoList;
		
	}

}
