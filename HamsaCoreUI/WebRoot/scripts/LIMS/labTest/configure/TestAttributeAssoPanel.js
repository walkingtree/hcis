Ext.namespace("LIMS.labTest.configure");

LIMS.labTest.configure.TestAttributeAssoPanel = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	monitorValid : true,
	
 	initComponent : function() {
		this.recordStatus = false;
		this.seqNbr = null;
		this.isAttributeDeleted = null;//Any attribute is deleted?
		this.widgets = new LIMS.labTest.Widgets();

		this.table = new LIMS.labTest.configure.TestAttributeGridPanel();
		
		this.buttonPanel = new Ext.Panel( {
							buttonsAlign : 'right',
							buttons : this.widgets.getAddBtn()
						});
		
		this.table.getToolBar().getEditBtn().on('click', function(){
			this.editHandler();
		},this);
		
		//Give a warning msg if it test is on edit mode
		//Make sure warnig will be given for one time only

		this.table.getToolBar().getDeleteBtn().on('click', function(){
			
			if(this.initialConfig.mode==limsMsg.editMode && !this.isAttributeDeleted){
				
				var mainThis = this;
				Ext.Msg.show({
					title:'Warning',
					msg: 'The template for this test will be reset if you delete attribute(s)<br/> Do you want to continue?',
					buttons: Ext.Msg.YESNO,
					fn: function(btn, text){
					if(btn == 'yes'){
						mainThis.deleteHandler();
					}
				},
				animEl: 'elId',
				icon: Ext.MessageBox.WARNING
				});
				
			}
			else{
				 this.deleteHandler();
			 }
			
		},this);
		
		this.table.getGrid().on('cellclick', function(  gridPanel , rowIndex, columnIndex,e ){
			this.table.setGridButtonsState( gridPanel.getSelectionModel() );
		},this);
		
		this.widgets.getAddBtn().on('click',function(){
					this.addBtnClicked();
			},this);

		Ext.applyIf(this, {
            items: [
	            {
					columnWidth:0.4,
	            	layout : 'form',
	            	items:[this.widgets.getTestAttributeCbx()]
			    },{
					columnWidth:0.4,
	            	layout : 'form',
	            	labelWidth:.001,
					items:[this.widgets.getIsMandatoryChk()]
				},{
					columnWidth:0.2,
	            	layout : 'form',
	            	labelAlign : 'right',
//	            	labelWidth:.001,
					items:[this.buttonPanel]
			    }
				,{
					columnWidth:1,
	            	layout : 'form',
					items:[this.table.getGrid()]
			    }
					
			]            
        });
        
		
        this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.widgets.getAddBtn().enable();
			} else {
				this.widgets.getAddBtn().disable();
				this.recordStatus = null;
				this.seqNbr = null;
			}
		}, this); 
		
        LIMS.labTest.configure.TestAttributeAssoPanel.superclass.initComponent.apply(this, arguments);

	},
	
	
	loadData : function( config ){
		this.config = config;
		this.getForm().setValues(config);
		this.loadGrid( config.detailsList );
	},
	

	editHandler: function(){
		
		var selectedRow = this.table.getGrid().getSelectionModel().getSelections();
		this.widgets.getTestAttributeCbx().setValue(selectedRow[0].data.labTestAttributeId);
		this.widgets.getIsMandatoryChk().setValue(selectedRow[0].data.isMandatory);
		
		this.table.getGrid().getStore().remove( selectedRow[0] );
		this.table.setGridButtonsInitialState();
	},
	
	deleteHandler: function(){
		var selectedRows = this.table.getGrid().getSelectionModel().getSelections();
		var record = this.table.getGrid().getStore().recordType;
		
		this.isAttributeDeleted = true;
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
	
	addBtnClicked : function(){
		
		if( Ext.isEmpty(this.widgets.getTestAttributeCbx().getValue())){
			
			warning(limsMsg.msgSelectAttribute);

			return;
		}
		//Call back-end to get details and insert it to the grid
		
		if( this.isDataAlredyExist( this.widgets.getTestAttributeCbx().getValue() ) ){
			Ext.Msg.show({
				msg : limsMsg.msgAttributeAlreadyExist,
				buttons : Ext.Msg.OK,
				fn : function(){
					this.widgets.getTestAttributeCbx().clearValue();
				},
				scope : this
			});
			
			return ;
		}
		
		    
		   
		LabConfigManager.getTestAttribute( 
				this.widgets.getTestAttributeCbx().getValue(),{
					callback:function( bm ){
						var record = this.table.getGrid().getStore().recordType;
						
						
						var data = new record({
							labTestAttributeId : bm.attributeCode,
							labTestAttributeName : bm.attributeName,
							type : bm.type.description,
							minValue : bm.minValue,
							maxValue : bm.maxValue,
							isMandatory : this.widgets.getIsMandatoryChk().getValue()?"Y":"N"
						});
						
						this.table.getGrid().getStore().add( data);
						this.widgets.getTestAttributeCbx().clearValue();
						
					},
					scope:this
				});
	},
	

	getPanel : function() {
		return this.panel;
	},
	 
	isDataAlredyExist : function( data ){
		var gridData = this.table.getGrid().getStore().data.items;
		for( var i=0 ; i < gridData.length ; i++){
			if( gridData[i].data.labTestAttributeId === data){
				return true;
			}
		}
		
		return false;
	}
	
	
});
