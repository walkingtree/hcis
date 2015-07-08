Ext.namespace("OPD.billing");

OPD.billing.AssignedServicePanel = new Ext.extend(Object,{
	constructor : function(config) {
	
	if( Ext.isEmpty( config )){
		config = { };
	}
		var mainThis = this;
		this.newAssignedServiceList = new Array();
		this.newAssigendServicePackageList = new Array();
		this.deletedAssignedServiceList = new Array();
		this.deletedAssignedServicePAckageList = new Array();
		
		this.assignedServiceDetailsPnl = new OPD.billing.AssignedServiceDetailsPanel();
		this.assignedServiceGridPnl = new OPD.billing.AssignedServiceGrid();
		
			if(!Ext.isEmpty(config.typeOfConsultation)&&config.typeOfConsultation == 'doctorConsultation'){
				
				ServiceManager.getAssignedServiceAndPackageList(config.appointmentNumber,
						configs.referenceTypeForOPD, 
						false,
						function(assignedServiceAndPackageBM){
					if( assignedServiceAndPackageBM!=null && assignedServiceAndPackageBM.length >0 ){
						
						for( var k = 0; k < assignedServiceAndPackageBM.length; k++ ){
							mainThis.serviceOrderNumber = assignedServiceAndPackageBM[k].serviceOrderNumber;
							mainThis.assignedServiceGridPnl.loadData(assignedServiceAndPackageBM[k]);
						}
					}
				});
//			this.serviceOrderNumber = mainThis.serviceOrderNumber;
			}
		
		
		this.assignedServiceGridPnl.editBtn.addListener('click',function(){
			this.editEntryInGrid();
			this.assignedServiceGridPnl.editBtn.disable();
			this.assignedServiceGridPnl.deleteBtn.disable();
		},this);
		
		this.assignedServicePnl = new Ext.Panel({
			width:'100%',
			height : '100%',
			items:[
				this.assignedServiceDetailsPnl.getPanel(),
				this.assignedServiceGridPnl.getPanel()
			]
		});
		
		this.assignedServiceDetailsPnl.addBtn.on( 
			'click', 
			function(){
				this.addDetailsToGrid();
			}, 
			this 
		);
		this.assignedServiceGridPnl.assocSvcDataStore.on('add', function( store,records,index){
			for(var i = 0; i<records.length; i++){
				if(Ext.isEmpty(records[i].data.serviceOrderNumber)){
					var assignedBm = this.convertRecordToBm(records[i].data);
					if(!Ext.isEmpty(assignedBm.service)){
						this.newAssignedServiceList.push(assignedBm);
					}else{
						this.newAssigendServicePackageList.push(assignedBm);
					}
				}
			}
		},this);
		
		this.assignedServiceGridPnl.assocSvcDataStore.on('remove', function(store,record,index){
			
				if(!Ext.isEmpty(record.data.serviceOrderNumber)){
					var assignedBm = this.convertRecordToBm(record.data)
					if(!Ext.isEmpty(assignedBm.service)){
						this.deletedAssignedServiceList.push(assignedBm);
					}else{
						this.deletedAssignedServicePAckageList.push(assignedBm);
					}
				}else{
					if(record.data.packageInd == configs.packageIndicator){
						if(this.newAssigendServicePackageList.length>0){
							for(var j=0; j<this.newAssigendServicePackageList.length; j++){
								var listItem = this.newAssigendServicePackageList[j];
								if(listItem.servicePackage.code == record.data.servicePackageCode &&
									listItem.requestedUnit == record.data.noOfUnits){
										this.newAssigendServicePackageList.removeItem(j);
										return;
								}
							}
						 }	
					}else{
						if(this.newAssignedServiceList.length>0){
							for(var k=0; k<this.newAssignedServiceList.length; k++){
								var listItem = this.newAssignedServiceList[k];
								if(listItem.service.code == record.data.servicePackageCode &&
									listItem.requestedUnits == record.data.noOfUnits && 
									listItem.serviceDate == record.data.serviceDate){
										this.newAssignedServiceList.removeItem(k);
										return;
								}
							}
						}
					}	
				}
		},this);
		
	},
	getPanel : function(){
		return this.assignedServicePnl;
	},
	addDetailsToGrid : function() {
		
		var recType = this.assignedServiceGridPnl.getPanel().getStore().recordType; 
		var data = this.assignedServiceDetailsPnl.getData();
		// this if condition written because the getData method returns undefined at the 
		//time of service or package not been selected. 
		if( Ext.isEmpty( data ) ){
			return;
		}
		var chargeIntoUnits = parseInt(data.noOfUnits,10)* data.serviceCharge;
		var serviceRecord = new recType({
			serviceCode: data.packageInd == configs.packageIndicator?data.servicePackageRec.code:data.serviceRec.code,
			serviceName: data.packageInd == configs.packageIndicator?data.servicePackageRec.description:data.serviceRec.description,
			serviceCharge: data.serviceCharge,
			servicePackageCode: data.packageInd == configs.packageIndicator?data.servicePackageRec.code:'',
			servicePackageDecs: data.packageInd == configs.packageIndicator?data.servicePackageRec.description:'',
			noOfUnits: data.noOfUnits,
			chargeIntoUnits: chargeIntoUnits,
			packageInd: data.packageInd,
			serviceDate:data.packageInd != configs.packageIndicator?data.serviceDate:'',
			serviceOrderNumber : '',
			hideMarkBillable : true,
			hideMarkUnbillable : true,
			hideCancel : true,
			isBillable : data.isBillable || 'Y' //Default is 'Y'
		});
		this.assignedServiceGridPnl.getPanel().getStore().add(serviceRecord);
		this.assignedServiceDetailsPnl.resetData({isFromAddBtn : true});
		
	},
	editEntryInGrid : function(){
		var selectedRecord = this.assignedServiceGridPnl.getSelectedRow();
		var data ={
			packageInd: selectedRecord[0].data.packageInd,
			noOfUnits: selectedRecord[0].data.noOfUnits,
			serviceCode: selectedRecord[0].data.serviceCode,
			servicePackageCode: selectedRecord[0].data.servicePackageCode,
			serviceDate:selectedRecord[0].data.serviceDate
		};
		this.assignedServiceDetailsPnl.loadData(data);
		this.assignedServiceGridPnl.deleteHandler();
	},
	convertRecordToBm : function(selectedRow){
		
		if(selectedRow.packageInd == configs.packageIndicator){
			var assignedServicePackageBm ={
				servicePackage:{code:selectedRow.servicePackageCode,description:selectedRow.servicePackageDecs},
				requestedUnit:parseInt(selectedRow.noOfUnits,10)
			};
			if( !Ext.isEmpty(selectedRow.id)){
				assignedServicePackageBm.packageAsgmId=parseInt(selectedRow.id,10);
			}
			return assignedServicePackageBm;
		}else{
			var assignedServiceBm = {
				service:{code:selectedRow.serviceCode,description:selectedRow.serviceName},
				requestedUnits:parseInt(selectedRow.noOfUnits,10),
				serviceDate:selectedRow.serviceDate
			};
			if( !Ext.isEmpty(selectedRow.id)){
				assignedServiceBm.serviceUid=parseInt(selectedRow.id,10);
			}
			return assignedServiceBm;
		}
	}
});