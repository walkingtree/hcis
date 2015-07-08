Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrder = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}else{
			if(Ext.isEmpty(config.values)){
				config.values = {};
			}
		}
		this.tmpEditPanel;
		this.editPanel;
		this.editWindow;
		this.isEditMode;
		var mainFormThis = this;
		this.isDoctorOrder = true;
		
		// ******************** BASIC DETAILS ********************************//
		this.doctorOrderBasicDetailsPanel = new IPD.addOrder.DoctorOrderBasicDetailsPanel(config);
		this.doctorOrderBasicDetailsPnl = this.doctorOrderBasicDetailsPanel.getPanel();
		
		// ******************* SERVICES SELECTION ****************************//
		this.selectionGroup = new Ext.form.RadioGroup({
			name:'svcSlctType',
			columns: [.35, .3, .22],
       		items: [
                    {boxLabel: "Individual selection", name: 'svcSlctTypeCd', inputValue: 1,checked: true},
                    {boxLabel: "Order template", name: 'svcSlctTypeCd', inputValue: 2},
                    {boxLabel: "Order group", name: 'svcSlctTypeCd', inputValue: 3}
       			   ],
      		listeners:{
      			change : {
      				fn : function(radioGroup, value){
          				if(value === '1'){
							mainFormThis.individualSelectionPanel.show();
							mainFormThis.orderDescTxrPnl.show();
							
							//2 START
							if(!Ext.isEmpty(mainFormThis.orderTemplateCardPanel.items)){
		        				mainFormThis.orderTemplateCardPanel.removeAll();
		        			}
							mainFormThis.orderTemplateComboPanel.hide();
							mainFormThis.orderTemplateCombo.clearValue();
							mainFormThis.orderGroupCombo.clearValue();
							//2 END
							
							//3 START
							if(mainFormThis.orderGroupSelectionPanel.items){
								mainFormThis.orderGroupSelectionPanel.removeAll();
							}
          					mainFormThis.orderGroupComboPanel.hide();
          					//3 END
          					
          				} else if(value === '2'){
							
							mainFormThis.orderTemplateComboPanel.show()
							mainFormThis.orderDescTxrPnl.show();
							mainFormThis.orderTemplateComboPanel.doLayout();
							
							//1 START
							if(!Ext.isEmpty(mainFormThis.specificPanelForOrderType.items)){
			        			mainFormThis.specificPanelForOrderType.removeAll();
			        		}
			        		mainFormThis.individualSelectionPanel.hide()
			        		mainFormThis.doctorOrderDetailspnl.resetPanel();
							mainFormThis.orderTypeCombo.clearValue();
			        		//1 END
							
							//3 START
							if(mainFormThis.orderGroupSelectionPanel.items){
								mainFormThis.orderGroupSelectionPanel.removeAll();
							}
							mainFormThis.orderGroupComboPanel.hide()
							mainFormThis.orderGroupCombo.clearValue();
							//3 END
          				
          				} else if(value === '3'){
							mainFormThis.orderGroupComboPanel.show()
							mainFormThis.orderGroupComboPanel.doLayout();
							
							mainFormThis.orderDescTxrPnl.hide();
							if(!Ext.isEmpty(this.orderGroupTabPanel)){
								mainFormThis.orderGroupTabPanel = '';
							}
							
							//1 START
							mainFormThis.individualSelectionPanel.hide()
							if(!Ext.isEmpty(mainFormThis.specificPanelForOrderType.items)){
			        			mainFormThis.specificPanelForOrderType.removeAll();
			        		}
			        		mainFormThis.doctorOrderDetailspnl.resetPanel();
							mainFormThis.orderTypeCombo.clearValue();
			        		//1 END
							
							//2 START
							if(!Ext.isEmpty(mainFormThis.orderTemplateCardPanel.items)){
		        				mainFormThis.orderTemplateCardPanel.removeAll();
		        			}
							mainFormThis.orderTemplateComboPanel.hide()
							mainFormThis.orderTemplateCombo.clearValue();
							//2 END
          				}
      				}
      			}
      		}
	 	});
		this.servicesSelectionFieldSet = new Ext.form.FieldSet({
			hidden:config.hideServiceSelectionFieldSet,
			title : 'Select order By',
			collapsible : false,
			labelAlign :'left',
			autoHeight : true,
			border : true,
			width : '60%',
			items : [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : 1
						},
						items: this.selectionGroup
					}
				]
		});
		
		//****************specific code starts*******************************************
		
		this.doctorOrderDetailspnl = new IPD.addOrder.DoctorOrderDetailsPanel( config );
		//*****************specific code ends********************************************
		
		this.orderGroupSelectionPanel = new Ext.Panel({
			frame:false,
			border:false,
			layout:'fit'
		});
		
		this.orderGroupCombo = new Ext.form.ComboBox({
			fieldLabel:	'Order groups',
			labelWidth : 130,
			hiddenName:'doctorGroup',
			mode:'local',
			store: loadDoctorOrderGroup(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '35%',
	        forceSelection : true,
	        listeners:{
	        	select:function(combo,record,index){
	        		var orderGroupName = record.data.code;
	        		OrderManager.getOrderTemplatesForGroup(orderGroupName,function(list){
						OrderManager.getOrderTypeAttributeBMForGroup(orderGroupName,function(attributeList){
		        			var config ={
	        					data : list,
	        					attrList : attributeList,
	        					mainFormThis : mainFormThis
		        			}
		        			if (!Ext.isEmpty(mainFormThis.orderGroupSelectionPanel.items)) {
								mainFormThis.orderGroupSelectionPanel.removeAll();
							}
		        			mainFormThis.orderGroupTabPanel = new IPD.addOrder.DoctorOrderTabPanel(config);
		        			var orderGroupTabPnl = mainFormThis.orderGroupTabPanel.getPanel();
		        			if(orderGroupTabPnl.items.length>0){
		        				mainFormThis.orderGroupSelectionPanel.add(orderGroupTabPnl); 
		        				mainFormThis.orderGroupSelectionPanel.doLayout();
		        			}
		        			mainFormThis.orderGroupComboPanel.add(mainFormThis.orderGroupSelectionPanel);
		        			mainFormThis.orderGroupComboPanel.doLayout();
						});
	        		});
	        	}
	        }
		});
		
		this.orderGroupComboPanel = new Ext.Panel({
			hidden:true,
			layout:'form',
			items:this.orderGroupCombo
		});
		
		this.orderTemplateCombo = new Ext.form.ComboBox({
			fieldLabel:	'Order template',
			labelWidth : 130,
			hiddenName:'doctorOrderTemplate',
			mode:'local',
			store: loadDoctorOrderTemplate(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '35%',
	        forceSelection : true,
	        listeners:{
	        	select:function(combo,record,index){
	        		OrderManager.getOrderTemplateForId(record.data.code,function(orderTemplate){
	        			Ext.apply(mainFormThis,orderTemplate);
	        			var config ={
	        						mode:'edit',
	        						values:{doctorOrderDetailsList:orderTemplate.orderTemplateDetailsList}
	        			}
	        			if(!Ext.isEmpty(mainFormThis.orderTemplateCardPanel.items)){
	        				mainFormThis.orderTemplateCardPanel.removeAll();
	        			}
	        			
						OrderManager.getOrderTypeHasAttribute(orderTemplate.doctorOrderType.code, 0 , 999 , 'ASC' ,function(list){
							if(list.totalSize > 0){	
		        				mainFormThis.orderTemplateSpecificPanel = new IPD.addOrder.SpecificOrderPanel({data:list.data});
								mainFormThis.specificPanelForOrderTemplate = mainFormThis.orderTemplateSpecificPanel.getPanel();
								mainFormThis.specificPanelForOrderTemplate.show();
								mainFormThis.orderTemplateCardPanel.add(mainFormThis.specificPanelForOrderTemplate);
								mainFormThis.orderTemplateCardPanel.doLayout();
								mainFormThis.specificPanelForOrderTemplate.getForm().setValues({admissionStatus:"REQUESTED"});
							}	
						});
	        			
	        			mainFormThis.doctorOrderDetailsForTemplatepnl = new IPD.addOrder.DoctorOrderDetailsPanel();
	        			mainFormThis.orderTemplateCardPanel.add(mainFormThis.doctorOrderDetailsForTemplatepnl.getPanel());
	        			mainFormThis.doctorOrderDetailsForTemplatepnl.doctorOrderDetailsServicesGrid.loadData(config);
	        			
        				mainFormThis.orderTemplateComboPanel.doLayout();
        				
	        		})
	        		
	        	}
	        }
		});
		
		this.orderTemplateCardPanel = new Ext.Panel({
			width:'100%'			
		});
		this.orderTemplateComboPanel = new Ext.Panel({
			layout:'form',
			hidden:true,
			items:[
				this.orderTemplateCombo,
				this.orderTemplateCardPanel
			]
		});
		this.orderTemplateComboPanel.doLayout();
		
		this.orderTypeCombo = new Ext.form.ComboBox({
			fieldLabel:	'Order type',
			hiddenName:'doctorOrderType',
			mode:'local',
			store: loadDoctorOrderType(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '35%',
	        allowBlank:false,
	        required:true,
	        forceSelection : true,
	        listeners:{
	        	select:function( combo,  record, index){

	        		if(!Ext.isEmpty(mainFormThis.specificPanelForOrderType.items)){
	        			mainFormThis.specificPanelForOrderType.removeAll();
	        		}
						OrderManager.getOrderTypeHasAttribute(record.data.code, 0 , 999 , configs.orderBy ,function(list){	
		        			if(list.totalSize > 0){
			        			mainFormThis.specificPanel = new IPD.addOrder.SpecificOrderPanel({data:list.data});	
								mainFormThis.orderTypeSpecificPanel = mainFormThis.specificPanel.getPanel();	
								mainFormThis.specificPanelForOrderType.add(mainFormThis.orderTypeSpecificPanel);	
			        			mainFormThis.orderTypeSpecificPanel.show();	
			        			mainFormThis.orderTypeComboPanel.doLayout();
			        			mainFormThis.orderTypeSpecificPanel.getForm().setValues({admissionStatus:"REQUESTED"});
		        			}
		        			
						}
					);
	        	}
	        }
		});
		
		this.specificPanelForOrderType = new Ext.Panel({
			layout:'form',
			width : '100%',
			border: false
		});
		
		this.orderTypeComboPanel = new Ext.Panel({
			layout:'form',
			border:false,
			items:[this.orderTypeCombo,this.specificPanelForOrderType]
	    });
	  /*  if(!Ext.isEmpty(config.values) && config.mode == "edit"){
		if(config.values.orderTypeCd == configs.orderStatus_Admission){
			mainFormThis.admission = new IPD.addOrder.AdmissionOrderPanel({data:attributeStore.data.items});
						mainFormThis.admissionPanel = mainFormThis.admission.getPanel();
						mainFormThis.specificPanelForOrderType.add(mainFormThis.admissionPanel);
	        			mainFormThis.admissionPanel.show();
	        			mainFormThis.orderTypeComboPanel.doLayout();
	        			mainFormThis.admissionPanel.getForm().setValues(config.values.orderSpecificAttributes);
		}
	    }*/
		
		this.individualSelectionPanel = new Ext.Panel({
			layout:'form',
			border:false,
			items:[this.orderTypeComboPanel,this.doctorOrderDetailspnl.getPanel()]
	    });
		this.servicesSelectionPanel = new Ext.Panel({
			layout:'form',
			frame : true,
			width : '100%',
			autoHeight : true,
			border : false,
			items: [
				this.servicesSelectionFieldSet,
				this.individualSelectionPanel,
				this.orderTemplateComboPanel,
				this.orderGroupComboPanel
			]
		});
		
		// *********** OTHER DETAILS PANEL *************//
		this.orderDescTxr = new Ext.form.TextArea({
	        fieldLabel: 'Order description',
	        name: 'orderDesc',
	        xtype: 'textarea',
	        anchor : '98%',
	        value: config.values ? config.values.orderDesc : null
		});
		this.orderDescTxrPnl = new Ext.Panel({
			layout:'form',
			width:'100%',
			items:this.orderDescTxr
		});
	
		this.receivedFromCbx = new Ext.form.ComboBox(
			{
				fieldLabel:	'Received from',
				hiddenName:'doctorId',
				mode:'local',
				store: loadDoctorsCbx(),
				triggerAction: 'all',
				displayField:'description',
				valueField:'code',
		        anchor : '95%',
		        disabled : config.mode == 'edit' ? true : false,
		        forceSelection : true,
		        allowBlank:false,
		        required:true,
		        value: (!Ext.isEmpty(config.values)) ? config.values.doctorId : null
			}
		);
		
		this.otherDetailsPanel = new Ext.form.FormPanel({
			collapsible: false,
			labelAlign:'left',
			width : '95%',
			autoHeight: true,
			border : false,
			style: 'padding-top:5px;',
			items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .5,
							labelWidth : 130
						},
						items: [
							{
								layout:'form',
								border:false,
								items:[
									{
								        fieldLabel: 'Order dictation',
								        name: 'orderDictation',
								        xtype: 'textfield',
								        anchor : '95%',
								        value: config.values ? config.values.orderDictation : null
									}
								]
							},{
								layout:'form',
								border:false,
								items:[
									this.receivedFromCbx
								]
							}
					    ]
					},{
						layout : 'column',
						defaults: {
							border: false,
							columnWidth : 1,
							labelWidth : 130
						},
						items: [
							{
								layout:'form',
								border:false,
								items:this.orderDescTxrPnl
						    }
					    ]
					}
				]
		});
		
		this.dbActionBtnsFieldSet = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				scope:this,
				handler : function() {
					this.saveBtnClicked(config);
				}
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler : function() {
					var thisObj = this;
					Ext.Msg.show({
					   msg: msg.resetmsg,
					   buttons: Ext.Msg.YESNO,
					   fn: function(btn){
					   	if(btn == "yes"){
					   		thisObj.resetBtnClicked( config );
					   	}
					    },
					   icon: Ext.MessageBox.QUESTION
					});
					
				}
			}]
		});
		// *********** WHOLE PANEL *********************//
		this.doctorOrderPanel = new Ext.Panel({
			layout:'form',
			frame : true,
			width : '100%',
			autoHeight : true,
			buttonAlign:'right',
			border : false,
			items: [
				this.doctorOrderBasicDetailsPnl,
				this.servicesSelectionPanel,
				this.otherDetailsPanel,
				this.dbActionBtnsFieldSet
			]
		});
		
		this.orderTypeCombo.on('blur',function(comp){
			if(Ext.isEmpty(comp.getValue()) && !Ext.isEmpty(this.specificPanelForOrderType)){
				this.specificPanelForOrderType.removeAll();
			}			
		},this);
		
		if( config.values.statusCd == configs.orderStatusApproved && config.mode == 'edit'){
			this.dbActionBtnsFieldSet.hide();
			this.doctorOrderDetailspnl.doctorOrderDetailsServicesPanel.hidePanel();
		}
		
