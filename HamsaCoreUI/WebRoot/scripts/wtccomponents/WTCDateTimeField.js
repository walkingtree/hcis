Ext.form.WTCDateTimeField = Ext.extend(Ext.form.DateField, {
	submitFormat:'d/m/Y g:i a',
	format:'d/m/Y g:i a',
	constructor: function(config) {
		
		config.altFormats = 'd/m/Y g:i a';
        Ext.form.WTCDateTimeField.superclass.constructor.call(this, config);
	}
	
    ,onRender:function() {

        // call parent
        Ext.form.WTCDateTimeField.superclass.onRender.apply(this, arguments);

        var name = this.name || this.el.dom.name;
        this.hiddenField = this.el.insertSibling({
             tag:'input'
            ,type:'hidden'
            ,name:name
            ,value:this.formatHiddenDate(this.parseDate(this.value))
        });
        this.hiddenName = name; // otherwise field is not found by BasicForm::findField
        this.el.dom.removeAttribute('name');
        this.el.on({
             keyup:{scope:this, fn:this.updateHidden}
            ,blur:{scope:this, fn:this.updateHidden}
        }, Ext.isIE ? 'after' : 'before');

        this.setValue = this.setValue.createSequence(this.updateHidden);

    } // eo function onRender

    ,onDisable: function(){
        // call parent
        Ext.form.WTCDateTimeField.superclass.onDisable.apply(this, arguments);
        if(this.hiddenField) {
            this.hiddenField.dom.setAttribute('disabled','disabled');
        }
    } // of function onDisable

    ,onEnable: function(){
        // call parent
        Ext.form.WTCDateTimeField.superclass.onEnable.apply(this, arguments);
        if(this.hiddenField) {
            this.hiddenField.dom.removeAttribute('disabled');
        }
    } // eo function onEnable

    ,formatHiddenDate : function(date){
        if(!Ext.isDate(date)) {
            return date;
        }
        if('timestamp' === this.submitFormat) {
            return date.getTime()/1000;
        }
        else {
            return Ext.util.Format.date(date, this.submitFormat);
        }
    }

    ,updateHidden:function() {
        this.hiddenField.dom.value = this.formatHiddenDate(this.getValue());
    } // eo function updateHidden
	
});

Ext.reg('wtcdatetimefield', Ext.form.WTCDateTimeField);