Ext.namespace("utils");

utils.ButtonsPanel = Ext.extend(Ext.Panel, {
	border : false,
	buttonsAlign : 'right',
	width:'100%',
    initComponent : function() {
    
       this.saveBtn = new Ext.Button({
	        text: 'Save',
	        scope : this,
			iconCls : 'save_btn',
	        disabled : true
        });

       this.resetBtn = new Ext.Button({
	        text: 'Reset',
			iconCls : 'cancel_btn',
	        scope : this
        });
    
    
        Ext.applyIf(this, {
            buttons: [this.saveBtn, this.resetBtn]            
        });
        utils.ButtonsPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getSaveBtn : function(){
    	return this.saveBtn;
    },

    getResetBtn : function(){
    	return this.resetBtn;
    }
});

Ext.reg('buttons-panel', utils.ButtonsPanel);
