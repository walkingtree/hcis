Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderDetailView = Ext.extend(Ext.form.FormPanel,{
	title : limsMsg.viewRequisitionOrde,
	height : '100%',
	width : '100%',
	layout : 'column',
	initComponent : function(){
	
		var mainThis = this;
		
		this.widgets = new LIMS.requisitionOrder.manage.BasicDetailWidgets();
		
		this.detailGrid = new LIMS.requisitionOrder.manage.RequisitionOrderDetailGrid();
		
		
		this.detailGrid.action.on('action',function(grid, record, action, row, col ){
						
						var serviceUID = record.data.serviceUID ;
						var requisitionOrderNbr = record.data.requisitionOrderNbr;
						
						if( action == "report-icon" || action == "view-icon"){
							
							var templateMode = limsMsg.MODE_EDIT;
							
							if(action == "view-icon"){
								
								
								if(limsMsg.SERVICE_STATUS_RESULT_ENTERED == record.data.statusCode){
								
								//TODO: also check whether the user has role to approve/disapprove or not	
									templateMode = limsMsg.MODE_APPROVE;
								}else{
									
									templateMode = limsMsg.MODE_VIEW;
								}
							}
							
							LabTestManager.getTestResultValue( serviceUID , function( testTemplateBM ){
								testTemplateBM.serviceUID = serviceUID;
								var configureTestTemplate = new LIMS.testTemplate.configure.ConfigureTestTemplate({isEnterTestResult : true,
									   testTemplateBM : testTemplateBM,
									   testCode : testTemplateBM.testCode,
									   MODE : templateMode });
								
								configureTestTemplate.frame = true;
								configureTestTemplate.title = limsMsg.enterTestResult; 
								configureTestTemplate.closable = true;
								configureTestTemplate.height = 430;

								
								var activeTab = layout.getCenterRegionTabPanel().add(configureTestTemplate);
								
								layout.getCenterRegionTabPanel().setActiveTab( activeTab );
								
								Ext.ux.event.Broadcast.subscribe('closeEnterTestResult', function(){
//									layout.getCenterRegionTabPanel().remove( activeTab , true);
									layout.getCenterRegionTabPanel().setActiveTab( mainThis );
									layout.getCenterRegionTabPanel().doLayout();
									mainThis.detailGrid.loadData();
								},this, null , mainThis.getPanel() );
								
								
							} );
						}else if(action == "bottom-arrow-icon"){
							
							this.modifyTestStatus(serviceUID, requisitionOrderNbr, limsMsg.SERVICE_STATUS_SPECIMEN_COLLECTED);
							
							
						}else if(action == "accept-icon"){

							this.modifyTestStatus(serviceUID, requisitionOrderNbr, limsMsg.SERVICE_STATUS_TEST_PERFORMED);

						}else if(action == "approve-icon"){

							this.modifyTestStatus(serviceUID, requisitionOrderNbr, limsMsg.SERVICE_STATUS_APPROVED);

						}else if(action == "disapprove-icon"){

							this.modifyTestStatus(serviceUID, requisitionOrderNbr, limsMsg.SERVICE_STATUS_DISAPPROVED);

						}
						},this);
		
		Ext.applyIf(this, {
			items: [
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getPatientNameTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getGenderTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getDOBTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getStreetTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getCityTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getCountryTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getStateTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getPinCodeTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getPhoneNbrTxf()]
			   },
			   {
					columnWidth:0.33,
	            	layout : 'form',
	            	items:[this.widgets.getEmailIdTxf()]
			   },
//			   {
//					columnWidth:0.33,
//	            	layout : 'form',
//	            	items:[this.widgets.getContactPersonTxf()]
//			   },
//			   {
//					columnWidth:0.33,
//	            	layout : 'form',
//	            	items:[this.widgets.getRelationshipTxf()]
//			   },
			   {
					columnWidth:1,
	            	layout : 'form',
	            	items:[this.detailGrid.getPanel()]
			   }

			]            
	    });
		LIMS.requisitionOrder.manage.RequisitionOrderDetailView.superclass.initComponent.apply(this, arguments);
	},
	
	getPanel : function(){
		return this;
	},
	
	setData: function( patientData ){
		
		if( !Ext.isEmpty( patientData )){

			if( Ext.isEmpty( patientData.contactDetailsBM )){
				patientData.contactDetailsBM = { };
			}
			else {
				if( patientData.contactDetailsBM.state == null ){
					var state = null;
				}
				else{
					state = patientData.contactDetailsBM.state.description;
				}
			}
			
			formValues = {
				patientName : patientData.fullName,
				gender : (Ext.isEmpty(patientData.gender)?"":patientData.gender.description),
				dob : !Ext.isEmpty( patientData.dateOfBirth ) ? new Date(patientData.dateOfBirth).format('d-m-Y') : "",
				street : patientData.contactDetailsBM.street,
				city : patientData.contactDetailsBM.city,
				country : patientData.contactDetailsBM.country.description ,
				state : state,
				pinCode : patientData.contactDetailsBM.pincode,
				phoneNbr : patientData.contactDetailsBM.phoneNumber,
				emailId : patientData.contactDetailsBM.email
			};
			
			this.getForm().setValues( formValues );
		}	
		
	},
	
	modifyTestStatus : function(serviceUID,requisitionOrderNbr,newStatus ){
		
		LabTestManager.modifyPatientTestStatus( serviceUID,newStatus,
				getAuthorizedUserInfo().userName,null,
				{
					callback : function() {
						this.detailGrid.loadData(
									{requisitionOrderNbr:requisitionOrderNbr});
					},
				scope : this
				});
	}
});