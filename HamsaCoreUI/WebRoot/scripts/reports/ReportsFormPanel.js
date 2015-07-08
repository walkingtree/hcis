Ext.namespace("reports.ReportsFormPanel");

reports.ReportsFormPanel = Ext.extend(Ext.form.FormPanel, {
	labelAlign : 'left',
	layout : 'form',
	border : false,
	height : '100%',
	frame : false,
	monitorValid : true,
	
    initComponent : function( config ) {
    
    		var attributeList = this.initialConfig.data;
    		var list = Array();
    		this.newDate = new Date();
    		
   			for (var i=0;i<attributeList.length;i++){
				var attribute = attributeList[i];
				if(attribute.xtype == 'string'){
				
					var textItem = new Ext.form.TextField({
						name:attribute.widgetName,
						anchor:'90%',
//						maxLength : attribute.widgetLength,
						fieldLabel:attribute.widgetLabel,
						allowBlank:attribute.requiredFlag == 'Y'?false:true,
						required : attribute.requiredFlag == 'Y'?true:false 
					});
					list.push({layout:'form',items:textItem});
				}else if(attribute.xtype == 'datetime'){
					var dateTimeItem = new Ext.form.WTCDateTimeField({
						name:attribute.widgetName,
						anchor:'90%',
						fieldLabel:attribute.widgetLabel,
						required : attribute.requiredFlag == 'Y'?true:false,
						allowBlank:attribute.requiredFlag == 'Y'?false:true,
						minValue : attribute.widgetName == 'expectedDischargeDate' ? this.newDate : null,
						value:attribute.widgetName == 'expectedAdmissionDate' ? this.newDate:
								(attribute.widgetName == 'expectedDischargeDate' ? this.newDate.add(Date.SECOND, 58) : null)
					});
					list.push({layout:'form',items:dateTimeItem});
				}else if(attribute.xtype == 'date'){
					
					var dateItem = new Ext.form.WTCDateField({
						name:attribute.widgetName,
						anchor:'90%',
						fieldLabel:attribute.widgetLabel,
						required : attribute.requiredFlag == 'Y'?true:false,
						allowBlank:attribute.requiredFlag == 'Y'?false:true
					});
					list.push({layout:'form',items:dateItem});
				}else if(attribute.xtype == 'longtext'){
					
					var textAreaItem = new Ext.form.TextArea({
						name:attribute.widgetName,
						anchor:'90%',
						fieldLabel:attribute.widgetLabel,
						required : attribute.requiredFlag == 'Y'?true:false,
						allowBlank:attribute.requiredFlag == 'Y'?false:true
					});
					list.push({layout:'form',items:textAreaItem});
				}else if(attribute.xtype == 'MVL'){
						
					var record = Ext.data.Record.create([
							    {name: 'code'},         
							    {name: 'description'}   
							]);
					var stores = new Ext.data.Store({
						reader:new Ext.data.ArrayReader({
								    id: 0                   
								}, record)
					});
					if(!Ext.isEmpty(attribute.MVLvalueList)){
						for(var j =0;j<attribute.MVLvalueList.length;j++){
							var recordData = stores.recordType;
							var codeDesc = attribute.MVLvalueList[j];
							var data = new recordData({
								code:codeDesc.code,
								description:codeDesc.description
							});
							stores.add(data);
						}
					}
					
					var comboItem = new Ext.form.ComboBox({
						hiddenName:attribute.widgetName,
						store:stores,
						anchor:'90%',
						fieldLabel:attribute.widgetLabel,
						allowBlank:attribute.requiredFlag == 'Y'?false:true,
						required : attribute.requiredFlag == 'Y'?true:false,
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
						forceSelection:true,
						mode:'local'
						
					});
		
					list.push({layout:'form',items:comboItem});
				}else if(attribute.xtype == 'number'){
					
					var numberItem = new Ext.form.NumberField({
						name:attribute.widgetName,
						anchor:'90%',
						fieldLabel:attribute.widgetLabel,
						required : attribute.requiredFlag == 'Y'?true:false,
						allowBlank:attribute.requiredFlag == 'Y'?false:true
					});
					list.push({layout:'form',items:numberItem});
				}
			}
		    		
    		
    
			Ext.applyIf(this, {
            items: [
    			{
					height:'100%',
					layout:'column',
					defaults:{
						columnWidth:.44,
						labelWidth: 100
					},
					items:list
				}
			]            
        });
        reports.ReportsFormPanel.superclass.initComponent.apply(this, arguments);
		
    }
});


