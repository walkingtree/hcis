Ext.namespace("LIMS.testTemplate.configure");
							 
LIMS.testTemplate.configure.TestTemplateButtonsPanel = Ext.extend(Ext.Panel, {
    initComponent : function() {
    
	this.buttonsPanel = new utils.ButtonsPanel();
       
       
       this.approveBtn = new Ext.Button({
	        text: 'Approve',
	        iconCls:'approve-icon'
	        
        });
        
       this.disapproveBtn = new Ext.Button({
	        text: 'Disapprove',
	        iconCls:'disapprove-icon'
       });

       this.printBtn = new Ext.Button({
	        text: 'Print',
	        iconCls:'print_btn'
        });
        
       this.markCmpldBtn = new Ext.Button({
    	    text: 'Mark completed',
			iconCls : 'accept-icon'
       });
      
       this.viewHistoryBtn = new Ext.Button({
	   	    text: 'View change history',
			iconCls : 'report-icon'
   });
       
       if(this.initialConfig.MODE === limsMsg.MODE_EDIT){
    	  
    	   Ext.applyIf(this, {
             buttons: [ this.buttonsPanel,this.markCmpldBtn],
             buttonsAlign : 'right'
         });
    	   
       }else if(this.initialConfig.MODE === limsMsg.MODE_VIEW){
    	   
    	   Ext.applyIf(this, {
             buttons: [ this.printBtn,this.viewHistoryBtn],
             buttonsAlign : 'right'
         }); 
       }else if(this.initialConfig.MODE === limsMsg.MODE_APPROVE){
    	   
    	   Ext.applyIf(this, {
             buttons: [ this.approveBtn,this.disapproveBtn,this.printBtn],
             buttonsAlign : 'right'
         });
       }
       else{
    	   //Default
    	   Ext.applyIf(this, {
               buttons: [ this.buttonsPanel],
               buttonsAlign : 'right'
           });
       }
       
//       Ext.applyIf(this, {
//            buttons: [ this.saveBtn,this.resetBtn],
//            buttonsAlign : 'right'
//        });
        
        LIMS.testTemplate.configure.TestTemplateButtonsPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getSaveBtn : function(){
    	return this.buttonsPanel.getSaveBtn(); 
    },
    
    getResetBtn : function(){
    	return this.buttonsPanel.getResetBtn(); 
    },

    getApproveBtn : function(){
    	return this.approveBtn;
    },
    
    getDisapproveBtn : function(){
    	return this.disapproveBtn;
    },
    
    getPrintBtn: function(){
    	return this.printBtn;
    },
    getMarkCmpldBtn : function(){
    	return this.markCmpldBtn;
    },
    getPanel : function(){
    	return this;
    },
    
    getViewHistoryBtn : function(){
    	return this.viewHistoryBtn;
    }
});


