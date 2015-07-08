function showEditDoctorWindow( config,
							   currentAddress,
							   emergencyAddress,
							   permanentAddress,
							   isViewMode, 
							   doctorDetailsStore,
							   manageDoctorMainPnl ) {
	 
var doctorPanel = new CreateDoctor( config,currentAddress,emergencyAddress,permanentAddress );
var panel = layout.getCenterRegionTabPanel().add({
		frame:true,
		title : config.title, 
		closable : true,
		height : 420,
		items : [ doctorPanel.getPanel() ],
		listeners:{
		  	beforeshow:function(){
		  		
			  		if(stateForSelectedCountryStore.data.items.length == 0 && !Ext.isEmpty(currentAddress.selectedCountryCode)){
			  			stateForSelectedCountryStore.load({params:{start:0, limit:9999}, arg:[currentAddress.selectedCountryCode]});
			  		}
			  		if(stateForSelectedCountryStore.data.items.length == 0 && !Ext.isEmpty(permanentAddress.selectedCountryCode)){
			  			stateForSelectedCountryStore.load({params:{start:0, limit:9999}, arg:[permanentAddress.selectedCountryCode]});
			  		}
			  		if(stateForSelectedCountryStore.data.items.length == 0 && !Ext.isEmpty(emergencyAddress.selectedCountryCode)){
			  			stateForSelectedCountryStore.load({params:{start:0, limit:9999}, arg:[emergencyAddress.selectedCountryCode]});
			  		}
			  		var currentConfig ={
			  			country:currentAddress.selectedCountryCode,
						state: currentAddress.selectedStateCode
					};
					var permanentConfig ={
			  			country:permanentAddress.selectedCountryCode,
						state: permanentAddress.selectedStateCode
					};
					var emergencyConfig ={
			  			country:emergencyAddress.selectedCountryCode,
						state: emergencyAddress.selectedStateCode
					};
					var emergencyAndPersonalConfig ={
			  			title: config.selectedEmergencySalutationCode,
						gender:config.selectedEmergencyGenderCode,
						relationship:config.selectedEmergencyRelationCode
					};
					
					doctorPanel.doctorCurrentContactDetails.formPanel.getForm().setValues(currentConfig);
		  			doctorPanel.doctorPermanentContactDetails.formPanel.getForm().setValues(permanentConfig);
		  			doctorPanel.doctorEmergencyContactDetails.formPanel.getForm().setValues(emergencyConfig);
			  		doctorPanel.emergencyDetails.formPanel.getForm().setValues(emergencyAndPersonalConfig);
			  		doctorPanel.doctorConsultationDetails.setData(config);
			  		
			  		if(isViewMode){
			  			var panelArray = new Array();
		   		  		panelArray[0] = doctorPanel.doctorCurrentContactDetails.formPanel;
		   		  		panelArray[1] = doctorPanel.doctorPermanentContactDetails.formPanel;
		   		  		panelArray[2] = doctorPanel.doctorEmergencyContactDetails.formPanel;
		   		  		panelArray[3] = doctorPanel.emergencyDetails.formPanel;
		   		  		panelArray[4] = doctorPanel.doctorQualificationDetails.formPanel;
			  			createModifyDoctorWindow.setTitle(msg.viewDoctor);	
						getViewModeTest(panelArray);
			  		}
		  	}
	  	}
	});
		
	layout.getCenterRegionTabPanel().setActiveTab( panel );
	layout.getCenterRegionTabPanel().doLayout();
	
   Ext.ux.event.Broadcast.subscribe('closeEditDoctor',function(){
//		layout.getCenterRegionTabPanel().remove( panel, true );
		layout.getCenterRegionTabPanel().setActiveTab( manageDoctorMainPnl );
		layout.getCenterRegionTabPanel().doLayout();
   },this,null,manageDoctorMainPnl);
}

function showAddDoctorWindow( manageDoctorMainPnl ){
	 var doctorForm =  new CreateDoctor({isPopup: true});
	
	 var addPanel = layout.getCenterRegionTabPanel().add({
		frame:true,
		title : msg.addDoctor, 
		closable : true,
		height : 420,
		items : [ doctorForm.getPanel() ]
	});
		
	layout.getCenterRegionTabPanel().setActiveTab( addPanel );
	layout.getCenterRegionTabPanel().doLayout();
	
     Ext.ux.event.Broadcast.subscribe('closeAddDoctor',function(){
//     	var tmpAddPanel = addPanel;
//		layout.getCenterRegionTabPanel().remove( tmpAddPanel, true );
		layout.getCenterRegionTabPanel().setActiveTab( manageDoctorMainPnl );
		layout.getCenterRegionTabPanel().doLayout();
     },this,null,manageDoctorMainPnl);
}

