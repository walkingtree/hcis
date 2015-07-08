Ext.namespace("OT.booking");

OT.booking.OTBookingSearchPanel = Ext.extend(Ext.form.FormPanel, {
	layout : 'column',
	border : false,
	frame:true,
	autoHeight : true,
	labelWidth:105,
	width:'98%',
	monitorValid : true,
	
	initComponent : function() {

		this.widgets = new OT.booking.OTBookingWidgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		this.buttonsPanel.getSearchButton().disabled =true;
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid 
					&& this.widgets.getSurgeryCbx().isValid()  
				  && !Ext.isEmpty(this.widgets.getBookigFromTxf().getValue())){
				this.buttonsPanel.getSearchButton().enable();
			} else {
				this.buttonsPanel.getSearchButton().disable();
			}
		}, this);

		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:1,
	            	layout : 'form',
					items:[this.widgets.getSurgeryCbx()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
	            	style : 'padding-top : 10px',
					items:[this.widgets.getBookigFromTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
	            	style : 'padding-top : 10px',
					items:[this.widgets.getBookigToTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getOtCodeCbx()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getSurgeonCbx()]
			    },
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
		OT.booking.OTBookingSearchPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		var config= this.getForm().getValues();
		config.bookingFrom = this.widgets.getBookigFromTxf().getValue();
		config.bookingTo = this.widgets.getBookigToTxf().getValue();		
		
		return config;
	},
	
	getPanel : function(){
	     return this;
	},
	
	getReset : function(){
		return this.getForm().reset();
	}
	
	
});

