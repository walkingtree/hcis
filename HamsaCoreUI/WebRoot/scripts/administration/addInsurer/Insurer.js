Ext.namespace("administration.addInsurer");

administration.addInsurer.Insurer = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		Ext.apply(this, config);

		this.contactDetailPanel = new  administration.addInsurer.MediclaimContactDetails();
		
		this.sponsorCbx = new Ext.form.ComboBox({
			anchor: '90%',
	        fieldLabel: 'Sponsor name',
	        store:loadSponsorsCbx(),
	        hiddenName: 'sponsorName',
			mode:'local',
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
	        forceSelection : true
		});
		
		this.insurerSponsorAssoc = new administration.addInsurer.InsurerSponsorAssoc({title: 'Associated sponsors', 
																		nameTitle:'Sponsor name',
																		cbx:this.sponsorCbx,
																		isViewBtnClicked:config.isViewBtnClicked});
		this.planPanel = new administration.insurerPlan.InsurancePlan();
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				scope:this,
				handler: function(){
					this.saveInsurer(config);
				}
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.resetInsurer();
				}
			}]
		});
		
		this.insurerNameTxf = new Ext.form.TextField({
			fieldLabel: 'Insurer name',
			name: 'insurerName',
	        anchor: '90%',
	        readOnly:config.mode=='edit'?true:false,
	        required:true,
	        allowBlank:false,
	        listeners:{
				blur:function(comp){
					if(!Ext.isEmpty(comp.getValue()) && Ext.isEmpty(config.mode)){
						InsuranceManager.getInsurer( 
							comp.getValue(), 
							null, 
							null, 
							null, 
							function(insurerBMList){
								if(insurerBMList != null && !Ext.isEmpty(insurerBMList) && 
									insurerBMList.length > 0){
									Ext.Msg.show({
					 					msg: "This insurer already exists..!",
									    buttons: Ext.Msg.OK,
									    icon: Ext.MessageBox.WARNING,
									    fn:function(btn){
									    	comp.reset();
									    	comp.focus();
									    }
				 					});
								}
						});
					}
				}				        
	        }
		});
		
		this.insurerDescriptionTxf = new Ext.form.TextField({
			fieldLabel: 'Description',
	        name: 'insurerDesc',
	        anchor: '90%'
		});
		
		this.addAsSponsorChk = new Ext.form.Checkbox({
			boxLabel:'Add as sponsor',
			fielsLabel:'',
			labelSeparator:''
		});
		
		if( !Ext.isEmpty(config) && config.mode == 'edit'){
			this.addAsSponsorChk.hide();
		}
		
		this.basicDetail = new Ext.form.FormPanel({
			monitorValid: true,
			items: [
				{
					layout : 'column',
					defaults: {border: false,layout: 'form'},
					items: [
						{
							columnWidth : .33,
							items:this.insurerNameTxf
						},
						{
							columnWidth : .66,
						    items:this.insurerDescriptionTxf
					    },
					    {
							columnWidth : .3,
							labelWidth:.1,
						    items:this.addAsSponsorChk
					    }
			    	]
				} 
			]
		});
		
		this.tabPanel = new Ext.TabPanel({
				activeTab:0,
				width : '100%',
				autoHeight : true,
				border : false,
				style:'padding-top:10px',
				layoutOnTabChange :true,
				items: [
					{
						title:'Contact details',
						autoScroll:true,
						autoHeight:true,
						frame:true,
						items:this.contactDetailPanel.getPanel()
					},
					this.insurerSponsorAssoc.getPanel()
				]
		});
		
		this.panel = new Ext.Panel({
			frame:true,
			width : '100%',
			autoHeight : true,
			border : false,
			items: [
				this.basicDetail,
				this.tabPanel,
				this.buttonPanel
			]
		});
		
		if(config.isViewBtnClicked){
			this.panel = new Ext.Panel({
				frame:true,
				width : '100%',
				autoHeight : true,
				border : false,
				items: this.insurerSponsorAssoc.getPanel()
			});
		}
		
		this.basicDetail.on("clientvalidation", function(thisForm, isValid) {
			if (isValid && this.contactDetailPanel.getPanel().getForm().isValid()){
				this.buttonPanel.buttons[0].enable();
			} else {
				this.buttonPanel.buttons[0].disable();
			}
		},this);

	},
	getPanel : function() {
			return this.panel;
	},
	getData : function() {
		var valuesMap = this.panel.getForm().getValues();
	},
	saveInsurer : function(config){
		if(this.basicDetail.getForm().isValid()){
			var assocSponsorRows = this.insurerSponsorAssoc.infoGrid.getStore().data.items;
			var basicDetails = this.basicDetail.getForm().getValues();
			var contactDetails = this.contactDetailPanel.getPanel().getForm().getValues();
			var sponsorBMList = new Array();
			var mainThis = this;

			for( var i = 0; i<assocSponsorRows.length; i++ ) {
				var currRow = assocSponsorRows[i].data;
				var sponsor = {
					sponsorName:currRow.sponsorName,
				    effectiveFromDate:Ext.isEmpty(currRow.effectiveFromDate)?null:currRow.effectiveFromDate, 
				    effectiveToDate:Ext.isEmpty(currRow.effectiveToDate)?null:currRow.effectiveToDate, 
					createdBy:authorisedUser.userName
				}
				sponsorBMList.push(sponsor);
			}
			
			var contactDetailsBM = {
	 				phoneNumber:contactDetails.phonenumber,
	 				mobileNumber:contactDetails.mobilenumber,
	 				faxNumber:contactDetails.faxnumber,
	 				email:contactDetails.emailaddress,
					firstName:contactDetails.firstname,
	 				middleName:contactDetails.middlename,
					lastName:contactDetails.lastname,
 					salutation: {code:contactDetails.title},
 					gender:{code:contactDetails.gender},
 					relationCode:{code:contactDetails.relationship}
			};
			
			var insurerBM = {
				insurerName : basicDetails.insurerName,
				insurerDesc : basicDetails.insurerDesc,
				contactDetailsBM : contactDetailsBM,
				createdBy :authorisedUser.userName,
				modifiedBy : authorisedUser.userName,
				sponsorInsurerAssociationBMList :sponsorBMList
			};
			
			if(config.mode == "edit"){
				InsuranceManager.modifyInsurer(insurerBM,function(){
					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
					Ext.ux.event.Broadcast.publish('closeInsurerPanel');
				});
			}else{
				InsuranceManager.createInsurer( insurerBM,function(){
					if(!Ext.isEmpty(insurerStore)){
							insurerStore.reload();
						}
				});
					if(mainThis.addAsSponsorChk.getValue()){
						mainThis.addAsSponsor();
					}else if(mainThis.isPopUp){
						layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
						Ext.ux.event.Broadcast.publish('closeInsurerPanel');
					}else{
						mainThis.resetInsurer();
					}	
			}
		} else {
			alertWarning("Enter all the required fields..!");
		  	return;
		}
	},
	resetInsurer : function(){
		this.insurerSponsorAssoc.infoGrid.getStore().removeAll();
		this.basicDetail.getForm().reset();
		this.contactDetailPanel.getPanel().getForm().reset();
		this.insurerSponsorAssoc.resetAssociationPanel();
		if(this.mode=='edit'){
			this.loadData(this);
		}
	},
	loadData : function(config){
		var config = config.selectedRow;
		this.basicDetail.getForm().setValues(config);
		
		var contactdetails = {
			phonenumber:config.contactDetailsBM.phoneNumber,
			mobilenumber:config.contactDetailsBM.mobileNumber,
			faxnumber:config.contactDetailsBM.faxNumber,
			emailaddress:config.contactDetailsBM.email,
			firstname:config.contactDetailsBM.firstName,
			middlename:config.contactDetailsBM.middleName,
			lastname:config.contactDetailsBM.lastName,
			title:Ext.isEmpty(config.contactDetailsBM.salutation)?null:config.contactDetailsBM.salutation.code,
			gender:Ext.isEmpty(config.contactDetailsBM.gender)?null:config.contactDetailsBM.gender.code,
			relationship:config.contactDetailsBM.relationCode
		}
		this.contactDetailPanel.getPanel().getForm().setValues(contactdetails);
		
		var assocSponsorsRows = config.sponsorInsurerAssociationBMList;
		var assocSponsorRecord = this.insurerSponsorAssoc.infoGrid.getStore().recordType;
		if(assocSponsorsRows!=null && assocSponsorsRows.length>0 ){
			for(var i = 0; i<assocSponsorsRows.length; i++){
				var sponsorRecord = new assocSponsorRecord({
					sponsorName: assocSponsorsRows[i].sponsorName,
					sponsorNameDesc: '',
					insurerName: assocSponsorsRows[i].insurerName,
					insurerNameDesc: '',
					effectiveFromDate: assocSponsorsRows[i].effectiveFromDate,
					effectiveToDate: assocSponsorsRows[i].effectiveToDate,
					createdBy: assocSponsorsRows[i].createdBy
				});
				 this.insurerSponsorAssoc.infoGrid.getStore().add(sponsorRecord);
			}
		}
	},
	addAsSponsor : function(){
		if(this.basicDetail.getForm().isValid()){
			var basicDetails = this.basicDetail.getForm().getValues();
			var contactDetails = this.contactDetailPanel.getPanel().getForm().getValues();
			var mainThis = this;

			var contactDetailsBM = {
	 				phoneNumber:contactDetails.phonenumber,
	 				mobileNumber:contactDetails.mobilenumber,
	 				faxNumber:contactDetails.faxnumber,
	 				email:contactDetails.emailaddress,
					firstName:contactDetails.firstname,
	 				middleName:contactDetails.middlename,
					lastName:contactDetails.lastname,
 					salutation: {code:contactDetails.title},
 					gender:{code:contactDetails.gender},
 					relationCode:{code:contactDetails.relationship}
			};

			var sponsorDetailsBM = {
				sponsorsName : basicDetails.insurerName,
				sponsorType :  {code:configs.sponsorType},
				sponsorDesc :  basicDetails.insurerDesc,
				contactDetailsBM : contactDetailsBM,
				createdBy :authorisedUser.userName,
				modifiedBy : authorisedUser.userName
			};
			
			InsuranceManager.createClaimSponsor(sponsorDetailsBM,{
				callback: function(sponsorDetailBM){
					if(sponsorDetailBM != null && !Ext.isEmpty(sponsorDetailBM)){
						if(mainThis.isPopUp){
							layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
							Ext.ux.event.Broadcast.publish('closeInsurerPanel');
						}else{
							mainThis.resetInsurer();
						}
					}
					sponsorStore.reload();
					insurerStore.reload();
				}}
		    );
		}
	},
	getFocus : function(config){
		if(config.mode == "edit"){
			this.insurerDescriptionTxf.focus();
		}else{
			this.insurerNameTxf.focus();
		}
	}
});