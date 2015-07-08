package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.wtc.hcis.da.DocVitalField;
import com.wtc.hcis.da.DocVitalFieldDAO;

public class DocVitalFieldDAOExtn extends DocVitalFieldDAO {
	
	public List<DocVitalField> getVitalFields(){
		
		DetachedCriteria criteria = DetachedCriteria.forClass(DocVitalField.class);
		
		criteria.addOrder(Order.asc(DocVitalFieldDAOExtn.FIELD_GROUP));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}

}
