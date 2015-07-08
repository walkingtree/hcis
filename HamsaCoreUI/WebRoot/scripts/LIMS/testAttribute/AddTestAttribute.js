Ext.namespace('LIMS.testAttribute');

// Needed to create separate object for dynamically added panel, as same type of
// object require to create multiple time. (DOM dont allow to use removed object's reference)
LIMS.testAttribute.NumericValPanel = Ext.extend(Ext.Panel,{
 	initComponent : function(){
	this.widgets = new  LIMS.testAttribute.Widgets();
		Ext.apply(this,{
			
			layout : 'column',
			defaults:{ columnWidth:.3},
			items: [
					{
						layout : 'form',
						labelWidth : 50,
						items:[this.widgets.getUnitCombo()]
					},
					
					{
						layout : 'form',
						labelWidth : 70,
						items:[this.widgets.getMinValueNbf()]
					},
					{
						layout : 'form',
						labelWidth : 70,
						items:[this.widgets.getMaxValueNbf()]
					}
				]
			
		});
		
		LIMS.testAttribute.NumericValPanel.superclass.initComponent.apply(this, arguments);
		
	},
	
	getWidgets : function(){
		return this.widgets;
	}
	
});


LIMS.testAttribute.AddTestAttribute = Ext.extend(Ext.form.FormPanel, {
//	layout : 'column',
	labelAlign : 'left',
//	labelWidth:115,
	monitorValid : true,
	width:'80%',
	border:false,
	frame: true,
	initComponent : function(){
	
		this.valueType = "NUMERIC";
		
		this.widgets = new  LIMS.testAttribute.Widgets();
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.valueTypeFieldSet = new Ext.form.FieldSet({
			title : 'Value type',
			collapsible : false,
			labelAlign :'left',
			autoHeight : true,
			border : true,
			width : '80%',
			items : [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : 1
						},
						items:  this.widgets.getValTypRdGrp()
					}
				]
		});
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);
	
		this.buttonsPanel.getResetBtn().on("click", function(){
			
			if( !Ext.isEmpty( this.initialConfig.mode ) && 
					this.initialConfig.mode == limsMsg.editMode ){
				this.loadData(this.config);
				this.loadData(this.config);
			}else{
				this.resetPanel();
//				this.getForm().reset();
				
			}
			
		}, this);
		
		this.widgets.getValTypRdGrp().on("change",function( radioGroup, value ){
			this.valueTypeChanged( value );
		},this);
		
		this.specificPanel = new Ext.Panel( {
			width : '100%',
			labelWidth :100,
			layout : 'form',
			height : 40
			
		});
		
		
		if(!Ext.isEmpty( this.initialConfig )){
			
			if( this.initialConfig.mode == limsMsg.editMode){
			
				this.widgets.getAttributeCodeTxf().disable();
			
			}
		}
		
		this.observationTextField = new Ext.form.TextField({
			name: limsMsg.observationValue,
			fieldLabel : limsMsg.lblPossibleValue,
			anchor:'60%'
		});
		
		this.observationPanel = new Ext.Panel({
			layout : 'form',
			items : this.observationTextField
		});
		
		this.numericValPanel = new LIMS.testAttribute.NumericValPanel();
		
		this.specificPanel.add(this.numericValPanel);
		this.specificPanel.add(this.observationPanel);
		this.observationPanel.hide();
		
		Ext.apply(this, {
	        items: [
	                {
	                	layout:'column',
	                	defaults:{
	                		columnWidth:.5
	                	},
	                	items:[{
							layout : 'form',
							labelWidth : 110,
							items:[this.widgets.getAttributeCodeTxf()]
						},
						
						{
							layout : 'form',
							items:[this.widgets.getAttributeNameTxf()]
						}]
	                },
	                {
			        	layout : 'form',
			        	width:'80%',
			        	items:[this.valueTypeFieldSet]
		        	},
		        	
		        	this.specificPanel,
		        	
		        	this.buttonsPanel
					
				]            
	    });
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
			}
		}, this); 
		
		
		LIMS.testAttribute.AddTestAttribute.superclass.initComponent.apply(this, arguments);
	},

	saveBtnClicked : function(){
		var mainThis =this;
		var inputValues = this.getForm().getValues();
		
		inputValues.type = this.valueType;
		
		var labTestAttributeBM = {

				attributeCode : inputValues[limsMsg.attributeCode] || this.widgets.getAttributeCodeTxf().getValue(),
				attributeName : inputValues[limsMsg.attributeName],
				unit : { code : inputValues[limsMsg.attrUnit]},
				type : { code :inputValues[limsMsg.attrType] },
				minValue : inputValues[limsMsg.minValue] || null,
				maxValue : inputValues[limsMsg.maxValue]|| null,
				observationValue : inputValues [limsMsg.observationValue]|| null ,
				createdBy :getAuthorizedUserInfo().userName
			};
		
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && 
				this.initialConfig.mode == schldVaccineMsg.editMode ){
					
				LabConfigManager.modifyTestAttribute( 
						labTestAttributeBM,{
					callback: function(){
						
						if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
							layout.getCenterRegionTabPanel().remove( mainThis, true );
							Ext.ux.event.Broadcast.publish('closeEditTestAttributeWindow');
						}
						loadTestAttributeStore().reload();
					},
					callbackScope:this
				});
			}
			else{
				LabConfigManager.addTestAttribute(labTestAttributeBM,{
					callback: function(){
//					this.resetPanel();
//					this.getForm().reset();
					loadTestAttributeStore().reload();
						if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
							layout.getCenterRegionTabPanel().remove( mainThis, true );
							Ext.ux.event.Broadcast.publish('closeAddTestAttributeWindow');
						}
						else{
							this.resetPanel();
						}
					},
					callbackScope:this
				});
			}
		
		
	},
	
	loadData : function( config ){
		this.config = config;
		
		if( !Ext.isEmpty( config) ){

			this.valueTypeChanged( config.typeCode); //The behavior is similar, so reuse it 
			
			this.getForm().setValues(config);
		}
		
		
	},
	
	valueTypeChanged : function( value ){
		

		if( value == limsMsg.ATTR_TYPE_NUMERIC){
			
//			this.specificPanel.removeAll();	
			this.observationTextField.setValue("");
			this.observationPanel.hide();
			this.numericValPanel.show();
			
			this.valueType = limsMsg.ATTR_TYPE_NUMERIC;
		
//			this.specificPanel.add( new LIMS.testAttribute.NumericValPanel() );
//			this.specificPanel.doLayout();
		
		}else if( value == limsMsg.ATTR_TYPE_TEXT ){

//			this.specificPanel.removeAll();
			this.numericValPanel.hide();
			this.observationPanel.hide();
			this.valueType = limsMsg.ATTR_TYPE_TEXT;
			//Nothing to add
		
		}else if( value == limsMsg.ATTR_TYPE_OBSERVATION ){

//			this.specificPanel.removeAll();
			this.numericValPanel.getWidgets().getUnitCombo().setValue("");
			this.numericValPanel.getWidgets().getMinValueNbf().setValue("");
			this.numericValPanel.getWidgets().getMaxValueNbf().setValue("");

			this.numericValPanel.hide();
			this.observationPanel.show();
			this.valueType = limsMsg.ATTR_TYPE_OBSERVATION;
			
//			this.specificPanel.add()
//			);
			
//			this.specificPanel.doLayout();
			
		}
		
		
	},
	
	resetPanel : function(){
		this.widgets.getAttributeCodeTxf().setValue("");
		this.widgets.getAttributeNameTxf().setValue("");
		this.widgets.getValTypRdGrp().setValue( limsMsg.ATTR_TYPE_NUMERIC, true);
		this.widgets.getValTypRdGrp().fireEvent('change', null ,limsMsg.ATTR_TYPE_NUMERIC);

	}
}
);

