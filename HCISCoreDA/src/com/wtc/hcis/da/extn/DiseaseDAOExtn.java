/**
 * 
 */
package com.wtc.hcis.da.extn;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Disease;
import com.wtc.hcis.da.DiseaseDAO;

/**
 * @author Bhavesh
 *
 */
public class DiseaseDAOExtn extends DiseaseDAO {

	public List<Disease> getDisease( String diseaseName, String diseaseDesc,
			   				  String diseaseGroup,	String serviceCode){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( Disease.class );
		
		if( diseaseName != null && !diseaseName.isEmpty() ){
			
			criteria.add( Restrictions.eq("diseaseName", diseaseName ));
		}
		if( diseaseDesc != null && !diseaseDesc.isEmpty() ){
					
			criteria.add( Restrictions.ilike( "description", "%" + diseaseDesc + "%"));
		}
		if( diseaseGroup != null && !diseaseGroup.isEmpty() ){
			
			criteria.add( Restrictions.eq("diseaseGroup", diseaseGroup));
		}
		if( serviceCode != null && !serviceCode.isEmpty() ){
			
			criteria.createAlias("diseaseRequiresServices", "diseaseRequiresServices" );
			criteria.add( Restrictions.eq("diseaseRequiresServices.id.diseaseName", serviceCode));
		}
		List<Disease> diseaseList = getHibernateTemplate().findByCriteria(criteria);
		
		return diseaseList;
	}
}
