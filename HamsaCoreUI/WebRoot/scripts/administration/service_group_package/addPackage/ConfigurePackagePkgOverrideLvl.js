Ext.namespace("administration.service_group_package.addPackage");

administration.service_group_package.addPackage.ConfigurePackagePkgOverrideLvl = Ext.extend(Object, {
	constructor : function(config) {
		Ext.QuickTips.init();
		
		var mainThis = this;
		this.slctdSvcRecord = null;
		
		var record = Ext.data.Record.create([
		  {name: "code", mapping: "serviceCode"},
		  {name: "description", mapping: "serviceName"},
		  {name: "serviceCharge"}
		]);

		this.svcsOfGivenGrpStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(ServiceManager.getServiceSummaryBMforGroup, true),
		    reader: new Ext.data.ListRangeReader({totalProperty:'totalSize'}, record),
		    remoteSort: true
		});
		
		this.serviceGroupCombo = new Ext.form.ComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.serviceGroup,
							mode:'local',
							store: loadAddServiceGroupCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'serviceGroup',
							anchor:'90%',
							emptyText: svcAndGrpAndPkgMsg.selectMsg,
							forceSelection : true
		});
		
		this.serviceGroupCombo.on('blur',function( thisCombo ){
			if( !Ext.isEmpty( thisCombo.getValue() )){
				this.serviceNameCombo.store.removeAll();
				this.serviceNameCombo.clearValue();
				this.serviceNameCombo.store.load({params:{start:0, limit:9999}, arg:[ thisCombo.getValue() ]});
			}else{
				this.serviceNameCombo.store.removeAll();
				this.serviceNameCombo.clearValue();
				this.serviceNameCombo.getStore().load({params:{start:0, limit:9999}, arg:[ null]});
			}
		},this);

		this.serviceNameCombo = new Ext.form.ComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.serviceName,
							mode:'local',
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'service',
							anchor:'90%',
							emptyText: svcAndGrpAndPkgMsg.selectMsg,
							forceSelection : true,
							store: this.svcsOfGivenGrpStore
		});
		
		this.noOfUnitsNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.noOfUnits,
							name:'noOfUnits',
							anchor:'60%',
							value:svcAndGrpAndPkgMsg.defaultValueForUnits
		});

		this.associateToPkgBtn = new Ext.Button({
							text:svcAndGrpAndPkgMsg.associateToPkgBtn,
							iconCls : 'add_btn',
							scope:this,
							tooltip:svcAndGrpAndPkgMsg.addEntryIntoGrid,
							handler: function(){
								this.associateToPkgButtonClicked(config);
							}
		});

		// Grid - start
		this.assocSvcRecord = Ext.data.Record.create([
	     	{name: 'serviceCode', mapping:'serviceCode'},
	      	{name: 'serviceName', mapping:'serviceName'},
	      	{name: 'serviceCharge', mapping:'serviceCharge',defaultValue:svcAndGrpAndPkgMsg.defaultValueForAmount},
	      	{name: 'noOfUnits', mapping:'numberOfUnits', defaultValue: svcAndGrpAndPkgMsg.minValueForAmount},
			{name: 'chargeIntoUnits',  defaultValue:svcAndGrpAndPkgMsg.defaultValueForAmount},
			{name:'groupCode'}
	       ]);
        
		this.assocSvcDataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.assocSvcRecord)
		});

		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
				}
			}
		});
		
		var assocSvcColumnHeaders = 
			 [  
				 this.gridChk, 
				 {header : svcAndGrpAndPkgMsg.code, dataIndex : 'serviceCode', 
			 			width : 120, sortable: true},
				 {header : svcAndGrpAndPkgMsg.name, dataIndex : 'serviceName', 
				 		width : 220, sortable: true},
				 {header : svcAndGrpAndPkgMsg.charge, dataIndex : 'serviceCharge', 
				 		align: 'right',width : 115,sortable: false, renderer:convertToAmount},
				 {header : svcAndGrpAndPkgMsg.noOfUnits, dataIndex : 'noOfUnits', 
				 		width : 110, sortable: false},
				 {header : svcAndGrpAndPkgMsg.chargeIntoUnits, 
				 		dataIndex : 'chargeIntoUnits',width : 190, align: 'right',
				 		sortable: false, renderer:convertToAmount} 
			  ];

		this.packageChargeLbl = new Ext.form.Label({
							text: svcAndGrpAndPkgMsg.packageCharge,
							style: 'padding-right:10px;',
			            	cls:'labelCls'
							
		});

		this.totalChargeLbl = new Ext.form.Label({
							text: svcAndGrpAndPkgMsg.defaultValueForAmount,
							style: 'padding-right:5px;',
			            	cls:'labelCls'
		});
			
		this.gridSummaryBar = new Ext.Toolbar({
			items: ['->',
					this.packageChargeLbl,
					this.totalChargeLbl]
		}); 
		
		this.editBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.btnEdit,
			iconCls: 'edit_btn',
			scope:this,
			disabled:true,
			tooltip:svcAndGrpAndPkgMsg.editGridEntry,
			handler: function(){
				this.editBtnClicked()
			}
		});
		
		this.deleteBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.btnDelete,
			iconCls: 'delete_btn',
			scope:this,
			disabled:true,
			tooltip:svcAndGrpAndPkgMsg.deleteEntryFormGrid,
			handler: function(){
				this.deleteBtnClicked();
			}
		});
		
		var gridButtonsBar = [
						this.editBtn,
					'-',this.deleteBtn
		];
					
		this.assocSvcGrid = new Ext.grid.GridPanel({
				title : svcAndGrpAndPkgMsg.associatedServices,		
				stripeRows : true,
				height : 200,
				autoWidth: true,
				autoScroll : true,
				border : false,
				frame:true,				
				store : this.assocSvcDataStore,
				sm:this.gridChk,
				remoteSort:true,
				viewConfig:{
					forceFit : true
				},
				columns : assocSvcColumnHeaders,
				style: 'padding-bottom:10px;',
				bbar: this.gridSummaryBar,
				tbar: gridButtonsBar,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						mainThis.setGridButtonsState(thisGrid.getSelectionModel());
					} 
				}
		});
		// Grid - end
	
		// Discount configuration
		this.discountTypeCombo = new Ext.form.ComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.discountType,
							mode:'local',
							store: loadDiscountTypeCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'discountTypeForPac',
							anchor:'90%',
							emptyText: svcAndGrpAndPkgMsg.selectMsg,
							forceSelection : true
		});
		
		this.discountAmtNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.discountAmt,
							name:'discountPctAmt',
							anchor:'90%',
							minValue : svcAndGrpAndPkgMsg.minValueForAmount
		});

		this.discountAmountPnl = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			anchor:'90%',
			hidden:true,
			defaults: {
				columnWidth:1,
				border:false,
				layout:'form',
				labelWidth:130
			},
			items: [
				{
					items:[this.discountAmtNbrField]
			    }
			]
		});	


		this.discountPctNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.discountPct,
							name:'discountPctAmt',
							anchor:'90%',
							minValue : svcAndGrpAndPkgMsg.minValueForAmount,
							maxValue: 100
							
		});

		this.discountPercentagePnl = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			anchor:'90%',
			hidden: true,
			defaults: {
				columnWidth:1,
				border:false,
				layout:'form',
				labelWidth:130
			},
			items: [
				{
					items:[this.discountPctNbrField]
			    }
			]
		});	

		
		this.discountConfigPanel = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			anchor:'90%',
			defaults: {
				columnWidth:1,
				border:false,
				layout:'form',
				labelWidth:130
			},
			items: [
				{
					items:[this.discountTypeCombo]
			    },{
					items:[this.discountAmountPnl]
				},{
					items:[this.discountPercentagePnl]
				}
			]
		});	
				
		// Charge summary
		this.discountAmtNbrFd = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.discountAmt,
							name:'discountAmt',
							anchor:'90%',
							disabled: true
		});
		
		this.effectiveChargeNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveCharge,
							name:'effectiveChargeForPac',
							anchor:'90%',
							disabled: true
		});
			
		this.chargeSummaryPanel = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			defaults: {
				columnWidth:1,
				border:false,
				layout:'form'
			},
			items: [
			    {
					items:[this.discountAmtNbrFd]
				},{
					items:[this.effectiveChargeNbrField]
				}
			]
		});	
			
		//Main Panel
		this.configPkgOverrideLvlPanel = new Ext.Panel({
				autoHeight : true,
				border : false,
				width: '75%',
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .5,
							items:this.serviceGroupCombo
						},{
						    columnWidth : .5,
						    items:this.noOfUnitsNbrField
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form'
						},
						items: [{
							columnWidth : .5,
							items:this.serviceNameCombo
						},{
						    columnWidth : .5,
						    items:this.associateToPkgBtn
					    }]
					},
					this.assocSvcGrid,
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							labelWidth:150
						},
						items: [{
							columnWidth : .5,
							items: this.discountConfigPanel
						},{
						    columnWidth : .5,
						    items: this.chargeSummaryPanel
					    }]
					}
			    ]
		});
		
		this.serviceGroupCombo.on('select', function(combo, record, index) {
			this.serviceNameCombo.store.removeAll();
			this.serviceNameCombo.clearValue();
			this.serviceNameCombo.store.load({params:{start:0, limit:9999}, arg:[record.data.code]});		
		}, this);

		this.serviceNameCombo.on('select', function(combo, record, index) {
			this.slctdSvcRecord = record.data;
		}, this);
		
		this.discountTypeCombo.on('select', function(combo, record, index) {
			var slctdType = record.data.code;
			if( slctdType == configs.discountType_Absolute ){
				this.discountPercentagePnl.hide();
				
				// ***********resetting the values start ***********************
				this.discountAmtNbrField.reset();
				this.discountAmtNbrFd.reset();
				this.effectiveChargeNbrField.reset();
				// ***********resetting the values end ***********************
				
				this.discountAmountPnl.show();
				this.discountAmountPnl.doLayout();
			} else if( slctdType == configs.discountType_Percentage ) {
				this.discountAmountPnl.hide();
				
				// ***********resetting the values start ***********************
				this.discountPctNbrField.reset();
				this.discountAmtNbrFd.reset();
				this.effectiveChargeNbrField.reset();
				// ***********resetting the values end ***********************
				
				this.discountPercentagePnl.show();
				this.discountPercentagePnl.doLayout();
			}
		}, this);
		
		this.discountAmtNbrField.on('blur', function( comp ){
			this.setChargeSummary(  );
		},this);
		
		this.discountPctNbrField.on('blur', function( comp ){
			this.setChargeSummary(  );
		},this);
	},
	setChargeSummary: function(  ){
		var totalCharge = this.totalChargeLbl.text;
		var discountAmount = null; 
		var type = this.discountTypeCombo.getValue();
		
		if( type == configs.discountType_Absolute ){
			var comp = this.discountAmtNbrField;
			if( totalCharge != svcAndGrpAndPkgMsg.defaultValueForAmount && comp.getValue() > 0 ){
				discountAmount = parseInt(totalCharge,10)- comp.getValue();
				this.discountAmtNbrFd.setValue( comp.getValue() );
				this.effectiveChargeNbrField.setValue( discountAmount );
				if( discountAmount < 0 ){
					error(  svcAndGrpAndPkgMsg.effectiveChargeInvalid );
					this.effectiveChargeNbrField.markInvalid();
				}
			}else{
				this.discountAmtNbrFd.reset();
				this.effectiveChargeNbrField.reset();
			}
		}else if( type == configs.discountType_Percentage ){
			var comp = this.discountPctNbrField;
			if( totalCharge != svcAndGrpAndPkgMsg.defaultValueForAmount && comp.getValue() > 0 ){
				var value = (parseInt(totalCharge,10)*comp.getValue())/100;
				discountAmount = parseInt(totalCharge,10)- value;
				this.discountAmtNbrFd.setValue( value );
				this.effectiveChargeNbrField.setValue( discountAmount );
				if( discountAmount < 0 ){
					error(  svcAndGrpAndPkgMsg.effectiveChargeInvalid );
					this.effectiveChargeNbrField.markInvalid();
				}
			}else{
				this.discountAmtNbrFd.reset();
				this.effectiveChargeNbrField.reset();
			}
		}
		
	},
	associateToPkgButtonClicked: function(){
		if( this.slctdSvcRecord == null ){
			error("Please select a service..!" );
			return;
		}
		
		var svcCd = this.slctdSvcRecord.code;
		var svcNm = this.slctdSvcRecord.description;
		
		var noOfUnits = "";
		if( Ext.isEmpty(this.noOfUnitsNbrField.getValue()) ){
			error("Please enter no.of units..!" )
			return;
		} else {
			noOfUnits =  parseInt(this.noOfUnitsNbrField.getValue(), 10);
		}
		
		if( Ext.isEmpty(svcCd) ){
			error("Please select a valid service name..!" )
			return;
		}
		
		var chargeIntoUnits = noOfUnits * this.slctdSvcRecord.serviceCharge;

		var recType = this.assocSvcGrid.getStore().recordType;
		var rec = new recType({serviceCode : svcCd, 
							   serviceName : svcNm,
							   serviceCharge : this.slctdSvcRecord.serviceCharge,
							   noOfUnits : noOfUnits,
							   chargeIntoUnits : chargeIntoUnits,
							   groupCode:this.serviceGroupCombo.getValue()
							   });
		
		 var storeRecords = this.assocSvcGrid.getStore().getRange();
		 if( storeRecords.length > 0){
		 	for ( var i = 0; i< storeRecords.length; i++){
		 		var storeRecord = storeRecords[i].data;
		 		if( storeRecord.serviceCode  == svcCd ){
		 			Ext.Msg.show({
					   msg: svcAndGrpAndPkgMsg.serviceAlreadyExistsWithPackage,
					   buttons: Ext.Msg.OK,
					   fn: function(){},
					   icon: Ext.MessageBox.ERROR
					});
					return;
		 		}
		 	}
		 }
		this.assocSvcGrid.getStore().add(rec);
		this.setPackageCost();
		this.setChargeSummary(  );
		this.resetData();
		this.slctdSvcRecord = null;
	},
	
	getPanel : function() {
		return this.configPkgOverrideLvlPanel;
	},
	
	getServiceGroupCombo : function() {
		return this.serviceGroupCombo;
	},
	
	getServiceNameCombo : function() {
		return this.serviceNameCombo;
	},
	setPackageCost: function(){
		var packageCost = this.assocSvcGrid.getStore().sum('chargeIntoUnits');
		if(packageCost == '0'){
			packageCost= svcAndGrpAndPkgMsg.defaultValueForAmount;
		}
		var convertedInrFormat = Ext.util.Format.money( packageCost );
		this.totalChargeLbl.setText( convertedInrFormat );
	},
	resetData:function(){
		this.serviceGroupCombo.clearValue();
		this.serviceNameCombo.clearValue();
		this.noOfUnitsNbrField.reset()
		this.svcsOfGivenGrpStore.removeAll();
	},
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.editBtn.disable();
		this.deleteBtn.disable();
		if( selectedRows.length == 1){
			this.editBtn.enable();
			this.deleteBtn.enable();
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			this.deleteBtn.enable();
		}
	},
	loadData: function( data ){
		this.serviceNameCombo.store.load({params:{start:0, limit:9999}, arg:[ data.groupCode ]});
		
		this.serviceNameCombo.store.on('load',function(){
			this.serviceGroupCombo.setValue( data.groupCode );
			this.serviceNameCombo.setValue( data.serviceCode );
			this.noOfUnitsNbrField.setValue( data.noOfUnits )

			this.slctdSvcRecord ={
				code: data.serviceCode,
				description: data.serviceName,
				serviceCharge: data.serviceCharge
			};
			
			this.serviceNameCombo.store.events['load'].clearListeners();
		},this);
		
	},
	geSelectedRow: function(){
		return this.assocSvcGrid.getSelectionModel().getSelections();
	},
	editBtnClicked: function(){
		var selectedRow = this.geSelectedRow();
		if( selectedRow.length > 0 ){
			this.loadData(selectedRow[0].data);
		}
		this.deleteBtnClicked();
		this.getGridBtnInitialState();
	},
	deleteBtnClicked: function(){
		var selectedRows = this.geSelectedRow();
		if( selectedRows.length > 0 ){
			for( var i =0; i < selectedRows.length; i++ ){
				this.assocSvcGrid.getStore().remove(selectedRows[i]);
			}
		}
		this.setPackageCost();
		this.setChargeSummary(  );
		this.getGridBtnInitialState();
	},
	getGridBtnInitialState: function(){
		this.editBtn.disable();
		this.deleteBtn.disable();
	},
	resetpackageLevelPanels: function( config ){
		
		this.resetData();
		
		// grid related 
		this.assocSvcGrid.getStore().removeAll();
		this.getGridBtnInitialState();
		
		// charge summary related
		this.discountAmtNbrFd.reset()
		this.effectiveChargeNbrField.reset();
		
		// discount configuration related
		this.discountTypeCombo.clearValue();
		this.discountPctNbrField.reset();
		this.discountAmtNbrField .reset();
		
		// setting default value for currency
		this.totalChargeLbl.setText(svcAndGrpAndPkgMsg.defaultValueForAmount);
		if(config.mode == svcAndGrpAndPkgMsg.editMode){
			this.loadStoreData( config );
		}
		
	},
	getData: function(){
		var storeData = this.assocSvcGrid.getStore().getRange();
		if(storeData.length > 0){
			var packageHasServicesList = new Array();
			for(var i =0; i<storeData.length; i++){
				var data = storeData[i].data;
				var packageHasService ={
					service: {code:data.serviceCode},
					numberOfUnits:data.noOfUnits,
					serviceCharge: data.serviceCharge
				}
				packageHasServicesList.push( packageHasService );
			}
			return packageHasServicesList;
		}else{
			return null;
		}
	},
	getdiscountInfo: function(){
		var effectiveCharge = this.effectiveChargeNbrField.getValue();
		if( Ext.isEmpty( effectiveCharge ) || effectiveCharge == 'undefined' ){
			effectiveCharge = parseFloat( this.totalChargeLbl.text );
		}
		var discountInfo = {
			discountPctAmt:this.discountAmtNbrFd .getValue(),
			effectiveChargeForPac: effectiveCharge ,
			packageCharge: this.totalChargeLbl.text
		}
		return discountInfo;
	},
	loadStoreData: function( config ){
		var servicePackageBm = config.servicePackageBM;
		var recordType = this.assocSvcGrid.getStore().recordType;
		if( servicePackageBm != null){
			if( servicePackageBm.serviceBMList !=null && servicePackageBm.serviceBMList.length > 0 ){
				for( var i = 0; i< servicePackageBm.serviceBMList.length; i++){
					var packageHasService = servicePackageBm.serviceBMList[i];
					var rec = new recordType({
					   serviceCode : !Ext.isEmpty(packageHasService.service)?packageHasService.service.code:'', 
					   serviceName : !Ext.isEmpty(packageHasService.service)?packageHasService.service.description:'',
					   serviceCharge : packageHasService.serviceCharge,
					   noOfUnits : packageHasService.numberOfUnits,
					   chargeIntoUnits : (packageHasService.numberOfUnits * packageHasService.serviceCharge)
				   });
				  this.assocSvcGrid.getStore().add( rec ); 
				}
			}
		}
		this.editBtn.hide();
		this.totalChargeLbl.setText( servicePackageBm.packageCharge );
		this.discountAmtNbrFd.setValue( servicePackageBm.discountAmountPct );
		this.effectiveChargeNbrField.setValue( servicePackageBm.effectiveCharge );
		// all time we get discount amount from back end, 
		//thats why we are setting the default type is absolute in modify case
		this.discountTypeCombo.setValue( configs.discountType_Absolute );
		this.discountAmountPnl.show();
		this.discountAmtNbrField.setValue( servicePackageBm.discountAmountPct );
		
	},
	
	getServiceNameCombo : function(){
		return this.serviceNameCombo;
	}
});