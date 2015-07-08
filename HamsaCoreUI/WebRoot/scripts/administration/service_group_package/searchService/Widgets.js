Ext.namespace('administration.service_group_package.searchService');

administration.service_group_package.searchService.Widgets = Ext.extend(Object, {
	constructor: function(config){
		Ext.QuickTips.init();
		
		Ext.apply(this, config);	
		
		this.serviceCodeTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.code,
			name:'serviceCode',
			anchor:'90%'
		});
		
		this.serviceNameTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.name,
			name:'serviceName',
			anchor:'90%'
		});
				
		this.serviceDescTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.description,
			name:'serviceDesc',
			anchor:'90%'
		});

		this.serviceGroupCombo = new Ext.form.OptComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.serviceGroup,
			mode:'local',
			store: loadAddServiceGroupCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'serviceGroup',
			anchor:'90%',
			emptyText: svcAndGrpAndPkgMsg.selectMsg,
			forceSelection : true
		});

		this.departmentCombo = new Ext.form.OptComboBox({
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
			forceSelection : true
		});

		this.statusCombo = new Ext.form.OptComboBox({
			fieldLabel:svcAndGrpAndPkgMsg.status,
			mode:'local',
			store: loadServiceStatusCbx(),
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			hiddenName:'status',
			anchor:'80%'
		});		
		
		this.createdAfterDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.createdAfter,
			name:'createdAfter',
			anchor:'90%'
		});
		
		this.createdBeforeDtFld = new Ext.form.WTCDateField({
			fieldLabel:svcAndGrpAndPkgMsg.createdBefore,
			name:'createdBefore',
			anchor:'90%'
		});
		
		this.procedureTxf = new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.procedure,
			name:'serviceProcedure',
			anchor:'90%'
		});

		this.chargesFromNbrField = new Ext.form.NumberField({
			fieldLabel:svcAndGrpAndPkgMsg.chargesFrom,
			name:'chargesFrom',
			anchor:'90%'
		});

		this.chargesToNbrField = new Ext.form.NumberField({
			fieldLabel:svcAndGrpAndPkgMsg.chargesTo,
			name:'chargesTo',
			anchor:'90%'
		});

		this.searchBtn = new Ext.Button({
	        text: svcAndGrpAndPkgMsg.btnSearch,
	    	iconCls : 'search_btn',
	    	tooltip: svcAndGrpAndPkgMsg.searchService,
	    	scope: this
	    });
	    
	    this.resetBtn = new Ext.Button({
			text:svcAndGrpAndPkgMsg.btnReset,
			iconCls : 'cancel_btn',
			scope:this,
			tooltip: svcAndGrpAndPkgMsg.resetSearchCreteria

		});
	    
	    this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			tooltip: svcAndGrpAndPkgMsg.addService,
			text : svcAndGrpAndPkgMsg.btnAdd,
			scope:this
		});
		
		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			tooltip: svcAndGrpAndPkgMsg.editService,
			text : svcAndGrpAndPkgMsg.btnEdit,
			scope:this,
			disabled:true
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			tooltip: svcAndGrpAndPkgMsg.deleteService,
			text : svcAndGrpAndPkgMsg.btnDelete,
			scope:this,
			disabled:true
		});
		
		this.editTemplateBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			tooltip: svcAndGrpAndPkgMsg.editTemplate,
			text : svcAndGrpAndPkgMsg.editTemplate,
			scope:this,
			disabled : true
		});

		
	},
	getSearchBtn: function(){
		return this.searchBtn ;
	},
	getResetBtn: function(){
		return this.resetBtn;
	},
	getChargesToNbrField: function(){
		return this.chargesToNbrField;
	},
	getChargesFromNbrField : function(){
		return this.chargesFromNbrField ;
	},
	getProcedureTxf : function(){
		return this.procedureTxf ;
	},
	getCreatedBeforeDtFld : function(){
		return this.createdBeforeDtFld ;
	},
	getCreatedAfterDtFld : function(){
		return this.createdAfterDtFld ;
	},
	getStatusCombo : function(){
		return this.statusCombo ;
	},
	getDepartmentCombo : function(){
		return this.departmentCombo ;
	},
	getServiceGroupCombo : function(){
		return this.serviceGroupCombo ;
	},
	getServiceDescTxf : function(){
		return this.serviceDescTxf ;
	},
	getServiceNameTxf : function(){
		return this.serviceNameTxf ;
	},
	getServiceCodeTxf : function(){
		return this.serviceCodeTxf ;
	},
	getAddBtn: function(){
		return this.addBtn ;
	},
	getEditBtn: function(){
		return this.editBtn ;
	},
	getDeleteBtn: function(){
		return this.deleteBtn ;
	},
	getEditTemplateBtn : function(){
		return this.editTemplateBtn;
	}
});
