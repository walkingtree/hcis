Ext.namespace("OT.register");

OT.register.OTRegisterSearchPanel = Ext.extend(Ext.form.FormPanel, {
	layout : 'column',
	border : false,
	frame:true,
	autoHeight : true,
	labelWidth:105,
	width:'98%',
	
	initComponent : function() {

		this.widgets = new OT.register.OTRegisterWidgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getPatientNameTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getDoctorNameTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getOtCodeCbx()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getSurgeryCbx()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getBookigFromTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getBookigToTxf()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getReferenceTypeCbx()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getReferenceNumberCbx()]
			    },{
					columnWidth:0.33,
	            	layout : 'form',
					items:[this.widgets.getOtBookingNoTxf()]
			    },
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
		
		OT.register.OTRegisterSearchPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		return this.getForm().getValues();
	},
	
	getPanel : function(){
	     return this;
	},
	
	reset : function(){
		return this.getForm().reset();
	}
	
	
});

