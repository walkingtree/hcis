Ext.namespace("administration.insurerPlan");

administration.insurerPlan.InsurancePlan = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		Ext.apply(this, config);
		var mainThis = this;

	   this.fromDate = new Ext.form.WTCDateField({
			fieldLabel:	'Valid (from)',
			name:'validFromDt',
	        anchor : '90%',
	        allowBlank:false,
	        required:true,
	        value:new Date(),
	        listeners:{
	        	change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel:	'Valid (to)',
			name:'validToDt',
	        anchor : '90%',
	        listeners:{
		   		change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
	    
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				scope:this,
				handler: function(){
					this.saveBtnClicked(config);
				}
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.resetBtnClicked();
				}
			}]
		});	
		this.serviceCbx = new Ext.form.ComboBox({
			fieldLabel:	'Service',
			hiddenName:'serviceNameCode',
			mode:'local',
			store: loadServicesCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '95%',
	        forceSelection : true
		});
		
		this.diseaseCbx = new Ext.form.ComboBox({
			fieldLabel:	'Disease',
			hiddenName:'diseaseNameCode',
			mode:'local',
			store: loadDiseaseCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '95%',
	        forceSelection : true
		});
		
		this.serviceGridPanel = new administration.insurerPlan.PlanInfoGrid({
			header:'Service',
			nameCode:'serviceNameCode',
			nameDesc:'serviceNameDesc',
			coverageAmnt:'serviceCoverageAmt',
			percentAbsInd:'servicePercentAbsInd',
			isCoveredFlag:'isServiceCoveredFlag',
			cbx:this.serviceCbx
		});
		
		this.diseaseGridPanel = new administration.insurerPlan.PlanInfoGrid({
			header:'Disease',
			nameCode:'diseaseNameCode',
			nameDesc:'diseaseNameDesc',
			coverageAmnt:'diseaseCoverageAmt',
			percentAbsInd:'diseasePercentAbsInd',
			isCoveredFlag:'isDiseaseCoveredFlag',
			cbx:this.diseaseCbx
			
		});
		
		this.serviceGrid = this.serviceGridPanel.infoGrid;
		this.diseaseGrid = this.diseaseGridPanel.infoGrid;
		
		this.serviceGridPanel.addBtn.addListener('click',function(){
			this.addServicesToGrid();
		},this);
		
		this.serviceGridPanel.editBtn.addListener('click',function(){
			this.editService();
		},this);
		
		this.serviceGridPanel.deleteBtn.addListener('click',function(){
			this.deleteRowFromGrid(this.serviceGrid);
		},this);
		
		this.diseaseGridPanel.addBtn.addListener('click',function(){
			this.addDiseasesToGrid();
		},this);
		
		this.diseaseGridPanel.editBtn.addListener('click',function(){
			this.editDisease();
		},this);
		
		this.diseaseGridPanel.deleteBtn.addListener('click',function(){
			this.deleteRowFromGrid(this.diseaseGrid);
		},this);
		
		this.planCodeTxf = new Ext.form.TextField({
			anchor: '90%',
	        fieldLabel: 'Code',
	        name: 'planCode',
	        readOnly:config.mode=='edit'?true:false,
	        allowBlank:false,
	        required:true,
	        listeners:{
				blur:function(comp){
					if(!Ext.isEmpty(comp.getValue()) && Ext.isEmpty(config.mode)){
						InsuranceManager.getInsurancePlans( 
							comp.getValue(), 
							null, 
							null, 
							null, 
							null, 
							null, 
							function(insurancePlanBMList){
								if(insurancePlanBMList != null && !Ext.isEmpty(insurancePlanBMList) && 
									insurancePlanBMList.length > 0){
									Ext.Msg.show({
					 					msg: "This plan already exists..!",
									    buttons: Ext.Msg.OK,
									    icon: Ext.MessageBox.WARNING,
									    fn:function(btn){
									    	comp.reset();
									    	comp.focus();
									    }
				 					});
								}
						});
					}
				}				        
	        }
		});
		
		this.planNameTxf = new Ext.form.TextField({
			anchor: '90%',
	        fieldLabel: 'Plan name',
	        name: 'planName',
	        allowBlank:false,
	        required:true
		});
		
		this.tabPanel = new Ext.TabPanel({
			activeTab:0,
			width : '100%',
			autoHeight : true,
			border : false,
			style:'padding-top:10px',
			layoutOnTabChange :true,
			items: [
				{
					title:'Services',
					autoScroll:true,
					autoHeight:true,
					frame:true,
					items:this.serviceGridPanel.getPanel()
				},
				{
					title:'Diseases',
					autoScroll:true,
					autoHeight:true,
					frame:true,
					items:this.diseaseGridPanel.getPanel()
				}
			]
		});
		
		this.amountNbf = new Ext.form.NumberField({
			anchor: '90%',
	        fieldLabel: 'Coverage amount',
	        name: 'totalCoverageAmt',
	        listeners:{
				blur:function(comp){
					if(!Ext.isEmpty(mainThis.indicatorCbx.getValue()) && 
						(mainThis.indicatorCbx.getValue() == "P") &&  
							parseInt(comp.getValue()) > 100){
						Ext.Msg.show({
		 					msg: "% value of amount cannot be greater than 100..!",
						    buttons: Ext.Msg.OK,
						    icon: Ext.MessageBox.ERROR,
						    fn:function(btn){
						    	mainThis.indicatorCbx.reset();
						    	comp.reset();
						    	comp.focus();
						    }
	 					});
	 					return;
					}
				}
			}
		});
		
		this.indicatorCbx = new Ext.form.ComboBox({
			anchor: '90%',
	        fieldLabel: 'Amount indicator',
	        hiddenName: 'percentAbsInd',
	        forceSelection:true,
	        store:[['P','%'],['A','Monetary']],
	        listeners:{
				select:function(comp,record,index){
					if(comp.getValue() == "P" && 
						!Ext.isEmpty(mainThis.amountNbf.getValue()) &&  
							parseInt(mainThis.amountNbf.getValue()) > 100){
						Ext.Msg.show({
		 					msg: "% value of amount cannot be greater than 100..!",
						    buttons: Ext.Msg.OK,
						    icon: Ext.MessageBox.ERROR,
						    fn:function(btn){
						    	mainThis.amountNbf.reset();
						    	mainThis.amountNbf.focus();
						    	comp.reset();
						    }
	 					});
	 					return;
					}
				}
			}
		});
		
		this.panel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			defaults:{columnWidth:.5,labelWidth:120},
			monitorValid:true,
			items:[
				{
					layout:'form',
					items:this.planCodeTxf
				},
				{
					layout:'form',
					items:this.planNameTxf
				},
				{
					layout:'form',
					items:[{
				        fieldLabel: 'Insurer',
				        xtype: 'combo',
				        store:loadInsurerCbx(),
				        name:'insurerName',
						mode:'local',
						triggerAction: 'all',
						displayField:'code',
						valueField:'code',
				        anchor:'90%',
				        forceSelection : true,
				        allowBlank:false,
				        required:true
				    }]
				},
				{
					layout:'form',
					items:this.fromDate
				},
				{
					layout:'form',
					items:this.toDate
				},
				{
					layout:'form',
					columnWidth:.5,
					items:[
						{xtype:'label'}
					]
				},
				{
					layout:'form',
					items:this.amountNbf
				},
				{
					layout:'form',
					items:this.indicatorCbx
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.tabPanel
				}
			]
		});
		
		this.planPanel = new Ext.Panel({
			frame:true,
			width : '100%',
			autoHeight : true,
			border : false,
			items: [
				this.panel,
				this.buttonPanel
			]
		});
		
		
		this.panel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonPanel.buttons[0].enable();
			} else {
				this.buttonPanel.buttons[0].disable();
			}
		},this);
	},
	getPanel : function() {
		return this.planPanel;
	},
	getData : function() {
		var valuesMap = this.panel.getForm().getValues();
	},
	resetBtnClicked: function(){
		this.panel.getForm().reset();
		this.fromDate.setMaxValue(null);
		this.toDate.setMinValue(null);
		this.serviceGrid.getStore().removeAll();
		this.diseaseGrid.getStore().removeAll();
		if(this.mode=='edit'){
			this.loadData(this);
		}
	},
	saveBtnClicked : function(config){
		if(this.panel.getForm().isValid()){
			var serviceRows = this.serviceGrid.getStore().data.items;
			var diseaseRows = this.diseaseGrid.getStore().data.items;
			var serviceList = new Array();
			var diseaseList = new Array();
			var basicDetails = this.panel.getForm().getValues();
			var mainThis = this;
			
			if(!Ext.isEmpty(basicDetails) && 
				!Ext.isEmpty(basicDetails['totalCoverageAmt']) &&
				  Ext.isEmpty(basicDetails['percentAbsInd'])){
				  	
				  	Ext.Msg.show({
	 					msg: "Please select amount indicator..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.indicatorCbx.focus();
					    }
 					});
	 					
				return;
			}
			
			for( var i = 0; i<serviceRows.length; i++ ) {
				var currRow = serviceRows[i].data;
				var services = {
					planName : {code:basicDetails.planCode},
					serviceName : {code:currRow.serviceNameCode},
					isCoverdFlag : currRow.isServiceCoveredFlag,
					percetAbsInd : currRow.servicePercentAbsInd == '%' ? 'P':(currRow.servicePercentAbsInd == 'Monetary' ? 'A' : null),
					coverageAmt : currRow.serviceCoverageAmt
				}
				serviceList.push(services);
			}
			
			for( var i = 0; i<diseaseRows.length; i++ ) {
				var currRow = diseaseRows[i].data;
				var diseases = {
					planName : {code:basicDetails.planCode},
					diseaceName : {code:currRow.diseaseNameCode},
					isCoverdFlag : currRow.isDiseaseCoveredFlag,
					percentAbsInd : currRow.diseasePercentAbsInd == '%' ? 'P':(currRow.diseasePercentAbsInd == 'Monetary' ? 'A' : null),
					coverageAmt : currRow.diseaseCoverageAmt
				}
				diseaseList.push(diseases);
			}
			
			var insurancePlanBM = {
				planCode : basicDetails.planCode,
				insurerName :  basicDetails.insurerName,
				planName : basicDetails.planName,
				validFromDt : getStringAsWtcDateFormat(basicDetails.validFromDt),
				validToDt : Ext.isEmpty(basicDetails.validToDt)? null:getStringAsWtcDateFormat(basicDetails.validToDt),
				totalCoverageAmt : basicDetails.totalCoverageAmt,
				percentAbsInd : basicDetails.percentAbsInd,
				lastModifiedBy : authorisedUser.userName,
				createdBy : authorisedUser.userName,
				planHasServicesBMList : serviceList,
				planCoversDiseaseBMList : diseaseList
			};
			
			
			if(config.mode == "edit"){
				InsuranceManager.modifyInsurancePlan(insurancePlanBM,function(){
					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
					Ext.ux.event.Broadcast.publish('closePlanPanel');
				});
			}else{
				InsuranceManager.createInsurancePlan(insurancePlanBM,{
					callback: function(insuranceBM){
						if(insuranceBM != null && !Ext.isEmpty(insuranceBM)){
							if(mainThis.isPopUp){
								layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
								Ext.ux.event.Broadcast.publish('closePlanPanel');
							}else{
								mainThis.resetBtnClicked();
							}
						}
					}}
			    );
			}
		} else {
			warning("Enter all the required fields..!");
		  	return;
		}
	},
	returnSelectedDataRecord: function(infoGrid){
    	var slctdRows = infoGrid.getSelectionModel().getSelections();
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
	resetServicePanel:function(){
		var config = {
			serviceNameCode:'',
			serviceNameDesc:'',
			serviceCoverageAmt:'',
			servicePercentAbsInd:''
		}
		this.panel.getForm().setValues(config);
	},
	resetDiseasePanel:function(){
		var config = {
			diseaseNameCode:'',
			diseaseNameDesc:'',
			diseaseCoverageAmt:'',
			diseasePercentAbsInd:''
		}
		this.panel.getForm().setValues(config);
	},
	addServicesToGrid : function(){
		var values = this.panel.getForm().getValues();
		if(!Ext.isEmpty(values['serviceNameCode'])){
			var record = this.serviceGrid.getStore().recordType;
			var mainThis = this;
			var slctdServiceDesc = this.serviceCbx.getRawValue();
			
			for( var i = 0; i<this.serviceGrid.getStore().data.items.length; i++ ) {
				var currRow = this.serviceGrid.getStore().data.items[i].data;
				if(currRow.serviceNameCode == values['serviceNameCode']){
					Ext.Msg.show({
	 					msg: "Seleted service already exists below..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.serviceCbx.reset();
					    	mainThis.serviceCbx.focus();
					    }
 					});
 					return;
				}
			}
			
			if(!Ext.isEmpty(values['serviceCoverageAmt']) && Ext.isEmpty(values['servicePercentAbsInd'])){
				Ext.Msg.show({
	 					msg: "Please select amount indicator..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.serviceGridPanel.indicatorCbx.focus();
					    }
 					});
 					return;
			}
			
		   	 var serviceRecord = new record({
					serviceNameCode: values['serviceNameCode'],
					serviceNameDesc: slctdServiceDesc,
					serviceCoverageAmt: values['serviceCoverageAmt'],
					servicePercentAbsInd: values['servicePercentAbsInd'],
					isServiceCoveredFlag: !Ext.isEmpty(values['isServiceCoveredFlag'])?'Y':'N'
			 });
			 this.serviceGrid.stopEditing();
			 
			 var insertAt = this.serviceGrid.getStore().data.items.length;		 
			 this.serviceGrid.getStore().insert(insertAt, serviceRecord);
			 this.resetServicePanel();
		}
	},
	addDiseasesToGrid : function(){
		var values = this.panel.getForm().getValues();
		if(!Ext.isEmpty(values['diseaseNameCode'])){
			 var record = this.diseaseGrid.getStore().recordType;
			 var mainThis = this;
			 var diseaseStore = loadDiseaseCbx();
			 var slctdDiseaseDesc = this.diseaseCbx.getRawValue();
			 
			 for( var i = 0; i<this.diseaseGrid.getStore().data.items.length; i++ ) {
				var currRow = this.diseaseGrid.getStore().data.items[i].data;
				if(currRow.diseaseNameCode == values['diseaseNameCode']){
					Ext.Msg.show({
	 					msg: "Seleted disease already exists below..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.diseaseCbx.reset();
					    	mainThis.diseaseCbx.focus();
					    }
 					});
 					return;
				}
			}
			
			if(!Ext.isEmpty(values['diseaseCoverageAmt']) && Ext.isEmpty(values['diseasePercentAbsInd'])){
				Ext.Msg.show({
	 					msg: "Please select amount indicator..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.diseaseGridPanel.indicatorCbx.focus();
					    }
 					});
 					return;
			}
			
		   	 var diseaseRecord = new record({
					diseaseNameCode: values['diseaseNameCode'],
					diseaseNameDesc: slctdDiseaseDesc,
					diseaseCoverageAmt: values['diseaseCoverageAmt'],
					diseasePercentAbsInd: values['diseasePercentAbsInd'],
					isDiseaseCoveredFlag:!Ext.isEmpty(values['isDiseaseCoveredFlag'])?'Y':'N'
			 });
			 this.diseaseGrid.stopEditing();
			 
			 var insertAt = this.diseaseGrid.getStore().data.items.length;		 
			 this.diseaseGrid.getStore().insert(insertAt, diseaseRecord);
			 this.resetDiseasePanel();
		}
	},
	editService : function(){
		this.resetServicePanel();
		var slctdRows = this.serviceGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.returnSelectedDataRecord(this.serviceGrid);
	     	var config = {
	     		isServiceCoveredFlag:(slctdRecordToEdit.isServiceCoveredFlag == 'Y') ? true : false,
	     		serviceCoverageAmt:slctdRecordToEdit.serviceCoverageAmt,
	     		serviceNameCode:slctdRecordToEdit.serviceNameCode,
	     		serviceNameDesc:slctdRecordToEdit.serviceNameDesc,
	     		servicePercentAbsInd:slctdRecordToEdit.servicePercentAbsInd
	     	};
			this.deleteRowFromGrid(this.serviceGrid);
			this.panel.getForm().setValues(config);
  		} else {
   			Ext.Msg.show({
			   msg: 'Please select atleast & atmost one row to edit..!',
			   buttons: Ext.Msg.OK,
			   icon: Ext.MessageBox.INFO
            })
  		}
	},
	editDisease : function(){
		this.resetDiseasePanel();
		var slctdRows = this.diseaseGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.returnSelectedDataRecord(this.diseaseGrid);
	     	var config = {
	     		isDiseaseCoveredFlag:(slctdRecordToEdit.isDiseaseCoveredFlag == 'Y') ? true : false,
	     		diseaseCoverageAmt:slctdRecordToEdit.diseaseCoverageAmt,
	     		diseaseNameCode:slctdRecordToEdit.diseaseNameCode,
	     		diseaseNameDesc:slctdRecordToEdit.diseaseNameDesc,
	     		diseasePercentAbsInd:slctdRecordToEdit.diseasePercentAbsInd
	     	};
	     	this.deleteRowFromGrid(this.diseaseGrid);
			this.panel.getForm().setValues(config);
  		} else {
   			Ext.Msg.show({
			   msg: 'Please select atleast & atmost one row to edit..!',
			   buttons: Ext.Msg.OK,
			   icon: Ext.MessageBox.INFO
            })
  		}
	},
	deleteRowFromGrid : function(infoGrid) {
		var slctdRows = infoGrid.getSelectionModel().getSelections();
	    if(!Ext.isEmpty(slctdRows) && slctdRows.length>0) {
	    	for(var i = 0; i<slctdRows.length; i++) {
	    		infoGrid.stopEditing();
				infoGrid.getStore().remove(slctdRows[i]);
	     	}
	    }
	},
	getPlanPanel : function(){
		return this.panel;
	},
	loadData : function(config){
		var config = config.selectedRow;
		
		this.panel.getForm().setValues(config);
		
		var assocServicesRows = config.planHasServicesBMList;
		var assocServicesRecord = this.serviceGrid.getStore().recordType;
		var assocDiseaseRows = config.planCoversDiseaseBMList;
		var assocDiseaseRecord = this.diseaseGrid.getStore().recordType;
		
		if(assocServicesRows!=null && assocServicesRows.length>0 ){
			for(var i = 0; i<assocServicesRows.length; i++){
				var serviceRecord = new assocServicesRecord({
					serviceNameCode: assocServicesRows[i].serviceName.code,
					serviceNameDesc: assocServicesRows[i].serviceName.description,
					serviceCoverageAmt: assocServicesRows[i].coverageAmt,
					servicePercentAbsInd: assocServicesRows[i].percetAbsInd == 'A' ? 'Monetary' : (assocServicesRows[i].percetAbsInd == 'P' ? '%' : null ),
					isServiceCoveredFlag: assocServicesRows[i].isCoverdFlag
				});
				 this.serviceGrid.getStore().add(serviceRecord);
			}
		}
		
		if(assocDiseaseRows!=null && assocDiseaseRows.length>0 ){
			for(var i = 0; i<assocDiseaseRows.length; i++){
				var diseaseRecord = new assocDiseaseRecord({
					diseaseNameCode: assocDiseaseRows[i].diseaceName.code,
					diseaseNameDesc: assocDiseaseRows[i].diseaceName.description,
					diseaseCoverageAmt: assocDiseaseRows[i].coverageAmt,
					diseasePercentAbsInd: assocDiseaseRows[i].percentAbsInd == 'A' ? 'Monetary' : (assocDiseaseRows[i].percentAbsInd == 'P' ? '%' : null ),
					isDiseaseCoveredFlag: assocDiseaseRows[i].isCoverdFlag
				});
				 this.diseaseGrid.getStore().add(diseaseRecord);
			}
		}
	},
	
	getFocus : function(config){
		if(config.mode == "edit"){
			this.planNameTxf.focus();
		}else{
			this.planCodeTxf.focus();
		}
	}

});