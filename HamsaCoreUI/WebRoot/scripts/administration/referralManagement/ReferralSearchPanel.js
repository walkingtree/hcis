Ext.namespace("administration.referralManagement");

administration.referralManagement.ReferralSearchPanel = Ext.extend(Ext.form.FormPanel, {
	title : 'Search',
	border:false,
	layout : 'column',
	border : false,
	frame:true,
	collapsed : false,
	collapsible: true,
	titleCollapse : true,
	height:125,
	labelWidth:90,
	width:'97%',	
	
	initComponent : function() {

		this.widgets = new administration.referralManagement.Widgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getReferralTypeCombo()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getUniqueNameTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
	            	labelWidth:55,
					items:[this.widgets.getAddressTxf()]
				},{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getCityTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getCountryCombo()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
	            	labelWidth:55,
					items:[this.widgets.getStateCombo()]
				},{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getPinCodeNbrFld()]
				},{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getPreferredTimeTxf()]
			    },{
	            	layout : 'form',
					columnWidth:0.33,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
        administration.referralManagement.ReferralSearchPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		return this.getForm().getValues();
	},
	getReset : function(){
		return this.getForm().reset();
	}
	
	
});

Ext.reg('refferal-search-panel', administration.referralManagement.ReferralSearchPanel);
