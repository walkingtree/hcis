Ext.namespace("administration.manageBed");

administration.manageBed.AssignBed = Ext.extend(Object, {
        constructor : function(config) {
                Ext.apply(this, config);
        
                var mainThis = this;
                var  prefBedRecord = Ext.data.Record.create([
                { name: 'bedNumber', mapping:'bedMasterBM.bedNumber' },
                { name: 'nursingUnitCode', mapping:'bedMasterBM.nursingUnit.code' },
                { name: 'nursingUnit', mapping:'bedMasterBM.nursingUnit.description' },
        { name: 'bedStatusCode',  mapping:'bedMasterBM.bedStatus.code'},
        { name: 'bedStatusDesc', mapping:'bedMasterBM.bedStatus.description'},
        { name: 'bedTypeCode', mapping:'bedMasterBM.bedType.code'},
        { name: 'bedTypeDesc', mapping: 'bedMasterBM.bedType.description'},
        { name: 'roomNbrCode', mapping: 'bedMasterBM.roomNbr.code'},
        { name: 'roomNbr', mapping: 'bedMasterBM.roomNbr.description'},
        { name: 'floorNbr', mapping: 'bedMasterBM.floorNbr'},
        {name: 'bedDesc',mapping:'bedMasterBM.description'},
        {name: 'dailyCharge',mapping:'bedMasterBM.dailyCharge',convert:numberToString},
        {name: 'hourlyCharge',mapping:'bedMasterBM.hourlyCharge',convert:numberToString},
        {name: 'depositAmount',mapping:'bedMasterBM.depositAmount',convert:numberToString},
        {name:'bedMasterBM.specialFeatureAvailabilityList'}
        ]);
                var preferedBedStore = new Ext.data.Store({
                                reader: new Ext.data.ListRangeReader({id: 'bedMasterBM.bedNumber', totalProperty:'totalSize'}, prefBedRecord),
                        proxy: new Ext.data.DWRProxy(BedManager.findPreferredBedAvailability, true)
                        });
                        
                this.infoGrid = new administration.manageBed.BedGrid({plugins:[], hideToolbar: true, store: preferedBedStore});
                
                this.searchBedPanel = new administration.manageBed.SearchBeds({ hideToolbar: true});
                this.searchBedsPnl =    new Ext.form.FieldSet({
                        title: 'Search beds',
                        collapsible: true,
                        collapsed: true,
                        width: '96%',
                        style:'padding:5px',
                        autoHeight: true,
                        autoScroll: true,
                        items: this.searchBedPanel.getPanel()
                });               
                
                this.preferredBedPnl =  new Ext.form.FieldSet({
                        title: 'Preferred beds',
                        collapsible: true,
                        width: '96%',
                        style:'padding:5px',
                        autoHeight: true,
                        hidden:true,
                        items: this.infoGrid.getPanel()
                });               
                
                this.findARN = new Ext.Button({
                                cls:'x-btn-icon',
                                icon:'images/find.png',
                                tooltip : msg.findARN,
                                scope:this,
                                style:'margin-bottom:7px',
                                handler:function(){
                                        searchARNPanel( this.assignBedInfoPanel.getForm(), this ).show();
                                }
                });
                
                this.findBRN = new Ext.Button({
                                cls:'x-btn-icon',
                                icon:'images/find.png',
                                tooltip : msg.findBRN,
                                scope:this,
                                style:'margin-bottom:7px',
                                handler:function(){
                                        searchBRNPanel( this.assignBedInfoPanel.getForm(), this ).show();
                                }
                });
                
                this.findBed = new Ext.Button({
                                cls:'x-btn-icon',
                                icon:'images/find.png',
                                tooltip : msg.findBed,
                                scope:this,
                                style:'margin-bottom:7px',
                                handler:function(){
                                        searchBedPanel( this.assignBedInfoPanel.getForm(), this ).show();
                                }
                });
                
                this.assignBtn = new Ext.Button({
                        text:'Assign',
                        iconCls:'save_btn',
                        scope:this,
                        style:'margin-bottom:7px',
                        handler:function(){
                                if (this.assignBedInfoPanel.getForm().isValid()) {
                                        var valuesMap = this.assignBedInfoPanel.getForm().getValues();
                                        var authorisedUser = getAuthorizedUserInfo();
                                        var bedReservationNbr = Ext.isEmpty( valuesMap['assignedBedReservationNbr'])? null :valuesMap['assignedBedReservationNbr'] - 0;
                                        
                                        if(!Ext.isEmpty(config) && config.bedTransfer==true ){
                                                BedManager.transferPatientToNewBed(config.currentBedNbr, valuesMap['assignedBedNumber'] , 
                                                                 authorisedUser.userName, 
                                                                 function(bedMasterBM){
                                                                        
                                                                        if( !Ext.isEmpty( mainThis.bedTransfer) && mainThis.bedTransfer == true ){
                                                                                layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
                                                                                Ext.ux.event.Broadcast.publish("closeAssignWindowFromAdmissions");
                                                                                return;
                                                                        }
                                                                        mainThis.infoGrid.getPanel().getStore().removeAll();
                                                                        mainThis.assignBedInfoPanel.getForm().reset();
                                                        });
                                        }else{
                                                
                                                
                                                BedManager.assignBed(valuesMap['assignedBedNumber'] , valuesMap['assignedAdmissionReqNbr'], 
                                                         bedReservationNbr ,
                                                         authorisedUser.userName,false, 
                                                         function(bedMasterBM){
                                                                
                                                                if( !Ext.isEmpty( mainThis.isFromAdmission) && mainThis.isFromAdmission == true ){
                                                                        layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
                                                                        Ext.ux.event.Broadcast.publish("closeAssignWindowFromAdmissions");
                                                                        return;
                                                                }
                                                                
                //                                              Ext.Msg.alert("Assigned", "Bed: " + bedMasterBM.bedNumber + " assigned");
                                                                mainThis.infoGrid.getPanel().getStore().removeAll();
                                                                mainThis.assignBedInfoPanel.getForm().reset();
                                                                
                                                                if(!Ext.isEmpty( mainThis.isPopup ) && mainThis.isPopup == true ){
                                                                        layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
                                                                        Ext.ux.event.Broadcast.publish("closeAssignWindow");
                                                                }
                                                });
                                        }
                                } else {
                                        error("Enter valid data and try again!");
                                }
                        }
                });
                
                this.arnTxf = new Ext.form.TextField({
                                required: true,
                                allowBlank: false,
                                anchor:'98%',
                                fieldLabel: 'ARN',
                                name: 'assignedAdmissionReqNbr',
                                value: config.arn
                });
                
                this.brnTxf = new Ext.form.TextField({
                                fieldLabel: 'BRN',
                                anchor:'98%',
                                name: 'assignedBedReservationNbr',
                                value: config.brn
                });
                
                this.bedNumberTxf = new Ext.form.TextField({
                                required: true,
                                allowBlank: false,
                                anchor:'98%',
                                fieldLabel: 'Bed number',
                                name: 'assignedBedNumber'
                });
                                        
                this.assignBedInfoPanel = new Ext.form.FormPanel({
                        frame: true,
                        border: false,
                        monitorValid: true,
                        items:[{
                                layout:'column',
                                border: false,
                                items:[
                                {
                                        layout:'column',
                                        labelWidth:50,
                                        columnWidth:.30,
                                        items:[
                                                {
                                                        layout:'form',
                                                        columnWidth:.85,
                                                        items:this.arnTxf
                                                },
                                                {
                                                        layout:'form',
                                                        columnWidth:.10,
                                                        items:this.findARN
                                                }
                                        ]
                                },
                                {
                                        layout:'column',
                                        labelWidth:50,
                                        columnWidth:.30,
                                        items:[
                                                {
                                                        layout:'form',
                                                        columnWidth:.85,
                                                        items:this.brnTxf
                                                },
                                                {
                                                        layout:'form',
                                                        columnWidth:.10,
                                                        items:this.findBRN
                                                }
                                        ]
                                },
                                {
                                        layout:'column',
                                        labelWidth:80,
                                        columnWidth:.30,
                                        items:[
                                                {
                                                        layout:'form',
                                                        columnWidth:.85,
                                                        items:this.bedNumberTxf 
                                                },
                                                {
                                                        layout:'form',
                                                        columnWidth:.10,
                                                        items:this.findBed
                                                }
                                        ]
                                }
                                
//                              ,
//                              {
//                                      layout:'form',
//                                      columnWidth:.07,
//                                      items:[
//                                      {
//                                              xtype:'button',
//                                              cls:'x-btn-icon',
//                                              icon:'images/find.png',
//                                              tooltip : 'Find admission',
//                                              scope:this,
//                                              style:'margin-bottom:7px',
//                                              handler:function(){
//                                              }
//                                      }
//                                      ]
//                              }
                                ,  
                                {
                                        layout:'form',
                                        columnWidth:.10,
                                        items:this.assignBtn
                                }
                                ]
                                }
                        ]
                });
                
                this.assignBedPanel = new Ext.Panel({
                        autoScroll: true,
                        border: false,
                        width:'100%',
                        height:'100%',
                        items: [this.assignBedInfoPanel, this.preferredBedPnl/*, this.searchBedsPnl*/]
                });

                this.preferredBedPnl.on("render", function() {
                        if (config.brn){
                                preferedBedStore.load({params:{start:0, limit:10}, arg:[config.brn],
                                callback : function(records, options, success){
                                                        if( !Ext.isEmpty( records ) && records.length > 0 ){
                                                                        this.preferredBedPnl.show();
                                                        }
                                                         },
                                        scope:this
                                });
                        }
                },this);
                
                this.assignBedInfoPanel.on("clientvalidation",
                        function(thisForm, isValid) {
                                if (isValid) {
                                        this.assignBtn.enable();
                                } else {
                                        this.assignBtn.disable();
                                }
                        }, 
                this);
                
        },
        getPanel : function() {
                return this.assignBedPanel;
        }
});


