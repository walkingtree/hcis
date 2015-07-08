var manageRosterRemoveDataStore;
function getManageRoster(config) {
	if(Ext.isEmpty(config)) {
		config ={};
	}
	Ext.QuickTips.init();
	
	var manageRosterDateStore;
	var gridStore;
	var selectedRosterRecord;
	
	var fromDate = new Ext.form.WTCDateField({
		fieldLabel:msg.fromdate,
		name:'fromdate',
		anchor:'98%',
		listeners:{
			change: function( date , newValue, oldValue){
	   			if(!Ext.isEmpty(date.getValue())){
	   				toDate.setMinValue(date.getValue());
	   			}
	   		}
		}
	});

	var toDate = new Ext.form.WTCDateField({
		fieldLabel:msg.todate,
		name:'todate',
		anchor:'98%',
		listeners:{
			change: function( date, newValue, oldValue ){
	   			if(!Ext.isEmpty(date.getValue())){
	   				fromDate.setMaxValue(date.getValue());
	   			}
	   		}
		}
	});

	var fromTime = new Ext.form.TimeField({
		fieldLabel:msg.fromtime,
		format:'H:i',
		name:'fromtime',
		anchor:'98%',
		listeners:{
			change: function( time ){
	   			if(!Ext.isEmpty(time.getValue())){
	   			}
	   		}
		}
	});
	
	var toTime = new Ext.form.TimeField({
		fieldLabel:msg.totime,
		format:'H:i',
		name:'totime',
		anchor:'98%',
		listeners:{
			change: function( time ){
	   			if(!Ext.isEmpty(time.getValue())){
	   			}
	   		}
		}
	});

	var entityIdTxf = new Ext.form.TextField({
		fieldLabel:msg.entityid,
		name:'entityid',
		anchor:'98%',
		//readOnly:true,
		value: config.type =='doctor'?config.entityid:''
	});
	
	var entityTypeCbx = new Ext.form.OptComboBox({
		forceSelection:true,
		fieldLabel:msg.entitytype,
		mode:'local',
		//readOnly:true,
		//hideTrigger:true,
		store:loadEntityType(),
		displayField:'description',
		valueField:'code',
		triggerAction:'all',
		name:'entitytype',
		anchor:'98%'
	});

	var searchBtn = new Ext.Button({
	   	text: 'Search',
	   	iconCls : 'search_btn',
	   	handler: function() {
	   		if(manageRosterDetailsPnl.getForm().isValid()){
   				var values = manageRosterDetailsPnl.getForm().getValues();
   				manageRosterDateStore.removeAll();
   				manageRosterDateStore.load({params:{start:0, limit:10}, arg:[values]});
   				fromDate.setMaxValue(null);
   				toDate.setMinValue(null);
   				handleButtonState(toolBar,[]);
   			}
	   	}
	});

	var buttonPanel = new Ext.Panel({
		buttonAlign:'right',
		border:false,
		autoHeight: true,
		header: false,
		buttons:[searchBtn,{
			xtype:'button',
			text:'Reset',
			iconCls : 'cancel_btn',
			scope:this,
			handler: function(){
				manageRosterDetailsPnl.getForm().reset();
				var values = manageRosterDetailsPnl.getForm().getValues();
	 			manageRosterDateStore.removeAll();
	 			manageRosterDateStore.load({params:{start:0, limit:10}, arg:[values]});
	 			handleButtonState(toolBar,[]);
			}
		}]
	});	

	var manageRosterDetailsPnl = new Ext.form.FormPanel({
		layout:'column',
		labelAlign:'top',
		border:false,
		defaults:{labelAlign:'left', columnWidth:.33, labelWidth:90},
		items:[
			{
	 			layout:'form',
	 			items:fromDate
	 		},
	 		{
	 			layout:'form',
	 			items:toDate
	 		},
	 		{
	 			layout:'form',
	 			items:fromTime
	 		},
	 		{
	 			layout:'form',
	 			items:toTime
	 		},
	 		{
	 			layout:'form',
	 			items:entityIdTxf
	 		},
	 		{
	 			layout:'form',
	 			items:entityTypeCbx
	 		},
	 		{
	 			layout:'form',
	 			items:[
	 				{
	 					xtype:'optcombo',
	 					forceSelection:true,
						fieldLabel:msg.status,
						name:'status',
						anchor:'98%',
						mode:'local',
						store:loadStatusType(),
						displayField:'description',
						valueField:'code',
						triggerAction:'all'
	 				}
	 			]
	 		},
	 		{
	 			layout:'form',
	 			items:[
	 				{
	 					xtype:'optcombo',
	 					forceSelection:true,
						fieldLabel:msg.roomno,
						mode:'local',
						store:loadRoomName(),
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
						name:'roomno',
						anchor:'98%'
	 				}
	 			]
	 		},
	 		{
	 			layout:'form',
	 			items:[
	 				{
	 					xtype:'textfield',
						fieldLabel:'Entity name',
						name:'entityname',
						anchor:'98%'
	 				}
	 			]
	 		},
	 		{
	 			layout:'form',
	 			items:[
	 				{
	 					xtype:'optcombo',
	 					forceSelection:true,
						fieldLabel:msg.weekdays,
						mode:'local',
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
						name:'day',
						anchor:'98%',
						store:loadWeekDays()
	 				}
	 			]
	 		},{
	 		columnWidth: .66,
	 		items: buttonPanel}
		]
	});
	
	var recordItemType = Ext.data.Record.create([
		{ name: 'entityId', mapping:'entityId',type:'string' },
		{ name: 'entityName', mapping:'entityName',type:'string' },
		{ name: 'entityType', mapping:'entityType',type:'string'},
        { name: 'roomNo', mapping:'roomBM.description',type:'string'},
        { name: 'roomId', mapping:'roomBM.roomCode',type:'string'},
        { name: 'rosterId',  mapping:'rosterCode', type:'int'},
        { name: 'workingDate', mapping:'workingDate',type:'date'},
        { name: 'startTime', mapping: 'startTime',type:'string'},
        { name: 'endTime', mapping: 'endTime',type:'string'},
        { name: 'active', mapping: 'active',type:'bool'},
        { name: 'weekday', mapping: 'weekDay',type:'string'},
        { name: 'isDoctorActive',mapping: 'isDoctorActive',type:'string'}
        ]);
        	
	 	manageRosterDateStore = new Ext.data.Store({
	        reader: new Ext.data.ListRangeReader({id: 'serviceName', totalProperty:'totalSize'}, recordItemType),
	        proxy: new Ext.data.DWRProxy(RosterManagementController.getRosterSummaries, true),
	    	sortInfo:{field: "workingDate", direction: "ASC"}
		});	
	
	var manageRosterGridChk = new Ext.grid.CheckboxSelectionModel({
		listeners:{
			rowselect : function( selectionModel, rowIndex, record){
				var selectedRows = selectionModel.getSelections();
				handleButtonState(toolBar,selectedRows);
			},
			rowdeselect : function( selectionModel, rowIndex, record){
				var selectedRows = selectionModel.getSelections();
				handleButtonState(toolBar,selectedRows);
			}
		}
	});
	
	var pagingBar = new Ext.WTCPagingToolbar({
        store: manageRosterDateStore,
        displayMsg: 'Displaying rosters {0} - {1} of {2}',
        emptyMsg: "No rosters to display"
    });
	
    var editBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         text:msg.edit,
         tooltip:msg.edit,
         icon:'images/pencil.png',
         disabled:true,
         handler: function(){
        	var store = managerosterGridPnl.getSelectionModel().getSelections();
            if(!Ext.isEmpty(store) && store.length == 1) {
	            var starttime = getDateInStringFormat(selectedRosterRecord.workingDate)+" "+getTimeWithColon(selectedRosterRecord.startTime);
	            var endtime = getDateInStringFormat(selectedRosterRecord.workingDate)+" "+getTimeWithColon(selectedRosterRecord.endTime);
	            Ext.ux.event.Broadcast.publish('setIsEditMode');
	            var config = {
	    			mode:'edit',
	    			type:'doctor',
	 				flag: false,
					selectedEntityId: selectedRosterRecord.entityId,
					selectedEntityName: selectedRosterRecord.entityName,
					workingDate: selectedRosterRecord.workingDate,
					selectedEntityType: selectedRosterRecord.entityType,
					selectedStartTime: starttime,
					selectedEndTime: endtime,
					selectedRoomNo: selectedRosterRecord.roomNo,
					selectedActive: selectedRosterRecord.active,
					selectedRosterId: selectedRosterRecord.rosterId,
					selectedRoomNoId: selectedRosterRecord.roomId
				}
				var createRoster = new CreateRoster(config);
				createRoster.loadPanel().show();
            } else {
            	alertError( msg.defaulterrrormessage);
            }
		}
    });
    
    var viewBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         icon:'images/zoom.png',
         text: msg.view,
         tooltip:msg.view,
         disabled:true,
         handler: function(){
        	var store = managerosterGridPnl.getSelectionModel().getSelections();
            if(!Ext.isEmpty(store) && store.length == 1) {
            	var starttime = getDateInStringFormat(selectedRosterRecord.workingDate)+" "+getTimeWithColon(selectedRosterRecord.startTime);
            	var endtime = getDateInStringFormat(selectedRosterRecord.workingDate)+" "+getTimeWithColon(selectedRosterRecord.endTime);
            	var config = {
        			mode:'edit',
        			type:'doctor',
     				flag:true,
   					selectedEntityId: selectedRosterRecord.entityId,
   					selectedEntityName: selectedRosterRecord.entityName,
   					workingDate: selectedRosterRecord.workingDate,
   					selectedEntityType: selectedRosterRecord.entityType,
   					selectedStartTime: starttime,
   					selectedEndTime:endtime,
   					selectedRoomNo: selectedRosterRecord.roomNo,
   					selectedActive: selectedRosterRecord.active,
   					selectedRosterId: selectedRosterRecord.rosterId,
   					selectedRoomNoId: selectedRosterRecord.roomId
				}
            	var window = new CreateRoster(config);
				window.loadPanel().show();
				//getViewMode(window.rosterMainPnl);	
            } else {
            	alertError( msg.defaulterrrormessage);
            }
		}
    });
    
    var deleteBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         text:msg.del,
         tooltip:msg.del,
         icon:'images/delete.png',
         disabled:true,
       	 handler: function(){
       	 	
       	 	Ext.Msg.show({
				msg: "Do you delete selected entries ?",
				icon : Ext.MessageBox.QUESTION,
				buttons: Ext.Msg.YESNO,
				fn: function( btnValue ){
					if( btnValue == configs.yes ){
						handleDeleteBtn(managerosterGridPnl);
						handleButtonState(toolBar , []);
					}
		   		}
			});
        }
    });
	
    var addBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         text:msg.add,
         tooltip:msg.add,
         icon:'images/add.png',
         handler: function(){
         	var addRosterPanel = new CreateRoster({ 
         		type:'doctor', 
         		mode:'', 
         		flag: false, 
         		entityName:config.entityid
     		});
         	
         	var panelToAdd = addRosterPanel.loadPanel();
         	panelToAdd.frame=true;
         	panelToAdd.title = 'Add Roster'; 
         	panelToAdd.closable = true;
         	panelToAdd.height = 420;
         	
         	
         	var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
				
			layout.getCenterRegionTabPanel().setActiveTab( panel );
			layout.getCenterRegionTabPanel().doLayout();
			
			Ext.ux.event.Broadcast.subscribe('closeAddRosterPanel',function(){
//				layout.getCenterRegionTabPanel().remove( panel, true );
				layout.getCenterRegionTabPanel().setActiveTab( manageRosterMainPnl );
				layout.getCenterRegionTabPanel().doLayout();
			},this, null,manageRosterDetailsPnl  );
		 }
    });
    
    var toolBar = new Ext.Toolbar({
    	items:[
			addBtn,'-',
//            editBtn,'-',
//            viewBtn,'-',
            deleteBtn,'-'
    	]
    });
	var managerosterGridPnl = new Ext.grid.GridPanel({
		autoScroll:true,
		stripeRows:true,
		border:false,
		frame:true,
		height:320,
		ds: manageRosterDateStore ,
		sm: manageRosterGridChk,
		enableColumnHide : true,
		viewConfig: {forceFit: true},
		cm: new Ext.grid.ColumnModel ([
				manageRosterGridChk,
				{header: "Entity id", width: 65, sortable: true, dataIndex:'entityId'},
				{header: "Entity name", width: 120, sortable: true, dataIndex:'entityName'},
				{header: "Entity type", width: 100, sortable: true, dataIndex:'entityType'},
				{header: "Roster id", width: 80, sortable: true, dataIndex:'rosterId'},
				{header: "Room no", width: 70, sortable: true, dataIndex:'roomNo'},
				{header: "Working date", width: 80, sortable: true, dataIndex:'workingDate',renderer : Ext.util.Format.dateRenderer('d/m/Y')},
				{header: "Start time", width:70,sortable: true, dataIndex: 'startTime'},
				{header: "End time", width:70,sortable: true, dataIndex: 'endTime'},
				{header: "Status", width:65,sortable: true, hidden: true, dataIndex: 'active'},
				{header: "Week day", width:75,sortable: true, dataIndex: 'weekday'},
				{header: "Active?", width:75,sortable: true, dataIndex: 'isDoctorActive'}
		]),
		bbar:pagingBar,
		listeners: {
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedRosterRecord = thisGrid.getStore().getAt(rowIndex).data;
					var selectedRosterRecords = thisGrid.getSelectionModel().getSelections();
					handleButtonState(toolBar,selectedRosterRecords);
				}
		},
		tbar:toolBar
	});	
	
	/**
	 * The code below hides the Entity type column of the grid
	 */
	managerosterGridPnl.getColumnModel().setHidden( managerosterGridPnl.getColumnModel().findColumnIndex('entityType'), true );
	
	var manageRosterMainPnl = new Ext.Panel({
         layout:'column',
		  height:'100%',
		  frame:true,
		   items:[
		          {
			          columnWidth:1,
			          border:false,
			          layout:'form',
			          items:manageRosterDetailsPnl
		           },
		           {
			          columnWidth:1,
			          border:false,
			          layout:'form',
			          items:managerosterGridPnl
		           }
		          ]
		});
	
