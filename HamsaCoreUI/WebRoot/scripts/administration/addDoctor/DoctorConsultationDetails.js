Ext.namespace("administration.addDoctor");


// Doctor Consultation details FormPanel created and used this in addDocotr Panel 

administration.addDoctor.DoctorConsultationDetails = Ext.extend(Ext.form.FormPanel,{	

	border:false,
	height : 400,
	frame:false,
 	initComponent : function() {
			if(!Ext.isEmpty(this.initialConfig)){
				config = this.initialConfig;
			}else{
				config = {};
			}
			
			this.followUpFeeNbr = new Ext.form.NumberField({
				name: 'followupfee',
				fieldLabel :'Followup Fee',
				anchor:'30%'
			});
			
			this.consultationFeeNbr = new Ext.form.NumberField({
				name: 'doctorConsultationcharges',
				fieldLabel :msg.consultationcharges+' ('+ getCurrencyIndicator() +')',
				anchor:'30%',
				value:!Ext.isEmpty(config.selectedconsultationCharge)?
				    	  config.selectedconsultationCharge:
				    	  configs.appointmentfee
			});
			
			this.daysCheckBox = new Ext.form.Checkbox({
				name : 'followupCheck',
				hideLabel : true,
//				height : '10px',
				anchor:'98%'
			});
			
			this.visitsCheckBox = new Ext.form.Checkbox({
				name : 'visitsCheck',
				hideLabel : true,
//				height : '10px',
				anchor:'98%'
			});
			
			this.daysNbr = new Ext.form.NumberField({
				fieldLabel :'Followup Days',
				disabled : true,
				name: 'followupDays',
				anchor:'98%'
			});
			
			this.visitsNbr = new Ext.form.NumberField({
				fieldLabel :'Followup Visits',
				disabled : true,
				name: 'followupVisits',
				anchor:'98%'
			});
			
			this.followUpDaysLbl = new Ext.form.Label({
				fieldLabel : '<b>Days</b>',
				labelSeparator : '',
				autoWidth : true
			});
			
			this.followUpVisitsLbl = new Ext.form.Label({
				fieldLabel : '<b>Visits</b>',
				labelSeparator : '',
				autoWidth : true
			});
			
			// Enable visit times textbox only when checking the Visits check box
			
			this.visitsCheckBox.on('check',function(comp,value){
				if(value == true){
					this.getVisitsNbr().enable();
				}else if(value == false){
					this.getVisitsNbr().reset();
					if(!Ext.isEmpty(config.mode)&&!Ext.isEmpty(config.selectedfollowUpVisits)){
						this.getVisitsNbr().setValue('');
					}
					this.getVisitsNbr().disable();
				}
			},this);
			
			// Enable followup day  textbox only when checking the Follow Up days check box
			
			this.daysCheckBox.on('check',function(comp,value){
				if(value == true){
					this.getDaysNbr().enable();
				}else if(value == false){
					this.getDaysNbr().reset();
					if(!Ext.isEmpty(config.mode)&&!Ext.isEmpty(config.selectedfollowUpDays)){
						this.getDaysNbr().setValue('');
					}
					this.getDaysNbr().disable();
				}
			},this);
			
			this.followupFieldSet = new Ext.form.FieldSet({
				height:'100%',
				width: '40%',
				border:true,
				title:'Followup Period',
				layout:'column',
				defaults:{labelWidth:100 },
				items:[
					{
						columnWidth:.15,
						layout:'form',
		 				items:this.getDaysCheckBox()
					},
					{
						columnWidth:.55,
						layout:'form',
		 				items:[
	 				       this.getDaysNbr()
						  ]
					},	  
					{
						columnWidth:.30,
						layout:'form',
//						labelWidth:10,
						items:[
						       this.getFollowUpDaysLbl()
					      ]
					},{
						columnWidth : 1,
						layout : 'column',
						items : [{
							columnWidth:.15,
							layout:'form',
							items:[
							       this.getVisitsCheckBox()
						      ]
						},
						{
							columnWidth:.55,
							layout:'form',
			 				items:this.getVisitsNbr()
						},
						{
							columnWidth:.30,
							layout:'form',
//							labelWidth:10,
			 				items:this.getFollowUpVisitsLbl()
						}]
					}]
			});
			
			Ext.applyIf(this, {
				layout:'form',
				defaults:{labelWidth:150 },
		        items: [{
		        	layout:'form',
		        	items : 
		        		this.getConsultationFeeNbr()
		        },
		                this.getFollowUpFeeNbr() ,
		                this.followupFieldSet  
				]            
		    });
			administration.addDoctor.DoctorConsultationDetails.superclass.initComponent.apply(this, arguments);	
			
	},
	
	getPanel:function(){
		return this;
	},
	getData:function(){
		return this.getForm().getValues();
	},
	reSetData:function(){
		return this.getForm().reset();
	},
	setData:function(data){
//		if(!Ext.isEmpty(data.title)){
//			this.getPanel().title = false;
//		}
		
		if(!Ext.isEmpty(data.selectedfollowUpCharge)){
			this.getFollowUpFeeNbr().setValue(data.selectedfollowUpCharge);
		}
		if(!Ext.isEmpty(data.selectedfollowUpDays)){
			this.getDaysCheckBox().checked=true;
			this.getDaysNbr().enable();
			this.getDaysNbr().setValue(data.selectedfollowUpDays);
		}
		if(!Ext.isEmpty(data.selectedfollowUpVisits)){
			this.getVisitsCheckBox().checked=true;
			this.getVisitsNbr().enable();
			this.getVisitsNbr().setValue(data.selectedfollowUpVisits);
		}
	},
	getFollowUpFeeNbr:function(){
		return this.followUpFeeNbr;
	},
	
	getConsultationFeeNbr:function(){
		return this.consultationFeeNbr;
	},
	
	getDaysCheckBox:function(){
		return this.daysCheckBox;
	},
	
	getVisitsCheckBox:function(){
		return this.visitsCheckBox;
	},
	
	getDaysNbr:function(){
		return this.daysNbr;
	},
	
	getVisitsNbr:function(){
		return this.visitsNbr;
	},
	
	getFollowUpDaysLbl:function(){
		return this.followUpDaysLbl;
	},
	
	getFollowUpVisitsLbl:function(){
		return this.followUpVisitsLbl;
	}
});
			
Ext.reg('consultation-details', administration.addDoctor.DoctorConsultationDetails);