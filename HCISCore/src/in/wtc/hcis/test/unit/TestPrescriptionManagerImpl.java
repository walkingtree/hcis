package in.wtc.hcis.test.unit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.PrescriptionBM;
import in.wtc.hcis.bm.base.PrescriptionLineItemBM;
import in.wtc.hcis.bo.doctor.DoctorManager;
import in.wtc.hcis.bo.prescription.PrescriptionManager;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPrescriptionManagerImpl {
    
	 PrescriptionManager prescriptionManager;
	@Before
	public void setUp() throws Exception {
		String[] ctxFileArr = {"applicationContext.xml","HCISCoreContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		prescriptionManager = (PrescriptionManager)ctx.getBean("PrescriptionManager");
	}

	@Test
	public void testAddPrescription() {
		try {
			List<PrescriptionBM> prescriptionBMList = new ArrayList<PrescriptionBM>();
			PrescriptionBM prescriptionBM = new PrescriptionBM();
			prescriptionBM.setAppointmentNumber("20");
			prescriptionBM.setPrescriptionText("Get X-ray and CTscan");
			
			Set<PrescriptionLineItemBM> prescriptionLineItemSet = new HashSet<PrescriptionLineItemBM>();
			PrescriptionLineItemBM prescriptionLineItem = new PrescriptionLineItemBM();
			prescriptionLineItem.setDosage("2times");
			prescriptionLineItem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineItem.setMedicineCode("1");
			prescriptionLineItem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineItem.setRemarks("test");
			prescriptionLineItem.setRepeats("after one Week");
			prescriptionLineItem.setStrength("200mg");
			
			prescriptionLineItemSet.add(prescriptionLineItem);
			
			PrescriptionLineItemBM prescriptionLineitem = new PrescriptionLineItemBM();
			prescriptionLineitem.setDosage("2times");
			prescriptionLineitem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineitem.setMedicineCode("2");
			prescriptionLineitem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineitem.setRemarks("test");
			prescriptionLineitem.setRepeats("after one Week");
			prescriptionLineitem.setStrength("200mg");
			
			prescriptionLineItemSet.add(prescriptionLineitem);
			
			prescriptionBM.setPrescriptionLineItemSet(prescriptionLineItemSet);
			
			prescriptionBMList.add(prescriptionBM);
			prescriptionManager.addPrescription(prescriptionBMList);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testGetMedicalPrescriptions() {
		try {
			List<PrescriptionBM> prescriptionList = prescriptionManager.getMedicalPrescriptions(new Integer("20"));
			Util.dumpBean(prescriptionList.get(0).getPrescriptionLineItemSet().toArray()[0]);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testModifyMedicalPrescription() {
		try {
			PrescriptionBM prescriptionBM = new PrescriptionBM();
			prescriptionBM.setAppointmentNumber("20");
			prescriptionBM.setPrescriptionNumber("1");
			prescriptionBM.setPrescriptionText("Get CTSCAN and CTscan");
			
			Set<PrescriptionLineItemBM> prescriptionLineItemSet = new HashSet<PrescriptionLineItemBM>();
			PrescriptionLineItemBM prescriptionLineItem = new PrescriptionLineItemBM();
			prescriptionLineItem.setDosage("3times");
			prescriptionLineItem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineItem.setMedicineCode("1");
			prescriptionLineItem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineItem.setRemarks("blood test");
			prescriptionLineItem.setRepeats("after three Week");
			prescriptionLineItem.setStrength("300mg");
			
			prescriptionLineItemSet.add(prescriptionLineItem);
			
			PrescriptionLineItemBM prescriptionLineitem = new PrescriptionLineItemBM();
			prescriptionLineitem.setDosage("4times");
			prescriptionLineitem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineitem.setMedicineCode("2");
			prescriptionLineitem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineitem.setRemarks("test1");
			prescriptionLineitem.setRepeats("after two Week");
			prescriptionLineitem.setStrength("500mg");
			
			prescriptionLineItemSet.add(prescriptionLineitem);
			
			prescriptionBM.setPrescriptionLineItemSet(prescriptionLineItemSet);
			
			PrescriptionBM modifiedPrescriptionBM =prescriptionManager.modifyMedicalPrescription(prescriptionBM);
			Util.dumpBean(modifiedPrescriptionBM);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

	@Test
	public void testRemovePrescription() {
		try {
			List<PrescriptionBM> prescriptionBMList = new ArrayList<PrescriptionBM>();
			PrescriptionBM prescriptionBM = new PrescriptionBM();
			prescriptionBM.setAppointmentNumber("20");
			prescriptionBM.setPrescriptionNumber("1");
			prescriptionBM.setPrescriptionText("Get X-ray and CTscan");
			
			Set<PrescriptionLineItemBM> prescriptionLineItemSet = new HashSet<PrescriptionLineItemBM>();
			PrescriptionLineItemBM prescriptionLineItem = new PrescriptionLineItemBM();
			prescriptionLineItem.setDosage("2times");
			prescriptionLineItem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineItem.setMedicineCode("1");
			prescriptionLineItem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineItem.setRemarks("test");
			prescriptionLineItem.setRepeats("after one Week");
			prescriptionLineItem.setStrength("200mg");
			
			prescriptionLineItemSet.add(prescriptionLineItem);
			
			PrescriptionLineItemBM prescriptionLineitem = new PrescriptionLineItemBM();
			prescriptionLineitem.setDosage("2times");
			prescriptionLineitem.setForm(new CodeAndDescription("1","Tablet"));
			prescriptionLineitem.setMedicineCode("2");
			prescriptionLineitem.setPrescriptionDate(Calendar.getInstance().getTime());
			prescriptionLineitem.setRemarks("test");
			prescriptionLineitem.setRepeats("after one Week");
			prescriptionLineitem.setStrength("200mg");
			
			prescriptionLineItemSet.add(prescriptionLineitem);
			
			prescriptionBM.setPrescriptionLineItemSet(prescriptionLineItemSet);
			
			prescriptionBMList.add(prescriptionBM);
			prescriptionManager.removePrescription(prescriptionBMList);
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

}
