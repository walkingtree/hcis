package in.wtc.hcis.ip.test.unittest;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.wtc.hcis.ip.da.AdmissionEntryPoint;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bo.medicine.BrandManager;
import in.wtc.hcis.ip.common.IPDataModelManager;
import in.wtc.hcis.ip.common.IPReferenceDataManager;
import in.wtc.hcis.ip.common.IPReferenceDataManagerImpl;
import in.wtc.hcis.test.unit.Util;
import junit.framework.TestCase;

/**
 * @author Bhavesh
 *
 */
public class TestIPReferenceDataManagerImpl extends TestCase {

	IPReferenceDataManager referenceDataManager;
	protected void setUp() throws Exception {
		String[] ctxFileArr = {"IPapplicationContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		referenceDataManager = (IPReferenceDataManager)ctx.getBean("IPReferenceDataManager");

	}

	public void testGetActionStatus() {
		try {
			List<CodeAndDescription> acnStsList = (List<CodeAndDescription>) referenceDataManager.getActionStatus(0,9,"ASC");
			Util.dumpBean(acnStsList.get(1));
			assertTrue(acnStsList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetActivityType() {
		try {
			List<CodeAndDescription> acvtList= (List<CodeAndDescription>)referenceDataManager.getActivityType(0,9,"ASC");
			Util.dumpBean(acvtList.get(0));
			assertTrue(acvtList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetActivityTypeString() {
		try {
			List<CodeAndDescription> acvtList= (List<CodeAndDescription>)referenceDataManager.getActivityTypeForGroup("GENERAL",0,9,"ASC");
			Util.dumpBean(acvtList.get(0));
			assertTrue(acvtList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetAdmissionEntryPoints() {
		try {
			List<CodeAndDescription>admissionEntryPointList = (List<CodeAndDescription>)referenceDataManager.getAdmissionEntryPoints(0,9,"ASC");
			Util.dumpBean(admissionEntryPointList.get(0));
			assertTrue(admissionEntryPointList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetAdmissionStatus() {
		try {
			List<CodeAndDescription>admissionStatusList = (List<CodeAndDescription>)referenceDataManager.getAdmissionStatus(0,9,"ASC");
			Util.dumpBean(admissionStatusList.get(0));
			assertTrue(admissionStatusList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedStatus() {
		try {
			List<CodeAndDescription>bedStatusList = (List<CodeAndDescription>)referenceDataManager.getBedStatus(0,9,"ASC");
			Util.dumpBean(bedStatusList.get(0));
			assertTrue(bedStatusList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedType() {
		try {
			List<CodeAndDescription>bedTypeList = (List<CodeAndDescription>)referenceDataManager.getBedType(0,9,"ASC");
			Util.dumpBean(bedTypeList.get(0));
			assertTrue(bedTypeList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetClaimSponsors() {
		try {
			List<CodeAndDescription>claimSponsorList = (List<CodeAndDescription>)referenceDataManager.getClaimSponsors(0,9,"ASC");
			Util.dumpBean(claimSponsorList.get(0));
			assertTrue(claimSponsorList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetCreditClass() {
		try {
			List<CodeAndDescription>creditClassList = (List<CodeAndDescription>)referenceDataManager.getCreditClass(0,9,"ASC");
			Util.dumpBean(creditClassList.get(0));
			assertTrue(creditClassList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetDischargeType() {
		try {
			List<CodeAndDescription>dischargeTypeList = (List<CodeAndDescription>)referenceDataManager.getDischargeType(0,9,"ASC");
			Util.dumpBean(dischargeTypeList.get(0));
			assertTrue(dischargeTypeList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetDoctorOrderStatus() {
		try {
			List<CodeAndDescription>doctorOrderStatusList = (List<CodeAndDescription>)referenceDataManager.getDoctorOrderStatus(0,9,"ASC");
			Util.dumpBean(doctorOrderStatusList.get(0));
			assertTrue(doctorOrderStatusList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetDoctorOrderType() {
		try {
			List<CodeAndDescription>doctorOrderTypeList = (List<CodeAndDescription>)referenceDataManager.getDoctorOrderType(0,9,"ASC");
			Util.dumpBean(doctorOrderTypeList.get(0));
			assertTrue(doctorOrderTypeList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetBedSpecialFeatures() {
		try {
			List<CodeAndDescription>bedSpecialFeatureList = (List<CodeAndDescription>)referenceDataManager.getBedSpecialFeatures(0,9,"ASC");
			Util.dumpBean(bedSpecialFeatureList.get(0));
			assertTrue(bedSpecialFeatureList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetFeedbackType() {
		fail("Not yet implemented");
	}

	public void testGetNursingUnitType() {
		try {
			List<CodeAndDescription>nursingUnitTypeList = (List<CodeAndDescription>)referenceDataManager.getNursingUnitType(0,9,"ASC");
			Util.dumpBean(nursingUnitTypeList.get(0));
			assertTrue(nursingUnitTypeList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetReservationStatus() {
		try {
			List<CodeAndDescription>reservationStatusList = (List<CodeAndDescription>)referenceDataManager.getReservationStatus(0,9,"ASC");
			Util.dumpBean(reservationStatusList.get(0));
			assertTrue(reservationStatusList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	public void testGetSponsorClaimStatus() {
		try {
			List<CodeAndDescription>sponsorClaimStatusList = (List<CodeAndDescription>)referenceDataManager.getSponsorClaimStatus(0,9,"ASC");
			Util.dumpBean(sponsorClaimStatusList.get(0));
			assertTrue(sponsorClaimStatusList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	public void testGetSponsorType() {
		try {
			List<CodeAndDescription>sponsorTypeList = (List<CodeAndDescription>)referenceDataManager.getSponsorType(0,9,"ASC");
			Util.dumpBean(sponsorTypeList.get(0));
			assertTrue(sponsorTypeList.size()>0);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

}
