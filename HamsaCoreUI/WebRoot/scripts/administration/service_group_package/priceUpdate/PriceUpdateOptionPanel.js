Ext.namespace("administration.service_group_package.priceUpdate");

administration.service_group_package.priceUpdate.PriceUpdateOptionPanel = Ext.extend(Ext.Panel, {
//			title : 'Search',
			border:false,
			layout : 'column',
			border : false,
			autoHeight:true,
//			labelWidth:150,
			width:'100%',
			buttonAlign:'center',			
			initComponent : function() {
	
			this.widgets = new administration.service_group_package.priceUpdate.Widgets();
			this.buttonsPanel = new utils.SearchButtonsPanel();
			
			this.priceUpdateRadioGroup = new Ext.form.RadioGroup({
    			name:'rdGrpIncrDcr',
    			columns: [100, 100],
//    			height : 30,
           		items: [
                        {boxLabel: servicePriceMsg.lblIncrease, name: 'increDecreInd', inputValue: 'I',checked: true},
                        {boxLabel:  servicePriceMsg.lblDecrease, name: 'increDecreInd', inputValue: 'D'}
           			   ]
        		}) 
			
			Ext.applyIf(this, {
	            	items : [ 
	                        
	                        {
								columnWidth : 1,
								layout : 'form',
								items : [ this.widgets.getAmountNbf() ]
							},
							{
								columnWidth : 1,
								layout : 'form',
								items : [ this.widgets.getPrcntAbsCombo() ]
							},
							{
				    			columnWidth:1,
				    			labelWidth:.001,
				            	layout : 'form',
//				            	frame : true,
				            	align:'left',
				            	items:[
			            	       this.priceUpdateRadioGroup
				            	]
	                        },
							{
								columnWidth : 1,
								layout : 'form',
								items : [ this.widgets.getEffectiveFromDtFld() ]
							},
							],
							buttons:[
								this.widgets.getApplyBtn() ,
								this.widgets.getSaveBtn()
							]
						});
			administration.service_group_package.priceUpdate.PriceUpdateOptionPanel.superclass.initComponent.apply(this, arguments);
		},
	
	getSaveBtn : function(){
		return this.widgets.getSaveBtn();
	},
	
	getApplyBtn : function(){
		return this.widgets.getApplyBtn();
	},
	
//	getValues : function(){
//		return this.getForm().getValues();
//	},
	
	resetPriceUpdateOptionPanel : function(){
		this.widgets.getAmountNbf().setValue(null);
		this.widgets.getPrcntAbsCombo().clearValue();
		this.widgets.getEffectiveFromDtFld().setValue(new Date());
		this.priceUpdateRadioGroup.setValue("I");
	}
	
	
});