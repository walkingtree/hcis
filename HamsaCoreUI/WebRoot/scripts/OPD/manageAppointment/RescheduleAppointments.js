Ext.namespace("OPD.manageAppointment");

OPD.manageAppointment.RescheduleAppointmentPanel = function(/* Object */config) {
	
	Ext.apply(this,config);
	
	this.wtcCalendar = new wtccomponents.wtccalendar.Calendar();
	var calendar = this.wtcCalendar;
	
	this.mainPanel = new Ext.form.FieldSet({
		border:false,
		style:'padding:0px',
		layout:'column',
		height:'100%',
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
		this.rescheduleAppointmentsDetailsPnl = new Ext.form.FieldSet({
			layout:'column',
			labelAlign: 'left',
	        height:'100%',
	        defaults:{
	        	columnWidth:1
	        },
			items:[
				{
					layout:'form',
					items:[
						{
							 xtype:'textfield',
							 fieldLabel:msg.patientid,
							 readOnly:true,
							 cls:'textfield-displayonly',
							 name:'rescheduleAppointmentPatientId',
							 anchor:'90%'
						}	
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'textfield',
							fieldLabel:msg.speciality,
							readOnly:true,
							cls:'textfield-displayonly',
							name:'rescheduleAppointmentSpeaciality',
							anchor:'90%'
						}	
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'textfield',
							fieldLabel:msg.primarydoctor,
							readOnly:true,
							cls:'textfield-displayonly',
							name:'rescheduleAppointmentPrimarydoctor',
							anchor:'90%'
						}	
					]
				},
				{
					layout:'form',
					items:[
						{
							xtype:'textfield',
							fieldLabel:msg.patientname,
							readOnly:true,
							cls:'textfield-displayonly',
							name:'rescheduleAppointmentPatientname',
							anchor:'90%'	 
						}	
					]
				}
			]	
	});
	
	this.appointmentDate  = new Ext.form.WTCDateField({
	   	name:'rescheduleAppointmentRescheduledAppointmentDate',
	   	fieldLabel:msg.appointmentDate,
	   	allowBlank:false,
		required:true,
	   	minValue:new Date().clearTime(),
	   	anchor:'100%',
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
	   				loadNewAppointmentCalendar( calendar, comp.getValue(), comp.getValue(), doctorId );
	   			}
	   		},
	   		scope:this
	   	}
	});
	
	this.formTimeTF = new Ext.form.TimeField({
		name:'rescheduleAppointmentRescheduledStarttime',
   		format:'g:i A',
   		allowBlank:false,
		required:true,
	   	fieldLabel:msg.starttime,
	   	increment:calendar.slotDuration,
	   	disabled:true,
	   	anchor:'98%',
	   	listeners:{
			select:function( comp,recod,index){
				var mainThis = this;
				
				this.toTimeTF.enable();
				
				var toTimeValue = getToTime( comp , this.toTimeTF );
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
		name:'rescheduleAppointmentRescheduledEndtime',
   		format:'g:i A',
   		allowBlank:false,
		required:true,
	   	fieldLabel:msg.endtime,
	   	increment:calendar.slotDuration,
	   	disabled:true,
	   	anchor:'100%',
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
										rescheduleAppointmentInfoPnl.
										getForm().
										getValues()['rescheduleAppointmentRescheduledAppointmentDate']+
										' '+
										mainThis.formTimeTF.getValue(),'d/m/Y g:i A');
										
					var toDate = Date.parseDate( 
										mainThis.
										rescheduleAppointmentInfoPnl.
										getForm().
										getValues()['rescheduleAppointmentRescheduledAppointmentDate']+
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
	
	this.referralTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel : msg.referralType,
			hiddenName : 'referralType',
			store : getReferralTypes(),
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
			anchor:'98%',
			required:true,
			allowBlank: false
		});
		
		this.bookingTypeCbx = new wtccomponents.WTCComboBox({
			fieldLabel: msg.bookingtype,
			hiddenName: 'bookingType',
			store:loadBookingTypesCbx(),
			anchor:'98%'
		});
	
	this.rescheduleAppointmentsOldAppointmentDetailsPnl = new Ext.form.FieldSet({
		layout:'column',
		labelAlign: 'left',
		height:'100%',
		title:msg.oldappointmentdetails,
		defaults:{
			columnWidth:1,
			labelWidth:115
		},
		items:[
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.appointmentDate,
						readOnly:true,
						cls:'textfield-displayonly',
						name:'rescheduleAppointmentOldAppointmentDate',
						anchor:'90%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.starttime,
						readOnly:true,
						cls:'textfield-displayonly',
						name:'rescheduleAppointmentOldStartTime',
						anchor:'90%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.endtime,
						readOnly:true,
						cls:'textfield-displayonly',
						name:'rescheduleAppointmentOldEndTime',
						anchor:'90%'
					}
				]
			}	
		]
	});
	this.rescheduledAppointmentDetailsPnl = new Ext.form.FieldSet({
		layout:'column',
		labelAlign: 'left',
		title:msg.rescheduledappointmentdetails,
		height:'100%',
		defaults:{
			columnWidth:.5,
			labelWidth:115
		},
		items:[
		{
			layout:'form',
			labelWidth:.01,
			items:[
				{
					xtype:'checkbox',
					boxLabel:msg.rescheduleanywayflag,
					labelSeparator:'',
					name:'rescheduleAppointmentRescheduleFlag',
					anchor:'90%'
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
			items:this.bookingTypeCbx
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
		}
	]
	});
	
	this.rescheduleAppointmentsOldAppointmentDetailsPnl.on('render',function(comp){
		comp.setHeight(this.rescheduleAppointmentsDetailsPnl.getHeight());
	},this);
	this.rescheduleAppointmentInfoPnl = new Ext.form.FormPanel({
		monitorValid:true,
		frame:true,
		layout:'column',
		defaults:{ columnWidth:.5},
		items:[{
		       layout:'form',
		       padding: 2,
		       items :this.rescheduleAppointmentsDetailsPnl
		       },{
		    	   layout:'form',
			       items :this.rescheduleAppointmentsOldAppointmentDetailsPnl
		               
		},
		{
			layout:'form',
			columnWidth:1,
			items:
			       this.rescheduledAppointmentDetailsPnl}
		],
		buttons:[
			{
				xtype:'button',
				text:msg.confirm,
				iconCls: 'accept-icon',
				style:'padding-top:5px',
				scope:this,
				handler:function(){
					var mainThis = this;
					this.confirmBtnHandler(mainThis);
//					handleResceduleAppointmentConfirmBtn( 
//			  		this.rescheduleAppointmentInfoPnl.getForm().getValues(),
//			  		this.rescheduleAppointmentInfoPnl.ownerCt.ownerCt );
				}
			},
			{
				xtype:'button',
				text:msg.reset,
				iconCls: 'reset-icon',
				scope:this,
				style:'padding-top:5px; padding-left:5px',
				handler:function(){
					var mainThis = this;
					this.resetBtnhandler(mainThis, config );
				}
			}
		]
	});
	var formPanel = this.rescheduleAppointmentInfoPnl;
	this.mainPanel.add(this.rescheduleAppointmentInfoPnl);
	this.mainPanel.add({height:480,layout:'fit',columnWidth:.4,items:this.wtcCalendar.getPanel()});
	this.mainPanel.doLayout();
	
	// for monitoring the personal details panel 
	this.rescheduleAppointmentInfoPnl.on("clientvalidation", function(thisForm, isValid) {
		if (isValid){
			this.rescheduleAppointmentInfoPnl.buttons[0].enable();
		} else {
			this.rescheduleAppointmentInfoPnl.buttons[0].disable();
		}
	}, this);
	};
	this.getPanel = function(){
		return this.mainPanel;
	};
	this.loadReferralNameForType = function(thisCombo , record ,index){
		var referralType = record.data.code;
		referralStore.load({params:{start:0, limit:9999}, arg:[referralType]});
	};
	this.loadPanel = function() {
		this.createPanel();
		this.mainPanel.doLayout();
		
		return this.mainPanel;
	};
	this.loadData = function(config){
		var loadValues= {
			rescheduleAppointmentPatientId:config.patientId,
			rescheduleAppointmentSpeaciality:config.speaciality,
			rescheduleAppointmentPrimarydoctor:config.primaryDoctor,
			rescheduleAppointmentPatientname:config.patientName,
			rescheduleAppointmentOldAppointmentDate:config.appointmentDateAsString,
			rescheduleAppointmentOldStartTime:config.fromTime,
			rescheduleAppointmentOldEndTime:config.toTime
		};
		
		this.rescheduleAppointmentInfoPnl.getForm().setValues(loadValues);
		loadNewAppointmentCalendar( calendar, config.appointmentDate , config.appointmentDate, config.primaryDoctorCode);
	};
	this.confirmBtnHandler = function(thisObj){
		if( thisObj.rescheduleAppointmentInfoPnl.getForm().isValid() ){
	 		var form = thisObj.rescheduleAppointmentInfoPnl.getForm().getValues();
	 		
	 		var startTime = form['rescheduleAppointmentRescheduledStarttime'];
	 		startTime = Date.parseDate(startTime, "g:i A").format("H:i");
			var endTime = form['rescheduleAppointmentRescheduledEndtime'];
			endTime = Date.parseDate(endTime, "g:i A").format("H:i");
			
	 		AppointmentManagementController.rescheduleAppointment( 
	 				thisObj.appointmentNo,
					form['rescheduleAppointmentPatientId'],
					form['rescheduleAppointmentSpeaciality'],
					form['rescheduleAppointmentPrimarydoctor'],
					form['rescheduleAppointmentOldAppointmentDate'],
					form['rescheduleAppointmentOldStartTime'],
				 	form['rescheduleAppointmentOldEndTime'],
				 	form['rescheduleAppointmentRescheduledAppointmentDate'],
				 	startTime,
				 	endTime,
				 	thisObj.patientFirstName,
				 	thisObj.patientMiddleName,
				 	thisObj.patientLastName,
				 	thisObj.speacialityCode,
				 	thisObj.primaryDoctorCode,
				 	form['rescheduleAppointmentRescheduleFlag'],
				 	thisObj.rosterCode,
				 	form['appointmentType'],
				 	form['bookingType'],
				 	form['referredBy'],
				 	getAuthorizedUserInfo().userName,
					function() { 
                  		Ext.ux.event.Broadcast.publish('closeRescheduleWindow');
						reloadManageAppointmentDataStore();
		            } 
			 )
 		}
	};
	this.resetBtnhandler = function( thisObj, config ){
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
						 thisObj.rescheduleAppointmentInfoPnl.getForm().reset();
						 thisObj.loadData( config );
						 thisObj.getInitialStatus();
					break;
					case 'no':				
					break;
				}
			}
		});
	};
	
	this.getInitialStatus = function(){
	
		this.appointmentDate.reset();
		this.formTimeTF.reset();
		this.toTimeTF.reset();
		this.formTimeTF.disable();
		this.toTimeTF.disable();
		
	};
};