Ext.namespace("LIMS.labTest");

LIMS.labTest.TestResultChangeHistory = Ext.extend(Ext.grid.GridPanel,{
	initComponent: function(){
		
		this.changeHistoryRecord = Ext.data.Record.create([
		 	{ name : "patientTestId" },
		 	{ name : "seqNbr"  },
		 	{ name : "attributeCode"},
			{ name : "attributeName"},
		 	{ name : "createdDtm" },
		 	{ name : "attributeOldValue" },
		 	{ name : "attributeValue" },
		 	{ name : "createdBy"},
		 	
		]);	
		
		this.dataStore = new Ext.data.GroupingStore({
			proxy: new Ext.data.DWRProxy(LabTestManager.getTestResultChangeHistory,true),
			reader: new Ext.data.ListRangeReader( 
					{id:'id', totalProperty:'totalSize'}, this.changeHistoryRecord ),
        	groupField:'createdDtm',//TODO: group on 'createdDtm' throwing error 
        	sortInfo: {field: 'seqNbr', direction: "ASC"},
        	remoteSort: true
		});
		

	     Ext.apply(this,{
	     	autoScroll:true,
			stripeRows:true,
			anchor:'100%',
			height:450,
			viewConfig:{forceFit:true},
			ds: this.dataStore,
			plugins : this.action,
			cm: new Ext.grid.ColumnModel ([
			 	{ header: "Seq. Nbr", dataIndex : "seqNbr",align:'left', sortable: true },
			 	{ header: "Attribute Code", dataIndex : "attributeCode",align:'centre', sortable: true} ,
			 	{ header: "Attribute Name", dataIndex : "attributeName", align:'centre',sortable: true  },
			 	{ header: "Old Value", dataIndex : "attributeOldValue",align:'centre', sortable: true  },
			 	{ header: "New Value", dataIndex : "attributeValue", sortable: true },
			 	{ header: "Changed By", dataIndex : "createdBy", sortable: true, width: 100  },
				{ header: "Change date", dataIndex : "createdDtm",renderer :Ext.util.Format.dateRenderer(configs.dtmFormat),//'d-M-Y h:i:s a')
			 	  sortable: true, width: 100  }
			]),
			view:  new Ext.grid.GroupingView({
			        viewConfig:{forceFit:true},
			        hideGroupedColumn:true,
			        enableGroupingMenu: false,
			        // custom grouping text template to display the number of items per group
			        groupTextTpl:  '{[getGroupText(values.text)]}({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})'
			    })
	     
	     });
	     
	     LIMS.labTest.TestResultChangeHistory.superclass.initComponent.apply(this, arguments);
	},
     getGrid: function(){
     	return this;
     },
     
     loadGridData : function( patientTestId ){
     
    	 this.dataStore.load({params:{start:0, limit:10}, arg:[ patientTestId ]});
     }
});

function getGroupText ( text ){
 	if(text.substring(text.indexOf(':')+1) == ' null'){
 	
 		var arrStr = text.split(':');
 		return 'Ungrouped'
 	}else{
 		return text;
 	}
 }