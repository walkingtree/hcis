Ext.namespace("administration.service_group_package.priceUpdate");

administration.service_group_package.priceUpdate.PriceUpdateSearchPanel = Ext.extend(Ext.Panel, {
//			title : 'Search',
			border:false,
			layout : 'column',
			border : false,
			frame:false,
			autoHeight:true,
			labelWidth:70,
//			width:'70%',
											
		initComponent : function() {
	
			this.widgets = new administration.service_group_package.priceUpdate.Widgets();
			
			this.widgets.getServiceNameTxf().anchor = '30%';
			Ext.applyIf(this, {
	            	items : [ 
	                        {
				    			columnWidth:.5,
				    			
				            	layout : 'form',
//				            	frame : true,
				            	align:'left',
				            	labelWidth:.001,
				            	items:[new Ext.form.RadioGroup({
	    			        			name:'rdGrpService',
	    			        			columns: [80, 100,100],
	    			               		items: [
	    			                            {boxLabel: "All", name: 'searchServBy', inputValue: 1,checked: true},
	    			                            {boxLabel: "Department", name: 'searchServBy', inputValue: 2},
	    			                            {boxLabel: "Service", name: 'searchServBy', inputValue: 2}
	    			               			   ]
	    			            		}) 
	            	       
				            	]
	                        },

//	                        {
//								columnWidth : 1,
//								layout : 'form',
//								items : [ this.widgets.getServiceNameTxf() ]
//							},
								
							]
						});
			administration.service_group_package.priceUpdate.PriceUpdateSearchPanel.superclass.initComponent.apply(this, arguments);
		},
	
	getButtonPanel : function(){
		return this.buttonsPanel;
	},
	
	getValues : function(){
		return this.getForm().getValues();
	},
	
	getReset : function(){
		return this.getForm().reset();
	}
	
	
});