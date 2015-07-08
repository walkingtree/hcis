Ext.namespace("administration.service_group_package.addService");

administration.service_group_package.addService.ConfigureService = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.QuickTips.init();
		Ext.apply(this, config);
		var mainThis = this;
		
		this.isLabTest = false;
		this.isSurgery = false;
		
		
		this.widgets = new administration.service_group_package.addService.Widgets();

		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			width: '80%',
			buttons:[
				this.widgets.getSaveBtn(),
				this.widgets.getResetBtn()
			]
		});	
		
		this.specificPanel = new Ext.Panel( {
							width : '80%',
							labelWidth :100
						});
		
		//Add the lab test panel as specific panel if LIMS is integrated and 
		if(config.mode == svcAndGrpAndPkgMsg.editMode ){
			
			if( getIntegratedLIMSValue() == "Y" && 
					config.selectedRow.serviceType == svcAndGrpAndPkgMsg.serviceTypeLaboratory){
			
				if(!Ext.isEmpty(this.specificPanel) ){
					this.specificPanel.removeAll();
				}
				this.addLabTestPanel = new LIMS.labTest.configure.AddLabTest({mode:svcAndGrpAndPkgMsg.editMode});
				this.addLabTestPanel.labelWidth = 100;
				this.specificPanel.add(this.addLabTestPanel);
				this.widgets.getServiceTypeCbx().hideTrigger = true;
				this.widgets.getServiceTypeCbx().disable(); 
				this.isLabTest = true;
			}
			else if( getIntegratedOTValue() === "Y" && 
						config.selectedRow.serviceType === svcAndGrpAndPkgMsg.serviceTypeSurgery){
				this.setSurgeryPanel( config.selectedRow.serviceCode );
			}
		}
		
		
		//Select listener for service type
		this.widgets.getServiceTypeCbx().on('select',function(combo,record,index){
			this.serviceTypeChanged(combo,record,index);
		},this);
		
		
		//save button listener
		this.widgets.getSaveBtn().on('click', function(comp,eventObj){
				this.saveButtonClicked(config);
		},this);
		
		// reset button listener
		this.widgets.getResetBtn().on('click', function(){
			this.resetButtonClicked(config);
		},this);
		
		
		
		this.configureServicePanel = new Ext.form.FormPanel({
				frame : false,
				autoHeight : true,
				border : false,
				monitorValid: true,
				autoScroll:false,
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items: this.widgets.getServiceCodeTxf()
						},{
						    columnWidth : .40,
						    items:this.widgets.getServiceNameTxf()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.widgets.getEffectiveFromDtFld()
						},{
						    columnWidth : .40,
						    items:this.widgets.getEffectiveToDtFld()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.widgets.getStatusCombo()
						},{
							columnWidth : .40,
							items:this.widgets.getServiceTypeCbx()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							layout : 'column',
							columnWidth : .40,
							items :[ 
							{
								layout: 'form',
								columnWidth : .65,
								items:this.widgets.getChargeNbrField()
							},
							{
								layout: 'form',
								columnWidth : .30,
								items:this.widgets.getConfigurePriceBtn()
								
							}]
							
						},
						{
							columnWidth : .40,
							items:this.widgets.getDepositAmountField()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.widgets.getServiceGroupCombo()
						},{
							columnWidth : .40,
							items:this.widgets.getDepartmentCombo()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .80,
							items:this.widgets.getServiceDescTextArea()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .80,
							items:this.widgets.getProcedureHtmlEditor()
						}]
					},
					
					this.specificPanel,
					
					this.buttonPanel
					
			    ]
		});
		
		Ext.ux.event.Broadcast.subscribe('setDefaultValuesInService', function(){
			mainThis.setDefaultValues();
		},this,null,mainThis.getPanel());
		
		Ext.ux.event.Broadcast.subscribe('getFocusInConfigureService', function(){
			mainThis.getFocus();
		}, this, null, mainThis.getPanel());

		
		this.configureServicePanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.widgets.getSaveBtn().enable();
			} else {
				this.widgets.getSaveBtn().disable();
			}
		}, this);
		
		this.initListeners( mainThis );
		
		if(config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.widgets.getServiceCodeTxf().disable();
			this.widgets.getChargeNbrField().disable();
			this.widgets.getConfigurePriceBtn().show();
			this.widgets.getServiceTypeCbx().disable();
		}
	},
	
	initListeners: function(mainThis){
	
		this.widgets.getEffectiveFromDtFld().on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.widgets.getEffectiveToDtFld().setMinValue(dateToBeSet.getValue());
   			}
		}, this);

		this.widgets.getEffectiveToDtFld().on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.widgets.getEffectiveFromDtFld().setMaxValue(dateToBeSet.getValue());
   			}
		}, this);

		this.widgets.getStatusCombo().on('focus',function(){
			var record = this.widgets.getStatusCombo().store.getAt( 0 );
			this.widgets.getStatusCombo().setValue( record.data.code );
		},this);
		
		this.widgets.getServiceCodeTxf().on('blur', function( comp ){
			ServiceManager.isServiceExist( comp.getValue(), {
				callback: function( flag ){
					if( flag ){
						Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.serviceExists,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	 mainThis.widgets.getServiceCodeTxf().reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		
		this.widgets.getConfigurePriceBtn().on('click',function(){
			
			var serviceCode = this.widgets.getServiceCodeTxf().getValue();
			var currentCharge = this.widgets.getChargeNbrField().getValue();
			
			var priceDetailsPanel = new administration.service_group_package.priceUpdate.PriceDetailsPanel(
					{ serviceCode : serviceCode,chargeNbrField : this.widgets.getChargeNbrField() });
			
			priceDetailsPanel.loadGridData( serviceCode, currentCharge );
			
			mainThis.priceDetailWindow = new Ext.Window({
				title: 'Price details for ' +serviceCode,
				items:priceDetailsPanel,
				frame:true,
				height:'40%',
				width:'50%'
			});
			mainThis.priceDetailWindow.show();
		},this);
	},
	
	saveButtonClicked : function( config ){
		
		var inputValues = this.configureServicePanel.getForm().getValues();
		var userInfo = getAuthorizedUserInfo();
		var mainThis = this;
		
		serviceBM = {
			serviceCode : inputValues['serviceCode'],
			serviceName : inputValues['serviceName'],
			effectiveFromDate : this.getDate(inputValues['effectiveFromDate']),
			effectiveToDate : this.getDate(inputValues['effectiveToDate']),
			serviceStatus : {code : inputValues['serviceStatus']},
			serviceCharge : parseFloat( this.widgets.getChargeNbrField().getValue() ),
			depositAmount : parseFloat(inputValues['depositAmount']),
			serviceGroup : {code : inputValues['serviceGroup']},
			department : {code : inputValues['department']},
			serviceDesc : inputValues['serviceDesc'],
			serviceProcedure : inputValues['serviceProcedure'],
			personId : userInfo.userName,
			serviceType : {code :mainThis.widgets.getServiceTypeCbx().getValue() }
		}
    			
		if (!Ext.isEmpty(config)){
		 	if(config.mode == svcAndGrpAndPkgMsg.addMode) {
				
		 		
		 	if( getIntegratedLIMSValue() == "Y" && this.isLabTest)	{
		 		
		 		
		 		LabTestBM = this.addLabTestPanel.getLabTestBM();
		 		
		 		
		 		LabTestManager.addLabTest(
						LabTestBM,
						serviceBM,
						function(){
							layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
							Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
//							Ext.Msg.show({
//							   msg: svcAndGrpAndPkgMsg.addServiceSuccessMsg,
//							   buttons: Ext.Msg.OK,
//							   fn: function(){
//								if(config.isPopUp){
//									mainThis.closeConfigureServiceWindow();	
//								}else{
//									mainThis.resetButtonClicked();
//								}
//								loadServicesCbx().reload();
//							   },
//							   icon: Ext.MessageBox.INFO
//							});
						}
					);	
		 		
		 	}else if(this.isSurgery){
		 		var surgeryBM = this.surgeryPanel.getSurgeryBM();
		 		surgeryBM.serviceBM = serviceBM;
		 		SurgeryManager.addSurgery( surgeryBM ,function( ){
 					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
 					Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
		 		});
		 	}else{
		 		ServiceManager.addService(
					serviceBM,
					function(){
						layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
						Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
//						Ext.Msg.show({
//						   msg: svcAndGrpAndPkgMsg.addServiceSuccessMsg,
//						   buttons: Ext.Msg.OK,
//						   fn: function(){
//							if(config.isPopUp){
//								mainThis.closeConfigureServiceWindow();	
//							}else{
//								mainThis.resetButtonClicked();
//							}
//							loadServicesCbx().reload();
//						   },
//						   icon: Ext.MessageBox.INFO
//						});
					}
				);
		 	}
		}

		 	if(config.mode == svcAndGrpAndPkgMsg.editMode) {
		 			 		
		 		
		 		serviceBM.serviceCode = config.selectedRow.serviceCode;
		 		
	 			if( getIntegratedLIMSValue() == "Y" && this.isLabTest)	{
			 		LabTestBM = this.addLabTestPanel.getLabTestBM();
			 		
			 		LabTestManager.modifyLabTest(
			 				LabTestBM,
			 				serviceBM,
							function(){
			 					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
			 					Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
//								Ext.Msg.show({
//								   msg: svcAndGrpAndPkgMsg.editServiceSuccessMsg,
//								   buttons: Ext.Msg.OK,
//								   fn: function(){
//									if(config.isPopUp){
//										mainThis.closeConfigureServiceWindow();	
//									}else{
//										mainThis.resetButtonClicked();
//									}
//									loadServicesCbx().reload();
//								   },
//								   icon: Ext.MessageBox.INFO
//								});
							});
	 			}else if( this.isSurgery ){
			 		var surgeryBM = this.surgeryPanel.getSurgeryBM();
			 		serviceBM.serviceCode =config.selectedRow.serviceCode;
			 		surgeryBM.serviceBM = serviceBM;
			 		
			 		SurgeryManager.modifySurgery( surgeryBM ,function( ){
	 					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
	 					Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
			 		});

	 			}else{
					ServiceManager.modifyService(
							serviceBM,
							function(){
								layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
								Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
//								Ext.Msg.show({
//								   msg: svcAndGrpAndPkgMsg.editServiceSuccessMsg,
//								   buttons: Ext.Msg.OK,
//								   fn: function(){
//									if(config.isPopUp){
//										mainThis.closeConfigureServiceWindow();	
//									}else{
//										mainThis.resetButtonClicked();
//									}
//									loadServicesCbx().reload();
//								   },
//								   icon: Ext.MessageBox.INFO
//								});
							}
						);
	 			}

			}
			
		}
	},
	
	
	serviceTypeChanged : function(combo,record,index){
		
		var serviceType = record.data.code;
		
		this.isLabTest = false;
		this.isSurgery = false;
		
//		if(!Ext.isEmpty(this.specificPanel) ){
//			this.specificPanel.removeAll();
//		}
//
//		if(serviceType == svcAndGrpAndPkgMsg.serviceTypeLaboratory){
//			this.isLabTest = true;
//			this.addLabTestPanel = new LIMS.labTest.configure.AddLabTest();
//			this.specificPanel.add(this.addLabTestPanel);
//			this.specificPanel.doLayout();
//		}
		
		this.setSpecificPanel(serviceType);
		
	},
	
	//this method is meant  for code re-usability, sets the specifPanel's status
	setSpecificPanel : function(serviceType){
		
		if(!Ext.isEmpty(this.specificPanel) ){
			this.specificPanel.removeAll();
		}

		if(serviceType == svcAndGrpAndPkgMsg.serviceTypeLaboratory){
			this.isLabTest = true;
			this.isSurgery = false;
			this.addLabTestPanel = new LIMS.labTest.configure.AddLabTest();
			this.specificPanel.add(this.addLabTestPanel);
			this.specificPanel.doLayout();
		}
		else if( getIntegratedOTValue() === "Y"  && 
					serviceType === svcAndGrpAndPkgMsg.serviceTypeSurgery){
			var mainThis = this;
			this.isSurgery = true;
			this.isLabTest = false;
			SurgeryManager.getSurgeryStatusTimeDetail(null,function( surgeryStatusTimeDetailList ){
				mainThis.surgeryPanel = new OT.configureSurgery.ConfigureSurgery({surgeryStatusTimeDetailList :  surgeryStatusTimeDetailList,
																					widgets : mainThis.widgets});
				mainThis.specificPanel.add( mainThis.surgeryPanel );
				mainThis.specificPanel.doLayout();
			});
		}
	},
	
	getDate : function(dateInput){
		return dateInput == 'undefined' ? null : Date.parseDate(dateInput,'d/m/Y');
	},
	
	resetButtonClicked : function(config){
//		this.initListeners();
		if( this.isSurgery && config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.surgeryPanel.reset();
		}
		if( !Ext.isEmpty(config) && config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.loadData(config);
		}else{
			
			this.resetConfigureServicePanel();
			
//			this.configureServicePanel.getForm().reset();
			this.setDefaultValues();
		}
	},

	loadData : function(config) {
						var slctdRow = config.selectedRow;
						this.configureServicePanel.getForm().setValues(slctdRow);
						this.widgets.getEffectiveToDtFld().setMinValue(slctdRow.effectiveFromDate);

						if (slctdRow.serviceType == svcAndGrpAndPkgMsg.serviceTypeLaboratory) {

							LabTestManager.getLabTestBM(this.widgets.getServiceCodeTxf().getValue(),
								{
									callback : function(bm) {
	
										if (!Ext.isEmpty(this.specificPanel)) {
	
											this.addLabTestPanel.loadData(bm);
										}
	
									},
									scope : this
								});
						}
					}, 

	closeConfigureServiceWindow : function(){
		Ext.ux.event.Broadcast.publish('closeConfigureServiceWindow');
		Ext.ux.event.Broadcast.publish('reloadSearchServicesGrid');
	},

	getPanel : function() {
		return this.configureServicePanel;
	},
	getFocus: function(){
		this.widgets.getServiceCodeTxf().focus();
	},
	setDefaultValues: function(){
		
		var record = this.widgets.getStatusCombo().store.getAt( 0 );
		this.widgets.getStatusCombo().setValue( record.data.code );
		
		this.widgets.getServiceGroupCombo().setValue( configs.defaultServiceGroup );
		this.widgets.getEffectiveFromDtFld().setValue( new Date() );	 
		this.widgets.getEffectiveToDtFld().setMinValue( new Date() );
		
		this.setSpecificPanel("");
	},
	
	setSurgeryPanel : function( surgeryCode ){
		this.isSurgery = true;
		this.surgeryCode = surgeryCode;
		if(!Ext.isEmpty(this.specificPanel) ){
			this.specificPanel.removeAll();
		}
		this.widgets.getServiceTypeCbx().hideTrigger = true;
		this.widgets.getServiceTypeCbx().disable(); 
		var mainThis = this;
		this.isSurgery = true;
		SurgeryManager.getSurgeryForCode(surgeryCode ,function( surgeryBM ){
			mainThis.surgeryPanel = new OT.configureSurgery.ConfigureSurgery({surgeryStatusTimeDetailList :  surgeryBM.surgeryStatusTimeBMList,
																				widgets : mainThis.widgets,
																				totalSurgeryTime : surgeryBM.totalTimeRequired * 1});
			mainThis.surgeryPanel.loadData( surgeryBM );
			mainThis.specificPanel.add( mainThis.surgeryPanel );
			mainThis.specificPanel.doLayout();
		});

	},
	
	resetConfigureServicePanel : function(){
		this.widgets.getServiceCodeTxf().setValue("");
		this.widgets.getServiceNameTxf().setValue("");
		this.widgets.getEffectiveFromDtFld().setValue("");
		this.widgets.getEffectiveToDtFld().setValue("");
		this.widgets.getStatusCombo().setValue("");
		this.widgets.getServiceTypeCbx().setValue("");
		this.widgets.getChargeNbrField().setValue("");
//		this.widgets.getConfigurePriceBtn().setValue("");
		this.widgets.getServiceGroupCombo().setValue("");
		this.widgets.getDepartmentCombo().setValue("");
		this.widgets.getServiceDescTextArea().setValue("");
		this.widgets.getProcedureHtmlEditor().setValue("");
		
		
	}
});
