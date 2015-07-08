Ext.override(Ext.grid.Column, {
    renderer : function(value){
        if(Ext.isDate(value)){
            return value.dateFormat('d/m/Y');
        }
        return value;
    }
});

Ext.override(Ext.layout.FormLayout, {
	getTemplateArgs: function(field) {
		var noLabelSep = !field.fieldLabel || field.hideLabel;
		var labelSep = (typeof field.labelSeparator == 'undefined' ? this.labelSeparator : field.labelSeparator);
		if (field.required) labelSep += '<span style="color: rgb(255, 0, 0); padding-left: 2px;">*</span>';
			return {
				id: field.id,
				label: field.fieldLabel,
				labelStyle: field.labelStyle||this.labelStyle||'',
				elementStyle: this.elementStyle||'',
				labelSeparator: noLabelSep ? '' : labelSep,
				itemCls: (field.itemCls||this.container.itemCls||'') + (field.hideLabel ? ' x-hide-label' : ''),
				clearCls: field.clearCls || 'x-form-clear-left'
			};
	}
});


Ext.ux.ComboBox = function(config){
	if (!config.hiddenName){
		config.hiddenName = config.name;
	}
    Ext.ux.ComboBox.superclass.constructor.call(this, config);
}
Ext.extend(Ext.ux.ComboBox,Ext.form.ComboBox,{});
Ext.reg('combo',Ext.ux.ComboBox);

Ext.override(Ext.form.RadioGroup, {afterRender:function () {
	var group = this;
	this.items.each(function (field) {
// Listen for 'check' event on each child item
		field.on("check", function (self, checked) {

// if checkbox is checked, then fire 'change' event on RadioGroup container
			if (checked) {
			group.fireEvent("change", group, self.getRawValue());
// Note, oldValue (third parameter in 'change' event listener) is not passed,
			}
// because is not easy to get it
		});
	});
	Ext.form.RadioGroup.superclass.afterRender.call(this);
}});

//vinay added for setting radio group values
Ext.override(Ext.form.RadioGroup, {
  getName: function() {
    return this.items.first().getName();
  },

  getValue: function() {
    var v;

    this.items.each(function(item) {
      v = item.getRawValue();
      return !item.getValue();
    });

    return v;
  },

  setValue: function(v) {
    this.items.each(function(item) {
      item.setValue(item.getRawValue() == v);
    });
  }
});

// vinay added for setting values to the radio
Ext.override(Ext.form.Radio, {   
    // private
    toggleValue : function() {
        if(!this.checked){
            // notify owning group that value changed
            if (this.ownerGroup) {
                this.ownerGroup.setValue(this.inputValue);
            }
            else {
                var els = this.getParent().select('input[name=' + this.el.dom.name + ']');
                els.each(function(el){
                    if (el.dom.id == this.id) {
                        this.setValue(true);
                    }
                    else {
                        Ext.getCmp(el.dom.id).setValue(false);
                    }
                }, this);				
            }
        }
    }
});

// for setting multiple values for field
Ext.override(Ext.ux.form.LovCombo,{
    beforeBlur : function(){
        var val = this.hiddenField ? this.hiddenField.value : this.getRawValue();
        if(this.forceSelection){
            if(val.length > 0 && val != this.emptyText){
               this.el.dom.value = Ext.isDefined(this.lastSelectionText) ? this.lastSelectionText : '';
                this.applyEmptyText();
            }else{
                this.clearValue();
            }
        }else{
            var rec = this.findRecord(this.displayField, val);
            if(rec){
                val = rec.get(this.valueField || this.displayField);
            }
            this.setValue(val);
        }
    }
});