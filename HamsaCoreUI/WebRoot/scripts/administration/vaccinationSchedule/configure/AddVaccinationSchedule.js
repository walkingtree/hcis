Ext.namespace("administration.vaccinationSchedule.configure");

administration.vaccinationSchedule.configure.AddVaccinationSchedule = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
 	initComponent : function() {
		this.recordStatus = null;
		this.seqNbr = null;
		
		this.widgets = new administration.vaccinationSchedule.Widgets();

		this.vaccinationsSelectionPanel = new administration.vaccinationSchedule.configure.VaccinationsSelectionPanel();

		this.table = new administration.vaccinationSchedule.configure.VaccinationsSelectionGridPanel();

		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);

		this.buttonsPanel.getResetBtn().on("click", function(){
			var tmpThis = this;
			Ext.Msg.show({
				msg: schldVaccineMsg.resetMessage,
				icon : Ext.MessageBox.QUESTION,
				buttons: Ext.Msg.YESNO,
				fn: function( btnValue ){
					if( btnValue == configs.yes ){
						tmpThis.resetForm();
					}
				}
			});
		}, this);
		
		this.vaccinationsSelectionPanel.getWidgets().getVaccinationNameCombo().on('select',
			function( combo, record, index ){
				var vaccinationCode = combo.getValue();
				var tmpThis = this;
				ScheduleManager.getVaccination( vaccinationCode , function(vaccinationBM){
					if( vaccinationBM != null && !Ext.isEmpty( vaccinationBM )){
						tmpThis.vaccinationsSelectionPanel.
							getWidgets().getAgeTxf().setValue( vaccinationBM.ageRange );
					}
				});
		},this);
		
		this.vaccinationsSelectionPanel.getWidgets().getAddBtn().on('click', function(){
			this.enableComponentsInEditCase();
			this.addHandler();
			this.vaccinationsSelectionPanel.getWidgets().getVaccinationNameCombo().enable();
			this.vaccinationsSelectionPanel.getWidgets().getPeriodTxf().enable();
			this.recordStatus = null;
			this.seqNbr = null;
		},this);
		
		this.table.getToolBar().getEditBtn().on('click', function(){
			this.disableComponentsInEditCase();
			this.vaccinationsSelectionPanel.getWidgets().getVaccinationNameCombo().disable();
			this.vaccinationsSelectionPanel.getWidgets().getPeriodTxf().disable();
			this.editHandler();
		},this);
		
		this.table.getToolBar().getDeleteBtn().on('click', function(){
			this.deleteHandler();
		},this);
		
		this.table.getGrid().on('cellclick', function(  gridPanel , rowIndex, columnIndex,e ){
			this.setGridButtonsState( gridPanel.getSelectionModel() );
		},this);
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && this.initialConfig.mode == schldVaccineMsg.editMode){
			this.widgets.getScheduleNameTxf().disable();
		}
		this.widgets.getScheduleNameTxf().on('blur',function( comp ){
			ScheduleManager.isVaccinationScheduleValid( comp.getValue(),{
				callback:function( valid ){
					if( !valid ){
						Ext.Msg.show({
						   msg: "Vaccination schedule with name '"+ comp.getValue()+"' already exist." +
						   		"<br>Please provide another schedule name and retry!",
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	comp.reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
	            	items:[this.widgets.getScheduleNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getScheduleDescTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeGroupTxf()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
	            	labelWidth:.001,
					items:[this.widgets.getIsActiveChk()]
			    },{
					columnWidth:1,
	            	layout : 'form',
					items:[this.vaccinationsSelectionPanel]
			    },{
					columnWidth:1,
	            	layout : 'form',
					items:[this.table.getGrid()]
			    },{
					columnWidth:1,
	            	layout : 'form',
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
        
        this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
				this.vaccinationsSelectionPanel.getWidgets().getAddBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
				this.vaccinationsSelectionPanel.getWidgets().getAddBtn().disable();
				this.recordStatus = null;
				this.seqNbr = null;
			}
		}, this); 
		
        administration.vaccinationSchedule.configure.AddVaccinationSchedule.superclass.initComponent.apply(this, arguments);

	},
	
	saveBtnClicked : function(){
		var inputValues = this.getForm().getValues();
		var tableData = this.table.getData();
		var activeFlag = false;
		if( !Ext.isEmpty( inputValues['activeFlag'] ) ){
			activeFlag = true;
		}
		
		var vaccinationScheduleBM = {
				scheduleName:this.widgets.getScheduleNameTxf().getValue(),
				description:inputValues['description'],
				ageGroup: inputValues['ageGroup'],
				userName: getAuthorizedUserInfo().userName, 
				activeFlag: activeFlag,
				vaccinationScheduleDetailList:tableData
		};
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && 
			this.initialConfig.mode == schldVaccineMsg.editMode ){
				
			var schedulename = this.widgets.getScheduleNameTxf().getValue();
			vaccinationScheduleBM.scheduleName = schedulename;
			
			ScheduleManager.modifyVaccinationSchedule( 
				vaccinationScheduleBM,{
				callback: function(){
					
					if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
						layout.getCenterRegionTabPanel().remove( this, true );
						Ext.ux.event.Broadcast.publish('closeEditVaccinationScheduleWindow');
					}
				},
				callbackScope:this
			});
		}
		else{
			ScheduleManager.addSchedule( vaccinationScheduleBM,{
				callback: function(){
					this.resetForm();
					vaccinationScheduleStore.reload();
					if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
						layout.getCenterRegionTabPanel().remove( this, true );
						Ext.ux.event.Broadcast.publish('closeAddVaccinationScheduleWindow');
					}
				},
				callbackScope:this
			});
		}
	},
	
	loadData : function( config ){
		this.config = config;
		this.getForm().setValues(config);
		this.loadGrid( config.detailsList );
	},
	
	resetForm : function( config ){
		this.getForm().reset();
		this.resetGrid();
		if( !Ext.isEmpty(this.initialConfig.mode) && this.initialConfig.mode == schldVaccineMsg.editMode ){
			loadData( this.config );
		}
	},
	addHandler: function(){
		var widgets = this.vaccinationsSelectionPanel.getWidgets();
		var record = this.table.getGrid().getStore().recordType;
		var tmpThis = this;
		var details = new record({
			seqNbr : tmpThis.seqNbr,
			recordStatus : tmpThis.recordStatus == 'edit'? tmpThis.recordStatus :'add',
			periodInDays: widgets.getPeriodTxf().getValue(),
			periodIndCode: 'D',
			periodInd: 'Days',
			age:widgets.getAgeTxf().getValue(),
			vaccinationCode:widgets.getVaccinationNameCombo().getValue(),
			vaccinationName:widgets.getVaccinationNameCombo().getRawValue(),
			dosage: widgets.getDosageTxf().getValue(),
			vaccinationTypeCode:widgets.getVaccinationTypeCombo().getValue(),
			vaccinationType:widgets.getVaccinationTypeCombo().getRawValue()
		});
		
		if( Ext.isEmpty( details.data.periodInDays ) ){
			error( schldVaccineMsg.periodIsEmpty );
			return;
		}
		
		if( Ext.isEmpty( details.data.vaccinationCode ) ){
			error( schldVaccineMsg.vaccinationIsEmpty );
			return;
		}
		
		var gridRows = this.table.getGrid().getStore().data.items;
		
		for( var i = 0 ; i < gridRows.length ; i++){
			if( gridRows[i].data.vaccinationCode == details.data.vaccinationCode &&
				gridRows[i].data.periodInDays == details.data.periodInDays ){
					error(" Vaccination with name : " +
						  details.data.vaccinationCode +
						  "<br>" +
						  	"& period : "+details.data.periodInDays +
						  "</br>" +
						  "<br>" +
						  	" has already been seleted ..!" + 
						  "</br>"
						  ,"Add failed ..!");
				
				return;
			}
		}
		
		details.period = details.period + details.periodInd;
		this.table.getGrid().getStore().add( details );
		this.vaccinationsSelectionPanel.resetData();
	},
	editHandler: function(){
		var selectedRow = this.table.getGrid().getSelectionModel().getSelections();
		this.vaccinationsSelectionPanel.setData( selectedRow[0].data );
		
		if( !Ext.isEmpty( selectedRow[0].data.seqNbr ) ){
			this.recordStatus = "edit";
			this.seqNbr = selectedRow[0].data.seqNbr;
		}
		
		this.table.getGrid().getStore().remove( selectedRow[0] );
		this.setInitialGridButtonState();
	},
	deleteHandler: function(){
		var selectedRows = this.table.getGrid().getSelectionModel().getSelections();
		var record = this.table.getGrid().getStore().recordType;
		
		for( var i =0; i<selectedRows.length; i++ ){
			if( !Ext.isEmpty( selectedRows[i].data.seqNbr ) ){
				
				selectedRows[i].data.recordStatus = "delete";
				
				var details = new record({
					seqNbr : selectedRows[i].data.seqNbr,
					deletedFlag : selectedRows[i].data.deletedFlag,
					userName : selectedRows[i].data.userName,
					recordStatus : selectedRows[i].data.recordStatus,
					periodInDays: selectedRows[i].data.periodInDays,
					periodIndCode: 'D',
					periodInd: 'Days',
					age:selectedRows[i].data.age,
					vaccinationCode:selectedRows[i].data.vaccinationCode,
					vaccinationName:selectedRows[i].data.vaccinationName,
					dosage: selectedRows[i].data.dosage,
					vaccinationTypeCode:selectedRows[i].data.vaccinationTypeCode,
					vaccinationType:selectedRows[i].data.vaccinationType
				});
				
				this.table.getGrid().getStore().remove( selectedRows[i] );
				this.table.getGrid().getStore().add( details );
				
			}else{
				this.table.getGrid().getStore().remove( selectedRows[i] );
			}
		}
		this.setInitialGridButtonState();
	},
	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.table.getToolBar().getEditBtn().disable();
		this.table.getToolBar().getDeleteBtn().disable();
		if( selectedRows.length == 1){
			this.table.getToolBar().getEditBtn().enable();
			this.table.getToolBar().getDeleteBtn().enable();
			
		} else if( selectedRows.length > 1){
			this.table.getToolBar().getEditBtn().disable();
			this.table.getToolBar().getDeleteBtn().enable();
		}
	},
	setInitialGridButtonState: function(){
		this.table.getToolBar().getEditBtn().disable();
		this.table.getToolBar().getDeleteBtn().disable();
	},
	resetGrid: function(){
		var rows = this.table.getGrid().getStore().getRange();
		for( var i =0; i<rows.length; i++ ){
			this.table.getGrid().getStore().remove( rows[i] );
		}
	},
	loadGrid: function( list ){
		if( !Ext.isEmpty( list )){
			var record = this.table.getGrid().getStore().recordType;
			for( var i = 0; i<list.length; i++ ){
				var data = new record({
					seqNbr:list[i].seqNbr,
					age : list[i].age,
					deletedFlag : list[i].deletedFlag,
					dosage : list[i].dosage,
					periodInDays : list[i].periodInDays,
					scheduleName : list[i].scheduleName,
					userName : list[i].userName,
					vaccinationCode : list[i].vaccinationName.code,
					vaccinationName : list[i].vaccinationName.description,
					vaccinationTypeCode : !Ext.isEmpty(list[i].vaccinationType) ? 
										  list[i].vaccinationType.code : null,
					vaccinationType : !Ext.isEmpty(list[i].vaccinationType) ? 
								       list[i].vaccinationType.description : null,
					periodInd : 'Days'
				});
				this.table.getGrid().getStore().add( data );
			}
		}
	},
	
	disableComponentsInEditCase : function(){
		this.table.getGrid().disable();
		this.buttonsPanel.disable();
//		this.widgets.getScheduleNameTxf().disable();
		this.widgets.getScheduleDescTxf().disable();
		this.widgets.getAgeGroupTxf().disable();
		this.widgets.getIsActiveChk().disable();
//		this.vaccinationsSelectionPanel
	},
	
	enableComponentsInEditCase : function(){
		this.table.getGrid().enable();
		this.buttonsPanel.enable();
//		this.widgets.getScheduleNameTxf().enable();
		this.widgets.getScheduleDescTxf().enable();
		this.widgets.getAgeGroupTxf().enable();
		this.widgets.getIsActiveChk().enable();
//		this.vaccinationsSelectionPanel
	}
});

Ext.reg('add-vaccination-schedule', administration.vaccinationSchedule.configure.AddVaccinationSchedule);
