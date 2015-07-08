/**
 * 
 */
package in.wtc.hcis.bo.referral;

import in.wtc.hcis.bm.base.ReferralBM;
import in.wtc.hcis.bm.base.ReferralLightBM;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.exceptions.HCISException;

import java.util.Date;
import java.util.List;

/**
 * @author Alok Ranjan
 * 
 * 
 */
public interface ReferralManager {
	/**
	 * This method returns the configured referral types. Some of the examples of referral types are
	 * 1) Doctor
	 * 2) Clinic / Nursing homes
	 * 3) Old patients
	 * 4) PR Agencies
	 * 
	 * @return
	 * @throws HCISException
	 */
	ListRange getReferralTypes( int start, int count, String orderBy )throws HCISException;
	
	/**
	 * This method returns all the active referral processes in the systems. Some of the example of 
	 * referral processes are
	 * 1) Bed usage
	 * 2) Service usage
	 * 3) Flat commission on consultation
	 * 4) Flat commission on admissions
	 * 
	 * @return
	 * @throws HCISException
	 */
	ListRange getReferralCommissionProcesses(int start, int count, String orderBy )throws HCISException;
	
	/**
	 * This method persists the referral details into the database. In addition, this method does following
	 * 1) Configure the charges for various processes - for example bed usage, service usage, pharmacy
	 * 2) Create referring entities account in the accounting system
	 * 
	 * @param referralBM
	 * @return -- the method returns automatically generated referral code
	 * @throws HCISException
	 */
	Integer saveReferral( ReferralBM referralBM ) throws HCISException;
	
	/**
	 * This method marks a referral as inactive and it does not delete the physical record.
	 * The deleted referrals will not be part of the search results.
	 * 
	 * @param referralCode
	 * @throws HCISException
	 */
	void deleteReferral( Integer referralCode ) throws HCISException;
	
	/**
	 * 
	 * @param modifiedReferralBM
	 * @return
	 * @throws HCISException
	 */
	ReferralBM modifyReferral( ReferralBM modifiedReferralBM ) throws HCISException;
	
	/**
	 * This method returns the active referral available in the system
	 * 
	 * @return
	 * @throws HCISException
	 */
	
	List<ReferralLightBM> getActiveReferrals(int start, int count, String orderBy )throws HCISException; 
	
	/**
	 *  This method returns the active referral available in the system which matches the information
	 *  supplied in ReferralLightBM. All the attributes of this business model are optional for this method.
	 *  
	 * @param referralLightBM
	 * @return
	 * @throws HCISException
	 */
	List<ReferralLightBM> getActiveReferrals(ReferralLightBM referralLightBM,
			int start, int count, String orderBy) throws HCISException; 

	
	/**
	 *  This method returns a listrange object of active referrals available in the system which matches the information
	 *  supplied in ReferralLightBM. All the attributes of this business model are optional for this method.

	 * @param referralLightBM
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange getActiveReferralsListRange(ReferralLightBM referralLightBM,
			int start, int count, String orderBy) throws HCISException; 

	
	/**
	 * Given a referral code number, this method returns complete detail about the referral. 
	 * 
	 * @param referralCode
	 * @return
	 * @throws HCISException
	 * 
	 */
	ReferralBM	getReferralDetails( Integer referralCode ) throws HCISException;
	
	/**
	 * This method generates referral commission report for a given referral code and period.
	 * 
	 * @param referralCode    -- (mandatory) the unique referral code of the referring organization / person
	 * @param commissionFromDtm -- (mandatory) The date from which commission needs to be reported
	 * @param commissionToDtm -- (mandatory) The date from which commission needs to which reported
	 * @param personId -- (mandatory) the user id of the person, who is running report
	 * @return		-- return the exact path of generated report file 
	 * @throws HCISException
	 */
	String runCommissionReport( Integer referralCode, 
	                          	Date commissionFromDtm, 
	                          	Date commissionToDtm,
	                          	String personId ) throws HCISException;
	
	/**
	 * This method generates commissions for the commission processes, for which individual billing processes have
	 * not already generated commission. Also, this method is responsible for creating payable in the accounting system.
	 * 
	 * @param referralCode -- (mandatory) the unique referral code of the referring organization / person
	 * @param personId -- (mandatory) the user id of the person, who is running report
	 * @throws HCISException
	 */
	void processCommission( Integer referralCode, String personId ) throws HCISException;
	
	/**
	 * This method return listRange of Active referral of given type.
	 * Returns null if no active referral found.
	 * @param referralType
	 * @param start
	 * @param count
	 * @param orderBy
	 * @return
	 * @throws HCISException
	 */
	ListRange getReferralNameOfType(String referralType,
											int start, int count, String orderBy ) throws HCISException ;

	/**
	 * @return ListRange of commission type indicators
	 * @throws HCISException
	 * TODO:Move to datamodelManager
	 */
	ListRange getCommissionTypeInd( int start, int count, String orderBy ) throws HCISException ;
}
