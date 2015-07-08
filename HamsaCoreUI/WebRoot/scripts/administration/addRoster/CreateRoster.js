CreateRoster = function(config){
	
	if(Ext.isEmpty(config)){
		config = {};
	}
	
	Ext.apply(this,config);
	var mainThis = this;
	
//	this.wtcCalendar = new wtccomponents.wtccalendar.Calendar();
//	var calendar = this.wtcCalendar;
	
	this.rosterMainPnl = new Ext.form.FormPanel({
	 	layout:'column',
	 	height:'100%',
	 	frame:true,
	 	border:false,
	  	buttonAlign:'right',
	  	defaults:{columnWidth:1},
	  	monitorValid:true
	});
	
//	if(config.isPopUp){
//		this.closeButton = {
//			xtype:'button',
//   			text:'Close',
//   			iconCls: 'cross_icon',
//   			scope:this,
//   			handler:function(){
//   				Ext.ux.event.Broadcast.publish('closeAddRosterPanel');
//   			}
//		}
//		
//		this.rosterMainPnl.buttons = [this.closeButton]
//	}
	
	var tempRosterMainPnl =  this.rosterMainPnl;
	var selectedEntityTypeCode;
	var selectedEntityId;
	var calendar;
	
	this.dblClickHandler = function(rosterConfig, viewMode) {
		  var tmpThis = this;
		  this.checkboxGroup = null;
		  var checkBoxFieldSet = null;
	
		  var fromDate = new Ext.form.WTCDateField({
		  	  fieldLabel:msg.fromDate,
			  anchor:'90%',
			  name:'daily_fromdate',
			  value: rosterConfig.date ,
			  listeners:{
			  		change: function(date) {
						if(!Ext.isEmpty(date.getValue())){
//							selectedPeriodStartDate = date.getValue();
							toDate.setMinValue(date.getValue());
							toDate.setValue(date.getValue());
							calendar.setNewDate(date.getValue());
						}
					},
					scope:this
			  }
		  });
		  
		  var toDate = new Ext.form.WTCDateField({
			 fieldLabel:msg.todate,
//			 hideLabel :  true,
			 anchor:'90%',
			 name:'daily_todate',
			 value: rosterConfig.date ,
			 listeners:{
			  		change: function(date) {
			  			if(!Ext.isEmpty(date.getValue())){
//			  				selectedPeriodEndDate = date.getValue();
			  				fromDate.setMaxValue(date.getValue());	
			  			}
					},
					scope:this
	  			}
		  });
		
		var fromTime = new Ext.form.TimeField({
			fieldLabel : msg.fromTime,
			format : 'g:i A',
//			hideLabel :  true,
			name : 'startTime',
			increment: calendar.slotDuration,
			anchor : '90%',
			required:true,
			allowBlank:false,
			value : rosterConfig.startTime,
			listeners:{
				select:function( comp,recod,index ){
					var toTimeValue = getToTime( comp , toTime );
					toTime.setValue( toTimeValue );
				}
			}
		});
		
		var toTime = new Ext.form.TimeField({
			fieldLabel : msg.toTime,
//			hideLabel :  true,
			format : 'g:i A',
			name : 'endTime',
			increment: calendar.slotDuration,
			anchor : '90%',
//			required:true,
			allowBlank:false,
			value : rosterConfig.endTime,
			listeners:{
				select:function( comp,recod,index ){
					var valuesMap = roster.getForm().getValues(); 
					var fromDate = Date.parseDate( 
										valuesMap['daily_fromdate']+
										' '+
										fromTime.getValue(),'d/m/Y g:i A');
										
					var toDate = Date.parseDate( 
										valuesMap['daily_todate']+
										' '+
										comp.getValue(),'d/m/Y g:i A');
					
	                var compareDates = compareTwoDates( fromDate, toDate);
	                
	                if(compareDates){
//	                	var startMin =  (fromDate.format("G")*60)*1 + fromDate.format("i")*1;
//	                	var endMin = 
	                	if( fromDate.format('A') === "PM" && toDate.format('A') === "AM"){
		                	 Ext.Msg.show({
		                		msg : "End time will consider as next date time because end time("+ comp.getValue()+") is less than start time("+valuesMap['startTime']+")",
		                		icon : Ext.MessageBox.WARNING,
		                		buttons : Ext.Msg.OKCANCEL,
		                		width : 300,
		                		fn : function(btn, text){
		                			if(btn === "cancel"){
		                				roster.getForm().reset();
		                			}
		                		}
		                	});
	                	}
	                }
	                else{
	                	comp.clearValue();
	                	comp.focus();
	                }
				}
			}
		});
		
		
		this.checkBoxFieldSet = new Ext.form.FieldSet({
			title : msg.rosterTypeDaily,
			readOnly : true,
			labelWidth : 100,
			items : []
			
		});
		
		this.repeatsCbx = new Ext.form.ComboBox({
			fieldLabel : msg.repeatsCbx,
			displayField:'description',
		    valueField:'code',
			forceSelection:true,
			store: loadRosterModeStore(),
			mode : 'local',
			triggerAction : 'all',
			anchor : '70%',
			name : 'repeats',
			value : rosterConfig.roomno,
			required:true,
			allowBlank:false,
			forceSelection : true,
			listeners : {
				select : function(thisCombo , record , index){
					tmpThis.checkBoxFieldSet.show();
					if( record.data.code === msg.DAILY){
						
						tmpThis.checkBoxFieldSet.setTitle( msg.rosterTypeDaily );
						if( !Ext.isEmpty( tmpThis.checkBoxFieldSet.items.items[0] )){
							tmpThis.checkBoxFieldSet.remove( tmpThis.checkBoxFieldSet.items.items[0] );
						}
						tmpThis.checkBoxFieldSet.hide();
					}
					else if(  record.data.code === msg.WEEKLY ){
						tmpThis.checkBoxFieldSet.setTitle( msg.rosterTypeWeekly );
						if( !Ext.isEmpty( tmpThis.checkBoxFieldSet.items.items[0] )){
							tmpThis.checkBoxFieldSet.remove( tmpThis.checkBoxFieldSet.items.items[0]);
						}
						
				
//						this.checkBoxesAttributes = [{boxLabel : "S", name : "sunday", value : 1, checked : true},
//						                           {boxLabel : "M", name : "monday", value : 2,  checked : false},
//						                           {boxLabel : "T", name : "tuesday", value : 3, checked : false},
//						                           {boxLabel : "W", name : "wednesday", value : 4, checked : false},
//						                           {boxLabel : "T", name : "thursday", value : 5, checked : false},
//						                           {boxLabel : "F", name : "friday", value : 6, checked : false},
//						                           {boxLabel : "S", name : "saturday", value : 7, checked : false}];
						
						tmpThis.checkboxGroup = new Ext.form.CheckboxGroup({
							columns : 7,
							items : [{boxLabel : "S", name : "sunday", value : 0, checked : false},
			                           {boxLabel : "M", name : "monday", value : 1,  checked : true},
			                           {boxLabel : "T", name : "tuesday", value : 2, checked : true},
			                           {boxLabel : "W", name : "wednesday", value : 3, checked : true},
			                           {boxLabel : "T", name : "thursday", value : 4, checked : true},
			                           {boxLabel : "F", name : "friday", value : 5, checked : true},
			                           {boxLabel : "S", name : "saturday", value : 6, checked : false}]
						});
						
						tmpThis.checkBoxFieldSet.add( tmpThis.checkboxGroup );
						tmpThis.checkBoxFieldSet.doLayout();
						
					}
					else if( record.data.code === msg.MONTHLY){
						tmpThis.checkBoxFieldSet.setTitle( msg.rosterTypeMonthly );
						if( !Ext.isEmpty( tmpThis.checkBoxFieldSet.items.items[0] )){
							tmpThis.checkBoxFieldSet.remove( tmpThis.checkBoxFieldSet.items.items[0]);
						}
						
						tmpThis.checkboxGroup = new Ext.form.CheckboxGroup({
							columns : 7,
							items : [{boxLabel : "1", name : "one", value : 1, checked : true},
			                           {boxLabel : "2", name : "two", value : 2,  checked : false},
			                           {boxLabel : "3", name : "three", value : 3, checked : false},
			                           {boxLabel : "4", name : "four", value : 4, checked : false},
			                           {boxLabel : "5", name : "five", value : 5, checked : false},
			                           {boxLabel : "6", name : "six", value : 6, checked : false},
			                           {boxLabel : "7", name : "seven", value : 7, checked : false},
			                           {boxLabel : "8", name : "eight", value : 8, checked : false},
			                           {boxLabel : "9", name : "nine", value : 9,  checked : false},
			                           {boxLabel : "10", name : "ten", value : 10, checked : false},
			                           {boxLabel : "11", name : "eleven", value : 11, checked : false},
			                           {boxLabel : "12", name : "twelve", value : 12, checked : false},
			                           {boxLabel : "13", name : "thirteen", value : 13, checked : false},
			                           {boxLabel : "14", name : "fourteen", value : 14, checked : false},
			                           {boxLabel : "15", name : "fifteen", value : 15, checked : false},
			                           {boxLabel : "16", name : "sixteen", value : 16,  checked : false},
			                           {boxLabel : "17", name : "seventeen", value : 17, checked : false},
			                           {boxLabel : "18", name : "eighteen", value : 18, checked : false},
			                           {boxLabel : "19", name : "nineteen", value : 19, checked : false},
			                           {boxLabel : "20", name : "twenty", value : 20, checked : false},
			                           {boxLabel : "21", name : "twentyOne", value : 21, checked : false},
			                           {boxLabel : "22", name : "twentyTwo", value : 22, checked : false},
			                           {boxLabel : "23", name : "twentyThree", value : 23,  checked : false},
			                           {boxLabel : "24", name : "twentyFour", value : 24, checked : false},
			                           {boxLabel : "25", name : "twentyFive", value : 25, checked : false},
			                           {boxLabel : "26", name : "twentySix", value : 26, checked : false},
			                           {boxLabel : "27", name : "twentySeven", value : 27, checked : false},
			                           {boxLabel : "28", name : "twentyEight", value : 28, checked : false},
			                           {boxLabel : "29", name : "twentyNine", value : 29, checked : false},
			                           {boxLabel : "30", name : "thirty", value : 30, checked : false},
			                           {boxLabel : "31", name : "thirtyOne", value : 31, checked : false},
			                           ]
						});
						
						tmpThis.checkBoxFieldSet.add( tmpThis.checkboxGroup );
						tmpThis.checkBoxFieldSet.doLayout();
						
					}
				}
			}
		});
		
		setCbxDefaultValue( this.repeatsCbx );
		
		
	var roomCbx = new Ext.form.ComboBox({
//			xtype : 'combo',
			fieldLabel : 'Room',
			displayField:'description',
		    valueField:'code',
			forceSelection:false,
			store: loadRoomName( rosterConfig.doctorId ),
			mode : 'local',
			triggerAction : 'all',
			anchor : '70%',
			name : 'roomno',
			required:true,
			allowBlank:false,
			listeners : {}
		});
	
		setCbxDefaultValue( roomCbx );

		var roster = new Ext.form.FormPanel({
			layout : 'column',
			frame : true,
			labelWidth : 80,
			buttonAlign : 'right',
			monitorValid:true,
//			defaults:{columnWidth:.33},
			items : [
	         {
	        	 layout : 'form',
	        	 labelWidth : 80,
	        	 columnWidth :.40,
	        	 items : fromDate
				
			},{
	        	 layout : 'form',
	        	 columnWidth :.40,
	        	 labelWidth : 80,
	        	 items : toDate
				
			},{
				layout : 'form',
				labelWidth:80,
				columnWidth:.40,
				items : fromTime
			}, {
				layout : 'form',
				labelWidth:80,
				columnWidth:.40,
				items : toTime
			},{
				layout : 'form',
				columnWidth : 1,
				items : [roomCbx,{
					layout : 'form',
//					labelWidth : 60,
					columnWidhth : 1,
					items : this.repeatsCbx
				},{
					layout : 'form',
					labelWidth : 80,
					columnWidhth : 1,
					items : this.checkBoxFieldSet
				}]
			}],
			buttons : [{
				text : 'Apply',
				cls : 'x-btn-text-icon',
				icon : 'images/accept.png',
				disabled:true,
				handler : function() {
					var daysList = new Array();
					var values = roster.getForm().getValues();
					var sTime = values['startTime'];
					sTime = Date.parseDate(sTime,"g:i A").format("Hi");
					var eTime = values['endTime'];
					eTime = Date.parseDate(eTime,"g:i A").format("Hi");
					
					var rosterObj = {
						workingDate: rosterConfig.date,
						startTime: sTime,
						endTime: eTime,
						roomBM: {roomCode: roomCbx.getValue()},
						active: true};
						var selectedEntityType;
					
					var valuesMap = tempRosterMainPnl.getForm().getValues(); 
					
					var tmpFromDate = values['daily_fromdate'];
					var tmpToDate = values['daily_todate'];
					var tmpPeriod = tmpThis.repeatsCbx.getValue();/*valuesMap['period'] == 0 ? 'DAILY' : 
				
					valuesMap['period'] == 1 ? 'WEEKLY' : 'MONTHLY'*/;
					
					if( !Ext.isEmpty( tmpThis.checkboxGroup )){
						tmpDaysList = tmpThis.checkboxGroup.getValue();
						for(var i = 0 ; i < tmpDaysList.length ; i++){
							daysList.push(tmpDaysList[i].value);
						}
					}	
	
					var rosterBM = {
						entityType: selectedEntityTypeCode,
						entityId: selectedEntityId,
						rosterTimeBMList: [rosterObj],
						fromDate: Date.parseDate(tmpFromDate,"d/m/Y"),
						toDate: Date.parseDate(tmpToDate, "d/m/Y"),
						period: tmpPeriod,
						daysList : daysList
					};
					
					RosterManager.addRoster(rosterBM, function(ret) {
						rosterdetails.close();
						RosterManager.getRoster(selectedEntityTypeCode, selectedEntityId, null, null,null,null,null,null,null,null, function(data){
							if (data == null) {
								data = Array();
							}
							calendar.createRoster(data);
							calendar.refreshView();
							Ext.ux.event.Broadcast.publish('reloadRosterStore');
							layout.getCenterRegionTabPanel().remove( mainThis.rosterMainPnl, true );
							Ext.ux.event.Broadcast.publish('closeAddRosterPanel');
						});
					});
				}
			}, {
				text : 'Cancel',
				cls : 'x-btn-text-icon',
				icon : 'images/cancel.png',
				handler : function() {
					rosterdetails.close();
				}
			}]
		});
		
		
		roster.on('render',function(thisPanel){
			var record = {data:{code:msg.DAILY}}
			if( viewMode === msg.dayViewMode){
				this.repeatsCbx.setValue(msg.DAILY);
				this.repeatsCbx.fireEvent('select',this.repeatsCbx,record);
			}
			else if(viewMode === msg.weekViewMode){
				this.repeatsCbx.setValue(msg.WEEKLY);
				record.data.code = msg.WEEKLY;
				this.repeatsCbx.fireEvent('select',this.repeatsCbx,record)
			}
			else if(viewMode === msg.monthViewMode){
				this.repeatsCbx.setValue(msg.MONTHLY);
				record.data.code = msg.MONTHLY;
				this.repeatsCbx.fireEvent('select',this.repeatsCbx,record)
			}
		},this);
		var indexOfSelectedDoctor = mainThis.entityNameCbx.getStore().find("code",mainThis.entityNameCbx.getValue());
		var doctorName = mainThis.entityNameCbx.getStore().getAt( indexOfSelectedDoctor ).data.description
		var rosterdetails = new Ext.Window({
					height : 400,
					width : 520,
					modal : true,
					title : "Add roster for " +doctorName,
//					title : (rosterConfig.existing == false) ? 'New Entry' : 'Edit Entry',
					iconCls : (rosterConfig.existing == false) ? 'newevent-icon' : 'editevent-icon',
					closable : false,
					resizable : false
				});
		rosterdetails.add(roster);
		rosterdetails.show();
		
		// for monitoring the proster panel 
		roster.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				// for confirm button
				roster.buttons[0].enable();
			} else {
				// for confirm button
				roster.buttons[0].disable();
			}
		});
	}
			
	this.createPanel = function(){
		Ext.QuickTips.init();
		if (!Ext.isEmpty(this.rosterMainPnl.items)) {
			this.rosterMainPnl.removeAll();
		}
	  

	  this.fromDate = new Ext.form.WTCDateField({
	  	  fieldLabel:msg.fromdate,
		  anchor:'98%',
		  name:'daily_fromdate',
		  value: config.mode == 'edit' ? config.workingDate :  new Date() ,
		  listeners:{
		  		change: function(date) {
					if(!Ext.isEmpty(date.getValue())){
//						selectedPeriodStartDate = date.getValue();
						this.toDate.setMinValue(date.getValue());
						this.toDate.setValue(date.getValue());
						calendar.setNewDate(date.getValue());
					}
				},
				scope:this
		  }
	  });
	  
	  this.toDate = new Ext.form.WTCDateField({
		 fieldLabel:msg.todate,
		 anchor:'98%',
		 name:'daily_todate',
		 value: config.mode == 'edit' ? config.workingDate : new Date() ,
		 listeners:{
		  		change: function(date) {
		  			if(!Ext.isEmpty(date.getValue())){
//		  				selectedPeriodEndDate = date.getValue();
		  				this.fromDate.setMaxValue(date.getValue());	
		  			}
				},
				scope:this
  			}
	  });
	  
//	  this.fromYearCombo = new Ext.form.ComboBox({
//	  	  fieldLabel:'Year',
//	  	  forceSelection:true,
//	  	  hiddenName:'fromyear',
//	  	  anchor:'95%',
//	  	  store:loadYears(),
//	  	  mode:'local',
//	  	  displayField:'description',
//	  	  valueField:'code',
//	  	  triggerAction:'all',
//	  	  listeners:{
//	  		select: function(comp,record,index) {
//		  		var formValues = mainThis.rosterMainPnl.getForm().getValues();
//		  		fromWeekStore.removeAll();
//				mainThis.fromWeekCombo.clearValue();
//		  		if(formValues['frommonth']!=''){
//		  			fromWeekStore.load({params:{start:0, limit:8}, arg:[formValues['frommonth'],record.data.code]});
//		  		}
//		  		mainThis.toYearCombo.setValue(formValues['fromyear']);
//	  			
//	  		}
//	  	}
//	  });
//	  
//	   this.fromMonthCombo = new Ext.form.ComboBox({
//			fieldLabel:'month',
//			forceSelection:true,
//			hiddenName:'frommonth',
//			anchor:'98%',
//			store:loadMonths(),
//			mode:'local',
//			displayField:'description',
//			valueField:'code',
//			triggerAction:'all',
//			listeners:{
//				select: function(comp,record,index) {
// 			  		var formValues = mainThis.rosterMainPnl.getForm().getValues();
// 			  		fromWeekStore.removeAll();
// 			  		mainThis.fromWeekCombo.clearValue();
// 			  		fromWeekStore.load({params:{start:0, limit:8}, arg:[record.data.code,formValues['fromyear']]});
//				}
//			}
//	  });
//	  
//	  this.fromWeekCombo = new Ext.form.ComboBox({
//		  	fieldLabel:'Week',
//		  	forceSelection:true,
//		  	hiddenName:'fromweek',
//		  	anchor:'95%',
//		  	store: loadFromWeeks(),
//		  	mode:'local',
//		  	displayField:'description',
//		  	valueField:'code',
//		  	triggerAction:'all',
//		  	listeners:{
//	  			select : function(comp,record,index) {
//	  				var formValues = mainThis.rosterMainPnl.getForm().getValues();
//	  				var date = getDateRangeOfWeek(parseInt(formValues['fromweek'],10));
//	  				date.setYear(mainThis.fromYearCombo.getValue());
//	  				newAppointmentCalendar.setNewDate(date);
//	  			}
//		  	}
//	  });
//	  
//	  this.toYearCombo = new Ext.form.ComboBox({
//	  	fieldLabel:'Year',
//	  	forceSelection:true,
//	  	hiddenName:'toyear',
//	  	anchor:'95%',
//	  	store:loadYears(),
//	  	mode:'local',
//	  	displayField:'description',
//	  	valueField:'code',
//	  	triggerAction:'all',
//	  	listeners:{
//	  		select: function(comp,record,index) {
//	  			var formValues = mainThis.rosterMainPnl.getForm().getValues();
//				mainThis.toWeekCombo.clearValue();
//		  		toWeekStore.removeAll();
//		  		if(formValues['tomonth']!= ''){
//		  			toWeekStore.load({params:{start:0, limit:8}, arg:[formValues['tomonth'],record.data.code]});
//		  		}
//	  		}
//	  	}
//	  });
//	  
//	   this.toMonthCombo = new Ext.form.ComboBox({
//			fieldLabel:'month',
//			forceSelection:true,
//			hiddenName:'tomonth',
//			anchor:'98%',
//			store:loadMonths(),
//			mode:'local',
//			displayField:'description',
//			valueField:'code',
//			triggerAction:'all',
//			listeners:{
//				select: function(comp,record,index) {
//					var formValues = mainThis.rosterMainPnl.getForm().getValues();
//		  			toWeekStore.removeAll();
//		  			mainThis.toWeekCombo.clearValue();
//		  			toWeekStore.load({params:{start:0, limit:8}, arg:[record.data.code,formValues['toyear']]});
//		  			mainThis.toWeekCombo.clearValue();
//				}
//			}
//	  });
//	  
//	  this.toWeekCombo = new Ext.form.ComboBox({
//		  	forceSelection:true,
//		  	fieldLabel:'Week',
//		  	hiddenName:'toweek',
//		  	anchor:'95%',
//		  	store:loadToWeeks(),
//		  	mode:'local',
//	  		displayField:'description',
//	  		valueField:'code',
//		  	triggerAction:'all'
//	  });
//	this.fromYearForMonthView = new Ext.form.ComboBox({
//	  	fieldLabel:'Year',
//	  	name:'monthlyfromyear',
//	  	anchor:'98%',
//	  	store:loadYears(),
//	  	mode:'local',
//	  	displayField:'description',
//	  	valueField:'code',
//	  	triggerAction:'all',
//	  	listeners:{
//			change : function(field,newValue,oldValue ) {
//				var form = mainThis.rosterMainPnl.getForm().getValues();
//				if(form['monthlyfrommonth']!='' && form['monthlyfromyear']!='') {
//					newAppointmentCalendar.setNewDate (new Date(form['monthlyfromyear'],form['monthlyfrommonth'],1));
//				}
//				mainThis.toYearForMonthView.setValue(form['monthlyfromyear']);
//			}
//		}
//	});
//	this.toYearForMonthView = new Ext.form.ComboBox({
//	  	fieldLabel:'Year',
//	  	name:'monthlytoyear',
//	  	anchor:'98%',
//	  	store:loadYears(),
//	  	mode:'local',
//	  	displayField:'description',
//	  	valueField:'code',
//	  	triggerAction:'all',
//	  	listeners:{
//			change : function(field,newValue,oldValue ) {
//				var form = mainThis.rosterMainPnl.getForm().getValues();
//				if(parseInt(form['monthlytoyear'],10)<parseInt(form['monthlyfromyear'],10)) {
//					Ext.Msg.alert('Roster','Please select correct from period');
//				}else {
//					if(parseInt(form['monthlytomonth'],10)<=parseInt(form['monthlyfrommonth'],10)) {
//						Ext.Msg.alert('Roster','Please select correct period');
//					}
//				}
//			}
//		}
//	});
	  
//	this.createRosterPnl = new Ext.form.FieldSet({
//		layout:'card',
//		activeItem:0,
//		height:'100%',
//		border:false,
//		hidden:config.mode == 'edit' ? true : false,
//		title:msg.applyrosterforperiod,
//		deferredRender:false,
//		renderHidden:true,
//		items:[
//	        	{
//			        xtype:'panel',
//			        layout:'column',
//			        defaults:{
//			        	labelAlign:'left',columnWidth:.3},
//			        items:[
//		 				{
//				 		  	layout:'form',
//				 		  	items:this.fromDate
//			 			},
//				 		{
//					 		layout:'form',
//					 		items:this.toDate
//				 		}
//			 		]
//		        }
//	        	{
//		         	xtype:'panel',
//		         	layout:'column',
//		         	width:'100%',
//		         	defaults:{columnWidth:.48},
//		         	items:[
//						{	
//							
//					 		items:[
//						 		{
//							 		xtype:'fieldset',
//							 		title:'From Period',
//							 		border: true,
//							 		height:'100%',
//							 		style:'margin-bottom:0px',
//							 		items:[
//						 			  {
//					 			  		layout:'column',
//					 			 		border:'false',
//						 			  	defaults:{ labelAlign:'left',columnWidth:.42, labelWidth:40 },
//						 			  	items:[
//						 			  		  {
//							 			  		 layout:'form',
//							 			  		 labelWidth:30,
//							 			  		 columnWidth:.3,
//							 			  		 items:this.fromYearCombo
//						 			  		 },
//						 			  		 {
//							 			  		 layout:'form',
//							 			  		 items:this.fromMonthCombo
//						 			  		 },
//						 			  		 {
//							 			  		 layout:'form',
//							 			  		 labelWidth:35,
//							 			  		 columnWidth:.27,
//							 			  		 items:this.fromWeekCombo
//								  		 	}
//					 		  		 	]
//								  	} 
//					 			]
//							}
//					 	]
//					},
//					{
//						 items:[
//					 		{
//						 		xtype:'fieldset',
//						 		title:'To Period',
//						 		height:'100%',
//						 		border: true,
//						 		style:'margin-bottom:0px;margin-left:10px',
//						 		items:[
//					 			  {
//					 			  	layout:'column',
//					 			  	border:false,
//					 			  	defaults:{ labelAlign:'left',columnWidth:.42, labelWidth:40 },
//					 			  	items:[
//					 			  		 {
//						 			  		 layout:'form',
//						 			  		 labelWidth:30,
//						 			  		 columnWidth:.3,
//						 			  		 items:this.toYearCombo
//					 			  		 },
//					 			  		 {
//						 			  		 layout:'form',
//						 			  		 items:this.toMonthCombo
//					 			  		 },
//					 			  		 {
//						 			  		 layout:'form',
//						 			  		 labelWidth:35,
//						 			  		 columnWidth:.27,
//						 			  		 items:this.toWeekCombo
//					 			  		 }
//				 			  		 ]
//					 			  }
//				 			   ]
//					 		}
//				 		]
//					}
//				]
//	        },
//	        {
//		         xtype:'panel',
//		         layout:'column',
//		         width:'100%',
//		         border:false,
//		         defaults:{columnWidth:.45},
//		         items:[
//					{
//						 items:[
//					 		{
//						 		xtype:'fieldset',
//						 		title:'From Period',
//						 		border: true,
//						 		height:'100%',
//						 		style:'margin-bottom:0px',
//						 		items:[
//					 			  {
//						 			  layout:'column',
//						 			  border:false,
//						 			  defaults:{ labelAlign:'left',columnWidth:.45, labelWidth:40 },
//						 			  items:[
//						 			  	 {
//						 			  		 layout:'form',
//						 			  		 items:this.fromYearForMonthView
//					 			  		 },
//					 			  		 {
//						 			  		 layout:'form',
//						 			  		 items:[
//					 			  		 		{
//					 			  		 			xtype:'combo',
//									 			  	fieldLabel:'month',
//									 			  	name:'monthlyfrommonth',
//									 			  	anchor:'98%',
//									 			  	store:loadMonths(),
//							 			  			mode:'local',
//							 			  			displayField:'description',
//								 			  		valueField:'code',
//									 			  	triggerAction:'all'
//					 			  		 		}
//				 			  		 		] 
//					 			  		 }
//				 			  		  ]
//					 			   } 
//				 			    ]
//					 		 }
//				 		  ]
//						},
//						{
//						 	items:[
//					 			{
//							 		xtype:'fieldset',
//							 		title:'To Period',
//							 		border: true,
//							 		height:'100%',
//							 		style:'margin-bottom:0px;margin-left:10px',
//							 		items:[
//						 			  {
//							 			  border:false,
//							 			  layout:'column',
//							 			  defaults:{ labelAlign:'left',columnWidth:.45, labelWidth:40 },
//						 			  	  items:[
//						 			  	  	 {
//							 			  		 layout:'form',
//							 			  		 items:this.toYearForMonthView
//						 			  		 },
//						 			  		 {
//							 			  		 layout:'form',
//							 			  		 items:[
//						 			  		 		{
//						 			  		 			xtype:'combo',
//										 			  	fieldLabel:'month',
//										 			  	name:'monthlytomonth',
//										 			  	anchor:'98%',
//									 			  		store:loadMonths(),
//									 			  		mode:'local',
//										 			  	displayField:'description',
//										 			  	valueField:'code',
//									 			  		triggerAction:'all',
//									 			  		listeners:{
//								 			  				change : function(field,newValue,oldValue ) {
//								 			  					var form = mainThis.rosterMainPnl.getForm().getValues();
//								 			  					if(form['monthlyfrommonth']=='' || form['monthlyfromyear']=='') {
//								 			  						Ext.Msg.alert('Roster','Please select form period');
//								 			  					}
//								 			  				}
//							 			  				}
//						 			  		 		}
//					 			  		 		]
//						 			  		 }
//					 			  		 ]
//						 			  }
//					 			  ]
//						 		}
//					 		]
//						}
//					]
//		     	}
//			]
//		});
		
		
		this.entityNameCbx = new Ext.form.ComboBox({
			hiddenName:'entityname',
//			readOnly:true,
			fieldLabel:msg.entityname,
			mode:'local',
 			allowBlank:false,
 			required:true,
			anchor:'98%',
			store: loadEntityName(),
			disabled:true,
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
 			value: config.mode === 'edit' ? config.selectedEntityName : '',
			listeners: {
				select:function(comp,record,index) {
					var mainThis = this;
					if(!Ext.isEmpty( mainThis.calendarPanel )){
//						mainThis.calendarPanel.remove( calendar );
						mainThis.rosterMainPnl.remove( mainThis.calendarPanel );
					}
					selectedEntityId = record.data.code;
					//get the selected doctor's rosters
					calendar = new wtccomponents.wtccalendar.Calendar();//this.wtcCalendar;
					calendar.dayViewDblClickHandler = this.dblClickHandler;
//					if(!Ext.isEmpty(this.calendarPanel)){
//						this.rosterMainPnl.remove(this.calendarPanel);
//					}
					this.calendarPanel = new Ext.Panel({
					 	width:'100%', 
					 	layout:'fit',
					 	height:400
				 	});
					
					mainThis.calendarPanel.add(calendar.getPanel());
					mainThis.calendarPanel.doLayout();
					mainThis.rosterMainPnl.add( mainThis.calendarPanel );
					mainThis.rosterMainPnl.doLayout();

					
					loadNewAppointmentCalendar(calendar , new Date().clearTime(),new Date().clearTime() , record.data.code );
					
//					RosterManager.getRoster(
//						selectedEntityTypeCode, 
//						record.data.code, 
//						null, 
//						null,
//						null,
//						null,
//						null,
//						null,
//						null,
//						null, 
//						function(data){
//							if (data == null) {
//								data = Array();
//							}
//							calendar.createRoster(data);
//							calendar.refreshView();
//							mainThis.calendarPanel.add(calendar.getPanel());
//							mainThis.calendarPanel.doLayout();
//							mainThis.rosterMainPnl.add( mainThis.calendarPanel );
//							mainThis.rosterMainPnl.doLayout();
//						});
				},
				scope: this
			}
		});
		
//		if(config.mode !== 'edit'){
//			loadRosterEntities().on('load', function(){
//			mainThis.rosterDetailsPnl.items.items[0].items.items[0].setValue( 'Doctor' );
//		},this);
//		}
		
		this.rosterDetailsPnl = new Ext.form.FieldSet({
			 layout:'column',
			 frame:true,
			 border:false,
			 defaults:{
			 	labelAlign:'left',
			 	labelWidth:80,
			 	columnWidth:.25
		 	 },
			 height:'100%',
			 items:[
//		 		{
//		 		 layout:'form',
//		 		 columnWidth:.4,
//		 		 labelAlign:'left',
//		 		 labelWidth:50,
//		 		 items:
//		 			{
//		 				 xtype:'radiogroup',
//		 				 fieldLabel: msg.period,
//		 				 columns: [.2,.25,.28],
//		           		 items: [
//		                    {boxLabel: msg.daily, name: 'period', inputValue: 0,checked: true},
//		                    {boxLabel: msg.weekly, name: 'period', inputValue: 1},
//		                    {boxLabel: msg.monthly, name: 'period', inputValue: 2}
//		    			 ],
//		    			 name:'periodname',
//		          		 listeners:{
//		          			change : {
//		          				fn : function(radioGroup  ,value){
//		          				if(value === '0'){
//									selectedPeriodInd = "DAILY";
//									mainThis.fromDate.setValue(new Date());
//									mainThis.toDate.setValue(new Date());
//		          				}else if(value === '1'){
//									selectedPeriodInd = "WEEKLY";
//		          				}else if(value === '2'){
//									selectedPeriodInd = "MONTHLY";
//		          				}
//		          					Ext.getCmp(mainThis.createRosterPnl.getId()).layout.setActiveItem(value);
//		          					if(value === '1'){
//		          						var comboConfig = {
//						   					fromyear: (new Date()).getFullYear(),
//						   					frommonth: (new Date()).getMonth(),
//						   					toyear:(new Date()).getFullYear(),
//						   					tomonth: (new Date()).getMonth()
//						   					
//						   				}
//						   				mainThis.rosterMainPnl.getForm().setValues(comboConfig);
//						   				fromWeekStore.load({params:{start:0, limit:8}, arg:[comboConfig.frommonth,comboConfig.fromyear]});
//						   				fromWeekStore.on('load',function(){
//						   					var fromWeekConfig = {
//						   						fromweek:(new Date()).getWeek()
//						   					}
//						   					mainThis.rosterMainPnl.getForm().setValues(fromWeekConfig);
//						   					fromWeekStore.events['load'].clearListeners();
//						   				});
//						   				
//						   				toWeekStore.load({params:{start:0, limit:8}, arg:[comboConfig.tomonth,comboConfig.toyear]});
//						   				toWeekStore.on('load',function(){
//						   					var toWeekConfig = {
//						   						toweek:(new Date()).getWeek()
//						   					}
//						   					mainThis.rosterMainPnl.getForm().setValues(toWeekConfig);
//						   					toWeekStore.events['load'].clearListeners();
//						   				});
//						   				
//		          					}else if(value === '2'){
//		          						var comboConfig = {
//						   					monthlyfromyear: (new Date()).getFullYear(),
//						   					monthlyfrommonth: (new Date()).getMonth(),
//						   					monthlytoyear:(new Date()).getFullYear(),
//						   					monthlytomonth: (new Date()).getMonth()
//						   				}
//						   				mainThis.rosterMainPnl.getForm().setValues(comboConfig);
//		          					}
//		          			        mainThis.createRosterPnl.doLayout();
//		          				}
//		          			}
//		          		}
//		 			}
//		 		},
		 		{
		 		 layout:'form',
		 		 items:[
		 		 		{
		 		 			xtype:'combo',
		 		 			name:'entityType',
		 		 			readOnly:true,
		 		 			fieldLabel:msg.entitytype,
		 		 			mode:'local',
		 		 			anchor:'98%',
		 		 			allowBlank:false,
		 		 			required:true,
		 		 			store: loadRosterEntities(),
		 		 			displayField:'description',
		 		 			valueField:'code',
		 		 			triggerAction:'all',
		 		 			value: config.mode === 'edit' ? config.selectedEntityType : 'Doctor',
		 		 			listeners:{
		 		 			  select:function(comp,record,index) {
		 		 			  	if(!Ext.isEmpty(comp.getValue())){
		 		 			  		this.entityNameCbx.enable();
		 		 			  		this.entityNameCbx.clearValue();
		 		 			  		if(!Ext.isEmpty(this.calendarPanel)){
										this.rosterMainPnl.remove(this.calendarPanel);
									}
		 		 			  	}
		 		 			  	 selectedEntityTypeCode = record.data.code;
		 		 			  	 entityNameStore.load({params:{start:0, limit:8}, arg:[record.data.description]});
		 		 			  },
		 		 			  render : function(){
		 		 				this.entityNameCbx.enable();
		 		 				this.entityNameCbx.clearValue();
		 		 				if(!Ext.isEmpty(this.calendarPanel)){
									this.rosterMainPnl.remove(this.calendarPanel);
								}
		 		 				entityNameStore.load({params:{start:0, limit:8}, arg:['Doctor']});
		 		 			  },
		 		 			  scope: this
		 		 			}
		 		 		}
		 			]
		 		},
		 		{
		 			layout:'form',
		 			columnWidth:.3,
		 			labelWidth:90,//60,
		 			items:[this.entityNameCbx]
		 		},
//		 		{
//		 		  	layout:'form',
//		 		  	columnWidth:.22,
//		 		  	labelWidth:65,//60,
//		 		  	items:this.fromDate
//	 			},
//		 		{
//			 		layout:'form',
//			 		columnWidth:.22,
//			 		labelWidth:60,
//			 		items:this.toDate
//		 		}
	 	 	]
	 	});
		
//		this.createRosterWindow = new Ext.Window({
//		   	title: (config['mode']=='edit'&& config['flag']==false)?
//		   			'Edit Roster':
//		   			((config['mode']=='edit' && config['flag']==true)?
//		   			'View Roster':'Add Roster'),
//		   	height:'70%',
//	        width:'80%',
//	        modal :true,
//	        closable:true,
//	        closeAction:'hide',
//	        draggable: false,
//	   		resizable :false,
//	   		y:0
//		});
		
		//&& newAppointmentCalendar.store.data.items.length>=1
//		this.rosterMainPnl.on("clientvalidation", function(thisForm, isValid) {
//			if(this.isPopUp){
//				if (isValid ){
//					this.rosterMainPnl.buttons[0].enable();
//				} else {
//					this.rosterMainPnl.buttons[0].disable();
//				}
//			}
//		}, this); 
	
//	 this.rosterMainPnl.add(this.createRosterPnl);
	 
 	 this.rosterMainPnl.add(this.rosterDetailsPnl);
//	 this.createRosterWindow.add(this.rosterMainPnl);
	 
	 this.rosterMainPnl.doLayout();
//	 this.createRosterWindow.doLayout();
	};
	
	this.getPanel = function(){
		return this.rosterMainPnl;
	};
	
	this.loadPanel = function() {
		this.createPanel();
//		this.rosterMainPnl.doLayout();
//		this.createRosterWindow.doLayout();
		
//		if( this.isPopUp ){
//			return this.createRosterWindow
//		}
		return this.rosterMainPnl
	}
}

