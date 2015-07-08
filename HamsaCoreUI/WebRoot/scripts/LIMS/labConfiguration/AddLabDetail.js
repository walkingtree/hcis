Ext.namespace('LIMS.labConfiguration');

LIMS.labConfiguration.AddLabDetail = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
	initComponent : function() {
	
		this.widget = new LIMS.labConfiguration.Widgets();
		
		this.labDetailPanel = new LIMS.labConfiguration.LabDetailPanel();
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && 
				this.initialConfig.mode == limsMsg.editMode ){
			
			this.labDetailPanel.disableLabIdTxf();
		}
		
		this.labContactDetail = new LIMS.labConfiguration.LabContactDetail();

		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);
		
		this.buttonsPanel.getResetBtn().on("click", function(){
			
			if( !Ext.isEmpty( this.initialConfig.mode ) && 
					this.initialConfig.mode == limsMsg.editMode ){
				this.loadData(this.config);
			}else{
				this.getForm().reset();
			}
			
		}, this);
		
		this.widget.getIsLabInsideHospitalChk().disable();
		this.widget.getHospitalNameCbx().on('select',function(comp, record, index){
			if(record.data.code == null){
				this.widget.getIsLabInsideHospitalChk().disable();
			} else {
				this.widget.getIsLabInsideHospitalChk().enable();
			}
		},this);
		
		this.widget.getHospitalNameCbx().on('blur',function(comp) {
			if(Ext.isEmpty(comp.getRawValue())){
				this.widget.getIsLabInsideHospitalChk().disable();
			} else {
				this.widget.getIsLabInsideHospitalChk().enable();
			}
		},this);
		
		Ext.applyIf(this, {
            items: [
                    
			       	{   
			       		columnWidth:0.5,
		            	layout : 'form',
			       		items:[this.widget.getHospitalNameCbx()]
			       	},
			       	{
			       		columnWidth:0.5,
		            	layout : 'form',
		            	labelWidth:.001,
			       		items:[this.widget.getIsLabInsideHospitalChk()]
			       	},
			       	{   
			       		columnWidth:0.5,
		            	layout : 'form',
				    	 items:[this.labDetailPanel]
				    },{   
			       		columnWidth:0.5,
		            	layout : 'form',
				    	 items:[this.labContactDetail]
				    },{
				    	columnWidth:1,
				    	layout : 'form',
				    	items:[this.buttonsPanel]
				    }
				    
			       	
			      ]
	
	
	});
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
			}
		}, this); 
		
	
		 LIMS.labConfiguration.AddLabDetail.superclass.initComponent.apply(this, arguments);

},

saveBtnClicked : function(){
//	var inputValues = this.getForm().getValues();
//	var street = this.labContactDetail.widgets.getStreetTxf().getValue();
	
	var contactDetailBM= {
			street:this.labContactDetail.widgets.getStreetTxf().getValue(),
			locality:this.labContactDetail.widgets.getLocalityTxf().getValue(),
			city:this.labContactDetail.widgets.getCityTxf().getValue(),
			country:{code:this.labContactDetail.widgets.getCountryCbx().getValue()},
			state:{code:this.labContactDetail.widgets.getStateCbx().getValue()},
			email:this.labContactDetail.widgets.getEmailIDTxf().getValue(),
			phoneNumber:this.labContactDetail.widgets.getPhoneNumberTxf().getValue(),
			mobileNumber:this.labContactDetail.widgets.getMobileNumberTxf().getValue(),
			faxNumber:this.labContactDetail.widgets.getFaxNumberTxf().getValue()
	};
	
	var labDetailBM = {
			
//			labDetail:	this.labDetailPanel.getLabDetails(),
//			labContactDetail:this.labContactDetail.getLabContactDetails(),
			labId:this.labDetailPanel.widgets.getLabIdTxf().getValue(),
			labType:{code: this.labDetailPanel.widgets.getLabTypeCbx().getValue()},
			labName: this.labDetailPanel.widgets.getLabNameTxf().getValue(),
			businessName:this.labDetailPanel.widgets.getBusinessNameTxf().getValue(),
			branchName: this.labDetailPanel.widgets.getBranchNameTxf().getValue(),
			labOperatorID:this.labDetailPanel.widgets.getLabOperatorIDTxf().getValue(),
			directionFromKnownPlace:this.labDetailPanel.widgets.getDirectionFromKnownPlaceTxf().getValue(),
//			hospitalCode:this.widget.getHospitalNameCbx().getValue(),
			hospital:{code:this.widget.getHospitalNameCbx().getValue()},
			contactDetail: contactDetailBM,
			createdBy:  getAuthorizedUserInfo().userName
			
		};
//		LabDetailManager.addLabDetail(labDetailBM);
	

	if( !Ext.isEmpty( this.initialConfig.mode ) && 
			this.initialConfig.mode == limsMsg.editMode ){
				
			labDetailBM.labId = this.initialConfig.labId;
			
			LabDetailManager.modifyLabDetail( 
					labDetailBM,{
				callback: function(){
						
					
					if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
						layout.getCenterRegionTabPanel().remove( this,true );
						Ext.ux.event.Broadcast.publish('closeEditLabWindow');
					}
					loadLabStore().reload();
				},
				callbackScope:this
			});
		}
		else{
			LabDetailManager.addLabDetail(labDetailBM ,{
			callback: function(){
				this.getForm().reset();
				if( !Ext.isEmpty(this.initialConfig.isPopup) && this.initialConfig.isPopup == true){
					layout.getCenterRegionTabPanel().remove( this,true );
					Ext.ux.event.Broadcast.publish('closeAddLabWindow');
				}

				loadLabStore().reload();
		
			},
			callbackScope:this
		});
	}
	
	},

loadData : function( config ){
		this.config = config;
		
		this.labContactDetail.setData(config);
		this.getForm().setValues(config);
//		this.loadGrid( config.detailsList );
	}

});