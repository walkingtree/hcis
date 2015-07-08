Ext.namespace("OPD.registration");

OPD.registration.PatientPersonalDetails = Ext.extend(Object,{

	constructor:function(config ){
		
		Ext.QuickTips.init();
	 	if( Ext.isEmpty(config) ){
	 		config = { }
	 	}

	 	this.titleCbx = new Ext.form.ComboBox({
	 		hiddenName:'title',
			fieldLabel:msg.title,
			store:loadTitleCbx(),
			mode:'local',
			displayField:'description',
			valueField:'code',
		    triggerAction: 'all',
		    value: config.title,
		    forceSelection:true,
		    anchor:'99%'
	 	});


	 	this.registrationChargeValueLbl = new Ext.form.Label({
	 		style:'margin-left:10px;',
			text:configs.registrationFee,
			name:'registrationFee'
		});

		this.registrationDate = new Ext.form.WTCDateTimeField({
			name:'registrationDate',
			fieldLabel:msg.regdate,
			allowBlank:false,
			required:true,
			disabled : config.mode == 'edit' ? true : false,
			anchor:'90%',
			minValue : config.mode == 'edit' ? null : new Date().clearTime( true ),
			value : config.mode == 'edit' ? config.registrationDate: new Date()	
		});	 

	 	this.registrationDetailsPnl= new Ext.Panel({
			layout :'form',
			frame :true,
	 		title:msg.registrationdetails,
			border :true,
			width :'100%',
			autoHeight :true,
			autoScroll :true,
			labelWidth :110,
	 		items:[
		 		{
		 			layout:'form',
		 			hidden:config.mode?false:true,
		 			items:[
		 			{
		 				xtype:'textfield',
						name:'registrationNumber',
						fieldLabel:msg.regnumber,
						disabled:true,
						anchor:'99%',
						value:config.registrationNumber,
						hidden:config.mode == 'edit' ? false: true	
		 			}]
		 		},
		 		this.registrationDate,
		 		//As there is no emergency concept right 
		 		//now thats why this field has been commented
		 		/*{
 					xtype:'combo',
					name:'registrationType',
					fieldLabel:msg.regtype,
					mode:'local',
					store:loadRegistrationTypeCbx(),
					displayField:'description',
					valueField:'code',
			        triggerAction: 'all',
			        anchor:'99%',
			        forceSelection:true,
			        value : config.registrationType
		 		}, */{
					xtype:'combo',
					name:'registrationStatus',
					fieldLabel:msg.regstatus,
					store:loadRegistrationStatusCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
			        triggerAction: 'all',
			        anchor:'99%',
			        forceSelection:true,
			        value : config.registrationStatus
		 		}, {
 					xtype:'combo',
					name:'patientRating',
					fieldLabel:msg.patientrating,
					store: loadPatientRatingCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
			        triggerAction: 'all',
			        anchor:'99%',
			        forceSelection:true,
			        value : config.patientRating
 				}, {
 					xtype:'combo',
					name:'patientCategory',
					fieldLabel:msg.patientcatogery,
					store:loadPatientCategoryCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
			        triggerAction: 'all',
			        anchor:'99%',
			        forceSelection:true,
			        value : config.patientCategory
		 		},{
		 			layout : 'column',
		 			items :[
			 			{
			 				columnWidth : .5,
							xtype:'label',
							style:'font-size:13px',
							text: msg.registrationfee + ": "
						},
			 			{
			 				columnWidth : .5,
							xtype:'label',
							style:'font-size:13px',
							text: getCurrencyIndicator() + this.registrationChargeValueLbl.text 
						}
						
					]
		 		}
	 		]
	 	
	 	});
		
	 	this.heightIndicatorCbx = new Ext.form.ComboBox({
			hiddenName:'heightIndicator',
			store:loadHeightIndicatorStore(),
			mode:'local',
			displayField:'description',
			valueField:'code',
	        triggerAction: 'all',
	        anchor:'50%',
	        forceSelection:true,
	        value : config.heightIndicator
	 	});
	 	
	 	this.weightIndicatorCbx = new Ext.form.ComboBox({
			hiddenName:'weightIndicator',
			store:loadWeightIndicatorStore(),
			mode:'local',
			displayField:'description',
			valueField:'code',
	        triggerAction: 'all',
	        anchor:'50%',
	        forceSelection:true,
	        value : config.weightIndicator
	 	});
	 	
	 	this.basicInfoPnl = new Ext.Panel({
			layout :'form',
			frame :true,
			title :"Basic information",
			border :true,
			width :'100%',
			height:392,
			autoScroll :true,
			bodyStyle :'padding:2px 2px 0',
			labelWidth :100,
	 		items:[
	 			this.titleCbx,
	 			{
 					xtype:'textfield',
					name:'firstName',
					fieldLabel:msg.firstname,
					allowBlank:false,
					required:true,
					anchor:'99%',
					value:config.firstName
 				},{
 					xtype:'textfield',
					name:'middleName',
					fieldLabel:msg.middlename,
					anchor:'99%',
					value:config.middleName
 				},{
 					xtype:'textfield',
					name:'lastName',
					fieldLabel:msg.lastname,
					anchor:'99%',
					value:config.lastName
 				},{
 					xtype:'wtcdatefield',
					name:'dateOfBirth',
					fieldLabel:msg.dateofbirth,
					anchor:'99%',
					maxValue:new Date(),
					value:config.dateOfBirth
				},{
 					xtype:'combo',
					name:'gender',
					fieldLabel:msg.sex,
					store:loadGenderCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
			        triggerAction: 'all',
			        anchor:'99%',
			        forceSelection:true,
			        value:config.gender
 				},{
 					layout:'column',
 					defaults:{ columnWidth:.3 },
 					items:[
 						{
 							layout:'form',
 							columnWidth:.7,
 							items:[{
							xtype:'textfield',
							name:'height',
							fieldLabel:msg.height,
							anchor:'99%',
							value:config.height
						}]
 						},
						this.heightIndicatorCbx
 					]
 				},{
 					layout:'column',
 					defaults:{ columnWidth:.3 },
 					items:[
 						{
 							layout:'form',
 							columnWidth:.7,
 							items:[{
								xtype:'textfield',
								name:'weight',
								fieldLabel:msg.weight,
								anchor:'99%',
								value:config.weight
							}]
 						},
						this.weightIndicatorCbx
 					]
 				},{
					xtype:'combo',
					name:'bloodGroup',
					fieldLabel:msg.bloodgroup,
					store:loadBloodGroupCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
				    triggerAction: 'all',
				    anchor:'99%',
				    forceSelection:true,
					value:config.bloodGroup
				},{
					xtype:'combo',
					name:'maritalStatus',
					fieldLabel:msg.maritalstatus,
					store:loadMaritalStatusCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
				    triggerAction: 'all',
				    anchor:'99%',
				    forceSelection:true,
					value:config.maritalStatus
				},{
					xtype:'textfield',
					name:'fatherHusband',
					fieldLabel:msg.fatherhusbandname,
					anchor:'99%',
					value:config.fatherHusband
				}
	 		]
	 	});
	 	
	 	this.additionalDetailsPnl = new Ext.Panel({
			layout :'form',
			frame :true,
			title : msg.additionaldetails,
			border :true,
			width :'100%',
			height:392,
			autoScroll :true,
			bodyStyle :'padding:2px 2px 0',
			labelWidth :120,
			items:[
				 {
					xtype:'combo',
					name:'religion',
					store:loadReligionCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
					triggerAction: 'all',
					fieldLabel:msg.religion,
					anchor:'99%',
					forceSelection:true,
					value:config.religion
				},{
					xtype:'combo',
					name:'motherTongue',
					fieldLabel:msg.mothertongue,
					anchor:'99%',
					store:loadMotherTongueCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
				    triggerAction: 'all',
				    forceSelection:true,
				    value:config.motherTongue
				}, {
					xtype:'textfield',
					name:'bloodDonorId',
					fieldLabel:msg.blooddonorid,
					anchor:'99%',
					value:config.bloodDonorId
				}, {
					xtype:'combo',
					name:'idProof',
					fieldLabel:msg.idproof,
					store:loadIdProofCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
				    triggerAction: 'all',
				    anchor:'99%',
				    forceSelection:true,
				    value:config.idProof
				}, {
					xtype:'textfield',
					name:'idNumber',
					fieldLabel:msg.idno,
					anchor:'99%',
					value:config.idNumber
				}, {
					xtype:'wtcdatefield',
					name:'idValidUpto',
					fieldLabel:msg.validupto,
					anchor:'99%',
					minValue:new Date(),
					value:config.idValidUpto
				}, {
					xtype:'combo',
					name:'nationality',
					fieldLabel:msg.nationality,
					store:loadNationalityCbx(),
					mode:'local',
					displayField:'description',
					valueField:'code',
				    triggerAction: 'all',
				    anchor:'99%',
				    forceSelection:true,
				    value:config.nationality
				}, {
					xtype:'textfield',
					name:'patientOccupation',
					fieldLabel:msg.patientoccupation,
					anchor:'99%',
					value:config.patientOccupation
				}, {
					xtype:'wtcamountfield',
					name:'monthlyIncome',
					fieldLabel:msg.monthlyincome+'('+getCurrencyIndicator()+')',
					anchor:'99%',
					value:config.monthlyIncome
				}
			]
		});
	
	 	config.showButton = true;//Make add button visible on photo uploader
		this.photoPanel = new  UploadPhotoPanel(config);
		this.uploadPhotoPanel = this.photoPanel.getPanel();
		this.uploadPhotoPanel.setHeight(200);
			
		 this.patientRegistrationPersonalDetailsPnl = new Ext.form.FormPanel({
		 	layout:'column',
	 		labelAlign:'left',
	 		height: '100%',
	 		monitorValid:true,
	 		defaults:{
	 			style:'margin:1px;'
			},	
			items :[
				{
					columnWidth:.33,
					items:[
							this.uploadPhotoPanel, 
							this.registrationDetailsPnl
						  ]
				},
				{
					layout:'column',
					columnWidth:.33,
					defaults:{
		 				style:'margin-bottom:2px;'
					},
					items:[
						this.basicInfoPnl
					]
				},
				{
					columnWidth:.33,
					items:this.additionalDetailsPnl
				}
			]
		});
		
		Ext.ux.event.Broadcast.subscribe('patientReset',function(){
			this.patientRegistrationPersonalDetailsPnl.getForm().reset();
			this.photoPanel.reset();
		},this, null, this.getPanel());
		
		if( config.mode == 'edit'){
			
		}
		
	},
	
	getPanel:function(){
		return this.patientRegistrationPersonalDetailsPnl;
	},
	
	getData:function(){
		var dataValues = this.patientRegistrationPersonalDetailsPnl.getForm().getValues();
		dataValues.registrationDate = this.registrationDate.getValue().format('d/m/Y g:i a');
		return dataValues;
	},
	
	getPhotoPanel : function(){
		return this.photoPanel;
	}
});