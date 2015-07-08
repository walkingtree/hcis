Ext.namespace("administration.manageSponsor");

function showAddEditSponsorWindow(config) {
	var sponsorPanel = new administration.addSponsor.Sponsor(config);
	
	var panelToAdd = sponsorPanel.getPanel();

	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;

	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
		sponsorPanel.loadData(config);
	}
	
	sponsorPanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closeSponsorPanel',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('getSearchSponsor');
	},this, null , config.mainThis.getPanel());
}

function getContactPerson(contactBM){
	if(contactBM != null && !Ext.isEmpty(contactBM)){
		var contactName = "";
		if(!Ext.isEmpty(contactBM.firstName)){
			contactName = contactName+contactBM.firstName;
		}
		if(!Ext.isEmpty(contactBM.middleName)){
			contactName = contactName + " " + contactBM.middleName;
		}
		if(!Ext.isEmpty(contactBM.lastName)){
			contactName = contactName + " " + contactBM.lastName;
		}
		return contactName;
	}
}
function getContactNumber(contactBM){
	if(contactBM != null && !Ext.isEmpty(contactBM)){
		var contactNumber = "";
		if(!Ext.isEmpty(contactBM.mobileNumber)){
			contactNumber = contactBM.mobileNumber;
		}else if(!Ext.isEmpty(contactBM.phoneNumber)){
			contactNumber = contactBM.phoneNumber;
		}
		return contactNumber;
	}
}

administration.manageSponsor.SponsorsGrid = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this;
		this.sponsorDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr', mapping:'seqNbr' },
			{ name: 'sponsorsName', mapping:'sponsorsName'},
			{ name: 'sponsorTypeCd', mapping:'sponsorType',convert:getCode},
			{ name: 'sponsorTypeDesc', mapping:'sponsorType',convert:getDescription},
			{ name: 'creditClassCd', mapping:'creditClass',convert:getCode},
			{ name: 'creditClassDesc', mapping:'creditClass',convert:getDescription},
			{ name: 'sponsorDesc', mapping:'sponsorDesc'},
			{ name: 'accountNumber', mapping:'accountNumber'},
			{ name: 'contactDetailsBM'},   //not null
			{ name: 'modifiedBy', mapping:'modifiedBy'},
			{ name: 'sponsorInsurerAssociationBMList'},
			{ name: 'sponsorSlaBMList'},
			{ name: 'contactPerson',mapping:'contactDetailsBM',convert:getContactPerson},
			{ name: 'contactNumber',mapping:'contactDetailsBM',convert:getContactNumber}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, this.sponsorDetailRecord),
        	proxy: new Ext.data.DWRProxy(InsuranceManager.findSponsors, true),
        	remoteSort:true
		});

	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No sponsors to display"
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
							dataIndex : 'sponsorsName',
							sortable: true
						}, {
							header : 'Description',
							dataIndex : 'sponsorDesc',
							sortable: true
						}, {
							header : 'Type',
							dataIndex : 'sponsorTypeDesc',
							sortable: true
						}, {
							header : 'Credit class',
							dataIndex : 'creditClassDesc',
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
				showAddEditSponsorWindow({
					 width: '95%',
					 height:'80%',
					 title: 'Add sponsor',
					 isPopUp:true,
					 mainThis : mainThis});
			}
	    });
	    
	    this.editBtn = new Ext.Button({
	    	iconCls : 'edit_btn',
			text : 'Edit',
			disabled : true,
			scope:this,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditSponsorWindow({
						 width: '95%',
						 height:'80%',
						 mode:'edit',
						 title: 'Edit sponsor',
						 selectedRow:selectedRow,
						 isPopUp:true,
						 mainThis : mainThis});
				}
			}
	    });
	    
	    this.deleteBtn = new Ext.Button({
	    	iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled : true,
			handler:function(){
				Ext.Msg.show({
					msg: msg.deleteMessage,
					buttons: Ext.Msg.YESNO,
					icon: Ext.MessageBox.QUESTION,
					fn: function(btnValue){
						if(btnValue == configs.yes){
							var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
							var sponsorList = new Array();
							for(var i = 0; i < selectedRow.length; i++){
								sponsorList.push(selectedRow[i].data.sponsorsName);
							}
							
							InsuranceManager.removeSponsors(sponsorList,function(isDeleted){
								if(!Ext.isEmpty(isDeleted) && isDeleted){
									Ext.ux.event.Broadcast.publish('loadSponsorGrid');
								}
							});
							
							sponsorStore.reload();
						}
					}
				});
			}
	    });
	    
	     this.viewBtn = new Ext.Button({
	    	iconCls : 'view-icon',
			text : 'View insurers',
			scope:this,
			disabled:true,
			handler : function(){
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditSponsorWindow({
						 width: '95%',
						 height:'95%',
						 mode:'edit',
						 title: 'Associated insurers for sponsor '+selectedRow.sponsorsName,
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
				sm: this.gridChk,
				tbar : tbar,
				columns : columns,
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
								comp.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null,null,null]});
							}
						},
					scope:this
				}
			});
			
		Ext.ux.event.Broadcast.subscribe('loadSponsorGrid',function(){
			if(this.infoGrid.getStore().data.items.length == 0){
				this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null]});
			}else{
				this.infoGrid.getStore().removeAll();
				this.infoGrid.getStore().reload();
			}
			
			this.editBtn.disable();
			this.deleteBtn.disable();
			this.viewBtn.disable();
		},this, null ,this.getPanel());
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(searchCriterea) {
		this.dataStore.load({params:{start:0, limit:10}, arg:[
				searchCriterea['sponsorName'],
				searchCriterea['sponsorType'],
				null,
				searchCriterea['creditClassCode'],
				null,
				searchCriterea['insurer']
		]});
	}
});