Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderSearchCriteria = Ext.extend(Ext.form.FormPanel,{
	height : '100%',
	width : '100%',
	layout : 'column',
	initComponent : function(){
		
		this.requisitionOrderWidgets = new  LIMS.requisitionOrder.manage.RequisitionOrderWidgets();
		
		Ext.applyIf(this, {
			style : 'padding : 10px 10px 10px 10px',
			labelWidth : 140,
			items : [
			         
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getPatientNameCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getPatientIdCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getReferenceTypeCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getTestStatusCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getRequisitionFromDate()]
			   },
			   {
					columnWidth:0.33, 
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getRequisitionToDate()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getDoctorNameCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getDoctorIdCbx()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.requisitionOrderWidgets.getTestNameCbx()]
			   }
			]
		});
		
		LIMS.requisitionOrder.manage.RequisitionOrderSearchCriteria.superclass.initComponent.apply(this , arguments);
		
	},
	
	getPanel : function(){
		return this;
	},
	reset : function(){
		return this.getForm().reset();
	}
});