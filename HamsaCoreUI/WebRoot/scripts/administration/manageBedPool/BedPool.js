Ext.namespace("administration.manageBedPool");

function showAddEditBedPoolWindow(config) {
	var bedPoolPanel = new administration.addBedPool.BedPool(config);
	var panelToAdd = bedPoolPanel.getPanel();
	panelToAdd.frame = true;
	panelToAdd.title = config.title;
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
	bedPoolPanel.loadData(config);
	}
	
//	Ext.ux.event.Broadcast.subscribe('closeBedPoolWindow',function(){
//	layout.getCenterRegionTabPanel().remove( panel, true );
//	Ext.ux.event.Broadcast.publish('loadBedPoolGrid');
//	});
	
	/*var win = new Ext.ux.Window({
		width: config.width,
		items: bedPoolPanel.getPanel(),
		title: config.title
	});
	bedPoolPanel.loadData(config);
	win.show();
	
	Ext.ux.event.Broadcast.subscribe('closeBedPoolWindow', function(){
		win.close();
	});*/
}
administration.manageBedPool.BedPool = Ext.extend(Object, {
	constructor : function(config) {
		
		this.bedPoolRecord = Ext.data.Record.create([
		{ name: 'bedPoolName'},
		{ name: 'poolDesc'},
        { name: 'totalNumberOfBed'},
        { name: 'numberOfAvailableBeds'},
        {name:'bedPoolUnitTypeAsgm'}
        ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '', totalProperty:'totalSize'}, this.bedPoolRecord),
        	proxy: new Ext.data.DWRProxy(BedManager.findBedPools, true)
		});
		
		var mainThis = this;
		
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.dataStore,
            displayMsg: 'Displaying pools {0} - {1} of {2}',
	        emptyMsg: "No pools to display"
		});
		
		
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					var selectedRows = selectionModel.getSelections();
					if( selectedRows.length > 1){
						mainThis.editBtn.disable();
						mainThis.viewUnitTypes.disable();
					}else{
						mainThis.editBtn.enable();
						mainThis.deleteBtn.enable();
						mainThis.viewUnitTypes.enable();
							
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
					mainThis.viewUnitTypes.disable();
				}
			}
		});
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditBedPoolWindow({
						 width:'90%'
						,title: 'Add bed pool'});
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
					BedManager.getBedPools(selectedRows[0].data.bedPoolName,null,null,null,function(bedPoolList){
						if(bedPoolList!=null){
							var config ={
								poolName: bedPoolList[0].bedPoolName,
								poolDesc: bedPoolList[0].poolDesc,
								assignedUnitTypeList: bedPoolList[0].bedPoolUnitTypeAsgm,
								width: '90%',
								title: 'Edit bed pool',
								mode: 'edit'
							};
							showAddEditBedPoolWindow(config);
						}
					})
				}
			}
		});
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			scope:this,
			text : 'Delete',
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				Ext.Msg.show({
							msg:msg.deleteBedPools,
							buttons: Ext.Msg.YESNO,
							icon: Ext.MessageBox.QUESTION,
							fn:function(btn){
								if( btn == configs.yes){
									if(selectedRows.length>0){
										var bedpoolList = new Array();
										for(var i = 0;i<selectedRows.length;i++){
											var rowdata = selectedRows[i].data;
											bedpoolList.push(rowdata.bedPoolName);
										}
										BedManager.removeBedPool(bedpoolList, function(){
											mainThis.infoGrid.getStore().reload();
											loadBedPools().reload();
										});
									}
								}
							}
						});
			}
		});
		this.viewUnitTypes = new Ext.Toolbar.Button({
			iconCls : 'view-icon',
			text : 'View unit types',
			scope:this,
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length>0){
					var unitTypeList =selectedRows[0].data.bedPoolUnitTypeAsgm;
					if(!Ext.isEmpty(unitTypeList)){
						var config ={
							mode:'edit',
							assignedUnitTypeList:unitTypeList
						};
						var bedPoolPanel = new administration.addBedPool.BedPool();
						var bedPoolAssocGrid = bedPoolPanel.getGridPanel();
						var topToolBar = bedPoolAssocGrid.getTopToolbar() ;
						//var bottomToolBar = bedPoolAssocGrid.getBottomToolbar();
						//bottomToolBar.hide();
						topToolBar.hide();
						var window = new Ext.ux.Window({
							width:'50%',
							items:bedPoolAssocGrid
						});
						bedPoolPanel.loadData(config);
						window.show();
					}else{
						Ext.Msg.show({
							msg: 'Selected bed pool doesn\'t have unit types..!',
							buttons: Ext.Msg.OK,
							icon: Ext.MessageBox.Info
						});
					}
				}
			}
		});
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 300,
				width : '100%',
				style : 'padding-right: 5px',
				autoScroll : true,
				viewConfig: {forceFit: true},
				border : true,
				sm:this.gridChk,
				store : this.dataStore,
				bbar : this.pagingBar,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						var selectedRecords = thisGrid.getSelectionModel().getSelections();
						if(selectedRecords.length >= 1){
							if (selectedRecords.length > 1) {
								mainThis.editBtn.disable();
								mainThis.viewUnitTypes.disable();
							} else {
								mainThis.editBtn.enable();
								mainThis.viewUnitTypes.enable();
							}
							mainThis.deleteBtn.enable();
						}else{
							mainThis.editBtn.disable();
							mainThis.viewUnitTypes.disable();
							mainThis.deleteBtn.disable();
						}
					} 
				},
				tbar : [
					this.addBtn, 
					{
						xtype : 'tbseparator'
					}, 
					this.editBtn,
					{
						xtype : 'tbseparator'
					}, 
					this.deleteBtn,
					{
						xtype : 'tbseparator'
					},
					this.viewUnitTypes
				],
				columns : [this.gridChk, {
							header : 'Name',
							dataIndex : 'bedPoolName',
							width : 100,
							sortable: true
						}, {
							header : 'Description',
							dataIndex : 'poolDesc',
							width : 140,
							sortable: true
						}, {
							header : 'Total beds',
							dataIndex : 'totalNumberOfBed',
							width : 100,
							sortable: true
						}, {
							header : 'Available beds',
							dataIndex : 'numberOfAvailableBeds',
							width : 100,
							sortable: true
						}]
			});
		
		Ext.ux.event.Broadcast.subscribe('reloadBedPoolGrid', function(){
			if(mainThis.infoGrid.getStore().data.length>0){
				mainThis.infoGrid.getStore().reload();
			}else{
				mainThis.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null]});
			}
			
		},mainThis,null,mainThis.getPanel());
			
	},
	getPanel : function() {
			return this.infoGrid;
	},
	search: function() {
//		this.dataStore.on('load', function(thisStore, recordArr, options) {
//			var x = 100;
//		}, this);
//		this.dataStore.load({params:{start:0, limit:25}, arg:[null,null,null,null]});
		
		var x = 100;
	}
});