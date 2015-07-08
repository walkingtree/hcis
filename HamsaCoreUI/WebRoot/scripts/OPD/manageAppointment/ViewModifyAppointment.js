/**
 * @author Sandeep Kumar
 */
Ext.namespace("OPD.manageAppointment");

OPD.manageAppointment.ViewModifyAppointmentPanel = function(/* Object */config) {
	
	if(Ext.isEmpty( config )){
		config = {};
	}
	
	Ext.apply(this,config);
	this.consultationDetailBtnFlag = false;
	this.wtcCalendar = new wtccomponents.wtccalendar.Calendar();
	var calendar = this.wtcCalendar;
	
	this.mainPanel = new Ext.form.FieldSet({
		 layout:'column',
		 height:'100%',
		 style:'padding: 0px',
		 border:false,
		 defaults:{
		 	columnWidth:.6,
		 	style:'margin-left:2px'
		 }
		 
	});
	
	this.createPanel = function(){
		Ext.QuickTips.init();
		if (!Ext.isEmpty(this.mainPanel.items)) {
			this.mainPanel.removeAll();
		}
		var mainThis = this;
		
		this.primaryDoctor
		
		this.referralTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel : msg.referralType,
			hiddenName : 'referralType',
			store : getReferralTypes(),
			disabled:config.flag,
			anchor:'98%',
			listeners : {
				select : function ( comp , record ,index ){
					this.referralNameCbx.clearValue();
					this.referralNameCbx.enable();
					this.loadReferralNameForType( comp , record ,index );
				},
			scope:this
			}
		});
		
		this.referralNameCbx = new wtccomponents.WTCComboBox({
			fieldLabel : msg.referralName,
			hiddenName : 'referredBy',
			disabled:true,
			store : loadReferralsCbx(),
			anchor:'98%'
		});

		this.appointmentTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel: msg.appointmentType,
			hiddenName: 'appointmentType',
			store:loadAppointmentTypeCbx(),
			disabled:config.flag,
			anchor:'98%'
		});
		
		this.bookingTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel: msg.bookingtype,
			hiddenName: 'bookingType',
			store:loadBookingTypesCbx(),
			disabled:config.flag,
			anchor:'98%'
		});
		
		this.bookTypeFieldSet = new Ext.Panel({border:false});
		
		this.viewModifyAppointmentsDetailsPnl = new Ext.form.FieldSet({
	  		layout:'column',
			labelAlign: 'left',
	        height: '100%',
	        defaults:{ columnWidth:.5, labelWidth:85  },
			items:[
				{
					layout:'form',
					items:[
						{
							 xtype:'numberfield',
							 disabled:true,
							 fieldLabel:msg.patientid,
							 readOnly:true,
							 name:'patientId',
							 anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'textfield',
							disabled:true,
							readOnly:true,
							fieldLabel:msg.patientname,
							name:'patientName',
							anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'combo',
							disabled:true,
							fieldLabel:msg.speciality,
							store:loadEspecialityCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							forceSelection:true,
							hideTrigger: true,
							mode:'local',
							name:'speciality',
							anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'combo',
							disabled:true,
							fieldLabel:msg.primarydoctor,
							store:loadDoctorsCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							forceSelection:true,
							hideTrigger: true,
							mode:'local',
							name:'doctor',
							anchor:'98%',
							listeners:{
								select : function( comp ){
									this.getDoctorCalendar();
								},
								scope:this
							}
						}
					]
				}
			 ]
		});
	
		this.appointmentDate  = new Ext.form.WTCDateField({
		   	name:'appointmentDate',
		   	disabled:true,
		   	fieldLabel:msg.appointmentDate,
//		   	allowBlank:false,
//			required:true,
		   	minValue:new Date().clearTime(true),
		   	anchor:'98%',
		   	listeners:{
		   		blur: function(comp){
		   			
		   			var formItems = formPanel.getForm().getValues();
		   			var doctorId = config.primaryDoctorCode;
		   			
		   			if(Ext.isEmpty(comp.getValue())){
		   				this.formTimeTF.clearValue();
		   				this.formTimeTF.disable();
		   				this.toTimeTF.clearValue();
		   				this.toTimeTF.disable();
		   				comp.focus();
		   			}else{
		   	   	   		this.formTimeTF.enable();
		   			}
		   			
		   			if( !Ext.isEmpty(comp.getValue())){
		   				loadNewAppointmentCalendar(calendar,comp.getValue(), comp.getValue(),doctorId);
		   			}
		   		},
		   		scope:this
		   	}
		});
		this.formTimeTF = new Ext.form.TimeField({
			name:'startTime',
			disabled:true,
	   		format:'g:i A',
//	   		allowBlank:false,
//			required:true,
		   	fieldLabel:msg.starttime,
		   	increment:calendar.slotDuration,
		   	anchor:'98%',
		   	listeners:{
				select:function( comp,recod,index){
					var mainThis = this;
					
					this.toTimeTF.enable();
					
					var toTimeValue = getToTime( comp, this.toTimeTF );
					this.toTimeTF.setValue( toTimeValue );
					
					if(Ext.isEmpty(this.appointmentDate.getValue())){
						Ext.Msg.show({
							msg: "Please specify appointment date..!",
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK,
							fn: function(){
								comp.clearValue();
								mainThis.appointmentDate.focus();
							}
						});
					}
		   		},
		   		blur : function( comp ){
		   			if( Ext.isEmpty( comp.getValue()) ){
		   				this.toTimeTF.clearValue();
		   				this.toTimeTF.disable();
		   				comp.focus();
		   			}
		   		},
		   		scope:this
		   	}
		});
		
		this.toTimeTF = new Ext.form.TimeField({
			name:'endTime',
	   		format:'g:i A',
	   		disabled:true,
//	   		allowBlank:false,
//			required:true,
		   	fieldLabel:msg.endtime,
		   	increment:calendar.slotDuration,
		   	anchor:'98%',
		   	listeners:{
				select:function( comp,recod,index){
					var mainThis = this;
					
					if(Ext.isEmpty(this.formTimeTF.getValue())){
						Ext.Msg.show({
							msg: "Please select start time..!",
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK,
							fn: function(){
								comp.clearValue();
								mainThis.formTimeTF.focus();
							}
						});
					}else{
						var fromDate = Date.parseDate( 
											mainThis.
											viewModifyAppointmentFormPanel.
											getForm().
											getValues()['appointmentDate']+
											' '+
											mainThis.formTimeTF.getValue(),'d/m/Y g:i A');
											
						var toDate = Date.parseDate( 
											mainThis.
											viewModifyAppointmentFormPanel.
											getForm().
											getValues()['appointmentDate']+
											' '+
											comp.getValue(),'d/m/Y g:i A');
						
		                var compareDates = compareTwoDates( fromDate, toDate);
		                
		                if( ! compareDates ){
		                	Ext.Msg.show({
								msg: "Start time should always be less than end time..!",
								icon : Ext.MessageBox.ERROR,
								buttons: Ext.Msg.OK,
								fn: function(){
									comp.clearValue();
									comp.focus();
								}
							});
		                }
					}
		   		},
		   		scope:this
		   	}
		});
		this.viewModifyAppointmentsAppointmentDetailsPnl = new Ext.form.FieldSet({
			layout:'column',
			title:msg.appointmentdetails,
			labelAlign: 'left',
	        height: '100%',
	        defaults:{ columnWidth:.5,labelWidth:110 },
			items:[
				{
					layout:'form',
					items:[
						{
							xtype:'wtcdatefield',
							disabled:true,
						 	fieldLabel:msg.bookingdate,
						 	name:'bookingDate',
							anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:this.appointmentDate
				},
				{
					layout:'form',
					items:this.formTimeTF
				},
				{
					layout:'form',
					items:this.toTimeTF
				},
				{
					layout:'form',
					items:[
						{
							xtype:'combo',
							disabled:true,
							fieldLabel:'Status',
							store:loadAppointmentsStatusCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							forceSelection:true,
							mode:'local',
							name:'appointmentStatus',
							anchor:'98%'
						}
					]
				},
				{
					layout:'form',
					items:this.bookingTypeCbx
				},
				{
					layout:'form',
					items:this.bookTypeFieldSet
				},
				{
					layout:'form',
					items:this.appointmentTypeCbx
				},
				{
					layout:'form',
					items:this.referralTypeCbx
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.referralNameCbx
				},
				{
					layout:'form',
					columnWidth:1,
					labelAlign:'top',
					items:[
						{
							xtype:'textarea',
							disabled:config.flag,
							fieldLabel:msg.remarks,
							name:'remarks',
							anchor:'100%'
						}
					]
				}
			]
		});
		
		

		this.doctorConsultationDetailsBtn = new Ext.Button({
			text:"Consultation details",
		  	disabled:true,
		  	icon : 'images/add.png',
		  	scope:this,
		  	handler:function(){
				
				// If appointment status is not as "Doctor has seen the patient" then first Appointment will be modified 
				// in "Doctor has seen the patient" status then it will allow to add consultation details.
				this.consultationDetailBtnFlag = true;
				if( config.appointmentStatusCode != "CAPTURED"){
					this.handleSaveBtn( config );
				}
				else{
					this.getDoctorConsultationDetails( config );
				}
//				if( this.consultationDetailBtnFlag ){
//				}
		  	}
		});
		
		this.patientCameToVisitChk = new Ext.form.Checkbox({
			checked: config.mode == 'edit'? config.patientCameToVisit : "" ,
			labelSeparator:'',
			disabled:config.mode == 'edit'? config.patientCameToVisit : "" ,
			boxLabel:msg.Patientcametovisitconsultant,
			name:'patientCameToVisit',
		    listeners: {
				check: function( chk, isChecked ){
					if( ! isChecked ){
						this.patientVisitedConsultantChk.setValue(false);
//						this.patientVisitedConsultantChk.disable();
						this.doctorSeenConsultantChk.setValue(false);
//						this.doctorSeenConsultantChk.disable();
						this.doctorConsultationDetailsBtn.disable();
						
//						var formValues = this.viewModifyAppointmentFormPanel.getForm().getValues();
//						if( Ext.isEmpty(formValues['remarks']) || formValues['remarks'] == '<br>'){
//							this.saveBtn.disable();
//						}else{
//							this.saveBtn.enable();
//						}
					}else {
//						this.saveBtn.enable();
//						this.doctorConsultationDetailsBtn.enable();
						this.patientVisitedConsultantChk.enable();
					}
				},
				scope:this
			}
		});
		this.patientVisitedConsultantChk = new Ext.form.Checkbox({
			checked: config.mode == 'edit' ? config.patientVisitedConsultant : "" ,
			labelSeparator:'',
			disabled: config.mode == 'edit' ? config.patientVisitedConsultant : "" ,
			boxLabel:msg.Patientvisitedconsultant,
			name:'patientVisitedConsultant',
			listeners:{
				check: function( chk, isChecked ){
					if( isChecked ){
//						this.patientCameToVisitChk.setValue( true );
						this.patientCameToVisitChk.disable();
						this.doctorSeenConsultantChk.enable();
					}else {
						this.patientCameToVisitChk.enable();
						this.doctorSeenConsultantChk.setValue(false);
//						this.doctorSeenConsultantChk.disable();
					}	
				},
				scope: this
			}
		});
		
		this.doctorSeenConsultantChk = new Ext.form.Checkbox({
			checked: config.mode == 'edit' ? config.consultantHasSeenPatient : "" ,
			labelSeparator:'',
			disabled: config.mode == 'edit' ? config.consultantHasSeenPatient : "" ,
			boxLabel:'Consultant has seen patient',
			name:'doctorHasSeenPatient',
			listeners:{
				check: function( chk, isChecked ){
					if( isChecked ){
						this.patientCameToVisitChk.disable();
//						this.patientVisitedConsultantChk.setValue( true );
						this.patientVisitedConsultantChk.disable();
//						this.doctorConsultationDetailsBtn.setVisible(true);
						this.doctorConsultationDetailsBtn.enable();
					}else {
						this.doctorConsultationDetailsBtn.disable();
						this.patientVisitedConsultantChk.enable();
						if( !this.patientVisitedConsultantChk.checked ){
							this.patientCameToVisitChk.enable();
						}
					}	
				},
				scope: this
				}
			});
			
			this.receiptConfig = {};
			
			this.issueReceiptBtn  = new Ext.Button({
				text:msg.issueReceiptBtn,
				iconCls:'money-icon',
				scope:this,
				disabled:false,
				handler:function(){
//					layout.getCenterRegionTabPanel().remove( mainThis.getPanel().ownerCt, true );
//					Ext.ux.event.Broadcast.publish('closeViewModifyWindow');
					if( !Ext.isEmpty( this.receiptConfig )){
						this.receiptConfig.selectedAccountHolderId = config.patientId;
					}
					showReceiptWindow( this.receiptConfig );
				}
			});
			
			
			this.admissionOrderBtn  = new Ext.Button({
				text:'Add admission order',
				icon : 'images/add.png',
				scope:this,
				disabled:true
			});

		this.viewmodifyAppointmentConsultationstatusDetailsPnl = new Ext.Panel({
            layout:'column',
			labelAlign: 'left',
			height:'100%',
			defaults:{
				columnWidth:1,
				labelWidth:.01
			},
	        items:[
	        	{
	        		layout:'form',
	        		items: this.patientCameToVisitChk
	        	},
	        	{
	        		layout:'form',
	        		items: this.patientVisitedConsultantChk
	        	},
	        	{
	        		layout:'form',
	        		columnWidth:.5,
	        		items:  this.doctorSeenConsultantChk
	        	},
	        	{
	        		layout:'form',
	        		columnWidth:.5,
	        		items: this.doctorConsultationDetailsBtn
	        	}
	        	
  			]
		});
		
		this.saveBtn = new Ext.Button({
			text:msg.btn_save,
		  	hidden:config.flag,
		  	iconCls:'save_btn',
		  	scope:this,
			handler:function(){
				this.handleSaveBtn( config );
			}
		});
		
		this.resetBtn = new Ext.Button({
		  	text:msg.reset,
		  	hidden:config.flag,
		  	text: msg.reset,
		  	scope:this,
			iconCls: 'reset-icon',
			handler:function(){
				var tmpThis = this;
				Ext.Msg.show({
					msg: msg.resetmsg,
					icon : Ext.MessageBox.QUESTION,
					buttons: Ext.Msg.YESNO,
					fn: function( btnValue ){
						if( btnValue == configs.yes){
							tmpThis.getPanelInitialState();
							tmpThis.loadData(tmpThis);
						}	
						
					}
				});
				
			}
		});
		
		this.viewModifyAppointmentFormPanel = new Ext.form.FormPanel({
			frame:true,
			buttonAlign:'left',
			items:[
				this.viewModifyAppointmentsDetailsPnl,
		        this.viewModifyAppointmentsAppointmentDetailsPnl
			],
			buttons:[
				this.saveBtn,
				this.resetBtn,
				{
				  	text:msg.printvisitslip,
				  	hidden:config.flag,
					handler:function(){
						this.handleViewModifyAppointmentPrintBtn();
					},
					scope : this
				},
				this.issueReceiptBtn,
				this.admissionOrderBtn
			]
		});
		
		if(config.bookingTypeCode=='PHONE'){
			this.bookTypeFieldSet.removeAll();
			this.bookTypeFieldSet.add({layout:'form',items:[{xtype:'numberfield',name:'phone',fieldLabel: 'Phone No',disabled:config.flag,value : config.phone, anchor:'98%'}]});
			this.mainPanel.doLayout();
		}else if(config.bookingTypeCode=='EMAIL'){
			this.bookTypeFieldSet.removeAll();
			this.bookTypeFieldSet.add({layout:'form',items:[{xtype:'textfield',name:'email',fieldLabel: 'Email',vtype:'email',disabled:config.flag,value : config.email, anchor:'98%'}]});
			this.mainPanel.doLayout();
		}else{
			this.bookTypeFieldSet.removeAll();
			this.mainPanel.doLayout();
		}
		
		this.bookingTypeCbx.on('select',function(comp){
			if(comp.getValue()=='PHONE'){
				this.bookTypeFieldSet.removeAll();
				this.bookTypeFieldSet.add({layout:'form',items:[{xtype:'numberfield',name:'phone',fieldLabel: 'Phone No',disabled:config.flag,anchor:'98%'}]});
				this.mainPanel.doLayout();
			}else if(comp.getValue()=='EMAIL'){
				this.bookTypeFieldSet.removeAll();
				this.bookTypeFieldSet .add({layout:'form',items:[{xtype:'textfield',name:'email',vtype:'email',disabled:config.flag,fieldLabel: 'Email',anchor:'98%'}]});
				this.mainPanel.doLayout();
			}else{
				this.bookTypeFieldSet.removeAll();
				this.mainPanel.doLayout();
			}
			
		},this);
		
		if( config.patientCameToVisit){
//			this.patientVisitedConsultantChk.enable();
			this.patientCameToVisitChk.enable();
		}
		if( config.patientVisitedConsultant){
			this.patientCameToVisitChk.disable();
			this.patientVisitedConsultantChk.enable();
			this.doctorSeenConsultantChk.enable();
		}	
		if( config.consultantHasSeenPatient){
			this.patientVisitedConsultantChk.disable();
			this.patientCameToVisitChk.disable();
			this.doctorSeenConsultantChk.disable();
			this.doctorConsultationDetailsBtn.enable();
			this.bookingTypeCbx.disable();
			this.bookTypeFieldSet.disable();
			this.appointmentTypeCbx.disable();
			this.referralTypeCbx.disable();
			this.referralNameCbx.disable();
			this.saveBtn.disable();
			this.resetBtn.disable();
		}	
		
		if(!Ext.isEmpty(config) && config.flag == true){
			this.doctorConsultationDetailsBtn.disable();
			this.patientCameToVisitChk.disable();
			this.patientVisitedConsultantChk.disable();
			this.doctorSeenConsultantChk.disable();
			this.viewModifyAppointmentFormPanel.add(this.viewmodifyAppointmentConsultationstatusDetailsPnl);
		}else if(!Ext.isEmpty(config) && config.flag == false){
			this.viewModifyAppointmentFormPanel.add(this.viewmodifyAppointmentConsultationstatusDetailsPnl);	
		}

		
		var formPanel = this.viewModifyAppointmentFormPanel;
		
		this.mainPanel.add(this.viewModifyAppointmentFormPanel);
	
		this.mainPanel.add({
			height:config.flag?400:480,
			layout:'fit',
			columnWidth:.4,
			items:this.wtcCalendar.getPanel()});
		this.mainPanel.doLayout();
	};
	
	this.getPanel = function(){
		return this.mainPanel;
	};
	
	this.loadPanel = function() {
		this.createPanel();
		this.mainPanel.doLayout();
		return this.mainPanel;
	};
	
	this.loadData = function(config){
		var loadValues= {
			patientId:config.patientId,
			speciality:config.speacialityCode,
			doctor:config.doctorCode,
			patientName:config.patientName,
			appointmentDate:config.appointmentDate,
			bookingDate:config.bookingDate,
			startTime:config.startTime,
			endTime:config.endTime,
			appointmentStatus:config.appointmentStatusCode,
			referredBy:config.referredByCode,
			referralType:config.referralTypeCode,
			bookingType: config.bookingTypeCode,
			phone: config.phone,
			email : config.email,
			appointmentType: config.appointmentTypeCode,
			remarks:config.remarks
		};
		Ext.apply( this, config );
		
		if( !Ext.isEmpty( config ) && !Ext.isEmpty( config.referredByCode ) ){
			referralStore.load({params:{start:0, limit:999}, arg:[config.referralTypeCode]});
			referralStore.on('load', function(){
				this.viewModifyAppointmentFormPanel.getForm().setValues(loadValues);
				loadNewAppointmentCalendar( calendar, config.appointmentDate, config.appointmentDate, config.doctorCode );
				referralStore.events['load'].clearListeners();
			},this);
		}else{
			this.viewModifyAppointmentFormPanel.getForm().setValues(loadValues);
			loadNewAppointmentCalendar( calendar, config.appointmentDate, config.appointmentDate,config.doctorCode );
		}
		
		doctorsStore.on('load',function(){
			this.viewModifyAppointmentFormPanel.getForm().setValues(loadValues);
		},this)
		
	};
	
	this.getDoctorCalendar = function(){
		var formItems = this.viewModifyAppointmentFormPanel.getForm().getValues();
		var doctorId = formItems['doctor']; 
		loadNewAppointmentCalendar( calendar , new Date().clearTime( true ), new Date().clearTime( true ), doctorId );
	};
	
	this.getDoctorConsultationDetails = function( config ){
		var consultationConfig = {
			appointmentNumber: config.appointmentNo,
			doctorCode: config.doctorCode,
			patientId: config.patientId,
			appointmentDate : config.appointmentDate
		};
		var doctorConsultation = doctorConsultationDetails(consultationConfig);
		var panel = layout.getCenterRegionTabPanel().add({
			frame:true,
			title : 'Cosultation details', 
			closable : true,
			height : 420,
			items : [doctorConsultation]
		});
		
		layout.getCenterRegionTabPanel().setActiveTab( panel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeDocConsultationDetailsPanel',function(){
			layout.getCenterRegionTabPanel().remove( panel, true );
			layout.getCenterRegionTabPanel().setActiveTab( this.viewModifyAppointmentFormPanel );
			layout.getCenterRegionTabPanel().doLayout();
			this.doctorSeenConsultantChk.enable();
		},this , null, this.getPanel() );
	};
	
	this.getPanelInitialState = function(){
		this.viewModifyAppointmentFormPanel.getForm().reset();
		this.patientVisitedConsultantChk.reset();
		this.patientCameToVisitChk.reset();
		this.doctorSeenConsultantChk.reset();
		this.doctorConsultationDetailsBtn.disable();
	};
	this.loadReferralNameForType = function(thisCombo , record ,index){
		var referralType = record.data.code;
		referralStore.load({params:{start:0, limit:9999}, arg:[referralType]});
	};
	this.handleSaveBtn = function( config ){
		var tmpThis = this;
		var modifiedValues = this.viewModifyAppointmentFormPanel.getForm().getValues();
		
		var patientVisitedConsultant = this.patientVisitedConsultantChk.getValue();
		var patientCameToVisit = this.patientCameToVisitChk.getValue();
		var doctorHasSeenPatient = this.doctorSeenConsultantChk.getValue();
		AppointmentManagementController.getDoctorRosterCode(
			 configs.doctor,
			 config.doctorCode,
			 config.appointmentDate ,
			 config.appointmentDate ,
			 config.startTime ,  
			 config.endTime , 
			 null, 
	   		 function(rosterId) {
                   var rosterCode = !Ext.isEmpty(rosterId) ? parseInt( rosterId ) : null;
                   AppointmentManagementController.modifyAppointment(
						config.appointmentNo, 
						config.patientId, 
						config.firstName ,  
						config.middleName , 
						config.lastName ,
						config.appointmentDate, 
						config.appointmentStatusCode,
						config.bookingDate, 
						patientCameToVisit , 
						patientVisitedConsultant,
						doctorHasSeenPatient,
						config.speacialityCode,
						config.doctorCode, 
						config.endTime , 
						config.startTime ,  
						modifiedValues['remarks'] ,
						rosterCode,
						modifiedValues['referredBy'],
						modifiedValues['appointmentType'],
						modifiedValues['bookingType'],
						modifiedValues['phone']||'',
						modifiedValues['email']||'',
						getAuthorizedUserInfo().userName,
						{
		                   callback:function() { 
		                   		reloadManageAppointmentDataStore();
		                   		PatientManager.getBusinessPartnerId( config.patientId , {
									callback: function( accountId ){
										referenceNbr = config.appointmentNo;
										clientName = configs.referenceTypeForOPD;
								
										BillManager.runBill( accountId, clientName, referenceNbr, 
														 function( billObjectBM ){
															 	if( tmpThis.consultationDetailBtnFlag ){
															 		
															 		tmpThis.getDoctorConsultationDetails( config );
															 		tmpThis.doctorSeenConsultantChk.disable();
															 		tmpThis.saveBtn.disable();
															 		tmpThis.resetBtn.disable();
															 		
															 	}
																  tmpThis.receiptConfig ={
																	   		selectedAccountHolderId:config.patientId
																   };
//																  tmpThis.issueReceiptBtn.enable();
																  if( !tmpThis.consultationDetailBtnFlag ){
																	  layout.getCenterRegionTabPanel().remove( tmpThis.getPanel().ownerCt, true );
																	  Ext.ux.event.Broadcast.publish('closeViewModifyWindow');
																  }
																  
										});
									},
									scope: this
								});
		                   } 
		                }
					 );
               });
	};
	
	this.handleViewModifyAppointmentPrintBtn = function(){
		var appointmentNumber = config.appointmentNo;
		AppointmentManager.generateVisitSlip(
				parseInt(appointmentNumber), 
				function(reportURL){
					window.open(getBaseURL() + reportURL);
		        });

	}
}
