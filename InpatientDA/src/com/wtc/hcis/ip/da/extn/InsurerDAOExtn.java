/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessResourceFailureException;

import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.InsurerDAO;
import com.wtc.hcis.ip.da.extn.util.DAUtil;

/**
 * @author Bhavesh
 *
 */
public class InsurerDAOExtn extends InsurerDAO {
	
	private static final Log log = LogFactory.getLog( InsurerDAOExtn.class );
	
	//Fields to sort based on UI request 
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	private Map<String,String> UIDBFieldMapForOrder = new HashMap<String, String>();
	
	public List<Insurer>  getInsurer(  String insurerName,
									   String insurerDescription,
									   String insurancePlanCd, 
									   String sponsorName,
									   String orderByClause, String sortDir) throws Exception{
		try {
			VelocityContext context = new VelocityContext();
			context.put("Util", new DAUtil());
			context.put("insurerName", insurerName);
			context.put("insurerDescription", insurerDescription);
			context.put("insurancePlanCd", insurancePlanCd);
			context.put("sponsorName", sponsorName);
			
			if (orderByClause != null && orderByClause.length() > 0) {
				if (sortDir.equalsIgnoreCase(ClaimSponsorDAOExtn.SORT_DIRECTION_ASC))
					context.put("orderBy", UIDBFieldMapForOrder.get(orderByClause) + " asc");
				else
					context.put("orderBy", UIDBFieldMapForOrder.get(orderByClause) + " desc");
			}
			String hqlQuery = DAUtil.getHqlQuery("insurerHQLGen.vm", context);
			
			Query insurerSearched = getSession().createQuery(hqlQuery);
			
			List resultList = insurerSearched.list();
			
			List newList = new ArrayList();
			List insurerNameList = new ArrayList();
			Iterator<Object> iterator = resultList.iterator();
			
			while (iterator.hasNext()) {
				Object[] objArray = (Object[]) iterator.next();
				Insurer insurer = (Insurer) objArray[0];
				if (newList.size() == 0) {
					newList.add(insurer);
					insurerNameList.add(insurer.getInsurerName());
				} else {
					if (!insurerNameList.contains(insurer.getInsurerName())) {
						newList.add(insurer);
						insurerNameList.add(insurer.getInsurerName());
					}
				}
			}
			return newList;
		} catch (Exception ex) {
			log.error(" Failed to get Insurers "  + ex.getMessage() );
			throw ex;
		}
	}

	public List<Insurer>  getInsurers(  String insurerName,
									   String insurerDescription,
									   String insurancePlanCd, 
									   String sponsorName,
									   String orderByClause, String sortDir) throws Exception{
		DetachedCriteria criteria = DetachedCriteria.forClass( Insurer.class );
		
		if( insurerName != null && !insurerName.isEmpty() ){
			criteria.add( Restrictions.ilike("insurerName", "%" + insurerName + "%"));
		}
		if( insurerDescription != null && !insurerDescription.isEmpty() ){
			criteria.add( Restrictions.ilike("insurerDesc", "%" + insurerDescription + "%"));
		}
		if( insurancePlanCd != null && !insurancePlanCd.isEmpty() ){
			criteria.add( Restrictions.eq("insurancePlanses.planCd", insurancePlanCd));
		}
		if( sponsorName != null && !sponsorName.isEmpty() ){
			criteria.createAlias("sponsorInsurerAssociations", "sponsorInsurerAssociations");
			criteria.add( Restrictions.eq("sponsorInsurerAssociations.id.sponsorName", sponsorName));
		}
		
		if (orderByClause != null && orderByClause.length() > 0) {

			if (sortDir.equals(ClaimSponsorDAOExtn.SORT_DIRECTION_ASC))

			if (sortDir.equals(SORT_DIRECTION_ASC))

				criteria.addOrder(Order.asc(UIDBFieldMapForOrder.get(orderByClause)));
			else
				criteria.addOrder(Order.desc(UIDBFieldMapForOrder.get(orderByClause)));
		}
		List<Insurer> insurerList = getHibernateTemplate().findByCriteria(criteria);
		
		return insurerList;
	}
	public void setUIDBFieldMapForOrder(Map<String, String> fieldMapForOrder) {
		UIDBFieldMapForOrder = fieldMapForOrder;
	}

}
