Ext.namespace("administration.vaccinationSchedule.manage");

administration.vaccinationSchedule.manage.VaccinationScheduleSearchPanel = Ext.extend(Ext.form.FormPanel, {
	title : 'Search',
	border:false,
	layout : 'column',
	border : false,
	frame:true,
	collapsed : false,
	collapsible: true,
	titleCollapse : true,
	height:150,
	labelWidth:105,
	width:'98%',
	
	initComponent : function() {

		this.widgets = new administration.vaccinationSchedule.manage.Widgets();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getScheduleNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getScheduleDescTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeGroupTxf()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getVaccinationNameCombo()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getActiveCombo()]
				},{
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
        administration.vaccinationSchedule.manage.VaccinationScheduleSearchPanel.superclass.initComponent.apply(this, arguments);
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

Ext.reg('refferal-search-panel', administration.vaccinationSchedule.manage.VaccinationScheduleSearchPanel);
