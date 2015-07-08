Ext.namespace("OPD.registration");

function showReceiptWindow(receiptConfig) {
	/** in the config we need to pass these below properties to loadData
	 * 
	 * 1.selectedAccountHolderId
		2.selectedAccountHolderName
		3.selectedEntityTypeId
		4.selectedEntityTypeName
		5.selectedAmount
		6.selectedReceiptDate
		7.makeReadOnly --> For making PatientId as readOnly in case of Requisition list 
	 */
	
		
	var createRcptPanel = new OPD.issueReceipt.IssueReceipt();
	
	var toBeAddPanel = createRcptPanel.getPanel();
	toBeAddPanel.frame= true;
	
	if(!Ext.isEmpty(receiptConfig) && receiptConfig.makeReadOnly === true)
	{
		toBeAddPanel.patientIdNbrField.readOnly = true;
	}
	
	var recptWindow = new Ext.Window( {
		title :'Create Receipt',
		width :'90%',
		height :'70%',
		y:0,
		modal :true,
		resizable :false,
		frame: true,
		closeAction :'hide',
		items : toBeAddPanel
	});
	createRcptPanel.loadData( receiptConfig.selectedAccountHolderId );
	recptWindow.show();
	
	Ext.ux.event.Broadcast.subscribe('closeReceiptWindow', function(){
		recptWindow.hide();
	},this, null , recptWindow);
}

