Ext.namespace("IPD.manageClaim");

IPD.manageClaim.SearchClaims = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
		var mainFormThisObj = this;
		this.infoGrid = new IPD.manageClaim.AdmissionClaimsGrid();
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
    			this.fromDate.setMaxValue(null);
	    		this.toDate.setMinValue(null);
    			this.infoGrid.search(this.searchPanel.getForm().getValues());
	    	}
		});
		
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel:	'Requested from',
			name:'requestedFromDt',
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
			fieldLabel:	'Requested to',
			name:'requestedToDt',
	        anchor : '70%',
	        listeners:{
		   		change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainFormThisObj.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
		
		this.statusCbx = new Ext.form.OptComboBox({
			fieldLabel: 'Status',
	        store: loadClaimStatusCbx(),
			mode:'local',
			displayField:'description',
			valueField:'code',
		    triggerAction: 'all',
		    hiddenName:'claimStatus',
		    forceSelection : true,
	        anchor:'97%'
		});
		
		this.sponsorCbx = new Ext.form.OptComboBox({
	        fieldLabel: 'Sponsor',
	        store:loadSponsorsCbx(),
	        hiddenName:'sponsor',
			mode:'local',
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
	        anchor:'90%',
	        forceSelection : true
		});
		
		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			buttons:[this.searchBtn,this.resetBtn]
		});
		
		this.panTxf = new Ext.form.TextField({
			fieldLabel: 'ARN',	
			name:'pan',
	        anchor:'90%'
		});
		
		this.searchPanel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			defaults:{columnWidth:.33,labelWidth:100},
			items: [
				{
					layout:'form',
					items:this.panTxf
				},
				{
					layout:'form',
					items:this.sponsorCbx
				},
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Plan name',
					        xtype: 'textfield',
					        name:'planName',
					        anchor:'90%'
					    }
					]
				},
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Policy number',
					        xtype: 'textfield',
					        name:'policyNbr',
					        anchor:'90%'
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
					items:this.statusCbx
				},
				{
					layout:'form',
					columnWidth:0.67,
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
		
		Ext.ux.event.Broadcast.subscribe('getSearchClaim',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.panel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadClaimGrid');
		},this,null,mainFormThisObj.getPanel());
		
		this.panel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadClaimGrid');
		return this.panel;
	},
	getFocus : function(){
		this.panTxf.focus();
	}
});