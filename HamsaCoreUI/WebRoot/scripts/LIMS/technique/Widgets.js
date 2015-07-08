Ext.namespace("LIMS.technique");

LIMS.technique.Widgets = Ext.extend(Object,{
	constructor: function(){
		
//		this.techniqueRadioGroup = new Ext.form.RadioGroup({
//			name: limsMsg.technique,
//			fieldLabel : limsMsg.lblTechnique,
//			anchor:'98%',
//			required:true,
//			allowBlank: false
//		});
//		
		
		this.techniqueCodeTxf = new Ext.form.TextField({
			name: limsMsg.techniqueCode,
			fieldLabel : limsMsg.lblTechniqueCode,
			anchor:'98%'
		});
		
		this.techniqueNameTxf = new Ext.form.TextField({
			name: limsMsg.techniqueName,
			fieldLabel : limsMsg.lblTechniqueName,
			anchor:'98%',
			required:true,
			allowBlank:false
		});
		
		this.techniqueDescTxf = new Ext.form.TextArea({
			name: limsMsg.techniqueDesc,
			fieldLabel : limsMsg.lblTechniqueDesc,
			anchor:'98%'
		});
		
		this.techniqueNameSearchTxf = new Ext.form.TextField({
			name: limsMsg.techniqueName,
			fieldLabel : limsMsg.lblTechniqueName,
			anchor:'98%'
		});
		
		
	      this.labTestCombo = new wtccomponents.WTCComboBox({
			store:loadLabTestStore(),
			hiddenName:'labTest',
			fieldLabel : limsMsg.lblTestName
		});
	      
	      this.techReagentCombo = new wtccomponents.WTCComboBox( {
			store : new Ext.data.SimpleStore(
					{
						fields : [ 'code', 'description' ],
						data : [ [ 'Y', limsMsg.lblTechnique ],
						         [ 'N', limsMsg.lblReagent ] ]
					}),
			hiddenName : 'isTechnique',
			fieldLabel : limsMsg.lblTechReagent,
			anchor:'99%'
	      });
	},
	
	
	getTechniqueNameTxf : function(){
		return this.techniqueNameTxf;
	},
	
	getTechniqueCodeTxf : function(){
		return this.techniqueCodeTxf;
	},
	
	getTechniqueNameSearchTxf : function(){
		return this.techniqueNameSearchTxf;
	},
	
	getTechniqueDescTxf : function(){
		return this.techniqueDescTxf;
	},
	
	getLabTestCombo : function(){
		return this.labTestCombo;
	},
	
	getTechReagentCombo : function(){
		return this.techReagentCombo;
	}
	
	
});
