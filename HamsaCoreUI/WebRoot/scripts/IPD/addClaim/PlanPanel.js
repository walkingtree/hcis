Ext.namespace("IPD.addClaim");

IPD.addClaim.plan = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
		this.codeLbl = new Ext.form.Label({
			text:'Code : ',
			style:'font-size:12px'
		});
		
		this.codeValueLbl = new Ext.form.Label({
			text:config.planCode,
			style:'font-size:12px'
		});
		
		this.nameLbl = new Ext.form.Label({
			text:'Name : ',
			style:'font-size:12px'
		});
		
		this.nameValueLbl = new Ext.form.Label({
			text:!Ext.isEmpty(config.planName)?config.planName:"--",
			style:'font-size:12px'
		});
		
		this.insurerLbl = new Ext.form.Label({
			text:'Insurer : ',
			style:'font-size:12px'
		});
		
		this.insurerVlaueLbl = new Ext.form.Label({
			text:!Ext.isEmpty(config.insurerName)?config.insurerName:"--",
			style:'font-size:12px'
		});
		
		this.validFromLbl = new Ext.form.Label({
			text:'Valid from : ',
			style:'font-size:12px'
		});
		
		this.validFromValueLbl = new Ext.form.Label({
			text:config.validFromDt.format('d/m/Y'),
			style:'font-size:12px'
		});
		
		this.validToLbl = new Ext.form.Label({
			text:'Valid to : ',
			style:'font-size:12px'
		});
		
		this.validToValueLbl = new Ext.form.Label({
			text:!Ext.isEmpty(config.validToDt)?config.validToDt.format('d/m/Y'):"--",
			style:'font-size:12px'
		});
		
		this.coverageAmountLbl = new Ext.form.Label({
			text:'Coverage amount : ',
			style:'font-size:12px'
		});
		
		this.coverageAmountValueLbl = new Ext.form.Label({
			text: (config.percentAbsInd == "P") ? config.totalCoverageAmt + ' %' : 
 				 ((config.percentAbsInd == "A") ? 'Rs. ' + config.totalCoverageAmt : '--'),
			style:'font-size:12px'
		});
		
		this.panel = new Ext.form.FieldSet({
			title:'Plan details',
			border:true,
			height:'100%',
			layout:'column',
			defaults:{columnWidth:.5},
			items:[
				{
					layout:'form',
					items:this.codeLbl
				},
				{
					layout:'form',
					items:this.codeValueLbl
				},
				{
					layout:'form',
					items:this.nameLbl
				},
				{
					layout:'form',
					items:this.nameValueLbl
				},
				{
					layout:'form',
					items:this.insurerLbl
				},
				{
					layout:'form',
					items:this.insurerVlaueLbl
				},
				{
					layout:'form',
					items:this.validFromLbl
				},
				{
					layout:'form',
					items:this.validFromValueLbl
				},
				{
					layout:'form',
					items:this.validToLbl
				},
				{
					layout:'form',
					items:this.validToValueLbl
				},
				{
					layout:'form',
					items:this.coverageAmountLbl
				},
				{
					layout:'form',
					items:this.coverageAmountValueLbl
				}
			]
		});
	},
	getPanel : function(){
		return this.panel;
	}
})