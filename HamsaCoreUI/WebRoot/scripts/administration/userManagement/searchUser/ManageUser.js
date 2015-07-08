Ext.namespace("administration.userManagement.searchUser");

function showAddEditUserWindow( config , mainThis ){
	var addUserPnl = new administration.userManagement.addUser.AddUser( config );
     	var tmpThis = this;
     	var paneltoAdd = new Ext.Panel({
     		items: addUserPnl.getPanel(),
     		width:'100%',
     		height:'100%',
     		closeAction:'hide',
     		modal:true,
     		listeners:{
	 			add: function(comp){
	 				if( config.mode == userMsg.editMode ){
		 				if( !Ext.isEmpty( config.country )){
		 					userManagerStateStore.on('load', function() {
		 						addUserPnl.getPanel().getForm().setValues(config);
		 						userManagerStateStore.events['load'].clearListeners();
		 					},this);
		 					userManagerStateStore.load({params:{start:0, limit:10}, arg:[config.country]});
		 				}else{
		 					addUserPnl.getPanel().getForm().setValues(config);
		 				}
		 				
	 			
	 				}
	 			}
	 		}
     	});
     	
     	paneltoAdd.frame = true;
     	paneltoAdd.title = config.title; 
     	paneltoAdd.closable = true;
     	paneltoAdd.height = 420;
     	
     	
		var addEdirUserTab= layout.getCenterRegionTabPanel().add(paneltoAdd);
	layout.getCenterRegionTabPanel().setActiveTab(addEdirUserTab);
	layout.getCenterRegionTabPanel().doLayout();
  	
//     	window.show();
     Ext.ux.event.Broadcast.subscribe('closeUserWindow', function(){
//		layout.getCenterRegionTabPanel().remove( addEdirUserTab , true );
	 	Ext.ux.event.Broadcast.publish('setManageUser');
	 }, this , null , mainThis.getPanel());	
}
 