function searchBedPanel( formPanel, scope  ){
var searchPanel =new administration.manageBed.SearchBeds({ hideToolbar: true,showAvailableBeds:true  });
        /*
         * showAvailableBeds property is used to display only available beds in case of Assign bed window
         */
        searchPanel.infoGrid.getPanel().on('cellclick',function(grid , rowIndex, columnIndex, e){
                var selectedRowModel = grid.getSelectionModel();
                var rowCount = selectedRowModel.getCount();
                selectedRowModel.singleSelect=true;
                if( rowCount === 1 ){
                        okBtn.enable();
                }else{
                        okBtn.disable();
                }
        });
        
        searchPanel.infoGrid.getPanel().setHeight( 200 );
        var okBtn = new Ext.Button({
                text: msg.ok,
                disabled:true,
                iconCls:'accept-icon ',
                handler:function(){
                        var selectedRowModel =searchPanel.infoGrid.getPanel().getSelectionModel();
                        var selectedRowData = selectedRowModel.getSelections();
                        var rowCount = selectedRowModel.getCount();
                        if(rowCount === 1){
                        	if(selectedRowData[0].data.bedTypeCode=="OT"){
                        		Ext.Msg.alert("Error","Unable to add Operation theater rooms to patients.");
                        	}else{
                                var config ={ assignedBedNumber:selectedRowData[0].data.bedNumber }
                                formPanel.setValues( config );
                                window.close();   
                        	}
                        }
                }
        });
        searchPanel.getPanel().frame = true;
        
        var window = new Ext.Window({
                        modal:true,
                        resizable:false,
                        title:msg.findBed,
                        width:'90%',
                        height:'80%',
                        closeAction: 'hide',
                        items:searchPanel.getPanel(),
                        y:10,
                        buttons:[
                                okBtn,
                                {
                                        text: msg.btn_cancel,
                                        iconCls:'cross_icon',
                                        scope:this,
                                        handler: function(){
                                                window.close();
                                        }
                                }
                        ]
                });
                
                return window;

}

