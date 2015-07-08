Ext.namespace("administration.manageBed");

function showAddEditBedBookingWindow(config , mainThis) {
	config.source = 'abed';
	var bedPanel = new administration.manageBed.BookBed(config);
	
	var panelToAdd = bedPanel.getPanel();
	panelToAdd.frame = true;
	panelToAdd.title = config.title;
	panelToAdd.closable = true;
	panelToAdd.height = 420;
	
	panelToAdd.on('render',function( thisPanel ){
		var test;
	},this);
	
	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
	
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
	if(config.mode == 'edit'){
	bedPanel.loadData(config);
	}
	
	//bedPanel.getFocus(config);
	
	Ext.ux.event.Broadcast.subscribe('closeBedBookWindow',function(){
//	layout.getCenterRegionTabPanel().remove( panel, true );
	Ext.ux.event.Broadcast.publish('loadBedGrid');
	},this, null , mainThis.getPanel() );
	
}

administration.manageBed.BedBookingGrid = Ext.extend(Object, {
	constructor : function(config) {
		
		this.bedReservationRecord = Ext.data.Record.create([
		{name: 'bedReservationNbr', mapping: 'bedReservationNbr'},
		{name: 'admissionReqNbr', mapping: 'admissionReqNbr'},
		{name: 'admissionStatus', mapping:'admissionStatus'},
		{ name: 'bedNumber', mapping:'bedNumber' },
		{ name: 'unitTypeCode', mapping:'unitType.code' },
		{ name: 'unitTypeDesc', mapping:'unitType.description' },
		{ name: 'createdBy', mapping: 'createdBy'},
        { name: 'reservationStatusCode',  mapping:'reservationStatus.code'},
        { name: 'reservationStatusDesc', mapping:'reservationStatus.description'},
        { name: 'bedTypeCode', mapping:'bedType.code'},
        { name: 'bedTypeDesc', mapping: 'bedType.description'},
        { name: 'requestValidTill', mapping: 'requestValidTill'},
        { name: 'gotPreferredRoom', mapping: 'gotPreferredRoom'},
        { name: 'reservationFromDtm', mapping: 'reservationFromDtm'},
        {name: 'reservationToDtm',mapping:'reservationToDtm'},
        {name: 'patientId',mapping:'patientId'},
        {name:'requiredFacilities'},
        {name: 'desiredFacilities'},
	    {name: 'hide1', type: 'boolean', mapping:'reservationStatus.code', convert: function( val, rec ){
	    	if ( val === "CONFIRMED" && 
	    		 !Ext.isEmpty(rec.admissionStatus) &&
	    		 rec.admissionStatus.code == "ADMITTED" ) 
	    		{return false;}
    		else {return true;}
	    }},
	    {name: 'hide2', type: 'boolean', mapping:'reservationStatus.code', convert: function(val, record){
	    		if (val === "CONFIRMED" || val === "WAITLIST" || val === "CREATED") {return false;}
	    		else {return true;}
	    }}
        
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'bedReservationNbr', totalProperty:'totalSize'}, this.bedReservationRecord),
        	proxy: new Ext.data.DWRProxy(BedManager.findBedBookings, true)
		});
		var mainThis = this;
		
	 	this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,keepSelection:true
			,actions:[{
				iconCls:'user-add-icon'
				,tooltip:'Assign patient',
				hideIndex:'hide1'
			}, {
				iconCls:'cross_icon'
				,tooltip:'Cancel booking',
				hideIndex:'hide2'
			}]
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.dataStore,
            displayMsg : 'Displaying bed bookings {0} - {1} of {2}',
			emptyMsg : "No bed bookings to display"
		});
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			singleSelect:true,
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
//					mainThis.editBtn.disable();
//					mainThis.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
//					mainThis.editBtn.disable();
//					mainThis.deleteBtn.disable();
				}
			}
		});
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : 'Add',
			scope:this,
			handler : function() {
				showAddEditBedBookingWindow({
						 width: 1000
						,title: 'Book bed',
						 isPopup: true},
						 mainThis);
			}
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			handler : function() {
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length>0){
					var config ={
						arn: selectedRows[0].data.admissionReqNbr,
						brn: selectedRows[0].data.bedReservationNbr,
						expectedDOA: selectedRows[0].data.reservationFromDtm,
						expectedDOD: selectedRows[0].data.reservationToDtm,
						bedNbr: selectedRows[0].data.bedNumber,
						bedDescription:selectedRows[0].data.bedDesc,
//						siteName:selectedRows[0].data.
						roomNbr:{code: selectedRows[0].data.roomNbrCode},
						floorNbr:selectedRows[0].data.floorNbr,
						nursingUnitType:{code: selectedRows[0].data.unitTypeCode, description: selectedRows[0].data.unitTypeDesc},
//						nursingUnitType: selectedRows[0].data.nursingUnitCode,
//						nursingUnitType: selectedRows[0].data.nursingUnit,
						bedType:selectedRows[0].data.bedTypeCode,
						bedStatus:selectedRows[0].data.bedStatusCode,
						dailyCharge:selectedRows[0].data.dailyCharge,
						hourlyCharge:selectedRows[0].data.hourlyCharge,
						depositAmount:selectedRows[0].data.depositAmount,
						requiredFacilities: selectedRows[0].data.requiredFacilities,
						desiredFacilities: selectedRows[0].data.desiredFacilities,
						width: 1000,
						title: 'Edit bed booking',
						mode: 'edit'
					};
					showAddEditBedBookingWindow(config, mainThis);
				}
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			xtype : 'tbbutton',
			iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			handler:function(){
				var selectedRows = this.infoGrid.getSelectionModel().getSelections();
				/*if(selectedRows.length>0){
					var romoveBedList = new Array();
					for(var i=0;i<selectedRows.length;i++){
						romoveBedList.push(selectedRows[i].data.bedNumber);
					}*/
					
					BedManager.cancelBooking(selectedRows[0].data.bedReservationNbr,authorisedUser.userName , function(retFlag){
						Ext.Msg.show({
							msg: 'bed(s) removed successfully..!',
							icon : Ext.MessageBox.INFO,
							buttons: Ext.Msg.OK,
							fn:function(btn){
								mainThis.infoGrid.getStore().reload();
							}
						});
					});
				}
		});
		
		var columns = [this.gridChk, {
							header : 'ARN',
							dataIndex : 'admissionReqNbr',
							width : 70,
							sortable: true
						}, {
							header : 'BRN',
							dataIndex : 'bedReservationNbr',
							width : 50,
							sortable: true
						}, {
							header : 'Unit Type',
							dataIndex : 'unitTypeDesc',
							width : 60,
							sortable: true
						}, {
							header : 'Bed Type',
							dataIndex : 'bedTypeDesc',
							width : 80,
							sortable: true
						}, {
							header : 'Bed Number',
							dataIndex : 'bedNumber',
							width : 85,
							sortable: true
						}, {
							header : 'Status',
							dataIndex : 'reservationStatusDesc',
							width : 80,
							sortable: true
						}, {
							header : 'Valid till',
							dataIndex : 'requestValidTill',
							renderer: Ext.util.Format.dateRenderer('d/m/Y g:i A'),
							width : 80,
							sortable: true
						}, {
							header : 'From Date',
							dataIndex : 'reservationFromDtm',
							renderer: Ext.util.Format.dateRenderer('d/m/Y g:i A'),
							width : 80,
							sortable: true
						}, {
							header : 'To Date',
							dataIndex : 'reservationToDtm',
							renderer: Ext.util.Format.dateRenderer('d/m/Y g:i A'),
							width : 80,
							sortable: true
						}, {
							header : 'Patient Id',
							dataIndex : 'patientId',
							width : 80,
							sortable: true
						}, this.action];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.includeColumns)){
	        var cs = [];
	        for(var i = 0, len = config.includeColumns.length; i < len; i++){
	            cs.push(columns[config.includeColumns[i]]);
	        }
	        columns = cs;
	    }
	    
	    var plugins = [new Ext.ux.grid.Search({
				iconCls:'icon-zoom'
				,readonlyIndexes:[]
				,disableIndexes:[]
				,minChars:3
				,autoFocus:true
//				,menuStyle:'radio'
			})];
			
		if (!Ext.isEmpty(config.plugins)) {
			plugins = config.plugins;
		}
	    
	    var tbar = [
	    	this.addBtn
//	    	{
//				xtype : 'tbseparator'
//			}, 
//			this.editBtn, 
//			{
//				xtype : 'tbseparator'
//			}, 
//			this.deleteBtn
		];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }
	    
		this.infoGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				height : 315,
				width: '99.5%',
				viewConfig: {
					forceFit: true
				},
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				tbar : tbar,
				sm:this.gridChk,
				columns : columns,
				plugins : this.action,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
