Ext.namespace("LIMS.technique");

LIMS.technique.SearchTechnique = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:false,
	
	initComponent : function() {

		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		this.searchPanel = new LIMS.technique.TechniqueSearchPanel();
		
		this.gridPanel = new LIMS.technique.TechniqueListGridPanel();
		
		
		
		this.searchPanel.getButtonPanel().getSearchButton().on('click',function(){
			this.searchButtonClicked();
		},this);
		
		this.searchPanel.getButtonPanel().getResetButtton().on('click',function(){
			this.resetButtonClicked();
		},this);
		
		this.gridPanel.getToolbar().getAddBtn().on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.gridPanel.getToolbar().getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.gridPanel.getToolbar().getDeleteBtn().on('click',function(){
			var tmpThis = this;
			Ext.Msg.show({
			   msg: limsMsg.deleteMessage,
			   buttons: Ext.Msg.YESNO,
			   fn: function( btnValue ){
			   		if( btnValue == configs.yes){
						tmpThis.delBtnClicked();			   		
			   		}
			   },
			   icon: Ext.MessageBox.QUESTION
			});
			
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		Ext.applyIf(this, {
            items: [
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.searchPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.gridPanel]
			    }
			]            
        });
		LIMS.technique.SearchTechnique.superclass.initComponent.apply(this, arguments);
		
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.dataStore.removeAll();
		this.gridPanel.loadData( searchCriteria );
//		this.gridPanel.dataStore.load({params:{start:0, limit:10}, arg:[null,searchCriteria[''],null]});
		 		
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.gridPanel.getReset();
		this.gridPanel.loadData();
	},
	
	addBtnClicked : function(){
		var tmpThis = this;
		var addPanel = new LIMS.technique.AddTechnique( {
							isPopup : true,
							mode : limsMsg.addMode
						});
		
		addPanel.frame=true;
		addPanel.title = limsMsg.ttlAddTechnique; 
		addPanel.closable = true;
		addPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add(addPanel);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddTechniqueWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this, null ,tmpThis);
	},
	
	editBtnClicked : function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;

		var configData = {
				techniqueId : selectedRowData.techniqueId,
				techniqueName : selectedRowData.name,
				isTechnique : selectedRowData.isTechnique,
				techniqueDesc : selectedRowData.description,
	   			detailsList: selectedRowData.vaccinationScheduleDetailList
			};
		
		var tmpThis = this;
		var editPanel = new LIMS.technique.AddTechnique( {
														isPopup : true,
														mode : limsMsg.editMode,
														techniqueId : selectedRowData.techniqueId
		});
		
		editPanel.frame=true;
		editPanel.title = limsMsg.ttlEditTechnique; 
		editPanel.closable = true;
		editPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add(editPanel);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		editPanel.loadData( configData );
		
		Ext.ux.event.Broadcast.subscribe('closeEditTechniqueWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this , null , tmpThis);
		
	},
	
	delBtnClicked : function(){
		var selectedRows = this.gridPanel.getSelection();
		var techniqueIdList = new Array();
		
		if( !Ext.isEmpty( selectedRows ) &&  selectedRows.length > 0 ){
			for( var i = 0; i<selectedRows.length; i++ ){
				techniqueIdList.push( selectedRows[i].data.techniqueId ) ;
			}
		}
		LabConfigManager.removeTechniqueReagent(techniqueIdList,{
			callback:function(){
//				vaccinationScheduleStore.reload();
				this.gridPanel.loadData();
			},
			callbackScope:this
		});
	}

});

