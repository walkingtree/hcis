Ext.namespace("administration.systemConfiguration");

administration.systemConfiguration.SystemParamFormPanel = Ext.extend( Ext.form.FormPanel, {
	labelAlign : 'left',
	layout : 'form',
	border : false,
	height : '100%',
	frame : false,
	monitorValid : true,
	
    initComponent : function() {
    
    		var attributeList = this.initialConfig.data;
    		var list = Array();
    		this.newDate = new Date();
    		
   			for (var i=0;i<attributeList.length;i++){
				var attribute = attributeList[i];
				if(attribute.dataType == 'string'){
				
					var textItem = new Ext.form.TextField({
						name:attribute.attrName,
						anchor:attribute.attrWidth+'%',
						value : attribute.attrValue,
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:textItem});
				}else if(attribute.dataType == 'datetime'){
					var dateTimeItem = new Ext.form.WTCDateTimeField({
						name:attribute.attrName,
						anchor:attribute.attrWidth+'%',
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>',
						value : attribute.attrValue
					});
					list.push({layout:'form',items:dateTimeItem});
				}else if(attribute.dataType == 'date'){
					
					var dateItem = new Ext.form.WTCDateField({
						name:attribute.attrName,
						anchor:attribute.attrWidth +'%',
						value : attribute.attrValue,
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:dateItem});
				}else if(attribute.dataType == 'longtext'){
					
					var textAreaItem = new Ext.form.TextArea({
						name:attribute.attrName,
						anchor:attribute.attrWidth+'%',
						value : attribute.attrValue,
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:textAreaItem});
				}else if(attribute.dataType == 'MVL'){
						
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
						hiddenName:attribute.attrName,
						store:stores,
						anchor:attribute.attrWidth+'%',
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>',
						value : attribute.attrValue,
						displayField:'description',
						valueField:'code',
						triggerAction:'all',
						forceSelection:true,
						mode:'local'
					});
		
					list.push({layout:'form',items:comboItem});
				}else if(attribute.dataType == 'number'){
					
					var numberItem = new Ext.form.NumberField({
						name:attribute.attrName,
						anchor:attribute.attrWidth+'%',
						value : attribute.attrValue,
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:numberItem});
				}
				else if(attribute.dataType == 'check'){
					
					var checkBoxItem = new Ext.form.Checkbox({
						name:attribute.attrName,
						checked : attribute.attrValue == 'Y'? true : false,
						boxLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:numberItem});
				}
				else if(attribute.dataType == 'radio'){
					
					var radioBtnItem = new Ext.form.Radio({
						name:attribute.attrName,
						checked : attribute.attrValue == 'Y'? true : false,
						fieldLabel:'<span qtip="'+attribute.attrDesc +'">'+attribute.attrLabel+'</span>'
					});
					list.push({layout:'form',items:numberItem});
				}
				
			}
    
			Ext.applyIf(this, {
            items: [{labelWidth : 150,
            		 items :list 
            	}]
			            
        });
        administration.systemConfiguration.SystemParamFormPanel.superclass.initComponent.apply(this, arguments);
		
    },
    getPanel : function(){
    	return this;
    },
    setDataValues : function( config ){
    	this.getForm().setValues( config )
    }
});


