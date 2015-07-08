/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.EntityContactCode;
import com.wtc.hcis.da.EntityContactCodeDAO;


/**
 * @author Rohit
 *
 */
public class EntityContactCodeDAOExtn extends EntityContactCodeDAO 
{
	private static final Log log = LogFactory.getLog( EntityContactCodeDAOExtn.class );
	
	public ArrayList<EntityContactCode> getEntityContactList( Integer personelId )
	{
		ArrayList<EntityContactCode> entityContactCodeList = null;
		DetachedCriteria entityContactCodeCriteria = DetachedCriteria.forClass( EntityContactCode.class );
		entityContactCodeCriteria.add( Restrictions.eq( "id.personelId", personelId ) );
		entityContactCodeList = (ArrayList<EntityContactCode>)getHibernateTemplate().findByCriteria( entityContactCodeCriteria );
		if( entityContactCodeList != null && !entityContactCodeList.isEmpty() ) {
			return entityContactCodeList;
		}
		else {
			return null;
		}
	}
	public List<EntityContactCode> getEntityContactListWithForEntity( Integer personelId, String entityTypeInd )
	{
		List<EntityContactCode> entityContactCodeList = null;
		DetachedCriteria entityContactCodeForEntityCriteria = DetachedCriteria.forClass( EntityContactCode.class );
		entityContactCodeForEntityCriteria.add( Restrictions.eq( "id.personelId", personelId ) );
		entityContactCodeForEntityCriteria.add( Restrictions.eq( "id.forEntityCode", entityTypeInd ) );
		entityContactCodeList = (ArrayList<EntityContactCode>)getHibernateTemplate().findByCriteria( entityContactCodeForEntityCriteria );
		if( entityContactCodeList != null && !entityContactCodeList.isEmpty() ) {
			return entityContactCodeList;
		}
		else {
			return null;
		}
	}
}
