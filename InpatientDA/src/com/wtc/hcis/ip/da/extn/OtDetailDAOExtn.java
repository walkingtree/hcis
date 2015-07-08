package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtDetail;
import com.wtc.hcis.ip.da.OtDetailDAO;

public class OtDetailDAOExtn extends OtDetailDAO {
	private static String OT_CODE = "otId";
	private static String OT_NAME = "name";
	private static String BED_NUMBER = "bedMaster.bedNumber";
	private static String SURGERY_CODE = "otSurgeryAssociations.id.surgeryCode";
	
	public List<OtDetail> getOTDetails(String otCode, String otName,
			String bedNumber, String surgeryCode ){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(OtDetail.class);
		
		if( otCode != null && !otCode.isEmpty()){
			criteria.add(Restrictions.eq(OtDetailDAOExtn.OT_CODE, otCode));
		}
		
		if( otName != null && !otName.isEmpty()){
			criteria.add(Restrictions.ilike(OtDetailDAOExtn.OT_NAME,"%" +otName+"%"));
		}
		
		if( bedNumber != null && !bedNumber.isEmpty()){
			criteria.add(Restrictions.eq(OtDetailDAOExtn.BED_NUMBER, bedNumber));
		}
		
		if( surgeryCode != null && !surgeryCode.isEmpty()){
			criteria.createAlias("otSurgeryAssociations", "otSurgeryAssociations");
			criteria.add(Restrictions.eq(OtDetailDAOExtn.SURGERY_CODE, surgeryCode));
		}
		
		List<OtDetail> otDetailList = getHibernateTemplate().findByCriteria(criteria);
		return otDetailList;
	}
}
