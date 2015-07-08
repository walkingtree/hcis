Ext.namespace("OPD.serviceAssignement");

OPD.serviceAssignement.SelectionPanel = Ext.extend(Object,{
	constructor: function( config ){
		
		Ext.apply( this, config );
		
		// this will give only selectedPanelWidgets only
		this.widgets = new OPD.serviceAssignement.SelectionPanelWidgets( config );
		
		this.mainPanel = new Ext.form.FormPanel({
				layout:'column',
				frame : true,
				autoHeight : true,
				border : false,
				defaults: {
					layout:'form',
					style:'padding-bottom:5px;'
				},
				items: [
					{
						columnWidth:0.37,
						items: this.widgets.getRequestedUnitsLbl()
					},
					{	
						columnWidth:0.63,
						items: this.widgets.getRequestedUnitsValLbl()
					},
					{
						columnWidth:0.37,
						items: this.widgets.getAlreadyRenderUnitsLbl()
					},
					{
						columnWidth:0.63,
						items: this.widgets.getAlreadyRenderUnitsValLbl()
					},
					{
						columnWidth:0.37,
						items: this.widgets.getAlreadyCanceledUnitsLbl()
					},
					{
						columnWidth:0.63,
						items: this.widgets.getAlreadyCanceledUnitsValLbl()
					},
					{
						layout:'form',
						labelWidth:100,
						items: this.widgets.getEntredUnits(),
						columnWidth:1
					},
					{
						columnWidth:1,
						buttonAlign:'center',
						buttons:[ 
							this.widgets.getOkBtn(),
							this.widgets.getCancelBtn()
						]
					}
					
				]
		});
		
	},
	getPanel: function(){
	 return this.mainPanel;
	},
	loadData: function( data ){
		this.widgets.getRequestedUnitsValLbl().setText( data.requestedUnits );
		this.widgets.getAlreadyRenderUnitsValLbl().setText( data.renderedUnits == 0?'0':data.renderedUnits );
		this.widgets.getAlreadyCanceledUnitsValLbl().setText( data.canceledUnits == 0?'0':data.canceledUnits );
		this.widgets.getEntredUnits().setValue( data.initialvalueForEnteredUnits );
	}
});