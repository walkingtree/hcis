Ext.namespace("administration.vaccinationSchedule.patientsVaccinationSchedule");

administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionGridPanel = Ext.extend(Ext.Panel, {
   	  title : 'Selected vaccination schedules',
      layout : 'fit',
      border : false,
      frame : false,
      initComponent : function() {
		var tempThis = this;
		
		this.dueDateDtf = new Ext.form.WTCDateField({
	 	});
		
	 	this.givenDateDtf = new Ext.form.WTCDateField({
	 		maxValue:new Date()
	 	});
	 	
	 	this.doctorCombo = new Ext.form.ComboBox({
			fieldLabel : 'Doctor',
			mode : 'local',
			store : loadDoctorsCbx(),
			displayField : 'description',
			valueField : 'code',
			triggerAction : 'all',
			hiddenName : 'givenByDoctor',
			forceSelection : true,
			anchor:'98%'
		});
		
	 	this.record = Ext.data.Record.create([ 
		    { name: 'hideDoctorCommentIcon'},
		    { name: 'hideCancelVaccinationIcon'},
		    { name: 'hideCancelVaccinationScheduleIcon'},
		    { name: 'hideEditVaccinationScheduleIcon'},
		    { name: 'patientId', type:'int'},
		    { name: 'userId', type:'string'},
		    { name: 'sequenceNumber', type: 'int'},
		    { name: 'scheduleNameCode', type:'string'},
		    { name: 'scheduleNameDesc', type:'string'},
		    { name: 'isNewRecord', type:'boolean'},
		    
		    //Patient vaccination schedule record
		    { name: 'assignedByDoctorCode', type: 'string'},
		    { name: 'assignedByDoctorDesc', type: 'string'},
		    { name: 'scheduleStatusCode', type:'string'},
		    { name: 'scheduleStatusDesc', type:'string'},
		    { name: 'startDate', type: 'date'},

		    //Patient vaccination schedule details record
		    { name: 'subSequenceNumber', type: 'int'},
		    { name: 'periodInDays', type:'int'},
		    { name: 'age', type:'string'},
		    { name: 'vaccinationNameCode', type:'string'},
		    { name: 'vaccinationNameDesc', type:'string'},
		    { name: 'vaccinationTypeCode', type:'string'},
		    { name: 'vaccinationTypeDesc', type:'string'},
		    { name: 'dosage', type:'string'},
		    { name: 'doctorComments', type:'string'},
		    { name: 'dueDate', type:'date'},
		    { name: 'givenDate', type:'date'},
		    { name: 'givenByDoctor', type:'string'},
		    { name: 'givenByDoctorDesc', type:'string'},
		    
		    { name: 'deletedFlag', type:'boolean'}
		]);

		this.dataStore = new Ext.data.GroupingStore({
			data:[],
			reader: new Ext.data.ArrayReader({id:'id'}, this.record),
        	groupField:'sequenceNumber',
        	sortInfo: {field: 'subSequenceNumber', direction: "ASC"}
		});
		
		this.rowActions = new Ext.ux.grid.RowActions({
			header:'Actions',
			autoWidth:false,
			hideMode:'display',
			keepSelection:true,
			actions:[{
				iconCls:'edit_btn',
				tooltip:'Doctor comments',
				hideIndex:'hideDoctorCommentIcon'
			},{
				iconCls:'cross_icon',
				tooltip:'Cancel vaccination',
				hideIndex:'hideCancelVaccinationIcon'
			}],
			groupActions:[{
				iconCls:'cross_icon',
				qtip:'Cancel vaccination schedule',
				hideIndex:'hideCancelVaccinationScheduleIcon',
				align:'left'
			},{
				iconCls:'edit_btn',
				qtip:'Edit vaccination schedule',
				hideIndex:'hideEditVaccinationScheduleIcon',
				align:'left'
			}],
			callbacks:{
				'icon-plus':function(grid, record, action, row, col) {
				 }
			 }
		});
		
		this.rowActions.on({
			groupaction:function(grid, records, action, groupId) {
				if(action == 'cross_icon'){
					tempThis.deletePatientVaccinationSchedules( grid , records );
				}
			 },
			 
			action:function(grid, record, action, row, col) {
				if (action === 'edit_btn') {
					 var htmlEditor = new Ext.form.HtmlEditor({
					 	height : '100%',
					 	anchor:'98%'
					 });
				 	 
					 var commentsWindow = new Ext.Window({
						 title: schldVaccineMsg.addComments,
						 items:htmlEditor,
						 frame:true,
						 height:'100%',
						 width:'50.8%',
						 modal:true,
						 listeners:{
						 	 beforeshow : function(comp){
						 	 	if(!Ext.isEmpty(record.data.doctorComments)){
						 	 		htmlEditor.setValue( record.data.doctorComments );
						 	 	}
							 }						
						 },
						 buttons:[
						 	 new Ext.Button({
								 text : "Ok",
								 iconCls : 'accept-icon',
								 handler : function(){ 
								 	var doctorComments = htmlEditor.getValue();
								 	record.data.doctorComments = doctorComments;
								 	commentsWindow.close();
							 	}
							 })
						 ]
					 });
					 commentsWindow.show();
				}
				
				if( action === 'cross_icon' ){
					tempThis.deletePatientVaccinationScheduleDetails( grid , record );
				}
			}
		});
		
        this.gridColumns = [
         	{
               header :"Age",
               dataIndex :'age',
               width : 80
	        },{
	              header :"Vaccination name",
	              dataIndex :'vaccinationNameDesc',
	              width : 150
	        },{
	              header :"Vaccination type",
	              dataIndex :'vaccinationTypeDesc',
	              width : 150
	        },{
	              header :"Schedule name",
	              dataIndex :'scheduleNameCode',
	              hidden:true,
	              width : 150
	        },{
	              header :"Schedule name",
	              dataIndex :'scheduleNameDesc',
	              width : 150
	        },{
	              header :"Dosage",
	              dataIndex :'dosage',
	              width : 100
	        },{
	              header :"Due date",
	              dataIndex :'dueDate',
	              renderer : Ext.util.Format.dateRenderer('d/m/Y'),
	              editor:this.dueDateDtf,
	              width : 90
	        },{
	              header :"Given date",
	              dataIndex :'givenDate',
	              width : 90,
	              renderer : Ext.util.Format.dateRenderer('d/m/Y'),
	              editor: this.givenDateDtf
	        },{
	              header :"Doctor",
	              dataIndex :"givenByDoctor",
	              width : 150,
	              editor: this.doctorCombo,
	              renderer: function(v, m, r) {
	              	if(Ext.isEmpty( r.data.givenByDoctorDesc )){
	              		return this.editor.lastSelectionText;
	              	}else{
	              		return r.data.givenByDoctorDesc;	
	              	}
              	  }
	        },{
	              header :"Sequence number",
	              dataIndex :'sequenceNumber',
	              width : 75,
	              hidden: true
	        },this.rowActions
	  	];
	  	
      	this.gridPnl = new Ext.grid.EditorGridPanel({
			 border : false,
			 height : 300,
			 width:'100%',
			 frame : true,
		     stripeRows :true,
		     autoScroll :true,
		     remoteSort :true,
		     store : this.dataStore,
		     stripeRows: false,
		     viewConfig : {forceFit :true},
		     columns : this.gridColumns,
		     plugins:[ this.rowActions ],
		     view: new Ext.grid.GroupingView({
		        forceFit:true,
		        hideGroupedColumn:true,
		        enableGroupingMenu: false,
		        // custom grouping text template to display the number of items per group
		        groupTextTpl: '{[values.rs[0].data.scheduleNameDesc]}' +
		        			  ' ,  '+
		        			  'By : '+'{[values.rs[0].data.assignedByDoctorDesc]}' +
		        			  ' ,  '+
		        			  'Status : '+'{[values.rs[0].data.scheduleStatusDesc]}'
		    })
//		    listeners:{
//		    	afteredit: function(e){
//					var dataIndex = e.grid.getColumnModel().getDataIndex(e.column);
//					if(dataIndex == 'givenDate'){
//						var tmpThis = this;
//						var dueDate = e.record.data.dueDate;
//						var givenDate = e.record.data.givenDate;
//						if( dueDate > givenDate){
//							Ext.Msg.show({
//							   title:'Save Changes?',
//							   msg: 'Given date cannot be before due date ..!',
//							   icon: Ext.MessageBox.ERROR,
//							   buttons: Ext.Msg.OK,
//							   fn: function( btn ){
//							   	  if( btn == "ok"){
//							   	  	var test = tmpThis.givenDateDtf.getValue();
//							   	  	var test1 = 10;
//							   	  }
//							   }
//							   
//							});
//							error("Given date cannot be before dueDate");
//							return;
//						}
//					}
//				},
//				scope:this
//		    }
      	});

      	Ext.applyIf(this, {items: [{columnWidth : 1,items :[this.gridPnl]}]});

        administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionGridPanel.superclass.initComponent.apply(this, arguments);
     },
      
     getGrid : function(){
     	return this.gridPnl;
     },
     
     getData : function(){
		var storeValues = this.gridPnl.getStore().data.items;
		var patientVaccinationScheduleDetailsBMList = new Array();
	
		for(var i =0; i<storeValues.length;i++){
			var tmpObj = {
				scheduleName : storeValues.scheduleName,
				patientId : storeValues.patientId,
				periodInDays : storeValues.periodInDays,
				age : storeValues.age,
				vaccinationCd : {code:storeValues.vaccinationNameCode,description:null},
				vaccinationTypeCd : {code:storeValues.vaccinationTypeCode,description:null},
				dosage : storeValues.dosage,
				doctorComments : storeValues.doctorComments,
				userId : storeValues.userName,
				dueDate : storeValues.dueDate,
				givenDate : storeValues.givenDate,
				doctor : {code:storeValues.doctorCode, description:null}
			}
			patientVaccinationScheduleDetailsBMList.push( tmpObj );
			
		}
		return patientVaccinationScheduleDetailsBMList;
     },
     
     deletePatientVaccinationSchedules : function( grid , records ){
     	
     	var scheduleStatusCode = records[0].data.scheduleStatusCode;
		
     	if( records[0].data.sequenceNumber < 0 ){
			for( var i = 0 ; i < records.length ; i++){
				grid.getStore().remove( records[i] );
			}
		}else {
			
			if( !Ext.isEmpty( scheduleStatusCode ) && 
			     scheduleStatusCode != configs.SCHEDULE_STATUS_NOT_STARTED_CODE){
				
		     	error( "Cannot cancel vaccination schedule" +
		     		   "<br>with vaccination status " +
		     		   "\""+records[0].data.scheduleStatusDesc+"\" ..!"+
		     		   "</br>", 
		     		   "Cancel failed ..!");
		     		   
			}else{
				ScheduleManager.cancelPatientVaccinationSchedule( records[0].data.sequenceNumber, 
					function(){
						for( var i = 0 ; i < records.length ; i++){
							grid.getStore().remove( records[i] );
						}
				});
			}
		}
     },
     
     deletePatientVaccinationScheduleDetails : function( grid , record ){
     	var scheduleStatusCode = record.data.scheduleStatusCode;
		if( !Ext.isEmpty( record ) ){
			if( record.data.sequenceNumber < 0 ){
				grid.getStore().remove( record );
			}else{
				
				var patVaccSchDetBM = {
					sequenceNumber : record.data.sequenceNumber,
					subSequenceNumber : record.data.subSequenceNumber,
					scheduleName : { code:record.data.scheduleNameCode,
									 description:record.data.scheduleNameDesc },
					patientId : record.data.patientId
				}
				
				ScheduleManager.cancelPatientVaccinationScheduleDetail( patVaccSchDetBM, 
					function(){
						grid.getStore().remove( record );	
				});
			}
		}
     }
});

Ext.reg('vaccination-schedules-selections-grid-panel', administration.vaccinationSchedule.patientsVaccinationSchedule.VaccinationSchedulesSelectionGridPanel);
