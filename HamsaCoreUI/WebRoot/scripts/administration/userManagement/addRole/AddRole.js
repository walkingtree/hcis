
Ext.namespace("administration.userManagement.addRole");
Ext.QuickTips.init();

administration.userManagement.addRole.AddRole = Ext.extend(Object,{
	constructor: function(config) {
	 this.Tree = Ext.tree;
	 this.avaliableTree = new this.Tree.TreePanel({
            animate:true,
            height : 150,
            autoScroll:true,
           	enableDD:true,
            useArrows:true,
            scope:this,
            containerScroll: true,
            loader: new Ext.ux.DWRTreeLoader(
            	{
            		dwrCall:RoleManagementController.getServices,
        			baseParams:
            			{
            				roleId:config.mode == 'edit'?config.roleName:null
        				}
            	}
            	), 
            border: true,
          	dropConfig: {appendOnly:false}
        });
        // set the root node
        this.avaliableTreeRoot = new this.Tree.AsyncTreeNode({
            text: 'Avaliable access', 
            hasChildren:true,
            draggable:false, // disable root node dragging
            id:'-1'
        });
        this.avaliableTree.setRootNode(this.avaliableTreeRoot);
        this.avaliableTree.expandAll();
        
		this.selectedTree = new this.Tree.TreePanel({
            animate:true,
            height : 150,
            autoScroll:true,
            containerScroll: true,
            useArrows:true,
            setModel:true,
            border: true,
            enableDD:true,
            dropConfig : {
            	 allowParentInsert:true,
            	 tree : this,
            	 onNodeDrop:function(n, dd, e, data){
            	 	var test = 100;
            	 }
            },
//            listeners:{
//            	movenode:function( tree,  node,  oldParent,  newParent,  index){
//            		var selectedRoot = tree.getRootNode();
//					var childerns = selectedRoot.childNodes;
//					if(childerns.length>0){
//						var flag=false;
//						for(var i=0; i<childerns.length;i++){
//							if(childerns[i].attributes.text != newParent.attributes.text){
//								flag = true;
//							}
//						}
//						if(flag){
//							selectedRoot.appendChild(oldParent);
//						}
//					}
//					oldParent.appendChild(node);
//            	}
//            },
            loader:  new Ext.ux.DWRTreeLoader(
            	{
            		dwrCall:RoleManagementController.getAssignedNodes,
            		baseParams:
            			{
            				roleId:config.mode == 'edit'?config.roleName:null
            			}
            	}),
            enableDD:true,
            dropConfig: {appendOnly:true}
        });
        
        // add a tree sorter in folder mode
        new this.Tree.TreeSorter(this.selectedTree, {folderSort:true});
        
        // add the root node
        this.selectedTreeRoot = new this.Tree.AsyncTreeNode({
            text: 'selected access', 
            draggable:false, 
            id:'-1'
        });
        this.selectedTree.setRootNode(this.selectedTreeRoot);
        this.selectedTree.expandAll();
        
        
        this.treeFieldSet =new Ext.form.FieldSet({
        	height:'100%',
        	title:userMsg.serviceassignement,
        	layout:'column',
			defaults:{
				columnWidth:.45,
				style:'margin:10px'
			},
        	items:[
        		{
					xtype:'fieldset',
					height:'30%',
					border:true,
					autoScroll:true,
					items:this.avaliableTree,
					hidden:config.flag?true:false,
					title:'Avaliable access'
				},
				{
					xtype:'fieldset',
					border:true,
					height:'30%',
					autoScroll:true,
					title:'Selected access',
					items:this.selectedTree
				}
        	]
        });  
        
        this.saveBtn = new Ext.Button({
			text:userMsg.save,
			iconCls:'save_btn',
   			hidden:config.flag?true:false,
   			tooltip:userMsg.saverole,
   			scope:this,
   			handler: function(){
   				this.saveHandler( config );
   			}
		});
		
		this.cancelBtn = new Ext.Button({
			text:userMsg.cancel,
			iconCls:'cross_icon',
	  		hidden:config.flag?true:false,
	  		scope:this,
	  		handler: function() {
	  			this.mainPanel.ownerCt.close();
	  		}
		});
		this.mainPanel = new Ext.form.FormPanel({
			layout:'column',
			frame:true,
			buttonAlign:'right',
			monitorValid:true,
			defaults:{
				columnWidth:.95
			},
			items:[
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textfield',
							name:'roleName',
							anchor:'90%',
							fieldLabel:userMsg.rolename,
							required:true,
							allowBlank: false,
							readOnly:config.mode?true:false
						}
					]
				},
				{
					layout:'form',
					border:false,
					items:[
						{
							xtype:'textarea',
							anchor:'90%',
							name:'description',
							fieldLabel:userMsg.description
						}
					]
					
				},
				{
					layout:'column',
					defaults:{
						columnWidth:.45
					},
					items:[
						{
							layout:'form',
							border:false,
							items:[
								{
									xtype:'wtcdatefield',
									anchor:'90%',
									name:'effectiveFrom',
									required : true,
									allowBlank : false,
									fieldLabel:userMsg.effectivefrom
								}
							]
							
						},
						{
							layout:'form',
							border:false,
							items:[
								{
									xtype:'wtcdatefield',
									anchor:'90%',
									name:'effectiveTo',
									fieldLabel:userMsg.effectiveto
								}
							]
							
						}
					]
				},
				this.treeFieldSet
			],
			buttons:[
				this.saveBtn,
				{
					text:userMsg.reset,
					iconCls:'reset-icon',
   			  		hidden:config.flag?true:false,
   			  		scope:this,
   			  		handler: function() {
   			  			this.mainPanel.getForm().reset();
   			  			this.selectedTreeRoot.reload();
   			  			this.avaliableTreeRoot.reload();
   			  		}
				}
//				this.cancelBtn
			]
		});
		
		this.mainPanel.on("clientvalidation", function(thisForm, isValid) {
			if (isValid){
				this.saveBtn.enable();
			} else {
				this.saveBtn.disable();
			}
		}, this);
	},
	getPanel:function() {
		return this.mainPanel;
	},
	loadData: function(config){
		if(config.mode=='edit'){
			this.mainPanel.getForm().setValues(config);
		}
	},
	movetoselected:function(tree, node, oldParent, newParent, index){
		var selectedRoot = this.selectedTree.getRootNode();
		var childerns = selectedRoot.childNodes;
		if(childerns.length>0){
			var flag=false;
			for(var i=0; i<childerns.length;i++){
				if(childerns[i].attributes.text != newParent.attributes.text){
					flag = true;
				}
			}
			if(flag){
				selectedRootNode.appendchild(oldParent);
			}
			oldParent.appendchild(node);
		}
	},
	saveHandler: function( config ){
		var mainThis = this;
		var selectionModel = this.selectedTree.getSelectionModel();
		var rootNode = selectionModel.tree.root;
		var serviceList = new Array();
		if(rootNode.childNodes.length>0) {
			for(var i = 0; i<rootNode.childNodes.length;i++){
				var child = rootNode.childNodes[i];
				var service = {
					id:child.attributes.id,
					name:child.attributes.text
				}
				if(child.childNodes.length>0){
					var childServiceList = new Array();
					for(var j =0; j<child.childNodes.length;j++){
						var childNode = child.childNodes[j]
						var childService ={
							id:childNode.attributes.id,
							name:childNode.attributes.text
						}
						childServiceList.push(childService);
					}
					service.childServices=childServiceList;
				}
				serviceList.push(service);
			}
		}
		var formPnl = this.mainPanel;
		var formValues = this.mainPanel.getForm().getValues();
		var role={
			id:formValues['roleName'],
			description:formValues['description'],
			startDtm:getStringAsWtcDateFormat(formValues['effectiveFrom']),
			endDtm:getStringAsWtcDateFormat(formValues['effectiveTo']),
			roleLevelInd:'A',
			roleHasServices:serviceList
		}
		if(config.mode != 'edit'){
			RoleManagementController.addRole(role, function(){
//				Ext.Msg.show({
//					 msg:userMsg.addrolesucessmsg,
//         			 buttons: Ext.Msg.OK,
//         			 icon: Ext.MessageBox.INFO,
//         			 fn: function(btn){
         			 	if(!Ext.isEmpty(config) && !Ext.isEmpty(config.gridObj.getStore())){
         			 		if(config.gridObj.getStore().data.items.length== 0) {
   								config.gridObj.getStore().load({params:{start:0, limit:10}, arg:[config.manageRoleMainPanel.getForm().getValues()]});
   							}else{
   								config.gridObj.getStore().reload();
   							}
         			 	}
         			 	formPnl.getForm().reset();
//         			 	formPnl.ownerCt.close();
						if( !Ext.isEmpty( config ) && config.isPopup == true ){
							layout.getCenterRegionTabPanel().remove( formPnl.ownerCt , true );
							Ext.ux.event.Broadcast.publish('closeAddRoleWindow');
						}
         			 	
//         			 }
//				});
			});
		}
		if(config.mode=='edit'){
			RoleManagementController.modifyRole(role, function(){
//				Ext.Msg.show({
//					 msg:userMsg.updaterolesucessmsg,
//         			 buttons: Ext.Msg.OK,
//         			 icon: Ext.MessageBox.INFO,
//         			 fn: function(btn){
         			 	if(!Ext.isEmpty(config) && !Ext.isEmpty( config.gridObj.getStore() )){
         			 		if(config.gridObj.getStore().data.items.length== 0) {
   								config.gridObj.getStore().load({params:{start:0, limit:10}, arg:[config.manageRoleMainPanel.getForm().getValues()]});
   							}else{
   								config.gridObj.getStore().reload();
   							}
         			 	}
         			 	formPnl.getForm().reset();
	 					if( !Ext.isEmpty( config ) && config.isPopup == true ){
	 						layout.getCenterRegionTabPanel().remove( formPnl.ownerCt , true );
							Ext.ux.event.Broadcast.publish('closeEditRoleWindow');
						}

//         			 }
//				});
			});
		}	
	}
});

