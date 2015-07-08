package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import in.wtc.hcis.bm.base.BrandBM;
import in.wtc.hcis.bo.medicine.BrandManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBrandManagerImpl {
	
	BrandManager brandManager;

	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);

			brandManager = (BrandManager)ctx.getBean("BrandManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testAddBrand() 
	{
		try
		{
			BrandBM brandBM = new BrandBM();
			brandBM.setBrandCode( "112" );
			brandBM.setDescription( "WTC@HCIS" );
			brandBM.setActive( true );
			

			brandManager.addBrand( brandBM );
			
			TestUtils.dumpBean( brandBM );
			assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testModifyBrand()
	{
		try
		{
			BrandBM brandBM = new BrandBM();
			brandBM.setBrandCode( "112" );
			brandBM.setDescription( "WTC" );
			brandBM.setActive( true );
			

			BrandBM brand = brandManager.modifyBrand(brandBM);
			
			TestUtils.dumpBean( brand );
			assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testRemoveBrand()
	{
		try
		{
			List<String> brandCodeList = new ArrayList<String>();
			brandCodeList.add( "112" );
			brandCodeList.add( "ALKE" );
			
			brandManager.removeBrand( brandCodeList );
			TestUtils.dumpBean( brandCodeList );
			assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetBrand()
	{
		try
		{
			List<BrandBM> brandBMList = new ArrayList<BrandBM>(); 
			brandBMList = brandManager.getBrand(null, null, false);
			TestUtils.dumpBean(brandBMList);
			assertTrue(true);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
