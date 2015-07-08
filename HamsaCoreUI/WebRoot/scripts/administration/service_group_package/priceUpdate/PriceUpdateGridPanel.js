Ext.namespace("administration.service_group_package.priceUpdate");

administration.service_group_package.priceUpdate.PriceUpdateGridPanel = Ext.extend(Ext.Panel, {
				    layout : 'fit',
				    border : false,
				    frame : false,
					width:'98%',
					height:350,
					initComponent : function(){
		
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		this.serviceRecord = Ext.data.Record.create([
		 	{ name : "serviceCode", mapping:'serviceCode' },
		 	{ name : "serviceName" , mapping:'serviceName' },
		 	{ name : "serviceGroupCd", mapping:'serviceGroup', convert: getCode},
		 	{ name : "serviceGroupDesc", mapping:'serviceGroup', convert: getDescription},
		 	{ name : "serviceCharge" ,mapping:'serviceCharge', type :'float' },
		 	{ name : "departmentName" ,mapping:'departmentName' },
		 	{ name:  "newCharge" , type:'float'},
//		 	{ name:'departmentDesc', mapping:'department',convert: getDescription},
		 	
		 	{ name:'hideSubmitted', type:'boolean', mapping:'packageAsgmId', 
		 		convert: function(value, rec){
		 			if( value != null){
		 				return true;
		 			}else{
		 				return false;
		 			}	
		 	}}
		 	
		]);	
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'Actions',
			autoWidth:false,
			hideMode:'display',
			keepSelection:true,
			actions:[],
			groupActions:[{
				iconCls:'accept-icon',
				qtip:'select All',
				hideIndex:'hideSubmitted',
				align:'left'
			}],
			callbacks:{
				'icon-plus':function(grid, record, action, row, col) {
				 }
			 }
		});
		
	  	this.rowActions.on({
			groupaction:function(grid, records, action, groupId) {
				if(action == 'accept-icon'){
					var checkBoxSelectionModel = grid.getSelectionModel();
					if( records.length > 0 && checkBoxSelectionModel.isIdSelected( records[0].id ) ){
						for( var i = 0; i<records.length; i++){
							var startIndex = grid.getStore().find('serviceCode',records[i].data.serviceCode);
							checkBoxSelectionModel.deselectRange( startIndex, startIndex+records.length );
						}
					}else{
						checkBoxSelectionModel.clearSelections();
						checkBoxSelectionModel.selectRecords(records);
					}
				}
				
			 }
		});
		
		this.dataStore = new Ext.data.GroupingStore({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.serviceRecord),
        	proxy: new Ext.data.DWRProxy(ServiceManager.findServices, true),
        	groupField:'departmentName',
        	sortInfo: {field: 'serviceCode', direction: "ASC"},
        	remoteSort: true
		});

		this.pagingBar = new Ext.WTCPagingToolbar({
				store : this.dataStore,
				displayMsg : svcAndGrpAndPkgMsg.displayingServicesMsg,
				emptyMsg : svcAndGrpAndPkgMsg.noServiceGroupsMsg
			});
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
//			listeners:{
//				rowselect : function( selectionModel, rowIndex, record){
//							},
//				rowdeselect : function( selectionModel, rowIndex, record){
//							}
//			}
		});
		

		var gridColumns =[
			this.gridChk,
			{ header: servicePriceMsg.serviceCode, dataIndex : "serviceCode", sortable: true, width: 100 },
		 	{ header: servicePriceMsg.ServiceName, dataIndex : "serviceName", sortable: true, width: 150  },
		 	{ header: servicePriceMsg.serviceCharge, dataIndex : "serviceCharge", sortable: true, width: 100  },
		 	{ header: servicePriceMsg.newCharge, dataIndex : "newCharge", editor:new Ext.form.NumberField(),sortable: true, width: 100  },
		 	{ header: servicePriceMsg.Department, dataIndex : "departmentName", sortable: true, width: 100  },
//		 	this.rowActions
		];		
		
		this.infoGrid = new Ext.grid.EditorGridPanel({
			frame : false,
			stripeRows : true,
			height : 350,
			width : '98%',
			style : 'padding-right: 5px',
			autoScroll : true,
			plugins:[this.rowActions],
			viewConfig:{forceFit: true},
			view: new Ext.grid.GroupingView({
		        viewConfig:{forceFit:true},
		        hideGroupedColumn:true,
		        enableGroupingMenu: false,
		        // custom grouping text template to display the number of items per group
		        groupTextTpl: '{[getGroupText(values.text)]}({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
		    }),
			border : true,
			store : this.dataStore,
			bbar : this.pagingBar,
//			tbar : gridButtons,
			columns : gridColumns,
			sm: this.gridChk
//			listeners:{
//				cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
//					mainThis.setGridButtonsState(thisGrid.getSelectionModel());
//				} 
//			}
		});
		
		
		this.infoGrid.on('render',function(){
			this.loadData();      
		  },this);
		
		this.infoGrid.getStore().on('load',function(){
            var hd = Ext.DomQuery.selectNode('.x-grid3-hd-checker', this.infoGrid.view.innerHd);
            if(hd) Ext.fly(hd).removeClass('x-grid3-hd-checker-on');

		},this);

	     
		
		Ext.applyIf(this, {
		 	  items: [{
		                  columnWidth : 1,
		                  items :[this.infoGrid]
		             }
					]            
	      });
	      
	      

		administration.service_group_package.priceUpdate.PriceUpdateGridPanel.superclass.initComponent.apply(this, arguments);
	},
	getPanel: function(){
		return this.infoGrid;
	},
	getSelectedRows: function(){
		return this.infoGrid.getSelectionModel().getSelections();
	},

	loadData : function( config ){
		this.infoGrid.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.infoGrid.getStore().load(
									{
										params : {
											start : 0,
											limit : 10
										},
										arg : [ config.serviceName || null , null, null, config.departmentCode || null, null,
												null, null, null, null, null,null ]
									});
		}else{
			this.infoGrid.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null,null,null, null,null,null,null,null]});
		}
