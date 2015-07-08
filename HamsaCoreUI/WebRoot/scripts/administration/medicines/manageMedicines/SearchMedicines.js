Ext.namespace("administration.medicines.manageMedicines");


function showAddEditMedicineWindow(config) {

	var configMedicinePanel = new administration.medicines.addMedicine.ConfigureMedicine(config);
	
	var win = new Ext.Window({
		height: '100%',
		width: 650,
		items: configMedicinePanel.getPanel(),
		title: config.title,
		frame:true,
		modal:true,
		listeners: {
			beforeshow : function(comp){
				if(config.mode == pharmacyMsg.editMode){
					configMedicinePanel.loadData(config);
				}
			}
		}
	});
	
	win.show();
	
	Ext.ux.event.Broadcast.subscribe('closeConfigureMedicineWindow', function(){
		win.close();
	}, this, null, win);
}

administration.medicines.manageMedicines.SearchMedicines = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
		this.medicineRecord = Ext.data.Record.create([
		     {name: "medicineCode", mapping:"medicineCode", type: "string"},
		     {name: "medicineName", mapping:"medicineName", type: "string"},
		     {name: "brandCode", mapping:"brand.code", type:"string"},
		     {name: "brandName", mapping:"brand.description", type: "string"},
		     {name: "medicineTypeCode", mapping:"medicineType.code", type:"string"},
		     {name: "medicineType", mapping:"medicineType.description", type: "string"},
		     {name: "strength", mapping:"strength", type: "string"},
		     {name: "dosagePerDay", mapping:"maximumDosage", type: "string"},
		     {name: "status", mapping:"active", type: "bool"}
	       ]);
        
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.medicineRecord),
        	proxy: new Ext.data.DWRProxy(MedicineManagementController.getMedicines, true)
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : pharmacyMsg.displayingMedicinesMsg,
				emptyMsg : pharmacyMsg.noMedicinesMsg
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
						[this.gridChk, 
						 {header: pharmacyMsg.medicineCode, width: 100, sortable: true, dataIndex:'medicineCode'},
  						 {header: pharmacyMsg.medicineName, width: 225, sortable: true, dataIndex:'medicineName'},
						 {header: pharmacyMsg.brandName, width: 225, sortable: true, dataIndex:'brandName'},
						 {header: pharmacyMsg.medicineType, width: 100, sortable: true, dataIndex:'medicineType'},
						 {header: pharmacyMsg.strength, width: 75, sortable: true, dataIndex:'strength'},
						 {header: pharmacyMsg.dosagePerDay, width: 100, sortable: true, dataIndex:'dosagePerDay'},
						 {header: pharmacyMsg.status, width: 75, sortable: true, dataIndex:'status'}
						];
						
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
			text:pharmacyMsg.btnReset,
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
		
		this.searchMedicinesPanel = new Ext.form.FormPanel({
			layout : 'column',
			labelAlign : 'left',
			autoHeight: true,
			autoHeight: true,
//			style:'padding:5px',
			border : false,
			items: [
				{
					columnWidth:.33,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.medicineCode,
							name:'medicineCode',
							anchor:'90%'
						  },{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.strength,
							name:'strength',
							anchor:'70%'
						  },{
							xtype:'optcombo',
							fieldLabel:pharmacyMsg.medicineType,
							mode:'local',
							store: loadMedicineType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							name:'medicineType',
							anchor:'90%'
					      }]
			    },{
					columnWidth:.33,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.medicineName,
							name:'medicineName',
							anchor:'90%'
						  },{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.dosagePerDay,
							name:'dosagePerDay',
							anchor:'70%'
						  }]
				},{
					columnWidth:.33,
					border:false,
					layout:'form',
					items:[{
							xtype:'optcombo',
							fieldLabel:pharmacyMsg.brandName,
							mode:'local',
							store: loadBrand(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							name:'brandName',
							anchor:'95%'
					      },{
							xtype:'optcombo',
							fieldLabel:pharmacyMsg.status,
							mode:'local',
							store:loadStatusType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							name:'status',
							anchor:'80%'
					      },this.buttonPanel]
				},{
					columnWidth:1,
					width: '100%',					
					items:this.infoGrid
				}
			]
		});
	
		Ext.ux.event.Broadcast.subscribe('reloadSearchMedicinesGrid', function(){
			if(mainThis.infoGrid.getStore().data.length > 0){
				mainThis.infoGrid.getStore().reload();
			}
			mainThis.editBtn.disable();
			mainThis.markInactiveBtn.disable();
		}, this, null, mainThis.getPanel());
	
		this.infoGrid.on("render", function(thisForm){
			this.searchButtonClicked();
		}, this);
		
		this.searchMedicinesPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

		
	},
	
	getPanel : function() {
		return this.searchMedicinesPanel;
	},
	
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		var status = true;
		for(var i = 0 ; i < selectedRows.length ; i++ ){
			if(selectedRows[i].data.status == false){
				status = false
			}
		}
		this.editBtn.disable();
		this.markInactiveBtn.disable();
		if( selectedRows.length == 1){
			this.editBtn.enable();
			if(status)
			this.markInactiveBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			if(status)
			this.markInactiveBtn.enable();
		}
	},
	
	addButtonClicked : function(){
		showAddEditMedicineWindow({
			title: pharmacyMsg.addMedicine,
			mode:pharmacyMsg.addMode,
			isPopup:true
		});
	},
	
	editButtonClicked : function(){
		var rowSelectedForEdit = this.infoGrid.getSelectionModel().getSelections()[0].data;
		showAddEditMedicineWindow({
			title: pharmacyMsg.editMedicine,
			mode:pharmacyMsg.editMode,
			selectedRow : rowSelectedForEdit,
			isPopup: true
		});
	},
	
	markInactiveButtonClicked: function(){
		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		if(selectedRows.length>0){
			var medicinesList = new Array();
			for(var i = 0;i<selectedRows.length;i++){
				var rowdata = selectedRows[i].data;
				medicinesList.push(rowdata.medicineCode);
			}
			MedicineManagementController.removeMedicines(medicinesList, function(){
			});
		}
		Ext.ux.event.Broadcast.publish('reloadSearchMedicinesGrid');
	},
	
	searchButtonClicked : function(){
   		var valuesMap = this.searchMedicinesPanel.getForm().getValues();
   		this.infoGrid.store.removeAll();
    		
   		var tmpStatus = null;
 		if(valuesMap.status === pharmacyMsg.activeStatus) {
 			 tmpStatus = true;
  		} else if(valuesMap.status === pharmacyMsg.inactiveStatus) {
			 tmpStatus = false;
		}
		
		this.infoGrid.store.load({params:{start:0, limit:this.pagingBar.pageSize}, 
								  arg:[
								  	   valuesMap['medicineCode'], 
  							   		   valuesMap['medicineName'],
  							   		   valuesMap['brandName'],
  							   		   valuesMap['strength'],
  							   		   valuesMap['medicineType'],
  							   		   valuesMap['dosagePerDay'],
  							   		   tmpStatus
  							      ]
  								});	
  		this.infoGrid.store.on('load',function(){
  			this.editBtn.disable();
  			this.markInactiveBtn.disable();
  		},this);						
	},
	resetSearch: function(){
		this.searchMedicinesPanel.getForm().reset();
		this.searchButtonClicked();
	}
	
});