Ext.namespace("IPD.admission");

IPD.admission.IPBilling = Ext.extend(Object, {
	constructor : function(config) {

		if(Ext.isEmpty(config)){
			config = {};
		}
		
		this.unbilledUsageGrid = new IPD.admission.UnbilledUsageGrid();
		this.viewPastTransactionsGrid = new IPD.admission.ViewPastTransactionsGrid(config);
		this.viewBillsGrid = new IPD.admission.ViewBillsGrid();
		
		var mainThis = this;
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				handler: function(){
				}
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				handler: function(){
				}
			}]
		});	
					  
		var billingTabPnl = new Ext.TabPanel({
			width:'100%',
			activeTab:0,
			border : false,
			deferredRender:false,
			layoutOnTabChange :true,
			hideMode:'offsets',
			items:[
			{
				title:'View Bills',
				autoScroll:true,
				autoHeight:true,
				items:this.viewBillsGrid.getPanel()
			},{
				title:'View Unbilled Detail',
				autoScroll:true,
				autoHeight:true,
				items:[this.unbilledUsageGrid.getPanel()]
			},{
				title:'View Past Transactions',
				autoScroll:true,
				autoHeight:true,
				items:[this.viewPastTransactionsGrid.getPanel()]
			}]
		});
		
		this.panel = new Ext.Panel({
				frame : true,
				width : '100%',
				autoHeight : true,
				border : false,
				items: [
				billingTabPnl]
		});
		
	},
	getPanel : function() {
			return this.panel;
	},
	loadGrids: function( arnNo ){
		this.viewBillsGrid.loadGrid( arnNo );
		this.unbilledUsageGrid.loadGrid( arnNo );
		this.viewPastTransactionsGrid.loadGrid( arnNo );
		
		this.viewBillsGrid.getPanel().doLayout();
	}
});