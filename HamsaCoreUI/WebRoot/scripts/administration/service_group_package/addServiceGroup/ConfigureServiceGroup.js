Ext.namespace("administration.service_group_package.addServiceGroup");

administration.service_group_package.addServiceGroup.ConfigureServiceGroup = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.QuickTips.init();
		
		Ext.apply(this, config);
		var mainThis = this;
		this.groupCodeTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.code,
							name:'serviceGroupCode',
							anchor:'90%',
							required:true,
							allowBlank:false,
							maxLength: 15
		});
		
		this.groupNameTxf = new Ext.form.TextField({
							fieldLabel:svcAndGrpAndPkgMsg.name,
							name:'groupName',
							anchor:'90%',
							required:true,
							allowBlank:false,
							maxLength: 45
		});

		this.parentGroupCombo = new Ext.form.OptComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.parentGroup,
							mode:'local',
							store: loadAddServiceGroupCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'parentGroup',
							anchor:'90%',
							emptyText: svcAndGrpAndPkgMsg.selectMsg
		});

		this.saveBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnSave,
			iconCls : 'save_btn',
			scope:this,
			disabled:true,
			tooltip: svcAndGrpAndPkgMsg.saveServiceGroup,
			handler: function(){
				this.saveButtonClicked(config);
			}
		});
		
		this.resetBtn =new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			tooltip: svcAndGrpAndPkgMsg.resetServiceGroup,
			scope:this,
			handler: function(){
				this.resetButtonClicked(config);
			}
		});
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[
				this.saveBtn,
				this.resetBtn
			]
		});	
	    
	    this.servicesSelectionGridsPanel = new administration.service_group_package.addServiceGroup.ServiceSelectionPanel( config );
	    
		this.configureServiceGroupPanel = new Ext.form.FormPanel({
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
							columnWidth : .33,
							items:this.groupCodeTxf
						},{
						    columnWidth : .33,
						    items:this.groupNameTxf
					    },{
							columnWidth : .33,
							items:this.parentGroupCombo
						}]
					},
					this.servicesSelectionGridsPanel.getPanel(),
					this.buttonPanel
			    ]
			    
			    
		});
		
		Ext.ux.event.Broadcast.subscribe('getFocusInConfigureServiceGroup', function(){
			mainThis.getFocus();
		}, this, null, mainThis.getPanel());

				
		this.initListeners( mainThis );
		
		if(config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.groupCodeTxf.disable();
		}
	},
	
	initListeners: function( mainThis ){
	
//		this.groupCodeTxf.on('focus', function(valueToBeSet) {
//			
//			if(serviceGroupTypesStore.getCount() > 0){
//				valueToBeSet = serviceGroupTypesStore.getAt(0);
//				this.parentGroupCombo.setValue(valueToBeSet.data.code);			
//			}
//
//			this.groupCodeTxf.events['focus'].clearListeners();
//			
//		}, this);
		
		this.groupCodeTxf.on('blur', function( comp ){
			ServiceManager.isServiceGroupExist( comp.getValue(), {
				callback: function( flag ){
					if( flag ){
						Ext.Msg.show({
						   msg: svcAndGrpAndPkgMsg.serviceGroupExists,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	 mainThis.groupCodeTxf.reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		
		this.configureServiceGroupPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.saveBtn.enable();
			} else {
				this.saveBtn.disable();
			}
		}, this);
		// handleing buttons state
		this.servicesSelectionGridsPanel.gridListeners();
	},

	saveButtonClicked : function(config){
		var mainThis = this;
		if(this.configureServiceGroupPanel.getForm().isValid()){
			var valueMap = this.configureServiceGroupPanel.getForm().getValues();
			
			var serviceGroupBM = {
				serviceGroupCode: valueMap.serviceGroupCode,
				groupName: valueMap.groupName,
				parentGroup:{code: valueMap.parentGroup},
				serviceSummaryList:this.servicesSelectionGridsPanel.getAssignedServices(),
				createdBy: getAuthorizedUserInfo().userName
 			}
     			
			if (!Ext.isEmpty(config) && (config.mode == svcAndGrpAndPkgMsg.addMode)) {
				ServiceManager.addServiceGroup(serviceGroupBM, function(){
					Ext.Msg.show({
					   msg: svcAndGrpAndPkgMsg.addServiceGroupSuccessMsg,
					   buttons: Ext.Msg.OK,
					   fn: function(btnValue){
					   	if(mainThis.isPopUp == true){
							mainThis.closeConfigureServiceGroupWindow();	
						}else{
							mainThis.resetButtonClicked(config);
						}
						loadAddServiceGroupCbx().reload();
					   },
					   icon: Ext.MessageBox.INFO
					});
				});
			}
			
			if (!Ext.isEmpty(config) && (config.mode == svcAndGrpAndPkgMsg.editMode)) {
				
				serviceGroupBM.serviceGroupCode = config.selectedRow.serviceGroupCode;
				
				ServiceManager.modifyServiceGroup(serviceGroupBM, function(ServiceGroupBM){
					Ext.Msg.show({
					   msg: svcAndGrpAndPkgMsg.editServiceGroupSuccessMsg,
					   buttons: Ext.Msg.OK,
					   fn: function(btnValue){
					   	if(mainThis.isPopUp == true){
							mainThis.closeConfigureServiceGroupWindow();	
						}else{
							mainThis.resetButtonClicked(config);
						}
						loadAddServiceGroupCbx().reload();
					   },
					   icon: Ext.MessageBox.INFO
					});
				});
			}
					
		} else {
			error(svcAndGrpAndPkgMsg.invalidData);
		}	
	},
	
	resetButtonClicked : function(config){
		this.configureServiceGroupPanel.getForm().reset();
		this.servicesSelectionGridsPanel.resetData();
		
		if(config.mode ==svcAndGrpAndPkgMsg.editMode){
			this.loadData(config);
		}
	},	

	getPanel : function() {
		return this.configureServiceGroupPanel;
	},
	getFocus: function() {
		this.groupCodeTxf.focus();
	},
	closeConfigureServiceGroupWindow: function(){
		var mainThis = this;
		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
		Ext.ux.event.Broadcast.publish('closeConfigureServiceGroupWindow');
		Ext.ux.event.Broadcast.publish('reloadSearchServiceGroupsGrid');
	},
	loadData: function(config){
		this.configureServiceGroupPanel.getForm().setValues(config.selectedRow);
		this.servicesSelectionGridsPanel.loadDataToGrids(config.selectedRow.serviceSummaryList);
	}
});

