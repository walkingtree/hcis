/**
 * 
 */
package in.wtc.hcis.ip.bo.insurance;

import java.util.Date;
import java.util.List;

import in.wtc.billing.bm.BillDataBM;
import in.wtc.hcis.bm.ip.AdmissionLightBM;
import in.wtc.hcis.bm.ip.ClaimRequestActivityBM;
import in.wtc.hcis.bm.ip.ClaimRequestBM;
import in.wtc.hcis.bm.ip.InsurancePlanBM;
import in.wtc.hcis.bm.ip.InsurerBM;
import in.wtc.hcis.bm.ip.SponsorDetailsBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

/**
 * @author Alok Ranjan
 * 
 * This module manages everything related to insurance in health care
 * At high level, this module is covering following functionality
 * 1) Managing relationship with the insurer. 
 * 		-- Examples of insurer include 
 * 			-- Employers
 *          -- Insurance providing companies (e.g. Tata AIG, Future Generali, Reliance)
 *          -- Intermediaries of insurance providing companies (Third Party Administrators)
 *      -- As part of managing relationships, hospital requires following:
 *          -- Defining payment terms
 *          -- Assigning credit class
 *          -- Configuring commissions     
 * 2) Managing insurance requests during an admission
 * 	    -- Requesting for complete amount from the same insurer
 * 		-- Requesting for partial amount from one or more insurer
 * 		-- Follow-up
 * 		-- Integration with billing
 */


public interface InsuranceManager {
	/**
	 * This method create a sponsor in the systems. Although, primarily, we will have following sponsors
	 * 1) Employers
	 * 2) Insurance providing companies 
	 * 3) Third Party Administrators
	 * 
	 * The system should provide flexibility of defining more sponsors. For example
	 * 1) Some NGOs
	 * 2) Some government programs (for example something related to BPL)
	 * 
	 * @param sponsorDetailsBM
	 * @return
	 * @throws HCISException
	 */
	SponsorDetailsBM createClaimSponsor( SponsorDetailsBM sponsorDetailsBM ) throws HCISException;
	
	/**
	 * This method crates new Insurer in the system.Every insurance plan on the system will associated with
	 * the insurer only. And hospital never interact directly with Insurer.  
	 * @param insurerBM
	 * @return
	 * @throws HCISException
	 */
	InsurerBM createInsurer( InsurerBM insurerBM ) throws HCISException;
	
	/**
	 * All the parameters of this method are optional.
	 * 
	 * @param sponsorName
	 * @param sponsorType
	 * @param sponsorDescription
	 * @param creditClassCode
	 * @param accountNumber
 	 * @param address
	 * @return
	 * @throws HCISException
	 */
	List<SponsorDetailsBM>getSponsors( String sponsorName, 
		                               String sponsorType,
		                               String sponsorDescription,
		                               String creditClassCode,
		                               String accountNumber,
		                               String insurerName )  throws HCISException; 
	
	
	/**
	 * This method modifies sponsor details, including details about 
	 * 1) the credit class assigned to them 
	 * 2) payment terms  ( will be added later)
	 * 3) commissions ( will be added later)
	 *  
	 * @param sponsorDetailsBM
	 * @return
	 * @throws HCISException
	 */
	SponsorDetailsBM modifyClaimSponsor( SponsorDetailsBM sponsorDetailsBM ) throws HCISException;
	
	/**
	 * This method creates a claim request for a given admission.
	 * 
	 * It is assumed that there is a possibility that entry for claim request has been created but request for claim is not been sent 
	 * to sponsor.In this case whenever the request will be sent to sponsor the corresponding record should be modified with appropriate data.  
	 * 
	 * While creating claim request we also have to  make sure the following - 
	 * 
	 * 1) The claim amount should not exceed the maximum coverage amount of the plan, against which the
	 * 	  request is being made.
	 * 2) Method should make sure that request should not include the services which are not covered by
	 *    the plan.
	 *  
	 * @param claimRequestBM
	 * @return
	 * @throws HCISException
	 */
	ClaimRequestBM createClaimRequest( ClaimRequestBM claimRequestBM )  throws HCISException; 
	
