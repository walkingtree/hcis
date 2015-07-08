var surgicalServiceStore;
var coordinatorEntityStore;
var otBedStore;
var surgeryServiceTypeStore;
var EspecialityStore;
var operationTheatreStore;
var surgeryStore;


var recordForOT = Ext.data.Record.create([
     {name: "code", mapping : "code", type: "string"},
     {name: "description", mapping :"description" , type: "string"},
 ]);

function loadSurgicalTypeServiceCbx(){
	var surgicalRecord = Ext.data.Record.create([
         {name: "code", mapping : "serviceCode", type: "string"},
         {name: "description", mapping :"serviceName" , type: "string"},
         {name : "serviceCharge",mapping :"serviceCharge" }
     ]);
	surgicalServiceStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getServiceByServiceTypeCode, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, surgicalRecord),
	    remoteSort: true
	});
	surgicalServiceStore.load({params:{start:0, limit:999}, arg:[otMsg.surgicalServiceType]});
	return surgicalServiceStore;
}

function loadCoordinatorEntityCbx(){
	
	coordinatorEntityStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getEntities, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordForOT),
	    remoteSort: true
	});
	coordinatorEntityStore.load({params:{start:0, limit:999}, arg:[otMsg.COORDINATOR_ENTITY]});
	return coordinatorEntityStore;
}

function loadOtBedCbx(){
	
	otBedStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(IPReferenceDataManager.getBedMaster, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordForOT),
	    remoteSort: true
	});
	otBedStore.load({params:{start:0, limit:999}, arg:[otMsg.OT_BED_TYPE]});
	return otBedStore;
}

function loadSurgeryServiceTypeCbx(){
	
	surgeryServiceTypeStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordForOT),
	    remoteSort: true
	});
	surgeryServiceTypeStore.load({params:{start:0, limit:999}, arg:[otMsg.SERVICE_TYPE_SURGERY]});
	return surgeryServiceTypeStore;
}

function loadEspecialityCbx(){
	
	EspecialityStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(DataModelManager.getEspectiality, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordForOT),
	    remoteSort: true
	});
	EspecialityStore.load({params:{start:0, limit:999}, arg:[]});
	return EspecialityStore;
}

function loadOperationTheatreCbx(){
	
	operationTheatreStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(OTReferenceDataManager.getOperationTheatreList, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, recordForOT),
	    remoteSort: true
	});
	operationTheatreStore.load({params:{start:0, limit:999}, arg:[]});
	return operationTheatreStore;
}

function loadSurgeryStore( ){
	var surgeryRecord = Ext.data.Record.create([
         {name: "code", mapping : "service", convert : getCode},
         {name: "description", mapping : "service", convert : getDescription},
         {name : "referenceType",mapping :"referenceType" },
         {name : "referenceNbr",mapping :"referenceNumber" },
         {name : "serviceUid",mapping :"serviceUid" }
     ]);
	var surgeryStore = new Ext.data.Store({
	    proxy: new Ext.data.DWRProxy(ServiceManager.getAssignedServicesForServiceType, true),
	    reader: new Ext.data.ListRangeReader(
	    	{totalProperty:'totalSize'}, surgeryRecord),
	    remoteSort: true
	});
//	surgicalServiceStore.load({params:{start:0, limit:999}, arg:[otMsg.surgicalServiceType]});
	return surgeryStore;

}







