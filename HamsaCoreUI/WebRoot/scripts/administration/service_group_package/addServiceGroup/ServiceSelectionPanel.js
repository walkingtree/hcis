Ext.namespace("administration.service_group_package.addServiceGroup");

administration.service_group_package.addServiceGroup.ServiceSelectionPanel = Ext.extend(Object, {
	constructor : function(config) {
		
		Ext.QuickTips.init();
		
		Ext.apply(this, config);
		
		if(Ext.isEmpty(config)){
			config = {};
		}
		
	    var plugins = [new Ext.ux.grid.Search({
			iconCls:'view-icon',
			readonlyIndexes:[],
			disableIndexes:[],
			minChars:1,
			autoFocus:true
		})];
		
		
		this.availableSvcsGrid = new administration.service_group_package.addServiceGroup.AvailableServicesGrid({title: svcAndGrpAndPkgMsg.availableServices, 
																								plugins: plugins, 
																								isPopup:config.isPopup});

		this.selectedSvcsGrid = new administration.service_group_package.addServiceGroup.SelectedServicesGrid({title: svcAndGrpAndPkgMsg.selectedServices});
		
		this.addBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.addButton,
			minWidth: 75,
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.addIntoAssignedServices,
			disabled:true,
			handler: function(){
				this.addBtnClicked();
				this.buttonsInitialState();
			}
		});
		
		this.addAllBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.addAllButton,
			minWidth: 75,
			disabled:true,
			tooltip: svcAndGrpAndPkgMsg.addIntoAssignedServices,
			scope:this,
			handler: function(){
				this.addAllBtnClicked();
				this.buttonsInitialState();
			}
		});
		
		this.removeBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.removeButton,
			minWidth: 75,						
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.removeFromAssignedServices,
			disabled:true,
			handler: function(){
				this.removeBtnClicked();
				this.buttonsInitialState();
			}
			
		});
		
		this.removeAllBtn = new Ext.Button({
			text: svcAndGrpAndPkgMsg.removeAllButton,
			minWidth: 75,						
			scope:this,
			disabled:true,
			tooltip: svcAndGrpAndPkgMsg.removeFromAssignedServices,
			handler: function(){
				this.removeAllBtnClicked();
				this.buttonsInitialState();
			}
		});
		
		this.buttonsPanel = new Ext.Panel({
			layout : 'column',
			height: 275,
			width: '100%',
			border : false,
			frame:false,
			defaults : {
				columnWidth:1,
				border:false,
				layout:'form'
			},
			items: [
				{
					style: 'padding-left:20px; padding-top:85px;',
					items:this.addBtn 
			    },{
					style: 'padding-left:20px; padding-top:10px;',
					items:this.addAllBtn
			    },{
					style: 'padding-left:20px; padding-top:10px;',
					items:this.removeBtn
			    },{
					style: 'padding-left:20px; padding-top:10px;',
					items:this.removeAllBtn
			    }
			]
		});
				
		this.serviceSelectionPanel = new Ext.Panel({
			layout:'column',
			frame:true,
			style: 'padding-top:10px;',
			autoHeight: true,
			items:[
				{
					columnWidth:.33,
					items: this.availableSvcsGrid.getAvlblSvcsGridPanel()
				},{
					columnWidth:.15,
					items: this.buttonsPanel
				},{
					columnWidth:.52,
					items: this.selectedSvcsGrid.getSlctdSvcsGridPanel()
				}
			]
		});
		
	},
	getPanel : function() {
		return this.serviceSelectionPanel;
	},
	addBtnClicked: function(){
		var avaliabelSelectedRows = this.availableSvcsGrid.getSelectedRows();
		if(avaliabelSelectedRows.length>0){
			this.selectedSvcsGrid.addEntryToGrid(avaliabelSelectedRows[0]);
			this.availableSvcsGrid.removeEntryToGrid(avaliabelSelectedRows[0]);
		}
	},
	addAllBtnClicked: function(){
		var avaliabelSelectedRows = this.availableSvcsGrid.getSelectedRows();
		if(avaliabelSelectedRows.length>0){
			for(var i =0; i<avaliabelSelectedRows.length; i++){
				this.selectedSvcsGrid.addEntryToGrid(avaliabelSelectedRows[i]);
				this.availableSvcsGrid.removeEntryToGrid(avaliabelSelectedRows[i]);
			}
		}
	},
	removeAllBtnClicked: function(){
		var assignedSelectedRows = this.selectedSvcsGrid.getSelectedRows();
		if(assignedSelectedRows.length>0){
			for(var i =0; i<assignedSelectedRows.length; i++){
				this.availableSvcsGrid.addEntryToGrid(assignedSelectedRows[i]);
				this.selectedSvcsGrid.removeEntryToGrid(assignedSelectedRows[i]);
			}
		}
	},
	removeBtnClicked: function(){
		var assignedSelectedRows = this.selectedSvcsGrid.getSelectedRows();
		if(assignedSelectedRows.length>0){
			this.availableSvcsGrid.addEntryToGrid(assignedSelectedRows[0]);
			this.selectedSvcsGrid.removeEntryToGrid(assignedSelectedRows[0]);
		}
	},
	
	getAssignedServices: function(){
		return this.selectedSvcsGrid.getData();
	},
	
	// loading the records at the time of modify service Group
	loadDataToGrids: function( serviceSummaryList ){
		var recordType = this.availableSvcsGrid.getAvlblSvcsGridPanel().getStore().recordType;
		if( serviceSummaryList!=null && serviceSummaryList.length > 0 ){
			 	for( var i = 0; i < serviceSummaryList.length; i++ ){
					var record = new recordType({
						serviceCode: serviceSummaryList[i].serviceCode,
						serviceName: serviceSummaryList[i].serviceName,
						serviceStatus: serviceSummaryList[i].serviceStatus.code,
						serviceStatusDesc: serviceSummaryList[i].serviceStatus.description,
						serviceCharge: serviceSummaryList[i].serviceCharge
					}); 
					this.loadAssignedService( record );
				}
				this.availableSvcsGrid.filterAvaliableServices( serviceSummaryList )
		}
	},
	
	loadAssignedService: function( record ){
		this.selectedSvcsGrid.addEntryToGrid( record );
	},
	
	// grid listeners, this clousure handleing the buttons state[Add, Add all, remove and removeAll buttons] 
	gridListeners: function(){
		//************************************avaliable service grid listeners start************************
		this.availableSvcsGrid.getAvlblSvcsGridPanel().on('cellclick', 
		function(grid, rowIndex, columnIndex, e){
			var selectionModel = grid.getSelectionModel();
			this.addButtonsState(selectionModel);
		},
		this);
		
		this.availableSvcsGrid.getAvlblSvcsGridPanel().getSelectionModel().on('rowselect', 
		function( SelectionModel , rowIndex, record ){
			this.addButtonsState(SelectionModel);
		},
		this);
		
		this.availableSvcsGrid.getAvlblSvcsGridPanel().getSelectionModel().on('rowdeselect', 
		function( SelectionModel , rowIndex, record ){
			this.addBtn.disable();
			this.addAllBtn.disable();
		},
		this);
		//************************************avaliable service grid listeners end************************
		
		//************************************assigned service grid listeners start************************
		
		this.selectedSvcsGrid.getSlctdSvcsGridPanel().on('cellclick', 
		function(grid, rowIndex, columnIndex, e){
			var selectionModel = grid.getSelectionModel();
			this.removeButtonsState(selectionModel);
		},
		this);
		
		this.selectedSvcsGrid.getSlctdSvcsGridPanel().getSelectionModel().on('rowselect', 
		function( SelectionModel , rowIndex, record ){
			this.removeButtonsState(SelectionModel);
		},
		this);
		
		this.selectedSvcsGrid.getSlctdSvcsGridPanel().getSelectionModel().on('rowdeselect', 
		function( SelectionModel , rowIndex, record ){
			this.removeBtn.disable();
			this.removeAllBtn.disable();
		},
		this);
		
		//************************************assigned service grid listeners end************************
	},
	buttonsInitialState: function(){
		this.addBtn.disable();
		this.removeAllBtn.disable();
		this.addAllBtn.disable();
		this.removeBtn.disable();
	},
	
	addButtonsState: function(selectionModel){
		this.addBtn.disable();
		this.addAllBtn.disable();
		if(selectionModel.getCount() >0){
			if(selectionModel.getCount() == 1){
				this.addBtn.enable();
				this.addAllBtn.enable();
			}else{
				this.addBtn.disable();
				this.addAllBtn.enable();
			}
		}
	},
	
	removeButtonsState: function(selectionModel){
		this.removeBtn.disable();
		this.removeAllBtn.disable();
		if(selectionModel.getCount() >0){
			if(selectionModel.getCount() == 1){
				this.removeBtn.enable();
				this.removeAllBtn.enable();
			}else{
				this.removeBtn.disable();
				this.removeAllBtn.enable();
			}
		}
	},
	// resetting the avaliable services and assigned services, 
	//and also getting initial state for all buttons[Add, AddAll, Remove and RemoveAll]
	resetData: function(){
		this.availableSvcsGrid.getAvlblSvcsGridPanel().getStore().reload();
		this.selectedSvcsGrid.getSlctdSvcsGridPanel().getStore().removeAll();
		this.buttonsInitialState();
	}
});