//	var manageRosterWindow = new Ext.Window({
//		title:msg.managerosters,
//   	    height:'70%',
//        width:'80%',
//      	modal :true,
//      	closable:true,
//	  	resizable :false,
//	  	items:manageRosterMainPnl,
//	  	listeners:{
//	  		beforeshow:function(){
//	  			var entityTypeConfig ={entitytype:configs.doctor};
//	  			manageRosterDetailsPnl.getForm().setValues(entityTypeConfig);
//	  		},
//	  		show:function(){
//	  			Ext.ux.event.Broadcast.publish('reloadRosterStore');
//	  		}
//	  		
//	  	},
//	  	buttons:[
//	  		{
//				xtype:'button',
//	   			text:'Cancel',
//	   			handler:function(){
//	   				manageRosterWindow.close();
//	   			}
//			}
//	  	]
//	});
	
	Ext.ux.event.Broadcast.subscribe('reloadRosterStore',function(){
		if(managerosterGridPnl.getStore().data.items.length == 0){
			managerosterGridPnl.getStore().load({params:{start:0, limit:10}, 
						arg:[manageRosterDetailsPnl.getForm().getValues()]});
		}else{
			managerosterGridPnl.getStore().removeAll();
			managerosterGridPnl.getStore().reload();
		}
	},this,null,manageRosterMainPnl);
	
	manageRosterDetailsPnl.on("render", function() {
		var values = manageRosterDetailsPnl.getForm().getValues();
		if (manageRosterDateStore.items) {
			manageRosterDateStore.removeAll();
		}
		if( !Ext.isEmpty(config.entityid)){
			values.entityid = config.entityid;
		}
		manageRosterDateStore.load({params:{start:0, limit:10}, arg:[values]});
	});
	
	var handleButtonState = function( toolBar , gridRows){
		var buttons = toolBar.items.items;
		
		if( gridRows.length == 1){
			for(var i = 0; i < buttons.length ; i++){
				if(buttons[i].text != msg.add &&
					gridRows[0].data.isDoctorActive == "YES"){
					buttons[i].enable();
				}else{
					if(buttons[i].text == msg.add){
						buttons[i].enable();
					}else{
						buttons[i].disable();
					}
				}
			}
		}else{
			for(var i = 0; i < buttons.length ; i++){
				if(buttons[i].text != msg.add ){
					buttons[i].disable();
				}
			}
		}
	}
	
	manageRosterMainPnl.on('destroy',function( comp ){
		InstanceFactory.removeInstance( comp.windowCode );
	},this);

	
	return manageRosterMainPnl;
}

