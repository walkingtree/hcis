Ext.namespace("OT.manageOT"); 

OT.manageOT.CheckListPanel = Ext.extend(Ext.Panel, {
	border : false,
    initComponent : function() {
		this.title = otMsg.ttlActivityPanel;
        this.record = Ext.data.Record.create( [
         	{ name : 'activityName',mapping : 'code' }, 
	        { name : 'checkListName', mapping : 'description'},
	        { name : 'type', mapping : 'type'},
        ]);

 		this.gridChk = new Ext.grid.CheckboxSelectionModel({

 		});

        this.gridColumns = [
			this.gridChk ,
			  { header :otMsg.lblActvityName, dataIndex :'activityName', width : 100 }, 
	           { header :otMsg.checkListName, dataIndex :'checkListName', width : 100 }, 
	           { header :otMsg.type , dataIndex :'type', width : 200 } 
	    ];
          
		this.labDataStore = new Ext.data.Store({
			proxy :new Ext.data.DWRProxy(LimsReferenceDataManager.getLabs, true),
			reader :new Ext.data.ListRangeReader( {id :'id',totalProperty :'totalSize'}, this.record),
			remoteSort :true	//
		
		});
		
		this.gridToolbar = new utils.GridToolBar();
		this.gridToolbar.getAddBtn().hide();
		this.gridToolbar.getReportBtn().hide();
		this.gridToolbar.getEditBtn().disable();
		this.gridToolbar.getDeleteBtn().disable();
		
	       
		this.gridPnl = new Ext.grid.GridPanel({
	        viewConfig : {forceFit :true},
	        remoteSort :true,
	      	frame : false,
	    	border : false,
	    	height : 200,
	    	width : '100%',
	    	tbar:this.toolBar,
	    	stripeRows :true,
	    	autoScroll :true,
	    	sm : this.gridChk,
	    	store : this.labDataStore,
	    	columns : this.gridColumns,
	    	tbar : this.gridToolbar
		});
		
		this.activityNameCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'activityName',
			fieldLabel : otMsg.lblActvityName,
			anchor : '90%',
			store : loadCountryCbx() 
		});
		
		this.checkListCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'checkList',
			fieldLabel : otMsg.lblCheckList,
			anchor : '90%',
			store : loadCountryCbx() 
		});

		
		this.activityRadioGroup = new Ext.form.RadioGroup({
			name : 'activity',
       		items : [
	            {boxLabel: otMsg.lblBeforeActivity , name: 'activity', inputValue: "Before activty",checked: true},
	            {boxLabel: otMsg.lblAfterActivity , name: 'activity', inputValue: "After activity",checked: false},
            ]
		});	 
		
		this.addBtn = new Ext.Button({
			text: otMsg.btnAdd,
			scope : this,
			iconCls :'add_btn',
			disabled : false
		});
		
		this.widgetsPanel = new Ext.form.FormPanel({
			layout : 'column',
			frame : true,
			border : false,
			items : [
		        {
		        	columnWidth : .5,
		        	labelWidth : 80,
		        	layout: 'form',
		        	items : this.activityNameCbx
		        },
		        {
		        	columnWidth : .5,
		        	labelWidth : 80,
		        	layout: 'form',
		        	items : this.checkListCbx
		        },
		        {
		        	columnWidth : .5,
		        	items : this.activityRadioGroup
		        },
		        {
		        	columnWidth : .5,
		        	layout : 'form',
		        	items : this.addBtn
		        }
	        ]
		});
		
		this.addBtn.on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.gridToolbar.getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.gridToolbar.getDeleteBtn().on('click',function(){
			this.delBtnClicked();
		},this);
		
		Ext.applyIf(this, {
			items: [
				this.widgetsPanel,
				this.gridPnl
			]            
		});
	
		LIMS.specimenCollectionPoint.CollectionPointLabAssociationGrid.superclass.initComponent.apply(this, arguments);
    },
    
    reset : function(){
    	this.activityNameCbx.clearValue();
    	this.checkListCbx.clearValue();
    	this.gridPnl.getStore().removeAll();
    },
    
    editBtnClicked : function(){
    	var selectedRows = this.getSelectedRows();
    	var activityName = selectedRows[0].data.activityName;
    	var checkListName = selectedRows[0].data.checkListName;
    	var type = selectedRows[0].data.type;
    	this.activityNameCbx.setValue( activityName );
    	this.checkListCbx.setValue( checkListName );
    	this.activityRadioGroup.setValue( type );
    	this.gridPnl.getStore().remove( selectedRows[0] );
    },
    
    getSelectedRows : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    
    delBtnClicked : function(){

    	var dataForDeletion = this.getSelectedRows();
		for( var i = 0 ; i < dataForDeletion.length ; i++){
			this.gridPnl.getStore().remove( dataForDeletion[i] );
		}
		
		this.gridToolbar.getDeleteBtn().disable();
    },
    addBtnClicked : function(){
    	var activityName = this.activityNameCbx.getValue();
    	var checkListName = this.checkListCbx.getValue();
    	var type =  this.activityRadioGroup.getValue();
    	
    	var recType = this.gridPnl.getStore().recordType;
    	
    	var record = new recType({
    		activityName : activityName,
    		checkListName : checkListName,
    		type : type
    	});
    	
    	this.gridPnl.getStore().add( record );
    }
    
});