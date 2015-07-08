Ext.namespace("administration.referralManagement");

administration.referralManagement.ReferralCommisionConfigGridPanel = Ext.extend(Ext.Panel, {
   	  title : 'Commission configuration',
      layout : 'fit',
      border : false,
      frame : false,
      initComponent : function() {

			this.commTypesStore = getComisssionTypes();
			
			this.commissionValueNbf = new Ext.form.NumberField({
                allowBlank: false,
                allowNegative: false,
                listeners:{
                	blur : function( comp ){
                		if( !Ext.isEmpty( this.commissionTypeCbx.getValue() ) && this.commissionTypeCbx.getValue() == "PERCENTAGE"){
                			if( comp.getValue() > 100){
                				comp.reset();
                				comp.focus();
                			}
                		}
                	},
                	scope:this
                }
	        });
	        
	        this.commissionTypeCbx = new Ext.form.ComboBox({
		  		typeAhead: true,
				mode : 'local',
                triggerAction: 'all',
                store : this.commTypesStore,
				displayField : 'description',
				valueField : 'code',
				hiddenName : 'comissionType',
				forceSelection : true,
				lazyRender:true,
                listClass: 'x-combo-list-small',
                listeners:{
                	select : function( comp ){
                		if( !Ext.isEmpty( this.commissionValueNbf.getValue() ) && this.commissionValueNbf.getValue() > 100){
                			if( comp.getValue() == "PERCENTAGE"){
                				comp.reset();
                				comp.focus();
                			}
                		}
                	},
                	scope:this
                }
            });
            
            this.record = Ext.data.Record.create([ 
			    {name: 'code', type: 'string'},
			    {name: 'description', type: 'string'},
			    {name: 'comissionValue', type: 'float'},
			    {name: 'comissionType'},
			    {name :'commissionTypeDesc'}
			]);
			
			this.referralCommProcessesStore = new Ext.data.Store({
				    proxy: new Ext.data.DWRProxy(ReferralManager.getReferralCommissionProcesses, true),
				    reader: new Ext.data.ListRangeReader(
				    	{totalProperty:'totalSize'}, this.record),
				    remoteSort: true
			});
		
            this.gridColumns = [
            	 {
	            	  id : 'code',
	                  header :"Process code",
	                  dataIndex :'code',
	                  hidden : true
	            }, {
	                  header :"Commission process",
	                  dataIndex :'description',
	                  width : 150
	            }, {
	                header: 'Comission value ',
	                dataIndex: 'comissionValue',
	                width: 75,
	                align: 'right',
	                renderer : this.inrMoney,
	                editor: this.commissionValueNbf
	                
	            }, {
	                  header :"Commission type ind",
	                  dataIndex :'comissionType',
	                  width : 75,
					  editor: this.commissionTypeCbx,
	                  renderer : function(v, m, r) {
						if(Ext.isEmpty( r.data.commissionTypeDesc )){
							return this.editor.lastSelectionText;
						}else{
							return r.data.commissionTypeDesc;
						}
					}
	            }
		    ];
            
            this.gridPnl = new Ext.grid.EditorGridPanel({
			  border : false,
			  height : 100,
			  frame : false,
              stripeRows :true,
              autoScroll :true,
              remoteSort :true,
              store : this.referralCommProcessesStore,
              clicksToEdit: 1,
              stripeRows: false,
              viewConfig : {
              forceFit :true
              },
              columns : this.gridColumns,
              sortInfo: {field: 'description', direction: 'ASC'}
            });

        Ext.applyIf(this, {
    	  items: [
               {
                     columnWidth : 1,
                     items :[this.gridPnl]
               }
			]            
        });

        administration.referralManagement.ReferralCommisionConfigGridPanel.superclass.initComponent.apply(this, arguments);
      },
      
      getGrid : function(){
      	return this.gridPnl;
      },
      
      getData : function(){
		var tmpList = new Array();
		var storeValues = this.gridPnl.getStore().data.items;
		for(var i =0; i<storeValues.length;i++){
			var referralCommissionBM = {
				entityType : {code : storeValues[i].data.code , description : storeValues[i].data.description},
				commissionTypeInd : storeValues[i].data.comissionType,
				commissionValue : storeValues[i].data.comissionValue,
				effectiveFromDate : storeValues[i].data.fromDateTime,
				effectiveToDate : storeValues[i].data.toDateTime
			}
			tmpList.push(referralCommissionBM);
		}
		return tmpList;
      },      
      loadGridStore: function( mode ){
      		if(mode != configs.edit){
      			this.referralCommProcessesStore.load({params:{start:0, limit:99}, arg:[]});
      		}
      	
      },
      inrMoney : function(v){
//      	if(Ext.isEmpty(v)){
//      		v = 0.00;
//      	}
            v = (Math.round((v-0)*100))/100;
            v = (v == Math.floor(v)) ? v + ".00" : ((v*10 == Math.floor(v*10)) ? v + "0" : v);
            v = String(v);
            var ps = v.split('.');
            var whole = ps[0];
            var sub = ps[1] ? '.'+ ps[1] : '.00';
            //var r = /(\d+)(\d{3})/ ;
            v = whole + sub;
            if(v.charAt(0) == '-'){
                return '- ' + v.substr(1);
            }
            return v;
        }
});

Ext.reg('refferal-comission-config-panel', administration.referralManagement.ReferralCommisionConfigGridPanel);
