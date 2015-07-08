Ext.namespace("LIMS.labTest");

LIMS.labTest.Widgets = Ext.extend(Object,{
	constructor: function(){
		
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
			
		  	store : loadLabTestStore(),
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
			anchor	   :'90%',
			hiddenName : 'isTechnique',
			fieldLabel : limsMsg.lblTechReagent
	      });
	      
	      
	      this.specimenCombo = new wtccomponents.WTCComboBox( {
				store : loadTestSpecimenStore(),
				hiddenName : 'specimenCombo',
				fieldLabel : limsMsg.lblSpecimen
		      });
	      
	      this.specimenQttyTxf = new Ext.form.NumberField({
				name: 'specimenQtty',
				fieldLabel : limsMsg.lblSpecimenQtty,
				anchor:'98%'
			});
	      

	      this.specimenUnitCombo = new wtccomponents.WTCComboBox( {
				store : loadTestMeasurementUnitStore(),
				hiddenName : 'specimenUnitCombo',
				fieldLabel : limsMsg.lblUnit
		      });
	      
	      this.isMandatoryChk = new Ext.form.Checkbox({
				boxLabel: limsMsg.lblIsMendatory,
				labelSeperator :'',
				name:'isMandatoryChk',
				checked:true
			});
	      
	      this.testAttributeTxf = new wtccomponents.WTCComboBox( {
				store : loadTestAttributeStore(),
				hiddenName : 'testAttribute',
				fieldLabel : limsMsg.lblAttributeName
		      });
	      
	      this.addBtn = new Ext.Toolbar.Button({
				iconCls : 'add_btn',
				text : limsMsg.btnAdd,
				scope: this
			});

	      this.testTypeCombo = new wtccomponents.WTCComboBox( {
				store : loadTestSpecimenStore(),
				hiddenName : 'testTypeCombo',
				fieldLabel : limsMsg.lblTestType
		      });
	     
	      //Gender for which test is available
	      this.testApplicableGenderCombo = new wtccomponents.WTCComboBox( {
				store : loadTestAvailableForGenderStore(),
				hiddenName : 'testApplicableGenderCombo',
				fieldLabel : limsMsg.lblTestApplicableFor,
				anchor : "90%",
				required:true,
				allowBlank:false
		      });
	      
	      this.testLabNameCombo = new wtccomponents.WTCComboBox( {
				store : loadLabStore(),
				hiddenName : 'testLabNameCombo',
				fieldLabel : limsMsg.lblLabName,
				anchor : '90%',
				required:true,
				allowBlank:false
		      });
	      
	      
	      this.testPreRequisiteTxa = new Ext.form.TextArea({
				name: 'testPreRequisite',
				fieldLabel : limsMsg.lblPreRequisites,
				anchor:'90%'
			});
	      
	      this.reviewRequiresChk = new Ext.form.Checkbox({
//				boxLabel: limsMsg.lblReviewRequires,
	    	  	fieldLabel: limsMsg.lblReviewRequires,
				labelSeperator :'',
				name:'reviewRequiresChk',
				checked:true
			});
	      
	      this.defaultReviewerTxf = new Ext.form.TextField({
				name: limsMsg.defaultReviewerId,
				fieldLabel : limsMsg.lblDefaultReviewer,
				anchor:'90%'
			});
	      
	      this.timeRequiresTxf = new Ext.form.TextField({
				name: 'timeRequiresTxf',
				fieldLabel : limsMsg.lblTimeRequires,
				anchor:'90%'
			});
	      
	      this.addTemplateBtn = new Ext.Toolbar.Button({
				iconCls : 'add_btn',
				text : limsMsg.btnAddTemplate,
				disabled : true,
				scope: this
			});
	      
	      this.attributeMinValueTxf = new Ext.form.TextField( {
				name : 'attributeMinValue',
				anchor : '98%'
			});

	      this.attributeMaxValueTxf = new Ext.form.TextField( {
				name : 'attributeMaxValue',
				anchor : '98%'
			});
	      
	      this.testAttributeCbx = new wtccomponents.WTCComboBox( {
				store : loadTestAttributeStore(),
				hiddenName : 'testAttributeCombo',
				fieldLabel : limsMsg.lblAttributeName
		      });
	      
	      this.measurementUnitCombo = new wtccomponents.WTCComboBox( {
				store : loadTestMeasurementUnitStore(),
				hiddenName : 'testLabNameCombo',
				fieldLabel : limsMsg.lblLabName,
				required:true,
				allowBlank:false
		      });
	      
	      this.techniqueReagentCbx = new  wtccomponents.WTCComboBox( {
				store : loadTechniqueReagentStore(),
				hiddenName : 'techniqueReagent',
				fieldLabel : limsMsg.lblTechReagent,
				anchor : '90%',
				required:true,
				allowBlank:false
		      });
			
	
			
			this.reagentCombo = new wtccomponents.WTCComboBox({
				hiddenName : 'techniqueReagent',
				fieldLabel : "Reagent",
				mode : 'local',
				store : loadReagentStore(),
				anchor : '90%',
				required:true,
				allowBlank:false
			});
			
			this.techniqueCombo = new wtccomponents.WTCComboBox({
				hiddenName : 'techniqueReagent',
				fieldLabel : "Technique",
				mode : 'local',
				store : loadTechniqueStore(),
				anchor : '90%',
				required:true,
				allowBlank:false
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
		
		//set its first value by default
		this.techReagentCombo.setValue(
				this.techReagentCombo.getStore().data.items[0].data.code);
		
		return this.techReagentCombo;
	},
	
	getSpecimenUnitCombo : function(){
		return this.specimenUnitCombo;
	},
	
	getSpecimenQttyTxf : function(){
		return this.specimenQttyTxf;
	},
	
	getSpecimenCombo : function(){
		return this.specimenCombo;
	},
	
	getIsMandatoryChk : function(){
		return this.isMandatoryChk;
	},
	
	getTestAttributeTxf : function(){
		return this.testAttributeTxf;
	},
	getAddBtn : function(){
		return this.addBtn;
	},
	
	getTestTypeCombo : function(){
		return this.testTypeCombo;
	},
	
	getTestApplicableGenderCombo : function(){
		return this.testApplicableGenderCombo;
	},
	
	getTestLabNameCombo : function(){
		return this.testLabNameCombo;
	},
	
	getTestPreRequisiteTxa : function(){
		return this.testPreRequisiteTxa;
	},
	
	getReviewRequiresChk : function(){
		return this.reviewRequiresChk;
	},
	
	getTimeRequiresTxf : function(){
		return this.timeRequiresTxf;
	},
	getDefaultReviewerTxf : function() {
		return this.defaultReviewerTxf;
	},
	getAddTemplateBtn : function(){
		return this.addTemplateBtn;
	},
	getAttributeMaxValueTxf : function(){
		return this.attributeMaxValueTxf;
	},
	getAttributeMinValueTxf : function(){
		return this.attributeMinValueTxf;
	},
	
	getTestAttributeCbx : function(){
		return this.testAttributeCbx;
	},
	
	getMeasurementUnitCombo : function(){
		return this.measurementUnitCombo;
	},
	
	getTechniqueReagentCbx : function(){
		return this.techniqueReagentCbx;
	},
	
		
	getReagentCombo : function(getNew){
		
		if( getNew){
			return new wtccomponents.WTCComboBox({
						hiddenName : 'techniqueReagent',
						fieldLabel : "Reagent",
						mode : 'local',
						store : loadReagentStore(),
						anchor : '90%',
						required:true,
						allowBlank:false
					});
		}
		return this.reagentCombo;
	},
	
	getTechniqueCombo : function(getNew){
		
		if( getNew){
			return new wtccomponents.WTCComboBox({
						hiddenName : 'techniqueReagent',
						fieldLabel : "Technique",
						mode : 'local',
						store : loadTechniqueStore(),
						anchor : '90%',
						required:true,
						allowBlank:false
					});
		}
		return this.techniqueCombo;
	}
	
});

