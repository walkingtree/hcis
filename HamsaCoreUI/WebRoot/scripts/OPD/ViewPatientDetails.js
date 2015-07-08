function getPatientDetailsConfig( patientInfoDetailBM ){
	
	   var patientBM = patientInfoDetailBM;
	   
	   var allergiesCount = patientBM.patientAllergiesBMList.length;
	   var isPatientHaveAnyAllergies =  (allergiesCount == 0) ? false : true;

	   var immunizationsCount = patientBM.patientImmunizationsBMList.length;
	   var isPatientHaveAnyImmunizations =  (immunizationsCount == 0) ? false : true;

	   var config = {
         mode: 'view',
         
         // Personal details tab
         registrationNumber : patientBM.personalDetailsBM.registrationNumber,
		 registrationDate : (null == patientBM.personalDetailsBM.registrationDate) ? null :patientBM.personalDetailsBM.registrationDate, 
         registrationType : (null == patientBM.personalDetailsBM.registrationType) ? null :patientBM.personalDetailsBM.registrationType.code,
         registrationStatus : (null == patientBM.personalDetailsBM.registrationStatus) ? null :patientBM.personalDetailsBM.registrationStatus.code,
         patientRating : (null == patientBM.personalDetailsBM.patientRating) ? null :patientBM.personalDetailsBM.patientRating.code,
         patientCategory : (null == patientBM.personalDetailsBM.patientCategory) ? null :patientBM.personalDetailsBM.patientCategory.code,
         
         title : (null == patientBM.personalDetailsBM.title) ? null :patientBM.personalDetailsBM.title.code,
         firstName : (null == patientBM.personalDetailsBM.firstName) ? null :patientBM.personalDetailsBM.firstName,
         middleName : (null == patientBM.personalDetailsBM.middleName) ? null :patientBM.personalDetailsBM.middleName,
         lastName : (null == patientBM.personalDetailsBM.lastName) ? null :patientBM.personalDetailsBM.lastName,
         dateOfBirth : (null == patientBM.personalDetailsBM.dateOfBirth) ? null :patientBM.personalDetailsBM.dateOfBirth,
         gender : (null == patientBM.personalDetailsBM.gender) ? null :patientBM.personalDetailsBM.gender.code,
         
         height : patientBM.personalDetailsAdditionalBM.height,
         weight : patientBM.personalDetailsAdditionalBM.weight,
         bloodGroup : (null == patientBM.personalDetailsAdditionalBM.bloodGroup) ? null :patientBM.personalDetailsAdditionalBM.bloodGroup.code,
         maritalStatus : (null == patientBM.personalDetailsAdditionalBM.maritalStatus) ? null :patientBM.personalDetailsAdditionalBM.maritalStatus.code,
         fatherHusband : patientBM.personalDetailsAdditionalBM.fatherHusband,

         religion : (null == patientBM.personalDetailsAdditionalBM.religion) ? null :patientBM.personalDetailsAdditionalBM.religion.code,
         motherTongue : (null == patientBM.personalDetailsAdditionalBM.motherTongue) ? null :patientBM.personalDetailsAdditionalBM.motherTongue.code,
         bloodDonorId : patientBM.personalDetailsAdditionalBM.bloodDonorId,
         idProof : (null == patientBM.personalDetailsAdditionalBM.idProof) ? null :patientBM.personalDetailsAdditionalBM.idProof.code,
         idNumber : patientBM.personalDetailsAdditionalBM.idNumber,
         idValidUpto : (null == patientBM.personalDetailsAdditionalBM.idValidUpto) ? null :patientBM.personalDetailsAdditionalBM.idValidUpto,
         nationality : (null == patientBM.personalDetailsAdditionalBM.nationality) ? null :patientBM.personalDetailsAdditionalBM.nationality.code,
         patientOccupation : patientBM.personalDetailsAdditionalBM.patientOccupation,
         monthlyIncome : patientBM.personalDetailsAdditionalBM.monthlyIncome,
         referredBy : (null == patientBM.personalDetailsAdditionalBM.referredBy) ? null :patientBM.personalDetailsAdditionalBM.referredBy.code,
         heightIndicator : ( null == patientBM.personalDetailsAdditionalBM.heightIndicator) ? null : patientBM.personalDetailsAdditionalBM.heightIndicator.code,
         weightIndicator : ( null == patientBM.personalDetailsAdditionalBM.weightIndicator ) ? null : patientBM.personalDetailsAdditionalBM.weightIndicator.code,
         
         // Other details tab
         smoking : patientBM.otherDetailsBM.smokingHabitFlag,
         drinking : patientBM.otherDetailsBM.drinksAlcohol,
         
         // Allergies tab
         isPatientHaveAnyAllergies : isPatientHaveAnyAllergies,
         patientAllergiesList : patientBM.patientAllergiesBMList,
         
         // Immunizations tab
         isPatientHaveAnyImmunizations : isPatientHaveAnyImmunizations,
         patientImmunizationsList : patientBM.patientImmunizationsBMList
       };
      
       var currAddConfig = {}; 
	   var premanentAddConfig = {};
	   var emrgPrimaryAddConfig = {};
	   var emrgSecondaryAddConfig = {};

	   var emrgPrimaryDetailsConfig = {};
	   var emrgSecondaryDetailsConfig = {};
      
       var contactDetails = patientBM.contacts.contactDetailList;
       for(var i =0; i<contactDetails.length; i++){
		   var tmpContact = contactDetails[i];
		   var tmpConfig = {
			 	 mode: 'edit',
			 	 addressType:'',
               	 selectedContactDetailsCode :tmpContact.contactDetailsCode,
				 selectedPersonelId :tmpContact.personelId,
				 selectedHouseNumber :tmpContact.houseNumber,
				 selectedStreet :tmpContact.street,
				 selectedCity :tmpContact.city,
				 selectedPincode : tmpContact.pincode,
				 selectedPhoneNumber :tmpContact.phoneNumber,
				 selectedMobileNumber :tmpContact.mobileNumber,
				 selectedFaxNumber :tmpContact.faxNumber,
				 selectedEmail :tmpContact.email,
				 selectedStayDuration :tmpContact.stayDuration,
				 selectedCountryCode : (null == tmpContact.country) ? null : tmpContact.country.code,
				 selectedCountry : (null == tmpContact.country) ? null : tmpContact.country.description,
				 selectedStateCode : (null == tmpContact.state) ? null : tmpContact.state.code,
				 selectedState : (null == tmpContact.state) ? null : tmpContact.state.description,
				 selectedContactType : (null == tmpContact.contactType) ? null : tmpContact.contactType,

				 selectedEmergencySalutationCode : (null == tmpContact.salutation) ? null :tmpContact.salutation.code,				 
				 selectedEmergencySalutation : (null == tmpContact.salutation) ? null :tmpContact.salutation.description,				 
               	 selectedEmergencyFirstName :tmpContact.firstName,
                 selectedEmergencyMiddleName : tmpContact.middleName,
				 selectedEmergencyLastName :tmpContact.lastName,
				 selectedEmergencyGenderCode : (null == tmpContact.gender) ? null :tmpContact.gender.code,	
				 selectedEmergencyGender : (null == tmpContact.gender) ? null :tmpContact.gender.description,	
				 selectedEmergencyRelationCode: (null == tmpContact.relationCode) ? null :tmpContact.relationCode.code,
				 selectedEmergencyRelation: (null == tmpContact.relationCode) ? null :tmpContact.relationCode.description,
				 selectedEmergencyPhoneNumber:tmpContact.phoneNumber,
				 selectedEmergencyMobileNumber: tmpContact.mobileNumber,
				 selectedEmergencyFaxNumber:tmpContact.faxNumber,
				 selectedEmergencyEmail: tmpContact.email
	       }
	       
	       if(tmpContact.contactType.code == "CURRENT"){
		       	tmpConfig.addressType = 'current';
		       	currAddConfig = tmpConfig;
	       }
	       
	       if(tmpContact.contactType.code == "PERMANENT"){
		       	tmpConfig.addressType = 'permanent';
		       	premanentAddConfig = tmpConfig;
	       }

	       if(tmpContact.contactType.code == "EMERGENCY_PRIMARY"){
		       	tmpConfig.addressType = 'emergency';
		       	emrgPrimaryAddConfig = tmpConfig;
		       	emrgPrimaryDetailsConfig = tmpConfig;
	       }

	       if(tmpContact.contactType.code == "EMERGENCY_SECONDARY"){
		       	tmpConfig.addressType = 'emergency';
		       	emrgSecondaryAddConfig = tmpConfig;
		       	emrgSecondaryDetailsConfig = tmpConfig;
	       }
       	}

		var config = {
						 title: 'View patient',
						 isPopUp : true,
						 config : config,
						 currAddConfig : currAddConfig,
						 emrgPrimaryAddConfig : emrgPrimaryAddConfig,
						 emrgSecondaryAddConfig : emrgSecondaryAddConfig,
						 premanentAddConfig : premanentAddConfig,
						 emrgPrimaryDetailsConfig : emrgPrimaryDetailsConfig,
						 emrgSecondaryDetailsConfig : emrgSecondaryDetailsConfig
	     };
	     return config;
}