Ext.namespace("wtccomponents");
wtccomponents.WTCComboBox =  Ext.extend(Ext.form.ComboBox, {
	displayField : 'description',
	valueField : 'code',
	triggerAction : 'all',
	forceSelection : true,
	mode:'local',
	anchor: '98%',
	initComponent : function(){
		wtccomponents.WTCComboBox.superclass.initComponent.call( this, arguments );
	}
});


