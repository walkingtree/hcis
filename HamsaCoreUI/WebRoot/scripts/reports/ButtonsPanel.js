Ext.namespace("reports");

reports.ButtonsPanel = Ext.extend(Ext.Panel, {
	border : false,
	buttonsAlign : 'right',
	
    initComponent : function() {
    
       this.generateReportBtn = new Ext.Button({
	        text: 'Generate Report',
	        scope : this
        });

       this.resetBtn = new Ext.Button({
	        text: 'Reset',
	        scope : this
        });
    
        Ext.applyIf(this, {
            buttons: [this.generateReportBtn, this.resetBtn]            
        });
        reports.ButtonsPanel.superclass.initComponent.apply(this, arguments);
    },
    
    getGenerateReportBtn : function(){
    	return this.generateReportBtn;
    },

    getResetButtton : function(){
    	return this.resetBtn;
    }

});

Ext.reg('reports-buttons-panel', reports.ButtonsPanel);
