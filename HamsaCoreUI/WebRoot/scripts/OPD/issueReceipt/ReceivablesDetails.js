Ext.namespace("OPD.issueReceipt");

OPD.issueReceipt.ReceivablesDetails = Ext.extend(Ext.form.FormPanel,{
	collapsible : false,
	collapsed :  false,
	frame : false,
	border : false,
	height : '100%',
	monitorValid : true,
	initComponent : function(){
		this.detailsPanel = new OPD.issueReceipt.BasicDetails();
		this.receiavbleGridPnl = new OPD.issueReceipt.ReceivablesGridPanel({patientId:this.initialConfig.patientId});
		this.paymentModePnl = new OPD.issueReceipt.PaymentMode();
		this.paymentModePnl.getPaymentModeCbx().setValue(msg.cash);

		this.createReceiptBtn = new Ext.Button({
	        text: msg.createReceipt,
	        scope : this,
	        iconCls : 'save_btn',
	        disabled:true
			
		});
		
		this.resetBtn =  new Ext.Button({
	        text: msg.reset,
	        scope : this,
	        iconCls : 'cancel_btn'
        });
		
		this.on('clientvalidation',function(thisForm, isValid){
			if(isValid ){
				this.createReceiptBtn.enable();
			}
			else{
				this.createReceiptBtn.disable();
			}
		},this);
        
   		Ext.applyIf(this, {
   			buttonAlign : 'right',
   			buttons : [this.createReceiptBtn,this.resetBtn],
   			items : [
   				this.detailsPanel.getPanel(),
   				this.receiavbleGridPnl.getPanel(),
   				{	
   					layout : 'column',
   					items : [
	   					{
	   						columnWidth : 1,
							layout : 'form',
							style:'padding:10px;',
							labelWidth : 50,
	   						items : this.paymentModePnl.getPanel()
	   					}
   					]
   					
   				}
   			]
   		});  
        OPD.issueReceipt.ReceivablesDetails.superclass.initComponent.apply(this, arguments);
	},
	getPanel :function(){
		return this;
	},
	
	getResetBtn : function(){
		return this.resetBtn;
	},
	getReceiptBtn:function(){
		return this.createReceiptBtn;
	},
	getCreateReceiptBtn : function(){
		return this.createReceiptBtn;
	},
	getPaymentModePanel: function(){
		return this.paymentModePnl;
	},
	getGridPanel : function(){
		return this.receiavbleGridPnl.getPanel();
	}
});

