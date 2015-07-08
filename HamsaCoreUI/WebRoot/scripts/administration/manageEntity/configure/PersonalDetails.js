Ext.namespace("administration.manageEntity.configure");

/*
 *  In this personal details FieldSet and Photopanel are created both are added to FormPanel   
 */

administration.manageEntity.configure.PersonalDetails=Ext.extend(Ext.form.FormPanel,{	
	border:false,
	layout : 'column',
	labelAlign : 'left',
	frame:false,
	monitorValid : true,
 	initComponent : function() {
			if(!Ext.isEmpty(this.initialConfig)){
				config = this.initialConfig;
			}else{
				config = {};
			}
			
			this.widgets = new administration.manageEntity.Widgets();
			config.showButton = true;
			this.photoPanel = new  UploadPhotoPanel(config);
			this.createEntityPersonalDetailsPnl= new Ext.form.FieldSet({
				layout:'form',
				labelAlign:'left',
				width:'93%',
				title : 'Personal details',
				border: true,
				defaults:{columnWidth:.5,labelWidth:100},
				items:[
				       this.widgets.getEntityTypeCombo(),
				       this.widgets.getTitleCombo(),
				       this.widgets.getEntitytNameTxf(),
				       this.widgets.getGenderCombo(),
				       this.widgets.getDateofbirthFld(),
				       this.widgets.getEntityFromJoiningDate(),
				       this.widgets.getIsActiveChk()
					
				]
			});
			
			Ext.applyIf(this, {
				layout:'column',
				
		        items: [
		            {
		            	  columnWidth:0.4,
				          layout:'form',
				          style:'padding-top:8px',
				          items:[
					          this.photoPanel.getPanel()
					          ]
				    },
				    {
				    	  columnWidth:0.6,
				          layout:'form',
				          style:'padding-left:8px',
				          items:this.createEntityPersonalDetailsPnl
				    }		    
				]            
		    });
			
	this.getPhotoPanel = function(){
		return this.photoPanel;
	};
	
	administration.manageEntity.configure.PersonalDetails.superclass.initComponent.apply(this, arguments);
 },
	
     loadData : function( config ){
		this.config = config;
		this.getForm().setValues(config);
		},
	
	resetForm : function( config ){
		this.getForm().reset();
		}
	
});

