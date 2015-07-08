Ext.namespace("administration.manageEntity.configure");


// Entity qualification FormPanel created and used this in AddEntity Panel 

administration.manageEntity.configure.EntityQualification=Ext.extend(Ext.form.FormPanel,{	

	border:false,
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
 	initComponent : function() {
			if(!Ext.isEmpty(this.initialConfig)){
				config = this.initialConfig;
			}else{
				config = {};
			}
			
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
			
			this.motherTongueStore.load({params:{start:0, limit:999}, arg:[]});
			

			
			
			this.qualificationCbx = new Ext.form.ComboBox({
				 hiddenName:'qualification',
				 forceselection:true,
				 store: loadQualificationCbx(),
				 mode:'local',
				 displayField:'description',
				 valueField:'code',
				 triggerAction:'all',
				 fieldLabel:entityMsg.qualification,
				 anchor:'98%',
				 editable:false,
				 value:config.qualification
       
			});
			
			
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
				    fieldLabel:entityMsg.knownlanguages,
				    anchor:'90%',
				    value:config.knownLanguages
				}
			);
			
			this.lovCombo.getStore().on('load',function(){
				this.lovCombo.setValue( config.knownLanguages );
			},this);
			
			this.referenceTxf = new Ext.form.TextField({
				name: 'referredBy',
				fieldLabel :entityMsg.referencedby,
				anchor:'98%',
				value:config.selectedReferredBy
			});
			
			this.experenceTxf = new Ext.form.HtmlEditor({
				name: 'experience',
				fieldLabel :entityMsg.experience,
				anchor:'98%',
				height:100,
				value: config.selectedworkExperience
			});
			
			this.qualificationFieldSet = new Ext.form.FieldSet({
				height:'100%',
				border:true,
				title:'Qualification',
				layout:'column',
				items:[
					{
						columnWidth:.33,
						layout:'form',
						border:false,
		 				items:this.getQualificationCbx()
					},
					{
						columnWidth:.33,
						layout:'form',
						border:false,
						labelWidth : 120,
		 				items:[
	 				       this.getLovCombo()
						  ]
					},	  
					{
						columnWidth:.33,
						layout:'form',
						border:false,
						items:[
						       this.getReferenceTxf()
					      ]
					},	  
					{
						columnWidth:1,
						layout:'form',
						border:false,
						items:[
						       this.getExperenceTxf()
					      ]
					}],
				 listeners:{
					afterrender:function( comp ){
						if( !Ext.isEmpty(config)&& config.mode == 'edit'){
							if( !Ext.isEmpty( qualificationStore ) ){
								
								qualificationStore.on('load', function(){
									this.qualificationCbx.setValue(config.qualification);
								},this);
								
							}else{
								this.qualificationCbx.setValue(config.qualification);								
							}
						}
					},
					scope:this
					
				}
				
			});
			
			Ext.applyIf(this, {
				layout:'column',
				
		        items: [
		                this.qualificationFieldSet   
				]            
		    });
			administration.manageEntity.configure.EntityQualification.superclass.initComponent.apply(this, arguments);	
			
	},
	
	getPanel:function(){
		return this;
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
	
	getQualificationCbx:function(){
		return this.qualificationCbx;
	},
	
	getLovCombo:function(){
		return this.lovCombo;
	},
	
	getReferenceTxf:function(){
		return this.referenceTxf;
	},
	
	getExperenceTxf:function(){
		return this.experenceTxf;
	},
	
	resetQualificationPanel : function(){
		this.getForm().reset();
		if( !Ext.isEmpty( this.knownLanguages )){
			this.lovCombo.setValue( this.knownLanguages );
		}
		
	}
});
			
Ext.reg('qualification-details', administration.manageEntity.configure.EntityQualification);