package in.wtc.hcis.bo.referral;

import in.wtc.hcis.bm.base.AccountInfoBM;
import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.ReferralBM;
import in.wtc.hcis.bm.base.ReferralCommissionBM;
import in.wtc.hcis.bm.base.ReferralLightBM;
import in.wtc.hcis.bo.common.ApplicationErrors;
import in.wtc.hcis.bo.common.DataModelManager;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ApplicationConstants;
import in.wtc.hcis.bo.constants.ReferralConstants;
import in.wtc.hcis.bo.constants.RegistrationConstants;
import in.wtc.hcis.bo.constants.ReportsDetail;
import in.wtc.hcis.bo.integration.EagleIntegration;
import in.wtc.hcis.bo.integration.EagleIntegrationImpl;
import in.wtc.hcis.exceptions.Fault;
import in.wtc.hcis.exceptions.HCISException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.Validate;

import com.wtc.hcis.da.Appointments;
import com.wtc.hcis.da.AssignedServices;
import com.wtc.hcis.da.Country;
import com.wtc.hcis.da.Patient;
import com.wtc.hcis.da.ReferenceDataList;
import com.wtc.hcis.da.ReferenceDataListDAO;
import com.wtc.hcis.da.ReferenceDataListId;
import com.wtc.hcis.da.Referral;
import com.wtc.hcis.da.ReferralCommission;
import com.wtc.hcis.da.ReferralCommissionProcess;
import com.wtc.hcis.da.ReferralCommissionProcessDAO;
import com.wtc.hcis.da.ReferralPayable;
import com.wtc.hcis.da.ReferralPayableId;
import com.wtc.hcis.da.RenderedService;
import com.wtc.hcis.da.State;
import com.wtc.hcis.da.StateId;
import com.wtc.hcis.da.extn.AppointmentsDAOExtn;
import com.wtc.hcis.da.extn.ReferralCommissionDAOExtn;
import com.wtc.hcis.da.extn.ReferralDAOExtn;
import com.wtc.hcis.da.extn.ReferralPayableDAOExtn;
import com.wtc.hcis.da.extn.RenderedServiceDAOExtn;
import com.wtc.report.ReportManager;
import com.wtc.report.model.ReportDetail;

/**
 * @author Alok Ranjan
 *
 */
public class ReferralManagerImpl implements ReferralManager {

	private ReferralDAOExtn referralDAO;
	private ReferralCommissionDAOExtn referralCommissionDAO;
	private ReferralCommissionProcessDAO referralCommissionProcessDAO;
	private ReferralPayableDAOExtn referralPayableDAO;
	private AppointmentsDAOExtn appointmentsDAO;
	private ReferenceDataListDAO referenceDataListDAO;
	private DataModelManager dataModelManager;
	private RenderedServiceDAOExtn renderedServiceDAO;
	
	private EagleIntegration eagleIntegration = new EagleIntegrationImpl();

	private ReportManager reportManager;
	
	private ResourceBundle reportsConfig = ResourceBundle.getBundle("in.wtc.hcis.bo.properties.ReportDetail", Locale.getDefault());
	
	/* This method first tries to delete the Referral and related details from
	 * database.If it is not being referred by any other transaction/entity
	 * (currently it check reference with Appointments,Patients and ReferralPayables )
	 * then it is deleted otherwise just mark it as INACTIVE.	
	 */
	public void deleteReferral(Integer referralCode) throws HCISException {
		try{

			Referral referral = this.getReferral(referralCode);
			
			boolean isReferralEligibleForDeletion = true;
			
			if(referral.getAppointmentses() != null && referral.getAppointmentses().size() > 0 ){
				
				isReferralEligibleForDeletion = false;
			}else if( referral.getReferralPayables() != null && referral.getReferralPayables().size() > 0 ){
				
				isReferralEligibleForDeletion = false;
			}else if( referral.getPatients() != null && referral.getPatients().size() > 0 ){
				
				isReferralEligibleForDeletion = false;
			}
			
			if(isReferralEligibleForDeletion){
				
				//Try to delete account 
				/*eagleIntegration.deleteAccount(referral.getReferralCode().toString(),
						  						 ReferralConstants.REFERENCE_TYPE_REFERRAL);*/
				
				//Get all related commissions
				List<ReferralCommission> referralCommissionList = referralCommissionDAO.getReferralCommissionOfReferral(referralCode );
				
				if( referralCommissionList != null && !referralCommissionList.isEmpty() ){
					for( ReferralCommission commission : referralCommissionList ){
						
						referralCommissionDAO.delete(commission);
					}
				}
			
			referralDAO.delete( referral );
			}else{
				referral.setActive( ApplicationConstants.ACTIVE_FLAG_NO );
				referral.setLastModifiedDtm( new Date() );
				referralDAO.attachDirty(referral);
			}
		}catch (Exception e) {
			
			Fault fault = new Fault( ApplicationErrors.REFERRAL_DELETE_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
									 fault.getFaultCode(),fault.getFaultType());
		}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#getActiveReferrals()
	 */
	public List<ReferralLightBM> getActiveReferrals(int start, int count, String orderBy ) throws HCISException {
		
		try {
			List<Referral>referralList = referralDAO.getActiveReferral();
			List<ReferralLightBM>referralLightBMList = null;
			
			if ( referralList != null && !referralList.isEmpty() ) {
				referralLightBMList = new ArrayList<ReferralLightBM>(0);
				
				for ( Referral referral : referralList ) {
					ReferralLightBM referralLightBM = this.convertReferralDM2LightBM(referral);
					referralLightBMList.add(referralLightBM);
				}
			}
			
			return referralLightBMList;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType() );
		
		}
	}
	
