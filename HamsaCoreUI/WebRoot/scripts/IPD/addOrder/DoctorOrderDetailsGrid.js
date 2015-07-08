Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrderDetailsGrid = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		this.selectedRecordData;
	
		this.doctorOrderServiceRecord = Ext.data.Record.create([
//											{ name: 'sequenceNumber', mapping:'sequenceNbr' },
//											{ name: 'subSequenceNumber', mapping:'subSequenceNbr' },
//									        { name: 'serviceCode',  mapping:'serviceCode'},
//									        { name: 'serviceName',  mapping:'serviceName'},
//									        { name: 'responsibleDepartmentCode', mapping:'responsibleDepartment.code'},
//									        { name: 'responsibleDepartmentDescription', mapping:'responsibleDepartment.description'},
//									        { name: 'actionRequiredCode', mapping:'actionRequired.code'},
//									      	{ name: 'actionRequiredDescription', mapping:'actionRequired.description'},
//									      	{ name: 'doctorOrderNbr'},
//									      	{ name : 'packageIndicator', mapping : 'packageIndicator'},
//									      	{ name : 'servicePackageCode', mapping : 'servicePackage',convert : getCode},
//									      	{ name : 'servicePackageDesc', mapping : 'servicePackage',convert : getDescription},
//									      	{ name : 'actionStatus',mapping : 'actionStatus',convert : getCode},
//									      	{ 
//									      		name :'hideCompleted', 
//									      		mapping :'actionStatus',
//									      		convert : function(val , rec){
//									      			if(val.code === configs.orderStatusCreated )
//									      			{return false;}
//									      			else{return true;}
//									      		}
//									      	}
								       ]);
										
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.doctorOrderServiceRecord)
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
			scope:this,
			text : 'Add'
		});
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			scope:this,
			text : msg.edit,
			disabled: true
		});
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : msg.del,
			scope : this,
			disabled: true,
			handler : function() {
				this.deleteBtnClicked();
				this.deleteBtn.disable();
				this.editBtn.disable();
			}
		});
		
		
		this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,widthSlope : 50
			,actions:[
				{
					iconCls:'accept-icon',
					tooltip:'Complete',
					hideIndex:'hideCompleted'
				}
				
			]
		});
		
		this.action.on({
			action:function(grid, record, action, row, col) {
				this.oldRecord = record;
				this.newRecord = this.oldRecord.data;
				this.newRecord.hideCompleted = true;
				this.recordToAdd = new Ext.data.Record(this.newRecord);
				this.row = row;
				this.flag = true;
				
				var config = {
					orderNumber:record.data.orderNumber,
					SequenceNumber:record.data.sequenceNumber,
					SubSeqBumber :record.data.subSequenceNumber,
					newStatus:configs.statusCompleted,
					personId: authorisedUser.userName,
					mode : 'detailMode'
				}
			
				if (action === 'accept-icon') {
					config.okBtnText = msg.completeText;
					config.newStatusCode = configs.orderStatusApproved;
				}
				
				var doctorOrderRemarksPanel = new IPD.doctorOrderList.DoctorOrderRemarksPanel(config);
				var remarksWindow = new Ext.Window({
					title: 'Remarks for ' + record.data.serviceName,
					items:doctorOrderRemarksPanel.getPanel(),
					frame:true,
					height:'40%',
					width:'40%'
				});
				remarksWindow.show();
				Ext.ux.event.Broadcast.subscribe('closeDoctorOrderDetailsRemarksWindow',function(flag){
					remarksWindow.close();
						if(this.flag && flag != false){
							mainThis.infoGrid.store.remove(this.oldRecord);
							mainThis.infoGrid.getStore().insert(this.row, this.recordToAdd);
							this.flag = false;
						}
					Ext.ux.event.Broadcast.publish('loadDoctorOrderGrid');
				}, this, null, remarksWindow)
			}
		});			
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				height : 150,
				width : '100%',
				autoScroll : true,
				border : false,
				store : this.dataStore,
				sm:this.gridChk,
				viewConfig: {forceFit: true},
				plugins : this.action,
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
							var selectedRecord = thisGrid.store.getAt(rowIndex).data;
							if( thisGrid.getSelectionModel().getSelections().length == 1 ){
								mainThis.editBtn.enable();
							}else{
								mainThis.editBtn.disable();
							}
					},
					celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
					}
				},
