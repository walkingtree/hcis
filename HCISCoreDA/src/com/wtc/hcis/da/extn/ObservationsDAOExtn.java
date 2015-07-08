package com.wtc.hcis.da.extn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.wtc.hcis.da.Observations;
import com.wtc.hcis.da.ObservationsDAO;
import com.wtc.hcis.da.Prescription;

public class ObservationsDAOExtn extends ObservationsDAO {

	public List<Observations> getObservationsForPatient( Integer patientId ) throws Exception {
		try {
			String hql = "from Appointments as app ,Observations as ob where app.appointmentNumber = ob.id.appointmentNumber and app.patient.patientId = :patientId";
			Query query = getSession().createQuery( hql );
			query.setInteger("patientId", patientId );
			List result = query.list();
			
			List observationsList = new ArrayList();
			if( result != null && result.size() > 0 ){
				
				Iterator<Object> iterator = result.iterator();
				while (iterator.hasNext()) {
					Object[] objArray = (Object[]) iterator.next();
					Observations observations = (Observations) objArray[1];
					observationsList.add( observations );
				}
			}
			
			return observationsList;
			
		} catch (Exception e) {
			throw e;
		}
	}
}
