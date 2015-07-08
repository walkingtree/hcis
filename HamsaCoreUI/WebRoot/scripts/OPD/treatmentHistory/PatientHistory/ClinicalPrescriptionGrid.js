Ext.namespace("OPD.treatmentHistory.PatientHistory");

OPD.treatmentHistory.PatientHistory.ClinicalPrescriptionGrid = Ext.extend(Ext.grid.GridPanel,{
	initComponent: function(){
		
		this.assignedServiceRecord = Ext.data.Record.create([
		 	{ name : "serviceCode", mapping:'service', convert: getCode },
		 	{ name : "serviceName" , mapping:'service', convert: getDescription },
		 	{ name : "packageCode", mapping:'servicePackage', convert: getCode},
		 	{ name : "packageName" ,mapping:'servicePackage', convert: getDescription},
		 	{ name : "serviceStatusCode", mapping:'assignedServiceStatus', convert: getCode },
		 	{ name : "serviceStatusDesc", mapping:'assignedServiceStatus', convert: getDescription },
		 	{ name : "requestedUnits", mapping:'requestedUnits'},
		 	{ name : "renderedUnits", mapping:'renderedUnits' },
		 	{ name : 'canceledUnits', mapping:'canceledUnits'},
		 	{ name : "billedUnits", mapping:'billedUnits' },
		 	{ name : "lastBillNbr" },
		 	{name : "serviceOrderNumber", mapping:"serviceOrderNumber"},
		 	{name : 'packageAsgmId', mapping: 'packageAsgmId'},
		 	{ name: 'serviceDate', mapping:'serviceDate'},
		 	{ name: 'patientId', mapping:'patientId'},
		 	{ name: 'serviceUid', mapping:'serviceUid'},
		 	{ name:'referenceNumber', mapping:'referenceNumber'},
		 	{ name:'packageAsgmId', mapping:'packageAsgmId'},
		 	{ name:'referenceNumber', mapping:'referenceNumber'},
		 	{ name:'appointmentDate', mapping:'appointmentDate'},
		 	{ name:'hideView', convert: function(val, rec){
							 			if(rec.assignedServiceStatus.code == mngSvcAsgntMsg.ASSIGNED_SERVICE_APPROVED){
							 				//TODO: at same time check for serviceType 'LABORATORY'.for now it is fine
							 				return false;
							 			}
							 			return true;
						    		}
		 	}
		 	
		]);	
		
		this.dataStore = new Ext.data.GroupingStore({
			proxy: new Ext.data.DWRProxy(ServiceManager.getAssignedServiceHistory,true),
			reader: new Ext.data.ListRangeReader( 
					{id:'id', totalProperty:'totalSize'}, this.assignedServiceRecord ),
        	groupField:'referenceNumber',
        	sortInfo: {field: 'referenceNumber', direction: "ASC"},
        	remoteSort: true
		});
		
	 	
		this.action = new Ext.ux.grid.RowActions({
			header:'Actions',
			keepSelection:true,
			widthSlope : 50,
			actions:[
				{
					iconCls:'view-icon',
					tooltip:'View test result',
					hideIndex:'hideView'
				}
			]
		});
	 	
		this.action.on('action',function(grid, record, action, row, col){
			
			var serviceUID = record.data.serviceUid;
			LabTestManager.getTestResultValue( serviceUID , function( testTemplateBM ){
				testTemplateBM.serviceUID = serviceUID;
				var configureTestTemplate = new LIMS.testTemplate.configure.ConfigureTestTemplate({isEnterTestResult : true,
					   testTemplateBM : testTemplateBM,
					   testCode : testTemplateBM.testCode,
					   MODE : "VIEW" });
			
				var activeTab = layout.getCenterRegionTabPanel().add({
				frame:true,
				title : limsMsg.enterTestResult, 
				closable : true,
				height : 430,
				items : [configureTestTemplate]
				});
				
				layout.getCenterRegionTabPanel().setActiveTab( activeTab );
				
//				Ext.ux.event.Broadcast.subscribe('closeEnterTestResult', function(){
//					layout.getCenterRegionTabPanel().remove( activeTab , true);
//					layout.getCenterRegionTabPanel().setActiveTab( mainThis );
//					layout.getCenterRegionTabPanel().doLayout();
//					mainThis.detailGrid.loadData();
//				},this, null, mainThis.getGrid());
			
		});},this);
		
	     Ext.apply(this,{
	     	autoScroll:true,
			stripeRows:true,
			height:450,
			viewConfig:{forceFit:true},
			ds: this.dataStore,
			plugins : this.action,
			cm: new Ext.grid.ColumnModel ([
//				{ header: mngSvcAsgntMsg.patientId, dataIndex : "patientId", sortable: true, width: 70 },
				{ header: mngSvcAsgntMsg.serviceCode, dataIndex : "serviceCode", sortable: true, width: 70 },
			 	{ header: mngSvcAsgntMsg.serviceName, dataIndex : "serviceName", sortable: true, width: 70  },
			 	{ header: mngSvcAsgntMsg.packageName, dataIndex : "packageName", sortable: true, width: 70  },
			 	{ header: mngSvcAsgntMsg.serviceStatus, dataIndex : "serviceStatusDesc", sortable: true, width: 70  },
			 	{ header: mngSvcAsgntMsg.requestedUnits, dataIndex : "requestedUnits",align:'right', sortable: true, width: 60  },
			 	{ header: mngSvcAsgntMsg.renderedUnits, dataIndex : "renderedUnits",align:'right', sortable: true, width: 60  } ,
			 	{ header: mngSvcAsgntMsg.billedUnits, dataIndex : "billedUnits", align:'right',sortable: true, width: 50  },
			 	{ header: mngSvcAsgntMsg.canceledUnits, dataIndex : "canceledUnits",align:'right', sortable: true, width: 70  },
			 	{ header: mngSvcAsgntMsg.lastBillNbr, dataIndex : "lastBillNbr", sortable: true, width: 50  },
			 	{ header: mngSvcAsgntMsg.serviceDate, dataIndex : "serviceDate", sortable: true, 
			 		renderer :Ext.util.Format.dateRenderer('d/m/Y'),width: 55  },
		 		{ header: mngSvcAsgntMsg.packageAssignementId, dataIndex : "packageAsgmId", sortable: true, width:50 },
		 		{ header: "Appointment number",hidden:true, dataIndex : "referenceNumber", sortable: true, width: 70  },
		 		this.action
			]),
			view:  new Ext.grid.GroupingView({
			        viewConfig:{forceFit:true},
			        hideGroupedColumn:true,
			        enableGroupingMenu: false,
			        // custom grouping text template to display the number of items per group
			        groupTextTpl: '{[getclinicalPrescriptionGroupText( values )]}'
			    })
	     
	     });
	     
	     OPD.treatmentHistory.PatientHistory.ClinicalPrescriptionGrid.superclass.initComponent.apply(this, arguments);
	},
     getGrid: function(){
     	return this;
     }
});
function getclinicalPrescriptionGroupText( values ){
	var text='';
	if(!Ext.isEmpty( values ) && !Ext.isEmpty( values.rs[0] ) && 
		values.rs[0].data.appointmentDate == null ){
		text += "Direct services"
	}else{
		text = values.text;
		if( !Ext.isEmpty( values ) && !Ext.isEmpty( values.rs[0] )){
			text += " Appointment date: "+  Ext.util.Format.date(values.rs[0].data.appointmentDate,'d/m/Y');
		}
	}
	return text;
}