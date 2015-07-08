
var appUser = CookieUtil.get('appuser');
var authorisedUser = {};
if(!Ext.isEmpty(appUser)){
	var userInfo = appUser.split(',');
		authorisedUser.userId = userInfo[0];
		authorisedUser.userName = userInfo[1];
		authorisedUser.role = userInfo[2];
		authorisedUser.roleId = userInfo[3];
		authorisedUser.password = userInfo[4];
}

var currencyIndicatorValue = "";
ConfigurationManager.getSystemConfiguration( 'CURRENCY_INDICATOR', function( attributeValue ){
		currencyIndicatorValue = attributeValue;
});

var integratedIPDValue = "";
ConfigurationManager.getSystemConfiguration( 'IS_INTEGRATED_IPD', function( attributeValue ){
		integratedIPDValue = attributeValue;
});


var integratedLIMSValue = "";
ConfigurationManager.getSystemConfiguration( 'IS_INTEGRATED_LIMS', function( attributeValue ){
	integratedLIMSValue = attributeValue;
});

var integratedOTValue = "";
ConfigurationManager.getSystemConfiguration( 'IS_INTEGRATED_OT', function( attributeValue ){
	integratedOTValue = attributeValue;
});

var emptyTextDate = "";
ConfigurationManager.getSystemConfiguration( 'DATE_EMPTY_TEXT', function( attributeValue ){
	emptyTextDate = attributeValue;
});


function getIntegratedIPD(){
	return integratedIPDValue;
}

function getCurrencyIndicator(){
	return currencyIndicatorValue;
}

function getIntegratedLIMSValue(){
	return integratedLIMSValue;
}

function getIntegratedOTValue(){
	return integratedOTValue;
}
//to get pharmacy URL

/*var pharmcayUrl = "";
ConfigurationManager.getSystemConfiguration( 'PHARMACY_URL', function( attributeValue ){
		pharmcayUrl = attributeValue;
});

function getPharmcayUrl(){
	return pharmcayUrl;
}*/


function getAuthorizedUserInfo(){
	return authorisedUser;
}

function getWelcomeText(){
	//return 'Welcome '+ getAuthorizedUserInfo().userName + ' ['+ getAuthorizedUserInfo().role +' ]'
//	return  getAuthorizedUserInfo().userName + ' logged in ! ';
	var userName = Ext.util.Format.capitalize( getAuthorizedUserInfo().userName );
	return 'Welcome '+ userName;
}

/**
 * The method below is used in convert property of record of the grid for
 * getting the code of the CodeAndDescription type object
 * @param {} codeAndDescObj
 * @return {}
 */
function getCode(codeAndDescObj){
	if(!Ext.isEmpty(codeAndDescObj)){
		return codeAndDescObj.code;
	}
	return null;
}

/**
 * The method below is used in convert property of record of the grid for
 * getting the description of the CodeAndDescription type object
 * @param {} codeAndDescObj
 * @return {}
 */
function getDescription(codeAndDescObj){
	if(!Ext.isEmpty(codeAndDescObj)){
		return codeAndDescObj.description;
	}
	return null;
}

/**
 * this method converts to amount.
 * @param {} value
 * @return {}
 */
function convertToAmount( value ){
	if(value != null && !Ext.isEmpty(value)){
		return Ext.util.Format.money( value );
	}
};

Ext.util.Format.money = function(v){
            v = (Math.round((v-0)*100))/100;
            v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
            v = String(v);
            var ps = v.split('.');
            var whole = ps[0];
            var sub = ps[1] ? '.'+ ps[1] : '.00';
            var r = /(\d+)(\d{3})/ ;
            v = whole + sub;
            if(v.charAt(0) == '-'){
                return '- ' + v.substr(1);
            }
            
            return v;
}

function error( message , title ){
 		Ext.Msg.show({
 			title:title,
			msg: message,
			icon : Ext.MessageBox.ERROR,
			buttons: Ext.Msg.OK,
			fn: function(){}
		});
}

function info( message , title ){
 		Ext.Msg.show({
 			title:title,
			msg: message,
			icon : Ext.MessageBox.INFO,
			buttons: Ext.Msg.OK,
			fn: function(){}
		});
}

/**
 * this method has been written for getting the date in the wtc date format which has 
 * first value as day, 2nd value as month and 3rd value as year. 
 * @param {} date
 * @return {}
 */
function getStringAsWtcDateFormat (date) {
	if(!Ext.isEmpty(date)&& date!='undefined'){
		var array =[];
		array = date.split("/");
		var  days = parseInt(array[0],10);
		var months =parseInt(array[1],10);
		var years = parseInt(array[2],10);
		var  convertedDate = new Date(years, months-1,days);
		return convertedDate;
	}else {
		return null;
	}
}

