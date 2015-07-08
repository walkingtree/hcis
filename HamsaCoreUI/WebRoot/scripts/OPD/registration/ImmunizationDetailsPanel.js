Ext.namespace("OPD.registration");

OPD.registration.ImmunizationDetailsPanel = Ext.extend(Object,{
		constructor : function(config) {

			Ext.QuickTips.init();
		 	if( Ext.isEmpty(config) ){
		 		config = { }
		 	}

			var mainThis = this;
			
			this.selectedItem;

			this.immunizationCbx = new Ext.form.ComboBox({
				mode:'local',
				store:  loadImmunizations(),
				forceSelection:true,
				triggerAction:'all',
				displayField:'description',
				valueField:'code',
				fieldLabel:msg.immunizationname,
				hiddenName:'immunizationName',
				anchor:'98%',
				emptyText:"Select...",
				listeners : {
					select : function(combo, record, index) {
						mainThis.selectedItem = record.data;
					}
				}
			});
			
			this.vaccinationDate = new Ext.form.WTCDateField({
				name : 'vaccinationDate',
				fieldLabel : msg.date,
				emptyText : "dd/mm/yyyy",
				anchor : '98%'
			});
			
			this.addBtn = new Ext.Button({
				text : msg.add,
	   			iconCls:'add_btn',
	   			tooltip : msg.addimmunization,
	   			scope:this,
	   			handler:function(){
					if( null == mainThis.selectedItem){
						info("Please select an allergy to add");
						return;
					}
	   				this.addImmunization();
	   			}
			});
		
			this.immunizationDetailsInputPanel = new Ext.Panel( {
				layout :'column',
				autoHeight : true,
				width:'70%',
				hidden :true,
				frame:true,
				title:"Enter immunization details",
				border: true,
				defaults : {
					labelAlign :'left'
				},
				items : [ 
					{
						layout :'form',
						labelWidth:125,
						columnWidth : .6,
						items :this.immunizationCbx
					}, {
						layout :'form',
						labelWidth: 55,
						columnWidth : .33,
						items :this.vaccinationDate
					}, {
						layout :'form',
						style:'padding-left:25px;',
						items :this.addBtn
					}
				]
			});
			
			this.immunizationGridPanel = new OPD.registration.ImmunizationGridPanel();

			this.knownImmunizationsChbx = new Ext.form.Checkbox({
				boxLabel: "Known immunizations ?",
				labelSeparator:'',
				name:'knownimmunizations',
				checked : (config.mode == 'edit' && config.isPatientHaveAnyImmunizations == true) ? true : false
			});

			this.mainPanel =new Ext.form.FormPanel({
					frame :false,
					border :true,
					autoHeight :true,
					autoScroll :true,
					items : [ {
								labelWidth : 0.1,
								items:[this.knownImmunizationsChbx]
							  },
							  this.immunizationDetailsInputPanel,
							  this.immunizationGridPanel.getPanel()]
			});

			this.knownImmunizationsChbx.addListener('check',
				function(comp, checked) {
					if (checked) {
						mainThis.immunizationDetailsInputPanel.show();
						mainThis.immunizationDetailsInputPanel.doLayout();
	
						mainThis.immunizationGridPanel.infoGrid.getStore().removeAll();
						mainThis.immunizationGridPanel.infoGrid.show();
					} else {
						mainThis.immunizationGridPanel.infoGrid.hide();
						mainThis.immunizationDetailsInputPanel.hide();
					}
				}
			);
			
			Ext.ux.event.Broadcast.subscribe('deleteImmunization', function(){
				var selectedRows = this.immunizationGridPanel.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length < 1)
					return;
				this.immunizationGridPanel.infoGrid.stopEditing();
				for(var i=0; i<selectedRows.length; i++){
					this.immunizationGridPanel.infoGrid.getStore().remove(selectedRows[i]); 
				}
			},this, null, mainThis.getPanel());
			
			Ext.ux.event.Broadcast.subscribe('editImmunization', function(){
				var selectedRows = this.immunizationGridPanel.infoGrid.getSelectionModel().getSelections();
				if(selectedRows.length <1)
					return;
					
	  			this.selectedItem ={
	  				code:selectedRows[0].data.immunizationCode,
	  				description:selectedRows[0].data.immunizationName
				}	
	  			this.immunizationCbx.setValue(selectedRows[0].data.immunizationCode);
	  			this.vaccinationDate.setValue(selectedRows[0].data.vaccinationDate);
	  			
	  			this.immunizationGridPanel.editBtn.disable();
	  			this.immunizationGridPanel.deleteBtn.disable();
	  			
				Ext.ux.event.Broadcast.publish('deleteImmunization');
				
			},this, null, mainThis.getPanel());
			
		},
		
		anyImmunizationsSelected : function() {
			var count = this.immunizationGridPanel.infoGrid.getStore().data.getCount();
			if(count > 0 )
				return true;
			return false;
		},
		
		getPanel: function(){
			return this.mainPanel;
		},
		
		getInfoGrid: function(){
			return this.immunizationGridPanel;
		},
		
		addImmunization : function(){
			var immunizationGrid = this.immunizationGridPanel.infoGrid;
			var immunizationRecord = immunizationGrid.getStore().recordType;
			var immunization = new immunizationRecord({
				immunizationName : this.selectedItem.description,
				immunizationCode : this.selectedItem.code,
				vaccinationDate : this.vaccinationDate.getValue()
			});
			immunizationGrid.stopEditing();

			// Check for the duplicates
			var noOfRec = immunizationGrid.getStore().data.getCount();
			for(var i=0; i< noOfRec ; i++){
				if(immunizationGrid.getStore().getAt(i).data.immunizationCode == immunization.data.immunizationCode){
					info(Ext.util.Format.uppercase(immunization.data.immunizationName) + " has been already selected");
					return;
				}
			}

  			immunizationGrid.getStore().insert(0,immunization);
  			// resetting the values in immunization details panel.
  			this.selectedItem = null;
  			this.immunizationCbx.setValue("Select...");
		},
		
		loadData : function(config){
			if(config.mode == 'edit' && config.isPatientHaveAnyImmunizations == true){
				// First show the grid				
				this.immunizationDetailsInputPanel.show();
				this.immunizationDetailsInputPanel.doLayout();

				this.immunizationGridPanel.infoGrid.getStore().removeAll();
				this.immunizationGridPanel.infoGrid.show();
				
				// Now load the immunzations.
	    		var gridRows = config.patientImmunizationsList;
				var record = this.immunizationGridPanel.infoGrid.getStore().recordType;
				if(gridRows!=null && gridRows.length>0 ){
					for(var i = 0; i<gridRows.length; i++){
						var immunizationRecord = new record({
							immunizationName : gridRows[i].immunizationDescrption,
							immunizationCode : gridRows[i].immunizationCode,
							vaccinationDate : gridRows[i].vaccinationDate
						});
						 this.immunizationGridPanel.infoGrid.getStore().add(immunizationRecord);
					}
				}
			}
		}
});