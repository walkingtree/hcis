Ext.namespace("LIMS.testTemplate.configure");

LIMS.testTemplate.configure.AvailableListTreePanel = Ext.extend(Ext.form.FieldSet,{
	initComponent : function(){
		
		var dd;
		var mainThis = this;
		var patientList = new Array();
		var doctorList = new Array();
		var testAttributeList = new Array();
		this.mandatoryWidgetCodeList = new Array();

		var availableList = this.initialConfig.data;
		
		if( !Ext.isEmpty( availableList.patientWidgetList )){
			for( var i=0 ;i<availableList.patientWidgetList.length ;i++){
				var patientInfo ={
					text: availableList.patientWidgetList[i].widgetLabel,
					leaf : true,
					widgetCode : availableList.patientWidgetList[i].widgetCode,
					widgetType : availableList.patientWidgetList[i].widgetType,
					sectionCode : availableList.patientWidgetList[i].sectionCode,
					widgetLabel : availableList.patientWidgetList[i].widgetLabel,
					data :  availableList.patientWidgetList[i].widgetCode+ ","+availableList.patientWidgetList[i].widgetType+","+availableList.patientWidgetList[i].sectionCode+","+availableList.patientWidgetList[i].widgetLabel,
					minValue : availableList.patientWidgetList[i].minValue,
					maxValue : availableList.patientWidgetList[i].maxValue,
					MVLValueList : availableList.patientWidgetList[i].MVLValueList
					
				};
				
				patientList.push( patientInfo );
			}
		}
		
		if( !Ext.isEmpty( availableList.doctorWidgetList )){
			for( var i=0 ;i<availableList.doctorWidgetList.length ;i++){
				var patientInfo ={
					text:availableList.doctorWidgetList[i].widgetLabel,
					leaf : true,
					widgetCode : availableList.doctorWidgetList[i].widgetCode,
					widgetType : availableList.doctorWidgetList[i].widgetType,
					sectionCode : availableList.doctorWidgetList[i].sectionCode,
					widgetLabel : availableList.doctorWidgetList[i].widgetLabel,
					data : availableList.doctorWidgetList[i].widgetCode + ","+availableList.doctorWidgetList[i].widgetType+","+availableList.doctorWidgetList[i].sectionCode+","+availableList.doctorWidgetList[i].widgetLabel,
					minValue : availableList.doctorWidgetList[i].minValue,
					maxValue : availableList.doctorWidgetList[i].maxValue,
					MVLValueList : availableList.doctorWidgetList[i].MVLValueList
				};
				
				doctorList.push( patientInfo );
			}
		}
		
		if( !Ext.isEmpty( availableList.testAttributeWidgetList )){
			for( var i=0 ;i<availableList.testAttributeWidgetList.length ;i++){
				
				if( availableList.testAttributeWidgetList[i].isMandatory === "Y"){
					this.mandatoryWidgetCodeList.push( availableList.testAttributeWidgetList[i].widgetCode );
				}
				
				var patientInfo ={
					text:availableList.testAttributeWidgetList[i].widgetLabel,
					leaf : true,
					widgetCode : availableList.testAttributeWidgetList[i].widgetCode,
					widgetType : availableList.testAttributeWidgetList[i].widgetType,
					sectionCode : availableList.testAttributeWidgetList[i].sectionCode,
					widgetLabel : availableList.testAttributeWidgetList[i].widgetLabel,
					data : availableList.testAttributeWidgetList[i].widgetCode+","+availableList.testAttributeWidgetList[i].widgetType+","+availableList.testAttributeWidgetList[i].sectionCode+","+availableList.testAttributeWidgetList[i].widgetLabel,
					minValue : availableList.testAttributeWidgetList[i].minValue,
					maxValue : availableList.testAttributeWidgetList[i].maxValue,
					MVLValueList : availableList.testAttributeWidgetList[i].MVLValueList
				};
				
				testAttributeList.push( patientInfo );
			}
		}	
	
	
	    
		this.treeRoot = new Ext.tree.TreePanel({
			root : 
			   	{
					
			   		draggable : false,
			   		id : '-1',
			   		hasChildren : true,
			   		children : [
			   		    { 
			   		    	text : limsMsg.patientDetail,
			   		    	children : patientList
			   		    },
			   		    {
			   		    	text : limsMsg.doctorDetail,
			   		    	children : doctorList
			   		    },
			   		    {
			   		    	text : limsMsg.testAttribute,
			   		    	children : testAttributeList
			   		    }
			   		]            
		   		},
		   		loader:new Ext.tree.TreeLoader({preloadChildren:true}),
		   		enableDrop : false,
		   		animate : true,
		   		enableDD : true,
		   		width:200,
		   		ddGroup:'t2div',
		   		useArrows : true,
		   		rootVisible : false
		}); 
	    
	    Ext.applyIf(this,{
	    	border : false,
	    	items : this.treeRoot
	    });
	    
	    LIMS.testTemplate.configure.AvailableListTreePanel.superclass.initComponent.apply(this,arguments);
		
	},
	
	getPanel : function(){
		return this.treeRoot;
	}
	
});