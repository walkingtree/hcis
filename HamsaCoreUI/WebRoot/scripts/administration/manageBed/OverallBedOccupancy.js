Ext.namespace("administration.manageBed");

administration.manageBed.OverallBedOccupancy = Ext.extend(Object, {
	constructor : function(config) {
	
		var occupancyCardPnl = {
				id: 'occupancyCardPnl',
				layout: 'card',
				activeItem: 0,
				autoScroll: true,
				items:[{}]
		};
		
		var responseHandler = function(listRange, orderBy){
					var BedDetailRecord = Ext.data.Record.create([
					{ name: 'bedNumber', mapping:'bedNumber' },
					{ name: 'nursingUnit', mapping:'nursingUnit' },
			        { name: 'bedStatus', mapping:'bedStatus'},
			        { name: 'bedType', mapping: 'bedType'},
			        { name: 'roomNbr', mapping: 'roomNbr'},
			        { name: 'floorNbr', mapping: 'floorNbr'}
			        ]);
			        
					var tmpPnl = {
						layout: 'column',
						//height: '100%',
						autoHeight: true,
						id: 'occupancy-col-panel',
						style:'padding-top:10px',//added
						layoutConfig: {
							columns: 6
						}
					};
				
					var cardPnl = Ext.getCmp('occupancyCardPnl');
					if (!Ext.isEmpty(cardPnl.items)) cardPnl.remove('occupancy-col-panel');
					
					cardPnl.add(tmpPnl);
					cardPnl.doLayout();

					var tempUnit = '';
					var unitWiseBedMap = Array();
					var bedArray = Array();
					for(var i = 0; i < listRange.data.length; i++) {
						var tempOrderByVal = listRange.data[i][orderBy].description ? listRange.data[i][orderBy].description : listRange.data[i][orderBy];
						if (Ext.isEmpty(tempUnit)) tempUnit = tempOrderByVal;
						
						if (tempUnit !== tempOrderByVal){
							unitWiseBedMap[tempUnit] = bedArray;
							tempUnit = tempOrderByVal;
							bedArray = Array();
						} 
						
						bedArray.push({bedNumber: listRange.data[i].bedNumber, bedStatus: listRange.data[i].bedStatus.code});
						unitWiseBedMap[tempUnit] = bedArray;
					}
					
					var totalAvailable = 0;
					var totalOccupied = 0;
					var totalDefective = 0;
					var totalCleaning = 0;
					var totalDirty = 0;
					var totalRetired =0;
					
					if (true || unitWiseBedMap.length > 0) {
						for (var key in unitWiseBedMap) {
							if (key === "remove" || key === "contains" || key === "removeItem" ) continue;
							
							var columns = [{
									header : key,
									dataIndex : 'bedNumber',
									width : 100,
									renderer : function(value, metadata, record, rowIndex, colIndex, store) {
										if (record.data.bedStatus === "AVAILABLE") {
											metadata.attr = 'style="background-color:#99FF00;"';
											totalAvailable += 1;
										}
										if (record.data.bedStatus === "OCCUPIED") {
											metadata.attr = 'style="background-color:#FFFF33;"';
											totalOccupied += 1;
										}
										if (record.data.bedStatus === "DEFECTIVE") {
											metadata.attr = 'style="background-color:#FF3300;color:white;"';
											totalDefective += 1;
										}
										if (record.data.bedStatus === "CLEANING") {
											metadata.attr = 'style="background-color:#99FFCC;"';
											totalCleaning += 1;
										}
										if (record.data.bedStatus === "PENDING_CLEANING") {
											metadata.attr = 'style="background-color:#3399FF;"';
											totalDirty += 1;
										}
										if (record.data.bedStatus === "RETIRED") {
											metadata.attr = 'style="background-color:#C0C0C0;"';
											totalRetired += 1;
										}
										
										var label1 = Ext.getCmp('bed-occupied-label');
										var label2 = Ext.getCmp('bed-available-label');
										var label3 = Ext.getCmp('bed-defective-label');
										var label4 = Ext.getCmp('bed-cleaning-label');
										var label5 = Ext.getCmp('bed-dirty-label');
										var label6 = Ext.getCmp('bed-retired-label');
										
										label1.setText(label1.initialConfig.text+"("+totalOccupied+")");
										label2.setText(label2.initialConfig.text+"("+totalAvailable+")");
										label3.setText(label3.initialConfig.text+"("+totalDefective+")");
										label4.setText(label4.initialConfig.text+"("+totalCleaning+")");
										label5.setText(label5.initialConfig.text+"("+totalDirty+")");
										label6.setText(label6.initialConfig.text+"("+totalRetired+")");
										return value;
									}
								}];
							var dataStore = new Ext.data.Store({
							});
							
							var bedGrid = new Ext.grid.GridPanel({
									frame : true,
									stripeRows : true,
									autoHeight : true,
									enableHdMenu: false,
									width: 100,
									border : false,
									store : dataStore,
									columns : columns,
									listeners: {
										mouseover: function(eventObj) {
											var x = 100;
										}
									}
							});
							
							var pnl = Ext.getCmp('occupancy-col-panel');
							pnl.add(bedGrid);
							
							var bedsList = unitWiseBedMap[key];
							
							for (var i = 0; i < bedsList.length; i++) {
								var tmpRec = new BedDetailRecord({bedNumber: bedsList[i].bedNumber, bedStatus: bedsList[i].bedStatus});
								dataStore.add(tmpRec);
							}
							
						}
					}
					
					cardPnl.layout.setActiveItem(1);
					cardPnl.layout.activeItem.doLayout();
				};
		
		this.radioCheckHandler = function(thisRadio, checked) {
			var groupVal = thisRadio.getGroupValue();
			if (groupVal === "1") {
				BedManager.findBeds(null, null,null, null, null,null, null,null, null, null,null,0, 999999, "UNIT ASC", function(listRange){responseHandler(listRange, 'nursingUnit')});
			}
			if (groupVal === "2") {
				BedManager.findBeds(null, null,null, null, null,null, null,null, null, null,null,0, 999999, "BED_TYPE ASC", function(listRange){responseHandler(listRange, 'bedType')});
			}
			if (groupVal === "3") {
				BedManager.findBeds(null, null,null, null, null,null, null,null, null, null,null,0, 999999, "FLOOR ASC", function(listRange){responseHandler(listRange, 'floorNbr')});
			}
		};
		
		var legendPanel = new Ext.Panel({
			frame: true,
			autoWidth: true,
			layout: 'column',
			layoutConfig: {columns: 10},
			defaults: {anchor: '80%', style:"padding-left:4px;padding-right:4px;"},
			items: [{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-occupied"
			}, {
				xtype: 'label',
				text: 'Occupied',
				id: 'bed-occupied-label'
			},{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-available"
			}, {
				xtype: 'label',
				text: 'Available',
				id: 'bed-available-label'
			},{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-defective"
			}, {
				xtype: 'label',
				text: 'Defective',
				id: 'bed-defective-label'
			},{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-cleaning"
			}, {
				xtype: 'label',
				text: 'Being Cleaned',
				id: 'bed-cleaning-label'
			},{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-pendingcleaning"
			}, {
				xtype: 'label',
				text: 'Pending Cleaning',
				id: 'bed-dirty-label'
			},{
				xtype: 'textfield',
				height: 20,
				width: 20,
				readOnly: true,
				cls: "bedlegend-retried"
			}, {
				xtype: 'label',
				text: 'Retired',
				id: 'bed-retired-label'
			}]
		});
		
		this.bedOccupancyPanel = new Ext.form.FormPanel({
//			style:'padding:5px',
			autoHeight:true,
			border : false,
			layout: 'column',
			//layoutConfig: {columns: 2},
			items:[
				{
				layout: 'form',
				border: false,
				columnWidth: 0.85,
				items: legendPanel
				},
				{
				layout: 'form',
				border: false,
				style:'padding-top:5px',
				columnWidth: .35,
				items: [{
                xtype: 'radiogroup',
                hideLabel: true,
                items: [
                    {boxLabel: 'Nursing Unit wise', name: 'boGroupCriteria', inputValue: 1, checked: true,
                    listeners: {
                    	check: this.radioCheckHandler,
                    	render: this.radioCheckHandler
                    }, scope: this},
                    {boxLabel: 'Bed Type wise', name: 'boGroupCriteria', inputValue: 2,
                    listeners: {
                    	check: this.radioCheckHandler
                    }, scope: this},
                    {boxLabel: 'Floor wise', name: 'boGroupCriteria', inputValue: 3,
                    listeners: {
                    	check: this.radioCheckHandler
                    }, scope: this}
                ]
            }]
			}, {
				layout: 'form',
				border: false,
				columnWidth: 1.0,
				items: occupancyCardPnl
			}]
		});
		
		this.bedOccupancyPanel.on('destroy',function( comp ){
			InstanceFactory.removeInstance( comp.windowCode );
		},this);
	
	},
	getPanel : function() {
		return this.bedOccupancyPanel;
	}
});