Ext.namespace("administration.medicines.addMedicine");

administration.medicines.addMedicine.ConfigureMedicine = Ext.extend(Object, {
	constructor : function(config) {
		
		this.statusCombo = new Ext.form.ComboBox({
							fieldLabel:pharmacyMsg.status,
							mode:'local',
							store: loadStatusType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'status',
							anchor:'80%',
							required:true,
							allowBlank:false
		});
		
		this.brandNameCombo = new Ext.form.ComboBox({
							fieldLabel:pharmacyMsg.brandName,
							mode:'local',
							store: loadBrand(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'brandCode',
							anchor:'80%',
							required:true,
							allowBlank:false
		});
	
		this.medicineTypeCombo = new Ext.form.ComboBox({
							fieldLabel:pharmacyMsg.medicineType,
							mode:'local',
							store: loadMedicineType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'medicineType',
							anchor:'80%',
							required:true,
							allowBlank:false
		});

		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:pharmacyMsg.btnSave,
				iconCls : 'save_btn',
				scope:this,
				disabled:true,
				handler: function(){
					this.saveMedicine(config);
				}
			},{
				xtype:'button',
				text:pharmacyMsg.btnReset,
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.resetMedicine(config);
				}
			}]
		});		
		
		this.medicineCodeTxf = new Ext.form.TextField({
			fieldLabel:pharmacyMsg.medicineCode,
			name:'medicineCode',
			anchor:'80%',
			required:true,
			allowBlank:false
	  	});
	  	
		this.configureMedicinePnl = new Ext.form.FormPanel({
			layout : 'column',
			labelAlign : 'left',
			labelWidth : 70,
			height: '100%',
			width: '100%',
			border : false,
			frame:true,
			monitorValid:true,
			defaults : {
				labelWidth : 125
			},
			items: [
				{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:this.medicineCodeTxf
			    },{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.medicineName,
							name:'medicineName',
							anchor:'80%',
							required:true,
							allowBlank:false
						  }]
				},{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[this.brandNameCombo]
				},{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[this.medicineTypeCombo]
				},{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.strength,
							name:'strength',
							anchor:'80%',
							required:true,
							allowBlank:false
						  }]
				},{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.dosagePerDay,
							name:'dosagePerDay',
							anchor:'80%',
							required:true,
							allowBlank:false
						  }]
				},{
					columnWidth:.5,
					border:false,
					layout:'form',
					items:[this.statusCombo]
				},{
					columnWidth:1,
					border:false,
					layout:'form',
					items:[this.buttonPanel]
				}
			]
		});
		
		this.configureMedicinePnl.on("clientvalidation", 
 									 function(thisForm, isValid) {
										if (isValid){
											this.buttonPanel.buttons[0].enable();
										} else {
											this.buttonPanel.buttons[0].disable();
										}
									 },
									 this
									);

		this.configureMedicinePnl.on("render", function(valueToBeSet) {
			valueToBeSet = statusTypeStore.getAt(0);
			this.statusCombo.setValue(valueToBeSet.data.code);
		}, this);
		
		if( config.mode == pharmacyMsg.editMode ){
			this.medicineCodeTxf.disable();
		}
		var mainThis = this;
		
		this.medicineCodeTxf.on('blur', function( comp ){
			MedicineManagementController.isMedicineExist( comp.getValue(), {
				callback: function( flag ){
					if( flag ){
						Ext.Msg.show({
						   msg: pharmacyMsg.medicineExists,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	 mainThis.medicineCodeTxf.reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		
		
	},
	
	getPanel : function() {
		return this.configureMedicinePnl;
	},
	
	loadData : function(config){
		var slctdRow = config.selectedRow;
		if(slctdRow[status] === false)
			slctdRow[status] = 0;
		else if(slctdRow[status] === true)
			slctdRow[status] = 1;
		this.configureMedicinePnl.getForm().setValues(slctdRow);
	}, 
	
	saveMedicine : function(config){
		var mainThis= this;
		if(this.configureMedicinePnl.getForm().isValid()){
			var inputValues = this.configureMedicinePnl.getForm().getValues();
			
			var tmpStatus = false;
	        if(inputValues['status'] === '1') {
	          tmpStatus = true;
	        }
 
			medicineBM = {
				medicineCode : inputValues['medicineCode'],
				medicineName : inputValues['medicineName'],
				brand : {code : inputValues['brandCode'], description : ""},
				medicineType : {code : inputValues['medicineType'], description : ""},
				strength : inputValues['strength'],
				maximumDosage : inputValues['dosagePerDay'],
				active : tmpStatus			
     		}
     			
			if (!Ext.isEmpty(config) && (config.mode == pharmacyMsg.addMode)) {
				MedicineManagementController.addMedicine(
				   medicineBM,
				   function(){
						mainThis.resetMedicine({});
				   }
				);
			}
			
			if (!Ext.isEmpty(config) && (config.mode == pharmacyMsg.editMode)) {
				var medicineCode = this.medicineCodeTxf.getValue();
				medicineBM.medicineCode = medicineCode;
				MedicineManagementController.modifyMedicine(
				   medicineBM,
				   function(){
						mainThis.resetMedicine({});
				   }
      			);
			}
			if( !Ext.isEmpty( config ) && config.isPopup == true ){
				this.closeConfigureMedicineWindow();
			}
					
		} else {
			error(pharmacyMsg.invalidData);
		}
	}, 
	
	closeConfigureMedicineWindow : function(){
		Ext.ux.event.Broadcast.publish('closeConfigureMedicineWindow');
		Ext.ux.event.Broadcast.publish('reloadSearchMedicinesGrid');
	},
	
	resetMedicine : function(config){
		this.configureMedicinePnl.getForm().reset();
		if(config.mode ==pharmacyMsg.editMode){
			this.loadData(config);
		}
	}
});