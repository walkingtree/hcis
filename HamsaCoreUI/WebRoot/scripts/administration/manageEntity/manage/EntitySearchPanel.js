Ext.namespace("administration.manageEntity.manage");

/*
 *  We create search panel for searching , add this panel in to Grid in EntityList
 */

administration.manageEntity.manage.EntitySearchPanel = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	border : false,
	frame:true,
	autoHeight : true,
	labelWidth:105,
	width:'98%',
	
	initComponent : function() {

		this.widgets = new administration.manageEntity.Widgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.3,
	            	layout : 'form',
					items:[this.widgets.getEntitytName()]
			    },{
					columnWidth:0.3,
	            	layout : 'form',
					items:[this.widgets.getEntityType()]
			    },{
					columnWidth:0.3,
	            	layout : 'form',
					items:[this.widgets.getGender()]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
		administration.manageEntity.manage.EntitySearchPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		return this.getForm().getValues();
	},
	
	getReset : function(){
		return this.getForm().reset();
	}
	
	
});

Ext.reg('refferal-search-panel', administration.manageEntity.manage.EntitySearchPanel);
