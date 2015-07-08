
//LIMS
var hospitalNameStore;
var labTypeStore;


var testTypeStore;
var testAvailableForGenderStore;
var labStore;
var testSpecimenStore;
var testAttributeStore;
var techniqueReagentStore;
var testMeasurementUnitStore;
var testAttributeTypeStore;
var labTestStore;
var reagentStore;
var techniqueStore;

//REQUISITION ORDER

var referenceTypeStore;
var testStatusStore;



function record(){
	var record = Ext.data.Record.create([
	  {name: "code", type: "string"},
	  {name: "description", type: "string"},
	  {name : "isDefault" , type : "string" }
	]);
	return record;
}



function loadHospitalNameStoreCbx(){
	if( Ext.isEmpty( hospitalNameStore ) ){
		hospitalNameStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getHospitalName, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		hospitalNameStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return hospitalNameStore;
}

function loadLabTypeStoreCbx(){
	if( Ext.isEmpty( labTypeStore ) ){
		labTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getLabType, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  });
		labTypeStore.load({params:{start:0, limit:8}, arg:[]});
	}
	return labTypeStore;
}


//REQUISITION ORDER

function loadReferenceTypeCbx(){
	if(Ext.isEmpty( referenceTypeStore )){
		referenceTypeStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
		    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
			
		});
		referenceTypeStore.load({params:{start:0, limit:8}, arg:[limsMsg.refType]});
	}
	
	return referenceTypeStore;
}


function loadTestStatusCbx(){
	if(Ext.isEmpty( testStatusStore )){
		testStatusStore = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
		    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
			
		});
		testStatusStore.load({params:{start:0, limit:8}, arg:[limsMsg.testRequisitionStatus]});
	}
	
	return testStatusStore;
}


function loadTestAvailableForGenderStore(){
	if( Ext.isEmpty( testAvailableForGenderStore ) ){
		testAvailableForGenderStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getTestApplicableGengers, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testAvailableForGenderStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testAvailableForGenderStore;
}


function loadLabStore(){
	if( Ext.isEmpty( labStore ) ){
		labStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getLabs, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		labStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return labStore;
}



function loadTestSpecimenStore(){
	if( Ext.isEmpty( testSpecimenStore ) ){
		testSpecimenStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getSpecimens, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testSpecimenStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testSpecimenStore;
}


function loadTestAttributeStore(){
	if( Ext.isEmpty( testAttributeStore ) ){
		testAttributeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getAllTestAttribute, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testAttributeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testAttributeStore;
}

function loadTestTypeStore(){
	if( Ext.isEmpty( testTypeStore ) ){
		testTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getTestTypes, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testTypeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testTypeStore;
}


function loadTechniqueReagentStore(){
	if( Ext.isEmpty( techniqueReagentStore ) ){
		techniqueReagentStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getTechniqueReagent, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		techniqueReagentStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return techniqueReagentStore;
}

function loadTestMeasurementUnitStore(){
	if( Ext.isEmpty( testMeasurementUnitStore ) ){
		testMeasurementUnitStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getMeasurementUnit, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testMeasurementUnitStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testMeasurementUnitStore;
}


function loadTestAttributeTypeStore(){
	if( Ext.isEmpty( testAttributeTypeStore ) ){
		testAttributeTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getAttributeType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		testAttributeTypeStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return testAttributeTypeStore;
}

function loadLabTestStore(){
	if( Ext.isEmpty( labTestStore ) ){
		labTestStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getLabTests, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		labTestStore.load({params:{start:0, limit:9999}, arg:[]});
	}
	return labTestStore;
}

function loadReagentStore(){
	if( Ext.isEmpty( reagentStore ) ){
		reagentStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getTechniqueReagentByType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		reagentStore.load({params:{start:0, limit:9999}, arg:["N"]});
	}
	return reagentStore;
}

function loadTechniqueStore(){
	if( Ext.isEmpty( techniqueStore ) ){
		techniqueStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(LimsReferenceDataManager.getTechniqueReagentByType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, record()),
	    remoteSort: true
	  });
		techniqueStore.load({params:{start:0, limit:9999}, arg:["Y"]});
	}
	return techniqueStore;
}


;
