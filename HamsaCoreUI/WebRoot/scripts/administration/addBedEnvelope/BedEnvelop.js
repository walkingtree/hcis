Ext.namespace("administration.addBedEnvelope");

administration.addBedEnvelope.BedEnvelop = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		if(Ext.isEmpty(config)){
			config={};
		}
		this.bedEnvelopRecord = Ext.data.Record.create([
		{ name: 'poolNameCd'},
		{ name: 'poolNameDesc'},
		{ name: 'effectiveFrom'},
        { name: 'effectiveTo'}
        ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ArrayReader({id: 'unitTypeCd'}, this.bedEnvelopRecord),
        	data:[]
		});

		this.pagingBar = new Ext.PagingToolbar({
				pageSize : 10,
				store : this.dataStore,
				displayInfo : true,
				displayMsg : 'Displaying envelops {0} - {1} of {2}',
				emptyMsg : "No envelops to display"
			});
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			disabled: true,
			scope:this,
			handler : function() {
				var selectedRow = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRow.length>0){
					var rowData = selectedRow[0].data;
					var editValues ={
						poolName:rowData.poolNameCd,
						effectiveFrom:rowData.effectiveFrom,
						effectiveTo:rowData.effectiveTo
					};
					this.bedEnvelopPanel.getForm().setValues(editValues);
					selectedBedPool ={code:rowData.poolNameCd,description:rowData.poolNameDesc}
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
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
				}
			}
		});

		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 200,
				width : '100%',
				autoScroll : true,
				border : false,
				store : this.dataStore,
				//bbar : this.pagingBar,
				sm:this.gridChk,
				tbar : new Ext.Toolbar({
					items:[
						this.editBtn,
						new Ext.Toolbar.Separator(),
						this.deleteBtn
					]
				}),

				columns : [this.gridChk, {
							header : 'Pool name',
							dataIndex : 'poolNameDesc',
							width : 140,
							sortable: true
						}, {
							header : 'Effective from',
							dataIndex : 'effectiveFrom',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 110,
							sortable: true
						}, {
							header : 'Effective to',
							dataIndex : 'effectiveTo',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 120,
							sortable: true
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

		this.infoGrid.getData = function(valuesMap, envelopTxf) {
			var recordsArr = this.store.getRange();
			if (!Ext.isEmpty(recordsArr)) {
				var retAsgmBMList = Array();
				for (var i=0; i<recordsArr.length; i++) {
					var tmpObj = recordsArr[i].data;
					var retAsgmBM = {
						envelopeName : envelopTxf.getValue(),
						poolName : tmpObj.poolNameCd,
						effectiveFromDt : Ext.isEmpty(tmpObj.effectiveFrom) ? null :tmpObj.effectiveFrom,
						effectiveToDt : Ext.isEmpty(tmpObj.effectiveTo) ? null : tmpObj.effectiveTo
					};
					
					retAsgmBMList[i] = retAsgmBM;
				}
				
				return retAsgmBMList;
			}
		};
		
		this.bedEnvelopNameTxf = new Ext.form.TextField({
	        fieldLabel: msg.envelopname,
	        xtype: 'textfield',
	        name : 'envelopeName',
	        allowBlank:false,
	        value:config.envelopeName,
	        required: true,
	        anchor : '98%'
	    });
	    
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				handler: function(){
					var valuesMap = this.bedEnvelopPanel.getForm().getValues();
					if(!this.bedEnvelopPanel.getForm().isValid()){
						Ext.Msg.show({
							msg: 'Please enter envelop name and retry..!',
							icon : Ext.MessageBox.INFO,
							buttons: Ext.Msg.OK
						});
						return;
					}
					var bedEnvelopeBM = this.getData();
					if(this.mode =='edit' ){
						var envelopNameTxf = this.bedEnvelopNameTxf.getValue();
						bedEnvelopeBM.envelopeName = envelopNameTxf;
						BedManager.modifyBedEnvelope(bedEnvelopeBM, function(){
										mainThis.resetData();
										if(!Ext.isEmpty(mainThis.title)){
//											Ext.ux.event.Broadcast.publish('closeBedEnvelopWindow');
											layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
											Ext.ux.event.Broadcast.publish('reloadBedEnvelopGrid');
										}
						});
						
					}else{
						BedManager.addBedEnvelope(bedEnvelopeBM, function(retCode) {
										mainThis.resetData();
										if(!Ext.isEmpty(mainThis.title)){
//											Ext.ux.event.Broadcast.publish('closeBedEnvelopWindow');
//											Ext.ux.event.Broadcast.publish('closeBedEnvelopWindow');
											layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
											Ext.ux.event.Broadcast.publish('reloadBedEnvelopGrid');
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
		
		var selectedBedPool = '';
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
		
		this.poolName = new Ext.form.ComboBox(							{
	        fieldLabel: msg.bedpool,
	        xtype: 'combo',
	        name: 'poolName',
	        store: loadBedPools(),
			mode:'local',
			displayField:'code',
			valueField:'code',
		    triggerAction: 'all',
		    required: true,
		    forceSelection:true,
		    listeners: {
		    	select : function(thisCbx, record, index) {
		    		selectedBedPool = record.data;
		    	}
		    },
	        anchor : '98%'
	    });
		
		
		this.bedEnvelopPanel = new Ext.form.FormPanel({
				layout : 'column',
				frame : true,
				labelAlign : 'left',
				width : '100%',
				autoHeight : true,
				defaults:{
					columnWidth : .25
				},
				border : false,
				items: [
					{
						layout:'form',
						items:this.bedEnvelopNameTxf
					},
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.description,
						        xtype: 'textfield',
						        name : 'envelopeDesc',
						        value:config.envelopeDesc,
						        anchor : '98%'
						    }
						]
					},
					{
						layout:'form',
						columnWidth : .50,
						items:[
							{
						        fieldLabel: msg.facilitytype,
						        xtype: 'combo',
						        name: 'facilityType',
						        store: loadFacilityType(),
								mode:'local',
								displayField:'description',
								valueField:'code',
							    triggerAction: 'all',
							    value:config.facilityType,
						        anchor : '50%',
						        required: true,
						        allowBlank: false
						    }
						]
					},
					{
						layout:'form',
						items:[this.poolName]
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
						        iconCls :'add_btn',
						        xtype: 'button',
					    		handler: function() {
					    			var valuesMap = this.bedEnvelopPanel.getForm().getValues();
					    			if(valuesMap['poolName']!=''){
					    				var matchedIndex = this.infoGrid.store.findBy(function(record, id) {
						    													if (record.data.poolNameCd == valuesMap['poolName']) {
						    														return true;
						    													}
						    													});
						    			if (matchedIndex > -1) {
						    				Ext.Msg.show({
												msg: 'Entry for this bed pool already exists!',
												icon : Ext.MessageBox.ERROR,
												buttons: Ext.Msg.OK
											});
						    				return;
						    			}
						    			
						    			var record = {
						    				poolNameCd : valuesMap['poolName'],
						    				poolNameDesc : selectedBedPool.description,
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
						    				poolName:''
						    			}
						    			this.bedEnvelopPanel.getForm().setValues(resetPanel);
						    			selectedBedPool ='';
						    			mainThis.effectiveFormDate.setMaxValue(null);
						    			mainThis.effectiveToDate.setMinValue(null);
					    			}else{
					    				Ext.Msg.show({
											msg: 'Please select bed pool..! ',
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
						columnWidth:1,
						items:this.infoGrid
					},
					{
						columnWidth:1,
						items:this.buttonPanel
					}
				]
		});
		
		this.bedEnvelopPanel.on("clientvalidation", function(thisForm, isValid) {
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
			return this.bedEnvelopPanel;
	},
	getData : function() {
		var valuesMap = this.bedEnvelopPanel.getForm().getValues();
		var bedEnvelopeBM = {
			envelopeName : valuesMap['envelopeName'],
			description : valuesMap['envelopeDesc'],
			facilityTypeInd : valuesMap['facilityType'],
			bedEnvelopePoolAsgmList : this.infoGrid.getData(valuesMap, this.bedEnvelopNameTxf )
		};
		
		return bedEnvelopeBM;
	},
	resetData:function(){
		this.bedEnvelopPanel.getForm().reset();
		this.infoGrid.getStore().removeAll();
		if(this.mode == 'edit'){
			this.loadData(this);	
		}		
	},
	loadData:function(config){
		if(config.mode =='edit'){
			this.bedEnvelopNameTxf.disable();
			var record = this.infoGrid.getStore().recordType;
			if(config.assignedPoolsList!=null && config.assignedPoolsList.length>0 ){
				for(var i = 0; i<config.assignedPoolsList.length; i++){
					var bedPoolRecord = new record({
						poolNameCd:config.assignedPoolsList[i].poolName,
						poolNameDesc:config.assignedPoolsList[i].poolDescription,
						effectiveFrom:config.assignedPoolsList[i].effectiveFromDt,
						effectiveTo:config.assignedPoolsList[i].effectiveToDt
					});
					 this.infoGrid.getStore().add(bedPoolRecord);
				}
				
			}
			
		}
	},
	getGridPanel: function(){
		return this.infoGrid;
	}
});