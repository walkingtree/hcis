Ext.namespace("OT.register");

OT.register.OTRegisterGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function() {
	var mainThis = this;
	this.bookingStatusList =null;
	this.surgeryStatusList = null;
	this.statusCount = 0;
	
	this.action = new Ext.ux.grid.RowActions({
		 header:'Actions'
		,keepSelection:true
		,widthSlope : 100
		,scope : this
		,actions:[
//			{
//				iconCls:'report-icon',
//				tooltip:'View/Modify notes',
//				hideIndex:'hideViewModifyNotes'
//			},
			{
				iconCls:'change-booking-status',
				tooltip:'Booking status',
				hideIndex:'hideBookingStatus',
				style : 'width : 55'
			},
			{
				iconCls:'change-surgery-status',
				tooltip:'Surgery status',
				hideIndex:'hideSurgeryStatus',
				style : 'width : 55'
			}
		]
	});	
	
	this.action.on({
		action : function(grid, record, action, row, col, a ,t){
		
//			if(action ==="report-icon"){
//				SurgeryManager.getOtNote(record.data.patientSurgeryId, function( otNotesBM ){
//					mainThis.createOTNotesWindow( otNotesBM );
//				});
//			}
//			else{
				var statusMenuItems = new Array();
				var i =0;
				var flag = null;
				if( action ==="change-booking-status"){
					if( !Ext.isEmpty( mainThis.bookingStatusList["rec"+row] )){
						flag = "Booking"
						for ( var i = 0 ; i < mainThis.bookingStatusList["rec"+row].length ; i++) {
							statusMenuItems.push({text : mainThis.bookingStatusList["rec"+row][i].description,status :mainThis.bookingStatusList["rec"+row][i].code });
						}
					}
				}
				if( action ==="change-surgery-status"){
					if( !Ext.isEmpty( mainThis.surgeryStatusList["rec"+row] )){
						flag = "Surgery";
						for ( var i = 0 ; i < mainThis.surgeryStatusList["rec"+row].length ; i++) {
							statusMenuItems.push({text : mainThis.surgeryStatusList["rec"+row][i].description,status :mainThis.surgeryStatusList["rec"+row][i].code });
						}
					}
				}
				var statusMenu = new Ext.menu.Menu({
					floating : true,
					items :statusMenuItems
				});
				statusMenu.on('itemclick',function( item ){
					mainThis.handleStatusMenu( item , record, flag);
				},this);
	
				statusMenu.show(t);
			}	
//		}
	});

    	
        this.record = Ext.data.Record.create( [
         	{ name : 'patientSurgeryId',mapping : 'patientSurgeryId' }, 
	        { name : 'serviceName', mapping :'surgery', convert : getDescription },
	        { name : 'otName', mapping : 'operationTheater', convert : getDescription},
	        { name : 'patient', mapping : 'patientId'},
	        { name : 'patientName', mapping : 'patientName'},
	        { name : 'referenceNumber', mapping : 'referenceNbr'},
	        { name : 'doctorName', mapping : 'doctorName'},
	        { name : 'otBookingNbr', mapping : 'otBookingNbr'},
	        { name : 'surgeryTime', mapping : 'surgeryDate',convert : function(date){
	        	if(!Ext.isEmpty(date)){return date.format('Y-m-d g:i a')}
	        	}},
	        { name : 'surgeryStatusCode', mapping : 'statusCode',convert : getCode},
	        { name : 'surgeryStatusDesc', mapping : 'statusCode',convert : getDescription},
	        { name : 'referenceType', mapping : 'referenceType'},
	        { 
	        	name : 'hideViewModifyNotes', 
	        	convert : function( v,rec ){
	        		return false;
	        	}
	        },
	        { 
	        	name : 'hideBookingStatus', 
	        	mapping : 'bookingStatusList',
	        	convert : function( v,rec ){
	        		if( rec.bookingStatusList == null){
	        			return true;
	        		}
	        		else{
	        			mainThis.createBookingStatusMenu( v ,rec);
	        		}
	        	}
	        },
	        { 
	        	name : 'hideSurgeryStatus',
	        	mapping : 'surgeryStatusList',
	        	convert: function(v , rec){
	        		if( rec.surgeryStatusList == null){
	        			return true;
	        		}
	        		else{
	        			mainThis.createSurgeryStatusMenu( v ,rec);
	        		}
        	}
        }
	        
         ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({
 			listeners : {
				rowselect : function(){
					mainThis.otNotesBtn.disable();
					mainThis.checkListBtn.disable();
					mainThis.setButtonState();
				},
				rowdeselect : function(){
					mainThis.checkListBtn.disable();
					mainThis.checkListBtn.disable();
					mainThis.setButtonState();
				}
			}
 			
 		});

        this.gridColumns = [
			this.gridChk ,
//	           { header :otMsg.serviceID, dataIndex :'patientSurgeryId', width : 100 }, 
	           { header :otMsg.serviceName , dataIndex :'serviceName', width : 100 } ,
	           { header :otMsg.lblOtName , dataIndex :'otName', width : 100 } ,
	           { header :otMsg.patient , dataIndex :'patient', width : 100 } ,
	           { header :otMsg.patientName , dataIndex :'patientName', width : 100 } ,
	           { header :otMsg.referenceNumber , dataIndex :'referenceNumber', width : 100 } ,
	           { header :otMsg.doctorName , dataIndex :'doctorName', width : 100 } ,
	           { header :otMsg.surgeryTime , dataIndex :'surgeryTime', width : 100 } ,
	           { header :otMsg.surgeryStatus , dataIndex :'surgeryStatusDesc', width : 100 } ,
	           this.action
	    ];
        
		this.store = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(SurgeryManager.getOTSurgeries, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
            store: this.store,
            displayMsg: limsMsg.pagingbarDisplayMsg,
	        emptyMsg: "No records to dispalay"
		});

       this.otNotesBtn = new Ext.Button({
	        text: 'OT notes',
	        scope : this,
	        iconCls:'add_btn',
	        disabled : true
        });

       this.checkListBtn = new Ext.Button({
	        text: 'Checklist',
	        scope : this,
	        iconCls:'add_btn',
	        disabled : true
       });
       
       this.otNotesBtn.on('click',function(){
    	   var selectedRecords = this.getSelectedRows();
    	   if( !Ext.isEmpty( selectedRecords)){
    		   var record = selectedRecords[0];
				SurgeryManager.getOtNote(record.data.patientSurgeryId, function( otNotesBM ){
				mainThis.createOTNotesWindow( otNotesBM );
			});

    	   }
       },this);
       
       this.checkListBtn.on('click',function(){
    	   var selectedRecords = this.getSelectedRows();
    	   if( !Ext.isEmpty( selectedRecords )){
    		   var record =  selectedRecords[0];
    		   this.showCheckListPanel(record, null);
    	   }
    	  
       },this);
       
       this.tbar = new Ext.Toolbar({
    	   items : ['-',this.otNotesBtn,'-',this.checkListBtn]
       });

	    this.remoteSort =true;
	  	this.frame = false;
		this.border = false;
		this.height = 250;
		this.width = '100%';
		this.stripeRows =true;
		this.autoScroll =true;
		this.columns = this.gridColumns;
		this.viewConfig = {forceFit :true};
		this.sm = this.gridChk;
		this.plugins = this.action;
		this.bbar = this.pagingBar;
		
		this.on('cellclick',function(){
			this.setButtonState();
		},this)
		
		this.on('render',function(){
			this.loadData();
		},this);
		
		OT.register.OTRegisterGrid.superclass.initComponent.apply(this, arguments);
    },
    
    // This will reset the grid.
    reset : function(){
    	this.getStore().removeAll();
    },
    
    // This will return selected records from the grid.
    getSelectedRows : function(){
    	return this.getSelectionModel().getSelections();
    },
    // This will return all the records from the grid.
    getData : function(){
    	var otList = this.getStore().getRange();
    	return otList;
    },
    // This is responsible to load the data into the grid.
    loadData : function( config ){
    	if( Ext.isEmpty( config )){
    		config = {};
    	}
    	var fromDate = Ext.isEmpty( config['bookingFrom'] ) || config['bookingFrom'] === ""? null : Date.parseDate(config['bookingFrom'],'d/m/Y');
    	var toDate = Ext.isEmpty( config['bookingTo'] ) || config['bookingTo'] === "" ? null : Date.parseDate(config['bookingto'],'d/m/Y');
    	
    	this.getStore().load({params:{start:0, limit:10}, arg:[	config['patientName'],
    	                                                       	config['doctorName'],
    	                                                       	config['otCode'],
    	                                                       	config['surgery'],
    	                                                       	fromDate,
    	                                                       	toDate,
																config['referenceType'],
																config['referenceNbr'],
																config['bookingNbr'],
											   ]});
    },
    // This will prepare MenuItem for Booking status.
    createBookingStatusMenu : function(v , rec){
    	if( Ext.isEmpty( this.bookingStatusList )){
    		this.bookingStatusList = {};
//    		this.bookingStatusCount = 0
    	}
    	if( rec.bookingStatusList != null){
    		this.bookingStatusList["rec"+this.statusCount] = new Array();
    		this.bookingStatusList["rec"+this.statusCount] = rec.bookingStatusList;
    		this.statusCount++;
    	}
    	return false;
    },
    // This will prepare MenuItem for Surgery Status
    
    createSurgeryStatusMenu : function(v, rec){
    	if( Ext.isEmpty( this.surgeryStatusList )){
    		this.surgeryStatusList = {};
//    		this.surgeryStatusCount = 0;
    	}
    	if( rec.surgeryStatusList != null){
    		this.surgeryStatusList["rec"+this.statusCount] = new Array();
    		this.surgeryStatusList["rec"+this.statusCount] = rec.surgeryStatusList;
    		this.statusCount++;
    		return false;
    	}
    },
 // This is the hander for the status MEnuItem.   
    handleStatusMenu : function(item , record , flag){
    	var tmpThis = this; 
    	this.showCheckListPanel(record, item.status , flag);
    },
    
    // This will create Ot NOtes window.
    
    createOTNotesWindow : function( otNotesBM ){
    	
    	var config = {
    		patientName : otNotesBM.patientName,
    		surgery : otNotesBM.surgery,
    		surgeon : otNotesBM.surgeonName,
    		patientSurgeryId : otNotesBM.patientSurgeryId
    	};
    	
    	var otNotesPanel = new OT.register.OTNotes({ otNotesBM :otNotesBM.otNotesFieldsBMList,
													 otRegisterGrid : this});
    	
    	otNotesPanel.setValues( config );

    	this.otNotesWindow = new Ext.Window({
			title: 'OT notes',
			items:otNotesPanel,
			frame:true,
			modal:true,
			height:'40%',
			width:'40%'
		});
		
		this.otNotesWindow.show();

    },
    // This will return selected record from the grid.
    getSelectedRows : function(){
    	return this.getSelectionModel().getSelections();
    },
    
    
    // This will set the status of the button.
    setButtonState : function(){
    	var selectedRecords = this.getSelectedRows();
    	this.otNotesBtn.disable();
    	this.checkListBtn.disable();
    	if( selectedRecords.length === 1){
    		this.otNotesBtn.enable();
        	this.checkListBtn.enable();	
    	}
    	else{
    		this.otNotesBtn.disable();
        	this.checkListBtn.disable();
    	}
    },
    
    // This will create checklist panel based on surgery status or patientSurgeyId.
    // If any checkList is required before or after any surgery status then then this checklist
    // panel will be responsible for the showing the checklist panel.
    showCheckListPanel : function(record , status , statusFlag){
    	
    	var This = this;
    	if( statusFlag != "Booking"){
	    	var referenceNbr = record.data.referenceNumber;
	    	var referenceType = record.data.referenceType;
	    	if( statusFlag != "Surgery"){
	    		var patientSurgeryId = record.data.patientSurgeryId;
	    	}
	    	SurgeryManager.getCheckLists(patientSurgeryId, status, function( checkListBM ){
	    		if( !Ext.isEmpty( checkListBM ) || !Ext.isEmpty( status ) ){
		    		if(!Ext.isEmpty( status )){
		    			var remarksPanel = new OT.register.OTRemarksPanel();
		    		}
		    		patientSurgeryId = record.data.patientSurgeryId;
		    		var checkListPanel = new OT.register.CheckListPanel({checkListBM : checkListBM,
		    															referenceNbr : referenceNbr,
		    															referenceType : referenceType,
		    															status : status,
		    															patientSurgeryId : patientSurgeryId,
		    															remarksPanel : remarksPanel ,
		    															otRegisterGrid : This,
		    															statusFlag : statusFlag,
		    															bookingNbr : record.data.otBookingNbr});
		    		if( !Ext.isEmpty( status )){
			    		remarksPanel.setTitle( "Remarks");
			    		remarksPanel.getOkBtn().hide();
			    		remarksPanel.cancelBtn.hide();
			    		checkListPanel.checkListTabPanel.insert(0,remarksPanel);
			    		checkListPanel.checkListTabPanel.doLayout();
			    		checkListPanel.checkListTabPanel.activeTab = 0;
		    		}
		    		
		
		    		var checkListWindow = new Ext.Window({
		    			title: 'Remarks',
		    			items:checkListPanel,
		    			frame:true,
		    			height:400,
		    			width:'50%',
		    			modal : true,
		    			autoScroll : true
		    		});
		    		checkListWindow.show();
	
	    		}
	    	});
    	}
    	else if( statusFlag === "Booking"){
    		var bookingNbr = record.data.otBookingNbr;
        	var patientSurgeryId = record.data.patientSurgeryId;
        	var remarksPanel = new OT.register.OTRemarksPanel();
   		
	    	var remarksWindow = new Ext.Window({
				title: 'Remarks',
				items:remarksPanel,
				frame:true,
				height:'40%',
				width:'40%'
			});
			remarksWindow.show();
			
			remarksPanel.getOkBtn().on('click',function(){
				var remarks = remarksPanel.getRemarks();
	    		OTManager.updateOtBookingStatus(bookingNbr,status,remarks,getAuthorizedUserInfo().userName,function(){
	    			This.statusCount = 0
	    			This.bookingStatusList = null;
	    			This.loadData();
	    			remarksWindow.close();
	    		});
			});	
    	}
    }
});