	/**
	 * This method is used to maintain life-cycle of insurance claim policy. At high level we have
	 * following steps in any insurance processing
	 * 1) Patient fills-up all his details into an insurance form (since this is usually a legal document
	 *    we are not going to implement this part at this moment)
	 * 2) Insurance coordinator sends Fax to the insurance form to the insurer
	 * (Here we must have an insurance claim request created in our system)
	 * 3) Insurer gets back to the hospital with current status of the insurance claim or hospital
	 *    sends inquiry about the status of the insurance claim to the insurer. One of the following
	 *    happens:
	 *    1) Claim rejected
	 *    2) Requires more information
	 *    3) Claim partially approved
	 *    4) Claim completely approved
	 * 4) In case, claim is partially or completely approved then accounting needs to be notified to 
	 *    consider the approved amount as paid. Accounting module needs to do following:
	 *    1) Create a receivable with account number as insurer's account number and reference as 
	 *       the current admission number
	 *    2) Create a credit adjustment for the patient using the current admission number as an account
	 *       number and policy number and insurer's code as remarks (or some sort of reference)
	 * 5) Following are the possible status in which a claim request may exist:
	 * 		1) CREATED -- Claim request has been created
	 * 		2) SUBMITTED -- Claim request has been submitted to the insurer
	 * 		3) APPROVED	-- Insurer has approved complete amount requested by the hospital
	 * 		4) PARTAPPROVED -- Insurer has partially approved the requested amount 
	 * 		5) REJECTED	-- Insurer has rejected the requested insurance amount
	 * 		6) MOREINFO	-- Insurer has requested for more information from the hospital
	 * 		7) RESUBMITTED -- The hospital has sent the requested information to the insurer and asked for approval again
	 * 6) Following table defines the valid transition among different states
	 * 			From Status 	|	To Status
	 * ------------------------------------------------------------------------
	 * 		1) CREATED  		|	SUBMITTED
	 * 		2) SUBMITTED  		|	APPROVED, PARTAPPROVED, REJECTED, MOREINFO
	 * 		3) APPROVED			|	-
	 * 		4) PARTAPPROVED 	|	-
	 * 		5) REJECTED			|	-
	 * 		6) MOREINFO			|	RESUBMITTED
	 * 		7) RESUBMITTED      | 	MOREINFO, APPROVED, PARTAPPROVED, REJECTED  
	 * --------------------------------------------------------------------------
	 * @param modifiedClaimRequestBM
	 * @param remarks
	 * @return
	 * @throws HCISException
	 */
	ClaimRequestBM modifyClaimRequests( ClaimRequestBM modifiedClaimRequestBM, String remarks )  throws HCISException;
	
	/**
	 * This method is expected to be most frequently used method during an admission.
	 *  
	 * @param admissionNumber
	 * @return
	 * @throws HCISException
	 */
	List<ClaimRequestBM> getClaimRequestsForAnAdmission( Integer admissionNumber ) throws HCISException;
	
	
	/**
	 * This method creates insurance plans with given plan details.Every plan is associated with any of the existing
	 * Insurer in the system.
	 * 
	 * Every plan can have Information about maximum coverage amount.This amount either could be absolute(in Rs.) or in
	 * terms of percentage. In case of percentage (less then 100) the remaining amount will be taken form the patient.
	 * 
	 * If services and diseases covered under the plan is also given, then creates corresponding entries.
	 * 
	 * @param insurancePlanBM
	 * @return
	 */
	
	  InsurancePlanBM createInsurancePlan( InsurancePlanBM insurancePlanBM) throws HCISException;

	

	/**
	 * This method gives all the insurance plans that matches the search criteria. All parameters of this method are
	 * optional.
	 * @param planCode
	 * @param planName
	 * @param insurerName
	 * @param sponsorName
	 * @param validFromDt
	 * @param validToDt
	 * @return
	 * @throws HCISException
	 */
	List<InsurancePlanBM> getInsurancePlans( String planCode,
										   	 String planName,
											 String insurerName,
											 String sponsorName,
											 Date   validFromDt,
											 Date   validToDt ) throws HCISException;

	
	/**
	 * This method modifies the details of existing insurance plan details.
	 * 
	 * If the association with services and disease is also given, then modify the corresponding records.  
	 * 
	 * @param insurancePlanBM
	 * @return
	 * @throws HCISException
	 */
	
	
	
	InsurancePlanBM modifyInsurancePlan ( InsurancePlanBM insurancePlanBM) throws HCISException;
	
	
	/**
	 * This method will be usually called from inside the create or modify claim request. 
	 * However, there may be situation when we would like to capture some events related to 
	 * the claim request, which doesn't necessarily change the claim status. 
	 * 
	 * @param claimRequestActivityBM
	 * @throws HCISException
	 */
	

	
	
	void createClaimRequestActivity( ClaimRequestActivityBM claimRequestActivityBM ) throws HCISException;
	
