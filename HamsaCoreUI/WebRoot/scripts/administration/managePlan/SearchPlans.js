Ext.namespace("administration.managePlan");

administration.managePlan.SearchPlans = Ext.extend(Object, {
	constructor : function(config) {
		this.infoGrid = new administration.managePlan.PlansGrid();
		this.searchBtn = new Ext.Button({
			text: 'Search',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		this.infoGrid.search(this.searchPanel.getForm().getValues());
				this.fromDate.setMaxValue(null);
	    		this.toDate.setMinValue(null);
	    	}
		});
		
		this.resetBtn = new Ext.Button({
			text: 'Reset',
	    	iconCls : 'cancel_btn',
	    	scope: this,
	    	handler: function() {
	    		this.searchPanel.getForm().reset();
	    		this.infoGrid.search(this.searchPanel.getForm().getValues());
	    		this.fromDate.setMaxValue(null);
	    		this.toDate.setMinValue(null);
	    	}
		});
		
		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			buttons:[
				this.searchBtn,
				this.resetBtn
			]
		});
		var mainFormThisObj = this;
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel:	'Valid (from)',
			name:'validFromDt',
	        anchor : '70%',
	        listeners:{
	        	change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainFormThisObj.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel:	'valid (to)',
			name:'validToDt',
	        anchor : '70%',
	        listeners:{
		   		change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainFormThisObj.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
		
		this.insurerCbx = new Ext.form.OptComboBox({
			fieldLabel: 'Insurer',
    		anchor:'85%',
    		store:loadInsurerCbx(),
	        hiddenName:'insurerName',
			mode:'local',
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
	        forceSelection : true
		});
		
		this.searchPanel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			defaults:{columnWidth:.3},
			labelWidth : 80,
			items:[
				{
					layout:'form',
					items:this.insurerCbx
				},
				{
					layout:'form',
					items:[
						{
							fieldLabel: 'Plan code',
			        		xtype: 'textfield',
			        		name:'planCode',
			        		anchor:'80%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							fieldLabel: 'Plan name',
			        		xtype: 'textfield',
			        		name:'planName',
			        		anchor:'85%'
						}
					]
				},
				{
					layout:'form',
					items:[
						{
							fieldLabel: 'Sponsor',
			        		xtype: 'optcombo',
			        		anchor:'85%',
			        		store:loadSponsorsCbx(),
					        name:'sponsorName',
							mode:'local',
							triggerAction: 'all',
							displayField:'code',
							valueField:'code',
					        forceSelection : true
						}
					]
				},
				{
					layout:'form',
					items:this.fromDate
				},
				{
					layout:'form',
					items:this.toDate
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.buttonPanel
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.infoGrid.getPanel()
				}
			]
		});
		
		this.panel = new Ext.Panel({
			layout:'column',
			frame:true,
			defaults:{columnWidth:1},
			items:[
				{
					columnWidth:1,
					items:this.searchPanel
				}
			]
		});

		Ext.ux.event.Broadcast.subscribe('getSearchInsurancePlan',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.panel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadPlanGrid');
		},this,null,mainFormThisObj.getPanel());
			
		this.panel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadPlanGrid');
		return this.panel;
	},
	getFocus : function(config){
		this.insurerCbx.focus();
	}
});