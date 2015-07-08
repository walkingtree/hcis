package com.wtc.hcis.da.extn;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.DeptEspecialityAssoc;
import com.wtc.hcis.da.DeptEspecialityAssocDAO;

public class DeptEspecialityAssocDAOExtn extends DeptEspecialityAssocDAO{
	
	private static String DEPARTMENT_NAME = "id.departmentCode";
	private static String IS_ACTIVE = "active";
	private static String IS_ACTIVE_YES ="Y";
	
	public List<DeptEspecialityAssoc> getSpecialityForDepartment( String departmentCode ) {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass( DeptEspecialityAssoc.class );
		
		detachedCriteria.add( Restrictions.eq(DeptEspecialityAssocDAOExtn.DEPARTMENT_NAME, departmentCode ));
		
		detachedCriteria.add( Restrictions.eq(DeptEspecialityAssocDAOExtn.IS_ACTIVE, DeptEspecialityAssocDAOExtn.IS_ACTIVE_YES));
		
		List<DeptEspecialityAssoc> deptEspecialityAssocList = getHibernateTemplate().findByCriteria(detachedCriteria);
		
		
		return deptEspecialityAssocList;
	}

}
