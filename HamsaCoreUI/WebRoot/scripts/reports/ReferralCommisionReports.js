Ext.namespace("administration.referralManagement");

administration.referralManagement.ReferralCommisionReports = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:false,
	monitorValid : true,
	
	initComponent : function() {

		this.widgets = new administration.referralManagement.Widgets();
		this.referralsListGridPanel = new administration.referralManagement.ReferralsListGridPanel();
		
		this.referralsListGridPanel.getToolbar().hide();
		
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		this.reportBtn = new Ext.Button({
	        text: 'Generate report',
	        scope : this,
	        disabled:true,
	        width:50,
	        handler:function(){
	        	this.reportButtonClicked();
	        }
        });
		
		this.generateReportPanel = new Ext.Panel({
			layout:'column',
			frame:false,
			style:'padding-top:10px',
			defaults:{columnWidth:.25},
			items:[
				{layout:'form',items:this.widgets.getFromDateTimeField()},
				{layout:'form',items:this.widgets.getToDateTimeField()},
				{layout:'form',columnWidth:.1,items:this.reportBtn}
			]
		});
		
		this.buttonsPanel.getSearchButton().on('click',function(){
			this.searchBtnClicked();
		},this);
		
		this.buttonsPanel.getResetButtton().on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.on('clientvalidation',function(thisForm , isValid){
			if(isValid && (this.referralsListGridPanel.getGridPanel().getSelectionModel().getCount() == 1)){
				this.reportBtn.enable();	
			}
			else{
				this.reportBtn.disable();
			}
		},this);
		
		this.widgets.getReferralTypeComboForSearch().on('select',function(thisCombo , record ,index){
				this.loadReferralNameForType(thisCombo , record ,index);
		},this);
		
		Ext.applyIf(this, {
            items: [
            	{
					columnWidth:0.50,
	            	layout : 'form',
					items:[this.widgets.getReferralTypeComboForSearch()]
            		
			    },{
					columnWidth:0.50,
	            	layout : 'form',
					items:[this.widgets.getUniqueNameTxfForSearch()]
			    
			    },{
			    	columnWidth : 1,
			    	layout : 'form',
			    	items : [this.buttonsPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.referralsListGridPanel]
			    },{
			    	columnWidth : 1,
			    	layout : 'form',
			    	items : [this.generateReportPanel]
			    }
			]            
        });
        administration.referralManagement.ReferralCommisionReports.superclass.initComponent.apply(this, arguments);
	},
	
	loadReferralNameForType : function(thisCombo , record ,index){
			var referralType = record.data.code;
			referralNameStore.load({params:{start:0, limit:99}, arg:[referralType]});
	},
	
	reportButtonClicked : function(){
		var reportCriteria = this.getForm().getValues();
		var selectedRow = this.referralsListGridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		Ext.Msg.show({
			msg : 'Referral Type : ' + selectedRowData.referralTypeDesc + ', Referral Name :' + selectedRowData.uniqueName+ ', From Date :' + reportCriteria['fromDateTimeField']
				+ ', To date :' + reportCriteria['toDateTimeField'],
			buttons : Ext.Msg.OK	
		});
		this.widgets.getFromDateTimeField().setMaxValue(null);
		this.widgets.getToDateTimeField().setMinValue(null);
	},
	
	searchBtnClicked : function(){
		var searchCriteria = this.getForm().getValues();
		var referralLightBM = {
   			referralType :Ext.isEmpty(searchCriteria['referralTypeForSearch'])? null :{code : searchCriteria['referralTypeForSearch']},
   			referralName : searchCriteria['referralNameForSearch']
   		}
   		this.referralsListGridPanel.loadData(referralLightBM);
	},
	
	resetBtnClicked : function(){
		this.getForm().reset();
		this.referralsListGridPanel.loadData();
	}
	
});

Ext.reg('refferals-commision-reports-panel', administration.referralManagement.ReferralCommisionReports);
