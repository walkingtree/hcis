Ext.namespace("IPD.addOrder");

IPD.addOrder.DoctorOrderTemplateServicesPanel = Ext.extend(Object,{
	constructor : function(config) {
		this.panelObj = this;
		this.addBtn = new Ext.Button({
	    	iconCls : 'add_btn',
	    	text: 'Add',
	    	scope:this
		});
		if(Ext.isEmpty(config)){
			config = {};
		}
		this.serviceCbx = new Ext.form.ComboBox({
			fieldLabel:	'Service name',
			hiddenName:'serviceCode',
			mode:'local',
			store: loadServicesCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '45%',
	        forceSelection : true
			
		});
		this.serviceGroupCbx = new Ext.form.ComboBox({
			fieldLabel:	'Service group',
			hiddenName:'serviceGroup',
			mode:'local',
			store: loadAddServiceGroupCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '90%',
	        forceSelection : true,
	        listeners: {
	        	select: function(comp, record,index){
	        		mainThis.serviceForGroupCbx.serviceTypeStore.removeAll();
	        		mainThis.serviceForGroupCbx.serviceTypeStore.load({params:{start:0, limit:9999}, arg:[record.data.code]});
	        	}
	        }
			
		});
		this.serviceForGroupCbx = new OPD.billing.ServiceCombo({anchor:'80%'});
		
		this.servicePackageCbx = new Ext.form.ComboBox({
			fieldLabel:	'Service package',
			hiddenName:'servicePackage',
			mode:'local',
			store: loadServicePackageCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '45%',
	        forceSelection : true
			
		});
		
		this.departmentCbx = new Ext.form.ComboBox({
			fieldLabel:	'Department',
			hiddenName:'responsibleDepartmentCode',
			mode:'local',
			store: loadDepartmentsCbx(),
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor : '63%',
	        forceSelection : true,
	        value:config.responsibleDepartmentCode
		});
		
		var mainThis = this;
		this.ServiceTypeSelectionGroup = new Ext.form.RadioGroup({
			name:'svcType',
			labelSeparator:'',
//			columns: [.31, .32, .31],
       		items: [
                    {boxLabel: "Individual service selection", name: 'svcTypeCd', inputValue: 1,checked: true},
                    {boxLabel: "Service group", name: 'svcTypeCd', inputValue: 2},
                    {boxLabel: "Service Package", name: 'svcTypeCd', inputValue: 3}
       			   ],
      		listeners:{
      			change : {
      				fn : function(radioGroup, value){
          				if(value === '1'){
      						mainThis.serviceTypeCardPanel.layout.setActiveItem(0);
      						mainThis.serviceTypeCardPanel.doLayout();
          				} else if(value === '2'){
							mainThis.serviceTypeCardPanel.layout.setActiveItem(1);
							mainThis.serviceTypeCardPanel.doLayout();
          				} else if(value === '3'){
							mainThis.serviceTypeCardPanel.layout.setActiveItem(2);
							mainThis.serviceTypeCardPanel.doLayout();
          				}
      				}
      			},
      			scope:this
      		}
	 	});
	 	this.serviceTypeCardPanel = new Ext.Panel({
	 		width:'100%',
			autoScroll : true,
			activeItem:0,
			deferredRender:false,
			renderHidden:true,
			layout:'card',
	 		items:[
	 			{
	 				layout:'form',
	 				labelWidth: 80,
	 				items:this.serviceCbx
	 			},
	 			{
	 				layout:'column',
	 				defaults: {columnWidth :.5},
	 				items:[
	 					{
	 						layout:'form',
	 						labelWidth: 80,
	 						items: this.serviceGroupCbx
	 					},
	 					{
	 						layout:'form',
	 						labelWidth: 80,
	 						items: this.serviceForGroupCbx.getCombo()
	 					}
	 				]
	 			},
	 			{
	 				layout:'form',
	 				labelWidth: 100,
	 				items:this.servicePackageCbx
	 			}
	 		]
	 	});
		this.serviceTypeselectionPanel = new Ext.Panel({
			height:'98%',
			border:false,
//			title:'Select service',
			items:[
				this.ServiceTypeSelectionGroup
			]
		});
		this.doctorOrderTemplateServicesFieldSet = new Ext.form.FieldSet({
			title : 'Template services',
			collapsible: false,
			labelAlign:'left',
			width : '92%',
			autoHeight: true,
			border : true,
			style: 'padding-top:10px',
			items: [
				{
					labelWidth:.01,
					style:'margin-bottom:5px',
					items:this.serviceTypeselectionPanel
				},
				{
					layout : 'column',
					defaults: {
						border: false,
						layout: 'form',
						labelWidth : 140,
						columnWidth : .3
					},
					items: [
						{
							layout:'form',
							border:false,
							items:[
								{
							        fieldLabel: 'Sequence number',
							        name: 'sequenceNumber',
							        xtype: 'numberfield',
							        anchor : '95%',//70%
							        value:config.sequenceNumber
								}
							]
						},{
							columnWidth:0.7,
							layout:'form',
							border:false,
							items:this.serviceTypeCardPanel 
						},{
							layout:'form',
							border:false,
							items:[
								{
							        fieldLabel: 'Sub-sequence number',
							        name: 'subSequenceNumber',
							        xtype: 'numberfield',
							        anchor : '95%',//70%
							        value:config.subSequenceNumber
								}
							]
						},
						{
							columnWidth : .5,
							layout:'form',
							labelWidth : 80,
							border:false,
							items:this.departmentCbx
					    },
					    {
							layout:'form',
							border:false,
							columnWidth : .63,
							items:[
								{
									xtype:'textfield',
									fieldLabel:	'Action required',
									name:'actionRequiredDescription',
							        anchor : '98%',
							        value:config.actionRequiredDescription
				   				}
							]
					    },
					    {
					    	columnWidth : .2,
							layout:'form',
							border:false,
							items:this.addBtn
			    		}
				    ]
				}
			]
		});
		this.doctorOrderTemplateServicesPanel = new Ext.Panel({
			border : true,
			items: [
				this.doctorOrderTemplateServicesFieldSet
			]
		});
	},
	getPanel : function() {
		return this.doctorOrderTemplateServicesPanel;
	},
	hidePanel : function(){
		this.doctorOrderTemplateServicesPanel.hide();
	}
});