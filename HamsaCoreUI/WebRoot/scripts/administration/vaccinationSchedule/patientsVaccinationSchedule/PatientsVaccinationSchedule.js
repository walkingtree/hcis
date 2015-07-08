Ext.namespace("administration.vaccinationSchedule.patientsVaccinationSchedule");

administration.vaccinationSchedule.patientsVaccinationSchedule.PatientsVaccinationSchedule = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
 	initComponent : function() {
		this.sequenceNumber = null;
		
		this.widgets = new administration.vaccinationSchedule.Widgets();

		this.vaccinationsScheduleSelectionPanel = new administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionPanel();

		this.table = new administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionGridPanel();

		this.buttonsPanel = new utils.ButtonsPanel();
		
		var tempThis = this;
		this.vaccinationsScheduleSelectionPanel.widgets.getVaccinationScheduleCombo().addListener('select', 
			function( combo ){
				this.getVaccinationScheduleDetails();
		},this);
		
		this.vaccinationsScheduleSelectionPanel.widgets.getAddBtn().addListener('click',function(){
			this.table.gridPnl.enable();
			this.vaccinationsScheduleSelectionPanel.widgets.getVaccinationScheduleCombo().enable();
			this.buttonsPanel.enable();
			this.widgets.getPatientIdTxf().enable();
			this.handleAddBtn();
			this.vaccinationsScheduleSelectionPanel.widgets.
				 getStartDateFld().setMinValue( new Date().clearTime() );
		},this);
		
		this.vaccinationsScheduleSelectionPanel.widgets.getDoctorCombo().addListener('select', 
			function( combo ){
		},this);
		
		this.widgets.getPatientIdTxf().addListener("blur",function(patientIdTxf){
			this.vaccinationsScheduleSelectionPanel.widgets.getVaccinationScheduleCombo().enable();
			this.table.gridPnl.getStore().removeAll();
			this.getPatientDetails( patientIdTxf.getValue() );
		},this);
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);

		this.buttonsPanel.getResetBtn().on("click", function(){
			this.resetForm();
		}, this);
		
		this.table.rowActions.on({
			groupaction:function(grid, records, action, groupId) {
				if( action == 'edit_btn'){
					
					var scheduleStatusCode = records[0].data.scheduleStatusCode;
					
					if( !Ext.isEmpty( scheduleStatusCode ) && 
					     scheduleStatusCode != configs.SCHEDULE_STATUS_NOT_STARTED_CODE){
						
				     	error( "Cannot modify vaccination schedule" +
				     		   "<br>with vaccination status " +
				     		   "\""+records[0].data.scheduleStatusDesc+"\" ..!"+
				     		   "</br>", 
				     		   "Modify failed ..!");
				     		   
					}else{
						var config = {
							vaccinationSchedule : records[0].data.scheduleNameCode,
							doctor : records[0].data.assignedByDoctorCode,
							startDate : records[0].data.startDate
						}
						
						if( records[0].data.startDate < new Date().clearTime()){
							tempThis.vaccinationsScheduleSelectionPanel.widgets.
								 getStartDateFld().setMinValue( null );
						}
						tempThis.vaccinationsScheduleSelectionPanel.widgets.
								 getVaccinationScheduleCombo().setValue( config.vaccinationSchedule );
						tempThis.vaccinationsScheduleSelectionPanel.widgets.
								 getDoctorCombo().setValue( config.doctor );
						tempThis.vaccinationsScheduleSelectionPanel.widgets.
								 getStartDateFld().setValue( config.startDate );
						
						tempThis.getVaccinationScheduleDetails();
						
						tempThis.vaccinationsScheduleSelectionPanel.widgets.
										getVaccinationScheduleCombo().disable();
						tempThis.buttonsPanel.disable();
						tempThis.widgets.getPatientIdTxf().disable();
						grid.disable();
						tempThis.vaccinationsScheduleSelectionPanel.widgets.getAddBtn().enable();
						
						for( var i = 0 ; i < records.length ; i++){
							grid.getStore().remove( records[i] );
						}
					}
				}
			}
		});
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
				this.vaccinationsScheduleSelectionPanel.widgets.getAddBtn().enable();
			} else {
				this.sequenceNumber = null;
				this.buttonsPanel.getSaveBtn().disable();
				this.table.gridPnl.getStore().removeAll();
				this.vaccinationsScheduleSelectionPanel.widgets.getAddBtn().disable();
			}
		}, this); 
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
	            	items:[this.widgets.getPatientIdTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPatientNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getAgeTxf()]
				},/*{
					columnWidth:0.5,
	            	layout : 'form',
	            	style : 'padding-left:230px;',
					items:[this.widgets.getShowVaccinationDetailsBtn()]
			    },*/{
					columnWidth:1,
	            	layout : 'form',
					items:[this.vaccinationsScheduleSelectionPanel]
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
        administration.vaccinationSchedule.patientsVaccinationSchedule.PatientsVaccinationSchedule.superclass.initComponent.apply(this, arguments);

	},
	
	saveBtnClicked : function(){
		
		var config = this.initialConfig;
		var tmpThis = this;
		
		ScheduleManager.removePatientVaccinationSchedule(config.patientId,function(){
			var tableData = tmpThis.table.gridPnl.getStore().data.items;
		
			if( !Ext.isEmpty( tableData ) && tableData.length > 0){
				
				var patVaccShedList = new Array();
				var patientVaccinationScheduleDetailsBMList = new Array();
				
				var patVaccShedBM;
				var sequenceNumber;
				
				for( var i = 0 ; i < tableData.length ; i++){
					//Patient vaccination schedule info
					var patVaccSchInfo = tableData[i].data;
					
					if( sequenceNumber != patVaccSchInfo.sequenceNumber ){
						patVaccShedBM = null;
						sequenceNumber = null;
						patientVaccinationScheduleDetailsBMList = new Array();
					}
					
					if( patVaccShedBM == null || Ext.isEmpty( patVaccShedBM )){
						
						patVaccShedBM = {
							patientId : patVaccSchInfo.patientId,
							doctor : { code : patVaccSchInfo.assignedByDoctorCode , 
									   description : patVaccSchInfo.assignedByDoctorDesc },
							scheduleName : { code : patVaccSchInfo.scheduleNameCode,
											 description : patVaccSchInfo.scheduleNameDesc },
							startDate : patVaccSchInfo.startDate,
							userId : getAuthorizedUserInfo().userName,
							statusCode : { code : patVaccSchInfo.scheduleStatusCode, 
							               description : patVaccSchInfo.scheduleStatusDesc },
							patientVaccinationScheduleDetailsBMList:""
						}
						
						patVaccShedList.push( patVaccShedBM );
						sequenceNumber = patVaccSchInfo.sequenceNumber;
						
					}
					
					var patVaccSchDetBM = {
						scheduleName : { code:patVaccSchInfo.scheduleNameCode,
										 description:patVaccSchInfo.scheduleNameDesc },
						patientId : patVaccSchInfo.patientId,
						periodInDays : patVaccSchInfo.periodInDays,
						age : patVaccSchInfo.age,
						vaccinationCd : { code : patVaccSchInfo.vaccinationNameCode, 
										  description : patVaccSchInfo.vaccinationNameDesc },
						vaccinationTypeCd : { code : patVaccSchInfo.vaccinationTypeCode, 
											  description : patVaccSchInfo.vaccinationTypeDesc },
						dosage : patVaccSchInfo.dosage,
						doctorComments : patVaccSchInfo.doctorComments,
						userId : getAuthorizedUserInfo().userName,
						dueDate : patVaccSchInfo.dueDate,
						givenDate : patVaccSchInfo.givenDate,
						doctor: { code : patVaccSchInfo.givenByDoctor }
					}
					
					patientVaccinationScheduleDetailsBMList.push( patVaccSchDetBM );
					patVaccShedBM.patientVaccinationScheduleDetailsBMList = patientVaccinationScheduleDetailsBMList;
				}
				
				ScheduleManager.savePatientVaccinationSchedule( patVaccShedList , function(){
					tmpThis.resetForm();
				});
			}else{
				info("No information to save..!");
			}
		});
		
		
	},
	
	resetForm : function(config){
		this.getForm().reset();
		this.table.gridPnl.getStore().removeAll();
	},
	
	getPatientDetails : function(patientId){
		var tmpThis = this;
		
		if( !Ext.isEmpty( patientId ) && patientId.length > 0){
			PatientManagementController.getPatientDetails( patientId , 
				function( patientLiteBM ){
					var patientAge;
					
					if( patientLiteBM != null ){
						if( patientLiteBM.dateOfBirth != null && 
							!Ext.isEmpty(patientLiteBM.dateOfBirth)){
								
							patientAge  = calculateDOB(patientLiteBM.dateOfBirth)[0] + " years";
							
							Ext.apply(tmpThis.initialConfig,patientAge);
						}
						
						tmpThis.widgets.getPatientNameTxf().setValue( patientLiteBM.fullName );
						tmpThis.widgets.getAgeTxf().setValue( patientAge );
						
						Ext.apply(tmpThis.initialConfig,patientLiteBM);
					}
			});
			
			ScheduleManager.getPatientVaccinationSchedules( patientId , 
				function( patientVaccinationScheduleBMList ){
					if( patientVaccinationScheduleBMList != null && 
						patientVaccinationScheduleBMList.length > 0){
						var patVaccSchListObj = {
							patVaccSchList : patientVaccinationScheduleBMList
						}
						Ext.apply(tmpThis.initialConfig,patVaccSchListObj);
						
						tmpThis.loadPatientVaccinationShedulesInGrid( patientVaccinationScheduleBMList );
					}
			});
		}
	},
	
	handleAddBtn : function(){
		var config = this.initialConfig;
		var record = this.table.getGrid().getStore().recordType;
		
		var scheduleNameCode = this.vaccinationsScheduleSelectionPanel.widgets.getVaccinationScheduleCombo().getValue();
		var scheduleNameDesc = this.vaccinationsScheduleSelectionPanel.widgets.getVaccinationScheduleCombo().lastSelectionText;
		var doctorCode = this.vaccinationsScheduleSelectionPanel.widgets.getDoctorCombo().getValue();
		var doctorDesc = this.vaccinationsScheduleSelectionPanel.widgets.getDoctorCombo().lastSelectionText;
		var startDate = this.vaccinationsScheduleSelectionPanel.widgets.getStartDateFld().getValue();
		var patientId = this.widgets.getPatientIdTxf().getValue();
		
		var seqNbr;
		
		if( this.sequenceNumber == null ){
			this.sequenceNumber = -1;
			seqNbr = this.sequenceNumber;
		}else{
			seqNbr = --this.sequenceNumber;
		}
		
		if(Ext.isEmpty( scheduleNameCode )){
			error( schldVaccineMsg.scheduleIsEmpty );
			return;
		}
		
		if(Ext.isEmpty( doctorCode )){
			error( schldVaccineMsg.doctorIsEmpty );
			return;
		}
		
		if(Ext.isEmpty( startDate )){
			error( schldVaccineMsg.startDateIsEmpty );
			return;
		}
		
		if( config.vaccSchDetList != null &&
				!Ext.isEmpty( config.vaccSchDetList ) &&
					config.vaccSchDetList.length > 0 ){

			for( var i = 0; i < config.vaccSchDetList.length ; i++){
				var dueDate = this.getDueDate( startDate, config.vaccSchDetList[i].periodInDays);
				
				var details = new record({
					hideDoctorCommentIcon  : false,
				    hideCancelVaccinationIcon : false,
				    hideCancelVaccinationScheduleIcon : false,
					hideEditVaccinationScheduleIcon:false,
					isNewRecord: true,
					sequenceNumber : seqNbr,
					
					userId : getAuthorizedUserInfo().userName,
					scheduleNameCode : scheduleNameCode,
					scheduleNameDesc : scheduleNameDesc,
					
					assignedByDoctorCode : doctorCode,
					assignedByDoctorDesc : doctorDesc,
					scheduleStatusCode :   configs.SCHEDULE_STATUS_NOT_STARTED_CODE,
					scheduleStatusDesc :   configs.SCHEDULE_STATUS_NOT_STARTED_DESC,
					startDate :	startDate,
					age : config.vaccSchDetList[i].age,
					patientId : config.patientId,
				    periodInDays : config.vaccSchDetList[i].periodInDays,
				    scheduleNameCode : config.vaccSchDetList[i].scheduleName,
					scheduleNameDesc : scheduleNameDesc,
				    vaccinationNameCode  : config.vaccSchDetList[i].vaccinationName.code,
				    vaccinationNameDesc  : config.vaccSchDetList[i].vaccinationName.description,
				    vaccinationTypeCode  : config.vaccSchDetList[i].vaccinationType.code,
				    vaccinationTypeDesc  : config.vaccSchDetList[i].vaccinationType.description,
				    dosage  : config.vaccSchDetList[i].dosage,
				    dueDate  : dueDate,
				    userId  : config.vaccSchDetList[i].userName,
				    deletedFlag  : config.vaccSchDetList[i].deletedFlag
				});
				
				this.table.getGrid().getStore().add( details );
			}
			
			this.vaccinationsScheduleSelectionPanel.resetData();		
		}
	},
	
	getDueDate : function( startDate , durationInDays){
		var oneDayInMilliSec = 24 * 60 * 60 * 1000;
		var endDateInMilliSec = 0;
		if( !Ext.isEmpty( durationInDays ) ){
			endDateInMilliSec = durationInDays * oneDayInMilliSec;
		}
		var dueDate = new Date(startDate.getTime() + endDateInMilliSec);
		
		return dueDate;
	},
	
	loadPatientVaccinationShedulesInGrid : function( patientVaccSchBMList ){
		
		if(!Ext.isEmpty( patientVaccSchBMList )){
			for( var i = 0; i < patientVaccSchBMList.length ; i++){
				var patVaccSchDetBMList = patientVaccSchBMList[i].patientVaccinationScheduleDetailsBMList;
				
				var sheduleStatus = patientVaccSchBMList[i].statusCode.code ;
				
				var patVaccSchBM = {
					hideCancelVaccinationScheduleIcon : 
						sheduleStatus == configs.SCHEDULE_STATUS_NOT_STARTED_CODE ? false : true,
					hideEditVaccinationScheduleIcon :
						sheduleStatus == configs.SCHEDULE_STATUS_NOT_STARTED_CODE ? false : true,
					patientId : patientVaccSchBMList[i].patientId,
					userId : patientVaccSchBMList[i].userID,
					sequenceNumber : patientVaccSchBMList[i].sequenceNumber,
					scheduleNameCode : patientVaccSchBMList[i].scheduleName.code,
					scheduleNameDesc : patientVaccSchBMList[i].scheduleName.description,
					
					assignedByDoctorCode : patientVaccSchBMList[i].doctor.code,
					assignedByDoctorDesc : patientVaccSchBMList[i].doctor.description,
					scheduleStatusCode :   patientVaccSchBMList[i].statusCode.code,
					scheduleStatusDesc :   patientVaccSchBMList[i].statusCode.description,
					startDate :	patientVaccSchBMList[i].startDate
				};
				
				this.loadPatVaccSchDetailsInGrid( patVaccSchBM, patVaccSchDetBMList);
			}
		}
	},
	
	loadPatVaccSchDetailsInGrid : function( patVaccSchBM , patVaccSchDetBMList ){
		
		if( patVaccSchDetBMList!= null && !Ext.isEmpty( patVaccSchDetBMList )){
			
			var record = this.table.getGrid().getStore().recordType;
			
			for( var i = 0 ; i < patVaccSchDetBMList.length ; i++){
				
				var details = new record({
					hideCancelVaccinationScheduleIcon : patVaccSchBM.hideCancelVaccinationScheduleIcon,
					hideEditVaccinationScheduleIcon : patVaccSchBM.hideEditVaccinationScheduleIcon,
					patientId : patVaccSchBM.patientId,
					userId : patVaccSchBM.userId,
					sequenceNumber : patVaccSchBM.sequenceNumber,
					scheduleNameCode : patVaccSchBM.scheduleNameCode,
					scheduleNameDesc : patVaccSchBM.scheduleNameDesc,
					assignedByDoctorCode : patVaccSchBM.assignedByDoctorCode,
					assignedByDoctorDesc : patVaccSchBM.assignedByDoctorDesc,
					scheduleStatusCode :   patVaccSchBM.scheduleStatusCode,
					scheduleStatusDesc :  patVaccSchBM.scheduleStatusDesc,
					startDate :	patVaccSchBM.startDate,
					isNewRecord: false,
					
					hideCancelVaccinationIcon : Ext.isEmpty(patVaccSchDetBMList[i].givenDate) ? false : true,
					hideDoctorCommentIcon : false,
					subSequenceNumber : patVaccSchDetBMList[i].subSequenceNumber,
			    	periodInDays : patVaccSchDetBMList[i].periodInDays,
			   		age : patVaccSchDetBMList[i].age,
			    	vaccinationNameCode : patVaccSchDetBMList[i].vaccinationCd.code,
			    	vaccinationNameDesc : patVaccSchDetBMList[i].vaccinationCd.description,
			    	vaccinationTypeCode : !Ext.isEmpty( patVaccSchDetBMList[i].vaccinationTypeCd )?
												patVaccSchDetBMList[i].vaccinationTypeCd.code:null,
				    vaccinationTypeDesc : !Ext.isEmpty( patVaccSchDetBMList[i].vaccinationTypeCd )?
				    							patVaccSchDetBMList[i].vaccinationTypeCd.description: null,
				    dosage : patVaccSchDetBMList[i].dosage,
				    doctorComments : patVaccSchDetBMList[i].doctorComments,
				    dueDate : patVaccSchDetBMList[i].dueDate,
				    givenDate : patVaccSchDetBMList[i].givenDate,
				    givenByDoctor : !Ext.isEmpty(patVaccSchDetBMList[i].doctor) ? 
				    				patVaccSchDetBMList[i].doctor.code :
				    				null,
				    givenByDoctorDesc : !Ext.isEmpty(patVaccSchDetBMList[i].doctor) ?
				    					patVaccSchDetBMList[i].doctor.description :
				    					null
				});
				
				this.table.getGrid().getStore().add( details );
			}	
		}	
	},
	
	getVaccinationScheduleDetails : function(){
		var tempThis = this;
		var vaccinationScheduleName = this.vaccinationsScheduleSelectionPanel.
										   widgets.getVaccinationScheduleCombo().getValue();
		if(!Ext.isEmpty( vaccinationScheduleName )){
			ScheduleManager.getVaccinationScheduleDetails( vaccinationScheduleName ,
				function( vaccinationScheduleDetailsList ){
					if( vaccinationScheduleDetailsList != null && 
						 vaccinationScheduleDetailsList.length > 0){
						var vaccSchDetListObj = {
							vaccSchDetList : vaccinationScheduleDetailsList
						};
					 	Ext.apply(tempThis.initialConfig,vaccSchDetListObj);
					}
				});
		}
	}
});

Ext.reg('patients-vaccination-schedule', administration.vaccinationSchedule.patientsVaccinationSchedule.PatientsVaccinationSchedule);
