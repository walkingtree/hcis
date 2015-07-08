/**
 * 
 */
package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.AdmissionClaimActivity;
import com.wtc.hcis.ip.da.AdmissionClaimActivityDAO;
import com.wtc.hcis.ip.da.AdmissionClaimRequest;

/**
 * @author Bhavesh
 *
 */
public class AdmissionClaimActivityDAOExtn extends AdmissionClaimActivityDAO {
	
	
	public List<AdmissionClaimActivity> getClaimRequestActivities( String claimRequestSequenceNbr,
																   String activityTypeCd,
																   String claimStatusCd,
																   String remarks,
																   String activityCreatedBy ){
		DetachedCriteria criteria = DetachedCriteria.forClass( AdmissionClaimActivity.class );
		
		if( claimRequestSequenceNbr != null && !claimRequestSequenceNbr.isEmpty() ){
			criteria.add(Restrictions.eq("id.requestSequenceNbr", claimRequestSequenceNbr ));
		}
		if( activityTypeCd != null && !activityTypeCd.isEmpty() ){
			criteria.add(Restrictions.eq("id.activityTypeCd", activityTypeCd ));
		}
		if( claimStatusCd != null && !claimStatusCd.isEmpty() ){
			criteria.add(Restrictions.eq("sponsorClaimStatus.claimStatusCd", claimStatusCd ));
		}
		if( remarks != null && !remarks.isEmpty() ){
			criteria.add(Restrictions.ilike("remarks", "%" + remarks + "%" ));
		}
		if( activityCreatedBy != null && !activityCreatedBy.isEmpty() ){
			criteria.add(Restrictions.eq("createdBy", activityCreatedBy ));
		}
		
		List<AdmissionClaimActivity> claimActivityList = getHibernateTemplate().findByCriteria(criteria);
		
		
		return claimActivityList;
	}

}
