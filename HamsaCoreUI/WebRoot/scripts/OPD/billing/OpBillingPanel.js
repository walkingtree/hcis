Ext.namespace("OPD.billing");

OPD.billing.OpBillingPanel = Ext.extend(Object, {
	constructor : function() {
	
		this.isFromRequisition = null;
		this.receiptConfig = {};//Object which holds data to create issue
		Ext.QuickTips.init();
		
		var mainThis = this;
		
		this.referenceNbr = null;
		this.referenceType = null;
		
	 	this.newPatientChbx = new Ext.form.Checkbox({
	 		boxLabel: "New Patient ?", 
	 		labelSeparator:'',
        	name: 'newPatient'
	 	});

		this.patientIdNrF = new Ext.form.NumberField({
			fieldLabel:opBillingMsg.patientid,
			name:'patientId',
			anchor:'98%'
		});
		
		this.patientIdNrF.on('change', function(comp, newValue, oldValue){
			var patientId = comp.getValue();
		  	if(!Ext.isEmpty(patientId)){
		  		if(newValue !== oldValue){
			  		this.setPatientDetails(parseInt(patientId,10));
		  		}
		  	} else {

		  		//There is no patient id now so clear receiptConfig
		  		this.receiptConfig={};
		  		//reset the form
		  		this.resetBtnHandler();
				this.createReceiptBtn.disable();
		  	}
		},this);
		
		this.appointmentNbrF = new Ext.form.NumberField({
			fieldLabel: opBillingMsg.appointmentNumber,
			name:'appointmentNumber',
			anchor: '98%',
			disabled:true
		
		});
		

		this.stateStoreForCountry = new Ext.data.Store({
		    proxy: new Ext.data.DWRProxy(DataModelManager.getStateWithCountry, true),
		    reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, Ext.data.Record.create([
							  {name: "code", type: "string"},
							  {name: "description", type: "string"}
							])),
		    remoteSort: true
	 	 });
	 	 
	 	 this.countryStore = new Ext.data.Store({
	 	 	 proxy: new Ext.data.DWRProxy(DataModelManager.getCountry, true),
		   	 reader: new Ext.data.ListRangeReader(
		    	{totalProperty:'totalSize'}, record()),
		    remoteSort: true
	  	});
	  	this.countryStore.load({params:{start:0, limit:9999}, arg:[]});

		this.titleCbx =  new wtccomponents.WTCComboBox({
			fieldLabel : opBillingMsg.title,
			hiddenName : 'title',
			anchor:'80%',
			store : loadTitleCbx(),
			disabled:true
		});

		this.patientFirstNameTxf = new Ext.form.TextField({
			fieldLabel:opBillingMsg.firstName,
			name:'firstName',
			anchor:'98%',
			allowBlank: false,
			required: true,
			disabled:true
		});
		
		this.patientMiddleNameTxf = new Ext.form.TextField({
			fieldLabel:opBillingMsg.middleName,
			name:'middleName',
			anchor:'98%',
			disabled:true
		});
		
		this.patientLastNameTxf = new Ext.form.TextField({
			fieldLabel:opBillingMsg.lastName,
			name:'lastName',
			anchor:'98%',
			disabled:true
		});
		
		this.genderCbx = new wtccomponents.WTCComboBox({
			fieldLabel : "Gender",
			hiddenName : 'gender',
			store : loadGenderCbx(),
			disabled:true
		});

		this.patientDobDt = new Ext.form.WTCDateField({
			fieldLabel:opBillingMsg.dateofbirth,
			name:'dateOfBirth',
			anchor:'98%',
			disabled:true
		});

		this.streetTxf = new Ext.form.TextField({
			fieldLabel: "Street",
			name:'street',
			anchor:'98%',
			disabled:true
		});
		
		this.cityOrDistrictTxf = new Ext.form.TextField({
			fieldLabel: opBillingMsg.cityDistrict,
			name:'city',
			anchor:'98%',
			disabled:true
		});
		
		this.manageAppointmentRecordType = Ext.data.Record.create([
		                                               			{ name : "serialNo", type : "int" }, 
		                                               			{ name : "appointmentNumber", type : "int" }, 
		                                               			{ name : "patientName", type : "string" }, 
		                                               			{ name : "patientFirstName", type : "string" }, 
		                                               			{ name : "patientLastName", type : "string" }, 
		                                               			{ name : "patientMiddleName", type : "string" }, 
		                                               			{ name : "patientId", type : "int" }, 
		                                               			{ name : "appointmentDate", type : "date" , convert:function(b){return b.format("d-m-Y");}}, 
		                                               			{ name : "appointmentStartTime", type : "string" }, 
		                                               			{ name : "appointmentEndTime", type : "string" }, 
		                                               			{ name : "appointmentStatus", mapping : 'appointmentStatus.description', type : "string" }, 
		                                               			{ name : "appointmentStatusCode", mapping : 'appointmentStatus.code', type : "string" }, 
		                                               			{ name : "amount", type : "string" }, 
		                                               			{ name : "speaciality", mapping : 'speaciality.description', type : "string" }, 
		                                               			{ name : "speacialityCode", mapping : 'speaciality.code', type : "string" }, 
		                                               			{ name : "bookingType", mapping : 'bookingType.description', type : "string" }, 
		                                               			{ name : "bookingTypeCode", mapping : 'bookingType.code', type : "string" }, 
		                                               			{ name : "primaryDoctor", mapping : 'primaryDoctor.description', type : "string" }, 
		                                               			{ name : "primaryDoctorCode", mapping : 'primaryDoctor.code', type : "string" },
		                                               			{ name : "rosterCode", type : "int" },
		                                               			{ name : "remarks", type : "string" }
		                                               		]);
		
		
		this.servicesStore = new Ext.data.Store({
			proxy : new Ext.data.DWRProxy(
					AppointmentManagementController.getAppointments, true),
			reader : new Ext.data.ListRangeReader({id : 'appointmentNumber',totalProperty : 'totalSize'}, 
												  this.manageAppointmentRecordType),
		    sortInfo: {
			    field: 'appointmentNumber',
			    direction: 'DESC' 
			}
		},this);
		
		this.patientIdNrF.on('change', function(comp){
			if(!Ext.isEmpty(comp.getValue())){
				this.appointmentServices.enable();
			}else{
				this.appointmentServices.disable();
			}
			if(!Ext.isEmpty(this.servicesStore)){
				this.servicesStore.removeAll();
				this.appointmentServices.clearValue();
			}
			var patientid = comp.getValue()+"";
			this.servicesStore.load({params:{start:0, limit:999}, arg:[null,null,patientid,null,null,null,null,null,null]});
		},this);
		this.servicesStore.on('load',function(){
			var mainThis = this;
			if(!Ext.isEmpty(this.servicesStore)&& this.servicesStore.getCount()>0){
				this.appointmentServices.setValue(this.servicesStore.getAt(0).data.appointmentNumber);
				if( !Ext.isEmpty( this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore())){
					this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore().removeAll();
					this.assignedServicePnl.assignedServiceGridPnl.clearRecordArray();//This array stores data for refill the grid with requisiton records 
				}
				if(!Ext.isEmpty(this.appointmentServices.getValue())){
					
					ServiceManager.getAssignedServiceAndPackageList(this.appointmentServices.getValue(),
							configs.referenceTypeForOPD, 
							true,
							function(assignedServiceAndPackageBM){
						if( assignedServiceAndPackageBM!=null && assignedServiceAndPackageBM.length >0 ){
							
							for( var k = 0; k < assignedServiceAndPackageBM.length; k++ ){
								mainThis.serviceOrderNumber = assignedServiceAndPackageBM[k].serviceOrderNumber;
								mainThis.assignedServicePnl.assignedServiceGridPnl.loadData(assignedServiceAndPackageBM[k]);
							}
						}
					});
					this.serviceOrderNumber = mainThis.serviceOrderNumber;
				}
			}
			
			
		},this);
		
		var servicesTpl=new Ext.XTemplate(
				'<tpl for=".">',
				'<div class="service-item"><table><tr><td><b><h1>{appointmentNumber}<h1></b></td><td></td>',
			    '<td><b>Doctor:</b> {primaryDoctor}</td></tr></table></div>',
			    '</tpl>'
			);

		
		this.appointmentServices =  new Ext.form.ComboBox({
			fieldLabel : 'AppointmentId',
			displayField: 'appointmentNumber',
			valueField : 'appointmentNumber',
			typeAhead: false,
		    triggerAction: 'all',
		    disabled : true,
			store : this.servicesStore,
			tpl: servicesTpl,
			anchor:'98%',
			loadingText: 'Searching...',
			itemSelector: 'div.service-item',
			mode:'local'

		});
		
		this.appointmentServices.on('select',function(comp){
			var mainThis = this;
			if( !Ext.isEmpty( this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore())){
				this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore().removeAll();
				this.assignedServicePnl.assignedServiceGridPnl.clearRecordArray();//This array stores data for refill the grid with requisiton records 
			}
			if(!Ext.isEmpty(comp.getValue())){
				
				ServiceManager.getAssignedServiceAndPackageList(comp.getValue(),
																configs.referenceTypeForOPD, 
																true,
																function(assignedServiceAndPackageBM){
					if( assignedServiceAndPackageBM!=null && assignedServiceAndPackageBM.length >0 ){
						for( var k = 0; k < assignedServiceAndPackageBM.length; k++ ){
							mainThis.serviceOrderNumber = assignedServiceAndPackageBM[k].serviceOrderNumber;
							mainThis.assignedServicePnl.assignedServiceGridPnl.loadData(assignedServiceAndPackageBM[k]);
						}
					}
				});
				this.serviceOrderNumber = mainThis.serviceOrderNumber;
			}
			
		},this);
		
		this.countryCbx =  new wtccomponents.WTCComboBox({
			fieldLabel : opBillingMsg.country,
			hiddenName : 'country',
			store : this.countryStore,
			allowBlank: false,
			required: true,
			disabled:true 
		});
		
		this.stateCbx =  new wtccomponents.WTCComboBox({
			fieldLabel : opBillingMsg.state,
			hiddenName : 'state',
			store : this.stateStoreForCountry,
			disabled:true
		});
		
		this.pincodeNBr =  new Ext.form.NumberField({
			fieldLabel : opBillingMsg.pincode,
			name : 'pincode',
			anchor:'98%',
			disabled:true
		});
		
		this.phoneNBr =  new Ext.form.WTCPhoneField({
			fieldLabel : opBillingMsg.phoneNumber,
			name : 'phoneNumber',
			anchor:'98%',
			disabled:true
		});
		
		this.mobileNBr =  new Ext.form.WTCPhoneField({
			fieldLabel : opBillingMsg.mobileNumber,
			name : 'mobileNumber',
			anchor:'98%',
			disabled:true
		});
		
		this.referralPnl = new Ext.Panel({
			layout:'column',
			defaults:{columnWidth:.5}
		});
		
		this.inputPanel = new Ext.Panel({
			layout : 'column',
			labelAlign : 'left',
			defaults : {
				layout : 'form'
			},
			style: 'padding-top:5px;',
			items : [
				{
					columnWidth:0.5,
					items : this.patientIdNrF
				},
				{
					columnWidth:0.5,
					items:this.appointmentServices
				}
			]
		});
		
		this.existingPatientPanel = new Ext.Panel({
			width:'94%',
			layout : 'column',
			labelAlign : 'left',
			defaults : {
				columnWidth:1,
				layout : 'form'
			},
			height : '100%',
			items : [
				{
					labelWidth:0.01,
					columnWidth:.2,
					items : this.newPatientChbx
				},
				{	columnWidth:.8,
					items :this.inputPanel
				}
			]
		});

		this.generateBillBtn = new Ext.Button({
			iconCls:'bill-icon',
			tooltip:opBillingMsg.generateBill,
			text:opBillingMsg.generateBill,
			scope:this,
			disabled: true,
			handler:function(){
				this.generateBillBtnHandler();
			}
		});
		
		this.generateBillAndReceiptBtn = new Ext.Button({
			tooltip:opBillingMsg.generateBillAndReceipt,
			text:opBillingMsg.generateBillAndReceipt,
			scope:this,
			handler:function(){
				this.generateBillBtnHandler(true);
			}
		});
			
		this.resetBtn = new Ext.Button({
			iconCls:'reset-icon',
			tooltip:opBillingMsg.retetData,
			text:opBillingMsg.reset,
			scope:this,
			handler: function(){
				var thisForReset = this;
				Ext.Msg.show({
				   msg: opBillingMsg.resetMessage,
				   buttons: Ext.Msg.YESNO,
				   fn: function(btn){
				   	if(btn==configs.yes){
				   		thisForReset.resetBtnHandler();
				   		thisForReset.newPatientChbx.setValue( false );
				   	}
				   },
				   icon: Ext.MessageBox.QUESTION
				});
				
			}
		});
			
		this.createReceiptBtn = new Ext.Button({
				text: opBillingMsg.createReceipt,
				scope: this,
				disabled: true,
				tooltip: opBillingMsg.createReceipt,
				handler: function(){
					showReceiptWindow( this.receiptConfig );
					this.createReceiptBtn.disable();
				}
		});

		this.patientContactDetailsPnl = new Ext.form.FieldSet({
			title : "Patient detail",
			width:'94%',
			layout : 'column',
			labelAlign : 'left',
			collapsed: true,
			defaults : {
				layout : 'form',
				columnWidth:.33
			},
			height : '100%',
			collapsible: true,
			items : [
				{
					items : this.titleCbx
				},
				{
					items : this.patientFirstNameTxf
				},	
				{
					items : this.patientMiddleNameTxf
				},
				{
					items : this.patientLastNameTxf
				},
				{
					items : this.genderCbx
				},
				{
					items : this.patientDobDt
				},
				{
					items : this.streetTxf
				},
				{
					items : this.cityOrDistrictTxf
				},
				{
					items : this.countryCbx
				},
				{
					items : this.stateCbx
				},
				{
					items :this.pincodeNBr
				},
				{
					items : this.phoneNBr
				},
				{
					items : this.mobileNBr
				},
				{
					columnWidth:.66,
					items : this.referralPnl
				}
			]
		});
		
		this.assignedServicePnl = new OPD.billing.AssignedServicePanel();
		 
		 this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			buttons:[
			    this.generateBillAndReceiptBtn,
				this.generateBillBtn ,
				this.resetBtn,
				this.createReceiptBtn
			]
		});

		this.OpBillingMainPanel = new Ext.form.FormPanel({
			width : '99%',
			autoHeight : true,
			autoScroll : true,
			layout : 'column',
			monitorValid: true,
			items : [
				{
					columnWidth : 1,
					items : this.existingPatientPanel
				},
				{
					columnWidth : 1,
					items :this.patientContactDetailsPnl
				},
				{
					columnWidth : 1,
					title:opBillingMsg.assignementOfServices,
					items : this.assignedServicePnl.getPanel()
				},
				{
					columnWidth : 1,
					items:this.buttonPanel
				}
			]
		});
		
		this.newPatientChbx.addListener('check',
			function(comp, checked) {
				this.inputPanel.setVisible(!checked);
				
				this.titleCbx.setDisabled(!checked);
				this.patientFirstNameTxf.setDisabled(!checked);
				this.patientMiddleNameTxf.setDisabled(!checked);
				this.patientLastNameTxf.setDisabled(!checked);
				this.genderCbx.setDisabled(!checked);
				this.patientDobDt.setDisabled(!checked);
				this.streetTxf.setDisabled(!checked);
				this.cityOrDistrictTxf.setDisabled(!checked);
				this.countryCbx.setDisabled(!checked);
				this.stateCbx.setDisabled(!checked);
				this.pincodeNBr.setDisabled(!checked);
				this.phoneNBr.setDisabled(!checked);
				this.mobileNBr.setDisabled(!checked);

				this.patientIdNrF.reset();
				this.appointmentServices.reset();
				
				this.titleCbx.reset();
				this.patientFirstNameTxf.reset();
				this.patientMiddleNameTxf.reset();
				this.patientLastNameTxf.reset();
				this.genderCbx.reset();
				this.patientDobDt.reset();
				this.streetTxf.reset();
				this.cityOrDistrictTxf.reset();
				this.countryCbx.reset();
				this.stateCbx.reset();
				this.pincodeNBr.reset();
				this.phoneNBr.reset();
				this.mobileNBr.reset();
				
				//Fire change event so that actions on value change will take place. i.e. resetting receiptObj etc
				this.patientIdNrF.fireEvent("change",this.patientIdNrF,this.patientIdNrF.getValue(),this.patientIdNrF.getRawValue());
				mainThis.assignedServicePnl.assignedServiceGridPnl.resetData();

				if( checked ){
					this.patientContactDetailsPnl.expand();
	
					this.referralTypeCbx = new wtccomponents.WTCComboBox({
						fieldLabel : opBillingMsg.referralType,
						hiddenName : 'referralType',
						store : getReferralTypes(),
						listeners : {
							select : function ( comp , record ,index ){
								this.referralNameCbx.clearValue();
								this.loadReferralNameForType( comp , record ,index );
							},
							scope:this
						}
					});
					
					this.referralNameCbx = new wtccomponents.WTCComboBox({
						fieldLabel : opBillingMsg.referralName,
						hiddenName : 'referralName',
						store : getReferralNameForType()
					});
					
					this.referralPnl.add({layout:'form',items:this.referralTypeCbx});
					this.referralPnl.add({layout:'form',items:this.referralNameCbx});
					this.referralPnl.doLayout();
					
					this.titleCbx.focus();
					
					setCbxDefaultValue( this.countryCbx );
					
				}else{
					if(!Ext.isEmpty(this.referralPnl.items)){
						this.referralPnl.removeAll();
					}
					this.patientIdNrF.focus();
					this.patientContactDetailsPnl.collapse();
				}
			},this
		);

		this.countryCbx.on('select',function(comp, record, index){
			this.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[record.data.code]});
			this.stateCbx.clearValue();
		},this);

		Ext.ux.event.Broadcast.subscribe('getFocusForFirstElementInOpBilling', function(){
			this.patientIdNrF.focus();
		},this, null, this.getPanel());
		
		this.OpBillingMainPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.generateBillBtn.enable();
			} else {
				this.generateBillBtn.disable();
			}
		}, this);
		

		
	},
	
	getPanel : function() {
		return  this.OpBillingMainPanel;
	},
	
	setPatientDetails: function(patientId){
		var args = arguments;
		var mainThis = this;
		PatientManagementController.getPatientDetails(patientId, function(patientLiteBm){
			if(!Ext.isEmpty(patientLiteBm)){
				var patientDetails = {
					patientId: patientLiteBm.patientId,
					gender: !Ext.isEmpty(patientLiteBm.gender)?patientLiteBm.gender.code:'',
					title: !Ext.isEmpty(patientLiteBm.title)?patientLiteBm.title.code:'',
					firstName: patientLiteBm.firstName,
					middleName: patientLiteBm.middleName,
					lastName: patientLiteBm.lastName,
					dateOfBirth: patientLiteBm.dateOfBirth,
					street: !Ext.isEmpty( patientLiteBm.contactDetailsBM)? patientLiteBm.contactDetailsBM.street:'',
					city: !Ext.isEmpty( patientLiteBm.contactDetailsBM)? patientLiteBm.contactDetailsBM.city:'',
					pincode: !Ext.isEmpty( patientLiteBm.contactDetailsBM)? patientLiteBm.contactDetailsBM.pincode:'',
					phoneNumber: !Ext.isEmpty( patientLiteBm.contactDetailsBM)? patientLiteBm.contactDetailsBM.phoneNumber:'',
					mobileNumber: !Ext.isEmpty( patientLiteBm.contactDetailsBM)? patientLiteBm.contactDetailsBM.mobileNumber:''
				}
				mainThis.OpBillingMainPanel.getForm().setValues(patientDetails);
				
				if(!Ext.isEmpty( patientLiteBm.contactDetailsBM)&& 
					!Ext.isEmpty( patientLiteBm.contactDetailsBM.country)){
						mainThis.countryCbx.setValue(patientLiteBm.contactDetailsBM.country.code);
						mainThis.stateStoreForCountry.load({params:{start:0, limit:9999}, arg:[patientLiteBm.contactDetailsBM.country.code]});
						
						mainThis.stateStoreForCountry.on('load',function(){
							if(!Ext.isEmpty(patientLiteBm.contactDetailsBM.state)){
								mainThis.stateCbx.setValue(patientLiteBm.contactDetailsBM.state.code);
							}else{
								mainThis.stateStoreForCountry.removeAll();
							}
							mainThis.stateStoreForCountry.events['load'].clearListeners();
						},mainThis);
				}
				
				// Reset the appointment number field.
				mainThis.appointmentServices.setValue('');
				
				// Clear the grid.
				if( args[1] != "isFromRequsition"){
					mainThis.assignedServicePnl.assignedServiceGridPnl.resetData();
				}
				
				//Got patientId now enable issue recieptBtn and set issue receipt config
				
				mainThis.receiptConfig.selectedAccountHolderId = patientId;
				
				mainThis.receiptConfig.makeReadOnly = true;
				
				mainThis.createReceiptBtn.enable();
												
			}else{
				Ext.Msg.show({
				   msg: msg.patientnotexist,
				   buttons: Ext.Msg.OK,
				   icon: Ext.MessageBox.INFO
				});
				mainThis.OpBillingMainPanel.getForm().reset();
				mainThis.createReceiptBtn.disable();
				return;
			}
		
		});
	
	},
	
