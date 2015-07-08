/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.InsurancePlansDAO;

/**
 * @author Bhavesh
 *
 */
public class InsurancePlansDAOExtn extends InsurancePlansDAO {
	
	private static final Log log = LogFactory.getLog( InsurancePlans.class );
	
	static final String INSURER_NAME = "insurer.insurerName";
	static final String PLAN_VALID_TO_DT = "validToDt";
	
	public List<InsurancePlans> getInsurancePlans( String planCode,
			 									   String planName,
			 									   String insurerName,
			 									   String sponsorName,
			 									   Date   validFromDt,
			 									   Date   validToDt ){
		
		try {
			DetachedCriteria  insurancePlanCriteria = DetachedCriteria.forClass( InsurancePlans.class );
			
			if( planCode != null && !planCode.isEmpty()){
				
				insurancePlanCriteria.add( Restrictions.eq("planCd", planCode));
			}
			
			if( planName != null && !planName.isEmpty()){
				
				insurancePlanCriteria.add( Restrictions.eq("planName", planName));
			}
					
			if( insurerName != null && !insurerName.isEmpty()){
				
				insurancePlanCriteria.add( Restrictions.eq("insurer.insurerName", insurerName));
			}
			if( sponsorName != null && !sponsorName.isEmpty()){
				
				insurancePlanCriteria.createAlias("insurer", "insurer");
				insurancePlanCriteria.createAlias("insurer.sponsorInsurerAssociations", "sponsorInsurerAssociations" );
				insurancePlanCriteria.add( Restrictions.eq("sponsorInsurerAssociations.claimSponsor.sponsorsName", sponsorName));
			}
			
			if( validFromDt != null ){
				insurancePlanCriteria.add( Restrictions.ge("validFromDt", validFromDt ));
			}
			
			if( validToDt != null ){
				insurancePlanCriteria.add( Restrictions.le("validToDt", validToDt ) );
			}
			
			
			List<InsurancePlans> insurancePlansList = getHibernateTemplate().findByCriteria(insurancePlanCriteria);
				  
			return insurancePlansList;
		} catch (RuntimeException ex) {
			log.error(" Failed to read plans "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<InsurancePlans>   getInsurancePlans( String planCode,
													 String insurerName,
													 String planName,
													 String serviceCode,
													 String diseaseName,
													 Double coverageAmntFrom,
													 Double coverageAmntTo,
													 Date   validFromDt,
													 Date   validToDt){
		
		try {
		StringBuffer hqlQuery = new StringBuffer("from InsurancePlans as insurancePlans");
		
		String and = "";
		
		boolean whereFlag = false;
		
		if(serviceCode != null && !serviceCode.isEmpty())
		{
			if( diseaseName != null && !diseaseName.isEmpty() )
			{
				hqlQuery = hqlQuery.append(",PlanCoversDisease as planCoversDisease where insurancePlans = planHasServices.insurancePlans" + 
											" and insurancePlans = planCoversDisease.insurancePlans");
			
				and = " and ";
			}
			else
			{
				hqlQuery = hqlQuery.append(",PlanHasServices as planHasServices where insurancePlans = planHasServices.insurancePlans");
				
				and = " and ";
			}
		}
		else
		{
			if( diseaseName != null && !diseaseName.isEmpty() ){
				
				hqlQuery = hqlQuery.append(",PlanCoversDisease as planCoversDisease where insurancePlans = planCoversDisease.insurancePlans");
				and = " and ";
			}
		}
		
	   String planCodeQuery = "";
	   String insurerNameQuery= "";
	   String planNameQuery = "";
       String coverageAmntFromQuery = "";
       String coverageAmntToQuery = "";
       String serviceCodeQuery = "";
       String diseaseNameQuery = "";
       String validFromDtQuery = "";
	   String validToDtQuery = "";
      
       
        if( planCode != null && !planCode.isEmpty()){
        	if( and.equals("") ){
        		
        		}
        	
        	planCodeQuery =  and + " insurancePlans.planCd = '" + planCode + "'";
        	whereFlag = true;
        	and = " and ";
        }
        
        if( insurerName != null && !insurerName.isEmpty()){
			
        	insurerNameQuery = and + " insurancePlans.insurer.insurerName = '" + insurerName + "'";
			
			whereFlag = true;
        	and = " and ";
		}
			

		if( planName != null && !planName.isEmpty()){
			
			planNameQuery = and + " insurancePlans.planName like '%" + planName + "%'";
			
			whereFlag = true;
        	and = " and ";
			
		}
		if( coverageAmntFrom != null ){
		
			coverageAmntFromQuery = and + " insurancePlans.totalCoverageAmt >= " + coverageAmntFrom;
			
			whereFlag = true;
        	and = " and ";
			
		}
		if( coverageAmntTo != null ){
			
			coverageAmntToQuery = and + " insurancePlans.totalCoverageAmt <= " + coverageAmntTo;
			
			whereFlag = true;
        	and = " and ";
			
		}
		
		if(serviceCode != null && !serviceCode.isEmpty()){
			serviceCodeQuery = and + " planHasServices.id.serviceCode = '" + serviceCode + "'";
			and = " and ";
			whereFlag = false;
			
		}
		if( diseaseName != null && !diseaseName.isEmpty() ){
			
			diseaseNameQuery = and + " planCoversDisease.id.diseaseName = '" + diseaseName + "'";
			whereFlag = false;
		}
		if( validFromDt != null ){
			
			validFromDtQuery = and + " insurancePlans.validFromDt >= :validFromDt ";
			whereFlag = true;
			and = " and ";
		}
		if( validToDt != null ){
			
			validToDtQuery = and + " insurancePlans.validToDt <= :validToDt ";
			whereFlag = true;
		}
		
		String where = "";
		if( whereFlag){
			where = " where ";
		}
		
		hqlQuery = hqlQuery.append( where + planCodeQuery + insurerNameQuery + planNameQuery + coverageAmntFromQuery 
										  + coverageAmntToQuery + serviceCodeQuery + diseaseNameQuery + validFromDtQuery + validToDtQuery);
		
		String finalQuery = hqlQuery.toString();
						
		Query query = getSession().createQuery( finalQuery ); 
		
		
		
		if( validFromDt != null ){
			query.setDate("validFromDt", validFromDt);			
		}
		if( validToDt != null ){
			query.setDate("validToDt", validToDt);
		}
		
		
		List<Object> resultList = query.list();
		
		Set<String> insurancePlansIdSet = new HashSet<String>();// as Set contains only unique values
		List<InsurancePlans> insurancePlansList = new ArrayList<InsurancePlans>();
		Iterator<Object> iterator = resultList.iterator();

// The resultList either contain list of Collection objects(in case of table joins) or list of  "InsurancePlans" objects		
		if ( resultList != null && !resultList.isEmpty() ) {
					if( resultList.get(0) instanceof InsurancePlans){
			
			while( iterator.hasNext()){
				
				InsurancePlans insurancePlans = (InsurancePlans) iterator.next();
				
				if( !insurancePlansIdSet.contains( insurancePlans.getPlanCd() )){

					insurancePlansIdSet.add( insurancePlans.getPlanCd() );
					insurancePlansList.add( insurancePlans );
				}
			}
		}else{
			while( iterator.hasNext() ) {
				Object [] objArray = (Object[])iterator.next();
				InsurancePlans insurancePlans =  (InsurancePlans)objArray[0];
				
				if( !insurancePlansIdSet.contains( insurancePlans.getPlanCd() )){
					insurancePlansIdSet.add( insurancePlans.getPlanCd() );
					insurancePlansList.add( insurancePlans );
				}
			}
		}
	
		return insurancePlansList;
	}
    return null;
	} catch (RuntimeException ex) {
		log.error(" Failed to read plans "  + ex.getMessage() );
		throw ex;
	}
	}

	public List<InsurancePlans>   getActivePlansForInsurer( String insurerName ){
		
	try {
			DetachedCriteria planCriteria = DetachedCriteria.forClass( InsurancePlans.class );
			
			List<InsurancePlans> insurancePlansList = new ArrayList<InsurancePlans>(0);
			
			if( insurerName != null && insurerName.length() > 0){
				planCriteria.add( Restrictions.eq(InsurancePlansDAOExtn.INSURER_NAME, insurerName));
				
			}
			
			planCriteria.add( Restrictions.or( Restrictions.ge(InsurancePlansDAOExtn.PLAN_VALID_TO_DT, new Date()),
					  Restrictions.isNull( InsurancePlansDAOExtn.PLAN_VALID_TO_DT )));
	
			insurancePlansList = getHibernateTemplate().findByCriteria( planCriteria );
			
			return insurancePlansList;
		} catch (RuntimeException ex) {
		log.error(" Failed to read plan "  + ex.getMessage() );
		throw ex;
		}
	}
	
}
