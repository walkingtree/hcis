Ext.namespace("administration.addBed");

administration.addBed.BedFeaturesPanel = function(/* Object */config) {
	Ext.apply(this,config);
	
	this.retFeaturesList = Array();
	
	var featuresList = config.data;
	this.bedList = new Array(); 
	var list = Array();
	var i = 0;
	for(i = 0; i < featuresList.length; i++) {
		var tmpFeature = featuresList[i];
		var item = new Ext.form.Checkbox({
				boxLabel: tmpFeature.data['description'],
//				id: config.suffix ? config.source + config.suffix + 'bedfeature-id' + i : '',
				name: tmpFeature.data['featureName'],
				checked : tmpFeature.data['availabilityFlag']
		});
		
		item.on('check', function(thisCbx, isChecked) {
		if (isChecked) {
			var bedSpecialFeatureAvailabilityBM = {
				featureName : thisCbx.name,
				description : thisCbx.boxLabel,
				availabilityFlag : true
			};
			this.retFeaturesList[thisCbx.name] = bedSpecialFeatureAvailabilityBM;
		} else {
			this.retFeaturesList[thisCbx.name] = "";
		}
		this.bedFeaturesFldSet.fireEvent( 'facilitychecked',thisCbx, isChecked );
//		Ext.ux.event.Broadcast.publish(config.source + "-bedfeaturechecked", this, thisCbx, isChecked);
	}, this);
		
		list[i] = item;
		this.bedList[item.name] = item;
	}
	this.checkBoxGroup = new Ext.form.CheckboxGroup({
		 border : true,
    	 columns: 5,
         items: list
	});
	this.bedFeaturesFldSet = new Ext.form.FieldSet({
			height:'100%',
			title:config.title ? config.title : 'Facilities',
			labelSeparator:' ',
			labelWidth:.01,
			items:this.checkBoxGroup
		});
	
	this.bedFeaturesFldSet.addEvents('facilitychecked');
//		{
//				xtype : 'checkboxgroup',
////	            fieldLabel: 'Facilities',
//	            border : true,
//	            columns: 6,
//	            items: list
//			}
		// In modify bed case we need to show already selected bed Special features list
		if(config.mode == 'edit' && !Ext.isEmpty( config.assignedSpecialFeaturesList )){
			for(var i=0;i<config.assignedSpecialFeaturesList.length; i++){
				var specialFeature = config.assignedSpecialFeaturesList[i];
				var bedSpecialFeatureAvailabilityBM = {
					featureName : specialFeature.featureName,
					description : specialFeature.description,
					availabilityFlag : true
				};
				this.retFeaturesList[specialFeature.featureName] = bedSpecialFeatureAvailabilityBM;
				for(var j=0; j<list.length;j++){
					if(list[j].name == specialFeature.featureName){
						list[j].setValue('true');
					}
				}
			}
			
		}
		
	this.getPanel = function() {
		return this.bedFeaturesFldSet;
	};
	
	this.getData = function() {
		var retList = Array();
		var i = 0;
		for (var key in this.retFeaturesList) {
			if (!Ext.isEmpty(this.retFeaturesList[key]) && (key != 'contains') && (key != 'remove') && (key != 'indexOf')&& (key != 'removeItem')) {
				retList[i] = {
					featureName : this.retFeaturesList[key].featureName,
					description : this.retFeaturesList[key].description,
					availabilityFlag : this.retFeaturesList[key].availabilityFlag
				};
				i++;
			}
		
		}
		
		return retList;
	};
	
	this.getCodeAndDescriptionData = function() {
		var retList = Array();
		var i = 0;
		for (var key in this.retFeaturesList) {
			if (!Ext.isEmpty(this.retFeaturesList[key]) && (key != 'contains') && (key != 'remove') && (key != 'indexOf')&& (key != 'removeItem')) {
				retList[i] = {
					code : this.retFeaturesList[key].featureName,
					description : this.retFeaturesList[key].description
				};
				i++;
			}
		
		}
		
		return retList;
	};
	this.getStringData = function(){
		var retList = Array();
		var i = 0;
		for (var key in this.retFeaturesList) {
			if (!Ext.isEmpty(this.retFeaturesList[key]) && (key != 'contains') && (key != 'remove') && (key != 'indexOf')&& (key != 'removeItem')) {
				retList[i] = this.retFeaturesList[key].featureName;
				i++;
			}
		}
		
		return retList;
	};
	
	this.loadData = function( config ){
		var list = this.checkBoxGroup.items.items;
		if(config.mode == 'edit'){
			for(var i=0;i<config.dataList.length; i++){
				var specialFeature = config.dataList[i];
				var bedSpecialFeatureAvailabilityBM = {
					featureName : specialFeature.code,
					description : specialFeature.description,
					availabilityFlag : true
				};
				this.retFeaturesList[specialFeature.code] = bedSpecialFeatureAvailabilityBM;
				for(var j=0; j<list.length;j++){
					if(list[j].name == specialFeature.code){
						list[j].setValue('true');
					}
				}
			}
			
		}
	};
};