function getChargeOverrideLevelLbl( chergeOverrideLevel ){
	if(!Ext.isEmpty(chergeOverrideLevel)){
		if( chergeOverrideLevel == configs.overrideLevel_Package ){
			return configs.overrideLevel_Package_Lbl;
		}else if( chergeOverrideLevel == configs.overrideLevel_Service ){
			return configs.overrideLevel_Service_Lbl;
		}
		
	}
	return null;
}

function getSameAsCurrentChk( addressType ){
	var sameAsCurrentChk = new Ext.form.Checkbox({
			boxLabel: addressType+' '+msg.sameascurrentaddress,
			labelSeparator:'',
			name:'currentparamanentchk'
		});
	return sameAsCurrentChk;
}

function convertStringDateTimeToDateObj( dateTimeInStringFormat ){
	if( dateTimeInStringFormat != null && !Ext.isEmpty( dateTimeInStringFormat )){
		return Date.parseDate( dateTimeInStringFormat ,'d/m/Y g:i A');
	}else {
		return null;
	}
}

function convertStringDateToDateObj( dateInStringFormat ){
	if( dateInStringFormat != null && !Ext.isEmpty( dateInStringFormat )){
		return Date.parseDate( dateInStringFormat ,'d/m/Y');
	}else {
		return null;
	}
}

function numberToString(value){
	if(value != null && !Ext.isEmpty(value)){
		var stringValue =  new String(value);
		return stringValue;
	}
}
/*
 * This method calculates age of paperson in terms of years , moths & days.
 * As an argument it takes date of birth in date format
 */
function calculateDOB(dob){
   var ageArray;
 	var dat = new Date();
 	var curday = dat.getDate();
 	var curmon = dat.getMonth() + 1;
 	var curyear = dat.getFullYear();
 	
 	
 	var dobDays = dob.getDate();
 	var dobmonths = dob.getMonth() + 1;
 	var dobyear = dob.getFullYear();
 	
 	if (curday == "" || curmon == "" || curyear == "" || dobDays == "" || dobmonths == "" || dobyear == "") {
 		Ext.Msg.alert('please fill all the values and click go -');
 	}
 	else {
 		var curd = new Date(curyear, curmon - 1, curday);
 		var cald = new Date(dobyear, dobmonths - 1, dobDays);
 		
 		var diff = Date.UTC(curyear, curmon, curday, 0, 0, 0) - Date.UTC(dobyear, dobmonths, dobDays, 0, 0, 0);
 		
 		ageArray = datediff(curd, cald);
		
		var testy =ageArray[0];
		var testm = ageArray[1];
		var testd = ageArray[2];
 	}
	return ageArray;
 }
 
 /*
 * This method is being called in calculateDOB method. It calculates the difference 
 * between two passed on dates and returns years, months and days
 */
 
function datediff(date1, date2) {
    var y1 = date1.getFullYear(), m1 = date1.getMonth(), d1 = date1.getDate(),
	 y2 = date2.getFullYear(), m2 = date2.getMonth(), d2 = date2.getDate();

    if (d1 < d2) {
        m1--;
        d1 += DaysInMonth(y2, m2);
    }
    if (m1 < m2) {
        y1--;
        m1 += 12;
    }
    return [y1 - y2, m1 - m2, d1 - d2];
}

/**
  * This method is being called in datediff(date1, date2)
  * @param {} Y
  * @param {} M
  * @return {}
  */
function DaysInMonth(Y, M) {
    with (new Date(Y, M, 1, 12)) {
        setDate(0);
        return getDate();
    }
}

function compareTwoDates(date1, date2){
	if(date1!=null && date2!=null && date1 !="" && date2 !=""){
		var dateFromComp = date1.getTime(); // milliseconds
		var dateToComp = date2.getTime();
	
		if (dateFromComp > dateToComp){
			return false;
		}else{
			return true;
		}	
	}
}

// added custom method for removing particular elements form an array.
Array.prototype.removeItem = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

var manageDoctorRecordType = Ext.data.Record.create([
    {name: "doctorId", type: "string"},
    {name: "doctorName", type: "string"},
	{name: "gender", type: "string"},
	{name: "knownLanguages", type: "string"},
	{name: "department",type:"string"},
	{name:"especiality",type:"string"},
	{name:"roomNo", type:"string"},
	{name:"consultationCharge",type:"string"},
	{name:"parmanent", type:"string"}
]);
	
	
var doctorManagementDataStore = new Ext.data.Store({
   proxy: new Ext.data.DWRProxy(DoctorManagementController.getDoctors, true),
   reader: new Ext.data.ListRangeReader( 
		{id:'id', totalProperty:'totalSize'}, manageDoctorRecordType),
   remoteSort: true
});