function handleDeleteBtn( managerosterGridPnl ){
	var store = managerosterGridPnl.getSelectionModel().getSelections();
    if(!Ext.isEmpty(store) && store.length >0) {
    var rosterModellist =[];
    for(i = 0; i<store.length;i++) {
    	var rosterData = store[i].data;
    	rosterModel = {
			entityType: rosterData.entityType,
			entityId: rosterData.entityId,
			rosterCode: rosterData.rosterId,
			workingDate: rosterData.workingDate,
			startTime: rosterData.startTime,
	 		endTime: rosterData.endTime,
	 		roomBM: {code:rosterData.roomId,description:rosterData.roomNo},
	 		active : rosterData.active
		}
		rosterModellist[i] = rosterModel;
    }
    
    manageRosterRemoveDataStore = new Ext.data.Store({
		proxy : new Ext.data.DWRProxy(RosterManagementController.removeRoster, true),
		reader : new Ext.data.ListRangeReader({
			id : 'id',
			totalProperty : 'totalSize'}, 
			manageAppointmentRecordType),
		remoteSort : true
	});
	
	manageRosterRemoveDataStore.load({params:{start:0, limit:999}, arg:[rosterModellist,null]});
	manageRosterRemoveDataStore.on({
           'load':{
               fn: function(){
               		if(manageRosterRemoveDataStore.data.items.length>0) {
	                	var window = new Ext.Window({
						   	height:'80%',
						   	width:'80%',
						  	modal:true,
						  	title:'Effected appointments',
						    resizable:false,
						    items:getRemoveAppointmentGrid(),
					   	    buttons:[
					   			{
					   				 xtype:'button',
					   				 text: "Remove roster",
					   				 style:'margin-left:500px;margin-top:10px;margin-bottom:10px',
									 cls:'x-btn-text-icon',
	   			 					 icon:'images/accept.png',
	   			 					 handler:function() {
	   			 					 	RosterManagementController.removeRoster(rosterModellist,true,0,0,'');
	   			 					 	window.close();
	   			 					 	managerosterGridPnl.getStore().reload();
	   			 					 }
					   			},
					   			{
					   				xtype:'button',
					   				text:msg.btn_cancel,
					   				clsIcon:'cancel_btn',
					   				scope:this,
					   				handler:function(){
					   					window.close();
					   				}
					   			}
				   			]
						});
						
						window.show();
					}else {
						managerosterGridPnl.getStore().reload();
					}
               },
               scope:this
           }
        });	
	}
}

