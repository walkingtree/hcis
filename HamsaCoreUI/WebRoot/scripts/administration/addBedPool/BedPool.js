Ext.namespace("administration.addBedPool");

administration.addBedPool.BedPool = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		
		if(Ext.isEmpty(config)){
			config={};
		}
		this.bedUnitTypeRecord = Ext.data.Record.create([
		{ name: 'poolName'},
		{ name: 'unitTypeCd'},
		{ name: 'unitTypeDesc'},
		{ name: 'effectiveFrom'},
        { name: 'effectiveTo'}
        ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ArrayReader({id: 'unitTypeCd'}, this.bedUnitTypeRecord),
        	data:[]
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			disabled: true,
			handler : function() {
				var selectedRow = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRow.length>0){
					var rowData = selectedRow[0].data;
					var editValues ={
						unitType:rowData.unitTypeCd,
						effectiveFrom:rowData.effectiveFrom,
						effectiveTo:rowData.effectiveTo
					};
					this.bedPoolPanel.getForm().setValues(editValues);
					selectedUnitType ={code:rowData.unitTypeCd, description:rowData.unitTypeDesc};
					this.infoGrid.getStore().remove(selectedRow[0]);
				}
				
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text :  msg.del,
			scope:this,
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length >0){
					for(var i =0; i<selectedRows.length;i++){
						this.infoGrid.getStore().remove(selectedRows[i]);
					}
				}
				
			}
		});
	
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					var selectedRows = selectionModel.getSelections();
					if( selectedRows.length > 1){
						mainThis.editBtn.disable();
					}else{
						mainThis.editBtn.enable();
						mainThis.deleteBtn.enable();
							
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.enable();
					mainThis.deleteBtn.enable();
				}
			}
		});

		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 200,
				width : '100%',
				style : 'padding-right: 5px',
				autoScroll : true,
				border : true,
				store : this.dataStore,
				sm:this.gridChk,
				viewConfig: {forceFit: true},
				tbar :new Ext.Toolbar({
						items:[
							this.editBtn,
							new Ext.Toolbar.Separator(),
							this.deleteBtn
						]
				}),
				columns : [this.gridChk, {
							header : 'Unit type',
							dataIndex : 'unitTypeDesc',
							width : 300,
							sortable: true
						}, {
							header : 'Effective from',
							dataIndex : 'effectiveFrom',
							width : 110,
							sortable: true,
							renderer: Ext.util.Format.dateRenderer('d/m/Y')
						}, {
							header : 'Effective to',
							dataIndex : 'effectiveTo',
							width : 110,
							sortable: true,
							renderer: Ext.util.Format.dateRenderer('d/m/Y')
						}],
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						var selectedRecords = thisGrid.getSelectionModel().getSelections();
						if(selectedRecords.length >= 1){
							if (selectedRecords.length > 1) {
								mainThis.editBtn.disable();
							} else {
								mainThis.editBtn.enable();
							}
							mainThis.deleteBtn.enable();
						}else{
							mainThis.editBtn.disable();
							mainThis.deleteBtn.disable();
						}
					} 
				}
			});

		this.infoGrid.getData = function() {
			var recordsArr = this.store.getRange();
			if (!Ext.isEmpty(recordsArr)) {
				var retAsgmBMList = Array();
				for (var i=0; i<recordsArr.length; i++) {
					var tmpObj = recordsArr[i].data;
					var retAsgmBM = {
						poolName : tmpObj.poolName,
						unitType : {code: tmpObj.unitTypeCd},
						effectiveFromDt : Ext.isEmpty(tmpObj.effectiveFrom) ? null : tmpObj.effectiveFrom,
						effectiveToDate : Ext.isEmpty(tmpObj.effectiveTo) ? null : tmpObj.effectiveTo
					};
					
					retAsgmBMList[i] = retAsgmBM;
				}
				
				return retAsgmBMList;
			}
		};

		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				handler: function(){
					var valuesMap = this.bedPoolPanel.getForm().getValues();
					if(!this.bedPoolPanel.getForm().isValid()){
						Ext.Msg.show({
							msg: 'Please enter bed pool name and retry..!',
							icon : Ext.MessageBox.INFO,
							buttons: Ext.Msg.OK
						});
						return;
					}
					var bedPoolBM = this.getData();
					if(this.mode =='edit' ){
						var poolName = this.bedPoolNameTxf.getValue();
						bedPoolBM.bedPoolName = poolName; 
						BedManager.modifyBedPool(bedPoolBM, function(){
									mainThis.resetData();
										loadBedPools().reload();
										if(!Ext.isEmpty(mainThis.title)){
											layout.getCenterRegionTabPanel().remove(  mainThis.bedPoolPanel, true );
											layout.getCenterRegionTabPanel().doLayout();
//											Ext.ux.event.Broadcast.publish('closeBedPoolWindow');
											Ext.ux.event.Broadcast.publish('reloadBedPoolGrid');
										}
						});
						
					}else{
						BedManager.addBedPool(bedPoolBM, function() {
										mainThis.resetData();
										loadBedPools().reload();
										if(!Ext.isEmpty(mainThis.title)){
											layout.getCenterRegionTabPanel().remove( mainThis.bedPoolPanel, true );
//											Ext.ux.event.Broadcast.publish('closeBedPoolWindow');
											Ext.ux.event.Broadcast.publish('reloadBedPoolGrid');
										}
							
						});
					}
				},
				scope : this
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				handler: function(){
					this.resetData();
				},
				scope:this
			}]
		});	
					
		var selectedUnitType = '';
		var mainThis = this;
		
		this.effectiveFormDate = new Ext.form.WTCDateField({
			fieldLabel: msg.effectivefrom,
	        name: 'effectiveFrom',
			anchor:'98%',
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
						mainThis.effectiveToDate.setMinValue(date.getValue());
					}
				}
			}
	
		});
		
		this.effectiveToDate = new Ext.form.WTCDateField({
			fieldLabel: msg.effectiveto,
			name: 'effectiveTo',
			anchor:'98%',
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
						mainThis.effectiveFormDate.setMaxValue(date.getValue());
					}
				}
			}
		});
		
		this.bedPoolNameTxf = new Ext.form.TextField({
	        fieldLabel: msg.poolname,
	        xtype: 'textfield',
	        name : 'poolName',
	        anchor : '98%',
	        value:config.poolName,
	        allowBlank:false,
	        required: true
	    });
	    
		this.bedPoolPanel = new Ext.form.FormPanel({
				layout:'column',
				frame : true,
				width : '100%',
				defaults:{ columnWidth:.25 },
				autoHeight : true,
				border : false,
				monitorValid: true,
				items: [
					{
						layout:'form',
						items:this.bedPoolNameTxf
					},
					{
						layout:'form',
						columnWidth:.60,
						items:[
							{
						        fieldLabel: msg.description,
						        xtype: 'textfield',
						        name : 'poolDesc',
						        anchor : '82%',
						        value:config.poolDesc
						    }
						]
					},
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.unittype,
						        xtype: 'combo',
						        name: 'unitType',
						        store: loadNursingUnitTypes(),
								mode:'local',
								displayField:'description',
								valueField:'code',
							    triggerAction: 'all',
							    forceSelection:true,
							    required: true,
							    listeners: {
							    	select : function(thisCbx, record, index) {
							    		selectedUnitType = record.data;
							    	}
							    },
						        anchor : '98%'
						    }
						]
					},
					{
						layout:'form',
						items:this.effectiveFormDate
					},
					{
						layout:'form',
						items:this.effectiveToDate
					},
					{
						layout:'form',
						items:[
							{
						        text: msg.add,
						        iconCls : 'add_btn',
						        xtype: 'button',
					    		handler: function() {
					    			var valuesMap = this.bedPoolPanel.getForm().getValues();
					    			
					    			var matchedIndex = this.infoGrid.store.findBy(function(record, id) {
					    													if (record.data.unitTypeCd == valuesMap['unitType']) {
					    														return true;
					    													}
					    													});
					    			if (matchedIndex > -1) {
					    				Ext.Msg.show({
											msg: 'Entry for this unit type already exists!',
											icon : Ext.MessageBox.ERROR,
											buttons: Ext.Msg.OK
										});
					    				return;
					    			}
					    			if(valuesMap['unitType']!=''){
					    				var record = {
						    				poolName : valuesMap['poolName'],
						    				unitTypeCd : valuesMap['unitType'],
					    					unitTypeDesc : selectedUnitType.description,
					    					effectiveFrom : this.effectiveFormDate.getValue(),
					    					effectiveTo : (Ext.isEmpty(valuesMap['effectiveTo']) || valuesMap['effectiveTo'] == "undefined") ? "" : this.effectiveToDate.getValue()
						    			};
						    			
						    			record = new Ext.data.Record(record);
										var recordArr = Array();
						    			recordArr[0] = record;
						    			this.infoGrid.store.insert(0, recordArr);
						    			
						    			var resetPanel ={
						    				effectiveFrom: '',
						    				effectiveTo:'',
						    				unitType:''
						    			}
						    			this.bedPoolPanel.getForm().setValues(resetPanel);
						    			selectedUnitType ='';
						    			mainThis.effectiveFormDate.setMaxValue(null);
						    			mainThis.effectiveToDate.setMinValue(null);
					    			}else{
					    				Ext.Msg.show({
											msg: 'Please select a unit type and try again!',
											icon : Ext.MessageBox.INFO,
											buttons: Ext.Msg.OK
										});
					    			}
					    		},
					    		scope: this
					    	}
						]
					},
					{
						layout:'form',
						columnWidth:1,
						items:this.infoGrid
					},
					{
						layout:'form',
						columnWidth:1,
						items:this.buttonPanel
					}]
		});
		
		this.bedPoolPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				var recCount = this.infoGrid.store.getCount();
				if (recCount > 0) {
					this.buttonPanel.buttons[0].enable();
				}
			} else {
				this.buttonPanel.buttons[0].disable();
			}
		}, this);
		
		
		
	},
	getPanel : function() {
			return this.bedPoolPanel;
	},
	getGridPanel: function(){
		return this.infoGrid;
	},
	getData : function() {
		var valuesMap = this.bedPoolPanel.getForm().getValues();
		var bedPoolBM = {
			bedPoolName : valuesMap['poolName'],
			poolDesc : valuesMap['poolDesc'],
			totalNumberOfBed : 0,
			numberOfAvailableBeds : 0,
			bedPoolUnitTypeAsgm : this.infoGrid.getData()
		};
		
		return bedPoolBM;
	},
	resetData:function(){
		this.bedPoolPanel.getForm().reset();
		this.infoGrid.getStore().removeAll();
		if(this.mode == 'edit'){
			this.loadData(this);	
		}
	},
	loadData:function(config){
		if(config.mode =='edit'){
			this.bedPoolNameTxf.disable();
			var record = this.infoGrid.getStore().recordType;
			if(config.assignedUnitTypeList!=null && config.assignedUnitTypeList.length>0 ){
				for(var i = 0; i<config.assignedUnitTypeList.length; i++){
					var bedUnitTypeRecord = new record({
						unitTypeCd:Ext.isEmpty(config.assignedUnitTypeList[i].unitType)?null:config.assignedUnitTypeList[i].unitType.code,
						unitTypeDesc:Ext.isEmpty(config.assignedUnitTypeList[i].unitType)?null:config.assignedUnitTypeList[i].unitType.description,
						effectiveFrom:config.assignedUnitTypeList[i].effectiveFromDt,
						effectiveTo:config.assignedUnitTypeList[i].effectiveToDate
					});
					 this.infoGrid.getStore().add(bedUnitTypeRecord);
				}
				
			}
			
		}
	}
});