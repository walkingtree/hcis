Ext.namespace("OPD.registration");

OPD.registration.AllergiesGridPanel =  Ext.extend(Object, {
	constructor : function(config) {
		
		var mainThis = this;
		
		this.allergiesGridRecord = Ext.data.Record.create([
											{ name: 'allergyName',type:'string'},
											{ name: 'allergyCode',type:'string'},
											{ name: 'fromDate ',type:'date'},
									        { name: 'toDate', type:'date'}
								       ]);
										
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.allergiesGridRecord)
		});
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					var gridStore = this.infoGrid.getStore();
					if( gridStore.data.items.length == 1){
						this.editBtn.enable();
					}else{
						this.editBtn.disable();
					}
					this.deleteBtn.enable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					this.editBtn.disable();
					this.deleteBtn.disable();
				},
				scope:this
			}
		});

		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : msg.edit,
			scope:this,
			disabled:true,
			handler : function() {
				Ext.ux.event.Broadcast.publish('editAllergy');
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : msg.del,
			scope:this,
			disabled:true,
			handler:function(){
				Ext.ux.event.Broadcast.publish('deleteAllergy');
			}
		});

		var gridButtonsBar = [
					'-',this.editBtn,
					'-',this.deleteBtn,
					'-'];
		
		this.infoGrid = new Ext.grid.GridPanel({
				frame : true,
				stripeRows : true,
				height : 290,
				hidden:true,
				width : '99%',
				autoScroll : true,
				border : true,
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				viewConfig:{
					forceFit : true
				},
				tbar : gridButtonsBar,
				columns : [this.gridChk, 
							{
								header : 'Allergy name',
								dataIndex : 'allergyName',
								width : 325,
								sortable: true
							}, {
								header : 'Start date',
								dataIndex : 'fromDate',
								width : 125,
								renderer: Ext.util.Format.dateRenderer('d/m/Y'),
								sortable: true
							}, {
								header : 'End Date',
								dataIndex : 'toDate',
								width : 125,
								renderer: Ext.util.Format.dateRenderer('d/m/Y'),
								sortable: true
							}
				],
				listeners:{
					cellclick: function(thisGrid, rowIndex, columnIndex, eventObj) {
						mainThis.setGridButtonsState(thisGrid.getSelectionModel());
					} 
				}
			});
	},

	setGridButtonsState : function(selectionModel){
		var selectedRows = selectionModel.getSelections();
		this.editBtn.disable();
		this.deleteBtn.disable();
		if( selectedRows.length == 1){
			this.editBtn.enable();
			this.deleteBtn.enable();
			
		} else if( selectedRows.length > 1){
			this.editBtn.disable();
			this.deleteBtn.enable();
		}
	},
	
	getPanel : function() {
		return this.infoGrid;
	},
	
    getData : function(){
		var allergiesBMList = new Array();
		var storeValues = this.infoGrid.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var allergiesBM = {
				allergyDescrption:storeValues[i].data.allergyName,
				allergyCode:storeValues[i].data.allergyCode,
				fromDate:storeValues[i].data.fromDate,
				toDate:storeValues[i].data.toDate
			}
			allergiesBMList.push(allergiesBM)
		}
		return allergiesBMList;
    }
});