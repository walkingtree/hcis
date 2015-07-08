Ext.namespace("IPD.admission");

IPD.admission.ViewBillsGrid = Ext.extend(Object,{
	constructor: function( config ){
		
		if( Ext.isEmpty( config )){
			config ={};
		}
		this.billRecord = Ext.data.Record.create([
			{name: 'billNumber', mapping:'billNumber'},
			{name: 'admissionNbr', mapping:'accountId'},
//			{name: 'patientName'},
			{name: 'billDate', mapping:'billDate'},
			{name: 'dueDate', mapping:'billDueDate'},
			{name: 'billAmount', mapping:'billAmount'}
//			{name: 'paidAmount'},
//			{name: 'balanceAmount'},
//			{name: 'paymentStatus'}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, 
												  this.billRecord ),
        	proxy: new Ext.data.DWRProxy(BillManager.findBillDetails, true)
		});
		
	 	this.action = new Ext.ux.grid.RowActions({
			 header:'Actions'
			,autoWidth:false
//			,hideMode:'display'
			,keepSelection:true
			,actions:[{
				iconCls:'pdf-icon'
				,tooltip:'View bill'
			}, {
				iconCls:'money-icon'
				,tooltip:'View transactions'
			}/*, {
				iconCls:'money-add-icon'
				,tooltip:'Apply Receipt'
			}*/]
		});

		// dummy action event handler - just outputs some arguments to console
		this.action.on({
			action:function(grid, record, action, row, col) {
				
				var arnNo = record.data.admissionNbr;
				var billNumber =record.data.billNumber;
				
				if( action === 'pdf-icon'){
					
					var billNbr = {billNbr: billNumber};
					CoreReportManager.generateReport( 
		 				"OPDPatientBill" , 
					    billNbr, 
					    function(reportURL){
				       		window.open(getBaseURL() + reportURL);
				        });
				}
				
				if( action === 'money-icon'){
					var viewTransactionsGridPnl = new IPD.admission.ViewPastTransactionsGrid();
					var txnWindow = new Ext.Window({
						height: '50%',
						width: '50%',
						title:'view Transactions for billNumber:'+billNumber,
						frame:true,
						closeAction:'hide',
						items: viewTransactionsGridPnl.getPanel()
					});
					viewTransactionsGridPnl.loadGrid( arnNo, billNumber );
					txnWindow.show();
				}
			}
			,beforeaction:function() {
			}
			,beforegroupaction:function() {
			}
			,groupaction:function(grid, records, action, groupId) {
			}
		});

		var columns = [ {
							header : 'Bill Number',
							dataIndex : 'billNumber',
							width :150,
							sortable: true
						},/* {
							header : 'Admission Number', //account_id
							dataIndex : 'admissionNbr',
							width : 110,
							sortable: true
						}, */{
							header : 'Bill Date',
							dataIndex : 'billDate',
							width : 150,
							sortable: true,
							renderer: Ext.util.Format.dateRenderer('d/m/Y')
						}, {
							header : 'Due Date',
							dataIndex : 'dueDate',
							width : 150,
							sortable: true,
							renderer: Ext.util.Format.dateRenderer('d/m/Y')
						}, {
							header : 'Bill Amount',
							dataIndex : 'billAmount',
							align:'right',
							width : 150,
							sortable: true,
							renderer:convertToAmount
						}, this.action];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.includeColumns)){
	        var cs = [];
	        for(var i = 0, len = config.includeColumns.length; i < len; i++){
	            cs.push(columns[config.includeColumns[i]]);
	        }
	        columns = cs;
	    }
	    
		this.infoGrid = new Ext.grid.GridPanel({
				frame : false,
				stripeRows : true,
				width:'100%',
				height: 300,
				autoScroll : true,
				border : false,
				viewConfig:{
					forceFit: true
				},
				store : this.dataStore,
				sm:this.gridChk,
				columns : columns,
				plugins : this.action,
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						var selectedRecords = thisGrid.getSelectionModel().getSelections();
						if(selectedRecords.length >1){
						}else{
						}
					} 
				}
		});
	},
	getPanel: function(){
		return this.infoGrid;
	},
	loadGrid: function( arnNo ){
		this.infoGrid.getStore().load({params:{start:0, limit: 9999}, 
								  arg:[ arnNo,configs.referenceTypeForIPD ] });	
	  	this.infoGrid.doLayout();
	}
	
});
