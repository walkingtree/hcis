Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.SearchLaboratory = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	monitorValid : true,
	height : '100%',
	frame:true,
	
	initComponent : function() {

		this.searchPanel = new LIMS.labConfiguration.LabSearchPanel();
		this.gridPanel = new LIMS.labConfiguration.LaboratoryListGrid();
		this.buttonsPanel = new utils.SearchButtonsPanel();
		
		
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
			   msg: limsMsg.deleteLabMsg,
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
		LIMS.labConfiguration.SearchLaboratory.superclass.initComponent.apply(this, arguments);
	},
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.dataStore.removeAll();
		this.gridPanel.loadData( searchCriteria ); 		
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.gridPanel.getReset();
		this.gridPanel.loadData();
	},
	
	addBtnClicked : function(){
		var tmpThis = this;
		var addPanel = new LIMS.labConfiguration.AddLabDetail({isPopup: true,
																mode:limsMsg.addMode});
		addPanel.frame = true;
		addPanel.title = limsMsg.addLab; 
		addPanel.closable = true;
		addPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add(addPanel);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddLabWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this , null ,tmpThis);
	},
	
	editBtnClicked : function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		
		var configData = {
			labId : selectedRowData.labId,
			hospitalName :selectedRowData.hospitalCode,
			labName : selectedRowData.labName,
			labType : selectedRowData.labType,
   			businessName : selectedRowData.businessName,
   			branchName :  selectedRowData.branchName,
   			labOperatorID : selectedRowData.labOperatorID,
   			directionFromKnownPlace :selectedRowData.directionFromKnownPlace,
   			street:selectedRowData.street,
   			locality : selectedRowData.locality,
   			city : selectedRowData.city,
   			state : selectedRowData.stateCode,
   			country : selectedRowData.countryCode,
   			emailID	: selectedRowData.emailID,
   			phoneNumber : selectedRowData.phoneNumber,
   			mobileNumber : selectedRowData.mobileNumber,
   			faxNumber : selectedRowData.faxNumber
		};
		
		var tmpThis = this;
		var editPanel = new LIMS.labConfiguration.AddLabDetail({isPopup: true,
																 mode:limsMsg.editMode,
																 labId : selectedRowData.labId
		});
		
		
		editPanel.frame = true;
		editPanel.title = limsMsg.editLabDetail; 
		editPanel.closable = true;
		editPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add(
				editPanel
		);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		editPanel.loadData( configData );
		
		Ext.ux.event.Broadcast.subscribe('closeEditLabWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this, null, tmpThis);
		
	},
	
	delBtnClicked : function(){
		var tmpThis = this;
		var selectedRows = this.gridPanel.getSelection();
		var labDetailIdList = new Array();
		
		if( !Ext.isEmpty( selectedRows ) &&  selectedRows.length > 0 ){
			for( var i = 0; i<selectedRows.length; i++ ){
				labDetailIdList.push( selectedRows[i].data.labId) ;
			}
		}
		LabDetailManager.removeLabDetail(labDetailIdList,{
			callback:function(){
//				labDetailStore.reload();
				this.gridPanel.loadData();
			},
			callbackScope:this
		});
	}

});

//Ext.reg('laboratory-list-panel', LIMS.LabConfiguration.LaboratoryList);
