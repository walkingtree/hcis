Ext.namespace("LIMS.testAttribute");

LIMS.testAttribute.SearchTestAttribute = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:false,
	
	initComponent : function() {

		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		this.searchPanel = new LIMS.testAttribute.TestAttributeSearchPanel();
		
		this.gridPanel = new LIMS.testAttribute.TestAttributeListGridPanel();
		
		
		
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
			   msg: limsMsg.deleteAttributeMsg,
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
		
		LIMS.testAttribute.SearchTestAttribute.superclass.initComponent.apply(this, arguments);
		
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
		var addPanel = new LIMS.testAttribute.AddTestAttribute( {
							isPopup : true,
							mode : limsMsg.addMode
						});
		addPanel.frame = true;
		addPanel.title = limsMsg.ttlAddAttributes; 
		addPanel.closable = true;
		addPanel.height = 420;

		var newPanel = layout.getCenterRegionTabPanel().add( addPanel );
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddTestAttributeWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			this.gridPanel.loadData();
		}, this, null , tmpThis);
	},
	
	editBtnClicked : function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;

		var tmpThis = this;
		var editPanel = new  LIMS.testAttribute.AddTestAttribute( {
														isPopup : true,
														mode : limsMsg.editMode,
														valueType :selectedRow.typeCode
						});

		editPanel.frame = true;
		editPanel.title = limsMsg.ttlEditAttributes; 
		editPanel.closable = true;
		editPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add( editPanel );
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
//		editPanel.valueTypeChanged( selectedRowData.typeCode);
		
		editPanel.loadData( selectedRowData );
		
		Ext.ux.event.Broadcast.subscribe('closeedittestattributewindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this, null,tmpThis );
		
	},
	
	delBtnClicked : function(){
		var selectedRows = this.gridPanel.getSelection();
		var attributeCodeList = new Array();
		
		if( !Ext.isEmpty( selectedRows ) &&  selectedRows.length > 0 ){
			for( var i = 0; i<selectedRows.length; i++ ){
				attributeCodeList.push( selectedRows[i].data.attributeCode ) ;
			}
		}
		
		LabConfigManager.removeTestAttribute(attributeCodeList,{
			callback:function(){
				this.gridPanel.loadData();
			},
			callbackScope:this
		});
	}
	
});

