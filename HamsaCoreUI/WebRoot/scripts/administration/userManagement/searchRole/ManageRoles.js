
Ext.namespace("administration.userManagement.searchRole");

administration.userManagement.searchRole.ManageRoles = Ext.extend(Object,{
	constructor: function() {
		Ext.QuickTips.init();
		var joiningDate;
		var mainThis = this;
		this.roleRecord = Ext.data.Record.create([
		    {name: 'roleName', type:'string', mapping:'name'},
		    {name:'effectiveFrom', type:'date'},
		    {name:'effectiveTo', type:'date'}
		]);
		
		this.roleStore = new Ext.data.Store({
			proxy: new Ext.data.DWRProxy(RoleManagementController.getRoles, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, this.roleRecord),
		    remoteSort: true
		});
		
		this.pageBar = new Ext.WTCPagingToolbar({
            store: this.roleStore,
            displayMsg: 'Displaying records {0} - {1} of {2}',
	        emptyMsg: "No roles to display"
		});
		
		this.gridChk =  new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.gridToolbar.editBtn.disable();
					mainThis.gridToolbar.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.gridToolbar.editBtn.disable();
					mainThis.gridToolbar.deleteBtn.disable();
				}
			}
		});
		
		this.gridToolbar = new utils.GridToolBar();
		
		this.gridPanel = new Ext.grid.GridPanel({
			autoScroll:true,
			stripeRows:true,
			height:300,
			sm:this.gridChk,
			ds: this.roleStore,
			viewConfig:{ forceFit: true},
			columns: [
				this.gridChk,
				{header:'Role name',width:200, sortable: true, dataIndex:'roleName'},
				{header:'Effective from', width:150, sortable: true, dataIndex:'effectiveFrom',renderer : Ext.util.Format.dateRenderer('d/m/Y')},
				{header: 'Effective to', width:150, sortable: true, dataIndex:'effectiveTo',renderer : Ext.util.Format.dateRenderer('d/m/Y')}
			],
			bbar:this.pageBar,
			listeners:{
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
					this.setGridButtonsState( thisGrid.getSelectionModel() );
				},
				scope: this
				},
			tbar:this.gridToolbar
		});
		
		this.searchBtn = new Ext.Button({
	        text: userMsg.search,
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	tooltip : userMsg.findrole,
	    	scope:this,
	    	handler: function() {
	    		this.searchHandler();
	    	},
	    	scope: this
	    });
	    
	    this.resetBtn = new Ext.Button({
			text:userMsg.reset,
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: userMsg.resetSearchCreteria,
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
		this.mainPanel = new Ext.form.FormPanel({
			layout:'column',
			title:userMsg.manageroles,
			frame: true,
			defaults:{
				columnWidth:1,
				border: false
			},
			items:[
				{
					layout:'column',
					defaults:{
						columnWidth:.33,
						border: false
					},
					items:[
						{
						layout:'form',
						items:[
							{
								xtype:'textfield',
								anchor:'90%',
								fieldLabel:userMsg.rolename,
								name:'roleName'
							}
						]
						},
						{
						layout:'form',
						items:[
							{
								xtype:'wtcdatefield',
								anchor:'90%',
								fieldLabel:userMsg.effectivefrom,
								name:'effectiveFrom',
								listeners:{
									blur: function( date ){
										joiningDate = date.getValue();
									}
								}
							}
						]
						},
						{
						layout:'form',
						items:[
							{
								xtype:'wtcdatefield',
								anchor:'90%',
								fieldLabel:userMsg.effectiveto,
								name:'effectiveTo',
								listeners:{
									blur: function( date ){
										if(!Ext.isEmpty(joiningDate)){
											var value = compareTwoDates(joiningDate, date.getValue());
											if(! value){
												date.markInvalid('Invalid date ');
												Ext.Msg.show({
												msg: userMsg.joiningdatecomparition,
												buttons: Ext.Msg.OK,
												icon : Ext.MessageBox.WARNING
												});
											}
										}
									}
								}
							}
						]
						}
					]
				},
				this.buttonPanel, 
				this.gridPanel
				]
		});
		
		this.initialListeners();
		
		this.gridPanel.getStore().on('load',function(){
			this.gridToolbar.getEditBtn().disable();
			this.gridToolbar.getDeleteBtn().disable();
		},this);
		
		this.mainPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel: function() {
		return this.mainPanel;
	},
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.gridToolbar.editBtn.disable();
		this.gridToolbar.deleteBtn.disable();
		if( selectedRows.length == 1){
			this.gridToolbar.editBtn.enable();
			this.gridToolbar.deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.gridToolbar.editBtn.disable();
			this.gridToolbar.deleteBtn.enable();
		}
	},
	initialListeners: function(){
		
		this.gridToolbar.addBtn.on('click', function(){
			this.addButtonClicked();
		}, this);
		
		this.gridToolbar.editBtn.on('click', function(){
			this.editButtonClicked();
		}, this);
		
		this.gridToolbar.deleteBtn.on('click', function(){
			this.deleteButtonHandler();
		}, this);
		
		this.gridPanel.on('render', function(){
			this.searchHandler();
		}, this);
		
	},
	loadGrid : function(){
		this.gridPanel.getStore().reload();
	},
	
	searchHandler: function(){
		this.roleStore.load({params:{start:0, limit:10}, arg:[this.mainPanel.getForm().getValues()]});
	},
	addButtonClicked: function(){
		var mainThis = this;

		var addRolePanel = new administration.userManagement.addRole.AddRole({
		gridObj:this.gridPanel,
		manageRoleMainPanel:this.mainPanel,
		isPopup : true
		});
	 	var panelToAdd = new Ext.Panel({
	 		width:'100%',
	 		height:'100%',
	 		items:addRolePanel.getPanel()
	 	});
//	 	window.show();

	 	panelToAdd.frame = true;
	 	panelToAdd.title = userMsg.addrole;
	 	panelToAdd.closable = true;
	 	panelToAdd.height = 420;
	 	
		var activeTab = layout.getCenterRegionTabPanel().add( panelToAdd );
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddRoleWindow', function(){
//			layout.getCenterRegionTabPanel().remove( activeTab , true );
			layout.getCenterRegionTabPanel().setActiveTab(this.mainPanel);	
			layout.getCenterRegionTabPanel().doLayout();
		},this, null , mainThis.getPanel());
		
		
	},
	editButtonClicked: function(){
		var mainThis = this;
     	var grid = this.gridPanel;
     	var form = this.mainPanel;
     	var selectedRows = grid.getSelectionModel().getSelections();
     	if(selectedRows!=null){
     		var rowData = selectedRows[0].data;
     		var roleSummary ={
     			id:rowData.roleName,
     			name:rowData.roleName,
 				effectiveFrom:rowData.effectiveFrom,
 				effectiveTo:rowData.effectiveTo
     		}
         	RoleManagementController.getRoleDetail(roleSummary, function(role){
         		if(role!=null){
         			var config ={
		         		roleName:role.id,
		         		description:role.description,
		         		effectiveFrom:role.startDtm,
		         		mode:'edit',
		         		effectiveTo:role.endDtm,
		         		assignedServiceList:role.roleHasServices,
		         		gridObj:grid,
						manageRoleMainPanel:form,
						isPopup : true
         			}
         			var addRolePanel = new administration.userManagement.addRole.AddRole(config);
		         	var panelToAdd = new Ext.Panel({
		         		
		         		width:'100%',
		         		height:'100%',
		         		closeAction:'hide',
		         		items:addRolePanel.getPanel()
		         	});
		         	
		         	panelToAdd.frame = true;
		         	panelToAdd.title = userMsg.editrole;
		         	panelToAdd.closable = true;
		         	panelToAdd.height = 420;
		         	
//		         	window.show();
					var activeTab = layout.getCenterRegionTabPanel().add( panelToAdd );
					addRolePanel.loadData(config);
					layout.getCenterRegionTabPanel().setActiveTab( activeTab );
					layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeEditRoleWindow', function(){
//			layout.getCenterRegionTabPanel().remove( activeTab , true );	
			layout.getCenterRegionTabPanel().setActiveTab(this.mainPanel);	
			layout.getCenterRegionTabPanel().doLayout();
		},this , null , mainThis.getPanel());
					
         		}
         	});
     	}
	},
	viewButtonHandler: function(){
     	var grid = this.gridPanel;
     	var form = this.mainPanel;
     	var selectedRows = grid.getSelectionModel().getSelections();
     	if(selectedRows!=null){
     		var rowData = selectedRows[0].data;
     		var roleSummary ={
     			id:rowData.roleName,
     			name:rowData.roleName,
 				effectiveFrom:rowData.effectiveFrom,
 				effectiveTo:rowData.effectiveTo
     		}
         	RoleManagementController.getRoleDetail(roleSummary, function(role){
         		if(role!=null){
         			var config ={
		         		roleName:role.id,
		         		description:role.description,
		         		effectiveFrom:role.startDtm,
		         		mode:'edit',
		         		flag:true,
		         		effectiveTo:role.endDtm,
		         		assignedServiceList:role.roleHasServices,
		         		gridObj:grid,
						manageRoleMainPanel:form
         			}
         			var addRolePanel = new administration.userManagement.addRole.AddRole(config);
		         	var window = new Ext.Window({
		         		title:userMsg.viewrole,
		         		width:'60%',
		         		height:'50%',
		         		frame:true,
		         		border:false,
		         		closeAction:'hide',
		         		items:addRolePanel.getPanel()
		         	});
		         	window.show();
		         	addRolePanel.loadData(config);
		         	
         		}
         	});
     	}
	},
	deleteButtonHandler: function(){
	 	var gridPnl = this.gridPanel;
	 	var selectedRows = gridPnl.getSelectionModel().getSelections();
	 	var roleSummarList = new Array();
	 	if(selectedRows!=null && selectedRows.length>0) {
	 		for(var i = 0; i<selectedRows.length; i++){
	 			var rowData = selectedRows[i].data;
	 			var roleSummary = {
	 				id:rowData.roleName,
	 				effectiveFrom:rowData.effectiveFrom,
	 				effectiveTo:rowData.effectiveTo
	 			}
	 			roleSummarList.push(roleSummary);
	 		}
	 	}
	 	RoleManagementController.removeRoles(roleSummarList,{
	 		callback: function(){
	 			this.gridToolbar.getEditBtn().disable();
				this.gridToolbar.getDeleteBtn().disable();
				this.loadGrid();
	 		},
	 		callbackScope:this
	 	});
					 
	},
	resetSearch: function(){
		this.mainPanel.getForm().reset();
		this.searchHandler();
	}
});