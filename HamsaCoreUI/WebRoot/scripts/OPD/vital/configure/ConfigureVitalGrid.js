Ext.namespace("OPD.vital.configure");

OPD.vital.configure.ConfigureVitalGrid = Ext.extend(Ext.grid.GridPanel,{
	
	initComponent: function(){
		this.vitalFieldBMList = this.initialConfig.vitalFieldBMList;
		this.rows =[];
		this.fields=[];
		this.data=[];
		this.columns=[];
		this.generateHeaders(this.vitalFieldBMList);
		this.reader = new Ext.data.ArrayReader({},this.fields);
		
		var group = new Ext.ux.plugins.GroupHeaderGrid({
	        rows: [this.rows]
	    });
		
	    this.remoteSort =true;
	  	this.frame = false;
		this.border = false;
		this.height = 200;
		this.width = '100%';
		this.stripeRows =true;
		this.autoScroll =true;
		this.viewConfig = {forceFit :true};
		this.store = new Ext.data.GroupingStore({
//            fields: this.fields,
			reader : this.reader,
            data : this.data,
            sortInfo:{field: 'DTM', direction: "ASC"},
            groupField : "DTM"
            
        });
		
		this.view = new Ext.grid.GroupingView({
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "Items" : "Item"]})',
        	hideGroupedColumn:true
        })
		
		this.on('render',function(){
			var temp;
		},this);
		
		
		this.plugins = group;
		
		OPD.vital.configure.ConfigureVitalGrid.superclass.initComponent.apply(this,arguments);
	},
	
	/**
	 *  This method will generate group header for the grid.
	 */
	
	generateHeaders : function( vitalFieldBMList ){
		var groupsHeaders = {};
		var temp = null;
		for( var i = 0 ; i < vitalFieldBMList.length ; i++){
			vitalFielldBM = vitalFieldBMList[i];
			if( Ext.isEmpty( groupsHeaders[vitalFielldBM.fieldGroup])){
				if( Ext.isEmpty( temp)){
					temp = 1;
					this.rows.push({
//						header : "Time",
						align  : 'center',
						colspan: 1
					});
					this.columns.push({
		                dataIndex: vitalMsg.TIME,
		                header:"Time" ,
		                width:100
					});
					this.fields.push({name : vitalMsg.TIME});

				}else{
					temp++;
				}
				groupsHeaders[""+temp] = vitalFielldBM.fieldGroup;
				groupsHeaders[vitalFielldBM.fieldGroup] = new Array();
				this.rows.push({
					header : vitalFielldBM.fieldGroup,
					align  : 'center',
		            colspan: 0
				});
				
			}
			var config = {};
			config["code"] = vitalFielldBM.code;
			config["name"] = vitalFielldBM.name;
			
			groupsHeaders[vitalFielldBM.fieldGroup].push( config );
			this.fields.push({
				name : vitalFielldBM.code
			});
			this.columns.push({
                dataIndex: vitalFielldBM.code,
                header:vitalFielldBM.name ,
                width:100
			});
			this.rows[temp].colspan++;
	/*
	 *  This is for test.//
	 */		
			if( i === vitalFieldBMList.length -1 ){
				this.rows.push({
//					header : "Time",
					align  : 'center',
					colspan: 1
				});
				this.columns.push({
	                dataIndex: vitalMsg.DTM,
	                header:"Date" ,
	                width:100
				});
				this.fields.push({
					name : vitalMsg.DTM
				});
			}
			
		}
//		return groupsHeaders;
	},
	
//	setData : function( dataList ){
//		
//		var recordType = this.getStore().recordType;
//		var record = {};
//		var records = new Array();
//		if( !Ext.isEmpty( dataList )){
//			var temp = dataList[0].forTime;
//			record['TIME'] = dataList[0].forTime.format('g:i A');
//			record['DTM'] = dataList[0].forTime;
//			for( var i = 0; i < dataList.length ; i++){
//				if( temp.format('G') === dataList[i].forTime.format('G')
//					&& temp.format('i') === dataList[i].forTime.format('i')){
//						record[dataList[i].code] = dataList[i].value;
//						if( i === dataList.length-1){
//							var vitalRecord = new recordType( record );
//							this.getStore().add( vitalRecord );
//						}
//				}
//				else{
//					var vitalRecord = new recordType( record );
//					this.getStore().add( vitalRecord );
//					temp = dataList[i].forTime;
//					record['TIME'] = dataList[i].forTime.format('g:i A');
//					record['DTM'] = dataList[i].forTime;
//				}
//			}
//		}
//	},
	
	setData : function( dataList ){
		
		if( !Ext.isEmpty( this.getStore() )){
			this.getStore().removeAll();
		}
		var recordType = this.getStore().recordType;
		var record = {};
		this.records = new Array();
		if( !Ext.isEmpty( dataList )){
			var temp = dataList[0].forTime;
			record[vitalMsg.TIME] =  dataList[0].forTime.format('g:i A');
			for( var i = 0; i < dataList.length ; i++){
				if( temp.format('G') === dataList[i].forTime.format('G')
					&& temp.format('i') === dataList[i].forTime.format('i')){
						record[dataList[i].code]= dataList[i].value;
						if( i === dataList.length-1){
//							var vitalRecord = new recordType( record );
//							this.getStore().add( vitalRecord );
//							record.push(dataList[i].forTime);
							record[vitalMsg.DTM] = dataList[i].forTime;
							this.getStore().add(new recordType( record ));
//							this.records.push(record);
//							this.getStore().loadData(this.records);
							var test;
//							this.store.sortInfo = {field: 'DTM', direction: "ASC"};
//							this.store.groupField = "DTM";
						}
				}
				else{
//					var vitalRecord = new recordType( record );
					
//					this.getStore().add( vitalRecord );
					record[vitalMsg.DTM] = dataList[i-1].forTime;
					this.getStore().add(new recordType( record ));
//					this.records.push(record);
					record = {};
					temp = dataList[i].forTime;
					record[vitalMsg.TIME] = dataList[i].forTime.format('g:i A');
					record[dataList[i].code] = dataList[i].value;
//					record.push(dataList[i].forTime);
				}
			}
		}
	}

	
});
