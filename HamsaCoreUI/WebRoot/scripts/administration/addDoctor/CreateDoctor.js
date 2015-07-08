CreateDoctor = Ext.extend(Object,{
	constructor:function(config,currAddConfig,emrgAddConfig,premanentAddConfig){
		var mainThis = this;
		if(Ext.isEmpty(config)) {
			config = {};
		}else{
			this.config = config;
		}
		
		if( Ext.isEmpty(currAddConfig)){
			currAddConfig = {};
			currAddConfig.addressType = 'current';
		}
		
		if( Ext.isEmpty(emrgAddConfig)){
			emrgAddConfig = {};
			emrgAddConfig.addressType = 'emergency';
		}
		
		if( Ext.isEmpty(premanentAddConfig)){
			premanentAddConfig = {};
			premanentAddConfig.addressType = 'permanent';
		}
		
		this.doctorConsultationDetails = new administration.addDoctor.DoctorConsultationDetails(config);
		
		this.doctorCurrentContactDetails = new OPD.ContactDetails(currAddConfig);
		this.doctorCurrentContactDetails.getCountryCbx().required=true;
		this.doctorCurrentContactDetails.getCountryCbx().allowBlank=false;
		
		this.doctorPermanentContactDetails = new OPD.ContactDetails(premanentAddConfig);
		this.doctorEmergencyContactDetails = new OPD.ContactDetails(emrgAddConfig);
		if(config.mode=='edit'){
			config.title='Edit doctor';
		}
		
		this.doctorQualificationDetails = new Qualification(config);
		this.emergencyDetails = new OPD.EmergencyContactDetails(config);
		this.personalDetails = new getDoctorPersonalDetails(config);
		
		this.sameAsCurrentChk = new getSameAsCurrentChk('Permanent');
		this.sameAsCurrentChkEmerg = new getSameAsCurrentChk('Emergency');

		this.sameAsCurrentChk.addListener('check',function(comp,checked){
			var data = mainThis.doctorCurrentContactDetails.getData();
			if(checked){
				mainThis.doctorPermanentContactDetails.setData( data );
				mainThis.doctorPermanentContactDetails.getPanel().disable();
			}else{
				mainThis.doctorPermanentContactDetails.getPanel().getForm().reset();
				mainThis.doctorPermanentContactDetails.getPanel().enable();
			}
		});
																	 
		this.sameAsCurrentChkEmerg.addListener('check',function(comp,checked){
			var data = mainThis.doctorCurrentContactDetails.getData();
			if(checked){
				mainThis.doctorEmergencyContactDetails.setData( data );
				mainThis.doctorEmergencyContactDetails.getPanel().disable();
			}else{
				mainThis.doctorEmergencyContactDetails.getPanel().getForm().reset();
				mainThis.doctorEmergencyContactDetails.getPanel().enable();
			}
		});
		
		
		this.createDoctorTabPnl = new Ext.TabPanel({
			width:'100%',
			height:'100%',
			border:false,
			frame: false,	
			activeTab:0,
			layoutOnTabChange :true,
			deferredRender:false,
			hideMode:'offsets',
			items:[
				{
					title:msg.personaldetails,
					border:false,
					frame: true,	
					items:this.personalDetails.getPanel()
				},
				{
					title:msg.contactdetails,
					border:false,
					frame: true,	
					items:[
				    	{
				    		layout:'column',
				    		defaults:{columnWidth:.45,style:'margin-left:5px;'},
				    		items:[
				    			{
				    				layout:'form',
				    				style:'margin-top:30px',
				    				items:this.doctorCurrentContactDetails.getPanel()
				    			},
				    			{
				    				layout:'column',
				    				defaults:{ columnWidth:1, style:'margin-bottom:5px;' },
				    				items:[
					    				{
					    					layout:'form',
					    					labelWidth:.01,
					    					items:this.sameAsCurrentChk
					    				},
					    				{
					    					layout:'form',
					    					items:this.doctorPermanentContactDetails.getPanel()
					    				}
				    				]
				    			}
				    		]
				    	}
				    ]
				},
				{
					title:msg.emergencycontactdetails,
					border:false,
					frame: true,	
					items:[
				    	{
				    		layout:'column',
				    		defaults:{columnWidth:.45,style:'margin-left:5px;'},
				    		items:[
				    			{
				    				layout:'form',
				    				style:'margin-top:30px',
				    				items:this.emergencyDetails.getPanel()
				    			},
				    			{
				    				layout:'column',
				    				defaults:{ columnWidth:1, style:'margin-bottom:5px;' },
				    				items:[
					    				{
					    					layout:'form',
					    					labelWidth:.01,
					    					items:this.sameAsCurrentChkEmerg
					    				},
					    				{
					    					layout:'form',
					    					items:this.doctorEmergencyContactDetails.getPanel()
					    				}
				    				]
				    			}
				    		]
				    	}
				    ]
				},
				{
					title:msg.qualificationdetails,
					border:false,
					frame: true,
					items:this.doctorQualificationDetails.getPanel()
				},
				{
					title:'Consultation Details',
					border:false,
					frame: true,
					items:this.doctorConsultationDetails.getPanel()
				},
			]
		});

		this.createDoctorformPnl = new Ext.Panel({
		     frame:false,
		     buttonAlign:'right',
		     items:this.createDoctorTabPnl,
		     buttons:[
				{
				text:'Save',
				cls:'x-btn-text-icon',
				icon:'images/disk.png',
				scope:this,
				hidden :config.type,
				handler: function(){
					 	this.handleSaveDoctor( 
					 		 config, 
					 		 currAddConfig, 
					 		 emrgAddConfig, 
					 		 premanentAddConfig,
					 		 this.doctorCurrentContactDetails ,
							 this.doctorPermanentContactDetails,
							 this.doctorEmergencyContactDetails,
							 this.personalDetails ,
							 this.emergencyDetails,
							 this.sameAsCurrentChk.getValue(),											 
							 this.sameAsCurrentChkEmerg.getValue(),
							 this.doctorQualificationDetails,
							 this.doctorConsultationDetails
					 	);
					}
					
				},
				{
					text:'Reset',
					cls:'x-btn-text-icon',
					icon:'images/delete.png',
					hidden :config.type,
					scope:this,
					handler: function(){
						this.resetBtnClicked();
//				  		 Ext.ux.event.Broadcast.publish('doctorReset');
//				  		 this.personalDetails.getPhotoPanel().reset( config );
				  		 this.sameAsCurrentChk.setValue(false);
				  		 this.sameAsCurrentChkEmerg.setValue(false);
					}
				}
//				{
//					text:'Cancel',
//					scope:this,
//					handler: function(){
//						if( config.mode == "edit"){
//							Ext.ux.event.Broadcast.publish('closeEditDoctor');
//						}else{
//							this.createDoctorformPnl.ownerCt.hide();
//							
//							this.doctorCurrentContactDetails.getPanel().getForm().reset();
//							this.doctorPermanentContactDetails.getPanel().getForm().reset();
//							this.doctorEmergencyContactDetails.getPanel().getForm().reset();
//							this.doctorQualificationDetails.getPanel().getForm().reset();
//							this.emergencyDetails.getPanel().getForm().reset();
//							this.personalDetails.getForm().reset();
//							this.sameAsCurrentChk.setValue(false);
//					  		this.sameAsCurrentChkEmerg.setValue(false);
//							this.createDoctorTabPnl.setActiveTab(0);
//						}
//					}
//				}
			]
	     });
	     
		Ext.ux.event.Broadcast.subscribe('doctorReset',function(){
			this.sameAsCurrentChkEmerg.reset();
			this.sameAsCurrentChk.reset();
		},this,null,mainThis.createDoctorformPnl);
		
		// for monitoring the personal details panel 
		this.personalDetails.getPanel().on("clientvalidation", function(thisForm, isValid) {
			if (isValid && this.doctorCurrentContactDetails.getCountryCbx().isValid()){
				this.createDoctorformPnl.buttons[0].enable();
			} else {
				this.createDoctorformPnl.buttons[0].disable();
			}
		}, this);
		
	     Ext.ux.event.Broadcast.subscribe('setDoctorTabActiveIndex',function(){
			mainThis.createDoctorTabPnl.setActiveTab(0);
		 },this,null,mainThis.createDoctorformPnl);
		
	},
	getPanel:function(){
		return this.createDoctorformPnl;
	},
	handleSaveDoctor:function(
		 config, 
 		 currAddConfig, 
 		 emrgAddConfig, 
 		 premanentAddConfig,
 		 doctorCurrentContactDetails ,
		 doctorPermanentContactDetails,
		 doctorEmergencyContactDetails,
		 personalDetails ,
		 emergencyDetails,
		 permanentChk,
		 emergencyChk,
		 doctorQualificationDetails,doctorConsultationDetails){
		var tmpThis = this;
		
		 	if( doctorCurrentContactDetails.getPanel().getForm().isValid() &&
 				doctorPermanentContactDetails.getPanel().getForm().isValid() &&
 				doctorEmergencyContactDetails.getPanel().getForm().isValid() &&
 				personalDetails.getPanel().getForm().isValid() &&
 				emergencyDetails.getPanel().getForm().isValid() &&
 				doctorQualificationDetails.getPanel().getForm().isValid()){
 				
 				var personalDetailsValues = personalDetails.getPanel().getForm().getValues();
				var emergencyContactValues = emergencyDetails.getData();
				var currentContactValues = doctorCurrentContactDetails.getData();
				var qualificationDetailsValues = doctorQualificationDetails.getData();
				var consultationDetails = doctorConsultationDetails.getData();
				var doctorEmergencyContactValues;
				var permanentContactValues;
					
				if(permanentChk==true){
					permanentContactValues = currentContactValues;
				}else{
					permanentContactValues = doctorPermanentContactDetails.getData();
				}
				
				if(emergencyChk==true){
					doctorEmergencyContactValues = currentContactValues;
				}else{
					doctorEmergencyContactValues = doctorEmergencyContactDetails.getData();
				}
					 
			    var doctorAge;
			 	var doctorJoiningDate = getStringAsWtcDateFormat (personalDetailsValues['personaldetailsJoiningdate']);
			 	var doctorLeavingDate = getStringAsWtcDateFormat (personalDetailsValues['personaldetailsLeavingDate']);
			 	var doctorDepJoiningDate = getStringAsWtcDateFormat (personalDetailsValues['doctorJoiningdate']);
			 	var doctorDepLeavingDate = getStringAsWtcDateFormat (personalDetailsValues['doctorleavingDate']);
			 	
			    if( getStringAsWtcDateFormat (personalDetailsValues['personaldetailsDateOfBirth']) != null){
			    	doctorAge = calculateDOB(getStringAsWtcDateFormat (personalDetailsValues['personaldetailsDateOfBirth']));
			    }
			 	
			    if( getStringAsWtcDateFormat (personalDetailsValues['personaldetailsDateOfBirth']) != null && doctorAge[0] < parseInt(configs.doctorsAge)){
			   		 alertError(msg.invalidDoctorAge); 
			    }else if(personalDetailsValues['personaldetailsFirstname']!='' && personalDetailsValues['doctorSpeciality']!='' && personalDetailsValues['doctorDepartmentname']!='') {
				 	 
				 	 var doctorDetails = {
					  	 religion:{code:personalDetailsValues['personaldetailsreligion'],description:''},
						 saluation:{code:personalDetailsValues['personaldetailsTitle'],description:''},
						 gender:{code:personalDetailsValues['personaldetailsGender'],description:''},
						 bloodGroup:{code:personalDetailsValues['personaldetailsBloodgroup'],description:''},
						 idProof:{code:personalDetailsValues['personaldetailsIdproof'],description:''},
						 marital:{code:personalDetailsValues['personaldetailsMeritialStatus'],description:''},
						 nationality:{code:personalDetailsValues['personaldetailsnationality'],description:''},
						 firstName:personalDetailsValues['personaldetailsFirstname'],
						 middleName:personalDetailsValues['personaldetailsMiddleName'],
						 lastName:personalDetailsValues['personaldetailsLastname'],
						 active:true,
						 joiningDt:doctorJoiningDate,
						 leavingDt:doctorLeavingDate,
						 dob:getStringAsWtcDateFormat (personalDetailsValues['personaldetailsDateOfBirth']),
//						 height:personalDetailsValues['personaldetailsHeight'],
//						 weight:personalDetailsValues['personaldetailsWeight'],
						 fatherHusbandName:personalDetailsValues['personaldetailsfatherhusbandname'],
						 idNumber:personalDetailsValues['personaldetailsIdno'],
						 idValidUpto:getStringAsWtcDateFormat (personalDetailsValues['personaldetailsValidupto']),
						 bloodDonorId:personalDetailsValues['personaldetailsBloodGroupId'],
						 qualification:qualificationDetailsValues['qualification'],
						 workExperience:qualificationDetailsValues['experience'],
						 referredBy:qualificationDetailsValues['referredBy'],
						 knownLanguages:qualificationDetailsValues['languages'],
						 imagePopertyBM :{fileName : personalDetails.getPhotoPanel().getImageName()},
						 userId:getAuthorizedUserInfo().userName
			 		}
				 		
			     if(personalDetailsValues['personaldetailsparmanentdoctorChk']=='on') {
				 	 doctorDetails.permanent = true;
				 }else {
					 doctorDetails.permanent = false;
				 }
					 
				 var doctorSpecialty = {
				     especialtyCode:{code:personalDetailsValues['doctorSpeciality'],description:''},
					 departmentCode:{code:personalDetailsValues['doctorDepartmentname'],description:''},
					 active:true,
//					 joiningDt:doctorDepJoiningDate,
//					 leavingDt:doctorDepLeavingDate,
					 roomCode:{code:personalDetailsValues['doctorRoomno'],description:''}
				 };
				 if( Ext.isEmpty(consultationDetails['doctorConsultationcharges'])) {
					 doctorSpecialty.consultationCharge = null;
				 }else {
					 doctorSpecialty.consultationCharge = parseFloat(consultationDetails['doctorConsultationcharges']);
				 }
				 
				 if( Ext.isEmpty(consultationDetails['followupfee'])) {
					 doctorSpecialty.followupCharge = null;
				 }else {
					 doctorSpecialty.followupCharge = parseFloat(consultationDetails['followupfee']);
				 }
				 
				 if( Ext.isEmpty(consultationDetails['followupDays'])) {
					 doctorSpecialty.followupDay = null;
				 }else {
					 doctorSpecialty.followupDay = parseInt(consultationDetails['followupDays']);
				 }
				 
				 if( Ext.isEmpty(consultationDetails['followupVisits'])) {
					 doctorSpecialty.followupVisit = null;
				 }else {
					 doctorSpecialty.followupVisit = parseInt(consultationDetails['followupVisits']);
				 }
					
				 var currentContactDetails ={
				   		firstName: personalDetailsValues['personaldetailsFirstname'],
				        salutation:{code:personalDetailsValues['personaldetailsTitle'],description:personalDetailsValues['']},
						middleName: personalDetailsValues['personaldetailsMiddleName'],
						lastName: personalDetailsValues['personaldetailsLastname'],
				   		houseNumber: currentContactValues['housename'],
						street: currentContactValues['placestreet'],
						city: currentContactValues['citydistrict'],
						pincode: currentContactValues['pincode'],
						phoneNumber: currentContactValues['phonenumber'],
						mobileNumber: currentContactValues['mobilenumber'],
						faxNumber: currentContactValues['faxnumber'],
						email: currentContactValues['emailaddress'],
						stayDuration: currentContactValues['durationofstay'],
						country:{code:currentContactValues['country'],description:currentContactValues['']},
						state:{code:currentContactValues['state'],description:currentContactValues['']},
						createdBy:getAuthorizedUserInfo().userName
				 };
		
				 var emergencyContactDetails ={
				        firstName: emergencyContactValues['firstname'],
				        salutation:{code:emergencyContactValues['title'],description:emergencyContactValues['']},
						middleName: emergencyContactValues['middlename'],
						lastName: emergencyContactValues['lastname'],
				   		gender:{code:emergencyContactValues['gender'],description:emergencyContactValues['']},
				   		phoneNumber: emergencyContactValues['phonenumber'],
						mobileNumber: emergencyContactValues['mobilenumber'],
						faxNumber: emergencyContactValues['faxnumber'],
						email: emergencyContactValues['emailaddress'],
						relationCode:{code:emergencyContactValues['relationship'],description:emergencyContactValues['']},
				   		houseNumber: doctorEmergencyContactValues['housename'],
						street: doctorEmergencyContactValues['placestreet'],
						city: doctorEmergencyContactValues['citydistrict'],
						pincode: doctorEmergencyContactValues['pincode'],
						stayDuration: doctorEmergencyContactValues['durationofstay'],
						country:{code:doctorEmergencyContactValues['country'],description:doctorEmergencyContactValues['']},
						state:{code:doctorEmergencyContactValues['state'],description:doctorEmergencyContactValues['']},
						createdBy:getAuthorizedUserInfo().userName
				 };
			 	
				 var ParmanentContactDetails ={
				   		firstName: personalDetailsValues['personaldetailsFirstname'],
				        salutation:{code:personalDetailsValues['personaldetailsTitle'],description:personalDetailsValues['']},
						middleName: personalDetailsValues['personaldetailsMiddleName'],
						lastName: personalDetailsValues['personaldetailsLastname'],
				   		houseNumber: permanentContactValues['housename'],
						street: permanentContactValues['placestreet'],
						city: permanentContactValues['citydistrict'],
						pincode: permanentContactValues['pincode'],
						phoneNumber: permanentContactValues['phonenumber'],
						mobileNumber: permanentContactValues['mobilenumber'],
						faxNumber: permanentContactValues['faxnumber'],
						email: permanentContactValues['emailaddress'],
						stayDuration: permanentContactValues['durationofstay'],
						country:{code:permanentContactValues['country'],description:permanentContactValues['']},
						state:{code:permanentContactValues['state'],description:permanentContactValues['']},
						createdBy:getAuthorizedUserInfo().userName
				 };
					 
				 if(!Ext.isEmpty(config) && 
				 	!Ext.isEmpty(currAddConfig)&& 
				 	!Ext.isEmpty(premanentAddConfig) && 
				 	(config.mode == 'edit')) {
				    	currentContactDetails.contactDetailsCode = currAddConfig.selectedContactDetailsCode;
				    	currentContactDetails.personelId = currAddConfig.selectedPersonelId;
				    	ParmanentContactDetails.contactDetailsCode = premanentAddConfig.selectedContactDetailsCode;
				    	ParmanentContactDetails.personelId = premanentAddConfig.selectedPersonelId;
			    	
			    	if(!Ext.isEmpty(emergencyContactValues['firstname'])) {
			     		emergencyContactDetails.contactDetailsCode =emrgAddConfig.selectedContactDetailsCode;
			     		emergencyContactDetails.personelId = emrgAddConfig.selectedPersonelId;
			    	}
				    	
			    	DoctorManagementController.modifyDoctorDetails(	
			    		doctorDetails,
						doctorSpecialty,
		      			currentContactDetails,
		      			ParmanentContactDetails,
		      			emergencyContactDetails,parseInt(config.selectedDoctorId),
		      			{
							callback:function() {
									Ext.ux.event.Broadcast.publish('reloadStore');
									doctorsStore.reload();
									 layout.getCenterRegionTabPanel().remove( tmpThis.getPanel().ownerCt );
//					 				Ext.ux.event.Broadcast.publish('closeEditDoctor');
							 } 
				  		});
		 			}else {
						 DoctorManagementController.saveDoctorDetails(  
						 		doctorDetails,
								doctorSpecialty,
			              		currentContactDetails,
			              		ParmanentContactDetails,
			              		emergencyContactDetails,
			              		{
									callback:function() {
								
										if( !Ext.isEmpty( config ) && config.isPopup == true ){
											Ext.ux.event.Broadcast.publish('reloadStore');
											doctorsStore.reload();
											 layout.getCenterRegionTabPanel().remove( tmpThis.getPanel().ownerCt);
										}else{
											Ext.ux.event.Broadcast.publish('doctorReset');
										}
				                    } 
			               		});
							}	                   
				   }else if(personalDetailsValues['personaldetailsFirstname']=='' && personalDetailsValues['doctorSpeciality']!='' && personalDetailsValues['doctorDepartmentname']!='') {
				       alertError(msg.msgsavedoctorfirstname);
				   }else if(personalDetailsValues['personaldetailsFirstname']!='' && personalDetailsValues['doctorSpeciality']=='' && personalDetailsValues['doctorDepartmentname']!='') {
				       alertError(msg.msgsavedoctorespeciality);
				    }else if(personalDetailsValues['personaldetailsFirstname']!='' && personalDetailsValues['doctorSpeciality']!='' && personalDetailsValues['doctorDepartmentname']=='') {
				       alertError(msg.msgsavedoctordepartment);
				    }else {
				       alertError(msg.msgsavedoctor);
				    }
			 	}else{
			 		alertError('Please correct the fields with red mark');
			 	}
 			},
 			
 			resetBtnClicked : function(){
 				this.doctorQualificationDetails.resetQualificationPanel();
 				this.personalDetails.resetPersonalDetailsPanel();
 				this.doctorCurrentContactDetails.resetContactDetails();
 				this.doctorPermanentContactDetails.resetContactDetails();
 				this.emergencyDetails.resetEmergencyContactDetailPanel();
 				this.doctorEmergencyContactDetails.resetContactDetails();
 				this.personalDetails.getPhotoPanel().reset( this.config );
 				this.doctorConsultationDetails.reSetData();
 			}
})