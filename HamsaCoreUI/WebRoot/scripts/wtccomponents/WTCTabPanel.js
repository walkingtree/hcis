Ext.namespace("wtccomponents");

wtccomponents.WTCTabPanel =  Ext.extend(Ext.TabPanel, {
	frame : true,
	initComponent : function(){
		wtccomponents.WTCTabPanel.superclass.initComponent.apply(this , arguments);
	},
	add : function(comp){
        this.initItems();
        this.setHeight(comp.height);
        var args = arguments.length > 1;
        if(args || Ext.isArray(comp)){
            var result = [];
            Ext.each(args ? arguments : comp, function(c){
                result.push(this.add(c));
            }, this);
            return result;
        }
        var c = this.lookupComponent(this.applyDefaults(comp));
        var index = this.items.length;
        if(this.fireEvent('beforeadd', this, c, index) !== false && this.onBeforeAdd(c) !== false){
            this.items.add(c);
            this.onAdd(c);
            this.fireEvent('add', this, c, index);
        }
        return c;
	},
	
	setHeight : function( height ){
		this.height = height;
	}

});