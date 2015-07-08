package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtSurgeryStatus;
import com.wtc.hcis.ip.da.OtSurgeryStatusDAO;

public class OtSurgeryStatusDAOExtn extends OtSurgeryStatusDAO {
	
	private static String OT_SURGERY_STATUS_CAPTURE_TIME = "captureTime";
	private static String OT_SURGERY_STATUS_CONTRIBUTE_TO_SCHEDULING = "contributeToScheduling";
	private static String OT_SURGERY_STATUS_SEQ_NBR = "seqNo";

	public List<OtSurgeryStatus> getOTSurgeryStatusList(String captureTime , String contributeToScheduling){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OtSurgeryStatus.class);
		
		if( captureTime != null && !captureTime.isEmpty()){
			criteria.add(Restrictions.eq(OtSurgeryStatusDAOExtn.OT_SURGERY_STATUS_CAPTURE_TIME, captureTime));
		}
		
		if( contributeToScheduling != null && !contributeToScheduling.isEmpty()){
			criteria.add(Restrictions.eq(OtSurgeryStatusDAOExtn.OT_SURGERY_STATUS_CONTRIBUTE_TO_SCHEDULING, contributeToScheduling));
		}
		
		criteria.addOrder(Order.asc(OtSurgeryStatusDAOExtn.OT_SURGERY_STATUS_SEQ_NBR));
		
		List<OtSurgeryStatus> otSurgeryStatusList = getHibernateTemplate().findByCriteria(criteria);
		return otSurgeryStatusList;
	} 
	
}
