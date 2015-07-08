Ext.namespace("IPD.addOrder");

IPD.addOrder.AdmissionOrderPanel = function(/* Object */config) {
	
	Ext.apply(this,config);
	
	var attributeList = config.data;
	var list = Array();
	var newDate = new Date();
	for (var i=0;i<attributeList.length;i++){
		var attribute = attributeList[i].data;
		if(attribute.type == 'string'){
		
			var textItem = new Ext.form.TextField({
				name:attribute.attributeName,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true
			});
			list.push({layout:'form',items:textItem});
		}else if(attribute.type == 'datetime'){
			var dateTimeItem = new Ext.form.WTCDateTimeField({
				name:attribute.attributeName,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true,
				minValue : attribute.attributeName == 'expectedDischargeDate' ? newDate : null,
				value:attribute.attributeName == 'expectedAdmissionDate' ? newDate:
						(attribute.attributeName == 'expectedDischargeDate' ? newDate.add(Date.SECOND, 58) : null)
			});
			list.push({layout:'form',items:dateTimeItem});
		}else if(attribute.type == 'date'){
			
			var dateItem = new Ext.form.WTCDateField({
				name:attribute.attributeName,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true
			});
			list.push({layout:'form',items:dateItem});
		}else if(attribute.type == 'longtext'){
			
			var textAreaItem = new Ext.form.TextArea({
				name:attribute.attributeName,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true
			});
			list.push({layout:'form',items:textAreaItem});
		}else if(attribute.type == 'MVL'){
				
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
				hiddenName:attribute.attributeName,
				store:stores,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true,
				displayField:'description',
				valueField:'code',
				triggerAction:'all',
				forceSelection:true,
				mode:'local'
				
			});

			list.push({layout:'form',items:comboItem});
		}else if(attribute.type == 'number'){
			
			var numberItem = new Ext.form.NumberField({
				name:attribute.attributeName,
				anchor:'95%',
				fieldLabel:attribute.attributeDesc,
				allowBlank:attribute.isMandatory == 'Y'?false:true
			});
			list.push({layout:'form',items:numberItem});
		}
	}
	
	this.admissionPanel = new Ext.form.FormPanel({
		height:'100%',
		width:'90%',
		hidden:true,
//		title:'Admission specific order details',
		items:[
			{
				xtype:'fieldset',
				height:'100%',
				title:'Admission specific order details',
				layout:'column',
				defaults:{
					columnWidth:.44,
					labelWidth:125
				},
				items:list
			}
		]
		
	});
	
	this.getPanel = function() {
		return this.admissionPanel;
	};
	
	this.getData = function() {
		if(this.admissionPanel.getForm().isValid()){
			return this.admissionPanel.getForm().getValues();
		}else{
			warning("Enter all the required fields..!");
			return null;
		}
		
	};
};
