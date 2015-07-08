Ext.namespace("LIMS.specimenCollectionPoint"); 

LIMS.specimenCollectionPoint.ConfigureCollectionPoint = Ext.extend(Ext.Panel,{
	initComponent : function(){
	
		// This will be initialized as true when this will be openend in edit case.
		// When selects any collection point from the collection point list and 
		// press edit button then it will be open in edit case
		this.isEditCollecionPoint = false;
		this.isFromSelectListener = false;
		
		// This panel contains all the widgets that is require to configure collection point
		// like collection point id, name , area covered and collection point's contact details.
	
		this.collectionPointDetailPanel = new LIMS.specimenCollectionPoint.CollectionPointDetailPanel();
		
		
		
		this.collectionPointDetailPanel.getCollectionpointWidgets().getCountryCbx().on('select',function( thisCombo, record , index ){
			this.isFromSelectListener = true;
			this.loadStateStoreForSelectedCountry( record.data.code );
		},this);
		
		// This grid will contains list of all the lab (ID and NAME)
		
		this.labListGrid = new LIMS.specimenCollectionPoint.CollectionPointLabAssociationGrid();
		this.labListGrid.setTitle( limsMsg.labList );
		this.labListGrid.loadStore();

		// This grid contains lab list asscoiated with this collection point .
		
 		this.associatedLabGrid = new LIMS.specimenCollectionPoint.CollectionPointLabAssociationGrid();
		this.associatedLabGrid.setTitle( limsMsg.associatedLab );
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.rightBtn = new Ext.Button({
			iconCls : 'right_arrow_icon',
			disabled : true
		});

		this.leftBtn = new Ext.Button({
			iconCls : 'left_arrow_icon',
			disabled : true
		});

		// This button is resposible for transferring selected left grid( Lab list grid) data 
		// to the right grid( Associated lab grid)

		this.rightBtn.on('click',function(thisBtn){
			this.rightBtnClicked()
		},this);

		// This button is resposible for transferring selected right grid( Associated lab grid) data 
		// to the left grid( Lab list grid)
		
		this.leftBtn.on('click',function(thisBtn){
			this.leftBtnClicked();
		},this);
		
		this.buttonsPanel.saveBtn.on('click',function( thisBtn ){
			this.saveBtnClicked();
		},this);
		
		this.buttonsPanel.resetBtn.on('click',function(thisBtn){
			this.resetBtnClicked();
		},this);
		
		this.collectionPointDetailPanel.on('clientvalidation',function(thisField , isValid){
			if( isValid ){
				this.buttonsPanel.saveBtn.enable();
			}
			else{
				this.buttonsPanel.saveBtn.disable();
			}
		},this);
		
		// This will be disable right button this button is exist in between two grid.
		// This button is resposible for transferring selected left grid( Lab list grid) data 
		// to the right grid( Associated lab grid)
		
		this.labListGrid.gridChk.on('rowselect',function(selectionModel, rowIndex , record){
			this.rightBtn.disable();
			this.changeBtnStatus(this.rightBtn, selectionModel.getSelections().length);
		},this);

		this.labListGrid.gridChk.on('rowdeselect',function(selectionModel, rowIndex , record){
			this.rightBtn.disable();
			this.changeBtnStatus(this.rightBtn, selectionModel.getSelections().length);
		},this);

		// This will be disable left button this button is exist in between two grid.
		// This button is resposible for transferring selected right grid( Associated lab grid) data 
		// to the left grid( Lab list grid)

		
		this.associatedLabGrid.gridChk.on('rowselect',function(selectionModel, rowIndex , record){
			this.leftBtn.disable();
			this.changeBtnStatus(this.leftBtn, selectionModel.getSelections().length);
		},this);

		this.associatedLabGrid.gridChk.on('rowdeselect',function(selectionModel, rowIndex , record){
			this.leftBtn.disable();
			this.changeBtnStatus(this.leftBtn, selectionModel.getSelections().length);
		},this);
		
		
		Ext.applyIf(this,{
			layout : 'column',
			items : [
			    {
			    	columnWidth : 1,
			    	items : this.collectionPointDetailPanel
			    },
			    {
			    	columnWidth : .47,
			    	items : this.labListGrid
			    },     
			    {
			    	columnWidth : .06,
			    	layout : 'form',
			    	items : [
		    	         {
		    	        	 height : 80,
		    	        	 frame : false,
		    	        	 border : false
		    	         }, 
		    	         this.rightBtn,
		    	         this.leftBtn
	    	         ]
			    },
			    {
			    	columnWidth : .47,
			    	items : this.associatedLabGrid
			    }, 
			    {
			    	columnWidth : 1,
			    	items : this.buttonsPanel
			    }    
			]
		});
		
		LIMS.specimenCollectionPoint.ConfigureCollectionPoint.superclass.initComponent.apply(this,arguments);
	},
	
	saveBtnClicked : function(){
		var mainThis = this;
		var collectionpointDetails = this.collectionPointDetailPanel.getForm().getValues();
		
		var contactDetails = {
			personName : 		collectionpointDetails['contactPerson'],
			street :  			collectionpointDetails['street'],
			locality :  		collectionpointDetails['locality'],
			city : 	 			collectionpointDetails['city'],
			phoneNumber :  		collectionpointDetails['phoneNumber'],
			mobileNumber :  	collectionpointDetails['mobileNumber'],
			faxNumber :  		collectionpointDetails['faxNumber'],
			email :  			collectionpointDetails['emailId'],
			country :  			{code : collectionpointDetails['country'] },
			state :  			{code : collectionpointDetails['state'] },
			createdBy :  		getAuthorizedUserInfo().userName
				
		};
		
		var associatedLabList = new Array();
		
		var gridDataList = this.associatedLabGrid.gridPnl.store.getRange();
		
		for( var i = 0 ; i < gridDataList.length ; i++){
			var associatedLab = {
				code : gridDataList[i].data.labId,
				description : gridDataList[i].data.labName
			};	
			associatedLabList.push( associatedLab );
		
		}
		
		var specimenCollectionPointBM = {
			collectionPointId : collectionpointDetails['collectionPointId'],
			name : collectionpointDetails['collectionPointName'],
			areaCovered : collectionpointDetails['areaCovered'],
			contactDetails : contactDetails,
			createdBy : getAuthorizedUserInfo().userName,
			associatedLabList :	associatedLabList
		};
		
		if( this.isEditCollecionPoint ){
			CollectionPointManager.modifyCollectionPoint( specimenCollectionPointBM ,function(){
				layout.getCenterRegionTabPanel().remove( mainThis );
	   			Ext.ux.event.Broadcast.publish('closeEditCollectionPoint');
	   			
			});
		}
		else{
			CollectionPointManager.addCollectionPoint( specimenCollectionPointBM ,function(){
				layout.getCenterRegionTabPanel().remove( mainThis );
	   			Ext.ux.event.Broadcast.publish('closeAddCollectionPoint');

			});
		}		
			
		
		
	},
	
	resetBtnClicked : function(){
		this.leftBtn.disable();
		this.rightBtn.disable();
		this.collectionPointDetailPanel.reset();
		this.labListGrid.removeData();
		this.labListGrid.loadStore();
		this.associatedLabGrid.removeData();
		if( !Ext.isEmpty( this.collectionPointBM ) && this.isEditCollecionPoint ){
			this.setValues(this.collectionPointBM );
		}	
	},
	
	rightBtnClicked : function(){
		var selectedRecords = this.labListGrid.getData();
		this.associatedLabGrid.setData( selectedRecords );
		this.labListGrid.removeData( selectedRecords );
		this.rightBtn.disable();
	},
	
	leftBtnClicked : function(){
		var selectedRecords = this.associatedLabGrid.getData();
		this.labListGrid.setData( selectedRecords );
		this.associatedLabGrid.removeData( selectedRecords );
		this.leftBtn.disable();
		
	},
	
	loadStateStoreForSelectedCountry : function( countryCode ){
		this.collectionPointDetailPanel.getCollectionpointWidgets().getStateCbx().getStore().load({params:{start:0, limit:10}, arg:[ countryCode ]});
		this.collectionPointDetailPanel.getCollectionpointWidgets().getStateCbx().clearValue();
	},
	
	changeBtnStatus : function( thisBtn , selectedRowCount ){
		if( selectedRowCount > 0 ){
			thisBtn.enable();
		}
		else{
			thisBtn.disable();
		}
	},
	
	setValues : function( collectionPointBM ){
		
		this.collectionPointBM = collectionPointBM;
		
		if( !Ext.isEmpty( collectionPointBM )){
		
			var mainThis = this;
			var country = null; 
			var state = null;
			
			if( !Ext.isEmpty( this.collectionPointBM.associatedLabList )){
				this.labListGrid.filterAvailableTestResults( this.collectionPointBM.associatedLabList );
				this.associatedLabGrid.setDataForAssociatedGrid( this.collectionPointBM.associatedLabList )
			}
			if( !Ext.isEmpty( this.collectionPointBM.contactDetails.country )){

				country = this.collectionPointBM.contactDetails.country.code;
				this.loadStateStoreForSelectedCountry( country );
				if( !Ext.isEmpty( this.collectionPointBM.contactDetails.state )){
					state = this.collectionPointBM.contactDetails.state.code;
					this.collectionPointDetailPanel.getCollectionpointWidgets().getStateCbx().getStore().on('load',function(){
						if( !this.isFromSelectListener ){
							this.collectionPointDetailPanel.getCollectionpointWidgets().getStateCbx().setValue( state );
						}
					},this);
				}
			}	
			formValues = {
				collectionPointId   :this.collectionPointBM.collectionPointId,
				collectionPointName :this.collectionPointBM.name,
				areaCovered 		:this.collectionPointBM.areaCovered,
				contactPerson 		:this.collectionPointBM.contactDetails.personName,
				street 				:this.collectionPointBM.contactDetails.street,
				locality 			:this.collectionPointBM.contactDetails.locality,
				city 				:this.collectionPointBM.contactDetails.city,
				phoneNumber 		:this.collectionPointBM.contactDetails.phoneNumber,
				mobileNumber 		:this.collectionPointBM.contactDetails.mobileNumber,
				faxNumber 			:this.collectionPointBM.contactDetails.faxNumber,
				emailId 			:this.collectionPointBM.contactDetails.email,
				country 			:country 
			};
			
			this.collectionPointDetailPanel.getForm().setValues( formValues );
			this.collectionPointDetailPanel.getCollectionpointWidgets().getCollectionPointIdTxf().readOnly = true;
		}
	}
	
});