Ext.namespace("OPD.billing");
/**
 * This grid contains information about assigned service and packages.
 * It has some row actions for records like-
 * Mark billable/unbillable and cancel assignment.
 * 
 *  It shows running total of billable items also at bottom of the grid.
 */
OPD.billing.AssignedServiceGrid = Ext.extend(Object, {
	constructor: function( config ){
	
		if( Ext.isEmpty( config )){
			config = { };
		}
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		this.recordArray = new Array();
		
		this.action = this.getActionButtons();
		
		this.action.on({
			action : function(grid, record, action, row, col){
				var tmpThis = this;
				if( action == "approve-icon"){
					ServiceManager.markAssignedServiceIsBillable(record.data.id,function( isMarkedAsBilled ){
						if( isMarkedAsBilled ){
							mainThis.recordArray[row].data.hideMarkBillable = true;
							mainThis.recordArray[row].data.hideMarkUnbillable = false;
							mainThis.recordArray[row].data.isBillable = isMarkedAsBilled;
							grid.getStore().removeAll();
							grid.getStore().add( mainThis.recordArray );
							
							//TODO: Confirm whether reloading the grid will be better,or  manually setting 'hideMarkBillable'
							//the assignedServiceBM has isBillable field
						}	
						
					});
				}
				else if( action == "disapprove-icon"){
					ServiceManager.markAssignedServiceIsBillable(record.data.id,function( isMarkedAsUnbilled ){
						if( isMarkedAsUnbilled ){
							mainThis.recordArray[row].data.hideMarkBillable = false;
							mainThis.recordArray[row].data.hideMarkUnbillable = true;
							mainThis.recordArray[row].data.isBillable = isMarkedAsUnbilled;
							grid.getStore().removeAll();
							grid.getStore().add( mainThis.recordArray );
						}
					});
				}
				else if( action == "cross_icon"){
					
					var ID = record.data.id;
					
					if( record.data.packageInd == "Y"){
						ServiceManager.cancelAssignedPackages( ID ,getAuthorizedUserInfo().userName , function(newStatus){
							mainThis.recordArray[row].data.hideCancel = true;
							mainThis.recordArray[row].data.statusDesc = newStatus.description;
							mainThis.recordArray[row].data.status = newStatus.code;
							grid.getStore().removeAll();
							grid.getStore().add( mainThis.recordArray );
						});
					}
					
					else {
						var tmp = mainThis.assocSvcGrid;
						ServiceManager.cancelIndevidualAssignedService( ID, getAuthorizedUserInfo().userName, function(newStatus){
							mainThis.recordArray[row].data.hideCancel = true;
							mainThis.recordArray[row].data.statusDesc = newStatus.description;
							mainThis.recordArray[row].data.status = newStatus.code;
							grid.getStore().removeAll();
							grid.getStore().add( mainThis.recordArray );
							
							
							
						} );
					}
				}	
			}
		});
		
		this.assocSvcRecord = Ext.data.Record.create([
	     	{name: 'serviceCode', mapping:'serviceCode'},
	      	{name: 'serviceName', mapping:'serviceName'},
	      	{name: 'servicePackageCode', mapping:'serviceCode'},
	      	{name: 'servicePackageDecs', mapping:'serviceName'},
	      	{name: 'serviceCharge', mapping:'serviceCharge', type:'float'},
	      	{name: 'noOfUnits', mapping:'numberOfUnits', type:'int'},
			{name: 'chargeIntoUnits', type:'float', defaultValue:'0.00'},
			{name: 'serviceDate'},
			{name: 'packageInd'},
			{name: 'serviceOrderNumber'},
			{name: 'id'},
			]);
        
		this.assocSvcDataStore = new Ext.data.Store({

			reader:  new Ext.data.ArrayReader({id:'id'},this.assocSvcRecord )
		});

		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
					mainThis.setGridButtonsState(selectionModel);
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
					mainThis.setGridButtonsState(selectionModel);
				}
			}
		});
		
		var assocSvcColumnHeaders = 
		  [  
			 this.gridChk, 
			 {header : opBillingMsg.code, dataIndex : 'serviceCode', width : 100, sortable: true},
			 {header : opBillingMsg.name, dataIndex : 'serviceName', width : 100, sortable: true},
			 {header : opBillingMsg.billNumber, dataIndex : 'billNbr',width :130},
			 {header : opBillingMsg.serviceDate, dataIndex : 'serviceDate', width : 150,renderer: Ext.util.Format.dateRenderer('d/m/Y'),sortable: false},
//			 {header : opBillingMsg.noOfUnits, dataIndex : 'noOfUnits', width : 100, sortable: false},
//			 {header : opBillingMsg.chargeIntoUnits, dataIndex : 'chargeIntoUnits', width : 100, sortable: false}, 
			 {header : opBillingMsg.packageIndicator, dataIndex : 'packageInd', width : 200, sortable: false},
			 {header : opBillingMsg.status, dataIndex : 'statusDesc', width : 100, sortable: false},
			 {header : opBillingMsg.charge, dataIndex : 'serviceCharge', width : 100, sortable: false},
			 this.action
		];
			  
		this.editBtn = new Ext.Button({
			text : opBillingMsg.edit,
			iconCls : 'edit_btn',
			scope : this,
			tooltip: opBillingMsg.editGridEntry,
			disabled: true
		});
		
		this.deleteBtn = new Ext.Button({
			text : opBillingMsg.del,
			iconCls : 'delete_btn',
			scope : this,
			disabled: true,
			tooltip: opBillingMsg.deleteEntryFormGrid,
			handler: function(){
				this.deleteHandler();
			}
		});
		var tbar = new Ext.Toolbar({
			items:[
	    	{
				xtype : 'tbseparator'
			}, 
//			this.editBtn, 
//			{
//				xtype : 'tbseparator'
//			}, 
			this.deleteBtn,
	    	{
				xtype : 'tbseparator'
			} 
		 ]
		});
		
		this.totalChargeFld =  new Ext.form.Label();
		
		var totalBar = new Ext.Toolbar({	
//			layout: 'form', 
			items:[
				{xtype: 'tbspacer', width: 825},'-',
			       new Ext.form.Label({text:'Total'}),
			       ' ',' ',' ',
			      this.totalChargeFld
			]
		});
		
		this.assocSvcGrid = new Ext.grid.GridPanel({
				stripeRows : true,
				height : 200,
				autoWidth: true,
				autoScroll : true,
				border : false,
				frame:false,				
				store : this.assocSvcDataStore,
				sm:this.gridChk,
				remoteSort:true,
				forceFit : true,
				columns : assocSvcColumnHeaders,
				tbar : tbar,
				bbar : totalBar,
				plugins : this.action,
				viewConfig:{
					forceFit:true
				},
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					 	mainThis.setGridButtonsState( thisGrid.getSelectionModel() );
					 }
				}
		});
		
		if( config.hideAction ){
			
			this.assocSvcGrid.colModel.config.remove( this.action );
		}
		
		//Update the Total Charge Field on every add or remove
