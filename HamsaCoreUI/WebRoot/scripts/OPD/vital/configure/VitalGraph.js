Ext.namespace("OPD.vital.configure");

/** 
 *  This will generate vitalGraph.
 */

OPD.vital.configure.VitalGraph = Ext.extend(Ext.Panel,{
	initComponent : function(){
		this.patientId = null;
		this.referenceNumber = null;
		this.referenceType = null;
		this.fields = new Array();
		this.data = new Array();
		this.record = null;
		this.graphForItems = {};
		this.colorForItem = {};
		
		this.colorForItem["TEMPERATURE"] = "CC0000";
		this.colorForItem["PULSE"] = "#800080";
        this.colorForItem["BLOOD_PRESSURE"] = "#008000"
		
		this.checkBoxList = new Array();
		this.itemList = new Array();
		this.checkBoxPanel = null;
		this.graphForItemsFlag = true;
		this.series = new Array();
		this.dataList = null;
		this.formatForXAxis = null;
		this.testPanel = new Ext.Panel({
			frame : true,
			border : false
		});
		
		this.reader = new Ext.data.ArrayReader({},this.record);
		this.store = new Ext.data.ArrayStore({
			fields:this.fields,
			data : this.data,
			reader : this.reader
		});
		
		this.printBtn = new Ext.Button({
			text: "Print",
			scope : this,
			iconCls : 'print_btn',
			disabled : false
		});
		
		this.vitalGraph = new Ext.chart.LineChart({
			store : this.store,
			xField : vitalMsg.TIME,
			series:this.series,
			height : 200,
			extraStyle: {
				legend: {
					display: 'right'
				}
			}
		});
		
		this.vitalGraphPanel = new Ext.Panel({
			frame : false,
			border : false,
			items : [this.testPanel,this.vitalGraph]
		});
		this.prepareStoreData(this.initialConfig.dataList);
//		this.setData(this.initialConfig.dataList);
		this.fromDate = this.generateField({widgetName : "fromDate",widgetLabel : "From date",widgetType :"WTCDateField"});
		this.toDate = this.generateField({widgetName : "toDate",widgetLabel : "To date",widgetType :"WTCDateField",widgetValue : ""});
		this.toDate.setValue( null );
		this.itemList.push({border : false,layout : 'form',columnWidth : .3,items:this.fromDate});
		this.itemList.push({border : false,layout : 'form',columnWidth : .3,items : this.toDate});
		
		this.on('render',function(){
//			if( !Ext.isEmpty(this.record)){
				this.checkBoxPanel = this.insertPanelWithItemList(0, this.checkBoxList );
				this.datePanel = this.insertPanelWithItemList(1, this.itemList);
//				this.addButton(this.datePanel, this.printBtn);
//			}
		},this);
		
		this.printBtn.on("click",function(){
			this.printBtnClicked();
		},this);
		
		this.fromDate.on('select',function(thisField , newValue , oldValue){
			var mainThis = this;
			var toDate = null;
			if(!Ext.isEmpty(mainThis.toDate.getValue())){
				toDate = mainThis.toDate.getValue();
			}
			VitalManager.getPatientVitalDetails(mainThis.referenceNumber, mainThis.referenceType, thisField.getValue().clearTime(), toDate,function( vitalFieldBMList ){
				mainThis.reset();
				mainThis.vitalGraph.store.removeAll();
				mainThis.setData( vitalFieldBMList );
			});
		},this);
		this.toDate.on('select',function(thisField , newValue , oldValue){
			var mainThis = this;
			var fromDate = null;
			if(!Ext.isEmpty(mainThis.fromDate.getValue())){
				fromDate = mainThis.fromDate.getValue();
			}
			VitalManager.getPatientVitalDetails(mainThis.referenceNumber, mainThis.referenceType, fromDate.clearTime(), thisField.getValue(),function( vitalFieldBMList ){
				mainThis.reset();
				mainThis.vitalGraph.store.removeAll();
				mainThis.setData( vitalFieldBMList );
			});
			
		},this);
		
		Ext.applyIf(this, {
			border : false,
	        items:[ 
	            this.vitalGraphPanel
	        ] 
	    });
			
		OPD.vital.configure.VitalGraph.superclass.initComponent.apply(this,arguments);
	},
	
	// This method will set Data into the graph.
	
	setData : function( dataList ){
		if( !Ext.isEmpty( this.fromDate)){
			var toDate = null;
			if( !Ext.isEmpty( this.toDate )){
				if( this.toDate.getValue() != ""){
					toDate = this.toDate.getValue().format('Y-m-d H:i:s');
				}
			}
			if( this.fromDate.getValue().format('Y-m-d H:i:s') === new Date().format('Y-m-d H:i:s')
				|| this.fromDate.getValue().format('Y-m-d H:i:s') === toDate){
				this.formatForXAxis = "g:i A";
			}
		}

		if( !Ext.isEmpty( dataList )){
			this.dataList = dataList;
			var recordType = this.vitalGraph.store.recordType; 
			this.fields.push("TIME");
			var dataObject = {};
			var recordConfig = new Array();
			var temp = dataList[0].forTime;
			dataObject["TIME"] = !Ext.isEmpty( this.formatForXAxis ) ? dataList[0].forTime.format(this.formatForXAxis) : dataList[0].forTime.format('Y-m-d H:i:s');
			for( var i=0 ; i < dataList.length ; i++){
				if( temp.format('G') === dataList[i].forTime.format('G')
						&& temp.format('i') === dataList[i].forTime.format('i')){
//					if( temp.format('Y-m-d H:i:s') === dataList[0].forTime.format('Y-m-d H:i:s')){
//						if( dataList[i].fieldType === "number"){
////								if( this.graphForItemsFlag ){
////									this.graphForItems[dataList[i].code] = true;
////								}
//								if( this.graphForItems[dataList[i].code]){
////									this[dataList[i].code.toLowerCase()+"Checkbox"] = new Ext.form.Checkbox({
////							        	name: dataList[i].code,
////							        	checked : true,
////							        	boxLabel : dataList[i].name
////								 	});
////									
////									this.onChecked( this[dataList[i].code.toLowerCase()+"Checkbox"] );
////									
////									this.checkBoxList.push({
////										border : false,
////										columnWidth : .3,
////										layout : 'form',
////										items:this[dataList[i].code.toLowerCase()+"Checkbox"]
////									});
////	
////									this.series.push({yField : dataList[i].code,displayName : dataList[i].name});
////									this.fields.push(dataList[i].code);
////									recordConfig.push({name : dataList[i].code});
//							}	
//						}
//					}
	// This is checking that this field is type of number and for this check should be checked then only
	//it will added to the graph. 			
					if( dataList[i].fieldType === "number" && this.graphForItems[dataList[i].code]){
						dataObject[dataList[i].code] = parseInt(dataList[i].value);
					}
					
	// This is checking that dataList[i] is the last element in the list.				
					if( i === dataList.length -1 ){
						if( dataList[i].fieldType === "number" && this.graphForItems[dataList[i].code]){
							dataObject[dataList[i].code] = parseInt(dataList[i].value);
						}
						this.data.push(dataObject);
						this.vitalGraph.store.add( new recordType( dataObject ));
					}
				}
				else{
					this.data.push(dataObject);
					this.vitalGraph.store.add( new recordType( dataObject ));
					dataObject = {};
					temp = dataList[i].forTime;
					dataObject["TIME"]= !Ext.isEmpty( this.formatForXAxis)? dataList[i].forTime.format(this.formatForXAxis) : dataList[i].forTime.format('Y-m-d H:i:s');
					if( this.graphForItems[dataList[i].code]){
						dataObject[dataList[i].code] = parseInt( dataList[i].value );
					}
				}			
			}
			this.graphForItemsFlag = false;
			this.data.push(dataObject);
//			this.record = new Ext.data.Record.create( recordConfig );
		}
				
	},
	insertPanelWithItemList : function(indexNbr, itemList ){
		this.panelWithItemList = new Ext.Panel({
			border : false,
			frame : false,
			layout : 'column',
			items : itemList,
			buttons : new Array()
		});
		this.testPanel.insert(indexNbr,this.panelWithItemList);
		return this.panelWithItemList;
	},
	
	onChecked : function( checkBox ){
		checkBox.on('check',function(thisCheckBox, isChecked){
			if( isChecked){
				if( !this.graphForItems[checkBox.getName()]){
					this.graphForItems[checkBox.getName()] = true;
					this.reset();
					this.vitalGraph.store.removeAll();
					this.setData(this.dataList);
				}
			}
			else{
				this.graphForItems[checkBox.getName()] = false;
				this.reset();
				this.vitalGraph.store.removeAll();
				this.setData(this.dataList);
//				this.vitalGraph.store.add( this.record );
			}
		},this)
	},
	reset : function(){
		this.fields = new Array();
		this.data = new Array();
		this.record = null;
		this.checkBoxList = new Array();
		this.checkBoxPanel = null;
		this.series = new Array();
		this.formatForXAxis = null;
	},
	
	// This method will be responsible for the generating the field
	
	generateField : function( configOption ){
		configOption
		this[configOption.widgetName+"Field"] = new Ext["form"][configOption.widgetType]({
			name : configOption.widgetName,
			fieldLabel : configOption.widgetLabel,
			anchor : configOption.widgetAnchor || '90%',
			value : Ext.isEmpty(configOption.widgetValue) ? new Date() : configOption.widgetValue
		});
		
		return this[configOption.widgetName+"Field"];
	},
	
	addButton : function( panel, button ){
		if( panel.buttons ){
			panel.buttons[panel.buttons.length] = button;
			panel.doLayout();
		}
	},
	printBtnClicked : function(){

	},
	
	setPatientInfo : function( patientInfo ){
		if( Ext.isEmpty( patientInfo )){
			patientInfo = {};
		}
		this.referenceNumber = patientInfo.referenceNumber;
		this.referenceType = patientInfo.referenceType;
		this.patientId = patientInfo.patientId;
	},
	
	prepareStoreData : function( dataList ){
		if( !Ext.isEmpty( dataList )){
			for( var i = 0 ; i < dataList.length ; i++){
				if( dataList[i].fieldType === "number"){
					this.graphForItems[dataList[i].code] = true;
					this[dataList[i].code.toLowerCase()+"Checkbox"] = new Ext.form.Checkbox({
			        	name: dataList[i].code,
			        	checked : true,
			        	boxLabel : dataList[i].name
				 	});
					
					this.onChecked( this[dataList[i].code.toLowerCase()+"Checkbox"] );
					
					this.checkBoxList.push({
						border : false,
						columnWidth : .3,
						layout : 'form',
						items:this[dataList[i].code.toLowerCase()+"Checkbox"]
					});
					this.series.push({yField : dataList[i].code,displayName : dataList[i].name,
						style: {
							size: 7,
							borderColor:this.colorForItem[dataList[i].code],
				            fillColor:"ffffff",
				            lineColor : this.colorForItem[dataList[i].code],
				            connectPoints:true
				        }});
					this.fields.push(dataList[i].code);
				}

			}
		}
	}

});