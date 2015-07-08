Ext.namespace("administration.manageEntity.manage");

/*
 * Grid two types of functionalities ADD & EDIT
 * ADD - It gives new AddEntity panel
 * EDIT - It opens the AddEntity panel with selected data in edit mode
 */

administration.manageEntity.manage.EntityList = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:true,
	
	initComponent : function() {

		this.searchPanel = new administration.manageEntity.manage.EntitySearchPanel();
		this.gridPanel = new administration.manageEntity.manage.EntityGridPanel();
		
		this.searchPanel.getButtonPanel().getSearchButton().on('click',function(){
			this.searchButtonClicked();
		},this);
		
		this.searchPanel.getButtonPanel().getResetButtton().on('click',function(){
			this.resetButtonClicked();
		},this);
		
		this.gridPanel.getToolbar().getAddBtn().on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.gridPanel.getToolbar().getEditBtn().on('click',function(){
			this.editBtnClicked();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		
		Ext.applyIf(this, {
            items: [
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.searchPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.gridPanel]
			    }
			]            
        });
		administration.manageEntity.manage.EntityList.superclass.initComponent.apply(this, arguments);
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.loadData( searchCriteria ); 		
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.gridPanel.getReset();
		this.gridPanel.loadData();
	},
	
	addBtnClicked : function(){
		var tmpThis = this;
		var addPanel = new administration.manageEntity.configure.AddEntity();

		addPanel.frame = true;
		addPanel.title = entityMsg.addEntity; 
		addPanel.closable = true;
		addPanel.height = 420;

		var newPanel = layout.getCenterRegionTabPanel().add( addPanel );
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
	},
	
	editBtnClicked : function(){
		var selectedRow = this.gridPanel.getSelection();
		var selectedRowData = selectedRow[0].data;
		var configData = {
				name : selectedRowData.name,
				typeCode : selectedRowData.typeCodeDesc,
				isPermanent : selectedRowData.isPermanent=='Y'?true:false,
				genderCode :selectedRowData.genderCode,
				saluation :selectedRowData.saluation,
				contactDetailsBM :selectedRowData.contactDetailsBM,
				country:selectedRowData.contactDetailsBM.country.code,
				configdata:{
			    state:Ext.isEmpty(selectedRowData.contactDetailsBM.state) ? null : selectedRowData.contactDetailsBM.state.description,
			    		pincode:selectedRowData.contactDetailsBM.pincode,
			    		placestreet:selectedRowData.contactDetailsBM.street,
			    		citydistrict:selectedRowData.contactDetailsBM.city,
			    		phonenumber:selectedRowData.contactDetailsBM.phoneNumber,
			    		mobilenumber:selectedRowData.contactDetailsBM.mobileNumber,
			    		emailaddress:selectedRowData.contactDetailsBM.email
		         },
		         contactDetailsCode :selectedRowData.contactDetailsBM.contactDetailsCode,
				dob :selectedRowData.dob,
				image :selectedRowData.image,
				knownLanguages :selectedRowData.knownLanguages,
				qualification :selectedRowData.qualification,
				joiningDt :selectedRowData.joiningDt,
				experience :selectedRowData.experience,
				referredBy :selectedRowData.referredBy,
				entityId:selectedRowData.entityId,
				imagePropertyBM:selectedRowData.image,
				mode:'edit',
				gridref:this.gridPanel
		};
		
		var tmpThis = this;
		var editPanel = new administration.manageEntity.configure.AddEntity(configData);
		editPanel.closable = true;
		editPanel.setData( configData );
		editPanel.frame = true;
		editPanel.title = entityMsg.editEntity; 
		
		var newPanel = layout.getCenterRegionTabPanel().add( editPanel );
		
		layout.getCenterRegionTabPanel().setActiveTab( newPanel );
		layout.getCenterRegionTabPanel().doLayout();
		
	}
	
});

Ext.reg('entity-list-panel', administration.manageEntity.manage.EntityList);
