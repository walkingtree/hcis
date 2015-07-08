Ext.namespace("IPD.admission.manageAdmissions");

//function showAddEditAdmissionWindow(config) {
//      var admissionOrderPanel = new IPD.admission.manageAdmissions.AdmissionOrder(config);
//      var panel = admissionOrderPanel.getPanel();
//      var win = new Ext.ux.Window({
//              width: config.width,
//              items: panel,
//              title: config.title,
//              listeners:{
//                      beforeshow: function(){
//                              if(config.mode == 'edit'){
//                                      // setting the values in edit case.
//                                      admissionOrderPanel.orderPanel.getForm().setValues(config);
//                                      admissionOrderPanel.basicDoctorOrderPanel.doctorOrderServicesGrid.loadData(config);
//                              }
//                      }
//              }
//      });
////    admissionOrderPanel.loadData(config);
//      panel.doLayout();
//      win.show();
//      Ext.ux.event.Broadcast.subscribe('closeAdmissionWindow', function(){
//              win.close();
//      });
//}

function getDate(date){
        if(date != null && !Ext.isEmpty(date)){
                return date.format('d/m/Y');
        }else{
                return null;
        }
}
function getTime(date){
        if(date != null && !Ext.isEmpty(date)){
                return date.format('g:i A');
        }else{
                return null;
        }

}
IPD.admission.manageAdmissions.ManageAdmissions = Ext.extend(Object, {
        constructor : function(config) {
        
                this.bedDetailRecord = Ext.data.Record.create([
                                { name: 'admissionNbr' },
                                { name : 'admissionReqNbr'},
                                { name: 'admissionEntryPointCd', mapping:'admissionEntryPoint' },
                        { name: 'admissionStatusCd',  mapping:'admissionStatus',convert:getCode},
                        { name: 'admissionStatusDesc',  mapping:'admissionStatus',convert:getDescription},
                        { name: 'admissionRequestedBy'},
                        { name: 'doctorId'},
                        { name: 'patientId'},
                        { name: 'patientName'},
                        { name: 'entryPointReference'},
                        { name: 'reasonForAdmission'},
                        { name: 'agenda'},
                        { name: 'admissionDt'},
                        { name: 'treatmentEstimationBy'},
                        { name: 'treatmentEstimatedCost'},
                        { name: 'expectedDischargeDtm'},
                        { name: 'expectedAdmissionDtm'},
                        { name: 'createDate',mapping:'createDtm',convert:getDate},
                        { name: 'createDateTime',mapping:'createDtm',convert:getTime},
                        { name: 'nursingUnitTypeCd',mapping:'nursingUnitType',convert:getCode},
                        { name: 'nursingUnitTypeDesc',mapping:'nursingUnitType',convert:getDescription},
                        { name: 'doctorOrderDetailsList'},
                        { name: 'doctorOrderNbr'},
                        { name: 'doctorOrderStatusCd',mapping:'doctorOrderStatus',convert:getCode},
                        { name: 'doctorOrderStatusDesc',mapping:'doctorOrderStatus',convert:getDescription},
                        { name: 'orderDesc'},
                        { name: 'orderDictation'},
                        { name: 'bedNumber'},
                        { name: 'hideAssignBed', 
                                        type: 'boolean', 
                                        mapping:'admissionStatus', 
                                        convert: function(val, rec){
                                        if (val.code === configs.admissionOrderStatusConfirm && Ext.isEmpty(rec.bedNumber)) 
                                                {return false;}
                                        else {return true;}
                                }
                                },
                                { name: 'hideTransforBed', 
                                        type: 'boolean', 
                                        mapping:'bedNumber', 
                                        convert: function(val, rec){
                                        if (!Ext.isEmpty(val) && val!='') 
                                                {return false;}
                                        else {return true;}
                                }
                                },
                        { name: 'hideBedBook', 
                                type: 'boolean', 
                                mapping:'admissionStatus', 
                                convert: function(val, rec){
                                        if (val.code === configs.admissionOrderStatusRequested ) 
                                                {return false;}
                                        else {return true;}
                                }
                                },
                                { name: 'hideDischarge', 
                                type: 'boolean', 
                                mapping:'admissionStatus', 
                                convert: function(val, rec){
                                        if (val.code === configs.admissionOrderStatusPreDischarge ) 
                                                {return false;}
                                        else {return true;}
                                }
                                },
                                { name: 'hideDischargeSummary', 
                                type: 'boolean', 
                                mapping:'admissionStatus', 
                                convert: function(val, rec){
                                        if (val.code === configs.admissionOrderStatusDischarge || 
                                                val.code === configs.admissionOrderStatusPreDischarge ) 
                                                {return false;}
                                        else {return true;}
                                }
                                },
                                { name: 'hideClaimRequest', 
                                type: 'boolean', 
                                mapping:'admissionStatus', 
                                convert: function(val, rec){
                                        if ( val.code === configs.admissionOrderStatusDischarge ) 
                                                {return true;}
                                        else {return false;}
                                }
                                }
        ]);

        this.action = new Ext.ux.grid.RowActions({
                         header:'Actions'
                        ,keepSelection:true
                        ,actions:[
                                {
                                        iconCls:'user-add-icon',
                                        tooltip:'Assign bed',
                                        hideIndex:'hideAssignBed'
                                },
                                {
                                        iconCls:'bed-transfer-icon',
                                        tooltip:'Transfor bed',
                                        hideIndex:'hideTransforBed'
                                },
                                {
                                        iconCls:'add_btn',
                                        tooltip:'Book bed',
                                        hideIndex:'hideBedBook'
                                },
                                {
                                        iconCls:'discharge-icon',
                                        tooltip:msg.discharge,
                                        hideIndex:'hideDischarge'
                                },
                                {
                                        iconCls:'view-icon',
                                        tooltip:msg.viewDischargeSummary,
                                        hideIndex:'hideDischargeSummary'
                                },
                                {
                                        iconCls:'claim-icon',
                                        tooltip:msg.issueClaimRequest,
                                        hideIndex:'hideClaimRequest'
                                }
                        ]
                });
                
                this.action.on({
                        action:function(grid, record, action, row, col) {
                                
                                if (action === 'user-add-icon') {
                                        var assignBedPnl = new administration.manageBed.AssignBed(
                                                                                                                                { arn: record.data.admissionReqNbr,
                                                                                                                                  isFromAdmission: true});
                                        
                                        var panelToAdd = assignBedPnl.getPanel();
                                        panelToAdd.frame = true;
                                        panelToAdd.title = "Assign bed"; 
                                        panelToAdd.closable = true;
                                        panelToAdd.height = 420;
                                        
                                        var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
                                        
                                        layout.getCenterRegionTabPanel().setActiveTab( panel );
                                        layout.getCenterRegionTabPanel().doLayout();
                                                
                                        Ext.ux.event.Broadcast.subscribe("closeAssignWindowFromAdmissions", function(){
                                                layout.getCenterRegionTabPanel().setActiveTab( mainThis.bookBedPanel );
                                                layout.getCenterRegionTabPanel().doLayout();
                                                
                                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                                
                                        },this , null , mainThis.getPanel());
                                }
                                
                                if (action === 'bed-transfer-icon') {
                                        
                                        var assignBedPnl = new administration.manageBed.AssignBed(
                                                        { arn: record.data.admissionReqNbr,
                                                          currentBedNbr: record.data.bedNumber,
                                                          bedTransfer: true});

                                                        var panelToAdd = assignBedPnl.getPanel();
                                                        panelToAdd.frame = true;
                                                        panelToAdd.title = "Transfor bed"; 
                                                        panelToAdd.closable = true;
                                                        panelToAdd.height = 420;
                                                        
                                                        var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
                                                        
                                                        layout.getCenterRegionTabPanel().setActiveTab( panel );
                                                        layout.getCenterRegionTabPanel().doLayout();
                                                        
                                                        Ext.ux.event.Broadcast.subscribe("closeAssignWindowFromAdmissions", function(){
                                                        layout.getCenterRegionTabPanel().setActiveTab( mainThis.bookBedPanel );
                                                        layout.getCenterRegionTabPanel().doLayout();
                                                        
                                                        Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                        
                                        },this , null , mainThis.getPanel());
                                }
                                
                                if (action === 'add_btn') {
                                        
                                        var config = {source:'MAD',isFromAdmission: true,
                                                                  arn: record.data.admissionReqNbr
                                    };
                                        var bookBedPnl = new administration.manageBed.BookBed( config );
                                        var panelToAdd = bookBedPnl.getPanel();
                                        panelToAdd.frame = true;
                                        panelToAdd.title = "Assign bed"; 
                                        panelToAdd.closable = true;
                                        panelToAdd.height = 420;
                                        
                                        
                                        var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
                                        
                                        layout.getCenterRegionTabPanel().setActiveTab( panel );
                                        layout.getCenterRegionTabPanel().doLayout();
                                        
                                        bookBedPnl.loadData( config );
                                        
                                        Ext.ux.event.Broadcast.subscribe("closeBookBedWindowFromAdmissions", function(){
//                                              var toBeRemovePanel = panel;
//              
//                                              layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
                                                layout.getCenterRegionTabPanel().setActiveTab( mainThis.bookBedPanel );
                                                layout.getCenterRegionTabPanel().doLayout();
                                                
                                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                                
                                        }, this , null , mainThis.getPanel());
                                }
                                
                                if (action === 'discharge-icon') {
                                         var arn = record.data.admissionReqNbr;
                                        OrderManager.getDishcargeSummary( arn, {
                                                callback: function( summary ){
                                                        var config;
                                                        if( !Ext.isEmpty( summary ) ){
                                                                 config ={
                                                                        dischargeSummaryLabel: msg.saveDischargeSummary,
                                                                        dischargeSummaryIcon:msg.editIcon
                                                                 }
                                                        }else{
                                                                config ={
                                                                        dischargeSummaryLabel: msg.saveDischargeSummary,
                                                                        dischargeSummaryIcon:msg.addIcon
                                                                }
                                                        }
                                                        
                                                        var dischargePanel = new IPD.admission.manageAdmissions.DischargePanel( config );
                                                        var dischargeWindow = new Ext.Window({
                                                                modal: true,
                                                                title:'Discharge',
                                                                width:'60%',
                                                                height:'60%',
                                                                items:dischargePanel
                                                        });
                                                        
                                                        dischargePanel.on("clientvalidation", function(thisForm, isValid) {
                                                                if (isValid){
                                                                        dischargePanel.getOkBtn().enable();
                                                                } else {
                                                                        dischargePanel.getOkBtn().disable();
                                                                }
                                                        }, this);
                                                        
                                                        dischargePanel.getOkBtn().on('click', function(){
                                                                
                                                                var arn = record.data.admissionReqNbr;
                                                                var dischargeSummary = dischargePanel.getForm().getValues();
                                                                var createdBy = getAuthorizedUserInfo().userName;
                                                                
                                                                OrderManager.dischargePatient(arn,dischargeSummary['dischargeSummary'],createdBy,{
                                                                        callback: function(){
                                                                                dischargeWindow.close();
                                                                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                                                        
                                                                        },
                                                                        scope:this
                                                                });
                                                        },this);
                                                        
                                                        dischargePanel.getDischargeSummaryBtn().on('click', function(){
                                                                
                                                                var arn = record.data.admissionReqNbr;
                                                                var dischargeSummary = dischargePanel.getForm().getValues();
                                                                var createdBy = getAuthorizedUserInfo().userName;
                                                                
                                                                OrderManager.saveDishcargeSummary(arn,dischargeSummary['dischargeSummary'],createdBy,{
                                                                        callback: function(){
                                                                                dischargeWindow.close();
                                                                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                                                        
                                                                        },
                                                                        scope:this
                                                                });
                                                        },this);
                                                        
                                                        dischargePanel.getCancelBtn().on('click', function(){
                                                                dischargeWindow.close();
                                                        },this);
                                                        
                                                        config = { dischargeSummary:summary };
                                                        dischargePanel.getForm().setValues( config );
                                                        dischargeWindow.show();
                                
                                                },
                                                scope: this
                                        });
                                        
                                }
                                
                                if( action === 'view-icon' ){
                                        var arn = record.data.admissionReqNbr
                                        OrderManager.getDishcargeSummary( arn, {
                                                callback: function( summary ){
                                                        var dischargePanel = new IPD.admission.manageAdmissions.DischargePanel();
                                                        var dischargeWindow = new Ext.Window({
                                                                modal: true,
                                                                title:msg.discharge,
                                                                width:'60%',
                                                                height:'60%',
                                                                items:dischargePanel
                                                        });
                                                        
                                                        dischargePanel.getCancelBtn().on('click', function(){
                                                                dischargeWindow.close();
                                                        },this);
                                                        
                                                        config = { dischargeSummary:summary };
                                                        dischargePanel.getForm().setValues( config );
                                                        dischargePanel.getOkBtn().hide();
                                                        dischargePanel.getDischargeSummaryBtn().hide();
                                                        dischargeWindow.show();
                                                },
                                                scope: this
                                        });
                                }
                                
                                if( action === 'claim-icon' ){
                                        
                                        var config = {
                                                admissionReqNbr: record.data.admissionReqNbr,
                                                patientName: record.data.patientName,
                                                isFromAdmission: true
                                        };
                                        var claimPanel = new IPD.addClaim.Claim( config );
                                        
                                        var claimWindow = new Ext.Window({
                                                modal: true,
                                                title:msg.addClaimRequest,
                                                height:'80%',
                                                width:'90%',
                                                items: claimPanel.getPanel()
                                        });
                                        
                                        claimPanel.getPanel().getForm().setValues( config );
                                        
                                        claimWindow.show();
                                        
                                        Ext.ux.event.Broadcast.subscribe('closeClaimPanelFromAdmission',function(){
                                                claimWindow.close();
                                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                                        }, this, null, claimWindow);
                                
                                }
                        }
                });
                
        this.dataStore = new Ext.data.Store({
                        reader: new Ext.data.ListRangeReader({id: 'admissionReqNbr', totalProperty:'totalSize'}, this.bedDetailRecord),
                proxy: new Ext.data.DWRProxy(AdmissionOrder.findAdmissions, true),
                remoteSort:true
                });
                var mainThis = this;
                
                this.pagingBar = new Ext.WTCPagingToolbar({
                                store : this.dataStore,
                                displayMsg : 'Displaying admisisons {0} - {1} of {2}',
                                emptyMsg : "No admissions to display"
                });
                        
                this.gridChk = new Ext.grid.CheckboxSelectionModel({
                        listeners:{
                                rowselect : function( selectionModel, rowIndex, record){
                                        var selectedRows = selectionModel.getSelections();
                                        if( selectedRows.length == 1){
//                                              mainThis.editBtn.enable();
                                        }else{
//                                              mainThis.editBtn.disable();
                                        }
                                        for(var i =0; i<selectedRows.length;i++){
                                                if(selectedRows[i].data.admissionStatusCd != configs.admmissionStatusRequested){
                                                        mainThis.confirmAdmissionBtn.disable();
                                                }else{
                                                        mainThis.confirmAdmissionBtn.enable();
                                                }
                                        }
                                        
                                },
                                rowdeselect : function( selectionModel, rowIndex, record){
//                                      mainThis.editBtn.disable();
                                        mainThis.confirmAdmissionBtn.disable();
                                }
                        }
                });
                
//              this.addBtn = new Ext.Toolbar.Button({
//                      xtype : 'tbbutton',
//                      iconCls : 'add_btn',
//                      text : 'Add',
//                      scope:this,
//                      handler:function(){
//                              showAddEditAdmissionWindow({width:'90%',
//                                                                                      title:'Create admission request'
//                                                                                      });
//                      }
//              });
//              
//              this.editBtn = new Ext.Toolbar.Button({
//                      xtype : 'tbbutton',
//                      iconCls : 'edit_btn',
//                      text : 'Edit',
//                      disabled:true,
//                      scope:this,
//                      handler:function(){
//                              var selectedRows = this.infoGrid.getSelectionModel().getSelections();
//                              if(selectedRows!=null && selectedRows.length>0){
//                                      var rowData = selectedRows[0].data;
//                                      showAddEditAdmissionWindow({width:'90%',
//                                                                                              title:'Modify admission request',
//                                                                                              mode:'edit',
////                                                                                            patientName:rowData.
////                                                                                            EDOA:rowData.
//                                                                                              EDOD:rowData.expectedDischargeDtm,
//                                                                                              patientId:rowData.patientId,
//                                                                                              doctorId:rowData.doctorId,
//                                                                                              estimatedCost:rowData.treatmentEstimatedCost,
//                                                                                              unitType:rowData.nursingUnitTypeCd,
//                                                                                              admissionEntryPoint:rowData.admissionEntryPointCd,
//                                                                                              entryPointReference:rowData.entryPointReference,
//                                                                                              agenda:rowData.agenda,
//                                                                                              admissionstatus:rowData.admissionStatusCd,
//                                                                                              reasonForAdmission:rowData.reasonForAdmission,
//                                                                                              orderDictation:rowData.orderDictation,
//                                                                                              orderDesc:rowData.orderDesc,
//                                                                                              values:{doctorOrderDetailsList:rowData.doctorOrderDetailsList}
//                                                                                              });
//                              }
//                      }
//              });
                
//              this.deleteBtn = new Ext.Toolbar.Button({
//                      xtype : 'tbbutton',
//                      iconCls : 'delete_btn',
//                      text : 'Delete',
//                      scope:this,
//                      handler:function(){}
//              });
                
                this.confirmAdmissionBtn = new Ext.Button({
                        text:msg.confirmAdmission,
                        scope:this,
                        disabled:true,
                        iconCls:'approve-icon',
                        handler: function(){
                                this.confirmAdmissionHandler();
                        }
                
                });
                
                var toolbarBtns =[
                        this.confirmAdmissionBtn
                ];
                
                if( !Ext.isEmpty( config ) && config.hideToolbar == true ){
                        toolbarBtns = [ ];
                }
                
                this.infoGrid = new Ext.grid.GridPanel({
                                frame : false,
                                stripeRows : true,
                                height : 305,
                                width : '100%',
                                autoScroll : true,
                                border : false,
                                store : this.dataStore,
                                sm:this.gridChk,
                                bbar : this.pagingBar,
                                viewConfig:{ forceFit: true },
                                plugins:[ this.action ],
                                tbar : toolbarBtns,
                                columns : [this.gridChk,  {
                                                        header : 'Request number',
                                                        dataIndex : 'admissionReqNbr',
                                                        width : 50,
                                                        sortable: true
                                                }, {
                                                        header : 'PAN',
                                                        dataIndex : 'admissionNbr',
                                                        width : 35,
                                                        sortable: true
                                                },{
                                                        header : 'Doctor id',
                                                        dataIndex : 'doctorId',
                                                        width : 55,
                                                        sortable: true
                                                }, {
                                                        header : 'Patient id',
                                                        dataIndex : 'patientId',
                                                        width : 55,
                                                        sortable: true
                                                }, {
                                                        header : 'Patient name',
                                                        dataIndex : 'patientName',
                                                        width : 75,
                                                        sortable: true
                                                }, {
                                                        header : 'Requested date',
                                                        dataIndex : 'createDate',
                                                        width : 85,
                                                        sortable: true
                                                }, {
                                                        header : 'Requested time',
                                                        dataIndex : 'createDateTime',
                                                        width : 85,
                                                        sortable: true
                                                }, {
                                                        header : 'Expected DOA',
                                                        dataIndex : 'expectedAdmissionDtm',
                                                        width : 80,
                                                        sortable: true
                                                }, {
                                                        header : 'Expected DOD',
                                                        dataIndex : 'expectedDischargeDtm',
                                                        width : 80,
                                                        sortable: true
                                                }, {
                                                        header : 'Status',
                                                        dataIndex : 'admissionStatusDesc',
                                                        width : 45,
                                                        sortable: true
                                                }, {
                                                        header : 'Bed number',
                                                        dataIndex : 'bedNumber',
                                                        width : 70,
                                                        sortable: true
                                                }, {
                                                        header : 'Admission Date',
                                                        dataIndex : 'admissionDt',
                                                        width : 80,
                                                        sortable: true
                                                },{
                                                        header : 'Nursing unit Type',
                                                        dataIndex : 'nursingUnitTypeDesc',
                                                        width : 100,
                                                        sortable: true
                                                }, this.action],
                                listeners:{
                                        cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
                                                var selectedRecords = thisGrid.getSelectionModel().getSelections();
                                                if(selectedRecords.length == 1){
                                                }else{
                                                }
                                                for(var i =0; i<selectedRecords.length;i++){
                                                if(selectedRecords[i].data.admissionStatusCd != configs.admmissionStatusRequested){
                                                        mainThis.confirmAdmissionBtn.disable();
                                                }else{
                                                        mainThis.confirmAdmissionBtn.enable();
                                                }
                                        }
                                        } 
                                }
                        });
                        
                
                        this.searchBtn = new Ext.Toolbar.Button({
                        xtype: 'button',
                        text: 'Search',
                        iconCls : 'search_btn',
                        handler:function(){
                                this.search();
                        },
                        scope:this
                    });
                    
                    this.resetBtn = new Ext.Button({
                        text: 'Reset',
                        scope : this,
                        iconCls:'reset-icon',
                        handler: function(){
                                this.resetHandler();
                        }
                });
        
                this.buttonsPanel = new Ext.Panel({
                        width:'100%',
                        height:'100%',
                        buttonAlign:'right',
                        buttons:[
                                this.searchBtn,
                                this.resetBtn
                        ]
                });
                    this.dischargeFromDate = new Ext.form.WTCDateField({
                                fieldLabel: 'Date of discharge(from)',
                                name: 'dischargeDateFrom',
                                listeners:{
                                        blur: function( date ){
                                                if(!Ext.isEmpty(date.getValue())){
                                                mainThis.dischargeToDate.setMinValue(date.getValue());
                                                }
                                        }
                                },
                                anchor:'98%'
                        });
                        this.dischargeToDate = new Ext.form.WTCDateField({
                                fieldLabel: 'Date of discharge(to)',
                                name: 'dischargeDateTo',
                                listeners:{
                                        blur: function( date ){
                                                if(!Ext.isEmpty(date.getValue())){
                                                mainThis.dischargeFromDate.setMaxValue(date.getValue());
                                                }
                                        }
                                },
                                anchor:'98%'
                        });
                
                        this.admisssionFromDate = new Ext.form.WTCDateField({
                                fieldLabel: 'Date of admission(from)',
                                name: 'admissionDateFrom',
                                listeners:{
                                        blur: function( date ){
                                                if(!Ext.isEmpty(date.getValue())){
                                                mainThis.admissionToDate.setMinValue(date.getValue());
                                                }
                                        }
                                },
                                anchor:'98%'
                        });
                        this.admissionToDate = new Ext.form.WTCDateField({
                                fieldLabel: 'Date of admission(to)',
                                name: 'admissionDateTo',
                                listeners:{
                                        blur: function( date ){
                                                if(!Ext.isEmpty(date.getValue())){
                                                mainThis.admisssionFromDate.setMaxValue(date.getValue());
                                                }
                                        }
                                },
                                anchor:'98%'
                        });
                        this.doctorCbx = new Ext.form.OptComboBox({
                                fieldLabel:msg.doctor,
                                name:'doctorId',
                                mode:'local',
                                store: loadDoctorsCbx(),
                                triggerAction: 'all',
                                displayField:'description',
                                valueField:'code',
                        forceSelection : true,
                        anchor : '98%'
                    });
                    
                        this.manageAdmissionPanel = new Ext.form.FormPanel({
                                labelAlign : 'left',
                                width : '100%',
                                autoHeight : true,
                                border : false,
                                frame: true,
                                layout : 'column',
                                defaults: {
                                        border: false,
                                        columnWidth : .33,
                                        labelWidth:140
                                },
                                items: [
                                        {
                                                layout:'form',
                                                items:this.admisssionFromDate
                                        },
                                        {
                                                layout:'form',
                                                items:this.admissionToDate
                                        },
                                        {
                                                layout:'form',
                                                columnWidth : .33,
                                                labelWidth:100,
                                                items:this.doctorCbx
                                        },
                                        {
                                                layout:'form',
                                                items:this.dischargeFromDate
                                        },
                                        {
                                                layout:'form',
                                                items:this.dischargeToDate
                                        },
                                        {
                                                layout:'form',
                                                columnWidth : .33,
                                                labelWidth:100,
                                                items:[
                                                        {
                                                        fieldLabel: msg.patientname,
                                                        xtype: 'textfield',
                                                        name:'patientName',
                                                                anchor:'98%'
                                                    }   
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                items:[
                                                        {
                                                        fieldLabel: msg.patientid,
                                                        xtype: 'numberfield',
                                                        name:'patienId',
                                                        anchor:'98%'
                                                    }
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                items:[
                                                        {
                                                        fieldLabel: 'Admission number',
                                                        xtype: 'numberfield',
                                                        name:'pan',
                                                                anchor:'98%'
                                                    }
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                columnWidth : .33,
                                                labelWidth:100,
                                                items:[
                                                        {
                                                        fieldLabel: msg.admissionstatus,
                                                        xtype: 'optcombo',
                                                        name: 'admissionstatusCd',
                                                        store: loadAdmissionStatusCbx() ,
                                                                mode:'local',
                                                                displayField:'description',
                                                                valueField:'code',
                                                            triggerAction: 'all',
                                                            forceSelection:true,
                                                        anchor : '98%'
                                                    }
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                items:[
                                                        {
                                                        fieldLabel: 'Bed number',
                                                        xtype: 'textfield',
                                                        anchor:'98%',
                                                        name:'bedNumber'
                                                    }
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                items:[
                                                        {
                                                        fieldLabel: msg.unittype,
                                                        xtype: 'optcombo',
                                                        name: 'unitTypeCd',
                                                        store: loadNursingUnitTypes(),
                                                                mode:'local',
                                                                displayField:'description',
                                                                valueField:'code',
                                                            triggerAction: 'all',
                                                            forceSelection:true,
                                                        anchor : '98%'
                                                    }
                                                ]
                                        },
                                        {
                                                layout:'form',
                                                columnWidth : 1,
                                                items:this.buttonsPanel
                                        }
                                ]
                        });
        
                        this.mainPanel = new Ext.Panel({
                                width:'100%',
                                height:'100%',
                                items:[
                                        this.manageAdmissionPanel,
                                        this.infoGrid
                                ]
                        });
                        
                        this.infoGrid.on('render', function(){
                                this.search();
                        },this);
                        
                        this.mainPanel.on('destroy',function( comp ){
                                InstanceFactory.removeInstance( comp.windowCode );
                        },this);
                        
                        Ext.ux.event.Broadcast.subscribe('reloadAdmissionOrderGrid', function(){
                                if( this.infoGrid.getStore().data.length > 0 ){
                                        this.infoGrid.getStore().reload();
                                }else{
                                        this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null]});
                                }
                        },this, null , mainThis.getPanel());
        },
        getPanel : function() {
                return this.mainPanel;
        },
        search:function(){
                var valueMap = this.manageAdmissionPanel.getForm().getValues();
                if(this.manageAdmissionPanel.getForm().isValid()){
                        this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[
                                                                Ext.isEmpty(valueMap['pan'])?null:parseInt(valueMap['pan'],10),
                                                                Ext.isEmpty(valueMap['doctorId'])?null:parseInt(valueMap['doctorId'],10),
                                                                Ext.isEmpty(valueMap['patienId'])?null:parseInt(valueMap['patienId'],10),
                                                                valueMap['patientName'],valueMap['admissionstatusCd'],
                                                                ( Ext.isEmpty( valueMap['admissionDateFrom'] )|| valueMap['admissionDateFrom']=='undefined' )?null:this.admisssionFromDate.getValue(),
                                                                ( Ext.isEmpty( valueMap['admissionDateTo'] )|| valueMap['admissionDateTo']=='undefined' )?null:this.admissionToDate.getValue(),
                                                                null,null,null,null,null,
                                                                ( Ext.isEmpty( valueMap['dischargeDateFrom'] )|| valueMap['dischargeDateFrom']=='undefined' )?null:this.dischargeFromDate.getValue(),
                                                                ( Ext.isEmpty( valueMap['dischargeDateTo'] )|| valueMap['dischargeDateTo']=='undefined' )?null:this.dischargeToDate.getValue(),
                                                                valueMap['unitTypeCd'],valueMap['bedNumber']]});
                        this.dischargeFromDate.setMaxValue(null);
                        this.dischargeToDate.setMinValue(null);
                        this.admisssionFromDate.setMaxValue(null);
                        this.admissionToDate.setMinValue(null);
                }else{
                        Ext.Msg.show({
                                msg: 'Enter valid data..!',
                                icon : Ext.MessageBox.WARNING,
                                buttons: Ext.Msg.OK
                        });
                        return;
                }
                                
        },
        getFocus:function(){
                this.doctorCbx.focus();
        },
        confirmAdmissionHandler: function(){
                var selectedRows = this.infoGrid.getSelectionModel().getSelections();
                var admissionReqNbrList = new Array();
                if(selectedRows.length >0){
                        for(var i=0;i<selectedRows.length;i++){
                                var admissionNbr = selectedRows[i].data.admissionReqNbr;
                                if(!Ext.isEmpty(admissionNbr)){
                                        admissionReqNbrList.push(parseInt(admissionNbr,10));
                                }
                        }
                AdmissionOrder.confirmAdmissions(admissionReqNbrList,function(){
                                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
                });     
                }
        },
        resetHandler: function(){
                this.manageAdmissionPanel.getForm().reset();
                Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
        }
});