/**
 * 
 */
package com.wtc.hcis.ip.da.extn;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplateDAO;

/**
 * @author Alok Ranjan
 *
 */
public class DoctorOrderTemplateDAOExtn extends DoctorOrderTemplateDAO {
	
	public static final String TEMPLATE_ID = "templateId";
	public static final String DOCTOR_ORDER_TYPE_CD = "doctorOrderType.orderTypeCd";
	
	public List<DoctorOrderTemplate> getOrderTemplates( String templateId,
											            String templateDesc, 
											            Integer templateForDoctorId,
											            String orderTypeCd ) {
		
		DetachedCriteria orderTemplateCriteria = DetachedCriteria.forClass( DoctorOrderTemplate.class );
		
		if ( templateId != null && templateId.length() > 0 ) {
			orderTemplateCriteria.add( Restrictions.ilike( DoctorOrderTemplateDAOExtn.TEMPLATE_ID, "%" + templateId + "%" ) );
		}
		
		if ( templateDesc != null && templateDesc.length() > 0 ) {
			orderTemplateCriteria.add( Restrictions.ilike( DoctorOrderTemplateDAOExtn.TEMPLATE_DESC, "%" + templateDesc + "%" ) );
		}
		
		if ( templateForDoctorId != null ) {
			orderTemplateCriteria.add( Restrictions.isNotNull( DoctorOrderTemplateDAOExtn.DOCTOR_ID ) );
			orderTemplateCriteria.add( Restrictions.eq( DoctorOrderTemplateDAOExtn.DOCTOR_ID, templateForDoctorId ) );
		}
		
		if ( orderTypeCd != null && orderTypeCd.length() > 0 ) {
			orderTemplateCriteria.add( Restrictions.eq( DoctorOrderTemplateDAOExtn.DOCTOR_ORDER_TYPE_CD,orderTypeCd ) );
		}
		
		List<DoctorOrderTemplate>doctorOrderList = getHibernateTemplate().findByCriteria( orderTemplateCriteria );
		
		return doctorOrderList;
	}
	
	public  List<DoctorOrderTemplate> findDoctorOrderTemplates( String templateId, String orderTypeCd,
																Integer doctorId, String serviceId,
																String departmentId ){
	
		StringBuffer hqlQueryString = new StringBuffer("from DoctorOrderTemplate as orderTemplate left join orderTemplate.doctorOrderTemplateDetailses as templateDetails");
		
		boolean whereFlag = true;
		String whereStr = "";
		String andStr = "";
		
		if (templateId != null && templateId.length() > 0) {
			whereStr = " where ";
			hqlQueryString.append(whereStr + " orderTemplate.templateId ='" + templateId + "'");
			andStr = " and "; whereStr = ""; whereFlag = false;
		}

		if (orderTypeCd != null && orderTypeCd.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " orderTemplate.doctorOrderType.orderTypeCd ='" + orderTypeCd + "'");
			andStr = " and "; whereStr = "";
		}
		
		if ( doctorId != null ) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " orderTemplate.doctorId=" + doctorId );
			andStr = " and "; whereStr = "";
		}
		
		if (serviceId != null && serviceId.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " templateDetails.serviceId ='" + serviceId + "'");
			andStr = " and "; whereStr = "";
		}
		if (departmentId != null && departmentId.length() > 0) {
			if (whereFlag) {
				whereStr = " where ";
				whereFlag = false;
			}
			hqlQueryString.append(whereStr + andStr + " templateDetails.responsibleDepartmentId ='" + departmentId + "'");
		}
		
		Query query = getSession().createQuery(hqlQueryString.toString());
		
		
		List resultList = query.list();
		List newList = new ArrayList() ;
		
		//**
		List doctorOrderTemplateList = new ArrayList();

		Iterator<Object> iterator = resultList.iterator();
		while( iterator.hasNext() ) {
			Object [] objArray = (Object[])iterator.next();
			DoctorOrderTemplate doctorOrderTemplate = (DoctorOrderTemplate)objArray[0];
			if(newList.size() == 0){
					newList.add(doctorOrderTemplate);
					doctorOrderTemplateList.add(doctorOrderTemplate.getTemplateId());
			}else{
				if( ! doctorOrderTemplateList.contains(doctorOrderTemplate.getTemplateId())){
					newList.add(doctorOrderTemplate);
					doctorOrderTemplateList.add(doctorOrderTemplate.getTemplateId());
				}
			}
		}
//		**
		
		return newList;
	}

}
