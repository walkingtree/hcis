
Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrderDetailsPanel = Ext.extend(Object, {
	constructor: function( config ){
		this.doctorOrderDetailsServicesPanel = new IPD.addOrder.DoctorOrderTemplateServicesPanel();
		if(!Ext.isEmpty(config) && config.values.statusCd == configs.orderStatusApproved){
			this.doctorOrderDetailsServicesGrid = new IPD.addOrder.DoctorOrderDetailsGrid();
		}
		else{
			this.doctorOrderDetailsServicesGrid = new IPD.addOrder.DoctorOrderServiceSelectionsGrid();
		}
		this.doctorOrderDetailsServicesGridPanel = this.doctorOrderDetailsServicesGrid.getPanel();
		
		this.doctorOrderDetailsServicesGrid.editBtn.addListener('click',function(){
			this.editEntryInGrid();
			this.doctorOrderDetailsServicesGrid.editBtn.disable();
			this.doctorOrderDetailsServicesGrid.deleteBtn.disable();
		},this);
		
		this.doctorOrderDetailsServicesPanel.addBtn.addListener('click',function(){
			this.addEntryToGrid();
		},this);
		
		this.doctorOrderDetailsServicesGrid.addBtn.hide();
		
		this.doctorDetailsMainPanel = new Ext.form.FormPanel({
			width:'100%',
			height:'100%',
			layout:'column',
			defaults:{
				columnWidth:1	
			},
			items:[
				this.doctorOrderDetailsServicesPanel.getPanel(),
				this.doctorOrderDetailsServicesGridPanel
			]
		});
	},
	resetPanel : function(){
		this.doctorDetailsMainPanel.getForm().reset();
		this.doctorOrderDetailsServicesPanel.serviceTypeCardPanel.layout.setActiveItem(0);
		this.doctorOrderDetailsServicesPanel.serviceTypeCardPanel.doLayout();
		this.doctorOrderDetailsServicesGridPanel.getStore().removeAll();
		
	},
	getPanel: function(){
		this.doctorOrderDetailsServicesGridPanel.getStore().removeAll();
		return this.doctorDetailsMainPanel;
	},
	addEntryToGrid: function(){
		   var record = this.doctorOrderDetailsServicesGridPanel.getStore().recordType;
		   var values = this.doctorDetailsMainPanel.getForm().getValues();
		   
		   if(values['sequenceNumber'] == ''){
			  warning("Sequence number is required field..!");
			  return;
		   }
		    
		   if(values['subSequenceNumber'] == ''){
			  warning("Sub-sequence number is required field..!");
			  return;
		   }
		   
		    // Get the responsible service description given its code.
		   var serviceName = null;
		   var slctdServiceDesc;
		   if(values['svcTypeCd'] == 1){
		   		serviceName = values['serviceCode'];
		   		slctdServiceDesc =  this.doctorOrderDetailsServicesPanel.serviceCbx.getRawValue();
		   	
		   }else if(values['svcTypeCd'] == 2){
		   		serviceName = values['serviceName'];
		   		slctdServiceDesc =  this.doctorOrderDetailsServicesPanel.serviceForGroupCbx.serviceCbx.getRawValue();
		   		slctdServiceGroupDesc =  this.doctorOrderDetailsServicesPanel.serviceGroupCbx.getRawValue();
		   	
		   }
		  
		    // Get the responsible department description given its code.
		   var slctdDeptDesc; 
		   slctdDeptDesc = this.doctorOrderDetailsServicesPanel.departmentCbx.getRawValue();
		   // Get the responsible package description given its code.
		   var slctdPackageDesc;
		   slctdPackageDesc = this.doctorOrderDetailsServicesPanel.servicePackageCbx.getValue();
		   
		    var serviceRecord = null;
			if(values['svcTypeCd'] == 1 || values['svcTypeCd'] == 2){
				  serviceRecord = new record({
					sequenceNumber: values['sequenceNumber'],
					subSequenceNumber: values['subSequenceNumber'],
					serviceCode: (values['svcTypeCd'] == 1 ) ? 
										 values['serviceCode'] : values['serviceName'] ,
					serviceName: slctdServiceDesc,
					responsibleDepartmentCode: values['responsibleDepartmentCode'],
					responsibleDepartmentDescription: slctdDeptDesc,
					actionRequiredDescription: values['actionRequiredDescription'],
					packageIndicator: Ext.isEmpty(slctdServiceDesc) ? null : "N" 
				 });
			}else {
				serviceRecord = new record({
					sequenceNumber: values['sequenceNumber'],
					subSequenceNumber: values['subSequenceNumber'],
					serviceCode: values['servicePackage'],
					serviceName: slctdPackageDesc,
					servicePackageCode: values['servicePackage'],
					servicePackageDesc: slctdPackageDesc,
					responsibleDepartmentCode: values['responsibleDepartmentCode'],
					responsibleDepartmentDescription: slctdDeptDesc,
					actionRequiredDescription: values['actionRequiredDescription'],
					packageIndicator: Ext.isEmpty(slctdPackageDesc) ? null : "Y"
				 });
			}
			 this.doctorOrderDetailsServicesGridPanel.stopEditing();
			 
			 var tableRows = this.doctorOrderDetailsServicesGridPanel.getStore().getRange();
			 for( var i = 0; i<tableRows.length; i++ ){
			 	var currRow = tableRows[i].data;
			 	var sequenceNumber = currRow.sequenceNumber;
			 	var subSequenceNumber = currRow.subSequenceNumber;
			 	
			 	if(values['sequenceNumber'] == sequenceNumber &&
			 		values['subSequenceNumber'] == subSequenceNumber){
			 			alertError( "This sequence no. & sub-sequence no. " +
			 						"<br> already exists</br> ");
			 			return;
			 	}
			 }
			 var insertAt = this.doctorOrderDetailsServicesGridPanel.getStore().data.items.length;		 
			 this.doctorOrderDetailsServicesGridPanel.getStore().insert(insertAt, serviceRecord);
			 this.resetDoctorDetailsFieldSet();
	},
	editEntryInGrid:function(){
		this.resetDoctorDetailsFieldSet();
		var slctdRows = this.doctorOrderDetailsServicesGridPanel.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.doctorOrderDetailsServicesGrid.returnSelectedDataRecord();
			this.doctorOrderDetailsServicesGrid.deleteBtnClicked();
			this.doctorDetailsMainPanel.getForm().setValues(slctdRecordToEdit);
			
  		}	
	},
	// This function clears all the selections inside the Template services field set.
	resetDoctorDetailsFieldSet : function() {
		var config = {
			sequenceNumber:'',
			subSequenceNumber:'',
			serviceCode:'',
			serviceGroup:'',
			servicePackage:'',
			serviceName:'',
			responsibleDepartmentCode:'',
			actionRequiredDescription:'',
			svcTypeCd:1
		}; 
		var formPanel = this.doctorDetailsMainPanel;
		formPanel.getForm().setValues(config); 
		this.doctorOrderDetailsServicesPanel.serviceForGroupCbx.serviceTypeStore.removeAll();
	}
});