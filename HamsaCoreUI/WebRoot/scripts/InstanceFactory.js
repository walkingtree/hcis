InstanceFactory = function(){
	var instance = {};

	return {
		removeInstance : function( winId ){
			instance['Win'+ winId] = null;
		},
		
		getInstance : function( nodeId ){
		 
         	if( nodeId === windowCode.referralList ){
         		if( Ext.isEmpty( instance['Win'+nodeId] )){
         			instance['Win'+nodeId]  = new administration.referralManagement.ReferralsList();     
         			return instance['Win'+nodeId];
         		}
         		else{
         			return instance['Win'+nodeId];
         		}
         	}
       	 	
         	if( nodeId === windowCode.vaccinationScheduleList){
       	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
       	 			instance['Win'+nodeId] = new administration.vaccinationSchedule.manage.VaccinationSchedulesList();
       	 			return instance['Win'+nodeId];
       	 		}
       	 		else{
       	 			return instance['Win'+nodeId];
       	 		}
       	 	}
       	 	
       	 	if( nodeId === windowCode.serviceGroupList  ) {
       	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
       	 			instance['Win'+nodeId] = new administration.service_group_package.searchServiceGroup.SearchServiceGroups().getPanel();
       	 			return instance['Win'+nodeId];
       	 		}
       	 		else{
       	 			return instance['Win'+nodeId];
       	 		}
       	 	}
       	 	
       	 	if( nodeId === windowCode.serviceList ||
       	 			nodeId === windowCode.serviceList1) {
       	 			if( Ext.isEmpty( instance['Win'+nodeId] )){
       	 			instance['Win'+nodeId] = new administration.service_group_package.searchService.SearchServices().getPanel();
       	 				return instance['Win'+nodeId];
       	 			}
       	 			else{
       	 				return instance['Win'+nodeId];
       	 			}
      	 		
      	 	}
       	 	
       	 	if( nodeId === windowCode.packageList ||
       	 			nodeId === windowCode.packageList1) {
       	 			if( Ext.isEmpty( instance['Win'+nodeId] )){
       	 				instance['Win'+nodeId] =  new administration.service_group_package.searchPackage.SearchPackages().getPanel();
       	 				return instance['Win'+nodeId];
       	 			}
       	 			else{
       	 				return instance['Win'+nodeId];
       	 			}
      	 	}
       	 	
    		if( nodeId === windowCode.brandList  ) {
    			if( Ext.isEmpty( instance['Win'+nodeId] )){
    				instance['Win'+nodeId] = new administration.medicines.manageBrands.SearchBrands().getPanel();
    				return instance['Win'+nodeId];
    			}	
    			else{
    				return instance['Win'+nodeId];
    			}
    		}
    		
    		if( nodeId === windowCode.medicineList ||
    				nodeId === windowCode.medicineList1) {
    				if( Ext.isEmpty( instance['Win'+nodeId] )){
    					instance['Win'+nodeId] = new administration.medicines.manageMedicines.SearchMedicines().getPanel();
    					return instance['Win'+nodeId];
    				}
    				else{
    					return instance['Win'+nodeId];
    				}
    		}

       	 	if( nodeId === windowCode.patientList ||
          	 		 nodeId === windowCode.patientList1) {
       	 			if( Ext.isEmpty( instance['Win'+nodeId] )){
       	 				instance['Win'+nodeId] = new OPD.managePatient.PatientMgmnt().getPanel();
       	 				return instance['Win'+nodeId];
       	 			}
       	 			else{
       	 				return instance['Win'+nodeId];
       	 			}
      	 	}
       	 	
       	 	if( nodeId=== windowCode.appointmentList ) {
       	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	       	 		instance['Win'+nodeId] =  new getManageAppointmentsPnl();
	       	 		return instance['Win'+nodeId];
       	 		}
       	 		else{
       	 			return instance['Win'+nodeId];
       	 		}
       	 	}

	   	 	if( nodeId === windowCode.doctorList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new getSearchDoctor();
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId === windowCode.entityList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageEntity.manage.EntityList();
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	
	   	 	if( nodeId === windowCode.rosterList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new getManageRoster();
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.userList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
		   	 		instance['Win'+nodeId] =  new administration.userManagement.searchUser.ManageUser().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.roleList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.userManagement.searchRole.ManageRoles().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.bedsList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageBed.SearchBeds().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.bedPoolList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageBedPool.SearchBedPool().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.bedEnvelopsList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageBedEnvelop.SearchBedEnvelop().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.manageBedBooking ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageBed.ManageBedBooking().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.doctorOrdersList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new IPD.doctorOrderList.SearchDoctorOrder().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if(nodeId === windowCode.claimList ||
	   	   	 		nodeId === windowCode.claimList1 ){
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new IPD.manageClaim.SearchClaims().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if(nodeId === windowCode.insurerList ||
	   	 		nodeId === windowCode.insurerList1){
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageInsurer.SearchInsuranceCompanies().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}

	   	 	if(nodeId === windowCode.sponsorList ||
	   	 		nodeId === windowCode.sponsorList1){
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageSponsor.SearchSponsors().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.insurancePlanList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.managePlan.SearchPlans().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}

	   	 	if( nodeId=== windowCode.doctorOrderTemplateList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new IPD.manageDoctorOrderTemplate.SearchDoctorOrderTemplate().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.doctorOrderGroupList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new IPD.manageDoctorOrderGroup.SearchDoctorOrderGroup().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}

	   	 	if( nodeId=== windowCode.serviceAssignmentList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new OPD.serviceAssignement.ManageServiceAssignment().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.manageAdmissions ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new IPD.admission.manageAdmissions.ManageAdmissions().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.requisitionList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new LIMS.requisitionOrder.manage.RequisitionOrderSearchPanel().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.searchTechnique ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new LIMS.technique.SearchTechnique();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.searchLaboratory ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new LIMS.labConfiguration.SearchLaboratory();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.collectionPointList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new LIMS.specimenCollectionPoint.manage.CollectionPointSearchPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.testAttributeList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new LIMS.testAttribute.SearchTestAttribute();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 	if( nodeId=== windowCode.otList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new OT.manageOT.OTSearchPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}
	   	 	
	   	 if( nodeId=== windowCode.searchCheckList ) {
	   	 		if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.checkList.CheckListSearchPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	   	 	}

	   	 	if( nodeId === windowCode.bedOccupancy ) {
		   	 	if( Ext.isEmpty( instance['Win'+nodeId] )){
	   	 			instance['Win'+nodeId] =  new administration.manageBed.OverallBedOccupancy().getPanel();
		   	 		return instance['Win'+nodeId];
	   	 		}
	   	 		else{
	   	 			return instance['Win'+nodeId];
	   	 		}
	 		}
	   	 
         	return null;
      	}
   	 	

	};
}();
