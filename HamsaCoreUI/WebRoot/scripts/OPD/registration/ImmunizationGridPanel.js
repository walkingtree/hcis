Ext.namespace("OPD.registration");

OPD.registration.ImmunizationGridPanel =  Ext.extend(Object, {
	constructor : function() {

		var mainThis = this;
		
		this.ImmunizationGridRecord = Ext.data.Record.create([
											{ name: 'immunizationName',type:'string'},
											{ name: 'vaccinationDate',type:'date'}
									        
								       ]);
										
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.ImmunizationGridRecord)
		});
			
		this.gridChk = new Ext.grid.CheckboxSelectionModel({
			listeners:{
				rowselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
				},
				rowdeselect : function( selectionModel, rowIndex, record){
					mainThis.editBtn.disable();
					mainThis.deleteBtn.disable();
				}
			}
		});

		this.editBtn = new Ext.Toolbar.Button({
			iconCls : 'edit_btn',
			text : msg.edit,
			scope:this,
			disabled:true,
			handler : function() {
				Ext.ux.event.Broadcast.publish('editImmunization');
			}
		});
		
		this.deleteBtn = new Ext.Toolbar.Button({
			iconCls : 'delete_btn',
			text : msg.del,
			scope:this,
			disabled:true,
			handler:function(){
				Ext.ux.event.Broadcast.publish('deleteImmunization');
			}
		});

		var gridButtonsBar = [
					'-',this.editBtn,
					'-',this.deleteBtn,
					'-'];

		this.infoGrid = new Ext.grid.GridPanel({
				width:'70%',
				frame : true,
				stripeRows : true,
				height : 250,
				hidden:true,
				autoScroll : true,
				border : true,
				store : this.dataStore,
				bbar : this.pagingBar,
				sm:this.gridChk,
				viewConfig:{
					forceFit : true
				},
				tbar :  gridButtonsBar,
				columns : [this.gridChk, 
							{
								header : 'Immunization Name',
								dataIndex : 'immunizationName',
								width : 450,
								sortable: true
							}, {
								header : 'Date',
								dataIndex : 'vaccinationDate',
								renderer: Ext.util.Format.dateRenderer('d/m/Y'),
								width : 125,
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
	
	getData:function(){
		var immunizationsBMList = new Array();
		var storeValues = this.infoGrid.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var tmpBM = {
				immunizationDescrption :storeValues[i].data.immunizationName,
				immunizationCode:storeValues[i].data.immunizationCode,
				vaccinationDate:storeValues[i].data.vaccinationDate
			}
			immunizationsBMList.push(tmpBM);
		}
		return immunizationsBMList;
	}
});