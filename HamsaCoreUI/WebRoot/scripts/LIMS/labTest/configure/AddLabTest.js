Ext.namespace("LIMS.labTest.configure");

LIMS.labTest.configure.AddLabTest = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
//	frame:true,
	style : 'padding-top:10px',
	monitorValid : true,
	labelWidth : 150,
 	initComponent : function() {

		this.widgets = new LIMS.labTest.Widgets();
//
		this.attributeAssoPanel = new LIMS.labTest.configure.TestAttributeAssoPanel({mode:this.initialConfig.mode});
		this.sampleAssoPanel = new  LIMS.labTest.configure.TestSampleAssoPanel();
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.length = this.buttonsPanel.buttons.length;
		this.buttonsPanel.buttons[this.length] =  this.widgets.getAddTemplateBtn();

		
		this.techOrReagentCombo = this.widgets.getTechReagentCombo();
		
		this.panel = new Ext.Panel({layout:'form'});
		
		// By default load complete tech/reagent store as at edit mode value
		// needs to set and BM does not have that information
//		this.techReagent = this.widgets.getTechniqueReagentCbx();
//		this.techReagent.fieldLabel = "Tech/Reagent";
		
		this.techReagent = this.widgets.getTechniqueCombo(true);
		this.panel.add( this.techReagent );
		
		this.testDetailPanel = new Ext.Panel({
			layout : 'column',
			items:[
		        {
					columnWidth:0.5,
					layout : 'form',
					labelWidth:150,
					items:[this.techOrReagentCombo]
				},
		
		        {
					columnWidth:0.5,
		        	layout : 'form',
		        	items:[this.panel]
			    },{
					columnWidth:0.5,
		        	layout : 'form',
		        	labelWidth:150,
					items:[this.widgets.getTestApplicableGenderCombo()]
				},{
					columnWidth:0.5,
		        	layout : 'form',
		        	items:[this.widgets.getTestLabNameCombo()]
			    },{
					columnWidth:0.5,
		        	layout : 'form',
		        	labelWidth:150,
					items:[this.widgets.getTimeRequiresTxf()]
			    },{
					columnWidth:0.5,
		        	layout : 'form',
					items:[this.widgets.getTestPreRequisiteTxa()]
				},{
					columnWidth:0.5,
		        	layout : 'form',
		        	labelWidth:105,
					items:[this.widgets.getReviewRequiresChk()]
			    },{
					columnWidth:0.5,
		        	layout : 'form',
					items:[this.widgets.getDefaultReviewerTxf()]
			    }
		    ]
	    });
	
		
		
		
		this.tabPanel = new Ext.TabPanel({
							activeTab : 0,
							width : '98%',
							border : false,
//							style : 'padding-top:10px',
							height : 250,
							layoutOnTabChange : true,
							items : [
						         {
						        	 frame: true,
//						        	 height:250,
						        	 title:limsMsg.ttltestDetail,
						        	 items:this.testDetailPanel
								 }, 
						         {
						        	 frame: true,
//						        	 height:250,
						        	 title:limsMsg.ttlAttributes,
						        	 items:this.attributeAssoPanel
						         },
						         {
						        	 frame: true,
//						        	 height:250,
						        	 title:limsMsg.ttlSamples,
						        	 items:this.sampleAssoPanel
						         }]
						});

		
		Ext.applyIf(this, {
            items: [
                {
					columnWidth:1,
	            	layout : 'form',
					items:[this.tabPanel ]
			    }
			   
			]            
        });
		
		
		this.techOrReagentCombo.on('select',function(comp, record, index) {
			this.setTechReagentPanel( record.data.code );
			
//			this.panel.removeAll();
//			
//			if( record.data.code == "Y"){
//				this.techReagent = this.widgets.getTechniqueCombo(true);
//				
//				this.panel.add(this.techReagent);
//				
//			}else{
//				
//				this.techReagent = this.widgets.getReagentCombo(true);
//				
//				this.panel.add(this.techReagent);
//				
//			}
//			
//			this.panel.doLayout();
		},this);
       
		
		this.widgets.getReviewRequiresChk().on('check',function(checkBox,checked){
													
													if( checked){
														
														this.widgets.getDefaultReviewerTxf().enable();
													}else{
														//unchecked
														this.widgets.getDefaultReviewerTxf().setValue("");
														this.widgets.getDefaultReviewerTxf().disable();
													}
												},
												this);
		
        LIMS.labTest.configure.AddLabTest.superclass.initComponent.apply(this, arguments);

	},
	
	
	loadData : function( config ){
		this.config = config;
		
		
		if( !Ext.isEmpty( this.config.isTechnique )){
			this.techOrReagentCombo.setValue( this.config.isTechnique );
			this.setTechReagentPanel( this.config.isTechnique );
		}
		this.techReagent.setValue(this.config.techniqueReagent.code);
//		this.reagentCombo.setValue(this.config.techniqueReagent.code);
		
		
//		this.widgets.getTestTypeCombo().setValue(this.config.testType.code),
		this.widgets.getTestApplicableGenderCombo().setValue(this.config.availableForGender.code),
		this.widgets.getTestLabNameCombo().setValue(this.config.lab.code),
		this.widgets.getTestPreRequisiteTxa().setValue(this.config.preRequisite),
		this.widgets.getTimeRequiresTxf().setValue(this.config.minTimeRequired),
		this.widgets.getReviewRequiresChk().setValue(this.config.reviewRequired =="Y"?true:false),
		this.widgets.getDefaultReviewerTxf().setValue(this.config.defaultReviewerId);
		this.attributeAssoPanel.table.loadGridData(this.config.labTestAttributeAssocBMList); 
		this.sampleAssoPanel.table.loadGridData(this.config.labTestSpecimenAssocBMList);
		
	},


	getLabTestBM : function(){
	
		var attributeAssocList = this.attributeAssoPanel.table.getData(); 
		var specimenAssocList = this.sampleAssoPanel.table.getData();
		var labTestBM = {
				testType:{code : this.widgets.getTestTypeCombo().getValue() },
				availableForGender: {code :this.widgets.getTestApplicableGenderCombo().getValue()},
				techniqueReagent:{code : this.techReagent.getValue()},
				lab:		{code : this.widgets.getTestLabNameCombo().getValue()},
				preRequisite	:this.widgets.getTestPreRequisiteTxa().getValue(),
				minTimeRequired	:this.widgets.getTimeRequiresTxf().getValue(),
				reviewRequired	:this.widgets.getReviewRequiresChk().getValue()?"Y":"N",
				defaultReviewerId :this.widgets.getDefaultReviewerTxf().getValue(),
				createdBy: getAuthorizedUserInfo().userName, 
				labTestSpecimenAssocBMList: specimenAssocList,
				labTestAttributeAssocBMList:attributeAssocList,
				isAttributeDeleted: this.attributeAssoPanel.isAttributeDeleted || null
		};
		
		return labTestBM;
	},
	
	setTechReagentPanel : function( isTechnique ){
		this.panel.removeAll();
		
		if( isTechnique === "Y"){
			this.techReagent = this.widgets.getTechniqueCombo(true);
			
			this.panel.add(this.techReagent);
			
		}else{
			
			this.techReagent = this.widgets.getReagentCombo(true);
			
			this.panel.add(this.techReagent);
			
		}
		
		this.panel.doLayout();

	}
	
});