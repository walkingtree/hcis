Ext.namespace("administration.checkList"); 

administration.checkList.CheckListSearchPanel = Ext.extend(Ext.Panel,{
	initComponent : function(){
		this.searchCriteriaWidgets = new administration.checkList.CheckListWidgets();
		
		this.searchGrid = new administration.checkList.CheckListSearchGrid({checkListSearchPanel : this});
		
		this.searchGridPanel = new Ext.Panel({
			frame : true,
			items : this.searchGrid
		});
		
		this.searchButtonPanel = new utils.SearchButtonsPanel();
		
		this.searchCriteriaPanel = new Ext.form.FormPanel({
			layout : 'column',
			items : [
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getCheckListNameTxf()
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getCheckListTypeCbx()
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getCheckListGroupTxf()
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getRoleCbx({hideTrigger:false})
		         }

	         ]
		});
		
		this.searchButtonPanel.getSearchButton().on('click',function(){
			this.searchBtnClicked();
		},this);
		

		this.searchButtonPanel.getResetButtton().on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.on('render',function(thisPanel){
			this.searchGrid.loadGrid();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

		
		Ext.applyIf(this,{
			items : [
		         this.searchCriteriaPanel,
		         this.searchButtonPanel,
		         this.searchGridPanel
			]
		});
		
		administration.checkList.CheckListSearchPanel.superclass.initComponent.apply(this,arguments);
	},
	
	searchBtnClicked : function(){
		var config = {
		checkListName : this.searchCriteriaWidgets.getCheckListNameTxf().getValue(),
		type : this.searchCriteriaWidgets.getCheckListTypeCbx().getValue(),
		groupName : this.searchCriteriaWidgets.getCheckListGroupTxf().getValue(),
		role : this.searchCriteriaWidgets.getRoleCbx().getValue()
		};
		
		this.searchGrid.loadGrid( config );
		
		
	},
	
	resetBtnClicked : function(){
		this.searchCriteriaPanel.getForm().reset();
		this.searchGrid.reset();
		this.searchGrid.loadGrid();
	},
	getSearchGrid : function(){
    	return this.searchGrid;
    }
});