
Ext.namespace('LIMS.technique');

LIMS.technique.AddTechnique = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	labelWidth:120,
	frame:		false,
	monitorValid : true,
 	initComponent : function(){
	
		this.widgets = new LIMS.technique.Widgets();
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);
	
		this.buttonsPanel.getResetBtn().on("click", function(){
			
			if( !Ext.isEmpty( this.initialConfig.mode ) && 
					this.initialConfig.mode == limsMsg.editMode ){
				this.loadData(this.config);
			}else{
				this.getForm().reset();
			}
			
		}, this);
		
		this.widgets.getTechReagentCombo().required=true;
		this.widgets.getTechReagentCombo().allowBlank=false;
		
		Ext.apply(this, {
			width:'60%',
			layout : 'column',
			frame: true,
	        items: [
				{
					columnWidth:0.5,
		        	layout : 'form',
					items:[this.widgets.getTechReagentCombo()]
				},{
					columnWidth:0.5,
	            	layout : 'form',
	            	labelWidth:60,
					items:[this.widgets.getTechniqueNameTxf()]
				}
	          ,{
					columnWidth:1,
	            	layout : 'form',
					items:[this.widgets.getTechniqueDescTxf()]
			    }
	            ,{
	            	columnWidth:1,
	            	layout : 'form',
	            	items:[this.buttonsPanel]
	            	
	            }
			]            
	    });
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
			}
		}, this); 
		
		
		LIMS.technique.AddTechnique.superclass.initComponent.apply(this, arguments);
	},

	saveBtnClicked : function(){
		var mainThis = this;
		var inputValues = this.getForm().getValues();
//		var tableData = this.table.getData();
		
		
		var techniequeReagentBM = {

						name : this.widgets.getTechniqueNameTxf().getValue(),
						techniequeReagentId : this.widgets.getTechniqueCodeTxf().getValue(),
						description : this.widgets.getTechniqueDescTxf().getValue(),
						isTechnique : this.widgets.getTechReagentCombo().getValue(),
						createdBy : getAuthorizedUserInfo().userName

					};
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && 
				this.initialConfig.mode == schldVaccineMsg.editMode ){
					
				techniequeReagentBM.techniequeReagentId = this.initialConfig.techniqueId;
				
				LabConfigManager.modifyTechniqueReagent( 
						techniequeReagentBM,{
					callback: function(){
						
						if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
							layout.getCenterRegionTabPanel().remove( mainThis , true );
							Ext.ux.event.Broadcast.publish('closeEditTechniqueWindow');
						}
						
						loadTechniqueReagentStore().reload();
						loadReagentStore().reload();
						loadTechniqueStore().reload();
					},
					callbackScope:this
				});
			}
			else{
				LabConfigManager.addTechniqueReagent(techniequeReagentBM,{
					callback: function(){
					this.getForm().reset();
						loadTechniqueReagentStore().reload();
						if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
							layout.getCenterRegionTabPanel().remove( mainThis , true );
							Ext.ux.event.Broadcast.publish('closeAddTechniqueWindow');
						}

						loadTechniqueReagentStore().reload();
						loadReagentStore().reload();
						loadTechniqueStore().reload();
					},
					callbackScope:this
				});
			}
		
		
	},
	
	loadData : function( config ){
		this.config = config;
		this.getForm().setValues(config);
	}
}
);

