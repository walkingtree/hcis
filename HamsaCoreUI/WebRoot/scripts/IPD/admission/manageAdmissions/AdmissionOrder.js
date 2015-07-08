Ext.namespace("IPD.admission.manageAdmissions");

IPD.admission.manageAdmissions.AdmissionOrder = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this,config);
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this;
		
		this.basicDoctorOrderPanel = new IPD.addOrder.DoctorOrder();
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				handler: function(){
					this.saveHandler();
				},
				scope:this
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				handler: function(){
				},
				scope:this
			}]
		});	
		this.patientNameTxf = new Ext.form.TextField({
			fieldLabel:msg.patientname,
			name:'patientName',
			readOnly:true,
			anchor : '95%'
		});
		this.expectedDateOfAdmission = new Ext.form.WTCDateTimeField({
			fieldLabel:msg.expecteddateofadmission,
	        value:new Date(),
	        anchor : '95%',
	        name:'EDOA'
		});	
		this.expectedDateOfDischarge = new Ext.form.WTCDateTimeField({
			fieldLabel:msg.expecteddateofdischarge,
	        anchor : '95%',
	        name:'EDOD'
		});
		this.orderPanel = new Ext.form.FormPanel({
				width : '100%',
				height : 390,
				border : false,
				layout : 'column',
				defaults: {
					columnWidth : .30
				},
				items: [
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.patientid,
						        xtype: 'textfield',
						        name:'patientId',
						        anchor : '95%',
						        allowBlank:false,
						        listeners:{
						        	blur:function(comp){
						        		if(!Ext.isEmpty(comp.getValue())){
						        			var admissionNbr = parseInt(comp.getValue(),10);
						        			OrderManager.getAdmittedPatient(admissionNbr,
					        				function(patientObj){
					        					if(patientObj != null){
					        						mainThis.patientNameTxf.setValue(patientObj.description);
					        					}else{
					        						comp.setValue('');
					        						mainThis.patientNameTxf.setValue('');
					        					}
					        				});
						        		}
						        	}
						        }
					    	}
						]
					},
					{
						layout:'form',
						items:this.patientNameTxf
					},
					{
						layout:'form',
						items:[
							{
						        xtype:'combo',
								fieldLabel:msg.doctor,
								name:'doctorId',
								mode:'local',
								store: loadDoctorsCbx(),
								triggerAction: 'all',
								displayField:'description',
								valueField:'code',
						        forceSelection : true,
						        allowBlank:false,
						        resizable:true,
						        anchor : '95%'
					    	}
						]
					},
					{
						layout:'form',
						items:this.expectedDateOfAdmission
					},
					{
						layout:'form',
						items:this.expectedDateOfDischarge
					},
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.estimatedcost,
						        xtype: 'numberfield',
						        anchor : '95%',
						        name:'estimatedCost'
						    }
						]
					},
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.unittype,
						        xtype: 'combo',
						        name: 'unitType',
						        store: loadNursingUnitTypes(),
								mode:'local',
								displayField:'description',
								valueField:'code',
							    triggerAction: 'all',
							    resizable:true,
							    forceSelection:true,
						        anchor : '95%'
						    }
						]
					},
					{
						layout:'form',
						items:[
							{
						        fieldLabel: msg.admissionentrypoint,
						        xtype: 'combo',
						        name: 'admissionEntryPoint',
						        store: loadAdmissionEntryPointCbx(),
								mode:'local',
								displayField:'description',
								valueField:'code',
							    triggerAction: 'all',
							    resizable:true,
							    forceSelection:true,
						        anchor : '95%'
						    }
						]
					},
					{
						layout:'form',
						items:[
							{
								xtype:'textfield',
								name:'entryPointReference',
								fieldLabel:msg.entrypointreference,
								anchor:'95%'
							
							}
						]
					},
					{
						layout:'form',
						columnWidth:.6,
						items:[
							{
								xtype:'textfield',
								name:'agenda',
								fieldLabel:msg.agenda,
								anchor:'98%'
							
							}
						]
					},
					{
						layout:'form',
				        hidden:!config.mode =='edit',
						items:[
							{
						        fieldLabel: msg.admissionstatus,
						        xtype: 'combo',
						        name: 'admissionstatus',
								mode:'local',
								store:loadAdmissionStatusCbx(),
								displayField:'description',
								valueField:'code',
							    triggerAction: 'all',
							    resizable:true,
							    forceSelection:true,
						        anchor : '95%'
						    }
						]
					},
					{
						layout:'form',
						columnWidth:.9,
						items:[
							{
								xtype:'textfield',
								name:'reasonForAdmission',
								fieldLabel:msg.reasonforadmission,
								anchor:'98%'
							
							}
						]
					},
					{
						layout:'form',
						columnWidth:1,
						items:this.basicDoctorOrderPanel.getBaseDoctorOrderPanel()
					}
					]
		});	
			
		this.admissionOrderPanel = new Ext.Panel({
				frame : true,
				width : '100%',
				autoHeight : true,
				border : false,
				items: [
				this.orderPanel,
				this.buttonPanel]
		});
		
	},
	getPanel : function() {
			return this.admissionOrderPanel;
	},
	getDoctorOrderDetails:function(){
		var tableRows = this.basicDoctorOrderPanel.doctorOrderServicesGrid.infoGrid.getStore().data.items;
		var doctorOrderDetailsList = new Array();
		for( var i = 0; i<tableRows.length; i++ ) {
			var currRow = tableRows[i].data;
			var doctorOrderDetailsBM = {
				sequenceNbr: parseInt(currRow.sequenceNumber,10),
				subSequenceNbr: parseInt(currRow.subSequenceNumber,10),
				serviceId: currRow.serviceCode,
				responsibleDepartmentId: currRow.responsibleDepartmentCode,
				actionDesc: currRow.actionRequiredDescription,
				actionRemarks:'',
				actionTakenBy:''
			}
			doctorOrderDetailsList.push(doctorOrderDetailsBM);
		}
		return doctorOrderDetailsList;
	},
	saveHandler:function(){
		var valueMap = this.orderPanel.getForm().getValues();
		var mainThis =this;
		if(this.orderPanel.getForm().isValid()){
			var doctorOrderGridDetails = this.getDoctorOrderDetails();
			if(doctorOrderGridDetails!=null && doctorOrderGridDetails.length>0){
				var admissionBM ={
					admissionEntryPoint:{code:valueMap['admissionEntryPoint']},
					doctorId:Ext.isEmpty(valueMap['doctorId'])?null:parseInt(valueMap['doctorId'],10),
					patientId:Ext.isEmpty(valueMap['patientId'])?null:parseInt(valueMap['patientId'],10),
					entryPointReference:valueMap['entryPointReference'],
					reasonForAdmission:valueMap['reasonForAdmission'],
					agenda:valueMap['agenda'],
					treatmentEstimatedCost:Ext.isEmpty(valueMap['estimatedCost'])?null:parseFloat(valueMap['estimatedCost']),
					treatmentEstimationBy:Ext.isEmpty(valueMap['estimatedCost'])?null:authorisedUser.userName,
					admissionRequestedBy:authorisedUser.userName,
					expectedDischargeDtm:Ext.isEmpty(valueMap['EDOD'])?null:this.expectedDateOfDischarge.getValue(),
//					expectedAdmissionDtm:Ext.isEmpty(valueMap['EDOA'])?null:this.expectedDateOfAdmission.getValue(),
					nursingUnitType:{code:valueMap['unitType']},
					doctorOrderDetailsList:this.getDoctorOrderDetails(),
					orderDesc:valueMap['orderDesc'],
					orderDictation:valueMap['orderDictation']
				};
				AdmissionOrder.createAdmissionOrder(admissionBM,function(admissionNbr){
						Ext.Msg.show({
							msg: msg.addadmission+':'+admissionNbr,
							icon : Ext.MessageBox.INFO,
							buttons: Ext.Msg.OK,
							fn:function(){
								mainThis.orderPanel.getForm().reset();
								mainThis.basicDoctorOrderPanel.doctorOrderServicesGrid.infoGrid.getStore().removeAll();
								if(mainThis.title){
									Ext.ux.event.Broadcast.publish('closeAdmissionWindow');
//									Ext.ux.event.Broadcast.publish('reloadAdmissionOrderGrid');
								}
							}
						});
				});
			}else{
				Ext.Msg.show({
					msg: 'Enter minimum single entry in doctor order details..!',
					icon : Ext.MessageBox.ERROR,
					buttons: Ext.Msg.OK
				});
				return;
			}
		}else{
			Ext.Msg.show({
				msg: 'Enter valid data..!',
				icon : Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK
			});
			return;
		}
	},
	resetHandler:function(){
		this.orderPanel.getForm().reset();
		this.basicDoctorOrderPanel.doctorOrderServicesGrid.infoGrid.getStore().removeAll();
	}
});