function getRemoveAppointmentGrid() {
	var bbar = new Ext.WTCPagingToolbar({
		store : manageRosterRemoveDataStore,
		displayInfo : true
	});

	var manageAppointmentGridPnlRosterPnl = new Ext.grid.GridPanel({
		autoScroll : true,
		stripeRows : true,
		border : false,
		frame : true,
		height : 250,
		viewConfig:{forceFit:true},
		ds : manageRosterRemoveDataStore,
		cm : new Ext.grid.ColumnModel([ {
					header : "Appointment No.",
					width : 50,
					sortable : true,
					dataIndex : 'appointmentNumber'
				}, {
					header : "Patient Name",
					width : 150,
					sortable : true,
					dataIndex : 'patientName'
				}, {
					header : "Patient First Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientFirstName'
				}, {
					header : "Patient Middle Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientMiddleName'
				}, {
					header : "Patient Last Name",
					width : 150,
					hidden : true,
					sortable : true,
					dataIndex : 'patientLastName'
				}, {
					header : "Patient ID",
					width : 100,
					sortable : true,
					dataIndex : 'patientId'
				}, {
					header : "Appointment Date",
					dataIndex : 'appointmentDate',
					renderer : Ext.util.Format.dateRenderer('d/m/Y')
				}, {
					header : "From Time",
					width : 80,
					sortable : true,
					dataIndex : 'appointmentStartTime'
				}, {
					header : "To Time",
					width : 80,
					sortable : true,
					dataIndex : 'appointmentEndTime'
				}, {
					header : "Appointment Status",
					width : 125,
					sortable : true,
					dataIndex : 'appointmentStatus'
				}, {
					header : "Appointment Status Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'appointmentStatusCode'
				}, {
					header : "Amount",
					width : 100,
					sortable : true,
					dataIndex : 'amount'
				}, {
					header : "Speaciality Name",
					width : 125,
					sortable : true,
					dataIndex : 'speaciality'
				}, {
					header : "Speaciality Code",
					width : 125,
					hidden : true,
					sortable : true,
					dataIndex : 'speacialityCode'
				}, {
					header : "Booking Type",
					width : 100,
					sortable : true,
					dataIndex : 'bookingType'
				}, {
					header : "Booking Type Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'bookingTypeCode'
				}, {
					header : "Doctor Name",
					width : 100,
					sortable : true,
					dataIndex : 'primaryDoctor'
				}, {
					header : "Doctor Code",
					width : 100,
					hidden : true,
					sortable : true,
					dataIndex : 'primaryDoctorCode'
				}]),

		bbar : bbar
	});
	return manageAppointmentGridPnlRosterPnl;
 }
