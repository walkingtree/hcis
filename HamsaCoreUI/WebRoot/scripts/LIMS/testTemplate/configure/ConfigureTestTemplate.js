Ext.namespace("LIMS.testTemplate.configure");

/**
 * This panel supports three modes which needs to be passed as config 
 * 1. 'EDIT' mode - Allow user to modify the value of attributes
 * 2. 'VIEW' mode - Give options to print the result
 * 3. 'APPROVE' mode -Give buttons to approve or disapprove the result
 */

LIMS.testTemplate.configure.ConfigureTestTemplate = Ext.extend(Ext.form.FormPanel,{
	height : '100%',
	monitorValid : true,
	initComponent : function(){
		
		var mainThis = this;
		this.configData = this.initialConfig;
		this.avalaibaleListFieldSet = null;
		this.dd = null;
		
		if( !this.configData.isEnterTestResult ){

			this.avalaibaleListFieldSet = new Ext.form.FieldSet({
				title : limsMsg.availList,
				labelWidth : 70,
				layout : 'form',
				border : true ,
				frame : false
			});	
			
			var testAttributeColumnWidth = .7;
			var columnWidth = .36;
		}
		else{
			var testAttributeColumnWidth = 1;
			var columnWidth = .5;
		}
		
		this.patientDetailFieldSet = new Ext.form.FieldSet({
			title : limsMsg.patientDetail,
			draggable :false,
			layout : 'form',
			frame : false,
			border : true
			
		});
		
		this.doctorDetailFieldSet = new Ext.form.FieldSet({
//			id : 'doctor-detail-panel',
			title : limsMsg.doctorDetail,
			draggable :false,
			layout : 'form',
			frame : false,
			border : true
			
		});
		
		this.testAttributeFieldSet = new Ext.form.FieldSet({
//			id : 'test-template',
			title : limsMsg.testAttribute,
			layout : 'form',
			height : 'auto',
			frame : false,
			border :true
		});
		
		this.templateDetailPanel = new Ext.Panel({
			layout : 'column',
			border : false,
			defaults : {
				columnWidth : columnWidth
			}
		});
		
		this.approverDetailPanel = new Ext.Panel({
			layout : 'column',
			border : false,
			defaults : {
				columnWidth : columnWidth
			}
		});
	
		//Button listener starts
		//Buttons panel and there listers will be decided base on the config option MODE
		
		this.buttonsPanel = new LIMS.testTemplate.configure.TestTemplateButtonsPanel({MODE:this.configData.MODE});
		this.buttonsPanel.getSaveBtn().enable();
		
		
		this.mainPanel = new Ext.Panel({
			layout : 'column',
			items : [
				{
					 columnWidth : 1,
					 items : this.templateDetailPanel
				},
				{
					columnWidth : testAttributeColumnWidth,
					layout : 'column',
					border : false,
					items : [
					   {
						   columnWidth : .5,
						   border : false,
						   items : this.patientDetailFieldSet
					   },
					   {
						   columnWidth : .5,
						   border : false,
						   items : this.doctorDetailFieldSet
					   },
					   {
						   columnWidth : 1,
						   border : false,
						   items : this.testAttributeFieldSet
					   }   
					]
				},
				{
					columnWidth : .3,
					border : false,
					items : this.avalaibaleListFieldSet
				},
				{
					columnWidth : 1,
					border : false,
					items : this.approverDetailPanel
				},
	         ]
		});
		
		
		
				
		this.buttonsPanel.getResetBtn().on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.buttonsPanel.getSaveBtn().on('click',function(){
			if( this.configData.isEnterTestResult ){
				this.saveBtnClickedForEnterTestResult();
			}
			else{
				this.saveBtnClicked();
			}
		},this);
		
		
		this.buttonsPanel.getMarkCmpldBtn().on('click',function(){
        	LabTestManager.modifyPatientTestStatus( 
					this.configData.testTemplateBM.serviceUID,
					limsMsg.SERVICE_STATUS_RESULT_ENTERED,
					getAuthorizedUserInfo().userName,null,
					{
						callback : function() {
						layout.getCenterRegionTabPanel().remove( mainThis , true);
						Ext.ux.event.Broadcast.publish('closeEnterTestResult');
						},
						scope : this
					});
			},this);
		
		this.buttonsPanel.getApproveBtn().on('click',function(){
        	LabTestManager.modifyPatientTestStatus( 
					this.configData.testTemplateBM.serviceUID,
					limsMsg.SERVICE_STATUS_APPROVED,
					getAuthorizedUserInfo().userName,null,
					{
						callback : function() {
						layout.getCenterRegionTabPanel().remove( mainThis , true);
						Ext.ux.event.Broadcast.publish('closeEnterTestResult');
						},
						scope : this
					});
			},this);
		
		this.buttonsPanel.getDisapproveBtn().on('click',function(){
//        	LabTestManager.modifyPatientTestStatus( 
//					this.configData.testTemplateBM.serviceUID,
//					limsMsg.SERVICE_STATUS_DISAPPROVED,
//					getAuthorizedUserInfo().userName,null,
//					{
//						callback : function() {
//						Ext.ux.event.Broadcast.publish('closeEnterTestResult');
//						},
//						scope : this
//					});
			
			var remarksPanel = new LIMS.testTemplate.RemarksWindow({serviceUID:this.configData.testTemplateBM.serviceUID,
																	SERVICE_STATUS:limsMsg.SERVICE_STATUS_DISAPPROVED});
			this.remarksWindow = new Ext.Window({
				title: 'Reason for Disapproval',
				items:remarksPanel,
				frame:true,
				height:'40%',
				width:'40%'
			});
			this.remarksWindow.show();
			
		
			},this);
		
		
		this.buttonsPanel.getPrintBtn().on('click',function(){
			this.prinBtnClicked();
		},this);
		
//Display the change history of test result		
		this.buttonsPanel.getViewHistoryBtn().on('click',function(){
			
			var resultChangeHistoryPnl = new LIMS.labTest.TestResultChangeHistory();
			
			resultChangeHistoryPnl.loadGridData(this.configData.testTemplateBM.serviceUID);
			
			this.remarksWindow = new Ext.Window({
				title: limsMsg.ttlResultChangeHistory,
				items:resultChangeHistoryPnl,
				frame:true,
				modal:true,
				layout : 'anchor'
			});
			
			this.remarksWindow.show();
			
				
			},this);
		
/*	This will set the height of patient details panel and doctor details panel.
 * 	first it will check which panel is having maximum height and set it to the another panel. 
 * 
 */
		this.on('afterlayout',function(){
			
			if( this.patientFieldHeight >= this.doctorFieldHeight ){
				this.doctorDetailFieldSet.setHeight( this.patientFieldHeight );
			}
			else{
				this.patientDetailFieldSet.setHeight( this.doctorFieldHeight );
			}
			

		},this);
		
		
		TestTemplateManager.getWidgetsForTest( this.configData.testCode ,function( testTemplateBM ){
			mainThis.loadWidget( testTemplateBM );
		});
		
		Ext.applyIf(this,{
			items : [
		         this.mainPanel,
		    	 this.buttonsPanel
			]        
		});
		
		LIMS.testTemplate.configure.ConfigureTestTemplate.superclass.initComponent.apply(this,arguments);
	
	},
	
	/*
	 *  If window will be the Configure test template then on click of save this method will be called.
	 *  This is responsible for the saving the test template.
	 */
	
	saveBtnClicked : function(){
		var patientWidgetArray = this.patientDetailFieldSet.items.items;
		var doctorWidgetArray = this.doctorDetailFieldSet.items.items;
		var testAttributeWidgetArray = this.testAttributeFieldSet.items.items;
		var approverDetailWidgetArray = this.approverDetailPanel.items.items;
		var templateDetailWidgetArray = this.templateDetailPanel.items.items;
		var patientWidgetList = new Array();
		var doctorWidgetList = new Array();
		var testAttributetWidgetList = new Array();
		var otherAtributeWidgetList = new Array();

		for( var i=0 ; i<testAttributeWidgetArray.length ; i++){
			
			for( var j = 0 ; j < this.treePanel.mandatoryWidgetCodeList.length ; j++){
				if( testAttributeWidgetArray[i].data.widgetCode === this.treePanel.mandatoryWidgetCodeList[j] ){
					this.treePanel.mandatoryWidgetCodeList.remove( this.treePanel.mandatoryWidgetCodeList[j] );
				}
			}
			
			var widgetConfig = {
				widgetCode : testAttributeWidgetArray[i].data.widgetCode,
				sequenceNbr : i+1
			};
			testAttributetWidgetList.push( widgetConfig );
		}
		
		
		for( var i=0 ; i<patientWidgetArray.length ; i++){
			var widgetConfig = {
				widgetCode : patientWidgetArray[i].data.widgetCode,
				sequenceNbr : i+1
			};
			patientWidgetList.push(widgetConfig );
		}
		
		for( var i=0 ; i<doctorWidgetArray.length ; i++){
			var widgetConfig = {
				widgetCode : doctorWidgetArray[i].data.widgetCode,
				sequenceNbr : i+1
			};
			doctorWidgetList.push(widgetConfig);
		}

		for( var i = 0; i < approverDetailWidgetArray.length ; i++){
			var widgetConfig = {
				widgetCode : approverDetailWidgetArray[i].data.widgetCode,
				sequenceNbr : i+1
			};
			otherAtributeWidgetList.push(widgetConfig);
		}

		for( var i = 0; i < templateDetailWidgetArray.length ; i++){
			var widgetConfig = {
				widgetCode : templateDetailWidgetArray[i].data.widgetCode,
				sequenceNbr : i+1
			};
			otherAtributeWidgetList.push(widgetConfig);
		}
		
		
		var templateBM = {
			testCode : this.configData.testCode,
			createdBy : getAuthorizedUserInfo().userName,
			patientWidgetList : patientWidgetList,
			doctorWidgetList : doctorWidgetList,
			testAttributeWidgetList:testAttributetWidgetList,
			otherWidgetList : otherAtributeWidgetList
		};
		
		templateBM.templateId = this.configData.testTemplateBM.templateId;
		
		if( !Ext.isEmpty( this.treePanel.mandatoryWidgetCodeList )){
			Ext.Msg.show({
				msg : limsMsg.mandatoryWidgetMsg,
				buttons : Ext.Msg.OK,
				fn : function(){
					
				}
			});
		}
		else {
				TestTemplateManager.modifyTestTemplate(templateBM,function(){
				Ext.ux.event.Broadcast.publish('closeAddEditTemplate');
			});
		}	
	},
	
	// If this window will be the Enter test result then on click of save this method will be execute.
	// This method is responsible for the saving of the test attributes value.
	
	saveBtnClickedForEnterTestResult : function(){
		
		var testAttributeWidgetArray = this.testAttributeFieldSet.items.items;
		var templateDetailPanelData = this.templateDetailPanel.items.items;
		var approverDetailPanelData = this.approverDetailPanel.items.items;
		var patientTestAttrValueBMList = new Array();
		
		for( var i = 0 ; i < testAttributeWidgetArray.length ; i++){
			var isEditedField = this.isEditedField( testAttributeWidgetArray[i]);
			if( isEditedField ){
				var widgetName = testAttributeWidgetArray[i].data.widgetCode;
				if( !Ext.isEmpty(testAttributeWidgetArray[i].numberField )){
					var widgetValue = testAttributeWidgetArray[i].numberField.getValue();
				}
				else if( !Ext.isEmpty(testAttributeWidgetArray[i].widget ) ){
					widgetValue  = testAttributeWidgetArray[i].widget.getValue();
				}	
	
				var PatientTestAttrValueBM = {
					testAttribute : {code : widgetName},
					seqNbr : i+1,
					attrValue : widgetValue 
				};
				
				patientTestAttrValueBMList.push( PatientTestAttrValueBM );
			}
		}
		
		var patientTestDetailBM = {
			patientTestId : this.configData.testTemplateBM.serviceUID,
			patientTestAttrValueBMList : patientTestAttrValueBMList,
			createdBy : getAuthorizedUserInfo.userName
		};
		
		LabTestManager.savePatientLabTestDetail( patientTestDetailBM ,function(){
			Ext.ux.event.Broadcast.publish('closeEnterTestResult');
		});
		
	},
	
	
	resetBtnClicked : function(){
		if( this.configData.isEnterTestResult ){
			this.testAttributeFieldSet.removeAll();
			if( !Ext.isEmpty( this.configData.testTemplateBM.testAttributeWidgetList )){
				this.dataArray = new Array();
				for ( var i = 0 ; i <this.testTemplate.testAttributeWidgetList.length ;i++){
					 widget = this.getWidget(this.configData.testTemplateBM.testAttributeWidgetList[i]);
					 
					 widget.remove( widget.items.items[1] ); 
					 if( !Ext.isEmpty( widget.numberField )){
						 widget.numberField.readOnly = false;
					 }
					 else{
						 widget.widget.readOnly = false;
						 widget.widget.anchor = '49%';
					 }
					 
					 this.dataArray.push( widget.getPanel());

				}
				
				this.testAttributeFieldSet.add( this.dataArray );
				this.testAttributeFieldSet.doLayout();
			}
		}
		else{
			var isReset = true;
			this.patientDetailFieldSet.removeAll();
			this.doctorDetailFieldSet.removeAll();
			this.testAttributeFieldSet.removeAll();
			this.loadWidget( this.configData.testTemplateBM , isReset );
		}	
	},
/*
 *  This method is responsible for creating widget Dynamically.
 *	In this test template panel we have five panel in which we are creating widgets dynamically.
 *	first is test related detail like  investigator name , test name .
 * 	second is for apprver detail like approver name, approved date and approver signature.
 * 	Third panel is for patient details .
 *  Fourth panel is for Doctor details.
 *  Fifth panel is for specific test attribute details
 *  This will be populated based on the configured template.
 *    
 */
	
	loadWidget : function( testTemplateBM , isReset ){

		this.testTemplate = this.configData.testTemplateBM;
		var widget;
		if( !Ext.isEmpty( this.testTemplate.patientWidgetList)){
		
			this.dataArray = new Array();
			
			for ( var i = 0 ; i <this.testTemplate.patientWidgetList.length ;i++){
				 widget  = this.getWidget(this.testTemplate.patientWidgetList[i]);
				 if( this.configData.isEnterTestResult ){
					 widget.remove( widget.items.items[1] );
					 widget.widget.disable();
				 }
				 this.dataArray.push( widget.getPanel());

			}

			this.patientDetailFieldSet.add( this.dataArray );
			this.patientDetailFieldSet.doLayout();
			this.patientFieldHeight = this.patientDetailFieldSet.getHeight();
		}
		
		if( !Ext.isEmpty( this.testTemplate.doctorWidgetList)){
			this.dataArray = new Array();
			for ( var i = 0 ; i <this.testTemplate.doctorWidgetList.length ;i++){
				 widget = this.getWidget(this.testTemplate.doctorWidgetList[i]);

				 if( this.configData.isEnterTestResult ){
					 widget.remove( widget.items.items[1] );
					 widget.widget.disable();
				 }
				 
				 this.dataArray.push( widget.getPanel()) ;

			}
			
			this.doctorDetailFieldSet.add( this.dataArray );
			this.doctorDetailFieldSet.doLayout();
			this.doctorFieldHeight = this.doctorDetailFieldSet.getHeight();
		}
		
		if( !Ext.isEmpty( this.testTemplate.testAttributeWidgetList )){
			this.dataArray = new Array();
			for ( var i = 0 ; i <this.testTemplate.testAttributeWidgetList.length ;i++){
				 widget = this.getWidget(this.testTemplate.testAttributeWidgetList[i]);
				 
				 if( this.configData.isEnterTestResult ){
					 widget.remove( widget.items.items[1] ); 
					 if( !Ext.isEmpty( widget.numberField )){
						 if( this.testTemplate.testAttributeWidgetList[i].isMandatory === "Y"){
							 if( Ext.isEmpty( this.testTemplate.testAttributeWidgetList[i].widgetValue )){
								 this.buttonsPanel.getMarkCmpldBtn().disable();
							 }
							 widget.numberField.allowBlank = false;
							 widget.numberField.required = true;
						 }
						 widget.numberField.readOnly = false;
					 }
					 else{
						 if( this.testTemplate.testAttributeWidgetList[i].isMandatory === "Y"){
							 if( Ext.isEmpty( this.testTemplate.testAttributeWidgetList[i].widgetValue )){
								 this.buttonsPanel.getMarkCmpldBtn().disable();
							 }
							 widget.widget.allowBlank = false;
							 widget.widget.required = true;
						 }
						 widget.widget.readOnly = false;
						 widget.widget.anchor = '49%';
					 }
				 }
				 
				 if( this.configData.isEditTemplate && Ext.isEmpty( widget.numberField ) ){
					 widget.widget.anchor = '49%';
				 }
				 
				 this.dataArray.push( widget.getPanel());

			}
			
			this.testAttributeFieldSet.add( this.dataArray );
		}
		else{
			if( this.testAttributeFieldSet.items.items.length === 0 ){
				this.testAttributeFieldSet.setHeight( 86 );
			}
		}
		
		if( !isReset ){
		
			if( !Ext.isEmpty( this.testTemplate.otherWidgetList )){
				for( var i = 0 ; i < this.testTemplate.otherWidgetList.length ; i++){
					widget = this.getWidget( this.testTemplate.otherWidgetList[i] );
					widget.remove( widget.items.items[1] );	
					if( widget.widget.sectionCode == limsMsg.SECTION_CODE_4){
						this.templateDetailPanel.add( widget.getPanel() );
					}
					else if( widget.widget.sectionCode == limsMsg.SECTION_CODE_5 ){
						this.approverDetailPanel.add( widget.getPanel() );
					}
				}
				this.approverDetailPanel.doLayout();
				this.templateDetailPanel.doLayout();
			}
			
			if( !Ext.isEmpty( this.avalaibaleListFieldSet )){
				this.treePanel = new LIMS.testTemplate.configure.AvailableListTreePanel({ data : testTemplateBM });
			
				this.avalaibaleListFieldSet.add( this.treePanel.getPanel() );
				
				this.treePanel.getPanel().on('startdrag',function( thisPanel , node , e ){
					this.dragStarted(thisPanel, node, e);
				},this);
			}	

		}
		this.doLayout();
	
	},
	
	// This method is resposible for the creating widget based on the widget data.
	// widget data means:-
	// widgetType :- What type of widget it will be(for ex - "textfield")
	// widgetCode :- what will be the widget name(for ex - "patientName")
	// widgetLabel :- what will be the widget label ( for ex:- "Patient name")
	// widgetValue :- if any widget is having any value ( for ex :- "Ramesh")
	// MVLValueList :- if widget is  type of combo then this MVLValueList will have its value.
	getWidget : function( widgetData ){
		var config = {
				widgetCode : widgetData.widgetCode,
				widgetType : widgetData.widgetType,
				widgetLabel : widgetData.widgetLabel,
				widgetValue : widgetData.widgetValue,
				sectionCode : widgetData.sectionCode,
				minValue : widgetData.minValue,
				maxValue : widgetData.maxValue,
				MVLValueList : widgetData.MVLValueList
		};
		var widget = new LIMS.testTemplate.configure.DynamicWidgetPanel( {data : config} );
		
		if( !Ext.isEmpty( widget.numberField )){
			widget.numberField.on('blur',function( thisWidget){
				thisWidget.isEdited = true;
			},this);
		}
		else{
			widget.widget.on('blur',function( thisWidget ){
				thisWidget.isEdited = true;
			},this);
		}
		
		return widget;

	},
	
	// When someone start dragging from the tree then this mehod will be invoke.
	// First this will set the drop target(target panel body). based on the dragged data.
	// based on this drop target this method will instantiate DROPTARGET . 
	// This DROPTARGET is taking drop target body as a input.
	// And after dropping, dragged data into its target then notifyDrop is being called.
	// this is responsible for creating widget based on the dropped data into the drop target body. 
	
	dragStarted : function( thisPanel , node , e){
		var mainThis = this;
		if( !Ext.isEmpty( this.dd )){
			this.dd.unreg();
		}
		this.nodeData = null;
		
		if( !Ext.isEmpty( node.attributes.children ) || node.attributes.leaf){
	
			if( node.attributes.leaf ){
				this.sectionCode = node.attributes.sectionCode;
			}
			else{
				this.sectionCode = node.attributes.children[0].sectionCode;
			}
			this.dropTarget = null;
			var targetPanel = null;
			if( this.sectionCode == limsMsg.SECTION_CODE_1){
				this.dropTarget = this.patientDetailFieldSet.body;
				targetPanel = this.patientDetailFieldSet;
			}
			else if( this.sectionCode == limsMsg.SECTION_CODE_2 ){
				this.dropTarget =this.doctorDetailFieldSet.body;
				targetPanel = this.doctorDetailFieldSet;
			}
			else if( this.sectionCode == limsMsg.SECTION_CODE_3 ){
				this.dropTarget = this.testAttributeFieldSet.body;
				targetPanel = this.testAttributeFieldSet;
			}
			
			this.dd = new Ext.dd.DropTarget(this.dropTarget,{
				ddGroup : 't2div',
				targetId:targetPanel.id,
				notifyDrop : function( dd , e , node){
					this.target = e.getTarget();
					var id = this.target.id;
					var component = targetPanel.findById( id );
				
					this.childArray = new Array();
					if( node.node.attributes.leaf){
						this.childArray.push( node.node.attributes ); 
					}
					else{
						this.childArray = node.node.attributes.children;
					}
							
					this.widgetForInsertion = new Array();
					
					for( var i = 0 ; i<this.childArray.length ; i++){
						
   					var config = {
							widgetCode : this.childArray[i].widgetCode,
							widgetType : this.childArray[i].widgetType,
							widgetLabel : this.childArray[i].widgetLabel,
							sectionCode : this.childArray[i].sectionCode,
							minValue : this.childArray[i].minValue,
							maxValue : this.childArray[i].maxValue,
							MVLValueList : this.childArray[i].MVLValueList
						}; 
					if( !Ext.isEmpty( component )){
						var component1 = component.ownerCt.ownerCt;
						this.indexNbr = mainThis.getIndexNbr( Ext.getCmp(this.targetId) , component1);
					}
					
					var widget = mainThis.getWidget( config );
					
					if( Ext.isEmpty( widget.numberField ) && (this.targetId === mainThis.testAttributeFieldSet.id )){
						widget.widget.anchor = '49%';
					}
					
					if( !Ext.isEmpty( this.indexNbr )){
						Ext.getCmp(this.targetId).insert( this.indexNbr + 1 ,widget );
					}
					else{
						Ext.getCmp(this.targetId).add( widget );
					}
					
					if( Ext.getCmp(this.targetId).items.items.length > 2 ){
						Ext.getCmp(this.targetId).setHeight('auto');
					}
					}
					Ext.getCmp(this.targetId).doLayout();
					return true;
				}
				
			});
		}

	},
	// This method will be invoke while saving the attribute value in the DB.
	// This method is resposible to tell that this attributes has been edited or not.
	
	isEditedField : function( thisField ){
		if( !Ext.isEmpty(thisField.numberField) ){
			return thisField.numberField.isEdited ;
		}
		else if( !Ext.isEmpty(thisField.widget) ){
			 return thisField.widget.isEdited ; 
		}
	},

	getIndexNbr : function( container , component ){
		var containerData = container.items.items;
		
		for( var i = 0 ; i < containerData.length ; i++){
			if( component.id == containerData[i].id){
				return i;
			}
			else {
				componentInContainerId = containerData[i].items.items[0].items.items[0].id
				if( componentInContainerId === component.id){
					return i;
				}
			}
		}
		return null;
	},
	// This will be invoke when someone click on the print on the Enter test result screen.
	prinBtnClicked : function(){
		var enterTestResult = this.cloneConfig( this );
		var window  =new  Ext.ux.PrinterWindow({
			panel : enterTestResult,
			frame : true
			
		});
		window.show();
	}


	
	
}); 