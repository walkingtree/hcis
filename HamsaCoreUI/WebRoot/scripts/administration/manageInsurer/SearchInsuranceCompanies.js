Ext.namespace("administration.manageInsurer");

administration.manageInsurer.SearchInsuranceCompanies = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		var mainThis = this;
		config = {type:'insurer'};
		
		this.infoGrid = new administration.manageInsurer.InsurersGrid(config);
		
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
		
		this.insurerNameTxf = new Ext.form.TextField({
			fieldLabel: 'Name',
        	name:'insurerName',
        	anchor:'90%'
		});
		
		this.searchPanel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			border : false,
			items:[
				{
					layout:'form',
					columnWidth : .3,
					labelWidth:40,
					items:this.insurerNameTxf
				},
				{
					layout:'form',
					labelWidth:50,
					columnWidth : .3,
					items:[{
				        fieldLabel: 'Sponsor',
				        xtype: 'optcombo',
				        store:loadSponsorsCbx(),
				        name:'sponsorName',
						mode:'local',
						triggerAction: 'all',
						displayField:'code',
						valueField:'code',
				        anchor:'90%',
				        forceSelection : true
				        
				    }]
				},
				{
					layout:'form',
					columnWidth : .4,
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
		
		Ext.ux.event.Broadcast.subscribe('getSearchInsuranceCompany',function(){
			layout.getCenterRegionTabPanel().setActiveTab( this.panel );
			layout.getCenterRegionTabPanel().doLayout();
			Ext.ux.event.Broadcast.publish('loadInsurerGrid');
			
			/*if(this.infoGrid.getPanel().getStore().data.items.length == 0){
				this.infoGrid.getPanel().getStore().load({params:{start:0, limit:10}, arg:[null, null, null, null]});
			}else{
				this.infoGrid.getPanel().getStore().removeAll();
				this.infoGrid.getPanel().getStore().reload();
			}*/
			//}
//			this.editBtn.disable();
//			this.deleteBtn.disable();
//			this.viewBtn.disable();
			
		},this, null, mainThis.getPanel() );
		
		this.panel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	getPanel : function() {
		return this.panel;
	},
	getFocus : function(config){
		this.insurerNameTxf.focus();
	}
});