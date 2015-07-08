Ext.namespace("IPD.managClaim");

IPD.manageClaim.ClaimStatusPanel = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
		this.okBtn = new Ext.Button({
			text: 'Ok',
	    	iconCls : 'accept-icon',
	    	scope: this,
	    	handler:function(){
	    		this.handleOkBtn(config);
	    	}
		});
		
		this.requestSeqNumberTxf = new Ext.form.TextField({
			fieldLabel:'Request sequence no.',
			name:'requestSequenceNbr',
			readOnly:true,
			anchor:'90%'
		});
		
		this.statusCodeTxf = new Ext.form.TextField({
			fieldLabel:'Status code',
			name:'claimStatusDesc',
			readOnly:true,
			anchor:'90%'
		});

		this.partialApprovalAmtNbf;
		
		if(config.claimStatusCd == configs.claimPartiallyApproved){
			this.partialApprovalAmtNbf = new Ext.form.NumberField({
				fieldLabel:'Approved amount (Rs.)',
				name:'approvedAmount',
				required:true,
				allowBlank:false,
				anchor:'90%'
			});
		}
		
		this.remarksTxa = new Ext.form.TextArea({
			fieldLabel:'Remarks',
			name:'remarks',
			anchor:'90%'
		});
		
		
		this.panel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			frame:true,
			buttonAlign:'center',
			monitorValid:true,
			defaults:{columnWidth:1,labelWidth:150},
			items: [
				{
					layout:'form',
					items:this.requestSeqNumberTxf
				},
				{
					layout:'form',
					items:this.statusCodeTxf
				},
				{
					layout:'form',
					items:this.partialApprovalAmtNbf
				},
				{
					layout:'form',
					items:this.remarksTxa
				}
			],
			buttons:[this.okBtn]
		});
		
		this.panel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.okBtn.enable();
			} else {
				this.okBtn.disable();
			}
		}, this);
	},
	getPanel : function() {
		return this.panel;
	},
	loadData : function(config){
		this.panel.getForm().setValues(config);
	},
	handleOkBtn : function(config){
		var formValues = this.panel.getForm().getValues();
		
		InsuranceManager.claimrequestActions(	parseInt(formValues.requestSequenceNbr),
												config.claimStatusCd,
												config.modifiedBy,
												formValues.remarks,
												formValues.approvedAmount
											);
		Ext.ux.event.Broadcast.publish('loadClaimGrid');
		Ext.ux.event.Broadcast.publish('closeClaimStatusWindow');										
	}
});