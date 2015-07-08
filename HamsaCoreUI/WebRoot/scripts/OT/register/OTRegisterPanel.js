Ext.namespace("OT.register");

OT.register.OTRegisterPanel = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:true,
	
	initComponent : function() {
		this.searchPanel = new OT.register.OTRegisterSearchPanel();
		this.gridPanel = new OT.register.OTRegisterGrid();
		
		this.searchPanel.getButtonPanel().getSearchButton().on('click',function(){
			this.searchButtonClicked();
		},this);
		
		this.searchPanel.getButtonPanel().getResetButtton().on('click',function(){
			this.resetButtonClicked();
		},this);
		
		Ext.applyIf(this, {
            items: [
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.searchPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					style : 'padding-top:10px',
					items:[this.gridPanel]
			    }
			]            
        });
		OT.register.OTRegisterPanel.superclass.initComponent.apply(this, arguments);
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.loadData( searchCriteria ); 	
	},
	
	resetButtonClicked : function(){
		this.searchPanel.reset();
		this.gridPanel.loadData();
	}
	
});