//		this.doctorOrderBasicDetailsPanel.getSearchPatientBtn().on('click',function(){
//			getSearchPatientPanel(this.otherDetailsPanel , this ).show();
//		},this);
		
		
		Ext.ux.event.Broadcast.subscribe('setPatientNameInDoctorOrderWindow',function(patientInfo ){
			mainFormThis.setPatientNameAndAdmissionNbr();
	
		},this, null, mainFormThis.getPanel() );	
			
		if( config.isFromDirectLink ){
			this.doctorOrderPanel.on('render',function(thisPanel){
				var indexNbr = this.orderTypeCombo.getStore().find('code','IP_ADMISSION');
				var record = this.orderTypeCombo.getStore().getAt( indexNbr );
				this.orderTypeCombo.fireEvent('select',this.orderTypeCombo , record , indexNbr );
				this.orderTypeCombo.setValue( 'IP_ADMISSION' );
				this.orderTypeCombo.disable();
			},this);
		}
		
		if( config.isFromDischargeLink ){
			this.doctorOrderPanel.on('render',function(thisPanel){
				var indexNbr = this.orderTypeCombo.getStore().find('code','DISCHARGE');
				var record = this.orderTypeCombo.getStore().getAt( indexNbr );
				this.orderTypeCombo.fireEvent('select',this.orderTypeCombo , record , indexNbr );
				this.orderTypeCombo.setValue( 'DISCHARGE' );
				this.orderTypeCombo.disable();
			},this);
		}
		
	},
	saveBtnClicked : function(config) {
		var mainThis = this;
		var otherdetails = this.otherDetailsPanel.getForm().getValues();
		if(config.mode == configs.edit || config.source == configs.appointment){
			otherdetails.doctorId = this.receivedFromCbx.getValue();
		}
		if( this.doctorOrderBasicDetailsPanel.patientCombo.isValid(false)&&
			this.otherDetailsPanel.getForm().isValid()){
			if(config.source == configs.appointment){
						config.mode = configs.add;
					}
			if( config.mode == configs.edit || config.source == configs.appointment ||  this.selectionGroup.getValue() == 1 ){
				if(this.orderTypeCombo.isValid(false)){
					var doctorOrderDetailsList =null;
					if(!Ext.isEmpty(this.doctorOrderDetailspnl)){
						doctorOrderDetailsList = this.doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.getData();
					}
					var doctorOrderBM = this.doctorOrderBasicDetailsPanel.getData();
						if(config.mode != configs.edit && config.source != configs.appointment){
							if(!Ext.isEmpty(this.specificPanel)){
								doctorOrderBM.orderSpecificAttributes = this.specificPanel.getData();
								if(doctorOrderBM.orderSpecificAttributes == null){
									return;
								}
							}	
						}
						else{
							if(!Ext.isEmpty(config.specificPnl.attributepanel)){
								doctorOrderBM.orderSpecificAttributes = config.specificPnl.attributepanel.getData();
//								loadAdmReqNoCbx.load({params:{start:0, limit:10}, arg:[]});
								if(doctorOrderBM.orderSpecificAttributes == null){
									return;
								}
							}
						}
					doctorOrderBM.doctorId = Ext.isEmpty(this.receivedFromCbx.getValue())?null:parseInt(this.receivedFromCbx.getValue());
					doctorOrderBM.doctorOrderType={code:this.orderTypeCombo.getValue()};
					doctorOrderBM.orderDesc=otherdetails.orderDesc;
					doctorOrderBM.orderDictation=otherdetails.orderDictation;
					doctorOrderBM.createdBy = authorisedUser.userName;
					doctorOrderBM.modifiedBy = authorisedUser.userName;
					doctorOrderBM.doctorOrderDetailsList = doctorOrderDetailsList;
					doctorOrderBM.doctorOrderNbr = config.values.doctorOrderNbr;

					var doctorOrderBMList = new Array();
					doctorOrderBMList.push(doctorOrderBM);
					
					if(config.mode == configs.edit){
						OrderManager.modifyDoctorOrder(doctorOrderBM,{
							callback: function(){
						    	if(!Ext.isEmpty(config.title)){
						    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
									Ext.ux.event.Broadcast.publish('closeDoctorOrderAddWindow');
								}
							}}
					    );
					}else{
						OrderManager.createDoctorOrder(doctorOrderBMList,{
							callback: function(){
								if( !Ext.isEmpty(config) && config.source == configs.appointment){
									layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
						    		Ext.ux.event.Broadcast.publish('closeDoctorOrderAddWindow');
						    		return;
								}
								mainThis.resetBtnClicked();
								
						    	if(!Ext.isEmpty(config.title)){
						    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
									Ext.ux.event.Broadcast.publish('closeDoctorOrderAddWindow');
								}
							}}
					    );
					}
				}
					
				}else if(this.selectionGroup.getValue() == "2"){
					if(!Ext.isEmpty(this.orderTemplateCombo.getValue())){
						var doctorOrderDetailsList =null;
						if(!Ext.isEmpty(this.doctorOrderDetailsForTemplatepnl)){
							doctorOrderDetailsList = this.doctorOrderDetailsForTemplatepnl.doctorOrderDetailsServicesGrid.getData();
						}
						
						var doctorOrderBM = this.doctorOrderBasicDetailsPanel.getData();
						if(!Ext.isEmpty(this.orderTemplateSpecificPanel)){
							doctorOrderBM.orderSpecificAttributes = this.orderTemplateSpecificPanel.getData();
							if(doctorOrderBM.orderSpecificAttributes == null){
								return;
							}
						}	
					
						doctorOrderBM.doctorId = Ext.isEmpty(this.receivedFromCbx.getValue())?null:parseInt(this.receivedFromCbx.getValue());
						doctorOrderBM.doctorOrderType={code:this.doctorOrderType.code};
						doctorOrderBM.orderDesc=otherdetails.orderDesc;
						doctorOrderBM.orderDictation=otherdetails.orderDictation;
						doctorOrderBM.createdBy = authorisedUser.userName;
						doctorOrderBM.modifiedBy = authorisedUser.userName;
						doctorOrderBM.doctorOrderDetailsList = doctorOrderDetailsList;
						doctorOrderBM.doctorOrderNbr = config.values.doctorOrderNbr;
						
						var doctorOrderBMListForTemplate = new Array();
						doctorOrderBMListForTemplate.push(doctorOrderBM);
					
						OrderManager.createDoctorOrder(	doctorOrderBMListForTemplate,
							function(){
						    	mainThis.resetBtnClicked();
						    	if(!Ext.isEmpty(config.title)){
									Ext.ux.event.Broadcast.publish('closeDoctorOrderAddWindow');
								}
						});
					}else{
						alertError("Please select a template and try again!");
					}

				}else if(this.selectionGroup.getValue() == "3"){
					if(!Ext.isEmpty(this.orderGroupCombo.getValue())){
						var doctorOrderList = new Array();
						var basicDetails = this.doctorOrderBasicDetailsPanel.getData();
						if(!Ext.isEmpty(this.orderGroupTabPanel)){
							var doctorOrderBmList = mainThis.orderGroupTabPanel.getData();
							if(doctorOrderBmList == null){
								return;
							}
						}
						for(var i = 0; i<doctorOrderBmList.length; i++){
							var doctorOrderBm =  doctorOrderBmList[i];
							doctorOrderBm.doctorId = Ext.isEmpty(this.receivedFromCbx.getValue())?null:parseInt(this.receivedFromCbx.getValue());
							doctorOrderBm.orderDictation=otherdetails.orderDictation;
							doctorOrderBm.patientId = basicDetails.patientId;
							doctorOrderBm.admissionNbr = basicDetails.admissionNbr;
							
							doctorOrderList.push(doctorOrderBm);
						}
						OrderManager.createDoctorOrder(	
							doctorOrderList, function(){
						    	mainThis.resetBtnClicked();
						    	if(!Ext.isEmpty(config.title)){
						    		
									Ext.ux.event.Broadcast.publish('closeDoctorOrderAddWindow');
								}
							});
					}else{
						alertError("Please select a group and try again!");
					}
				}
			}else {
			warning("Please enter all the required fields and try again!");
		  	return;
		}
	},

	resetBtnClicked : function( config ) {
		if( Ext.isEmpty(config)){
			config = { };
		}
		if( config.mode == 'edit' && config.isFromOrderList){
//			this.doctorOrderBasicDetailsPanel.resetPanel( config );
			if( !Ext.isEmpty(config.specificPnl.attributepanel)){
				config.specificPnl.attributepanel.setData( config.values.orderSpecificAttributes );
			}
			this.doctorOrderDetailspnl.resetPanel();
			this.otherDetailsPanel.getForm().setValues( config.values );
			this.doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.loadData(config);		
			
		}
		else{
			this.doctorOrderBasicDetailsPanel.resetPanel();
			this.selectionGroup.setValue('1');
			this.orderGroupCombo.clearValue();
			this.orderTemplateCombo.clearValue();
			this.otherDetailsPanel.getForm().reset();

			if(!Ext.isEmpty(this.specificPanelForOrderType.items)){
				this.specificPanelForOrderType.removeAll();
			}
			
			/* this for specific to individual selection start*/
			this.doctorOrderDetailspnl.resetPanel();
			this.orderTypeCombo.clearValue();
			/* this for specific to individual selection end*/
		}
	},
	
	getPanel : function(config) {
		
		return this.doctorOrderPanel;
	},
	loadData:function(config){
		this.doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.loadData(config);
		this.orderTypeCombo.setValue( config.values.orderTypeCd);
		this.orderTypeCombo.disable();
	},
	showAddWindow:function(config,grid){
		var tmpPanel = new IPD.add.DoctorOrderTemplateServicesPanel(config);
		var mainThis = this;
		tmpPanel.addBtn.addListener('click',function(){
			this.addDetailsToGrid(grid);
		},this);
		this.addPanel = new Ext.form.FormPanel({
			layout:'form',
			frame : true,
			width : '100%',
			autoHeight : true,
			border : false,
			items: tmpPanel.getPanel()
		});
		
		this.addWindow = new Ext.Window({
			title:'Add doctor order details',
			frame:true,
			width:'90%',
			height:'100%',
			modal:true,
			resizable:false,
			layout:'form',
			closeAction:'hide',
			items:this.addPanel,
			listeners:{
				beforehide:function(comp){
					mainThis.addPanel.getForm().reset();
				}
			}
		});
		
		this.addWindow.show();
	},
	showEditWindow:function(grid,gridPanel){
		var slctdRows = grid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = gridPanel.returnSelectedDataRecord();

			this.tmpEditPanel = new wtc.hcis.ip.DoctorOrderTemplateServicesPanel(slctdRecordToEdit);
	     	this.tmpEditPanel.addBtn.addListener('click',function(){
	     		this.addDetailsToGrid(grid);
			},this);
			this.editPanel = new Ext.form.FormPanel({
				layout:'form',
				frame : true,
				width : '100%',
				autoHeight : true,
				border : false,
				items: this.tmpEditPanel.getPanel()
			});
			this.editWindow = new Ext.Window({
				title:'Edit doctor order details',
				frame:true,
				width:'90%',
				height:'100%',
				modal:true,
				resizable:false,
				layout:'form',
				
				items:this.editPanel
			});
			this.isEditMode = true;
			this.editWindow.show();
  		}
	},
	addDetailsToGrid:function(grid){
	   var record = grid.getStore().recordType;
	   var values;
	   if(!Ext.isEmpty(this.isEditMode) && this.isEditMode){
	   		values = this.editPanel.getForm().getValues();
	   		this.doctorOrderServicesGrid.deleteBtnClicked();
	   }else{
	   		values = this.addPanel.getForm().getValues();
	   }
	   
	   if(values['sequenceNumber'] == ''){
		  warning("Sequence number is required field..!");
		  return;
	   }
	    
	   if(values['subSequenceNumber'] == ''){
		  warning("Sub-sequence number is required field..!");
		  return;
	   }
	   
	   // Get the responsible service description given its code.
	   var slctdServiceDesc;
	   if(!Ext.isEmpty(ServiceNameStore)){
	   		for(var i=0; i<ServiceNameStore.data.items.length; i++){
		   		var currRec = ServiceNameStore.data.items[i];
		   		if(currRec.data.code == values['serviceCode']){
		   			slctdServiceDesc = currRec.data.description;
					break;		   			
		   		}
		   }
	   }

	   // Get the responsible department description given its code.
	   var slctdDeptDesc; 
	   if(!Ext.isEmpty(departmentsStore)){
	   		for(var i=0; i<departmentsStore.data.items.length; i++){
		   		var currRec = departmentsStore.data.items[i];
		   		if(currRec.data.code == values['responsibleDepartmentCode']){
		   			slctdDeptDesc = currRec.data.description;
					break;		   			
		   		}
		   }
	   }

	   var serviceRecord = new record({
			sequenceNumber: values['sequenceNumber'],
			subSequenceNumber: values['subSequenceNumber'],
			serviceCode: values['serviceCode'],
			serviceName: slctdServiceDesc,
			responsibleDepartmentCode: values['responsibleDepartmentCode'],
			responsibleDepartmentDescription: slctdDeptDesc,
			actionRequiredDescription: values['actionRequiredDescription']
		 });
		 grid.stopEditing();
		 
		 var insertAt = grid.getStore().data.items.length;		 
		 grid.getStore().insert(insertAt, serviceRecord);
		 
		 if(!Ext.isEmpty(this.isEditMode) && this.isEditMode){
		 	this.editPanel.getForm().reset();
			this.editWindow.close();
			this.isEditMode = "";
			return;
		 }
		 this.addPanel.getForm().reset();
		 this.addWindow.hide();
	},
	getBaseDoctorOrderPanel:function(){
		this.doctorOrderPanel = new Ext.Panel({
//			title:msg.doctororderdetails,
			width : '100%',
//			height:'100%',
			autoHeight : true,
			border : true,
			items: [
				this.otherDetailsPanel
			]
		});
		return this.doctorOrderPanel;
	},
	getFocus: function(){
		this.doctorOrderBasicDetailsPanel.patientCombo.focus();
	},
	getLayout : function(){
		this.doctorOrderPanel.doLayout();
	},
	setPatientNameAndAdmissionNbr : function(){
	  	patientStore.load({params:{start:0, limit:9999}, arg:[]});
		patientStore.on('load',function(){
			this.doctorOrderBasicDetailsPanel.getPatientCombo().setValue( patientInfo.patientId )
			IPDataModelManager.getActiveAdmissionOfPatient(patientInfo.patientId,
			function(admissionNbr){
				if(admissionNbr != null){
					this.doctorOrderBasicDetailsPanel.getAdmissionNbrField().setValue(admissionNbr);
					this.doctorOrderBasicDetailsPanel.getAdmissionNbrField().setDisabled(true);
				}else {
					this.doctorOrderBasicDetailsPanel.getAdmissionNbrField().setValue('');
					this.doctorOrderBasicDetailsPanel.getAdmissionNbrField().setDisabled(true);
				}
			});
		})
		
	},
	setDefaultValues:function( config ){
			if(!Ext.isEmpty(config) &&  !Ext.isEmpty( doctorOrderTypeStore ) && doctorOrderTypeStore.getTotalCount() > 0 ){
				this.orderTypeCombo.setValue( configs.orderStatus_Admission );
			}else{
				doctorOrderTypeStore.on('load', function(){
					if( !Ext.isEmpty(config)){
						this.orderTypeCombo.setValue( configs.orderStatus_Admission );
						config = '';
						doctorOrderTypeStore.events['load'].clearListeners();
					}
				},this)
			}
	
	}
});