// function getAppointmentGrid(manageDataStore) {
//
//	var bbar = new Ext.PagingToolbar({
//				pageSize : 5,
//				store : manageAppointmentDataStore,
//				displayInfo : true
//			});
//
//	var manageAppointmentGridPnl_rosterPnl = new Ext.grid.GridPanel({
//				autoScroll : true,
//				stripeRows : true,
//				border : false,
//				frame : true,
//				height : 160,
//				ds : manageDataStore,
//				cm : new Ext.grid.ColumnModel([{
//							header : "S.No.",
//							width : 50,
//							sortable : true,
//							dataIndex : 'serialNo'
//						}, {
//							header : "Appointment No.",
//							width : 50,
//							sortable : true,
//							hidden : true,
//							dataIndex : 'appointmentNo'
//						}, {
//							header : "Patient Name",
//							width : 150,
//							sortable : true,
//							dataIndex : 'patientName'
//						}, {
//							header : "Patient First Name",
//							width : 150,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'patientFirstName'
//						}, {
//							header : "Patient Middle Name",
//							width : 150,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'patientMiddleName'
//						}, {
//							header : "Patient Last Name",
//							width : 150,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'patientLastName'
//						}, {
//							header : "Patient ID",
//							width : 100,
//							sortable : true,
//							dataIndex : 'patientId'
//						}, {
//							header : "Appointment Date",
//							dataIndex : 'appointmentDate',
//							renderer : Ext.util.Format.dateRenderer('d/m/Y')
//						}, {
//							header : "From Time",
//							width : 80,
//							sortable : true,
//							dataIndex : 'appointmentStartTime'
//						}, {
//							header : "To Time",
//							width : 80,
//							sortable : true,
//							dataIndex : 'appointmentEndTime'
//						}, {
//							header : "Appointment Status",
//							width : 125,
//							sortable : true,
//							dataIndex : 'appointmentStatus'
//						}, {
//							header : "Appointment Status Code",
//							width : 100,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'appointmentStatusCode'
//						}, {
//							header : "Amount",
//							width : 100,
//							sortable : true,
//							dataIndex : 'amount'
//						}, {
//							header : "Department Name",
//							width : 125,
//							sortable : true,
//							dataIndex : 'department'
//						}, {
//							header : "Department Code",
//							width : 125,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'departmentCode'
//						}, {
//							header : "Booking Type",
//							width : 100,
//							sortable : true,
//							dataIndex : 'bookingType'
//						}, {
//							header : "Booking Type Code",
//							width : 100,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'bookingTypeCode'
//						}, {
//							header : "Doctor Name",
//							width : 100,
//							sortable : true,
//							dataIndex : 'primaryDoctor'
//						}, {
//							header : "Doctor Code",
//							width : 100,
//							hidden : true,
//							sortable : true,
//							dataIndex : 'primaryDoctorCode'
//						}]),
//
//				bbar : bbar
//			});
//			return manageAppointmentGridPnl_rosterPnl;
// }
 
