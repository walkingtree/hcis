Ext.namespace("administration.manageBed");

function showAddEditBedWindow(config) {
	config.source = "abed";
	var bedPanel = new administration.addBed.Bed(config);
	var panelToAdd = bedPanel.getPanel();
	panelToAdd.title = config.title;
	panelToAdd.closable = true;
	panelToAdd.frame =true;
	panelToAdd.height = 420;
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
	bedPanel.loadData(config);
	}
	
//	Ext.ux.event.Broadcast.subscribe('closeBedWindow',function(){
//	layout.getCenterRegionTabPanel().remove( panel, true );
////	Ext.ux.event.Broadcast.publish('loadBedGrid');
//	},config.mainThis);
}

administration.manageBed.BedGrid = Ext.extend(Object, {
	constructor : function(config) {
		
		this.bedDetailRecord = Ext.data.Record.create([
		{ name: 'seqNbr', mapping:'seqNbr' },
		{ name: 'bedNumber', mapping:'bedNumber' },
		{ name: 'nursingUnitCode', mapping:'nursingUnit.code' },
		{ name: 'nursingUnit', mapping:'nursingUnit.description' },
        { name: 'bedStatusCode',  mapping:'bedStatus.code'},
        { name: 'bedStatusDesc', mapping:'bedStatus.description'},
        { name: 'bedTypeCode', mapping:'bedType.code'},
        { name: 'bedTypeDesc', mapping: 'bedType.description'},
        { name: 'roomNbrCode', mapping: 'roomNbr.code'},
        { name: 'roomNbr', mapping: 'roomNbr.description'},
        { name: 'floorNbr', mapping: 'floorNbr'},
        { name: 'siteName', mapping: 'siteName'},
        {name: 'bedDesc',mapping:'description'},
        {name: 'dailyCharge',mapping:'dailyCharge',convert:numberToString},
        {name: 'hourlyCharge',mapping:'hourlyCharge',convert:numberToString},
        {name: 'depositAmount',mapping:'depositAmount',convert:numberToString},
        {name:'specialFeatureAvailabilityList'}
        ]);
		
		this.dataStore = config.store ? config.store : new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'bedNumber', totalProperty:'totalSize'}, this.bedDetailRecord),
        	proxy: new Ext.data.DWRProxy(BedManager.findBeds, true)
		});
		var mainThis = this;
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.dataStore,
            displayMsg : 'Displaying beds {0} - {1} of {2}',
			emptyMsg : "No beds to display"
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
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditBedWindow({
						 width: 1000
						,title: 'Add bed'
						,mainThis : mainThis});
			}
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			disabled: true,
			handler : function() {
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length>0){
					var config ={
						bedNbr: selectedRows[0].data.bedNumber,
						bedDescription:selectedRows[0].data.bedDesc,
						siteName:selectedRows[0].data.siteName,
						roomNbr:selectedRows[0].data.roomNbrCode,
						floorNbr:selectedRows[0].data.floorNbr,
						nursingUnit:selectedRows[0].data.nursingUnitCode,
						bedType:selectedRows[0].data.bedTypeCode,
						bedStatus:selectedRows[0].data.bedStatusCode,
						dailyCharge:selectedRows[0].data.dailyCharge,
						hourlyCharge:selectedRows[0].data.hourlyCharge,
						depositAmount:selectedRows[0].data.depositAmount,
						assignedSpecialFeaturesList: selectedRows[0].data.specialFeatureAvailabilityList,
						width: 1000,
						title: 'Edit bed',
						mode: 'edit',
						mainThis : mainThis
					};
					showAddEditBedWindow(config);
					 this.infoGrid.getSelectionModel().clearSelections();
				}
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			xtype : 'tbbutton',
			iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length>0){
				
					Ext.Msg.show({
							title : 'Delete bed',
							msg : 'Are you sure you want to delete the selected bed(s)?',
							buttons : Ext.Msg.YESNOCANCEL,
							fn : function(buttonId, text, opt) {
								if (buttonId == "yes") {
									var romoveBedList = new Array();
									for(var i=0;i<selectedRows.length;i++){
										romoveBedList.push(selectedRows[i].data.bedNumber);
									}
									BedManager.removeBed(romoveBedList, function(retFlag){
										mainThis.infoGrid.getStore().reload();
									});
								 mainThis.infoGrid.getSelectionModel().clearSelections();
								}
							},
							animEl : 'elId',
							icon : Ext.MessageBox.QUESTION
					});
				}
			}
		});
		
		var columns = [this.gridChk, {
							header : 'Bed number',
							dataIndex : 'bedNumber',
							width : 70,
							sortable: true
						}, {
							header : 'Floor',
							dataIndex : 'floorNbr',
							width : 50,
							sortable: true
						}, {
							header : 'Room',
							dataIndex : 'roomNbr',
							width : 50,
							sortable: true
						}, {
							header : 'Unit',
							dataIndex : 'nursingUnit',
							width : 60,
							sortable: true
						}, {
							header : 'Bed type',
							dataIndex : 'bedTypeDesc',
							width : 80,
							sortable: true
						}, {
							header : 'Deposit amount',
							dataIndex : 'depositAmount',
							width : 85,
							sortable: true
						}, {
							header : 'Daily charge',
							dataIndex : 'dailyCharge',
							width : 80,
							sortable: true
						}, {
							header : 'Hourly charge',
							dataIndex : 'hourlyCharge',
							width : 80,
							sortable: true
						}, {
							header : 'Status',
							dataIndex : 'bedStatusDesc',
							width : 50,
							sortable: true
						}/*, {
							header : 'Expected DOA',
							dataIndex : 'expectedDOA',
							width : 80,
							sortable: true
						}, {
							header : 'Expected DOD',
							dataIndex : 'expectedDOD',
							width : 80,
							sortable: true
						}*/];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.includeColumns)){
	        var cs = [];
	        for(var i = 0, len = config.includeColumns.length; i < len; i++){
	            cs.push(columns[config.includeColumns[i]]);
	        }
	        columns = cs;
	    }
	    
	    var plugins = [new Ext.ux.grid.Search({
				iconCls:'icon-zoom'
				,readonlyIndexes:[]
				,disableIndexes:[]
				,minChars:3
				,autoFocus:true
//				,menuStyle:'radio'
			})];
			
		if (!Ext.isEmpty(config.plugins)) {
			plugins = config.plugins;
		}
	    
	    var tbar = [
	    	this.addBtn, 
	    	{
				xtype : 'tbseparator'
			}, 
			this.editBtn, 
			{
				xtype : 'tbseparator'
			}, 
			this.deleteBtn
		];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }
	    
		this.infoGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				height : 320,
				width: '99.5%',
