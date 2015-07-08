/**
 * 
 */
package in.wtc.hcis.ip.test.unittest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.ip.ClaimDocumentBM;
import in.wtc.hcis.bm.ip.ClaimRequestBM;
import in.wtc.hcis.bm.ip.InsurancePlanBM;
import in.wtc.hcis.bm.ip.InsurerBM;
import in.wtc.hcis.bm.ip.PlanCoversDiseaseBM;
import in.wtc.hcis.bm.ip.SponsorDetailsBM;
import in.wtc.hcis.bm.ip.SponsorInsurerAssociationBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.bo.insurance.InsuranceManager;
import in.wtc.hcis.test.unit.Util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import junit.framework.TestCase;

/**
 * @author Bhavesh
 *
 */
public class TestInsuranceManagerImpl extends TestCase {

	InsuranceManager insuranceManager;
	protected  void   setUp() throws Exception {
//		super.setUp();

		String[] ctxFileArr = {"IPapplicationContext.xml","AccountantContextBLTest.xml","HCISCoreContext.xml","IPManagementContext.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(ctxFileArr);
		insuranceManager = (InsuranceManager)ctx.getBean("InsuranceManager");

	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#createClaimSponsor(in.wtc.hcis.bm.ip.SponsorDetailsBM)}.
	 */
	public void testCreateClaimSponsor() {
		
		try {
			SponsorDetailsBM sponsorDetailsBM = new SponsorDetailsBM();
			
			sponsorDetailsBM.setCreditClass(new CodeAndDescription("SILVER",""));
			sponsorDetailsBM.setSponsorsName("Spledind");
			sponsorDetailsBM.setSponsorType(new CodeAndDescription("TPA",""));
			sponsorDetailsBM.setModifiedBy("Bhavesh");
			sponsorDetailsBM.setSponsorDesc("");
			sponsorDetailsBM.setCreatedBy("Bhavesh");
			ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
			
			contactDetailsBM.setCity( "Hyderabad" );
			contactDetailsBM.setCountry(new CodeAndDescription("IN", "") );
			contactDetailsBM.setEmail("email@mail.com");
			contactDetailsBM.setFaxNumber("1234567890");
			contactDetailsBM.setFirstName("Rajesh");
			contactDetailsBM.setLastName("Sharma");
			contactDetailsBM.setMobileNumber("98987654321");
			contactDetailsBM.setPincode("432345");
			contactDetailsBM.setState(new CodeAndDescription("AP",""));

			sponsorDetailsBM.setContactDetailsBM(contactDetailsBM);
			
			List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = new ArrayList<SponsorInsurerAssociationBM>();
			SponsorInsurerAssociationBM associationBM = new SponsorInsurerAssociationBM();
			
			associationBM.setInsurerName("Spledind");
			associationBM.setSponsorName("Spledind");
			associationBM.setEffectiveFromDate(new Date());
			sponsorInsurerAssociationBMList.add(associationBM);
			
			
//			sponsorDetailsBM.setSponsorInsurerAssociationBMList(sponsorInsurerAssociationBMList);
			insuranceManager.createClaimSponsor(sponsorDetailsBM);
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
		
		
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#createSponsorInsurerAssociation(java.util.List)}.
	 */
	public void testCreateSponsorInsurerAssociation() {
		fail("Not yet implemented");
	}

	

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#createClaimRequestActivity(in.wtc.hcis.bm.ip.ClaimRequestActivityBM)}.
	 */
	public void testCreateClaimRequestActivity() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#createInsurancePlan(in.wtc.hcis.bm.ip.InsurancePlanBM)}.
	 */
	public void testCreateInsurancePlan() {
		try {
			InsurancePlanBM insurancePlanBM = new InsurancePlanBM();
			
			insurancePlanBM.setPlanCode("LIC_JEENAN");
			insurancePlanBM.setPlanName("JEEVAN JYOTI");
			insurancePlanBM.setInsurerName("Paramount");
			insurancePlanBM.setTotalCoverageAmt(1000000.00);
			insurancePlanBM.setPercentAbsInd("A");
			insurancePlanBM.setValidFromDt( new Date());
			
			
			PlanCoversDiseaseBM planCoversDiseaseBM = new PlanCoversDiseaseBM();
			
			planCoversDiseaseBM.setPlanName(new CodeAndDescription("LIC_JEENAN",""));
			planCoversDiseaseBM.setCoverageAmt(100.00);
			planCoversDiseaseBM.setDiseaceName(new CodeAndDescription("MALARIA",""));
			planCoversDiseaseBM.setCreatedBy("Bhavesh");
			planCoversDiseaseBM.setPercentAbsInd("P");
			
			PlanCoversDiseaseBM planCoversDiseaseBM2 = new PlanCoversDiseaseBM();

			planCoversDiseaseBM2.setPlanName(new CodeAndDescription("LIC_JEENAN",""));
			planCoversDiseaseBM2.setCoverageAmt(100.00);
			planCoversDiseaseBM2.setDiseaceName(new CodeAndDescription("INFLUENZA",""));
			planCoversDiseaseBM2.setCreatedBy("Bhavesh");
			planCoversDiseaseBM2.setPercentAbsInd("P");
			
			List<PlanCoversDiseaseBM> planCoversDiseaseBMList = new ArrayList<PlanCoversDiseaseBM>();
			
			planCoversDiseaseBMList.add(planCoversDiseaseBM);
			planCoversDiseaseBMList.add(planCoversDiseaseBM2);
			
			insurancePlanBM.setPlanCoversDiseaseBMList(planCoversDiseaseBMList);

			insuranceManager.createInsurancePlan(insurancePlanBM);
			
			assertTrue(true);
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#createClaimRequest(in.wtc.hcis.bm.ip.ClaimRequestBM)}.
	 */
	public void testCreateClaimRequest() {
		
		try {
			ClaimRequestBM claimRequestBM = new ClaimRequestBM();
			
			claimRequestBM.setAdmissionReqNbr(1);
			claimRequestBM.setClaimStatusCd(new CodeAndDescription("CREATED","" ));
			claimRequestBM.setCreatedBy("Bhavesh");
			claimRequestBM.setCreateDtm(new Date());
			claimRequestBM.setSponsorName("Paramount");
			claimRequestBM.setPlanCode("LIC_JEENAN");
			claimRequestBM.setClaimSubsequenceNbr(2);
			
			Date policyEffectiveFromDt = new Date();
			policyEffectiveFromDt.setDate(10);
			policyEffectiveFromDt.setMonth(5);
			policyEffectiveFromDt.setYear(2009-1900);
			claimRequestBM.setPolicyEffectiveFromDt(policyEffectiveFromDt);
			
			claimRequestBM.setPolicyNbr("12345");
			claimRequestBM.setRequestedAmount( 20000.00 );

			List<ClaimDocumentBM> claimDocumentBMList = new ArrayList<ClaimDocumentBM>();
			
			ClaimDocumentBM claimDocumentBM = new ClaimDocumentBM();
			claimDocumentBM.setDocumentName("Doctor prescription");
			claimDocumentBM.setDocumentPath("C:\\MyDocument");
			claimDocumentBM.setCreatedBy("Bhavesh");
			
			ClaimDocumentBM claimDocumentBM2 = new ClaimDocumentBM();
			claimDocumentBM2.setDocumentName("Cost Estimation Details");
			claimDocumentBM2.setDocumentPath("C:\\MyDocument");
			claimDocumentBM2.setCreatedBy("Bhavesh");
			
			claimDocumentBMList.add( claimDocumentBM );
			claimDocumentBMList.add(claimDocumentBM2);
			claimRequestBM.setClaimDocumentBMList(claimDocumentBMList); 
			
			InsurerBM insurerBM =  new InsurerBM();
			
			insurerBM.setInsurerName("Max Newyork");
			
			claimRequestBM.setInsurerBM(insurerBM);
			
			insuranceManager.createClaimRequest(claimRequestBM);
			assertTrue(true);
			
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#getClaimRequestActivities(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testGetClaimRequestActivities() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#getClaimRequests(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.util.Date, java.util.Date, java.util.Date)}.
	 */
	public void testGetClaimRequests() {
		
		
		
			try {
				ListRange claimRequestBMList = insuranceManager.findClaimRequests(null,		//requestSequenceNbr,
																							null,		//admissionNumber,
																							null,		//patientId,
																							"Ramesh",	//patientName,
																							null,		//sponsorType,
																							null,		//sponsorName, 
																							null,
																							null,
																							null,		//policyNumber,
																							null,		//claimStatusCode,
																							null,		//claimRequestCreationFromDt,
																							null,		//claimRequestCreationToDt,
																							null,		//claimApprovalFromDt, 
																							null,
																							0,
																							10,
																							null);		//claimApprovalToDt)


					for( ClaimRequestBM claimRequestBM : (ClaimRequestBM[])claimRequestBMList.getData()){
						
						Util.dumpBean(claimRequestBM);
					}
			assertTrue(true);
			} catch (HCISException e) {
				e.printStackTrace();
				assertTrue(false);
			}
	
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#getClaimRequestsForAnAdmission(java.lang.Integer)}.
	 */
	public void testGetClaimRequestsForAnAdmission() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#getInsurancePlans(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Double, java.lang.Double)}.
	 */
	public void testGetInsurancePlans() {
		
		try {
			List<InsurancePlanBM> insurancePlanBMList = insuranceManager.getInsurancePlans( null,	//planCode,
																							null,	//sponsorName,
																							"jeevan",	//planName, 
																							null,	//coverageAmntFrom,
																							null,	//coverageAmntTo)
																							null);
			for( InsurancePlanBM insurancePlanBM : insurancePlanBMList ){
				
				Util.dumpBean( insurancePlanBM );
			}
			
			assertTrue(!insurancePlanBMList.isEmpty());
		} catch (HCISException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#getSponsors(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	public void testGetSponsors() {
		
		
	}
	
	public void test(){
		int[] arr = {11,22,33,44,55};
		
		prn(arr,arr.length);
	}
	void prn(int a[],int lenght){
	if(lenght !=0){
		System.out.println(a[lenght-1]);
		prn( a, lenght-1);
	}
}
	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#modifyClaimRequests(in.wtc.hcis.bm.ip.ClaimRequestBM, java.lang.String)}.
	 */
	public void testModifyClaimRequests() {
		
		
		
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#modifyInsurancePlan(in.wtc.hcis.bm.ip.InsurancePlanBM)}.
	 */
	public void testModifyInsurancePlan() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link in.wtc.hcis.ip.bo.insurance.InsuranceManagerImpl#modifyClaimSponsor(in.wtc.hcis.bm.ip.SponsorDetailsBM)}.
	 */
	public void testModifyClaimSponsor() {
		fail("Not yet implemented");
	}

	public void testFindSponsor(){
		
		try {
			ListRange result = insuranceManager.findSponsors("","","","","","ICICI Lombard Insurance",0,10,"contactPerson asc");
//				creditClassCode, accountNumber, insurerName, start, count, orderBy);
			
			for( int i = 0; i<result.getTotalSize();i++ ){
				Util.dumpBean(result.getData()[i]);
				Util.dumpBean(((SponsorDetailsBM)result.getData()[i]).getContactDetailsBM());
			}
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	

	public void testGetInsurer(){
		
		try {
			ListRange result = insuranceManager.findInsurer("","",null,"",0,10,"contactPerson asc");
//(insurerName, insurerDescription, insurancePlanCd, sponsorName, start, count, orderBy)
			for( int i = 0; i<result.getTotalSize();i++ ){
				Util.dumpBean(result.getData()[i]);
				Util.dumpBean(((InsurerBM)result.getData()[i]).getContactDetailsBM());
			}
			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
}
