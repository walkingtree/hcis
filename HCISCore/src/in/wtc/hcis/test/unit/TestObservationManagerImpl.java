package in.wtc.hcis.test.unit;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ObservationBM;
import in.wtc.hcis.bo.observations.ObservationManager;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestObservationManagerImpl extends TestCase {
	
	private ObservationManager observationManager;

	protected void setUp() throws Exception {
		try {
			String[] ctxFileArr = { "applicationContext.xml", "HCISCoreContext.xml" };
		    ApplicationContext ctx = new ClassPathXmlApplicationContext( ctxFileArr );
		    observationManager = (ObservationManager)ctx.getBean( "ObservationManager" );
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

//	public void testAddObservations() {
//		try {
//			List<ObservationBM>observationBMList = new ArrayList<ObservationBM>();
//			ObservationBM observationBM = new ObservationBM();
//			observationBM.setAppointmentNumber( 6 );
//			observationBM.setDoctor( new CodeAndDescription( "2", "Rama Krishna" ) );
//			observationBM.setObservationText( "observation Text-2" );
//			observationBM.setRemarks( "remarks-2" );
//			observationBMList.add( observationBM );
//			
//			observationBM = new ObservationBM();
//			observationBM.setAppointmentNumber( 6 );
//			observationBM.setDoctor( new CodeAndDescription( "2", "Rama Krishna" ) );
//			observationBM.setObservationText( "observation Text-4" );
//			observationBM.setRemarks( "remarks-4" );
//			observationBMList.add( observationBM );
//			
//			observationManager.addObservations( observationBMList );
//			assertTrue(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

	public void testGetObservations() {
		try {
			List<ObservationBM> observationsBMList = observationManager.getObservations( 6 );
			ObservationBM observationsBM = observationsBMList.get(2);
			observationsBM.setObservationText( "Observation Text Modified - 2" );
			observationsBM = observationManager.modifyObservationDetails(observationsBM);
			TestUtils.dumpBean( observationsBM );
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testModifyObservationDetails() {
		try {
			List<ObservationBM> observationsBMList = observationManager.getObservations( 6 );
			ObservationBM observationsBM = observationsBMList.get(2);
			observationsBM.setObservationText( "Observation Text Modified - 2" );
			observationsBM = observationManager.modifyObservationDetails(observationsBM);
			TestUtils.dumpBean( observationsBM );
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