OPD.registration.PatientRegistrationPanel = Ext.extend(Object,
{
	constructor : function(	isPopUp, 
							config, 
							currAddConfig,
							emrgPrimaryAddConfig, 
							emrgSecondaryAddConfig,
							premanentAddConfig, 
							emrgPrimaryDetailsConfig,
							emrgSecondaryDetailsConfig) {
			
		this.isPopUp = isPopUp; 
		this.config = config;
		this.emrgPrimaryAddConfig = emrgPrimaryAddConfig;
		this.emrgSecondaryAddConfig = emrgSecondaryAddConfig;
		this.currAddConfig = currAddConfig;
		this.premanentAddConfig = premanentAddConfig;
		this.emrgPrimaryDetailsConfig = emrgPrimaryDetailsConfig;
		this.emrgSecondaryDetailsConfig = emrgSecondaryDetailsConfig;
		
		var mainThis = this;

		if (Ext.isEmpty(config)) {
			this.config = {};
		}

		this.receiptConfig;

		if (Ext.isEmpty(currAddConfig)) {
			this.currAddConfig = {};
			this.currAddConfig.addressType = 'current'
		}

		if (Ext.isEmpty(emrgPrimaryAddConfig)) {
			this.emrgPrimaryAddConfig = {};
			this.emrgPrimaryAddConfig.addressType = 'emergency'
		}

		if (Ext.isEmpty(emrgSecondaryAddConfig)) {
			this.emrgSecondaryAddConfig = {};
			this.emrgSecondaryAddConfig.addressType = 'emergency'
		}

		if (Ext.isEmpty(premanentAddConfig)) {
			this.premanentAddConfig = {};
			this.premanentAddConfig.addressType = 'permanent'
		}

		this.personalDetails = new OPD.registration.PatientPersonalDetails(this.config);

		this.patientCurrentContactDetails = new OPD.ContactDetails(this.currAddConfig);
		this.patientCurrentContactDetails.getCountryCbx().required=true;
		this.patientCurrentContactDetails.getCountryCbx().allowBlank=false;
		
		this.patientPermanentContactDetails = new OPD.ContactDetails(this.premanentAddConfig);

		this.patientEmergencyPrimaryContactDetails = new OPD.ContactDetails(this.emrgPrimaryAddConfig);
		this.emergencyPrimaryDetails = new OPD.EmergencyContactDetails(this.emrgPrimaryDetailsConfig);

		this.patientEmergencySecondaryContactDetails = new OPD.ContactDetails(this.emrgSecondaryAddConfig);
		this.emergencySecondaryDetails = new OPD.EmergencyContactDetails(this.emrgSecondaryDetailsConfig);

		this.allergiesPanel = new OPD.registration.AllergiesDetailsPanel(config);
		this.allergiesPanel.loadData(this.config);

		this.immunizationPanel = new OPD.registration.ImmunizationDetailsPanel(config);
		this.immunizationPanel.loadData(this.config);

		this.patientOtherDetailsPanel = new OPD.registration.PatientOtherDetails(this.config);
		this.patientOtherDetailsPanel.loadData(this.config);

		this.sameAsCurrentChk = getSameAsCurrentChk('Permanent');
		this.sameAsCurrentChkEmergPrimary = getSameAsCurrentChk('Emergency primary');
		this.sameAsCurrentChkEmergSecondary = getSameAsCurrentChk('Emergency secondary');

		this.sameAsCurrentChk.addListener(
						'check',
						function(comp, checked) {
							var data = mainThis.patientCurrentContactDetails.getData();
							if (checked) {
								mainThis.patientPermanentContactDetails.setData(data);
								mainThis.patientPermanentContactDetails.getPanel().disable();
							} else {
								mainThis.patientPermanentContactDetails.getPanel().getForm().reset();
								mainThis.patientPermanentContactDetails.stateStoreForCountry.removeAll();
								mainThis.patientPermanentContactDetails.getPanel().enable();
								mainThis.patientPermanentContactDetails.stateCombo.disable();
							}
						});
		this.sameAsCurrentChkEmergPrimary.addListener(
						'check',
						function(comp, checked) {
							var data = mainThis.patientCurrentContactDetails.getData();
							if (checked) {
								mainThis.patientEmergencyPrimaryContactDetails.setData(data);
								mainThis.patientEmergencyPrimaryContactDetails.getPanel().disable();
							} else {
								mainThis.patientEmergencyPrimaryContactDetails.getPanel().getForm().reset();
								mainThis.patientEmergencyPrimaryContactDetails.stateStoreForCountry.removeAll();
								mainThis.patientEmergencyPrimaryContactDetails.getPanel().enable();
								mainThis.patientEmergencyPrimaryContactDetails.stateCombo.disable();
							}
						});

		this.sameAsCurrentChkEmergSecondary
				.addListener(
						'check',
						function(comp, checked) {
							var data = mainThis.patientCurrentContactDetails.getData();
							if (checked) {
								mainThis.patientEmergencySecondaryContactDetails.setData(data);
								mainThis.patientEmergencySecondaryContactDetails.getPanel().disable();
							} else {
								mainThis.patientEmergencySecondaryContactDetails.getPanel().getForm().reset();
								mainThis.patientEmergencySecondaryContactDetails.stateStoreForCountry.removeAll();
								mainThis.patientEmergencySecondaryContactDetails.getPanel().enable();
								mainThis.patientEmergencySecondaryContactDetails.stateCombo.disable();
							}
						});

		this.patientRegistrationTabPnl = new Ext.TabPanel({
			width :'100%',
			height : 460,
			activeTab :0,
			deferredRender :false,
			layoutOnTabChange :true,
			hideMode :'offsets',
			items : [
					{
						title :msg.personaldetails,
						frame :true,
						items :this.personalDetails.getPanel()
					},
					{
						title :msg.contactdetails,
						autoScroll :true,
						height :400,
						frame :true,
						items : [ {
							layout :'column',
							defaults : {
								columnWidth :.40,
								style :'margin-left:5px;'
							},
							items : [
									{
										layout :'form',
										style :'margin-top:30px',
										items :this.patientCurrentContactDetails.getPanel()
									},
									{
										layout :'column',
										defaults : {
											columnWidth :1,
											style :'margin-bottom:5px;'
										},
										items : [
												{
													layout :'form',
													labelWidth :.01,
													items :this.sameAsCurrentChk
												},
												{
													layout :'form',
													items :this.patientPermanentContactDetails.getPanel()
												} ]
									} ]
						} ]
					},
//					{
//						title :msg.emergencycontactdetails,
//						autoScroll :true,
//						height :400,
//						frame :true,
//						items : [ {
//							layout :'column',
//							defaults : {
//								columnWidth :1
//							},
//							items : [
//									{
//										layout :'column',
//										defaults : {
//											columnWidth :.45,
//											style :'margin-left:5px;'
//										},
//										items : [
//												{
//													layout :'form',
//													style :'margin-top:30px',
//													items :this.emergencyPrimaryDetails.getPanel()
//												},
//												{
//													layout :'column',
//													defaults : {
//														columnWidth :1,
//														style :'margin-bottom:5px;'
//													},
//													items : [
//															{
//																layout :'form',
//																labelWidth :.01,
//																items :this.sameAsCurrentChkEmergPrimary
//															},
//															{
//																layout :'form',
//																items :this.patientEmergencyPrimaryContactDetails.getPanel()
//															} ]
//												} ]
//									},
//									{
//										layout :'column',
//										defaults : {
//											columnWidth :.97
//										},
//										items : [ {
//											xtype :'fieldset',
//											height :'100%',
//											collapsible :true,
//											collapsed :true,
//											layout :'column',
//											title :'Emergency secondary contact Details',
//											items : [ {
//												layout :'column',
//												defaults : {
//													columnWidth :.48,
//													style :'margin-left:5px;'
//												},
//												items : [
//														{
//															layout :'form',
//															style :'margin-top:30px',
//															items :this.emergencySecondaryDetails.getPanel()
//														},
//														{
//															layout :'column',
//															defaults : {
//																columnWidth :1,
//																style :'margin-bottom:5px;'
//															},
//															items : [
//																	{
//																		layout :'form',
//																		labelWidth :.01,
//																		items :this.sameAsCurrentChkEmergSecondary
//																	},
//																	{
//																		layout :'form',
//																		items :this.patientEmergencySecondaryContactDetails.getPanel()
//																	} ]
//														} ]
//											} ]
//										} ]
//									} ]
//						} ]
//
//					},
					{
						title :msg.allergies,
						autoScroll :true,
						height :400,
						frame :true,
						items :this.allergiesPanel.getPanel()
					},
					{
						title :msg.immunizations,
						autoScroll :true,
						height :400,
						frame :true,
						items :this.immunizationPanel.getPanel()
					},
					{
						title :msg.otherdetails,
						autoScroll :true,
						height :400,
						frame :true,
						items :this.patientOtherDetailsPanel.getPanel()
					} 
				]
		});

		this.saveBtn = new Ext.Button({
			text :msg.btn_save,
			iconCls :'save_btn',
			scope :this,
			hidden :this.config.type,
			handler : function() {
				this.saveBtnHandler();
			}
		});

		this.resetBtn = new Ext.Button( {
			text :msg.reset,
			iconCls :'reset-icon',
			hidden :this.config.type,
			scope :this,
			handler : function() {
				this.resetButtonClicked( config );
			}
		});

		this.buttonsPanel = new Ext.Panel( {
			buttonAlign :'right',
			buttons : [ this.saveBtn, this.resetBtn]
		});

		this.patientRegistrationFormPanel = new Ext.Panel( {
			height : '100%',
			frame :false,
			buttonAlign :'right',
			items : [ this.patientRegistrationTabPnl,
					this.buttonsPanel ]
		});

		// for monitoring the personal details panel
		this.personalDetails.getPanel().on("clientvalidation",
				function(thisForm, isValid) {
					if ( isValid  && this.patientCurrentContactDetails.getCountryCbx().isValid() ) {
						this.buttonsPanel.buttons[0].enable();
					} else {
						this.buttonsPanel.buttons[0].disable();
					}
				}, this);
	},

	saveBtnHandler : function() {

		// Personal details tab
		var personalDetailsValues = this.personalDetails.getData();
		
		var patientRegistrationPersonalDetails = {
			registrationDate :convertStringDateTimeToDateObj(personalDetailsValues['registrationDate']),
			registrationType : {code :'NORMAL'/*personalDetailsValues['registrationType']*/},
			registrationStatus : {code :personalDetailsValues['registrationStatus']},
			patientRating : {code :personalDetailsValues['patientRating']},
			patientCategory : {code :personalDetailsValues['patientCategory']},
			
			firstName :personalDetailsValues['firstName'],
			middleName :personalDetailsValues['middleName'],
			lastName :personalDetailsValues['lastName'],
			dateOfBirth :convertStringDateToDateObj(personalDetailsValues['dateOfBirth']),
			registrationNumber :personalDetailsValues['registrationNumber'],
			gender : {code :personalDetailsValues['gender']},
			title : {code : personalDetailsValues['title']},
			imagePopertyBM :{fileName : this.personalDetails.getPhotoPanel().getImageName(),
							 filePath : this.personalDetails.getPhotoPanel().getImagePath()}
		};

		var patientRegistrationAdditionalDetails = {
			height :personalDetailsValues['height'],
			weight :personalDetailsValues['weight'],
			heightIndicator:{code:personalDetailsValues['heightIndicator']},
			weightIndicator:{code:personalDetailsValues['weightIndicator']},
			idNumber :personalDetailsValues['idNumber'],
			bloodDonorId :personalDetailsValues['bloodDonorId'],
			patientOccupation :personalDetailsValues['patientOccupation'],
			monthlyIncome :personalDetailsValues['monthlyIncome'],
			fatherHusband :personalDetailsValues['fatherHusband'],
			idValidUpto :convertStringDateToDateObj(personalDetailsValues['idValidUpto']),
			religion : {code :personalDetailsValues['religion']},
			maritalStatus : {code :personalDetailsValues['maritalStatus']},
			idProof : {code :personalDetailsValues['idProof']},
			bloodGroup : {code :personalDetailsValues['bloodGroup']},
			motherTongue : {code :personalDetailsValues['motherTongue']},
			nationality : {code :personalDetailsValues['nationality']},
			referredBy : {code :personalDetailsValues['referredBy']}
		};
		
		
		// Contact details
		var currentContactValues = this.patientCurrentContactDetails.getData();
		var currentContactDetails = {
			firstName :personalDetailsValues['firstName'],
			salutation : {code :personalDetailsValues['title']},
			gender : {code :personalDetailsValues['gender']},
			middleName :personalDetailsValues['middleName'],
			lastName :personalDetailsValues['lastName'],
			houseNumber :currentContactValues['housename'],
			street :currentContactValues['placestreet'],
			city :currentContactValues['citydistrict'],
			pincode :currentContactValues['pincode'],
			phoneNumber :currentContactValues['phonenumber'],
			mobileNumber :currentContactValues['mobilenumber'],
			faxNumber :currentContactValues['faxnumber'],
			email :currentContactValues['emailaddress'],
			stayDuration :currentContactValues['durationofstay'],
			country : {code :currentContactValues['country']},
			state : {code :currentContactValues['state']}
		}
		
		var permanentChk = this.sameAsCurrentChk.getValue();
		var permanentContactValues;		
		if (permanentChk == true) {
			permanentContactValues = currentContactValues;
		} else {
			permanentContactValues = this.patientPermanentContactDetails.getData();
		}

		var permanentContactDetails = {
			salutation : {code :personalDetailsValues['title']},
			firstName :personalDetailsValues['firstName'],
			middleName :personalDetailsValues['middleName'],
			lastName :personalDetailsValues['lastName'],
			gender : {code :personalDetailsValues['gender']},
			houseNumber :permanentContactValues['housename'],
			street :permanentContactValues['placestreet'],
			city :permanentContactValues['citydistrict'],
			pincode :permanentContactValues['pincode'],
			phoneNumber :permanentContactValues['phonenumber'],
			mobileNumber :permanentContactValues['mobilenumber'],
			faxNumber :permanentContactValues['faxnumber'],
			email :permanentContactValues['emailaddress'],
			stayDuration :permanentContactValues['durationofstay'],
			country : {code :permanentContactValues['country']},
			state : {code :permanentContactValues['state']}
		}
				
		// Emergency contact details
//		var emergencyPrimaryValues = this.emergencyPrimaryDetails.getData();
		
//		var emergencyPrimaryChk = this.sameAsCurrentChkEmergPrimary.getValue();
//		var emergencyPrimaryContactValues;
//		if (emergencyPrimaryChk == true) {
//			emergencyPrimaryContactValues = currentContactValues;
//		} else {
//			emergencyPrimaryContactValues = this.patientEmergencyPrimaryContactDetails.getData();
//		}
//		
//		var emergencyPrimaryContactDetails = {
//			salutation : {code :emergencyPrimaryValues['title']},
//			firstName :emergencyPrimaryValues['firstname'],
//			middleName :emergencyPrimaryValues['middlename'],
//			lastName :emergencyPrimaryValues['lastname'],
//			gender : {code :emergencyPrimaryValues['gender']},
//			phoneNumber :emergencyPrimaryValues['phonenumber'],
//			mobileNumber :emergencyPrimaryValues['mobilenumber'],
//			faxNumber :emergencyPrimaryValues['faxnumber'],
//			email :emergencyPrimaryValues['emailaddress'],
//			relationCode : {code :emergencyPrimaryValues['relationship']},
//			houseNumber :emergencyPrimaryContactValues['housename'],
//			street :emergencyPrimaryContactValues['placestreet'],
//			city :emergencyPrimaryContactValues['citydistrict'],
//			pincode :emergencyPrimaryContactValues['pincode'],
//			stayDuration :emergencyPrimaryContactValues['durationofstay'],
//			country : {code :emergencyPrimaryContactValues['country']},
//			state : {code :emergencyPrimaryContactValues['state']}
//		}
//		

//		var emergencySecondaryValues = this.emergencySecondaryDetails.getData();
//		
//		var emergencySecondaryChk = this.sameAsCurrentChkEmergSecondary.getValue();
//		var emergencySecondaryContactValues;
//		if (emergencySecondaryChk == true) {
//			emergencySecondaryContactValues = currentContactValues;
//		} else {
//			emergencySecondaryContactValues = this.patientEmergencySecondaryContactDetails.getData();
//		}
//
//		var emergencySecondaryContactDetails = {
//			salutation : {code :emergencySecondaryValues['title']},
//			firstName :emergencySecondaryValues['firstname'],
//			middleName :emergencySecondaryValues['middlename'],
//			lastName :emergencySecondaryValues['lastname'],
//			gender : {code :emergencySecondaryValues['gender']},
//			phoneNumber :emergencySecondaryValues['phonenumber'],
//			mobileNumber :emergencySecondaryValues['mobilenumber'],
//			faxNumber :emergencySecondaryValues['faxnumber'],
//			email :emergencySecondaryValues['emailaddress'],
//			relationCode : {code :emergencySecondaryValues['relationship']},
//			houseNumber :emergencySecondaryContactValues['housename'],
//			street :emergencySecondaryContactValues['placestreet'],
//			city :emergencySecondaryContactValues['citydistrict'],
//			pincode :emergencySecondaryContactValues['pincode'],
//			stayDuration :emergencySecondaryContactValues['durationofstay'],
//			country : {code :emergencySecondaryContactValues['country']},
//			state : {code :emergencySecondaryContactValues['state']}
//		}

		// Allergies
		var allergyDetails = null;
		var tmpBool = this.allergiesPanel.anyAllergiesSelected();
		if(tmpBool == true){
			allergyDetails = this.allergiesPanel.getInfoGrid().getData();
		}
		
		// Immunizations
		var immunizationDetails = null;
		var tmpBool = this.immunizationPanel.anyImmunizationsSelected();
		if(tmpBool == true){
			immunizationDetails = this.immunizationPanel.getInfoGrid().getData();
		}
		
		// Other details
		var otherDetailsBM =  this.patientOtherDetailsPanel.getData();
		
		if (this.config.mode == 'edit') {

			patientRegistrationPersonalDetails.registrationNumber = this.config.registrationNumber;
			patientRegistrationAdditionalDetails.registrationNumber = this.config.registrationNumber;

			currentContactDetails.contactDetailsCode = this.currAddConfig.selectedContactDetailsCode;
			currentContactDetails.personelId = this.currAddConfig.selectedPersonelId;
			currentContactDetails.contactType = {code :this.config.selectedcurrentContactTypeCode};
			currentContactDetails.forEntity = {
				code :this.config.selectedcurrentForEntityCode,
				description :this.config.selectedcurrentForEntity
			};

			permanentContactDetails.contactDetailsCode = this.premanentAddConfig.selectedContactDetailsCode;
			permanentContactDetails.personelId = this.premanentAddConfig.selectedPersonelId;
			permanentContactDetails.contactType = {code :this.config.selectedpermanentContactTypeCode};
			permanentContactDetails.forEntity = {
				code :this.config.selectedpermanentForEntityCode,
				description :this.config.selectedpermanentForEntity
			};

//			emergencySecondaryContactDetails.contactDetailsCode = this.emrgSecondaryAddConfig.selectedContactDetailsCode;
//			emergencySecondaryContactDetails.personelId = this.emrgSecondaryAddConfig.selectedPersonelId;
//			emergencySecondaryContactDetails.contactType = {code :this.emrgSecondaryAddConfig.selectedContactType};
//			emergencySecondaryContactDetails.forEntity = {
//				code :this.config.selectedemergencySecondaryForEntityCode,
//				description :this.config.selectedemergencySecondaryForEntity
//			};
//
//			emergencyPrimaryContactDetails.contactDetailsCode = this.emrgPrimaryAddConfig.selectedContactDetailsCode;
//			emergencyPrimaryContactDetails.personelId = this.emrgPrimaryAddConfig.selectedPersonelId;
//			emergencyPrimaryContactDetails.contactType = {code :this.emrgPrimaryAddConfig.selectedContactType};
//			emergencyPrimaryContactDetails.forEntity = {
//				code :this.config.selectedemergencyPrimaryForEntityCode,
//				description :this.config.selectedemergencyPrimaryForEntity
//			};

			PatientManagementController.updatePatient(
					patientRegistrationPersonalDetails,
					patientRegistrationAdditionalDetails,
					currentContactDetails,
					permanentContactDetails,
//					emergencyPrimaryContactDetails,
//					emergencySecondaryContactDetails,
					allergyDetails,
					immunizationDetails,
					otherDetailsBM,
					permanentChk,
//					emergencyPrimaryChk,
//					emergencySecondaryChk,
					getAuthorizedUserInfo().userName,
					{
						callback : this.saveBtnCallBackUpdate,
						callbackScope : this
					}
				);
				
		} else {
			PatientManagementController.registerPatient(
					patientRegistrationPersonalDetails,
					patientRegistrationAdditionalDetails,
					currentContactDetails,
					permanentContactDetails,
					allergyDetails,
					immunizationDetails,
					otherDetailsBM,
					permanentChk,
					getAuthorizedUserInfo().userName, 
					{
						callback : this.saveBtnCallBackRegister,
						callbackScope : this
					}
				);
		}
	},
	
	saveBtnCallBackRegister: function(patientLiteBM){

		var personalDetailsValues = this.personalDetails.getData();

		var mainThis =this;
    	if(!Ext.isEmpty(this.isPopUp) &&  true == this.isPopUp){
    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
			Ext.ux.event.Broadcast.publish('closeRegisterPatientWindow');
		} else {
			this.resetButtonClicked();
		}
		
		patientStore.reload();
		
		PatientManager.getBusinessPartnerId( patientLiteBM.patientId , {
					callback: function( accountId ){
						referenceNbr = patientLiteBM.patientId;
						clientName = configs.PatientRegistrationType;
				
						BillManager.runBill( accountId, clientName, referenceNbr, 
										 function( billObjectBM ){
										 	Ext.Msg.show({
												msg: "Patient has been successfully created with the ID :" + 
													 patientLiteBM.patientId +"." +
													 "<br> Do you want to issue a receipt now..?",
												icon : Ext.MessageBox.INFO,
												buttons: Ext.Msg.YESNO,
												fn: function(buttonValue){
													if(buttonValue == configs.yes){
														var receiptConfig ={
														   		selectedAccountHolderId: patientLiteBM.patientId
													     };
														showReceiptWindow(receiptConfig);				
													}
												}
											});
						});	
					},
					scope: this
		});
		
	},
	
	saveBtnCallBackUpdate: function(patientInfoDetailBM){
		var personalDetailsValues = this.personalDetails.getData();
		var mainThis =this;

    	if(!Ext.isEmpty(this.isPopUp) &&  true == this.isPopUp){
    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
			Ext.ux.event.Broadcast.publish('closeRegisterPatientWindow');
		} else {
			this.resetButtonClicked();
		}
		patientStore.reload();
	},

	resetButtonClicked : function( config ) {
		this.personalDetails.getPanel().getForm().reset();
		this.personalDetails.getPhotoPanel().reset( config );
		this.patientCurrentContactDetails.getPanel().getForm().reset();
		this.patientPermanentContactDetails.getPanel().getForm().reset();
		
		this.patientEmergencyPrimaryContactDetails.getPanel().getForm().reset();
		this.emergencyPrimaryDetails.getPanel().getForm().reset();
		this.patientEmergencySecondaryContactDetails.getPanel().getForm().reset();
		this.emergencySecondaryDetails.getPanel().getForm().reset();
		
		this.allergiesPanel.getPanel().getForm().reset();
		
		this.immunizationPanel.getPanel().getForm().reset();
		
		this.patientOtherDetailsPanel.getPanel().getForm().reset();

		this.sameAsCurrentChk.setValue(false);
		this.sameAsCurrentChkEmergPrimary.setValue(false);
		this.sameAsCurrentChkEmergSecondary.setValue(false);
	},

	getPanel : function() {
		return this.patientRegistrationFormPanel;
	},

	getTabPanel : function() {
		return this.patientRegistrationTabPnl;
	},

	getFocus : function() {
		this.personalDetails.titleCbx.focus();
	}

});
