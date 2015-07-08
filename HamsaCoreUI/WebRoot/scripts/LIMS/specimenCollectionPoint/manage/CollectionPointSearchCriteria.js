Ext.namespace("LIMS.specimenCollectionPoint.manage"); 

LIMS.specimenCollectionPoint.manage.CollectionPointSearchCriteria = Ext.extend(Ext.Panel,{
	monitorValid : true,
	initComponent : function(){

		this.collectionPointWidgets = new LIMS.specimenCollectionPoint.CollectionPointWidgets();
		
		this.associatedLabIdCbx = new Ext.form.OptComboBox({
			fieldLabel : limsMsg.associatedLab,
			hiddenName : 'associatedLab',
			store : loadLabStore(),
			mode:'local',
			displayField:'description',
			valueField:'code',
			triggerAction:'all',
			anchor:'80%',
			emptyText: limsMsg.selectMsg,
			forceSelection : true
		});
		
		this.collectionPointIdTxf = new Ext.form.TextField({
			name : 'collectionPointId',
			fieldLabel : limsMsg.collectionPointId,
			anchor : '80%'
		});	
		
		this.collectionPointNameTxf = new Ext.form.TextField({
			name : 'collectionPointName',
			fieldLabel : limsMsg.collectionPointName,
			anchor : '80%'
		});	

		this.areaCoveredTxf = new Ext.form.TextField({
			name : 'areaCovered',
			fieldLabel : limsMsg.areaCovered,
			anchor : '80%'
		});	

		this.cityTxf = new Ext.form.TextField({
			name : 'city',
			fieldLabel : limsMsg.city,
			anchor : '80%'
		});
		
	
		Ext.applyIf(this,{
			layout : 'column',
			labelWidth : 122,
			items : [
		         {
		        	 columnWidth : .50,
		        	 layout : 'form',
		        	 items : this.collectionPointIdTxf
		         },
		         {
		        	 columnWidth : .50,
		        	 layout : 'form',
		        	 items : this.collectionPointNameTxf
		         },
		         {
		        	 columnWidth : .50,
		        	 layout : 'form',
		        	 items : this.areaCoveredTxf
		         },
		         {
		        	 columnWidth : .50,
		        	 layout : 'form',
		        	 items : this.cityTxf
		         },
		         {
		        	 columnWidth : .50,
		        	 layout : 'form',
		        	 items : this.associatedLabIdCbx
		         }
			]
		});
	
		LIMS.specimenCollectionPoint.manage.CollectionPointSearchCriteria.superclass.initComponent.apply(this,arguments);
	}
});