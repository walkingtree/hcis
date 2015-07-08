Ext.namespace("utils");

utils.GridToolBar = Ext.extend(Ext.Toolbar, {
    initComponent : function() {
    
       this.addBtn = new Ext.Button({
	        text: 'Add',
	        scope : this,
	        iconCls:'add_btn'
        });

       this.editBtn = new Ext.Button({
	        text: 'Edit',
	        scope : this,
	        disabled:true,
	        iconCls:'edit_btn'
        });
    
       this.deleteBtn = new Ext.Button({
	        text: 'Delete',
	        scope : this,
	        disabled:true,
	        iconCls:'delete_btn'
	        
        });
        
        this.reportBtn = new Ext.Button({
	        text: 'Generate report',
	        scope : this,
	        disabled:true,
	        hidden : true
        });
        
        
        Ext.applyIf(this, {
//            items: ['-', this.addBtn, this.EditSep, this.editBtn,this.DelSep, this.deleteBtn, this.reportSep,this.reportBtn, '-' ]            
            items: ['-', this.addBtn, this.editBtn, this.deleteBtn,this.reportBtn,'-']
        });
        
        utils.GridToolBar.superclass.initComponent.apply(this, arguments);
    },
    
    getAddBtn : function(){
    	return this.addBtn; 
    },
    
    getEditBtn : function(){
    	return this.editBtn; 
    },
    
    getDeleteBtn : function(){
    	return this.deleteBtn; 
    },
    
    getReportBtn : function(){
    	return this.reportBtn;
    }
    
});

Ext.reg('grid-toolbar', utils.GridToolBar);