//				tbar : [//this.addBtn, '-',
//						this.editBtn, '-', 
//						this.deleteBtn
//				],

				columns : [this.gridChk, 
							{
								header : 'Sequence Number',
								dataIndex : 'sequenceNumber',
								width : 125,
								sortable: true
							}, {
								header : 'Sub-sequenceNumber',
								dataIndex : 'subSequenceNumber',
								width : 125,
								sortable: true
							}, {
								header : 'Service',
								dataIndex : 'serviceName',
								width : 125,
								sortable: true
							}, {
								header : 'Responsible Department',
								dataIndex : 'responsibleDepartmentDescription',
								width : 150,
								sortable: true
							}, {
								header : 'Action Required',
								dataIndex : 'actionRequiredDescription',
								width : 125,
								sortable: true
							}, {
								header : 'Package indicator',
								dataIndex : 'packageIndicator',
								width : 100,
								sortable: true
							},
							this.action
				]					
			});
	},
	
	getPanel : function() {
		return this.infoGrid;
	},
	
	deleteBtnClicked: function(){
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
	    if(!Ext.isEmpty(slctdRows) && slctdRows.length>0) {
	    	for(var i = 0; i<slctdRows.length; i++) {
	    		this.infoGrid.stopEditing();
				this.infoGrid.getStore().remove(slctdRows[i]);
	     	}
	    }
    },
    
    returnSelectedDataRecord: function(){
    	var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
      		var record = null;
      		if(slctdRows[0].data.packageIndicator =='N'){
	  			 record ={
		      		sequenceNumber: slctdRows[0].data.sequenceNumber,
		      		serviceCode: slctdRows[0].data.serviceCode,
		      		svcTypeCd: 1,
		      		subSequenceNumber: slctdRows[0].data.subSequenceNumber,
		      		responsibleDepartmentCode: slctdRows[0].data.responsibleDepartmentCode,
		      		actionRequiredDescription: slctdRows[0].data.actionRequiredDescription
	      		}
      		}else{
      			record ={
		      		sequenceNumber: slctdRows[0].data.sequenceNumber,
		      		servicePackage: slctdRows[0].data.serviceCode,
		      		svcTypeCd: 3,
		      		subSequenceNumber: slctdRows[0].data.subSequenceNumber,
		      		responsibleDepartmentCode: slctdRows[0].data.responsibleDepartmentCode,
		      		actionRequiredDescription: slctdRows[0].data.actionRequiredDescription
	      		}
      		}
	     	return record;
  		} 
    },
    loadData:function(config){
    	if(config.mode =='edit'){
    		var gridRows = config.values.doctorOrderDetailsList;
			var record = this.infoGrid.getStore().recordType;
			if(config.values.doctorOrderDetailsList!=null && config.values.doctorOrderDetailsList.length>0 ){
				for(var i = 0; i<config.values.doctorOrderDetailsList.length; i++){
					if(config.values.doctorOrderDetailsList[i].packageIndicator == 'Y'){
						var doctorOrderRecord = new record({
							sequenceNumber:config.values.doctorOrderDetailsList[i].sequenceNbr,
							subSequenceNumber:config.values.doctorOrderDetailsList[i].subSequenceNbr,
							serviceCode:Ext.isEmpty( config.values.doctorOrderDetailsList[i].servicePackage)?'': config.values.doctorOrderDetailsList[i].servicePackage.code,
							serviceName:Ext.isEmpty( config.values.doctorOrderDetailsList[i].servicePackage)?'': config.values.doctorOrderDetailsList[i].servicePackage.description,
							servicePackageCode:Ext.isEmpty( config.values.doctorOrderDetailsList[i].servicePackage)?'': config.values.doctorOrderDetailsList[i].servicePackage.code,
							servicePackageDesc: Ext.isEmpty( config.values.doctorOrderDetailsList[i].servicePackage)?'': config.values.doctorOrderDetailsList[i].servicePackage.description,
							responsibleDepartmentCode:Ext.isEmpty(config.values.doctorOrderDetailsList[i].responsibleDepartment)?'':config.values.doctorOrderDetailsList[i].responsibleDepartment.code,
							responsibleDepartmentDescription:Ext.isEmpty(config.values.doctorOrderDetailsList[i].responsibleDepartment)?'':config.values.doctorOrderDetailsList[i].responsibleDepartment.description,
							actionRequiredCode:config.values.doctorOrderDetailsList[i].actionDesc,
							actionRequiredDescription:config.values.doctorOrderDetailsList[i].actionDesc,
							packageIndicator : config.values.doctorOrderDetailsList[i].packageIndicator,
							hideCompleted : this.getCompletionIcon( config.values.doctorOrderDetailsList[i].actionStatus ),
							orderNumber : config.values.doctorOrderNbr
							
						});
						 this.infoGrid.getStore().add(doctorOrderRecord);
					}else{
						var doctorOrderRecord = new record({
							sequenceNumber:config.values.doctorOrderDetailsList[i].sequenceNbr,
							subSequenceNumber:config.values.doctorOrderDetailsList[i].subSequenceNbr,
							serviceCode:config.values.doctorOrderDetailsList[i].serviceCode,
							serviceName:config.values.doctorOrderDetailsList[i].serviceName,
							responsibleDepartmentCode:Ext.isEmpty(config.values.doctorOrderDetailsList[i].responsibleDepartment)?'':config.values.doctorOrderDetailsList[i].responsibleDepartment.code,
							responsibleDepartmentDescription:Ext.isEmpty(config.values.doctorOrderDetailsList[i].responsibleDepartment)?'':config.values.doctorOrderDetailsList[i].responsibleDepartment.description,
							actionRequiredCode:config.values.doctorOrderDetailsList[i].actionDesc,
							actionRequiredDescription:config.values.doctorOrderDetailsList[i].actionDesc,
							packageIndicator : config.values.doctorOrderDetailsList[i].packageIndicator,
							hideCompleted :this.getCompletionIcon( config.values.doctorOrderDetailsList[i].actionStatus ),
							orderNumber : config.values.doctorOrderNbr
						});
						 this.infoGrid.getStore().add(doctorOrderRecord);
					}
				}
			}
		}
    },
    getData : function(){
    	var tableRows = this.infoGrid.getStore().getRange();
		var doctorOrderDetailsList = new Array();
		for( var i = 0; i<tableRows.length; i++ ) {
			var currRow = tableRows[i].data;
				if(currRow.packageIndicator == 'Y'){
					var doctorOrderDetailsBM = {
						sequenceNbr: parseInt(currRow.sequenceNumber,10),
						subSequenceNbr: parseInt(currRow.subSequenceNumber,10),
						servicePackage: {code : currRow.serviceCode},
						responsibleDepartment: {code:currRow.responsibleDepartmentCode},
						actionDesc: currRow.actionRequiredDescription,
						packageIndicator: currRow.packageIndicator
					}
				doctorOrderDetailsList.push(doctorOrderDetailsBM);
			}else{
				var doctorOrderDetailsBM = {
					sequenceNbr: parseInt(currRow.sequenceNumber,10),
					subSequenceNbr: parseInt(currRow.subSequenceNumber,10),
					serviceCode: currRow.serviceCode,
					responsibleDepartment: {code:currRow.responsibleDepartmentCode},
					actionDesc: currRow.actionRequiredDescription,
					packageIndicator: currRow.packageIndicator,
					serviceCode: currRow.serviceCode,
					serviceName: currRow.serviceName
				}
				doctorOrderDetailsList.push(doctorOrderDetailsBM);
			}
			
		}
		return doctorOrderDetailsList;
    },
    getCompletionIcon : function( actionStatus ){
    	if( configs.statusCompleted == getCode(actionStatus)){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
});