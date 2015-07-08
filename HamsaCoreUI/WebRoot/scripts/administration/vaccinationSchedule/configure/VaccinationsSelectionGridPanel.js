Ext.namespace("administration.vaccinationSchedule.configure");

administration.vaccinationSchedule.configure.VaccinationsSelectionGridPanel = Ext.extend(Ext.Panel, {
   	  title : 'Selected vaccinations',
      layout : 'fit',
      border : false,
      frame : false,
      initComponent : function() {
		
      	var tempThis = this;
      	
	 	this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					this.toolBar.getEditBtn().disable();
					this.toolBar.getDeleteBtn().enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.toolBar.getEditBtn().disable();
					this.toolBar.getDeleteBtn().disable();
				},
				scope:this
			},
			renderer : function(v, p, record){
		        if ( record.data.deletedFlag || record.data.recordStatus == "delete" ){
		            return '<div> </div>';
		        }else{
		            return '<div class="x-grid3-row-checker"> </div>';
		        }
		    }

		});
		
	 	this.vaccinationDetailsRecord = Ext.data.Record.create([
	 		{ name: 'seqNbr'},
	 		{ name: 'recordStatus'},
			{ name: 'periodInDays',type:'int'},
			{ name: 'age',type:'string'},
			{ name: 'periodInd',type:'string'},
	        { name: 'vaccinationCode', type:'string'},
	        { name: 'vaccinationName', type:'string'},
	        { name: 'dosage', type:'string'},
	        { name: 'vaccinationTypeCode', type:'string'},
	        { name: 'vaccinationType', type:'string'},
	        { name: 'deletedFlag', type:'bool'}
       	]);
       	
       	this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.vaccinationDetailsRecord )
		});
		
        this.gridColumns = new Ext.grid.ColumnModel([
        	this.gridChk,
         	{
	           header :"Vaccination name",
	           dataIndex :'vaccinationName',
	           width : 200,
	           renderer: function(value, metadata, record, rowIndex, colIndex, store){
	           		if( record.data.deletedFlag || record.data.recordStatus == "delete" ){
	           			metadata.attr = 'style="background-color:red;"';
						return value;
	           		}else{
	           			return value;
	           		}
				}
	        },{
	           header :"Vaccination type",
	           dataIndex :'vaccinationType',
	           width : 200,
	           renderer: function(value, metadata, record, rowIndex, colIndex, store){
	           		if( record.data.deletedFlag || record.data.recordStatus == "delete" ){
	           			metadata.attr = 'style="background-color:red;"';
						return value;
	           		}else{
	           			return value;
	           		}
				}
	        },{
	           header :"Age",
	           dataIndex :'age',
	           width : 200,
	           renderer: function(value, metadata, record, rowIndex, colIndex, store){
	           		if( record.data.deletedFlag || record.data.recordStatus == "delete" ){
	           			metadata.attr = 'style="background-color:red;"';
						return value;
	           		}else{
	           			return value;
	           		}
				}
	        },{
	           header :"Dosage",
	           dataIndex :'dosage',
	           width : 200,
	           renderer: function(value, metadata, record, rowIndex, colIndex, store){
	           		if( record.data.deletedFlag || record.data.recordStatus == "delete" ){
	           			metadata.attr = 'style="background-color:red;"';
						return value;
	           		}else{
	           			return value;
	           		}
				}
	        },{
               header :"Period (in days)",
               dataIndex :'periodInDays',
               width : 200,
	           renderer: function(value, metadata, record, rowIndex, colIndex, store){
	           		if( record.data.deletedFlag || record.data.recordStatus == "delete" ){
	           			metadata.attr = 'style="background-color:red;"';
						return value;
	           		}else{
	           			return value;
	           		}
				}
	        }
	  ]);
            
	  this.toolBar = new utils.GridToolBar();
	  this.toolBar.getAddBtn().hide();
	 
      this.gridPnl = new Ext.grid.GridPanel({
		  border : false,
		  height : 300,
		  width:'100%',
		  frame : true,
          stripeRows :true,
          autoScroll :true,
          remoteSort :true,
          store : this.dataStore,
          stripeRows: false,
		  tbar : this.toolBar,    
          viewConfig : { forceFit :true},
          cm : this.gridColumns,
          sm: this.gridChk,
          listeners : {
			  rowclick : function (grid, rowIndex, e) {
			      var record = grid.getStore().getAt(rowIndex);
			      if ( record.data.deletedFlag  || record.data.recordStatus == "delete" ){
			          grid.getSelectionModel().deselectRow(rowIndex);
			      }
			  }
		  }
      });
	  