//				hidden: true,
				viewConfig: {
					forceFit: true
				},
				border : true,
				store : this.dataStore,
				bbar : this.pagingBar,
				tbar : tbar,
				sm:this.gridChk,
				columns : columns,
				plugins : plugins,
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
		var tmpThis = this;
		Ext.ux.event.Broadcast.subscribe('reloadBedGrid', function(){
			if(tmpThis.infoGrid.getStore().data.length>0){
				tmpThis.infoGrid.getStore().reload();
			}else{
				tmpThis.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null,null,null,null,null,null]});
			}
			
		},this,null,tmpThis.getPanel());
		
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(valueMap,callback,bedFeaturesList) {
		if(!Ext.isEmpty(valueMap)){
			this.dataStore.load({params:{start:0, limit:10}, arg:[valueMap['bedNumber'],valueMap['roomNbr'],valueMap['floor'],
										valueMap['nursingUnit'],valueMap['patientId'],valueMap['patientname'],
										valueMap['panNumber'],valueMap['bedStatus'],
										valueMap['dischargeDateFrom']== 'undefined'?null:Date.parseDate(valueMap['dischargeDateFrom'],'d/m/Y'),
										valueMap['dischargeDateTo']== 'undefined'?null:Date.parseDate(valueMap['dischargeDateTo'],'d/m/Y'), bedFeaturesList]});
			// resetting the from date and todate values.->callback()
//			callback();
		}else{
			this.dataStore.load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null,null,null,null,null,null]});
		}
		
	}
});