package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.LabTemplateWidget;
import com.wtc.hcis.da.LabTemplateWidgetDAO;

public class LabTemplateWidgetDAOExtn extends LabTemplateWidgetDAO {
	
	private final String SECTION_CODE_3 = "SECTION_3";
	
	public final String TEST_CODE = "asso.id.testCode";
	
	
	public List<LabTemplateWidget> getWidgetsForTest(String testCode){
		
		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(LabTemplateWidget.class);
		
		
		criteria.createAlias("labTestAttribute", "labTestAttribute",Criteria.LEFT_JOIN);
		criteria.createAlias("labTestAttribute.labTestAttributeAssociations", "asso",Criteria.LEFT_JOIN);
		
		criteria.add( Restrictions.or(
									  Restrictions.ne(LabTemplateWidgetDAOExtn.SECTION_CODE, SECTION_CODE_3),
					                  (Restrictions.and(Restrictions.eq(LabTemplateWidgetDAOExtn.SECTION_CODE, SECTION_CODE_3),									
													    Restrictions.eq(TEST_CODE, testCode)))));
		
		return criteria.list();
	}

}
