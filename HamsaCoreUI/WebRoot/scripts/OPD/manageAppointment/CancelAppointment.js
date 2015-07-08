/**
 * @author Vinay Kurudi
 */
Ext.namespace("OPD.manageAppointment");
 
OPD.manageAppointment.CancelAppointmentPanel = function(/* Object */config) {
	
	Ext.apply(this,config);
	
	this.mainPanel = new Ext.form.FieldSet({
		 height:'100%'
	});
		
	this.createPanel = function(){
		Ext.QuickTips.init();
		if (!Ext.isEmpty(this.mainPanel.items)) {
			this.mainPanel.removeAll();
		}
	
	this.cancleAppointmentsDetailsPnl = new Ext.form.FieldSet({
		layout:'column',
		labelAlign:'left',
        height: '100%',
        defaults:{
        	columnWidth:.5,
        	labelWidth:85
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
						 name:'cancelAppointmentPatientId',
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
						name:'cancelAppointmentPatientname',
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
						name:'cancelAppointmentSpeaciality',
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
						name:'cancelAppointmentPrimarydoctor',
						anchor:'90%'
					}
				]
			}		           
	   	]
	});
	this.cancleAppointmentsOldAppointmentDetailsPnl = new Ext.form.FieldSet({
		layout:'column',
		title:msg.appointmentdetails,
		height: '100%',
		labelAlign: 'left',
		defaults:{
			columnWidth:.5,
			labelWidth:150
		},
		items:[
			{
				layout:'form',
				items:[
					{
						xtype: 'textfield',
				 		name:'cancelAppointmentAppointmentDate',
				 		fieldLabel: msg.appointmentDate,
				 		readOnly:true,
						cls:'textfield-displayonly',
				 		anchor: '90%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						name:'cancelAppointmentAppointmentStatus',
						fieldLabel:msg.appointmentstatus,
						readOnly:true,
						cls:'textfield-displayonly',
						anchor:'90%'	
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						name:'cancelAppointmentStartTime',
						fieldLabel:msg.starttime,
						readOnly:true,
						cls:'textfield-displayonly',
						anchor:'90%'
					}
				]
			},
			{
				layout:'form',
				items:[
					{
						xtype:'textfield',
						name:'cancelAppointmentEndTime',
						fieldLabel:msg.endtime,
						readOnly:true,
						cls:'textfield-displayonly',
						anchor:'90%'
					}
				]
			},
			{
				layout:'form',
				columnWidth:1,
				items:[
					{
						xtype: 'textfield',
				 		name:'cancelAppointmentBookingType',
				 		fieldLabel: msg.bookingtype,
				 		readOnly:true,
						cls:'textfield-displayonly',
				 		anchor: '80%'
					}
				]
			},
			{
				layout:'form',
				columnWidth:1,
				items:[
					{
						xtype:'combo',
						name:'cancelAppointmentCancellationReason',
						fieldLabel:msg.cancellationreason,
						anchor:'80%',
						store:loadCancellationReasonCbx(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    forceSelection: true,
					    allowBlank:false,
					    required:true
					}
				]
			},
		 	{
			 	columnWidth: 1,
			 	layout: 'form',
			 	items:[
			 		{
		    			xtype:'textarea',
		    			allowBlank:true,
						name:'cancelAppointmentAppointmentRemarks',
						fieldLabel:msg.appointmentremarks,
						anchor:'100%',
						allowBlank:false,
					    required:true
				    }
			 	]
			 }
		]
	});
	this.cancleAppointmentsInfoPnl = new Ext.form.FormPanel({
			frame:true,
			monitorValid:true,
			items:[
				this.cancleAppointmentsDetailsPnl,
		        this.cancleAppointmentsOldAppointmentDetailsPnl
			],
			buttons:[
				{
					xtype: 'button',
					text: msg.confirm,
					iconCls: 'accept-icon',
					scope:this,
					handler:function(){
						var mainThis = this;
						this.confirmBtnHandler(mainThis);
					}
				},
				{
					xtype: 'button',
					style:'padding-left:5px',
					text: msg.reset,
					iconCls: 'reset-icon',
					scope:this,
					handler:function(){
						var mainThis = this;
						this.resetBtnhandler(mainThis);
					}
			   } 
			]
		});
		this.mainPanel.add(this.cancleAppointmentsInfoPnl);
		this.mainPanel.doLayout();
		
		// for monitoring the personal details panel 
		this.cancleAppointmentsInfoPnl.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.cancleAppointmentsInfoPnl.buttons[0].enable();
			} else {
				this.cancleAppointmentsInfoPnl.buttons[0].disable();
			}
		}, this);
	};
	
	this.getPanel = function(){
		return this.mainPanel;
	};
	
	this.loadPanel = function() {
		this.createPanel();
		this.mainPanel.doLayout();
		
		return this.mainPanel;
	};
	// for loading data initially, at the time of page construction
	this.loadData = function(config){
		var loadValues= {
			cancelAppointmentPatientId:config.patientId,
			cancelAppointmentSpeaciality:config.speaciality,
			cancelAppointmentPrimarydoctor:config.primaryDoctor,
			cancelAppointmentPatientname:config.patientName,
			cancelAppointmentAppointmentDate:config.appointmentDateAsString,
			cancelAppointmentAppointmentStatus:config.appointmentStatus,
			cancelAppointmentBookingType:config.bookingType,
			cancelAppointmentStartTime:config.fromTime,
			cancelAppointmentEndTime:config.toTime
		};
		this.cancleAppointmentsInfoPnl.getForm().setValues(loadValues);
	};
	//confirm button handler
	this.confirmBtnHandler = function(/* scope Object */thisObj){
		var form = thisObj.cancleAppointmentsInfoPnl.getForm().getValues();
		if( (form['cancelAppointmentCancellationReason'] === "") || 
 			(form['cancelAppointmentCancellationReason'] === null) ){
	 		Ext.Msg.show({
					msg: msg.mentioncancellationreason,
					icon : Ext.MessageBox.WRNING,
					buttons: Ext.Msg.OK
			})
	 	}else{
	 		AppointmentManagementController.cancelAppointment(
 				thisObj.appointmentNo,
			    form['cancelAppointmentPatientId'],
			    form['cancelAppointmentSpeaciality'],
				form['cancelAppointmentPrimarydoctor'],
				form['cancelAppointmentPatientname'],
				form['cancelAppointmentAppointmentDate'],
				form['cancelAppointmentStartTime'],
				form['cancelAppointmentEndTime'],
				thisObj.patientFirstName,
				thisObj.patientMiddleName,
				thisObj.patientLastName,
				thisObj.speacialityCode,
				thisObj.primaryDoctorCode,
				form['cancelAppointmentAppointmentRemarks'],
				form['cancelAppointmentAppointmentStatus'],
				thisObj.appointmentStatusCode,
				form['cancelAppointmentBookingType'],
				thisObj.bookingTypeCode,
				form['cancelAppointmentCancellationReason'],
				thisObj.rosterCode,
				getAuthorizedUserInfo().userName,
                function() { 
	           		Ext.ux.event.Broadcast.publish('closeCancelWindow');
					reloadManageAppointmentDataStore();
            	} 
			  )
	 	}
	};
	// reset button handler
	this.resetBtnhandler = function(/* scope Object */thisObj){
		 Ext.Msg.show({
			title: msg.resettitle,
			msg: msg.resetmsg,
			icon : Ext.MessageBox.QUESTION,
			buttons:{
				yes: true,
				no: true
			},
			fn: function(btn) {
				switch(btn){
					case 'yes':
					
						var resetCancelAppointmentConfig = {
							cancelAppointmentAppointmentRemarks: '',
							cancelAppointmentCancellationReason: ''
						}
						thisObj.cancleAppointmentsInfoPnl.getForm().setValues( resetCancelAppointmentConfig );
					break;
					case 'no':				
					break;
				}
			}
		});
	};	
};