	private ReferralLightBM convertReferralDM2LightBM( Referral referral ) {
		ReferralLightBM referralLightBM = new ReferralLightBM();
		
		referralLightBM.setCity(referral.getCity());
		
		Country country = referral.getCountry();
		if( null != country ){
			referralLightBM.setCountry(new CodeAndDescription( 
							country.getCountryCode(),country.getDescription()));
			
			
			if( null != referral.getStateCode() ){
				
				StateId stateId = new StateId();
				stateId.setCountryCode(country.getCountryCode());
				stateId.setStateCode(referral.getStateCode());
				State state = dataModelManager.getStateByCode(stateId);
				
				referralLightBM.setState(new CodeAndDescription( 
										state.getId().getStateCode(),state.getDescription()));
			}

		}
		
		referralLightBM.setPincode(referral.getPinCode());
		referralLightBM.setPreferredContactTime(referral.getPreferredContactTime());
		referralLightBM.setReferralAddress(referral.getAddress());
		referralLightBM.setReferralCode(referral.getReferralCode());
		referralLightBM.setReferralName(referral.getReferralName());
		
		ReferenceDataList referralType = this.getReferralType( referral.getReferralTypeCode());
		if( null != referralType ){
			referralLightBM.setReferralType( new CodeAndDescription(
					referralType.getId().getAttrCode(),referralType.getAttrDesc()));
		}

		return referralLightBM;
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#getActiveReferrals(in.wtc.hcis.bm.base.ReferralLightBM)
	 */
	public List<ReferralLightBM> getActiveReferrals( ReferralLightBM referralLightBM,
													 int start, int count,
													 String orderBy) throws HCISException {
		try {
			List<Referral>referralList = null;
			if(null == referralLightBM){
				referralList = referralDAO.getActiveReferral();
			} else {
				
				String referralTypeCode = null;
				String countryCode = null;
				String stateCode = null;
				
				if( referralLightBM.getReferralType() != null &&
						referralLightBM.getReferralType().getCode() != null ){
					referralTypeCode = referralLightBM.getReferralType().getCode();
				}
				
				if( referralLightBM.getCountry() != null &&
						referralLightBM.getCountry().getCode() != null ){
					countryCode = referralLightBM.getCountry().getCode();
				}
				
				if( referralLightBM.getState() != null &&
						referralLightBM.getState().getCode() != null ){
					stateCode = referralLightBM.getState().getCode();
				}
				
				referralList = referralDAO.getActiveReferral( referralLightBM.getCity(),
						countryCode, 
						referralLightBM.getPincode(),
						referralLightBM.getPreferredContactTime(),
						referralLightBM.getReferralAddress(),
						referralLightBM.getReferralCode(),
						referralLightBM.getReferralName(),
						referralTypeCode,
						stateCode );
			}
			
			
			List<ReferralLightBM>referralLightBMList = null;
			
			if ( referralList != null && !referralList.isEmpty() ) {
				referralLightBMList = new ArrayList<ReferralLightBM>(0);
				
				for ( Referral referral : referralList ) {
					ReferralLightBM referralLightBMTemp = this.convertReferralDM2LightBM(referral);
					referralLightBMList.add(referralLightBMTemp);
				}
			}
			
			return referralLightBMList;
		} catch ( Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}

	
	public ListRange getActiveReferralsListRange( ReferralLightBM referralLightBM, 
												  int start, int count,String orderBy) throws HCISException {
		
		ListRange listRange = new ListRange();
		
		List<ReferralLightBM> tmpList = getActiveReferrals(referralLightBM, start, count, orderBy);
			
		List<ReferralLightBM> pageSizeData = new ArrayList<ReferralLightBM>();
		int index = 0;
		if (tmpList != null && tmpList.size() > 0) {
			while ((start + index < start + count)	&& (tmpList.size() > start + index)) {

				ReferralLightBM referralLight = tmpList.get(start + index);
				pageSizeData.add(referralLight);
				index++;
			}
			listRange.setData(pageSizeData.toArray());
			listRange.setTotalSize(tmpList.size());
		}else{
			listRange.setData(new Object[0]);
			listRange.setTotalSize(0);
		}
		
		return listRange;
		
	}
	
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#getReferralDetails(java.lang.Long)
	 */
	public ReferralBM getReferralDetails( Integer referralCode )
			throws HCISException {
			 try {
				Referral referral = this.getReferral(referralCode);
				 
				return this.convertReferralDM2BM(referral);
			} catch (RuntimeException e) {
				Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
				
				throw new HCISException( fault + e.getMessage(),
						 fault.getFaultCode(),fault.getFaultType()  );
			}
	}

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#getReferralTypes()
	 */
	//TODO:This method should move to datamodelManager as codeAndDescription returning method. 
	public ListRange getReferralTypes( int start, int count, String orderBy ) throws HCISException {
		try {
			ListRange listRange = new ListRange();
			
			List<ReferenceDataList> referenceDataList = this.getReferenceDataList(ReferralConstants.REFFERAL_TYPE_CONTEXT);
			
			if ( referenceDataList != null && !referenceDataList.isEmpty() ) {
				
				 Object[] codeDescObj = new Object[referenceDataList.size()];
				 
				for ( int index = 0 ; index < referenceDataList.size();index++ ) {
					
					ReferenceDataList dataList = referenceDataList.get(index);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(dataList.getId().getAttrCode());
					codeAndDescription.setDescription( dataList.getAttrDesc() );
					codeDescObj[index] = codeAndDescription;
				}
				
				listRange.setData( codeDescObj );
				listRange.setTotalSize( referenceDataList.size() );
			}else{
				listRange.setData( new Object[0] );
				listRange.setTotalSize( 0 );
			}
			return listRange;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_TYPE_READ_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}
	//TODO:This method should move to datamodelManager as codeAndDescription returning method. 
	public ListRange getCommissionTypeInd( int start, int count, String orderBy ) throws HCISException {
		try {
			ListRange listRange = new ListRange();
			
			List<ReferenceDataList> referenceDataList = this.getReferenceDataList(ReferralConstants.PCT_ABS_IND_CONTEXT);
			
			if ( referenceDataList != null && !referenceDataList.isEmpty() ) {
				
				 Object[] codeDescObj = new Object[referenceDataList.size()];
				 
				for ( int index = 0 ; index < referenceDataList.size();index++ ) {
					
					ReferenceDataList dataList = referenceDataList.get(index);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(dataList.getId().getAttrCode());
					codeAndDescription.setDescription( dataList.getAttrDesc() );
					codeDescObj[index] = codeAndDescription;
				}
				
				listRange.setData( codeDescObj );
				listRange.setTotalSize( referenceDataList.size() );
			}else{
				listRange.setData( new Object[0] );
				listRange.setTotalSize( 0 );
			}
			return listRange;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_TYPE_READ_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}
	
	 /*
	  * 
	  */
	public ListRange getReferralNameOfType(String referralType,
			int start, int count, String orderBy ) throws HCISException {
		try {
			ListRange listRange = new ListRange();
			
			List<Referral> referralList = referralDAO.getActiveReferralOfType(referralType);
			
			
			if ( referralList != null && !referralList.isEmpty() ) {
				
				 Object[] codeDescObj = new Object[referralList.size()];
				 
				for ( int index = 0 ; index < referralList.size() ; index++) {
					Referral referral = referralList.get(index);
					CodeAndDescription codeAndDescription = new CodeAndDescription();
					codeAndDescription.setCode(referral.getReferralCode().toString());
					codeAndDescription.setDescription( referral.getReferralName() );
					codeDescObj[index] = codeAndDescription;
				}
				
				listRange.setData( codeDescObj );
				listRange.setTotalSize( referralList.size() );
			}else{
				listRange.setData( new Object[0] );
				listRange.setTotalSize( 0 );
			}
			return listRange;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}
	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#runCommissionReport(java.lang.Integer, java.util.Date, java.util.Date, java.lang.Boolean)
	 */
	public String runCommissionReport( Integer referralCode, 
	                                   Date commissionFromDtm, 
	                                   Date commissionToDtm, 
	                                   String personId )
			throws HCISException {
		
		Referral referral = this.getReferral(referralCode);
		
		try {
			ReportDetail reportDetail = new ReportDetail();
			reportDetail.setName(reportsConfig.getString( ReportsDetail.REFERRAL_COMMISSION_REPORT_NAME ) );
			reportDetail.setFileName(reportsConfig.getString( ReportsDetail.REFERRAL_COMMISSION_REPORT_FILENAME ) );
			
			Map<String, Object> reportParamMap = new HashMap<String, Object>();
			reportParamMap.put("referralCode", referralCode.toString() );
			reportParamMap.put("commissionFromDtm", commissionFromDtm.toString() );
			reportParamMap.put("commissionToDtm", commissionToDtm.toString() );
			
			String reportFileName = reportManager.generateReport( reportDetail, reportParamMap, null);
			
			return reportFileName;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_RUN_REPORT_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	/**
	 * This method runs the commission process and creates payable amount entry.
	 * 
	 * Currently method supports process for appointments and assigned service
	 * through referral.
	 */
	
	public void processCommission( Integer referralCode, String personId ) throws HCISException {
		
		try {
			Referral referral = this.getReferral(referralCode);
			
			Date lastProcessedDate = referralPayableDAO.getLastProcessDate( referralCode );
			
			if( lastProcessedDate == null ){
				
				Calendar leastDate = Calendar.getInstance();
				leastDate.setTimeInMillis(0);
				
				lastProcessedDate = leastDate.getTime();
			}
			
			this.processReferralCommission( referral, personId, lastProcessedDate );
			
			Double payableAmount = this.accountCommissionRefferalPayable( referral );

			if( payableAmount > 0.0){
				Integer businessPartnerId = eagleIntegration.getBusinessPartnerId(referralCode, RegistrationConstants.REFERENCE_TYPE_REFERRAL);
				Integer recordID = eagleIntegration.createPayment(businessPartnerId, payableAmount, "", null);
			}
			
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_PROCESS_COMMISSION_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}
	
	/**
	 * This method processes unbillable items like appointments. It is expected that billable entities 
	 * would themselves know how to give commission for their usages.
	 * 
	 * @param referral
	 * @param personId
	 * @param lastProcessedDate
	 */
	private void processReferralCommission( Referral 	referral, 
			                                String 		personId, 
			                                Date 		lastProcessedDate  ) {
		
		//First process the appointments
		
		

			List<Appointments>appointmentsList = appointmentsDAO.getAppointmentsForReferral( referral.getReferralCode(),
																							 lastProcessedDate );
			if ( appointmentsList != null && !appointmentsList.isEmpty() ) {
				for ( Appointments appointments : appointmentsList ) {
						
					Double payableAmount = this.processForAppointments( referral , appointments );
					
					if ( payableAmount > 0 ) {
						this.createReferralPayble( referral,
												   appointments.getPatient(),
												   "OPD",//TODO:
												   appointments.getAppointmentNumber(),
								                   personId, 
								                   ReferralConstants.REFERRAL_COMMISSION_TYPE_APPOINTMENTS, 
								                   payableAmount,
								                   appointments.getCapturedDtm());
					}
				}
			}

		//Now process the assigned services for the patients came through referral
		
		//
		//referral-Commission For Service is defined for the referral 
				
			List<RenderedService> renderedServiceList = renderedServiceDAO.getRenderedServicesOfReferral(referral.getReferralCode(),
																										 lastProcessedDate);
			if( renderedServiceList != null && !renderedServiceList.isEmpty()){
				//This patient has something for which referral can get commission
				//
				for( RenderedService renderedService : renderedServiceList){
					
					ReferralCommission referralCommissionForService = referralCommissionDAO.getApplicableReferralCommission(
							referral.getReferralCode(),
							ReferralConstants.REFERRAL_COMMISSION_TYPE_SERVICES,
							renderedService.getId().getRenderedDtm());
					
					Double payableAmount = this.processForService( referralCommissionForService , renderedService );

					if ( payableAmount > 0 ) {
						
						AssignedServices assignedServices = renderedService.getAssignedServices();
						
						this.createReferralPayble(referral, assignedServices.getPatient(),
								assignedServices.getReferenceType(), Integer.parseInt(assignedServices.getReferenceNumber()),
								personId, ReferralConstants.REFERRAL_COMMISSION_TYPE_SERVICES, payableAmount,assignedServices.getCreateDtm());
						
					}
			}
		}
	}
	
	private Double processForService(ReferralCommission referralCommission , RenderedService renderedService){
		Double payableAmount = 0.0;
		
		if ( referralCommission.getPctAbsInd().equals( ReferralConstants.REFERRAL_COMMISSION_IND_ABSOLUTE )  ) {
			payableAmount = referralCommission.getPctAbsValue();
		} else {
			payableAmount = referralCommission.getPctAbsValue() * renderedService.getServiceCharge()/ 100.00;
		}
		
		return payableAmount;
	}
	
	
	private Double processForAppointments( Referral referral , Appointments appointments ){
	
		
		ReferralCommission referralCommission = referralCommissionDAO.getApplicableReferralCommission(referral.getReferralCode(),
															  		  ReferralConstants.REFERRAL_COMMISSION_TYPE_APPOINTMENTS,
															  		  appointments.getCapturedDtm());
		Double payableAmount = 0.0;		
		if( referralCommission != null ){
			
			if ( referralCommission.getPctAbsInd().equals( ReferralConstants.REFERRAL_COMMISSION_IND_ABSOLUTE )  ) {
				payableAmount = referralCommission.getPctAbsValue();
			} else {
				payableAmount = referralCommission.getPctAbsValue() * appointments.getConsultationCharge() / 100.00;
			}
		
		}
			
		return payableAmount;
	}
	
	private Double accountCommissionRefferalPayable( Referral referral ) {
		
		Double payableAmount = 0.0; 
		
		List<ReferralPayable>referralPayableList = referralPayableDAO.getUnAccountedReferralPayable( referral.getReferralCode(), ReferralConstants.REFERRAL_PAYABLE_ACCONUTED_NO );
		
		if ( referralPayableList != null && !referralPayableList.isEmpty() ) {
			
			for ( ReferralPayable referralPayable : referralPayableList ) {
				payableAmount += referralPayable.getPayableAmt();
				referralPayable.setAccounted( ReferralConstants.REFERRAL_PAYABLE_ACCONUTED_YES );
				referralPayable.setProcessedDtm( new Date() );
				referralPayableDAO.attachDirty( referralPayable );
			}
		}
		return payableAmount;
	}
	
	private void createReferralPayble( Referral referral,
									   Patient patient,
									   String processReferenceText,
									   Integer referenceNbr,
									   String personId, 
									   String commissionProcessTypeCd,
									   Double commissionAmount,
									   Date eventDate) throws HCISException {
		ReferralPayable referralPayable = new ReferralPayable();
		referralPayable.setCreatedBy(personId);
		ReferralPayableId referralPayableId = new ReferralPayableId();
		referralPayableId.setCommissionProcessTypeCd( commissionProcessTypeCd );
		referralPayableId.setReferralCode(referral.getReferralCode());
		referralPayableId.setPayableCreateDtm( new Date() );
		referralPayableId.setProcessReferenceText(processReferenceText);
		referralPayableId.setReferenceNbr(referenceNbr);
		referralPayable.setId( referralPayableId );
		referralPayable.setPatient(patient);
		referralPayable.setPayableAmt(commissionAmount);
		referralPayable.setEventDtm(eventDate);
		
		//Default  ACCONUTED indicator 
		referralPayable.setAccounted( ReferralConstants.REFERRAL_PAYABLE_ACCONUTED_NO);
		referralPayable.setReferral(referral);
		
		referralPayableDAO.save(referralPayable);
	}
	

	/* (non-Javadoc)
	 * @see in.wtc.hcis.bo.referral.ReferralManager#saveReferral(in.wtc.hcis.bm.base.ReferralBM)
	 */
	public Integer saveReferral(ReferralBM referralBM) throws HCISException {

		try {
			Validate.notNull(referralBM," ReferralBM must not be null ");
			Referral referral = this.convertReferralBM2DM(referralBM, null );
			referral.setCreateDtm(Calendar.getInstance().getTime());
			referral.setCreatedBy(referralBM.getCreatedBy());

			referralDAO.save(referral);

			List<ReferralCommissionBM>referralCommissionBMList = referralBM.getReferralCommissionList();

			if ( referralCommissionBMList != null && !referralCommissionBMList.isEmpty() ) {
				for ( ReferralCommissionBM referralCommissionBM : referralCommissionBMList ) {
					referralCommissionBM.setReferralCode(referral.getReferralCode());
					this.saveReferralCommission(referralCommissionBM, referralBM.getCreatedBy());
				}
			}

			//Create account for patient in EAGLE ERP 
			try {
				
				AccountInfoBM accountInfoBM = this.convertReferralToAccountInfoBM(referral);

				eagleIntegration.createAccount(accountInfoBM, true);

			} catch (Exception e) {
				Fault fault = new Fault(ApplicationErrors.CREATE_ACCOUNT_FAILED);
				throw new HCISException(fault, e);
			}
			//Create account for patient in EAGLE ERP - END 

			return referral.getReferralCode();
		} catch ( Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_SAVE_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}

	private void saveReferralCommission( ReferralCommissionBM referralCommissionBM, String createdBy ) throws Exception{

		// Set default values
		if(referralCommissionBM.getCommissionTypeInd() == null || referralCommissionBM.getCommissionTypeInd().equals("")){
			
			ReferenceDataList referenceData = this.getReferenceData(ReferralConstants.PCT_ABS_IND_CONTEXT,
																ReferralConstants.REFERRAL_COMMISSION_IND_ABSOLUTE);
			if( referenceData != null )	{
			
				referralCommissionBM.setCommissionTypeInd(new CodeAndDescription(referenceData.getId().getAttrCode(),
																				 referenceData.getAttrDesc()));
			}
			
		}else{
			
			if( referralCommissionBM.getCommissionTypeInd().equals(ReferralConstants.REFERRAL_COMMISSION_IND_PERCENTAGE)
					&& referralCommissionBM.getCommissionValue() > 100.0){
				
				throw new Exception(" Percentage value of 'Referral Commission' can not be grater than 100");
			}
		}
		
		if(null == referralCommissionBM.getCommissionValue()){
			referralCommissionBM.setCommissionValue(new Double(0));
		}

		ReferralCommission referralCommission = this.convertReferralCommissionBM2DM(referralCommissionBM, null );
		referralCommission.setCreatedBy( createdBy );
		referralCommission.setCreatedDtm(Calendar.getInstance().getTime());
		referralCommissionDAO.save(referralCommission);
	}

	private AccountInfoBM convertReferralToAccountInfoBM(Referral referral){
	
		AccountInfoBM accountInfoBM = new AccountInfoBM();
		
		ContactDetailsBM contactDetailsBM = new ContactDetailsBM();
		
		contactDetailsBM.setPersonelId( referral.getReferralCode());
		
		Country country = referral.getCountry(); 
		if( country != null ){
			
			contactDetailsBM.setCountry(new CodeAndDescription(country.getCountryCode(),
															   country.getDescription()));
		}
		
		contactDetailsBM.setState( new CodeAndDescription(referral.getStateCode(),""));
		contactDetailsBM.setCreatedBy(referral.getCreatedBy());
		contactDetailsBM.setCity( referral.getCity() );
		contactDetailsBM.setEmail( referral.getEmail() );
		contactDetailsBM.setFaxNumber( referral.getFax() );
		contactDetailsBM.setFirstName( referral.getReferralName());
		contactDetailsBM.setForEntity( new CodeAndDescription(
									RegistrationConstants.REFERENCE_TYPE_REFERRAL,""));
		contactDetailsBM.setHouseNumber(referral.getAddress());
		contactDetailsBM.setMobileNumber( referral.getMobile() );
		contactDetailsBM.setPhoneNumber( referral.getPhone());
		contactDetailsBM.setPincode( referral.getPinCode() );
		contactDetailsBM.setStreet( referral.getAddress() );
		
		accountInfoBM.setContactDetailsBM(contactDetailsBM);
		accountInfoBM.setEntityCategory( new CodeAndDescription("1000004",""));//TODO:Change it to real one,its just for testing
		return  accountInfoBM;
	}
/**
 * This method modifies existing Referral in the system. 
 * If referral commission 
 * @param modifiedReferralBM
 * @return
 */
	public ReferralBM modifyReferral( ReferralBM modifiedReferralBM ){
		
		try {
			Validate.notNull(modifiedReferralBM, "ReferralBM must not be null");
			
			Referral existingReferral = this.getReferral( modifiedReferralBM.getReferralCode() );
			
			Referral referral = this.convertReferralBM2DM(modifiedReferralBM, existingReferral );
			referral.setLastModifiedDtm( Calendar.getInstance().getTime());
			referralDAO.attachDirty(referral);
			
			List<ReferralCommissionBM>referralCommissionBMList = modifiedReferralBM.getReferralCommissionList();

			if ( referralCommissionBMList != null && !referralCommissionBMList.isEmpty() ) {
				for ( ReferralCommissionBM referralCommissionBM : referralCommissionBMList ) {
					referralCommissionBM.setReferralCode(referral.getReferralCode());
					this.saveReferralCommission(referralCommissionBM, modifiedReferralBM.getCreatedBy());
				}
			}
			
			//Modify contact details on accounting system
			

			AccountInfoBM accountInfoBM = this.convertReferralToAccountInfoBM(referral);

			eagleIntegration.modifyAccountDetails(accountInfoBM);


			return this.convertReferralDM2BM(referral);
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_MODIFY_FAILED );
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}
	
	public ListRange getReferralCommissionProcesses(int start, int count, String orderBy )throws HCISException{
		
		try {
			ListRange referralCommissionProcessList = new ListRange();
			
			List<ReferralCommissionProcess> referralCommissionProcesses = referralCommissionProcessDAO.findByActive(ApplicationConstants.ACTIVE_FLAG_YES);
			if(null == referralCommissionProcesses){
				referralCommissionProcesses = new ArrayList<ReferralCommissionProcess>();
			}
			
			Object[] codeDescObj = new Object[referralCommissionProcesses.size()];
			int referralCommissionCount = 0;
			for ( ReferralCommissionProcess referralCommissionProcess : referralCommissionProcesses ) {
				codeDescObj[referralCommissionCount] = new CodeAndDescription( referralCommissionProcess.getCommissionTypeCode(), referralCommissionProcess.getCommissionTypeDesc() );
				referralCommissionCount++;
			}
			
			referralCommissionProcessList.setData(codeDescObj);
			referralCommissionProcessList.setTotalSize(referralCommissionProcesses.size());
			
			return referralCommissionProcessList;
		} catch (RuntimeException e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_COMMISSION_PROCESS_READ_FAILED);
			
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType()  );
		}
	}

	
	/**
	 * This method converts referral business model into referral data model
	 * 
	 * @param referralBM
	 * @param existingReferral
	 * @return
	 */
	private Referral convertReferralBM2DM( ReferralBM referralBM, Referral existingReferral ) {
		
		Referral referral = null;
		
		if ( existingReferral != null ) {
			referral = existingReferral;
		} else {
			referral = new Referral();
			referral.setActive( ApplicationConstants.ACTIVE_FLAG_YES );
		}
		
		referral.setAddress( referralBM.getReferralAddress() );
		referral.setCity( referralBM.getCity() );
		
		
		if( referralBM.getReferralType() != null &&
				referralBM.getReferralType().getCode() != null &&
				referralBM.getReferralType().getCode().length() > 0 ){
			
			//To make sure that ReferralTypeCode already exist in system
			this.getReferralType(referralBM.getReferralType().getCode());
			
			referral.setReferralTypeCode( referralBM.getReferralType().getCode() );			
			
		}
		
		if( referralBM.getCountry() != null &&
				 referralBM.getCountry().getCode() != null &&
				referralBM.getCountry().getCode().length() > 0){
			
			Country country = dataModelManager.getCountrybyCode(referralBM.getCountry().getCode());
			referral.setCountry( country );
			
			if( referralBM.getState() != null &&
					referralBM.getState().getCode() != null && 
				   referralBM.getState().getCode().length() > 0){
				
				StateId stateId = new StateId();
				stateId.setCountryCode(country.getCountryCode());
				stateId.setStateCode(referralBM.getState().getCode());
				State state = dataModelManager.getStateByCode(stateId);
				referral.setStateCode( state.getId().getStateCode() );

			}
		}
		
		referral.setEmail( referralBM.getEmail() );
		referral.setFax( referralBM.getFaxNumber() );
		referral.setMobile( referralBM.getMobileNumber() );
		referral.setPhone( referralBM.getPhoneNumber() );
		referral.setPinCode( referralBM.getPincode() );
		referral.setPreferredContactTime( referralBM.getPreferredContactTime() );
		referral.setQualification( referralBM.getQualification() );
		referral.setReferralName( referralBM.getReferralName() );

		
		return referral;
	}
	
	private ReferralBM convertReferralDM2BM( Referral referral ) {
		ReferralBM referralBM = new ReferralBM();
		
		referralBM.setCity( referral.getCity() );
		
		Country country = referral.getCountry();
		if( null != country ){
			referralBM.setCountry(new CodeAndDescription(country.getCountryCode(),country.getDescription()));
			
			if( null != referral.getStateCode() ){
				
				StateId stateId = new StateId();
				stateId.setCountryCode(country.getCountryCode());
				stateId.setStateCode(referral.getStateCode());
				State state = dataModelManager.getStateByCode(stateId);
				
				referralBM.setState(new CodeAndDescription( 
										state.getId().getStateCode(),state.getDescription()));
			}
		}
		
		referralBM.setCreatedBy( referral.getCreatedBy() );
		referralBM.setCreateDtm( referral.getCreateDtm() );
		referralBM.setEmail( referral.getEmail() );
		referralBM.setFaxNumber( referral.getFax() );
		referralBM.setMobileNumber(referral.getMobile());
		referralBM.setPhoneNumber(referral.getPhone());
		referralBM.setPincode(referral.getPinCode());
		referralBM.setPreferredContactTime(referral.getPreferredContactTime());
		referralBM.setQualification(referral.getQualification());
		referralBM.setReferralAddress(referral.getAddress());
		referralBM.setReferralCode(referral.getReferralCode());
		referralBM.setReferralName(referral.getReferralName());
		
		ReferenceDataList referralType = this.getReferralType( referral.getReferralTypeCode());
		if( null != referralType ){
			referralBM.setReferralType( new CodeAndDescription(
					referralType.getId().getAttrCode(),referralType.getAttrDesc()));
		}
		
		List<ReferralCommission>referralCommissionList = this.getAllAplicableReferralCommissions(referral.getReferralCode());
		if ( referralCommissionList != null && !referralCommissionList.isEmpty()) {
			
			List<ReferralCommissionBM>referralCommissionBMList = new ArrayList<ReferralCommissionBM>(0);
			
				for ( ReferralCommission referralCommission : referralCommissionList ) {
					ReferralCommissionBM referralCommissionBM = this.convertReferralCommissionDM2BM(referralCommission);
					referralCommissionBMList.add( referralCommissionBM );
				}
			
			referralBM.setReferralCommissionList( referralCommissionBMList );
		}
		
		return referralBM;
	}
	
	private List<ReferralCommission> getAllAplicableReferralCommissions( Integer referralCode ){
		
		List<ReferralCommission>referralCommissionList = new ArrayList<ReferralCommission>(0);
		List<ReferralCommissionProcess> referralCommissionProcessList = referralCommissionProcessDAO.findByActive("Y");
		
		if( referralCommissionProcessList != null && !referralCommissionProcessList.isEmpty() ){
			for( ReferralCommissionProcess commissionProcess : referralCommissionProcessList ){
				
				
				ReferralCommission referralCommission = referralCommissionDAO.getApplicableReferralCommission(referralCode,
																			  commissionProcess.getCommissionTypeCode(),
																			  new Date());
				if( referralCommission != null ){
					referralCommissionList.add( referralCommission );
				}
			}
		}
		
		return referralCommissionList;
	}
	
	private ReferralCommission convertReferralCommissionBM2DM( ReferralCommissionBM referralCommissionBM, 
			                                                   ReferralCommission existingReferralCommission ) {
		ReferralCommission referralCommission = null;
		
		if ( existingReferralCommission != null ) {
			referralCommission = existingReferralCommission;
		}else{
			referralCommission = new ReferralCommission();
		}
		
		if( referralCommissionBM.getCommissionTypeInd()!= null ){
			referralCommission.setPctAbsInd(referralCommissionBM.getCommissionTypeInd().getCode());
		}
		
		referralCommission.setPctAbsValue(referralCommissionBM.getCommissionValue());
		
		Referral referral = this.getReferral( referralCommissionBM.getReferralCode() );
		referralCommission.setReferral(referral);
		
		if( referralCommissionBM.getEntityType() != null && 
				referralCommissionBM.getEntityType().getCode() != null && 
			   referralCommissionBM.getEntityType().getCode().length() > 0){
			
			ReferralCommissionProcess referralCommissionProcess = this.getReferralCommissionProcess( 
																	   referralCommissionBM.getEntityType().getCode() );
			referralCommission.setReferralCommissionProcess( referralCommissionProcess );
		}
		
		return referralCommission;
	}
	
	private ReferralCommissionProcess getReferralCommissionProcess( String commissionTypeCode ) {
		try {
			ReferralCommissionProcess referralCommissionProcess = referralCommissionProcessDAO.findById( commissionTypeCode );
			
			if ( referralCommissionProcess == null ) {
				throw new Exception( "Referral Commission Types with COMMISSION_TYPE_CODE = " + commissionTypeCode + " does not exist. ");
			}
			
			return referralCommissionProcess;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_COMMISSION_TYPE_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	private Referral getReferral( Integer referralCode ) {
		try {
			Referral referral = referralDAO.findById( referralCode );
			
			if ( referral == null ) {
				throw new Exception( " There is no referral configured for REFERRAL_CODE = " + referralCode );
			}
			
			return referral;
		} catch (Exception e) {
			Fault fault = new Fault( ApplicationErrors.REFERRAL_READ_FAILED );
			
			throw new HCISException( fault.getFaultMessage() + e.getMessage(),
									 fault.getFaultCode(),
									 fault.getFaultType() );
		}
	}
	
	private ReferralCommissionBM convertReferralCommissionDM2BM( ReferralCommission referralCommission ) {
		ReferralCommissionBM referralCommissionBM = new ReferralCommissionBM();
		referralCommissionBM.setReferralCode(referralCommission.getReferral().getReferralCode());
		referralCommissionBM.setEntityType( new CodeAndDescription(referralCommission.getReferralCommissionProcess().getCommissionTypeCode(),
											referralCommission.getReferralCommissionProcess().getCommissionTypeDesc() )  );
		if( referralCommission.getPctAbsInd() != null  ){
			ReferenceDataList dataList =  this.getReferenceData(ReferralConstants.PCT_ABS_IND_CONTEXT, referralCommission.getPctAbsInd());
			if( dataList != null ){
				referralCommissionBM.setCommissionTypeInd( new CodeAndDescription(dataList.getId().getAttrCode(),dataList.getAttrDesc()));
			}
			
		}
		referralCommissionBM.setCommissionValue( referralCommission.getPctAbsValue() );
		
		return referralCommissionBM;
	}

	private ReferenceDataList getReferralType(String referralTypeCode ){
		
		ReferenceDataList referenceDataList = null;
		try {
			if( null != referralTypeCode && referralTypeCode.length() > 0){
				
				ReferenceDataListId dataListId = new ReferenceDataListId();
				dataListId.setContext(ReferralConstants.REFFERAL_TYPE_CONTEXT);
				dataListId.setAttrCode( referralTypeCode );
				referenceDataList = referenceDataListDAO.findById(dataListId);
			}else{
				throw new Exception(" Referral type with code :"+ referralTypeCode + " dose not exist " );
			}
		} catch (Exception e) {
			Fault fault = new Fault(ApplicationErrors.REFERRAL_TYPE_READ_FAILED);
			throw new HCISException( fault + e.getMessage(),
					 fault.getFaultCode(),fault.getFaultType() );
			
		}
		
		return referenceDataList;
	}
	
	//TODO:This method should move to datamodelManager as codeAndDescription returning method. 
	private List<ReferenceDataList> getReferenceDataList(String context){
		List<ReferenceDataList> referenceDataList = referenceDataListDAO.findByProperty("id.context",context);
													
		return referenceDataList;
		
	}
	
	//TODO:This method will be very frequently used method and must move to some common place
	private ReferenceDataList getReferenceData(String context, String attrCode){
		
		ReferenceDataListId id = new ReferenceDataListId();
		id.setContext(context);
		id.setAttrCode(attrCode);
		
		ReferenceDataList referenceData = referenceDataListDAO.findById(id);
		return referenceData;
	}
	
	public void setReferralDAO(ReferralDAOExtn referralDAO) {
		this.referralDAO = referralDAO;
	}

	public void setReferralCommissionDAO(
			ReferralCommissionDAOExtn referralCommissionDAO) {
		this.referralCommissionDAO = referralCommissionDAO;
	}

	public void setReferralCommissionProcessDAO(
			ReferralCommissionProcessDAO referralCommissionProcessDAO) {
		this.referralCommissionProcessDAO = referralCommissionProcessDAO;
	}

	public void setReferralPayableDAO(ReferralPayableDAOExtn referralPayableDAO) {
		this.referralPayableDAO = referralPayableDAO;
	}

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}
	
	public void setReportsConfig(ResourceBundle reportsConfig) {
		this.reportsConfig = reportsConfig;
	}

	public void setAppointmentsDAO(AppointmentsDAOExtn appointmentsDAO) {
		this.appointmentsDAO = appointmentsDAO;
	}

	public void setReferenceDataListDAO(ReferenceDataListDAO referenceDataListDAO) {
		this.referenceDataListDAO = referenceDataListDAO;
	}

	public void setDataModelManager(DataModelManager dataModelManager) {
		this.dataModelManager = dataModelManager;
	}

	public void setRenderedServiceDAO(RenderedServiceDAOExtn renderedServiceDAO) {
		this.renderedServiceDAO = renderedServiceDAO;
	}

}
