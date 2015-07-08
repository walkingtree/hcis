Ext.namespace("administration.checkList");

administration.checkList.CheckListWidgets =  Ext.extend(Object,{
	constructor : function(){
		
		this.checkListNameTxf= new Ext.form.TextField({
			name : 'checkListName',
			fieldLabel : checkListMsg.lblCheckListName,
			anchor : '90%'
		});
	
		/****################# Group(auto complete text box)###################**********/
		
		function record(){
			var record = Ext.data.Record.create([
			  {name: "code", type: "string"},
			  {name: "description", type: "string"}
			]);
			return record;
		}

		function loadGroupNameStore(){
			
			var groupNameStore = new Ext.data.Store({
							    proxy: new Ext.data.DWRProxy(CheckListManager.getCheckListGroups, true),
							    reader: new Ext.data.ListRangeReader(
						    	{totalProperty:'totalSize'}, record()),
							    remoteSort: true
								
								});
			
			groupNameStore.load({params:{start:0, limit:100}, arg:[null]});
			
			return groupNameStore;
		}
		
		
		
		function loadCheckListRoleStore(){
			
			var checkListRoleStore = new Ext.data.Store({
							    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
							    reader: new Ext.data.ListRangeReader(
						    	{totalProperty:'totalSize'}, record()),
							    remoteSort: true
								
								});
			
			checkListRoleStore.load({params:{start:0, limit:100}, arg:["CHECKLIST_ROLE"]});
			
			return checkListRoleStore;
		}
		
		function loadCheckListTypeStore(){
			
			var checkListTypeStore = new Ext.data.Store({
							    proxy: new Ext.data.DWRProxy(DataModelManager.getReferenceDataList, true),
							    reader: new Ext.data.ListRangeReader(
						    	{totalProperty:'totalSize'}, record()),
							    remoteSort: true
								
								});
			
			checkListTypeStore.load({params:{start:0, limit:100}, arg:["CHECKLIST_TYPE"]});
			
			return checkListTypeStore;
		}



		this.checkListGroupTxf= new wtccomponents.WTCComboBox({
			name : 'checkListGroup',
			fieldLabel : checkListMsg.lblGroup,
			anchor : '90%',
			displayField : 'code',
			hideTrigger: true,
			store : loadGroupNameStore(),
			forceSelection : false
		});
	
		this.checkListTypeCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'checkListType',
			fieldLabel : checkListMsg.lblCheckListType,
			anchor : '90%',
//			displayField : 'code',
			store : loadCheckListTypeStore() //TODO:
		});
		
		this.roleCbx = new wtccomponents.WTCComboBox({
			hiddenName : 'role',
			fieldLabel : checkListMsg.lblRole,
			anchor : '90%',
//			displayField : 'code',
			store : loadCheckListRoleStore() //TODO:
		});
		
		this.questionTxf= new Ext.form.TextField({
			name : 'question',
			fieldLabel : checkListMsg.lblQues,
			anchor : '90%'
		});

		this.preRequisiteTxa= new Ext.form.TextArea({
			name : 'preRequisite',
			fieldLabel : checkListMsg.lblPreReqMsg,
			anchor : '90%'
		});
		
		this.addBtn = new Ext.Toolbar.Button({
			iconCls : 'add_btn',
			text : limsMsg.btnAdd,
			scope: this
		});
	},
	
	getCheckListNameTxf : function(config){
		
		Utils.applyConfigProperty(this.checkListNameTxf,config);
		return this.checkListNameTxf;
	},

	getCheckListGroupTxf : function(config){
		
		Utils.applyConfigProperty(this.checkListGroupTxf,config);
		return this.checkListGroupTxf;
	},

	
	getCheckListTypeCbx : function(config){
		
		Utils.applyConfigProperty(this.checkListTypeCbx,config);
		return this.checkListTypeCbx;
	},

	
	getRoleCbx : function(config){
		Utils.applyConfigProperty(this.roleCbx,config);
		return this.roleCbx;
	},
	
	getQuestionTxf : function(config){
		
		Utils.applyConfigProperty(this.questionTxf,config);
		return this.questionTxf;
	},

	getAddBtn : function(){
		return this.addBtn;
	},
	
	getPreRequisiteTxa : function(){
		return this.preRequisiteTxa;
	}
	
});