	/**
	 * This method is used to retrieve all the activity happening on a claim request. 
	 * All the parameters of this method are optional.
	 * Following parameters support partial lookup:
	 * 1) remarks
	 * 2) activity requested by
	 * 
	 * @param admissionNumber
	 * @param claimRequestSequenceNbr
	 * @param activityTypeCd
	 * @param claimStatusCd
	 * @param remarks
	 * @param activityCreatedBy
	 * @return
	 * @throws HCISException
	 */
	List<ClaimRequestActivityBM>getClaimRequestActivities( String claimRequestSequenceNbr,
			                                               String activityTypeCd,
			                                               String claimStatusCd,
			                                               String remarks,
			                                               String activityCreatedBy ) throws HCISException;
/**
 * This UI specific method returns ListRange of ClaimRequests
 * @param requestSequenceNbr
 * @param admissionNumber
 * @param patientId
 * @param patientName
 * @param sponsorType
 * @param sponsorName
 * @param policyNumber
 * @param claimStatusCode
 * @param claimRequestCreationFromDt
 * @param claimRequestCreationToDt
 * @param claimApprovalFromDt
 * @param claimApprovalToDt
 * @param start
 * @param count
 * @param orderBy
 * @return
 * @throws HCISException
 */	
	ListRange findClaimRequests( Integer requestSequenceNbr,
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
					            int start, int count, String orderBy) throws HCISException;
	
	/**
	 * 
	 * @param sponsorName
	 * @param sponsorType
	 * @param sponsorDescription
	 * @param creditClassCode
	 * @param accountNumber
	 * @param insurerName
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange findSponsors( String sponsorName, 
				            String sponsorType,
				            String sponsorDescription,
				            String creditClassCode,
				            String accountNumber,
				            String insurerName,
				            int start, int count, String orderBy )  throws HCISException;

/**
 * 
 * @param planCode
 * @param sponsorName
 * @param planName
 * @param serviceCode
 * @param diseaseName
 * @param coverageAmntFrom
 * @param coverageAmntTo
 * @param validFromDt
 * @param validToDt
 * @return
 * @throws HCISException
 */
	
	ListRange findInsurancePlans( String planCode,
							   	 String planName,
								 String insurerName,
								 String sponsorName,
								 Date   validFromDt,
								 Date   validToDt,
							   int start, int count, String orderBy ) throws HCISException;
	
	/**
	 * This method remove  sponsors and related details from the system.
	 *  
	 * 
	 * @param sponsorNameList
	 * @return
	 * @throws HCISException
	 */
	 boolean removeSponsors( List<String> sponsorNameList ) throws HCISException;
	 
	 /**
	  * The method removes association(s) with sponsor and plans for the Insurers(s).
	  * @param insurerNameList
	  * @return
	  * @throws HCISException
	  */
	 boolean removeInsurers( List<String> insurerNameList ) throws HCISException;
	
	/**
	 * Removes Insurance plans for given plan code list.
	 * @param planCodeList
	 * @return
	 * @throws HCISException
	 */
	 boolean removeInsurancePlans(List<String> planCodeList ) throws HCISException;
	
	/**
	  * This method calculates and returns the total coverage amount, against the given Insurance-
	  *  plan and requested amount.
	  * @param planCode
	  * @param requestedAmount
	  * @return
	  * @throws HCISException
	  */
	 Double getInsuranceAmount(String planCode, Double requestedAmount ) throws HCISException;
	 
	 /**
	  * This method returns list of Insurers.
	  * All parameters are optional.
	  * Method performs partial lookup for insurerDescription.
	  */
	  List<InsurerBM> getInsurer(String insurerName,
								String insurerDescription,
								String insurancePlanCd, 
								String sponsorName ) throws HCISException;
	  
	  ListRange findInsurer(String insurerName,
							String insurerDescription,
							String insurancePlanCd, 
							String sponsorName ,
							 int start, int count, String orderBy) throws HCISException;
	  
	  /**
	   *This method modify the existing Insurer and its association with the sponsors 
	   * @param insurerBM
	   */
	  
	  void modifyInsurer( InsurerBM insurerBM )throws HCISException;
	  
		/**
		 * This method removes the claim request which is in 'CREATED' status.
		 * @param requestSequenceNbr
		 */ 
	  boolean removeCliamRequest( Long[] requestSequenceNbrList) throws HCISException;
	  
	  /* This method gives the name of the patient corresponding to
		* admission request number.
		* Implemented only for front end use.
		*/
	 AdmissionLightBM getAdmissionInfo(Integer admissionReqNbr);
	

	BillDataBM processMediclaim( String referenceType,
								 String accountId,
								 Long billNumber)  throws HCISException;
	
	/**
	 * 
	 * @param planCd
	 * @return
	 * @throws HCISException
	 */
	InsurancePlanBM getInsurancePlanSummary(String planCd ) throws HCISException;
	
	/**
	 * This method modified the status of claim request.The method is intended to be used as action against
	 * one claim request. In case of partial approval the field approvedAmount must be passed from UI.
	 */
	
	void claimrequestActions( Long requestSequenceNbr, String newStatusCd,
							 String modifiedBy, String remarks,Double approvedAmount ) throws HCISException;
}



