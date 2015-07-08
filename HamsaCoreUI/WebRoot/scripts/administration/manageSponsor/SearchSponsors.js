Ext.namespace("administration.manageSponsor");
administration.manageSponsor.SearchSponsors = Ext.extend(Object, {
	constructor : function(config) {
		this.infoGrid = new administration.manageSponsor.SponsorsGrid();
		this.searchBtn = new Ext.Button({
			text: 'Search',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		this.infoGrid.search(this.searchPanel.getForm().getValues());
	    	}
		});
		
		this.resetBtn = new Ext.Button({
			text: 'Reset',
	    	iconCls : 'cancel_btn',
	    	scope: this,
	    	handler: function() {
	    		this.searchPanel.getForm().reset();
	    		this.infoGrid.search(this.searchPanel.getForm().getValues());
	    	}
		});
		
		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			buttons:[
				this.searchBtn,
				this.resetBtn
			]
		});
		
		this.sponsorNameTxf = new Ext.form.TextField({
			fieldLabel: 'Name',
        	name:'sponsorName',
        	anchor:'80%'
		});
		
		this.searchPanel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			defaults:{columnWidth : .33},
			items:[
				{
					layout:'form',
					labelWidth:50,
					items:this.sponsorNameTxf
				},
				{
					layout:'form',
					labelWidth : 50,
					items:[{
				        fieldLabel: 'Type',
				        xtype: 'optcombo',
				        store:loadSponsorTypeCbx(),
				        name:'sponsorType',
						mode:'local',
						triggerAction: 'all',
						displayField:'description',
						valueField:'code',
				        anchor:'80%',
				        forceSelection : true
				    }]
				},
				{
					layout:'form',
			    	labelWidth:65,
					items:[{
				        fieldLabel: 'Credit class',
				        xtype: 'optcombo',
				        store:loadCreditClassCbx(),
				        name:'creditClassCode',
						mode:'local',
						triggerAction: 'all',
						displayField:'description',
						valueField:'code',
				        anchor:'80%',
				        forceSelection : true
				    }]
				},
				{
					layout:'form',
					labelWidth:50,
					items:[{
				        fieldLabel: 'Insurer',
				        xtype: 'optcombo',
				        store:loadInsurerCbx(),
				        name:'insurer',
						mode:'local',
						triggerAction: 'all',
						displayField:'code',
						valueField:'code',
				        anchor:'80%',
				        forceSelection : true
				        
				    }]
				},
				{
					layout:'form',
					columnWidth : .67,
					items:[this.buttonPanel]
				},
				{
					layout:'form',
					columnWidth : 1,
					items:[this.infoGrid.getPanel()]
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
		
		Ext.ux.event.Broadcast.subscribe('getSearchSponsor',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.panel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadSponsorGrid');
		}, this, null, this.getPanel());
		
		this.panel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
	},
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadSponsorGrid');
		return this.panel;
	},
	getFocus: function(){
		this.sponsorNameTxf.focus();
	}
});