/**
 * 
 */
package in.wtc.hcis.ip.test.unittest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ServiceBM;
import in.wtc.hcis.bm.ip.DoctorOrderBM;
import in.wtc.hcis.bm.ip.DoctorOrderDetailsBM;
import in.wtc.hcis.bm.ip.DoctorOrderGroupBM;
import in.wtc.hcis.bm.ip.OrderGroupTemplateAssociationBM;
import in.wtc.hcis.bm.ip.OrderTemplateBM;
import in.wtc.hcis.bm.ip.OrderTemplateSummaryBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.ip.bo.order.DoctorOrderConstants;
import in.wtc.hcis.ip.bo.order.DoctorOrderManager;
import in.wtc.hcis.ip.bo.order.OrderManager;
import in.wtc.hcis.test.unit.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wtc.hcis.ip.da.DoctorOrderGroupHasTemplate;
import com.wtc.hcis.ip.da.DoctorOrderTemplate;

import junit.framework.TestCase;

/**
 * @author Bhavesh
 *
 */
public class TestDoctorOrderManagerImpl extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	OrderManager doctorOrderManager;
	
	protected void setUp() throws Exception {
		super.setUp();
		String[] ctxFileArr = {"applicationContextTest.xml","HCISCoreContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		doctorOrderManager = (OrderManager)ctx.getBean("OrderManager");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#approveDoctorOrder(java.lang.Integer, java.lang.Integer, java.lang.String)}.
	 */
	public void testApproveDoctorOrder() {
		try{
			
			Util.dumpBean( doctorOrderManager.approveDoctorOrder(1, 1,"", "Bhavesh"));
			assertTrue(true);
		}catch(Exception e){
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createDoctorOrder(java.util.List)}.
	 */
	public void testCreateDoctorOrderListOfDoctorOrderBM() {
		try {
			DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
			
			doctorOrderBM.setDoctorId(1);
			doctorOrderBM.setPatientId(1);
			doctorOrderBM.setLastModifiedTm(new Date());
			doctorOrderBM.setDoctorOrderStatus(new CodeAndDescription("CREATED","Order has been created by a doctor"));
			doctorOrderBM.setDoctorOrderType(new CodeAndDescription("IP_ADMISSION","GENERAL"));
			doctorOrderBM.setCreatedBy("Bhavesh");
			doctorOrderBM.setDoctorOrderGroup(null);

//			doctorOrderBM.setDoctorOrderDetailsList(doctorOrderDetailsList);
			
			
			
			DoctorOrderBM doctorOrderBM1 = new DoctorOrderBM();
			
			doctorOrderBM1.setDoctorId(1);
			doctorOrderBM1.setPatientId(1);
			doctorOrderBM1.setLastModifiedTm(new Date());
			doctorOrderBM1.setDoctorOrderStatus(new CodeAndDescription("APPROVED","Order has been APPROVED"));
			doctorOrderBM1.setDoctorOrderType(new CodeAndDescription("GENERAL","GENERAL"));
			
			DoctorOrderBM[] doctorOrderBMList = new DoctorOrderBM[2];
			doctorOrderBMList[0] = (doctorOrderBM);
			doctorOrderBMList[1] = (doctorOrderBM1);

		doctorOrderManager.createDoctorOrder( doctorOrderBMList );
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createDoctorOrder(java.lang.String)}.
	 */
	public void testCreateDoctorOrderString() {
		try {
//			doctorOrderManager.createDoctorOrder("HEART_SURGERY",1,1);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	
	}

	public void testGetDoctorOrderSearched(){
		try {
			ListRange listRange = doctorOrderManager.findDoctorOrders(null ,//admissionNbr,
												null ,//patientId,
												"Ramesh  Verma",//patientName,
												null ,//doctorOrderStatusCd,
												null ,//doctorOrderTypeCd,
												null ,//orderDateFrom,
												null ,//orderDateTo,
												0 ,//start,
												10 ,//count,
												null );//orderBy
		
		Util.dumpBean(listRange);
		assertTrue(listRange != null);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createDoctorOrderForTemplates(java.util.List)}.
	 */
	public void testCreateDoctorOrderForTemplates() {
		try {
			List<String> templateIdList = new ArrayList<String>();
			
			templateIdList.add("1");
			templateIdList.add("2");
			
//			doctorOrderManager.createDoctorOrderForTemplates(templateIdList, 1, 1);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createDoctorOrderType(in.wtc.hcis.bm.base.CodeAndDescription)}.
	 */
	public void testCreateDoctorOrderType() {
		
		try {
			CodeAndDescription doctorOrder= new CodeAndDescription("IMMEDIATE","Doctor odrder to be followed immediatly");
			doctorOrderManager.createDoctorOrderType(doctorOrder);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createOrderGroup(in.wtc.hcis.bm.ip.DoctorOrderGroupBM)}.
	 */
	public void testCreateOrderGroup() {
		try {
			DoctorOrderGroupBM doctorOrderGroupBM = new DoctorOrderGroupBM();
			
			List<OrderGroupTemplateAssociationBM> orderGroupTemplateList = new ArrayList<OrderGroupTemplateAssociationBM>();
			OrderGroupTemplateAssociationBM associationBM1 = new OrderGroupTemplateAssociationBM();
			associationBM1.setOrderGroupName("FRACTURE");
			associationBM1.setOrderTemplateName("1");
			associationBM1.setSequenceNbr(1);
			
			OrderGroupTemplateAssociationBM associationBM2 = new OrderGroupTemplateAssociationBM();
			associationBM1.setOrderGroupName("FRACTURE");
			associationBM1.setOrderTemplateName("2");
			associationBM2.setSequenceNbr(1);
			
			orderGroupTemplateList.add(associationBM1);
			orderGroupTemplateList.add(associationBM2);
			
			doctorOrderGroupBM.setOrderGroupName("FRACTURE");
			doctorOrderGroupBM.setOrderGroupDesc("Group for fracture");
			doctorOrderGroupBM.setOrderGroupTemplateAssociationList(orderGroupTemplateList);
			
			doctorOrderManager.createOrderGroup(doctorOrderGroupBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#createOrderTemplate(in.wtc.hcis.bm.ip.OrderTemplateBM)}.
	 */
	public void testCreateOrderTemplate() {
		
		try {
			OrderTemplateBM orderTemplateBM = new OrderTemplateBM();
			
			orderTemplateBM.setTemplateId("5");
			orderTemplateBM.setTemplateDesc("Template Added by Test case");
			orderTemplateBM.setDoctorOrderType(new CodeAndDescription("GENERAL",""));
			
			doctorOrderManager.createOrderTemplate(orderTemplateBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#getDoctorOrderGroups(java.lang.String, java.lang.String, java.lang.Integer)}.
	 */
	public void testGetDoctorOrderGroups() {
		try {
			List<DoctorOrderGroupBM> doctorOrderList= doctorOrderManager.getDoctorOrderGroups("FRACTURE",null,null );
			
			for(DoctorOrderGroupBM groupBM : doctorOrderList)
			Util.dumpBean(groupBM);
			
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#getDoctorOrders(java.lang.Integer, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testGetDoctorOrders() {
		
		try {
			
			List<DoctorOrderBM> doctorOrderList = doctorOrderManager.getDoctorOrders( 4,null, 
			        null, null,null,null, null,null );
			
			for(DoctorOrderBM doctorOrderBM : doctorOrderList )
				Util.dumpBean(doctorOrderBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#getOrderTemplates(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)}.
	 */
	public void testGetOrderTemplates() {
		try {
/*			List<OrderTemplateSummaryBM> orderTemplatesList = doctorOrderManager.getOrderTemplates( "",null,null,null);
			
			for (OrderTemplateSummaryBM summaryBM : orderTemplatesList)
				Util.dumpBean(summaryBM);
	*/		assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
			
		}
	}

	public void testFindDoctorOrders() {
		try {
	ListRange listRange = doctorOrderManager.findDoctorOrders(1,	// admissionNbr,
															  1,  	//  patientId,
															"this",	//  patientName,
															  "df",	//  doctorOrderStatusCd,
															    "", //  doctorOrderTypeCd,
														new Date(), // orderDateFrom,
														new Date(),	//  orderDateTo,
																1,	//  start, 
																9,	//  count,
															"" );	//  orderBy)

			
		for (Object object  : listRange.getData())
				Util.dumpBean(object);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
			
		}
	}
	public void testFindDoctorOrderTemplates() {
		
		
	/*	ListRange listRange1 = doctorOrderManager.findDoctorOrderGroups("",//orderGroupName,
																		"",//orderTemplateId,
																		23,//doctorId,
																		null,//createdFromDate,
																		null,//createdToDate,
																		0,//start, 
																		10,//count, 
																		null);//orderBy)
*/		
	ListRange listRange = doctorOrderManager.findDoctorOrderTemplates(	"",   //templateId,
																		"",   //orderTypeCd,
																			1,   // doctorId,
																			null,   //serviceId,
																			null,   //departmentId,
																			0,   //start, 
																			10,   //count,
																			null);  //orderBy)
	
	}
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#getOrderTypes()}.
	 */
	public void testGetOrderTypes() {
		try {
			List<CodeAndDescription>  orderTypeList = doctorOrderManager.getOrderTypes();
			
			for(CodeAndDescription orderType : orderTypeList)
				Util.dumpBean(orderType);
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#getServicesAssociatedWithOrder(java.lang.Integer)}.
	 */
	public void testGetServicesAssociatedWithOrder() {
		try {
			List<ServiceBM> serviceRelatedWithOrderList = doctorOrderManager.getServicesAssociatedWithOrder( 1 );
			
			for(ServiceBM serviceBM : serviceRelatedWithOrderList)
				Util.dumpBean(serviceBM);
			
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#modifyDoctorOrder(in.wtc.hcis.bm.ip.DoctorOrderBM)}.
	 */
	public void testModifyDoctorOrder() {
		try {
			DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
			
			doctorOrderBM.setDoctorOrderNbr(1);
			
			doctorOrderBM.setDoctorId(1);
			doctorOrderBM.setPatientId(21);
			doctorOrderBM.setLastModifiedTm(new Date());
			doctorOrderBM.setDoctorOrderStatus(new CodeAndDescription("DISAPPROVED","Order has been created by a doctor"));
			doctorOrderBM.setDoctorOrderType(new CodeAndDescription("IP_ADMISSION","GENERAL"));
			

			doctorOrderManager.modifyDoctorOrder(doctorOrderBM);
			
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
			
		}
		
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#modifyDoctorOrderType(in.wtc.hcis.bm.base.CodeAndDescription)}.
	 */
	public void testModifyDoctorOrderType() {
		try {
			doctorOrderManager.modifyDoctorOrderType(new CodeAndDescription("GENERAL","General Doctor Order"));
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#modifyOrderGroup(in.wtc.hcis.bm.ip.DoctorOrderGroupBM)}.
	 */
	public void testModifyOrderGroup() {
		try{
		DoctorOrderGroupBM doctorOrderGroupBM = new DoctorOrderGroupBM();
		
		List<OrderGroupTemplateAssociationBM> orderGroupTemplateList = new ArrayList<OrderGroupTemplateAssociationBM>();
		OrderGroupTemplateAssociationBM associationBM1 = new OrderGroupTemplateAssociationBM();
		associationBM1.setOrderGroupName("FRACTURE");
		associationBM1.setOrderTemplateName("1");
		associationBM1.setSequenceNbr(3);
		
		OrderGroupTemplateAssociationBM associationBM2 = new OrderGroupTemplateAssociationBM();
		associationBM1.setOrderGroupName("FRACTURE");
		associationBM1.setOrderTemplateName("2");
		associationBM2.setSequenceNbr(2);
		
		orderGroupTemplateList.add(associationBM1);
		orderGroupTemplateList.add(associationBM2);
		
		doctorOrderGroupBM.setOrderGroupName("FRACTURE");
		doctorOrderGroupBM.setOrderGroupDesc("Group for Normal fracture (Only)");
		doctorOrderGroupBM.setOrderGroupTemplateAssociationList(orderGroupTemplateList);
		
		doctorOrderManager.modifyOrderGroup(doctorOrderGroupBM);
		assertTrue(true);
	} catch (RuntimeException e) {
		e.printStackTrace();
		assertTrue(false);
	}

		
		
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#modifyOrderTemplate(in.wtc.hcis.bm.ip.OrderTemplateBM)}.
	 */
	public void testModifyOrderTemplate() {
		try {
			OrderTemplateBM orderTemplateBM = new OrderTemplateBM();
			
			orderTemplateBM.setTemplateId("5");
			orderTemplateBM.setTemplateDesc("Template Modified by Test case");
			orderTemplateBM.setDoctorOrderType(new CodeAndDescription("GENERAL",""));
			
			doctorOrderManager.modifyOrderTemplate(orderTemplateBM);
			assertTrue(true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#removeDoctorOrderType(java.util.List)}.
	 */
	public void testRemoveDoctorOrderType() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#removeOrderGroup(java.lang.String)}.
	 */
	public void testRemoveOrderGroup() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#removeOrderTemplate(java.lang.String)}.
	 */
	public void testRemoveOrderTemplate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.order.DoctorOrderManagerImpl#disapproveDoctorOrder(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String)}.
	 */
	public void testDisapproveDoctorOrder() {
		try {
			DoctorOrderBM doctorOrderBM = doctorOrderManager.disapproveDoctorOrder( 4,1,"Disapproved for Testing","Bhavesh");
			Util.dumpBean(doctorOrderBM);
			
			assertTrue(doctorOrderBM != null);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
 public void testRemoveDoctorOrderList(){
	 
	 
	 try {
		List<Integer> orderNbrList = new ArrayList<Integer>();
		 orderNbrList.add(8);
		 orderNbrList.add(9);
//		 orderNbrList.add(10);
		 orderNbrList.add(11);
//		 assertTrue(doctorOrderManager.removeDoctorOrderList(orderNbrList));
	} catch (RuntimeException e) {
		e.printStackTrace();
		assertTrue(false);
	}
 
 }
 
 public void testgetOrderTypeHasAttribute() {
		try {
			
			doctorOrderManager.getOrderTypeHasAttribute("IP_ADMISSION" , 0, 100, "");
			assertTrue(true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
 
 public void testCreateDoctorOrderWithIndividualSelection() {
	 try {
		 DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
		 doctorOrderBM.setDoctorId(new Integer(1));
		 doctorOrderBM.setPatientId(new Integer(2));
		 doctorOrderBM.setDoctorOrderStatus(new CodeAndDescription(DoctorOrderConstants.ORDER_STATUS_CREATED,""));
		 doctorOrderBM.setDoctorOrderType(new CodeAndDescription(DoctorOrderConstants.ORDER_TYPE_IP_ADMISSION,""));
		 doctorOrderBM.setOrderCreationDate(Calendar.getInstance().getTime());
		 doctorOrderBM.setOrderDesc("For testing purpose");
		 doctorOrderBM.setOrderDictation("Test");
		 
		 List<DoctorOrderDetailsBM> doctorOrderDetilsList = new ArrayList<DoctorOrderDetailsBM>(0); 
		 
		 DoctorOrderDetailsBM doctorOrderDetailsBM = new DoctorOrderDetailsBM();
		 doctorOrderDetailsBM.setSequenceNbr(new Integer(1));
		 doctorOrderDetailsBM.setSubSequenceNbr(new Integer(1));
		 doctorOrderDetailsBM.setServiceCode("DENTURE");
		 doctorOrderDetailsBM.setActionDesc("For Testing");
		 
		 doctorOrderDetilsList.add(doctorOrderDetailsBM);
		 
		 DoctorOrderDetailsBM doctorOrderDetailsBM1 = new DoctorOrderDetailsBM();
		 doctorOrderDetailsBM1.setSequenceNbr(new Integer(2));
		 doctorOrderDetailsBM1.setSubSequenceNbr(new Integer(1));
		 doctorOrderDetailsBM1.setPackageIndicator("Y");
		 doctorOrderDetailsBM1.setServicePackage(new CodeAndDescription("PKG2",""));
		 doctorOrderDetailsBM1.setActionDesc("For Testing");
		 
		 doctorOrderDetilsList.add(doctorOrderDetailsBM1);
		 
		 Map<String, String> specificAttributesList = new HashMap<String, String>(0);
		 specificAttributesList.put(DoctorOrderConstants.NURSING_UNIT_TYPE_CD_ATTR , "GENERAL");
		 doctorOrderBM.setCreatedBy("Vinay");
		 doctorOrderBM.setOrderSpecificAttributes(specificAttributesList);
		 
		 doctorOrderBM.setDoctorOrderDetailsList(doctorOrderDetilsList);
		 
		 DoctorOrderBM [] doctorOrderBMList = new DoctorOrderBM[1];
		 doctorOrderBMList[0] = doctorOrderBM;
		 
		 doctorOrderManager.createDoctorOrder(doctorOrderBMList);
		 
		assertTrue(true);
	} catch (Exception e) {
		e.printStackTrace();
		assertTrue(false);
	}
 }
 
 public void testCreateDoctorOrderWithOrderTemplate(){
	 try {
		 DoctorOrderBM doctorOrderBM = new DoctorOrderBM();
		 doctorOrderBM.setDoctorId(new Integer(1));
		 doctorOrderBM.setPatientId(new Integer(2));
		 doctorOrderBM.setDoctorOrderStatus(new CodeAndDescription(DoctorOrderConstants.ORDER_STATUS_CREATED,""));
		 doctorOrderBM.setDoctorOrderType(new CodeAndDescription(DoctorOrderConstants.ORDER_TYPE_IP_ADMISSION,""));
		 doctorOrderBM.setOrderCreationDate(Calendar.getInstance().getTime());
		 doctorOrderBM.setOrderDesc("For testing purpose");
		 doctorOrderBM.setOrderDictation("Test");
		 
		 doctorOrderBM.setDoctorOrderTemplate(new CodeAndDescription("",""));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 
 }

}
