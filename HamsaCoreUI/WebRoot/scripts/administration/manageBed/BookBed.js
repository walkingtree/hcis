Ext.namespace("administration.manageBed");

administration.manageBed.BookBed = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
		Ext.apply( this, config );
		
		this.bedMandatoryFeaturesPanel = new administration.addBed.BedFeaturesPanel({source: config.source, suffix: 'req',title: 'Required facilities', data: bedFeaturesStore.data.items});
		this.bedOptionalFeaturesPanel = new administration.addBed.BedFeaturesPanel({source: config.source, suffix: 'opt',title: 'Desired facilities', data: bedFeaturesStore.data.items});
		
		this.bedMandatoryFeaturesPanel.getPanel().on('facilitychecked',function( thisCbx , isChecked){
			this.bedOptionalFeaturesPanel.bedList[thisCbx.name].setDisabled(isChecked);
		},this);

		this.bedOptionalFeaturesPanel.getPanel().on('facilitychecked',function( thisCbx , isChecked){
			this.bedMandatoryFeaturesPanel.bedList[thisCbx.name].setDisabled(isChecked);
		},this);

		this.edoa = new Ext.form.WTCDateField({
			fieldLabel: 'EDOA',
			name: 'expectedDOA', //date of arrival
			allowBlank: false,
			required: true,
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
					mainThis.edod.setMinValue(date.getValue());
					}
				}
			}
		});
		this.edod = new Ext.form.WTCDateField({
			fieldLabel: 'EDOD',
			name: 'expectedDOD', //date of discharge
			allowBlank: false,
			required: true,
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
					mainThis.edoa.setMaxValue(date.getValue());
					}
				}
			}
		});
		
		this.bedReservationNbr = new Ext.form.NumberField({
	        fieldLabel: 'BRN',
	        name: 'brn',
	        allowBlank: false,
	        readOnly:true,
	        required: true
		});
		
		this.checkAvailabilityBtn = new Ext.Button({
	    	text: 'Check availability',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		if(this.bookBedPanel.getForm().isValid()){
	    			var bedReservationBM = this.getData();
	    			BedManager.checkBedAvaibilityForBooking(bedReservationBM, function(status){
	    				Ext.Msg.alert('Status', status.description);
	    			});
	    		}else{
	    			Ext.Msg.show({
						msg: 'Please enter valid data and try again! ',
						icon : Ext.MessageBox.ERROR,
						buttons: Ext.Msg.OK
					});
	    		}
	    		
	    	}
		});
		this.bookBedBtn = new Ext.Button({
	    	text: 'Book',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		if(this.bookBedPanel.getForm().isValid()){
	    			var bedReservationBM = this.getData();
	    			bedReservationBM.createdBy = getAuthorizedUserInfo().userName; 
	    			BedManager.bookBed(bedReservationBM, 
	    			{
	    				callback:function(retBedReservationBM){
		    				//Ext.Msg.alert('Status', retBedReservationBM.reservationStatus.description);
		    				this.bookBedPanel.getForm().reset();
		    				
		    				if(!Ext.isEmpty( this.isPopup ) && this.isPopup == true ){
		    					layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
								Ext.ux.event.Broadcast.publish("closeBedBookWindow");
							}
							if( !Ext.isEmpty( this.isFromAdmission) && this.isFromAdmission == true ){
								layout.getCenterRegionTabPanel().remove( mainThis.getPanel(), true );
								Ext.ux.event.Broadcast.publish("closeBookBedWindowFromAdmissions");
							}
		    			},
		    			scope:this
	    			} );
	    		}else{
	    			Ext.Msg.show({
						msg: 'Please enter valid data and try again! ',
						icon : Ext.MessageBox.ERROR,
						buttons: Ext.Msg.OK
					});
	    		}
	    		
	    	}
		});
		
		this.saveBtn = new Ext.Button({
			text: 'Save',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	hidden:true,
	    	handler: function() {
	    		if(this.bookBedPanel.getForm().isValid()){
	    			var bedReservationBM = this.getData(config);
	    			BedManager.modifyBedBooking(bedReservationBM, function(status){
	    				//Ext.Msg.alert('Status', status.description);
	    			});
	    		}else{
	    			Ext.Msg.show({
						msg: 'Please enter valid data and try again! ',
						icon : Ext.MessageBox.ERROR,
						buttons: Ext.Msg.OK
					});
	    		}
	    	}
		});  
		
		this.resetBtn = new Ext.Button({
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.bookBedPanel.getForm().reset();
					if(this.mode =='edit'){
						this.bookBedPanel.getForm().setValues(config);
					}
				}
		});
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[this.checkAvailabilityBtn, this.bookBedBtn,this.resetBtn,this.saveBtn]
		});				  
		
		this.getData = function(config) {
			var valuesMap = this.bookBedPanel.getForm().getValues();
			
			var bedReservationBM = {
				admissionReqNbr: valuesMap['arn'],
				unitType : {code: valuesMap['nursingUnitType'], description: valuesMap['']},
				bedType : {code: valuesMap['bedType'], description: valuesMap['']},
				reservationFromDtm : Date.parseDate(valuesMap['expectedDOA'],'d/m/Y'),
				reservationToDtm : Date.parseDate(valuesMap['expectedDOD'],'d/m/Y'),
				requiredFacilities: this.bedMandatoryFeaturesPanel.getCodeAndDescriptionData(),
				desiredFacilities: this.bedOptionalFeaturesPanel.getCodeAndDescriptionData()
			};
			if(!(Ext.isEmpty(config)) && config.mode == "edit"){
				bedReservationBM.bedReservationNbr = valuesMap['brn'];
			}
			
			return bedReservationBM;
		};
		 
		this.bedReservationPanel = new Ext.Panel();
		
		if(!(Ext.isEmpty(config))  && config.mode == "edit"){
			this.checkAvailabilityBtn.hide();
			this.bookBedBtn.hide();
			this.resetBtn.hide();
			this.saveBtn.setVisible(true);
			this.bedReservationPanel.add({ layout:'form',items:this.bedReservationNbr});
			this.bedReservationPanel.doLayout();
		}
		
		this.bookBedPanel = new Ext.form.FormPanel({
			layout : 'column',
			width : '98%',
//			style:'padding:5px',
			autoHeight:true,
			border : false,
			frame: true,
			monitorValid: true,
			defaults: {
				border: false,
				columnWidth : .33,
				labelWidth:80,
				defaults : {
					anchor : '95%'
				}
			},
			items:[{
					layout:'form',
					items:[
						{
					        fieldLabel: 'ARN',
					        xtype: 'combo',
					        name: 'arn',
					        store:loadAdmReqNoCbx(),
					        mode:'local',
					        triggerAction:'all',
					        forceSelection: true,
					        allowBlank: false,
					        valueField: 'code',
					        displayField:'description',
					        required: true
					    }
					]
				},{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Unit Type',
					        xtype: 'combo',
					        name: 'nursingUnitType',
					        store: loadNursingUnitTypes(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all',
						    required: true,
						    allowBlank: false
					    }
					]
				},
				{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Bed Type',
					        xtype: 'optcombo',
					        name: 'bedType',
					        store: loadBedTypes(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all'
					    }
					]
				},
				{
					layout:'form',
					items:this.edoa
				},
				{
					layout:'form',
					items:this.edod
				},
				{
					layout:'form',
					items:this.bedReservationPanel
				},
				{
					layout:'form',
					columnWidth:1,
			    	items:this.bedMandatoryFeaturesPanel.getPanel()
				},
				{
					layout:'form',
					columnWidth:1,
			    	items:this.bedOptionalFeaturesPanel.getPanel()
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.buttonPanel
				}]
		});
