/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabTechniqueReagent;
import com.wtc.hcis.da.LabTechniqueReagentDAO;

/**
 * @author Bhavesh
 *
 */
public class LabTechniqueReagentDAOExtn extends LabTechniqueReagentDAO {

	
	public static final String 	LAB_TECHNIQUE_REAGENT_ID = "ID";
	public static final String 	LAB_TEST_CODE = "labTests.testCode";
	
	public List<LabTechniqueReagent> getLabTechniqueReagent(Integer techReagentId,
													 String name, String isTechnique, 
													 String testCode) {

		DetachedCriteria criteria = DetachedCriteria.forClass( LabTechniqueReagent.class );
		
		if( techReagentId != null ){
			
			criteria.add( Restrictions.eq(LAB_TECHNIQUE_REAGENT_ID, techReagentId));
		}
		if( name!= null && !name.isEmpty() ){
			
			criteria.add( Restrictions.ilike(LabTechniqueReagentDAOExtn.NAME, "%" + name + "%"));
		}
		if (isTechnique != null && !isTechnique.isEmpty()) {
			
			criteria.add( Restrictions.eq(LabTechniqueReagentDAOExtn.IS_TECHNIQUE, isTechnique));
			
		}
		if (testCode != null && !testCode.isEmpty()) {

			criteria.createAlias("labTests", "labTests");
			
			criteria.add( Restrictions.eq(LabTechniqueReagentDAOExtn.LAB_TEST_CODE, testCode));
		}
		
		List labTechniqueList =  getHibernateTemplate().findByCriteria(criteria);
		
		return labTechniqueList;
	}
}
