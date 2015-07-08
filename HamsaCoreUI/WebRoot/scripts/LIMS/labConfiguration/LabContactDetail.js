Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.LabContactDetail = Ext.extend(Ext.form.FieldSet, {
	title : "Contact Detail",
	border: true,
	layout : 'form',
	frame:false,
	anchor:'100%',
	labelAlign : 'left',
	height : 270,
	width:'94%',
	
	initComponent : function() {

		this.widgets = new LIMS.labConfiguration.Widgets();
		Ext.applyIf(this, {
            items: [
				{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getStreetTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getLocalityTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getCityTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getCountryCbx()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getStateCbx()]
				},{
					columnWidth:0.067,
	            	layout : 'form',
					items:[this.widgets.getEmailIDTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getPhoneNumberTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getMobileNumberTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getFaxNumberTxf()]
				}
			]            
        });
       LIMS.labConfiguration.LabContactDetail.superclass.initComponent.apply(this, arguments);
	},
	getWidgets : function(){
		return this.widgets;
	},
	getLabContactDetails: function(){
		var contactdetails = {
				street:this.widgets.getStreetTxf().getValue(),
				locality:this.widgets.getLocalityTxf().getValue(),
				city:this.widgets.getCityTxf().getValue(),
				country:this.widgets.getCountryCbx().getRawValue(),
				state:this.widgets.getStateCbx().getRawValue(),
				emailID:this.widgets.getEmailIDTxf().getValue(),
				phoneNumber:this.widgets.getPhoneNumberTxf().getValue(),
				mobileNumber:this.widgets.getMobileNumberTxf().getValue(),
				faxNumber:this.widgets.getFaxNumberTxf().getValue()
		}
		return contactdetails;
	},
	resetData: function(){
		
		this.widgets.getStreetTxf().reset();
		this.widgets.getLocalityTxf().reset();
		this.widgets.getCityTxf().reset();
		this.widgets.getCountryCbx().reset();
		this.widgets.getStateCbx().reset();
		this.widgets.getEmailIDTxf().reset();
		this.widgets.getPhoneNumberTxf().reset();
		this.widgets.getMobileNumberTxf().reset();
		this.widgets.getFaxNumberTxf().reset();
	},
	setData: function( config ){
		if( !Ext.isEmpty( config )){
			this.widgets.getStreetTxf().setValue(config.street);
			this.widgets.getLocalityTxf().setValue(config.locality);
			this.widgets.getCityTxf().setValue(config.city);
			this.widgets.getCountryCbx().setValue(config.country);
			

			if(!Ext.isEmpty( config.country)){
				
				var record = {data:{code:config.country}};
				
				this.widgets.getCountryCbx().fireEvent('select',null,record);
			}
			
			
			this.widgets.getEmailIDTxf().setValue(config.emailID);
			this.widgets.getPhoneNumberTxf().setValue(config.phoneNumber);
			this.widgets.getMobileNumberTxf().setValue(config.mobileNumber);
			this.widgets.getFaxNumberTxf().setValue(config.faxNumber);
			
			if(!Ext.isEmpty( config.state)){
				this.widgets.getStateCbx().clearValue();
				this.widgets.getStateCbx().store.on('load',function(store,records,options){
					this.widgets.getStateCbx().setValue(config.state);
					config = {};//Bad bad.. (but need to clear the old config to prevent the resetting  of
								//state store on every combo reload )
				},this);
			}
		}
	}

});