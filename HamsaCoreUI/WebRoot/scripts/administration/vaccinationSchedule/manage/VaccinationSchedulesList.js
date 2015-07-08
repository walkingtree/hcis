Ext.namespace("administration.vaccinationSchedule.manage");

administration.vaccinationSchedule.manage.VaccinationSchedulesList = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:true,
	
	initComponent : function() {

		this.searchPanel = new administration.vaccinationSchedule.manage.VaccinationScheduleSearchPanel();
		this.gridPanel = new administration.vaccinationSchedule.manage.VaccinationSchedulesListGridPanel();
		
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
			   msg: schldVaccineMsg.deleteMessage,
			   buttons: Ext.Msg.YESNO,
			   fn: function( btnValue ){
			   		if( btnValue == configs.yes){
						tmpThis.delBtnClicked();			   		
			   		}
			   },
			   icon: Ext.MessageBox.QUESTION
			});
			
		},this);
		
		this.gridPanel.getViewDetailsBtn().on('click',function(){
			this.viewVaccinationScheduleDetails();
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
        administration.vaccinationSchedule.manage.VaccinationSchedulesList.superclass.initComponent.apply(this, arguments);
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.loadData( searchCriteria ); 		
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.gridPanel.getReset();
		this.gridPanel.loadData();
	},
	
	addBtnClicked : function(){
		var tmpThis = this;
		var addPanel = new administration.vaccinationSchedule.configure.AddVaccinationSchedule({isPopup: true,
																								mode:schldVaccineMsg.addMode});
		addPanel.frame = true;
		addPanel.title = schldVaccineMsg.addVaccinationSchedule; 
		addPanel.closable = true;
		addPanel.height = 420;

		var newPanel = layout.getCenterRegionTabPanel().add(addPanel);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddVaccinationScheduleWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this, null ,tmpThis );
	},
	
	editBtnClicked : function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		var active = false;
		if( selectedRowData.activeFlag == "YES" ){
			active = true;
		}
		var configData = {
			scheduleName : selectedRowData.scheduleName,
			description : selectedRowData.scheduleDesc,
   			ageGroup : selectedRowData.ageGroup,
   			activeFlag : active,
   			detailsList: selectedRowData.vaccinationScheduleDetailList
		};
		
		var tmpThis = this;
		var editPanel = new administration.vaccinationSchedule.configure.AddVaccinationSchedule({isPopup: true,
																								 mode:schldVaccineMsg.editMode});
		editPanel.frame=true;
		editPanel.title = schldVaccineMsg.editVaccinationSchedule; 
		editPanel.closable = true;
		editPanel.height = 420;
		
		var newPanel = layout.getCenterRegionTabPanel().add(editPanel);
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
		editPanel.loadData( configData );
		
		Ext.ux.event.Broadcast.subscribe('closeEditVaccinationScheduleWindow', function(){
			
//			layout.getCenterRegionTabPanel().remove( newPanel, true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			
			this.gridPanel.loadData();
		}, this, null ,tmpThis);
		
	},
	
	delBtnClicked : function(){
		var tmpThis = this;
		var selectedRows = this.gridPanel.getSelection();
		var scheduleNameList = new Array();
		
		if( !Ext.isEmpty( selectedRows ) &&  selectedRows.length > 0 ){
			for( var i = 0; i<selectedRows.length; i++ ){
				scheduleNameList.push( selectedRows[i].data.scheduleName ) ;
			}
		}
		ScheduleManager.removeVaccinationSchedules(scheduleNameList,{
			callback:function(){
				vaccinationScheduleStore.reload();
				this.gridPanel.loadData();
			},
			callbackScope:this
		});
	},
	viewVaccinationScheduleDetails: function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		var detailsList = selectedRowData.vaccinationScheduleDetailList;
		if( !Ext.isEmpty( detailsList ) && detailsList.length > 0 ){
			var tablePanel = new administration.vaccinationSchedule.configure.VaccinationsSelectionGridPanel();
			var window = new Ext.Window({
				height: '70%',
				width:'60%',
				title:'View vaccination schedule details',
				modal:true,
				buttonAlign : 'right',
				items:tablePanel.getGrid(),
				buttons:[
					new Ext.Button({
						text : "Close",
						iconCls : 'cross_icon',
						handler : function(){ window.close();}
					})
				]
			});
			tablePanel.getGrid().getTopToolbar().hide();
			tablePanel.getGrid().getColumnModel().setHidden( 0, true );
			
			window.show();
			tablePanel.loadGridData( detailsList );
		}
	}
});

Ext.reg('vaccinationschedules-list-panel', administration.vaccinationSchedule.manage.VaccinationSchedulesList);
