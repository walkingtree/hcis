Ext.namespace("OPD.issueReceipt");

OPD.issueReceipt.ReceivablesGridPanel = Ext.extend(Ext.Panel, {
    title : msg.recvblMsg,
    border : false,
    frame : false,
	width:'100%',	
    initComponent : function() {
    	var mainThis = this;
         this.record = Ext.data.Record.create( [
	        { name : 'receivableDate', mapping : 'transactionDate'},
	        { name : 'receivable', mapping : 'receivableId'},
	        { name : 'amount', mapping : 'amount'},
	        { name : 'rmngAmt', mapping : 'remngAmount'}
         ]);
//		this.checkBoxModel = new Ext.grid.CheckboxSelectionModel();
		
        this.gridColumns = [
//        		this.checkBoxModel,
	           { header :"Receivable date", dataIndex :'receivableDate', width : 200 }, 
	           { header :"Receivable id", dataIndex :'receivable', width : 150 }, 
	           { header :"Amount", dataIndex :'amount', width : 100 }, 
	           { header :"Remaining amount", dataIndex :'rmngAmt', width : 100 }
	    ];
          
		this.dataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(AccountantManager.getPatinetReceivable, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	
		
		});
		
	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No receivables to display"
    });
       
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : true,
		  border : false,
		  height : 300,
          stripeRows :true,
          autoScroll :true,
          bbar : this.pagingBar,
          store : this.dataStore,
//          sm: this.checkBoxModel,
          columns : this.gridColumns
      });

      Ext.applyIf(this,{
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
			]            
      });
		
       this.gridPnl.on('render', function(){
       		this.gridPnl.getStore().load({params:{start:0, limit:9999}, arg:[ this.initialConfig.patientId]});
       },this);
       
      OPD.issueReceipt.ReceivablesGridPanel.superclass.initComponent.apply(this, arguments);
    },
    getPanel : function(){
    	return this;
    }
});

