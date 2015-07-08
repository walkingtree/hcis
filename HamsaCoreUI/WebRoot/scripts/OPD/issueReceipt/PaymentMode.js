Ext.namespace("OPD.issueReceipt");

OPD.issueReceipt.PaymentMode = Ext.extend(Ext.Panel ,{
	initComponent: function(config){
		if(Ext.isEmpty(config)){
			config = { }
		}
		this.cashPymtDetailPanel = new Ext.Panel({
				layout: 'form'
			});
		
		this.amountNumberField = new Ext.form.NumberField({
			name : 'PayAmt',
			anchor : '89%',
			fieldLabel : 'Amount',
			decimalPrecision : 2,
			allowBlank : false,
			required : true
        });
		
		this.chequePymtDetailPanel = new Ext.Panel({
			layout: 'column',
			width:'90%',
			labelAlign : 'left',
			labelWidth : 105,
			defaults:{columnWidth:.5},
			items: [
				{
					layout:'form',
					items:{
						xtype: 'textfield',
						fieldLabel: msg.checkNo,
						name: 'CheckNo',
						anchor : '98%'
					}
					
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
						fieldLabel: msg.accountName,
						name: 'A_Name',
						anchor : '98%'
					}
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
						fieldLabel: msg.accountNo,
						name: 'AccountNo',
						anchor : '98%'
					}
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
						fieldLabel: msg.micr,
						name: 'Micr',
						anchor : '98%'
					}
				}
			]
		});
		
		this.creditCardPymtDetailPanel = new Ext.Panel({
			layout: 'column',
			labelAlign : 'left',
			width:'90%',
			labelWidth : 105,
			defaults:{columnWidth:.5},
			items: [
				{
					layout:'form',
					items:{
						xtype: 'combo',
				        fieldLabel: msg.creditCardType,
				        store:loadCreditCardTypeCbx(),
						mode:'local',
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
				        name: 'CreditCardType',
				        anchor : '98%'
					}
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
				        fieldLabel: msg.cardNumber,
				        name: 'CreditCardNumber',
				        anchor : '98%'
					}
				},
				{
					layout:'column',
					items:[
						{
							layout:'form',
							columnWidth:.75,
							items:{
								xtype: 'combo',
								store:loadMonths(),
								displayField:'description',
								triggerAction:'all',
								mode:'local',
						        fieldLabel: msg.expiryMonth,
						        name: 'CreditCardExpMM',
						        anchor : '98%'
							}
						},
						{
							layout:'form',
							columnWidth:.238,
							labelWidth:.1,
							items:{
								xtype: 'combo',
								store:loadYears(),
								mode:'local',
								displayField:'description',
								triggerAction:'all',
						        name: 'CreditCardExpYY',
						        anchor : '98%'
							}
						}
					]
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
				        fieldLabel: msg.accountName,
				        name: 'A_Name1',
				        anchor : '98%'
					}
				},
				{
					layout:'form',
					items:{
						xtype: 'combo',
						store:loadTransactionTypeCbx(),
						mode:'local',
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
				        fieldLabel: msg.transactionName,
				        name: 'TrxType',
				        anchor : '98%'
					}
				},
				{
					layout:'form',
					items:{
						xtype: 'textfield',
				        fieldLabel: msg.verificationCode,
				        name: 'CreditCardVV',
				        anchor : '98%'
					}
				}
			]	
		});
		
		this.pymtDetailPanel = new Ext.Panel({
			id: 'CreatePayment-Detail-ID',
			layout: 'card',
			region: 'center',
			labelWidth : 105,
			activeItem : 0,
			items: [
				this.cashPymtDetailPanel,
				this.chequePymtDetailPanel,
				this.creditCardPymtDetailPanel
			]
		});
		
		this.paymentModeCbx = new Ext.form.ComboBox({
			xtype: 'combo',
			fieldLabel: 'Payment mode',
			store: loadPaymentModeCbx(),
			hiddenName: 'TenderType',
			anchor: '98%',
			mode: 'local',
			editable : true,
			displayField: 'description',
			valueField : 'code',
			triggerAction: 'all',
			listeners: {
                select: {
                    fn: function(combo, value){
                    	selectedPymtMethod = value.data;
                        var pymtDetailPanel = this.pymtDetailPanel;
                        
                        if (combo.value == 'X') {
							pymtDetailPanel.layout.setActiveItem(0);
						}
                        if (combo.value == 'K') {
							pymtDetailPanel.layout.setActiveItem(1);
						}
                        if (combo.value == 'C') {
							pymtDetailPanel.layout.setActiveItem(2);
						}
                    },
                    scope : this
                }
            }
			
		});
		
		 this.pymtDetailFieldSet = new Ext.Panel({
			collapsible: false,
			layout: 'column',
			labelWidth : 105,
			height: 'auto',
			width: '98%',
			items: [
				{
					layout : 'form',
					columnWidth : 0.45,
					items:this.paymentModeCbx
				},
				{
					layout : 'form',
					columnWidth : 0.5,
					items : this.amountNumberField
				},
				this.pymtDetailPanel
			]	
		});
		
   		Ext.applyIf(this, {
   			items : [
				this.pymtDetailFieldSet
   			]
   		});  
		
		
      	OPD.issueReceipt.PaymentMode.superclass.initComponent.apply(this, arguments);
		
	},
	getPanel : function(){
		return this;
	},
	getPaymentModeCbx : function(){
		return this.paymentModeCbx;
	}
});	
