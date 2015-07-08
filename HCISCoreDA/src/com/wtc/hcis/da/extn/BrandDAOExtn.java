/**
 * 
 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Brand;
import com.wtc.hcis.da.BrandDAO;

/**
 * @author Rohit
 *
 */
public class BrandDAOExtn extends BrandDAO 
{
	private static final Log log = LogFactory.getLog(BrandDAOExtn.class);
	public static final String BRAND_CODE = "brandCode";
	
	public List<Brand> getBrand( String brandCode, 
								String description,
								Boolean active)
	{
		try
		{
			List<Brand> brandList = null;
			DetachedCriteria brandCriteria = DetachedCriteria.forClass(Brand.class);
			if( brandCode != null && !brandCode.equalsIgnoreCase("") ) {
				brandCriteria.add( Restrictions.ilike( BrandDAOExtn.BRAND_CODE, "%" + brandCode + "%" ) );
			}
			if( description != null && !description.equalsIgnoreCase("") ) {
				brandCriteria.add( Restrictions.ilike( BrandDAOExtn.DESCRIPTION, "%" + description + "%" ) );
			}
			if( active != null ) {
				brandCriteria.add( Restrictions.eq( BrandDAOExtn.ACTIVE, new Short(active.equals(true)?"1":"0") ) );
			}
			brandList = getHibernateTemplate().findByCriteria( brandCriteria );
			return brandList;
		}catch (RuntimeException ex ) {
			log.error("Failed to retrieve doctors "  + ex.getMessage() );
			throw ex;
		}
	}
}
