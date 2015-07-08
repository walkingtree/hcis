Ext.namespace("OPD.serviceAssignement");

OPD.serviceAssignement.SelectionPanelWidgets = Ext.extend(Object,{
	constructor: function( config ){
		
		if( Ext.isEmpty( config ) ){
			config.enteredUnitsLbl =mngSvcAsgntMsg.defaultUnitsLbl;
			
		}
		
		this.requestedUnitsLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.requestedUnits + ':',
			cls:'label-font'
		});
		
		this.requestedUnitsValLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.defaultUnits,
			cls:'label-font'
		});
		
		this.alreadyRenderUnitsLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.alreadyRenderUnits+':',
			cls:'label-font'
		});
		
		this.alreadyRenderUnitsValLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.defaultUnits,
			cls:'label-font'
		});
		
		this.alreadyCanceledUnitsLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.alreadyCalceledUnits+':',
			cls:'label-font'
		});
		
		this.alreadyCanceledUnitsValLbl = new Ext.form.Label ({
			text: mngSvcAsgntMsg.defaultUnits,
			cls:'label-font'
		});
		
		this.entredUnits = new Ext.form.NumberField({
			value: mngSvcAsgntMsg.defaultUnits,
			fieldLabel : config.enteredUnitsLbl,
			anchor:'50%',
			name: mngSvcAsgntMsg.fromenteredUnits,
			allowBlank: false,
			required: true
		}); 
		
		this.okBtn = new Ext.Button({
			iconCls : 'accept-icon',
			text : mngSvcAsgntMsg.btnOk,
			scope: this
		});
		
		this.cancelBtn = new Ext.Button({
			iconCls : 'cross_icon',
			text : mngSvcAsgntMsg.btnCancel,
			scope: this
		});
		
	},
	getRequestedUnitsLbl: function(){
		return this.requestedUnitsLbl;
	},
	getRequestedUnitsValLbl: function(){
		return this.requestedUnitsValLbl;
	},
	getEntredUnits: function(){
		return this.entredUnits;
	},
	getAlreadyRenderUnitsLbl: function(){
		return this.alreadyRenderUnitsLbl;
	},
	getAlreadyRenderUnitsValLbl: function(){
		return this.alreadyRenderUnitsValLbl;
	},
	getAlreadyCanceledUnitsLbl: function(){
		return this.alreadyCanceledUnitsLbl;
	},
	getAlreadyCanceledUnitsValLbl: function(){
		return this.alreadyCanceledUnitsValLbl;
	},
	getOkBtn: function(){
		return this.okBtn;
	},
	getCancelBtn: function(){
		return this.cancelBtn;
	}
});