Ext.namespace("OT.manageOT");

OT.manageOT.OTWidgets =  Ext.extend(Object,{
	constructor : function(){
		
		this.otIdTxf= new Ext.form.TextField({
			name : 'otCode',
			fieldLabel : otMsg.otCode,
			anchor : '90%'
		});
	
		this.otNameTxf= new Ext.form.TextField({
			name : 'otName',
			fieldLabel : otMsg.lblOtName,
			anchor : '90%'
		});

		this.bedNbrCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'bedNbr',
			fieldLabel : otMsg.lblBedNbr,
			anchor : '90%',
			displayField : 'code',
			store : loadOtBedCbx() 
		});
		

		this.coordinatorCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'coordinator',
			fieldLabel : otMsg.lblCoordinator,
			anchor : '90%',
			store : loadCoordinatorEntityCbx()
		});
		

		this.surgeryCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'surgery',
			fieldLabel : otMsg.lblSurgery,
			anchor : '60%',
			store : loadSurgicalTypeServiceCbx()
		});
		
	},
	
	getOtNameTxf : function(config){
		if( !Ext.isEmpty(config) ){
			if( config.required ){
				this.otNameTxf.allowBlank = false;
				this.otNameTxf.required = true;
			}
		}
		return this.otNameTxf;
	},

	getBedNbrCbx : function(config){
		if( !Ext.isEmpty(config) ){
			if( config.required ){
				this.bedNbrCbx.allowBlank = false;
				this.bedNbrCbx.required = true;
			}
		}
		return this.bedNbrCbx;
	},

	getCoordinatorCbx : function(config){
		if( !Ext.isEmpty(config) ){
			if( config.required ){
				this.coordinatorCbx.allowBlank = false;
				this.coordinatorCbx.required = true;
			}
		}
		return this.coordinatorCbx;
	},

	getSurgeryCbx : function(){
		return this.surgeryCbx;
	},
	
	getOtCodeTxf : function( config ){
		if( !Ext.isEmpty(config) ){
			if( config.required ){
				this.otIdTxf.allowBlank = false;
				this.otIdTxf.required = true;
			}
		}
		return this.otIdTxf;
	}

});