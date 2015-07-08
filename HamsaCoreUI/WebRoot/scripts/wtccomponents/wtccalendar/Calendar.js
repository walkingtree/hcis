Ext.namespace("wtccomponents.wtccalendar");

wtccomponents.wtccalendar.Calendar = Ext.extend(Object, {
	constructor : function(config) {

		Ext.QuickTips.init();
		
		var tmpThis = this; 
		this.appointmentColorCode = "#FFFF00";
		this.rosterColorCode = "#33FF99";
		this.rosterNotAvailColorCode = "#FF0000";
		this.slotDuration = 20; //always in minutes
		this.workBeginTime = "00:00AM"; //this must be in g:iA date format
		this.workEndTime = "11:40PM"; //this must be in g:iA date format
		this.currentDate = new Date();
		this.currentDate = this.currentDate.clearTime();
		this.viewMode = msg.dayViewMode;
		this.dayViewDblClickHandler = null;
		this.rosterRecords = Array();
		
		var dayFillerCount = 0;
		var dayCount = 1;
		var currDate = this.currentDate;
		var firstDateOfMonth = currDate.getFirstDateOfMonth();
		var lastDateOfMonth = currDate.getLastDateOfMonth();
		var prevMonthDate = currDate;
		prevMonthDate.setMonth(currDate.format('n') - 1);
		var lastDateOfPrevMonth = new Date(firstDateOfMonth.getTime() - (24*60*60*1000));
		
		var thisMonthDateArray = null;
		var prevMonthDateArray = null;
		var nextMonthDateArray = null;
		var nextMonth = null;
		var isThisCurrentMonth = null;
		this.doctorId = null;
		
		/**
		 *  This template will be used if the viewMode is "month". 
		 *  getDay method is responsible to get day of the selected month and fill the cells of the grid.Based on the month this method will return days.
		 *  If viewMode is "day" and Appointment is booked in slot then that slot will shown in yellow. And if roster is 
		 *  available in slot then that slot will be shown in green   
		 *  
		 *  In "week" viewMode  this color code is same as day.
		 *  In "month" viewMode appointmentCount and rosterCount will bill shown for particular date.
		 *  If appointmentCount is greater than 0 and rosterount is greater than 0 then color code will yellow.
		 *  If roster count is greater greater than 0 and appointment count is 0 then coor code will be green.
		 *  If appointmentCount is greater than 0  and rosterCount is 0 then color code will be red.
		 *  
		 */
		
		this.monthViewTpl = new Ext.XTemplate(
			'<div class="st-dtitle">{[this.getDay(this)]}</div>',
			{
				getDay: function(element){
					var div1 = '';
					var div2 = '';
					if (dayFillerCount < firstDateOfMonth.format('w')) {
						var ret = lastDateOfPrevMonth.format('j') - (firstDateOfMonth.format('w') -1 - dayFillerCount);
						dayFillerCount++;
						return ret;
					}
					if (dayCount > lastDateOfMonth.format('j')) {
						dayCount = 1;
						nextMonth = new Date(lastDateOfMonth.getTime()+ (24*60*60*1000)).format('n');
						isThisCurrentMonth = false;
						nextMonthCount = dayCount;
						dayCount++;
						return nextMonthCount;
					}
					var tmp ;
					var rosterCount = this.getRosterCount( dayCount );
					var appointmentCount = this.getAppointmentCount( dayCount );
					rosterCount = rosterCount - 2*appointmentCount;
					if( appointmentCount > 0 ){
						div1 = '<div style = "background-color:'+tmpThis.appointmentColorCode+'; text-align:center;  width:100%;">Available: '+rosterCount +'</div>';
						div2 = '<div style = "background-color:'+tmpThis.appointmentColorCode+'; text-align:center;  width:100%;">Booked: '+appointmentCount +'</div>';
					}
					else if( rosterCount > 0 ){
						div1 = '<div style = "background-color:'+tmpThis.rosterColorCode+'; text-align:center;  width:100%;">Available: '+rosterCount +'</div>';
						div2 = '<div style = "background-color:'+tmpThis.rosterColorCode+'; text-align:center;  width:100%;">Booked: '+appointmentCount+'</div>';
					}
					if( rosterCount === 0 ){
						div1 = '<div style = "background-color:'+tmpThis.rosterNotAvailColorCode+'; text-align:center;  width:100%;">Available: '+rosterCount +'</div>';
						div2 = '<div style = "background-color:'+tmpThis.rosterNotAvailColorCode+'; text-align:center;  width:100%;">Booked: '+appointmentCount +'</div>';
					}
					if( tmpThis.isContains(thisMonthDateArray, dayCount) && isThisCurrentMonth){
						tmp = '<div style = "width:100%;">'+dayCount+'</div>' + div1+div2;
					}
					else{
						tmp = dayCount;
					}
					
					dayCount++;
					return tmp;
				},
				
				/**
				 *  This method will be used to count roster available for the doctor in particular date in a month.
				 *  This method will first get end time and start time for the roster and then calculate numberOfSlot based on this endTime 
				 *  and startTime. And then this method will increase rosterCount by this numberOfSlot. 
				 *    
				 *   
				 */
				getRosterCount : function( dayOfTheMonth ){
					if( !Ext.isEmpty(  thisMonthDateArray["Day"+dayOfTheMonth] )){
						var rosterCount = 0;
						for( var i = 0 ; i < thisMonthDateArray["Day"+dayOfTheMonth].length ; i++){
							var timeInfo = thisMonthDateArray["Day"+dayOfTheMonth][i].split("|");
							
							var tmpStartTime = Date.parseDate(timeInfo[0], "g:iA");
							var tmpOrigStartTime = Date.parseDate(timeInfo[0], "g:iA");
							var tmpOrigEndTime = Date.parseDate(timeInfo[1], "g:iA");
							var endMin = (tmpOrigEndTime.format("G")*60)*1 + tmpOrigEndTime.format("i")*1;
							
							if( endMin === 0){
								endMin = 1440;
							}
							
							var startMin = (tmpStartTime.format("G")*60)*1 + tmpStartTime.format("i")*1;

							
							var numSlots = 1 * ((endMin -startMin)/tmpThis.slotDuration);
							
							rosterCount = rosterCount + numSlots;
						}
						return rosterCount;
					}
				},
				
/**
 *  This method will be used to count total number of appointment available in particular date. 
 *  This method will check appointment number is available or not for the date ;
 */
				getAppointmentCount : function( dayOfTheMonth ){
					if( !Ext.isEmpty(  thisMonthDateArray["Day"+dayOfTheMonth] )){
					var appointmentCount = 0;
						for( var i = 0 ; i < thisMonthDateArray["Day"+dayOfTheMonth].length ; i++){
							var appointmentInfo = thisMonthDateArray["Day"+dayOfTheMonth][i].split("|");
							
							if( !Ext.isEmpty( appointmentInfo[2] )){
								appointmentCount++;
							}
						}
						return appointmentCount;
					}	
				}
			}
		);
/**
 * 		//each item in the array corresponds to RosterTimeBM 
 *		//and the field names must be as stated in rosterRecordFields
 *		This method will add information about the roster and appointment into the records.
 *		This information will be used to show roster and appointment into the calendar.
 */
		this.loadRosters = function() {
			if (this.viewMode === "day" ) {
				for (var i = 0; i < this.rosterRecords.length; i++) {
					var tmpRec = this.rosterRecords[i];
					var tmpEndTime = Date.parseDate(tmpRec.endTime, "g:iA");
					var tmpStartTime = Date.parseDate(tmpRec.startTime, "g:iA");
					
					var index = this.dataStore.findBy(function(rec, id){
						if (rec.data.startTime === tmpStartTime.format("h:i A") && rec.data.date.format('l, F j, Y') ===tmpRec.workingDate.format('l, F j, Y')) {
							return true;
						}
					});
					if (index > -1) {
						var temp = this.dataStore.getAt(index);
						temp.data.date = tmpRec.workingDate,
						temp.data.room = tmpRec.roomBM,
						temp.data.slotType = tmpRec.isAppointment != null && tmpRec.isAppointment ? '':'AVAILABILITY';
						temp.data.originalStartTime = tmpRec.originalStartTime;
						temp.data.originalEndTime = tmpRec.originalEndTime;
						temp.data.patientId = tmpRec.patientId;
						temp.data.appointmentNumber = tmpRec.appointmentNumber;
						temp.data.bookingType = tmpRec.bookingType;
						temp.data.entityName = tmpRec.entityName;
					}
				}
			}
/**
 *  	If viewMode is week Row Array object will be created with its row index number. 
 *  	This Row array object will contain information of the columns which is having rosters or appointment.
 *  	This information will be used(in renderer) to show roster and appointment in calendar in "week" view Mode.  
 */			
			if( this.viewMode === "week"){
				for (var i = 0; i < this.rosterRecords.length; i++) {
					var tmpRec = this.rosterRecords[i];
					var tmpEndTime = Date.parseDate(tmpRec.endTime, "g:iA");
					var tmpStartTime = Date.parseDate(tmpRec.startTime, "g:iA");
					
					var index = this.dataStore.findBy(function(rec, id){
						if (rec.data.startTime === tmpStartTime.format("h:i A")) {
							return true;
						}
					});
					if (index > -1) {
						var temp = this.dataStore.getAt(index);
						if( Ext.isEmpty( temp.data["Row"+index] )){
							temp.data["Row"+index] = new Array();
						}
						temp.data.slotType = tmpRec.isAppointment != null && tmpRec.isAppointment ? '':'AVAILABILITY';
						temp.data["Row"+index].push( tmpRec.workingDate.format("D j/n") +"|"+ tmpRec.isAppointment+"|"+tmpRec.appointmentNumber+"|"+tmpRec.entityName+"|"+tmpRec.patientId+"|"+tmpRec.agenda );
					}
				}
			} 
		};
		
		//rosterTimeBMArr - array of RosterTimeBM objects
/**
 * 	This method will be used to create records with roster and appointment information.
 * 	rosterTimeBMArr contains all the roster and appointment list 
 */		
		this.createRoster = function(rosterTimeBMArr) {
			if (rosterTimeBMArr != null) {
//				if (rosterTimeBMArr.length === 0) {
					this.rosterRecords = new Array();
//				}
					
				for (var i = 0; i < rosterTimeBMArr.length; i++) {
					var tmpStartTime = Date.parseDate(rosterTimeBMArr[i].startTime, "Hi");
					var tmpOrigStartTime = Date.parseDate(rosterTimeBMArr[i].startTime, "Hi");
					var tmpOrigEndTime = Date.parseDate(rosterTimeBMArr[i].endTime, "Hi");
					var endMin = (tmpOrigEndTime.format("G")*60)*1 + tmpOrigEndTime.format("i")*1;
					
					if( tmpOrigEndTime.format('j') >  tmpOrigStartTime.format('j')){
						if( endMin === 0){
							endMin = 1440;
						}
					}
					
					var startMin = (tmpStartTime.format("G")*60)*1 + tmpStartTime.format("i")*1;

					var numSlots = 1 * ((endMin - startMin)/this.slotDuration);
					
					tmpEndTime = new Date(tmpStartTime.getTime() + (this.slotDuration*60*1000)); 
					
					for (var j = 0; j < numSlots; j++) {
						var tmpRec = {
							originalStartTime: tmpOrigStartTime.format("h:i A"),
							originalEndTime: tmpOrigEndTime.format("h:i A"),
							rosterCode : rosterTimeBMArr[i].rosterCode,
							workingDate : rosterTimeBMArr[i].workingDate,
							startTime : tmpStartTime.format("g:iA"),
							endTime : tmpEndTime.format("g:iA"),
							roomBM : rosterTimeBMArr[i].roomBM,
							isAppointment : rosterTimeBMArr[i].isAppointment,
							patientId : rosterTimeBMArr[i].patientId,
							appointmentNumber : rosterTimeBMArr[i].appointmentNumber,
							bookingType : rosterTimeBMArr[i].bookingType,
							entityName : rosterTimeBMArr[i].entityName,
							entityId : rosterTimeBMArr[i].entityId,
							agenda : rosterTimeBMArr[i].agenda
						};
						this.rosterRecords.push(tmpRec);
						
						tmpStartTime = new Date(tmpStartTime.getTime()*1 + (this.slotDuration*60*1000)*1);
						tmpEndTime = new Date(tmpStartTime.getTime()*1 + (this.slotDuration*60*1000)*1);
					}
				}
				
				//this.loadRosters();
			}
		};


		this.dataStore = new Ext.data.Store({
		});
		
/**
 *  This method is responsible to generate view of of the calendar. Based on the view it will generate Columns and rows.
 *  
 */		

		this.generateView = function() {
				dayFillerCount = 0;
				dayCount = 1;
				nextMonth = null;
				isThisCurrentMonth = true;
				firstDateOfMonth = this.currentDate.getFirstDateOfMonth();
				lastDateOfMonth = this.currentDate.getLastDateOfMonth();
				lastDateOfPrevMonth = new Date(firstDateOfMonth.getTime() - (24*60*60*1000));
				var testCount = 0;

				this.dataStore.removeAll();
				if (!Ext.isEmpty(this.panel.items)) {
					this.panel.removeAll();
				}
				
/**
 *  If viewMode is month then rosterCount and appointmentCount will be shown in the calendar.
 *  To show this count thisMonthDateArray object will be created . This object will contains only those dates 
 *  which is having roster or appointment with its(roster and appointment) information. This will be used in getRosterCount 
 *  and getAppointmentCount method.
 */				
				
				
				if( this.viewMode === "month"){
					thisMonthDateArray = {};
					prevMonthDateArray = new Array();
					nextMonthDateArray = new Array();
					var nextMonthDate = new Date(lastDateOfMonth.getTime() + (24*60*60*1000));
//					var nextMonth = nextMonthDate.format('n');
					
					for(var i = 0 ; i < this.rosterRecords.length ; i++){
						var tmpRec = this.rosterRecords[i];
						var appointmentNbr = Ext.isEmpty(tmpRec.appointmentNumber)?"":tmpRec.appointmentNumber;
						if( tmpRec.workingDate.format("n") === this.currentDate.format("n")){
//							thisMonthDateArray.push(tmpRec.workingDate.format('j'));
							var workingDay = tmpRec.workingDate.format('j');
							if( Ext.isEmpty( thisMonthDateArray["Day"+workingDay] )){
								thisMonthDateArray["Day"+workingDay] = new Array();
								thisMonthDateArray[""+testCount] = tmpRec.workingDate.format('j');
//								thisMonthDateArray["Day"+workingDay].push(tmpRec.workingDate.format('j'));
								thisMonthDateArray["Day"+workingDay].push(tmpRec.startTime +"|"+tmpRec.endTime+"|"+appointmentNbr);
//								thisMonthDateArray["Day"+workingDay].push(tmpRec.isAppointment);
								testCount++;
							}
							else if( !Ext.isEmpty( thisMonthDateArray["Day"+workingDay] )){
								thisMonthDateArray["Day"+workingDay].push(tmpRec.startTime +"|"+tmpRec.endTime+"|"+appointmentNbr);
							}
						}
						
						// This is for previous month date count which is having roster.
						
						if( tmpRec.workingDate.format("n") === lastDateOfPrevMonth.format('n')){
							var dayOfTheWeekForFirstDate = firstDateOfMonth.format('w');
							var prevMonthDayCount = 1;
							for( var j = 0 ; j < dayOfTheWeekForFirstDate ;j++){ 
								var prevMonthDate = lastDateOfPrevMonth.format('j') - (dayOfTheWeekForFirstDate - prevMonthDayCount);
								if( prevMonthDate === tmpRec.workingDate.format('j')){
									prevMonthDateArray.push(prevMonthDate);
								}
								prevMonthDayCount++;
							}
						}
						
						// this is for the next month date Array which is having 
						
						if( tmpRec.workingDate.format('n') === nextMonthDate.format('n')){
							var dayOfTheWeekForTheLastDate = lastDateOfMonth.format('w');
							var nextMonthDayCount = 1;
							for( var k = dayOfTheWeekForTheLastDate ; k < 7 ; k++){
								if( nextMonthDayCount === tmpRec.workingDate.format('j')){
//									nextMonth = nextMonthDate.format('n');
									nextMonthDateArray.push(nextMonthDayCount);
								}	
								nextMonthDayCount++;
							}
						}
					}
				}
				// This will generate columns based on the viewMode.
				var columns = this.getColumns();
				// this will geerate rows based on the viewMode.
				var recArr = this.generateRows();

				this.dataStore.add(recArr);
			
				calendarGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				autoHeight : true,
				columnLines: true,
				iconCls : 'add_btn',
				cls: (this.viewMode === "month")?'calendar-grid':'',
				//width: 600,
				border : false,
				store : this.dataStore,
				columns : columns,
				viewConfig: {forceFit: true},
				listeners:{
					cellcontextmenu: function(thisGrid, rowIndex, columnIndex, eventObj) {
					
/**
 *  Whenever someone right click on the this(cellcontextmenu) event will be generated and call will come to this place 
 *  If the viewMode is month then this will show detail about the clicked cell date . 
 *  viewMode will be changed into "day" viewMode and it will show roster and appointment details for the clicked date. 
 */					
						document.oncontextmenu = function(){
							return false;
						}
						if( this.viewMode === "month"){
							var clickedDate = null;
							if(  !Ext.isEmpty( eventObj.target.previousSibling )){
								if( !Ext.isEmpty( eventObj.target.previousSibling.previousSibling )){
									clickedDate = eventObj.target.previousSibling.previousSibling.textContent;
								}
								else{
									clickedDate =eventObj.target.previousSibling.textContent;
								}
							}
							else{
								clickedDate =eventObj.target.textContent;
							}
							
							if( clickedDate > 22 && rowIndex === 0){
								this.currentDate = new Date(lastDateOfPrevMonth.setDate(parseInt( clickedDate )));
							}
							else if( clickedDate < 7 && (rowIndex === 4 || rowIndex === 5)){
								this.currentDate = new Date(lastDateOfMonth.getTime() + (24*60*60*1000*parseInt( clickedDate )));
							}
							else{
								this.currentDate = new Date(this.currentDate.setDate( parseInt( clickedDate )));
							}	
							this.viewMode = 'day';
							this.changeDayMonthYearLabel();
							this.generateView();
							this.loadRosters();
						}
					},

/**
 *  Whenever someone double click on the this(celldblclick) event will be generated and call will come to this place
 *  This will show add roster window . 
 *  This gets the textContent of the clicked cell and based on the textContent it populates date into date field in Add roster window.
 */
					
					celldblclick: function(thisGrid, rowIndex, colIndex, eventObj) {
						var record = thisGrid.store.getAt(rowIndex);
						var dateToPopulate = this.currentDate.clone();
						if( this.viewMode === "day"){
							dateToPopulate = this.currentDate;
						}
						else if( this.viewMode === "week"){
							var clickedCellHeader = thisGrid.colModel.getColumnHeader( colIndex );
							var clickedDayAndMonth = clickedCellHeader.substr(clickedCellHeader.indexOf(" ")+1).split("/");
							var clickedDay = parseInt(clickedDayAndMonth[0]);
							var clickedMonth = parseInt(clickedDayAndMonth[1]);
							var currentDay = this.currentDate.format('j');
							var currentMonth = this.currentDate.format('n');
							if( this.currentDate.format('n') === clickedMonth){
								if( this.currentDate.format('j') === clickedDay){
									dateToPopulate = this.currentDate;
								}
								else{
									if( clickedDay > this.currentDate.format('j')){
										dateToPopulate = new Date(this.currentDate.getTime() + (24*60*60*1000*(clickedDay  - this.currentDate.format('j'))));
									}
									else {
										dateToPopulate = new Date(this.currentDate.getTime() - (24*60*60*1000*( this.currentDate.format('j')  -  clickedDay )));
									}
								}
								
							}
							else{
								var dayDiff = null;
								if( currentMonth > clickedMonth){
									dayDiff = (this.currentDate.format('w')) - colIndex;
									dateToPopulate = new Date(firstDateOfMonth.getTime() - (24*60*60*1000*(dayDiff)));
								}
								else{
									dayDiff = colIndex - (this.currentDate.format('w') +1)  ;
									dateToPopulate = new Date(this.currentDate.getTime() + (24*60*60*1000*(dayDiff)));
								}
							}
						}
						else if( this.viewMode === "month"){
							var clickedDate = null;
							if(  !Ext.isEmpty( eventObj.target.previousSibling )){
								if( !Ext.isEmpty( eventObj.target.previousSibling.previousSibling )){
									clickedDate = eventObj.target.previousSibling.previousSibling.textContent;
								}
								else{
									clickedDate =eventObj.target.previousSibling.textContent;
								}
							}
							else{
								clickedDate =eventObj.target.textContent;
							}
								
							if( clickedDate > 22 && rowIndex === 0){
								dateToPopulate = new Date(lastDateOfPrevMonth.setDate(parseInt( clickedDate )));
							}
							else if( clickedDate < 7 && (rowIndex === 4 || rowIndex === 5)){
								dateToPopulate = new Date(lastDateOfMonth.getTime() + (24*60*60*1000*parseInt( clickedDate )));
							}
							else{
								dateToPopulate = new Date(dateToPopulate.setDate( parseInt( clickedDate )));
							}	
						}
						
//						var rosterConfig;
						if (Ext.isEmpty(record.data.originalStartTime)) {
							rosterConfig = {existing: false, date: dateToPopulate, startTime : record.data.startTime,endTime : record.data.endTime, doctorId : tmpThis.doctorId};
						} else {
							rosterConfig = {existing: true, date: dateToPopulate, startTime : record.data.originalStartTime,endTime : record.data.originalEndTime, roomno: record.data.room.roomCode, doctorId : tmpThis.doctorId};
						}
						if (this.dayViewDblClickHandler) this.dayViewDblClickHandler(rosterConfig, this.viewMode);
					},
					scope: this
				}
				});		
					
				this.panel.add(calendarGrid);
				this.panel.doLayout();
		}
		
		/*
		 *  This Button will be used to show the calendar in "day" viewMode.
		 *  
		 */
		
		this.dayViewBtn = new Ext.Toolbar.Button({
			iconCls : 'day_view_icon',
			tooltip : 'Day view',
			scope:this,
			handler : function() {
				this.viewMode = 'day';
				this.changeDayMonthYearLabel();
				this.generateView();
				this.loadRosters();
			}
		});
		
/*
 *  This button will be used to show the calendar in WEELY basis.
 *  
 */
		this.weekViewBtn = new Ext.Toolbar.Button({
			iconCls : 'week_view_icon',
			tooltip : 'Week view',
			scope:this,
			handler : function() {
				this.viewMode = 'week';
				this.changeDayMonthYearLabel();
				loadNewAppointmentCalendar( this , this.dateForFirstDayOfTheWeek , this.dateForLastDayOfTheWeek , this.doctorId );
//				this.generateView();
//				this.loadRosters();
			}
		});

		
/*
 *   This button will be used to show calendar in Monthly view. 
 *   In month view all the columns will show week day(From Sunday to Saturday).
 *   And Grid cell will show day of the month.
 *   In this view appointmentCount and rosterCount will be shown in grid cell.
 */
		this.monthViewBtn = new Ext.Toolbar.Button({
			iconCls : 'month_view_icon',
			tooltip : 'Month view',
			scope:this,
			handler : function() {
				this.viewMode = 'month';
				firstDateOfMonth = this.currentDate.getFirstDateOfMonth();
				lastDateOfMonth = this.currentDate.getLastDateOfMonth();
				this.changeDayMonthYearLabel();
				loadNewAppointmentCalendar( this , firstDateOfMonth , lastDateOfMonth , this.doctorId );
//				this.generateView();
			}
		});

/*
 *  This button will be used to select Today's date. 
 *  This will change "day" viewMode and select Today's date.
 */		
		
		this.todayBtn = new Ext.Toolbar.Button({
			iconCls : 'calendar_icon',
			tooltip : 'Today',
			text: 'Today',
			scope:this,
			handler : function() {
				this.viewMode = 'day';
				this.currentDate = new Date();
				this.changeDayMonthYearLabel();
				this.currentDate = this.currentDate.clearTime();
				loadNewAppointmentCalendar( this , this.currentDate , this.currentDate , this.doctorId );
//				this.generateView();
//				this.loadRosters();
			}
		});
		
/*
 *  
 */
		
		this.selectdatefromSelector = function(dp, dateval){ 
			this.currentDate = dateval;
			this.viewMode = 'day';
			this.changeDayMonthYearLabel();
			loadNewAppointmentCalendar( this , this.currentDate , this.currentDate , this.doctorId );
//			this.generateView();
//			this.loadRosters();
		}
		
		this.dateSelectionMenu = new Ext.menu.DateMenu();
		this.dateSelectionMenu.picker.setValue(this.currentDate);
		this.dateSelectionMenu.addListener('select', this.selectdatefromSelector , this);
		
		this.dateMenu = new Ext.Toolbar.Button({
			iconCls : 'calendar_icon',
			tooltip : 'Select a date',
			text: 'Select...',
			menu: this.dateSelectionMenu
		});
		
		this.dayMonthYearLabel = new Ext.form.Label({
			text : this.currentDate.format('l, F j, Y')
		});
		

		/**
		 *  This button will be used to goto the next Date, Week Or Month based on the viewMode.
		 *  If viewMode is day then this will take to  the next date .
		 *  If the viewmode is week then this will take to the next week.
		 *  If the viewMode is month then this will take to the next month
		 */
		
		this.nextDateBtn = new Ext.Toolbar.Button({
			iconCls : 'date_next_icon',
			tooltip : 'Go to next date',
			scope:this,
			handler : function() {
				if( this.viewMode === "day"){
					this.currentDate = new Date(this.currentDate.getTime() + (24*60*60*1000));
					this.currentDate = this.currentDate.clearTime();
//					this.viewMode = 'day';
					this.changeDayMonthYearLabel();
					loadNewAppointmentCalendar( this , this.currentDate , this.currentDate , this.doctorId );
//					this.generateView();
//					this.loadRosters();
				}
				else if( this.viewMode === "week"){
					this.currentDate = new Date(this.currentDate.getTime() + (24*60*60*1000*7));
					this.currentDate = this.currentDate.clearTime();
					this.changeDayMonthYearLabel();
					
					loadNewAppointmentCalendar( this , this.dateForFirstDayOfTheWeek , this.dateForLastDayOfTheWeek , this.doctorId );
//					this.generateView();
//					this.loadRosters();
				}
				else if( this.viewMode === "month"){
//					lastDateOfPrevMonth = this.currentDate.getLastDateOfMonth();
					this.lastDateOfThisMonth = this.currentDate.getLastDateOfMonth();
					this.currentDate = new Date( this.lastDateOfThisMonth.getTime() + (24*60*60*1000));
					firstDateOfMonth = this.currentDate.getFirstDateOfMonth();
					lastDateOfMonth = this.currentDate.getLastDateOfMonth();
					lastDateOfPrevMonth = this.lastDateOfThisMonth;
					this.changeDayMonthYearLabel();
					loadNewAppointmentCalendar( this , firstDateOfMonth , lastDateOfMonth , this.doctorId );
//					this.generateView();
//					this.loadRosters();
				}
			}
		});

		/**
		 *  This button will be used to goto the previous Date, Week Or Month based on the viewMode.
		 *  If viewMode is day then this will take to  the previous date .
		 *  If the viewmode is week then this will take to the previous week.
		 *  If the viewMode is month then this will take to the previous month
		 */
		
		
		this.previousDateBtn = new Ext.Toolbar.Button({
			iconCls : 'date_previous_icon',
			tooltip : 'Go to previous date',
			scope:this,
			handler : function() {
				if( this.viewMode === "day"){
					this.currentDate = new Date(this.currentDate.getTime() - (24*60*60*1000));
					this.currentDate = this.currentDate.clearTime();
					this.changeDayMonthYearLabel();
					loadNewAppointmentCalendar( this , this.currentDate , this.currentDate , this.doctorId );
//					this.generateView();
//					this.loadRosters();
				}
				else if( this.viewMode === "week"){
					this.currentDate = new Date(this.currentDate.getTime() - (24*60*60*1000*7));
					this.currentDate = this.currentDate.clearTime();
					this.changeDayMonthYearLabel();
					loadNewAppointmentCalendar( this , this.dateForFirstDayOfTheWeek , this.dateForLastDayOfTheWeek , this.doctorId );
//					this.generateView();
//					this.loadRosters();
				} 
				else if( this.viewMode === "month"){
					this.firstDateOfThisMonth = this.currentDate.getFirstDateOfMonth();
					this.currentDate = new Date( this.firstDateOfThisMonth.getTime() - (24*60*60*1000));
					firstDateOfMonth = this.currentDate.getFirstDateOfMonth();
					this.currentDate = firstDateOfMonth;
					lastDateOfMonth = this.currentDate.getLastDateOfMonth();
					lastDateOfPrevMonth = new Date(firstDateOfMonth.getTime() - (24*60*60*1000));
					this.changeDayMonthYearLabel();
					loadNewAppointmentCalendar( this , firstDateOfMonth , lastDateOfMonth , this.doctorId );
//					this.generateView();
				}
			}
		});

		var CalEntryRecord = Ext.data.Record.create([
			{ name: 'date'},
			{ name: 'startTime'},
			{ name: 'endTime'},
			{ name: 'isBooked'},
			{ name: 'slotType'}//,
//			{ name: 'patientId'},
//			{ name: 'appointmentNumber'},
//			{ name: 'bookingType'}
        ]);
		
		
		/**
		 *  This method will be used to generate the rows in the grid . This method will generate rows 
		 *  based on the viewMode(day, week , month  ).  
		 */
		this.generateRows = function() {
			var recordArr = Array();
			if (this.viewMode === 'day' || this.viewMode === 'week') {
				var tmpStatTime = Date.parseDate(this.workBeginTime, "g:iA");
				var tmpEndTime = new Date(tmpStatTime.getTime() + (this.slotDuration * 60 * 1000));
				var endTime = Date.parseDate(this.workEndTime, "g:iA");
				var i = 0;
				while(true) {
					var tmpRec = new CalEntryRecord({
						seqNbr: i,
						date: this.currentDate,
						startTime: tmpStatTime.format("h:i A"),
						endTime: tmpEndTime.format("h:i A")
					});
					
					recordArr[i] = tmpRec;
					i++;
					tmpStatTime = new Date(tmpStatTime.getTime() + (this.slotDuration * 60 * 1000));
					tmpEndTime = new Date(tmpEndTime.getTime() + (this.slotDuration * 60 * 1000));
					if (tmpStatTime.getTime() > endTime.getTime()) {
						break;
					}
				}
				
				return recordArr;
			}
			
			if (this.viewMode === 'month') {
				var tmpEndTime = Date.parseDate(this.workBeginTime, "g:iA");
				var endTime = Date.parseDate(this.workEndTime, "g:iA");
				var rowCount = 5;
				if (lastDateOfMonth.format('j') === 31 && firstDateOfMonth.format('w') > 4) rowCount = 6;
				if (lastDateOfMonth.format('j') === 30 && firstDateOfMonth.format('w') > 5) rowCount = 6;

				for (var i = 0; i < rowCount; i++) {
					var tmpRec = new CalEntryRecord({
						seqNbr: i,
						date: this.currentDate,
						startTime: this.workBeginTime,
						endTime: this.workEndTime
					});
					
					recordArr[i] = tmpRec;
				}
				
				return recordArr;
			}
		};
		
		/**
		 *  this method will be used to get columns in the grid . What are the columns the grid will have this will
		 *  be decided by this method based on the viewMode. 
		 *  If viewMode is day then it returns two column one for timing(startTime) and other for showing the roster(Scedule).
		 *  If viewMode is week then it returns eight columns first one is for timing(startTime) and others will be 
		 *  used for showing the rosters on weekly basis it means seven columns will be from Sunday to Saturday.
		 *  If viewMode is month then this returns  seven columns from Sunday to Saturday .
		 */
		
		this.getColumns = function() {
			if (this.viewMode === 'day') {
				var currDate = this.currentDate;
				currDate = currDate.format('l, F j, Y');
				var columns = [{
							dataIndex: 'startTime',
							width : 60,
							fixed: true,
							sortable: false,
							menuDisabled : true,
							resizable : false,
							renderer: function(value, metadata, record, rowIndex, colIndex, store){
								metadata.attr = 'style="background-color:#CCFFFF;"';
								return value;
							}
						},{
							header : currDate,
							dataIndex: 'agenda',
							width :540,
							sortable: false,
							menuDisabled : true,
							resizable : false,
							align: 'left',
							renderer: function(value, metadata, record, rowIndex, colIndex, store){
								if (record.data.slotType === 'AVAILABILITY' && record.data.date.format('l, F j, Y') === currDate ) {
									metadata.attr = 'style="background-color:'+tmpThis.rosterColorCode+';"';
								} /*else {
									metadata.attr = 'style="background-color:#FF9966;"';
								}*/
								if (record.data.slotType === '' && record.data.date.format('l, F j, Y') === currDate) {
									metadata.attr = 'style="background-color:'+tmpThis.appointmentColorCode+';"';
									
									var appointmentDetails = '';
									if( !Ext.isEmpty( record.data.appointmentNumber ) ){
										appointmentDetails += "Appointment number: "+record.data.appointmentNumber;
									}
									if( !Ext.isEmpty( record.data.patientId ) ){
										appointmentDetails += " ,<br/>"+record.data.entityName+"( "+record.data.patientId+" )";
									}
									if( !Ext.isEmpty( record.data.bookingType ) ){
										appointmentDetails += ", "+record.data.bookingType;
										
									}
									 value = String.format(appointmentDetails);
								}
								
								return value;
							}
						}];
						
				return columns;
			}
			
			if (this.viewMode === 'week') {
				var currDate = this.currentDate;
				var columns = Array();
				columns[0] = {
							dataIndex: 'startTime',
							width : 60,
							sortable: false,
							menuDisabled : true,
							resizable : false,
							renderer: function(value, metadata, record, rowIndex, colIndex, store){
								metadata.attr = 'style="background-color:#CCFFFF;"';
								return value;
							}
						};
				for (var i = 0; i < 7 ; i++) {
					currDate = new Date(currDate.getTime() - (currDate.getDay() * 24 * 60 * 60 * 1000) + (i * 24 * 60 * 60 * 1000));
					columns[i+1] = {
							header : currDate.format("D j/n"),
							dataIndex: 'agenda',
							width : 540/7,
							sortable: false,
							menuDisabled : true,
							resizable : false,
							align: 'center',
							renderer: function(value, metadata, record, rowIndex, colIndex, store){
								//colIndex 0 is empty so  -1 to get the correct day position in the grid
							if( !Ext.isEmpty( record.data["Row"+rowIndex] )){
								var columnInfo = record.data["Row"+rowIndex];
								for( var i = 0 ; i < columnInfo.length ; i++){
									
									var cellInfo = columnInfo[i].split("|");
									if((record.data.slotType === 'AVAILABILITY' || record.data.slotType === '' )
											&& cellInfo[0] === this.header ) {
											if( cellInfo[1] ==="true"){
												metadata.attr = 'style="background-color:'+tmpThis.appointmentColorCode+';"';
												if( cellInfo[5] ==="undefined"){
													cellInfo[5] = "";
												}
												metadata.attr+='ext:qtip="'+cellInfo[3]+'('+cellInfo[4]+') '+cellInfo[5]+'"';
//												                                      ;
												value = cellInfo[3]+"("+cellInfo[4]+")";
												this.toolTip = cellInfo[2];
												
											}
											else{
												metadata.attr = 'style="background-color:'+tmpThis.rosterColorCode+';"';
											}
										
									} 
								}
							}

								return value;
							}
						};
				}
						
				return columns;
			}
			
			if (this.viewMode === 'month') {
				var currDate = this.currentDate;
				var columns = Array();
				for (var i = 0; i < 7 ; i++) {
					currDate = new Date(currDate.getTime() - (currDate.getDay() * 24 * 60 * 60 * 1000) + (i * 24 * 60 * 60 * 1000));
					columns[i] = {
							header : currDate.format("D"),
							dataIndex: 'agenda',
							width : 540/7,
							sortable: false,
							menuDisabled : true,
							resizable : false,
							align: 'center',
							renderer: function(value, metadata, record, rowIndex, colIndex, store){
								if (record.data.slotType === 'AVAILABILITY') {
									metadata.attr = 'style="background-color:#33FF99;"';
								} else {
									//metadata.attr = 'style="background-color:#FF9966;"';
								}
								return value;
							},
							xtype:'templatecolumn',tpl:this.monthViewTpl
						};
				}
						
				return columns;
			}
			
		};

	    var tbar = [
	    	this.dayViewBtn, 
	    	{
				xtype : 'tbseparator'
			}, 
			this.weekViewBtn, 
			{
				xtype : 'tbseparator'
			}, 
			this.monthViewBtn, 
			{
				xtype : 'tbseparator'
			}, 
			this.todayBtn,
			{
				xtype : 'tbseparator'
			},
			this.dateMenu,
			{
				xtype : 'tbfill'
			}, 
			{
				xtype : 'tbseparator'
			},
			this.dayMonthYearLabel,
			{
				xtype : 'tbseparator'
			},
			this.previousDateBtn, 
			{
				xtype : 'tbseparator'
			}, 
			this.nextDateBtn
		];

		this.panel = new Ext.Panel({
			tbar: tbar,
			height: 500,
			//width: 620,
			autoScroll: true,
			items: this.calendarGrid
		});
		
		this.refreshView = function() {
			this.generateView();
			this.loadRosters();
		};
		
		this.addEntry = function(rosterRecord) {
			var recArr = Array();
			recArr[0] = rosterRecord;
			this.createRoster(recArr);
			this.refreshView();
		};
		
		this.setNewDate = function(date) {
			this.currentDate = date.clearTime();
			this.refreshView();
		}
		
		this.panel.on("render", function() {
			this.refreshView();
		}, this);
	
	},
	getPanel : function() {
		return this.panel;
	},
	
	// This methjod will be used to check that this dataObject is having the data or not.
	 
	isContains : function( dataObject , data){
		var i =0;
		while( !Ext.isEmpty( dataObject[""+i] )){
			if(dataObject[""+i] == data){
				return true;
			}
			i++;
		}
		return false;
	},
	
