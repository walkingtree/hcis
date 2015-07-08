Ext.namespace("OPD.serviceAssignement");

  OPD.serviceAssignement.ManageServiceAssignment = Ext.extend(Object,{
  	constructor: function(){
  		
  		this.widgets = new OPD.serviceAssignement.Widgets();
  		
  		this.manageServiceAssignmentGridPnl = new OPD.serviceAssignement.ManageServiceAssignmentGrid();
  		
  		var mainThis = this;
  		
  		this.buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
			buttons:[
				this.widgets.getSearchBtn(),
				this.widgets.getResetBtn()
			]
		});	
  		this.mainPanel = new Ext.form.FormPanel({
				frame : true,
				autoHeight : true,
				border : false,
				monitorValid: true,
				items: [
					{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							labelWidth:90,
							columnWidth : .33
						},
						items: [{
							items: this.widgets .getServiceCode()
						},{
							labelWidth:105,
						    items: this.widgets .getServiceName()
					    },{
						    items: this.widgets .getPackageCode()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							labelWidth:90,
							columnWidth : .33
						},
						items: [{
							items: this.widgets .getPackageName()
						},{
							labelWidth:105,
						    items: this.widgets .getEffectiveFrom()
					    },{
						    items: this.widgets .getEffectiveTo()
					    }]
					},{
						layout : 'column',
						defaults: {
							border: false,
							labelWidth:90,
							 columnWidth : .33,
							layout: 'form'
						},
						items: [{
						    items: this.widgets .getDepartment()
					    },{
					    	labelWidth:105,
						    items: this.widgets .getServiceGroup()
					    },{
							items: this.widgets .getReftype()
						}]
					},{
						layout : 'column',
						defaults: {
							border: false,
							labelWidth:90,
							 columnWidth : .33,
							layout: 'form'
						},
						items: [{
							items: this.widgets .getRefNbr()
						},{
							labelWidth:105,
							items: this.widgets.getPatient()
						}]
					},
					this.buttonPanel,
					this.manageServiceAssignmentGridPnl.getPanel()
			    ]
		});
		
		Ext.ux.event.Broadcast.subscribe('getFocusForFirstElementInManageServiceAssignment', function(){
			mainThis.widgets.getServiceCode().focus();
		}, this, null, mainThis.getPanel());
		
		this.mainPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		this.initialListeners();
		
  	},
  	getPanel: function() {
  		return this.mainPanel;
  	},
  	searchBtnClicked: function(){
  		var valuesMap = this.mainPanel.getForm().getValues();
  			
		this.manageServiceAssignmentGridPnl.getPanel().getStore().load({params:{start:0, limit:this.manageServiceAssignmentGridPnl.pagingBar.pageSize}, 
							  arg:[
						   		   valuesMap['serviceCode'],
						   		   valuesMap['serviceName'],
						   		   valuesMap['packageCode'],
						   		   valuesMap['PackageName'], 
						   		   getStringAsWtcDateFormat( valuesMap['serviceFromDt'] ), 
						   		   getStringAsWtcDateFormat( valuesMap['serviceToDt'] ),
						   		   valuesMap['department'],
						   		   valuesMap['serviceGroup'],
						   		   valuesMap['refType'],
						   		   valuesMap['refNbr'],
						   		   valuesMap['patient']
						   		   ]
		});	
  	},
	renderBtnClicked: function(){
  		var mainThis = this;
		var selectedRows = this.manageServiceAssignmentGridPnl.getSelectedRows();
		var data = selectedRows[0].data;
		var serviceUid = data.serviceUid;
		var enteredUnits = 1;
		var renderBy = getAuthorizedUserInfo().userName;
		ServiceManager.modifyAssignedServiceToRendered( serviceUid,enteredUnits,renderBy,{
			callback : function(){
				mainThis.manageServiceAssignmentGridPnl.getPanel().getStore().reload();
			}
		});
//		if( selectedRows.length > 0 ){
//			
//			var config = {
//				enteredUnitsLbl: 'Rendering units',
//				requestedUnits: data.requestedUnits,
//				renderedUnits: data.renderedUnits,
//				canceledUnits : data.canceledUnits,
//				initialvalueForEnteredUnits : 1
//			};
//			var selectionPnl =  new OPD.serviceAssignement.SelectionPanel( config );
//			selectionPnl.loadData( config );
//			
//			var win = new Ext.Window({
//				height: '30%',
//				width: '30%',
//				items: selectionPnl.getPanel(),
//				title: mngSvcAsgntMsg.forRenderingServices,
//				frame:true,
//				closeAction:'hide',
//				modal:true
//			});
//			function checkOkBtnStatus( comp ){
//				var requestedUnits = parseInt( selectionPnl.widgets.getRequestedUnitsValLbl().text,10 );
//				var renderedUnits = parseInt( selectionPnl.widgets.getAlreadyRenderUnitsValLbl().text, 10);
//				var calceledUnits =   parseInt( selectionPnl.widgets.getAlreadyCanceledUnitsValLbl().text, 10);
//				var avaliableUnits = requestedUnits -( renderedUnits + calceledUnits  );
//				selectionPnl.widgets.getOkBtn().enable(); 
//				if( Ext.isEmpty( comp.getValue() ) ){
//					selectionPnl.widgets.getOkBtn().disable();
//				}
//				if( comp.getValue() > avaliableUnits ){
//					comp.markInvalid();
//					selectionPnl.widgets.getOkBtn().disable();
//				}
//			};
//			checkOkBtnStatus( selectionPnl.widgets.getEntredUnits() );
//			win.show();
//			
//			selectionPnl.widgets.getCancelBtn().on('click', function(){
//				win.close();
//				Ext.ux.event.Broadcast.publish('clearSelectionsInManageAssignedServiceGrid');
//			},this);
//			
//			selectionPnl.widgets.getOkBtn().on('click', function(){
//				var serviceUid = data.serviceUid;
//				var enteredUnits = selectionPnl.widgets.getEntredUnits().getValue();
//				if( !Ext.isEmpty( enteredUnits ) && enteredUnits <= 0 ){
//					Ext.Msg.show({
//						msg: mngSvcAsgntMsg.enteredUnitsShouldNotZero,
//						icon : Ext.MessageBox.ERROR,
//						buttons: Ext.Msg.OK,
//						fn: function(){}
//					});
//					return ;
//				}
//				var renderBy = getAuthorizedUserInfo().userName;
//				ServiceManager.modifyAssignedServiceToRendered( serviceUid,enteredUnits,renderBy,{
//				callback: function(){
//					win.close();
//					Ext.ux.event.Broadcast.publish('reloadManageAssignedServiceGrid');
//					selectionPnl.widgets.getOkBtn().events['click'].clearListeners();
//				},
//				callbackScope: this
//				} );
//			},this);
//			
//			selectionPnl.widgets.getEntredUnits().on('blur', function( comp ){
//				checkOkBtnStatus( comp );
//			},this);
//			
//		}
	},
	viewAssignemtBtnClicked: function(){
	
	},
	deleteBtnClicked: function(){
		var selectedRows = this.manageServiceAssignmentGridPnl.getSelectedRows();
		Ext.Msg.show({
			msg: mngSvcAsgntMsg.deleteAssignedServiceMsg,
			icon : Ext.MessageBox.QUESTION,
			buttons: Ext.Msg.YESNO,
			fn: function( btnValue ){
				if( btnValue== configs.yes ){
						if( selectedRows.length > 0 ){
						var serviceUidList = new Array();
						var deletedBy = getAuthorizedUserInfo().userName;
						for ( var i = 0; i<selectedRows.length; i++ ){
							var data = selectedRows[i].data;
							serviceUidList.push( data.serviceUid );
						}
						ServiceManager.removeAssignedServices( serviceUidList,deletedBy,{
							callback: function(){
								Ext.ux.event.Broadcast.publish('reloadManageAssignedServiceGrid');
							},
							callbackScope: this
						});
					}
				}
			}
		});
	},
	replaceBtnClicked: function(){
		
	},
	canceledBtnClicked: function(){
		var selectedRows = this.manageServiceAssignmentGridPnl.getSelectedRows();
		if( selectedRows.length > 0 ){
			var serviceUidList = new Array();
			var canceledBy = getAuthorizedUserInfo().userName;
			for ( var i = 0; i<selectedRows.length; i++ ){
				var data = selectedRows[i].data;
				serviceUidList.push( data.serviceUid );
			}
		}
		ServiceManager.cancelAssignedServices( serviceUidList,canceledBy,{
			callback: function(){
				Ext.ux.event.Broadcast.publish('reloadManageAssignedServiceGrid');
			},
			callbackScope: this
		});
	
	},
	resetBtnClicked: function(){
		this.mainPanel.getForm().reset();
		Ext.ux.event.Broadcast.publish('reloadManageAssignedServiceGrid');
	},
  	initialListeners: function(){
  		
  		// listener for search button
  		
  		this.widgets.getSearchBtn().on('click', function(){
  			this.searchBtnClicked();
  		}, this);
  		
  		// render listener on grid for initially loading the grid.
  		
  		this.manageServiceAssignmentGridPnl.getPanel().on('render', function(){
  			this.searchBtnClicked();
  		}, this);
  		
  		// listener for reset button
  		
  		this.widgets.getResetBtn().on('click', function (){
  			this.resetBtnClicked();
  		},this);
  		
  		// listener for render button
  		
  		this.manageServiceAssignmentGridPnl.renderBtn.on('click', function(){
  			this.renderBtnClicked();
  		}, this);
  		
  		// listener for delete button
  		
  		this.manageServiceAssignmentGridPnl.cancelBtn.on('click', function(){
  			this.canceledBtnClicked();
  		}, this);
  		// listener for replace button
  		
  		this.manageServiceAssignmentGridPnl.viewAssignmentsBtn.on('click', function(){
  			this.viewAssignemtBtnClicked();
  		}, this);
  		// listener for view assignments button
  		
  		this.manageServiceAssignmentGridPnl.deleteBtn.on('click', function(){
  			this.deleteBtnClicked();
  		}, this);
  		// listener for cancel button
  		
  		this.manageServiceAssignmentGridPnl.replaceBtn.on('click', function(){
  			this.replaceBtnClicked();
  		}, this);
  		// Listeners for book OT Button.
  		// This will be responsible for the OT Booking.
  		this.manageServiceAssignmentGridPnl.bookOTBtn.on('click',function(){
  			this.bookBtnClicked();
  		},this);
  	},
  	
  	bookBtnClicked : function(){
  		var recordArray = this.manageServiceAssignmentGridPnl.getSelectedRows();
  		var config = null;
  		var additionalInfoConfig = null;
  		if( recordArray.length === 1){
  			var record = recordArray[0];
  			config = {
  				patientId : record.data.patientId,
  				name : record.data.patientName,
  				referenceNbr : record.data.referenceNumber,
  				referenceType : record.data.referenceType,
  				surgeon : record.data.doctorId,
  				surgery : record.data.serviceCode
  			};
  			
  			additionalInfoConfig = {
				serviceUid : record.data.serviceUid	
			};
  		}
  		
		var panel = new OT.booking.OTBooking();
		panel.setValues( config );
		panel.title = 'Book OT';
		panel.frame = true;
		panel.closable = true;
		panel.height = 420;
		
		
		panel.setAdditionalInfo( additionalInfoConfig ); 
		
		var activeTab =	layout.getCenterRegionTabPanel().add(panel);
		layout.getCenterRegionTabPanel().setActiveTab( activeTab );
		layout.getCenterRegionTabPanel().doLayout();
		

  	}
  });
  
  
  
