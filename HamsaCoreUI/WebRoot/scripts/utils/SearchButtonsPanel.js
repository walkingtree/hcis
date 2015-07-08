Ext.namespace("utils");

utils.SearchButtonsPanel = Ext.extend(Ext.Panel, {
	border : false,
	buttonsAlign : 'right',
	
    initComponent : function() {
    
       this.searchBtn = new Ext.Button({
	        text: 'Search',
	        scope : this,
	        iconCls : 'search_btn'
        });

       this.resetBtn = new Ext.Button({
	        text: 'Reset',
	        scope : this,
	        iconCls : 'cancel_btn'
        });
    
    
        Ext.applyIf(this, {
            buttons: [this.searchBtn, this.resetBtn]            
        });
        utils.SearchButtonsPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getSearchButton : function(){
    	return this.searchBtn;
    },

    getResetButtton : function(){
    	return this.resetBtn;
    }

});

Ext.reg('search-buttons-panel', utils.SearchButtonsPanel);
