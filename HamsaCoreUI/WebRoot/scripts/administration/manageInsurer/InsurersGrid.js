Ext.namespace("administration.manageInsurer");

function showAddEditInsurerWindow(config) {

	var insurerPanel = new administration.addInsurer.Insurer(config);
	
	var panelToAdd = insurerPanel.getPanel();
	
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
		insurerPanel.loadData(config);
	}
	
	insurerPanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closeInsurerPanel',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		
		Ext.ux.event.Broadcast.publish('getSearchInsuranceCompany');
	},this , null ,config.mainThis.getPanel() );
}


administration.manageInsurer.InsurersGrid = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this;
		
		this.insurerRecord = Ext.data.Record.create([
			{ name: 'insurerName', mapping:'insurerName' },
			{ name: 'insurerDesc', mapping:'insurerDesc' },
			{ name: 'contactDetailsBM'},
	        { name: 'createdDate',  mapping:'createdDate'},
	        { name: 'createdBy', mapping:'createdBy'},
	        { name: 'modifiedBy', mapping:'modifiedBy'},
	        { name: 'modifiedDate', mapping: 'modifiedDate'},
	        { name: 'sponsorInsurerAssociationBMList'},
	        { name: 'contactPerson',mapping:'contactDetailsBM',convert:getContactPerson},
			{ name: 'contactNumber',mapping:'contactDetailsBM',convert:getContactNumber}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, this.insurerRecord),
        	proxy: new Ext.data.DWRProxy(InsuranceManager.findInsurer, true),
        	remoteSort:true
		});

	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No insurers to display"
    });
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.deleteBtn.enable();
					if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.viewBtn.disable();
					}else{
						mainThis.editBtn.enable();
						mainThis.viewBtn.enable();
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					if( selectionModel.getSelections().length == 1){
						mainThis.editBtn.enable();
						mainThis.viewBtn.enable();
					}else if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.viewBtn.disable();
						mainThis.deleteBtn.enable();
					}else{
						mainThis.deleteBtn.disable();
						mainThis.editBtn.disable();
						mainThis.viewBtn.disable();
					}
				}
			}
		});
		
		var columns = [this.gridChk, {
							header : 'Name',
							dataIndex : 'insurerName',
							sortable: true
						}, {
							header : 'Description',
							dataIndex : 'insurerDesc',
							sortable: true
						}, {
							header : 'Contact person',
							dataIndex : 'contactPerson',
							sortable: true
						}, {
							header : 'Contact number',
							dataIndex : 'contactNumber',
							sortable: true
						}];
	   
	    this.addBtn = new Ext.Button({
	    	iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditInsurerWindow({
					 width: '95%',
					 height:'95%',
					 title: 'Add insurer',
					 isPopUp:true,
					 mainThis : mainThis
					 });
			}
	    });
	    
	    this.editBtn = new Ext.Button({
	    	iconCls : 'edit_btn',
			text : 'Edit',
			disabled:true,
			scope:this,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditInsurerWindow({
					 width: '95%',
					 height:'95%',
					 mode:'edit',
					 title: 'Edit insurer',
					 selectedRow:selectedRow,
					 isPopUp:true,
					 mainThis : mainThis});
				}
			}
	    });
	    
	    this.deleteBtn = new Ext.Button({
	    	iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope:this,
			handler:function(){
				
				Ext.Msg.show({
					msg: msg.deleteMessage,
					buttons: Ext.Msg.YESNO,
					icon: Ext.MessageBox.QUESTION,
					fn: function(btnValue){
						if(btnValue == configs.yes){
							var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
							var insurerList = new Array();
							for(var i = 0; i < selectedRow.length; i++){
								insurerList.push(selectedRow[i].data.insurerName);
							}
							
							InsuranceManager.removeInsurers(insurerList,function(isDeleted){
								if(!Ext.isEmpty(isDeleted) && isDeleted){
									Ext.ux.event.Broadcast.publish('loadInsurerGrid');
								}
							});
							
							insurerStore.reload();
						}
					}
				});
			}
	    });
	    
	    this.viewBtn = new Ext.Button({
	    	iconCls : 'view-icon',
			text : 'View sponsors',
			scope:this,
			disabled:true,
			handler : function(){
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditInsurerWindow({
						 width: '95%',
						 height:'95%',
						 mode:'edit',
						 title: 'Associated sponsors for insurer '+selectedRow.insurerName,
						 selectedRow:selectedRow,
						 isViewBtnClicked : true,
						 mainThis : mainThis
					 });
				}
			}
	    });
	    
	    var tbar = [
	    	this.addBtn, 
	    	{xtype : 'tbseparator'}, 
	    	this.editBtn, 
	    	{xtype : 'tbseparator'}, 
	    	this.deleteBtn,
    		{xtype : 'tbseparator'}, 
    		this.viewBtn];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }

		this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			height : 305,
			width : '100%',
			autoScroll : true,
			viewConfig:{forceFit : true},
			border : false,
			store : this.dataStore,
			bbar : this.pagingBar,
			tbar : tbar,
			columns : columns,
			sm:this.gridChk,
			listeners:{
			 	cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedRecord = thisGrid.getStore().getAt(rowIndex).data; 
					if( thisGrid.getSelectionModel().getSelections().length == 1 ){
						this.editBtn.enable();
					}else{
						this.editBtn.disable();
					}
				},
				celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
				},
				render:function( comp ){
					if(comp.getStore().data.items.length == 0){
						comp.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null]});
					}
				},
				scope:this
			}
		});
		
		Ext.ux.event.Broadcast.subscribe('loadInsurerGrid',function(){
			if( !Ext.isEmpty(this.infoGrid.getStore())){
		if(this.infoGrid.getStore().data.items.length == 0){
				this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null]});
		}else{
				this.infoGrid.getStore().removeAll();
				this.infoGrid.getStore().reload();
			}
			}
			this.editBtn.disable();
			this.deleteBtn.disable();
			this.viewBtn.disable();
		},this , null , mainThis.getPanel());
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(searchCriterea){
		this.dataStore.load({params:{start:0, limit:10}, arg:[
				searchCriterea['insurerName'],
				null,
				null,
				searchCriterea['sponsorName']
		]});
	}
});