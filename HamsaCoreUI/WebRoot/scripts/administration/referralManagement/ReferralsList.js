Ext.namespace("administration.referralManagement");

administration.referralManagement.ReferralsList = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:false,
	
	initComponent : function() {

		this.referralSearchPanel = new administration.referralManagement.ReferralSearchPanel();
		this.referralsListGridPanel = new administration.referralManagement.ReferralsListGridPanel();
		
		this.referralSearchPanel.getButtonPanel().getSearchButton().on('click',function(){
			this.searchButtonClicked();
		},this);
		
		
		this.referralSearchPanel.getButtonPanel().getResetButtton().on('click',function(){
			this.resetButtonClicked();
		},this);

			
		this.toolBar = this.referralsListGridPanel.getToolbar();
        this.toolBar.getAddBtn().show();
        this.toolBar.getEditBtn().show(); 
        this.toolBar.getDeleteBtn().show();
		
		this.referralsListGridPanel.getToolbar().getAddBtn().on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.referralsListGridPanel.getToolbar().getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.referralsListGridPanel.getToolbar().getDeleteBtn().on('click',function(){
			this.delBtnClicked();
		},this);
		
		this.referralsListGridPanel.getCheckBoxSelection().on('rowselect',function(gridChk, rowIndex, record){
			this.btnEnableDisable(gridChk, rowIndex, record);
		},this);


		this.referralsListGridPanel.getCheckBoxSelection().on('rowdeselect',function(gridChk, rowIndex, record){
			this.btnEnableDisable(gridChk, rowIndex, record);
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		Ext.applyIf(this, {
            items: [
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.referralSearchPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.referralsListGridPanel]
			    }
			]            
        });
        administration.referralManagement.ReferralsList.superclass.initComponent.apply(this, arguments);
	},
	
	
	searchButtonClicked : function(){
		var searchCriteria = this.referralSearchPanel.getValues();
		var referralLightBM = {
   			referralType :{code : searchCriteria['referralType']},
   			referralName : searchCriteria['referralName'],
   			referralAddress : searchCriteria['address'],
   			city : searchCriteria['city'],
   			pincode : searchCriteria['pinCode'],
   			country :{code : searchCriteria['country']},
   			state :{code : searchCriteria['state']},
   			preferredContactTime : searchCriteria['preferredTime']
   		};
   		this.referralsListGridPanel.loadData(referralLightBM);
		this.getButtonsInitialState();
	},
	
	resetButtonClicked : function(){
		this.referralSearchPanel.getReset();
		this.referralsListGridPanel.loadData();
		this.getButtonsInitialState();
	},
	
	addBtnClicked : function(){
		var tmpThis = this;
		var panel = new administration.referralManagement.AddReferral({mode : 'add'});
		panel.title = 'Add referral';
		panel.frame = true;
		panel.closable = true;
		panel.height = 420;
		
		var activeTab =	layout.getCenterRegionTabPanel().add(panel);
		
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();
		
		Ext.ux.event.Broadcast.subscribe('closeAddReferralPanel',function(){
//			layout.getCenterRegionTabPanel().remove( activeTab , true );
			layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
			layout.getCenterRegionTabPanel().doLayout();
			tmpThis.referralsListGridPanel.loadData();
			tmpThis.getButtonsInitialState();
		},tmpThis,null,tmpThis);
	},
	
	editBtnClicked : function(){
		var tmpThis = this;
		var selectedRow = this.referralsListGridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		var referralCode = selectedRowData.referralCode;
		ReferralManager.getReferralDetails(referralCode,function(referralBM){
			if(referralBM != null && !Ext.isEmpty(referralBM)){
				var editPanel = new administration.referralManagement.AddReferral({
						mode: 'edit',
						referralType: referralBM.referralType.code
				});
				
				editPanel.frame =true;
				editPanel.title = "Edit referral";
				editPanel.closable =true;
				editPanel.height =420;
				
				var activeTab = layout.getCenterRegionTabPanel().add(editPanel);
				
				editPanel.loadData(referralBM);
				
				layout.getCenterRegionTabPanel().setActiveTab( activeTab );
				layout.getCenterRegionTabPanel().doLayout();
				
				Ext.ux.event.Broadcast.subscribe('closeEditReferralPanel',function(){
//					layout.getCenterRegionTabPanel().remove( activeTab , true );
					layout.getCenterRegionTabPanel().setActiveTab( tmpThis );
					layout.getCenterRegionTabPanel().doLayout();
					tmpThis.referralsListGridPanel.loadData();
					tmpThis.getButtonsInitialState();
				},tmpThis,null,tmpThis);
			}
		});
	},
	
	delBtnClicked : function(){
		var tmpThis = this;
		var selectedRow = this.referralsListGridPanel.getSelection();
		var referralCode = selectedRow[0].data.referralCode;
		
		Ext.Msg.show({
			msg: "Do you delete selected entries ?",
			icon : Ext.MessageBox.QUESTION,
			buttons: Ext.Msg.YESNO,
			fn: function( btnValue ){
				if( btnValue == configs.yes ){
					ReferralManager.deleteReferral(referralCode,function(){
						tmpThis.referralSearchPanel.getReset();
						tmpThis.referralsListGridPanel.loadData();
						tmpThis.getButtonsInitialState();
					});
				}
	   		}
		});
	},
	
	btnEnableDisable : function(gridChk, rowIndex, record){
		if(gridChk.getSelections().length == 1){
			this.referralsListGridPanel.getToolbar().getEditBtn().enable();
			this.referralsListGridPanel.getToolbar().getDeleteBtn().enable();
				
		}
		else {
			this.referralsListGridPanel.getToolbar().getEditBtn().disable();
			this.referralsListGridPanel.getToolbar().getDeleteBtn().disable();
		}
	},
	
	getButtonsInitialState : function(){
        this.referralsListGridPanel.getToolbar().getEditBtn().disable();
		this.referralsListGridPanel.getToolbar().getDeleteBtn().disable();
	}
});

Ext.reg('refferals-list-panel', administration.referralManagement.ReferralsList);
