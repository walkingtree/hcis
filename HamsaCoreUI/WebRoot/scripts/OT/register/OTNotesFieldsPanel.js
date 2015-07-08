Ext.namespace("OT.register");

OT.register.OTNotesFieldsPanel = Ext.extend(Ext.form.FormPanel,{
	initComponent : function(){
		this.widgetList = this.initialConfig.widgetList;
		this.itemList = new Array();
		this.timeField = null;
		
		for( var i = 0; i < this.widgetList.length ; i++){
			this.widgetInfo = this.widgetList[i];
			this.widgetName = this.widgetInfo.fieldCode;
			this.widgetLabel = this.widgetInfo.fieldName;
			this.widgetAnchor = this.widgetInfo.anchor || "90%";
			this.widgetValue = this.widgetInfo.value;
			this.widgetType = this.widgetInfo.fieldType;
			if( this.widgetType === "number"){
				this.widgetType = "NumberField";
			}
			else if(this.widgetType === "string"){
				this.widgetType = "TextField";
			}
			else if(this.widgetType === "MVL"){
				this.widgetType = "ComboBox"
			}
			else if(this.widgetType === "time"){
				this.widgetType = "TimeField";
			}
			else if( this.widgetType === 0){
				this.widgetType = "TextField";
			}
			
			this.widgetItem = new Ext["form"][this.widgetType];
			this.widgetItem.name = this.widgetName;
			this.widgetItem.fieldLabel = this.widgetLabel;
			this.widgetItem.anchor = this.widgetAnchor;
			this.widgetItem.value = this.widgetValue;
			
			this.widgetConfig = {
				columnWidth : 1,
				layout : 'form',
				items : this.widgetItem
			}
			this.itemList.push(this.widgetConfig);
		}
		
		Ext.applyIf(this, {
			layout : 'column',
			frame : true,
	        items: this.itemList,
	        buttons : this.buttons
			            
	    });
		
		OT.register.OTNotesFieldsPanel.superclass.initComponent.apply(this, arguments);
	},
	
	getValuesAsOTNotesFieldsBMList : function(){
		var otNotesFieldsBMList = new Array();
		for( var i = 0 ; i < this.itemList.length ; i++){
			var config = {
				fieldCode : this.itemList[i].items.getName(),
				value : this.itemList[i].items.getValue()
			};
			
			otNotesFieldsBMList.push(config);
		}
		
		return otNotesFieldsBMList;
	},
	
	reset : function(){
		this.getForm().reset();
//		for( var i = 0 ; i < this.itemList.length ; i++){
//			this.itemList[i].items.setValue("");
//		}
	}
});