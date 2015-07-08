Ext.namespace("administration.manageBed");

administration.manageBed.ManageBedBooking = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		
			if( !Ext.isEmpty( config ) ){
				this.infoGrid = new administration.manageBed.BedBookingGrid({plugins:[],hideToolbar: config.hideToolbar});
			}else{
				this.infoGrid = new administration.manageBed.BedBookingGrid({plugins:[]});
			}
		
		this.infoGrid.action.on({
			action:function(grid, record, action, row, col) {
				if (action === 'user-add-icon') {
					var assignBedPnl = new administration.manageBed.AssignBed({brn: record.data.bedReservationNbr,
											arn: record.data.admissionReqNbr,
											isPopup: true});
					
					var panelToAdd = assignBedPnl.getPanel();
					panelToAdd.frame = true;
					panelToAdd.title = "Assign bed"; 
					panelToAdd.closable = true;
					panelToAdd.height = 420;
					
					var panel = layout.getCenterRegionTabPanel().add(panelToAdd);
					
					layout.getCenterRegionTabPanel().setActiveTab( panel );
					layout.getCenterRegionTabPanel().doLayout();
						
					Ext.ux.event.Broadcast.subscribe("closeAssignWindow", function(){
//						var toBeRemovePanel = panel;
//		
//						layout.getCenterRegionTabPanel().remove( toBeRemovePanel, true );
						layout.getCenterRegionTabPanel().setActiveTab( mainThis.bookBedPanel );
						layout.getCenterRegionTabPanel().doLayout();
						
						Ext.ux.event.Broadcast.publish("reloadBedBookingGrid");
						
					},this, null, mainThis.getPanel());
				}
			
				if (action === 'cross_icon') {
					Ext.Msg.show({
							title : 'Cancel booking',
							msg : 'Are you sure you want to cancel the booking with request number: ' + record.data.bedReservationNbr,
							buttons : Ext.Msg.YESNO,
							fn : function(buttonId, text, opt) {
								if (buttonId == "yes") {
									BedManager.cancelBooking(record.data.bedReservationNbr, authorisedUser.userName, function(ret){
										if (ret) {
											Ext.Msg.alert("Cancel booking", "Booking request is cancelled");
											Ext.ux.event.Broadcast.publish("reloadBedBookingGrid");
										}
									});
								}
							},
							animEl : 'elId',
							icon : Ext.MessageBox.QUESTION
						});

				}
			}
		});
		this.bedMandatoryFeaturesPanel = new administration.addBed.BedFeaturesPanel({source: 'mbed', suffix: 'mbreq',title: 'Required facilities', data: bedFeaturesStore.data.items});
		this.bedOptionalFeaturesPanel = new administration.addBed.BedFeaturesPanel({source: 'mbed', suffix: 'mbopt',title: 'Desired facilities', data: bedFeaturesStore.data.items});
		

		this.edoa = new Ext.form.WTCDateField({
			fieldLabel: 'EDOA',
			name: 'expectedDOA', //date of arrival
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
			listeners:{
				blur: function( date ){
					if(!Ext.isEmpty(date.getValue())){
					mainThis.edoa.setMaxValue(date.getValue());
					}
				}
			}
		});
		
		this.searchBtn = new Ext.Button({
	    	text: 'Search',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		if(this.bookBedPanel.getForm().isValid()){
	    			this.infoGrid.search(this.bookBedPanel.getForm().getValues(), null);
	    		}else{
	    			Ext.Msg.show({
						msg: 'Please enter valid data and try again! ',
						icon : Ext.MessageBox.ERROR,
						buttons: Ext.Msg.OK
					});
	    		}
	    		
	    	}
		});
		
		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			buttons:[this.searchBtn,{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.bookBedPanel.getForm().reset();
					if(this.mode =='edit'){
						this.bookBedPanel.getForm().setValues(config);
					}
				}
			}]
		});				  
		
		this.getData = function() {
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
			
			return bedReservationBM;
		};
		
		this.bookBedPanel = new Ext.form.FormPanel({
			layout : 'column',
			width : '98%',
//			style:'padding:5px',
			autoHeight:true,
			border : false,
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
					        xtype: 'textfield',
					        name: 'admissionReqNbr'
					    }
					]
				},{
					layout:'form',
					items:[
						{
					        fieldLabel: 'Unit Type',
					        xtype: 'optcombo',
					        name: 'unitType',
					        store: loadNursingUnitTypes(),
							mode:'local',
							displayField:'description',
							valueField:'code',
						    triggerAction: 'all'
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
				},
				{
			    	columnWidth:1,
			    	width: '100%',
			    	items:this.infoGrid.getPanel()
			    }
			    
			]
		});
		
		this.bookBedPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		Ext.ux.event.Broadcast.subscribe("mbed"+"-bedfeaturechecked", function(ct, cbx, isChecked){
			var type = cbx.getId().substr("mbed".length , 5);
			var id = '';
			if (type === "mbopt") id = "mbed"+"mbreq" + cbx.getId().substr("mbed".length + 5);
			if (type === "mbreq") id = "mbed"+"mbopt" + cbx.getId().substr("mbed".length + 5);
			
			Ext.getCmp(id).setDisabled(isChecked);
		}, this, null, this.getPanel());
	
	},
	getPanel : function() {
		return this.bookBedPanel;
	}
});