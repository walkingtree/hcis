Ext.namespace("administration.vaccinationSchedule.manage");

function getActiveFlagValue( value ){
	if( value ){
		return "YES";
	}else{
		return "NO";
	}
}
administration.vaccinationSchedule.manage.VaccinationSchedulesListGridPanel = Ext.extend(Ext.Panel, {
    title : 'Vaccinations schedules List',
    layout : 'fit',
    border : false,
    frame : false,
	width:'98%',
	initComponent : function() {
        this.record = Ext.data.Record.create( [ 
	        { name : 'scheduleName', mapping : 'scheduleName'},
	        { name : 'scheduleDesc', mapping : 'description'},
	        { name : 'ageGroup', mapping : 'ageGroup'},
	        { name : 'activeFlag', mapping : 'activeFlag', convert : getActiveFlagValue},
	        { name : 'vaccinationScheduleDetailList'}
    	]);

	 	this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					 this.toolBar.getEditBtn().disable();
					 this.viewDetailsBtn.disable();
					this.toolBar.getDeleteBtn().enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.toolBar.getEditBtn().disable();
					this.viewDetailsBtn.disable();
					this.toolBar.getDeleteBtn().disable();
				},
				scope:this
			}
		});
		
        this.gridColumns = [
			this.gridChk ,
	           { header :"Schedule name", dataIndex :'scheduleName', width : 150 }, 
	           { header :"Schedule description", dataIndex :'scheduleDesc', width : 200 }, 
	           { header :"Age group", dataIndex :'ageGroup', width : 200 }, 
	           { header :"Active?", dataIndex :'activeFlag', width : 200 } 
	    ];
       
	   this.viewDetailsBtn = new Ext.Button({
	   		text: schldVaccineMsg.viewDetails,
	   		iconCls:'view-icon',
	   		disabled: true
	   });
       this.toolBar = new utils.GridToolBar();
	   this.toolBar.add( this.viewDetailsBtn );
	   this.toolBar.add( new Ext.Toolbar.Separator());
       
	   this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: '',totalProperty:'totalSize'}, this.record),
        	proxy: new Ext.data.DWRProxy(ScheduleManager.findSchedules, true)
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			store : this.dataStore,
			displayMsg : schldVaccineMsg.displayingScheduleMsg,
			emptyMsg : schldVaccineMsg.noScheduleMsg
		});
		
       this.gridPnl = new Ext.grid.GridPanel({
		  frame : false,
		  border : false,
          tbar : this.toolBar,
		  height : 300,
          stripeRows :true,
          autoScroll :true,
          store : this.dataStore,
          columns : this.gridColumns,
          viewConfig : {forceFit :true},
          remoteSort :true,
          sm: this.gridChk,
          bbar:this.pagingBar,
          sortInfo: {field: 'scheduleName', direction: 'ASC'}
      });
      
      this.gridPnl.on('cellclick', function( thisGrid, rowIndex, colIndex, e){
      		this.setGridButtonsState( thisGrid.getSelectionModel() );
      },this);
      
      this.gridPnl.on('render',function(){
		this.loadData();      
	  },this);

      Ext.applyIf(this, {
	 	  items: [{
	                  columnWidth : 1,
	                  items :[this.gridPnl]
	             }
			]            
      });

      administration.vaccinationSchedule.manage.VaccinationSchedulesListGridPanel.superclass.initComponent.apply(this, arguments);
    },
    loadData : function( config ){
		this.gridPnl.getStore().removeAll();
		if( !Ext.isEmpty( config )){
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ config.scheduleName,
																			config.description,
																			config.vaccinationName,
																			config.ageGroup,
																			config.age,
																			config.activeFlag
																			]});
		}else{
			this.gridPnl.getStore().load({params:{start:0, limit:10}, arg:[ null, null,null,null,null,null]});
		}
		this.setGridButtonsInitialState();
    },

    getReset : function(){
    	this.gridPnl.getStore().removeAll();
    },
    
    getToolbar : function(){
    	return this.toolBar;
    },
    getViewDetailsBtn: function(){
    	return this.viewDetailsBtn;
    },
    getSelection : function(){
    	return this.gridPnl.getSelectionModel().getSelections();
    },
    setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.toolBar.getEditBtn().disable();
		this.viewDetailsBtn.disable();
		this.toolBar.getDeleteBtn().disable();
		if( selectedRows.length == 1){
			this.toolBar.getEditBtn().enable();
			this.viewDetailsBtn.enable();
			this.toolBar.getDeleteBtn().enable();
			
		} else if( selectedRows.length > 1){
			this.toolBar.getEditBtn().disable();
			this.viewDetailsBtn.disable();
			this.toolBar.getDeleteBtn().enable();
		}
	},
	setGridButtonsInitialState: function(){
		this.toolBar.getEditBtn().disable();
		this.viewDetailsBtn.disable();
		this.toolBar.getDeleteBtn().disable();
	}
});

Ext.reg('vaccinationschedules-list-grid-panel', administration.vaccinationSchedule.manage.VaccinationSchedulesListGridPanel);