function showManageRosterTab( manageDoctorMainPnl, data ){

	var searchRosterPanel =  new getManageRoster({ type:'doctor', entityid: data.doctorId });
	
	 var rosterPanel = layout.getCenterRegionTabPanel().add({
		frame:true,
		title :  'Manage doctor rosters', 
		closable : true,
		height : 420,
		items : [ searchRosterPanel ]
	});
		
	layout.getCenterRegionTabPanel().setActiveTab( rosterPanel );
	layout.getCenterRegionTabPanel().doLayout();
	
     Ext.ux.event.Broadcast.subscribe('closeManageRosterTab',function(){
     	var toBeRemovePanel = rosterPanel;
		
		layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
		layout.getCenterRegionTabPanel().setActiveTab( manageDoctorMainPnl );
		layout.getCenterRegionTabPanel().doLayout();
     },this, null, manageDoctorMainPnl);
}



function getSearchDoctor(){
 var selectedRecord;
 
 Ext.QuickTips.init();
 
 var fromDate = new Ext.form.WTCDateField({
 	fieldLabel:msg.joiningdatefrom,
	name:'joiningdatefrom',
	anchor:'70%',
	listeners:{
   		change: function( date, newValue, oldValue ){
   			if(!Ext.isEmpty(date.getValue())){
   				toDate.setMinValue(date.getValue());
   			}
   		}
	}
 });
 
 var toDate = new Ext.form.WTCDateField({
 	fieldLabel:msg.joiningdateto,
	name:'joiningdateto',
	anchor:'70%',
	listeners:{
   		change: function( date, newValue, oldValue ){
   			if(!Ext.isEmpty(date.getValue())){
   				fromDate.setMaxValue(date.getValue());
   			}
   		}
	}
 });
 
 var searchBtn = new Ext.Button({
	text: 'Search',
	iconCls : 'search_btn',
	handler: function() {
		if(manageDoctorsSearchPnl.getForm().isValid()){
	   					searchForDoctors(manageDoctorsSearchPnl,manageDoctorGridPnl);
	   					fromDate.setMaxValue(null);
	   					toDate.setMinValue(null);
	   	}else{
	   		alertWarning("Enter all the required fields..!");
	   	}
	   	Ext.ux.event.Broadcast.publish('getSearchDoctorInitialState');
	}
 });

 var buttonPanel = new Ext.Panel({
	buttonAlign:'right',
	border:false,
	autoHeight: true,
	header: false,
	buttons:[searchBtn,{
		xtype:'button',
		text:'Reset',
		iconCls : 'cancel_btn',
		scope:this,
		handler: function(){
			manageDoctorsSearchPnl.getForm().reset();
			searchForDoctors(manageDoctorsSearchPnl,manageDoctorGridPnl);
			Ext.ux.event.Broadcast.publish('getSearchDoctorInitialState');
		}
	}]
 });				  
 
 var manageDoctorsSearchPnl = new Ext.form.FormPanel({
		layout:'column',
		labelAlign:'left',
		border:false,
		defaults:{labelWidth:155, columnWidth:.50},
		items:[
			{
				layout:'form',
				border:false,
				items:fromDate
			},
			{
				layout:'form',
				border:false,
				items:toDate
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'numberfield',
						fieldLabel:msg.doctorid,
						name:'doctorid',
						anchor:'95%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection: true,
						fieldLabel:msg.department,
						store: loadDepartmentsCbx(),
						displayField:'description',
						valueField:'code',
						triggerAction: 'all',
						mode:'local',
						resizable:true,
						anchor:'95%',
						name:'department'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'textfield',
						fieldLabel:'Doctor name',
						name:'doctorname',
						vtype:'alpha',
						anchor:'95%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection: true,
						fieldLabel:msg.roomno,
						anchor:'95%',
						name:'roomno',
						store: loadRoomName(),
						displayField:'description',
						valueField:'code',
						triggerAction: 'all',
						mode:'local',
						resizable:true
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.consultationchargesfrom,
						name:'consultationchargesfrom',
						anchor:'95%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.consultationchargesto,
						name:'consultationchargesto',
						anchor:'95%'
					}
				]
			},{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection: true,
						fieldLabel:msg.speciality,
						name:'especiality',
						mode:'local',
						store: loadEspecialityCbx(),
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
						resizable:true,
						anchor:'95%'
					}
				]
			},
			{
				layout:'form',
				columnWidth: 1.0,
				border:false,
				items:buttonPanel
			}
		]
	});
	
	var seletionModel = new Ext.grid.CheckboxSelectionModel({
		listeners:{
			rowselect : function( selectionModel, rowIndex, record){
					editBtn.disable();
					viewBtn.disable();
					manageRosterBtn.disable();
					deleteBtn.enable()
			},
			rowdeselect : function( selectionModel, rowIndex, record){
				editBtn.disable();
				viewBtn.disable();
				manageRosterBtn.disable();
				deleteBtn.disable();
			}
		}
	});
	
	var bbar = new Ext.WTCPagingToolbar({
                store: doctorManagementDataStore,
                displayMsg: msg.pagingbarDisplayMsg,
		        emptyMsg: "No doctors to display"
    });
    
	var addBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         text:msg.add,
         tooltip:msg.add,
         icon:'images/add.png',
         handler: function(){
             var selectedrows = manageDoctorGridPnl.getSelectionModel().getSelections();
            
             showAddDoctorWindow( manageDoctorMainPnl );
		}
    });
    
    var editBtn = new Ext.Toolbar.Button({
        cls:'x-btn-text-icon',
        text:msg.edit,
        tooltip:msg.edit,
       	disabled:true,
        icon:'images/pencil.png',
        handler: function(){
             if (Ext.isEmpty(selectedRecord)) {
				alertError(msg.defaulterrrormessage);
			}else {
				var selectedrows = manageDoctorGridPnl.getSelectionModel().getSelections();
				if(selectedrows.length == 1) {
					 var store = getDoctorDetailsStore( selectedRecord.doctorId )
					editviewBtnHandler( false,store, manageDoctorMainPnl );
				}
			}
    	}
    });
    
    var viewBtn = new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         icon:'images/zoom.png',
         text: msg.view,
         disabled:true,
         tooltip:msg.view,
         handler: function(){
            if (Ext.isEmpty(selectedRecord)) {
				alertError(msg.defaulterrrormessage);
			}else {
				var selectedrows = manageDoctorGridPnl.getSelectionModel().getSelections();
				if(selectedrows.length == 1) {
					var store = getDoctorDetailsStore( selectedRecord.doctorId )
					editviewBtnHandler( true,store );
				}
			}
        }
    });
    
    var deleteBtn =  new Ext.Toolbar.Button({
         cls:'x-btn-text-icon',
         text:msg.del,
         tooltip:msg.del,
    	 disabled:true,
         icon:'images/delete.png',
         handler: function(){
           var selectedrows = manageDoctorGridPnl.getSelectionModel().getSelections();
           if(Ext.isEmpty(selectedrows)) {
           	  alertError(msg.defaulterrrormessage);
           }
           else {
	           var patientArr =[];
	           var rowCount = manageDoctorGridPnl.getSelectionModel().getCount();
	           for(i=0;i<rowCount;i++) {
	               patientArr[i] =  parseInt(selectedrows[i].data.doctorId);
	           }
	           DoctorManagementController.removeDoctors(patientArr,function() {
	                      info( 'Removed successfully!' );
	                      for(j=0;j<rowCount;j++) {
							manageDoctorGridPnl.stopEditing();
							manageDoctorGridPnl.getStore().remove(selectedrows[j]);
	                      }
	                      Ext.ux.event.Broadcast.publish('reloadStore');
				});
           }
    	}
    });
    
    var manageRosterBtn = new Ext.Toolbar.Button({
		text:'Manage roster',
		iconCls: 'roster-icon',
		disabled:true,
		handler: function() {
			 var selectedrows = manageDoctorGridPnl.getSelectionModel().getSelections();
			 if(selectedrows.length == 1) {
			 	var data = selectedrows[0].data;
			 	showManageRosterTab( manageDoctorMainPnl, data );
			 	manageDoctorGridPnl.getSelectionModel().clearSelections();
			 }else{
			 	info(msg.selectdoctor);
			 }
		}
	});
    
	var toolBar = new Ext.Toolbar({
		items:[  
			addBtn,
            editBtn,
//            viewBtn ,
//            deleteBtn,
            manageRosterBtn
        ]
	});
	
	var manageDoctorGridPnl = new Ext.grid.GridPanel({
		autoScroll:true,
		stripeRows:true,
		border:false,
		height:300,
		viewConfig:{ forceFit: true},
		ds: doctorManagementDataStore,
		cm: new Ext.grid.ColumnModel ([
				seletionModel,
				{header: "Doctor Id", width: 80, sortable: true, dataIndex:'doctorId'},
				{header: "Doctor Name", width: 150, sortable: true, dataIndex:'doctorName'},
				{header: "Gender", width: 75, sortable: true, dataIndex:'gender'},
				{header: "Known Languages", width: 100, sortable: true, dataIndex:'knownLanguages'},
				{header: "Department", width: 100, sortable: true, dataIndex:'department'},
				{header: "Specialty", sortable: true, dataIndex: 'especiality'},
				{header: "Room No", width: 60, sortable: true, dataIndex:'roomNo'},
				{header: "Consultation Charges(in Rs.)", width: 145, sortable: true, dataIndex:'consultationCharge', renderer: Ext.util.Format.money},
				{header: "Parmanent?", width: 75, sortable: true, dataIndex:'parmanent'}
		]),
		sm:seletionModel,
		bbar:bbar,
	    listeners:{
			 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					selectedRecord = thisGrid.getStore().getAt(rowIndex).data; 
					var selectedRows = thisGrid.getSelectionModel().getSelections();
					editBtn.disable();
					viewBtn.disable();
					manageRosterBtn.disable();
					deleteBtn.disable();
					
					if( selectedRows.length == 1){
						editBtn.enable();
						viewBtn.enable();
						manageRosterBtn.enable();
						deleteBtn.enable();
						
					} else if( selectedRows.length > 1){
						editBtn.disable();
						viewBtn.disable();
						manageRosterBtn.disable();
						deleteBtn.enable();
					}
			},
			celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
				//commented as this needs to be consitent with other screens
//				selectedRecord = thisGrid.getStore().getAt(rowIndex).data; 
//				var store = getDoctorDetailsStore( selectedRecord.doctorId )
//				editviewBtnHandler( true,store );
			}
		},
		tbar:toolBar
	});	
				
	var manageDoctorMainPnl = new Ext.Panel({
        layout:'column',
		height:'100%',
		border:false,
		frame:true,
		items:[
          {
	          columnWidth:1,
	          border:false,
	          layout:'form',
	          items:[
	                    manageDoctorsSearchPnl 
	                ]
           },
           {
	          columnWidth:1,
	          border:false,
	          layout:'form',
	          items:[
	                    manageDoctorGridPnl
	                ]
           }
          ]
	});
	var mainThis = this;
	
	Ext.ux.event.Broadcast.subscribe('reloadStore',function(){
		if(manageDoctorGridPnl.getStore().data.items.length == 0){
			manageDoctorGridPnl.getStore().load({params:{start:0, limit:10}, 
						arg:[null,null,null,null,null,null,null,null,null]});
		}else{
			manageDoctorGridPnl.getStore().removeAll();
			manageDoctorGridPnl.getStore().reload();
		}
		
		Ext.ux.event.Broadcast.publish('getSearchDoctorInitialState');
	},this, null ,manageDoctorMainPnl);
	
	Ext.ux.event.Broadcast.subscribe('getSearchDoctorInitialState',function(){
		addBtn.enable();
		editBtn.disable();
		manageRosterBtn.disable();
		deleteBtn.disable();
	},this, null,manageDoctorMainPnl );
	
	manageDoctorsSearchPnl.getForm().reset();
	// initially loading 10 records of the grid panel.
	manageDoctorGridPnl.on("render", function(thisForm){
			searchForDoctors(manageDoctorsSearchPnl,manageDoctorGridPnl);
		}, this);
