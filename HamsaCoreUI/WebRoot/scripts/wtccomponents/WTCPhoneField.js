/**
 * @author sandeep
 */

Ext.form.VTypes["phoneVal"] = /^([\(\)\+\-0-9A-Za-z])+$/;
Ext.form.VTypes["phoneMask"] = /[\(\)\+\-0-9A-Za-z]/;
Ext.form.VTypes["phoneText"] = 'Not a valid phone number.';
Ext.form.VTypes["phone"]=function(v){
	return Ext.form.VTypes["phoneVal"].test(v);
}

Ext.form.WTCPhoneField = Ext.extend(Ext.form.TextField, {
	constructor: function(config) {
		config.maskRe = /^([\(\)\+\-0-9A-Za-z\' '])/;
		config.vtype = "phone";
        Ext.form.WTCPhoneField.superclass.constructor.call(this, config);
	}
});

Ext.reg('wtcphonefield', Ext.form.WTCPhoneField);