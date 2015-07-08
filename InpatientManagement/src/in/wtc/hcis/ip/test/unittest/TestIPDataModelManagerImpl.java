package in.wtc.hcis.ip.test.unittest;

import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.IPDataModelManager;
import in.wtc.hcis.ip.common.IPReferenceDataManager;
import in.wtc.hcis.test.unit.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.BedEnvelope;
import com.wtc.hcis.ip.da.BedMaster;
import com.wtc.hcis.ip.da.BedPool;
import com.wtc.hcis.ip.da.BedReservation;
import com.wtc.hcis.ip.da.BedStatus;
import com.wtc.hcis.ip.da.BedType;
import com.wtc.hcis.ip.da.NursingUnit;
import com.wtc.hcis.ip.da.NursingUnitType;

import junit.framework.TestCase;

/**
 * @author Bhavesh
 *
 */
public class TestIPDataModelManagerImpl extends TestCase {

	IPDataModelManager dataModelManager;
	
	protected void setUp() throws Exception {
		String[] ctxFileArr = {"IPapplicationContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		dataModelManager = (IPDataModelManager)ctx.getBean("IPDataModelManager");
	}

	public void testGetBedEnvelope() {
		try {
			BedEnvelope bedEnvelope = dataModelManager.getBedEnvelope("ENV1") ;
			Util.dumpBean(bedEnvelope);
			assertTrue(bedEnvelope!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedMaster() {
		try{
		BedMaster bedMaster = dataModelManager.getBedMaster("010002");
		Util.dumpBean(bedMaster);
		assertTrue(bedMaster!=null);
	} catch (HCISException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		assertTrue(false);
	}
	}

	public void testGetBedPool() {
		try{
			BedPool bedPool = dataModelManager.getBedPool("BED_POOL1");
			Util.dumpBean(bedPool);
			assertTrue(bedPool!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	

	public void testGetBedReservation() {
		try{
			BedReservation bedReservation = dataModelManager.getBedReservation(new Integer(1001));
			Util.dumpBean(bedReservation);
			assertTrue(bedReservation!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedStatus() {
		try{
			BedStatus bedStatus = dataModelManager.getBedStatus("CLEANING");
			Util.dumpBean(bedStatus);
			assertTrue(bedStatus!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedType() {
		try{
			BedType bedType = dataModelManager.getBedType("OT");
			Util.dumpBean(bedType);
			assertTrue(bedType!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetNursingUnit() {
		try{
			NursingUnit nursingUnit = dataModelManager.getNursingUnit("NU1");
			Util.dumpBean(nursingUnit);
			assertTrue(nursingUnit!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetNursingUnitType() {
		try{
			NursingUnitType nursingUnitType = dataModelManager.getNursingUnitType("ICU");
			Util.dumpBean(nursingUnitType);
			assertTrue(nursingUnitType!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	public void testGetAdmission(){
		try{
			Admission admission  = dataModelManager.getAdmission(new Integer(1));
			Util.dumpBean(admission);
			assertTrue(admission!=null);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
}
