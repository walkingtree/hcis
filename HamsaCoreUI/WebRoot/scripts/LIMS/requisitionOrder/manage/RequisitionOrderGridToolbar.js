Ext.namespace("LIMS.requisitionOrder.manage");
							 
LIMS.requisitionOrder.manage.RequisitionOrderGridToolbar = Ext.extend(Ext.Toolbar, {
    initComponent : function() {
    
       this.addBtn = new Ext.Button({
	        text: limsMsg.add,
	        iconCls:'add_btn'
        });

       this.editBtn = new Ext.Button({
	        text: limsMsg.btnEdit,
	        iconCls:'edit_btn',
	        disabled : true
       });
       
       this.deleteBtn = new Ext.Button({
	        text: limsMsg.del,
	        disabled:true,
	        iconCls:'delete_btn'
	        
        });
        
       this.viewBtn = new Ext.Button({
	        text: limsMsg.btnViewDetails,
	        disabled:true,
	        iconCls:'view-icon'
       });

       this.generateBillBtn = new Ext.Button({
	        text: limsMsg.generateBill,
	        disabled:true
        });
        
       this.createReceiptBtn = new Ext.Button({
	        text: limsMsg.createReceipt,
	        disabled:true
       });
        
        Ext.applyIf(this, {
//            items: ['-', this.addBtn, this.EditSep, this.editBtn,this.DelSep, this.deleteBtn, this.reportSep,this.reportBtn, '-' ]            
            items: ['-', this.addBtn,'-',this.editBtn,'-', this.viewBtn,'-',this.createReceiptBtn,'-']
        });
        
        LIMS.requisitionOrder.manage.RequisitionOrderGridToolbar.superclass.initComponent.apply(this, arguments);
    },
    
    getAddBtn : function(){
    	return this.addBtn; 
    },
    
    getEditBtn : function(){
    	return this.editBtn; 
    },

    getViewBtn : function(){
    	return this.viewBtn; 
    },
    
    getDeleteBtn : function(){
    	return this.deleteBtn; 
    },
    
    getGenerateBillBtn : function(){
    	return this.generateBillBtn;
    },
    
    getCreateReceiptBtn : function(){
    	return this.createReceiptBtn;
    }
    
});

Ext.reg('reui-grid-toolbar', LIMS.requisitionOrder.manage.RequisitionOrderGridToolbar);
