Ext.namespace("administration.referralManagement");

administration.referralManagement.AddReferral = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:true,
	monitorValid : true,
 	initComponent : function() {
 		
 		this.refCode = null;
 		
 		var mainThis = this;

		this.widgets = new administration.referralManagement.Widgets();
		this.qualificationPnl = new Ext.Panel({layout:'form'});
		
		if(this.initialConfig.mode == "edit" && this.initialConfig.referralType == "DOCTOR"){
			this.qualificationTxf = new Ext.form.TextField({
					name : 'qualification',
					fieldLabel : 'Qualification',
					anchor : '95%'
			});
			this.qualificationPnl.add(this.widgets.getQualificationTxf());
			this.qualificationPnl.doLayout();
		}
		
		this.table = new administration.referralManagement.ReferralCommisionConfigGridPanel();
		this.table.loadGridStore( this.initialConfig.mode );
		this.contactsPanel = new administration.referralManagement.ContactsPanel();
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.buttonsPanel.getSaveBtn().on("click", function(){
			this.saveBtnClicked();
		}, this);

		this.buttonsPanel.getResetBtn().on("click", function(){
			 Ext.Msg.show({
				title: msg.resettitle,
				msg: msg.resetmsg,
				icon : Ext.MessageBox.QUESTION,
				buttons: {
						yes: true,
						no: true
				},
				
				fn: function(btn) {
					switch(btn){
						case 'yes':
						mainThis.resetForm();
						break;
						case 'no':				
						break;
						}
					}
				});
		}, this);
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonsPanel.getSaveBtn().enable();
			} else {
				this.buttonsPanel.getSaveBtn().disable();
			}
		}, this); 
		
		this.widgets.getReferralTypeCombo().addListener('select',function(comp){
			if( comp.getValue() == "DOCTOR"){
				if(!Ext.isEmpty(this.qualificationPnl.items)){
					this.qualificationPnl.removeAll();
				}
				this.qualificationTxf = new Ext.form.TextField({
						name : 'qualification',
						fieldLabel : 'Qualification',
						anchor : '95%'
				});
				this.qualificationPnl.add(this.qualificationTxf);
			}else{
				this.qualificationPnl.removeAll();
			}
			
			this.qualificationPnl.doLayout();
		},this);
		
		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.5,
	            	layout : 'form',
	            	items:[this.widgets.getUniqueNameTxf()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getReferralTypeCombo()]
			    },{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.qualificationPnl /*this.widgets.getQualificationTxf()*/]
				},{
					columnWidth:0.5,
	            	layout : 'form',
					items:[this.widgets.getPreferredTimeTxf()]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.contactsPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.table]
			    },{
	            	layout : 'form',
					columnWidth:1,
					items:[this.buttonsPanel]
			    }
			    		
			]            
        });
        administration.referralManagement.AddReferral.superclass.initComponent.apply(this, arguments);

	},
	
	saveBtnClicked : function(){
		var inputValues = this.getForm().getValues();
		var commConfiguration = this.table.getData();
		var refType = this.widgets.getReferralTypeCombo().getValue();
		var tmpThis = this;		
		var commArray = new Array()
		
		for(var i = 0 ; i< commConfiguration.length ; i++){
			if( Ext.isEmpty(commConfiguration[i].commissionTypeInd) ||  
					commConfiguration[i].commissionTypeInd == null){
				Ext.Msg.show({
					msg: "Commission type ind in commission configuration <br>should not be empty..!</br>",
					icon : Ext.MessageBox.ERROR,
					buttons: Ext.Msg.OK
				});
				
				return;
			}
			var commconfig = {
				entityType : {code : commConfiguration[i].entityType.code},
				commissionTypeInd : {code : commConfiguration[i].commissionTypeInd},
				commissionValue : commConfiguration[i].commissionValue
			}
			commArray.push(commconfig);
		}
				
		
		if(!Ext.isEmpty(this.refCode)){
			var referralBM = {
			referralCode : this.refCode,
			referralName : inputValues['referralName'],
			referralType : {code : refType} ,
			qualification : inputValues['qualification'],
			preferredContactTime : inputValues['preferredTime'],
			
			referralAddress : inputValues['address'],
			city : inputValues['city'],
			country :{code : inputValues['country'] },
			state :{code : inputValues['state'] },
			pincode : inputValues['pinCode'],
			phoneNumber : inputValues['phoneNbr'],
			mobileNumber : inputValues['mobileNbr'],
			faxNumber : inputValues['faxNbr'],
			email : inputValues['emailAddress'],
			createdBy : getAuthorizedUserInfo().userName,
			
			referralCommissionList : commArray 
		};
		ReferralManager.modifyReferral(
		   referralBM,
		   function(){
			 	Ext.ux.event.Broadcast.publish('closeEditReferralPanel');
			 	layout.getCenterRegionTabPanel().remove( tmpThis , true );
//			 	tmpThis.close();
			   }
	      	);
		}
		else
		{
			var referralBM = {
				referralName : inputValues['referralName'],
				referralType :  {code : refType} ,
				qualification : inputValues['qualification'],
				preferredContactTime : inputValues['preferredTime'],
				
				referralAddress : inputValues['address'],
				city : inputValues['city'],
				country :  {code : inputValues['country'] },
				state :{code : inputValues['state'] },
				pincode : inputValues['pinCode'],
				phoneNumber : inputValues['phoneNbr'],
				mobileNumber : inputValues['mobileNbr'],
				faxNumber : inputValues['faxNbr'],
				email : inputValues['emailAddress'],
				createdBy : getAuthorizedUserInfo().userName,
				referralCommissionList : commArray 
			};
			ReferralManager.saveReferral(
			   referralBM,
			   function(referralId){
			   		if(tmpThis.initialConfig.mode === 'add'){
			   			layout.getCenterRegionTabPanel().remove( tmpThis , true );
			   			Ext.ux.event.Broadcast.publish('closeAddReferralPanel');
			   		}
	   				else if( tmpThis.initialConfig.mode === 'addReferralFromAppointment'){
		   				Ext.ux.event.Broadcast.publish('closeAddReferralPanelFromAppointment', refType ,referralId );
		   				layout.getCenterRegionTabPanel().remove( tmpThis , true );
		   			}
			   		else {
				   		tmpThis.getForm().reset();
				   		tmpThis.table.getGrid().getStore().reload();
				   	}	
			   }
	      	);
      	}
	},
	
	loadData : function(config){
		if( Ext.isEmpty(this.initialConfig.dataValues) ){
			this.initialConfig.dataValues = config;
		}
		this.refCode = config.referralCode;
		var name = config.referralName;
		var address = config.referralAddress;
		var countryCode; 
		if(!Ext.isEmpty(config.country)){
			countryCode = config.country.code;
		}
		var dataValues = {
			referralName : name,
			referralType : config.referralType.code,
			qualification : config.qualification,
			preferredTime : config.preferredContactTime,
			address : address ,
			city : config.city,
			state : Ext.isEmpty(config.state) ? '' : config.state.code,
			country :Ext.isEmpty(config.country) ? '' : config.country.code,
			pinCode : config.pincode,
			phoneNbr : config.phoneNumber,
			mobileNbr : config.mobileNumber,
			faxNbr : config.faxNumber,
			emailAddress : config.email
		}
		
		if(!Ext.isEmpty(countryCode)){
			this.contactsPanel.widgets.getStateCombo().enable();
			this.widgets.getStateCombo().store.load({params:{start:0, limit:9999}, arg:[countryCode]});
			this.widgets.getStateCombo().store.on('load',function( Store , records,  options  ){
				this.getForm().setValues(dataValues);

			},this);
		}else {
			this.getForm().setValues(dataValues);
		}
		var referralCommissionList = config.referralCommissionList;
		if(!Ext.isEmpty(referralCommissionList)){
			var commissionList = referralCommissionList;
			var recType = this.table.getGrid().getStore().recordType;
			
			if(this.table.getGrid().getStore().data.items.length > 0){
				this.table.getGrid().getStore().removeAll();	
			}
			
			for(var i = 0; i< commissionList.length; i++ ){
				var commision = commissionList[i];
				var gridRecord = new recType({
					comissionValue : commision.commissionValue,
					description : commision.entityType.description ,
					comissionType : commision.commissionTypeInd.code,
					commissionTypeDesc : commision.commissionTypeInd.description,
					code : commision.entityType.code
				});
				this.table.getGrid().getStore().add( gridRecord );
			}
		}		

	},
	
	resetForm : function(config){
		this.getForm().reset();
		if(!Ext.isEmpty(this.initialConfig.referralType)){
			this.widgets.getReferralTypeCombo().setValue( this.initialConfig.referralType );
		}	
		if(this.initialConfig.mode != 'edit'){
			this.contactsPanel.getWidgets().getStateCombo().disable();
			this.table.getGrid().getStore().load({params:{start:0, limit:99}, arg:[]});
		}
		else{	
			this.loadData(this.initialConfig.dataValues);
		}
		// NEED TO LOAD THE INITIAL DATA.
			
	},
	setReferralType : function( referralType ){
		this.initialConfig.referralType = referralType;
		this.widgets.getReferralTypeCombo().setValue( referralType );
		this.widgets.getReferralTypeCombo().disable();
		if(referralType === 'DOCTOR'){
			this.qualificationTxf = new Ext.form.TextField({
					name : 'qualification',
					fieldLabel : 'Qualification',
					anchor : '95%'
			});
			this.qualificationPnl.add(this.qualificationTxf);
		}
		else{
			this.qualificationPnl.removeAll();
		}
	}		
});

Ext.reg('add-refferals-panel', administration.referralManagement.AddReferral);
