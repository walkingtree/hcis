Ext.namespace('administration.service_group_package.addService');	

administration.service_group_package.addService.Widgets = Ext.extend(Object,{
	constructor: function(config){
		Ext.apply(this, config);
		
		this.serviceCodeTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.code,
			name:'serviceCode',
			anchor:'90%',
			required:true,
			allowBlank:false,
			maxLength: 15
		});
		
		this.serviceNameTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.name,
			name:'serviceName',
			anchor:'90%',
			required:true,
			allowBlank:false,
			maxLength: 45
		});

		this.effectiveFromDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.effectiveFrom,
			name:'effectiveFromDate',
			anchor:'70%',
			required:true,
			allowBlank:false,
//			value: new Date(),
			emptyText: 'dd/mm/YYYY'
		});
		
		this.effectiveToDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.effectiveTo,
			name:'effectiveToDate',
			anchor:'70%',
			emptyText: 'dd/mm/YYYY'
		});

		this.statusCombo = new Ext.form.ComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.status,
			mode:'local',
			store: loadServiceStatusCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'serviceStatus',
			anchor:'70%',
			allowBlank:false,
			forceSelection : true,
			required:true,
			emptyText: svcAndGrpAndPkgMsg.selectMsg
		});
		
		this.serviceTypeCbx = new Ext.form.ComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.serviceType,
			mode:'local',
			store: loadServiceTypeCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'serviceType',
			anchor:'90%',
			allowBlank:false,
			forceSelection : true,
			required:true,
			emptyText: svcAndGrpAndPkgMsg.selectMsg
		});
		
		
		this.suspendFromDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.suspendFrom,
			name:'suspendedFromDt',
			anchor:'70%',
			disabled : true,
			minValue: new Date(),
			emptyText: 'dd/mm/YYYY'
		});
		
		this.suspendToDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.suspendTo,
			name:'suspendedToDt',
			anchor:'70%',
			disabled : true,
			emptyText: 'dd/mm/YYYY'
		});

		this.chargeNbrField = new Ext.form.WTCAmountField({
			fieldLabel:svcAndGrpAndPkgMsg.charge,
			name:'serviceCharge',
			anchor:'98%',
			required:true,
			allowBlank:false,
			value: svcAndGrpAndPkgMsg.defaultValueForAmount
		});

		this.depositAmountField = new Ext.form.WTCAmountField({
			fieldLabel:svcAndGrpAndPkgMsg.depositAmount,
			name:'depositAmount',
			anchor:'60%',
			allowBlank: false,
			value: svcAndGrpAndPkgMsg.defaultValueForAmount
		});

		this.serviceGroupCombo = new Ext.form.ComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.serviceGroup,
			mode:'local',
			store: loadAddServiceGroupCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'serviceGroup',
			anchor:'90%',
			allowBlank:false,
			emptyText: svcAndGrpAndPkgMsg.selectMsg,
			forceSelection : true,
			required:true
		});

		this.departmentCombo = new Ext.form.ComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.department,
			mode:'local',
			store: loadDepartmentsCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'department',
			anchor:'90%',
			allowBlank:false,
			emptyText: svcAndGrpAndPkgMsg.selectMsg,
			forceSelection : true,
			required:true
		});
	
		this.serviceDescTextArea = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.description,
			name:'serviceDesc',
			height:60,
			anchor:'90%'
		});

		this.procedureHtmlEditor = new Ext.form.HtmlEditor({
			fieldLabel:svcAndGrpAndPkgMsg.procedure,
			name:'serviceProcedure',
			anchor:'90%',
			height:115
		});
		this.saveBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnSave,
			iconCls : 'save_btn',
			tooltip: svcAndGrpAndPkgMsg.saveService,
			scope:this
		});
		
		this.resetBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			tooltip: svcAndGrpAndPkgMsg.resetService,
			scope:this
		});
		
		this.configurePriceBtn = new Ext.Button({
			iconCls : 'editevent-icon',
			tooltip: svcAndGrpAndPkgMsg.configurePriceDetail,
			scope:this,
			hidden:true
		});
		
	},
	getServiceCodeTxf: function(){
		return this.serviceCodeTxf;
	},
	getServiceNameTxf: function(){
		return this.serviceNameTxf;
	},
	getEffectiveFromDtFld: function(){
		return this.effectiveFromDtFld;
	},
	getEffectiveToDtFld: function(){
		return this.effectiveToDtFld;
	},
	getStatusCombo: function(){
		return this.statusCombo;
	},
	getSuspendFromDtFld: function(){
		return this.suspendFromDtFld;
	},
	getSuspendToDtFld: function(){
		return this.suspendToDtFld;
	},
	getChargeNbrField: function(){
		return this.chargeNbrField;
	},
	getDepartmentCombo: function(){
		return this.departmentCombo;
	},
	getServiceGroupCombo: function(){
		return this.serviceGroupCombo;
	},
	getDepositAmountField: function(){
		return this.depositAmountField;
	},
	getSaveBtn : function(){
		return this.saveBtn;
	},
	getResetBtn: function(){
		return this.resetBtn;
	},
	getProcedureHtmlEditor: function(){
		return this.procedureHtmlEditor;
	},
	getServiceDescTextArea: function(){
		return this.serviceDescTextArea;
	},
	getServiceTypeCbx: function(){
		return this.serviceTypeCbx;
	},
	getConfigurePriceBtn: function(){
		return this.configurePriceBtn;
	}
});

	