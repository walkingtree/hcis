package in.wtc.hcis.ip.bo.insurance;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.billing.bm.BillDetailsBM;
import in.wtc.billing.bm.BillingSubprocessBM;
import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.ReceivableBM;
import in.wtc.hcis.bm.ip.AdmissionLightBM;
import in.wtc.hcis.bm.ip.ClaimDocumentBM;
import in.wtc.hcis.bm.ip.ClaimRequestActivityBM;
import in.wtc.hcis.bm.ip.ClaimRequestBM;
import in.wtc.hcis.bm.ip.InsurancePlanBM;
import in.wtc.hcis.bm.ip.InsurerBM;
import in.wtc.hcis.bm.ip.PlanCoversDiseaseBM;
import in.wtc.hcis.bm.ip.PlanHasServicesBM;
import in.wtc.hcis.bm.ip.SponsorDetailsBM;
import in.wtc.hcis.bm.ip.SponsorInsurerAssociationBM;
import in.wtc.hcis.bm.ip.SponsorSlaBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;
import in.wtc.hcis.ip.common.IPDataModelConverter;
import in.wtc.hcis.ip.common.IPDataModelManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.ContactDetails;
import com.wtc.hcis.da.ContactDetailsDAO;
import com.wtc.hcis.da.Disease;
import com.wtc.hcis.da.DiseaseRequiresServiceDAO;
import com.wtc.hcis.da.EntityAcctMap;
import com.wtc.hcis.da.EntityAcctMapDAO;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.Service;
import com.wtc.hcis.da.extn.DiseaseDAOExtn;
import com.wtc.hcis.ip.da.ActivityType;
import com.wtc.hcis.ip.da.Admission;
import com.wtc.hcis.ip.da.AdmissionClaimActivity;
import com.wtc.hcis.ip.da.AdmissionClaimActivityId;
import com.wtc.hcis.ip.da.AdmissionClaimRequest;
import com.wtc.hcis.ip.da.AdmissionDAO;
import com.wtc.hcis.ip.da.ClaimDocument;
import com.wtc.hcis.ip.da.ClaimDocumentDAO;
import com.wtc.hcis.ip.da.ClaimDocumentId;
import com.wtc.hcis.ip.da.ClaimSponsor;
import com.wtc.hcis.ip.da.ClaimSponsorSla;
import com.wtc.hcis.ip.da.ClaimSponsorSlaDAO;
import com.wtc.hcis.ip.da.ClaimSponsorSlaId;
import com.wtc.hcis.ip.da.InsurancePlans;
import com.wtc.hcis.ip.da.Insurer;
import com.wtc.hcis.ip.da.PlanCoversDisease;
import com.wtc.hcis.ip.da.PlanCoversDiseaseDAO;
import com.wtc.hcis.ip.da.PlanCoversDiseaseId;
import com.wtc.hcis.ip.da.PlanHasServices;
import com.wtc.hcis.ip.da.PlanHasServicesDAO;
import com.wtc.hcis.ip.da.PlanHasServicesId;
import com.wtc.hcis.ip.da.SponsorClaimStatus;
import com.wtc.hcis.ip.da.SponsorInsurerAssociation;
import com.wtc.hcis.ip.da.SponsorInsurerAssociationDAO;
import com.wtc.hcis.ip.da.SponsorInsurerAssociationId;
import com.wtc.hcis.ip.da.extn.AdmissionClaimActivityDAOExtn;
import com.wtc.hcis.ip.da.extn.AdmissionClaimRequestDAOExtn;
import com.wtc.hcis.ip.da.extn.ClaimSponsorDAOExtn;
import com.wtc.hcis.ip.da.extn.InsurancePlansDAOExtn;
import com.wtc.hcis.ip.da.extn.InsurerDAOExtn;

/**
 * @author Alok Ranjan This module implements logic for managing insurance
 *         handling by a hospital
 * 
 */
public class InsuranceManagerImpl implements InsuranceManager {

	private IPDataModelConverter converter;
	private IPDataModelManager dataModelManager;
	private DataModelManager coreDataModelManager;
	
	private ClaimSponsorDAOExtn claimSponsorDAO;
	private InsurancePlansDAOExtn insurancePlansDAO;
	private PlanCoversDiseaseDAO planCoversDiseaseDAO;
	private PlanHasServicesDAO planHasServicesDAO;
	private AdmissionClaimRequestDAOExtn admissionClaimRequestDAO;
	private AdmissionClaimActivityDAOExtn admissionClaimActivityDAO;
	private ContactDetailsDAO contactDetailsDAO;
	private SponsorInsurerAssociationDAO sponsorInsurerAssociationDAO;
	private ClaimSponsorSlaDAO claimSponsorSlaDAO;
	private ClaimDocumentDAO claimDocumentDAO;
	
	private DiseaseDAOExtn diseaseDAO;
	private DiseaseRequiresServiceDAO diseaseRequiresServiceDAO;
	private InsurerDAOExtn insurerDAO;
	private AdmissionDAO admissionDAO;
	private EntityAcctMapDAO entityAcctMapDAO;

	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();
	
