Ext.namespace("administration.referralManagement");

administration.referralManagement.ContactsPanel = Ext.extend(Ext.form.FieldSet, {
	title : "Contact details",
	border:true,
	layout : 'column',
	frame:false,
	labelAlign : 'left',
	width:'94%',
	
	initComponent : function() {

		this.widgets = new administration.referralManagement.Widgets();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAddressTxf()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getCityTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getCountryCombo()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getStateCombo()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPinCodeNbrFld()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPhoneNbrTxf()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getMobileNbrTxf()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getFaxNbrTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getEmailAddressTxf()]
			    }
			]            
        });
        administration.referralManagement.ContactsPanel.superclass.initComponent.apply(this, arguments);
	},
	getWidgets : function(){
		return this.widgets;
	}
});

Ext.reg('contacts-panel', administration.referralManagement.ContactsPanel);