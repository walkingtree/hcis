Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrderTabPanel = function(/* Object */config) {
	
	Ext.apply(this,config);
	
	this.TemplateTabPanel = new Ext.TabPanel({
		autoScroll:true,
		height : 630,
		activeTab:0,
		width:'97%',
		deferredRender:false,
		layoutOnTabChange :true,
		border:false,
		frame:true,
		hideMode:'offsets'
	});
	if(!Ext.isEmpty( config.data )){
		var templateList = config.data;
	}
	var attributeList = config.attrList;
	var mainThis = this;
	for(var i=0;i<templateList.length;i++){
		var template = templateList[i];
		var orderTemplateOrderType = template.doctorOrderType.code;
		var attributeArray = new Array();
		var doctorOrderDetailspnl = new IPD.addOrder.DoctorOrderDetailsPanel();
		
		var config ={
			mode:'edit',
			values:{doctorOrderDetailsList:template.orderTemplateDetailsList}
		}
		var orderDescription =new Ext.form.TextArea({
			fieldLabel: 'Order description',
	        name: 'orderDesc',
	        anchor : '100%'
		});
		
		this.tabForTemplate = new Ext.Panel({
//			width:'93%'
		});
		
		for(var j=0 ; j<attributeList.length ; j++){
			var attribute = attributeList[j];
			if( orderTemplateOrderType == attribute.orderType.code){
				attributeArray.push(attribute);
			}
		}
		
		if(!Ext.isEmpty( attributeArray )){
			var specificOrderForTab = new IPD.addOrder.SpecificOrderPanel({ data:attributeArray });
			var specificPanelForTab = specificOrderForTab.getPanel();
			specificPanelForTab.show();
			this.tabForTemplate.add(specificPanelForTab);
		}
		
		this.tabForTemplate.add(doctorOrderDetailspnl.getPanel());
		
		this.tabForTemplate.add({layout:'form',labelAlign:'top',items:orderDescription});
		
		doctorOrderDetailspnl.doctorOrderDetailsServicesGridPanel.getStore().removeAll();
		doctorOrderDetailspnl.doctorOrderDetailsServicesGrid.loadData(config);
		
		this.TemplateTabPanel.add({
			orderType:template.doctorOrderType.code,
			templateId:template.templateId,
			title:template.templateDesc,
			frame:true,
			items:this.tabForTemplate});
		};

	this.getPanel= function(){
		this.TemplateTabPanel.doLayout();
		return this.TemplateTabPanel;
	};
	this.getData = function(){
		var tabPanelItems = this.TemplateTabPanel.items.items;
		var doctorOrderBmList = new Array();
		if(tabPanelItems.length>0){
			for(var i = 0; i<tabPanelItems.length; i++){
				// each tab items
				var doctorOrderBM ={};
				var tab = tabPanelItems[i].items.items;
				var TabItems = tab[0].items.items;
				var tableRows;
				if(TabItems.length ==2){
					 var gridPanel = TabItems[0].items.items;
					 tableRows = gridPanel[1].getStore().data.items;
				}else{
					var gridPanel = TabItems[1].items.items;
					tableRows = gridPanel[1].getStore().data.items;
				}
			doctorOrderBM.doctorOrderDetailsList = this.getDetailsList(tableRows);
			
			doctorOrderBM.doctorOrderType= {code:tabPanelItems[i].orderType};
			doctorOrderBM.createdBy = authorisedUser.userName;
			doctorOrderBM.modifiedBy = authorisedUser.userName;
			doctorOrderBM.doctorOrderTemplate = {code:tabPanelItems[i].templateId};
			if(TabItems.length==3){
				if( TabItems[0].getForm().isValid() ){
					doctorOrderBM.orderSpecificAttributes = TabItems[0].getForm().getValues();
				}
				else {
					warning("Enter all the required fields..!");
					return null;
				}
				doctorOrderBM.orderDesc=TabItems[2].items.items[0].getValue();
			}
			if(TabItems.length==2){
				doctorOrderBM.orderDesc=TabItems[1].items.items[0].getValue();
			}
			doctorOrderBmList.push(doctorOrderBM);
			}
			return doctorOrderBmList;
		}
		return doctorOrderBmList;
	};
	this.resetTabPanel = function(){
		var tabPanelItems = this.TemplateTabPanel.items.items;
		if(tabPanelItems.length>0){
			for(var i = 0; i<tabPanelItems.length; i++){
				// each tab items
				var TabItems = tabPanelItems[i].items.items;
				TabItems[0].getStore().removelAll();
				if(TabItems.length==3){
					TabItems[1].getForm().reset();
					TabItems[2].reset();
				}
				if(TabItems.length==2){
					TabItems[1].reset();
				}
			}	
		}
	};
	
	this.getDetailsList = function(tableRows){
		var doctorOrderDetailsList = new Array();
		for( var i = 0; i<tableRows.length; i++ ) {
			var currRow = tableRows[i].data;
				if(currRow.packageIndicator == configs.packageIndicator){
					var doctorOrderDetailsBM = {
						sequenceNbr: parseInt(currRow.sequenceNumber,10),
						subSequenceNbr: parseInt(currRow.subSequenceNumber,10),
						servicePackage: {code : currRow.serviceCode},
						responsibleDepartment: {code:currRow.responsibleDepartmentCode},
						actionDesc: currRow.actionRequiredDescription,
						actionRemarks:'',
						actionTakenBy:'',
						packageIndicator: currRow.packageIndicator
					}
				doctorOrderDetailsList.push(doctorOrderDetailsBM);
			}else{
				var doctorOrderDetailsBM = {
					sequenceNbr: parseInt(currRow.sequenceNumber,10),
					subSequenceNbr: parseInt(currRow.subSequenceNumber,10),
					serviceCode: currRow.serviceCode,
					responsibleDepartment: {code:currRow.responsibleDepartmentCode},
					actionDesc: currRow.actionRequiredDescription,
					actionRemarks:'',
					actionTakenBy:'',
					packageIndicator: currRow.packageIndicator
				}
				doctorOrderDetailsList.push(doctorOrderDetailsBM);
			}
			
		}
		return doctorOrderDetailsList;
	};
}