function searchARNPanel( formPanel, scope  ){
        
        var searchPanel =new IPD.admission.manageAdmissions.ManageAdmissions({ hideToolbar: true });
        
        searchPanel.infoGrid.getColumnModel().setHidden( 14, true );
        
        searchPanel.infoGrid.on('cellclick',function(grid , rowIndex, columnIndex, e){
                var selectedRowModel = grid.getSelectionModel();
                var rowCount = selectedRowModel.getCount();
                if( rowCount === 1 ){
                        okBtn.enable();
                }else{
                        okBtn.disable();
                }
        });
        
        searchPanel.infoGrid.setHeight( 200 );
        var okBtn = new Ext.Button({
                text: msg.ok,
                disabled:true,
                iconCls:'accept-icon ',
                handler:function(){
                        var selectedRowModel =searchPanel.infoGrid.getSelectionModel();
                        var selectedRowData = selectedRowModel.getSelections();
                        var rowCount = selectedRowModel.getCount();
                        if(rowCount === 1){
                                var config ={ assignedAdmissionReqNbr:selectedRowData[0].data.admissionReqNbr }
                                formPanel.setValues( config );
                                window.close();         
                        }
                }
        });
        searchPanel.getPanel().frame = true;
        
        var window = new Ext.Window({
                        modal:true,
                        resizable:false,
                        title:msg.findARN,
                        width:'90%',
                        height:'80%',
                        closeAction: 'hide',
                        items:searchPanel.getPanel(),
                        y:10,
                        buttons:[
                                okBtn,
                                {
                                        text: msg.btn_cancel,
                                        iconCls:'cross_icon',
                                        scope:this,
                                        handler: function(){
                                                window.close();
                                        }
                                }
                        ]
                });
                
                return window;

}

