Ext.namespace("OT.configureSurgery");

OT.configureSurgery.ConfigureSurgery = Ext.extend(Ext.Panel,{
	border : true,
	initComponent : function(){
	
		this.surgeryStatusTimeDetailList = this.initialConfig.surgeryStatusTimeDetailList;
		
		this.serviceWidgets = this.initialConfig.widgets;
		
		this.surgeryWidgets = new OT.configureSurgery.SurgeryWidgets();
		
		this.associatedOTGrid = new OT.configureSurgery.AssociatedOTGrid();
		
		this.surgeryStatusTimeDetail = new OT.configureSurgery.SurgeryStatusTimeDetailPanel({surgeryStatusTimeDetailList : this.surgeryStatusTimeDetailList,
																							configureSurgery : this,
																							totalSurgeryTime : this.initialConfig.totalSurgeryTime});
		
		this.addBtn = new Ext.Button({
			text: otMsg.btnAdd,
			scope : this,
			iconCls : 'add_btn',
			disabled : true
		});
		
		this.labelPanel = new Ext.Panel({
			layout :'column',
			style : 'padding : 0px 0px 10px 0px',
			items : [
		         {
		        	 columnWidth : .65,
		        	 layout : 'form',
		        	 style : 'font-size:15px',
		        	 xtype : 'label',
		        	 text : "Time configuration (in min)"
		         },
		         {
		        	 columnWidht : .35,
		        	 style : 'font-size:15px',
		        	 layout : 'form',
		        	 xtype : 'label',
		        	 text : "Surgeon required ?"
		         }
	         ]
		});
		
		this.surgeryPanel = new Ext.Panel({
			frame : true,
			border : false,
			layout : 'column',
			items : [
		         {
		        	 columnWidth : .5,
		        	 layout : 'form',
		        	 items : this.surgeryWidgets.getSurgeryTypeCbx()
		         },
		         {
		        	 columnWidth : .5,
		        	 layout : 'form',
		        	 items : this.surgeryWidgets.getSpecialityCbx()
		         },
		         {
		        	 columnWidth : 1,
		        	 xtype : 'fieldset',
		        	 style : 'padding : 10px 10px 10px 10px',
		        	 title : otMsg.surgeryTimeDetail,
		        	 layout : 'form',
		        	 items :[this.labelPanel, this.surgeryStatusTimeDetail]
		         },
		         {
		        	 columnWidth : .5,
		        	 style : 'padding : 10px 0px 0px 0px',
		        	 labelWidth : 150,
		        	 layout : 'form',
		        	 items : this.surgeryWidgets.getTotalNumberField()
		         },
		         {
		        	 columnWidth : 1,
		        	 style : 'padding : 10px 0px 0px 0px',
		        	 labelWidth : 150,
		        	 layout : 'form',
		        	 items : this.surgeryWidgets.getSurgeonRefreshmentTimeTxf()
		         }
	         ]
		});
		
		this.associatedOTGridPanel = new Ext.Panel({
			frame : true,
			border : false,
			layout : 'column',
			items : [//this.surgeryWidgets.getOtNameCbx(),this.associatedOTGrid
		         {
		        	columnWidth : .5,
		        	layout : 'form',
		        	frame : false,
		        	border : false,
		        	items :  this.surgeryWidgets.getOtNameCbx()
		         },
		         {
		        	columnWidth : .5,
		        	layout : 'form',
		        	frame : false,
		        	border : false,
		        	items :  this.addBtn
		         },

		         {
		        	 layout : 'form',
		        	 columnWidth : 1,
	        		 frame : true,
	        		 border : false,
		        	 items : this.associatedOTGrid
		         },
	         ]
		});
		
		var tabPanel = new Ext.TabPanel({
		    activeTab: 0,
		    items: [{
		    		title : otMsg.surgeryPanel,
		    		border : false,
	            	items : this.surgeryPanel
		    	},{
		    		title :otMsg.associatedOT,
		    		border : false,
		    		items : this.associatedOTGridPanel
		    	}
            ]
		});
		
		this.addBtn.on('click',function(thisBtn){
			this.addBtnClicked();
		},this);
		
		this.surgeryWidgets.getOtNameCbx().on('blur',function(comp){
			if( !Ext.isEmpty(comp.getRawValue())){
				this.addBtn.enable();
			}
			else{
				this.addBtn.disable();
			}
			
		},this);
		
		this.surgeryWidgets.getOtNameCbx().on('select',function(comp){
			if( !Ext.isEmpty(comp.getRawValue())){
				this.addBtn.enable();
			}
			else{
				this.addBtn.enable();
			}
			
		},this);

		
		Ext.applyIf(this, {
            items:tabPanel
        });
		
		OT.configureSurgery.ConfigureSurgery.superclass.initComponent.apply(this, arguments);
		
	},
	
	getSurgeryWidgests : function(){
		return this.surgeryWidgets;
	},
	
	addBtnClicked : function(){
		var otCode = this.surgeryWidgets.getOtNameCbx().getValue();
		if( this.associatedOTGrid.getStore().find("otCode" , otCode) != -1){
			Ext.Msg.show({
				msg :otMsg.otExistMsg,
				buttons : Ext.Msg.OK
				
			});
		}
		else{
			var otName = this.surgeryWidgets.getOtNameCbx().getRawValue();
			var recordType = this.associatedOTGrid.getStore().recordType;
			var otRecord = new recordType({
				otCode : otCode,
				otName : otName
			});
			
			this.associatedOTGrid.getStore().add( otRecord );
		}
		this.surgeryWidgets.getOtNameCbx().clearValue();
		this.addBtn.disable();
		
	},
	
	getSurgeryBM : function(){
		
		var otSurgeryAssoBMList = new Array();
		var associatedOTList = this.associatedOTGrid.getData();
		
		for(var i = 0 ; i < associatedOTList.length ; i++){
			var associatedOT = associatedOTList[i].data;
			var surgeryAssoBM = {
				surgeryName : {code : this.serviceWidgets.getServiceCodeTxf().getValue()},
				otName : {code : associatedOT.otCode , description : associatedOT.otName},
				createdBy : getAuthorizedUserInfo().userName
			};
			otSurgeryAssoBMList.push( surgeryAssoBM );
		}
		
		var surgeryBM = {
//			surgeryCode :,
//			surgeryName :,
			typeCode : {code : this.surgeryWidgets.getSurgeryTypeCbx().getValue()},
			specialtyCode : {code :this.surgeryWidgets.getSpecialityCbx().getValue() },
			doctorRefreshmentTime : this.surgeryWidgets.getSurgeonRefreshmentTimeTxf().getValue() ,
			totalTimeRequired : this.surgeryWidgets.getTotalNumberField().getValue(),
			userId : getAuthorizedUserInfo().userName,
			surgeryStatusTimeBMList : this.surgeryStatusTimeDetail.getSurgeryStatusTimeBMList(),
			otSurgeryAssoBMList : otSurgeryAssoBMList
		};
		
		return surgeryBM;
	},
	
	loadData : function( surgeryBM ){
		if( Ext.isEmpty( surgeryBM )){
			surgeryBM = { };
		}
		this.surgeryBM = surgeryBM;
//		this.surgeryWidgets.getSurgeryTypeCbx().setValue( surgeryBM.typeCode.code );
//		this.surgeryWidgets.getSpecialityCbx().setValue( surgeryBM.specialtyCode.code );
		this.setComboValue(this.surgeryWidgets.getSurgeryTypeCbx(), surgeryBM.typeCode.code );
		this.setComboValue(this.surgeryWidgets.getSpecialityCbx(), surgeryBM.specialtyCode.code);
		this.surgeryWidgets.getTotalNumberField().setValue( surgeryBM.totalTimeRequired );
		this.surgeryWidgets.getSurgeonRefreshmentTimeTxf().setValue( surgeryBM.doctorRefreshmentTime );
		
		this.associatedOTGrid.getStore().removeAll();
		
		this.associatedOTGrid.loadGrid(surgeryBM.otSurgeryAssoBMList);
	},
	
	setComboValue : function( thisCombo , value){
		thisCombo.setValue( value );
		thisCombo.getStore().on('load',function(){ 
			thisCombo.setValue( value );
		},this);
	},
	
	reset : function(){
		this.loadData(this.surgeryBM);
		this.surgeryStatusTimeDetail.reset();
	}
	
});