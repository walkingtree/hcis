package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.ReportParam;
import com.wtc.hcis.da.ReportParamDAO;

public class ReportParamDAOExtn extends ReportParamDAO {

	public List<ReportParam>getOrderedReportParams(String reportCode){
		
		DetachedCriteria criteria = DetachedCriteria.forClass( ReportParam.class);
		criteria.add( Restrictions.eq("id.reportCode", reportCode))
				.addOrder(Order.asc("widgetSeqNbr"));
		
		return getHibernateTemplate().findByCriteria(criteria);
	}
}
