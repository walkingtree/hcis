Ext.namespace("LIMS.testAttribute");

LIMS.testAttribute.TestAttributeSearchPanel = Ext.extend(Ext.form.FormPanel, {
//			title : 'Search',
			border:false,
			layout : 'column',
			border : false,
			frame:false,
			height:100,
			labelWidth:105,
			width:'98%',
			padding:10,
									
		initComponent : function() {
	
			this.widgets = new LIMS.testAttribute.Widgets();
			this.buttonsPanel = new utils.SearchButtonsPanel();
			
			//Set the widget according to search
			this.widgets.getAttributeNameTxf().required = false;
			this.widgets.getAttributeNameTxf().allowBlank = true;
			
			Ext.applyIf(this, {
	            items : [ {
								columnWidth : 0.3,
								layout : 'form',
								items : [ this.widgets.getAttributeNameTxf() ]
							},
							{
								columnWidth : 0.3,
								layout : 'form',
								labelWidth:70,
								items : [ this.widgets.getAttributeTypeCombo() ]
							},
							{
								columnWidth : 0.3,
								layout : 'form',
								labelWidth:75,
								items : [ this.widgets.getTestCombo() ]
							},
							
							 {
								columnWidth : 1,
								layout : 'form',
								items : [this.buttonsPanel ]
							}
							
							]
						});
			LIMS.testAttribute.TestAttributeSearchPanel.superclass.initComponent.apply(this, arguments);
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