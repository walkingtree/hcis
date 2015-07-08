package in.wtc.hcis.bo.observations;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ObservationBM;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.constants.ApplicationErrors;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wtc.hcis.da.Doctor;
import com.wtc.hcis.da.Observations;
import com.wtc.hcis.da.ObservationsDAO;
import com.wtc.hcis.da.ObservationsId;
import com.wtc.hcis.da.extn.ObservationsDAOExtn;

/**
 * @author Ajit Kumar
 *
 */
public class ObservationManagerImpl implements ObservationManager {
	ObservationsDAOExtn observationsDAO;
	DataModelManager dataModelManager;

	public void addObservations( List<ObservationBM> observationsList ) throws HCISException {
		try {
			if ( observationsList != null && !observationsList.isEmpty() ) {
				Integer appointmentNbr = observationsList.get(0).getAppointmentNumber();
				List<Observations>existingObservationsList = observationsDAO.findByProperty( "id.appointmentNumber", appointmentNbr );
				
				Integer startingSeqNbr = 0;
				if ( existingObservationsList != null && !existingObservationsList.isEmpty() ) {
					startingSeqNbr = existingObservationsList.size();
				}
				
				for ( ObservationBM tmpObservationBM : observationsList ) {
					Observations observations = convertObservationBM2DM(tmpObservationBM, null);
					ObservationsId observationsId = observations.getId();
					startingSeqNbr++;
					observationsId.setObservationSeqNo(startingSeqNbr.toString());
					observations.setId( observationsId );
					observations.setCreateDtm(new Date());
					observationsDAO.save(observations);
				}
			}
			
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.OBSERVATION_ADD_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public List<ObservationBM> getObservations(Integer appointmentNumber) throws HCISException {
		try {
			List<ObservationBM> observationBMList = new ArrayList<ObservationBM>();
			List<Observations> observationsList = observationsDAO.findByProperty("id.appointmentNumber", appointmentNumber);
			for (Observations tmpObservation : observationsList) {
				ObservationBM observations = convertObservationDM2BM(tmpObservation);
				observationBMList.add(observations);
			}
			
			return observationBMList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.OBSERVATION_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	//Observation will be always one for an appointment 
	public ObservationBM modifyObservationDetails(ObservationBM observationsBM) throws HCISException {
		try {
			ObservationsId observationsId = new ObservationsId();
			observationsId.setAppointmentNumber(observationsBM.getAppointmentNumber());
			
			String seqNbr = 0 +"";
			if( observationsBM.getObservationSeqNo() != null){
				seqNbr = observationsBM.getObservationSeqNo();
			}else{
				observationsBM.setObservationSeqNo(seqNbr);
			}
			observationsId.setObservationSeqNo(seqNbr);
			
			Observations existingObservation = observationsDAO.findById(observationsId); 
			Observations dirtyObservation = convertObservationBM2DM(observationsBM, existingObservation);
			
			observationsDAO.attachDirty(dirtyObservation);
			
			return observationsBM;// convertObservationDM2BM(dirtyObservation);
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.OBSERVATION_MODIFY_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}
	
	private ObservationBM convertObservationDM2BM(Observations observation) {
		ObservationBM observationBM = new ObservationBM();
		
		observationBM.setAppointmentNumber(observation.getId().getAppointmentNumber());
//		observationBM.setAppointmentDate(observation.getAppointments().getAppointmentDate());
		CodeAndDescription doctor = new CodeAndDescription();
		doctor.setCode(observation.getDoctor().getDoctorId().toString());
		doctor.setDescription(observation.getDoctor().getFullName());
		observationBM.setDoctor(doctor);
		observationBM.setDate(observation.getCreateDtm());
		
		observationBM.setObservationSeqNo(observation.getId().getObservationSeqNo());
		observationBM.setObservationText(observation.getObservations());
		observationBM.setRemarks(observation.getRemarks());
		
		return observationBM;
	}
	
	private Observations convertObservationBM2DM( ObservationBM observation, Observations transientDM ) {
		Observations observationDM;

		if (transientDM != null)
			observationDM = transientDM;
		else
			observationDM = new Observations();
		if(observation.getDoctor()!=null && 
			observation.getDoctor().getCode()!=null && 
			!observation.getDoctor().getCode().equals("")){
			Doctor doctor = dataModelManager.getDoctor(new Integer(observation.getDoctor().getCode()));
			observationDM.setDoctor(doctor);
		}
		observationDM.setObservations(observation.getObservationText());
		observationDM.setRemarks(observation.getRemarks());
		
		ObservationsId observationsId = new ObservationsId();
		observationsId.setAppointmentNumber(observation.getAppointmentNumber());
		observationsId.setObservationSeqNo(observation.getObservationSeqNo());
		observationDM.setId(observationsId);
		
		return observationDM;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public List<ObservationBM> getObservationsForPatient(Integer patientId )
			throws HCISException {
		try {
			List<ObservationBM> observationBMList = new ArrayList<ObservationBM>(0);
			List<Observations> observationsList = observationsDAO.getObservationsForPatient( patientId );
			if( null != observationsList && observationsList.size() > 0  ){
				for (Observations tmpObservation : observationsList) {
					ObservationBM observations = convertObservationDM2BM(tmpObservation);
					observationBMList.add(observations);
				}
			}
			
			return observationBMList;
		} catch(Exception ex) {
			Fault fault = new Fault(ApplicationErrors.OBSERVATION_READ_FAILED);
			HCISException hcisException = new HCISException(fault, ex);
			throw hcisException;
		}
	}

	public void setObservationsDAO(ObservationsDAOExtn observationsDAO) {
		this.observationsDAO = observationsDAO;
	}
}
