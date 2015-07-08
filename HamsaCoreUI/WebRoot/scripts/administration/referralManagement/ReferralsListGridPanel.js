Ext.namespace("administration.referralManagement");

administration.referralManagement.ReferralsListGridPanel = Ext.extend(Ext.Panel, {
    title : 'Referrals List',
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',	
    initComponent : function() {
    	var mainThis = this;
         this.record = Ext.data.Record.create( [
         	{ name : 'referralCode' }, 
	        { name : 'referralTypeCode', mapping : 'referralType',convert : getCode},
		    { name : 'referralTypeDesc', mapping : 'referralType', convert : getDescription  },
		    { name : 'uniqueName', mapping : 'referralName' },
		    { name : 'address', mapping : 'referralAddress' },
//		    { name : 'cityCode', mapping : 'cityCode', type : 'string' },
		    { name : 'cityDesc', mapping : 'city', type : 'string' },
		    { name : 'stateCode', mapping : 'state', convert : getCode },
		    { name : 'stateDesc', mapping : 'state',convert : getDescription  },
		    { name : 'countryCode', mapping : 'country', convert : getCode},
		    { name : 'countryDesc', mapping : 'country', convert : getDescription },
		    { name : 'pinCode', mapping : 'pincode' },
		    { name : 'preferredTime', mapping : 'preferredContactTime' }
         ]);

	 	this.gridChk = new Ext.grid.CheckboxSelectionModel();

        this.gridColumns = [
			this.gridChk ,
	           { header :"Referral type", dataIndex :'referralTypeDesc', width : 100 }, 
	           { header :"Name", dataIndex :'uniqueName', width : 200 }, 
	           { header :"Address", dataIndex :'address', width : 150 }, 
	           { header :"City", dataIndex :'cityDesc', width : 100 }, 
	           { header :"State", dataIndex :'stateDesc', width : 100 }, 
	           { header :"Country", dataIndex :'countryDesc', width : 100 }, 
	           { header :"Pin code", dataIndex :'pinCode', width : 70 }, 
	           { header : 'Preferred time', dataIndex :'preferredTime', width : 100 }
	    ];
          
		this.dataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(ReferralManager.getActiveReferralsListRange, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	
		
		});
		
	this.pagingBar = new Ext.WTCPagingToolbar({
                store: this.dataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No referrals to display"
    });
       
	   this.toolBar = new utils.GridToolBar();
	   
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : true,
		  border : false,
		  height : 315,
		  tbar:this.toolBar,
          stripeRows :true,
          autoScroll :true,
          sm : this.gridChk,
          bbar : this.pagingBar,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sortInfo: {field: 'referralTypeCode', direction: 'ASC'}
      });
      
      this.gridPnl.on('render',function(){
		this.loadData(null);      
	  },this);

      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
			]            
      });

      administration.referralManagement.ReferralsListGridPanel.superclass.initComponent.apply(this, arguments);
    },
    loadData : function(referralLightBM){
    		this.gridPnl.getStore().removeAll();
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[referralLightBM]});
    },

    getReset : function(){
    	this.gridPnl.getStore().removeAll();
    },
    
    getToolbar : function(){
    	return this.toolBar;
    },
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    getGridPanel : function(){
    	return this.gridPnl;
    },
    getCheckBoxSelection : function(){
    	return this.gridChk
    }
});

Ext.reg('refferals-list-grid-panel', administration.referralManagement.ReferralsListGridPanel);
