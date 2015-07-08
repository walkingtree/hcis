Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrderBasicDetailsPanel = Ext.extend(Object,{
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}else{
			if(Ext.isEmpty(config.values)){
				config.values = {};
			}
		}
		var panelObj = this;
		
		this.patientCombo = new Ext.form.ComboBox({
			fieldLabel:	msg.patient,
			hiddenName:'patientId',
			mode:'local',
			store: loadPatientCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        disabled : config.mode == 'edit' ? true : false,
	        anchor : '98%',
	        forceSelection : true,
	        allowBlank:false,
	        required:true,
	        value:config.values.patientId,
	        disabled : config.mode == configs.edit ? true : false,
	        listeners:{
	        	select:function(comp, record,index){
	        		var test = this;
	        		if(!Ext.isEmpty(comp.getValue())){
	        			var patientId = parseInt(comp.getValue(),10);
	        			IPDataModelManager.getActiveAdmissionOfPatient(patientId,
        				function(admissionNbr){
        					if(admissionNbr != null){
        						test.admissionNbrField.setValue(admissionNbr);
        						test.admissionNbrField.setDisabled(true);
        					}else {
        						test.admissionNbrField.setValue('');
        						test.admissionNbrField.setDisabled(false);
        					}
        				});
	        		}
	        	},
	        	scope : this
	        }
		});
		
		this.statusCombo = new Ext.form.ComboBox({
	        fieldLabel: msg.status,
	        name: 'doctorOrderStatus',
			mode:'local',
			store: loadDoctorOrderStatus(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '98%',
	        allowBlank:false,
	        disabled : config.mode == 'edit' ? true : false,
	        forceSelection : true,
	        value:config.values.statusCd
		});
		
		this.admissionNbrField = new Ext.form.NumberField({
	        fieldLabel: msg.admissionNumber,
	        name: 'admissionNbr',
	        xtype: 'numberfield',
	        anchor : '98%',
	        disabled : true
		});
		
		if(config.mode == 'edit' && config.source == 'appointment'){
			var id = this.patientCombo.getValue();
			if(!Ext.isEmpty(id)){
		        			var patientId = parseInt(id,10);
		        			IPDataModelManager.getActiveAdmissionOfPatient(patientId,{
		        				callback:function(admissionNbr){
		        					if(admissionNbr != null){
		        						this.admissionNbrField.setValue(admissionNbr);
		        						this.admissionNbrField.setDisabled(true);
		        					}else {
		        						this.admissionNbrField.setValue('');
		        						this.admissionNbrField.setDisabled(true);
		        					}
	        					},
	        					scope: this
		        			});
	        				
		      }
		}
		
//		this.searchPatientBtn = new Ext.Button(
//			{
//	   			cls:'x-btn-icon',
//	   			icon:'images/find.png',
//	   			tooltip : msg.findpatient,
//	   			disabled : config.mode == 'edit' ? true : false,
//	   			scope:this,
//	   			style:'margin-bottom:7px',
//			}
//		);
		
		
		
		this.doctorOrderBasicDetailsPanel = new Ext.Panel({
			collapsible: false,
			layout:'column',
			width : '97.5%',
			autoHeight: true,
			style: 'padding-top:5px;',
			border : false,
			defaults:{columnWidth:.32,labelWidth:70},
			items: [
				{
					layout:'column',
					items:[
						{
							border:false,
							columnWidth : 1,
							labelWidth:50,
							layout: 'form', 
							items :this.patientCombo
						}
//						{
//							columnWidth : .1,
//							layout : 'form',
//							items : this.searchPatientBtn
//						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:115,
					style:'padding-left:10px',
					items:this.admissionNbrField
				},
			    {
					layout:'form',
					border:false,
					labelWidth:50,
					hidden:config.mode == 'edit'?((config.source == 'appointment')? true : false):true,
					items:this.statusCombo
				}
			]
		});
	},
	getPanel : function() {
		return this.doctorOrderBasicDetailsPanel;
	},
	getData : function() {
		var values;
		if(this.patientCombo.isValid(false) ){
			 values = {
				patientId:this.patientCombo.getValue(),
				doctorOrderStatus:{code:this.statusCombo.getValue()},
				admissionNbr:this.admissionNbrField.getValue()
			};
		}
			
		return values;
	},
	resetPanel:function(){
		this.patientCombo .clearValue();
		this.statusCombo.clearValue();
		this.admissionNbrField.setValue('');
	},
	getSearchPatientBtn : function(){
		return this.searchPatientBtn
	},
	getPatientCombo : function(){
		return this.patientCombo;	
	},
	getAdmissionNbrField : function(){
		return this.admissionNbrField;
	}	
	
});