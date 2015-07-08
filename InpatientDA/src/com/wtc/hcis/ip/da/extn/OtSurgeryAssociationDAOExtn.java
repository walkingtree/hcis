package com.wtc.hcis.ip.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.OtSurgeryAssociation;
import com.wtc.hcis.ip.da.OtSurgeryAssociationDAO;

public class OtSurgeryAssociationDAOExtn extends OtSurgeryAssociationDAO{
	
	private static String OT_ID = "id.otId";
	private static String SURGERY_CODE = "id.surgeryCode";
	
	public List<OtSurgeryAssociation> getSurgeryAssociationList(String otId, String surgeryCode){
		DetachedCriteria criteria = DetachedCriteria.forClass(OtSurgeryAssociation.class);
		
		if( otId != null && !otId.isEmpty()){
			criteria.add(Restrictions.eq(OtSurgeryAssociationDAOExtn.OT_ID, otId));
		}
		
		if( surgeryCode != null && !surgeryCode.isEmpty()){
			criteria.add(Restrictions.eq(OtSurgeryAssociationDAOExtn.SURGERY_CODE, surgeryCode));
		}
		
		List<OtSurgeryAssociation> otSurgeryAssoList = getHibernateTemplate().findByCriteria(criteria);
		
		return otSurgeryAssoList;
	}

}
