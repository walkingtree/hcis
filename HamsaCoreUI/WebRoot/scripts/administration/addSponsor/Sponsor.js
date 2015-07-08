Ext.namespace("administration.addSponsor");

administration.addSponsor.Sponsor = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
		Ext.apply(this, config);
		
		this.contactDetailPanel = new  administration.addInsurer.MediclaimContactDetails();
		
		this.insurerCbx = new Ext.form.ComboBox({
			anchor: '90%',
	        fieldLabel: 'Insurer name',
	        store:loadInsurerCbx(),
	        hiddenName: 'insurerName',
			mode:'local',
			triggerAction: 'all',
			displayField:'code',
			valueField:'code',
	        forceSelection : true
		});
		
		this.insurerSponsorAssoc = new administration.addInsurer.InsurerSponsorAssoc({title: 'Associated insurers', 
																		nameTitle:'Insurer name',
																		cbx:this.insurerCbx,
																		isViewBtnClicked:config.isViewBtnClicked});
		this.slaPanel = new administration.addSponsor.SLA();
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:'Save',
				iconCls : 'save_btn',
				scope:this,
				handler: function(){
					this.saveSponsor(config);
				}
			},{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.resetSponsor();
				}
			}]
		});
		
		this.sponsorNameTxf = new Ext.form.TextField({
			fieldLabel: 'Sponsor name',
	        name: 'sponsorsName',
	        anchor: '90%',
	        readOnly:config.mode=='edit'?true:false,
	        required:true,
	        allowBlank:false,
	        listeners:{
				blur:function(comp){
					if(!Ext.isEmpty(comp.getValue()) && Ext.isEmpty(config.mode)){
						InsuranceManager.getSponsors( 
							comp.getValue(), 
							null, 
							null, 
							null, 
							null, 
							null, 
							function(sponsorBMList){
								if(sponsorBMList != null && !Ext.isEmpty(sponsorBMList) && 
									sponsorBMList.length > 0){
									Ext.Msg.show({
					 					msg: "This sponsor already exists..!",
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
		
		this.sponsorDescriptionTxf = new Ext.form.TextField({
			fieldLabel: 'Description',
	        name: 'sponsorDesc',
	        anchor: '90%'
		});
		
		this.basicDetail = new Ext.form.FormPanel({
			monitorValid: true,
			items: [
				{
					layout : 'column',
					defaults: {border: false,layout: 'form'},
					items: [
						{	columnWidth : .50,
						 	items:this.sponsorNameTxf
					 	},
				 		{
				 			columnWidth : .50,
				    		items:this.sponsorDescriptionTxf
			    		},
				    	{	columnWidth : .50,
				    		items:{
						        fieldLabel: 'Type',
						        xtype: 'combo',
						        name: 'sponsorTypeCd',
						        store:loadSponsorTypeCbx(),
								mode:'local',
								triggerAction: 'all',
								displayField:'description',
								valueField:'code',
						        anchor:'90%',
						        forceSelection : true,
						        allowBlank:false,
						        required:true
						    }
					    },{
			    			columnWidth : .50,
		    				items:{
						        fieldLabel: 'Credit class',
						        xtype: 'combo',
						        name: 'creditClassCd',
								store:loadCreditClassCbx(),
								mode:'local',
								triggerAction: 'all',
								displayField:'description',
								valueField:'code',
						        anchor:'90%',
						        forceSelection : true
						    }
					    },{
					    	columnWidth : .5,
					    	items : {
						        fieldLabel: 'Account number',
						        xtype: 'textfield',
						        name: 'accountNumber',
						        anchor: '90%'
						     }   
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
					this.insurerSponsorAssoc.getPanel(),
					{
						title:'SLA detail',
						autoScroll:true,
						autoHeight:true,
						items:this.slaPanel.getPanel()
					}
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
	saveSponsor : function(config){
		if(this.basicDetail.getForm().isValid()){
			var assocInsurerRows = this.insurerSponsorAssoc.infoGrid.getStore().data.items;
			var slaRows = this.slaPanel.infoGrid.getStore().data.items;
			var basicDetails = this.basicDetail.getForm().getValues();
			var contactDetails = this.contactDetailPanel.getPanel().getForm().getValues();
			var insurerBMList = new Array();
			var sponsorSlaBMList = new Array();
			var mainThis = this;

			for( var i = 0; i<assocInsurerRows.length; i++ ) {
				var currRow = assocInsurerRows[i].data;
				var insurer = {
					insurerName:currRow.insurerName,
				    effectiveFromDate:Ext.isEmpty(currRow.effectiveFromDate)?null:currRow.effectiveFromDate, 
				    effectiveToDate:Ext.isEmpty(currRow.effectiveToDate)?null:currRow.effectiveToDate, 
					createdBy:authorisedUser.userName
				}
				insurerBMList.push(insurer);
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
			
			for( var i = 0; i<slaRows.length; i++ ) {
				var currRow = slaRows[i].data;
				var slaBM = {
					sponsorName:basicDetails.sponsorsName,
					activityType:{code:currRow.activityTypeCd},
				    slaPeriod:currRow.slaPeriod, 
				    slaUnit:currRow.slaUnit 
				}
				sponsorSlaBMList.push(slaBM);
			}
			
			var sponsorDetailsBM = {
				sponsorsName : basicDetails.sponsorsName,
				sponsorType :  {code:basicDetails.sponsorTypeCd},
				creditClass :  {code:basicDetails.creditClassCd},
				sponsorDesc :  basicDetails.sponsorDesc,
				accountNumber : basicDetails.accountNumber,
				contactDetailsBM : contactDetailsBM,
				createdBy :authorisedUser.userName,
				modifiedBy : authorisedUser.userName,
				sponsorInsurerAssociationBMList :insurerBMList, 
				sponsorSlaBMList : sponsorSlaBMList
			};
			
			if(config.mode == "edit"){
				InsuranceManager.modifyClaimSponsor(sponsorDetailsBM,function(){
					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
					Ext.ux.event.Broadcast.publish('closeSponsorPanel');
				});
			}else{
				InsuranceManager.createClaimSponsor(sponsorDetailsBM,{
					callback: function(sponsorDetailBM){
						if(!Ext.isEmpty(sponsorStore)){
							sponsorStore.reload();
						}
						if(sponsorDetailBM != null && !Ext.isEmpty(sponsorDetailBM)){
							if(mainThis.isPopUp){
								layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
								Ext.ux.event.Broadcast.publish('closeSponsorPanel');
							}else{
								mainThis.resetSponsor();
							}
						}
//						sponsorStore.reload();
					}}
			    );
			}
			
		} else {
			alertWarning("Enter all the required fields..!");
		  	return;
		}
	},
	resetSponsor : function(){
		this.insurerSponsorAssoc.infoGrid.getStore().removeAll();
		this.insurerSponsorAssoc.assocPanel.getForm().reset();
		this.slaPanel.infoGrid.getStore().removeAll();
		this.slaPanel.slaDetailPanel.getForm().reset();
		this.basicDetail.getForm().reset();
		this.contactDetailPanel.getPanel().getForm().reset();
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
		
		var assocInsurerRows = config.sponsorInsurerAssociationBMList;
		var assocInsurerRecord = this.insurerSponsorAssoc.infoGrid.getStore().recordType;
		var sponsorSlaRows = config.sponsorSlaBMList;
		var sponsorSlaRecord = this.slaPanel.infoGrid.getStore().recordType;
		
		if(assocInsurerRows!=null && assocInsurerRows.length>0 ){
			for(var i = 0; i<assocInsurerRows.length; i++){
				var insurerRecord = new assocInsurerRecord({
					sponsorName: assocInsurerRows[i].sponsorName,
					sponsorNameDesc: '',
					insurerName: assocInsurerRows[i].insurerName,
					insurerNameDesc: '',
					effectiveFromDate: assocInsurerRows[i].effectiveFromDate,
					effectiveToDate: assocInsurerRows[i].effectiveToDate,
					createdBy: assocInsurerRows[i].createdBy
				});
				 this.insurerSponsorAssoc.infoGrid.getStore().add(insurerRecord);
			}
		}
		
		if(sponsorSlaRows!=null && sponsorSlaRows.length>0 ){
			for(var i = 0; i<sponsorSlaRows.length; i++){
				var slaRecord = new sponsorSlaRecord({
					sponsorName: sponsorSlaRows[i].sponsorName,
					activityTypeCd: sponsorSlaRows[i].activityType.code,
					activityTypeDesc: sponsorSlaRows[i].activityType.description,
					slaPeriod: sponsorSlaRows[i].slaPeriod,
					slaUnit: sponsorSlaRows[i].slaUnit
				});
				 this.slaPanel.infoGrid.getStore().add(slaRecord);
			}
		}
	},
	getFocus : function(config){
		if(config.mode == "edit"){
			this.sponsorDescriptionTxf.focus();
		}else{
			this.sponsorNameTxf.focus();
		}
	}
});