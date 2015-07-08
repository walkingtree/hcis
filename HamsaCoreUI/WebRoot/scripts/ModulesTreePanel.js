ModulesTreePanel = Ext.extend(Ext.Panel, {
  	  initComponent : function() {
		Ext.QuickTips.init();
	      this.treeRoot = new Ext.tree.AsyncTreeNode({
	           text: 'Hamsa', 
	           hasChildren:true,
	           draggable:false, // disable root node dragging
	           id:'-1',
	           listeners:{
	           		load:function(){
	           			this.tree.collapseAll();
	           		},
	           		scope:this
	           }
	      });
	 	 
	      var mainThis = this;
	  	  this.tree = new Ext.tree.TreePanel({
	            animate:true,
	            autoScroll:true,
	           	enableDD:false,
	            useArrows:false,
	            height : screen.height - 245,
	            scope:this,
	//            containerScroll: true,
	            loader: new Ext.ux.DWRTreeLoader(
	            	{
	            		dwrCall:RoleManagementController.getAssignedNodes,
	        			baseParams:
	            			{
	            				roleId:getAuthorizedUserInfo().roleId
	//            				roleId:null
	        				}
	            	}
	        	),
	            border: false,
	          	dropConfig: {appendOnly:false},
	          	rootVisible : false
	//          	lines: false
	        });
	        
	        this.tree.setRootNode(this.treeRoot);

	        
	        this.tree.on('click', function(node, e){
				if(node.attributes.hasChildren || !node.attributes.leaf){
					node.expand();
				} else {
					
					var paneltoAdd = this.getPanelToAdd(node);
						
						
						/*if(paneltoAdd != null && !Ext.isEmpty(paneltoAdd)){
							var x = layout.getCenterRegionTabPanel().add({
									windowCode : node.attributes.id,
									frame:true,
									title : node.attributes.text, 
									closable : true,
									height : screen.height - 245,
									items : [paneltoAdd]
								}
							);*/
							
					if(paneltoAdd != null && !Ext.isEmpty(paneltoAdd)){
						
						paneltoAdd.closable = true;
						paneltoAdd.height = screen.height - 245;
						paneltoAdd.frame = true;
						paneltoAdd.border = false;
						paneltoAdd.title = node.attributes.text;
						paneltoAdd.windowCode = node.attributes.id;
							
//							var paneltoAdd  = new Ext.Panel({height : 768,frame : true})
							
					var x = layout.getCenterRegionTabPanel().add(paneltoAdd);
					
						if( !Ext.isEmpty(paneltoAdd.defaultValues ) && typeof paneltoAdd.defaultValues == 'function'){
							paneltoAdd.defaultValues();
						}
						
						layout.getCenterRegionTabPanel().setActiveTab(x);
						layout.getCenterRegionTabPanel().doLayout();
						
						
					}
				}
			}, this);
			
			this.root = this.tree.getRootNode();
			
			this.toolbarPanel = new Ext.Toolbar({
	          		items:[
	          			 {
							xtype : 'trigger',
							triggerClass : 'x-form-clear-trigger',
							width : 150,
							onTriggerClick : function() {
								this.setValue('');
								mainThis.tree.filter.clear();
							},
							enableKeyEvents : true,
							listeners : {
								keyup : {
									buffer : 150,
									fn : function(field, e) {
										if (Ext.EventObject.ESC === e
												.getKey()) {
											field.onTriggerClick();
										} else {
											var val = this.getRawValue();
											var re = new RegExp('.*' + val
															+ '.*', 'i');
											mainThis.tree.filter.clear();
											mainThis.tree.filter.filter(re,
													'text');
										}
									}
								}
							}
						},
	          			{xtype: 'tbseparator'},
	          			{xtype: 'tbbutton',iconCls:'expand-all', tooltip:msg.expandAll,scope: this,handler:function(){
	          				this.tree.expandAll();
	          			}},
	          			{xtype: 'tbseparator'},
	          			{xtype: 'tbbutton',iconCls:'collapse-all', tooltip:msg.collapseAll,scope: this,handler:function(){
	          				this.tree.collapseAll();
	          			}}
	          						
	          		]
          	});
			
			this.tree.filter = new Ext.ux.tree.TreeFilterX(this.tree);
			
	    Ext.applyIf(this, {
	    	items: [ this.toolbarPanel,this.tree ]            
	    });
	     
      	ModulesTreePanel.superclass.initComponent.apply(this, arguments);
   	 },
   	 
   	 getPanelToAdd: function(node){
   	 	var panelToAdd = null;
   	 	
   	 	//ENTITY STARTS
   	 	
   		if( node.attributes.id === windowCode.addEntity ){
   			panelToAdd = 	new administration.manageEntity.configure.AddEntity();
   	 		
   	   		return panelToAdd;
   	   	}
   	 	
   	 	//ENTITY END 
   	 	
   	 	//REFERRAL START
   	 	if(node.attributes.id === windowCode.addReferral){
   	 		panelToAdd = new administration.referralManagement.AddReferral();
   	 	}
//   	 	if(node.attributes.id === windowCode.referralList){
//   	 	}
   	 	if(node.attributes.id === windowCode.referralReport){
   	 		panelToAdd = new administration.referralManagement.ReferralCommisionReports();
   	 	}
   	 	//REFERRAL END
   	 	
   	 	//VACCINATION START
   	 	if(node.attributes.id === windowCode.addVaccinationSechdule){
   	 		panelToAdd = new administration.vaccinationSchedule.configure.AddVaccinationSchedule({isPopup: false,
																								  mode:schldVaccineMsg.addMode});
   	 	}
//   	 	if(node.attributes.id === windowCode.vaccinationScheduleList){
//   	 		panelToAdd = new administration.vaccinationSchedule.manage.VaccinationSchedulesList();
//   	 	}
   	 	if(node.attributes.id === windowCode.patientVacciantionSchedule){
   	 		panelToAdd = new administration.vaccinationSchedule.
   	 						 patientsVaccinationSchedule.PatientsVaccinationSchedule();
   	 	}
   	 	//VACCINATION END
   	 	
   	 	//SERVICES START
   	 	if( node.attributes.id === windowCode.configureServiceGroup ) {
   	 		var panel = new administration.service_group_package.addServiceGroup.ConfigureServiceGroup({
							mode: svcAndGrpAndPkgMsg.addMode,
							isPopUp : false
						});
   	 		panelToAdd = panel.getPanel();
   	 	}
//   	 	if( node.attributes.id === windowCode.serviceGroupList  ) {
//   	 		var panel = new administration.service_group_package.searchServiceGroup.SearchServiceGroups();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 	if( node.attributes.id === windowCode.configureService ) {
   	 		var panel = new administration.service_group_package.addService.ConfigureService({
						mode: svcAndGrpAndPkgMsg.addMode,
						isPopUp : false
					});
   	 		panelToAdd = panel.getPanel();
   	 	}
//   	 	if( node.attributes.id === windowCode.serviceList ||
//   	 			 node.attributes.id === windowCode.serviceList1) {
//   	 		var panel = new administration.service_group_package.searchService.SearchServices();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 	if( node.attributes.id === windowCode.configurePackage ) {
   	 		var panel = new administration.service_group_package.addPackage.ConfigurePackage({
							mode: svcAndGrpAndPkgMsg.addMode,
							isPopUp : false
						});
   	 		panelToAdd = panel.getPanel();
   	 	}
//   	 	if( node.attributes.id === windowCode.packageList ||
//   	 			 node.attributes.id === windowCode.packageList1) {
//   	 		var panel =  new administration.service_group_package.searchPackage.SearchPackages();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 	
   	 	if( node.attributes.id === windowCode.updateServicePrice ) {
   	 		panelToAdd =  new administration.service_group_package.priceUpdate.UpdataServicePrice();
	 		 
	 	}
   	 	
   	 	// SERVICES END
   	 	
   	 	// MEDICINES START
		if( node.attributes.id === windowCode.configureBrand ) {
			var panel = new administration.medicines.addBrand.ConfigureBrand({
				title: pharmacyMsg.addBrand,
				mode:pharmacyMsg.addMode,
				isPopup : false
			});
			panelToAdd = panel.getPanel();
		}
		
//		if( node.attributes.id === windowCode.brandList  ) {
//			var panel = new administration.medicines.manageBrands.SearchBrands();
//			panelToAdd = panel.getPanel();
//		}
		if( node.attributes.id === windowCode.configureMedicine ) {
			var panel = new administration.medicines.addMedicine.ConfigureMedicine({
				title: pharmacyMsg.addMedicine,
				mode:pharmacyMsg.addMode,
				isPopup: false
			});
			panelToAdd = panel.getPanel();
		}
//		if( node.attributes.id === windowCode.medicineList ||
//				node.attributes.id === windowCode.medicineList1) {
//			var panel = new administration.medicines.manageMedicines.SearchMedicines();
//			panelToAdd = panel.getPanel();
//		}
		// MEDICINES END
   	 	
   	 	//PATIENT START
   	 	if( node.attributes.id === windowCode.patientRegistration ||
   	 		 node.attributes.id === windowCode.patientRegistration1) {
   	 		var panel =  new OPD.registration.PatientRegistrationPanel();
   	 		panelToAdd = panel.getPanel();
   	 	}
   	 	
//   	 	if( node.attributes.id === windowCode.patientList ||
//   	 		 node.attributes.id === windowCode.patientList1) {
//   	 		var panel =  new OPD.managePatient.PatientMgmnt();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 	//PATIENT END
   	 	
   	 	//NEW APPOINTMENT START
   	 	if( node.attributes.id === windowCode.newAppointment ) {
   	 		var panel =  new newAppointmentPanel();
   	 		panelToAdd = panel.loadPanel();
   	 	}
   	 	//NEW APPOINTMENT END
   	 	
   	 	//NEW APPOINTMENT START
//   	 	if( node.attributes.id === windowCode.appointmentList ) {
//   	 		var panel =  new getManageAppointmentsPnl();
//   	 		panelToAdd = panel;
//   	 	}
   	 	//NEW APPOINTMENT END
   	 	
   	 	//TREATMENT HISTORY START
   	 	if( node.attributes.id === windowCode.treatmentHistory ) {
   	 		var panel =  new OPD.treatmentHistory.PatientHistory.History({directFlag: true});
   	 		panelToAdd = panel;
   	 	}
   	 	//TREATMENT HISTORY END
   	 	
		//OPD-BILLING START
   	 	
   	 	if( node.attributes.id === windowCode.billing ||
   	 			 node.attributes.id === windowCode.billing1) {
   	 		var panel =  new OPD.billing.OpBillingPanel();
   	 		panelToAdd = panel.getPanel();
   	 	}
   	 	
   	 	//OPD-BILLING END

   	 if( node.attributes.id === windowCode.addCheckList) {
   		 	panelToAdd =  new administration.checkList.configure.ConfigureCheckList();
	 		
	 	}
   	 	
   	 	// SERVICE ASSIGNEMENT START
   	 		if( node.attributes.id === windowCode.serviceAssignement ) {
	   	 		var panel =  new OPD.serviceAssignement.ManageServiceAssignment();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
   	 		
   	 	// SERVICE ASSIGNEMENT END
   	 	
	   	// DOCTOR MANAGEMENT START
	   	 	if( node.attributes.id === windowCode.addDoctor ) {
	   	 		var panel =  new CreateDoctor({isPopup: false});
	   	 		panelToAdd = panel.getPanel();
	   	 	}
//	   	 	if( node.attributes.id === windowCode.doctorList ) {
//	   	 		var panel =  new getSearchDoctor();
//	   	 		panelToAdd = panel;
//	   	 	}
	   	 	
	   	// DOCTOR MANAGEMENT END 	
		
		// ROSTER MANAGEMENT START
	   	 	if( node.attributes.id === windowCode.addRoster ) {
	   	 		var panel =  new CreateRoster({ 
                 		type:'doctor', 
                 		mode:'', 
                 		flag: false, 
                 		entityName:''
             		});
	   	 		panelToAdd = panel.loadPanel();
	   	 	}
	   	 	
//	   	 	if( node.attributes.id === windowCode.rosterList ) {
//	   	 		var panel =  new getManageRoster();
//	   	 		panelToAdd = panel;
//	   	 	}
	   	// ROSTER MANAGEMENT END

   	 	//USER & ROLES START
   	 		if( node.attributes.id === windowCode.createUser ) {
	   	 		var panel =  new administration.userManagement.addUser.AddUser({isPopup: false, mode:userMsg.addMode});
	   	 		panelToAdd = panel.getPanel();
	   	 		panel.setDefaultValues();
	   	 	}
	   	 	
//	   	 	if( node.attributes.id === windowCode.userList ) {
//	   	 		var panel =  new administration.userManagement.searchUser.ManageUser();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
	   	 	if( node.attributes.id === windowCode.createRole ) {
	   	 		var panel =  new administration.userManagement.addRole.AddRole({});
	   	 		panelToAdd = panel.getPanel();
	   	 	}
//	   	 	if( node.attributes.id === windowCode.roleList ) {
//	   	 		var panel =  new administration.userManagement.searchRole.ManageRoles();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
   	 	// USER & ROLES END
	   	 	
	   	 // BED START
	   	 	if( node.attributes.id === windowCode.addBed ) {
	   	 		var panel =  new administration.addBed.Bed();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
	   	 	if( node.attributes.id === windowCode.addBedPool ) {
	   	 		var panel =  new administration.addBedPool.BedPool();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
	   	 	if( node.attributes.id === windowCode.addBedEnvelop ) {
	   	 		var panel =  new administration.addBedEnvelope.BedEnvelop();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
//	   	 	if( node.attributes.id === windowCode.bedsList ) {
//	   	 		var panel =  new administration.manageBed.SearchBeds();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
//	   	 	if( node.attributes.id === windowCode.bedPoolList ) {
//	   	 		var panel =  new administration.manageBedPool.SearchBedPool();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
//	   	 	if( node.attributes.id === windowCode.bedEnvelopsList ) {
//	   	 		var panel =  new administration.manageBedEnvelop.SearchBedEnvelop();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
	   	 	if( node.attributes.id === windowCode.bedBooking ) {
	   	 		var panel =  new administration.manageBed.BookBed({source: 'bbed'});
	   	 		panelToAdd = panel.getPanel();
	   	 	}
	   	 	if( node.attributes.id === windowCode.assignBed ) {
	   	 		var panel =  new administration.manageBed.AssignBed({});
	   	 		panelToAdd = panel.getPanel();
	   	 	}
//	   	 	if( node.attributes.id === windowCode.bedOccupancy ) {
//	   	 		var panel =  new administration.manageBed.OverallBedOccupancy();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
//	   	 	if( node.attributes.id === windowCode.manageBedBooking ) {
//	   	 		var panel =  new administration.manageBed.ManageBedBooking();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
	   	 	if( node.attributes.id === windowCode.addOrder ) {
	   	 		var panel =  new IPD.addOrder.DoctorOrder();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
	   	 	if( node.attributes.id === windowCode.addOrderTemplate ) {
	   	 		var panel =  new IPD.addOrderTemplate.DoctorOrderTemplate();
	   	 		panelToAdd = panel.getPanel();
	   	 	}
//	   	 	if( node.attributes.id === windowCode.doctorOrdersList ) {
//	   	 		var panel =  new IPD.doctorOrderList.SearchDoctorOrder();
//	   	 		panelToAdd = panel.getPanel();
//	   	 	}
   	 	// BED END
	   	 	
   	 	
   	 	// REPORTS START
   	 	if( node.attributes.id === 'PAT-VACC-SCHEDULE' ) {
   	 		panelToAdd =  new reports.patientVaccinationScheduleReport.InputForm();
   	 	}
   	 	// REPORTS END
   	 	
   	 	//IPD START
   	 	
   	 	if(node.attributes.id === windowCode.addInsurer ||
   	 			node.attributes.id === windowCode.addInsurer1){
   	 		var panel = new administration.addInsurer.Insurer();
   	 		panelToAdd = panel.getPanel();
   	 	}
  
   	 	if(node.attributes.id === windowCode.addInsurerPlan ){
   	 		var panel = new administration.insurerPlan.InsurancePlan();
   	 		panelToAdd = panel.getPanel();
   	 	}
  
   	 	if(node.attributes.id === windowCode.addSponsor ||
   	 			node.attributes.id === windowCode.addSponsor1 ){
   	 		var panel = new administration.addSponsor.Sponsor();
   	 		panelToAdd = panel.getPanel();
   	 	}
   	 	if(node.attributes.id === windowCode.addClaim ||
   	 			node.attributes.id === windowCode.addClaim1 ){
   	 		var panel = new IPD.addClaim.Claim();
   	 		panelToAdd = panel.getPanel();
   	 	}

//   	 	if(node.attributes.id === windowCode.claimList ||
//   	 		node.attributes.id === windowCode.claimList1 ){
//   	 		var panel = new IPD.manageClaim.SearchClaims();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
	
   	 	if(node.attributes.id === windowCode.doctorOrderGroup ){
   	 		var panel = new IPD.doctorOrderGroup.DoctorOrderGroup();
   	 		panelToAdd = panel.getPanel();
   	 	}

//   	 	if(node.attributes.id === windowCode.insurerList ||
//   	 		node.attributes.id === windowCode.insurerList1){
//   	 		var panel = new administration.manageInsurer.SearchInsuranceCompanies();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
 
//   	 	if(node.attributes.id === windowCode.sponsorList ||
//   	 		node.attributes.id === windowCode.sponsorList1){
//   	 		var panel = new administration.manageSponsor.SearchSponsors();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 
//   	 	if(node.attributes.id === windowCode.insurancePlanList ){
//   	 		var panel = new administration.managePlan.SearchPlans();
//   	 		panelToAdd = panel.getPanel();
//   	 	}

   	 	if(node.attributes.id === windowCode.systemConfiguration ){
   	 		var panel = new administration.systemConfiguration.SystemParamPanel();
   	 		panelToAdd = panel.getPanel();
   	 	}

//   	 	if(node.attributes.id === windowCode.doctorOrderTemplateList ){
//   	 		var panel = new IPD.manageDoctorOrderTemplate.SearchDoctorOrderTemplate();
//   	 		panelToAdd = panel.getPanel();
//   	 	}

//   	 	if(node.attributes.id === windowCode.doctorOrderGroupList ){
//   	 		var panel = new IPD.manageDoctorOrderGroup.SearchDoctorOrderGroup();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
   	 	
//   	 	if( node.attributes.id === windowCode.serviceAssignmentList ) {
//   	 		var panel =  new OPD.serviceAssignement.ManageServiceAssignment();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
 
 		// OPD ISSUE RECEIPT
 		
   	 	if(node.attributes.id === windowCode.issueReceipt ){
   	 		var panel = new OPD.issueReceipt.IssueReceipt();
   	 		panelToAdd = panel.getPanel();
   	 	}
   	 	// ADMISSION START
 		if( node.attributes.id === windowCode.viewAdmissionDetails ){
 			var panel = new IPD.admission.Admission();
   	 		panelToAdd = panel.getPanel();
 		}
 		//This window is called from Billing node
 		if( node.attributes.id === windowCode.admission1){
 			var panel = new IPD.admission.Admission({fromBilling:true});
   	 		panelToAdd = panel.getPanel();
 		}
// 		 if(node.attributes.id === windowCode.manageAdmissions ){
//			var panel = new IPD.admission.manageAdmissions.ManageAdmissions();
//			panelToAdd = panel.getPanel();
//		}

 		 
   	 	//ADMISSION END
   	 	
   	 	if( node.attributes.id === 	windowCode.pharmacy ){
   	 		var pharmacyUrl = 'http://10.0.0.75:8087/webui';
   	 		window.open(pharmacyUrl);
   	 	}
   	 	
   	 	if( node.attributes.id === 	windowCode.materialManagement ){
   	 		var materialManagementUrl = 'http://10.0.0.75:8087/webui';
   	 		window.open(materialManagementUrl);
   	 	}
   	 	
   	 	if( node.attributes.id === 	windowCode.accounting ){
   	 		var accountingUrl = 'http://10.0.0.75:8087/webui';
   	 		window.open(accountingUrl);
   	 	}
   	 	
   	 	if( node.attributes.id === 	windowCode.humanResource ){
   	 		var humanResourceUrl = 'http://10.0.0.75:8087/webui/WtcHRM-1.0';
   	 		window.open(humanResourceUrl);
   	 	}
   	 	
   	 	if( node.attributes.id === 	windowCode.kabota ){
   	 		var analysisUrl = 'http://10.0.0.75:9090/kabota';
   	 		window.open(analysisUrl);
   	 	}
   	 	

   	 	// LIMS REQUISITION ORDER
   	 	
//   	 	if( node.attributes.id === 	windowCode.requisitionList ){
//   	 		var panel = new LIMS.requisitionOrder.manage.RequisitionOrderSearchPanel();
//   	 		panelToAdd = panel.getPanel();
//   	 	}
//   	 	
   	 	
   	 	
   	 	//LIMS starts
   	 	
   	 if( node.attributes.id === windowCode.addTechnique ){
	 		panelToAdd = new LIMS.technique.AddTechnique();
	 	}
   	 
//   	if( node.attributes.id === windowCode.searchTechnique ){
//   			panelToAdd = new LIMS.technique.SearchTechnique();
//   		}
   	
   	if( node.attributes.id === windowCode.addLabTest ){
 		panelToAdd = new LIMS.labTest.configure.AddLabTest();
 	}
   	if( node.attributes.id === windowCode.addLabDetail ){
 		panelToAdd = new LIMS.labConfiguration.AddLabDetail();
   	}
   	
//   	if( node.attributes.id === windowCode.searchLaboratory ){
// 		panelToAdd = new LIMS.labConfiguration.SearchLaboratory();
//   	}
   	
   	if( node.attributes.id === windowCode.addCollectionPoint ){
   		panelToAdd = new LIMS.specimenCollectionPoint.ConfigureCollectionPoint();
   	}

//   	if( node.attributes.id === windowCode.collectionPointList ){
//   		if( !Ext.isEmpty( window.collectionPointList )){
// 			layout.getCenterRegionTabPanel().setActiveTab( window.collectionPointList.ownerCt )
// 		}
// 		else{
// 			panelToAdd = new LIMS.specimenCollectionPoint.manage.CollectionPointSearchPanel();
// 			window.collectionPointList = panelToAdd;
// 		}	
//   	}

   
   	if( node.attributes.id === windowCode.addTestAttribute ){
 		panelToAdd = new LIMS.testAttribute.AddTestAttribute();
   		
   	}
   	
	if( node.attributes.id === windowCode.addOT ){
 		panelToAdd = new OT.manageOT.ConfigureOT();
   		
   	}
	
	if( node.attributes.id === windowCode.otBooking ){
 		panelToAdd = new OT.booking.OTBookingList();
   		
   	}
	
	if( node.attributes.id === windowCode.otRegister ){
 		panelToAdd = new OT.register.OTRegisterPanel();
   		
   	}
	 	
//   	if( node.attributes.id === windowCode.testAttributeList ){
//   		
//   		panelToAdd = new LIMS.testAttribute.SearchTestAttribute();
//   		
//   	}
   	if( Ext.isEmpty( panelToAdd )){
	 	panelToAdd = InstanceFactory.getInstance( node.attributes.id );
   	}
   	 	if( node.attributes.id === windowCode.admissionOrder ){
   	 		
   	 		var panel = new IPD.addOrder.DoctorOrder({isFromDirectLink : true});
			panelToAdd = panel.getPanel();
   	 	}
   	 	
   	 if( node.attributes.id === windowCode.dischargeOrder ){
	 		
	 		var panel = new IPD.addOrder.DoctorOrder({isFromDischargeLink : true});
			panelToAdd = panel.getPanel();
	 	}
 		
   	 	return panelToAdd;
 	}
 	
});

function filterTree( node, value, treeFilter ){
 	var childNodes = node.childNodes;
 	for( var i=0 ; i< childNodes.length ; i++ ){
 		
 		if( childNodes[i].childNodes.length > 0 ){
 			this.filterTree( childNodes[i], value, treeFilter );
 		}else{
 			var startNode = childNodes[i];
 			
// 			var f = function( startNode, value ){
 				var len = value.length;	
 				var nodeTest =  startNode.attributes.text.substr ( 0 ,len )
 				
 				if( !(value.toLowerCase() == nodeTest.toLowerCase() ) ){
// 					return true;
 					if( !startNode.hasChildNodes() ){
 						startNode.remove( );
 					}
 					
 				}
// 			}
 			
// 			var array = new Array();
// 			
// 			array.push(startNode,value );
 			
// 			startNode.cascade(f, this,array );
 		}
 	}
   	 
}

Ext.reg('modules-tree-panel', ModulesTreePanel);
