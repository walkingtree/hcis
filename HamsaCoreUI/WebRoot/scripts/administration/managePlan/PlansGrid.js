Ext.namespace("administration.managePlan");

function showAddEditPlanWindow(config) {
	
	var insurerPlanPanel = new administration.insurerPlan.InsurancePlan(config);
	var panelToAdd = insurerPlanPanel.getPanel();
	panelToAdd.frame=true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
		insurerPlanPanel.loadData(config);
	}
	
	insurerPlanPanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closePlanPanel',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('getSearchInsurancePlan');
	},this,null,config.mainThis.getPanel());
}

administration.managePlan.PlansGrid = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
		this.planDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr', mapping:'seqNbr'},
			{ name: 'planCode', mapping:'planCode' },
			{ name: 'insurerName', mapping:'insurerName' },
	        { name: 'planName',  mapping:'planName'},
	        { name: 'validFromDt', mapping:'validFromDt'},
	        { name: 'validToDt', mapping:'validToDt'},
	        { name: 'totalCoverageAmt', mapping: 'totalCoverageAmt'},
	        { name: 'percentAbsInd', mapping: 'percentAbsInd'},
	        { name: 'lastModifiedDt', mapping: 'lastModifiedDt'},
	        { name: 'lastModifiedBy',mapping:'lastModifiedBy'},
	        { name: 'planHasServicesBMList'},
	        { name: 'planCoversDiseaseBMList'}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, 
												  this.planDetailRecord),
        	proxy: new Ext.data.DWRProxy(InsuranceManager.findInsurancePlans, true),
        	remoteSort:true
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayMsg : 'Displaying plans {0} - {1} of {2}',
			emptyMsg : "No plans to display"
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
		
		var columns = [this.gridChk, {
							header : 'Plan Code',
							dataIndex : 'planCode',
							sortable: true
						}, {
							header : 'Plan Name',
							dataIndex : 'planName',
							sortable: true
						}, {
							header : 'Insurer',
							dataIndex : 'insurerName',
							sortable: true
						}, {
							header : 'Valid (from)',
							dataIndex : 'validFromDt',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							sortable: true
						}, {
							header : 'Valid (upto)',
							dataIndex : 'validToDt',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							sortable: true
						}];

	    this.addBtn = new Ext.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditPlanWindow({
						 width: '95%',
						 height:'95%',
						 title: 'Add plan',
						 isPopUp:true,
						 mainThis : mainThis});
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
					showAddEditPlanWindow({
						 width : '95%',
						 height : '95%',
						 mode :'edit',
						 title : 'Edit plan',
						 selectedRow : selectedRow,
						 isPopUp:true,
						 mainThis : mainThis});
				}
			}
		});
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope : this,
			handler : function(){
				
				Ext.Msg.show({
					msg: msg.deleteMessage,
					buttons: Ext.Msg.YESNO,
					icon: Ext.MessageBox.QUESTION,
					fn: function(btnValue){
						if(btnValue == configs.yes){
							var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
							var planList = new Array();
							for(var i = 0; i < selectedRow.length; i++){
								planList.push(selectedRow[i].data.planCode);
							}
							
							InsuranceManager.removeInsurancePlans(planList,function(isDeleted){
								if(!Ext.isEmpty(isDeleted) && isDeleted){
									Ext.ux.event.Broadcast.publish('loadPlanGrid');
								}
							});
						}
					}
				});
			}
		});
	    
	    var tbar = [ 
	    	this.addBtn, 
    		{ xtype : 'tbseparator' }, 
    		this.editBtn, 
    		{xtype : 'tbseparator'}, 
    		this.deleteBtn
		];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }

		this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			height : 305,
			width : '100%',
			autoScroll : true,
			border : false,
			store : this.dataStore,
			bbar : this.pagingBar,
			tbar : tbar,
			columns : columns,
			sm:this.gridChk,
			viewConfig: {forceFit : true},
			listeners:{
				 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						if( thisGrid.getSelectionModel().getSelections().length == 1 ){
							mainThis.editBtn.enable();
						}else{
							mainThis.editBtn.disable();
						}
				},
				celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
				}
			}
		});
		
		this.infoGrid.on("render", function(thisCmp) {
			if (this.dataStore.getTotalCount() > 0) {
				this.dataStore.removeAll();
			}
			this.dataStore.load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null]});
		}, this);
		
		Ext.ux.event.Broadcast.subscribe('loadPlanGrid',function(){
			if(this.infoGrid.getStore().data.items.length == 0){
				this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null]});
			}else{
				this.infoGrid.getStore().removeAll();
				this.infoGrid.getStore().reload();
			}
			this.editBtn.disable();
			this.deleteBtn.disable();
		},this, null, this.getPanel());
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(searchCriterea) {
		this.dataStore.load({params:{start:0, limit:10}, 
			arg:[
				searchCriterea['planCode'],
				searchCriterea['planName'],
				searchCriterea['insurerName'],
				searchCriterea['sponsorName'],
				getStringAsWtcDateFormat(searchCriterea['validFromDt']),
				getStringAsWtcDateFormat(searchCriterea['validToDt'])
			]});
	}
});