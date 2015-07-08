Ext.namespace("reports.ReportsPanel");

reports.ReportsPanel = Ext.extend(Ext.Panel, {
	title : 'Reports',
	labelAlign : 'left',
	border : false,
	frame:true,
	monitorValid : true,
	
    initComponent : function() {
    	
    
    	this.reportNameCbx = new Ext.form.ComboBox({
				fieldLabel : 'Report name',
				mode : 'local',
				store : reportsNameStore(),
				displayField : 'description',
				//value : this.refTypeStore.getAt(0).get('desc'),
				valueField : 'code',
				triggerAction : 'all',
				hiddenName : 'reportName',
				anchor : '40%',
				forceSelection : true,
				required : true,
				allowBlank : false
		});
		
		
		this.generateReportBtn =  new Ext.Button({
	        text: 'Generate report',
	        scope : this,
	        iconCls : 'search_btn',
	        disabled: true
        });

		this.resetBtn =  new Ext.Button({
	        text: 'Reset',
	        scope : this,
	        iconCls : 'cancel_btn'
        });
        
        this.ReportFieldPanel = new Ext.form.FieldSet({
        	title : 'Reports Field',
       		collapsible : false,
			collapsed :  false,
			frame : false,
			border : true,
			height : '100%',
			hidden: true
        }); 
        
        var mainThis = this;
        this.dynamicFormPanel = null;

		this.reportNameCbx.on('select',function( combo,  record, index ){
	
			CoreReportManager.getReportWidgets(record.data.code, 0 , 999 , 'ASC',{
				callback: function(list){
					this.generateReportBtn.enable();
					this.ReportFieldPanel.removeAll();
					this.dynamicFormPanel = new reports.ReportsFormPanel({data : list.data});
					this.ReportFieldPanel.show();
					this.ReportFieldPanel.add(mainThis.dynamicFormPanel);
					this.ReportFieldPanel.setTitle(list.data[0].reportName.description);
					this.ReportFieldPanel.doLayout();
				},
				scope:this
			});	
			
		},this);
		
		this.generateReportBtn.on('click',function(){
			var reportName = this.reportNameCbx.getValue();
			var dataValue = this.dynamicFormPanel.getForm().getValues();
			CoreReportManager.generateReport(reportName , dataValue, function(reportURL){
									       		window.open(getBaseURL() + reportURL);
									       });
		},this);
		
		this.on('clientvalidation',function(thisForm, isValid){
			if(isValid){
				this.generateReportBtn.enable();
			}
			else {
				this.generateReportBtn.disable();
			}
		},this);
		
		this.resetBtn.on('click',function(){
			this.resetBtnClicked();
		},this)
		
		
		
		Ext.applyIf(this, {
           items: [
   			 {
					columnWidth:1,
	            	layout : 'form',
	            	items:[
	            		{
							layout : 'form',
	            			items : this.reportNameCbx
	            		},
	            		this.ReportFieldPanel,{
		            		layout : 'column',
							buttonAlign : 'right',
		            		buttons : [
			            		this.generateReportBtn,
			            		this.resetBtn
		            		]
	            		}
	            	]
			    }
			]            
        });
        reports.ReportsPanel.superclass.initComponent.apply(this, arguments);
		
    },
    
    resetBtnClicked : function(){
    	if(!Ext.isEmpty(this.dynamicFormPanel)){
    		this.dynamicFormPanel.getForm().reset();
    	}
    	this.ReportFieldPanel.remove(this.dynamicFormPanel);
    	this.ReportFieldPanel.doLayout();
    	this.reportNameCbx.reset();
    	this.generateReportBtn.disable();
    }
});

Ext.reg('reports-panel', reports.ReportsPanel);