//		this.setGridButtonsInitialState();
    },
	
	
    updateGridData : function(updatePirce, increDecreInd,pectAbsInd){

		var selectedRows = this.infoGrid.getSelectionModel().getSelections();
		
		//code to calculate updated amount
		
		updatePirce = parseFloat(updatePirce);
		
		if( increDecreInd == "D"){
			updatePirce = updatePirce * -1;
		}
		
		for(var i =0; i<selectedRows.length;i++){
		
			if(pectAbsInd == "P"){
				selectedRows[i].data.newCharge = selectedRows[i].data.serviceCharge + ( selectedRows[i].data.serviceCharge * updatePirce/100 );
			}else{
				selectedRows[i].data.newCharge = selectedRows[i].data.serviceCharge + updatePirce;
			}
			
			this.infoGrid.getStore().add( selectedRows[i] );
		}
		
    },
    
    loadGridData: function( list ){
		if( !Ext.isEmpty( list )){
			var record = this.infoGrid.getStore().recordType;
			for( var i = 0; i<list.length; i++ ){
				var data = new record({
					serviceCode : list[i].serviceCode,
					serviceName : list[i].serviceName,
					serviceCharge : list[i].serviceCharge,
					newCharge : list[i].newCharge,
					departmentName : list[i].departmentName
				});
				this.infoGrid.getStore().add( data );
			}
		}
     },
     
     getCheckBoxSelectionModel : function(){
    	 return this.gridChk;
     }
 		
});
 function getGroupText ( text ){
 	if(text.substring(text.indexOf(':')+1) == ' null'){
 	
 		var arrStr = text.split(':');
 		return 'Individual services: '
 	}else{
 		return text;
 	}
 }