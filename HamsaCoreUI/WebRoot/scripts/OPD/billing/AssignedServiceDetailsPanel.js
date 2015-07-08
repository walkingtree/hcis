Ext.namespace("OPD.billing");

OPD.billing.AssignedServiceDetailsPanel = Ext.extend(Object, {
	constructor : function(){
		Ext.QuickTips.init();
		
		this.noOfUnitsNbrField = new Ext.form.NumberField({
			fieldLabel : opBillingMsg.noOfUnits,
			name : 'noOfUnits',
			anchor : '50%',
			value:1
		});

		this.addBtn = new Ext.Button({
			text : opBillingMsg.add,
			iconCls : 'add_btn',
			scope : this,
			tooltip: opBillingMsg.addEntryIntoGrid
		});
		
		this.ServiceTypeSelectionGroup = new Ext.form.RadioGroup({
			name : 'serviceType',
			labelSeparator : '',
			columns : [130, 130,130,130],
       		items : [
                    {boxLabel: opBillingMsg.individualService , name: 'serviceTypeCd', inputValue: 1,checked: true},
                    {boxLabel: opBillingMsg.serviceGroup , name: 'serviceTypeCd', inputValue: 2},
                    {boxLabel: opBillingMsg.servicePackage , name: 'serviceTypeCd', inputValue: 3},
                    {boxLabel: opBillingMsg.test , name: 'serviceTypeCd', inputValue: 4},
       			   ],
      		listeners:{
      			change : {
      				fn : function(radioGroup, value){
						this.assignedServiceCardpnl.resetData();
						this.serviceDateDt.reset();
          				if(value === '1'){
          					mainThis.assignedCardPnl.setWidth(200);
      						mainThis.assignedServiceCardpnl.getPanel().layout.setActiveItem(0);
      						mainThis.assignedServiceCardpnl.getPanel().doLayout();
      						mainThis.servieDatePnl.show();
          				} else if(value === '2'){
          					mainThis.assignedCardPnl.setWidth(400);
							mainThis.assignedServiceCardpnl.getPanel().layout.setActiveItem(1);
							mainThis.assignedServiceCardpnl.getPanel().doLayout();
							mainThis.servieDatePnl.show();
          				} else if(value === '3'){
          					mainThis.assignedCardPnl.setWidth(250);
							mainThis.assignedServiceCardpnl.getPanel().layout.setActiveItem(2);
							mainThis.assignedServiceCardpnl.getPanel().doLayout();
							mainThis.servieDatePnl.hide();
          				} else if(value === '4'){
          					mainThis.assignedCardPnl.setWidth(250);
							mainThis.assignedServiceCardpnl.getPanel().layout.setActiveItem(3);
							mainThis.servieDatePnl.items.items[0].fieldLabel = "Test date";
							mainThis.assignedServiceCardpnl.getPanel().doLayout();
							mainThis.servieDatePnl.show();
          				}
      				}
      			},
      			scope:this
      		}
	 	});
		
 		var mainThis = this;
 		
	 	this.assignedServiceCardpnl = new OPD.billing.AssignedServiceCardPanel();
	 	
	 	this.buttonPanel = new Ext.Panel({
	 		buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
	 		items:[ this.addBtn ]
	 	});
	 	
	 	this.serviceDateDt = new Ext.form.WTCDateTimeField({
	 		fieldLabel: opBillingMsg.serviceDate,
	 		name: 'serviceDate',
	 		anchor:'95%',
	 		value : new Date()
	 	});
	 	this.servieDatePnl = new Ext.Panel({
	 		layout:'form',
	 		items:this.serviceDateDt
	 	});
	 	this.assignedCardPnl = new Ext.Panel({
			layout : 'form',
			width:200,
			items : this.assignedServiceCardpnl.getPanel()
		});
		this.assignedServiceDetailsPnl = new Ext.Panel({
			layout:'column',
			items:[
				{
					layout : 'form',
					labelWidth:.01,
					columnWidth:1,
					items : this.ServiceTypeSelectionGroup
				},
				{
					layout : 'form',
					columnWidth:.30,
					labelWidth : 80,
					items : this.servieDatePnl
				},
				{
					columnWidth: .55,
					items: this.assignedCardPnl
				},
//				{
//					layout : 'form',
//					columnWidth:.40,
//					items : this.noOfUnitsNbrField
//				},
				{
					layout : 'form',
					columnWidth : .15,
//					columnWidth:.60,
					items : [this.buttonPanel]
				}
			]
		});
	},
	getPanel : function() {
		return this.assignedServiceDetailsPnl;
	},
	getData: function(){
		
		var recordForAdd ={
			noOfUnits: this.noOfUnitsNbrField.getValue()
		};
		if(this.ServiceTypeSelectionGroup.getValue() == 1){
			var serviceCode = this.assignedServiceCardpnl.individualServiceCbx.getCombo().getValue();
			if( Ext.isEmpty( serviceCode  ) ){
				error( opBillingMsg.selectServiceOrPackage );
				return;
			}
			recordForAdd.serviceRec = this.assignedServiceCardpnl.getServiceRecForServiceCode(serviceCode);
			recordForAdd.packageInd = configs.packageIndicatorNo;
			recordForAdd.serviceCharge = recordForAdd.serviceRec.serviceCharge;
			recordForAdd.serviceDate = this.serviceDateDt.getValue();
		}else if(this.ServiceTypeSelectionGroup.getValue() == 2){
			var serviceCode = this.assignedServiceCardpnl.serviceForGroupCbx.getCombo().getValue();
			if( Ext.isEmpty( serviceCode  ) ){
				error( opBillingMsg.selectServiceOrPackage );
				return;
			}
			recordForAdd.serviceRec = this.assignedServiceCardpnl.getServiceRecForServiceCode(serviceCode);
			recordForAdd.packageInd = configs.packageIndicatorNo;
			recordForAdd.serviceCharge = recordForAdd.serviceRec.serviceCharge;
			recordForAdd.serviceDate = this.serviceDateDt.getValue();
		}else if(this.ServiceTypeSelectionGroup.getValue() == 3){
			var servicePackageCode = this.assignedServiceCardpnl.servicePackageCbx.getValue();
			if( Ext.isEmpty( servicePackageCode  ) ){
				error( opBillingMsg.selectServiceOrPackage );
				return;
			}
			recordForAdd.servicePackageRec = this.assignedServiceCardpnl.getServicePackageRecForServicePackageCode(servicePackageCode);
			recordForAdd.packageInd = configs.packageIndicator;
			recordForAdd.serviceCharge = recordForAdd.servicePackageRec.serviceCharge;
		} else if(this.ServiceTypeSelectionGroup.getValue() == 4){
			var testServiceCode = this.assignedServiceCardpnl.labTestServiceCbx.getValue();
			if( Ext.isEmpty( testServiceCode  ) ){
				error( opBillingMsg.selectTest );
				return;
			}
			recordForAdd.serviceRec = this.assignedServiceCardpnl.getServiceRecForTestServiceCode( testServiceCode );
			recordForAdd.packageInd = configs.packageIndicatorNo;
			recordForAdd.serviceCharge = recordForAdd.serviceRec.serviceCharge;
			recordForAdd.serviceDate = this.serviceDateDt.getValue();
		}
		return recordForAdd;
	},
	loadData: function(data){
		this.noOfUnitsNbrField.setValue(data.noOfUnits);
		if(data.packageInd!=configs.packageIndicator){
			this.serviceDateDt.setValue(data.serviceDate);
		}
		this.ServiceTypeSelectionGroup.setValue(data.packageInd==configs.packageIndicator?3:1);
		this.assignedServiceCardpnl.loadData(data);
	},
	resetData: function( config ){
		if( Ext.isEmpty( config )){
			config = {};
		}
		if( !config.isFromAddBtn ){
			this.noOfUnitsNbrField.setValue(1);
			this.ServiceTypeSelectionGroup.setValue(1);
		}
		this.assignedServiceCardpnl.resetData();
		this.serviceDateDt.reset();
	}
	
});