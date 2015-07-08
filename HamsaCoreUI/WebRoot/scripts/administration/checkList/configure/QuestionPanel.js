Ext.ns("administration.checkList.configure");

administration.checkList.configure.QuestionPanel = Ext.extend(Ext.Panel, {
	border:false,
	layout : 'column',
	labelAlign : 'left',
	monitorValid : true,
	
 	initComponent : function() {
		this.recordStatus = false;
		this.widgets = new administration.checkList.CheckListWidgets();

		this.table = new administration.checkList.configure.AssociatedQuesGrid();
		
		this.buttonPanel = new Ext.Panel( {
							buttonsAlign : 'right',
							buttons : this.widgets.getAddBtn()
						});
		

		this.widgets.getAddBtn().on('click',function(thisBtn){
			this.addBtnClicked();
		},this);

		this.table.gridToolbar.getEditBtn().on('click',function(){
			this.gridEditBtnClicked();
		},this);
		
		Ext.applyIf(this, {
			border:false,
			layout : 'column',
			labelAlign : 'left',
//			defaults:{labelWidth:70},
            items: [
	            {
					columnWidth:0.4,
	            	layout : 'form',
	            	items:[this.widgets.getCheckListGroupTxf()]
			    },{
					columnWidth:0.4,
	            	layout : 'form',
					items:[this.widgets.getRoleCbx()]
				},{
					columnWidth:0.8,
	            	layout : 'form',
					items:[this.widgets.getQuestionTxf()]
				},{
					columnWidth:0.2,
	            	layout : 'form',
	            	labelAlign : 'right',
					items:[this.buttonPanel]
			    }
				,{
					columnWidth:1,
	            	layout : 'form',
					items:[this.table]
			    }
					
			]            
        });
        
		
        this.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.widgets.getAddBtn().enable();
			} else {
				this.widgets.getAddBtn().disable();
				this.recordStatus = null;
				this.seqNbr = null;
			}
		}, this); 
		
        administration.checkList.configure.QuestionPanel.superclass.initComponent.apply(this, arguments);

	},
	
	
	loadData : function( config ){
		this.table.loadGrid(config);
	},
	

	
	addBtnClicked : function(){
		
		if( Ext.isEmpty(this.widgets.getQuestionTxf().getValue())){
			
			warning(checkListMsg.msgSectQues);

			return;
		}
		
		if( this.isDataAlredyExist( this.widgets.getCheckListGroupTxf().getValue(),
				this.widgets.getRoleCbx().getValue(),this.widgets.getQuestionTxf().getValue() ) ){
			Ext.Msg.show({
				msg : checkListMsg.msgRecordAlreadyExist,
				buttons : Ext.Msg.OK,
				fn : function(){
					this.widgets.getTestAttributeCbx().clearValue();
				},
				scope : this
			});
			
			return ;
		}
		
    	var recordType = this.table.getStore().recordType;
			var record = new recordType({
				question : this.widgets.getQuestionTxf().getValue(),
				role : this.widgets.getRoleCbx().getValue(),
				roleDesc : this.widgets.getRoleCbx().getRawValue(),
				group : this.widgets.getCheckListGroupTxf().getRawValue()
			});
			
			this.table.getStore().add( record );
		
			this.resetPanel();
	},
	
	isDataAlredyExist : function( group,role,question ){
		var gridData = this.table.getStore().data.items;
		for( var i=0 ; i < gridData.length ; i++){
			if( gridData[i].data.question === question &&
					gridData[i].data.role === role &&
						gridData[i].data.group === group){
				return true;
			}
		}
		
		return false;
	},
	
	getGridData : function(){
		return this.table.getData();
	},
	
	gridEditBtnClicked : function(){
    	var selectedRows = this.table.getSelectedRows();

    	this.widgets.getCheckListGroupTxf().setValue(selectedRows[0].data.group);
		this.widgets.getRoleCbx().setValue(selectedRows[0].data.role);
		this.widgets.getQuestionTxf().setValue(selectedRows[0].data.question);
    	this.table.getStore().remove( selectedRows[0] );
    	
    	this.table.setGridButtonState();
    },
    
    resetPanel : function(){
    	this.widgets.getCheckListGroupTxf().setValue("");
		this.widgets.getRoleCbx().setValue("");
		this.widgets.getQuestionTxf().setValue("");
    }
	
});
