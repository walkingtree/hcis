Ext.namespace("administration.service_group_package.addPackage");

administration.service_group_package.addPackage.ConfigurePackage = Ext.extend(Object, {
	constructor : function(config) {
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		Ext.apply(this, config );
		this.configurePackagePkgOverrideLvl = new administration.service_group_package.addPackage.ConfigurePackagePkgOverrideLvl(config);
		this.packageOverridePnl = this.configurePackagePkgOverrideLvl.getPanel();
		
		this.configurePackageSvcOverrideLvl = new administration.service_group_package.addPackage.ConfigurePackageSvcOverrideLvl(config);
		this.serviceOverridePnl = this.configurePackageSvcOverrideLvl.getPanel();
		this.serviceOverridePnl.hide();
		
		this.packageCodeTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.code,
							name:'packageId',
							anchor:'90%',
							required:true,
							allowBlank:false,
							maxLength: 15
		});
		
		this.packageNameTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.name,
							name:'name',
							anchor:'90%',
							required:true,
							allowBlank:false,
							maxLength: 45
		});

		this.effectiveFromDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveFrom,
							name:'effectiveFromDt',
							anchor:'70%',
							required:true,
							allowBlank:false,
							emptyText:svcAndGrpAndPkgMsg.defaultDateFormat
		});
		
		this.effectiveToDtFld = new Ext.form.WTCDateField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveTo,
							name:'effectiveToDt',
							anchor:'70%',
							emptyText:svcAndGrpAndPkgMsg.defaultDateFormat
		});

		this.statusCombo = new Ext.form.ComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.status,
							mode:'local',
							displayField:'description',
							store: loadPackageServiceStatusCbx(),
							hiddenName:'servicePackageStatus',
							valueField:'code',
							triggerAction:'all',
							anchor:'70%',
							allowBlank:false,
							forceSelection : true,
							required:true,
							emptyText: svcAndGrpAndPkgMsg.selectMsg
		});
		
		Ext.ux.event.Broadcast.subscribe('setDefaultValues', function(){
			mainThis.setDefaultValues();
		}, this, null, mainThis.getPanel());
		
		this.packageDescTextArea = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.description,
							name:'description',
							height:60,
							anchor:'90%',
							required:true,
							allowBlank:false
		});
		
		this.chargeOverrideLevelGd = new Ext.form.RadioGroup({
			name:'chargeOverrideLevel',
			columns: [.30, .30],
       		items: [
                    {boxLabel: "Package level discounts", name: 'slctdChargeOvrdLvlInd', inputValue: 1,checked: true},
                    {boxLabel: "Service level discounts", name: 'slctdChargeOvrdLvlInd', inputValue: 2 }
       			   ],
      		listeners:{
      			change : {
      				fn : function(radioGroup, value){
          				if(value === '1'){
//          					Ext.Msg.show({
//          						msg: svcAndGrpAndPkgMsg.doUWantToSwitchFromPacToSvc,
//          						fn: function( btnValue ){
//          							if( btnValue == configs.yes){
          							mainThis.packageOverridePnl.show();
	              					mainThis.packageOverridePnl.doLayout();
	              					if( !Ext.isEmpty(config.servicePackageBM) && config.servicePackageBM.chargeOverrideLevel == configs.overrideLevel_Service ){
	              						mainThis.configurePackageSvcOverrideLvl.resetServiceLevelPanels( config );
	              					}else{
	              						mainThis.configurePackageSvcOverrideLvl.resetServiceLevelPanels( {} );
	              					}
	              					mainThis.serviceOverridePnl.hide();
//          							}else{
//          								this.chargeOverrideLevelGd.setValue('1');
//          							}
//          						},
//          						buttons: Ext.Msg.YESNO,
//	   						    icon: Ext.MessageBox.QUESTION
//          					});	
          					
          				} else if(value === '2'){
//          					Ext.Msg.show({
//          						msg: svcAndGrpAndPkgMsg.doUWantToSwitchFromPacToSvc,
//          						fn: function( btnValue ){
//          							if( btnValue == configs.yes){
          								mainThis.configurePackageSvcOverrideLvl.setDefaultValues();
		              					mainThis.packageOverridePnl.hide();
		              					if( !Ext.isEmpty(config.servicePackageBM) && config.servicePackageBM.chargeOverrideLevel == configs.overrideLevel_Package ){
		              						mainThis.configurePackagePkgOverrideLvl.resetpackageLevelPanels( config );
		              					}else{
		              						mainThis.configurePackagePkgOverrideLvl.resetpackageLevelPanels( {} );
		              					}
		              					
		              					mainThis.serviceOverridePnl.show();
		              					mainThis.serviceOverridePnl.doLayout();
//          							}else{
//          								this.chargeOverrideLevelGd.setValue('2');
//          							}
//          						},
//          						buttons: Ext.Msg.YESNO,
//	   						    icon: Ext.MessageBox.QUESTION
//          					});
          					
          				}
      				}
      			}
      		}
	 	});
	 	
		this.chargeOverriderLvlFieldSet = new Ext.form.FieldSet({
			title : svcAndGrpAndPkgMsg.chargeOvrdLvl,
			collapsible : false,
			labelAlign :'left',
			autoHeight : true,
			border : true,
			width: '73%',
			items : [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : 1
						},
						items: this.chargeOverrideLevelGd
					}
				]
		});
		
		this.saveBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnSave,
			iconCls : 'save_btn',
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.savePackage,
			handler: function(){
				this.saveButtonClicked(config);
			}
		});
		
		this.resetBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.resetPackage,
			handler: function(){
				this.resetButtonClicked(config);
			}
		});
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			width: '75%',
			buttons:[
				this.saveBtn,
				this.resetBtn
			]
		});	
		
		this.configurePackagePanel = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				border : false,
				monitorValid: true,
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.packageCodeTxf
						},{
						    columnWidth : .40,
						    items:this.packageNameTxf
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.effectiveFromDtFld
						},{
						    columnWidth : .40,
						    items:this.effectiveToDtFld
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .40,
							items:this.statusCombo
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .75,
							items:this.packageDescTextArea
						}]
					},
					this.chargeOverriderLvlFieldSet,
					this.packageOverridePnl,
					this.serviceOverridePnl,
					this.buttonPanel
			    ]
		});

		this.configurePackagePanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.saveBtn.enable();
			} else {
				this.saveBtn.disable();
			}
		}, this);

		this.initListeners(mainThis);
	
		if(config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.packageCodeTxf.disable();
		}
		
		Ext.ux.event.Broadcast.subscribe('getFocusInConfigurePackage', function(){
			mainThis.packageCodeTxf.focus();
		}, this, null, mainThis.getPanel());
	},
	
	initListeners: function( mainThis ){
	
		this.packageCodeTxf.on('blur', function( comp ){
			ServiceManager.isServicePackageExist( comp.getValue(), {
				callback: function( flag ){
					if( flag ){
						Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.packageExists,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	 mainThis.packageCodeTxf.reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		
		this.effectiveFromDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.effectiveToDtFld.setMinValue(dateToBeSet.getValue());
   			}
		}, this);

		this.effectiveToDtFld.on('change', function(dateToBeSet) {
   			if(!Ext.isEmpty(dateToBeSet.getValue())){
   				this.effectiveFromDtFld.setMaxValue(dateToBeSet.getValue());
   			}
		}, this);

	},
	
	saveButtonClicked : function( config ){
		var mainThis = this;
		if(this.configurePackagePanel.getForm().isValid()){
			var inputValues = this.configurePackagePanel.getForm().getValues();
			var authorisedUserInfo = getAuthorizedUserInfo();
			
			var packageBM = {
					packageId: inputValues.packageId,
					name: inputValues.name,
					description: inputValues.description,
					servicePackageStatus: { code: inputValues.servicePackageStatus },
					createdBy: authorisedUserInfo.userName,
					effectiveFromDt: this.getDate( inputValues.effectiveFromDt ),
					effectiveToDt: this.getDate( inputValues.effectiveToDt )
     			};
     			if( inputValues.slctdChargeOvrdLvlInd == '1'){
     				var discountInfoForPac = this.configurePackagePkgOverrideLvl.getdiscountInfo();
					packageBM.chargeOverrideLevel = configs.overrideLevel_Package;
					packageBM.discountAmountPct = !Ext.isEmpty( discountInfoForPac.discountPctAmt )?parseFloat( discountInfoForPac.discountPctAmt ):0.00;
					packageBM.packageCharge = !Ext.isEmpty( discountInfoForPac.packageCharge )?parseFloat( discountInfoForPac.packageCharge ):0.00;
					packageBM.discountType = inputValues.discountTypeForPac;
					packageBM.effectiveCharge = parseFloat( discountInfoForPac.effectiveChargeForPac);
					packageBM.serviceBMList = this.configurePackagePkgOverrideLvl.getData();
					
				}else{
					var discountInfoForSvc = this.configurePackageSvcOverrideLvl.getdiscountInfo();
					packageBM.discountAmountPct = Ext.isEmpty( discountInfoForSvc.discountAmountPct )?0:parseFloat( discountInfoForSvc.discountAmountPct);
					packageBM.effectiveCharge = Ext.isEmpty( discountInfoForSvc.effectiveCharge )?0:parseFloat( discountInfoForSvc.effectiveCharge );
					packageBM.packageCharge = (Ext.isEmpty( discountInfoForSvc.discountAmountPct )&& Ext.isEmpty( discountInfoForSvc.effectiveCharge )) ?0:packageBM.effectiveCharge + packageBM.discountAmountPct;
					packageBM.chargeOverrideLevel = configs.overrideLevel_Service;
					packageBM.serviceBMList = this.configurePackageSvcOverrideLvl.getData();
				}
				
				if( Ext.isEmpty( packageBM.serviceBMList )){
					Ext.Msg.show({
					   msg: svcAndGrpAndPkgMsg.atleastOneServiceAssociationIsRequired,
					   buttons: Ext.Msg.OK,
					   fn: function(){},
					   icon: Ext.MessageBox.ERROR
					});
					return;
				}
     			
			if (!Ext.isEmpty(config) && (config.mode == svcAndGrpAndPkgMsg.addMode)) {
				ServiceManager.addServicePackage( packageBM , function(){
						Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.addServicePackageSuccessMsg,
						   buttons: Ext.Msg.OK,
						   fn: function( ){
						   if( mainThis.isPopUp == true){
							   layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
						   		Ext.ux.event.Broadcast.publish('closeConfigurePackageWindow');
							}else{
								mainThis.resetButtonClicked( config );
							}
							loadServicePackageCbx().reload();
						   },
						   icon: Ext.MessageBox.INFO
						});	
				});
			}
			
			if (!Ext.isEmpty(config) && (config.mode == svcAndGrpAndPkgMsg.editMode)) {
				packageBM.packageId = config.servicePackageBM.packageId;
				ServiceManager.modifyServicePackage( packageBM ,{
					callback:  function(){
						Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.editServicePackageSuccessMsg,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   if( mainThis.isPopUp == true){
							   layout.getCenterRegionTabPanel().remove( mainThis.configurePackagePanel, true );
						   		Ext.ux.event.Broadcast.publish('closeConfigurePackageWindow');
							}else{
								mainThis.resetButtonClicked( config );
							}
							loadServicePackageCbx().reload();
						   },
						   icon: Ext.MessageBox.INFO
						});	
					},
					callbackScope: mainThis
				});
			}
					
		} else {
			error(svcAndGrpAndPkgMsg.invalidData);
		}	
	},
	
	resetButtonClicked : function( config ){
		this.configurePackagePanel.getForm().reset();
		this.configurePackagePkgOverrideLvl.resetpackageLevelPanels( {} );
		this.configurePackageSvcOverrideLvl.resetServiceLevelPanels( {} );
		if(config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.loadData(config);
		}
	},

	closeConfigurePackageWindow : function(){
//		Ext.ux.event.Broadcast.publish('closeConfigurePackageWindow');
		Ext.ux.event.Broadcast.publish('reloadSearchPackagesGrid');
		Ext.ux.event.Broadcast.publish('resetBtnSearchPackages');
	},

	getPanel : function() {
		return this.configurePackagePanel;
	},
	
	getDate : function(dateInput){
		return Ext.isEmpty(dateInput)||dateInput == 'undefined' ? null : Date.parseDate(dateInput,'d/m/Y');
	},
	
	loadData: function( config ){
		var servicePackageBm = config.servicePackageBM;
		var servicepackageBm = {
			packageId: servicePackageBm.packageId,
			name: servicePackageBm.name,
			effectiveFromDt: servicePackageBm.effectiveFromDt,
			effectiveToDt: servicePackageBm.effectiveToDt,
			servicePackageStatus: !Ext.isEmpty(servicePackageBm.servicePackageStatus)?servicePackageBm.servicePackageStatus.code:'',
			description: servicePackageBm.description,
			slctdChargeOvrdLvlInd: servicePackageBm.chargeOverrideLevel == configs.overrideLevel_Package? '1': '2'
		};
		this.configurePackagePanel.getForm().setValues( servicepackageBm );
		
		if( servicePackageBm.chargeOverrideLevel == configs.overrideLevel_Package ){
			this.configurePackagePkgOverrideLvl.loadStoreData( config );
		}else if( servicePackageBm.chargeOverrideLevel == configs.overrideLevel_Service  ){
			this.configurePackageSvcOverrideLvl.loadStoreData( config );
		}
	},
	setDefaultValues: function(){
//		var record = this.statusCombo.store.getAt( 0 );
//		this.statusCombo.setValue( record.data.code );
		
	}
});