//*********************************************************
		this.assocSvcDataStore.on('add', function( store,records,index){
			
			this.updateTotalChargeFld();
			
		},this);
		
		this.assocSvcDataStore.on('remove', function( store,records,index){
			
			this.updateTotalChargeFld();
			
		},this);
		
		this.assocSvcDataStore.on('clear', function( store,records,index){
			
			this.totalChargeFld.setText(0.0);
			
		},this);
		
//*********************************************************
		
	},
	getPanel : function(){
		return this.assocSvcGrid;
	},
	getSelectedRow : function(){
		return this.assocSvcGrid.getSelectionModel().getSelections();
	},
	deleteHandler : function(){
		var dataForDeletion = this.getSelectedRow();
		for( var i = 0 ; i < dataForDeletion.length ; i++){
			this.assocSvcGrid.getStore().remove( dataForDeletion[i] );
		}
		this.deleteBtn.disable();
	},
	getStoreData: function(){
		return this.assocSvcGrid.getStore().getRange();
	},
	getData: function(){

		var selectedRows = this.getStoreData();
		if(selectedRows.length>0){
			var  assignedSercvicePackageList = new Array();
			var serviceOrderNumber;
			var assignedServiceBmList = new Array();
			for (var i =0;i<selectedRows.length;i++){
				var selectedRow = selectedRows[i].data;
				if(Ext.isEmpty( selectedRow.id )){
					if(Ext.isEmpty(selectedRow.serviceOrderNumber)){
						if(selectedRow.packageInd == configs.packageIndicator){
							var assignedServicePackageBm ={
								servicePackage:{code:selectedRow.servicePackageCode,description:selectedRow.servicePackageDecs},
								requestedUnit:parseInt(selectedRow.noOfUnits,10)
							};
							assignedSercvicePackageList.push(assignedServicePackageBm);
						}else{
							var assignedServiceBm = {
								service:{code:selectedRow.serviceCode,description:selectedRow.serviceName},
								requestedUnits:parseInt(selectedRow.noOfUnits,10),
								serviceDate:selectedRow.serviceDate
							};
							assignedServiceBmList.push(assignedServiceBm);
						}
					}
				}
				else{
					serviceOrderNumber = selectedRow.serviceOrderNumber;
				}
			}
			var AssignSvcAndPackageBM = {
				assignedPackageBMList:assignedSercvicePackageList,
				assignedServiceBMList:assignedServiceBmList,
				serviceOrderNumber:serviceOrderNumber 
			};
			return AssignSvcAndPackageBM;
		}
		
	},
	//Insert data into assigned service grid
	loadData: function(assignedServiceAndPackageBM){
		if(!Ext.isEmpty(assignedServiceAndPackageBM)){
			if(!Ext.isEmpty(assignedServiceAndPackageBM.assignedServiceBMList) && 
				assignedServiceAndPackageBM.assignedServiceBMList.length >0){
				for(var i = 0; i<assignedServiceAndPackageBM.assignedServiceBMList.length; i++){
					var service = assignedServiceAndPackageBM.assignedServiceBMList[i];
					
					this.loadEachRow(service,configs.packageIndicatorNo);
				}	
			}
			if(!Ext.isEmpty(assignedServiceAndPackageBM.assignedPackageBMList) && 
				assignedServiceAndPackageBM.assignedPackageBMList.length >0){
				for(var j = 0; j<assignedServiceAndPackageBM.assignedPackageBMList.length; j++){
					var servicePackage = assignedServiceAndPackageBM.assignedPackageBMList[j];
					
					this.loadEachRow(servicePackage,configs.packageIndicator);
				}	
			}
		}
		
	},
	
	resetData: function(){
		
		if( !Ext.isEmpty( this.assocSvcGrid.getStore() ) ){

			this.assocSvcGrid.getStore().removeAll();

		}
		
	},
	
	setRecordArrayToGrid : function(){
		if( !Ext.isEmpty( this.recordArray )){
			// If We are editing assigned service from the requisition search screen the this.recordArray will be having some record.		
			this.assocSvcGrid.getStore().removeAll();
			this.assocSvcGrid.getStore().add( this.recordArray );
		}
		
	},
	clearRecordArray : function(){
		this.recordArray = new Array();
	},
	loadEachRow: function(record,packageInd){
		var recType = this.assocSvcGrid.getStore().recordType;
		var noOfUnits = packageInd == configs.packageIndicator?record.requestedUnit:record.requestedUnits;
		var serviceCharge = packageInd == configs.packageIndicator?record.effectiveCharge:record.serviceCharge;
		var chargeIntoUnits =noOfUnits* serviceCharge;
		var status = packageInd == "Y" ? record.assignedPackageStatus : record.assignedServiceStatus ;
		var serviceDate = Ext.isEmpty(record.serviceDate) ? new Date() : record.serviceDate;
		
		var serviceRecord = new recType({
			serviceCode: packageInd == configs.packageIndicator?record.servicePackage.code:record.service.code,
			serviceName: packageInd == configs.packageIndicator?record.servicePackage.description:record.service.description,
			serviceCharge: serviceCharge,
			servicePackageCode: packageInd == configs.packageIndicator?record.servicePackage.code:'',
			servicePackageDecs: packageInd == configs.packageIndicator?record.servicePackage.description:'',
//			noOfUnits: noOfUnits,
			chargeIntoUnits: chargeIntoUnits,
			packageInd: packageInd,
			serviceDate: packageInd != configs.packageIndicator? serviceDate :new Date(),
			serviceOrderNumber: record.serviceOrderNumber,
			id: packageInd == configs.packageIndicator?record.packageAsgmId:record.serviceUid,
			hideMarkBillable : packageInd == "N"? this.getMarkBillableBtn( record.isBillable , status.code , record.lastBillNumber) : true,
			hideMarkUnbillable : packageInd == "N"? this.getMarkUnbillableBtn( record.isBillable , status.code , record.lastBillNumber) : true,
			hideCancel : this.getCancelBtn( packageInd , status.code),
			billNbr:record.lastBillNumber,
			status : status.code,
			statusDesc: status.description,
			isBillable: record.isBillable || 'Y' //Default is 'Y'
		});

		this.assocSvcGrid.getStore().add( serviceRecord );

		this.recordArray.push( serviceRecord );
		
	},
	
	setGridButtonsState : function( selectionModel ){
		var selectedRows = selectionModel.getSelections();
		this.editBtn.disable();
		this.deleteBtn.disable();
		var row = selectedRows[0];
		if( selectedRows.length === 1 && row.data.serviceOrderNumber === ''){
			this.editBtn.enable();
			this.deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			if( row.data.serviceOrderNumber === '' ){
				this.deleteBtn.enable();
			}	
		}
	},
	
	onRender : function( assignedServiceAndPackageBM){
		this.assocSvcGrid.on('render',function(){
			this.loadData( assignedServiceAndPackageBM );
		},this);
	},
	getActionButtons : function(){
		var action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,widthSlope : 50
			,actions:[
				{
					iconCls:'approve-icon',
					tooltip:'Mark billable',
					hideIndex:'hideMarkBillable'
				},
				{
					iconCls:'disapprove-icon',
					tooltip:'Mark unbillable',
					hideIndex:'hideMarkUnbillable'
				},
				{
					iconCls:'cross_icon',
					tooltip:'Cancel',
					hideIndex:'hideCancel'
				}
			]
		});
		
		return action;
	},
	
	getCancelBtn : function( pkgInd , status){
		if( pkgInd == 'Y'){
			if( status == limsMsg.SERVICE_STATUS_REQUESTED){
				return false;
			}
			else {
				return true;
			}
		}
		else{
			if( status == limsMsg.SERVICE_STATUS_REQUESTED){
				return false;
			}
			else 
				return true;
		}
		
	},
	
	getMarkBillableBtn : function( isBillable ,statusCode , billNbr){
		if( isBillable == 'N' && Ext.isEmpty( billNbr )){
			if( statusCode === limsMsg.SERVICE_STATUS_REQUESTED || statusCode == "RENDERED")
			{	
				return false;
			}
			else{
				return true;
			}
		}
		else {
			return true
		}
	},
	
	getMarkUnbillableBtn : function( isBillable , statusCode ,billNbr){
		if( isBillable == "Y" && Ext.isEmpty( billNbr ) ){
			if( statusCode == limsMsg.SERVICE_STATUS_REQUESTED ){
				return false;
			}	
		}
		else{
			return true;
		}
	},

	// Updates the value of totalCharge field, base on the records
	//available in the grid
	updateTotalChargeFld : function(){
		
		var allRecords = this.getStoreData();
		
		var totalCharge = 0.0;
		Ext.each(allRecords, function(curRecord){
			
			if(curRecord.data.isBillable === 'Y' && Ext.isEmpty(curRecord.data.billNbr) && curRecord.data.status != 'CANCELED'){//TODO:try to avoid using statuscode comparison 
				
				totalCharge += curRecord.data.serviceCharge * (curRecord.data.noOfUnits || 1);
			}
			
		},this);
		
		this.totalChargeFld.setText(totalCharge);
	}
}); 