//	getAppointmentDetailsWindow: function( patientId ){
//		this.appointmentGrid = new OPD.manageAppointment.ManageAppointmentGrid({isSingleSelection: true}).getPanel();
//		
////		this.appointmentGrid  = getManageAppointmentsPnl().items.items[1];
//		
//		this.getServicesBtn = new Ext.Button({
////			iconCls:
//			text:opBillingMsg.getServices,
//			scope:this,
//			handler: function(){
//				this.getServicesBtnHandler();
//				
//			}
//		});
//		this.cancelBtn = new Ext.Button({
//			iconCls:'cancel_btn',
//			text:opBillingMsg.cancel,
//			scope:this,
//			handler: function(){
//				this.cancelBtnHandler();
//			}
//		});
//		
//		this.appointmentGrid.getStore().load({params : {start : 0,limit : 10},
//			arg : [ null,null,numberToString( patientId ),null,null,null,null,null,null]
//		});
//		
//		
//		this.window = new Ext.Window({
//		   height:'70%',
//		   width:'100%',
//		   modal:true,
//		   closeAction:'hide',
//		   title:opBillingMsg.appointmentDetails,
//		   resizable:false,
//		   items:[this.appointmentGrid],
//		   buttons:[this.getServicesBtn,this.cancelBtn]
//		});
//		
//		this.appointmentGrid.getStore().on('load', function(){
//			if( this.appointmentGrid.getStore().getCount() > 0 ){
//				var patientName = this.appointmentGrid.getStore().getAt(0).data.patientName;
//				this.window.setTitle( opBillingMsg.appointmentDetails+" of "+patientName);
//				this.window.show(); 
//			}else{
//				Ext.Msg.show({
//					msg: ' No appointment for  patientID:'+patientId,
//					icon : Ext.MessageBox.INFO,
//					buttons: Ext.Msg.OK,
//					fn: function(){}
//				});
//			}
//			this.appointmentGrid.getStore().events['load'].clearListeners();
//		},this);
//		
//	},
	
	generateBillBtnHandler: function(bothFunction){
		var mainThis = this;
		var data = this.OpBillingMainPanel.getForm().getValues();
		
		if(  mainThis.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore().getCount() == 0 ){
			info(" please assign atleast one service and retry!..");
			return;
		}
		var assignedServiceAndPackageBm = mainThis.assignedServicePnl.assignedServiceGridPnl.getData();
		assignedServiceAndPackageBm.createdBy = authorisedUser.userName;
				
		if(mainThis.newPatientChbx.getValue()){
			var personalDetails = this.getPatientDetails( data );
			var currentContactDetails = this.getContactDetails( data );
			var permanentContactDetails = this.getContactDetails( data );
			var patientAditionalDetails = this.getPersonalDetailsAdditional( data );
			var createdBy = getAuthorizedUserInfo().userName;
			PatientManagementController.registerPatient(  personalDetails,// personal details bm
															patientAditionalDetails,// patient additional details bm
															currentContactDetails,// current contact details bm
															permanentContactDetails,// permanent contact details bm
//															{},// emergency primary contact details bm
//															{},// emergency secodary contact details bm
															null,//  list of allergies	
															null,// list of immunizations
															null,// other details bm
															true,// current address same as permanent
//															false,// current address same as emergency primary
//															false,// current address same as emergency secondary
															createdBy,
															function( patientLiteBm ){
																	assignedServiceAndPackageBm.patientId = patientLiteBm.patientId;
																	assignedServiceAndPackageBm.referenceNumber = patientLiteBm.patientId;
																	assignedServiceAndPackageBm.referenceType = configs.PatientRegistrationType;
																	mainThis.commitNewServices(patientLiteBm.fullName, assignedServiceAndPackageBm);
															}
														);			
		} else {
			if( Ext.isEmpty(data.patientId )){
				info(" please provide patient id or select as a new patient");
				return;
			}
			if( Ext.isEmpty( mainThis.appointmentServices.getValue() )){
				assignedServiceAndPackageBm.referenceNumber = parseInt( data.patientId,10);
				assignedServiceAndPackageBm.referenceType = configs.PatientRegistrationType;
			}else{
				assignedServiceAndPackageBm.referenceNumber = parseInt(mainThis.appointmentServices.getValue(),10);
				assignedServiceAndPackageBm.referenceType = configs.referenceTypeForOPD;
			}
			
			assignedServiceAndPackageBm.patientId = data.patientId;
			
			var fullName = this.patientFirstNameTxf.getValue() + ' ' +  this.patientMiddleNameTxf.getValue() + ' ' + this.patientLastNameTxf.getValue();
			
			mainThis.commitNewServices(fullName, assignedServiceAndPackageBm, bothFunction);
		}
	},
	
	commitNewServices : function(fullName, assignedServiceAndPackageBm,bothFunction ){
		var tmpThis = this;
		
		var chkValue = this.newPatientChbx.getValue();
		
		if( !Ext.isEmpty( this.referenceNbr )){
			assignedServiceAndPackageBm.referenceNumber = this.referenceNbr;
		}
		
		if( !Ext.isEmpty( this.referenceType )){
			assignedServiceAndPackageBm.referenceType = this.referenceType;
		}
		
		var accountId, clientName, referenceNbr;
		accountId = 'PAT-' + assignedServiceAndPackageBm.patientId;
		PatientManager.getBusinessPartnerId( assignedServiceAndPackageBm.patientId, {
			callback: function( accountId ){
				referenceNbr = assignedServiceAndPackageBm.referenceNumber;
				clientName =assignedServiceAndPackageBm.referenceType;
		
				ServiceManager.assignSvcAndPackages( assignedServiceAndPackageBm, function(serviceOrderNumber){

				BillManager.runBill( accountId, clientName, referenceNbr, 
								 function( billObjectBM ){
									  var billNbr = {billNbr: billObjectBM.billNumber};
									  if(bothFunction==true){
										  var createdBy = getAuthorizedUserInfo().userId;
										  AccountantManager.createReceipt( assignedServiceAndPackageBm.patientId, 
												  							billObjectBM.billAmount, 
												  							null,
												  							createdBy,
												  							billObjectBM.billNumber,{
																				callback: function(){
										        				
											  								CoreReportManager.generateReport("OPDPatientBill" , billNbr, function(reportURL){
											  								window.open(getBaseURL() + reportURL);
											  });
																				},
																				scope:this
											});
									  }else{
										  CoreReportManager.generateReport("OPDPatientBill" , billNbr, function(reportURL){
											  window.open(getBaseURL() + reportURL);
										  });
										  tmpThis.createReceiptBtn.enable();  
									  }
									 	
										  tmpThis.receiptConfig ={
											   		selectedEntityTypeId: configs.Patient,
											   		selectedEntityTypeName:configs.hospitalPatient,
											   		selectedAccountHolderId:assignedServiceAndPackageBm.patientId,
											   		selectedAccountHolderName: fullName,
											   		selectedReceiptDate: billObjectBM.billDueDate,
											   		selectedAmount: billObjectBM.billAmount
											   };
										   
										  tmpThis.resetBtnHandler();
										  if( !Ext.isEmpty( tmpThis.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore() )){
											  
											  tmpThis.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore().removeAll();
										  }
								});
				});	
			},
			scope: this
		});
	},
	
	resetBtnHandler: function(){
		if( Ext.isEmpty( this.isFromRequisition )){
			// If we are editing assigned service from the requisition order then isFromRequisition will be initialized.
			this.patientIdNrF.reset();
			this.appointmentServices.reset();
			
			this.titleCbx.reset();
			this.patientFirstNameTxf.reset();
			this.patientMiddleNameTxf.reset();
			this.patientLastNameTxf.reset();
			this.genderCbx.reset();
			this.patientDobDt.reset();
			this.streetTxf.reset();
			this.cityOrDistrictTxf.reset();
			this.countryCbx.reset();
			this.stateCbx.reset();
			this.pincodeNBr.reset();
			this.phoneNBr.reset();
			this.mobileNBr.reset();
			
			this.assignedServicePnl.assignedServiceDetailsPnl.resetData();
			this.assignedServicePnl.assignedServiceGridPnl.clearRecordArray();
			this.assignedServicePnl.assignedServiceGridPnl.resetData();
		}else{
			
			this.assignedServicePnl.assignedServiceGridPnl.setRecordArrayToGrid();
		}	
		
	},
	
	getServicesBtnHandler: function(){
		var mainThis = this;
		if( !Ext.isEmpty( this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore())){
			this.assignedServicePnl.assignedServiceGridPnl.getPanel().getStore().removeAll();
			this.assignedServicePnl.assignedServiceGridPnl.clearRecordArray();//This array stores data for refill the grid with requisiton records 
		}
		if(this.appointmentGrid.getSelectionModel().getCount() == 1){
			
			var selectedRows = this.appointmentGrid.getSelectionModel().getSelections();
			this.appointmentServices.setValue(selectedRows[0].data.appointmentNumber);	
			ServiceManager.getAssignedServiceAndPackageList(selectedRows[0].data.appointmentNumber,
															configs.referenceTypeForOPD, 
															true,
															function(assignedServiceAndPackageBM){
				if( assignedServiceAndPackageBM!=null && assignedServiceAndPackageBM.length >0 ){
					for( var k = 0; k < assignedServiceAndPackageBM.length; k++ ){
						mainThis.serviceOrderNumber = assignedServiceAndPackageBM[k].serviceOrderNumber;
						mainThis.assignedServicePnl.assignedServiceGridPnl.loadData(assignedServiceAndPackageBM[k]);
					}
				}
			});
			this.serviceOrderNumber = mainThis.serviceOrderNumber;
			this.appointmentGrid.getStore().removeAll();
			this.window .close();
		}
		
	},
	
	cancelBtnHandler: function(){
		this.window.close();
	},
	
	getPatientDetails: function( data ){
		var personalDetails = {
			firstName: data.firstName,
			middleName: data.middleName,
			lastName: data.lastName,
			registrationDate: new Date(),
			dateOfBirth: getStringAsWtcDateFormat( data.dateOfBirth ),
			title:{code: data.title},
			gender:{code: data.gender},
			registrationType: {code: configs.registrationTypeForBilling}
		};
		return personalDetails;
	},
	
	getContactDetails: function( data ){
		var contactDetails ={
			firstName: data.firstName,
	        salutation:{code: data.title},
			middleName: data.middleName,
			lastName: data.lastName,
			street:  data.street,
			city:  data.city,
			pincode:  data.pincode,
			phoneNumber:  data.phoneNumber,
			mobileNumber:  data.mobileNumber,
			country:{code: data.country},
			state:{code: data.state},
			forEntity:{code:configs.Patient}
		};
		return contactDetails ;
	},
	
	getPersonalDetailsAdditional: function( data ){
		var personalAditionalDetails = {
			referredBy : {code :data.referralName}		
		};
		
		return personalAditionalDetails;
	},
	
	loadReferralNameForType : function(thisCombo , record ,index){
			var referralType = record.data.code;
			referralNameStore.load({params:{start:0, limit:99}, arg:[referralType]});
	},
	
	setPatientData : function( patientData ){
		if( !Ext.isEmpty(patientData) ){
			
			if( Ext.isEmpty( patientData.contactDetailsBM )){
				patientData.contactDetailsBM = { };
			}
			
			formValues = {
				firstName :  patientData.firstName,
				middleName : patientData.middleName,
				lastName : patientData.lastName,
				gender : patientData.gender,
				dateOfBirth : patientData.dateOfBirth,
				street : patientData.contactDetailsBM.street,
				city : patientData.contactDetailsBM.city,
				country : patientData.contactDetailsBM.country,
				pincode : patientData.contactDetailsBM.pincode,
				phoneNumber : patientData.contactDetailsBM.phoneNumber,
				mobileNumber : patientData.contactDetailsBM.mobileNumber
				
			};
			
			this.OpBillingMainPanel.getForm().setValues( formValues );
		}
	},
	
	onRender : function( patientId ,isFromRequsition){
		this.OpBillingMainPanel.on('render',function(){
			this.setPatientDetails( patientId , isFromRequsition);
		},this);
	}
});
