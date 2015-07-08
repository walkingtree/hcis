Ext.namespace("administration.addInsurer");

administration.addInsurer.InsurerSponsorAssoc = Ext.extend(Object, {
	constructor : function(config) {
		if(Ext.isEmpty(config)){
			config = {};
		}
		
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
			{ name: 'sponsorName' , mapping:'sponsorName' },
			{ name: 'sponsorNameDesc'},
			{ name: 'insurerName' , mapping:'insurerName' },
			{ name: 'insurerNameDesc'},
			{ name: 'effectiveFromDate' ,  mapping:'effectiveFromDate' },
			{ name: 'effectiveToDate' , mapping:'effectiveToDate' },
			{ name: 'createdDate' , mapping:'createdDate' },
			{ name: 'createdBy' , mapping:'createdBy' }
		]);
		
		this.dataStore = new Ext.data.Store({
			 data:[],
			 reader: new Ext.data.ArrayReader({id:'id'}, this.record)
		});
		
		this.columns = [this.gridChk, {
							header : config.nameTitle,
							dataIndex : 'insurerName',
							hidden: config.nameTitle == "Sponsor name" ?true : false,
							sortable: true
						},{
							header : config.nameTitle,
							dataIndex : 'sponsorName',
							hidden: config.nameTitle == "Insurer name" ?true : false,
							sortable: true
						}, {
							header : 'Effective (from)',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							dataIndex : 'effectiveFromDate',
							sortable: true
						}, {
							header : 'Effective (to)',
							renderer: Ext.util.Format.dateRenderer('d/m/Y'),
							dataIndex : 'effectiveToDate',
							sortable: true
						}];

	    this.addBtn = new Ext.Button({
	    	text:'Add',
			iconCls : 'add_btn',
			scope : this,
			handler:function(){
			 	this.addAssociatedInsurersToGrid(config);
			}
	    });
	    this.editBtn = new Ext.Button({
			iconCls : 'edit_btn',
			text : 'Edit',
			disabled:true,
			scope:this,
			handler:function(){
				this.editAssociatedInsurence();
			}
	    });
	    
      	this.deleteBtn = new Ext.Button({
			iconCls : 'delete_btn',
			text : 'Delete',
			disabled:true,
			scope:this,
			handler:function(){
				this.deleteBtnClicked();
			}
	    });
	    
	    /*
	    this.insurerSponsorAssoc.addBtn.addListener('click',function(){
			this.insurerSponsorAssoc.
		},this);
		
		this.insurerSponsorAssoc.editBtn.addListener('click',function(){
			this.insurerSponsorAssoc.
		},this);
		
		this.insurerSponsorAssoc.deleteBtn.addListener('click',function(){
			this.insurerSponsorAssoc.
		},this);
	    */
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel:	'Effective (from)',
			name:'effectiveFromDate',
	        anchor : '90%',
	        listeners:{
	        	change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel:	'Effective (to)',
			name:'effectiveToDate',
	        anchor : '90%',
	        listeners:{
		   		change: function( date ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});

		this.infoGrid = new Ext.grid.GridPanel({
			frame : false,
			stripeRows : true,
			height : 200,
			autoScroll : true,
			forceFit : true,
			border : false,
			store : this.dataStore,
			tbar : new Ext.Toolbar({
					items:[this.editBtn, '-', this.deleteBtn]
				}),
			columns : this.columns,
			sm:this.gridChk,
			viewConfig:{forceFit:true},
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
		
		if(!Ext.isEmpty(config.cbx)){
			config.cbx.addListener('select',function(comp, record, index){
				this.fromDate.setValue(new Date());
			},this);
		}
		
		this.assocPanel = new Ext.form.FormPanel({
			layout:'column',
			items:[
				{
					layout:'form',
					columnWidth:.50,
					items:config.cbx
				},
				{
					layout:'form',
					columnWidth:.50,
					items:this.fromDate
				},
				{
					layout:'form',
					columnWidth:.50,
					items:this.toDate
				},
				{
					layout:'form',
					columnWidth:.1,
					items:this.addBtn
				}
			]
		});
		
		this.panel = new Ext.Panel({
				title : config.title,
				frame:true,
				autoHeight : true,
				items: [
					this.assocPanel,
					{xtype: 'form', border:false, items:[this.infoGrid]}
				]
		});
		
		if(config.isViewBtnClicked){
			this.panel = new Ext.Panel({
					frame:true,
					autoHeight : true,
					items: this.infoGrid
			});
			this.infoGrid.getColumnModel().setHidden( 0, true );
			this.infoGrid.getTopToolbar().hide();
		}
	},
	getPanel : function() {
			return this.panel;
	},
	getData : function() {
		var valuesMap = this.panel.getForm().getValues();
	},
	resetAssociationPanel:function(){
		this.assocPanel.getForm().reset();
	},
	addAssociatedInsurersToGrid : function(config){
		var values = this.assocPanel.getForm().getValues();
		if(this.assocPanel.getForm().isValid() && 
			(!Ext.isEmpty(values['insurerName']) || 
				!Ext.isEmpty(values['sponsorName']))){
					
			 var record = this.infoGrid.getStore().recordType;
			 var slctdInsurerDesc;
			 var slctdSponsorDesc;
			 
			 for( var i = 0; i<this.infoGrid.getStore().data.items.length; i++ ) {
				var currRow = this.infoGrid.getStore().data.items[i].data;
				var mainThis = this;
				if(!Ext.isEmpty(currRow.insurerName) && (currRow.insurerName == values['insurerName'])){
					Ext.Msg.show({
	 					msg: "Seleted insurer already exists below..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	config.cbx.reset();
					    	config.cbx.focus();
					    }
 					});
 					return;
				}else if(!Ext.isEmpty(currRow.sponsorName) && (currRow.sponsorName == values['sponsorName'])){
					Ext.Msg.show({
	 					msg: "Seleted sponsor already exists below..!",
					    buttons: Ext.Msg.OK,
					    icon: Ext.MessageBox.ERROR,
					    fn:function(btn){
					    	config.cbx.reset();
					    	config.cbx.focus();
					    }
 					});
 					return;
				}
			}
      
			if(config.title == "Associated Insurers"){
				
				for(var i=0; i<insurerStore.data.items.length; i++){
					var currRec = insurerStore.data.items[i];
					if(currRec.data.code == values['insurerName']){
						slctdInsurerDesc = currRec.data.description;
						break;		   			
					}
				}
				
			}else if(config.title == "Associated Sponsors"){
				
				for(var i=0; i<sponsorStore.data.items.length; i++){
					var currRec = sponsorStore.data.items[i];
					if(currRec.data.code == values['sponsorName']){
						slctdSponsorDesc = currRec.data.description;
						break;		   			
					}
				}
			}
			 
		   	 var assocRecord = new record({
					sponsorName: values['sponsorName'],
					sponsorNameDesc:slctdSponsorDesc,
					insurerName: values['insurerName'],
					insurerNameDesc:slctdInsurerDesc,
					effectiveFromDate: Ext.isEmpty(values['effectiveFromDate'])? '':this.fromDate.getValue(),
					effectiveToDate: Ext.isEmpty(values['effectiveToDate'])? '':this.toDate.getValue(),
					createdBy:authorisedUser.userName
			 });
			 
			 this.infoGrid.stopEditing();
			 
			 var insertAt = this.infoGrid.getStore().data.items.length;		 
			 this.infoGrid.getStore().insert(insertAt, assocRecord);
			 
			 this.resetAssociationPanel();
			 this.fromDate.setMaxValue(null);
			 this.toDate.setMinValue(null);
		}else{
			if(config.title == "Associated Insurers"){
				alertError("Please enter the insurer name..!");
			}else if(config.title == "Associated Sponsors"){
				alertError("Please enter the sponsor name..!");
			}
		}
	},
	editAssociatedInsurence:function(){
		this.resetAssociationPanel();
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	var slctdRecordToEdit = this.returnSelectedDataRecord();
			this.deleteBtnClicked();
			this.assocPanel.getForm().setValues(slctdRecordToEdit);
			
  		} else {
  			alertWarning('Please select atleast & atmost one row to edit..!');
  		}
	},
	deleteBtnClicked : function() {
		var slctdRows = this.infoGrid.getSelectionModel().getSelections();
	    if(!Ext.isEmpty(slctdRows) && slctdRows.length>0) {
	    	for(var i = 0; i<slctdRows.length; i++) {
	    		this.infoGrid.stopEditing();
				this.infoGrid.getStore().remove(slctdRows[i]);
	     	}
	    }
	},
	returnSelectedDataRecord: function(){
    	var slctdRows = this.infoGrid.getSelectionModel().getSelections();
      	if(slctdRows.length == 1) {
	     	return slctdRows[0].data;
  		} else {
  			alertWarning('Please select atleast & atmost one row to edit..!');
  		}
    }
});