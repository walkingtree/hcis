Ext.namespace("administration.service_group_package.addPackage");

administration.service_group_package.addPackage.ConfigurePackageSvcOverrideLvl = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.apply(this, config);
		Ext.QuickTips.init();
		
		var mainThis =this;
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
							anchor:'98%'
		});

		this.serviceNameCombo = new Ext.form.ComboBox({
							fieldLabel: svcAndGrpAndPkgMsg.serviceName,
							mode:'local',
							store: this.svcsOfGivenGrpStore,
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'serviceName',
							anchor:'98%'
		});
		
		this.serviceChargeNbrField = new Ext.form.NumberField({
							fieldLabel: svcAndGrpAndPkgMsg.serviceCharge,
							name:'serviceCharge',
							anchor:'80%',
							disabled: true
		});

		this.noOfUnitsNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.noOfUnits,
							name:'noOfUnits',
							anchor:'80%',
							value: svcAndGrpAndPkgMsg.defaultValueForUnits
		});


		this.serviceSelectionPanel = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			anchor:'95%',
			defaults: {
				columnWidth:1,
				border:false,
				layout:'form',
				labelWidth:130
			},
			items: [
				{
					items:[this.serviceGroupCombo]
			    },{
					items:[this.serviceNameCombo]
				},{
					items:[this.serviceChargeNbrField]
			    },{
					items:[this.noOfUnitsNbrField]
				}
			]
		});	
		
		this.discountTypeCombo = new Ext.form.ComboBox({
							fieldLabel:svcAndGrpAndPkgMsg.discountType,
							mode:'local',
							store: loadDiscountTypeCbx(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'discountType',
							anchor:'90%'
		});
		
		this.discountAmtPctNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.discount,
							name:'discount',
							anchor:'90%',
							minValue: svcAndGrpAndPkgMsg.minValueForAmount
		});
		
		this.effectiveChargeNbrField = new Ext.form.NumberField({
							fieldLabel:svcAndGrpAndPkgMsg.effectiveCharge,
							name:'effectiveCharge',
							anchor:'85%',
							disabled: true
		});

		this.discountConfigurationPanel = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '100%',
			border : false,
			anchor:'90%',
			style: 'padding-bottom:10px;',
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
					items:[this.discountAmtPctNbrField]
				},{
					items:[this.effectiveChargeNbrField]
			    }
			]
		});

		this.associateToPkgBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.associateToPkgBtn,
			iconCls : 'add_btn',
			scope:this,
			tooltip:svcAndGrpAndPkgMsg.addEntryIntoGrid,
			handler: function(){
				this.associateToPkgButtonClicked();
			}
		});
		
		// Grid - start
		
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
					
		this.assocSvcRecord = Ext.data.Record.create([
	     	{name: 'serviceCode', mapping:'service.code'},
	      	{name: 'serviceName', mapping:'service.description'},
	      	{name: 'serviceCharge', mapping:'serviceCharge', type:'float', defaultValue: svcAndGrpAndPkgMsg.defaultValueForAmount},
	      	{name: 'noOfUnits', mapping:'numberOfUnits', type:'int', defaultValue: svcAndGrpAndPkgMsg.minValueForAmount},
			{name: 'chargeIntoUnits', type:'float', defaultValue: svcAndGrpAndPkgMsg.defaultValueForAmount},
	      	{name: 'discountAmt', mapping:'discountAmtPct', type:'float', defaultValue: svcAndGrpAndPkgMsg.defaultValueForAmount},
			{name: 'effectiveCharge', mapping:'discountAmtPct',type:'float', defaultValue: svcAndGrpAndPkgMsg.defaultValueForAmount},
			{name:'serviceGroup'},
			{name: 'discountType'},
			{name: 'discountPercentage'}
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
							 		width : 60, sortable: true},
							 {header : svcAndGrpAndPkgMsg.name, dataIndex : 'serviceName', 
							 		width : 100, sortable: true},
							 {header : svcAndGrpAndPkgMsg.charge, dataIndex : 'serviceCharge', 
							 		width : 70, align: 'right', sortable: false, renderer:convertToAmount},
							 {header : svcAndGrpAndPkgMsg.noOfUnits, dataIndex : 'noOfUnits', 
							 		width : 70, align: 'right', sortable: false},
							 {header : svcAndGrpAndPkgMsg.chargeIntoUnits, dataIndex : 'chargeIntoUnits', 
							 		width : 80, align: 'right', sortable: false, renderer:convertToAmount}, 
							 {header : svcAndGrpAndPkgMsg.discountAmt, dataIndex : 'discountAmt', 
							 		width : 80, align: 'right', sortable: false, renderer:convertToAmount},
							 {header : svcAndGrpAndPkgMsg.effectiveCharge, dataIndex : 'effectiveCharge',
							 		width : 100,  align: 'right',sortable: false, renderer:convertToAmount}
						  ];

		this.totalsLbl = new Ext.form.Label({
							text: svcAndGrpAndPkgMsg.total,
							style: 'padding-right:25px;',
							cls:'labelCls'
		});

		this.totalOfDiscountAmountsLbl = new Ext.form.Label({
							text: svcAndGrpAndPkgMsg.defaultValueForAmount,
							style: 'padding-right:75px;',
							cls:'labelCls'
		});

		this.totalOfEffectiveChargesLbl = new Ext.form.Label({
							text: svcAndGrpAndPkgMsg.defaultValueForAmount,
							style: 'padding-right:2px;',
							cls:'labelCls'
		});
			
		this.gridSummaryBar = new Ext.Toolbar({
			items: [this.totalsLbl,
					'->',
					this.totalOfDiscountAmountsLbl,
					this.totalOfEffectiveChargesLbl]
		}); 
								
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
				style: 'padding-top:10px; padding-bottom:10px;',
				bbar: this.gridSummaryBar,
				tbar: gridButtonsBar,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						mainThis.setGridButtonsState(thisGrid.getSelectionModel());
					} 
				}
		});
		// Grid - end

		//Main Panel
		this.configSvcOverrideLvlPanel = new Ext.Panel({
				autoHeight : true,
				border : false,
				width: '75%',
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							labelWidth:150
						},
						items: [{
							columnWidth : .5,
							items: this.serviceSelectionPanel
						},{
						    columnWidth : .5,
						    items: [this.discountConfigurationPanel,
						    		this.associateToPkgBtn]
					    }]
					},
					this.assocSvcGrid
			    ]
		});
		this.initialListeners();
		
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

	},
	
	getPanel : function() {
		return this.configSvcOverrideLvlPanel;
	},
	initialListeners: function(){
		// initial listeners
		this.serviceGroupCombo.on('select', function( combo, record, index ) {
			this.serviceNameCombo.store.removeAll();
			this.serviceNameCombo.clearValue();
			this.serviceNameCombo.store.load({params:{start:0, limit:9999}, arg:[record.data.code]});		
		}, this);
		
		this.serviceNameCombo.on('select', function( combo, record, index ) {
			this.slctdSvcRecord = record.data;
			this.serviceChargeNbrField.setValue( this.slctdSvcRecord.serviceCharge );
			this.calculateEffectiveCharge();
		}, this);
		
		this.discountTypeCombo.on('select', function( combo, record, index ) {
			var slctdType = record.data.code;
			 if( slctdType == configs.discountType_Percentage ) {
				this.discountAmtPctNbrField.maxValue = 100;
			 }else if( slctdType == configs.discountType_Absolute ){
			 	this.discountAmtPctNbrField.maxValue = Number.MAX_VALUE;
			 }
			 this.calculateEffectiveCharge();
		}, this);
		
		this.discountAmtPctNbrField.on('blur', function( comp ){
			var discountAmt = this.calculateDiscountAmt();
			var charge = this.slctdSvcRecord.serviceCharge * this.noOfUnitsNbrField.getValue();
			var effectiveCharge = charge - discountAmt;
			this.effectiveChargeNbrField.setValue( effectiveCharge );
			if( effectiveCharge < 0 ){
				error(  svcAndGrpAndPkgMsg.effectiveChargeInvalid );
				this.effectiveChargeNbrField.markInvalid();
				return;
			}
		},this);
		
		this.noOfUnitsNbrField.on('blur', function( comp ){
			this.calculateEffectiveCharge();
		},this);
	},
	calculateEffectiveCharge: function(){
		if( !Ext.isEmpty(this.discountAmtPctNbrField.getValue()) && !Ext.isEmpty(this.noOfUnitsNbrField.getValue()) ){
			var discountAmt = this.calculateDiscountAmt();
			var charge = this.slctdSvcRecord.serviceCharge * this.noOfUnitsNbrField.getValue();
			var effectiveCharge = charge - discountAmt;
			this.effectiveChargeNbrField.setValue( effectiveCharge );
			if( effectiveCharge < 0 ){
				error(  svcAndGrpAndPkgMsg.effectiveChargeInvalid );
				this.effectiveChargeNbrField.markInvalid();
				return;
			}
		}
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
	associateToPkgButtonClicked: function(){
		if( this.slctdSvcRecord == null ){
			error("Please select a service..!" );
			return;
		}
		
		var svcCd = this.slctdSvcRecord.code;
		var svcNm = this.slctdSvcRecord.description;
		
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
		
		var discountAmt = this.calculateDiscountAmt();
		if(Ext.isEmpty( discountAmt )){
			error("Please specify discount amount..!" )
			return;
		}
		
		var effectiveCharge = this.effectiveChargeNbrField.getValue();
		
		if( effectiveCharge < 0 ){
			error(  svcAndGrpAndPkgMsg.effectiveChargeInvalid );
			return;
		}
		
		var recType = this.assocSvcGrid.getStore().recordType;
		var rec = new recType({serviceCode : svcCd, 
							   serviceName : svcNm,
							   serviceCharge : this.slctdSvcRecord.serviceCharge,
							   noOfUnits : noOfUnits,
							   chargeIntoUnits : chargeIntoUnits,
							   discountAmt: discountAmt,
							   effectiveCharge: effectiveCharge,
							   serviceGroup:this.serviceGroupCombo.getValue(),
							   discountType: this.discountTypeCombo.getValue(),
							   discountPercentage:this.discountAmtPctNbrField .getValue()
							   });
							   
		this.assocSvcGrid.getStore().add(rec);
		this.setTotalCost();
		this.resetData();
		this.slctdSvcRecord = null;
	},
	calculateDiscountAmt: function(){
		var discountType = this.discountTypeCombo.getValue();
		if( discountType == configs.discountType_Percentage ){
			var charge = this.slctdSvcRecord.serviceCharge * this.noOfUnitsNbrField.getValue();
			return ( charge * this.discountAmtPctNbrField .getValue() )/100;
		}else if( discountType == configs.discountType_Absolute ){
			return this.discountAmtPctNbrField.getValue();
		}
	},
	setTotalCost: function(){
		var discountCost = this.assocSvcGrid.getStore().sum('discountAmt');
		if(discountCost == '0'){
			discountCost = svcAndGrpAndPkgMsg.defaultValueForAmount;
		}
		var convertedInrFormatForDiscount = Ext.util.Format.money( discountCost );
		this.totalOfDiscountAmountsLbl.setText( convertedInrFormatForDiscount );
		
		var effectiveCost = this.assocSvcGrid.getStore().sum('effectiveCharge');
		if( effectiveCost == '0' ){
			effectiveCost =  svcAndGrpAndPkgMsg.defaultValueForAmount;
		}
		var convertedInrFormatForEffectiveCharge = Ext.util.Format.money( effectiveCost );
		this.totalOfEffectiveChargesLbl.setText( convertedInrFormatForEffectiveCharge );
	},
	resetData: function(){
		
		// related to service panel
		this.serviceGroupCombo.clearValue();
		this.serviceNameCombo.clearValue();
		this.svcsOfGivenGrpStore.removeAll();
		this.serviceChargeNbrField.reset();
		this.noOfUnitsNbrField.reset();
		
		// related to discount panel
		this.discountTypeCombo.clearValue();
		this.discountAmtPctNbrField.reset();
		this.discountAmtPctNbrField.maxValue = Number.MAX_VALUE;
		this.effectiveChargeNbrField.reset();
		
		this.setDefaultValues();
	},
	loadRecordData: function( data ){
		this.serviceNameCombo.store.load({params:{start:0, limit:9999}, arg:[ data.serviceGroup ]});
		
		this.serviceNameCombo.store.on('load',function(){
			this.serviceGroupCombo.setValue( data.serviceGroup );
			this.serviceNameCombo.setValue( data.serviceCode );
			this.noOfUnitsNbrField.setValue( data.noOfUnits );
			this.serviceChargeNbrField.setValue( data.serviceCharge );
			
			this.slctdSvcRecord ={
				code: data.serviceCode,
				description: data.serviceName,
				serviceCharge: data.serviceCharge
			};
			
			this.discountTypeCombo.setValue( data.discountType );
			this.discountAmtPctNbrField.setValue( data.discountAmt );
			this.discountAmtPctNbrField.maxValue = data.discountType == configs.discountType_Percentage?100
																			:Number.MAX_VALUE;
			this.effectiveChargeNbrField.setValue( data.effectiveCharge );
			
			this.serviceNameCombo.store.events['load'].clearListeners();
		},this);
		
	},
	getSelectedRow: function(){
		return this.assocSvcGrid.getSelectionModel().getSelections();
	
	},
	editBtnClicked: function(){
		var selectedRow = this.getSelectedRow();
		if( selectedRow.length > 0 ){
			this.loadRecordData( selectedRow[0].data );
			this.deleteBtnClicked();
		}
		this.getGridBtnInitialState();
	},
	deleteBtnClicked: function(){
		var selectedRows = this.getSelectedRow();
		if( selectedRows.length > 0 ){
			for( var k =0; k<selectedRows.length; k++ ){
				this.assocSvcGrid.getStore().remove( selectedRows[k] );
			}
		}
		this.getGridBtnInitialState();
		this.setTotalCost();
	},
	getGridBtnInitialState: function(){
		this.editBtn.disable();
		this.deleteBtn.disable();
	},
	resetServiceLevelPanels: function( config ){
		
		this.resetData();
		
		// grid related 
		this.assocSvcGrid.getStore().removeAll();
		this.getGridBtnInitialState();
		
		// setting default value for currency labels
		this.totalOfDiscountAmountsLbl.setText(svcAndGrpAndPkgMsg.defaultValueForAmount);
		this.totalOfEffectiveChargesLbl.setText(svcAndGrpAndPkgMsg.defaultValueForAmount);
		
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
					numberOfUnits: data.noOfUnits,
					effectiveCharge: data.effectiveCharge,
					serviceCharge: data.chargeIntoUnits,
					discountType: data.discountType,
					discountAmtPct: data.discountType ==configs.discountType_Absolute?data.discountAmt:data.discountPercentage
				}
				packageHasServicesList.push( packageHasService );
			}
			return packageHasServicesList;
		}else{
			return null;
		}
	},
	getdiscountInfo: function(){
		var discountInfo ={
			discountAmountPct: this.totalOfDiscountAmountsLbl.text,
			effectiveCharge:this.totalOfEffectiveChargesLbl.text
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
					var discountAmount = packageHasService.discountAmtPct;
					// in packageHasService bm service charge amount is coming after calculating with no of units with service charge 
					var serviceCharge = packageHasService.serviceCharge/ packageHasService.numberOfUnits ;
					if(packageHasService.discountType == configs.discountType_Percentage ){
						discountAmount = packageHasService.serviceCharge - packageHasService.effectiveCharge ;
					}
					var rec =new recordType({
					   serviceCode : !Ext.isEmpty(packageHasService.service)?packageHasService.service.code:'', 
					   serviceName : !Ext.isEmpty(packageHasService.service)?packageHasService.service.description:'',
					   serviceCharge : serviceCharge,
					   noOfUnits : packageHasService.numberOfUnits,
					   discountAmt: discountAmount.toFixed( 2 ),
					   effectiveCharge: (packageHasService.effectiveCharge).toFixed( 2 ),
					   discountType: packageHasService.discountType,
					   chargeIntoUnits : (packageHasService.serviceCharge).toFixed( 2 ),// serviceCharge is having value, after calculating with no of units.
					   discountPercentage: packageHasService.discountType == configs.discountType_Percentage?packageHasService.discountAmtPct:0
				   });
				  this.assocSvcGrid.getStore().add( rec ); 
				}
			}
		}
		this.editBtn.hide();
		this.totalOfEffectiveChargesLbl.setText( servicePackageBm.effectiveCharge );
		this.totalOfDiscountAmountsLbl.setText( servicePackageBm.discountAmountPct );
	},
	setDefaultValues: function(){
		this.discountTypeCombo.setValue( configs.discountType_Absolute );
		this.discountAmtPctNbrField .setValue( 0 );
	},
	
	getServiceNameCombo : function(){
		return this.serviceNameCombo;
	}
});