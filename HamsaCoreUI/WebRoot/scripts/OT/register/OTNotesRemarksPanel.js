Ext.namespace("OT.register");

OT.register.OTRemarksPanel = Ext.extend(Ext.form.FormPanel, {
	layout:'column',
	width : '100%',
	height : 290,
	border : false,
	frame:true,
	buttonAlign:'right',
	labelAlign :'left',
	monitorValid:true,
	
	initComponent : function() {
	
		this.okBtn = new Ext.Button({
			text: msg.ok,
	    	iconCls : 'accept-icon',
	    	scope: this
		});
		

		this.cancelBtn = new Ext.Button({
			text: msg.close,
	    	iconCls : 'cross_icon',
	    	scope: this,
	    	handler:function(){
			this.ownerCt.destroy();
		}
		});

		
		this.remarksTxa = new Ext.form.HtmlEditor({
			name:'remarks',
//			allowBlank :false,
//			required : true, 
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
		
		OT.register.OTRemarksPanel.superclass.initComponent.apply(this,arguments);
	},

	getOkBtn : function(){
		return this.okBtn;
	},
	
	getRemarks : function(){
		var formValues = this.getForm().getValues();
		var remarks = formValues['remarks'];
		
		return remarks;
		
	}
});