var manageEntityRecordType = Ext.data.Record.create([
                                                     
                                                     {name: "entityName", type: "string"},
                                                 	{name: "gender", type: "string"},
                                                 	{name:"entityType", type:"string"}
                                                 ]);
                                                 	

var entityManagementDataStore = new Ext.data.Store({
	   proxy: new Ext.data.DWRProxy(DoctorManagementController.getDoctors, true),
	   reader: new Ext.data.ListRangeReader( 
			{id:'id', totalProperty:'totalSize'}, manageEntityRecordType),
	   remoteSort: true
	});
/**
 * This function takes from time component and to time component and returns 
 * the next index of the selected time index in from time component
 * @param {} fromTimeComp
 * @param {} toTimeComp
 * @return {}
 */
var getToTime = function( fromTimeComp, toTimeComp ){
	var fromTimeStore = fromTimeComp.store;
	var toTimeStore = toTimeComp.store;
	var selectedTime = fromTimeComp.getValue();
	
	for(var i = 0 ; i < toTimeStore.data.items.length ; i++){
		if( toTimeStore.data.items[i].data.field1 == selectedTime ){
			return toTimeStore.data.items[i+1].data.field1;
		}
	}
};

/**
 * this method is loading roster entries and appointment entries[related to roster entries] 
 * which are already created into calendar.
 * Based on the date[appointment date] and doctorId the entries will be get from the backend.
 * @param {} date
 * @param {} doctorId
 */
var loadNewAppointmentCalendar = function( calendar, fromDate, toDate ,doctorId ){
	AppointmentManagementController.getDoctorRoster(fromDate,toDate,doctorId,function(appointmentRosterList){
		if( fromDate.format('j M Y') === toDate.format('j M Y')){
			calendar.setNewDate (fromDate);
		}
		if(appointmentRosterList!=null && appointmentRosterList.length > 0) {
			calendar.createRoster(appointmentRosterList);
		}else{
//			calendar.setNewDate (  );
			calendar.createRoster(new Array());
		}
		calendar.setDoctorId( doctorId );
		calendar.refreshView();
	});
};

dwr.engine.setErrorHandler(function( message ){
	
	var userInfo = message.substr( 0,message.indexOf(":") );
	var stackTrace = message.substr(message.indexOf(":") + 1);
	
	if( message.indexOf(":") < 0 ){
		userInfo = message;
	}
	
	var title = "Error";
	
	
	var dvRecord = Ext.data.Record.create([
	    {name: 'userInfo'},        
	    {name: 'stackTrace'}
	]);
	
	var  dvStore = new Ext.data.Store({
		data : [['userInfo','stackTrace']],
		reader : new Ext.data.ArrayReader({id: 0}, dvRecord)
	});

	var tplUserInfo = new Ext.XTemplate(
	    '<tpl for=".">',
	        '<div>',
	        '	<div style="float:left; width: 10%">' +
	        '  		<img src="images/icon-error.gif" />' +
	        '	</div>' +
	        '   <div style="float:left">' +
	        '  		<p class="label-font" align="justify">' +
	        			userInfo +
			'		</p>' +
	        '	</div>'+
	        '</div>',
	    '</tpl>'

	);
	
	var tplStackTrace = new Ext.XTemplate(
	     '<tpl for=".">',
	        '<div>',
	        '	<div style="float:left">' +
	        '  		<p class="label-font" align="justify">' +
	        			stackTrace +
			'		</p>' +
	        '	</div>'+
	        '</div>',
	    '</tpl>'
	);
	
	var userInfoPanel = new Ext.form.FieldSet({
		border:false,
		autoHeight : true,
		items : new Ext.DataView({
		        store: dvStore,
		        tpl: tplUserInfo,
		        autoHeight:true,
		        overClass:'x-view-over',
		        itemSelector:'div.thumb-wrap',
		        emptyText: 'No images to display'
		})
	});
	
	var detailsPanel = new Ext.form.FieldSet({
		title:"Details",
		width:'90%',
		collapsible:true,
		collapsed:true,
		autoHeight : true,
		style:'padding-top:0px;margin-left:5px',
		items : new Ext.DataView({
		        store: dvStore,
		        tpl: tplStackTrace,
		        autoHeight:true,
		        overClass:'x-view-over',
		        itemSelector:'div.thumb-wrap',
		        emptyText: 'No images to display'
		})
	});
	
	var btnPnl = new Ext.Panel({
		buttonAlign:'center',
		border:false,
		frame:false,
		buttons:[new Ext.Button({
			text:"OK",
			iconCls:'accept-icon',
			handler:function(){
				win.close();
			}
		})]
	});
	var win = new Ext.Window({
		title:title,
		height:"25%",
		width:"40%",
		modal:true,
		buttonAlign:'center',
		items:[userInfoPanel,detailsPanel,btnPnl]
	});
	
	if(  !Ext.isEmpty( frontPage )  && !Ext.isEmpty( frontPage.getEl() ) ){
		frontPage.getEl().unmask();//unmasking the front page
	}
	
	win.show();
	
	return;
});