//		
//		var selectedRows = this.manageServiceAssignmentGridPnl.getSelectedRows();
//		if( selectedRows.length > 0 ){
//			var data = selectedRows[0].data;
//			var initialValue = data.requestedUnits - ( data.renderedUnits + data.canceledUnits );
//			var config = {
//				enteredUnitsLbl: 'Canceling units',
//				requestedUnits: data.requestedUnits,
//				renderedUnits: data.renderedUnits,
//				canceledUnits : data.canceledUnits,
//				initialvalueForEnteredUnits: initialValue
//			};
//			var selectionPnl =  new OPD.serviceAssignement.SelectionPanel( config );
//			selectionPnl.loadData( config );
//			
//			var win = new Ext.Window({
//				height: '30%',
//				width: '30%',
//				items: selectionPnl.getPanel(),
//				title: mngSvcAsgntMsg.forCancelServices,
//				frame:true,
//				closeAction:'hide',
//				modal:true
//			});
//			
//			win.show();
//			
//			selectionPnl.widgets.getCancelBtn().on('click', function(){
//				win.close();
//				Ext.ux.event.Broadcast.publish('clearSelectionsInManageAssignedServiceGrid');
//			},this);
//			
//			selectionPnl.widgets.getOkBtn().on('click', function(){
//				var serviceUid = data.serviceUid;
//				var enteredUnits = selectionPnl.widgets.getEntredUnits().getValue();
//				var renderBy = getAuthorizedUserInfo().userName;
//				ServiceManager.modifyAssignedServiceToRendered( serviceUid,enteredUnits,renderBy,{
//				callback: function(){
//					win.close();
//					Ext.ux.event.Broadcast.publish('reloadManageAssignedServiceGrid');
//				},
//				callbackScope: this
//				} );
//				selectionPnl.widgets.getOkBtn().events['click'].clearListeners();
//			},this);
//			
//			selectionPnl.widgets.getEntredUnits().on('blur', function( comp ){
//				var requestedUnits = parseInt( selectionPnl.widgets.getRequestedUnitsValLbl().text,10 );
//				var renderedUnits = parseInt( selectionPnl.widgets.getAlreadyRenderUnitsValLbl().text, 10);
//				var canceldUnits = parseInt( selectionPnl.widgets.getAlreadyCanceledUnitsValLbl().text, 10);
//				var avaliableUnits = requestedUnits - renderedUnits;
//				if( Ext.isEmpty( comp.getValue() ) ){
//					selectionPnl.widgets.getOkBtn().disable();
//				}
//				if( comp.getValue() > avaliableUnits ){
//					comp.markInvalid();
//					selectionPnl.widgets.getOkBtn().disable();
//				}
//			},this);
//			
//		}
	
	