/**
	 * 
	 */
package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.LabDetails;
import com.wtc.hcis.da.LabDetailsDAO;


/**
 * @author Asha
 *
 */

public class LabDetailsDAOExtn extends LabDetailsDAO {

	public static final String 	LAB_ID = "labId";
//		public static final String 	LAB_TEST_CODE = "labTests.testCode";
		
		public List<LabDetails> getLabDetails(String labId, String HospitalName,String labType,String labName,
				String businessName, String branchName, String labOperatorID, String directionFromKnownPlace)
		 {

			DetachedCriteria criteria = DetachedCriteria.forClass( LabDetails.class );
			
			if( labId != null && !labId.isEmpty()){
				
				criteria.add( Restrictions.eq(LAB_ID, labId));
			}
			if( labName!= null && !labName.isEmpty() ){
				
				criteria.add( Restrictions.ilike(LabDetailsDAOExtn.LAB_NAME, "%" + labName + "%"));
			}
			if( labType!= null && !labType.isEmpty() ){
				
				criteria.add( Restrictions.eq(LabDetailsDAOExtn.LAB_TYPE,labType));
			}
			if( businessName!= null && !businessName.isEmpty() ){
	
				criteria.add( Restrictions.ilike(LabDetailsDAOExtn.BUSINESS_NAME, "%" + businessName + "%"));
			}
			if( labName!= null && !labName.isEmpty() ){
	
				criteria.add( Restrictions.ilike(LabDetailsDAOExtn.LAB_NAME, "%" + labName + "%"));
			}
			if( branchName!= null && !branchName.isEmpty() ){
	
				criteria.add( Restrictions.	ilike(LabDetailsDAOExtn.BRANCH_NAME, "%" + branchName + "%"));
			}
			if( labOperatorID!= null && !labOperatorID.isEmpty() ){
	
				criteria.add( Restrictions.ilike(LabDetailsDAOExtn.LAB_OPERATOR_ID, "%" + labOperatorID + "%"));
			}
			if( directionFromKnownPlace!= null && !directionFromKnownPlace.isEmpty() ){
				
				criteria.add( Restrictions.ilike(LabDetailsDAOExtn.DIRECTION_FROM_KNOWN_PLACE, "%" + directionFromKnownPlace + "%"));
			}

//			if (labId != null && !labId.isEmpty()) {
//
//				criteria.createAlias("labId", "labId");
//				
//				criteria.add( Restrictions.eq(LabDetailsDAOExtn.LAB_ID, labId));
//			}
			
			List labDetailsList =  getHibernateTemplate().findByCriteria(criteria);
			
			return labDetailsList;
		}
	}

	

