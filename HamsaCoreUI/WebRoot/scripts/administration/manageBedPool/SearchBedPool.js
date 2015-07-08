Ext.namespace("administration.manageBedPool");

administration.manageBedPool.SearchBedPool = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
		this.infoGrid = new administration.manageBedPool.BedPool();
		
		this.effectiveFormDate = new Ext.form.WTCDateField({
			fieldLabel: msg.effectivefrom,
	        name: 'effectiveFrom',
			anchor:'98%',
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
						mainThis.effectiveToDate.setMinValue(date.getValue());
					}
				}
			}
	
		});
		
		this.effectiveToDate = new Ext.form.WTCDateField({
			fieldLabel: msg.effectiveto,
			name: 'effectiveTo',
			anchor:'98%',
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
						mainThis.effectiveFormDate.setMaxValue(date.getValue());
					}
				}
			}
		});
		
		this.searchBtn = new Ext.Button({
	    	text: 'Search',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() 
	    	{
	    		var valuesMap = this.searchBedPoolPanel.getForm().getValues();
	    		if(this.searchBedPoolPanel.getForm().isValid()){
	    			this.infoGrid.dataStore.load({params:{start:0, limit:10}, arg:[valuesMap['poolName'], 
	    							 valuesMap['nursingUnit'],null,null]});
				 	mainThis.effectiveToDate.setMinValue(null);
				 	mainThis.effectiveFormDate.setMaxValue(null);
	    		}
	    		
	    	}
		});
		
		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			buttons:[this.searchBtn,{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.searchBedPoolPanel.getForm().reset();
					this.infoGrid.dataStore.load({params:{start:0, limit:10}, arg:[null,null,null,null]});
				}
			}]
		});				  

		this.searchBedPoolPanel = new Ext.form.FormPanel({
			layout:'column',
			labelAlign : 'left',
//			style:'padding-top:5px',
			width : '100%',
			autoHeight:true,
			defaults:{
				columnWidth:.33
			},
			border : false,
			items: [
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Name',
					        xtype: 'textfield',
					        name : 'poolName',
					        anchor:'98%'
					    }
					]
				},
				{
					layout:'form',
					columnWidth:.64,
					items:[
						{
					        fieldLabel: 'Unit type',
					        xtype: 'optcombo',
					        name: 'nursingUnit',
					        store: loadNursingUnitTypes(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
					        anchor:'50%'
					    }
					]
					
				},
				{
					layout:'form',
					items:this.effectiveFormDate
					
				},
				{
					layout:'form',
					items:this.effectiveToDate
					
				},this.buttonPanel,
				{
					columnWidth:1,
					items:this.infoGrid.getPanel()
				}
				]
		});
		
		this.searchBedPoolPanel.on("render", function(){this.infoGrid.dataStore.load({params:{start:0, limit:10}, arg:[null,null,null,null]});}, this);
		
		this.searchBedPoolPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	
	},
	getPanel : function() {
		return this.searchBedPoolPanel;
	}
});