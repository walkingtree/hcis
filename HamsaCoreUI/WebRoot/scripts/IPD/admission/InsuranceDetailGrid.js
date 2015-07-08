Ext.namespace("IPD.admission");

IPD.admission.InsuranceDetailGrid = Ext.extend(Object, {

	constructor : function(config) {

		if(Ext.isEmpty(config)){
			config = {};
		}

		this.claimDetailRecord = Ext.data.Record.create([
			{ name: 'seqNbr', mapping:'seqNbr'},
			{ name: 'requestSequenceNbr', mapping:'requestSequenceNbr'},//type:long
			{ name: 'admissionReqNbr', mapping:'admissionReqNbr'},//type:Integer//Not Null
			{ name: 'claimSubsequenceNbr', mapping:'claimSubsequenceNbr'},//Integer//Not Null
			{ name: 'sponsorName', mapping:'sponsorName'},//Not Null
			{ name: 'sponsorDesc', mapping:'sponsorDesc'},
			{ name: 'insurerName', mapping: 'insurerName'},
			{ name: 'insurerDesc', mapping: 'insurerDesc'},
			{ name: 'planCode', mapping:'planCode'},//Not Null
			{ name: 'planName', mapping:'planName'},
			{ name: 'policyNbr', mapping:'policyNbr'},
			{ name: 'claimStatusCd', mapping:'claimStatusCd', convert:getCode},
			{ name: 'claimStatusCdDesc', mapping:'claimStatusCd', convert:getDescription},
			{ name: 'policyEffectiveFromDt', mapping:'policyEffectiveFromDt'},//Not Null
			{ name: 'policyEffectiveToDt', mapping:'policyEffectiveToDt'},
			{ name: 'policyHolderName', mapping:'policyHolderName'},
			{ name: 'preferenceSequenceNbr', mapping:'preferenceSequenceNbr'},//Integer
			{ name: 'createdBy', mapping:'createdBy'},
			{ name: 'createDtm', mapping:'createDtm'},
			{ name: 'requestDtm', mapping:'requestDtm'},
			{ name: 'requestedAmount', mapping:'requestedAmount'},//double
			{ name: 'patientAmount', mapping:'patientAmount'},//double
			{ name: 'approvalThrough', mapping:'approvalThrough'},
			{ name: 'approvedAmount', mapping:'approvedAmount'},//double
			{ name: 'approvalDate', mapping:'approvalDate'},
			{ name: 'finalClaimedAmount', mapping:'finalClaimedAmount'},//double
			{ name: 'billNbr', mapping:'billNbr'},//long
			{ name: 'lastModifiedDtm', mapping:'lastModifiedDtm'},
			{ name: 'modifiedBy', mapping:'modifiedBy'},
			{ name: 'claimDocumentBMList'},
			{ name: 'insurerBM'},
			{ name: 'patientId'},
			{ name: 'patientName'},
			{ name: 'estimationGivenBy'},
			{ name: 'estimatedCost'},//double
			{ name: 'insuranceAmount'}
        ]);
		
		this.dataStore = new Ext.data.Store({
			reader: new Ext.data.ListRangeReader({id: 'id', totalProperty:'totalSize'}, this.claimDetailRecord),
        	proxy: new Ext.data.DWRProxy(InsuranceManager.findClaimRequests, true),
        	sortInfo: {field: 'requestSequenceNbr', direction: "ASC"},
        	remoteSort: true
		});
		
		var gridColumns = [
			{ header : 'S.No.', dataIndex : 'seqNbr', width : 30, hidden:true, sortable: true }, 
			{ header : 'Claim number', dataIndex : 'requestSequenceNbr', width :60, sortable: true}, 
			{ header : 'Status', dataIndex : 'claimStatusCdDesc', width :60, sortable: true }, 
			{ header : 'Sponsor', dataIndex : 'sponsorName', width : 60, sortable: true }, 
			{ header : 'Insurer',  dataIndex : 'insurerName', width : 60, sortable: true },
			{ header : 'Plan Name', dataIndex : 'planName', width : 60, sortable: true }, 
			{ header : 'Policy No#', dataIndex : 'policyNbr', width : 60, sortable: true }, 
			{ header : 'Requested on', dataIndex : 'requestDtm', width : 60, sortable: true, renderer: Ext.util.Format.dateRenderer('d/m/Y') }, 
			{ header : 'Approved on', dataIndex : 'approvalDate', width : 60, sortable: true, renderer: Ext.util.Format.dateRenderer('d/m/Y') }, 
			{ header : 'Requested amount (Rs.)', dataIndex : 'requestedAmount' , width : 60, align:'right' }, 
			{ header : 'Approved amount (Rs.)', dataIndex : 'approvedAmount', width : 60, align:'right' },
			{ header : 'Claimed amount (Rs.)', dataIndex : 'finalClaimedAmount', width : 60, align:'right' },
			{ header : 'Patient amount (Rs.)', dataIndex : 'patientAmount', width : 60, align:'right' }
		];

		this.infoGrid = new Ext.grid.GridPanel({
			stripeRows : true,
			width : '100%',
			height : 300,
			autoScroll:true,
			viewConfig :{
				forceFit : true
			},
			store : this.dataStore,
			columns : gridColumns
		});
	},
	
	getPanel : function() {
		return this.infoGrid;
	}
});