Ext.namespace("IPD.manageDoctorOrderTemplate");

function showAddEditDoctorOrderTemplateWindow(config) {
	var doctorOrderTemplatePanel = new IPD.addOrderTemplate.DoctorOrderTemplate(config);
	var panelToAdd = doctorOrderTemplatePanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
		doctorOrderTemplatePanel.loadData(config);
	}
	
	doctorOrderTemplatePanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closeDoctorOrderTemplateWindow',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('getSearchDoctorOrderTemplate');
	},this,null,config.mainThis.getPanel());
}

IPD.manageDoctorOrderTemplate.SearchDoctorOrderTemplate = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
		this.doctorOrderTemplateRecord = Ext.data.Record.create([
				{ name: 'seqNbr',mapping: 'seqNbr'},
				{ name: 'templateId', mapping:'templateId' },
				{ name: 'templateDesc', mapping:'templateDesc' },
		        { name: 'doctorOrderType',  mapping:'doctorOrderType',convert:getDescription},
		        { name: 'doctorOrderTypeCd', mapping: 'doctorOrderType',convert:getCode},
		        { name: 'doctorId', mapping: 'doctorId',convert:numberToString},
		        { name: 'doctorName', mapping:'doctorName'},
		        { name: 'activeFlag', mapping:'activeFlag'},
		        { name: 'doctorOrderDetailsList',mapping:'orderTemplateDetailsList'}
	    ]);
	    
	    this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'doctorOrderNbr', totalProperty:'totalSize'}, this.doctorOrderTemplateRecord),
        	proxy: new Ext.data.DWRProxy(OrderManager.findDoctorOrderTemplates, true),
        	remoteSort: true
		});

	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No doctor order templates to display"
    });
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.deleteBtn.enable();
					if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
					}else{
						mainThis.editBtn.enable();
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					if( selectionModel.getSelections().length == 1){
						mainThis.editBtn.enable();
					}else if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.deleteBtn.enable();
					}else{
						mainThis.deleteBtn.disable();
						mainThis.editBtn.disable();
					}
				}
			}
		});
		
		this.addBtn = new Ext.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditDoctorOrderTemplateWindow({
						 width: '100%'
					    ,height:'80%'
						,title: 'Add doctor order template'
						,mainThis : mainThis});
			}
		});
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			disabled: true,
			scope:this,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditDoctorOrderTemplateWindow({
						width: '100%',
					    height:'80%',
						title: 'Edit doctor order template',
						mode:'edit',
						values:selectedRow,
						mainThis : mainThis
					});
				}
			}
		});
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled: true,
			handler:function(){
					Ext.Msg.show({
							title : 'Delete order template',
							msg : 'Are you sure you want to delete the selected order template(s)?',
							buttons : Ext.Msg.YESNOCANCEL,
							fn : function(buttonId, text, opt) {
								if (buttonId == "yes") {
									var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
									var templateList = new Array();
									for(var i = 0; i < selectedRow.length; i++){
										templateList.push(selectedRow[i].data.templateId);
									}
									
									OrderManager.removeOrderTemplateList(templateList,function(isDeleted){
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
		this.searchBtn = new Ext.Button({
			text: 'Search',
	        xtype: 'button',
	    	iconCls : 'search_btn',
	    	scope:this,
	    	handler: function() {
		    	if(this.searchDoctorOrderTemplatePanel.getForm().isValid()){
		    		var searchCriteria = this.searchDoctorOrderTemplatePanel.getForm().getValues();
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
					this.searchDoctorOrderTemplatePanel.getForm().reset();
					var searchCriteria = this.searchDoctorOrderTemplatePanel.getForm().getValues();
		    		this.search(searchCriteria);
				}
			}]
		});				  

		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 305,
				width : '100%',
				autoScroll : true,
				border : false,
				viewConfig: {forceFit:true},
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
							var selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
							if( thisGrid.getSelectionModel().getSelections().length == 1 ){
								mainThis.editBtn.enable();
							}else{
								mainThis.editBtn.disable();
							}
					},
					celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
					},
					render : function(comp){
						if(comp.getStore().data.items.length == 0){
							comp.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null , null]});
						}
					},
					scope : this
				},
				tbar : [this.addBtn, {
							xtype : 'tbseparator'
						},this.editBtn , {
							xtype : 'tbseparator'
						},this.deleteBtn],

				columns : [this.gridChk, {
							header : 'S.No.',
							dataIndex : 'seqNbr',
							width : 40,
							sortable: true
						}, {
							header : 'Name',
							dataIndex : 'templateId',
							width : 200,
							sortable: true
						}, {
							header : 'Description',
							dataIndex : 'templateDesc',
							width : 300,
							sortable: true
						}, {
							header : 'Order type',
							dataIndex : 'doctorOrderType',
							width : 250,
							sortable: true
						}, {
							header : 'Doctor',
							dataIndex : 'doctorName',
							width : 300,
							sortable: true
						}
					]
			});
		this.searchDoctorOrderTemplatePanel = new Ext.form.FormPanel({
			labelAlign : 'left',
			layout : 'column',
//			style: 'padding-top:10px',
			anchor: '95%',
			defaults:{columnWidth:.33,labelWidth:80},
			items: [
				{
		    		layout:'form',
		    		items:[{
		    		xtype: 'optcombo',
		    		fieldLabel: 'Template',
			        hiddenName:'doctorOrderTemplate',
			        store: loadDoctorOrderTemplate(),
			        mode:'local',
			        triggerAction: 'all',
					displayField:'description',
					valueField:'code',
			        anchor : '95%',
			        forceSelection : true,
			        resizable:false}]
			    },
			    {
					layout:'form',
					items:[
						{
							xtype:'optcombo',
							fieldLabel:	msg.ordertype,
							name:'orderType',
							mode:'local',
							store: loadDoctorOrderType(),
							triggerAction: 'all',
							valueField:'code',
							displayField:'description',
					        anchor : '95%',
					        forceSelection:true,
					        resizable:false
		   				}
					]
			    },
			    {
				 	layout:'form',
				 	items:[
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
					        resizable:false
					    }
				 	]
			    },
			    {
					layout:'form',
					items:[
						{
							xtype:'optcombo',
							fieldLabel:	'Service',
							name:'serviceCode',
							mode:'local',
							store: loadServicesCbx(),
							triggerAction: 'all',
							displayField:'description',
							valueField:'code',
					        anchor : '95%',
					        resizable:false,
					        forceSelection : true
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'optcombo',
							fieldLabel:	'Department',
							name:'responsibleDepartmentCode',
							mode:'local',
							store: loadDepartmentsCbx(),
							triggerAction: 'all',
							displayField:'description',
							valueField:'code',
					        anchor : '95%',
					        resizable:false,
					        forceSelection : true
		   				}
					]
			    },
			    {
			    	layout:'form',
					border:false,
					items:buttonPanel
			    },
			    {
			    	layout:'form',
			    	columnWidth:1,
					border:false,
					items:this.infoGrid
			    }
			]
		});
		
		Ext.ux.event.Broadcast.subscribe('loadTemplateGrid',function(){
			if(this.infoGrid.getStore().data.items == 0){
					this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null]});
				}else{
					this.infoGrid.getStore().reload();
				}
				this.deleteBtn.disable(); 
				this.editBtn.disable();
		}, this, null, this.getPanel() );
		
		Ext.ux.event.Broadcast.subscribe('getSearchDoctorOrderTemplate',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.searchDoctorOrderTemplatePanel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadTemplateGrid');
		},this,null,mainThis.getPanel());
	
		this.searchDoctorOrderTemplatePanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadTemplateGrid');
		return this.searchDoctorOrderTemplatePanel;
	},
	search : function(searchCriteria){
		this.dataStore.removeAll();
		this.dataStore.load({params:{start:0, limit:10}, arg:[	searchCriteria['doctorOrderTemplate'],
																searchCriteria['orderType'],
																searchCriteria['doctorId'],
																searchCriteria['serviceCode'],
																searchCriteria['responsibleDepartmentCode']
															   ]});
	},
	getFocus:function(){
		//this.templateNameCbx.focus();
	}
});