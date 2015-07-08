Ext.namespace("IPD.admission.manageAdmissions");

IPD.admission.manageAdmissions.DischargePanel = Ext.extend( Ext.form.FormPanel,{

	initComponent: function(){
	
			this.okBtn = new Ext.Button({
				text: msg.discharge,
		    	iconCls : 'accept-icon'
			});
			
			
			this.dischargeSummaryBtn = new Ext.Button({
				text: !Ext.isEmpty( this.initialConfig.dischargeSummaryLabel )?
				       this.initialConfig.dischargeSummaryLabel:'' ,
		    	iconCls:!Ext.isEmpty( this.initialConfig.dischargeSummaryIcon )?
				         this.initialConfig.dischargeSummaryIcon:''
			});
	
			this.cancelBtn = new Ext.Button({
				text: msg.close,
		    	iconCls : 'cross_icon'
			});
		
			Ext.apply(this,{
				width:'100%',
				height:'100%',
				frame: true,
				monitorValid: true,
				labelAlign:'top',
				items:[
							  {
							     xtype:'htmleditor',
							     fieldLabel:msg.dischargeSummary,
							     anchor:'100%',
							     height:300,
							     name:'dischargeSummary'
						   	  }
			      ],
			      buttons:[ this.okBtn,this.cancelBtn, this.dischargeSummaryBtn ]
			
			});
			
			IPD.admission.manageAdmissions.DischargePanel.superclass.initComponent.apply( this, arguments );
		},
		getOkBtn: function(){
			return this.okBtn;
		},
		getCancelBtn: function(){
			return this.cancelBtn;
		},
		getDischargeSummaryBtn: function(){
			return this.dischargeSummaryBtn;
		}
});