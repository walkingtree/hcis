Ext.namespace("administration.vaccinationSchedule");

administration.vaccinationSchedule.Messages = function(){
	
	
	this.scheduleName = "Schedule name";
	this.scheduleCode = "Schedule code";
	this.dosage ="Dosage";
	this.description ="Description";
	
	this.vaccinationName ="Vaccination name";
	this.range ="Range";
	this.fixed ="Fixed";
	this.fixedRepetition =" Fixed repetition";
	this.Period ="Period (in days)";
	this.afterPeriod ="After period";
	
	this.others ="others";
	this.disease ="Disease";
	this.remarks ="Remarks";
	
	this.afterVaccinationName =" After vaccination name";
	
	// grid headers 
	this.period ="Period";
	
	// form names
	this.formVaccinationName ="vaccinationName";
	this.formScheduleDesc = "description";
	this.formDosage ="dosage";
	this.formScheduleName ="scheduleName";
	this.formRangePeriod ="rangePeriod";
	this.formFixedPeriod ="fixedPeriod";
	this.formFixedRepetitionPeriod ="fixedRepetitionPeriod";
	this.formOthers ="others";
	this.formAfterPeriod ="afterPeriod";
	
	this.formDisease ="disease";
	this.formPeriodCbx = "period";
	this.formAfterPeriodCbx ="afterPeriod";
	this.formAfterPeriod ="afterPeriodtext";
	this.formOtherVaccine ="otherVaccine";
	this.formAfterOtherVaccine ="afterOtherVaccine";
	this.formRemarks ="remarks";
	this.formAfterVaccinationName ="afterVaccinationName";
	this.formScheduleId ="scheduleId";
	this.scheduleId =" schedule Id";
	
	//buttons
	this.btnSaveSchedule =" schedule";
	this.btnReset ="Reset";
	this.btnAdd ="Add";
	this.btnEdit ="Edit";
	this.btnDelete ="Delete";
	this.btnViewDetails ="View details";
	this.btnGenerateSchedule ="Generate schedule";
	this.viewDetails = "View schedule details";
	
	// titles
	this.vaccinationDetails ="Item Details";
	this.addVaccinationSchedule = 'Add vaccination schedule';
	this.editVaccinationSchedule ='Edit vaccination schedule';
	this.addComments = "Add comments";
	this.periodIsEmpty =" Please specify the period and retry!";
	this.diseaseIsEmpty =" Please specify the disease and retry!";
	this.vaccinationIsEmpty =" Please specify the vaccination and retry!";
	this.resetMessage ="Do you want to reset?";
	this.scheduleIsEmpty = "Please select vaccination schedule!";
	this.doctorIsEmpty = "Please select a doctor!";
	this.startDateIsEmpty = "Please specify start date!";
	this.scheduleAlredyExists = "This schedule has already been selected!";
	this.displayingScheduleMsg = "Displaying Vaccination schedules {0} - {1} of {2}";
	this.noScheduleMsg = "No schedules found";
	
	this.deleteMessage ="Do you want to delete selected vaccinationschedules?";
	
	// mode
	this.editMode ="edit";
	this.addMode ="add";
};

 var schldVaccineMsg = new administration.vaccinationSchedule.Messages();