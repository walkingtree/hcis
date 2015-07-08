Ext.namespace("administration.manageBedEnvelop");

function showAddEditBedEnvelopWindow(config) {
	var bedEnvelopPnl = new administration.addBedEnvelope.BedEnvelop(config);
	var panelToAdd = bedEnvelopPnl.getPanel();
	
	panelToAdd.frame = true;
	panelToAdd.title = config.title;
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
	bedEnvelopPnl.loadData(config);
	}
	
	//bedPanel.getFocus(config);
	
//	Ext.ux.event.Broadcast.subscribe('closeBedEnvelopWindow',function(){
////	layout.getCenterRegionTabPanel().remove( panel, true );
////	Ext.ux.event.Broadcast.publish('loadBedEnvelopGrid');
//	});
	
	
	/*var win = new Ext.ux.Window({
		width: config.width,
		items: panel,
		title: config.title
	});
	bedEnvelopPnl.loadData(config);
	panel.doLayout();
	win.show();
	Ext.ux.event.Broadcast.subscribe('closeBedEnvelopWindow', function(){
		win.close();
	});*/
}
administration.manageBedEnvelop.SearchBedEnvelop = Ext.extend(Object, {
	constructor : function(config) {
		
		this.bedEnvelopeRecord = Ext.data.Record.create([
		{ name: 'envelopeName'},
		{ name: 'description'},
        { name: 'facilityTypeInd', convert: function(val, rec){
        							var index = facilityTypeStore.find("code", val);
        							var record = facilityTypeStore.getAt(index);
        							return record.data.description;}},
        {name:'bedEnvelopePoolAsgmList'}
        ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '', totalProperty:'totalSize'}, this.bedEnvelopeRecord),
        	proxy: new Ext.data.DWRProxy(BedManager.findBedEnvelopes, true)
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.dataStore,
            displayMsg : 'Displaying envelops {0} - {1} of {2}',
			emptyMsg : "No envelops to display"
		});
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					var selectedRows = selectionModel.getSelections();
					if( selectedRows.length > 1){
						mainThis.editBtn.disable();
						mainThis.viewPools.disable();
					}else{
						mainThis.editBtn.enable();
						mainThis.deleteBtn.enable();
						mainThis.viewPools.enable();
							
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
					mainThis.viewPools.disable();
				}
			}
		});
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditBedEnvelopWindow({
						 width: '90%',
						 title: 'Add bed envelop'});	
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
					BedManager.getBedEnvelopes(selectedRows[0].data.envelopeName,null,null,null,null,function(bedEnvelopList){
						if(bedEnvelopList!=null){
							var config ={
								envelopeName: bedEnvelopList[0].envelopeName,
								envelopeDesc: bedEnvelopList[0].description,
								facilityType: bedEnvelopList[0].facilityTypeInd,
								assignedPoolsList: bedEnvelopList[0].bedEnvelopePoolAsgmList,
								width: '90%',
								title: 'Edit bed envelop',
								mode: 'edit'
							};
							showAddEditBedEnvelopWindow(config);
						}
					})
				}
			}
		});
		var mainThis = this;
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				Ext.Msg.show({
							msg: msg.deleteBedEnvelops,
							buttons: Ext.Msg.YESNO,
							icon: Ext.MessageBox.QUESTION,
							fn:function(btn){
								if( btn == configs.yes ){
									if(selectedRows.length>0){
										var bedEnvelopList = new Array();
										for(var i = 0;i<selectedRows.length;i++){
											var rowdata = selectedRows[i].data;
											bedEnvelopList.push(rowdata.envelopeName);
										}
										BedManager.removeBedEnvelope(bedEnvelopList, function(){
											mainThis.infoGrid.getStore().reload();
										});
									}
								}
							}
						});
			}
		});
		
		this.viewPools = new Ext.Toolbar.Button({
			iconCls : 'view-icon',
			text : 'View pools',
			scope:this,
			disabled: true,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length>0){
					var bedPoolsList =selectedRows[0].data.bedEnvelopePoolAsgmList;
					if( bedPoolsList!= null && bedPoolsList.length>0){
					// because we are passing list of associations, and mode is edit, because in the loadData method we are checking that config.mode =='edit'
					// then only it inserts the entries.	
						var config ={
							mode:'edit',
							assignedPoolsList:bedPoolsList
						};
						var bedEnvelop = new administration.addBedEnvelope.BedEnvelop();
						var bedEnvelopAssocGrid = bedEnvelop.getGridPanel();
						var topToolBar = bedEnvelopAssocGrid.getTopToolbar() ;
						//var bottomToolBar = bedEnvelopAssocGrid.getBottomToolbar();
						//bottomToolBar.hide();
						topToolBar.hide();
						var window = new Ext.ux.Window({
							width:'50%',
							items:bedEnvelopAssocGrid
						});
						bedEnvelop.loadData(config);
						window.show();
					}else{
						Ext.Msg.show({
							msg: 'Selected bed envelop doesn\'t have bed pools..!',
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
				autoScroll : true,
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				viewConfig: {forceFit: true},
				sm:this.gridChk,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						var selectedRecords = thisGrid.getSelectionModel().getSelections();
						if(selectedRecords.length >= 1){
							if (selectedRecords.length > 1) {
								mainThis.editBtn.disable();
								mainThis.viewPools.disable();
							} else {
								mainThis.editBtn.enable();
								mainThis.viewPools.enable();
							}
							mainThis.deleteBtn.enable();
						}else{
							mainThis.editBtn.disable();
							mainThis.viewPools.disable();
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
				this.viewPools
				],
				columns : [this.gridChk, {
							header : 'Envelop name',
							dataIndex : 'envelopeName',
							width : 100,
							sortable: true
						}, {
							header : 'Description',
							dataIndex : 'description',
							width : 150,
							sortable: true
						}, {
							header : 'Facility type',
							dataIndex : 'facilityTypeInd',
							width : 100,
							sortable: true
						}]
			});
		
		
		this.searchBtn = new Ext.Button({
	        text: 'Search',
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	handler: function() {
	    		var valuesMap = this.searchBedEnvelopPanel.getForm().getValues();
	    		this.infoGrid.store.load({params:{start:0, limit:10}, arg:[valuesMap['envelopeName'], 
	    							 valuesMap['facilityTypeInd'],
	    							 valuesMap['poolName'],null,null]});
	    	},
	    	scope: this
	    });
	    
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			buttons:[this.searchBtn,{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.searchBedEnvelopPanel.getForm().reset();
					this.infoGrid.store.load({params:{start:0, limit:10}, arg:[null,null,null,null,null]});
				}
			}]
		});				  

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
		
		this.searchBedEnvelopPanel = new Ext.form.FormPanel({
			layout : 'column',
			labelAlign : 'left',
			width : '98%',
			height:'auto',
//			style:'padding:5px',
			defaults:{
				columnWidth : .33
			},
			border : false,
			items: [
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Envelop name',
					        xtype: 'textfield',
					        name: 'envelopeName',
					        anchor : '98%'
					    }
					]
				},
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Facility type',
					        xtype: 'optcombo',
					        name: 'facilityTypeInd',
					        store: loadFacilityType(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
					        anchor : '98%'
					    }
					]
				},
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Pool name',
					        xtype: 'optcombo',
					        name: 'poolName',
					        store: loadBedPools(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
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
				},this.buttonPanel,
				{
					columnWidth:1,
					items:this.infoGrid
				}
				]
		});
		
		Ext.ux.event.Broadcast.subscribe('reloadBedEnvelopGrid', function(){
			if(mainThis.infoGrid.getStore().data.length>0){
				mainThis.infoGrid.getStore().reload();
			}else{
				mainThis.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null]});
			}
			
		},this,null,mainThis.getPanel());

		this.searchBedEnvelopPanel.on("render", function(){this.infoGrid.store.load({params:{start:0, limit:10}, arg:[null,null,null,null,null]});}, this);
		
		this.searchBedEnvelopPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel : function() {
		return this.searchBedEnvelopPanel;
	}
});