//function changeFromDate(newdate) {
//	obj = {daily_fromdate: newdate }
//	var form =getCreateRoster().items.items[0];
//	form.getForm().setValues(obj);
//}

//function getRosterStore(config) {
//	var rosterData = newAppointmentCalendar.store.data.items;
//	var rosterTimeBMList = [];
//	
//	if(rosterData.length == 0){
//		return false;
//	}else if(rosterData.length > 0){
//		for( var count = 0; count<newAppointmentCalendar.store.getCount(); count++){
//			var rdata = rosterData[count].data;
//			var sdate = rdata.startdate;
//			var startDate = sdate.split(" ");
//			var edate = rdata.enddate;
//			var endDate = edate.split(" ");
//	 	    var rosterTimeBM = {
//				workingDate:  convertCalendarStringDateToDateFormat(startDate[0]),
//				startTime: convertTimeTo24HrsFormat(sdate),
//			 	endTime: convertTimeTo24HrsFormat(edate),
//			 	roomBM: {roomCode :rosterData[count].data.location}
//	  		}
//	  		if(rdata.active ==="on") {
//	  			rosterTimeBM.active = true;
//	  		}else {
//	  			rosterTimeBM.active = false;
//	  		}
//	  		if(!Ext.isEmpty(config) && config.mode=='edit') {
//	  			rosterTimeBM.rosterCode = config.selectedRosterId;
//	  		}
//	  		rosterTimeBMList[count] = rosterTimeBM;
//	    }
//	    return rosterTimeBMList;
//	}
//}
//
// function getDoctorRoster( date, doctorId, calendar){
//	RosterManagementController.getDoctorRoster(date, doctorId ,function(doctorRosterList){
// 		if(doctorRosterList!=null && doctorRosterList.length >0){
//			var dataStore = calendar.store;
//			dataStore.removeAll();
//			for(var i =0; i<doctorRosterList.length;i++){
//				var data = doctorRosterList[i];
//				var rosterRecord =  calendar.store.recordType;
//				var roster = {
//					startdate:data.startTime,
//					enddate:data.endTime,
//					subject:data.roomno.description,//data.agenda,
//					color:data.color,
//					parent:data.parent,
//					priority:data.priority,
//					active:data.active,
//					roomno:{code:data.code,description:data.description}
//				};
//				if(data.remarks!=null) {
//					roster.description =data.remarks;
//				}else {
//					roster.description ='';
//				}
//				var roster= new rosterRecord(roster)
//				
//				dataStore.insert(i, roster);
//			}
// 		}
// 		calendar.setNewDate (date);
//		calendar.refreshCalendarView();
// 	});
// }
// 
// function loadCalendarInEditViewMode(rosterData,calendar){
//	var dataStore = calendar.store;
//	var rosterRecord =  calendar.store.recordType;
//	dataStore.removeAll();
//	var roster = {
//		startdate:rosterData.selectedStartTime,
//		enddate:rosterData.selectedEndTime,
//		subject:rosterData.selectedRoomNo,//data.agenda,
//		color:'#99cc00',
//		parent:0,
//		priority:1,
//		active:rosterData.selectedActive,
//		roomno:{code:rosterData.selectedRoomNoId,description:rosterData.selectedRoomNo}
//	};
//	if(rosterData.remarks!=null) {
//		roster.description =rosterData.remarks;
//	}else {
//		roster.description ='';
//	}
//	var roster= new rosterRecord(roster)
//	dataStore.insert(0, roster);
//	calendar.setNewDate (rosterData.workingDate);
//	calendar.refreshCalendarView();
// }