///*
// *    This event will used to fired from optional or required bed type checkboxes.
// */
//		this.bookBedPanel.addEvents('facilitychecked');
//		
//		this.bookBedPanel.on('facilitychecked',function( thisCheckBox, isChecked , indexNbr){
//			var type = cbx.getId().substr(config.source.length , 3);
//			if (type === "opt"){
//				this.bedMandatoryFeaturesPanel.bedList[i].setDisabled(isChecked);
//			}
//			else if (type === "req"){
//				this.bedOptionalFeaturesPanel.bedList[i].setDisabled(isChecked);
//			}
//
//		},this);
		
			
		this.bookBedPanel.on("clientvalidation",
			function(thisForm, isValid) {
				if (isValid) {
					this.checkAvailabilityBtn.enable();
					this.bookBedBtn.enable();
				} else {
					this.checkAvailabilityBtn.disable();
					this.bookBedBtn.disable();
				}
			}, 
		this);
		
//		Ext.ux.event.Broadcast.subscribe(config.source+"-bedfeaturechecked", function(ct, cbx, isChecked){
//			var type = cbx.getId().substr(config.source.length , 3);
//			var id = '';
//			if (type === "opt") id = config.source+"req" + cbx.getId().substr(config.source.length + 3);
//			if (type === "req") id = config.source+"opt" + cbx.getId().substr(config.source.length + 3);
//			
//			Ext.getCmp(id).setDisabled(isChecked);
//		}, this, null, this.getPanel());

		
	},
	getPanel : function() {
		return this.bookBedPanel;
	},
	setDefaultValues: function(){
		this.edoa.setMinValue(new Date().clearTime(true));
		this.edoa.setValue(new Date());
		this.edod.setMinValue( new Date());
	},
	loadData: function(config){
		this.bookBedPanel.getForm().setValues(config);
		
		if( !Ext.isEmpty( config.requiredFacilities ) && config.requiredFacilities.length > 0 ){
			var requiredFacilitiesData = {
				mode:'edit',
				dataList:config.requiredFacilities
			};
			this.bedMandatoryFeaturesPanel.loadData( requiredFacilitiesData );
		}
		
	}
});