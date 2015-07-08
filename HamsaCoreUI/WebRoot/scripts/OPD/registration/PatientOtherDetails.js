Ext.namespace("OPD.registration");

OPD.registration.PatientOtherDetails =  Ext.extend(Object,{
	constructor:function(config){

		Ext.QuickTips.init();
	 	if( Ext.isEmpty(config) ){
	 		config = { }
	 	}
		
	 	this.smokingChk = new Ext.form.Checkbox({
	 		boxLabel: msg.smoking, 
	 		labelSeparator:'',
        	name: 'smoking'
	 	});
	 	
	 	this.drinkingChk = new Ext.form.Checkbox({
	 		boxLabel: msg.drinking, 
            name:'drinking',
            labelSeparator:''
	 	});

		this.patientHabitsPanel = new Ext.form.FormPanel({
			layout:'column',
			height:'100%',
			title:'Patient habits',
			defaults:{
				layout:'form',
				columnWidth:1,
				labelWidth:1,
				style:'padding:5px'
			},
			items:[
				this.smokingChk,
				this.drinkingChk
			]
		});
	},

	getPanel:function(){
		return this.patientHabitsPanel;
	},
	
	getData:function(){
		var values = this.patientHabitsPanel.getForm().getValues();
		var tmpBM ={
			smokingHabitFlag: values['smoking']== 'on'?true:false,
			drinksAlcohol: values['drinking']== 'on'?true:false
		}
		return tmpBM;
	},
		
	loadData : function(config){
		if(config.mode == 'edit'){
			this.smokingChk.setValue(config.smoking == true ? 'true' : 'false');
			this.drinkingChk.setValue(config.drinking == true ? 'true' : 'false');
		}
	}
	
});