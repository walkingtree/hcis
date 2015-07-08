package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.MedicineBM;
import in.wtc.hcis.bo.medicine.MedicineManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMedicineManagerImpl {
	
	MedicineManager medicineManager;

	@Before
	public void setUp() throws Exception {
		try {
			String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
			ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);

			medicineManager = ( MedicineManager )ctx.getBean("MedicineManager");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testAddMedicine() 
	{
		try
		{
			MedicineBM medicineBM = new MedicineBM();
			medicineBM.setMedicineCode( "1113" );
			medicineBM.setMedicineName( "crocin" );
			medicineBM.setBrand( new CodeAndDescription("111","WTC") );
			medicineBM.setStrength( "50mg" );
			medicineBM.setMedicineType( new CodeAndDescription( "TABLET","Tablet" ) );
//			medicineBM.setMedicineType( new CodeAndDescription( "CAPSULE","Capsule" ) );
//			medicineBM.setMedicineType( new CodeAndDescription( "SYRUP","Syrup" ) );
			medicineBM.setMaximumDosage( "2 a day" );
			medicineBM.setActive( true );
			
			medicineManager.addMedicine( medicineBM );
			TestUtils.dumpBean( medicineBM );
			assertTrue( true );
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetMedicine() 
	{
		try
		{
			List<MedicineBM> medicineBMList = new ArrayList<MedicineBM>();
			medicineBMList = medicineManager.getMedicine(null, null, null, null, null, null, true);
			TestUtils.dumpBean(medicineBMList);
			assertTrue( true);
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testModifyMedicine() 
	{
		try
		{
			MedicineBM modifiedMedicineBM = new MedicineBM();
			modifiedMedicineBM.setMedicineCode( "1111" );
			modifiedMedicineBM.setMedicineName( "Crocin Pain Relief" );
			modifiedMedicineBM.setBrand( new CodeAndDescription("112","WTC@HCIS") );
			modifiedMedicineBM.setStrength( "500mg" );
			modifiedMedicineBM.setMedicineType( new CodeAndDescription( "TABLET","Tablet" ) );
//			modifiedMedicineBM.setMedicineType( new CodeAndDescription( "CAPSULE","Capsule" ) );
//			medicineBM.setMedicineType( new CodeAndDescription( "SYRUP","Syrup" ) );
			modifiedMedicineBM.setMaximumDosage( "2 a day" );
			modifiedMedicineBM.setActive( true );
			
			MedicineBM medicineBM = medicineManager.modifyMedicine(modifiedMedicineBM);
			TestUtils.dumpBean( medicineBM );
			assertTrue( true );
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	@Test
	public void testRemoveMedicine() 
	{
		try
		{
			List<String> medicineCodeList = new ArrayList<String>();
			medicineCodeList.add( "1111" );
			medicineCodeList.add( "1112" );
			medicineCodeList.add( "1114" );
			
			medicineManager.removeMedicine(medicineCodeList);
			TestUtils.dumpBean( medicineCodeList );
			assertTrue( true );
		}catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