//	manageDoctorGridPnl.getStore().removeAll();
	
	manageDoctorMainPnl.on('destroy',function( comp ){
		InstanceFactory.removeInstance( comp.windowCode );
	},this);

	
	return manageDoctorMainPnl;
}

function searchForDoctors(manageDoctorsSearchPnl,manageDoctorGrid) {
  var items = manageDoctorsSearchPnl.getForm().getValues();
  var joiningdatefrom = items['joiningdatefrom'];
  var joiningdateto   = items['joiningdateto'];
  var doctorname = items['doctorname'];
  var consultationchargesfrom = items['consultationchargesfrom'];
  var consultationchargesto = items['consultationchargesto'];
  var doctorid = items['doctorid'];
  var especiality=items['especiality'];
  var department =items['department'];
  var roomno =items['roomno'];
  
    doctorManagementDataStore.removeAll();
	doctorManagementDataStore.load({params:{start:0, limit:10}, 
									arg:[	doctorid,
											department,
											especiality,
											doctorname,
											roomno,
											consultationchargesfrom,
											consultationchargesto,
											joiningdatefrom,
											joiningdateto]});
}

function getDoctorDetailsStore( doctorId ) {
  var doctorDetailsStore = new Ext.data.Store({
    proxy: new Ext.data.DWRProxy(DoctorManagementController.getDoctorDetails, true),
    reader: new Ext.data.ListRangeReader(
    	{totalProperty:'totalSize'}, getDoctorDetailsRecordType()),
    remoteSort: true
  });
 doctorDetailsStore.removeAll();
 doctorDetailsStore.load({params:{start:0, limit:8}, arg:[parseInt( doctorId )]});
 return doctorDetailsStore;
}
	  
