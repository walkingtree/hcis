Ext.namespace("administration.medicines.addBrand");

administration.medicines.addBrand.ConfigureBrand = Ext.extend(Object, {
	constructor : function(config) {
		
		this.statusCombo = new Ext.form.ComboBox({
							fieldLabel:pharmacyMsg.status,
							mode:'local',
							store: loadStatusType(),
							displayField:'description',
							valueField:'code',
							triggerAction:'all',
							hiddenName:'status',
							anchor:'80%',
							required:true,
							allowBlank:false
		});
		
		this.buttonPanel = new Ext.form.FieldSet({
			buttonAlign:'right',
			border:false,
			buttons:[{
				xtype:'button',
				text:pharmacyMsg.btnSave,
				iconCls : 'save_btn',
				scope:this,
				disabled:true,
				handler: function(){
					this.saveBrand(config);
				}
			},{
				xtype:'button',
				text:pharmacyMsg.btnReset,
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.resetBrand(config);
				}
			}]
		});		
		
		this.brandCodeTxf = new Ext.form.TextField({
			fieldLabel:pharmacyMsg.brandCode,
			name:'brandCode',
			anchor:'80%',
			maxLength: 25,
			required:true,
			allowBlank:false
		  });
		  
		this.configureBrandPnl = new Ext.form.FormPanel({
			layout : 'column',
			labelAlign : 'left',
			height: '100%',
			width: '60%',
			border : false,
			frame:true,
			monitorValid:true,
			items: [
				{
					columnWidth:1,
					border:false,
					layout:'form',
					items:this.brandCodeTxf
			    },{
					columnWidth:1,
					border:false,
					layout:'form',
					items:[{
							xtype:'textfield',
							fieldLabel:pharmacyMsg.brandName,
							name:'brandName',
							anchor:'80%',
							maxLength: 45,
							required:true,
							allowBlank:false
						  }]
				},{
					columnWidth:1,
					border:false,
					layout:'form',
					items:[this.statusCombo]
				},{
					columnWidth:1,
					border:false,
					layout:'form',
					items:[this.buttonPanel]
				}
			]
		});
		
		this.configureBrandPnl.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.buttonPanel.buttons[0].enable();
			} else {
				this.buttonPanel.buttons[0].disable();
			}
		}, this);

		this.configureBrandPnl.on("render", function(valueToBeSet) {
			valueToBeSet = statusTypeStore.getAt(0);
			this.statusCombo.setValue(valueToBeSet.data.code);
		}, this);
		
		if( config.mode == pharmacyMsg.editMode ){
			this.brandCodeTxf.disable();
		}
		
		var mainThis = this;
		
		this.brandCodeTxf.on('blur', function( comp ){
			BrandManagementController.isBrandExist( comp.getValue(), {
				callback: function( flag ){
					if( flag ){
						Ext.Msg.show({
						   msg: pharmacyMsg.brandExists,
						   buttons: Ext.Msg.OK,
						   fn: function(){
						   	 mainThis.brandCodeTxf.reset();
						   },
						   icon: Ext.MessageBox.ERROR
						});
					}
				},
				callbackScope: this
			});
		},this);
		
		
	},
	
	getPanel : function() {
		return this.configureBrandPnl;
	},
	
	loadData : function(config){
		var slctdRow = config.selectedRow;
		if(slctdRow[status] === false)
			slctdRow[status] = 0;
		else if(slctdRow[status] === true)
			slctdRow[status] = 1;
		this.configureBrandPnl.getForm().setValues(slctdRow);
	}, 
	
	saveBrand : function(config){
		var mainThis = this;
		if(this.configureBrandPnl.getForm().isValid()){
			var inputValues = this.configureBrandPnl.getForm().getValues();
			
			brandBM = {
				brandCode:inputValues['brandCode'],
				description:inputValues['brandName']
     			}
     			
	        if(inputValues['status'] === '1') {
	          brandBM.active = true;
	        } else {
	          brandBM.active = false;
	        }
       
			if (!Ext.isEmpty(config) && (config.mode == pharmacyMsg.addMode)) {
				BrandManagementController.addBrand(brandBM,
				   function(){
						mainThis.resetBrand({});
						loadBrand().load({params:{start:0, limit:9999}, arg:[]});
				   }
				);
			}
			
			if (!Ext.isEmpty(config) && (config.mode == pharmacyMsg.editMode)) {
				var brandCode = this.brandCodeTxf.getValue();
				brandBM.brandCode = brandCode;
				
				BrandManagementController.modifyBrand(
				   brandBM,
				   function(){
						mainThis.resetBrand({});
				   }
      			);
			}
			if(!Ext.isEmpty( config ) && config.isPopup == true){
				this.closeConfigureBrandWindow();
			}
			
		} else {
			error(pharmacyMsg.invalidData);
		}
	}, 
	
	closeConfigureBrandWindow : function(){
		Ext.ux.event.Broadcast.publish('closeConfigureBrandWindow');
		Ext.ux.event.Broadcast.publish('reloadSearchBrandsGrid');
	},
	
	resetBrand : function(config){
		this.configureBrandPnl.getForm().reset();
		if(config.mode ==pharmacyMsg.editMode){
			this.loadData(config);
		}
	}
});