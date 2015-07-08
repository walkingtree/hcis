package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabCollectionPoint;
import com.wtc.hcis.da.LabCollectionPointDAO;
import com.wtc.hcis.da.LabRequisitionOrder;

public class LabCollectionPointDAOExtn extends LabCollectionPointDAO {
	
	public static final String COLLECTION_POINT_ID = "collectionPointId";
	public static final String CITY = "contactDetails.city";
	public static final String ASSOCIATED_LAB_ID = "labCollectionPointLabAssociations.id.labId";
	

	public List<LabCollectionPoint> getCollectionPoint( String collectionPointName,
			 String collectionPointId,
			 String associatedLabId,
			 String areaCovered,
			 String city){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(LabCollectionPoint.class);
		
		if( collectionPointName != null && !collectionPointName.isEmpty()){
			criteria.add(Restrictions.ilike(LabCollectionPointDAOExtn.NAME, "%"+ collectionPointName +"%"));
		}
		
		if( collectionPointId != null && !collectionPointId.isEmpty()){
			criteria.add(Restrictions.eq(LabCollectionPointDAOExtn.COLLECTION_POINT_ID, collectionPointId));
		}
		
		if( associatedLabId != null && !associatedLabId.isEmpty()){
			criteria.createAlias("labCollectionPointLabAssociations", "labCollectionPointLabAssociations");
			criteria.add(Restrictions.eq(LabCollectionPointDAOExtn.ASSOCIATED_LAB_ID, associatedLabId));
		}
		
		if( areaCovered != null && !areaCovered.isEmpty()){
			criteria.add(Restrictions.ilike(LabCollectionPointDAOExtn.AREA_COVERED, "%"+ areaCovered + "%"));
		}
		
		if( city != null && !city.isEmpty()){
			criteria.createAlias("contactDetails", "contactDetails");			
			criteria.add(Restrictions.ilike(LabCollectionPointDAOExtn.CITY, "%" + city + "%"));
		}
		
		List<LabCollectionPoint> collectionPointList = getHibernateTemplate().findByCriteria(criteria);
		
		return collectionPointList;
		
	}
	
	
}
