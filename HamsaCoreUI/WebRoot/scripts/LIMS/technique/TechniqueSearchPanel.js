Ext.namespace("LIMS.technique");

LIMS.technique.TechniqueSearchPanel = Ext.extend(Ext.form.FormPanel, {
//			title : 'Search',
			border:false,
			layout : 'column',
			border : false,
			frame:false,
			height:100,
			labelWidth:75,
			width:'98%',
			padding:10,
									
		initComponent : function() {
	
			this.widgets = new LIMS.technique.Widgets();
			this.buttonsPanel = new utils.SearchButtonsPanel();
			
			Ext.applyIf(this, {
	            items : [ {
								columnWidth : 0.25,
								layout : 'form',
								items : [ this.widgets.getTechniqueNameSearchTxf() ]
							},
							{
								columnWidth : 0.35,
								labelWidth:120,
								layout : 'form',
								items : [ this.widgets.getTechReagentCombo() ]
							},
							{
								columnWidth : 0.3,
								layout : 'form',
								items : [ this.widgets.getLabTestCombo() ]
							},
							
							 {
								columnWidth : 1,
								layout : 'form',
								items : [this.buttonsPanel ]
							}
							
							]
						});
			LIMS.technique.TechniqueSearchPanel.superclass.initComponent.apply(this, arguments);
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