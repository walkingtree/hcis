Ext.namespace("IPD.doctorOrderList");

IPD.doctorOrderList.DoctorOrderRemarksPanel = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
		this.okBtn = new Ext.Button({
			text: Ext.isEmpty(config.okBtnText) ? msg.ok : config.okBtnText,
	    	iconCls : 'accept-icon',
	    	scope: this,
	    	handler:function(){
	    		this.handleOkBtn(config);
	    	}
		});
		
		if(config.isRequired){
			this.okBtn.disable();
		}
		else{
			this.okBtn.enable();
		}

		this.cancelBtn = new Ext.Button({
			text: msg.close,
	    	iconCls : 'cross_icon',
	    	scope: this,
	    	handler:function(){
	    		if(config.mode == 'detailMode'){
	    			Ext.ux.event.Broadcast.publish('closeDoctorOrderDetailsRemarksWindow',false);
	    		}
	    		else {
	    			Ext.ux.event.Broadcast.publish('closeDoctorOrderRemarksWindow');
	    		}	
	    	}
		});

		
		this.remarksTxa = new Ext.form.TextArea({
			name:'remarks',
			allowBlank : config.mode == 'detailMode' || config.mode == 'infoMode' || config.isRequired == false ?true :false,
			required : config.mode == 'detailMode'|| config.mode == 'infoMode' || config.isRequired == false? false :true, 
			anchor:'98%'
		});
		
		
		this.panel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			frame:true,
			buttonAlign:'right',
			labelAlign :'left',
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
		
		this.panel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.okBtn.enable();
			} else {
				this.okBtn.disable();
			}
		}, this);
		
		if(config.mode == 'infoMode'){
			this.remarksTxa.setValue(config.remarks);
			this.okBtn.hide();
			this.cancelBtn.hide();
		}
	},
	getPanel : function() {
		return this.panel;
	},
	handleOkBtn : function( config ){
		var formValues = this.panel.getForm().getValues();
		var remarks = formValues['remarks'];
		if(config.mode == 'detailMode'){
			OrderManager.updateOrderDetailsStatus(config.orderNumber,config.SequenceNumber,config.SubSeqBumber,config.newStatus,config.personId,remarks,
			function( isOrderDetailUpdated ){
				if( isOrderDetailUpdated ){
					Ext.ux.event.Broadcast.publish('closeDoctorOrderDetailsRemarksWindow');
				}
				
			})
		}
		else{
			OrderManager.updateOrderStatus(	config.orderNumber, config.newStatusCode, config.personId, remarks,
												function( isOrderUpdated ){
													if( isOrderUpdated ){
														Ext.ux.event.Broadcast.publish('loadDoctorOrderGrid');
														Ext.ux.event.Broadcast.publish('closeDoctorOrderRemarksWindow');
													}										
												});
		}										
	}
});