//						mainThis.setGridButtonsState ( thisGrid.getSelectionModel() );
					} 
				}
		});
		Ext.ux.event.Broadcast.subscribe('reloadBedBookingGrid', function(){
			if(mainThis.infoGrid.getStore().data.length>0){
				mainThis.infoGrid.getStore().reload();
			}else{
				mainThis.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null,null,null,null,null]});
			}
			
		}, this , null , mainThis.getPanel());
		
		this.infoGrid.on("render", function(thisCmp) {
			if (this.dataStore.getTotalCount() > 0) {
				this.dataStore.removeAll();
			}
			this.search();
		}, this);
		
	},
	getPanel : function() {
		return this.infoGrid;
	},
	search: function(valueMap,callback) {
		if(!Ext.isEmpty(valueMap)){
			this.dataStore.load({params:{start:0, limit:10}, arg:[valueMap['bedReservationNbr'],valueMap['unitType'],valueMap['bedType'],
										valueMap['bedNumber'],valueMap['reservationStatus'],valueMap['admissionReqNbr'],
										valueMap['expectedDOA']== 'undefined'?null:Date.parseDate(valueMap['expectedDOA'],'d/m/Y'),
										valueMap['expectedDOD']== 'undefined'?null:Date.parseDate(valueMap['expectedDOD'],'d/m/Y')]});
			// resetting the from date and todate values.->callback()
//			callback();
		}else{
			this.dataStore.load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null,null,null]});
		}
		
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
	}
});