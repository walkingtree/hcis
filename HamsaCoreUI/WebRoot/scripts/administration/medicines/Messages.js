Ext.namespace("administration.medicines");

administration.medicines.Messages= function(){
	
	//Titles
	this.manageBrands = "Manage brands";
	this.addBrand = "Add brand";
	this.editBrand = "Edit brand";

	this.manageMedicines = "Manage medicines";
	this.addMedicine = "Add medicine";
	this.editMedicine = "Edit medicine";
	
	//Labels
	this.brandCode = "Brand code";
	this.brandName = "Brand name";
	
	this.medicineCode = "Medicine code";
	this.medicineName = "Medicine name";
	this.medicineType = "Medicine type";
	this.strength = "Strength";
	this.dosagePerDay = "Dosage(per day)";
	
	this.status = "Status";

	//Buttons
	this.btnSearch = "Search";
	this.btnAdd = "Add";
	this.btnEdit = "Edit";
	this.btnMarkInactive = 'Mark Inactive';
	this.btnSave = "Save";
	this.btnReset = "Reset";
	
	//Tool tips
	this.searchBrands = "Click here to search for brands";
	
	//Miscellaneous
	this.activeStatus = "1";
	this.inactiveStatus = "0";
	this.isActive = "Is active?";
	
	this.addMode = 'add';
	this.editMode = 'edit';
	
	//Errors/Messages
	this.displayingBrandsMsg = "Displaying brands {0} - {1} of {2}";
	this.noBrandsMsg = "No brands found";
	this.markBrandInactiveMsg = "Brand(s) has been inactived successfully..!";
	this.addBrandSuccessMsg = "Brand has been created successfully..!";
	this.editBrandSuccessMsg = "Brand has been updated successfully..!";
	this.invalidData = "Data entered is valid. Please re-enter..!";

	this.displayingMedicinesMsg = "Displaying Medicines {0} - {1} of {2}";
	this.noMedicinesMsg = "No Medicines found";
	this.markMedicineInactiveMsg = "Medicine(s) has been inactived successfully..!";
	this.addMedicineSuccessMsg = "Medicine has been created successfully..!";
	this.editMedicineSuccessMsg = "Medicine has been updated successfully..!";
	
	this.resetSearchCreteria = " Reset search criteria";
	this.brandExists = "Brand code already exist in system. Please type another Brand code and retry..!";
	this.medicineExists = "Medicine code already exist in system. Please type another Medicine code and retry..!";
}
 
var pharmacyMsg = new administration.medicines.Messages();
