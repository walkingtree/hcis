Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.LabSearchPanel = Ext.extend(Ext.form.FormPanel, {
	title : 'Laboratory Search',
	border:false,
	layout : 'column',
	frame:true,
	titleCollapse : true,
	height:175,
	labelWidth:105,
	width:'98%',
	
	initComponent : function() {

		this.widgets = new LIMS.labConfiguration.Widgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getHospitalNameCbx()]
			    },
			    {
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getLabTypeCbx(false)]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getLabIdTxf(false)]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getLabNameTxf(false)]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getBusinessNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getBranchNameTxf()]
				},{
	            	layout : 'form',
					columnWidth:0.5,
					items:[this.widgets.getLabOperatorIDTxf()]
			    },{
			    	layout : 'form',
			    	columnWidth:1,
			    	items:[this.buttonsPanel]
			    }
			    
			    		
			]            
        });
		LIMS.labConfiguration.LabSearchPanel.superclass.initComponent.apply(this, arguments);
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

//Ext.reg('laboratory-search-panel', LIMS.LabConfiguration.LabSearchPanel);
