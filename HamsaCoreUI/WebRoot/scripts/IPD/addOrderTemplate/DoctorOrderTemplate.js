Ext.namespace("IPD.addOrderTemplate");

IPD.addOrderTemplate.DoctorOrderTemplate = Ext.extend(Object, {
	constructor : function(config) {
		
		if(Ext.isEmpty(config)){
			config = {values : {}};
		}else{
			if(Ext.isEmpty(config.values)){
				config.values = {};
			}
		}
		
		var mainFormThisObj = this;
		
		this.isActiveCheckBox = new Ext.form.Checkbox({
			boxLabel:msg.active,
			name:'activeFlag',
			labelSeparator:'',
			style:'font-size:small'
		});
		
		this.templateTxf = new Ext.form.TextField({
	        fieldLabel: msg.template,
	        name: 'templateId',
	        anchor : '98%',
	        allowBlank:false,
	        required:true,
	        value: config.values ? config.values.templateId: null,
	        readOnly : (!Ext.isEmpty(config.values ) && config.mode == configs.edit)  ? true: false,
	        listeners:{
	        	blur:function(comp){
	        		if( config.mode != 'edit'){
		        		if(!Ext.isEmpty(comp.getValue())){
		        			OrderManager.getOrderTemplateForId(comp.getValue(),
								function(template){
									if(template != null ){
										Ext.Msg.show({
	 					 					msg: msg.templateAlreadyExist,
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
		
		
		this.doctorOrderTemplateFieldSet = new Ext.form.FormPanel({
			collapsible: false,
			labelAlign:'left',
			width : '97.5%',
			autoHeight: true,
			border : true,
			monitorValid: true,
			layout:'column',
			items: [
				{
					layout:'form',
					border:false,
					labelWidth:80,
					columnWidth:.3,
					items:this.templateTxf
				},
				{
					layout:'form',
					border:false,
					labelWidth:140,
					columnWidth:.6,
					items:[
						{
					        fieldLabel: msg.templatDescription,
					        name: 'templateDesc',
					        xtype: 'textfield',
					        anchor : '98%',
					        allowBlank:false,
				            required:true,
	        				value: config.values ? config.values.templateDesc: null
						}
					]
				},
				{
					layout:'form',
					border:false,
					labelWidth:80,
					columnWidth:.3,
					items:[
					       {
					    	   xtype:'combo',
						        fieldLabel: msg.doctor,
						        name: 'doctorId',
								mode:'local',
								store: loadDoctorsCbx(),
								triggerAction: 'all',
								displayField:'description',
								valueField:'code',
						        anchor : '98%',
						        forceSelection : true,
						        value: config.values ? config.values.doctorId: null
					       }]
				},
				{
					layout:'form',
					border:false,
					labelWidth:80,
					columnWidth:.3,
					items:[
						{
							xtype:'combo',
							fieldLabel:	msg.ordertype,
							name:'doctorOrderTypeCd',
							mode:'local',
							store: loadDoctorOrderType(),
							triggerAction: 'all',
							displayField:'description',
							valueField:'code',
					        anchor : '98%',
				           	required:true,
					        allowBlank:false,
					        forceSelection : true,
					        value: config.values ? config.values.doctorOrderTypeCd: null
		   				}
					]
			    },
			    {
					layout:'form',
					border:false,
					labelWidth: 1,
					columnWidth:.15,
					style:'margin-left:30px',
					items:this.isActiveCheckBox
			    }
			]
		});
		
		loadDoctorsCbx().on('load',function( thisStore ){
			if( !Ext.isEmpty( this.doctorOrderTemplateFieldSet.items.items[2].items.items[0] )){
				this.doctorOrderTemplateFieldSet.items.items[2].items.items[0].setValue( config.values.doctorId );
			}
		},this);
		
		this.doctorOrderDetailspnl = new IPD.addOrder.DoctorOrderDetailsPanel();
		
		this.dbActionBtnsFieldSet = new Ext.Panel({
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
					this.resetBtnClicked(config);
				}
			}]
		});
		
		
		
		this.doctorOrderTemplatePanel = new Ext.Panel({
			frame : true,
			width : '100%',
			autoHeight : true,
			border : false,
			items: [
				this.doctorOrderTemplateFieldSet,
				this.doctorOrderDetailspnl.getPanel(),
				this.dbActionBtnsFieldSet
			]
		});
		
		// for monitoring the personal details panel 
		this.doctorOrderTemplateFieldSet.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
			this.dbActionBtnsFieldSet.buttons[0].enable();
			} else {
			this.dbActionBtnsFieldSet.buttons[0].disable();
			}
		}, this);
		
		
	},

	saveBtnClicked : function(config) {
		if(this.doctorOrderTemplateFieldSet.getForm().isValid()){
			
			var basicDetails = this.doctorOrderTemplateFieldSet.getForm().getValues();
			var isActiveSlctd = this.isActiveCheckBox.getValue();
			var orderTemplateBM = {
				templateId : basicDetails.templateId,
				templateDesc :  basicDetails.templateDesc,
				doctorId : (!Ext.isEmpty( basicDetails.doctorId ) && basicDetails.doctorId != 'undefined')? parseInt(basicDetails.doctorId,10):null,
				doctorOrderType : {code:basicDetails.doctorOrderTypeCd},
				activeFlag : isActiveSlctd?'Y':'N',
				orderTemplateDetailsList : this.doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.getData()
			};
			
			var mainThis = this;
			
			if(config.mode == "edit"){
				OrderManager.modifyOrderTemplate(orderTemplateBM,{
					callback: function(){
				    	if(!Ext.isEmpty(config.title)){
				    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
							Ext.ux.event.Broadcast.publish('closeDoctorOrderTemplateWindow');
						}else{
							this.resetBtnClicked();
						}
						
						doctorOrderTemplatesStore.reload();
					},
					scope: this}
			    );
			}else{
				OrderManager.createOrderTemplate(orderTemplateBM,{
					callback: function(){
				    	if(!Ext.isEmpty(config.title)){
				    		layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
							Ext.ux.event.Broadcast.publish('closeDoctorOrderTemplateWindow');
						}else{
							this.resetBtnClicked();
						}
						doctorOrderTemplatesStore.reload();
					},
					scope : this}
			    );
			}
												   
		} else {
			warning("Please enter all the required fields and try again!");
		  	return;
		}
	},

	resetBtnClicked : function(config) {
		this.doctorOrderTemplateFieldSet.getForm().reset();
		this.doctorOrderDetailspnl.resetPanel();
		if( !Ext.isEmpty(config) && config.mode == configs.edit ){
			this.loadData(config);
		}
	},
	
	getPanel : function() {
		return this.doctorOrderTemplatePanel;
	},
	loadData: function(config){
		
		this.doctorOrderTemplateFieldSet.getForm().setValues(config.values);
		if( !Ext.isEmpty(config.values) && (config.values.activeFlag === "Y") )
			this.isActiveCheckBox.setValue( true );
		if( !Ext.isEmpty(config) && config.mode == 'edit' ){
			this.doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.loadData(config);
		}
		
	},
	getFocus: function(){
		this.templateTxf.focus();
	}
});