function editviewBtnHandler(isViewMode, doctorDetailsStore, manageDoctorMainPnl ) {
   doctorDetailsStore.on(
       'load', function(){
				var data = doctorDetailsStore.data.items[0].data;
				 var age = {};
				 if( data.personaldetailsDateOfBirth != null && data.personaldetailsDateOfBirth != "" ){
				 	 age  = calculateDOB( data.personaldetailsDateOfBirth );
				 }else{
				 	age[0] = "";
				 	age[1] = "";
				 	age[2] = "";
				 }
				
				 var currentAddress = {
				 	 mode: 'edit',
                     type: isViewMode,
                     addressType:'current',
                	 selectedContactDetailsCode :data.currentcontactId,
					 selectedPersonelId :data.doctorId,
					 selectedHouseNumber :data.currenthousename,
					 selectedStreet :data.currentplacestreet,
					 selectedCity :data.currentcitydistrict,
					 selectedPincode :data.currentpincode,
					 selectedPhoneNumber :data.currentphonenumber,
					 selectedMobileNumber :data.currentmobilenumber,
					 selectedFaxNumber :data.currentfaxnumber,
					 selectedEmail :data.currentemailaddress,
					 selectedStayDuration :data.currentdurationodstay,
					 selectedCountry :data.currentcountry,
					 selectedState :data.currentstate,
					 selectedCountryCode :data.currentcountryCode,
					 selectedStateCode :data.currentstateCode,
					 selectedContactType :data.currentcontactType
                };
              	                
                var emergencyAddress = {
                	mode: 'edit',
                    type: isViewMode,
                    addressType:'emergency',
                	selectedContactDetailsCode :data.emergencycontactId,
					selectedPersonelId :data.doctorId,
					selectedHouseNumber :data.emergencyHousename,
					selectedStreet :data.emergencyPalcestreet,
					selectedCity :data.emergencyCityDistrict,
					selectedPincode :data.emergencyPincode,
					selectedStayDuration :data.emergencyDurationofstay,
					selectedCountry :data.emergencyCountry,
					selectedState :data.emergencyState,
					selectedCountryCode :data.emergencyCountryCode,
					selectedStateCode :data.emergencyStateCode,
					selectedContactType :data.emergencycontactType
                };
                
                var permanentAddress = {
                	mode: 'edit',
                    type: isViewMode,
                    addressType:'permanent',
                	selectedContactDetailsCode :data.parmanentcontactId,
					selectedPersonelId :data.doctorId,
					selectedHouseNumber :data.parmanentHousename,
					selectedStreet :data.parmanentPlacestreet,
					selectedCity :data.parmanentCitydistrict,
					selectedPincode :data.parmanentpincode,
					selectedPhoneNumber :data.parmanentPhonenumber,
					selectedMobileNumber :data.parmanentMobilenumber,
					selectedFaxNumber :data.parmanentFaxnumber,
					selectedEmail :data.parmanentEmailaddress,
					selectedStayDuration :data.parmanentDurationofstay,
					selectedCountry :data.parmanentCountry,
					selectedState :data.parmanentState,
					selectedCountryCode :data.parmanentCountryCode,
					selectedStateCode :data.parmanentStateCode,
					selectedContactType :data.parmanentcontactType
                };
                
				var config = {
                	isPopup: true,    
					mode: 'edit',
//                     title:'Edit doctor',
                     type: isViewMode,
                     selectedDoctorId: data.doctorId,
                     imagePropertyBM: data.imagePopertyBM, 
                     selectedFirstName: data.personaldetailsFirstname,
                     selectedreligion: data.personaldetailsreligion,
					 selectedsaluation: data.personaldetailsTitle,
					 selectedgender: data.personaldetailsGender,
					 selectedbloodGroup: data.personaldetailsBloodgroup,
					 selectedidProof: data.personaldetailsIdproof,
					 selectedmarital: data.personaldetailsMeritialStatus,
					 selectedNationality:data.personaldetailsNationality,
					 selectedNationalityCode:data.personaldetailsNationalityCode,
					 selectedreligionCode: data.personaldetailsreligionCode,
					 selectedsaluationCode: data.personaldetailsTitleCode,
					 selectedgenderCode: data.personaldetailsGenderCode,
					 selectedbloodGroupCode: data.personaldetailsBloodgroupCode,
					 selectedidProofCode: data.personaldetailsIdproofCode,
					 selectedmaritalCode: data.personaldetailsMeritialStatusCode,
					 selectedfirstName: data.personaldetailsFirstname,
					 selectedmiddleName: data.personaldetailsMiddleName,
					 selectedlastName: data.personaldetailsLastname,
					 selectedpermanent: data.personaldetailsparmanentdoctorChk,
					 selectedjoiningDt: data.personaldetailsJoiningdate,
					 selectedleavingDt: data.personaldetailsLeavingDate,
					 selecteddob: data.personaldetailsDateOfBirth,
					 selectedDobYears:age[0],
					 selectedDobMonths: age[1],
					 selectedDobDays: age[2],
					 selectedheight: data.personaldetailsHeight,
					 selectedweight: data.personaldetailsWeight,
					 selectedfatherHusbandName: data.personaldetailsfatherhusbandname,
					 selectedidNumber: data.personaldetailsIdno,
					 selectedidValidUpto: data.personaldetailsValidupto,
					 selectedbloodDonorId: data.personaldetailsBloodGroupId,
					 
					 selectedworkExperience: data.experience,
					 selectedknownLanguages :data.knownLanguages,
					 selectedQualification :data.qualification,	
					 selectedReferredBy :data.referredBy,
					 
                     selectedespecialty:data.doctorSpeciality,
					 selecteddepartment:data.doctorDepartmentname,
					 selectedroom:data.doctorRoomno,
					 selectedespecialtyCode:data.doctorSpecialityCode,
					 selecteddepartmentCode:data.doctorDepartmentnameCode,
					 selectedroomCode:data.doctorRoomnoCode,
					 selecteddepjoiningDt :data.doctorJoiningdate,
					 selecteddepleavingDt :data.doctorleavingDate,
					 selectedconsultationCharge :data.doctorConsultationcharges,
					 selectedfollowUpCharge :data.followupfee,
					 selectedfollowUpDays :data.followupDays,
					 selectedfollowUpVisits :data.followupVisits,
					 
					 selectedEmergencyFirstName :data.emergencyFristname,
					 selectedEmergencyMiddleName :data.emergencyMiddlename,
					 selectedEmergencyLastName :data.emergencyLastname,
					 selectedEmergencySalutation :data.emergencytitle,
					 selectedEmergencyGender :data.emergencyGender,
					 selectedEmergencySalutationCode :data.emergencytitleCode,
					 selectedEmergencyGenderCode :data.emergencyGenderCode,
					 selectedEmergencyRelation:data.emergencyRelation,
					 selectedEmergencyRelationCode:data.emergencyRelationCode,
					 selectedEmergencyPhoneNumber:data.emergencyPhonenumber,
					 selectedEmergencyMobileNumber:data.emergencyMobilenumber,
					 selectedEmergencyFaxNumber:data.emergencyFaxnumber,
					 selectedEmergencyEmail:data.emergencyEmailaddress
              };
              	                
              	showEditDoctorWindow( config,currentAddress,emergencyAddress,permanentAddress,isViewMode, doctorDetailsStore,manageDoctorMainPnl );
				
   		});
}
	  
  
