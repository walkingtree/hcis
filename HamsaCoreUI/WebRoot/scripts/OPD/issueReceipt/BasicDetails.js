Ext.namespace("OPD.issueReceipt");

OPD.issueReceipt.BasicDetails = Ext.extend(Ext.form.FieldSet,{
   	title : msg.patientDetail,
	collapsible : false,
	collapsed :  false,
	frame : false,
	border : true,
	height : '100%',
	initComponent : function(){
		this.patientNameLbl= new Ext.form.Label({
			text : msg.patientname+':',
			cls : 'label-font',
			anchor : '90%'
		}); 	
		
		this.patientNameValueLbl = new Ext.form.Label({
			text :'',
			cls : 'label-font',
			anchor : '90%'
		}); 		
		
		this.accountNameLbl= new Ext.form.Label({
			text : msg.accountNumber+':',
			cls : 'label-font',
			anchor : '90%'
		}); 

		this.accountNumberValueLbl = new Ext.form.Label({
			text : '',
			cls : 'label-font',
			anchor : '90%'
		}); 		
		
   		Ext.applyIf(this, {
   			items : [
   				{
   					layout : 'column',
   					items :[
   						{
   							columnWidth : .12,
   							items :this.patientNameLbl
   						},
   						{
   							columnWidth : .35,
   							items :this.patientNameValueLbl			
   						},
   						{
   							columnWidth : .15,
   							items :this.accountNameLbl
   						},
   						{
   							columnWidth : .35,
   							items :this.accountNumberValueLbl			
   						}
   					]
   				}
   			]
   		});  
        OPD.issueReceipt.BasicDetails.superclass.initComponent.apply(this, arguments);
	},
	getPanel :function(){
		return this;
	},
	getPatientNameValueLbl:function(){
		return this.patientNameValueLbl ;
	},
	getAccountNameValueLbl:function(){
		return this.accountNumberValueLbl ;
	}
});

