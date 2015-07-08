package in.wtc.billing.test.unit;

import java.util.Iterator;
import java.util.List;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillObjectBM;
import in.wtc.billing.bo.BillManagerImpl;
import in.wtc.billing.exception.BillException;
import in.wtc.billing.util.Utils;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBillManager {
	
	private BillManagerImpl billManagerImpl;
	
	private String accountId = "100001";
	
	@Before
	public void setUp() throws Exception {
		try {
//			String[] ctxFileArr = {"applicationContext.xml","AccountantContextDA.xml","AccountantContextBL.xml","BillingContext.xml","TestMergedOPIPapplicationContext.xml","HCISCoreContext.xml","IPManagementContext.xml"};
			String[] ctxFileArr = {"TestapplicationContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
			billManagerImpl = (BillManagerImpl)ctx.getBean("BillManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Test
	public void testGetBillDetails() {
		try {
			BillObjectBM billObjectBM = billManagerImpl.getBillDetails(129l);
			
			Utils.dumpBean(billObjectBM);
			assertTrue(true);
		} catch (BillException e) {
			assertTrue(false);
			e.printStackTrace();
		}
		
	}

	@Test
	public void testGetBillDetailsString() {
		List<BillObjectBM>billObjectList = billManagerImpl.getBillDetails("100001");
		
		if ( billObjectList != null && billObjectList.size() > 0 ) {
			for ( BillObjectBM billObjectBM : billObjectList ) {
				Utils.dumpBean(billObjectBM);
				
				List<BillDataBM>billDataBMList = billObjectBM.getBillDataList();
				if ( billDataBMList != null ) {
					for ( BillDataBM billDataBM : billDataBMList ) {
						Utils.dumpBean(billDataBM);
					}
				}
			}
		}
	}

	@Test
	public void testGetBillOverview() {
		try {
			Utils.dumpBean( billManagerImpl.getBillOverview(67l));
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetBillSummary() {
		
		try {
			Iterator iterator = billManagerImpl.getBillSummary(this.accountId).iterator();
			while (iterator.hasNext()) {
				Utils.dumpBean(iterator.next() );
				assertTrue(true);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testPrintDuplicateBill() {
		
		try {
			Utils.dumpBean(billManagerImpl.printDuplicateBill(29l));
			assertTrue(true);
		} catch (BillException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testRunBill() {
		try {
			BillObjectBM billObjectBM = billManagerImpl.runBill("1000005", "EMR", "" );
			
			Utils.dumpBean(billObjectBM);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetBillDataForAProcess() {
		try {
			BillDataBM billDataBM = billManagerImpl.getBillDataForAProcess(53l, "ACCOUNTING");
			
			Utils.dumpBean(billDataBM);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	@Test
	public void testGetCurrentlyRegisteredProcesses() {
		try {
			List<String> processes = billManagerImpl.getCurrentlyRegisteredProcesses();
			
			for( String process : processes){
				System.out.println(process);
				
			}
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

}
