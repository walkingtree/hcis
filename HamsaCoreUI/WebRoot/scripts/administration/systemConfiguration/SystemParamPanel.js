Ext.namespace("administration.systemConfiguration");

administration.systemConfiguration.SystemParamPanel = Ext.extend(Ext.Panel, {
	title : msg.systemParamTitle,
	labelAlign : 'left',
	border : false,
	frame:true,
	initComponent : function(){
		
		var mainThis = this;
		this.resetData = null; 
		
		this.updateBtn =  new Ext.Button({
	        text: msg.update,
	        scope : this,
	        iconCls : 'search_btn',
	        disabled : true
        });

		this.resetBtn =  new Ext.Button({
	        text: msg.reset,
	        scope : this,
	        iconCls : 'cancel_btn'
        });
        
        
        this.on('render',function( thisPanel ){
        	ConfigurationManager.getSystemConfigurations(0,999,'ASC',function(list){
        		if( !Ext.isEmpty(list)){
        			mainThis.createSystemParamFieldSet(list)
	        	}
        	})
        },this);
        
        this.updateBtn.on('click',function(){
        	this.dataValue = this.systemConfigurationParamPanel.getForm().getValues();
        	ConfigurationManager.updateSystemConfiguration( this.dataValue ,function(){
				mainThis.systemConfigurationParamPanel.setDataValues( mainThis.dataValue );
        	});
        },this);
        
        this.resetBtn.on('click',function(){
        	if( !Ext.isEmpty( this.dataValue )){
				this.resetBtnClicked( this.dataValue );
			}
			else{
				this.resetBtnClicked();
			}		
        },this);
        
   		Ext.applyIf(this, {
   			buttonAlign : 'right',
   			buttons : [this.updateBtn ,this.resetBtn],
   			items : this.systemConfigurationFieldPanel
   		});  
   		
        administration.systemConfiguration.SystemParamPanel.superclass.initComponent.apply(this, arguments);
   		   
	},
	getPanel : function(){
		return this;
	},
	createSystemParamFieldSet : function( config ){
		this.resetData = config;
     	this.systemConfigurationParamPanel = new administration.systemConfiguration.SystemParamFormPanel({data : config.data});
       	this.add(this.systemConfigurationParamPanel.getPanel());
       	this.doLayout();
       	this.systemConfigurationParamPanel.on('clientValidation',function(thisForm,isValid){
       		if(isValid){
       			this.updateBtn.enable();
       		}
       		else{
       			this.updateBtn.disable();
       		}
       	},this);
		
	},
	resetBtnClicked : function( config ){
		if( Ext.isEmpty(config)){
       		this.removeAll();
       		this.createSystemParamFieldSet( this.resetData )
		}
		else {
			this.systemConfigurationParamPanel.setDataValues( config )
		}
	}
	
});