//      this.initialListeners();
      
      Ext.applyIf(this, {items: [{ columnWidth : 1, items :[ this.gridPnl ]}]});

        administration.vaccinationSchedule.configure.VaccinationsSelectionGridPanel.superclass.initComponent.apply(this, arguments);
     },
      
     getGrid : function(){
     	return this.gridPnl;
     },
     getToolBar: function(){
     	return this.toolBar;
     },
     getData : function(){
		var tmpList = new Array();
		var storeValues = this.gridPnl.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var values = storeValues[i].data;
			var vaccinationScheduleDetailBM ={
				seqNbr:values.seqNbr,
				recordStatus:values.recordStatus,
				periodInDays:values.periodInDays,
				vaccinationName:{ code:values.vaccinationCode},
				vaccinationType:{ code:values.vaccinationTypeCode},
				age:values.age,
				dosage:values.dosage,
				deletedFlag: Ext.isEmpty(values.deletedFlag) ? false : true,
				userName: getAuthorizedUserInfo().userName
			};
			tmpList.push( vaccinationScheduleDetailBM );
		}
		return tmpList;
     },
     deleteRow: function(){
     	var selections =  this.getSelectionModel().getSelections();
     	if( selections.length >0 ){
     		for( var i = 0; i<selections.length; i++ ){
     			this.getStore().remove( selections[i] );
     		}
     	}
     },
     loadGridData: function( list ){
		if( !Ext.isEmpty( list )){
			var record = this.getGrid().getStore().recordType;
			for( var i = 0; i<list.length; i++ ){
				var data = new record({
					age : list[i].age,
					deletedFlag : list[i].deletedFlag,
					dosage : list[i].dosage,
					periodInDays : list[i].periodInDays,
					scheduleName : list[i].scheduleName,
					userName : list[i].userName,
					vaccinationCode : list[i].vaccinationName.code,
					vaccinationName : list[i].vaccinationName.description,
					vaccinationTypeCode : !Ext.isEmpty( list[i].vaccinationType)? 
											list[i].vaccinationType.code : null,
					vaccinationType : !Ext.isEmpty( list[i].vaccinationType)?
											list[i].vaccinationType.description: null
				});
				this.getGrid().getStore().add( data );
			}
		}
     },
     
     initialListeners : function(){
		this.addedVaccinationScheduleDetailsList = new Array();
		this.deletedVaccinationScheduleDetailsList = new Array();
		
		this.gridPnl.getStore().on('add', function( store, records, index ){
			for(var i = 0; i<records.length; i++){
				if(Ext.isEmpty(records[i].data.seqNbr)){
					var vaccinationScheduleDetailBM = this.convertRecordToBm(records[i].data);
					this.addedVaccinationScheduleDetailsList.push( vaccinationScheduleDetailBM );
				}
			}
		},this);
		
		this.gridPnl.getStore().on('remove', function( store, record, index ){
				if( !Ext.isEmpty( record ) && !Ext.isEmpty( record.data.seqNbr ) ){
					var vaccinationScheduleDetailBM = this.convertRecordToBm(record.data);
					this.deletedVaccinationScheduleDetailsList.push( vaccinationScheduleDetailBM );
				}else{
					if( this.addedVaccinationScheduleDetailsList.length > 0 ){
						for( var k = 0; k < this.addedVaccinationScheduleDetailsList.length; k++ ){
							var listItem = this.addedVaccinationScheduleDetailsList[k];
							if(listItem.periodInDays == record.data.periodInDays &&
								listItem.vaccinationName.code == record.data.vaccinationCode ){
									this.addedVaccinationScheduleDetailsList.removeItem(k);
									return;
							}
						}
					}
				}
		},this);
	},
	
	convertRecordToBm : function( record ){

		var vaccinationScheduleDetailBM ={
				periodInDays:record.periodInDays,
				vaccinationName:{ code:record.vaccinationCode},
				vaccinationType:{ code:record.vaccinationTypeCode},
				age:record.age,
				dosage:record.dosage,
				deletedFlag: Ext.isEmpty(record.deletedFlag) ? false : true,
				userName: getAuthorizedUserInfo().userName
		};
		
		if( !Ext.isEmpty( record.seqNbr )){
			vaccinationScheduleDetailBM.seqNbr = record.seqNbr; 
		}	
		return vaccinationScheduleDetailBM;	
	}
});

Ext.reg('vaccination-selections-grid-panel', administration.vaccinationSchedule.configure.VaccinationsSelectionGridPanel);
