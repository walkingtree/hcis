Ext.namespace("OPD.vital.configure");

/**
 * This will create panel for the given widgets.
 */
OPD.vital.configure.DynamicPanel = Ext.extend(Ext.Panel,{
	initComponent : function(){
		this.widgetList = this.initialConfig.widgetList;
		this.itemList = new Array();
		this.timeField = null;
		this.groupCode = null;
		
		for( var i = 0; i < this.widgetList.length ; i++){
			this.widgetInfo = this.widgetList[i];
			this.widgetName = this.widgetInfo.code;
			this.widgetLabel = this.widgetInfo.name || this.widgetInfo.label;
			this.widgetAnchor = this.widgetInfo.anchor || "90%";
			this.widgetValue = this.widgetInfo.value;
			this.widgetType = this.widgetInfo.fieldType;
			this.columnWidth = this.widgetInfo.columnWidth || .33 ;
			this.widgetGroup = this.widgetInfo.group || "group"+i;
			this.labelWidth = this.widgetInfo.labelWidth || 100;
			this.checked = this.widgetInfo.checked || false ;
			
			this.j = 0;
			if( this.widgetType === "number"){
				this.widgetType = "NumberField";
			}
			else if(this.widgetType === "string"){
				this.widgetType = "TextField";
			}
			else if(this.widgetType === "MVL"){
				this.widgetType = "ComboBox";
			}
			else if(this.widgetType === "time"){
				this.widgetType = "TimeField";
			}
			else if( this.widgetType === "radio"){
				this.widgetType = "Radio";
			}
			
			// This will create widget for the given type. 
			
			this.widgetItem = new Ext["form"][this.widgetType];
			this.widgetItem.name = this.widgetName;
			this.widgetItem.fieldLabel = this.widgetLabel;
			this.widgetItem.anchor = this.widgetAnchor;
			this.widgetItem.value = this.widgetValue;
			this.widgetItem.text = this.widgetName;
			this.widgetItem.checkListDetailId = this.widgetInfo.checkListDetailId;
			this.widgetItem.checkListInstanceId = this.widgetInfo.checkListInstanceId;
			this.widgetItem.checkListId = this.widgetInfo.checkListId;
	
			// This will create WidgetGroup for the widget if it is required.
			// 

			if( !Ext.isEmpty( Ext["form"][this.widgetType+"Group"])){
				if( !Ext.isEmpty( this.widgetInfo.checkListInstanceId )){
					this.widgetItem.name = this.widgetItem.name+this.widgetInfo.checkListInstanceId;
				}
				if( !Ext.isEmpty( this.groupCode )){
					if( this.groupCode != this.widgetInfo.groupCode ){
						this.groupCode = this.widgetInfo.groupCode; 
						this.j++;
					}
					
				}
				this.widgetItem.boxLabel = this.widgetLabel;
				this.widgetItem.fieldLabel = null;
				this.widgetItem.checked = this.checked;
				this.widgetItemClone = this.widgetItem;
				this.labelWidth = .01;
				if( !Ext.isEmpty( this.widgetGroup ) && Ext.isEmpty( this[this.widgetGroup+this.j])){
					if( Ext.isEmpty( this.groupCode )){
						this.groupCode = this.widgetInfo.groupCode;
					}
					this[this.widgetGroup+this.j] = new Ext["form"][this.widgetType+"Group"]({items : []});
					this.widgetItem.checked = this.checked;
					this[this.widgetGroup+this.j].items[0] = this.widgetItem;
					this[this.widgetGroup+this.j].name = this.widgetGroup;
					this[this.widgetGroup+this.j].columns = [130, 130,130,130];
				}
				else{
					this[this.widgetGroup+this.j].items[this[this.widgetGroup+this.j].items.length - 1 ] = this.widgetItem;
					
					
//					this.j++;
//					this.widgetGroup = Ext["form"][this.widgetType+"Group"];
//					this.widgetGroup.name = this.widgetGroup;
//					this.widgetGroup.columns = [130, 130,130,130];

				}
//				this.widgetItemClone = this.widgetItem;
//				this.widgetGroup.add( this.widgetItemClone )
//				this.widgetItem = this.widgetGroup;
			}
			
			if( !Ext.isEmpty( this.widgetItem )){
				this.widgetConfig = {
					columnWidth : this.columnWidth,
					labelWidth : this.labelWidth,
					layout : 'form',
					items : this.widgetItem
				}
				this.itemList.push(this.widgetConfig);
			}
		}
		
		Ext.applyIf(this, {
			layout : 'column',
			frame : true,
            items: this.itemList,
            buttons : this.buttons
			            
        });
		
		OPD.vital.configure.DynamicPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getValues : function(){
		var obejctValues = {};
		for( var i = 0 ; i < this.itemList.length ; i++){
			obejctValues[this.itemList[i].items.getName()] = this.itemList[i].items.getValue();
		}
		return obejctValues;
	},
	
	getCodeAndValueList : function(){
		var timeFieldValue = null ;
		if( !Ext.isEmpty( this.timeField )){
			timeFieldValue = this.timeField.items.getValue();
		}
		var codeAndValueList = new Array();
		var currentDate = this.getDateWithSelectedTime( timeFieldValue );
		
		for( var i = 0 ; i < this.itemList.length ; i++){
			var codeAndValue = {};
			codeAndValue["code"] = this.itemList[i].items.getName();
			codeAndValue["value"] = this.itemList[i].items.getValue()+""; 
			codeAndValue["forTime"] = currentDate;
			codeAndValueList.push(codeAndValue);
		}
		
		return codeAndValueList;
	},
	
	setTimeField : function( timeField ){
		if( !Ext.isEmpty( timeField )){
			this.timeField = timeField;
			this.add( timeField )
		}
	},
	
	getDateWithSelectedTime : function( selectedTime ){
		if( !Ext.isEmpty( selectedTime )){
			var currentDate = new Date();
			var temp = selectedTime.split(":");
			var temp1= temp[1].split(" ");
			if(temp1[1] === "PM" && temp[0] != "12"){
				temp[0]= parseInt(temp[0]) + 12
			}
			currentDate.clearTime();
			currentDate.setHours( temp[0]);
			currentDate.setMinutes( temp1[0]);
			
			return currentDate;
		}
		
	},
	
	reset : function(){
		for( var i = 0 ; i < this.itemList.length ; i++){
			this.itemList[i].items.setValue("");
		}
	}
});