	/**
	 * This method creates new claim sponsor.
	 * 
	 */
	public SponsorDetailsBM createClaimSponsor( SponsorDetailsBM sponsorDetailsBM )
			throws HCISException {

		   try {
			
			ClaimSponsor claimSponsor = converter.convertClaimSponsorBM2DM(sponsorDetailsBM, null);
			
			ContactDetailsBM contactDetailsBM = sponsorDetailsBM.getContactDetailsBM();
			
			if( contactDetailsBM != null ){
				
				contactDetailsBM.setCountry(new CodeAndDescription(InsuranceManagementConstants.SPONSOR_DEFAULT_COUNTRY_CD,""));
				ContactDetails contactDetails = converter.convertContactDetalisBM2DM(contactDetailsBM, null);
				contactDetails.setCreateDtm(new Date());
				contactDetails.setCreatedBy( sponsorDetailsBM.getCreatedBy() );
				contactDetailsDAO.save(contactDetails);
				claimSponsor.setContactCode(contactDetails.getContactCode());
			}
		    claimSponsor.setCreatedBy( sponsorDetailsBM.getCreatedBy() );
		    claimSponsor.setCreatedDtm( new Date() );

		    //Create account for sponsor and store it
		    String	accountId = createAccountForSponsor(sponsorDetailsBM);  
		    
		    claimSponsor.setAccountNumber(accountId);
		    claimSponsorDAO.save(claimSponsor);
		    
		    
		    //  Create SLA entry
		    List<SponsorSlaBM> sponsorSlaBMList = sponsorDetailsBM.getSponsorSlaBMList();
		    if( sponsorSlaBMList != null && !sponsorSlaBMList.isEmpty()){
			   for(SponsorSlaBM sponsorSlaBM : sponsorSlaBMList ){
				   ClaimSponsorSla claimSponsorSla = new ClaimSponsorSla();

				   ClaimSponsorSlaId slaId = new ClaimSponsorSlaId();
				   slaId.setActivityTypeCd( sponsorSlaBM.getActivityType().getCode() );
				 
				   if( sponsorSlaBM.getSponsorName() != null && !sponsorSlaBM.getSponsorName().isEmpty() ){
					   slaId.setSponsorName( sponsorSlaBM.getSponsorName() );
				   }else{
					   slaId.setSponsorName( claimSponsor.getSponsorsName() );   
				   }
				   
				   claimSponsorSla.setId( slaId );
				   claimSponsorSla.setSlaPeriod( sponsorSlaBM.getSlaPeriod() );
				   claimSponsorSla.setPeriodUnit( sponsorSlaBM.getSlaUnit() );
				   claimSponsorSlaDAO.save( claimSponsorSla );
			   }
		    }
		   
		   
		   List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = sponsorDetailsBM.getSponsorInsurerAssociationBMList();
		   
		   if ( sponsorInsurerAssociationBMList != null && !sponsorInsurerAssociationBMList.isEmpty()){
			   for( SponsorInsurerAssociationBM associationBM : sponsorInsurerAssociationBMList ){
			   		
				   associationBM.setSponsorName( sponsorDetailsBM.getSponsorsName() );
				   associationBM.setCreatedBy( sponsorDetailsBM.getCreatedBy() );
				   associationBM.setCreatedDate( new Date() );
				   this.createSponsorInsurerAssociation(associationBM);
			   }
		   }

		   return converter.convertSponsorDetailsDM2BM(claimSponsor);
	   
		} catch ( Exception e ) {
			Fault fault = new Fault( ApplicationErrors.ADD_CLAIM_SPONSOR_FAILED );
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	/**
	 * This method creates account for sponsor.
	 * The format of account for sponsor is 'SPONSOR-<<sponsor count + 1>>'.
	 *  
	 * @param sponsorDetailsBM
	 * @return
	 */
	private String createAccountForSponsor( SponsorDetailsBM sponsorDetailsBM){
		
		try {

			AccountInfoBM accountInfoBM = new AccountInfoBM();
			ContactDetailsBM contactBM = sponsorDetailsBM.getContactDetailsBM();
			
			if (contactBM != null) {
				
					contactBM.setForEntity( new CodeAndDescription(
								InsuranceManagementConstants.REFERENCE_TYPE_SPONSOR,""));
					accountInfoBM.setContactDetailsBM(contactBM);
			}
			accountInfoBM.setEntityCategory(new CodeAndDescription("1000002",""));
			Integer accountId = eagleIntegration.createAccount(accountInfoBM, false);

			return accountId.toString();
		} catch (Exception e) {
		Fault fault = new Fault(ApplicationErrors.CREATE_ACCOUNT_FAILED);
			throw new HCISException( fault ,e);
		}
		//Create account for patient in EAGLE ERP - END
	}
	
	
	/*
	 * This method crates new Insurer in the system.Every insurance plan on the system will associated with
	 * the insurer only.Hospital never interact directly with Insurer it deals with Sponsor only. So
	 * every Insurer must be associated with at least one Sponsor.
	 */
	
	public InsurerBM createInsurer( InsurerBM insurerBM ) throws HCISException{
		
		try {
			Insurer insurer = converter.convertInsurerBM2DM(insurerBM, null ); 
//		create contact entry first
			ContactDetailsBM contactDetailsBM = insurerBM.getContactDetailsBM();
			
			if( contactDetailsBM != null){
				
				ContactDetails contactDetails = converter.convertContactDetalisBM2DM(contactDetailsBM, null);
				
				contactDetails.setCreateDtm(new Date());
				contactDetails.setCreatedBy( insurerBM.getCreatedBy() );
				
				contactDetailsDAO.save(contactDetails);
				
				insurer.setContactCode(contactDetails.getContactCode());
			}
			insurer.setCreatedDtm( new Date() );
			insurerDAO.save( insurer );
			
			 List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = insurerBM.getSponsorInsurerAssociationBMList();
			   if ( sponsorInsurerAssociationBMList != null && !sponsorInsurerAssociationBMList.isEmpty()){
				   for( SponsorInsurerAssociationBM associationBM : sponsorInsurerAssociationBMList ){
				   		
					   associationBM.setInsurerName( insurerBM.getInsurerName() );
					   associationBM.setCreatedBy( insurerBM.getCreatedBy() );
					   associationBM.setCreatedDate( new Date() );
					   this.createSponsorInsurerAssociation(associationBM);
				   }
			   }

			return converter.convertInsurerDM2BM(insurer);
		} catch ( Exception e) {
			Fault fault = new Fault( ApplicationErrors.ADD_INSURER_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

/**
 * This method creates association between Sponsor and Insurer for given  SponsorInsurerAssociationBM .
 * @param sponsorInsurerAssociationBMList
 */	
	
	private void createSponsorInsurerAssociation( SponsorInsurerAssociationBM sponsorInsurerAssociationBM){
		
			if ( sponsorInsurerAssociationBM != null ) {
				
					SponsorInsurerAssociation sponsorInsurerAssociationDM =  new SponsorInsurerAssociation();
					SponsorInsurerAssociationId sponsorInsurerAssociationId = new SponsorInsurerAssociationId();
					
					sponsorInsurerAssociationId.setSponsorName(sponsorInsurerAssociationBM.getSponsorName());
					sponsorInsurerAssociationId.setInsurerName(sponsorInsurerAssociationBM.getInsurerName());
					sponsorInsurerAssociationDM.setId( sponsorInsurerAssociationId );
//					sponsorInsurerAssociationDM.setClaimSponsorBySponsorName( dataModelManager.getClaimSponsor(associationBM.getSponsorName()));
//					sponsorInsurerAssociationDM.setClaimSponsorByInsurerName(dataModelManager.getClaimSponsor(associationBM.getInsuresName()));
					sponsorInsurerAssociationDM.setEffectiveFromDt(sponsorInsurerAssociationBM.getEffectiveFromDate());
					sponsorInsurerAssociationDM.setEffectiveToDt(sponsorInsurerAssociationBM.getEffectiveToDate());
					sponsorInsurerAssociationDM.setCreatedBy( sponsorInsurerAssociationBM.getCreatedBy() );
					sponsorInsurerAssociationDM.setCreatedDtm( sponsorInsurerAssociationBM.getCreatedDate() );
					sponsorInsurerAssociationDAO.save(sponsorInsurerAssociationDM);
			}
	}
	/**
	 * This method creates Claim Request and  Claim Request Activity.
	 * 
	 * It is also possible that associated Insurer (Insurance company ) name is given at the time of claim request, which is not exist in the
	 * system (In case of TPA or any third party sponsor ). In this case the method first creates Inaure's entry (as new CliamSponsor
	 * with same contact detail as exiting sponsor )  then creates association with given sponsor. 
	 *  
	 * 
	 */

	public ClaimRequestBM createClaimRequest( ClaimRequestBM claimRequestBM) throws HCISException {

		try {
			InsurancePlans insurancePlans = dataModelManager.getInsurancePlan(claimRequestBM.getPlanCode());
			Double requesteAmnt = claimRequestBM.getRequestedAmount();
			Double maxCoverageAmnt = new Double(0);
			
			Calendar today = Calendar.getInstance();
			
			if( claimRequestBM.getPolicyEffectiveToDt().before( today.getTime() ) ){
				Fault fault = new Fault( ApplicationErrors.CREATE_CLAIM_REQUEST_FAILED );
				HCISException hcisException = new HCISException(fault.getFaultMessage()+ ":Policy is not effective at today's date ", fault.getFaultCode(), fault.getFaultType());
				throw hcisException;
			}

/*		String percentAbsInd = insurancePlans.getPercentAbsInd();
			
			if (percentAbsInd != null && !percentAbsInd.isEmpty() && 
					percentAbsInd.equals(InsuranceManagementConstants.PERCENT_ABS_IND_ABSOLUTE)) {

				maxCoverageAmnt = insurancePlans.getTotalCoverageAmt();

				// The requested amount should be less then Maximum coverage amount of
				// the Insurance plan

				if (requesteAmnt > maxCoverageAmnt) {
					Fault fault = new Fault( ApplicationErrors.CREATE_CLAIM_REQUEST_FAILED );
					HCISException hcisException = new HCISException(fault.getFaultMessage()+ ": Calimed amount is greater then Coverage Amount", fault.getFaultCode(), fault.getFaultType());
					throw hcisException;
				}
			}
			else if (insurancePlans.getPercentAbsInd().equals(InsuranceManagementConstants.PERCENT_ABS_IND_PRECENT)) {

				Admission admission = dataModelManager.getAdmission(claimRequestBM.getAdmissionReqNbr());

				if (admission.getTreatmentEstimatedCost() != null) {

					maxCoverageAmnt = (admission.getTreatmentEstimatedCost()* insurancePlans.getTotalCoverageAmt() / 100.0);
				}
			}*/

			

//		Check whether the Claim request contains insurerBM ( in case of claiming against new insurance company )
			
			if( claimRequestBM.getInsurerBM() != null ){
				
//			Create new Insurer(Insurance company ) with contact detail same as existing Sponsor
				InsurerBM insurerBM = claimRequestBM.getInsurerBM();
				if( insurerBM.getContactDetailsBM() == null && ( insurerBM.getSponsorInsurerAssociationBMList() == null
																 ||insurerBM.getSponsorInsurerAssociationBMList().isEmpty() )){	
					
					ClaimSponsor claimSponsor = dataModelManager.getClaimSponsor( claimRequestBM.getSponsorName() );

					ContactDetails insureContactDetails = contactDetailsDAO.findById(claimSponsor.getContactCode());
					
					insureContactDetails.setContactCode(null);
					contactDetailsDAO.save(insureContactDetails);
					
					Insurer insurer = converter.convertInsurerBM2DM(insurerBM, null );
					insurer.setContactCode( insureContactDetails.getContactCode() );
					
					insurerDAO.save( insurer );
					
//				Create association with existing Sponsor 
					SponsorInsurerAssociationBM sponsorInsurerAssociationBM = new SponsorInsurerAssociationBM();
					sponsorInsurerAssociationBM.setSponsorName( claimRequestBM.getSponsorName() );
					sponsorInsurerAssociationBM.setInsurerName( insurerBM.getInsurerName() );
					sponsorInsurerAssociationBM.setEffectiveFromDate( today.getTime());//Today
					sponsorInsurerAssociationBM.setCreatedBy( claimRequestBM.getCreatedBy() );
					sponsorInsurerAssociationBM.setCreatedDate( today.getTime() );
					
					this.createSponsorInsurerAssociation(sponsorInsurerAssociationBM);
				
				}else{
					this.createInsurer(insurerBM);
				}
			}
			
			if( claimRequestBM.getClaimStatusCd() == null || claimRequestBM.getClaimStatusCd().getCode() == null 
														  || claimRequestBM.getClaimStatusCd().getCode().isEmpty()){
				claimRequestBM.setClaimStatusCd(new CodeAndDescription(InsuranceManagementConstants.CLAIM_STATUS_CREATED,null ));
			}
			AdmissionClaimRequest admissionClaimRequest = converter.convertClaimRequestBM2DM(claimRequestBM, null);

			//set subsequence Nbr
			Integer subsequenceNbr = admissionClaimRequestDAO.nextSubsequenceNumberForARN( claimRequestBM.getAdmissionReqNbr());
			admissionClaimRequest.setClaimSubsequenceNbr(subsequenceNbr);
			admissionClaimRequest.setCreatedDtm( today.getTime() );
			admissionClaimRequestDAO.save(admissionClaimRequest);
			
			List<ClaimDocumentBM> claimDocumentBMList = claimRequestBM.getClaimDocumentBMList();
			
			if( claimDocumentBMList != null && !claimDocumentBMList.isEmpty()){
				
				for( ClaimDocumentBM claimDocumentBM : claimDocumentBMList ){
					
					ClaimDocument claimDocument = new ClaimDocument();
					ClaimDocumentId claimDocumentId = new ClaimDocumentId();
					
					claimDocumentId.setRequestSequenceNbr(admissionClaimRequest.getRequestSequenceNbr());
					claimDocumentId.setCreateDtm( today.getTime() );
					claimDocumentId.setDocumentName( claimDocumentBM.getDocumentName() );
					
					claimDocument.setId( claimDocumentId );
					claimDocument.setDocumentPath( claimDocumentBM.getDocumentPath() );
					claimDocument.setCreatedBy( claimRequestBM.getCreatedBy() );
					
					claimDocumentDAO.save( claimDocument );
					
				}
			}
			
			

			ClaimRequestActivityBM claimRequestActivityBM = new ClaimRequestActivityBM();

			String activityTyepeCode = InsuranceManagementConstants.CLAIM_REQ_ACTIVITY_PREFIX
										+ admissionClaimRequest.getSponsorClaimStatus().getClaimStatusCd();
			
			claimRequestActivityBM.setActivityType(new CodeAndDescription(activityTyepeCode, null));
			claimRequestActivityBM.setCreatedBy(admissionClaimRequest.getCreatedBy());
			claimRequestActivityBM.setRemarks("");
			claimRequestActivityBM.setRequestSequenceNbr(admissionClaimRequest.getRequestSequenceNbr());
			claimRequestActivityBM.setSponsorClaimStatus(new CodeAndDescription( admissionClaimRequest.getSponsorClaimStatus().getClaimStatusCd(),
																				 admissionClaimRequest.getSponsorClaimStatus().getClaimStatusDesc()));

			this.createClaimRequestActivity(claimRequestActivityBM);

			return converter.convertAdmissionClaimRequestDM2BM(admissionClaimRequest);
		} catch (RuntimeException e) {

			Fault fault = new Fault( ApplicationErrors.CREATE_CLAIM_REQUEST_FAILED );
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	public void createClaimRequestActivity(	ClaimRequestActivityBM claimRequestActivityBM ) throws HCISException {
	
			AdmissionClaimActivity admissionClaimActivity = new AdmissionClaimActivity();
	
			AdmissionClaimActivityId activityId = new AdmissionClaimActivityId();
	
			activityId.setRequestSequenceNbr(claimRequestActivityBM.getRequestSequenceNbr());
			activityId.setActivityTypeCd(claimRequestActivityBM.getActivityType().getCode());
			activityId.setCreateDtm( new Date() );
	
			admissionClaimActivity.setId(activityId);
			admissionClaimActivity.setRemarks(claimRequestActivityBM.getRemarks());
	
			SponsorClaimStatus claimStatus = dataModelManager.getSponsorClaimStatus(claimRequestActivityBM.getSponsorClaimStatus().getCode());
	
			admissionClaimActivity.setSponsorClaimStatus(claimStatus);
			
			admissionClaimActivityDAO.save(admissionClaimActivity);
	}

	/**
	 *  This method creates new insurance plan on system.
	 */
	
	public InsurancePlanBM createInsurancePlan(InsurancePlanBM insurancePlanBM)	throws HCISException {

			InsurancePlans insurancePlans = converter.convertInsurancePlanBM2DM( insurancePlanBM, null );
			insurancePlans.setCreatedBy( insurancePlanBM.getCreatedBy() );
			insurancePlans.setCreatedDtm( new Date() );
			
			insurancePlansDAO.save(insurancePlans);
	
			List<PlanCoversDiseaseBM> planCoversDiseaseBMList = insurancePlanBM.getPlanCoversDiseaseBMList();
	
			if ( planCoversDiseaseBMList != null	&& !planCoversDiseaseBMList.isEmpty() ) {
				
				for (PlanCoversDiseaseBM planCoversDiseaseBM : planCoversDiseaseBMList) {
					
					planCoversDiseaseBM.setPlanName(  new CodeAndDescription(insurancePlanBM.getPlanCode(),"") );
					planCoversDiseaseBM.setCreatedBy( insurancePlanBM.getCreatedBy());
					planCoversDiseaseBM.setCreatedDate( new Date() );
					this.createPlanDiseasAssociation(planCoversDiseaseBM);
			
				}
			}
			List<PlanHasServicesBM> planHasServicesBMList = insurancePlanBM.getPlanHasServicesBMList();
	
			if (planHasServicesBMList != null && !planHasServicesBMList.isEmpty()) {
				for (PlanHasServicesBM planHasServicesBM : planHasServicesBMList) {
					planHasServicesBM.setPlanName( new CodeAndDescription(insurancePlanBM.getPlanCode(),""));
					planHasServicesBM.setCreatedBy( insurancePlanBM.getCreatedBy() );
					planHasServicesBM.setCreatedDate( new Date() );
					this.createPlanServiceAssociation( planHasServicesBM );
				}
			}

		return converter.convertInsurancePlansDM2BM(insurancePlans); 
	}

	/**
	 * This  method creates association between insurance plans and diseases covered by it.
	 * @param planCoversDiseaseBMList
	 */
	private void createPlanDiseasAssociation( PlanCoversDiseaseBM planCoversDiseaseBM ){
		
				PlanCoversDisease planCoversDisease = new PlanCoversDisease();
				PlanCoversDiseaseId planCoversDiseaseId = new PlanCoversDiseaseId();

				planCoversDiseaseId.setDiseaseName( planCoversDiseaseBM.getDiseaceName().getCode());
				planCoversDiseaseId.setPlanCd(planCoversDiseaseBM.getPlanName().getCode());
				planCoversDisease.setId(planCoversDiseaseId);

				//TODO: need to review (Bhavesh)
//				Disease disease = dataModelManager.getDisease(planCoversDiseaseBM.getDiseaceName().getCode());
//				planCoversDisease.setDisease(disease);

				planCoversDisease.setPercentAbsInd(planCoversDiseaseBM.getPercentAbsInd());
				planCoversDisease.setCoverageAmt(planCoversDiseaseBM.getCoverageAmt());
				planCoversDisease.setIsCoverd( planCoversDiseaseBM.getIsCoverdFlag() );
				planCoversDisease.setCreatedDtm(new Date());
				planCoversDisease.setCreatedBy( planCoversDiseaseBM.getCreatedBy() );
				planCoversDiseaseDAO.save(planCoversDisease);

		
	}
	/**
	 * This  method creates association between insurance plans and the Services that covered by the plan.
	 * @param planHasServicesBMList
	 */
	
	private void createPlanServiceAssociation( PlanHasServicesBM planHasServicesBM){
		
				PlanHasServices planHasServices = new PlanHasServices();

				PlanHasServicesId planHasServicesId = new PlanHasServicesId();
				planHasServicesId.setPlanCd(planHasServicesBM.getPlanName().getCode());
				planHasServicesId.setServiceCode(planHasServicesBM.getServiceName().getCode());
				planHasServices.setId(planHasServicesId);

				planHasServices.setPercentAbsInd(planHasServicesBM.getPercetAbsInd());
				planHasServices.setCoverageAmt(planHasServicesBM.getCoverageAmt());
				planHasServices.setIsCoverd(planHasServicesBM.getIsCoverdFlag());
				planHasServices.setCreatedBy( planHasServicesBM.getCreatedBy() );
				planHasServices.setCreatedDtm( planHasServicesBM.getCreatedDate() );
				
				planHasServicesDAO.save(planHasServices);
	}
	
	
	public List<ClaimRequestActivityBM> getClaimRequestActivities( String claimRequestSequenceNbr,
																   String activityTypeCd,
																   String claimStatusCd,
																   String remarks,
																   String activityCreatedBy ) throws HCISException {
		
		List<AdmissionClaimActivity> admissionClaimActivityList = admissionClaimActivityDAO.getClaimRequestActivities(claimRequestSequenceNbr, activityTypeCd,
																							claimStatusCd, remarks, activityCreatedBy);
		
		List<ClaimRequestActivityBM> claimRequestActivityBMList = new ArrayList<ClaimRequestActivityBM>(0);
		if( admissionClaimActivityList != null && !admissionClaimActivityList.isEmpty() ){
			for ( AdmissionClaimActivity admissionClaimActivity : admissionClaimActivityList ){
				 
				ClaimRequestActivityBM activityBM = new ClaimRequestActivityBM();
				
				ActivityType activityType = admissionClaimActivity.getActivityType();
				if( activityType != null ){
					activityBM.setActivityType( new CodeAndDescription(activityType.getActivityTypeCd(),activityType.getActivityDesc()));
				}
				activityBM.setCreatedBy(admissionClaimActivity.getCreatedBy() );
				activityBM.setCreationDtm( admissionClaimActivity.getId().getCreateDtm() );
				activityBM.setRemarks( admissionClaimActivity.getRemarks() );
				activityBM.setRequestSequenceNbr( admissionClaimActivity.getId().getRequestSequenceNbr() );
				
				SponsorClaimStatus sponsorClaimStatus = admissionClaimActivity.getSponsorClaimStatus();
				if( sponsorClaimStatus != null ){
					activityBM.setSponsorClaimStatus(new CodeAndDescription(sponsorClaimStatus.getClaimStatusCd(),sponsorClaimStatus.getClaimStatusDesc() ));
				}
				
				claimRequestActivityBMList.add( activityBM );
			}
		}
		return claimRequestActivityBMList;
	}

	public List<ClaimRequestBM> getClaimRequests( Integer requestSequenceNbr,
												  Integer admissionNumber,
												  Integer patientId,
												  String patientName,
												  String sponsorType,
												  String sponsorName,
												  String planName,
												  String planCode,
												  String policyNumber,
												  String claimStatusCode,
												  Date claimRequestCreationFromDt,
												  Date claimRequestCreationToDt,
												  Date claimApprovalFromDt,
												  Date claimApprovalToDt,
												  int start, int count, String orderBy) throws HCISException {

		String[] orderByInfo;
		
		if( orderBy != null && orderBy.length() > 0 ){
			orderByInfo =  orderBy.trim().split(" "); 
		}else{
			orderByInfo = new String[2];
		}
			List<AdmissionClaimRequest> admissionClaimRequestList = 
										admissionClaimRequestDAO.getClaimRequests( requestSequenceNbr,
																				   admissionNumber,
																				   patientId,
																				   sponsorType, 
																				   sponsorName,
																				   planName,
																				   planCode,
																				   policyNumber,
																				   claimStatusCode,
																				   claimRequestCreationFromDt,
																				   claimRequestCreationToDt,
																				   claimApprovalFromDt,
																				   claimApprovalToDt,
																				   orderByInfo[0],orderByInfo[1]);
		
			List<ClaimRequestBM> claimRequestBMList =  new ArrayList<ClaimRequestBM>();
			
			if (admissionClaimRequestList != null && !admissionClaimRequestList.isEmpty()) {

				for (AdmissionClaimRequest claimRequest : admissionClaimRequestList) {
					
					if( patientName != null && !patientName.isEmpty() ){
						
						if( claimRequest.getAdmission().getPatientId() != null){
							
							Patient patient = coreDataModelManager.getPatient(claimRequest.getAdmission().getPatientId());
							
							if ( patient.getFullName() != null && patient.getFullName().toLowerCase().contains( patientName.toLowerCase() )  ) {
								ClaimRequestBM claimRequestBM = converter.convertAdmissionClaimRequestDM2BM( claimRequest );
								if( claimRequestBM != null ){
									AdmissionLightBM admissionLightBM = this.getAdmissionInfo(claimRequest.getAdmission().getAdmissionReqNbr());
									claimRequestBM.setPatientName( admissionLightBM.getPatientName() );
									claimRequestBM.setEstimatedCost( admissionLightBM.getEstimatedTreatmentAmnt() );
									claimRequestBM.setEstimationGivenBy( admissionLightBM.getEstimatedBy() );
									claimRequestBM.setInsuranceAmount( this.getInsuranceAmount( claimRequest.getInsurancePlans().getPlanCd(), claimRequest.getRequestedAmount()));
									claimRequestBMList.add( claimRequestBM );
								}
							}
						}
					}else{
						ClaimRequestBM claimRequestBM = converter.convertAdmissionClaimRequestDM2BM( claimRequest );
						if( claimRequestBM != null ){
							AdmissionLightBM admissionLightBM = this.getAdmissionInfo(claimRequest.getAdmission().getAdmissionReqNbr());
							claimRequestBM.setPatientName( admissionLightBM.getPatientName() );
							claimRequestBM.setEstimatedCost( admissionLightBM.getEstimatedTreatmentAmnt() );
							claimRequestBM.setEstimationGivenBy( admissionLightBM.getEstimatedBy() );
							claimRequestBM.setInsuranceAmount( this.getInsuranceAmount( claimRequest.getInsurancePlans().getPlanCd(), claimRequest.getRequestedAmount()));
							claimRequestBMList.add( claimRequestBM );
						}
					}
				}
		}

		return claimRequestBMList;
	}

	public List<ClaimRequestBM> getClaimRequestsForAnAdmission(	Integer admissionNumber) throws HCISException {
		
				List<AdmissionClaimRequest> admissionClaimRequestList = admissionClaimRequestDAO.findByProperty("admission.admissionNbr", admissionNumber);
				List<ClaimRequestBM> claimRequestBMList = new ArrayList();
		
				if (admissionClaimRequestList != null && !admissionClaimRequestList.isEmpty()) {
		
					for(AdmissionClaimRequest claimRequest : admissionClaimRequestList){
						
						claimRequestBMList.add(converter.convertAdmissionClaimRequestDM2BM( claimRequest ));
					}
		
				}

				return claimRequestBMList;
	}

	public 	List<InsurancePlanBM> getInsurancePlans( String planCode,
												   	 String planName,
													 String insurerName,
													 String sponsorName,
													 Date   validFromDt,
													 Date   validToDt ) throws HCISException {
		
		List<InsurancePlans> insurancePlansList = insurancePlansDAO.getInsurancePlans(planCode, planName, insurerName,
																	sponsorName, validFromDt, validToDt);
		
		List<InsurancePlanBM> insurancePlanBMList = new ArrayList<InsurancePlanBM>();
		
		if(insurancePlansList!= null && !insurancePlansList.isEmpty()){
			for( InsurancePlans insurancePlans : insurancePlansList ){
				
				InsurancePlanBM insurancePlanBM = converter.convertInsurancePlansDM2BM(insurancePlans); 
				
				List<PlanCoversDisease> planCoversDiseaseList = planCoversDiseaseDAO.findByProperty("id.planCd", insurancePlans.getPlanCd() );
				List<PlanCoversDiseaseBM> planCoversDiseaseBMList = new ArrayList<PlanCoversDiseaseBM>(0);
				if( planCoversDiseaseList != null && !planCoversDiseaseList.isEmpty()){
					
					for( PlanCoversDisease planCoversDisease : planCoversDiseaseList){
						
						PlanCoversDiseaseBM coversDiseaseBM = new PlanCoversDiseaseBM();
						coversDiseaseBM.setPlanName( new CodeAndDescription(insurancePlans.getPlanCd(),insurancePlans.getPlanName()));
						
						Disease disease = diseaseDAO.findById(planCoversDisease.getId().getDiseaseName());
						if( disease != null ){
							coversDiseaseBM.setDiseaceName(new CodeAndDescription(disease.getDiseaseName(),disease.getDescription()));	
						}
						coversDiseaseBM.setPercentAbsInd( planCoversDisease.getPercentAbsInd() );
						coversDiseaseBM.setCoverageAmt( planCoversDisease.getCoverageAmt() );
						coversDiseaseBM.setIsCoverdFlag( planCoversDisease.getIsCoverd() );
						coversDiseaseBM.setCreatedBy( planCoversDisease.getCreatedBy() );
						coversDiseaseBM.setCreatedDate( planCoversDisease.getCreatedDtm() );
						planCoversDiseaseBMList.add( coversDiseaseBM );
					}
				}
				
				List<PlanHasServices> planHasServicesList = planHasServicesDAO.findByProperty("id.planCd", insurancePlans.getPlanCd());
				List<PlanHasServicesBM> planHasServicesBMList = new ArrayList<PlanHasServicesBM>(0);
				if( planHasServicesList != null && !planHasServicesList.isEmpty() ){
					for( PlanHasServices planHasServices : planHasServicesList ){
						
						PlanHasServicesBM planHasServicesBM = new PlanHasServicesBM();
						planHasServicesBM.setPlanName(new CodeAndDescription(insurancePlans.getPlanCd(),insurancePlans.getPlanName()) );
						
						Service service = coreDataModelManager.getServiceByCode( planHasServices.getId().getServiceCode() );
						if( service != null ){
							planHasServicesBM.setServiceName(new CodeAndDescription(service.getServiceCode(), service.getServiceName()));
						}
						planHasServicesBM.setPercetAbsInd( planHasServices.getPercentAbsInd() );
						planHasServicesBM.setIsCoverdFlag( planHasServices.getIsCoverd() );
						planHasServicesBM.setCoverageAmt( planHasServices.getCoverageAmt() );
						planHasServicesBM.setCreatedBy( planHasServices.getCreatedBy() );
						planHasServicesBM.setCreatedDate( planHasServices.getCreatedDtm() );
						planHasServicesBMList.add( planHasServicesBM );
					}
				}
				insurancePlanBM.setPlanCoversDiseaseBMList(planCoversDiseaseBMList);
				insurancePlanBM.setPlanHasServicesBMList(planHasServicesBMList);
				insurancePlanBMList.add( insurancePlanBM );
				
			}
		}
		
		return insurancePlanBMList;
	}

	/**
	 * This method returns InsurancePlanBM without having the related service and diseases list.
	 * @param planCd
	 * @return
	 */
	public InsurancePlanBM getInsurancePlanSummary(String planCd ){
		
		InsurancePlanBM insurancePlanBM = null; 
		
		if( planCd != null && !planCd.isEmpty()){
			InsurancePlans insurancePlans = dataModelManager.getInsurancePlan(planCd);
			
			insurancePlanBM = converter.convertInsurancePlansDM2BM(insurancePlans);
		}
		
		return insurancePlanBM;
	}
	public ListRange findInsurancePlans( String planCode,
									   	 String planName,
										 String insurerName,
										 String sponsorName,
										 Date   validFromDt,
										 Date   validToDt,
										 int start, int count, String orderBy) throws HCISException {
		
		
		
		List<InsurancePlanBM> insurancePlanBMList = this.getInsurancePlans(planCode, planName, insurerName,
																	sponsorName, validFromDt, validToDt);

		ListRange listRange = new ListRange();

		List<InsurancePlanBM> pageSizeData = new ArrayList<InsurancePlanBM>();
		int index = 0;
		if (insurancePlanBMList != null && insurancePlanBMList.size() > 0) {
			while ((start + index < start + count)
					&& (insurancePlanBMList.size() > start + index)) {

				InsurancePlanBM insurancePlanBM = insurancePlanBMList.get(start + index);
//				insurancePlanBM.setSeqNbr(start + index + 1);
				pageSizeData.add(insurancePlanBM);
				index++;
			}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(insurancePlanBMList.size());
		} else {
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}

		return listRange;

	}
	public List<SponsorDetailsBM> getSponsors( String sponsorName,
											   String sponsorType,
											   String sponsorDescription,
											   String creditClassCode, 
											   String accountNumber,
											   String insurerName )	throws HCISException {
		
			
		List<ClaimSponsor> claimSponsorList = claimSponsorDAO.getClaimSponsors( sponsorName,
																				sponsorType,
																				sponsorDescription,
																				creditClassCode,
																				accountNumber,
																				insurerName,
																				null,
																				null);
		
		List<SponsorDetailsBM> sponsorDetailsBMList = new ArrayList<SponsorDetailsBM>();	
		
		if( claimSponsorList != null && !claimSponsorList.isEmpty() ){
			
			for( ClaimSponsor claimSponsor : claimSponsorList ){
				SponsorDetailsBM sponsorDetailsBM = converter.convertSponsorDetailsDM2BM( claimSponsor ); 
				ContactDetails contactDetails = contactDetailsDAO.findById( claimSponsor.getContactCode() );
				Validate.notNull(contactDetails, " Contact details not found for sponsor '"+sponsorDetailsBM.getSponsorsName()+"'");
				sponsorDetailsBM.setContactDetailsBM( converter.convertContactDetailsDM2BM( contactDetails ));
				
				List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
				List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = new ArrayList<SponsorInsurerAssociationBM>(0);
				if( sponsorInsurerAssociationList != null && !sponsorInsurerAssociationList.isEmpty() ){
					for ( SponsorInsurerAssociation association : sponsorInsurerAssociationList ){
						SponsorInsurerAssociationBM associationBM = converter.convertSponsorInsurerAssociationDM2BM(association);
						sponsorInsurerAssociationBMList.add( associationBM );
					}
				}
				
				List<ClaimSponsorSla> claimSponsorSlaList = claimSponsorSlaDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
				List<SponsorSlaBM> sponsorSlaBMList = new ArrayList<SponsorSlaBM>(0);
				if( claimSponsorSlaList != null && !claimSponsorSlaList.isEmpty() ){
					for( ClaimSponsorSla claimSponsorSla : claimSponsorSlaList ){
						
						SponsorSlaBM sponsorSlaBM = new SponsorSlaBM();
						sponsorSlaBM.setSponsorName( claimSponsorSla.getClaimSponsor().getSponsorsName() );
						
						ActivityType activityType =  claimSponsorSla.getActivityType() ;
						if( activityType != null ){
							sponsorSlaBM.setActivityType(new CodeAndDescription( activityType.getActivityTypeCd(),activityType.getActivityDesc()));	
						}
						sponsorSlaBM.setSlaPeriod( claimSponsorSla.getSlaPeriod() );
						sponsorSlaBM.setSlaUnit( claimSponsorSla.getPeriodUnit() );
						
						sponsorSlaBMList.add( sponsorSlaBM );
					}
				}
				
				sponsorDetailsBM.setSponsorInsurerAssociationBMList(sponsorInsurerAssociationBMList);
				sponsorDetailsBM.setSponsorSlaBMList(sponsorSlaBMList);
				sponsorDetailsBMList.add( sponsorDetailsBM );
			}
		}
		return sponsorDetailsBMList;
	
	}
	
	
	public ListRange findSponsors( String sponsorName, 
						            String sponsorType,
						            String sponsorDescription,
						            String creditClassCode,
						            String accountNumber,
						            String insurerName,
						            int start, int count, String orderBy ){
		try {
			
			String[] orderByInfo;
			
			if( orderBy != null && orderBy.length() > 0 ){
				orderByInfo =  orderBy.trim().split(" "); 
			}else{
				orderByInfo = new String[2];
			}
				
			List<ClaimSponsor> claimSponsorList = claimSponsorDAO.getClaimSponsors( sponsorName,
																					sponsorType,
																					sponsorDescription,
																					creditClassCode,
																					accountNumber,
																					insurerName,
																					orderByInfo[0],
																					orderByInfo[1]);
			
			List<SponsorDetailsBM> sponsorDetailsBMList = new ArrayList<SponsorDetailsBM>();	
			
			if( claimSponsorList != null && !claimSponsorList.isEmpty() ){
				
				for( ClaimSponsor claimSponsor : claimSponsorList ){
					SponsorDetailsBM sponsorDetailsBM = converter.convertSponsorDetailsDM2BM( claimSponsor ); 
					ContactDetails contactDetails = contactDetailsDAO.findById( claimSponsor.getContactCode() );
					
					Validate.notNull(contactDetails, " Contact details not found for sponsor '"+sponsorDetailsBM.getSponsorsName()+"'");
					sponsorDetailsBM.setContactDetailsBM( converter.convertContactDetailsDM2BM( contactDetails ));
					
					List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
					List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = new ArrayList<SponsorInsurerAssociationBM>(0);
					if( sponsorInsurerAssociationList != null && !sponsorInsurerAssociationList.isEmpty() ){
						for ( SponsorInsurerAssociation association : sponsorInsurerAssociationList ){
							SponsorInsurerAssociationBM associationBM = converter.convertSponsorInsurerAssociationDM2BM(association);
							sponsorInsurerAssociationBMList.add( associationBM );
						}
					}
					
					List<ClaimSponsorSla> claimSponsorSlaList = claimSponsorSlaDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
					List<SponsorSlaBM> sponsorSlaBMList = new ArrayList<SponsorSlaBM>(0);
					if( claimSponsorSlaList != null && !claimSponsorSlaList.isEmpty() ){
						for( ClaimSponsorSla claimSponsorSla : claimSponsorSlaList ){
							
							SponsorSlaBM sponsorSlaBM = new SponsorSlaBM();
							sponsorSlaBM.setSponsorName( claimSponsorSla.getClaimSponsor().getSponsorsName() );
							
							ActivityType activityType =  claimSponsorSla.getActivityType() ;
							if( activityType != null ){
								sponsorSlaBM.setActivityType(new CodeAndDescription( activityType.getActivityTypeCd(),activityType.getActivityDesc()));	
							}
							sponsorSlaBM.setSlaPeriod( claimSponsorSla.getSlaPeriod() );
							sponsorSlaBM.setSlaUnit( claimSponsorSla.getPeriodUnit() );
							
							sponsorSlaBMList.add( sponsorSlaBM );
						}
					}
					
					sponsorDetailsBM.setSponsorInsurerAssociationBMList(sponsorInsurerAssociationBMList);
					sponsorDetailsBM.setSponsorSlaBMList(sponsorSlaBMList);
					sponsorDetailsBMList.add( sponsorDetailsBM );
				}
			}
		
			
			
	//---------------------------------------------------------------		
			
			ListRange listRange = new ListRange();
			
			List<SponsorDetailsBM> pageSizeData = new ArrayList<SponsorDetailsBM>();
			
			int index = 0;
			if (sponsorDetailsBMList != null && sponsorDetailsBMList.size() > 0) {
			while( (start+index < start + count) && (sponsorDetailsBMList.size() > start+index) ){
				
				SponsorDetailsBM sponsorDetailsBM = sponsorDetailsBMList.get(start+index);
				sponsorDetailsBM.setSeqNbr( start + index +1 );
				pageSizeData.add( sponsorDetailsBM );
					index++;
				}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(sponsorDetailsBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch ( Exception e) {
			Fault fault = new Fault(ApplicationErrors.READ_CLAIM_SPONSOR_FAILED);
			throw new HCISException(fault,e);
		}
		
	}
	
/*
 * This method returns list of Insurers.
 * All parameters are optional.
 * Method performs partial lookup for insurerDescription.
 */
	public List<InsurerBM> getInsurer(  String insurerName,
										String insurerDescription,
										String insurancePlanCd, 
										String sponsorName )	throws HCISException {

		try {
			List<Insurer> insurerList = insurerDAO.getInsurers( insurerName,
															    insurerDescription,
															    insurancePlanCd, 
															    sponsorName,
															    null,null);

			List<InsurerBM> insurerBMList = new ArrayList<InsurerBM>();

			if (insurerList != null && !insurerList.isEmpty()) {

				for (Insurer insurer : insurerList) {
					InsurerBM insurerBM = converter.convertInsurerDM2BM(insurer);
					ContactDetails contactDetails = contactDetailsDAO.findById(insurer.getContactCode());
					insurerBM.setContactDetailsBM(converter.convertContactDetailsDM2BM(contactDetails));
					
					List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.insurerName", insurer.getInsurerName() );
					List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = new ArrayList<SponsorInsurerAssociationBM>(0);
					if( sponsorInsurerAssociationList != null && !sponsorInsurerAssociationList.isEmpty() ){
						for ( SponsorInsurerAssociation association : sponsorInsurerAssociationList ){
							SponsorInsurerAssociationBM associationBM = converter.convertSponsorInsurerAssociationDM2BM(association);
							sponsorInsurerAssociationBMList.add( associationBM );
						}
					}
					insurerBM.setSponsorInsurerAssociationBMList(sponsorInsurerAssociationBMList);
					insurerBMList.add(insurerBM);
				}
			}
			return insurerBMList;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.READ_INSURER_FAILED);
			throw new HCISException(fault,e);
		}

	}
	
	public ListRange findInsurer( String insurerName,
								  String insurerDescription,
								  String insurancePlanCd, 
								  String sponsorName ,
								  int start, int count, String orderBy){
	
		try {
	//		List<InsurerBM> insurerBMList = this.getInsurer(insurerName, insurerDescription,
	//														insurancePlanCd, sponsorName);
	
	
			String[] orderByInfo;
			
			if( orderBy != null && orderBy.length() > 0 ){
				orderByInfo =  orderBy.trim().split(" "); 
			}else{
				orderByInfo = new String[2];
			}
	
			List<Insurer> insurerList = insurerDAO.getInsurers( insurerName,
															    insurerDescription,
															    insurancePlanCd, 
															    sponsorName,
															    orderByInfo[0],orderByInfo[1]);
	
			List<InsurerBM> insurerBMList = new ArrayList<InsurerBM>();
	
			if (insurerList != null && !insurerList.isEmpty()) {
	
				for (Insurer insurer : insurerList) {
					InsurerBM insurerBM = converter.convertInsurerDM2BM(insurer);
					ContactDetails contactDetails = contactDetailsDAO.findById(insurer.getContactCode());
					Validate.notNull(contactDetails, " Contact details not found for insurer '"+insurerBM.getInsurerName()+"'");
					insurerBM.setContactDetailsBM(converter.convertContactDetailsDM2BM(contactDetails));
					
					List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.insurerName", insurer.getInsurerName() );
					List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = new ArrayList<SponsorInsurerAssociationBM>(0);
					if( sponsorInsurerAssociationList != null && !sponsorInsurerAssociationList.isEmpty() ){
						for ( SponsorInsurerAssociation association : sponsorInsurerAssociationList ){
							SponsorInsurerAssociationBM associationBM = converter.convertSponsorInsurerAssociationDM2BM(association);
							sponsorInsurerAssociationBMList.add( associationBM );
						}
					}
					insurerBM.setSponsorInsurerAssociationBMList(sponsorInsurerAssociationBMList);
					insurerBMList.add(insurerBM);
				}
			}
			
			ListRange listRange = new ListRange();
	
			List<InsurerBM> pageSizeData = new ArrayList<InsurerBM>();
			int index = 0;
			if (insurerBMList != null && insurerBMList.size() > 0) {
				while ((start + index < start + count)	&& (insurerBMList.size() > start + index)) {
	
					InsurerBM insurerBM = insurerBMList.get(start + index);
					insurerBM.setSeqNbr(start + index + 1);
					pageSizeData.add(insurerBM);
					index++;
				}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(insurerBMList.size());
			} else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
	
			return listRange;
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.READ_INSURER_FAILED);
			throw new HCISException(fault,e);
		}
	}

/** This method modifies the state of a existing claim request.
 * 
 */
	public ClaimRequestBM modifyClaimRequests( ClaimRequestBM modifiedClaimRequestBM, String remarks)throws HCISException {

		try {
			Long requestSequenceNbr = modifiedClaimRequestBM.getRequestSequenceNbr();
			
			if (requestSequenceNbr != null) {
				
				AdmissionClaimRequest existingClaimRequest = dataModelManager.getAdmissionClaimRequest(requestSequenceNbr);				
				
				String newStatus = null;
				if( modifiedClaimRequestBM.getClaimStatusCd()!=null &&  modifiedClaimRequestBM.getClaimStatusCd().getCode() != null ){
					newStatus = modifiedClaimRequestBM.getClaimStatusCd().getCode();
				}
	
				String oldClaimStatus = existingClaimRequest.getSponsorClaimStatus().getClaimStatusCd();
				
				// If claim request is not in 'CREATED' status then do not allow to modify sponsor, insurer and plan related details   
				//
				
				if( !InsuranceManagementConstants.CLAIM_STATUS_CREATED.equals( oldClaimStatus )){
					
					Admission admission = existingClaimRequest.getAdmission();
					
					if( 	!admission.getAdmissionReqNbr().equals( modifiedClaimRequestBM.getAdmissionReqNbr())
						||	!existingClaimRequest.getClaimSponsor().getSponsorsName().equals(modifiedClaimRequestBM.getSponsorName())
						||	!existingClaimRequest.getInsurer().getInsurerName().equals( modifiedClaimRequestBM.getInsurerName())
						||	!existingClaimRequest.getInsurancePlans().getPlanCd().equals( modifiedClaimRequestBM.getPlanCode()) ){
						
						throw new Exception("Modificaion of ARN/Sponsor/Insurer/Plan details are not allowed for this claim request");
						
					}
				}
				
 
				boolean isValidStateTrasition = true;
				if( ! oldClaimStatus.equals( newStatus ) ){
					
					isValidStateTrasition = this.isValidStatusTransition( oldClaimStatus, newStatus);
					
				}
				
				if ( isValidStateTrasition ) {


					//	If new status is 'SUBMITTED' then set today as requested date for claim
					
					if( newStatus.equalsIgnoreCase(InsuranceManagementConstants.CLAIM_STATUS_SUBMITTED)){
						modifiedClaimRequestBM.setRequestDtm( new Date() );
					}
					
					// If new status is 'APPROVED' then set date of approval
					
					if( newStatus.equalsIgnoreCase(InsuranceManagementConstants.CLAIM_STATUS_APPROVED)){
						
						if( existingClaimRequest.getRequestedAmount() < modifiedClaimRequestBM.getApprovedAmount() ){
							throw new Exception("Approval amount ("+ modifiedClaimRequestBM.getApprovedAmount() + 
												") can not be greater then requested amount ("
												+ existingClaimRequest.getRequestedAmount() + ")");
						}
						
						modifiedClaimRequestBM.setApprovalDate( new Date() );
					}
					

					AdmissionClaimRequest admissionClaimRequest = converter.convertClaimRequestBM2DM(modifiedClaimRequestBM,
																									  existingClaimRequest);
					
					admissionClaimRequestDAO.attachDirty(admissionClaimRequest);

					// Create corresponding Activity Entry only if status has changed
					
					
					if(!oldClaimStatus.equals(newStatus)){
						
						ClaimRequestActivityBM claimRequestActivityBM = new ClaimRequestActivityBM();
						
						String activityTyepeCode = InsuranceManagementConstants.CLAIM_REQ_ACTIVITY_PREFIX
						+ admissionClaimRequest.getSponsorClaimStatus().getClaimStatusCd();
						
						claimRequestActivityBM.setActivityType(new CodeAndDescription(activityTyepeCode, null));
						claimRequestActivityBM.setCreatedBy(admissionClaimRequest.getCreatedBy());
						claimRequestActivityBM.setCreationDtm(new Date());
						claimRequestActivityBM.setRemarks(remarks);
						claimRequestActivityBM.setRequestSequenceNbr(admissionClaimRequest.getRequestSequenceNbr());
						claimRequestActivityBM.setSponsorClaimStatus(new CodeAndDescription( admissionClaimRequest.getSponsorClaimStatus().getClaimStatusCd(),
								admissionClaimRequest.getSponsorClaimStatus().getClaimStatusDesc()));
						
						this.createClaimRequestActivity(claimRequestActivityBM);
					}
					
					return converter.convertAdmissionClaimRequestDM2BM(admissionClaimRequest);
					
				
				} else {
					throw new Exception("This 'Status transition' is Not Valid! ");
				}
			}
			return null;
			
		} catch (Exception e) {

			Fault fault = new Fault(ApplicationErrors.MODIFY_CLAIM_REQUEST_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}

	}

	/**
	 * This method checks whether the transition of claim status is valid or
	 * not.
	 * 
	 * Following table defines the valid transition among different states
	 * 
	 * ------------------------------------------------------------------------
	 *  		From Status 	|	To Status
	 * ------------------------------------------------------------------------
	 * 		1) CREATED  		|	SUBMITTED
	 * 		2) SUBMITTED  		|	APPROVED, PARTAPPROVED, REJECTED, MOREINFO
	 * 		3) APPROVED			|	-
	 * 		4) PARTAPPROVED 	|	-
	 * 		5) REJECTED			|	-
	 * 		6) MOREINFO			|	RESUBMITTED
	 * 		7) RESUBMITTED      | 	MOREINFO, APPROVED, PARTAPPROVED, REJECTED  
	 * --------------------------------------------------------------------------
	 * 
	 * 
	 * @param currentStatus
	 * @param newStatus
	 * @return
	 */

	private boolean isValidStatusTransition(String currentStatus, String newStatus) {

		List<String> newStatusList = new ArrayList<String>(0);

		newStatusList.add(InsuranceManagementConstants.CLAIM_STATUS_APPROVED);
		newStatusList.add(InsuranceManagementConstants.CLAIM_STATUS_PARTAPPROVED);
		newStatusList.add(InsuranceManagementConstants.CLAIM_STATUS_RESUBMITTED);
		newStatusList.add(InsuranceManagementConstants.CLAIM_STATUS_MOREINFO);
		newStatusList.add(InsuranceManagementConstants.CLAIM_STATUS_REJECTED);

		if (currentStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_SUBMITTED)
				|| currentStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_RESUBMITTED))
			{
			
				if (newStatusList.contains(newStatus)) {

				return true;
			}
		} else if (currentStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_CREATED)) 
			{
				if (newStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_SUBMITTED)) {

				return true;
			}
		} else if (currentStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_MOREINFO)) 
			{
				if (newStatus.equals(InsuranceManagementConstants.CLAIM_STATUS_RESUBMITTED)) {

				return true;
			}
		}

		return false;
	}

	public InsurancePlanBM modifyInsurancePlan( InsurancePlanBM insurancePlanBM )	throws HCISException {
		
		try {
			InsurancePlans existingInsurancePlans = dataModelManager.getInsurancePlan(insurancePlanBM.getPlanCode());
			
			if( existingInsurancePlans != null){
			InsurancePlans insurancePlans = converter.convertInsurancePlanBM2DM(insurancePlanBM, existingInsurancePlans);
			
			insurancePlansDAO.attachDirty(insurancePlans);
			}
			
			
			List<PlanCoversDisease> planCoversDiseaseList = planCoversDiseaseDAO.findByProperty("id.planCd", insurancePlanBM.getPlanCode() );
			
			if( planCoversDiseaseList != null && !planCoversDiseaseList.isEmpty()){
				
				for( PlanCoversDisease planCoversDisease : planCoversDiseaseList){
					
					planCoversDiseaseDAO.delete( planCoversDisease );
				}
			}
			
			List<PlanCoversDiseaseBM> planCoversDiseaseBMList = insurancePlanBM.getPlanCoversDiseaseBMList();
			
			if( planCoversDiseaseBMList != null && !planCoversDiseaseBMList.isEmpty()){
				for (PlanCoversDiseaseBM planCoversDiseaseBM : planCoversDiseaseBMList) {
				
					planCoversDiseaseBM.setPlanName(  new CodeAndDescription(insurancePlanBM.getPlanCode(),"") );
					planCoversDiseaseBM.setCreatedBy( insurancePlanBM.getLastModifiedBy() );
					planCoversDiseaseBM.setCreatedDate( new Date() );
					this.createPlanDiseasAssociation(planCoversDiseaseBM);
			
				}
			}
			
			
			List<PlanHasServices> planHasServicesList = planHasServicesDAO.findByProperty("id.planCd", insurancePlanBM.getPlanCode());
			
			if( planHasServicesList != null && !planHasServicesList.isEmpty() ){
				
				for( PlanHasServices planHasServices : planHasServicesList){
				
					planHasServicesDAO.delete( planHasServices );
				}
			}
			
			List<PlanHasServicesBM> planHasServicesBMList = insurancePlanBM.getPlanHasServicesBMList();
			
			if( planHasServicesBMList != null && !planHasServicesList.isEmpty() ){
				
				for( PlanHasServicesBM planHasServicesBM : planHasServicesBMList ){
				
					planHasServicesBM.setPlanName( new CodeAndDescription(insurancePlanBM.getPlanCode(),""));
					planHasServicesBM.setCreatedBy(insurancePlanBM .getCreatedBy() );
					planHasServicesBM.setCreatedDate( new Date() );
					this.createPlanServiceAssociation( planHasServicesBM );
				}
				
			}
			return null;
		} catch ( Exception e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_INSURANCE_PLAN_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}  
	}


	public SponsorDetailsBM modifyClaimSponsor( SponsorDetailsBM sponsorDetailsBM ) throws HCISException {
		
		try {
			ClaimSponsor existingClaimSponsor = dataModelManager.getClaimSponsor(sponsorDetailsBM.getSponsorsName());
			
				
//			Modify the contact details first	
				
				   ContactDetailsBM contactDetailsBM = sponsorDetailsBM.getContactDetailsBM();

				   if( contactDetailsBM != null){
					   if( contactDetailsBM.getContactDetailsCode() == null ){
						   contactDetailsBM.setContactDetailsCode( existingClaimSponsor.getContactCode() );
					   }	
						ContactDetails existingcontactDetails = contactDetailsDAO.findById( contactDetailsBM.getContactDetailsCode() );
						
						ContactDetails contactDetails = converter.convertContactDetalisBM2DM(contactDetailsBM, existingcontactDetails );
						
						contactDetailsDAO.attachDirty( contactDetails );
						
					}
				
				ClaimSponsor claimSponsor = converter.convertClaimSponsorBM2DM(sponsorDetailsBM, existingClaimSponsor);
				claimSponsor.setLastModifiedBy( sponsorDetailsBM.getModifiedBy() );
				claimSponsor.setModifiedDtm( new Date() );
				
				claimSponsorDAO.attachDirty( claimSponsor );
				
			
//		ClaimSponsor claimSponsor = dataModelManager.getClaimSponsor(sponsorDetailsBM.getSponsorsName());
			
			List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
			
			if( sponsorInsurerAssociationList!= null && !sponsorInsurerAssociationList.isEmpty()){
				
				for( SponsorInsurerAssociation  sponsorInsurerAssociation : sponsorInsurerAssociationList ){
					
					sponsorInsurerAssociationDAO.delete( sponsorInsurerAssociation );
				}
			}
			
			List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = sponsorDetailsBM.getSponsorInsurerAssociationBMList();
			
			if( sponsorInsurerAssociationBMList!= null && !sponsorInsurerAssociationBMList.isEmpty()){
				
				for( SponsorInsurerAssociationBM associationBM : sponsorInsurerAssociationBMList ){

					associationBM.setSponsorName( claimSponsor.getSponsorsName() );
					associationBM.setCreatedBy( sponsorDetailsBM.getModifiedBy() );
					associationBM.setCreatedDate( new Date() );
					this.createSponsorInsurerAssociation(associationBM);

				}
			}

			
//		  Modify SLA entry
			
			//Delete old SLA Entry first
			
			try {
				List<ClaimSponsorSla> claimSponsorSlaList = claimSponsorSlaDAO.findByProperty("id.sponsorName", claimSponsor.getSponsorsName() );
				
				if( claimSponsorSlaList != null && !claimSponsorSlaList.isEmpty() ){
					for( ClaimSponsorSla sla : claimSponsorSlaList ){
						claimSponsorSlaDAO.delete( sla );
					}
				}
			} catch ( Exception e) {
				
				Fault fault = new Fault( ApplicationErrors.REMOVE_SPONSOR_SLA_FAILED );
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}

//		Create new SAL entry
			   List<SponsorSlaBM> sponsorSlaBMList = sponsorDetailsBM.getSponsorSlaBMList();
			   
			   if( sponsorSlaBMList != null && !sponsorSlaBMList.isEmpty()){
				   
				   for(SponsorSlaBM sponsorSlaBM : sponsorSlaBMList ){
					   
					   ClaimSponsorSla claimSponsorSla = new ClaimSponsorSla();
					   
					   ClaimSponsorSlaId slaId = new ClaimSponsorSlaId();
					   slaId.setActivityTypeCd( sponsorSlaBM.getActivityType().getCode() );
					 
					   if( sponsorSlaBM.getSponsorName() != null && !sponsorSlaBM.getSponsorName().isEmpty() ){
						   
						   slaId.setSponsorName( sponsorSlaBM.getSponsorName() );
					   }else{
						   
						   slaId.setSponsorName( claimSponsor.getSponsorsName() );   
					   }
					   
					   claimSponsorSla.setId( slaId );
					   claimSponsorSla.setSlaPeriod( sponsorSlaBM.getSlaPeriod() );
					   claimSponsorSla.setPeriodUnit( sponsorSlaBM.getSlaUnit() );
					   
					   claimSponsorSlaDAO.save( claimSponsorSla );
				   }
			   }
			   
			 
			SponsorDetailsBM detailsBMtoBeReturn =  converter.convertSponsorDetailsDM2BM( claimSponsor );
			
			detailsBMtoBeReturn.setContactDetailsBM( contactDetailsBM );
			
			return detailsBMtoBeReturn;
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_CLAIM_SPONSOR_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
		
	}

	public void modifyInsurer( InsurerBM insurerBM ){
		
		try {
			Insurer existingInsurer = dataModelManager.getInsurer( insurerBM.getInsurerName() );
			
			
//		Modify the contact details first	
			
			   ContactDetailsBM contactDetailsBM = insurerBM.getContactDetailsBM();
			   
				if( contactDetailsBM != null){
					
					if( contactDetailsBM.getContactDetailsCode() == null ){
						   contactDetailsBM.setContactDetailsCode( existingInsurer.getContactCode() );
					}
					 
					ContactDetails existingcontactDetails = contactDetailsDAO.findById( contactDetailsBM.getContactDetailsCode() );
					
					ContactDetails contactDetails = converter.convertContactDetalisBM2DM(contactDetailsBM, existingcontactDetails );
					
					contactDetailsDAO.attachDirty( contactDetails );
				}

				Insurer insurer = converter.convertInsurerBM2DM(insurerBM, existingInsurer);
				insurer.setModifiedDtm( new Date() );
				
				insurerDAO.attachDirty( insurer );
				
//			Delete association with sponsor(s)
				
				List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.insurerName", insurer.getInsurerName() );
				
				if( sponsorInsurerAssociationList!= null && !sponsorInsurerAssociationList.isEmpty()){
					
					for( SponsorInsurerAssociation  sponsorInsurerAssociation : sponsorInsurerAssociationList ){
						
						sponsorInsurerAssociationDAO.delete( sponsorInsurerAssociation );
					}
				}
				
				List<SponsorInsurerAssociationBM> sponsorInsurerAssociationBMList = insurerBM.getSponsorInsurerAssociationBMList();
				   if ( sponsorInsurerAssociationBMList != null && !sponsorInsurerAssociationBMList.isEmpty()){
					   for( SponsorInsurerAssociationBM associationBM : sponsorInsurerAssociationBMList ){
					   		
						   associationBM.setInsurerName( insurerBM.getInsurerName() );
						   associationBM.setCreatedBy( insurerBM.getCreatedBy() );
						   associationBM.setCreatedDate( new Date() );
						   this.createSponsorInsurerAssociation(associationBM);
					   }
				   }
		} catch (HCISException e) {
			Fault fault = new Fault(ApplicationErrors.MODIFY_INSURER_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}
	
	public ListRange findClaimRequests( Integer requestSequenceNbr,
										Integer admissionNumber, 
							            Integer patientId,
							            String  patientName,
							            String  sponsorType,
							            String  sponsorName,
							            String planName,
										String planCode,
							            String  policyNumber,
							            String  claimStatusCode,
							            Date    claimRequestCreationFromDt,
							            Date    claimRequestCreationToDt,
							            Date	claimApprovalFromDt,
							            Date    claimApprovalToDt,
							            int start, int count, String orderBy) throws HCISException{
		
		
		try {
	
			String[] orderByInfo;
			
			if( orderBy != null && orderBy.length() > 0 ){
				orderByInfo =  orderBy.trim().split(" "); 
			}else{
				orderByInfo = new String[2];
			}
				List<AdmissionClaimRequest> admissionClaimRequestList = 
											admissionClaimRequestDAO.getClaimRequests( requestSequenceNbr,
																					   admissionNumber,
																					   patientId,
																					   sponsorType, 
																					   sponsorName,
																					   planName,
																					   planCode,
																					   policyNumber,
																					   claimStatusCode,
																					   claimRequestCreationFromDt,
																					   claimRequestCreationToDt,
																					   claimApprovalFromDt,
																					   claimApprovalToDt,
																					   orderByInfo[0],orderByInfo[1]);
				
			List<ClaimRequestBM> claimRequestBMList =  new ArrayList<ClaimRequestBM>();
			
			if (admissionClaimRequestList != null && !admissionClaimRequestList.isEmpty()) {
		
					for (AdmissionClaimRequest claimRequest : admissionClaimRequestList) {
						
						if( patientName != null && !patientName.isEmpty()&& claimRequest.getAdmission().getPatientId() != null ){
							
								Patient patient = coreDataModelManager.getPatient(claimRequest.getAdmission().getPatientId());
								
								if ( patient.getFullName() != null && patient.getFullName().toLowerCase().contains( patientName.toLowerCase() )  ) {
									ClaimRequestBM claimRequestBM = converter.convertAdmissionClaimRequestDM2BM( claimRequest );
									if( claimRequestBM != null ){
										AdmissionLightBM admissionLightBM = this.getAdmissionInfo(claimRequest.getAdmission().getAdmissionReqNbr());
										claimRequestBM.setPatientName( admissionLightBM.getPatientName() );
										claimRequestBM.setEstimatedCost( admissionLightBM.getEstimatedTreatmentAmnt() );
										claimRequestBM.setEstimationGivenBy( admissionLightBM.getEstimatedBy() );
										claimRequestBM.setInsuranceAmount( this.getInsuranceAmount( claimRequest.getInsurancePlans().getPlanCd(), claimRequest.getRequestedAmount()));
										claimRequestBMList.add( claimRequestBM );
									}
								}
					}else{

						ClaimRequestBM claimRequestBM = converter.convertAdmissionClaimRequestDM2BM( claimRequest );
						if( claimRequestBM != null ){
							AdmissionLightBM admissionLightBM = this.getAdmissionInfo(claimRequest.getAdmission().getAdmissionReqNbr());
							claimRequestBM.setPatientName( admissionLightBM.getPatientName() );
							claimRequestBM.setEstimatedCost( admissionLightBM.getEstimatedTreatmentAmnt() );
							claimRequestBM.setEstimationGivenBy( admissionLightBM.getEstimatedBy() );
							claimRequestBM.setInsuranceAmount( this.getInsuranceAmount( claimRequest.getInsurancePlans().getPlanCd(), claimRequest.getRequestedAmount()));
							claimRequestBMList.add( claimRequestBM );
						}
					}
				}
		}		
			
			//----------------------------------------------------------------
			
			ListRange listRange = new ListRange();
			
			List<ClaimRequestBM> pageSizeData = new ArrayList<ClaimRequestBM>();
			int index = 0;
			if (claimRequestBMList != null && claimRequestBMList.size() > 0) {
			while( (start+index < start + count) && (claimRequestBMList.size() > start+index) ){
				
				ClaimRequestBM claimRequestBM = claimRequestBMList.get(start+index);
				claimRequestBM.setSeqNbr( start + index +1 );
				pageSizeData.add( claimRequestBM );
					index++;
			}
				listRange.setData(pageSizeData.toArray());
				listRange.setTotalSize(claimRequestBMList.size());
			}else {
				listRange.setData(new Object[0]);
				listRange.setTotalSize(0);
			}
			
			return listRange;
		} catch (RuntimeException e) {
			Fault fault = new Fault(ApplicationErrors.READ_CLAIM_REQUEST_FAILED);
			HCISException hcisException = new HCISException(fault, e);
			throw hcisException;
		}
	}

	/* This method tries to remove the sponsors and related details from database.
	 * However it will succeed only when the given sponsor is not referred any where else(i.e. claim request etc.)
	 * Otherwise throws exception (database constraints violation.)  
	 * 
	 * (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.insurance.InsuranceManager#removeSponsor(java.util.List)
	 */
	
	public boolean removeSponsors( List<String> sponsorNameList ) throws HCISException{
		
		try {
			if( sponsorNameList != null && !sponsorNameList.isEmpty() ){
				for ( String sponsorName : sponsorNameList ){
					this.removeSponsor(sponsorName);
				}
				return true;
			}
			return false;
		} catch (HCISException e) {
			throw e;
		}
	}
	
	private void removeSponsor( String sponsorName ){
		
			
			try {
				ClaimSponsor sponsor = dataModelManager.getClaimSponsor(sponsorName);
				
				//Delete old SLA Entry first
				
				try {
					List<ClaimSponsorSla> claimSponsorSlaList = claimSponsorSlaDAO.findByProperty("id.sponsorName", sponsorName );
					
					if( claimSponsorSlaList != null && !claimSponsorSlaList.isEmpty() ){
						for( ClaimSponsorSla sla : claimSponsorSlaList ){
							claimSponsorSlaDAO.delete( sla );
						}
					}
				} catch ( Exception e) {
					
					Fault fault = new Fault( ApplicationErrors.REMOVE_SPONSOR_SLA_FAILED );
					
					throw new HCISException( fault.getFaultMessage() + e.getMessage(),
											 fault.getFaultCode(),
											 fault.getFaultType() );
				}
				
//			Delete the association with Insurer (If any).
				List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.sponsorName", sponsorName );
				
				if( sponsorInsurerAssociationList!= null && !sponsorInsurerAssociationList.isEmpty()){
					
					for( SponsorInsurerAssociation  sponsorInsurerAssociation : sponsorInsurerAssociationList ){
						
						sponsorInsurerAssociationDAO.delete( sponsorInsurerAssociation );
					}
				}

				
//				Delete corresponding account
/*				if( sponsor.getAccountNumber() != null ){
					eagleIntegration.deleteAccountId( sponsor.getAccountNumber() );
				}
*/				
			Integer contactCode = sponsor.getContactCode();
				
				claimSponsorDAO.delete( sponsor );
				//claimSponsorDAO.getSessionFactory().getCurrentSession().flush();
//				Delete contact details 
					
					ContactDetails contactDetails = contactDetailsDAO.findById( contactCode );
					contactDetailsDAO.delete( contactDetails );
			
			} catch (Exception e) {
				Fault fault = new Fault( ApplicationErrors.REMOVE_SPONSOR_FAILED );
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
			}
		
	}
	/*The method  deletes remove association(s) with sponsor and plans for the Insurers(s).
	 * (non-Javadoc)
	 * @see in.wtc.hcis.ip.bo.insurance.InsuranceManager#removeInsurers(java.util.List)
	 */
	 public boolean removeInsurers( List<String> insurerNameList ) throws HCISException{
		 
		 if( insurerNameList != null && !insurerNameList.isEmpty() ){
			 for( String insurerName : insurerNameList ){
				 this.removeInsurer(insurerName);
			 }
			 return true;
		 }
		 
		 return false;
	 }
	 
/**
 * This method first try to delete associated plan first then deletes association with sponsor.
 * If neither plan nor insure is used in any claim request then only the method successes.
 * 
 * @param insurerName
 */
	 private void removeInsurer( String insurerName ){
		 
		 try {
			Insurer insurer = dataModelManager.getInsurer(insurerName);
	
//			Try to Delete related plan first
			
			 try {
				List<InsurancePlans> insurancePlansList = insurancePlansDAO.findByProperty( "insurer.insurerName", insurerName );
				 if(insurancePlansList != null && !insurancePlansList.isEmpty() ){
					 for( InsurancePlans insurancePlans : insurancePlansList ){
						 this.removeInsurancePlan( insurancePlans.getPlanCd() );
					 }
				 }
			} catch (Exception e) {
				Fault fault = new Fault(ApplicationErrors.REMOVE_INSURANCE_PLAN_FAILED);
				
				throw new HCISException(fault, e);
			}
			 
				
//			Delete association with sponsor(s)
				
			List<SponsorInsurerAssociation> sponsorInsurerAssociationList = sponsorInsurerAssociationDAO.findByProperty("id.insurerName", insurer.getInsurerName() );
			
			if( sponsorInsurerAssociationList!= null && !sponsorInsurerAssociationList.isEmpty()){
				
				for( SponsorInsurerAssociation  sponsorInsurerAssociation : sponsorInsurerAssociationList ){
					
					sponsorInsurerAssociationDAO.delete( sponsorInsurerAssociation );
				}
			}
				
//				Delete contact details 
					
			ContactDetails contactDetails = contactDetailsDAO.findById( insurer.getContactCode() );
			contactDetailsDAO.delete( contactDetails );
			insurerDAO.delete( insurer );
		
		 } catch (Exception e) {
			 Fault fault = new Fault( ApplicationErrors.REMOVE_INSURER_FAILED );
				
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
										 fault.getFaultCode(),
										 fault.getFaultType() );
		}
		 
	 }
	 
	public boolean removeInsurancePlans(List<String> planCodeList ){
		
		if( planCodeList != null && !planCodeList.isEmpty() ){
			for ( String planCode : planCodeList ){
				this.removeInsurancePlan( planCode );
			}
			return true;
		}
		return false;
	}
	
	
	private void removeInsurancePlan( String planCode ){
		
		try {
			InsurancePlans insurancePlans = dataModelManager.getInsurancePlan( planCode );
			
			List<PlanCoversDisease> planCoversDiseaseList = planCoversDiseaseDAO.findByProperty("id.planCd", planCode );
			
			if( planCoversDiseaseList != null && !planCoversDiseaseList.isEmpty()){
				
				for( PlanCoversDisease planCoversDisease : planCoversDiseaseList){
					
					planCoversDiseaseDAO.delete( planCoversDisease );
				}
			}
			
			List<PlanHasServices> planHasServicesList = planHasServicesDAO.findByProperty("id.planCd", planCode );
			
			if( planHasServicesList != null && !planHasServicesList.isEmpty() ){
				
				for( PlanHasServices planHasServices : planHasServicesList){
				
					planHasServicesDAO.delete( planHasServices );
				}
			}
			
			insurancePlansDAO.delete( insurancePlans );
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REMOVE_INSURANCE_PLAN_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	
	public boolean removeCliamRequest( Long[] requestSequenceNbrList){
		
		if( requestSequenceNbrList != null && requestSequenceNbrList.length >0 ){
			for( Long requestSequenceNbr : requestSequenceNbrList ){
				this.removeClaimRequest(requestSequenceNbr);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes the claim request which is in 'CREATED' status.
	 * @param requestSequenceNbr
	 */
	private void removeClaimRequest(Long requestSequenceNbr ){
		
		try {
			List<AdmissionClaimActivity> claimActivityList = admissionClaimActivityDAO.findByProperty("id.requestSequenceNbr", requestSequenceNbr);
			//It should return only 1 record; as claim is just created and no further action has took place.
			if( claimActivityList != null && claimActivityList.size() == 1 && 
					claimActivityList.get(0).getActivityType().getActivityTypeCd().equals(InsuranceManagementConstants.CLAIM_REQ_ACTIVITY_PREFIX+InsuranceManagementConstants.CLAIM_STATUS_CREATED)){
//			Go ahead for deletion.
				
				admissionClaimActivityDAO.delete( claimActivityList.get(0) );
				
				AdmissionClaimRequest admissionClaimRequest = dataModelManager.getAdmissionClaimRequest(requestSequenceNbr);
				
				List<ClaimDocument> claimDocumentList = claimDocumentDAO.findByProperty("id.requestSequenceNbr", requestSequenceNbr );
				
				if( claimDocumentList != null && !claimDocumentList.isEmpty()){
					for( ClaimDocument claimDocument : claimDocumentList ){
					
						claimDocumentDAO.delete( claimDocument );
					}
				}
				
				admissionClaimRequestDAO.delete( admissionClaimRequest );
			}else{
				throw new Exception(" It is possible to delete claim request with Created status only ");
			}
		}catch (Exception e) {
			 Fault fault = new Fault( ApplicationErrors.REMOVE_CLAIM_REQUEST_FAILED );
				
				throw new HCISException( fault.getFaultMessage() + e.getMessage(),
											 fault.getFaultCode(),
											 fault.getFaultType() );
		}
		
	}
	
	/**
	 * This method modified the status of claim request.The method is intended to be used as action against
	 * one claim request. In case of partial approval the field approvedAmount must be passed from UI.
	 */
	
	public void claimrequestActions( Long requestSequenceNbr, String newStatusCd,
							 String modifiedBy, String remarks,Double approvedAmount ){
		try{
			
			if( newStatusCd != null ){
				newStatusCd = newStatusCd.trim();
			}
			AdmissionClaimRequest claimRequest = dataModelManager.getAdmissionClaimRequest(requestSequenceNbr);
			
			SponsorClaimStatus currentClaimStatus = claimRequest.getSponsorClaimStatus();
			SponsorClaimStatus newClaimStatus = dataModelManager.getSponsorClaimStatus(newStatusCd );
			
			
			if( this.isValidStatusTransition(currentClaimStatus.getClaimStatusCd(),newStatusCd ) ){
				
				claimRequest.setSponsorClaimStatus(newClaimStatus);
				
				if( InsuranceManagementConstants.CLAIM_STATUS_SUBMITTED.equals(newStatusCd )){
					claimRequest.setRequestDtm( new Date());
					
				}else if( InsuranceManagementConstants.CLAIM_STATUS_PARTAPPROVED.equals(newStatusCd ) ){
					
					//in case of "PARTAPPROVED" there should be some approvalAmount come from front end
					
					if( claimRequest.getRequestedAmount().doubleValue() < approvedAmount.doubleValue() ){
						throw new Exception("Approval amount ("+ approvedAmount + 
											") can not be greater then requested amount ("
											+ claimRequest.getRequestedAmount() + ")");
					}
					
					claimRequest.setApprovedAmount(approvedAmount);
				}else if( InsuranceManagementConstants.CLAIM_STATUS_APPROVED.equals(newStatusCd)){
					/*in case of "Approved" if requested amount is greater than the maximum coverage amount.
					If it is the case then exception is raised*/
					claimRequest.setApprovalDtm( new Date() );
					Double sponsorAmount = new Double(0);
					Double requestedAmount = claimRequest.getRequestedAmount().doubleValue();
					InsurancePlans insurancePlans = claimRequest.getInsurancePlans();
					
					if(  insurancePlans.getTotalCoverageAmt() != null){
						
						sponsorAmount = insurancePlans.getTotalCoverageAmt();
					}
					
					if( requestedAmount > sponsorAmount ){
						throw new Exception("Requested amount ("+ requestedAmount + 
											") can not be greater than maximum coverage amount ("
											+ sponsorAmount + ")");
					}else{
						claimRequest.setApprovedAmount( requestedAmount );
					}
				}
				
				claimRequest.setLastModifiedDtm( new Date() );
				claimRequest.setModifiedBy( modifiedBy );
				
				admissionClaimRequestDAO.attachDirty(claimRequest);
				//create corresponding activity log
				
				ClaimRequestActivityBM activityBM = new ClaimRequestActivityBM();
				
				activityBM.setRequestSequenceNbr(requestSequenceNbr);
				activityBM.setSponsorClaimStatus( new CodeAndDescription(newStatusCd,""));
				activityBM.setActivityType(new CodeAndDescription(InsuranceManagementConstants.CLAIM_REQ_ACTIVITY_PREFIX+newStatusCd,""));
				activityBM.setRemarks(remarks);
				activityBM.setCreatedBy(modifiedBy);
				this.createClaimRequestActivity(activityBM);
			}else{
				// The status transition is not valid
				
				throw new Exception("The status trasition from '" + currentClaimStatus.getClaimStatusDesc()+
									"' to '"+ newClaimStatus.getClaimStatusCd() + "' is not allowed !");
				
			}
		}catch( Exception e ){
			Fault fault = new Fault( ApplicationErrors.MODIFY_CLAIM_REQUEST_FAILED);
			throw new HCISException( fault,e);
		}
	}
	
	
	/*
	  * This method calculates and returns the total coverage amount, against the given Insurance-
	  *  plan and requested amount.
	  * @param planCode
	  * @param requestedAmount
	  * @return
	  * @throws HCISException
	  */
	 public Double getInsuranceAmount(String planCode, Double requestedAmount ) throws HCISException{
		 
		 InsurancePlans insurancePlans = dataModelManager.getInsurancePlan( planCode );
			
		 
		 	
		Double sponsorAmount = new Double(0);
		
		if ( requestedAmount == null || requestedAmount <= 0 ) {
			return sponsorAmount;
		}

		if (insurancePlans.getPercentAbsInd().equals(InsuranceManagementConstants.PERCENT_ABS_IND_ABSOLUTE)) {

			if ( requestedAmount <= insurancePlans.getTotalCoverageAmt() ) {
				sponsorAmount = requestedAmount;
			} else {
				sponsorAmount = insurancePlans.getTotalCoverageAmt();
			}

		} else if (insurancePlans.getPercentAbsInd().equals(InsuranceManagementConstants.PERCENT_ABS_IND_PRECENT)) {
			if(insurancePlans.getTotalCoverageAmt() != null){
				sponsorAmount = ( requestedAmount * insurancePlans.getTotalCoverageAmt() / 100.0);
			}
		}
		
		return  sponsorAmount;
	 }
	 
	 /**
	  * This method calculates the exact amount that the sponsor will be liable to pay to the
	  * health care provider. This calculation is purely based on 
	  * 1) the insurance plan set by the sponsor with the health care provider
	  * 2) the claim request details, which indicates
	  *    1) how much amount was approved and
	  *    2) how much amount has already been claimed against the request
	  * 3) current balance amount of the account 
	  * 
	  * @param claimRequest
	  * @param additionalAmountDuringThisBill
	  * @return
	  * @throws HCISException
	  */
	 private ClaimAmountDetails getActualClaimAmount( AdmissionClaimRequest claimRequest, 
			                              Double additionalAmountDuringThisBill )  throws HCISException {
		 ClaimAmountDetails claimAmountDetails = new ClaimAmountDetails();
		 claimAmountDetails.setRequestSequenceNbr(claimRequest.getRequestSequenceNbr());
		 Double actualClaimAmount = 0.0;
		 Double alreadyClaimAmount = 0.0;
		 Double patientAmount = 0.0;
		 
		 if ( claimRequest.getPatientAmount() != null ) {
			 claimAmountDetails.setPatientAmount( claimRequest.getPatientAmount() ); 
		 } else {
			 claimAmountDetails.setPatientAmount( 0.0 );
		 }
		 
		 InsurancePlans insurancePlans = dataModelManager.getInsurancePlan( claimRequest.getInsurancePlans().getPlanCd() );
		 
		 Double approvedAmount = 0.0;
		 if( claimRequest.getApprovedAmount() != null ){
			 approvedAmount = claimRequest.getApprovedAmount();
		 }
		 
		 if ( claimRequest.getFinalClaimedAmount() != null ) {
			 alreadyClaimAmount = claimRequest.getFinalClaimedAmount();
		 }
		 
		 if (insurancePlans.getPercentAbsInd().equals( InsuranceManagementConstants.PERCENT_ABS_IND_ABSOLUTE ) ) {
			 if ( additionalAmountDuringThisBill <= (approvedAmount - alreadyClaimAmount ) ) {
				 actualClaimAmount = additionalAmountDuringThisBill;
			 } else {
				 actualClaimAmount = approvedAmount - alreadyClaimAmount;
			 }
		 } else if (insurancePlans.getPercentAbsInd().equals(InsuranceManagementConstants.PERCENT_ABS_IND_PRECENT)) {
			 Double sponsorShare = 1.0;
			 
			 if( insurancePlans.getTotalCoverageAmt() != null ){
				 
				 sponsorShare = insurancePlans.getTotalCoverageAmt() / 100.0;
			 }
			 Double maxPossibleClaimAmount = additionalAmountDuringThisBill * sponsorShare ;
			 
			 if ( maxPossibleClaimAmount <= ( approvedAmount - alreadyClaimAmount ) ) {
				 actualClaimAmount = maxPossibleClaimAmount;
			 } else {
				 actualClaimAmount = approvedAmount - alreadyClaimAmount;
			 }
			 
			 //
			 // Assume that as part of this claim - we are trying to settle amount x. 
			 // Sponsor needs to pay amount y * x and patient needs to pay (1-y) * x
             //         where y = <<Coverage amount in percentage>> / 100.00. 
			 //         for example -- if sponsor is supposed to pay 80% then y = 80/100 = .8
			 // actualClaimAmount is the amount that sponsor is supposed to pay.
			 // Hence, y * x = actualClaimAmount
			 // i.e. x = actualClaimAmount / y
			 // paientAmount = (1-y) * x = (1-y) * (actualClaimAmount / y)
			 //
			 
			 patientAmount = (new Double(1.0) - sponsorShare ) * (actualClaimAmount / sponsorShare );
		 }
		 claimAmountDetails.setAdditionalClaimedAmount(actualClaimAmount);
		 claimAmountDetails.setFinalClaimedAmount( actualClaimAmount + alreadyClaimAmount );
		 
		 if( claimAmountDetails.getPatientAmount() != null ){
			 
			 claimAmountDetails.setPatientAmount( claimAmountDetails.getPatientAmount() + patientAmount);
		 }else{
			 claimAmountDetails.setPatientAmount( patientAmount);
		 }
		 claimAmountDetails.setAdditionalPatientAmount(patientAmount);
		 claimAmountDetails.setApprovedAmount(approvedAmount);
		 
		 return claimAmountDetails;
	 }
	
	
	 
	 /* This method gives the name of the patient corresponding to
		* admission request number.
		* Implemented only for front end use.
		*/
		public AdmissionLightBM getAdmissionInfo(Integer admissionReqNbr){

		try {
			if(admissionReqNbr != null){
				Admission admission = dataModelManager.getAdmission( admissionReqNbr );
				AdmissionLightBM admissionLightBM = new AdmissionLightBM();
				
				if(admission != null){
					
					admissionLightBM.setAdmissionReqNbr( admission.getAdmissionNbr() );
					admissionLightBM.setAdmissionNbr( admission.getAdmissionNbr() );
					admissionLightBM.setEstimatedTreatmentAmnt( admission.getTreatmentEstimatedCost() );
				    admissionLightBM.setEstimatedBy( admission.getTreatmentEstimationBy() );
				    admissionLightBM.setPatientId( admission.getPatientId() );
					
				    Patient patient = coreDataModelManager.getPatient( admission.getPatientId() );
					if(patient != null ){
						String patientName = "";
						if(patient.getFullName() != null && patient.getFullName().length() > 0){
							patientName = patient.getFullName();
						}
						admissionLightBM.setPatientName(patientName);	
					}
				}
				return admissionLightBM;
			}
		} catch (HCISException e) {
		e.printStackTrace();
		}
		return null;

		}
		
	/**
	 * Process mediclaim method evaluates the sponsors and patients amount and
	 * modifies the entry on database accordingly.This is supposed to call at the
	 * time of billing.
	 * As the part of process this method also creates adjustments on accounting
	 * tables.
	 * Finally it returns the data structure having the details to be entered as the
	 * bill item details.
	 * 
	 * @param referenceType- 
	 * @param accountNumber- Admission account number
	 * @param billNumber - bill number should come from billManager
	 * @return
	 */
	public BillDataBM processMediclaim( String referenceType, 
			                            String  accountNumber, 
			                            Long billNumber) {
		try {
//			if ( referenceType == null ) {
//				referenceType = "";
//			} else {
//				referenceType = referenceType + "-";
//			}
			
			Integer admissionReqNbr = null;
			Integer accountId = Integer.valueOf(accountNumber);
			//
			//Get the admission object from accountId
			List<EntityAcctMap> entityAcctMapList = entityAcctMapDAO.findByBusinessPartnerId(accountId);
			
			if(entityAcctMapList != null && !entityAcctMapList.isEmpty()){
				admissionReqNbr = entityAcctMapList.get(0).getId().getEntityId();
			}
			
			BillDataBM billDataBM = null;
			HashMap<String, BillingSubprocessBM>billDataMap = null ;
			BillingSubprocessBM billingSubprocessBM = null;
			List<BillDetailsBM>billDetailsBMList = null;
			Double subProcessTotals = 0.0;
			
			Calendar today = Calendar.getInstance();
			//List<AcReceivable>accountReceivableList = receivableDAO.getReceivables(accountId); 
			
			Admission admission = dataModelManager.getAdmission(admissionReqNbr);
			
			List<ReceivableBM> receivableBMList = eagleIntegration.getReceivables(accountId);
			
			if ( receivableBMList != null && !receivableBMList.isEmpty() ) {
				//
				// We have got all the receivable. Sum of all the receivable amount will be 
				// total billed treatment cost at this moment.
				//
				Double totalTreatmentAmt = 0.0;
				Double additionalAmountDuringThisBill = 0.0;
				
				for ( ReceivableBM receivableBM : receivableBMList ) {
					totalTreatmentAmt += receivableBM.getAmount();
				}
			
				Double treatmentActualCost = 0.0;
				if( admission.getTreatmentActualCost() != null  ){
					treatmentActualCost = admission.getTreatmentActualCost();
				}
				additionalAmountDuringThisBill = totalTreatmentAmt.doubleValue() - treatmentActualCost;
				
				admission.setTreatmentActualCost(totalTreatmentAmt);
				admission.setLastUpdatedDtm( today.getTime() );
				admission.setModifiedBy( InsuranceManagementConstants.BILLING_PERSON_ID);
				admissionDAO.attachDirty( admission );
				
				//
				// We just need to retrieve those requests which have not yet been completely exhausted.
				// Whenever, any request is processed, its final claim amount would be incremented by the
				// actual claim amount claimed against that request. 
				// Method "getUnExhaustedClaimRequest" also returns requests in the order preferred by the patient. Hence, in case of 
				// multiple claim request, exhaust the claim amount in the same order in which the elements are
				// in the claim request list.
				//
				List<AdmissionClaimRequest> admissionClaimRequestList = admissionClaimRequestDAO.getUnExhaustedClaimRequest( admissionReqNbr );
				
				if( admissionClaimRequestList != null && !admissionClaimRequestList.isEmpty() ){
					for(AdmissionClaimRequest claimRequest : admissionClaimRequestList  ){
						
						String sponsorAccount = claimRequest.getClaimSponsor().getAccountNumber();
						
						if( sponsorAccount == null ){
							throw new Exception(" Sponsor account is not found! ");
						}
						String remarkForSponsor = "Claim from Sponsor :" + claimRequest.getClaimSponsor().getSponsorsName();
						//
						// Find out the plan used for the claim request. Identify the patient amount and actual amount claimed
						// from the sponsor during this billing.
						//
						
						ClaimAmountDetails claimAmountDetails = this.getActualClaimAmount(claimRequest, additionalAmountDuringThisBill );
						additionalAmountDuringThisBill = additionalAmountDuringThisBill 
						                                 - claimAmountDetails.getAdditionalPatientAmount() 
						                                 - claimAmountDetails.getAdditionalClaimedAmount();
						
						claimRequest.setBillNbr(billNumber);
						claimRequest.setFinalClaimedAmount( claimAmountDetails.getFinalClaimedAmount() );
						claimRequest.setPatientAmount( claimAmountDetails.getPatientAmount() );
						claimRequest.setLastModifiedDtm( today.getTime());
						claimRequest.setModifiedBy( InsuranceManagementConstants.BILLING_PERSON_ID );
						admissionClaimRequestDAO.attachDirty( claimRequest );
						
						//create receipt  using patient's account, with the amount the sponsor 
						//suppose to pay on behalf of patient (the actual treatment amount covered by
						//the insurance plan) and create payment using the sponsors account with the same amount
						// , which will be billed as per the agreement  between sponsor and health-care provider
						//
						// As part of this call, return the adjustment created for the patient to the billing process.
						//
						
						eagleIntegration.createReceipt(accountId, claimAmountDetails.getAdditionalClaimedAmount(),
													   null, InsuranceManagementConstants.BILLING_PERSON_ID ,
													   String.valueOf(billNumber), " claim from sponsor " + claimRequest.getClaimSponsor().getSponsorsName());
						
						
						
						//TODO: Passing bill number will have some significance? confirm from Eagle 
						eagleIntegration.createPayment(Integer.valueOf(sponsorAccount), claimAmountDetails.getAdditionalClaimedAmount(),null, remarkForSponsor);
						
						if( billDataBM == null ){
							billDataBM = new BillDataBM();
							
							billDataBM.setProcessName(InsuranceManagementConstants.BILL_PROCESS_NAME_ACCOUNTING);
							billDataBM.setProcessTotalBillAmount(0.0);
							billDataMap = new HashMap<String, BillingSubprocessBM>(0);
							billDataBM.setBillDetailsMap(billDataMap);
							
							billingSubprocessBM = new BillingSubprocessBM();
							billingSubprocessBM.setSubProcessName(InsuranceManagementConstants.CLAIMED_AGAINST_SPONSORS);
							billDetailsBMList = new ArrayList<BillDetailsBM>(0);
						}
						
						BillDetailsBM billDetailsBM = new BillDetailsBM();
						
						billDetailsBM.setItemCount( 1 );
						billDetailsBM.setItemName( remarkForSponsor );
						billDetailsBM.setItemSequenceNbr(billDetailsBMList.size() + 1);
						billDetailsBM.setItemPrice( claimAmountDetails.getAdditionalPatientAmount() );
						billDetailsBM.setDiscounts(0.0);
						billDetailsBM.setNetPrice( claimAmountDetails.getAdditionalPatientAmount() );
						billDetailsBM.setTransactionDate( new Date() );
						
						billDetailsBMList.add( billDetailsBM );
						subProcessTotals +=   claimAmountDetails.getAdditionalPatientAmount();
						
						
						if ( additionalAmountDuringThisBill <= 0 ) {
							//
							// Even though we may have more claim request, there is nothing left to be claimed.
							//
							break;
						}
					}
				}
				if( billingSubprocessBM != null ){
					billingSubprocessBM.setBillDetailsList(billDetailsBMList);
					billingSubprocessBM.setSubProcessTotals(subProcessTotals);
					billDataMap.put(InsuranceManagementConstants.CLAIMED_AGAINST_SPONSORS, billingSubprocessBM);
				}
			}
			
			return billDataBM;
		} catch (Exception e) {
		
			Fault fault = new Fault(ApplicationErrors.ERROR_ENCOUNTERED);
			throw new HCISException( fault, e);
		}
	}
		
	
	public void setConverter(IPDataModelConverter converter) {
		this.converter = converter;
	}

	public void setDataModelManager(IPDataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setCoreDataModelManager(DataModelManager coreDataModelManager) {
		this.coreDataModelManager = coreDataModelManager;
	}

	public void setPlanCoversDiseaseDAO(PlanCoversDiseaseDAO planCoversDiseaseDAO) {
		this.planCoversDiseaseDAO = planCoversDiseaseDAO;
	}

	public void setPlanHasServicesDAO(PlanHasServicesDAO planHasServicesDAO) {
		this.planHasServicesDAO = planHasServicesDAO;
	}

	public void setAdmissionClaimRequestDAO(
			AdmissionClaimRequestDAOExtn admissionClaimRequestDAO) {
		this.admissionClaimRequestDAO = admissionClaimRequestDAO;
	}

	public void setContactDetailsDAO(ContactDetailsDAO contactDetailsDAO) {
		this.contactDetailsDAO = contactDetailsDAO;
	}

	public void setSponsorInsurerAssociationDAO(
			SponsorInsurerAssociationDAO sponsorInsurerAssociationDAO) {
		this.sponsorInsurerAssociationDAO = sponsorInsurerAssociationDAO;
	}

	public void setInsurancePlansDAO(InsurancePlansDAOExtn insurancePlansDAO) {
		this.insurancePlansDAO = insurancePlansDAO;
	}

	public void setClaimSponsorDAO(ClaimSponsorDAOExtn claimSponsorDAO) {
		this.claimSponsorDAO = claimSponsorDAO;
	}

	public void setClaimSponsorSlaDAO(ClaimSponsorSlaDAO claimSponsorSlaDAO) {
		this.claimSponsorSlaDAO = claimSponsorSlaDAO;
	}

	public void setClaimDocumentDAO(ClaimDocumentDAO claimDocumentDAO) {
		this.claimDocumentDAO = claimDocumentDAO;
	}

	public void setAdmissionClaimActivityDAO(
			AdmissionClaimActivityDAOExtn admissionClaimActivityDAO) {
		this.admissionClaimActivityDAO = admissionClaimActivityDAO;
	}

	public void setDiseaseRequiresServiceDAO(
			DiseaseRequiresServiceDAO diseaseRequiresServiceDAO) {
		this.diseaseRequiresServiceDAO = diseaseRequiresServiceDAO;
	}

	public void setInsurerDAO(InsurerDAOExtn insurerDAO) {
		this.insurerDAO = insurerDAO;
	}

	public void setDiseaseDAO(DiseaseDAOExtn diseaseDAO) {
		this.diseaseDAO = diseaseDAO;
	}

	public void setAdmissionDAO(AdmissionDAO admissionDAO) {
		this.admissionDAO = admissionDAO;
	}

	public void setEntityAcctMapDAO(EntityAcctMapDAO entityAcctMapDAO) {
		this.entityAcctMapDAO = entityAcctMapDAO;
	}

	
}
