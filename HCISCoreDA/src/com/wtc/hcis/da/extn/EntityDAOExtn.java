/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;

import com.wtc.hcis.da.EntityDAO;
import com.wtc.hcis.da.Entity;

/**
 * @author Madhavi
 *
 */
public class EntityDAOExtn extends EntityDAO {


	private static final Log log = LogFactory.getLog( EntityDAOExtn.class );
	
	//Fields to sort based on UI request 
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	
	public List<Entity>  getEntities(  Integer entityId, String name,
			String genderCode, String typeCode, String stateCode,
			String contryCode, String city, String address, String isPermanent,
			int start, int count) throws Exception{
		
		DetachedCriteria criteria = DetachedCriteria.forClass( Entity.class );
		criteria.createAlias("contactDetails", "contactDetails");
		
		if( name != null && !name.isEmpty() ){
			criteria.add( Restrictions.ilike(EntityDAOExtn.NAME, name));
		}
		if( entityId != null ){
			criteria.add( Restrictions.eq("entityId",entityId ));
		}
		if( isPermanent != null && !isPermanent.isEmpty() ){
			criteria.add( Restrictions.eq(EntityDAOExtn.IS_PERMANENT, isPermanent));
		}
		
		if( genderCode != null && !genderCode.isEmpty() ){
			criteria.add( Restrictions.eq("gender.genderCode", genderCode));
		}
		
		if( typeCode != null && !typeCode.isEmpty() ){
			criteria.add( Restrictions.eq("type", typeCode));
		}
		
		if( stateCode != null && !stateCode.isEmpty() ){
			criteria.add( Restrictions.eq("contactDetails.stateCode", stateCode));
		}
		
		if( contryCode != null && !contryCode.isEmpty() ){
			criteria.add( Restrictions.eq("contactDetails.contryCode", contryCode));
		}
		
		if( city != null && !city.isEmpty() ){
			criteria.add( Restrictions.ilike("contactDetails.city", city));
		}
		
		List<Entity> entityList = getHibernateTemplate().findByCriteria(criteria);
		
		return entityList;
	}
	
}



