Ext.ns("administration.checkList.configure");


administration.checkList.configure.ConfigureCheckList = Ext.extend(Ext.form.FormPanel,{
	
	border:false,
	layout : 'column',
	labelAlign : 'left',
	monitorValid : true,
	
	initComponent : function(){
			
		this.widgets = new administration.checkList.CheckListWidgets();
		
		this.buttonsPanel = new utils.ButtonsPanel();
		
		this.questionPanel = new administration.checkList.configure.QuestionPanel();
		
		this.table = new administration.checkList.configure.AssociatedQuesGrid();
		
		this.buttonsPanel.getSaveBtn().on('click',function(){
			this.saveBtnClicked();
		},this);
		
		this.buttonsPanel.getResetBtn().on('click',function(){
			this.resetBtnClicked();
		},this);
		
		this.on('clientvalidation',function(thisPanel, isValid){
			
			if( isValid){
				this.buttonsPanel.getSaveBtn().enable();
			}
			else{
				this.buttonsPanel.getSaveBtn().disable();
			}
		},this);
		
		Ext.apply(this,{
			border:false,
			layout : 'column',
			labelAlign : 'left',
			items : [
				{
					columnWidth:0.4,
	            	layout : 'form',
	            	labelWidth:110,
					items:[this.widgets.getCheckListNameTxf({required:true,allowBlank:false})]
				},
				{
					columnWidth:0.4,
					layout : 'form',
					labelWidth :55,
					items:[this.widgets.getCheckListTypeCbx({required:true,allowBlank:false})]
				},
				
				{
					columnWidth:1,
					xtype:'fieldset',
					layout : 'form',
					labelWidth : 55,
					collapsible: false,
					labelAlign:'left',
					width : '92%',
					style: 'padding-top:10px',
//					autoHeight: true,
					border : true,
					title:"Details",
					items:[this.questionPanel]
				},
				{
					columnWidth:1,
					layout : 'form',
					labelWidth : 55,
					items:[this.buttonsPanel]
				},
			]
		});
	administration.checkList.configure.ConfigureCheckList.superclass.initComponent.apply(this, arguments);
	},
	
	resetBtnClicked : function(){
		this.getForm().reset();
	},
	
	addBtnClicked : function(){
		
	},
	
	saveBtnClicked : function(){
		var gridDataList = this.questionPanel.getGridData();
		var checkListDetailList = new Array();
		for( var i = 0 ; i < gridDataList.length ; i++){
			var checkListDetailBM = {
				
				checkListDetailId: gridDataList[i].data.checkListDetailId,
				question : gridDataList[i].data.question,
				role : {code : gridDataList[i].data.role},
				group : gridDataList[i].data.group
			};
			checkListDetailList.push( checkListDetailBM );
		}
		
		var checkListBM = {
			name : this.widgets.getCheckListNameTxf().getValue(),
			checkListType : {code : this.widgets.getCheckListTypeCbx().getValue()},
			userId : getAuthorizedUserInfo().userName,
			checkListDetailBMList : checkListDetailList,
			prerequisite:this.widgets.getPreRequisiteTxa().getValue()
		};
		
		var mainThis = this;
		
		if(this.initialConfig.mode === "edit"){
			
			checkListBM.checkListId=this.initialConfig.checkListId;
			CheckListManager.modifyCheckList(checkListBM,false, function(){
				layout.getCenterRegionTabPanel().remove( mainThis );
				
				if( !Ext.isEmpty( mainThis.initialConfig.grid )){
					mainThis.initialConfig.grid.loadGrid();
				}
					
			});
		
		}else{
			
			CheckListManager.addCheckList( checkListBM, function(){
				if( mainThis.initialConfig.mode === "add"){
					layout.getCenterRegionTabPanel().remove( mainThis );
					if( !Ext.isEmpty( mainThis.initialConfig.grid )){
						mainThis.initialConfig.grid.loadGrid();
					}
				}
				else{
					mainThis.resetBtnClicked();
				}
			} );
			
		}
		
				
	},
	
	loadData : function( config ){
		this.getForm().setValues( config );
		this.widgets.getCheckListTypeCbx().getStore().on('load',function(thisCombo){
			this.widgets.getCheckListTypeCbx().setValue( config.type);
		},this); 
		
		this.questionPanel.loadData(config);
	}
	
	
});