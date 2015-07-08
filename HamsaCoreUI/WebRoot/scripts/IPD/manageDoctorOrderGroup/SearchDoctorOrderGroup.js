Ext.namespace("IPD.manageDoctorOrderGroup");

function showAddEditDoctorOrderGroupWindow(config) {
	var doctorOrderGroupPanel = new IPD.doctorOrderGroup.DoctorOrderGroup(config);
	var panelToAdd = doctorOrderGroupPanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;

	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
		doctorOrderGroupPanel.loadData(config);
	}
	
	doctorOrderGroupPanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closeDoctorOrderGroupWindow',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('getDoctorOrderGroupPanel');
	},this,null,config.mainThis.getPanel());

}

IPD.manageDoctorOrderGroup.SearchDoctorOrderGroup = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel: 'Order date(from)',
			name: 'orderDateFrom',
	        anchor : '95%',
	        listeners:{
	        	change: function( date, newValue,oldValue ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel: 'Order date(to)',
			name: 'orderDateTo',
	        anchor : '95%',
	        listeners:{
		   		change: function( date, newValue,oldValue ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
		this.doctorOrderGroupRecord = Ext.data.Record.create([
				{ name: 'seqNbr',mapping: 'sequenceNbr'},
				{ name: 'orderGroupName', mapping:'orderGroupName' },
				{ name: 'orderGroupDesc', mapping:'orderGroupDesc' },
		        { name: 'groupForDoctorId',  mapping:'groupForDoctorId',convert:numberToString},
		        { name: 'creationDate', mapping: 'creationDate'},
		        { name: 'createdBy', mapping: 'createdBy'},
		        { name: 'groupForDoctorName', mapping:'groupForDoctorName'},
		        { name: 'orderGroupTemplateAssociationList'}
	    ]);
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'doctorOrderNbr', totalProperty:'totalSize'}, this.doctorOrderGroupRecord),
        	proxy: new Ext.data.DWRProxy(OrderManager.findDoctorOrderGroups, true),
        	remoteSort: true
		});
		
	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No doctor order groups to display"
    });
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.deleteBtn.enable();
					if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.viewTemplateBtn.disable();
					}else{
						mainThis.editBtn.enable();
						mainThis.viewTemplateBtn.enable();
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					if( selectionModel.getSelections().length == 1){
						mainThis.editBtn.enable();
						mainThis.viewTemplateBtn.enable();
					}else if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.viewTemplateBtn.disable();
						mainThis.deleteBtn.enable();
					}else{
						mainThis.deleteBtn.disable();
						mainThis.editBtn.disable();
						mainThis.viewTemplateBtn.disable();
					}
				}
			}
		});
		this.addBtn = new Ext.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditDoctorOrderGroupWindow({
						 width: '100%'
						,height: '80%'
						,title: 'Add doctor order group',
						mainThis : mainThis});
			}
		});
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			disabled:true,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditDoctorOrderGroupWindow({
						 width: '100%',
						 height:'80%',
						 title: 'Edit doctor order group',
						 mode:'edit',
						 rowValues:selectedRow,
						 mainThis : mainThis
					});
				}
			}
		});
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled:true,
			handler:function(){
					Ext.Msg.show({
							title : 'Delete order group',
							msg : 'Are you sure you want to delete the selected order group(s)?',
							buttons : Ext.Msg.YESNOCANCEL,
							fn : function(buttonId, text, opt) {
								if (buttonId == "yes") {
									var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
									var groupList = new Array();
									for(var i = 0; i < selectedRow.length; i++){
										groupList.push(selectedRow[i].data.orderGroupName);
									}
									
									OrderManager.removeOrderGroupList(groupList,function(isDeleted){
										if(!Ext.isEmpty(isDeleted) && isDeleted){
									    	mainThis.dataStore.reload();
										}
									});
								}
							},
							animEl : 'elId',
							icon : Ext.MessageBox.QUESTION
					});
			}
		});
		this.viewTemplateBtn = new Ext.Button({
			iconCls : 'view-icon',
			text : 'View template',
			scope:this,
			disabled:true,
			handler:function(){
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditDoctorOrderGroupWindow({
						 width: '80%',
						 height:'80%',
						 title: 'View doctor order templates',
						 mode:'edit',
						 isViewTemplates:true,
						 rowValues:selectedRow,
						 mainThis : mainThis
					});
				}
			}
		});
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 305,
				width : '100%',
				forceFit:true,
				autoScroll : true,
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				viewConfig:{forceFit:true},
				sm:this.gridChk,
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
							var selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
							if( thisGrid.getSelectionModel().getSelections().length == 1 ){
								mainThis.editBtn.enable();
								mainThis.viewTemplateBtn.enable();
							}else{
								mainThis.editBtn.disable();
								mainThis.viewTemplateBtn.disable();
							}
						},
						celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
						},
						render:function( comp ){
							if(comp.getStore().data.items.length == 0){
							comp.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null , null]});
						}
					},
					scope : this 
				},
				tbar : [this.addBtn, {
							xtype : 'tbseparator'
						},this.editBtn, {
							xtype : 'tbseparator'
						},this.deleteBtn,{
							xtype : 'tbseparator'
						},this.viewTemplateBtn],

				columns : [this.gridChk, /*{
							header : 'S.No.',
							dataIndex : 'seqNbr',
							width : 40,
							sortable: true
						},*/ {
							header : 'Group name',
							dataIndex : 'orderGroupName',
							width : 200,
							sortable: true
						}, {
							header : 'Group description',
							dataIndex : 'orderGroupDesc',
							width : 220,
							sortable: true
						},
						{
							header : 'Order for',
							dataIndex : 'groupForDoctorName',
							width : 250,
							sortable: true
						},
						{
							header : 'Creation date',
							dataIndex : 'creationDate',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 200,
							sortable: true
						}]
		});
			
		this.searchBtn = new Ext.Button({
	    	iconCls : 'search_btn',
	    	text:'Search',
	    	scope:this,
	    	handler: function() {
		    	if(this.searchDoctorOrderGroupPanel.getForm().isValid()){
		    		var searchCriteria = this.searchDoctorOrderGroupPanel.getForm().getValues();
		    		this.search(searchCriteria);
		    	} else {
		    		error("ENTER VALID DATA..!");
		    	}
	    	}
		});
		var buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
			buttons:[this.searchBtn,{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.searchDoctorOrderGroupPanel.getForm().reset();
					var searchCriteria = this.searchDoctorOrderGroupPanel.getForm().getValues();
		    		this.search(searchCriteria);
				}
			}]
		});				  
		this.groupNameCbx = new Ext.form.OptComboBox({
	        fieldLabel: msg.groupname,
	        hiddenName:'doctorOrderGroup',
			mode:'local',
			store: loadDoctorOrderGroup(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '95%',
	        forceSelection : true,
	        resizable:true
	    });
		this.searchDoctorOrderGroupPanel = new Ext.form.FormPanel({
			labelAlign : 'left',
			layout:'column',
//			style: 'padding-top:10px',
			defaults:{columnWidth:.33},
			items: [
				{
					layout:'form',
					labelWidth:110,
					items:this.groupNameCbx
				 },
				 {
				 	layout:'form',
					labelWidth:100,
				 	items:
				 	{
				        fieldLabel: msg.doctorname,
				        xtype: 'optcombo',
				        name:'doctorId',
						mode:'local',
						store: loadDoctorsCbx(),
						triggerAction: 'all',
						displayField:'description',
						valueField:'code',
				        anchor : '95%',
				        forceSelection : true,
				        resizable:true
				    }
			    },
		    	{
		    		layout:'form',
					labelWidth:70,
		    		items:
		    		{
				        fieldLabel: 'Template',
				        xtype: 'optcombo',
				        name:'doctorOrderTemplate',
				        store: loadDoctorOrderTemplate(),
				        mode:'local',
				        triggerAction: 'all',
						displayField:'description',
						valueField:'code',
				        anchor : '95%',
				        forceSelection : true,
				        resizable:true
				    }
			    },
		    	{	
		    		layout:'form',
					labelWidth:110,
		    		items:this.fromDate
	    		},
		    	{
		    		layout:'form',
					labelWidth:100,
		    		items:this.toDate
	    		},
		    	{
		    		layout:'form',
		    		items:buttonPanel
	    		},
		    	{
		    		layout:'form',
		    		columnWidth:1,
		    		items:this.infoGrid
	    		}
		    ]
		});
		
		Ext.ux.event.Broadcast.subscribe('loadGroupGrid',function(){
			if(this.infoGrid.getStore().data.items == 0){
					this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null]});
				}else{
					this.infoGrid.getStore().reload();
				}
				this.deleteBtn.disable(); 
				this.editBtn.disable();
				this.viewTemplateBtn.disable();
		},this, null, this.getPanel() );
		
		
		Ext.ux.event.Broadcast.subscribe('getDoctorOrderGroupPanel',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.searchDoctorOrderGroupPanel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadGroupGrid');
		},this,null,mainThis.getPanel());
		
		this.searchDoctorOrderGroupPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
	},
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadGroupGrid');
		return this.searchDoctorOrderGroupPanel;
	},
	search : function(searchCriteria){
		this.dataStore.removeAll();
		this.dataStore.load({params:{start:0, limit:10}, arg:[	searchCriteria['doctorOrderGroup'],
																searchCriteria['doctorOrderTemplate'],
																searchCriteria['doctorId'],
																getStringAsWtcDateFormat(searchCriteria['orderDateFrom']),
																getStringAsWtcDateFormat(searchCriteria['orderDateTo'])
															   ]});
	},
	getFocus:  function(){
		this.groupNameCbx.getFocus();
	}
});