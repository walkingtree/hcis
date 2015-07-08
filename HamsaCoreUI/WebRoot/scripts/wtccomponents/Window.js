Ext.namespace("Ext.ux");

Ext.ux.Window = Ext.extend(Ext.Window, {
	constructor: function(config) {
		
		config.border = false;
		config.autoHeight = true;
		config.layout = 'fit';
		config.closeAction = 'hide';
		config.modal = true;

        Ext.ux.Window.superclass.constructor.call(this, config);
	}
});

Ext.reg('wtcwindow', Ext.ux.Window);