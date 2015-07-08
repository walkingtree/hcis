Ext.namespace("administration.addBed");

administration.addBed.Bed = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		
		if(Ext.isEmpty(config)){
			config={};
		}
		var bedFeatureConfig ={
			data: bedFeaturesStore.data.items
		};
		if(config.mode=='edit'){
			bedFeatureConfig.assignedSpecialFeaturesList = config.assignedSpecialFeaturesList;
			bedFeatureConfig.mode = config.mode;
		}
		this.bedFeaturesPanel = new administration.addBed.BedFeaturesPanel(bedFeatureConfig);
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				disabled: true,
				handler: function(){
					var mainThis =this;
					if(!this.bedPanel.getForm().isValid()){
						Ext.Msg.show({
							msg: 'Enter valid data..!',
							icon : Ext.MessageBox.ERROR,
							buttons: Ext.Msg.OK
						});
						return;
					}
					var bedMasterBM = this.getData();
					if(this.mode == 'edit'){
						var bedNumber = this.bedNumberTxf.getValue();
						bedMasterBM.bedNumber = bedNumber;
						BedManager.modifyBed(bedMasterBM, function(retCode) {
										if(config.title){
//											mainThis.bedPanel.getForm().reset();
//											Ext.ux.event.Broadcast.publish('closeBedWindow');
											layout.getCenterRegionTabPanel().remove( mainThis.getPanel() );
											Ext.ux.event.Broadcast.publish('reloadBedGrid');
										}
						});
						
					}else{
						BedManager.addBed(bedMasterBM, function(retCode) {
										mainThis.bedPanel.getForm().reset();
										if(config.title){
//											Ext.ux.event.Broadcast.publish('closeBedWindow');
											layout.getCenterRegionTabPanel().remove( mainThis.getPanel() );
											Ext.ux.event.Broadcast.publish('reloadBedGrid');
										}
											
						});
					}
					
				},
				scope : this
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					var resetScope = this;
					Ext.Msg.show({
						   msg:msg.resetmsg,
						   buttons: Ext.Msg.YESNO,
						   fn: function( btnValue ){
								if( btnValue ==configs.yes ){
									resetScope.bedPanel.getForm().reset();
									if(resetScope.mode =='edit'){
										resetScope.bedPanel.getForm().setValues(config);
									}
								}
						   	},
						   icon: Ext.MessageBox.ERROR
						});
				}
			}]
		});
		
		this.bedNumberTxf = new Ext.form.TextField({
	        fieldLabel: 'Bed number',
	        xtype: 'textfield',
	        name: 'bedNbr',
	        value:config.bedNbr,
	        allowBlank:false,
	        required: true,
	        listeners:{
	        	blur: function( comp ){
	        		if(  !Ext.isEmpty( comp.getValue() ) ){
	        			BedManager.isBedExist( comp.getValue() ,{
	        				callback: function( isExist ){
	        					if( isExist ){
	        						Ext.Msg.show({
									   msg:' Bed already exist in the system with Bed Number:  '+ comp.getValue(),
									   buttons: Ext.Msg.OK,
									   fn: function(){
									   	comp.reset();
									   },
									   icon: Ext.MessageBox.ERROR
									});
	        					}
	        				},
	        				scope:this
	        			});
	        		}
	        		
	        	}
	        }
	    });
	    
	    if( config.mode =='edit' ){
			this.bedNumberTxf.disable();
		}
	    
	    this.chargeModeStore = new Ext.data.SimpleStore({
	   		fields: ['code', 'description'],
			data : [
				['D',"Per day"],
				['H',"Per hour"]
			]
	  });
	    
	    
	    config.chargeMode = !Ext.isEmpty( config.hourlyCharge ) ? "H" : "D";
	    
	    
	    this.chargeModeCbx = new wtccomponents.WTCComboBox({
	    	hideLabel : true,
	    	hiddenName : 'chargeModeCbx',
	    	store : this.chargeModeStore,
	    	value : config.chargeMode,
//	    	displayField : 'description',
//	    	valueField : 'code',
	    	anchor : '85%'
	    });
	    
	    this.chargeNumberField = new Ext.form.NumberField({
	        fieldLabel: 'Charge('+getCurrencyIndicator()+')',
	        decimalPrecision : 2, 
	        name: 'charge',
	        anchor : '98%',
	        value: !Ext.isEmpty( config.hourlyCharge ) ? config.hourlyCharge : config.dailyCharge 
	    });
	    
	    
		this.bedPanel = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				border : false,
				monitorValid: true,
				items: [{
					layout : 'column',
					defaults: {
						border: false,
						layout: 'form',
						defaults : {
							anchor : '95%'
						}
					},
					items: [{
					columnWidth : .32,
					items:this.bedNumberTxf
					},{
				    columnWidth : .66,
				    items:{
				        fieldLabel: 'Description',
				        xtype: 'textfield',
				        name: 'bedDescription',
				        value:config.bedDescription
				    }}]
				}, {
					layout : 'column',
					defaults: {
						border: false,
						layout: 'form',
						columnWidth : .32,
						defaults : {
							anchor : '95%'
						}
					},
					items: [{items:{
				        fieldLabel: 'Site',
				        xtype: 'textfield',
				        name: 'siteName',
				        value:config.siteName
				    }},{items:{
				        fieldLabel: 'Room',
				        xtype: 'combo',
				        name: 'roomNbr',
				        store: loadRoomName(),
						mode:'local',
						allowBlank:false,
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    forceSelection:true,
				        required: true
				    }},{items:{
				        fieldLabel: 'Floor',
				        xtype: 'textfield',
				        name : 'floorNbr',
				        value:config.floorNbr
				    }}]
				}, {
					layout : 'column',
					defaults: {
						border: false,
						layout: 'form',
						columnWidth : .32,
						defaults : {
							anchor : '95%'
						}
					},
					items: [{items:{
				        fieldLabel: 'Unit',
				        xtype: 'combo',
				        name: 'nursingUnit',
				        store: loadNursingUnits(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    forceSelection:true,
					    allowBlank:false,
				        required: true
				    }},{items:{
				        fieldLabel: 'Bed type',
				        xtype: 'combo',
				        name: 'bedType',
				        store: loadBedTypes(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    forceSelection:true,
					    allowBlank:false,
				        required: true
				    }},{items:{
				        fieldLabel: 'Status',
				        xtype: 'combo',
				        name: 'bedStatus',
				        store: loadBedStatus(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    forceSelection:true,
					    allowBlank:false,
				        required: true
				    }}]
				}, {
					layout : 'column',
					defaults: {
						border: false,
						layout: 'form',
						columnWidth : .32,
						defaults : {
//							anchor : '85%'
						}
					},
					items : [
				         {
				        	 columnWidth : .20,
				        	 items : this.chargeNumberField
				         },
				         {
				        	 columnWidth : .12,
				        	 items : this.chargeModeCbx
				         },
				         {
				         items :[{
				        	 		fieldLabel: 'Deposit amount',
							        xtype: 'numberfield',
							        decimalPrecision : 2, 
							        name: 'depositAmount',
							        value:config.depositAmount,
							        anchor : '95%'
				         	}]
				         }

			         ]
//					items: [{items:{
//				        fieldLabel: 'Daily charge',
//				        xtype: 'numberfield',
//				        decimalPrecision : 2, 
//				        name: 'dailyCharge',
//				        value:config.dailyCharge
//				    }},{items:{
//				        fieldLabel: 'Hourly charge',
//				        xtype: 'numberfield',
//				        decimalPrecision : 2, 
//				        name: 'hourlyCharge',
//				        value:config.hourlyCharge
//				    }},{items:{
//				        fieldLabel: 'Deposit amount',
//				        xtype: 'numberfield',
//				        decimalPrecision : 2, 
//				        name: 'depositAmount',
//				        value:config.depositAmount
//				    }}]
				},
				this.bedFeaturesPanel.getPanel(),
				this.buttonPanel]
		});
		
		this.bedPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonPanel.buttons[0].enable();
			} else {
				this.buttonPanel.buttons[0].disable();
			}
		}, this);
	},
	getPanel : function() {
			return this.bedPanel;
	},
	getData : function() {
		//return BedMasterBM
		var valuesMap = this.bedPanel.getForm().getValues();
		
		if( valuesMap['chargeModeCbx'] === 'H'){
			valuesMap['hourlyCharge'] = valuesMap['charge'];
		}
		else{
			valuesMap['dailyCharge'] = valuesMap['charge'];
		}
		
		var bedMasterBM = {
			bedNumber : valuesMap['bedNbr'],
			nursingUnit : {code: valuesMap['nursingUnit'], description: valuesMap['']},
			bedStatus : {code: valuesMap['bedStatus'], description: valuesMap['']},
			bedType : {code: valuesMap['bedType'], description: valuesMap['']},
			roomNbr : {code: valuesMap['roomNbr'], description: valuesMap['']},
			floorNbr : valuesMap['floorNbr'],
			siteName : valuesMap['siteName'],
			description : valuesMap['bedDescription'],
			dailyCharge : valuesMap['dailyCharge'],
			hourlyCharge : valuesMap['hourlyCharge'],
			depositAmount : valuesMap['depositAmount']
		};
		
		var specialFeatureAvailabilityList = this.bedFeaturesPanel.getData();
		
		bedMasterBM.specialFeatureAvailabilityList = specialFeatureAvailabilityList;
		
		return bedMasterBM;
	},
	loadData:function(config){
		var comboValues = {
			nursingUnit:config.nursingUnit,
			bedStatus:config.bedStatus,
			bedType:config.bedType,
			roomNbr:config.roomNbr
		};
		this.bedPanel.getForm().setValues(comboValues);
	}
});