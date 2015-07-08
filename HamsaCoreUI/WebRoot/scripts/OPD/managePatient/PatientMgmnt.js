Ext.namespace("OPD.managePatient");

OPD.managePatient.PatientMgmnt = Ext.extend(Object,{

	constructor : function() {

		loadRegistrationTypeCbx();
		loadRegistrationStatusCbx();
		loadPatientRatingCbx();
		loadPatientCategoryCbx();
		
		loadTitleCbx();
		loadGenderCbx();
		loadBloodGroupCbx();
		loadMaritalStatusCbx();

		loadReligionCbx();
		loadMotherTongueCbx();
		loadIdProofCbx();
		loadNationalityCbx();
		loadCountryCbx();
		loadStateCbx();
		
		loadWeightIndicatorStore();
		loadHeightIndicatorStore();
	
		this.regFromDate = new Ext.form.WTCDateField( {
			name :'registrationFrom',
			fieldLabel :msg.regfrom,
			anchor :'95%'
		});

		this.regToDate = new Ext.form.WTCDateField( {
			name :'registrationTo',
			fieldLabel :msg.regto,
			anchor :'95%'
		});

		this.lastVisitedFrom = new Ext.form.WTCDateField( {
			name :'lastVisitedFrom',
			fieldLabel :msg.patientlastvisitedfrom,
			anchor :'95%'
		});

		this.lastVisitedTo = new Ext.form.WTCDateField( {
			name :'lastVisitedTo',
			fieldLabel :msg.patientlastvisitedto,
			anchor :'95%'
		});

		this.birthDateFrom = new Ext.form.WTCDateField( {
			name :'birthDateFrom',
			fieldLabel :msg.agefrom,
			anchor :'95%'
		});

		this.birthDateTo = new Ext.form.WTCDateField( {
			name :'birthDateTo',
			anchor :'95%',
			fieldLabel :msg.ageto
		});

		this.searchCriteriaForm = new Ext.form.FormPanel({
			layout :'column',
			labelAlign :'left',
			 bodyStyle: 'padding: 5px 5px 0 5px;',
			defaults : {
				columnWidth :.33,
				labelWidth :110
			},
			items : [
					{
						layout :'form',
						items : [ {
							xtype :'numberfield',
							name :'patientId',
							fieldLabel :msg.patientid,
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'textfield',
							fieldLabel :'Patient name',
							name :'patientName',
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'wtcphonefield',
							name :'phoneNo',
							fieldLabel :msg.phonenumber,
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'optcombo',
							name :'gender',
							mode :'local',
							forceSelection :true,
							store :loadGenderCbx(),
							triggerAction :'all',
							valueField :'code',
							displayField :'description',
							fieldLabel :msg.sex,
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'optcombo',
							name :'maritalStatus',
							mode :'local',
							forceSelection :true,
							store :loadMaritalStatusCbx(),
							triggerAction :'all',
							valueField :'code',
							displayField :'description',
							fieldLabel :'Marital Status',
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'textfield',
							name :'fatherHusbandName',
							fieldLabel :msg.fatherhusbandname,
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items : [ {
							xtype :'optcombo',
							name :'registrationStatus',
							mode :'local',
							forceSelection :true,
							store :loadRegistrationStatusCbx(),
							triggerAction :'all',
							valueField :'code',
							displayField :'description',
							fieldLabel :msg.regstatus,
							anchor :'95%'
						} ]
					},
					{
						layout :'form',
						items :this.regFromDate
					},
					{
						layout :'form',
						items :this.regToDate
					},
					{
						layout :'form',
						items :this.birthDateFrom
					},
					{
						layout :'form',
						items :this.birthDateTo
					},
					{
						layout :'form',
						items :this.lastVisitedFrom
					},
					{
						layout :'form',
						items :this.lastVisitedTo
					},
					{
						layout :'form',
						columnWidth :.66,
						buttons : [ {
							xtype :'button',
							cls :'x-btn-text-icon',
							text :'Search',
							icon :'images/find.png',
							tooltip :msg.findpatient,
							scope:this,
							handler : function() {
								this.searchButtonClicked();
							}
						},{
							xtype :'button',
							cls :'x-btn-text-icon',
							text :'Reset',
							icon :'images/arrow_undo.png',
							scope:this,
							handler : function() {
								this.resetButtonClicked();
							}
						} ]
					}]
		});


		this.gridPanel = new OPD.managePatient.SearchPatientsGridPanel();
		
		this.gridPanel.viewAppointmentsBtn.on('click', function(){
			this.handleViewAppointmentsBtn();
		},this);
		
		this.gridPanel.transferEmergency2NormalBtn.on('click', function(){
			this.emergencyToNormalButtonClicked();
		},this);
		
		this.bottomButtonsPanel = new Ext.Panel( {
			buttonAlign :'right',
			border :false,
			hidden :true,
			buttons : [
			{
				xtype :'button',
				text :'View Appointments',
				scope:this,
				handler : function() {
					this.handleViewAppointmentsBtn();
				}
			}, /*{
				xtype :'button',
				text :'View History',
				handler : function() {
				}
			}*/ {
				xtype :'button',
				text :'Emergency To Normal',
				scope:this,
				handler : function() {
					this.emergencyToNormalButtonClicked();
				}
			} ]
		});


		this.patientMgmntMainPnl = new Ext.Panel( {
			autoScroll :true,
			height:'100%',
			width:'100%',
			frame:true,
			items:[
				this.searchCriteriaForm,
				this.gridPanel.getPanel()
//				this.bottomButtonsPanel				
			]
		});

		this.gridPanel.getPanel().on("render", function(thisForm) {
			this.searchButtonClicked();
		}, this);

		this.regFromDate.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.regToDate.setMinValue(date.getValue());
				}
		}, this);
		
		this.regToDate.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.regFromDate.setMaxValue(date.getValue());
				}
		}, this);

		this.lastVisitedFrom.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.lastVisitedTo.setMinValue(date.getValue());
				}
		}, this);
		
		this.lastVisitedTo.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.lastVisitedFrom.setMaxValue(date.getValue());
				}
		}, this);

		this.birthDateFrom.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.birthDateTo.setMinValue(date.getValue());
				}
		}, this);
		
		this.birthDateTo.on("change", function(date, newValue, oldValue) {
				if (!Ext.isEmpty(date.getValue())) {
					this.birthDateFrom.setMaxValue(date.getValue());
				}
		}, this);

		this.gridPanel.getPanel().on("cellclick", function(thisGrid, rowIndex, columnIndex, eventObj) {
			this.setGridButtonsState(thisGrid.getSelectionModel());
		} , this);
	
		var mainThis = this;
		
		Ext.ux.event.Broadcast.subscribe('reloadPatientStore', function() {
			this.gridPanel.getPanel().getStore().removeAll();
			this.gridPanel.getPanel().getStore().reload();
		}, this, null, mainThis.getPanel() );
		
		
		Ext.ux.event.Broadcast.subscribe('closeRegisterPatientPnl',function(){
			layout.getCenterRegionTabPanel().setActiveTab( mainThis.patientMgmntMainPnl );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadPatientsGrid');
		},this, null , mainThis.getPanel());
		
		this.patientMgmntMainPnl.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

	},
	
	getPanel: function(){
		return this.patientMgmntMainPnl;
	}, 
	
	setGridButtonsState : function(selectionModel){
		var selectedRows = this.gridPanel.getPanel().getSelectionModel().getSelections();
		
		this.gridPanel.getEditBtn().disable();
		this.gridPanel.getPrintCardBtn().disable();
		this.gridPanel.getAttendantCardBtn().disable();
		this.gridPanel.viewAppointmentsBtn.disable();
		this.gridPanel.patientHistoryBtn.disable();
		this.gridPanel.transferEmergency2NormalBtn.disable();
		
		this.bottomButtonsPanel.hide();
		
		if( selectedRows.length == 1){
			this.gridPanel.getEditBtn().enable();
			this.gridPanel.getPrintCardBtn().enable();
			this.gridPanel.getAttendantCardBtn().enable();
			this.gridPanel.viewAppointmentsBtn.enable();
			this.gridPanel.patientHistoryBtn.enable();
			this.gridPanel.transferEmergency2NormalBtn.disable();
			if( selectedRows[0].data.registrationTypeCode == configs.emergencyRegistrationType){
				this.gridPanel.transferEmergency2NormalBtn.enable();
			}
			this.bottomButtonsPanel.show();

		} else if( selectedRows.length > 1){
			this.gridPanel.getEditBtn().disable();
			this.gridPanel.getPrintCardBtn().disable();
			this.gridPanel.getAttendantCardBtn().disable();
			this.gridPanel.viewAppointmentsBtn.disable();
			this.gridPanel.patientHistoryBtn.disable();
			this.gridPanel.transferEmergency2NormalBtn.disable();
			
			this.bottomButtonsPanel.hide();
		}
		
	},

	searchButtonClicked: function(){
		var values = this.searchCriteriaForm.getForm().getValues();
		this.gridPanel.getPanel().getStore().removeAll();

		this.gridPanel.getPanel().getStore().load( {
			params : {start :0, limit :10},
			arg : [
					values['patientId'],
					values['patientName'],
					values['phoneNo'],
					values['gender'],
					values['maritalStatus'],
					values['fatherHusbandName'],
					values['registrationStatus'],
					getStringAsWtcDateFormat(values['registrationFrom']),
					getStringAsWtcDateFormat(values['registrationTo']),
					getStringAsWtcDateFormat(values['birthDateFrom']),
					getStringAsWtcDateFormat(values['birthDateTo']),
					getStringAsWtcDateFormat(values['lastVisitedFrom']),
					getStringAsWtcDateFormat(values['lastVisitedTo'])
			]
		});
		this.regFromDate.setMaxValue(null);
		this.regToDate.setMinValue(null);
		this.lastVisitedFrom.setMaxValue(null);
		this.lastVisitedTo.setMinValue(null);
		this.birthDateFrom.setMaxValue(null);
		this.birthDateTo.setMinValue(null);
		
		this.gridPanel.setGridButtonsInitialState();
	},
	
	resetButtonClicked: function(){
		this.searchCriteriaForm.getForm().reset();
		this.searchButtonClicked();
	},
	emergencyToNormalButtonClicked: function(){
		var selectedRows = this.gridPanel.getPanel().getSelectionModel().getSelections();
		var patientIdsList = [];
		if(selectedRows[0].data.registrationType == 'Emergency'){
			patientIdsList[0] = selectedRows[0].data.patientId;
			PatientManagementController.convertEmergency2Normal(patientIdsList,{
																	callback : this.emergencyToNormalBtnCallBack,
																	callbackScope : this
																	}
																);
		} else {
			Ext.Msg.show({
				msg: msg.emergencytonormalerrormessage,
				icon : Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK
			});
		}
	},
	
	emergencyToNormalBtnCallBack : function(){
		var mainThis = this;
		Ext.Msg.show({
			msg: "Patient status has been updated from emergency to normal ..!",
			icon : Ext.MessageBox.INFO,
			buttons: Ext.Msg.OK,
			fn: function(){
				mainThis.gridPanel.getPanel().getStore().reload();
			}
		});
	},
	
	handleViewAppointmentsBtn : function(){
		this.selectedRows = this.gridPanel.getPanel().getSelectionModel().getSelections();

		if(!Ext.isEmpty(this.selectedRows)&& this.selectedRows.length == 1) {
			var patientId = this.selectedRows[0].data.patientId;
			this.appointmentGrid = new OPD.manageAppointment.ManageAppointmentGrid({isSingleSelection: true}).getPanel();
			
			this.appointmentGrid.getColumnModel().setHidden( 0, true );
			this.window = new Ext.Window({
				height:'80%',
	          	width:'95%',
				modal:true,
				closable:true,
				title:'View appointments of'+ " " +this.selectedRows[0].data.name,
				resizable:false,
				items:[this.appointmentGrid]
			});
			
			this.appointmentGrid.getStore().load({params : {start : 0,limit : 10},
				arg : [ 
					null,
					null,
					numberToString( patientId ),
					null,
					null,
					null,
					null,
					null,
					null]
			});
			
			this.window.show();
		}
	}
});
