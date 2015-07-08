Ext.namespace("administration.service_group_package.priceUpdate");

administration.service_group_package.priceUpdate.Widgets = Ext.extend(Object,{
	constructor: function(){
		
		this.amountNbf = new Ext.form.NumberField({
			name: "amount",
			fieldLabel : servicePriceMsg.lblAmount,
			anchor:'98%',
			required:true,
			allowBlank:false,
			minValue:0
		});
		
		
	      this.prcntAbsCombo = new wtccomponents.WTCComboBox({
			store:new Ext.data.SimpleStore({
		   		fields: ['code', 'description'],
				data : [
					['P',"Percent"],
					['A',"Absolute"]
				]
		  }),
			hiddenName:'pectAbsInd',
			fieldLabel : servicePriceMsg.lblPercentOrAbs,
			anchor:'98%',
			required:true,
			allowBlank:false
		});
	      
	      var minDate = new Date();
	      minDate.setDate( minDate.getDate() - 1);
	      
			this.effectiveFromDtFld = new Ext.form.WTCDateField({
				fieldLabel:svcAndGrpAndPkgMsg.effectiveFrom,
				name:'effectiveFromDate',
				anchor:'98%',
				required:true,
				allowBlank:false,
				value: new Date(),
				emptyText: 'dd/mm/YYYY',
				minValue: minDate
			});
			
			this.effectiveToDtFld = new Ext.form.WTCDateField({
				fieldLabel:svcAndGrpAndPkgMsg.effectiveTo,
				name:'effectiveToDate',
				anchor:'70%',
				emptyText: 'dd/mm/YYYY'
			});
			
			this.serviceNameTxf = new Ext.form.TextField({
				fieldLabel:svcAndGrpAndPkgMsg.name,
				name:'serviceName',
				anchor:'90%'
			});
			
			this.serviceSearchRdGrp = new Ext.form.RadioGroup({
    			name:'rdGrpService',
    			columns: [80, 100,100],
           		items: [
                        {boxLabel: servicePriceMsg.lblAll, name: 'searchServBy', inputValue: 'All',checked: true},
                        {boxLabel: servicePriceMsg.lblDepartment, name: 'searchServBy', inputValue: 'DEPARTMENT'},
                        {boxLabel: servicePriceMsg.lblService, name: 'searchServBy', inputValue: 'SERVICE'}
           			   ]
        		}) ;
			

			this.departmentCombo = new Ext.form.OptComboBox({
				fieldLabel:svcAndGrpAndPkgMsg.department,
				mode:'local',
				store: loadDepartmentsCbx(),
				displayField:'description',
				valueField:'code',
				hiddenName:'department'
			});
			
	       this.applyBtn = new Ext.Button({
		        text: 'Preview new price',
		        scope : this,
				iconCls : 'accept-icon'
	        });

	       this.saveBtn = new Ext.Button({
		        text: 'Save',
		        scope : this,
				iconCls : 'save_btn',
		        disabled : true
	        });
	},
	
	getAmountNbf : function(){
		return this.amountNbf;
	},
	
	getPrcntAbsCombo : function(){
		return this.prcntAbsCombo;
	},
	getEffectiveFromDtFld: function(){
		return this.effectiveFromDtFld;
	}, 
	getEffectiveToDtFld: function(){
		return this.effectiveToDtFld;
	},
	
	getServiceNameTxf : function(){
		return this.serviceNameTxf;
	},
	getServiceSearchRdGrp : function(){
		return this.serviceSearchRdGrp;
	},
	
	getDepartmentCombo: function(){
		return this.departmentCombo;
	},
	
	getNewDepartmentCombo: function(){

		return new Ext.form.OptComboBox({
					fieldLabel:svcAndGrpAndPkgMsg.department,
					mode:'local',
					store: loadDepartmentsCbx(),
					displayField:'description',
					valueField:'code',
					triggerAction: 'all',
					hiddenName:'department'
				});
	},
	
	getNewServiceNameTxf : function(){
		return new Ext.form.TextField({
			fieldLabel:svcAndGrpAndPkgMsg.name,
			name:'serviceName'
		});
	},
	
	
	getApplyBtn : function(){
		
		return this.applyBtn;
	},
	
	getSaveBtn : function(){
		
		return this.saveBtn;
	}
	
});
