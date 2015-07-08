Ext.namespace("administration.addSponsor");

administration.addSponsor.SLA = Ext.extend(Object, {
	constructor : function(config) {
		Ext.apply(this, config);
		var mainThis = this;
		
		this.record = Ext.data.Record.create([
			{ name: 'sponsorName' , mapping:'sponsorName' },
			{ name: 'activityTypeCd' },
			{ name: 'activityTypeDesc' },
			{ name: 'slaPeriod' ,  mapping:'slaPeriod' },
			{ name: 'slaUnit' , mapping:'slaUnit' }
		]);
		
		this.dataStore = new Ext.data.Store({
			data:[],
			reader: new Ext.data.ArrayReader({id:'id'}, this.record)
		});
			
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
		
		this.addBtn = new Ext.Button({
			text:'Add',
			scope : this,
			iconCls : 'add_btn',
			handler: function(){
				this.addSlaToGrid();
			}
		});
		
		this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			disabled:true,
			scope:this,
			handler : function() {
				this.editSla();
			}
		});
		
		this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope:this,
			handler:function(){
				this.deleteRowFromGrid();
			}
		});
		
		this.columns = [this.gridChk, {
							header : 'Activity',
							dataIndex : 'activityTypeDesc',
							width : 500,
							sortable: true
						}, {
							header : 'SLA Duration',
							dataIndex : 'slaPeriod',
							width : 100,
							sortable: true
						}, {
							header : 'Unit',
							dataIndex : 'slaUnit',
							width : 100,
							sortable: true
						}];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.includeColumns)){
	        var cs = [];
	        for(var i = 0, len = config.includeColumns.length; i < len; i++){
	            cs.push(columns[config.includeColumns[i]]);
	        }
	        columns = cs;
	    }
	    
	    this.tbar = [
    		this.editBtn, 
	    	{xtype : 'tbseparator'}, 
	    	this.deleteBtn];

	    if(!Ext.isEmpty(config) && !Ext.isEmpty(config.hideToolbar)){
	        tbar = [];
	    }

		this.infoGrid = new Ext.grid.GridPanel({
				stripeRows : true,
				height : 208,
				autoScroll : true,
				viewConfig:{forceFit : true},
				border : false,
				store : this.dataStore,
				bbar : this.pagingBar,
				tbar : this.tbar,
				columns : this.columns,
				sm:this.gridChk,
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
			
		this.activityCbx = new Ext.form.ComboBox({
			anchor: '100%',
	        fieldLabel: 'Activity',
	        hiddenName: 'activityTypeCd',
	        store:loadActivityCbx(),
			mode:'local',
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor:'90%',
	        forceSelection : true
		});
		
		this.slaField = new Ext.form.NumberField({
			anchor: '100%',
	        fieldLabel: 'Duration',
	        name: 'slaPeriod'
		});
		
		this.durationUnit = new Ext.form.ComboBox({
			anchor: '90%',
	        fieldLabel: '',
	        labelSeparator:'',
	        hiddenName: 'slaUnit',
	        store:['Hours','Days','Weeks','Months','Years'],
			mode:'local',
			triggerAction: 'all',
			displayField:'description',
			valueField:'code',
	        anchor:'98%',
	        forceSelection : true
		});
		
		this.slaDetailPanel = new Ext.form.FormPanel({
			layout:'column',
			width : '100%',
			autoHeight : true,
			border : false,
			items:[
				{layout:'form',columnWidth:.5,labelWidth:50,items:this.activityCbx},
				{layout:'form',columnWidth:.15,labelWidth:50,items:this.slaField},
			    {layout:'form',columnWidth:.1,labelWidth:0.01,items:this.durationUnit},
			    {layout:'form',columnWidth:.1,style:'padding-left:20px',items: this.addBtn}
			]
		});
		
		this.panel = new Ext.Panel({
				width : '100%',
				autoHeight : true,
				frame: true,
				items: [{border:false, items:[this.slaDetailPanel, this.infoGrid]}]
		});
						  
	},
	getPanel : function() {
			return this.panel;
	},
	getData : function() {
		var valuesMap = this.panel.getForm().getValues();
	},
	addSlaToGrid : function(){
		var mainThis = this;
		if(this.slaDetailPanel.getForm().isValid() && !Ext.isEmpty(this.activityCbx.getValue())){
			var values = this.slaDetailPanel.getForm().getValues();
		 	var record = this.infoGrid.getStore().recordType;
		 
		 	for( var i = 0; i<this.infoGrid.getStore().data.items.length; i++ ) {
				var currRow = this.infoGrid.getStore().data.items[i].data;
				if(currRow.activityTypeCd == values['activityTypeCd']){
					Ext.Msg.show({
	 					msg: "Seleted activity already exists below..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	mainThis.activityCbx.reset();
					    	mainThis.activityCbx.focus();
					    }
 					});
 					return;
				}
			}
			
			var activityStore = loadActivityCbx();
		 	var activityDesc;
		 	for(var i=0; i<activityStore.data.items.length; i++){
				var currRec = activityStore.data.items[i];
				if(currRec.data.code == values['activityTypeCd']){
					activityDesc = currRec.data.description;
					break;		   			
				}
		 	}

		 	if(!Ext.isEmpty(this.activityCbx.getValue()) && 
		 		(Ext.isEmpty(this.slaField.getValue()) || 
		 			Ext.isEmpty(this.durationUnit.getValue()))){
		 			
		 			Ext.Msg.show({
	 					msg: "Please enter duration..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	if(Ext.isEmpty(mainThis.slaField.getValue())){
					    		mainThis.slaField.focus();
					    	}else if(Ext.isEmpty(mainThis.durationUnit.getValue())){
					    		mainThis.durationUnit.focus();
					    	}
					    }
 					});
 					return;
		 	}
		 
		   	 var slaRecord = new record({
					sponsorName: values['sponsorName'],
					activityTypeCd: values['activityTypeCd'],
					activityTypeDesc: activityDesc,
					slaPeriod: values['slaPeriod'],
					slaUnit:values['slaUnit']
			 });
			 this.infoGrid.stopEditing();
			 var insertAt = this.infoGrid.getStore().data.items.length;		 
			 this.infoGrid.getStore().insert(insertAt, slaRecord);
			 this.resetSlaPanel();
		}
	},
	resetSlaPanel : function(){
		this.slaDetailPanel.getForm().reset();
	},
	editSla : function(){
		this.resetSlaPanel();
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.returnSelectedDataRecord();
			this.deleteRowFromGrid();
			this.slaDetailPanel.getForm().setValues(slctdRecordToEdit);
  		} else {
  			alertWarning('Please select atleast & atmost one row to edit..!');
  		}
	},
	returnSelectedDataRecord: function(){
    	var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	return slctdRows[0].data;
  		} else {
  			alertWarning('Please select atleast & atmost one row to edit..!');
  		}
    },
	deleteRowFromGrid : function() {
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
	    if(!Ext.isEmpty(slctdRows) && slctdRows.length>0) {
	    	for(var i = 0; i<slctdRows.length; i++) {
	    		this.infoGrid.stopEditing();
				this.infoGrid.getStore().remove(slctdRows[i]);
	     	}
	    }
	}
});