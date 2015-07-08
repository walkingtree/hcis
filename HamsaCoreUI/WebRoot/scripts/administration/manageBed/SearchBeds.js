Ext.namespace("administration.manageBed");

administration.manageBed.SearchBeds = Ext.extend(Object, {
        constructor : function(config) {
                var mainThis = this;
                if( !Ext.isEmpty( config ) ){
                        this.infoGrid = new administration.manageBed.BedGrid({plugins:[],hideToolbar: config.hideToolbar});
                }else{
                        this.infoGrid = new administration.manageBed.BedGrid({plugins:[]});
                }
                
                this.bedFeaturesPanel = new administration.addBed.BedFeaturesPanel({data: bedFeaturesStore.data.items});
                
                this.dischargeFromDate = new Ext.form.WTCDateField({
                        fieldLabel: 'Date of discharge(from)',
                        name: 'dischargeDateFrom',
                        listeners:{
                                blur: function( date ){
                                        if(!Ext.isEmpty(date.getValue())){
                                        mainThis.dischargeToDate.setMinValue(date.getValue());
                                        }
                                }
                        }
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
                        }
                });
                this.searchBtn = new Ext.Button({
                text: 'Search',
                iconCls : 'search_btn',
                scope: this,
                handler: function() {
                        if(this.searchBedPanel.getForm().isValid()){
                                var callback = function(){
                                        this.dischargeToDate.setMinValue(null);
                                        this.dischargeFromDate.setMaxValue(null);
                                }
                                var obj=this.searchBedPanel.getForm().getValues();
                                if( !Ext.isEmpty( config ) && config.showAvailableBeds==true ){
                                                obj["bedStatus"]="AVAILABLE";
                                                this.infoGrid.search(obj,callback,this.bedFeaturesPanel.getStringData());
                                        }else if( !Ext.isEmpty( config ) ){
                                                this.infoGrid.search(obj, null,null);
                                        }else{
                                                this.infoGrid.search(obj, null,null);
                                        }
                                
                        }else{
                                Ext.Msg.show({
                                                msg: 'Enter valid data..! ',
                                                icon : Ext.MessageBox.ERROR,
                                                buttons: Ext.Msg.OK
                                        });
                        }
                        
                }
                });
                
                this.buttonPanel = new Ext.Panel({
                        buttonAlign:'right',
                        border:false,
                        autoHeight: true,
                        buttons:[this.searchBtn,{
                                xtype:'button',
                                text:'Reset',
                                iconCls : 'cancel_btn',
                                scope:this,
                                handler: function(){
                                        this.searchBedPanel.getForm().reset();
                                        if( !Ext.isEmpty( config ) && config.showAvailableBeds==true ){
                                                /*
                                                 * This condition is useful for displaying the available beds in Assign Window
                                                 */
                                                var obj=this.searchBedPanel.getForm().getValues();
                                                obj["bedStatus"]="AVAILABLE";
                                                this.infoGrid.search(obj, null,null);
                                        }else if( !Ext.isEmpty( config ) ){
                                                this.infoGrid.search({}, null,null);
                                        }
                                }
                        }]
                });                               

                this.searchBedPanel = new Ext.form.FormPanel({
                        layout : 'column',
                        width : '98%',
//                      style:'padding-top:5px',
                        autoHeight:true,
                        border : false,
                        defaults: {
                                border: false,
                                columnWidth : .32,
                                labelWidth:135,
                                defaults : {
                                        anchor : '98%'
                                }
                        },
                        items:[
                                {
                                        layout:'form',
                                        labelWidth:100,
                                        items:[
                                                {
                                                fieldLabel: 'Bed number',
                                                xtype: 'textfield',
                                                name:'bedNumber'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        items:[
                                                {
                                                fieldLabel: 'Room',
                                                xtype: 'optcombo',
                                                name: 'roomNbr',
                                                store: loadRoomName(),
                                                        mode:'local',
                                                        displayField:'description',
                                                        valueField:'code',
                                                    triggerAction: 'all'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        items:[
                                                {
                                                fieldLabel: 'Floor',
                                                xtype: 'textfield',
                                                name:'floor'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        labelWidth:100,
                                        items:[
                                                {
                                                fieldLabel: 'Unit',
                                                xtype: 'optcombo',
                                                name: 'nursingUnit',
                                                store: loadNursingUnits(),
                                                        mode:'local',
                                                        displayField:'description',
                                                        valueField:'code',
                                                    triggerAction: 'all',
                                                    forceSelaction: true
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        items:[
                                                {
                                                fieldLabel: 'Patient Id',
                                                xtype: 'textfield',
                                                name:'patientId'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        items:[
                                                {
                                                fieldLabel: 'Admission number',
                                                xtype: 'textfield',
                                                name:'panNumber'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        labelWidth:100,
                                        items:[
                                                {
                                                fieldLabel: 'Status',
                                                xtype: 'optcombo',
                                                store: loadBedStatus(),
                                                        mode:'local',
                                                        displayField:'description',
                                                        valueField:'code',
                                                    triggerAction: 'all',
                                                    name:'bedStatus',
                                                    forceSelection: true
                                            }
                                        ]
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
                                        labelWidth:100,
                                        items:[
                                                {
                                                fieldLabel: 'Patient name',
                                                xtype: 'textfield',
                                                name:'patientname'
                                            }
                                        ]
                                },
                                {
                                        layout:'form',
                                        columnWidth:1,
                                items:this.bedFeaturesPanel.getPanel()
                                },
                                {
                                        layout:'form',
                                        columnWidth:1,
                                        items:this.buttonPanel
                                },
                                {
                                columnWidth:1,
                                width: '100%',
                                items:this.infoGrid.getPanel()
                            }
                            
                        ]
                });
                
                this.searchBedPanel.on("render", function(){
                        
                        if( !Ext.isEmpty( config ) && config.showAvailableBeds==true ){
                                /*
                                 * This condition is useful for displaying the available beds in Assign Window
                                 */
                                var obj=this.searchBedPanel.getForm().getValues();
                                obj["bedStatus"]="AVAILABLE";
                                this.infoGrid.search(obj, null,null);
                        }else if( !Ext.isEmpty( config ) ){
                                
                                this.infoGrid.search(this.searchBedPanel.getForm().getValues(), null,null);
                        }else{
                                this.infoGrid.search({}, null,null);
                        }
                        
                }, this);
                
                this.searchBedPanel.on('destroy',function( comp ){
                        InstanceFactory.removeInstance( comp.windowCode );
                },this);
        
        },
        getPanel : function() {
                return this.searchBedPanel;
        }
});