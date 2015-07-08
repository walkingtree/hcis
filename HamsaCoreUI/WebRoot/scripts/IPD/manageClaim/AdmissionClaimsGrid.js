Ext.namespace("IPD.manageClaim");

function showAddEditClaimRequestWindow(config) {

	var claimPanel = new IPD.addClaim.Claim(config);
	var panelToAdd = claimPanel.getPanel();
	panelToAdd.frame = true;
	panelToAdd.title = config.title; 
	panelToAdd.closable = true;
	panelToAdd.height = 420;

	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	if(config.mode == 'edit'){
		claimPanel.loadData(config);
	}
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	Ext.ux.event.Broadcast.subscribe('closeClaimPanel',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		Ext.ux.event.Broadcast.publish('getSearchClaim');
		
	},this , null ,config.mainThis.getPanel());
	
}	

IPD.manageClaim.AdmissionClaimsGrid = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this

		this.claimDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr', mapping:'seqNbr'},
			{ name: 'requestSequenceNbr', mapping:'requestSequenceNbr'},//type:long
			{ name: 'admissionReqNbr', mapping:'admissionReqNbr'},//type:Integer//Not Null
			{ name: 'claimSubsequenceNbr', mapping:'claimSubsequenceNbr'},//Integer//Not Null
			{ name: 'sponsorName', mapping:'sponsorName'},//Not Null
			{ name: 'sponsorDesc', mapping:'sponsorDesc'},
			{ name: 'insurerName', mapping: 'insurerName'},
			{ name: 'insurerDesc', mapping: 'insurerDesc'},
			{ name: 'planCode', mapping:'planCode'},//Not Null
			{ name: 'planName', mapping:'planName'},
			{ name: 'policyNbr', mapping:'policyNbr'},
			{ name: 'claimStatusCd', mapping:'claimStatusCd', convert:getCode},
			{ name: 'claimStatusCdDesc', mapping:'claimStatusCd', convert:getDescription},
			{ name: 'policyEffectiveFromDt', mapping:'policyEffectiveFromDt'},//Not Null
			{ name: 'policyEffectiveToDt', mapping:'policyEffectiveToDt'},
			{ name: 'policyHolderName', mapping:'policyHolderName'},
			{ name: 'preferenceSequenceNbr', mapping:'preferenceSequenceNbr'},//Integer
			{ name: 'createdBy', mapping:'createdBy'},
			{ name: 'createDtm', mapping:'createDtm'},
			{ name: 'requestDtm', mapping:'requestDtm'},
			{ name: 'requestedAmount', mapping:'requestedAmount'},//double
			{ name: 'patientAmount', mapping:'patientAmount'},//double
			{ name: 'approvalThrough', mapping:'approvalThrough'},
			{ name: 'approvedAmount', mapping:'approvedAmount'},//double
			{ name: 'approvalDate', mapping:'approvalDate'},
			{ name: 'finalClaimedAmount', mapping:'finalClaimedAmount'},//double
			{ name: 'billNbr', mapping:'billNbr'},//long
			{ name: 'lastModifiedDtm', mapping:'lastModifiedDtm'},
			{ name: 'modifiedBy', mapping:'modifiedBy'},
			{ name: 'claimDocumentBMList'},
			{ name: 'insurerBM'},
			{ name: 'patientId'},
			{ name: 'patientName'},
			{ name: 'estimationGivenBy'},
			{ name: 'estimatedCost'},//double
			{ name: 'insuranceAmount'},
			{ name: 'hideSubmitted', 
				type: 'boolean', 
				mapping:'claimStatusCd', 
				convert: function(val, rec){
	    			if (val.code === configs.claimCreated) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
	    	{ 	name: 'hideApproved', 
	    		type: 'boolean', 
	    		mapping:'claimStatusCd', 
	    		convert: function(val, rec){
	    			if (val.code === configs.claimSubmitted || val.code === configs.claimResubmitted) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
    		{	name: 'hidePartapproved', 
	    		type: 'boolean', 
	    		mapping:'claimStatusCd', 
	    		convert: function(val, rec){
	    			if (val.code === configs.claimSubmitted || val.code === configs.claimResubmitted) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
    		{	name: 'hideRejected', 
	    		type: 'boolean', 
	    		mapping:'claimStatusCd', 
	    		convert: function(val, rec){
	    			if (val.code === configs.claimSubmitted || val.code === configs.claimResubmitted) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
    		{	name: 'hideMoreinfo', 
	    		type: 'boolean', 
	    		mapping:'claimStatusCd', 
	    		convert: function(val, rec){
	    			if (val.code === configs.claimSubmitted || val.code === configs.claimResubmitted) 
	    				{return false;}
	    			else {return true;}
	    		}
    		},
    		{	name: 'hideResubmitted', 
	    		type: 'boolean', 
	    		mapping:'claimStatusCd', 
	    		convert: function(val, rec){
	    			if (val.code === configs.claimMoreinfo) {return false;}
	    			else {return true;}
	    		}
    		}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, this.claimDetailRecord),
        	proxy: new Ext.data.DWRProxy(InsuranceManager.findClaimRequests, true),
        	remoteSort:true
		});
		
	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No claims to display"
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
		
		this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,actions:[
				{
					iconCls:'accept-icon',
					tooltip:'Submit',
					hideIndex:'hideSubmitted'
				},
				{
					iconCls:'approve-icon',
					tooltip:'Approve',
					hideIndex:'hideApproved'
				},
				{
					iconCls:'partapprove-icon',
					tooltip:'Partially approve',
					hideIndex:'hidePartapproved'
				},
				{
					iconCls:'cross_icon',
					tooltip:'Reject',
					hideIndex:'hideRejected'
				},
				{
					iconCls:'info-icon',
					tooltip:'More information required',
					hideIndex:'hideMoreinfo'
				},
				{
					iconCls:'resubmit-icon',
					tooltip:'Re-submit',
					hideIndex:'hideResubmitted'
				}
			]
		});

		// dummy action event handler - just outputs some arguments to console
		this.action.on({
			action:function(grid, record, action, row, col) {
				var config = {
					requestSequenceNbr : record.data.requestSequenceNbr,
					approvedAmount : record.data.approvedAmount,
					modifiedBy : authorisedUser.userName
				}
				
				if (action === 'accept-icon') {
					 config.claimStatusCd = configs.claimSubmitted;
					 config.claimStatusDesc =  msg.submitDesc;
				}
			
				if (action === 'approve-icon') {
					config.claimStatusCd = configs.claimFullyApproved;
					config.claimStatusDesc =  msg.approveDesc;
				}
				
				if (action === 'partapprove-icon') {
					config.claimStatusCd = configs.claimPartiallyApproved;
					config.claimStatusDesc = msg.disApproveDesc;
				}
				
				if (action === 'cross_icon') {
					config.claimStatusCd = configs.claimRejected;
					config.claimStatusDesc = msg.rejectDesc;

				}
				
				if (action === 'info-icon') {
					config.claimStatusCd = configs.claimMoreinfo;
					config.claimStatusDesc =  msg.moreInfoRequiredDesc;
				}
				
				if (action === 'resubmit-icon') {
					config.claimStatusCd = configs.claimResubmitted;
					config.claimStatusDesc = msg.reSubmitDesc;
				}
				
				var claimStatusPanel = new IPD.manageClaim.ClaimStatusPanel(config);
				var statusWindow = new Ext.Window({
					title: 'New status',
					items:claimStatusPanel.getPanel(),
					frame:true,
					height:'40%',
					width:'40%',
					listeners:{
						beforeshow : function(comp){
							claimStatusPanel.loadData(config);
						}						
					}
				});
				
				statusWindow.show();
				
				Ext.ux.event.Broadcast.subscribe('closeClaimStatusWindow',function(){
					statusWindow.close();
				}, this, null, statusWindow );
			}
		});
		
		
		var columns = [this.gridChk,{
							header : 'S.No.',
							dataIndex : 'seqNbr',
							width : 40,
							hidden:true,
							sortable: true
						}, {
							header : 'Claim number',
							dataIndex : 'requestSequenceNbr',
							width : 40,
							sortable: true
						}, {
							header : 'Claim status',
							dataIndex : 'claimStatusCdDesc',
							width : 80,
							sortable: true
						},{
							header : 'ARN',
							dataIndex : 'admissionReqNbr',
							width : 40,
							sortable: true
						}, {
							header : 'Sponsor',
							dataIndex : 'sponsorName',
							width : 150,
							sortable: true
						}, {
							header : 'Insurer',
							dataIndex : 'insurerName',
							width : 150,
							sortable: true
						},{
							header : 'Plan Name',
							dataIndex : 'planName',
							width : 150,
							sortable: true
						}, {
							header : 'Policy number',
							dataIndex : 'policyNbr',
							width : 80,
							sortable: true
						}, {
							header : 'Requested date',
							dataIndex : 'requestDtm',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 100,
							sortable: true
						}, {
							header : 'Approval date',
							dataIndex : 'approvalDate',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 100,
							sortable: true
						}, {
							header : 'Requested amount (Rs.)',
							dataIndex : 'requestedAmount',
							align:'right',
							width : 150,
							sortable: true
						}, {
							header : 'Approved amount (Rs.)',
							dataIndex : 'approvedAmount',
							align:'right',
							width : 150,
							sortable: true
						}, {
							header : 'Claimed amount (Rs.)',
							dataIndex : 'finalClaimedAmount',
							align:'right',
							width : 150,
							sortable: true
						}, {
							header : 'Patient amount (Rs.)',
							dataIndex : 'patientAmount',
							align:'right',
							width : 150,
							sortable: true
						}, this.action];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.includeColumns)){
	        var cs = [];
	        for(var i = 0, len = config.includeColumns.length; i < len; i++){
	            cs.push(columns[config.includeColumns[i]]);
	        }
	        columns = cs;
	    }
	    
	    this.addBtn = new Ext.Button({
	    	iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditClaimRequestWindow({
					 width: '100%',
					 height:'100%',
					 title: 'Add claim request',
					 mode : 'add',
					 mainThis : mainThis});
			}
	    });
	    this.editBtn = new Ext.Button({
	    	iconCls : 'edit_btn',
			text : 'Edit',
			disabled:true,
			scope: this,
			handler : function() {
				if(this.infoGrid.getSelectionModel().getSelections().length > 0){
					var selectedRow = this.infoGrid.getSelectionModel().getSelections()[0].data;
					showAddEditClaimRequestWindow({
						 width: '80%',
						 height:'80%',
						 mode:'edit',
						 title: 'Edit claim request',
						 selectedRow : selectedRow,
						 mainThis : mainThis});
				}
			}
	    });
	    
	    this.deleteBtn = new Ext.Button({
	    	iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope:this,
			handler : function(){
				Ext.Msg.show({
					msg: msg.deleteMessage,
					buttons: Ext.Msg.YESNO,
					icon: Ext.MessageBox.QUESTION,
					fn: function(btnValue){
						if(btnValue == configs.yes){
							var selectedRow = mainThis.infoGrid.getSelectionModel().getSelections();
							var claimList = new Array();
							for(var i = 0; i < selectedRow.length; i++){
								claimList.push(parseInt(selectedRow[i].data.requestSequenceNbr));
							}
							
							InsuranceManager.removeCliamRequest(claimList,function(isDeleted){
								if(!Ext.isEmpty(isDeleted) && isDeleted){
									Ext.ux.event.Broadcast.publish('loadClaimGrid');
								}
							});
						}
					}
				});
			}
	    });
	    
	    var tbar = [
	    	this.addBtn, 
	    	{xtype : 'tbseparator'}, 
	    	this.editBtn, 
	    	{xtype : 'tbseparator'}, 
	    	this.deleteBtn];

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
			sm:this.gridChk,
			columns : columns,
			plugins : this.action,
			viewConfig:{forceFit:true},
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
				render:function( comp ){
					if(comp.getStore().data.items.length == 0){
						comp.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null, null,
																				null, null, null, null, null, null,null]});
					}
				},
				scope:this
				
			}
		});
		
		Ext.ux.event.Broadcast.subscribe('loadClaimGrid',function(){
			if(this.infoGrid.getStore().data.items.length == 0){
				this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null, null,
																				null, null, null, null, null, null, null]});
			}else{
				this.infoGrid.getStore().removeAll();
				this.infoGrid.getStore().reload();
			}
			
			this.editBtn.disable();
			this.deleteBtn.disable();
		},this , null , mainThis.getPanel());
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(searchCriterea) {
		this.dataStore.load({params:{start:0, limit:10}, arg:[
			null,
			searchCriterea['pan'],
			null,
			null,
			null,
			searchCriterea['sponsor'],
			searchCriterea['planName'],
			null,
			searchCriterea['policyNbr'],
			searchCriterea['claimStatus'],
			getStringAsWtcDateFormat(searchCriterea['requestedFromDt']),
			getStringAsWtcDateFormat(searchCriterea['requestedToDt']),
			null,
			null]});
	}
});