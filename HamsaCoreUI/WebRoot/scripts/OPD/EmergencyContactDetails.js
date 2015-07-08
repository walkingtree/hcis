/**
 * @author Vinay Kurudi
 */

Ext.namespace("OPD");

OPD.EmergencyContactDetails = Ext.extend(Object,{
	constructor : function(config) {
						
			if (Ext.isEmpty(config)) {
				config = {}
			}
			
			Ext.apply(this, config);

			this.emrgncyDetailsFormPanel = new Ext.Panel(
					{
						layout :'form',
						frame :true,
						border :true,
						width :'100%',
						autoHeight :true,
						autoScroll :true,
						bodyStyle :'padding:2px 2px 0',
						title :'Contact person details',
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
									anchor :'99%',
									forceSelection :true,
									value:config.selectedEmergencySalutationCode
								},
								{
									xtype :'textfield',
									name :'firstname',
									value :config.selectedEmergencyFirstName,
									allowBlank :!Ext.isEmpty(config.allowBlank) ? config.allowBlank: true,
									required :config.required,
									fieldLabel :msg.firstname,
									anchor :'99%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyMiddleName,
									name :'middlename',
									fieldLabel :msg.middlename,
									anchor :'99%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyLastName,
									name :'lastname',
									fieldLabel :msg.lastname,
									anchor :'99%'
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
									anchor :'99%',
									forceSelection :true,
									value:config.selectedEmergencyGenderCode
								},
								{
									xtype :'combo',
									name :'relationship',
									store :loadRelationshipCbx(),
									mode :'local',
									displayField :'description',
									valueField :'code',
									triggerAction :'all',
									fieldLabel :msg.relationship,
									anchor :'99%',
									forceSelection :true,
									value:config.selectedEmergencyRelationCode
								},
								{
									xtype :'wtcphonefield',
									value :config.selectedEmergencyPhoneNumber,
									name :'phonenumber',
									fieldLabel :msg.phonenumber,
									allowBlank :!Ext.isEmpty(config.allowBlank) ? config.allowBlank : true,
									required :config.required,
									anchor :'99%'
								},
								{
									xtype :'wtcphonefield',
									value :config.selectedEmergencyMobileNumber,
									name :'mobilenumber',
									fieldLabel :msg.mobilenumber,
									anchor :'99%'
								},
								{
									xtype :'wtcphonefield',
									name :'faxnumber',
									value :config.selectedEmergencyFaxNumber,
									fieldLabel :msg.faxnumber,
									anchor :'99%'
								},
								{
									xtype :'textfield',
									value :config.selectedEmergencyEmail,
									name :'emailaddress',
									fieldLabel :msg.emailAddress,
									allowBlank :!Ext.isEmpty(config.allowBlank) ? config.allowBlank : true,
									required :config.required,
									vtype :'email',
									anchor :'99%'
								} ]
					});

			this.formPanel = new Ext.form.FormPanel( {
				border :false,
				items :this.emrgncyDetailsFormPanel
			});

			Ext.ux.event.Broadcast.subscribe('doctorReset',
					function() {
						this.formPanel.getForm().reset();
					}, this, null, this.getPanel());

			Ext.ux.event.Broadcast.subscribe('patientReset',
					function() {
						this.formPanel.getForm().reset();
					}, this, null, this.getPanel());
		},
		
		getPanel : function() {
			return this.formPanel;
		},
		
		getData : function() {
			return this.formPanel.getForm().getValues();
		},
		
		setData : function(data) {
			this.formPanel.getForm().setValues(data);
		},
		resetEmergencyContactDetailPanel : function(){
			this.formPanel.getForm().reset();
		}
		
		
	});
