

function getDoctordepartmentSpectiality(config){
var joiningDate;
var createDoctorDepartmentDetailsPnl= new Ext.form.FieldSet({
		layout:'column',
		labelAlign:'left',
		height:'100%',
		border:true,
		defaults:{labelWidth:120},
		items:[
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection:true,
				        fieldLabel:msg.departmentname,
				        name:'doctorDepartmentname',
				        mode:'local',
				        store:loadDepartmentsCbx(),
				        displayField:'description',
						valueField:'code',
						triggerAction:'all',
					    anchor:'95%'
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection:true,
				        fieldLabel:msg.speciality,
				        name:'doctorSpeciality',
				        mode:'local',
					    store:loadEspecialityCbx(),
					    displayField:'description',
					    valueField:'code',
					    triggerAction:'all',
					    anchor:'95%'
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'wtcdatefield',
				        fieldLabel:msg.joiningdate,
				        name:'doctorJoiningdate',
					    anchor:'95%',
					    value: ((!Ext.isEmpty(config) && (config.mode == 'edit')) ? config.selecteddepjoiningDt : "" ),
					    listeners:{
                       		blur: function( date ){
                       			joiningDate = date.getValue();
                       		}
                       }
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'wtcdatefield',
				        fieldLabel:msg.leavingdate,
				        name:'doctorleavingDate',
					    anchor:'95%',
					    value: ((!Ext.isEmpty(config) && (config.mode == 'edit')) ? config.selecteddepleavingDt : "" ),
					    listeners:{
					    	blur: function( date ){
					    		if(!Ext.isEmpty(joiningDate)){
					    			var value = compareTwoDates(joiningDate, date.getValue());
					    			if(! value){
					    				date.markInvalid('Invalid date ');
					    				alertError(msg.departmentJoiningDate);
					    			}
					    		}
					    	}
					    }
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'optcombo',
						forceSelection:true,
				        fieldLabel:msg.roomno,
				        name:'doctorRoomno',
				        mode:'local',
				        store:loadRoomName(),
					    displayField:'description',
					    valueField:'code',
					    triggerAction:'all',
					    anchor:'95%'
					}
				]
			},
			{
				columnWidth:.5,
				layout:'form',
				border:false,
				items:[
					{
						xtype:'textfield',
				        fieldLabel:msg.consultationcharges,
				        name:'doctorConsultationcharges',
					    anchor:'95%',
					    value: ((!Ext.isEmpty(config) && (config.mode == 'edit')) ? config.selectedconsultationCharge : "" )
					}
				]
			}
		]
	});
	
	var formPanel = new Ext.form.FormPanel({
		border:false,
		items:createDoctorDepartmentDetailsPnl,
		listeners:{
			render:function( comp ){
				var comboConfig ={
					doctorSpeciality: config.selectedespecialty,
					doctorDepartmentname: config.selecteddepartment,
					doctorRoomno: config.selectedroom
				};
				comp.getForm().setValues(comboConfig);
			}
		}
	});
	
	Ext.ux.event.Broadcast.subscribe('doctorReset',function(){
		formPanel.getForm().reset();
	}, this, null, formPanel );
	
	return formPanel;
}

