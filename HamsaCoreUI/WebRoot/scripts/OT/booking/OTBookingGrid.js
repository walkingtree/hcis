Ext.namespace("OT.booking");

OT.booking.OTBookingGrid = Ext.extend(Ext.grid.GridPanel, {
    initComponent : function() {
    	
		var slotDateFormat = "d-m-y h:i A";//
	
        this.record = Ext.data.Record.create( [
         	{ name : 'otCode',mapping : 'otId' }, 
	        { name : 'otName', mapping : 'otName'},
	        { name : 'surgeon', mapping : 'doctorName'},
	        { name : 'surgeonCode', mapping : 'doctorId'},
//	        { name : 'surgeryCode', mapping : 'surgeryCode'},
	        { name : 'bookingFrom', mapping : 'availableFromDtm',type : 'date', convert:this.convertDateToDateTime,dateFormat : slotDateFormat},
	        { name : 'bookingTo', mapping : 'availableToDTM', type : 'date',convert:this.convertDateToDateTime,dateFormat : slotDateFormat},
	        { name : 'availableOTSlot', mapping : 'availableOTSlot'}
         ]);
        
        this.widgets = new OT.booking.OTBookingWidgets(); 

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({
 			singleSelect:true,
 			listeners:{
 					rowselect : function( selectionModel, rowIndex, record){
 			if(this.gridChk.getCount() == 1){
	 			this.widgets.getBookBtn().enable();
	 			if( !Ext.isEmpty( this.initialConfig.okBtn)){
	 				this.initialConfig.okBtn.enable();
	 			}
 			}else{
 				this.widgets.getBookBtn().disable();
	 			if( !Ext.isEmpty( this.initialConfig.okBtn)){
	 				this.initialConfig.okBtn.enable();
	 			}
 			}
					},
					rowdeselect : function( selectionModel, rowIndex, record){
						this.widgets.getBookBtn().disable();
						if(this.gridChk.getCount() == 1){
				 			if( !Ext.isEmpty( this.initialConfig.okBtn)){
				 				this.initialConfig.okBtn.enable();
				 			}
						}
						else{
				 			if( !Ext.isEmpty( this.initialConfig.okBtn)){
				 				this.initialConfig.okBtn.enable();
				 			}
						}

					},
					scope:this
 			}
 		});
 		

        this.gridColumns = [
			this.gridChk ,
	           { header :otMsg.bookingCode, dataIndex :'otCode', width : 150 }, 
	           { header :otMsg.OtNamelbl , dataIndex :'otName', width : 150 } ,
	           { header :otMsg.surgeonName , dataIndex :'surgeon', width : 150 } ,
	           { header : otMsg.availableOTSlot, dataIndex : 'availableOTSlot'} ,
	           { header :otMsg.bookingFrom , dataIndex :'bookingFrom', width : 150 } ,
	           { header :otMsg.bookingTo , dataIndex :'bookingTo', width : 100 } 
	    ];
          
		this.store = new Ext.data.GroupingStore({
			proxy :new Ext.data.DWRProxy(OTManager.getOTAvaibilitySlot, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			groupField:'availableOTSlot',
			remoteSort :true,
			sortInfo: {
			    field: 'bookingFrom',
			    direction: 'ASC' // or 'DESC' (case sensitive for local sorting)
			}
		
		});
		
		this.on('render',function(){
	    	  this.loadData();      
		  },this);
		
		this.title = otMsg.availableOTList;
	    this.remoteSort =true;
	  	this.frame = false;
		this.border = false;
		this.height = 300;
		this.width = '100%';
		this.stripeRows =true;
		this.autoScroll =true;
		this.columns = this.gridColumns;
		this.view = new Ext.grid.GroupingView({
	        viewConfig:{forceFit:true},
	        hideGroupedColumn:true,
	        enableGroupingMenu: false,
	        groupTextTpl:'{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
	    });
		this.tbar = [this.widgets.getBookBtn(),this.initialConfig.okBtn];
		this.sm = this.gridChk;
		this.store = this.store;
		
		OT.booking.OTBookingGrid.superclass.initComponent.apply(this, arguments);
    },
    
    loadData : function( config ){
		this.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.getStore().load({params:{start:0, limit:10}, arg:[ config.surgery,
																	config.otCode,
																	config.doctorName,
																	config.timePeriod,
																	config.bookingFrom ,
																	config.bookingTo 
//																	null,null,null,null,null,null
																			]});
		}else{
//			this.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null,null,null]});
		}
    },
    
    getReset : function(){
    	this.getStore().removeAll();
    },
    
    getSelectedRows : function(){
    	return this.getSelectionModel().getSelections();
    },
    
    getData : function(){
    	var otList = this.getStore().getRange();
    	return otList;
    },
    
    //Date format converter 
    convertDateToDateTime : function(date){
    	if(!Ext.isEmpty( date )){
    		return date.format(this.dateFormat);
    	}
    }
    

});
