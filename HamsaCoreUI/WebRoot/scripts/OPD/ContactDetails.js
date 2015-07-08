Ext.namespace("OPD");

OPD.ContactDetails = Ext.extend(Object, {
	constructor : function(config) {

		if (Ext.isEmpty(config)) {
			config = {}
		}
		Ext.apply(this, config);

		var mainThis = this;

		var title = '' + config.addressType + ' contact details';

		this.stateStoreForCountry = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getStateWithCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, Ext.data.Record.create([
							  {name: "code", type: "string"},
							  {name: "description", type: "string"}
							])),
		    remoteSort: true
	 	 });

		this.stateCombo =  new Ext.form.ComboBox({
			hiddenName :'state',
			store : this.stateStoreForCountry,
			mode :'local',
			disabled : (Ext.isEmpty(config.selectedCountryCode))  ? true : false,  
			displayField :'description',
			valueField :'code',
			triggerAction :'all',
			fieldLabel :msg.state,
			forceSelection :true,
			anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor,
			forceSelection :true,
			value: config.selectedStateCode
		});

		this.countryCombo =  new Ext.form.ComboBox({
			hiddenName :'country',
			store : loadCountryCbx(),
			mode :'local',
			displayField :'description',
			forceSelection :true,
			valueField :'code',
			triggerAction :'all',
			fieldLabel :msg.country,
			anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor,
			forceSelection :true,
			value: config.selectedCountryCode,
			listeners : {
				select : function(comp, record, index) {
					if(record.data.code == null){
						mainThis.stateCombo.disable();
					} else {
						mainThis.stateCombo.enable();
						mainThis.stateCombo.clearValue();
					}
					if (this.stateStoreForCountry.getTotalCount() > 0) {
						this.stateStoreForCountry.removeAll();
					}
					this.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[record.data.code]});
					
				},
				blur : function(comp) {
					if(Ext.isEmpty(comp.getRawValue())){
						mainThis.stateCombo.disable();
					} else {
						mainThis.stateCombo.enable();
					}
				},
				scope : this
			}
		});
		
		if( config.addressType == 'current' || config.addressType == 'contact'){
			setCbxDefaultValue( this.countryCombo );
			this.stateCombo.enable();
			this.stateCombo.getStore().load({params:{start:0, limit:9999}, arg:['208']});
			
		}	

		var ttl=null;
		
		this.contactDetailsPanel = new Ext.Panel( {
			layout :'form',
			frame :true,
			border :true,
			width :'100%',
			autoHeight :true,
			autoScroll :true,
			bodyStyle :'padding:2px 2px 0',
			title :(config.isFromEntity==true?ttl:(config.addressType=='contact'?'contact details':title.charAt(0).toUpperCase() + title.slice(1))),
			items : [
					{
						xtype :'textfield',
						value :config.selectedStreet,
						name :'placestreet',
						fieldLabel :msg.placestreet,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					this.countryCombo,
					this.stateCombo,
					{
						xtype :'textfield',
						value :config.selectedCity,
						name :'citydistrict',
						fieldLabel :msg.citydistrict,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'numberfield',
						value :config.selectedPincode,
						name :'pincode',
						fieldLabel :msg.pincode,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'textfield',
						hidden :(config.addressType == 'current'
							|| config.addressType == 'permanent' ? (config.addressType=='contact'?true:false)
							: true),
					hideLabel :(config.addressType == 'current'
							|| config.addressType == 'permanent' ? false
							: true),
						value :config.selectedStayDuration,
						name :'durationofstay',
						fieldLabel :msg.durationofstay,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'wtcphonefield',
						hidden :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: (config.addressType == 'contact'?false:true)),
						hideLabel :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: (config.addressType == 'contact'?false:true)),
						value :config.selectedPhoneNumber,
						name :'phonenumber',
						fieldLabel :msg.phonenumber,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'wtcphonefield',
						hidden :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: (config.addressType == 'contact'?false:true)),
						hideLabel :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: (config.addressType == 'contact'?false:true)),
						value :config.selectedMobileNumber,
						name :'mobilenumber',
						fieldLabel :msg.mobilenumber,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'wtcphonefield',
						hidden :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: true),
						hideLabel :(config.addressType == 'current'
								|| config.addressType == 'permanent' ? false
								: true),
						value :config.selectedFaxNumber,
						name :'faxnumber',
						fieldLabel :msg.faxnumber,
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					},
					{
						xtype :'textfield',
						hidden :(config.addressType == 'current' ? false
								: (config.addressType == 'contact'?false:true)),
						hideLabel :(config.addressType == 'current' ? false
								: (config.addressType == 'contact'?false:true)),
						value :config.selectedEmail,
						name :'emailaddress',
						fieldLabel :msg.emailAddress,
						vtype :'email',
						anchor :(Ext.isEmpty(config.anchor) && config.anchor == null)?'99%':config.anchor
					} ]
		});

		this.formPanel = new Ext.form.FormPanel( {
			border :false,
			items :this.contactDetailsPanel
		});

		if(config.mode === 'edit'){
			this.setData({state:config.selectedStateCode, country:config.selectedCountryCode});
		}

		Ext.ux.event.Broadcast.subscribe('doctorReset', function() {
			this.formPanel.getForm().reset();
		}, this, null, mainThis.getPanel());

		Ext.ux.event.Broadcast.subscribe('patientReset', function() {
			this.formPanel.getForm().reset();
		}, this, null, mainThis.getPanel());
	},

	getPanel : function() {
		return this.formPanel;
	},

	getData : function() {
		return this.formPanel.getForm().getValues();
	},

	// adding listener for state store if country having some value, then
	// manually we need to call the state store
	// in the load event we need to set the values to the form panel.
	loadListenerForStateStore : function(data) {
		this.formPanel.getForm().setValues(data);
	},

	setData : function(data) {
		if (!Ext.isEmpty(data.country)) {
			this.stateStoreForCountry.on("load", function() {
				this.formPanel.getForm().setValues(data);
				data = null;
			}, this);
			if (this.stateStoreForCountry.getTotalCount() > 0) {
				this.stateStoreForCountry.removeAll();
			}
			this.stateStoreForCountry.load( {
				params : {
					start :0,
					limit :9999
				},
				arg : [ data.country ]
			});
		} else {
			this.formPanel.getForm().setValues(data);
			this.stateCombo.disable();
		}
	},
	
	loadStateComboBeforeShow: function(countryCode){
		if(countryCode == null || countryCode == '')
			return;
		if (this.stateStoreForCountry.getTotalCount() > 0) {
			this.stateStoreForCountry.removeAll();
		}
		this.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[countryCode]});
	},
	getCountryCbx:function(){
		return this.countryCombo;
	},
	
	setContryCbxValue : function(code){
		this.countryCombo.setValue(code);
		this.countryCombo.getStore().on('load',function(){this.countryCombo.setValue(code);},this);
	},
	
	resetContactDetails : function(){
		this.formPanel.getForm().reset();
		if( this.addressType == 'current' ){
			setCbxDefaultValue( this.countryCombo );
		}
		if( !Ext.isEmpty( this.selectedCountryCode )){
			this.setData({state:this.selectedStateCode, country:this.selectedCountryCode});
		}
	}
	
	
});