administration.userManagement.searchUser.ManageUser = Ext.extend(Object, {
	constructor: function(){
		Ext.QuickTips.init();
		var joiningDate;
		var mainThis = this;
		
		this.UserRecord = Ext.data.Record.create([
		    {name: 'UserId', type:'string',mapping:'id'},     
		    {name: 'UserName', type:'string',mapping:'name'},
		    {name:'effectiveFrom', type:'date',mapping:'effectiveFrom'},
		    {name:'effectiveTo', type:'date',mapping:'effectiveTo'},
		    {name:'country', type:'string',mapping:'country.description'},
		    {name:'state', type:'string',mapping:'state.description'},
		    {name:'email', type:'string',mapping:'email'},
		    {name:'contactNumber', type:'string',mapping:'contactNumber'},
		     {name:'role', type:'string',mapping:'role.description'},
		     {name : 'zipcode'}
		]);
		
		this.userStore = new Ext.data.Store({
			 	proxy: new Ext.data.DWRProxy(UserManagementController.getUsers, true),
			    reader: new Ext.data.ListRangeReader(
			    	{totalProperty:'totalSize'}, this.UserRecord),
			    remoteSort: true
		});
		
		this.addBtn = new Ext.Toolbar.Button ({
			 cls:'x-btn-text-icon',
	         text:userMsg.add,
	         tooltip:userMsg.add,
	         icon:'images/add.png',
	         scope:this,
	         handler: function(){
	         	this.addButtonHandler();
	         }
		});
		this.editBtn = new Ext.Toolbar.Button({
			 cls:'x-btn-text-icon',
             text:userMsg.edit,
             tooltip:userMsg.edit,
	         icon:'images/pencil.png',
	         scope:this,
	         disabled: true,
	         handler: function(){
	         	this.editButtonHandler();
	         }
		});
		
		this.viewBtn = new Ext.Toolbar.Button({
			 cls:'x-btn-text-icon',
	         icon:'images/zoom.png',
	         text: userMsg.view,
	         tooltip:userMsg.view,
	         scope:this,
	         disabled: true,
	         handler: function(){
	         	this.viewBtnHandler();
	         }
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			cls: 'x-btn-text-icon',
			text: userMsg.del,
			tooltip:userMsg.del,
			icon:'images/delete.png',
			scope:this,
			disabled: true,
			handler: function(){
				this.deleteButtonHandler();
			}
		});
		
		this.gridToolbarButtons = new Ext.Toolbar({
			items:[
				this.addBtn,
				this.editBtn,
//				this.viewBtn,
				this.deleteBtn
			]
		});
		
		this.pageBar = new Ext.WTCPagingToolbar({
            store: this.userStore,
            displayMsg: 'Displaying records {0} - {1} of {2}',
	        emptyMsg: "No users to display"
		});
		this.gridChk =  new Ext.grid.CheckboxSelectionModel({
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

		
		this.gridPanel = new Ext.grid.GridPanel({
			autoScroll:true,
			stripeRows:true,
			height:300,
			ds: this.userStore,
			sm:this.gridChk,
			viewConfig:{ forceFit: true },
			cm: new Ext.grid.ColumnModel([
				this.gridChk,
				{header:'User ID', width:100, sortable:true, dataIndex:'UserId'},
				{header:'User name',width:125, sortable: true, dataIndex:'UserName'},
				{header:'Effective from', width:75, sortable: true, dataIndex:'effectiveFrom',renderer : Ext.util.Format.dateRenderer('d/m/Y')},
				{header: 'Effective to', width:75, sortable: true, dataIndex:'effectiveTo',renderer : Ext.util.Format.dateRenderer('d/m/Y')},
				{header: 'Country', width: 75, sortable: true, dataIndex:'country'},
				{header: 'State', width: 100, sortable: true, dataIndex:'state'},
				{header: 'Contact number', width:100, sortable: true, dataIndex: 'contactNumber'},
				{header: 'Email address', width:125, sortable: true, dataIndex: 'email'},
				{header: 'Role', width:75, sortable: true, dataIndex: 'role'}
			]),
			bbar:this.pageBar,
			listeners:{
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
					mainThis.setGridButtonsState( thisGrid.getSelectionModel() );
				},
				celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
					selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
					mainThis.setGridButtonsState( thisGrid.getSelectionModel() );
					}
				},

			tbar:this.gridToolbarButtons
//			viewConfig:{
//				forceFit:true
//			},
		});
		
		this.searchBtn = new Ext.Button({
	        text: pharmacyMsg.btnSearch,
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	scope:true,
	    	handler: function() {
	    		this.searchHandler();
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
		
		this.stateCombo = new Ext.form.ComboBox({
			anchor:'90%',
			fieldLabel:userMsg.stateprovince,
			name:'state',
			mode:'local',
			store:UserManagerloadStateCbx(),
			displayField:'description',
			disabled : true,
			valueField:'code',
			triggerAction:'all',
			forceSelection:true
			
		}); 
		
		this.mainPanel = new Ext.form.FormPanel({
			layout:'column',
			title:userMsg.manageuser,
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
								fieldLabel:userMsg.userId,
								name:'userName'
							}
						]
						},
						{
						layout:'form',
						items:[
							{
								xtype:'textfield',
								anchor:'90%',
								fieldLabel:userMsg.name,
								name:'name'
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
										if(joiningDate != 'undefined' && joiningDate != ''){
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
						},
						{
						layout:'form',
						items:[
							{
								xtype:'combo',
								anchor:'90%',
								fieldLabel:userMsg.country,
								name:'country',
								mode:'local',
								store:UserManagerloadCountryCbx(),
								displayField:'description',
								valueField:'code',
								triggerAction:'all',
								scope : this,
								forceSelection: true,
								listeners:{
									select: function(combo, record,index) {
										values ={state:''};
										var test = combo;
										combo.ownerCt.ownerCt.ownerCt.getForm().setValues(values);
										mainThis.stateCombo.enable();
										userManagerStateStore.load({params:{start:0, limit:8}, arg:[record.data.code]});
									}
								}
							}
						]
						},
						{
						layout:'form',
						items:[
								this.stateCombo
						]
						},
						{
						layout:'form',
						items:[
							{
								xtype:'combo',
								anchor:'90%',
								fieldLabel:userMsg.role,
								name:'role',
								mode:'local',
								store:UserManagerloadRoleCbx(),
								displayField:'description',
								valueField:'code',
								triggerAction:'all',
								forceSelection:true
							}
						]
						},
						{
							layout:'form',
							columnWidth:.66,
							items:this.buttonPanel
						}
						 
					]
				},
				this.gridPanel
				]
		});
		
		Ext.ux.event.Broadcast.subscribe('reloadSearchUsersGrid', function(){
			mainThis.reloadGrid();
		}, this, null, mainThis.getPanel());
		
		this.userStore.on('load',function(){
			this.deleteBtn.disable();
			this.editBtn.disable();
			this.viewBtn.disable();
		},this);
		
		this.gridPanel.on('render', function(){
			mainThis.searchHandler();
		});
		
		 Ext.ux.event.Broadcast.subscribe('setManageUser', function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.mainPanel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('reloadSearchUsersGrid');

		},this, null ,mainThis.getPanel() );
		 
		this.mainPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
 
		
	},
	getPanel: function(){
		return this.mainPanel;
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
	searchHandler: function(){
		var valueMap = this.mainPanel.getForm().getValues();
		this.gridPanel.getStore().load({params:{start:0, limit:10}, arg:[ valueMap ]});
	},
	addButtonHandler: function(){
     	var config = {
     		gridObj:this.gridPanel,
     		manageUserMainPanel:this.mainPanel,
     		mode: userMsg.addMode,
 			isPopup: true,
 			title:userMsg.adduser
     	};
     	showAddEditUserWindow( config , this);
	},
	editButtonHandler: function(){
		var mainThis =this;
     	var selectedRows =this.gridPanel.getSelectionModel().getSelections();
     	var userWindow;
     	if(selectedRows.length == 1){
     		userSummary ={
     			id:selectedRows[0].data.UserId
     		};
         		UserManagementController.getUserDetails(userSummary, function(user){
         			if(user!=null) {
         			var houseNo;
         			var street;
         			if(!Ext.isEmpty(user.address)){
         				var values = user.address.split(',');
         				houseNo = values[0];
         				street = values[1];  
         			}
         			var config = 
	         				{
	         					mode:'edit',
			         			userName: user.userId,
			         			firstName: user.firstName,
			         			lastName: user.lastName,
			         			effectiveFrom: user.startDtm,
			         			effectiveTo: user.endDtm,
			         			contactNumber: user.contactNumber,
			         			zipCode: user.zipcode,
			         			country: user.country.code,
			         			state: user.state.code,
			         			city: user.city,
			         			houseName : houseNo,
			         			street : street,
			         			email: user.emailAddress,
			         			role: user.role.id,
			         			confirmPassword:user.password,
			         			password:user.password,
			         			isPopup: true,
			         			title: 'Edit user'
			         		};	
		         		showAddEditUserWindow( config , mainThis);
         			}
         		});
     	}else{
     		Ext.Msg.show({
     			msg:'please select single entry and retry!',
     			 buttons: Ext.Msg.OK,
     			 icon: Ext.MessageBox.ERROR
     		});
     	}
	},
	viewBtnHandler:function(){
	 	var selectedRows =this.gridPanel.getSelectionModel().getSelections();
	 	var userWindow;
	 	if(selectedRows.length == 1){
	 		userSummary ={
	 			id:selectedRows[0].data.UserId
	 		};
	     		UserManagementController.getUserDetails(userSummary, function(user){
	     			if(user!=null) {
	     			var config = 
	         				{
	         					mode:'edit',
	         					flag:true,
			         			userName: user.userId,
			         			firstName: user.firstName,
			         			lastName: user.lastName,
			         			effectiveFrom: user.startDtm,
			         			effectiveTo: user.endDtm,
			         			contactNumber: user.contactNumber,
			         			zipCode: user.zipCode,
			         			country: user.country.code,
			         			state: user.state.code,
			         			city: user.city,
			         			email: user.emailAddress,
			         			role: user.role.id,
			         			confirmPassword:user.password,
			         			password:user.password,
			         			isPopup: true,
			         			title: 'View user'
			         		};	
		         		showAddEditUserWindow( config );
	     			}
	     		});
	 	}else{
	 		Ext.Msg.show({
	 			msg:'please select single entry and retry!',
	 			 buttons: Ext.Msg.OK,
	 			 icon: Ext.MessageBox.ERROR
	 		});
	 	}
	},
	deleteButtonHandler: function(){
	 	var gridPnl = this.gridPanel;
	 	var selectedRows =  gridPnl.getSelectionModel().getSelections();
	 	Ext.Msg.show({
			msg: userMsg.removeusersmsg,
			 buttons: Ext.Msg.YESNO,
			 icon: Ext.MessageBox.QUESTION,
			 fn:function( btnValue ){
			 	if( btnValue == configs.yes ){
			 		var userList = new Array();
				 	if(selectedRows.length >0){
				 		for(var i=0;i<selectedRows.length;i++){
				 			var rowData = selectedRows[i].data;
					 		var userSummary ={
					 			id:rowData.UserId
					 		}
					 		userList.push(userSummary);
				 		}
				 		UserManagementController.removeUsers(userList, function(){
				 			Ext.ux.event.Broadcast.publish('reloadSearchUsersGrid');
						});
					}
			 	}
			 }
		});
	},
	resetSearch: function(){
		this.mainPanel.getForm().reset();
		this.stateCombo.disable(); 
		this.searchHandler();
	},
	reloadGrid: function(){
		if( this.gridPanel.getStore().data.length > 0){
			this.gridPanel.getStore().reload();
		}
		this.editBtn.disable();
		this.deleteBtn.disable();
	}
});