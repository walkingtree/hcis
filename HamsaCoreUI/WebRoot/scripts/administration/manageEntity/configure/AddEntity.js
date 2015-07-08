Ext.namespace("administration.manageEntity.configure");

/**
 * This panel supports three modes which needs to be passed as config 
 * 1. 'ADD' mode - Allow user to add the details of an entity
 * 2. 'EDIT' mode - Allow user to modify  the details of an entity
 */

administration.manageEntity.configure.AddEntity = Ext.extend(Ext.Panel, {
	border:false,
	labelAlign : 'left',
	height:700,
	frame:false,
	
	monitorValid : true,
 	initComponent : function() {
		this.recordStatus = null;
		this.seqNbr = null;
		this.config = this.initialConfig;
		this.config.isFromEntity = true;
		this.entityQualificationDetails = new administration.manageEntity.configure.EntityQualification(this.config);
		this.entityId = null;
		this.contactDetailsCode = null;
		this.gridRef=null;
		
		this.personalDetails = new administration.manageEntity.configure.PersonalDetails(this.config);
		
		this.config.addressType='contact';
		this.entityContactDetails = new OPD.ContactDetails(this.config);
		this.entityContactDetails.getCountryCbx().required=true;
		this.entityContactDetails.getCountryCbx().allowBlank=false;
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);

		this.buttonsPanel.getResetBtn().on("click", function(){
			var tmpThis = this;
			Ext.Msg.show({
				msg: entityMsg.resetMessage,
				icon : Ext.MessageBox.QUESTION,
				buttons: Ext.Msg.YESNO,
				fn: function( btnValue ){
					if( btnValue == configs.yes ){
						tmpThis.resetForm();
					}
				}
			});
		}, this);
		
		this.entityContactDetails.contactDetailsPanel.frame=false;
		this.entityContactDetails.contactDetailsPanel.border=false;
		
		this.contactFielsSet=new Ext.form.FieldSet({
			layout:'form',
			labelAlign:'left',
			autoHeight:true,
			width:'90%',
			title : 'Contact Details',
			border: true,
			defaults:{columnWidth:.5,labelWidth:100},
			items:[this.entityContactDetails.getPanel()]
		});
		
		
		this.personalDetails.on("clientvalidation", function(thisForm, isValid) {
			if (isValid 
					&& this.entityContactDetails.getCountryCbx().isValid()  
				  && !Ext.isEmpty(this.personalDetails.widgets.getEntitytNameTxf().getValue().trim() ) &&
					this.personalDetails.widgets.getEntityTypeCombo().isValid() &&
					this.personalDetails.widgets.getGenderCombo().isValid()
					){
				this.buttonsPanel.buttons[0].enable();
			} else {
				this.buttonsPanel.buttons[0].disable();
			}
		}, this);
		
		this.on('afterrender',function(thisPanel){
			this.personalDetails.createEntityPersonalDetailsPnl.setHeight( this.contactFielsSet.getHeight());
		},this);
		
		Ext.apply(this, {
			width:'100%',
			height:'100%',
			layout : 'form',
			autoScroll: false,
			frame: true,
	        items: [
					{
						border:false,
						layout:'column',
						items:[{columnWidth:0.6,
			            		layout : 'form',
			            		items :this.personalDetails
			            		},
			            		{columnWidth:0.4,
			            		layout : 'form',
			            		items :this.contactFielsSet
			            		},
			            		{border:false,
								columnWidth:1,
								items:[this.entityQualificationDetails.getPanel()]
			            		},
			            		{border:false,
									columnWidth:1,
									items:[this.buttonsPanel]
				            	}
			            	]
					}]          
	    });
		
        administration.manageEntity.configure.AddEntity.superclass.initComponent.apply(this, arguments);

	},
	
	// Save Button handling start 
	
	saveBtnClicked : function(){
    	this.personaldetailsvalues = this.personalDetails.getForm().getValues();
		this.contactdetailsvalues = this.entityContactDetails.getPanel().getForm().getValues();
		this.qualificationDetailsvalues=this.entityQualificationDetails.getPanel().getForm().getValues();
		this.contactDetails={
				
				street: this.contactdetailsvalues['placestreet'],
				city: this.contactdetailsvalues['citydistrict'],
				pincode: this.contactdetailsvalues['pincode'],
				phoneNumber: this.contactdetailsvalues['phonenumber'],
				mobileNumber: this.contactdetailsvalues['mobilenumber'],
				email: this.contactdetailsvalues['emailaddress'],
				country:{code:this.contactdetailsvalues['country'],description:this.contactdetailsvalues['']},
				state:{code:this.contactdetailsvalues['state'],description:this.contactdetailsvalues['']},
				createdBy:getAuthorizedUserInfo().userName
				
		};
		
		this.entityDetails={
				typeCode:{code:this.personalDetails.widgets.getEntityTypeCombo().getValue(),description:''},
				saluation:{code:this.personalDetails.widgets.getTitleCombo().getValue(),description:''},
				name:this.personalDetails.widgets.getEntitytNameTxf().getValue(),
				gender:{code:this.personalDetails.widgets.getGenderCombo().getValue()},
				dob:this.personalDetails.widgets.getDateofbirthFld().getValue()==''?null:this.personalDetails.widgets.getDateofbirthFld().getValue(),
				joiningDt:this.personalDetails.widgets.getEntityFromJoiningDate().getValue()==''?null:this.personalDetails.widgets.getEntityFromJoiningDate().getValue(),
				isPermanent:this.personalDetails.widgets.getIsActiveChk().getValue()?"Y":"N",
				qualification:this.qualificationDetailsvalues['qualification'],
				referredBy:this.qualificationDetailsvalues['referredBy'],
				knownLanguages:this.qualificationDetailsvalues['languages'],
				experience:this.qualificationDetailsvalues['experience'],
				contactDetailsBM:this.contactDetails,
				image :{fileName : this.personalDetails.getPhotoPanel().getImageName(),
					 filePath : this.personalDetails.getPhotoPanel().getImagePath()},
				userId:getAuthorizedUserInfo().userName
		};
		
		if( !Ext.isEmpty( this.initialConfig.mode ) && 
				this.initialConfig.mode == "edit" ){
			this.entityDetails.entityId = this.entityId;
			this.contactDetails.contactDetailsCode = this.contactDetailsCode;
			EntityManager.modifyEntity( this.entityDetails,{
				callback: function(){
					this.gridRef.reloadGrid();
					this.gridRef.setGridButtonsInitialState();
					layout.getCenterRegionTabPanel().remove( this, true );
				},
				callbackScope:this
			});
			
			}
			else{
				EntityManager.addEntity( this.entityDetails,{
					callback: function(){
						this.resetForm();
					},
					callbackScope:this
				});
			}
	},
		
		
	resetForm : function( config ){
		    this.entityQualificationDetails.resetQualificationPanel();
			this.personalDetails.resetForm();
			this.entityContactDetails.resetContactDetails();
			this.personalDetails.getPhotoPanel().reset( this.config );
	},
	setData :  function( config ){
		this.entityId = config.entityId;
		this.contactDetailsCode =config.contactDetailsCode;
		this.entityContactDetails.getPanel().getForm().setValues(config.configdata);
		this.entityContactDetails.setContryCbxValue(config.country);
		this.personalDetails.widgets.getEntityTypeCombo().setValue(config.typeCode);
		this.personalDetails.widgets.getEntitytNameTxf().setValue(config.name);
		this.personalDetails.widgets.getDateofbirthFld().setValue(config.dob);
		this.personalDetails.widgets.getEntityFromJoiningDate().setValue(config.joiningDt);
		this.personalDetails.widgets.getIsActiveChk().setValue(config.isPermanent);
		this.personalDetails.widgets.getGenderCombo().setValue(config.genderCode);
		this.personalDetails.widgets.getTitleCombo().setValue(config.saluation);
		this.entityQualificationDetails.getQualificationCbx().setValue(config.qualification);
		this.entityQualificationDetails.getLovCombo().setValue(config.knownLanguages);
		this.entityQualificationDetails.getReferenceTxf().setValue(config.referredBy);
		this.entityQualificationDetails.getExperenceTxf().setValue(config.experience);
		this.gridRef= config.gridref;
	}
	
});

