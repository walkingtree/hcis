Ext.namespace('GIS');

GIS.westpanel = Ext.extend(Ext.Panel, {
    initComponent : function(  ){
		
		this.widget = new GIS.widgets();
    	
    	Ext.apply(this,{
    		layout:'column',
    		border:false,
    		defaults:{ 
    			columnWidth:1,
				layout:'form',
				labelAlign: 'top'
			},
    		items:[
					{
						border:false,
		 				items:this.widget.getTreatmentTypeCbx()
					},
    				{
						border:false,
		 				items:this.widget.getCountryCbx()
    				},
    				{
						border:false,
		 				items:this.widget.getStateCbx()
    				},
    				{
						border:false,
		 				items:this.widget.getTreatmentReasonTxf()
					},
					{
						border:false,
						items:this.widget.getToDate()
					},
					{
						border:false,
						items:this.widget.getFromDate()
					}
					
				]
    	});
    	 GIS.westpanel.superclass.initComponent.apply(this, arguments);
}
    			
    				
});