Ext.namespace("administration.service_group_package.priceUpdate");

administration.service_group_package.priceUpdate.UpdataServicePrice = Ext.extend(Ext.form.FormPanel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	border : false,
	frame:false,
	width:'90%',
	monitorValid : true,


	initComponent : function() {

		this.buttonsPanel = new utils.SearchButtonsPanel();

		this.widgets = new administration.service_group_package.priceUpdate.Widgets();
		
//		this.searchPanel = new administration.service_group_package.priceUpdate.PriceUpdateSearchPanel();
		
		this.gridPanel = new administration.service_group_package.priceUpdate.PriceUpdateGridPanel();
		
		this.optionPanel = new administration.service_group_package.priceUpdate.PriceUpdateOptionPanel();
		
		this.seviceContextPanl = new Ext.Panel( {
												width : '90%',
												labelWidth: 100,
												layout : 'form',
												defaults: {           
												    anchore : '30%'
												}
											});
		
		Ext.applyIf(this, {
			
            items: [
				     {
		    			columnWidth:.5,
		            	layout : 'form',
		            	align:'left',
		            	labelWidth:.001,
		            	items:[this.widgets.getServiceSearchRdGrp()]
                    }, 
                    {
						columnWidth : 1,
						layout : 'form',
						items : [ this.seviceContextPanl ]
					},
                    {
			    	layout : 'column',
			    	columnWidth : 1,
					defaults: {
						border: false,
						layout: 'form'
					},
					items: [{
						columnWidth : .7,
						items: this.gridPanel
					},{
					    columnWidth : .3,
					    items:this.optionPanel
				    }]
			    },
			]            
        });
		
		this.optionPanel.getSaveBtn().on('click',function(){
			
			this.saveBtnClicked();
		},this);
		
		this.optionPanel.getApplyBtn().on('click',function(){
			
			this.applyBtnClicked();
		},this);
		
		
		this.widgets.getServiceSearchRdGrp().on("change",function( radioGroup, value ){
			this.searchTypeChanged( value );
			
		},this);
		
		this.isFormValid = false;
		
		this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid && this.selecteRowCount > 0){
				this.isFormValid = true;
				this.optionPanel.getApplyBtn().enable();
				this.optionPanel.getSaveBtn().enable();
			} else {
				this.optionPanel.getSaveBtn().disable();
				this.optionPanel.getApplyBtn().disable();
				this.isFormValid = false;
			}
		}, this); 

		if( this.gridPanel.getSelectedRows().length > 0){
			this.optionPanel.getApplyBtn().enable();
		}
		
		this.selecteRowCount = 0;
		
		this.gridPanel.getCheckBoxSelectionModel().on('rowselect', function( thisSelectionModel, rowIndex, record){
			
			this.selecteRowCount = thisSelectionModel.getSelections().length;
      		
			if( this.selecteRowCount > 0 &&	this.isFormValid){
      			this.optionPanel.getSaveBtn().enable();
      			this.optionPanel.getApplyBtn().enable();
      		}else{
      			this.optionPanel.getSaveBtn().disable();
				this.optionPanel.getApplyBtn().disable();
      		}
      },this);

		this.gridPanel.getCheckBoxSelectionModel().on('rowdeselect', function( thisSelectionModel, rowIndex, record){
			
			this.selecteRowCount = thisSelectionModel.getSelections().length;
      		
			if( this.selecteRowCount > 0 &&	this.isFormValid){
      			this.optionPanel.getSaveBtn().enable();
      			this.optionPanel.getApplyBtn().enable();
      		}else{
      			this.optionPanel.getSaveBtn().disable();
				this.optionPanel.getApplyBtn().disable();
      		}
			
      },this);
		
		
		administration.service_group_package.priceUpdate.UpdataServicePrice.superclass.initComponent.apply(this, arguments);
		
	},
	
	searchButtonClicked : function(){
		var searchCriteria = this.searchPanel.getValues();
		this.gridPanel.dataStore.removeAll();
		this.gridPanel.loadData( searchCriteria );
		 		
	},
	
	resetButtonClicked : function(){
		this.searchPanel.getReset();
		this.gridPanel.getReset();
		this.gridPanel.loadData();
	},
	
	
	saveBtnClicked : function(){
		var selectedRows = this.gridPanel.getSelectedRows();
		var servicePriceConfig = {};
		
		if( !Ext.isEmpty( selectedRows ) &&  selectedRows.length > 0 ){
			for( var i = 0; i<selectedRows.length; i++ ){
				
				servicePriceConfig[ selectedRows[i].data.serviceCode ] = ""+selectedRows[i].data.newCharge ;
				
			}
		}
		
		var values = this.getForm().getValues();
		
		var servicePriceUpdateBM = {
							updateAmount : !Ext.isEmpty(values['amount'])?parseFloat(values['amount']):null,
							pectAbsInd : values['pectAbsInd'],
							increDecreInd : values['increDecreInd'],
							effectiveFromDt : getStringAsWtcDateFormat(values['effectiveFromDate']),
							userId :  getAuthorizedUserInfo().userName, 
	                        servicePriceMap:servicePriceConfig
						}; 
			
		ServiceManager.updateServicePrice(servicePriceUpdateBM,{
			callback:function(){
				this.optionPanel.resetPriceUpdateOptionPanel();
//				this.gridPanel.loadData();
//				this.getForm().reset();
			},
			callbackScope:this
		});
	},
	
	applyBtnClicked : function(){
		
		var values = this.getForm().getValues();
		this.gridPanel.updateGridData(values.amount, values.increDecreInd, values.pectAbsInd);
	
	},
	
	searchTypeChanged : function( value ){
		
		if( value == 'All'){
			
			this.seviceContextPanl.removeAll();
			this.gridPanel.loadData();
			
			
		}else if( value == 'DEPARTMENT'){
			
			this.seviceContextPanl.removeAll();
			var deptCbx = this.widgets.getNewDepartmentCombo();
			this.seviceContextPanl.add(deptCbx);
			
			deptCbx.on('select', function( comnp,record,index ){
				
				this.gridPanel.loadData({departmentCode: record.data.code});
				
			},this);
			
			
			this.seviceContextPanl.doLayout();
			
		}else if( value == 'SERVICE'){
			
			this.seviceContextPanl.removeAll();
			
			var serviceNameTxf = this.widgets.getNewServiceNameTxf();
			this.seviceContextPanl.add(serviceNameTxf);
			serviceNameTxf.enableKeyEvents = true;
			
			serviceNameTxf.on('keydown',function( comp, e ){
				
				//If enter key is pressed then do the back-end call
				if( e.browserEvent.keyCode == "13"){
					
					this.gridPanel.loadData({serviceName: comp.getValue()});
				}
				
			},this);
			this.seviceContextPanl.doLayout();
		}
	}
	
	

});

