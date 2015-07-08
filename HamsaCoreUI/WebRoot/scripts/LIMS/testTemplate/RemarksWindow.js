Ext.namespace("LIMS.testTemplate");

LIMS.testTemplate.RemarksWindow = Ext.extend(Ext.form.FormPanel, {
	layout:'column',
	width : '100%',
	border : false,
	frame:true,
	buttonAlign:'right',
	labelAlign :'left',
	monitorValid:true,
	
	initComponent : function() {
	
		this.configData = this.initialConfig;
	
		this.okBtn = new Ext.Button({
			text: msg.ok,
	    	iconCls : 'accept-icon',
	    	scope: this,
	    	handler:function(){
	    		this.handleOkBtn();
	    	}
		});
		

		this.cancelBtn = new Ext.Button({
			text: msg.close,
	    	iconCls : 'cross_icon',
	    	scope: this,
	    	handler:function(){
			this.ownerCt.destroy();
		}
		});

		
		this.remarksTxa = new Ext.form.TextArea({
			name:'remarks',
			allowBlank :false,
			required : true, 
			anchor:'98%'
		});
		
		
		Ext.applyIf(this,{
						monitorValid:true,
						defaults:{columnWidth:1,labelWidth:.01},
						items: [
							{
								layout:'form',
								items:this.remarksTxa
							}
						],
						buttons:[this.okBtn, this.cancelBtn]
					});
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.okBtn.enable();
			} else {
				this.okBtn.disable();
			}
		}, this);
		
		LIMS.testTemplate.RemarksWindow.superclass.initComponent.apply(this,arguments);
	},

	handleOkBtn : function( ){
		var formValues = this.getForm().getValues();
		var remarks = formValues['remarks'];

    	LabTestManager.modifyPatientTestStatus( 
				this.configData.serviceUID,
				this.configData.SERVICE_STATUS,
				getAuthorizedUserInfo().userName,remarks,
				{
					callback : function() {
					Ext.ux.event.Broadcast.publish('closeEnterTestResult');
					this.ownerCt.destroy();
					},
					scope : this
				});
		
	}
});