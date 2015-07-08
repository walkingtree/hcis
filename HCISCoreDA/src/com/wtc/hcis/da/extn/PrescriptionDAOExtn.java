package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.Prescription;
import com.wtc.hcis.da.PrescriptionDAO;

public class PrescriptionDAOExtn extends PrescriptionDAO {
	private static final Log log = LogFactory.getLog(PrescriptionDAOExtn.class);
	
	public List<Prescription> findPrescriptions(Integer appointmentNumber) {
		try {
			List<Prescription> prescriptionList = null;
			DetachedCriteria prescriptionCriteria = DetachedCriteria.forClass(Prescription.class);
			prescriptionCriteria.add(Restrictions.eq("srcRefNumber", appointmentNumber))
								.addOrder(Order.asc("prescriptionNumber"));
			
			prescriptionList = getHibernateTemplate().findByCriteria(prescriptionCriteria);
			
			return prescriptionList;
		} catch (RuntimeException ex ) {
			log.error("Failed to retrieve prescriptions "  + ex.getMessage() );
			throw ex;
		}
	}
	
	public List<Prescription> findPrescriptionForPatient( Integer patientId ) throws Exception{
		try {
			
			String hql = "from Appointments as app ,Prescription as pr where app.appointmentNumber = pr.srcRefNumber and app.patient.patientId = :patientId";
			Query query = getSession().createQuery( hql );
			query.setInteger("patientId", patientId );
			List result = query.list();
			
			List prescriptionList = new ArrayList();
			if( result != null && result.size() > 0 ){
				
				Iterator<Object> iterator = result.iterator();
				while (iterator.hasNext()) {
					Object[] objArray = (Object[]) iterator.next();
					Prescription prescription = (Prescription) objArray[1];
					prescriptionList.add( prescription );
				}
			}
			
			
			return prescriptionList;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