/**
 *  This method will be responsible for the changing the text of the label that is beside 
 *  the next and previous button. 
 *  if viewMode is month then label will be like Jul 2010.
 *  If viewMode is week then label will be like 4 Jul - 10 Jul 2010
 *  If viewmode is Day then label will be like Sunday, July 4, 2010 
 */	
	
	changeDayMonthYearLabel : function(){
		if( this.viewMode === "day"){
			
			this.dayMonthYearLabel.setText( this.currentDate.format('l, F j, Y') );
		}
		else if( this.viewMode === "week"){
			var dayOfTheWeekForCurrentDate = this.currentDate.format('w');
			this.dateForFirstDayOfTheWeek = new Date(this.currentDate.getTime() - ( (24*60*60*1000*dayOfTheWeekForCurrentDate)));
			this.dateForLastDayOfTheWeek = new Date(this.currentDate.getTime() + ( (24*60*60*1000)*(6-dayOfTheWeekForCurrentDate)));
			var dayMonthYearLabel = this.dateForFirstDayOfTheWeek.format('j M') +"-"+ this.dateForLastDayOfTheWeek.format('j M Y');
			this.dayMonthYearLabel.setText( dayMonthYearLabel );
		}
		else if( this.viewMode === "month"){
			var dayMonthYearLabel = this.currentDate.format('F Y');
			
			this.dayMonthYearLabel.setText( dayMonthYearLabel );
		}
	},
	
	// This method is responsible for the set the doctor id.
	
	setDoctorId : function( doctorId ){
		this.doctorId = doctorId;
	}
});