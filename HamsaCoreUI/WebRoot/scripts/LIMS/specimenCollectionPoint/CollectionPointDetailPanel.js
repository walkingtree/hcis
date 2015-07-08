Ext.namespace("LIMS.specimenCollectionPoint"); 

LIMS.specimenCollectionPoint.CollectionPointDetailPanel = Ext.extend(Ext.form.FormPanel,{
	monitorValid : true,
	initComponent : function(){

		this.collectionPointWidgets = new LIMS.specimenCollectionPoint.CollectionPointWidgets();
		
		this.collectionPointPanel = new Ext.Panel({
			frame :false,
			border : false,
			layout : 'column',
			labelWidth : 122,
			items :[
		        {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getCollectionPointIdTxf()
			    },
			    {
			    	columnWidth : .5,
			    	layout : 'form',
			    	items : this.collectionPointWidgets.getCollectionPointNameTxf()
			    },
			    {
			    	columnWidth : 1,
			    	layout : 'form',
			    	items : this.collectionPointWidgets.getAreaCoveredTxf()
			    } 
	        ] 
		});
	
		this.contactDetailFieldSet = new Ext.form.FieldSet({
			title : limsMsg.contactDetail,
			draggable :false,
			layout : 'column',
			frame : false,
			border : true,
			labelWidth : 110,
			items : [
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getContactPersonNameTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getLocalityTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getCountryCbx()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getStateCbx()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getStreetTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getCityTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getPhoneNumberTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getMobileNumberField()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getEmailIdTxf()
			    },   
			    {     
				    columnWidth : .5,
				    layout : 'form',
				    items : this.collectionPointWidgets.getFaxNumberField()
			    }   
			]
		});
		
		Ext.applyIf(this,{
			layout : 'column',
			items : [
			    this.collectionPointPanel,
			    this.contactDetailFieldSet
			]
		});
	
		LIMS.specimenCollectionPoint.CollectionPointDetailPanel.superclass.initComponent.apply(this,arguments);
	},
	
	getCollectionpointWidgets : function(){
		return this.collectionPointWidgets;
	},
	reset : function(){
		this.getForm().reset();
	}
});