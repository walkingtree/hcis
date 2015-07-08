Ext.namespace("IPD.doctorOrderList");

IPD.doctorOrderList.SearchDoctorOrder = Ext.extend(Object, {
	constructor : function(config) {
		var mainThis = this;
		this.infoGrid = new IPD.doctorOrderList.SearchDoctorOrderGrid();
		this.fromDate = new Ext.form.WTCDateField({
			fieldLabel: msg.orderDateForm,
			name: 'orderDateFrom',
	        anchor : '95%',
	        listeners:{
	        	change: function( date, newValue, oldValue ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.toDate.setMinValue(date.getValue());
		   			}
		   		}
	        }
		});
 		var searchBtn = new Ext.Button({
	    	text: 'Search',
	    	iconCls : 'search_btn',
	    	scope: this,
	    	handler: function() {
	    		if(this.searchDoctorOrderPanel.getForm().isValid()){
		    		var searchCriteria = this.searchDoctorOrderPanel.getForm().getValues();
		    		this.infoGrid.search(searchCriteria);
		    		this.fromDate.setMaxValue(null);
		    		this.toDate.setMinValue(null);
		    	} else {
		    		error("ENTER VALID DATA..!");
		    	}
	    	}
		});
		
		var buttonPanel = new Ext.Panel({
			buttonAlign:'right',
			border:false,
			autoHeight: true,
			header: false,
			buttons:[searchBtn,{
				xtype:'button',
				text:'Reset',
				iconCls : 'cancel_btn',
				scope:this,
				handler: function(){
					this.searchDoctorOrderPanel.getForm().reset();
					var searchCriteria = this.searchDoctorOrderPanel.getForm().getValues();
		    		this.infoGrid.search(searchCriteria);
				}
			}]
		});				  
		this.toDate = new Ext.form.WTCDateField({
			fieldLabel: msg.orderDateTo,
			name: 'orderDateTo',
	        anchor : '95%',
	        listeners:{
		   		change: function( date, newValue, oldValue  ){
		   			if(!Ext.isEmpty(date.getValue())){
		   				mainThis.fromDate.setMaxValue(date.getValue());
		   			}
		   		}
			}
		});
		this.admissionNbr = new Ext.form.NumberField({
	        fieldLabel: msg.admissionNumber,
	        name: 'admissionNbr',
	        anchor : '95%'
		});
		this.searchDoctorOrderPanel = new Ext.form.FormPanel({
			labelAlign:'left',
			width : '100%',
			autoHeight: true,
			border : false,
			items: [
					{
						layout : 'column',
				    	style: 'padding-top:10px',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth : 105
						},
						items: [
							{
								layout:'form',
								border:false,
								items:this.admissionNbr
							},{
								layout:'form',
								border:false,
								items:[
									{
								        fieldLabel: msg.patientid,
								        name: 'patientId',
								        xtype: 'numberfield',
								        anchor : '95%'
									}
								]
							},{
								layout:'form',
								border:false,
								items:[
									{
								        fieldLabel:msg.patientname,
								        name: 'patientName',
								        xtype: 'textfield',
								        anchor : '95%'
									}
								]
							}
					    ]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth : 105
						},
						items: [
							{
								layout:'form',
								border:false,
								items:[
									{
										xtype:'optcombo',
										fieldLabel:	msg.orderstatus,
										name:'status',
										mode:'local',
										store: loadDoctorOrderStatus(),
										triggerAction: 'all',
										valueField:'code',
										displayField:'description',
								        anchor : '95%',
								        forceSelection:true,
								        resizable:true
					   				}
								]
						    }, {
								layout:'form',
								border:false,
								items:[
									{
										xtype:'optcombo',
										fieldLabel:	msg.ordertype,
										name:'orderType',
										mode:'local',
										store: loadDoctorOrderType(),
										triggerAction: 'all',
										valueField:'code',
										displayField:'description',
								        anchor : '95%',
								        forceSelection:true,
								        resizable:true
					   				}
								]
						    }
					    ]
					},{
						layout : 'column',
						defaults: {
							border: false,
							layout: 'form',
							columnWidth : .33,
							labelWidth : 105
						},
						items: [
							{
								layout:'form',
								border:false,
								items:this.fromDate
							}, {
								layout:'form',
								border:false,
								items:this.toDate
							}
					    ]
					},
					buttonPanel,
					this.infoGrid.getPanel()]
		});
		
		this.searchDoctorOrderPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	},
	
	getPanel : function() {
//		Ext.ux.event.Broadcast.publish('loadDoctorOrderGrid');
		this.infoGrid.infoGrid.store.load({params:{start:0, limit:10}, arg:[null, null, null, null, null, null, null ]});
		return this.searchDoctorOrderPanel;
	},
	getFocus : function(){
		this.admissionNbr.focus();
	}
});