function searchBRNPanel( formPanel, scope  ){
        
        var searchPanel =new administration.manageBed.ManageBedBooking({ hideToolbar: true });
        
        searchPanel.infoGrid.getPanel().getColumnModel().setHidden( 11, true );
        
        searchPanel.infoGrid.getPanel().on('cellclick',function(grid , rowIndex, columnIndex, e){
                var selectedRowModel = grid.getSelectionModel();
                var rowCount = selectedRowModel.getCount();
                if( rowCount === 1 ){
                        okBtn.enable();
                }else{
                        okBtn.disable();
                }
        });
        
        searchPanel.infoGrid.getPanel().setHeight( 150 );
        var okBtn = new Ext.Button({
                text: msg.ok,
                disabled:true,
                iconCls:'accept-icon ',
                handler:function(){
                        var selectedRowModel =searchPanel.infoGrid.getPanel().getSelectionModel();
                        var selectedRowData = selectedRowModel.getSelections();
                        var rowCount = selectedRowModel.getCount();
                        if(rowCount === 1){
                                var config ={ assignedBedReservationNbr:selectedRowData[0].data.bedReservationNbr }
                                formPanel.setValues( config );
                                window.close();         
                        }
                }
        });
        searchPanel.getPanel().frame = true;
        
        var window = new Ext.Window({
                        modal:true,
                        resizable:false,
                        title:msg.findARN,
                        width:'90%',
                        height:'80%',
                        closeAction: 'hide',
                        items:searchPanel.getPanel(),
                        y:10,
                        buttons:[
                                okBtn,
                                {
                                        text: msg.btn_cancel,
                                        iconCls:'cross_icon',
                                        scope:this,
                                        handler: function(){
                                                window.close();
                                        }
                                }
                        ]
                });
                
                return window;

}