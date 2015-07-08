package in.wtc.ui.controller;

import in.wtc.hcis.bm.base.CodeAndDescription;
import in.wtc.hcis.bm.base.ContactBM;
import in.wtc.hcis.bm.base.ContactDetailsBM;
import in.wtc.hcis.bm.base.OtherDetailsBM;
import in.wtc.hcis.bm.base.PatientAllergiesBM;
import in.wtc.hcis.bm.base.PatientImmunizationsBM;
import in.wtc.hcis.bm.base.PatientInfoDetailBM;
import in.wtc.hcis.bm.base.PatientInfoSummaryBM;
import in.wtc.hcis.bm.base.PatientLiteBM;
import in.wtc.hcis.bm.base.PersonalDetailsAdditionalBM;
import in.wtc.hcis.bm.base.PersonalDetailsBM;
import in.wtc.hcis.bo.common.DateUtils;
import in.wtc.hcis.bo.common.ListRange;
import in.wtc.hcis.bo.constants.ContactType;
import in.wtc.hcis.bo.patient.PatientManager;
import in.wtc.hcis.bo.registration.RegistrationManager;
import in.wtc.ui.model.PatientSummary;
import in.wtc.ui.utils.AgeCalculation;
import in.wtc.ui.utils.Converters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientManagementController {

	public  RegistrationManager	registrationManager;
	private PatientManager patientManager;

	public PatientLiteBM registerPatient(
			PersonalDetailsBM personalDetails,
			PersonalDetailsAdditionalBM personalDetailsAdditional,
			ContactDetailsBM currentContactDetails,
			ContactDetailsBM permanentContactDetails,
//			ContactDetailsBM primaryEmergencyContact,
//			ContactDetailsBM secondaryEmergencyContact,
			List<PatientAllergiesBM> patientAllergiesBMList,
			List<PatientImmunizationsBM> immunizationsBMList,
			OtherDetailsBM otherDetailsBM,
			boolean isPermanentContactSameAsCurrent,
//			boolean isEmergencyPriContactSameAsCurrent,
//			boolean isEmergencySecContactSameAsCurrent, 
			String createdBy) {

		PatientInfoDetailBM bmToSave = preparePatientInfoDetailBM(
				personalDetails, personalDetailsAdditional,
				currentContactDetails, permanentContactDetails,
//				primaryEmergencyContact, secondaryEmergencyContact,
				patientAllergiesBMList, immunizationsBMList, otherDetailsBM,
				isPermanentContactSameAsCurrent
//				isEmergencyPriContactSameAsCurrent,
//				isEmergencySecContactSameAsCurrent
				);
		
		bmToSave.setCreatedBy(createdBy);
		
		PatientLiteBM patientLiteBM =registrationManager.registerPatient( bmToSave );
		
		return patientLiteBM;
	}
	
	public PatientInfoDetailBM updatePatient(
			PersonalDetailsBM personalDetails,
			PersonalDetailsAdditionalBM personalDetailsAdditional,
			ContactDetailsBM currentContactDetails,
			ContactDetailsBM permanentContactDetails,
//			ContactDetailsBM primaryEmergencyContact,
//			ContactDetailsBM secondaryEmergencyContact,
			List<PatientAllergiesBM> patientAllergiesBMList,
			List<PatientImmunizationsBM> immunizationsBMList,
			OtherDetailsBM otherDetailsBM,
			boolean isPermanentContactSameAsCurrent,
//			boolean isEmergencyPriContactSameAsCurrent,
//			boolean isEmergencySecContactSameAsCurrent, 
			String modifiedBy) {

		PatientInfoDetailBM bmToSave = preparePatientInfoDetailBM(
				personalDetails, personalDetailsAdditional,
				currentContactDetails, permanentContactDetails,
//				primaryEmergencyContact, secondaryEmergencyContact,
				patientAllergiesBMList, immunizationsBMList, otherDetailsBM,
				isPermanentContactSameAsCurrent
//				isEmergencyPriContactSameAsCurrent,
//				isEmergencySecContactSameAsCurrent
				);
		
		bmToSave.setModifiedBy(modifiedBy);

		PatientInfoDetailBM modifiedPatient = patientManager.modifyPatientDetails(bmToSave);

		return modifiedPatient;
	}
	
	private PatientInfoDetailBM preparePatientInfoDetailBM(
			PersonalDetailsBM personalDetails,
			PersonalDetailsAdditionalBM personalDetailsAdditional,
			ContactDetailsBM currentContactDetails,
			ContactDetailsBM permanentContactDetails,
//			ContactDetailsBM primaryEmergencyContact,
//			ContactDetailsBM secondaryEmergencyContact,
			List<PatientAllergiesBM> patientAllergiesBMList,
			List<PatientImmunizationsBM> immunizationsBMList,
			OtherDetailsBM otherDetailsBM,
			boolean isPermanentContactSameAsCurrent
//			boolean isEmergencyPriContactSameAsCurrent,
//			boolean isEmergencySecContactSameAsCurrent 
			){
		
		// Personal details
		personalDetails.setDateOfBirth( Converters.getDate( personalDetails.getDateOfBirth() ));
		personalDetails.setRegistrationDate( Converters.getDate( personalDetails.getRegistrationDate() ));
		personalDetails.setGender(Converters.getEntityValue(personalDetails.getGender()));
		personalDetails.setPatientCategory(Converters.getEntityValue(personalDetails.getPatientCategory()));
		personalDetails.setPatientRating(Converters.getEntityValue(personalDetails.getPatientRating()));
		personalDetails.setRegistrationStatus( Converters.getEntityValue(personalDetails.getRegistrationStatus()));
		personalDetails.setRegistrationType( Converters.getEntityValue(personalDetails.getRegistrationType()));
		personalDetails.setTitle( Converters.getEntityValue(personalDetails.getTitle()));
		
		personalDetailsAdditional.setIdValidUpto( Converters.getDate( personalDetailsAdditional.getIdValidUpto() ) );
		personalDetailsAdditional.setVisaValidUpto( Converters.getDate( personalDetailsAdditional.getVisaValidUpto() ) );
		personalDetailsAdditional.setBloodGroup( Converters.getEntityValue(personalDetailsAdditional.getBloodGroup()) );
		personalDetailsAdditional.setIdProof( Converters.getEntityValue(personalDetailsAdditional.getIdProof()) );
		personalDetailsAdditional.setMaritalStatus( Converters.getEntityValue(personalDetailsAdditional.getMaritalStatus()) );
		personalDetailsAdditional.setMotherTongue( Converters.getEntityValue(personalDetailsAdditional.getMotherTongue()));
		personalDetailsAdditional.setNationality( Converters.getEntityValue(personalDetailsAdditional.getNationality()));
		personalDetailsAdditional.setReferredBy( Converters.getEntityValue(personalDetailsAdditional.getReferredBy()) );
		personalDetailsAdditional.setReligion( Converters.getEntityValue(personalDetailsAdditional.getReligion()));
		
		// Contact details 
		List<ContactDetailsBM> contactDetailList = new ArrayList<ContactDetailsBM>(0);
		ContactBM contacts = new ContactBM();
		
		if( (currentContactDetails.getFirstName()  != null) && (! currentContactDetails.getFirstName().equals("")) ){
			currentContactDetails.setContactType(new CodeAndDescription(ContactType.CURRENT, null));
			contactDetailList.add(currentContactDetails);
		}
		
		if( permanentContactDetails.getFirstName()  != null  && (! permanentContactDetails.getFirstName().equals("")) ){
			permanentContactDetails.setContactType(new CodeAndDescription(ContactType.PERMANENT, null));
			contactDetailList.add(permanentContactDetails);
		}
		
//		if( primaryEmergencyContact.getFirstName()  != null && (! primaryEmergencyContact.getFirstName().equals(""))){
//			primaryEmergencyContact.setContactType(new CodeAndDescription(ContactType.EMERGENCY_PRIMARY, null));
//			contactDetailList.add(primaryEmergencyContact);
//		}
		
//		if( secondaryEmergencyContact.getFirstName()  != null  && (! secondaryEmergencyContact.getFirstName().equals(""))){
//			secondaryEmergencyContact.setContactType(new CodeAndDescription(ContactType.EMERGENCY_SECONDARY, null));
//			contactDetailList.add(secondaryEmergencyContact);
//		}
		
		for( ContactDetailsBM contactDetails : contactDetailList ){
			contactDetails.setForEntity(new CodeAndDescription( "PATIENT","PATIENT"));
			contactDetails.setContactType( Converters.getEntityValue(contactDetails.getContactType()) );
			contactDetails.setCountry( Converters.getEntityValue(contactDetails.getCountry()) );
			contactDetails.setGender( Converters.getEntityValue(contactDetails.getGender()) );
			contactDetails.setForEntity( Converters.getEntityValue(contactDetails.getForEntity()) );
			contactDetails.setRelationCode( Converters.getEntityValue(contactDetails.getRelationCode()) );
			contactDetails.setSalutation( Converters.getEntityValue(contactDetails.getSalutation()) );
			contactDetails.setState( Converters.getEntityValue(contactDetails.getState()) );
		}
		
		if( contactDetailList != null && ( contactDetailList.size() > 0)){
			contacts.setContactDetailList(contactDetailList);
		}
		
		PatientInfoDetailBM patientInfoDetailsBM = new PatientInfoDetailBM();
		
		patientInfoDetailsBM.setPersonalDetailsBM( personalDetails );
		patientInfoDetailsBM.setPersonalDetailsAdditionalBM( personalDetailsAdditional );
		
		patientInfoDetailsBM.setContacts( contacts );
		patientInfoDetailsBM.getContacts().setIsPermanentContactSameAsCurrent(isPermanentContactSameAsCurrent);
//		patientInfoDetailsBM.getContacts().setIsEmergencyPriContactSameAsCurrent(isEmergencyPriContactSameAsCurrent);
//		patientInfoDetailsBM.getContacts().setIsEmergencySecContactSameAsCurrent(isEmergencySecContactSameAsCurrent);
		
		patientInfoDetailsBM.setPatientAllergiesBMList(patientAllergiesBMList);
		patientInfoDetailsBM.setPatientImmunizationsBMList(immunizationsBMList);
		patientInfoDetailsBM.setOtherDetailsBM( otherDetailsBM );
		
		return patientInfoDetailsBM;
	}
	
	public PatientLiteBM getPatientDetails(Integer patientId) {
		if (patientId != null) {
			PatientLiteBM patientDetails = patientManager.getPatientLiteBM(patientId);
			return patientDetails;
		}
		return null;
	}

	public ListRange getPatientsDetails(	String patientId, 
											String patientName,
											String phoneNumber, 
											String genderCode, 
											String maritalStatusCode,
											String fatherHusbandName, 
											String registrationStatus,
											Date registrationFrom, 
											Date registrationTo, 
											Date fromAge,
											Date toAge, 
											Date patientLastVisitedFromDate,
											Date patientLastVisitedToDate, 
											int start, 
											int count, 
											String orderBy) {

		ListRange listrange = new ListRange();

		Integer patientCode = null;
		if (patientId != null && !patientId.equals("")) {
			patientCode = new Integer(patientId);
		}

		List<PatientInfoSummaryBM> patientSummaryList = patientManager.getPatients(
																			patientCode,
																			Converters.getEntityValue(patientName),
																			Converters.getEntityValue(phoneNumber), 
																			Converters.getEntityValue(genderCode), 
																			Converters.getEntityValue(maritalStatusCode),
																			Converters.getEntityValue(fatherHusbandName),
																			Converters.getEntityValue(registrationStatus), 
																			Converters.getDate(registrationFrom), 
																			Converters.getDate(registrationTo), 
																			Converters.getDate(fromAge), 
																			Converters.getDate(toAge),
																			Converters.getDate(patientLastVisitedFromDate), 
																			Converters.getDate(patientLastVisitedToDate)
																		);

		List<PatientSummary> patientSummariesList = new ArrayList<PatientSummary>();
		List<PatientSummary> pageSizeData = new ArrayList<PatientSummary>();
		if (patientSummaryList != null) {
			for (PatientInfoSummaryBM patientInfoSummary : patientSummaryList) {
				PatientSummary patientSummary = new PatientSummary();

				if (patientInfoSummary.getPersonalDetailsBM() != null) {
					patientSummary.setFirstName(patientInfoSummary.getPersonalDetailsBM().getFirstName());
					patientSummary.setMiddleName(patientInfoSummary.getPersonalDetailsBM().getMiddleName());
					patientSummary.setLastName(patientInfoSummary.getPersonalDetailsBM().getLastName());
					patientSummary.setPatientId(patientInfoSummary.getPersonalDetailsBM().getRegistrationNumber().toString());
					patientSummary.setRegistrationDate(patientInfoSummary.getPersonalDetailsBM().getRegistrationDate());
					patientSummary.setAge(AgeCalculation.calculateDOB(patientInfoSummary.getPersonalDetailsBM().getDateOfBirth()));

					patientSummary.setName(patientInfoSummary.getPersonalDetailsBM().getFirstName()
							+ " "
							+ patientInfoSummary.getPersonalDetailsBM().getMiddleName()
							+ " "
							+ patientInfoSummary.getPersonalDetailsBM().getLastName() + " ");

					if (patientInfoSummary.getPersonalDetailsBM().getRegistrationStatus() != null) {
						patientSummary.setRegistrationStatus(patientInfoSummary.getPersonalDetailsBM().getRegistrationStatus().getDescription());
					} else {
						patientSummary.setRegistrationStatus(null);
					}

					if (patientInfoSummary.getPersonalDetailsBM().getRegistrationType() != null) {
						patientSummary.setRegistrationType(patientInfoSummary.getPersonalDetailsBM().getRegistrationType().getDescription());
						patientSummary.setRegistrationTypeCode( patientInfoSummary.getPersonalDetailsBM().getRegistrationType().getCode());
					} else {
						patientSummary.setRegistrationType(null);
					}
				}

				if (patientInfoSummary.getContactDetailsBM() != null) {
					patientSummary.setOthers(patientInfoSummary.getContactDetailsBM().getPhoneNumber());
				}

				patientSummary.setLastVisitedDate(patientInfoSummary.getLastVisited());
				patientSummariesList.add(patientSummary);
			}

			int index = 0;
			while ((start + index < start + count)
					&& (patientSummariesList.size() > start + index)) {
				pageSizeData.add(patientSummariesList.get(start + index));
				index++;
			}

			listrange.setData(pageSizeData.toArray());
			listrange.setTotalSize(patientSummaryList.size());
		} else {
			Object[] patientSummaryObj = new Object[0];
			listrange.setData(patientSummaryObj);
			listrange.setTotalSize(patientSummaryObj.length);
		}

		return listrange;
	}
	
	public void setPatientManager(PatientManager patientManager) {
		this.patientManager = patientManager;
	}

	public void setRegistrationManager(
			RegistrationManager registrationManager) {
		this.registrationManager = registrationManager;
	}

	public void convertEmergency2Normal(List<Integer> patientIdList) {
		patientManager.emergencyToNormal(patientIdList);
	}
}
