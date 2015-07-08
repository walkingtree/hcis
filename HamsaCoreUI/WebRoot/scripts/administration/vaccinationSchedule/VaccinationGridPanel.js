Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.VaccinationGridPanel = Ext.extend(Object,{
	constructor: function(config){
		
		Ext.apply(this,config);
	
		var mainThis = this;
		
		this.widgets = new administration.vaccinationSchedule.Widgets();
		
		this.vaccinationScheduleRecord = Ext.data.Record.create([
			{ name : "vaccinationName", type : "string" }, 
			{ name : "vaccinationCode", type : "string" }, 
			{ name : "diseaceCode", type : "string" }, 
			{ name : "diseaceName", type : "string" }, 
			{ name : "period", type : "string" }, 
			{ name : "dosage", type : "string" }, 
			{ name : "afterVaccinationName", type : "string" }, 
			{ name : "afterVaccinationCode", type : "string" }, 
			{ name : "remarks", type : "string" }, 
			{ name : "afterPeriod", type : "string" },
			{ name : "periodInd", type : "string" },
			{ name : "afterPeriodInd", type : "string" },
			{ name : "ItemOthersFlag" },
			{ name : "ItemAfterOthersFlag" }
		]);
		
		this.DataStore = new Ext.data.Store({
			reader : new Ext.data.ListRangeReader({id : 'id',totalProperty : 'totalSize'}, 
												  this.vaccinationScheduleRecord)
		});
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					this.widgets.getEditBtn().disable();
					this.widgets.getDeleteBtn().enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.widgets.getEditBtn().disable();
					this.widgets.getDeleteBtn().disable();
				},
				scope:this
			}
			
		});
		
		this.tbarButtons = new Ext.Toolbar({
			items:['-', this.widgets.getEditBtn(),
				   '-', this.widgets.getDeleteBtn(),
				   '-'
			]
		});
		this.infoGrid = new Ext.grid.GridPanel({
			autoScroll : true,
			stripeRows : true,
			border : false,
			frame : false,
			height : 150,
			viewConfig:{
				forceFit: true
			},
			listeners:{
				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					this.setGridButtonsState(thisGrid.getSelectionModel());
				},
				scope:this
			},
			sm: this.gridChk ,
			ds : this.DataStore,
			cm : new Ext.grid.ColumnModel([
					this.gridChk ,
					{
						header : "Period",
						width : 40,
						dataIndex : 'period'
					},
					{
						header : "Period ind",
						width : 60,
						dataIndex : 'periodInd'
					},
					{
						header : "Disease",
						width : 125,
						dataIndex : 'diseaceName'
					},
					{
						header : "Vaccination name",
						width : 125,
						dataIndex : 'vaccinationName'
					},
					{
						header : "dosage",
						width : 50,
						dataIndex : 'dosage'
					},
					{
						header : " After vaccination",
						width : 125,
						dataIndex : 'afterVaccinationName'
					},
					{
						header : "After period",
						width : 40,
						dataIndex : 'afterPeriod'
					},
					{
						header : "After period ind",
						width : 60,
						dataIndex : 'afterPeriodInd'
					}
			]),
			tbar: this.tbarButtons
			});
	},
	
	getPanel: function(){
		return this.infoGrid;
	},
	
	loadDataIntoGrid: function(){
	
	},
	
	getSelectedRows: function(){
		return this.infoGrid.getSelectionModel().getSelections();
	},
	
	deleteListener: function(){
		var selectedRows = this.getSelectedRows();
		if( !Ext.isEmpty( selectedRows ) ){
			for ( var i = 0; i< selectedRows.length; i++ ){
				this.infoGrid.getStore().remove( selectedRows[i] );
			}
		}
	},
	
	setGridButtonsState : function( selectionModel ){
		var selectedRows = selectionModel.getSelections();
		this.widgets.getEditBtn().disable();
		this.widgets.getDeleteBtn().disable();
		if( selectedRows.length == 1){
			this.widgets.getEditBtn().enable();
			this.widgets.getDeleteBtn().enable();
			
		} else if( selectedRows.length > 1){
			this.widgets.getEditBtn().disable();
			this.widgets.getDeleteBtn().enable();
		}
	},
	
	initialStatusForGridButtons : function(){
		this.widgets.getEditBtn().disable();
		this.widgets.getDeleteBtn().enable();
	}
});