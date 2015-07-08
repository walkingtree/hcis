
Qualification = Ext.extend(Object,{
	constructor:function(config){
			if(Ext.isEmpty(config)){
				config = {};
			}
			
			Ext.apply(this,config);
			
			this.record = Ext.data.Record.create([
        	  {name: "code", type: "string"},
        	  {name: "description", type: "string"}
        	]);
			
			this.motherTongueStore = new Ext.data.Store({
			    proxy: new Ext.data.DWRProxy(DataModelManager.getMotherTongue, true),
			    reader: new Ext.data.ListRangeReader(
			    	{totalProperty:'totalSize'}, this.record),
			    remoteSort: true
			});
			
			this.motherTongueStore.load({params:{start:0, limit:8}, arg:[]});
			

			
			
			this.QualificationCbx = new Ext.form.ComboBox({
				 hiddenName:'qualification',
				 forceselection:true,
				 store: loadQualificationCbx(),
				 mode:'local',
				 displayField:'description',
				 valueField:'code',
				 triggerAction:'all',
				 fieldLabel:msg.qualification,
				 anchor:'98%',
				 editable:false,
				 value:config.selectedQualification
       
			})
			
			this.lovCombo = new Ext.ux.form.LovCombo(								
				{
					store:this.motherTongueStore,
					hideOnSelect:false,
					mode:'local',
					hideTrigger :true,
					readOnly:false,
					hiddenName:'languages',
					displayField:'description',
					valueField:'description',
				    triggerAction: 'all',
				    fieldLabel:msg.knownlanguages,
				    anchor:'98%',
				    value:config.selectedknownLanguages
				}
			)
			
			this.qualificationFieldSet = new Ext.form.FieldSet({
				height:'100%',
				border:false,
				items:[
					{
						columnWidth:1,
						layout:'form',
						border:false,
		 				items:this.QualificationCbx
					},
					{
						columnWidth:1,
						layout:'form',
						border:false,
		 				items:[
	 				       this.lovCombo
						  ]
					},	  
					{
						columnWidth:1,
						layout:'form',
						border:false,
						items:[
							  {
							     xtype:'textfield',
							     fieldLabel:msg.referencedby,
							     name:'referredBy',
							     anchor:'98%',
							     value:config.selectedReferredBy
							  }
					      ]
					},	  
					{
						columnWidth:1,
						layout:'form',
						border:false,
						items:[
							  {
							     xtype:'htmleditor',
							     fieldLabel:msg.experience,
							     anchor:'98%',
							     height:200,
							     name:'experience',
							     value: config.selectedworkExperience
						   	  }
					      ]
					}],
				 listeners:{
					afterrender:function( comp ){
						if( !Ext.isEmpty(config)&& config.mode == 'edit'){
							if( !Ext.isEmpty( qualificationStore ) ){
								
								qualificationStore.on('load', function(){
									this.QualificationCbx.setValue(config.selectedQualification);
								},this);
								
							}else{
								this.QualificationCbx.setValue(config.selectedQualification);								
							}
						}
					},
					scope:this
					
				}
				
			});
			
			this.formPanel = new Ext.form.FormPanel({
				border:false,
				items:this.qualificationFieldSet
			});
			
//			Ext.ux.event.Broadcast.subscribe('doctorReset',function(){
//				this.formPanel.getForm().reset();
//			},this);
			
			this.motherTongueStore.on('load',function(){
				this.lovCombo.setValue( config.selectedknownLanguages );
			},this);
	},
	getPanel:function(){
		return this.formPanel;
	},
	getData:function(){
		return this.formPanel.getForm().getValues();
	},
	setData:function(data){
		this.formPanel.getForm().setValues(data);
	},
	loadCombos:function(qualification){
		this.QualificationCbx.setValue(qualification) ;
	},
	resetQualificationPanel : function(){
		this.formPanel.getForm().reset();
		if( !Ext.isEmpty( this.selectedknownLanguages )){
			this.lovCombo.setValue( this.selectedknownLanguages );
		}
		
	}
});
			
