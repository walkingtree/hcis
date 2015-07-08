
function getDoctorPersonalDetails(config){
	var joiningDate;
	var depJoiningDate;
	var mainThis = this;
	
	if(Ext.isEmpty(config)){
		config = {};
	}
	
	var comboConfig ={
			personaldetailsreligion: config.selectedreligionCode,
			personaldetailsTitle: config.selectedsaluationCode,
			personaldetailsGender: config.selectedgenderCode,
			personaldetailsBloodgroup: config.selectedbloodGroupCode,
			personaldetailsIdproof: config.selectedidProofCode,
			personaldetailsMeritialStatus: config.selectedmaritalCode,
			personaldetailsnationality:config.selectedNationalityCode,
			
			doctorSpeciality: config.selectedespecialtyCode,
			doctorDepartmentname: config.selecteddepartmentCode,
			doctorRoomno: config.selectedroomCode
		}
	 
	var doctorFromJoiningDate = new Ext.form.WTCDateField({
	 	fieldLabel:msg.joiningdate,
	    name:'personaldetailsJoiningdate',
	    value: config.selectedjoiningDt,
	    anchor:'96%',
	    listeners:{
       		change: function( date ){
       			if(!Ext.isEmpty(date.getValue())){
	   				doctorToJoiningDate.setMinValue(date.getValue());
	   			}
       		}
        }
	});
	
	var doctorToJoiningDate = new Ext.form.WTCDateField({
	 	fieldLabel:msg.leavingdate,
	    name:'personaldetailsLeavingDate',
	    value:config.selectedleavingDt,
	    anchor:'96%',
	    listeners:{
	    	change: function( date ){
       			if(!Ext.isEmpty(date.getValue())){
	   				doctorFromJoiningDate.setMaxValue(date.getValue());
	   			}
       		}
	    }
	});
	
	var depFromJoiningDate = new Ext.form.WTCDateField({
	 	fieldLabel:msg.joiningdate,
        name:'doctorJoiningdate',
	    anchor:'96%',
	    value: config.selecteddepjoiningDt,
	    listeners:{
       		change: function( date ){
       			if(!Ext.isEmpty(date.getValue())){
	   				depToJoiningDate.setMinValue(date.getValue());
	   			}
       		}
       }
	});
	
	var depToJoiningDate = new Ext.form.WTCDateField({
	 	fieldLabel:msg.leavingdate,
        name:'doctorleavingDate',
	    anchor:'100%',
	    value:config.selecteddepleavingDt,
	    listeners:{
	    	change: function( date ){
       			if(!Ext.isEmpty(date.getValue())){
	   				depFromJoiningDate.setMaxValue(date.getValue());
	   			}
       		}
	    }
	});
	
	var doctorDateOfBirth = new Ext.form.WTCDateField({
   		 fieldLabel:msg.dateofbirth,
         name:'personaldetailsDateOfBirth',
         value: config.selecteddob,
         anchor:'96%',
         listeners:{
	         change: function(date){
			     if(!Ext.isEmpty(date.getValue())){
					var age  = calculateDOB(date.getValue());
					var doctorAge = {};
					if( age[0] < parseInt(configs.doctorsAge)){
						date.setValue('');
						date.focus();
	//					date.markInvalid('Invalid date ');
					}
			     }
		     }
	   	 }
	});
	
	var createDoctorPersonalDetailsPnl= new Ext.form.FieldSet({
		layout:'column',
		labelAlign:'left',
		height:'100%',
		title:msg.personaldetails,
		style:'padding-bottom:22px',
		defaults:{columnWidth:.5,labelWidth:95},
		items:[
			{
				layout:'form',
				border:false,
				items:[
						{
							xtype:"combo",
							forceSelection : true,
							fieldLabel:msg.title,
							store:loadTitleCbx(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
						    name:'personaldetailsTitle',
						    anchor:'96%'
						}
					   
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:"textfield",
						fieldLabel:msg.firstname,
						name:'personaldetailsFirstname',
						value: config.selectedFirstName,
						anchor:'96%',
						allowBlank:false,
						required:true
						

					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
					    xtype:"textfield",
						fieldLabel:msg.middlename,
						name:'personaldetailsMiddleName',
						value: config.selectedmiddleName ,
						anchor:'96%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
					    xtype:"textfield",
						fieldLabel:msg.lastname,
						name:'personaldetailsLastname',
						value: config.selectedlastName,
						anchor:'96%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:doctorDateOfBirth
			},
			{
				layout:'form',
				border:false,
				items:[
					{
					   	 xtype:"combo",
					     forceSelection : true,
						 store: loadGenderCbx(),
						 mode:'local',
						 displayField:'description',
						 valueField:'code',
				         triggerAction: 'all',
					     fieldLabel:msg.sex,
					     name:'personaldetailsGender',
					     anchor:'96%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
					    xtype:'combo',
						forceSelection : true,
						store: loadMaritalStatusCbx(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
				        fieldLabel:msg.maritalstatus,
				        name:'personaldetailsMeritialStatus',
				        anchor:'96%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
						{
							xtype:'textfield',
							fieldLabel:msg.fatherhusbandname,
							name:'personaldetailsfatherhusbandname',
							value:config.selectedfatherHusbandName,
							anchor:'96%'
						}
					   
				]
			},
			{
				layout:'form',
				border:false,
				items:[
						{
							xtype:'combo',
							forceSelection : true,
							store:loadReligionCbx(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
							fieldLabel:msg.religion,
							name:'personaldetailsreligion',
							anchor:'96%'
						}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'combo',
						forceSelection : true,
						store: loadNationalityCbx(),
						value:config.selectedNationality,
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
						fieldLabel:msg.nationality,
						name:'personaldetailsnationality',
						anchor:'96%'

					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
					    xtype:'combo',
						forceSelection : true,
						fieldLabel:msg.bloodgroup,
						store:loadBloodGroupCbx(),
						mode:'local',
						displayField:'description',
						valueField:'code',
					    triggerAction: 'all',
					    name:'personaldetailsBloodgroup',
					    anchor:'96%'
					}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'textfield',
						fieldLabel:msg.blooddonorid,
						name:'personaldetailsBloodGroupId',
						value: config.selectedbloodDonorId,
						anchor:'96%'
			 		 }
			      ]
			},
			{
				layout:'form',
				border:false,
				items:doctorFromJoiningDate
			},
			{
				layout:'form',
				border:false,
				items:doctorToJoiningDate
			},
			{
				layout:'form',
				border:false,
				labelWidth:110,
				items:[
					{
						xtype:'checkbox',
						fieldLabel:msg.parmanentdoctor,
						name:'personaldetailsparmanentdoctorChk',
						checked : config.mode == 'edit'?config.selectedpermanent:false ,
						anchor:'96%'
					}
				]
			}
		]
	});
	
	var specialtyCombo = new Ext.form.ComboBox({
		forceSelection:true,
        fieldLabel:msg.speciality,
        hiddenName:'doctorSpeciality',
        mode:'local',
	    store: loadSpecialtyForDepartmentCbx(),
	    displayField:'description',
	    valueField:'code',
	    triggerAction:'all',
	    anchor:'97%',
		allowBlank:false,
		required:true,
		disabled: true
	});
	
	if( !Ext.isEmpty( config ) && config.mode == 'edit'){
		specialtyCombo.enable();
	}
	
	var createDoctorDepartmentDetailsPnl= new Ext.form.FieldSet({
		title:'Doctor Specialty',
		layout:'column',
		labelAlign:'left',
		height:'100%',
		border:true,
		defaults:{labelWidth:120 , columnWidth:.33},
		items:[
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'combo',
						forceSelection:true,
				        fieldLabel:msg.departmentname,
				        name:'doctorDepartmentname',
				        mode:'local',
				        store:loadDepartmentsCbx(),
				        displayField:'description',
						valueField:'code',
						triggerAction:'all',
					    anchor:'96%',
						allowBlank:false,
						required:true,
						value : config.selecteddepartmentCode,
						listeners:{
							select: function( combo, record, index ){
								selectHandler(  record.data.code );
							}
						}
					}
				]
			},
			{
				layout:'form',
				border:false,
				items: specialtyCombo
			},
			{
				layout:'form',
				border:false,
				items:[
					{
						xtype:'combo',
						forceSelection:true,
				        fieldLabel:msg.roomno,
				        name:'doctorRoomno',
				        mode:'local',
				        store:loadRoomName(),
					    displayField:'description',
					    valueField:'code',
					    triggerAction:'all',
					    anchor:'96%'
					}
				]
			}
		]
	});
	
	var creteDoctorDetailsPnl = new Ext.form.FieldSet({
	  height:'100%',
	  style:'padding:2px',
	  border:false
	});
	
	creteDoctorDetailsPnl.add( createDoctorPersonalDetailsPnl );
	
    var identificationFieldSet = new Ext.form.FieldSet({
		layout:'column',
		height:'100%',
		border:true,
		title:msg.identificationdetails,
		style:'padding-top:5px',
		defaults:{columnWidth:1, labelAlign:'left'},
		items:[
			{
				layout:'form',
				border:false,
				items:[
						{
							xtype:'combo',
							forceSelection : true,
							store:loadIdProofCbx(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
						    fieldLabel:msg.idproof,
						    name:'personaldetailsIdproof',
						    anchor:'100%'
				    	}
				]
			},
			{
				layout:'form',
				border:false,
				items:[
						{
					     xtype:'textfield',
						 fieldLabel:msg.idno,
						 anchor:'100%',
						 name:'personaldetailsIdno',
						 value: config.selectedidNumber
						}
				  ]
			},	  
			{
				layout:'form',
				border:false,
				items:[
						{
						xtype:'wtcdatefield',
						fieldLabel:msg.validupto,
						anchor:'100%',
						name:'personaldetailsValidupto',
						value: config.selectedidValidUpto
						}
				      ]
			}
		]
	});
	
	this.photoPanel = new  UploadPhotoPanel(config);
//	var uploadPhotoPanel = photoPanel.getPanel();
//	
//	uploadPhotoPanel.add(identificationFieldSet);
	
	var createDoctorPersonalDetailsMainPnl = new Ext.form.FormPanel({
         layout:'column',
		 frame: false,
		 border:false,
		 monitorValid:true,
		 scope : this,
		 items:[
	           {
		          columnWidth:.3,
		          border:false,
		          layout:'form',
		          style:'padding-top:08px;',
		          items:[
			          this.photoPanel.getPanel(),
			          identificationFieldSet
		          ]
	           },
	           {
		          columnWidth:.7,
		          border:false,
		          layout:'form',
		          items:creteDoctorDetailsPnl
	           },
	           {
		          columnWidth:1,
		          border:false,
		          layout:'form',
		          items:createDoctorDepartmentDetailsPnl
	           }
          ],
          listeners:{
				render:function(){
					mainThis.setData( comboConfig );
				}
			}
		});
		
	Ext.ux.event.Broadcast.subscribe('doctorReset',function(){
		createDoctorPersonalDetailsMainPnl.getForm().reset();
		specialtyForDepartmentStore.removeAll();
		specialtyCombo.disable();
	}, this, null, createDoctorPersonalDetailsMainPnl );
	
	this.getPanel = function(){
		return createDoctorPersonalDetailsMainPnl;
	};
	
	this.getPhotoPanel = function(){
		return this.photoPanel;
	};
	
	this.resetPersonalDetailsPanel = function(){
		createDoctorPersonalDetailsMainPnl.getForm().reset();
		this.setData(this.comboConfig);
		
	};
	
	var selectHandler = function(  selectedDeptCode ){
		specialtyCombo.enable();
		if( specialtyForDepartmentStore.getTotalCount() > 0 ){
			specialtyForDepartmentStore.removeAll();
		}
		specialtyCombo.clearValue();
		specialtyForDepartmentStore.load({params:{start:0, limit:999}, arg:[selectedDeptCode]});

	};
	
	this.setData = function( comboConfig ){
		this.comboConfig = comboConfig;
		if( !Ext.isEmpty( config ) && config.mode == 'edit' ){
			specialtyForDepartmentStore.load({params:{start:0, limit:999}, arg:[ config.selecteddepartmentCode ]});
  			specialtyForDepartmentStore.on('load', function(){
  				if( ! Ext.isEmpty(comboConfig) ){
  					createDoctorPersonalDetailsMainPnl.getForm().setValues(comboConfig);
					comboConfig = null;
  				}
				
			});
		}else{
			createDoctorPersonalDetailsMainPnl.getForm().setValues(comboConfig);
		}

	};
		
}

