Ext.namespace("OPD.manageAppointment");

OPD.manageAppointment.ManageAppointmentGrid = Ext.extend(Object,{
	constructor: function(config){
		
		Ext.apply(this,config);
		var mainThis = this;
		this.manageAppointmentRecordType = Ext.data.Record.create([
			{ name : "serialNo", type : "int" }, 
			{ name : "appointmentNumber", type : "int" }, 
			{ name : "patientName", type : "string" }, 
			{ name : "patientFirstName", type : "string" }, 
			{ name : "patientLastName", type : "string" }, 
			{ name : "patientMiddleName", type : "string" }, 
			{ name : "patientId", type : "int" }, 
			{ name : "appointmentDate", type : "date" }, 
			{ name : "appointmentStartTime", type : "string" }, 
			{ name : "appointmentEndTime", type : "string" }, 
			{ name : "appointmentStatus", mapping : 'appointmentStatus.description', type : "string" }, 
			{ name : "appointmentStatusCode", mapping : 'appointmentStatus.code', type : "string" }, 
			{ name : "amount", type : "string" }, 
			{ name : "speaciality", mapping : 'speaciality.description', type : "string" }, 
			{ name : "speacialityCode", mapping : 'speaciality.code', type : "string" }, 
			{ name : "bookingType", mapping : 'bookingType.description', type : "string" }, 
			{ name : "bookingTypeCode", mapping : 'bookingType.code', type : "string" }, 
			{ name : "primaryDoctor", mapping : 'primaryDoctor.description', type : "string" }, 
			{ name : "primaryDoctorCode", mapping : 'primaryDoctor.code', type : "string" },
			{ name : "rosterCode", type : "int" },
			{ name : "remarks", type : "string" }
		]);
		
		this.DataStore = new Ext.data.Store({
			proxy : new Ext.data.DWRProxy(
					AppointmentManagementController.getAppointments, true),
			reader : new Ext.data.ListRangeReader({id : 'id',totalProperty : 'totalSize'}, 
												  this.manageAppointmentRecordType),
			remoteSort : true
		});
		
		this.pagingBar = new Ext.WTCPagingToolbar({
			pageSize: 10,
            store: this.DataStore,
            displayInfo: true,
        	displayMsg: 'Displaying records {0} - {1} of {2}',
        	emptyMsg: "No appointments to display"
		});
		this.gridChk = new Ext.grid.CheckboxSelectionModel({singleSelect: config.isSingleSelection});
		
		this.manageAppointmentGridPnl = new Ext.grid.GridPanel({
			autoScroll : true,
			stripeRows : true,
			border : false,
			frame : false,
			bbar: this.pagingBar,
			height : 300,
			forceFit: true,
			sm: this.gridChk ,
			ds : this.DataStore,
			viewConfig:{forceFit:true},
			cm : new Ext.grid.ColumnModel([this.gridChk , {
						header : "Appointment No.",
						width : 50,
						sortable : true,
						dataIndex : 'appointmentNumber'
					}, /*{
						header : "Patient Name",
						width : 100,
						sortable : true,
						dataIndex : 'patientName'
					},*/ {
						header : "Patient First Name",
						width : 150,
						hidden : true,
						sortable : true,
						dataIndex : 'patientFirstName'
					}, {
						header : "Patient Middle Name",
						width : 150,
						hidden : true,
						sortable : true,
						dataIndex : 'patientMiddleName'
					}, {
						header : "Patient Last Name",
						width : 150,
						hidden : true,
						sortable : true,
						dataIndex : 'patientLastName'
					}, {
						header : "Patient ID",
						width : 60,
						sortable : true,
						dataIndex : 'patientId'
					}, {
						header : "Appointment Date",
						dataIndex : 'appointmentDate',
						renderer : Ext.util.Format.dateRenderer('d/m/Y')
					}, {
						header : "From Time",
						width : 70,
						sortable : true,
						dataIndex : 'appointmentStartTime'
					}, {
						header : "To Time",
						width : 50,
						sortable : true,
						dataIndex : 'appointmentEndTime'
					}, {
						header : "Appointment Status",
						width : 140,
						sortable : true,
						dataIndex : 'appointmentStatus'
					}, {
						header : "Appointment Status Code",
						width : 100,
						hidden : true,
						sortable : true,
						dataIndex : 'appointmentStatusCode'
					}, {
						header : "Amount",
						width : 50,
						sortable : true,
						dataIndex : 'amount'
					}, {
						header : "Speaciality Name",
						width : 100,
						sortable : true,
						dataIndex : 'speaciality'
					}, {
						header : "Speaciality Code",
						width : 125,
						hidden : true,
						sortable : true,
						dataIndex : 'speacialityCode'
					}, {
						header : "Booking Type",
						width : 75,
						sortable : true,
						dataIndex : 'bookingType'
					}, {
						header : "Booking Type Code",
						width : 100,
						hidden : true,
						sortable : true,
						dataIndex : 'bookingTypeCode'
					}, {
						header : "Doctor Name",
						width : 100,
						sortable : true,
						dataIndex : 'primaryDoctor'
					}, {
						header : "Doctor Code",
						width : 100,
						hidden : true,
						sortable : true,
						dataIndex : 'primaryDoctorCode'
					}])
			});
	},
	getPanel: function(){
		return this.manageAppointmentGridPnl;
	}
});