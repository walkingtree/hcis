Ext.namespace("OPD.issueReceipt");

OPD.issueReceipt.IssueReceipt = Ext.extend(Ext.Panel,{
	labelAlign : 'left',
	border : false,
	frame:false,
	monitorValid : true,
	initComponent : function(){
	
		this.issueReceipPanel = null;
		
		this.issueReceiptPnl = new Ext.Panel();
	
		this.patientIdNbrField = new Ext.form.NumberField({
			name : 'patientId',
			anchor : '98%',
			fieldLabel :msg.patientid,
			emptyText : msg.patientEmptyText,
			allowBlank : false,
			required : true
		}); 		
		
		this.getOpenRcvblBtn = new Ext.Button({
	        text: msg.getOpenRcvbl,
	        scope : this,
	        iconCls : 'search_btn',
	        disabled : true
		});
		
        this.getOpenRcvblBtn.on('click',function(){
        	var patientId = this.patientIdNbrField.getValue();
        	PatientManager.getPatientLiteBM( patientId,{
        			callback: function( patientLiteBM ){
        				if(!Ext.isEmpty(patientLiteBM)){
        					this.issueReceipPanel = new OPD.issueReceipt.ReceivablesDetails( { patientId:patientId } );
        	
				        	 this.issueReceipPanel.paymentModePnl.amountNumberField.on("blur",function(comp ){
					        	if(!Ext.isEmpty(comp.getValue()) ){
					        		this.issueReceipPanel.getCreateReceiptBtn().enable();
					        	}
					        	else {
					        		this.issueReceipPanel.getCreateReceiptBtn().disable();
					        	}
					        },this);
				        	 if( Ext.isEmpty(this.issueReceiptPnl.items) ){
					        	this.issueReceiptPnl.removeAll();
					        	this.issueReceiptPnl.add(this.issueReceipPanel);
					        	this.issueReceiptPnl.doLayout();
					        	this.add( this.issueReceiptPnl );
				        	 }else{
				        		 this.issueReceiptPnl.removeAll();
						        	this.issueReceiptPnl.add(this.issueReceipPanel);
						        	this.issueReceiptPnl.doLayout();
				        	 }
				        	this.doLayout();
				        	
				        	this.issueReceipPanel.getResetBtn().on('click',function(){
				        		this.remove(this.issueReceipPanel);
				        		this.getOpenRcvblBtn.disable();
				        		this.patientIdNbrField.reset();
				        	},this);
				        	
				        	this.issueReceipPanel.getCreateReceiptBtn().on('click',function(){
				        		var patientId = this.patientIdNbrField.getValue();
				        		var values = this.issueReceipPanel.getForm().getValues();
				        		var amount = parseFloat( values['PayAmt'] );
				        		var attributeList = this.getMap( values );
//				        		var gridPanel = this.issueReceipPanel.getGridPanel().gridPnl;
//				        		var records = gridPanel.getSelectionModel().getSelections();
//				        		var createdBy = getAuthorizedUserInfo().userId;
//				        		var invoiceId;
//				        		if( gridPanel.getSelectionModel().hasSelection() ){
//				        			invoiceId = records[0].data.receivable;
//				        		}else{
//				        			Ext.Msg.show({
//									   msg: 'Please select atleast one receivable and retry..!',
//									   buttons: Ext.Msg.OK,
//									   fn: function(){},
//									   icon: Ext.MessageBox.ERROR
//									});
//									return;
//				        		}
				        		var createdBy = getAuthorizedUserInfo().userId;
				        		var invoiceId = null;
								AccountantManager.createReceipt( patientId, amount, 
																 attributeList,
																 createdBy,
																 invoiceId,{
									callback: function(){
										this.patientIdNbrField.reset();
										Ext.destroy( this.issueReceipPanel );
				        				this.getOpenRcvblBtn.disable();
				        				
				        				PatientManager.generateRecieptSlip(patientId, amount,
				        						{callback:function(reportUrl){
				        							window,open(reportUrl);
				        						},scope:this});
									},
									scope:this
								});
				        	},this);
        					
				        		var patientName = patientLiteBM.fullName;
								var accountNumber = patientLiteBM.businessPartnerId;
								
				        		this.issueReceipPanel.detailsPanel.getAccountNameValueLbl().setText( accountNumber ); 
								this.issueReceipPanel.detailsPanel.getPatientNameValueLbl().setText( patientName );
        				}
								
        			},
					scope:this
    		});
        	
        },this);
        
        this.patientIdNbrField.on('blur',function(thisField){
        	if(!Ext.isEmpty(thisField.getValue())){
        		this.getOpenRcvblBtn.enable();
        	}
        	else {
        		this.getOpenRcvblBtn.disable();
        		this.remove(this.issueReceipPanel);
        	}
        },this);
        
   		Ext.applyIf(this, {
   			items : [
   				{
   					layout : 'column',
   					items : [
   						{
   							columnWidth : .40,
   							layout : 'form',
   							labelWidth : 70,
   							items : this.patientIdNbrField
   						},
   						{
   							columnWidth :.2,
   							items : this.getOpenRcvblBtn
   						}
   					]
   				}
   			]
   		});  
   		
        OPD.issueReceipt.IssueReceipt.superclass.initComponent.apply(this, arguments);
        
	},
	getPanel :function(){
		return this;
	},
	loadData: function( patientId ){
        	
        		PatientManager.getPatientLiteBM( patientId,{
        			callback: function( patientLiteBM ){
						var patientName = patientLiteBM.fullName;
						var accountNumber = patientLiteBM.businessPartnerId;
						
						 this.patientIdNbrField.setValue( patientId );
						this.issueReceipPanel = new OPD.issueReceipt.ReceivablesDetails( { patientId:patientId } );
				        	
						 this.issueReceipPanel.paymentModePnl.amountNumberField.on("blur",function(comp ){
					        	if(!Ext.isEmpty(comp.getValue()) ){
					        		this.issueReceipPanel.getCreateReceiptBtn().enable();
					        	}
					        	else {
					        		this.issueReceipPanel.getCreateReceiptBtn().disable();
					        	}
					        },this);
				        	
					        this.getReceivableGridPanel().setHeight( 150 );
					        
				        	this.issueReceiptPnl.removeAll();
				        	this.issueReceiptPnl.add(this.issueReceipPanel);
				        	this.issueReceiptPnl.doLayout();
				        	this.add( this.issueReceiptPnl );
				        	this.doLayout();
				        	
				        	
				        	this.issueReceipPanel.getResetBtn().on('click',function(){
				        		//this.remove(this.issueReceipPanel);
				        		this.getOpenRcvblBtn.disable();
//				        		this.patientIdNbrField.setValue("");
				        		this.issueReceipPanel.paymentModePnl.amountNumberField.setValue("");
				        		
				        	},this);
				        	
				        	this.issueReceipPanel.getCreateReceiptBtn().on('click',function(){
				        		var patientId = this.patientIdNbrField.getValue();
				        		var values = this.issueReceipPanel.getForm().getValues();
				        		var amount = parseFloat( values['PayAmt'] );
				        		var attributeList = this.getMap( values );
//				        		var gridPanel = this.issueReceipPanel.getGridPanel();
//				        		var records = gridPanel.getSelectionModel().getSelections();
//				        		var createdBy = getAuthorizedUserInfo().userId;
//				        		var invoiceId;
//				        		if( gridPanel.getSelectionModel().hasSelections() ){
//				        			invoiceId = records[0].data.receivable;
//				        		}else{
//				        			Ext.Msg.show({
//									   msg: 'Please select atleast one receivable and retry..!',
//									   buttons: Ext.Msg.OK,
//									   fn: function(){},
//									   icon: Ext.MessageBox.ERROR
//									});
//				        		}
				        		var createdBy = getAuthorizedUserInfo().userId;
				        		var invoiceId = null ;
								AccountantManager.createReceipt( patientId,amount,
																 attributeList,
																 createdBy,
																 invoiceId,{
									callback: function(){
								
									PatientManager.generateRecieptSlip(patientId, amount,
			        						{callback:function(reportUrl){
			        							window.open(reportUrl);
			        							Ext.ux.event.Broadcast.publish('closeReceiptWindow');
			        						},scope:this});
									},
									scope:this
								});
				        	},this);
				        	
						this.issueReceipPanel.detailsPanel.getAccountNameValueLbl().setText( accountNumber ); 
						this.issueReceipPanel.detailsPanel.getPatientNameValueLbl().setText( patientName );
						
						this.getOpenReceivablesBtn().hide();
				},
				scope:this
        		});
		},
		getOpenReceivablesBtn: function(){
			return this.getOpenRcvblBtn;
		},
		getMap: function( map ){
			var combo = this.issueReceipPanel.getPaymentModePanel().getPaymentModeCbx();
			var mapArray = {};
			mapArray.TenderType = map.TenderType;
			
            if (combo.getValue() == 'K') {
				mapArray.CheckNo= map.CheckNo;
				mapArray.A_Name= map.A_Name;
				mapArray.AccountNo= map.AccountNo;
				mapArray.Micr= map.Micr;
			}
            if (combo.getValue() == 'C') {
				mapArray.CreditCardType= map.CreditCardType;
				mapArray.CreditCardNumber= map.CreditCardNumber;
				mapArray.CreditCardExpMM= map.CreditCardExpMM;
				mapArray.CreditCardExpYY= map.CreditCardExpYY;
				mapArray.A_Name= map.A_Name1;
				mapArray.TrxType= map.TrxType;
				mapArray.CreditCardVV= map.CreditCardVV;
			}
			
			return mapArray;
		},
		getReceivableGridPanel: function(){
			return this.issueReceipPanel.getGridPanel().gridPnl;
		},
		remove : function(){
			this.issueReceiptPnl.removeAll();
        	this.issueReceiptPnl.doLayout();
//        	this.doLayout();
		}
});

