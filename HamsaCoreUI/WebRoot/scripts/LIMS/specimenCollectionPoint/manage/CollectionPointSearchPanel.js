Ext.namespace("LIMS.specimenCollectionPoint.manage"); 

LIMS.specimenCollectionPoint.manage.CollectionPointSearchPanel = Ext.extend(Ext.form.FormPanel,{
	height : 500,
	frame : true,
	border : true,
	initComponent : function(){

		this.collectionPointSearchCriteria = new LIMS.specimenCollectionPoint.manage.CollectionPointSearchCriteria();
		
	
		this.collectionPointSearchGrid = new LIMS.specimenCollectionPoint.manage.CollectionPointSearchGrid();
		
		this.searchButtonsPanel = new utils.SearchButtonsPanel();
		
		this.searchButtonsPanel.searchBtn.on('click',function(){
			this.searchBtnClicked();
		},this);
		
		this.searchButtonsPanel.resetBtn.on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.collectionPointSearchGrid.gridToolbar.addBtn.on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.collectionPointSearchGrid.gridToolbar.editBtn.on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	
		Ext.applyIf(this,{
			layout : 'column',
			items : [
		         this.collectionPointSearchCriteria,
		         this.searchButtonsPanel,
		         this.collectionPointSearchGrid
	         ]
		});
	
		LIMS.specimenCollectionPoint.manage.CollectionPointSearchPanel.superclass.initComponent.apply(this,arguments);
	},
	
	searchBtnClicked : function(){
		var searchCriteria = this.getForm().getValues();
		this.collectionPointSearchGrid.loadGrid( searchCriteria )
	},
	
	resetBtnClicked : function(){
		this.getForm().reset();
		this.collectionPointSearchGrid.reset();
	},
	
	addBtnClicked : function(){

		var mainThis = this;
			
		var addCollectionPoint = new LIMS.specimenCollectionPoint.ConfigureCollectionPoint();
		addCollectionPoint.closable = true;
		addCollectionPoint.frame = true;
		addCollectionPoint.title = limsMsg.addCollectionPoint;
		addCollectionPoint.height = 420;

		var activeTab = layout.getCenterRegionTabPanel().add(addCollectionPoint);
		
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();	
		
		Ext.ux.event.Broadcast.subscribe('closeAddCollectionPoint',function(){
//			layout.getCenterRegionTabPanel().remove( activeTab , true );
			layout.getCenterRegionTabPanel().setActiveTab( mainThis );
			layout.getCenterRegionTabPanel().doLayout();
			mainThis.collectionPointSearchGrid.reset();
		},this,null,mainThis);

		
	},
	
	editBtnClicked : function(){
		
		var mainThis = this;
		
		var collectionPointId = this.collectionPointSearchGrid.getSelectedData()[0].data.collectionPointId;
		
		CollectionPointManager.getCollectionPointForId( collectionPointId ,function( collectionPointBM ){
			var editCollectionPoint = new LIMS.specimenCollectionPoint.ConfigureCollectionPoint();
			
			editCollectionPoint.setValues( collectionPointBM );
			editCollectionPoint.isEditCollecionPoint = true;
			
			editCollectionPoint.closable = true;
			editCollectionPoint.frame = true;
			editCollectionPoint.title = limsMsg.editCollectionPoint;
			editCollectionPoint.height = 420;

			var activeTab = layout.getCenterRegionTabPanel().add(editCollectionPoint);
			
			layout.getCenterRegionTabPanel().setActiveTab( activeTab );
			layout.getCenterRegionTabPanel().doLayout();		
			Ext.ux.event.Broadcast.subscribe('closeEditCollectionPoint',function(){
//				layout.getCenterRegionTabPanel().remove( activeTab , true );
				layout.getCenterRegionTabPanel().setActiveTab( mainThis );
				layout.getCenterRegionTabPanel().doLayout();
				mainThis.collectionPointSearchGrid.reset();
				mainThis.collectionPointSearchGrid.setGridButtonsState(mainThis.collectionPointSearchGrid.gridPnl.getSelectionModel());
			},this, null , mainThis);
			
		});
		
	}
});