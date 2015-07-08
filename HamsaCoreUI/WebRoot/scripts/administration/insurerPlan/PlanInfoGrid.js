Ext.namespace("administration.insurerPlan");
administration.insurerPlan.PlanInfoGrid = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		var mainThis = this;
		
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.deleteBtn.enable();
					if( selectionModel.getSelections().length == 1){
						mainThis.editBtn.enable();
					}else{
						mainThis.editBtn.disable();
					}
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					if( selectionModel.getSelections().length == 1){
						mainThis.editBtn.enable();
					}else if( selectionModel.getSelections().length > 1){
						mainThis.editBtn.disable();
						mainThis.deleteBtn.enable();
					}else{
						mainThis.deleteBtn.disable();
						mainThis.editBtn.disable();
					}
				}
			}
		});
		
		this.record = Ext.data.Record.create([
			{ name: config.nameCode , mapping:config.nameCode },
			{ name: config.nameDesc , mapping:config.nameDesc },
			{ name: config.coverageAmnt ,  mapping:config.coverageAmnt },
			{ name: config.percentAbsInd , mapping:config.percentAbsInd },
			{ name: config.isCoveredFlag , mapping:config.isCoveredFlag }
		]);
		
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.record)
		});
		
		this.columns = [
			this.gridChk, {
			header : config.header,
			dataIndex : config.nameDesc,
			sortable: true
		}, {
			header : 'Amount',
			dataIndex : config.coverageAmnt,
			sortable: true
		}, {
			header : 'Amount indicator' ,
			dataIndex : config.percentAbsInd,
			sortable: true
		},{
			header : 'Covered?' ,
			dataIndex : config.isCoveredFlag,
			sortable: true
		}];
	    
	     this.addBtn = new Ext.Button({
	    	text:'Add',
	    	scope : this,
			iconCls : 'add_btn'
	    });
	    
	    this.editBtn = new Ext.Button({
	    	iconCls : 'edit_btn',
			text : 'Edit',
			scope:this,
			disabled:true
	    });
	    
	    this.deleteBtn = new Ext.Button({
	    	iconCls : 'delete_btn',
			text : 'Delete',
			scope:this,
			disabled:true
	    });
	    
	    this.tbar = [
	    	this.editBtn, 
    		{xtype : 'tbseparator'},
    		this.deleteBtn
		];
		
		if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }
	    
	    this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			height : 200,
			autoScroll : true,
			border : false,
			sm:this.gridChk,
			store : this.dataStore,
			tbar : this.tbar,
			columns : this.columns,
			viewConfig: {forceFit : true},
			listeners:{
				 cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
					if( thisGrid.getSelectionModel().getSelections().length == 1 ){
						mainThis.editBtn.enable();
					}else{
						mainThis.editBtn.disable();
					}
				},
				celldblclick:function(thisGrid, rowIndex, columnIndex, eventObj){
				}
			}
		});
		
		this.amountNbf = new Ext.form.NumberField({
			fieldLabel:'Amount',
			anchor:'95%',
			name:config.coverageAmnt,
			listeners:{
				blur:function(comp){
					if(!Ext.isEmpty(mainThis.indicatorCbx.getValue()) && 
						(mainThis.indicatorCbx.getValue() == "P") &&  
							parseInt(comp.getValue()) > 100){
						Ext.Msg.show({
		 					msg: "% value of amount cannot be greater than 100..!",
						    buttons: Ext.Msg.OK,
						    icon: Ext.MessageBox.ERROR,
						    fn:function(btn){
						    	comp.reset();
						    	comp.focus();
						    	mainThis.indicatorCbx.reset();
						    }
	 					});
	 					return;
					}
				}
			}
		});
		
		this.indicatorCbx = new Ext.form.ComboBox({
			fieldLabel:'Amount indicator',
			anchor:'95%',
			store:[['P','%'],['A','Monetary']],
			forceSelection:true,
			name:config.percentAbsInd,
			listeners:{
				select:function(comp,record,index){
					if(comp.getValue() == "P" && 
						!Ext.isEmpty(mainThis.amountNbf.getValue()) &&  
							parseInt(mainThis.amountNbf.getValue()) > 100){
						Ext.Msg.show({
		 					msg: "% value of amount cannot be greater than 100..!",
						    buttons: Ext.Msg.OK,
						    icon: Ext.MessageBox.ERROR,
						    fn:function(btn){
						    	mainThis.amountNbf.reset();
						    	mainThis.amountNbf.focus();
						    	comp.reset();
						    }
	 					});
	 					return;
					}
				}
			}
		});
		
		this.panel = new Ext.Panel({
			layout:'column',
			items:[
				{
					layout:'form',
					labelWidth:120,
					columnWidth:.5,
					items:config.cbx
				},
				{
					layout:'form',
					labelWidth:120,
					columnWidth:.5,
					items:this.amountNbf
				},
				{
					layout:'form',
					columnWidth:.50,
					items:this.indicatorCbx
				},
				{
					layout:'form',
					columnWidth:.17,
					labelWidth:.1,
					items:[
						{
							xtype:'checkbox',
							boxLabel:'Covered?',
							labelSeparator:'',
							checked:true,
							name:config.isCoveredFlag
						}
					]
				},
				{
					layout:'form',
					columnWidth:.25,
					items:this.addBtn
				},
				{
					layout:'form',
					columnWidth:1,
					items:this.infoGrid
				}
			]
		});
	},
	getPanel : function(){
		return this.panel;
	}
	
})