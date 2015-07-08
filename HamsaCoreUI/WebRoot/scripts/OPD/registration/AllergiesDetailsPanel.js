Ext.namespace("OPD.registration");

OPD.registration.AllergiesDetailsPanel = Ext.extend(Object,{
		constructor : function(config) {

			Ext.QuickTips.init();
		 	if( Ext.isEmpty(config) ){
		 		config = { }
		 	}

			var mainThis = this;
			
			this.selectedItem;

			this.allergiesCbx = new Ext.form.ComboBox( {
				mode :'local',
				store :loadAllergies(),
				forceSelection :true,
				triggerAction :'all',
				displayField :'description',
				valueField :'code',
				hiddenName :'allergyName',
				fieldLabel :msg.allergyname,
				anchor :'99%',
				emptyText : "Select...",
				listeners : {
					select : function(combo, record, index) {
						this.selectedItem = record.data;
					},
					scope:this
				}
			});

			this.fromDate = new Ext.form.WTCDateField( {
				name :'fromDate',
				fieldLabel :msg.from,
				anchor :'99%',
				emptyText : "dd/mm/yyyy",
				listeners : {
					blur : function(date) {
						if (!Ext.isEmpty(date.getValue())) {
							this.toDate.setMinValue(date.getValue());
						}
					},
					scope:this
				}
			});

			this.toDate = new Ext.form.WTCDateField( {
				name :'toDate',
				fieldLabel :msg.to,
				anchor :'99%',
				emptyText : "dd/mm/yyyy",
				listeners : {
					blur : function(date) {
						if (!Ext.isEmpty(date.getValue())) {
							this.fromDate.setMaxValue(date.getValue());
						}
					},
					scope:this
				}
			});

			this.addBtn = new Ext.Button( {
				text :msg.add,
				iconCls :'add_btn',
				tooltip :msg.addallergy,
				scope :this,
				handler : function() {
					if( null == this.allergiesCbx.getValue() ||
						Ext.isEmpty(this.allergiesCbx.getValue())){
						this.selectedItem = null;
						error("Please select an allergy to add ..!","Add allergy failed ..!");
						return;
					}
//					Ext.ux.event.Broadcast.publish('addAllergy');
					this.addAllergy();
				}
			});

			this.allergyDetailsInputPanel = new Ext.Panel( {
				layout :'column',
				autoHeight : true,
				width : '99%',
				hidden :true,
				frame:true,
				title:"Enter allergy details",
				border: true,
				defaults : {
					labelAlign :'left',
					labelWidth:80
				},
				items : [ 
					{
						layout :'form',
						columnWidth : .4,
						items :this.allergiesCbx
					}, {
						layout :'form',
						labelWidth:35,
						columnWidth : .25,
						items :this.fromDate
					}, {
						layout :'form',
						labelWidth:35,
						columnWidth : .25,
						items :this.toDate
					}, {
						layout :'form',
						columnWidth : .1,
						items :this.addBtn,
						style:'padding-left:2px;'
					}
				]
			});
			
			this.allergiesGridPanel = new OPD.registration.AllergiesGridPanel();
			
			this.knownAllergiesChbx = new Ext.form.Checkbox({
				boxLabel: "Known allergies ?",
				labelSeparator:'',
				name:'knownallergies',
				checked : (config.mode == 'edit' && config.isPatientHaveAnyAllergies == true) ? true : false
			});
			
			this.mainPanel = new Ext.form.FormPanel({
					frame :false,
					border :true,
					autoHeight :true,
					autoScroll :true,
					defaults:{
						style:'padding-bottom: 5px;'
					},
					items : [ {
								labelWidth : 0.1,
								items:[this.knownAllergiesChbx]
							  },
							  this.allergyDetailsInputPanel,
							  this.allergiesGridPanel.getPanel()]
			});

			this.knownAllergiesChbx.addListener('check',
				function(comp, checked) {
					if (checked) {
						mainThis.allergyDetailsInputPanel.show();
						mainThis.allergyDetailsInputPanel.doLayout();
	
						mainThis.allergiesGridPanel.infoGrid.getStore().removeAll();
						mainThis.allergiesGridPanel.infoGrid.show();
					} else {
						mainThis.allergiesGridPanel.infoGrid.hide();
						mainThis.allergyDetailsInputPanel.hide();
					}
				}
			);
			
			Ext.ux.event.Broadcast.subscribe('deleteAllergy', function(){
				var selectedRows = this.allergiesGridPanel.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length < 1)
					return;
				this.allergiesGridPanel.infoGrid.stopEditing();
				for(var i=0; i<selectedRows.length; i++){
					this.allergiesGridPanel.infoGrid.getStore().remove(selectedRows[i]); 
				}
				this.allergiesGridPanel.editBtn.disable();
	  			this.allergiesGridPanel.deleteBtn.disable();
			},this, null, mainThis.getPanel());
			
			Ext.ux.event.Broadcast.subscribe('editAllergy', function(){
				var selectedRows = this.allergiesGridPanel.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length <1)
					return;
	  			this.selectedItem ={
	  				code:selectedRows[0].data.allergyCode,
	  				description:selectedRows[0].data.allergyName
				}	
				
	  			this.allergiesCbx.setValue(selectedRows[0].data.allergyCode);
	  			this.fromDate.setValue(selectedRows[0].data.fromDate);
	  			this.toDate.setValue(selectedRows[0].data.toDate);
	  			
	  			this.allergiesGridPanel.editBtn.disable();
	  			this.allergiesGridPanel.deleteBtn.disable();
	  			
//				Ext.ux.event.Broadcast.publish('deleteAllergy');
			},this, null, mainThis.getPanel());
			
		},
		
		getPanel : function() {
			return this.mainPanel;
		},
		
		anyAllergiesSelected: function(){
			var count = this.allergiesGridPanel.infoGrid.getStore().data.getCount();
			if(count > 0 )
				return true;
			return false;
		},
		
		getInfoGrid: function(){
			return this.allergiesGridPanel;
		},
		
		loadData : function(config){
			if(config.mode == 'edit' && config.isPatientHaveAnyAllergies == true){
				// First show the grid				
				this.allergyDetailsInputPanel.show();
				this.allergyDetailsInputPanel.doLayout();

				this.allergiesGridPanel.infoGrid.getStore().removeAll();
				this.allergiesGridPanel.infoGrid.show();
				
				// Now load the allergies.
	    		var gridRows = config.patientAllergiesList;
				var record = this.allergiesGridPanel.infoGrid.getStore().recordType;
				if(gridRows!=null && gridRows.length > 0 ){
					for(var i = 0; i<gridRows.length; i++){
						var allergyRecord = new record({
							allergyName : gridRows[i].allergyDescrption,
							allergyCode : gridRows[i].allergyCode,
							fromDate : gridRows[i].fromDate,
							toDate : gridRows[i].toDate
						});
						 this.allergiesGridPanel.infoGrid.getStore().add(allergyRecord);
					}
				}
			}
		},
		
		addAllergy : function(){
			Ext.ux.event.Broadcast.publish('deleteAllergy');
			var allergiesGrid = this.allergiesGridPanel.infoGrid;
			var allergyRecord = allergiesGrid.getStore().recordType;
			
			var allergy = new allergyRecord({
				allergyName : this.selectedItem.description,
				allergyCode : this.selectedItem.code,
				fromDate : this.fromDate.getValue(),
				toDate :  this.toDate.getValue()
			});
			allergiesGrid.stopEditing();
			
			// Check for the duplicates
			var noOfRec = allergiesGrid.getStore().data.getCount();
			for(var i=0; i< noOfRec ; i++){
				var allergyData = allergiesGrid.getStore().getAt(i).data;
				if( allergyData.allergyCode == allergy.data.allergyCode ){
					
					if(allergyData.fromDate == allergy.data.fromDate){
						error( "Allergy " +"\""+ Ext.util.Format.uppercase(allergy.data.allergyName)+"\"" + 
							   " has already been selected..!", 
							   "Add allergy failed..!" );
						return;
					}else if(!Ext.isEmpty( allergyData.fromDate )){
						if( allergyData.fromDate.getDate() == allergy.data.fromDate.getDate() ){
							error( "Allergy " +"\""+ Ext.util.Format.uppercase(allergy.data.allergyName)+"\"" + 
								   " has already been selected..!", 
								   "Add allergy failed..!" );
							return;
						}					
					} 
				}
			}
			
  			allergiesGrid.getStore().insert(0,allergy);
  			// resetting the values in allergies details panel.
  			this.selectedItem = null;
  			this.fromDate.setValue( null );
  			this.fromDate.setMaxValue( null );
  			this.toDate.setValue( null );
  			this.toDate.setMinValue( null );
  			this.allergiesCbx.clearValue();
		}
});