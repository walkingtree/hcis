Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.LabDetailPanel = Ext.extend(Ext.form.FieldSet, {
	title : "Lab information",
	border: true,
	layout : 'form',
	frame:false,
	anchor:'100%',
	labelAlign : 'left',
	height : 270,
	width:'94%',
	
	initComponent : function() {

		this.widgets = new LIMS.labConfiguration.Widgets();
		Ext.applyIf(this, {
            items: [
                    
            	{
            		columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getLabTypeCbx()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getLabIdTxf()]
			    },{
			    	columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getLabNameTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:this.widgets.getBusinessNameTxf()
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getBranchNameTxf()]
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getLabOperatorIDTxf()]
					
				},{
					columnWidth:0.20,
	            	layout : 'form',
					items:[this.widgets.getDirectionFromKnownPlaceTxf()]
				}
				
			]            
        });
       LIMS.labConfiguration.LabDetailPanel.superclass.initComponent.apply(this, arguments);
	},
	getWidgets : function(){
		return this.widgets;
	},
	getLabDetails: function(){
		var details = {
		
			labType:this.widgets.getLabTypeCbx().getRawValue(),
			labId:this.widgets.getLabIdTxf().getValue(),
			labName: this.widgets.getLabNameTxf().getValue(),
			businessName:this.widgets.getBusinessNameTxf().getValue(),
			branchName:this.widgets.getBranchNameTxf().getValue(),
			directionFromKnownPlace:this.widgets.getDirectionFromKnownPlaceTxf().getValue(),
			labOperatorID:this.widgets.getLabOperatorIDTxf().getValue()
			
			
		}
		return details;
	},
	resetData: function(){
		
		this.widgets.getLabTypeCbx().reset(),
		this.widgets.getLabIdTxf().reset();
		this.widgets.getLabNameTxf().reset();
		this.widgets.getBusinessNameTxf().reset();
		this.widgets.getBranchNameTxf().reset();
		this.widgets.getDirectionFromKnownPlaceTxf().reset(),
		this.widgets.getLabOperatorIDTxf().reset();
	},
	setData: function( config ){
		if( !Ext.isEmpty( config )){
			this.widgets.getHospitalNameCbx().setValue( config.hospitalName );
			this.widgets.getLabTypeCbx().setValue(config.laboratoryType);
			this.widgets.getLabIdTxf().setValue(config.laboratoryId);
			this.widgets.getLabNameTxf().setValue(config.laboratoryName);
			this.widgets.getBusinessNameTxf().setValue(config.businessName);
			this.widgets.getBranchNameTxf().setValue(config.branchName);
			this.widgets.getDirectionFromKnownPlaceTxf().setValue(config.getDirectionFromKnownPlace);
			this.widgets.getLabOperatorIDTxf().setValue(config.labOperatorID);
		
		}
		
	},
	disableLabIdTxf : function(){
		this.widgets.getLabIdTxf().disable();
	}
	
});

//Ext.reg('Lab-detail-panel',LIMS.LabConfiguration.LabDetailPanel);