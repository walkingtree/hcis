Ext.namespace("LIMS.requisitionOrder.manage");

LIMS.requisitionOrder.manage.RequisitionOrderWidgets = Ext.extend(Object,{
	constructor : function(){
	
		
		this.patientNameCbx = new Ext.form.TextField({
			fieldLabel : limsMsg.patientName,
			name : 'patientName',
			anchor : '98%'
		});
		
		this.patientIdCbx = new Ext.form.NumberField({
			fieldLabel : limsMsg.patientId,
			name : 'patientId',
			anchor : '98%'
		});
		
		this.referenceTypeCbx = new Ext.form.OptComboBox({
			fieldLabel : limsMsg.referenceType,
			hiddenName : 'referenceType',
			store : loadReferenceTypeCbx(),
			mode:'local',
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			anchor:'98%',
			emptyText: limsMsg.selectMsg,
			forceSelection : true
		});

		this.doctorNameCbx = new Ext.form.TextField({
			fieldLabel : limsMsg.doctorName,
			name : 'doctorName',
			anchor : '98%'	
		});
	
		this.doctorIdCbx = new Ext.form.NumberField({
			fieldLabel : limsMsg.doctorId,
			name : 'doctorId',
			anchor : '98%'
		});
		
		this.requisitionFromDate = new Ext.form.WTCDateField({
			fieldLabel : limsMsg.requisitionFromDate,
			name : 'requisitionFromDate',
			anchor : '98%'
		});

		this.requisitionToDate = new Ext.form.WTCDateField({
			fieldLabel : limsMsg.requisitionToDate,
			name : 'requisitionToDate',
			anchor : '98%'
		});
		
		this.testNameCbx = new Ext.form.OptComboBox({
			fieldLabel : limsMsg.testName,
			hiddenName : 'testName',
			store : loadLabTestServiceCbx(),
			mode:'local',
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			anchor:'98%',
			emptyText: limsMsg.selectMsg,
			forceSelection : true
			
		});
		
		this.testStatusCbx = new Ext.form.OptComboBox({
			fieldLabel : limsMsg.testStatus,
			hiddenName : 'testStatus',
			store : loadTestStatusCbx(),
			mode:'local',
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			anchor:'98%',
			emptyText: limsMsg.selectMsg,
			forceSelection : true
			
		});
		
	},
	
	getPatientNameCbx : function(){
		return this.patientNameCbx;
	},

	getPatientIdCbx : function(){
		return this.patientIdCbx;
	},
	getReferenceTypeCbx : function(){
		return this.referenceTypeCbx;
	},
	getDoctorNameCbx : function(){
		return this.doctorNameCbx;
	},
	getDoctorIdCbx : function(){
		return this.doctorIdCbx;
	},
	getRequisitionFromDate : function(){
		return this.requisitionFromDate;
	},
	getRequisitionToDate : function(){
		return this.requisitionToDate;
	},
	getTestNameCbx : function(){
		return this.testNameCbx;
	},
	getTestStatusCbx : function(){
		return this.testStatusCbx;
	}

});