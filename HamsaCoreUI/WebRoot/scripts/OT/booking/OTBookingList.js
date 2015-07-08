Ext.namespace("OT.booking");

OT.booking.OTBookingList = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:true,
	
	initComponent : function() {
		var mainThis = this;
		this.searchPanel = new OT.booking.OTBookingSearchPanel();
		this.widgets = new OT.booking.OTBookingWidgets();
		this.buttonsPanel = new utils.ButtonsPanel();
		this.booking = new OT.booking.OTBooking();
		
		
       this.okBtn = new Ext.Button({
	        text: 'Ok',
	        scope : this,
			iconCls : 'save_btn',
	        disabled : true
        });

		
		this.searchPanel.widgets.getSurgeryCbx().on('select',function( thisCombo ){
			
			var surgeryCode = thisCombo.getValue();
			mainThis.searchPanel.remove( mainThis.statusTimePanel );
			
			var totalNumberField = mainThis.widgets.getTotalNumberField(true);
			var totalOTNumberField = mainThis.widgets.getTotalOTNumberField(true);
			SurgeryManager.getSurgeryStatusTimeDetail(surgeryCode,function( surgeryStatusTimeDetailList ){
			
				if( !Ext.isEmpty( surgeryStatusTimeDetailList )){
					
					this.surgeryStatusTimeDetail = new OT.configureSurgery.SurgeryStatusTimeDetailPanel({surgeryStatusTimeDetailList : surgeryStatusTimeDetailList,
															totalNumberField : totalNumberField,
															totalOTTime : totalOTNumberField });
					
					var totalTime = this.surgeryStatusTimeDetail.getTotalTime();
					this.surgeryStatusTimeDetail.totalSurgeryTime = totalTime;
					 
					var totalOTTime = this.surgeryStatusTimeDetail.getTotalOTTime();
					this.surgeryStatusTimeDetail.totalOTTime = totalOTTime;
					
					totalOTNumberField.setValue( totalOTTime );
					totalNumberField.setValue( totalTime );
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
					
					mainThis.statusTimePanel = new Ext.form.FieldSet({
						columnWidth : 1,
						title : 'Time Caliculation',
						style : 'padding : 10px 10px 10px 10px',
						border : true,
						items :[this.labelPanel, this.surgeryStatusTimeDetail,
						        {layout : 'form',labelWidth:220,items : totalNumberField},	
								{layout : 'form',labelWidth:220,items : totalOTNumberField}]
					});
		
					
					mainThis.searchPanel.insert(1,mainThis.statusTimePanel);
					
					mainThis.searchPanel.doLayout();
				}
			});
		});
		
		this.gridPanel = new OT.booking.OTBookingGrid( { okBtn : this.okBtn});
		
		
		
		this.booking.frame=true;
		var win = new Ext.Window({
			width:'80%',
			height:'80%',
		  	modal:true,
		    resizable:true,
		    closeAction : 'hide',
		    items:[this.booking]
		});
		
		this.gridPanel.widgets.getBookBtn().on('click',function(){
			var selections =  this.gridPanel.getSelectionModel().getSelected();
			config ={
					ot :selections.data.otCode,
					fromTime : selections.data.bookingFrom,
					toTime : selections.data.bookingTo,
					surgeon : selections.data.surgeonCode
			};
			this.booking.setValues(config);
			win.show();
		},this);
		
		this.searchPanel.getButtonPanel().getSearchButton().on('click',function(){
			this.searchButtonClicked();
		},this);
		
		this.searchPanel.getButtonPanel().getResetButtton().on('click',function(){
			this.resetButtonClicked();
		},this);
		
		this.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
		
		if( this.initialConfig.isFromBookOT ){
//			this.buttons = [this.okBtn];
			
			this.okBtn.on('click',function(){
				this.okBtnClicked();
			},this);
			
			this.gridPanel.topToolbar[0].hide();
		}
		else{
			this.okBtn.hide();
		}
		
		Ext.applyIf(this, {
            items: [
			    {
	            	layout : 'form',
					columnWidth:1,
					items:[this.searchPanel]
			    },{
	            	layout : 'form',
					columnWidth:1,
					style : 'padding-top:10px',
					items:[this.gridPanel]
			    }
			]            
        });
		OT.booking.OTBookingList.superclass.initComponent.apply(this, arguments);
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		if( searchCriteria.bookingTo === ""){
			searchCriteria.bookingTo = null;
		}
		this.gridPanel.loadData( searchCriteria ); 	
//		this.searchPanel.getReset();
//		this.searchPanel.remove( this.statusTimePanel );
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.searchPanel.remove( this.statusTimePanel );
		this.gridPanel.getReset();
	},
	okBtnClicked : function(){
		var selectedRecords = this.gridPanel.getSelectedRows();
		if(!Ext.isEmpty( selectedRecords )){
			var selectedRecord = selectedRecords[0];
			var config = {
				ot : selectedRecord.data.otCode,
				surgeon : selectedRecord.data.surgeon,
				fromTime : selectedRecord.data.bookingFrom,
				toTime : selectedRecord.data.bookingTo
			};
			
			if( this.initialConfig.setOTSlotValues){
				this.initialConfig.setOTSlotValues( config )
			}
			
			this.ownerCt.close();
		}
		
	}
	
});
