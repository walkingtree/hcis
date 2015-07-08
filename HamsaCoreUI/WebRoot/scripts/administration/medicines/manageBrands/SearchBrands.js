Ext.namespace("administration.medicines.manageBrands");


function showAddEditBrandWindow(config) {

	var configBrandPanel = new administration.medicines.addBrand.ConfigureBrand(config);
	
	var win = new Ext.Window({
		height: '100%',
		width: '40%',
		items: configBrandPanel.getPanel().setSize('100%','100%'),
		title: config.title,
		frame:true,
		modal:true,
		listeners: {
			beforeshow : function(comp){
				if(config.mode == pharmacyMsg.editMode){
					configBrandPanel.loadData(config);
				}
			}
		}
	});
	
	win.show();
	
	Ext.ux.event.Broadcast.subscribe('closeConfigureBrandWindow', function(){
		win.close();
	}, this, null, win);
}

administration.medicines.manageBrands.SearchBrands = Ext.extend(Object, {
	constructor : function(config) {
		
		var mainThis = this;
		
		this.brandRecord = Ext.data.Record.create([
	     	{name: "brandCode", type: "string"},
	      	{name: "brandName", type: "string", mapping:'description'},
	       	{name: "status", type: "bool", mapping:'active'}
	       ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.brandRecord),
        	proxy: new Ext.data.DWRProxy(BrandManagementController.getBrands, true)
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : pharmacyMsg.displayingBrandsMsg,
				emptyMsg : pharmacyMsg.noBrandsMsg
			});
		
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.markInactiveBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.markInactiveBtn.disable();
				}
			}
		});
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : pharmacyMsg.btnAdd,
			scope:this,
			handler : function() {
				mainThis.addButtonClicked();
			}
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : pharmacyMsg.btnEdit,
			scope:this,
			disabled:true,
			handler : function() {
				mainThis.editButtonClicked();
			}
		});
		
		this.markInactiveBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : pharmacyMsg.btnMarkInactive,
			scope:this,
			disabled:true,
			handler:function(){
				mainThis.markInactiveButtonClicked();
			}
		});
		
		var gridColumns = 
						[this.gridChk, {
							header : pharmacyMsg.brandCode,
							dataIndex : 'brandCode',
							width : 120,
							sortable: true
						}, {
							header : pharmacyMsg.brandName,
							dataIndex : 'brandName',
							width : 300,
							sortable: true
						}, {
							header : pharmacyMsg.status,
							dataIndex : 'status',
							width : 100,
							sortable: true
						}];
						
		var gridButtonsBar = [
					'-',this.addBtn,
					'-',this.editBtn,
					'-',this.markInactiveBtn,
					'-'];
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height: 300,
				autoScroll : true,
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				remoteSort:true,
				viewConfig:{
					forceFit : true
				},
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						mainThis.setGridButtonsState(thisGrid.getSelectionModel());
					} 
				},
				tbar : gridButtonsBar,
				columns : gridColumns
			});
		
		this.searchBtn = new Ext.Button({
	        text: pharmacyMsg.btnSearch,
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	handler: function() {
	    		mainThis.searchButtonClicked();
	    	},
	    	scope: this
	    });
	    
	     this.resetBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: pharmacyMsg.resetSearchCreteria,
			handler: function(){
				this.resetSearch();
			}

		});
	    
	    this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
			buttons:[
				this.searchBtn,
				this.resetBtn 
				]
		});	
		
		this.searchBrandsPanel = new Ext.form.FormPanel({
			layout : 'column',
			labelAlign : 'left',
			autoHeight: true,
//			style:'padding:5px',
			border : false,
			items: [
				{
					columnWidth:.30,
					border:false,
					layout:'form',
					labelWidth:80,
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.brandCode,
							name:'brandCode',
							anchor:'90%'
						  }]
			    },{
					columnWidth:.30,
					border:false,
					layout:'form',
					labelWidth:80,
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.brandName,
							name:'brandName',
							anchor:'90%'
						  }]
				},{
					columnWidth:.30,
					border:false,
					layout:'form',
					labelWidth:60,
					items:[{
							xtype:'optcombo',
							fieldLabel:pharmacyMsg.status,
							mode:'local',
							store:loadStatusType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							name:'status',
							anchor:'70%'
					      }]
				},{
					columnWidth:1,
					border:false,
					layout:'form',
					items: this.buttonPanel
				},{
					columnWidth:1,
					width: '100%',
					items:this.infoGrid
				}
			]
		});
	
		Ext.ux.event.Broadcast.subscribe('reloadSearchBrandsGrid', function(){
			if(this.infoGrid.getStore().data.length > 0){
				this.infoGrid.getStore().reload();
			}
			this.editBtn.disable();
			this.markInactiveBtn.disable();
		}, this, null, mainThis.getPanel());
	
		this.infoGrid.on("render", function(thisForm){
			this.searchButtonClicked();
		}, this);
		
		this.searchBrandsPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

		
	},
	
	getPanel : function() {
		return this.searchBrandsPanel;
	},
	
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		var status = true;
		for(var i = 0 ; i< selectedRows.length ; i++){
				if(selectedRows[i].data.status == false){
					status = false;
				}
			}
		this.editBtn.disable();
		this.markInactiveBtn.disable();
		if( selectedRows.length == 1){
			this.editBtn.enable();
			if(status)
			this.markInactiveBtn.enable();
		} 
		else if( selectedRows.length > 1){
			this.editBtn.disable();
			if(status)
			this.markInactiveBtn.enable();
		}
	},
	
	addButtonClicked : function(){
		showAddEditBrandWindow({
			title: pharmacyMsg.addBrand,
			mode:pharmacyMsg.addMode,
			isPopup: true
		});
	},
	
	editButtonClicked : function(){
		var rowSelectedForEdit = this.infoGrid.getSelectionModel().getSelections()[0].data;
		showAddEditBrandWindow({
			title: pharmacyMsg.editBrand,
			mode:pharmacyMsg.editMode,
			selectedRow : rowSelectedForEdit,
			isPopup: true
		});
	},
	
	markInactiveButtonClicked: function(){
		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		if(selectedRows.length>0){
			var brandsList = new Array();
			for(var i = 0;i<selectedRows.length;i++){
				var rowdata = selectedRows[i].data;
				brandsList.push(rowdata.brandCode);
			}
			BrandManagementController.removeBrands(brandsList, function(){
			});
		}
		Ext.ux.event.Broadcast.publish('reloadSearchBrandsGrid');
	},
	
	searchButtonClicked : function(){
   		var valuesMap = this.searchBrandsPanel.getForm().getValues();
   		this.infoGrid.store.removeAll();
    		
   		var tmpStatus = null;
 		if(valuesMap.status === pharmacyMsg.activeStatus) {
 			 tmpStatus = true;
  		} else if(valuesMap.status === pharmacyMsg.inactiveStatus) {
			 tmpStatus = false;
		}
		
		this.infoGrid.store.load({params:{start:0, limit:this.pagingBar.pageSize}, 
								  arg:[
								  	   valuesMap['brandCode'], 
  							   		   valuesMap['brandName'],
  							   		   tmpStatus
  							      ]
  								});	
  								
  		this.infoGrid.store.on('load',function(){
  			this.editBtn.disable();
  			this.markInactiveBtn.disable();
  		},this);
  								
	},
	resetSearch: function(){
		this.searchBrandsPanel.getForm().reset();
		this.searchButtonClicked();
	}
	
});