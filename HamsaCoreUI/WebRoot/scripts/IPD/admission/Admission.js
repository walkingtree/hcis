Ext.namespace("IPD.admission");

IPD.admission.Admission = Ext.extend(Object, {
	constructor : function(config) {
	
		if( Ext.isEmpty(config)){
			config = { }
		}
		
		var mainThis = this;

		this.currPatientId = null;
		
		this.doctorOrdersGrid = new IPD.admission.DoctorOrderGrid();
		
		this.bedsGrid = new IPD.admission.BedDetailGrid();
		
		this.insuranceGrid = new IPD.admission.InsuranceDetailGrid(true);

		this.servicesGrid = new IPD.admission.ServiceDetailGrid();
		
		this.billingPanel = new IPD.admission.IPBilling(config);
		
		this.patientVitalDetailPanel = new Ext.Panel({
			frame : true,
			height : '100%'
		});
		
		this.goButton = new Ext.Button({
				scope :this,
				disabled:true,
				cls :'x-btn-text-icon',
				text :'GO',
				icon :'images/find.png',
				handler : function() {
					this.addmissionTabPnl.setActiveTab(0);
					mainThis.goButtonClicked();
				}
		});
		
		this.generateBillBtn = new Ext.Button({
			iconCls:'bill-icon',
			tooltip:msg.generateBill,
			text:msg.generateBill,
			scope:this,
			handler:function(){
				this.generateBillBtnHandler();
			}
		});
		
		this.searchCriteriaForm = new Ext.form.FormPanel({
			layout :'column',
			labelAlign :'left',
			bodyStyle: 'padding: 5px 5px 0 0;',
			monitorValid:true,
			items : [{
						layout :'form',
						labelWidth:35,
						items : [ {
							xtype :'numberfield',
							name :'arnNo',
							fieldLabel :'ARN',
							anchor :'98%',
							allowBlank:false
						} ]
					}, {
						layout :'form',
						items : [ this.goButton]
					},{
						layout :'form',
						style:'padding-left:10px;',
						items : [ this.generateBillBtn ]
					}
				]
		});
		
		this.searchCriteriaForm.on("clientvalidation",
				function(thisForm, isValid) {
					if (isValid) {
						this.goButton.enable();
						this.generateBillBtn.enable();
					} else {
						this.goButton.disable();
						this.generateBillBtn.disable();
					}
		}, this);
		
		
		
		this.patientId = new Ext.form.NumberField({
			name :'patientId',
			fieldLabel :msg.patientid,
			anchor :'98%',
			disabled : true
		});

		this.patientName = new Ext.form.TextField({
			fieldLabel :'Patient name',
			name :'patientName',
			anchor :'98%',
			disabled : true		
		});
				
		this.bedNumber = new Ext.form.TextField({
			fieldLabel :'Bed number',
			name :'bedNumber',
			anchor :'98%',
			disabled : true		
		});

		this.outstandingAmt = new Ext.form.NumberField({
			name :'outstandingAmt',
			fieldLabel :'Outstanding amount',
			anchor :'98%',
			disabled : true		
		});

		this.basicInfoPanel = new Ext.Panel({
			layout :'column',
			border : true,
			frame:true,
			autoHeight:true,
			weight:'100%',
			title : "Basic details",
			bodyStyle: 'padding: 5px 5px 0 0;',
			hidden:true,
			defaults : {
				labelWidth :90,
				columnWidth :.25
			},
			items : [
					{
						layout :'form',
						items : [this.patientId]
					},
					{
						layout :'form',
						columnWidth :.5,
						items : [this.patientName]
					},
					{
						layout :'form',
						items : [ this.bedNumber]
					},
					{
						layout :'form',
						items : [ this.outstandingAmt ]
					}
				]
		});
		
//		this.doctorOrderGridPanel = this.doctorOrdersGrid.getPanel()
		
		this.addmissionTabPnl = new Ext.TabPanel({
			width:'100%',
			activeTab:0,
			border : true,
			deferredRender:false,
			layoutOnTabChange :true,
			hideMode:'offsets',
			frame : true,
			hidden:true,
			items:[
		       {
		    	   title : vitalMsg.patientVital,
		    	   autoScroll : true,
		    	   autoHeight:true,
		    	   items : this.patientVitalDetailPanel
	    	   },
//		       {
//		    	   title : "Vital graph",
//		    	   autoScroll : true,
//		    	   autoHeight:true,
//		    	   items : this.patientVitalGraphPanel
//	    	   },
	    	   {
					title:'Doctor Order',
					autoScroll:true,
					autoHeight:true,
					items:[{xtype: 'panel',
							items:this.doctorOrdersGrid.getPanel()
							}]
				},{
					title:'Bed Detail',
					autoScroll:true,
					autoHeight:true,
					items:[{xtype: 'panel',
							items:this.bedsGrid.getPanel()
							}]
				},{
					title:'Services Detail',
					autoScroll:true,
					autoHeight:true,
					items:[{xtype: 'panel',
							items:this.servicesGrid.getPanel()
					}]
				},{
					title:'Insurance Detail',
					autoScroll:true,
					autoHeight:true,
					items:[{xtype: 'panel',
							items:this.insuranceGrid.getPanel()
					}]
				},{
					title:'Billing Detail',
					autoScroll:true,
					autoHeight:true,
					items:[{xtype: 'panel',
							items:this.billingPanel.getPanel()
					}]
				},{
					title:'Patient Detail',
					autoScroll:true,
					autoHeight:true,
					frame:true,
					tabIndicator : "PD"
				}
			],
			listeners: {
				tabchange : function(tabbedPanel, activePanel){
					var tabInd = activePanel.initialConfig.tabIndicator;
					if(tabInd !== "PD"){
						return;
					}

					var patientId = this.currPatientId;
				
					if(!Ext.isEmpty(this.patientRegistrationPanel)){
						Ext.destroy(this.patientRegistrationPanel);
					}
					
			        PatientManager.getPatientInfoDetailBM(
			        	parseInt(patientId), 
			        	{
					       	callback : function(patientInfoDetailBM){
					       		this.patientDetailsCallBack(patientInfoDetailBM, activePanel)
					       	},
					       	callbackScope: this
				      	}
			        );
				}, 
				scope : this
			}
		});

		
		this.admissionPanel = new Ext.Panel({
				frame : true,
				width : '100%',
				autoHeight : true,
				border : false,
				items: [
				this.searchCriteriaForm,
				this.basicInfoPanel,
				this.addmissionTabPnl]
		});
		if( config.fromBilling ){
			//If this panel is called form billing remove Doctor Order detail panel
			this.addmissionTabPnl.remove( this.addmissionTabPnl.items.items[0] );
			this.addmissionTabPnl.remove( this.addmissionTabPnl.items.items[0] );
			this.addmissionTabPnl.doLayout();
		}		
		
	},
	
	getPanel : function() {
		return this.admissionPanel;
	},
	
	goButtonClicked : function (){
	
		this.currPatientId = null;
		var mainThis = this;
	
		var values = this.searchCriteriaForm.getForm().getValues();
		var arnNo = values['arnNo'];
		
		VitalManager.getVitalFields(
			{
				callback : function(vitalFieldBMList){
				if( !Ext.isEmpty( this.patientVitalDetailPanel )){
					this.patientVitalDetailPanel.removeAll();
				}
				var patientVitalDetailPanel = new OPD.vital.configure.ConfigureVital({vitalFieldBMList : vitalFieldBMList});
				patientVitalDetailPanel.activeTab = 0;
				this.patientVitalDetailPanel.add( patientVitalDetailPanel );
				this.patientVitalDetailPanel.doLayout();

				AdmissionOrder.getPatientBasicDetails(
					parseInt(arnNo,10),
						{
							callback : function(patientBasicDetailBM){
							mainThis.patientBasicDetailsCallBack(patientBasicDetailBM, arnNo);
							var patientInfo = {
								patientId : mainThis.currPatientId,
								referenceNumber : arnNo,
								referenceType : "IPD"
							};
							patientVitalDetailPanel.setPatientInfo( patientInfo );
//							patientVitalGraphPanel.setPatientInfo( patientInfo );
						},
						scope: this
					}
				);
			},
			scope : this
		});
	},
	
	patientBasicDetailsCallBack : function(patientBasicDetailBM, arnNo){

		if(null === patientBasicDetailBM){
			Ext.Msg.show({
				msg: "Admission number not exists",
				icon : Ext.MessageBox.INFO,
				buttons: Ext.Msg.OK
			});
	
		} else {
			
			this.basicInfoPanel.show();
			this.basicInfoPanel.doLayout();
			
			this.addmissionTabPnl.show();
			
			this.currPatientId = patientBasicDetailBM.patientId;
			
			// Basic details
			this.loadBasicDetailsPanel(patientBasicDetailBM);

			// Bed detail.
			if (this.bedsGrid.dataStore.getTotalCount() > 0) {
				this.bedsGrid.dataStore.removeAll();
			}
			this.bedsGrid.dataStore.load({
				params : {start :0,limit :9999},
				arg : [  null, null, null, null, null, null, arnNo, null, null, null, null ]
			});	
	
			// Doctor orders detail.
			if (this.doctorOrdersGrid.dataStore.getTotalCount() > 0) {
				this.doctorOrdersGrid.dataStore.removeAll();
			}
			this.doctorOrdersGrid.dataStore.load({
				params : {start :0,limit :9999},
				arg : [ arnNo, null, null, null, null, null, null ]
			});	
	
			// Services detail.
			if (this.servicesGrid.dataStore.getTotalCount() > 0) {
				this.servicesGrid.dataStore.removeAll();
			}
			this.servicesGrid.dataStore.load({
				params : {start :0,limit :9999},
				arg : [ null, null, null, null, null, null, null, null, 'IPD', arnNo, null ]
			});	
			
			// Insurance detail.
			if (this.insuranceGrid.dataStore.getTotalCount() > 0) {
				this.insuranceGrid.dataStore.removeAll();
			}
			this.insuranceGrid.dataStore.load({
				params : {start :0,limit :9999},
				arg : [ null, arnNo, null, null, null, null, null, null, null, null, null, null, null, null ]
			});	
			
			this.billingPanel.loadGrids( arnNo );
		}
	},
	
	loadBasicDetailsPanel : function(patientBasicDetailBM){
		this.patientId.setValue(patientBasicDetailBM.patientId);
		this.patientName.setValue(patientBasicDetailBM.patientName);
		this.bedNumber.setValue(patientBasicDetailBM.bedNumber);
		this.outstandingAmt.setValue(patientBasicDetailBM.outstandingAmt);
	},
	generateBillBtnHandler: function(){
		var values = this.searchCriteriaForm.getForm().getValues();
		var arnNo = values['arnNo'];
		AdmissionOrder.getBusinessPartnerId( arnNo, {
			callback: function( accountId ){
				BillManager.runBill( accountId,
					 				 configs.referenceTypeForIPD,
					 				 arnNo,{
			 		callback:function( billObjectBM ){
//									 	 var messagesText = "Bill has been created successfully,<br> biilAmount ="+billObjectBM.billAmount
//									 	+" <br> bill due date="+Ext.util.Format.date(billObjectBM.billDueDate,"d/m/Y")+ "<br> bill Number ="+billObjectBM.billNumber;
//											Ext.Msg.show({
//											   msg: messagesText,
//											   buttons: Ext.Msg.OK,
//											   fn: function(){
//											   },
//											   icon: Ext.MessageBox.INFO
//											});
			 			
			 			var billNbr = {billNbr: billObjectBM.billNumber};
			 			CoreReportManager.generateReport( 
			 				"OPDPatientBill" , 
						    billNbr, 
						    function(reportURL){
					       		window.open(getBaseURL() + reportURL);
					        });
					},
					scope: this
									 			 	
		});
			},
			scope: this
		});
			
	},
	patientDetailsCallBack : function(patientInfoDetailBM, activePanel){
	   var patientBM = patientInfoDetailBM;
	   
	   var allergiesCount = patientBM.patientAllergiesBMList.length;
	   var isPatientHaveAnyAllergies =  (allergiesCount == 0) ? false : true;

	   var immunizationsCount = patientBM.patientImmunizationsBMList.length;
	   var isPatientHaveAnyImmunizations =  (immunizationsCount == 0) ? false : true;

	   var config = {
         mode: 'edit',
         
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

						
		this.patientRegistrationPanel = 
			new OPD.registration.PatientRegistrationPanel(
															 true,
															 config,
															 currAddConfig,
															 emrgPrimaryAddConfig,
															 emrgSecondaryAddConfig,
															 premanentAddConfig,
															 emrgPrimaryDetailsConfig,
															 emrgSecondaryDetailsConfig
														);
        if(!Ext.isEmpty(activePanel.items))	{
        	activePanel.removeAll();
        }
		activePanel.add(this.patientRegistrationPanel.getTabPanel());
		activePanel.doLayout();
	}
	
	
});
