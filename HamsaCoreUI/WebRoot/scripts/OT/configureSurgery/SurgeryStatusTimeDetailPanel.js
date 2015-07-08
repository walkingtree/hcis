Ext.namespace("OT.configureSurgery");

OT.configureSurgery.SurgeryStatusTimeDetailPanel = Ext.extend(Ext.Panel,{
	border : false,
	frame : false,
	initComponent : function(){
		
		this.totalSurgeryTime = 0;
		this.totalOTTime = 0;
		if( !Ext.isEmpty( this.initialConfig.totalSurgeryTime )){
			this.totalSurgeryTime = this.initialConfig.totalSurgeryTime;
		}
		
		if( !Ext.isEmpty( this.initialConfig.totalOTTime )){
			this.totalOTTime = this.initialConfig.totalOTTime;
		}
		this.fieldBeforeChangingValue = 0;
	
		this.surgeryStatusTimeDetailList = this.initialConfig.surgeryStatusTimeDetailList;
		this.configureSurgery = this.initialConfig.configureSurgery;
		this.list = new Array();
		this.surgeryStatusTimeDetailObject = {};
		
		for( var i = 0 ; i < this.surgeryStatusTimeDetailList.length ; i++){
			
			this.surgeryStatusTimeDetailObject["status"+i] = new Array();
	
			var surgeryStatusTimeDetail = this.surgeryStatusTimeDetailList[i];
			
			this.numberField = new Ext.form.NumberField({
				name:surgeryStatusTimeDetail.surgeryStatus.code,
				anchor:'80%',
				fieldLabel:surgeryStatusTimeDetail.surgeryStatus.description,
				value : surgeryStatusTimeDetail.time,
				allowNegative : false,
				contributeToScheduling:surgeryStatusTimeDetail.contributeToScheduling
			});
			
			this.surgeonRequiredCheckbox = new Ext.form.Checkbox({
	        	name: 'surgeonRequired',
	        	checked : surgeryStatusTimeDetail.surgeonRequired === "Y" ? true : false
		 	});
			
			this.surgeryStatusTimeDetailObject["status"+i].push( this.numberField );
			this.surgeryStatusTimeDetailObject["status"+i].push( this.surgeonRequiredCheckbox );
			
			this.dynamicPanel = new Ext.Panel({
				frame : false,
				border : false,
				layout : 'column',
				items : [
			         {
			        	 labelWidth : 220,
			        	 columnWidth : .6,
			        	 layout : 'form',
			        	 border : false,
			        	 items : this.numberField
			         },
			         {
			        	 columnWidth : .2,
			        	 labelWidth : .1,
			        	 layout : 'form',
			        	 border : false,
			        	 items : this.surgeonRequiredCheckbox
			         }

		         ]
			});
			
			this.list.push(this.dynamicPanel);
			
			this.numberField.on('blur',function(thisField){
				var thisFieldValue = (thisField.getValue() * 1) -  this.fieldBeforeChangingValue;
				this.totalSurgeryTime = this.totalSurgeryTime + thisFieldValue;
				if(thisField.contributeToScheduling=="Y"){
					this.totalOTTime = this.totalOTTime + thisFieldValue;
				}
				if( !Ext.isEmpty( this.totalSurgeryTime )){
					if( !Ext.isEmpty( this.configureSurgery)){
						this.configureSurgery.getSurgeryWidgests().getTotalNumberField().setValue( this.totalSurgeryTime );
					}
				}
				
				if( !Ext.isEmpty( this.initialConfig.totalNumberField )){
					this.initialConfig.totalNumberField.setValue( this.totalSurgeryTime );
				}
				if( !Ext.isEmpty( this.initialConfig.totalOTTime )){
					this.initialConfig.totalOTTime.setValue(this.totalOTTime) ;
				}

			},this);

			this.numberField.on('focus',function(thisField){
				this.fieldBeforeChangingValue =(thisField.getValue() * 1);
			},this);

		}
		
		Ext.applyIf(this, {
            items: this.list
			            
        });
		OT.configureSurgery.SurgeryStatusTimeDetailPanel.superclass.initComponent.apply(this, arguments);

	},
	/*
	 *  This method will return surgeryStatusTimeBM .
	 *  At the time of saving this method will be called.
	 *  and this will return surgery status time detail.
	 */
	getSurgeryStatusTimeBMList : function(){
		var surgeryStatusTimeBMList = new Array();
		var i = 0;
		while( this.surgeryStatusTimeDetailObject["status"+i] ){
			var surgeryStatusTimeBM = {
				surgeryCode : this.configureSurgery.serviceWidgets.getServiceCodeTxf().getValue(),
				surgeryStatus : {code : this.surgeryStatusTimeDetailObject["status"+i][0].name},
				time : this.surgeryStatusTimeDetailObject["status"+i][0].getValue(),
				surgeonRequired :this.surgeryStatusTimeDetailObject["status"+i][1].getValue() === true ? "Y" : "N", 
				userId : getAuthorizedUserInfo().userName
			};	
			
			surgeryStatusTimeBMList.push( surgeryStatusTimeBM );
			i++;
			
		}
		
		
		return surgeryStatusTimeBMList;
	},
	
	/*
	 *  In edit case this method will reset SurgeryStatusTimeDetail Panel.
	 *  
	 */
	
	reset : function(){
		this.totalSurgeryTime = this.initialConfig.totalSurgeryTime;
		var i = 0;
		while( this.surgeryStatusTimeDetailObject["status"+i] ){
			var value =this.surgeryStatusTimeDetailList[i].surgeonRequired === "Y" ? true : false;
			this.surgeryStatusTimeDetailObject["status"+i][0].setValue( this.surgeryStatusTimeDetailList[i].time);	
			this.surgeryStatusTimeDetailObject["status"+i][1].setValue( value );
			i++;
		}
	},
	
	getTotalTime : function(){
		var totalTime = 0*1;
		var i = 0;
		while( this.surgeryStatusTimeDetailObject["status"+i] ){
			totalTime =totalTime + (+this.surgeryStatusTimeDetailObject["status"+i][0].getValue());	
			i++;
		}
		
		return totalTime;
		
	},
	
	getTotalOTTime : function(){
		var totalOTTime = 0*1;
		var i = 0;
		while( this.surgeryStatusTimeDetailObject["status"+i] ){
			if(this.surgeryStatusTimeDetailObject["status"+i][0].contributeToScheduling=="Y"){
				totalOTTime = totalOTTime + (+this.surgeryStatusTimeDetailObject["status"+i][0].getValue());	
			}
			i++;
		}
		
		return totalOTTime;
		
	}
	
});