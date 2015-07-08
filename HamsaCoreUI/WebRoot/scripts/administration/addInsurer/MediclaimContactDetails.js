Ext.namespace("administration.addInsurer");

administration.addInsurer.MediclaimContactDetails = Ext.extend(Object,{
	constructor : function(config) {
						
			if (Ext.isEmpty(config)) {
				config = {}
			}
			
			Ext.apply(this, config);
			
			this.contactDetailsFormPanel = new Ext.Panel(
					{
						layout :'form',
						frame :false,
						border :false,
						width :'100%',
						autoHeight :true,
						autoScroll :true,
						items : [
								{
									xtype :'combo',
									name :'title',
									store :loadTitleCbx(),
									mode :'local',
									displayField :'description',
									forceSelection :true,
									triggerAction :'all',
									valueField :'code',
									fieldLabel :msg.title,
									anchor :'23%',
									forceSelection :true
								},
								{
									xtype :'textfield',
									name :'firstname',
									value :config.selectedEmergencyFirstName,
									allowBlank :false,
									required :true,
									fieldLabel :msg.firstname,
									anchor :'40%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyMiddleName,
									name :'middlename',
									fieldLabel :msg.middlename,
									anchor :'40%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyLastName,
									name :'lastname',
									fieldLabel :msg.lastname,
									anchor :'40%'
								},
								{
									xtype :'combo',
									name :'gender',
									store :loadGenderCbx(),
									forceSelection :true,
									mode :'local',
									displayField :'description',
									valueField :'code',
									triggerAction :'all',
									fieldLabel :msg.sex,
									anchor :'23%',
									forceSelection :true
								},
								{
									xtype :'wtcphonefield',
									value :config.selectedEmergencyPhoneNumber,
									name :'phonenumber',
									fieldLabel :msg.phonenumber,
									allowBlank :false,
									required :true,
									anchor :'30%'
								},
								{
									xtype :'wtcphonefield',
									value :config.selectedEmergencyMobileNumber,
									name :'mobilenumber',
									fieldLabel :msg.mobilenumber,
									anchor :'30%'
								},
								{
									xtype :'wtcphonefield',
									name :'faxnumber',
									value :config.selectedEmergencyFaxNumber,
									fieldLabel :msg.faxnumber,
									anchor :'30%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyEmail,
									name :'emailaddress',
									fieldLabel :msg.emailAddress,
									allowBlank :false,
									required :true,
									vtype :'email',
									anchor :'40%'
								} ]
					});

			this.formPanel = new Ext.form.FormPanel( {
				border :false,
				items :this.contactDetailsFormPanel
			});
		},
		
		getPanel : function() {
			return this.formPanel;
		},
		
		getData : function() {
			return this.formPanel.getForm().getValues();
		},
		
		setData : function(data) {
			this.formPanel.getForm().setValues(data);
		}
		
	});
