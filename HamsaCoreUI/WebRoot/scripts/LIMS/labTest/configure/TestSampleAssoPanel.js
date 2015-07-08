Ext.namespace("LIMS.labTest.configure");

LIMS.labTest.configure.TestSampleAssoPanel = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
 	initComponent : function() {
		this.recordStatus = null;
		this.seqNbr = null;
		
		this.widgets = new LIMS.labTest.Widgets();

		this.buttonPanel = new Ext.Panel({
			buttonsAlign : 'right',
			buttons : this.widgets.getAddBtn()
		});
		
		
		this.table = new LIMS.labTest.configure.TestSampleGridPanel();
		
		this.table.getToolBar().getEditBtn().on('click', function(){
			this.editHandler();
		},this);
		
		this.table.getToolBar().getDeleteBtn().on('click', function(){
			this.deleteHandler();
		},this);
		
		this.table.getGrid().on('cellclick', function(  gridPanel , rowIndex, columnIndex,e ){
			this.table.setGridButtonsState( gridPanel.getSelectionModel() );
		},this);
		
		

		Ext.applyIf(this, {
			defaults :{labelWidth : 80 },
            items: [
	            {
					columnWidth:0.33,
	            	layout : 'form',
	            	labelWidth : 55,
	            	items:[this.widgets.getSpecimenCombo()]
			    },
			    {
					columnWidth:0.33,
	            	layout : 'form',
	            	labelWidth : 60,
					items:[this.widgets.getSpecimenQttyTxf()]
			    },
			    {
					columnWidth:0.34,
	            	layout : 'form',
	            	labelWidth : 50,
					items:[this.widgets.getSpecimenUnitCombo()]
				},
				{
					columnWidth:0.5,
	            	layout : 'form',
	            	labelWidth:.001,
					items:[this.widgets.getIsMandatoryChk()]
				},
				{
					columnWidth:.5,
	            	layout : 'form',
	            	buttonAlign :'right',
//	            	labelWidth:.001,
					items:[this.buttonPanel]
			    },
			    {
					columnWidth:1,
	            	layout : 'form',
					items:[this.table.getGrid()]
			    }
					
			]            
        });
        
		this.widgets.getAddBtn().on('click', function(){
			
			var tmpThis = this;
			//check whether values are in the field or not
			
			var emptyFieldMsg = '';
			
			if(Ext.isEmpty(tmpThis.widgets.getSpecimenCombo().getValue())){
				
				emptyFieldMsg = tmpThis.widgets.getSpecimenCombo().fieldLabel + ", ";
			}
			
			if( tmpThis.isDataAlredyExist( tmpThis.widgets.getSpecimenCombo().getValue() ) ){
				Ext.Msg.show({
					msg : limsMsg.msgSpecimenAlreadyExist,
					buttons : Ext.Msg.OK,
					fn : function(){
						tmpThis.widgets.getSpecimenCombo().clearValue();
						tmpThis.widgets.getSpecimenQttyTxf().setValue(''); 
						tmpThis.widgets.getSpecimenUnitCombo().clearValue();
					},
					scope : this
				});
				
				return ;
			}

			
			if(Ext.isEmpty(tmpThis.widgets.getSpecimenQttyTxf().getValue())){
				
				emptyFieldMsg = emptyFieldMsg + tmpThis.widgets.getSpecimenQttyTxf().fieldLabel + ", ";
			}

			if(Ext.isEmpty(tmpThis.widgets.getSpecimenUnitCombo().getValue())){
				
				emptyFieldMsg = emptyFieldMsg +  tmpThis.widgets.getSpecimenUnitCombo().fieldLabel + ", ";
			}

			if( emptyFieldMsg != '' ){
				warning(" Please provide " + emptyFieldMsg);
				
				return;
			}
				
			
				
				var record = this.table.getGrid().getStore().recordType;
				
				var details = new record({
					specimenId : tmpThis.widgets.getSpecimenCombo().getValue(),
					specimenName : tmpThis.widgets.getSpecimenCombo().getRawValue(),
					unit: tmpThis.widgets.getSpecimenUnitCombo().getRawValue(),
					quantity:tmpThis.widgets.getSpecimenQttyTxf().getValue(),
					isMandatory:tmpThis.widgets.getIsMandatoryChk().getValue()?"Y":"N"
				});
				this.table.getGrid().getStore().add( details );
				
				tmpThis.widgets.getSpecimenCombo().clearValue();
				tmpThis.widgets.getSpecimenQttyTxf().setValue(''); 
				tmpThis.widgets.getSpecimenUnitCombo().clearValue();

		},this);
		
        this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.widgets.getAddBtn().enable();
			} else {
				this.widgets.getAddBtn().disable();
				this.recordStatus = null;
				this.seqNbr = null;
			}
		}, this); 
		
        LIMS.labTest.configure.TestSampleAssoPanel.superclass.initComponent.apply(this, arguments);

	},
	
	
	loadData : function( config ){
		this.config = config;
		this.getForm().setValues(config);
		this.loadGrid( config.detailsList );
	},
	
	editHandler: function(){
		
		var selectedRow = this.table.getGrid().getSelectionModel().getSelections();
		this.widgets.getSpecimenCombo().setValue(selectedRow[0].data.specimenId);
		this.widgets.getSpecimenQttyTxf().setValue(selectedRow[0].data.quantity);
		this.widgets.getSpecimenUnitCombo().setValue(selectedRow[0].data.unit);
		this.widgets.getIsMandatoryChk().setValue(selectedRow[0].data.isMandatory == "Y"?true:false);
		
		this.table.getGrid().getStore().remove( selectedRow[0] );
		this.table.setGridButtonsInitialState();
	},
	
	deleteHandler: function(){
		var selectedRows = this.table.getGrid().getSelectionModel().getSelections();
		var record = this.table.getGrid().getStore().recordType;
		
		for( var i =0; i<selectedRows.length; i++ ){
				this.table.getGrid().getStore().remove( selectedRows[i] );
			
		}
		this.table.setGridButtonsInitialState();
	},
	resetGrid: function(){
		var rows = this.table.getGrid().getStore().getRange();
		for( var i =0; i<rows.length; i++ ){
			this.table.getGrid().getStore().remove( rows[i] );
		}
	},
	loadGrid: function( list ){
		if( !Ext.isEmpty( list )){
			var record = this.table.getGrid().getStore().recordType;
			for( var i = 0; i<list.length; i++ ){
				var data = new record({
					seqNbr:list[i].seqNbr,
					age : list[i].age,
					deletedFlag : list[i].deletedFlag,
					dosage : list[i].dosage,
					periodInDays : list[i].periodInDays,
					scheduleName : list[i].scheduleName,
					userName : list[i].userName,
					vaccinationCode : list[i].vaccinationName.code,
					vaccinationName : list[i].vaccinationName.description,
					vaccinationTypeCode : !Ext.isEmpty(list[i].vaccinationType) ? 
										  list[i].vaccinationType.code : null,
					vaccinationType : !Ext.isEmpty(list[i].vaccinationType) ? 
								       list[i].vaccinationType.description : null,
					periodInd : 'Days'
				});
				this.table.getGrid().getStore().add( data );
			}
		}
	},
	
	isDataAlredyExist : function( data ){
		var gridData = this.table.getGrid().getStore().data.items;
		for( var i=0 ; i < gridData.length ; i++){
			if( gridData[i].data.specimenId === data){
				return true;
			}
		}
		
		return false;
	}

	
});
