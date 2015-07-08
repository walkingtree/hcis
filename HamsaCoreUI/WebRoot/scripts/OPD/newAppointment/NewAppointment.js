/**
 * @author Sandeep Kumar
 */
function viewPatientDetails( parameters ){
	var patientRegistrationPnl = 
		new OPD.registration.PatientRegistrationPanel(
			parameters.isPopUp,
			parameters.config, 
			parameters.currAddConfig, 
			parameters.emrgPrimaryAddConfig,
			parameters.emrgSecondaryAddConfig,
			parameters.premanentAddConfig,
			parameters.emrgPrimaryDetailsConfig,
			parameters.emrgSecondaryDetailsConfig);

	patientRegistrationPnl.buttonsPanel.hide();
	
 	var panel = layout.getCenterRegionTabPanel().add({
		frame:true,
		title : parameters.title, 
		closable : true,
		height : 420,
		items : [ patientRegistrationPnl.getPanel() ]
	});
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
};

newAppointmentPanel = function(/* Object */config) {
	
	Ext.apply(this,config);

	this.wtcCalendar = new wtccomponents.wtccalendar.Calendar();
	var calendar = this.wtcCalendar;
	
	this.mainPanel = new Ext.Panel({
		 border:false,
		 layout:'column',
		 height:'100%',
		 width:'97%',
		 style:'padding:0px',
		 defaults:{
		 	columnWidth:.5,
		 	style:'margin-left:2px'
		 }
	});
	
	this.createPanel = function(){
		Ext.QuickTips.init();
		if (!Ext.isEmpty(this.mainPanel.items)) {
			this.mainPanel.removeAll();
		}
		this.issuReceiptBtn  = new Ext.Button({
			text:msg.issueReceiptBtn,
			scope:this,
			disabled:true,
			handler:function(){
				if(!Ext.isEmpty(this.isPopup) && this.isPopup == true){
					layout.getCenterRegionTabPanel().remove( panel, true );
					Ext.ux.event.Broadcast.publish('AppointmentAddWindowClose');
				}
				showReceiptWindow( this.receiptConfig );
			}
		});
	  	
		this.consultationChargesLbl = new Ext.form.Label({
			text: getCurrencyIndicator()+'. ' + configs.defaultAmountValue  ,
			width:150,
			name:'appointmentFee'
		});
		var mainThis = this;
		
		this.doctorConsultationCharge = 0.00;
		this.record = Ext.data.Record.create([
		  	{name: "code", type: "string"},
		  	{name: "description", type: "string"}
		]);
		
		this.doctorsForSpeacialityStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getDoctorsOfSpeaciality, true),
		    reader: new Ext.data.ListRangeReader({totalProperty:'totalSize'}, this.record),
		    remoteSort: true
		 });
		
		this.loadAppointmentNumbersStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(AppointmentManager.getAppointmentForFollowUps, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, this.record),
		    remoteSort: true
	  });
		
		
		
		this.viewPatinetDetailsBtn = new Ext.Button({
   			cls:'x-btn-icon',
   			icon:'images/zoom.png',
   			tooltip : msg.viewpatientdetails,
   			scope:this,
   			handler:function(){
				var form = this.appointmentsNewAppointmentsDetailsPnl.getForm().getValues();
				 PatientManager.getPatientInfoDetailBM(parseInt( form['patientId'] ), function( patientInfoBM ){
				 	if(!Ext.isEmpty( patientInfoBM ) ){
				 		var config = getPatientDetailsConfig( patientInfoBM );
				 		viewPatientDetails( config );
				 	}
				 
				 });
   			}
		});
		// initially we are disabling viewPatinetDetailsBtn button. once user enters patient id then we enable viewPatinetDetailsBtn button.
		this.viewPatinetDetailsBtn.disable();
		
		this.appointmentDate  = new Ext.form.WTCDateField({
		   	name:'appointmentDate',
		   	fieldLabel:msg.appointmentDate,
		   	allowBlank:false,
			required:true,
		   	minValue:new Date().clearTime(true),
		   	value : new Date(),
		   	disabled:true,
		   	anchor:'70%',
		   	listeners:{
		   		change: function(comp){
					this.formTimeTF.reset();
		   			this.formTimeTF.enable();
		   			this.getAssinedTimeArray();
		   			this.toTimeTF.reset();
	   				this.toTimeTF.disable();
		   			if(Ext.isEmpty(comp.getValue())){
		   				this.formTimeTF.reset();
		   				this.formTimeTF.disable();
		   				this.toTimeTF.reset();
		   				this.toTimeTF.disable();
		   				comp.focus();
		   			}
		   			
		   			if(Ext.isEmpty(this.primaryDoctorCbx.getValue())){
		   				Ext.Msg.show({
							msg: msg.selectdoctor,
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK,
							fn: function(){
								comp.reset();
								mainThis.formTimeTF.reset();
				   				mainThis.formTimeTF.disable();
				   				mainThis.toTimeTF.reset();
				   				mainThis.toTimeTF.disable();
				   				comp.disable();
								mainThis.primaryDoctorCbx.focus();
							}
						});
		   			}else{
		   				loadNewAppointmentCalendar( 
		   					calendar,
		   					comp.getValue(), 
		   					comp.getValue(),
		   					this.primaryDoctorCbx.getValue());
		   			}
		   		},
		   		scope:this
		   	}
		});
		this.tmpArr = new Array();
		this.formTimeTF = new Ext.form.ComboBox({
			name:'startTime',
	   		allowBlank:false,
			required:true,
		   	fieldLabel:msg.starttime,
		   	triggerAction:'all',
		   	increment:calendar.slotDuration,
		   	disabled:true,
		   	mode:'local',
		   	store:new Ext.data.ArrayStore({
		        fields: [
		            'myId'		           
		        ]
		    }),
		   	anchor:'98%',
		   	displayField:'myId',
		   	listeners:{
				select:function( comp,recod,index){
					this.toTimeTF.getStore().removeAll();
					this.toTimeTF.reset();
					this.toTimeTF.enable();
					var record = this.toTimeTF.getStore().recordType;
					for(i=index;i<comp.getStore().getCount()-1;i++ ){
						this.toTimeTF.getStore().add( new record({toTime :comp.getStore().getAt(i+1).data.myId}));	
					}
					if(Ext.isEmpty(this.appointmentDate.getValue())){
						Ext.Msg.show({
							msg: "Please specify appointment date..!",
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK,
							fn: function(){
								comp.reset();
								mainThis.appointmentDate.focus();
							}
						});
					}
		   		},
		   		blur : function( comp ){
		   			if( Ext.isEmpty( comp.getValue()) ){
		   				this.toTimeTF.reset();
		   				this.toTimeTF.disable();
		   				comp.focus();
		   			}
		   		},
		   		scope:this
		   	}
		});
		
		this.toTimeTF = new Ext.form.ComboBox({
			name:'endTime',
	   		allowBlank:false,
			required:true,
		   	fieldLabel:msg.endtime,
		   	anchor:'99%',
		   	triggerAction:'all',
		   	disabled:true,
		   	mode:'local',
		   	store:new Ext.data.ArrayStore({
		        fields: [
		            'toTime'		           
		        ]
		    }),
		   	anchor:'98%',
		   	displayField:'toTime',
		   	listeners:{
				select:function( comp,recod,index){
					if(Ext.isEmpty(this.formTimeTF.getValue())){
						Ext.Msg.show({
							msg: "Please select start time..!",
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK,
							fn: function(){
								comp.reset();
								mainThis.formTimeTF.focus();
							}
						});
						comp.disable(true);
					}
		   		},
		   		scope:this
		   	}
		});
		
		this.appointmentComboFieldSet = new Ext.Panel({border:false});
		
		this.primaryDoctorCbx = new Ext.form.ComboBox({
		   	fieldLabel: msg.primarydoctor,
		   	hiddenName:'primaryDoctor',
		   	disabled:true,
		   	store: this.doctorsForSpeacialityStore,
			mode:'local',
			displayField:'description',
			valueField:'code',
		    triggerAction: 'all',
		    allowBlank:false,
			required:true,
		    anchor:'98%',
		   	listeners:{
		   	   select : function(combo,record,index ){
		   	   	  this.getDoctorCalendar();
		   	   	   if(!Ext.isEmpty(this.appointmentDate.getValue())){
		   	   	   		this.appointmentDate.reset();
		   	   	   		this.formTimeTF.reset();
		   				this.toTimeTF.reset();
		   				this.formTimeTF.disable();
		   				this.toTimeTF.disable();
		   				
		   	   	   }
		   	   	this.formTimeTF.getStore().removeAll();
		   		this.formTimeTF.reset();
		   		this.toTimeTF.reset();
		   	   	this.tmpArr = this.getAssinedTimeArray();
//		   	   	this.formTimeTF.getStore().loadData(this.tmpArr);
		   	 	  this.appointmentDate.enable();
		   	   	  this.formTimeTF.enable();
			   	   AppointmentManagementController.getConsutationCharge( combo.getValue(),
				   	   function( consultationCharge ){
				   	   	if( consultationCharge != null ){
				   	   		mainThis.doctorConsultationCharge = consultationCharge;
			   	  	 		mainThis.consultationChargesLbl.setText(getCurrencyIndicator()+'. '+ consultationCharge);
				   	  	 }
			   	   });
			   	   
			   	this.appointmentComboFieldSet.removeAll();
				this.appointmentComboFieldSet.add({layout:'form',items:[{xtype:'combo',
																		name:'appointmentNumber',
																		fieldLabel: 'Appointment Number',
																		store: this.loadAppointmentNumbersStore,
																		mode:'local',
																		displayField:'description',
																		valueField:'code',
																		triggerAction: 'all',
																		anchor:'98%'}]});
				this.loadAppointmentNumbersStore.load({params:{start:0, limit:999}, arg:[parseInt(mainThis.patientIdNbrF.getValue()),parseInt(combo.getValue())]});
				this.loadAppointmentNumbersStore.on('load',function(){
					
					this.appointmentComboFieldSet.doLayout();
					this.appointmentsNewAppointmentsDetailsPnl.doLayout();
					if(this.loadAppointmentNumbersStore.getCount()!=0){
						this.appointmentComboFieldSet.items.items[0].items.items[0].reset();
						this.appointmentComboFieldSet.items.items[0].items.items[0].setValue(this.loadAppointmentNumbersStore.getAt(0).data.code);
						mainThis.appointmentTypeCbx.setValue( 'FOLLOW-UP' );
						if(!Ext.isEmpty(combo.getValue())){
							AppointmentManagementController.getConsutationChargeForFolloeUp( combo.getValue(),
									function( consultationCharge ){
								if( consultationCharge != null ){
									mainThis.doctorConsultationCharge = consultationCharge;
									mainThis.consultationChargesLbl.setText(getCurrencyIndicator()+'. '+ consultationCharge);
								}
							});
						}
					}else{
						this.appointmentComboFieldSet.removeAll();
						this.appointmentComboFieldSet.doLayout();
						this.appointmentsNewAppointmentsDetailsPnl.doLayout();
						mainThis.appointmentTypeCbx.setValue( 'PRIMARY' );
					}
				},this);
				
		   	   },
		   	   blur : function(comp){
		   	   		if(Ext.isEmpty(comp.getValue())){
		   				this.formTimeTF.reset();
		   				this.toTimeTF.reset();
		   				this.appointmentDate.reset();
		   				this.formTimeTF.disable();
		   				this.toTimeTF.disable();
		   				this.appointmentDate.disable();
		   				comp.focus();
		   	   		}
		   	   },
		   	   scope:this
		   	}
		});
		
		this.patientIdNbrF = new Ext.form.NumberField({
   			fieldLabel:msg.patientid,
   			name:'patientId',
   			allowBlank:false,
   			required:true,
   			anchor:'98%',
   			listeners:{
   				blur: function(comp){
   					var patientId = comp.getValue();
   					if( patientId != null && patientId != ""){
   						PatientManagementController.getPatientDetails( patientId,
						function( patientLiteBM ){
							if( patientLiteBM != null ){
								mainThis.viewPatinetDetailsBtn.enable();
								var config = {
									firstName: patientLiteBM.firstName,
									middleName: patientLiteBM.middleName,
									lastName: patientLiteBM.lastName
								};
								formPanel.getForm().setValues( config );
							}else{
								Ext.Msg.show({
									msg: msg.noPatientFound,
									icon : Ext.MessageBox.WARNING,
									buttons: Ext.Msg.OK,
									fn: function(btn) {
										var config = {
											patientId: '',
											firstName: '',
											middleName: '',
											lastName: ''
										}
										formPanel.getForm().setValues( config );
										mainThis.viewPatinetDetailsBtn.disable();
									}
								});
							}
						});
   					}else{
   						var config = {
							patientId: '',
							firstName: '',
							middleName: '',
							lastName: ''
						}
						formPanel.getForm().setValues( config );
						mainThis.viewPatinetDetailsBtn.disable();
   					}
   				}
   			}
		});
		
		this.referralTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel : msg.referralType,
			hiddenName : 'referralType',
			store : getReferralTypes(),
			anchor:'98%',
			listeners : {
				select : function ( comp , record ,index ){
					this.referralNameCbx.clearValue();
					this.referralNameCbx.enable();
					this.addReferralBtn.enable();
					this.loadReferralNameForType( comp , record ,index );
				},
			scope:this
			}
		});

		this.referralNameCbx = new wtccomponents.WTCComboBox({
			fieldLabel : msg.referralName,
			hiddenName : 'referredBy',
			store : loadReferralsCbx(),
			disabled:true,
			anchor:'98%'
		});
		
		this.addReferralBtn = new Ext.Button({
	        scope : this,
	        iconCls:'add_btn',
	        disabled : true,
	        tooltip : msg.addReferral,
	        handler : function(){
	        	this.addReferralBtnClicked();
	        }
			
		});

		this.appointmentTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel: msg.appointmentType,
			hiddenName: 'appointmentType',
			store:loadAppointmentTypeCbx(),
			required: true,
			allowBlank: false,
			anchor:'98%'
		});
		
		this.appointmentTypeCbx.on('select',function(combo,record,index){
				if(combo.getValue() == 'FOLLOW-UP'){
					
					if(!Ext.isEmpty(this.primaryDoctorCbx.getValue())){
						AppointmentManagementController.getConsutationChargeForFolloeUp( this.primaryDoctorCbx.getValue(),
								function( consultationCharge ){
							if( consultationCharge != null ){
								mainThis.doctorConsultationCharge = consultationCharge;
								mainThis.consultationChargesLbl.setText(getCurrencyIndicator()+'. '+ consultationCharge);
							}
						});
					}
					
					if(!Ext.isEmpty(this.primaryDoctorCbx.getValue()) && !Ext.isEmpty(this.patientIdNbrF.getValue())){
						
						this.appointmentComboFieldSet.removeAll();
						this.appointmentComboFieldSet.add({layout:'form',items:[{xtype:'combo',
							name:'appointmentNumber',
							fieldLabel: 'Appointment Number',
							store: mainThis.loadAppointmentNumbersStore,
							mode:'local',
							displayField:'description',
							valueField:'code',
							triggerAction: 'all',
							anchor:'98%'}]});
						this.loadAppointmentNumbersStore.load({params:{start:0, limit:999}, arg:[parseInt(mainThis.patientIdNbrF.getValue()),parseInt(mainThis.primaryDoctorCbx.getValue())]});
						this.appointmentComboFieldSet.doLayout();
						this.appointmentsNewAppointmentsDetailsPnl.doLayout();
						if(this.loadAppointmentNumbersStore.getCount()!=0){
							this.appointmentComboFieldSet.items.items[0].items.items[0].setValue(this.loadAppointmentNumbersStore.getAt(0).data.code);
						}else{
							this.appointmentComboFieldSet.removeAll();
							this.appointmentComboFieldSet.doLayout();
							this.appointmentsNewAppointmentsDetailsPnl.doLayout();
							combo.setValue('PRIMARY');
							if(!Ext.isEmpty(this.primaryDoctorCbx.getValue())){
								AppointmentManagementController.getConsutationCharge( this.primaryDoctorCbx.getValue(),
										function( consultationCharge ){
									if( consultationCharge != null ){
										mainThis.doctorConsultationCharge = consultationCharge;
										mainThis.consultationChargesLbl.setText(getCurrencyIndicator()+'. '+ consultationCharge);
									}
								});
							}
						}
					}else{
						combo.setValue('PRIMARY');
						Ext.Msg.alert('Alert','Select patient and Docotor');
					}
					
				}else{
					if(!Ext.isEmpty(this.primaryDoctorCbx.getValue())){
						AppointmentManagementController.getConsutationCharge( this.primaryDoctorCbx.getValue(),
								function( consultationCharge ){
							if( consultationCharge != null ){
								mainThis.doctorConsultationCharge = consultationCharge;
								mainThis.consultationChargesLbl.setText(getCurrencyIndicator()+'. '+ consultationCharge);
							}
						});
					}
					mainThis.appointmentComboFieldSet.removeAll();
					this.appointmentComboFieldSet.doLayout();
					this.appointmentsNewAppointmentsDetailsPnl.doLayout();
				}
		},this);
		
		this.bookingTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel: msg.bookingtype,
			hiddenName: 'bookingType',
			store:loadBookingTypesCbx(),
			required: true,
			allowBlank: false,
			anchor:'98%'
		});
		
		this.phoneNbrTxt = new Ext.form.NumberField({
			name:'Phoneno',
		   	fieldLabel:'Phone No',
		   	anchor:'99%',
		   	hidden : true
		});
		
		this.emailIdTxt = new Ext.form.TextField({
			name:'Email',
		   	fieldLabel:'Email',
		   	vtype : 'email',
		   	anchor:'99%',
		   	hidden : true
		});
		
		this.bookTypeFieldSet = new Ext.Panel({border:false});
		
		this.bookingTypeCbx.on('select',function(comp){
			if(comp.getValue()=='PHONE'){
				this.bookTypeFieldSet.removeAll();
				this.bookTypeFieldSet.add({layout:'form',items:[{xtype:'numberfield',name:'Phoneno',fieldLabel: 'Phone No',anchor:'98%'}]});
				this.bookTypeFieldSet.doLayout();
				this.appointmentsNewAppointmentsDetailsPnl.doLayout();
			}else if(comp.getValue()=='EMAIL'){
				this.bookTypeFieldSet.removeAll();
				this.bookTypeFieldSet .add({layout:'form',items:[{xtype:'textfield',name:'Email',fieldLabel: 'Email',vtype:'email',anchor:'98%'}]});
				this.bookTypeFieldSet.doLayout();
				this.appointmentsNewAppointmentsDetailsPnl.doLayout();
			}else{
				this.bookTypeFieldSet.removeAll();
				this.bookTypeFieldSet.doLayout();
				this.appointmentsNewAppointmentsDetailsPnl.doLayout();
			}
			
		},this);
		
		this.appointmentsNewAppointmentsDetailsPnl = new Ext.form.FormPanel({
			layout:'column',
			labelAlign:'left',
			monitorValid:true,
			frame:true,
			defaults:{
				columnWidth:1,
				labelWidth:125
			},
			items:[
				{
					layout:'column',
					items:[
						{
							layout:'form',
							columnWidth:.80,
							items:this.patientIdNbrF
						},
						{
							layout:'form',
							columnWidth:.07,
							items:[
								{
									xtype:'button',
						   			cls:'x-btn-icon',
						   			icon:'images/find.png',
						   			tooltip : msg.findpatient,
						   			scope:this,
						   			style:'margin-bottom:7px',
						   			handler:function(){
						   				getSearchPatientPanel(
						   					this.appointmentsNewAppointmentsDetailsPnl.getForm(),this).show();
						   			}
								}
							]
						},
						{
							layout:'form',
		   					columnWidth:.1,
							items:this.viewPatinetDetailsBtn
				   		}
					]
				},
				{
					layout:'form',
					items:[
						{
						   	xtype:'textfield',
						   	name:'firstName',
						   	fieldLabel: msg.patientfirstname,
						   	anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'textfield',
						   	fieldLabel: msg.patientmiddlename,
						   	name:'middleName',
						   	anchor:'98%'
						}
					]
				},
				{
   					layout:'form',
   					items:[
   						{
							xtype:'textfield',
		   					fieldLabel: msg.patientlastname,
		   					name:'lastName',
		   					anchor:'98%'
   						}
   					]
   				},
				{
					layout:'form',
					items:[
						this.referralTypeCbx
					]
				},
				{
					layout: 'column',
					items : [{
						layout:'form',
						columnWidth : .92,
						items:[
							this.referralNameCbx
						]
					},
					{
						layout : 'column',
						columnWidth : .08,
						items :[this.addReferralBtn]
					}]
				},
				{
					layout:'form',
					items:[
						this.bookingTypeCbx
					]
				},
				{
					layout:'form',
					items:[
					       this.bookTypeFieldSet
					]
				},
				
				{
					layout:'form',
					style:'padding-top:5px',
					items:[{
							layout:'form',
							columnWidth:1,
							items:[{
									xtype:'fieldset',
									title:msg.appointmentdetails,
									height:'100%',
									items:[{
											layout:'column',
											labelAlign:'left',
											defaults:{labelWidth:120,columnWidth:1},
											items:[{
													layout:'form',
													items:[{
															xtype:'combo',
														   	fieldLabel: msg.speciality,
														   	store: loadEspecialityCbx(),
														   	name:'speciality',
															mode:'local',
															displayField:'description',
															valueField:'code',
															allowBlank:false,
															required:true,
														    triggerAction: 'all',
														    anchor:'98%',
														    listeners:{
														    	select: function( comp, record, index){
																	this.appointmentComboFieldSet.removeAll();
																	this.appointmentComboFieldSet.doLayout();
																	mainThis.appointmentTypeCbx.setValue( 'PRIMARY' );
																	this.appointmentsNewAppointmentsDetailsPnl.doLayout();
														    		if(!Ext.isEmpty(comp.getValue())){
														    			calendar.setNewDate (new Date());
																		calendar.createRoster(new Array());
																		calendar.refreshView();
																		this.setWidgetsState();
																		
														    			this.primaryDoctorCbx.enable();
														    			if (this.primaryDoctorCbx.store.length > 0) {
																			this.primaryDoctorCbx.store.removeAll();
																		}
																		
																		this.primaryDoctorCbx.reset();
																		this.primaryDoctorCbx.store.load({params:{start:0, limit:9999}, arg:[record.data.code]});
														    		}
													    		},
													    		scope: this
														    }
														}]
												},
												{
													layout:'form',
													items:this.primaryDoctorCbx
												},
												{
													layout:'form',
													items :this.appointmentComboFieldSet
												},
												{
													layout:'form',
													items:[
														this.appointmentTypeCbx
													]
												},
												{
													layout:'form',
													columnWidth:.33,
													items:[{
															xtype:'label',
															style:'padding-top:5px;' +
																  'font-size:12px',
															text:msg.appointmentfee+":"
														}]
												},
												{
													layout:'form',
													columnWidth:.5,
													items:this.consultationChargesLbl
												},
												{
													layout:'form',
													style:'padding-top:5px',
													items:this.appointmentDate
												},
												{
													columnWidth:0.65,
													layout:'form',
													items:this.formTimeTF 
												},
												{
													columnWidth:0.35,
													labelWidth:30,
													layout:'form',
													items:this.toTimeTF
												},
												{
													layout:'form',
													items:[{
															xtype:'textarea',
															name:'remarks',
															fieldLabel:msg.remarks,
															allowBlank:false,
															required:true,
															anchor:'100%'
														}]
												}]
										}]
								}]
						}]
				}
			],
			buttons:[
				{
					text:msg.confirm,
					iconCls: 'accept-icon',
					scope:this,
					handler:function(){
						var formPanel = this.appointmentsNewAppointmentsDetailsPnl;
						 var formItems = formPanel.getForm().getValues();
						 
						 var patientId = formItems['patientId'];
						 var firstName = formItems['firstName'];
						 var speacialityId = formItems['speciality'];
						 var doctorId = formItems['primaryDoctor'];
						 var middleName = formItems['middleName'];
						 var lastName = formItems['lastName'];
						 var referredBy = formItems['referredBy'];
						 var appointmentCharges = mainThis.doctorConsultationCharge;
						 var appointmentdate = formItems['appointmentDate'];
						 var startTime = formItems['startTime'];
						 startTime = Date.parseDate(startTime, "g:iA").format("H:i");
						 var endTime = formItems['endTime'];
						 endTime = Date.parseDate(endTime, "g:iA").format("H:i");
						 var reasonForAppointment = formItems['remarks'];
						 var createdBy = getAuthorizedUserInfo().userName;
						 var appointmentType = formItems['appointmentType'];
						 var bookingType = formItems['bookingType'];
						 var phone = formItems['Phoneno']||'';
						 var email = formItems['Email']||'';
						 var remarks = formItems['remarks'];
						 var primaryAppointmentNumber = formItems['appointmentNumber'];
						AppointmentManagementController.CreateNewAppointment(
							  patientId ,
							  speacialityId ,
							  doctorId ,
							  primaryAppointmentNumber,
							  getStringAsWtcDateFormat(appointmentdate),
							  startTime,
							  endTime,
							  reasonForAppointment ,
							  firstName,
							  middleName,
							  lastName,
							  referredBy,
							  appointmentType,
							  bookingType,
							  phone,
							  email,
							  createdBy,
							  appointmentCharges,
							  remarks,
							  function( appointmentBM ){
							  	   if( (appointmentBM == null) || Ext.isEmpty( appointmentBM )){
							  	   	 error(msg.cannotCreateAppointment);
							  	   }
							  	   
								   mainThis.receiptConfig ={
										selectedAccountHolderId: appointmentBM.patientId,
										selectedAccountHolderName: appointmentBM.firstName+" "+
																   appointmentBM.middleName+" "+
																   appointmentBM.lastName,
										selectedEntityTypeId: configs.appointment,
										selectedEntityTypeName: configs.appointmentDisplayValue,
										selectedAmount: mainThis.doctorConsultationCharge,
										selectedReceiptDate: appointmentBM.appointmentDate
									}
									
									mainThis.resetPanel();
									mainThis.issuReceiptBtn.enable();
		                    }
	                   );
					}
		   		},
		   		{
					text:msg.reset,
					iconCls: 'reset-icon',
					scope:this,
					handler:function(){
						var formPanel = this.appointmentsNewAppointmentsDetailsPnl;
						  Ext.Msg.show({
								title: msg.resettitle,
								msg: msg.resetmsg,
								icon : Ext.MessageBox.QUESTION,
								buttons: {
										yes: true,
										no: true
								},
								
								fn: function(btn) {
								switch(btn){
										case 'yes':
											mainThis.resetPanel();
										break;
										case 'no':				
										break;
									}
								}
							});
					}
			    }
//			    {//TODO: configure reminder functionality need to be implemented.
//					text:msg.btn_configurereminder,
//					scope:this,
//					handler:function(){
//						handleNewAppointmentConfigureReminderBtn();
//					}
//			    },
//			    this.issuReceiptBtn
			]
		});
		// for monitoring the personal details panel 
		this.appointmentsNewAppointmentsDetailsPnl.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				// for confirm button
				this.appointmentsNewAppointmentsDetailsPnl.buttons[0].enable();
			} else {
				// for confirm button
				this.appointmentsNewAppointmentsDetailsPnl.buttons[0].disable();
			}
		}, this);
		
		this.calendarpanel = new Ext.Panel({
			height:480,
			layout:'fit',
			width:'100%',
			items:this.wtcCalendar.getPanel()
		});
		
		var formPanel  = this.appointmentsNewAppointmentsDetailsPnl;
		this.mainPanel.add(this.appointmentsNewAppointmentsDetailsPnl);
		this.mainPanel.add(this.calendarpanel);
		this.mainPanel.doLayout();
		
		this.mainPanel.defaultValues = function(  ){
			if( !Ext.isEmpty( bookingTypeStore ) && bookingTypeStore.getTotalCount() > 0 ){
				mainThis.bookingTypeCbx.setValue( configs.defValForBookingType );
			}else{
				bookingTypeStore.on('load', function(){
					mainThis.bookingTypeCbx.setValue( configs.defValForBookingType );
					bookingTypeStore.events['load'].clearListeners();
				},this);
			
			}	
			
			if( !Ext.isEmpty( appointmentTypeStore ) && appointmentTypeStore.getTotalCount() > 0 ){
				mainThis.appointmentTypeCbx.setValue( configs.defValForAppointmentType );
			}else{
				appointmentTypeStore.on('load', function(){
					mainThis.appointmentTypeCbx.setValue( configs.defValForAppointmentType );
					appointmentTypeStore.events['load'].clearListeners();
				},this);
			
			}
			
			
		};
		this.calendarpanel.doLayout();
	};
	
	this.loadPatientData = function( patientData ){
		if( !Ext.isEmpty( patientData )){
			this.appointmentsNewAppointmentsDetailsPnl.getForm().setValues( patientData );
			if(!Ext.isEmpty( patientData.mode )){
				if(!Ext.isEmpty( patientData.referralType )){
					this.referralNameCbx.enable();
					this.addReferralBtn.enable();
				}
				this.primaryDoctorCbx.enable();
				this.formTimeTF.disable();
				this.toTimeTF.disable();
				this.appointmentDate.disable();
				this.appointmentComboFieldSet.removeAll();
				this.appointmentComboFieldSet.add({layout:'form',items:[{xtype:'combo',
																		name:'appointmentNumber',
																		fieldLabel: 'Appointment Number',
																		store: this.loadAppointmentNumbersStore,
																		mode:'local',
																		displayField:'description',
																		valueField:'code',
																		triggerAction: 'all',
																		anchor:'98%'}]});
				
				this.loadAppointmentNumbersStore.load({params:{start:0, limit:999}, arg:[parseInt(patientData.patientId),parseInt(patientData.primaryDoctorCode)]});
				this.appointmentComboFieldSet.doLayout();
				this.appointmentsNewAppointmentsDetailsPnl.doLayout();
				this.appointmentTypeCbx.setValue( 'FOLLOW-UP' );
				this.primaryDoctorCbx.store.load({params:{start:0, limit:9999}, arg:[patientData.speacialityCode]});

				this.loadAppointmentNumbersStore.on('load',function(){
					if(!Ext.isEmpty(this.loadAppointmentNumbersStore.getAt(0).data.code)  && this.loadAppointmentNumbersStore.getCount()!=0){
						this.appointmentComboFieldSet.items.items[0].items.items[0].setValue(this.loadAppointmentNumbersStore.getAt(0).data.code);
					}
				},this);
				
				loadEspecialityCbx().on('load',function(){
					this.appointmentsNewAppointmentsDetailsPnl.getForm().setValues({speciality:patientData.speacialityCode});
				},this);
				
				this.doctorsForSpeacialityStore.on('load',function(){
					this.primaryDoctorCbx.setValue(patientData.primaryDoctorCode);
				},this);
					
	   			}
		}
	};
	
	this.getPanel = function(){
		return this.mainPanel;
	};
	this.loadPanel = function() {
		this.createPanel();
		this.mainPanel.doLayout();
		return this.mainPanel;
	};
	//setting focus for first element.
	this.getFocus = function(){
		this.patientIdNbrF.focus();
	};
	
	this.resetPanel = function(){
		this.appointmentsNewAppointmentsDetailsPnl.getForm().reset();
		this.consultationChargesLbl.setText( configs.defaultAmountValue+'('+getCurrencyIndicator()+')' );
		this.getButtonsInitialStatus();
		this.formTimeTF.disable();
		this.toTimeTF.disable();
		this.referralNameCbx.disable();
		this.addReferralBtn.disable();
		this.appointmentDate.disable();
		calendar.createRoster(new Array());
  	    calendar.refreshView();
  	    this.appointmentComboFieldSet.removeAll();
	};
	
	this.getButtonsInitialStatus = function(){
		this.viewPatinetDetailsBtn.disable();
		this.primaryDoctorCbx.disable();
	};
	
	this.setWidgetsState = function(){
		this.appointmentDate.reset();
		this.appointmentDate.disable();
   		
   		this.formTimeTF.reset();
   		this.formTimeTF.disable();
   		
		this.toTimeTF.reset();
		this.toTimeTF.disable();
	};
	
	this.getDoctorCalendar = function(){
		var formItems = this.appointmentsNewAppointmentsDetailsPnl.getForm().getValues();
		var doctorId = formItems['primaryDoctor'];
		loadNewAppointmentCalendar( calendar , new Date().clearTime( true ),  new Date().clearTime( true ),doctorId );
	};
	 this.loadReferralNameForType = function(thisCombo , record ,index){
		var referralType = record.data.code;
		referralStore.load({params:{start:0, limit:99}, arg:[referralType]});
	};
	
	this.getAssinedTimeArray = function(a){
		var formItems = this.appointmentsNewAppointmentsDetailsPnl.getForm().getValues();
		var doctorId = formItems['primaryDoctor'];
		if(!Ext.isEmpty(formItems['appointmentDate'])){

			var fromDate = formItems['appointmentDate'];
			fromDate = Date.parseDate(fromDate, "d/m/Y");
			var toDate = fromDate;
		}else{
			var fromDate = new Date().clearTime( true );
			var toDate = new Date().clearTime( true );
		}
		
		var dateArray = new Array();
		var record = this.formTimeTF.getStore().recordType;
		var mainThis = this;
		
		var myloadNewAppointmentCalendar = function( calendar, fromDate, toDate ,doctorId){
			AppointmentManagementController.getDoctorRoster(fromDate,toDate,doctorId,function(appointmentRosterList){
				if( fromDate.format('j M Y') === toDate.format('j M Y')){
					calendar.setNewDate (fromDate);
				}
				if(appointmentRosterList!=null && appointmentRosterList.length > 0) {
					calendar.createRoster(appointmentRosterList);
				}else{
//					calendar.setNewDate (  );
					calendar.createRoster(new Array());
				}
				if(!Ext.isEmpty(calendar.rosterRecords)){
					mainThis.formTimeTF.getStore().removeAll();
					mainThis.formTimeTF.getStore().add( new record( {myId : calendar.rosterRecords[0].startTime}));
					for(i=0;i<calendar.rosterRecords.length;i++){
						if(calendar.rosterRecords[i].appointmentNumber==null){
							mainThis.formTimeTF.getStore().add( new record({myId : calendar.rosterRecords[i].endTime}));
						}
					}
				}
			});
		};
		
		myloadNewAppointmentCalendar(calendar , fromDate,  toDate,doctorId);	
	};
	
	this.addReferralBtnClicked = function(){
		config = {mode : 'addReferralFromAppointment'};
		var referralPanel = new administration.referralManagement.AddReferral( config );
		var referralType = this.referralTypeCbx.getValue();
		var win = new Ext.Window({
			height: '100%',
			width: 650,
			items: referralPanel,
			title: 'Add referral',
			frame:true,
			modal:true,
			listeners: {
				beforeshow : function(){
						referralPanel.setReferralType( referralType );
				}
			}
		});
		
		win.show();
		
		Ext.ux.event.Broadcast.subscribe('closeAddReferralPanelFromAppointment',function(refType , referralCode ){

			win.close();
			this.referralNameCbx.store.load({params:{start:0, limit:99}, arg:[refType]});
			this.referralNameCbx.store.on('load',function(){
				if(!Ext.isEmpty( referralCode )){
//					this.referralTypeCbx.setValue( refType );
					this.referralNameCbx.setValue( referralCode );
					referralCode = null;
				}	
			},this, null, this.getPanel());	
			
		},this);
	
	};
	
		
};