function warning( message ){
 		Ext.Msg.show({
			msg: message,
			icon : Ext.MessageBox.WARNING,
			buttons: Ext.Msg.OK,
			fn: function(){}
		});
};
function info( message ){
 		Ext.Msg.show({
			msg: message,
			icon : Ext.MessageBox.INFO,
			buttons: Ext.Msg.OK,
			fn: function(){}
		});
};
function alertWarning( message ){
 		Ext.Msg.show({
			msg: message,
			icon : Ext.MessageBox.WARNING,
			buttons: Ext.Msg.OK
		});
};
function alertError( message ){
 		Ext.Msg.show({
			msg: message,
			icon : Ext.MessageBox.ERROR,
			buttons: Ext.Msg.OK
		});
};

	
function setCbxDefaultValue( combo ){
	if( !Ext.isEmpty( combo.getStore() )){
		if( combo.getStore().data.length === 0){
			combo.getStore().on('load',function( thisCombo ){
				var index = combo.getStore().find( "isDefault" , "Y");
				if( index >= 0){
					var defaultValue = combo.getStore().getAt( index ).data.code;
					combo.setValue( defaultValue );
				}
				
			},this);
		}
		else{
			var index = combo.getStore().find( "isDefault" , "Y");
			if( index >= 0){
				var defaultValue = combo.getStore().getAt( index ).data.code;
				combo.setValue( defaultValue );
			}
		}
	}	
}



// enables simpleSelect behavior in CheckboxSelectionModel and fixes a bug
// wherein the header checkbox remains selected when rows are deselected
Ext.apply(Ext.grid.CheckboxSelectionModel.prototype, {

    onMouseDown: function(e, t){
        // if using left mouse button and simpleSelect or checkbox was clicked
        if(e.button === 0 && (this.simpleSelect || t.className == 'x-grid3-row-checker')){
            e.stopEvent();
            var row = e.getTarget('.x-grid3-row');
            if(row){
                var index = row.rowIndex;
                if(this.isSelected(index)){
                    this.deselectRow(index);

                    // deselect checkbox header if selected
                    var hd = Ext.DomQuery.selectNode('.x-grid3-hd-checker', this.grid.view.innerHd);
                    if(hd) Ext.fly(hd).removeClass('x-grid3-hd-checker-on');
                }else{
                    this.selectRow(index, true);
                }
            }
        }
    }

});


Ext.override(Ext.Panel, {

    beforeDestroy : function(){
//    if(!this.isDestroyed){
    	Ext.ux.event.Broadcast.removeSubscriptionsForObject(this);
//        if(this.fireEvent('beforedestroy', this) !== false){
//            this.destroying = true;
//            this.beforeDestroy();
//            if(this.ownerCt && this.ownerCt.remove){
//                this.ownerCt.remove(this, false);
//            }
//            if(this.rendered){
//                this.el.remove();
//                if(this.actionMode == 'container' || this.removeMode == 'container'){
//                    this.container.remove();
//                }
//            }
//            // Stop any buffered tasks
//            if(this.focusTask && this.focusTask.cancel){
//                this.focusTask.cancel();
//            }
//            this.onDestroy();
//            Ext.ComponentMgr.unregister(this);
//            this.fireEvent('destroy', this);
//            this.purgeListeners();
//            this.destroying = false;
//            this.isDestroyed = true;
//        }
//    }
}

});

const Utils = {
		applyConfigProperty :function (targetObject,configObject){
			
			if( !Ext.isEmpty(targetObject) && !Ext.isEmpty(configObject)){
				for(var prop in configObject) {
				    if(configObject.hasOwnProperty(prop)){
				    	
				    	targetObject[prop] =configObject[prop];
				    }
			      
				}
			}
		}
	};

