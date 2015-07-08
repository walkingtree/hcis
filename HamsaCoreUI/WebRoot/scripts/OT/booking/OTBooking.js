Ext.namespace("OT.booking");

OT.booking.OTBooking = Ext.extend(Ext.form.FormPanel,{
	monitorValid : true,
	initComponent : function(){
	
		this.serviceUid = null;
	
		this.otBookingWidgets = new OT.booking.BookingWidgets();
		
		this.otBookingWidgets.getPatientIdTxf().on('blur',function(thisField){
			var patientId = thisField.getValue();
			this.otBookingWidgets.getSurgeryCbx().getStore().load({params:{start:0, limit:999}, arg:[null, null,patientId,"SURGICAL"]});
		},this);
		
		this.bookBtn = new Ext.Button({
			text: "Book",
			scope : this,
			iconCls : 'add_btn',			
			disabled : false
		});
		
		this.searchOTSlotBtn = new Ext.Button({
//			text: "Search slot",
			scope : this,
			iconCls : 'search_btn',
			disabled : false
		});

		this.searchBtn = new Ext.Button({
			scope : this,
			iconCls : 'search_btn',
			disabled : false
		});
		
		
		this.otSlotFieldSet = new Ext.form.FieldSet({
			title : bookingMsg.otSlot,
			layout : 'column',
			items : [
		         {
		        	 layout : 'form',
		        	 columnWidth : .5,
		        	 items : this.otBookingWidgets.getOtCbx()
		         },    
		         {
		        	 layout : 'form',
		        	 columnWidth : .34,
		        	 items : this.otBookingWidgets.getFromTimeTmf()
		         },    
		         {
		        	 layout : 'form',
		        	 labelWidth : .01,
		        	 columnWidth : .16,
		        	 items : this.otBookingWidgets.getFromTimeField()
		         },    
		         {
		        	 layout : 'form',
		        	 columnWidth : .34,
		        	 items : this.otBookingWidgets.getToTimeTmf()
		         },    
		         {
		        	 layout : 'form',
		        	 columnWidth : .16,
		        	 labelWidth : .01,
		        	 items : this.otBookingWidgets.getToTimeField()
		         },    
		         {
		        	 layout : 'form',
		        	 columnWidth : .42,
		        	 items : this.otBookingWidgets.getSurgeonCbx()
		         },    
		         {
		        	 columnWidth : .08,
		        	 items : this.searchOTSlotBtn
		         },
	         ]
		});
		
		this.bookingPanel = new Ext.Panel({
			buttons : this.bookBtn,
			layout : 'column',
			items : [              
               {   
	         	   layout : 'form',
	        	   columnWidth : .42,
	        	   items : this.otBookingWidgets.getPatientIdTxf()
	           },
	           {
	        	   columnWidth : .08,
	        	   items : this.searchBtn
	           },
	           {   
	        	   layout : 'form',
	        	   columnWidth : .5,
	        	   items : this.otBookingWidgets.getPatientNameTxf()
	           },
	           {   
	        	   layout : 'form',
	        	   columnWidth : .5,
	        	   items : this.otBookingWidgets.getSurgeryCbx()
	           },
	           {   
	        	   layout : 'form',
	        	   columnWidth : .5,
	        	   items : this.otBookingWidgets.getReferenceNbrTxf()
	           },
	           {   
	        	   layout : 'form',
	        	   columnWidth : .5,
	        	   items : this.otBookingWidgets.getReferenceTypeTxf()
	           },
	           {
	        	   columnWidth : 1,
	        	   items : this.otSlotFieldSet
	           }
	       ]
		});
		
		this.otBookingWidgets.getSurgeryCbx().on('select',function( thisCombo , record ){
			
			var indexNbr = 	thisCombo.getStore().find("code",thisCombo.getValue());
			var record =  thisCombo.getStore().getAt( indexNbr );
			this.otBookingWidgets.getReferenceNbrTxf().setValue( record.data.referenceNbr );
			this.otBookingWidgets.getReferenceTypeTxf().setValue( record.data.referenceType );
			this.serviceUid = record.data.serviceUid;
//			var config = {
//				referenceType : record.data.referenceType,
//				referenceNbr : record.data.referenceNbr
//			};
//			this.setValues( config );
		},this);

		this.searchBtn.on('click',function( thisBtn ){
			getSearchPatientPanel(this.getForm(), this).show();
		},this);

		this.bookBtn.on('click',function( thisBtn ){
			this.bookBtnClicked();
		},this);
		
		
		this.searchOTSlotBtn.on('click',function(){
			this.searchOTSlotBtnClicked();
		},this);
		
		
		this.on('clientvalidation' , function(thisForm,isValid){
			if( isValid ){
				this.bookBtn.enable();
			}
			else{
				this.bookBtn.disable();
			}
		});
		Ext.applyIf(this, {
            items:[
               this.bookingPanel,
           ]
        });
		
		OT.booking.OTBooking.superclass.initComponent.apply(this, arguments);
		
	},
	
	setValues : function( config ){
		if( Ext.isEmpty( config )){
			config = {}; 
		}
		var patientId = null;
		if( !Ext.isEmpty( config.patientId)){
			patientId = parseInt(config.patientId)
		}
		this.otBookingWidgets.getOtCbx().getStore().reload();
		this.otBookingWidgets.getOtCbx().getStore().on('load',function( thisStore ){
			this.otBookingWidgets.getOtCbx().setValue( config.ot);
		},this);
		this.otBookingWidgets.getSurgeonCbx().getStore().reload();
		this.otBookingWidgets.getSurgeonCbx().getStore().on('load',function( thisStore ){
			this.otBookingWidgets.getSurgeonCbx().setValue( config.surgeon);
		},this);
		this.otBookingWidgets.getSurgeryCbx().getStore().load({params:{start:0, limit:999}, arg:[null, null,patientId,"SURGICAL"]});
		this.otBookingWidgets.getSurgeryCbx().getStore().on('load',function( thisStore ){
			this.otBookingWidgets.getSurgeryCbx().setValue( config.surgery);
		},this);

		this.getForm().setValues( config );
	},
	resetForm : function(){
		this.getForm().reset();
		this.otBookingWidgets.getPatientIdTxf().setValue("");
		this.otBookingWidgets.getPatientNameTxf().setValue("");
		this.otBookingWidgets.getSurgeryCbx().setValue("");
		this.otBookingWidgets.getReferenceNbrTxf().setValue("");
		this.otBookingWidgets.getReferenceTypeTxf().setValue("");
	},
	
	bookBtnClicked : function(){
		var formValues = this.getForm().getValues();
		
		var fromDtm = this.getDateWithGivenDateAndTime(this.otBookingWidgets.getFromTimeTmf().getValue(), formValues['fromTimeField']);
		var toDtm = this.getDateWithGivenDateAndTime(this.otBookingWidgets.getToTimeTmf().getValue(), formValues['toTimeField']);
		
		var bookingBM = {
			surgery : {code : formValues['surgery']},
			operationTheater :{code : formValues['ot']},
			doctorId : !Ext.isEmpty( formValues['surgeon'] ) ? parseInt( formValues['surgeon'] ) : null,
			patientId : !Ext.isEmpty( formValues['patientId'] ) ? parseInt( formValues['patientId'] ) : null,
			bookingFromDtm : fromDtm,
			bookingToDtm : toDtm,
			userId : getAuthorizedUserInfo().userName,
			referenceType : formValues['referenceType'],
			referenceNumber : formValues['referenceNbr'],
			serviceUId : this.serviceUid
		}
		
		OTManager.bookOT( bookingBM , false ,{callback : function(){
				this.resetForm();
			},
			scope :this
		});
	},
	
	getDateWithGivenDateAndTime : function(date , time ){
		var timeInfo = time.split(":");
		var hour = parseInt( timeInfo[0] );
		var min = parseInt( timeInfo[1].split(" ")[0] );
		var ampmInd = timeInfo[1].split(" ")[1];
		
		if( ampmInd === "PM" && hour != 12){
			hour = hour + 12;
		}
		else if( ampmInd === "AM" && hour === 12){
			hour = hour -12;
		}
		
		date.setHours( hour );
		date.setMinutes( min );
		
		return date;
	},
	
	setAdditionalInfo : function( config ){
		if( Ext.isEmpty( config )){
			config = {};
		}
		this.serviceUid = config.serviceUid;
	},
	
	searchOTSlotBtnClicked : function(){
		var searchOTSlotPanel = new OT.booking.OTBookingList({isFromBookOT : true,
															setOTSlotValues : this.setOTSlotValues,
															otBooking : this});
		
		var searhOTSlOTSlotWindow = new Ext.Window({
			title: 'Search ot slot',
			items:searchOTSlotPanel,
			frame:true,
			height:450,
			width:'60%',
			autoScroll : true
			
		});
		searhOTSlOTSlotWindow.show();

	},
	
	setOTSlotValues : function( config ){
		if( Ext.isEmpty( config )){
			config = {};
		}
		this.otBooking.otBookingWidgets.getOtCbx().setValue( config.ot );
		this.otBooking.otBookingWidgets.getSurgeonCbx( config.surgeon );
		this.otBooking.otBookingWidgets.getFromTimeTmf( config.fromTime )
	}

});