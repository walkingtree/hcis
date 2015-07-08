package in.wtc.hcis.test.unit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.hcis.bm.base.AssignSvcAndPackageBM;
import in.wtc.hcis.bm.base.AssignedPackageBM;
import in.wtc.hcis.bm.base.AssignedServiceBM;
import in.wtc.hcis.bm.base.AssignedServiceInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PackageHasServiceBM;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.base.ServiceGroupBM;
import in.wtc.hcis.bm.base.ServicePackageBM;
import in.wtc.hcis.bm.base.ServiceSummaryBM;
import in.wtc.hcis.bo.services.ServiceManager;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtc.hcis.da.ServicePackage;

public class TestServiceManagerImpl {
ServiceManager serviceManager;
	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"in\\wtc\\hcis\\test\\unit\\applicationContextTest.xml","AccountantContextBLTest.xml","applicationContextReport.xml","HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
			
			serviceManager = (ServiceManager)ctx.getBean("ServiceManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testAddAssignedService() {
		try {
			AssignedServiceBM assignedServiceBM = new AssignedServiceBM();
			assignedServiceBM.setPatientId(new Integer("49") );
			ArrayList<AssignedServiceInfoBM> assignedServiceInfoBMArr =new ArrayList<AssignedServiceInfoBM>();
			
			AssignedServiceInfoBM assignedServiceInfoBM= new AssignedServiceInfoBM();
			assignedServiceInfoBM.setAmount("500");
			assignedServiceInfoBM.setServiceCharge("200");
			assignedServiceInfoBM.setServiceCode("2002");
			
			
			assignedServiceInfoBMArr.add(assignedServiceInfoBM);
//			assignedServiceBM.setServiceInfoList(assignedServiceInfoBMArr);
		
			// TODO : Alok to check this
//			serviceManager.addAssignedService(assignedServiceBM);

			
			assertTrue(true);
			
		} catch (RuntimeException e) {
		
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testAddService() {
		try {
//			ServiceBM serviceBM = new ServiceBM();
//			
//			serviceBM.setDepartment(new CodeAndDescription("DENTAL", ""));
//			serviceBM.setServiceCharge(100.0);
//			serviceBM.setServiceCode("DENTURE");
////			serviceBM.setServiceGroup(new CodeAndDescription("1001","Radiology"));
//			serviceBM.setServiceName("Apply Denture");
//			serviceBM.setServiceStatus(new CodeAndDescription("CREATED",""));
//			serviceBM.setCreatedBy("Bhavesh");
//			serviceBM.setDepositAmount(500.0);
//			serviceBM.setEffectiveFromDate(new Date());
//			serviceBM.setServiceDesc("Artificially replaces missing teeth");
//			serviceBM.setServiceProcedure("Service Procedure");
//			serviceManager.addService(serviceBM);
			
			
			ServiceBM serviceBM = new ServiceBM();
			
			serviceBM.setDepartment(new CodeAndDescription("RADIOLOGY_IMAGING", ""));
			serviceBM.setServiceCharge(100.0);
			serviceBM.setServiceCode("XRAY");
//			serviceBM.setServiceGroup(new CodeAndDescription("1001","Radiology"));
			serviceBM.setServiceName("X-Ray");
			serviceBM.setServiceStatus(new CodeAndDescription("CREATED",""));
			serviceBM.setPersonId("Bhavesh");
			serviceBM.setDepositAmount(500.0);
			serviceBM.setEffectiveFromDate(new Date());
			serviceBM.setServiceDesc("Pring Xray Film");
			serviceBM.setServiceProcedure("Service Procedure");
			serviceManager.addService(serviceBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	@Test
	public void testAddServiceGroup() {
		try {
			ServiceGroupBM serviceGroupBM = new ServiceGroupBM();
			serviceGroupBM.setGroupName("RadioLogy");
			serviceGroupBM.setIsActive(new Boolean(true));
			serviceGroupBM.setParentGroup(null);
			serviceGroupBM.setServiceGroupCode("RADIOLOGY");
			serviceGroupBM.setCreatedBy("Bhavesh");
			
			List<ServiceSummaryBM> serviceSummaryBMList = new ArrayList<ServiceSummaryBM>();
			
			ServiceSummaryBM serviceSummaryBM1 = new ServiceSummaryBM();
			
			serviceSummaryBM1.setServiceCode("XRAY");
			serviceSummaryBM1.setServiceCharge(200.0);
			serviceSummaryBM1.setServiceStatus(new CodeAndDescription("CONCEPT",""));
			
			serviceSummaryBMList.add( serviceSummaryBM1 );
			
			serviceGroupBM.setServiceSummaryList( serviceSummaryBMList );
			
			serviceManager.addServiceGroup(serviceGroupBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAddServicePackage(){
	
		try {
			ServicePackageBM servicePackageBM = new ServicePackageBM();
			servicePackageBM.setName("Test Package2");
			servicePackageBM.setDescription("Paskage Created for Test");
			servicePackageBM.setCreatedBy("Bhavesh");
			servicePackageBM.setPackageCharge(500.0);
			servicePackageBM.setPackageId("PKG2");
//			servicePackageBM.setChargeOverrideLevel("P");
			servicePackageBM.setDiscountAmountPct(10.0);
//			servicePackageBM.setDiscountType("A");
			servicePackageBM.setEffectiveFromDt(new Date());
			servicePackageBM.setEffectiveCharge(400.0);
			
			List<PackageHasServiceBM> serviceBMList = new ArrayList<PackageHasServiceBM>(0);
			PackageHasServiceBM packageHasServiceBM = new PackageHasServiceBM();
			
			packageHasServiceBM.setService(new CodeAndDescription("XRAY",""));
			packageHasServiceBM.setEffectiveCharge(80.0);
			packageHasServiceBM.setServiceCharge(10.0);
			packageHasServiceBM.setNumberOfUnits(1);
			packageHasServiceBM.setDiscountType("A");
			packageHasServiceBM.setDiscountAmtPct(50.0);
			
			PackageHasServiceBM packageHasServiceBM1 = new PackageHasServiceBM();
			packageHasServiceBM1.setService(new CodeAndDescription("DENTURE",""));
			packageHasServiceBM1.setEffectiveCharge(800.0);
			packageHasServiceBM1.setServiceCharge(110.0);
			packageHasServiceBM1.setNumberOfUnits(1);
			packageHasServiceBM1.setDiscountType("A");
			packageHasServiceBM1.setDiscountAmtPct(50.0);
			
			serviceBMList.add( packageHasServiceBM );
			serviceBMList.add( packageHasServiceBM1 );
			
			servicePackageBM.setServiceBMList(serviceBMList);
			serviceManager.addServicePackage(servicePackageBM);
			assertTrue( true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void testAssignSvc(){
		
		try {
			AssignSvcAndPackageBM assignSvcAndPackageBM = new AssignSvcAndPackageBM();
			
			assignSvcAndPackageBM.setDoctorId(1);
			assignSvcAndPackageBM.setPatientId(8);
			assignSvcAndPackageBM.setCreatedBy("Bhavesh");
			assignSvcAndPackageBM.setReferenceType("OPD");
			assignSvcAndPackageBM.setReferenceNumber("1001");
			
			List<AssignedPackageBM> assignedPackageBMList = new ArrayList<AssignedPackageBM>(0);
			AssignedPackageBM assignedPackageBM = new AssignedPackageBM();
			
			assignedPackageBM.setServicePackage( new CodeAndDescription("PKG2",""));
			assignedPackageBM.setEffectiveCharge(400.0);
			assignedPackageBMList.add(assignedPackageBM);
			
			List<AssignedServiceBM> assignedServiceBMList = new ArrayList<AssignedServiceBM>(0);
			
			AssignedServiceBM assignedServiceBM1 = new AssignedServiceBM();
			assignedServiceBM1.setPatientId(1);
			assignedServiceBM1.setRequestedUnits(3);
			assignedServiceBM1.setService(new CodeAndDescription("XRAY",""));
						
			AssignedServiceBM assignedServiceBM2 = new AssignedServiceBM();
			assignedServiceBM2.setPatientId(1);
			assignedServiceBM2.setRequestedUnits(1);
			assignedServiceBM2.setService(new CodeAndDescription("DENTURE",""));
			
			assignedServiceBMList.add( assignedServiceBM1 );
			assignedServiceBMList.add(assignedServiceBM2);
			
			assignSvcAndPackageBM.setAssignedServiceBMList(assignedServiceBMList);
			assignSvcAndPackageBM.setAssignedPackageBMList(assignedPackageBMList);
			serviceManager.assignSvcAndPackages(assignSvcAndPackageBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetCurrentlyAvailableServicePackages(){
		try {
			
			Util.dumpBean(serviceManager.getCurrentlyAvailableServicePackages());
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetAssignedServicesList() {
		try{
			List<AssignedServiceBM> svcList = serviceManager.getAssignedServicesList("10001", "");
			// TODO : Alok to test
			Util.dumpBean(svcList);
			if (svcList.size() > 0)
				for(AssignedServiceBM tmp: svcList)
					Util.dumpBean(tmp);
			
			assertTrue(true);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			assertTrue(false);
		}
		}

	@Test
	public void testGetServiceGroup() {
	
		try {
			List<ServiceGroupBM> serviceList = serviceManager.getServiceGroup("", "",
																			  "", "XRAY");
			
			Util.dumpBean(serviceList);
			if (serviceList.size() > 0)
				for (ServiceGroupBM tmpSvc : serviceList)
					Util.dumpBean(tmpSvc);
			
			assertTrue(true);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetServiceGroupList() {
		try {
			serviceManager.getServiceGroupList();
			assertTrue(true);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}

//	@Test
//	public void testGetServicesForSearchCriteria() {try {
//		List<ServiceBM> svcList = serviceManager.getServices("CTScan", "1001", "1001", new Double(50), new Double(300),new Boolean(true));
//		assertTrue(svcList.size() > 0);
//	} catch (RuntimeException e) {
//		
//		e.printStackTrace();
//		assertTrue(false);
//	}
//	}

	@Test
	public void testGetServicesLisAll() {
		try {
			List<CodeAndDescription> svcList = serviceManager.getServicesList();
			
			Util.dumpBean(svcList.get(0));
			
			assertTrue(svcList.size() > 0);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetServicesListForSvcGroup() {
		try {
			List<CodeAndDescription> svcList = serviceManager.getServicesOfGroup("RADIOLOGY");
			Util.dumpBean(svcList);
			assertTrue(svcList.size() > 0);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testModifyAssignedService() {
		try {
						
			AssignedServiceBM assignedServiceBM1 = new AssignedServiceBM();
			
			assignedServiceBM1.setServiceUid(5);
			assignedServiceBM1.setPatientId(1);
			assignedServiceBM1.setRequestedUnits(3);
			assignedServiceBM1.setRenderedUnits(3);
			assignedServiceBM1.setAssignedServiceStatus(new CodeAndDescription("REQUESTED",""));
			assignedServiceBM1.setReferenceNumber("122");
			assignedServiceBM1.setReferenceType("OPD");
			assignedServiceBM1.setModifiedBy("Bhavesh");
			
			AssignedServiceBM modifiedAssignedServiceBM = serviceManager.modifyAssignedService(assignedServiceBM1);
			Util.dumpBean(modifiedAssignedServiceBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testModifyAssignedPackage() {
		try {
						
			AssignedPackageBM assignedPackageBM = new AssignedPackageBM();
			
			assignedPackageBM.setPackageAsgmId(1);
			
			assignedPackageBM.setDoctorId(1);
			assignedPackageBM.setPatientId(1);
			assignedPackageBM.setReferenceType("OPD");
			assignedPackageBM.setReferenceNumber("123");
			assignedPackageBM.setAssignedPackageStatus( new CodeAndDescription("REQUESTED",""));
			assignedPackageBM.setBillNbr(11111);
			assignedPackageBM.setModifiedBy("Bhavesh");
			
			
			 serviceManager.modifyAssignedPackage(assignedPackageBM);
			Util.dumpBean(assignedPackageBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	@Test
	public void testModifyService() {
		try {
			ServiceBM serviceBM = new ServiceBM();
			
			serviceBM.setDepartment(new CodeAndDescription("RADIOLOGY_IMAGING", ""));
			serviceBM.setServiceCharge(100.0);
			serviceBM.setServiceCode("XRAY");
//			serviceBM.setServiceGroup(new CodeAndDescription("1001","Radiology"));
			serviceBM.setServiceName("X-Ray");
			serviceBM.setServiceStatus(new CodeAndDescription("ACTIVE",""));
			serviceBM.setPersonId("Bhavesh");
			serviceBM.setDepositAmount(500.0);
			serviceBM.setEffectiveFromDate(new Date());
			serviceBM.setServiceDesc("Prits Xray Film");
			serviceBM.setServiceProcedure("Service Procedure");
			serviceBM.setPersonId("Bhavesh Kumar");
			serviceManager.modifyService(serviceBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testModifyServiceGroup() {
		try {
			ServiceGroupBM modifiedServiceGroupBM = new ServiceGroupBM();
			modifiedServiceGroupBM.setGroupName("Radiology");
			modifiedServiceGroupBM.setIsActive(new Boolean(true));
			modifiedServiceGroupBM.setParentGroup(null);
			modifiedServiceGroupBM.setServiceGroupCode("RADIOLOGY");
			modifiedServiceGroupBM.setModifiedBy("Bhavesh");
			
			List<ServiceSummaryBM> serviceSummaryList = new ArrayList<ServiceSummaryBM>(0);
			
			ServiceSummaryBM serviceSummaryBM = new ServiceSummaryBM();
			serviceSummaryBM.setServiceCode("XRAY");
			ServiceSummaryBM serviceSummaryBM1 = new ServiceSummaryBM();
			serviceSummaryBM1.setServiceCode("DENTURE");
			
			serviceSummaryList.add( serviceSummaryBM );
			serviceSummaryList.add( serviceSummaryBM1 );
			modifiedServiceGroupBM.setServiceSummaryList(serviceSummaryList);
			
			serviceManager.modifyServiceGroup(modifiedServiceGroupBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	@Test
	public void testModifyServicePackage(){
		try {
			ServicePackageBM servicePackageBM = new ServicePackageBM();
			servicePackageBM.setName("Test Package2");
			servicePackageBM.setDescription("Paskage Modified by Test");
			servicePackageBM.setModifiedBy("Bhavesh");
			servicePackageBM.setServicePackageStatus(new CodeAndDescription("ACTIVE",""));
			servicePackageBM.setPackageCharge(500.0);
			servicePackageBM.setPackageId("PKG2");
			servicePackageBM.setChargeOverrideLevel("P");
			servicePackageBM.setDiscountAmountPct(10.0);
			servicePackageBM.setDiscountType("A");
			servicePackageBM.setEffectiveFromDt(new Date());
			servicePackageBM.setEffectiveCharge(400.0);
			
			List<PackageHasServiceBM> serviceBMList = new ArrayList<PackageHasServiceBM>(0);

			PackageHasServiceBM packageHasServiceBM = new PackageHasServiceBM();
			packageHasServiceBM.setService(new CodeAndDescription("XRAY",""));
			packageHasServiceBM.setEffectiveCharge(80.0);
			packageHasServiceBM.setServiceCharge(10.0);
			packageHasServiceBM.setNumberOfUnits(1);
			packageHasServiceBM.setDiscountType("A");
			packageHasServiceBM.setDiscountAmtPct(50.0);
			
			PackageHasServiceBM packageHasServiceBM1 = new PackageHasServiceBM();
			packageHasServiceBM1.setService(new CodeAndDescription("DENTURE",""));
			packageHasServiceBM1.setEffectiveCharge(800.0);
			packageHasServiceBM1.setServiceCharge(110.0);
			packageHasServiceBM1.setNumberOfUnits(1);
			packageHasServiceBM1.setDiscountType("A");
			packageHasServiceBM1.setDiscountAmtPct(50.0);
			
			serviceBMList.add( packageHasServiceBM );
			serviceBMList.add( packageHasServiceBM1 );
			
			servicePackageBM.setServiceBMList(serviceBMList);
			serviceManager.modifyServicePackage(servicePackageBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			assertTrue(false);
		}
		
	}
	
	
	@Test
	public void testModifyAssignedServiceToRendered() {
		try{
			serviceManager.modifyAssignedServiceToRendered(2, 1, "Bhavesh");
			assertTrue(true);
		}catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testRemoveAssignedServices() {
		fail("Not yet implemented");
	}

//	@Test
//	public void testRemoveService() {
//		try {
//			ServiceBM serviceBMtoRemove= new ServiceBM();
//			serviceBMtoRemove.setServiceCode("2001");
//			serviceBMtoRemove.setDepartment(new CodeAndDescription("1001", "Physiology"));
//			serviceBMtoRemove.setServiceCharge("400");
////			serviceBMtoRemove.setServiceGroup(new CodeAndDescription("1001","Radiology"));
//			serviceBMtoRemove.setServiceName("Ex-Ray");
//			serviceBMtoRemove.setServiceStatus(new CodeAndDescription("A","Active"));
//			
//					
//			serviceManager.removeService(serviceBMtoRemove);
//			assertTrue(true);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			assertTrue(false);
//		}
//	}

//	@Test
//	public void testRemoveServiceGroup() {
//		try {
//			ServiceGroupBM serviceGroupBMtoRemove = new ServiceGroupBM();
//			serviceGroupBMtoRemove.setGroupName("MRI");
//			serviceGroupBMtoRemove.setIsActive(new Boolean(true));
//			serviceGroupBMtoRemove.setParentGroupId(null);
//			serviceGroupBMtoRemove.setServiceGroupCode("1003");
////			serviceGroupBMtoRemove.setServicesList(new HashSet<ServiceBM>());
//			
//			serviceManager.removeServiceGroup(serviceGroupBMtoRemove);
//			assertTrue(true);
//		} catch (RuntimeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			assertTrue(false);
//		}
//		
//	}

	@Test
	public void testBillServiceDetails(){
		try{
			serviceManager.billServiceDetails("OPD", "1001",101, "Bhavesh");
			assertTrue(true);
		}catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetBillData(){
		try{
			Util.dumpBean( serviceManager.getBillData( 72 ));
			assertTrue(true);
		}catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testGetUbilledAssignedAssignedServies(){
		try {
			BillDataBM billDataBM = serviceManager.getUbilledAssignedServices("IPD", 3+"");
			

			
			Util.dumpBean(billDataBM);
			Map map = billDataBM.getBillDetailsMap();
			
			Set<String> itr = map.keySet();
			
			for( String key : itr ){
				
				BillingSubprocessBM billingSubprocessBM = (BillingSubprocessBM)map.get(key); 
				Util.dumpBean(billingSubprocessBM);
				
				List<BillDetailsBM>billDetailsList = billingSubprocessBM.getBillDetailsList();
				for(BillDetailsBM billDetailsBM : billDetailsList ){
					Util.dumpBean(billDetailsBM);
				}
			}
			
		
		} catch (HCISException e) {
			
			e.printStackTrace();
		}
	}

}
