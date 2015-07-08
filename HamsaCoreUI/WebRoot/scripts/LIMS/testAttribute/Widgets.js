Ext.namespace("LIMS.testAttribute");

LIMS.testAttribute.Widgets = Ext.extend(Object,{
	constructor: function(){
		
	
		this.attributeCodeTxf = new Ext.form.TextField({
			name: limsMsg.attributeCode,
			fieldLabel : limsMsg.lblAttributeCode,
			anchor:'98%',
			required:true,
			allowBlank:false
		});
		
		this.attributeNameTxf = new Ext.form.TextField({
			name: limsMsg.attributeName,
			fieldLabel : limsMsg.lblAttributeName,
			anchor:'98%',
			required:true,
			allowBlank:false
		});
		
		
      this.unitCombo = new wtccomponents.WTCComboBox( {
			store : loadTestMeasurementUnitStore(),
			hiddenName : limsMsg.attrUnit,
			fieldLabel : limsMsg.lblUnit
	      });
	      
		this.possibleValuesTxf = new Ext.form.TextField({
			name: limsMsg.observationValue,
			fieldLabel : limsMsg.lblPossibleValue,
			anchor:'98%'
		});
		
		this.minValueNbf = new Ext.form.NumberField( {
			name : limsMsg.minValue,
			fieldLabel : limsMsg.lblMinValue,
			anchor : '98%'
		});
		
		this.maxValueNbf = new Ext.form.NumberField( {
			name :limsMsg.maxValue,
			fieldLabel : limsMsg.lblMaxVal,
			anchor : '98%'
		});
		
		this.valTypRdGrp = new Ext.form.RadioGroup({
						name:'rdGptypeCode',
						columns: [100, 100, 100],
			       		items: [
			                    {boxLabel:limsMsg.lblNumeric, name: 'typeCode', inputValue: limsMsg.ATTR_TYPE_NUMERIC,checked: true},
			                    {boxLabel:limsMsg.lblText, name: 'typeCode', inputValue: limsMsg.ATTR_TYPE_TEXT},
			                    {boxLabel:limsMsg.lblObservation, name: 'typeCode', inputValue: limsMsg.ATTR_TYPE_OBSERVATION}
			       			   ]
			    		});
		
		this.testCombo = new wtccomponents.WTCComboBox( {
			store : loadLabTestStore(),
			hiddenName : 'labTest',
			fieldLabel : limsMsg.lblTestName
	      });
		
		this.attributeTypeCombo = new wtccomponents.WTCComboBox( {
			store : loadTestAttributeTypeStore(),
			hiddenName : limsMsg.attrType,
			fieldLabel : limsMsg.lblAttributeType
	      });
		
		
	},
	
	
	
	getAttributeNameTxf : function(){
		return this.attributeNameTxf;
	},
	
	getAttributeCodeTxf : function(){
		return this.attributeCodeTxf;
	},
	
	getPossibleValuesTxf : function(){
		return this.possibleValuesTxf;
	},

	getUnitCombo : function(){
		return this.unitCombo;
	},
	
	getMinValueNbf : function(){
		return this.minValueNbf;
	},
	
	getMaxValueNbf : function(){
		return this.maxValueNbf;
	},
	
	getValTypRdGrp : function(){
		return this.valTypRdGrp;
	},
	
	getTestCombo : function(){
		return this.testCombo;
	},
	
	getAttributeTypeCombo: function(){
		return this.attributeTypeCombo;
	}
	
});
