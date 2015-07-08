Ext.namespace("OT.manageOT"); 

OT.manageOT.OTSearchPanel = Ext.extend(Ext.Panel,{
	initComponent : function(){
		this.searchCriteriaWidgets = new OT.manageOT.OTWidgets();
		this.searchCriteriaWidgets.getSurgeryCbx().anchor = "90%";
		
		this.otSearchGrid = new OT.manageOT.OTSearchGrid({otSearchPanel : this});
		
		this.searchGrid = new Ext.Panel({
			frame : true,
			items : this.otSearchGrid
		});
		
		this.searchButtonPanel = new utils.SearchButtonsPanel();
//		
//		this.otCodeTxf = new Ext.form.TextField({
//			name : 'otCode',
//			fieldLabel : otMsg.otCode,
//			anchor : '90%'
//		});
		
		this.searchCriteriaPanel = new Ext.form.FormPanel({
			layout : 'column',
			items : [
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getOtCodeTxf() 
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getOtNameTxf()
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getBedNbrCbx()
		         },
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.searchCriteriaWidgets.getSurgeryCbx()
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
			this.otSearchGrid.loadGrid();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);

		
		Ext.applyIf(this,{
			items : [
		         this.searchCriteriaPanel,
		         this.searchButtonPanel,
		         this.searchGrid
			]
		});
		
		OT.manageOT.OTSearchPanel.superclass.initComponent.apply(this,arguments);
	},
	
	searchBtnClicked : function(){
		var config = {
		otId : this.searchCriteriaWidgets.getOtCodeTxf().getValue(),
		otName : this.searchCriteriaWidgets.getOtNameTxf().getValue(),
		bedNumber : this.searchCriteriaWidgets.getBedNbrCbx().getValue(),
		surgeryCode : this.searchCriteriaWidgets.getSurgeryCbx().getValue()
		};
		
		this.otSearchGrid.loadGrid( config );
		
		
	},
	
	resetBtnClicked : function(){
		this.searchCriteriaPanel.getForm().reset();
		this.otSearchGrid.loadGrid();
	},
	getOTSearchGridPanel : function(){
    	return this.otSearchGrid;
    }
});