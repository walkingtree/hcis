/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.ClaimSponsorDAO;
import com.wtc.hcis.ip.da.extn.util.DAUtil;

/**
 * @author Bhavesh
 *
 */
public class ClaimSponsorDAOExtn extends ClaimSponsorDAO {

	public static final String INSURER_NAME = "sponsorInsurerAssociations.id.insurerName";
	
	//Fields to sort based on UI request 
	public static final String SORT_DIRECTION_ASC = "ASC";
	public static final String SORT_DIRECTION_DESC = "DESC";
	private Map<String,String> UIDBFieldMapForOrder = new HashMap<String, String>();
 
	public	List<ClaimSponsor> getClaimSponsors( String sponsorName,
										 String sponsorTypeCd,
										 String sponsorDescription,
										 String creditClassCode, 
										 String accountNumber,
										 String insurerName,
										 String orderByClause, String sortDir){
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria sponsorCriteria = session.createCriteria( ClaimSponsor.class );
		sponsorCriteria.createAlias("sponsorType", "sponsorType" , sponsorCriteria.LEFT_JOIN)
				.createAlias("creditClass", "creditClass", sponsorCriteria.LEFT_JOIN);
		
		if( sponsorName != null && !sponsorName.isEmpty() ){
			sponsorCriteria.add(Restrictions.ilike("sponsorsName",  "%" + sponsorName + "%"));
		}
		
		if( sponsorTypeCd != null && !sponsorTypeCd.isEmpty() ){
			sponsorCriteria.add(Restrictions.eq("sponsorType.sponsorTypeCd", sponsorTypeCd));
		}
		
		if( sponsorDescription != null && !sponsorDescription.isEmpty() ){
			sponsorCriteria.add(Restrictions.eq("sponsorDesc", sponsorDescription));
		}
		
		if( creditClassCode != null && !creditClassCode.isEmpty() ){
			sponsorCriteria.add(Restrictions.eq("creditClass.creditClassCd", creditClassCode));
		}
		
		if( accountNumber != null && !accountNumber.isEmpty() ){
			sponsorCriteria.add(Restrictions.eq("accountNumber", accountNumber));
		}
		if( insurerName != null && !insurerName.isEmpty() ){
			sponsorCriteria.createAlias("sponsorInsurerAssociations", "sponsorInsurerAssociations");
			sponsorCriteria.add(Restrictions.eq( ClaimSponsorDAOExtn.INSURER_NAME, insurerName ));
		}
		
		if (orderByClause != null && orderByClause.length() > 0) {

			if (sortDir.equals(ClaimSponsorDAOExtn.SORT_DIRECTION_ASC))

			if (sortDir.equals(SORT_DIRECTION_ASC))

				sponsorCriteria.addOrder(Order.asc(UIDBFieldMapForOrder.get(orderByClause)));
			else
				sponsorCriteria.addOrder(Order.desc(UIDBFieldMapForOrder.get(orderByClause)));
		}
		
		
		List<ClaimSponsor> claimSponsorList = sponsorCriteria.list();
		return claimSponsorList;
	}
	
	public Integer getSponsorCount(){
		
		Integer sponsorCount = 0; 
		
		DetachedCriteria sponsorCriteria = DetachedCriteria.forClass( ClaimSponsor.class );
		sponsorCriteria.setProjection( Projections.rowCount() );
		
		List result = getHibernateTemplate().findByCriteria( sponsorCriteria );
		
		if( result != null && !result.isEmpty() ){
			sponsorCount = (Integer)result.get(0);
		}
		return sponsorCount;
	}
	
	
	
	public	List<ClaimSponsor> getClaimSponsor(  String sponsorName,
												 String sponsorTypeCd,
												 String sponsorDescription,
												 String creditClassCode, 
												 String accountNumber,
												 String insurerName,
												 String orderByClause, String sortDir){
		
		try {
//			VelocityEngine engine = new VelocityEngine();
//			Properties props = new Properties();
//			props.setProperty(VelocityEngine.RESOURCE_LOADER, "classpath");
//			props.setProperty("classpath." + VelocityEngine.RESOURCE_LOADER + ".class",
//					ClasspathResourceLoader.class.getName());
//			engine.init(props);
//
//			Template template = engine.getTemplate("sponsorHQLGen.vm");

			VelocityContext context = new VelocityContext();
			context.put("Util", new DAUtil());
			context.put("sponsorName", sponsorName);
			context.put("sponsorTypeCd", sponsorTypeCd);
			context.put("sponsorDescription", sponsorDescription);
			context.put("creditClassCode", creditClassCode);
			context.put("accountNumber", accountNumber);
			context.put("insurerName", insurerName);
			
			if (orderByClause != null && orderByClause.length() > 0) {
				if (sortDir.equalsIgnoreCase(ClaimSponsorDAOExtn.SORT_DIRECTION_ASC))
					context.put("orderBy", UIDBFieldMapForOrder.get(orderByClause) + " asc");
				else
					context.put("orderBy", UIDBFieldMapForOrder.get(orderByClause) + " desc");
			}

//			StringWriter writer = new StringWriter();
//			template.merge(context, writer);
//			writer.close();
//
//			String hqlQuery = writer.getBuffer().toString();

			String hqlQuery = DAUtil.getHqlQuery("sponsorHQLGen.vm", context);
			
			Query sponsorSearched = getSession().createQuery(hqlQuery);
			
			List resultList = sponsorSearched.list();
			
			List newList = new ArrayList();
			List sponsorNameList = new ArrayList();
			Iterator<Object> iterator = resultList.iterator();
			
			while (iterator.hasNext()) {
				Object[] objArray = (Object[]) iterator.next();
				ClaimSponsor claimSponsor = (ClaimSponsor) objArray[0];
				if (newList.size() == 0) {
					newList.add(claimSponsor);
					sponsorNameList.add(claimSponsor.getSponsorsName());
				} else {
					if (!sponsorNameList.contains(claimSponsor.getSponsorsName())) {
						newList.add(claimSponsor);
						sponsorNameList.add(claimSponsor.getSponsorsName());
					}
				}
			}
			return newList;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void setUIDBFieldMapForOrder(Map<String, String> fieldMapForOrder) {
		UIDBFieldMapForOrder = fieldMapForOrder;
	}
}
