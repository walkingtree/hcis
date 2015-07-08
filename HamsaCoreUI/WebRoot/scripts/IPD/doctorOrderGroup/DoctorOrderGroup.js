Ext.namespace("IPD.doctorOrderGroup");

IPD.doctorOrderGroup.DoctorOrderGroup = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {rowValues : {}};
		}else{
			if(Ext.isEmpty(config.rowValues)){
				config.rowValues = {};
			}
		}
		Ext.apply(this, config);
		
		var mainThis = this;
		
		var mainFormThisObj = this;
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel:	msg.effectivefrom,
			name:'effectiveFromDt',
	        anchor : '95%',
	        listeners:{
	        	change: function( date, newValue,oldValue ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainFormThisObj.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel:	msg.effectiveto,
			name:'effectiveToDt',
	        anchor : '95%',
	        listeners:{
		   		change: function( date, newValue,oldValue ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainFormThisObj.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
		this.groupNameTxf = new Ext.form.TextField({
	        fieldLabel: msg.orderGroupName,
	        name: 'orderGroupName',
	        readOnly : (config.mode == 'edit')? true: false,
	        allowBlank : false,
	        required :true,
	        anchor : '95%',
	        allowBlank:false,
	        value: config.rowValues ? config.rowValues.orderGroupName : null,
	        listeners:{
	        	blur:function(comp){
	        		if( config.mode != 'edit'){
		        		if(!Ext.isEmpty(comp.getValue())){
		        			OrderManager.getDoctorOrderGroups(comp.getValue(),null,null,
							function(orderGroupList){
								if(orderGroupList != null && orderGroupList.length > 0){
									Ext.Msg.show({
					 					msg: msg.groupAlreadyExist,
									    buttons: Ext.Msg.OK,
									    icon: Ext.MessageBox.Error,
									    fn:function(btn){
									    	comp.setValue("");
									    	comp.focus();
									    }
				 					});
								}
							});
		        		}
	        		}
	        	}
	        }
		});
		this.basicDetailsFieldSet = new Ext.form.FieldSet({
			hidden:config.isViewTemplates,
			layout:'column',
			labelAlign:'left',
			height:'100%',
			border : true,
			defaults:{columnWidth:.5,labelWidth:80},
			items: [
				{
					layout:'form',
					border:false,
					items:this.groupNameTxf
				},
				{
					layout:'form',
					border:false,
					items:[
						{
					        xtype: 'textfield',
					        fieldLabel: msg.description,
					        allowBlank : false,
					        required :true,
					        name: 'orderGroupDesc',
					        anchor : '95%',
					        value: config.rowValues ? config.rowValues.orderGroupDesc : null
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'combo',
							fieldLabel:	msg.doctor,
							name:'groupForDoctorId',
							mode:'local',
							store: loadDoctorsCbx(),
							triggerAction: 'all',
							displayField:'description',
					        anchor : '95%',
					        valueField:'code',
					        forceSelection : true,
					        resizable:true,
					        value: config.rowValues ? config.rowValues.groupForDoctorId : null
						}
					]
		   		}
			]
		});
		
		loadDoctorsCbx().on('load',function( thisStore){
			if( !Ext.isEmpty( this.basicDetailsFieldSet.items.items[2].items.items[0] )){
				this.basicDetailsFieldSet.items.items[2].items.items[0].setValue( config.rowValues.groupForDoctorId );
			}
 		},this);
		
		this.orderTemplateDescTxf = new Ext.form.TextField({
			fieldLabel: msg.templatedesc,
	        name: 'orderTemplateDesc',
	        readOnly:true,
	        anchor:'95%'
		});
		
		this.orderTemplateCbx = new Ext.form.ComboBox({
			fieldLabel:	msg.orderTemplate,
			name:'orderTemplateName',
			mode:'local',
			store: loadDoctorOrderTemplate(),
			triggerAction: 'all',
			displayField:'code',
	        anchor : '95%',
			valueField:'code',
	        forceSelection : true,
	        resizable:true,
	        listeners:{
	        	select:function(comp,record,index){
	        		var template = comp.getValue();
	        		if(!Ext.isEmpty(template)){
	        			OrderManager.getOrderTemplateForId(comp.getValue(),
						function(orderTemplateBm){
							if(orderTemplateBm != null ){
								mainFormThisObj.orderTemplateDescTxf.
											setValue(orderTemplateBm.templateDesc);
							}
						});
	        		}
	        	}
	        }
		});
		
		//*********** Basic details *******************************************//
		this.gridInputDetailsPanel = new Ext.form.FieldSet({
			hidden:config.isViewTemplates,
			title : '',
			collapsible: false,
			labelAlign:'left',
			width : '97.5%',
			autoHeight: true,
			border : true,
			style: 'padding-top:10px',
			items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .5,
							labelWidth : 125
						},
						items: [
							{
								layout:'form',
								border:false,
								items:[
									{
										xtype: 'numberfield',
								        fieldLabel: msg.sequenceNumber,
								        name: 'sequenceNbr',
								        anchor : '95%'
									}
								]
							}, {
								layout:'form',
								border:false,
								items:this.orderTemplateCbx
							},{
								layout:'form',
								border:false,
								items:this.orderTemplateDescTxf
							}
					    ]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .5,
							labelWidth : 125
						},
						items: [
							{
								layout:'form',
								border:false,
								items:this.fromDate
						    }, {
								layout:'form',
								border:false,
								items:this.toDate
						    }, {
								layout:'form',
								border:false,
								items:[
									{
								        text: msg.add,
								        xtype: 'button',
								    	iconCls : 'add_btn',
								    	scope: this,
								    	handler: function() {
								    		mainFormThisObj.addBtnClicked();
								    	}
									}
								]
					    	}
					    ]
					}
				]
		});
		
		//*********** Grid Panel *******************************************//
		this.doctorOrderGroupRecord = Ext.data.Record.create([
											{ name: 'sequenceNbr', mapping:'sequenceNbr' },
											{ name: 'orderTemplateName', mapping:'orderTemplateName' },
											{ name: 'orderTemplateDesc', mapping:'orderTemplateDesc' },
									        { name: 'effectiveFromDt',  mapping:'effectiveFromDt'},
									        { name: 'effectiveToDt', mapping:'effectiveToDt'}
								       ]);
		
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.doctorOrderGroupRecord)
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
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			disabled:true,
			scope:this,
			handler : function() {
				this.editBtnClicked();
				this.editBtn.disable();
				this.deleteBtn.disable();
			}
		});
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope:this,
			handler : function() {
				this.deleteBtnClicked();
				this.deleteBtn.disable();
				this.editBtn.disable();
			}
		});
		
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				height : 200,
				width : '100%',
				autoScroll : true,
				border : false,
				store : this.dataStore,
				sm:this.gridChk,
				viewConfig:{forceFit:true},
				listeners:{
					 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
							var selectedRecord = thisGrid.getStore().getAt(rowIndex).data;
							if( thisGrid.getSelectionModel().getSelections().length == 1 ){
								mainThis.editBtn.enable();
							}else{
								mainThis.editBtn.disable();
							}
					}
				},
				tbar : new Ext.Toolbar({
					items:[this.editBtn, '-', this.deleteBtn]
				}),
				columns : [
						this.gridChk, {
							header : 'S.No.',
							dataIndex : 'sequenceNbr',
							width : 50,
							sortable: true
						}, {
							header : 'Order template name',
							dataIndex : 'orderTemplateName',
							width : 200,
							sortable: true
						}, {
							header : 'Order template description',
							dataIndex : 'orderTemplateDesc',
							width : 200,
							sortable: true
						}, {
							header : 'Effective from',
							dataIndex : 'effectiveFromDt',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 200,
							sortable: true
						}, {
							header : 'Effective to',
							dataIndex : 'effectiveToDt',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							width : 200,
							sortable: true
						}]
			});

		//*********** Save & Cancel buttons panel ****************************//
		this.buttonPanel = new Ext.form.FieldSet({
			hidden:config.isViewTemplates,
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:msg.btn_save,
				iconCls : 'save_btn',
				handler: function(){
					mainFormThisObj.saveBtnClicked(config);
				}
			},{
				xtype:'button',
				text:msg.reset,
				iconCls : 'cancel_btn',
				handler: function(){
					mainFormThisObj.resetBtnClicked(config);
				}
			}]
		});	


		//*********** Whole panel *******************************************//
		this.doctorOrderGroupPanel = new Ext.form.FormPanel({
			frame : true,
			width : '100%',
			autoHeight : true,
			border : false,
			monitorValid: true,
			items: [
				this.basicDetailsFieldSet,
				this.gridInputDetailsPanel,
				this.infoGrid,
				this.buttonPanel
			]
		});
		// for monitoring the personal details panel 
		this.doctorOrderGroupPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
			this.buttonPanel.buttons[0].enable();
			} else {
			this.buttonPanel.buttons[0].disable();
			}
		}, this);
		
		if(config.isViewTemplates){
			this.infoGrid.getColumnModel().setHidden( 0, true );
			this.infoGrid.getTopToolbar().hide();
		}
	},
	
	//*********** Button listeners *******************************************//
	addBtnClicked : function() {
	   var record = this.dataStore.recordType;
	   var values = this.doctorOrderGroupPanel.getForm().getValues();
	   
   	   if(values['sequenceNbr'] == ''){
		  warning("Sequence number is required field..!");
		  return;
	   }
		    
		   //if(values['orderTemplateName'] == ''){
			  ///warning("Order template name is required field..!");
			  //return;
		   //}

	   var orderGroupRecord = new record({
			sequenceNbr : values['sequenceNbr'],
			orderTemplateName : values['orderTemplateName'],
			orderTemplateDesc : values['orderTemplateDesc'],
			effectiveFromDt : Ext.isEmpty(values['effectiveFromDt'])? '':this.fromDate.getValue(),
			effectiveToDt : Ext.isEmpty(values['effectiveToDt'])? '':this.toDate.getValue()
		});
		this.infoGrid.stopEditing();
		 
		 var tableRows = this.infoGrid.getStore().getRange();
		 for( var i = 0; i<tableRows.length; i++ ){
		 	var currRow = tableRows[i].data;
		 	var sequenceNumber = currRow.sequenceNbr;
		 	
		 	if( values['sequenceNbr'] == sequenceNumber ){
		 			alertError( "This sequence no. already exists ");
		 			return;
		 	}
		 }
			 
		var insertAt = this.infoGrid.getStore().data.items.length;		 
		this.infoGrid.getStore().insert(insertAt, orderGroupRecord);
		
		this.resetBasicFieldSet();
		this.fromDate.setMaxValue(null);
		this.toDate.setMinValue(null);
	},

	editBtnClicked : function() {
		this.resetBasicFieldSet();
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.returnSelectedDataRecord();
			this.deleteBtnClicked();
			this.doctorOrderGroupPanel.getForm().setValues(slctdRecordToEdit);
			
  		} else {
   			Ext.Msg.show({
			   msg: 'Please select atleast & atmost one row to edit..!',
			   buttons: Ext.Msg.OK,
			   icon: Ext.MessageBox.INFO
            })
  		}
	},
	
	deleteBtnClicked : function() {
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
	    if(!Ext.isEmpty(slctdRows) && slctdRows.length>0) {
	    	for(var i = 0; i<slctdRows.length; i++) {
	    		this.infoGrid.stopEditing();
				this.infoGrid.getStore().remove(slctdRows[i]);
	     	}
	    }
	},
	
	saveBtnClicked : function(config) {
	
		if(this.doctorOrderGroupPanel.getForm().isValid()){
			
			var tableRows = this.infoGrid.getStore().data.items;
			var orderGroupTemplatesList = new Array();
			var basicDetails = this.doctorOrderGroupPanel.getForm().getValues();
			var mainThis = this;
			
			for( var i = 0; i<tableRows.length; i++ ) {
				var currRow = tableRows[i].data;
				var orderGroupTemplateAssociationBM = {
					sequenceNbr : currRow.sequenceNbr,
					orderTemplateName : currRow.orderTemplateName,
					orderTemplateDesc : currRow.orderTemplateDesc,
					effectiveFromDt : Ext.isEmpty(currRow.effectiveFromDt)?null:currRow.effectiveFromDt,
					effectiveToDt : Ext.isEmpty(currRow.effectiveToDt)?null:currRow.effectiveToDt,
					orderGroupName: basicDetails.orderGroupName
				}
				orderGroupTemplatesList.push(orderGroupTemplateAssociationBM);
			}

			var doctorOrderGroupBM = {
				orderGroupName : basicDetails.orderGroupName,
				orderGroupDesc :  basicDetails.orderGroupDesc,
				groupForDoctorId : (!Ext.isEmpty( basicDetails.groupForDoctorId ) && basicDetails.groupForDoctorId != 'undefined' )?
										parseInt(basicDetails.groupForDoctorId,10) : null,
				orderGroupTemplateAssociationList : orderGroupTemplatesList
			};
			
			if(config.mode == "edit"){
				OrderManager.modifyOrderGroup(doctorOrderGroupBM,function(){
			    	mainThis.resetBtnClicked();
			    	mainThis.fromDate.setMinValue(null);
			    	mainThis.toDate.setMaxValue(null);
			    	if(!Ext.isEmpty(config.title)){
			    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
						Ext.ux.event.Broadcast.publish('closeDoctorOrderGroupWindow');
					}
					doctorOrderGroupStore.reload();
				});
			}else{
				OrderManager.createOrderGroup(doctorOrderGroupBM,{
					callback: function(){
				    	mainThis.resetBtnClicked();
				    	mainThis.fromDate.setMinValue(null);
				    	mainThis.toDate.setMaxValue(null);
				    	if(!Ext.isEmpty(config.title)){
				    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
							Ext.ux.event.Broadcast.publish('closeDoctorOrderGroupWindow');
						}
						doctorOrderGroupStore.reload();
					}}
			    );
			}
			
		} else {
			warning("Please enter all the required fields and try again!");
		  	return;
		}
	},

	resetBtnClicked : function(config) {
		this.doctorOrderGroupPanel.getForm().reset();
		this.infoGrid.getStore().removeAll();
		if( !Ext.isEmpty(config) && config.mode == configs.edit){
			this.loadData(config);
		}
	},
	
	returnSelectedDataRecord: function(){
    	var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	return slctdRows[0].data;
  		} else {
   			Ext.Msg.show({
			   msg: 'Please select atleast & atmost one row to edit..!',
			   buttons: Ext.Msg.YESNO,
			   icon: Ext.MessageBox.INFO
            })
  		}
    },

	resetBasicFieldSet : function() {
		var formPanel = this.doctorOrderGroupPanel;
		var obj ={
					sequenceNbr:'',
					orderTemplateName: '',
					effectiveFromDt: '',
					effectiveToDt: '',
					orderTemplateDesc:''
				 };
		formPanel.getForm().setValues(obj); 
	},
	
	getPanel : function() {
		return this.doctorOrderGroupPanel;
	},
	loadData:function(config){
		this.doctorOrderGroupPanel.getForm().setValues(config.rowValues);
		
    	if(config.mode =='edit'){
    		var gridRows = config.rowValues.orderGroupTemplateAssociationList;
			var record = this.infoGrid.getStore().recordType;
			if(gridRows!=null && gridRows.length>0 ){
				for(var i = 0; i<gridRows.length; i++){
					var doctorOrderGroupRecord = new record({
						sequenceNbr:gridRows[i].sequenceNbr,
						orderTemplateName:gridRows[i].orderTemplateName,
						orderTemplateDesc:gridRows[i].orderTemplateDesc,
						effectiveFromDt:gridRows[i].effectiveFromDt,
						effectiveToDt:gridRows[i].effectiveToDt
					});
					 this.infoGrid.getStore().add(doctorOrderGroupRecord);
				}
			}
		}
	},
	getFocus : function(){
		this.groupNameTxf.focus();
	}
});