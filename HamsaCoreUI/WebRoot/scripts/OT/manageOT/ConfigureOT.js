Ext.namespace("OT.manageOT"); 

OT.manageOT.ConfigureOT = Ext.extend(Ext.Panel,{
	initComponent : function(){
	
		this.gridDataList = null;
	
		this.otWidgets = new OT.manageOT.OTWidgets();
		
		this.associatedSurgeryGrid = new OT.manageOT.AssociatedSurgeryGrid( this.otWidgets );
		
		this.checkListPanel = new OT.manageOT.CheckListPanel(); 
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.addBtn = new Ext.Button({
			text: otMsg.btnAdd,
			scope : this,
			iconCls : 'add_btn',
			disabled : true
		});
		
		this.formPanel = new Ext.form.FormPanel({
			layout : 'column',
			monitorValid : true,
			defaults : {
				labelWidth : 80,
				padding : '0px 0px 0px 0px'
			},
			items : [
		         {
		        	layout : 'form',
		        	columnWidth : .50,
		        	items :this.otWidgets.getOtCodeTxf({required : true}) 
		         },
		         {
		        	layout : 'form',
		        	columnWidth : .50,
		        	items :this.otWidgets.getOtNameTxf({required : true}) 
		         },
		         {
		        	layout : 'form',
		        	columnWidth : .50,
		        	items :this.otWidgets.getBedNbrCbx({required : true}) 
		         },
		         {
		        	layout : 'form',
		        	columnWidth : .50,
		        	items :this.otWidgets.getCoordinatorCbx({required : true}) 
		         }
	         ]
		});
		
		this.associatedSurgeryPanel = new Ext.form.FieldSet({
        	title : otMsg.associatedService,
//        	frame : true,
//        	border : true,
        	items :[
    	        {
    	        	layout : 'column',
    	        	frame : false,
    	        	border : false,
    	        	items :[
	        	        {
		       	        	labelWidth : 80,
		       	        	columnWidth : .5,
		   		        	layout : 'form',
		   		        	items : this.otWidgets.getSurgeryCbx()	        	       
		   	        	},
		   	        	{
		   	        		columnWidth : .5,
		   	        		items : this.addBtn
		   	        	}
	   	        	]
    	        },
    	        this.associatedSurgeryGrid 
	        ]
         });
		
		this.buttonsPanel.getSaveBtn().on('click',function(){
			this.saveBtnClicked();
		},this);
		
		this.buttonsPanel.getResetBtn().on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.addBtn.on('click',function(){
			this.addBtnClicked();
		},this);
		
		this.formPanel.on('clientvalidation',function(thisPanel, isValid){
			
			if( isValid){
				this.buttonsPanel.getSaveBtn().enable();
			}
			else{
				this.buttonsPanel.getSaveBtn().disable();
			}
		},this);
		
		this.otWidgets.getSurgeryCbx().on('blur',function(comp){
			if( !Ext.isEmpty(comp.getValue())){
				this.addBtn.enable();
			}
			else{
				this.addBtn.enable();
			}
			
		},this);
		
		this.otWidgets.getSurgeryCbx().on('select',function(comp){
			if( !Ext.isEmpty(comp.getValue())){
				this.addBtn.enable();
			}
			else{
				this.addBtn.enable();
			}
			
		},this);

		
		Ext.applyIf(this,{
			items : [
		         this.formPanel,
		         this.associatedSurgeryPanel,
		         this.buttonsPanel
//		         {
//	        	     xtype : 'tabpanel',
//	        	     activeTab : 0,
//	        	     frame : false,
//	        	     items :[ 
//        	              this.checkListPanel
//		        	 ]	
//		         },
		         
			]
		});
		
		OT.manageOT.ConfigureOT.superclass.initComponent.apply(this,arguments);
	},
	
	saveBtnClicked : function(){
		var mainThis = this;
		var otSearchPanel = this.initialConfig.otSearchPanel;
		var associatedSurgeryList = this.associatedSurgeryGrid.getData();
		var otCode = this.otWidgets.getOtCodeTxf().getValue();
		var otName = this.otWidgets.getOtNameTxf().getValue();
		var bedNumber = this.otWidgets.getBedNbrCbx().getValue();
		var coordinatorId = this.otWidgets.getCoordinatorCbx().getValue();
		var indexNbr = this.otWidgets.getCoordinatorCbx().getStore().find('code',coordinatorId+"");
		var coordinator = this.otWidgets.getCoordinatorCbx().getStore().getAt( indexNbr );
		var otSurgeryAssoBMList = new Array();
		
		for(var i = 0 ; i < associatedSurgeryList.length ; i++){
			var associatedSurgery = associatedSurgeryList[i].data;
			var surgeryAssoBM = {
				otName : {code : otCode, description : otName},
				surgeryName : {code : associatedSurgery.surgeryCode , description : associatedSurgery.surgeryCode},
				createdBy : getAuthorizedUserInfo().userName
			}
			otSurgeryAssoBMList.push( surgeryAssoBM );
		}
		
		var otDetailBM = {
			otId : otCode,
			bedNumber : bedNumber,
			name : otName,
			coordinator : {code : coordinator.data.code , description : coordinator.data.description},
			createdBy : getAuthorizedUserInfo().userName,
			otSurgeryAssoBMList : otSurgeryAssoBMList
		};
		
		if( this.initialConfig.mode === "edit"){
			OTManager.modifyOTDetail( otDetailBM , function( otDetail){
				layout.getCenterRegionTabPanel().remove( mainThis );
				layout.getCenterRegionTabPanel().setActiveTab( otSearchPanel );
				if( !Ext.isEmpty( otSearchPanel.getOTSearchGridPanel() )){
					otSearchPanel.getOTSearchGridPanel().loadGrid();
				}
			});
		}
		else{
			OTManager.addOT( otDetailBM , function(){
				if( mainThis.initialConfig.mode === "add"){
					layout.getCenterRegionTabPanel().remove( mainThis );
					layout.getCenterRegionTabPanel().setActiveTab( otSearchPanel );
					if( !Ext.isEmpty( otSearchPanel.getOTSearchGridPanel() )){
						otSearchPanel.getOTSearchGridPanel().loadGrid();
					}
						}
				else{
					mainThis.resetBtnClicked();
				}
			});
		}
	},
	
	resetBtnClicked : function(){
		this.otWidgets.getSurgeryCbx().clearValue();
		this.formPanel.getForm().reset();
		this.associatedSurgeryGrid.reset();
		if( !Ext.isEmpty( this.gridDataList)){
			this.loadSurgeryAssoGrid(this.gridDataList);
		}
//		this.checkListPanel.reset();
	},
	
	addBtnClicked : function(){
		var surgeryCode = this.otWidgets.getSurgeryCbx().getValue();
		if( this.associatedSurgeryGrid.getStore().find("surgeryCode" , surgeryCode) != -1){
			Ext.Msg.show({
				msg :otMsg.existMsg,
				buttons : Ext.Msg.OK
				
			});
		}
		else{
			var recordType = this.associatedSurgeryGrid.getStore().recordType;
			var indexNbr = this.otWidgets.getSurgeryCbx().getStore().find('code',surgeryCode+"");
			var record = this.otWidgets.getSurgeryCbx().getStore().getAt( indexNbr );
			var surgeryName = record.data.description;
			var surgeryRecord = new recordType({
				surgeryCode : surgeryCode,
				surgeryName : surgeryName
			});
			
			this.associatedSurgeryGrid.getStore().add( surgeryRecord );
		}
		this.otWidgets.getSurgeryCbx().clearValue();
		this.addBtn.disable();
		
		
	},
	
	loadData : function( config ){
		this.formPanel.getForm().setValues( config );
		this.otWidgets.getBedNbrCbx().getStore().on('load',function(thisCombo){
			this.otWidgets.getBedNbrCbx().setValue( config.bedNbr);
		},this); 
		this.otWidgets.getCoordinatorCbx().getStore().on('load',function(thisCombo){
			this.otWidgets.getCoordinatorCbx().setValue( config.coordinator);
		},this); 
	},
	
	loadSurgeryAssoGrid : function( gridDataList ){
		this.gridDataList = gridDataList;
		this.associatedSurgeryGrid.loadGrid( gridDataList );
	}

	
});