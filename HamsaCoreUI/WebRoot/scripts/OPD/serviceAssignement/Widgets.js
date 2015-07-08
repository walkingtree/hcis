Ext.namespace("OPD.serviceAssignement.");

OPD.serviceAssignement.Widgets = Ext.extend(Object, {
	constructor: function( config ){
		
		Ext.QuickTips.init();
		
		Ext.apply( this, config );
		
		this.serviceGroupCbx = new Ext.form.OptComboBox({
			hiddenName: mngSvcAsgntMsg.formServiceGroup,
			fieldLabel: mngSvcAsgntMsg.serviceGroup,
		    store: loadAddServiceGroupCbx(),
		    mode:'local',
		    anchor: '95%',
		    triggerAction:'all',
		    forceSelection: true,
		    valueField: 'code',
		    displayField: 'description'
		});
		
		this.serviceNameTxf = new Ext.form.TextField({
			fieldLabel: mngSvcAsgntMsg.serviceName,
			name: mngSvcAsgntMsg.formServiceName,
			anchor: '95%'
		});
		
		this.serviceCodeTxf = new Ext.form.TextField({
			fieldLabel: mngSvcAsgntMsg.serviceCode,
			name: mngSvcAsgntMsg.formServiceCode,
			anchor: '95%'
		});
		
		this.packageNameTxf = new Ext.form.TextField({
			fieldLabel: mngSvcAsgntMsg.packageName,
			name: mngSvcAsgntMsg.formPackageName,
			anchor: '95%'
		});
		
		this.packageCodeTxf = new Ext.form.TextField({
			fieldLabel: mngSvcAsgntMsg.packageCode,
			name: mngSvcAsgntMsg.formPackageCode,
			anchor: '95%'
		});	
		
		this.patientCbx = new Ext.form.OptComboBox({
			hiddenName: mngSvcAsgntMsg.formPatient,
			fieldLabel: mngSvcAsgntMsg.patient,
		    store: loadPatientCbx(),
		    anchor: '95%',
		    triggerAction:'all',
		    forceSelection: true,
		    mode:'local',
		    valueField: 'code',
		    displayField: 'description'
		});
		
		this.refNbrTxf = new Ext.form.TextField({
			fieldLabel: mngSvcAsgntMsg.refNbr,
			name: mngSvcAsgntMsg.formRefNbr,
			anchor: '95%'
		});	
		
		this.referenceTypeCbx = new Ext.form.OptComboBox({
			hiddenName: mngSvcAsgntMsg.formRefType,
			fieldLabel: mngSvcAsgntMsg.reftype,
		    store: loadReferenceTypeCbx(),
		    anchor: '95%',
		    triggerAction:'all',
		    mode:'local',
		    forceSelection: true,
		    valueField: 'code',
		    displayField: 'description'
		});
		
		this.departmentCbx = new Ext.form.OptComboBox({
			hiddenName: mngSvcAsgntMsg.formDepartment,
			fieldLabel: mngSvcAsgntMsg.department,
		    store: loadDepartmentsCbx(),
		    anchor: '95%',
		    triggerAction:'all',
		    forceSelection: true,
		    valueField: 'code',
		    mode:'local',
		    displayField: 'description'
		});
		
		
		// these two widgets are earlier effective from and effective to labels. but we changed the labels to service
		// date from and service date to . so these to fileds are related to service date from and service date to .
		this.effectiveFromDtf = new Ext.form.WTCDateField({
			fieldLabel: mngSvcAsgntMsg.serviceDateFrom,
		    name: mngSvcAsgntMsg.formEffectiveFrom,
		    anchor: '85%'
		});
		
		this.effectiveToDtf = new Ext.form.WTCDateField({
			fieldLabel: mngSvcAsgntMsg.serviceDateTo,
		    name: mngSvcAsgntMsg.formEffectiveTo,
		    anchor: '80%'
		});
		
		this.searchBtn = new Ext.Button({
			text: mngSvcAsgntMsg.btnSearch,
			iconCls: 'search_btn',
			scope:this,
			tooltip: mngSvcAsgntMsg.searchAssignedServices
		});
		
		this.renderBtn = new Ext.Button({
			iconCls : 'accept-icon',
			text : mngSvcAsgntMsg.btnRender,
			scope:this,
			disabled:true,
			tooltip: mngSvcAsgntMsg.renderAssignedService
		});
		
		this.cancelBtn = new Ext.Button({
			iconCls : 'cross_icon',
			text : mngSvcAsgntMsg.btnCancel,
			scope:this,
			disabled:true,
			tooltip: mngSvcAsgntMsg.cancelAssignedService

		});
		
		this.viewAssignmentsBtn = new Ext.Button({
			iconCls : 'view-icon',
			text : mngSvcAsgntMsg.btnViewAssignments,
			scope:this,
			disabled:true,
			tooltip: mngSvcAsgntMsg.viewAssignedService

		});
		
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : mngSvcAsgntMsg.btnDelete,
			scope:this,
			disabled:true,
			tooltip: mngSvcAsgntMsg.deleteAssignedService

		});
		
		this.replaceBtn = new Ext.Button({
			iconCls : 'reset-icon',
			text : mngSvcAsgntMsg.btnReplace,
			scope:this,
			disabled:true,
			tooltip: mngSvcAsgntMsg.repalceAssignedService

		});
		this.resetBtn = new Ext.Button({
			text:'Reset',
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: mngSvcAsgntMsg.resetSearchCreteria

		});
		
	},
	getServiceCode: function(){
		return this.serviceCodeTxf;
	},
	getServiceName: function(){
		return this.serviceNameTxf;
	},
	getPackageCode: function(){
		return this.packageCodeTxf;
	},
	getPackageName: function(){
		return this.packageNameTxf;
	},
	getEffectiveFrom: function(){
		return this.effectiveFromDtf ;
	},
	getEffectiveTo: function(){
		return this.effectiveToDtf;
	},
	getDepartment: function(){
		return this.departmentCbx;
	},
	getServiceGroup: function(){
		return this.serviceGroupCbx;
	},
	getReftype: function(){
		return this.referenceTypeCbx;
	},
	getRefNbr: function(){
		return this.refNbrTxf;
	},
	getPatient: function(){
		return this.patientCbx;
	},
	getSearchBtn: function(){
		return this.searchBtn;
	},
	getRenderBtn: function(){
		return this.renderBtn;
	},
	getCancelBtn: function(){
		return this.cancelBtn;
	},
	getViewAssignmentsBtn: function(){
		return this.viewAssignmentsBtn;
	},
	getDeleteBtn: function(){
		return this.deleteBtn;
	},
	getReplaceBtn: function(){
		return this.replaceBtn;
	},
	getResetBtn: function(){
		return this.resetBtn;
	}

});